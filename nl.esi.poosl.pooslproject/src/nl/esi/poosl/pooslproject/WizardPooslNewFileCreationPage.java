package nl.esi.poosl.pooslproject;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

public class WizardPooslNewFileCreationPage extends WizardNewFileCreationPage {

	public WizardPooslNewFileCreationPage(String pageName, IStructuredSelection selection) {
		super(pageName, selection);
		setTitle("POOSL model Wizard");
		setDescription("Create a new POOSL model with System");
		setFileExtension("poosl");
	}
}
