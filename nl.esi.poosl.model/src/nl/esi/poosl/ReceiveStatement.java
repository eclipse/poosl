/**
 */
package nl.esi.poosl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Receive Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.ReceiveStatement#getPortDescriptor <em>Port Descriptor</em>}</li>
 *   <li>{@link nl.esi.poosl.ReceiveStatement#getName <em>Name</em>}</li>
 *   <li>{@link nl.esi.poosl.ReceiveStatement#getVariables <em>Variables</em>}</li>
 *   <li>{@link nl.esi.poosl.ReceiveStatement#getReceptionCondition <em>Reception Condition</em>}</li>
 *   <li>{@link nl.esi.poosl.ReceiveStatement#getPostCommunicationExpression <em>Post Communication Expression</em>}</li>
 * </ul>
 *
 * @see nl.esi.poosl.PooslPackage#getReceiveStatement()
 * @model
 * @generated
 */
public interface ReceiveStatement extends Statement
{
	/**
	 * Returns the value of the '<em><b>Port Descriptor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port Descriptor</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port Descriptor</em>' containment reference.
	 * @see #setPortDescriptor(PortDescriptor)
	 * @see nl.esi.poosl.PooslPackage#getReceiveStatement_PortDescriptor()
	 * @model containment="true"
	 * @generated
	 */
	PortDescriptor getPortDescriptor();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.ReceiveStatement#getPortDescriptor <em>Port Descriptor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port Descriptor</em>' containment reference.
	 * @see #getPortDescriptor()
	 * @generated
	 */
	void setPortDescriptor(PortDescriptor value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see nl.esi.poosl.PooslPackage#getReceiveStatement_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.ReceiveStatement#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Variables</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.OutputVariable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variables</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getReceiveStatement_Variables()
	 * @model containment="true"
	 * @generated
	 */
	EList<OutputVariable> getVariables();

	/**
	 * Returns the value of the '<em><b>Reception Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reception Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reception Condition</em>' containment reference.
	 * @see #setReceptionCondition(Expression)
	 * @see nl.esi.poosl.PooslPackage#getReceiveStatement_ReceptionCondition()
	 * @model containment="true"
	 * @generated
	 */
	Expression getReceptionCondition();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.ReceiveStatement#getReceptionCondition <em>Reception Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reception Condition</em>' containment reference.
	 * @see #getReceptionCondition()
	 * @generated
	 */
	void setReceptionCondition(Expression value);

	/**
	 * Returns the value of the '<em><b>Post Communication Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Post Communication Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Post Communication Expression</em>' containment reference.
	 * @see #setPostCommunicationExpression(Expression)
	 * @see nl.esi.poosl.PooslPackage#getReceiveStatement_PostCommunicationExpression()
	 * @model containment="true"
	 * @generated
	 */
	Expression getPostCommunicationExpression();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.ReceiveStatement#getPostCommunicationExpression <em>Post Communication Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Post Communication Expression</em>' containment reference.
	 * @see #getPostCommunicationExpression()
	 * @generated
	 */
	void setPostCommunicationExpression(Expression value);

} // ReceiveStatement
