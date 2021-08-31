/*******************************************************************************
 * Copyright (c) 2021 TNO/ESI
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    TNO/ESI - initial API and implementation
 *    Obeo - refactoring
 *******************************************************************************/
package org.eclipse.poosl.xtext.ui.references;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.poosl.AssignmentExpression;
import org.eclipse.poosl.DataClass;
import org.eclipse.poosl.DataMethod;
import org.eclipse.poosl.Declaration;
import org.eclipse.poosl.Instance;
import org.eclipse.poosl.InstanceParameter;
import org.eclipse.poosl.InstantiableClass;
import org.eclipse.poosl.MessageParameter;
import org.eclipse.poosl.MessageSignature;
import org.eclipse.poosl.NewExpression;
import org.eclipse.poosl.OutputVariable;
import org.eclipse.poosl.PooslPackage.Literals;
import org.eclipse.poosl.Port;
import org.eclipse.poosl.PortReference;
import org.eclipse.poosl.ProcessClass;
import org.eclipse.poosl.ProcessMethod;
import org.eclipse.poosl.ProcessMethodCall;
import org.eclipse.poosl.ReceiveStatement;
import org.eclipse.poosl.SendStatement;
import org.eclipse.poosl.Variable;
import org.eclipse.poosl.VariableExpression;
import org.eclipse.poosl.xtext.helpers.PooslReferenceHelper;
import org.eclipse.text.edits.ReplaceEdit;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.ui.refactoring.IRefactoringUpdateAcceptor;
import org.eclipse.xtext.ui.refactoring.impl.DefaultReferenceUpdater;
import org.eclipse.xtext.ui.refactoring.impl.StatusWrapper;
import org.eclipse.xtext.util.ITextRegion;

