package nl.esi.poosl.xtext.ui.preferences;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import nl.esi.poosl.xtext.GlobalConstants;
import nl.esi.poosl.xtext.ui.internal.PooslActivator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

public class BasicClassesPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
	private static final String HELP_ID = "nl.esi.poosl.help.help_preferences_basic_classes";
	private static final String FILE_EXTENSION = "poosl";
	private static final String LABEL_FILE_LOCATION = "File Location:";
	private static final String LABEL_INFO = "General advice: use the default basic classes.";
	private static final String LABEL_POOSL_FILE = "Select a poosl file.";
	private static final String LABEL_USE_DEFAULT = "Use the default basic classes.";
	private static final String LABEL_BUTTON = "Browse...";
	private static final String WARNING_NOT_A_POOSL_FILE = "The basic classes file is not a .poosl file";
	private static final String WARNING_DOES_NOT_EXIST = "The basic classes file does not exist";
	private static final String WARNING_EMPTY = "File path cannot be empty.";
	private static final String WORKSPACE_SUBSTRING = "platform:/resource";

	private static final Logger LOGGER = Logger.getLogger(BasicClassesPreferencePage.class.getName());

	/**
	 * Parent Layout which owns the other elements
	 */
	private Composite composite;

	/**
	 * The element that contains the location of the file as string. It is disabled
	 * when the default file is used.
	 */
	private Text basicClassesControl;

	/**
	 * The selection button. If selected the standard basic classes file will be
	 * used. (selected by default)
	 */
	private Button basicClassesButton;

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(PooslActivator.getInstance().getPreferenceStore());
	}

	/**
	 * Adds a description {@link Label}, location {@link #basicClassesControl},
	 * browse {@link Button} and use default selection
	 * {@link BasicClassesPreferencePage#basicClassesButton} to the parent
	 * {@link #composite}
	 */
	@Override
	protected Control createContents(Composite parent) {
		composite = parent;

		addBrowseFileGroup(parent);
		addUseDefaultButton(parent);

		Label infoLabel = new Label(parent, SWT.NONE);
		infoLabel.setLayoutData(new GridData(SWT.TOP, SWT.TOP, false, true));
		infoLabel.setText(LABEL_INFO);

		basicClassesControl.setEnabled(!basicClassesButton.getSelection());
		if (basicClassesControl.getText().isEmpty()) {
			basicClassesButton.setSelection(true);
		}
		PlatformUI.getWorkbench().getHelpSystem().setHelp(composite, HELP_ID);
		getPreferenceStore().addPropertyChangeListener(changeListener);
		validate();
		return parent;
	}

	/**
	 * Adds a composite group which holds a label, a textcontrol and a browse
	 * button.
	 * 
	 * @param parent a {@link Composite} which the group will be added to
	 * @return the composite group
	 */
	private Composite addBrowseFileGroup(Composite parent) {
		Composite fileGroup = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;

		fileGroup.setLayout(gridLayout);
		fileGroup.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

		Label basicLabel = new Label(fileGroup, SWT.NONE);
		basicLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, true));
		basicLabel.setText(LABEL_FILE_LOCATION);

		basicClassesControl = new Text(fileGroup, SWT.BORDER);
		basicClassesControl.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		basicClassesControl
				.setText(getPreferenceStore().getString(GlobalConstants.PREFERENCES_CUSTOM_BASIC_CLASS_PATH));
		basicClassesControl.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				validate();
			}
		});

		Button basicBrowseButton = new Button(fileGroup, SWT.PUSH);
		basicBrowseButton.setText(LABEL_BUTTON);
		basicBrowseButton.setLayoutData(new GridData(SWT.RIGHT, SWT.BEGINNING, false, false));
		basicBrowseButton.addSelectionListener(basicPathListener);
		return fileGroup;

	}

	/**
	 * Adds the selection button to the parent
	 * 
	 * @param parent The {@link Composite} which the button will be added
	 * @return Return the created button
	 */
	private Button addUseDefaultButton(Composite parent) {
		basicClassesButton = new Button(parent, SWT.CHECK);
		basicClassesButton.setText(LABEL_USE_DEFAULT);
		basicClassesButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));
		basicClassesButton
				.setSelection(!getPreferenceStore().getString(GlobalConstants.PREFERENCES_USE_DEFAULT_BASIC_CLASS)
						.equals(GlobalConstants.PREFERENCES_BASIC_CLASSES_CUSTOM));
		basicClassesButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				validate();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
		});
		return basicClassesButton;
	}

	/**
	 * Listener thats called when information is changed in the store. Also see
	 * {@link IPreferenceStore #addPropertyChangeListener(IPropertyChangeListener)}
	 */
	IPropertyChangeListener changeListener = new IPropertyChangeListener() {

		@Override
		public void propertyChange(PropertyChangeEvent event) {
			if (event.getProperty() == GlobalConstants.PREFERENCES_CUSTOM_BASIC_CLASS_PATH
					|| event.getProperty() == GlobalConstants.PREFERENCES_USE_DEFAULT_BASIC_CLASS) {
				try {
					ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.CLEAN_BUILD, null);
				} catch (CoreException e) {
					LOGGER.log(Level.SEVERE, e.getMessage(), e);
				}
			}
		}
	};

	/**
	 * Selection listener when browsing for the custom basic classes file. It has
	 * its build in validator and filter.
	 */
	SelectionListener basicPathListener = new SelectionListener() {

		@Override
		public void widgetSelected(SelectionEvent e) {

			ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(composite.getShell(),
					new WorkbenchLabelProvider(), new BaseWorkbenchContentProvider());

			dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
			dialog.setTitle(LABEL_POOSL_FILE);
			dialog.setAllowMultiple(false);
			dialog.setValidator(new ISelectionStatusValidator() {

				@Override
				public IStatus validate(Object[] selection) {
					if (selection.length > 0 && selection[0] instanceof IFile
							&& FILE_EXTENSION.equals(((IFile) selection[0]).getFileExtension())) {
						return new Status(Status.OK, GlobalConstants.PREFERENCE_PLUGIN_ID, "");
					}
					return new Status(Status.ERROR, GlobalConstants.PREFERENCE_PLUGIN_ID, LABEL_POOSL_FILE);
				}
			});
			dialog.setBlockOnOpen(true);
			dialog.addFilter(new ViewerFilter() {
				@Override
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					return !(element instanceof IFile) || FILE_EXTENSION.equals(((IFile) element).getFileExtension());
				}
			});
			if (dialog.open() == ElementTreeSelectionDialog.OK) {
				for (Object result : dialog.getResult()) {

					String platformString = WORKSPACE_SUBSTRING + result.toString().substring(1);
					basicClassesControl.setText(platformString);
				}
				basicClassesButton.setSelection(false);
				basicClassesControl.setEnabled(true);
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			widgetSelected(e);
		}
	};

	/**
	 * Will validate the the supplied file when the the default basic classes is not
	 * used
	 */
	public void validate() {
		setErrorMessage(null);
		basicClassesControl.setEnabled(!basicClassesButton.getSelection());
		if (!basicClassesButton.getSelection()) {
			if (!basicClassesControl.getText().isEmpty()) {
				if (basicClassesControl.getText().startsWith(WORKSPACE_SUBSTRING)) {

					try {
						String platformString = basicClassesControl.getText();
						String relative = platformString.substring(WORKSPACE_SUBSTRING.length());
						IFile basicFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(relative));
						if (!basicFile.exists()) {
							setErrorMessage(WARNING_DOES_NOT_EXIST);
							setValid(false);
						} else if (!isPooslFile(basicFile.getName())) {
							setErrorMessage(WARNING_NOT_A_POOSL_FILE);
							setValid(false);
						} else {
							setValid(true);
						}
					} catch (Exception e) {
						setErrorMessage(WARNING_DOES_NOT_EXIST);
						setValid(false);
					}

				} else {

					File basicFile = new File(basicClassesControl.getText());

					if (basicFile.isDirectory() || !basicFile.exists()) {
						setErrorMessage(WARNING_DOES_NOT_EXIST);
						setValid(false);
					} else if (!isPooslFile(basicFile.getName())) {
						setErrorMessage(WARNING_NOT_A_POOSL_FILE);
						setValid(false);
					} else {
						setValid(true);
					}
				}
			} else {
				setErrorMessage(WARNING_EMPTY);
				setValid(false);
			}
		} else {
			setValid(true);
		}
	}

	/**
	 * Verifies if the file is a poosl file.
	 * 
	 * @param location file location as {@link String}
	 * @return true is it is a poosl file
	 */
	private boolean isPooslFile(String location) {
		if (location.isEmpty() || !location.contains(".")) {
			return false;
		} else {
			String extension = location.substring(location.lastIndexOf('.'));
			return extension.equals("." + FILE_EXTENSION);
		}
	}

	@Override
	protected void performDefaults() {
		basicClassesButton.setSelection(true);
		basicClassesControl.setText("");
		basicClassesControl.setEnabled(false);
		super.performDefaults();
	}

	@Override
	protected void performApply() {
		saveState();
		super.performApply();
	}

	/**
	 * Saves information to the {@link IPreferenceStore}. The
	 * {@link #basicClassesButton} selection variable is saved as a String because
	 * of a bug for retrieving booleans.
	 */
	private void saveState() {

		getPreferenceStore().setValue(GlobalConstants.PREFERENCES_CUSTOM_BASIC_CLASS_PATH,
				basicClassesControl.getText());

		getPreferenceStore().setValue(GlobalConstants.PREFERENCES_USE_DEFAULT_BASIC_CLASS,
				(basicClassesButton.getSelection()) ? GlobalConstants.PREFERENCES_BASIC_CLASSES_DEFAULT
						: GlobalConstants.PREFERENCES_BASIC_CLASSES_CUSTOM);

	}

	@Override
	public boolean performOk() {
		saveState();
		return super.performOk();
	}
}