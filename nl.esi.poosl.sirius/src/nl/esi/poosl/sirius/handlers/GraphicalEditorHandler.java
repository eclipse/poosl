package nl.esi.poosl.sirius.handlers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;

import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.Poosl;
import nl.esi.poosl.sirius.helpers.ConvertHelper;
import nl.esi.poosl.sirius.helpers.GraphicalEditorHelper;
import nl.esi.poosl.xtext.importing.ImportingHelper;

public class GraphicalEditorHandler extends AbstractHandler {
	private static final Logger LOGGER = Logger.getLogger(GraphicalEditorHandler.class.getName());

	private static final String COMMAND_EDITOR_CLASS_DIAGRAM = "nl.esi.poosl.commands.sirius.editor.openclassdiagram";
	private static final String COMMAND_EDITOR_GRAPHICAL = "nl.esi.poosl.commands.sirius.editor.opengraphicaleditor";
	private static final String COMMAND_EDITOR_STRUCTURE_DIAGRAM = "nl.esi.poosl.commands.sirius.editor.opencompositestructurediagram";

	private static final String WARNING_CURSOR_POSITION_UNKNOWN = "Cursor position was not found.";
	private static final String WARNING_SELECTION_CAST_FAILED = "Selection could not be transfered to a textselection.";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String commandId = event.getCommand().getId();

		IEditorPart edit = HandlerUtil.getActiveEditor(event);
		IEditorInput input = edit.getEditorInput();
		IFile file = null;
		if (input instanceof FileEditorInput) {
			file = ((FileEditorInput) input).getFile();

			if (commandId.equals(COMMAND_EDITOR_CLASS_DIAGRAM)) {
				Poosl poosl = ConvertHelper.convertIFileToPoosl(file);
				GraphicalEditorHelper.openGraphicalEditorFromFile(file, edit, poosl,
						commandId.equals(COMMAND_EDITOR_CLASS_DIAGRAM));
			} else {
				ISelection selection = HandlerUtil.getCurrentSelection(event);
				TextSelection text = (TextSelection) selection;
				IWorkbenchPart part = HandlerUtil.getActivePart(event);
				EObject target = null;
				EObject result = getEObject(part, text, edit);

				if (result != null) {
					target = getDiagramTarget(result);
					if (commandId.equals(COMMAND_EDITOR_GRAPHICAL)) {
						GraphicalEditorHelper.openGraphicalEditor(target, edit, file.getProject());

					} else if (commandId.equals(COMMAND_EDITOR_STRUCTURE_DIAGRAM)) {
						// Open structure diagram of selected system or cluster.
						// If none is selected find the main cluster (project
						// explorer behavior)
						if (target instanceof ClusterClass) {
							GraphicalEditorHelper.openGraphicalEditor(target, edit, file.getProject());
						} else {
							if (target instanceof Poosl) {
								GraphicalEditorHelper.openGraphicalEditorFromFile(file, edit, (Poosl) target,
										commandId.equals(COMMAND_EDITOR_CLASS_DIAGRAM));
							} else {
								LOGGER.log(Level.WARNING,
										"Could find target for opening the graphical editor, from command "
												+ commandId);
							}
						}
					} else {
						LOGGER.log(Level.WARNING, "Command id unknown: " + commandId);
					}
				}
			}
		} else {
			LOGGER.log(Level.WARNING, "Could not get file to open the graphical editor, from command " + commandId);
		}
		return null;
	}

	private EObject getEObject(IWorkbenchPart part, ISelection selection, IEditorPart edit) {
		if (edit instanceof XtextEditor) {
			XtextEditor xtextEditor = (XtextEditor) edit;
			IXtextDocument document = (IXtextDocument) xtextEditor.getDocumentProvider()
					.getDocument(xtextEditor.getEditorInput());
			int cursorposition = getCursorPosition(part);
			int lineOffset = -1;

			try {
				int lineNumber;
				if (cursorposition != -1) {
					lineNumber = document.getLineOfOffset(cursorposition);
				} else {
					if (selection instanceof TextSelection) {
						TextSelection text = (TextSelection) selection;
						lineNumber = text.getStartLine();
					} else {
						LOGGER.log(Level.SEVERE, WARNING_SELECTION_CAST_FAILED);
						return null;
					}
				}
				lineOffset = document.getLineInformation(lineNumber).getOffset();
			} catch (BadLocationException e) {
				LOGGER.log(Level.WARNING, e.getMessage(), e);
			}

			IResource resource = xtextEditor.getEditorInput().getAdapter(IResource.class);
			return getSemanticObject(resource, lineOffset);
		}
		return null;
	}

	private int getCursorPosition(IWorkbenchPart part) {
		try {
			return ((StyledText) part.getAdapter(Control.class)).getCaretOffset();
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, WARNING_CURSOR_POSITION_UNKNOWN, e.getMessage());
			return -1;
		}
	}

	private EObject getSemanticObject(IResource resource, int lineOffset) {
		XtextResourceSet resourceSet = new XtextResourceSet();
		resourceSet.getPackageRegistry().put(nl.esi.poosl.PooslPackage.eINSTANCE.getNsURI(),
				nl.esi.poosl.PooslPackage.eINSTANCE);

		URI uri = URI.createPlatformResourceURI(resource.toString().substring(2), true);
		XtextResource xtextResource = (XtextResource) resourceSet.getResource(uri, true);

		ILeafNode leafNode = NodeModelUtils.findLeafNodeAtOffset(xtextResource.getParseResult().getRootNode(),
				lineOffset);
		EObject actualSemanticObject = NodeModelUtils.findActualSemanticObjectFor(leafNode);

		if (actualSemanticObject == null) {
			actualSemanticObject = ImportingHelper.toPoosl(xtextResource);
		}

		return actualSemanticObject;
	}

	/**
	 * Search from bottom to top to the first object that can be used to create a
	 * diagram
	 * 
	 * @param target
	 * @return
	 */
	private EObject getDiagramTarget(EObject target) {
		EObject object = target;
		while (object != null && !(object instanceof ClusterClass) && !(object instanceof Poosl)) {
			object = object.eContainer();
		}
		return object;
	}
}
