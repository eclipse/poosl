package nl.esi.poosl.xtext.custom;

import org.eclipse.emf.ecore.resource.Resource;

import nl.esi.poosl.Expression;
import nl.esi.poosl.ExpressionStatement;
import nl.esi.poosl.IfStatement;
import nl.esi.poosl.Statement;
import nl.esi.poosl.StatementSequenceRoundBracket;
import nl.esi.poosl.SwitchStatement;
import nl.esi.poosl.SwitchStatementCase;
import nl.esi.poosl.WhileStatement;
import nl.esi.poosl.xtext.helpers.HelperFunctions;

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
