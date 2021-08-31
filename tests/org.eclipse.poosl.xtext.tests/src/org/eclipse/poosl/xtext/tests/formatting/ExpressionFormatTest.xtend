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
class ExpressionFormatTest {

	@Inject extension FormatterTestHelper

	@Test 
	def void elseIf() {

		assertFormatted[
			toBeFormatted='''
			data class someClass   extends Object   
				variables
				
				methods someMethod() : Object | |  if  true  then  nil	else  if  (  true )   
				then nil  else  if ( true )  then  nil  fi fi  fi;return   nil'''
			
			expectation ='''
			data class someClass extends Object
			variables
			
			methods
				someMethod() : Object | |
					if true then
						nil
					else if (true) then
						nil
					else if (true) then
						nil
					fi fi fi;
					return nil'''			
		]
	}
	
	@Test 
	def void eSwitch() {

		assertFormatted[
			toBeFormatted='''
			data class someClass   extends Object   
							variables
							
			methods   someMethod() : Object | |  	
				switch    (1    +   1) do		
					case  true
			 then		1   +   1		
			 
			 default			1   +   1 	od ; 
			  return  nil'''
			
			expectation ='''
			data class someClass extends Object
			variables
			
			methods
				someMethod() : Object | |
					switch (1 + 1) do
						case true then
							1 + 1
						default
							1 + 1
					od;
					return nil'''	
		]
	}
	
	@Test 
	def void eWhile() {

		assertFormatted[
			toBeFormatted='''
			data class someClass   extends Object   
										variables
										
			methods   someMethod() : Object | | while   ( 1  +  1  +3 )   do  	 1  +  1  +3  ; self   
			someMethod()		od   ; return nil			'''
			
			expectation ='''
			data class someClass extends Object
			variables
			
			methods
				someMethod() : Object | |
					while (1 + 1 + 3) do
						1 + 1 + 3;
						self someMethod()
					od;
					return nil			'''			
		]
	}

}
