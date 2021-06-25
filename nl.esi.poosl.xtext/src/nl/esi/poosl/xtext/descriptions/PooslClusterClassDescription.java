package nl.esi.poosl.xtext.descriptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.xtext.resource.IEObjectDescription;

import nl.esi.poosl.Channel;
import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.Instance;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.xtext.helpers.HelperFunctions;
import nl.esi.poosl.xtext.helpers.PooslChannelHelper;
import nl.esi.poosl.xtext.helpers.PooslValidationHelper;

public class PooslClusterClassDescription {
	private static final String STR_CHANNELS = "Channels";
	private static final String STR_INSTANCES = "Instances";

	private PooslClusterClassDescription() {
		throw new IllegalStateException("Utility class");
	}

	// --- Set -------

	public static Map<String, String> createUserData(ClusterClass cClass) {
		Map<String, String> userData = new HashMap<>();
		setUserDataInstances(userData, cClass);
		setUserDataChannels(userData, cClass);
		return userData;
	}

	private static void setUserDataInstances(Map<String, String> userData, ClusterClass cClass) {
		StringBuilder instances = new StringBuilder();
		for (Instance instance : cClass.getInstances()) {
			String iName = instance.getName();
			String iClass = instance.getClassDefinition();
			if (iName != null && iClass != null) {
				instances.append(iName + ":" + iClass + ";");
			}
		}
		userData.put(STR_INSTANCES, instances.toString());
	}

	private static void setUserDataChannels(Map<String, String> userData, ClusterClass cClass) {
		StringBuilder channels = new StringBuilder();
		for (Channel channel : cClass.getChannels()) {
			PooslChannelHelper channelDescr = new PooslChannelHelper(channel);
			channels.append(channelDescr.toString()).append(";");
		}
		userData.put(STR_CHANNELS, channels.toString());
	}

	// --- Get -------

	public static Map<String, String> getInstancesMap(IEObjectDescription descr) {
		if (!checkValidity(descr))
			return Collections.emptyMap();

		Map<String, String> allInstances = new HashMap<>();
		String instancesString = descr.getUserData(STR_INSTANCES);
		String[] instanceDescriptions = instancesString.split(";");
		for (String instanceDescription : instanceDescriptions) {
			if (!instanceDescription.isEmpty()) {
				String[] instClass = instanceDescription.split(":");
				if (instClass.length == 2) {
					allInstances.put(instClass[0], instClass[1]);
				} else {
					Logger.getGlobal().log(Level.WARNING,
							"Could not add description of instance : " + instanceDescription);
				}
			}
		}

		return allInstances;
	}

	public static PooslChannelHelper getChannelDescription(IEObjectDescription descr, String externalPortName) {
		if (!checkValidity(descr))
			return null;

		String clusterName = HelperFunctions.getName(descr);
		String[] channelDescriptions = getSplittedChannels(descr);
		Map<String, String> instances = getInstancesMap(descr);

		for (String stringDescription : channelDescriptions) {
			PooslChannelHelper ch = new PooslChannelHelper(clusterName, stringDescription, instances);
			if (externalPortName.equals(ch.getExternalPortName())) {
				return ch;
			}
		}
		return null;
	}

	public static List<PooslChannelHelper> getChannelDescriptions(IEObjectDescription descr) {
		if (!checkValidity(descr))
			return null;

		String clusterName = HelperFunctions.getName(descr);
		String[] channelDescriptions = getSplittedChannels(descr);
		Map<String, String> instances = getInstancesMap(descr);

		List<PooslChannelHelper> channels = new ArrayList<>();
		for (String stringDescription : channelDescriptions) {
			channels.add(new PooslChannelHelper(clusterName, stringDescription, instances));
		}
		return channels;
	}

	private static String[] getSplittedChannels(IEObjectDescription descr) {
		String channelsString = descr.getUserData(STR_CHANNELS);
		return channelsString.split(";");
	}

	private static boolean checkValidity(IEObjectDescription descr) {
		return PooslValidationHelper.checkValidity(descr, Literals.CLUSTER_CLASS);
	}
}
