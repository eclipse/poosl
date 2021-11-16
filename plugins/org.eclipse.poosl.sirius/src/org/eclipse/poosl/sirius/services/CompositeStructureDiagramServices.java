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
package org.eclipse.poosl.sirius.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.poosl.Channel;
import org.eclipse.poosl.ClusterClass;
import org.eclipse.poosl.DataClass;
import org.eclipse.poosl.DataMethod;
import org.eclipse.poosl.Instance;
import org.eclipse.poosl.InstancePort;
import org.eclipse.poosl.InstantiableClass;
import org.eclipse.poosl.Poosl;
import org.eclipse.poosl.PooslPackage.Literals;
import org.eclipse.poosl.Port;
import org.eclipse.poosl.ProcessClass;
import org.eclipse.poosl.ProcessMethod;
import org.eclipse.poosl.Variable;
import org.eclipse.poosl.sirius.helpers.NameHelper;
import org.eclipse.poosl.xtext.custom.PooslCache;
import org.eclipse.poosl.xtext.helpers.HelperFunctions;
import org.eclipse.poosl.xtext.helpers.PooslReferenceHelper;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.xtext.resource.IEObjectDescription;

/**
 * This class is used by the poosl.odesign file to have more advanced queries on
 * the model that are called by ODesign
 * expressions.
 * <p>
 * According to sirius documentation, this class needs to be stateless and have
 * a constructor without any arguments.
 * There is no guarantee that the same instance will be used when a function is
 * called twice.
 * </p>
 * 
 * @author Koen Staal
 */
public class CompositeStructureDiagramServices {

    public static EObject getODesignDebugging(EObject value) {
        return value;
    }

    /**
     * This function is used by the bordered instanceport node to determine all
     * unique instance ports connected to the
     * provided instance.
     * 
     * @param instance
     *     The instance to check the unique connected instance ports for.
     * @return A list of InstancePorts to be added as a graphical element to the
     *     provided instance.
     */
    private List<InstancePort> getInstancePorts(Instance instance) {
        // Get all channels from the containing clusterclass and create a list
        // with unique instanceports.
        Map<String, InstancePort> allInstancePorts;
        if (instance.eContainer() instanceof ClusterClass) {
            allInstancePorts = getUniqueInstancePorts((ClusterClass) instance.eContainer());
        } else {
            return Collections.emptyList();
        }
        // Add only the instanceports that belong to this instance
        List<InstancePort> instancePorts = new ArrayList<>();
        for (Entry<String, InstancePort> entry : allInstancePorts.entrySet()) {
            if (entry.getValue().getInstance().equals(instance)) {
                instancePorts.add(entry.getValue());
            }
        }
        return instancePorts;
    }

    /**
     * Returns ports the instance.
     * 
     * @param instance
     *     The ports of this instance will be returned
     * @return list of {@link EObject} with all ports of the instance
     */
    public List<EObject> getDeclaredInstancePorts(Instance instance) {
        List<EObject> result = new ArrayList<>();
        List<Port> declaredPorts = getDeclaredPorts(instance);
        result.addAll(getDeclaredPorts(instance));

        for (InstancePort instancePort : getInstancePorts(instance)) {
            String portName = instancePort.getPort().getPort();
            if (!declaredPorts.stream().anyMatch(it -> portName.equals(it.getName()))) {
                result.add(instancePort);
            }
        }
        return result;
    }

    /**
     * Returns the name of the port.
     * 
     * @param object
     *     Is an {@link InstancePort} or a {@link Port}
     * @return the name of the port
     */
    public String getPortName(Port object) {
        return object.getName();
    }

    /**
     * Returns the name of the port.
     * 
     * @param object
     *     Is an {@link InstancePort} or a {@link Port}
     * @return the name of the port
     */
    public String getPortName(InstancePort object) {
        return object.getPort().getPort();
    }

