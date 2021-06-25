package nl.esi.poosl.sirius.navigator;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.actions.OpenFileAction;

import nl.esi.poosl.Poosl;
import nl.esi.poosl.sirius.IPreferenceConstants;
import nl.esi.poosl.sirius.helpers.ConvertHelper;
import nl.esi.poosl.sirius.helpers.GraphicalEditorHelper;

public class PooslOpenAction extends Action {
	private final IWorkbenchPage page;
	private IStructuredSelection selection;

	public PooslOpenAction(IWorkbenchPage page) {
		this.page = page;
	}

	public void selectionChanged(IStructuredSelection selection) {
		this.selection = selection;
	}

	@Override
	public void run() {
		IFile file = ConvertHelper.convertISelectionToIFile(selection);
		Poosl poosl = ConvertHelper.convertIFileToPoosl(file);

		OpenFilePreferenceManager prefManager = new OpenFilePreferenceManager(poosl);
		String pref = prefManager.getEditorPreference();
		if (pref == null) {
			// dialog window was canceled, don't do anything
			return;
		}

		if (pref.equals(IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_TEXTUAL)) {
			OpenFileAction fileAction = new OpenFileAction(page);
			fileAction.selectionChanged(selection);
			fileAction.run();
		} else {
			GraphicalEditorHelper.openGraphicalEditor(prefManager.getDiagramTarget(), null, file.getProject());
		}
	}
}
