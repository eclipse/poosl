/**
 */
package nl.esi.poosl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>New Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.NewExpression#getDataClass <em>Data Class</em>}</li>
 * </ul>
 *
 * @see nl.esi.poosl.PooslPackage#getNewExpression()
 * @model
 * @generated
 */
public interface NewExpression extends Expression
{
	/**
	 * Returns the value of the '<em><b>Data Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Class</em>' attribute.
	 * @see #setDataClass(String)
	 * @see nl.esi.poosl.PooslPackage#getNewExpression_DataClass()
	 * @model
	 * @generated
	 */
	String getDataClass();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.NewExpression#getDataClass <em>Data Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Class</em>' attribute.
	 * @see #getDataClass()
	 * @generated
	 */
	void setDataClass(String value);

} // NewExpression
