package org.eclipse.poosl.pooslproject;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

public class WizardPooslNewFileCreationPage extends WizardNewFileCreationPage {

    public WizardPooslNewFileCreationPage(String pageName, IStructuredSelection selection) {
        super(pageName, selection);
        setTitle("POOSL model Wizard");
        setDescription("Create a new POOSL model with System");
        setFileExtension("poosl"); //$NON-NLS-1$
    }
}
