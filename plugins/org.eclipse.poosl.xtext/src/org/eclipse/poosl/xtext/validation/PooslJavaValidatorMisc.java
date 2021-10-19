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

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.poosl.AssignmentExpression;
import org.eclipse.poosl.BinaryOperatorExpression;
import org.eclipse.poosl.Channel;
import org.eclipse.poosl.ClusterClass;
import org.eclipse.poosl.DataMethod;
import org.eclipse.poosl.DataMethodCallExpression;
import org.eclipse.poosl.DelayStatement;
import org.eclipse.poosl.Expression;
import org.eclipse.poosl.ExpressionSequence;
import org.eclipse.poosl.ExpressionStatement;
import org.eclipse.poosl.GuardedStatement;
import org.eclipse.poosl.IfExpression;
import org.eclipse.poosl.IfStatement;
import org.eclipse.poosl.Import;
import org.eclipse.poosl.Instance;
import org.eclipse.poosl.InstanceParameter;
import org.eclipse.poosl.InstancePort;
import org.eclipse.poosl.NewExpression;
import org.eclipse.poosl.OperatorBinary;
import org.eclipse.poosl.Poosl;
import org.eclipse.poosl.PooslPackage.Literals;
import org.eclipse.poosl.ProcessMethodCall;
import org.eclipse.poosl.ReceiveStatement;
import org.eclipse.poosl.ReturnExpression;
import org.eclipse.poosl.SendStatement;
import org.eclipse.poosl.Statement;
import org.eclipse.poosl.SwitchExpression;
import org.eclipse.poosl.SwitchExpressionCase;
import org.eclipse.poosl.UnaryOperatorExpression;
import org.eclipse.poosl.VariableExpression;
import org.eclipse.poosl.WhileExpression;
import org.eclipse.poosl.WhileStatement;
import org.eclipse.poosl.xtext.custom.FormattingHelper;
import org.eclipse.poosl.xtext.custom.PooslCache;
import org.eclipse.poosl.xtext.custom.PooslMessageType;
import org.eclipse.poosl.xtext.custom.TypingHelper;
import org.eclipse.poosl.xtext.descriptions.PooslDataMethodDescription;
import org.eclipse.poosl.xtext.descriptions.PooslMessageSignatureDescription;
import org.eclipse.poosl.xtext.helpers.ConnectedPortsHelper;
import org.eclipse.poosl.xtext.helpers.HelperFunctions;
import org.eclipse.poosl.xtext.helpers.PooslDataMethodParser;
import org.eclipse.poosl.xtext.importing.ImportingHelper;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckType;

import com.google.common.collect.Iterables;

