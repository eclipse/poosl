/**
 */
package org.eclipse.poosl;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Data Method Unary Operator</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.poosl.DataMethodUnaryOperator#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.eclipse.poosl.PooslPackage#getDataMethodUnaryOperator()
 * @model
 * @generated
 */
public interface DataMethodUnaryOperator extends DataMethod {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. The literals are from the enumeration
     * {@link org.eclipse.poosl.OperatorUnary}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see org.eclipse.poosl.OperatorUnary
     * @see #setName(OperatorUnary)
     * @see org.eclipse.poosl.PooslPackage#getDataMethodUnaryOperator_Name()
     * @model
     * @generated
     */
    OperatorUnary getName();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.DataMethodUnaryOperator#getName <em>Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see org.eclipse.poosl.OperatorUnary
     * @see #getName()
     * @generated
     */
    void setName(OperatorUnary value);

} // DataMethodUnaryOperator
