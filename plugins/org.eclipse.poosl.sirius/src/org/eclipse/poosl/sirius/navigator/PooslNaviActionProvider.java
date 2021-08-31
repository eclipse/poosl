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
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

/**
 * The PooslNaviActionProvider.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslNaviActionProvider extends CommonActionProvider {
    private PooslOpenAction action;

    private boolean contribute;

    public PooslNaviActionProvider() {
    }

    @Override
    public void init(ICommonActionExtensionSite aConfig) {
        ICommonViewerSite site = aConfig.getViewSite();

        if (site instanceof ICommonViewerWorkbenchSite) {
            IWorkbenchPage page = ((ICommonViewerWorkbenchSite) site).getPage();
            action = new PooslOpenAction(page);
            contribute = true;
        }
    }

    @Override
    public void fillActionBars(IActionBars actionBars) {
        if (!contribute) {
            return;
        }

        IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
        if (selection.size() == 1 && selection.getFirstElement() instanceof IFile) {
            action.selectionChanged(selection);
            actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, action);
        }
    }
}
