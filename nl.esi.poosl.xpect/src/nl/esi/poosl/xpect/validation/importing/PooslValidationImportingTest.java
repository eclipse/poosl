package nl.esi.poosl.xpect.validation.importing;

import java.util.List;

import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.validation.Issue;
import org.junit.runner.RunWith;
import org.xpect.runner.LiveExecutionType;
import org.xpect.runner.Xpect;
import org.xpect.runner.XpectRunner;
import org.xpect.text.IRegion;
import org.xpect.xtext.lib.tests.ValidationTest;
import org.xpect.xtext.lib.tests.ValidationTestConfig;
import org.xpect.xtext.lib.tests.ValidationTestModuleSetup.ConsumedIssues;
import org.xpect.xtext.lib.tests.ValidationTestModuleSetup.IssuesByLine;
import org.xpect.xtext.lib.util.NextLine;

import com.google.common.collect.Multimap;

import nl.esi.poosl.xpect.IPooslLinesExpectation;

@RunWith(XpectRunner.class)
public class PooslValidationImportingTest extends ValidationTest {		
	
	@Xpect(liveExecution = LiveExecutionType.FAST)
	@ConsumedIssues({ Severity.INFO, Severity.ERROR, Severity.WARNING })
	public void contains(IPooslLinesExpectation expectation, @IssuesByLine Multimap<IRegion, Issue> line2issue, @NextLine IRegion line, ValidationTestConfig cfg) {
		List<String> issues = getActualIssues(line2issue, line, cfg, Severity.ERROR, Severity.WARNING, Severity.INFO, Severity.IGNORE);
		expectation.assertContains("", issues);
	}

} 