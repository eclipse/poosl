package nl.esi.poosl.rotalumisclient.breakpoint;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.ui.actions.IToggleBreakpointsTarget;
import org.eclipse.debug.ui.actions.IToggleBreakpointsTargetExtension;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;

import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.ProcessMethodCall;
import nl.esi.poosl.Statement;
import nl.esi.poosl.StatementSequence;
import nl.esi.poosl.SwitchStatementCase;
import nl.esi.poosl.rotalumisclient.Messages;
import nl.esi.poosl.rotalumisclient.PooslConstants;
import nl.esi.poosl.rotalumisclient.debug.PooslDebugHelper;

public class PooslLineBreakpointTarget implements IToggleBreakpointsTarget, IToggleBreakpointsTargetExtension {
	private static final Logger LOGGER = Logger.getLogger(PooslLineBreakpointTarget.class.getName());

	@Override
	public boolean canToggleLineBreakpoints(IWorkbenchPart part, ISelection selection) {
		return true;
	}

	@Override
	public void toggleLineBreakpoints(IWorkbenchPart part, ISelection selection) throws CoreException {
		XtextEditor xtextEditor = getEditor(part);
		IXtextDocument document = getDocument(xtextEditor);
		if (xtextEditor != null && document != null) {
			IEditorInput editorInput = xtextEditor.getEditorInput();
			if (editorInput == null) {
				return;
			}
			IResource resource = editorInput.getAdapter(IResource.class);
			final int selectedLineNumber = ((ITextSelection) selection).getStartLine();

			// Check if there is an existing breakpoint on this line number
			if (deleteBreakpointAtLine(resource, selectedLineNumber)) {
				return;
			}

			Statement actualSemanticObject = getSemanticStatement(resource, document, selectedLineNumber);
			if (actualSemanticObject == null) {
				PooslDebugHelper.showInfoMessage(Messages.DIALOG_BREAKPOINT_TITLE,
						Messages.DIALOG_BREAKPOINT_NO_STATEMENT);
				return;
			}
			if (actualSemanticObject instanceof ProcessMethodCall
					&& actualSemanticObject.eContainer() instanceof ProcessClass) {
				// Initial method call
				PooslDebugHelper.showInfoMessage(Messages.DIALOG_BREAKPOINT_TITLE,
						Messages.DIALOG_BREAKPOINT_INIT_METHOD);
				return;
			}

			int statementLineNumber = NodeModelUtils.getNode(actualSemanticObject).getStartLine() - 1;

			// Check if there is an existing breakpoint on this line number
			if (!deleteBreakpointAtLine(resource, statementLineNumber)) {
				// create line breakpoint (doc line numbers start at 0)
				PooslLineBreakpoint lineBreakpoint = new PooslLineBreakpoint(resource, statementLineNumber + 1);
				DebugPlugin.getDefault().getBreakpointManager().addBreakpoint(lineBreakpoint);
			}
		}
	}

	private boolean deleteBreakpointAtLine(IResource resource, int lineNumber) throws CoreException {
		IBreakpoint[] breakpoints = DebugPlugin.getDefault().getBreakpointManager()
				.getBreakpoints(PooslConstants.DEBUG_MODEL_ID);
		for (int i = 0; i < breakpoints.length; i++) {
			IBreakpoint breakpoint = breakpoints[i];
			if (resource.equals(breakpoint.getMarker().getResource())
					&& ((ILineBreakpoint) breakpoint).getLineNumber() == (lineNumber + 1)) {
				// existing breakpoint; delete
				breakpoint.delete();
				return true;
			}
		}
		return false;
	}

	private Statement getSemanticStatement(IResource resource, IXtextDocument document, int lineNumber) {
		int lineOffset = -1;
		try {
			lineOffset = document.getLineInformation(lineNumber).getOffset();
		} catch (BadLocationException e) {
			LOGGER.log(Level.WARNING, e.getMessage(), e);
		}
		EObject actualSemanticObject = getSemanticObject(resource, lineOffset);
		if (actualSemanticObject instanceof ProcessMethod) {
			actualSemanticObject = ((ProcessMethod) actualSemanticObject).getBody();
		}
		if (actualSemanticObject instanceof SwitchStatementCase) {
			actualSemanticObject = ((SwitchStatementCase) actualSemanticObject).getBody();
		}
		while (actualSemanticObject instanceof StatementSequence) {
			StatementSequence sequence = ((StatementSequence) actualSemanticObject);
			if (!sequence.getStatements().isEmpty()) {
				actualSemanticObject = sequence.getStatements().get(0);
			} else {
				actualSemanticObject = null;
			}
		}
		while (actualSemanticObject != null && !(actualSemanticObject instanceof Statement)) {
			actualSemanticObject = actualSemanticObject.eContainer();
		}
		return (Statement) actualSemanticObject;
	}

	private EObject getSemanticObject(IResource resource, int lineOffset) {
		XtextResourceSet resourceSet = new XtextResourceSet();
		resourceSet.getPackageRegistry().put(nl.esi.poosl.PooslPackage.eINSTANCE.getNsURI(),
				nl.esi.poosl.PooslPackage.eINSTANCE);
		URI uri = URI.createFileURI(resource.getLocation().toOSString());
		XtextResource xtextResource = (XtextResource) resourceSet.getResource(uri, true);
		ILeafNode leafNode = NodeModelUtils.findLeafNodeAtOffset(xtextResource.getParseResult().getRootNode(),
				lineOffset);
		return NodeModelUtils.findActualSemanticObjectFor(leafNode);
	}

	/*
	 * Returns the editor being used to edit a Poosl file, associated with the given
	 * part, or <code>null</code> if none.
	 * 
	 * @param part workbench part
	 * 
	 * @return the editor being used to edit a Poosl file, associated with the given
	 * part, or <code>null</code> if none
	 */
	private XtextEditor getEditor(IWorkbenchPart part) {
		if (part instanceof XtextEditor) {
			XtextEditor editorPart = (XtextEditor) part;
			IResource resource = editorPart.getEditorInput().getAdapter(IResource.class);
			if (resource != null) {
				String extension = resource.getFileExtension();
				if (extension != null && "poosl".equals(extension)) {
					return editorPart;
				}
			}
		}
		return null;
	}

	private IXtextDocument getDocument(XtextEditor xtextEditor) {
		if (xtextEditor != null) {
			IDocumentProvider documentProvider = xtextEditor.getDocumentProvider();
			if (documentProvider != null) {
				IDocument document = documentProvider.getDocument(xtextEditor.getEditorInput());
				if (document instanceof IXtextDocument) {
					return (IXtextDocument) document;
				}
			}
		}
		return null;
	}

	@Override
	public void toggleMethodBreakpoints(IWorkbenchPart part, ISelection selection) throws CoreException {
		throw new UnsupportedOperationException("toggleMethodBreakpoints() not supported");
	}

	@Override
	public boolean canToggleMethodBreakpoints(IWorkbenchPart part, ISelection selection) {
		return false;
	}

	@Override
	public void toggleWatchpoints(IWorkbenchPart part, ISelection selection) throws CoreException {
		throw new UnsupportedOperationException("toggleWatchpoints() not supported");
	}

	@Override
	public boolean canToggleWatchpoints(IWorkbenchPart part, ISelection selection) {
		return false;
	}

	@Override
	public void toggleBreakpoints(IWorkbenchPart part, ISelection selection) throws CoreException {
		toggleLineBreakpoints(part, selection);
	}

	@Override
	public boolean canToggleBreakpoints(IWorkbenchPart part, ISelection selection) {
		return true;
	}
}
