package nl.esi.poosl.rotalumisclient.views;

import java.util.Collections;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import nl.esi.poosl.generatedxmlclasses.TInstanceType;
import nl.esi.poosl.rotalumisclient.PooslConstants;
import nl.esi.poosl.rotalumisclient.debug.PooslDebugHelper;
import nl.esi.poosl.rotalumisclient.debug.PooslDebugTarget;
import nl.esi.poosl.rotalumisclient.views.diagram.PooslTreeContentProvider;
import nl.esi.poosl.rotalumisclient.views.diagram.PooslTreeLabelProvider;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

public class PooslViewButtonHandler implements IHandler {
	private static final Logger LOGGER = Logger.getLogger(PooslViewButtonHandler.class.getName());

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		DebugPlugin plugin = DebugPlugin.getDefault();
		if (event.getCommand().getId().equals(PooslConstants.COMMAND_DIAGRAM_VIEW)) {
			String action = event.getParameter("nl.esi.poosl.rotalumisclient.views.diagramviewaction");
			if (action.equals(PooslConstants.COMMAND_DIAGRAM_VIEW_CLEAR_VIEW)) {
				DebugEvent debugEvent = new DebugEvent(this, DebugEvent.MODEL_SPECIFIC,
						PooslConstants.CLEAR_COMM_EVENTS);
				if (plugin != null) {
					plugin.fireDebugEventSet(new DebugEvent[] { debugEvent });
				}
			} else if (action.equals(PooslConstants.COMMAND_DIAGRAM_VIEW_SCROLL_LOCK)) {
				final IWorkbench workbench = PlatformUI.getWorkbench();
				workbench.getDisplay().asyncExec(new Runnable() {
					@Override
					public void run() {
						IViewPart view = workbench.getActiveWorkbenchWindow().getActivePage()
								.findView(PooslConstants.ID_POOSL_SEQUENCEDIAGRAMVIEW);
						if (view != null) {
							PooslSequenceDiagramView sequenceDiagramView = (PooslSequenceDiagramView) view;
							Command command = event.getCommand();
							boolean newValue = true;
							try {
								newValue ^= HandlerUtil.toggleCommandState(command);
							} catch (ExecutionException e) {
								LOGGER.log(Level.WARNING, e.getMessage(), e);
							}
							sequenceDiagramView.setScrollLock(newValue);
						}
					}
				});
			} else if (action.equals(PooslConstants.COMMAND_DIAGRAM_VIEW_SETTINGS)) {
				Object[] instances = getInstances().entrySet().toArray();
				ILabelProvider lp = new PooslTreeLabelProvider();
				ITreeContentProvider cp = new PooslTreeContentProvider(instances);
				PooslSequenceDiagramConfigurationView dialog = new PooslSequenceDiagramConfigurationView(
						Display.getDefault().getActiveShell(), lp, cp);
				dialog.setTitle("Sequence diagram settings");
				dialog.setMessage(
						"Uncheck instances to filter them out.\nCheck clusters to hide the containing instances.");
				dialog.setInput(instances);
				PooslDebugTarget debugTarget = PooslDebugHelper.getCurrentDebugTarget();
				dialog.setInitialSelections(
						debugTarget.getPooslSequenceDiagramMessageProvider().getMessageFilter().entrySet().toArray());
				dialog.setInitialFilterSetting(
						debugTarget.getPooslSequenceDiagramMessageProvider().isFilterSettingEnabled());
				dialog.setMessageCount(debugTarget.getPooslSequenceDiagramMessageProvider().getMessageSerialNumber());
				int buttonPressed = dialog.open();
				if (buttonPressed == Window.OK) {
					Object[] result = dialog.getResult();
					boolean filterSetting = dialog.isFilterSettingEnabled();
					try {
						if (filterSetting != debugTarget.getPooslSequenceDiagramMessageProvider()
								.isFilterSettingEnabled() || filterSetting) {
							debugTarget.getPooslSequenceDiagramMessageProvider().setFilterSetting(filterSetting);
						}
						debugTarget.getPooslSequenceDiagramMessageProvider().setMessageFilter(result);
					} catch (CoreException e) {
						LOGGER.log(Level.WARNING, "Could not set message filter settings.", e);
					}
				}
			}
		} else if (event.getCommand().getId().equals(PooslConstants.COMMAND_DIAGRAM_SETUP)) {
			DebugEvent debugEvent = new DebugEvent(this, DebugEvent.MODEL_SPECIFIC, PooslConstants.COMM_EVENTS_CHANGE);
			debugEvent.setData(!HandlerUtil.toggleCommandState(event.getCommand()));
			if (plugin != null) {
				plugin.fireDebugEventSet(new DebugEvent[] { debugEvent });
			}
		}
		return null;
	}

	private Map<String, TInstanceType> getInstances() {
		Map<String, TInstanceType> instances = null;
		PooslDebugTarget debugTarget = PooslDebugHelper.getCurrentDebugTarget();
		if (debugTarget != null) {
			instances = debugTarget.getInstances();
		} else {
			return Collections.emptyMap();
		}
		return instances;
	}

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// HandlerListeners are not used by this handler
	}

	@Override
	public boolean isEnabled() {
		PooslDebugTarget debugTarget = PooslDebugHelper.getCurrentDebugTarget();
		if (debugTarget != null) {
			return !debugTarget.isTerminated();
		}
		return false;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// HandlerListeners are not used by this handler
	}

	@Override
	public void dispose() {
		// Nothing to dispose
	}
}
