/*******************************************************************************
 * Copyright (c) 2021 TNO/ESI
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    TNO/ESI - initial API and implementation
 *    Obeo - refactoring
 *******************************************************************************/
package org.eclipse.poosl.xtext.ui.references;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.IResourceServiceProvider.Registry;
import org.eclipse.xtext.ui.refactoring.IRefactoringUpdateAcceptor;
import org.eclipse.xtext.ui.refactoring.IReferenceUpdater;
import org.eclipse.xtext.ui.refactoring.impl.ReferenceUpdaterDispatcher;
import org.eclipse.xtext.ui.refactoring.impl.StatusWrapper;

import com.google.inject.Inject;

/**
 * The PooslReferenceUpdaterDispatcher.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
@SuppressWarnings("restriction")
public class PooslReferenceUpdaterDispatcher extends ReferenceUpdaterDispatcher {

    @Inject
    private IResourceServiceProvider.Registry resourceServiceProviderRegistry;

    @Override
    protected ReferenceDescriptionAcceptor createFindReferenceAcceptor(
            IRefactoringUpdateAcceptor updateAcceptor) {
        return new PooslReferenceDescriptionAcceptor(resourceServiceProviderRegistry,
                updateAcceptor.getRefactoringStatus());
    }

    public static class PooslReferenceDescriptionAcceptor extends ReferenceDescriptionAcceptor {

        private StatusWrapper status;

        public PooslReferenceDescriptionAcceptor(Registry resourceServiceProviderRegistry,
                StatusWrapper status) {
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
                        getReferenceUpdater2ReferenceDescriptions().put(referenceUpdater,
                                referenceDescription);
                }
            }
        }
    }
}
