package nl.esi.poosl.rotalumisclient.views;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import nl.esi.poosl.rotalumisclient.PooslConstants;
import nl.esi.poosl.rotalumisclient.debug.PooslVariable;

import org.eclipse.debug.ui.IDetailPane;
import org.eclipse.debug.ui.IDetailPaneFactory;
import org.eclipse.jface.viewers.IStructuredSelection;

public class PooslVariableDetailPaneFactory implements IDetailPaneFactory {

	@Override
	public Set<String> getDetailPaneTypes(IStructuredSelection selection) {
		if (selection.getFirstElement() instanceof PooslVariable) {
			Set<String> detailPaneTypes = new HashSet<>();
			detailPaneTypes.add(PooslConstants.ID_POOSL_VARIABLESVIEW_DETAIL_PANE);
			return detailPaneTypes;
		}
		return Collections.emptySet();
	}

	@Override
	public String getDefaultDetailPane(IStructuredSelection selection) {
		return PooslConstants.ID_POOSL_VARIABLESVIEW_DETAIL_PANE;
	}

	@Override
	public IDetailPane createDetailPane(String paneID) {
		if (paneID.equals(PooslConstants.ID_POOSL_VARIABLESVIEW_DETAIL_PANE)) {
			return new PooslVariableDetailPane();
		}
		return null;
	}

	@Override
	public String getDetailPaneName(String paneID) {
		return null;
	}

	@Override
	public String getDetailPaneDescription(String paneID) {
		return null;
	}
}
