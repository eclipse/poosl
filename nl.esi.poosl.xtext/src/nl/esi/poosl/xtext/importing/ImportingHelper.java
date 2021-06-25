package nl.esi.poosl.xtext.importing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.osgi.service.prefs.Preferences;

import nl.esi.poosl.Import;
import nl.esi.poosl.Poosl;
import nl.esi.poosl.xtext.GlobalConstants;

public final class ImportingHelper {
	private static final String BASIC_CLASSES_JAR_PATH = "nl/esi/poosl/xtext/BasicClasses.poosl";
	private static final String BASIC_CLASSES_PATH = Thread.currentThread().getContextClassLoader()
			.getResource(BASIC_CLASSES_JAR_PATH).toString();

	private ImportingHelper() {
		throw new IllegalStateException("Utility class");
	}

	public static boolean isValidImport(Import imp) {
		String importString = importToString(imp);
		if (importString != null) {
			URI importURI = URI.createURI(importString);
			return isValidUri(imp.eResource(), importURI);
		}
		return false;
	}

	public static boolean isValidImportLib(Import imp) {
		String importString = importToString(imp);
		if (importString != null) {
			URI importURI = URI.createURI(importString);
			return isValidLibUri(imp.eResource(), importURI);
		}
		return false;
	}

	private static boolean isValidUri(Resource base, URI importURI) {
		if (GlobalConstants.FILE_EXTENSION.equals(importURI.fileExtension())
				&& (importURI.isRelative() || importURI.isFile())) {
			URI location = resolveImportUri(base.getURI(), importURI);
			return base.getResourceSet().getURIConverter().exists(location, Collections.emptyMap());
		}
		return false;
	}

	private static boolean isValidLibUri(Resource base, URI importURI) {
		if (GlobalConstants.FILE_EXTENSION.equals(importURI.fileExtension()) && importURI.isRelative()) {
			List<String> segments = importURI.segmentsList();
			String firstSegment = segments.get(0);
			if (!segments.contains("..") && firstSegment != null && !firstSegment.startsWith("~")) {
				URI location = resolveImportLibUri(base, importURI);
				return location != null;
			}
		}
		return false;
	}

	public static URI resolveImportUri(URI baseURI, URI importURI) {
		URI location = importURI;
		if (location.isRelative()) {
			URI finalBaseURI;
			if (baseURI.isPlatform()) {
				IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
				// try creating platform resource
				URI resolved = importURI.resolve(baseURI);
				if (resolved.isPlatformResource()) {
					try {
						IFile basicFile = workspaceRoot.getFile(new Path(resolved.toPlatformString(true)));
						if (basicFile.exists()) {
							return resolved;
						}
					} catch (IllegalArgumentException e) {
						// do nothing, continue to make an absolute path
					}
				}
				// get base absolute path to resolve file outside workspace
				IPath rootPath = workspaceRoot.getLocation();
				IPath basePath = rootPath.append(baseURI.toPlatformString(true));
				finalBaseURI = URI.createFileURI(basePath.toString());
			} else {
				finalBaseURI = baseURI;
			}
			return importURI.resolve(finalBaseURI);
		} else {
			// absolute path no resolved needed
			return location;
		}
	}

	public static URI resolveImportLibUri(Resource base, URI importUri) {
		if (base.getURI().isPlatform() && !importUri.isEmpty()) {
			Path pPath = new Path(base.getURI().toPlatformString(true));
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(pPath.segment(0));

			List<String> includes = getIncludes(project);
			for (String include : includes) {
				IPath path = new Path(include).addTrailingSeparator();
				URI baseURI = (path.isAbsolute()) ? URI.createFileURI(path.toString())
						: URI.createPlatformResourceURI(path.toString(), true);
				URI resolved = resolveImportUri(baseURI, importUri);
				boolean inIncludePath = resolved.toString().startsWith(baseURI.toString());
				if (inIncludePath && base.getResourceSet().getURIConverter().exists(resolved, Collections.emptyMap())) {
					return resolved;
				}
			}
		}
		return null;
	}

