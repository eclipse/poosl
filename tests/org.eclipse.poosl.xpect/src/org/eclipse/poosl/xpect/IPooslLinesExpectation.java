/*
 * Custom compare class for Poosl. It is not used anymore at the moment but might be in the future.
 */
package org.eclipse.poosl.xpect;

import org.xpect.XpectImport;
import org.xpect.expectation.ILinesExpectation;

@XpectImport(PooslLinesExpectationImpl.class)
public interface IPooslLinesExpectation extends ILinesExpectation {
    void assertEquals(Iterable<?> string);

    void assertEquals(String message, Iterable<?> string);

    void assertContains(String message, Iterable<?> string);
}
