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
package org.eclipse.poosl.sirius.helpers;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.poosl.Poosl;
import org.eclipse.poosl.xtext.GlobalConstants;
import org.eclipse.poosl.xtext.importing.ImportingHelper;

/**
 * The ConvertHelper.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class ConvertHelper {

    private ConvertHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static Poosl convertIFileToPoosl(IFile file) {
        if (file != null) {
            Resource resource = convertIFileToResource(file);
            if (resource != null) {
                return ImportingHelper.toPoosl(resource);
            }
        }
        return null;
    }

    public static Resource convertIFileToResource(IFile file) {
        String fileExtension = file.getFileExtension();
        if (fileExtension != null && fileExtension.equals(GlobalConstants.FILE_EXTENSION)) {
            URI uri = URI.createPlatformResourceURI(file.getFullPath().toString().substring(1),
                    true);
            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getPackageRegistry().put(
                    org.eclipse.poosl.PooslPackage.eINSTANCE.getNsURI(),
                    org.eclipse.poosl.PooslPackage.eINSTANCE);
            return resourceSet.getResource(uri, true);
        }
        return null;
    }

    public static Resource convertIPathToResource(IPath path) {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IFile ifile = workspace.getRoot().getFile(path);
        return convertIFileToResource(ifile);
    }

    public static IFile convertISelectionToIFile(ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            Object firstElement = ((IStructuredSelection) selection).getFirstElement();
            if (firstElement instanceof IFile) {
                return (IFile) firstElement;
            }
        }
        return null;
    }
}
