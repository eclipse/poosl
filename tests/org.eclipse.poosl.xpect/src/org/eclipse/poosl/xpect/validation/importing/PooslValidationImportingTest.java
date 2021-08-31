/*******************************************************************************
 * Copyright (c) 2021 TNO/ESI
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    TNO/ESI - initial API and implementation
 *    Obeo - refactoring
 *******************************************************************************/
package org.eclipse.poosl.xpect.validation.importing;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.poosl.xpect.description.Scan;
import org.eclipse.xtext.validation.Issue;
import org.junit.runner.RunWith;
import org.xpect.XpectImport;
import org.xpect.runner.XpectRunner;
import org.xpect.xtext.lib.tests.ValidationTest;
import org.xpect.xtext.lib.util.IssueFormatter;

import com.google.common.base.Function;

/**
 * The PooslValidationImportingTest.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
@RunWith(XpectRunner.class)
@XpectImport(Scan.class)
public class PooslValidationImportingTest extends ValidationTest {

    private static final String WS_VAR = "%xpectWS";

    private static final String wsPath;

    static {
        IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
        wsPath = wsRoot.getLocation().toPortableString();
    }

    @Override
    protected Function<? super Issue, String> createIssueFormatter(CharSequence document) {
        return new IssueFormatter(document, false) {
            @Override
            public String apply(Issue issue) {
                String result = super.apply(issue);
                return result.replace(wsPath, WS_VAR);
            }
        };
    }

    // private static final List<String> FILES = Arrays.asList(
    // "import.poosl", //$NON-NLS-1$
    // "importClusterTwice.poosl", //$NON-NLS-1$
    // "importClusterTwice2.poosl", //$NON-NLS-1$
    // "importClusterTwice3.poosl", //$NON-NLS-1$
    // "importDataTwice.poosl", //$NON-NLS-1$
    // "importDataTwice2.poosl", //$NON-NLS-1$
    // "importDataTwice3.poosl"); //$NON-NLS-1$
    //
    // /**
    // * Copies all files from "import" package to "import" folder of workspace.
    // * <p>
    // * Reserved to JUnit runner.
    // * </p>
    // */
    // // @BeforeClass
    // public static void setupImport() throws Exception, InterruptedException {
    // IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
    // IPath wsRootPath = wsRoot.getLocation();
    //
    // ImportingTestBase.setupResources(PooslValidationImportingTest.class,
    // FILES,
    // // See org.xpect.xtext.lib.setup.workspace.WorkspaceDefaultsSetup#initializeDefaultProject()
    // wsRootPath.toFile().toPath().resolve("default_project") // ??
    // );
    // }

    // @Xpect(liveExecution = LiveExecutionType.FAST)
    // @ConsumedIssues({ Severity.INFO, Severity.ERROR, Severity.WARNING })
    // public void contains(IPooslLinesExpectation expectation, @IssuesByLine Multimap<IRegion, Issue> line2issue,
    // @NextLine IRegion line, ValidationTestConfig cfg) {
    // List<String> issues = getActualIssues(line2issue, line, cfg, Severity.ERROR, Severity.WARNING, Severity.INFO,
    // Severity.IGNORE);
    // expectation.assertContains("", issues); //$NON-NLS-1$
    // }

}
