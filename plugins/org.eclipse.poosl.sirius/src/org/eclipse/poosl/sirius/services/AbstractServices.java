package org.eclipse.poosl.sirius.services;

import org.eclipse.emf.ecore.EObject;

public class AbstractServices {
    protected static final String COPYOF = "CopyOf";

    private static final String BUNDLERESOURCE = "bundleresource";

    protected boolean isBundleResource(EObject object) {
        return object.eResource().getURI().scheme().equals(BUNDLERESOURCE);
    }
}
