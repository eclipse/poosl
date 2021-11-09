/* ************************************************************************** *
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
 * ************************************************************************* */
package org.eclipse.poosl.xtext.ui.tests;

import static org.junit.Assert.assertArrayEquals;

import java.util.Objects;
import java.util.stream.Stream;

import org.eclipse.help.HelpSystem;
import org.junit.Test;

/**
 * Ensure help is available.
 * <p>
 * There is no constant in help
 * </p>
 *
 * @author nperansin
 */
public class PooslHelpReferenceTest {

    private static final String[] HELP_IDS = {
            org.eclipse.poosl.xtext.ui.PooslEditor.HELP_ID,
            org.eclipse.poosl.sirius.preferences.EditorPreferencePage.HELP_ID,
            org.eclipse.poosl.xtext.ui.preferences.BasicClassesPreferencePage.HELP_ID,
            org.eclipse.poosl.xtext.ui.preferences.IncludePropertyPage.HELP_ID,
            org.eclipse.poosl.rotalumisclient.preferences.DebugPreferencePage.HELP_ID,
            org.eclipse.poosl.rotalumisclient.preferences.SequenceDiagramPreferencePage.HELP_ID,
            org.eclipse.poosl.rotalumisclient.views.debugview.PooslDebugView.HELP_ID,
            org.eclipse.poosl.rotalumisclient.views.stacktraceview.StackTraceVariableView.HELP_ID,
            org.eclipse.poosl.rotalumisclient.views.stacktraceview.StackTraceView.HELP_ID,
            org.eclipse.poosl.rotalumisclient.views.PooslPETView.HELP_ID,
            org.eclipse.poosl.rotalumisclient.views.PooslSequenceDiagramView.HELP_ID,
            org.eclipse.poosl.rotalumisclient.views.PooslVariableDetailPane.HELP_ID,
            org.eclipse.poosl.rotalumisclient.views.PooslVariablesView.HELP_ID };

    @Test
    public void helpContextExists() {
        String[] availables = Stream.of(HELP_IDS)
                .map(it -> HelpSystem.getContext(it) != null ? it : null) //
                .filter(Objects::nonNull)//
                .toArray(String[]::new);

        assertArrayEquals(HELP_IDS, availables);
    }
}
