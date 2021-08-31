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

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>While Expression</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.poosl.WhileExpression#getCondition <em>Condition</em>}</li>
 * <li>{@link org.eclipse.poosl.WhileExpression#getBody <em>Body</em>}</li>
 * </ul>
 *
 * @see org.eclipse.poosl.PooslPackage#getWhileExpression()
 * @model
 * @generated
 */
public interface WhileExpression extends Expression {
    /**
     * Returns the value of the '<em><b>Condition</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the value of the '<em>Condition</em>' containment reference.
     * @see #setCondition(Expression)
     * @see org.eclipse.poosl.PooslPackage#getWhileExpression_Condition()
     * @model containment="true"
     * @generated
     */
    Expression getCondition();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.WhileExpression#getCondition <em>Condition</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Condition</em>' containment reference.
     * @see #getCondition()
     * @generated
     */
    void setCondition(Expression value);

    /**
     * Returns the value of the '<em><b>Body</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the value of the '<em>Body</em>' containment reference.
     * @see #setBody(Expression)
     * @see org.eclipse.poosl.PooslPackage#getWhileExpression_Body()
     * @model containment="true"
     * @generated
     */
    Expression getBody();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.WhileExpression#getBody <em>Body</em>}' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Body</em>' containment reference.
     * @see #getBody()
     * @generated
     */
    void setBody(Expression value);

} // WhileExpression
