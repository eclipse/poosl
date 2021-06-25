package nl.esi.poosl.sirius.navigator;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

public class PooslLabelProvider implements ICommonLabelProvider {

	@Override
	public Image getImage(Object element) {
		// Empty
		return null;
	}

	@Override
	public String getText(Object element) {
		// Empty
		return null;
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		// Empty
	}

	@Override
	public void dispose() {
		// Empty
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// Empty
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
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
	public String getDescription(Object anElement) {
		// Empty
		return null;
	}

	@Override
	public void init(ICommonContentExtensionSite aConfig) {
		// Empty
	}
}
