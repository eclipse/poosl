/**
 */
package nl.esi.poosl.impl;

import nl.esi.poosl.NewExpression;
import nl.esi.poosl.PooslPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>New Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.impl.NewExpressionImpl#getDataClass <em>Data Class</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NewExpressionImpl extends ExpressionImpl implements NewExpression
{
	/**
	 * The default value of the '{@link #getDataClass() <em>Data Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataClass()
	 * @generated
	 * @ordered
	 */
	protected static final String DATA_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDataClass() <em>Data Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataClass()
	 * @generated
	 * @ordered
	 */
	protected String dataClass = DATA_CLASS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NewExpressionImpl()
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
		return PooslPackage.Literals.NEW_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDataClass()
	{
		return dataClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDataClass(String newDataClass)
	{
		String oldDataClass = dataClass;
		dataClass = newDataClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.NEW_EXPRESSION__DATA_CLASS, oldDataClass, dataClass));
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
			case PooslPackage.NEW_EXPRESSION__DATA_CLASS:
				return getDataClass();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID) {
			case PooslPackage.NEW_EXPRESSION__DATA_CLASS:
				setDataClass((String)newValue);
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
			case PooslPackage.NEW_EXPRESSION__DATA_CLASS:
				setDataClass(DATA_CLASS_EDEFAULT);
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
			case PooslPackage.NEW_EXPRESSION__DATA_CLASS:
				return DATA_CLASS_EDEFAULT == null ? dataClass != null : !DATA_CLASS_EDEFAULT.equals(dataClass);
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
		result.append(" (dataClass: ");
		result.append(dataClass);
		result.append(')');
		return result.toString();
	}

} //NewExpressionImpl
