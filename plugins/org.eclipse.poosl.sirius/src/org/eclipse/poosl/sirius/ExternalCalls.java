package org.eclipse.poosl.sirius;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.poosl.Channel;
import org.eclipse.poosl.ClusterClass;
import org.eclipse.poosl.DataClass;
import org.eclipse.poosl.DataMethod;
import org.eclipse.poosl.Instance;
import org.eclipse.poosl.InstancePort;
import org.eclipse.poosl.InstantiableClass;
import org.eclipse.poosl.Poosl;
import org.eclipse.poosl.Port;
import org.eclipse.poosl.PortReference;
import org.eclipse.poosl.ProcessClass;
import org.eclipse.poosl.ProcessMethod;
import org.eclipse.poosl.Variable;
import org.eclipse.poosl.impl.PooslFactoryImpl;
import org.eclipse.poosl.sirius.helpers.ColorGraphHelper;
import org.eclipse.poosl.sirius.helpers.CreationHelper;
import org.eclipse.poosl.sirius.helpers.DiagramDebugNote;
import org.eclipse.poosl.sirius.helpers.GraphicalEditorHelper;
import org.eclipse.poosl.sirius.helpers.TextualEditorHelper;
import org.eclipse.poosl.sirius.navigator.GraphicalPreferenceManager;
import org.eclipse.poosl.xtext.helpers.HelperFunctions;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.impl.DRepresentationElementImpl;
import org.eclipse.ui.IEditorPart;

/**
 * This class is used by the poosl.odesign file to have more advanced queries on the model that are called by Acceleo3
 * expressions.
 * 
 * According to sirius documentation this class needs to be stateless and have a constructor without any arguments.
 * There is no guarantee that the same instance will be used when a function is called twice.
 * 
 * @author Koen Staal
 */
public class ExternalCalls implements IExternalJavaAction {
    private static final String PARAMETER_ELEMENT = "element"; //$NON-NLS-1$

    private static final String PARAMETER_TARGET = "target"; //$NON-NLS-1$

    private static final String PARAMETER_SOURCE = "source"; //$NON-NLS-1$

    private static final String PARAMETER_VIEW = "view"; //$NON-NLS-1$

    private static final String ACTION_EDIT_PARAMETER = "editparameter"; //$NON-NLS-1$

    private static final String ACTION_EDIT_VARIABLE = "editvariable"; //$NON-NLS-1$

    private static final String ACTION_EDIT_METHOD = "editmethod"; //$NON-NLS-1$

    private static final String ACTION_DELETE_INSTANCE = "deleteinstance"; //$NON-NLS-1$

    private static final String ACTION_KEY = "action"; //$NON-NLS-1$

    private static final String ACTION_MENU_CHANGE_COLOR = "changecolor"; //$NON-NLS-1$

    private static final String ACTION_MENU_OPEN_TEXTUAL_EDITOR = "opentextualeditor"; //$NON-NLS-1$

    private static final String ACTION_MENU_OPEN_GRAPHICAL_EDITOR = "opengraphicaleditor"; //$NON-NLS-1$

    private static final String ACTION_MENU_OPEN_CLASS_DIAGRAM = "openclassdiagram"; //$NON-NLS-1$

    private static final String ACTION_DOUBLE_CLICK_EDGE = "doubleclickedge"; //$NON-NLS-1$

    private static final String ACTION_DOUBLE_CLICK_OPENEDITOR = "doubleclickopeneditor"; //$NON-NLS-1$

    private static final String ACTION_DOUBLE_CLICK_CHANNEL = "doubleclickchannel"; //$NON-NLS-1$

    private static final String ACTION_PALLET_NODE_CREATION = "createprocessclass"; //$NON-NLS-1$

    private static final String ACTION_PALLET_CREATE_PORT = "createport"; //$NON-NLS-1$

    private static final String ACTION_PALLET_DELETE_PORT = "deleteport"; //$NON-NLS-1$

    private static final String ACTION_PALLET_CREATE_CONNECTION = "createconnection"; //$NON-NLS-1$

    private static final String ACTION_PALLET_RECONNECT_CONNECTION = "reconnectconnection"; //$NON-NLS-1$

    private static final String ACTION_DELETE_CONNECTION = "deleteconnection"; //$NON-NLS-1$

