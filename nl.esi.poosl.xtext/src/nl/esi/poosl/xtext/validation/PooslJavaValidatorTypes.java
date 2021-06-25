package nl.esi.poosl.xtext.validation;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.diagnostics.Diagnostic;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckType;

import com.google.common.collect.Iterables;

import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.DataMethodBinaryOperator;
import nl.esi.poosl.DataMethodNamed;
import nl.esi.poosl.DataMethodUnaryOperator;
import nl.esi.poosl.DelayStatement;
import nl.esi.poosl.Expression;
import nl.esi.poosl.ExpressionStatement;
import nl.esi.poosl.GuardedStatement;
import nl.esi.poosl.IfStatement;
import nl.esi.poosl.Instance;
import nl.esi.poosl.InstanceParameter;
import nl.esi.poosl.OutputVariable;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.PortExpression;
import nl.esi.poosl.PortReference;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.ProcessMethodCall;
import nl.esi.poosl.ReceiveStatement;
import nl.esi.poosl.SendStatement;
import nl.esi.poosl.SwitchStatement;
import nl.esi.poosl.SwitchStatementCase;
import nl.esi.poosl.WhileStatement;
import nl.esi.poosl.xtext.custom.FormattingHelper;
import nl.esi.poosl.xtext.custom.PooslCache;
import nl.esi.poosl.xtext.custom.PooslMessageType;
import nl.esi.poosl.xtext.custom.PooslTypeSystem;
import nl.esi.poosl.xtext.custom.TypingHelper;
import nl.esi.poosl.xtext.descriptions.PooslDataMethodDescription;
import nl.esi.poosl.xtext.descriptions.PooslDeclarationDescription;
import nl.esi.poosl.xtext.descriptions.PooslMessageSignatureDescription;
import nl.esi.poosl.xtext.descriptions.PooslProcessMethodDescription;
import nl.esi.poosl.xtext.helpers.HelperFunctions;
import nl.esi.poosl.xtext.helpers.PooslVariableTypeHelper;

public class PooslJavaValidatorTypes extends PooslJavaValidatorAcyclicRelations {
	private static final String SIGNATURE_EXPRESSIONS_INCOMPATIBLE = "Expressions of type {0} is incompatible with type String";
	private static final String RECEPTION_INCOMPATIBLE = "Reception condition of type {0} is incompatible with type Boolean";
	private static final String CONDITION_INCOMPATIBLE = "Condition of type {0} is incompatible with type Boolean";
	private static final String GUARD_INCOMPATIBLE = "Guard of type {0} is incompatible with type Boolean";
	private static final String DELAY_INCOMPATIBLE = "Delay of type {0} is incompatible with type Integer and Real";

	private static final String PROCESSMETHOD_OVERRIDDE_OUTPUT_TYPE = "Output types {0} are not equal to, or subtypes of, the output types {1} of the overridden method in class {2}";
	private static final String PROCESSMETHOD_OVERRIDDE_INPUT_TYPE = "Input types {0} are not equal to, or supertypes of, the input types {1} of the overridden method in class {2}";
	private static final String DATAMETHOD_RETURN_TYPE = "Method body of type {0} is not compatible with method return type {1}";
	private static final String DATAMETHOD_OVERRIDDEN_RETURN_TYPE = "Return type {0} is not equal to, or subtype of, the return type {1} of the overridden method in class {2}";
	private static final String DATAMETHOD_TYPES = "Parameter types {0} are not equal to, or supertypes of, the parameter types {1} of the overridden method in class {2}";

	private static final String SIGNATURE_RECEIVE_NOT_FOUND = "No receive message {0} of port {1} with {2} variables";
	private static final String SIGNATURE_RECEIVE_INCOMPATIBLE = "Variable of type {0} is incompatible with declaration type {1}";
	private static final String SIGNATURE_RECEIVE_GLOBAL_NOT_FOUND = "No receive message {0} with these argument types as arguments";

