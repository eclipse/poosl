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

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.ILog;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.poosl.sirius.helpers.ConvertHelper;
import org.eclipse.poosl.sirius.helpers.GraphicalEditorHelper;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.ide.IDE;

/**
 * The ProjectExplorerHandler.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class ProjectExplorerHandler extends AbstractHandler {
    private static final ILog LOGGER = Platform.getLog(ProjectExplorerHandler.class);

    private static final String COMMAND_EXPLORER_OPEN_SYSTEM = "org.eclipse.poosl.commands.sirius.explorer.opensystemdiagram"; //$NON-NLS-1$

    private static final String COMMAND_EXPLORER_OPEN_CLASS = "org.eclipse.poosl.commands.sirius.explorer.openclassdiagram"; //$NON-NLS-1$

    private static final String COMMAND_EXPLORER_OPEN_TEXTUAL = "org.eclipse.poosl.commands.sirius.explorer.opentextualeditor"; //$NON-NLS-1$

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);

        if (event.getCommand().getId().equals(COMMAND_EXPLORER_OPEN_CLASS)) {
            GraphicalEditorHelper.openGraphicalEditorFromSelection(selection, true);
        } else if (event.getCommand().getId().equals(COMMAND_EXPLORER_OPEN_SYSTEM)) {
            GraphicalEditorHelper.openGraphicalEditorFromSelection(selection, false);
        } else if (event.getCommand().getId().equals(COMMAND_EXPLORER_OPEN_TEXTUAL)) {
            try {
                IWorkbenchSite site = HandlerUtil.getActiveSite(event);
                IDE.openEditor(site.getPage(), ConvertHelper.convertISelectionToIFile(selection));
            } catch (PartInitException e) {
                LOGGER.warn("Could not open the texual editor of the selected file: " + e.getMessage());
            }
        } else {
            LOGGER.warn("Command id is unknown, opening default diagram.");
            GraphicalEditorHelper.openGraphicalEditorFromSelection(selection, true);
        }
        return null;
    }
}