    private static final String ACTION_DELETE_METHOD = "deletemethod"; //$NON-NLS-1$

    private static final String ACTION_DELETE_PORT_FROM_INSTANCE = "deleteportfrominstance"; //$NON-NLS-1$

    private static final String ACTION_DOUBLE_CLICK_CLASSDIAGRAM_MEMBER = "doubleclickclassdiagrammember"; //$NON-NLS-1$

    private static final String ACTION_PALLET_CREATE_VARIABLE = "createvariable"; //$NON-NLS-1$

    private static final String ACTION_PALLET_CREATE_PARAMETER = "createparameter"; //$NON-NLS-1$

    private static final String ACTION_DELETE_PARAMETER = "deleteparameter"; //$NON-NLS-1$

    private static final String ACTION_DELETE_INSTANCE_VARIABLE = "deletevariable"; //$NON-NLS-1$

    private static final String ACTION_PALLET_CREATE_METHOD = "createmethod"; //$NON-NLS-1$

    private static final String ACTION_DELETE_EXTERN_PORT = "deleteexternport"; //$NON-NLS-1$

    private static final String ACTION_SET_INHERITANCE = "setinheritance"; //$NON-NLS-1$

    private static final String ACTION_DELETE_INHERITANCE = "deleteinheritance"; //$NON-NLS-1$

    private static final String ACTION_CREATE_PROCESS = "createprocess"; //$NON-NLS-1$

    private static final String ACTION_CREATE_DATA = "createdata"; //$NON-NLS-1$

    private static final String ACTION_CREATE_CLUSTER = "createcluster"; //$NON-NLS-1$

    private static final String ACTION_DELETE_PROCESS = "deleteprocess"; //$NON-NLS-1$

    private static final String ACTION_DELETE_CLUSTER = "deletecluster"; //$NON-NLS-1$

    private static final String ACTION_DELETE_DATA = "deletedata"; //$NON-NLS-1$

    private static final String ACTION_CREATE_SYSTEM = "createsystem"; //$NON-NLS-1$

    private static final String ACTION_DELETE_SYSTEM = "deletesystem"; //$NON-NLS-1$

    private static final String ACTION_CREATE_CONTAINMENT = "createcontainment"; //$NON-NLS-1$

    private static final String ACTION_DELETE_CONTAINMENT = "deletecontainment"; //$NON-NLS-1$

    private static final String ACTION_SAVE = "save"; //$NON-NLS-1$

    private static final String ACTION_SHOW_HIDE_DEBUG_ELEMENTS = "showhidedebug"; //$NON-NLS-1$

    private static final String ACTION_MENU_OPEN_TEXTUAL_EDITOR_TEXTUAL = "openinstancetextualeditor"; //$NON-NLS-1$

    private static final Logger LOGGER = Logger.getLogger(ExternalCalls.class.getName());

    private static final String DEFAULT_DEBUG_LABEL = "? 0 / ! 0 "; //$NON-NLS-1$

    public ExternalCalls() {
    }

    @Override
    public boolean canExecute(Collection<? extends EObject> arg0) {
        return true;
    }

