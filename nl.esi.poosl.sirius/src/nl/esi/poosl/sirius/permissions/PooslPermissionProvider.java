package nl.esi.poosl.sirius.permissions;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionProvider;

public class PooslPermissionProvider implements IPermissionProvider {
	private IPermissionAuthority permissionAuthority;

	/**
	 * Default constructor.
	 * 
	 * @param permissionAuthority {@link IPermissionAuthority}
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
