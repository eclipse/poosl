package nl.esi.poosl.pooslproject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

public class PooslNewModelWithSystemWizard extends AbstractPooslModelWizard implements INewWizard {
	private static final Logger LOGGER = Logger.getLogger(PooslNewModelWithSystemWizard.class.getName());
	private static final String WIZARD_NAME = "New Poosl model with System";
	private WizardNewFileCreationPage pageOne;

	@Override
	public boolean performFinish() {
		IFile file = pageOne.createNewFile();
		addTemplate(file);
		return openFile(file);
	}

	@Override
	public void addPages() {
		super.addPages();
		pageOne = new WizardPooslNewFileWithSystemCreationPage(WIZARD_NAME, selection);
		addPage(pageOne);
	}

	private static void addTemplate(IFile file) {
		URL url;
		InputStream inputStream = null;

		try {
			url = new URL("platform:/plugin/nl.esi.poosl.pooslproject/templates/system.poosl");
			inputStream = url.openConnection().getInputStream();
			file.setContents(inputStream, 0, null);
		} catch (IOException | CoreException e) {
			LOGGER.log(Level.FINE, "Could not add template to poosl file.");
		}
	}

}
