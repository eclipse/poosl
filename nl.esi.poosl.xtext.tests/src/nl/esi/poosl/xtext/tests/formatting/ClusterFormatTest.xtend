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
class ClusterFormatTest {

	@Inject 
	extension FormatterTestHelper

	@Test 
	def void channels() {

		assertFormatted[
			toBeFormatted='''
			  cluster  class  someClass() 
			ports	
			instances  
			instance   :   someClass(    )
			channels  
			{  instance  . port,  instance  . port,instance  . port   }  
			
			{  instance  . port  }  '''
			
			expectation ='''
			cluster class someClass()
			ports
			
			instances
				instance : someClass()
			channels
				{ instance.port, instance.port, instance.port }
				{ instance.port }'''			
		]
	}
	
	@Test 
	def void instances() {

		assertFormatted[
			toBeFormatted='''
			system
			 
			instances	
			
			someInstance   :       someClass( a :=    2   , b  :=    String)	
			
			someInstance  :  someClass(a    :=    2  )	
			
			someInstance   :   someClass(    )   
				channels '''
			
			expectation ='''
			system
			instances
				someInstance : someClass(a := 2, b := String)
				someInstance : someClass(a := 2)
				someInstance : someClass()
			channels '''			
		]
	}
	
	@Test 
	def void ports() {

		assertFormatted[
			toBeFormatted='''
			process class   someClass () 
			ports a b c d , e    , f   ,g	
			messages	
			variables	
			init 
				someMethod()()
			methods
				someMethod()() | |
					skip
			'''
			
			expectation ='''
			process class someClass()
			ports
				a
				b
				c
				d,
				e,
				f,
				g
			messages
			
			variables
			
			init
				someMethod()()
			methods
				someMethod()() | |
					skip
			'''			
		]
	}

	@Test 
	def void receiveMessages() {

		assertFormatted[
			toBeFormatted='''
			process class   someClass () 
			ports	a      b    
			messages a ? Receive (  )   a ? ReceiveInt ( Integer  )   b ? Receive (Integer , String , String  ) b ? ReceiveReturn () 	
			variables	
			init 
				someMethod()()
			methods
				someMethod()() | |
					skip
			'''
			
			expectation ='''
			process class someClass()
			ports
				a
				b
			messages
				a?Receive()
				a?ReceiveInt(Integer)
				b?Receive(Integer, String, String)
				b?ReceiveReturn()
			variables
			
			init
				someMethod()()
			methods
				someMethod()() | |
					skip
			'''			
		]
	}
	
	@Test 
	def void sendMessages() {

		assertFormatted[
			toBeFormatted='''
			process class   someClass() 
			ports	a      b    
			messages a ! Send (  )   a ! SendInt ( Integer  )   b ! Send (Integer , String , String  ) b ! SendReturn () 	
			variables	
			init 
				someMethod()()
			methods
				someMethod()() | |
					skip
			'''
			
			expectation ='''
			process class someClass()
			ports
				a
				b
			messages
				a!Send()
				a!SendInt(Integer)
				b!Send(Integer, String, String)
				b!SendReturn()
			variables
			
			init
				someMethod()()
			methods
				someMethod()() | |
					skip
			'''			
		]
	}

}
