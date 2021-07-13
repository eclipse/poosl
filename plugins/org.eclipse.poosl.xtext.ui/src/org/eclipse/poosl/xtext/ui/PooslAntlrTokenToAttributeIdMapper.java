package org.eclipse.poosl.xtext.ui;

import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultAntlrTokenToAttributeIdMapper;
import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultHighlightingConfiguration;

public class PooslAntlrTokenToAttributeIdMapper extends DefaultAntlrTokenToAttributeIdMapper {

    @Override
    protected String calculateId(String tokenName, int tokenType) {
        if ("RULE_CHARACTER".equals(tokenName) || "RULE_POOSL_STRING".equals(tokenName)) { //$NON-NLS-1$ //$NON-NLS-2$
            return DefaultHighlightingConfiguration.STRING_ID;
        }

        if ("RULE_BINARY".equals(tokenName) || "RULE_OCTAL".equals(tokenName) || "RULE_HEXADECIMAL".equals(tokenName) || "RULE_DECIMAL_CORE".equals(tokenName) || "RULE_REAL_CORE".equals(tokenName)) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
            return DefaultHighlightingConfiguration.NUMBER_ID;
        }
        return super.calculateId(tokenName, tokenType);
    }
}
