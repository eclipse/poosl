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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.poosl.rotalumisclient.extension.ExternDebugMessage;
import org.eclipse.poosl.sirius.helpers.GraphicalEditorHelper;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.UIJob;

/**
 * This class colors representation with debug information.
 * <p>
 * It is meant to be a singleton accessed by the activator.
 * </p>
 *
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class GraphicalDebugUpdater {
    private static final ILog LOGGER = Platform.getLog(GraphicalDebugUpdater.class);

    private static final Map<String, List<URI>> LOCKED_FILES = new HashMap<>();

    /**
     * This map contains messages that need still need to be drawn. When the
     * message is drawn it is removed from this
     * map to avoid the overhead of redrawing
     */
    private final ConcurrentHashMap<String, PooslDrawMessage> drawMessages = new ConcurrentHashMap<>();

    /**
     * This map contains the last message of the launch. When a new
     * communication diagram is opened it retrieves the
     * last message from this map.
     */
    private final ConcurrentHashMap<String, PooslDrawMessage> lastMessages = new ConcurrentHashMap<>();

    /**
     * This set contains which launches are not used yet and might need setup
     */
    private final Set<String> notSetupLaunches = new HashSet<>();

    private final Map<String, Map<String, String>> instancePortMappings = new HashMap<>();

    private final Map<String, PathCalculator> pathCalculators = new HashMap<>();

    private final UpdateAllDiagramsViewJob updateJob = new UpdateAllDiagramsViewJob();

    public void addMessage(ExternDebugMessage message) {
        String launchID = message.getLaunch();
        Map<String, String> instancePortMap = instancePortMappings.get(launchID);
        if (instancePortMap != null) {
            PooslDrawMessage pooslDrawMessage = new PooslDrawMessage(message, instancePortMap);
            updateMessageCounters(pooslDrawMessage);
            drawMessages.put(launchID, pooslDrawMessage);
            lastMessages.put(launchID, pooslDrawMessage);

            // No need to start updatejob at all if no representations/diagrams
            // exist
            Map<Session, Set<DRepresentationDescriptor>> session2Descriptors = UpdateHelper
                    .getLaunchRepresentations(drawMessages.keySet());
            if (!session2Descriptors.isEmpty()) {
                drawMessages();
            }
        } else {
            LOGGER.warn(
                    "Received message but there is no instanceport mapping to calculate messagepath, it's likely that the message was received after the debug sessions was terminated.");
        }
    }

    private void updateMessageCounters(PooslDrawMessage pooslDrawMessage) {
        PathCalculator calculator = getPathCalculator(pooslDrawMessage.getMessage().getLaunch());
        calculator.addMessage(pooslDrawMessage);
    }

    private PathCalculator getPathCalculator(String launchID) {
        PathCalculator calc = pathCalculators.get(launchID);
        if (calc == null) {
            calc = new PathCalculator(launchID);
            pathCalculators.put(calc.launchID, calc);
        }
        return calc;
    }

    private void drawMessages() {
        PlatformUI.getWorkbench().getDisplay().asyncExec(() -> updateJob.schedule(200));
    }

    public void launchStopped(String launchID) {
        pathCalculators.remove(launchID);
        drawMessages.remove(launchID);
        lastMessages.remove(launchID);
        instancePortMappings.remove(launchID);
        LOCKED_FILES.remove(launchID);
        verifyLockedFiles();
    }

    public static void verifyLockedFiles() {
        try {
            List<String> currentLaunches = GraphicalEditorHelper.getCurrentLaunchIDs();
            Iterator<String> iterator = LOCKED_FILES.keySet().iterator();
            while (iterator.hasNext()) {
                String launchID = iterator.next();
                if (!currentLaunches.contains(launchID)) {
                    iterator.remove();
                }
            }
        } catch (CoreException e) {
            LOGGER.warn("Could not get current launches.", e);
        }
    }

    /**
     * Colors the representation with debug information.
     *
     * @param session
     *     to update
     * @param descriptor
     *     of representation
     */
    public void draw(Session session, DRepresentationDescriptor descriptor) {
        if (descriptor != null) {
            Job singleUpdateJob = new UpdateSingleDiagramViewJob(session, descriptor);
            UpdateHelper.getPooslShell().getDisplay().asyncExec(() -> singleUpdateJob.schedule());
        } else {
            LOGGER.warn("No debug representation found to show.");
        }
    }

    private class UpdateAllDiagramsViewJob extends UIJob {
        UpdateAllDiagramsViewJob() {
            super("Updating All Diagrams");
        }

        @Override
        public IStatus runInUIThread(IProgressMonitor monitor) {
            Map<String, PooslDrawMessage> messages = new HashMap<>();

            Iterator<String> iterator = drawMessages.keySet().iterator();
            while (iterator.hasNext()) {
                PooslDrawMessage drawMessage = drawMessages.remove(iterator.next());
                messages.put(drawMessage.getMessage().getLaunch(), drawMessage);
            }

            Map<Session, Set<DRepresentationDescriptor>> session2Descriptors = UpdateHelper
                    .getLaunchRepresentations(messages.keySet());

            for (Entry<Session, Set<DRepresentationDescriptor>> entry : session2Descriptors
                    .entrySet()) {
                Session session = entry.getKey();
                Set<DRepresentationDescriptor> descriptors = entry.getValue();

                ArrayList<DRepresentationDescriptor> diagrams2Color = new ArrayList<>();

                IEditingSession editingSession = SessionUIManager.INSTANCE.getUISession(session);
                if (editingSession != null) {
                    for (DRepresentationDescriptor descriptor : descriptors) {
                        DialectEditor editor = editingSession
                                .getEditor(descriptor.getRepresentation());
                        if (editor != null) {
                            diagrams2Color.add(descriptor);
                        }
                    }
                }

                IStatus result = colorChannel(session, diagrams2Color, messages, pathCalculators);
                if (result.getSeverity() > IStatus.WARNING) {
                    return result;
                }

            }

            return Status.OK_STATUS;
        }
    }

    private class UpdateSingleDiagramViewJob extends UIJob {
        private DRepresentationDescriptor descriptor;

        private Session session;

        UpdateSingleDiagramViewJob(Session session, DRepresentationDescriptor descriptor) {
            super("Updating Single Diagram");
            this.session = session;
            this.descriptor = descriptor;
        }

        @Override
        public IStatus runInUIThread(IProgressMonitor monitor) {
            String launchID = GraphicalEditorHelper
                    .getLaunchIdFromDocumentation(descriptor.getDocumentation());
            PooslDrawMessage toDrawMessage = lastMessages.get(launchID);
            Map<String, PooslDrawMessage> messages = new HashMap<>();
            messages.put(launchID, toDrawMessage);
            return colorChannel(session, Arrays.asList(descriptor), messages, pathCalculators);
        }
    }

    private static IStatus colorChannel(
            Session session, List<DRepresentationDescriptor> descriptors,
            Map<String, PooslDrawMessage> messages, Map<String, PathCalculator> pathCalculators) {
        try {
            session.getTransactionalEditingDomain().getCommandStack().execute(
                    new ColorChannelCommand(session, descriptors, messages, pathCalculators));
            // CHECKSTYLE.OFF: IllegalCatch // EDT command stack is not explicit
        } catch (Exception e) {
            // CHECKSTYLE.ON: IllegalCatch
            return Status.CANCEL_STATUS;

        }
        return Status.OK_STATUS;
    }

    public void launchStarted(
            String launchID, Map<String, String> instancePortMap, List<URI> files) {
        notSetupLaunches.add(launchID);
        instancePortMappings.put(launchID, instancePortMap);
        LOCKED_FILES.put(launchID, files);
    }

    public Map<String, List<URI>> getLockedFiles() {
        return LOCKED_FILES;
    }

    public boolean isSetup(String launchID) {
        return !notSetupLaunches.remove(launchID);
    }
}
