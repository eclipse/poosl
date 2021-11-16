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
package org.eclipse.poosl.rotalumisclient.views.diagram;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;

/**
 * This is a wrapper class for a lifeline to add a property descriptor to it.
 * 
 * @author Jeroen van Schelven
 *
 */
public class PooslDiagramLifeline implements IPropertySource {
    private static final String PROPERTY_NAME = "poosldiagramlifeline.name"; //$NON-NLS-1$

    // class definition
    private static final IPropertyDescriptor[] PROPERTIES = new IPropertyDescriptor[] {
            new PropertyDescriptor(PROPERTY_NAME, "Hierarchical name") };

    private final String name;

    public PooslDiagramLifeline(String name) {
        this.name = name;
    }

    @Override
    public Object getEditableValue() {
        return null;
    }

    @Override
    public IPropertyDescriptor[] getPropertyDescriptors() {
        return PROPERTIES;
    }

    @Override
    public Object getPropertyValue(Object id) {
        if (id.equals(PROPERTY_NAME)) {
            return name;
        }
        return null;
    }

    @Override
    public boolean isPropertySet(Object id) {
        return false;
    }

    @Override
    public void resetPropertyValue(Object id) {
        // Not used due to the fact that isPropertySet returns false
    }

    @Override
    public void setPropertyValue(Object id, Object value) {
        // Not used due to the fact that isPropertySet returns false
    }
}
