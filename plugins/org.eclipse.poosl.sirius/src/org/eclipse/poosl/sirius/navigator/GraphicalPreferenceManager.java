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
package org.eclipse.poosl.sirius.navigator;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.poosl.sirius.IPreferenceConstants;
import org.eclipse.poosl.sirius.navigator.PooslEditorPreferenceDialog.OpenStrategy;

/**
 * The GraphicalPreferenceManager.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class GraphicalPreferenceManager extends OpenPreferenceManager {
    // Used from Graphical views
    public GraphicalPreferenceManager() {
        diagramDescription = PooslEditorPreferenceDialog.COMPOSITE_STRUCTURE_DIAGRAM;
    }

    public String getEditorPreference() {
        IPreferencesService preferencesService = Platform.getPreferencesService();
        Boolean dontask = preferencesService.getBoolean(IPreferenceConstants.PREFERENCE_PLUGIN_ID,
                IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_DONT_ASK, false, null);
        String currentPref = getCurrentPreference(preferencesService);

        return dontask ? currentPref : askPreference();

    }

    private String getCurrentPreference(IPreferencesService preferencesService) {
        return preferencesService.getString(IPreferenceConstants.PREFERENCE_PLUGIN_ID,
                IPreferenceConstants.PREFERENCE_GRAPHICAL_EDITOR,
                IPreferenceConstants.PREFERENCE_GRAPHICAL_EDITOR_TEXTUAL, null);
    }

    private String askPreference() {
        OpenStrategy result = showEditorPreferenceDialog();
        switch (result) {
        case TEXTUAL:
            return IPreferenceConstants.PREFERENCE_GRAPHICAL_EDITOR_TEXTUAL;
        case GRAPHICAL:
            return IPreferenceConstants.PREFERENCE_GRAPHICAL_EDITOR_GRAPHICAL;
        case CANCEL:
            return null;
        default:
            return IPreferenceConstants.PREFERENCE_GRAPHICAL_EDITOR_TEXTUAL;
        }
    }

    @Override
    protected String getDialogLabel() {
        return Messages.GPM_DIALOG_LABEL;
    }
}
