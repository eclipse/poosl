package nl.esi.poosl.sirius.navigator.edit;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CopyFilesAndFoldersOperation;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.ide.undo.CopyResourcesOperation;
import org.eclipse.ui.ide.undo.WorkspaceUndoUtil;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.ide.dialogs.IDEResourceInfoUtils;

import nl.esi.poosl.sirius.helpers.PooslDiagramRefactorHelper;

/**
 * Operation that also copies Poosl/Sirius Diagrams This class is based on
 * {@link CopyFilesAndFoldersOperation}.
 * 
 * @author kstaal
 *
 */
@SuppressWarnings("restriction")
public class PooslCopyOperation {

	/**
	 * Status containing the errors detected when running the operation or
	 * <code>null</code> if no errors detected.
	 */
	private MultiStatus errorStatus;

	/**
	 * The parent shell used to show any dialogs.
	 */
	private Shell messageShell;

	/**
	 * Whether or not the copy has been canceled by the user.
	 */
	private boolean canceled = false;

	/**
	 * Whether or not the operation creates virtual folders and links instead of
	 * folders and files.
	 */
	private boolean createVirtualFoldersAndLinks = false;

	/**
	 * Whether or not the operation creates links instead of folders and files.
	 */
	private boolean createLinks = false;

	/**
	 * Overwrite all flag.
	 */

	private String[] modelProviderIds;

	private static final IResource[] EMPTY_RESOURCE_ARRAY = new IResource[0];

	/**
	 * Creates a new operation initialized with a shell.
	 *
	 * @param shell parent shell for error dialogs
	 */
	public PooslCopyOperation(Shell shell) {
		messageShell = shell;
	}

	/**
	 * Returns a new name for a copy of the resource at the given path in the given
	 * workspace. This name is determined automatically.
	 *
	 * @param originalName the full path of the resource
	 * @param workspace    the workspace
	 * @return the new full path for the copy
	 */
	static IPath getAutoNewNameFor(IPath originalName, IWorkspace workspace) {
		String resourceName = originalName.lastSegment();
		IPath leadupSegment = originalName.removeLastSegments(1);
		boolean isFile = !originalName.hasTrailingSeparator();

		String newName = computeNewName(resourceName, isFile);
		while (true) {
			IPath pathToTry = leadupSegment.append(newName);
			if (!workspace.getRoot().exists(pathToTry)) {
				return pathToTry;
			}
			newName = computeNewName(newName, isFile);
		}
	}

	private static String computeNewName(String str, boolean isFile) {
		int lastIndexOfDot = str.lastIndexOf('.');
		String fileExtension = ""; //$NON-NLS-1$
		String fileNameNoExtension = str;
		if (isFile && lastIndexOfDot > 0) {
			fileExtension = str.substring(lastIndexOfDot);
			fileNameNoExtension = str.substring(0, lastIndexOfDot);
		}
		Pattern p = Pattern.compile("[0-9]+$"); //$NON-NLS-1$
		Matcher m = p.matcher(fileNameNoExtension);
		if (m.find()) {
			// String ends with a number: increment it by 1
			int newNumber = Integer.parseInt(m.group()) + 1;
			String numberStr = m.replaceFirst(Integer.toString(newNumber));
			return numberStr + fileExtension;
		}
		return fileNameNoExtension + "2" + fileExtension; //$NON-NLS-1$
	}

	/**
	 * Checks whether the resources with the given names exist.
	 *
	 * @param resources IResources to checl
	 * @return Multi status with one error message for each missing file.
	 */
	private IStatus checkExist(IResource[] resources) {
		MultiStatus multiStatus = new MultiStatus(PlatformUI.PLUGIN_ID, IStatus.OK, getProblemsMessage(), null);

		for (int i = 0; i < resources.length; i++) {
			IResource resource = resources[i];
			if (resource != null && !resource.isVirtual()) {
				URI location = resource.getLocationURI();
				String message = null;
				if (location != null) {
					IFileInfo info = IDEResourceInfoUtils.getFileInfo(location);
					if (info == null || !info.exists()) {
						if (resource.isLinked()) {
							message = NLS.bind(IDEWorkbenchMessages.CopyFilesAndFoldersOperation_missingLinkTarget,
									resource.getName());
						} else {
							message = NLS.bind(IDEWorkbenchMessages.CopyFilesAndFoldersOperation_resourceDeleted,
									resource.getName());
						}
					}
				}
				if (message != null) {
					IStatus status = new Status(IStatus.ERROR, PlatformUI.PLUGIN_ID, IStatus.OK, message, null);
					multiStatus.add(status);
				}
			}
		}
		return multiStatus;
	}

