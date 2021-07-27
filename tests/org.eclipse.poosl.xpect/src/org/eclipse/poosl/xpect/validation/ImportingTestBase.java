package org.eclipse.poosl.xpect.validation;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.poosl.xtext.GlobalConstants;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.junit.BeforeClass;
import org.xpect.xtext.lib.tests.ValidationTest;

/**
 * Base class to install poosl resources used in tests.
 * 
 * @author Obeo
 */
public class ImportingTestBase extends ValidationTest {
    

    private static List<String> IMPORT_FILENAMES = 
            Stream.concat(
                // poosl files
                Stream.of(
                    "import",  //$NON-NLS-1$
                    "import2",  //$NON-NLS-1$
                    "import3",  //$NON-NLS-1$
                    "import31",  //$NON-NLS-1$
                    "importInit",  //$NON-NLS-1$
                    "ImportInstanceParameter",  //$NON-NLS-1$
                    "importMethods",  //$NON-NLS-1$
                    "importProcessTwice",  //$NON-NLS-1$
                    "importProcessTwice2",  //$NON-NLS-1$
                    "importSuperUnused",  //$NON-NLS-1$
                    "importSuperUnused2",  //$NON-NLS-1$
                    "importUniquePortMessage", //$NON-NLS-1$
                    "ProcessMethodUsedChild",  //$NON-NLS-1$
                    "ProcessMethodUsedParent" //$NON-NLS-1$
                ).map(it -> it  + "." + GlobalConstants.FILE_EXTENSION),
                    // other files
                Stream.of("notpoosl.java")  //$NON-NLS-1$
            ).map(it -> "imports/" + it)  //$NON-NLS-1$
            .collect(Collectors.toList());
    
    /**
     * Copies all files from "import" package to "import" folder of workspace.
     * <p>
     * Reserved to JUnit runner.
     * </p>
     */
    @BeforeClass
    public static void setupImport() throws Exception, InterruptedException {
        IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
        IPath wsRootPath = wsRoot.getLocation();
        
        setupResources(ImportingTestBase.class, IMPORT_FILENAMES, wsRootPath.toFile().toPath());
    }

    public static void setupResources(Class<?> anchor, List<String> resources, Path rootTarget) throws Exception, InterruptedException {
        System.err.println("Setup " + resources.size() + " Resources to " + rootTarget);
        
        WorkspaceModifyOperation setup = new WorkspaceModifyOperation() {
            
            @Override
            protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
                try {
                    IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
                    IPath wsRootPath = wsRoot.getLocation();
    
                    System.err.println("In ws: " + wsRootPath.toFile());
                    
                    // mkdir
                    // Path targetFolder = wsRootPath.toFile().toPath().resolve(IMPORT_PATH);
                    Files.createDirectories(rootTarget);
                    
                    // copy all
                    for (String resource : resources) {
                        Path target = rootTarget.resolve(resource);
                        Files.createDirectories(target.getParent());
                        try (InputStream in = anchor.getResourceAsStream(resource)) {
                            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
                            System.err.println("Copy " + target);
                        }
                    }
                } catch (IOException fail) {
                    throw new InvocationTargetException(fail);
                }
            }
        };
        try {
            setup.run(new NullProgressMonitor());
        } catch (InvocationTargetException fail) {
            unwrapCause(fail);
        }
    }
    
    private static void unwrapCause(InvocationTargetException fail) throws Exception {
        Throwable cause = fail.getCause();
        if (cause instanceof RuntimeException) {
            throw (RuntimeException) cause;
        } else if (cause instanceof Error) {
            throw (Error) cause;
        } else {
            // There is no other case.
            throw (Exception) cause;
        }
    }
    
}
