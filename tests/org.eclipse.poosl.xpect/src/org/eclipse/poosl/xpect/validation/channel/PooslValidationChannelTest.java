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

/**
 * The PooslValidationChannelTest.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
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
