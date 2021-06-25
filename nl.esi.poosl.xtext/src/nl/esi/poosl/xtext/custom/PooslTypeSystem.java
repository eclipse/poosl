package nl.esi.poosl.xtext.custom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.IEObjectDescription;

import nl.esi.poosl.AssignmentExpression;
import nl.esi.poosl.BinaryOperatorExpression;
import nl.esi.poosl.BooleanConstant;
import nl.esi.poosl.CharacterConstant;
import nl.esi.poosl.CurrentTimeExpression;
import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethodCallExpression;
import nl.esi.poosl.EnvironmentConstant;
import nl.esi.poosl.Expression;
import nl.esi.poosl.ExpressionSequence;
import nl.esi.poosl.FloatConstant;
import nl.esi.poosl.IfExpression;
import nl.esi.poosl.IntegerConstant;
import nl.esi.poosl.NewExpression;
import nl.esi.poosl.NilConstant;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.RealConstant;
import nl.esi.poosl.ReturnExpression;
import nl.esi.poosl.SelfExpression;
import nl.esi.poosl.StringConstant;
import nl.esi.poosl.SwitchExpression;
import nl.esi.poosl.SwitchExpressionCase;
import nl.esi.poosl.UnaryOperatorExpression;
import nl.esi.poosl.VariableExpression;
import nl.esi.poosl.WhileExpression;
import nl.esi.poosl.xtext.helpers.HelperFunctions;
import nl.esi.poosl.xtext.helpers.PooslVariableTypeHelper;
import nl.esi.poosl.xtext.validation.PooslIssueCodes;
import nl.esi.poosl.xtext.validation.PooslJavaValidatorTypes;

public class PooslTypeSystem {
	private final PooslJavaValidatorTypes validator;

	public PooslTypeSystem(PooslJavaValidatorTypes validator) {
		this.validator = validator;
	}

	private void error(String message, EObject source, String code, String... issueCode) {
		if (validator != null) {
			if (issueCode != null && issueCode.length > 0) {
				validator.generateError(message, source, null, -1, code, issueCode);
			} else {
				validator.generateError(message, source, null, -1, code);
			}
		}
	}

	private void warning(String message, EObject source, String code, String... issueCode) {
		if (validator != null) {
			if (issueCode != null && issueCode.length > 0) {
				validator.generateWarning(message, source, null, -1, code, issueCode);
			} else {
				validator.generateWarning(message, source, null, -1, code);
			}
		}
	}

	// --- Entry points -------

	public List<String> getAndCheckExpressionsType(List<Expression> listOfExpressions) {
		List<String> types = new ArrayList<>();
		if (listOfExpressions != null) {
			for (Expression expressions : listOfExpressions) {
				types.add(getAndCheckExpressionType(expressions));
			}
		}
		return types;
	}

	public String getAndCheckExpressionType(Expression expression) {
		// Do not replace this by a switch statement, as this turns out to block
		// stack optimizations.
		if (expression instanceof ExpressionSequence) {
			return caseExpressionSequence((ExpressionSequence) expression);
		} else if (expression instanceof DataMethodCallExpression) {
			return caseDataMethodCall((DataMethodCallExpression) expression);
		} else if (expression instanceof AssignmentExpression) {
			return caseAssignmentExpression((AssignmentExpression) expression);
		} else if (expression instanceof IfExpression) {
			return caseIfExpression((IfExpression) expression);
		} else if (expression instanceof SwitchExpression) {
			return caseSwitchExpression((SwitchExpression) expression);
		} else if (expression instanceof WhileExpression) {
			return caseWhileExpression((WhileExpression) expression);
		} else if (expression instanceof ReturnExpression) {
			return caseReturnExpression((ReturnExpression) expression);
		} else if (expression instanceof BinaryOperatorExpression) {
			return caseBinaryOperatorExpression((BinaryOperatorExpression) expression);
		} else if (expression instanceof UnaryOperatorExpression) {
			return caseUnaryOperatorExpression((UnaryOperatorExpression) expression);
		} else if (expression instanceof BooleanConstant) {
			return caseBooleanConstant((BooleanConstant) expression);
		} else if (expression instanceof CharacterConstant) {
			return caseCharacterConstant((CharacterConstant) expression);
		} else if (expression instanceof CurrentTimeExpression) {
			return caseCurrentTime((CurrentTimeExpression) expression);
		} else if (expression instanceof FloatConstant) {
			return caseFloatConstant((FloatConstant) expression);
		} else if (expression instanceof IntegerConstant) {
			return caseIntegerConstant((IntegerConstant) expression);
		} else if (expression instanceof NewExpression) {
			return caseNew((NewExpression) expression);
		} else if (expression instanceof NilConstant) {
			return caseNilConstant((NilConstant) expression);
		} else if (expression instanceof RealConstant) {
			return caseRealConstant((RealConstant) expression);
		} else if (expression instanceof SelfExpression) {
			return caseSelf((SelfExpression) expression);
		} else if (expression instanceof StringConstant) {
			return caseStringConstant((StringConstant) expression);
		} else if (expression instanceof EnvironmentConstant) {
			return caseEnvironmentConstant((EnvironmentConstant) expression);
		} else if (expression instanceof VariableExpression) {
			return caseVariable((VariableExpression) expression);
		}

		return null;
	}

