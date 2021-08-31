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
package org.eclipse.poosl.xtext.importing;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.poosl.AssignmentExpression;
import org.eclipse.poosl.DataClass;
import org.eclipse.poosl.DataMethod;
import org.eclipse.poosl.Declaration;
import org.eclipse.poosl.Instance;
import org.eclipse.poosl.InstanceParameter;
import org.eclipse.poosl.MessageParameter;
import org.eclipse.poosl.NewExpression;
import org.eclipse.poosl.OutputVariable;
import org.eclipse.poosl.PortReference;
import org.eclipse.poosl.ProcessClass;
import org.eclipse.poosl.ProcessMethodCall;
import org.eclipse.poosl.ReceiveStatement;
import org.eclipse.poosl.SendStatement;
import org.eclipse.poosl.VariableExpression;
import org.eclipse.poosl.xtext.helpers.PooslReferenceHelper;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.impl.DefaultReferenceDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionStrategy;
import org.eclipse.xtext.util.IAcceptor;

/**
 * The PooslResourceDescriptionStrategy.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslResourceDescriptionStrategy extends DefaultResourceDescriptionStrategy {

    @Override
    public boolean createReferenceDescriptions(EObject from, URI exportedContainerURI, IAcceptor<IReferenceDescription> acceptor) {

        // --- Message References -------

        if (from instanceof SendStatement) {
            validateAndAccept(from, exportedContainerURI, acceptor, PooslReferenceHelper.getSendSignatureDescription((SendStatement) from));
        } else if (from instanceof ReceiveStatement) {
            validateAndAccept(from, exportedContainerURI, acceptor, PooslReferenceHelper.getReceiveSignatureDescription((ReceiveStatement) from));
        } else if (from instanceof ProcessMethodCall) {
            validateAndAccept(from, exportedContainerURI, acceptor, PooslReferenceHelper.getProcessMethodDescription((ProcessMethodCall) from));
        }

        // --- Port References -------

        else if (from instanceof PortReference) {
            validateAndAccept(from, exportedContainerURI, acceptor, PooslReferenceHelper.getPortDescription((PortReference) from));
        }

        // --- Class References -------

        else if (from instanceof ProcessClass) {
            validateAndAccept(from, exportedContainerURI, acceptor, PooslReferenceHelper.getProcessClassDescription((ProcessClass) from));
        } else if (from instanceof DataClass) {
            validateAndAccept(from, exportedContainerURI, acceptor, PooslReferenceHelper.getDataClassDescription((DataClass) from));
        } else if (from instanceof Instance) {
            validateAndAccept(from, exportedContainerURI, acceptor, PooslReferenceHelper.getInstantiableClassDescription((Instance) from));
        } else if (from instanceof NewExpression) {
            validateAndAccept(from, exportedContainerURI, acceptor, PooslReferenceHelper.getDataClassDescription((NewExpression) from));
        } else if (from instanceof Declaration) {
            validateAndAccept(from, exportedContainerURI, acceptor, PooslReferenceHelper.getDataClassDescription((Declaration) from));
        } else if (from instanceof MessageParameter) {
            validateAndAccept(from, exportedContainerURI, acceptor, PooslReferenceHelper.getDataClassDescription((MessageParameter) from));
        } else if (from instanceof DataMethod) {
            validateAndAccept(from, exportedContainerURI, acceptor, PooslReferenceHelper.getDataClassDescription((DataMethod) from));
        }

        // --- Variable References -------

        else if (from instanceof AssignmentExpression) {
            validateAndAccept(from, exportedContainerURI, acceptor, PooslReferenceHelper.getVariableDescription((AssignmentExpression) from));
        } else if (from instanceof VariableExpression) {
            validateAndAccept(from, exportedContainerURI, acceptor, PooslReferenceHelper.getVariableDescription((VariableExpression) from));
        } else if (from instanceof OutputVariable) {
            validateAndAccept(from, exportedContainerURI, acceptor, PooslReferenceHelper.getVariableDescription((OutputVariable) from));
        } else if (from instanceof InstanceParameter) {
            validateAndAccept(from, exportedContainerURI, acceptor, PooslReferenceHelper.getVariableDescription((InstanceParameter) from));
        }
        return super.createReferenceDescriptions(from, exportedContainerURI, acceptor);
    }

    private void validateAndAccept(EObject fromObject, URI exportedContainerURI, IAcceptor<IReferenceDescription> acceptor, IEObjectDescription call) {
        if (call != null) {
            URI from = EcoreUtil.getURI(fromObject);
            URI to = call.getEObjectURI();
            if (to != null && !from.trimFragment().equals(to.trimFragment())) {
                acceptor.accept(new DefaultReferenceDescription(from, to, null, -1, exportedContainerURI));
            }
        }
    }
}
