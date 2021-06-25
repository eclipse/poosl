package nl.esi.poosl.sirius.debug;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.resource.IEObjectDescription;

import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.Instance;
import nl.esi.poosl.Poosl;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.rotalumisclient.PooslConstants;
import nl.esi.poosl.rotalumisclient.extension.ExternDebugItem;
import nl.esi.poosl.rotalumisclient.extension.ExternDebugMessage;
import nl.esi.poosl.rotalumisclient.extension.ExternLaunchStartMessage;
import nl.esi.poosl.rotalumisclient.extension.IPooslDebugInformer;
import nl.esi.poosl.sirius.Activator;
import nl.esi.poosl.sirius.delete.PooslDiagramDeleteHelper;
import nl.esi.poosl.sirius.helpers.GraphicalEditorHelper;
import nl.esi.poosl.sirius.permissions.PooslPermissionAuthority;
import nl.esi.poosl.xtext.helpers.HelperFunctions;
import nl.esi.poosl.xtext.helpers.PooslReferenceHelper;

public class GraphicalDebugListener implements IPooslDebugInformer {
	private static final Logger LOGGER = Logger.getLogger(GraphicalDebugListener.class.getName());

	@Override
	public void launchStart(ExternLaunchStartMessage startMessage) {
		String projectName = startMessage.getProjectName();

		List<URI> files = new ArrayList<>();
		files.addAll(startMessage.getFiles());
		files.addAll(getRelativeUris(startMessage.getRelativeModelPath(), files, projectName));

		Activator.MESSAGEUPDATER.launchStarted(startMessage.getLaunchID(), startMessage.getInstancePortMap(), files);
	}

	@Override
	public void lastMessageChanged(ExternDebugMessage lastMessage) {
		Activator.MESSAGEUPDATER.addMessage(lastMessage);
	}

	@Override
	public void debugSelectionChanged(final ExternDebugItem selectedItem, ExternDebugMessage message) {
		final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(selectedItem.getProjectName());
		final EObject diagramTarget = getDiagramTarget(selectedItem);
		if (diagramTarget != null) {
			GraphicalEditorHelper.openCommunicationDiagram(selectedItem, project, diagramTarget);
		} else {
			LOGGER.log(Level.SEVERE, "Could not find ecore model of the diagram target " + selectedItem.getDiagram());
		}
	}

	@Override
	public void launchStopped(final String launchID) {
		final Map<Session, Set<DRepresentationDescriptor>> session2Descriptors = new HashMap<>();
		ArrayList<String> idAsArray = new ArrayList<>(Arrays.asList(launchID));
		for (final Session session : SessionManager.INSTANCE.getSessions()) {
			UpdateHelper.addRepresentations(idAsArray, session2Descriptors, session);
			PooslPermissionAuthority authority = GraphicalEditorHelper.getPermissionAuthority(session);
			if (authority != null) {
				authority.deActivate(launchID);
			}
		}
		Activator.MESSAGEUPDATER.launchStopped(launchID);
		PooslDiagramDeleteHelper.closeAndDeleteDiagrams(UpdateHelper.getPooslShell(), session2Descriptors);
	}

	private EObject getDiagramTarget(ExternDebugItem selectedItem) {
		Poosl poosl = UpdateHelper.getEcoreModel(selectedItem.getRelativeModelPath());
		// get cluster from name
		ClusterClass system = HelperFunctions.getSystem(poosl);
		String[] path = selectedItem.getDiagram().split(PooslConstants.PATH_SEPARATOR);

		if (path.length == 2) {
			return system;
		} else {
			return getClusterClass(path, system);
		}
	}

	private ClusterClass getClusterClass(String[] path, ClusterClass cluster) {
		for (int i = 2; i < path.length; i++) {
			cluster = getInstanceClass(cluster, path[i]);
		}
		return cluster;
	}

	private ClusterClass getInstanceClass(ClusterClass cluster, String instanceName) {
		if (cluster != null && instanceName != null) {
			for (Instance instance : cluster.getInstances()) {
				if (instance.getName().equals(instanceName)) {
					IEObjectDescription descr = PooslReferenceHelper.getInstantiableClassDescription(instance);
					if (descr.getEClass() == Literals.CLUSTER_CLASS) {
						EObject obj = descr.getEObjectOrProxy();
						if (obj.eIsProxy())
							obj = EcoreUtil.resolve(obj, cluster);
						return (ClusterClass) obj;
					}
				}
			}
		}
		return null;
	}

	private List<URI> getRelativeUris(String relativeModelPath, List<URI> files, String projectName) {
		String absoluteMain = getMainFile(relativeModelPath, files);
		List<URI> relatives = new ArrayList<>();
		if (absoluteMain != null) {
			String base = absoluteMain.substring(0,
					absoluteMain.length() - relativeModelPath.substring(projectName.length() + 1).length());
			for (URI absolute : files) {
				if (absolute.isFile() && absolute.toFileString().startsWith(base)) {
					relatives.add(URI.createPlatformResourceURI(
							absolute.toFileString().replace(base, "\\" + projectName), true));
				}
			}
		}
		return relatives;
	}

	/**
	 * find main file (used to start the debug session), IDE no longer controls the
	 * location in the list
	 * 
	 * @param relativeModelPath
	 * @param files
	 * @return
	 */
	private String getMainFile(String relativeModelPath, List<URI> files) {
		// first or last are most likely to be main file.
		String main = files.get(files.size() - 1).toFileString();
		if (main != null && main.endsWith(relativeModelPath)) {
			return main;
		} else {
			main = files.get(0).toFileString();
			if (main != null && main.endsWith(relativeModelPath)) {
				return main;
			}
		}
		// if still not found check all
		for (int i = 1; i < files.size() - 1; i++) {
			main = files.get(i).toFileString();
			if (main.endsWith(relativeModelPath)) {
				return main;
			}
		}
		return null;
	}

	/**
	 * Get Session without having to provide the monitor and still use the Progress
	 * Service from non gui context
	 * 
	 * @param activeProject
	 * @param editor
	 * @param create
	 * @return session or null
	 */
	public static Session getSession(final IProject activeProject, final IEditorPart editor, final boolean create) {
		final Set<Session> sessions = new HashSet<>();
		IRunnableWithProgress runnable = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				sessions.add(GraphicalEditorHelper.getSession(activeProject, editor, create, false, monitor));
			}
		};

		Shell shell = UpdateHelper.getPooslShell();
		IRunnableContext context = new ProgressMonitorDialog(shell);
		try {
			PlatformUI.getWorkbench().getProgressService().runInUI(context, runnable, null);
		} catch (InvocationTargetException | InterruptedException e) {
			LOGGER.log(Level.WARNING, "Could not get session", e.getCause());
		}
		if (sessions.iterator().hasNext()) {
			return sessions.iterator().next();
		}
		return null;
	}
}