	// --- Specific cases -------

	private String caseBooleanConstant(BooleanConstant getDataClassBoolean) {
		return HelperFunctions.CLASS_NAME_BOOLEAN;
	}

	private String caseCharacterConstant(CharacterConstant characterConstant) {
		return HelperFunctions.CLASS_NAME_CHAR;
	}

	private String caseFloatConstant(FloatConstant realConstant) {
		return HelperFunctions.CLASS_NAME_FLOAT;
	}

	private String caseIntegerConstant(IntegerConstant integerConstant) {
		return HelperFunctions.CLASS_NAME_INTEGER;
	}

	private String caseRealConstant(RealConstant realConstant) {
		return HelperFunctions.CLASS_NAME_REAL;
	}

	private String caseStringConstant(StringConstant stringConstant) {
		return HelperFunctions.CLASS_NAME_STRING;
	}

	private String caseEnvironmentConstant(EnvironmentConstant environmentConstant) {
		return HelperFunctions.CLASS_NAME_OBJECT;
	}

	private String caseCurrentTime(CurrentTimeExpression currentTime) {
		return HelperFunctions.CLASS_NAME_REAL;
	}

	private String caseNilConstant(NilConstant nilConstant) {
		return HelperFunctions.CLASS_NAME_OBJECT;
	}

	private String caseNew(NewExpression newExpr) {
		return newExpr.getDataClass();
	}

	private String caseSelf(SelfExpression self) {
		DataClass parent = HelperFunctions.getContainingDataClass(self);
		if (parent == null) {
			return null;
		} else {
			if (parent.getName() != null) {
				return parent.getName();
			} else {
				return HelperFunctions.CLASS_NAME_OBJECT;
			}
		}
	}

	private String caseVariable(VariableExpression variableExpression) {
		if (variableExpression.getVariable() != null) {
			String type = PooslVariableTypeHelper.getVariableType(variableExpression, variableExpression.getVariable());
			if (type != null && PooslCache.get(variableExpression.eResource()).getDataClassMap().containsKey(type)) {
				return type;
			}
		}
		return null;
	}

	private String caseExpressionSequence(ExpressionSequence expressions) {
		String result = null;
		for (Expression expr : expressions.getExpressions()) {
			result = getAndCheckExpressionType(expr);
		}
		return result;
	}

	private String caseReturnExpression(ReturnExpression returnExpression) {
		return getAndCheckExpressionType(returnExpression.getExpression());
	}

	private String caseAssignmentExpression(AssignmentExpression assignmentExpression) {
		Resource resource = assignmentExpression.eResource();
		String typeOfVariableName = PooslVariableTypeHelper.getVariableType(assignmentExpression,
				assignmentExpression.getVariable());
		String typeOfExpressionName = getAndCheckExpressionType(assignmentExpression.getExpression());

		if (PooslCache.get(resource).getDataClassMap().containsKey(typeOfVariableName)) {
			if (!TypingHelper.isCompatible(resource, typeOfExpressionName, typeOfVariableName)) {
				warning("Expression of type " + typeOfExpressionName + " is not compatible with variable of type "
						+ typeOfVariableName, assignmentExpression, PooslIssueCodes.INVALID_ASSIGNMENT_TYPE,
						typeOfExpressionName, typeOfVariableName);
			} else {
				return typeOfExpressionName;
			}
		}
		return null;
	}

