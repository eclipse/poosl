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

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

/**
 * The PooslCommonContentProvider.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslCommonContentProvider implements ICommonContentProvider {

    @Override
    public Object[] getElements(Object inputElement) {
        // Empty
        return null;
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        // Empty
        return null;
    }

    @Override
    public Object getParent(Object element) {
        // Empty
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        // Empty
        return false;
    }

    @Override
    public void dispose() {
        // Empty
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // Empty
    }

    @Override
    public void restoreState(IMemento aMemento) {
        // Empty
    }

    @Override
    public void saveState(IMemento aMemento) {
        // Empty
    }

    @Override
    public void init(ICommonContentExtensionSite aConfig) {
        // Empty
    }
}
