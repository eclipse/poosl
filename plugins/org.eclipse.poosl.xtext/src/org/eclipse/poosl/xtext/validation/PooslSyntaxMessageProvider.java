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
package org.eclipse.poosl.xtext.validation;

import org.eclipse.xtext.diagnostics.Diagnostic;
import org.eclipse.xtext.nodemodel.SyntaxErrorMessage;
import org.eclipse.xtext.parser.antlr.SyntaxErrorMessageProvider;

/**
 * The PooslSyntaxMessageProvider.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslSyntaxMessageProvider extends SyntaxErrorMessageProvider {
    private static final String MISSING_EOF = "missing EOF at";

    private static final String NO_VIABLE_ALTERNATIVE = "no viable alternative at input";

    private static final String UNEXPECTED_INPUT = "unexpected input";

    @Override
    public SyntaxErrorMessage getSyntaxErrorMessage(IParserErrorContext context) {
        if (context.getDefaultMessage().startsWith(MISSING_EOF)) {
            String message = UNEXPECTED_INPUT
                    + context.getDefaultMessage().substring(MISSING_EOF.length());
            return new SyntaxErrorMessage(message, Diagnostic.SYNTAX_DIAGNOSTIC);
        }
        if (context.getDefaultMessage().startsWith(NO_VIABLE_ALTERNATIVE)) {
            String message = UNEXPECTED_INPUT
                    + context.getDefaultMessage().substring(NO_VIABLE_ALTERNATIVE.length());
            return new SyntaxErrorMessage(message, Diagnostic.SYNTAX_DIAGNOSTIC);
        }
        return super.getSyntaxErrorMessage(context);
    }
}
