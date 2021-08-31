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
package org.eclipse.poosl.sirius.debug;

import java.util.Set;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

/**
 * The DeleteRepresentationCommand.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class DeleteRepresentationCommand extends RecordingCommand {
    private Set<DRepresentationDescriptor> descriptors;

    private Session session;

    /**
     * Specific command to delete the given representations.
     * 
     * @param session
     *            the session on which to delete the {@link DRepresentationDescriptor} s. WARNING : can only delete
     *            {@link DRepresentationDescriptor}s owned by this {@link Session} because we have one
     *            TransactionalEditingDomain per {@link Session}.
     * @param descriptors
     *            {@link Set} of {@link DRepresentationDescriptor}s to delete.
     */
    public DeleteRepresentationCommand(Session session, Set<DRepresentationDescriptor> descriptors) {
        super(session.getTransactionalEditingDomain(), "Delete representations");
        this.session = session;
        this.descriptors = descriptors;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        for (DRepresentationDescriptor descriptor : descriptors) {
            if (descriptor != null) {
                DialectManager.INSTANCE.deleteRepresentation(descriptor, session);
            }
        }
    }
}
