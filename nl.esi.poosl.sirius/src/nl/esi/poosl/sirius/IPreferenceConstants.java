package nl.esi.poosl.sirius;

public final class IPreferenceConstants {
	public static final String PREFERENCE_PLUGIN_ID = "nl.esi.poosl.sirius";
	public static final String POOSL_FILE_EXT = "poosl";

	public static final String PREFERENCE_PROJECT_EXPLORER = "PrefProjectExplorerDiagram";
	public static final String PREFERENCE_PROJECT_EXPLORER_TEXTUAL = "PrefPETextual";
	public static final String PREFERENCE_PROJECT_EXPLORER_GRAPHICAL = "PrefPEGraphical";
	public static final String PREFERENCE_PROJECT_EXPLORER_CLASS_DIAGRAM = "PrefPEClassDiagram";

	public static final String PREFERENCE_PROJECT_EXPLORER_NO_SYSTEM = "PrefProjectExplorerNoDiagram";

	public static final String PREFERENCE_GRAPHICAL_EDITOR_GRAPHICAL = "PrefGEClassDiagram";
	public static final String PREFERENCE_GRAPHICAL_EDITOR_TEXTUAL = "PrefGETextual";
	public static final String PREFERENCE_GRAPHICAL_EDITOR = "PrefGraphicalEditor";

	public static final String PREFERENCE_PROJECT_EXPLORER_DONT_ASK = "PrefProjecetExplorerDontAsk";

	private IPreferenceConstants() {
		throw new IllegalStateException("Utility class");
	}
}
