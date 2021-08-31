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
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.poosl.sirius.helpers.ColorGraphHelper;
import org.eclipse.poosl.sirius.helpers.DiagramDebugNote;
import org.eclipse.poosl.sirius.helpers.GraphicalEditorHelper;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

/**
 * The ColorChannelCommand.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class ColorChannelCommand extends RecordingCommand {
    private static final String COMMAND_LABEL = "Color Representations";

    private List<DRepresentationDescriptor> descriptors;

    private Map<String, PooslDrawMessage> messages;

    private Map<String, PathCalculator> pathCalculators;

    /**
     * Specific command to delete the given representations.
     * 
     * @param session
     *            the session on which to delete the {@link DRepresentationDescriptor} s. WARNING : can only delete
     *            {@link DRepresentationDescriptor}s owned by this {@link Session} because we have one
     *            TransactionalEditingDomain per {@link Session}.
     * @param descriptors
     *            {@link Set} of {@link DRepresentationDescriptor}s to delete.
     * @param pathCalculators
     * @param messages
     */
    public ColorChannelCommand(Session session, List<DRepresentationDescriptor> descriptors, Map<String, PooslDrawMessage> messages, Map<String, PathCalculator> pathCalculators) {
        super(session.getTransactionalEditingDomain(), COMMAND_LABEL);
        this.descriptors = descriptors;
        this.messages = messages;
        this.pathCalculators = pathCalculators;
    }

    public ColorChannelCommand(Session session, DRepresentationDescriptor descriptor, Map<String, PooslDrawMessage> messages, Map<String, PathCalculator> pathCalculators) {
        this(session, new ArrayList<DRepresentationDescriptor>(), messages, pathCalculators);
        this.descriptors.add(descriptor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        for (DRepresentationDescriptor descriptor : descriptors) {
            String launchId = GraphicalEditorHelper.getLaunchIdFromDocumentation(descriptor.getDocumentation());
            PooslDrawMessage drawMessage = messages.get(launchId);
            PathCalculator pathCalculator = (pathCalculators.get(launchId) == null) ? (new PathCalculator("")) : pathCalculators.get(launchId); //$NON-NLS-1$
            DRepresentation representation = descriptor.getRepresentation();
            ColorGraphHelper.colorChannel(representation, drawMessage, pathCalculator);
            DiagramDebugNote debugNote = new DiagramDebugNote(representation);
            debugNote.setDescription(drawMessage);
        }
    }
}
