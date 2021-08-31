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

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

/**
 * The PooslLabelProvider.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslLabelProvider implements ICommonLabelProvider {

    @Override
    public Image getImage(Object element) {
        // Empty
        return null;
    }

    @Override
    public String getText(Object element) {
        // Empty
        return null;
    }

    @Override
    public void addListener(ILabelProviderListener listener) {
        // Empty
    }

    @Override
    public void dispose() {
        // Empty
    }

    @Override
    public boolean isLabelProperty(Object element, String property) {
        // Empty
        return false;
    }

    @Override
    public void removeListener(ILabelProviderListener listener) {
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
    public String getDescription(Object anElement) {
        // Empty
        return null;
    }

    @Override
    public void init(ICommonContentExtensionSite aConfig) {
        // Empty
    }
}
