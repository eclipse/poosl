/**
 */
package nl.esi.poosl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Switch Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.SwitchStatement#getExpression <em>Expression</em>}</li>
 *   <li>{@link nl.esi.poosl.SwitchStatement#getCases <em>Cases</em>}</li>
 *   <li>{@link nl.esi.poosl.SwitchStatement#getDefaultBody <em>Default Body</em>}</li>
 * </ul>
 *
 * @see nl.esi.poosl.PooslPackage#getSwitchStatement()
 * @model
 * @generated
 */
public interface SwitchStatement extends Statement
{
	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(Expression)
	 * @see nl.esi.poosl.PooslPackage#getSwitchStatement_Expression()
	 * @model containment="true"
	 * @generated
	 */
	Expression getExpression();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.SwitchStatement#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(Expression value);

	/**
	 * Returns the value of the '<em><b>Cases</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.SwitchStatementCase}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cases</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cases</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getSwitchStatement_Cases()
	 * @model containment="true"
	 * @generated
	 */
	EList<SwitchStatementCase> getCases();

	/**
	 * Returns the value of the '<em><b>Default Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Body</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Body</em>' containment reference.
	 * @see #setDefaultBody(Statement)
	 * @see nl.esi.poosl.PooslPackage#getSwitchStatement_DefaultBody()
	 * @model containment="true"
	 * @generated
	 */
	Statement getDefaultBody();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.SwitchStatement#getDefaultBody <em>Default Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Body</em>' containment reference.
	 * @see #getDefaultBody()
	 * @generated
	 */
	void setDefaultBody(Statement value);

} // SwitchStatement
