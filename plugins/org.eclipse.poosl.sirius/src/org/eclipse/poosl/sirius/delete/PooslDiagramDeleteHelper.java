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
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.ILog;

import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.poosl.sirius.debug.DiagramEditScheduler;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * The PooslDiagramDeleteHelper.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class PooslDiagramDeleteHelper {
    private static final String DELETE_DIAGRAMS_FAILED = "Could not delete the diagrams.";

    private static final ILog LOGGER = Platform.getLog(PooslDiagramDeleteHelper.class);

    private PooslDiagramDeleteHelper() {
    }

    /**
     * Wrapper for closeAndDeleteDiagrams.
     * 
     * @param shell
     * @param session
     * @param allResourceDiagrams
     */
    public static void closeAndDeleteDiagrams(Shell shell, Session session, Collection<DRepresentationDescriptor> allResourceDiagrams) {
        Map<Session, Set<DRepresentationDescriptor>> session2Descriptors = new HashMap<>();
        session2Descriptors.put(session, new HashSet<>(allResourceDiagrams));
        closeAndDeleteDiagrams(shell, session2Descriptors);
    }

    /**
     * Close any open diagrams and delete them using {@link CloseAndDeleteDiagramRunnable} and
     * {@link DeleteDiagramRunnable}.
     * 
     * @param shell
     * @param session2Descriptors
     */

    public static void closeAndDeleteDiagrams(Shell shell, final Map<Session, Set<DRepresentationDescriptor>> session2Descriptors) {
        try {
            if (containsRepresentations(session2Descriptors)) {
                IRunnableContext context = new ProgressMonitorDialog(shell);
                ISchedulingRule scheduler = new DiagramEditScheduler();
                PlatformUI.getWorkbench().getProgressService().runInUI(context, new CloseDiagramRunnable(session2Descriptors), scheduler);
                PlatformUI.getWorkbench().getProgressService().runInUI(context, new DeleteDiagramRunnable(session2Descriptors), scheduler);
            }
        } catch (InvocationTargetException | InterruptedException e) {
            LOGGER.warn(DELETE_DIAGRAMS_FAILED, e);
        }
    }

    /**
     * This method can be used to avoid unnecessary actions.
     * 
     * @param session2Descriptors
     * @return true if the maps contains representations
     */
    private static boolean containsRepresentations(Map<Session, Set<DRepresentationDescriptor>> session2Descriptors) {
        for (Entry<Session, Set<DRepresentationDescriptor>> entry : session2Descriptors.entrySet()) {
            Set<DRepresentationDescriptor> descriptors = entry.getValue();
            if (descriptors != null && !descriptors.isEmpty())
                return true;
        }
        return false;
    }
}