/**
 * The PooslReferenceUpdater.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
@SuppressWarnings("restriction")
public class PooslReferenceUpdater extends DefaultReferenceUpdater {

    @Override
    protected void createReferenceUpdate(EObject referringElement, URI referringResourceURI, EReference reference, int indexInList, EObject newTargetElement,
            IRefactoringUpdateAcceptor updateAcceptor) {

        if (reference == null) {

            // --- Message References -------

            if (referringElement instanceof SendStatement) {
                String newReferenceText = ((MessageSignature) newTargetElement).getName();
                Iterable<String> usedNames = PooslReferenceHelper.getExistingSendSignatureNamesByPortAndNumberOfParameters((SendStatement) referringElement, EcoreUtil2.getURI(newTargetElement));
                createTextChange(referringElement, referringResourceURI, indexInList, updateAcceptor, Literals.SEND_STATEMENT__NAME, newReferenceText, usedNames);

            } else if (referringElement instanceof ReceiveStatement) {
                String newReferenceText = ((MessageSignature) newTargetElement).getName();
                Iterable<String> usedNames = PooslReferenceHelper.getExistingReceiveSignatureNamesByPortAndNumberOfParameters((ReceiveStatement) referringElement, EcoreUtil2.getURI(newTargetElement));
                createTextChange(referringElement, referringResourceURI, indexInList, updateAcceptor, Literals.RECEIVE_STATEMENT__NAME, newReferenceText, usedNames);

                // --- Process Method References -------

            } else if (referringElement instanceof ProcessMethodCall) {
                String newReferenceText = ((ProcessMethod) newTargetElement).getName();
                Iterable<String> usedNames = PooslReferenceHelper.getProcessMethodNamesByNumberOfInputAndOutputArguments((ProcessMethodCall) referringElement, EcoreUtil2.getURI(newTargetElement));
                createTextChange(referringElement, referringResourceURI, indexInList, updateAcceptor, Literals.PROCESS_METHOD_CALL__METHOD, newReferenceText, usedNames);

                // --- Port References -------

            } else if (referringElement instanceof PortReference) {
                String newReferenceText = ((Port) newTargetElement).getName();
                Iterable<String> usedNames = PooslReferenceHelper.getPortNames(referringElement, EcoreUtil2.getURI(newTargetElement));
                createTextChange(referringElement, referringResourceURI, indexInList, updateAcceptor, Literals.PORT_REFERENCE__PORT, newReferenceText, usedNames);

                // --- Class References -------

            } else if (referringElement instanceof ProcessClass) {
                String newReferenceText = ((ProcessClass) newTargetElement).getName();
                Iterable<String> usedNames = PooslReferenceHelper.getInstantiableClassNames(referringElement, EcoreUtil2.getURI(newTargetElement));
                createTextChange(referringElement, referringResourceURI, indexInList, updateAcceptor, Literals.PROCESS_CLASS__SUPER_CLASS, newReferenceText, usedNames);

            } else if (referringElement instanceof DataClass) {
                String newReferenceText = ((DataClass) newTargetElement).getName();
                Iterable<String> usedNames = PooslReferenceHelper.getDataClassNames(referringElement, EcoreUtil2.getURI(newTargetElement));
                createTextChange(referringElement, referringResourceURI, indexInList, updateAcceptor, Literals.DATA_CLASS__SUPER_CLASS, newReferenceText, usedNames);

            } else if (referringElement instanceof Instance) {
                String newReferenceText = ((InstantiableClass) newTargetElement).getName();
                Iterable<String> usedNames = PooslReferenceHelper.getInstantiableClassNames(referringElement, EcoreUtil2.getURI(newTargetElement));
                createTextChange(referringElement, referringResourceURI, indexInList, updateAcceptor, Literals.INSTANCE__CLASS_DEFINITION, newReferenceText, usedNames);

            } else if (referringElement instanceof NewExpression) {
                String newReferenceText = ((DataClass) newTargetElement).getName();
                Iterable<String> usedNames = PooslReferenceHelper.getDataClassNames(referringElement, EcoreUtil2.getURI(newTargetElement));
                createTextChange(referringElement, referringResourceURI, indexInList, updateAcceptor, Literals.NEW_EXPRESSION__DATA_CLASS, newReferenceText, usedNames);

            } else if (referringElement instanceof DataMethod) {
                String newReferenceText = ((DataClass) newTargetElement).getName();
                Iterable<String> usedNames = PooslReferenceHelper.getDataClassNames(referringElement, EcoreUtil2.getURI(newTargetElement));
                createTextChange(referringElement, referringResourceURI, indexInList, updateAcceptor, Literals.DATA_METHOD__RETURN_TYPE, newReferenceText, usedNames);

            } else if (referringElement instanceof Declaration) {
                String newReferenceText = ((DataClass) newTargetElement).getName();
                Iterable<String> usedNames = PooslReferenceHelper.getDataClassNames(referringElement, EcoreUtil2.getURI(newTargetElement));
                createTextChange(referringElement, referringResourceURI, indexInList, updateAcceptor, Literals.DECLARATION__TYPE, newReferenceText, usedNames);

            } else if (referringElement instanceof MessageParameter) {
                String newReferenceText = ((DataClass) newTargetElement).getName();
                Iterable<String> usedNames = PooslReferenceHelper.getDataClassNames(referringElement, EcoreUtil2.getURI(newTargetElement));
                createTextChange(referringElement, referringResourceURI, indexInList, updateAcceptor, Literals.MESSAGE_PARAMETER__TYPE, newReferenceText, usedNames);

                // --- Variable References -------

            } else if (referringElement instanceof OutputVariable) {
                String newReferenceText = ((Variable) newTargetElement).getName();
                Iterable<String> usedNames = PooslReferenceHelper.getVariableNames((OutputVariable) referringElement, EcoreUtil2.getURI(newTargetElement));
                createTextChange(referringElement, referringResourceURI, indexInList, updateAcceptor, Literals.OUTPUT_VARIABLE__VARIABLE, newReferenceText, usedNames);

            } else if (referringElement instanceof VariableExpression) {
                String newReferenceText = ((Variable) newTargetElement).getName();
                Iterable<String> usedNames = PooslReferenceHelper.getVariableNames((VariableExpression) referringElement, EcoreUtil2.getURI(newTargetElement));
                createTextChange(referringElement, referringResourceURI, indexInList, updateAcceptor, Literals.VARIABLE_EXPRESSION__VARIABLE, newReferenceText, usedNames);

            } else if (referringElement instanceof AssignmentExpression) {
                String newReferenceText = ((Variable) newTargetElement).getName();
                Iterable<String> usedNames = PooslReferenceHelper.getVariableNames((AssignmentExpression) referringElement, EcoreUtil2.getURI(newTargetElement));
                createTextChange(referringElement, referringResourceURI, indexInList, updateAcceptor, Literals.ASSIGNMENT_EXPRESSION__VARIABLE, newReferenceText, usedNames);

            } else if (referringElement instanceof InstanceParameter) {
                String newReferenceText = ((Variable) newTargetElement).getName();
                Iterable<String> usedNames = PooslReferenceHelper.getVariableNames((InstanceParameter) referringElement, EcoreUtil2.getURI(newTargetElement));
                createTextChange(referringElement, referringResourceURI, indexInList, updateAcceptor, Literals.INSTANCE_PARAMETER__PARAMETER, newReferenceText, usedNames);
            }

        } else {
            super.createReferenceUpdate(referringElement, referringResourceURI, reference, indexInList, newTargetElement, updateAcceptor);
        }
    }

    private void createTextChange(EObject referringElement, URI referringResourceURI, int indexInList, IRefactoringUpdateAcceptor updateAcceptor, EAttribute structFeature, String newReferenceText,
            Iterable<String> usedNames) {
        if (!getTransientValueService().isValueInListTransient(referringElement, indexInList, structFeature)) {
            ITextRegion referenceTextRegion = getLocationInFileProvider().getFullTextRegion(referringElement, structFeature, indexInList);
            String temp = verifyUniqueName(usedNames, newReferenceText);

            if (temp == null) {
                updateAcceptor.getRefactoringStatus().add(RefactoringStatus.ERROR, "Refactoring introduces a name conflict.", referringElement, referenceTextRegion);
            }

            TextEdit referenceEdit = new ReplaceEdit(referenceTextRegion.getOffset(), referenceTextRegion.getLength(), newReferenceText);
            updateAcceptor.accept(referringResourceURI, referenceEdit);
        }
    }

    private String verifyUniqueName(Iterable<String> usedNames, String newReferenceText) {
        if (usedNames != null) {
            for (String name : usedNames) {
                if (name.equals(newReferenceText)) {
                    return null;
                }
            }
        }
        return newReferenceText;
    }

    @Override
    protected List<IReferenceDescription> resolveReferenceProxies(ResourceSet resourceSet, Collection<IReferenceDescription> values, StatusWrapper status, IProgressMonitor monitor) {
        List<IReferenceDescription> unresolvedDescriptions = null;
        for (IReferenceDescription referenceDescription : values) {
            if (monitor.isCanceled()) {
                throw new OperationCanceledException();
            }
            EObject sourceEObject = resourceSet.getEObject(referenceDescription.getSourceEObjectUri(), true);
            if (sourceEObject == null) {
                handleCannotLoadReferringElement(referenceDescription, status);
            } else {
                // this should not be necessary. see
                // https://bugs.eclipse.org/bugs/show_bug.cgi?id=385408
                if (sourceEObject.eIsProxy()) {
                    sourceEObject = EcoreUtil.resolve(sourceEObject, sourceEObject.eResource());
                    if (sourceEObject.eIsProxy()) {
                        handleCannotLoadReferringElement(referenceDescription, status);
                    }
                }

                if (referenceDescription.getEReference() == null)
                    continue;

                EObject resolvedReference = resolveReference(sourceEObject, referenceDescription);
                if (resolvedReference == null || resolvedReference.eIsProxy())
                    handleCannotResolveExistingReference(sourceEObject, referenceDescription, status);
                else
                    continue;
            }
            if (unresolvedDescriptions == null)
                unresolvedDescriptions = newArrayList();
            unresolvedDescriptions.add(referenceDescription);
        }
        return (unresolvedDescriptions == null) ? Collections.<IReferenceDescription> emptyList() : unresolvedDescriptions;
    }
}
