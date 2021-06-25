package nl.esi.poosl.sirius.debug;

public class PathCounter {
	private final String pathId;
	private int in;
	private int out;

	public PathCounter(String pathId) {
		super();
		this.pathId = pathId;
		this.in = 0;
		this.out = 0;
	}

	public void addIncoming() {
		in++;
	}

	public void addOutgoing() {
		out++;
	}

	public int getIn() {
		return in;
	}

	public int getOut() {
		return out;
	}

	public String getPathId() {
		return pathId;
	}
}
