package nl.esi.poosl.rotalumisclient.perspectives;

import nl.esi.poosl.rotalumisclient.PooslConstants;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

public class PooslEditPerspective implements IPerspectiveFactory {
	private IPageLayout factory;

	public void createInitialLayout(IPageLayout factory) {
		this.factory = factory;
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
		factory.createFolder("left", // NON-NLS-1
				IPageLayout.LEFT, 0.25f, factory.getEditorArea());
		IFolderLayout topleft = factory.createFolder("topleft", // NON-NLS-1
				IPageLayout.TOP, 0.5f, "left");
		topleft.addView("org.eclipse.ui.navigator.ProjectExplorer");

		IFolderLayout bottomleft = factory.createFolder("bottomleft", // NON-NLS-1
				IPageLayout.BOTTOM, 0.5f, "left");
		bottomleft.addView(IPageLayout.ID_OUTLINE);

		IFolderLayout bottom = factory.createFolder("bottom", // NON-NLS-1
				IPageLayout.BOTTOM, 0.8f, factory.getEditorArea());
		bottom.addView(IConsoleConstants.ID_CONSOLE_VIEW);
		bottom.addView("org.eclipse.ui.views.ProblemView");
		bottom.addView("org.eclipse.debug.ui.BreakpointView");
		bottom.addView("org.eclipse.ui.views.TaskList");
		bottom.addView("org.eclipse.ui.views.PropertySheet");
	}

	private void addActionSets() {
		factory.addActionSet("org.eclipse.debug.ui.launchActionSet"); // NON-NLS-1
		factory.addActionSet("org.eclipse.debug.ui.breakpointActionSet"); // NON-NLS-1
		factory.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);
	}

	private void addPerspectiveShortcuts() {
		factory.addPerspectiveShortcut(PooslConstants.ID_POOSL_DEBUG_PERSPECTIVE);
	}

	private void addNewWizardShortcuts() {
		factory.addNewWizardShortcut("nl.esi.poosl.pooslproject.projectwizard");// NON-NLS-1
		factory.addNewWizardShortcut("nl.esi.poosl.pooslproject.filewizard");// NON-NLS-1
		factory.addNewWizardShortcut("nl.esi.poosl.pooslproject.filewithsystemwizard");// NON-NLS-1
	}

	private void addViewShortcuts() {
		factory.addShowViewShortcut(PooslConstants.ID_POOSL_DEBUGVIEW);
		factory.addShowViewShortcut(PooslConstants.ID_POOSL_PETVIEW);
		factory.addShowViewShortcut(PooslConstants.ID_POOSL_VARIABLESVIEW);
		factory.addShowViewShortcut(PooslConstants.ID_POOSL_SEQUENCEDIAGRAMVIEW);
		factory.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
		factory.addShowViewShortcut(IPageLayout.ID_OUTLINE);
	}
}
