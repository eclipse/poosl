/*******************************************************************************
 * Copyright (c) 2021 TNO/ESI
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    TNO/ESI - initial API and implementation
 *    Obeo - refactoring
 *******************************************************************************/
package org.eclipse.poosl.xtext.tests.formatting

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.formatter.FormatterTestHelper
import org.junit.Test
import org.junit.runner.RunWith
import org.eclipse.poosl.xtext.tests.PooslInjectorProvider 

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
