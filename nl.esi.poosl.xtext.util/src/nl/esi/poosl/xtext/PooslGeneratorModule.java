package nl.esi.poosl.xtext;

import javax.inject.Inject;

import org.eclipse.xtext.xtext.generator.DefaultGeneratorModule;
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming;
import org.eclipse.xtext.xtext.generator.model.TypeReference;
import org.eclipse.xtext.xtext.generator.model.project.IXtextProjectConfig;

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
