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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.poosl.Instance;
import org.eclipse.poosl.InstantiableClass;
import org.eclipse.poosl.Poosl;
import org.eclipse.poosl.PooslPackage;
import org.eclipse.poosl.xtext.importing.ImportingHelper;

/**
 * Customization of InstanceItemProvider
 * <p>
 * Name is conform to generation gap pattern of MWE2.
 * </p>
 *
 * @author <a href="nicolas.peransin@obeo.fr">Nicolas PERANSIN</a>
 */
public class InstanceItemProviderCustom extends InstanceItemProvider {

    /**
     * Default constructor.
     *
     * @param adapterFactory
     *     factory
     */
    public InstanceItemProviderCustom(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    protected void addClassDefinitionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(new ItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(), getString("_UI_Instance_classDefinition_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", //$NON-NLS-1$
                        "_UI_Instance_classDefinition_feature", //$NON-NLS-1$
                        "_UI_Instance_type"), //$NON-NLS-1$
                PooslPackage.Literals.INSTANCE__CLASS_DEFINITION, true, false, true, null, null,
                null) {

            @Override
            public Collection<?> getChoiceOfValues(Object object) {
                List<InstantiableClass> scopedClasses = new ArrayList<>();
                if (object instanceof Instance) {
                    Instance instance = (Instance) object;
                    List<Resource> resources = ImportingHelper
                            .computeAllDependencies(instance.eResource());
                    for (Resource resource : resources) {
                        Poosl poosl = ImportingHelper.toPoosl(resource);
                        scopedClasses.addAll(poosl.getClusterClasses());
                        scopedClasses.addAll(poosl.getProcessClasses());
                    }
                }
                return scopedClasses;
            }
        });
    }

}
