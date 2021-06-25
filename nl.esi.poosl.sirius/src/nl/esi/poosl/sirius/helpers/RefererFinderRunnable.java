package nl.esi.poosl.sirius.helpers;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.xtext.findReferences.IReferenceFinder;
import org.eclipse.xtext.findReferences.IReferenceFinder.Acceptor;
import org.eclipse.xtext.findReferences.TargetURIConverter;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.ui.editor.findrefs.ResourceAccess;

import com.google.common.base.Predicate;
import com.google.inject.Provider;

import nl.esi.poosl.Channel;
import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.Instance;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.ProcessMethodCall;
import nl.esi.poosl.xtext.ui.internal.PooslActivator;
import nl.esi.poosl.xtext.ui.references.PooslReferenceFinder;

@SuppressWarnings("restriction")
public class RefererFinderRunnable implements IRunnableWithProgress {
	private static PooslReferenceFinder pooslRefererFinder;
	private static IResourceDescriptions resourceDescriptions;
	private static TargetURIConverter targetUriConverter; // Deprecated, eventually use:
															// nl.esi.poosl.xtext.ui.references.PooslReferenceFinder
	private static Provider<ResourceAccess> resourceAccesProvider;

	private static PooslReferenceFinder getPooslReferenceFinder(Resource resource) {
		if (pooslRefererFinder == null) {
			pooslRefererFinder = (PooslReferenceFinder) IResourceServiceProvider.Registry.INSTANCE
					.getResourceServiceProvider(resource.getURI()).get(IReferenceFinder.class);
		}
		return pooslRefererFinder;
	}

	private static IResourceDescriptions getIResourceDescriptions(Resource resource) {
		if (resourceDescriptions == null) {
			resourceDescriptions = IResourceServiceProvider.Registry.INSTANCE
					.getResourceServiceProvider(resource.getURI()).get(IResourceDescriptions.class);
		}
		return resourceDescriptions;
	}

	private static TargetURIConverter getTargetURIConverter(Resource resource) {
		if (targetUriConverter == null) {
			targetUriConverter = IResourceServiceProvider.Registry.INSTANCE
					.getResourceServiceProvider(resource.getURI()).get(TargetURIConverter.class);
		}
		return targetUriConverter;
	}

	private static Provider<ResourceAccess> getResourceAccessProvider(Resource resource) {
		if (resourceAccesProvider == null) {
			resourceAccesProvider = PooslActivator.getInstance().getInjector("nl.esi.poosl.xtext.Poosl")
					.getProvider(ResourceAccess.class);
		}
		return resourceAccesProvider;
	}

	private EObject result = null;
	private final EObject element;
	private final Resource resource;

	public RefererFinderRunnable(EObject element, Resource resource) {
		this.element = element;
		this.resource = resource;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		final URI uri = EcoreUtil.getURI(element);
		Predicate<URI> targetURIs = createURIPredicate(uri);

		SubMonitor progress = SubMonitor.convert(monitor, "Finding references", 100);

		final Set<EObject> localReferers = new HashSet<>();
		final Set<URI> externReferers = new HashSet<>();
		Acceptor acceptor = createReferenceAcceptor(localReferers, externReferers);
		PooslReferenceFinder finder = getPooslReferenceFinder(element.eResource());

		finder.findReferences(targetURIs, resource, acceptor, progress.newChild(20));
		Iterator<EObject> localIt = localReferers.iterator();
		if (localIt.hasNext()) {
			result = findContainer(localIt.next());
			return;
		}

		Provider<ResourceAccess> resourceAccess = getResourceAccessProvider(resource);
		IResourceDescriptions indexData = getIResourceDescriptions(resource);
		TargetURIConverter targetURIConverter = getTargetURIConverter(resource);
		ResourceSet resourceSet = resource.getResourceSet();
		Set<URI> uriSet = Collections.singleton(uri);

		finder.findAllReferences(targetURIConverter.fromIterable(uriSet), resourceAccess.get(), indexData, acceptor,
				progress.newChild(80));

		Iterator<URI> externIt = externReferers.iterator();
		if (externIt.hasNext()) {
			result = findContainer(resourceSet.getEObject(externIt.next(), true));
		}
	}

	public EObject getResult() {
		return result;
	}

	private static EObject findContainer(EObject originalObject) {
		EObject object = originalObject;
		while (object != null && !(object instanceof Instance) && !(object instanceof DataMethod)
				&& !(object instanceof ProcessMethod)
				&& !(object instanceof ProcessMethodCall
						&& object.eContainingFeature() == Literals.PROCESS_CLASS__INITIAL_METHOD_CALL)
				&& !(object instanceof Channel) && !(object instanceof DataClass) && !(object instanceof ProcessClass)
				&& !(object instanceof ClusterClass)) {
			object = object.eContainer();
		}
		return object;
	}

	private static Acceptor createReferenceAcceptor(final Set<EObject> referers, final Set<URI> externReferers) {
		return new Acceptor() {
			@Override
			public void accept(EObject source, URI sourceURI, EReference eReference, int index, EObject targetOrProxy,
					URI targetURI) {
				referers.add(source);
			}

			@Override
			public void accept(IReferenceDescription description) {
				externReferers.add(description.getContainerEObjectURI());
			}
		};
	}

	private static Predicate<URI> createURIPredicate(final URI uri) {
		return new Predicate<URI>() {
			@Override
			public boolean apply(URI input) {
				return uri.equals(input);
			}
		};
	}
}
