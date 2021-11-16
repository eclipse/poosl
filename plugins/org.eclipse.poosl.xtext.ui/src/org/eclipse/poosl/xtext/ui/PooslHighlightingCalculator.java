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
package org.eclipse.poosl.xtext.ui;

import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.poosl.Annotation;
import org.eclipse.poosl.AssignmentExpression;
import org.eclipse.poosl.Channel;
import org.eclipse.poosl.DataClass;
import org.eclipse.poosl.DataMethod;
import org.eclipse.poosl.DataMethodCallExpression;
import org.eclipse.poosl.Declaration;
import org.eclipse.poosl.FloatConstant;
import org.eclipse.poosl.Instance;
import org.eclipse.poosl.InstanceParameter;
import org.eclipse.poosl.InstancePort;
import org.eclipse.poosl.InstantiableClass;
import org.eclipse.poosl.IntegerConstant;
import org.eclipse.poosl.MessageParameter;
import org.eclipse.poosl.MessageSignature;
import org.eclipse.poosl.NewExpression;
import org.eclipse.poosl.OutputVariable;
import org.eclipse.poosl.PooslPackage.Literals;
import org.eclipse.poosl.Port;
import org.eclipse.poosl.PortReference;
import org.eclipse.poosl.ProcessMethod;
import org.eclipse.poosl.ProcessMethodCall;
import org.eclipse.poosl.RealConstant;
import org.eclipse.poosl.ReceiveStatement;
import org.eclipse.poosl.SendStatement;
import org.eclipse.poosl.Variable;
import org.eclipse.poosl.VariableExpression;
import org.eclipse.xtext.ide.editor.syntaxcoloring.HighlightingStyles;
import org.eclipse.xtext.ide.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.ide.editor.syntaxcoloring.ISemanticHighlightingCalculator;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.CancelIndicator;

