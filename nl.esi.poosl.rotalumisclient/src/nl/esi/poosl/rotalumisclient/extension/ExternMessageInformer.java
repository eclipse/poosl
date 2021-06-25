package nl.esi.poosl.rotalumisclient.extension;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugTarget;

import nl.esi.poosl.generatedxmlclasses.TCommunicationEvent;
import nl.esi.poosl.rotalumisclient.PooslConstants;
import nl.esi.poosl.rotalumisclient.debug.PooslDebugTarget;

public class ExternMessageInformer {
	private static final Logger LOGGER = Logger.getLogger(ExternMessageInformer.class.getName());

	public void executeInformMessage(ILaunch launch, TCommunicationEvent event) {
		IDebugTarget target = launch.getDebugTarget();

		if (target instanceof PooslDebugTarget) {
			PooslDebugTarget pooslTarget = (PooslDebugTarget) target;
			if (!pooslTarget.isEdited()) {
				try {
					String launchid = launch.getLaunchConfiguration()
							.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_PORT, "");
					String modelpath = launch.getLaunchConfiguration()
							.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_RELATIVE_PATH, "");
					ExternDebugMessage externMassage = new ExternDebugMessage(launchid, modelpath, event);
					for (IPooslDebugInformer extension : ExtensionHelper.getExtensions()) {
						executeExtensionMessage(extension, externMassage);
					}
				} catch (InstantiationException | CoreException e) {
					LOGGER.log(Level.WARNING, e.getMessage(), e);
				}
			}
		}
	}

	private void executeExtensionMessage(final IPooslDebugInformer o, final ExternDebugMessage message) {
		ISafeRunnable runnable = new ISafeRunnable() {
			@Override
			public void handleException(Throwable e) {
				LOGGER.log(Level.FINE, "Exception in client" + e.getMessage());
			}

			@Override
			public void run() throws Exception {
				o.lastMessageChanged(message);
			}
		};
		SafeRunner.run(runnable);
	}
}
