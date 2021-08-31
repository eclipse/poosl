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

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

/**
 * The PooslNewModelWizard.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslNewModelWizard extends AbstractPooslModelWizard implements INewWizard {
    private static final String WIZARD_NAME = "New Poosl model";

    private WizardNewFileCreationPage pageOne;

    public PooslNewModelWizard() {
        super();
        setWindowTitle(WIZARD_NAME);
    }

    @Override
    public boolean performFinish() {
        IFile file = pageOne.createNewFile();
        return openFile(file);
    }

    @Override
    public void addPages() {
        super.addPages();
        pageOne = new WizardPooslNewFileCreationPage(WIZARD_NAME, selection);
        addPage(pageOne);
    }
}
