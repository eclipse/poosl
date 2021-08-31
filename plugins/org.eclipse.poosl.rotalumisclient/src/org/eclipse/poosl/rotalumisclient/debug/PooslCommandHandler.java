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
package org.eclipse.poosl.rotalumisclient.debug;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.poosl.rotalumisclient.PooslConstants;

/**
 * The PooslCommandHandler.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslCommandHandler implements IHandler {

    @Override
    public void addHandlerListener(IHandlerListener handlerListener) {
        // Listeners are not supported by this command handler
    }

    @Override
    public void dispose() {
        // Nothing to dispose
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        String param = event.getParameter(PooslConstants.COMMAND_STEPTYPE);
        PooslDebugTarget debugTarget = PooslDebugHelper.getCurrentDebugTarget();
        if (debugTarget != null && debugTarget.isSuspended() && !debugTarget.isTerminated()) {
            if (param.equals(PooslConstants.COMMAND_STEPTYPE_STEP)) {
                debugTarget.step();
            } else if (param.equals(PooslConstants.COMMAND_STEPTYPE_TIME_STEP)) {
                debugTarget.timeStep();
            } else if (param.equals(PooslConstants.COMMAND_STEPTYPE_COMMUNICATION_STEP)) {
                debugTarget.communicationStep();
            }
        }
        return null;
    }

    @Override
    public boolean isEnabled() {
        PooslDebugTarget debugTarget = PooslDebugHelper.getCurrentDebugTarget();
        if (debugTarget != null) {
            return debugTarget.isSuspended() && !debugTarget.isTerminated();
        }
        return false;
    }

    @Override
    public boolean isHandled() {
        return true;
    }

    @Override
    public void removeHandlerListener(IHandlerListener handlerListener) {
        // Listeners are not supported by this command handler
    }
}
