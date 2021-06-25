/**
 */
package nl.esi.poosl.impl;

import java.util.Collection;

import nl.esi.poosl.Expression;
import nl.esi.poosl.PooslPackage;
import nl.esi.poosl.SwitchExpression;
import nl.esi.poosl.SwitchExpressionCase;

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
 * An implementation of the model object '<em><b>Switch Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.impl.SwitchExpressionImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.SwitchExpressionImpl#getCases <em>Cases</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.SwitchExpressionImpl#getDefaultBody <em>Default Body</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SwitchExpressionImpl extends ExpressionImpl implements SwitchExpression
{
	/**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression expression;

	/**
	 * The cached value of the '{@link #getCases() <em>Cases</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCases()
	 * @generated
	 * @ordered
	 */
	protected EList<SwitchExpressionCase> cases;

	/**
	 * The cached value of the '{@link #getDefaultBody() <em>Default Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultBody()
	 * @generated
	 * @ordered
	 */
	protected Expression defaultBody;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SwitchExpressionImpl()
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
		return PooslPackage.Literals.SWITCH_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getExpression()
	{
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs)
	{
		Expression oldExpression = expression;
		expression = newExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PooslPackage.SWITCH_EXPRESSION__EXPRESSION, oldExpression, newExpression);
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
	public void setExpression(Expression newExpression)
	{
		if (newExpression != expression) {
			NotificationChain msgs = null;
			if (expression != null)
				msgs = ((InternalEObject)expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PooslPackage.SWITCH_EXPRESSION__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PooslPackage.SWITCH_EXPRESSION__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.SWITCH_EXPRESSION__EXPRESSION, newExpression, newExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<SwitchExpressionCase> getCases()
	{
		if (cases == null) {
			cases = new EObjectContainmentEList<SwitchExpressionCase>(SwitchExpressionCase.class, this, PooslPackage.SWITCH_EXPRESSION__CASES);
		}
		return cases;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getDefaultBody()
	{
		return defaultBody;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefaultBody(Expression newDefaultBody, NotificationChain msgs)
	{
		Expression oldDefaultBody = defaultBody;
		defaultBody = newDefaultBody;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PooslPackage.SWITCH_EXPRESSION__DEFAULT_BODY, oldDefaultBody, newDefaultBody);
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
	public void setDefaultBody(Expression newDefaultBody)
	{
		if (newDefaultBody != defaultBody) {
			NotificationChain msgs = null;
			if (defaultBody != null)
				msgs = ((InternalEObject)defaultBody).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PooslPackage.SWITCH_EXPRESSION__DEFAULT_BODY, null, msgs);
			if (newDefaultBody != null)
				msgs = ((InternalEObject)newDefaultBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PooslPackage.SWITCH_EXPRESSION__DEFAULT_BODY, null, msgs);
			msgs = basicSetDefaultBody(newDefaultBody, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.SWITCH_EXPRESSION__DEFAULT_BODY, newDefaultBody, newDefaultBody));
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
			case PooslPackage.SWITCH_EXPRESSION__EXPRESSION:
				return basicSetExpression(null, msgs);
			case PooslPackage.SWITCH_EXPRESSION__CASES:
				return ((InternalEList<?>)getCases()).basicRemove(otherEnd, msgs);
			case PooslPackage.SWITCH_EXPRESSION__DEFAULT_BODY:
				return basicSetDefaultBody(null, msgs);
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
			case PooslPackage.SWITCH_EXPRESSION__EXPRESSION:
				return getExpression();
			case PooslPackage.SWITCH_EXPRESSION__CASES:
				return getCases();
			case PooslPackage.SWITCH_EXPRESSION__DEFAULT_BODY:
				return getDefaultBody();
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
			case PooslPackage.SWITCH_EXPRESSION__EXPRESSION:
				setExpression((Expression)newValue);
				return;
			case PooslPackage.SWITCH_EXPRESSION__CASES:
				getCases().clear();
				getCases().addAll((Collection<? extends SwitchExpressionCase>)newValue);
				return;
			case PooslPackage.SWITCH_EXPRESSION__DEFAULT_BODY:
				setDefaultBody((Expression)newValue);
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
			case PooslPackage.SWITCH_EXPRESSION__EXPRESSION:
				setExpression((Expression)null);
				return;
			case PooslPackage.SWITCH_EXPRESSION__CASES:
				getCases().clear();
				return;
			case PooslPackage.SWITCH_EXPRESSION__DEFAULT_BODY:
				setDefaultBody((Expression)null);
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
			case PooslPackage.SWITCH_EXPRESSION__EXPRESSION:
				return expression != null;
			case PooslPackage.SWITCH_EXPRESSION__CASES:
				return cases != null && !cases.isEmpty();
			case PooslPackage.SWITCH_EXPRESSION__DEFAULT_BODY:
				return defaultBody != null;
		}
		return super.eIsSet(featureID);
	}

} //SwitchExpressionImpl
