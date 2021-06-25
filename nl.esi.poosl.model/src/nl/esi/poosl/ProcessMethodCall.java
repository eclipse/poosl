/**
 */
package nl.esi.poosl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Process Method Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.ProcessMethodCall#getInputArguments <em>Input Arguments</em>}</li>
 *   <li>{@link nl.esi.poosl.ProcessMethodCall#getOutputVariables <em>Output Variables</em>}</li>
 *   <li>{@link nl.esi.poosl.ProcessMethodCall#getMethod <em>Method</em>}</li>
 * </ul>
 *
 * @see nl.esi.poosl.PooslPackage#getProcessMethodCall()
 * @model
 * @generated
 */
public interface ProcessMethodCall extends Statement
{
	/**
	 * Returns the value of the '<em><b>Input Arguments</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Arguments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Arguments</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getProcessMethodCall_InputArguments()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getInputArguments();

	/**
	 * Returns the value of the '<em><b>Output Variables</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.OutputVariable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Variables</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Variables</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getProcessMethodCall_OutputVariables()
	 * @model containment="true"
	 * @generated
	 */
	EList<OutputVariable> getOutputVariables();

	/**
	 * Returns the value of the '<em><b>Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method</em>' attribute.
	 * @see #setMethod(String)
	 * @see nl.esi.poosl.PooslPackage#getProcessMethodCall_Method()
	 * @model
	 * @generated
	 */
	String getMethod();

	/**
	 * Sets the value of the '{@link nl.esi.poosl.ProcessMethodCall#getMethod <em>Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method</em>' attribute.
	 * @see #getMethod()
	 * @generated
	 */
	void setMethod(String value);

} // ProcessMethodCall
