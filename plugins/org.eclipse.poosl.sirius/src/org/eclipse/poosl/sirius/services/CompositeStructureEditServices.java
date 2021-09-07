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
package org.eclipse.poosl.sirius.services;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.poosl.Channel;
import org.eclipse.poosl.Instance;
import org.eclipse.poosl.InstancePort;
import org.eclipse.poosl.Port;
import org.eclipse.poosl.sirius.helpers.CreationHelper;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.impl.DRepresentationElementImpl;

/**
 * Services used in diagrams to edit composite structures.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">arjan mooij</a>
 *
 */
public class CompositeStructureEditServices {

    public void createConnection(EObject source, DDiagramElement sourceView, DDiagramElement targetView) {
        CreationHelper.createConnection(getModelElement(sourceView.getTarget(), sourceView), targetView, //
                getModelElement(targetView.getTarget(), targetView));
        AbstractServices.validateRepresentation(source, targetView);
    }

    public void deleteConnectionEnd(Channel element, EObject view, EObject port, EObject portParent) {
        CreationHelper.deleteChannelConnection(element, port, portParent);
        AbstractServices.validateRepresentation(element, view);
    }

    public void reconnectConnection(Channel self, AbstractDNode sourceView, AbstractDNode targetView) {
        CreationHelper.reconnectConnection(self, targetView, //
                sourceView.getTarget(), ((AbstractDNode) sourceView.eContainer()).getTarget(), //
                targetView.getTarget(), ((AbstractDNode) targetView.eContainer()).getTarget());
        AbstractServices.validateRepresentation(self, targetView);
    }

    private static EObject getModelElement(EObject model, EObject diagramelement) {
        if (!(model instanceof Port)) {
            return (model instanceof EObject) ? (EObject) model : null;
        }

        EObject sourceContainer = null;
        if (diagramelement instanceof EObject) {
            EObject containerElement = diagramelement.eContainer();
            if (containerElement instanceof DRepresentationElementImpl) {
                sourceContainer = ((DRepresentationElementImpl) containerElement).getTarget();
            }
        }

        if (!(sourceContainer instanceof Instance)) {
            return (model instanceof EObject) ? (EObject) model : null;
        }

        InstancePort newinstanceport = CreationHelper.createInstancePort(//
                (Instance) sourceContainer, (Port) model);

        // XXX Suspicious save
        // Instance port is not attach to session yet as container is Channel.
        SessionManager.INSTANCE.getSession(sourceContainer).save(new NullProgressMonitor());
        return newinstanceport;

    }
}
