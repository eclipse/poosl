/**
 */
package org.eclipse.poosl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Data Method Call Expression</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.poosl.DataMethodCallExpression#getTarget <em>Target</em>}</li>
 * <li>{@link org.eclipse.poosl.DataMethodCallExpression#isOnSuperClass <em>On Super Class</em>}</li>
 * <li>{@link org.eclipse.poosl.DataMethodCallExpression#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.poosl.DataMethodCallExpression#getArguments <em>Arguments</em>}</li>
 * </ul>
 *
 * @see org.eclipse.poosl.PooslPackage#getDataMethodCallExpression()
 * @model
 * @generated
 */
public interface DataMethodCallExpression extends Expression {
    /**
     * Returns the value of the '<em><b>Target</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Target</em>' containment reference.
     * @see #setTarget(Expression)
     * @see org.eclipse.poosl.PooslPackage#getDataMethodCallExpression_Target()
     * @model containment="true"
     * @generated
     */
    Expression getTarget();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.DataMethodCallExpression#getTarget <em>Target</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Target</em>' containment reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(Expression value);

    /**
     * Returns the value of the '<em><b>On Super Class</b></em>' attribute. The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>On Super Class</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>On Super Class</em>' attribute.
     * @see #setOnSuperClass(boolean)
     * @see org.eclipse.poosl.PooslPackage#getDataMethodCallExpression_OnSuperClass()
     * @model default="false"
     * @generated
     */
    boolean isOnSuperClass();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.DataMethodCallExpression#isOnSuperClass <em>On Super Class</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>On Super Class</em>' attribute.
     * @see #isOnSuperClass()
     * @generated
     */
    void setOnSuperClass(boolean value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.poosl.PooslPackage#getDataMethodCallExpression_Name()
     * @model default=""
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.poosl.DataMethodCallExpression#getName <em>Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Arguments</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.poosl.Expression}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Arguments</em>' containment reference list isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Arguments</em>' containment reference list.
     * @see org.eclipse.poosl.PooslPackage#getDataMethodCallExpression_Arguments()
     * @model containment="true"
     * @generated
     */
    EList<Expression> getArguments();

} // DataMethodCallExpression
