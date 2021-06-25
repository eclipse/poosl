/**
 */
package nl.esi.poosl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>If Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.IfStatement#getCondition <em>Condition</em>}</li>
 *   <li>{@link nl.esi.poosl.IfStatement#getThenClause <em>Then Clause</em>}</li>
 *   <li>{@link nl.esi.poosl.IfStatement#getElseClause <em>Else Clause</em>}</li>
 * </ul>
 *
 * @see nl.esi.poosl.PooslPackage#getIfStatement()
 * @model
 * @generated
 */
public interface IfStatement extends Statement
{
	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference.
	 * @see #setCondition(Expression)
	 * @see nl.esi.poosl.PooslPackage#getIfStatement_Condition()
	 * @model containment="true"
	 * @generated
	 */
	Expression getCondition();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.IfStatement#getCondition <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' containment reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(Expression value);

	/**
	 * Returns the value of the '<em><b>Then Clause</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Then Clause</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Then Clause</em>' containment reference.
	 * @see #setThenClause(Statement)
	 * @see nl.esi.poosl.PooslPackage#getIfStatement_ThenClause()
	 * @model containment="true"
	 * @generated
	 */
	Statement getThenClause();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.IfStatement#getThenClause <em>Then Clause</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Then Clause</em>' containment reference.
	 * @see #getThenClause()
	 * @generated
	 */
	void setThenClause(Statement value);

	/**
	 * Returns the value of the '<em><b>Else Clause</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Else Clause</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Else Clause</em>' containment reference.
	 * @see #setElseClause(Statement)
	 * @see nl.esi.poosl.PooslPackage#getIfStatement_ElseClause()
	 * @model containment="true"
	 * @generated
	 */
	Statement getElseClause();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.IfStatement#getElseClause <em>Else Clause</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Else Clause</em>' containment reference.
	 * @see #getElseClause()
	 * @generated
	 */
	void setElseClause(Statement value);

} // IfStatement
