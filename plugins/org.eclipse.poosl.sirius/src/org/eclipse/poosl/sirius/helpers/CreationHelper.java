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
package org.eclipse.poosl.sirius.helpers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.poosl.Channel;
import org.eclipse.poosl.ClusterClass;
import org.eclipse.poosl.DataClass;
import org.eclipse.poosl.DataMethod;
import org.eclipse.poosl.DataMethodBinaryOperator;
import org.eclipse.poosl.DataMethodNamed;
import org.eclipse.poosl.DataMethodUnaryOperator;
import org.eclipse.poosl.Declaration;
import org.eclipse.poosl.Instance;
import org.eclipse.poosl.InstancePort;
import org.eclipse.poosl.InstantiableClass;
import org.eclipse.poosl.OperatorBinary;
import org.eclipse.poosl.OperatorUnary;
import org.eclipse.poosl.Poosl;
import org.eclipse.poosl.PooslFactory;
import org.eclipse.poosl.PooslPackage.Literals;
import org.eclipse.poosl.Port;
import org.eclipse.poosl.PortReference;
import org.eclipse.poosl.ProcessClass;
import org.eclipse.poosl.ProcessMethod;
import org.eclipse.poosl.ProcessMethodCall;
import org.eclipse.poosl.ReturnExpression;
import org.eclipse.poosl.Variable;
import org.eclipse.poosl.impl.PooslFactoryImpl;
import org.eclipse.poosl.sirius.dialogs.NameDialog;
import org.eclipse.poosl.sirius.dialogs.NameDialog.PooslClassType;
import org.eclipse.poosl.sirius.dialogs.NewInstanceDialog;
import org.eclipse.poosl.sirius.dialogs.NewInstanceDialog.Type;
import org.eclipse.poosl.sirius.dialogs.NewMethodDataDialog;
import org.eclipse.poosl.sirius.dialogs.NewMethodProcessDialog;
import org.eclipse.poosl.sirius.dialogs.NewPortDialog;
import org.eclipse.poosl.sirius.dialogs.NewVariableDialog;
import org.eclipse.poosl.sirius.dialogs.PooslProgressMonitor;
import org.eclipse.poosl.sirius.services.CompositeStructureDiagramServices;
import org.eclipse.poosl.xtext.custom.PooslCache;
import org.eclipse.poosl.xtext.helpers.HelperFunctions;
import org.eclipse.poosl.xtext.helpers.PooslReferenceHelper;
import org.eclipse.poosl.xtext.scoping.PooslScopeProvider;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * The CreationHelper.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class CreationHelper {
    private static final ILog LOGGER = Platform.getLog(CreationHelper.class);

    private static final String COMMA = ","; //$NON-NLS-1$

    private static final String MESSAGE_TITLE_ADD_PORT = "Adding port";

    private static final String MESSAGE_ADD_PORT = "You are adding a port to an instance. Continuing will add a port to ";

    private static final String MESSAGE_TITLE_DELETE_PORT = "Deleting port";

    private static final String MESSAGE_DELETE_PORT = "Are you sure you want to delete this port? Deleting the port from this instance will remove the port from the class, which is used at multiple places.";

    private static final String MESSAGE_TITLE_IN_USE = "Element in use";

    private static final String USED_BY_MESSAGE = "This element cannot be edited or deleted, because it is used by ";

    private static final String USED_BY_INSTANCE_CLUSTER = "instance \"%s\" in cluster class \"%s\".";

    private static final String USED_BY_INSTANCE_SYSTEM = "instance \"%s\" in system.";

    private static final String USED_BY_METHOD_DATA = "method \"%s\" in data class \"%s\".";

    private static final String USED_BY_METHOD_PROCESS = "method \"%s\" in process class \"%s\".";

    private static final String USED_BY_DATACLASS = "data class \"%s\".";

    private static final String USED_BY_PROCESSCLASS = "process class \"%s\".";

    private static final String USED_BY_CLUSTERCLASS = "cluster class \"%s\".";

    private static final String USED_BY_SYSTEM = "system.";

    private static final String USED_BY_INITIAL_METHOD_CALL = "the initial method call of process class \"%s\".";

    private static final String USED_BY_CHANNEL_SYSTEM = "a channel in system.";

    private static final String USED_BY_CHANNEL_CLUSTERCLASS = "a channel in cluster class \"%s\".";

    private CreationHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static void createNewInstance(ClusterClass container) {
        createNewInstance(container, null);
    }

    public static void createNewInstance(ClusterClass container, InstantiableClass inst) {
        Poosl poosl = HelperFunctions.getContainingPoosl(container);
        if (poosl == null) {
            return;
        }

        NewInstanceDialog inputDialog = new NewInstanceDialog(Display.getDefault().getActiveShell(),
                poosl, container);
        if (inst != null) {
            inputDialog.setInstantiableClass(inst);
        }

        if (inputDialog.open() == Window.OK) {
            String name = inputDialog.getName();
            String classDef = (inst == null)
                ? getInstantiableClass(container, poosl, inputDialog) : inst.getName();

            Instance instance = PooslFactoryImpl.init().createInstance();
            instance.setClassDefinition(classDef);
            instance.setName(name);
            container.getInstances().add(instance);
            saveChanges(container);
        }
    }

    /**
     * Creates a channel with the two ports, if a channel already exists add the
     * source port to the target port. If 2
     * channels are selected the 2 channels are combined
     * 
     * @param sourceobject
     *     to connect
     * @param targetView
     *     representation of target
     * @param targetobject
     */
    public static void createConnection(
            EObject sourceobject, EObject targetView, EObject targetobject) {
        if (sourceobject instanceof Port) {
            // From externalport
            doCreateConnection((Port) sourceobject, targetView, targetobject);
        } else if (sourceobject instanceof InstancePort) {
            // From instanceport
            doCreateConnection((InstancePort) sourceobject, targetView, targetobject);
        } else if (sourceobject instanceof Channel) {
            // From Channel
            doCreateConnection((Channel) sourceobject, targetView, targetobject);
        }
        CreationHelper.doValidate(targetobject, targetView);
    }

    private static void doCreateConnection(
            Port sourceport, EObject targetView, EObject targetobject) {
        Channel sourcechannel = findChannelForExternalPort(sourceport);
        InstancePort targetport = null;
        Channel targetchannel = null;
        if (targetobject instanceof Channel) {
            // exterport to channel
            targetchannel = (Channel) targetobject;
        } else if (targetobject instanceof InstancePort) {
            // exterport to instanceport
            targetport = (InstancePort) targetobject;
            targetchannel = (Channel) targetport.eContainer();
        } else if (targetobject instanceof Port) {
            return;
        }
        combineChannels(sourceport, sourcechannel, targetport, targetchannel);
    }

    private static void doCreateConnection(
            InstancePort sourceport, EObject targetView, EObject targetobject) {
        Channel sourcechannel = (Channel) sourceport.eContainer();
        if (targetobject instanceof Channel) {
            // instanceport to channel
            Channel targetchannel = (Channel) targetobject;
            combineChannels(sourcechannel, targetchannel, sourceport, null);
        } else if (targetobject instanceof InstancePort) {
            // instanceport to instanceport
            InstancePort targetport = (InstancePort) targetobject;
            Channel targetchannel = (Channel) targetport.eContainer();
            combineChannels(sourcechannel, targetchannel, sourceport, targetport);
        } else if (targetobject instanceof Port) {
            // instanceport to externport
            Port targetport = (Port) targetobject;
            Channel targetchannel = findChannelForExternalPort(targetport);
            combineChannels(targetport, targetchannel, sourceport, sourcechannel);
        }
    }

    private static void doCreateConnection(
            Channel sourcechannel, EObject targetView, EObject targetobject) {
        if (targetobject instanceof Channel) {
            // channel to channel
            Channel targetchannel = (Channel) targetobject;
            combineChannels(sourcechannel, targetchannel, null, null);
        } else if (targetobject instanceof InstancePort) {
            // channel to instanceport
            InstancePort targetport = (InstancePort) targetobject;
            Channel targetchannel = (Channel) targetport.eContainer();
            combineChannels(sourcechannel, targetchannel, null, targetport);
        } else if (targetobject instanceof Port) {
            // channel to externalport
            Port targetport = (Port) targetobject;
            Channel targetchannel = findChannelForExternalPort(targetport);
            combineChannels(targetport, targetchannel, null, sourcechannel);
        }
    }

    /**
     * The connection is changed which means a port should be removed from the
     * channel and add the new port to the
     * channel. This method uses
     * {@link CreationHelper#createConnection(EObject, Object, EObject)} to add
     * the new port
     * to the channel.
     * 
     * @param channel
     *     to update
     * @param view
     *     element to refresh
     * 
     * @param old
     *     The port that will be removed from the channel
     * @param oldContainer
     *     Diagram target element
     * @param target
     *     The port that will added to the channel
     * @param targetContainer
     */
    public static void reconnectConnection(
            Channel channel, EObject view, //
            EObject old, EObject oldContainer, EObject target, EObject targetContainer) {

        // Remove disconnected port from channel and remember this channel
        doDeleteChannelConnection(channel, old, oldContainer);

        if (target instanceof InstancePort) {
            channel.getInstancePorts().add((InstancePort) target);
        } else if (targetContainer instanceof ClusterClass) {
            channel.setExternalPort((Port) target);
        } else {
            channel.getInstancePorts().add(//
                    createInstancePort((Instance) targetContainer, (Port) target));
        }

        CreationHelper.doValidate(target, view);
    }

    /**
     * Creates InstancePort.
     * 
     * @param instance
     *     of element
     * @param port
     *     of element
     * @return new unattached InstancePort
     */
    public static InstancePort createInstancePort(Instance instance, Port port) {
        InstancePort result = PooslFactoryImpl.init().createInstancePort();
        result.setInstance(instance);
        PortReference portRef = PooslFactoryImpl.init().createPortReference();
        portRef.setPort(port.getName());
        result.setPort(portRef);
        return result;
    }

    public static void createNewPort(EObject object) {
        if (object instanceof ClusterClass) {
            ClusterClass cl = (ClusterClass) object;
            addPort(cl);
            saveChanges(cl);
        } else if (object instanceof Instance) {
            Instance instance = (Instance) object;
            InstantiableClass obj = PooslReferenceHelper.getInstantiableClassEObject(instance);
            if (obj != null) {
                String type = (obj instanceof ClusterClass) ? "cluster class" : "process class";
                boolean dialog = MessageDialog.openConfirm(Display.getDefault().getActiveShell(),
                        MESSAGE_TITLE_ADD_PORT, MESSAGE_ADD_PORT + type + " " + obj.getName()); //$NON-NLS-1$
                if (dialog) {
                    addPort(obj);
                    saveChanges(obj);
                }
            }
        }
    }

    public static void createVariable(EObject container) {
        NewVariableDialog variableDialog = new NewVariableDialog(
                Display.getDefault().getActiveShell(), container.eResource(),
                getExistingVariablesFromClass(container), "variable"); //$NON-NLS-1$
        if (variableDialog.open() == Window.OK) {
            String name = variableDialog.getName();
            String selectedClass = variableDialog.getSelectedClass();

            Declaration declaration = createDeclaration(name, selectedClass);
            if (container instanceof DataClass) {
                ((DataClass) container).getInstanceVariables().add(declaration);
            } else if (container instanceof ProcessClass) {
                ((ProcessClass) container).getInstanceVariables().add(declaration);
            }
            saveChanges(container);
        }
    }

    public static Declaration createDeclaration(List<String> variables, String type) {
        PooslFactory pooslFactory = PooslFactoryImpl.init();
        Declaration declaration = pooslFactory.createDeclaration();
        for (String string : variables) {
            Variable var = pooslFactory.createVariable();
            var.setName(string);
            declaration.getVariables().add(var);
        }
        declaration.setType(type);
        return declaration;
    }

    public static void createParameter(final InstantiableClass container) {
        Resource resource = container.eResource();
        final NewVariableDialog parameterDialog = new NewVariableDialog(
                Display.getDefault().getActiveShell(), resource,
                getExistingVariablesFromClass(container), "parameter"); //$NON-NLS-1$

        if (parameterDialog.open() == Window.OK) {
            String name = parameterDialog.getName();
            String selectedClass = parameterDialog.getSelectedClass();

            Declaration declaration = createDeclaration(name, selectedClass);
            declaration.setType(selectedClass);
            container.getParameters().add(declaration);
        }
        saveChanges(container);
    }

    public static void createMethod(EObject container) {
        if (container instanceof ProcessClass) {
            ProcessClass processclass = (ProcessClass) container;
            NewMethodProcessDialog methodDialog = new NewMethodProcessDialog(
                    Display.getDefault().getActiveShell(), processclass);
            if (methodDialog.open() == Window.OK) {
                String name = methodDialog.getName();
                List<Declaration> inputVariables = methodDialog.getInputVariables();
                List<Declaration> outputVariables = methodDialog.getOutputVariables();

                PooslFactory pooslFactory = PooslFactoryImpl.init();
                ProcessMethod processMethod = pooslFactory.createProcessMethod();
                processMethod.setName(name);
                processMethod.setBody(pooslFactory.createSkipStatement());
                processMethod.getInputParameters().addAll(inputVariables);
                processMethod.getOutputParameters().addAll(outputVariables);
                processclass.getMethods().add(processMethod);
            }
        } else if (container instanceof DataClass) {
            DataClass dataClass = (DataClass) container;
            NewMethodDataDialog methodDialog = new NewMethodDataDialog(
                    Display.getDefault().getActiveShell(), dataClass);
            if (methodDialog.open() == Window.OK) {
                String name = methodDialog.getName();
                List<Declaration> inputVariables = methodDialog.getInputVariables();
                String selectedClass = methodDialog.getSelectedClass();

                PooslFactory pooslFactory = PooslFactoryImpl.init();
                DataMethodNamed dataMethod = pooslFactory.createDataMethodNamed();
                dataMethod.setName(name);
                ReturnExpression returnExpression = pooslFactory.createReturnExpression();
                returnExpression.setExpression(pooslFactory.createNilConstant());
                dataMethod.setBody(returnExpression);
                dataMethod.getParameters().addAll(inputVariables);
                dataMethod.setReturnType(selectedClass);
                dataClass.setNative(false);
                dataClass.getDataMethodsNamed().add(dataMethod);
            }
        }
        saveChanges(container);
    }

    public static void createProcessClass(Poosl container) {
        NameDialog nameDialog = new NameDialog(Display.getDefault().getActiveShell(),
                NameHelper.getAllInstantiableNames(container), PooslClassType.PROCESSCLASS);
        if (nameDialog.open() == Window.OK) {
            String name = nameDialog.getName();

            ProcessClass processClass = createProcessClass(name);
            container.getProcessClasses().add(processClass);
            saveChanges(container);
        }
    }

    public static void createDataClass(Poosl container) {
        List<String> dataClassNames = new ArrayList<>(
                PooslCache.get(container.eResource()).getDataClassMap().keySet());

        NameDialog nameDialog = new NameDialog(Display.getDefault().getActiveShell(),
                dataClassNames, PooslClassType.DATACLASS);
        if (nameDialog.open() == Window.OK) {
            PooslFactory pooslFactory = PooslFactoryImpl.init();
            DataClass dataClass = pooslFactory.createDataClass();
            dataClass.setName(nameDialog.getName());
            container.getDataClasses().add(dataClass);
            saveChanges(container);
        }
    }

    public static void createSystem(Poosl container) {
        ClusterClass system = HelperFunctions.getSystem(container);
        if (system == null) {
            PooslFactory factory = PooslFactoryImpl.init();
            system = factory.createClusterClass();
            container.getClusterClasses().add(system);
            saveChanges(container);
        }
    }

    public static void createClusterClass(Poosl container) {
        NameDialog nameDialog = new NameDialog(Display.getDefault().getActiveShell(),
                NameHelper.getAllInstantiableNames(container), PooslClassType.CLUSTERCLASS);
        if (nameDialog.open() == Window.OK) {
            String name = nameDialog.getName();

            ClusterClass clusterClass = createClusterClass(name);
            container.getClusterClasses().add(clusterClass);
            saveChanges(container);
        }
    }

    public static void deleteInstance(Instance instance) {
        if (isEditAllowed(instance) && instance.eContainer() instanceof ClusterClass) {
            ClusterClass cluster = (ClusterClass) instance.eContainer();
            for (Iterator<Channel> channelIt = cluster.getChannels().iterator(); channelIt
                    .hasNext(); /**/) {
                Channel channel = channelIt.next();
                for (Iterator<InstancePort> iterator = channel.getInstancePorts()
                        .iterator(); iterator.hasNext(); /**/) {
                    InstancePort instanceport = iterator.next();
                    if (instanceport.getInstance() == instance) {
                        iterator.remove();
                        if (channel.getInstancePorts().isEmpty()
                                || (channel.getInstancePorts().size() == 1
                                        && channel.getExternalPort() == null)) {
                            channelIt.remove();
                        }
                    }
                }
            }
            cluster.getInstances().remove(instance);
            saveChanges(cluster);
        }
    }

    public static void deleteChannelConnection(Channel channel, EObject port, EObject portParent) {
        doDeleteChannelConnection(channel, port, portParent);
        saveChanges(channel);
    }

    private static void doDeleteChannelConnection(
            Channel channel, EObject port, EObject portParent) {
        if (port instanceof InstancePort) {
            channel.getInstancePorts().remove(port);
        } else if (portParent instanceof ClusterClass) {
            channel.setExternalPort(null);
        } else {
            String portName = ((Port) port).getName();
            channel.getInstancePorts().removeIf(it -> //
            it.getPort().getPort().equals(portName) && it.getInstance() == portParent);
        }
        saveChanges(channel);

    }

    public static boolean deleteInstanceVariable(Variable var) {
        if (isEditAllowed(var) && var.eContainer() instanceof Declaration) {
            Declaration declaration = (Declaration) var.eContainer();

            EObject adjusted = null;
            if (declaration.getVariables().size() > 1) {
                declaration.getVariables().remove(var);
                adjusted = declaration;
            } else {
                adjusted = declaration.eContainer();
                if (declaration.eContainer() instanceof ProcessClass) {
                    ProcessClass processclass = (ProcessClass) declaration.eContainer();
                    processclass.getInstanceVariables().remove(declaration);
                } else if (declaration.eContainer() instanceof DataClass) {
                    DataClass dataclass = (DataClass) declaration.eContainer();
                    dataclass.getInstanceVariables().remove(declaration);
                }
            }

            if (adjusted != null) {
                saveChanges(adjusted);
            }
            return true;
        }
        return false;
    }

    public static void deleteParameter(Variable var) {
        if (isEditAllowed(var) && var.eContainer() instanceof Declaration) {
            Declaration declaration = (Declaration) var.eContainer();

            EObject adjusted = null;

            if (declaration.getVariables().size() > 1) {
                declaration.getVariables().remove(var);
                adjusted = declaration;

            } else {
                if (declaration.eContainer() instanceof InstantiableClass) {
                    InstantiableClass inst = (InstantiableClass) declaration.eContainer();
                    inst.getParameters().remove(declaration);
                    adjusted = inst;
                }
            }
            saveChanges(adjusted);
        }
    }

    public static void deleteMethod(ProcessMethod method) {
        if (isEditAllowed(method) && method.eContainer() instanceof ProcessClass) {
            ProcessClass processclass = (ProcessClass) method.eContainer();
            processclass.getMethods().remove(method);
            saveChanges(processclass);
        }
    }

    public static void deleteMethod(DataMethod method) {
        if (method.eContainer() instanceof DataClass) {
            DataClass dataclass = (DataClass) method.eContainer();
            if (method instanceof DataMethodNamed) {
                DataMethodNamed datamethodnamed = (DataMethodNamed) method;
                dataclass.getDataMethodsNamed().remove(datamethodnamed);
            } else if (method instanceof DataMethodUnaryOperator) {
                DataMethodUnaryOperator datamethodunary = (DataMethodUnaryOperator) method;
                dataclass.getDataMethodsUnaryOperator().remove(datamethodunary);
            } else if (method instanceof DataMethodBinaryOperator) {
                DataMethodBinaryOperator datamethobinarynamed = (DataMethodBinaryOperator) method;
                dataclass.getDataMethodsBinaryOperator().remove(datamethobinarynamed);
            }
            saveChanges(dataclass);
        }
    }

    public static void deleteInstancePort(InstancePort iPort) {
        IEObjectDescription port = PooslReferenceHelper.getInstancePortDescription(iPort);
        if (port != null) {
            EObject obj = port.getEObjectOrProxy();
            if (obj.eIsProxy())
                obj = EcoreUtil.resolve(obj, iPort);
            deletePort((Port) obj);
        } else {
            LOGGER.error("Port couldn't be found.");
            // should not happen?
        }
    }

    public static void deleteInheritance(EObject element) {
        showDialogInUse("Deletion of inheritance relations is not supported yet.");
    }

    public static void deleteClusterClass(ClusterClass element) {
        if (isEditAllowed(element)) {
            Poosl poosl = HelperFunctions.getContainingPoosl(element);
            poosl.getClusterClasses().remove(element);
            saveChanges(poosl);
        }
    }

    public static void deleteDataClass(DataClass dclass) {
        if (isEditAllowed(dclass)) {
            Poosl poosl = HelperFunctions.getContainingPoosl(dclass);
            poosl.getDataClasses().remove(dclass);
            saveChanges(poosl);
        }
    }

    public static void deleteProcessClass(ProcessClass element) {
        if (isEditAllowed(element)) {
            Poosl poosl = HelperFunctions.getContainingPoosl(element);
            poosl.getProcessClasses().remove(element);
            saveChanges(poosl);
        }
    }

    public static void deleteContainment(EObject source, EObject target) {
        if (source instanceof ClusterClass && target instanceof InstantiableClass) {
            ClusterClass cluster = (ClusterClass) source;
            InstantiableClass inst = (InstantiableClass) target;
            List<Instance> targetInstances = new ArrayList<>();
            for (Instance instance : cluster.getInstances()) {
                if (instance.getClassDefinition().equals(inst.getName())) {
                    if (!isEditAllowed(instance)) {
                        return;
                    }
                    targetInstances.add(instance);
                }
            }
            cluster.getInstances().removeAll(targetInstances);
            saveChanges(cluster);
        }
    }

    public static void editMethod(ProcessMethod processMethod) {
        if (isEditAllowed(processMethod)) {
            NewMethodProcessDialog methodDialog = new NewMethodProcessDialog(
                    Display.getDefault().getActiveShell(),
                    (ProcessClass) processMethod.eContainer());
            methodDialog.setProcessMethod(processMethod);

            if (methodDialog.open() == Window.OK) {
                String name = methodDialog.getName();
                List<Declaration> inputVariables = methodDialog.getInputVariables();
                List<Declaration> outputVariables = methodDialog.getOutputVariables();

                processMethod.setName(name);
                for (Declaration declaration : inputVariables) {
                    if (!processMethod.getInputParameters().contains(declaration)) {
                        processMethod.getInputParameters().add(declaration);
                    } else if (declaration.getVariables().isEmpty()) {
                        processMethod.getInputParameters().remove(declaration);
                    }
                }
                for (Declaration declaration : outputVariables) {
                    if (!processMethod.getOutputParameters().contains(declaration)) {
                        processMethod.getOutputParameters().add(declaration);
                    } else if (declaration.getVariables().isEmpty()) {
                        processMethod.getOutputParameters().remove(declaration);
                    }
                }
            }
            saveChanges(processMethod);
        }
    }

    public static void editMethod(DataMethod dataMethod) {
        NewMethodDataDialog methodDialog = new NewMethodDataDialog(
                Display.getDefault().getActiveShell(), (DataClass) dataMethod.eContainer());
        methodDialog.setDataMethod(dataMethod);

        if ((methodDialog.open() == Window.OK) && dataMethod instanceof DataMethodNamed) {
            String name = methodDialog.getName();
            List<Declaration> inputVariables = methodDialog.getInputVariables();
            String selectedClass = methodDialog.getSelectedClass();

            ((DataMethodNamed) dataMethod).setName(name);
            for (Declaration declaration : inputVariables) {
                if (!dataMethod.getParameters().contains(declaration)) {
                    dataMethod.getParameters().add(declaration);
                }
            }
            dataMethod.setReturnType(selectedClass);
            saveChanges(dataMethod);
        }
    }

    public static void editVariable(Variable variable) {
        if (isEditAllowed(variable)) {
            Resource resource = variable.eResource();
            Declaration declaration = (Declaration) variable.eContainer();
            NewVariableDialog variableDialog = new NewVariableDialog(
                    Display.getDefault().getActiveShell(), resource,
                    getExistingVariablesWithoutDeclaration(declaration), "variable"); //$NON-NLS-1$
            variableDialog.setVariable(declaration);

            if (variableDialog.open() == Window.OK) {
                String name = variableDialog.getName();
                String selectedClass = variableDialog.getSelectedClass();

                editDeclarationVariables(declaration, name);
                declaration.setType(selectedClass);
                saveChanges(declaration);
            }
        }
    }

    public static Declaration editDeclarationVariables(Declaration declaration, String name) {
        String[] split = name.split(COMMA);
        List<String> variables = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            variables.add(split[i].trim());
        }
        return editDeclarationVariables(declaration, variables);
    }

    public static Declaration editDeclarationVariables(
            Declaration declaration, List<String> variables) {
        PooslFactory pooslFactory = PooslFactoryImpl.init();
        int existingvars = declaration.getVariables().size();

        for (int i = 0; i < variables.size(); i++) {
            if (i < existingvars) {
                declaration.getVariables().get(i).setName(variables.get(i));
            } else { // create new variable if needed
                Variable var = pooslFactory.createVariable();
                var.setName(variables.get(i));
                declaration.getVariables().add(var);
            }
        }

        // remove any excessive variables
        int delete = variables.size();
        for (int i = declaration.getVariables().size() - delete; i > 0; i--) {
            Variable var = declaration.getVariables().get(delete);
            if (isEditAllowed(var)) {
                declaration.getVariables().remove(delete);
            } else {
                delete++;
            }
        }
        return declaration;
    }

    public static void editParameter(Variable variable) {
        if (isEditAllowed(variable)) {
            Resource resource = variable.eResource();
            Declaration declaration = (Declaration) variable.eContainer();

            NewVariableDialog variableDialog = new NewVariableDialog(
                    Display.getDefault().getActiveShell(), resource,
                    getExistingVariablesWithoutDeclaration(declaration), "parameter"); //$NON-NLS-1$
            StringBuilder varname = new StringBuilder();
            for (int i = 0; i < declaration.getVariables().size(); i++) {
                varname = (i > 0) ? varname.append(COMMA) : varname;
                varname.append(declaration.getVariables().get(i).getName());
            }
            variableDialog.setVariable(varname.toString(), declaration.getType());

            if (variableDialog.open() == Window.OK) {
                String name = variableDialog.getName();

                editDeclarationVariables(declaration, name);
                declaration.setType(variableDialog.getSelectedClass());
                saveChanges(declaration);
            }
        }
    }

    private static List<String> getExistingVariablesWithoutDeclaration(Declaration declaration) {
        List<String> existingVariables = getExistingVariablesFromClass(declaration.eContainer());
        existingVariables.removeAll(getVariableNames(declaration.getVariables()));
        return existingVariables;
    }

    public static List<String> getExistingVariablesFromClass(EObject object) {
        if (object instanceof DataClass) {
            return variableScopeToNameList(
                    PooslScopeProvider.helperScope_DataClass_variable((DataClass) object));
        } else if (object instanceof ProcessClass) {
            return variableScopeToNameList(PooslScopeProvider
                    .helperScope_ProcessClass_parameterAndVariable((ProcessClass) object));
        } else if (object instanceof ClusterClass) {
            return getVariableNames(
                    PooslScopeProvider.getLocalScopeParameters((ClusterClass) object));
        } else {
            return Collections.emptyList();
        }
    }

    public static void createInheritance(EObject source, EObject target) {
        if (!source.equals(target))
            if (source instanceof ProcessClass && target instanceof ProcessClass
                    && (((ProcessClass) source).getSuperClass() == null)) {
                ProcessClass sourceProcess = (ProcessClass) source;
                ProcessClass targetProcess = (ProcessClass) target;
                if (!HelperFunctions.isReflexiveAncestorProcess(sourceProcess, targetProcess)) {
                    sourceProcess.setSuperClass(targetProcess.getName());
                    saveChanges(sourceProcess);
                }
            } else if (source instanceof DataClass && target instanceof DataClass
                    && (((DataClass) source).getSuperClass() == null)) {
                DataClass sourceData = (DataClass) source;
                DataClass targetData = (DataClass) target;
                if (!HelperFunctions.isReflexiveAncestorData(sourceData, targetData)) {
                    sourceData.setSuperClass(targetData.getName());
                    saveChanges(sourceData);
                }
            }
    }

    public static void createContainment(EObject source, EObject target) {
        if (!source.equals(target) && source instanceof ClusterClass
                && target instanceof InstantiableClass) {
            CreationHelper.createNewInstance((ClusterClass) source, (InstantiableClass) target);
        }
    }

    private static Channel findChannelForExternalPort(Port sourceport) {
        if (sourceport.eContainer() instanceof ClusterClass) {
            ClusterClass cluster = (ClusterClass) sourceport.eContainer();
            for (Channel clChannel : cluster.getChannels()) {
                if (clChannel.getExternalPort() == sourceport) {
                    return clChannel;
                }
            }
        }
        return null;
    }

    private static String getInstantiableClass(
            ClusterClass container, Poosl poosl, NewInstanceDialog inputDialog) {
        String instclass;
        if (inputDialog.getHasNewClass()) {
            if (inputDialog.getSelectedType() == Type.PROCESSCLASS) {
                ProcessClass process = CreationHelper
                        .createProcessClass(inputDialog.getNewClassName());
                poosl.getProcessClasses().add(process);
                instclass = process.getName();
            } else {
                ClusterClass cluster = CreationHelper
                        .createClusterClass(inputDialog.getNewClassName());
                poosl.getClusterClasses().add(cluster);
                instclass = cluster.getName();
            }
            SessionManager.INSTANCE.getSession(container).save(new NullProgressMonitor());
        } else {
            instclass = inputDialog.getSelectedClass();
        }
        return instclass;
    }

    private static void addPort(InstantiableClass instClass) {
        NewPortDialog inputDialog = new NewPortDialog(Display.getDefault().getActiveShell(),
                instClass);
        if (inputDialog.open() == Window.OK) {
            String name = inputDialog.getName();

            Port p = PooslFactoryImpl.init().createPort();
            p.setName(name);
            instClass.getPorts().add(p);
        }
    }

    private static void combineChannels(
            Port sourceport, Channel sourcechannel, InstancePort targetport,
            Channel targetchannel) {
        if (sourcechannel == targetchannel && sourcechannel != null) {
            return;
        }

        if (targetchannel != null) {
            if (sourcechannel != null) {
                while (!sourcechannel.getInstancePorts().isEmpty()) {
                    // when adding the instanceport it is removed from the
                    // sourcechannel
                    targetchannel.getInstancePorts().add(sourcechannel.getInstancePorts().get(0));
                }

                targetchannel.setExternalPort(sourceport);
                ClusterClass cluster = (ClusterClass) sourcechannel.eContainer();
                cluster.getChannels().remove(sourcechannel);
            } else {
                targetchannel.setExternalPort(sourceport);
            }
        } else if (targetport != null) {
            if (sourcechannel != null) {
                sourcechannel.getInstancePorts().add(targetport);
            } else {
                Channel channel = PooslFactoryImpl.init().createChannel();
                channel.getInstancePorts().add(targetport);
                channel.setExternalPort(sourceport);
                ClusterClass cluster = (ClusterClass) targetport.getInstance().eContainer();
                cluster.getChannels().add(channel);
                SessionManager.INSTANCE.getSession(cluster).save(new PooslProgressMonitor());
            }
        }
    }

    private static void combineChannels(
            Channel sourcechannel, Channel targetchannel, InstancePort sourceport,
            InstancePort targetport) {
        if (sourcechannel == targetchannel && sourcechannel != null) {
            return;
        }

        if (targetchannel != null) {
            if (sourcechannel != null) {
                while (!sourcechannel.getInstancePorts().isEmpty()) {
                    // when adding the instanceport it is removed from the
                    // sourcechannel
                    targetchannel.getInstancePorts().add(sourcechannel.getInstancePorts().get(0));
                }
                if (targetchannel.getExternalPort() == null
                        && sourcechannel.getExternalPort() != null) {
                    targetchannel.setExternalPort(sourcechannel.getExternalPort());
                }
                removeObsoleteChannel(sourcechannel);
            } else {
                targetchannel.getInstancePorts().add(sourceport);
            }
        } else {
            if (sourcechannel != null) {
                sourcechannel.getInstancePorts().add(targetport);
            } else {
                Channel channel = PooslFactoryImpl.init().createChannel();
                channel.getInstancePorts().add(targetport);
                channel.getInstancePorts().add(sourceport);
                ClusterClass cluster = (ClusterClass) targetport.getInstance().eContainer();
                cluster.getChannels().add(channel);
            }
        }
    }

    private static boolean removeObsoleteChannel(Channel object) {
        Channel channel = object;
        if (CompositeStructureDiagramServices.getNumberOfChannelEnds(channel) < 3) {
            if (channel.eContainer() != null) {
                ClusterClass cluster = (ClusterClass) channel.eContainer();
                cluster.getChannels().remove(channel);
            }
            return true;
        }
        return false;
    }

    public static List<Variable> getUsedMethodVariables(Declaration declaration) {
        if (declaration.eContainer() instanceof DataMethod) {
            DataMethod datamethod = (DataMethod) declaration.eContainer();
            return getAllUsedVariables(datamethod);
        } else if (declaration.eContainer() instanceof ProcessMethod) {
            ProcessMethod processmethod = (ProcessMethod) declaration.eContainer();
            return getAllUsedVariables(processmethod);
        }
        return null;
    }

    public static DataClass findDataClass(Resource resource, String selectedClass) {
        IEObjectDescription qName = PooslCache.get(resource).getDataClassMap().get(selectedClass);
        if (qName != null) {
            return (DataClass) qName.getEObjectOrProxy();
        }

        return null;
    }

    private static DialectEditor getDialectEditor(EObject targetobject, EObject targetView) {
        DDiagram diagram = null;
        DialectEditor dialecteditor = null;
        if (targetView instanceof DDiagramElement) {
            DDiagramElement diagramelement = (DDiagramElement) targetView;
            diagram = diagramelement.getParentDiagram();
        } else if (targetView instanceof DDiagram) {
            diagram = (DDiagram) targetView;
        }

        Session session = SessionManager.INSTANCE.getSession(targetobject);

        if (diagram != null && session != null) {
            IEditorPart tempeditor = DialectUIManager.INSTANCE.openEditor(session, diagram,
                    new NullProgressMonitor());

            if (tempeditor instanceof DialectEditor) {
                dialecteditor = (DialectEditor) tempeditor;
                dialecteditor.needsRefresh(DialectEditor.PROP_FORCE_REFRESH);
                dialecteditor.validateRepresentation();
            }
        }
        return dialecteditor;
    }

    public static void saveChanges(EObject eObject) {
        Session session = SessionManager.INSTANCE.getSession(eObject);
        if (session != null) {
            session.save(new NullProgressMonitor());
        }
    }

    private static void doValidate(EObject targetobject, EObject targetView) {

        DialectEditor editor = getDialectEditor(targetobject, targetView);
        if (editor != null) {
            editor.validateRepresentation();
        }
    }

    public static void deletePort(Port port) {
        if (!isEditAllowed(port)) {
            return;
        }

        EObject holder = port.eContainer();
        List<ClusterClass> classesusingport = new ArrayList<>();
        EObject container = port.eContainer();
        if (container instanceof ClusterClass) {
            classesusingport.add((ClusterClass) container);
        }

        boolean allowed = true;
        if (classesusingport.size() > 1) {
            allowed = MessageDialog.openConfirm(Display.getDefault().getActiveShell(),
                    MESSAGE_TITLE_DELETE_PORT, MESSAGE_DELETE_PORT);
        }

        if (allowed) {
            for (ClusterClass cClass : classesusingport) {
                for (Channel channel : cClass.getChannels()) {
                    for (Iterator<InstancePort> iterator = channel.getInstancePorts()
                            .iterator(); iterator.hasNext(); /**/) {
                        InstancePort instancePort = iterator.next();
                        String iPort = (instancePort.getPort() != null)
                            ? instancePort.getPort().getPort() : ""; //$NON-NLS-1$
                        if (iPort.equals(port.getName())) {
                            iterator.remove();
                        }
                    }
                }
                // remove connection inside cluster to the port
                if (port.eContainer() instanceof ClusterClass) {
                    ClusterClass cluster = (ClusterClass) port.eContainer();
                    for (Channel channel : cluster.getChannels()) {
                        if (channel.getExternalPort().getName().equals(port.getName())) {
                            channel.setExternalPort(null);
                        }
                    }
                }
            }
            if (port.eContainer() instanceof InstantiableClass) {
                InstantiableClass instantiableClass = (InstantiableClass) port.eContainer();
                instantiableClass.getPorts().remove(port);
            }
        }
        saveChanges(holder);

    }

    private static void showDialogInUse(String message) {
        MessageDialog.openError(Display.getDefault().getActiveShell(), MESSAGE_TITLE_IN_USE,
                message);
    }

    private static ProcessClass createProcessClass(String name) {
        PooslFactory pooslFactory = PooslFactoryImpl.init();
        ProcessClass processClass = pooslFactory.createProcessClass();
        processClass.setName(name);
        ProcessMethod processMethod = pooslFactory.createProcessMethod();
        processMethod.setName("Initialise");
        processMethod.setBody(pooslFactory.createSkipStatement());
        processClass.getMethods().add(processMethod);
        ProcessMethodCall processMethodCall = pooslFactory.createProcessMethodCall();
        processMethodCall.setMethod(processMethod.getName());
        processClass.setInitialMethodCall(processMethodCall);
        return processClass;
    }

    private static ClusterClass createClusterClass(String name) {
        PooslFactory pooslFactory = PooslFactoryImpl.init();
        ClusterClass clusterClass = pooslFactory.createClusterClass();
        clusterClass.setName(name);
        return clusterClass;
    }

    private static Declaration createDeclaration(String name, String type) {
        ArrayList<String> variables = new ArrayList<>();
        String[] vars = name.split(COMMA);
        for (int i = 0; i < vars.length; i++) {
            variables.add(vars[i].trim());
        }
        return createDeclaration(variables, type);
    }

    private static boolean isEditAllowed(EObject element) {
        Resource resource = element.eResource();
        RefererFinderRunnable runnable = new RefererFinderRunnable(element, resource);
        try {
            IWorkbench wb = PlatformUI.getWorkbench();
            wb.getProgressService().run(true, true, runnable);
        } catch (InvocationTargetException e) {
            LOGGER.warn("Could not find reference to element " + element, e);
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        EObject referer = runnable.getResult();

        if (referer != null) {
            Resource res = referer.eResource();
            if (res instanceof LazyLinkingResource && referer != element) {
                String message = createUsedMessage(referer, res);
                showDialogInUse(message);
                return false;
            }

        }
        return true;
    }

    private static String createUsedMessage(EObject referer, Resource resource) {
        String message = null;

        if (referer instanceof Instance) {
            ClusterClass cluster = (ClusterClass) referer.eContainer();
            message = (cluster.getName() == null)
                ? String.format(USED_BY_INSTANCE_SYSTEM, ((Instance) referer).getName())
                : String.format(USED_BY_INSTANCE_CLUSTER, ((Instance) referer).getName(),
                        cluster.getName());

        } else if (referer instanceof DataMethod) {
            String name = null;
            if (referer instanceof DataMethodNamed) {
                name = ((DataMethodNamed) referer).getName();
            } else if (referer instanceof DataMethodBinaryOperator) {
                OperatorBinary op = ((DataMethodBinaryOperator) referer).getName();
                if (op != null) {
                    name = op.getName();
                }
            } else if (referer instanceof DataMethodUnaryOperator) {
                OperatorUnary op = ((DataMethodUnaryOperator) referer).getName();
                if (op != null) {
                    name = op.getName();
                }
            }
            message = String.format(USED_BY_METHOD_DATA, name,
                    ((DataClass) referer.eContainer()).getName());
        } else if (referer instanceof ProcessMethod) {
            message = String.format(USED_BY_METHOD_PROCESS, ((ProcessMethod) referer).getName(),
                    ((ProcessClass) referer.eContainer()).getName());
        } else if (referer instanceof ProcessMethodCall
                && referer.eContainingFeature() == Literals.PROCESS_CLASS__INITIAL_METHOD_CALL) {
            message = String.format(USED_BY_INITIAL_METHOD_CALL,
                    ((ProcessClass) referer.eContainer()).getName());
        } else if (referer instanceof Channel) {
            message = (((InstantiableClass) referer.eContainer()).getName() == null)
                ? USED_BY_CHANNEL_SYSTEM : String.format(USED_BY_CHANNEL_CLUSTERCLASS,
                        ((ClusterClass) referer.eContainer()).getName());
        } else if (referer instanceof DataClass) {
            message = String.format(USED_BY_DATACLASS, ((DataClass) referer).getName());
        } else if (referer instanceof ProcessClass) {
            message = String.format(USED_BY_PROCESSCLASS, ((ProcessClass) referer).getName());
        } else if (referer instanceof ClusterClass) {
            message = (((ClusterClass) referer).getName() == null)
                ? USED_BY_SYSTEM
                : String.format(USED_BY_CLUSTERCLASS, ((ClusterClass) referer).getName());
        }

        return USED_BY_MESSAGE + message + " (" + resource.getURI().toString() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
    }

    public static List<Variable> getAllUsedVariables(ProcessMethod pMethod) {
        return getAllUsedVariables(pMethod.getBody());
    }

    private static List<Variable> getAllUsedVariables(EObject body) {
        if (body != null) {
            List<Variable> variables = new ArrayList<>();
            TreeIterator<EObject> ti = body.eAllContents();
            while (ti.hasNext()) {
                EObject eobj = ti.next();
                List<EObject> crossReferences = eobj.eCrossReferences();
                for (EObject eObject : crossReferences) {
                    if (eObject instanceof Variable) {
                        variables.add((Variable) eObject);
                    }
                }
            }
            List<EObject> crossReferences = body.eCrossReferences();
            for (EObject eObject : crossReferences) {
                if (eObject instanceof Variable) {
                    variables.add((Variable) eObject);
                }
            }
            return variables;
        } else {
            return Collections.emptyList();
        }
    }

    public static List<Variable> getAllUsedVariables(DataMethod dMethod) {
        return getAllUsedVariables(dMethod.getBody());
    }

    public static List<String> variableScopeToNameList(IScope variableScope) {
        Iterable<String> names = HelperFunctions.getNames(variableScope.getAllElements());
        return Lists.newArrayList(names);
    }

    private static List<String> getVariableNames(Iterable<Variable> variables) {
        Iterable<String> transformed = Iterables.transform(variables, it -> it.getName());
        Iterables.filter(transformed, it -> it != null);
        return Lists.newArrayList(transformed);
    }
}
