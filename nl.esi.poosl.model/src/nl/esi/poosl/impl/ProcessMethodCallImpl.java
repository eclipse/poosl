/**
 */
package nl.esi.poosl.impl;

import java.util.Collection;

import nl.esi.poosl.Expression;
import nl.esi.poosl.OutputVariable;
import nl.esi.poosl.PooslPackage;
import nl.esi.poosl.ProcessMethodCall;

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
 * An implementation of the model object '<em><b>Process Method Call</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.impl.ProcessMethodCallImpl#getInputArguments <em>Input Arguments</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.ProcessMethodCallImpl#getOutputVariables <em>Output Variables</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.ProcessMethodCallImpl#getMethod <em>Method</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProcessMethodCallImpl extends StatementImpl implements ProcessMethodCall
{
	/**
	 * The cached value of the '{@link #getInputArguments() <em>Input Arguments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputArguments()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> inputArguments;

	/**
	 * The cached value of the '{@link #getOutputVariables() <em>Output Variables</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<OutputVariable> outputVariables;

	/**
	 * The default value of the '{@link #getMethod() <em>Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethod()
	 * @generated
	 * @ordered
	 */
	protected static final String METHOD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMethod() <em>Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethod()
	 * @generated
	 * @ordered
	 */
	protected String method = METHOD_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessMethodCallImpl()
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
		return PooslPackage.Literals.PROCESS_METHOD_CALL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Expression> getInputArguments()
	{
		if (inputArguments == null) {
			inputArguments = new EObjectContainmentEList<Expression>(Expression.class, this, PooslPackage.PROCESS_METHOD_CALL__INPUT_ARGUMENTS);
		}
		return inputArguments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<OutputVariable> getOutputVariables()
	{
		if (outputVariables == null) {
			outputVariables = new EObjectContainmentEList<OutputVariable>(OutputVariable.class, this, PooslPackage.PROCESS_METHOD_CALL__OUTPUT_VARIABLES);
		}
		return outputVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMethod()
	{
		return method;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMethod(String newMethod)
	{
		String oldMethod = method;
		method = newMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.PROCESS_METHOD_CALL__METHOD, oldMethod, method));
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
			case PooslPackage.PROCESS_METHOD_CALL__INPUT_ARGUMENTS:
				return ((InternalEList<?>)getInputArguments()).basicRemove(otherEnd, msgs);
			case PooslPackage.PROCESS_METHOD_CALL__OUTPUT_VARIABLES:
				return ((InternalEList<?>)getOutputVariables()).basicRemove(otherEnd, msgs);
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
			case PooslPackage.PROCESS_METHOD_CALL__INPUT_ARGUMENTS:
				return getInputArguments();
			case PooslPackage.PROCESS_METHOD_CALL__OUTPUT_VARIABLES:
				return getOutputVariables();
			case PooslPackage.PROCESS_METHOD_CALL__METHOD:
				return getMethod();
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
			case PooslPackage.PROCESS_METHOD_CALL__INPUT_ARGUMENTS:
				getInputArguments().clear();
				getInputArguments().addAll((Collection<? extends Expression>)newValue);
				return;
			case PooslPackage.PROCESS_METHOD_CALL__OUTPUT_VARIABLES:
				getOutputVariables().clear();
				getOutputVariables().addAll((Collection<? extends OutputVariable>)newValue);
				return;
			case PooslPackage.PROCESS_METHOD_CALL__METHOD:
				setMethod((String)newValue);
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
			case PooslPackage.PROCESS_METHOD_CALL__INPUT_ARGUMENTS:
				getInputArguments().clear();
				return;
			case PooslPackage.PROCESS_METHOD_CALL__OUTPUT_VARIABLES:
				getOutputVariables().clear();
				return;
			case PooslPackage.PROCESS_METHOD_CALL__METHOD:
				setMethod(METHOD_EDEFAULT);
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
			case PooslPackage.PROCESS_METHOD_CALL__INPUT_ARGUMENTS:
				return inputArguments != null && !inputArguments.isEmpty();
			case PooslPackage.PROCESS_METHOD_CALL__OUTPUT_VARIABLES:
				return outputVariables != null && !outputVariables.isEmpty();
			case PooslPackage.PROCESS_METHOD_CALL__METHOD:
				return METHOD_EDEFAULT == null ? method != null : !METHOD_EDEFAULT.equals(method);
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
		result.append(" (method: ");
		result.append(method);
		result.append(')');
		return result.toString();
	}

} //ProcessMethodCallImpl
