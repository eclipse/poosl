/*
 * Custom compare class for Poosl. It is not used anymore at the moment but might be in the future.
 */
package nl.esi.poosl.xpect;

import org.xpect.XpectImport;

@XpectImport(PooslLinesExpectationImpl.class)
public interface IPooslLinesExpectation {
	void assertEquals (Iterable<?> string);

	void assertEquals(String message, Iterable<?> string);
	
	void assertContains(String message, Iterable<?> string);
}