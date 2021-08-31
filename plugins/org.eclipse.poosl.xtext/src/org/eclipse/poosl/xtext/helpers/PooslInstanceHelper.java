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
package org.eclipse.poosl.xtext.helpers;

/**
 * The PooslInstanceHelper.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslInstanceHelper {
    private String aClassName;

    private String iName;

    public PooslInstanceHelper(String aClassName, String iName) {
        this.aClassName = aClassName;
        this.iName = iName;
    }

    public String getArchitecturalClassName() {
        return aClassName;
    }

    public String getInstanceName() {
        return iName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PooslInstanceHelper) {
            PooslInstanceHelper instance = (PooslInstanceHelper) obj;
            return instance.getArchitecturalClassName().equals(this.getArchitecturalClassName()) && instance.getInstanceName().equals(this.getInstanceName());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return getArchitecturalClassName().hashCode() + getInstanceName().hashCode();
    }
}
