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
package org.eclipse.poosl.provider;

import org.eclipse.emf.common.notify.AdapterFactory;

/**
 * Customization of instance port.
 * 
 * @author <a href="nicolas.peransin@obeo.fr">Nicolas PERANSIN</a>
 */
public class InstancePortItemProviderCustom extends InstancePortItemProvider {

    public InstancePortItemProviderCustom(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    protected void addPortPropertyDescriptor(Object object) {
        // port is removed or virtual or not applicable for instance port ?
    }

    @Override
    protected void addInstancePropertyDescriptor(Object object) {
        // instance is removed or virtual or not applicable for instance port ?
    }

}
