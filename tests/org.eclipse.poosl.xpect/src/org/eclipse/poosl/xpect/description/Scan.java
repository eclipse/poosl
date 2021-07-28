package org.eclipse.poosl.xpect.description;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.xpect.setup.ThisRootTestClass;
import org.xpect.setup.XpectSetupComponent;
import org.xpect.util.IBundleInfo;
import org.xpect.util.IBundleInfo.Context;
import org.xpect.xtext.lib.setup.FileSetupContext;
import org.xpect.xtext.lib.setup.workspace.IResourceFactory;
import org.xpect.xtext.lib.setup.workspace.Workspace.Instance;
import org.xpect.xtext.lib.util.IFileUtil;
import org.xpect.xtext.lib.util.URIUtil;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * Resources factory that scan sources to import them in IContainer.
 * 
 * @author Obeo
 */
@XpectSetupComponent
public class Scan implements IResourceFactory<IResource, IContainer> {

    private final static String EXTENSION_TOKEN = ".";

    /** Ignored extension when importing a resource */
    private List<String> ignoredExtensions = new ArrayList<>();
    {
        // See built-in extension at
        // org.xpect.runner.XpectTestFiles $Builder#fileExtensions()
        ignoredExtensions.add(toExtension(".xt"));
        ignoredExtensions.add(toExtension(".java"));
        ignoredExtensions.add(toExtension(".class"));
    }

    /** Extension to import: all when empty */
    private List<String> extensions = new ArrayList<String>();

    /** Relative path to scan from current file */
    private String from;

    /**
     * Scan all files without ignored extensions.
     */
    public Scan() {
        this(null, null); // any file from context
    }

    /**
     * Constructor.
     * 
     * @param from
     *            path to start to import.
     * @param extension
     *            to import (all if null).
     */
    public Scan(String from, String extension) {
        setFrom(from);
        if (extension != null) {
            addExtension(extension);
        }
    }

    /**
     * Add an extension to ignore.
     * <p>
     * Use in Scan as: ignoredExtension='properties'
     * </p>
     * 
     * @param value
     *            of extension
     */
    public void addIgnoredExtension(String value) {
        ignoredExtensions.add(toExtension(value));
    }

    /**
     * Add an extension to import.
     * <p>
     * Use in Scan as: extension='mylang'
     * </p>
     * <p>
     * Once an extension is defined, others are ignored unless declared explicitly.
     * </p>
     * 
     * @param value
     *            of extension
     */
    public void addExtension(String value) {
        extensions.add(toExtension(value));
    }

    private static String toExtension(String value) {
        return !value.startsWith(EXTENSION_TOKEN) //
                ? EXTENSION_TOKEN + value
                : value;
    }

    /**
     * Sets the relative path to current file.
     * <p>
     * When not set file folder is used
     * </p>
     * 
     * @param path
     *            to scan
     */
    public void setFrom(String path) {
        this.from = path;
    }

    @Override
    public IResource create(FileSetupContext ctx, IContainer container, Instance instance) throws IOException, CoreException {
        URI thisUri = ctx.getXpectFileURI();
        // ctx.getXpectFile()(expectedType, annotations)
        if (!thisUri.isFile()) {
            throw new IllegalStateException("Context not supported: " + thisUri);
        }
        Path thisPath = Path.of(thisUri.toFileString());
        Path src = thisPath.getParent();
        if (from != null && !from.isEmpty()) {
            src = src.resolve(from);
        }

        Class<?> testContext = ctx.get(Class.class, ThisRootTestClass.class);
        IBundleInfo bundleInfo = IBundleInfo.Registry.INSTANCE.getBundle(testContext);
        Path testPath = Path.of(ctx.getXpectFileURI().deresolve(bundleInfo.getRootURI()).path()).getParent();

        // testPath will include src/ or bin/ segment, so context is ROOT
        List<URI> children = bundleInfo.find(Context.ROOT, testPath.toString(), //
                getFilenameMatcher(), //
                extensions.toArray(new String[extensions.size()]));

        // TODO Use
        children = bundleInfo.find(Context.ROOT, "src\\org\\eclipse\\poosl\\xpect\\validation\\importing", getFilenameMatcher());

        // IBundleInfo bundleInfo = IBundleInfo.Registry.INSTANCE.getBundle(ctx.getXpectFileURI());
        // bundleInfo.find(context, path, matcher, fileExtensions)

        for (Path child : Files.list(src) // To improve: option for recursive
                .filter(it -> !thisPath.equals(it) // skip test resource, included by default
                        && isApplicable(it))
                .collect(Collectors.toList())) {
            // IOException inhibits 'forEach' usage
            IFileUtil.create(container, //
                    URIUtil.createLocalURI(src.relativize(child).toString()), //
                    Files.newInputStream(child));
        }

        return null; // in fact, it is not used ??
    }

    private Predicate<String> getFilenameMatcher() {
        return extensions.isEmpty() //
                ? name -> !containsExtension(name, ignoredExtensions)
                : Predicates.alwaysTrue();
    }

    private boolean isApplicable(Path file) {
        String filename = file.getFileName().toString();

        return (extensions.isEmpty() //
                ? !containsExtension(filename, ignoredExtensions)
                : containsExtension(filename, extensions)) //
                && !Files.isDirectory(file);
    }

    private static boolean containsExtension(String filename, List<String> knownExtensions) {
        return knownExtensions.stream().anyMatch(it -> filename.endsWith(it));
    }

}
