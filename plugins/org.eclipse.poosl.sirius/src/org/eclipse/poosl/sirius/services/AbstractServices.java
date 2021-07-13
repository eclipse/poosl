package org.eclipse.poosl.sirius.services;

import org.eclipse.emf.ecore.EObject;

public class AbstractServices {
    protected static final String COPYOF = "CopyOf"; //$NON-NLS-1$

    private static final String BUNDLERESOURCE = "bundleresource"; //$NON-NLS-1$

    protected boolean isBundleResource(EObject object) {
        return object.eResource().getURI().scheme().equals(BUNDLERESOURCE);
    }
}
