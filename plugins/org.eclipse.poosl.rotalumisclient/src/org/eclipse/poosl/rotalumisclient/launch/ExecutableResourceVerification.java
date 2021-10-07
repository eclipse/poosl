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
package org.eclipse.poosl.rotalumisclient.launch;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.poosl.pooslproject.PooslProjectConstant;
import org.eclipse.poosl.rotalumisclient.PooslConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.eclipse.ui.model.WorkbenchPartLabelProvider;
import org.eclipse.ui.progress.UIJob;

/**
 * Verification of edited model.
 * <p>
 * Verification is only direct project and ignore inclusion. This is only best effort to avoid user-surprise on simple
 * cases.
 * </p>
 * 
 * @author Obeo
 */
public class ExecutableResourceVerification {

    private static final String CHARSET_FORCE_TITLE = "Unsupported Charset";

    private static final String CHARSET_FORCE_MESSAGE = //
            "Only " + PooslProjectConstant.SUPPORTED_CHARSET.displayName() + " is supported.\n"//
                    + "This has no impact when only Latin Characters are used in models.\n" //
                    + "(You can turn off this warning in Execution Configuration)\n"//
                    + "Continue execution ?";

    private static final Logger LOGGER = Logger.getLogger(ExecutableResourceVerification.class.getName());

    private final IFile model;

    private final IProject project;

    private final boolean forceCharset;

    /**
     * Default constructor.
     * 
     * @param model
     *            to verify
     * @param forceCharset
     *            assume compatible and ignore charset setting
     */
    public ExecutableResourceVerification(IFile model, boolean forceCharset) {
        this.project = model.getProject();
        this.model = model;
        this.forceCharset = forceCharset;
    }

    public boolean isValid() throws CoreException, InterruptedException {
        return !isEditing() && isCharsetSupported();
    }

    private boolean isCharsetSupported() throws CoreException {
        if (forceCharset || PooslProjectConstant.SUPPORTED_CHARSET.name().equals(model.getCharset())) {
            return true;
        }
        AtomicBoolean confirm = new AtomicBoolean(false);

        Display display = Display.getDefault();
        display.syncExec(() -> {
            confirm.set(MessageDialog.openConfirm(display.getActiveShell(), CHARSET_FORCE_TITLE, CHARSET_FORCE_MESSAGE));
        });
        return confirm.get();
    }

    private boolean isEditing() throws CoreException, InterruptedException {
        final Map<IEditorPart, IWorkbenchPage> dirtyResources = getDirtyResources();

        if (dirtyResources.isEmpty()) {
            return false;
        }

        final IEditorPart[] dirtyEditorParts = new IEditorPart[dirtyResources.size()];
        int i = 0;
        for (IEditorPart editorPart : dirtyResources.keySet()) {
            dirtyEditorParts[i] = editorPart;
            i++;
        }

        UIJob uiJob = new UIJob("") { //$NON-NLS-1$
            @Override
            public IStatus runInUIThread(IProgressMonitor monitor) {
                ListSelectionDialog resourceSelectionDialog = new ListSelectionDialog(Display.getDefault().getActiveShell(), dirtyEditorParts, new ArrayContentProvider(),
                        new WorkbenchPartLabelProvider(), "The following file(s) contain unsaved changes.\nSelect files to save.");
                resourceSelectionDialog.setInitialSelections((Object[]) dirtyEditorParts);
                if (resourceSelectionDialog.open() != SelectionDialog.OK) {
                    return new Status(IStatus.CANCEL, PooslConstants.PLUGIN_ID, ""); //$NON-NLS-1$
                }

                Object[] result = resourceSelectionDialog.getResult();
                for (Object resultObject : result) {
                    IEditorPart dirtyResourcePart = (IEditorPart) resultObject;
                    dirtyResources.get(dirtyResourcePart).saveEditor(dirtyResourcePart, false);
                }
                return new Status(IStatus.OK, PooslConstants.PLUGIN_ID, ""); //$NON-NLS-1$
            }
        };
        uiJob.schedule();
        try {
            uiJob.join();
        } catch (InterruptedException e1) {
            LOGGER.log(Level.WARNING, "LaunchDelegate could not join UIJob for unsaved model dialog", e1);
            throw e1;
        }

        return uiJob.getResult().getSeverity() == IStatus.CANCEL;
    }

    private Map<IEditorPart, IWorkbenchPage> getDirtyResources() throws PartInitException, CoreException {
        final Map<IEditorPart, IWorkbenchPage> dirtyResources = new HashMap<>();

        IWorkbench workbench = PlatformUI.getWorkbench();
        if (workbench != null) {
            for (IWorkbenchWindow workbenchWindow : workbench.getWorkbenchWindows()) {
                for (IWorkbenchPage workbenchPage : workbenchWindow.getPages()) {
                    for (IEditorReference editorReference : workbenchPage.getEditorReferences()) {
                        if (editorReference.isDirty()) {
                            IEditorInput editorInput = editorReference.getEditorInput();
                            if (editorInput instanceof IFileEditorInput) {
                                IFileEditorInput fileEditorInput = (IFileEditorInput) editorInput;
                                String filePath = fileEditorInput.getStorage().getFullPath().toString();
                                if (filePath.startsWith("/" + project.getName() + "/")) { //$NON-NLS-1$ //$NON-NLS-2$
                                    dirtyResources.put(editorReference.getEditor(true), workbenchPage);
                                }
                            }
                        }
                    }
                }
            }
        }
        return dirtyResources;
    }

}
