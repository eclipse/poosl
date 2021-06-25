package nl.esi.poosl.xtext.ui.quickfix;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.model.edit.ISemanticModification;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;

import nl.esi.poosl.AssignmentExpression;
import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.DataMethodBinaryOperator;
import nl.esi.poosl.DataMethodNamed;
import nl.esi.poosl.DataMethodUnaryOperator;
import nl.esi.poosl.Declaration;
import nl.esi.poosl.Expression;
import nl.esi.poosl.Instance;
import nl.esi.poosl.InstanceParameter;
import nl.esi.poosl.MessageSignature;
import nl.esi.poosl.OutputVariable;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.PortReference;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.ProcessMethodCall;
import nl.esi.poosl.ReceiveStatement;
import nl.esi.poosl.SendStatement;
import nl.esi.poosl.Statement;
import nl.esi.poosl.Variable;
import nl.esi.poosl.xtext.custom.FormattingHelper;
import nl.esi.poosl.xtext.custom.PooslCache;
import nl.esi.poosl.xtext.custom.PooslMessageType;
import nl.esi.poosl.xtext.custom.TypingHelper;
import nl.esi.poosl.xtext.descriptions.PooslDataMethodDescription;
import nl.esi.poosl.xtext.descriptions.PooslDeclarationDescription;
import nl.esi.poosl.xtext.descriptions.PooslMessageSignatureDescription;
import nl.esi.poosl.xtext.descriptions.PooslProcessMethodDescription;
import nl.esi.poosl.xtext.helpers.HelperFunctions;
import nl.esi.poosl.xtext.helpers.PooslMessageSignatureCallHelper;
import nl.esi.poosl.xtext.helpers.PooslReferenceHelper;
import nl.esi.poosl.xtext.helpers.PooslVariableTypeHelper;
import nl.esi.poosl.xtext.scoping.PooslScopeProvider;
import nl.esi.poosl.xtext.validation.PooslIssueCodes;
import nl.esi.poosl.xtext.validation.PooslJavaValidatorSuppress.WarningType;

public class PooslQuickfixProviderTypes extends PooslQuickfixProviderUniqueIdentifiers {

	private boolean applyConversionMethod(Issue issue, Expression element, IModificationContext context) {
		if (issue.getData().length >= 2) {
			List<TextChange> changes = getConversionChanges(element, issue.getData()[0], issue.getData()[1]);
			if (changes == null)
				return false;
			if (!applyTextChanges(context.getXtextDocument(), element.eResource(), changes)) {
				showWarning("Conversion method could not be applied.");
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	@Fix(PooslIssueCodes.TYPECHECK)
	public void typecheck(final Issue issue, IssueResolutionAcceptor acceptor) {
		addSuppressWarning(issue, acceptor, WarningType.TYPECHECK);
	}

	@Fix(PooslIssueCodes.MISSING_MESSAGE_DECLARATION)
	public void missingMessageDeclarationAll(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Extend the message declarations based on all statements in this process", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						Statement issueStatement = (Statement) element;
						ProcessClass pClass = HelperFunctions.getContainingProcessClass(issueStatement);
						List<ReceiveStatement> usedReceiveMessages = new ArrayList<>();
						List<SendStatement> usedSendMessages = new ArrayList<>();
						getUsedMessagesFromProcess(pClass, usedSendMessages, usedReceiveMessages);
						if (!updateMessageDeclarations(pClass, context.getXtextDocument(), usedReceiveMessages,
								usedSendMessages)) {
							showWarning("Message declarations could not be extended based on all statements.");
						}
					}
				});
	}

