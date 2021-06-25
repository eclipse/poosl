package nl.esi.poosl.xtext;

import org.eclipse.xtext.linking.ILinkingDiagnosticMessageProvider;
import org.eclipse.xtext.parser.antlr.ISyntaxErrorMessageProvider;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;

import nl.esi.poosl.xtext.importing.PooslResourceDescriptionStrategy;

public class PooslRuntimeModule extends nl.esi.poosl.xtext.AbstractPooslRuntimeModule {
	public Class<? extends org.eclipse.xtext.resource.IResourceDescription.Manager> bindIResourceDescription$Manager() {
		return nl.esi.poosl.xtext.importing.PooslResourceDescriptionManager.class;
	}

	@Override
	public Class<? extends org.eclipse.xtext.scoping.IGlobalScopeProvider> bindIGlobalScopeProvider() {
		return nl.esi.poosl.xtext.importing.PooslImportUriGlobalScopeProvider.class;
	}

	public Class<? extends ILinkingDiagnosticMessageProvider> bindILinkingDiagnosticMessageProvider() {
		return nl.esi.poosl.xtext.validation.PooslLinkingMessageProvider.class;
	}

	public Class<? extends ISyntaxErrorMessageProvider> bindISyntaxErrorMessageProvider() {
		return nl.esi.poosl.xtext.validation.PooslSyntaxMessageProvider.class;
	}

	@Override
	public Class<? extends org.eclipse.xtext.naming.IQualifiedNameProvider> bindIQualifiedNameProvider() {
		return nl.esi.poosl.xtext.custom.PooslNameProvider.class;
	}

	public Class<? extends IDefaultResourceDescriptionStrategy> bindIDefaultResourceDescriptionStrategy() {
		return PooslResourceDescriptionStrategy.class;
	}

}
