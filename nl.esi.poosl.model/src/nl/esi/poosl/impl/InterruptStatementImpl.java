/**
 */
package nl.esi.poosl.impl;

import nl.esi.poosl.InterruptStatement;
import nl.esi.poosl.PooslPackage;
import nl.esi.poosl.Statement;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Interrupt Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.impl.InterruptStatementImpl#getNormalClause <em>Normal Clause</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.InterruptStatementImpl#getInterruptingClause <em>Interrupting Clause</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InterruptStatementImpl extends StatementImpl implements InterruptStatement
{
	/**
	 * The cached value of the '{@link #getNormalClause() <em>Normal Clause</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNormalClause()
	 * @generated
	 * @ordered
	 */
	protected Statement normalClause;

	/**
	 * The cached value of the '{@link #getInterruptingClause() <em>Interrupting Clause</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterruptingClause()
	 * @generated
	 * @ordered
	 */
	protected Statement interruptingClause;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InterruptStatementImpl()
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
		return PooslPackage.Literals.INTERRUPT_STATEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Statement getNormalClause()
	{
		return normalClause;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNormalClause(Statement newNormalClause, NotificationChain msgs)
	{
		Statement oldNormalClause = normalClause;
		normalClause = newNormalClause;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PooslPackage.INTERRUPT_STATEMENT__NORMAL_CLAUSE, oldNormalClause, newNormalClause);
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
	public void setNormalClause(Statement newNormalClause)
	{
		if (newNormalClause != normalClause) {
			NotificationChain msgs = null;
			if (normalClause != null)
				msgs = ((InternalEObject)normalClause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PooslPackage.INTERRUPT_STATEMENT__NORMAL_CLAUSE, null, msgs);
			if (newNormalClause != null)
				msgs = ((InternalEObject)newNormalClause).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PooslPackage.INTERRUPT_STATEMENT__NORMAL_CLAUSE, null, msgs);
			msgs = basicSetNormalClause(newNormalClause, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.INTERRUPT_STATEMENT__NORMAL_CLAUSE, newNormalClause, newNormalClause));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Statement getInterruptingClause()
	{
		return interruptingClause;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInterruptingClause(Statement newInterruptingClause, NotificationChain msgs)
	{
		Statement oldInterruptingClause = interruptingClause;
		interruptingClause = newInterruptingClause;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PooslPackage.INTERRUPT_STATEMENT__INTERRUPTING_CLAUSE, oldInterruptingClause, newInterruptingClause);
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
	public void setInterruptingClause(Statement newInterruptingClause)
	{
		if (newInterruptingClause != interruptingClause) {
			NotificationChain msgs = null;
			if (interruptingClause != null)
				msgs = ((InternalEObject)interruptingClause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PooslPackage.INTERRUPT_STATEMENT__INTERRUPTING_CLAUSE, null, msgs);
			if (newInterruptingClause != null)
				msgs = ((InternalEObject)newInterruptingClause).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PooslPackage.INTERRUPT_STATEMENT__INTERRUPTING_CLAUSE, null, msgs);
			msgs = basicSetInterruptingClause(newInterruptingClause, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.INTERRUPT_STATEMENT__INTERRUPTING_CLAUSE, newInterruptingClause, newInterruptingClause));
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
			case PooslPackage.INTERRUPT_STATEMENT__NORMAL_CLAUSE:
				return basicSetNormalClause(null, msgs);
			case PooslPackage.INTERRUPT_STATEMENT__INTERRUPTING_CLAUSE:
				return basicSetInterruptingClause(null, msgs);
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
			case PooslPackage.INTERRUPT_STATEMENT__NORMAL_CLAUSE:
				return getNormalClause();
			case PooslPackage.INTERRUPT_STATEMENT__INTERRUPTING_CLAUSE:
				return getInterruptingClause();
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
			case PooslPackage.INTERRUPT_STATEMENT__NORMAL_CLAUSE:
				setNormalClause((Statement)newValue);
				return;
			case PooslPackage.INTERRUPT_STATEMENT__INTERRUPTING_CLAUSE:
				setInterruptingClause((Statement)newValue);
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
			case PooslPackage.INTERRUPT_STATEMENT__NORMAL_CLAUSE:
				setNormalClause((Statement)null);
				return;
			case PooslPackage.INTERRUPT_STATEMENT__INTERRUPTING_CLAUSE:
				setInterruptingClause((Statement)null);
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
			case PooslPackage.INTERRUPT_STATEMENT__NORMAL_CLAUSE:
				return normalClause != null;
			case PooslPackage.INTERRUPT_STATEMENT__INTERRUPTING_CLAUSE:
				return interruptingClause != null;
		}
		return super.eIsSet(featureID);
	}

} //InterruptStatementImpl
