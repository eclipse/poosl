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

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.poosl.PooslPackage;

import com.google.inject.Injector;

/**
 * Initialization support for running Xtext languages without equinox extension registry.
 * <p>
 * Initiated by xtext.
 * </p>
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 */
public class PooslStandaloneSetup extends PooslStandaloneSetupGenerated {

    public static void doSetup() {
        new PooslStandaloneSetup().createInjectorAndDoEMFRegistration();
    }

    @Override
    public void register(Injector injector) {
        if (!EPackage.Registry.INSTANCE.containsKey("http://www.esi.nl/comma/actions/Actions")) { //$NON-NLS-1$
            EPackage.Registry.INSTANCE.put("http://poosl.eclipse.org/poosl/3.0.0", PooslPackage.eINSTANCE); //$NON-NLS-1$
        }

        super.register(injector);
    }
}