	/**
	 * Check if the user wishes to overwrite the supplied resource or all resources.
	 *
	 * @param source      the source resource
	 * @param destination the resource to be overwritten
	 * @return one of IDialogConstants.YES_ID, IDialogConstants.YES_TO_ALL_ID,
	 *         IDialogConstants.NO_ID, IDialogConstants.CANCEL_ID indicating whether
	 *         the current resource or all resources can be overwritten, or if the
	 *         operation should be canceled.
	 */
	private int checkOverwrite(final IResource source, final IResource destination) {

		// Dialogs need to be created and opened in the UI thread
		PooslOverwriteRunnable query = new PooslOverwriteRunnable(source, destination, messageShell);
		messageShell.getDisplay().syncExec(query);
		return query.getResult();
	}

	/**
	 * Recursively collects existing files in the specified destination path.
	 *
	 * @param destinationPath destination path to check for existing files
	 * @param copyResources   resources that may exist in the destination
	 * @param existing        holds the collected existing files
	 */
	private void collectExistingReadonlyFiles(IPath destinationPath, IResource[] copyResources,
			ArrayList<IFile> existing) {
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();

		for (int i = 0; i < copyResources.length; i++) {
			IResource source = copyResources[i];
			IPath newDestinationPath = destinationPath.append(source.getName());
			IResource newDestination = workspaceRoot.findMember(newDestinationPath);
			IFolder folder;

			if (newDestination == null) {
				continue;
			}
			folder = getFolder(newDestination);
			if (folder != null) {
				IFolder sourceFolder = getFolder(source);

				if (sourceFolder != null) {
					try {
						collectExistingReadonlyFiles(newDestinationPath, sourceFolder.members(), existing);
					} catch (CoreException exception) {
						recordError(exception);
					}
				}
			} else {
				IFile file = getFile(newDestination);
				if (file != null && file.isReadOnly()) {
					existing.add(file);
				}
			}
		}
	}

	/**
	 * Copies the given resources to the destination. The current Thread is halted
	 * while the resources are copied using a WorkspaceModifyOperation. This method
	 * should be called from the UIThread.
	 *
	 * @param resources   the resources to copy
	 * @param destination destination to which resources will be copied
	 * @return the resources which actually got copied
	 * @throws InterruptedException
	 * @see WorkspaceModifyOperation
	 * @see Display#getThread()
	 * @see Thread#currentThread()
	 */
	public IResource[] copyResources(final IResource[] resources, IContainer destination) throws InterruptedException {
		final IPath destinationPath = destination.getFullPath();
		final IResource[][] copiedResources = new IResource[1][0];

		// test resources for existence separate from validate API.
		// Validate is performance critical and resource exists
		// check is potentially slow. Fixes bugs 16129/28602.
		IStatus resourceStatus = checkExist(resources);
		if (resourceStatus.getSeverity() != IStatus.OK) {
			displayError(resourceStatus);
			return copiedResources[0];
		}
		String errorMsg = validateDestination(destination, resources);
		if (errorMsg != null) {
			displayError(errorMsg);
			return copiedResources[0];
		}

		IRunnableWithProgress op = new IRunnableWithProgress() {
			@Override
			public void run(IProgressMonitor monitor) {
				copyResources(resources, destinationPath, copiedResources, monitor);
			}
		};

		try {
			PlatformUI.getWorkbench().getProgressService().runInUI(new ProgressMonitorDialog(messageShell), op, null);
		} catch (InvocationTargetException e) {
			display(e);
		}

		// If errors occurred, open an Error dialog
		if (errorStatus != null) {
			displayError(errorStatus);
			errorStatus = null;
		}

		return copiedResources[0];
	}

