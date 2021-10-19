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
package org.eclipse.poosl.rotalumisclient.breakpoint;

import java.text.MessageFormat;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.LineBreakpoint;
import org.eclipse.poosl.rotalumisclient.PooslConstants;

/**
 * The PooslLineBreakpoint.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslLineBreakpoint extends LineBreakpoint {

    private static final String MARKER_ID = "org.eclipse.poosl.rotalumisclient.pooslLineBreakpointMarker"; //$NON-NLS-1$

    private static final String MESSAGE_PATTERN = "Poosl Line Breakpoint: {0} [line: {1}]";

    /**
     * Sets the resource and the line of this breakpoint into marker.
     *
     * @param resource
     *     to breakpoint
     * @param lineNumber
     *     of breakpoint
     * @throws DebugException
     *     if fails to mark
     */
    public void markLine(final IResource resource, final int lineNumber) throws DebugException {
        run(getMarkerRule(resource), monitor -> {
            IMarker marker = resource.createMarker(MARKER_ID);
            setMarker(marker);
            marker.setAttribute(IBreakpoint.ENABLED, true);
            marker.setAttribute(IBreakpoint.PERSISTED, true);
            marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
            marker.setAttribute(IBreakpoint.ID, getModelIdentifier());
            marker.setAttribute(IMarker.MESSAGE,
                    MessageFormat.format(MESSAGE_PATTERN, resource.getName(), lineNumber));
        });
    }

    @Override
    public String getModelIdentifier() {
        return PooslConstants.DEBUG_MODEL_ID;
    }
}
