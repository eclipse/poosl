/**
 */
package nl.esi.poosl;

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
 *   <li>{@link nl.esi.poosl.ProcessClass#getReceiveMessages <em>Receive Messages</em>}</li>
 *   <li>{@link nl.esi.poosl.ProcessClass#getSendMessages <em>Send Messages</em>}</li>
 *   <li>{@link nl.esi.poosl.ProcessClass#getInstanceVariables <em>Instance Variables</em>}</li>
 *   <li>{@link nl.esi.poosl.ProcessClass#getMethods <em>Methods</em>}</li>
 *   <li>{@link nl.esi.poosl.ProcessClass#getInitialMethodCall <em>Initial Method Call</em>}</li>
 *   <li>{@link nl.esi.poosl.ProcessClass#getSuperClass <em>Super Class</em>}</li>
 * </ul>
 *
 * @see nl.esi.poosl.PooslPackage#getProcessClass()
 * @model
 * @generated
 */
public interface ProcessClass extends InstantiableClass
{
	/**
	 * Returns the value of the '<em><b>Receive Messages</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.MessageSignature}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Receive Messages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Receive Messages</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getProcessClass_ReceiveMessages()
	 * @model containment="true"
	 * @generated
	 */
	EList<MessageSignature> getReceiveMessages();

	/**
	 * Returns the value of the '<em><b>Send Messages</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.MessageSignature}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Send Messages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Send Messages</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getProcessClass_SendMessages()
	 * @model containment="true"
	 * @generated
	 */
	EList<MessageSignature> getSendMessages();

	/**
	 * Returns the value of the '<em><b>Instance Variables</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.Declaration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instance Variables</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instance Variables</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getProcessClass_InstanceVariables()
	 * @model containment="true"
	 * @generated
	 */
	EList<Declaration> getInstanceVariables();

	/**
	 * Returns the value of the '<em><b>Methods</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.ProcessMethod}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Methods</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Methods</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getProcessClass_Methods()
	 * @model containment="true"
	 * @generated
	 */
	EList<ProcessMethod> getMethods();

	/**
	 * Returns the value of the '<em><b>Initial Method Call</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial Method Call</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial Method Call</em>' containment reference.
	 * @see #setInitialMethodCall(ProcessMethodCall)
	 * @see nl.esi.poosl.PooslPackage#getProcessClass_InitialMethodCall()
	 * @model containment="true"
	 * @generated
	 */
	ProcessMethodCall getInitialMethodCall();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.ProcessClass#getInitialMethodCall <em>Initial Method Call</em>}' containment reference.
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
	 * <p>
	 * If the meaning of the '<em>Super Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super Class</em>' attribute.
	 * @see #setSuperClass(String)
	 * @see nl.esi.poosl.PooslPackage#getProcessClass_SuperClass()
	 * @model
	 * @generated
	 */
	String getSuperClass();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.ProcessClass#getSuperClass <em>Super Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Super Class</em>' attribute.
	 * @see #getSuperClass()
	 * @generated
	 */
	void setSuperClass(String value);

} // ProcessClass
