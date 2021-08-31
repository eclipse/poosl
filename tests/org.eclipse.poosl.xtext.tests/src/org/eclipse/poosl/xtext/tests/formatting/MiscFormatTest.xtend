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
class MiscFormatTest {

	@Inject extension FormatterTestHelper

	@Test 
	def void commentsNewLine() {

		assertFormatted[
			toBeFormatted='''
			/* comment line */ process class   someClass() 
			/* comment line */ ports	
			messages /* comment line */	
			variables	/* comment line */
			init // comment \n 
				someMethod()()/* comment line */
			methods/* comment line */
				someMethod()() | |/* comment line */
					skip // comment'''
			
			expectation ='''
			/* comment line */
			process class someClass()
			/* comment line */
			ports
			
			messages
			
			/* comment line */
			variables
			
			/* comment line */
			init // comment \n 
				someMethod()()
			/* comment line */
			methods
				/* comment line */
				someMethod()() | |
					/* comment line */
					skip // comment'''			
		]
	}
	
	@Test 
	def void importsAndClasses() {

		assertFormatted[
			toBeFormatted='''
			import "import.poosl" import "import.poosl" 
			process class someClass()ports messages variables init someMethod()() 
			methods someMethod()() | | skip data class someClass extends Object variables methods system instances channels'''
			
			expectation ='''
			import "import.poosl"
			import "import.poosl"
			
			process class someClass()
			ports
			
			messages
			
			variables
			
			init
				someMethod()()
			methods
				someMethod()() | |
					skip
			
			data class someClass extends Object
			variables
			
			methods
			
			
			system
			instances
			
			channels'''			
		]
	}

}
