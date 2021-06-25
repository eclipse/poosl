package nl.esi.poosl.sirius.navigator;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

public class PooslNaviActionProvider extends CommonActionProvider {
	private PooslOpenAction action;
	private boolean contribute = false;

	public PooslNaviActionProvider() {
	}

	@Override
	public void init(ICommonActionExtensionSite aConfig) {
		ICommonViewerSite site = aConfig.getViewSite();

		if (site instanceof ICommonViewerWorkbenchSite) {
			IWorkbenchPage page = ((ICommonViewerWorkbenchSite) site).getPage();
			action = new PooslOpenAction(page);
			contribute = true;
		}
	}

	@Override
	public void fillActionBars(IActionBars actionBars) {
		if (!contribute) {
			return;
		}

		IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
		if (selection.size() == 1 && selection.getFirstElement() instanceof IFile) {
			action.selectionChanged(selection);
			actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, action);
		}
	}
}
