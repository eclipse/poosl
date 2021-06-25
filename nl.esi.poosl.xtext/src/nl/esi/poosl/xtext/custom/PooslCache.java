package nl.esi.poosl.xtext.custom;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;

import com.google.inject.Provider;

public final class PooslCache {
	// http://stackoverflow.com/questions/8309909/how-do-i-attach-some-cached-information-to-an-eclipse-editor-or-resource

	private PooslCache() {
		throw new IllegalStateException("Utility class");
	}

	public static PooslCacheEntry get(final Resource resource) {
		if (resource instanceof XtextResource) {
			return ((XtextResource) resource).getCache().get(PooslCache.class.getName(), resource,
					new Provider<PooslCacheEntry>() {
						public PooslCacheEntry get() {
							return new PooslCacheEntry(resource);
						}
					});
		} else {
			return new PooslCacheEntry(resource);
		}
	}

	public static void clear(final Resource resource) {
		if (resource instanceof XtextResource) {
			((XtextResource) resource).getCache().clear(resource);
		}
	}

	public static PooslCacheEntry cleanGet(final Resource resource) {
		clear(resource);
		return get(resource);
	}
}
