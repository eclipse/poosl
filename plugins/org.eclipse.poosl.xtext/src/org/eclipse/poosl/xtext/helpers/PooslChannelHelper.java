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
package org.eclipse.poosl.xtext.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.poosl.Channel;
import org.eclipse.poosl.InstancePort;

/**
 * The PooslChannelHelper.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslChannelHelper {
    private static final String COMMA = ","; //$NON-NLS-1$

    private String externalPortName;

    private List<PooslInstancePortHelper> instancePorts = new ArrayList<>();

    public PooslChannelHelper(String aClassName, String stringDescription,
            Map<String, String> instances) {
        String[] ports = stringDescription.split(COMMA);
        if (ports != null && ports.length != 0) {
            for (int i = 0; i < ports.length; i++) {
                if (!ports[i].contains(":")) { //$NON-NLS-1$
                    externalPortName = ports[i];
                } else {
                    instancePorts.add(new PooslInstancePortHelper(aClassName, ports[i], instances));
                }
            }
        }
    }

    public PooslChannelHelper(Channel channel) {
        externalPortName = (channel.getExternalPort() != null)
            ? channel.getExternalPort().getName() : ""; //$NON-NLS-1$
        for (InstancePort iPort : channel.getInstancePorts()) {
            if (iPort.getInstance() != null && iPort.getPort() != null) {
                instancePorts.add(new PooslInstancePortHelper(iPort));
            }
        }
    }

    public String getExternalPortName() {
        return externalPortName;
    }

    public List<PooslInstancePortHelper> getInstancePorts() {
        return instancePorts;
    }

    public String toString() {
        StringBuilder channelDescriptions = new StringBuilder();
        if (getExternalPortName() != null && !getExternalPortName().isEmpty()) {
            channelDescriptions.append(getExternalPortName()).append(COMMA);
        }
        for (PooslInstancePortHelper ip : getInstancePorts()) {
            channelDescriptions.append(ip.toString()).append(COMMA);
        }
        return channelDescriptions.toString();
    }
}
