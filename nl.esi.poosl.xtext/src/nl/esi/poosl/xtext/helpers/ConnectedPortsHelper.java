package nl.esi.poosl.xtext.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.IEObjectDescription;

import nl.esi.poosl.Channel;
import nl.esi.poosl.Instance;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.xtext.descriptions.PooslClusterClassDescription;

public class ConnectedPortsHelper {

	private ConnectedPortsHelper() {
		throw new IllegalStateException("Utility class");
	}

	public static List<InstanceAndPort> getConnectedProcessPorts(Channel channel) {
		List<InstanceAndPort> connectedProcessPorts = new ArrayList<>();
		Resource resource = channel.eResource();
		List<InstanceAndPort> newAncestorChain = Collections.<InstanceAndPort>emptyList();
		PooslChannelHelper channelHelper = new PooslChannelHelper(channel);
		Set<PooslInstanceHelper> history = new HashSet<>();
		getConnectedProcessPorts(connectedProcessPorts, resource, newAncestorChain, channelHelper, history);
		return connectedProcessPorts;
	}

	public static List<InstanceAndPort> getConnectedProcessPorts(Instance instance, String portName) {
		List<InstanceAndPort> connectedProcessPorts = new ArrayList<>();
		IEObjectDescription iClass = PooslReferenceHelper.getInstantiableClassDescription(instance);
		if (iClass != null) {
			String className = HelperFunctions.getName(iClass);
			List<InstanceAndPort> ancestorChain = Collections.<InstanceAndPort>emptyList();
			InstanceAndPort node = new InstanceAndPort(ancestorChain, instance.getName(), className, portName);
			if (iClass.getEClass() == Literals.PROCESS_CLASS) {
				connectedProcessPorts.add(node);
			} else if (iClass.getEClass() == Literals.CLUSTER_CLASS) {
				Resource resource = instance.eResource();
				List<InstanceAndPort> newAncestorChain = Collections.<InstanceAndPort>singletonList(node);
				PooslChannelHelper channelHelper = PooslClusterClassDescription.getChannelDescription(iClass, portName);
				Set<PooslInstanceHelper> history = new HashSet<>();
				if (channelHelper != null) {
					getConnectedProcessPorts(connectedProcessPorts, resource, newAncestorChain, channelHelper, history);
				}
			}
		}
		return connectedProcessPorts;
	}

	/**
	 * Adds all process ports connected to the channel to the connectedPorts. If an
	 * instance of the port is a Cluster it will add the instance ports from the
	 * channel in that cluster, if an instance of the ports in this is a cluster
	 * these will also be added etc.
	 * 
	 * @param connectedProcessPorts {@link List} to which all process ports are
	 *                              added as {@link InstanceAndPort}
	 * @param channelHelper         the {@link PooslChannelHelper} from which the
	 *                              instance port will be retrieved
	 * @param history               to avoid cyclic channels
	 *                              {@link PooslInstanceHelper} will get added to
	 *                              history {@link Set}
	 */
	private static void getConnectedProcessPorts(List<InstanceAndPort> connectedProcessPorts, Resource resource,
			List<InstanceAndPort> ancestorChain, PooslChannelHelper channelHelper, Set<PooslInstanceHelper> history) {
		for (PooslInstancePortHelper instancePort : channelHelper.getInstancePorts()) {
			String portName = instancePort.getPortName();
			String iClassName = instancePort.getInstantiableClassName();
			PooslInstanceHelper instanceHelper = instancePort.getInstance();
			IEObjectDescription iClass = HelperFunctions.getInstantiableClass(resource, iClassName);
			if (instanceHelper != null && iClass != null) {
				String instanceName = instanceHelper.getInstanceName();
				InstanceAndPort node = new InstanceAndPort(ancestorChain, instanceName, iClassName, portName);
				if (iClass.getEClass().equals(Literals.PROCESS_CLASS)) {
					connectedProcessPorts.add(node);
				} else if (iClass.getEClass().equals(Literals.CLUSTER_CLASS)) {
					PooslChannelHelper extChannelHelper = PooslClusterClassDescription.getChannelDescription(iClass,
							portName);
					if (extChannelHelper != null) {
						if (!history.add(instanceHelper)) {
							return;
						}

						List<InstanceAndPort> newAncestorChain = new ArrayList<>();
						newAncestorChain.addAll(ancestorChain);
						newAncestorChain.add(node);

						getConnectedProcessPorts(connectedProcessPorts, resource, newAncestorChain, extChannelHelper,
								history);
						history.remove(instanceHelper);
					}
				}
			}
		}
	}

	public static class InstanceAndPort {
		private final List<InstanceAndPort> ancestorChain;
		private final String localInstanceName;
		private final String instantiableClassName;
		private final String portName;

		public InstanceAndPort(List<InstanceAndPort> ancestorChain, String localInstanceName,
				String instantiableClassName, String portName) {
			this.ancestorChain = ancestorChain;
			this.localInstanceName = localInstanceName;
			this.instantiableClassName = instantiableClassName;
			this.portName = portName;
		}

		public String getInstantiableClassName() {
			return instantiableClassName;
		}

		public String getPortName() {
			return portName;
		}

		public String toString() {
			StringBuilder builder = new StringBuilder();
			for (InstanceAndPort ancestor : ancestorChain) {
				builder.append(ancestor.localInstanceName + "/");
			}
			builder.append(localInstanceName + "[" + instantiableClassName + "]" + "." + portName);
			return builder.toString();
		}

		public boolean canCommunicate(InstanceAndPort other) {
			List<InstanceAndPort> chain1 = new ArrayList<>();
			chain1.addAll(this.ancestorChain);
			chain1.add(this);

			List<InstanceAndPort> chain2 = new ArrayList<>();
			chain2.addAll(other.ancestorChain);
			chain2.add(other);

			int commonSize = Math.min(chain1.size(), chain2.size());

			// Communication is only possible between two different instances of
			// a single system or cluster class
			for (int i = 0; i < commonSize; i++) {
				InstanceAndPort element1 = chain1.get(i);
				InstanceAndPort element2 = chain2.get(i);
				if (!(element1.localInstanceName.equals(element2.localInstanceName)
						&& element1.portName.equals(element2.portName))) {
					return !element1.localInstanceName.equals(element2.localInstanceName);
				}
			}
			return false;
		}
	}
}
