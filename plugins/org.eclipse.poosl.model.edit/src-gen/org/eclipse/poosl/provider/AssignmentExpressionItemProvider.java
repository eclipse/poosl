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
package org.eclipse.poosl.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.poosl.AssignmentExpression;
import org.eclipse.poosl.PooslFactory;
import org.eclipse.poosl.PooslPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.poosl.AssignmentExpression} object. <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class AssignmentExpressionItemProvider extends ExpressionItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public AssignmentExpressionItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addVariablePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Variable feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addVariablePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_AssignmentExpression_variable_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_AssignmentExpression_variable_feature", "_UI_AssignmentExpression_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        PooslPackage.Literals.ASSIGNMENT_EXPRESSION__VARIABLE, true, false, true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
     * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION);
        }
        return childrenFeatures;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EStructuralFeature getChildFeature(Object object, Object child) {
        // Check the type of the specified child object and return the proper feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

    /**
     * This returns AssignmentExpression.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/AssignmentExpression")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((AssignmentExpression) object).getVariable();
        return label == null || label.length() == 0 ? getString("_UI_AssignmentExpression_type") : //$NON-NLS-1$
                getString("_UI_AssignmentExpression_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached children and by creating
     * a viewer notification, which it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(AssignmentExpression.class)) {
        case PooslPackage.ASSIGNMENT_EXPRESSION__EXPRESSION:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
            return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that can be created
     * under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION, PooslFactory.eINSTANCE.createExpressionSequence()));

        newChildDescriptors.add(createChildParameter(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION, PooslFactory.eINSTANCE.createExpressionSequenceRoundBracket()));

        newChildDescriptors.add(createChildParameter(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION, PooslFactory.eINSTANCE.createAssignmentExpression()));

        newChildDescriptors.add(createChildParameter(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION, PooslFactory.eINSTANCE.createCurrentTimeExpression()));

        newChildDescriptors.add(createChildParameter(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION, PooslFactory.eINSTANCE.createDataMethodCallExpression()));

        newChildDescriptors.add(createChildParameter(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION, PooslFactory.eINSTANCE.createIfExpression()));

        newChildDescriptors.add(createChildParameter(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION, PooslFactory.eINSTANCE.createSwitchExpression()));

        newChildDescriptors.add(createChildParameter(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION, PooslFactory.eINSTANCE.createNewExpression()));

        newChildDescriptors.add(createChildParameter(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION, PooslFactory.eINSTANCE.createReturnExpression()));

        newChildDescriptors.add(createChildParameter(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION, PooslFactory.eINSTANCE.createSelfExpression()));

        newChildDescriptors.add(createChildParameter(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION, PooslFactory.eINSTANCE.createVariableExpression()));

        newChildDescriptors.add(createChildParameter(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION, PooslFactory.eINSTANCE.createWhileExpression()));

        newChildDescriptors.add(createChildParameter(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION, PooslFactory.eINSTANCE.createBinaryOperatorExpression()));

        newChildDescriptors.add(createChildParameter(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION, PooslFactory.eINSTANCE.createUnaryOperatorExpression()));

        newChildDescriptors.add(createChildParameter(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION, PooslFactory.eINSTANCE.createBooleanConstant()));

        newChildDescriptors.add(createChildParameter(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION, PooslFactory.eINSTANCE.createCharacterConstant()));

        newChildDescriptors.add(createChildParameter(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION, PooslFactory.eINSTANCE.createIntegerConstant()));

        newChildDescriptors.add(createChildParameter(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION, PooslFactory.eINSTANCE.createNilConstant()));

        newChildDescriptors.add(createChildParameter(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION, PooslFactory.eINSTANCE.createRealConstant()));

        newChildDescriptors.add(createChildParameter(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION, PooslFactory.eINSTANCE.createFloatConstant()));

        newChildDescriptors.add(createChildParameter(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION, PooslFactory.eINSTANCE.createStringConstant()));

        newChildDescriptors.add(createChildParameter(PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION, PooslFactory.eINSTANCE.createEnvironmentConstant()));
    }

}
