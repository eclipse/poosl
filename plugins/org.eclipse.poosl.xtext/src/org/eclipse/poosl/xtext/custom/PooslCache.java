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
package org.eclipse.poosl.xtext.custom;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;

import com.google.inject.Provider;

/**
 * The PooslCache.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class PooslCache {
    // http://stackoverflow.com/questions/8309909/how-do-i-attach-some-cached-information-to-an-eclipse-editor-or-resource

    private PooslCache() {
        throw new IllegalStateException("Utility class"); //$NON-NLS-1$
    }

    public static PooslCacheEntry get(final Resource resource) {
        if (resource instanceof XtextResource) {
            return ((XtextResource) resource).getCache().get(PooslCache.class.getName(), resource, new Provider<PooslCacheEntry>() {
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
