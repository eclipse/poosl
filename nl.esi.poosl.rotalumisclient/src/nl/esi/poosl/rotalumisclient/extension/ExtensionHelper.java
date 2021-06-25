package nl.esi.poosl.rotalumisclient.extension;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

public class ExtensionHelper {
	private static final String POOSL_DEUBUG_ATTRIBUTE = "class";
	private static final String IPOOSLDEBUGINFORMER_ID = "nl.esi.poosl.rotalumisclient.debuginformer";

	private static final Logger LOGGER = Logger.getLogger(ExtensionHelper.class.getName());

	private ExtensionHelper() {
		throw new IllegalStateException("Utility class");
	}

	public static List<IPooslDebugInformer> getExtensions() {
		List<IPooslDebugInformer> extensions = new ArrayList<>();
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] config = registry.getConfigurationElementsFor(IPOOSLDEBUGINFORMER_ID);
		try {
			for (IConfigurationElement e : config) {
				final Object o = e.createExecutableExtension(POOSL_DEUBUG_ATTRIBUTE);

				if (o instanceof IPooslDebugInformer) {
					extensions.add((IPooslDebugInformer) o);
				}
			}
		} catch (CoreException ex) {
			LOGGER.log(Level.FINE, "Extension could not be loaded.");
		}
		return extensions;
	}
}
