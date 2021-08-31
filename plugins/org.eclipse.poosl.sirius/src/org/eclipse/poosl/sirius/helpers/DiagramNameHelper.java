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
package org.eclipse.poosl.sirius.helpers;

import java.text.MessageFormat;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.poosl.ClusterClass;

/**
 * The DiagramNameHelper.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class DiagramNameHelper {
    public static final String COMMUNICATION_DIAGRAM_PREFIX = "<COMM>"; //$NON-NLS-1$

    private static final String CLASS_DIAGRAM_LABEL = "Classes ({0})";

    private static final String SYSTEM_DIAGRAM_LABEL = "System ({0})";

    private static final String CLUSTER_DIAGRAM_LABEL = "Cluster {0} ({1})";

    private static final String INSTANCE_LABEL_BREAKOFF = ".."; //$NON-NLS-1$

    private static final String INSTANCE_LABEL_SUFFIX = "]"; //$NON-NLS-1$

    private static final String INSTANCE_LABEL_PREFIX = " ["; //$NON-NLS-1$

    private DiagramNameHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static String getCommunicationDiagramName(EObject target, String instance) {
        String normal = getDiagramName(target);
        return getCommunicationDiagramNameFromOriginal(normal, instance);
    }

    public static String getCommunicationDiagramNameFromOriginal(String originalName, String instance) {
        return COMMUNICATION_DIAGRAM_PREFIX + originalName + getInstanceLabel(instance);
    }

    public static String getDiagramName(EObject target) {
        String filename = target.eResource().getURI().lastSegment();
        String name;
        if (target instanceof ClusterClass) {
            name = (((ClusterClass) target).getName() != null) ? MessageFormat.format(CLUSTER_DIAGRAM_LABEL, ((ClusterClass) target).getName(), filename)
                    : MessageFormat.format(SYSTEM_DIAGRAM_LABEL, filename);
        } else {
            name = MessageFormat.format(CLASS_DIAGRAM_LABEL, filename);
        }
        return name;
    }

    private static String getInstanceLabel(String instance) {
        String coreInstanceLabel;
        if (instance.length() > 25) {
            coreInstanceLabel = INSTANCE_LABEL_BREAKOFF + instance.substring(instance.length() - 25);
        } else {
            coreInstanceLabel = instance;
        }
        return INSTANCE_LABEL_PREFIX + coreInstanceLabel + INSTANCE_LABEL_SUFFIX;
    }
}
