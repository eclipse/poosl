/**
 */
package nl.esi.poosl.provider;


import java.util.Collection;
import java.util.List;

import nl.esi.poosl.PooslFactory;
import nl.esi.poosl.PooslPackage;
import nl.esi.poosl.SelStatement;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link nl.esi.poosl.SelStatement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SelStatementItemProvider extends StatementItemProvider
{
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SelStatementItemProvider(AdapterFactory adapterFactory)
	{
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
	{
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
	{
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(PooslPackage.Literals.SEL_STATEMENT__CLAUSES);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child)
	{
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns SelStatement.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object)
	{
		return overlayImage(object, getResourceLocator().getImage("full/obj16/SelStatement"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object)
	{
		return getString("_UI_SelStatement_type");
	}
	

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification)
	{
		updateChildren(notification);

		switch (notification.getFeatureID(SelStatement.class)) {
			case PooslPackage.SEL_STATEMENT__CLAUSES:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
	{
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.SEL_STATEMENT__CLAUSES,
				 PooslFactory.eINSTANCE.createStatementSequence()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.SEL_STATEMENT__CLAUSES,
				 PooslFactory.eINSTANCE.createStatementSequenceRoundBracket()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.SEL_STATEMENT__CLAUSES,
				 PooslFactory.eINSTANCE.createAbortStatement()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.SEL_STATEMENT__CLAUSES,
				 PooslFactory.eINSTANCE.createDelayStatement()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.SEL_STATEMENT__CLAUSES,
				 PooslFactory.eINSTANCE.createExpressionStatement()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.SEL_STATEMENT__CLAUSES,
				 PooslFactory.eINSTANCE.createGuardedStatement()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.SEL_STATEMENT__CLAUSES,
				 PooslFactory.eINSTANCE.createIfStatement()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.SEL_STATEMENT__CLAUSES,
				 PooslFactory.eINSTANCE.createSwitchStatement()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.SEL_STATEMENT__CLAUSES,
				 PooslFactory.eINSTANCE.createInterruptStatement()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.SEL_STATEMENT__CLAUSES,
				 PooslFactory.eINSTANCE.createParStatement()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.SEL_STATEMENT__CLAUSES,
				 PooslFactory.eINSTANCE.createProcessMethodCall()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.SEL_STATEMENT__CLAUSES,
				 PooslFactory.eINSTANCE.createReceiveStatement()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.SEL_STATEMENT__CLAUSES,
				 PooslFactory.eINSTANCE.createSendStatement()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.SEL_STATEMENT__CLAUSES,
				 PooslFactory.eINSTANCE.createSelStatement()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.SEL_STATEMENT__CLAUSES,
				 PooslFactory.eINSTANCE.createSkipStatement()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.SEL_STATEMENT__CLAUSES,
				 PooslFactory.eINSTANCE.createWhileStatement()));
	}

}
