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
package org.eclipse.poosl.xtext.custom;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.poosl.Expression;
import org.eclipse.poosl.ExpressionStatement;
import org.eclipse.poosl.IfStatement;
import org.eclipse.poosl.Statement;
import org.eclipse.poosl.StatementSequenceRoundBracket;
import org.eclipse.poosl.SwitchStatement;
import org.eclipse.poosl.SwitchStatementCase;
import org.eclipse.poosl.WhileStatement;
import org.eclipse.poosl.xtext.helpers.HelperFunctions;

/**
 * The PooslTypeSystemStatement.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslTypeSystemStatement {
    private final PooslTypeSystem typeSystem;

    public PooslTypeSystemStatement(PooslTypeSystem typeSystem) {
        this.typeSystem = typeSystem;
    }

    // --- Entry points -------

    public String getExpressionType(Statement statement) {
        // Do not replace this by a switch statement, as this turns out to block
        // stack optimizations.
        if (statement instanceof StatementSequenceRoundBracket) {
            return caseStatementSequence((StatementSequenceRoundBracket) statement);
        } else if (statement instanceof IfStatement) {
            return caseIfStatement((IfStatement) statement);
        } else if (statement instanceof SwitchStatement) {
            return caseSwitchStatement((SwitchStatement) statement);
        } else if (statement instanceof WhileStatement) {
            return caseWhileStatement((WhileStatement) statement);
        } else if (statement instanceof ExpressionStatement) {
            return caseExpressionStatement((ExpressionStatement) statement);
        }

        return null;
    }

    // --- Specific cases -------

    private String caseStatementSequence(StatementSequenceRoundBracket expressions) {
        String result = null;
        for (Statement statement : expressions.getStatements()) {
            result = getExpressionType(statement);
            if (result == null) {
                return null;
            }
        }
        return result;
    }

    private String caseIfStatement(IfStatement ifStatement) {
        Resource resource = ifStatement.eResource();
        String typeOfThenClauseName = getExpressionType(ifStatement.getThenClause());
        if (ifStatement.getElseClause() == null) {
            if (typeOfThenClauseName != null) {
                return HelperFunctions.CLASS_NAME_OBJECT;
            }
        } else {
            String typeOfElseClauseName = getExpressionType(ifStatement.getElseClause());
            if (typeOfThenClauseName != null && typeOfElseClauseName != null) {
                return TypingHelper.greatestCommonAncestor(resource, typeOfThenClauseName, typeOfElseClauseName);
            }
        }
        return null;
    }

    private String caseSwitchStatement(SwitchStatement switchStatement) {
        Resource resource = switchStatement.eResource();
        Statement defaultBody = switchStatement.getDefaultBody();
        String returnType;
        if (defaultBody == null) {
            returnType = HelperFunctions.CLASS_NAME_OBJECT;
        } else {
            returnType = getExpressionType(defaultBody);
            if (returnType == null) {
                return null;
            }
        }

        for (SwitchStatementCase switchCase : switchStatement.getCases()) {
            Statement caseBody = switchCase.getBody();
            String caseBodyType = getExpressionType(caseBody);
            if (caseBodyType == null) {
                return null;
            }
            returnType = TypingHelper.greatestCommonAncestor(resource, returnType, caseBodyType);
        }

        return returnType;
    }

    private String caseWhileStatement(WhileStatement whileStatement) {
        String bodyType = getExpressionType(whileStatement.getBody());
        if (bodyType == null) {
            return null;
        }
        return HelperFunctions.CLASS_NAME_OBJECT;
    }

    private String caseExpressionStatement(ExpressionStatement expressionStatement) {
        Expression expression = expressionStatement.getExpression();
        return typeSystem.getAndCheckExpressionType(expression);
    }
}
