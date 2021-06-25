package nl.esi.poosl.rotalumisclient.launch;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBException;

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
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.eclipse.ui.model.WorkbenchPartLabelProvider;
import org.eclipse.ui.progress.UIJob;

import nl.esi.poosl.rotalumisclient.Client;
import nl.esi.poosl.rotalumisclient.Messages;
import nl.esi.poosl.rotalumisclient.PooslConstants;
import nl.esi.poosl.rotalumisclient.debug.PooslDebugTarget;
import nl.esi.poosl.rotalumisclient.debug.SimulatorTerminationWatcher;
import nl.esi.poosl.rotalumisclient.logging.PooslLogger;
import nl.esi.poosl.rotalumisclient.runner.RotalumisRunner;
import nl.esi.poosl.xtext.importing.ImportingHelper;

public class LaunchDelegate implements ILaunchConfigurationDelegate {
	private static final String OPTION_FILE_POOSL = "--poosl";
	private static final String OPTION_DEBUG = "--debug";
	private static final String OPTION_STACKSIZE = "-l";
	private static final String OPTION_SEED = "-d";
	private static final String OPTION_PORT = "-p";
	private static final String OPTION_INCLUDE_LIB = "-I";
	private static final String OPTION_BASIC_CLASSES = "--basic-classes";
	private static final String OPTION_TEST = "--unittest";
	private static final String OPTION_EXTERNAL_CONFIG = "-e";
	private static final String OPTION_QUIET = "--quiet";

	private static final Logger LOGGER = Logger.getLogger(LaunchDelegate.class.getName());
	private static final Random random = new Random();

