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
class DistributionsLibFormatTest {

	@Inject extension FormatterTestHelper

	@Test 
	def void format() {

		assertFormatted[

			toBeFormatted = '''
			// API version 3.0 - Library of Random Distributions - Embedded Systems Innovations by TNO
			
			/* This library provides several random distributions. Each of them provides method "withParameter" or withParameters
			 * to initialize the distribution, while method "sample" returns a value from the sample space of the distribution
			 */
			
			/* Super Class Distribution */
			data class Distribution extends Object
			variables Random: RandomGenerator
			methods
				/* Returns a pretty print - it is redefined in subclasses */
				printString : String
					return "Unspecified Distribution"
			
				// Method for implementation
				initialise : Distribution
					Random := new(RandomGenerator) randomiseSeed;
					return self
			
			
			/* Bernoulli Distribution */
			data class Bernoulli extends Distribution
			variables SuccessProbability: Real
			methods
				/* Initializes the success probability S for the Bernoulli distribution */
				withParameter(S: Object) : Bernoulli
					self initialise;
					if (S = nil) | ((S isOfType("Integer") not) & (S isOfType("Real") not)) then self error("Parameter of Bernoulli distribution must be an Integer or Real") fi;
					if S isOfType("Integer") then S := S asReal fi;
					if (S < 0.0) | (S > 1.0) then self error("Parameter for Bernoulli distribution must be within the interval [0, 1]") fi;
					SuccessProbability := S;
					return self
			
				/* Returns a sample for the Bernoulli distribution if initialized. Otherwise, it returns nil */
				sample : Boolean
					return if Random != nil then Random random < SuccessProbability else nil fi 
			
				/* Returns a sample for the Bernoulli distribution if initialized. Otherwise, it returns nil - Alternative */
				yieldsSuccess : Boolean
					return self sample 
			
				/* Returns a pretty print */
				printString : String
					return if Random != nil then "Bernoulli(" + SuccessProbability printString + ")" else "Uninitialized Bernoulli" fi
			
			
			/* Beta Distribution (Shape Parameters Only) */
			data class Beta extends Distribution
			variables GammaA, GammaB: Gamma, Alpha, Beta: Real
			methods
				/* Initializes the shape parameters A and B for the Beta distribution */
				withParameters(A, B: Object) : Beta
					self initialise;
					if (A = nil) | ((A isOfType("Integer") not) & (A isOfType("Real") not)) | (B = nil) | ((B isOfType("Integer") not) & (B isOfType("Real") not)) then self error("Shape parameters of Beta distribution must be Integers or Reals") fi;
					if A isOfType("Integer") then A := A asReal fi; if B isOfType("Integer") then B := B asReal fi;
					if (A <= 0.0) | (B <= 0.0) then self error("Shape parameters of Beta distribution should be larger than 0.0") fi;
					Alpha := A; Beta := B;
					GammaA := new(Gamma) withParameters(Alpha, 1.0);
					GammaB := new(Gamma) withParameters(Beta, 1.0);
					return self
			
				/* Returns a sample for the Beta distribution if initialized. Otherwise, it returns nil */
				sample : Real |Sample: Real|
					if Random != nil then
						Sample := GammaA sample;
						return Sample / (Sample + GammaB sample)
					else
						return nil
					fi
			
				/* Returns a pretty print */
				printString : String
					return if Random != nil then "Beta(" + Alpha printString + ", " + Beta printString + ")" else "Uninitialized Beta" fi
			
			
			/* Beta4 Distribution (Shape Parameters and Bounded) */
			data class Beta4 extends Distribution
			variables GammaA, GammaB: Gamma, Alpha, Beta, LowerBound, UpperBound: Real, Fixed: Boolean
			methods
				/* Initializes the shape parameters A and B, the lower bound L and upper bound U for the Beta distribution */
				withParameters(A, B, L, U: Object) : Beta4 |Temp: Real|
					self initialise;
					if (A = nil) | ((A isOfType("Integer") not) & (A isOfType("Real") not)) | (B = nil) | ((B isOfType("Integer") not) & (B isOfType("Real") not)) then self error("Shape parameters of Beta4 distribution must be Integers or Reals") fi;
					if (L = nil) | ((L isOfType("Integer") not) & (L isOfType("Real") not)) then self error("Lower Bound for Beta4 distribution must be an Integer or Real") fi;
					if (U = nil) | ((U isOfType("Integer") not) & (U isOfType("Real") not)) then self error("Upper Bound for Beta4 distribution must be an Integer or Real") fi;
					Alpha := A; Beta := B; LowerBound := L; UpperBound := U;
					Fixed := LowerBound = UpperBound;
					if Fixed not then
						if LowerBound >= UpperBound then Temp := LowerBound; LowerBound := UpperBound; UpperBound := Temp fi;
						if (Alpha <= 0.0) | (Beta <= 0.0) then self error("Shape parameters of Beta4 distribution should be larger than 0.0") fi;
									GammaA := new(Gamma) withParameters(Alpha, 1.0);
									GammaB := new(Gamma) withParameters(Beta, 1.0)
					fi;
					return self
			
				/* Returns a sample for the Beta4 distribution if initialized. Otherwise, it returns nil */
				sample : Real |Sample: Real|
					if Random != nil then
						if Fixed then
										return LowerBound
						else
										Sample := GammaA sample;
										if Sample = 0.0 then return LowerBound else return LowerBound + (UpperBound - LowerBound) * (Sample / (Sample + GammaB sample)) fi
						fi
					else
						return nil
					fi
			
				/* Returns a pretty print */
				printString : String
					return if Random != nil then "Beta4(" + Alpha printString + ", " + Beta printString + ", " + LowerBound printString + ", " + UpperBound printString + ")" else "Uninitialized Beta4" fi
			
			
			/* Discrete Uniform Distribution */
			data class DiscreteUniform extends Distribution
			variables LowerBound, IntervalLength: Integer, Fixed: Boolean
			methods
				/* Initializes the lower bound L and upper bound U for the Uniform distribution */
				withParameters(L, U: Object) : DiscreteUniform |Temp: Integer|
					self initialise; Fixed := L = U;
					if (L = nil) | (L isOfType("Integer") not) | (U = nil) | (U isOfType("Integer") not) then self error("Parameters of Discrete Uniform distribution must be Integers") fi;
					if L > U then Temp := L; L := U; U := Temp fi;
					LowerBound := L;
					IntervalLength := U - L + 1;
					return self
			
				/* Returns a sample for the Discrete Uniform distribution if initialized. Otherwise, it returns nil */
				sample : Integer
					if Random != nil then
						return if Fixed then LowerBound else LowerBound + (Random random * (IntervalLength asReal)) floor asInteger fi
					else
						return nil
					fi
			
				/* Returns a pretty print */
				printString : String
					return if Random != nil then "DiscreteUniform(" + LowerBound printString + ", " + (LowerBound + IntervalLength - 1) printString + ")" else "Uninitialized Discrete Uniform" fi
			
			
			/* Exponential Distribution */
			data class Exponential extends Distribution
			variables Lambda: Real
			methods
				/* Initializes the parameter L for the Exponential distribution */
				withParameter(L: Object) : Exponential
					self initialise;
					if (L = nil) | ((L isOfType("Integer") not) & (L isOfType("Real") not)) then self error("Parameter for Exponential distribution must be an Integer or Real") fi;
					if L isOfType("Integer") then L := L asReal fi;
					if L <= 0.0 then self error("Parameter for Exponential distribution must be positive") fi;
					Lambda := L;
					return self
			
				/* Returns a sample for the Exponential distribution if initialized. Otherwise, it returns nil */
				sample : Real |Sample: Real|
					if Random != nil then
						Sample := -(Random random ln) / Lambda;
						return if Sample > 0.0 then Sample else self sample fi
					else
						return nil
					fi
			
				/* Returns a pretty print */
				printString : String
					return if Random != nil then "Exponential(" + Lambda printString + ")" else "Uninitialized Exponential Distribution" fi
			
			
			/* Gamma Distribution */
			data class Gamma extends Distribution
			variables Alpha, Beta: Real
			methods
				/* Initializes the shape parameter A and scale parameter B for the Gamma distribution */
				withParameters(A, B: Object) : Gamma
					self initialise;
					if (A = nil) | ((A isOfType("Integer") not) & (A isOfType("Real") not))  then self error("Shape parameter of Gamma distribution must be an Integer or a Real") fi;
					if (B = nil) | ((B isOfType("Integer") not) & (B isOfType("Real") not))  then self error("Scale parameter of Gamma distribution must be an Integer or a Real") fi;
					if A isOfType("Integer") then A := A asReal fi; if B isOfType("Integer") then B := B asReal fi;
					if A <= 0.0 then self error("Shape parameter of Gamma distribution should be larger than 0.0") fi;
					if B <= 0.0 then self error("Scale parameter of Gamma distribution should be larger than 0.0") fi;
					Alpha := A; Beta := B;
					return self
			
				/* Returns a sample for the Gamma distribution if initialized. Otherwise, it returns nil*/
				sample : Real |T, ainv, e, b, p, bbb, ccc, u, u1, u2, v, x, z, r, MAGICCONST: Real|
					if Random != nil then
						MAGICCONST := 1.0 + 4.5 ln;
						T := -1.0;
						if Alpha > 1.0 then
										ainv := ((2.0 * Alpha) - 1.0) sqrt;
										bbb := Alpha - (4.0 ln);
										ccc := Alpha + ainv;
										while T < 0.0 do
											u1 := Random random;
											if (1.0e-7 < u1) & (u1 < 0.9999999) then
															u2 := 1.0 - Random random;
															v := ((u1 / (1.0 - u1)) ln) / ainv;
															x := Alpha * (v exp);
															z := u1 * u1 * u2;
															r := bbb + ccc * v - x;
															if ((r + MAGICCONST - 4.5 * z) >= 0.0) | (r >= (z ln)) then T := x * Beta fi
											fi
										od
						fi;
						if Alpha = 1.0 then
										u := Random random;
										while u <= 1.0e-7 do u := Random random od;
										T := -(u ln) * Beta
						fi;
						if Alpha < 1.0 then
							while T < 0.0 do
											u := Random random;
											e := 1.0 exp;
											b := (e + Alpha) / e;
											p := b * u;
											if p <= 1.0 then
														x := p power(1.0 / Alpha)
											else
														x := -(((b - p) / Alpha) ln)
											fi;
											u1 := Random random;
											if p > 1.0 then
														if u1 <= (x power(Alpha - 1.0)) then T := x * Beta fi
											else
														if u1 <= ((-x) exp) then T := x * Beta fi
											fi
										od
						fi;
						return T
					else
						return nil
					fi
			
				/* Returns a pretty print */
				printString : String
					return if Random != nil then "Gamma(" + Alpha printString + ", " + Beta printString + ")" else "Uninitialized Gamma Distribution" fi
			
			
			/* Discrete Distribution */
			data class Discrete extends Distribution
			variables Samples, LowerBounds, UpperBounds, Weights: Array
			methods
				/* Adds Value to the sample space with weight Weight for the Discrete distribution. In case a specific Value is added multiple times, the effective probability on generating Value as sample depends on the total Weight */
				withParameters(Value: Object, Weight: Object) : Discrete |Index, Size: Integer, CumulativeWeight, Total: Real|
					if Random = nil then
					  		self initialise;
					  		Samples := new(Array);
					  		Weights := new(Array);
					  		LowerBounds := new(Array);
					  		UpperBounds := new(Array)
					fi;
					if (Weight = nil) | ((Weight isOfType("Integer") not) & (Weight isOfType("Real") not)) then self error("Weight parameter of Discrete distribution must be an Integer or a Real") fi;
					if Weight isOfType("Integer") then Weight := Weight asReal fi;	
					Size := Samples size + 1;
					Samples resize(Size); Weights resize(Size);
					Samples putAt(Size, Value); Weights putAt(Size, Weight);
					LowerBounds resize(Size); UpperBounds resize(Size);
					Index := 1; Total := 0.0;
					while Index <= Size do
						Total := Total + Weights at(Index);
						Index := Index + 1
					od;
					Index := 1; CumulativeWeight := 0.0;
					while Index <= Size do
					  		LowerBounds putAt(Index, CumulativeWeight/Total);
					  		CumulativeWeight := CumulativeWeight + Weights at(Index);
					  		UpperBounds putAt(Index, CumulativeWeight/Total);
					  		Index := Index + 1
					od;
					return self
			
				/* Returns a sample for the Discrete distribution if initialized. Otherwise, it returns nil*/
				sample : Object |Sample: Real, Result : Object, Index: Integer|
					if Random != nil then
						Sample := Random random;
						Index := 1;
						while (Result = nil) & (Index <= Samples size) do
										if (LowerBounds at(Index) < Sample) & (Sample <= UpperBounds at(Index)) then Result := Samples at(Index) else Index := Index + 1 fi
						od;
						return Result
					else
						return nil
					fi
			
				/* Returns a pretty print */
				printString : String |PrintOut : String, Index: Integer|
					if Random != nil then
					 			PrintOut := "Discrete Distribution\n";
					 			Index := 1;
					 			while Index <= Samples size do
					 						PrintOut := PrintOut + "Value: " + Samples at(Index) printString + " Probability: " + (UpperBounds at(Index) - LowerBounds at(Index)) printString cr;
					 						Index := Index + 1
					 			od
					else
					 			PrintOut := "Uninitialized Discrete Distribution"
					fi;
					return PrintOut
			
			
			/*  Normal Distribution */
			data class Normal extends Distribution
			variables Mean, StandardDeviation: Real
			methods
				/* Initializes the mean M and variance V parameters for the Normal distribution */
				withParameters(M, V: Object) : Normal
					self initialise;
					if (M = nil) | ((M isOfType("Integer") not) & (M isOfType("Real") not)) then self error("Mean parameter of Normal distribution must be an Integer or a Real") fi;
					if (V = nil) | ((V isOfType("Integer") not) & (V isOfType("Real") not)) then self error("Variance parameter of Normal distribution must be an Integer or a Real") fi;
					if M isOfType("Integer") then M := M asReal fi;	 if V isOfType("Integer") then V := V asReal fi;
					Mean := M; StandardDeviation := V sqrt;
					return self
			
				/* Returns a sample for the Normal distribution if initialized. Otherwise, it returns nil*/
				sample : Real |S, U, X, Y: Real|
					if Random != nil then
						S := 1.0;
						while S >= 1.0 do
							X := (2.0 * Random random) - 1.0;
										Y := (2.0 * Random random) - 1.0;
										S := (X * X) + (Y * Y)
						od;
						U := (-2.0 * (S ln) / S) sqrt;
						return Mean + (X * U * StandardDeviation)
					else
						return nil
					fi
			
				/* Returns a pretty print */
				printString : String
					return if Random != nil then "Normal(" + Mean printString + ", " + StandardDeviation sqr printString + ")" else "Uninitialized Normal Distribution" fi
			
			
			/* PERT Distribution */
			data class PERT extends Beta4
			variables Mode: Real
			methods
				/* Initializes the lower bound L, mode M and upper bound U for the PERT distribution */
				withParameters(L, M, U: Object) : PERT |Mean, Std, Temp: Real|
					self initialise;
					if (L = nil) | ((L isOfType("Integer") not) & (L isOfType("Real") not)) then self error("Lower Bound for PERT distribution must be an Integer or a Real") fi;
					if (M = nil) | ((M isOfType("Integer") not) & (M isOfType("Real") not)) then self error("Mode Bound for PERT distribution must be an Integer or a Real") fi;
					if (U = nil) | ((U isOfType("Integer") not) & (U isOfType("Real") not)) then self error("Upper Bound for PERT distribution must be an Integer or a Real") fi;
					if L isOfType("Integer") then L := L asReal fi; if M isOfType("Integer") then M := M asReal fi; if U isOfType("Integer") then U := U asReal fi;
					LowerBound := L; UpperBound := U; Mode := M;
					Fixed := LowerBound = UpperBound;
					if Fixed not then
						if LowerBound > UpperBound then Temp := LowerBound; LowerBound := UpperBound; UpperBound := Temp fi;
									if (LowerBound >= Mode) | (Mode >= UpperBound) then self error("Parameters of PERT distribution do not satisfy LowerBound < Mode < UpperBound or LowerBound = UpperBound") fi;
									Mean := (LowerBound + 4.0 * Mode + UpperBound) / 6.0;
									Std := (UpperBound - LowerBound) / 6.0;
									Alpha := ((Mean - LowerBound)/(UpperBound - LowerBound)) * ((((Mean - LowerBound) * (UpperBound - Mean)) / (Std sqr)) - 1.0);
									Beta := ((UpperBound - Mean) / (Mean - LowerBound)) * Alpha;
									GammaA := new(Gamma) withParameters(Alpha, 1.0);
									GammaB := new(Gamma) withParameters(Beta, 1.0)
					fi;
					return self
			
				/* Returns a pretty print */
				printString : String
					return if Random != nil then "PERT(" + LowerBound printString + ", " + Mode printString + ", " + UpperBound printString + ")" else "Uninitialized PERT Distribution" fi
			
			
			/* Triangle Distribution */
			data class Triangle extends Distribution
			variables LowerBound, Mode, UpperBound: Real, Fixed: Boolean
			methods
				/* Initializes the lower bound L, mode M and upper bound U for the Trangle distribution */
				withParameters(L, M, U: Object) : Triangle |Temp: Real|
					self initialise;
					if (L = nil) | ((L isOfType("Integer") not) & (L isOfType("Real") not)) then self error("Lower Bound for Triangle distribution must be an Integer or a Real") fi;
					if (M = nil) | ((M isOfType("Integer") not) & (M isOfType("Real") not)) then self error("Mode Bound for Triangle distribution must be an Integer or a Real") fi;
					if (U = nil) | ((U isOfType("Integer") not) & (U isOfType("Real") not)) then self error("Upper Bound for Triangle distribution must be an Integer or a Real") fi;
					if L isOfType("Integer") then L := L asReal fi; if M isOfType("Integer") then M := M asReal fi; if U isOfType("Integer") then U := U asReal fi;
					LowerBound := L; UpperBound := U; Mode := M;
					Fixed := LowerBound = UpperBound;
					if Fixed not then
						if LowerBound > UpperBound then Temp := LowerBound; LowerBound := UpperBound; UpperBound := Temp fi;
						if (LowerBound >= Mode) | (Mode >= UpperBound) then self error("Parameters of Triangle distribution do not satisfy LowerBound < Mode < UpperBound or LowerBound = UpperBound") fi
					fi;
					return self
			
				/* Returns a sample for the Triangle distribution if initialized. Otherwise, it returns nil */
				sample : Real |Sample: Real|
					if Random != nil then
						if Fixed then
										return LowerBound
						else
										Sample := Random random;
										if Sample <= (Mode - LowerBound) / (UpperBound - LowerBound) then
													return LowerBound + (Sample * (UpperBound - LowerBound) * (Mode - LowerBound)) sqrt
										else
													return UpperBound - ((1.0 - Sample) * (UpperBound - LowerBound) * (UpperBound - Mode)) sqrt
										fi
						fi
					else
						return nil
					fi
			
				/* Returns a pretty print */
				printString : String
					return if Random != nil then "Triangle(" + LowerBound printString + ", " + Mode printString + ", " + UpperBound printString + ")" else "Uninitialized Trangle Distribution" fi
			
			
			/* Uniform Distribution */
			data class Uniform extends Distribution
			variables LowerBound, IntervalLength: Real
			methods
				/* Initializes the lower bound L and upper bound U for the Uniform distribution */
				withParameters(L, U: Object) : Uniform |Temp: Real|
					self initialise;
					if (L = nil) | ((L isOfType("Integer") not) & (L isOfType("Real") not)) then self error("Lower Bound for Uniform distribution must be an Integer or a Real") fi;
					if (U = nil) | ((U isOfType("Integer") not) & (U isOfType("Real") not)) then self error("Upper Bound for Uniform distribution must be an Integer or a Real") fi;
					if L isOfType("Integer") then L := L asReal fi; if U isOfType("Integer") then U := U asReal fi;
					if L > U then Temp := L; L := U; U := Temp fi;
					LowerBound := L;
					IntervalLength := U - L;
					return self
			
				/* Returns a sample for the Uniform distribution if initialized. Otherwise, it returns nil */
				sample : Real
					return if Random != nil then LowerBound + (Random random * IntervalLength) else nil fi
			
				/* Returns a pretty print */
				printString : String
					return if Random != nil then "Uniform(" + LowerBound printString + ", " + (LowerBound + IntervalLength) printString + ")" else "Uninitialized Uniform Distribution" fi
			
			
			/* Weibull Distribution */
			data class Weibull extends Distribution
			variables Shape, Scale: Real
			methods
				/* Initializes the scale K and shape L for the Weibull distribution */
				withParameters(K, L: Object) : Weibull
					self initialise;
					if (K = nil) | ((K isOfType("Integer") not) & (K isOfType("Real") not)) then self error("Scale parameter for Weibull distribution must be an Integer or a Real") fi;
					if (L = nil) | ((L isOfType("Integer") not) & (L isOfType("Real") not)) then self error("Shape parameter for Weibull distribution must be an Integer or a Real") fi;
					if K isOfType("Integer") then K := K asReal fi; if L isOfType("Integer") then L := L asReal fi;
					if  (K <= 0.0) | (L <= 0.0) then self error("Parameters for Weibull distribution must be larger than 0.0") fi;
					Scale := L / self gamma(1.0 + 1.0 / K);
					Shape := K;
					return self
			
				/* Returns a sample for the Weibull distribution if initialized. Otherwise, it returns nil */
				sample : Real |Sample: Real|
					if Random != nil then
						Sample := (-(Random random ln) ln / Shape) exp * Scale;
						if Sample > 0.0 then
										return Sample
						else
										return self sample
						fi
					else
						return nil
					fi
			
				/* Returns a pretty print */
				printString : String
					return if Random != nil then "Weibull(" + Shape printString + ", " + Scale printString + ")" else "Uninitialized Weibull Distribution" fi
			
				// Method for implementation - gamma function
				gamma(z: Real) : Real |pi,t,x: Real, g,i: Integer, lanczos_coef: Array|
					g := 7;
					lanczos_coef := new(Array) resize(9) putAt(1,0.99999999999980993) 
						putAt(2,676.5203681218851) putAt(3,-1259.1392167224028)
						putAt(4,771.32342877765313) putAt(5,-176.61502916214059) 
						putAt(6,12.507343278686905) putAt(7,-0.13857109526572012)
						putAt(8,9.9843695780195716e-6) putAt(9,1.5056327351493116e-7);
					pi := 3.14159265358979323846264338327950288419716939937510;
					if z < 0.5 then 
						return pi / (pi * z) sin * self gamma(1.0 - z)
					else
						z := z - 1.0;
						i := 1;
						x := lanczos_coef at(1);
						while i < g + 2 do
							x := x + lanczos_coef at(i + 1)/(z + i asReal);
							i := i + 1
						od;
						t := z + g asReal + 0.5;
						return (2.0 * pi) sqrt * t power(z + 0.5) * (-t) exp * x
					fi
			
			
			/* Histogram */
			data class Histogram extends Object
			variables NumberOfSamples, NumberOfBins: Integer, Histogram: Array, Minimum, Maximum, IntervalSize: Real, Start: Real
			methods
				/* Initializes the Histogram to range from lower bound L to upper bound U using N bins */
				withParameters(L, U: Object, N: Integer) : Histogram |Temp: Real|
					if (L = nil) | ((L isOfType("Integer") not) & (L isOfType("Real") not)) | (U = nil) | ((U isOfType("Integer") not) & (U isOfType("Real") not)) then self error("Bounds for Histogram must be Integers or Reals") fi;
					if L isOfType("Integer") then L := L asReal fi; if U isOfType("Integer") then U := U asReal fi;
					if (N = nil) | (N isOfType("Integer") not) then self error("Number of bins for Histogram must be an Integer") fi;
					Minimum := L; Maximum := U; NumberOfBins := N;
					if Minimum >= Maximum then Temp := Minimum; Minimum := Maximum; Maximum := Temp fi;
					if NumberOfBins <= 2 then self error("Number Of Slots for Histogram must be larger than 2") fi;
					Histogram := new(Array) resize(NumberOfBins) putAll(0);
					NumberOfSamples := 0;
					IntervalSize := (Maximum - Minimum) / (NumberOfBins - 1) asReal;
					Start := Minimum - 0.5 * IntervalSize;
					return self
			
				/* Registers a sample for the Histogram */
				sample(Value: Real) : Histogram |i: Integer, b: Boolean|
					NumberOfSamples := NumberOfSamples + 1;
					if (Value >= Minimum) & (Value <= Maximum) then
					 			b := true; i := 1;
					 			while b & (i <= NumberOfBins) do
					 						if (Value >= Start + (i - 1) asReal * IntervalSize) & (Value < Start + i asReal * IntervalSize) then Histogram putAt(i, Histogram at(i) + 1); b := false fi;
					 						i := i + 1
					 			od
					fi;
					return self
			
				/* Returns a pretty print */
				printString : String |i: Integer, Result: String|
					Result := "Probability\t\tSample Value Range\n";
					if NumberOfSamples != 0 then
					 			i := 1;
					 			while i <= NumberOfBins do
					 						Result := Result + (Histogram at(i) asReal / NumberOfSamples asReal) printString + "\t[" + (Start + (i - 1) asReal * IntervalSize) printString + ", " + (Start + i asReal * IntervalSize) printString + "]\n";
					 						i := i + 1
					 			od
					fi;
					return Result'''

			expectation = '''
			// API version 3.0 - Library of Random Distributions - Embedded Systems Innovations by TNO
			/* This library provides several random distributions. Each of them provides method "withParameter" or withParameters
			 * to initialize the distribution, while method "sample" returns a value from the sample space of the distribution
			 */
			/* Super Class Distribution */
			data class Distribution extends Object
			variables
				Random : RandomGenerator
			methods
				/* Returns a pretty print - it is redefined in subclasses */
				printString : String
					return "Unspecified Distribution"
			
				// Method for implementation
				initialise : Distribution
					Random := new(RandomGenerator) randomiseSeed;
					return self
			
			/* Bernoulli Distribution */
			data class Bernoulli extends Distribution
			variables
				SuccessProbability : Real
			methods
				/* Initializes the success probability S for the Bernoulli distribution */
				withParameter(S : Object) : Bernoulli
					self initialise;
					if (S = nil) | ((S isOfType("Integer") not) & (S isOfType("Real") not)) then
						self error("Parameter of Bernoulli distribution must be an Integer or Real")
					fi;
					if S isOfType("Integer") then
						S := S asReal
					fi;
					if (S < 0.0) | (S > 1.0) then
						self error("Parameter for Bernoulli distribution must be within the interval [0, 1]")
					fi;
					SuccessProbability := S;
					return self
			
				/* Returns a sample for the Bernoulli distribution if initialized. Otherwise, it returns nil */
				sample : Boolean
					return if Random != nil then
						Random random < SuccessProbability
					else
						nil
					fi
			
				/* Returns a sample for the Bernoulli distribution if initialized. Otherwise, it returns nil - Alternative */
				yieldsSuccess : Boolean
					return self sample
			
				/* Returns a pretty print */
				printString : String
					return if Random != nil then
						"Bernoulli(" + SuccessProbability printString + ")"
					else
						"Uninitialized Bernoulli"
					fi
			
			/* Beta Distribution (Shape Parameters Only) */
			data class Beta extends Distribution
			variables
				GammaA, GammaB : Gamma,
				Alpha, Beta : Real
			methods
				/* Initializes the shape parameters A and B for the Beta distribution */
				withParameters(A, B : Object) : Beta
					self initialise;
					if (A = nil) | ((A isOfType("Integer") not) & (A isOfType("Real") not)) | (B = nil) | ((B isOfType("Integer")
							not) & (B isOfType("Real") not)) then
						self error("Shape parameters of Beta distribution must be Integers or Reals")
					fi;
					if A isOfType("Integer") then
						A := A asReal
					fi;
					if B isOfType("Integer") then
						B := B asReal
					fi;
					if (A <= 0.0) | (B <= 0.0) then
						self error("Shape parameters of Beta distribution should be larger than 0.0")
					fi;
					Alpha := A;
					Beta := B;
					GammaA := new(Gamma) withParameters(Alpha, 1.0);
					GammaB := new(Gamma) withParameters(Beta, 1.0);
					return self
			
				/* Returns a sample for the Beta distribution if initialized. Otherwise, it returns nil */
				sample : Real | Sample : Real |
					if Random != nil then
						Sample := GammaA sample;
						return Sample / (Sample + GammaB sample)
					else
						return nil
					fi
			
				/* Returns a pretty print */
				printString : String
					return if Random != nil then
						"Beta(" + Alpha printString + ", " + Beta printString + ")"
					else
						"Uninitialized Beta"
					fi
			
			/* Beta4 Distribution (Shape Parameters and Bounded) */
			data class Beta4 extends Distribution
			variables
				GammaA, GammaB : Gamma,
				Alpha, Beta, LowerBound, UpperBound : Real,
				Fixed : Boolean
			methods
				/* Initializes the shape parameters A and B, the lower bound L and upper bound U for the Beta distribution */
				withParameters(A, B, L, U : Object) : Beta4 | Temp : Real |
					self initialise;
					if (A = nil) | ((A isOfType("Integer") not) & (A isOfType("Real") not)) | (B = nil) | ((B isOfType("Integer")
							not) & (B isOfType("Real") not)) then
						self error("Shape parameters of Beta4 distribution must be Integers or Reals")
					fi;
					if (L = nil) | ((L isOfType("Integer") not) & (L isOfType("Real") not)) then
						self error("Lower Bound for Beta4 distribution must be an Integer or Real")
					fi;
					if (U = nil) | ((U isOfType("Integer") not) & (U isOfType("Real") not)) then
						self error("Upper Bound for Beta4 distribution must be an Integer or Real")
					fi;
					Alpha := A;
					Beta := B;
					LowerBound := L;
					UpperBound := U;
					Fixed := LowerBound = UpperBound;
					if Fixed not then
						if LowerBound >= UpperBound then
							Temp := LowerBound;
							LowerBound := UpperBound;
							UpperBound := Temp
						fi;
						if (Alpha <= 0.0) | (Beta <= 0.0) then
							self error("Shape parameters of Beta4 distribution should be larger than 0.0")
						fi;
						GammaA := new(Gamma) withParameters(Alpha, 1.0);
						GammaB := new(Gamma) withParameters(Beta, 1.0)
					fi;
					return self
			
				/* Returns a sample for the Beta4 distribution if initialized. Otherwise, it returns nil */
				sample : Real | Sample : Real |
					if Random != nil then
						if Fixed then
							return LowerBound
						else
							Sample := GammaA sample;
							if Sample = 0.0 then
								return LowerBound
							else
								return LowerBound + (UpperBound - LowerBound) * (Sample / (Sample + GammaB sample))
							fi
						fi
					else
						return nil
					fi
			
				/* Returns a pretty print */
				printString : String
					return if Random != nil then
						"Beta4(" + Alpha printString + ", " + Beta printString + ", " + LowerBound printString + ", " + UpperBound
							printString + ")"
					else
						"Uninitialized Beta4"
					fi
			
			/* Discrete Uniform Distribution */
			data class DiscreteUniform extends Distribution
			variables
				LowerBound, IntervalLength : Integer,
				Fixed : Boolean
			methods
				/* Initializes the lower bound L and upper bound U for the Uniform distribution */
				withParameters(L, U : Object) : DiscreteUniform | Temp : Integer |
					self initialise;
					Fixed := L = U;
					if (L = nil) | (L isOfType("Integer") not) | (U = nil) | (U isOfType("Integer") not) then
						self error("Parameters of Discrete Uniform distribution must be Integers")
					fi;
					if L > U then
						Temp := L;
						L := U;
						U := Temp
					fi;
					LowerBound := L;
					IntervalLength := U - L + 1;
					return self
			
				/* Returns a sample for the Discrete Uniform distribution if initialized. Otherwise, it returns nil */
				sample : Integer
					if Random != nil then
						return if Fixed then
							LowerBound
						else
							LowerBound + (Random random * (IntervalLength asReal)) floor asInteger
						fi
					else
						return nil
					fi
			
				/* Returns a pretty print */
				printString : String
					return if Random != nil then
						"DiscreteUniform(" + LowerBound printString + ", " + (LowerBound + IntervalLength - 1) printString + ")"
					else
						"Uninitialized Discrete Uniform"
					fi
			
			/* Exponential Distribution */
			data class Exponential extends Distribution
			variables
				Lambda : Real
			methods
				/* Initializes the parameter L for the Exponential distribution */
				withParameter(L : Object) : Exponential
					self initialise;
					if (L = nil) | ((L isOfType("Integer") not) & (L isOfType("Real") not)) then
						self error("Parameter for Exponential distribution must be an Integer or Real")
					fi;
					if L isOfType("Integer") then
						L := L asReal
					fi;
					if L <= 0.0 then
						self error("Parameter for Exponential distribution must be positive")
					fi;
					Lambda := L;
					return self
			
				/* Returns a sample for the Exponential distribution if initialized. Otherwise, it returns nil */
				sample : Real | Sample : Real |
					if Random != nil then
						Sample := -(Random random ln) / Lambda;
						return if Sample > 0.0 then
							Sample
						else
							self sample
						fi
					else
						return nil
					fi
			
				/* Returns a pretty print */
				printString : String
					return if Random != nil then
						"Exponential(" + Lambda printString + ")"
					else
						"Uninitialized Exponential Distribution"
					fi
			
			/* Gamma Distribution */
			data class Gamma extends Distribution
			variables
				Alpha, Beta : Real
			methods
				/* Initializes the shape parameter A and scale parameter B for the Gamma distribution */
				withParameters(A, B : Object) : Gamma
					self initialise;
					if (A = nil) | ((A isOfType("Integer") not) & (A isOfType("Real") not)) then
						self error("Shape parameter of Gamma distribution must be an Integer or a Real")
					fi;
					if (B = nil) | ((B isOfType("Integer") not) & (B isOfType("Real") not)) then
						self error("Scale parameter of Gamma distribution must be an Integer or a Real")
					fi;
					if A isOfType("Integer") then
						A := A asReal
					fi;
					if B isOfType("Integer") then
						B := B asReal
					fi;
					if A <= 0.0 then
						self error("Shape parameter of Gamma distribution should be larger than 0.0")
					fi;
					if B <= 0.0 then
						self error("Scale parameter of Gamma distribution should be larger than 0.0")
					fi;
					Alpha := A;
					Beta := B;
					return self
			
				/* Returns a sample for the Gamma distribution if initialized. Otherwise, it returns nil*/
				sample : Real | T, ainv, e, b, p, bbb, ccc, u, u1, u2, v, x, z, r, MAGICCONST : Real |
					if Random != nil then
						MAGICCONST := 1.0 + 4.5 ln;
						T := -1.0;
						if Alpha > 1.0 then
							ainv := ((2.0 * Alpha) - 1.0) sqrt;
							bbb := Alpha - (4.0 ln);
							ccc := Alpha + ainv;
							while T < 0.0 do
								u1 := Random random;
								if (1.0e-7 < u1) & (u1 < 0.9999999) then
									u2 := 1.0 - Random random;
									v := ((u1 / (1.0 - u1)) ln) / ainv;
									x := Alpha * (v exp);
									z := u1 * u1 * u2;
									r := bbb + ccc * v - x;
									if ((r + MAGICCONST - 4.5 * z) >= 0.0) | (r >= (z ln)) then
										T := x * Beta
									fi
								fi
							od
						fi;
						if Alpha = 1.0 then
							u := Random random;
							while u <= 1.0e-7 do
								u := Random random
							od;
							T := -(u ln) * Beta
						fi;
						if Alpha < 1.0 then
							while T < 0.0 do
								u := Random random;
								e := 1.0 exp;
								b := (e + Alpha) / e;
								p := b * u;
								if p <= 1.0 then
									x := p power(1.0 / Alpha)
								else
									x := -(((b - p) / Alpha) ln)
								fi;
								u1 := Random random;
								if p > 1.0 then
									if u1 <= (x power(Alpha - 1.0)) then
										T := x * Beta
									fi
								else if u1 <= ((-x) exp) then
									T := x * Beta
								fi fi
							od
						fi;
						return T
					else
						return nil
					fi
			
				/* Returns a pretty print */
				printString : String
					return if Random != nil then
						"Gamma(" + Alpha printString + ", " + Beta printString + ")"
					else
						"Uninitialized Gamma Distribution"
					fi
			
			/* Discrete Distribution */
			data class Discrete extends Distribution
			variables
				Samples, LowerBounds, UpperBounds, Weights : Array
			methods
				/* Adds Value to the sample space with weight Weight for the Discrete distribution. In case a specific Value is added multiple times, the effective probability on generating Value as sample depends on the total Weight */
				withParameters(Value : Object, Weight : Object) : Discrete | Index, Size : Integer, CumulativeWeight, Total : Real |
					if Random = nil then
						self initialise;
						Samples := new(Array);
						Weights := new(Array);
						LowerBounds := new(Array);
						UpperBounds := new(Array)
					fi;
					if (Weight = nil) | ((Weight isOfType("Integer") not) & (Weight isOfType("Real") not)) then
						self error("Weight parameter of Discrete distribution must be an Integer or a Real")
					fi;
					if Weight isOfType("Integer") then
						Weight := Weight asReal
					fi;
					Size := Samples size + 1;
					Samples resize(Size);
					Weights resize(Size);
					Samples putAt(Size, Value);
					Weights putAt(Size, Weight);
					LowerBounds resize(Size);
					UpperBounds resize(Size);
					Index := 1;
					Total := 0.0;
					while Index <= Size do
						Total := Total + Weights at(Index);
						Index := Index + 1
					od;
					Index := 1;
					CumulativeWeight := 0.0;
					while Index <= Size do
						LowerBounds putAt(Index, CumulativeWeight / Total);
						CumulativeWeight := CumulativeWeight + Weights at(Index);
						UpperBounds putAt(Index, CumulativeWeight / Total);
						Index := Index + 1
					od;
					return self
			
				/* Returns a sample for the Discrete distribution if initialized. Otherwise, it returns nil*/
				sample : Object | Sample : Real, Result : Object, Index : Integer |
					if Random != nil then
						Sample := Random random;
						Index := 1;
						while (Result = nil) & (Index <= Samples size) do
							if (LowerBounds at(Index) < Sample) & (Sample <= UpperBounds at(Index)) then
								Result := Samples at(Index)
							else
								Index := Index + 1
							fi
						od;
						return Result
					else
						return nil
					fi
			
				/* Returns a pretty print */
				printString : String | PrintOut : String, Index : Integer |
					if Random != nil then
						PrintOut := "Discrete Distribution\n";
						Index := 1;
						while Index <= Samples size do
							PrintOut := PrintOut + "Value: " + Samples at(Index) printString + " Probability: " + (UpperBounds
								at(Index) - LowerBounds at(Index)) printString cr;
							Index := Index + 1
						od
					else
						PrintOut := "Uninitialized Discrete Distribution"
					fi;
					return PrintOut
			
			/*  Normal Distribution */
			data class Normal extends Distribution
			variables
				Mean, StandardDeviation : Real
			methods
				/* Initializes the mean M and variance V parameters for the Normal distribution */
				withParameters(M, V : Object) : Normal
					self initialise;
					if (M = nil) | ((M isOfType("Integer") not) & (M isOfType("Real") not)) then
						self error("Mean parameter of Normal distribution must be an Integer or a Real")
					fi;
					if (V = nil) | ((V isOfType("Integer") not) & (V isOfType("Real") not)) then
						self error("Variance parameter of Normal distribution must be an Integer or a Real")
					fi;
					if M isOfType("Integer") then
						M := M asReal
					fi;
					if V isOfType("Integer") then
						V := V asReal
					fi;
					Mean := M;
					StandardDeviation := V sqrt;
					return self
			
				/* Returns a sample for the Normal distribution if initialized. Otherwise, it returns nil*/
				sample : Real | S, U, X, Y : Real |
					if Random != nil then
						S := 1.0;
						while S >= 1.0 do
							X := (2.0 * Random random) - 1.0;
							Y := (2.0 * Random random) - 1.0;
							S := (X * X) + (Y * Y)
						od;
						U := (-2.0 * (S ln) / S) sqrt;
						return Mean + (X * U * StandardDeviation)
					else
						return nil
					fi
			
				/* Returns a pretty print */
				printString : String
					return if Random != nil then
						"Normal(" + Mean printString + ", " + StandardDeviation sqr printString + ")"
					else
						"Uninitialized Normal Distribution"
					fi
			
			/* PERT Distribution */
			data class PERT extends Beta4
			variables
				Mode : Real
			methods
				/* Initializes the lower bound L, mode M and upper bound U for the PERT distribution */
				withParameters(L, M, U : Object) : PERT | Mean, Std, Temp : Real |
					self initialise;
					if (L = nil) | ((L isOfType("Integer") not) & (L isOfType("Real") not)) then
						self error("Lower Bound for PERT distribution must be an Integer or a Real")
					fi;
					if (M = nil) | ((M isOfType("Integer") not) & (M isOfType("Real") not)) then
						self error("Mode Bound for PERT distribution must be an Integer or a Real")
					fi;
					if (U = nil) | ((U isOfType("Integer") not) & (U isOfType("Real") not)) then
						self error("Upper Bound for PERT distribution must be an Integer or a Real")
					fi;
					if L isOfType("Integer") then
						L := L asReal
					fi;
					if M isOfType("Integer") then
						M := M asReal
					fi;
					if U isOfType("Integer") then
						U := U asReal
					fi;
					LowerBound := L;
					UpperBound := U;
					Mode := M;
					Fixed := LowerBound = UpperBound;
					if Fixed not then
						if LowerBound > UpperBound then
							Temp := LowerBound;
							LowerBound := UpperBound;
							UpperBound := Temp
						fi;
						if (LowerBound >= Mode) | (Mode >= UpperBound) then
							self
								error("Parameters of PERT distribution do not satisfy LowerBound < Mode < UpperBound or LowerBound = UpperBound")
						fi;
						Mean := (LowerBound + 4.0 * Mode + UpperBound) / 6.0;
						Std := (UpperBound - LowerBound) / 6.0;
						Alpha := ((Mean - LowerBound) / (UpperBound - LowerBound)) * ((((Mean - LowerBound) * (UpperBound - Mean)) /
							(Std sqr)) - 1.0);
						Beta := ((UpperBound - Mean) / (Mean - LowerBound)) * Alpha;
						GammaA := new(Gamma) withParameters(Alpha, 1.0);
						GammaB := new(Gamma) withParameters(Beta, 1.0)
					fi;
					return self
			
				/* Returns a pretty print */
				printString : String
					return if Random != nil then
						"PERT(" + LowerBound printString + ", " + Mode printString + ", " + UpperBound printString + ")"
					else
						"Uninitialized PERT Distribution"
					fi
			
			/* Triangle Distribution */
			data class Triangle extends Distribution
			variables
				LowerBound, Mode, UpperBound : Real,
				Fixed : Boolean
			methods
				/* Initializes the lower bound L, mode M and upper bound U for the Trangle distribution */
				withParameters(L, M, U : Object) : Triangle | Temp : Real |
					self initialise;
					if (L = nil) | ((L isOfType("Integer") not) & (L isOfType("Real") not)) then
						self error("Lower Bound for Triangle distribution must be an Integer or a Real")
					fi;
					if (M = nil) | ((M isOfType("Integer") not) & (M isOfType("Real") not)) then
						self error("Mode Bound for Triangle distribution must be an Integer or a Real")
					fi;
					if (U = nil) | ((U isOfType("Integer") not) & (U isOfType("Real") not)) then
						self error("Upper Bound for Triangle distribution must be an Integer or a Real")
					fi;
					if L isOfType("Integer") then
						L := L asReal
					fi;
					if M isOfType("Integer") then
						M := M asReal
					fi;
					if U isOfType("Integer") then
						U := U asReal
					fi;
					LowerBound := L;
					UpperBound := U;
					Mode := M;
					Fixed := LowerBound = UpperBound;
					if Fixed not then
						if LowerBound > UpperBound then
							Temp := LowerBound;
							LowerBound := UpperBound;
							UpperBound := Temp
						fi;
						if (LowerBound >= Mode) | (Mode >= UpperBound) then
							self
								error("Parameters of Triangle distribution do not satisfy LowerBound < Mode < UpperBound or LowerBound = UpperBound")
						fi
					fi;
					return self
			
				/* Returns a sample for the Triangle distribution if initialized. Otherwise, it returns nil */
				sample : Real | Sample : Real |
					if Random != nil then
						if Fixed then
							return LowerBound
						else
							Sample := Random random;
							if Sample <= (Mode - LowerBound) / (UpperBound - LowerBound) then
								return LowerBound + (Sample * (UpperBound - LowerBound) * (Mode - LowerBound)) sqrt
							else
								return UpperBound - ((1.0 - Sample) * (UpperBound - LowerBound) * (UpperBound - Mode)) sqrt
							fi
						fi
					else
						return nil
					fi
			
				/* Returns a pretty print */
				printString : String
					return if Random != nil then
						"Triangle(" + LowerBound printString + ", " + Mode printString + ", " + UpperBound printString + ")"
					else
						"Uninitialized Trangle Distribution"
					fi
			
			/* Uniform Distribution */
			data class Uniform extends Distribution
			variables
				LowerBound, IntervalLength : Real
			methods
				/* Initializes the lower bound L and upper bound U for the Uniform distribution */
				withParameters(L, U : Object) : Uniform | Temp : Real |
					self initialise;
					if (L = nil) | ((L isOfType("Integer") not) & (L isOfType("Real") not)) then
						self error("Lower Bound for Uniform distribution must be an Integer or a Real")
					fi;
					if (U = nil) | ((U isOfType("Integer") not) & (U isOfType("Real") not)) then
						self error("Upper Bound for Uniform distribution must be an Integer or a Real")
					fi;
					if L isOfType("Integer") then
						L := L asReal
					fi;
					if U isOfType("Integer") then
						U := U asReal
					fi;
					if L > U then
						Temp := L;
						L := U;
						U := Temp
					fi;
					LowerBound := L;
					IntervalLength := U - L;
					return self
			
				/* Returns a sample for the Uniform distribution if initialized. Otherwise, it returns nil */
				sample : Real
					return if Random != nil then
						LowerBound + (Random random * IntervalLength)
					else
						nil
					fi
			
				/* Returns a pretty print */
				printString : String
					return if Random != nil then
						"Uniform(" + LowerBound printString + ", " + (LowerBound + IntervalLength) printString + ")"
					else
						"Uninitialized Uniform Distribution"
					fi
			
			/* Weibull Distribution */
			data class Weibull extends Distribution
			variables
				Shape, Scale : Real
			methods
				/* Initializes the scale K and shape L for the Weibull distribution */
				withParameters(K, L : Object) : Weibull
					self initialise;
					if (K = nil) | ((K isOfType("Integer") not) & (K isOfType("Real") not)) then
						self error("Scale parameter for Weibull distribution must be an Integer or a Real")
					fi;
					if (L = nil) | ((L isOfType("Integer") not) & (L isOfType("Real") not)) then
						self error("Shape parameter for Weibull distribution must be an Integer or a Real")
					fi;
					if K isOfType("Integer") then
						K := K asReal
					fi;
					if L isOfType("Integer") then
						L := L asReal
					fi;
					if (K <= 0.0) | (L <= 0.0) then
						self error("Parameters for Weibull distribution must be larger than 0.0")
					fi;
					Scale := L / self gamma(1.0 + 1.0 / K);
					Shape := K;
					return self
			
				/* Returns a sample for the Weibull distribution if initialized. Otherwise, it returns nil */
				sample : Real | Sample : Real |
					if Random != nil then
						Sample := (-(Random random ln) ln / Shape) exp * Scale;
						if Sample > 0.0 then
							return Sample
						else
							return self sample
						fi
					else
						return nil
					fi
			
				/* Returns a pretty print */
				printString : String
					return if Random != nil then
						"Weibull(" + Shape printString + ", " + Scale printString + ")"
					else
						"Uninitialized Weibull Distribution"
					fi
			
				// Method for implementation - gamma function
				gamma(z : Real) : Real | pi, t, x : Real, g, i : Integer, lanczos_coef : Array |
					g := 7;
					lanczos_coef := new(Array) resize(9) putAt(1, 0.99999999999980993) putAt(2, 676.5203681218851) putAt(3,
						-1259.1392167224028) putAt(4, 771.32342877765313) putAt(5, -176.61502916214059) putAt(6, 12.507343278686905)
						putAt(7, -0.13857109526572012) putAt(8, 9.9843695780195716e-6) putAt(9, 1.5056327351493116e-7);
					pi := 3.14159265358979323846264338327950288419716939937510;
					if z < 0.5 then
						return pi / (pi * z) sin * self gamma(1.0 - z)
					else
						z := z - 1.0;
						i := 1;
						x := lanczos_coef at(1);
						while i < g + 2 do
							x := x + lanczos_coef at(i + 1) / (z + i asReal);
							i := i + 1
						od;
						t := z + g asReal + 0.5;
						return (2.0 * pi) sqrt * t power(z + 0.5) * (-t) exp * x
					fi
			
			/* Histogram */
			data class Histogram extends Object
			variables
				NumberOfSamples, NumberOfBins : Integer,
				Histogram : Array,
				Minimum, Maximum, IntervalSize : Real,
				Start : Real
			methods
				/* Initializes the Histogram to range from lower bound L to upper bound U using N bins */
				withParameters(L, U : Object, N : Integer) : Histogram | Temp : Real |
					if (L = nil) | ((L isOfType("Integer") not) & (L isOfType("Real") not)) | (U = nil) | ((U isOfType("Integer")
							not) & (U isOfType("Real") not)) then
						self error("Bounds for Histogram must be Integers or Reals")
					fi;
					if L isOfType("Integer") then
						L := L asReal
					fi;
					if U isOfType("Integer") then
						U := U asReal
					fi;
					if (N = nil) | (N isOfType("Integer") not) then
						self error("Number of bins for Histogram must be an Integer")
					fi;
					Minimum := L;
					Maximum := U;
					NumberOfBins := N;
					if Minimum >= Maximum then
						Temp := Minimum;
						Minimum := Maximum;
						Maximum := Temp
					fi;
					if NumberOfBins <= 2 then
						self error("Number Of Slots for Histogram must be larger than 2")
					fi;
					Histogram := new(Array) resize(NumberOfBins) putAll(0);
					NumberOfSamples := 0;
					IntervalSize := (Maximum - Minimum) / (NumberOfBins - 1) asReal;
					Start := Minimum - 0.5 * IntervalSize;
					return self
			
				/* Registers a sample for the Histogram */
				sample(Value : Real) : Histogram | i : Integer, b : Boolean |
					NumberOfSamples := NumberOfSamples + 1;
					if (Value >= Minimum) & (Value <= Maximum) then
						b := true;
						i := 1;
						while b & (i <= NumberOfBins) do
							if (Value >= Start + (i - 1) asReal * IntervalSize) & (Value < Start + i asReal * IntervalSize) then
								Histogram putAt(i, Histogram at(i) + 1);
								b := false
							fi;
							i := i + 1
						od
					fi;
					return self
			
				/* Returns a pretty print */
				printString : String | i : Integer, Result : String |
					Result := "Probability\t\tSample Value Range\n";
					if NumberOfSamples != 0 then
						i := 1;
						while i <= NumberOfBins do
							Result := Result + (Histogram at(i) asReal / NumberOfSamples asReal) printString + "\t[" + (Start + (i -
								1) asReal * IntervalSize) printString + ", " + (Start + i asReal * IntervalSize) printString +
								"]\n";
							i := i + 1
						od
					fi;
					return Result'''

		]
	}

}
