package nl.esi.poosl.rotalumisclient.launch;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
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
import org.osgi.framework.Bundle;

import nl.esi.poosl.rotalumisclient.PooslConstants;

public class LaunchConfigurationPooslTab extends AbstractLaunchConfigurationTab {
	// UI text
	private static final String GROUP_MODEL = "Model:";
	private static final String GROUP_EXTERN = "External port configuration:";
	private static final String GROUP_SIM = "Simulator settings:";
	private static final String BTN_RANDOM = "Random";
	private static final String BTN_BROWSE = "Browse";
	private static final String LABEL_SEED = "Seed for resolving non-determinism: ";
	private static final String LABEL_PORT = "Server port: ";
	private static final String LABEL_QUIET = "Quiet mode:";
	private static final String LABEL_PORTINFO = "The server port is only used for connecting with the simulator in debug mode.\n"
			+ "To run debug sessions concurrently, use a different port for each run configuration.\nFor more information check the user manual.";

	// File Dialog
	private static final String FILE_DIALOG_TITLE = "Select poosl model";
	private static final String EXTERN_CONFIG_DIALOG_TITLE = "Select the file with the extern port configuration:";
	private static final String FILE_NAME_FILTER = "*.";

	// Validation
	private static final String VALIDATION_FILE_EMPTY = "Model path cannot be empty";
	private static final String VALIDATION_FILE_POOSL = "The model is not a .poosl file";
	private static final String VALIDATION_FILE_IS_DIR = "The path is a directory and not a .poosl file";
	private static final String VALIDATION_FILE_NOT_EXIST = "The selected model does not exist";
	private static final String VALIDATION_EXTERNAL_FILE_NOT_EXIST = "The selected external port configuration file does not exist";
	private static final String VALIDATION_EXTERNAL_FILE_IS_DIR = "The selected external port configuration file is a directory and not a ."
			+ PooslConstants.EXTERN_CONFIG_EXTENSION + " file";
	private static final String VALIDATION_EXTERNAL_FILE_EXTENSION = "The selected external port configuration file is not a ."
			+ PooslConstants.EXTERN_CONFIG_EXTENSION + " file";
	private static final String VALIDATION_SEED_EMPTY = "Seed for resolving non-determinism cannot be empty";
	private static final String VALIDATION_SEED_NOT_INT = "Seed for resolving non-determinism is not a valid integer";
	private static final String VALIDATION_PORT_EMPTY = "Simulator port cannot be empty";
	private static final String VALIDATION_PORT_NOT_INT = "Simulator port is not a valid integer";
	private static final String VALIDATION_PORT_INVALID = "Simulator port cannot be < 1";

	private static final Logger LOGGER = Logger.getLogger(LaunchConfigurationPooslTab.class.getName());

	// UI components
	private Composite control;
	private Text modelPathTextControl;
	private Text externPathTextControl;
	private Text portTextControl;
	private Text seedTextControl;
	private Text stackSizeTextControl;
	private Button randomSeedButton;
	private Button quietButton;

	// Relative model info
	private String projectName = "";
	private String relativePath = "";

