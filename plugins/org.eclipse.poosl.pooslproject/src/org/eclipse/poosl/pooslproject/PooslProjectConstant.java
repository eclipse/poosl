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
package org.eclipse.poosl.pooslproject;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Constants for Poosl Projects.
 * 
 * @author Obeo
 */
public final class PooslProjectConstant {

    /** Id of Project Wizard. */
    public static final String PROJECT_WIZARD_ID = PooslNewProjectWizard.ID;

    /** Id of model Wizard. */
    public static final String FILE_WIZARD_ID = PooslNewModelWizard.ID;

    /** Id of model Wizard with system. */
    public static final String SYSFILE_WIZARD_ID = PooslNewModelWithSystemWizard.ID;

    /**
     * Charset supported for interpretation.
     * <p>
     * Currently simulation only support one charset for reading model for any
     * platform host.
     * </p>
     */
    public static final Charset SUPPORTED_CHARSET = StandardCharsets.ISO_8859_1;

    /** Perspective ID for edition. */
    public static final String ID_POOSL_EDIT_PERSPECTIVE = "org.eclipse.poosl.normalperspective"; //$NON-NLS-1$

    private PooslProjectConstant() {
    }

}
