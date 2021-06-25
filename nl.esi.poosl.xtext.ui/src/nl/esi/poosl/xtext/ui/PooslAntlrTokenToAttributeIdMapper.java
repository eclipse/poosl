package nl.esi.poosl.xtext.ui;

import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultAntlrTokenToAttributeIdMapper;
import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultHighlightingConfiguration;

public class PooslAntlrTokenToAttributeIdMapper extends DefaultAntlrTokenToAttributeIdMapper {

	@Override
	protected String calculateId(String tokenName, int tokenType) {
		if ("RULE_CHARACTER".equals(tokenName) || "RULE_POOSL_STRING".equals(tokenName)) {
			return DefaultHighlightingConfiguration.STRING_ID;
		}

		if ("RULE_BINARY".equals(tokenName) || "RULE_OCTAL".equals(tokenName) || "RULE_HEXADECIMAL".equals(tokenName)
				|| "RULE_DECIMAL_CORE".equals(tokenName) || "RULE_REAL_CORE".equals(tokenName)) {
			return DefaultHighlightingConfiguration.NUMBER_ID;
		}
		return super.calculateId(tokenName, tokenType);
	}
}
