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
package org.eclipse.poosl.rotalumisclient.views;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.debug.ui.IDetailPane;
import org.eclipse.debug.ui.IDetailPaneFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.poosl.rotalumisclient.PooslConstants;
import org.eclipse.poosl.rotalumisclient.debug.PooslVariable;

/**
 * The PooslVariableDetailPaneFactory.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslVariableDetailPaneFactory implements IDetailPaneFactory {

    @Override
    public Set<String> getDetailPaneTypes(IStructuredSelection selection) {
        if (selection.getFirstElement() instanceof PooslVariable) {
            Set<String> detailPaneTypes = new HashSet<>();
            detailPaneTypes.add(PooslConstants.ID_POOSL_VARIABLESVIEW_DETAIL_PANE);
            return detailPaneTypes;
        }
        return Collections.emptySet();
    }

    @Override
    public String getDefaultDetailPane(IStructuredSelection selection) {
        return PooslConstants.ID_POOSL_VARIABLESVIEW_DETAIL_PANE;
    }

    @Override
    public IDetailPane createDetailPane(String paneID) {
        if (paneID.equals(PooslConstants.ID_POOSL_VARIABLESVIEW_DETAIL_PANE)) {
            return new PooslVariableDetailPane();
        }
        return null;
    }

    @Override
    public String getDetailPaneName(String paneID) {
        return null;
    }

    @Override
    public String getDetailPaneDescription(String paneID) {
        return null;
    }
}
