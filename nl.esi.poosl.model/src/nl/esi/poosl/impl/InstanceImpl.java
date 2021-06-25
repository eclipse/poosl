/**
 */
package nl.esi.poosl.impl;

import java.util.Collection;

import nl.esi.poosl.Instance;
import nl.esi.poosl.InstanceParameter;
import nl.esi.poosl.PooslPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.impl.InstanceImpl#getName <em>Name</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.InstanceImpl#getInstanceParameters <em>Instance Parameters</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.InstanceImpl#getClassDefinition <em>Class Definition</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InstanceImpl extends AnnotableImpl implements Instance
{
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInstanceParameters() <em>Instance Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstanceParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<InstanceParameter> instanceParameters;

	/**
	 * The default value of the '{@link #getClassDefinition() <em>Class Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassDefinition()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASS_DEFINITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassDefinition() <em>Class Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassDefinition()
	 * @generated
	 * @ordered
	 */
	protected String classDefinition = CLASS_DEFINITION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InstanceImpl()
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
		return PooslPackage.Literals.INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName()
	{
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName)
	{
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.INSTANCE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<InstanceParameter> getInstanceParameters()
	{
		if (instanceParameters == null) {
			instanceParameters = new EObjectContainmentEList<InstanceParameter>(InstanceParameter.class, this, PooslPackage.INSTANCE__INSTANCE_PARAMETERS);
		}
		return instanceParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getClassDefinition()
	{
		return classDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setClassDefinition(String newClassDefinition)
	{
		String oldClassDefinition = classDefinition;
		classDefinition = newClassDefinition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.INSTANCE__CLASS_DEFINITION, oldClassDefinition, classDefinition));
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
			case PooslPackage.INSTANCE__INSTANCE_PARAMETERS:
				return ((InternalEList<?>)getInstanceParameters()).basicRemove(otherEnd, msgs);
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
			case PooslPackage.INSTANCE__NAME:
				return getName();
			case PooslPackage.INSTANCE__INSTANCE_PARAMETERS:
				return getInstanceParameters();
			case PooslPackage.INSTANCE__CLASS_DEFINITION:
				return getClassDefinition();
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
			case PooslPackage.INSTANCE__NAME:
				setName((String)newValue);
				return;
			case PooslPackage.INSTANCE__INSTANCE_PARAMETERS:
				getInstanceParameters().clear();
				getInstanceParameters().addAll((Collection<? extends InstanceParameter>)newValue);
				return;
			case PooslPackage.INSTANCE__CLASS_DEFINITION:
				setClassDefinition((String)newValue);
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
			case PooslPackage.INSTANCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PooslPackage.INSTANCE__INSTANCE_PARAMETERS:
				getInstanceParameters().clear();
				return;
			case PooslPackage.INSTANCE__CLASS_DEFINITION:
				setClassDefinition(CLASS_DEFINITION_EDEFAULT);
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
			case PooslPackage.INSTANCE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PooslPackage.INSTANCE__INSTANCE_PARAMETERS:
				return instanceParameters != null && !instanceParameters.isEmpty();
			case PooslPackage.INSTANCE__CLASS_DEFINITION:
				return CLASS_DEFINITION_EDEFAULT == null ? classDefinition != null : !CLASS_DEFINITION_EDEFAULT.equals(classDefinition);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString()
	{
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", classDefinition: ");
		result.append(classDefinition);
		result.append(')');
		return result.toString();
	}

} //InstanceImpl
