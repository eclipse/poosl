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
package org.eclipse.poosl.xtext;

import javax.inject.Inject;

import org.eclipse.xtext.xtext.generator.DefaultGeneratorModule;
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming;
import org.eclipse.xtext.xtext.generator.model.TypeReference;
import org.eclipse.xtext.xtext.generator.model.project.IXtextProjectConfig;

/**
 * Generator module with Poosl naming convention.
 * <p>
 * By default, generator uses name based on plugin name. <br>
 * Poosl project forces the name to 'PooslActivator'. </br>
 * </p>
 * 
 * @author <a href="nicolas.peransin@obeo.fr">Nicolas PERANSIN</a>
 */
@SuppressWarnings("restriction")
public class PooslGeneratorModule extends DefaultGeneratorModule {

    public Class<? extends XtextGeneratorNaming> bindNaming() {
        return PooslGeneratorNaming.class;
    }

    public static class PooslGeneratorNaming extends XtextGeneratorNaming {

        @Inject
        IXtextProjectConfig projectConfig;

        @Override
        public TypeReference getEclipsePluginActivator() {
            String pluginName = projectConfig.getEclipsePlugin().getName();
            return new TypeReference(pluginName + ".internal", "PooslActivator"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }
}
