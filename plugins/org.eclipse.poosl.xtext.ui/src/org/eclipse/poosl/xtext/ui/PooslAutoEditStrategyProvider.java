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
package org.eclipse.poosl.xtext.ui;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.poosl.xtext.GlobalConstants;
import org.eclipse.xtext.ui.editor.autoedit.DefaultAutoEditStrategyProvider;

/**
 * The PooslAutoEditStrategyProvider.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslAutoEditStrategyProvider extends DefaultAutoEditStrategyProvider {

    @Override
    protected void configureCompoundBracesBlocks(IEditStrategyAcceptor acceptor) {
        if (isCloseAutoCompleteActivated()) {
            super.configureCompoundBracesBlocks(acceptor);
        }
    }

    @Override
    protected void configureMultilineComments(IEditStrategyAcceptor acceptor) {
        if (isCloseAutoCompleteActivated()) {
            super.configureMultilineComments(acceptor);
        }
    }

    @Override
    protected void configureCurlyBracesBlock(IEditStrategyAcceptor acceptor) {
        if (isCloseAutoCompleteActivated()) {
            super.configureCurlyBracesBlock(acceptor);
        }
    }

    @Override
    protected void configureSquareBrackets(IEditStrategyAcceptor acceptor) {
        if (isCloseAutoCompleteActivated()) {
            super.configureSquareBrackets(acceptor);
        }
    }

    @Override
    protected void configureParenthesis(IEditStrategyAcceptor acceptor) {
        if (isCloseAutoCompleteActivated()) {
            super.configureParenthesis(acceptor);
        }
    }

    @Override
    protected void configureStringLiteral(IEditStrategyAcceptor acceptor) {
        if (isCloseAutoCompleteActivated()) {
            super.configureStringLiteral(acceptor);
        }
    }

    private Boolean isCloseAutoCompleteActivated() {
        IPreferencesService preferencesService = Platform.getPreferencesService();
        if (preferencesService != null) {
            return preferencesService.getBoolean(GlobalConstants.PREFERENCE_PLUGIN_ID,
                    GlobalConstants.PREFERENCES_AUTOCOMPLETE_BRACKETS_AND_QUOTES, false, null);
        } else {
            return false;
        }
    }
}
