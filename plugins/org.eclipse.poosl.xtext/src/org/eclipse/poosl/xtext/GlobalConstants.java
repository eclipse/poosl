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
package org.eclipse.poosl.xtext;

/**
 * The GlobalConstants.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class GlobalConstants {
    public static final String PREFERENCE_PLUGIN_ID = "org.eclipse.poosl.xtext.ui"; //$NON-NLS-1$

    public static final String FILE_EXTENSION = "poosl"; //$NON-NLS-1$

    public static final String POOSL_SYSTEM = "system"; //$NON-NLS-1$

    public static final String PREFERENCES_USE_DEFAULT_BASIC_CLASS = "usedefaultbasicclass"; //$NON-NLS-1$

    public static final String PREFERENCES_BASIC_CLASSES_DEFAULT = "defaultbasicclasses"; //$NON-NLS-1$

    public static final String PREFERENCES_BASIC_CLASSES_CUSTOM = "custombasicclasses"; //$NON-NLS-1$

    public static final String PREFERENCES_CUSTOM_BASIC_CLASS_PATH = "custombasicclasspath"; //$NON-NLS-1$

    public static final String PREFERENCES_DONT_ASK_EDIT_PERSPECTIVE = "dontaskperspective"; //$NON-NLS-1$

    public static final String PREFERENCES_OPEN_EDIT_PERSPECTIVE = "openeditperspective"; //$NON-NLS-1$

    public static final String PREFERENCES_AUTOCOMPLETE_BRACKETS_AND_QUOTES = "autcompletebracketsquotes"; //$NON-NLS-1$

    public static final String PREFERENCES_FORMAT_ON_SAVE = "formatonsave"; //$NON-NLS-1$

    public static final String PREFERENCES_INCLUDE_VERSION = "poosl.preferences.version"; //$NON-NLS-1$

    public static final String PREFERENCES_INCLUDE_KEY = "poosl.include"; //$NON-NLS-1$

    public static final String PREFERENCES_INCLUDE_LOCATION = "org.eclipse.poosl.xtext.Poosl"; //$NON-NLS-1$

    public static final String PREFERENCES_VERSION = "1"; //$NON-NLS-1$

    private GlobalConstants() {
        throw new IllegalStateException("Utility class");
    }
}