	@Fix(PooslIssueCodes.MISSING_MESSAGE_DECLARATION)
	public void missingMessageDeclarationOne(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Extend the message declarations based on this statement", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						Statement issueStatement = (Statement) element;
						ProcessClass pClass = HelperFunctions.getContainingProcessClass(issueStatement);
						List<ReceiveStatement> usedReceiveMessages = new LinkedList<>();
						List<SendStatement> usedSendMessages = new LinkedList<>();
						if (issueStatement instanceof ReceiveStatement) {
							usedReceiveMessages.add((ReceiveStatement) issueStatement);
						}
						if (issueStatement instanceof SendStatement) {
							usedSendMessages.add((SendStatement) issueStatement);
						}
						if (!updateMessageDeclarations(pClass, context.getXtextDocument(), usedReceiveMessages,
								usedSendMessages)) {
							showWarning("Message declarations could not be extended based on this statement.");
						}
					}
				});
	}

	@Fix(PooslIssueCodes.INVALID_TYPES_RECEIVE_STATEMENT)
	public void invalidTypesReceiveStatementAll(final Issue issue, IssueResolutionAcceptor acceptor) {
		addSuppressWarning(issue, acceptor, WarningType.TYPECHECK);
		acceptor.accept(issue, "Extend the message declarations based on all statements in this process", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						Statement issueStatement = (Statement) element;
						ProcessClass pClass = HelperFunctions.getContainingProcessClass(issueStatement);
						List<ReceiveStatement> usedReceiveMessages = new ArrayList<>();
						List<SendStatement> usedSendMessages = new ArrayList<>();
						getUsedMessagesFromProcess(pClass, usedSendMessages, usedReceiveMessages);
						if (!updateMessageDeclarations(pClass, context.getXtextDocument(), usedReceiveMessages,
								usedSendMessages)) {
							showWarning("Message declarations could not be extended based on all statements.");
						}
					}
				});
	}

	@Fix(PooslIssueCodes.INVALID_TYPES_RECEIVE_STATEMENT)
	public void invalidTypesReceiveStatementOne(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Extend the message declarations based on this statement", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						Statement issueStatement = (Statement) element;
						ProcessClass pClass = HelperFunctions.getContainingProcessClass(issueStatement);
						List<ReceiveStatement> usedReceiveMessages = new LinkedList<>();
						List<SendStatement> usedSendMessages = new LinkedList<>();
						if (issueStatement instanceof ReceiveStatement) {
							usedReceiveMessages.add((ReceiveStatement) issueStatement);
						}
						if (issueStatement instanceof SendStatement) {
							usedSendMessages.add((SendStatement) issueStatement);
						}
						if (!updateMessageDeclarations(pClass, context.getXtextDocument(), usedReceiveMessages,
								usedSendMessages)) {
							showWarning("Message declarations could not be extended based on this statement.");
						}
					}
				});
	}

	@Fix(PooslIssueCodes.INVALID_TYPES_SEND_STATEMENT)
	public void invalidTypesSendStatementAll(final Issue issue, IssueResolutionAcceptor acceptor) {
		addSuppressWarning(issue, acceptor, WarningType.TYPECHECK);
		acceptor.accept(issue, "Extend the message declarations based on all statements in this process", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						Statement issueStatement = (Statement) element;
						ProcessClass pClass = HelperFunctions.getContainingProcessClass(issueStatement);
						List<ReceiveStatement> usedReceiveMessages = new ArrayList<>();
						List<SendStatement> usedSendMessages = new ArrayList<>();
						getUsedMessagesFromProcess(pClass, usedSendMessages, usedReceiveMessages);
						if (!updateMessageDeclarations(pClass, context.getXtextDocument(), usedReceiveMessages,
								usedSendMessages)) {
							showWarning("Message declarations could not be extended based on all statements.");
						}
					}
				});
	}

	@Fix(PooslIssueCodes.INVALID_TYPES_SEND_STATEMENT)
	public void invalidTypesSendStatementOne(final Issue issue, IssueResolutionAcceptor acceptor) {
		addSuppressWarning(issue, acceptor, WarningType.TYPECHECK);
		acceptor.accept(issue, "Extend the message declarations based on this statement", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						Statement issueStatement = (Statement) element;
						ProcessClass pClass = HelperFunctions.getContainingProcessClass(issueStatement);
						List<ReceiveStatement> usedReceiveMessages = new LinkedList<>();
						List<SendStatement> usedSendMessages = new LinkedList<>();
						if (issueStatement instanceof ReceiveStatement) {
							usedReceiveMessages.add((ReceiveStatement) issueStatement);
						}
						if (issueStatement instanceof SendStatement) {
							usedSendMessages.add((SendStatement) issueStatement);
						}
						if (!updateMessageDeclarations(pClass, context.getXtextDocument(), usedReceiveMessages,
								usedSendMessages)) {
							showWarning("Message declarations could not be extended based on this statement.");
						}
					}
				});
	}

	private boolean updateMessageDeclarations(ProcessClass pClass, IXtextDocument doc,
			List<ReceiveStatement> usedReceiveMessages, List<SendStatement> usedSendMessages) {
		Resource resource = pClass.eResource();

		Map<String, MessageChange> recMessageChanges = new HashMap<>();
		for (MessageSignature messageSignature : getScopeMessageSignatureObjects(pClass, PooslMessageType.RECEIVE)) {
			MessageChange change = new MessageChange(messageSignature);
			recMessageChanges.put(change.getID(), change);
		}

		for (ReceiveStatement statement : usedReceiveMessages) {
			if (statement.getPortDescriptor() instanceof PortReference) {
				String sigId = PooslMessageSignatureCallHelper.getSignatureID(statement);
				if (sigId != null) {
					List<String> types = new ArrayList<>();
					for (OutputVariable outVar : statement.getVariables()) {
						types.add(PooslVariableTypeHelper.getVariableType(statement, outVar.getVariable()));
					}
					MessageChange messageChange = recMessageChanges.get(sigId);
					if (messageChange != null) {
						messageChange.updateTypes(resource, types);
					} else {
						recMessageChanges.put(sigId, new MessageChange(sigId, types));
					}
				}
			}
		}

		Map<String, MessageChange> sendMessageChanges = new HashMap<>();
		for (MessageSignature messageSignature : getScopeMessageSignatureObjects(pClass, PooslMessageType.SEND)) {
			MessageChange change = new MessageChange(messageSignature);
			sendMessageChanges.put(change.getID(), change);
		}

		for (SendStatement statement : usedSendMessages) {
			if (statement.getPortDescriptor() instanceof PortReference) {
				String sigId = PooslMessageSignatureCallHelper.getSignatureID(statement);
				if (sigId != null) {
					List<String> types = pooslTypeSystem.getAndCheckExpressionsType(statement.getArguments());
					MessageChange messageChange = sendMessageChanges.get(sigId);
					if (messageChange != null) {
						messageChange.updateTypes(resource, types);
					} else {
						sendMessageChanges.put(sigId, new MessageChange(sigId, types));
					}
				}
			}
		}

		List<TextChange> changes = new ArrayList<>();

		StringBuilder messages = new StringBuilder();
		createNewMessageText(messages, sendMessageChanges.values(), "!");
		createNewMessageText(messages, recMessageChanges.values(), "?");
		changes.add(new TextChange(resource, getLastMessageNode(pClass).getTotalEndOffset(), 0, messages.toString()));

		updateExistingMessageText(recMessageChanges.values(), changes);
		updateExistingMessageText(sendMessageChanges.values(), changes);
		return applyTextChanges(doc, resource, changes);
	}

	@Fix(PooslIssueCodes.INVALID_TYPES_SEND_STATEMENT)
	public void invalidTypesSendStatement2(final Issue issue, IssueResolutionAcceptor acceptor) {
		addSuppressWarning(issue, acceptor, WarningType.TYPECHECK);
		acceptor.accept(issue, "Apply conversion method", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) throws BadLocationException {
						if (element instanceof Expression) {
							applyConversionMethod(issue, (Expression) element, context);
						}
					}
				});
	}

	@Fix(PooslIssueCodes.INVALID_TYPES_RECEIVE_STATEMENT)
	public void invalidTypesReceiveStatementVariables(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Change types of variables used in this receive statement", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						ReceiveStatement statement = (ReceiveStatement) element;

						IEObjectDescription receiveSign = PooslReferenceHelper
								.getReceiveSignatureDescription(statement);
						if (receiveSign != null) {
							List<String> sigTypes = PooslMessageSignatureDescription.getParameterTypes(receiveSign);
							if (changeOutputVariableTypes(element, statement.getVariables(), sigTypes, context)) {
								return;
							}
						}
						showWarning("Could not change types of variables used in this receive statement");
					}
				});
	}

	@Fix(PooslIssueCodes.INVALID_INPUT_TYPES_PROCESS_METHOD_CALL)
	public void invalidInputTypesProcessMethodCall(final Issue issue, IssueResolutionAcceptor acceptor) {
		addSuppressWarning(issue, acceptor, WarningType.TYPECHECK);
		acceptor.accept(issue, "Change types of input parameter declared in process method", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						Resource resource = element.eResource();
						ProcessMethodCall pMethodCall = (ProcessMethodCall) element.eContainer();
						IEObjectDescription descr = PooslReferenceHelper.getProcessMethodDescription(pMethodCall);
						if (descr != null) {
							EObject pMethod = descr.getEObjectOrProxy();
							if (pMethod.eIsProxy())
								pMethod = EcoreUtil2.resolve(pMethod, element);

							List<String> callTypes = pooslTypeSystem
									.getAndCheckExpressionsType(pMethodCall.getInputArguments());
							List<String> methodTypes = PooslProcessMethodDescription.getInputParameterTypeNames(descr);
							List<TextChange> changes = new ArrayList<>();
							for (int i = 0; i < callTypes.size(); i++) {
								String gca = TypingHelper.greatestCommonAncestor(resource, callTypes.get(i),
										methodTypes.get(i));
								Declaration decl = findDeclarationIndex(((ProcessMethod) pMethod).getInputParameters(),
										i);
								changes.add(new TextChange(decl, Literals.DECLARATION__TYPE, gca));
							}
							if (applyTextChanges(context.getXtextDocument(), resource, changes))
								return;
						}
						showWarning("The input parameters of the process method could not be changed.");
					}
				});
	}

	@Fix(PooslIssueCodes.INVALID_INPUT_TYPES_PROCESS_METHOD_CALL)
	public void invalidInputTypesProcessMethodCall2(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Apply conversion method", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) throws BadLocationException {
						if (element instanceof Expression) {
							applyConversionMethod(issue, (Expression) element, context);
						}
					}
				});
	}

	@Fix(PooslIssueCodes.INVALID_OUTPUT_TYPES_PROCESS_METHOD_CALL)
	public void invalidOutputTypesProcessMethodCall(final Issue issue, IssueResolutionAcceptor acceptor) {
		addSuppressWarning(issue, acceptor, WarningType.TYPECHECK);
		acceptor.accept(issue, "Change types of output parameters declared in process method", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						Resource resource = element.eResource();
						ProcessMethodCall pMethodCall = (ProcessMethodCall) element;

						IEObjectDescription descr = PooslReferenceHelper.getProcessMethodDescription(pMethodCall);
						if (descr != null) {
							List<String> methodTypes = PooslProcessMethodDescription.getOutputParameterTypeNames(descr);
							EObject pMethod = descr.getEObjectOrProxy();
							if (pMethod.eIsProxy())
								pMethod = EcoreUtil2.resolve(pMethod, element);

							List<TextChange> changes = new ArrayList<>();
							for (int i = 0; i < methodTypes.size(); i++) {
								OutputVariable outputVariable = pMethodCall.getOutputVariables().get(i);
								String callType = PooslVariableTypeHelper.getVariableType(outputVariable,
										outputVariable.getVariable());
								String gca = TypingHelper.greatestCommonAncestor(resource, callType,
										methodTypes.get(i));
								Declaration decl = findDeclarationIndex(((ProcessMethod) pMethod).getOutputParameters(),
										i);
								if (decl != null) {
									changes.add(new TextChange(decl, Literals.DECLARATION__TYPE, gca));
								}
							}
							if (applyTextChanges(context.getXtextDocument(), resource, changes))
								return;
						}
						showWarning("The output parameters of the process method could not be changed.");
					}
				});
	}

	@Fix(PooslIssueCodes.INVALID_OUTPUT_TYPES_PROCESS_METHOD_CALL)
	public void invalidOutputTypesProcessMethodCall2(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Change types of output variables used in process method call", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {

						ProcessMethodCall pMethodCall = (ProcessMethodCall) element;
						IEObjectDescription descr = PooslReferenceHelper.getProcessMethodDescription(pMethodCall);
						if (descr != null) {
							List<String> methodTypes = PooslProcessMethodDescription.getOutputParameterTypeNames(descr);
							if (changeOutputVariableTypes(element, pMethodCall.getOutputVariables(), methodTypes,
									context)) {
								return;
							}
						}
						showWarning("The types of the output variable can not be changed.");
					}
				});
	}

	private boolean changeOutputVariableTypes(EObject element, EList<OutputVariable> outputVariables,
			List<String> actualTypes, IModificationContext context) {
		Resource resource = element.eResource();
		List<String> callTypes = new ArrayList<>();
		for (OutputVariable outVar : outputVariables) {
			callTypes.add(PooslVariableTypeHelper.getVariableType(outVar, outVar.getVariable()));
		}

		Map<String, String> varChanges = new HashMap<>();
		for (int i = actualTypes.size() - 1; i >= 0; i--) {
			String varName = outputVariables.get(i).getVariable();
			String newType = varChanges.get(varName);
			String varType = (newType != null) ? newType : PooslVariableTypeHelper.getVariableType(element, varName);
			String sigType = actualTypes.get(i);

			String gca = TypingHelper.greatestCommonAncestor(resource, varType, sigType);
			varChanges.put(varName, gca);
		}

		List<TextChange> changes = new ArrayList<>();
		IScope vars = PooslScopeProvider.getScopeOutputVariableVariable(element);

		for (IEObjectDescription description : vars.getAllElements()) {
			String varName = HelperFunctions.getName(description);
			if (varChanges.containsKey(varName)) {
				EObject obj = description.getEObjectOrProxy();
				if (obj.eIsProxy()) {
					obj = EcoreUtil.resolve(obj, element);
				}
				changes.add(new TextChange(((Variable) obj).eContainer(), Literals.DECLARATION__TYPE,
						varChanges.get(varName)));
			}
		}
		return applyTextChanges(context.getXtextDocument(), resource, changes);
	}

	@Fix(PooslIssueCodes.INVALID_INSTANCE_PARAMETER_TYPE)
	public void invalidInstanceParameterType(final Issue issue, IssueResolutionAcceptor acceptor) {
		addSuppressWarning(issue, acceptor, WarningType.TYPECHECK);
		acceptor.accept(issue, "Change types of instance parameters", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						Resource resource = element.eResource();
						InstanceParameter currentIParameter = (InstanceParameter) element;
						Instance instance = (Instance) currentIParameter.eContainer();

						String iClass = instance.getClassDefinition();
						Map<String, IEObjectDescription> expectedParams = PooslCache.get(resource)
								.getInstantiableClassParameters(iClass);
						List<TextChange> changes = new ArrayList<>();

						for (InstanceParameter instanceParameter : instance.getInstanceParameters()) {
							String actualType = pooslTypeSystem
									.getAndCheckExpressionType(instanceParameter.getExpression());
							if (instanceParameter.getParameter() != null) {
								IEObjectDescription descr = expectedParams.get(instanceParameter.getParameter());
								if (descr != null) {
									String expType = PooslDeclarationDescription.getType(descr);
									if (!TypingHelper.isSubtype(resource, expType, actualType)) {
										String gca = TypingHelper.greatestCommonAncestor(resource, expType, actualType);
										EObject var = descr.getEObjectOrProxy();
										if (var.eIsProxy()) {
											var = EcoreUtil.resolve(var, element);
										}
										changes.add(new TextChange(var.eContainer(), Literals.DECLARATION__TYPE, gca));
									}
								}
							}
						}
						if (!applyTextChanges(context.getXtextDocument(), resource, changes)) {
							showWarning("Could not change the instance parameter types.");
						}
					}
				});
	}

	@Fix(PooslIssueCodes.INVALID_INSTANCE_PARAMETER_TYPE)
	public void invalidInstanceParameterType2(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Apply conversion method", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) throws BadLocationException {
						if (element instanceof InstanceParameter) {
							applyConversionMethod(issue, ((InstanceParameter) element).getExpression(), context);
						}
					}
				});
	}

	@Fix(PooslIssueCodes.INCOMPATIBLE_TYPE)
	public void incompatibleType(final Issue issue, IssueResolutionAcceptor acceptor) {
		addSuppressWarning(issue, acceptor, WarningType.TYPECHECK);
		acceptor.accept(issue, "Apply conversion method", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) throws BadLocationException {
						if (element instanceof Expression) {
							applyConversionMethod(issue, (Expression) element, context);
						}
					}
				});
	}

	@Fix(PooslIssueCodes.INVALID_RETURN_TYPE)
	public void invalidReturnType(final Issue issue, IssueResolutionAcceptor acceptor) {
		addSuppressWarning(issue, acceptor, WarningType.TYPECHECK);
		acceptor.accept(issue, "Change return type", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						if (element instanceof DataMethodNamed) {
							DataMethodNamed dataMethod = (DataMethodNamed) element;
							Resource eResource = dataMethod.eResource();

							String returnType = dataMethod.getReturnType();
							String bodyType = pooslTypeSystem.getAndCheckExpressionType(dataMethod.getBody());

							if (returnType != null && bodyType != null) {
								String gca = TypingHelper.greatestCommonAncestor(eResource, returnType, bodyType);
								applyTextChange(context.getXtextDocument(), eResource, dataMethod,
										Literals.DATA_METHOD__RETURN_TYPE, gca);
							}
						}
					}
				});
	}

	@Fix(PooslIssueCodes.INVALID_RETURN_TYPE)
	public void invalidReturnType2(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Apply conversion method", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) throws BadLocationException {
						if (element instanceof DataMethodNamed) {
							applyConversionMethod(issue, ((DataMethodNamed) element).getBody(), context);
						}
					}
				});
	}

	@Fix(PooslIssueCodes.INVALID_OVERRIDE_RETURN_TYPE)
	public void invalidOverrideReturnType(final Issue issue, IssueResolutionAcceptor acceptor) {
		addSuppressWarning(issue, acceptor, WarningType.TYPECHECK);
		acceptor.accept(issue, "Change return type of super class", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						Resource resource = element.eResource();
						DataMethod dataMethod = (DataMethod) element;
						DataClass dClass = (DataClass) element.eContainer();
						String outputType = dataMethod.getReturnType();

						// Get parent Method
						int args = HelperFunctions.computeNumberOfVariables(dataMethod.getParameters());
						String dClassName = HelperFunctions.getCorrectedDataClassExtendsAsString(resource,
								dClass.getName());
						EReference ref = null;
						String dMethodName = "";
						if (dataMethod instanceof DataMethodUnaryOperator) {
							dMethodName = ((DataMethodUnaryOperator) dataMethod).getName().getLiteral();
							ref = Literals.DATA_CLASS__DATA_METHODS_UNARY_OPERATOR;
						} else if (dataMethod instanceof DataMethodBinaryOperator) {
							dMethodName = ((DataMethodBinaryOperator) dataMethod).getName().getLiteral();
							ref = Literals.DATA_CLASS__DATA_METHODS_BINARY_OPERATOR;
						} else {
							dMethodName = ((DataMethodNamed) dataMethod).getName();
							ref = Literals.DATA_CLASS__DATA_METHODS_NAMED;
						}
						IEObjectDescription method = PooslReferenceHelper.getDataMethod(resource, dClassName,
								dMethodName, args, ref);

						String outputTypeP = PooslDataMethodDescription.getReturnType(method);
						if (method != null) {
							EObject dataMethodP = method.getEObjectOrProxy();
							if (dataMethodP.eIsProxy()) {
								dataMethodP = EcoreUtil2.resolve(dataMethodP, element);
							}
							if (applyTextChange(context.getXtextDocument(), resource, dataMethodP,
									Literals.DATA_METHOD__RETURN_TYPE,
									TypingHelper.greatestCommonAncestor(resource, outputType, outputTypeP)))
								return;
						}
						showWarning("Failed to change the return type in the super class.");
					}
				});
	}

	@Fix(PooslIssueCodes.INVALID_OVERRIDE_PARAMETER_TYPE)
	public void invalidOverrideParameterType(final Issue issue, IssueResolutionAcceptor acceptor) {
		addSuppressWarning(issue, acceptor, WarningType.TYPECHECK);
		acceptor.accept(issue, "Change parameter types", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						Resource resource = element.eResource();
						DataMethod dataMethod = (DataMethod) element;
						List<String> inputTypes = TypingHelper.getDeclarationTypeNames(dataMethod.getParameters());
						String[] inputTypesP = FormattingHelper.unformatTypeNames(issue.getData()[0]);

						List<TextChange> changes = new ArrayList<>();
						for (int i = 0; i < inputTypesP.length; i++) {
							if (!TypingHelper.isSubtype(resource, inputTypes.get(i), inputTypesP[i])) {
								changes.add(new TextChange(dataMethod.getParameters().get(i),
										Literals.DECLARATION__TYPE, TypingHelper.greatestCommonAncestor(resource,
												inputTypes.get(i), inputTypesP[i])));
							}
							if (applyTextChanges(context.getXtextDocument(), resource, changes))
								return;
						}
						showWarning("Parameter types could not be changed");
					}
				});
	}

	@Fix(PooslIssueCodes.INVALID_OVERRIDE_INPUT_PARAMETER_TYPE)
	public void invalidOverrideInputParameterType(final Issue issue, IssueResolutionAcceptor acceptor) {
		addSuppressWarning(issue, acceptor, WarningType.TYPECHECK);
		acceptor.accept(issue, "Change input types", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						Resource resource = element.eResource();
						ProcessMethod pMethod = (ProcessMethod) element;

						List<String> inputTypes = TypingHelper.getDeclarationTypeNames(pMethod.getInputParameters());
						String[] inputTypesP = FormattingHelper.unformatTypeNames(issue.getData()[0]);
						List<TextChange> changes = new ArrayList<>();

						for (int i = 0; i < inputTypesP.length; i++) {
							String gca = TypingHelper.greatestCommonAncestor(resource, inputTypesP[i],
									inputTypes.get(i));
							changes.add(new TextChange(pMethod.getInputParameters().get(i), Literals.DECLARATION__TYPE,
									gca));
						}
						if (!applyTextChanges(context.getXtextDocument(), resource, changes)) {
							showWarning("Input types could not be changed.");
						}
					}
				});
	}

	@Fix(PooslIssueCodes.INVALID_OVERRIDE_OUTPUT_PARAMETER_TYPE)
	public void invalidOverrideOutputParameterType(final Issue issue, IssueResolutionAcceptor acceptor) {
		addSuppressWarning(issue, acceptor, WarningType.TYPECHECK);
		acceptor.accept(issue, "Change output types of super class method", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						ProcessMethod pMethod = (ProcessMethod) element;
						Resource resource = pMethod.eResource();

						ProcessClass pClass = HelperFunctions.getContainingProcessClass(pMethod);
						int nrInput = HelperFunctions.computeNumberOfVariables(pMethod.getInputParameters());
						int nrOutput = HelperFunctions.computeNumberOfVariables(pMethod.getOutputParameters());
						IEObjectDescription superMethod = PooslCache.get(resource)
								.getProcessMethods(pClass.getSuperClass(), nrInput, nrOutput).get(pMethod.getName());

						if (superMethod != null) {
							List<String> outputTypesP = PooslProcessMethodDescription
									.getOutputParameterTypeNames(superMethod);
							List<String> outputTypes = TypingHelper
									.getDeclarationTypeNames(pMethod.getOutputParameters());

							EObject obj = superMethod.getEObjectOrProxy();
							if (obj.eIsProxy()) {
								obj = EcoreUtil.resolve(obj, pMethod);
							}
							ProcessMethod pMethodP = (ProcessMethod) obj;
							List<TextChange> changes = new ArrayList<>();
							for (int i = 0; i < outputTypesP.size(); i++) {
								String gca = TypingHelper.greatestCommonAncestor(resource, outputTypesP.get(i),
										outputTypes.get(i));
								changes.add(new TextChange(pMethodP.getOutputParameters().get(i),
										Literals.DECLARATION__TYPE, gca));
							}
							if (applyTextChanges(context.getXtextDocument(), resource, changes)) {
								return;
							}
						}
						showWarning("Output type could not be changed.");
					}
				});
	}

	@Fix(PooslIssueCodes.INVALID_ASSIGNMENT_TYPE)
	public void invalidAssignmentType(final Issue issue, IssueResolutionAcceptor acceptor) {
		addSuppressWarning(issue, acceptor, WarningType.TYPECHECK);
		acceptor.accept(issue, "Change type of variable declaration", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						AssignmentExpression assignmentExpression = (AssignmentExpression) element;
						Resource resource = assignmentExpression.eResource();

						String gca = TypingHelper.greatestCommonAncestor(resource, issue.getData()[1],
								issue.getData()[0]);
						IEObjectDescription descr = PooslReferenceHelper.getVariableDescription(assignmentExpression);
						if (descr != null) {
							EObject obj = descr.getEObjectOrProxy();
							if (obj.eIsProxy()) {
								obj = EcoreUtil.resolve(obj, element);
							}
							applyTextChange(context.getXtextDocument(), resource, ((Variable) obj).eContainer(),
									Literals.DECLARATION__TYPE, gca);
						}
					}
				});
	}

	@Fix(PooslIssueCodes.INVALID_ASSIGNMENT_TYPE)
	public void invalidAssignmentType2(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Apply conversion method", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) throws BadLocationException {
						if (element instanceof AssignmentExpression) {
							applyConversionMethod(issue, ((AssignmentExpression) element).getExpression(), context);
						}
					}
				});
	}

	// --- Helper -------

	private void createNewMessageText(StringBuilder messages, Collection<MessageChange> changes, String messageType) {
		for (MessageChange mChange : changes) {
			if (mChange.isNewMessage()) {
				PooslMessageSignatureCallHelper message = new PooslMessageSignatureCallHelper(mChange.id);
				messages.append("\n\t" + message.getPort() + messageType + message.getName() + "(");

				for (int i = 0; i < mChange.changedTypes.length; i++) {
					messages.append(mChange.changedTypes[i]);
					if (i < mChange.changedTypes.length - 1) {
						messages.append(",");
					}
				}
				messages.append(")");
			}
		}
	}

	private void updateExistingMessageText(Collection<MessageChange> recChanges, List<TextChange> changes) {
		for (MessageChange messageChange : recChanges) {
			if (!messageChange.isNewMessage()) {
				for (int i = 0; i < messageChange.changedTypes.length; i++) {
					String type = messageChange.changedTypes[i];
					if (type != null) {
						changes.add(new TextChange(messageChange.signature.getParameters().get(i), type));
					}
				}
			}
		}
	}

	private INode getLastMessageNode(ProcessClass pClass) {
		INode lastNode = null;
		for (MessageSignature messageSignature : pClass.getReceiveMessages()) {
			ICompositeNode node = NodeModelUtils.getNode(messageSignature);
			if (lastNode == null) {
				lastNode = node;
			} else {
				if (node.getStartLine() > lastNode.getStartLine()) {
					lastNode = node;
				}
			}
		}
		for (MessageSignature messageSignature : pClass.getSendMessages()) {
			ICompositeNode node = NodeModelUtils.getNode(messageSignature);
			if (lastNode == null) {
				lastNode = node;
			} else {
				if (node.getStartLine() > lastNode.getStartLine()) {
					lastNode = node;
				}
			}
		}
		if (lastNode != null) {
			return lastNode;
		}

		ICompositeNode pNode = NodeModelUtils.findActualNodeFor(pClass);
		for (ILeafNode iLeafNode : pNode.getLeafNodes()) {
			if (iLeafNode.getText().equals("messages")) {
				return iLeafNode;
			}
		}

		// The keyword messages is always present, if not parsing will fail.
		// To be complete we will return the lastNode if it isn't.
		if (pNode.getLeafNodes().iterator().hasNext()) {
			return pNode.getLeafNodes().iterator().next();
		}
		return pNode;
	}

	private Declaration findDeclarationIndex(List<Declaration> declarations, int vIndex) {
		int variableIndex = vIndex;
		for (Declaration decl : declarations) {
			variableIndex -= decl.getVariables().size();
			if (variableIndex < 0) {
				return decl;
			}
		}
		return null;
	}

	private static Iterable<MessageSignature> getScopeMessageSignatureObjects(ProcessClass pClass,
			PooslMessageType type) {
		List<MessageSignature> signs = new ArrayList<>();
		for (IEObjectDescription description : PooslScopeProvider.getScopeMessages(pClass, type)) {
			EObject obj = description.getEObjectOrProxy();
			if (obj.eIsProxy()) {
				obj = EcoreUtil2.resolve(obj, pClass);
			}
			signs.add((MessageSignature) obj);
		}
		return signs;
	}

	/**
	 * Returns messages used in this process /**
	 * 
	 * @param pClass
	 * @return
	 */

	public static void getUsedMessagesFromProcess(ProcessClass pClass, List<SendStatement> usedSendMessages,
			List<ReceiveStatement> usedReceiveMessages) {
		for (ProcessMethod pMethod : pClass.getMethods()) {
			if (pMethod instanceof ProcessMethod) {
				TreeIterator<EObject> ti = pMethod.eAllContents();
				while (ti.hasNext()) {
					EObject eobj = ti.next();

					if (eobj instanceof SendStatement) {
						usedSendMessages.add((SendStatement) eobj);
					}
					if (eobj instanceof ReceiveStatement) {
						usedReceiveMessages.add((ReceiveStatement) eobj);
					}
				}
			}
		}
	}

	/**
	 * Helper Class to record any changes to a signature
	 * 
	 * @author kstaal
	 *
	 */
	class MessageChange {
		private String id;
		private String[] currentTypes;
		private String[] changedTypes;
		private MessageSignature signature;
		private Boolean newMessage;

		public MessageChange(MessageSignature signature) {
			id = PooslMessageSignatureCallHelper.getSignatureID(signature);
			this.signature = signature;
			int sizeParams = signature.getParameters().size();
			currentTypes = new String[sizeParams];
			changedTypes = new String[sizeParams];
			for (int i = 0; i < sizeParams; i++) {
				currentTypes[i] = signature.getParameters().get(i).getType();
			}
			newMessage = false;
		}

		public MessageChange(String sigId, List<String> types) {
			id = sigId;
			changedTypes = new String[types.size()];
			for (int i = 0; i < changedTypes.length; i++) {
				changedTypes[i] = types.get(i);
			}
			newMessage = true;
		}

		public Boolean isNewMessage() {
			return newMessage;
		}

		public String getID() {
			return id;
		}

		private void updateTypes(Resource resource, List<String> types) {
			for (int i = 0; i < types.size(); i++) {
				String useType = types.get(i);
				String declarationType = getType(i);
				if (!TypingHelper.isCompatible(resource, useType, declarationType)) {
					changedTypes[i] = TypingHelper.greatestCommonAncestor(resource, useType, declarationType);
				}
			}
		}

		private String getType(int i) {
			return (changedTypes[i] != null) ? changedTypes[i] : currentTypes[i];
		}
	}

}
