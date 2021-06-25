package nl.esi.poosl.pooslproject;

import org.eclipse.core.resources.IFile;

import org.eclipse.ui.INewWizard;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

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
