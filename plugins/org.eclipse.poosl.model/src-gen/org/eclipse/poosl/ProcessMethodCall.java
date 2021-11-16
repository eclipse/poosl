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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Process Method Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.poosl.ProcessMethodCall#getInputArguments <em>Input Arguments</em>}</li>
 *   <li>{@link org.eclipse.poosl.ProcessMethodCall#getOutputVariables <em>Output Variables</em>}</li>
 *   <li>{@link org.eclipse.poosl.ProcessMethodCall#getMethod <em>Method</em>}</li>
 * </ul>
 *
 * @see org.eclipse.poosl.PooslPackage#getProcessMethodCall()
 * @model
 * @generated
 */
public interface ProcessMethodCall extends Statement
{
	/**
	 * Returns the value of the '<em><b>Input Arguments</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.poosl.Expression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Arguments</em>' containment reference list.
	 * @see org.eclipse.poosl.PooslPackage#getProcessMethodCall_InputArguments()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getInputArguments();

	/**
	 * Returns the value of the '<em><b>Output Variables</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.poosl.OutputVariable}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Variables</em>' containment reference list.
	 * @see org.eclipse.poosl.PooslPackage#getProcessMethodCall_OutputVariables()
	 * @model containment="true"
	 * @generated
	 */
	EList<OutputVariable> getOutputVariables();

	/**
	 * Returns the value of the '<em><b>Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method</em>' attribute.
	 * @see #setMethod(String)
	 * @see org.eclipse.poosl.PooslPackage#getProcessMethodCall_Method()
	 * @model
	 * @generated
	 */
	String getMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.poosl.ProcessMethodCall#getMethod <em>Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method</em>' attribute.
	 * @see #getMethod()
	 * @generated
	 */
	void setMethod(String value);

} // ProcessMethodCall
