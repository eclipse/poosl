/**
 */
package org.eclipse.poosl;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Data Method Binary Operator</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.poosl.DataMethodBinaryOperator#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.eclipse.poosl.PooslPackage#getDataMethodBinaryOperator()
 * @model
 * @generated
 */
public interface DataMethodBinaryOperator extends DataMethod {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. The literals are from the enumeration
     * {@link org.eclipse.poosl.OperatorBinary}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see org.eclipse.poosl.OperatorBinary
     * @see #setName(OperatorBinary)
     * @see org.eclipse.poosl.PooslPackage#getDataMethodBinaryOperator_Name()
     * @model
     * @generated
     */
    OperatorBinary getName();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.DataMethodBinaryOperator#getName <em>Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see org.eclipse.poosl.OperatorBinary
     * @see #getName()
     * @generated
     */
    void setName(OperatorBinary value);

} // DataMethodBinaryOperator
