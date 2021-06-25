package nl.esi.poosl.sirius.navigator.edit;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerSite;

public class PooslEditActionProvider extends CommonActionProvider {
	private boolean contribute = false;
	private PooslEditGroup editGroup;

	@Override
	public void init(ICommonActionExtensionSite aSite) {
		super.init(aSite);
		ICommonViewerSite site = aSite.getViewSite();
		editGroup = new PooslEditGroup(site.getShell());
		contribute = true;

	}

	@Override
	public void fillActionBars(IActionBars actionBars) {
		if (!contribute) {
			return;
		}
		editGroup.fillActionBars(actionBars);
	}

	@Override
	public void dispose() {
		editGroup.dispose();
	}

	@Override
	public void fillContextMenu(IMenuManager menu) {
		editGroup.fillContextMenu(menu);
	}

	@Override
	public void setContext(ActionContext context) {
		editGroup.setContext(context);
	}

	@Override
	public void updateActionBars() {
		editGroup.updateActionBars();
	}
}
