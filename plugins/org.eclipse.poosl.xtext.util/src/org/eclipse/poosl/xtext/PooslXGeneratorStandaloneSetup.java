package org.eclipse.poosl.xtext;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.emf.mwe.utils.StandaloneSetup;
import org.eclipse.xtext.xtext.generator.XtextGeneratorStandaloneSetup;

import com.google.inject.Injector;


/**
 * Setup the path for generation project.
 * <p>
 * Xtext uses hard-coded pattern to detect '.project' descriptor. 
 * <br>Unfortunately, this choice drives at lot of </br>
 * </p>
 * @author Obeo
 */
public class PooslXGeneratorStandaloneSetup extends XtextGeneratorStandaloneSetup {

    /** Path matching .classpath content */
    private static final String[] WELL_KNOWN_CLASSPATH = {
            "target",  // NON-NLS-1 //$NON-NLS-1$
            "jdt-classes",  // NON-NLS-1 //$NON-NLS-1$
    };
    
    /** Eclipse project descriptor */ 
    private static final String PROJECT_FILENAME = ".project"; // NON-NLS-1 //$NON-NLS-1$

    private Logger log = Logger.getLogger(getClass());

    @Override
    public void initialize(Injector injector) {
        super.initialize(injector);
        new StandaloneSetup() {
            @Override
            protected File findProjectFileForPossibleClassesFolder(File f) {
                return findProjectFile(f);
            };
        }.setScanClassPath(isScanClasspath());
    }
    

    
    protected File findProjectFile(File f) {
        File parentFile = f.getParentFile();
        if (parentFile == null ||
                !f.getName().equals(WELL_KNOWN_CLASSPATH[1])) {
            return null;
        }
        File grandParentFile = parentFile.getParentFile();
        if (grandParentFile == null || 
                !parentFile.getName().equals(WELL_KNOWN_CLASSPATH[0])
                ) {
            return null;
        }
        File projectFile = new File(grandParentFile, PROJECT_FILENAME);
        if (projectFile.exists()) {
            log.warn("Fix Project path for " + grandParentFile.getName()); // NON-NLS-1 only in build
            return projectFile;
        }
        return null;
    }
    
    
}
