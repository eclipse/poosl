/**
 */
package nl.esi.poosl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.DataClass#getName <em>Name</em>}</li>
 *   <li>{@link nl.esi.poosl.DataClass#isNative <em>Native</em>}</li>
 *   <li>{@link nl.esi.poosl.DataClass#getInstanceVariables <em>Instance Variables</em>}</li>
 *   <li>{@link nl.esi.poosl.DataClass#getDataMethodsNamed <em>Data Methods Named</em>}</li>
 *   <li>{@link nl.esi.poosl.DataClass#getDataMethodsUnaryOperator <em>Data Methods Unary Operator</em>}</li>
 *   <li>{@link nl.esi.poosl.DataClass#getDataMethodsBinaryOperator <em>Data Methods Binary Operator</em>}</li>
 *   <li>{@link nl.esi.poosl.DataClass#getSuperClass <em>Super Class</em>}</li>
 * </ul>
 *
 * @see nl.esi.poosl.PooslPackage#getDataClass()
 * @model
 * @generated
 */
public interface DataClass extends Annotable
{
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see nl.esi.poosl.PooslPackage#getDataClass_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.DataClass#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Native</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Native</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Native</em>' attribute.
	 * @see #setNative(boolean)
	 * @see nl.esi.poosl.PooslPackage#getDataClass_Native()
	 * @model
	 * @generated
	 */
	boolean isNative();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.DataClass#isNative <em>Native</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Native</em>' attribute.
	 * @see #isNative()
	 * @generated
	 */
	void setNative(boolean value);

	/**
	 * Returns the value of the '<em><b>Instance Variables</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.Declaration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instance Variables</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instance Variables</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getDataClass_InstanceVariables()
	 * @model containment="true"
	 * @generated
	 */
	EList<Declaration> getInstanceVariables();

	/**
	 * Returns the value of the '<em><b>Data Methods Named</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.DataMethodNamed}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Methods Named</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Methods Named</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getDataClass_DataMethodsNamed()
	 * @model containment="true"
	 * @generated
	 */
	EList<DataMethodNamed> getDataMethodsNamed();

	/**
	 * Returns the value of the '<em><b>Data Methods Unary Operator</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.DataMethodUnaryOperator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Methods Unary Operator</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Methods Unary Operator</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getDataClass_DataMethodsUnaryOperator()
	 * @model containment="true"
	 * @generated
	 */
	EList<DataMethodUnaryOperator> getDataMethodsUnaryOperator();

	/**
	 * Returns the value of the '<em><b>Data Methods Binary Operator</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.DataMethodBinaryOperator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Methods Binary Operator</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Methods Binary Operator</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getDataClass_DataMethodsBinaryOperator()
	 * @model containment="true"
	 * @generated
	 */
	EList<DataMethodBinaryOperator> getDataMethodsBinaryOperator();

	/**
	 * Returns the value of the '<em><b>Super Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super Class</em>' attribute.
	 * @see #setSuperClass(String)
	 * @see nl.esi.poosl.PooslPackage#getDataClass_SuperClass()
	 * @model
	 * @generated
	 */
	String getSuperClass();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.DataClass#getSuperClass <em>Super Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Super Class</em>' attribute.
	 * @see #getSuperClass()
	 * @generated
	 */
	void setSuperClass(String value);

} // DataClass
