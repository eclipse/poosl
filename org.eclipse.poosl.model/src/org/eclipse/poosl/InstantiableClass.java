/**
 */
package org.eclipse.poosl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Instantiable Class</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.poosl.InstantiableClass#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.poosl.InstantiableClass#getParameters <em>Parameters</em>}</li>
 * <li>{@link org.eclipse.poosl.InstantiableClass#getPorts <em>Ports</em>}</li>
 * </ul>
 *
 * @see org.eclipse.poosl.PooslPackage#getInstantiableClass()
 * @model
 * @generated
 */
public interface InstantiableClass extends Annotable {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.poosl.PooslPackage#getInstantiableClass_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.InstantiableClass#getName <em>Name</em>}' attribute. <!--
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
     * {@link org.eclipse.poosl.Declaration}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Parameters</em>' containment reference list.
     * @see org.eclipse.poosl.PooslPackage#getInstantiableClass_Parameters()
     * @model containment="true"
     * @generated
     */
    EList<Declaration> getParameters();

    /**
     * Returns the value of the '<em><b>Ports</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.poosl.Port}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ports</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Ports</em>' containment reference list.
     * @see org.eclipse.poosl.PooslPackage#getInstantiableClass_Ports()
     * @model containment="true"
     * @generated
     */
    EList<Port> getPorts();

} // InstantiableClass