    /**
     * Return is a string describing the instance, used in system and cluster
     * diagram.
     * 
     * @param instance
     *     the instance
     * @return string containing classdefinition and type
     */
    public String getInstanceName(Instance instance) {
        return instance.getName() + " : " + instance.getClassDefinition(); //$NON-NLS-1$
    }

    public boolean isInstanceConnected(Channel parent, AbstractDNode endView) {
        EObject endContainer = ((AbstractDNode) endView.eContainer()).getTarget();

        if (endContainer instanceof ClusterClass // <=> ExternalPort
                || endView.getTarget() instanceof InstancePort // undeclared
        ) {
            return true;
        }
        Port port = (Port) endView.getTarget();
        Instance instance = (Instance) endContainer;

        return parent.getInstancePorts().stream().anyMatch(it -> //
        it.getInstance() == instance //
                && it.getPort().getPort().equals(port.getName()));
    }

    public boolean isInstanceSimpleConnected(
            Channel parent, AbstractDNode sourceView, AbstractDNode targetView) {
        return isInstanceSimpleSource(parent, sourceView.getTarget(),
                ((AbstractDNode) sourceView.eContainer()).getTarget())
                && isInstanceSimpleTarget(parent, targetView.getTarget(),
                        ((AbstractDNode) targetView.eContainer()).getTarget());
    }

    private static boolean isInstanceSimpleSource(
            Channel channel, EObject port, EObject portParent) {
        if (channel.getExternalPort() != null) {
            return channel.getExternalPort() == port && channel.eContainer() == portParent;
        }
        InstancePort expected = channel.getInstancePorts().get(0);
        if (port instanceof InstancePort) {
            return expected == port;
        }
        return expected.getInstance() == portParent
                && expected.getPort().getPort().equals(((Port) port).getName());
    }

    private static boolean isInstanceSimpleTarget(
            Channel channel, EObject port, EObject portParent) {
        InstancePort expected = channel.getInstancePorts()
                .get(channel.getExternalPort() != null ? 0 : 1);
        if (port instanceof InstancePort) {
            return expected == port;
        }
        return expected.getInstance() == portParent
                && expected.getPort().getPort().equals(((Port) port).getName());
    }

    public List<Port> getDeclaredPorts(Instance instance) {
        List<Port> result = new ArrayList<>();

        Resource resource = instance.eResource();
        PooslCache.clear(resource);
        Map<String, IEObjectDescription> ports = PooslCache.get(resource)
                .getInstantiableClassPorts(instance.getClassDefinition());
        for (Entry<String, IEObjectDescription> entry : ports.entrySet()) {
            EObject obj = entry.getValue().getEObjectOrProxy();
            result.add((Port) (obj.eIsProxy() ? EcoreUtil.resolve(obj, instance) : obj));
        }

        return result;
    }

    /**
     * Determines the number of connections of the given channel, used to decide
     * if a channel should be shown.
     * 
     * @param channel
     *     the Channel
     * @return number of connections
     */
    public static int getNumberOfChannelEnds(Channel channel) {
        return getUniqueInstancePorts(new HashMap<>(), channel).size() //
                + (channel.getExternalPort() != null ? 1 : 0);
    }

    /**
     * Evaluates if a channel is simple (2 ends) or a cluster.
     * 
     * @param channel
     *     to evaluate
     * @return true if only 2 ends
     */
    public static boolean isSimpleChannel(Channel channel) {
        int extSize = channel.getExternalPort() != null ? 1 : 0;
        return channel.getInstancePorts().size() + extSize == 2;
    }

    /**
     * Gets the end of a channel assuming it have 2 ends exactly.
     * 
     * @param parent
     *     channel
     * @param target
     *     end
     * @return Port or InstancePort
     */
    public EObject getSimpleChannelEnd(Channel parent, boolean target) {
        boolean usingExternal = parent.getExternalPort() != null;
        int index;
        if (!target) { // source
            if (usingExternal) {
                return parent.getExternalPort();
            }
            index = 0;
        } else {
            index = usingExternal ? 0 : 1;
        }
        InstancePort iPort = parent.getInstancePorts().get(index);

        return getApplicablePort(iPort, getDeclaredPorts(iPort.getInstance()));
    }

