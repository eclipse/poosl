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
import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.poosl.pooslproject.PooslProjectConstant;
import org.eclipse.poosl.rotalumisclient.Activator;
import org.eclipse.poosl.rotalumisclient.PooslConstants;
import org.eclipse.poosl.xtext.GlobalConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.osgi.framework.Bundle;

/**
 * The LaunchConfigurationPooslTab.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class LaunchConfigurationPooslTab extends AbstractLaunchConfigurationTab {

    // UI text
    private static final String MODEL_GROUP = "Model";

    private static final String EXTERN_CFG_GROUP = "External port configuration";

    private static final String GROUP_SIM = "Simulator settings";

    private static final String BTN_RANDOM = "Random";

    private static final String BTN_BROWSE = "Browse";

    private static final String LABEL_SEED = "Seed for resolving non-determinism:";

    private static final String LABEL_PORT = "Server port:";

    private static final String LABEL_FORCE_CHARSET = "Avoid charset verification (Assuming "
            + PooslProjectConstant.SUPPORTED_CHARSET.displayName() + " compatible)";

    private static final String LABEL_QUIET = "Quiet mode:";

    private static final String LABEL_PORTINFO = "The server port is only used for connecting with the simulator in debug mode.\n"
            + "To run debug sessions concurrently, use a different port for each run configuration.\nFor more information check the user manual.";

    // File Dialog
    private static final String FILE_DIALOG_TITLE = "Select poosl model";

    private static final String FILE_DIALOG_MESSAGE = "Select POOSL model to execute from the workspace:";

    private static final String EXTERN_CONFIG_DIALOG_TITLE = "Select the file with the extern port configuration:";

    private static final String FILE_NAME_FILTER = "*."; //$NON-NLS-1$

    // Validation
    private static final String VALIDATION_FILE_EMPTY = "{0} path cannot be empty";

    private static final String VALIDATION_FILE_EXTENSION = "The {0} is not a .{1} file";

    private static final String VALIDATION_FILE_IS_DIR = "The {0} path is a directory and not a .{1} file";

    private static final String VALIDATION_FILE_NOT_EXIST = "The selected {0} file does not exist";

    private static final String VALIDATION_SEED_EMPTY = "Seed for resolving non-determinism cannot be empty";

    private static final String VALIDATION_SEED_NOT_INT = "Seed for resolving non-determinism is not a valid integer";

    private static final String VALIDATION_PORT_EMPTY = "Simulator port cannot be empty";

    private static final String VALIDATION_PORT_NOT_INT = "Simulator port is not a valid integer";

    private static final String VALIDATION_PORT_INVALID = "Simulator port cannot be < 1";

    private static final Logger LOGGER = Logger
            .getLogger(LaunchConfigurationPooslTab.class.getName());

    // UI components
    private Composite control;

    private Text modelPathTextControl;

    private Text externPathTextControl;

    private Button forceCharsetControl;

    private Text portTextControl;

    private Text seedTextControl;

    private Text stackSizeTextControl;

    private Button randomSeedButton;

    private Button quietButton;

    // Relative model info
    private String projectName = ""; //$NON-NLS-1$

    private String relativePath = ""; //$NON-NLS-1$

    private void browseModel() {

        ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(getShell(),
                new WorkbenchLabelProvider(), new BaseWorkbenchContentProvider());
        dialog.addFilter(new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                if (element instanceof IContainer) {
                    return true;
                }
                if (!(element instanceof IFile)) {
                    return false;
                }
                IFile file = (IFile) element;
                return GlobalConstants.FILE_EXTENSION.equals(file.getFileExtension());
            }
        });

        dialog.setTitle(FILE_DIALOG_TITLE);
        dialog.setMessage(FILE_DIALOG_MESSAGE);
        dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
        dialog.setAllowMultiple(false);

        if (dialog.open() == Dialog.OK && dialog.getFirstResult() instanceof IFile) { // ignore container selection
            IFile selection = (IFile) dialog.getFirstResult();
            setModelPathText(selection.getFullPath().toPortableString());
            setRelativeElements();
            scheduleUpdateJob();
        }

    }

    private void browseExternPath() {
        FileDialog fDialog = new FileDialog(getShell());
        fDialog.setFilterExtensions(
                new String[] { FILE_NAME_FILTER + PooslConstants.EXTERN_CONFIG_EXTENSION });
        fDialog.setFilterPath(getFileSelectPath(externPathTextControl));
        fDialog.setText(EXTERN_CONFIG_DIALOG_TITLE);
        String filePath = fDialog.open();
        if (filePath != null) {
            externPathTextControl.setText(filePath);
            scheduleUpdateJob();
        }
    }

    private void setModelPathText(String path) {
        String text = path;
        if (path == null) {
            text = "";
        } else if (path.isEmpty() || IPath.SEPARATOR != path.charAt(0)) {
            text = path;
        } else {
            text = path.substring(1);
        }
        modelPathTextControl.setText(text);
    }

    private String getFileSelectPath(Text textControl) {
        String modelPath = textControl.getText();
        if (modelPath.isEmpty()) {
            return ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
        } else {
            return modelPath;
        }
    }

    @Override
    public void createControl(Composite parent) {
        control = new Composite(parent, SWT.NONE);
        control.setLayout(new GridLayout(1, false));

        modelPathTextControl = createBrowsableText(control, MODEL_GROUP, evt -> {
            setRelativeElements();
            scheduleUpdateJob();
        }, evt -> browseModel());
        forceCharsetControl = new Button(control, SWT.CHECK);
        forceCharsetControl.setText(LABEL_FORCE_CHARSET);
        forceCharsetControl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        forceCharsetControl.addSelectionListener(
                SelectionListener.widgetSelectedAdapter(evt -> scheduleUpdateJob()));
        externPathTextControl = createBrowsableText(control, EXTERN_CFG_GROUP,
                evt -> scheduleUpdateJob(), evt -> browseExternPath());
        createSimulatorSettings();

        Label portInfoLabel = new Label(control, SWT.NONE);
        portInfoLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.BOTTOM, false, true));
        portInfoLabel.setText(LABEL_PORTINFO);
    }

    private void createSimulatorSettings() {
        Group serverGroup = new Group(control, SWT.SHADOW_OUT);
        serverGroup.setText(GROUP_SIM);
        serverGroup.setLayout(new GridLayout(3, false));
        serverGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        Label seedLabel = new Label(serverGroup, SWT.NONE);
        seedLabel.setText(LABEL_SEED);

        seedTextControl = new Text(serverGroup, SWT.BORDER);
        seedTextControl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        seedTextControl.setText(""); //$NON-NLS-1$
        seedTextControl.addModifyListener(evt -> scheduleUpdateJob());

        randomSeedButton = new Button(serverGroup, SWT.CHECK);
        randomSeedButton.setText(BTN_RANDOM);
        randomSeedButton.addSelectionListener(SelectionListener.widgetSelectedAdapter(evt -> {
            seedTextControl.setEnabled(!randomSeedButton.getSelection());
            scheduleUpdateJob();
        }));

        Label stackSizeLabel = new Label(serverGroup, SWT.NONE);
        stackSizeLabel.setText("Maximum stack size for data methods in bytes:");

        stackSizeTextControl = new Text(serverGroup, SWT.BORDER);
        stackSizeTextControl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        stackSizeTextControl.setText(""); //$NON-NLS-1$

        new Label(serverGroup, SWT.NONE);

        Label portLabel = new Label(serverGroup, SWT.NONE);
        portLabel.setText(LABEL_PORT);

        portTextControl = new Text(serverGroup, SWT.BORDER);
        portTextControl.setLayoutData(new GridData(SWT.FILL, SWT.WRAP, true, false));
        portTextControl.addModifyListener(evt -> scheduleUpdateJob());
        portTextControl.setText(""); //$NON-NLS-1$

        new Label(serverGroup, SWT.NONE);

        Label quietLabel = new Label(serverGroup, SWT.NONE);
        quietLabel.setText(LABEL_QUIET);

        quietButton = new Button(serverGroup, SWT.CHECK);
        quietButton.addSelectionListener(
                SelectionListener.widgetSelectedAdapter(evt -> scheduleUpdateJob()));

    }

    private Text createBrowsableText(
            Composite container, String label, ModifyListener onEdit,
            Consumer<SelectionEvent> browse) {
        Group group = new Group(control, SWT.SHADOW_OUT);
        group.setText(label);
        group.setLayout(new GridLayout(2, false));
        group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

        Text result = new Text(group, SWT.BORDER);
        result.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        result.setText(""); //$NON-NLS-1$
        result.addModifyListener(onEdit);

        Button browseButton = new Button(group, SWT.PUSH);
        browseButton.setText(BTN_BROWSE);
        browseButton.setLayoutData(new GridData(SWT.END, SWT.TOP, false, false));
        browseButton.addSelectionListener(SelectionListener.widgetSelectedAdapter(browse));
        return result;
    }

    @Override
    public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
        configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_FORCE_CHARSET, false);
        configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_PORT,
                PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_SERVER_PORT);
        configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_IS_RANDOM_SEED,
                PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_IS_RANDOM_SEED);
        configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SEED,
                PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_SEED);
        configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_IS_QUIET,
                PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_IS_QUIET);
        configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_MAX_STACKSIZE,
                PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_MAX_STACKSIZE_BYTES);
    }

    @Override
    public void initializeFrom(ILaunchConfiguration configuration) {
        if (configuration == null) {
            return;
        }
        try {
            projectName = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_PROJECT,
                    ""); //$NON-NLS-1$
            relativePath = configuration
                    .getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_RELATIVE_PATH, ""); //$NON-NLS-1$
            setModelPathText(relativePath);

            String externalConfigPath = configuration
                    .getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_EXTERNAL_CONFIG_PATH, ""); //$NON-NLS-1$
            if (!externalConfigPath.isEmpty()) {
                externPathTextControl.setText(externalConfigPath);
            }

            forceCharsetControl.setSelection(configuration
                    .getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_FORCE_CHARSET, false));

            String serverPort = configuration.getAttribute(
                    PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_PORT,
                    PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_SERVER_PORT);
            portTextControl.setText(serverPort);
            String stackSize = configuration.getAttribute(
                    PooslConstants.CONFIGURATION_ATTRIBUTE_MAX_STACKSIZE,
                    PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_MAX_STACKSIZE_BYTES);
            stackSizeTextControl.setText(stackSize);
            String seed = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SEED,
                    PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_SEED);
            seedTextControl.setText(seed);
            boolean boolAttribute = configuration.getAttribute(
                    PooslConstants.CONFIGURATION_ATTRIBUTE_IS_RANDOM_SEED,
                    PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_IS_RANDOM_SEED);
            randomSeedButton.setSelection(boolAttribute);
            boolean quietAttribute = configuration.getAttribute(
                    PooslConstants.CONFIGURATION_ATTRIBUTE_IS_QUIET,
                    PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_IS_QUIET);
            quietButton.setSelection(quietAttribute);
            seedTextControl.setEnabled(!boolAttribute);
        } catch (CoreException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }

    }

    @Override
    public void performApply(ILaunchConfigurationWorkingCopy configuration) {
        configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_PROJECT, projectName);
        configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_RELATIVE_PATH,
                relativePath);
        configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_EXTERNAL_CONFIG_PATH,
                externPathTextControl.getText());
        configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_FORCE_CHARSET,
                forceCharsetControl.getSelection());
        configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_PORT,
                portTextControl.getText());
        configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_MAX_STACKSIZE,
                stackSizeTextControl.getText());
        configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SEED,
                seedTextControl.getText());
        configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_IS_RANDOM_SEED,
                randomSeedButton.getSelection());
        configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_IS_QUIET,
                quietButton.getSelection());
    }

    @Override
    public boolean isValid(ILaunchConfiguration launchConfig) {
        String errorMessage = Stream.of(//
                validateModel(), //
                validateExternConfiguration(), //
                validatePort(), //
                validateSeed()) //
                .filter(it -> it != null && !it.isEmpty())//
                .findFirst().orElse(null);

        setErrorMessage(errorMessage);
        return errorMessage == null || errorMessage.isEmpty();
    }

    private String validateSeed() {
        if (!seedTextControl.getText().isEmpty() && !randomSeedButton.getSelection()) {
            if (!isInteger(seedTextControl.getText())) {
                return VALIDATION_SEED_NOT_INT;
            }
        } else if (!randomSeedButton.getSelection()) {
            return VALIDATION_SEED_EMPTY;
        }
        return null;
    }

    private String validatePort() {
        if (portTextControl.getText().isEmpty()) {
            return VALIDATION_PORT_EMPTY;
        }
        if (!isInteger(portTextControl.getText())) {
            return VALIDATION_PORT_INVALID;
        }
        try {
            if (Integer.parseInt(portTextControl.getText()) < 1) {
                return VALIDATION_PORT_INVALID;
            }
        } catch (NumberFormatException e) {
            return VALIDATION_PORT_NOT_INT;
        }

        return null;
    }

    private String validateModel() {
        String text = modelPathTextControl.getText();
        String realPath = text;
        if (!text.isEmpty()) {
            IPath ePath;
            try {
                ePath = ResourcesPlugin.getWorkspace().getRoot()
                        .getFile(Path.fromPortableString(IPath.SEPARATOR + text)).getLocation();
            } catch (IllegalArgumentException invalidPath) {
                return MessageFormat.format(VALIDATION_FILE_NOT_EXIST, MODEL_GROUP,
                        GlobalConstants.FILE_EXTENSION);
            }
            if (ePath != null) {
                realPath = ePath.toOSString();
            }
        }
        return validateExistingFile(MODEL_GROUP, realPath, GlobalConstants.FILE_EXTENSION);
    }

    private String validateExternConfiguration() {
        String text = externPathTextControl.getText();
        return text.isEmpty() //
            ? null //
            : validateExistingFile(EXTERN_CFG_GROUP, text, PooslConstants.EXTERN_CONFIG_EXTENSION);
    }

    private static String validateExistingFile(String role, String path, String extension) {
        if (path.isEmpty()) {
            return MessageFormat.format(VALIDATION_FILE_EMPTY, role, extension);
        }
        File file = new File(path);
        if (!file.exists()) {
            return MessageFormat.format(VALIDATION_FILE_NOT_EXIST, role, extension);
        }
        if (file.isDirectory()) {
            return MessageFormat.format(VALIDATION_FILE_IS_DIR, role, extension);
        }
        String filename = file.getName();
        int ext = filename.lastIndexOf('.');

        if (ext == -1 || !extension.equals(filename.substring(ext + 1))) {
            return MessageFormat.format(VALIDATION_FILE_EXTENSION, path, extension);
        }
        return null;
    }

    public static boolean isInteger(String s) {
        try (Scanner sc = new Scanner(s.trim())) {
            if (!sc.hasNextInt(10)) {
                return false;
            }
            sc.nextInt(10);
            return !sc.hasNext();
        }
    }

    @Override
    public boolean canSave() {
        return true;
    }

    @Override
    public String getName() {
        return "Simulator";
    }

    @Override
    public Control getControl() {
        return control;
    }

    @Override
    public Image getImage() {
        Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
        Path path = new Path("icons/poosl_simulation.gif"); //$NON-NLS-1$
        URL fileURL = FileLocator.find(bundle, path, null);
        InputStream inputStream = null;
        try {
            inputStream = fileURL.openStream();
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            return null;
        }
        return new Image(Display.getDefault(), inputStream);
    }

    private void setRelativeElements() {
        projectName = ""; //$NON-NLS-1$
        relativePath = ""; //$NON-NLS-1$

        String modelLocation = modelPathTextControl.getText();
        if (modelLocation.isEmpty()) {
            return;
        }
        relativePath = IPath.SEPARATOR + modelLocation;

        int index = modelLocation.indexOf(IPath.SEPARATOR);
        projectName = (index > -1) //
            ? modelLocation.substring(0, index) //
            : modelLocation;
    }
}