/**
 * The PooslJavaValidatorMisc.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslJavaValidatorMisc extends PooslJavaValidatorUnusedElements {
    private static final String INVALID_IMPORTLIB_LOCATION_INCL_INFO = " The Poosl include paths can be changed using Project -> Properties -> Poosl.";

    private static final String INVALID_IMPORTLIB_UNRESOLVED_ORIGINAL_AND_LOCAL = "Imported file \"{0}\" contains an importlib \"{1}\" that can not be resolved using the Poosl include "
            + "paths of the current project." + INVALID_IMPORTLIB_LOCATION_INCL_INFO;

    private static final String INVALID_IMPORTLIB_UNRESOLVED_LOCAL = "Imported file \"{0}\" contains an importlib \"{1}\" that can not be resolved using the Poosl include paths of the "
            + "current project. However, it was originally resolved to \"{2}\"."
            + INVALID_IMPORTLIB_LOCATION_INCL_INFO;

    private static final String INVALID_IMPORTLIB_RESOLVED_DIFF = "Imported file \"{0}\" contains an importlib \"{1}\" that resolves to \"{3}\" using the Poosl include paths of the "
            + "current project. However, it was originally resolved to \"{2}\"."
            + INVALID_IMPORTLIB_LOCATION_INCL_INFO;

    private static final String NO_RECEIVING_MESSAGE = "On {0}, process port {1} can send message ''{2}'' with {3} parameter(s),"
            + " but no process port can receive it";

    private static final String NO_SENDING_MESSAGE = "On {0}, process port {1} can receive message ''{2}'' with {3} parameter(s),"
            + " but no process port can send it";

    private static final String MISMATCH_SENDING = "On {0}, the parameters of message ''{1}!{2}{3}'' "
            + "are not a subtype of the parameters of the following corresponding messages:{4}";

    private static final String MISMATCH_RECEIVE = "On {0}, the parameters of message ''{1}?{2}{3}'' "
            + "are not a supertype of the parameters of the following corresponding messages:{4}";

    private static final String ASSIGNMENT_SUGGESTION = "Perhaps you mean assignment ':=' instead of equality '='";

    private static final String CHANNEL_CONNECTS_ONLY_ONE_INSTANCE = "Unused channel (all connected ports belong to the same instance, so communication is impossible)";

    private static final String INVALID_IMPORT = "Invalid import URI: {0} (some example URI formats: \"../folder/file.poosl\", \"file:///C:/folder/file.poosl\") URI can not point to a linked resource (linked resources are shown in the Project explorer with a shortcut icon)";

    private static final String INVALID_IMPORTLIB = "Invalid importlib URI: {0} (example URI format: \"file.poosl\") URI was not found in any of the Poosl include paths (Project -> Properties -> Poosl) URI can not point outside the Include Path or to a linked resource (linked resources are shown in the Project explorer with a shortcut icon)";

    private static final String RETURN_NESTED_IN_RETURN = "\"return\" is nested inside another \"return\"";

    private static final String RETURN_NOT_AT_END = "\"return\" is not at the end of the method body";

    private static final String RETURN_MISSING = "Missing \"return\" at the end of the method body";

    private static final String INIT_CALL_MISSING = "Data class {0} has an @Init method that needs to be called immediately after new({0})";

    @Check(CheckType.FAST)
    public void checkImports(Poosl poosl) {
        PERF.benchmark("checkImports", () -> { //$NON-NLS-1$
            for (Import imp : poosl.getImports()) {
                String importURI = imp.getImportURI();
                if (importURI != null && !ImportingHelper.isValidImport(imp)) {
                    // If import is not resolved try to resolve as importlib, if
                    // resolvable provide different code for quickfix
                    if (!ImportingHelper.isValidImportLib(imp)) {
                        error(MessageFormat.format(INVALID_IMPORT, importURI), imp,
                                Literals.IMPORT__IMPORT_URI);
                    } else {
                        error(MessageFormat.format(INVALID_IMPORT, importURI), imp, null,
                                PooslIssueCodes.INVALID_IMPORT_VALID_IMPORTLIB, importURI);
                    }
                } else {
                    checkImportLibLocations(imp);
                }
            }

            for (Import imp : poosl.getImportLibs()) {
                String importURI = imp.getImportURI();
                if (importURI != null && !ImportingHelper.isValidImportLib(imp)) {
                    if (!ImportingHelper.isValidImport(imp)) {
                        error(MessageFormat.format(INVALID_IMPORTLIB, importURI), imp,
                                Literals.IMPORT__IMPORT_URI);
                    } else {
                        error(MessageFormat.format(INVALID_IMPORTLIB, importURI), imp, null,
                                PooslIssueCodes.INVALID_IMPORTLIB_VALID_IMPORT, importURI);
                    }
                } else {
                    checkImportLibLocations(imp);
                }
            }
        });
    }

    /**
     * Checks if importlib locations in the imports are resolved to the same
     * file location as from the current resource
     * location.
     * 
     * @param imp
     */
    private void checkImportLibLocations(Import imp) {
        Resource resource = imp.eResource();
        Map<Pair<String, String>, URI> importlibPairs = PooslCache.get(resource)
                .getImportLibsPerImport().get(imp);
        if (importlibPairs == null) {
            return;
        }
        for (Entry<Pair<String, String>, URI> importLibs : importlibPairs.entrySet()) {
            String originalResolved = importLibs.getKey().getFirst();
            String originalRaw = importLibs.getKey().getSecond();
            URI sourceFileOfImportLib = importLibs.getValue();

            URI resolved = ImportingHelper.resolveImportLibUri(resource,
                    URI.createURI(originalRaw));
            if (resolved == null) {
                // Error when importlib can not be resolved from current
                // file (Rotalumis WILL not find this file)
                String originalFile = sourceFileOfImportLib.trimFragment().toString();
                error(originalResolved.isEmpty()
                    ? MessageFormat.format(INVALID_IMPORTLIB_UNRESOLVED_ORIGINAL_AND_LOCAL,
                            originalFile, originalRaw)
                    : MessageFormat.format(INVALID_IMPORTLIB_UNRESOLVED_LOCAL, originalFile,
                            originalRaw, originalResolved),
                        imp, Literals.IMPORT__IMPORT_URI);
            } else if (!originalResolved.isEmpty()) {
                // importlib CAN be resolved from current file
                // If the originalResolved is null, an error is already
                // shown in the original file and there is no need to
                // propagate this to the current file.

                // Warning when importlib resolves to different location
                // (Rotalumis MAY find duplicate classes or MAY not find
                // needed classes)
                String localResolved = resolved.toString();
                if (!localResolved.equals(originalResolved)) {
                    String originalFile = sourceFileOfImportLib.trimFragment().toString();
                    warning(MessageFormat.format(INVALID_IMPORTLIB_RESOLVED_DIFF, originalFile,
                            originalRaw, originalResolved, localResolved), imp,
                            Literals.IMPORT__IMPORT_URI, null, WarningType.IMPORT);
                }
            }

        }

    }

    @Check(CheckType.FAST)
    public void checkChannelConnectsMultipleInstances(Channel channel) {
        if (channel.getExternalPort() == null && channel.getInstancePorts().size() > 1) {
            Set<Instance> instances = new HashSet<>();
            for (InstancePort iPort : channel.getInstancePorts()) {
                instances.add(iPort.getInstance());
            }

            if (instances.size() == 1) {
                warning(CHANNEL_CONNECTS_ONLY_ONE_INSTANCE, channel, null,
                        PooslIssueCodes.UNCONNECTED_CHANNEL, WarningType.UNCONNECTED);
            }
        }
    }

    @Check(CheckType.FAST)
    public void warnLocationOfReturn(DataMethod dMethod) {
        warnLocationOfReturnRecursive(dMethod.getBody(), true, false);
    }

    //CHECKSTYLE.OFF: CyclomaticComplexity - final dispatch
    private void warnLocationOfReturnRecursive(
            Expression expr, boolean isLast, boolean returnHasAlreadyOccurred) {
        boolean done = false;
        if (expr instanceof ReturnExpression) {
            done = doWarnLocationOfReturnRecursive((ReturnExpression) expr, isLast,
                    returnHasAlreadyOccurred);
        } else if (expr instanceof ExpressionSequence) {
            done = doWarnLocationOfReturnRecursive((ExpressionSequence) expr, isLast,
                    returnHasAlreadyOccurred);
        } else if (expr instanceof IfExpression) {
            done = doWarnLocationOfReturnRecursive((IfExpression) expr, isLast,
                    returnHasAlreadyOccurred);

        } else if (expr instanceof SwitchExpression) {
            done = doWarnLocationOfReturnRecursive((SwitchExpression) expr, isLast,
                    returnHasAlreadyOccurred);
        } else if (expr instanceof WhileExpression) {
            warnLocationOfReturnRecursive(((WhileExpression) expr).getCondition(), false,
                    returnHasAlreadyOccurred);
            warnLocationOfReturnRecursive(((WhileExpression) expr).getBody(), false,
                    returnHasAlreadyOccurred);
            done = !isLast;
        } else if (expr instanceof AssignmentExpression) {
            warnLocationOfReturnRecursive(((AssignmentExpression) expr).getExpression(), false,
                    returnHasAlreadyOccurred);
            done = !isLast;
        } else if (expr instanceof DataMethodCallExpression) {
            done = doWarnLocationOfReturnRecursive((DataMethodCallExpression) expr, isLast,
                    returnHasAlreadyOccurred);
        } else if (expr instanceof BinaryOperatorExpression) {
            warnLocationOfReturnRecursive(((BinaryOperatorExpression) expr).getLeftOperand(), false,
                    returnHasAlreadyOccurred);
            warnLocationOfReturnRecursive(((BinaryOperatorExpression) expr).getRightOperand(),
                    false, returnHasAlreadyOccurred);
            done = !isLast;
        } else if (expr instanceof UnaryOperatorExpression) {
            warnLocationOfReturnRecursive(((UnaryOperatorExpression) expr).getOperand(), false,
                    returnHasAlreadyOccurred);
            done = !isLast;
        } else {
            done = !isLast;
        }

        if (!done && expr != null) {
            warning(RETURN_MISSING, expr, null, PooslIssueCodes.LOCATION_OF_RETURN_MISSING,
                    WarningType.RETURN);
        }
    }
    //CHECKSTYLE.ON

    private boolean doWarnLocationOfReturnRecursive(
            DataMethodCallExpression expr, boolean isLast, boolean returnHasAlreadyOccurred) {
        warnLocationOfReturnRecursive(expr.getTarget(), false, returnHasAlreadyOccurred);
        for (Expression ex : expr.getArguments()) {
            warnLocationOfReturnRecursive(ex, false, returnHasAlreadyOccurred);
        }

        return !isLast;
    }

    private boolean doWarnLocationOfReturnRecursive(
            SwitchExpression expr, boolean isLast, boolean returnHasAlreadyOccurred) {
        warnLocationOfReturnRecursive(expr.getExpression(), false, returnHasAlreadyOccurred);
        for (SwitchExpressionCase switchCase : expr.getCases()) {
            warnLocationOfReturnRecursive(switchCase.getValue(), false, returnHasAlreadyOccurred);
            warnLocationOfReturnRecursive(switchCase.getBody(), isLast, returnHasAlreadyOccurred);
        }

        if (expr.getDefaultBody() != null) {
            warnLocationOfReturnRecursive(expr.getDefaultBody(), isLast, returnHasAlreadyOccurred);
            return true;
        }
        return !isLast;
    }

    private boolean doWarnLocationOfReturnRecursive(
            IfExpression expr, boolean isLast, boolean returnHasAlreadyOccurred) {
        warnLocationOfReturnRecursive(expr.getCondition(), false, returnHasAlreadyOccurred);
        warnLocationOfReturnRecursive(expr.getThenClause(), isLast, returnHasAlreadyOccurred);

        if (expr.getElseClause() != null) {
            warnLocationOfReturnRecursive(expr.getElseClause(), isLast, returnHasAlreadyOccurred);
            return true;
        }
        return !isLast;
    }

    private boolean doWarnLocationOfReturnRecursive(
            ExpressionSequence expr, boolean isLast, boolean returnHasAlreadyOccurred) {
        List<Expression> sequence = expr.getExpressions();
        if (!sequence.isEmpty()) {
            Expression lastInSequence = sequence.get(sequence.size() - 1);
            for (Expression ex : sequence) {
                warnLocationOfReturnRecursive(ex, isLast && (ex == lastInSequence),
                        returnHasAlreadyOccurred);
            }
        }
        return true;
    }

    private boolean doWarnLocationOfReturnRecursive(
            ReturnExpression expr, boolean isLast, boolean returnHasAlreadyOccurred) {
        warnLocationOfReturnRecursive(expr.getExpression(), false, true);
        if (returnHasAlreadyOccurred) {
            warning(RETURN_NESTED_IN_RETURN, expr, null,
                    PooslIssueCodes.LOCATION_OF_RETURN_SUPERFLUOUS, WarningType.RETURN);
        } else if (!isLast) {
            warning(RETURN_NOT_AT_END, expr, null, PooslIssueCodes.LOCATION_OF_RETURN_SUPERFLUOUS,
                    WarningType.RETURN);
        }

        return true;
    }

    @Check(CheckType.FAST)
    public void warnEqualityInsteadOfAssignmentInStatement(Statement statement) {
        if (statement instanceof DelayStatement) {
            warnEqualityInsteadOfAssignment(((DelayStatement) statement).getExpression(), false);
        } else if (statement instanceof ExpressionStatement) {
            warnEqualityInsteadOfAssignment(((ExpressionStatement) statement).getExpression(),
                    true);
        } else if (statement instanceof GuardedStatement) {
            warnEqualityInsteadOfAssignment(((GuardedStatement) statement).getGuard(), false);
        } else if (statement instanceof IfStatement) {
            warnEqualityInsteadOfAssignment(((IfStatement) statement).getCondition(), false);
        } else if (statement instanceof ProcessMethodCall) {
            for (Expression expr : ((ProcessMethodCall) statement).getInputArguments()) {
                warnEqualityInsteadOfAssignment(expr, false);
            }
        } else if (statement instanceof ReceiveStatement) {
            warnEqualityInsteadOfAssignmentInReceive(statement);
        } else if (statement instanceof SendStatement) {
            warnEqualityInsteadOfAssignmentInSend(statement);
        } else if (statement instanceof WhileStatement) {
            warnEqualityInsteadOfAssignment(((WhileStatement) statement).getCondition(), false);
        }
    }

    private void warnEqualityInsteadOfAssignmentInSend(Statement statement) {
        for (Expression expr : ((SendStatement) statement).getArguments()) {
            warnEqualityInsteadOfAssignment(expr, false);
        }
        warnEqualityInsteadOfAssignment(
                ((SendStatement) statement).getPostCommunicationExpression(), true);
    }

    private void warnEqualityInsteadOfAssignmentInReceive(Statement statement) {
        warnEqualityInsteadOfAssignment(((ReceiveStatement) statement).getReceptionCondition(),
                false);
        warnEqualityInsteadOfAssignment(
                ((ReceiveStatement) statement).getPostCommunicationExpression(), true);
    }

    @Check(CheckType.FAST)
    public void warnEqualityInsteadOfAssignmentInDataMethod(DataMethod method) {
        warnEqualityInsteadOfAssignment(method.getBody(), false);
    }

    @Check(CheckType.FAST)
    public void warnEqualityInsteadOfAssignmentInInstanceParameter(
            InstanceParameter instanceParameter) {
        warnEqualityInsteadOfAssignment(instanceParameter.getExpression(), false);
    }

    private void warnEqualityInsteadOfAssignment(Expression expr, boolean includeLastExpression) {
        if (expr instanceof BinaryOperatorExpression) {
            warnEqualityInsteadOfAssignmentForBiOperator((BinaryOperatorExpression) expr,
                    includeLastExpression);
        } else if (expr instanceof ReturnExpression) {
            warnEqualityInsteadOfAssignment(((ReturnExpression) expr).getExpression(),
                    includeLastExpression);
        } else if (expr instanceof ExpressionSequence) {
            warnEqualityInsteadOfAssignmentForExpression((ExpressionSequence) expr,
                    includeLastExpression);
        } else if (expr instanceof IfExpression) {
            warnEqualityInsteadOfAssignment(((IfExpression) expr).getCondition(), false);
            warnEqualityInsteadOfAssignment(((IfExpression) expr).getThenClause(),
                    includeLastExpression);
            warnEqualityInsteadOfAssignment(((IfExpression) expr).getElseClause(),
                    includeLastExpression);
        } else if (expr instanceof WhileExpression) {
            warnEqualityInsteadOfAssignment(((WhileExpression) expr).getCondition(), false);
            warnEqualityInsteadOfAssignment(((WhileExpression) expr).getBody(), true);
        } else if (expr instanceof AssignmentExpression) {
            warnEqualityInsteadOfAssignment(((AssignmentExpression) expr).getExpression(), false);
        } else if (expr instanceof DataMethodCallExpression) {
            warnEqualityInsteadOfAssignment(((DataMethodCallExpression) expr).getTarget(), false);
            for (Expression ex : ((DataMethodCallExpression) expr).getArguments()) {
                warnEqualityInsteadOfAssignment(ex, false);
            }
        } else if (expr instanceof UnaryOperatorExpression) {
            warnEqualityInsteadOfAssignment(((UnaryOperatorExpression) expr).getOperand(), false);
        }
    }

    private void warnEqualityInsteadOfAssignmentForExpression(
            ExpressionSequence expr, boolean includeLastExpression) {
        List<Expression> sequence = expr.getExpressions();
        if (!sequence.isEmpty()) {
            Expression lastInSequence = sequence.get(sequence.size() - 1);
            for (Expression ex : sequence) {
                warnEqualityInsteadOfAssignment(ex,
                        includeLastExpression || (ex != lastInSequence));
            }
        }
    }

    private void warnEqualityInsteadOfAssignmentForBiOperator(
            BinaryOperatorExpression expr, boolean includeLastExpression) {
        if (includeLastExpression && expr.getName() == OperatorBinary.EQUAL
                && expr.getLeftOperand() instanceof VariableExpression) {
            warning(ASSIGNMENT_SUGGESTION, expr, Literals.BINARY_OPERATOR_EXPRESSION__NAME,
                    PooslIssueCodes.EQUALITY_INSTEAD_OF_ASSIGNMENT, WarningType.ASSIGNMENT);
        } else {
            warnEqualityInsteadOfAssignment(expr.getLeftOperand(), false);
            warnEqualityInsteadOfAssignment(expr.getRightOperand(), false);
        }
    }

    // --- Instance -------

    @Check(CheckType.FAST)
    public void errorMissingInstanceParameter(Instance instance) {
        Resource resource = instance.eResource();
        String iClass = instance.getClassDefinition();
        Set<String> classParameterNames = PooslCache.get(resource)
                .getInstantiableClassParameters(iClass).keySet();
        for (String classParameter : classParameterNames) {
            boolean found = false;
            for (InstanceParameter ip : instance.getInstanceParameters()) {
                if (ip.getParameter() != null && ip.getParameter().equals(classParameter)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                error("Missing instantiation for parameter " + classParameter, instance,
                        Literals.INSTANCE__CLASS_DEFINITION,
                        PooslIssueCodes.MISSING_INSTANCE_PARAMETER);
            }
        }
    }

    public void warnSendReceiveMismatchUnconnectedPort(Instance instance) {
        Resource resource = instance.eResource();
        ClusterClass aClass = (ClusterClass) instance.eContainer();
        String className = instance.getClassDefinition();
        Set<String> ports = PooslCache.get(resource).getInstantiableClassPorts(className).keySet();
        for (String port : ports) {
            if (!isInstancePortUsed(instance, port, aClass)) {
                // Port is an unconnected port
                List<ConnectedPortsHelper.InstanceAndPort> connectedPorts = ConnectedPortsHelper
                        .getConnectedProcessPorts(instance, port);
                warnSendReceiveMismatch(resource, connectedPorts, instance, port);
            }
        }
    }

    @Check(CheckType.FAST)
    public void warnSendReceiveMismatchInternalChannel(Channel channel) {
        if (channel.getExternalPort() == null && !channel.getInstancePorts().isEmpty()) {
            // Channel is an internal channel
            // get all ports that this channel is connected to and add their
            // messages to the lists of total messages on this channel
            Resource resource = channel.eResource();
            List<ConnectedPortsHelper.InstanceAndPort> connectedPorts = ConnectedPortsHelper
                    .getConnectedProcessPorts(channel);
            warnSendReceiveMismatch(resource, connectedPorts, channel, null);
        }
    }

    /*
     * This function is being used by 2 different functions checking for 
     * mismatches between sending and receiving messages. 
     * The only difference is that one is based on the entrypoint of a channel
     * and the other of an (instance, port). 
     * So either channel or (instance, port) in this call will always be null.
     */
    private void warnSendReceiveMismatch(
            Resource resource, List<ConnectedPortsHelper.InstanceAndPort> connectedPorts,
            EObject context, String port) {
        // Check if the channel is connected to at least 2 process ports
        // otherwise generate a warning and
        // don't check for mismatch in messages because this is surely the case!
        // (and will generate a lot more warnings on the same line).
        if (connectedPorts.isEmpty()) {

            warning(context instanceof Channel
                ? "Internal channel hierarchically connects no process ports"
                : "Unconnected port '" + port + "' hierarchically connects no process ports",
                    context, null, PooslIssueCodes.UNCONNECTED, WarningType.UNCONNECTED);

        } else if (connectedPorts.size() == 1) {
            ConnectedPortsHelper.InstanceAndPort pp = connectedPorts.get(0);

            warning(context instanceof Channel
                ? "Internal channel hierarchically connects only process port " + pp.toString()
                : "Unconnected port '" + port + "' hierarchically connects only process port "
                        + pp.toString(),
                    context, null, PooslIssueCodes.UNCONNECTED, WarningType.UNCONNECTED);

        } else {
            Map<String, Map<ConnectedPortsHelper.InstanceAndPort, List<String>>> sendMap = createMapPortToTypes(
                    resource, connectedPorts, PooslMessageType.SEND);
            Map<String, Map<ConnectedPortsHelper.InstanceAndPort, List<String>>> receiveMap = createMapPortToTypes(
                    resource, connectedPorts, PooslMessageType.RECEIVE);

            warnMessageTypeMismatch(context, port, resource, receiveMap, sendMap,
                    PooslMessageType.SEND);
            warnMessageTypeMismatch(context, port, resource, sendMap, receiveMap,
                    PooslMessageType.RECEIVE);
        }
    }

    private Map<String, Map<ConnectedPortsHelper.InstanceAndPort, List<String>>> createMapPortToTypes(
            Resource resource, List<ConnectedPortsHelper.InstanceAndPort> connectedPorts,
            PooslMessageType type) {
        Map<String, Map<ConnectedPortsHelper.InstanceAndPort, List<String>>> map = new HashMap<>();
        for (ConnectedPortsHelper.InstanceAndPort portEntry : connectedPorts) {
            if (portEntry.getInstantiableClassName() != null && portEntry.getPortName() != null) {
                Iterable<IEObjectDescription> messageSignatures = Iterables.filter(
                        PooslCache.get(resource).getMessages(portEntry.getInstantiableClassName(),
                                type),
                        PooslMessageSignatureDescription.predicateMessage(portEntry.getPortName()));

                for (IEObjectDescription messageSignature : messageSignatures) {
                    List<String> paramTypes = PooslMessageSignatureDescription
                            .getParameterTypes(messageSignature);
                    String key = HelperFunctions.getName(messageSignature) + "|" //$NON-NLS-1$
                            + paramTypes.size();
                    if (key != null) {
                        Map<ConnectedPortsHelper.InstanceAndPort, List<String>> portEntries = map
                                .get(key);
                        if (portEntries == null) {
                            portEntries = new HashMap<>();
                            map.put(key, portEntries);
                        }
                        portEntries.put(portEntry, paramTypes);
                    }
                }
            }
        }
        return map;
    }

    private void warnMessageTypeMismatch(
            EObject context, String port, Resource resource,
            Map<String, Map<ConnectedPortsHelper.InstanceAndPort, List<String>>> actualMap,
            Map<String, Map<ConnectedPortsHelper.InstanceAndPort, List<String>>> expectedMap,
            PooslMessageType expectedMessageType) {

        String actualTypeString = "?"; //$NON-NLS-1$

        String noExchangeMessage = NO_RECEIVING_MESSAGE;
        String mismatchExchange = MISMATCH_SENDING;

        if (expectedMessageType == PooslMessageType.RECEIVE) {
            actualTypeString = "!"; //$NON-NLS-1$

            noExchangeMessage = NO_SENDING_MESSAGE;
            mismatchExchange = MISMATCH_RECEIVE;
        }
        String contextName = context instanceof Channel
            ? "this internal channel" : "unconnected port '" + port + "'";

        for (Entry<String, Map<ConnectedPortsHelper.InstanceAndPort, List<String>>> expectedEntry : //
        expectedMap.entrySet()) {
            String messageEncoding = expectedEntry.getKey();
            int separatorIndex = messageEncoding.lastIndexOf('|');
            String messageName = messageEncoding.substring(0, separatorIndex);
            String numberOfParameters = messageEncoding.substring(separatorIndex + 1);

            Map<ConnectedPortsHelper.InstanceAndPort, List<String>> expectedPortEntries = //
                    expectedEntry.getValue();
            Map<ConnectedPortsHelper.InstanceAndPort, List<String>> actualPortEntries = //
                    actualMap.get(messageEncoding);

            for (Entry<ConnectedPortsHelper.InstanceAndPort, List<String>> expectedPortEntry : //
            expectedPortEntries.entrySet()) {
                ConnectedPortsHelper.InstanceAndPort expectedPortAndProcess = expectedPortEntry
                        .getKey();
                List<String> expectedTypes = expectedPortEntry.getValue();

                Map<ConnectedPortsHelper.InstanceAndPort, List<String>> filteredActualPortEntries = new HashMap<>();
                if (actualPortEntries != null) {
                    for (Entry<ConnectedPortsHelper.InstanceAndPort, List<String>> actualPortEntry : //
                    actualPortEntries.entrySet()) {
                        ConnectedPortsHelper.InstanceAndPort actualPortAndProcess = //
                                actualPortEntry.getKey();
                        if (actualPortAndProcess.canCommunicate(expectedPortAndProcess)) {
                            filteredActualPortEntries.put(actualPortEntry.getKey(),
                                    actualPortEntry.getValue());
                        }
                    }
                }

                if (filteredActualPortEntries.isEmpty()) {

                    warning(MessageFormat.format(noExchangeMessage, contextName,
                            expectedPortAndProcess, messageName, numberOfParameters), context, null,
                            PooslIssueCodes.UNCONNECTED_INTERNAL_CHANNEL, WarningType.UNCONNECTED);

                } else {
                    String incompatibleMessagesString = computeIncompatibleMessages(resource,
                            actualTypeString, messageName, filteredActualPortEntries, expectedTypes,
                            expectedMessageType);
                    if (!"".equals(incompatibleMessagesString)) { //$NON-NLS-1$

                        warning(MessageFormat.format(mismatchExchange, contextName,
                                expectedPortAndProcess, messageName,
                                FormattingHelper.formatTypes(expectedTypes),
                                incompatibleMessagesString), context, null, null,
                                WarningType.UNCONNECTED);
                    }
                }
            }
        }
    }

    private static String computeIncompatibleMessages(
            Resource resource, String actualTypeString, String messageName,
            Map<ConnectedPortsHelper.InstanceAndPort, List<String>> actualPortEntries,
            List<String> expectedTypes, PooslMessageType expectedMessageType) {
        StringBuilder formatted = new StringBuilder();
        for (Entry<ConnectedPortsHelper.InstanceAndPort, List<String>> actualPortEntry : actualPortEntries
                .entrySet()) {
            ConnectedPortsHelper.InstanceAndPort actualPortAndProcess = actualPortEntry.getKey();
            List<String> actualTypes = actualPortEntry.getValue();

            if ((expectedMessageType == PooslMessageType.RECEIVE
                    && !TypingHelper.isSubtype(resource, actualTypes, expectedTypes))
                    || (expectedMessageType == PooslMessageType.SEND
                            && !TypingHelper.isSubtype(resource, expectedTypes, actualTypes))) {
                formatted.append(
                        " " + actualPortAndProcess.toString() + actualTypeString + messageName); //$NON-NLS-1$
                FormattingHelper.formatTypes(formatted, actualTypes);
            }
        }
        return formatted.toString();
    }

    private static boolean isInstancePortUsed(Instance instance, String port, ClusterClass aClass) {
        for (Channel channel : aClass.getChannels()) {
            for (InstancePort instancePort : channel.getInstancePorts()) {
                String instancePortName = (instancePort.getPort() != null)
                    ? instancePort.getPort().getPort() : ""; //$NON-NLS-1$
                if ((instancePort.getInstance() == instance) && (instancePortName.equals(port))) {
                    return true;
                }
            }
        }
        return false;
    }

    @Check(CheckType.FAST)
    public void errorInitImplementation(NewExpression newExpr) {
        IScope initMethods = HelperFunctions.getGlobalScope(newExpr.eResource(),
                Literals.DATA_CLASS__DATA_METHODS_NAMED,
                PooslDataMethodDescription.predicateDataMethodInit(newExpr.getDataClass()));

        if (initMethods.getAllElements().iterator().hasNext()) {
            Iterable<String> initMethodStrings = PooslDataMethodParser
                    .getDataMethodStrings(initMethods.getAllElements());

            EObject container = newExpr.eContainer();
            if (container instanceof DataMethodCallExpression
                    && newExpr == ((DataMethodCallExpression) container).getTarget()) {
                Iterable<IEObjectDescription> initMethodCalled = Iterables.filter(
                        initMethods.getAllElements(),
                        PooslDataMethodDescription.predicateDataMethod(
                                ((DataMethodCallExpression) container).getName(),
                                ((DataMethodCallExpression) container).getArguments().size()));
                if (!initMethodCalled.iterator().hasNext()) {
                    // called method isn't an init method
                    error(MessageFormat.format(INIT_CALL_MISSING, newExpr.getDataClass()), newExpr,
                            null, PooslIssueCodes.INIT_CALL_MISSING,
                            Iterables.toArray(initMethodStrings, String.class));
                }

            } else {
                // newExpr isn't followed by any method call
                error(MessageFormat.format(INIT_CALL_MISSING, newExpr.getDataClass()), newExpr,
                        null, PooslIssueCodes.INIT_CALL_MISSING,
                        Iterables.toArray(initMethodStrings, String.class));
            }
        }
    }
}
