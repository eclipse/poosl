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

import java.util.HashMap;
import java.util.Map;

/**
 * The PathCalculator.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PathCalculator {
    final String launchID;

    private final Map<String, PathCounter> pathCounters = new HashMap<>();

    public PathCalculator(String launchID) {
        this.launchID = launchID;
    }

    public void addMessage(PooslDrawMessage drawMessage) {
        for (MessagePath paths : drawMessage.getMessagePaths().values()) {
            getPathCounter(getPathId(paths.getOwner(), paths.getSender(), paths.getSenderPort()))
                    .addOutgoing();
            getPathCounter(
                    getPathId(paths.getOwner(), paths.getReceiver(), paths.getReceiverPort()))
                            .addIncoming();
        }
    }

    public PathCounter getPathCounter(String pathId) {
        PathCounter pathCounter = pathCounters.get(pathId);
        if (pathCounter == null) {
            pathCounter = new PathCounter(pathId);
            pathCounters.put(pathId, pathCounter);
        }
        return pathCounter;
    }

    public String getPathCounterLabel(String pathId) {
        PathCounter pCount = getPathCounter(pathId);
        return "? " + pCount.getIn() + " / ! " + pCount.getOut(); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public String getPathCounterLabel(String owner, String instance, String port) {
        return getPathCounterLabel(getPathId(owner, instance, port));
    }

    private String getPathId(String owner, String instance, String port) {
        return owner + "_" + instance + "_" + port; //$NON-NLS-1$ //$NON-NLS-2$
    }
}
