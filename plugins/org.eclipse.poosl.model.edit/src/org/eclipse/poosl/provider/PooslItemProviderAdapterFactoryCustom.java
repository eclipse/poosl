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
/**
 * 
 */
package org.eclipse.poosl.provider;

import org.eclipse.emf.common.notify.Adapter;

/**
 * The PooslItemProviderAdapterFactoryCustom.
 * 
 * @author <a href="nicolas.peransin@obeo.fr">Nicolas PERANSIN</a>
 *
 */
public class PooslItemProviderAdapterFactoryCustom extends PooslItemProviderAdapterFactory {

    @Override
    public Adapter createChannelAdapter() {
        if (channelItemProvider == null) {
            channelItemProvider = new ChannelItemProviderCustom(this);
        }

        return channelItemProvider;
    }

    @Override
    public Adapter createInstanceAdapter() {
        if (instanceItemProvider == null) {
            instanceItemProvider = new InstanceItemProviderCustom(this);
        }

        return instanceItemProvider;
    }

    @Override
    public Adapter createInstancePortAdapter() {
        if (instancePortItemProvider == null) {
            instancePortItemProvider = new InstancePortItemProviderCustom(this);
        }

        return instancePortItemProvider;
    }
}
