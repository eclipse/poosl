package nl.esi.poosl.rotalumisclient.preferences;

import nl.esi.poosl.rotalumisclient.Activator;
import nl.esi.poosl.rotalumisclient.PooslConstants;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.ui.editor.preferences.fields.LabelFieldEditor;

public class SequenceDiagramPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	IntegerFieldEditor messageBufferField;

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(),
				"nl.esi.poosl.help.help_preferences_sequence_diagram");
	}

	@Override
	protected void createFieldEditors() {
		messageBufferField = new IntegerFieldEditor(PooslConstants.PREFERENCES_MESSAGE_BUFFER_SIZE,
				"&Maximum message buffer size: ", getFieldEditorParent());
		int messageBufferSize = getPreferenceStore().getInt(PooslConstants.PREFERENCES_MESSAGE_BUFFER_SIZE);
		if (messageBufferSize == 0) {
			getPreferenceStore().setValue(PooslConstants.PREFERENCES_MESSAGE_BUFFER_SIZE,
					PooslConstants.DEFAULT_MESSAGE_BUFFER_SIZE);
		}
		addField(messageBufferField);
		addField(new LabelFieldEditor("Warning: setting the maximum too high may lead to an out of memory exception.",
				getFieldEditorParent()));
	}

	@Override
	protected void checkState() {
		super.checkState();
		if (messageBufferField.getIntValue() > 0) {
			setErrorMessage(null);
			setValid(true);
		} else {
			setErrorMessage("Message buffer size must be larger than 0.");
			setValid(false);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		super.propertyChange(event);
		if (event.getProperty().equals(IntegerFieldEditor.VALUE)) {
			checkState();
		}
	}

	@Override
	protected void performDefaults() {
		super.performDefaults();
		messageBufferField.setStringValue(String.valueOf(PooslConstants.DEFAULT_MESSAGE_BUFFER_SIZE));
	}
}
