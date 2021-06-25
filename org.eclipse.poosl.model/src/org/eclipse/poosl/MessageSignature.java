/**
 */
package org.eclipse.poosl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Message Signature</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.poosl.MessageSignature#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.poosl.MessageSignature#getParameters <em>Parameters</em>}</li>
 * <li>{@link org.eclipse.poosl.MessageSignature#getPort <em>Port</em>}</li>
 * </ul>
 *
 * @see org.eclipse.poosl.PooslPackage#getMessageSignature()
 * @model
 * @generated
 */
public interface MessageSignature extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.poosl.PooslPackage#getMessageSignature_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.MessageSignature#getName <em>Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Parameters</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.poosl.MessageParameter}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Parameters</em>' containment reference list.
     * @see org.eclipse.poosl.PooslPackage#getMessageSignature_Parameters()
     * @model containment="true"
     * @generated
     */
    EList<MessageParameter> getParameters();

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
     * @see org.eclipse.poosl.PooslPackage#getMessageSignature_Port()
     * @model containment="true"
     * @generated
     */
    PortReference getPort();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.MessageSignature#getPort <em>Port</em>}' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Port</em>' containment reference.
     * @see #getPort()
     * @generated
     */
    void setPort(PortReference value);

} // MessageSignature
