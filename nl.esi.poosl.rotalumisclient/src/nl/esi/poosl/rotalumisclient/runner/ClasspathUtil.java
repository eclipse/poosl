package nl.esi.poosl.rotalumisclient.runner;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Set;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.common.collect.Sets;

public class ClasspathUtil {
	private static final String BUNDLE_SYMBOLIC_NAME = "Bundle-SymbolicName";
	private static final Logger LOG = Logger.getLogger(ClasspathUtil.class.getName());

	private ClasspathUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static Collection<URL> findResources(String... fileNames) {
		Set<URL> result = Sets.newLinkedHashSet();
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		try {
			for (String fileName : fileNames) {
				Enumeration<URL> resources = classLoader.getResources(fileName);
				while (resources.hasMoreElements())
					result.add(resources.nextElement());
			}
		} catch (IOException e) {
			LOG.log(Level.WARNING, e.getMessage(), e);
		}
		if (classLoader instanceof URLClassLoader) {
			@SuppressWarnings("resource")
			URLClassLoader ucl = (URLClassLoader) classLoader;
			for (URL u : ucl.getURLs())
				if (!u.getFile().endsWith(".jar")) {
					try {
						java.io.File f = new java.io.File(u.toURI());
						if (f.isDirectory()) {
							int levels = 5;
							W: while (levels >= 0 && f != null) {
								for (String fileName : fileNames) {
									java.io.File pl = new java.io.File(f + "/" + fileName);
									if (pl.isFile()) {
										result.add(pl.toURI().toURL());
										break W;
									}
								}
								levels--;
								f = f.getParentFile();
							}
						}
					} catch (MalformedURLException | URISyntaxException e) {
						LOG.log(Level.WARNING, e.getMessage(), e);
					}
				}
		}
		// for some reason, ucl.getURLs() doesn't catch the current project in
		// standalone maven surefire
		return result;
	}

	// try-with-resources will automatically close after execution of the block or
	// at an exception
	public static String getSymbolicName(URL url) {
		try (InputStream manifestInputStream = url.openStream()) {
			Manifest manifest = new Manifest(manifestInputStream);
			return getSymbolicName(manifest);
		} catch (IOException e) {
			LOG.log(Level.WARNING, "error parsing manifest", e);
		}
		return null;
	}

	public static String getSymbolicName(Manifest manifest) {
		String name = manifest.getMainAttributes().getValue(BUNDLE_SYMBOLIC_NAME);
		if (name != null) {
			int i = name.indexOf(';');
			if (i >= 0)
				name = name.substring(0, i);
		}
		return name;
	}
}
