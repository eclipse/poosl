package nl.esi.poosl.xtext.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nl.esi.poosl.Channel;
import nl.esi.poosl.InstancePort;

public class PooslChannelHelper {
	private String externalPortName;
	private List<PooslInstancePortHelper> instancePorts = new ArrayList<>();

	public PooslChannelHelper(String aClassName, String stringDescription, Map<String, String> instances) {
		String[] ports = stringDescription.split(",");
		if (ports != null && ports.length != 0) {
			for (int i = 0; i < ports.length; i++) {
				if (!ports[i].contains(":")) {
					externalPortName = ports[i];
				} else {
					instancePorts.add(new PooslInstancePortHelper(aClassName, ports[i], instances));
				}
			}
		}
	}

	public PooslChannelHelper(Channel channel) {
		externalPortName = (channel.getExternalPort() != null) ? channel.getExternalPort().getName() : "";
		for (InstancePort iPort : channel.getInstancePorts()) {
			if (iPort.getInstance() != null && iPort.getPort() != null) {
				instancePorts.add(new PooslInstancePortHelper(iPort));
			}
		}
	}

	public String getExternalPortName() {
		return externalPortName;
	}

	public List<PooslInstancePortHelper> getInstancePorts() {
		return instancePorts;
	}

	public String toString() {
		StringBuilder channelDescriptions = new StringBuilder();
		if (getExternalPortName() != null && !getExternalPortName().isEmpty()) {
			channelDescriptions.append(getExternalPortName()).append(",");
		}
		for (PooslInstancePortHelper ip : getInstancePorts()) {
			channelDescriptions.append(ip.toString()).append(",");
		}
		return channelDescriptions.toString();
	}
}
