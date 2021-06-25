package nl.esi.poosl.xtext.ui.quickfix;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.impl.HiddenLeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.model.edit.ISemanticModification;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.eclipse.xtext.validation.Issue;

import nl.esi.poosl.AssignmentExpression;
import nl.esi.poosl.BinaryOperatorExpression;
import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.DataMethodCallExpression;
import nl.esi.poosl.Expression;
import nl.esi.poosl.ExpressionSequence;
import nl.esi.poosl.Instance;
import nl.esi.poosl.InstanceParameter;
import nl.esi.poosl.InstancePort;
import nl.esi.poosl.InstantiableClass;
import nl.esi.poosl.OutputVariable;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.ProcessMethodCall;
import nl.esi.poosl.ReturnExpression;
import nl.esi.poosl.UnaryOperatorExpression;
import nl.esi.poosl.xtext.custom.PooslCache;
import nl.esi.poosl.xtext.custom.PooslTypeSystem;
import nl.esi.poosl.xtext.descriptions.PooslDataMethodDescription;
import nl.esi.poosl.xtext.helpers.HelperFunctions;
import nl.esi.poosl.xtext.helpers.PooslReferenceHelper;
import nl.esi.poosl.xtext.helpers.PooslVariableTypeHelper;
import nl.esi.poosl.xtext.validation.PooslIssueCodes;

public class PooslQuickfixProviderUnresolved extends PooslQuickfixProviderUnusedElements {
	protected final PooslTypeSystem pooslTypeSystem = new PooslTypeSystem(null);

