package nl.esi.poosl.legacysupport.poosl2xml;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.handlers.HandlerUtil;

public class CommandHandler implements IHandler {
	private static final Logger LOGGER = Logger.getLogger(CommandHandler.class
			.getName());

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// This handler does not use listeners
	}

	@Override
	public void dispose() {
		// Nothing to dispose
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		Boolean asSheSim = Boolean.parseBoolean(event
				.getParameter("nl.esi.poosl.commands.generatexml.shesim"));

		if (selection instanceof IStructuredSelection) {
			Set<IProject> projects = new HashSet<>();

			for (Object object : ((IStructuredSelection) selection).toList()) {
				if (object instanceof IFile) {
					IFile file = (IFile) object;
					if (file2Xml(asSheSim, file)) {
						projects.add(file.getProject());
					}					
				}
			}
			for (IProject project : projects) {
				refreshProject(project);
			}
		}

		if (selection instanceof TextSelection) {
			IEditorInput editorInput = HandlerUtil.getActiveEditorInput(event);
			if (editorInput instanceof IFileEditorInput) {
				IFile file = ((IFileEditorInput) editorInput).getFile();

				if (file2Xml(asSheSim, file)) {
					refreshProject(file.getProject());
				}				
			}
		}
		return null;
	}

	private void refreshProject(IProject project) {
		try {
			project.refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			LOGGER.log(Level.WARNING, "Xml generation could not refresh project \"" + project.getName()
					+ "\" after generation.", e);
		}
	}

	private Boolean file2Xml(Boolean asSheSim, IFile file) {
		try {		
			Poosl2xml.getInstanceRuntime().exportPoosl2Xml(file.getLocation().toOSString(), null, asSheSim, true);			 	
		} catch (final PooslValidationException e) {
			LOGGER.log(Level.WARNING, e.getMessage(), e);
			showXmlError(e);
			return false;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return false;
		}
		return true;
	}

	private void showXmlError(final PooslValidationException e) {
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				MessageDialog.openError(
						Display.getDefault().getActiveShell(),
						"Could not generate XML",
						"The model contains validation errors:\n"
								+ e.getMessage());
			}
		});
	}

	@Override
	public boolean isEnabled() {
		// This commands visible when is checked in the plugin.xml so when
		// visible it is also available
		return true;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// This handler does not use listeners
	}

}
