/**
 */
package org.eclipse.poosl;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abort Statement</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.poosl.AbortStatement#getNormalClause <em>Normal Clause</em>}</li>
 * <li>{@link org.eclipse.poosl.AbortStatement#getAbortingClause <em>Aborting Clause</em>}</li>
 * </ul>
 *
 * @see org.eclipse.poosl.PooslPackage#getAbortStatement()
 * @model
 * @generated
 */
public interface AbortStatement extends Statement {
    /**
     * Returns the value of the '<em><b>Normal Clause</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Normal Clause</em>' containment reference isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Normal Clause</em>' containment reference.
     * @see #setNormalClause(Statement)
     * @see org.eclipse.poosl.PooslPackage#getAbortStatement_NormalClause()
     * @model containment="true"
     * @generated
     */
    Statement getNormalClause();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.AbortStatement#getNormalClause <em>Normal Clause</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Normal Clause</em>' containment reference.
     * @see #getNormalClause()
     * @generated
     */
    void setNormalClause(Statement value);

    /**
     * Returns the value of the '<em><b>Aborting Clause</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Aborting Clause</em>' containment reference isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Aborting Clause</em>' containment reference.
     * @see #setAbortingClause(Statement)
     * @see org.eclipse.poosl.PooslPackage#getAbortStatement_AbortingClause()
     * @model containment="true"
     * @generated
     */
    Statement getAbortingClause();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.AbortStatement#getAbortingClause <em>Aborting Clause</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Aborting Clause</em>' containment reference.
     * @see #getAbortingClause()
     * @generated
     */
    void setAbortingClause(Statement value);

} // AbortStatement
