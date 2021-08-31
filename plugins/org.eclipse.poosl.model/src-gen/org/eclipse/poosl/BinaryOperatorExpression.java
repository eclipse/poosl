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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Binary Operator Expression</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.poosl.BinaryOperatorExpression#getLeftOperand <em>Left Operand</em>}</li>
 * <li>{@link org.eclipse.poosl.BinaryOperatorExpression#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.poosl.BinaryOperatorExpression#getRightOperand <em>Right Operand</em>}</li>
 * </ul>
 *
 * @see org.eclipse.poosl.PooslPackage#getBinaryOperatorExpression()
 * @model
 * @generated
 */
public interface BinaryOperatorExpression extends Expression {
    /**
     * Returns the value of the '<em><b>Left Operand</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the value of the '<em>Left Operand</em>' containment reference.
     * @see #setLeftOperand(Expression)
     * @see org.eclipse.poosl.PooslPackage#getBinaryOperatorExpression_LeftOperand()
     * @model containment="true"
     * @generated
     */
    Expression getLeftOperand();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.BinaryOperatorExpression#getLeftOperand <em>Left Operand</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Left Operand</em>' containment reference.
     * @see #getLeftOperand()
     * @generated
     */
    void setLeftOperand(Expression value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. The literals are from the enumeration
     * {@link org.eclipse.poosl.OperatorBinary}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see org.eclipse.poosl.OperatorBinary
     * @see #setName(OperatorBinary)
     * @see org.eclipse.poosl.PooslPackage#getBinaryOperatorExpression_Name()
     * @model
     * @generated
     */
    OperatorBinary getName();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.BinaryOperatorExpression#getName <em>Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see org.eclipse.poosl.OperatorBinary
     * @see #getName()
     * @generated
     */
    void setName(OperatorBinary value);

    /**
     * Returns the value of the '<em><b>Right Operand</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the value of the '<em>Right Operand</em>' containment reference.
     * @see #setRightOperand(Expression)
     * @see org.eclipse.poosl.PooslPackage#getBinaryOperatorExpression_RightOperand()
     * @model containment="true"
     * @generated
     */
    Expression getRightOperand();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.BinaryOperatorExpression#getRightOperand <em>Right Operand</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Right Operand</em>' containment reference.
     * @see #getRightOperand()
     * @generated
     */
    void setRightOperand(Expression value);

} // BinaryOperatorExpression
