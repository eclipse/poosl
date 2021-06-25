/**
 */
package nl.esi.poosl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Poosl</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.Poosl#getImports <em>Imports</em>}</li>
 *   <li>{@link nl.esi.poosl.Poosl#getDataClasses <em>Data Classes</em>}</li>
 *   <li>{@link nl.esi.poosl.Poosl#getProcessClasses <em>Process Classes</em>}</li>
 *   <li>{@link nl.esi.poosl.Poosl#getClusterClasses <em>Cluster Classes</em>}</li>
 *   <li>{@link nl.esi.poosl.Poosl#getImportLibs <em>Import Libs</em>}</li>
 * </ul>
 *
 * @see nl.esi.poosl.PooslPackage#getPoosl()
 * @model
 * @generated
 */
public interface Poosl extends EObject
{
	/**
	 * Returns the value of the '<em><b>Imports</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.Import}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imports</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getPoosl_Imports()
	 * @model containment="true"
	 * @generated
	 */
	EList<Import> getImports();

	/**
	 * Returns the value of the '<em><b>Data Classes</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.DataClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Classes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Classes</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getPoosl_DataClasses()
	 * @model containment="true"
	 * @generated
	 */
	EList<DataClass> getDataClasses();

	/**
	 * Returns the value of the '<em><b>Process Classes</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.ProcessClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process Classes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process Classes</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getPoosl_ProcessClasses()
	 * @model containment="true"
	 * @generated
	 */
	EList<ProcessClass> getProcessClasses();

	/**
	 * Returns the value of the '<em><b>Cluster Classes</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.ClusterClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cluster Classes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cluster Classes</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getPoosl_ClusterClasses()
	 * @model containment="true"
	 * @generated
	 */
	EList<ClusterClass> getClusterClasses();

	/**
	 * Returns the value of the '<em><b>Import Libs</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.Import}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Import Libs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Import Libs</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getPoosl_ImportLibs()
	 * @model containment="true"
	 * @generated
	 */
	EList<Import> getImportLibs();

} // Poosl
