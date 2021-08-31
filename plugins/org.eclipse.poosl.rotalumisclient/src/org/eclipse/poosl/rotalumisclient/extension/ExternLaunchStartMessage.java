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

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;

/**
 * The ExternLaunchStartMessage.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class ExternLaunchStartMessage {
    private final String projectName;

    private final String relativeModelPath;

    private final String launchID;

    private final Map<String, String> instancePortMap;

    private final List<URI> files;

    public ExternLaunchStartMessage(String launchID, String modelPath, String projectName, Map<String, String> instancePortMap, List<URI> files) {
        super();
        this.launchID = launchID;
        this.projectName = projectName;
        this.relativeModelPath = modelPath;
        this.instancePortMap = instancePortMap;
        this.files = files;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getRelativeModelPath() {
        return relativeModelPath;
    }

    public String getLaunchID() {
        return launchID;
    }

    public Map<String, String> getInstancePortMap() {
        return instancePortMap;
    }

    public List<URI> getFiles() {
        return files;
    }
}
