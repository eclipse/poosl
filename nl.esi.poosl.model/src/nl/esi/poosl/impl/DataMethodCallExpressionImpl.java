/**
 */
package nl.esi.poosl.impl;

import java.util.Collection;

import nl.esi.poosl.DataMethodCallExpression;
import nl.esi.poosl.Expression;
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
 * An implementation of the model object '<em><b>Data Method Call Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.impl.DataMethodCallExpressionImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.DataMethodCallExpressionImpl#isOnSuperClass <em>On Super Class</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.DataMethodCallExpressionImpl#getName <em>Name</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.DataMethodCallExpressionImpl#getArguments <em>Arguments</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DataMethodCallExpressionImpl extends ExpressionImpl implements DataMethodCallExpression
{
	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected Expression target;

	/**
	 * The default value of the '{@link #isOnSuperClass() <em>On Super Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOnSuperClass()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ON_SUPER_CLASS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOnSuperClass() <em>On Super Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOnSuperClass()
	 * @generated
	 * @ordered
	 */
	protected boolean onSuperClass = ON_SUPER_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "";

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
	 * The cached value of the '{@link #getArguments() <em>Arguments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArguments()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> arguments;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataMethodCallExpressionImpl()
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
		return PooslPackage.Literals.DATA_METHOD_CALL_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getTarget()
	{
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTarget(Expression newTarget, NotificationChain msgs)
	{
		Expression oldTarget = target;
		target = newTarget;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PooslPackage.DATA_METHOD_CALL_EXPRESSION__TARGET, oldTarget, newTarget);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTarget(Expression newTarget)
	{
		if (newTarget != target) {
			NotificationChain msgs = null;
			if (target != null)
				msgs = ((InternalEObject)target).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PooslPackage.DATA_METHOD_CALL_EXPRESSION__TARGET, null, msgs);
			if (newTarget != null)
				msgs = ((InternalEObject)newTarget).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PooslPackage.DATA_METHOD_CALL_EXPRESSION__TARGET, null, msgs);
			msgs = basicSetTarget(newTarget, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.DATA_METHOD_CALL_EXPRESSION__TARGET, newTarget, newTarget));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isOnSuperClass()
	{
		return onSuperClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOnSuperClass(boolean newOnSuperClass)
	{
		boolean oldOnSuperClass = onSuperClass;
		onSuperClass = newOnSuperClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.DATA_METHOD_CALL_EXPRESSION__ON_SUPER_CLASS, oldOnSuperClass, onSuperClass));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.DATA_METHOD_CALL_EXPRESSION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Expression> getArguments()
	{
		if (arguments == null) {
			arguments = new EObjectContainmentEList<Expression>(Expression.class, this, PooslPackage.DATA_METHOD_CALL_EXPRESSION__ARGUMENTS);
		}
		return arguments;
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
			case PooslPackage.DATA_METHOD_CALL_EXPRESSION__TARGET:
				return basicSetTarget(null, msgs);
			case PooslPackage.DATA_METHOD_CALL_EXPRESSION__ARGUMENTS:
				return ((InternalEList<?>)getArguments()).basicRemove(otherEnd, msgs);
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
			case PooslPackage.DATA_METHOD_CALL_EXPRESSION__TARGET:
				return getTarget();
			case PooslPackage.DATA_METHOD_CALL_EXPRESSION__ON_SUPER_CLASS:
				return isOnSuperClass();
			case PooslPackage.DATA_METHOD_CALL_EXPRESSION__NAME:
				return getName();
			case PooslPackage.DATA_METHOD_CALL_EXPRESSION__ARGUMENTS:
				return getArguments();
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
			case PooslPackage.DATA_METHOD_CALL_EXPRESSION__TARGET:
				setTarget((Expression)newValue);
				return;
			case PooslPackage.DATA_METHOD_CALL_EXPRESSION__ON_SUPER_CLASS:
				setOnSuperClass((Boolean)newValue);
				return;
			case PooslPackage.DATA_METHOD_CALL_EXPRESSION__NAME:
				setName((String)newValue);
				return;
			case PooslPackage.DATA_METHOD_CALL_EXPRESSION__ARGUMENTS:
				getArguments().clear();
				getArguments().addAll((Collection<? extends Expression>)newValue);
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
			case PooslPackage.DATA_METHOD_CALL_EXPRESSION__TARGET:
				setTarget((Expression)null);
				return;
			case PooslPackage.DATA_METHOD_CALL_EXPRESSION__ON_SUPER_CLASS:
				setOnSuperClass(ON_SUPER_CLASS_EDEFAULT);
				return;
			case PooslPackage.DATA_METHOD_CALL_EXPRESSION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PooslPackage.DATA_METHOD_CALL_EXPRESSION__ARGUMENTS:
				getArguments().clear();
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
			case PooslPackage.DATA_METHOD_CALL_EXPRESSION__TARGET:
				return target != null;
			case PooslPackage.DATA_METHOD_CALL_EXPRESSION__ON_SUPER_CLASS:
				return onSuperClass != ON_SUPER_CLASS_EDEFAULT;
			case PooslPackage.DATA_METHOD_CALL_EXPRESSION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PooslPackage.DATA_METHOD_CALL_EXPRESSION__ARGUMENTS:
				return arguments != null && !arguments.isEmpty();
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
		result.append(" (onSuperClass: ");
		result.append(onSuperClass);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //DataMethodCallExpressionImpl
