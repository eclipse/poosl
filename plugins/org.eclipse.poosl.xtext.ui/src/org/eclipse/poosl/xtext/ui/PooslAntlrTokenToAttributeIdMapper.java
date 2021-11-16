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

import java.util.Arrays;
import java.util.List;

import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultAntlrTokenToAttributeIdMapper;
import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultHighlightingConfiguration;

/**
 * The PooslAntlrTokenToAttributeIdMapper.
 *
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslAntlrTokenToAttributeIdMapper extends DefaultAntlrTokenToAttributeIdMapper {

    private static final List<String> STRING_TOKENS = Arrays.asList(//
            "RULE_CHARACTER", //$NON-NLS-1$
            "RULE_POOSL_STRING" //$NON-NLS-1$
    );

    private static final List<String> NUMBER_TOKENS = Arrays.asList(//
            "RULE_BINARY", //$NON-NLS-1$
            "RULE_OCTAL", //$NON-NLS-1$
            "RULE_HEXADECIMAL", //$NON-NLS-1$
            "RULE_DECIMAL_CORE", //$NON-NLS-1$
            "RULE_REAL_CORE" //$NON-NLS-1$
    );

    @Override
    protected String calculateId(String tokenName, int tokenType) {
        if (STRING_TOKENS.contains(tokenName)) {
            return DefaultHighlightingConfiguration.STRING_ID;
        }

        if (NUMBER_TOKENS.contains(tokenName)) {
            return DefaultHighlightingConfiguration.NUMBER_ID;
        }
        return super.calculateId(tokenName, tokenType);
    }
}
