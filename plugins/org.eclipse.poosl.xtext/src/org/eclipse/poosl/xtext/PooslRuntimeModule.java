package org.eclipse.poosl.xtext;

import org.eclipse.poosl.xtext.importing.PooslResourceDescriptionStrategy;
import org.eclipse.xtext.linking.ILinkingDiagnosticMessageProvider;
import org.eclipse.xtext.parser.antlr.ISyntaxErrorMessageProvider;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;

//@CHECKSTYLE:OFF naming inherited from XText generation
public class PooslRuntimeModule extends org.eclipse.poosl.xtext.AbstractPooslRuntimeModule {
    public Class<? extends org.eclipse.xtext.resource.IResourceDescription.Manager> bindIResourceDescription$Manager() {
        return org.eclipse.poosl.xtext.importing.PooslResourceDescriptionManager.class;
    }

    @Override
    public Class<? extends org.eclipse.xtext.scoping.IGlobalScopeProvider> bindIGlobalScopeProvider() {
        return org.eclipse.poosl.xtext.importing.PooslImportUriGlobalScopeProvider.class;
    }

    public Class<? extends ILinkingDiagnosticMessageProvider> bindILinkingDiagnosticMessageProvider() {
        return org.eclipse.poosl.xtext.validation.PooslLinkingMessageProvider.class;
    }

    public Class<? extends ISyntaxErrorMessageProvider> bindISyntaxErrorMessageProvider() {
        return org.eclipse.poosl.xtext.validation.PooslSyntaxMessageProvider.class;
    }

    @Override
    public Class<? extends org.eclipse.xtext.naming.IQualifiedNameProvider> bindIQualifiedNameProvider() {
        return org.eclipse.poosl.xtext.custom.PooslNameProvider.class;
    }

    public Class<? extends IDefaultResourceDescriptionStrategy> bindIDefaultResourceDescriptionStrategy() {
        return PooslResourceDescriptionStrategy.class;
    }

}
