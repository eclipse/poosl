package nl.esi.poosl.sirius.navigator.rename;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.sirius.business.api.session.Session;

import nl.esi.poosl.sirius.helpers.ConvertHelper;
import nl.esi.poosl.sirius.helpers.GraphicalEditorHelper;
import nl.esi.poosl.sirius.helpers.PooslDiagramRefactorHelper;

/**
 * Change that updates any Sirius diagrams of the file or files in the folder.
 * 
 * @author kstaal
 *
 */
public class PooslFileChange extends Change {
	private static final String NAME = "PooslFileChange";
	private static final Logger LOGGER = Logger.getLogger(PooslFileChange.class.getName());

	private IFile fFile;
	private IFolder fFolder;
	private String newName;

	private Resource oldResource;
	private Session session;

	public PooslFileChange(IFile fFile, String newName) {
		this.fFile = fFile;
		this.newName = newName;
	}

	public PooslFileChange(IFile fFile, IFolder folder, String newName) {
		this.fFile = fFile;
		this.fFolder = folder;
		this.newName = newName;
	}

	private void copyFileChangeToNewResource(IFile file, IProgressMonitor monitor) {
		IPath fullPath = createNewPath(file.getFullPath());
		renameResourceDiagrams(monitor, file.getProject(), fullPath);
	}

	private void copyFolderChangeToNewResource(IFile file, IFolder folder, IProgressMonitor monitor) {
		IPath folderPath = createNewPath(folder.getFullPath());
		IPath filePath = file.getFullPath();
		filePath = filePath.removeFirstSegments(folderPath.segmentCount());
		filePath = folderPath.append(filePath);
		renameResourceDiagrams(monitor, folder.getProject(), filePath);
	}

	private IPath createNewPath(IPath path) {
		IPath oldPath = path;
		oldPath = oldPath.removeLastSegments(1);
		return oldPath.append(newName);
	}

	private void renameResourceDiagrams(IProgressMonitor monitor, IProject project, IPath fullPath) {
		Resource newResource = ConvertHelper.convertIPathToResource(fullPath);
		session = GraphicalEditorHelper.getSession(project, null, false, true, monitor);

		if (session != null) {
			PooslDiagramRefactorHelper.renameResource(session, oldResource, newResource, monitor);
		}
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void initializeValidationData(IProgressMonitor pm) {
		oldResource = ConvertHelper.convertIFileToResource(fFile);
	}

	@Override
	public RefactoringStatus isValid(IProgressMonitor pm) {
		return new RefactoringStatus();
	}

	@Override
	public Change perform(IProgressMonitor pm) throws CoreException {
		if (oldResource != null) { // poosl resource
			if (fFolder == null) {
				copyFileChangeToNewResource(fFile, pm);
			} else {
				copyFolderChangeToNewResource(fFile, fFolder, pm);
			}
		}
		return null;
	}

	@Override
	public Object getModifiedElement() {
		return session;
	}

	@Override
	public void dispose() {
		try {
			if (oldResource != null) {
				oldResource.delete(null);
			}
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, "Error unloading old resource after rename.", e);
		}
		fFile = null;
		fFolder = null;
		newName = null;
		oldResource = null;
		session = null;
		super.dispose();
	}
}
