/**
 */
package org.eclipse.poosl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Expression Sequence</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.poosl.ExpressionSequence#getExpressions <em>Expressions</em>}</li>
 * </ul>
 *
 * @see org.eclipse.poosl.PooslPackage#getExpressionSequence()
 * @model
 * @generated
 */
public interface ExpressionSequence extends Expression {
    /**
     * Returns the value of the '<em><b>Expressions</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.poosl.Expression}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Expressions</em>' containment reference list isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Expressions</em>' containment reference list.
     * @see org.eclipse.poosl.PooslPackage#getExpressionSequence_Expressions()
     * @model containment="true"
     * @generated
     */
    EList<Expression> getExpressions();

} // ExpressionSequence
