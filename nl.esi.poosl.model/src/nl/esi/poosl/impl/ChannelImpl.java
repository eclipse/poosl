/**
 */
package nl.esi.poosl.impl;

import java.util.Collection;

import nl.esi.poosl.Channel;
import nl.esi.poosl.InstancePort;
import nl.esi.poosl.PooslPackage;
import nl.esi.poosl.Port;

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
 * An implementation of the model object '<em><b>Channel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link nl.esi.poosl.impl.ChannelImpl#getExternalPort <em>External Port</em>}</li>
 *   <li>{@link nl.esi.poosl.impl.ChannelImpl#getInstancePorts <em>Instance Ports</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ChannelImpl extends AnnotableImpl implements Channel
{
	/**
	 * The cached value of the '{@link #getExternalPort() <em>External Port</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalPort()
	 * @generated
	 * @ordered
	 */
	protected Port externalPort;

	/**
	 * The cached value of the '{@link #getInstancePorts() <em>Instance Ports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstancePorts()
	 * @generated
	 * @ordered
	 */
	protected EList<InstancePort> instancePorts;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ChannelImpl()
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
		return PooslPackage.Literals.CHANNEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Port getExternalPort()
	{
		if (externalPort != null && externalPort.eIsProxy()) {
			InternalEObject oldExternalPort = (InternalEObject)externalPort;
			externalPort = (Port)eResolveProxy(oldExternalPort);
			if (externalPort != oldExternalPort) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PooslPackage.CHANNEL__EXTERNAL_PORT, oldExternalPort, externalPort));
			}
		}
		return externalPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Port basicGetExternalPort()
	{
		return externalPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setExternalPort(Port newExternalPort)
	{
		Port oldExternalPort = externalPort;
		externalPort = newExternalPort;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PooslPackage.CHANNEL__EXTERNAL_PORT, oldExternalPort, externalPort));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<InstancePort> getInstancePorts()
	{
		if (instancePorts == null) {
			instancePorts = new EObjectContainmentEList<InstancePort>(InstancePort.class, this, PooslPackage.CHANNEL__INSTANCE_PORTS);
		}
		return instancePorts;
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
			case PooslPackage.CHANNEL__INSTANCE_PORTS:
				return ((InternalEList<?>)getInstancePorts()).basicRemove(otherEnd, msgs);
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
			case PooslPackage.CHANNEL__EXTERNAL_PORT:
				if (resolve) return getExternalPort();
				return basicGetExternalPort();
			case PooslPackage.CHANNEL__INSTANCE_PORTS:
				return getInstancePorts();
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
			case PooslPackage.CHANNEL__EXTERNAL_PORT:
				setExternalPort((Port)newValue);
				return;
			case PooslPackage.CHANNEL__INSTANCE_PORTS:
				getInstancePorts().clear();
				getInstancePorts().addAll((Collection<? extends InstancePort>)newValue);
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
			case PooslPackage.CHANNEL__EXTERNAL_PORT:
				setExternalPort((Port)null);
				return;
			case PooslPackage.CHANNEL__INSTANCE_PORTS:
				getInstancePorts().clear();
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
			case PooslPackage.CHANNEL__EXTERNAL_PORT:
				return externalPort != null;
			case PooslPackage.CHANNEL__INSTANCE_PORTS:
				return instancePorts != null && !instancePorts.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ChannelImpl
