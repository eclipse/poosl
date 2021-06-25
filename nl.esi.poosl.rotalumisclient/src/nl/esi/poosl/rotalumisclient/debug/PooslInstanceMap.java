package nl.esi.poosl.rotalumisclient.debug;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import nl.esi.poosl.generatedxmlclasses.TInspectCluster;
import nl.esi.poosl.generatedxmlclasses.TInspectInstance;
import nl.esi.poosl.generatedxmlclasses.TInspectModel;
import nl.esi.poosl.generatedxmlclasses.TInspectPort;
import nl.esi.poosl.generatedxmlclasses.TInspectType;
import nl.esi.poosl.generatedxmlclasses.TInstanceType;

public class PooslInstanceMap {
	private static final String INSTANCE_PORT_SEPERATOR = ".";

	private final Map<String, TInstanceType> instances = new LinkedHashMap<>();
	private final Map<String, TInstanceType> processInstances = new LinkedHashMap<>();
	private final Map<String, String> instancePortMap = new HashMap<>();

	public void setModel(TInspectModel model) {
		for (TInspectInstance tInspectInstance : model.getInstance()) {
			computeInstancesAndPorts(tInspectInstance, Collections.<String, String>emptyMap());
		}
		setProcessInstances();
	}

	public Map<String, TInstanceType> getInstances() {
		return instances;
	}

	public Map<String, TInstanceType> getProcessInstances() {
		return processInstances;
	}

	public Map<String, String> getInstancePortMap() {
		return instancePortMap;
	}

	private void computeInstancesAndPorts(TInspectInstance instance, Map<String, String> channel2External) {
		instances.put(instance.getProcessPath(), instance.getType());

		String pPath = instance.getProcessPath() + INSTANCE_PORT_SEPERATOR;
		if (instance.getType() == TInstanceType.CLUSTER) {
			TInspectCluster cluster = instance.getCluster();
			Map<String, String> cChannel2External = new HashMap<>();
			for (TInspectPort iPort : instance.getPort()) {
				storePortToExt(iPort, pPath, channel2External);
				cChannel2External.put(iPort.getDownChannel().getName(), pPath + iPort.getName());
			}
			for (TInspectInstance tInspectInstance : cluster.getInstance()) {
				computeInstancesAndPorts(tInspectInstance, cChannel2External);
			}
		} else {
			// process class
			for (TInspectPort iPort : instance.getPort()) {
				storePortToExt(iPort, pPath, channel2External);
			}
		}
	}

	private void storePortToExt(TInspectPort iPort, String pPath, Map<String, String> channel2External) {
		if (iPort.getUpChannel() != null) {
			String portPath = pPath + iPort.getName();
			String extPort = channel2External.get(iPort.getUpChannel().getName());
			instancePortMap.put(portPath, extPort);
		}
	}

	/**
	 * Creates an array of threads based on the instances of the provided model.
	 * 
	 * @param target The requesting PooslDebugTarget that wants to create threads.
	 * @param model  The Poosl model to create threads from
	 * @return A new array with PooslThreads
	 */
	public PooslThread[] createThreads(PooslDebugTarget target) {
		Map<String, TInstanceType> pInstances = getProcessInstances();
		PooslThread[] threads = new PooslThread[pInstances.size()];
		int i = 0;
		for (Entry<String, TInstanceType> instance : pInstances.entrySet()) {
			PooslThread thread = new PooslThread(target, instance.getKey(), TInspectType.PROCESS);
			threads[i] = thread;
			i++;
		}
		return threads;
	}

	public void setProcessInstances() {
		for (Entry<String, TInstanceType> entry : instances.entrySet()) {
			if (entry.getValue().equals(TInstanceType.PROCESS)) {
				processInstances.put(entry.getKey(), entry.getValue());
			}
		}
	}
}
