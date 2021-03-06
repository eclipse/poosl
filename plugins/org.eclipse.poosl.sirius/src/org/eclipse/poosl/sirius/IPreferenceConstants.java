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
package org.eclipse.poosl.sirius;

/**
 * The IPreferenceConstants.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class IPreferenceConstants {

    /** Plugin id. */
    public static final String PREFERENCE_PLUGIN_ID = Activator.PLUGIN_ID;

    /** Project explorer. */
    public static final String PROJECT_EXPLORER = "PrefProjectExplorerDiagram"; //$NON-NLS-1$

    /** textual from Project explorer. */
    public static final String PREFERENCE_PROJECT_EXPLORER_TEXTUAL = "PrefPETextual"; //$NON-NLS-1$

    /** graphical from Project explorer. */
    public static final String PREFERENCE_PROJECT_EXPLORER_GRAPHICAL = "PrefPEGraphical"; //$NON-NLS-1$

    /** graphical from Project explorer for CD. */
    public static final String PREFERENCE_PROJECT_EXPLORER_CLASS_DIAGRAM = "PrefPEClassDiagram"; //$NON-NLS-1$

    /** graphical from Project explorer for no diagram. */
    public static final String PREFERENCE_PROJECT_EXPLORER_NO_SYSTEM = "PrefProjectExplorerNoDiagram"; //$NON-NLS-1$

    /** graphical from diagram. */
    public static final String PREFERENCE_GRAPHICAL_EDITOR_GRAPHICAL = "PrefGEClassDiagram"; //$NON-NLS-1$

    /** text from diagram. */
    public static final String PREFERENCE_GRAPHICAL_EDITOR_TEXTUAL = "PrefGETextual"; //$NON-NLS-1$

    /** Preference from diagram. */
    public static final String PREFERENCE_GRAPHICAL_EDITOR = "PrefGraphicalEditor"; //$NON-NLS-1$

    /** Answer memory. */
    public static final String PREFERENCE_PROJECT_EXPLORER_DONT_ASK = "PrefProjectExplorerDontAsk"; //$NON-NLS-1$

    private IPreferenceConstants() {
        throw new IllegalStateException("Utility class");
    }
}
