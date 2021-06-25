package nl.esi.poosl.sirius.debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.UIJob;

import nl.esi.poosl.rotalumisclient.extension.ExternDebugMessage;
import nl.esi.poosl.sirius.helpers.GraphicalEditorHelper;

public class GraphicalDebugUpdater {
	private static final Logger LOGGER = Logger.getLogger(GraphicalDebugUpdater.class.getName());

	private static final Map<String, List<URI>> lockedFiles = new HashMap<>();

	/**
	 * This map contains messages that need still need to be drawn. When the message
	 * is drawn it is removed from this map to avoid the overhead of redrawing
	 */
	private final ConcurrentHashMap<String, PooslDrawMessage> drawMessages = new ConcurrentHashMap<>();

	/**
	 * This map contains the last message of the launch. When a new communication
	 * diagram is opened it retrieves the last message from this map.
	 */
	private final ConcurrentHashMap<String, PooslDrawMessage> lastMessages = new ConcurrentHashMap<>();

	/**
	 * This set contains which launches are not used yet and might need setup
	 */
	private final Set<String> notSetupLaunches = new HashSet<>();

	private final Map<String, Map<String, String>> instancePortMappings = new HashMap<>();
	private final Map<String, PathCalculator> pathCalculators = new HashMap<>();

	private final UpdateAllDiagramsViewJob updateJob = new UpdateAllDiagramsViewJob();

	public void addMessage(ExternDebugMessage message) {
		String launchID = message.getLaunch();
		Map<String, String> instancePortMap = instancePortMappings.get(launchID);
		if (instancePortMap != null) {
			PooslDrawMessage pooslDrawMessage = new PooslDrawMessage(message, instancePortMap);
			updateMessageCounters(pooslDrawMessage);
			drawMessages.put(launchID, pooslDrawMessage);
			lastMessages.put(launchID, pooslDrawMessage);

			// No need to start updatejob at all if no representations/diagrams
			// exist
			Map<Session, Set<DRepresentationDescriptor>> session2Descriptors = UpdateHelper
					.getLaunchRepresentations(drawMessages.keySet());
			if (!session2Descriptors.isEmpty()) {
				drawMessages();
			}
		} else {
			LOGGER.log(Level.INFO,
					"Received message but there is no instanceport mapping to calculate messagepath, it's likely that the message was received after the debug sessions was terminated.");
		}
	}

	private void updateMessageCounters(PooslDrawMessage pooslDrawMessage) {
		PathCalculator calculator = getPathCalculator(pooslDrawMessage.getMessage().getLaunch());
		calculator.addMessage(pooslDrawMessage);
	}

	private PathCalculator getPathCalculator(String launchID) {
		PathCalculator calc = pathCalculators.get(launchID);
		if (calc == null) {
			calc = new PathCalculator(launchID);
			pathCalculators.put(calc.launchID, calc);
		}
		return calc;
	}

	private void drawMessages() {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				updateJob.schedule(200);
			}
		});
	}

	public void launchStopped(String launchID) {
		pathCalculators.remove(launchID);
		drawMessages.remove(launchID);
		lastMessages.remove(launchID);
		instancePortMappings.remove(launchID);
		lockedFiles.remove(launchID);
		verifyLockedFiles();
	}

	public static void verifyLockedFiles() {
		try {
			List<String> currentLaunches = GraphicalEditorHelper.getCurrentLaunchIDs();
			for (Iterator<String> iterator = lockedFiles.keySet().iterator(); iterator.hasNext();) {
				String launchID = iterator.next();
				if (!currentLaunches.contains(launchID)) {
					iterator.remove();
				}
			}
		} catch (CoreException e) {
			LOGGER.log(Level.WARNING, "Could not get current launches.");
		}
	}

	public void draw(Session session, DRepresentationDescriptor descriptor) {
		if (descriptor != null) {
			final UpdateSingleDiagramViewJob singleUpdateJob = new UpdateSingleDiagramViewJob(session, descriptor);
			UpdateHelper.getPooslShell().getDisplay().asyncExec(new Runnable() {
				@Override
				public void run() {
					singleUpdateJob.schedule();
				}
			});
		} else {
			LOGGER.log(Level.WARNING, "No debug representation found to show.");
		}
	}

	class UpdateAllDiagramsViewJob extends UIJob {
		public UpdateAllDiagramsViewJob() {
			super("Updating All Diagrams");
		}

		@Override
		public IStatus runInUIThread(IProgressMonitor monitor) {
			Map<String, PooslDrawMessage> currentDrawMessages = new HashMap<>();

			for (Iterator<String> iterator = drawMessages.keySet().iterator(); iterator.hasNext();) {
				PooslDrawMessage drawMessage = drawMessages.remove(iterator.next());
				currentDrawMessages.put(drawMessage.getMessage().getLaunch(), drawMessage);
			}

			Map<Session, Set<DRepresentationDescriptor>> session2Descriptors = UpdateHelper
					.getLaunchRepresentations(currentDrawMessages.keySet());
			try {
				for (Entry<Session, Set<DRepresentationDescriptor>> entry : session2Descriptors.entrySet()) {
					Session session = entry.getKey();
					Set<DRepresentationDescriptor> descriptors = entry.getValue();

					ArrayList<DRepresentationDescriptor> diagrams2Color = new ArrayList<>();

					IEditingSession editingSession = SessionUIManager.INSTANCE.getUISession(session);
					if (editingSession != null) {
						for (DRepresentationDescriptor descriptor : descriptors) {
							DialectEditor editor = editingSession.getEditor(descriptor.getRepresentation());
							if (editor != null) {
								diagrams2Color.add(descriptor);
							}
						}
					}

					session.getTransactionalEditingDomain().getCommandStack().execute(
							new ColorChannelCommand(session, diagrams2Color, currentDrawMessages, pathCalculators));
				}
			} catch (Exception e) {
				return Status.CANCEL_STATUS;
			}
			return Status.OK_STATUS;
		}
	}

	class UpdateSingleDiagramViewJob extends UIJob {
		private DRepresentationDescriptor descriptor;
		private Session session;

		public UpdateSingleDiagramViewJob(Session session, DRepresentationDescriptor descriptor) {
			super("Updating Single Diagram");
			this.session = session;
			this.descriptor = descriptor;
		}

		@Override
		public IStatus runInUIThread(IProgressMonitor monitor) {
			String launchID = GraphicalEditorHelper.getLaunchIdFromDocumentation(descriptor.getDocumentation());
			PooslDrawMessage toDrawMessage = lastMessages.get(launchID);
			Map<String, PooslDrawMessage> messages = new HashMap<>();
			messages.put(launchID, toDrawMessage);
			try {
				session.getTransactionalEditingDomain().getCommandStack()
						.execute(new ColorChannelCommand(session, descriptor, messages, pathCalculators));

			} catch (Exception e) {
				return Status.CANCEL_STATUS;
			}
			return Status.OK_STATUS;
		}
	}

	public void launchStarted(String launchID, Map<String, String> instancePortMap, List<URI> files) {
		notSetupLaunches.add(launchID);
		this.instancePortMappings.put(launchID, instancePortMap);
		lockedFiles.put(launchID, files);
	}

	public Map<String, List<URI>> getLockedFiles() {
		return lockedFiles;
	}

	public boolean isSetup(String launchID) {
		return !notSetupLaunches.remove(launchID);
	}
}
