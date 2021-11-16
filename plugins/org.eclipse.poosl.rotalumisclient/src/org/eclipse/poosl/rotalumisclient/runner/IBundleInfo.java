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
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.poosl.rotalumisclient.runner.ResourceAccess.ResourceInfo;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;

/**
 * The IBundleInfo.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public interface IBundleInfo {

    class BundleInfo implements IBundleInfo {
        private ResourceInfo info;

        private final URI location;

        private final String symbolicName;

        public BundleInfo(String symbolicName, URI locationURI) {
            super();
            this.symbolicName = symbolicName;
            this.location = locationURI;
        }

        private ResourceInfo getInfo() {
            if (info == null)
                info = ResourceAccess.create(location);
            return info;
        }

        @Override
        public List<URI> find(Context context) {
            return getInfo().find(context);
        }

        @Override
        public URI find(Context context, String fileName) {
            return getInfo().find(context, fileName);
        }

        @Override
        public List<URI> find(
                Context context, String path, Predicate<String> matcher, String... fileExtensions) {
            return getInfo().find(context, path, matcher, fileExtensions);
        }

        @Override
        public URI getRootURI() {
            return getInfo().getLocation();
        }

        @Override
        public String getSymbolicName() {
            return symbolicName;
        }

        @Override
        public String toString() {
            return symbolicName + ": " + getInfo(); //$NON-NLS-1$
        }
    }

    enum Context {
        CLASSPATH, ROOT, SOURCE
    }

    class Delegate implements Registry {
        private IBundleInfo.Registry delegate;

        public Delegate() {
            super();
        }

        @Override
        public Collection<String> getAllBundleNames() {
            return delegate.getAllBundleNames();
        }

        @Override
        public IBundleInfo getBundle(Class<?> clazz) {
            return delegate.getBundle(clazz);
        }

        @Override
        public IBundleInfo getBundle(String symbolicName) {
            return delegate.getBundle(symbolicName);
        }

        public IBundleInfo.Registry getDelegate() {
            return delegate;
        }

        public void setDelegate(IBundleInfo.Registry delegate) {
            this.delegate = delegate;
        }

        @Override
        public IBundleInfo getBundle(URI uri) {
            return delegate.getBundle(uri);
        }
    }

    public interface Registry {
        Registry INSTANCE = EcorePlugin.IS_ECLIPSE_RUNNING
            ? new Delegate() : new StandaloneBundleRegistry();

        Collection<String> getAllBundleNames();

        IBundleInfo getBundle(Class<?> clazz);

        IBundleInfo getBundle(URI uri);

        IBundleInfo getBundle(String symbolicName);
    }

    class StandaloneBundleRegistry implements IBundleInfo.Registry {
        public static final Logger LOG = Logger.getLogger(StandaloneBundleRegistry.class.getName());

        private final Map<URI, IBundleInfo> locationToBundle;

        private final Map<String, IBundleInfo> symbolicNameToBundle;

        public StandaloneBundleRegistry() {
            symbolicNameToBundle = Maps.newLinkedHashMap();
            locationToBundle = Maps.newLinkedHashMap();
            for (URL url : ClasspathUtil.findResources("META-INF/MANIFEST.MF")) { //$NON-NLS-1$
                String name;
                try {
                    name = ClasspathUtil.getSymbolicName(url);
                    if (name != null) {
                        URI location = URI.createURI(url.toString()).trimSegments(2)
                                .appendSegment(""); //$NON-NLS-1$
                        BundleInfo info = createBundleInfo(name, location);
                        symbolicNameToBundle.put(name, info);
                        locationToBundle.put(location, info);
                    }
                } catch (Exception e) {
                    LOG.log(Level.WARNING, "can't open " + url, e);
                }
            }
        }

        private BundleInfo createBundleInfo(String name, URI location) {
            return new BundleInfo(name, location);
        }

        @Override
        public Collection<String> getAllBundleNames() {
            return symbolicNameToBundle.keySet();
        }

        @Override
        public IBundleInfo getBundle(Class<?> clazz) {
            URI bundleURI = getBundleURI(clazz);
            IBundleInfo info = locationToBundle.get(bundleURI);
            if (info != null)
                return info;
            info = createBundleInfo(null, bundleURI);
            locationToBundle.put(bundleURI, info);
            return info;
        }

        @Override
        public IBundleInfo getBundle(String symbolicName) {
            return symbolicNameToBundle.get(symbolicName);
        }

        private URI getBundleURI(Class<?> clazz) {
            String[] segments = clazz.getName().split("\\."); //$NON-NLS-1$
            String fileName = Joiner.on('/').join(segments) + ".class"; //$NON-NLS-1$
            URL resource = clazz.getClassLoader().getResource(fileName);
            if ("jar".equals(resource.getProtocol())) { //$NON-NLS-1$
                return URI.createURI(resource.toString()).trimSegments(segments.length)
                        .appendSegment(""); //$NON-NLS-1$
            } else {
                File classFile;
                try {
                    classFile = new File(resource.toURI());
                    File packageRootFolder = classFile;
                    for (int i = 0; i < segments.length; i++) {
                        packageRootFolder = packageRootFolder.getParentFile();
                        if (packageRootFolder == null)
                            throw new RuntimeException(
                                    "Could not determine package root for " + clazz);
                    }
                    File current = packageRootFolder;
                    while (current != null) {
                        if (new File(current, ".project").isFile()) //$NON-NLS-1$
                            return URI.createFileURI(current.toString()).appendSegment(""); //$NON-NLS-1$
                        current = current.getParentFile();
                    }
                    throw new RuntimeException(
                            "Could not find .project file in super-folder of " + packageRootFolder);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        @Override
        public String toString() {
            return Joiner.on("\n").join(symbolicNameToBundle.values()); //$NON-NLS-1$
        }

        @Override
        public IBundleInfo getBundle(URI uri) {
            return createBundleInfo(null, uri);
        }
    }

    List<URI> find(Context context);

    /**
     * Provides a URI for provided filename.
     * <p>
     * Depending to the implementation, uri maybe null if resource does not exit
     * but there is no warranty.
     * </p>
     * 
     * @param context
     *     to search in
     * @param fileName
     *     of resource
     * @return URI or null if path is
     */
    URI find(Context context, String fileName);

    List<URI> find(
            Context context, String path, Predicate<String> matcher, String... fileExtensions);

    URI getRootURI();

    String getSymbolicName();
}
