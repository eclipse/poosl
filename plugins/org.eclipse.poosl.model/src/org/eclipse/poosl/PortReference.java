/**
 */
package org.eclipse.poosl;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Port Reference</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.poosl.PortReference#getPort <em>Port</em>}</li>
 * </ul>
 *
 * @see org.eclipse.poosl.PooslPackage#getPortReference()
 * @model
 * @generated
 */
public interface PortReference extends PortDescriptor {
    /**
     * Returns the value of the '<em><b>Port</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Port</em>' attribute isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Port</em>' attribute.
     * @see #setPort(String)
     * @see org.eclipse.poosl.PooslPackage#getPortReference_Port()
     * @model
     * @generated
     */
    String getPort();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.PortReference#getPort <em>Port</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Port</em>' attribute.
     * @see #getPort()
     * @generated
     */
    void setPort(String value);

} // PortReference