	private final ModifyListener fBasicModifyListener = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent evt) {
			setRelativeElements();
			scheduleUpdateJob();
		}
	};

	private final SelectionListener modelPathListener = new SelectionListener() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			FileDialog fDialog = new FileDialog(getShell());
			fDialog.setFilterExtensions(new String[] { FILE_NAME_FILTER + PooslConstants.POOSL_EXTENSION });
			fDialog.setFilterPath(getFileSelectPath(modelPathTextControl));
			fDialog.setText(FILE_DIALOG_TITLE);
			String filePath = fDialog.open();
			if (filePath != null) {
				modelPathTextControl.setText(filePath);
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			widgetSelected(e);
		}
	};

	private final SelectionListener externPathListener = new SelectionListener() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			FileDialog fDialog = new FileDialog(getShell());
			fDialog.setFilterExtensions(new String[] { FILE_NAME_FILTER + PooslConstants.EXTERN_CONFIG_EXTENSION });
			fDialog.setFilterPath(getFileSelectPath(externPathTextControl));
			fDialog.setText(EXTERN_CONFIG_DIALOG_TITLE);
			String filePath = fDialog.open();
			if (filePath != null) {
				externPathTextControl.setText(filePath);
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			widgetSelected(e);
		}
	};

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

		GridLayout controlGrid = new GridLayout();
		controlGrid.numColumns = 1;
		control.setLayout(controlGrid);

		GridLayout grid = new GridLayout();
		grid.numColumns = 3;
		grid.makeColumnsEqualWidth = false;
		GridData defaultGridData = new GridData(SWT.FILL, SWT.FILL, true, false);

		Group modelGroup = new Group(control, SWT.SHADOW_OUT);
		modelGroup.setText(GROUP_MODEL);
		modelGroup.setLayout(grid);
		modelGroup.setLayoutData(defaultGridData);

		modelPathTextControl = new Text(modelGroup, SWT.BORDER);
		modelPathTextControl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		modelPathTextControl.setText("");
		modelPathTextControl.addModifyListener(fBasicModifyListener);

		Button modelBrowseButton = new Button(modelGroup, SWT.PUSH);
		modelBrowseButton.setText(BTN_BROWSE);
		modelBrowseButton.setLayoutData(new GridData(SWT.END, SWT.TOP, false, false));
		modelBrowseButton.addSelectionListener(modelPathListener);

		Group externGroup = new Group(control, SWT.SHADOW_OUT);
		externGroup.setText(GROUP_EXTERN);
		externGroup.setLayout(grid);
		externGroup.setLayoutData(defaultGridData);

		externPathTextControl = new Text(externGroup, SWT.BORDER);
		externPathTextControl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		externPathTextControl.setText("");
		externPathTextControl.addModifyListener(fBasicModifyListener);

		Button externBrowseButton = new Button(externGroup, SWT.PUSH);
		externBrowseButton.setText(BTN_BROWSE);
		externBrowseButton.setLayoutData(new GridData(SWT.END, SWT.TOP, false, false));
		externBrowseButton.addSelectionListener(externPathListener);

		Group serverGroup = new Group(control, SWT.SHADOW_OUT);
		serverGroup.setText(GROUP_SIM);
		serverGroup.setLayout(grid);
		serverGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Label seedLabel = new Label(serverGroup, SWT.NONE);
		seedLabel.setText(LABEL_SEED);

		seedTextControl = new Text(serverGroup, SWT.BORDER);
		seedTextControl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		seedTextControl.setText("");
		seedTextControl.addModifyListener(fBasicModifyListener);

		randomSeedButton = new Button(serverGroup, SWT.CHECK);
		randomSeedButton.setText(BTN_RANDOM);
		randomSeedButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				scheduleUpdateJob();
				boolean selection = ((Button) e.getSource()).getSelection();
				seedTextControl.setEnabled(!selection);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		Label stackSizeLabel = new Label(serverGroup, SWT.NONE);
		stackSizeLabel.setText("Maximum stack size for data methods in bytes:");

		stackSizeTextControl = new Text(serverGroup, SWT.BORDER);
		stackSizeTextControl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		stackSizeTextControl.setText("");

		new Label(serverGroup, SWT.NONE);

		Label portLabel = new Label(serverGroup, SWT.NONE);
		portLabel.setText(LABEL_PORT);

		portTextControl = new Text(serverGroup, SWT.BORDER);
		portTextControl.setLayoutData(new GridData(SWT.FILL, SWT.WRAP, true, false));
		portTextControl.addModifyListener(fBasicModifyListener);
		portTextControl.setText("");

		new Label(serverGroup, SWT.NONE);

		Label quietLabel = new Label(serverGroup, SWT.NONE);
		quietLabel.setText(LABEL_QUIET);

		quietButton = new Button(serverGroup, SWT.CHECK);
		quietButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				scheduleUpdateJob();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		Label portInfoLabel = new Label(control, SWT.NONE);
		portInfoLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.BOTTOM, false, true));
		portInfoLabel.setText(LABEL_PORTINFO);
	}

	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
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
		if (configuration != null) {
			try {
				String modelPath = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_MODEL_PATH, "");
				if (!modelPath.isEmpty()) {
					modelPathTextControl.setText(modelPath);
				}

				String externalConfigPath = configuration
						.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_EXTERNAL_CONFIG_PATH, "");
				if (!externalConfigPath.isEmpty()) {
					externPathTextControl.setText(externalConfigPath);
				}
				String serverPort = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_PORT,
						PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_SERVER_PORT);
				portTextControl.setText(serverPort);
				String stackSize = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_MAX_STACKSIZE,
						PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_MAX_STACKSIZE_BYTES);
				stackSizeTextControl.setText(stackSize);
				String seed = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SEED,
						PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_SEED);
				seedTextControl.setText(seed);
				boolean boolAttribute = configuration.getAttribute(
						PooslConstants.CONFIGURATION_ATTRIBUTE_IS_RANDOM_SEED,
						PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_IS_RANDOM_SEED);
				randomSeedButton.setSelection(boolAttribute);
				boolean quietAttribute = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_IS_QUIET,
						PooslConstants.CONFIGURATION_ATTRIBUTE_DEFAULT_IS_QUIET);
				quietButton.setSelection(quietAttribute);
				seedTextControl.setEnabled(!boolAttribute);
			} catch (CoreException e) {
				LOGGER.log(Level.WARNING, e.getMessage(), e);
			}
		}
	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_MODEL_PATH, modelPathTextControl.getText());
		configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_EXTERNAL_CONFIG_PATH,
				externPathTextControl.getText());
		configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_PORT, portTextControl.getText());
		configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_MAX_STACKSIZE,
				stackSizeTextControl.getText());
		configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SEED, seedTextControl.getText());
		configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_IS_RANDOM_SEED,
				randomSeedButton.getSelection());
		configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_IS_QUIET, quietButton.getSelection());
		configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_PROJECT, projectName);
		configuration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_RELATIVE_PATH, relativePath);
	}

	@Override
	public boolean isValid(ILaunchConfiguration launchConfig) {
		setErrorMessage(null);
		String errorMessage = null;
		if (!modelPathTextControl.getText().isEmpty()) {
			File modelFile = new File(modelPathTextControl.getText());
			if (!modelFile.exists()) {
				errorMessage = VALIDATION_FILE_NOT_EXIST;
			} else if (modelFile.isDirectory()) {
				errorMessage = VALIDATION_FILE_IS_DIR;
			} else if (!PooslConstants.POOSL_EXTENSION
					.equals(modelFile.getName().substring(modelFile.getName().lastIndexOf('.') + 1))) {
				errorMessage = VALIDATION_FILE_POOSL;
			}
		} else {
			errorMessage = VALIDATION_FILE_EMPTY;
		}

		if (!externPathTextControl.getText().isEmpty()) {
			File externFile = new File(externPathTextControl.getText());
			if (!externFile.exists()) {
				errorMessage = VALIDATION_EXTERNAL_FILE_NOT_EXIST;
			} else if (externFile.isDirectory()) {
				errorMessage = VALIDATION_EXTERNAL_FILE_IS_DIR;
			} else if (!PooslConstants.EXTERN_CONFIG_EXTENSION
					.equals(externFile.getName().substring(externFile.getName().lastIndexOf('.') + 1))) {
				errorMessage = VALIDATION_EXTERNAL_FILE_EXTENSION;
			}
		}

		if (!portTextControl.getText().isEmpty()) {
			if (!isInteger(portTextControl.getText())) {
				errorMessage = VALIDATION_PORT_INVALID;
			} else {
				try {
					if (Integer.parseInt(portTextControl.getText()) < 1) {
						errorMessage = VALIDATION_PORT_INVALID;
					}
				} catch (NumberFormatException e) {
					errorMessage = VALIDATION_PORT_NOT_INT;
				}
			}
		} else {
			errorMessage = VALIDATION_PORT_EMPTY;
		}

		if (!seedTextControl.getText().isEmpty() && !randomSeedButton.getSelection()) {
			if (!isInteger(seedTextControl.getText())) {
				errorMessage = VALIDATION_SEED_NOT_INT;
			}
		} else if (!randomSeedButton.getSelection()) {
			errorMessage = VALIDATION_SEED_EMPTY;
		}

		if (errorMessage != null && !errorMessage.isEmpty()) {
			setErrorMessage(errorMessage);
			return false;
		} else {
			return true;
		}
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
		Bundle bundle = Platform.getBundle(PooslConstants.PLUGIN_ID);
		Path path = new Path("icons/poosl_simulation.gif");
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
		String modelLocation = modelPathTextControl.getText();
		if (!modelLocation.isEmpty()) {
			for (IProject iProject : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
				String projectLocation = iProject.getLocation().toOSString();
				if (modelLocation.startsWith(projectLocation)) {
					projectName = iProject.getName();
					relativePath = "\\" + projectName + modelLocation.substring(projectLocation.length());

					return;
				}
			}
		}
		projectName = "";
		relativePath = "";
	}
}
