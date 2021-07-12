package org.eclipse.poosl.xtext;

import javax.inject.Inject;

import org.eclipse.xtext.xtext.generator.DefaultGeneratorModule;
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming;
import org.eclipse.xtext.xtext.generator.model.TypeReference;
import org.eclipse.xtext.xtext.generator.model.project.IXtextProjectConfig;

/**
 * Generator module with Poosl naming convention.
 * <p>
 * By default, generator uses name based on plugin name.
 * <br>
 * Poosl project forces the name to 'PooslActivator'.
 * </br>
 * </p>
 * 
 * 
 * @author Obeo
 */
@SuppressWarnings("restriction")
public class PooslGeneratorModule extends DefaultGeneratorModule {

    public Class<? extends XtextGeneratorNaming> bindNaming() {
        return PooslGeneratorNaming.class;
    }

    public static class PooslGeneratorNaming extends XtextGeneratorNaming {

        @Inject
        IXtextProjectConfig projectConfig;


        @Override
        public TypeReference getEclipsePluginActivator() {
            String pluginName = projectConfig.getEclipsePlugin().getName();
            return new TypeReference(pluginName + ".internal", "PooslActivator");
        }
    }
}
