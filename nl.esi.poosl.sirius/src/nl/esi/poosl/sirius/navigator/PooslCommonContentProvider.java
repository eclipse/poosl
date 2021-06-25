package nl.esi.poosl.sirius.navigator;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

public class PooslCommonContentProvider implements ICommonContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		// Empty
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		// Empty
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// Empty
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		// Empty
		return false;
	}

	@Override
	public void dispose() {
		// Empty
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// Empty
	}

	@Override
	public void restoreState(IMemento aMemento) {
		// Empty
	}

	@Override
	public void saveState(IMemento aMemento) {
		// Empty
	}

	@Override
	public void init(ICommonContentExtensionSite aConfig) {
		// Empty
	}
}
