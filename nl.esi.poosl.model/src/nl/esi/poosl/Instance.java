/**
 */
package nl.esi.poosl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.Instance#getName <em>Name</em>}</li>
 *   <li>{@link nl.esi.poosl.Instance#getInstanceParameters <em>Instance Parameters</em>}</li>
 *   <li>{@link nl.esi.poosl.Instance#getClassDefinition <em>Class Definition</em>}</li>
 * </ul>
 *
 * @see nl.esi.poosl.PooslPackage#getInstance()
 * @model
 * @generated
 */
public interface Instance extends Annotable
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
	 * @see nl.esi.poosl.PooslPackage#getInstance_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.Instance#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Instance Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.InstanceParameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instance Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instance Parameters</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getInstance_InstanceParameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<InstanceParameter> getInstanceParameters();

	/**
	 * Returns the value of the '<em><b>Class Definition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Definition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Definition</em>' attribute.
	 * @see #setClassDefinition(String)
	 * @see nl.esi.poosl.PooslPackage#getInstance_ClassDefinition()
	 * @model
	 * @generated
	 */
	String getClassDefinition();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.Instance#getClassDefinition <em>Class Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Definition</em>' attribute.
	 * @see #getClassDefinition()
	 * @generated
	 */
	void setClassDefinition(String value);

} // Instance
