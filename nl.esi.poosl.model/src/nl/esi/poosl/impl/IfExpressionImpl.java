/**
 */
package nl.esi.poosl.impl;

import nl.esi.poosl.Expression;
import nl.esi.poosl.IfExpression;
import nl.esi.poosl.PooslPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>If Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.impl.IfExpressionImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.IfExpressionImpl#getThenClause <em>Then Clause</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.IfExpressionImpl#getElseClause <em>Else Clause</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IfExpressionImpl extends ExpressionImpl implements IfExpression
{
	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected Expression condition;

	/**
	 * The cached value of the '{@link #getThenClause() <em>Then Clause</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThenClause()
	 * @generated
	 * @ordered
	 */
	protected Expression thenClause;

	/**
	 * The cached value of the '{@link #getElseClause() <em>Else Clause</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElseClause()
	 * @generated
	 * @ordered
	 */
	protected Expression elseClause;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IfExpressionImpl()
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
		return PooslPackage.Literals.IF_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getCondition()
	{
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCondition(Expression newCondition, NotificationChain msgs)
	{
		Expression oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PooslPackage.IF_EXPRESSION__CONDITION, oldCondition, newCondition);
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
	public void setCondition(Expression newCondition)
	{
		if (newCondition != condition) {
			NotificationChain msgs = null;
			if (condition != null)
				msgs = ((InternalEObject)condition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PooslPackage.IF_EXPRESSION__CONDITION, null, msgs);
			if (newCondition != null)
				msgs = ((InternalEObject)newCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PooslPackage.IF_EXPRESSION__CONDITION, null, msgs);
			msgs = basicSetCondition(newCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.IF_EXPRESSION__CONDITION, newCondition, newCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getThenClause()
	{
		return thenClause;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetThenClause(Expression newThenClause, NotificationChain msgs)
	{
		Expression oldThenClause = thenClause;
		thenClause = newThenClause;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PooslPackage.IF_EXPRESSION__THEN_CLAUSE, oldThenClause, newThenClause);
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
	public void setThenClause(Expression newThenClause)
	{
		if (newThenClause != thenClause) {
			NotificationChain msgs = null;
			if (thenClause != null)
				msgs = ((InternalEObject)thenClause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PooslPackage.IF_EXPRESSION__THEN_CLAUSE, null, msgs);
			if (newThenClause != null)
				msgs = ((InternalEObject)newThenClause).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PooslPackage.IF_EXPRESSION__THEN_CLAUSE, null, msgs);
			msgs = basicSetThenClause(newThenClause, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.IF_EXPRESSION__THEN_CLAUSE, newThenClause, newThenClause));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getElseClause()
	{
		return elseClause;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetElseClause(Expression newElseClause, NotificationChain msgs)
	{
		Expression oldElseClause = elseClause;
		elseClause = newElseClause;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PooslPackage.IF_EXPRESSION__ELSE_CLAUSE, oldElseClause, newElseClause);
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
	public void setElseClause(Expression newElseClause)
	{
		if (newElseClause != elseClause) {
			NotificationChain msgs = null;
			if (elseClause != null)
				msgs = ((InternalEObject)elseClause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PooslPackage.IF_EXPRESSION__ELSE_CLAUSE, null, msgs);
			if (newElseClause != null)
				msgs = ((InternalEObject)newElseClause).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PooslPackage.IF_EXPRESSION__ELSE_CLAUSE, null, msgs);
			msgs = basicSetElseClause(newElseClause, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.IF_EXPRESSION__ELSE_CLAUSE, newElseClause, newElseClause));
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
			case PooslPackage.IF_EXPRESSION__CONDITION:
				return basicSetCondition(null, msgs);
			case PooslPackage.IF_EXPRESSION__THEN_CLAUSE:
				return basicSetThenClause(null, msgs);
			case PooslPackage.IF_EXPRESSION__ELSE_CLAUSE:
				return basicSetElseClause(null, msgs);
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
			case PooslPackage.IF_EXPRESSION__CONDITION:
				return getCondition();
			case PooslPackage.IF_EXPRESSION__THEN_CLAUSE:
				return getThenClause();
			case PooslPackage.IF_EXPRESSION__ELSE_CLAUSE:
				return getElseClause();
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
			case PooslPackage.IF_EXPRESSION__CONDITION:
				setCondition((Expression)newValue);
				return;
			case PooslPackage.IF_EXPRESSION__THEN_CLAUSE:
				setThenClause((Expression)newValue);
				return;
			case PooslPackage.IF_EXPRESSION__ELSE_CLAUSE:
				setElseClause((Expression)newValue);
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
			case PooslPackage.IF_EXPRESSION__CONDITION:
				setCondition((Expression)null);
				return;
			case PooslPackage.IF_EXPRESSION__THEN_CLAUSE:
				setThenClause((Expression)null);
				return;
			case PooslPackage.IF_EXPRESSION__ELSE_CLAUSE:
				setElseClause((Expression)null);
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
			case PooslPackage.IF_EXPRESSION__CONDITION:
				return condition != null;
			case PooslPackage.IF_EXPRESSION__THEN_CLAUSE:
				return thenClause != null;
			case PooslPackage.IF_EXPRESSION__ELSE_CLAUSE:
				return elseClause != null;
		}
		return super.eIsSet(featureID);
	}

} //IfExpressionImpl
