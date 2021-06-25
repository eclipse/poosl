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
