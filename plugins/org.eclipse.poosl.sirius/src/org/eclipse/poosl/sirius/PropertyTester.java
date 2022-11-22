/*******************************************************************************
 * Copyright (c) 2022 Obeo
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
package org.eclipse.poosl.sirius;

import org.eclipse.poosl.PooslPackage;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.ui.IEditorPart;

public class PropertyTester extends org.eclipse.core.expressions.PropertyTester {

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object,
     *     java.lang.String,
     *     java.lang.Object[], java.lang.Object)
     */
    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        boolean res = false;
        if ("isPooslEditor".equals(property) && receiver instanceof IEditorPart) {
            String editorId = ((IEditorPart) receiver).getEditorSite().getId();
            if ("org.eclipse.poosl.xtext.Poosl".equals(editorId)) {
                res = true;
            } else if (receiver instanceof DDiagramEditor && ((DDiagramEditor) receiver)
                    .getRepresentation() instanceof DSemanticDiagram) {
                DSemanticDiagram diagram = (DSemanticDiagram) ((DDiagramEditor) receiver)
                        .getRepresentation();
                return diagram.getTarget().eClass().getEPackage() instanceof PooslPackage;
            }
        }
        return res;
    }

}
