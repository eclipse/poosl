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
 * The ExternDebugItem.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class ExternDebugItem {
    private final String diagram;

    private final String relativeModelPath;

    private final String projectName;

    private final String launchID;

    public ExternDebugItem(String diagram, String relativeModelPath, String projectName,
            String launchID) {
        this.diagram = diagram;
        this.relativeModelPath = relativeModelPath;
        this.projectName = projectName;
        this.launchID = launchID;
    }

    public String getDiagram() {
        return diagram;
    }

    public String getRelativeModelPath() {
        return relativeModelPath;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getLaunchID() {
        return launchID;
    }
}
