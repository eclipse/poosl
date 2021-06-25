package nl.esi.poosl.rotalumisclient.runner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.SystemUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;

import nl.esi.poosl.rotalumisclient.PooslConstants;
import nl.esi.poosl.rotalumisclient.runner.IBundleInfo.Context;

public class RotalumisRunner {
	private static final String ERROR_MESSAGE_ROTALUMIS_EXTRACTION = "Could not find or extract the Rotalumis engine at:\n {0}\n "
			+ "(Exclamation marks are not supported in the path at the end of a directory name.)";
	private static final String ERROR_MESSAGE_ROTALUMIS_CREATION = "Could not create temporary file for Rotalumis at location: {0}";
	private static final String RESULT_MESSAGE_ROTALUMIS_CREATION = "Temporary file for Rotalumis already exists at location: {0}";
	private static final String RESULT_MESSAGE_ROTALUMIS_SET_EXECUTABLE = "Failed to make temporary file for Rotalumis executable at location: {0}";
	private static final String ROTALUMIS_LOCATION_KEY = "POOSL_ROTALUMIS";
	private static final Logger LOGGER = Logger.getLogger(RotalumisRunner.class.getName());

	private RotalumisRunner() {
		throw new IllegalStateException("Utility class");
	}

	public static File getRotalumis() throws IOException, CoreException {
		try {
			String customRotalumis = System.getenv(ROTALUMIS_LOCATION_KEY);
			if (customRotalumis != null) {
				LOGGER.log(Level.INFO, "Using custom Rotalumis: " + customRotalumis);
				return new File(customRotalumis);
			}
		} catch (SecurityException exception) {
			LOGGER.log(Level.WARNING,
					"Access to system variables was denied when trying to get custom Rotalumis location.",
					exception.getCause());
		}
		return getPluginRotalumis();
	}

	/*
	 * Locate and extract the Rotalumis engine executable from the Plugin in a
	 * transparent way into a temporary file. The Registries should be able to
	 * locate the Plugin/bundles properly, both in OSGi and non-OSGi mode.
	 */
	private static File getPluginRotalumis() throws IOException, CoreException {
		String osPath = buildOSPath();
		IBundleInfo bundle = IBundleInfo.Registry.INSTANCE.getBundle(PooslConstants.PLUGIN_ID_ROTALUMIS_EXECUTABLES);
		URI jarLocation = bundle.getRootURI();
		URI rotLocation = bundle.find(Context.SOURCE, osPath);
		File tempFile = createRotalumisFile(osPath, jarLocation);
		File tempDir = new File(tempFile.getParent());

		if (!tempDir.exists()) {
			tempDir.mkdirs();
		}
		try {
			boolean result = tempFile.createNewFile();
			if (!result) {
				LOGGER.log(Level.FINE,
						MessageFormat.format(RESULT_MESSAGE_ROTALUMIS_CREATION, tempFile.getAbsolutePath()));
			}
		} catch (IOException e) {
			throw new IOException(MessageFormat.format(ERROR_MESSAGE_ROTALUMIS_CREATION, tempFile.getAbsolutePath()),
					e);
		}

		try (InputStream inputStream = org.eclipse.emf.ecore.resource.URIConverter.INSTANCE
				.createInputStream(rotLocation); OutputStream fileStream = new FileOutputStream(tempFile)) {
			int i = 0;
			byte[] buf = new byte[1024];
			while ((i = inputStream.read(buf)) != -1) {
				fileStream.write(buf, 0, i);
			}
		} catch (FileNotFoundException e) {
			if (!tempFile.exists()) {
				throw e;
			}
		} catch (IOException e) {
			throw new IOException(MessageFormat.format(ERROR_MESSAGE_ROTALUMIS_EXTRACTION, rotLocation.toString()), e);
		}

		boolean result = tempFile.setExecutable(true);
		if (!result) {
			LOGGER.log(Level.FINE,
					MessageFormat.format(RESULT_MESSAGE_ROTALUMIS_SET_EXECUTABLE, tempFile.getAbsolutePath()));
		}

		return tempFile;
	}

	private static File createRotalumisFile(String osPath, URI jarLocation) {
		URI jarUriLocation = URI.createFileURI(jarLocation.toString());
		List<String> pathSegments = new ArrayList<>(jarUriLocation.segmentsList());

		// remove the scheme
		if (jarLocation.scheme() != null && pathSegments.get(0).contains(jarLocation.scheme())) {
			pathSegments.remove(0);
		}
		// remove the last segment if empty
		if (pathSegments.get(pathSegments.size() - 1).isEmpty()) {
			pathSegments.remove(pathSegments.size() - 1);
		}
		// remove the jar segment
		pathSegments.remove(pathSegments.size() - 1);

		// create filePath which is in the same directory as the Rotalumis jar
		StringBuilder filePathBuilder = new StringBuilder();
		// add File separator to create an absolute path for non windows machines
		if (!SystemUtils.IS_OS_WINDOWS) {
			filePathBuilder.append(File.separator);
		}
		for (String segment : pathSegments) {
			filePathBuilder.append(segment + File.separator);
		}
		return new File(filePathBuilder.toString() + osPath);
	}

	private static String buildOSPath() throws CoreException {
		StringBuilder stringBuilder = new StringBuilder();
		if (SystemUtils.IS_OS_WINDOWS) {
			stringBuilder.append("windows/");
		} else if (SystemUtils.IS_OS_LINUX) {
			stringBuilder.append("linux/");
		} else if (SystemUtils.IS_OS_MAC) {
			stringBuilder.append("mac/");
		} else {
			IStatus status = new Status(Status.ERROR, PooslConstants.PLUGIN_ID,
					"There is no support for your operating system.", null);
			throw new CoreException(status);
		}
		if (SystemUtils.OS_ARCH.equals(Platform.ARCH_X86)) {
			stringBuilder.append("32bit/");
		} else if ("i386".equals(SystemUtils.OS_ARCH)) {
			stringBuilder.append("32bit/");
		} else if (SystemUtils.OS_ARCH.equals(Platform.ARCH_X86_64)) {
			stringBuilder.append("64bit/");
		} else if ("amd64".equals(SystemUtils.OS_ARCH)) {
			// For some reason the constant for Platform.ARCH_amd64 is
			// deprecated
			stringBuilder.append("64bit/");
		} else {
			IStatus status = new Status(Status.ERROR, PooslConstants.PLUGIN_ID,
					"There is no support for your architecture. (" + SystemUtils.OS_ARCH + ")", null);
			LOGGER.log(Level.SEVERE, status.getMessage(), status);
			throw new CoreException(status);
		}
		if (SystemUtils.IS_OS_WINDOWS) {
			stringBuilder.append("rotalumis.exe");
		} else if (SystemUtils.IS_OS_LINUX) {
			stringBuilder.append("rotalumis");
		} else if (SystemUtils.IS_OS_MAC) {
			stringBuilder.append("rotalumis");
		}
		return stringBuilder.toString();
	}
}
