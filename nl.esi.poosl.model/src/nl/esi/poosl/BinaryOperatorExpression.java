/**
 */
package nl.esi.poosl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Binary Operator Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.BinaryOperatorExpression#getLeftOperand <em>Left Operand</em>}</li>
 *   <li>{@link nl.esi.poosl.BinaryOperatorExpression#getName <em>Name</em>}</li>
 *   <li>{@link nl.esi.poosl.BinaryOperatorExpression#getRightOperand <em>Right Operand</em>}</li>
 * </ul>
 *
 * @see nl.esi.poosl.PooslPackage#getBinaryOperatorExpression()
 * @model
 * @generated
 */
public interface BinaryOperatorExpression extends Expression
{
	/**
	 * Returns the value of the '<em><b>Left Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Operand</em>' containment reference.
	 * @see #setLeftOperand(Expression)
	 * @see nl.esi.poosl.PooslPackage#getBinaryOperatorExpression_LeftOperand()
	 * @model containment="true"
	 * @generated
	 */
	Expression getLeftOperand();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.BinaryOperatorExpression#getLeftOperand <em>Left Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Operand</em>' containment reference.
	 * @see #getLeftOperand()
	 * @generated
	 */
	void setLeftOperand(Expression value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The literals are from the enumeration {@link nl.esi.poosl.OperatorBinary}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see nl.esi.poosl.OperatorBinary
	 * @see #setName(OperatorBinary)
	 * @see nl.esi.poosl.PooslPackage#getBinaryOperatorExpression_Name()
	 * @model
	 * @generated
	 */
	OperatorBinary getName();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.BinaryOperatorExpression#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see nl.esi.poosl.OperatorBinary
	 * @see #getName()
	 * @generated
	 */
	void setName(OperatorBinary value);

	/**
	 * Returns the value of the '<em><b>Right Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Operand</em>' containment reference.
	 * @see #setRightOperand(Expression)
	 * @see nl.esi.poosl.PooslPackage#getBinaryOperatorExpression_RightOperand()
	 * @model containment="true"
	 * @generated
	 */
	Expression getRightOperand();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.BinaryOperatorExpression#getRightOperand <em>Right Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Operand</em>' containment reference.
	 * @see #getRightOperand()
	 * @generated
	 */
	void setRightOperand(Expression value);

} // BinaryOperatorExpression
