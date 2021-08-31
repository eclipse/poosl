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
package org.eclipse.poosl.sirius.navigator.edit;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerSite;

/**
 * The PooslEditActionProvider.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslEditActionProvider extends CommonActionProvider {
    private boolean contribute;

    private PooslEditGroup editGroup;

    @Override
    public void init(ICommonActionExtensionSite aSite) {
        super.init(aSite);
        ICommonViewerSite site = aSite.getViewSite();
        editGroup = new PooslEditGroup(site.getShell());
        contribute = true;

    }

    @Override
    public void fillActionBars(IActionBars actionBars) {
        if (!contribute) {
            return;
        }
        editGroup.fillActionBars(actionBars);
    }

    @Override
    public void dispose() {
        editGroup.dispose();
    }

    @Override
    public void fillContextMenu(IMenuManager menu) {
        editGroup.fillContextMenu(menu);
    }

    @Override
    public void setContext(ActionContext context) {
        editGroup.setContext(context);
    }

    @Override
    public void updateActionBars() {
        editGroup.updateActionBars();
    }
}
