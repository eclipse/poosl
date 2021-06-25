package nl.esi.poosl.xtext.ui;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.xtext.ui.editor.autoedit.DefaultAutoEditStrategyProvider;

import nl.esi.poosl.xtext.GlobalConstants;

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
