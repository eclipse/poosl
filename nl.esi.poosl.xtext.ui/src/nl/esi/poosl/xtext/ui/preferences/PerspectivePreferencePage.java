package nl.esi.poosl.xtext.ui.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import nl.esi.poosl.xtext.GlobalConstants;
import nl.esi.poosl.xtext.ui.internal.PooslActivator;

public class PerspectivePreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
	private static final String BRACKETQUOTE_TEXT = "When typing an opening bracket/quote automatically add a closing bracket/quote. (Restart Eclipse for changes to take effect)";
	private static final String EDIT_PERSPECTIVE_BUTTON_TEXT = "Automatically switch to the POOSL Edit perspective when opening a POOSL file.";
	private static final String FORMAT_ON_SAVE_TEXT = "Format POOSL file on save.";

	private Button useEditPerspectiveButton;
	private Button dontaskEditPerspectiveButton;
	private Button autoCloseBracketQuoteButton;
	private Button formatOnSaveButton;

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(PooslActivator.getInstance().getPreferenceStore());
	}

	/**
	 * Adds a description {@link Label}, location {@link #basicClassesControl},
	 * browse {@link Button} and use default selection
	 * {@link PerspectivePreferencePage#useEditPerspectiveButton} to the parent
	 * {@link #composite}
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite mainContainer = new Composite(parent, SWT.NONE);
		mainContainer.setLayout(new GridLayout());
		mainContainer.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, true));
		addEditPerspectiveButton(mainContainer);
		addClosingBracketsQuotesButton(mainContainer);
		addFormatOnSaveButton(mainContainer);
		return parent;
	}

	/**
	 * Adds the selection button to the parent
	 * 
	 * @param parent The {@link Composite} which the button will be added
	 * @return Return the created button
	 */
	private void addEditPerspectiveButton(Composite parent) {
		dontaskEditPerspectiveButton = new Button(parent, SWT.CHECK);
		dontaskEditPerspectiveButton.setText(
				"Don't ask for a perspective switch every time a POOSL file is opened from a non-POOSL perspective.");
		dontaskEditPerspectiveButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));
		boolean dontask = getPreferenceStore().getBoolean(GlobalConstants.PREFERENCES_DONT_ASK_EDIT_PERSPECTIVE);
		dontaskEditPerspectiveButton.setSelection(dontask);

		Composite comp = new Composite(parent, SWT.NONE);
		GridLayout margin = new GridLayout();
		margin.marginLeft = 10;
		comp.setLayout(margin);

		useEditPerspectiveButton = new Button(comp, SWT.CHECK);
		useEditPerspectiveButton.setText(EDIT_PERSPECTIVE_BUTTON_TEXT);
		useEditPerspectiveButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, true));
		useEditPerspectiveButton.setEnabled(dontask);

		useEditPerspectiveButton
				.setSelection(getPreferenceStore().getBoolean(GlobalConstants.PREFERENCES_OPEN_EDIT_PERSPECTIVE));

		dontaskEditPerspectiveButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				useEditPerspectiveButton.setEnabled(dontaskEditPerspectiveButton.getSelection());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// do nothing
			}
		});
	}

	private void addClosingBracketsQuotesButton(Composite parent) {
		autoCloseBracketQuoteButton = new Button(parent, SWT.CHECK);
		autoCloseBracketQuoteButton.setText(BRACKETQUOTE_TEXT);
		autoCloseBracketQuoteButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));
		boolean autoclose = getPreferenceStore()
				.getBoolean(GlobalConstants.PREFERENCES_AUTOCOMPLETE_BRACKETS_AND_QUOTES);
		autoCloseBracketQuoteButton.setSelection(autoclose);
	}

	private void addFormatOnSaveButton(Composite parent) {
		formatOnSaveButton = new Button(parent, SWT.CHECK);
		formatOnSaveButton.setText(FORMAT_ON_SAVE_TEXT);
		formatOnSaveButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));
		boolean format = getPreferenceStore().getBoolean(GlobalConstants.PREFERENCES_FORMAT_ON_SAVE);
		formatOnSaveButton.setSelection(format);
	}

	@Override
	protected void performDefaults() {
		useEditPerspectiveButton.setSelection(false);
		dontaskEditPerspectiveButton.setSelection(false);
		autoCloseBracketQuoteButton.setSelection(false);
		super.performDefaults();
	}

	@Override
	protected void performApply() {
		saveState();
		super.performApply();
	}

	/**
	 * Saves information to the {@link IPreferenceStore}. The
	 * {@link #useEditPerspectiveButton} selection variable is saved as a String
	 * because of a bug for retrieving booleans.
	 */
	private void saveState() {
		getPreferenceStore().setValue(GlobalConstants.PREFERENCES_DONT_ASK_EDIT_PERSPECTIVE,
				dontaskEditPerspectiveButton.getSelection());
		getPreferenceStore().setValue(GlobalConstants.PREFERENCES_OPEN_EDIT_PERSPECTIVE,
				useEditPerspectiveButton.getSelection());
		getPreferenceStore().setValue(GlobalConstants.PREFERENCES_AUTOCOMPLETE_BRACKETS_AND_QUOTES,
				autoCloseBracketQuoteButton.getSelection());
		getPreferenceStore().setValue(GlobalConstants.PREFERENCES_FORMAT_ON_SAVE, formatOnSaveButton.getSelection());
	}

	@Override
	public boolean performOk() {
		saveState();
		return super.performOk();
	}
}