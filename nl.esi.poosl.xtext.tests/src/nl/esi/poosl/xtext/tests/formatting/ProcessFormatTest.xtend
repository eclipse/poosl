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
class ProcessFormatTest {

	@Inject extension FormatterTestHelper

	@Test 
	def void init() {

		assertFormatted[
			toBeFormatted='''
			process class   someClass() 
			ports	
			messages	
			variables	
			init 
			        	someMethod   (    )    (   )    
			methods
				someMethod()() | |
					skip'''
			
			expectation ='''
			process class someClass()
			ports
			
			messages
			
			variables
			
			init
				someMethod()()
			methods
				someMethod()() | |
					skip'''			
		]
	}
	
	@Test 
	def void methodBody() {

		assertFormatted[
			toBeFormatted='''
			process class   someClass() 
			ports	
			messages	
			variables	
			init 
				someMethod()()
			methods
					someMethod()() | |	abort		skip	with (		skip;		skip	 ) ;  
					delay   1 ;	interrupt		skip	with (		skip ;		skip	);	new (Object) ;		skip'''
			
			expectation ='''
			process class someClass()
			ports
			
			messages
			
			variables
			
			init
				someMethod()()
			methods
				someMethod()() | |
					abort
						skip
					with (
						skip;
						skip
					);
					delay 1;
					interrupt
						skip
					with (
						skip;
						skip
					);
					new(Object);
					skip'''			
		]
	}
	
	@Test 
	def void methodVariables() {

		assertFormatted[
			toBeFormatted='''
			process class   someClass() 
			ports	
			messages	
			variables 
			init 
				someMethod()()
			methods someMethod()() skip
				someMethod(a : Integer   , b ,  c  ,  d   :   String)(z   :  Integer,  x,s  :  Integer) | r : Integer, t   ,   y   , u  :    Integer |
					skip'''
			
			expectation ='''
			process class someClass()
			ports
			
			messages
			
			variables
			
			init
				someMethod()()
			methods
				someMethod()()
					skip
			
				someMethod(a : Integer, b, c, d : String)(z : Integer, x, s : Integer) | r : Integer, t, y, u : Integer |
					skip'''			
		]
	}
	
	@Test 
	def void methodVarMulti() {

		assertFormatted[
			toBeFormatted='''
			process class   someClass() 
			ports	
			messages	
			variables 
			init 
				someMethod()()
			methods someMethod()() skip
				someMethod( 
					a : Integer   , b ,  c  ,  d   :   String)(
					 z   :  Integer,  x,s  :  Integer) |  
					 r : Integer, t   ,   y   , u  :    Integer |
					skip'''
			
			expectation ='''
			process class someClass()
			ports
			
			messages
			
			variables
			
			init
				someMethod()()
			methods
				someMethod()()
					skip
			
				someMethod(
						a : Integer,
						b, c, d : String)(
						z : Integer,
						x, s : Integer) |
						r : Integer,
						t, y, u : Integer |
					skip'''			
		]
	}
	
	@Test 
	def void parameters() {

		assertFormatted[
			toBeFormatted='''
			 process class   someClass   (   a   :   String    , b ,  c,   d : Integer)  
			 ports	 
			 messages	 
			 variables	 
			 init 
			 	someMethod()() 
			 methods 
			 	someMethod()() | | 
			 		skip '''
			
			expectation ='''
			process class someClass(a : String, b, c, d : Integer)
			ports
			
			messages
			
			variables
			
			init
				someMethod()()
			methods
				someMethod()() | |
					skip '''			
		]
	}
	
	@Test 
	def void parametersMulti() {

		assertFormatted[
			toBeFormatted='''
			 process class   someClass(
			 	a    :   String      , b ,  c,   d : Integer)  
			 ports	 
			 messages	 
			 variables	 
			 init 
			 	someMethod()() 
			 methods 
			 	someMethod()() | | 
			 		skip '''
			
			expectation ='''
			process class someClass(
					a : String,
					b, c, d : Integer)
			ports
			
			messages
			
			variables
			
			init
				someMethod()()
			methods
				someMethod()() | |
					skip '''			
		]
	}
	
	@Test 
	def void variables() {

		assertFormatted[
			toBeFormatted='''
			 process class   someClass ()  
			 ports	 
			 messages	 
			 variables	 a  		 : 		   Integer  	, 		 b  	 :	   String    c  :    String   d 	:   String  
			 init  
			 	someMethod()() 
			 methods 
			 	someMethod()() | | 
			 		skip '''
			
			expectation ='''
			process class someClass()
			ports
			
			messages
			
			variables
				a : Integer,
				b : String
				c : String
				d : String
			init
				someMethod()()
			methods
				someMethod()() | |
					skip '''			
		]
	}
	

}
