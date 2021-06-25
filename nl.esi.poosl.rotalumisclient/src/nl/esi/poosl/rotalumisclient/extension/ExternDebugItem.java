package nl.esi.poosl.rotalumisclient.extension;

public class ExternDebugItem {
	private final String diagram;
	private final String relativeModelPath;
	private final String projectName;
	private final String launchID;

	public ExternDebugItem(String diagram, String relativeModelPath, String projectName, String launchID) {
		this.diagram = diagram;
		this.relativeModelPath = relativeModelPath;
		this.projectName = projectName;
		this.launchID = launchID;
	}

	public String getDiagram() {
		return diagram;
	}

	public String getRelativeModelPath() {
		return relativeModelPath;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getLaunchID() {
		return launchID;
	}
}
