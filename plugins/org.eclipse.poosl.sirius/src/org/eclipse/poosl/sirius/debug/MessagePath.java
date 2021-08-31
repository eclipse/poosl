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

import org.eclipse.poosl.rotalumisclient.PooslConstants;

/**
 * The MessagePath.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class MessagePath {
    private final String owner;

    private final String sender;

    private final String receiver;

    private final String senderPort;

    private final String receiverPort;

    public MessagePath(String sender, String receiver, String senderPort, String receiverPort) {
        super();

        this.sender = sender;
        this.receiver = receiver;
        this.senderPort = senderPort;
        this.receiverPort = receiverPort;

        String[] sendLocation = getProcessAsArray(this.sender);
        String[] receiveLocation = getProcessAsArray(this.receiver);
        if (sendLocation.length == receiveLocation.length) {
            this.owner = this.sender.substring(0, this.sender.lastIndexOf(PooslConstants.PATH_SEPARATOR));
        } else {
            this.owner = (sendLocation.length > receiveLocation.length) ? this.receiver : this.sender;
        }
    }

    private String[] getProcessAsArray(String process) {
        return process.substring(1).split(PooslConstants.PATH_SEPARATOR);
    }

    public String getSenderPort() {
        return senderPort;
    }

    public String getReceiverPort() {
        return receiverPort;
    }

    public String getOwner() {
        return owner;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }
}
