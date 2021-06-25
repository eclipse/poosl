package nl.esi.poosl.sirius.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.IEObjectDescription;

import nl.esi.poosl.Channel;
import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.Instance;
import nl.esi.poosl.InstancePort;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.Port;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.Variable;
import nl.esi.poosl.impl.ChannelImpl;
import nl.esi.poosl.sirius.helpers.NameHelper;
import nl.esi.poosl.xtext.custom.PooslCache;
import nl.esi.poosl.xtext.helpers.HelperFunctions;
import nl.esi.poosl.xtext.helpers.PooslReferenceHelper;

/**
 * This class is used by the poosl.odesign file to have more advanced queries on
 * the model that are called by Acceleo3 expressions.
 * 
 * According to sirius documentation this class needs to be stateless and have a
 * constructor without any arguments. There is no guarantee that the same
 * instance will be used when a function is called twice.
 * 
 * @author Koen Staal
 */
public class CompositeStructureDiagramServices extends AbstractServices {

	public CompositeStructureDiagramServices() {
	}

	/**
	 * This function is used by the bordered instanceport node to determine all
	 * unique instance ports connected to the provided instance.
	 * 
	 * @param instance The instance to check the unique connected instanceports for.
	 * @return A list of InstancePorts to be added as a graphical element to the
	 *         provided instance.
	 */
	private List<InstancePort> getInstancePorts(Instance instance) {
		// Get all channels from the containing clusterclass and create a list
		// with unique instanceports.
		Map<String, InstancePort> allInstancePorts;
		if (instance.eContainer() instanceof ClusterClass) {
			allInstancePorts = getUniqueInstancePorts((ClusterClass) instance.eContainer());
		} else {
			return null;
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
	 * Will return a list of {@link EObject} ports and instanceports of the instance
	 * 
	 * @param instance The ports of this instance will be returned
	 * @return list of {@link EObject} with all ports of the instance
	 */
	public List<EObject> getClassDefinitionPorts(Instance instance) {

		List<EObject> allports = new ArrayList<>();
		List<InstancePort> instancePorts = getInstancePorts(instance);

		for (InstancePort instancePort : instancePorts) {
			if (instancePort.eContainer() != null) {
				allports.add(instancePort);
			}
		}
		for (Port port : getUnconnectedPorts(instance, instancePorts)) {
			allports.add(port);
		}
		return allports;
	}

	/**
	 * Returns the name of the port
	 * 
	 * @param object Is an {@link InstancePort} or a {@link Port}
	 * @return the name of the port
	 */
	public String getPortName(EObject object) {
		if (object instanceof InstancePort) {
			return ((InstancePort) object).getPort().getPort();
		} else {
			return ((Port) object).getName();
		}
	}

	/**
	 * Return is a string describing the instance, used in system and cluster
	 * diagram
	 * 
	 * @param instance the instance
	 * @return string containing classdefinition and type
	 */
	public String getInstanceName(Instance instance) {
		return instance.getName() + " : " + instance.getClassDefinition();
	}

	/**
	 * This function is used by the bordered port node to determine all unconnected
	 * ports to the provided instance.
	 * 
	 * @param instance The instance to check the unconnected ports for.
	 * @return A list of Ports to be added as a graphical element to the provided
	 *         instance.
	 */
	private List<Port> getUnconnectedPorts(Instance instance, List<InstancePort> instancePorts) {
		List<Port> unconnectedPorts = new ArrayList<>();
		// Check if every port of the classdefinition is connected. If not then
		// add it to the list
		Set<String> connected = new HashSet<>();
		for (InstancePort iPort : instancePorts) {
			connected.add(iPort.getPort().getPort());
		}

		Resource resource = instance.eResource();
		PooslCache.clear(resource);
		Map<String, IEObjectDescription> ports = PooslCache.get(resource)
				.getInstantiableClassPorts(instance.getClassDefinition());
		for (Entry<String, IEObjectDescription> entry : ports.entrySet()) {
			if (!connected.contains(entry.getKey())) {
				EObject obj = entry.getValue().getEObjectOrProxy();
				if (obj.eIsProxy())
					obj = EcoreUtil.resolve(obj, instance);
				unconnectedPorts.add((Port) obj);
			}
		}

		return unconnectedPorts;
	}

	/**
	 * get Channels that have dont have 2 connections from a cluster used by
	 * "ChannelCluster" and "Channelsystem"
	 * 
	 * @param archClass The ArchitecturalClass
	 * @return Lists of channels
	 */
	public List<Channel> getChannels(ClusterClass cluster) {
		List<Channel> channels = new ArrayList<>();
		for (Channel channel : cluster.getChannels()) {
			if (getNumberOfChannelConnections(channel) != 2) {
				channels.add(channel);
			}
		}
		return channels;
	}

	/**
	 * Determines the number of connections of the given channel, used to decide if
	 * a channel should be shown
	 * 
	 * @param channel the Channel
	 * @return number of connections
	 */
	public static int getNumberOfChannelConnections(Channel channel) {
		if (channel.getExternalPort() != null) {
			return getNumberOfInstanceports(channel) + 1;
		} else {
			return getNumberOfInstanceports(channel);
		}
	}

	/**
	 * Get number of unique instance ports
	 * 
	 * @param ch
	 * @return Number of unique instance points
	 */
	private static int getNumberOfInstanceports(Channel ch) {
		Map<String, InstancePort> uniques = new HashMap<>();
		getUniqueInstancePorts(uniques, ch);
		return uniques.size();
	}

	/**
	 * Determines if a straight line from the given port to another port needs to be
	 * drawn
	 * 
	 * @param p (external)Port or instanceport
	 * @return
	 */
	public EObject getSingleConnectedPort(EObject p) {
		ChannelImpl channel = (ChannelImpl) p.eContainer();
		if (channel.getExternalPort() != null && channel.getInstancePorts().size() == 1) {
			return channel.getExternalPort();
		} else if (channel.getExternalPort() == null && channel.getInstancePorts().size() == 2) {
			if (channel.getInstancePorts().get(0).equals(p)) {
				return channel.getInstancePorts().get(1);
			}
		} else {
			return null;
		}
		return null;
	}

	public EObject getSingleConnectedPort(Port p) {
		return null;
	}

	/**
	 * This function is used by the ChannelConnection to get a list of the unique
	 * connected instancePorts of the provided channel.
	 * 
	 * @param channel The channel to check the connected instanceports for.
	 * @return A list of InstancePorts that is connected to the provided channel.
	 */
	public List<InstancePort> getInstancePorts(Channel channel) {
		// Get all channels from the containing clusterclass and create a list
		// with unique instanceports.
		Map<String, InstancePort> allInstancePorts = getUniqueInstancePorts((ClusterClass) channel.eContainer());

		// Add the instance ports for this channel to the list based on the new
		// unique instanceport list.
		List<InstancePort> instancePorts = new ArrayList<>();
		for (InstancePort instancePort : channel.getInstancePorts()) {
			if (instancePort.getPort() != null && instancePort.getInstance() != null) {
				instancePorts.add(allInstancePorts
						.get(instancePort.getInstance().getName() + "|" + instancePort.getPort().getPort()));
			}
		}
		return instancePorts;
	}

	public Object getCreationChannel(EObject object) {
		return null;
	}

	/**
	 * Private helper function to get all uniqueInstancePorts from the provided
	 * ClusterClass.
	 * 
	 * @param cc The ClusterClass from which to get all the unique instanceports.
	 * @return A hashmap containing the unique instanceports with keys of their
	 *         instanceName+portName.
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
	 * puts all the instanceports in the given map
	 * 
	 * @param allUniqueInstancePorts
	 * @param ch
	 */
	private static void getUniqueInstancePorts(Map<String, InstancePort> allUniqueInstancePorts, Channel ch) {
		for (InstancePort instancePort : ch.getInstancePorts()) {
			if (instancePort != null && instancePort.getPort() != null && instancePort.getInstance() != null) {
				allUniqueInstancePorts.put(
						instancePort.getInstance().getName() + "|" + instancePort.getPort().getPort(), instancePort);
			}
		}
	}

	/**
	 * Returns true if the object should have the option to change the color in the
	 * menu
	 * 
	 * @param object
	 * @return
	 */
	public boolean showMenuChangeColor(EObject object) {
		return (object instanceof Port || object instanceof InstancePort || object instanceof Channel);
	}

	/**
	 * Returns true if the object should have the option to go to the textual editor
	 * 
	 * @param object
	 * @return
	 */
	public boolean showMenuOpenTextualEditor(EObject object) {
		return (!isBundleResource(object)
				&& (object instanceof Instance || object instanceof ClusterClass || object instanceof ProcessClass
						|| object instanceof DataClass || object instanceof Variable || object instanceof ProcessMethod
						|| object instanceof DataMethod || object instanceof nl.esi.poosl.Poosl));
	}

	/**
	 * Returns true if the object should have the option to go to the textual editor
	 * of the instance
	 * 
	 * @param object
	 * @return
	 */
	public boolean showMenuOpenTextualEditorInstance(EObject object) {
		return object instanceof Instance;
	}

	/**
	 * Returns true if the object should have the option to go to the graphical
	 * editor
	 * 
	 * @param object
	 * @return
	 */
	public boolean showMenuOpenGraphicalEditor(EObject object) {
		if (object instanceof Instance) {
			return HelperFunctions.isClusterInstance((Instance) object);
		} else if (object instanceof ClusterClass) {
			return true;
		}
		return false;
	}

	/**
	 * Returns true if the object should have the option to go to the structure
	 * diagram from the structure diagram
	 * 
	 * @param object
	 * @return
	 */

	public boolean showMenuInstanceOpenStructureDiagram(EObject object) {
		if (object instanceof Instance) {
			return HelperFunctions.isClusterInstance((Instance) object);
		}
		return false;
	}

	/**
	 * return true if the object has the option to have instances
	 * 
	 * @param object
	 * @return
	 */
	public boolean canCreateInstance(EObject object) {
		return (object instanceof ClusterClass);
	}

	/**
	 * return true if the object has the option to have ports
	 * 
	 * @param object
	 * @return
	 */
	public boolean canCreatePort(EObject object) {
		return (object instanceof ClusterClass || object instanceof Instance);
	}

	/**
	 * this method is used to create channels, if a line can be drawn to the object
	 * to represent a channel it returns true
	 * 
	 * @param object
	 * @return
	 */
	public boolean isPort(EObject object) {
		if (object instanceof Port) {
			return true;
		} else {
			if (object instanceof InstancePort) {
				return true;
			} else {
				return (object instanceof Channel);
			}
		}
	}

	public static String getUniqueInstanceName(ClusterClass container, String original) {
		return NameHelper.getUniqueInstanceName(COPYOF + original, container);
	}

	public boolean isClusterClass(Instance instance) {
		IEObjectDescription classDef = PooslReferenceHelper.getInstantiableClassDescription(instance);
		return classDef.getEClass().equals(Literals.CLUSTER_CLASS);
	}

	public EObject getSystem(EObject system) {
		PooslCache.clear(system.eResource());
		return system;
	}

	public EObject getCluster(EObject cluster) {
		PooslCache.clear(cluster.eResource());
		return cluster;
	}

	public String getDiagramName(ClusterClass cClass) {
		if (cClass.getName() != null) {
			return cClass.getName();
		} else {
			return "System";
		}
	}

	public boolean isClusterDiagram(ClusterClass cClass) {
		return cClass.getName() != null;
	}

	public boolean isSystemDiagram(ClusterClass cClass) {
		return cClass.getName() == null;
	}
}