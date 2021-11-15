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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.poosl.ClusterClass;
import org.eclipse.poosl.PooslPackage;
import org.eclipse.poosl.Port;

/**
 * Customization of ChannelItemProvider
 * <p>
 * Name is conform to generation gap pattern of MWE2.
 * </p>
 *
 * @author <a href="nicolas.peransin@obeo.fr">Nicolas PERANSIN</a>
 */
public class ChannelItemProviderCustom extends ChannelItemProvider {

    /**
     * Default constructor.
     *
     * @param adapterFactory
     *     factory
     */
    public ChannelItemProviderCustom(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    protected void addExternalPortPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(new ItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(), getString("_UI_Channel_externalPort_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", //$NON-NLS-1$
                        "_UI_Channel_externalPort_feature", //$NON-NLS-1$
                        "_UI_Channel_type"), //$NON-NLS-1$
                PooslPackage.Literals.CHANNEL__EXTERNAL_PORT, true, false, true, null, null, null) {
            @Override
            public Collection<?> getChoiceOfValues(Object object) {
                List<Port> list = new ArrayList<>();
                if (object instanceof EObject) {
                    EObject eObject = (EObject) object;
                    while ((eObject.eContainer() != null) && !(eObject instanceof ClusterClass)) {
                        eObject = eObject.eContainer();
                    }
                    if (eObject instanceof ClusterClass) {
                        return ((ClusterClass) eObject).getPorts();
                    }
                }
                return list;
            }
        });
    }

}
