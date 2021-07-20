package org.eclipse.poosl.xtext;

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

    @Override
    public void initialize(Injector injector) {
        super.initialize(injector);
        new LocalStandaloneSetup(false).setScanClassPath(isScanClasspath());
    }
    
}
