package nl.esi.poosl.xpect;


import java.util.Collection;
import java.util.List;

import org.eclipse.xtext.util.Pair;
import org.junit.Assert;
import org.junit.ComparisonFailure;
import org.xpect.XpectArgument;
import org.xpect.XpectImport;
import org.xpect.expectation.LinesExpectation;
import org.xpect.expectation.impl.AbstractExpectation;
import org.xpect.expectation.impl.ActualCollection;
import org.xpect.expectation.impl.ActualCollection.ActualItem;
import org.xpect.expectation.impl.ExpectationCollection;
import org.xpect.expectation.impl.ExpectationCollection.ExpectationItem;
import org.xpect.expectation.impl.ExpectationRegionProvider;
import org.xpect.expectation.impl.TargetSyntaxSupport;
import org.xpect.setup.XpectSetupFactory;
import org.xpect.state.Creates;
import org.xpect.text.Text;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

@SuppressWarnings("restriction")
@XpectSetupFactory
@XpectImport(ExpectationRegionProvider.class)
public class PooslLinesExpectationImpl extends AbstractExpectation implements IPooslLinesExpectation {

	private final LinesExpectation annotation;

	public PooslLinesExpectationImpl(XpectArgument argument, TargetSyntaxSupport targetSyntax) {
		super(argument, targetSyntax);
		this.annotation = argument.getAnnotationOrDefault(LinesExpectation.class);
	}

	public void assertEquals(Iterable<?> actual) {
		assertEquals("", actual);
	}

	public void assertEquals(String message, Iterable<?> actual) {
		Assert.assertNotNull(actual);

		ExpectationCollection exp = new ExpectationCollection(); 
		exp.setCaseSensitive(annotation.caseSensitive());
		exp.setOrdered(annotation.ordered());
		exp.setQuoted(annotation.quoted());
		exp.setSeparator('\n');
		exp.setWhitespaceSensitive(annotation.whitespaceSensitive());
		exp.init(getExpectation());

		ActualCollection act = new ActualCollection();
		act.setTargetLiteralSupport(getTargetSyntaxLiteral());
		act.setCaseSensitive(annotation.caseSensitive());
		act.setOrdered(annotation.ordered());
		act.setQuoted(annotation.quoted());
		act.setSeparator('\n');
		act.setWhitespaceSensitive(annotation.whitespaceSensitive());
		act.init(actual, annotation.itemFormatter());

		if (!exp.matches(act)) {
			List<String> expString = Lists.newArrayList();
			List<String> actString = Lists.newArrayList();
			for (Pair<Collection<ExpectationItem>, ActualItem> pair : exp.map(act)) {
				if (pair.getFirst() != null && !pair.getFirst().isEmpty()) {
					if (pair.getSecond() != null)
						expString.add(pair.getSecond().getEscaped());
					else
						expString.add(pair.getFirst().iterator().next().getEscaped());
				}
				if (pair.getSecond() != null)
					actString.add(pair.getSecond().getEscaped());
			}
			String nl = new Text(getRegion().getDocument()).getNL();
			String expDoc = replaceInDocument(Joiner.on(nl).join(expString));
			String actDoc = replaceInDocument(Joiner.on(nl).join(actString));
			throw new ComparisonFailure(message, expDoc, actDoc);
		}
	}
	
	public void assertContains(String message, Iterable<?> actual) {
		Assert.assertNotNull(actual);

		ExpectationCollection exp = new PooslExpectationCollection(); 
		exp.setCaseSensitive(annotation.caseSensitive());
		exp.setOrdered(annotation.ordered());
		exp.setQuoted(annotation.quoted());
		exp.setSeparator('\n');
		exp.setWhitespaceSensitive(annotation.whitespaceSensitive());
		exp.init(getExpectation());

		ActualCollection act = new ActualCollection();
		act.setTargetLiteralSupport(getTargetSyntaxLiteral());
		act.setCaseSensitive(annotation.caseSensitive());
		act.setOrdered(annotation.ordered());
		act.setQuoted(annotation.quoted());
		act.setSeparator('\n');
		act.setWhitespaceSensitive(annotation.whitespaceSensitive());
		act.init(actual, annotation.itemFormatter());

		if (!exp.matches(act)) {
			List<String> expString = Lists.newArrayList();
			List<String> actString = Lists.newArrayList();
			for (Pair<Collection<ExpectationItem>, ActualItem> pair : exp.map(act)) {
				if (pair.getFirst() != null && !pair.getFirst().isEmpty()) {
					if (pair.getSecond() != null)
						expString.add(pair.getSecond().getEscaped());
					else
						expString.add(pair.getFirst().iterator().next().getEscaped());
				}
				if (pair.getSecond() != null)
					actString.add(pair.getSecond().getEscaped());
			}
			String nl = new Text(getRegion().getDocument()).getNL();
			String expDoc = replaceInDocument(Joiner.on(nl).join(expString));
			String actDoc = replaceInDocument(Joiner.on(nl).join(actString));
			throw new ComparisonFailure(message, expDoc, actDoc);
		}
	}

	@Creates
	public IPooslLinesExpectation create() {
		return this;
	}

	public LinesExpectation getAnnotation() {
		return annotation;
	}
}
