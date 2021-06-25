package nl.esi.poosl.rotalumisclient.views.diagram;

public class PooslDrawableDiagramMessage {
	private int serialNumber;
	private final String content;
	private final int from;
	private final int to;
	private final PooslDiagramMessage sourceMessage;

	public PooslDrawableDiagramMessage(PooslDiagramMessage sourceMessage, int from, int to) {
		this.sourceMessage = sourceMessage;
		this.content = sourceMessage.getPayload();
		this.from = from;
		this.to = to;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public String getContent() {
		return content;
	}

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

	public PooslDiagramMessage getSourceMessage() {
		return sourceMessage;
	}
}
