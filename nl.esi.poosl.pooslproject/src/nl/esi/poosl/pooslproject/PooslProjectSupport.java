package nl.esi.poosl.pooslproject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class PooslProjectSupport {
	private static final Logger LOGGER = Logger.getLogger(PooslProjectSupport.class.getName());
	public static final String NATURE_ID = "org.eclipse.xtext.ui.shared.xtextNature";

	private PooslProjectSupport() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * For this marvelous project we need to: - create the default Eclipse project -
	 * add the custom project nature - create the folder structure
	 * 
	 * @param projectName
	 * @param location
	 * @return
	 * @throws CoreException
	 */
	public static IProject createProject(String projectName, URI location) throws CoreException {
		Assert.isNotNull(projectName);
		Assert.isTrue(projectName.trim().length() > 0);
		IProject project = createBaseProject(projectName, location);
		if (project != null) {
			addNature(project, NATURE_ID);
			addProjectStructure(project);
		}
		return project;
	}

	/**
	 * Just do the basics: create a basic project.
	 * 
	 * @param location
	 * @param projectName
	 * @throws CoreException
	 */
	private static IProject createBaseProject(String projectName, URI location) throws CoreException {
		// it is acceptable to use the ResourcesPlugin class
		IProject newProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);

		if (newProject != null && !newProject.exists()) {
			URI projectLocation = location;
			IProjectDescription desc = newProject.getWorkspace().newProjectDescription(newProject.getName());
			if (location != null && ResourcesPlugin.getWorkspace().getRoot().getLocationURI().equals(location)) {
				projectLocation = null;
			}

			desc.setLocationURI(projectLocation);
			newProject.create(desc, null);
			if (!newProject.isOpen()) {
				newProject.open(null);
			}
		}

		return newProject;
	}

	private static void createFolder(IFolder folder) throws CoreException {
		IContainer parent = folder.getParent();
		if (parent instanceof IFolder) {
			createFolder((IFolder) parent);
		}
		if (!folder.exists()) {
			folder.create(false, true, null);
		}
	}

	/**
	 * Create a folder structure with a parent root, overlay, and a few child
	 * folders.
	 * 
	 * @param newProject
	 * @param paths
	 * @throws CoreException
	 */
	private static void addProjectStructure(IProject newProject) throws CoreException {
		IFolder modelsFolder = newProject.getFolder("models");
		createFolder(modelsFolder);
		IFile modelFile = newProject.getFile("models/model.poosl");
		createFile(modelFile);
	}

	private static void createFile(IFile file) throws CoreException {
		URL url;
		InputStream inputStream = null;
		try {
			url = new URL("platform:/plugin/nl.esi.poosl.pooslproject/templates/" + file.getName());
			inputStream = url.openConnection().getInputStream();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		file.create(inputStream, true, null);
	}

	private static void addNature(IProject project, String natureID) throws CoreException {
		if (!project.hasNature(natureID)) {
			IProjectDescription description = project.getDescription();
			String[] prevNatures = description.getNatureIds();
			String[] newNatures = new String[prevNatures.length + 1];
			System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
			newNatures[prevNatures.length] = natureID;
			description.setNatureIds(newNatures);

			IProgressMonitor monitor = null;
			project.setDescription(description, monitor);
		}
	}
}