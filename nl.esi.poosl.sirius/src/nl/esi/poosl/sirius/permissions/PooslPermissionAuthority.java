package nl.esi.poosl.sirius.permissions;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.gmf.runtime.notation.impl.DiagramImpl;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.ecore.extender.business.api.permission.IAuthorityListener;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.LockStatus;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import com.google.common.collect.MapMaker;

import nl.esi.poosl.sirius.Activator;

public class PooslPermissionAuthority implements IPermissionAuthority {
	private final Set<URI> creatingURIs = new HashSet<>();

	public PooslPermissionAuthority() {
	}

	private Map<String, List<URI>> getLockedUris() {
		return Activator.MESSAGEUPDATER.getLockedFiles();
	}

	private List<URI> getLockedUris(String launchID) {
		return Activator.MESSAGEUPDATER.getLockedFiles().get(launchID);
	}

	private boolean isReadOnly(EObject diagramObj) {
		if (diagramObj instanceof DiagramImpl) {
			DiagramImpl dd = (DiagramImpl) diagramObj;
			diagramObj = dd.getElement();
		}
		if (diagramObj instanceof DSemanticDecorator) {
			diagramObj = ((DSemanticDecorator) diagramObj).getTarget();
		}
		if (diagramObj == null)
			return false;

		URI uri = null;
		if (diagramObj.eResource() != null) {
			uri = diagramObj.eResource().getURI();
		} else {
			if (diagramObj.eIsProxy()) {
				uri = ((InternalEObject) diagramObj).eProxyURI().trimFragment();
			} else {
				return false;
			}
		}

		if (uri != null) {
			if (creatingURIs.contains(uri)) {
				return false;
			}

			for (Entry<String, List<URI>> entry : getLockedUris().entrySet()) {
				if (entry.getValue().contains(uri)) {
					return true;
				}
			}
		}
		return false;
	}

	private void notify(final boolean approval, final EObject obj) {
		if (obj instanceof DSemanticDiagram) {
			if (approval) {
				if (isLocked(obj)) {
					releaseFromLockedAndNotify(obj);
				}
			} else {
				if (!isLocked(obj)) {
					storeAsLockedAndNotify(obj);
				}
			}
		}
	}

	/**
	 * Check approval for editing this object.
	 * 
	 * @param eObject the object
	 * @return <code>true</code>if approval, <code>false</code> otherwise
	 */
	private boolean checkApproval(final EObject eObject) {
		final boolean approval = !isReadOnly(eObject);
		notify(approval, eObject);
		return approval;
	}

	@Override
	public boolean canCreateIn(final EObject obj) {
		return checkApproval(obj);
	}

	@Override
	public boolean canDeleteInstance(final EObject target) {
		return checkApproval(target);
	}

	@Override
	public boolean canEditFeature(final EObject obj, final String featureName) {
		return checkApproval(obj);
	}

	@Override
	public boolean canEditInstance(final EObject obj) {
		return checkApproval(obj);
	}

	@Override
	public void setReportIssues(final boolean report) {
		// do nothing
	}

	@Override
	public void notifyInstanceChange(final EObject instance) {
		// do nothing
	}

	@Override
	public void notifyInstanceDeletion(final EObject instance) {
		// do nothing
	}

	@Override
	public void notifyNewInstanceCreation(final EObject instance) {
		// do nothing
	}

	@SuppressWarnings("unchecked")
	@Override
	public void notifyLock(Collection<? extends EObject> elements) {
		for (IAuthorityListener listener : listeners) {
			listener.notifyIsLocked((Collection<EObject>) elements);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void notifyUnlock(Collection<? extends EObject> elements) {
		for (IAuthorityListener listener : listeners) {
			listener.notifyIsReleased((Collection<EObject>) elements);
		}
	}

	@Override
	public LockStatus getLockStatus(EObject element) {
		return isLocked(element) ? LockStatus.LOCKED_BY_OTHER : LockStatus.NOT_LOCKED;
	}

	/** the authority listeners. */
	private List<IAuthorityListener> listeners = new CopyOnWriteArrayList<>();

	/** the locked objects. */
	private ConcurrentMap<EObject, Object> lockedObjects = new MapMaker().concurrencyLevel(4).weakKeys().makeMap();

	/**
	 * Check if an object is locked or not.
	 * 
	 * @param eObject the object to check
	 * @return <code>true</code> if is locked, <code>false</code> otherwise
	 */
	private boolean isLocked(final EObject eObject) {
		return lockedObjects.containsKey(eObject);
	}

	/**
	 * Store as locked instance and notify.
	 * 
	 * @param eObject the locked instance
	 */
	private void storeAsLockedAndNotify(final EObject eObject) {
		lockedObjects.put(eObject, true);
		for (IAuthorityListener listener : listeners) {
			listener.notifyIsLocked(eObject);
		}
	}

	/**
	 * Release and notify.
	 * 
	 * @param eObject the locked instance
	 */
	private void releaseFromLockedAndNotify(final EObject eObject) {
		lockedObjects.remove(eObject);
		for (IAuthorityListener listener : listeners) {
			listener.notifyIsReleased(eObject);
		}
	}

	@Override
	public void addAuthorityListener(final IAuthorityListener listener) {
		// The same listener cannot be added multiple times
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	@Override
	public void dispose(final ResourceSet set) {
		if (set == null) {
			listeners.clear();
			lockedObjects.clear();
		}
	}

	@Override
	public void init(final ResourceSet set) {
		if (set != null) {
			// we may create a map for each resource set
		}
	}

	@Override
	public void removeAuthorityListener(final IAuthorityListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void setListening(final boolean shouldListen) {
		// do nothing
	}

	@Override
	public boolean isChanged(final EObject instance) {
		return false;
	}

	@Override
	public boolean isNewInstance(final EObject instance) {
		return false;
	}

	public void deActivate(String launch) {
		List<URI> unlocked = getLockedUris(launch);
		for (EObject object : lockedObjects.keySet()) {
			if (object.eResource() != null && unlocked.contains(object.eResource().getURI())) {
				releaseFromLockedAndNotify(object);
			}
		}
	}

	public void creatingRepresentation(URI uri) {
		creatingURIs.add(uri);
	}

	public void createdRepresentation(URI uri) {
		creatingURIs.remove(uri);
	}
}
