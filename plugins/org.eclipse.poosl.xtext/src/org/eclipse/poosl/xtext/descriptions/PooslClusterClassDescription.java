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
package org.eclipse.poosl.xtext.descriptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Platform;
import org.eclipse.poosl.Channel;
import org.eclipse.poosl.ClusterClass;
import org.eclipse.poosl.Instance;
import org.eclipse.poosl.PooslPackage.Literals;
import org.eclipse.poosl.xtext.helpers.HelperFunctions;
import org.eclipse.poosl.xtext.helpers.PooslChannelHelper;
import org.eclipse.poosl.xtext.helpers.PooslValidationHelper;
import org.eclipse.poosl.xtext.importing.ImportingHelper;
import org.eclipse.xtext.resource.IEObjectDescription;

/**
 * The PooslClusterClassDescription.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class PooslClusterClassDescription {

    private static final ILog LOGGER = Platform.getLog(ImportingHelper.class);

    private static final String SEMICOLON = ";"; //$NON-NLS-1$

    private static final String STR_CHANNELS = "Channels";

    private static final String STR_INSTANCES = "Instances";

    private PooslClusterClassDescription() {
        throw new IllegalStateException("Utility class");
    }

    // --- Set -------

    public static Map<String, String> createUserData(ClusterClass cClass) {
        Map<String, String> userData = new HashMap<>();
        setUserDataInstances(userData, cClass);
        setUserDataChannels(userData, cClass);
        return userData;
    }

    private static void setUserDataInstances(Map<String, String> userData, ClusterClass cClass) {
        StringBuilder instances = new StringBuilder();
        for (Instance instance : cClass.getInstances()) {
            String iName = instance.getName();
            String iClass = instance.getClassDefinition();
            if (iName != null && iClass != null) {
                instances.append(iName + ":" + iClass + SEMICOLON); //$NON-NLS-1$
            }
        }
        userData.put(STR_INSTANCES, instances.toString());
    }

    private static void setUserDataChannels(Map<String, String> userData, ClusterClass cClass) {
        StringBuilder channels = new StringBuilder();
        for (Channel channel : cClass.getChannels()) {
            PooslChannelHelper channelDescr = new PooslChannelHelper(channel);
            channels.append(channelDescr.toString()).append(SEMICOLON);
        }
        userData.put(STR_CHANNELS, channels.toString());
    }

    // --- Get -------

    public static Map<String, String> getInstancesMap(IEObjectDescription descr) {
        if (!checkValidity(descr))
            return Collections.emptyMap();

        Map<String, String> allInstances = new HashMap<>();
        String instancesString = descr.getUserData(STR_INSTANCES);
        String[] instanceDescriptions = instancesString.split(SEMICOLON);
        for (String instanceDescription : instanceDescriptions) {
            if (!instanceDescription.isEmpty()) {
                String[] instClass = instanceDescription.split(":"); //$NON-NLS-1$
                if (instClass.length == 2) {
                    allInstances.put(instClass[0], instClass[1]);
                } else {
                    LOGGER.warn("Could not add description of instance : " + instanceDescription);
                }
            }
        }

        return allInstances;
    }

    public static PooslChannelHelper getChannelDescription(
            IEObjectDescription descr, String externalPortName) {
        if (!checkValidity(descr))
            return null;

        String clusterName = HelperFunctions.getName(descr);
        String[] channelDescriptions = getSplittedChannels(descr);
        Map<String, String> instances = getInstancesMap(descr);

        for (String stringDescription : channelDescriptions) {
            PooslChannelHelper ch = new PooslChannelHelper(clusterName, stringDescription,
                    instances);
            if (externalPortName.equals(ch.getExternalPortName())) {
                return ch;
            }
        }
        return null;
    }

    public static List<PooslChannelHelper> getChannelDescriptions(IEObjectDescription descr) {
        if (!checkValidity(descr)) {
            return null;
        }

        String clusterName = HelperFunctions.getName(descr);
        String[] channelDescriptions = getSplittedChannels(descr);
        Map<String, String> instances = getInstancesMap(descr);

        List<PooslChannelHelper> channels = new ArrayList<>();
        for (String stringDescription : channelDescriptions) {
            channels.add(new PooslChannelHelper(clusterName, stringDescription, instances));
        }
        return channels;
    }

    private static String[] getSplittedChannels(IEObjectDescription descr) {
        String channelsString = descr.getUserData(STR_CHANNELS);
        return channelsString.split(SEMICOLON);
    }

    private static boolean checkValidity(IEObjectDescription descr) {
        return PooslValidationHelper.checkValidity(descr, Literals.CLUSTER_CLASS);
    }
}
