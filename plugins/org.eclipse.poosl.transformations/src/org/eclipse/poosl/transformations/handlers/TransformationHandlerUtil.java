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
package org.eclipse.poosl.transformations.handlers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

/**
 * The TransformationHandlerUtil.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class TransformationHandlerUtil {
    private static final Logger LOGGER = Logger.getLogger(TransformationHandlerUtil.class.getName());

    private TransformationHandlerUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void refreshProject(IProject project) {
        try {
            project.refreshLocal(IResource.DEPTH_INFINITE, null);
        } catch (CoreException e) {
            LOGGER.log(Level.WARNING, "Document generation could not refresh project \"" + project.getName() + "\" after generation.", e);
        }
    }
}
