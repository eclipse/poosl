package nl.esi.poosl.rotalumisclient.views.diagram;

import java.util.Map.Entry;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

public class PooslTreeLabelProvider implements ILabelProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {
		// This label provider does not need to support listeners
	}

	@Override
	public void dispose() {
		// Nothing to dispose
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// This label provider does not need to support listeners
	}

	@Override
	public Image getImage(Object element) {
		return null;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof Entry<?, ?>) {
			String instanceName = (String) ((Entry<?, ?>) element).getKey();
			instanceName = instanceName.substring(instanceName.lastIndexOf('/'), instanceName.length());
			return instanceName;
		}
		return "";
	}
}