	private static final String SIGNATURE_SEND_GLOBAL_NOT_FOUND = "No send message {0} with these argument types as arguments";
	private static final String SIGNATURE_SEND_INCOMPATIBLE = "Argument of type {0} is incompatible with declaration type {1}";
	private static final String SIGNATURE_SEND_NOT_FOUND = "No send message {0} of port {1} with {2} arguments";

	private static final String PROCESSMETHOD_CALL_OUTPUT_INCOMPATIBLE = "Output variable of type {0} is incompatible with declaration type {1}";
	private static final String PROCESSMETHOD_CALL_OUTPUT_SIZE = "Expected {0} output variables instead of {1}";
	private static final String PROCESSMETHOD_CALL_ARG_INCOMPATIBLE = "Input argument of type {0} is incompatible with declaration type {1}";
	private static final String PROCESSMETHOD_CALL_ARG_SIZE = "Expected {0} input arguments instead of {1}";
	public static final String NOT_DECLARED = "{0} ''{1}'' is not declared.";

	private static final String INSTANCE_PARAMETERS_INCOMPATIBLE = "Expression of type {0} is not compatible with variable of type {1}";

	private final PooslTypeSystem typeSystem = new PooslTypeSystem(this);

	public void generateError(String message, EObject source, EStructuralFeature feature, int index, String code,
			String... issueCode) {
		error(message, source, feature, index, code, issueCode);
	}

	public void generateWarning(String message, EObject source, EStructuralFeature feature, int index, String code,
			String... issueCode) {
		warning(message, source, feature, index, code, WarningType.TYPECHECK, issueCode);
	}

	// --- Methods -------

	@Check(CheckType.FAST)
	public void checkTypesDataMethodReturn(DataMethod dataMethod) {
		Resource resource = dataMethod.eResource();
		String returnTypeName = dataMethod.getReturnType();
		String bodyTypeName = typeSystem.getAndCheckExpressionType(dataMethod.getBody());
		if (!TypingHelper.isCompatible(resource, bodyTypeName, returnTypeName)) {
			warning(MessageFormat.format(DATAMETHOD_RETURN_TYPE, bodyTypeName, returnTypeName),
					Literals.DATA_METHOD__RETURN_TYPE, PooslIssueCodes.INVALID_RETURN_TYPE, WarningType.TYPECHECK,
					bodyTypeName, returnTypeName);
		}
	}

	@Check(CheckType.FAST)
	public void checkTypesDataMethodNamed(DataMethodNamed dataMethod) {
		checkTypesDataMethod(dataMethod, dataMethod.getName(), Literals.DATA_CLASS__DATA_METHODS_NAMED);
	}

	@Check(CheckType.FAST)
	public void checkTypesDataMethodUnaryOperator(DataMethodUnaryOperator dataMethod) {
		checkTypesDataMethod(dataMethod, dataMethod.getName().getLiteral(),
				Literals.DATA_CLASS__DATA_METHODS_UNARY_OPERATOR);
	}

	@Check(CheckType.FAST)
	public void checkTypesDataMethodBinaryOperator(DataMethodBinaryOperator dataMethod) {
		checkTypesDataMethod(dataMethod, dataMethod.getName().getLiteral(),
				Literals.DATA_CLASS__DATA_METHODS_BINARY_OPERATOR);
	}

