/**
 */
package nl.esi.poosl.impl;

import java.util.Collection;

import nl.esi.poosl.Expression;
import nl.esi.poosl.OutputVariable;
import nl.esi.poosl.PooslPackage;
import nl.esi.poosl.PortDescriptor;
import nl.esi.poosl.ReceiveStatement;

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
 * An implementation of the model object '<em><b>Receive Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.impl.ReceiveStatementImpl#getPortDescriptor <em>Port Descriptor</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.ReceiveStatementImpl#getName <em>Name</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.ReceiveStatementImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.ReceiveStatementImpl#getReceptionCondition <em>Reception Condition</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.ReceiveStatementImpl#getPostCommunicationExpression <em>Post Communication Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReceiveStatementImpl extends StatementImpl implements ReceiveStatement
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
	 * The cached value of the '{@link #getVariables() <em>Variables</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<OutputVariable> variables;

	/**
	 * The cached value of the '{@link #getReceptionCondition() <em>Reception Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReceptionCondition()
	 * @generated
	 * @ordered
	 */
	protected Expression receptionCondition;

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
	protected ReceiveStatementImpl()
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
		return PooslPackage.Literals.RECEIVE_STATEMENT;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PooslPackage.RECEIVE_STATEMENT__PORT_DESCRIPTOR, oldPortDescriptor, newPortDescriptor);
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
				msgs = ((InternalEObject)portDescriptor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PooslPackage.RECEIVE_STATEMENT__PORT_DESCRIPTOR, null, msgs);
			if (newPortDescriptor != null)
				msgs = ((InternalEObject)newPortDescriptor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PooslPackage.RECEIVE_STATEMENT__PORT_DESCRIPTOR, null, msgs);
			msgs = basicSetPortDescriptor(newPortDescriptor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.RECEIVE_STATEMENT__PORT_DESCRIPTOR, newPortDescriptor, newPortDescriptor));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.RECEIVE_STATEMENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<OutputVariable> getVariables()
	{
		if (variables == null) {
			variables = new EObjectContainmentEList<OutputVariable>(OutputVariable.class, this, PooslPackage.RECEIVE_STATEMENT__VARIABLES);
		}
		return variables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getReceptionCondition()
	{
		return receptionCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReceptionCondition(Expression newReceptionCondition, NotificationChain msgs)
	{
		Expression oldReceptionCondition = receptionCondition;
		receptionCondition = newReceptionCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PooslPackage.RECEIVE_STATEMENT__RECEPTION_CONDITION, oldReceptionCondition, newReceptionCondition);
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
	public void setReceptionCondition(Expression newReceptionCondition)
	{
		if (newReceptionCondition != receptionCondition) {
			NotificationChain msgs = null;
			if (receptionCondition != null)
				msgs = ((InternalEObject)receptionCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PooslPackage.RECEIVE_STATEMENT__RECEPTION_CONDITION, null, msgs);
			if (newReceptionCondition != null)
				msgs = ((InternalEObject)newReceptionCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PooslPackage.RECEIVE_STATEMENT__RECEPTION_CONDITION, null, msgs);
			msgs = basicSetReceptionCondition(newReceptionCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.RECEIVE_STATEMENT__RECEPTION_CONDITION, newReceptionCondition, newReceptionCondition));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PooslPackage.RECEIVE_STATEMENT__POST_COMMUNICATION_EXPRESSION, oldPostCommunicationExpression, newPostCommunicationExpression);
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
				msgs = ((InternalEObject)postCommunicationExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PooslPackage.RECEIVE_STATEMENT__POST_COMMUNICATION_EXPRESSION, null, msgs);
			if (newPostCommunicationExpression != null)
				msgs = ((InternalEObject)newPostCommunicationExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PooslPackage.RECEIVE_STATEMENT__POST_COMMUNICATION_EXPRESSION, null, msgs);
			msgs = basicSetPostCommunicationExpression(newPostCommunicationExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.RECEIVE_STATEMENT__POST_COMMUNICATION_EXPRESSION, newPostCommunicationExpression, newPostCommunicationExpression));
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
			case PooslPackage.RECEIVE_STATEMENT__PORT_DESCRIPTOR:
				return basicSetPortDescriptor(null, msgs);
			case PooslPackage.RECEIVE_STATEMENT__VARIABLES:
				return ((InternalEList<?>)getVariables()).basicRemove(otherEnd, msgs);
			case PooslPackage.RECEIVE_STATEMENT__RECEPTION_CONDITION:
				return basicSetReceptionCondition(null, msgs);
			case PooslPackage.RECEIVE_STATEMENT__POST_COMMUNICATION_EXPRESSION:
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
			case PooslPackage.RECEIVE_STATEMENT__PORT_DESCRIPTOR:
				return getPortDescriptor();
			case PooslPackage.RECEIVE_STATEMENT__NAME:
				return getName();
			case PooslPackage.RECEIVE_STATEMENT__VARIABLES:
				return getVariables();
			case PooslPackage.RECEIVE_STATEMENT__RECEPTION_CONDITION:
				return getReceptionCondition();
			case PooslPackage.RECEIVE_STATEMENT__POST_COMMUNICATION_EXPRESSION:
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
			case PooslPackage.RECEIVE_STATEMENT__PORT_DESCRIPTOR:
				setPortDescriptor((PortDescriptor)newValue);
				return;
			case PooslPackage.RECEIVE_STATEMENT__NAME:
				setName((String)newValue);
				return;
			case PooslPackage.RECEIVE_STATEMENT__VARIABLES:
				getVariables().clear();
				getVariables().addAll((Collection<? extends OutputVariable>)newValue);
				return;
			case PooslPackage.RECEIVE_STATEMENT__RECEPTION_CONDITION:
				setReceptionCondition((Expression)newValue);
				return;
			case PooslPackage.RECEIVE_STATEMENT__POST_COMMUNICATION_EXPRESSION:
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
			case PooslPackage.RECEIVE_STATEMENT__PORT_DESCRIPTOR:
				setPortDescriptor((PortDescriptor)null);
				return;
			case PooslPackage.RECEIVE_STATEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PooslPackage.RECEIVE_STATEMENT__VARIABLES:
				getVariables().clear();
				return;
			case PooslPackage.RECEIVE_STATEMENT__RECEPTION_CONDITION:
				setReceptionCondition((Expression)null);
				return;
			case PooslPackage.RECEIVE_STATEMENT__POST_COMMUNICATION_EXPRESSION:
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
			case PooslPackage.RECEIVE_STATEMENT__PORT_DESCRIPTOR:
				return portDescriptor != null;
			case PooslPackage.RECEIVE_STATEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PooslPackage.RECEIVE_STATEMENT__VARIABLES:
				return variables != null && !variables.isEmpty();
			case PooslPackage.RECEIVE_STATEMENT__RECEPTION_CONDITION:
				return receptionCondition != null;
			case PooslPackage.RECEIVE_STATEMENT__POST_COMMUNICATION_EXPRESSION:
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

} //ReceiveStatementImpl
