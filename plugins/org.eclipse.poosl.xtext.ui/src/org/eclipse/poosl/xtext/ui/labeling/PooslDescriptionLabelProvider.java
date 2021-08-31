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
package org.eclipse.poosl.xtext.ui.labeling;

import org.eclipse.poosl.PooslPackage.Literals;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.ui.label.DefaultDescriptionLabelProvider;

/**
 * Provides labels for a IEObjectDescriptions and IResourceDescriptions. This PooslDescriptionLabelProvider is used to
 * create better label text for References in System. Other text representations are retrieved by
 * {@link DefaultDescriptionLabelProvider}
 * 
 * see http://www.eclipse.org/Xtext/documentation.html#labelProvider
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 */
public class PooslDescriptionLabelProvider extends DefaultDescriptionLabelProvider {

    @Override
    public Object text(IEObjectDescription element) {
        if (element.getEClass() == Literals.POOSL) {
            return "System"; //$NON-NLS-1$
        }
        return super.text(element);
    }
}
