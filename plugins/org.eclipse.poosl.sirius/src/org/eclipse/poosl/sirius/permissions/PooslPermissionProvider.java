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
package org.eclipse.poosl.sirius.permissions;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionProvider;

/**
 * The PooslPermissionProvider.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslPermissionProvider implements IPermissionProvider {
    private IPermissionAuthority permissionAuthority;

    /**
     * Default constructor.
     * 
     * @param permissionAuthority
     *     {@link IPermissionAuthority}
     */
    public PooslPermissionProvider(IPermissionAuthority permissionAuthority) {
        this.permissionAuthority = permissionAuthority;
    }

    public PooslPermissionProvider() {
        this.permissionAuthority = new PooslPermissionAuthority();
    }

    /**
     * Overridden to always provides the {@link IPermissionAuthority}.
     * 
     * {@inheritDoc}
     */
    public boolean provides(ResourceSet set) {
        return true;
    }

    /**
     * Overridden to provides the specified {@link IPermissionAuthority}.
     * 
     * {@inheritDoc}
     */
    public IPermissionAuthority getAuthority(ResourceSet set) {
        return permissionAuthority;
    }
}
