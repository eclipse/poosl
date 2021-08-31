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
package org.eclipse.poosl.rotalumisclient.views.diagram;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.poosl.generatedxmlclasses.TCommunicationEvent;
import org.eclipse.poosl.generatedxmlclasses.TCommunicationEventParameter.Parameter;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;

/**
 * This is a wrapper class for a diagram message to add a property descriptor to it.
 * 
 * @author Jeroen van Schelven
 *
 */
public class PooslDiagramMessage implements IPropertySource {
    private static final String PROPERTY_SEND_PORT = "poosldiagrammessage.sendport"; //$NON-NLS-1$

    private static final String PROPERTY_RECEIVE_PORT = "poosldiagrammessage.receiveport"; //$NON-NLS-1$

    private static final String PROPERTY_TIMESTAMP = "poosldiagrammessage.timestamp"; //$NON-NLS-1$

    private static final String PROPERTY_MESSAGE = "poosldiagrammessage.message"; //$NON-NLS-1$

    private final TCommunicationEvent communicationEvent;

    private final IPropertyDescriptor[] properties;

    public PooslDiagramMessage(TCommunicationEvent event) {
        this.communicationEvent = event;
        List<IPropertyDescriptor> propertiesList = new ArrayList<>();
        propertiesList.add(new PropertyDescriptor(PROPERTY_TIMESTAMP, "Simulated time"));
        propertiesList.add(new PropertyDescriptor(PROPERTY_SEND_PORT, "Sender port"));
        propertiesList.add(new PropertyDescriptor(PROPERTY_RECEIVE_PORT, "Receiver port"));
        propertiesList.add(new PropertyDescriptor(PROPERTY_MESSAGE, "Message name"));

        int i = 1;
        for (Parameter parameter : communicationEvent.getMessage().getParameters().getParameter()) {
            propertiesList.add(new PropertyDescriptor(parameter, "Parameter " + i));
            i++;
        }
        properties = propertiesList.toArray(new IPropertyDescriptor[0]);
    }

    @Override
    public Object getEditableValue() {
        return null;
    }

    @Override
    public IPropertyDescriptor[] getPropertyDescriptors() {
        return properties;
    }

    @Override
    public Object getPropertyValue(Object id) {
        if (id.equals(PROPERTY_TIMESTAMP)) {
            return communicationEvent.getSimulationTime().toString();
        } else if (id.equals(PROPERTY_SEND_PORT)) {
            return communicationEvent.getSender().getProcessPath() + "." + communicationEvent.getSender().getPortName(); //$NON-NLS-1$
        } else if (id.equals(PROPERTY_RECEIVE_PORT)) {
            return communicationEvent.getReceiver().getProcessPath() + "." + communicationEvent.getReceiver().getPortName(); //$NON-NLS-1$
        } else if (id.equals(PROPERTY_MESSAGE)) {
            return communicationEvent.getMessage().getName();
        }
        for (Parameter parameter : communicationEvent.getMessage().getParameters().getParameter()) {
            if (id.equals(parameter)) {
                return new CommunicationMessageParamater(parameter);
            }
        }
        return null;
    }

    @Override
    public boolean isPropertySet(Object id) {
        return false;
    }

    @Override
    public void resetPropertyValue(Object id) {
        // Not used due to the fact that isPropertySet returns false
    }

    @Override
    public void setPropertyValue(Object id, Object value) {
        // Not used due to the fact that isPropertySet returns false
    }

    public String getSendProcess() {
        return communicationEvent.getSender().getProcessPath();
    }

    public String getSendPort() {
        return communicationEvent.getSender().getPortName();
    }

    public String getReceiveProcess() {
        return communicationEvent.getReceiver().getProcessPath();
    }

    public String getReceivePort() {
        return communicationEvent.getReceiver().getPortName();
    }

    public List<Parameter> getParamters() {
        return communicationEvent.getMessage().getParameters().getParameter();
    }

    public String getMessageName() {
        return communicationEvent.getMessage().getName();
    }

    public BigDecimal getSimulatedTime() {
        return communicationEvent.getSimulationTime();
    }

    class CommunicationMessageParamater implements IPropertySource {
        private static final String PROPERTY_MESSAGE_PARAMETER_VALUE = "poosldiagrammessage.message.parameter.value"; //$NON-NLS-1$

        private static final String PROPERTY_MESSAGE_PARAMETER_TYPE = "poosldiagrammessage.message.parameter.type"; //$NON-NLS-1$

        Parameter parameter;

        CommunicationMessageParamater(Parameter parameter) {
            this.parameter = parameter;
        }

        @Override
        public Object getEditableValue() {
            return null;
        }

        @Override
        public IPropertyDescriptor[] getPropertyDescriptors() {
            return new IPropertyDescriptor[] { new PropertyDescriptor(PROPERTY_MESSAGE_PARAMETER_VALUE, "Value"), new PropertyDescriptor(PROPERTY_MESSAGE_PARAMETER_TYPE, "Type") };
        }

        @Override
        public Object getPropertyValue(Object id) {
            if (id.equals(PROPERTY_MESSAGE_PARAMETER_VALUE)) {
                return parameter.getValue();
            } else if (id.equals(PROPERTY_MESSAGE_PARAMETER_TYPE)) {
                return parameter.getType();
            }
            return null;
        }

        @Override
        public boolean isPropertySet(Object id) {
            return false;
        }

        @Override
        public void resetPropertyValue(Object id) {
            // Not used due to the fact that isPropertySet returns false
        }

        @Override
        public void setPropertyValue(Object id, Object value) {
            // Not used due to the fact that isPropertySet returns false
        }

    }

    public String getFormattedPayLoad() {
        String payload = communicationEvent.getMessage().getName();
        for (Parameter param : communicationEvent.getMessage().getParameters().getParameter()) {
            payload += "\n    " + param.getValue() + " : " + param.getType(); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return payload;
    }

    public String getPayload() {
        String payload = ""; //$NON-NLS-1$
        for (Parameter param : communicationEvent.getMessage().getParameters().getParameter()) {
            payload += ", " + param.getValue(); //$NON-NLS-1$
        }
        if (!payload.isEmpty()) {
            payload = payload.substring(2);
        }
        payload = communicationEvent.getMessage().getName() + "(" + payload + ")"; //$NON-NLS-1$ //$NON-NLS-2$
        return payload;
    }
}
