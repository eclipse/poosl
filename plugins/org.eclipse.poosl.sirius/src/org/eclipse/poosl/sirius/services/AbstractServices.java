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

import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.poosl.sirius.helpers.CreationHelper;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.IEditorPart;

/**
 * The AbstractServices.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
@SuppressWarnings("checkstyle:HideUtilityClassConstructor") // not applicable for ODesign Services
public class AbstractServices {

    /** Prefix for name in copy operation. */
    public static final String COPYOF = "CopyOf"; //$NON-NLS-1$

    private static final String BUNDLERESOURCE = "bundleresource"; //$NON-NLS-1$

    /**
     * Verify if an object comes from a built-in model.
     * 
     * @param object
     *            to test
     * @return true if built-in
     */
    public static boolean isBundleResource(EObject object) {
        // XXX Suspicious:
        // Default definition may not come from library
        // but from preferences.
        // It should be assumed that such library is not editable.
        return object.eResource().getURI().scheme().equals(BUNDLERESOURCE);
    }

    /**
     * Triggers a validation on associated representation when change is detected.
     * 
     * @param element
     *            edited
     * @param view
     *            to validate
     */
    public static void validateRepresentation(Object element, Object view) {
        DRepresentation representation = getRepresentation(view);
        if (representation == null || element == null) {
            return; // nothing to validate
        }
        Session session = getSession(element);

        if (session == null) {
            return; // nothing to validate
        }

        IEditorPart part = DialectUIManager.INSTANCE.openEditor(session, representation, new NullProgressMonitor());
        if (!(part instanceof DialectEditor)) {
            return; // not applicable.
        }
        session.addListener(new SessionListener() {
            @Override
            public void notify(int changeKind) {
                if (changeKind == SessionListener.SEMANTIC_CHANGE) {
                    ((DialectEditor) part).validateRepresentation();
                    session.removeListener(this);
                }
            }
        });

    }

    private static DRepresentation getRepresentation(Object view) {
        if (view instanceof DDiagramElement) {
            DDiagramElement diagramelement = (DDiagramElement) view;
            return diagramelement.getParentDiagram();
        } else if (view instanceof DRepresentation) {
            return (DRepresentation) view;
        } else if (view instanceof EObject) {
            return getRepresentation(((EObject) view).eContainer());
        }

        return null;
    }

    private static Session getSession(Object element) {
        Resource resource = ((EObject) element).eResource();
        Session session = SessionManager.INSTANCE.getSession((EObject) element);
        session = (session == null && resource != null) ? SessionManager.INSTANCE.getSession(resource) : session;
        if (session == null) {
            // XXX This fallback makes no sense.
            Collection<Session> sessions = SessionManager.INSTANCE.getSessions();
            if (sessions.size() == 1 && sessions.iterator().hasNext()) {
                session = sessions.iterator().next();
            }
        }
        return session;
    }

    /**
     * Deletes an element.
     * 
     * @param it
     *            to delete
     * @param containerView
     *            of deleted element
     */
    public static void deletePooslObject(EObject it, EObject containerView) {
        SiriusUtil.delete(it);
        CreationHelper.saveChanges(containerView);
        validateRepresentation(containerView, containerView);
    }
}
