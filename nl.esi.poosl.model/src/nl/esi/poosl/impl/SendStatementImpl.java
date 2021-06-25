/**
 */
package nl.esi.poosl.impl;

import java.util.Collection;

import nl.esi.poosl.Expression;
import nl.esi.poosl.PooslPackage;
import nl.esi.poosl.PortDescriptor;
import nl.esi.poosl.SendStatement;

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
 * An implementation of the model object '<em><b>Send Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.impl.SendStatementImpl#getPortDescriptor <em>Port Descriptor</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.SendStatementImpl#getName <em>Name</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.SendStatementImpl#getArguments <em>Arguments</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.SendStatementImpl#getPostCommunicationExpression <em>Post Communication Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SendStatementImpl extends StatementImpl implements SendStatement
{
	/**
	 * The cached value of the '{@link #getPortDescriptor() <em>Port Descriptor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortDescriptor()
	 * @generated
	 * @ordered
	 */
	protected PortDescriptor portDescriptor;

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
	 * The cached value of the '{@link #getArguments() <em>Arguments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArguments()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> arguments;

	/**
	 * The cached value of the '{@link #getPostCommunicationExpression() <em>Post Communication Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostCommunicationExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression postCommunicationExpression;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SendStatementImpl()
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
		return PooslPackage.Literals.SEND_STATEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PortDescriptor getPortDescriptor()
	{
		return portDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPortDescriptor(PortDescriptor newPortDescriptor, NotificationChain msgs)
	{
		PortDescriptor oldPortDescriptor = portDescriptor;
		portDescriptor = newPortDescriptor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PooslPackage.SEND_STATEMENT__PORT_DESCRIPTOR, oldPortDescriptor, newPortDescriptor);
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
	public void setPortDescriptor(PortDescriptor newPortDescriptor)
	{
		if (newPortDescriptor != portDescriptor) {
			NotificationChain msgs = null;
			if (portDescriptor != null)
				msgs = ((InternalEObject)portDescriptor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PooslPackage.SEND_STATEMENT__PORT_DESCRIPTOR, null, msgs);
			if (newPortDescriptor != null)
				msgs = ((InternalEObject)newPortDescriptor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PooslPackage.SEND_STATEMENT__PORT_DESCRIPTOR, null, msgs);
			msgs = basicSetPortDescriptor(newPortDescriptor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.SEND_STATEMENT__PORT_DESCRIPTOR, newPortDescriptor, newPortDescriptor));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.SEND_STATEMENT__NAME, oldName, name));
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
			arguments = new EObjectContainmentEList<Expression>(Expression.class, this, PooslPackage.SEND_STATEMENT__ARGUMENTS);
		}
		return arguments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getPostCommunicationExpression()
	{
		return postCommunicationExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPostCommunicationExpression(Expression newPostCommunicationExpression, NotificationChain msgs)
	{
		Expression oldPostCommunicationExpression = postCommunicationExpression;
		postCommunicationExpression = newPostCommunicationExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PooslPackage.SEND_STATEMENT__POST_COMMUNICATION_EXPRESSION, oldPostCommunicationExpression, newPostCommunicationExpression);
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
	public void setPostCommunicationExpression(Expression newPostCommunicationExpression)
	{
		if (newPostCommunicationExpression != postCommunicationExpression) {
			NotificationChain msgs = null;
			if (postCommunicationExpression != null)
				msgs = ((InternalEObject)postCommunicationExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PooslPackage.SEND_STATEMENT__POST_COMMUNICATION_EXPRESSION, null, msgs);
			if (newPostCommunicationExpression != null)
				msgs = ((InternalEObject)newPostCommunicationExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PooslPackage.SEND_STATEMENT__POST_COMMUNICATION_EXPRESSION, null, msgs);
			msgs = basicSetPostCommunicationExpression(newPostCommunicationExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.SEND_STATEMENT__POST_COMMUNICATION_EXPRESSION, newPostCommunicationExpression, newPostCommunicationExpression));
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
			case PooslPackage.SEND_STATEMENT__PORT_DESCRIPTOR:
				return basicSetPortDescriptor(null, msgs);
			case PooslPackage.SEND_STATEMENT__ARGUMENTS:
				return ((InternalEList<?>)getArguments()).basicRemove(otherEnd, msgs);
			case PooslPackage.SEND_STATEMENT__POST_COMMUNICATION_EXPRESSION:
				return basicSetPostCommunicationExpression(null, msgs);
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
			case PooslPackage.SEND_STATEMENT__PORT_DESCRIPTOR:
				return getPortDescriptor();
			case PooslPackage.SEND_STATEMENT__NAME:
				return getName();
			case PooslPackage.SEND_STATEMENT__ARGUMENTS:
				return getArguments();
			case PooslPackage.SEND_STATEMENT__POST_COMMUNICATION_EXPRESSION:
				return getPostCommunicationExpression();
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
			case PooslPackage.SEND_STATEMENT__PORT_DESCRIPTOR:
				setPortDescriptor((PortDescriptor)newValue);
				return;
			case PooslPackage.SEND_STATEMENT__NAME:
				setName((String)newValue);
				return;
			case PooslPackage.SEND_STATEMENT__ARGUMENTS:
				getArguments().clear();
				getArguments().addAll((Collection<? extends Expression>)newValue);
				return;
			case PooslPackage.SEND_STATEMENT__POST_COMMUNICATION_EXPRESSION:
				setPostCommunicationExpression((Expression)newValue);
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
			case PooslPackage.SEND_STATEMENT__PORT_DESCRIPTOR:
				setPortDescriptor((PortDescriptor)null);
				return;
			case PooslPackage.SEND_STATEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PooslPackage.SEND_STATEMENT__ARGUMENTS:
				getArguments().clear();
				return;
			case PooslPackage.SEND_STATEMENT__POST_COMMUNICATION_EXPRESSION:
				setPostCommunicationExpression((Expression)null);
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
			case PooslPackage.SEND_STATEMENT__PORT_DESCRIPTOR:
				return portDescriptor != null;
			case PooslPackage.SEND_STATEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PooslPackage.SEND_STATEMENT__ARGUMENTS:
				return arguments != null && !arguments.isEmpty();
			case PooslPackage.SEND_STATEMENT__POST_COMMUNICATION_EXPRESSION:
				return postCommunicationExpression != null;
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
		result.append(')');
		return result.toString();
	}

} //SendStatementImpl