	private void checkTypesDataMethod(DataMethod dataMethod, String name, EReference literal) {
		Resource resource = dataMethod.eResource();
		DataClass dClass = (DataClass) dataMethod.eContainer();

		List<String> types = TypingHelper.getDeclarationTypeNames(dataMethod.getParameters());
		String outputType = dataMethod.getReturnType();
		String superClass = HelperFunctions.getCorrectedDataClassExtendsAsString(dClass);
		List<IEObjectDescription> methods = PooslCache.get(resource).getDataMethods(superClass, name, types.size(),
				literal);

		for (IEObjectDescription dMethod : methods) {
			List<String> typesP = PooslDataMethodDescription.getParameterTypeNames(dMethod);

			String dClassName = PooslDataMethodDescription.getClassName(dMethod);
			if (!TypingHelper.isSubtype(resource, typesP, types)) {
				StringBuilder sbTypes = new StringBuilder();
				FormattingHelper.formatTypes(sbTypes, types);
				StringBuilder sbTypesP = new StringBuilder();
				FormattingHelper.formatTypes(sbTypesP, typesP);

				warning(MessageFormat.format(DATAMETHOD_TYPES, sbTypes, sbTypesP, dClassName),
						Literals.DATA_METHOD__PARAMETERS, PooslIssueCodes.INVALID_OVERRIDE_PARAMETER_TYPE,
						WarningType.TYPECHECK, sbTypesP.toString());
			}

			String outputTypeP = PooslDataMethodDescription.getReturnType(dMethod);
			if (!TypingHelper.isSubtype(resource, outputType, outputTypeP)) {
				warning(MessageFormat.format(DATAMETHOD_OVERRIDDEN_RETURN_TYPE, outputType, outputTypeP, dClassName),
						Literals.DATA_METHOD__RETURN_TYPE, PooslIssueCodes.INVALID_OVERRIDE_RETURN_TYPE,
						WarningType.TYPECHECK, outputTypeP);
			}
		}
	}

	@Check(CheckType.FAST)
	public void checkTypesProcessMethod(ProcessMethod pMethod) {
		Resource resource = pMethod.eResource();
		ProcessClass pClass = (ProcessClass) pMethod.eContainer();
		String superClassName = pClass.getSuperClass();

		if (superClassName != null) {
			List<String> inputTypes = TypingHelper.getDeclarationTypeNames(pMethod.getInputParameters());
			List<String> outputTypes = TypingHelper.getDeclarationTypeNames(pMethod.getOutputParameters());

			IEObjectDescription parentMethod = PooslCache.get(resource)
					.getProcessMethods(superClassName, inputTypes.size(), outputTypes.size()).get(pMethod.getName());
			if (parentMethod != null) {
				List<String> inputTypesP = PooslProcessMethodDescription.getInputParameterTypeNames(parentMethod);
				List<String> outputTypesP = PooslProcessMethodDescription.getOutputParameterTypeNames(parentMethod);

				if (!TypingHelper.isSubtype(resource, inputTypesP, inputTypes)) {
					StringBuilder sbTypes = new StringBuilder();
					FormattingHelper.formatTypes(sbTypes, inputTypes);
					StringBuilder sbTypesP = new StringBuilder();
					FormattingHelper.formatTypes(sbTypesP, inputTypesP);

					warning(MessageFormat.format(PROCESSMETHOD_OVERRIDDE_INPUT_TYPE, sbTypes, sbTypesP, superClassName),
							Literals.PROCESS_METHOD__INPUT_PARAMETERS,
							PooslIssueCodes.INVALID_OVERRIDE_INPUT_PARAMETER_TYPE, WarningType.TYPECHECK,
							sbTypesP.toString());
				}

				if (!TypingHelper.isSubtype(resource, outputTypes, outputTypesP)) {
					StringBuilder sbTypes = new StringBuilder();
					FormattingHelper.formatTypes(sbTypes, outputTypes);
					StringBuilder sbTypesP = new StringBuilder();
					FormattingHelper.formatTypes(sbTypesP, outputTypesP);

					warning(MessageFormat.format(PROCESSMETHOD_OVERRIDDE_OUTPUT_TYPE, sbTypes, sbTypesP,
							superClassName), Literals.PROCESS_METHOD__OUTPUT_PARAMETERS,
							PooslIssueCodes.INVALID_OVERRIDE_OUTPUT_PARAMETER_TYPE, WarningType.TYPECHECK);
				}
			}
		}
	}

	// --- Statement -------

