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
 * A representation of the model object '<em><b>Process Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.poosl.ProcessClass#getReceiveMessages <em>Receive Messages</em>}</li>
 *   <li>{@link org.eclipse.poosl.ProcessClass#getSendMessages <em>Send Messages</em>}</li>
 *   <li>{@link org.eclipse.poosl.ProcessClass#getInstanceVariables <em>Instance Variables</em>}</li>
 *   <li>{@link org.eclipse.poosl.ProcessClass#getMethods <em>Methods</em>}</li>
 *   <li>{@link org.eclipse.poosl.ProcessClass#getInitialMethodCall <em>Initial Method Call</em>}</li>
 *   <li>{@link org.eclipse.poosl.ProcessClass#getSuperClass <em>Super Class</em>}</li>
 * </ul>
 *
 * @see org.eclipse.poosl.PooslPackage#getProcessClass()
 * @model
 * @generated
 */
public interface ProcessClass extends InstantiableClass
{
	/**
	 * Returns the value of the '<em><b>Receive Messages</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.poosl.MessageSignature}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Receive Messages</em>' containment reference list.
	 * @see org.eclipse.poosl.PooslPackage#getProcessClass_ReceiveMessages()
	 * @model containment="true"
	 * @generated
	 */
	EList<MessageSignature> getReceiveMessages();

	/**
	 * Returns the value of the '<em><b>Send Messages</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.poosl.MessageSignature}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Send Messages</em>' containment reference list.
	 * @see org.eclipse.poosl.PooslPackage#getProcessClass_SendMessages()
	 * @model containment="true"
	 * @generated
	 */
	EList<MessageSignature> getSendMessages();

	/**
	 * Returns the value of the '<em><b>Instance Variables</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.poosl.Declaration}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instance Variables</em>' containment reference list.
	 * @see org.eclipse.poosl.PooslPackage#getProcessClass_InstanceVariables()
	 * @model containment="true"
	 * @generated
	 */
	EList<Declaration> getInstanceVariables();

	/**
	 * Returns the value of the '<em><b>Methods</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.poosl.ProcessMethod}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Methods</em>' containment reference list.
	 * @see org.eclipse.poosl.PooslPackage#getProcessClass_Methods()
	 * @model containment="true"
	 * @generated
	 */
	EList<ProcessMethod> getMethods();

	/**
	 * Returns the value of the '<em><b>Initial Method Call</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial Method Call</em>' containment reference.
	 * @see #setInitialMethodCall(ProcessMethodCall)
	 * @see org.eclipse.poosl.PooslPackage#getProcessClass_InitialMethodCall()
	 * @model containment="true"
	 * @generated
	 */
	ProcessMethodCall getInitialMethodCall();

	/**
	 * Sets the value of the '{@link org.eclipse.poosl.ProcessClass#getInitialMethodCall <em>Initial Method Call</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial Method Call</em>' containment reference.
	 * @see #getInitialMethodCall()
	 * @generated
	 */
	void setInitialMethodCall(ProcessMethodCall value);

	/**
	 * Returns the value of the '<em><b>Super Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super Class</em>' attribute.
	 * @see #setSuperClass(String)
	 * @see org.eclipse.poosl.PooslPackage#getProcessClass_SuperClass()
	 * @model
	 * @generated
	 */
	String getSuperClass();

	/**
	 * Sets the value of the '{@link org.eclipse.poosl.ProcessClass#getSuperClass <em>Super Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Super Class</em>' attribute.
	 * @see #getSuperClass()
	 * @generated
	 */
	void setSuperClass(String value);

} // ProcessClass