	@Override
	public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor)
			throws CoreException {
		PooslLogger.setup();
		monitor.beginTask("Starting simulation.", 3);
		monitor.setTaskName("Validating model.");

		if (validateConfiguration(configuration)) {
			try {
				final String confProjectName = configuration
						.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_PROJECT, "");
				IProject project = getProject(confProjectName);

				if (checkForDirtyFiles(project)) {
					terminateLaunch(launch);
					return;
				}
				final String confModelPath = configuration
						.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_MODEL_PATH, "");
				final String externalConfigPath = configuration
						.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_EXTERNAL_CONFIG_PATH, "");
				final String confLaunchMode = launch.getLaunchMode();
				final String confMaxStackSize = configuration.getAttribute(
						PooslConstants.CONFIGURATION_ATTRIBUTE_MAX_STACKSIZE,
						PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_MAX_STACKSIZE_BYTES);
				final String confRotalumisPath = RotalumisRunner.getRotalumis().getAbsolutePath();
				final String confSeed = getSimulationSeed(configuration);
				final boolean confIsQuiet = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_IS_QUIET,
						PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_IS_QUIET);
				final String confServerPort = configuration.getAttribute(
						PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_PORT,
						PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_SERVER_PORT);
				final String confServerIp = PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_IP;
				final List<String> includes = getIncludePaths(project);
				final boolean confTest = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_TEST_CONF,
						false);

				if (confLaunchMode.equals(ILaunchManager.DEBUG_MODE)
						&& !isServerPortAvailable(confServerPort, launch)) {
					return;
				}

				StringBuilder loggingBuilder = new StringBuilder();
				loggingBuilder.append("Starting Simulation\n\tmode: ").append(confLaunchMode);
				loggingBuilder.append("\n\tSimulator location: ").append(confRotalumisPath);
				loggingBuilder.append("\n\tModel location: ").append(confModelPath);
				loggingBuilder.append("\n\tSimulator seed: ").append(confSeed);
				loggingBuilder.append("\n\tip & port: ").append(confServerIp + ":" + confServerPort);
				loggingBuilder.append("\n\tMax Stack size: ").append(confMaxStackSize);
				loggingBuilder.append("\n\tRun as Test: ").append(confTest);
				loggingBuilder.append("\n\tExternal Config: ").append(externalConfigPath);
				LOGGER.info(loggingBuilder.toString());

				monitor.worked(2);
				monitor.setTaskName("Starting Rotalumis");

				// Determining Rotalumis command based on launchmode
				// Creating command list to start Rotalumis process
				List<String> commandList = new ArrayList<>();

				commandList.add(confRotalumisPath);
				if (mode.equals(ILaunchManager.RUN_MODE)) {
					if (confTest) {
						commandList.add(OPTION_DEBUG);
						commandList.add(OPTION_TEST);
					} else {
						commandList.add(OPTION_FILE_POOSL);
					}
					commandList.add(confModelPath);

					if (externalConfigPath != null && !externalConfigPath.isEmpty()) {
						commandList.add(OPTION_EXTERNAL_CONFIG);
						commandList.add(externalConfigPath);
					}
					if (!ImportingHelper.useDefaultBasicclasses()) {
						commandList.add(OPTION_BASIC_CLASSES);
						commandList.add(ImportingHelper.getBasicAbsoluteString());
					}
					for (String include : includes) {
						commandList.add(OPTION_INCLUDE_LIB);
						commandList.add(include);
					}
				} else if (confLaunchMode.equals(ILaunchManager.DEBUG_MODE)) {
					commandList.add(OPTION_PORT);
					commandList.add(confServerPort);
					// debug basic classes will be set during compile request
				}

				commandList.add(OPTION_SEED);
				commandList.add(confSeed);
				commandList.add(OPTION_STACKSIZE);
				commandList.add(confMaxStackSize);
				if (confIsQuiet) {
					commandList.add(OPTION_QUIET);
				}

				String[] commandLine = commandList.toArray(new String[commandList.size()]);
				LOGGER.info("Starting Rotalumis process: " + Arrays.toString(commandLine));

				// Switching to working directory
				File workingDirectory = createWorkingDirectory(confModelPath);

				// Execute Rotalumis process
				Process proc = DebugPlugin.exec(commandLine, workingDirectory);

				// Create Iprocess based on Rotalumis process and add it to the
				// current launch
				IProcess process = DebugPlugin.newProcess(launch, proc, "[Seed: " + confSeed + "]");
				LOGGER.finest("Platform default charset: " + System.getProperty(PooslConstants.PLATFORM_ENCODING));

				if (confLaunchMode.equals(ILaunchManager.RUN_MODE)) {
					// When in run mode start a thread and wait for process
					// termination.
					Thread simulatorTerminationWatcher = new Thread(
							new SimulatorTerminationWatcher(null, proc, confProjectName));
					simulatorTerminationWatcher.setName("Simulator termination watcher");
					simulatorTerminationWatcher.start();
					monitor.done();
				} else if (confLaunchMode.equals(ILaunchManager.DEBUG_MODE)) {
					// Create a client to connect to the running Rotalumis
					// process
					Client client = new Client(PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_IP,
							Integer.valueOf(confServerPort));

					// Create a debugtarget to take responsibility of
					// debugging commands
					PooslDebugTarget pooslDebugTarget = new PooslDebugTarget(launch, process, client, proc, includes);
					monitor.setTaskName("Compiling Model and gathering debug information");
					pooslDebugTarget.start();
				}
				monitor.setTaskName("Starting simulation");
				monitor.done();

			} catch (JAXBException | IOException | InterruptedException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
				IStatus status = new Status(Status.ERROR, PooslConstants.PLUGIN_ID,
						e.getMessage() + (e.getCause() != null ? "\n\n" + e.getCause().getMessage() : ""), e);
				throw new CoreException(status);
			}
		}
	}

	private IProject getProject(String projectName) {
		if (projectName != null && !projectName.isEmpty()) {
			return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		}
		return null;
	}

	private String getSimulationSeed(ILaunchConfiguration configuration) throws CoreException {
		final boolean isRandomSeed = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_IS_RANDOM_SEED,
				PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_IS_RANDOM_SEED);
		String seed;
		if (isRandomSeed) {
			seed = Integer.toString(random.nextInt(Integer.MAX_VALUE));
		} else {
			String seedString = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SEED,
					PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_SEED);
			seed = (LaunchConfigurationPooslTab.isInteger(seedString)) ? seedString : "1";
		}
		return seed;
	}

	private List<String> getIncludePaths(IProject project) {
		List<String> includes = ImportingHelper.getIncludes(project);
		List<String> absolute = new ArrayList<>();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		for (Iterator<String> it = includes.iterator(); it.hasNext();) {
			String include = it.next();
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

	private boolean checkForDirtyFiles(final IProject project) throws CoreException, InterruptedException {
		final Map<IEditorPart, IWorkbenchPage> dirtyResources = new HashMap<>();
		if (project != null) {

			IWorkbench workbench = PlatformUI.getWorkbench();
			if (workbench != null) {
				for (IWorkbenchWindow workbenchWindow : workbench.getWorkbenchWindows()) {
					for (IWorkbenchPage workbenchPage : workbenchWindow.getPages()) {
						for (IEditorReference editorReference : workbenchPage.getEditorReferences()) {
							if (editorReference.isDirty()) {
								IEditorInput editorInput = editorReference.getEditorInput();
								if (editorInput instanceof IFileEditorInput) {
									IFileEditorInput fileEditorInput = (IFileEditorInput) editorInput;
									String filePath = fileEditorInput.getStorage().getFullPath().toString();
									if (filePath.startsWith("/" + project.getName() + "/")) {
										dirtyResources.put(editorReference.getEditor(true), workbenchPage);
									}
								}
							}
						}
					}
				}
			}
		}

		if (!dirtyResources.isEmpty()) {
			final IEditorPart[] dirtyEditorParts = new IEditorPart[dirtyResources.size()];
			int i = 0;
			for (IEditorPart editorPart : dirtyResources.keySet()) {
				dirtyEditorParts[i] = editorPart;
				i++;
			}
			UIJob uiJob = new UIJob("") {

				@Override
				public IStatus runInUIThread(IProgressMonitor monitor) {
					ListSelectionDialog resourceSelectionDialog = new ListSelectionDialog(
							Display.getDefault().getActiveShell(), dirtyEditorParts, new ArrayContentProvider(),
							new WorkbenchPartLabelProvider(),
							"The following file(s) contain unsaved changes.\nSelect files to save.");
					resourceSelectionDialog.setInitialSelections((Object[]) dirtyEditorParts);
					int userInput = resourceSelectionDialog.open();
					if (userInput == SelectionDialog.OK) {
						Object[] result = resourceSelectionDialog.getResult();
						for (Object resultObject : result) {
							IEditorPart dirtyResourcePart = (IEditorPart) resultObject;
							dirtyResources.get(dirtyResourcePart).saveEditor(dirtyResourcePart, false);
						}
					} else if (userInput == SelectionDialog.CANCEL) {
						return new Status(IStatus.CANCEL, PooslConstants.PLUGIN_ID, "");
					}
					return new Status(IStatus.OK, PooslConstants.PLUGIN_ID, "");
				}
			};
			uiJob.schedule();
			try {
				uiJob.join();
			} catch (InterruptedException e1) {
				LOGGER.log(Level.WARNING, "LaunchDelegate could not join UIJob for unsaved model dialog", e1);
				throw e1;
			}
			IStatus result = uiJob.getResult();
			if (result.getSeverity() == IStatus.CANCEL) {
				return true;
			}
		}
		return false;
	}

	private void terminateLaunch(ILaunch launch) throws DebugException {
		launch.terminate();
		ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
		launchManager.removeLaunch(launch);
	}

	private boolean isServerPortAvailable(final String serverPort, final ILaunch myLaunch) throws CoreException {
		ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
		for (final ILaunch launch : launchManager.getLaunches()) {
			String launchMode = launch.getLaunchMode();
			if (myLaunch != launch && !launch.isTerminated() && launchMode != null
					&& launchMode.equals(ILaunchManager.DEBUG_MODE)) {
				String activePort = launch.getLaunchConfiguration().getAttribute(
						PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_PORT,
						PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_SERVER_PORT);
				if (activePort.equals(serverPort)) {
					String inuse = "(by possibly by another model)";
					IDebugTarget debugTarget = launch.getDebugTarget();
					if (debugTarget != null) {
						String name = debugTarget.getName();
						if (name != null) {
							inuse = "by " + name;
						}
					}
					final String message = MessageFormat.format(Messages.DIALOG_NOTSTART_TEXT, serverPort, inuse);
					LOGGER.info(message);
					Display.getDefault().syncExec(new Runnable() {

						@Override
						public void run() {
							MessageDialog dialog = new MessageDialog(Display.getDefault().getActiveShell(),
									Messages.DIALOG_NOTSTART_TITLE,
									MessageDialog.getImage(Dialog.DLG_IMG_MESSAGE_ERROR), message, MessageDialog.ERROR,
									new String[] { Messages.DIALOG_NOTSTART_BT_TERMINATE,
											Messages.DIALOG_NOTSTART_BT_CANCEL },
									0);
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
		File workingDirectory = new File(modelPath.substring(0, modelPath.lastIndexOf(File.separator)) + File.separator
				+ PooslConstants.CONFIGURATION_ATTRIBUTE_SIMULATOR_INOUT_FOLDER);
		LOGGER.info("Working directory location: " + workingDirectory.getPath());
		if (!workingDirectory.exists() && !workingDirectory.mkdir()) {
			IStatus status = new Status(Status.ERROR, PooslConstants.PLUGIN_ID,
					"Could not create " + PooslConstants.CONFIGURATION_ATTRIBUTE_SIMULATOR_INOUT_FOLDER + " directory",
					null);
			LOGGER.log(Level.SEVERE, status.getMessage(), status);
			throw new CoreException(status);
		}
		return workingDirectory;
	}

	boolean validateConfiguration(ILaunchConfiguration configuration) throws CoreException {
		if (configuration != null) {
			String modelPath = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_MODEL_PATH, "");
			String serverPort = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_PORT,
					PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_SERVER_PORT);
			if (!"".equals(modelPath)) {
				if (!modelPath.endsWith(".poosl")) {
					IStatus status = new Status(Status.ERROR, PooslConstants.PLUGIN_ID,
							"The selected launch configuration does not contain a valid path to a poosl model.", null);
					LOGGER.log(Level.SEVERE, status.getMessage(), status);
					throw new CoreException(status);
				}
				File f = new File(modelPath);
				if (!f.exists()) {
					IStatus status = new Status(Status.ERROR, PooslConstants.PLUGIN_ID,
							"The model in the selected launch configuration does not exist. "
									+ "This error can occur after moving the associated model. "
									+ "\n\nRight click in the project explorer or editor, select \"Run as -> Poosl model\" or \"Debug as -> Poosl model\" to automatically update and start the launch configuration. "
									+ "\n\nFor more information on running or debugging models and editing the launch configuration open Help (F1) and go to \"Contents -> Poosl -> Simulate\"",
							null);
					throw new CoreException(status);
				}
			} else {
				IStatus status = new Status(Status.ERROR, PooslConstants.PLUGIN_ID,
						"The selected launch configuration does not contain a path to a model.", null);
				LOGGER.log(Level.SEVERE, status.getMessage(), status);
				throw new CoreException(status);
			}
			String errorMessage = null;
			if (serverPort.isEmpty() || !LaunchConfigurationPooslTab.isInteger(serverPort)) {
				errorMessage = "The selected launch configuration does not contain a valid server port.";
			}
			if (errorMessage != null && !errorMessage.isEmpty()) {
				IStatus status = new Status(Status.ERROR, PooslConstants.PLUGIN_ID, errorMessage, null);
				LOGGER.log(Level.SEVERE, status.getMessage(), status);
				throw new CoreException(status);
			}
		} else {
			IStatus status = new Status(Status.ERROR, PooslConstants.PLUGIN_ID,
					"The selected run configuration is not valid.", null);
			LOGGER.log(Level.SEVERE, status.getMessage(), status);
			throw new CoreException(status);
		}

		return true;
	}
}