	@Check(CheckType.FAST)
	public void checkTypesExpressionStatement(ExpressionStatement statement) {
		typeSystem.getAndCheckExpressionType(statement.getExpression());
	}

	@Check(CheckType.FAST)
	public void checkTypesDelayStatement(DelayStatement statement) {
		Resource resource = statement.eResource();
		Expression expression = statement.getExpression();
		String typeName = typeSystem.getAndCheckExpressionType(expression);
		if (!TypingHelper.isCompatible(resource, typeName, HelperFunctions.CLASS_NAME_FLOAT)
				&& !TypingHelper.isCompatible(resource, typeName, HelperFunctions.CLASS_NAME_INTEGER)
				&& !TypingHelper.isCompatible(resource, typeName, HelperFunctions.CLASS_NAME_REAL)) {
			warning(MessageFormat.format(DELAY_INCOMPATIBLE, typeName), expression, null, PooslIssueCodes.TYPECHECK,
					WarningType.TYPECHECK);
		}
	}

	@Check(CheckType.FAST)
	public void checkTypesGuardedStatement(GuardedStatement statement) {
		Resource resource = statement.eResource();
		Expression expressions = statement.getGuard();
		String typeName = typeSystem.getAndCheckExpressionType(expressions);
		if (!TypingHelper.isCompatible(resource, typeName, HelperFunctions.CLASS_NAME_BOOLEAN)) {
			warning(MessageFormat.format(GUARD_INCOMPATIBLE, typeName), expressions, null,
					PooslIssueCodes.INCOMPATIBLE_TYPE, WarningType.TYPECHECK, typeName, "Boolean");
		}
	}

	@Check(CheckType.FAST)
	public void checkTypesIfStatement(IfStatement statement) {
		Resource resource = statement.eResource();
		Expression expressions = statement.getCondition();
		String typeName = typeSystem.getAndCheckExpressionType(expressions);
		if (!TypingHelper.isCompatible(resource, typeName, HelperFunctions.CLASS_NAME_BOOLEAN)) {
			warning(MessageFormat.format(CONDITION_INCOMPATIBLE, typeName), expressions, null,
					PooslIssueCodes.INCOMPATIBLE_TYPE, WarningType.TYPECHECK, typeName, "Boolean");
		}
	}

	@Check(CheckType.FAST)
	public void checkTypesSwitchStatement(SwitchStatement statement) {
		Expression expression = statement.getExpression();
		String expressionType = typeSystem.getAndCheckExpressionType(expression);

		for (SwitchStatementCase switchCase : statement.getCases()) {
			Expression caseValue = switchCase.getValue();
			String caseValueType = typeSystem.getAndCheckExpressionType(expression);
			if (!TypingHelper.isCompatible(statement.eResource(), caseValueType, expressionType)) {
				warning("Case condition of type " + caseValueType + " is not compatible with switch expression of type "
						+ expressionType, caseValue, null, PooslIssueCodes.INCOMPATIBLE_TYPE, WarningType.TYPECHECK,
						caseValueType, expressionType);
			}
		}
	}

	@Check(CheckType.FAST)
	public void checkTypesWhileStatement(WhileStatement statement) {
		Resource resource = statement.eResource();
		Expression expressions = statement.getCondition();
		String typeName = typeSystem.getAndCheckExpressionType(expressions);
		if (!TypingHelper.isCompatible(resource, typeName, HelperFunctions.CLASS_NAME_BOOLEAN)) {
			warning(MessageFormat.format(CONDITION_INCOMPATIBLE, typeName), expressions, null,
					PooslIssueCodes.INCOMPATIBLE_TYPE, WarningType.TYPECHECK, typeName, "Boolean");
		}
	}

