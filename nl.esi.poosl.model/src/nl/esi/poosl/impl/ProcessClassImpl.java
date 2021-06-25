/**
 */
package nl.esi.poosl.impl;

import java.util.Collection;

import nl.esi.poosl.Declaration;
import nl.esi.poosl.MessageSignature;
import nl.esi.poosl.PooslPackage;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
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
 * An implementation of the model object '<em><b>Process Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.impl.ProcessClassImpl#getReceiveMessages <em>Receive Messages</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.ProcessClassImpl#getSendMessages <em>Send Messages</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.ProcessClassImpl#getInstanceVariables <em>Instance Variables</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.ProcessClassImpl#getMethods <em>Methods</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.ProcessClassImpl#getInitialMethodCall <em>Initial Method Call</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.ProcessClassImpl#getSuperClass <em>Super Class</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProcessClassImpl extends InstantiableClassImpl implements ProcessClass
{
	/**
	 * The cached value of the '{@link #getReceiveMessages() <em>Receive Messages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReceiveMessages()
	 * @generated
	 * @ordered
	 */
	protected EList<MessageSignature> receiveMessages;

	/**
	 * The cached value of the '{@link #getSendMessages() <em>Send Messages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSendMessages()
	 * @generated
	 * @ordered
	 */
	protected EList<MessageSignature> sendMessages;

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
	 * The cached value of the '{@link #getMethods() <em>Methods</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethods()
	 * @generated
	 * @ordered
	 */
	protected EList<ProcessMethod> methods;

	/**
	 * The cached value of the '{@link #getInitialMethodCall() <em>Initial Method Call</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialMethodCall()
	 * @generated
	 * @ordered
	 */
	protected ProcessMethodCall initialMethodCall;

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
	protected ProcessClassImpl()
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
		return PooslPackage.Literals.PROCESS_CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<MessageSignature> getReceiveMessages()
	{
		if (receiveMessages == null) {
			receiveMessages = new EObjectContainmentEList<MessageSignature>(MessageSignature.class, this, PooslPackage.PROCESS_CLASS__RECEIVE_MESSAGES);
		}
		return receiveMessages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<MessageSignature> getSendMessages()
	{
		if (sendMessages == null) {
			sendMessages = new EObjectContainmentEList<MessageSignature>(MessageSignature.class, this, PooslPackage.PROCESS_CLASS__SEND_MESSAGES);
		}
		return sendMessages;
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
			instanceVariables = new EObjectContainmentEList<Declaration>(Declaration.class, this, PooslPackage.PROCESS_CLASS__INSTANCE_VARIABLES);
		}
		return instanceVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ProcessMethod> getMethods()
	{
		if (methods == null) {
			methods = new EObjectContainmentEList<ProcessMethod>(ProcessMethod.class, this, PooslPackage.PROCESS_CLASS__METHODS);
		}
		return methods;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ProcessMethodCall getInitialMethodCall()
	{
		return initialMethodCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInitialMethodCall(ProcessMethodCall newInitialMethodCall, NotificationChain msgs)
	{
		ProcessMethodCall oldInitialMethodCall = initialMethodCall;
		initialMethodCall = newInitialMethodCall;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PooslPackage.PROCESS_CLASS__INITIAL_METHOD_CALL, oldInitialMethodCall, newInitialMethodCall);
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
	public void setInitialMethodCall(ProcessMethodCall newInitialMethodCall)
	{
		if (newInitialMethodCall != initialMethodCall) {
			NotificationChain msgs = null;
			if (initialMethodCall != null)
				msgs = ((InternalEObject)initialMethodCall).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PooslPackage.PROCESS_CLASS__INITIAL_METHOD_CALL, null, msgs);
			if (newInitialMethodCall != null)
				msgs = ((InternalEObject)newInitialMethodCall).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PooslPackage.PROCESS_CLASS__INITIAL_METHOD_CALL, null, msgs);
			msgs = basicSetInitialMethodCall(newInitialMethodCall, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.PROCESS_CLASS__INITIAL_METHOD_CALL, newInitialMethodCall, newInitialMethodCall));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.PROCESS_CLASS__SUPER_CLASS, oldSuperClass, superClass));
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
			case PooslPackage.PROCESS_CLASS__RECEIVE_MESSAGES:
				return ((InternalEList<?>)getReceiveMessages()).basicRemove(otherEnd, msgs);
			case PooslPackage.PROCESS_CLASS__SEND_MESSAGES:
				return ((InternalEList<?>)getSendMessages()).basicRemove(otherEnd, msgs);
			case PooslPackage.PROCESS_CLASS__INSTANCE_VARIABLES:
				return ((InternalEList<?>)getInstanceVariables()).basicRemove(otherEnd, msgs);
			case PooslPackage.PROCESS_CLASS__METHODS:
				return ((InternalEList<?>)getMethods()).basicRemove(otherEnd, msgs);
			case PooslPackage.PROCESS_CLASS__INITIAL_METHOD_CALL:
				return basicSetInitialMethodCall(null, msgs);
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
			case PooslPackage.PROCESS_CLASS__RECEIVE_MESSAGES:
				return getReceiveMessages();
			case PooslPackage.PROCESS_CLASS__SEND_MESSAGES:
				return getSendMessages();
			case PooslPackage.PROCESS_CLASS__INSTANCE_VARIABLES:
				return getInstanceVariables();
			case PooslPackage.PROCESS_CLASS__METHODS:
				return getMethods();
			case PooslPackage.PROCESS_CLASS__INITIAL_METHOD_CALL:
				return getInitialMethodCall();
			case PooslPackage.PROCESS_CLASS__SUPER_CLASS:
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
			case PooslPackage.PROCESS_CLASS__RECEIVE_MESSAGES:
				getReceiveMessages().clear();
				getReceiveMessages().addAll((Collection<? extends MessageSignature>)newValue);
				return;
			case PooslPackage.PROCESS_CLASS__SEND_MESSAGES:
				getSendMessages().clear();
				getSendMessages().addAll((Collection<? extends MessageSignature>)newValue);
				return;
			case PooslPackage.PROCESS_CLASS__INSTANCE_VARIABLES:
				getInstanceVariables().clear();
				getInstanceVariables().addAll((Collection<? extends Declaration>)newValue);
				return;
			case PooslPackage.PROCESS_CLASS__METHODS:
				getMethods().clear();
				getMethods().addAll((Collection<? extends ProcessMethod>)newValue);
				return;
			case PooslPackage.PROCESS_CLASS__INITIAL_METHOD_CALL:
				setInitialMethodCall((ProcessMethodCall)newValue);
				return;
			case PooslPackage.PROCESS_CLASS__SUPER_CLASS:
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
			case PooslPackage.PROCESS_CLASS__RECEIVE_MESSAGES:
				getReceiveMessages().clear();
				return;
			case PooslPackage.PROCESS_CLASS__SEND_MESSAGES:
				getSendMessages().clear();
				return;
			case PooslPackage.PROCESS_CLASS__INSTANCE_VARIABLES:
				getInstanceVariables().clear();
				return;
			case PooslPackage.PROCESS_CLASS__METHODS:
				getMethods().clear();
				return;
			case PooslPackage.PROCESS_CLASS__INITIAL_METHOD_CALL:
				setInitialMethodCall((ProcessMethodCall)null);
				return;
			case PooslPackage.PROCESS_CLASS__SUPER_CLASS:
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
			case PooslPackage.PROCESS_CLASS__RECEIVE_MESSAGES:
				return receiveMessages != null && !receiveMessages.isEmpty();
			case PooslPackage.PROCESS_CLASS__SEND_MESSAGES:
				return sendMessages != null && !sendMessages.isEmpty();
			case PooslPackage.PROCESS_CLASS__INSTANCE_VARIABLES:
				return instanceVariables != null && !instanceVariables.isEmpty();
			case PooslPackage.PROCESS_CLASS__METHODS:
				return methods != null && !methods.isEmpty();
			case PooslPackage.PROCESS_CLASS__INITIAL_METHOD_CALL:
				return initialMethodCall != null;
			case PooslPackage.PROCESS_CLASS__SUPER_CLASS:
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
		result.append(" (superClass: ");
		result.append(superClass);
		result.append(')');
		return result.toString();
	}

} //ProcessClassImpl
