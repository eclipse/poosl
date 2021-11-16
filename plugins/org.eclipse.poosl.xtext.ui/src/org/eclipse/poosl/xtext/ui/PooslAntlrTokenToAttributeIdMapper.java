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

import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultAntlrTokenToAttributeIdMapper;
import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultHighlightingConfiguration;

/**
 * The PooslAntlrTokenToAttributeIdMapper.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslAntlrTokenToAttributeIdMapper extends DefaultAntlrTokenToAttributeIdMapper {

    @Override
    protected String calculateId(String tokenName, int tokenType) {
        if ("RULE_CHARACTER".equals(tokenName) || "RULE_POOSL_STRING".equals(tokenName)) { //$NON-NLS-1$ //$NON-NLS-2$
            return DefaultHighlightingConfiguration.STRING_ID;
        }

        if ("RULE_BINARY".equals(tokenName) || "RULE_OCTAL".equals(tokenName) //$NON-NLS-1$//$NON-NLS-2$
                || "RULE_HEXADECIMAL".equals(tokenName) || "RULE_DECIMAL_CORE".equals(tokenName) //$NON-NLS-1$//$NON-NLS-2$
                || "RULE_REAL_CORE".equals(tokenName)) { //$NON-NLS-1$
            return DefaultHighlightingConfiguration.NUMBER_ID;
        }
        return super.calculateId(tokenName, tokenType);
    }
}
