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

/**
 * The IPooslDebugInformer.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public interface IPooslDebugInformer {

    /**
     * Notifies the extension that it has been activated.
     * 
     * @param object
     *            the selected object in the debug tree.
     * @param message
     *            the debug event message
     */
    void debugSelectionChanged(ExternDebugItem object, ExternDebugMessage message);

    /**
     * Notifies that a debugging session ended.
     * 
     * @param launchID
     *            the session ID
     */
    void launchStopped(String launchID);

    /**
     * Notifies of an event during a debugging session.
     * 
     * @param lastMessage
     *            the debug event message
     */
    void lastMessageChanged(ExternDebugMessage lastMessage);

    /**
     * Notifies that a debugging session started.
     * 
     * @param object
     *            the start message
     */
    void launchStart(ExternLaunchStartMessage object);
}
