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
package org.eclipse.poosl.sirius.navigator;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.poosl.Poosl;
import org.eclipse.poosl.sirius.IPreferenceConstants;
import org.eclipse.poosl.sirius.helpers.ConvertHelper;
import org.eclipse.poosl.sirius.helpers.GraphicalEditorHelper;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.actions.OpenFileAction;

/**
 * The PooslOpenAction.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslOpenAction extends Action {
    private final IWorkbenchPage page;

    private IStructuredSelection selection;

    public PooslOpenAction(IWorkbenchPage page) {
        this.page = page;
    }

    public void selectionChanged(IStructuredSelection sel) {
        this.selection = sel;
    }

    @Override
    public void run() {
        IFile file = ConvertHelper.convertISelectionToIFile(selection);
        Poosl poosl = ConvertHelper.convertIFileToPoosl(file);

        OpenFilePreferenceManager prefManager = new OpenFilePreferenceManager(poosl);
        String pref = prefManager.getEditorPreference();
        if (pref == null) {
            // dialog window was canceled, don't do anything
            return;
        }

        if (pref.equals(IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_TEXTUAL)) {
            OpenFileAction fileAction = new OpenFileAction(page);
            fileAction.selectionChanged(selection);
            fileAction.run();
        } else {
            GraphicalEditorHelper.openGraphicalEditor(prefManager.getDiagramTarget(), null, file.getProject());
        }
    }
}
