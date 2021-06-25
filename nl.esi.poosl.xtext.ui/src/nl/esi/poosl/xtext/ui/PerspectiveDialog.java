package nl.esi.poosl.xtext.ui;

import nl.esi.poosl.xtext.GlobalConstants;
import nl.esi.poosl.xtext.ui.internal.PooslActivator;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class PerspectiveDialog extends Dialog {
	private static final String TITLE = "Confirm Perspective Switch";
	private static final String TEXT_OPEN_EDIT_PERSPECTIVE = "You are opening a POOSL file; it is recomended that you use the POOSL Edit perspective.\n"
			+ "Do you want to switch to the POOSL Edit perspective now?";
	private static final String TEXT_REMEMBER_CHOICE = "Remember my decision";

	private Button button;

	public PerspectiveDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		Label label = new Label(container, SWT.NONE);
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		label.setText(TEXT_OPEN_EDIT_PERSPECTIVE);
		button = new Button(container, SWT.CHECK);
		button.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		button.setText(TEXT_REMEMBER_CHOICE);
		return container;
	}

	// overriding this methods allows you to set the
	// title of the custom dialog
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(TITLE);
	}

	@Override
	protected void okPressed() {
		saveInput(true);
		super.okPressed();
	}

	@Override
	protected void cancelPressed() {
		saveInput(false);
		super.cancelPressed();
	}

	private void saveInput(boolean openPerspective) {
		if (button.getSelection()) {
			IPreferenceStore pStore = PooslActivator.getInstance().getPreferenceStore();
			if (pStore != null) {
				pStore.setValue(GlobalConstants.PREFERENCES_DONT_ASK_EDIT_PERSPECTIVE, true);
				pStore.setValue(GlobalConstants.PREFERENCES_OPEN_EDIT_PERSPECTIVE, openPerspective);
			}
		}
	}
}