    @Override
    public void execute(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
        if (parameters.get(ACTION_KEY) != null) {
            String action = (String) parameters.get(ACTION_KEY);
            switch (action) {
            case ACTION_MENU_CHANGE_COLOR:
                ColorGraphHelper.changeColor(eobjects, parameters);
                break;
            case ACTION_MENU_OPEN_TEXTUAL_EDITOR:
                openTextualEditor(eobjects, parameters, false);
                break;
            case ACTION_MENU_OPEN_TEXTUAL_EDITOR_TEXTUAL:
                openTextualEditor(eobjects, parameters, true);
                break;
            case ACTION_MENU_OPEN_GRAPHICAL_EDITOR:
                openGraphicalEditor(eobjects, parameters);
                break;
            case ACTION_MENU_OPEN_CLASS_DIAGRAM:
                openClassDiagram(eobjects, parameters);
                break;
            case ACTION_DOUBLE_CLICK_OPENEDITOR:
                handleDoubleClickOpenEditor(eobjects, parameters);
                break;
            case ACTION_DOUBLE_CLICK_CHANNEL:
                handleDoubleClickChannel(eobjects);
                break;
            case ACTION_DOUBLE_CLICK_EDGE:
                handleDoubleClickEdge(eobjects);
                break;
            case ACTION_PALLET_NODE_CREATION:
                createInstance(eobjects, parameters);
                break;
            case ACTION_DELETE_INSTANCE:
                deleteInstance(eobjects, parameters);
                break;
            case ACTION_PALLET_CREATE_PORT:
                createPort(eobjects, parameters);
                break;
            case ACTION_PALLET_DELETE_PORT:
                deleteport(eobjects, parameters);
                break;
            case ACTION_PALLET_CREATE_CONNECTION:
                createConnection(eobjects, parameters);
                break;
            case ACTION_PALLET_RECONNECT_CONNECTION:
                reconnectConnection(eobjects, parameters);
                break;
            case ACTION_DELETE_CONNECTION:
                deleteConnection(eobjects, parameters);
                break;
            case ACTION_DELETE_EXTERN_PORT:
                deleteExternPort(eobjects, parameters);
                break;
            case ACTION_DELETE_PORT_FROM_INSTANCE:
                deletePortFromInstance(eobjects, parameters);
                break;
            case ACTION_DOUBLE_CLICK_CLASSDIAGRAM_MEMBER:
                handleDoubleClickClassDiagramMember(eobjects);
                break;
            case ACTION_PALLET_CREATE_VARIABLE:
                createInstanceVariable(eobjects, parameters);
                break;
            case ACTION_PALLET_CREATE_PARAMETER:
                createParameter(eobjects, parameters);
                break;
            case ACTION_DELETE_PARAMETER:
                deleteParameter(eobjects, parameters);
                break;
            case ACTION_DELETE_INSTANCE_VARIABLE:
                deleteInstanceVariable(eobjects, parameters);
                break;
            case ACTION_PALLET_CREATE_METHOD:
                createMethod(eobjects, parameters);
                break;
            case ACTION_EDIT_METHOD:
                editMethod(eobjects, parameters);
                break;
            case ACTION_EDIT_VARIABLE:
                editVariable(eobjects, parameters);
                break;
            case ACTION_EDIT_PARAMETER:
                editParameter(eobjects, parameters);
                break;
            case ACTION_DELETE_METHOD:
                deleteMethod(eobjects, parameters);
                break;
            case ACTION_SET_INHERITANCE:
                createInheritance(eobjects, parameters);
                break;
            case ACTION_CREATE_CONTAINMENT:
                createContainment(eobjects, parameters);
                break;
            case ACTION_DELETE_INHERITANCE:
                deleteInheritance(eobjects, parameters);
                break;
            case ACTION_CREATE_PROCESS:
                createProcess(eobjects, parameters);
                break;
            case ACTION_CREATE_DATA:
                createData(eobjects, parameters);
                break;
            case ACTION_CREATE_CLUSTER:
                createCluster(eobjects, parameters);
                break;
            case ACTION_DELETE_PROCESS:
                deleteProcess(eobjects, parameters);
                break;
            case ACTION_DELETE_DATA:
                deleteData(eobjects, parameters);
                break;
            case ACTION_DELETE_CLUSTER:
                deleteCluster(eobjects, parameters);
                break;
            case ACTION_CREATE_SYSTEM:
                createSystem(eobjects, parameters);
                break;
            case ACTION_DELETE_SYSTEM:
                // TODO remove from gui
                break;
            case ACTION_DELETE_CONTAINMENT:
                deleteContainment(eobjects, parameters);
                break;
            case ACTION_SAVE:
                save(eobjects, parameters);
                break;
            case ACTION_SHOW_HIDE_DEBUG_ELEMENTS:
                showHideDebugElements(eobjects, parameters);
                break;
            default:
                LOGGER.log(Level.SEVERE, "Unknown action:" + action);
            }
        } else {
            LOGGER.log(Level.SEVERE, "No Action specified");
        }
    }