    private EObject getApplicablePort(InstancePort iPort, List<Port> declaredPorts) {
        Port declaredPort = findDeclaration(iPort, declaredPorts);
        return declaredPort != null ? declaredPort : iPort;
    }

    private Port findDeclaration(InstancePort iPort, List<Port> declaredPorts) {
        String portName = iPort.getPort().getPort();
        return declaredPorts.stream() //
                .filter(it -> portName.equals(it.getName())) //
                .findFirst().orElse(null);
    }

    /**
     * Gets the end of a channel assuming it have 2 ends exactly.
     * 
     * @param parent
     *     channel
     * @return Port or InstancePort list
     */
    public List<? extends EObject> getChannelEnds(Channel parent) {
        List<EObject> result = new ArrayList<EObject>(parent.getInstancePorts().size() + 1);
        if (parent.getExternalPort() != null) {
            result.add(parent.getExternalPort());
        }

        Map<Instance, List<Port>> declaredPorts = new HashMap<>();
        for (InstancePort iPort : parent.getInstancePorts()) {
            result.add(getApplicablePort(iPort, // Port or InstancePort
                    declaredPorts.computeIfAbsent(iPort.getInstance(), //
                            instance -> getDeclaredPorts(iPort.getInstance()))));
        }
        return result;
    }

    /**
     * This function is used by the ChannelConnection to get a list of the
     * unique connected instancePorts of the
     * provided channel.
     * 
     * @param channel
     *     The channel to check the connected instanceports for.
     * @return A list of InstancePorts that is connected to the provided
     *     channel.
     */
    public List<InstancePort> getInstancePorts(Channel channel) {
        // Get all channels from the containing clusterclass and create a list
        // with unique instanceports.
        Map<String, InstancePort> allInstancePorts = getUniqueInstancePorts(
                (ClusterClass) channel.eContainer());

        // Add the instance ports for this channel to the list based on the new
        // unique instanceport list.
        List<InstancePort> instancePorts = new ArrayList<>();
        for (InstancePort instancePort : channel.getInstancePorts()) {
            if (instancePort.getPort() != null && instancePort.getInstance() != null) {
                instancePorts.add(allInstancePorts.get(instancePort.getInstance().getName() + "|" //$NON-NLS-1$
                        + instancePort.getPort().getPort()));
            }
        }
        return instancePorts;
    }

    // Used in fake mapping
    // public Object getCreationChannel(EObject object) {
    // return null;
    // }

    /**
     * Private helper function to get all uniqueInstancePorts from the provided
     * ClusterClass.
     * 
     * @param cluster
     *     The ClusterClass from which to get all the unique instanceports.
     * @return A hashmap containing the unique instanceports with keys of their
     *     instanceName+portName.
     */
    private Map<String, InstancePort> getUniqueInstancePorts(ClusterClass cluster) {
        Map<String, InstancePort> allUniqueInstancePorts = new HashMap<>();
        // Get all channels from the containing clusterclass and create a list
        // with unique instanceports.
        for (Channel ch : cluster.getChannels()) {
            getUniqueInstancePorts(allUniqueInstancePorts, ch);
        }
        return allUniqueInstancePorts;
    }

    /**
     * Puts all the instanceports in the given map.
     * 
     * @param accu
     *     accumulator
     * @param ch
     *     channel to get from
     * @return accumulator
     */
    private static Map<String, InstancePort> getUniqueInstancePorts(
            Map<String, InstancePort> accu, Channel ch) {
        for (InstancePort instancePort : ch.getInstancePorts()) {
            if (instancePort != null && instancePort.getPort() != null //
                    && instancePort.getInstance() != null) {
                accu.put(instancePort.getInstance().getName() //
                        + "|" + instancePort.getPort().getPort(), instancePort); // $NON-NLS-1$
            }
        }
        return accu;
    }

