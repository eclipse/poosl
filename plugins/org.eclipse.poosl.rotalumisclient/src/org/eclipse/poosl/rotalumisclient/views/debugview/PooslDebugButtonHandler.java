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
package org.eclipse.poosl.rotalumisclient.views.debugview;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.poosl.rotalumisclient.PooslConstants;
import org.eclipse.poosl.rotalumisclient.views.WindowCreater;
import org.eclipse.ui.PlatformUI;

/**
 * Button handler for the buttons that are shown at the top of the {@link PooslDebugView}.
 * 
 * @author Koen Staal
 *
 */
public class PooslDebugButtonHandler implements IHandler {

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        if (event.getCommand().getId().equals(PooslConstants.COMMAND_CLEAR_LAUNCHES)) {
            ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
            for (ILaunch launch : launchManager.getLaunches()) {
                if (launch.isTerminated()) {
                    launchManager.removeLaunch(launch);
                }
            }
        } else if (event.getCommand().getId().equals(PooslConstants.COMMAND_CLOSE_THREAD_WINDOWS)) {
            WindowCreater.closeAllThreadWindows(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage());
        }
        return null;
    }

    @Override
    public void addHandlerListener(IHandlerListener handlerListener) {
        // This button handler does not need listeners
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isHandled() {
        return true;
    }

    @Override
    public void removeHandlerListener(IHandlerListener handlerListener) {
        // This button handler does not need listeners
    }

    @Override
    public void dispose() {
        // Nothing to dispose
    }
}
