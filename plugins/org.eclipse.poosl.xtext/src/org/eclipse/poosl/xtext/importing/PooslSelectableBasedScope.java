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
package org.eclipse.poosl.xtext.importing;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.ISelectable;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.SelectableBasedScope;

import com.google.common.base.Predicate;

/**
 * In the default SelectableBasedScope implementation, two IEObjectDescriptions shadow each other if they have the same
 * name. In POOSL, shadowing is typically determined by the combination of name and numbers of parameters.
 * 
 * To allow using the default IQualifiedNameConverter and overloading methods, the SelectableBasedScope never considers
 * two IEObjectDescriptions to shadow each other.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 */
public final class PooslSelectableBasedScope extends SelectableBasedScope {
    private PooslSelectableBasedScope(IScope outer, ISelectable selectable, Predicate<IEObjectDescription> filter, EClass type, boolean ignoreCase) {
        super(outer, selectable, filter, type, ignoreCase);
    }

    public static IScope createScope(IScope outer, ISelectable selectable, Predicate<IEObjectDescription> filter, EClass type, boolean ignoreCase) {
        if (selectable == null || selectable.isEmpty()) {
            return outer;
        }
        return new PooslSelectableBasedScope(outer, selectable, filter, type, ignoreCase);
    }

    @Override
    protected boolean isShadowed(IEObjectDescription input) {
        return false;
    }
}
