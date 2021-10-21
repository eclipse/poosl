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
package org.eclipse.poosl.rotalumisclient.views;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.contexts.IDebugContextManager;
import org.eclipse.debug.ui.contexts.IDebugContextService;
import org.eclipse.poosl.rotalumisclient.PooslConstants;
import org.eclipse.poosl.rotalumisclient.debug.PooslDebugHelper;
import org.eclipse.poosl.rotalumisclient.debug.PooslDebugTarget;
import org.eclipse.poosl.rotalumisclient.debug.PooslThread;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.part.ViewPart;

/**
 * The ViewHelper.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class ViewHelper {
    private static final String SECONDID_SPLIT = "<>"; //$NON-NLS-1$

    private static final Logger LOGGER = Logger.getLogger(ViewHelper.class.getName());

    private ViewHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static IDebugContextService getDebugService(IWorkbenchPart part) {
        IWorkbenchWindow window = null;
        try {
            window = part.getSite().getWorkbenchWindow().getWorkbench().getActiveWorkbenchWindow();
        } catch (NullPointerException e) {
            // Unlikely, unless for unexpected configuration
            LOGGER.log(Level.WARNING,
                    "Could not get workbench window while getting debugContextService",
                    e.getCause());
        }
        IDebugContextManager contextManager = DebugUITools.getDebugContextManager();
        if (contextManager != null && window != null) {
            return contextManager.getContextService(window);
        }
        return null;
    }

    /**
     * If the secondID represents the thread it returns true.
     * 
     * This method is used by views that have a secondID to determine if they
     * should show information of the thread or
     * react to changes in the debug state. Views that have a secondID are
     * dedicated to show only information on a
     * specific thread.
     * <p>
     * Also see {@link ViewHelper#isTargetID(IDebugTarget, String)} and
     * {@link ViewHelper#getSecondId(PooslThread)}
     * </p>
     * 
     * @param thread
     *     The thread that contains new information
     * @param secondID
     *     secondid of the {@link ViewPart}
     * @return true if the secondid represents the thread
     * 
     */
    public static boolean isThreadID(PooslThread thread, String secondID) {
        String threadId = getSecondId(thread);
        return secondID == null || threadId.equals(secondID);
    }

    /**
     * If the secondID represents the IDebugTarget it returns true.
     * 
     * This method is used by views that have a secondid to determine if they
     * should react to changes in the debug
     * state. Views that have a secondid are dedicated to show only information
     * on a specific thread.
     * <p>
     * Also see {@link ViewHelper#isThreadID(PooslThread, String)} and
     * {@link ViewHelper#getSecondId(PooslThread)}
     * </p>
     * 
     * @param target
     * @param secondID
     * @return <true> if the id represents the target
     */
    public static boolean isTargetID(IDebugTarget target, String secondID) {
        try {
            String targetName = target.getName();
            return secondID == null || targetName.equals(getDebugTargetName(secondID));
        } catch (DebugException e) {
            LOGGER.log(Level.WARNING, "Could not compare secondary id with DebugTarget.", e);
        }
        return false;
    }

    /**
     * Returns the secondID of the given thread. The secondID contains the
     * debugtarget name and thread name. This
     * secondID is used by Views that only need to show information on a
     * specific thread.
     * 
     * @param thread
     *     {@link PooslThread}
     * @return If the target or thread name could not be retrieved it returns an
     *     empty string.
     */
    public static String getSecondId(PooslThread thread) {
        ILaunchConfiguration launchConfiguration = thread.getDebugTarget().getLaunch()
                .getLaunchConfiguration();

        String port = ""; //$NON-NLS-1$
        try {
            port = launchConfiguration
                    .getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_PORT, ""); //$NON-NLS-1$
        } catch (CoreException e) {
            LOGGER.log(Level.WARNING,
                    "Rotalumis Port could not be retrieved from the launch configuration.", e);
        }

        String id = ""; //$NON-NLS-1$
        try {
            id = PooslConstants.THREAD_VIEW_ID + port + SECONDID_SPLIT
                    + thread.getDebugTarget().getName() + SECONDID_SPLIT + thread.getName();
        } catch (DebugException e) {
            LOGGER.log(Level.SEVERE, "Thread name could not be read.", e);
        }
        return id;
    }

    /**
     * Get target name based on the secondID.
     * 
     * @param secondID
     * @return the target name
     */
    public static String getDebugTargetName(String secondID) {
        String targetName = ""; //$NON-NLS-1$
        if (secondID != null) {
            String[] result = secondID.split(SECONDID_SPLIT);
            if (result.length == 3) {
                targetName = result[1];
            }

        }
        return targetName;
    }

    /**
     * Get thread name based on the secondid.
     * 
     * @param secondID
     * @return the thread name
     */
    public static String getThreadName(String secondID) {
        String threadName = ""; //$NON-NLS-1$
        if (secondID != null) {
            String[] result = secondID.split(SECONDID_SPLIT);
            if (result.length == 3) {
                threadName = result[2];
            }
        }
        return threadName;
    }

    /**
     * If the thread is not yet set, get the thread represented by the secondid
     * using the events source only. Used by
     * the separate PETView and Variable view after the model is terminated and
     * restarted.
     * 
     * @param thread
     *     Current {@link PooslThread}
     * @param debugEvent
     *     {@link DebugEvent} that is triggered
     * @param secondID
     *     {@link String} secondid that represents the thread.
     * @return {@link PooslThread} thread
     */
    public static PooslThread getThreadFromEvent(
            PooslThread thread, DebugEvent debugEvent, String secondID) {
        PooslThread result = thread;
        if (result != null && result.isTerminated()) {
            result = null;
        }
        if (result == null && secondID != null) {
            if (debugEvent.getSource() instanceof PooslDebugTarget) {

                try {
                    PooslDebugTarget target = (PooslDebugTarget) debugEvent.getSource();
                    String targetName = target.getName();
                    if (targetName.equals(ViewHelper.getDebugTargetName(secondID))) {
                        result = PooslDebugHelper.getThreadByName(target.getThreads(),
                                ViewHelper.getThreadName(secondID));
                    }
                } catch (DebugException e) {
                    LOGGER.log(Level.WARNING, "Could not get name of the debugtarget.", e);
                }
            } else if (debugEvent.getSource() instanceof PooslThread) {
                PooslThread newThread = (PooslThread) debugEvent.getSource();
                result = (isThreadID(newThread, secondID)) ? newThread : null;
            }
        }
        return result;
    }
}