	private String caseIfExpression(IfExpression ifExpression) {
		Resource resource = ifExpression.eResource();
		Expression condition = ifExpression.getCondition();
		String typeName = getAndCheckExpressionType(condition);
		if (!TypingHelper.isCompatible(resource, typeName, HelperFunctions.CLASS_NAME_BOOLEAN)) {
			warning("Condition of type " + typeName + " is not compatible with type Boolean", condition,
					PooslIssueCodes.INCOMPATIBLE_TYPE, typeName, HelperFunctions.CLASS_NAME_BOOLEAN);
		}

		String typeOfThenClauseName = getAndCheckExpressionType(ifExpression.getThenClause());
		if (ifExpression.getElseClause() == null) {
			if (typeOfThenClauseName != null) {
				return HelperFunctions.CLASS_NAME_OBJECT;
			}
		} else {
			String typeOfElseClauseName = getAndCheckExpressionType(ifExpression.getElseClause());
			if (typeOfThenClauseName != null && typeOfElseClauseName != null) {
				return TypingHelper.greatestCommonAncestor(resource, typeOfThenClauseName, typeOfElseClauseName);
			}
		}
		return null;
	}

	private String caseSwitchExpression(SwitchExpression switchExpression) {
		Resource resource = switchExpression.eResource();
		Expression expression = switchExpression.getExpression();
		String expressionType = getAndCheckExpressionType(expression);

		Expression defaultBody = switchExpression.getDefaultBody();
		String returnType;
		if (defaultBody == null) {
			returnType = HelperFunctions.CLASS_NAME_OBJECT;
		} else {
			returnType = getAndCheckExpressionType(defaultBody);
		}

		for (SwitchExpressionCase switchCase : switchExpression.getCases()) {
			Expression caseValue = switchCase.getValue();
			String caseValueType = getAndCheckExpressionType(caseValue);

			if (!TypingHelper.isCompatible(resource, caseValueType, expressionType)) {
				warning("Case condition of type " + caseValueType + " is not compatible with switch expression of type "
						+ expressionType, caseValue, PooslIssueCodes.INCOMPATIBLE_TYPE, caseValueType, expressionType);
			}

			Expression caseBody = switchCase.getBody();
			String caseBodyType = getAndCheckExpressionType(caseBody);
			returnType = TypingHelper.greatestCommonAncestor(resource, returnType, caseBodyType);
		}

		return returnType;
	}

	private String caseWhileExpression(WhileExpression whileExpression) {
		Resource resource = whileExpression.eResource();
		Expression condition = whileExpression.getCondition();
		String typeName = getAndCheckExpressionType(condition);
		if (!TypingHelper.isCompatible(resource, typeName, HelperFunctions.CLASS_NAME_BOOLEAN)) {
			warning("Condition of type " + typeName + " is not compatible with type Boolean", condition,
					PooslIssueCodes.INCOMPATIBLE_TYPE, typeName, "Boolean");
		}

		getAndCheckExpressionType(whileExpression.getBody());
		return HelperFunctions.CLASS_NAME_OBJECT;
	}

