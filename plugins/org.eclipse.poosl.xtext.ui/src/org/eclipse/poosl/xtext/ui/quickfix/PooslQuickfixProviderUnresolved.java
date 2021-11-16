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
package org.eclipse.poosl.xtext.ui.quickfix;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.poosl.AssignmentExpression;
import org.eclipse.poosl.BinaryOperatorExpression;
import org.eclipse.poosl.DataClass;
import org.eclipse.poosl.DataMethod;
import org.eclipse.poosl.DataMethodCallExpression;
import org.eclipse.poosl.Expression;
import org.eclipse.poosl.ExpressionSequence;
import org.eclipse.poosl.Instance;
import org.eclipse.poosl.InstanceParameter;
import org.eclipse.poosl.InstancePort;
import org.eclipse.poosl.InstantiableClass;
import org.eclipse.poosl.OutputVariable;
import org.eclipse.poosl.PooslPackage.Literals;
import org.eclipse.poosl.ProcessClass;
import org.eclipse.poosl.ProcessMethod;
import org.eclipse.poosl.ProcessMethodCall;
import org.eclipse.poosl.ReturnExpression;
import org.eclipse.poosl.UnaryOperatorExpression;
import org.eclipse.poosl.xtext.custom.PooslCache;
import org.eclipse.poosl.xtext.custom.PooslTypeSystem;
import org.eclipse.poosl.xtext.descriptions.PooslDataMethodDescription;
import org.eclipse.poosl.xtext.helpers.HelperFunctions;
import org.eclipse.poosl.xtext.helpers.PooslReferenceHelper;
import org.eclipse.poosl.xtext.helpers.PooslVariableTypeHelper;
import org.eclipse.poosl.xtext.validation.PooslIssueCodes;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.impl.HiddenLeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.edit.ISemanticModification;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.eclipse.xtext.validation.Issue;

