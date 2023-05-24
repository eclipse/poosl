/*******************************************************************************
 * Copyright (c) 2023 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.poosl.sirius.elk;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.eclipse.elk.alg.layered.options.LayeredOptions;
import org.eclipse.elk.core.math.ElkPadding;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.PortLabelPlacement;
import org.eclipse.elk.core.service.LayoutMapping;
import org.eclipse.elk.graph.ElkConnectableShape;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.ElkPort;
import org.eclipse.elk.graph.util.ElkGraphUtil;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.poosl.Channel;
import org.eclipse.poosl.Port;
import org.eclipse.poosl.ProcessClass;
import org.eclipse.poosl.sirius.helpers.GraphicalEditorHelper;
import org.eclipse.sirius.diagram.elk.GmfLayoutCommand;
import org.eclipse.sirius.diagram.elk.IELKLayoutExtension;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Extension of the default ELK Layered layout to manage corner cases.
 *
 * @author wpiers
 */
public class PooslELKLayoutExtension implements IELKLayoutExtension {

    private static final String[] AFFECTED_POOSL_DIAGRAMS = new String[] { "Cluster diagram" };

    private static final double ELK_DEFAULT_PADDING = 10.;

    private static final int NODE_MIN_HEIGHT = 100;

    private static final int NODE_MIN_WIDTH = 200;

    private static final double NODE_HORIZONTAL_SPACING = 100.;

    private LayoutMapping layoutMapping;

    private List<ElkEdge> reversedEdges;

    @Override
    public void beforeELKLayout(LayoutMapping aLayoutMapping) {
        reversedEdges = new ArrayList<>();
        if (isPooslDiagram(aLayoutMapping)) {
            layoutMapping = aLayoutMapping;

            // Edges
            layoutMapping.getGraphMap().entrySet().stream()
                    .filter(entry -> entry.getKey() instanceof ElkEdge).forEach(entry -> {
                        fixEdgeLayout((ElkEdge) entry.getKey(), entry.getValue());
                    });

            // Nodes
            layoutMapping.getGraphMap().entrySet().stream()
                    .filter(entry -> entry.getKey() instanceof ElkNode).forEach(entry -> {
                        ElkNode elkNode = (ElkNode) entry.getKey();
                        if (isRootNode(elkNode)) {
                            fixRootNodeLayout(elkNode, entry.getValue());
                        } else {
                            fixNodeLayout(elkNode, entry.getValue());
                        }
                    });
        }
    }

    protected void fixRootNodeLayout(ElkNode elkNode, Object value) {
        // Fix node spacing
        elkNode.setProperty(LayeredOptions.SPACING_NODE_NODE_BETWEEN_LAYERS,
                NODE_HORIZONTAL_SPACING);

        // Fix port label placement
        elkNode.setProperty(CoreOptions.PORT_LABELS_PLACEMENT, EnumSet
                .of(PortLabelPlacement.OUTSIDE, PortLabelPlacement.NEXT_TO_PORT_IF_POSSIBLE));
        elkNode.setProperty(CoreOptions.NODE_LABELS_PADDING, new ElkPadding(10.));

        // Set padding for System ports when necessary
        if (elkNode.getPorts().stream().anyMatch(port -> !port.getIncomingEdges().isEmpty())) {
            elkNode.setProperty(CoreOptions.PADDING, new ElkPadding(ELK_DEFAULT_PADDING,
                    NODE_HORIZONTAL_SPACING, ELK_DEFAULT_PADDING, ELK_DEFAULT_PADDING));
        }
    }

    protected void fixNodeLayout(ElkNode elkNode, Object value) {
        // Fix node minimum size
        if (!(getSemanticTarget(value) instanceof Channel)) {
            elkNode.setProperty(CoreOptions.NODE_SIZE_MINIMUM,
                    new KVector(NODE_MIN_WIDTH, NODE_MIN_HEIGHT));
        }

        // Fix port label placement
        elkNode.setProperty(CoreOptions.PORT_LABELS_PLACEMENT,
                EnumSet.of(PortLabelPlacement.INSIDE, PortLabelPlacement.NEXT_TO_PORT_IF_POSSIBLE));
    }

    protected void fixEdgeLayout(ElkEdge elkEdge, Object value) {
        // Change edge direction according to POOSL sent/received messages
        if (isEdgeToBeReversed(elkEdge)) {
            reversedEdges.add(elkEdge);
            reverseEdge(elkEdge);
        }
    }