	private static final Logger LOGGER = Logger.getLogger(PooslCopyOperation.class.getName());

	private void display(InvocationTargetException e) {
		LOGGER.log(Level.WARNING, MessageFormat.format("Exception in {0}.performCopy(): {1}", getClass().getName(),
				e.getTargetException()), e.getCause());
		displayError(NLS.bind(IDEWorkbenchMessages.CopyFilesAndFoldersOperation_internalError,
				e.getTargetException().getMessage()));
	}

	/**
	 * Display the supplied status in an error dialog.
	 *
	 * @param status The status to display
	 */
	private void displayError(final IStatus status) {
		messageShell.getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				ErrorDialog.openError(messageShell, getProblemsTitle(), null, status);
			}
		});
	}

	/**
	 * Creates a file or folder handle for the source resource as if it were to be
	 * created in the destination container.
	 *
	 * @param destination destination container
	 * @param source      source resource
	 * @return IResource file or folder handle, depending on the source type.
	 */
	IResource createLinkedResourceHandle(IContainer destination, IResource source) {
		IWorkspace workspace = destination.getWorkspace();
		IWorkspaceRoot workspaceRoot = workspace.getRoot();
		IPath linkPath = destination.getFullPath().append(source.getName());
		IResource linkHandle;

		if (source.getType() == IResource.FOLDER) {
			linkHandle = workspaceRoot.getFolder(linkPath);
		} else {
			linkHandle = workspaceRoot.getFile(linkPath);
		}
		return linkHandle;
	}

	/**
	 * Opens an error dialog to display the given message.
	 *
	 * @param message the error message to show
	 */
	private void displayError(final String message) {
		messageShell.getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				MessageDialog.openError(messageShell, getProblemsTitle(), message);
			}
		});
	}

	/**
	 * Returns the resource either casted to or adapted to an IFile.
	 *
	 * @param resource resource to cast/adapt
	 * @return the resource either casted to or adapted to an IFile.
	 *         <code>null</code> if the resource does not adapt to IFile
	 */
	private IFile getFile(IResource resource) {
		if (resource instanceof IFile) {
			return (IFile) resource;
		}
		return resource.getAdapter(IFile.class);
	}

	/**
	 * Returns the resource either casted to or adapted to an IFolder.
	 *
	 * @param resource resource to cast/adapt
	 * @return the resource either casted to or adapted to an IFolder.
	 *         <code>null</code> if the resource does not adapt to IFolder
	 */
	private IFolder getFolder(IResource resource) {
		if (resource instanceof IFolder) {
			return (IFolder) resource;
		}
		return resource.getAdapter(IFolder.class);
	}

	/**
	 * Returns a new name for a copy of the resource at the given path in the given
	 * workspace.
	 *
	 * @param originalName the full path of the resource
	 * @param workspace    the workspace
	 * @return the new full path for the copy, or <code>null</code> if the resource
	 *         should not be copied
	 */
	private IPath getNewNameFor(final IPath originalName, final IWorkspace workspace) {
		final IResource resource = workspace.getRoot().findMember(originalName);
		final IPath prefix = resource.getFullPath().removeLastSegments(1);
		final String[] returnValue = { "" }; //$NON-NLS-1$

		messageShell.getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				IInputValidator validator = new IInputValidator() {
					@Override
					public String isValid(String string) {
						if (resource.getName().equals(string)) {
							return IDEWorkbenchMessages.CopyFilesAndFoldersOperation_nameMustBeDifferent;
						}
						IStatus status = workspace.validateName(string, resource.getType());
						if (!status.isOK()) {
							return status.getMessage();
						}
						if (workspace.getRoot().exists(prefix.append(string))) {
							return IDEWorkbenchMessages.CopyFilesAndFoldersOperation_nameExists;
						}
						return null;
					}
				};

				final String initial = getAutoNewNameFor(originalName, workspace).lastSegment();
				InputDialog dialog = new InputDialog(messageShell,
						IDEWorkbenchMessages.CopyFilesAndFoldersOperation_inputDialogTitle,
						NLS.bind(IDEWorkbenchMessages.CopyFilesAndFoldersOperation_inputDialogMessage,
								resource.getName()),
						initial, validator) {

					@Override
					protected Control createContents(Composite parent) {
						Control contents = super.createContents(parent);
						int lastIndexOfDot = initial.lastIndexOf('.');
						if (resource instanceof IFile && lastIndexOfDot > 0) {
							getText().setSelection(0, lastIndexOfDot);
						}
						return contents;
					}
				};
				dialog.setBlockOnOpen(true);
				dialog.open();
				if (dialog.getReturnCode() == Window.CANCEL) {
					returnValue[0] = null;
				} else {
					returnValue[0] = dialog.getValue();
				}
			}
		});
		if (returnValue[0] == null) {
			throw new OperationCanceledException();
		}
		return prefix.append(returnValue[0]);
	}

	/**
	 * Returns the task title for this operation's progress dialog.
	 *
	 * @return the task title
	 */
	private String getOperationTitle() {
		return IDEWorkbenchMessages.CopyFilesAndFoldersOperation_operationTitle;
	}

	/**
	 * Returns the message for this operation's problems dialog.
	 *
	 * @return the problems message
	 */
	private String getProblemsMessage() {
		return IDEWorkbenchMessages.CopyFilesAndFoldersOperation_problemMessage;
	}

	/**
	 * Returns the title for this operation's problems dialog.
	 *
	 * @return the problems dialog title
	 */
	private String getProblemsTitle() {
		return IDEWorkbenchMessages.CopyFilesAndFoldersOperation_copyFailedTitle;
	}

	/**
	 * Returns whether the given resource is accessible. Files and folders are
	 * always considered accessible and a project is accessible if it is open.
	 *
	 * @param resource the resource
	 * @return <code>true</code> if the resource is accessible, and
	 *         <code>false</code> if it is not
	 */
	private boolean isAccessible(IResource resource) {
		switch (resource.getType()) {
		case IResource.FILE:
			return true;
		case IResource.FOLDER:
			return true;
		case IResource.PROJECT:
			return ((IProject) resource).isOpen();
		default:
			return false;
		}
	}

	/**
	 * Returns whether any of the given source resources are being recopied to their
	 * current container.
	 *
	 * @param sourceResources the source resources
	 * @param destination     the destination container
	 * @return <code>true</code> if at least one of the given source resource's
	 *         parent container is the same as the destination
	 */
	boolean isDestinationSameAsSource(IResource[] sourceResources, IContainer destination) {
		IPath destinationLocation = destination.getLocation();

		for (int i = 0; i < sourceResources.length; i++) {
			IResource sourceResource = sourceResources[i];
			if (sourceResource.getParent().equals(destination)) {
				return true;
			} else if (destinationLocation != null) {
				// do thorough check to catch linked resources. Fixes bug 29913.
				IPath sourceLocation = sourceResource.getLocation();
				IPath destinationResource = destinationLocation.append(sourceResource.getName());
				if (sourceLocation != null && sourceLocation.isPrefixOf(destinationResource)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Copies the given resources to the destination container with the given name.
	 * <p>
	 * Note: the destination container may need to be created prior to copying the
	 * resources.
	 * </p>
	 *
	 * @param resources   the resources to copy
	 * @param destination the path of the destination container
	 * @param monitor     a progress monitor for showing progress and for
	 *                    cancelation
	 * @return <code>true</code> if the copy operation completed without errors
	 */
	private boolean performCopy(IResource[] resources, IPath destination, IProgressMonitor monitor) {

		IPath[] destinationPaths = new IPath[resources.length];
		try {
			for (int i = 0; i < resources.length; i++) {
				IResource source = resources[i];
				destinationPaths[i] = destination.append(source.getName());
				if (source.getType() != IResource.FILE) {
					destinationPaths[i] = destinationPaths[i].addTrailingSeparator();
				}

			}
			CopyResourcesOperation op = new CopyResourcesOperation(resources, destinationPaths,
					IDEWorkbenchMessages.CopyFilesAndFoldersOperation_copyTitle);
			op.setModelProviderIds(getModelProviderIds());
			PlatformUI.getWorkbench().getOperationSupport().getOperationHistory().execute(op, monitor,
					WorkspaceUndoUtil.getUIInfoAdapter(messageShell));

			copyDiagrams(resources, destinationPaths, monitor);

		} catch (ExecutionException e) {
			if (e.getCause() instanceof CoreException) {
				recordError((CoreException) e.getCause());
			} else {
				IDEWorkbenchPlugin.log(e.getMessage(), e);
				displayError(e.getMessage());
			}
			return false;
		}
		return true;
	}

	/**
	 * Individually copies the given resources to the specified destination
	 * container checking for name collisions. If a collision is detected, it is
	 * saved with a new name.
	 * <p>
	 * Note: the destination container may need to be created prior to copying the
	 * resources.
	 * </p>
	 *
	 * @param resources   the resources to copy
	 * @param destination the path of the destination container
	 * @return <code>true</code> if the copy operation completed without errors.
	 */
	private boolean performCopyWithAutoRename(IResource[] resources, IPath destination, IProgressMonitor monitor) {
		IWorkspace workspace = resources[0].getWorkspace();
		IPath[] destinationPaths = new IPath[resources.length];
		try {
			for (int i = 0; i < resources.length; i++) {
				IResource source = resources[i];
				destinationPaths[i] = destination.append(source.getName());
				if (source.getType() != IResource.FILE) {
					destinationPaths[i] = destinationPaths[i].addTrailingSeparator();
				}

				if (workspace.getRoot().exists(destinationPaths[i])) {
					destinationPaths[i] = getNewNameFor(destinationPaths[i], workspace);
				}
			}
			CopyResourcesOperation op = new CopyResourcesOperation(resources, destinationPaths,
					IDEWorkbenchMessages.CopyFilesAndFoldersOperation_copyTitle);
			op.setModelProviderIds(getModelProviderIds());
			PlatformUI.getWorkbench().getOperationSupport().getOperationHistory().execute(op, monitor,
					WorkspaceUndoUtil.getUIInfoAdapter(messageShell));

			copyDiagrams(resources, destinationPaths, monitor);
		} catch (ExecutionException e) {
			if (e.getCause() instanceof CoreException) {
				recordError((CoreException) e.getCause());
			} else {
				IDEWorkbenchPlugin.log(e.getMessage(), e);
				displayError(e.getMessage());
			}
			return false;
		}
		return true;
	}

	/**
	 * Records the core exception to be displayed to the user once the action is
	 * finished.
	 *
	 * @param error a <code>CoreException</code>
	 */
	private void recordError(CoreException error) {
		if (errorStatus == null) {
			errorStatus = new MultiStatus(PlatformUI.PLUGIN_ID, IStatus.ERROR, getProblemsMessage(), error);
		}

		errorStatus.merge(error.getStatus());
	}

	/**
	 * Checks whether the destination is valid for copying the source resources.
	 * <p>
	 * Note this method is for internal use only. It is not API.
	 * </p>
	 *
	 * @param destination     the destination container
	 * @param sourceResources the source resources
	 * @return an error message, or <code>null</code> if the path is valid
	 */
	public String validateDestination(IContainer destination, IResource[] sourceResources) {
		if (!isAccessible(destination)) {
			return IDEWorkbenchMessages.CopyFilesAndFoldersOperation_destinationAccessError;
		}
		IContainer firstParent = null;
		URI destinationLocation = destination.getLocationURI();
		for (int i = 0; i < sourceResources.length; i++) {
			IResource sourceResource = sourceResources[i];
			if (firstParent == null) {
				firstParent = sourceResource.getParent();
			} else if (!firstParent.equals(sourceResource.getParent())) {
				// Resources must have common parent. Fixes bug 33398.
				return IDEWorkbenchMessages.CopyFilesAndFoldersOperation_parentNotEqual;
			}

			// verify that if the destination is a virtual folder, the resource must be
			// either a link or another virtual folder
			if (destination.isVirtual() && !sourceResource.isLinked() && !sourceResource.isVirtual() && !createLinks
					&& !createVirtualFoldersAndLinks) {
				return NLS.bind(
						IDEWorkbenchMessages.CopyFilesAndFoldersOperation_sourceCannotBeCopiedIntoAVirtualFolder,
						sourceResource.getName());
			}
			URI sourceLocation = sourceResource.getLocationURI();
			if (sourceLocation == null) {
				if (sourceResource.isLinked()) {
					// Don't allow copying linked resources with undefined path
					// variables. See bug 28754.
					return NLS.bind(IDEWorkbenchMessages.CopyFilesAndFoldersOperation_missingPathVariable,
							sourceResource.getName());
				}
				return NLS.bind(IDEWorkbenchMessages.CopyFilesAndFoldersOperation_resourceDeleted,
						sourceResource.getName());

			}
			if (!destination.isVirtual()) {
				if (sourceLocation.equals(destinationLocation)) {
					return NLS.bind(IDEWorkbenchMessages.CopyFilesAndFoldersOperation_sameSourceAndDest,
							sourceResource.getName());
				}
				// is the source a parent of the destination?
				if (new Path(sourceLocation.toString()).isPrefixOf(new Path(destinationLocation.toString()))) {
					return IDEWorkbenchMessages.CopyFilesAndFoldersOperation_destinationDescendentError;
				}
			}

			String linkedResourceMessage = validateLinkedResource(destination, sourceResource);
			if (linkedResourceMessage != null) {
				return linkedResourceMessage;
			}
		}
		return null;
	}

	/**
	 * Validates that the given source resources can be copied to the destination as
	 * decided by the VCM provider.
	 *
	 * @param destination     copy destination
	 * @param sourceResources source resources
	 * @return <code>true</code> all files passed validation or there were no files
	 *         to validate. <code>false</code> one or more files did not pass
	 *         validation.
	 */
	private boolean validateEdit(IContainer destination, IResource[] sourceResources) {
		ArrayList<IFile> copyFiles = new ArrayList<>();

		collectExistingReadonlyFiles(destination.getFullPath(), sourceResources, copyFiles);
		if (!copyFiles.isEmpty()) {
			IFile[] files = copyFiles.toArray(new IFile[copyFiles.size()]);
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IStatus status = workspace.validateEdit(files, messageShell);

			canceled = !status.isOK();
			return status.isOK();
		}
		return true;
	}

	/**
	 * Check if the destination is valid for the given source resource.
	 *
	 * @param destination destination container of the operation
	 * @param source      source resource
	 * @return String error message or null if the destination is valid
	 */
	private String validateLinkedResource(IContainer destination, IResource source) {
		if (!source.isLinked() || source.isVirtual()) {
			return null;
		}
		IWorkspace workspace = destination.getWorkspace();
		IResource linkHandle = createLinkedResourceHandle(destination, source);
		IStatus locationStatus = workspace.validateLinkLocationURI(linkHandle, source.getRawLocationURI());

		if (locationStatus.getSeverity() == IStatus.ERROR) {
			return locationStatus.getMessage();
		}
		IPath sourceLocation = source.getLocation();
		if (!source.getProject().equals(destination.getProject()) && source.getType() == IResource.FOLDER
				&& sourceLocation != null) {
			// prevent merging linked folders that point to the same
			// file system folder
			try {
				IResource[] members = destination.members();
				for (int j = 0; j < members.length; j++) {
					if (sourceLocation.equals(members[j].getLocation())
							&& source.getName().equals(members[j].getName())) {
						return NLS.bind(IDEWorkbenchMessages.CopyFilesAndFoldersOperation_sameSourceAndDest,
								source.getName());
					}
				}
			} catch (CoreException exception) {
				displayError(NLS.bind(IDEWorkbenchMessages.CopyFilesAndFoldersOperation_internalError,
						exception.getMessage()));
			}
		}
		return null;
	}

	/**
	 * Returns whether moving all of the given source resources to the given
	 * destination container could be done without causing name collisions.
	 *
	 * @param destination     the destination container
	 * @param sourceResources the list of resources
	 * @return <code>true</code> if there would be no name collisions, and
	 *         <code>false</code> if there would
	 */
	private IResource[] validateNoNameCollisions(IContainer destination, IResource[] sourceResources) {
		List<IResource> copyItems = new ArrayList<>();
		IWorkspaceRoot workspaceRoot = destination.getWorkspace().getRoot();
		int overwrite = IDialogConstants.NO_ID;

		// Check to see if we would be overwriting a parent folder.
		// Cancel entire copy operation if we do.
		for (int i = 0; i < sourceResources.length; i++) {
			final IResource sourceResource = sourceResources[i];
			final IPath destinationPath = destination.getFullPath().append(sourceResource.getName());
			final IPath sourcePath = sourceResource.getFullPath();

			IResource newResource = workspaceRoot.findMember(destinationPath);
			if (newResource != null && destinationPath.isPrefixOf(sourcePath)) {
				displayError(NLS.bind(IDEWorkbenchMessages.CopyFilesAndFoldersOperation_overwriteProblem,
						destinationPath, sourcePath));

				canceled = true;
				return EMPTY_RESOURCE_ARRAY;
			}
		}
		// Check for overwrite conflicts
		for (int i = 0; i < sourceResources.length; i++) {
			final IResource source = sourceResources[i];
			final IPath destinationPath = destination.getFullPath().append(source.getName());

			IResource newResource = workspaceRoot.findMember(destinationPath);
			if (newResource != null) {
				if (overwrite != IDialogConstants.YES_TO_ALL_ID || (newResource.getType() == IResource.FOLDER
						&& !PooslOverwriteRunnable.homogenousResources(source, destination))) {
					overwrite = checkOverwrite(source, newResource);
				}
				if (overwrite == IDialogConstants.YES_ID || overwrite == IDialogConstants.YES_TO_ALL_ID) {
					copyItems.add(source);
				} else if (overwrite == IDialogConstants.CANCEL_ID) {
					canceled = true;
					return EMPTY_RESOURCE_ARRAY;
				}
			} else {
				copyItems.add(source);
			}
		}
		return copyItems.toArray(new IResource[copyItems.size()]);
	}

	private void copyResources(final IResource[] resources, final IPath destinationPath,
			final IResource[][] copiedResources, IProgressMonitor monitor) {
		IResource[] copyResources = resources;

		// Fix for bug 31116. Do not provide a task name when
		// creating the task.
		monitor.beginTask("", 100); //$NON-NLS-1$
		monitor.setTaskName(getOperationTitle());
		monitor.worked(10); // show some initial progress

		// Checks only required if this is an exisiting container path.
		boolean copyWithAutoRename = false;
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		if (root.exists(destinationPath)) {
			IContainer container = (IContainer) root.findMember(destinationPath);
			// If we're copying to the source container then perform
			// auto-renames on all resources to avoid name collisions.
			if (isDestinationSameAsSource(copyResources, container)) {
				copyWithAutoRename = true;
			} else {
				// If no auto-renaming will be happening, check for
				// potential name collisions at the target resource
				copyResources = validateNoNameCollisions(container, copyResources);
				if (copyResources == null) {
					if (canceled) {
						return;
					}
					displayError(IDEWorkbenchMessages.CopyFilesAndFoldersOperation_nameCollision);
					return;
				}
				if (!validateEdit(container, copyResources)) {
					return;
				}
			}
		}

		errorStatus = null;
		if (copyResources.length > 0) {
			if (copyWithAutoRename) {

				performCopyWithAutoRename(copyResources, destinationPath, SubMonitor.convert(monitor, 90));
			} else {
				performCopy(copyResources, destinationPath, SubMonitor.convert(monitor, 90));
			}
		}
		monitor.done();
		copiedResources[0] = copyResources;
	}

	/**
	 * Returns the model provider ids that are known to the client that instantiated
	 * this operation.
	 *
	 * @return the model provider ids that are known to the client that instantiated
	 *         this operation.
	 * 
	 */
	public String[] getModelProviderIds() {
		return modelProviderIds;
	}

	private void copyDiagrams(IResource[] resources, IPath[] destinationPaths, IProgressMonitor monitor) {

		for (int i = 0; i < resources.length; i++) {
			PooslDiagramRefactorHelper.copy(resources[i], destinationPaths[i], monitor);
		}
	}
}