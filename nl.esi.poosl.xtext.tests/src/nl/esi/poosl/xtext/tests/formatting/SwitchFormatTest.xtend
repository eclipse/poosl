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
class SwitchFormatTest {

	@Inject extension FormatterTestHelper

	@Test 
	def void format() {

		assertFormatted[
			toBeFormatted='''
			/* ATM Switch */
			
			import "../../libraries/distributions.poosl"
			
			data class FIFOLink extends Object
			variables
				element : Object,
				nextLink : FIFOLink,
				previousLink : FIFOLink
			methods
				element : Object
					return element
				nextLink : FIFOLink
					return nextLink
				previousLink : FIFOLink
					return previousLink
				setElement(anObject : Object) : FIFOLink
					element := anObject ;
					return self
				setNextLink(aLink : FIFOLink) : FIFOLink
					nextLink := aLink ;
					return self
				setPreviousLink(aLink : FIFOLink) : FIFOLink
					previousLink := aLink ;
					return self
			
			data class BoundedFIFOBuffer extends Object
			variables
				fifoDepth, size : Integer,
				firstLink, lastLink : FIFOLink
			methods
				depth : Integer
					return fifoDepth
				firstElement : Object
					if fifoDepth = 0 then
						return nil
					else
						return lastLink element
					fi
				isEmpty : Boolean
					return fifoDepth = 0
				isFull : Boolean
					return fifoDepth = size
				isNotEmpty : Boolean
					return fifoDepth > 0
				isNotFull : Boolean
					return fifoDepth < size
				put(anElement : Object) : BoundedFIFOBuffer | aLink : FIFOLink |
					if fifoDepth = 0 then
						firstLink := new(FIFOLink) setElement(anElement) ;
						lastLink := firstLink
					else
						aLink := new(FIFOLink) setElement(anElement) setNextLink(firstLink) ;
						firstLink setPreviousLink(aLink) ;
						firstLink := aLink
					fi ;
					fifoDepth := fifoDepth + 1 ;
					return self
				removeFirstElement : Object | aLink : FIFOLink |
					if fifoDepth = 0 then
						return nil
					else
						aLink := lastLink ;
						if fifoDepth > 1 then
							lastLink := lastLink previousLink ;
							lastLink setNextLink(nil)
						else
							firstLink := nil ;
							lastLink := nil
						fi ;
						fifoDepth := fifoDepth - 1 ;
						return aLink element
					fi
				size(s : Integer) : BoundedFIFOBuffer
					fifoDepth := 0 ;
					size := s ;
					firstLink := nil ;
					lastLink := nil ;
					return self
			
			data class Cell extends Object
			variables
				entryTime, exitTime : Real,
				route : BoundedFIFOBuffer
			methods
				destinationPort : Integer
					return route firstElement
				nextDestinationPort : Cell
					route removeFirstElement ;
					return self
				setArbitraryRoute : Cell | rand : RandomGenerator |
					rand := new(RandomGenerator) randomiseSeed ;
					route := new(BoundedFIFOBuffer) size(2) ;
					if rand random > 0.5 then
						route put(1)
					else
						route put(2)
					fi ;
					if rand random > 0.5 then
						route put(1)
					else
						route put(2)
					fi ;
					return self
				setEntryTime(t : Real) : Cell
					entryTime := t ;
					return self
				setExitTime(t : Real) : Cell
					exitTime := t ;
					return self
			
			process class Source(trafficDistribution : Exponential)
			ports
				o
			messages
				o!cell(Cell)
			variables
			init
				transmit()()
			methods
				transmit()() | cell : Cell |
					cell := new(Cell) setArbitraryRoute setEntryTime(currentTime) ;
					o!cell(cell) ;
					delay trafficDistribution sample ;
					transmit()()
			
			process class Destination
			ports
				i
			messages
				i?cell(Cell)
			variables
			init
				receive()()
			methods
				receive()() | cell : Cell |
					i?cell(cell) ;
					receive()()
			
			process class Switch(bufferSize : Integer, cellTime : Real)
			ports
				i1,
				i2,
				o1,
				o2
			messages
				i1?cell(Cell),
				i2?cell(Cell),
				o1!cell(Cell),
				o2!cell(Cell)
			variables
				outputBufferArray : Array
			init
				startUp()()
			methods
				startUp()()
					outputBufferArray := new(Array) resize(2) ;
					outputBufferArray putAt(1, new(BoundedFIFOBuffer) size(bufferSize)) ;
					outputBufferArray putAt(2, new(BoundedFIFOBuffer) size(bufferSize)) ;
					par
						handleInput1()()
					and
						handleInput2()()
					and
						handleOutput1()()
					and
						handleOutput2()()
					rap
				handleInput1()() | cell : Cell |
					i1?cell(cell | outputBufferArray at(cell destinationPort) isNotFull) {
						outputBufferArray at(cell destinationPort) put(cell) ;
						cell nextDestinationPort
					} ;
					delay cellTime ;
					handleInput1()()
				handleInput2()() | cell : Cell |
					i2?cell(cell | outputBufferArray at(cell destinationPort) isNotFull) {
						outputBufferArray at(cell destinationPort) put(cell) ;
						cell nextDestinationPort
					} ;
					delay cellTime ;
					handleInput2()()
				handleOutput1()()
					[ outputBufferArray at(1) isNotEmpty ] o1!cell(outputBufferArray at(1) firstElement) {
						outputBufferArray at(1) removeFirstElement
					} ;
					delay cellTime ;
					handleOutput1()()
				handleOutput2()()
					[ outputBufferArray at(2) isNotEmpty ] o2!cell(outputBufferArray at(2) firstElement) {
						outputBufferArray at(2) removeFirstElement
					} ;
					delay cellTime ;
					handleOutput2()()
			
			cluster class MultistageSwitch
			ports
				i1,
				i2,
				i3,
				i4,
				o1,
				o2,
				o3,
				o4
			instances
				x1 : Switch(bufferSize := 10, cellTime := 20.0)
				x2 : Switch(bufferSize := 10, cellTime := 20.0)
				x3 : Switch(bufferSize := 10, cellTime := 20.0)
				x4 : Switch(bufferSize := 10, cellTime := 20.0)
			channels
				{ i1, x1.i1 }
				{ i2, x1.i2 }
				{ i3, x2.i1 }
				{ i4, x2.i2 }
				{ x1.o1, x3.i1 }
				{ x1.o2, x4.i1 }
				{ x2.o1, x3.i2 }
				{ x2.o2, x4.i2 }
				{ x3.o1, o1 }
				{ x3.o2, o2 }
				{ x4.o1, o3 }
				{ x4.o2, o4 }
			
			system
			instances
				x : MultistageSwitch()
				source1 : Source(trafficDistribution := new(Exponential) withParameter(3.0))
				source2 : Source(trafficDistribution := new(Exponential) withParameter(3.0))
				source3 : Source(trafficDistribution := new(Exponential) withParameter(3.0))
				source4 : Source(trafficDistribution := new(Exponential) withParameter(3.0))
				destination1 : Destination()
				destination2 : Destination()
				destination3 : Destination()
				destination4 : Destination()
			channels
				{ x.i1, source1.o }
				{ x.i2, source2.o }
				{ x.i3, source3.o }
				{ x.i4, source4.o }
				{ x.o1, destination1.i }
				{ x.o2, destination2.i }
				{ x.o3, destination3.i }
				{ x.o4, destination4.i }'''
			
			expectation ='''
			/* ATM Switch */
			import "../../libraries/distributions.poosl"
			
			data class FIFOLink extends Object
			variables
				element : Object,
				nextLink : FIFOLink,
				previousLink : FIFOLink
			methods
				element : Object
					return element
			
				nextLink : FIFOLink
					return nextLink
			
				previousLink : FIFOLink
					return previousLink
			
				setElement(anObject : Object) : FIFOLink
					element := anObject;
					return self
			
				setNextLink(aLink : FIFOLink) : FIFOLink
					nextLink := aLink;
					return self
			
				setPreviousLink(aLink : FIFOLink) : FIFOLink
					previousLink := aLink;
					return self
			
			data class BoundedFIFOBuffer extends Object
			variables
				fifoDepth, size : Integer,
				firstLink, lastLink : FIFOLink
			methods
				depth : Integer
					return fifoDepth
			
				firstElement : Object
					if fifoDepth = 0 then
						return nil
					else
						return lastLink element
					fi
			
				isEmpty : Boolean
					return fifoDepth = 0
			
				isFull : Boolean
					return fifoDepth = size
			
				isNotEmpty : Boolean
					return fifoDepth > 0
			
				isNotFull : Boolean
					return fifoDepth < size
			
				put(anElement : Object) : BoundedFIFOBuffer | aLink : FIFOLink |
					if fifoDepth = 0 then
						firstLink := new(FIFOLink) setElement(anElement);
						lastLink := firstLink
					else
						aLink := new(FIFOLink) setElement(anElement) setNextLink(firstLink);
						firstLink setPreviousLink(aLink);
						firstLink := aLink
					fi;
					fifoDepth := fifoDepth + 1;
					return self
			
				removeFirstElement : Object | aLink : FIFOLink |
					if fifoDepth = 0 then
						return nil
					else
						aLink := lastLink;
						if fifoDepth > 1 then
							lastLink := lastLink previousLink;
							lastLink setNextLink(nil)
						else
							firstLink := nil;
							lastLink := nil
						fi;
						fifoDepth := fifoDepth - 1;
						return aLink element
					fi
			
				size(s : Integer) : BoundedFIFOBuffer
					fifoDepth := 0;
					size := s;
					firstLink := nil;
					lastLink := nil;
					return self
			
			data class Cell extends Object
			variables
				entryTime, exitTime : Real,
				route : BoundedFIFOBuffer
			methods
				destinationPort : Integer
					return route firstElement
			
				nextDestinationPort : Cell
					route removeFirstElement;
					return self
			
				setArbitraryRoute : Cell | rand : RandomGenerator |
					rand := new(RandomGenerator) randomiseSeed;
					route := new(BoundedFIFOBuffer) size(2);
					if rand random > 0.5 then
						route put(1)
					else
						route put(2)
					fi;
					if rand random > 0.5 then
						route put(1)
					else
						route put(2)
					fi;
					return self
			
				setEntryTime(t : Real) : Cell
					entryTime := t;
					return self
			
				setExitTime(t : Real) : Cell
					exitTime := t;
					return self
			
			process class Source(trafficDistribution : Exponential)
			ports
				o
			messages
				o!cell(Cell)
			variables
			
			init
				transmit()()
			methods
				transmit()() | cell : Cell |
					cell := new(Cell) setArbitraryRoute setEntryTime(currentTime);
					o!cell(cell);
					delay trafficDistribution sample;
					transmit()()
			
			process class Destination
			ports
				i
			messages
				i?cell(Cell)
			variables
			
			init
				receive()()
			methods
				receive()() | cell : Cell |
					i?cell(cell);
					receive()()
			
			process class Switch(bufferSize : Integer, cellTime : Real)
			ports
				i1,
				i2,
				o1,
				o2
			messages
				i1?cell(Cell),
				i2?cell(Cell),
				o1!cell(Cell),
				o2!cell(Cell)
			variables
				outputBufferArray : Array
			init
				startUp()()
			methods
				startUp()()
					outputBufferArray := new(Array) resize(2);
					outputBufferArray putAt(1, new(BoundedFIFOBuffer) size(bufferSize));
					outputBufferArray putAt(2, new(BoundedFIFOBuffer) size(bufferSize));
					par
						handleInput1()()
					and
						handleInput2()()
					and
						handleOutput1()()
					and
						handleOutput2()()
					rap
			
				handleInput1()() | cell : Cell |
					i1?cell(cell | outputBufferArray at(cell destinationPort) isNotFull) {
						outputBufferArray at(cell destinationPort) put(cell);
						cell nextDestinationPort
					};
					delay cellTime;
					handleInput1()()
			
				handleInput2()() | cell : Cell |
					i2?cell(cell | outputBufferArray at(cell destinationPort) isNotFull) {
						outputBufferArray at(cell destinationPort) put(cell);
						cell nextDestinationPort
					};
					delay cellTime;
					handleInput2()()
			
				handleOutput1()()
					[ outputBufferArray at(1) isNotEmpty ] o1!cell(outputBufferArray at(1) firstElement) {outputBufferArray at(1)
						removeFirstElement};
					delay cellTime;
					handleOutput1()()
			
				handleOutput2()()
					[ outputBufferArray at(2) isNotEmpty ] o2!cell(outputBufferArray at(2) firstElement) {outputBufferArray at(2)
						removeFirstElement};
					delay cellTime;
					handleOutput2()()
			
			cluster class MultistageSwitch
			ports
				i1,
				i2,
				i3,
				i4,
				o1,
				o2,
				o3,
				o4
			instances
				x1 : Switch(bufferSize := 10, cellTime := 20.0)
				x2 : Switch(bufferSize := 10, cellTime := 20.0)
				x3 : Switch(bufferSize := 10, cellTime := 20.0)
				x4 : Switch(bufferSize := 10, cellTime := 20.0)
			channels
				{ i1, x1.i1 }
				{ i2, x1.i2 }
				{ i3, x2.i1 }
				{ i4, x2.i2 }
				{ x1.o1, x3.i1 }
				{ x1.o2, x4.i1 }
				{ x2.o1, x3.i2 }
				{ x2.o2, x4.i2 }
				{ x3.o1, o1 }
				{ x3.o2, o2 }
				{ x4.o1, o3 }
				{ x4.o2, o4 }
			
			system
			instances
				x : MultistageSwitch()
				source1 : Source(trafficDistribution := new(Exponential) withParameter(3.0))
				source2 : Source(trafficDistribution := new(Exponential) withParameter(3.0))
				source3 : Source(trafficDistribution := new(Exponential) withParameter(3.0))
				source4 : Source(trafficDistribution := new(Exponential) withParameter(3.0))
				destination1 : Destination()
				destination2 : Destination()
				destination3 : Destination()
				destination4 : Destination()
			channels
				{ x.i1, source1.o }
				{ x.i2, source2.o }
				{ x.i3, source3.o }
				{ x.i4, source4.o }
				{ x.o1, destination1.i }
				{ x.o2, destination2.i }
				{ x.o3, destination3.i }
				{ x.o4, destination4.i }'''			
		]
	}

}