    private void showHideDebugElements(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
        Object view = parameters.get(PARAMETER_VIEW);

        if (view instanceof ArrayList<?>) {
            view = ((ArrayList<?>) view).get(0);
        }

        if (view instanceof EObject) {
            EObject eobj = (EObject) view;
            while (eobj != null && !(eobj instanceof DSemanticDiagram)) {
                eobj = eobj.eContainer();
            }

            if (eobj instanceof DSemanticDiagram) {
                DSemanticDiagram diagram = (DSemanticDiagram) eobj;

                DiagramDebugNote note = new DiagramDebugNote(diagram);

                if (note.isVisible()) {
                    note.setVisible(false);
                    for (DEdge dEdge : diagram.getEdges()) {
                        EdgeStyle style = dEdge.getOwnedStyle();
                        style.getBeginLabelStyle().getDescription().setLabelExpression(""); //$NON-NLS-1$
                        style.getEndLabelStyle().getDescription().setLabelExpression(""); //$NON-NLS-1$
                    }
                } else {
                    note.setVisible(true);
                    for (DEdge dEdge : diagram.getEdges()) {
                        if (!isChannelConnection(dEdge.getTargetNode())) {
                            dEdge.getOwnedStyle().getEndLabelStyle().getDescription().setLabelExpression(DEFAULT_DEBUG_LABEL);
                        }
                        if (!isChannelConnection(dEdge.getSourceNode())) {
                            dEdge.getOwnedStyle().getBeginLabelStyle().getDescription().setLabelExpression(DEFAULT_DEBUG_LABEL);
                        }
                    }
                }
            }
        }
    }

    private boolean isChannelConnection(EdgeTarget edgeside) {
        if (edgeside instanceof DNode) {
            DNode node = (DNode) edgeside;
            return node.getTarget() instanceof Channel;
        }
        return false;
    }

    private void save(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
        Object container = parameters.get("container"); //$NON-NLS-1$
        if (container instanceof EObject) {
            CreationHelper.saveChanges((EObject) container);
        }
    }

    private void deleteContainment(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
        Object view = parameters.get(PARAMETER_VIEW);

        if (view instanceof DEdge) {
            DEdge edge = (DEdge) view;

            if (edge.getSourceNode() instanceof DNodeList && edge.getTargetNode() instanceof DNodeList) {
                CreationHelper.deleteContainment(((DNodeList) edge.getSourceNode()).getTarget(), ((DNodeList) edge.getTargetNode()).getTarget());
                doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
            }
        }
    }

