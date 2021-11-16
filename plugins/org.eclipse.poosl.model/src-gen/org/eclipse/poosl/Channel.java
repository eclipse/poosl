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
 * A representation of the model object '<em><b>Channel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.poosl.Channel#getExternalPort <em>External Port</em>}</li>
 *   <li>{@link org.eclipse.poosl.Channel#getInstancePorts <em>Instance Ports</em>}</li>
 * </ul>
 *
 * @see org.eclipse.poosl.PooslPackage#getChannel()
 * @model
 * @generated
 */
public interface Channel extends Annotable
{
	/**
	 * Returns the value of the '<em><b>External Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Port</em>' reference.
	 * @see #setExternalPort(Port)
	 * @see org.eclipse.poosl.PooslPackage#getChannel_ExternalPort()
	 * @model
	 * @generated
	 */
	Port getExternalPort();

	/**
	 * Sets the value of the '{@link org.eclipse.poosl.Channel#getExternalPort <em>External Port</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Port</em>' reference.
	 * @see #getExternalPort()
	 * @generated
	 */
	void setExternalPort(Port value);

	/**
	 * Returns the value of the '<em><b>Instance Ports</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.poosl.InstancePort}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instance Ports</em>' containment reference list.
	 * @see org.eclipse.poosl.PooslPackage#getChannel_InstancePorts()
	 * @model containment="true"
	 * @generated
	 */
	EList<InstancePort> getInstancePorts();

} // Channel
