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
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interrupt Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.poosl.InterruptStatement#getNormalClause <em>Normal Clause</em>}</li>
 *   <li>{@link org.eclipse.poosl.InterruptStatement#getInterruptingClause <em>Interrupting Clause</em>}</li>
 * </ul>
 *
 * @see org.eclipse.poosl.PooslPackage#getInterruptStatement()
 * @model
 * @generated
 */
public interface InterruptStatement extends Statement
{
	/**
	 * Returns the value of the '<em><b>Normal Clause</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Normal Clause</em>' containment reference.
	 * @see #setNormalClause(Statement)
	 * @see org.eclipse.poosl.PooslPackage#getInterruptStatement_NormalClause()
	 * @model containment="true"
	 * @generated
	 */
	Statement getNormalClause();

	/**
	 * Sets the value of the '{@link org.eclipse.poosl.InterruptStatement#getNormalClause <em>Normal Clause</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Normal Clause</em>' containment reference.
	 * @see #getNormalClause()
	 * @generated
	 */
	void setNormalClause(Statement value);

	/**
	 * Returns the value of the '<em><b>Interrupting Clause</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interrupting Clause</em>' containment reference.
	 * @see #setInterruptingClause(Statement)
	 * @see org.eclipse.poosl.PooslPackage#getInterruptStatement_InterruptingClause()
	 * @model containment="true"
	 * @generated
	 */
	Statement getInterruptingClause();

	/**
	 * Sets the value of the '{@link org.eclipse.poosl.InterruptStatement#getInterruptingClause <em>Interrupting Clause</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interrupting Clause</em>' containment reference.
	 * @see #getInterruptingClause()
	 * @generated
	 */
	void setInterruptingClause(Statement value);

} // InterruptStatement
