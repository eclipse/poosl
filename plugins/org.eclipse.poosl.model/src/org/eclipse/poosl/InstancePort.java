/**
 */
package org.eclipse.poosl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Instance Port</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.poosl.InstancePort#getInstance <em>Instance</em>}</li>
 * <li>{@link org.eclipse.poosl.InstancePort#getPort <em>Port</em>}</li>
 * </ul>
 *
 * @see org.eclipse.poosl.PooslPackage#getInstancePort()
 * @model
 * @generated
 */
public interface InstancePort extends EObject {
    /**
     * Returns the value of the '<em><b>Instance</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Instance</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Instance</em>' reference.
     * @see #setInstance(Instance)
     * @see org.eclipse.poosl.PooslPackage#getInstancePort_Instance()
     * @model
     * @generated
     */
    Instance getInstance();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.InstancePort#getInstance <em>Instance</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Instance</em>' reference.
     * @see #getInstance()
     * @generated
     */
    void setInstance(Instance value);

    /**
     * Returns the value of the '<em><b>Port</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Port</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Port</em>' containment reference.
     * @see #setPort(PortReference)
     * @see org.eclipse.poosl.PooslPackage#getInstancePort_Port()
     * @model containment="true"
     * @generated
     */
    PortReference getPort();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.InstancePort#getPort <em>Port</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Port</em>' containment reference.
     * @see #getPort()
     * @generated
     */
    void setPort(PortReference value);

} // InstancePort
