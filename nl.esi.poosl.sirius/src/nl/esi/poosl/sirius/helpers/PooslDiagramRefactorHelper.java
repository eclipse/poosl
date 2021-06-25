package nl.esi.poosl.sirius.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.tools.api.command.semantic.RemoveSemanticResourceCommand;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.Poosl;
import nl.esi.poosl.sirius.IPreferenceConstants;
import nl.esi.poosl.sirius.navigator.edit.CopyFileRepresentationsCommand;
import nl.esi.poosl.sirius.navigator.edit.RenameFileRepresentationsCommand;
import nl.esi.poosl.xtext.helpers.HelperFunctions;
import nl.esi.poosl.xtext.importing.ImportingHelper;

/**
 * Class containing utility for copying diagrams
 * 
 * @author kstaal
 *
 */
public class PooslDiagramRefactorHelper {
	private static final String CONTAINER_GET_MEMBERS_FAILED = "Could not get members from IContainer.";
	private static final String COPY_FOLDER_FAILED = "Could not copy resources from folder.";
	private static final String DESTINATION_SESSION_NOT_FOUND = "Session destination is null, could not get session to copy the diagrams to.";

	private static final Logger LOGGER = Logger.getLogger(PooslDiagramRefactorHelper.class.getName());

	private PooslDiagramRefactorHelper() {
		throw new IllegalStateException("Utility class");
	}

	public static void copy(IResource resource, IPath destinationPath, IProgressMonitor monitor) {
		String sourceProjectName = resource.getFullPath().segment(0);
		IProject sourceProject = ResourcesPlugin.getWorkspace().getRoot().getProject(sourceProjectName);
		Session session = GraphicalEditorHelper.getSession(sourceProject, null, false, true, monitor);
		if (session != null) {
			String destinationProjectName = destinationPath.segment(0);
			Session destinationSession = getDestinationSession(sourceProjectName, session, destinationProjectName,
					monitor);
			if (destinationSession != null) {
				IResource destination = convertIPathToIResource(resource, destinationPath);
				if (destination != null) {
					copyResource(session, destinationSession, resource, destination, monitor);
				}
			} else {
				LOGGER.log(Level.WARNING, DESTINATION_SESSION_NOT_FOUND);
			}
		}
	}

	private static IResource convertIPathToIResource(IResource resource, IPath destinationPath) {
		IResource destination = null;
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		if (resource.getType() == IResource.FILE) {
			destination = workspace.getRoot().getFile(destinationPath);
		}
		if (resource.getType() == IResource.FOLDER) {
			destination = workspace.getRoot().getFolder(destinationPath);
		}
		return destination;
	}

	public static void copyIResource(final Session resSession, final Session destSession, IResource source,
			IResource destination, IProgressMonitor monitor) {
		Resource oldResource = ConvertHelper.convertIPathToResource(source.getFullPath());
		if (oldResource == null)
			return;
		Resource newResource = ConvertHelper.convertIPathToResource(destination.getFullPath());
		copyResource(resSession, destSession, oldResource, newResource, monitor);
	}

	public static List<DRepresentationDescriptor> copyResource(final Session resSession, final Session destSession,
			Resource oldResource, Resource newResource, IProgressMonitor monitor) {
		AddSemanticResourceCommand command = new AddSemanticResourceCommand(destSession, newResource.getURI(), monitor);
		destSession.getTransactionalEditingDomain().getCommandStack().execute(command);

		List<DRepresentationDescriptor> descriptors = getAllResourceDiagrams(resSession, oldResource);
		if (!descriptors.isEmpty()) {
			GraphicalEditorHelper.checkViewPoint(newResource, destSession, monitor);
			RecordingCommand copy = new CopyFileRepresentationsCommand(destSession.getTransactionalEditingDomain(),
					destSession, newResource, descriptors, monitor);
			destSession.getTransactionalEditingDomain().getCommandStack().execute(copy);
			copy.dispose();
		}
		return descriptors;
	}

