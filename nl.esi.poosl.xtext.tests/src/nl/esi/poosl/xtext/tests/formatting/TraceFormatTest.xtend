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
class TraceFormatTest {

	@Inject extension FormatterTestHelper

	@Test 
	def void format() {

		assertFormatted[
			toBeFormatted='''
			import "../../libraries/trace.poosl"
			
			/* This example model gives an impression of how the interface to the TRACE tool can be used */
			
			// Examplifies the approach of using process class TraceProcess (for access to TRACE via files or streaming through a socket)
			process class TraceExample1(Streaming: Boolean)
			ports Trace
			messages
				Trace!Time(Integer, String, String),
				Trace!Attribute(String, Boolean, Boolean, Boolean, Boolean, Boolean),
				Trace!Context(String, Set),
				Trace!Quantity(String, String),
				Trace!Color(String, String),
				Trace!Filter(String, Set),
				Trace!WriteConfiguration(String),
				Trace!WriteConfiguration,
				Trace!WriteQuantity(String, Set),
				Trace!Result(String, Object),
				Trace!WriteTrace(String),
				Trace!WriteTrace,
				Trace!FullResource(String, Object, String, String, Map),
				Trace!AmountResource(String, Object, String, String, Map),
				Trace!FullClaim(String, Real, Real, String, Map),
				Trace!AmountClaim(String, Real, Real, String, Map, Object),
				Trace!FullClaimStart(String, Real, String, Map),
				Trace!AmountClaimStart(String, Real, String, Map, Object),
				Trace!FullClaimStop(Integer, Real),
				Trace!AmountClaimStop(Integer, Real),
				Trace!Dependency(Integer, Integer, Integer, String, Map),
				Trace?Claim(Integer)
			variables ID, ID1, ID2: Integer
			init Init()()
			methods
			Init()()
				Trace!Time(0,"s","%5g");
				Trace!Attribute("Name", true, false, false, true, true);
				Trace!Attribute("Core", true, false, false, true, true);
				Trace!Attribute("Package", true, false, false, true, true);
				Trace!Attribute("Activity", true, false, false, true, true);
				Trace!Attribute("Thread", true, false, false, true, true);
				Trace!Attribute("Connection", true, false, false, true, true);
				Trace!Attribute("Event", true, false, false, true, true);
				Trace!Attribute("Relevance", false, false, true, false, false);
				Trace!Context("CPU", new(Set) add("Name") add("Core"));
				Trace!Context("GPU", new(Set) add("Name"));
				Trace!Context("Bus", new(Set) add("Name"));
				Trace!Context("Memory", new(Set) add("Name"));
				Trace!Context("Task", new(Set) add("Package") add("Activity") add("Thread"));
				Trace!Context("Message", new(Set) add("Connection"));
				Trace!Context("Storage", new(Set) add("Package") add("Activity") add("Thread"));
				Trace!Context("User", new(Set) add("Name"));
				Trace!Context("UserEvent", new(Set) add("Event") add("Package"));
				Trace!Context("Dependency", new(Set) add("Name") add("Relevance"));
				Trace!Color("Start", "009955");
				Trace!Color("Kill", "990000");
				Trace!Filter("Relevance", new(Set) add("cross-reference"));
				Trace!Quantity("CPU_Usage", "%");
				Trace!Quantity("Memory1", "MB");
				Trace!Quantity("Memory2", "MB");
				Trace!Quantity("Latency", "ms");
				if !Streaming then
					Trace!WriteConfiguration("configuration.txt");
					Trace!WriteTrace("trace1.etf")
				else
					Trace!WriteConfiguration;
					Trace!WriteTrace
				fi;
				Trace!AmountResource("Memory1", 4096000, "B", "Memory", new(Map) putAt("Name", "Memory1"));
				Trace!AmountResource("Memory2", 4096000, "B", "Memory", new(Map) putAt("Name", "Memory2"));
				Trace!FullResource("Adreno", 100, "%", "GPU", new(Map) putAt("Name", "Adreno"));
				Trace!AmountResource("GMem", 524288, "B", "Memory", new(Map) putAt("Name", "GMem"));
				Trace!FullResource("User", 1, "E", "User", new(Map) putAt("Name", "User"));
				Trace!FullResource("MultiMediaFabric", 100, "%", "Bus", new(Map) putAt("Name", "MultiMediaFabic"));
				Trace!AmountResource("L2 Cache", 2097152, "B", "Memory", new(Map) putAt("Name", "L2 Cache"));
				Trace!FullResource("AppsFabric", 100, "%", "Bus", new(Map) putAt("Name", "AppsFabric"));
				Trace!FullResource("Krait1", 100, "%", "CPU", new(Map) putAt("Name", "Krait") putAt("Core", "1"));
				Trace!FullResource("Krait2", 100, "%", "CPU", new(Map) putAt("Name", "Krait") putAt("Core", "2"));
				Trace!FullClaim("User", 1.3, 20.3, "UserEvent", new(Map) putAt("Event", "Start") putAt("Package", "user.event1"));
				Trace?Claim(ID1);
				Trace!FullClaim("MultiMediaFabric", 1.3, 15.3, "Message", new(Map) putAt("Connection", "GPU -> DDR"));
				Trace!FullClaimStart("AppsFabric", 5.3, "Message", new(Map) putAt("Connection", "CPU -> DDR"));
				Trace?Claim(ID);
				Trace!FullClaimStop(ID, 10.3);
				Trace!FullClaim("AppsFabric", 11.3, 17.3, "Message", new(Map) putAt("Connection", "GPU -> DDR"));
				Trace?Claim(ID2);
				Trace!Dependency(ID1, ID2, 0, "Dependency", new(Map) putAt("Name", "TaskDependency1") putAt("Relevance", "cross-reference"));
				Trace!AmountClaimStart("Memory1", 5.5, "Storage", new(Map) putAt("Package", "package") putAt("Activity", "activity") putAt("Thread", "2"), 1000000);
				Trace?Claim(ID); 
				Trace!AmountClaimStop(ID, 16.3);
				Trace!AmountClaim("Memory2", 5.3, 18.3, "Storage", new(Map) putAt("Package", "Package") putAt("Activity", "activity") putAt("Thread", "1"), 1908234);
				Trace?Claim(ID1);
				Trace!FullClaim("AppsFabric", 17.3, 25.3, "Message", new(Map) putAt("Connection", "CPU -> DDR"));
				Trace?Claim(ID);
				Trace!AmountClaim("Memory1", 20.5, 40.5, "Storage", new(Map) putAt("Package", "package") putAt("Activity", "activity") putAt("Thread", "3"), 3500000);
				Trace!AmountClaim("Memory2", 19.3, 29.3, "Storage", new(Map) putAt("Package", "package") putAt("Activity", "activity") putAt("Thread", "4"), 1581100);
				Trace?Claim(ID2);
				Trace!Dependency(ID1, ID2, 0, "Dependency", new(Map) putAt("Name", "TaskDependency2") putAt("Relevance", "cross-reference"));
				Trace!FullClaim("User", 20.52, 30.52, "UserEvent", new(Map) putAt("Event", "Start") putAt("Package", "user.event2"));
				Trace!FullClaim("Adreno", 15.3, 30.6, "Task", new(Map) putAt("Package", "performance.text") putAt("Activity", "test.activity") putAt("Thread", "0"));
				Trace?Claim(ID2);
				Trace!Dependency(ID, ID2, 1, "Dependency", new(Map) putAt("Name", "TaskDependency3") putAt("Relevance", "cross-reference"));
				Trace!AmountClaim("GMem", 10.3, 30.86, "Storage", new(Map) putAt("Package", "package") putAt("Activity", "activity") putAt("Thread", "1"), 225000);
				Trace!FullClaim("Krait1", 1.302, 16.69, "Task", new(Map) putAt("Package", "performance.text") putAt("Activity", "TestActivity") putAt("Thread", "5"));
				Trace!FullClaim("Krait1", 17.302, 20.69, "Task", new(Map) putAt("Package", "performance.text") putAt("Activity", "TestActivity") putAt("Thread", "6"));
				Trace!FullClaim("Krait2", 2.9, 13.69, "Task", new(Map) putAt("Package", "performance.text") putAt("Activity", "TestActivity") putAt("Thread", "7"));
				Trace!FullClaim("Krait2", 15.302, 28.69, "Task", new(Map) putAt("Package", "performance.text") putAt("Activity", "TestActivity") putAt("Thread", "8"));
				Trace!WriteQuantity("quantity1a.eqf", new(Set) add("trace1.etf"));
				Trace!Result("CPU_Usage", 0.5076471864537928);
				Trace!Result("Memory1", 0.8306952941501128);
				Trace!Result("Memory2", 0.8379125403722565);
				Trace!Result("Latency", 11261.635700000095);
				Trace!WriteQuantity("quantity1b.eqf", new(Set) add("trace1.etf"));
				Trace!Result("CPU_Usage", 0.5904346596943799);
				Trace!Result("Memory1", 0.8373007948980877);
				Trace!Result("Memory2", 0.338276447163645);
				Trace!Result("Latency", 9628.774466666566)
			
			// Examplifies the approach of using the Trace data class for access to TRACE through files
			process class TraceExample2
			ports
			messages
			variables Trace: Trace, ID, ID1, ID2: Integer, Claim: String
			init Run()()
			methods
			Run()()
				Trace := new(Trace);
				Trace time(0,"s","%5g");
				Trace attribute("Name", true, false, false, true, true);
				Trace attribute("Core", true, false, false, true, true);
				Trace attribute("Package", true, false, false, true, true);
				Trace attribute("Activity", true, false, false, true, true);
				Trace attribute("Thread", true, false, false, true, true);
				Trace attribute("Connection", true, false, false, true, true);
				Trace attribute("Event", true, false, false, true, true);
				Trace attribute("Relevance", false, false, true, false, false);
				Trace context("CPU", new(Set) add("Name") add("Core"));
				Trace context("GPU", new(Set) add("Name"));
				Trace context("Bus", new(Set) add("Name"));
				Trace context("Memory", new(Set) add("Name"));
				Trace context("Task", new(Set) add("Package") add("Activity") add("Thread"));
				Trace context("Message", new(Set) add("Connection"));
				Trace context("Storage", new(Set) add("Package") add("Activity") add("Thread"));
				Trace context("User", new(Set) add("Name"));
				Trace context("UserEvent", new(Set) add("Event") add("Package"));
				Trace context("Dependency", new(Set) add("Name") add("Relevance"));
				Trace color("Start", "009955");
				Trace color("Kill", "990000");
				Trace filter("Relevance", new(Set) add("cross-reference"));
				Trace quantity("CPU_Usage", "%");
				Trace quantity("Memory1", "MB");
				Trace quantity("Memory2", "MB");
				Trace quantity("Latency", "ms");
				Trace writeConfiguration("configuration.txt");
				Trace writeTrace("trace2.etf");
				Trace write(Trace amountResource("Memory1", 4096000, "B", "Memory", new(Map) putAt("Name", "Memory1")));
				Trace write(Trace amountResource("Memory2", 4096000, "B", "Memory", new(Map) putAt("Name", "Memory2")));
				Trace write(Trace fullResource("Adreno", 100, "%", "GPU", new(Map) putAt("Name", "Adreno")));
				Trace write(Trace amountResource("GMem", 524288, "B", "Memory", new(Map) putAt("Name", "GMem")));
				Trace write(Trace fullResource("User", 1, "E", "User", new(Map) putAt("Name", "User")));
				Trace write(Trace fullResource("MultiMediaFabric", 100, "%", "Bus", new(Map) putAt("Name", "MultiMediaFabic")));
				Trace write(Trace amountResource("L2 Cache", 2097152, "B", "Memory", new(Map) putAt("Name", "L2 Cache")));
				Trace write(Trace fullResource("AppsFabric", 100, "%", "Bus", new(Map) putAt("Name", "AppsFabric")));
				Trace write(Trace fullResource("Krait1", 100, "%", "CPU", new(Map) putAt("Name", "Krait") putAt("Core", "1")));
				Trace write(Trace fullResource("Krait2", 100, "%", "CPU", new(Map) putAt("Name", "Krait") putAt("Core", "2")));
				Claim := Trace fullClaim("User", 1.3, 20.3, "UserEvent", new(Map) putAt("Event", "Start") putAt("Package", "user.event1"));
				Trace write(Claim);
				ID1 := Claim splitOn('\t') at(2) toInteger;
				Trace write(Trace fullClaim("MultiMediaFabric", 1.3, 15.3, "Message", new(Map) putAt("Connection", "GPU -> DDR")));
				ID := Trace fullClaimStart("AppsFabric", 5.3, "Message", new(Map) putAt("Connection", "CPU -> DDR"));
				Trace write(Trace fullClaimStop(ID, 10.3));
				Claim := Trace fullClaim("AppsFabric", 11.3, 17.3, "Message", new(Map) putAt("Connection", "GPU -> DDR"));
				Trace write(Claim);
				ID2 := Claim splitOn('\t') at(2) toInteger;
				Trace write(Trace dependency(ID1, ID2, 0, "Dependency", new(Map) putAt("Name", "TaskDependency1") putAt("Relevance", "cross-reference")));
				ID := Trace amountClaimStart("Memory1", 5.5, "Storage", new(Map) putAt("Package", "package") putAt("Activity", "activity") putAt("Thread", "2"), 1000000);
				Trace write(Trace amountClaimStop(ID, 16.3));
				Claim := Trace amountClaim("Memory2", 5.3, 18.3, "Storage", new(Map) putAt("Package", "Package") putAt("Activity", "activity") putAt("Thread", "1"), 1908234);
				Trace write(Claim); 
				ID1 := Claim splitOn('\t') at(2) toInteger;
				Claim := Trace fullClaim("AppsFabric", 17.3, 25.3, "Message", new(Map) putAt("Connection", "CPU -> DDR"));
				Trace write(Claim); 
				ID := Claim splitOn('\t') at(2) toInteger;
				Trace write(Trace amountClaim("Memory1", 20.5, 40.5, "Storage", new(Map) putAt("Package", "package") putAt("Activity", "activity") putAt("Thread", "3"), 3500000));
				Claim := Trace amountClaim("Memory2", 19.3, 29.3, "Storage", new(Map) putAt("Package", "package") putAt("Activity", "activity") putAt("Thread", "4"), 1581100);
				Trace write(Claim); 
				ID2 := Claim splitOn('\t') at(2) toInteger;
				Trace write(Trace dependency(ID1, ID2, 0, "Dependency", new(Map) putAt("Name", "TaskDependency2") putAt("Relevance", "cross-reference")));
				Trace write(Trace fullClaim("User", 20.52, 30.52, "UserEvent", new(Map) putAt("Event", "Start") putAt("Package", "user.event2")));
				Claim := Trace fullClaim("Adreno", 15.3, 30.6, "Task", new(Map) putAt("Package", "performance.text") putAt("Activity", "test.activity") putAt("Thread", "0")); 
				Trace write(Claim);
				ID2 := Claim splitOn('\t') at(2) toInteger;
				Trace write(Trace dependency(ID, ID2, 1, "Dependency", new(Map) putAt("Name", "TaskDependency3") putAt("Relevance", "cross-reference")));
				Trace write(Trace amountClaim("GMem", 10.3, 30.86, "Storage", new(Map) putAt("Package", "package") putAt("Activity", "activity") putAt("Thread", "1"), 225000));
				Trace write(Trace fullClaim("Krait1", 1.302, 16.69, "Task", new(Map) putAt("Package", "performance.text") putAt("Activity", "TestActivity") putAt("Thread", "5")));
				Trace write(Trace fullClaim("Krait1", 17.302, 20.69, "Task", new(Map) putAt("Package", "performance.text") putAt("Activity", "TestActivity") putAt("Thread", "6")));
				Trace write(Trace fullClaim("Krait2", 2.9, 13.69, "Task", new(Map) putAt("Package", "performance.text") putAt("Activity", "TestActivity") putAt("Thread", "7")));
				Trace write(Trace fullClaim("Krait2", 15.302, 28.69, "Task", new(Map) putAt("Package", "performance.text") putAt("Activity", "TestActivity") putAt("Thread", "8")));
				Trace writeQuantity("quantity2a.eqf", new(Set) add("trace2.etf"));
				Trace result("CPU_Usage", 0.5076471864537928);
				Trace result("Memory1", 0.8306952941501128);
				Trace result("Memory2", 0.8379125403722565);
				Trace result("Latency", 11261.635700000095);
				Trace writeQuantity("quantity2b.eqf", new(Set) add("trace2.etf"));
				Trace result("CPU_Usage", 0.5904346596943799);
				Trace result("Memory1", 0.8373007948980877);
				Trace result("Memory2", 0.338276447163645);
				Trace result("Latency", 9628.774466666566)
			
			cluster class TraceExample(HostName: String, PortNumber: Integer)
			ports
			instances
				TraceTest1: TraceExample1(Streaming := PortNumber != nil)
				TraceTest2: TraceExample2()
				TraceProcess: TraceProcess(HostName := HostName, PortNumber := PortNumber)
			channels
				{TraceTest1.Trace, TraceProcess.Trace}
			
			system
			instances
				TraceExample: TraceExample(HostName := nil, PortNumber := nil)			    // Using files only
				// TraceExample: TraceExample(HostName := "localhost", PortNumber := 9292)	// These are the default settings in TRACE in case of streaming
			channels'''
			
			expectation ='''
			import "../../libraries/trace.poosl"
			
			/* This example model gives an impression of how the interface to the TRACE tool can be used */
			// Examplifies the approach of using process class TraceProcess (for access to TRACE via files or streaming through a socket)
			process class TraceExample1(Streaming : Boolean)
			ports
				Trace
			messages
				Trace!Time(Integer, String, String),
				Trace!Attribute(String, Boolean, Boolean, Boolean, Boolean, Boolean),
				Trace!Context(String, Set),
				Trace!Quantity(String, String),
				Trace!Color(String, String),
				Trace!Filter(String, Set),
				Trace!WriteConfiguration(String),
				Trace!WriteConfiguration,
				Trace!WriteQuantity(String, Set),
				Trace!Result(String, Object),
				Trace!WriteTrace(String),
				Trace!WriteTrace,
				Trace!FullResource(String, Object, String, String, Map),
				Trace!AmountResource(String, Object, String, String, Map),
				Trace!FullClaim(String, Real, Real, String, Map),
				Trace!AmountClaim(String, Real, Real, String, Map, Object),
				Trace!FullClaimStart(String, Real, String, Map),
				Trace!AmountClaimStart(String, Real, String, Map, Object),
				Trace!FullClaimStop(Integer, Real),
				Trace!AmountClaimStop(Integer, Real),
				Trace!Dependency(Integer, Integer, Integer, String, Map),
				Trace?Claim(Integer)
			variables
				ID, ID1, ID2 : Integer
			init
				Init()()
			methods
				Init()()
					Trace!Time(0, "s", "%5g");
					Trace!Attribute("Name", true, false, false, true, true);
					Trace!Attribute("Core", true, false, false, true, true);
					Trace!Attribute("Package", true, false, false, true, true);
					Trace!Attribute("Activity", true, false, false, true, true);
					Trace!Attribute("Thread", true, false, false, true, true);
					Trace!Attribute("Connection", true, false, false, true, true);
					Trace!Attribute("Event", true, false, false, true, true);
					Trace!Attribute("Relevance", false, false, true, false, false);
					Trace!Context("CPU", new(Set) add("Name") add("Core"));
					Trace!Context("GPU", new(Set) add("Name"));
					Trace!Context("Bus", new(Set) add("Name"));
					Trace!Context("Memory", new(Set) add("Name"));
					Trace!Context("Task", new(Set) add("Package") add("Activity") add("Thread"));
					Trace!Context("Message", new(Set) add("Connection"));
					Trace!Context("Storage", new(Set) add("Package") add("Activity") add("Thread"));
					Trace!Context("User", new(Set) add("Name"));
					Trace!Context("UserEvent", new(Set) add("Event") add("Package"));
					Trace!Context("Dependency", new(Set) add("Name") add("Relevance"));
					Trace!Color("Start", "009955");
					Trace!Color("Kill", "990000");
					Trace!Filter("Relevance", new(Set) add("cross-reference"));
					Trace!Quantity("CPU_Usage", "%");
					Trace!Quantity("Memory1", "MB");
					Trace!Quantity("Memory2", "MB");
					Trace!Quantity("Latency", "ms");
					if !Streaming then
						Trace!WriteConfiguration("configuration.txt");
						Trace!WriteTrace("trace1.etf")
					else
						Trace!WriteConfiguration;
						Trace!WriteTrace
					fi;
					Trace!AmountResource("Memory1", 4096000, "B", "Memory", new(Map) putAt("Name", "Memory1"));
					Trace!AmountResource("Memory2", 4096000, "B", "Memory", new(Map) putAt("Name", "Memory2"));
					Trace!FullResource("Adreno", 100, "%", "GPU", new(Map) putAt("Name", "Adreno"));
					Trace!AmountResource("GMem", 524288, "B", "Memory", new(Map) putAt("Name", "GMem"));
					Trace!FullResource("User", 1, "E", "User", new(Map) putAt("Name", "User"));
					Trace!FullResource("MultiMediaFabric", 100, "%", "Bus", new(Map) putAt("Name", "MultiMediaFabic"));
					Trace!AmountResource("L2 Cache", 2097152, "B", "Memory", new(Map) putAt("Name", "L2 Cache"));
					Trace!FullResource("AppsFabric", 100, "%", "Bus", new(Map) putAt("Name", "AppsFabric"));
					Trace!FullResource("Krait1", 100, "%", "CPU", new(Map) putAt("Name", "Krait") putAt("Core", "1"));
					Trace!FullResource("Krait2", 100, "%", "CPU", new(Map) putAt("Name", "Krait") putAt("Core", "2"));
					Trace!FullClaim("User", 1.3, 20.3, "UserEvent", new(Map) putAt("Event", "Start") putAt("Package",
						"user.event1"));
					Trace?Claim(ID1);
					Trace!FullClaim("MultiMediaFabric", 1.3, 15.3, "Message", new(Map) putAt("Connection", "GPU -> DDR"));
					Trace!FullClaimStart("AppsFabric", 5.3, "Message", new(Map) putAt("Connection", "CPU -> DDR"));
					Trace?Claim(ID);
					Trace!FullClaimStop(ID, 10.3);
					Trace!FullClaim("AppsFabric", 11.3, 17.3, "Message", new(Map) putAt("Connection", "GPU -> DDR"));
					Trace?Claim(ID2);
					Trace!Dependency(ID1, ID2, 0, "Dependency", new(Map) putAt("Name", "TaskDependency1") putAt("Relevance",
						"cross-reference"));
					Trace!AmountClaimStart("Memory1", 5.5, "Storage", new(Map) putAt("Package", "package") putAt("Activity",
						"activity") putAt("Thread", "2"), 1000000);
					Trace?Claim(ID);
					Trace!AmountClaimStop(ID, 16.3);
					Trace!AmountClaim("Memory2", 5.3, 18.3, "Storage", new(Map) putAt("Package", "Package") putAt("Activity",
						"activity") putAt("Thread", "1"), 1908234);
					Trace?Claim(ID1);
					Trace!FullClaim("AppsFabric", 17.3, 25.3, "Message", new(Map) putAt("Connection", "CPU -> DDR"));
					Trace?Claim(ID);
					Trace!AmountClaim("Memory1", 20.5, 40.5, "Storage", new(Map) putAt("Package", "package") putAt("Activity",
						"activity") putAt("Thread", "3"), 3500000);
					Trace!AmountClaim("Memory2", 19.3, 29.3, "Storage", new(Map) putAt("Package", "package") putAt("Activity",
						"activity") putAt("Thread", "4"), 1581100);
					Trace?Claim(ID2);
					Trace!Dependency(ID1, ID2, 0, "Dependency", new(Map) putAt("Name", "TaskDependency2") putAt("Relevance",
						"cross-reference"));
					Trace!FullClaim("User", 20.52, 30.52, "UserEvent", new(Map) putAt("Event", "Start") putAt("Package",
						"user.event2"));
					Trace!FullClaim("Adreno", 15.3, 30.6, "Task", new(Map) putAt("Package", "performance.text") putAt("Activity",
						"test.activity") putAt("Thread", "0"));
					Trace?Claim(ID2);
					Trace!Dependency(ID, ID2, 1, "Dependency", new(Map) putAt("Name", "TaskDependency3") putAt("Relevance",
						"cross-reference"));
					Trace!AmountClaim("GMem", 10.3, 30.86, "Storage", new(Map) putAt("Package", "package") putAt("Activity",
						"activity") putAt("Thread", "1"), 225000);
					Trace!FullClaim("Krait1", 1.302, 16.69, "Task", new(Map) putAt("Package", "performance.text") putAt("Activity",
						"TestActivity") putAt("Thread", "5"));
					Trace!FullClaim("Krait1", 17.302, 20.69, "Task", new(Map) putAt("Package", "performance.text") putAt("Activity",
						"TestActivity") putAt("Thread", "6"));
					Trace!FullClaim("Krait2", 2.9, 13.69, "Task", new(Map) putAt("Package", "performance.text") putAt("Activity",
						"TestActivity") putAt("Thread", "7"));
					Trace!FullClaim("Krait2", 15.302, 28.69, "Task", new(Map) putAt("Package", "performance.text") putAt("Activity",
						"TestActivity") putAt("Thread", "8"));
					Trace!WriteQuantity("quantity1a.eqf", new(Set) add("trace1.etf"));
					Trace!Result("CPU_Usage", 0.5076471864537928);
					Trace!Result("Memory1", 0.8306952941501128);
					Trace!Result("Memory2", 0.8379125403722565);
					Trace!Result("Latency", 11261.635700000095);
					Trace!WriteQuantity("quantity1b.eqf", new(Set) add("trace1.etf"));
					Trace!Result("CPU_Usage", 0.5904346596943799);
					Trace!Result("Memory1", 0.8373007948980877);
					Trace!Result("Memory2", 0.338276447163645);
					Trace!Result("Latency", 9628.774466666566)
			
			// Examplifies the approach of using the Trace data class for access to TRACE through files
			process class TraceExample2
			ports
			
			messages
			
			variables
				Trace : Trace,
				ID, ID1, ID2 : Integer,
				Claim : String
			init
				Run()()
			methods
				Run()()
					Trace := new(Trace);
					Trace time(0, "s", "%5g");
					Trace attribute("Name", true, false, false, true, true);
					Trace attribute("Core", true, false, false, true, true);
					Trace attribute("Package", true, false, false, true, true);
					Trace attribute("Activity", true, false, false, true, true);
					Trace attribute("Thread", true, false, false, true, true);
					Trace attribute("Connection", true, false, false, true, true);
					Trace attribute("Event", true, false, false, true, true);
					Trace attribute("Relevance", false, false, true, false, false);
					Trace context("CPU", new(Set) add("Name") add("Core"));
					Trace context("GPU", new(Set) add("Name"));
					Trace context("Bus", new(Set) add("Name"));
					Trace context("Memory", new(Set) add("Name"));
					Trace context("Task", new(Set) add("Package") add("Activity") add("Thread"));
					Trace context("Message", new(Set) add("Connection"));
					Trace context("Storage", new(Set) add("Package") add("Activity") add("Thread"));
					Trace context("User", new(Set) add("Name"));
					Trace context("UserEvent", new(Set) add("Event") add("Package"));
					Trace context("Dependency", new(Set) add("Name") add("Relevance"));
					Trace color("Start", "009955");
					Trace color("Kill", "990000");
					Trace filter("Relevance", new(Set) add("cross-reference"));
					Trace quantity("CPU_Usage", "%");
					Trace quantity("Memory1", "MB");
					Trace quantity("Memory2", "MB");
					Trace quantity("Latency", "ms");
					Trace writeConfiguration("configuration.txt");
					Trace writeTrace("trace2.etf");
					Trace write(Trace amountResource("Memory1", 4096000, "B", "Memory", new(Map) putAt("Name", "Memory1")));
					Trace write(Trace amountResource("Memory2", 4096000, "B", "Memory", new(Map) putAt("Name", "Memory2")));
					Trace write(Trace fullResource("Adreno", 100, "%", "GPU", new(Map) putAt("Name", "Adreno")));
					Trace write(Trace amountResource("GMem", 524288, "B", "Memory", new(Map) putAt("Name", "GMem")));
					Trace write(Trace fullResource("User", 1, "E", "User", new(Map) putAt("Name", "User")));
					Trace write(Trace fullResource("MultiMediaFabric", 100, "%", "Bus", new(Map) putAt("Name", "MultiMediaFabic")));
					Trace write(Trace amountResource("L2 Cache", 2097152, "B", "Memory", new(Map) putAt("Name", "L2 Cache")));
					Trace write(Trace fullResource("AppsFabric", 100, "%", "Bus", new(Map) putAt("Name", "AppsFabric")));
					Trace write(Trace fullResource("Krait1", 100, "%", "CPU", new(Map) putAt("Name", "Krait") putAt("Core", "1")));
					Trace write(Trace fullResource("Krait2", 100, "%", "CPU", new(Map) putAt("Name", "Krait") putAt("Core", "2")));
					Claim := Trace fullClaim("User", 1.3, 20.3, "UserEvent", new(Map) putAt("Event", "Start") putAt("Package",
						"user.event1"));
					Trace write(Claim);
					ID1 := Claim splitOn('\t') at(2) toInteger;
					Trace write(Trace fullClaim("MultiMediaFabric", 1.3, 15.3, "Message", new(Map) putAt("Connection",
						"GPU -> DDR")));
					ID := Trace fullClaimStart("AppsFabric", 5.3, "Message", new(Map) putAt("Connection", "CPU -> DDR"));
					Trace write(Trace fullClaimStop(ID, 10.3));
					Claim := Trace fullClaim("AppsFabric", 11.3, 17.3, "Message", new(Map) putAt("Connection", "GPU -> DDR"));
					Trace write(Claim);
					ID2 := Claim splitOn('\t') at(2) toInteger;
					Trace write(Trace dependency(ID1, ID2, 0, "Dependency", new(Map) putAt("Name", "TaskDependency1")
						putAt("Relevance", "cross-reference")));
					ID := Trace amountClaimStart("Memory1", 5.5, "Storage", new(Map) putAt("Package", "package") putAt("Activity",
						"activity") putAt("Thread", "2"), 1000000);
					Trace write(Trace amountClaimStop(ID, 16.3));
					Claim := Trace amountClaim("Memory2", 5.3, 18.3, "Storage", new(Map) putAt("Package", "Package")
						putAt("Activity", "activity") putAt("Thread", "1"), 1908234);
					Trace write(Claim);
					ID1 := Claim splitOn('\t') at(2) toInteger;
					Claim := Trace fullClaim("AppsFabric", 17.3, 25.3, "Message", new(Map) putAt("Connection", "CPU -> DDR"));
					Trace write(Claim);
					ID := Claim splitOn('\t') at(2) toInteger;
					Trace write(Trace amountClaim("Memory1", 20.5, 40.5, "Storage", new(Map) putAt("Package", "package")
						putAt("Activity", "activity") putAt("Thread", "3"), 3500000));
					Claim := Trace amountClaim("Memory2", 19.3, 29.3, "Storage", new(Map) putAt("Package", "package")
						putAt("Activity", "activity") putAt("Thread", "4"), 1581100);
					Trace write(Claim);
					ID2 := Claim splitOn('\t') at(2) toInteger;
					Trace write(Trace dependency(ID1, ID2, 0, "Dependency", new(Map) putAt("Name", "TaskDependency2")
						putAt("Relevance", "cross-reference")));
					Trace write(Trace fullClaim("User", 20.52, 30.52, "UserEvent", new(Map) putAt("Event", "Start") putAt("Package",
						"user.event2")));
					Claim := Trace fullClaim("Adreno", 15.3, 30.6, "Task", new(Map) putAt("Package", "performance.text")
						putAt("Activity", "test.activity") putAt("Thread", "0"));
					Trace write(Claim);
					ID2 := Claim splitOn('\t') at(2) toInteger;
					Trace write(Trace dependency(ID, ID2, 1, "Dependency", new(Map) putAt("Name", "TaskDependency3")
						putAt("Relevance", "cross-reference")));
					Trace write(Trace amountClaim("GMem", 10.3, 30.86, "Storage", new(Map) putAt("Package", "package")
						putAt("Activity", "activity") putAt("Thread", "1"), 225000));
					Trace write(Trace fullClaim("Krait1", 1.302, 16.69, "Task", new(Map) putAt("Package", "performance.text")
						putAt("Activity", "TestActivity") putAt("Thread", "5")));
					Trace write(Trace fullClaim("Krait1", 17.302, 20.69, "Task", new(Map) putAt("Package", "performance.text")
						putAt("Activity", "TestActivity") putAt("Thread", "6")));
					Trace write(Trace fullClaim("Krait2", 2.9, 13.69, "Task", new(Map) putAt("Package", "performance.text")
						putAt("Activity", "TestActivity") putAt("Thread", "7")));
					Trace write(Trace fullClaim("Krait2", 15.302, 28.69, "Task", new(Map) putAt("Package", "performance.text")
						putAt("Activity", "TestActivity") putAt("Thread", "8")));
					Trace writeQuantity("quantity2a.eqf", new(Set) add("trace2.etf"));
					Trace result("CPU_Usage", 0.5076471864537928);
					Trace result("Memory1", 0.8306952941501128);
					Trace result("Memory2", 0.8379125403722565);
					Trace result("Latency", 11261.635700000095);
					Trace writeQuantity("quantity2b.eqf", new(Set) add("trace2.etf"));
					Trace result("CPU_Usage", 0.5904346596943799);
					Trace result("Memory1", 0.8373007948980877);
					Trace result("Memory2", 0.338276447163645);
					Trace result("Latency", 9628.774466666566)
			
			cluster class TraceExample(HostName : String, PortNumber : Integer)
			ports
			
			instances
				TraceTest1 : TraceExample1(Streaming := PortNumber != nil)
				TraceTest2 : TraceExample2()
				TraceProcess : TraceProcess(HostName := HostName, PortNumber := PortNumber)
			channels
				{ TraceTest1.Trace, TraceProcess.Trace }
			
			system
			instances
				TraceExample : TraceExample(HostName := nil, PortNumber := nil) // Using files only
				// TraceExample: TraceExample(HostName := "localhost", PortNumber := 9292)	// These are the default settings in TRACE in case of streaming
			channels'''			
		]
	}

}
