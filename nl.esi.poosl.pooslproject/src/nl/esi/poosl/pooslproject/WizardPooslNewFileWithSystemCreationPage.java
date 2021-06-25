package nl.esi.poosl.pooslproject;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

public class WizardPooslNewFileWithSystemCreationPage extends WizardNewFileCreationPage {

	public WizardPooslNewFileWithSystemCreationPage(String pageName, IStructuredSelection selection) {
		super(pageName, selection);
		setTitle("POOSL model with System Wizard");
		setDescription("Create a new POOSL model with System");
		setFileExtension("poosl");
	}
}
