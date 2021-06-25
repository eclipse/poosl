package nl.esi.poosl.sirius.helpers;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ecore.extender.business.internal.permission.ReadOnlyWrapperPermissionAuthority;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.UserSession;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.INavigatorContentService;

import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.Instance;
import nl.esi.poosl.Poosl;
import nl.esi.poosl.rotalumisclient.PooslConstants;
import nl.esi.poosl.rotalumisclient.extension.ExternDebugItem;
import nl.esi.poosl.sirius.Activator;
import nl.esi.poosl.sirius.Messages;
import nl.esi.poosl.sirius.debug.CopyRepresentationCommand;
import nl.esi.poosl.sirius.debug.GraphicalDebugUpdater;
import nl.esi.poosl.sirius.debug.UpdateHelper;
import nl.esi.poosl.sirius.delete.PooslDiagramDeleteHelper;
import nl.esi.poosl.sirius.navigator.CompositeStructureDiagram;
import nl.esi.poosl.sirius.permissions.PooslPermissionAuthority;
import nl.esi.poosl.xtext.helpers.PooslReferenceHelper;

@SuppressWarnings("restriction")
public class GraphicalEditorHelper {
	private static final String WARNING_MODELING_PROJECT_EXPLORER = "Modeling extension in the project explorer could not be removed.";
	private static final String SIRIUS_EXPLORER_CONTENT_ID = "org.eclipse.sirius.ui.resource.content.session";
	private static final String WARNING_TARGET_UNKNOWN = "Could not determine diagram owner.";
	private static final String VIEWPOINT = "POOSL viewpoint";
	private static final String AIRD_FILE_EXTENSION = ".aird";
	private static final String DEFAULT_AIRD_FILE = "/representations.aird";

	private static final Logger LOGGER = Logger.getLogger(GraphicalEditorHelper.class.getName());

