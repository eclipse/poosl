/**
 */
package nl.esi.poosl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interrupt Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.InterruptStatement#getNormalClause <em>Normal Clause</em>}</li>
 *   <li>{@link nl.esi.poosl.InterruptStatement#getInterruptingClause <em>Interrupting Clause</em>}</li>
 * </ul>
 *
 * @see nl.esi.poosl.PooslPackage#getInterruptStatement()
 * @model
 * @generated
 */
public interface InterruptStatement extends Statement
{
	/**
	 * Returns the value of the '<em><b>Normal Clause</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Normal Clause</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Normal Clause</em>' containment reference.
	 * @see #setNormalClause(Statement)
	 * @see nl.esi.poosl.PooslPackage#getInterruptStatement_NormalClause()
	 * @model containment="true"
	 * @generated
	 */
	Statement getNormalClause();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.InterruptStatement#getNormalClause <em>Normal Clause</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Normal Clause</em>' containment reference.
	 * @see #getNormalClause()
	 * @generated
	 */
	void setNormalClause(Statement value);

	/**
	 * Returns the value of the '<em><b>Interrupting Clause</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interrupting Clause</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interrupting Clause</em>' containment reference.
	 * @see #setInterruptingClause(Statement)
	 * @see nl.esi.poosl.PooslPackage#getInterruptStatement_InterruptingClause()
	 * @model containment="true"
	 * @generated
	 */
	Statement getInterruptingClause();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.InterruptStatement#getInterruptingClause <em>Interrupting Clause</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interrupting Clause</em>' containment reference.
	 * @see #getInterruptingClause()
	 * @generated
	 */
	void setInterruptingClause(Statement value);

} // InterruptStatement
