/**
 */
package nl.esi.poosl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Statement Sequence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.StatementSequence#getStatements <em>Statements</em>}</li>
 * </ul>
 *
 * @see nl.esi.poosl.PooslPackage#getStatementSequence()
 * @model
 * @generated
 */
public interface StatementSequence extends Statement
{
	/**
	 * Returns the value of the '<em><b>Statements</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.Statement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Statements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Statements</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getStatementSequence_Statements()
	 * @model containment="true"
	 * @generated
	 */
	EList<Statement> getStatements();

} // StatementSequence
