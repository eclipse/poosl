/**
 * Copyright (c) 2021 TNO/ESI
 *  This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License 2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 * 
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     TNO/ESI - initial API and implementation
 *     Obeo - refactoring
 */
package org.eclipse.poosl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.poosl.Expression;
import org.eclipse.poosl.PooslPackage;
import org.eclipse.poosl.Statement;
import org.eclipse.poosl.SwitchStatementCase;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Switch Statement Case</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.poosl.impl.SwitchStatementCaseImpl#getValue <em>Value</em>}</li>
 * <li>{@link org.eclipse.poosl.impl.SwitchStatementCaseImpl#getBody <em>Body</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SwitchStatementCaseImpl extends EObjectImpl implements SwitchStatementCase {
    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected Expression value;

    /**
     * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getBody()
     * @generated
     * @ordered
     */
    protected Statement body;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected SwitchStatementCaseImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PooslPackage.Literals.SWITCH_STATEMENT_CASE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Expression getValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetValue(Expression newValue, NotificationChain msgs) {
        Expression oldValue = value;
        value = newValue;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PooslPackage.SWITCH_STATEMENT_CASE__VALUE, oldValue, newValue);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setValue(Expression newValue) {
        if (newValue != value) {
            NotificationChain msgs = null;
            if (value != null)
                msgs = ((InternalEObject) value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PooslPackage.SWITCH_STATEMENT_CASE__VALUE, null, msgs);
            if (newValue != null)
                msgs = ((InternalEObject) newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PooslPackage.SWITCH_STATEMENT_CASE__VALUE, null, msgs);
            msgs = basicSetValue(newValue, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.SWITCH_STATEMENT_CASE__VALUE, newValue, newValue));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Statement getBody() {
        return body;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetBody(Statement newBody, NotificationChain msgs) {
        Statement oldBody = body;
        body = newBody;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PooslPackage.SWITCH_STATEMENT_CASE__BODY, oldBody, newBody);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setBody(Statement newBody) {
        if (newBody != body) {
            NotificationChain msgs = null;
            if (body != null)
                msgs = ((InternalEObject) body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PooslPackage.SWITCH_STATEMENT_CASE__BODY, null, msgs);
            if (newBody != null)
                msgs = ((InternalEObject) newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PooslPackage.SWITCH_STATEMENT_CASE__BODY, null, msgs);
            msgs = basicSetBody(newBody, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.SWITCH_STATEMENT_CASE__BODY, newBody, newBody));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case PooslPackage.SWITCH_STATEMENT_CASE__VALUE:
            return basicSetValue(null, msgs);
        case PooslPackage.SWITCH_STATEMENT_CASE__BODY:
            return basicSetBody(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case PooslPackage.SWITCH_STATEMENT_CASE__VALUE:
            return getValue();
        case PooslPackage.SWITCH_STATEMENT_CASE__BODY:
            return getBody();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case PooslPackage.SWITCH_STATEMENT_CASE__VALUE:
            setValue((Expression) newValue);
            return;
        case PooslPackage.SWITCH_STATEMENT_CASE__BODY:
            setBody((Statement) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case PooslPackage.SWITCH_STATEMENT_CASE__VALUE:
            setValue((Expression) null);
            return;
        case PooslPackage.SWITCH_STATEMENT_CASE__BODY:
            setBody((Statement) null);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case PooslPackage.SWITCH_STATEMENT_CASE__VALUE:
            return value != null;
        case PooslPackage.SWITCH_STATEMENT_CASE__BODY:
            return body != null;
        }
        return super.eIsSet(featureID);
    }

} // SwitchStatementCaseImpl
