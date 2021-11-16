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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * The ExtensionHelper.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class ExtensionHelper {
    private static final String POOSL_DEUBUG_ATTRIBUTE = "class"; //$NON-NLS-1$

    private static final String IPOOSLDEBUGINFORMER_ID = "org.eclipse.poosl.rotalumisclient.debuginformer"; //$NON-NLS-1$

    private static final Logger LOGGER = Logger.getLogger(ExtensionHelper.class.getName());

    private ExtensionHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static List<IPooslDebugInformer> getExtensions() {
        List<IPooslDebugInformer> extensions = new ArrayList<>();
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IConfigurationElement[] config = registry
                .getConfigurationElementsFor(IPOOSLDEBUGINFORMER_ID);
        try {
            for (IConfigurationElement e : config) {
                final Object o = e.createExecutableExtension(POOSL_DEUBUG_ATTRIBUTE);

                if (o instanceof IPooslDebugInformer) {
                    extensions.add((IPooslDebugInformer) o);
                }
            }
        } catch (CoreException ex) {
            LOGGER.log(Level.FINE, "Extension could not be loaded.");
        }
        return extensions;
    }
}
