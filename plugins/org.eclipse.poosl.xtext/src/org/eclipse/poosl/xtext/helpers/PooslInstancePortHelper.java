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

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.poosl.ClusterClass;
import org.eclipse.poosl.Instance;
import org.eclipse.poosl.InstancePort;
import org.eclipse.poosl.xtext.GlobalConstants;

/**
 * The PooslInstancePortHelper.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslInstancePortHelper {
    private PooslInstanceHelper instanceHelper;

    private String port;

    private String instantiableClass;

    public PooslInstancePortHelper(InstancePort iPort) {
        ClusterClass arch = (ClusterClass) iPort.eContainer().eContainer();
        String aClassName = (arch.getName() != null) ? arch.getName() : GlobalConstants.POOSL_SYSTEM;
        String instanceName = (iPort.getInstance() != null) ? iPort.getInstance().getName() : ""; //$NON-NLS-1$

        instanceHelper = new PooslInstanceHelper(aClassName, instanceName);
        port = (iPort.getPort() != null) ? iPort.getPort().getPort() : ""; //$NON-NLS-1$
        instantiableClass = getClassDefinition(instanceName, arch);
    }

    public PooslInstancePortHelper(String aClassName, String description, Map<String, String> instances) {
        String[] portDescr = description.split(":"); //$NON-NLS-1$
        if (portDescr.length == 2) {
            instanceHelper = new PooslInstanceHelper(aClassName, portDescr[0]);
            port = portDescr[1];
            instantiableClass = instances.get(portDescr[0]);
        } else {
            Logger.getGlobal().log(Level.WARNING, this.getClass().getName() + description);
        }
    }

    private String getClassDefinition(String instanceName, ClusterClass arch) {
        for (Instance instance : arch.getInstances()) {
            if (instance.getName().equals(instanceName)) {
                return instance.getClassDefinition();
            }
        }
        return ""; //$NON-NLS-1$
    }

    public PooslInstanceHelper getInstance() {
        return instanceHelper;
    }

    public String getPortName() {
        return port;
    }

    public String getInstantiableClassName() {
        return instantiableClass;
    }

    @Override
    public String toString() {
        return getInstance().getInstanceName() + ":" + getPortName(); //$NON-NLS-1$
    }
}
