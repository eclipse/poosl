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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Unary Operator Expression</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.poosl.UnaryOperatorExpression#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.poosl.UnaryOperatorExpression#getOperand <em>Operand</em>}</li>
 * </ul>
 *
 * @see org.eclipse.poosl.PooslPackage#getUnaryOperatorExpression()
 * @model
 * @generated
 */
public interface UnaryOperatorExpression extends Expression {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. The literals are from the enumeration
     * {@link org.eclipse.poosl.OperatorUnary}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see org.eclipse.poosl.OperatorUnary
     * @see #setName(OperatorUnary)
     * @see org.eclipse.poosl.PooslPackage#getUnaryOperatorExpression_Name()
     * @model
     * @generated
     */
    OperatorUnary getName();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.UnaryOperatorExpression#getName <em>Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see org.eclipse.poosl.OperatorUnary
     * @see #getName()
     * @generated
     */
    void setName(OperatorUnary value);

    /**
     * Returns the value of the '<em><b>Operand</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the value of the '<em>Operand</em>' containment reference.
     * @see #setOperand(Expression)
     * @see org.eclipse.poosl.PooslPackage#getUnaryOperatorExpression_Operand()
     * @model containment="true"
     * @generated
     */
    Expression getOperand();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.UnaryOperatorExpression#getOperand <em>Operand</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Operand</em>' containment reference.
     * @see #getOperand()
     * @generated
     */
    void setOperand(Expression value);

} // UnaryOperatorExpression
