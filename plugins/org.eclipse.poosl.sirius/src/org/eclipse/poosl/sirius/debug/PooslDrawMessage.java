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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Platform;
import org.eclipse.poosl.rotalumisclient.PooslConstants;
import org.eclipse.poosl.rotalumisclient.extension.ExternDebugMessage;
import org.eclipse.poosl.xtext.GlobalConstants;

/**
 * The PooslDrawMessage.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslDrawMessage {
    private static final ILog LOGGER = Platform.getLog(PooslDrawMessage.class);

    private ExternDebugMessage message;

    private Map<String, MessagePath> messagePaths;

    private String[] sendLocation;

    private String[] receiveLocation;

    private int commonIndex;

    public PooslDrawMessage(ExternDebugMessage message, Map<String, String> instancePortMap) {
        setMessage(message);
        setCommonClusterIndex();
        setMessagePaths(calculateMessagePath(instancePortMap));
    }

    private void setCommonClusterIndex() {
        // find highest common cluster
        commonIndex = 0;
        while (sendLocation[commonIndex] != null && receiveLocation[commonIndex] != null
                && sendLocation[commonIndex].equals(receiveLocation[commonIndex])) {
            commonIndex++;
        }
    }

    private void setMessage(ExternDebugMessage message) {
        this.message = message;
        sendLocation = message.getSendProcess().substring(1).split(PooslConstants.PATH_SEPARATOR);
        receiveLocation = message.getReceiveProcess().substring(1)
                .split(PooslConstants.PATH_SEPARATOR);
    }

    public ExternDebugMessage getMessage() {
        return message;
    }

    /**
     * Returns a {@link Map} diagram name as key and the messagepath as value,
     * may return null.
     * 
     * @return the diagram/messagePath map
     */
    public Map<String, MessagePath> getMessagePaths() {
        return messagePaths;
    }

    public void setMessagePaths(List<MessagePath> messagePathList) {
        if (messagePaths == null) {
            messagePaths = new HashMap<>();
        }
        for (MessagePath mPath : messagePathList) {
            messagePaths.put(mPath.getOwner(), mPath);
        }
    }

    public MessagePath getMessagePath(String owner) {
        if (messagePaths != null) {
            return messagePaths.get(owner);
        } else {
            return null;
        }
    }

    private List<MessagePath> calculateMessagePath(Map<String, String> instancePortMap) {
        List<MessagePath> allPaths = new ArrayList<>();
        List<MessagePath> sendingPaths = createMessagePaths(message.getSendProcess(),
                message.getSendPort(), (sendLocation.length - commonIndex) - 1, instancePortMap,
                true);
        List<MessagePath> receivingPaths = createMessagePaths(message.getReceiveProcess(),
                message.getReceivePort(), (receiveLocation.length - commonIndex) - 1,
                instancePortMap, false);

        allPaths.addAll(sendingPaths);
        allPaths.addAll(receivingPaths);
        if (!messageToAdapter()) {
            allPaths.add(createMainMessagePath(sendingPaths, receivingPaths));
        }
        return allPaths;
    }

    private MessagePath createMainMessagePath(
            List<MessagePath> sendingPaths, List<MessagePath> receivingPaths) {
        String sender;
        String senderPort;
        if (sendingPaths.isEmpty()) {
            sender = message.getSendProcess();
            senderPort = message.getSendPort();
        } else {
            MessagePath lastSending = sendingPaths.get(sendingPaths.size() - 1);
            sender = lastSending.getReceiver();
            senderPort = lastSending.getReceiverPort();
        }

        String receiver;
        String receiverPort;
        if (receivingPaths.isEmpty()) {
            receiver = message.getReceiveProcess();
            receiverPort = message.getReceivePort();
        } else {
            MessagePath firstReceiving = receivingPaths.get(receivingPaths.size() - 1);
            receiver = firstReceiving.getSender();
            receiverPort = firstReceiving.getSenderPort();
        }
        return new MessagePath(sender, receiver, senderPort, receiverPort);
    }

    private List<MessagePath> createMessagePaths(
            String process, String port, int main, Map<String, String> instancePortMap,
            boolean sending) {
        List<MessagePath> path = new ArrayList<>();

        if (!isAdapterPath(sending)) {

            String processAndPort = process + "." + port; //$NON-NLS-1$
            for (int i = 0; i < main; i++) {
                String externProcessAndPort = instancePortMap.get(processAndPort);
                if (externProcessAndPort == null) {
                    LOGGER.warn("Could not find the external port for " + processAndPort); //$NON-NLS-1$
                    return path;
                }

                String[] externInfo = externProcessAndPort.split("\\."); //$NON-NLS-1$
                String externProcess = externInfo[0];
                String externPort = externInfo[1];

                String[] receiverInfo = processAndPort.split("\\."); //$NON-NLS-1$
                String receiverInfoProcess = receiverInfo[0];
                String receiverInfoPort = receiverInfo[1];

                if (sending) {
                    path.add(new MessagePath(receiverInfoProcess, externProcess, receiverInfoPort,
                            externPort));
                } else {
                    path.add(new MessagePath(externProcess, receiverInfoProcess, externPort,
                            receiverInfoPort));
                }

                processAndPort = externProcessAndPort;
            }

        }
        return path;
    }

    private boolean isAdapterPath(boolean sending) {
        if (!messageToAdapter()) {
            return false;
        }

        String[] locations = sending ? sendLocation : receiveLocation;
        return !locations[0].equals(GlobalConstants.POOSL_SYSTEM);
    }

    private boolean messageToAdapter() {
        return commonIndex == 0;
    }
}
