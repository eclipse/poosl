/*******************************************************************************
 * Copyright (c) 2021 TNO/ESI
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    TNO/ESI - initial API and implementation
 *    Obeo - refactoring
 *******************************************************************************/
package org.eclipse.poosl.sirius.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.poosl.ClusterClass;
import org.eclipse.poosl.Poosl;
import org.eclipse.poosl.sirius.helpers.ConvertHelper;
import org.eclipse.poosl.sirius.helpers.GraphicalEditorHelper;
import org.eclipse.poosl.xtext.importing.ImportingHelper;
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

/**
 * The GraphicalEditorHandler.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class GraphicalEditorHandler extends AbstractHandler {
    private static final ILog LOGGER = Platform.getLog(GraphicalEditorHandler.class);

    private static final String COMMAND_EDITOR_CLASS_DIAGRAM = "org.eclipse.poosl.commands.sirius.editor.openclassdiagram"; //$NON-NLS-1$

    private static final String COMMAND_EDITOR_GRAPHICAL = "org.eclipse.poosl.commands.sirius.editor.opengraphicaleditor"; //$NON-NLS-1$

    private static final String COMMAND_EDITOR_STRUCTURE_DIAGRAM = "org.eclipse.poosl.commands.sirius.editor.opencompositestructurediagram"; //$NON-NLS-1$

    private static final String WARNING_CURSOR_POSITION_UNKNOWN = "Cursor position was not found.";

    private static final String WARNING_SELECTION_CAST_FAILED = "Selection could not be transfered to a textselection.";

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        String commandId = event.getCommand().getId();

        IEditorPart edit = HandlerUtil.getActiveEditor(event);
        IEditorInput input = edit.getEditorInput();
        if (!(input instanceof FileEditorInput)) {
            LOGGER.warn("Unsupported input " + input //$NON-NLS-1$ unexpected
                    + " for command " + commandId); //$NON-NLS-1$
            return null;
        }
        IFile file = ((FileEditorInput) input).getFile();

        if (commandId.equals(COMMAND_EDITOR_CLASS_DIAGRAM)) {
            // unique by file
            Poosl poosl = ConvertHelper.convertIFileToPoosl(file);
            GraphicalEditorHelper.openGraphicalEditorFromFile(file, edit, poosl, true);
        } else {
            ISelection selection = HandlerUtil.getCurrentSelection(event);
            TextSelection text = (TextSelection) selection;
            IWorkbenchPart part = HandlerUtil.getActivePart(event);
            EObject target = null;
            EObject result = getEObject(part, text, edit);

            if (result == null) {
                // unlikely
                return null;
            }

            target = getDiagramTarget(result);
            if (commandId.equals(COMMAND_EDITOR_GRAPHICAL)) {
                GraphicalEditorHelper.openGraphicalEditor(target, edit, file.getProject());

            } else if (commandId.equals(COMMAND_EDITOR_STRUCTURE_DIAGRAM)) {
                // Open structure diagram of selected system or cluster.
                // If none is selected find the main cluster (project
                // explorer behavior)
                if (target instanceof ClusterClass) {
                    GraphicalEditorHelper.openGraphicalEditor(target, edit, file.getProject());
                } else if (target instanceof Poosl) {
                    GraphicalEditorHelper.openGraphicalEditorFromFile(file, edit, (Poosl) target,
                            false);
                } else {
                    LOGGER.warn("Unexpected target for structural diagram: " //$NON-NLS-1$
                            + target.getClass().getName());
                }

            } else {
                LOGGER.warn("Unknown Command id: " + commandId); //$NON-NLS-1$
            }

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
                        LOGGER.error(WARNING_SELECTION_CAST_FAILED);
                        return null;
                    }
                }
                lineOffset = document.getLineInformation(lineNumber).getOffset();
            } catch (BadLocationException e) {
                LOGGER.warn(e.getMessage(), e);
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
            LOGGER.warn(WARNING_CURSOR_POSITION_UNKNOWN, e);
            return -1;
        }
    }

    private EObject getSemanticObject(IResource resource, int lineOffset) {
        XtextResourceSet resourceSet = new XtextResourceSet();
        resourceSet.getPackageRegistry().put(org.eclipse.poosl.PooslPackage.eINSTANCE.getNsURI(),
                org.eclipse.poosl.PooslPackage.eINSTANCE);

        URI uri = URI.createPlatformResourceURI(resource.toString().substring(2), true);
        XtextResource xtextResource = (XtextResource) resourceSet.getResource(uri, true);

        ILeafNode leafNode = NodeModelUtils
                .findLeafNodeAtOffset(xtextResource.getParseResult().getRootNode(), lineOffset);
        EObject actualSemanticObject = NodeModelUtils.findActualSemanticObjectFor(leafNode);

        if (actualSemanticObject == null) {
            actualSemanticObject = ImportingHelper.toPoosl(xtextResource);
        }

        return actualSemanticObject;
    }

    /**
     * Searches from bottom to top to the first object that can be used to
     * create a diagram.
     * 
     * @param origin
     *     to search from
     * @return found target
     */
    private EObject getDiagramTarget(EObject origin) {
        EObject object = origin;
        while (object != null && !(object instanceof ClusterClass) && !(object instanceof Poosl)) {
            object = object.eContainer();
        }
        return object;
    }
}
