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

import java.text.MessageFormat;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Platform;

/**
 * The TransformationHandlerUtil.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class TransformationHandlerUtil {
    private static final ILog LOGGER = Platform.getLog(TransformationHandlerUtil.class);

    private static final String REFRESH_FAILURE = "Document generation could not refresh project \"{0}\" after generation.";

    private TransformationHandlerUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void refreshProject(IProject project) {
        try {
            project.refreshLocal(IResource.DEPTH_INFINITE, null);
        } catch (CoreException e) {
            LOGGER.warn(MessageFormat.format(REFRESH_FAILURE, project.getName()), e);
        }
    }
}
