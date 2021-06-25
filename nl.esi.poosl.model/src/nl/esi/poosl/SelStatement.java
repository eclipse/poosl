/**
 */
package nl.esi.poosl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sel Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.SelStatement#getClauses <em>Clauses</em>}</li>
 * </ul>
 *
 * @see nl.esi.poosl.PooslPackage#getSelStatement()
 * @model
 * @generated
 */
public interface SelStatement extends Statement
{
	/**
	 * Returns the value of the '<em><b>Clauses</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.Statement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Clauses</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Clauses</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getSelStatement_Clauses()
	 * @model containment="true"
	 * @generated
	 */
	EList<Statement> getClauses();

} // SelStatement
