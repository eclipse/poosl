package nl.esi.poosl.xtext.ui;

import org.eclipse.xtext.ui.editor.model.TerminalsTokenTypeToPartitionMapper;

public class PooslTerminalsTokenTypeToPartitionMapper extends TerminalsTokenTypeToPartitionMapper {

	@Override
	protected String calculateId(String tokenName, int tokenType) {
		if ("RULE_POOSL_STRING".equals(tokenName)) {
			return STRING_LITERAL_PARTITION;
		} else if ("RULE_CHARACTER".equals(tokenName)) {
			return STRING_LITERAL_PARTITION;
		} else {
			return super.calculateId(tokenName, tokenType);
		}
	}
}
