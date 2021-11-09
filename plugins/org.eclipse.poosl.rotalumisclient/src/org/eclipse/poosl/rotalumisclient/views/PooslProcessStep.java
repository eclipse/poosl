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

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.DebugException;
import org.eclipse.poosl.rotalumisclient.debug.PooslDebugTarget;
import org.eclipse.poosl.rotalumisclient.debug.PooslThread;

/**
 * PooslProcessStep utility.
 *
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class PooslProcessStep {

    private static final ILog LOGGER = Platform.getLog(PooslProcessStep.class);

    private PooslProcessStep() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Call process step on thread.
     * <p>
     * Only applicable for PooslDebugTarget: no effect otherwise.
     * </p>
     *
     * @param thread
     *     to process
     */
    public static void doProcessStep(PooslThread thread) {
        if (thread != null && thread.getDebugTarget() instanceof PooslDebugTarget) {
            PooslDebugTarget pTarget = (PooslDebugTarget) thread.getDebugTarget();
            try {
                pTarget.processStep(thread.getName());
            } catch (DebugException e) {
                LOGGER.warn("Could not get thread name.", e);
            }
        }
    }

}
