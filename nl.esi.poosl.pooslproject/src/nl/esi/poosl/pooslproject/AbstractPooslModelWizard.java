package nl.esi.poosl.pooslproject;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.ide.IDE;

import nl.esi.poosl.rotalumisclient.PooslConstants;

public abstract class AbstractPooslModelWizard extends Wizard implements INewWizard {
	private static final Logger LOGGER = Logger.getLogger(AbstractPooslModelWizard.class.getName());
	private static final String WIZARD_NAME = "New Poosl model with system";
	private IWorkbench workbench;
	protected IStructuredSelection selection;

	public AbstractPooslModelWizard() {
		super();
		setWindowTitle(WIZARD_NAME);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
	}

	protected boolean openFile(IFile file) {
		boolean result = file != null;
		if (result) {
			try {
				IDE.openEditor(workbench.getActiveWorkbenchWindow().getActivePage(), file);
			} catch (PartInitException e) {
				LOGGER.log(Level.SEVERE, "Could not open new editor.", e);
			}
			IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
			if (window != null) {
				try {
					workbench.showPerspective(PooslConstants.ID_POOSL_EDIT_PERSPECTIVE, window);
				} catch (WorkbenchException e) {
					LOGGER.log(Level.SEVERE, "Could not switch to poosl perspective.", e);
				}
			}
		}
		// else no file created...result == false
		return result;
	}
}
