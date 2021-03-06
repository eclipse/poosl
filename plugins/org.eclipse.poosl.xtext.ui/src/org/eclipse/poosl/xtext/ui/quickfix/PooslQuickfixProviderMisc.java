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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.poosl.Channel;
import org.eclipse.poosl.ClusterClass;
import org.eclipse.poosl.Instance;
import org.eclipse.poosl.InstanceParameter;
import org.eclipse.poosl.InstancePort;
import org.eclipse.poosl.NewExpression;
import org.eclipse.poosl.PooslPackage.Literals;
import org.eclipse.poosl.Port;
import org.eclipse.poosl.xtext.custom.PooslCache;
import org.eclipse.poosl.xtext.helpers.PooslDataMethodParser;
import org.eclipse.poosl.xtext.helpers.PooslReferenceHelper;
import org.eclipse.poosl.xtext.ui.contentassist.PooslProposalProviderTypes;
import org.eclipse.poosl.xtext.validation.PooslIssueCodes;
import org.eclipse.poosl.xtext.validation.PooslJavaValidatorSuppress.WarningType;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.impl.HiddenLeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.IModification;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.model.edit.ISemanticModification;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;

/**
 * The PooslQuickfixProviderMisc.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslQuickfixProviderMisc extends PooslQuickfixProviderTypes {

    private static final String SEPARATOR = ", "; //$NON-NLS-1$

    @Fix(PooslIssueCodes.UNKNOWN_ANNOTATION)
    public void unknownAnnotation(final Issue issue, IssueResolutionAcceptor acceptor) {
        addSuppressWarning(issue, acceptor, WarningType.ANNOTATION);
    }

    @Fix(PooslIssueCodes.INIT_CALL_MISSING)
    public void initCallMissing(final Issue issue, IssueResolutionAcceptor acceptor) {
        for (final String dataMethodString : issue.getData()) {
            acceptor.accept(issue,
                    "Apply initialization method "
                            + PooslDataMethodParser.getDeclarationString(dataMethodString), // label
                    null, // description
                    null, // icon
                    new ISemanticModification() {
                        public void apply(EObject element, IModificationContext context) {
                            NewExpression expr = (NewExpression) element;
                            StringBuilder builder = new StringBuilder(" "); //$NON-NLS-1$
                            builder.append(PooslDataMethodParser.getMethodName(dataMethodString));
                            builder.append(PooslProposalProviderTypes.createDefaultParameterList(
                                    PooslDataMethodParser.getNumberArgs(dataMethodString)));

                            applyTextChange(context.getXtextDocument(), element.eResource(),
                                    element, NodeModelUtils.getNode(expr).getTotalEndOffset(), 0,
                                    builder.toString());
                        }
                    });
        }
    }

    @Fix(PooslIssueCodes.UNCONNECTED)
    @Fix(PooslIssueCodes.UNCONNECTED_INTERNAL_CHANNEL)
    public void unconnectedExternalChannel(final Issue issue, IssueResolutionAcceptor acceptor) {
        addSuppressWarning(issue, acceptor, WarningType.UNCONNECTED);
    }

    @Fix(PooslIssueCodes.UNCONNECTED_CHANNEL)
    public void unconnectedChannelWarning(final Issue issue, IssueResolutionAcceptor acceptor) {
        addSuppressWarning(issue, acceptor, WarningType.UNCONNECTED);
        removeChannel(issue, acceptor);
    }

    @Fix(PooslIssueCodes.UNCONNECTED_EXTERNAL_PORT)
    public void unconnectedExternalPort1(final Issue issue, IssueResolutionAcceptor acceptor) {
        addSuppressWarning(issue, acceptor, WarningType.UNCONNECTED);
        acceptor.accept(issue, "Add channel that connects this port", // label
                null, // description
                null, // icon
                new ISemanticModification() {
                    public void apply(EObject element, IModificationContext context) {
                        Port port = (Port) element;
                        ClusterClass cClass = (ClusterClass) port.eContainer();

                        String channel = "\n\t{ " + port.getName() + " }"; //$NON-NLS-1$ //$NON-NLS-2$
                        INode lastNode = getLastChannelNode(cClass);
                        if (lastNode != null
                                && applyTextChange(context.getXtextDocument(), element.eResource(),
                                        cClass, lastNode.getTotalEndOffset(), 0, channel)) {
                            return;
                        }
                        showWarning("Could not create channel.");
                    }
                });
    }

    private INode getLastChannelNode(ClusterClass aClass) {
        List<INode> nodes = NodeModelUtils.findNodesForFeature(aClass,
                Literals.CLUSTER_CLASS__CHANNELS);
        if (!nodes.isEmpty())
            return nodes.get(nodes.size() - 1);
        for (ILeafNode iLeafNode : NodeModelUtils.getNode(aClass).getLeafNodes()) {
            if (!iLeafNode.isHidden() && iLeafNode.getText().equals("channels")) //$NON-NLS-1$
                return iLeafNode;
        }
        return null;
    }

    @Fix(PooslIssueCodes.UNCONNECTED_EXTERNAL_PORT)
    public void unconnectedExternalPort2(final Issue issue, IssueResolutionAcceptor acceptor) {
        acceptor.accept(issue, "Remove this port", // label
                null, // description
                null, // icon
                new ISemanticModification() {
                    public void apply(EObject element, IModificationContext context) {
                        applyTextChange(context.getXtextDocument(), element.eResource(), element,
                                null, ""); //$NON-NLS-1$
                    }
                });
    }

    @Fix(PooslIssueCodes.LOCATION_OF_RETURN_MISSING)
    public void locationOfReturnMissing(final Issue issue, IssueResolutionAcceptor acceptor) {
        addSuppressWarning(issue, acceptor, WarningType.RETURN);
        acceptor.accept(issue, "Insert \"return\"", // label
                null, // description
                null, // icon
                new ISemanticModification() {
                    public void apply(EObject element, IModificationContext context)
                            throws BadLocationException {
                        IXtextDocument xtextDocument = context.getXtextDocument();
                        xtextDocument.replace(issue.getOffset(), 0, "return "); //$NON-NLS-1$
                    }
                });
    }

    @Fix(PooslIssueCodes.LOCATION_OF_RETURN_SUPERFLUOUS)
    public void locationOfReturnSuperfluous(final Issue issue, IssueResolutionAcceptor acceptor) {
        addSuppressWarning(issue, acceptor, WarningType.RETURN);
        acceptor.accept(issue, "Remove \"return\"", // label
                null, // description
                null, // icon
                new ISemanticModification() {
                    public void apply(EObject element, IModificationContext context)
                            throws BadLocationException {
                        IXtextDocument xtextDocument = context.getXtextDocument();
                        xtextDocument.replace(issue.getOffset(), "return".length(), ""); //$NON-NLS-1$ //$NON-NLS-2$
                    }
                });
    }

    @Fix(PooslIssueCodes.EQUALITY_INSTEAD_OF_ASSIGNMENT)
    public void equalityInsteadOfAssignment(final Issue issue, IssueResolutionAcceptor acceptor) {
        acceptor.accept(issue, "Replace equality by assignment", // label
                null, // description
                null, // icon
                new ISemanticModification() {
                    public void apply(EObject element, IModificationContext context)
                            throws BadLocationException {
                        IXtextDocument xtextDocument = context.getXtextDocument();
                        xtextDocument.replace(issue.getOffset(), "=".length(), ":="); //$NON-NLS-1$ //$NON-NLS-2$
                    }
                });
    }

    @Fix(PooslIssueCodes.MISSING_INSTANCE_PARAMETER)
    public void missingInstanceParameter(final Issue issue, IssueResolutionAcceptor acceptor) {
        acceptor.accept(issue, "Add missing parameter instantiations", // label
                null, // description
                null, // icon
                new ISemanticModification() {
                    public void apply(EObject element, IModificationContext context) {
                        Instance instance = (Instance) element;
                        IEObjectDescription iClass = PooslReferenceHelper
                                .getInstantiableClassDescription(instance);
                        if (iClass != null) {

                            Set<String> parameterNames = PooslCache.get(instance.eResource())
                                    .getInstantiableClassParameters(instance.getClassDefinition())
                                    .keySet();

                            Set<String> usedParameters = new HashSet<>();
                            for (InstanceParameter instanceParameter : instance
                                    .getInstanceParameters()) {
                                if (instanceParameter.getParameter() != null) {
                                    usedParameters.add(instanceParameter.getParameter());
                                }
                            }

                            StringBuilder decl = new StringBuilder(""); //$NON-NLS-1$
                            for (String param : parameterNames) {
                                if (!usedParameters.contains(param)) {
                                    if (decl.length() != 0)
                                        decl.append(SEPARATOR);
                                    decl.append(param + " := nil"); //$NON-NLS-1$
                                }
                            }

                            INode lastNode = getLastInstanceParamNodeAndUpdateDecl(instance, decl);
                            if (lastNode != null && applyTextChange(context.getXtextDocument(),
                                    element.eResource(), instance, lastNode.getTotalEndOffset(), 0,
                                    decl.toString())) {
                                return;
                            }
                        }
                        showWarning("Instance parameters could not be added.");
                    }
                });
    }

    @Fix(PooslIssueCodes.MULTIPLE_CHANNELS_CONNECTED_TO_INSTANCE_PORT)
    public void multipleChannelsToInstancePort(
            final Issue issue, IssueResolutionAcceptor acceptor) {
        acceptor.accept(issue, "Merge all channels that are connected to this instance port", // label
                null, // description
                null, // icon
                new ISemanticModification() {
                    public void apply(EObject element, IModificationContext context)
                            throws BadLocationException {

                        InstancePort originalPort = (InstancePort) element;
                        String portName = (originalPort.getPort() != null)
                            ? originalPort.getPort().getPort() : ""; //$NON-NLS-1$
                        Channel originalChannel = (Channel) originalPort.eContainer();
                        ClusterClass aClass = (ClusterClass) originalChannel.eContainer();

                        Set<Channel> channelsToRemove = new HashSet<>();
                        for (Channel channel : aClass.getChannels()) {
                            if (!channel.equals(originalChannel)) {
                                for (InstancePort instancePort : channel.getInstancePorts()) {
                                    String iPortName = (instancePort.getPort() != null)
                                        ? instancePort.getPort().getPort() : ""; //$NON-NLS-1$
                                    if (instancePort.getInstance()
                                            .equals(originalPort.getInstance())
                                            && iPortName.equals(portName)) {
                                        channelsToRemove.add(channel);
                                    }
                                }
                            }
                        }

                        if (!mergeChannels(context.getXtextDocument(), originalChannel,
                                channelsToRemove)) {
                            showWarning("Could not merge channels");
                        }
                    }
                });
    }

    @Fix(PooslIssueCodes.MULTIPLE_CHANNELS_CONNECTED_TO_EXTERNAL_PORT)
    public void multipleChannelsToExternalPort(
            final Issue issue, IssueResolutionAcceptor acceptor) {
        acceptor.accept(issue, "Merge all channels that are connected to this external port", // label
                null, // description
                null, // icon
                new ISemanticModification() {
                    public void apply(EObject element, IModificationContext context)
                            throws BadLocationException {
                        Channel originalChannel = (Channel) element;

                        ClusterClass aClass = (ClusterClass) originalChannel.eContainer();

                        Set<Channel> channelsToRemove = new HashSet<>();
                        for (Channel channel : aClass.getChannels()) {
                            if (!channel.equals(originalChannel) && originalChannel
                                    .getExternalPort().equals(channel.getExternalPort())) {
                                channelsToRemove.add(channel);
                            }
                        }

                        if (!mergeChannels(context.getXtextDocument(), originalChannel,
                                channelsToRemove)) {
                            showWarning("Could not merge channels");
                        }
                    }
                });
    }

    @Fix(PooslIssueCodes.INVALID_IMPORT_VALID_IMPORTLIB)
    public void changeToImportLib(final Issue issue, IssueResolutionAcceptor acceptor) {
        acceptor.accept(issue, "Change import to importlib", // label
                "URI could not be resolved relative to the current file.\nHowever it can be resolved using the Poosl include paths.",
                null, new IModification() {
                    @Override
                    public void apply(IModificationContext context) throws Exception {
                        context.getXtextDocument().replace(issue.getOffset(), 6, "importlib"); //$NON-NLS-1$
                    }
                });
    }

    @Fix(PooslIssueCodes.INVALID_IMPORTLIB_VALID_IMPORT)
    public void changeToImport(final Issue issue, IssueResolutionAcceptor acceptor) {
        acceptor.accept(issue, "Change importlib to import", // label
                "URI could not be resolved using the Poosl include paths.\nHowever it can be resolved relative to the current file.",
                null, new IModification() {
                    @Override
                    public void apply(IModificationContext context) throws Exception {
                        context.getXtextDocument().replace(issue.getOffset(), 9, "import"); //$NON-NLS-1$
                    }
                });
    }

    private boolean mergeChannels(
            IXtextDocument doc, Channel originalChannel, Set<Channel> channelsToRemove) {

        List<TextChange> changes = new ArrayList<>();
        String extPort = (originalChannel.getExternalPort() != null)
            ? originalChannel.getExternalPort().getName() : null;
        Set<String> originalPorts = new HashSet<>();
        // Remove duplicates in original channel
        for (InstancePort originalInstancePort : originalChannel.getInstancePorts()) {
            if (!originalPorts.add(computeInstancePortName(originalInstancePort))) {
                ICompositeNode portNode = NodeModelUtils.findActualNodeFor(originalInstancePort);
                INode prevNode = getInstancePortSeparator(portNode);
                if (portNode != null) {
                    if (prevNode == null) {
                        changes.add(new TextChange(originalChannel.eResource(),
                                portNode.getOffset(), portNode.getLength(), "")); //$NON-NLS-1$
                    } else {
                        changes.add(
                                new TextChange(originalChannel.eResource(), prevNode.getOffset(),
                                        portNode.getTotalEndOffset() - prevNode.getOffset(), "")); //$NON-NLS-1$
                    }
                }
            }
        }

        // get the ports from the merging channel and remove it
        Set<String> newPorts = new HashSet<>();
        for (Channel channel : channelsToRemove) {
            for (InstancePort instancePort : channel.getInstancePorts()) {
                String oldPort = computeInstancePortName(instancePort);
                if (!originalPorts.contains(oldPort))
                    newPorts.add(oldPort);
            }
            if (channel.getExternalPort() != null && extPort == null)
                extPort = channel.getExternalPort().getName();
            changes.add(new TextChange(channel, "")); //$NON-NLS-1$
        }
        // create String with new the new ports to append to original channel
        StringBuilder builder = new StringBuilder();
        for (String newPort : newPorts) {
            builder.append(SEPARATOR + newPort);
        }
        if (originalChannel.getExternalPort() == null && extPort != null) {
            builder.append(SEPARATOR + extPort);
        }

        INode lastNode = getLastChannelPortNode(originalChannel);
        if (lastNode != null) {
            changes.add(new TextChange(originalChannel.eResource(), lastNode.getTotalEndOffset(), 0,
                    builder.toString()));
            return applyTextChanges(doc, originalChannel.eResource(), changes);
        }
        return false;
    }

    private INode getInstancePortSeparator(ICompositeNode portNode) {
        INode node = portNode;
        while (portNode != null && !portNode.getText().equals(",") //$NON-NLS-1$
                && !(portNode instanceof HiddenLeafNode)) {
            node = portNode.getPreviousSibling();
        }
        return node;
    }

    private INode getLastChannelPortNode(Channel originalChannel) {
        INode lastNode = null;
        List<INode> nodes = NodeModelUtils.findNodesForFeature(originalChannel,
                Literals.CHANNEL__INSTANCE_PORTS);
        if (!nodes.isEmpty())
            lastNode = nodes.get(nodes.size() - 1);

        if (originalChannel.getExternalPort() != null) {
            List<INode> extNodes = NodeModelUtils.findNodesForFeature(originalChannel,
                    Literals.CHANNEL__EXTERNAL_PORT);
            if (!extNodes.isEmpty()) {
                INode extNode = extNodes.get(extNodes.size() - 1);
                if (lastNode == null) {
                    return extNode;
                } else {
                    lastNode = (extNode.getOffset() > lastNode.getOffset()) ? extNode : lastNode;
                }
            }
        }
        return lastNode;
    }

    private String computeInstancePortName(InstancePort instancePort) {
        Instance instance = instancePort.getInstance();
        if (instance != null) {
            String instanceName = instance.getName();
            String portName = (instancePort.getPort() != null)
                ? instancePort.getPort().getPort() : ""; //$NON-NLS-1$
            if (instanceName != null && portName != null) {
                return instanceName + "." + portName; //$NON-NLS-1$
            }
        }
        return null;
    }

    private INode getLastInstanceParamNodeAndUpdateDecl(Instance instance, StringBuilder decl) {
        // Case: instance with parameters
        List<INode> nodes = NodeModelUtils.findNodesForFeature(instance,
                Literals.INSTANCE__INSTANCE_PARAMETERS);
        if (!nodes.isEmpty()) {
            decl.insert(0, SEPARATOR);
            return nodes.get(nodes.size() - 1);
        }

        // Case: instance without parameters but with ()
        for (ILeafNode iLeafNode : NodeModelUtils.findActualNodeFor(instance).getLeafNodes()) {
            if (!iLeafNode.isHidden() && iLeafNode.getText().equals("(")) { //$NON-NLS-1$
                return iLeafNode;
            }
        }

        // Case: instance without parameters and without ()
        decl.insert(0, "("); //$NON-NLS-1$
        decl.append(")"); //$NON-NLS-1$
        return NodeModelUtils.getNode(instance);
    }
}
