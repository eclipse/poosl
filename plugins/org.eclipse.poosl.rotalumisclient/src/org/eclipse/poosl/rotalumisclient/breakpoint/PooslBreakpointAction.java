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
package org.eclipse.poosl.rotalumisclient.breakpoint;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

/**
 * The PooslBreakpointAction.
 *
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslBreakpointAction implements IEditorActionDelegate {
    private static final ILog LOGGER = Platform.getLog(PooslBreakpointAction.class);

    IEditorPart editorPart;

    ISelection selection;

    @Override
    public void run(IAction action) {
        final PooslLineBreakpointTarget adapter = new PooslLineBreakpointTarget();
        try {
            adapter.toggleLineBreakpoints(editorPart, selection);
        } catch (CoreException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    @Override
    public void selectionChanged(IAction action, ISelection sel) {
        selection = sel;
    }

    @Override
    public void setActiveEditor(IAction action, IEditorPart targetEditor) {
        editorPart = targetEditor;
    }

}