	@Fix(PooslIssueCodes.UNDECLARED_DATA_METHOD_BINARY)
	public void undeclaredDataMethodBinary(final Issue issue, final IssueResolutionAcceptor acceptor) {
		final String message = issue.getMessage();
		final String missingDeclarationName = message.substring(message.indexOf('\'') + 1, message.lastIndexOf('\''));
		acceptor.accept(issue, "Create method '" + missingDeclarationName + "'", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						Resource resource = element.eResource();
						BinaryOperatorExpression dataMethodCall = (BinaryOperatorExpression) element;
						String leftOperand = pooslTypeSystem.getAndCheckExpressionType(dataMethodCall.getLeftOperand());
						IEObjectDescription dataDescr = PooslCache.get(resource).getDataClassMap().get(leftOperand);

						if (dataDescr == null) {
							showWarning(
									"Quickfix could not be applied because the type of the left operant could not be determined.");
							return;
						}

						String varType = pooslTypeSystem.getAndCheckExpressionType(dataMethodCall.getRightOperand());
						if (varType == null) {
							varType = HelperFunctions.CLASS_NAME_OBJECT;
						}
						String returnType = HelperFunctions.CLASS_NAME_OBJECT;
						String method = "\n\t" + dataMethodCall.getName() + "(arg : " + varType + ") : " + returnType
								+ "\n" + "\t\treturn self";
						EObject targetType = dataDescr.getEObjectOrProxy();
						if (targetType.eIsProxy()) {
							targetType = EcoreUtil2.resolve(targetType, element);
						}
						INode lastNode = getLastMethodNode((DataClass) targetType);
						if (lastNode == null || !applyTextChange(context.getXtextDocument(), resource, targetType,
								lastNode.getTotalEndOffset(), 0, method)) {
							showWarning("Could not add datamethod to class \"" + leftOperand
									+ "\". The file that contains the definition of the data class \"" + leftOperand
									+ "\" cannot be opened.");
						}
					}
				});
	}

	@Fix(PooslIssueCodes.UNDECLARED_DATA_METHOD_BINARY)
	public void undeclaredDataMethodBinary2(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Apply conversion method to right argument", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) throws BadLocationException {
						Resource resource = element.eResource();
						BinaryOperatorExpression dataMethodCall = (BinaryOperatorExpression) element;
						Expression argument = dataMethodCall.getRightOperand();

						String leftOperand = pooslTypeSystem.getAndCheckExpressionType(dataMethodCall.getLeftOperand());
						String rightOperand = pooslTypeSystem.getAndCheckExpressionType(argument);
						IEObjectDescription method = PooslReferenceHelper.getDataMethod(resource, leftOperand,
								dataMethodCall.getName().getLiteral(), 1,
								Literals.DATA_CLASS__DATA_METHODS_BINARY_OPERATOR);

						if (method != null) {
							String expClass = PooslDataMethodDescription.getParameterTypeNames(method).get(0);
							List<TextChange> changes = getConversionChanges(argument, rightOperand, expClass);
							if (changes == null)
								return;
							if (applyTextChanges(context.getXtextDocument(), resource, changes))
								return;
						}
						showWarning("No method could be found to convert '" + rightOperand + "' to the correct type.");
					}
				});
	}

	@Fix(PooslIssueCodes.UNDECLARED_DATA_METHOD_UNARY)
	public void undeclaredDataMethodUnary(final Issue issue, final IssueResolutionAcceptor acceptor) {
		final String message = issue.getMessage();
		final String missingDeclarationName = message.substring(message.indexOf('\'') + 1, message.lastIndexOf('\''));
		acceptor.accept(issue, "Create method '" + missingDeclarationName + "'", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						Resource resource = element.eResource();
						UnaryOperatorExpression dataMethodCall = (UnaryOperatorExpression) element;
						String targetTypeName = pooslTypeSystem.getAndCheckExpressionType(dataMethodCall.getOperand());
						IEObjectDescription targetDescr = PooslCache.get(resource).getDataClassMap()
								.get(targetTypeName);

						if (targetDescr == null) {
							showWarning(
									"Quickfix could not be applied because the type of the left operant could not be determined.");
							return;
						}

						String returnType = HelperFunctions.CLASS_NAME_OBJECT;
						String method = "\n\t" + dataMethodCall.getName().getLiteral() + " : " + returnType
								+ "\n\t\treturn self";
						EObject object = targetDescr.getEObjectOrProxy();
						if (object.eIsProxy()) {
							object = EcoreUtil2.resolve(object, element);
						}
						DataClass targetType = (DataClass) object;
						INode lastNode = getLastMethodNode(targetType);
						if (lastNode == null || !applyTextChange(context.getXtextDocument(), resource, targetType,
								lastNode.getTotalEndOffset(), 0, method)) {
							showWarning("Could not add datamethod to class \"" + targetTypeName
									+ "\". The file that contains the definition of the data class \"" + targetTypeName
									+ "\" cannot be opened.");
						}
					}
				});
	}

	@Fix(PooslIssueCodes.UNDECLARED_DATA_METHOD_NAMED)
	public void undeclaredDataMethodNamed(final Issue issue, final IssueResolutionAcceptor acceptor) {
		final String message = issue.getMessage();
		final String missingDeclarationName = message.substring(message.indexOf('\'') + 1, message.lastIndexOf('\''));
		acceptor.accept(issue, "Create method '" + missingDeclarationName + "'", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						Resource resource = element.eResource();
						DataMethodCallExpression dataMethodCall = (DataMethodCallExpression) element;
						String targetTypeName = pooslTypeSystem.getAndCheckExpressionType(dataMethodCall.getTarget());
						IEObjectDescription targetDescr = PooslCache.get(resource).getDataClassMap()
								.get(targetTypeName);

						if (targetDescr == null) {
							showWarning(
									"Quickfix could not be applied because the type of the left operant could not be determined.");
							return;
						}

						String vars = createArgumentString(dataMethodCall.getArguments());
						String returnType = HelperFunctions.CLASS_NAME_OBJECT;
						String method = "\n\t" + dataMethodCall.getName() + "(" + vars + ") : " + returnType
								+ "\n\t\treturn self";
						EObject object = targetDescr.getEObjectOrProxy();
						if (object.eIsProxy()) {
							object = EcoreUtil2.resolve(object, element);
						}
						DataClass targetType = (DataClass) object;
						INode lastNode = getLastMethodNode(targetType);
						if (lastNode == null || !applyTextChange(context.getXtextDocument(), resource, targetType,
								lastNode.getTotalEndOffset(), 0, method)) {
							showWarning("Could not add datamethod to class \"" + targetType.getName()
									+ "\". The file that contains the definition of the data class \""
									+ targetType.getName() + "\" cannot be opened.");
						}
					}
				});
	}

	@Fix(PooslIssueCodes.UNDECLARED_DATA_METHOD_NAMED)
	public void undeclaredDataMethodNamed2(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Apply conversion methods", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) throws BadLocationException {
						Resource resource = element.eResource();
						DataMethodCallExpression dataMethodCall = (DataMethodCallExpression) element;
						List<Expression> arguments = dataMethodCall.getArguments();
						String targetType = pooslTypeSystem.getAndCheckExpressionType(dataMethodCall.getTarget());
						IEObjectDescription method = PooslReferenceHelper.getDataMethod(resource, targetType,
								dataMethodCall.getName(), arguments.size(), Literals.DATA_CLASS__DATA_METHODS_NAMED);

						if (method != null) {
							List<TextChange> totalChanges = new ArrayList<>();
							List<String> parameterTypeNames = PooslDataMethodDescription.getParameterTypeNames(method);
							for (int i = 0; i < parameterTypeNames.size(); i++) {
								String actualType = pooslTypeSystem.getAndCheckExpressionType(arguments.get(i));
								List<TextChange> changes = getConversionChanges(arguments.get(i), actualType,
										parameterTypeNames.get(i));
								if (changes == null)
									return;
								totalChanges.addAll(changes);
							}
							if (!applyTextChanges(context.getXtextDocument(), resource, totalChanges)) {
								showWarning("Could not apply all conversion methods.");
							}
						}
					}
				});
	}

	protected List<TextChange> getConversionChanges(Expression element, String fromClass, String toClass) {
		if (!fromClass.equals(toClass) && toClass != null) {
			Map<String, IEObjectDescription> conversionMethods = PooslCache.get(element.eResource())
					.getDataMethodsNamed(fromClass, 0, toClass);

			if (!conversionMethods.isEmpty()) {
				String asMethod = null;
				String printStringMethod = null;
				String nonReturnObjectMethod = null;
				for (Entry<String, IEObjectDescription> entry : conversionMethods.entrySet()) {
					if ("printString".equals(entry.getKey())) {
						printStringMethod = entry.getKey();
					} else if (("as" + toClass).equals(entry.getKey())) {
						asMethod = entry.getKey();
					} else {
						String className = PooslDataMethodDescription.getClassName(entry.getValue());
						String returnType = PooslDataMethodDescription.getReturnType(entry.getValue());
						if (!HelperFunctions.CLASS_NAME_OBJECT.equals(className)
								&& !HelperFunctions.CLASS_NAME_OBJECT.equals(returnType)) {
							nonReturnObjectMethod = entry.getKey();
						}
					}
				}
				String dMethodName = null;
				if (asMethod != null) {
					dMethodName = asMethod;
				} else if (printStringMethod != null) {
					dMethodName = printStringMethod;
				} else if (nonReturnObjectMethod != null) {
					dMethodName = nonReturnObjectMethod;
				}

				if (dMethodName != null) {
					Expression lastExpr = element;
					while (lastExpr instanceof ExpressionSequence || lastExpr instanceof ReturnExpression) {
						if (lastExpr instanceof ExpressionSequence) {
							List<Expression> exprs = ((ExpressionSequence) lastExpr).getExpressions();
							lastExpr = (exprs.isEmpty()) ? null : exprs.get(exprs.size() - 1);
						} else if (lastExpr instanceof ReturnExpression) {
							lastExpr = ((ReturnExpression) lastExpr).getExpression();
						}
					}
					if (lastExpr != null) {
						ICompositeNode node = NodeModelUtils.getNode(lastExpr);
						List<TextChange> changes = new ArrayList<>();
						if (lastExpr instanceof BinaryOperatorExpression || lastExpr instanceof AssignmentExpression) {
							changes.add(new TextChange(lastExpr.eResource(), node.getOffset(), 0, "("));
							changes.add(new TextChange(lastExpr.eResource(), node.getOffset() + node.getLength(), 0,
									") " + dMethodName));
							return changes;
						} else {
							changes.add(new TextChange(lastExpr.eResource(), node.getTotalEndOffset(), 0,
									" " + dMethodName));
						}
						return changes;
					}
				}
			}
		}
		showWarning("No method could be found to convert '" + fromClass + "' to '" + toClass + "'.");
		return null;
	}

	@Fix(org.eclipse.xtext.diagnostics.Diagnostic.LINKING_DIAGNOSTIC)
	public void undeclaredVariable(final Issue issue, final IssueResolutionAcceptor acceptor) {
		IModificationContext modificationContext = getModificationContextFactory().createModificationContext(issue);
		final IXtextDocument xtextDocument = modificationContext.getXtextDocument();
		xtextDocument.readOnly(new IUnitOfWork.Void<XtextResource>() {
			@Override
			public void process(XtextResource xtextResource) throws Exception {
				EObject cause = xtextResource.getResourceSet().getEObject(issue.getUriToProblem(), true);
				final String message = issue.getMessage();
				final String missingDeclarationName = message.substring(message.indexOf('\'') + 1,
						message.lastIndexOf('\''));
				final String issueUriToProblem = issue.getUriToProblem().toString();
				if (message.startsWith("Variable")) {
					// A reference is made to an instance parameter so only show
					// the quickfix to add instance parameters
					if (issueUriToProblem.contains("instanceParameters")) {
						acceptCreateInstanceClassParameter(issue, acceptor, missingDeclarationName);
					} else {
						// Add a quickfix to add the missing variable as class
						// variable, if its not the initial method call also add
						// quickfix as local variable
						if (!issueUriToProblem.contains("initialMethodCall")) {
							acceptCreateLocalVariable(issue, acceptor, missingDeclarationName);
						}
						acceptCreateClassVariable(issue, acceptor, missingDeclarationName);
					}
					// If the missing variable is inside a processClass also add
					// quickfix to add it as a parameter of that class
					if (issueUriToProblem.contains("processClasses")) {
						acceptCreateClassParameter(issue, acceptor, missingDeclarationName);
					}
				} else if (message.startsWith("Port")) {
					// If the missing port is referenced in a process or
					// clusterclass
					// but not as an instanceport add this quickfix
					if ((issueUriToProblem.contains("processClasses") || issueUriToProblem.contains("clusterClasses"))
							&& !issueUriToProblem.contains("instancePorts")) {
						acceptCreatePort(issue, acceptor, missingDeclarationName);
					}
					// Else add the quickfix to add the port to the referenced
					// clusterclass or processclass (only available in
					// instanceports so
					// in system and cluster).
					else if ((issueUriToProblem.contains("clusterClasses") || issueUriToProblem.contains("system"))
							&& issueUriToProblem.contains("instancePorts")) {
						if (cause instanceof InstancePort
								&& ((InstancePort) cause).getInstance().getClassDefinition() != null) {
							acceptCreateInstancePort(issue, acceptor, missingDeclarationName);
						}
					}
				} else if (message.startsWith("ProcessMethod")) {
					acceptCreateProcessMethod(issue, acceptor, missingDeclarationName);
				} else if (message.startsWith("InstantiableClass")) {
					acceptCreateProcessClass(issue, acceptor, missingDeclarationName);
					// This quickfix cannot be executed as a semantic
					// modification
					// because the end result contains an error (missing process
					// class someClass).
					// The serializer cannot serialize the class if it contains
					// errors.
					acceptCreateClusterClass(issue, acceptor, missingDeclarationName);
				} else if (message.startsWith("DataClass")) {
					acceptCreateDataClass(issue, acceptor, missingDeclarationName);
				}
			}

			private void acceptCreateDataClass(final Issue issue, final IssueResolutionAcceptor acceptor,
					final String missingDeclarationName) {
				acceptor.accept(issue, "Create data class '" + missingDeclarationName + "'", // label
						null, // description
						null, // icon
						new ISemanticModification() {
							public void apply(EObject element, IModificationContext context) {
								String dataClass = "\n\ndata class " + missingDeclarationName + " extends Object"
										+ "\nvariables" + "\nmethods";
								if (!applyTextChange(context.getXtextDocument(), element.eResource(), element,
										context.getXtextDocument().getLength(), 0, dataClass)) {
									showWarning("Could not create Data Class '" + missingDeclarationName + "'.");
								}
							}
						});
			}

			private void acceptCreateClusterClass(final Issue issue, final IssueResolutionAcceptor acceptor,
					final String missingDeclarationName) {
				acceptor.accept(issue, "Create cluster class '" + missingDeclarationName + "'", // label
						null, // description
						null, // icon
						new ISemanticModification() {
							public void apply(EObject element, IModificationContext context) throws Exception {
								String clusterClass = "\n\ncluster class " + missingDeclarationName + "()\nports"
										+ "\ninstances" + "\n\tsomeInstance: someClass()" + "\nchannels\n";
								if (!applyTextChange(context.getXtextDocument(), element.eResource(), element,
										context.getXtextDocument().getLength(), 0, clusterClass)) {
									showWarning("Could not create Cluster Class '" + missingDeclarationName + "'.");
								}
							}
						});
			}

			private void acceptCreateProcessClass(final Issue issue, final IssueResolutionAcceptor acceptor,
					final String missingDeclarationName) {
				acceptor.accept(issue, "Create process class '" + missingDeclarationName + "'", // label
						null, // description
						null, // icon
						new ISemanticModification() {
							public void apply(EObject element, IModificationContext context) {
								String processClass = "\n\nprocess class " + missingDeclarationName + "\nports"
										+ "\nmessages\nvariables\ninit\n\tsomeMethod()()\nmethods"
										+ "\n\tsomeMethod()()\n\t\tskip";
								if (!applyTextChange(context.getXtextDocument(), element.eResource(), element,
										context.getXtextDocument().getLength(), 0, processClass)) {
									showWarning("Could not create Process Class '" + missingDeclarationName + "'.");
								}
							}
						});
			}

			private void acceptCreateProcessMethod(final Issue issue, final IssueResolutionAcceptor acceptor,
					final String missingDeclarationName) {
				acceptor.accept(issue, "Create method '" + missingDeclarationName + "'", // label
						null, // description
						null, // icon
						new ISemanticModification() {
							public void apply(EObject element, IModificationContext context) {
								ProcessClass pClass = HelperFunctions.getContainingProcessClass(element);
								if (pClass != null) {
									String input = "";
									String output = "";

									if (element instanceof ProcessMethodCall) {
										ProcessMethodCall processMethodCall = (ProcessMethodCall) element;
										input = createArgumentString(processMethodCall.getInputArguments());

										StringBuilder outputBuilder = new StringBuilder();

										for (int j = 0; j < processMethodCall.getOutputVariables().size(); j++) {
											if (j != 0) {
												outputBuilder.append(" , ");
											}
											OutputVariable outputVariable = processMethodCall.getOutputVariables()
													.get(j);
											String varType = PooslVariableTypeHelper.getVariableType(outputVariable,
													outputVariable.getVariable());
											if (varType == null) {
												varType = HelperFunctions.CLASS_NAME_OBJECT;
											}
											outputBuilder.append("outputVar" + j + " : " + varType);
										}
										output = outputBuilder.toString();
									}

									String method = "\n\t" + missingDeclarationName + "(" + input + ")(" + output
											+ ") | | \n\t\tskip";
									INode lastNode = getLastMethodNode(pClass);
									if (lastNode == null || !applyTextChange(context.getXtextDocument(),
											element.eResource(), pClass, lastNode.getTotalEndOffset(), 0, method)) {
										showWarning(
												"Could not create process method '" + missingDeclarationName + "'.");
									}
								}
							}
						});
			}

			private void acceptCreateInstancePort(final Issue issue, final IssueResolutionAcceptor acceptor,
					final String missingDeclarationName) {
				acceptor.accept(issue, "Create port '" + missingDeclarationName + "'.", // label
						null, // description
						null, // icon
						new ISemanticModification() {
							public void apply(EObject element, IModificationContext context) {
								InstancePort instancePort = (InstancePort) element;
								if (instancePort.getInstance() == null
										|| instancePort.getInstance().getClassDefinition() == null) {
									showWarning(
											"Could not create port because the class definition could not be determined.");
									return;
								}
								InstantiableClass iClass = PooslReferenceHelper
										.getInstantiableClassEObject(instancePort.getInstance());
								INode lastNode = getLastPortNode(iClass);

								if (lastNode != null) {
									applyTextChange(context.getXtextDocument(), element.eResource(), iClass,
											lastNode.getTotalEndOffset(), 0, "\n\t" + missingDeclarationName);
								} else {
									showWarning("Could not create the new port '" + missingDeclarationName + "'.");
								}
							}
						});
			}

			private void acceptCreatePort(final Issue issue, final IssueResolutionAcceptor acceptor,
					final String missingDeclarationName) {
				acceptor.accept(issue, "Create port '" + missingDeclarationName + "'", // label
						null, // description
						null, // icon
						new ISemanticModification() {
							public void apply(EObject element, IModificationContext context) {
								EObject container = element.eContainer();
								while (!(container instanceof InstantiableClass) && container != null) {
									container = container.eContainer();
								}

								if (container instanceof InstantiableClass) {
									InstantiableClass iClass = (InstantiableClass) container;
									INode lastNode = getLastPortNode(iClass);
									if (lastNode != null) {
										applyTextChange(context.getXtextDocument(), element.eResource(), iClass,
												lastNode.getTotalEndOffset(), 0, "\n\t" + missingDeclarationName);
										return;
									}
								}
								showWarning("Could not create the new port '" + missingDeclarationName + "'.");
							}
						});
			}

			private void acceptCreateClassParameter(final Issue issue, final IssueResolutionAcceptor acceptor,
					final String missingDeclarationName) {
				acceptor.accept(issue, "Create class parameter '" + missingDeclarationName + "'", // label
						null, // description
						null, // icon
						new ISemanticModification() {
							public void apply(EObject element, IModificationContext context) {

								ProcessClass pClass = HelperFunctions.getContainingProcessClass(element);
								if (pClass != null) {
									StringBuilder decl = new StringBuilder(
											missingDeclarationName + " : " + HelperFunctions.CLASS_NAME_OBJECT);
									INode lastNode = getLastInstanceParamNode(pClass, decl);
									if (lastNode != null
											&& applyTextChange(context.getXtextDocument(), element.eResource(), pClass,
													lastNode.getTotalEndOffset(), 0, decl.toString())) {
										return;
									}
								}
								showWarning("Could not create class parameter '" + missingDeclarationName + "'.");
							}
						});
			}

			private void acceptCreateClassVariable(final Issue issue, final IssueResolutionAcceptor acceptor,
					final String missingDeclarationName) {
				acceptor.accept(issue, "Create class variable '" + missingDeclarationName + "'", // label
						null, // description
						null, // icon
						new ISemanticModification() {
							public void apply(EObject element, IModificationContext context) {
								EObject container = element.eContainer();
								while (!(container instanceof ProcessClass || container instanceof DataClass)
										&& container != null) {
									container = container.eContainer();
								}

								INode lastNode = null;
								String decl = "\n\t" + missingDeclarationName + " : "
										+ HelperFunctions.CLASS_NAME_OBJECT;
								if (container instanceof ProcessClass) {
									lastNode = getLastVariableNode((ProcessClass) container);
								} else if (container instanceof DataClass) {
									lastNode = getLastVariableNode((DataClass) container);
								}

								if (lastNode != null && applyTextChange(context.getXtextDocument(), element.eResource(),
										container, lastNode.getTotalEndOffset(), 0, decl)) {
									return;
								}
								showWarning("Could not create class variable '" + missingDeclarationName + "'.");
							}
						});
			}

			private void acceptCreateLocalVariable(final Issue issue, final IssueResolutionAcceptor acceptor,
					final String missingDeclarationName) {
				acceptor.accept(issue, "Create local variable '" + missingDeclarationName + "'", // label
						null, // description
						null, // icon
						new ISemanticModification() {
							public void apply(EObject element, IModificationContext context) {
								EObject container = element.eContainer();
								while (!(container instanceof ProcessMethod || container instanceof DataMethod)
										&& container != null) {
									container = container.eContainer();
								}

								StringBuilder decl = new StringBuilder(
										" " + missingDeclarationName + " : " + HelperFunctions.CLASS_NAME_OBJECT);
								INode lastNode = null;
								if (container instanceof ProcessMethod) {
									lastNode = getLastLocalVarNode((ProcessMethod) container, decl,
											Literals.PROCESS_METHOD__LOCAL_VARIABLES, Literals.PROCESS_METHOD__BODY);
								} else if (container instanceof DataMethod) {
									lastNode = getLastLocalVarNode((DataMethod) container, decl,
											Literals.DATA_METHOD__LOCAL_VARIABLES, Literals.DATA_METHOD__BODY);
								}

								if (lastNode != null && applyTextChange(context.getXtextDocument(), element.eResource(),
										container, lastNode.getTotalEndOffset(), 0, decl.toString())) {
									return;
								}
								showWarning("Failed to add local variable '" + missingDeclarationName + "'.");
							}
						});
			}

			private void acceptCreateInstanceClassParameter(final Issue issue, final IssueResolutionAcceptor acceptor,
					final String missingDeclarationName) {
				acceptor.accept(issue, "Create instance class parameter '" + missingDeclarationName + "'", // label
						null, // description
						null, // icon
						new ISemanticModification() {
							public void apply(EObject element, IModificationContext context) {
								InstanceParameter iParam = (InstanceParameter) element;
								EObject container = iParam.eContainer();
								if (container instanceof Instance) {
									Instance instance = (Instance) container;
									if (instance.getClassDefinition() != null) {
										InstantiableClass iClass = PooslReferenceHelper
												.getInstantiableClassEObject(instance);
										String varType = pooslTypeSystem
												.getAndCheckExpressionType(iParam.getExpression());
										if (varType == null) {
											varType = HelperFunctions.CLASS_NAME_OBJECT;
										}

										StringBuilder dec = new StringBuilder(missingDeclarationName + " : " + varType);
										INode lastNode = getLastInstanceParamNode(iClass, dec);

										if (lastNode != null
												&& applyTextChange(context.getXtextDocument(), element.eResource(),
														iClass, lastNode.getTotalEndOffset(), 0, dec.toString())) {
											return;
										}
									}
								}
								showWarning("Failed to add instance parameter '" + missingDeclarationName + "'.");
							}
						});
			}
		});
	}

	private INode getLastLocalVarNode(EObject object, StringBuilder builder, EReference refLocal, EReference refBody) {
		List<INode> nodes = NodeModelUtils.findNodesForFeature(object, refLocal);
		if (!nodes.isEmpty()) {
			builder.insert(0, ",");
			return nodes.get(nodes.size() - 1);
		}

		INode lastNode = null;
		INode bodyNode = null;
		List<INode> bodyNodes = NodeModelUtils.findNodesForFeature(object, refBody);
		if (!bodyNodes.isEmpty())
			bodyNode = bodyNodes.get(0);

		for (ILeafNode iLeafNode : NodeModelUtils.getNode(object).getLeafNodes()) {
			if (bodyNode != null && iLeafNode.getOffset() >= bodyNode.getOffset())
				break;

			if (!iLeafNode.isHidden()) {
				if (iLeafNode.getText().equals("|"))
					return iLeafNode;
				lastNode = iLeafNode;
			}
		}
		builder.insert(0, " |").append(" |");
		return lastNode;
	}

	private INode getLastVariableNode(DataClass dClass) {
		List<INode> nodes = NodeModelUtils.findNodesForFeature(dClass, Literals.DATA_CLASS__INSTANCE_VARIABLES);
		return getLastVariableNode(dClass, nodes);
	}

	private INode getLastVariableNode(ProcessClass pClass) {
		List<INode> nodes = NodeModelUtils.findNodesForFeature(pClass, Literals.PROCESS_CLASS__INSTANCE_VARIABLES);
		return getLastVariableNode(pClass, nodes);
	}

	private INode getLastVariableNode(EObject object, List<INode> nodes) {
		if (!nodes.isEmpty())
			return nodes.get(nodes.size() - 1);

		for (ILeafNode iLeafNode : NodeModelUtils.findActualNodeFor(object).getLeafNodes()) {
			if (!iLeafNode.isHidden() && iLeafNode.getText().equals("variables"))
				return iLeafNode;

		}
		return null;
	}

	private INode getLastInstanceParamNode(InstantiableClass iClass, StringBuilder declaration) {

		List<INode> nodes = NodeModelUtils.findNodesForFeature(iClass, Literals.INSTANTIABLE_CLASS__PARAMETERS);
		if (!nodes.isEmpty()) {
			declaration.insert(0, " , ");
			return nodes.get(nodes.size() - 1);
		} else {
			List<INode> nameNodes = NodeModelUtils.findNodesForFeature(iClass, Literals.INSTANTIABLE_CLASS__NAME);
			if (nameNodes.isEmpty()) {
				return null;
			}
			INode nameNode = nameNodes.get(0);
			INode lastNode = nameNode.getNextSibling();
			while ((lastNode instanceof HiddenLeafNode) && lastNode.hasNextSibling()) {
				lastNode = lastNode.getNextSibling();
			}
			if (lastNode.getText().equals("(")) {
				return lastNode;
			} else {
				declaration.insert(0, "(");
				declaration.append(")");
				return nameNode;
			}
		}
	}

	private INode getLastMethodNode(DataClass targetType) {
		INode lastDataMethod = null;
		List<INode> binary = NodeModelUtils.findNodesForFeature(targetType,
				Literals.DATA_CLASS__DATA_METHODS_BINARY_OPERATOR);
		lastDataMethod = !binary.isEmpty() ? binary.get(binary.size() - 1) : lastDataMethod;

		List<INode> named = NodeModelUtils.findNodesForFeature(targetType, Literals.DATA_CLASS__DATA_METHODS_NAMED);
		lastDataMethod = (!named.isEmpty()
				&& (lastDataMethod == null || named.get(named.size() - 1).getOffset() > lastDataMethod.getOffset()))
						? named.get(named.size() - 1)
						: lastDataMethod;

		List<INode> unary = NodeModelUtils.findNodesForFeature(targetType,
				Literals.DATA_CLASS__DATA_METHODS_UNARY_OPERATOR);
		lastDataMethod = (!unary.isEmpty()
				&& (lastDataMethod == null || unary.get(unary.size() - 1).getOffset() > lastDataMethod.getOffset()))
						? unary.get(unary.size() - 1)
						: lastDataMethod;

		if (lastDataMethod == null) {
			ICompositeNode s = NodeModelUtils.findActualNodeFor(targetType);
			for (ILeafNode iLeafNode : s.getLeafNodes()) {
				if (!iLeafNode.isHidden() && iLeafNode.getText().equals("methods")) {
					return iLeafNode;
				}
			}

		}
		return lastDataMethod;
	}

	private INode getLastMethodNode(ProcessClass pClass) {
		List<INode> methods = NodeModelUtils.findNodesForFeature(pClass, Literals.PROCESS_CLASS__METHODS);
		INode lastDataMethod = (!methods.isEmpty()) ? methods.get(methods.size() - 1) : null;
		if (lastDataMethod == null) {
			ICompositeNode s = NodeModelUtils.findActualNodeFor(pClass);
			for (ILeafNode iLeafNode : s.getLeafNodes()) {
				if (!iLeafNode.isHidden() && iLeafNode.getText().equals("methods")) {
					return iLeafNode;
				}
			}

		}
		return lastDataMethod;
	}

	private String createArgumentString(List<Expression> arguments) {
		StringBuilder vars = new StringBuilder();
		for (int i = 0; i < arguments.size(); i++) {
			if (i != 0) {
				vars.append(" , ");
			}
			String varType = pooslTypeSystem.getAndCheckExpressionType(arguments.get(i));
			if (varType == null) {
				varType = HelperFunctions.CLASS_NAME_OBJECT;
			}
			vars.append("arg" + i + " : " + varType);
		}
		return vars.toString();
	}

	private INode getLastPortNode(InstantiableClass iClass) {
		List<INode> nodes = NodeModelUtils.findNodesForFeature(iClass, Literals.INSTANTIABLE_CLASS__PORTS);
		if (!nodes.isEmpty())
			return nodes.get(nodes.size() - 1);

		ICompositeNode s = NodeModelUtils.findActualNodeFor(iClass);
		for (ILeafNode iLeafNode : s.getLeafNodes()) {
			if (!iLeafNode.isHidden() && iLeafNode.getText().equals("ports")) {
				return iLeafNode;
			}
		}
		return null;
	}
}
