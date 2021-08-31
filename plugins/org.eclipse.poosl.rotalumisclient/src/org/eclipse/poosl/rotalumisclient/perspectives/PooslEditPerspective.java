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
package org.eclipse.poosl.rotalumisclient.perspectives;

import org.eclipse.poosl.rotalumisclient.PooslConstants;
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
    private static final String LEFT_LITERAL = "left"; //$NON-NLS-1$

    private IPageLayout factory;

    @Override
    public void createInitialLayout(IPageLayout pFactory) {
        this.factory = pFactory;
        addViews();
        addActionSets();
        addNewWizardShortcuts();
        addPerspectiveShortcuts();
        addViewShortcuts();
    }

    private void addViews() {
        // Creates the overall folder layout.
        // Note that each new Folder uses a percentage of the remaining
        // EditorArea.
        factory.createFolder(LEFT_LITERAL, IPageLayout.LEFT, 0.25f, factory.getEditorArea());
        IFolderLayout topleft = factory.createFolder("topleft", // NON-NLS-1 //$NON-NLS-1$
                IPageLayout.TOP, 0.5f, LEFT_LITERAL);
        topleft.addView("org.eclipse.ui.navigator.ProjectExplorer"); //$NON-NLS-1$

        IFolderLayout bottomleft = factory.createFolder("bottomleft", // NON-NLS-1 //$NON-NLS-1$
                IPageLayout.BOTTOM, 0.5f, LEFT_LITERAL);
        bottomleft.addView(IPageLayout.ID_OUTLINE);

        IFolderLayout bottom = factory.createFolder("bottom", // NON-NLS-1 //$NON-NLS-1$
                IPageLayout.BOTTOM, 0.8f, factory.getEditorArea());
        bottom.addView(IConsoleConstants.ID_CONSOLE_VIEW);
        bottom.addView("org.eclipse.ui.views.ProblemView"); //$NON-NLS-1$
        bottom.addView("org.eclipse.debug.ui.BreakpointView"); //$NON-NLS-1$
        bottom.addView("org.eclipse.ui.views.TaskList"); //$NON-NLS-1$
        bottom.addView("org.eclipse.ui.views.PropertySheet"); //$NON-NLS-1$
    }

    private void addActionSets() {
        factory.addActionSet("org.eclipse.debug.ui.launchActionSet"); // NON-NLS-1 //$NON-NLS-1$
        factory.addActionSet("org.eclipse.debug.ui.breakpointActionSet"); // NON-NLS-1 //$NON-NLS-1$
        factory.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);
    }

    private void addPerspectiveShortcuts() {
        // XXX: Circular dependency !
        // This must be declared in perspective extension
        factory.addPerspectiveShortcut(PooslConstants.ID_POOSL_DEBUG_PERSPECTIVE); 
    }

    private void addNewWizardShortcuts() {
        // XXX: Circular dependency !
        // This must be declared in perspective extension
        factory.addNewWizardShortcut("org.eclipse.poosl.pooslproject.projectwizard"); // NON-NLS-1 //$NON-NLS-1$
        factory.addNewWizardShortcut("org.eclipse.poosl.pooslproject.filewizard"); // NON-NLS-1 //$NON-NLS-1$
        factory.addNewWizardShortcut("org.eclipse.poosl.pooslproject.filewithsystemwizard"); // NON-NLS-1 //$NON-NLS-1$
    }

    private void addViewShortcuts() {
        factory.addShowViewShortcut(PooslConstants.ID_POOSL_DEBUGVIEW); // from rotalumisclient
        factory.addShowViewShortcut(PooslConstants.ID_POOSL_PETVIEW); // from rotalumisclient
        factory.addShowViewShortcut(PooslConstants.ID_POOSL_VARIABLESVIEW); // from rotalumisclient
        factory.addShowViewShortcut(PooslConstants.ID_POOSL_SEQUENCEDIAGRAMVIEW); // from rotalumisclient
        factory.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
        factory.addShowViewShortcut(IPageLayout.ID_OUTLINE);
    }
}
