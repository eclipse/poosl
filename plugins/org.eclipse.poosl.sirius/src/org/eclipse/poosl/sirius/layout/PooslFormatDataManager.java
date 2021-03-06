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
package org.eclipse.poosl.sirius.layout;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey;
import org.eclipse.sirius.diagram.ui.tools.api.format.semantic.SiriusFormatDataManagerForSemanticElements;
import org.eclipse.sirius.diagram.ui.tools.internal.format.semantic.SemanticNodeFormatDataKey;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * PooslFormatDataManager drives by the semantic elements (EObject). Use for
 * layout duplication:
 * <ul>
 * <li>Right-mouse click -> Edit -> Copy format</li>
 * <li>Right-mouse click -> Edit -> Paste format/layout/style</li>
 * </ul>
 * 
 * @author Koen Staal
 */
@SuppressWarnings("restriction")
public class PooslFormatDataManager extends SiriusFormatDataManagerForSemanticElements {
    private static final PooslFormatDataManager INSTANCE = new PooslFormatDataManager();

    /**
     * Default constructor.
     */
    public PooslFormatDataManager() {
        // Nothing.
    }

    @Override
    public PooslFormatDataManager getInstance() {
        return INSTANCE;
    }

    /**
     * Creates {@link SemanticNodeFormatDataKey} for {@link AbstractDNode} and
     * {@link DDiagram}, and creates
     * {@link PooslEdgeFormatDataKey} for {@link DEdge}.
     */
    @Override
    public FormatDataKey createKey(final DSemanticDecorator semanticDecorator) {
        FormatDataKey result = null;
        final EObject realSemanticElement = semanticDecorator.getTarget();
        if (semanticDecorator instanceof DEdge) {
            result = new PooslEdgeFormatDataKey((DEdge) semanticDecorator);
        } else if (semanticDecorator instanceof AbstractDNode
                || semanticDecorator instanceof DDiagram) {
            result = new SemanticNodeFormatDataKey(realSemanticElement);
        }
        return result;
    }
}
