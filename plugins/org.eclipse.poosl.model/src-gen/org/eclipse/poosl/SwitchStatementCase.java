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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Switch Statement Case</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.poosl.SwitchStatementCase#getValue <em>Value</em>}</li>
 * <li>{@link org.eclipse.poosl.SwitchStatementCase#getBody <em>Body</em>}</li>
 * </ul>
 *
 * @see org.eclipse.poosl.PooslPackage#getSwitchStatementCase()
 * @model
 * @generated
 */
public interface SwitchStatementCase extends EObject {
    /**
     * Returns the value of the '<em><b>Value</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the value of the '<em>Value</em>' containment reference.
     * @see #setValue(Expression)
     * @see org.eclipse.poosl.PooslPackage#getSwitchStatementCase_Value()
     * @model containment="true"
     * @generated
     */
    Expression getValue();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.SwitchStatementCase#getValue <em>Value</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Value</em>' containment reference.
     * @see #getValue()
     * @generated
     */
    void setValue(Expression value);

    /**
     * Returns the value of the '<em><b>Body</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the value of the '<em>Body</em>' containment reference.
     * @see #setBody(Statement)
     * @see org.eclipse.poosl.PooslPackage#getSwitchStatementCase_Body()
     * @model containment="true"
     * @generated
     */
    Statement getBody();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.SwitchStatementCase#getBody <em>Body</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Body</em>' containment reference.
     * @see #getBody()
     * @generated
     */
    void setBody(Statement value);

} // SwitchStatementCase
