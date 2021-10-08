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
package org.eclipse.poosl.rotalumisclient.runner;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.SystemUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.poosl.rotalumisclient.Activator;
import org.eclipse.poosl.rotalumisclient.Messages;
import org.eclipse.poosl.rotalumisclient.PooslConstants;
import org.eclipse.poosl.rotalumisclient.runner.IBundleInfo.Context;

/**
 * The RotalumisRunner.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class RotalumisRunner {

    private static final String SEGMENT_SEPARATOR = "/"; //$NON-NLS-1$

    private static final Logger LOGGER = Logger.getLogger(RotalumisRunner.class.getName());

    private RotalumisRunner() {
        throw new IllegalStateException("Utility class");
    }

    public static File getRotalumis() throws IOException, CoreException {
        try {
            String customRotalumis = System.getenv(PooslConstants.RUNNER_PROPERTY_KEY);
            if (customRotalumis != null) {
                LOGGER.log(Level.INFO, "Using custom Rotalumis: " + customRotalumis);
                return new File(customRotalumis);
            }
        } catch (SecurityException exception) {
            LOGGER.log(Level.WARNING, "Access to system variables was denied when trying to get custom Rotalumis location.", exception.getCause());
        }
        return getPluginRotalumis();
    }

    /*
     * Locate and extract the Rotalumis engine executable from the Plugin in a transparent way into a temporary file.
     * The Registries should be able to locate the Plugin/bundles properly, both in OSGi and non-OSGi mode.
     */
    private static File getPluginRotalumis() throws IOException, CoreException {
        String exePath = getOsSegment() + SEGMENT_SEPARATOR //
                + getArchSegment() + SEGMENT_SEPARATOR //
                + getFilename();
        IBundleInfo bundle = IBundleInfo.Registry.INSTANCE.getBundle(PooslConstants.PLUGIN_ID_ROTALUMIS_EXECUTABLES);
        URI rotLocation = bundle.find(Context.SOURCE, exePath);
        if (rotLocation == null || !rotLocation.isFile()) {
            String detail = rotLocation != null ? rotLocation.toString() //
                    : PooslConstants.PLUGIN_ID_ROTALUMIS_EXECUTABLES + SEGMENT_SEPARATOR + exePath;
            throw new IOException(MessageFormat.format(Messages.RUNNER_NO_ENGINE_ERROR, detail));
        }
        File result = new File(rotLocation.toFileString());
        if (!result.canExecute() && !result.setExecutable(true)) {
            // FIXME: PLUGIN_ID_ROTALUMIS_EXECUTABLES should set the file as executable
            throw new IOException(MessageFormat.format(Messages.RUNNER_NOT_EXECUTABLE_ERROR, rotLocation));
        }
        return result;

    }

    private static String getOsSegment() throws CoreException {
        if (SystemUtils.IS_OS_WINDOWS) {
            return "windows"; //$NON-NLS-1$
        } else if (SystemUtils.IS_OS_LINUX) {
            return "linux"; //$NON-NLS-1$
        } else if (SystemUtils.IS_OS_MAC) {
            return "mac"; //$NON-NLS-1$
        } else {
            throw toNoSupportFailure(SystemUtils.OS_NAME);
        }
    }

    private static String getArchSegment() throws CoreException {
        // FIXME: maybe Platform.getOSArch() should be used
        if (SystemUtils.OS_ARCH.equals(Platform.ARCH_X86) //
                || "i386".equals(SystemUtils.OS_ARCH)) { //$NON-NLS-1$
            return "32bit"; //$NON-NLS-1$
        } else if (SystemUtils.OS_ARCH.equals(Platform.ARCH_X86_64) //
                || "amd64".equals(SystemUtils.OS_ARCH) //$NON-NLS-1$
        ) {
            return "64bit"; //$NON-NLS-1$
        } else {
            throw toNoSupportFailure(SystemUtils.OS_ARCH);
        }
    }

    private static String getFilename() throws CoreException {
        if (SystemUtils.IS_OS_WINDOWS) {
            return "rotalumis.exe"; //$NON-NLS-1$
        } else if (SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_MAC) {
            return "rotalumis"; //$NON-NLS-1$
        } else {
            throw toNoSupportFailure(SystemUtils.OS_NAME);
        }
    }

    private static CoreException toNoSupportFailure(String value) {
        IStatus status = new Status(Status.ERROR, Activator.PLUGIN_ID, //
                MessageFormat.format(Messages.RUNNER_NO_SUPPORT, value), null);
        LOGGER.log(Level.SEVERE, status.getMessage(), status);
        return new CoreException(status);
    }
}
