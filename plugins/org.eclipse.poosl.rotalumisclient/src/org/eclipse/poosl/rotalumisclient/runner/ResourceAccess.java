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
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.poosl.rotalumisclient.runner.IBundleInfo.Context;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

/**
 * The ResourceAccess.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class ResourceAccess {

    private ResourceAccess() {
        throw new IllegalStateException("Utility class");
    }

    public static class ArchivedResourceInfo extends ResourceInfo {
        private static final Logger LOG = Logger.getLogger(ArchivedResourceInfo.class.getName());

        public ArchivedResourceInfo(URI location) {
            super(location);
        }

        @Override
        public List<URI> find(Context context) {
            return Collections.singletonList(location);
        }

        @Override
        public URI find(Context context, String fileName) {
            return resolve(fileName);
        }

        @Override
        public List<URI> find(Context context, String path, Predicate<String> matcher, String... fileExtensions) {
            String archiveFileName = URI.createURI(location.authority().substring(0, location.authority().length() - 1)).toFileString();
            List<URI> result = Lists.newArrayList();
            try {
                JarFile jarFile = new JarFile(new File(archiveFileName));
                try {
                    Enumeration<JarEntry> entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry next = entries.nextElement();
                        String name = next.getName();
                        if (!path.isEmpty() && !name.startsWith(path))
                            continue;
                        if (matches(name, fileExtensions) && matcher.apply(name)) {
                            URI resolved = URI.createURI(name).resolve(location);
                            result.add(resolved);
                        }
                    }
                } finally {
                    if (jarFile != null)
                        jarFile.close();
                }
            } catch (IOException e) {
                LOG.log(Level.WARNING, e.getMessage());
            }
            return result;
        }

        @Override
        public String toString() {
            return prettyPrint(this.getClass().getSimpleName(), location.toString());
        }

    }

    public static class GeneralProjectInfo extends ResourceInfo {

        public GeneralProjectInfo(URI location) {
            super(location);
        }

        @Override
        public List<URI> find(Context context) {
            return Collections.singletonList(location);
        }

        @Override
        public URI find(Context context, String fileName) {
            return resolve(fileName);
        }

        @Override
        public List<URI> find(Context context, String path, Predicate<String> matcher, String... fileExtensions) {
            File root = new File(location.toFileString());
            List<URI> result = Lists.newArrayList();
            collect(root, root, result, matcher, fileExtensions);
            return result;
        }

        @Override
        public String toString() {
            return prettyPrint(this.getClass().getSimpleName(), location.toString());
        }

    }

    public static class JavaProjectInfo extends ResourceInfo {
        private final String output;

        private final List<String> sourceFolders;

        public JavaProjectInfo(URI location, List<String> sourceFolders, String output) {
            super(location);
            this.sourceFolders = sourceFolders;
            this.output = output;
        }

        @Override
        public List<URI> find(Context context) {
            List<URI> res = null;
            switch (context) {
            case CLASSPATH:
                res = Collections.singletonList(resolve(output));
                break;
            case ROOT:
                res = Collections.singletonList(location);
                break;
            case SOURCE:
                List<URI> result = Lists.newArrayList();
                for (String src : sourceFolders)
                    result.add(resolve(src));
                res = result;
                break;
            default:
                res = Collections.emptyList();
            }
            return res;
        }

        @Override
        public URI find(Context context, String fileName) {
            for (URI uri : find(context)) {
                File parent = new File(uri.toFileString());
                if (new File(parent, fileName).exists())
                    return URI.createURI(fileName).resolve(uri.hasTrailingPathSeparator() ? uri : uri.appendSegment("")); //$NON-NLS-1$
            }
            return null;
        }

        @Override
        public List<URI> find(Context context, String path, Predicate<String> matcher, String... fileExtensions) {
            List<URI> result = Lists.newArrayList();
            for (URI uri : find(context)) {
                File folder = new File(new File(uri.toFileString()), path);
                if (folder.isDirectory()) {
                    collect(folder, folder, result, matcher, fileExtensions);
                }
            }
            return result;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
            builder.append(",location=" + location.toString()); //$NON-NLS-1$
            builder.append(",classpath=" + output); //$NON-NLS-1$
            builder.append(",source=" + sourceFolders); //$NON-NLS-1$
            return builder.toString();
        }

    }

    public abstract static class ResourceInfo {
        /**
         * The resource location.
         */
        protected final URI location;

        public ResourceInfo(URI location) {
            super();
            this.location = location;
        }

        protected void collect(File root, File file, List<URI> result, Predicate<String> matcher, String... fileExtensions) {
            if (file.isFile()) {
                if (matches(file.getName(), fileExtensions)) {
                    URI uri = URI.createFileURI(file.getAbsolutePath());
                    URI name = uri.deresolve(URI.createURI(root.toString()));
                    if (matcher.apply(name.toString())) {
                        result.add(uri);
                    }
                }
            } else if (file.isDirectory()) {
                for (File child : file.listFiles())
                    collect(root, child, result, matcher, fileExtensions);
            }
        }

        public abstract List<URI> find(Context context);

        /**
         * Provides a URI for provided filename.
         * <p>
         * Depending to the implementation, uri maybe null if resource does not exit but there is no warranty.
         * </p>
         * 
         * @param context
         *            to search in
         * @param fileName
         *            of resource
         * @return URI or null if path is
         */
        public abstract URI find(Context context, String fileName);

        public abstract List<URI> find(Context context, String path, Predicate<String> matcher, String... fileExtensions);

        public URI getLocation() {
            return location;
        }

        protected boolean matches(String name, String... fileExtensions) {
            if (fileExtensions.length == 0)
                return true;
            int index = name.lastIndexOf('.');
            if (index < 0)
                return false;
            String ext = name.substring(index + 1, name.length());
            for (int i = 0; i < fileExtensions.length; i++)
                if (ext.equals(fileExtensions[i]))
                    return true;
            return false;
        }

        protected URI resolve(String fileName) {
            return URI.createURI(fileName).resolve(location);
        }

    }

    public static ResourceInfo create(URI uri) {
        if (uri.isArchive())
            return new ArchivedResourceInfo(uri);
        else {
            File dot = new File(uri.toFileString() + ".classpath"); //$NON-NLS-1$
            if (dot.isFile()) {
                DotClasspath dotClasspath = new DotClasspath(dot);
                return new JavaProjectInfo(uri, dotClasspath.getSources(), dotClasspath.getOutput());
            }
            return new GeneralProjectInfo(uri);
        }
    }

    private static String prettyPrint(String className, String location) {
        return className + ",location=" + location; //$NON-NLS-1$
    }
}
