/**
 */
package nl.esi.poosl;

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
 *   <li>{@link nl.esi.poosl.Channel#getExternalPort <em>External Port</em>}</li>
 *   <li>{@link nl.esi.poosl.Channel#getInstancePorts <em>Instance Ports</em>}</li>
 * </ul>
 *
 * @see nl.esi.poosl.PooslPackage#getChannel()
 * @model
 * @generated
 */
public interface Channel extends Annotable
{
	/**
	 * Returns the value of the '<em><b>External Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Port</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Port</em>' reference.
	 * @see #setExternalPort(Port)
	 * @see nl.esi.poosl.PooslPackage#getChannel_ExternalPort()
	 * @model
	 * @generated
	 */
	Port getExternalPort();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.Channel#getExternalPort <em>External Port</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Port</em>' reference.
	 * @see #getExternalPort()
	 * @generated
	 */
	void setExternalPort(Port value);

	/**
	 * Returns the value of the '<em><b>Instance Ports</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.InstancePort}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instance Ports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instance Ports</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getChannel_InstancePorts()
	 * @model containment="true"
	 * @generated
	 */
	EList<InstancePort> getInstancePorts();

} // Channel
