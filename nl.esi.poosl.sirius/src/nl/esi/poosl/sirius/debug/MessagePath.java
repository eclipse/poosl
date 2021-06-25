package nl.esi.poosl.sirius.debug;

import nl.esi.poosl.rotalumisclient.PooslConstants;

public class MessagePath {
	private final String owner;
	private final String sender;
	private final String receiver;
	private final String senderPort;
	private final String receiverPort;

	public MessagePath(String sender, String receiver, String senderPort, String receiverPort) {
		super();

		this.sender = sender;
		this.receiver = receiver;
		this.senderPort = senderPort;
		this.receiverPort = receiverPort;

		String[] sendLocation = getProcessAsArray(this.sender);
		String[] receiveLocation = getProcessAsArray(this.receiver);
		if (sendLocation.length == receiveLocation.length) {
			this.owner = this.sender.substring(0, this.sender.lastIndexOf(PooslConstants.PATH_SEPARATOR));
		} else {
			this.owner = (sendLocation.length > receiveLocation.length) ? this.receiver : this.sender;
		}
	}

	private String[] getProcessAsArray(String process) {
		return process.substring(1).split(PooslConstants.PATH_SEPARATOR);
	}

	public String getSenderPort() {
		return senderPort;
	}

	public String getReceiverPort() {
		return receiverPort;
	}

	public String getOwner() {
		return owner;
	}

	public String getSender() {
		return sender;
	}

	public String getReceiver() {
		return receiver;
	}
}