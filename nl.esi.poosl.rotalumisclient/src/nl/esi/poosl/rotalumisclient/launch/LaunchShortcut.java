package nl.esi.poosl.rotalumisclient.launch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IPersistableSourceLocator;
import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupDirector;
import org.eclipse.debug.core.sourcelookup.ISourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.ProjectSourceContainer;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;

import nl.esi.poosl.rotalumisclient.PooslConstants;
import nl.esi.poosl.rotalumisclient.logging.PooslLogger;
import nl.esi.poosl.xtext.importing.ImportingHelper;

public class LaunchShortcut implements ILaunchShortcut {
	private static final Logger LOGGER = Logger.getLogger(LaunchShortcut.class.getName());

	@Override
	public void launch(ISelection selection, String mode) {
		PooslLogger.setup();
		LOGGER.info("launching from selection: " + selection + " in mode: " + mode);
		IStructuredSelection sel = (IStructuredSelection) selection;
		for (Object file : sel.toArray()) {
			IFile f = (IFile) file;
			actualLaunch(f, mode);
		}
	}

	@Override
	public void launch(IEditorPart editor, String mode) {
		PooslLogger.setup();
		LOGGER.info("launching from editor: " + editor.getTitle() + " in mode: " + mode);
		actualLaunch(((IFileEditorInput) editor.getEditorInput()).getFile(), mode);
	}

	private void actualLaunch(IFile file, String mode) {
		try {
			ILaunchManager mgr = DebugPlugin.getDefault().getLaunchManager();
			ILaunchConfigurationType lct = mgr.getLaunchConfigurationType(getLaunchType());
			ILaunchConfiguration[] options = mgr.getLaunchConfigurations(lct);
			ILaunchConfiguration configuration = null;
			for (ILaunchConfiguration iLaunchConfiguration : options) {
				if (isLaunchConfiguration(file, iLaunchConfiguration)) {
					configuration = iLaunchConfiguration;
					LOGGER.info("Found an existing configuration for the model: " + file.getLocation().toString());
				}
			}
			if (configuration == null) {
				ILaunchConfigurationWorkingCopy tempConfiguration = createLaunchConfiguration(mgr, lct, file,
						file.getName());
				configuration = tempConfiguration.doSave();
				LOGGER.info("Created a new configuration for the model: " + file.getLocation().toString());
			} else {
				validateNewAttributes(configuration, file);
			}
			DebugUITools.launch(configuration, mode);
		} catch (CoreException e) {
			LOGGER.log(Level.WARNING, e.getMessage(), e);
		}
	}

	private boolean isLaunchConfiguration(IFile file, ILaunchConfiguration iLaunchConfiguration) throws CoreException {
		return iLaunchConfiguration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_MODEL_PATH, "")
				.equals(file.getLocation().toOSString());
	}

	private ILaunchConfigurationWorkingCopy createLaunchConfiguration(ILaunchManager mgr, ILaunchConfigurationType lct,
			IFile file, String name) throws CoreException {

		ILaunchConfigurationWorkingCopy tempConfiguration = lct.newInstance(null, name);
		tempConfiguration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_MODEL_PATH,
				file.getLocation().toOSString());

		String externalConfigPath = findExternalConfigPath(file);
		if (externalConfigPath != null) {
			tempConfiguration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_EXTERNAL_CONFIG_PATH,
					externalConfigPath);
		}

		tempConfiguration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_PROJECT, file.getProject().getName());
		tempConfiguration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_RELATIVE_PATH,
				file.getFullPath().toOSString());
		tempConfiguration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_PORT, "10001");
		tempConfiguration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_TEST_CONF, isTestConfiguration());

		String type = tempConfiguration.getType().getSourceLocatorId();
		IPersistableSourceLocator locator = mgr.newSourceLocator(type);
		if (locator instanceof AbstractSourceLookupDirector) {
			AbstractSourceLookupDirector director = (AbstractSourceLookupDirector) locator;
			director.setSourceContainers(createSourceContainers(file.getProject()));

			tempConfiguration.setAttribute(ILaunchConfiguration.ATTR_SOURCE_LOCATOR_MEMENTO, director.getMemento());
			tempConfiguration.setAttribute(ILaunchConfiguration.ATTR_SOURCE_LOCATOR_ID, director.getId());
		}
		return tempConfiguration;
	}

	private ISourceContainer[] createSourceContainers(IProject project) {
		List<ISourceContainer> sourceContainers = new ArrayList<>();

		ProjectSourceContainer projectContainer = new ProjectSourceContainer(project, false);
		sourceContainers.add(projectContainer);

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		for (Iterator<String> it = ImportingHelper.getIncludes(project).iterator(); it.hasNext();) {
			String include = it.next();
			IPath includePath = new Path(include);
			if (!includePath.isAbsolute()) {
				IProject iProject = root.getProject(includePath.segment(0));
				ProjectSourceContainer includedContainer = new ProjectSourceContainer(iProject, false);
				sourceContainers.add(includedContainer);
			}
		}
		return sourceContainers.toArray(new ISourceContainer[sourceContainers.size()]);
	}

	/**
	 * Validates if the configuration contains the "new" attributes, if not set them
	 * 
	 * @param configuration
	 * @param file
	 * @throws CoreException
	 */
	private void validateNewAttributes(ILaunchConfiguration configuration, IFile file) throws CoreException {
		String projectName = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_PROJECT, "");
		String relativePath = configuration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_RELATIVE_PATH, "");
		if (file != null && (projectName.isEmpty() || relativePath.isEmpty())) {
			ILaunchConfigurationWorkingCopy tempConfiguration = configuration.getWorkingCopy();
			if (relativePath.isEmpty()) {
				tempConfiguration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_RELATIVE_PATH,
						file.getFullPath().toOSString());
			}
			if (projectName.isEmpty()) {
				tempConfiguration.setAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_PROJECT,
						file.getProject().getName());
			}
			tempConfiguration.doSave();
		}
	}

	/**
	 * Returns the path of an ini file in the same directory as the provided file
	 * 
	 * @param file
	 * @return Absolute path as String to the ini file
	 * @throws CoreException
	 */
	private String findExternalConfigPath(IFile file) throws CoreException {
		final String[] iniFileLocation = new String[1];
		IResourceProxyVisitor visitor = new IResourceProxyVisitor() {

			@Override
			public boolean visit(IResourceProxy proxy) throws CoreException {
				if (proxy.getName().endsWith(PooslConstants.EXTERN_CONFIG_EXTENSION)) {
					iniFileLocation[0] = proxy.requestResource().getLocation().toOSString();
				}
				return true;
			}
		};
		file.getParent().accept(visitor, IResource.DEPTH_ONE, IResource.NONE);
		return iniFileLocation[0];
	}

	protected String getLaunchType() {
		return PooslConstants.CONFIGURATION_ATTRIBUTE_LAUNCH_TYPE;
	}

	protected boolean isTestConfiguration() {
		return false;
	}
}
