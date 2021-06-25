package nl.esi.poosl.rotalumisclient.logging;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

import nl.esi.poosl.rotalumisclient.Activator;
import nl.esi.poosl.rotalumisclient.PooslConstants;

public final class PooslLogger {
	private static FileHandler fileHTML = null;
	private static final Logger LOGGER = Logger.getLogger("nl.esi.poosl");
	private static IPropertyChangeListener propertyChangeListener;

	private PooslLogger() {
		throw new IllegalStateException("Utility class");
	}

	public static void setup() {
		// Configure the LOGGER
		for (Handler handler : LOGGER.getHandlers()) {
			handler.close();
			LOGGER.removeHandler(handler);
		}
		// Get the log level value from the preferences
		String logLevel = Platform.getPreferencesService().getString(PooslConstants.PLUGIN_ID,
				PooslConstants.PREFERENCES_LOG_LEVEL, PooslConstants.DEFAULT_LOG_LEVEL, null);
		LOGGER.setLevel(Level.parse(logLevel));

		// Add a listener to the preferences to react on changes
		try {
			// try to remove listener, avoiding duplicate
			Activator.getDefault().getPreferenceStore().removePropertyChangeListener(propertyChangeListener);
		} catch (Exception e) {
			// remove not possible
		}

		propertyChangeListener = new IPropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				if ("LOG_LEVEL".equals(event.getProperty())) {
					String value = event.getNewValue().toString();
					LOGGER.setLevel(Level.parse(value));
					if (fileHTML != null) {
						fileHTML.setLevel(Level.parse(value));
					}
				}
			}
		};
		Activator.getDefault().getPreferenceStore().addPropertyChangeListener(propertyChangeListener);

		// create HTML Formatter
		Formatter formatterHTML = new PooslHtmlFormatter();

		// Create HTML LOGGER
		File logFile = new File("Logging.html");
		if (logFile.exists()) {
			boolean result = logFile.delete();
			if (!result) {
				LOGGER.log(Level.WARNING, "Could not delete file " + logFile.getAbsolutePath());
			}
		}
		try {
			fileHTML = new FileHandler("Logging.html", false);
		} catch (SecurityException | IOException e) {
			LOGGER.log(Level.WARNING, e.getMessage(), e);
		}
		if (fileHTML != null) {
			fileHTML.setLevel(Level.parse(logLevel));
			fileHTML.setFormatter(formatterHTML);
			LOGGER.addHandler(fileHTML);
		}

		// Disable default logging to console
		LOGGER.setUseParentHandlers(false);

		// Create console handler
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.WARNING);
		LOGGER.addHandler(consoleHandler);
	}
}
