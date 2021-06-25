package nl.esi.poosl.xtext.importing;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.AbstractGlobalScopeProvider;
import org.eclipse.xtext.scoping.impl.LoadOnDemandResourceDescriptions;
import org.eclipse.xtext.util.IResourceScopeCache;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Provider;

// based on org.eclipse.xtext.scoping.impl.ImportUriGlobalScopeProvider

public class PooslImportUriGlobalScopeProvider extends AbstractGlobalScopeProvider {
	@Inject
	private Provider<LoadOnDemandResourceDescriptions> loadOnDemandDescriptions;

	@Inject
	private IResourceScopeCache cache;

	@Inject
	private IResourceDescription.Manager resourceDescriptionManager;

	public void setCache(IResourceScopeCache cache) {
		this.cache = cache;
	}

	public IResourceDescriptions getResourceDescriptions(Resource resource, Collection<URI> importUris) {
		IResourceDescriptions result = getResourceDescriptions(resource);
		LoadOnDemandResourceDescriptions demandResourceDescriptions = loadOnDemandDescriptions.get();
		demandResourceDescriptions.initialize(result, importUris, resource);
		return demandResourceDescriptions;
	}

	@Override
	protected IScope getScope(Resource resource, boolean ignoreCase, EClass type,
			Predicate<IEObjectDescription> filter) {

		Set<URI> uniqueImportURIs = getImportedUris(resource);
		IResourceDescriptions descriptions = getResourceDescriptions(resource, uniqueImportURIs);
		List<URI> urisAsList = Lists.newArrayList(uniqueImportURIs);
		Collections.reverse(urisAsList);
		IScope scope = IScope.NULLSCOPE;
		for (URI uri : urisAsList) {
			scope = createLazyResourceScope(scope, uri, descriptions, type, filter, ignoreCase);
		}

		return scope;
	}

	public IScope getScope(Resource resource, EClass type, Predicate<IEObjectDescription> filter) {
		return getScope(resource, false, type, filter);
	}

	public Set<URI> getImportedUris(final Resource resource) {
		return cache.get(PooslImportUriGlobalScopeProvider.class.getName(), resource, new Provider<Set<URI>>() {
			public Set<URI> get() {
				IResourceDescriptions context = getResourceDescriptions(resource);
				IResourceDescription candidate = resourceDescriptionManager.getResourceDescription(resource);
				Set<URI> uniqueImportURIs = ((PooslResourceDescriptionManager) resourceDescriptionManager)
						.computeAllDependencies(candidate, context);
				// Required for local scoping!
				uniqueImportURIs.add(resource.getURI());
				return uniqueImportURIs;
			}
		});
	}

	private IScope createLazyResourceScope(IScope parent, URI uri, IResourceDescriptions descriptions, EClass type,
			Predicate<IEObjectDescription> filter, boolean ignoreCase) {
		IResourceDescription description = descriptions.getResourceDescription(uri);
		return PooslSelectableBasedScope.createScope(parent, description, filter, type, ignoreCase);
	}

	public void setLoadOnDemandDescriptions(Provider<LoadOnDemandResourceDescriptions> loadOnDemandDescriptions) {
		this.loadOnDemandDescriptions = loadOnDemandDescriptions;
	}

	public Provider<LoadOnDemandResourceDescriptions> getLoadOnDemandDescriptions() {
		return loadOnDemandDescriptions;
	}
}
