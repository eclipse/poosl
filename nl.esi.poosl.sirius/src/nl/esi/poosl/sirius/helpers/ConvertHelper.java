package nl.esi.poosl.sirius.helpers;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import nl.esi.poosl.Poosl;
import nl.esi.poosl.sirius.IPreferenceConstants;
import nl.esi.poosl.xtext.importing.ImportingHelper;

public class ConvertHelper {

	private ConvertHelper() {
		throw new IllegalStateException("Utility class");
	}

	public static Poosl convertIFileToPoosl(IFile file) {
		if (file != null) {
			Resource resource = convertIFileToResource(file);
			if (resource != null) {
				return ImportingHelper.toPoosl(resource);
			}
		}
		return null;
	}

	public static Resource convertIFileToResource(IFile file) {
		String fileExtension = file.getFileExtension();
		if (fileExtension != null && fileExtension.equals(IPreferenceConstants.POOSL_FILE_EXT)) {
			URI uri = URI.createPlatformResourceURI(file.getFullPath().toString().substring(1), true);
			ResourceSet resourceSet = new ResourceSetImpl();
			resourceSet.getPackageRegistry().put(nl.esi.poosl.PooslPackage.eINSTANCE.getNsURI(),
					nl.esi.poosl.PooslPackage.eINSTANCE);
			return resourceSet.getResource(uri, true);
		}
		return null;
	}

	public static Resource convertIPathToResource(IPath path) {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IFile ifile = workspace.getRoot().getFile(path);
		return convertIFileToResource(ifile);
	}

	public static IFile convertISelectionToIFile(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Object firstElement = ((IStructuredSelection) selection).getFirstElement();
			if (firstElement instanceof IFile) {
				return (IFile) firstElement;
			}
		}
		return null;
	}
}
