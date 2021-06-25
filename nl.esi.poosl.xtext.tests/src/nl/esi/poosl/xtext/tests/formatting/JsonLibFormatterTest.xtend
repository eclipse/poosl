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
class JsonLibFormatterTest {

	@Inject extension FormatterTestHelper

	@Test 
	def void format() {

		assertFormatted[
			toBeFormatted='''
			import "structures.poosl"
			
			/* This data class converts JSON-compatible POOSL data structures to JSON strings and vice versa */
			data class JSON
			variables Input: String, Pointer: Integer
			methods
			// Methods for End-Users
			
			/* Parses a JSON-compatible string to a POOSL data structure */
			parse(S: String) : Object |POOSL: Object|
				if (S = nil) | (S isOfType("String") not) then self error("Parameter of method \"parse\" for JSON must be a String") fi;
				if S = "" then self error("Parameter of method \"parse\" for JSON must be a non-empty String") fi;
				Input := S; Pointer := 1;
				POOSL := self parseAny;
				Input := nil; Pointer := nil;
				return POOSL
			
			/* Converts a JSON-compatible POOSL data structure to a JSON string */
			convert(O: Object) : String |JSON: String|
				if O = nil then
					JSON := "null"
				else
					if (O isOfType("Boolean") | O isOfType("Integer") | O isOfType("Real")) then
						JSON := O printString
					else if O isOfType("String") then
						JSON := self convertString(O)
					else if O isOfType("Array") then
						JSON := self convertArray(O)
					else if O isOfType("Map") then
						JSON := self convertMap(O)
					else
						self error("Parameter " + O printString + " for method \"convert\" of JSON is not JSON-compatible")
					fi fi fi fi
				fi;	
				return JSON
			
			/* Converts a POOSL String to a JSON string */
			convertString(S: String) : String |JSON: String, Symbol: Char, i: Integer|
				if (S = nil) | (S isOfType("String") not) then self error("Parameter for method \"convertString\" of JSON must be a String") fi;
				JSON := "\""; i := 1;
				while i <= S size do
					Symbol := S at(i);
					if Symbol = '\\' then
						JSON concat("\\\\")
					else if Symbol = '"' then
						JSON concat("\\\"")
					else
						JSON concat(Symbol asString)
					fi fi;
					i := i + 1
				od;
				return JSON concat("\"")
			
			/* Converts a POOSL Array to a JSON string */
			convertArray(A: Array) : String |JSON: String, i: Integer|
				if (A = nil) | (A isOfType("Array") not) then self error("Parameter for method \"convertArray\" of JSON must be an Array") fi;
				JSON := "["; i := 1;
				while i <= A size do
					JSON concat(self convert(A at(i)));
					if i < A size then JSON concat(",") fi;
					i := i + 1
				od;
				return JSON concat("]")
				
			/* Converts a POOSL Map to a JSON string */
			convertMap(M: Map) : String |JSON: String, i: Iterator|
				if (M = nil) | (M isOfType("Map") not) then self error("Parameter for method \"convertMap\" of JSON must be a Map") fi;
				JSON := "{"; i := M iterator;
				while i isDone not do
					if (i key = nil) | (i key isOfType("String") not) then self error("Key \"" + i key printString + "\" in Map parameter for method \"convertMap\" of JSON must be a String") fi;
					JSON concat(self convert(i key)) concat(":") concat(self convert(i value));
					i advance;
					if i isDone not then JSON concat(",") fi
				od;
				return JSON concat("}")
			
			// Methods for implementation
			
			parseAny : Object |Origin: Integer, Symbol: Char, Any: Object|
				if (Input = nil) | (Pointer = nil) then self error("Direct use of method \"parseObject\" of JSON: use method \"parse\" of JSON instead") fi;
				Origin := Pointer;
				self parseWhiteSpace;
				if Pointer <= (Input size) then
					Symbol := Input at(Pointer);
					if Symbol = 't' then
						if Input size < Pointer + 3 then self error("Invalid boolean true detected at end of JSON string") fi;
						if (Input at(Pointer + 1) != 'r') | (Input at(Pointer + 2) != 'u') | (Input at(Pointer + 3) != 'e') then self error("Invalid boolean true detected from index " + Pointer printString + " of JSON string") fi;
						Any := true;
						Pointer := Pointer + 4
					else if Symbol = 'f' then
						if Input size < Pointer + 4 then self error("Invalid boolean false detected at end of JSON string") fi;
						if (Input at(Pointer + 1) != 'a') | (Input at(Pointer + 2) != 'l') | (Input at(Pointer + 3) != 's') | (Input at(Pointer + 4) != 'e') then self error("Invalid boolean false detected from index " + Pointer printString + " of JSON string") fi;
						Any := false;
						Pointer := Pointer + 5
					else if Symbol = 'n' then
						if Input size < Pointer + 3 then self error("Invalid value null detected at end of JSON string") fi;
						if (Input at(Pointer + 1) != 'u') | (Input at(Pointer + 2) != 'l') | (Input at(Pointer + 3) != 'l') then self error("Invalid value null detected from index " + Pointer printString + " of JSON string") fi;
						Any := nil;
						Pointer := Pointer + 4
					else if (Symbol = '+') | (Symbol = '-') | ((Symbol asciiIndex > 47) & (Symbol asciiIndex < 58)) then
						Any := self parseNumber
					else if Symbol = '"' then
						Any := self parseString
					else if Symbol = '[' then
						Any := self parseArray
					else if Symbol = '{' then
						Any := self parseMap
					fi fi fi fi fi fi fi
				else
					self error("Invalid number detected from index " + Origin printString + " in JSON string")
				fi;
				return Any
			
			parseWhiteSpace : JSON |Symbol: Char, NF: Boolean|
				if (Input = nil) | (Pointer = nil) then self error("Direct use of method \"parseWhiteSpace\" of JSON: use method \"parse\" of JSON instead") fi;
				NF := true;
				while NF & (Pointer <= Input size) do
					Symbol := Input at(Pointer);
					if (Symbol = ' ') | (Symbol = '\t') | (Symbol = '\r') | (Symbol = '\f') | (Symbol = '\n') then
						Pointer := Pointer + 1 
					else
						NF := false
					fi	
				od;
				return self
			
			filterNumber : String |Symbol: Char, Origin: Integer, NF: Boolean|
				if (Input = nil) | (Pointer = nil) then self error("Direct use of method \"filterNumber\" of JSON: use method \"parse\" of JSON instead") fi;
				Origin := Pointer; NF := true;
				while NF & (Pointer <= Input size) do
					Symbol := Input at(Pointer);
					if (Symbol asciiIndex > 47) & (Symbol asciiIndex < 58) then Pointer := Pointer + 1 else NF := false fi
				od;
				if Pointer <= Origin then self error("Invalid number detected from index " + Pointer printString + " of JSON string") fi;
				return Input subString(Origin, Pointer - Origin)
			
			parseNumber : Object |Symbol: Char, Number: Object, NumberAsString: String, Origin: Integer, Decimal: Boolean|
				if (Input = nil) | (Pointer = nil) then self error("Direct use of method \"parseNumber\" of JSON: use method \"parse\" of JSON instead") fi;
				Origin := Pointer; Decimal := false;
				NumberAsString := "";
				Symbol := Input at(Pointer);
				if (Symbol = '-') | (Symbol = '+') then
					if Symbol = '-' then NumberAsString concat(Symbol asString) fi;
					Pointer := Pointer + 1
				fi;
				NumberAsString concat(self filterNumber);
				if Pointer <= Input size then
					Symbol := Input at(Pointer);
					if Symbol = '.' then
						Decimal := true;
						NumberAsString concat(Symbol asString);
						Pointer := Pointer + 1;
						NumberAsString concat(self filterNumber)
					fi
				else
					self error("Incomplete number detected from index " + Pointer printString + " in JSON string")
				fi;
				if Pointer <= Input size then
					Symbol := Input at(Pointer);
					if (Symbol = 'e') | (Symbol = 'E') then
						Pointer := Pointer + 1;
						if Pointer <= Input size then
							Symbol := Input at(Pointer);
							if Symbol = '-' then
								if !Decimal then NumberAsString concat(".0e-") else NumberAsString concat("e-") fi;
								Pointer := Pointer + 1
							else if Symbol = '+' then
								NumberAsString concat("e");
								Pointer := Pointer + 1
							else
								NumberAsString concat("e")
							fi fi;
							NumberAsString concat(self filterNumber)
						else
							self error("Incomplete number detected from index " + Pointer printString + " in JSON string")
						fi
					fi
				fi;
				if NumberAsString isInteger then
					Number := NumberAsString toInteger
				else if NumberAsString isReal then
					Number := NumberAsString toReal
				else
					self error("Invalid number detected from index " + Origin printString + " in JSON string")
				fi fi;
				return Number
			
			parseHexDigit(Symbol: Char) : Integer
				if (Input = nil) | (Pointer = nil) then self error("Direct use of method \"parseString\" of JSON: use method \"parse\" of JSON instead") fi;
				if (Symbol asciiIndex > 47) & (Symbol asciiIndex < 58) then
					return Symbol asString toInteger
				else if (Symbol = 'a') | (Symbol = 'A') then
					return 10
				else if (Symbol = 'b') | (Symbol = 'B') then
					return 11
				else if (Symbol = 'c') | (Symbol = 'C') then
					return 12
				else if (Symbol = 'd') | (Symbol = 'D') then
					return 13
				else if (Symbol = 'e') | (Symbol = 'E') then
					return 14
				else if (Symbol = 'f') | (Symbol = 'F') then
					return 15
				else
					return nil
				fi fi fi fi fi fi fi
			
			parseString : String |Symbol: Char, NF: Boolean, S: String, Third, Forth: Integer|
				if (Input = nil) | (Pointer = nil) then self error("Direct use of method \"parseString\" of JSON: use method \"parse\" of JSON instead") fi;
				Pointer := Pointer + 1; S := ""; NF := true;
				while NF & (Pointer <= Input size) do
					Symbol := Input at(Pointer);
					if (Symbol asciiIndex > 0) & (Symbol asciiIndex < 256) then
						if Symbol = '\\' then
							if ((Pointer + 1) <= (Input size)) then
								Pointer := Pointer + 1;
								Symbol := Input at(Pointer)
							fi;
							if Symbol = '"' then
								S concat("\"")
							else if Symbol = '\\' then
								S concat("\\")
							else if Symbol = '\'' then
								S concat("'")
							else if Symbol = "/" then
								S concat("/")
							else if Symbol = 'b' then
								S concat("\b")
							else if Symbol = 'f' then
								S concat("\f")
							else if Symbol = 'n' then
								S concat("\n")
							else if Symbol = 'r' then
								S concat("\r")
							else if Symbol = 't' then
								S concat("\t")
							else if Symbol = 'u' then
								if ((Pointer + 4) > (Input size)) then self error("Incomplete unicode escape sequence detected at index " + Pointer printString + " in JSON string") fi;
								if (Input at(Pointer + 1) != '0') | (Input at(Pointer + 2)  != '0') then self error("Unsupported unicode escape sequence detected at index " + Pointer printString + " in JSON string") fi;
								Third := self parseHexDigit(Input at(Pointer + 3)); Forth := self parseHexDigit(Input at(Pointer + 4));
								if (Third = nil) | (Forth = nil) then self error("Invalid unicode escape sequence detected at index " + Pointer printString + " in JSON string") fi;
								S concat(((Third * 16) + Forth) asAsciiChar() asString);
								Pointer := Pointer + 4 
							else
								S concat(Symbol asString)
							fi fi fi fi fi fi fi fi fi fi
						else
							if Symbol = '"' then
								NF := false
							else
								S concat(Symbol asString)
							fi
						fi
					else
						self error("Unsupported character detected at index " + Pointer printString + " in JSON string")
					fi;
					Pointer := Pointer + 1
				od;
				return S
			
			parseArray : Array |Origin: Integer, Symbol: Char, NF: Boolean, A: Array|
				if (Input = nil) | (Pointer = nil) then self error("Direct use of method \"parseArray\" of JSON: use method \"parse\" of JSON instead") fi;
				Origin := Pointer; Pointer := Pointer + 1;
				A := new(Array);
				self parseWhiteSpace;
				if Pointer <= Input size then
					if Input at(Pointer) != ']' then
						NF := true;
						while NF & (Pointer <= Input size) do
							A resize(A size + 1);
							A putAt(A size, self parseAny);
							self parseWhiteSpace;
							if Pointer <= Input size then
								Symbol := Input at(Pointer);
								if Symbol = ',' then
									Pointer := Pointer + 1
								else if Symbol = ']' then
									NF := false
								else
									self error("Invalid array detected from index " + Pointer printString + " in JSON string")
								fi fi
							else
								self error("Incomplete array detected from index " + Origin printString + " in JSON string")
							fi
						od
					fi
				else
					self error("Incomplete array detected from index " + Origin printString + " in JSON string")
				fi;
				Pointer := Pointer + 1;
				return A
			
			parseMap : Map |Origin: Integer, Key: String, Symbol: Char, NF: Boolean, M: Map|
				if (Input = nil) | (Pointer = nil) then self error("Direct use of method \"parseMap\" of JSON: use method \"parse\" of JSON instead") fi;
				Origin := Pointer; Pointer := Pointer + 1;
				M := new(Map);
				self parseWhiteSpace;
				if Pointer <= Input size then
					if Input at(Pointer) != '}' then
						NF := true;
						while NF & (Pointer <= Input size) do
							self parseWhiteSpace;
							Key := self parseString;
							self parseWhiteSpace;
							if Pointer <= Input size then
								if Input at(Pointer) = ':' then
									Pointer := Pointer + 1
								else
									self error("Invalid dictionary detected from index " + Pointer printString + " in JSON string")
								fi
							else
								self error("Incomplete dictionary detected from index " + Origin printString + " in JSON string")
							fi;
							self parseWhiteSpace;
							M putAt(Key, self parseAny);
							self parseWhiteSpace;
							if Pointer <= Input size then
								Symbol := Input at(Pointer);
								if Symbol = ',' then
									Pointer := Pointer + 1
								else if Symbol = '}' then
									NF := false
								else
									self error("Invalid dictionary detected from index " + Pointer printString + " in JSON string")
								fi fi
							else
								self error("Incomplete dictionary detected from index " + Origin printString + " in JSON string")
							fi
						od
					fi
				else
					self error("Incomplete dictionary detected from index " + Origin printString + " in JSON string")
				fi;
				Pointer := Pointer + 1;
				return M'''
			
			expectation ='''
			import "structures.poosl"
			
			/* This data class converts JSON-compatible POOSL data structures to JSON strings and vice versa */
			data class JSON
			variables
				Input : String,
				Pointer : Integer
			methods
			// Methods for End-Users
				/* Parses a JSON-compatible string to a POOSL data structure */
				parse(S : String) : Object | POOSL : Object |
					if (S = nil) | (S isOfType("String") not) then
						self error("Parameter of method \"parse\" for JSON must be a String")
					fi;
					if S = "" then
						self error("Parameter of method \"parse\" for JSON must be a non-empty String")
					fi;
					Input := S;
					Pointer := 1;
					POOSL := self parseAny;
					Input := nil;
					Pointer := nil;
					return POOSL
			
				/* Converts a JSON-compatible POOSL data structure to a JSON string */
				convert(O : Object) : String | JSON : String |
					if O = nil then
						JSON := "null"
					else if (O isOfType("Boolean") | O isOfType("Integer") | O isOfType("Real")) then
						JSON := O printString
					else if O isOfType("String") then
						JSON := self convertString(O)
					else if O isOfType("Array") then
						JSON := self convertArray(O)
					else if O isOfType("Map") then
						JSON := self convertMap(O)
					else
						self error("Parameter " + O printString + " for method \"convert\" of JSON is not JSON-compatible")
					fi fi fi fi fi;
					return JSON
			
				/* Converts a POOSL String to a JSON string */
				convertString(S : String) : String | JSON : String, Symbol : Char, i : Integer |
					if (S = nil) | (S isOfType("String") not) then
						self error("Parameter for method \"convertString\" of JSON must be a String")
					fi;
					JSON := "\"";
					i := 1;
					while i <= S size do
						Symbol := S at(i);
						if Symbol = '\\' then
							JSON concat("\\\\")
						else if Symbol = '"' then
							JSON concat("\\\"")
						else
							JSON concat(Symbol asString)
						fi fi;
						i := i + 1
					od;
					return JSON concat("\"")
			
				/* Converts a POOSL Array to a JSON string */
				convertArray(A : Array) : String | JSON : String, i : Integer |
					if (A = nil) | (A isOfType("Array") not) then
						self error("Parameter for method \"convertArray\" of JSON must be an Array")
					fi;
					JSON := "[";
					i := 1;
					while i <= A size do
						JSON concat(self convert(A at(i)));
						if i < A size then
							JSON concat(",")
						fi;
						i := i + 1
					od;
					return JSON concat("]")
			
				/* Converts a POOSL Map to a JSON string */
				convertMap(M : Map) : String | JSON : String, i : Iterator |
					if (M = nil) | (M isOfType("Map") not) then
						self error("Parameter for method \"convertMap\" of JSON must be a Map")
					fi;
					JSON := "{";
					i := M iterator;
					while i isDone not do
						if (i key = nil) | (i key isOfType("String") not) then
							self error("Key \"" + i key printString +
								"\" in Map parameter for method \"convertMap\" of JSON must be a String")
						fi;
						JSON concat(self convert(i key)) concat(":") concat(self convert(i value));
						i advance;
						if i isDone not then
							JSON concat(",")
						fi
					od;
					return JSON concat("}")
			
			// Methods for implementation
				parseAny : Object | Origin : Integer, Symbol : Char, Any : Object |
					if (Input = nil) | (Pointer = nil) then
						self error("Direct use of method \"parseObject\" of JSON: use method \"parse\" of JSON instead")
					fi;
					Origin := Pointer;
					self parseWhiteSpace;
					if Pointer <= (Input size) then
						Symbol := Input at(Pointer);
						if Symbol = 't' then
							if Input size < Pointer + 3 then
								self error("Invalid boolean true detected at end of JSON string")
							fi;
							if (Input at(Pointer + 1) != 'r') | (Input at(Pointer + 2) != 'u') | (Input at(Pointer + 3) != 'e') then
								self error("Invalid boolean true detected from index " + Pointer printString + " of JSON string")
							fi;
							Any := true;
							Pointer := Pointer + 4
						else if Symbol = 'f' then
							if Input size < Pointer + 4 then
								self error("Invalid boolean false detected at end of JSON string")
							fi;
							if (Input at(Pointer + 1) != 'a') | (Input at(Pointer + 2) != 'l') | (Input at(Pointer + 3) != 's') |
									(Input at(Pointer + 4) != 'e') then
								self error("Invalid boolean false detected from index " + Pointer printString + " of JSON string")
							fi;
							Any := false;
							Pointer := Pointer + 5
						else if Symbol = 'n' then
							if Input size < Pointer + 3 then
								self error("Invalid value null detected at end of JSON string")
							fi;
							if (Input at(Pointer + 1) != 'u') | (Input at(Pointer + 2) != 'l') | (Input at(Pointer + 3) != 'l') then
								self error("Invalid value null detected from index " + Pointer printString + " of JSON string")
							fi;
							Any := nil;
							Pointer := Pointer + 4
						else if (Symbol = '+') | (Symbol = '-') | ((Symbol asciiIndex > 47) & (Symbol asciiIndex < 58)) then
							Any := self parseNumber
						else if Symbol = '"' then
							Any := self parseString
						else if Symbol = '[' then
							Any := self parseArray
						else if Symbol = '{' then
							Any := self parseMap
						fi fi fi fi fi fi fi
					else
						self error("Invalid number detected from index " + Origin printString + " in JSON string")
					fi;
					return Any
			
				parseWhiteSpace : JSON | Symbol : Char, NF : Boolean |
					if (Input = nil) | (Pointer = nil) then
						self error("Direct use of method \"parseWhiteSpace\" of JSON: use method \"parse\" of JSON instead")
					fi;
					NF := true;
					while NF & (Pointer <= Input size) do
						Symbol := Input at(Pointer);
						if (Symbol = ' ') | (Symbol = '\t') | (Symbol = '\r') | (Symbol = '\f') | (Symbol = '\n') then
							Pointer := Pointer + 1
						else
							NF := false
						fi
					od;
					return self
			
				filterNumber : String | Symbol : Char, Origin : Integer, NF : Boolean |
					if (Input = nil) | (Pointer = nil) then
						self error("Direct use of method \"filterNumber\" of JSON: use method \"parse\" of JSON instead")
					fi;
					Origin := Pointer;
					NF := true;
					while NF & (Pointer <= Input size) do
						Symbol := Input at(Pointer);
						if (Symbol asciiIndex > 47) & (Symbol asciiIndex < 58) then
							Pointer := Pointer + 1
						else
							NF := false
						fi
					od;
					if Pointer <= Origin then
						self error("Invalid number detected from index " + Pointer printString + " of JSON string")
					fi;
					return Input subString(Origin, Pointer - Origin)
			
				parseNumber : Object | Symbol : Char, Number : Object, NumberAsString : String, Origin : Integer,
						Decimal : Boolean |
					if (Input = nil) | (Pointer = nil) then
						self error("Direct use of method \"parseNumber\" of JSON: use method \"parse\" of JSON instead")
					fi;
					Origin := Pointer;
					Decimal := false;
					NumberAsString := "";
					Symbol := Input at(Pointer);
					if (Symbol = '-') | (Symbol = '+') then
						if Symbol = '-' then
							NumberAsString concat(Symbol asString)
						fi;
						Pointer := Pointer + 1
					fi;
					NumberAsString concat(self filterNumber);
					if Pointer <= Input size then
						Symbol := Input at(Pointer);
						if Symbol = '.' then
							Decimal := true;
							NumberAsString concat(Symbol asString);
							Pointer := Pointer + 1;
							NumberAsString concat(self filterNumber)
						fi
					else
						self error("Incomplete number detected from index " + Pointer printString + " in JSON string")
					fi;
					if Pointer <= Input size then
						Symbol := Input at(Pointer);
						if (Symbol = 'e') | (Symbol = 'E') then
							Pointer := Pointer + 1;
							if Pointer <= Input size then
								Symbol := Input at(Pointer);
								if Symbol = '-' then
									if !Decimal then
										NumberAsString concat(".0e-")
									else
										NumberAsString concat("e-")
									fi;
									Pointer := Pointer + 1
								else if Symbol = '+' then
									NumberAsString concat("e");
									Pointer := Pointer + 1
								else
									NumberAsString concat("e")
								fi fi;
								NumberAsString concat(self filterNumber)
							else
								self error("Incomplete number detected from index " + Pointer printString + " in JSON string")
							fi
						fi
					fi;
					if NumberAsString isInteger then
						Number := NumberAsString toInteger
					else if NumberAsString isReal then
						Number := NumberAsString toReal
					else
						self error("Invalid number detected from index " + Origin printString + " in JSON string")
					fi fi;
					return Number
			
				parseHexDigit(Symbol : Char) : Integer
					if (Input = nil) | (Pointer = nil) then
						self error("Direct use of method \"parseString\" of JSON: use method \"parse\" of JSON instead")
					fi;
					if (Symbol asciiIndex > 47) & (Symbol asciiIndex < 58) then
						return Symbol asString toInteger
					else if (Symbol = 'a') | (Symbol = 'A') then
						return 10
					else if (Symbol = 'b') | (Symbol = 'B') then
						return 11
					else if (Symbol = 'c') | (Symbol = 'C') then
						return 12
					else if (Symbol = 'd') | (Symbol = 'D') then
						return 13
					else if (Symbol = 'e') | (Symbol = 'E') then
						return 14
					else if (Symbol = 'f') | (Symbol = 'F') then
						return 15
					else
						return nil
					fi fi fi fi fi fi fi
			
				parseString : String | Symbol : Char, NF : Boolean, S : String, Third, Forth : Integer |
					if (Input = nil) | (Pointer = nil) then
						self error("Direct use of method \"parseString\" of JSON: use method \"parse\" of JSON instead")
					fi;
					Pointer := Pointer + 1;
					S := "";
					NF := true;
					while NF & (Pointer <= Input size) do
						Symbol := Input at(Pointer);
						if (Symbol asciiIndex > 0) & (Symbol asciiIndex < 256) then
							if Symbol = '\\' then
								if ((Pointer + 1) <= (Input size)) then
									Pointer := Pointer + 1;
									Symbol := Input at(Pointer)
								fi;
								if Symbol = '"' then
									S concat("\"")
								else if Symbol = '\\' then
									S concat("\\")
								else if Symbol = '\'' then
									S concat("'")
								else if Symbol = "/" then
									S concat("/")
								else if Symbol = 'b' then
									S concat("\b")
								else if Symbol = 'f' then
									S concat("\f")
								else if Symbol = 'n' then
									S concat("\n")
								else if Symbol = 'r' then
									S concat("\r")
								else if Symbol = 't' then
									S concat("\t")
								else if Symbol = 'u' then
									if ((Pointer + 4) > (Input size)) then
										self error("Incomplete unicode escape sequence detected at index " + Pointer printString +
											" in JSON string")
									fi;
									if (Input at(Pointer + 1) != '0') | (Input at(Pointer + 2) != '0') then
										self error("Unsupported unicode escape sequence detected at index " + Pointer printString +
											" in JSON string")
									fi;
									Third := self parseHexDigit(Input at(Pointer + 3));
									Forth := self parseHexDigit(Input at(Pointer + 4));
									if (Third = nil) | (Forth = nil) then
										self error("Invalid unicode escape sequence detected at index " + Pointer printString +
											" in JSON string")
									fi;
									S concat(((Third * 16) + Forth) asAsciiChar() asString);
									Pointer := Pointer + 4
								else
									S concat(Symbol asString)
								fi fi fi fi fi fi fi fi fi fi
							else if Symbol = '"' then
								NF := false
							else
								S concat(Symbol asString)
							fi fi
						else
							self error("Unsupported character detected at index " + Pointer printString + " in JSON string")
						fi;
						Pointer := Pointer + 1
					od;
					return S
			
				parseArray : Array | Origin : Integer, Symbol : Char, NF : Boolean, A : Array |
					if (Input = nil) | (Pointer = nil) then
						self error("Direct use of method \"parseArray\" of JSON: use method \"parse\" of JSON instead")
					fi;
					Origin := Pointer;
					Pointer := Pointer + 1;
					A := new(Array);
					self parseWhiteSpace;
					if Pointer <= Input size then
						if Input at(Pointer) != ']' then
							NF := true;
							while NF & (Pointer <= Input size) do
								A resize(A size + 1);
								A putAt(A size, self parseAny);
								self parseWhiteSpace;
								if Pointer <= Input size then
									Symbol := Input at(Pointer);
									if Symbol = ',' then
										Pointer := Pointer + 1
									else if Symbol = ']' then
										NF := false
									else
										self error("Invalid array detected from index " + Pointer printString + " in JSON string")
									fi fi
								else
									self error("Incomplete array detected from index " + Origin printString + " in JSON string")
								fi
							od
						fi
					else
						self error("Incomplete array detected from index " + Origin printString + " in JSON string")
					fi;
					Pointer := Pointer + 1;
					return A
			
				parseMap : Map | Origin : Integer, Key : String, Symbol : Char, NF : Boolean, M : Map |
					if (Input = nil) | (Pointer = nil) then
						self error("Direct use of method \"parseMap\" of JSON: use method \"parse\" of JSON instead")
					fi;
					Origin := Pointer;
					Pointer := Pointer + 1;
					M := new(Map);
					self parseWhiteSpace;
					if Pointer <= Input size then
						if Input at(Pointer) != '}' then
							NF := true;
							while NF & (Pointer <= Input size) do
								self parseWhiteSpace;
								Key := self parseString;
								self parseWhiteSpace;
								if Pointer <= Input size then
									if Input at(Pointer) = ':' then
										Pointer := Pointer + 1
									else
										self error("Invalid dictionary detected from index " + Pointer printString +
											" in JSON string")
									fi
								else
									self error("Incomplete dictionary detected from index " + Origin printString +
										" in JSON string")
								fi;
								self parseWhiteSpace;
								M putAt(Key, self parseAny);
								self parseWhiteSpace;
								if Pointer <= Input size then
									Symbol := Input at(Pointer);
									if Symbol = ',' then
										Pointer := Pointer + 1
									else if Symbol = '}' then
										NF := false
									else
										self error("Invalid dictionary detected from index " + Pointer printString +
											" in JSON string")
									fi fi
								else
									self error("Incomplete dictionary detected from index " + Origin printString +
										" in JSON string")
								fi
							od
						fi
					else
						self error("Incomplete dictionary detected from index " + Origin printString + " in JSON string")
					fi;
					Pointer := Pointer + 1;
					return M'''			
		]
	}

}