    private void createContainment(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
        EObject source = (EObject) parameters.get(PARAMETER_SOURCE);
        EObject target = (EObject) parameters.get(PARAMETER_TARGET);
        CreationHelper.createContainment(source, target);
        doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));

    }

    private void createSystem(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
        EObject container = (EObject) parameters.get(PARAMETER_ELEMENT);

        if (container instanceof Poosl) {
            CreationHelper.createSystem((Poosl) container);
            doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
        }

    }

    private void deleteCluster(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
        EObject element = (EObject) parameters.get(PARAMETER_ELEMENT);

        if (element instanceof ClusterClass) {
            CreationHelper.deleteClusterClass((ClusterClass) element);
            doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
        }
    }

    private void deleteData(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
        EObject element = (EObject) parameters.get(PARAMETER_ELEMENT);

        if (element instanceof DataClass) {
            CreationHelper.deleteDataClass((DataClass) element);
            doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
        }
    }

    private void deleteProcess(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
        EObject element = (EObject) parameters.get(PARAMETER_ELEMENT);

        if (element instanceof ProcessClass) {
            CreationHelper.deleteProcessClass((ProcessClass) element);
            doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
        }

    }

    private void createCluster(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
        EObject container = (EObject) parameters.get(PARAMETER_ELEMENT);

        if (container instanceof Poosl) {
            CreationHelper.createClusterClass((Poosl) container);
            doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
        }
    }

    private void createData(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
        EObject container = (EObject) parameters.get(PARAMETER_ELEMENT);

        if (container instanceof Poosl) {
            CreationHelper.createDataClass((Poosl) container);
            doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
        }
    }

    private void createProcess(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
        EObject container = (EObject) parameters.get(PARAMETER_ELEMENT);

        if (container instanceof Poosl) {
            CreationHelper.createProcessClass((Poosl) container);
            doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
        }
    }

    private void createInheritance(Collection<? extends EObject> arg0, Map<String, Object> parameters) {
        EObject source = (EObject) parameters.get(PARAMETER_SOURCE);
        EObject target = (EObject) parameters.get(PARAMETER_TARGET);
        CreationHelper.createInheritance(source, target);
        doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
    }

    private void deleteInheritance(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
        EObject element = (EObject) parameters.get(PARAMETER_ELEMENT);
        CreationHelper.deleteInheritance(element);
        doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
    }

    private void deleteExternPort(Collection<? extends EObject> arg0, Map<String, Object> parameters) {
        EObject eobject = (EObject) parameters.get(PARAMETER_ELEMENT);

        if (eobject instanceof Port) {
            CreationHelper.deletePort((Port) eobject);
            doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
        }
    }

    private void deleteMethod(Collection<? extends EObject> arg0, Map<String, Object> parameters) {
        EObject method = (EObject) parameters.get(PARAMETER_ELEMENT);
        if (method instanceof ProcessMethod) {
            CreationHelper.deleteMethod((ProcessMethod) method);
            doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
        } else if (method instanceof DataMethod) {
            CreationHelper.deleteMethod((DataMethod) method);
            doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
        }
    }

    private void editVariable(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
        if (!eobjects.isEmpty()) {
            EObject target = eobjects.iterator().next();
            if (target instanceof Variable) {
                CreationHelper.editVariable((Variable) target);
                doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
            }
        }
    }

    private void editParameter(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
        if (!eobjects.isEmpty()) {
            EObject target = eobjects.iterator().next();
            if (target instanceof Variable) {
                CreationHelper.editParameter((Variable) target);
                doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
            }
        }
    }

    private void editMethod(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
        if (!eobjects.isEmpty()) {
            EObject target = eobjects.iterator().next();
            if (target instanceof ProcessMethod) {
                ProcessMethod processMethod = (ProcessMethod) target;
                CreationHelper.editMethod(processMethod);
                doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
            } else if (target instanceof DataMethod) {
                DataMethod datamethod = (DataMethod) target;
                CreationHelper.editMethod(datamethod);
                doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
            }
        }
    }

    /**
     * remove variable from the declaration or the whole declaration if it contains only 1 variable
     * 
     * @param arg0
     * @param parameters
     */
    private void deleteParameter(Collection<? extends EObject> arg0, Map<String, Object> parameters) {
        Variable var = (Variable) parameters.get(PARAMETER_ELEMENT);
        CreationHelper.deleteParameter(var);
        doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
    }

    private void deleteInstanceVariable(Collection<? extends EObject> arg0, Map<String, Object> parameters) {
        Variable var = (Variable) parameters.get(PARAMETER_ELEMENT);
        CreationHelper.deleteInstanceVariable(var);
        doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
    }

    private void createMethod(Collection<? extends EObject> arg0, Map<String, Object> parameters) {
        EObject container = (EObject) parameters.get(PARAMETER_ELEMENT);
        if (container instanceof ProcessMethod || container instanceof DataMethod) {
            container = container.eContainer();
        } else if (container instanceof Variable) {
            container = container.eContainer().eContainer();
        }
        CreationHelper.createMethod(container);
        doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
    }

    private void createParameter(Collection<? extends EObject> arg0, Map<String, Object> parameters) {
        EObject container = (EObject) parameters.get(PARAMETER_ELEMENT);
        if (container instanceof ProcessMethod) {
            container = container.eContainer();
        } else if (container instanceof Variable) {
            container = container.eContainer().eContainer();
        }

        if (container instanceof InstantiableClass) {
            InstantiableClass instantiableclass = (InstantiableClass) container;
            CreationHelper.createParameter(instantiableclass);
            doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
        }
    }

    private void createInstanceVariable(Collection<? extends EObject> arg0, Map<String, Object> parameters) {
        EObject container = (EObject) parameters.get(PARAMETER_ELEMENT);
        if (container instanceof ProcessMethod || container instanceof DataMethod) {
            container = container.eContainer();
        } else if (container instanceof Variable) {
            container = container.eContainer().eContainer();
        }
        CreationHelper.createVariable(container);
        doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
    }

    private void handleDoubleClickClassDiagramMember(Collection<? extends EObject> eobjects) {
        for (EObject eObject : eobjects) {
            if (eObject instanceof DDiagramElement) {
                DDiagramElement nodeSpec = (DDiagramElement) eObject;
                openEditor(nodeSpec.getTarget(), ""); //$NON-NLS-1$
            }
        }
    }

    private void deletePortFromInstance(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {

        EObject eobject = (EObject) parameters.get(PARAMETER_ELEMENT);

        if (eobject instanceof InstancePort) {
            InstancePort instancePort = (InstancePort) eobject;
            CreationHelper.deleteInstancePort(instancePort);
            doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
        } else if (eobject instanceof Port) {
            Port port = (Port) eobject;
            CreationHelper.deletePort(port);
            doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
        }

    }

    private void createConnection(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {

        Object sourcelement = parameters.get("sourceview"); //$NON-NLS-1$
        Object source = parameters.get(PARAMETER_SOURCE);
        EObject sourceobject = getModelElement(source, sourcelement);

        Object targetelement = parameters.get("targetview"); //$NON-NLS-1$
        Object target = parameters.get(PARAMETER_TARGET);
        EObject targetobject = getModelElement(target, targetelement);

        CreationHelper.createConnection(sourceobject, targetelement, targetobject);
        doValidate(source, targetelement);

    }

    private void reconnectConnection(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
        Object source = parameters.get(PARAMETER_SOURCE);
        Object targetelement = parameters.get("targetview"); //$NON-NLS-1$
        Object target = parameters.get(PARAMETER_TARGET);
        EObject targetobject = getModelElement(target, targetelement);

        CreationHelper.reconnectConnection(source, targetelement, targetobject);
        doValidate(target, targetelement);
    }

    private EObject getModelElement(Object model, Object diagramelement) {
        if (!(model instanceof Port)) {
            return (model instanceof EObject) ? (EObject) model : null;
        }

        EObject sourceContainer = null;
        if (diagramelement instanceof EObject) {
            EObject containerElement = ((EObject) diagramelement).eContainer();
            if (containerElement instanceof DRepresentationElementImpl) {
                sourceContainer = ((DRepresentationElementImpl) containerElement).getTarget();
            }
        }

        if (sourceContainer instanceof Instance) {
            InstancePort newinstanceport = PooslFactoryImpl.init().createInstancePort();
            PortReference port = PooslFactoryImpl.init().createPortReference();
            String name = ((Port) model).getName();
            port.setPort(name);
            newinstanceport.setInstance((Instance) sourceContainer);
            newinstanceport.setPort(port);
            SessionManager.INSTANCE.getSession(sourceContainer).save(new NullProgressMonitor());
            return newinstanceport;
        } else {
            return (model instanceof EObject) ? (EObject) model : null;
        }
    }

    private void createPort(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
        EObject object = (EObject) parameters.get(PARAMETER_ELEMENT);
        CreationHelper.createNewPort(object);
        doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
    }

    private void deleteport(Collection<? extends EObject> arg0, Map<String, Object> parameters) {
        Port port = (Port) parameters.get(PARAMETER_ELEMENT);
        if (port.eContainer() instanceof ClusterClass) {
            ClusterClass cl = (ClusterClass) port.eContainer();
            cl.getPorts().remove(port);
            doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
        }
    }

    private void deleteInstance(Collection<? extends EObject> arg0, Map<String, Object> parameters) {
        Instance instance = (Instance) parameters.get(PARAMETER_ELEMENT);
        CreationHelper.deleteInstance(instance);
        doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
    }

    private void deleteConnection(Collection<? extends EObject> arg0, Map<String, Object> parameters) {
        if (parameters.get(PARAMETER_VIEW) instanceof DEdge) {
            DEdge connection = (DEdge) parameters.get(PARAMETER_VIEW);

            CreationHelper.deleteChannelConnection(connection);
            doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
        }
    }

    private void createInstance(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
        ClusterClass container = (ClusterClass) parameters.get(PARAMETER_ELEMENT);
        CreationHelper.createNewInstance(container);
        doValidate(parameters.get(PARAMETER_ELEMENT), parameters.get(PARAMETER_VIEW));
    }

    private void openGraphicalEditor(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
        Object diagramObject = parameters.get(PARAMETER_VIEW);
        if (diagramObject instanceof ArrayList<?>) {
            diagramObject = ((ArrayList<?>) diagramObject).get(0);
        }
        String documentation = null;

        if (diagramObject instanceof DDiagramElement) {
            DDiagramElement element = (DDiagramElement) diagramObject;
            documentation = getDocumentation(element);
        }

        if (!eobjects.isEmpty()) {
            EObject target = eobjects.iterator().next();
            if (target instanceof Instance) {
                if (HelperFunctions.isClusterInstance((Instance) target)) {
                    GraphicalEditorHelper.openGraphicalEditor(target, documentation);
                }
            } else if (target instanceof ClusterClass) {
                GraphicalEditorHelper.openGraphicalEditor(target, documentation);
            }
        }
    }

    private void openTextualEditor(Collection<? extends EObject> eobjects, Map<String, Object> parameters, boolean instance) {
        if (!eobjects.isEmpty()) {
            EObject obj = eobjects.iterator().next();
            if (obj instanceof DRepresentationElementImpl) {
                DRepresentationElementImpl repElement = (DRepresentationElementImpl) obj;
                obj = repElement.getTarget();
            }
            TextualEditorHelper.openTextualEditor(obj, true, instance);
        }
    }

    private void openClassDiagram(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
        EObject object = eobjects.iterator().next();
        if (object != null) {
            Poosl poosl = HelperFunctions.getContainingPoosl(object);
            GraphicalEditorHelper.openGraphicalEditor(poosl, ""); //$NON-NLS-1$
        }
    }

    private void handleDoubleClickOpenEditor(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
        for (EObject eObject : eobjects) {
            if (eObject instanceof DDiagramElement) {
                DDiagramElement element = (DDiagramElement) eObject;
                openEditor(element.getTarget(), getDocumentation(element));
            }
        }
    }

    private void handleDoubleClickChannel(Collection<? extends EObject> arg0) {
        for (EObject eObject : arg0) {
            if (eObject instanceof DNode) {
                DNode nodeSpec = (DNode) eObject;
                if (nodeSpec.getTarget() instanceof Channel) {
                    ColorGraphHelper.colorChannel(nodeSpec);
                }
            }
        }
    }

    private void handleDoubleClickEdge(Collection<? extends EObject> arg0) {
        for (EObject eObject : arg0) {
            if (eObject instanceof DEdge) {
                DEdge edgeSpec = (DEdge) eObject;
                ColorGraphHelper.colorChannel(edgeSpec);
            }
        }
    }

    private void doValidate(Object element, Object view) {
        DDiagram diagram = getDiagram(view);
        final Session session = getSession(element, diagram);

        if (session != null) {
            final IEditorPart tempeditor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
            if (tempeditor instanceof DialectEditor) {
                final DialectEditor dialectEditor = (DialectEditor) tempeditor;
                session.addListener(new SessionListener() {
                    @Override
                    public void notify(int changeKind) {
                        if (changeKind == SessionListener.SEMANTIC_CHANGE) {
                            dialectEditor.validateRepresentation();

                            session.removeListener(this);
                        }
                    }
                });
            }
        }
    }

    private Session getSession(Object element, DDiagram diagram) {
        if (diagram != null && element instanceof EObject) {
            Resource resource = ((EObject) element).eResource();
            Session session = SessionManager.INSTANCE.getSession((EObject) element);
            session = (session == null && resource != null) ? SessionManager.INSTANCE.getSession(resource) : session;
            if (session == null) {
                Collection<Session> sessions = SessionManager.INSTANCE.getSessions();
                if (sessions.size() == 1 && sessions.iterator().hasNext()) {
                    session = sessions.iterator().next();
                }
            }
            return session;
        }
        return null;
    }

    private DDiagram getDiagram(Object view) {
        if (view instanceof DDiagramElement) {
            DDiagramElement diagramelement = (DDiagramElement) view;
            return diagramelement.getParentDiagram();
        } else if (view instanceof DDiagram) {
            return (DDiagram) view;
        }
        return null;
    }

    private void openEditor(EObject target, String documentation) {
        if (target instanceof ClusterClass || (target instanceof Instance && HelperFunctions.isClusterInstance((Instance) target))) {
            GraphicalPreferenceManager prefManager = new GraphicalPreferenceManager();
            String pref = prefManager.getEditorPreference();

            if (pref == null) {
                // canceled dialog window
                return;
            }
            if (pref.equals(IPreferenceConstants.PREFERENCE_GRAPHICAL_EDITOR_GRAPHICAL)) {
                GraphicalEditorHelper.openGraphicalEditor(target, documentation);
            } else {
                TextualEditorHelper.openTextualEditor(target, true);
            }

        } else {
            TextualEditorHelper.openTextualEditor(target, true);
        }
    }

    private String getDocumentation(DDiagramElement element) {
        return element.getParentDiagram().getDocumentation();
    }

}
