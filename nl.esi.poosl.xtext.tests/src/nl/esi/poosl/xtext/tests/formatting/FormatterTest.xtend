package nl.esi.poosl.xtext.tests.formatting

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.formatter.FormatterTestHelper
import org.junit.Test
import org.junit.runner.RunWith
import nl.esi.poosl.xtext.tests.PooslInjectorProvider 

@RunWith(XtextRunner)
@InjectWith(PooslInjectorProvider) 
class FormatterTest {

	@Inject extension FormatterTestHelper

	@Test def void compoundInterface() {
		
				assertFormatted[
			toBeFormatted = '''
			'''
			
			]

//		assertFormatted[
//			toBeFormatted = '''
//				import "CameraSpec.sm"
//				
//				Project Camera {
//				
//					Compound Interfaces {
//						ICamera {
//							version
//							"1.0"
//				
//							description
//							"Demonstrator for documentation generation functionality with ComMA."
//				
//							interfaces // Describe the interfaces that you want to be included
//							ICamera type SSCF // Leave SSCF type 
//							IPower type SSCF
//						}
//					}
//				}
//			'''
//		]
	}


}