	@Check(CheckType.FAST)
	public void checkTypesReceiveMessage(ReceiveStatement statement) {
		Resource resource = statement.eResource();
		ProcessClass pClass = HelperFunctions.getContainingProcessClass(statement);
		String statementMsgName = statement.getName();
		String statementPortName = ((PortReference) statement.getPortDescriptor()).getPort();

		List<String> argumentTypes = new ArrayList<>();
		for (OutputVariable variable : statement.getVariables()) {
			argumentTypes.add(PooslVariableTypeHelper.getVariableType(statement, variable.getVariable()));
		}
		String pName = pClass.getName();
		if (pName != null) {
			checkMessageSignature(resource, statement.getPortDescriptor(), pName, argumentTypes, statementMsgName,
					statementPortName, PooslMessageType.RECEIVE);
		}

		// --- getReceptionCondition() -------
		Expression expressions = statement.getReceptionCondition();
		String typeName = typeSystem.getAndCheckExpressionType(expressions);
		if (!TypingHelper.isCompatible(resource, typeName, HelperFunctions.CLASS_NAME_BOOLEAN)) {
			warning(MessageFormat.format(RECEPTION_INCOMPATIBLE, typeName), expressions, null,
					PooslIssueCodes.INCOMPATIBLE_TYPE, WarningType.TYPECHECK, typeName, "Boolean");
		}

		// --- getPostCommunicationExpressions() -------
		typeSystem.getAndCheckExpressionType(statement.getPostCommunicationExpression());
	}

	@Check(CheckType.FAST)
	public void checkTypesSendMessage(SendStatement statement) {
		Resource resource = statement.eResource();
		ProcessClass pClass = HelperFunctions.getContainingProcessClass(statement);
		String statementMsgName = statement.getName();
		String statementPortName = ((PortReference) statement.getPortDescriptor()).getPort();
		List<String> argumentTypes = typeSystem.getAndCheckExpressionsType(statement.getArguments());
		String pName = pClass.getName();
		if (pName != null) {
			checkMessageSignature(resource, statement.getPortDescriptor(), pName, argumentTypes, statementMsgName,
					statementPortName, PooslMessageType.SEND);
		}

		// --- getPostCommunicationExpressions() -------
		typeSystem.getAndCheckExpressionType(statement.getPostCommunicationExpression());
	}

	private void checkMessageSignature(Resource resource, EObject portDescriptor, String pClass,
			List<String> argumentTypes, String statementMsgName, String statementPortName, PooslMessageType type) {
		String invalidType;
		EReference variables;
		String warningNotfound;
		String warningIncompatible;
		String warningGlobalNotFound;
		if (type == PooslMessageType.SEND) {
			invalidType = PooslIssueCodes.INVALID_TYPES_SEND_STATEMENT;
			variables = Literals.SEND_STATEMENT__ARGUMENTS;
			warningNotfound = SIGNATURE_SEND_NOT_FOUND;
			warningIncompatible = SIGNATURE_SEND_INCOMPATIBLE;
			warningGlobalNotFound = SIGNATURE_SEND_GLOBAL_NOT_FOUND;
		} else {
			invalidType = PooslIssueCodes.INVALID_TYPES_RECEIVE_STATEMENT;
			variables = Literals.RECEIVE_STATEMENT__VARIABLES;
			warningNotfound = SIGNATURE_RECEIVE_NOT_FOUND;
			warningIncompatible = SIGNATURE_RECEIVE_INCOMPATIBLE;
			warningGlobalNotFound = SIGNATURE_RECEIVE_GLOBAL_NOT_FOUND;
		}

		if (portDescriptor instanceof PortReference) {
			if (statementPortName != null && statementMsgName != null) {
				List<String> declarationTypes = getDeclarationTypesOfSignature(resource, pClass, statementPortName,
						statementMsgName, type, argumentTypes.size());

				if (declarationTypes == null) {
					error(MessageFormat.format(warningNotfound, statementMsgName, statementPortName,
							argumentTypes.size()), null, PooslIssueCodes.MISSING_MESSAGE_DECLARATION);
				} else {
					for (int i = 0; i < argumentTypes.size(); i++) {
						String useTypeName = argumentTypes.get(i);
						String declarationType = declarationTypes.get(i);
						if (!TypingHelper.isCompatible(resource, useTypeName, declarationType)) {
							warning(MessageFormat.format(warningIncompatible, useTypeName, declarationType), variables,
									i, invalidType, WarningType.TYPECHECK);
						}
					}
				}
			}
		} else if (portDescriptor instanceof PortExpression) {
			String portDescriptorTypeName = typeSystem
					.getAndCheckExpressionType(((PortExpression) portDescriptor).getExpression());
			if (!TypingHelper.isCompatible(resource, portDescriptorTypeName, HelperFunctions.CLASS_NAME_STRING)) {
				warning(MessageFormat.format(SIGNATURE_EXPRESSIONS_INCOMPATIBLE, portDescriptorTypeName),
						portDescriptor, null, invalidType, WarningType.TYPECHECK);
			}

			List<List<String>> declarationTypesList = getDeclarationTypesOfAllSignature(resource, pClass,
					statementPortName, statementMsgName, type, argumentTypes.size());
			if (statementMsgName != null) {
				if (declarationTypesList.isEmpty()) {
					error(warningNotfound, null);
				} else {
					boolean globalFit = false;
					for (List<String> declarationTypes : declarationTypesList) {
						boolean localFit = TypingHelper.isCompatible(resource, argumentTypes, declarationTypes);
						globalFit = globalFit || localFit;
					}
					if (!globalFit) {
						warning(MessageFormat.format(warningGlobalNotFound, statementMsgName), variables, null,
								invalidType, WarningType.TYPECHECK);
					}
				}
			}
		}
	}

