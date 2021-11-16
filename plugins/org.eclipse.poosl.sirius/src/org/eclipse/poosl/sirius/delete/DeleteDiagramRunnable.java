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
package org.eclipse.poosl.sirius.delete;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.poosl.sirius.debug.DeleteRepresentationCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

/**
 * The DeleteDiagramRunnable.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class DeleteDiagramRunnable implements IRunnableWithProgress {
    private static final String DELETE_DIAGRAMS = "Deleting Diagrams";

    private static final String DELETE_DIAGRAM = "Deleting Diagram";

    private final Map<Session, Set<DRepresentationDescriptor>> session2Descriptors;

    public DeleteDiagramRunnable(Map<Session, Set<DRepresentationDescriptor>> session2Descriptors) {
        this.session2Descriptors = session2Descriptors;
    }

    @Override
    public void run(IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException {
        try {
            String taskName = session2Descriptors.size() > 1 ? DELETE_DIAGRAM : DELETE_DIAGRAMS;
            monitor.beginTask(taskName, session2Descriptors.size());
            for (Entry<Session, Set<DRepresentationDescriptor>> entry : session2Descriptors
                    .entrySet()) {
                Session session = entry.getKey();
                Set<DRepresentationDescriptor> descriptors = entry.getValue();
                Command deleteDRepresentationsCmd = new DeleteRepresentationCommand(session,
                        descriptors);
                session.getTransactionalEditingDomain().getCommandStack()
                        .execute(deleteDRepresentationsCmd);
            }
        } finally {
            monitor.done();
        }
    }
}
