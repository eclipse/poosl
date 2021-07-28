package org.eclipse.poosl.xpect.validation.channel;

import java.util.List;

import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.validation.Issue;
import org.junit.ComparisonFailure;
import org.junit.runner.RunWith;
import org.xpect.expectation.ILinesExpectation;
import org.xpect.runner.Xpect;
import org.xpect.runner.XpectRunner;
import org.xpect.text.IRegion;
import org.xpect.xtext.lib.tests.ValidationTest;
import org.xpect.xtext.lib.tests.ValidationTestConfig;
import org.xpect.xtext.lib.tests.ValidationTestModuleSetup.ConsumedIssues;
import org.xpect.xtext.lib.tests.ValidationTestModuleSetup.IssuesByLine;
import org.xpect.xtext.lib.util.NextLine;

import com.google.common.collect.Multimap;

@RunWith(XpectRunner.class)
public class PooslValidationChannelTest extends ValidationTest {

    /**
     * Tests a warning exist is expected but the message has no set format.
     * 
     * @param expectation
     *            used to log failure
     * @param line2issue
     *            issues associated to line
     * @param line
     *            tested
     * @param cfg
     *            of the test
     */
    @Xpect
    @ConsumedIssues(Severity.WARNING)
    public void warningsNoExpectations(ILinesExpectation expectation, @IssuesByLine Multimap<IRegion, Issue> line2issue, @NextLine IRegion line, ValidationTestConfig cfg) {

        List<String> issues = getActualIssues(line2issue, line, cfg, Severity.WARNING);
        if (issues.size() == 0) {
            throw new ComparisonFailure("A warning was expected but none was found.", issues.toString(), "!"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

}
