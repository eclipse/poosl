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
package org.eclipse.poosl.rotalumisclient.extension;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.poosl.rotalumisclient.PooslConstants;

/**
 * The ExternLaunchStopInformer.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class ExternLaunchStopInformer {
    private static final Logger LOGGER = Logger.getLogger(ExternLaunchStopInformer.class.getName());

    @Execute
    public void executeInformLaunchStopped(ILaunch launch) {
        ILaunchConfiguration launchConfiguration = launch.getLaunchConfiguration();
        try {
            String id = launchConfiguration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_PORT, ""); //$NON-NLS-1$
            for (IPooslDebugInformer extension : ExtensionHelper.getExtensions()) {
                executeExtensionLaunch(extension, id);
            }
        } catch (CoreException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    private void executeExtensionLaunch(final IPooslDebugInformer o, final String launchID) {
        ISafeRunnable runnable = new ISafeRunnable() {
            @Override
            public void handleException(Throwable e) {
                LOGGER.log(Level.FINE, "Exception in client" + e.getMessage());
            }

            @Override
            public void run() throws Exception {
                o.launchStopped(launchID);
            }
        };
        SafeRunner.run(runnable);
    }
}
