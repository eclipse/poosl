package nl.esi.poosl.xtext.ui.references;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.IResourceServiceProvider.Registry;
import org.eclipse.xtext.ui.refactoring.IRefactoringUpdateAcceptor;
import org.eclipse.xtext.ui.refactoring.IReferenceUpdater;
import org.eclipse.xtext.ui.refactoring.impl.ReferenceUpdaterDispatcher;
import org.eclipse.xtext.ui.refactoring.impl.StatusWrapper;

import com.google.inject.Inject;

@SuppressWarnings("restriction")
public class PooslReferenceUpdaterDispatcher extends ReferenceUpdaterDispatcher {

	@Inject
	private IResourceServiceProvider.Registry resourceServiceProviderRegistry;

	@Override
	protected ReferenceDescriptionAcceptor createFindReferenceAcceptor(IRefactoringUpdateAcceptor updateAcceptor) {
		return new PooslReferenceDescriptionAcceptor(resourceServiceProviderRegistry,
				updateAcceptor.getRefactoringStatus());
	}

	public static class PooslReferenceDescriptionAcceptor extends ReferenceDescriptionAcceptor {

		private StatusWrapper status;

		public PooslReferenceDescriptionAcceptor(Registry resourceServiceProviderRegistry, StatusWrapper status) {
			super(resourceServiceProviderRegistry, status);
			this.status = status;
		}

		@Override
		public void accept(IReferenceDescription referenceDescription) {
			if (referenceDescription.getSourceEObjectUri() == null
					|| referenceDescription.getTargetEObjectUri() == null) {
				handleCorruptReferenceDescription(referenceDescription, status);
			} else {
				URI sourceResourceURI = referenceDescription.getSourceEObjectUri().trimFragment();
				IReferenceUpdater referenceUpdater = getReferenceUpdater(sourceResourceURI);
				if (referenceUpdater == null) {
					handleNoReferenceUpdater(sourceResourceURI, status);
				} else {
					if (referenceDescription.getEReference() == null
							&& !(referenceUpdater instanceof PooslReferenceUpdater))
						handleCorruptReferenceDescription(referenceDescription, status);
					else
						getReferenceUpdater2ReferenceDescriptions().put(referenceUpdater, referenceDescription);
				}
			}
		}
	}
}
