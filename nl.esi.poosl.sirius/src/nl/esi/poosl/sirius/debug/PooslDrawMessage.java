package nl.esi.poosl.sirius.debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import nl.esi.poosl.rotalumisclient.PooslConstants;
import nl.esi.poosl.rotalumisclient.RotalumisConstants;
import nl.esi.poosl.rotalumisclient.extension.ExternDebugMessage;

public class PooslDrawMessage {
	private static final Logger LOGGER = Logger.getLogger(PooslDrawMessage.class.getName());

	private ExternDebugMessage message;
	private Map<String, MessagePath> messagePaths;

	private String[] sendLocation;
	private String[] receiveLocation;

	private int commonIndex;

	public PooslDrawMessage(ExternDebugMessage message, Map<String, String> instancePortMap) {
		setMessage(message);
		setCommonClusterIndex();
		setMessagePaths(calculateMessagePath(instancePortMap));
	}

	private void setCommonClusterIndex() {
		// find highest common cluster
		commonIndex = 0;
		while (sendLocation[commonIndex] != null && receiveLocation[commonIndex] != null
				&& sendLocation[commonIndex].equals(receiveLocation[commonIndex])) {
			commonIndex++;
		}
	}

	private void setMessage(ExternDebugMessage message) {
		this.message = message;
		sendLocation = message.getSendProcess().substring(1).split(PooslConstants.PATH_SEPARATOR);
		receiveLocation = message.getReceiveProcess().substring(1).split(PooslConstants.PATH_SEPARATOR);
	}

	public ExternDebugMessage getMessage() {
		return message;
	}

	/**
	 * @return {@link Map} diagram name as key and the messagepath as value, may
	 *         return null
	 */
	public Map<String, MessagePath> getMessagePaths() {
		return messagePaths;
	}

	public void setMessagePaths(List<MessagePath> messagePathList) {
		if (messagePaths == null) {
			messagePaths = new HashMap<>();
		}
		for (MessagePath mPath : messagePathList) {
			messagePaths.put(mPath.getOwner(), mPath);
		}
	}

	public MessagePath getMessagePath(String owner) {
		if (messagePaths != null) {
			return messagePaths.get(owner);
		} else {
			return null;
		}
	}

	private List<MessagePath> calculateMessagePath(Map<String, String> instancePortMap) {
		List<MessagePath> allPaths = new ArrayList<>();
		List<MessagePath> sendingPaths = createMessagePaths(message.getSendProcess(), message.getSendPort(),
				(sendLocation.length - commonIndex) - 1, instancePortMap, true);
		List<MessagePath> receivingPaths = createMessagePaths(message.getReceiveProcess(), message.getReceivePort(),
				(receiveLocation.length - commonIndex) - 1, instancePortMap, false);

		allPaths.addAll(sendingPaths);
		allPaths.addAll(receivingPaths);
		if (!messageToAdapter()) {
			allPaths.add(createMainMessagePath(sendingPaths, receivingPaths));
		}
		return allPaths;
	}

	private MessagePath createMainMessagePath(List<MessagePath> sendingPaths, List<MessagePath> receivingPaths) {
		String sender;
		String senderPort;
		if (sendingPaths.isEmpty()) {
			sender = message.getSendProcess();
			senderPort = message.getSendPort();
		} else {
			MessagePath lastSending = sendingPaths.get(sendingPaths.size() - 1);
			sender = lastSending.getReceiver();
			senderPort = lastSending.getReceiverPort();
		}

		String receiver;
		String receiverPort;
		if (receivingPaths.isEmpty()) {
			receiver = message.getReceiveProcess();
			receiverPort = message.getReceivePort();
		} else {
			MessagePath firstReceiving = receivingPaths.get(receivingPaths.size() - 1);
			receiver = firstReceiving.getSender();
			receiverPort = firstReceiving.getSenderPort();
		}
		return new MessagePath(sender, receiver, senderPort, receiverPort);
	}

	private List<MessagePath> createMessagePaths(String process, String port, int main,
			Map<String, String> instancePortMap, boolean sending) {
		List<MessagePath> path = new ArrayList<>();

		if (!isAdapterPath(sending)) {
			try {
				String processAndPort = process + "." + port;
				for (int i = 0; i < main; i++) {
					String externProcessAndPort = instancePortMap.get(processAndPort);
					if (externProcessAndPort == null) {
						LOGGER.log(Level.WARNING, "Could not find the external port for " + processAndPort);
						return path;
					}

					String[] externInfo = externProcessAndPort.split("\\.");
					String externProcess = externInfo[0];
					String externPort = externInfo[1];

					String[] receiverInfo = processAndPort.split("\\.");
					String receiverInfoProcess = receiverInfo[0];
					String receiverInfoPort = receiverInfo[1];

					if (sending) {
						path.add(new MessagePath(receiverInfoProcess, externProcess, receiverInfoPort, externPort));
					} else {
						path.add(new MessagePath(externProcess, receiverInfoProcess, externPort, receiverInfoPort));
					}

					processAndPort = externProcessAndPort;
				}
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE,
						"Could not calculate message path for process:" + process + ", port: " + port + ", main: "
								+ main + ", instanceportmap " + instancePortMap + ", and sending is " + sending,
						e.getCause());
			}
		}
		return path;
	}

	private boolean isAdapterPath(boolean sending) {
		if (!messageToAdapter()) {
			return false;
		}

		if (sending) {
			return !sendLocation[0].equals(RotalumisConstants.CLUSTER_SYSTEM);
		} else {
			return !receiveLocation[0].equals(RotalumisConstants.CLUSTER_SYSTEM);
		}
	}

	private boolean messageToAdapter() {
		return commonIndex == 0;
	}
}
