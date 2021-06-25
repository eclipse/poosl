package nl.esi.poosl.rotalumisclient.debug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.State;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.progress.UIJob;

import nl.esi.poosl.generatedxmlclasses.TCommunicationEvent;
import nl.esi.poosl.generatedxmlclasses.TInstanceType;
import nl.esi.poosl.rotalumisclient.PooslConstants;
import nl.esi.poosl.rotalumisclient.views.PooslSequenceDiagramView;
import nl.esi.poosl.rotalumisclient.views.diagram.PooslDiagramMessage;

public class PooslSequenceDiagramMessageProvider {
	private static final int UPDATE_INTERVAL_MS = 50;
	private static final int DEFAULT_MESSAGE_BUFFER_SIZE = 1000;

	private PooslDebugTarget target;
	private boolean filterSetting;
	private int messageSerialNumber;

	private final Map<String, TInstanceType> messageFilter = new HashMap<>();
	private final PooslDiagramMessage[] messageArray;
	private final List<String> messageOrder;

	private final UpdateDiagramViewJob diagramUpdateJob;

	public PooslSequenceDiagramMessageProvider(PooslDebugTarget target) throws CoreException {
		this.target = target;
		diagramUpdateJob = new UpdateDiagramViewJob("Updating sequence diagam", target);

		ILaunchConfigurationWorkingCopy launchConfigurationWorkingCopy = target.getLaunch().getLaunchConfiguration()
				.getWorkingCopy();
		Set<String> visibleInstances = launchConfigurationWorkingCopy.getAttribute(
				PooslConstants.CONFIGURATION_ATTRIBUTE_SEQUENCE_DIAGRAM_FILTER_VISIBLE, new HashSet<String>());
		Set<String> invisibleInstances = launchConfigurationWorkingCopy.getAttribute(
				PooslConstants.CONFIGURATION_ATTRIBUTE_SEQUENCE_DIAGRAM_FILTER_INVISIBLE, new HashSet<String>());
		messageOrder = launchConfigurationWorkingCopy
				.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SEQUENCE_DIAGRAM_ORDER, new ArrayList<String>());
		filterSetting = launchConfigurationWorkingCopy
				.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SEQUENCE_DIAGRAM_FILTER_SETTING, true);

		Map<String, TInstanceType> allInstances = target.getInstances();

		if (visibleInstances.isEmpty()) {
			// no previous filter exists so create a default one with all
			// instances visible
			invisibleInstances.clear();
			messageOrder.clear();
			messageFilter.putAll(target.getProcessInstances());
			for (Entry<String, TInstanceType> entry : allInstances.entrySet()) {
				if (entry.getValue() == TInstanceType.CLUSTER) {
					invisibleInstances.add(entry.getKey());
				} else if (entry.getValue() == TInstanceType.PROCESS) {
					visibleInstances.add(entry.getKey());
					messageOrder.add(entry.getKey());
				}
			}
		} else {
			// create filter based on the launch configuration (and remove
			// no longer existing instances from the list)
			Set<String> visibleInstancesToRemove = new HashSet<>();
			for (String visibleInstance : visibleInstances) {
				if (allInstances.containsKey(visibleInstance)) {
					messageFilter.put(visibleInstance, allInstances.get(visibleInstance));
				} else {
					visibleInstancesToRemove.add(visibleInstance);
				}
			}
			visibleInstances.removeAll(visibleInstancesToRemove);
			messageOrder.removeAll(visibleInstancesToRemove);

			Set<String> invisibleInstancesToRemove = new HashSet<>();
			for (String invisibleInstance : invisibleInstances) {
				if (!allInstances.containsKey(invisibleInstance)) {
					invisibleInstancesToRemove.add(invisibleInstance);
				}
			}
			invisibleInstances.removeAll(invisibleInstancesToRemove);

			// add new instances of the model to the visible filter
			for (Entry<String, TInstanceType> entry : allInstances.entrySet()) {
				String instance = entry.getKey();
				if (!visibleInstances.contains(instance) && !invisibleInstances.contains(instance)) {
					if (entry.getValue() == TInstanceType.PROCESS) {
						visibleInstances.add(instance);
						messageOrder.add(instance);
						messageFilter.put(instance, entry.getValue());
					} else if (entry.getValue() == TInstanceType.CLUSTER) {
						invisibleInstances.add(instance);
					}
				}
			}
		}

		launchConfigurationWorkingCopy.setAttribute(
				PooslConstants.CONFIGURATION_ATTRIBUTE_SEQUENCE_DIAGRAM_FILTER_INVISIBLE, invisibleInstances);
		launchConfigurationWorkingCopy
				.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SEQUENCE_DIAGRAM_FILTER_VISIBLE, visibleInstances);
		launchConfigurationWorkingCopy.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SEQUENCE_DIAGRAM_ORDER,
				messageOrder);
		launchConfigurationWorkingCopy.doSave();

		int maxMessageBufferSize = Platform.getPreferencesService().getInt(PooslConstants.PLUGIN_ID,
				PooslConstants.PREFERENCES_MESSAGE_BUFFER_SIZE, DEFAULT_MESSAGE_BUFFER_SIZE, null);
		messageArray = new PooslDiagramMessage[maxMessageBufferSize];
	}

	public void updateSequenceDiagramViewEventSetting() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				ICommandService commandService = window.getService(ICommandService.class);
				Command command = commandService.getCommand("nl.esi.poosl.rotalumisclient.views.diagram.setupaction");
				State state = command.getState("org.eclipse.ui.commands.toggleState");
				boolean enabled = (target == null) || target.isCommEventsEnabled();
				state.setValue(enabled);
			}
		});
	}

	public Map<String, TInstanceType> getMessageFilter() {
		return messageFilter;
	}

	public PooslDiagramMessage[] getMessages() {
		return messageArray;
	}

	public void addMessage(TCommunicationEvent event) {
		boolean validMessage;
		if (filterSetting) {
			String fromProcess = getVisibleLifeLineName(event.getReceiver().getProcessPath());
			String toProcess = getVisibleLifeLineName(event.getSender().getProcessPath());

			// When from and to process are equal this means a message is within
			// the cluster (so discarded)
			validMessage = !fromProcess.equals(toProcess);
		} else {
			validMessage = true;
		}

		if (validMessage) {
			PooslDiagramMessage message = new PooslDiagramMessage(event);
			messageArray[messageSerialNumber % messageArray.length] = message;
			messageSerialNumber++;
			// Schedule the update of the messages in the diagram view with a
			// delay of the defined interval.
			// This will make sure that every call made to add more messages
			// within this update interval will only trigger one more schedule of the
			// job once the current one is finished.

			diagramUpdateJob.schedule(UPDATE_INTERVAL_MS);
		}
	}

	public PooslDiagramMessage getLastMessage() {
		if (messageSerialNumber != 0) {
			return messageArray[(messageSerialNumber - 1) % messageArray.length];
		} else {
			return null;
		}
	}

	public void clearMessages() {
		messageSerialNumber = 0;
		if (messageArray != null) {
			Arrays.fill(messageArray, null);
		}
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				PooslSequenceDiagramView view = findSequenceView();
				if (view != null) {
					view.clearView(target);
				}
			}
		});
	}

	private String getVisibleLifeLineName(String instanceName) {
		String visibleInstanceName = instanceName;
		if (messageFilter.containsKey(visibleInstanceName)) {
			return visibleInstanceName;
		} else {
			int lastIndex = 0;
			while ((lastIndex = visibleInstanceName.lastIndexOf('/')) != -1) {
				visibleInstanceName = visibleInstanceName.substring(0, lastIndex);
				if (messageFilter.containsKey(visibleInstanceName)) {
					return visibleInstanceName;
				}
			}
		}
		return "";
	}

	public void setMessageFilter(Object[] filter) throws CoreException {
		messageFilter.clear();
		for (Object object : filter) {
			if (object != null) {
				Entry<?, ?> entry = (Entry<?, ?>) object;
				messageFilter.put((String) entry.getKey(), (TInstanceType) entry.getValue());
			}
		}
		ILaunchConfigurationWorkingCopy launchConfigurationWorkingCopy = target.getLaunch().getLaunchConfiguration()
				.getWorkingCopy();
		launchConfigurationWorkingCopy.setAttribute(
				PooslConstants.CONFIGURATION_ATTRIBUTE_SEQUENCE_DIAGRAM_FILTER_VISIBLE, messageFilter.keySet());
		Set<String> invisibleInstances = new HashSet<>();
		invisibleInstances.addAll(target.getInstances().keySet());
		invisibleInstances.removeAll(messageFilter.keySet());
		launchConfigurationWorkingCopy.setAttribute(
				PooslConstants.CONFIGURATION_ATTRIBUTE_SEQUENCE_DIAGRAM_FILTER_INVISIBLE, invisibleInstances);
		launchConfigurationWorkingCopy.doSave();

		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				PooslSequenceDiagramView view = findSequenceView();
				if (view != null) {
					view.setFilter(messageFilter);
				}
			}
		});
	}

	public void setMessageOrder(List<String> messageOrder) throws CoreException {
		this.messageOrder.clear();
		this.messageOrder.addAll(messageOrder);
		ILaunchConfigurationWorkingCopy launchConfigurationWorkingCopy = target.getLaunch().getLaunchConfiguration()
				.getWorkingCopy();
		launchConfigurationWorkingCopy.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SEQUENCE_DIAGRAM_ORDER,
				messageOrder);
		launchConfigurationWorkingCopy.doSave();
	}

	private class UpdateDiagramViewJob extends UIJob {
		private PooslDebugTarget target;

		public UpdateDiagramViewJob(String name, PooslDebugTarget target) {
			super(name);
			this.target = target;
		}

		@Override
		public IStatus runInUIThread(IProgressMonitor monitor) {
			if (target != null && PooslDebugHelper.isActiveDebugTarget(target) && !target.isTerminated()) {
				PooslSequenceDiagramView view = findSequenceView();
				if (view != null) {
					view.update(messageArray, false, messageSerialNumber);
					return Status.OK_STATUS;
				}
			}
			return Status.CANCEL_STATUS;
		}
	}

	private static PooslSequenceDiagramView findSequenceView() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		if (window != null) {
			IViewPart view = window.getActivePage().findView(PooslConstants.ID_POOSL_SEQUENCEDIAGRAMVIEW);
			if (view instanceof PooslSequenceDiagramView) {
				return (PooslSequenceDiagramView) view;
			}
		}
		return null;
	}

	public List<String> getMessageOrder() {
		return messageOrder;
	}

	public int getMessageSerialNumber() {
		return messageSerialNumber;
	}

	public boolean isFilterSettingEnabled() {
		return filterSetting;
	}

	public void setFilterSetting(boolean filterSetting) throws CoreException {
		// Changing the filter setting will clear all messages first.
		clearMessages();
		this.filterSetting = filterSetting;
		ILaunchConfigurationWorkingCopy launchConfigurationWorkingCopy = target.getLaunch().getLaunchConfiguration()
				.getWorkingCopy();
		launchConfigurationWorkingCopy
				.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SEQUENCE_DIAGRAM_FILTER_SETTING, filterSetting);
		launchConfigurationWorkingCopy.doSave();
	}

	public void dispose() {
		messageSerialNumber = 0;
		if (messageArray != null) {
			Arrays.fill(messageArray, null);
		}
		if (!PlatformUI.getWorkbench().getDisplay().isDisposed()) {
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				@Override
				public void run() {
					PooslSequenceDiagramView view = findSequenceView();
					if (view != null) {
						view.clearView(target);
					}
					diagramUpdateJob.target = null;
					target = null;
				}
			});
		}
	}
}
