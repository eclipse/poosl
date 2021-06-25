package nl.esi.poosl.xpect;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import nl.esi.poosl.xpect.validation.channel.PooslValidationChannelTest;
import nl.esi.poosl.xpect.validation.importing.PooslValidationImportingTest;
import nl.esi.poosl.xpect.validation.misc.PooslValidationMiscTest;
import nl.esi.poosl.xpect.validation.suppress.PooslValidationSuppressTest;
import nl.esi.poosl.xpect.validation.typecheck.PooslValidationTypeCheckTest;
import nl.esi.poosl.xpect.validation.unique.PooslValidationUniqueTest;
import nl.esi.poosl.xpect.validation.unused.PooslValidationUnusedTest;

@SuiteClasses({ 
		//Disabled scoping and linking test, no longer uses default xtext reference
/*    *///PooslLinkingTest.class, // 
	    //PooslScopingTest.class,
		PooslValidationMiscTest.class, //
		PooslValidationUnusedTest.class, 
		PooslValidationUniqueTest.class,
		PooslValidationTypeCheckTest.class,
		PooslValidationChannelTest.class,
		PooslValidationImportingTest.class,
		PooslValidationSuppressTest.class		
})

@RunWith(Suite.class) 
public class AllPooslXpectTests { 

} 