	public static Resource resolveImport(Import imp, boolean importlib) {
		Resource resource = imp.eResource();
		String importString = importToString(imp);
		if (importString != null) {
			URI importURI = URI.createURI(importString);
			if ((!importlib && isValidUri(resource, importURI)) || (importlib && isValidLibUri(resource, importURI))) {
				return resolveImport(imp.eResource(), importURI, importlib);
			}
		}
		return null;
	}

	private static Resource resolveImport(Resource base, URI importURI, boolean importlib) {
		if (base != null) {
			Resource importedResource = null;
			try {
				URI resolvedURI = !importlib ? resolveImportUri(base.getURI(), importURI)
						: resolveImportLibUri(base, importURI);
				if (resolvedURI == null) {
					return null;
				}
				ResourceSet resourceSet = base.getResourceSet();
				if (resourceSet == null) {
					resourceSet = new ResourceSetImpl();
				}
				importedResource = resourceSet.getResource(resolvedURI, true);
			} catch (RuntimeException e) {
				return null;
			}
			return importedResource;
		}
		return null;
	}

	public static Poosl toPoosl(Resource resource) {
		if (resource != null) {
			TreeIterator<EObject> contents = resource.getAllContents();
			if (contents != null && contents.hasNext()) {
				EObject head = contents.next();
				if (head instanceof Poosl) {
					return (Poosl) head;
				}
			}
		}
		return null;
	}

	/**
	 * This function will try to read the preferencestore for the basic classes
	 * settings and return either the default value or the user supplied value. If
	 * the preferenceStore cannot be accessed (usually in case of running this
	 * function from commandline) the function will return the relative path to the
	 * basicClasses file inside the poosl2xml.jar
	 * 
	 * @return The basic classes path
	 */
	public static URI getBasicClassesURI() {
		String basicClassesLocation = BASIC_CLASSES_PATH;
		IPreferencesService preferencesService = Platform.getPreferencesService();
		if (preferencesService != null && !useDefaultBasicclasses()) {
			String path = preferencesService.getString(GlobalConstants.PREFERENCE_PLUGIN_ID,
					GlobalConstants.PREFERENCES_CUSTOM_BASIC_CLASS_PATH, BASIC_CLASSES_PATH, null);
			if (!path.startsWith("platform")) {
				return URI.createFileURI(path);
			}
			basicClassesLocation = path;
		}
		return URI.createURI(basicClassesLocation);
	}

	public static String getBasicAbsoluteString() {
		URI uri = getBasicClassesURI();
		if (uri.isPlatformResource()) {
			IPath rootPath = ResourcesPlugin.getWorkspace().getRoot().getLocation();
			IPath basePath = rootPath.append(uri.toPlatformString(true));
			uri = URI.createFileURI(basePath.toString());
		}
		return uri.toFileString();
	}

	public static boolean useDefaultBasicclasses() {
		IPreferencesService preferencesService = Platform.getPreferencesService();
		String useBasicClasses = preferencesService.getString(GlobalConstants.PREFERENCE_PLUGIN_ID,
				GlobalConstants.PREFERENCES_USE_DEFAULT_BASIC_CLASS, GlobalConstants.PREFERENCES_BASIC_CLASSES_DEFAULT,
				null);
		return useBasicClasses.equals(GlobalConstants.PREFERENCES_BASIC_CLASSES_DEFAULT);
	}

	public static List<Resource> computeAllDependencies(Resource resource) {
		List<Resource> dependencies = new ArrayList<>();
		try {
			Resource basicResource = resolveImport(resource, getBasicClassesURI(), false);
			if (basicResource != null) {
				dependencies.add(basicResource);
			}
			dependencies.add(basicResource);
		} catch (Exception e) {
			Logger.getGlobal().log(Level.WARNING, "Runtime Exception when computing all dependencies import", e);
		}
		computeAllDependencies(resource, dependencies);
		return dependencies;
	}

