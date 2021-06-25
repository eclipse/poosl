/**
 */
package nl.esi.poosl.impl;

import nl.esi.poosl.AbortStatement;
import nl.esi.poosl.PooslPackage;
import nl.esi.poosl.Statement;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abort Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.impl.AbortStatementImpl#getNormalClause <em>Normal Clause</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.AbortStatementImpl#getAbortingClause <em>Aborting Clause</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AbortStatementImpl extends StatementImpl implements AbortStatement
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
	 * The cached value of the '{@link #getAbortingClause() <em>Aborting Clause</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbortingClause()
	 * @generated
	 * @ordered
	 */
	protected Statement abortingClause;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbortStatementImpl()
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
		return PooslPackage.Literals.ABORT_STATEMENT;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PooslPackage.ABORT_STATEMENT__NORMAL_CLAUSE, oldNormalClause, newNormalClause);
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
				msgs = ((InternalEObject)normalClause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PooslPackage.ABORT_STATEMENT__NORMAL_CLAUSE, null, msgs);
			if (newNormalClause != null)
				msgs = ((InternalEObject)newNormalClause).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PooslPackage.ABORT_STATEMENT__NORMAL_CLAUSE, null, msgs);
			msgs = basicSetNormalClause(newNormalClause, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.ABORT_STATEMENT__NORMAL_CLAUSE, newNormalClause, newNormalClause));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Statement getAbortingClause()
	{
		return abortingClause;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAbortingClause(Statement newAbortingClause, NotificationChain msgs)
	{
		Statement oldAbortingClause = abortingClause;
		abortingClause = newAbortingClause;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PooslPackage.ABORT_STATEMENT__ABORTING_CLAUSE, oldAbortingClause, newAbortingClause);
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
	public void setAbortingClause(Statement newAbortingClause)
	{
		if (newAbortingClause != abortingClause) {
			NotificationChain msgs = null;
			if (abortingClause != null)
				msgs = ((InternalEObject)abortingClause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PooslPackage.ABORT_STATEMENT__ABORTING_CLAUSE, null, msgs);
			if (newAbortingClause != null)
				msgs = ((InternalEObject)newAbortingClause).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PooslPackage.ABORT_STATEMENT__ABORTING_CLAUSE, null, msgs);
			msgs = basicSetAbortingClause(newAbortingClause, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.ABORT_STATEMENT__ABORTING_CLAUSE, newAbortingClause, newAbortingClause));
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
			case PooslPackage.ABORT_STATEMENT__NORMAL_CLAUSE:
				return basicSetNormalClause(null, msgs);
			case PooslPackage.ABORT_STATEMENT__ABORTING_CLAUSE:
				return basicSetAbortingClause(null, msgs);
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
			case PooslPackage.ABORT_STATEMENT__NORMAL_CLAUSE:
				return getNormalClause();
			case PooslPackage.ABORT_STATEMENT__ABORTING_CLAUSE:
				return getAbortingClause();
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
			case PooslPackage.ABORT_STATEMENT__NORMAL_CLAUSE:
				setNormalClause((Statement)newValue);
				return;
			case PooslPackage.ABORT_STATEMENT__ABORTING_CLAUSE:
				setAbortingClause((Statement)newValue);
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
			case PooslPackage.ABORT_STATEMENT__NORMAL_CLAUSE:
				setNormalClause((Statement)null);
				return;
			case PooslPackage.ABORT_STATEMENT__ABORTING_CLAUSE:
				setAbortingClause((Statement)null);
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
			case PooslPackage.ABORT_STATEMENT__NORMAL_CLAUSE:
				return normalClause != null;
			case PooslPackage.ABORT_STATEMENT__ABORTING_CLAUSE:
				return abortingClause != null;
		}
		return super.eIsSet(featureID);
	}

} //AbortStatementImpl
