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
class DataFormatTest {

	@Inject extension FormatterTestHelper

	@Test 
	def void methodBody() {

		assertFormatted[
			toBeFormatted='''
			data    class    someClass   extends Object  variables    methods someMethod() : Object 
			 |  |  if  true  then	nil  fi ;  switch nil  do  case  42  then  nil  default  nil
			 	od ;  while  true  do  nil   od; 1 + 1;  new ( Object ) ;  self   someMethod()   ;  
			 	  return    nil'''
			
			expectation ='''
			data class someClass extends Object
			variables
			
			methods
				someMethod() : Object | |
					if true then
						nil
					fi;
					switch nil do
						case 42 then
							nil
						default
							nil
					od;
					while true do
						nil
					od;
					1 + 1;
					new( Object );
					self someMethod();
					return nil'''			
		]
	}
	
	@Test 
	def void methodVariables() {

		assertFormatted[
			toBeFormatted='''
			data    class    someClass   extends Object  variables    methods    
			someMethod(a : Integer, c,d,e : String) : Object     |     f : Integer  
			 ,    g   ,   h   ,   i: String|      return    nil'''
			
			expectation ='''
			data class someClass extends Object
			variables
			
			methods
				someMethod(a : Integer, c, d, e : String) : Object | f : Integer, g, h, i : String |
					return nil'''			
		]
	}
	
	@Test 
	def void methodVarComments() {

		assertFormatted[
			toBeFormatted='''
			data    class    someClass   extends Object  variables    methods    
			someMethod(/*Comment line*/a : Integer, c,d,e : String) :    Object     |   f   
			  : Integer   ,    g   ,   h   ,   i: String|      return    nil'''
			
			expectation ='''
			data class someClass extends Object
			variables
			
			methods
				someMethod(
						/*Comment line*/
						a : Integer,
						c, d, e : String) : Object | f : Integer, g, h, i : String |
					return nil'''			
		]
	}

	@Test 
	def void methodVarMulti() {

		assertFormatted[
			toBeFormatted='''
			data    class    someClass   extends Object  variables    methods  
						  someMethod(a : Integer, c,d,e : String) :    Object     |    f  
			  : Integer   ,    g   ,   h   ,   i: String|      return    nil'''
			
			expectation ='''
			data class someClass extends Object
			variables
			
			methods
				someMethod(a : Integer, c, d, e : String) : Object | f : Integer, g, h, i : String |
					return nil'''			
		]
	}
	
	@Test 
	def void variables() {

		assertFormatted[
			toBeFormatted='''
			data 	 class 	someClass				 extends	 Object 	
			variables 	a    :    Integer,   b   :   String    c :   String   d   :   Integer 	
			 c  :  Integer  methods'''
			
			expectation ='''
			data class someClass extends Object
			variables
				a : Integer,
				b : String
				c : String
				d : Integer
				c : Integer
			methods'''			
		]
	}

}
