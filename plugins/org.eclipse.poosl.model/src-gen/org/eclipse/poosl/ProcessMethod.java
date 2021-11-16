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
 * A representation of the model object '<em><b>Process Method</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.poosl.ProcessMethod#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.poosl.ProcessMethod#getInputParameters <em>Input Parameters</em>}</li>
 *   <li>{@link org.eclipse.poosl.ProcessMethod#getOutputParameters <em>Output Parameters</em>}</li>
 *   <li>{@link org.eclipse.poosl.ProcessMethod#getLocalVariables <em>Local Variables</em>}</li>
 *   <li>{@link org.eclipse.poosl.ProcessMethod#getBody <em>Body</em>}</li>
 * </ul>
 *
 * @see org.eclipse.poosl.PooslPackage#getProcessMethod()
 * @model
 * @generated
 */
public interface ProcessMethod extends Annotable
{
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.poosl.PooslPackage#getProcessMethod_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.poosl.ProcessMethod#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Input Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.poosl.Declaration}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Parameters</em>' containment reference list.
	 * @see org.eclipse.poosl.PooslPackage#getProcessMethod_InputParameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<Declaration> getInputParameters();

	/**
	 * Returns the value of the '<em><b>Output Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.poosl.Declaration}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Parameters</em>' containment reference list.
	 * @see org.eclipse.poosl.PooslPackage#getProcessMethod_OutputParameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<Declaration> getOutputParameters();

	/**
	 * Returns the value of the '<em><b>Local Variables</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.poosl.Declaration}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Local Variables</em>' containment reference list.
	 * @see org.eclipse.poosl.PooslPackage#getProcessMethod_LocalVariables()
	 * @model containment="true"
	 * @generated
	 */
	EList<Declaration> getLocalVariables();

	/**
	 * Returns the value of the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body</em>' containment reference.
	 * @see #setBody(Statement)
	 * @see org.eclipse.poosl.PooslPackage#getProcessMethod_Body()
	 * @model containment="true"
	 * @generated
	 */
	Statement getBody();

	/**
	 * Sets the value of the '{@link org.eclipse.poosl.ProcessMethod#getBody <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body</em>' containment reference.
	 * @see #getBody()
	 * @generated
	 */
	void setBody(Statement value);

} // ProcessMethod
