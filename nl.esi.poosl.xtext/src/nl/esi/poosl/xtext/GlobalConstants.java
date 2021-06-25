package nl.esi.poosl.xtext;

public final class GlobalConstants {
	public static final String PREFERENCE_PLUGIN_ID = "nl.esi.poosl.xtext.ui";
	public static final String FILE_EXTENSION = "poosl";

	public static final String POOSL_SYSTEM = "system";

	public static final String PREFERENCES_USE_DEFAULT_BASIC_CLASS = "usedefaultbasicclass";
	public static final String PREFERENCES_BASIC_CLASSES_DEFAULT = "defaultbasicclasses";
	public static final String PREFERENCES_BASIC_CLASSES_CUSTOM = "custombasicclasses";
	public static final String PREFERENCES_CUSTOM_BASIC_CLASS_PATH = "custombasicclasspath";
	public static final String PREFERENCES_DONT_ASK_EDIT_PERSPECTIVE = "dontaskperspective";
	public static final String PREFERENCES_OPEN_EDIT_PERSPECTIVE = "openeditperspective";
	public static final String PREFERENCES_AUTOCOMPLETE_BRACKETS_AND_QUOTES = "autcompletebracketsquotes";
	public static final String PREFERENCES_FORMAT_ON_SAVE = "formatonsave";

	public static final String PREFERENCES_INCLUDE_VERSION = "poosl.preferences.version";
	public static final String PREFERENCES_INCLUDE_KEY = "poosl.include";
	public static final String PREFERENCES_INCLUDE_LOCATION = "nl.esi.poosl.xtext.Poosl";
	public static final String PREFERENCES_VERSION = "1";

	private GlobalConstants() {
		throw new IllegalStateException("Utility class");
	}
}
