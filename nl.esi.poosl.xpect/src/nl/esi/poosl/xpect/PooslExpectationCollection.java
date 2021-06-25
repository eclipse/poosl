package nl.esi.poosl.xpect;

import java.util.Iterator;

import org.xpect.expectation.impl.ActualCollection;
import org.xpect.expectation.impl.ActualCollection.ActualItem;
import org.xpect.expectation.impl.ExpectationCollection;

@SuppressWarnings("restriction")
public class PooslExpectationCollection
		extends ExpectationCollection {

	
	protected boolean matchesUnordered(ActualCollection actual) {
		if (isPure()) {
			Iterator<ExpectationItem> exIt = this.iterator();
			Iterator<ActualItem> actIt = actual.iterator();
			while (exIt.hasNext() && actIt.hasNext()) {
				ExpectationItem exp = exIt.next();
				ActualItem act = actIt.next();
				String[] expecteds = exp.getNormalized().split("%");
				for (int i = 0; i < expecteds.length; i++) {
					if(!act.getNormalized().contains(expecteds[i])) {
						return false;
					}
				}
			}
			return true;
		}
		return super.matchesUnordered(actual);
	}
}
