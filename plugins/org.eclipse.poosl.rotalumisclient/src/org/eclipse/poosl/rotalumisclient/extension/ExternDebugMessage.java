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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.poosl.generatedxmlclasses.TCommunicationEvent;
import org.eclipse.poosl.generatedxmlclasses.TCommunicationEventParameter.Parameter;
import org.eclipse.poosl.rotalumisclient.views.diagram.PooslDiagramMessage;

/**
 * The ExternDebugMessage.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class ExternDebugMessage {
    private final String simTime;

    private final String sendProcess;

    private final String sendPort;

    private final String receiveProcess;

    private final String receivePort;

    private final String messageName;

    private final String launchID;

    private final String relativeModelPath;

    private final Map<String, String> parameters = new HashMap<>();

    private ExternDebugMessage(String launchID, BigDecimal simTime, String relativeModelPath, String sendProcess, String sendPort, String receiveProcess, String receivePort, String messageName,
            List<Parameter> parameters) throws InstantiationException {
        this.simTime = simTime.toString();
        this.sendProcess = sendProcess;
        this.sendPort = sendPort;
        this.receiveProcess = receiveProcess;
        this.receivePort = receivePort;
        this.messageName = messageName;
        this.launchID = launchID;
        this.relativeModelPath = relativeModelPath;
        for (Parameter parameter : parameters) {
            this.parameters.put(parameter.getType(), parameter.getValue());
        }

        if (sendProcess == null || sendPort == null || receiveProcess == null || receivePort == null || launchID == null) {
            throw new InstantiationException();
        }
    }

    public ExternDebugMessage(String launch, String modelPath, TCommunicationEvent commEvent) throws InstantiationException {
        this(launch, commEvent.getSimulationTime(), modelPath, commEvent.getSender().getProcessPath(), commEvent.getSender().getPortName(), commEvent.getReceiver().getProcessPath(),
                commEvent.getReceiver().getPortName(), commEvent.getMessage().getName(), commEvent.getMessage().getParameters().getParameter());
    }

    public ExternDebugMessage(String launch, String modelPath, PooslDiagramMessage message) throws InstantiationException {
        this(launch, message.getSimulatedTime(), modelPath, message.getSendProcess(), message.getSendPort(), message.getReceiveProcess(), message.getReceivePort(), message.getMessageName(),
                message.getParamters());
    }

    public String getSimTime() {
        return simTime;
    }

    public String getSendProcess() {
        return sendProcess;
    }

    public String getSendPort() {
        return sendPort;
    }

    public String getReceiveProcess() {
        return receiveProcess;
    }

    public String getReceivePort() {
        return receivePort;
    }

    public String getMessageName() {
        return messageName;
    }

    public String getLaunch() {
        return launchID;
    }

    public String getRelativeModelPath() {
        return relativeModelPath;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
}
