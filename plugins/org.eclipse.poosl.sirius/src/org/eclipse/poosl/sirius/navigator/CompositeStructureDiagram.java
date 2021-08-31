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
package org.eclipse.poosl.sirius.navigator;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.poosl.ClusterClass;
import org.eclipse.poosl.Instance;
import org.eclipse.poosl.Poosl;
import org.eclipse.poosl.xtext.helpers.HelperFunctions;

/**
 * The CompositeStructureDiagram.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class CompositeStructureDiagram {

    private CompositeStructureDiagram() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Returns the main target for a composite structure diagram in the poosl model.
     * 
     * @param poosl
     * @return null if not appropriate target is found
     */
    public static EObject findTarget(Poosl poosl) {
        ClusterClass system = HelperFunctions.getSystem(poosl);
        if (system != null) {
            return system;
        } else if (poosl.getClusterClasses().size() == 1) {
            return poosl.getClusterClasses().get(0);
        } else {
            return getMainCluster(poosl);
        }
    }

    /**
     * find a main cluster in poosl
     * 
     * @param poosl
     * @return Main ClusterClass, returns null if there are multiple or none.
     */
    private static EObject getMainCluster(Poosl poosl) {
        Set<String> clusterSet = new HashSet<>();
        for (ClusterClass clusterClass : poosl.getClusterClasses()) {
            for (Instance instance : clusterClass.getInstances()) {
                clusterSet.add(instance.getClassDefinition());
            }
        }

        ClusterClass mainCluster = null;
        for (ClusterClass clusterClass : poosl.getClusterClasses()) {
            if (!clusterSet.contains(clusterClass.getName())) {
                if (mainCluster != null) {
                    // multiple main
                    return null;
                }
                mainCluster = clusterClass;
            }
        }
        return mainCluster;
    }
}
