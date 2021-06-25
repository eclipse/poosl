package nl.esi.poosl.xtext.helpers;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.Instance;
import nl.esi.poosl.InstancePort;
import nl.esi.poosl.xtext.GlobalConstants;

public class PooslInstancePortHelper {
	private PooslInstanceHelper instanceHelper;
	private String port;
	private String instantiableClass;

	public PooslInstancePortHelper(InstancePort iPort) {
		ClusterClass arch = (ClusterClass) iPort.eContainer().eContainer();
		String aClassName = (arch.getName() != null) ? arch.getName() : GlobalConstants.POOSL_SYSTEM;
		String instanceName = (iPort.getInstance() != null) ? iPort.getInstance().getName() : "";

		instanceHelper = new PooslInstanceHelper(aClassName, instanceName);
		port = (iPort.getPort() != null) ? iPort.getPort().getPort() : "";
		instantiableClass = getClassDefinition(instanceName, arch);
	}

	public PooslInstancePortHelper(String aClassName, String description, Map<String, String> instances) {
		String[] portDescr = description.split(":");
		if (portDescr.length == 2) {
			instanceHelper = new PooslInstanceHelper(aClassName, portDescr[0]);
			port = portDescr[1];
			instantiableClass = instances.get(portDescr[0]);
		} else {
			Logger.getGlobal().log(Level.WARNING, this.getClass().getName() + description);
		}
	}

	private String getClassDefinition(String instanceName, ClusterClass arch) {
		for (Instance instance : arch.getInstances()) {
			if (instance.getName().equals(instanceName)) {
				return instance.getClassDefinition();
			}
		}
		return "";
	}

	public PooslInstanceHelper getInstance() {
		return instanceHelper;
	}

	public String getPortName() {
		return port;
	}

	public String getInstantiableClassName() {
		return instantiableClass;
	}

	@Override
	public String toString() {
		return getInstance().getInstanceName() + ":" + getPortName();
	}
}
