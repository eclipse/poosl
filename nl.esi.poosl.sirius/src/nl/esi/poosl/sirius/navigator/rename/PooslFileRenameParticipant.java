package nl.esi.poosl.sirius.navigator.rename;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.RenameParticipant;

public class PooslFileRenameParticipant extends RenameParticipant {
	private static final Logger LOGGER = Logger.getLogger(PooslFileRenameParticipant.class.getName());
	private static final String NAME = "PooslFileRenameParticipant";

	private IFile fFile;
	private IFolder fFolder;

	public PooslFileRenameParticipant() {
		// do nothing
	}

	@Override
	protected boolean initialize(Object element) {
		if (element instanceof IFile) {
			fFile = (IFile) element;
			return true;
		} else if (element instanceof IFolder) {
			fFolder = (IFolder) element;
			return true;
		}
		return false;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public RefactoringStatus checkConditions(IProgressMonitor pm, CheckConditionsContext context) {
		return new RefactoringStatus();
	}

	@Override
	public Change createChange(IProgressMonitor pm) throws CoreException {
		Change change = null;
		if (fFile != null) {
			change = new PooslFileChange(fFile, getArguments().getNewName());
		} else if (fFolder != null) {
			change = new CompositeChange("PooslFolderRename", getFolderChanges(fFolder));
		}
		fFile = null;
		fFolder = null;
		return change;
	}

	private Change[] getFolderChanges(IFolder folder) {
		final List<IFile> files = new ArrayList<>();
		IResourceVisitor visitor = new IResourceVisitor() {
			@Override
			public boolean visit(IResource resource) {

				if (resource.getType() == IResource.FILE && "poosl".equalsIgnoreCase(resource.getFileExtension())) {
					files.add((IFile) resource);
				}
				return true;
			}
		};
		try {
			folder.accept(visitor);
		} catch (CoreException e) {
			LOGGER.log(Level.WARNING, "Cannot accept delta from resource changelistener", e);
		}
		Change[] changes = new Change[files.size()];
		for (int i = 0; i < files.size(); i++) {
			changes[i] = new PooslFileChange(files.get(i), folder, getArguments().getNewName());
		}
		return changes;
	}

}
