package nl.esi.poosl.rotalumisclient.extension;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.e4.core.di.annotations.Execute;

import nl.esi.poosl.rotalumisclient.PooslConstants;

public class ExternLaunchStopInformer {
	private static final Logger LOGGER = Logger.getLogger(ExternLaunchStopInformer.class.getName());

	@Execute
	public void executeInformLaunchStopped(ILaunch launch) {
		ILaunchConfiguration launchConfiguration = launch.getLaunchConfiguration();
		try {
			String id = launchConfiguration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_PORT, "");
			for (IPooslDebugInformer extension : ExtensionHelper.getExtensions()) {
				executeExtensionLaunch(extension, id);
			}
		} catch (CoreException e) {
			LOGGER.log(Level.WARNING, e.getMessage(), e);
		}
	}

	private void executeExtensionLaunch(final IPooslDebugInformer o, final String launchID) {
		ISafeRunnable runnable = new ISafeRunnable() {
			@Override
			public void handleException(Throwable e) {
				LOGGER.log(Level.FINE, "Exception in client" + e.getMessage());
			}

			@Override
			public void run() throws Exception {
				o.launchStopped(launchID);
			}
		};
		SafeRunner.run(runnable);
	}
}
