/*******************************************************************************
 * Copyright (c) 2021 TNO/ESI
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    TNO/ESI - initial API and implementation
 *    Obeo - refactoring
 *******************************************************************************/
package org.eclipse.poosl.rotalumisclient.launch;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.poosl.rotalumisclient.Activator;
import org.eclipse.poosl.rotalumisclient.Client;
import org.eclipse.poosl.rotalumisclient.Messages;
import org.eclipse.poosl.rotalumisclient.PooslConstants;
import org.eclipse.poosl.rotalumisclient.debug.PooslDebugTarget;
import org.eclipse.poosl.rotalumisclient.debug.SimulatorTerminationWatcher;
import org.eclipse.poosl.rotalumisclient.logging.PooslLogger;
import org.eclipse.poosl.rotalumisclient.runner.RotalumisRunner;
import org.eclipse.poosl.xtext.importing.ImportingHelper;
import org.eclipse.swt.widgets.Display;

/**
 * The LaunchDelegate.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class LaunchDelegate implements ILaunchConfigurationDelegate {
    private static final String OPTION_FILE_POOSL = "--poosl"; //$NON-NLS-1$

    private static final String OPTION_DEBUG = "--debug"; //$NON-NLS-1$

    private static final String OPTION_STACKSIZE = "-l"; //$NON-NLS-1$

    private static final String OPTION_SEED = "-d"; //$NON-NLS-1$

    private static final String OPTION_PORT = "-p"; //$NON-NLS-1$

    private static final String OPTION_INCLUDE_LIB = "-I"; //$NON-NLS-1$

    private static final String OPTION_BASIC_CLASSES = "--basic-classes"; //$NON-NLS-1$

    private static final String OPTION_TEST = "--unittest"; //$NON-NLS-1$

    private static final String OPTION_EXTERNAL_CONFIG = "-e"; //$NON-NLS-1$

    private static final String OPTION_QUIET = "--quiet"; //$NON-NLS-1$

    private static final Logger LOGGER = Logger.getLogger(LaunchDelegate.class.getName());

    private static final Random RANDOM = new Random();

    @Override
    public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws CoreException {
        PooslLogger.setup();
        monitor.beginTask("Starting simulation.", 3);
        monitor.setTaskName("Validating model.");

        checkConfiguration(configuration);
        try {
            IProject project = getProject(configuration);
            IFile model = getModel(configuration);
            boolean forceCharset = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_FORCE_CHARSET, false);

            if (model == null || !new ExecutableResourceVerification(model, forceCharset).isValid()) {
                terminateLaunch(launch);
                return;
            }

            String confModelPath = model.getLocation().toOSString();

            final String externalConfigPath = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_EXTERNAL_CONFIG_PATH, ""); //$NON-NLS-1$
            final String confLaunchMode = launch.getLaunchMode();
            final String confMaxStackSize = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_MAX_STACKSIZE, PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_MAX_STACKSIZE_BYTES);
            final String confRotalumisPath = RotalumisRunner.getRotalumis().getAbsolutePath();
            final String confSeed = getSimulationSeed(configuration);
            final boolean confIsQuiet = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_IS_QUIET, PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_IS_QUIET);
            final String confServerPort = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_PORT, PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_SERVER_PORT);
            final String confServerIp = PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_IP;
            final boolean confTest = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_TEST_CONF, false);

            if (confLaunchMode.equals(ILaunchManager.DEBUG_MODE) && !isServerPortAvailable(confServerPort, launch)) {
                return;
            }
            StringBuilder loggingBuilder = new StringBuilder();
            loggingBuilder.append("Starting Simulation\n\tmode: ").append(confLaunchMode); //$NON-NLS-1$
            loggingBuilder.append("\n\tSimulator location: ").append(confRotalumisPath); //$NON-NLS-1$
            loggingBuilder.append("\n\tModel location: ").append(model.getLocation().toOSString()); //$NON-NLS-1$
            loggingBuilder.append("\n\tSimulator seed: ").append(confSeed); //$NON-NLS-1$
            loggingBuilder.append("\n\tip & port: ").append(confServerIp + ":" + confServerPort); //$NON-NLS-1$ //$NON-NLS-2$
            loggingBuilder.append("\n\tMax Stack size: ").append(confMaxStackSize); //$NON-NLS-1$
            loggingBuilder.append("\n\tRun as Test: ").append(confTest); //$NON-NLS-1$
            loggingBuilder.append("\n\tExternal Config: ").append(externalConfigPath); //$NON-NLS-1$
            LOGGER.info(loggingBuilder.toString());

            monitor.worked(2);
            monitor.setTaskName("Starting Rotalumis");

            // Determining Rotalumis command based on launchmode
            // Creating command list to start Rotalumis process
            List<String> args = new ArrayList<>();

            args.add(confRotalumisPath);
            if (mode.equals(ILaunchManager.RUN_MODE)) {
                if (confTest) {
                    args.add(OPTION_DEBUG);
                    args.add(OPTION_TEST);
                } else {
                    args.add(OPTION_FILE_POOSL);
                }
                args.add(confModelPath);

                if (externalConfigPath != null && !externalConfigPath.isEmpty()) {
                    args.add(OPTION_EXTERNAL_CONFIG);
                    args.add(externalConfigPath);
                }
                if (!ImportingHelper.useDefaultBasicclasses()) {
                    args.add(OPTION_BASIC_CLASSES);
                    args.add(ImportingHelper.getBasicAbsoluteString());
                }
                for (String include : getIncludePaths(project)) {
                    args.add(OPTION_INCLUDE_LIB);
                    args.add(include);
                }
            } else if (confLaunchMode.equals(ILaunchManager.DEBUG_MODE)) {
                args.add(OPTION_PORT);
                args.add(confServerPort);
                // debug basic classes will be set during compile request
            }

            args.add(OPTION_SEED);
            args.add(confSeed);
            args.add(OPTION_STACKSIZE);
            args.add(confMaxStackSize);
            if (confIsQuiet) {
                args.add(OPTION_QUIET);
            }

            LOGGER.info("Starting Rotalumis process: " + args);

            // Switching to working directory
            File workingDirectory = createWorkingDirectory(confModelPath);

            // Execute Rotalumis process
            Process proc = DebugPlugin.exec(args.toArray(new String[args.size()]), workingDirectory);

            // Create Iprocess based on Rotalumis process and add it to the
            // current launch
            IProcess process = DebugPlugin.newProcess(launch, proc, "[Seed: " + confSeed + "]");
            LOGGER.finest("Platform default charset: " + System.getProperty(PooslConstants.PLATFORM_ENCODING));

            if (confLaunchMode.equals(ILaunchManager.RUN_MODE)) {
                // When in run mode start a thread and wait for process
                // termination.
                Thread runner = new Thread(new SimulatorTerminationWatcher(null, proc, project.getName()));
                runner.setName("Simulator termination watcher");
                runner.start();
            } else if (confLaunchMode.equals(ILaunchManager.DEBUG_MODE)) {
                // Create a client to connect to the running Rotalumis
                // process
                Client client = new Client(PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_IP, Integer.valueOf(confServerPort));
                monitor.setTaskName("Starting simulation");

                // Create a debugtarget to take responsibility of
                // debugging commands
                PooslDebugTarget runner = new PooslDebugTarget(launch, process, client, proc, getIncludePaths(project));
                monitor.setTaskName("Compiling Model and gathering debug information");
                runner.start();
            }
            monitor.done();

        } catch (IOException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            String errorMessage = e.getMessage();
            if (e.getCause() != null) {
                errorMessage += "\n\n" + e.getCause().getMessage(); //$NON-NLS-1$
            }
            throw new CoreException(new Status(Status.ERROR, Activator.PLUGIN_ID, errorMessage, e));
        }

    }

    private static IProject getProject(ILaunchConfiguration configuration) throws CoreException {
        String projectName = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_PROJECT, ""); //$NON-NLS-1$
        return (projectName != null && !projectName.isEmpty()) ? ResourcesPlugin.getWorkspace().getRoot().getProject(projectName) : null;
    }

    private static IFile getModel(ILaunchConfiguration configuration) throws CoreException {
        String filePath = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_RELATIVE_PATH, ""); //$NON-NLS-1$
        if (filePath == null || filePath.isEmpty()) {
            return null;
        }
        return ResourcesPlugin.getWorkspace().getRoot().getFile(Path.fromPortableString(filePath));
    }

    private static String getSimulationSeed(ILaunchConfiguration configuration) throws CoreException {
        final boolean isRandomSeed = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_IS_RANDOM_SEED, PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_IS_RANDOM_SEED);
        if (isRandomSeed) {
            return Integer.toString(RANDOM.nextInt(Integer.MAX_VALUE));
        }
        String seedString = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SEED, PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_SEED);
        return (LaunchConfigurationPooslTab.isInteger(seedString)) ? seedString : PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_SEED;
    }

    private static List<String> getIncludePaths(IProject project) {
        List<String> includes = ImportingHelper.getIncludes(project);
        List<String> absolute = new ArrayList<>();
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        for (String include : includes) {
            IPath includePath = new Path(include);
            if (!includePath.isAbsolute()) {
                IProject iProject = root.getProject(includePath.segment(0));
                if (iProject.getLocation() != null) {
                    if (includePath.segmentCount() == 1) {
                        includePath = iProject.getLocation().addTrailingSeparator();
                    } else {
                        IFolder folder = iProject.getFolder(includePath.removeFirstSegments(1));
                        includePath = folder.getLocation().addTrailingSeparator();
                    }
                    absolute.add(includePath.toOSString());
                }
            } else {
                absolute.add(includePath.toOSString());
            }
        }
        return absolute;
    }

    private static void terminateLaunch(ILaunch launch) throws DebugException {
        launch.terminate();
        ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
        launchManager.removeLaunch(launch);
    }

    private boolean isServerPortAvailable(final String serverPort, final ILaunch myLaunch) throws CoreException {
        ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
        for (final ILaunch launch : launchManager.getLaunches()) {
            String launchMode = launch.getLaunchMode();
            IDebugTarget debugTarget = launch.getDebugTarget();
            if (myLaunch != launch && !launch.isTerminated() && launchMode != null && launchMode.equals(ILaunchManager.DEBUG_MODE) && debugTarget instanceof PooslDebugTarget) {
                String activePort = launch.getLaunchConfiguration().getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_PORT, PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_SERVER_PORT);
                if (activePort.equals(serverPort)) {
                    String inuse = "(by possibly by another model)";
                    String name = debugTarget.getName();
                    if (name != null) {
                        inuse = "by " + name;
                    }
                    final String message = MessageFormat.format(Messages.DIALOG_NOTSTART_TEXT, serverPort, inuse);
                    LOGGER.info(message);
                    Display.getDefault().syncExec(new Runnable() {

                        @Override
                        public void run() {
                            MessageDialog dialog = new MessageDialog(Display.getDefault().getActiveShell(), Messages.DIALOG_NOTSTART_TITLE, MessageDialog.getImage(Dialog.DLG_IMG_MESSAGE_ERROR),
                                    message, MessageDialog.ERROR, new String[] { Messages.DIALOG_NOTSTART_BT_TERMINATE, Messages.DIALOG_NOTSTART_BT_CANCEL }, 0);
                            int result = dialog.open();
                            if (result == 0) {
                                try {
                                    terminateLaunch(launch);
                                    terminateLaunch(myLaunch);
                                    DebugUITools.launch(myLaunch.getLaunchConfiguration(), myLaunch.getLaunchMode());
                                } catch (DebugException e) {
                                    LOGGER.log(Level.WARNING, "Could not terminate launch", e);
                                }
                            } else {
                                try {
                                    terminateLaunch(myLaunch);
                                } catch (DebugException e) {
                                    LOGGER.log(Level.WARNING, "Could not terminate launch", e);
                                }
                            }
                        }
                    });
                    return false;
                }
            }
        }
        return true;
    }

    private File createWorkingDirectory(String modelPath) throws CoreException {
        File workingDirectory = new File(modelPath.substring(0, modelPath.lastIndexOf(File.separator)) + File.separator + PooslConstants.CONFIGURATION_ATTRIBUTE_SIMULATOR_INOUT_FOLDER);
        LOGGER.info("Working directory location: " + workingDirectory.getPath());
        if (!workingDirectory.exists() && !workingDirectory.mkdir()) {
            IStatus status = new Status(Status.ERROR, Activator.PLUGIN_ID, "Could not create " + PooslConstants.CONFIGURATION_ATTRIBUTE_SIMULATOR_INOUT_FOLDER + " directory", null);
            LOGGER.log(Level.SEVERE, status.getMessage(), status);
            throw new CoreException(status);
        }
        return workingDirectory;
    }

    private void checkConfiguration(ILaunchConfiguration configuration) throws CoreException {
        if (configuration == null) {
            IStatus status = new Status(Status.ERROR, Activator.PLUGIN_ID, "The selected run configuration is not valid.", null);
            LOGGER.log(Level.SEVERE, status.getMessage(), status);
            throw new CoreException(status);
        }
        String modelPath = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_RELATIVE_PATH, ""); //$NON-NLS-1$
        String serverPort = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_PORT, PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_SERVER_PORT);
        if (!"".equals(modelPath)) { //$NON-NLS-1$
            if (!modelPath.endsWith(".poosl")) { //$NON-NLS-1$
                IStatus status = new Status(Status.ERROR, Activator.PLUGIN_ID, //
                        "The selected launch configuration does not contain a valid path to a poosl model.", null);
                LOGGER.log(Level.SEVERE, status.getMessage(), status);
                throw new CoreException(status);
            }

            if (!ResourcesPlugin.getWorkspace().getRoot().getFile(Path.fromPortableString(modelPath)).exists()) {
                IStatus status = new Status(Status.ERROR, Activator.PLUGIN_ID, "The model in the selected launch configuration does not exist. "
                        + "This error can occur after moving the associated model. "
                        + "\n\nRight click in the project explorer or editor, select \"Run as -> Poosl model\" or \"Debug as -> Poosl model\" to automatically update and start the launch configuration. "
                        + "\n\nFor more information on running or debugging models and editing the launch configuration open Help (F1) and go to \"Contents -> Poosl -> Simulate\"", null);
                throw new CoreException(status);
            }
        } else {
            IStatus status = new Status(Status.ERROR, Activator.PLUGIN_ID, "The selected launch configuration does not contain a path to a model.", null);
            LOGGER.log(Level.SEVERE, status.getMessage(), status);
            throw new CoreException(status);
        }
        String errorMessage = null;
        if (serverPort.isEmpty() || !LaunchConfigurationPooslTab.isInteger(serverPort)) {
            errorMessage = "The selected launch configuration does not contain a valid server port.";
        }
        if (errorMessage != null && !errorMessage.isEmpty()) {
            IStatus status = new Status(Status.ERROR, Activator.PLUGIN_ID, errorMessage, null);
            LOGGER.log(Level.SEVERE, status.getMessage(), status);
            throw new CoreException(status);
        }

    }
}
