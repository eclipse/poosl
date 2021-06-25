package nl.esi.poosl.sirius.navigator;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

import nl.esi.poosl.sirius.navigator.PooslEditorPreferenceDialog.OpenStrategy;

public abstract class OpenPreferenceManager {
	protected String diagramDescription;

	protected abstract String getDialogLabel();

	protected OpenStrategy showEditorPreferenceDialog() {
		return showEditorPreferenceDialog(false);
	}

	protected OpenStrategy showEditorPreferenceDialog(boolean showClassDiagram) {
		PooslEditorPreferenceDialog dialog = new PooslEditorPreferenceDialog(Display.getDefault().getActiveShell(),
				getDialogLabel(), diagramDescription, showClassDiagram);
		if (dialog.open() == Window.OK) {
			return dialog.getSelection();
		} else {
			return OpenStrategy.CANCEL;
		}
	}
}
