package nl.esi.poosl.sirius.debug;

import java.util.HashMap;
import java.util.Map;

public class PathCalculator {
	final String launchID;
	private final Map<String, PathCounter> pathCounters = new HashMap<>();

	public PathCalculator(String launchID) {
		this.launchID = launchID;
	}

	public void addMessage(PooslDrawMessage drawMessage) {
		for (MessagePath paths : drawMessage.getMessagePaths().values()) {
			getPathCounter(getPathId(paths.getOwner(), paths.getSender(), paths.getSenderPort())).addOutgoing();
			getPathCounter(getPathId(paths.getOwner(), paths.getReceiver(), paths.getReceiverPort())).addIncoming();
		}
	}

	public PathCounter getPathCounter(String pathId) {
		PathCounter pathCounter = pathCounters.get(pathId);
		if (pathCounter == null) {
			pathCounter = new PathCounter(pathId);
			pathCounters.put(pathId, pathCounter);
		}
		return pathCounter;
	}

	public String getPathCounterLabel(String pathId) {
		PathCounter pCount = getPathCounter(pathId);
		return "? " + pCount.getIn() + " / ! " + pCount.getOut();
	}

	public String getPathCounterLabel(String owner, String instance, String port) {
		return getPathCounterLabel(getPathId(owner, instance, port));
	}

	private String getPathId(String owner, String instance, String port) {
		return owner + "_" + instance + "_" + port;
	}
}