    /**
     * Returns true if the object should have the option to change the color in
     * the menu.
     * 
     * @param object
     *     to evaluate
     * @return true if colored
     */
    public boolean showMenuChangeColor(EObject object) {
        return object instanceof Port || object instanceof InstancePort
                || object instanceof Channel;
    }

    /**
     * Returns true if the object should have the option to go to the textual
     * editor.
     * 
     * @param object
     *     to evaluate
     * @return true if editable by text
     */
    public boolean showMenuOpenTextualEditor(EObject object) {
        return !AbstractServices.isBundleResource(object) //
                && (object instanceof Instance //
                        || object instanceof ClusterClass //
                        || object instanceof ProcessClass //
                        || object instanceof DataClass //
                        || object instanceof Variable //
                        || object instanceof ProcessMethod //
                        || object instanceof DataMethod //
                        || object instanceof Poosl);
    }

    /**
     * Returns true if the object should have the option to go to the textual
     * editor of the instance.
     * 
     * @param object
     *     to evaluate
     * @return true if have textual editor
     */
    public boolean showMenuOpenTextualEditorInstance(EObject object) {
        return object instanceof Instance;
    }

    /**
     * Returns true if the object should have the option to go to the graphical
     * editor.
     * 
     * @param object
     *     to evaluate
     * @return true if have graphical editor
     */
    public boolean showMenuOpenGraphicalEditor(EObject object) {
        return showMenuInstanceOpenStructureDiagram(object) //
                || object instanceof ClusterClass;
    }

    /**
     * Returns true if the object should have the option to go to the structure
     * diagram from the structure diagram.
     * 
     * @param object
     *     to evaluate
     * @return true if with Structure diagram
     */

    public boolean showMenuInstanceOpenStructureDiagram(EObject object) {
        return object instanceof Instance && HelperFunctions.isClusterInstance((Instance) object);
    }

    /**
     * return true if the object has the option to have instances.
     * 
     * @param object
     *     to evaluate
     * @return true when allowed
     */
    public boolean canCreateInstance(EObject object) {
        return object instanceof ClusterClass;
    }

    /**
     * return true if the object has the option to have ports.
     * 
     * @param object
     *     to evaluate
     * @return true when allowed
     */
    public boolean canCreatePort(EObject object) {
        return object instanceof InstantiableClass || object instanceof Instance;
    }

    /**
     * Evaluates when creating channels, if a line can be drawn to the object to
     * represent a channel it returns true.
     * 
     * @param object
     *     to evaluate
     * @return true when connectable
     */
    public boolean isClusterConnectableEnd(EObject object) {
        return object instanceof Port //
                || object instanceof InstancePort //
                || object instanceof Channel;
    }

    public static String getUniqueInstanceName(ClusterClass container, String original) {
        return NameHelper.getUniqueInstanceName(AbstractServices.COPYOF + original, container);
    }

    public boolean isClusterClass(Instance instance) {
        IEObjectDescription classDef = PooslReferenceHelper
                .getInstantiableClassDescription(instance);
        return classDef.getEClass().equals(Literals.CLUSTER_CLASS);
    }

    public EObject getInstanceType(Instance instance) {
        IEObjectDescription classDef = PooslReferenceHelper
                .getInstantiableClassDescription(instance);
        return classDef != null ? classDef.getEObjectOrProxy() : null;
    }

    /**
     * Clears the cache of provided element.
     * 
     * @param it
     *     element to clean
     * @return it
     */
    public EObject getCleanedCacheSelf(EObject it) {
        PooslCache.clear(it.eResource());
        return it;
    }

    /**
     * Returns name for diagram main container.
     * 
     * @param cClass
     *     to get from
     * @return name
     */
    public String getDiagramName(ClusterClass cClass) {
        return !isSystemDiagram(cClass) ? cClass.getName() : "System";
    }

    /**
     * Evaluates a is class is System.
     * 
     * @param cClass
     *     to evaluate
     * @return true if system
     */
    public boolean isSystemDiagram(ClusterClass cClass) {
        return cClass.getName() == null;
    }
}
