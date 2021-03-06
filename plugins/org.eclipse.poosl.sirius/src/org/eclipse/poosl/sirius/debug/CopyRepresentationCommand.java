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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.poosl.sirius.helpers.DiagramNameHelper;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

/**
 * The CopyRepresentationCommand.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class CopyRepresentationCommand extends RecordingCommand {
    private static final String COMMAND_LABEL = "Copy Communication Diagram";

    private final Collection<DRepresentationDescriptor> descriptors;

    private final Session session;

    private final String launch;

    private final String instance;

    private final List<DRepresentationDescriptor> createdDescriptors = new ArrayList<>();

    /**
     * Specific command to copy the given representations.
     * 
     * @param domain
     *     the current editing domain.
     * @param descriptors
     *     the representations to copy.
     * @param session
     *     the current session.
     * @param instance
     * @param launch
     */
    public CopyRepresentationCommand(TransactionalEditingDomain domain,
            Collection<DRepresentationDescriptor> descriptors, Session session, String launch,
            String instance) {
        super(domain, COMMAND_LABEL);
        this.descriptors = descriptors;
        this.launch = launch;
        this.session = session;
        this.instance = instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (descriptors == null || session == null) {
            return;
        }

        for (DRepresentationDescriptor descriptor : descriptors) {
            DRepresentation createdRepresentation = DialectManager.INSTANCE.copyRepresentation(
                    descriptor, DiagramNameHelper.getCommunicationDiagramNameFromOriginal(
                            descriptor.getName(), instance),
                    session, null);
            DRepresentationDescriptor createdDescriptor = new DRepresentationQuery(
                    createdRepresentation).getRepresentationDescriptor();
            String documentation = launch + "," + instance; //$NON-NLS-1$
            createdDescriptor.setDocumentation(documentation);
            createdDescriptors.add(createdDescriptor);
        }
    }

    public List<DRepresentationDescriptor> getCreatedDescriptors() {
        return createdDescriptors;
    }
}
