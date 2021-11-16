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
package org.eclipse.poosl.rotalumisclient.debug;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;

/**
 * The SimulatorTerminationWatcher.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class SimulatorTerminationWatcher implements Runnable {
    private static final Logger LOGGER = Logger
            .getLogger(SimulatorTerminationWatcher.class.getName());

    private final PooslDebugTarget debugTarget;

    private final Process simulationProcess;

    private final String projectName;

    public SimulatorTerminationWatcher(PooslDebugTarget debugTarget, Process simulationProcess,
            String projectName) {
        this.debugTarget = debugTarget;
        this.simulationProcess = simulationProcess;
        this.projectName = projectName;
    }

    @Override
    public void run() {
        try {
            int exitCode = simulationProcess.waitFor();
            LOGGER.info("Simulator process terminated with return value: " + exitCode);
            if (debugTarget != null) {
                debugTarget.terminate();
            }
        } catch (InterruptedException e) {
            LOGGER.log(Level.WARNING, "Simulator was interrupted.", e);
            Thread.currentThread().interrupt();
        } catch (DebugException e) {
            LOGGER.log(Level.WARNING, "Debugexception occured during simulator process.", e);
        }
        try {
            if (projectName != null && !projectName.isEmpty()) {
                IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
                project.refreshLocal(IResource.DEPTH_INFINITE, null);
            }
        } catch (CoreException e) {
            LOGGER.log(Level.WARNING,
                    "Could not get project name from launch configuration.\nWill not be able to refresh the project.",
                    e);
        }
    }
}
