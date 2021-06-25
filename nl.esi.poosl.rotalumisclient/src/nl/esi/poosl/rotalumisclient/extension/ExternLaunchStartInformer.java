package nl.esi.poosl.rotalumisclient.extension;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.common.util.URI;

import nl.esi.poosl.rotalumisclient.PooslConstants;
import nl.esi.poosl.rotalumisclient.debug.PooslDebugTarget;

public class ExternLaunchStartInformer {
	private static final Logger LOGGER = Logger.getLogger(ExternLaunchStartInformer.class.getName());

	@Execute
	public void executeInformDebugSelection(PooslDebugTarget pooslTarget, List<URI> files) {

		ILaunchConfiguration launchConfiguration = pooslTarget.getLaunch().getLaunchConfiguration();
		try {
			ExternLaunchStartMessage message = new ExternLaunchStartMessage(
					launchConfiguration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_PORT, ""),
					launchConfiguration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_RELATIVE_PATH, ""),
					launchConfiguration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_PROJECT, ""),
					pooslTarget.getInstancePortMap(), files);
			for (IPooslDebugInformer extension : ExtensionHelper.getExtensions()) {
				executeLaunchStart(extension, message);
			}
		} catch (CoreException e) {
			LOGGER.log(Level.WARNING, e.getMessage(), e);
		}
	}

	private void executeLaunchStart(final IPooslDebugInformer extension, final ExternLaunchStartMessage message) {
		ISafeRunnable runnable = new ISafeRunnable() {
			@Override
			public void handleException(Throwable e) {
				LOGGER.log(Level.FINE, "Exception in client" + e.getMessage());
			}

			@Override
			public void run() throws Exception {
				extension.launchStart(message);
			}
		};
		SafeRunner.run(runnable);
	}
}
