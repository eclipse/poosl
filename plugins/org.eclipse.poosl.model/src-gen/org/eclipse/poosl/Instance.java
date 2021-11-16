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
 * A representation of the model object '<em><b>Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.poosl.Instance#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.poosl.Instance#getInstanceParameters <em>Instance Parameters</em>}</li>
 *   <li>{@link org.eclipse.poosl.Instance#getClassDefinition <em>Class Definition</em>}</li>
 * </ul>
 *
 * @see org.eclipse.poosl.PooslPackage#getInstance()
 * @model
 * @generated
 */
public interface Instance extends Annotable
{
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.poosl.PooslPackage#getInstance_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.poosl.Instance#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Instance Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.poosl.InstanceParameter}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instance Parameters</em>' containment reference list.
	 * @see org.eclipse.poosl.PooslPackage#getInstance_InstanceParameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<InstanceParameter> getInstanceParameters();

	/**
	 * Returns the value of the '<em><b>Class Definition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Definition</em>' attribute.
	 * @see #setClassDefinition(String)
	 * @see org.eclipse.poosl.PooslPackage#getInstance_ClassDefinition()
	 * @model
	 * @generated
	 */
	String getClassDefinition();

	/**
	 * Sets the value of the '{@link org.eclipse.poosl.Instance#getClassDefinition <em>Class Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Definition</em>' attribute.
	 * @see #getClassDefinition()
	 * @generated
	 */
	void setClassDefinition(String value);

} // Instance