	private static List<List<String>> getDeclarationTypesOfAllSignature(Resource resource, String pClass, String port,
			String messageName, PooslMessageType expectedType, int paramCount) {
		List<List<String>> signatures = new ArrayList<>();
		if (pClass != null && port != null && expectedType != null) {
			Iterable<IEObjectDescription> messages = Iterables.filter(
					PooslCache.get(resource).getMessages(pClass, expectedType),
					PooslMessageSignatureDescription.predicateMessage(port, messageName));
			for (IEObjectDescription descr : messages) {
				List<String> parameterTypes = PooslMessageSignatureDescription.getParameterTypes(descr);
				if (paramCount == parameterTypes.size()) {
					signatures.add(parameterTypes);
				}
			}
		}
		return signatures;
	}

	private static List<String> getDeclarationTypesOfSignature(Resource resource, String pClass, String port,
			String messageName, PooslMessageType expectedType, int paramCount) {
		if (pClass != null && port != null && expectedType != null) {
			Iterable<IEObjectDescription> messages = Iterables.filter(
					PooslCache.get(resource).getMessages(pClass, expectedType),
					PooslMessageSignatureDescription.predicateMessage(port, messageName));
			for (IEObjectDescription descr : messages) {
				List<String> parameterTypes = PooslMessageSignatureDescription.getParameterTypes(descr);
				if (paramCount == parameterTypes.size()) {
					return parameterTypes;
				}
			}
		}
		return null;
	}