    @Override
    public void afterELKLayout(LayoutMapping aLayoutMapping) {
        if (isPooslDiagram(aLayoutMapping)) {
            reversedEdges.forEach(it -> reverseEdge(it));
        }
    }

    @Override
    public void afterGMFCommandApplied(
            GmfLayoutCommand gmfLayoutCommand, LayoutMapping aLayoutMapping) {
    }

    private boolean isPooslDiagram(LayoutMapping aLayoutMapping) {
        Object parentElement = aLayoutMapping.getParentElement();
        if (parentElement instanceof EditPart
                && ((EditPart) parentElement).getModel() instanceof View
                && ((View) ((EditPart) parentElement).getModel())
                        .getElement() instanceof DSemanticDecorator) {
            DSemanticDecorator semanticDecorator = (DSemanticDecorator) ((View) ((EditPart) parentElement)
                    .getModel()).getElement();
            boolean inPooslDiagram = GraphicalEditorHelper.isInPooslDiagram(semanticDecorator,
                    AFFECTED_POOSL_DIAGRAMS);
            return inPooslDiagram;
        }
        return false;
    }

    private boolean isRootNode(ElkNode node) {
        return layoutMapping.getLayoutGraph().equals(node.getParent());
    }

    protected boolean isEdgeToBeReversed(ElkEdge elkEdge) {
        if (elkEdge.isHyperedge()) {
            return false;
        }
        ElkPort sourceElkPort = ElkGraphUtil.getSourcePort(elkEdge);
        ElkPort targetElkPort = ElkGraphUtil.getTargetPort(elkEdge);
        return (sourceElkPort != null && isInvalidPort(PortKind.SOURCE, sourceElkPort))
                || (targetElkPort != null && isInvalidPort(PortKind.TARGET, targetElkPort));
    }

    private void reverseEdge(ElkEdge elkEdge) throws IllegalArgumentException {
        if (elkEdge.isHyperedge()) {
            throw new IllegalArgumentException(
                    "The method reverseEdge does not handle \"hyperedge\"");
        }
        ElkConnectableShape source = elkEdge.getSources().get(0);
        ElkConnectableShape target = elkEdge.getTargets().get(0);
        elkEdge.getSources().remove(source);
        elkEdge.getTargets().remove(target);
        elkEdge.getSources().add(target);
        elkEdge.getTargets().add(source);

        for (ElkEdgeSection section : elkEdge.getSections()) {
            double oldStartX = section.getStartX();
            double oldStartY = section.getStartY();
            section.setStartX(section.getEndX());
            section.setStartY(section.getEndY());
            section.setEndX(oldStartX);
            section.setEndY(oldStartY);

            ECollections.reverse(section.getBendPoints());
        }
    }

    enum PortKind {
        SOURCE, TARGET, UNDEFINED
    }

    private boolean isInvalidPort(PortKind expected, ElkPort sourceElkPort) {
        boolean res = false;
        EObject semanticTarget = getSemanticTarget(layoutMapping.getGraphMap().get(sourceElkPort));
        if (semanticTarget instanceof Port) {
            PortKind portKind = getPortKind((Port) semanticTarget);
            res = !(portKind == PortKind.UNDEFINED || portKind == expected);
        }
        return res;
    }

    private PortKind getPortKind(Port port) {
        PortKind res = PortKind.UNDEFINED;
        if (port.eContainer() instanceof ProcessClass) {
            ProcessClass processClass = (ProcessClass) port.eContainer();
            long sendMessages = processClass.getSendMessages().stream()
                    .filter(ms -> ms.getPort().getPort().equals(port.getName())).count();
            long receiveMessages = processClass.getReceiveMessages().stream()
                    .filter(ms -> ms.getPort().getPort().equals(port.getName())).count();
            if (sendMessages > receiveMessages) {
                res = PortKind.SOURCE;
            } else if (receiveMessages > sendMessages) {
                res = PortKind.TARGET;
            }
        }
        return res;

    }

    private static EObject getSemanticTarget(Object o) {
        if (o instanceof GraphicalEditPart) {
            Object view = ((GraphicalEditPart) o).getModel();
            if (view instanceof View) {
                EObject eObject = ((View) view).getElement();
                if (eObject instanceof DSemanticDecorator) {
                    return ((DSemanticDecorator) eObject).getTarget();
                }
            }
        }
        return null;
    }

}
