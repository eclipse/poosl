/**
 */
package nl.esi.poosl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Send Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.SendStatement#getPortDescriptor <em>Port Descriptor</em>}</li>
 *   <li>{@link nl.esi.poosl.SendStatement#getName <em>Name</em>}</li>
 *   <li>{@link nl.esi.poosl.SendStatement#getArguments <em>Arguments</em>}</li>
 *   <li>{@link nl.esi.poosl.SendStatement#getPostCommunicationExpression <em>Post Communication Expression</em>}</li>
 * </ul>
 *
 * @see nl.esi.poosl.PooslPackage#getSendStatement()
 * @model
 * @generated
 */
public interface SendStatement extends Statement
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
	 * @see nl.esi.poosl.PooslPackage#getSendStatement_PortDescriptor()
	 * @model containment="true"
	 * @generated
	 */
	PortDescriptor getPortDescriptor();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.SendStatement#getPortDescriptor <em>Port Descriptor</em>}' containment reference.
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
	 * @see nl.esi.poosl.PooslPackage#getSendStatement_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.SendStatement#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Arguments</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Arguments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Arguments</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getSendStatement_Arguments()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getArguments();

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
	 * @see nl.esi.poosl.PooslPackage#getSendStatement_PostCommunicationExpression()
	 * @model containment="true"
	 * @generated
	 */
	Expression getPostCommunicationExpression();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.SendStatement#getPostCommunicationExpression <em>Post Communication Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Post Communication Expression</em>' containment reference.
	 * @see #getPostCommunicationExpression()
	 * @generated
	 */
	void setPostCommunicationExpression(Expression value);

} // SendStatement