/**
 * The PooslQuickfixProviderUnresolved.
 *
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslQuickfixProviderUnresolved extends PooslQuickfixProviderUnusedElements {

    /** Type inference singleton. */
    protected final PooslTypeSystem pooslTypeSystem = new PooslTypeSystem(null);

    @Fix(PooslIssueCodes.UNDECLARED_DATA_METHOD_BINARY)
    public void undeclaredDataMethodBinary(
            final Issue issue, final IssueResolutionAcceptor acceptor) {
        final String message = issue.getMessage();
        final String missingDeclarationName = message.substring(message.indexOf('\'') + 1,
                message.lastIndexOf('\''));
        acceptor.accept(issue,
                MessageFormat.format("Create method ''{0}''", missingDeclarationName), // label
                null, // description
                null, // icon
                (ISemanticModification) (element, context) -> {
                    Resource resource = element.eResource();
                    BinaryOperatorExpression dataMethodCall = (BinaryOperatorExpression) element;
                    String leftOperand = pooslTypeSystem
                            .getAndCheckExpressionType(dataMethodCall.getLeftOperand());
                    IEObjectDescription dataDescr = PooslCache.get(resource).getDataClassMap()
                            .get(leftOperand);

                    if (dataDescr == null) {
                        showWarning(
                                "Quickfix could not be applied because the type of the left operant could not be determined.");
                        return;
                    }

                    String varType = pooslTypeSystem
                            .getAndCheckExpressionType(dataMethodCall.getRightOperand());
                    if (varType == null) {
                        varType = HelperFunctions.CLASS_NAME_OBJECT;
                    }
                    String returnType = HelperFunctions.CLASS_NAME_OBJECT;
                    // CHECKSTYLE:OFF code gen
                    String method = "\n\t" + dataMethodCall.getName() + "(arg : " + varType //$NON-NLS-1$//$NON-NLS-2$
                            + ") : " + returnType + "\n" //$NON-NLS-1$ //$NON-NLS-2$
                            + "\t\treturn self"; //$NON-NLS-1$
                    // CHECKSTYLE:ON
                    EObject targetType = dataDescr.getEObjectOrProxy();
                    if (targetType.eIsProxy()) {
                        targetType = EcoreUtil.resolve(targetType, element);
                    }
                    INode lastNode = getLastMethodNode((DataClass) targetType);
                    if (lastNode == null || !applyTextChange(context.getXtextDocument(), resource,
                            targetType, lastNode.getTotalEndOffset(), 0, method)) {
                        showWarning(MessageFormat.format(
                                "Could not add datamethod to class \"{0}\". The file that contains the definition of the data class \"{1}\" cannot be opened.",
                                leftOperand, leftOperand));
                    }
                });
    }

    @Fix(PooslIssueCodes.UNDECLARED_DATA_METHOD_BINARY)
    public void undeclaredDataMethodBinary2(final Issue issue, IssueResolutionAcceptor acceptor) {
        acceptor.accept(issue, "Apply conversion method to right argument", // label
                null, // description
                null, // icon
                (ISemanticModification) (element, context) -> {
                    Resource resource = element.eResource();
                    BinaryOperatorExpression dataMethodCall = (BinaryOperatorExpression) element;
                    Expression argument = dataMethodCall.getRightOperand();

                    String leftOperand = pooslTypeSystem
                            .getAndCheckExpressionType(dataMethodCall.getLeftOperand());
                    String rightOperand = pooslTypeSystem.getAndCheckExpressionType(argument);
                    IEObjectDescription method = PooslReferenceHelper.getDataMethod(resource,
                            leftOperand, dataMethodCall.getName().getLiteral(), 1,
                            Literals.DATA_CLASS__DATA_METHODS_BINARY_OPERATOR);

                    if (method != null) {
                        String expClass = PooslDataMethodDescription.getParameterTypeNames(method)
                                .get(0);
                        List<TextChange> changes = getConversionChanges(argument, rightOperand,
                                expClass);
                        if (changes == null) {
                            return;
                        }
                        if (applyTextChanges(context.getXtextDocument(), resource, changes)) {
                            return;
                        }
                    }
                    showWarning(MessageFormat.format(
                            "No method could be found to convert ''{0}'' to the correct type.",
                            rightOperand));
                });
    }

    @Fix(PooslIssueCodes.UNDECLARED_DATA_METHOD_UNARY)
    public void undeclaredDataMethodUnary(
            final Issue issue, final IssueResolutionAcceptor acceptor) {
        final String message = issue.getMessage();
        final String missingDeclarationName = message.substring(message.indexOf('\'') + 1,
                message.lastIndexOf('\''));
        acceptor.accept(issue,
                MessageFormat.format("Create method ''{0}''", missingDeclarationName), // label
                null, // description
                null, // icon
                (ISemanticModification) (element, context) -> {
                    Resource resource = element.eResource();
                    UnaryOperatorExpression dataMethodCall = (UnaryOperatorExpression) element;
                    String targetTypeName = pooslTypeSystem
                            .getAndCheckExpressionType(dataMethodCall.getOperand());
                    IEObjectDescription targetDescr = PooslCache.get(resource).getDataClassMap()
                            .get(targetTypeName);

                    if (targetDescr == null) {
                        showWarning(
                                "Quickfix could not be applied because the type of the left operant could not be determined.");
                        return;
                    }

                    String returnType = HelperFunctions.CLASS_NAME_OBJECT;
                    // CHECKSTYLE:OFF code gen
                    String method = "\n\t" + dataMethodCall.getName().getLiteral() + " : " //$NON-NLS-1$//$NON-NLS-2$
                            + returnType + "\n\t\treturn self"; //$NON-NLS-1$
                    // CHECKSTYLE:ON
                    EObject object = targetDescr.getEObjectOrProxy();
                    if (object.eIsProxy()) {
                        object = EcoreUtil.resolve(object, element);
                    }
                    DataClass targetType = (DataClass) object;
                    INode lastNode = getLastMethodNode(targetType);
                    if (lastNode == null || !applyTextChange(context.getXtextDocument(), resource,
                            targetType, lastNode.getTotalEndOffset(), 0, method)) {
                        showWarning(MessageFormat.format(
                                "Could not add datamethod to class \"{0}\". The file that contains the definition of the data class \"{1}\" cannot be opened.",
                                targetTypeName, targetTypeName));
                    }
                });
    }

    @Fix(PooslIssueCodes.UNDECLARED_DATA_METHOD_NAMED)
    public void undeclaredDataMethodNamed(
            final Issue issue, final IssueResolutionAcceptor acceptor) {
        final String message = issue.getMessage();
        final String missingDeclarationName = message.substring(message.indexOf('\'') + 1,
                message.lastIndexOf('\''));
        acceptor.accept(issue,
                MessageFormat.format("Create method ''{0}''", missingDeclarationName), // label
                null, // description
                null, // icon
                (ISemanticModification) (element, context) -> {
                    Resource resource = element.eResource();
                    DataMethodCallExpression dataMethodCall = (DataMethodCallExpression) element;
                    String targetTypeName = pooslTypeSystem
                            .getAndCheckExpressionType(dataMethodCall.getTarget());
                    IEObjectDescription targetDescr = PooslCache.get(resource).getDataClassMap()
                            .get(targetTypeName);

                    if (targetDescr == null) {
                        showWarning(
                                "Quickfix could not be applied because the type of the left operant could not be determined.");
                        return;
                    }

                    String vars = createArgumentString(dataMethodCall.getArguments());
                    String returnType = HelperFunctions.CLASS_NAME_OBJECT;
                    // CHECKSTYLE:OFF code gen
                    String method = "\n\t" + dataMethodCall.getName() + "(" + vars + ") : " //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
                            + returnType + "\n\t\treturn self"; //$NON-NLS-1$
                    // CHECKSTYLE:ON
                    EObject object = targetDescr.getEObjectOrProxy();
                    if (object.eIsProxy()) {
                        object = EcoreUtil.resolve(object, element);
                    }
                    DataClass targetType = (DataClass) object;
                    INode lastNode = getLastMethodNode(targetType);
                    if (lastNode == null || !applyTextChange(context.getXtextDocument(), resource,
                            targetType, lastNode.getTotalEndOffset(), 0, method)) {
                        showWarning(MessageFormat.format(
                                "Could not add datamethod to class \"{0}\". The file that contains the definition of the data class \"{1}\" cannot be opened.",
                                targetType.getName(), targetType.getName()));
                    }
                });
    }

    @Fix(PooslIssueCodes.UNDECLARED_DATA_METHOD_NAMED)
    public void undeclaredDataMethodNamed2(final Issue issue, IssueResolutionAcceptor acceptor) {
        acceptor.accept(issue, "Apply conversion methods", // label
                null, // description
                null, // icon
                (ISemanticModification) (element, context) -> {
                    Resource resource = element.eResource();
                    DataMethodCallExpression dataMethodCall = (DataMethodCallExpression) element;
                    List<Expression> arguments = dataMethodCall.getArguments();
                    String targetType = pooslTypeSystem
                            .getAndCheckExpressionType(dataMethodCall.getTarget());
                    IEObjectDescription method = PooslReferenceHelper.getDataMethod(resource,
                            targetType, dataMethodCall.getName(), arguments.size(),
                            Literals.DATA_CLASS__DATA_METHODS_NAMED);

                    if (method != null) {
                        List<TextChange> totalChanges = new ArrayList<>();
                        List<String> parameterTypeNames = PooslDataMethodDescription
                                .getParameterTypeNames(method);
                        for (int i = 0; i < parameterTypeNames.size(); i++) {
                            String actualType = pooslTypeSystem
                                    .getAndCheckExpressionType(arguments.get(i));
                            List<TextChange> changes = getConversionChanges(arguments.get(i),
                                    actualType, parameterTypeNames.get(i));
                            if (changes == null) {
                                return;
                            }
                            totalChanges.addAll(changes);
                        }
                        if (!applyTextChanges(context.getXtextDocument(), resource, totalChanges)) {
                            showWarning("Could not apply all conversion methods.");
                        }
                    }
                });
    }

    protected List<TextChange> getConversionChanges(
            Expression element, String fromClass, String toClass) {
        if (fromClass.equals(toClass) || toClass == null) {
            return noConvertionFix(fromClass, toClass);
        }
        Map<String, IEObjectDescription> conversionMethods = PooslCache.get(element.eResource())
                .getDataMethodsNamed(fromClass, 0, toClass);

        if (conversionMethods.isEmpty()) {
            return noConvertionFix(fromClass, toClass);
        }
        String dMethodName = getConversionMethod(toClass, conversionMethods);
        if (dMethodName == null) {
            return noConvertionFix(fromClass, toClass);
        }
        Expression lastExpr = findLastReturnExpression(element);
        return lastExpr != null
            ? createConvertionMethodTextChange(lastExpr, dMethodName)
            : noConvertionFix(fromClass, toClass);
    }

    private Expression findLastReturnExpression(Expression element) {
        Expression lastExpr = element;
        while (lastExpr instanceof ExpressionSequence || lastExpr instanceof ReturnExpression) {
            if (lastExpr instanceof ExpressionSequence) {
                List<Expression> exprs = ((ExpressionSequence) lastExpr).getExpressions();
                lastExpr = (exprs.isEmpty()) ? null : exprs.get(exprs.size() - 1);
            } else if (lastExpr instanceof ReturnExpression) {
                lastExpr = ((ReturnExpression) lastExpr).getExpression();
            }
        }
        return lastExpr;
    }

    private List<TextChange> noConvertionFix(String fromClass, String toClass) {
        showWarning(MessageFormat.format("No method could be found to convert ''{0}'' to ''{1}''.", //$NON-NLS-1$
                fromClass, toClass));
        return null;
    }

    private String getConversionMethod(
            String toClass, Map<String, IEObjectDescription> conversionMethods) {
        String asMethod = null;
        String printStringMethod = null;
        String nonReturnObjectMethod = null;
        for (Entry<String, IEObjectDescription> entry : conversionMethods.entrySet()) {
            if ("printString".equals(entry.getKey())) { //$NON-NLS-1$
                printStringMethod = entry.getKey();
            } else if (("as" + toClass).equals(entry.getKey())) { //$NON-NLS-1$
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
        return dMethodName;
    }

    private List<TextChange> createConvertionMethodTextChange(
            Expression lastExpr, String methodName) {
        ICompositeNode node = NodeModelUtils.getNode(lastExpr);
        List<TextChange> changes = new ArrayList<>();
        if (lastExpr instanceof BinaryOperatorExpression
                || lastExpr instanceof AssignmentExpression) {
            changes.add(new TextChange(lastExpr.eResource(), node.getOffset(), 0, "(")); //$NON-NLS-1$
            changes.add(new TextChange(lastExpr.eResource(), node.getOffset() + node.getLength(), 0,
                    ") " + methodName)); //$NON-NLS-1$
            return changes;
        } else {
            changes.add(new TextChange(lastExpr.eResource(), node.getTotalEndOffset(), 0,
                    " " + methodName)); //$NON-NLS-1$
        }
        return changes;
    }

    private final class UndeclaredVariableUoW extends IUnitOfWork.Void<XtextResource> {

        private final Issue issue;

        private final IssueResolutionAcceptor acceptor;

        private UndeclaredVariableUoW(Issue issue, IssueResolutionAcceptor acceptor) {
            this.issue = issue;
            this.acceptor = acceptor;
        }

        @Override
        public void process(XtextResource xtextResource) throws Exception {
            EObject cause = xtextResource.getResourceSet().getEObject(issue.getUriToProblem(),
                    true);
            final String message = issue.getMessage();
            final String missingDeclarationName = message.substring(message.indexOf('\'') + 1,
                    message.lastIndexOf('\''));
            final String issueUriToProblem = issue.getUriToProblem().toString();
            if (message.startsWith("Variable")) {
                // A reference is made to an instance parameter so only show
                // the quickfix to add instance parameters
                if (issueUriToProblem.contains("instanceParameters")) {
                    acceptCreateInstanceClassParameter(missingDeclarationName);
                } else {
                    // Add a quickfix to add the missing variable as class
                    // variable, if its not the initial method call also add
                    // quickfix as local variable
                    if (!issueUriToProblem.contains("initialMethodCall")) {
                        acceptCreateLocalVariable(missingDeclarationName);
                    }
                    acceptCreateClassVariable(missingDeclarationName);
                }
                // If the missing variable is inside a processClass also add
                // quickfix to add it as a parameter of that class
                if (issueUriToProblem.contains("processClasses")) {
                    acceptCreateClassParameter(missingDeclarationName);
                }
            } else if (message.startsWith("Port")) {
                // If the missing port is referenced in a process or
                // clusterclass
                // but not as an instanceport add this quickfix
                if ((issueUriToProblem.contains("processClasses")
                        || issueUriToProblem.contains("clusterClasses"))
                        && !issueUriToProblem.contains("instancePorts")) {
                    acceptCreatePort(missingDeclarationName);
                }
                // Else add the quickfix to add the port to the referenced
                // clusterclass or processclass (only available in
                // instanceports so
                // in system and cluster).
                else if ((issueUriToProblem.contains("clusterClasses")
                        || issueUriToProblem.contains("system"))
                        && issueUriToProblem.contains("instancePorts")) {
                    if (cause instanceof InstancePort
                            && ((InstancePort) cause).getInstance().getClassDefinition() != null) {
                        acceptCreateInstancePort(missingDeclarationName);
                    }
                }
            } else if (message.startsWith("ProcessMethod")) {
                acceptCreateProcessMethod(missingDeclarationName);
            } else if (message.startsWith("InstantiableClass")) {
                acceptCreateProcessClass(missingDeclarationName);
                // This quickfix cannot be executed as a semantic
                // modification
                // because the end result contains an error (missing process
                // class someClass).
                // The serializer cannot serialize the class if it contains
                // errors.
                acceptCreateClusterClass(missingDeclarationName);
            } else if (message.startsWith("DataClass")) {
                acceptCreateDataClass(missingDeclarationName);
            }
        }

        private void acceptCreateDataClass(final String missingDeclarationName) {
            acceptor.accept(issue, "Create data class '" + missingDeclarationName + "'", // label
                    null, // description
                    null, // icon
                    (ISemanticModification) (element, context) -> {
                        String dataClass = "\n\ndata class " + missingDeclarationName //$NON-NLS-1$
                                + " extends Object" + "\nvariables" + "\nmethods"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        if (!applyTextChange(context.getXtextDocument(), element.eResource(),
                                element, context.getXtextDocument().getLength(), 0, dataClass)) {
                            showWarning("Could not create Data Class '" + missingDeclarationName
                                    + "'.");
                        }
                    });
        }

        private void acceptCreateClusterClass(final String missingDeclarationName) {
            acceptor.accept(issue, "Create cluster class '" + missingDeclarationName + "'", // label
                    null, // description
                    null, // icon
                    (ISemanticModification) (element, context) -> {
                        String clusterClass = "\n\ncluster class " + missingDeclarationName //$NON-NLS-1$
                                + "()\nports" + "\ninstances" //$NON-NLS-1$//$NON-NLS-2$
                                + "\n\tsomeInstance: someClass()" + "\nchannels\n"; //$NON-NLS-1$ //$NON-NLS-2$
                        if (!applyTextChange(context.getXtextDocument(), element.eResource(),
                                element, context.getXtextDocument().getLength(), 0, clusterClass)) {
                            showWarning("Could not create Cluster Class '" + missingDeclarationName
                                    + "'.");
                        }
                    });
        }

        private void acceptCreateProcessClass(final String missingDeclarationName) {
            acceptor.accept(issue, "Create process class '" + missingDeclarationName + "'", // label
                    null, // description
                    null, // icon
                    (ISemanticModification) (element, context) -> {
                        String processClass = "\n\nprocess class " + missingDeclarationName //$NON-NLS-1$
                                + "\nports" //$NON-NLS-1$
                                + "\nmessages\nvariables\ninit\n\tsomeMethod()()\nmethods" //$NON-NLS-1$
                                + "\n\tsomeMethod()()\n\t\tskip"; //$NON-NLS-1$
                        if (!applyTextChange(context.getXtextDocument(), element.eResource(),
                                element, context.getXtextDocument().getLength(), 0, processClass)) {
                            showWarning("Could not create Process Class '" + missingDeclarationName
                                    + "'.");
                        }
                    });
        }

        private void acceptCreateProcessMethod(final String missingDeclarationName) {
            acceptor.accept(issue, "Create method '" + missingDeclarationName + "'", // label
                    null, // description
                    null, // icon
                    (ISemanticModification) (element, context) -> {
                        ProcessClass pClass = HelperFunctions.getContainingProcessClass(element);
                        if (pClass != null) {
                            // CHECKSTYLE:OFF code gen
                            String input = ""; //$NON-NLS-1$
                            String output = ""; //$NON-NLS-1$

                            if (element instanceof ProcessMethodCall) {
                                ProcessMethodCall processMethodCall = (ProcessMethodCall) element;
                                input = createArgumentString(processMethodCall.getInputArguments());

                                StringBuilder outputBuilder = new StringBuilder();

                                for (int j = 0; j
                                        < processMethodCall.getOutputVariables().size(); j++) {
                                    if (j != 0) {
                                        outputBuilder.append(" , "); //$NON-NLS-1$
                                    }
                                    OutputVariable outputVariable = processMethodCall
                                            .getOutputVariables().get(j);
                                    String varType = PooslVariableTypeHelper.getVariableType(
                                            outputVariable, outputVariable.getVariable());
                                    if (varType == null) {
                                        varType = HelperFunctions.CLASS_NAME_OBJECT;
                                    }
                                    outputBuilder.append("outputVar" + j + " : " + varType); //$NON-NLS-1$ //$NON-NLS-2$
                                }
                                output = outputBuilder.toString();
                            }

                            String method = "\n\t" + missingDeclarationName + "(" + input //$NON-NLS-1$//$NON-NLS-2$
                                    + ")(" + output + ") | | \n\t\tskip"; //$NON-NLS-1$ //$NON-NLS-2$
                            INode lastNode = getLastMethodNode(pClass);
                            // CHECKSTYLE:ON
                            if (lastNode == null || !applyTextChange(context.getXtextDocument(),
                                    element.eResource(), pClass, lastNode.getTotalEndOffset(), 0,
                                    method)) {
                                showWarning("Could not create process method '"
                                        + missingDeclarationName + "'.");
                            }
                        }
                    });
        }

        private void acceptCreateInstancePort(final String missingDeclarationName) {
            acceptor.accept(issue, "Create port '" + missingDeclarationName + "'.", // label
                    null, // description
                    null, // icon
                    (ISemanticModification) (element, context) -> {
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
                                    lastNode.getTotalEndOffset(), 0,
                                    "\n\t" + missingDeclarationName); //$NON-NLS-1$
                        } else {
                            showWarning("Could not create the new port '" + missingDeclarationName
                                    + "'.");
                        }
                    });
        }

        private void acceptCreatePort(final String missingDeclarationName) {
            acceptor.accept(issue, "Create port '" + missingDeclarationName + "'", // label
                    null, // description
                    null, // icon
                    (ISemanticModification) (element, context) -> {
                        EObject container = element.eContainer();
                        while (!(container instanceof InstantiableClass) && container != null) {
                            container = container.eContainer();
                        }

                        if (container instanceof InstantiableClass) {
                            InstantiableClass iClass = (InstantiableClass) container;
                            INode lastNode = getLastPortNode(iClass);
                            if (lastNode != null) {
                                applyTextChange(context.getXtextDocument(), element.eResource(),
                                        iClass, lastNode.getTotalEndOffset(), 0,
                                        "\n\t" + missingDeclarationName); //$NON-NLS-1$
                                return;
                            }
                        }
                        showWarning(
                                "Could not create the new port '" + missingDeclarationName + "'.");
                    });
        }

        private void acceptCreateClassParameter(final String missingDeclarationName) {
            acceptor.accept(issue, "Create class parameter '" + missingDeclarationName + "'", // label
                    null, // description
                    null, // icon
                    (ISemanticModification) (element, context) -> {

                        ProcessClass pClass = HelperFunctions.getContainingProcessClass(element);
                        if (pClass != null) {
                            StringBuilder decl = new StringBuilder(missingDeclarationName + " : " //$NON-NLS-1$
                                    + HelperFunctions.CLASS_NAME_OBJECT);
                            INode lastNode = getLastInstanceParamNode(pClass, decl);
                            if (lastNode != null && applyTextChange(context.getXtextDocument(),
                                    element.eResource(), pClass, lastNode.getTotalEndOffset(), 0,
                                    decl.toString())) {
                                return;
                            }
                        }
                        showWarning("Could not create class parameter '" + missingDeclarationName
                                + "'.");
                    });
        }

        private void acceptCreateClassVariable(final String missingDeclarationName) {
            acceptor.accept(issue, "Create class variable '" + missingDeclarationName + "'", // label
                    null, // description
                    null, // icon
                    (ISemanticModification) (element, context) -> {
                        EObject container = element.eContainer();
                        while (!(container instanceof ProcessClass
                                || container instanceof DataClass) && container != null) {
                            container = container.eContainer();
                        }

                        INode lastNode = null;
                        String decl = "\n\t" + missingDeclarationName + " : " //$NON-NLS-1$//$NON-NLS-2$
                                + HelperFunctions.CLASS_NAME_OBJECT;
                        if (container instanceof ProcessClass) {
                            lastNode = getLastVariableNode((ProcessClass) container);
                        } else if (container instanceof DataClass) {
                            lastNode = getLastVariableNode((DataClass) container);
                        }

                        if (lastNode != null
                                && applyTextChange(context.getXtextDocument(), element.eResource(),
                                        container, lastNode.getTotalEndOffset(), 0, decl)) {
                            return;
                        }
                        showWarning("Could not create class variable '" + missingDeclarationName
                                + "'.");
                    });
        }

        private void acceptCreateLocalVariable(final String missingDeclarationName) {
            acceptor.accept(issue, "Create local variable '" + missingDeclarationName + "'", // label
                    null, // description
                    null, // icon
                    (ISemanticModification) (element, context) -> {
                        EObject container = element.eContainer();
                        while (!(container instanceof ProcessMethod
                                || container instanceof DataMethod) && container != null) {
                            container = container.eContainer();
                        }

                        StringBuilder decl = new StringBuilder(" " + missingDeclarationName //$NON-NLS-1$
                                + " : " + HelperFunctions.CLASS_NAME_OBJECT); //$NON-NLS-1$
                        INode lastNode = null;
                        if (container instanceof ProcessMethod) {
                            lastNode = getLastLocalVarNode(container, decl,
                                    Literals.PROCESS_METHOD__LOCAL_VARIABLES,
                                    Literals.PROCESS_METHOD__BODY);
                        } else if (container instanceof DataMethod) {
                            lastNode = getLastLocalVarNode(container, decl,
                                    Literals.DATA_METHOD__LOCAL_VARIABLES,
                                    Literals.DATA_METHOD__BODY);
                        }

                        if (lastNode != null && applyTextChange(context.getXtextDocument(),
                                element.eResource(), container, lastNode.getTotalEndOffset(), 0,
                                decl.toString())) {
                            return;
                        }
                        showWarning(
                                "Failed to add local variable '" + missingDeclarationName + "'.");
                    });
        }

        private void acceptCreateInstanceClassParameter(final String missingDeclarationName) {
            acceptor.accept(issue,
                    "Create instance class parameter '" + missingDeclarationName + "'", // label
                    null, // description
                    null, // icon
                    (ISemanticModification) (element, context) -> {
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

                                StringBuilder dec = new StringBuilder(
                                        missingDeclarationName + " : " + varType); //$NON-NLS-1$
                                INode lastNode = getLastInstanceParamNode(iClass, dec);

                                if (lastNode != null && applyTextChange(context.getXtextDocument(),
                                        element.eResource(), iClass, lastNode.getTotalEndOffset(),
                                        0, dec.toString())) {
                                    return;
                                }
                            }
                        }
                        showWarning("Failed to add instance parameter '" + missingDeclarationName
                                + "'.");
                    });
        }
    }

    @Fix(org.eclipse.xtext.diagnostics.Diagnostic.LINKING_DIAGNOSTIC)
    public void undeclaredVariable(final Issue issue, final IssueResolutionAcceptor acceptor) {
        getModificationContextFactory()//
                .createModificationContext(issue)//
                .getXtextDocument()//
                .readOnly(new UndeclaredVariableUoW(issue, acceptor));
    }

    private INode getLastLocalVarNode(
            EObject object, StringBuilder builder, EReference refLocal, EReference refBody) {
        List<INode> nodes = NodeModelUtils.findNodesForFeature(object, refLocal);
        if (!nodes.isEmpty()) {
            builder.insert(0, ","); //$NON-NLS-1$
            return nodes.get(nodes.size() - 1);
        }

        INode lastNode = null;
        INode bodyNode = null;
        List<INode> bodyNodes = NodeModelUtils.findNodesForFeature(object, refBody);
        if (!bodyNodes.isEmpty()) {
            bodyNode = bodyNodes.get(0);
        }

        for (ILeafNode iLeafNode : NodeModelUtils.getNode(object).getLeafNodes()) {
            if (bodyNode != null && iLeafNode.getOffset() >= bodyNode.getOffset()) {
                break;
            }

            if (!iLeafNode.isHidden()) {
                if (iLeafNode.getText().equals("|")) {
                    return iLeafNode;
                }
                lastNode = iLeafNode;
            }
        }
        builder.insert(0, " |").append(" |"); //$NON-NLS-1$ //$NON-NLS-2$
        return lastNode;
    }

    private INode getLastVariableNode(DataClass dClass) {
        List<INode> nodes = NodeModelUtils.findNodesForFeature(dClass,
                Literals.DATA_CLASS__INSTANCE_VARIABLES);
        return getLastVariableNode(dClass, nodes);
    }

    private INode getLastVariableNode(ProcessClass pClass) {
        List<INode> nodes = NodeModelUtils.findNodesForFeature(pClass,
                Literals.PROCESS_CLASS__INSTANCE_VARIABLES);
        return getLastVariableNode(pClass, nodes);
    }

    private INode getLastVariableNode(EObject object, List<INode> nodes) {
        if (!nodes.isEmpty()) {
            return nodes.get(nodes.size() - 1);
        }

        for (ILeafNode iLeafNode : NodeModelUtils.findActualNodeFor(object).getLeafNodes()) {
            if (!iLeafNode.isHidden() && iLeafNode.getText().equals("variables")) {
                return iLeafNode;
            }

        }
        return null;
    }

    private INode getLastInstanceParamNode(InstantiableClass iClass, StringBuilder declaration) {

        List<INode> nodes = NodeModelUtils.findNodesForFeature(iClass,
                Literals.INSTANTIABLE_CLASS__PARAMETERS);
        if (!nodes.isEmpty()) {
            declaration.insert(0, " , "); //$NON-NLS-1$
            return nodes.get(nodes.size() - 1);
        } else {
            List<INode> nameNodes = NodeModelUtils.findNodesForFeature(iClass,
                    Literals.INSTANTIABLE_CLASS__NAME);
            if (nameNodes.isEmpty()) {
                return null;
            }
            INode nameNode = nameNodes.get(0);
            INode lastNode = nameNode.getNextSibling();
            while ((lastNode instanceof HiddenLeafNode) && lastNode.hasNextSibling()) {
                lastNode = lastNode.getNextSibling();
            }
            if (lastNode.getText().equals("(")) { //$NON-NLS-1$
                return lastNode;
            } else {
                declaration.insert(0, "("); //$NON-NLS-1$
                declaration.append(")"); //$NON-NLS-1$
                return nameNode;
            }
        }
    }

    private INode getLastMethodNode(DataClass targetType) {
        INode lastDataMethod = null;
        List<INode> binary = NodeModelUtils.findNodesForFeature(targetType,
                Literals.DATA_CLASS__DATA_METHODS_BINARY_OPERATOR);
        lastDataMethod = !binary.isEmpty() ? binary.get(binary.size() - 1) : lastDataMethod;

        List<INode> named = NodeModelUtils.findNodesForFeature(targetType,
                Literals.DATA_CLASS__DATA_METHODS_NAMED);
        lastDataMethod = (!named.isEmpty() && (lastDataMethod == null
                || named.get(named.size() - 1).getOffset() > lastDataMethod.getOffset()))
                    ? named.get(named.size() - 1) : lastDataMethod;

        List<INode> unary = NodeModelUtils.findNodesForFeature(targetType,
                Literals.DATA_CLASS__DATA_METHODS_UNARY_OPERATOR);
        lastDataMethod = (!unary.isEmpty() && (lastDataMethod == null
                || unary.get(unary.size() - 1).getOffset() > lastDataMethod.getOffset()))
                    ? unary.get(unary.size() - 1) : lastDataMethod;

        if (lastDataMethod == null) {
            ICompositeNode s = NodeModelUtils.findActualNodeFor(targetType);
            for (ILeafNode iLeafNode : s.getLeafNodes()) {
                if (!iLeafNode.isHidden() && iLeafNode.getText().equals("methods")) { //$NON-NLS-1$
                    return iLeafNode;
                }
            }

        }
        return lastDataMethod;
    }

    private INode getLastMethodNode(ProcessClass pClass) {
        List<INode> methods = NodeModelUtils.findNodesForFeature(pClass,
                Literals.PROCESS_CLASS__METHODS);
        INode lastDataMethod = (!methods.isEmpty()) ? methods.get(methods.size() - 1) : null;
        if (lastDataMethod == null) {
            ICompositeNode s = NodeModelUtils.findActualNodeFor(pClass);
            for (ILeafNode iLeafNode : s.getLeafNodes()) {
                if (!iLeafNode.isHidden() && iLeafNode.getText().equals("methods")) { //$NON-NLS-1$
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
                vars.append(" , "); //$NON-NLS-1$
            }
            String varType = pooslTypeSystem.getAndCheckExpressionType(arguments.get(i));
            if (varType == null) {
                varType = HelperFunctions.CLASS_NAME_OBJECT;
            }
            vars.append("arg" + i + " : " + varType); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return vars.toString();
    }

    private INode getLastPortNode(InstantiableClass iClass) {
        List<INode> nodes = NodeModelUtils.findNodesForFeature(iClass,
                Literals.INSTANTIABLE_CLASS__PORTS);
        if (!nodes.isEmpty()) {
            return nodes.get(nodes.size() - 1);
        }

        ICompositeNode s = NodeModelUtils.findActualNodeFor(iClass);
        for (ILeafNode iLeafNode : s.getLeafNodes()) {
            if (!iLeafNode.isHidden() && iLeafNode.getText().equals("ports")) { //$NON-NLS-1$
                return iLeafNode;
            }
        }
        return null;
    }
}
