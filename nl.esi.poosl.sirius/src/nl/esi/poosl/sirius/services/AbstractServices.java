package nl.esi.poosl.sirius.services;

import org.eclipse.emf.ecore.EObject;

public class AbstractServices {
	private static final String BUNDLERESOURCE = "bundleresource";
	protected static final String COPYOF = "CopyOf";

	protected boolean isBundleResource(EObject object) {
		return object.eResource().getURI().scheme().equals(BUNDLERESOURCE);
	}
}
