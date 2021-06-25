package nl.esi.poosl.rotalumisclient.views.stacktraceview;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import nl.esi.poosl.generatedxmlclasses.TEengineEventErrorResponse;

public class StackTraceContentProvider implements IStructuredContentProvider {

	@Override
	public void dispose() {
		// do nothing
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// do nothing
	}

	@Override
	public Object[] getElements(Object input) {
		if (input instanceof TEengineEventErrorResponse) {
			TEengineEventErrorResponse error = (TEengineEventErrorResponse) input;
			return error.getStacktrace().getStackframe().toArray();
		} else if (input instanceof List) {
			return ((List<?>) input).toArray();
		}
		return null;
	}
}
