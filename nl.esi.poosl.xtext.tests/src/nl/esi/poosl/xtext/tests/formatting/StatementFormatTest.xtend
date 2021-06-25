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
class StatementFormatTest {

	@Inject extension FormatterTestHelper

	@Test 
	def void abort() {

		assertFormatted[
			toBeFormatted='''
			 process class   someClass()  
			 ports	 
			 messages	 
			 variables	 
			 init  
			 	someMethod()() 
			 methods 
			 	someMethod()() | | 
			 		abort			1      +  1 ;			1      +  1		with (			1      +  1 ;			1      +  1 		)'''
			
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
						1 + 1;
						1 + 1
					with (
						1 + 1;
						1 + 1
					)'''			
		]
	}
	
	@Test 
	def void elseIf() {

		assertFormatted[
			toBeFormatted='''
			 process class   someClass ()  
			 ports	 
			 messages	 
			 variables	 
			 init  
			 	someMethod()() 
			 methods 
			 	someMethod()() | | 
			 		 if  true  then  nil 	else  if  (  true )   then		nil	  else  if ( true )  then  nil  fi fi  fi '''
			
			expectation ='''
			process class someClass()
			ports
			
			messages
			
			variables
			
			init
				someMethod()()
			methods
				someMethod()() | |
					if  true then
						nil
					else if  (true) then
						nil
					else if (true) then
						nil
					fi fi fi '''			
		]
	}
	
	@Test 
	def void guard() {

		assertFormatted[
			toBeFormatted='''
			 process class   someClass()  
			 ports	 
			 messages	 
			 variables	 
			 init  
			 	someMethod()() 
			 methods 
			 	someMethod()() | | 
			 		 [  1   ==   1   ] 1 +   1 '''
			
			expectation ='''
			process class someClass()
			ports
			
			messages
			
			variables
			
			init
				someMethod()()
			methods
				someMethod()() | |
					[  1 == 1   ] 1 + 1 '''			
		]
	}
	@Test 
	def void interrupt() {

		assertFormatted[
			toBeFormatted='''
			  process class   someClass 		()  
			  ports	 messages  			  	variables  	init  
			  someMethod()() 		  methods 	  someMethod()() | | 
			  interrupt			1      +  1 ;			1      +  1		with (			1      +  1 ;			1      +  1 		)'''
			
			expectation ='''
			process class someClass()
			ports
			
			messages
			
			variables
			
			init
				someMethod()()
			methods
				someMethod()() | |
					interrupt
						1 + 1;
						1 + 1
					with (
						1 + 1;
						1 + 1
					)'''			
		]
	}
	@Test 
	def void parallel() {

		assertFormatted[
			toBeFormatted='''
			  process class   someClass()  
			 ports	 
			 messages	 
			 variables	 
			 init  
			 	someMethod()() 
			 methods 
			 	someMethod()() | | 
			 		 par			1   +  1 ;			1   +   1		and			1   +  1 ;			1   +   1		rap '''
			
			expectation ='''
			process class someClass()
			ports
			
			messages
			
			variables
			
			init
				someMethod()()
			methods
				someMethod()() | |
					par
						1 + 1;
						1 + 1
					and
						1 + 1;
						1 + 1
					rap '''			
		]
	}
	@Test 
	def void receive() {

		assertFormatted[
			toBeFormatted='''
			 process class   someClass()  
			 ports	 p  
			 messages	p ? Send(Integer,Integer) 
			 variables	 
			 init  
			 	someMethod()() 
			 methods 
			 	someMethod()() | a : Integer | 
			 p ? Send (  a   ,   a  ) {  1 + 1 ;   1+  1 }  '''
			
			expectation ='''
			process class someClass()
			ports
				p
			messages
				p?Send(Integer, Integer)
			variables
			
			init
				someMethod()()
			methods
				someMethod()() | a : Integer |
					p?Send(a, a) {
						1 + 1;
						1 + 1
					}  '''			
		]
	}
	@Test 
	def void select() {

		assertFormatted[
			toBeFormatted='''
			 process class   someClass()  
			 ports	 
			 messages	 
			 variables	 
			 init  
			 	someMethod()() 
			 methods 
			 	someMethod()() | | 
			 		sel		 	1   +  1   ;		 	1   +  1  		 or	 	1   +  1   ;		 	1   +  1  		 les '''
			
			expectation ='''
			process class someClass()
			ports
			
			messages
			
			variables
			
			init
				someMethod()()
			methods
				someMethod()() | |
					sel
						1 + 1;
						1 + 1
					or
						1 + 1;
						1 + 1
					les '''			
		]
	}
	@Test 
	def void send() {

		assertFormatted[
			toBeFormatted='''
			 process class   someClass()  
			 ports	 p  
			 messages	p ! Send(Integer,Integer) 
			 variables	 
			 init  
			 	someMethod()() 
			 methods 
			 	someMethod()() | a : Integer | 
			 p ! Send (1 ,1  ) {  1 + 1 ;   1+  1 }  '''
			
			expectation ='''
			process class someClass()
			ports
				p
			messages
				p!Send(Integer, Integer)
			variables
			
			init
				someMethod()()
			methods
				someMethod()() | a : Integer |
					p!Send(1, 1) {
						1 + 1;
						1 + 1
					}  '''			
		]
	}
	
	@Test 
	def void eSwitch() {

		assertFormatted[
			toBeFormatted='''
			 process class   someClass()  
			 ports	 
			 messages	 
			 variables	 
			 init  
			 	someMethod()() 
			 methods 
			 	someMethod()() | | 
			 		  		switch    1    +   1 do			case   true    then		1   +   1		default			1   +   1 	od  '''
			
			expectation ='''
			process class someClass()
			ports
			
			messages
			
			variables
			
			init
				someMethod()()
			methods
				someMethod()() | |
					switch 1 + 1 do
						case true then
							1 + 1
						default
							1 + 1
					od  '''			
		]
	}
	
	@Test 
	def void eWhile() {

		assertFormatted[
			toBeFormatted='''
			 process class   someClass ()  
			 ports	 
			 messages	 
			 variables	 
			 init  
			 	someMethod()() 
			 methods 
			 	someMethod()() | | 
			 		 while   ( 1  +  1  +3 )   do  	 1  +  1  +3  ;  1  +   1		od    '''
			
			expectation ='''
			process class someClass()
			ports
			
			messages
			
			variables
			
			init
				someMethod()()
			methods
				someMethod()() | |
					while   (1 + 1 + 3) do
						1 + 1 + 3;
						1 + 1
					od    '''			
		]
	}
	

}
