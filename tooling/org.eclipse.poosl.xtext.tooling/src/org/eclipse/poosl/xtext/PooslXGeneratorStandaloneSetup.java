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

import org.eclipse.xtext.xtext.generator.XtextGeneratorStandaloneSetup;

import com.google.inject.Injector;

/**
 * Setup the path for generation project.
 * <p>
 * Xtext uses hard-coded pattern to detect '.project' descriptor. <br>
 * Unfortunately, this choice drives at lot of </br>
 * </p>
 * 
 * @author <a href="nicolas.peransin@obeo.fr">Nicolas PERANSIN</a>
 */
public class PooslXGeneratorStandaloneSetup extends XtextGeneratorStandaloneSetup {

    @Override
    public void initialize(Injector injector) {
        super.initialize(injector);
        new LocalStandaloneSetup(false).setScanClassPath(isScanClasspath());
    }

}
