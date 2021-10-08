/*******************************************************************************
 * Copyright (c) 2021 TNO/ESI
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    TNO/ESI - initial API and implementation
 *    Obeo - refactoring
 *******************************************************************************/
package org.eclipse.poosl.rotalumisclient.logging;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.poosl.rotalumisclient.Activator;
import org.eclipse.poosl.rotalumisclient.PooslConstants;

/**
 * The PooslLogger.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class PooslLogger {

    private static final File RUNNING_LOGFILE = new File("Logging.html"); //$NON-NLS-1$

    private static FileHandler fileHTML;

    private static final Logger LOGGER = Logger.getLogger("org.eclipse.poosl"); //$NON-NLS-1$

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
        String logLevel = Platform.getPreferencesService().getString(Activator.PLUGIN_ID, PooslConstants.PREFERENCES_LOG_LEVEL, PooslConstants.DEFAULT_LOG_LEVEL, null);
        LOGGER.setLevel(Level.parse(logLevel));

        // try to remove listener, avoiding duplicate
        IPreferenceStore prefs = Activator.getDefault().getPreferenceStore();
        if (propertyChangeListener != null) {
            prefs.removePropertyChangeListener(propertyChangeListener);
        }

        propertyChangeListener = new IPropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent event) {
                if (PooslConstants.PREFERENCES_LOG_LEVEL.equals(event.getProperty())) { // $NON-NLS-1$
                    String value = event.getNewValue().toString();
                    LOGGER.setLevel(Level.parse(value));
                    if (fileHTML != null) {
                        fileHTML.setLevel(Level.parse(value));
                    }
                }
            }
        };
        prefs.addPropertyChangeListener(propertyChangeListener);

        // create HTML Formatter
        Formatter formatterHTML = new PooslHtmlFormatter();

        // Create HTML LOGGER
        if (RUNNING_LOGFILE.exists() && !RUNNING_LOGFILE.delete()) {
            LOGGER.log(Level.WARNING, "Could not delete file " // $NON-NLS-1$
                    + RUNNING_LOGFILE.getAbsolutePath());
        }
        try {
            fileHTML = new FileHandler(RUNNING_LOGFILE.getPath(), false); // $NON-NLS-1$
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
