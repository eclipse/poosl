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

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.poosl.pooslproject.PooslProjectConstant;
import org.eclipse.poosl.rotalumisclient.PooslConstants;
import org.eclipse.poosl.xtext.ui.PooslEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Tests for perspectives.
 *
 * @author nperansin
 */
@RunWith(Parameterized.class)
public class PooslPerspectiveTest {

    @Parameterized.Parameters(name = "{0}")
    public static List<String> getPerspectiveId() {
        return Arrays.asList(//
                PooslProjectConstant.ID_POOSL_EDIT_PERSPECTIVE,
                PooslConstants.ID_POOSL_DEBUG_PERSPECTIVE);
    }

    private String perspective;

    public PooslPerspectiveTest(String id) {
        perspective = id;
    }

    /**
     * Text editor is acknowledge it.
     * 
     * @throws CoreException
     *     on failure.
     */
    @Test
    public void editorSupported() throws CoreException {
        assertTrue(PooslEditor.isSupportedPerspective(perspective));
    }

    /**
     * Evaluates existence.
     * 
     * @throws CoreException
     *     on failure.
     */
    @Test
    public void perspectiveExists() throws CoreException {
        IWorkbench wb = PlatformUI.getWorkbench();
        wb.showPerspective(perspective, wb.getActiveWorkbenchWindow());
        assertTrue(PooslEditor.isSupportedPerspective(perspective));
    }

}
