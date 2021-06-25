package nl.esi.poosl.xtext.ui;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.debug.ui.actions.ToggleBreakpointAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.xtext.ui.editor.XtextEditor;

import nl.esi.poosl.xtext.GlobalConstants;

public class PooslEditor extends XtextEditor {
	private static final Logger LOGGER = Logger.getLogger(PooslEditor.class.getName());
	private static final String POOSL_BREAKPOINT_ACTION = "RulerDoubleClick";

	PooslEditor() {
		super();
		setHelpContextId("nl.esi.poosl.help.help_editor");
		checkPerspective();
	}

	/**
	 * Overrides the normal save action to apply formatting before the save, if the
	 * user has set it as preference. To avoid only formatting a selection, the
	 * selection is set to 0. The cursor will the stay at the start of the selection
	 * if there was any and formatting will be applied to the whole file
	 */
	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		IPreferencesService preferencesService = Platform.getPreferencesService();
		boolean format = preferencesService.getBoolean(GlobalConstants.PREFERENCE_PLUGIN_ID,
				GlobalConstants.PREFERENCES_FORMAT_ON_SAVE, false, null);

		if (format) {
			ITextOperationTarget target = getSourceViewer().getTextOperationTarget();
			if (target.canDoOperation(ISourceViewer.FORMAT)) {
				getSourceViewer().setSelectedRange(getSourceViewer().getSelectedRange().x, 0);
				target.doOperation(ISourceViewer.FORMAT);
			}
		}
		super.doSave(progressMonitor);
	}

	private void checkPerspective() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		IPerspectiveDescriptor perspective = window.getActivePage().getPerspective();
		if (!perspective.getId().equals("nl.esi.poosl.normalperspective")
				&& !perspective.getId().equals("nl.esi.poosl.debugperspective")) {
			IPreferencesService preferencesService = Platform.getPreferencesService();
			boolean dontask = preferencesService.getBoolean(GlobalConstants.PREFERENCE_PLUGIN_ID,
					GlobalConstants.PREFERENCES_DONT_ASK_EDIT_PERSPECTIVE, false, null);
			if (dontask) {
				boolean usepersp = preferencesService.getBoolean(GlobalConstants.PREFERENCE_PLUGIN_ID,
						GlobalConstants.PREFERENCES_OPEN_EDIT_PERSPECTIVE, false, null);
				if (usepersp) {
					openPerspective(workbench, window);
				}
			} else {
				PerspectiveDialog dialog = new PerspectiveDialog(Display.getDefault().getActiveShell());
				if (dialog.open() == Window.OK) {
					openPerspective(workbench, window);
				}
			}
		}
	}

	private void openPerspective(IWorkbench workbench, IWorkbenchWindow window) {
		try {
			workbench.showPerspective("nl.esi.poosl.normalperspective", window);
		} catch (WorkbenchException e) {
			LOGGER.log(Level.WARNING, "Could not open perspective", e);
		}
	}

	@Override
	protected void createActions() {
		ToggleBreakpointAction action = new ToggleBreakpointAction(this, getDocument(), getVerticalRuler());
		setAction(POOSL_BREAKPOINT_ACTION, action);
		super.createActions();
	}

	@Override
	protected void rulerContextMenuAboutToShow(IMenuManager menu) {
		IAction action = getAction(POOSL_BREAKPOINT_ACTION);
		menu.add(action);
		super.rulerContextMenuAboutToShow(menu);
	}
}
