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

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.debug.ui.actions.ToggleBreakpointAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.poosl.pooslproject.PooslProjectConstant;
import org.eclipse.poosl.xtext.GlobalConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.xtext.ui.editor.XtextEditor;

/**
 * The PooslEditor.
 *
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslEditor extends XtextEditor {
    private static final ILog LOGGER = Platform.getLog(PooslEditor.class);

    private static final String POOSL_BREAKPOINT_ACTION = "RulerDoubleClick"; //$NON-NLS-1$

    PooslEditor() {
        setHelpContextId("org.eclipse.poosl.help.help_editor"); //$NON-NLS-1$
        checkPerspective();
    }

    /**
     * Overrides the normal save action to apply formatting before the save, if
     * the user has set it as preference. To
     * avoid only formatting a selection, the selection is set to 0. The cursor
     * will the stay at the start of the
     * selection if there was any and formatting will be applied to the whole
     * file
     */
    @Override
    public void doSave(IProgressMonitor progressMonitor) {
        IPreferencesService preferencesService = Platform.getPreferencesService();
        boolean format = preferencesService.getBoolean(GlobalConstants.PREFERENCE_PLUGIN_ID,
                GlobalConstants.PREFERENCES_FORMAT_ON_SAVE, false, null);

        if (format) {
            ITextOperationTarget target = getSourceViewer().getTextOperationTarget();
            if (target.canDoOperation(ISourceViewer.FORMAT)) {
                getSourceViewer().setSelectedRange(getSourceViewer().getSelectedRange().x, 0);
                target.doOperation(ISourceViewer.FORMAT);
            }
        }
        super.doSave(progressMonitor);
    }

    private void checkPerspective() {
        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        IPerspectiveDescriptor perspective = window.getActivePage().getPerspective();
        if (!perspective.getId().equals(PooslProjectConstant.ID_POOSL_EDIT_PERSPECTIVE)
                // XXX should be dynamically provided (extension point)
                && !perspective.getId().equals("org.eclipse.poosl.debugperspective")) { //$NON-NLS-1$
            IPreferencesService preferencesService = Platform.getPreferencesService();
            boolean dontask = preferencesService.getBoolean(GlobalConstants.PREFERENCE_PLUGIN_ID,
                    GlobalConstants.PREFERENCES_DONT_ASK_EDIT_PERSPECTIVE, false, null);
            if (dontask) {
                boolean usepersp = preferencesService.getBoolean(
                        GlobalConstants.PREFERENCE_PLUGIN_ID,
                        GlobalConstants.PREFERENCES_OPEN_EDIT_PERSPECTIVE, false, null);
                if (usepersp) {
                    openPerspective(workbench, window);
                }
            } else {
                PerspectiveDialog dialog = new PerspectiveDialog(
                        Display.getDefault().getActiveShell());
                if (dialog.open() == Window.OK) {
                    openPerspective(workbench, window);
                }
            }
        }
    }

    private void openPerspective(IWorkbench workbench, IWorkbenchWindow window) {
        try {
            workbench.showPerspective(PooslProjectConstant.ID_POOSL_EDIT_PERSPECTIVE, window);
        } catch (WorkbenchException e) {
            LOGGER.warn("Could not open perspective", e); //$NON-NLS-1$
        }
    }

    @Override
    protected void createActions() {
        ToggleBreakpointAction action = new ToggleBreakpointAction(this, getDocument(),
                getVerticalRuler());
        setAction(POOSL_BREAKPOINT_ACTION, action);
        super.createActions();
    }

    @Override
    protected void rulerContextMenuAboutToShow(IMenuManager menu) {
        IAction action = getAction(POOSL_BREAKPOINT_ACTION);
        menu.add(action);
        super.rulerContextMenuAboutToShow(menu);
    }
}
