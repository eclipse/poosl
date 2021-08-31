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
package org.eclipse.poosl.sirius.services;

import org.eclipse.emf.ecore.EObject;

/**
 * The AbstractServices.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class AbstractServices {
    protected static final String COPYOF = "CopyOf"; //$NON-NLS-1$

    private static final String BUNDLERESOURCE = "bundleresource"; //$NON-NLS-1$

    protected boolean isBundleResource(EObject object) {
        return object.eResource().getURI().scheme().equals(BUNDLERESOURCE);
    }
}
