/**
 * Copyright (c) 2021 TNO/ESI
 *  This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License 2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 * 
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     TNO/ESI - initial API and implementation
 *     Obeo - refactoring
 */
package org.eclipse.poosl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Output Variable</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.poosl.OutputVariable#getVariable <em>Variable</em>}</li>
 * </ul>
 *
 * @see org.eclipse.poosl.PooslPackage#getOutputVariable()
 * @model
 * @generated
 */
public interface OutputVariable extends EObject {
    /**
     * Returns the value of the '<em><b>Variable</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Variable</em>' attribute.
     * @see #setVariable(String)
     * @see org.eclipse.poosl.PooslPackage#getOutputVariable_Variable()
     * @model
     * @generated
     */
    String getVariable();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.OutputVariable#getVariable <em>Variable</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Variable</em>' attribute.
     * @see #getVariable()
     * @generated
     */
    void setVariable(String value);

} // OutputVariable
