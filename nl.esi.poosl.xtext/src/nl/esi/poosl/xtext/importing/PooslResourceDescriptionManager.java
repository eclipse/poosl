package nl.esi.poosl.xtext.importing;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionManager;
import org.eclipse.xtext.util.IResourceScopeCache;

import com.google.inject.Inject;

import nl.esi.poosl.PooslPackage;
import nl.esi.poosl.xtext.descriptions.PooslDescription;

public class PooslResourceDescriptionManager extends DefaultResourceDescriptionManager {
	private static final String POOSL_EXTENSION = ".poosl";

	// --- Identify which aspects of the models are externally visible
	// (regarding scoping and validation)

	@Inject
	private final IResourceScopeCache cache = IResourceScopeCache.NullImpl.INSTANCE;

	@Override
	protected IResourceDescription internalGetResourceDescription(Resource resource,
			IDefaultResourceDescriptionStrategy strategy) {
		return new PooslResourceDescription(resource, strategy, cache);
	}

	// --- Identify which models are affected by an externally visible change

	private IResourceDescription uriToResourceDescription(URI uri, IResourceDescriptions context) {
		if (context != null) {
			IResourceDescription candidate = context.getResourceDescription(uri);
			if (candidate != null) {
				return candidate;
			}
		}

		try {
			ResourceSet resourceSet = new ResourceSetImpl();
			Resource resource = resourceSet.getResource(uri, true);
			return getResourceDescription(resource);
		} catch (RuntimeException e) {
			return null;
		}
	}

	@Override
	public boolean isAffected(Delta delta, IResourceDescription candidate) {
		if (candidate.getURI().toString().endsWith(POOSL_EXTENSION)) {
			Set<URI> dependencies = computeAllDependencies(candidate, null);

			if (delta.haveEObjectDescriptionsChanged()) {
				URI deltaURI = decode(delta.getUri());
				if (dependencies.contains(deltaURI)) {
					return true;
				}
			}

			return false;
		}

		return super.isAffected(delta, candidate);
	}

	@Override
	public boolean isAffected(Collection<Delta> deltas, IResourceDescription candidate, IResourceDescriptions context) {
		if (candidate.getURI().toString().endsWith(POOSL_EXTENSION)) {
			Set<URI> dependencies = computeAllDependencies(candidate, context);

			for (IResourceDescription.Delta delta : deltas) {
				if (delta.haveEObjectDescriptionsChanged()) {
					URI deltaURI = decode(delta.getUri());
					if (dependencies.contains(deltaURI)) {
						return true;
					}
				}
			}

			return false;
		}

		return super.isAffected(deltas, candidate, context);
	}

	public Set<URI> computeAllDependencies(IResourceDescription candidate, IResourceDescriptions context) {
		if (candidate instanceof PooslResourceDescription
				&& (((PooslResourceDescription) candidate).getRecursiveDependencies() != null)) {
			return ((PooslResourceDescription) candidate).getRecursiveDependencies();
		} else {
			Set<URI> dependencies = new LinkedHashSet<>();
			dependencies.add(ImportingHelper.getBasicClassesURI());
			computeAllDependencies(dependencies, candidate, context);
			URI candidateUri = decode(candidate.getURI());
			dependencies.remove(candidateUri);
			if (candidate instanceof PooslResourceDescription) {
				((PooslResourceDescription) candidate).setRecursiveDependencies(dependencies);
			}
			return dependencies;
		}
	}

	private void computeAllDependencies(Set<URI> dependencies, IResourceDescription candidate,
			IResourceDescriptions context) {
		if (candidate != null) {
			URI candidateUri = decode(candidate.getURI());
			if (candidateUri != null && !dependencies.contains(candidateUri)) {
				dependencies.add(candidateUri);

				for (IEObjectDescription obj : candidate.getExportedObjectsByType(PooslPackage.Literals.POOSL)) {
					for (String importString : PooslDescription.getImports(obj)) {
						URI importedURI = ImportingHelper.resolveImportUri(candidateUri, URI.createURI(importString));
						if (importedURI != null && !dependencies.contains(importedURI)) {
							IResourceDescription importedCandidate = uriToResourceDescription(importedURI, context);
							computeAllDependencies(dependencies, importedCandidate, context);
						}
					}
					for (String importString : PooslDescription.getImportLibs(obj)) {
						URI importedURI = URI.createURI(importString);
						if (importedURI != null && !dependencies.contains(importedURI)) {
							IResourceDescription importedCandidate = uriToResourceDescription(importedURI, context);
							computeAllDependencies(dependencies, importedCandidate, context);
						}
					}
					// there exist only 1 POOSL object per
					// PooslResourceDescription
					return;
				}
			}
		}
	}

	// decode, dependencies are stored decoded, should be stored encoded?
	private URI decode(URI uri) {
		if (uri.isPlatform()) {
			return URI.createPlatformResourceURI(uri.toPlatformString(true), false);
		}
		return uri;
	}
}