	private String caseDataMethodCall(DataMethodCallExpression expr) {
		Resource resource = expr.eResource();
		String runningClass = getAndCheckExpressionType(expr.getTarget());
		List<String> argumentTypes = getAndCheckExpressionsType(expr.getArguments());

		if (expr.isOnSuperClass() && runningClass != null && !HelperFunctions.CLASS_NAME_OBJECT.equals(runningClass)) {
			runningClass = HelperFunctions.getCorrectedDataClassExtendsAsString(resource, runningClass);
		}

		if (runningClass != null) {
			String dMethodName = expr.getName();
			List<IEObjectDescription> dMethods = PooslCache.get(resource).getDataMethods(dMethodName,
					argumentTypes.size(), Literals.DATA_CLASS__DATA_METHODS_NAMED);
			List<String> resultTypeNames = TypingHelper.getReturnTypesDataMethodNamed(resource, dMethods, runningClass,
					argumentTypes);

			if (resultTypeNames.isEmpty()) {
				if (dMethods.isEmpty()) {
					error("No method '" + dMethodName + "' with  " + argumentTypes.size()
							+ " parameters defined for any data class", expr,
							PooslIssueCodes.UNDECLARED_DATA_METHOD_NAMED);
				} else {
					String strResult = "";
					if (!argumentTypes.isEmpty()) {
						StringBuilder buf = new StringBuilder();
						for (String dClass : argumentTypes) {
							String name = (dClass == null) ? "" : dClass;
							buf.append(", ").append(name);
						}
						strResult = buf.toString().substring(2);
					}
					warning("No method '" + dMethodName + "' defined for left expression of type " + runningClass
							+ " with parameters of types (" + strResult + ")", expr,
							PooslIssueCodes.UNDECLARED_DATA_METHOD_NAMED);
				}
			} else {
				return TypingHelper.greatestCommonAncestor(resource, resultTypeNames);
			}
		}
		return null;
	}

	private String caseBinaryOperatorExpression(BinaryOperatorExpression binaryOperatorExpression) {
		Resource resource = binaryOperatorExpression.eResource();
		String runningClass = getAndCheckExpressionType(binaryOperatorExpression.getLeftOperand());
		String argumentType = getAndCheckExpressionType(binaryOperatorExpression.getRightOperand());

		if (runningClass != null && argumentType != null) {
			String dMethodName = binaryOperatorExpression.getName().getLiteral();
			List<IEObjectDescription> dMethods = PooslCache.get(resource).getDataMethods(dMethodName, 1,
					Literals.DATA_CLASS__DATA_METHODS_BINARY_OPERATOR);
			List<String> resultTypeNames = TypingHelper.getReturnTypesDataMethodNamed(resource, dMethods, runningClass,
					Collections.singletonList(argumentType));

			if (resultTypeNames.isEmpty()) {
				if (dMethods.isEmpty()) {
					error("No binary operation '" + dMethodName + "' defined for any data class",
							binaryOperatorExpression, PooslIssueCodes.UNDECLARED_DATA_METHOD_BINARY);
				} else {
					warning("No binary operation '" + dMethodName + "' defined for left expression of type "
							+ runningClass + " and right expression of type " + argumentType, binaryOperatorExpression,
							PooslIssueCodes.UNDECLARED_DATA_METHOD_BINARY);
				}
			} else {
				return TypingHelper.greatestCommonAncestor(resource, resultTypeNames);
			}
		}
		return null;
	}

	private String caseUnaryOperatorExpression(UnaryOperatorExpression unaryOperatorExpression) {
		Resource resource = unaryOperatorExpression.eResource();
		String runningClass = getAndCheckExpressionType(unaryOperatorExpression.getOperand());

		if (runningClass != null) {
			String dMethodName = unaryOperatorExpression.getName().getLiteral();
			List<IEObjectDescription> dMethods = PooslCache.get(resource).getDataMethods(dMethodName, 0,
					Literals.DATA_CLASS__DATA_METHODS_UNARY_OPERATOR);
			List<String> resultTypeNames = TypingHelper.getReturnTypesDataMethodNamed(resource, dMethods, runningClass,
					Collections.<String>emptyList());

			if (resultTypeNames.isEmpty()) {
				if (dMethods.isEmpty()) {
					error("No unary operation '" + dMethodName + "' defined for any data class",
							unaryOperatorExpression, PooslIssueCodes.UNDECLARED_DATA_METHOD_UNARY);
				} else {
					warning("No unary operation '" + dMethodName + "' defined for expression of type " + runningClass,
							unaryOperatorExpression, PooslIssueCodes.UNDECLARED_DATA_METHOD_UNARY);
				}
			} else {
				return TypingHelper.greatestCommonAncestor(resource, resultTypeNames);
			}
		}
		return null;
	}
}
