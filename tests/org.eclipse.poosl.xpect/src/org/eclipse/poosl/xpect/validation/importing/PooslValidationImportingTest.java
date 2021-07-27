package org.eclipse.poosl.xpect.validation.importing;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.poosl.xpect.IPooslLinesExpectation;
import org.eclipse.poosl.xpect.description.Scan;
import org.eclipse.poosl.xpect.validation.ImportingTestBase;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.validation.Issue;
import org.junit.runner.RunWith;
import org.xpect.XpectImport;
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

@RunWith(XpectRunner.class)
@XpectImport(Scan.class)
public class PooslValidationImportingTest extends ValidationTest {
    
    private static final List<String> FILES = Arrays.asList(
            "import.poosl", //$NON-NLS-1$
            "importClusterTwice.poosl", //$NON-NLS-1$
            "importClusterTwice2.poosl", //$NON-NLS-1$
            "importClusterTwice3.poosl", //$NON-NLS-1$
            "importDataTwice.poosl", //$NON-NLS-1$
            "importDataTwice2.poosl", //$NON-NLS-1$
            "importDataTwice3.poosl"); //$NON-NLS-1$
    
    /**
     * Copies all files from "import" package to "import" folder of workspace.
     * <p>
     * Reserved to JUnit runner.
     * </p>
     */
    // @BeforeClass
    public static void setupImport() throws Exception, InterruptedException {
        IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
        IPath wsRootPath = wsRoot.getLocation();
        
        ImportingTestBase.setupResources(PooslValidationImportingTest.class, 
                FILES,
                // See org.xpect.xtext.lib.setup.workspace.WorkspaceDefaultsSetup#initializeDefaultProject()
                wsRootPath.toFile().toPath().resolve("default_project") // ??
        );
    }

    @Xpect(liveExecution = LiveExecutionType.FAST)
    @ConsumedIssues({ Severity.INFO, Severity.ERROR, Severity.WARNING })
    public void contains(IPooslLinesExpectation expectation, @IssuesByLine Multimap<IRegion, Issue> line2issue, @NextLine IRegion line, ValidationTestConfig cfg) {
        List<String> issues = getActualIssues(line2issue, line, cfg, 
                Severity.ERROR, Severity.WARNING, Severity.INFO, Severity.IGNORE);
        expectation.assertContains("", issues); //$NON-NLS-1$
    }

}
