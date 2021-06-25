/**
 */
package nl.esi.poosl.impl;

import java.util.Collection;

import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethodBinaryOperator;
import nl.esi.poosl.DataMethodNamed;
import nl.esi.poosl.DataMethodUnaryOperator;
import nl.esi.poosl.Declaration;
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
 * An implementation of the model object '<em><b>Data Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.impl.DataClassImpl#getName <em>Name</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.DataClassImpl#isNative <em>Native</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.DataClassImpl#getInstanceVariables <em>Instance Variables</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.DataClassImpl#getDataMethodsNamed <em>Data Methods Named</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.DataClassImpl#getDataMethodsUnaryOperator <em>Data Methods Unary Operator</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.DataClassImpl#getDataMethodsBinaryOperator <em>Data Methods Binary Operator</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.DataClassImpl#getSuperClass <em>Super Class</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DataClassImpl extends AnnotableImpl implements DataClass
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
	 * The default value of the '{@link #isNative() <em>Native</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNative()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NATIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNative() <em>Native</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNative()
	 * @generated
	 * @ordered
	 */
	protected boolean native_ = NATIVE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInstanceVariables() <em>Instance Variables</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstanceVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<Declaration> instanceVariables;

	/**
	 * The cached value of the '{@link #getDataMethodsNamed() <em>Data Methods Named</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataMethodsNamed()
	 * @generated
	 * @ordered
	 */
	protected EList<DataMethodNamed> dataMethodsNamed;

	/**
	 * The cached value of the '{@link #getDataMethodsUnaryOperator() <em>Data Methods Unary Operator</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataMethodsUnaryOperator()
	 * @generated
	 * @ordered
	 */
	protected EList<DataMethodUnaryOperator> dataMethodsUnaryOperator;

	/**
	 * The cached value of the '{@link #getDataMethodsBinaryOperator() <em>Data Methods Binary Operator</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataMethodsBinaryOperator()
	 * @generated
	 * @ordered
	 */
	protected EList<DataMethodBinaryOperator> dataMethodsBinaryOperator;

	/**
	 * The default value of the '{@link #getSuperClass() <em>Super Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperClass()
	 * @generated
	 * @ordered
	 */
	protected static final String SUPER_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSuperClass() <em>Super Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperClass()
	 * @generated
	 * @ordered
	 */
	protected String superClass = SUPER_CLASS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataClassImpl()
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
		return PooslPackage.Literals.DATA_CLASS;
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
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.DATA_CLASS__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isNative()
	{
		return native_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNative(boolean newNative)
	{
		boolean oldNative = native_;
		native_ = newNative;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.DATA_CLASS__NATIVE, oldNative, native_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Declaration> getInstanceVariables()
	{
		if (instanceVariables == null) {
			instanceVariables = new EObjectContainmentEList<Declaration>(Declaration.class, this, PooslPackage.DATA_CLASS__INSTANCE_VARIABLES);
		}
		return instanceVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<DataMethodNamed> getDataMethodsNamed()
	{
		if (dataMethodsNamed == null) {
			dataMethodsNamed = new EObjectContainmentEList<DataMethodNamed>(DataMethodNamed.class, this, PooslPackage.DATA_CLASS__DATA_METHODS_NAMED);
		}
		return dataMethodsNamed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<DataMethodUnaryOperator> getDataMethodsUnaryOperator()
	{
		if (dataMethodsUnaryOperator == null) {
			dataMethodsUnaryOperator = new EObjectContainmentEList<DataMethodUnaryOperator>(DataMethodUnaryOperator.class, this, PooslPackage.DATA_CLASS__DATA_METHODS_UNARY_OPERATOR);
		}
		return dataMethodsUnaryOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<DataMethodBinaryOperator> getDataMethodsBinaryOperator()
	{
		if (dataMethodsBinaryOperator == null) {
			dataMethodsBinaryOperator = new EObjectContainmentEList<DataMethodBinaryOperator>(DataMethodBinaryOperator.class, this, PooslPackage.DATA_CLASS__DATA_METHODS_BINARY_OPERATOR);
		}
		return dataMethodsBinaryOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSuperClass()
	{
		return superClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSuperClass(String newSuperClass)
	{
		String oldSuperClass = superClass;
		superClass = newSuperClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.DATA_CLASS__SUPER_CLASS, oldSuperClass, superClass));
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
			case PooslPackage.DATA_CLASS__INSTANCE_VARIABLES:
				return ((InternalEList<?>)getInstanceVariables()).basicRemove(otherEnd, msgs);
			case PooslPackage.DATA_CLASS__DATA_METHODS_NAMED:
				return ((InternalEList<?>)getDataMethodsNamed()).basicRemove(otherEnd, msgs);
			case PooslPackage.DATA_CLASS__DATA_METHODS_UNARY_OPERATOR:
				return ((InternalEList<?>)getDataMethodsUnaryOperator()).basicRemove(otherEnd, msgs);
			case PooslPackage.DATA_CLASS__DATA_METHODS_BINARY_OPERATOR:
				return ((InternalEList<?>)getDataMethodsBinaryOperator()).basicRemove(otherEnd, msgs);
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
			case PooslPackage.DATA_CLASS__NAME:
				return getName();
			case PooslPackage.DATA_CLASS__NATIVE:
				return isNative();
			case PooslPackage.DATA_CLASS__INSTANCE_VARIABLES:
				return getInstanceVariables();
			case PooslPackage.DATA_CLASS__DATA_METHODS_NAMED:
				return getDataMethodsNamed();
			case PooslPackage.DATA_CLASS__DATA_METHODS_UNARY_OPERATOR:
				return getDataMethodsUnaryOperator();
			case PooslPackage.DATA_CLASS__DATA_METHODS_BINARY_OPERATOR:
				return getDataMethodsBinaryOperator();
			case PooslPackage.DATA_CLASS__SUPER_CLASS:
				return getSuperClass();
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
			case PooslPackage.DATA_CLASS__NAME:
				setName((String)newValue);
				return;
			case PooslPackage.DATA_CLASS__NATIVE:
				setNative((Boolean)newValue);
				return;
			case PooslPackage.DATA_CLASS__INSTANCE_VARIABLES:
				getInstanceVariables().clear();
				getInstanceVariables().addAll((Collection<? extends Declaration>)newValue);
				return;
			case PooslPackage.DATA_CLASS__DATA_METHODS_NAMED:
				getDataMethodsNamed().clear();
				getDataMethodsNamed().addAll((Collection<? extends DataMethodNamed>)newValue);
				return;
			case PooslPackage.DATA_CLASS__DATA_METHODS_UNARY_OPERATOR:
				getDataMethodsUnaryOperator().clear();
				getDataMethodsUnaryOperator().addAll((Collection<? extends DataMethodUnaryOperator>)newValue);
				return;
			case PooslPackage.DATA_CLASS__DATA_METHODS_BINARY_OPERATOR:
				getDataMethodsBinaryOperator().clear();
				getDataMethodsBinaryOperator().addAll((Collection<? extends DataMethodBinaryOperator>)newValue);
				return;
			case PooslPackage.DATA_CLASS__SUPER_CLASS:
				setSuperClass((String)newValue);
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
			case PooslPackage.DATA_CLASS__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PooslPackage.DATA_CLASS__NATIVE:
				setNative(NATIVE_EDEFAULT);
				return;
			case PooslPackage.DATA_CLASS__INSTANCE_VARIABLES:
				getInstanceVariables().clear();
				return;
			case PooslPackage.DATA_CLASS__DATA_METHODS_NAMED:
				getDataMethodsNamed().clear();
				return;
			case PooslPackage.DATA_CLASS__DATA_METHODS_UNARY_OPERATOR:
				getDataMethodsUnaryOperator().clear();
				return;
			case PooslPackage.DATA_CLASS__DATA_METHODS_BINARY_OPERATOR:
				getDataMethodsBinaryOperator().clear();
				return;
			case PooslPackage.DATA_CLASS__SUPER_CLASS:
				setSuperClass(SUPER_CLASS_EDEFAULT);
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
			case PooslPackage.DATA_CLASS__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PooslPackage.DATA_CLASS__NATIVE:
				return native_ != NATIVE_EDEFAULT;
			case PooslPackage.DATA_CLASS__INSTANCE_VARIABLES:
				return instanceVariables != null && !instanceVariables.isEmpty();
			case PooslPackage.DATA_CLASS__DATA_METHODS_NAMED:
				return dataMethodsNamed != null && !dataMethodsNamed.isEmpty();
			case PooslPackage.DATA_CLASS__DATA_METHODS_UNARY_OPERATOR:
				return dataMethodsUnaryOperator != null && !dataMethodsUnaryOperator.isEmpty();
			case PooslPackage.DATA_CLASS__DATA_METHODS_BINARY_OPERATOR:
				return dataMethodsBinaryOperator != null && !dataMethodsBinaryOperator.isEmpty();
			case PooslPackage.DATA_CLASS__SUPER_CLASS:
				return SUPER_CLASS_EDEFAULT == null ? superClass != null : !SUPER_CLASS_EDEFAULT.equals(superClass);
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
		result.append(", native: ");
		result.append(native_);
		result.append(", superClass: ");
		result.append(superClass);
		result.append(')');
		return result.toString();
	}

} //DataClassImpl
