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

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

/**
 * The PooslNewProjectWizard.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslNewProjectWizard extends Wizard implements INewWizard {
    private static final Logger LOGGER = Logger.getLogger(PooslNewProjectWizard.class.getName());

    private static final String WIZARD_NAME = "New Poosl project";

    private WizardNewProjectCreationPage pageOne;

    /**
     * Constructor.
     */
    public PooslNewProjectWizard() {
        setWindowTitle(WIZARD_NAME);
    }

    @Override
    public void addPages() {
        pageOne = new WizardNewProjectCreationPage(WIZARD_NAME);
        pageOne.setTitle(WIZARD_NAME);
        pageOne.setDescription("This wizard creates a new Poosl project");
        addPage(pageOne);
    }

    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        // No need to save these variables for late use
    }

    @Override
    public boolean performFinish() {
        String name = pageOne.getProjectName();
        URI location = !pageOne.useDefaults() ? pageOne.getLocationURI() : null;

        try {
            ResourcesPlugin.getWorkspace().run(monitor -> {
                PooslProjectSupport.createProject(name, location);
            }, null);
            IWorkbench workbench = PlatformUI.getWorkbench();
            IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
            if (window != null) {
                try {
                    workbench.showPerspective(PooslProjectSupport.ID_POOSL_EDIT_PERSPECTIVE, window);
                } catch (WorkbenchException e) {
                    LOGGER.log(Level.WARNING, "Could switch to poosl perspective.", e);
                }
            }
            return true;
        } catch (CoreException e) {
            if (e.getStatus().getCode() == IResourceStatus.CASE_VARIANT_EXISTS) {
                pageOne.setErrorMessage("A project with that name but different capitalization already exists in the workspace.");
            }
            LOGGER.log(Level.SEVERE, "Error trying to create project.", e);
            return false;
        }
    }

}
