package nl.esi.poosl.sirius.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

import nl.esi.poosl.sirius.Activator;
import nl.esi.poosl.sirius.IPreferenceConstants;

public class EditorPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
	private static final String TEXT_LABEL_DONT_ASK = "Don't ask which editor to use every time a POOSL file or model is opened.";
	private static final String TEXT_LABEL_SYSTEM_OR_CLUSTER = "Only files that contain a System or a single Cluster Class can be opened directly as Composite Structure Diagram.";

	private static final String TEXT_DESCRIPTION = "Open behavior for the Project Explorer and Graphical Editor.";

	private static final String TEXT_GROUP_EXPLORER_TITLE = "Double-click within the Project Explorer";
	private static final String TEXT_RADIO_EXPLORER_TITLE = "File with a System or a single Cluster Class";
	private static final String TEXT_RADIO_GRAPHICAL_TITLE = "Double-click within a Graphical Editor";
	private static final String TEXT_RADIO_EXPLORER_NOSYSTEM_TITLE = "File without a System or a single Cluster Class";

	private static final String TEXT_RADIO_OPTION_TEXTUAL = "Show Textual Editor";
	private static final String TEXT_RADIO_OPTION_GRAPHICAL = "Show Composite Structure Diagram";
	private static final String TEXT_RADIO_OPTION_CLASS = "Show Class Diagram";

	private Composite compMain;
	private Composite compGraphical;

	private RadioGroupFieldEditor rgrProjectExplorer;
	private RadioGroupFieldEditor rgrGraphicalBehavior;
	private RadioGroupFieldEditor rgrProjectExplorerNoSystem;

	private Group grpProjectExplorer;

	private Button btnAsk;

	/**
	 * Create the preference page.
	 */
	public EditorPreferencePage() {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription(TEXT_DESCRIPTION);
	}

	/**
	 * Create contents of the preference page.
	 * 
	 * @param parent
	 */
	@Override
	public Control createContents(Composite parent) {
		compMain = new Composite(parent, SWT.NONE);
		compMain.setLayout(new FormLayout());

		IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench != null) {
			workbench.getHelpSystem().setHelp(getControl(), "nl.esi.poosl.help.help_preferences_graphical_behavior");
		}

		createProjectGroup();
		createGraphGroup();
		createAskButton();

		return compMain;
	}

	private void createProjectGroup() {
		grpProjectExplorer = new Group(compMain, SWT.NONE);
		grpProjectExplorer.setText(TEXT_GROUP_EXPLORER_TITLE);
		grpProjectExplorer.setLayout(new FormLayout());
		FormData fdGrpPorjectExplorer = new FormData();
		fdGrpPorjectExplorer.left = new FormAttachment(0, 10);
		fdGrpPorjectExplorer.top = new FormAttachment(0, 10);
		fdGrpPorjectExplorer.right = new FormAttachment(100, -10);

		grpProjectExplorer.setLayoutData(fdGrpPorjectExplorer);

		Label lblDiagramInfo = new Label(grpProjectExplorer, SWT.WRAP);
		FormData fdLblDiagramInfo = new FormData();
		fdLblDiagramInfo.top = new FormAttachment(0, 10);
		fdLblDiagramInfo.width = 100;
		fdLblDiagramInfo.right = new FormAttachment(100, -10);
		fdLblDiagramInfo.left = new FormAttachment(0, 10);
		lblDiagramInfo.setLayoutData(fdLblDiagramInfo);
		lblDiagramInfo.setText(TEXT_LABEL_SYSTEM_OR_CLUSTER);

		Composite compProjectExplorer = new Composite(grpProjectExplorer, SWT.TOP);
		FormData fdPojectExplorerContainer = new FormData();
		fdPojectExplorerContainer.top = new FormAttachment(lblDiagramInfo, 10, SWT.DEFAULT);
		fdPojectExplorerContainer.right = new FormAttachment(100, -10);
		fdPojectExplorerContainer.left = new FormAttachment(0, 10);
		compProjectExplorer.setLayoutData(fdPojectExplorerContainer);
		compProjectExplorer.setLayout(new GridLayout());

		rgrProjectExplorer = new RadioGroupFieldEditor(IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER,
				TEXT_RADIO_EXPLORER_TITLE, 1,
				new String[][] {
						{ TEXT_RADIO_OPTION_TEXTUAL, IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_TEXTUAL },
						{ TEXT_RADIO_OPTION_GRAPHICAL, IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_GRAPHICAL },
						{ TEXT_RADIO_OPTION_CLASS, IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_CLASS_DIAGRAM } },
				compProjectExplorer, true);

		Composite compProjectExplorerNoSystem = new Composite(grpProjectExplorer, SWT.NONE);
		FormData fdComposite = new FormData();
		fdComposite.top = new FormAttachment(compProjectExplorer, 10);
		fdComposite.right = new FormAttachment(100, -10);
		fdComposite.left = new FormAttachment(0, 10);
		compProjectExplorerNoSystem.setLayoutData(fdComposite);
		compProjectExplorerNoSystem.setLayout(new GridLayout());

		rgrProjectExplorerNoSystem = new RadioGroupFieldEditor(
				IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_NO_SYSTEM, TEXT_RADIO_EXPLORER_NOSYSTEM_TITLE, 1,
				new String[][] {
						{ TEXT_RADIO_OPTION_TEXTUAL, IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_TEXTUAL },
						{ TEXT_RADIO_OPTION_CLASS, IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_CLASS_DIAGRAM } },
				compProjectExplorer, true);

		rgrProjectExplorer.setPage(this);
		rgrProjectExplorer.setPreferenceStore(getPreferenceStore());
		rgrProjectExplorer.load();

		rgrProjectExplorerNoSystem.setPage(this);
		rgrProjectExplorerNoSystem.setPreferenceStore(getPreferenceStore());
		rgrProjectExplorerNoSystem.load();
	}

	private void createGraphGroup() {
		compGraphical = new Composite(compMain, SWT.NONE);
		FormData fdComposite = new FormData();
		fdComposite.top = new FormAttachment(grpProjectExplorer, 6);
		fdComposite.left = new FormAttachment(grpProjectExplorer, 0, SWT.LEFT);
		fdComposite.right = new FormAttachment(100, -10);
		compGraphical.setLayoutData(fdComposite);
		compGraphical.setLayout(new GridLayout());

		rgrGraphicalBehavior = new RadioGroupFieldEditor(IPreferenceConstants.PREFERENCE_GRAPHICAL_EDITOR,
				TEXT_RADIO_GRAPHICAL_TITLE, 1,
				new String[][] {
						{ TEXT_RADIO_OPTION_TEXTUAL, IPreferenceConstants.PREFERENCE_GRAPHICAL_EDITOR_TEXTUAL },
						{ TEXT_RADIO_OPTION_GRAPHICAL, IPreferenceConstants.PREFERENCE_GRAPHICAL_EDITOR_GRAPHICAL } },
				compGraphical, true);

		rgrGraphicalBehavior.setPage(this);
		rgrGraphicalBehavior.setPreferenceStore(getPreferenceStore());
		rgrGraphicalBehavior.load();
	}

	private void createAskButton() {
		Composite askContainer = new Composite(compMain, SWT.WRAP);
		askContainer.setLayout(new FormLayout());

		FormData fdComposite = new FormData();
		fdComposite.top = new FormAttachment(compGraphical, 10);
		fdComposite.right = new FormAttachment(100, -10);
		fdComposite.left = new FormAttachment(0, 10);
		askContainer.setLayoutData(fdComposite);

		btnAsk = new Button(askContainer, SWT.CHECK);
		FormData fdBtnCheckButton1 = new FormData();
		fdBtnCheckButton1.bottom = new FormAttachment(100);
		fdBtnCheckButton1.top = new FormAttachment(0);
		fdBtnCheckButton1.left = new FormAttachment(0);
		btnAsk.setLayoutData(fdBtnCheckButton1);

		Label lblAsk = new Label(askContainer, SWT.WRAP);
		FormData fdLblNewLabel = new FormData();
		fdLblNewLabel.right = new FormAttachment(grpProjectExplorer, 335, SWT.RIGHT);

		fdLblNewLabel.bottom = new FormAttachment(100);
		fdLblNewLabel.top = new FormAttachment(0);
		fdLblNewLabel.left = new FormAttachment(btnAsk, 10);

		lblAsk.setLayoutData(fdLblNewLabel);
		lblAsk.setText(TEXT_LABEL_DONT_ASK);

		// Don't ask which view to use every time a POOSL file or model is
		// opened.

		boolean ask = getPreferenceStore().getBoolean(IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_DONT_ASK);
		btnAsk.setSelection(ask);
	}

	public void init(IWorkbench workbench) {
		// do nothing
	}

	@Override
	protected void performDefaults() {
		rgrProjectExplorer.loadDefault();
		rgrProjectExplorerNoSystem.loadDefault();
		rgrGraphicalBehavior.loadDefault();
		btnAsk.setSelection(
				getPreferenceStore().getDefaultBoolean(IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_DONT_ASK));
		super.performDefaults();
	}

	@Override
	protected void performApply() {
		rgrProjectExplorer.store();
		rgrProjectExplorerNoSystem.store();
		rgrGraphicalBehavior.store();
		getPreferenceStore().setValue(IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_DONT_ASK, btnAsk.getSelection());
		super.performApply();
	}

	@Override
	public boolean performOk() {
		rgrProjectExplorer.store();
		rgrProjectExplorerNoSystem.store();
		rgrGraphicalBehavior.store();
		getPreferenceStore().setValue(IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_DONT_ASK, btnAsk.getSelection());

		return super.performOk();
	}
}
