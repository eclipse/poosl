package nl.esi.poosl.sirius.navigator;

import java.text.MessageFormat;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import nl.esi.poosl.sirius.Activator;
import nl.esi.poosl.sirius.IPreferenceConstants;

public class PooslEditorPreferenceDialog extends Dialog {
	private static final String TEXTUAL_EDITOR = "Textual Editor";
	private static final String GRAPHICAL_EDITOR = "Graphical Editor ({0})";
	public static final String CLASS_DIAGRAM = "Class Diagram";
	public static final String DIAGRAM_NAME_SYSTEM = " of System";
	public static final String DIAGRAM_NAME_CLUSTER = " of Cluster ";
	public static final String COMPOSITE_STRUCTURE_DIAGRAM = "Composite Structure Diagram";
	private static final Point MINIMUM_SIZE = new Point(500, 300);

	private static final String TITLE = "Choose Editor";
	private Button btnTextual;
	private Button btnGraphical;
	private Button btnRememberMyDecision;

	private OpenStrategy selection;
	private final String diagramDescription;
	private final String dialogLabel;
	private Button btnClassDiagram;
	private final boolean isClassDiagramShown;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */

	public PooslEditorPreferenceDialog(Shell parentShell, String dialogLabel, String diagramDescription,
			boolean isClassDiagramShown) {
		super(parentShell);
		this.dialogLabel = dialogLabel;
		this.diagramDescription = diagramDescription;
		this.isClassDiagramShown = isClassDiagramShown;
		parentShell.setText("Choose Editor");
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(TITLE);
		newShell.setMinimumSize(MINIMUM_SIZE);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new FormLayout());
		GridData gdContainer = new GridData(SWT.FILL, SWT.FILL, true, true);
		container.setLayoutData(gdContainer);

		Label lblNewLabel = new Label(container, SWT.WRAP);
		FormData fdLblNewLabel = new FormData();
		fdLblNewLabel.right = new FormAttachment(100, -10);
		fdLblNewLabel.top = new FormAttachment(0, 13);
		fdLblNewLabel.left = new FormAttachment(0, 11);
		lblNewLabel.setLayoutData(fdLblNewLabel);
		lblNewLabel.setText(dialogLabel);
		createButtons(container, lblNewLabel);
		createButtonListeners();
		loadPreferences();
		return container;
	}

	private void createButtonListeners() {
		btnGraphical.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnGraphical.setSelection(true);
				btnTextual.setSelection(false);
				btnClassDiagram.setSelection(false);
			}
		});
		btnTextual.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnGraphical.setSelection(false);
				btnTextual.setSelection(true);
				btnClassDiagram.setSelection(false);
			}
		});
		btnClassDiagram.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnGraphical.setSelection(false);
				btnTextual.setSelection(false);
				btnClassDiagram.setSelection(true);
			}
		});
	}

	private void loadPreferences() {
		IPreferencesService preferencesService = Platform.getPreferencesService();
		String pref = preferencesService.getString(IPreferenceConstants.PREFERENCE_PLUGIN_ID,
				IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER,
				IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_TEXTUAL, null);
		boolean textual = pref.equals(IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_TEXTUAL);
		btnTextual.setSelection(textual);
		btnGraphical.setSelection(!textual);
		btnGraphical.setSelection(false);

	}

	private void createButtons(Composite container, Label lblNewLabel) {
		btnTextual = new Button(container, SWT.RADIO);
		FormData fdBtnTextual = new FormData();
		fdBtnTextual.top = new FormAttachment(lblNewLabel, 6);
		fdBtnTextual.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		btnTextual.setLayoutData(fdBtnTextual);
		btnTextual.setText(TEXTUAL_EDITOR);

		btnGraphical = new Button(container, SWT.RADIO | SWT.WRAP);
		FormData fdBtnGraphical = new FormData();
		fdBtnGraphical.right = new FormAttachment(100, -10);
		fdBtnGraphical.top = new FormAttachment(btnTextual, 6);
		fdBtnGraphical.left = new FormAttachment(0, 11);
		fdBtnGraphical.width = 100;

		btnGraphical.setLayoutData(fdBtnGraphical);
		btnGraphical.setText(MessageFormat.format(GRAPHICAL_EDITOR, diagramDescription));

		btnRememberMyDecision = new Button(container, SWT.CHECK);
		FormData fdBtnRememberMyDecision = new FormData();
		fdBtnRememberMyDecision.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		btnRememberMyDecision.setText("Remember my decision");

		btnClassDiagram = new Button(container, SWT.RADIO | SWT.WRAP);
		if (isClassDiagramShown) {
			FormData fdBtnClassDiagram = new FormData();
			fdBtnClassDiagram.right = new FormAttachment(100, -10);
			fdBtnClassDiagram.top = new FormAttachment(btnGraphical, 6);
			fdBtnClassDiagram.left = new FormAttachment(0, 11);
			fdBtnClassDiagram.width = 100;

			btnClassDiagram.setLayoutData(fdBtnClassDiagram);
			btnClassDiagram.setText(MessageFormat.format(GRAPHICAL_EDITOR, CLASS_DIAGRAM));

			fdBtnRememberMyDecision.top = new FormAttachment(btnClassDiagram, 18);
		} else {
			btnClassDiagram.setVisible(false);

			fdBtnRememberMyDecision.top = new FormAttachment(btnGraphical, 18);
		}

		btnRememberMyDecision.setLayoutData(fdBtnRememberMyDecision);
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return MINIMUM_SIZE;
	}

	@Override
	protected void okPressed() {
		saveInput();
		super.okPressed();
	}

	private void saveInput() {
		if (btnTextual.getSelection()) {
			selection = OpenStrategy.TEXTUAL;
		} else if (btnGraphical.getSelection()) {
			selection = OpenStrategy.GRAPHICAL;
		} else {
			selection = OpenStrategy.CLASSDIAGRAM;
		}

		IPreferenceStore pStore = Activator.getDefault().getPreferenceStore();
		if (pStore != null) {
			if (btnTextual.getSelection()) {
				pStore.setValue(IPreferenceConstants.PREFERENCE_GRAPHICAL_EDITOR,
						IPreferenceConstants.PREFERENCE_GRAPHICAL_EDITOR_TEXTUAL);
				pStore.setValue(IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER,
						IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_TEXTUAL);
				pStore.setValue(IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_NO_SYSTEM,
						IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_TEXTUAL);
			} else {
				pStore.setValue(IPreferenceConstants.PREFERENCE_GRAPHICAL_EDITOR,
						IPreferenceConstants.PREFERENCE_GRAPHICAL_EDITOR_GRAPHICAL);
				pStore.setValue(IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_NO_SYSTEM,
						IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_CLASS_DIAGRAM);
				if (btnGraphical.getSelection()) {
					pStore.setValue(IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER,
							IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_GRAPHICAL);
				} else {
					pStore.setValue(IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER,
							IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_CLASS_DIAGRAM);
				}
			}
			pStore.setValue(IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_DONT_ASK,
					btnRememberMyDecision.getSelection());
		}
	}

	public OpenStrategy getSelection() {
		return selection;
	}

	public enum OpenStrategy {
		TEXTUAL, GRAPHICAL, CLASSDIAGRAM, CANCEL
	}
}
