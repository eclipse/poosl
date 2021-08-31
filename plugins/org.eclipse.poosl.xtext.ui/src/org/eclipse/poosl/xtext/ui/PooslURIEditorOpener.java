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
package org.eclipse.poosl.xtext.ui;

import org.apache.log4j.Logger;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.xtext.ui.editor.LanguageSpecificURIEditorOpener;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;

/**
 * The PooslURIEditorOpener.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslURIEditorOpener extends LanguageSpecificURIEditorOpener {
    private static final Logger LOGGER = Logger.getLogger(PooslURIEditorOpener.class);

    @Override
    public IEditorPart open(URI uri, EReference crossReference, int indexInList, boolean select) {
        IEditorPart part = super.open(uri, crossReference, indexInList, select);
        if (part == null) {
            try {
                if (uri.isFile()) {
                    IPath path = new Path(uri.toFileString());
                    if (path.isAbsolute()) {
                        IFileStore fileStore = EFS.getLocalFileSystem().getStore(path);
                        if (fileStore != null) {
                            IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                            IEditorPart editor = IDE.openEditorOnFileStore(activePage, fileStore);
                            selectAndReveal(editor, uri, crossReference, indexInList, select);
                            return EditorUtils.getXtextEditor(editor);
                        }
                    }
                }

            } catch (WrappedException e) {
                LOGGER.error("Error while opening editor part for EMF URI '" + uri + "'", e.getCause());
            } catch (PartInitException partInitException) {
                LOGGER.error("Error while opening editor part for EMF URI '" + uri + "'", partInitException);
            }
        }
        return part;
    }
}
