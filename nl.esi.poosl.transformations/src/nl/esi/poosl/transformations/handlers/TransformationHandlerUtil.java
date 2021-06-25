package nl.esi.poosl.transformations.handlers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class TransformationHandlerUtil {
	private static final Logger LOGGER = Logger.getLogger(TransformationHandlerUtil.class.getName());

	private TransformationHandlerUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static void refreshProject(IProject project) {
		try {
			project.refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			LOGGER.log(Level.WARNING,
					"Document generation could not refresh project \"" + project.getName() + "\" after generation.", e);
		}
	}
}
