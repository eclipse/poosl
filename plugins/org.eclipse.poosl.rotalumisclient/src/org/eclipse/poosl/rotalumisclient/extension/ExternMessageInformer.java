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
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.poosl.generatedxmlclasses.TCommunicationEvent;
import org.eclipse.poosl.rotalumisclient.PooslConstants;
import org.eclipse.poosl.rotalumisclient.debug.PooslDebugTarget;

/**
 * The ExternMessageInformer.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class ExternMessageInformer {
    private static final Logger LOGGER = Logger.getLogger(ExternMessageInformer.class.getName());

    public void executeInformMessage(ILaunch launch, TCommunicationEvent event) {
        IDebugTarget target = launch.getDebugTarget();

        if (target instanceof PooslDebugTarget) {
            PooslDebugTarget pooslTarget = (PooslDebugTarget) target;
            if (!pooslTarget.isEdited()) {
                try {
                    String launchid = launch.getLaunchConfiguration()
                            .getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_PORT, ""); //$NON-NLS-1$
                    String modelpath = launch.getLaunchConfiguration()
                            .getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_RELATIVE_PATH, ""); //$NON-NLS-1$
                    ExternDebugMessage externMassage = new ExternDebugMessage(launchid, modelpath,
                            event);
                    for (IPooslDebugInformer extension : ExtensionHelper.getExtensions()) {
                        executeExtensionMessage(extension, externMassage);
                    }
                } catch (InstantiationException | CoreException e) {
                    LOGGER.log(Level.WARNING, e.getMessage(), e);
                }
            }
        }
    }

    private void executeExtensionMessage(
            final IPooslDebugInformer o, final ExternDebugMessage message) {
        ISafeRunnable runnable = new ISafeRunnable() {
            @Override
            public void handleException(Throwable e) {
                LOGGER.log(Level.FINE, "Exception in client" + e.getMessage());
            }

            @Override
            public void run() throws Exception {
                o.lastMessageChanged(message);
            }
        };
        SafeRunner.run(runnable);
    }
}