/**
 * The PooslHighlightingCalculator.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslHighlightingCalculator implements ISemanticHighlightingCalculator {

    @Override
    public void provideHighlightingFor(
            XtextResource resource, IHighlightedPositionAcceptor acceptor, CancelIndicator cancel) {
        if (resource == null || resource.getParseResult() == null) {
            return;
        }

        TreeIterator<EObject> contents = resource.getAllContents();
        while (contents != null && contents.hasNext()) {
            EObject element = contents.next();

            // IDENTIFIER
            if (element instanceof Annotation) {
                // note: non-default style
                process(acceptor,
                        NodeModelUtils.findNodesForFeature(element, Literals.ANNOTATION__NAME),
                        HighlightingStyles.NUMBER_ID);
            } else if (element instanceof Channel) {
                process(acceptor, NodeModelUtils.findNodesForFeature(element,
                        Literals.CHANNEL__EXTERNAL_PORT));
            } else if (element instanceof InstantiableClass) {
                process(acceptor, NodeModelUtils.findNodesForFeature(element,
                        Literals.INSTANTIABLE_CLASS__NAME));
                process(acceptor, NodeModelUtils.findNodesForFeature(element,
                        Literals.PROCESS_CLASS__SUPER_CLASS));
            } else if (element instanceof DataClass) {
                process(acceptor,
                        NodeModelUtils.findNodesForFeature(element, Literals.DATA_CLASS__NAME));
                process(acceptor, NodeModelUtils.findNodesForFeature(element,
                        Literals.DATA_CLASS__SUPER_CLASS));
            } else if (element instanceof DataMethod) {
                process(acceptor, NodeModelUtils.findNodesForFeature(element,
                        Literals.DATA_METHOD_NAMED__NAME));
                process(acceptor, NodeModelUtils.findNodesForFeature(element,
                        Literals.DATA_METHOD__RETURN_TYPE));
            } else if (element instanceof Declaration) {
                process(acceptor,
                        NodeModelUtils.findNodesForFeature(element, Literals.DECLARATION__TYPE));
            } else if (element instanceof AssignmentExpression) {
                process(acceptor, NodeModelUtils.findNodesForFeature(element,
                        Literals.ASSIGNMENT_EXPRESSION__VARIABLE));
            } else if (element instanceof DataMethodCallExpression) {
                process(acceptor, NodeModelUtils.findNodesForFeature(element,
                        Literals.DATA_METHOD_CALL_EXPRESSION__NAME));
            } else if (element instanceof InstanceParameter) {
                process(acceptor, NodeModelUtils.findNodesForFeature(element,
                        Literals.INSTANCE_PARAMETER__PARAMETER));
            } else if (element instanceof Instance) {
                process(acceptor,
                        NodeModelUtils.findNodesForFeature(element, Literals.INSTANCE__NAME));
                process(acceptor, NodeModelUtils.findNodesForFeature(element,
                        Literals.INSTANCE__CLASS_DEFINITION));
            } else if (element instanceof InstancePort) {
                process(acceptor, NodeModelUtils.findNodesForFeature(element,
                        Literals.INSTANCE_PORT__INSTANCE));
                process(acceptor,
                        NodeModelUtils.findNodesForFeature(element, Literals.INSTANCE_PORT__PORT));
            } else if (element instanceof MessageParameter) {
                process(acceptor, NodeModelUtils.findNodesForFeature(element,
                        Literals.MESSAGE_PARAMETER__TYPE));
            } else if (element instanceof MessageSignature) {
                process(acceptor, NodeModelUtils.findNodesForFeature(element,
                        Literals.MESSAGE_SIGNATURE__PORT));
                process(acceptor, NodeModelUtils.findNodesForFeature(element,
                        Literals.MESSAGE_SIGNATURE__NAME));
            } else if (element instanceof NewExpression) {
                process(acceptor, NodeModelUtils.findNodesForFeature(element,
                        Literals.NEW_EXPRESSION__DATA_CLASS));
            } else if (element instanceof OutputVariable) {
                process(acceptor, NodeModelUtils.findNodesForFeature(element,
                        Literals.OUTPUT_VARIABLE__VARIABLE));
            } else if (element instanceof PortReference) {
                process(acceptor,
                        NodeModelUtils.findNodesForFeature(element, Literals.PORT_REFERENCE__PORT));
            } else if (element instanceof Port) {
                process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.PORT__NAME));
            } else if (element instanceof ProcessMethodCall) {
                process(acceptor, NodeModelUtils.findNodesForFeature(element,
                        Literals.PROCESS_METHOD_CALL__METHOD));
            } else if (element instanceof ProcessMethod) {
                process(acceptor,
                        NodeModelUtils.findNodesForFeature(element, Literals.PROCESS_METHOD__NAME));
            } else if (element instanceof ReceiveStatement) {
                process(acceptor, NodeModelUtils.findNodesForFeature(element,
                        Literals.RECEIVE_STATEMENT__NAME));
            } else if (element instanceof SendStatement) {
                process(acceptor,
                        NodeModelUtils.findNodesForFeature(element, Literals.SEND_STATEMENT__NAME));
            } else if (element instanceof VariableExpression) {
                process(acceptor, NodeModelUtils.findNodesForFeature(element,
                        Literals.VARIABLE_EXPRESSION__VARIABLE));
            } else if (element instanceof Variable) {
                process(acceptor,
                        NodeModelUtils.findNodesForFeature(element, Literals.VARIABLE__NAME));
            }

            // Float, Integer and Real
            else if (element instanceof FloatConstant) {
                process(acceptor,
                        NodeModelUtils.findNodesForFeature(element, Literals.FLOAT_CONSTANT__VALUE),
                        HighlightingStyles.NUMBER_ID);
            } else if (element instanceof IntegerConstant) {
                process(acceptor, NodeModelUtils.findNodesForFeature(element,
                        Literals.INTEGER_CONSTANT__VALUE), HighlightingStyles.NUMBER_ID);
            } else if (element instanceof RealConstant) {
                process(acceptor,
                        NodeModelUtils.findNodesForFeature(element, Literals.REAL_CONSTANT__VALUE),
                        HighlightingStyles.NUMBER_ID);
            }
        }
    }

    private void process(IHighlightedPositionAcceptor acceptor, List<INode> actualNodes) {
        process(acceptor, actualNodes, HighlightingStyles.DEFAULT_ID);
    }

    private void process(
            IHighlightedPositionAcceptor acceptor, List<INode> actualNodes, String highlighting) {
        for (INode actualNode : actualNodes) {
            acceptor.addPosition(actualNode.getOffset(), actualNode.getLength(), highlighting);
        }
    }
}