	@Check(CheckType.FAST)
	public void checkTypesProcessMethodCall(ProcessMethodCall pMethodCall) {
		Resource resource = pMethodCall.eResource();
		String pClass = HelperFunctions.getContainingProcessClass(pMethodCall).getName();
		if (pClass != null) {
			String useMethodName = pMethodCall.getMethod();
			List<Expression> useExpressions = pMethodCall.getInputArguments();
			List<OutputVariable> useVariables = pMethodCall.getOutputVariables();

			IEObjectDescription pMethod = PooslCache.get(resource)
					.getProcessMethods(pClass, useExpressions.size(), useVariables.size()).get(useMethodName);

			if (pMethod == null) {

				error(MessageFormat.format(NOT_DECLARED, Literals.PROCESS_METHOD.getName(), pMethodCall.getMethod()),
						pMethodCall, Literals.PROCESS_METHOD_CALL__METHOD, Diagnostic.LINKING_DIAGNOSTIC);
			} else {
				// --- getInputParameter -------
				List<String> inputParamTypes = PooslProcessMethodDescription.getInputParameterTypeNames(pMethod);
				if (inputParamTypes.size() != useExpressions.size()) {
					error(MessageFormat.format(PROCESSMETHOD_CALL_ARG_SIZE, inputParamTypes.size(),
							useExpressions.size()), Literals.PROCESS_METHOD_CALL__INPUT_ARGUMENTS);
				} else {
					for (int i = 0; i < useExpressions.size(); i++) {
						String useTypeName = typeSystem.getAndCheckExpressionType(useExpressions.get(i));
						String declarationTypeName = inputParamTypes.get(i);
						if (!TypingHelper.isCompatible(resource, useTypeName, declarationTypeName)) {
							warning(MessageFormat.format(PROCESSMETHOD_CALL_ARG_INCOMPATIBLE, useTypeName,
									declarationTypeName), useExpressions.get(i), null,
									PooslIssueCodes.INVALID_INPUT_TYPES_PROCESS_METHOD_CALL, WarningType.TYPECHECK,
									useTypeName, declarationTypeName);
						}
					}
				}

				// --- getOutputVariables -------
				List<String> outputParamTypes = PooslProcessMethodDescription.getOutputParameterTypeNames(pMethod);
				if (outputParamTypes.size() != useVariables.size()) {
					error(MessageFormat.format(PROCESSMETHOD_CALL_OUTPUT_SIZE, outputParamTypes.size(),
							useVariables.size()), Literals.PROCESS_METHOD_CALL__OUTPUT_VARIABLES);
				} else {
					for (int i = 0; i < useVariables.size(); i++) {
						String useTypeName = PooslVariableTypeHelper.getVariableType(pMethodCall,
								useVariables.get(i).getVariable());
						String declarationTypeName = outputParamTypes.get(i);

						if (!TypingHelper.isCompatible(resource, useTypeName, declarationTypeName)) {
							warning(MessageFormat.format(PROCESSMETHOD_CALL_OUTPUT_INCOMPATIBLE, useTypeName,
									declarationTypeName), Literals.PROCESS_METHOD_CALL__OUTPUT_VARIABLES, i,
									PooslIssueCodes.INVALID_OUTPUT_TYPES_PROCESS_METHOD_CALL, WarningType.TYPECHECK);
						}
					}
				}
			}
		}
	}

	// --- Instance -------

	@Check(CheckType.FAST)
	public void checkTypesInstanceParameter(InstanceParameter instanceParameter) {
		Resource resource = instanceParameter.eResource();
		String varTypeString = getParameterDescription(instanceParameter);

		Expression expression = instanceParameter.getExpression();
		String exprTypeString = typeSystem.getAndCheckExpressionType(expression);
		if (!TypingHelper.isCompatible(resource, exprTypeString, varTypeString)) {
			warning(MessageFormat.format(INSTANCE_PARAMETERS_INCOMPATIBLE, exprTypeString, varTypeString), null,
					PooslIssueCodes.INVALID_INSTANCE_PARAMETER_TYPE, WarningType.TYPECHECK, exprTypeString,
					varTypeString);
		}
	}

	private String getParameterDescription(InstanceParameter instanceParameter) {
		Resource resource = instanceParameter.eResource();
		Instance instance = (Instance) instanceParameter.eContainer();
		String literalFromObject = instance.getClassDefinition();
		String paramName = instanceParameter.getParameter();

		if (paramName != null) {
			IEObjectDescription descr = PooslCache.get(resource).getInstantiableClassParameters(literalFromObject)
					.get(paramName);
			if (descr != null) {
				return PooslDeclarationDescription.getType(descr);
			}
		}
		return null;
	}
}
