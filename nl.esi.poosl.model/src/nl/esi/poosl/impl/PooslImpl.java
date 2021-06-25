/**
 */
package nl.esi.poosl.impl;

import java.util.Collection;

import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.DataClass;
import nl.esi.poosl.Import;
import nl.esi.poosl.Poosl;
import nl.esi.poosl.PooslPackage;
import nl.esi.poosl.ProcessClass;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Poosl</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.impl.PooslImpl#getImports <em>Imports</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.PooslImpl#getDataClasses <em>Data Classes</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.PooslImpl#getProcessClasses <em>Process Classes</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.PooslImpl#getClusterClasses <em>Cluster Classes</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.PooslImpl#getImportLibs <em>Import Libs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PooslImpl extends EObjectImpl implements Poosl
{
	/**
	 * The cached value of the '{@link #getImports() <em>Imports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImports()
	 * @generated
	 * @ordered
	 */
	protected EList<Import> imports;

	/**
	 * The cached value of the '{@link #getDataClasses() <em>Data Classes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<DataClass> dataClasses;

	/**
	 * The cached value of the '{@link #getProcessClasses() <em>Process Classes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<ProcessClass> processClasses;

	/**
	 * The cached value of the '{@link #getClusterClasses() <em>Cluster Classes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClusterClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<ClusterClass> clusterClasses;

	/**
	 * The cached value of the '{@link #getImportLibs() <em>Import Libs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportLibs()
	 * @generated
	 * @ordered
	 */
	protected EList<Import> importLibs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PooslImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PooslPackage.Literals.POOSL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Import> getImports()
	{
		if (imports == null) {
			imports = new EObjectContainmentEList<Import>(Import.class, this, PooslPackage.POOSL__IMPORTS);
		}
		return imports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<DataClass> getDataClasses()
	{
		if (dataClasses == null) {
			dataClasses = new EObjectContainmentEList<DataClass>(DataClass.class, this, PooslPackage.POOSL__DATA_CLASSES);
		}
		return dataClasses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ProcessClass> getProcessClasses()
	{
		if (processClasses == null) {
			processClasses = new EObjectContainmentEList<ProcessClass>(ProcessClass.class, this, PooslPackage.POOSL__PROCESS_CLASSES);
		}
		return processClasses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ClusterClass> getClusterClasses()
	{
		if (clusterClasses == null) {
			clusterClasses = new EObjectContainmentEList<ClusterClass>(ClusterClass.class, this, PooslPackage.POOSL__CLUSTER_CLASSES);
		}
		return clusterClasses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Import> getImportLibs()
	{
		if (importLibs == null) {
			importLibs = new EObjectContainmentEList<Import>(Import.class, this, PooslPackage.POOSL__IMPORT_LIBS);
		}
		return importLibs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID) {
			case PooslPackage.POOSL__IMPORTS:
				return ((InternalEList<?>)getImports()).basicRemove(otherEnd, msgs);
			case PooslPackage.POOSL__DATA_CLASSES:
				return ((InternalEList<?>)getDataClasses()).basicRemove(otherEnd, msgs);
			case PooslPackage.POOSL__PROCESS_CLASSES:
				return ((InternalEList<?>)getProcessClasses()).basicRemove(otherEnd, msgs);
			case PooslPackage.POOSL__CLUSTER_CLASSES:
				return ((InternalEList<?>)getClusterClasses()).basicRemove(otherEnd, msgs);
			case PooslPackage.POOSL__IMPORT_LIBS:
				return ((InternalEList<?>)getImportLibs()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID) {
			case PooslPackage.POOSL__IMPORTS:
				return getImports();
			case PooslPackage.POOSL__DATA_CLASSES:
				return getDataClasses();
			case PooslPackage.POOSL__PROCESS_CLASSES:
				return getProcessClasses();
			case PooslPackage.POOSL__CLUSTER_CLASSES:
				return getClusterClasses();
			case PooslPackage.POOSL__IMPORT_LIBS:
				return getImportLibs();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID) {
			case PooslPackage.POOSL__IMPORTS:
				getImports().clear();
				getImports().addAll((Collection<? extends Import>)newValue);
				return;
			case PooslPackage.POOSL__DATA_CLASSES:
				getDataClasses().clear();
				getDataClasses().addAll((Collection<? extends DataClass>)newValue);
				return;
			case PooslPackage.POOSL__PROCESS_CLASSES:
				getProcessClasses().clear();
				getProcessClasses().addAll((Collection<? extends ProcessClass>)newValue);
				return;
			case PooslPackage.POOSL__CLUSTER_CLASSES:
				getClusterClasses().clear();
				getClusterClasses().addAll((Collection<? extends ClusterClass>)newValue);
				return;
			case PooslPackage.POOSL__IMPORT_LIBS:
				getImportLibs().clear();
				getImportLibs().addAll((Collection<? extends Import>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID)
	{
		switch (featureID) {
			case PooslPackage.POOSL__IMPORTS:
				getImports().clear();
				return;
			case PooslPackage.POOSL__DATA_CLASSES:
				getDataClasses().clear();
				return;
			case PooslPackage.POOSL__PROCESS_CLASSES:
				getProcessClasses().clear();
				return;
			case PooslPackage.POOSL__CLUSTER_CLASSES:
				getClusterClasses().clear();
				return;
			case PooslPackage.POOSL__IMPORT_LIBS:
				getImportLibs().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch (featureID) {
			case PooslPackage.POOSL__IMPORTS:
				return imports != null && !imports.isEmpty();
			case PooslPackage.POOSL__DATA_CLASSES:
				return dataClasses != null && !dataClasses.isEmpty();
			case PooslPackage.POOSL__PROCESS_CLASSES:
				return processClasses != null && !processClasses.isEmpty();
			case PooslPackage.POOSL__CLUSTER_CLASSES:
				return clusterClasses != null && !clusterClasses.isEmpty();
			case PooslPackage.POOSL__IMPORT_LIBS:
				return importLibs != null && !importLibs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //PooslImpl
