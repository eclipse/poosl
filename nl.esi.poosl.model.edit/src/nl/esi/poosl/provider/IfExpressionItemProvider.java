/**
 */
package nl.esi.poosl.provider;


import java.util.Collection;
import java.util.List;

import nl.esi.poosl.IfExpression;
import nl.esi.poosl.PooslFactory;
import nl.esi.poosl.PooslPackage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link nl.esi.poosl.IfExpression} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class IfExpressionItemProvider extends ExpressionItemProvider
{
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IfExpressionItemProvider(AdapterFactory adapterFactory)
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
			childrenFeatures.add(PooslPackage.Literals.IF_EXPRESSION__CONDITION);
			childrenFeatures.add(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE);
			childrenFeatures.add(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE);
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
	 * This returns IfExpression.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object)
	{
		return overlayImage(object, getResourceLocator().getImage("full/obj16/IfExpression"));
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
		return getString("_UI_IfExpression_type");
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

		switch (notification.getFeatureID(IfExpression.class)) {
			case PooslPackage.IF_EXPRESSION__CONDITION:
			case PooslPackage.IF_EXPRESSION__THEN_CLAUSE:
			case PooslPackage.IF_EXPRESSION__ELSE_CLAUSE:
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
				(PooslPackage.Literals.IF_EXPRESSION__CONDITION,
				 PooslFactory.eINSTANCE.createExpressionSequence()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__CONDITION,
				 PooslFactory.eINSTANCE.createExpressionSequenceRoundBracket()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__CONDITION,
				 PooslFactory.eINSTANCE.createAssignmentExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__CONDITION,
				 PooslFactory.eINSTANCE.createCurrentTimeExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__CONDITION,
				 PooslFactory.eINSTANCE.createDataMethodCallExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__CONDITION,
				 PooslFactory.eINSTANCE.createIfExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__CONDITION,
				 PooslFactory.eINSTANCE.createSwitchExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__CONDITION,
				 PooslFactory.eINSTANCE.createNewExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__CONDITION,
				 PooslFactory.eINSTANCE.createReturnExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__CONDITION,
				 PooslFactory.eINSTANCE.createSelfExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__CONDITION,
				 PooslFactory.eINSTANCE.createVariableExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__CONDITION,
				 PooslFactory.eINSTANCE.createWhileExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__CONDITION,
				 PooslFactory.eINSTANCE.createBinaryOperatorExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__CONDITION,
				 PooslFactory.eINSTANCE.createUnaryOperatorExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__CONDITION,
				 PooslFactory.eINSTANCE.createBooleanConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__CONDITION,
				 PooslFactory.eINSTANCE.createCharacterConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__CONDITION,
				 PooslFactory.eINSTANCE.createIntegerConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__CONDITION,
				 PooslFactory.eINSTANCE.createNilConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__CONDITION,
				 PooslFactory.eINSTANCE.createRealConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__CONDITION,
				 PooslFactory.eINSTANCE.createFloatConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__CONDITION,
				 PooslFactory.eINSTANCE.createStringConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__CONDITION,
				 PooslFactory.eINSTANCE.createEnvironmentConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE,
				 PooslFactory.eINSTANCE.createExpressionSequence()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE,
				 PooslFactory.eINSTANCE.createExpressionSequenceRoundBracket()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE,
				 PooslFactory.eINSTANCE.createAssignmentExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE,
				 PooslFactory.eINSTANCE.createCurrentTimeExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE,
				 PooslFactory.eINSTANCE.createDataMethodCallExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE,
				 PooslFactory.eINSTANCE.createIfExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE,
				 PooslFactory.eINSTANCE.createSwitchExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE,
				 PooslFactory.eINSTANCE.createNewExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE,
				 PooslFactory.eINSTANCE.createReturnExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE,
				 PooslFactory.eINSTANCE.createSelfExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE,
				 PooslFactory.eINSTANCE.createVariableExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE,
				 PooslFactory.eINSTANCE.createWhileExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE,
				 PooslFactory.eINSTANCE.createBinaryOperatorExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE,
				 PooslFactory.eINSTANCE.createUnaryOperatorExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE,
				 PooslFactory.eINSTANCE.createBooleanConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE,
				 PooslFactory.eINSTANCE.createCharacterConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE,
				 PooslFactory.eINSTANCE.createIntegerConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE,
				 PooslFactory.eINSTANCE.createNilConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE,
				 PooslFactory.eINSTANCE.createRealConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE,
				 PooslFactory.eINSTANCE.createFloatConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE,
				 PooslFactory.eINSTANCE.createStringConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE,
				 PooslFactory.eINSTANCE.createEnvironmentConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE,
				 PooslFactory.eINSTANCE.createExpressionSequence()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE,
				 PooslFactory.eINSTANCE.createExpressionSequenceRoundBracket()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE,
				 PooslFactory.eINSTANCE.createAssignmentExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE,
				 PooslFactory.eINSTANCE.createCurrentTimeExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE,
				 PooslFactory.eINSTANCE.createDataMethodCallExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE,
				 PooslFactory.eINSTANCE.createIfExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE,
				 PooslFactory.eINSTANCE.createSwitchExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE,
				 PooslFactory.eINSTANCE.createNewExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE,
				 PooslFactory.eINSTANCE.createReturnExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE,
				 PooslFactory.eINSTANCE.createSelfExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE,
				 PooslFactory.eINSTANCE.createVariableExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE,
				 PooslFactory.eINSTANCE.createWhileExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE,
				 PooslFactory.eINSTANCE.createBinaryOperatorExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE,
				 PooslFactory.eINSTANCE.createUnaryOperatorExpression()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE,
				 PooslFactory.eINSTANCE.createBooleanConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE,
				 PooslFactory.eINSTANCE.createCharacterConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE,
				 PooslFactory.eINSTANCE.createIntegerConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE,
				 PooslFactory.eINSTANCE.createNilConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE,
				 PooslFactory.eINSTANCE.createRealConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE,
				 PooslFactory.eINSTANCE.createFloatConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE,
				 PooslFactory.eINSTANCE.createStringConstant()));

		newChildDescriptors.add
			(createChildParameter
				(PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE,
				 PooslFactory.eINSTANCE.createEnvironmentConstant()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection)
	{
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == PooslPackage.Literals.IF_EXPRESSION__CONDITION ||
			childFeature == PooslPackage.Literals.IF_EXPRESSION__THEN_CLAUSE ||
			childFeature == PooslPackage.Literals.IF_EXPRESSION__ELSE_CLAUSE;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
