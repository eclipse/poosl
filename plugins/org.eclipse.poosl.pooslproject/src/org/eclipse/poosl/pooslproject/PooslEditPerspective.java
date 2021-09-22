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
package org.eclipse.poosl.pooslproject;

import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

/**
 * The PooslEditPerspective.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslEditPerspective implements IPerspectiveFactory {

    @Override
    public void createInitialLayout(IPageLayout pFactory) {
        addViews(pFactory);
        addActionSets(pFactory);
        addNewWizardShortcuts(pFactory);
        addViewShortcuts(pFactory);
    }

    private void addViews(IPageLayout factory) {
        // Creates the overall folder layout.
        // Note that each new Folder uses a percentage of the remaining
        // EditorArea.

        String leftSideId = "left"; //$NON-NLS-1$
        factory.createFolder(leftSideId, IPageLayout.LEFT, 0.25f, factory.getEditorArea());
        factory.createFolder("topleft", IPageLayout.TOP, 0.5f, leftSideId) // $NON-NLS-1$
                .addView(IPageLayout.ID_PROJECT_EXPLORER);

        factory.createFolder("bottomleft", IPageLayout.BOTTOM, 0.5f, leftSideId) // $NON-NLS-1$
                .addView(IPageLayout.ID_OUTLINE);

        IFolderLayout bottom = factory.createFolder("bottom", //$NON-NLS-1$
                IPageLayout.BOTTOM, 0.8f, factory.getEditorArea());
        bottom.addView(IConsoleConstants.ID_CONSOLE_VIEW);
        bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
        bottom.addView(IDebugUIConstants.ID_BREAKPOINT_VIEW);
        bottom.addView(IPageLayout.ID_TASK_LIST);
        bottom.addView(IPageLayout.ID_PROP_SHEET);
    }

    private void addActionSets(IPageLayout factory) {
        factory.addActionSet(IDebugUIConstants.DEBUG_ACTION_SET);
        factory.addActionSet(IDebugUIConstants.LAUNCH_ACTION_SET);
        factory.addActionSet(IDebugUIConstants.PLUGIN_ID + ".breakpointActionSet"); // $NON-NLS-1$
        factory.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);
    }

    private void addNewWizardShortcuts(IPageLayout factory) {
        factory.addNewWizardShortcut(PooslProjectConstant.PROJECT_WIZARD_ID);
        factory.addNewWizardShortcut(PooslProjectConstant.FILE_WIZARD_ID);
        factory.addNewWizardShortcut(PooslProjectConstant.SYSFILE_WIZARD_ID);
    }

    private void addViewShortcuts(IPageLayout factory) {
        factory.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
        factory.addShowViewShortcut(IPageLayout.ID_OUTLINE);
    }
}