	public static void renameResource(Session session, Resource oldResource, Resource newResource,
			IProgressMonitor monitor) {
		AddSemanticResourceCommand command = new AddSemanticResourceCommand(session, newResource.getURI(), monitor);
		session.getTransactionalEditingDomain().getCommandStack().execute(command);

		List<DRepresentationDescriptor> descriptors = getAllResourceDiagrams(session, oldResource);
		if (!descriptors.isEmpty()) {
			GraphicalEditorHelper.checkViewPoint(newResource, session, monitor);
			RenameFileRepresentationsCommand renameCommand = new RenameFileRepresentationsCommand(session, newResource,
					descriptors);
			session.getTransactionalEditingDomain().getCommandStack().execute(renameCommand);
			RemoveSemanticResourceCommand removeCommand = new RemoveSemanticResourceCommand(session, oldResource,
					monitor, true);
			session.getTransactionalEditingDomain().getCommandStack().execute(removeCommand);
			removeCommand.dispose();
		}
	}

	public static List<DRepresentationDescriptor> getAllResourceDiagrams(Session session, Resource resource) {
		Poosl poosl = ImportingHelper.toPoosl(resource);
		Set<EObject> dOwners = new HashSet<>();
		dOwners.add(poosl);
		ClusterClass system = HelperFunctions.getSystem(poosl);
		if (system != null) {
			dOwners.add(system);
		}
		dOwners.addAll(poosl.getClusterClasses());
		List<DRepresentationDescriptor> descriptors = new ArrayList<>();
		for (EObject eObject : dOwners) {
			Collection<DRepresentationDescriptor> allObjectDescriptors = GraphicalEditorHelper
					.getAllObjectDescriptors(session, eObject);
			descriptors.addAll(allObjectDescriptors);
		}
		return descriptors;
	}

	private static Session getDestinationSession(String sourceProjectName, final Session session,
			String destinationProjectName, IProgressMonitor monitor) {
		Session destinationSession;
		if (destinationProjectName.equals(sourceProjectName)) {
			destinationSession = session;
		} else {
			IProject destinationProject = ResourcesPlugin.getWorkspace().getRoot().getProject(destinationProjectName);
			destinationSession = GraphicalEditorHelper.getSession(destinationProject, null, true, true, monitor);
		}
		return destinationSession;
	}

	private static void copyResource(Session session, Session destSession, IResource source, IResource destination,
			IProgressMonitor monitor) {
		if (source.getType() == IResource.FILE
				&& source.getFileExtension().equals(IPreferenceConstants.POOSL_FILE_EXT)) {
			copyIResource(session, destSession, source, destination, monitor);
		}

		if (IResource.FOLDER == source.getType()) {
			IFolder folder = (IFolder) source;
			Map<String, IResource> membersMap = getMembersMap(destination);
			try {
				for (IResource iResource : folder.members()) {
					copyResource(session, destSession, iResource, membersMap.get(iResource.getName()), monitor);
				}
			} catch (CoreException e) {
				LOGGER.log(Level.WARNING, COPY_FOLDER_FAILED, e);
			}
		}
	}

	/**
	 * 
	 * @param resource
	 * @return
	 */
	private static Map<String, IResource> getMembersMap(IResource resource) {
		Map<String, IResource> map = new HashMap<>();
		if (resource instanceof IContainer) {
			IContainer container = (IContainer) resource;
			try {
				for (IResource iResource : container.members()) {
					map.put(iResource.getName(), iResource);
				}
			} catch (CoreException e) {
				LOGGER.log(Level.WARNING, CONTAINER_GET_MEMBERS_FAILED, e);
			}
		}
		return map;
	}

	public static void copyDiagramElements(DRepresentation newRepresentation, URI oldUri, EObject newDiagramTarget) {
		newRepresentation.eSet(ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET, newDiagramTarget);

		for (DRepresentationElement elements : newRepresentation.getRepresentationElements()) {
			EObject oldDiagramSem = elements.getTarget();

			if (EcoreUtil.getURI(oldDiagramSem).trimFragment().equals(oldUri)) {
				String fragment = EcoreUtil.getURI(oldDiagramSem).fragment();
				EObject newDiagramSem = newDiagramTarget.eResource().getEObject(fragment);
				elements.eSet(ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET, newDiagramSem);
			}
		}
	}
}