	private GraphicalEditorHelper() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Add modeling nature to the project. If it fails, then an error message dialog
	 * is opened.
	 * 
	 * @param project The project to which the nature must be added
	 * @return True if adding the nature succeeded
	 */
	private static boolean addModelingNature(final IProject project, IProgressMonitor monitor) {
		try {
			ModelingProjectManager.INSTANCE.convertToModelingProject(project, monitor);
		} catch (final CoreException e) {
			LOGGER.log(Level.WARNING, "Adding modeling nature failed.", e);
			Display.getDefault().syncExec(new Runnable() {
				@Override
				public void run() {
					MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.ERROR_MODELLING_TITLE,
							MessageFormat.format(Messages.ERROR_MODELLING_MESSAGE, e.getMessage()));
				}
			});
			return false;
		}
		return true;
	}

	/**
	 * Gets the sirius eobject thats corresponds to the given eobject
	 * 
	 * @param target
	 * @param session
	 * @return
	 */
	public static EObject getSiriusObject(EObject target, Session session) {
		URI uri = EcoreUtil.getURI(target);
		EObject object = session.getTransactionalEditingDomain().getResourceSet().getEObject(uri, false);
		if (object == null) {
			return target;
		} else {
			return object;
		}
	}

	/**
	 * @param airdfile
	 * @return new session
	 */
	private static Session createSession(IProject project, IEditorPart editor, IProgressMonitor monitor) {
		String pathName = project.getFullPath().append(ModelingProject.DEFAULT_REPRESENTATIONS_FILE_NAME).toString();
		URI sessionResourceURI = URI.createPlatformResourceURI(pathName, true);

		addModelingNature(project, monitor);

		Session session = SessionManager.INSTANCE.getSession(sessionResourceURI, monitor);

		try {
			removeModelingFromProjectExpl(editor);
		} catch (PartInitException e) {
			LOGGER.log(Level.WARNING, WARNING_MODELING_PROJECT_EXPLORER, e);
		}

		session.open(monitor);
		session.save(monitor);
		return session;
	}

	/**
	 * Find the aird file the project or create one if none exists
	 * 
	 * @param activeProject in which the aird file must exist
	 * @return location of the aird file
	 * @throws CoreException
	 */
	private static URI findRepresentationFile(IProject activeProject) throws CoreException {
		final String[] airdlocation = { "" };
		activeProject.accept(new IResourceVisitor() {
			@Override
			public boolean visit(IResource resource) throws CoreException {
				if (resource.getFileExtension() != null && resource.getFileExtension().equals(AIRD_FILE_EXTENSION)) {
					airdlocation[0] = resource.getFullPath().toString();
				}
				return false;
			}
		});

		if (!airdlocation[0].isEmpty()) {
			return URI.createPlatformResourceURI(airdlocation[0], true);
		} else {
			return null;
		}
	}

	/**
	 * Get description for the object that is used to make a new representation
	 * 
	 * @param session
	 * @param object
	 * @param vps
	 * @return
	 */
	private static RepresentationDescription getDescription(EObject object, Collection<Viewpoint> vps) {
		Collection<RepresentationDescription> possiblediagrams = DialectManager.INSTANCE
				.getAvailableRepresentationDescriptions(vps, object);
		if (!possiblediagrams.isEmpty()) {
			return possiblediagrams.iterator().next();
		} else {
			return null;
		}
	}

	/**
	 * find an existing representation for the target
	 * 
	 * @param session
	 * @param target
	 * @return
	 */
	private static DRepresentationDescriptor getExistingDescriptor(Session session, EObject target) {
		Collection<DRepresentationDescriptor> descriptors = getAllObjectDescriptors(session, target);
		for (DRepresentationDescriptor descriptor : descriptors) {
			if (descriptor.getRepresentation().getDocumentation().isEmpty()) {
				return descriptor;
			}
		}
		return null;
	}

	public static Collection<DRepresentationDescriptor> getAllObjectDescriptors(Session session, EObject target) {
		EObject siriusObject = getSiriusObject(target, session);
		if (siriusObject != null) {
			return DialectManager.INSTANCE.getRepresentationDescriptors(siriusObject, session);
		} else {
			return Collections.emptyList();
		}
	}

	public static Boolean isLaunchRepresentation(DRepresentationDescriptor descriptor, Collection<String> launchIDs) {
		DRepresentation representation = descriptor.getRepresentation();
		if (representation.getName().startsWith(DiagramNameHelper.COMMUNICATION_DIAGRAM_PREFIX)) {
			return launchIDs.contains(getLaunchIdFromDocumentation(representation.getDocumentation()));
		} else {
			return false;
		}
	}

	private static Boolean isInstanceRepresentation(DRepresentationDescriptor descriptor, String launchID,
			String instance) {
		DRepresentation representation = descriptor.getRepresentation();
		if (representation.getName().startsWith(DiagramNameHelper.COMMUNICATION_DIAGRAM_PREFIX)) {
			String doc = representation.getDocumentation();
			return launchID.equals(getLaunchIdFromDocumentation(doc))
					&& instance.equals(getInstanceFromDocumentation(doc));
		} else {
			return false;
		}
	}

	public static String getLaunchIdFromDocumentation(String documentation) {
		String[] info = documentation.split(",");
		if (info.length == 2) {
			return info[0];
		} else {
			return documentation;
		}
	}

	public static String getInstanceFromDocumentation(String documentation) {
		String[] info = documentation.split(",");
		if (info.length == 2) {
			return info[1];
		} else {
			return documentation;
		}
	}

	private static DRepresentationDescriptor getExistingDebugDescriptor(Session session, EObject target,
			String serverPort, String instance) {
		EObject siriusObject = getSiriusObject(target, session);
		if (siriusObject != null) {
			for (DRepresentationDescriptor descriptor : DialectManager.INSTANCE
					.getRepresentationDescriptors(siriusObject, session)) {
				if (isInstanceRepresentation(descriptor, serverPort, instance)) {
					return descriptor;
				}
			}
		}
		return null;
	}

	/**
	 * Removes modeling extension from project explorer. When there was a package
	 * explorer opened but no project explorer, the package explorer will be brought
	 * (back) to front. If there is a project explorer already opened nothing will
	 * change. Focus will always go to the editor provided if it is in the active
	 * workbench.
	 * 
	 * @param editor The editor to give focus
	 * @throws PartInitException if explorer can not be shown
	 */
	private static void removeModelingFromProjectExpl(IEditorPart editor) throws PartInitException {
		IWorkbenchWindow activeWorkbench = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (activeWorkbench != null) {
			IWorkbenchPage page = activeWorkbench.getActivePage();
			if (page != null) {
				CommonNavigator explorer = (CommonNavigator) page.findView(IPageLayout.ID_PROJECT_EXPLORER);
				if (explorer == null) {
					page.showView(IPageLayout.ID_PROJECT_EXPLORER);
					explorer = (CommonNavigator) page.findView(IPageLayout.ID_PROJECT_EXPLORER);
					IViewPart pack = page.findView("org.eclipse.jdt.ui.PackageExplorer");
					if (pack != null) {
						page.activate(pack);
					}
					if (editor != null) {
						IEditorPart diagram = page.findEditor(editor.getEditorInput());
						if (diagram != null) {
							page.activate(diagram);
						}
					}
				}

				if (explorer != null) {
					INavigatorContentService content = explorer.getNavigatorContentService();
					if (content != null && content.isActive(SIRIUS_EXPLORER_CONTENT_ID)) {
						content.getActivationService().deactivateExtensions(new String[] { SIRIUS_EXPLORER_CONTENT_ID },
								false);
						content.getActivationService().persistExtensionActivations();

					}
				}
			}
		}
	}

	/**
	 * This method is used to go from Graphical editor to another graphical editor.
	 * 
	 * @param target
	 */
	public static void openGraphicalEditor(final EObject target, final String documentation) {
		try {
			String instanceName = "";
			EObject currentTarget = target;
			if (currentTarget instanceof Instance) {
				Instance instance = (Instance) currentTarget;
				currentTarget = PooslReferenceHelper.getInstantiableClassEObject(instance);
				instanceName = instance.getName();
			}
			if (currentTarget instanceof ClusterClass || currentTarget instanceof nl.esi.poosl.Poosl) {
				EObjectQuery query = new EObjectQuery(currentTarget);
				Session session = query.getSession();
				if (session != null) {
					if (documentation != null && !documentation.isEmpty()) {
						instanceName = getInstanceFromDocumentation(documentation) + "/" + instanceName;
						openDebugDiagram(getLaunchIdFromDocumentation(documentation), instanceName, currentTarget,
								session, new NullProgressMonitor());
					} else {
						openGraphicalEditor(currentTarget, session, new NullProgressMonitor());
					}
				} else {
					Display.getDefault().asyncExec(new Runnable() {
						@Override
						public void run() {
							MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Poosl Editor",
									"Can not open a Poosl editor for a file outside of the workspace.");
						}
					});
				}
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Could not open graphical Editor", e);
		}
	}

	public static void openGraphicalEditorFromSelection(ISelection selection, boolean classDiagram) {
		IFile file = ConvertHelper.convertISelectionToIFile(selection);
		if (file == null) {
			LOGGER.log(Level.WARNING, "Could not get file from the selection");
			return;
		}

		Poosl poosl = ConvertHelper.convertIFileToPoosl(file);
		if (poosl == null) {
			LOGGER.log(Level.WARNING, "Could not get poosl model from the selection");
			return;
		}

		openGraphicalEditorFromFile(file, null, poosl, classDiagram);
	}

	/**
	 * If openClassDiagram is true opens a classdiagram. Otherwise will open the
	 * system specification. When no system exists and the poosl model contains only
	 * 1 clusterclass open the composite structure diagram of that cluster.
	 * 
	 * @param file             {@link IFile} used to get graphical context
	 * @param poosl            Contains the objects that are used for opening the
	 *                         diagram
	 * @param openClassDiagram if true opens the class diagram
	 */
	public static void openGraphicalEditorFromFile(IFile file, IEditorPart editor, Poosl poosl,
			boolean openClassDiagram) {
		if (openClassDiagram) {
			openGraphicalEditor(poosl, editor, file.getProject());
		} else {
			EObject mainTarget = CompositeStructureDiagram.findTarget(poosl);
			if (mainTarget != null) {
				openGraphicalEditor(mainTarget, editor, file.getProject());
			} else {
				Display.getDefault().asyncExec(new Runnable() {
					@Override
					public void run() {
						MessageDialog.openError(Display.getDefault().getActiveShell(),
								Messages.ERROR_MISSING_SYSTEM_AND_CLUSTER_TITLE,
								Messages.ERROR_MISSING_SYSTEM_AND_CLUSTER_MESSAGE);
					}
				});
			}
		}
	}

	/**
	 * This method is used to go from textual editor to the graphical editor.
	 * 
	 * @param target EObject from which the diagram must be shown
	 * @param editor The editor is used to determine the active project
	 * @return
	 * @return
	 */
	public static void openGraphicalEditor(final EObject target, final IEditorPart editor,
			final IProject activeProject) {
		try {
			PlatformUI.getWorkbench().getProgressService().run(false, false, new IRunnableWithProgress() {
				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					if (target != null) {
						Session session = getSession(target, activeProject, editor, true, true, monitor);
						if (session != null) {
							openGraphicalEditor(target, session, monitor);
						}
					} else {
						LOGGER.log(Level.FINE, WARNING_TARGET_UNKNOWN);
					}
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			LOGGER.log(Level.WARNING, "Could not open Graphical editor", e);
		}
	}

	private static IEditorPart openGraphicalEditor(EObject object, Session session, IProgressMonitor monitor) {
		if (object == null) {
			LOGGER.log(Level.SEVERE, WARNING_TARGET_UNKNOWN);
			return null;
		}

		DRepresentationDescriptor descriptor = getExistingDescriptor(session, object);
		if (descriptor == null) {
			URI uri = object.eResource().getURI();
			PooslPermissionAuthority authority = getPermissionAuthority(session);
			if (authority != null) {
				authority.creatingRepresentation(uri);
			}

			descriptor = createNewRepresentation(object, session, null, null, monitor);

			if (authority != null) {
				authority.createdRepresentation(uri);
			}
		}

		IEditorPart editor = null;
		if (descriptor != null) {
			GraphicalDebugUpdater.verifyLockedFiles();
			editor = DialectUIManager.INSTANCE.openEditor(session, descriptor.getRepresentation(), monitor);
			if (editor instanceof DialectEditor) {
				((DialectEditor) editor).validateRepresentation();
			}
		}

		session.save(monitor);
		return editor;
	}

	private static Session getSession(EObject target, IProject activeProject, IEditorPart editor, boolean create,
			boolean open, IProgressMonitor monitor) {
		EObjectQuery query = new EObjectQuery(target);
		Session session = query.getSession();
		if (session == null) {
			session = getSession(activeProject, editor, create, open, monitor);
		}
		return session;
	}

	/**
	 * Get a graphical {@link Session} for the project
	 * 
	 * @param activeProject the project to look at for a session
	 * @param editor        if editor is provided it will get focus if a modeling
	 *                      nature was added
	 * @param open          true to open the session if a closed one is found
	 * @param create        true to create session if none can be found
	 * @return
	 */
	public static Session getSession(IProject activeProject, IEditorPart editor, boolean create, boolean open,
			IProgressMonitor monitor) {
		String pathName = activeProject.getFullPath().append(ModelingProject.DEFAULT_REPRESENTATIONS_FILE_NAME)
				.toString();
		URI sessionResourceURI = URI.createPlatformResourceURI(pathName, true);

		boolean defaultExists = isAlreadyLoaded(sessionResourceURI) || defaultResourceExists(activeProject);
		boolean customExists = false;
		if (!defaultExists) {
			try {
				URI location = findRepresentationFile(activeProject);
				customExists = location != null;
				if (customExists) {
					sessionResourceURI = location;
				}
			} catch (CoreException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}
		}

		Session session = null;
		if (defaultExists || customExists) {
			session = SessionManager.INSTANCE.getSession(sessionResourceURI, monitor);
		} else {
			if (create) {
				session = createSession(activeProject, editor, monitor);
			}
		}

		if (open && session != null && !session.isOpen()) {
			session.open(monitor);
		}
		return session;
	}

	private static boolean defaultResourceExists(IProject activeProject) {
		IFile defaultFile = activeProject.getFile(new Path(DEFAULT_AIRD_FILE));
		return defaultFile.exists();
	}

	private static boolean isAlreadyLoaded(URI representationsFileURI) {
		for (Session session : Collections.unmodifiableCollection(SessionManager.INSTANCE.getSessions())) {
			if (representationsFileURI.equals(session.getSessionResource().getURI())) {
				return true;
			}
		}
		return false;
	}

	private static DRepresentationDescriptor createNewRepresentation(EObject object, Session session, String launchID,
			String instance, IProgressMonitor monitor) {
		checkViewPoint(object.eResource(), session, monitor);
		TransactionalEditingDomain ted = session.getTransactionalEditingDomain();
		final RepresentationDescription description = getDescription(object, session.getSelectedViewpoints(false));
		CreateRepresentationCommand createCommand = new CreateRepresentationCommand(session, description, object,
				launchID, instance, monitor);
		ted.getCommandStack().execute(createCommand);
		DRepresentationDescriptor createdDescriptor = createCommand.getCreatedDescriptor();
		if (createdDescriptor == null) {
			LOGGER.severe("Representation could not be created for " + instance + ", " + launchID + ".");
		}
		return createdDescriptor;
	}

	public static void checkViewPoint(Resource resource, Session session, IProgressMonitor monitor) {
		Collection<Viewpoint> vps = session.getSelectedViewpoints(true);
		if (vps.isEmpty()) {
			AddSemanticResourceCommand command = new AddSemanticResourceCommand(session, resource.getURI(), monitor);
			session.getTransactionalEditingDomain().getCommandStack().execute(command);
			UserSession userSession = UserSession.from(session);
			userSession.selectViewpoint(VIEWPOINT);
			userSession.save(monitor);
		}
	}

	public static PooslPermissionAuthority getPermissionAuthority(Session session) {
		if (session != null) {
			IPermissionAuthority authority = PermissionAuthorityRegistry.getDefault()
					.getPermissionAuthority(session.getTransactionalEditingDomain().getResourceSet());
			if (authority instanceof ReadOnlyWrapperPermissionAuthority) {
				return (PooslPermissionAuthority) ((ReadOnlyWrapperPermissionAuthority) authority)
						.getWrappedAuthority();
			}
		}
		return null;
	}

	public static void openCommunicationDiagram(final ExternDebugItem selectedItem, final IProject project,
			final EObject diagramTarget) {
		try {
			PlatformUI.getWorkbench().getProgressService().run(false, false, new IRunnableWithProgress() {
				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					monitor.beginTask("Setup environment and open Communication Diagram " + selectedItem.getDiagram(),
							3);
					String launchID = selectedItem.getLaunchID();
					Session session = getSession(diagramTarget, project, null, true, true, monitor);
					if (session != null) {
						if (!session.isOpen()) {
							session.open(monitor);
						}
						monitor.worked(1);
						setupDebugSession(launchID, session);
						monitor.worked(1);
						openDebugDiagram(launchID, selectedItem.getDiagram(), diagramTarget, session, monitor);
						monitor.worked(1);
					} else {
						monitor.worked(3);
						LOGGER.log(Level.WARNING, "Could not get session to open Communication Diagram");
					}
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			LOGGER.log(Level.WARNING, "Could not open Communication Diagram", e);
		}
	}

	private static void openDebugDiagram(String launchID, String instance, EObject diagramTarget, Session session,
			IProgressMonitor monitor) {
		if (diagramTarget == null) {
			return;
		}
		String instanceLabel = instance;
		DRepresentationDescriptor debugDescriptor = getDebugRepresentation(diagramTarget, session, launchID,
				instanceLabel);

		if (debugDescriptor != null) {
			openDebugEditor(session, debugDescriptor.getRepresentation(), monitor);
		} else {
			// Creating a whole new diagram, for this we need get temporary
			// write access
			URI resourceUri = diagramTarget.eResource().getURI();
			PooslPermissionAuthority authority = getPermissionAuthority(session);
			if (authority != null) {
				authority.creatingRepresentation(resourceUri);
			} else {
				LOGGER.log(Level.SEVERE,
						"Could not get permission authority for communication diagram " + instanceLabel);
				Display.getDefault().asyncExec(new Runnable() {
					@Override
					public void run() {
						MessageDialog.openError(Display.getDefault().getActiveShell(), "Communication Diagram",
								"Can not get permission to lock communication diagram.");
					}
				});
			}
			try {
				debugDescriptor = createNewRepresentation(diagramTarget, session, launchID, instanceLabel, monitor);
				if (debugDescriptor != null) {
					openDebugEditor(session, debugDescriptor.getRepresentation(), monitor);
				} else {
					LOGGER.log(Level.SEVERE, "Communication diagram was not created");
				}
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE, "Communication diagram could not be created", e);
			} finally {
				// resource should always be unlocked
				if (authority != null) {
					authority.createdRepresentation(resourceUri);
				}
			}
		}

		Activator.MESSAGEUPDATER.draw(session, debugDescriptor);
		session.save(monitor);
	}

	private static void openDebugEditor(Session session, DRepresentation debugRepresentation,
			IProgressMonitor monitor) {
		if (debugRepresentation != null) {
			GraphicalDebugUpdater.verifyLockedFiles();
			IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, debugRepresentation, monitor);
			if (editor != null) {
				editor.setFocus();
			}
		}
	}

	/**
	 * Getting a debug diagram by either getting an existing one or by copying a
	 * normal diagram
	 * 
	 * @param diagramTarget
	 * @param session
	 * @param launchID
	 * @return the debug diagram
	 */
	private static DRepresentationDescriptor getDebugRepresentation(EObject diagramTarget, Session session,
			String launchID, String instance) {
		DRepresentationDescriptor debugDescriptor = getExistingDebugDescriptor(session, diagramTarget, launchID,
				instance);
		if (debugDescriptor == null) {
			// If already an editing representation exists we can make a copy
			// from it
			DRepresentationDescriptor originalDescriptor = getExistingDescriptor(session, diagramTarget);
			if (originalDescriptor != null) {
				List<DRepresentationDescriptor> descriptors = new ArrayList<>();
				descriptors.add(originalDescriptor);
				try {
					final TransactionalEditingDomain transDomain = session.getTransactionalEditingDomain();
					CopyRepresentationCommand command = new CopyRepresentationCommand(transDomain, descriptors, session,
							launchID, instance);
					transDomain.getCommandStack().execute(command);
					debugDescriptor = command.getCreatedDescriptors().get(0);
				} catch (Exception e) {
					LOGGER.log(Level.WARNING, "Could not create a debug diagram by copying the original.", e);
				}
			}
		}
		return debugDescriptor;
	}

	/**
	 * find and remove any communication diagrams that were left over after a sudden
	 * shutdown of eclipse
	 * 
	 * @param launchID
	 * @param session
	 */
	private static void setupDebugSession(String launchID, Session session) {
		if (!Activator.MESSAGEUPDATER.isSetup(launchID) && session != null) {
			final Set<DRepresentationDescriptor> descriptors = UpdateHelper
					.getLaunchRepresentationsFromSession(launchID, session);
			if (!descriptors.isEmpty()) {
				PooslDiagramDeleteHelper.closeAndDeleteDiagrams(UpdateHelper.getPooslShell(), session, descriptors);
			}
		}
	}

	public static List<String> getCurrentLaunchIDs() throws CoreException {
		List<String> currentLaunches = new ArrayList<>();
		for (ILaunch launch : DebugPlugin.getDefault().getLaunchManager().getLaunches()) {
			String launchID = launch.getLaunchConfiguration()
					.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_PORT, "");
			if (!launchID.isEmpty()) {
				currentLaunches.add(launchID);
			}
		}
		return currentLaunches;
	}
}
