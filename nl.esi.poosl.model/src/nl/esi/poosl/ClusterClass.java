/**
 */
package nl.esi.poosl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cluster Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.ClusterClass#getChannels <em>Channels</em>}</li>
 *   <li>{@link nl.esi.poosl.ClusterClass#getInstances <em>Instances</em>}</li>
 * </ul>
 *
 * @see nl.esi.poosl.PooslPackage#getClusterClass()
 * @model
 * @generated
 */
public interface ClusterClass extends InstantiableClass
{
	/**
	 * Returns the value of the '<em><b>Channels</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.Channel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Channels</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Channels</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getClusterClass_Channels()
	 * @model containment="true"
	 * @generated
	 */
	EList<Channel> getChannels();

	/**
	 * Returns the value of the '<em><b>Instances</b></em>' containment reference list.
	 * The list contents are of type {@link nl.esi.poosl.Instance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instances</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instances</em>' containment reference list.
	 * @see nl.esi.poosl.PooslPackage#getClusterClass_Instances()
	 * @model containment="true"
	 * @generated
	 */
	EList<Instance> getInstances();

} // ClusterClass