	private static void computeAllDependencies(Resource resource, List<Resource> dependencies) {
		if (resource != null && !dependencies.contains(resource)) {
			Poosl poosl = toPoosl(resource);
			if (poosl != null) {
				dependencies.add(resource);
				for (Import pImport : poosl.getImports()) {
					computeAllDependencies(resolveImport(pImport, false), dependencies);
				}
				for (Import pImport : poosl.getImportLibs()) {
					computeAllDependencies(resolveImport(pImport, true), dependencies);
				}
			}
		}
	}

	public static List<String> getIncludes(IProject project) {
		List<String> includes = new ArrayList<>();
		if (project != null) {
			IPreferencesService service = Platform.getPreferencesService();
			Preferences[] prefs = new Preferences[] { new ProjectScope(project).getNode("nl.esi.poosl.xtext.Poosl") };
			String version = service.get(GlobalConstants.PREFERENCES_INCLUDE_VERSION, "0", prefs);
			String key = getIncludeKey(Integer.parseInt(version), project);
			int i = 0;
			String result = service.get(key + i, null, prefs);

			while (result != null && !result.isEmpty()) {
				includes.add(result);
				result = service.get(key + ++i, null, prefs);
			}
		}
		return includes;
	}

	public static String getIncludeKey(Integer version, IProject project) {
		return version > 0 ? GlobalConstants.PREFERENCES_INCLUDE_KEY : project.getName() + ".include";
	}

	public static String importToString(Import pImport) {
		if (pImport != null) {
			String importString = pImport.getImportURI();
			if (importString != null && importString.length() > 2) {
				importString = importString.substring(1, importString.length() - 1);
				return unescapeString(importString);
			}
		}
		return null;
	}

	private static String unescapeString(String importString) {
		int escape = importString.indexOf('\\'); // equal to string.contains \\
		if (escape == -1) {
			return importString;
		} else {
			// add all following char to byte array in case of combined hex
			// sequence,
			// example (on linux): \xe2\x92\xac = euro sign
			byte[] bytes = new byte[importString.length() - escape];
			int bIndex = 0;

			for (int index = escape; index < importString.length(); index++) {
				char c = importString.charAt(index);
				if (c == '\\') {
					index++;
					if (index >= importString.length()) {
						// ended with an escape
						break;
					}
					switch (importString.charAt(index)) {
					case 'n':
						bytes[bIndex++] = '\n';

						break;
					case 't':
						bytes[bIndex++] = '\t';
						break;
					case 'v':
						// bytes[bIndex++] = '\v';
						break;
					case 'b':
						bytes[bIndex++] = '\b';
						break;
					case 'r':
						bytes[bIndex++] = '\r';
						break;
					case 'f':
						bytes[bIndex++] = '\f';
						break;
					case 'a':
						// bytes[bIndex++] = '\a';
						break;
					case '?':
						bytes[bIndex++] = '?';
						break;
					case '\'':
						bytes[bIndex++] = '\'';
						break;
					case '"':
						bytes[bIndex++] = '"';
						break;
					case '\\':
						bytes[bIndex++] = '\\';
						break;
					case '0':
						bytes[bIndex++] = '\0';
						break;
					case 'x':
						if (index + 1 < importString.length()) {
							int length = 0;
							char x = importString.charAt(index + 1);
							String hex = "";
							while (length < 2 && isHexDigit(x)) {
								hex += x;
								length++;
								if (length < 2 && index + length + 1 != importString.length()) {
									x = importString.charAt(index + length + 1);
								}
							}
							bytes[bIndex++] = (byte) Integer.parseInt(hex, 16);
							index += length;
						}
						break;
					default:
						Logger.getGlobal().log(Level.WARNING, "Unsupported escape sequence on character " + index + " "
								+ "of string " + importString + ".");
						break;
					}
				} else {
					bytes[bIndex++] = (byte) c;
				}
			}
			return importString.substring(0, escape) + new String(bytes).trim();
		}
	}

	private static boolean isHexDigit(char c) {
		if (Character.isDigit(c)) {
			return true;
		} else {
			for (int i = 0; i < HEX_CHARS.length; i++) {
				if (c == HEX_CHARS[i]) {
					return true;
				}
			}
			return false;
		}
	}

	private static final char[] HEX_CHARS = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'A', 'B', 'C', 'D', 'E', 'F' };
}
