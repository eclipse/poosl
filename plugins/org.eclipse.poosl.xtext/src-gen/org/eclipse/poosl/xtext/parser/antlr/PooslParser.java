/*******************************************************************************
 * Copyright (c) 2021 TNO/ESI.
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
package org.eclipse.poosl.xtext.parser.antlr;

import com.google.inject.Inject;
import org.eclipse.poosl.xtext.parser.antlr.internal.InternalPooslParser;
import org.eclipse.poosl.xtext.services.PooslGrammarAccess;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

public class PooslParser extends AbstractAntlrParser {

	@Inject
	private PooslGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	

	@Override
	protected InternalPooslParser createParser(XtextTokenStream stream) {
		return new InternalPooslParser(stream, getGrammarAccess());
	}

	@Override 
	protected String getDefaultRuleName() {
		return "Poosl";
	}

	public PooslGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(PooslGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
