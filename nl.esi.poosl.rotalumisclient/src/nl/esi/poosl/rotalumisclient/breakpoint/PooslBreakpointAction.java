package nl.esi.poosl.rotalumisclient.breakpoint;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

public class PooslBreakpointAction implements IEditorActionDelegate {
	private static final Logger LOGGER = Logger.getLogger(PooslBreakpointAction.class.getName());
	IEditorPart editorPart;
	ISelection selection;

	@Override
	public void run(IAction action) {
		final PooslLineBreakpointTarget adapter = new PooslLineBreakpointTarget();
		try {
			adapter.toggleLineBreakpoints(editorPart, selection);
		} catch (CoreException e) {
			LOGGER.log(Level.WARNING, e.getMessage(), e);
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

	@Override
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		this.editorPart = targetEditor;
	}

}
