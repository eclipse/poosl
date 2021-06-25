package nl.esi.poosl.xpect.syntax;

import org.eclipse.emf.ecore.EObject
import org.junit.runner.RunWith
import org.xpect.XpectImport
import org.xpect.expectation.IStringExpectation
import org.xpect.expectation.StringExpectation
import org.xpect.runner.Xpect
import org.xpect.runner.XpectRunner
import org.xpect.xtext.lib.setup.ThisModel
import org.xpect.xtext.lib.setup.XtextStandaloneSetup
import org.xpect.xtext.lib.setup.XtextWorkspaceSetup
import org.xpect.xtext.lib.util.EObjectFormatter

@RunWith(typeof(XpectRunner)) 
@XpectImport(#[typeof(XtextStandaloneSetup), typeof(XtextWorkspaceSetup)])

class PooslSyntaxTest {

	@Xpect
	def void ast(@StringExpectation IStringExpectation expectation, @ThisModel EObject model) {
		val actual = new EObjectFormatter().resolveCrossReferences().format(model);
		expectation.assertEquals(actual);
	}

}  
