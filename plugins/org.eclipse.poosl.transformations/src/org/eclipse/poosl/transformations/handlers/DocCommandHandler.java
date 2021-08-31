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
package org.eclipse.poosl.transformations.handlers;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.poosl.transformations.poosl2html.Poosl2Html;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * The DocCommandHandler.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class DocCommandHandler implements IHandler {

    @Override
    public void addHandlerListener(IHandlerListener handlerListener) {
        // This handler does not use listeners
    }

    @Override
    public void dispose() {
        // Nothing to dispose
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);

        if (selection instanceof IStructuredSelection) {
            Set<IProject> projects = new HashSet<>();
            for (Object object : ((IStructuredSelection) selection).toList()) {
                if (object instanceof IFile) {
                    IFile file = (IFile) object;
                    projects.add(file.getProject());
                    Poosl2Html.generateDocumentation(file.getLocation().toOSString());
                }
            }
            for (IProject project : projects) {
                TransformationHandlerUtil.refreshProject(project);
            }
        }
        if (selection instanceof TextSelection) {
            IEditorInput editorInput = HandlerUtil.getActiveEditorInput(event);
            if (editorInput instanceof IFileEditorInput) {
                IFile file = ((IFileEditorInput) editorInput).getFile();
                Poosl2Html.generateDocumentation(file.getLocation().toOSString());
                TransformationHandlerUtil.refreshProject(file.getProject());
            }
        }
        return null;
    }

    @Override
    public boolean isEnabled() {
        // This commands visible when is checked in the plugin.xml so when
        // visible it is also available
        return true;
    }

    @Override
    public boolean isHandled() {
        return true;
    }

    @Override
    public void removeHandlerListener(IHandlerListener handlerListener) {
        // This handler does not use listeners
    }
}
