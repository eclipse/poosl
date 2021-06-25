package nl.esi.poosl.rotalumisclient;

public final class PooslConstants {
	// Debug model static
	public static final String DEBUG_MODEL_ID = "nl.esi.poosl.rotalumisclient.DebugModelPresentation";
	public static final String PLUGIN_ID = "nl.esi.poosl.rotalumisclient";
	public static final String PLUGIN_ID_ROTALUMIS_EXECUTABLES = "nl.tue.rotalumis.executables";
	public static final String POOSL_EXTENSION = "poosl";
	public static final String EXTERN_CONFIG_EXTENSION = "ini";

	// View ID statics
	public static final String ID_POOSL_SEQUENCEDIAGRAMVIEW = "nl.esi.poosl.views.sequencediagramview";
	public static final String ID_POOSL_VARIABLESVIEW = "nl.esi.poosl.views.variablesview";
	public static final String ID_POOSL_PETVIEW = "nl.esi.poosl.views.petview";
	public static final String ID_POOSL_DEBUGVIEW = "nl.esi.poosl.views.debugview";
	public static final String ID_POOSL_VARIABLESVIEW_DETAIL_PANE = "nl.esi.poosl.rotalumisclient.PooslVariableDetailPane";
	public static final String ID_POOSL_STACKTRACEVIEW = "nl.esi.poosl.views.stacktraceview";
	public static final String ID_POOSL_STACKTRACEVARIABLEVIEW = "nl.esi.poosl.views.stacktracevariableview";

	// Perspective ID statics
	public static final String ID_POOSL_EDIT_PERSPECTIVE = "nl.esi.poosl.normalperspective";
	public static final String ID_POOSL_DEBUG_PERSPECTIVE = "nl.esi.poosl.debugperspective";

	// Debug event statics
	public static final int INSPECT_REQUEST = 1234;
	public static final int BREAKPOINT_HIT = 2345;
	public static final int INSPECT_RECEIVED = 3456;
	public static final int PERFORM_TRANSITION = 4567;
	public static final int COMM_EVENTS_CHANGE = 5678;
	public static final int CLEAR_COMM_EVENTS = 6789;
	public static final int ERROR_STATE = 7890;
	public static final int STOPPED_STATE = 8901;
	public static final int ENGINE_ERROR = 9012;
	public static final int STACKFRAME_INSPECT = 0123;

	// Configuration statics
	public static final String CONFIGURATION_ATTRIBUTE_MODEL_PATH = "modelpath";
	public static final String CONFIGURATION_ATTRIBUTE_EXTERNAL_CONFIG_PATH = "externpath";
	public static final String CONFIGURATION_ATTRIBUTE_PROJECT = "projectname";
	public static final String CONFIGURATION_ATTRIBUTE_SERVER_IP = "127.0.0.1";
	public static final String CONFIGURATION_ATTRIBUTE_SERVER_PORT = "serverport";
	public static final String CONFIGURATION_ATTRIBUTE_DEFAULT_SERVER_PORT = "10001";
	public static final String CONFIGURATION_ATTRIBUTE_MAX_STACKSIZE = "maxStackSize";
	public static final String CONFIGURATION_ATTRIBUTE_DEFAULT_MAX_STACKSIZE_BYTES = "8388608";
	public static final String CONFIGURATION_ATTRIBUTE_RELATIVE_PATH = "relativeModelPath";
	public static final String CONFIGURATION_ATTRIBUTE_IS_RANDOM_SEED = "israndomseed";
	public static final boolean CONFIGURATION_ATTRIBUTE_DEFAULT_IS_RANDOM_SEED = false;
	public static final String CONFIGURATION_ATTRIBUTE_SEED = "simulationseed";
	public static final String CONFIGURATION_ATTRIBUTE_DEFAULT_SEED = "1";
	public static final String CONFIGURATION_ATTRIBUTE_IS_QUIET = "isquiet";
	public static final boolean CONFIGURATION_ATTRIBUTE_DEFAULT_IS_QUIET = false;
	public static final String CONFIGURATION_ATTRIBUTE_SEQUENCE_DIAGRAM_FILTER_VISIBLE = "visiblefilter";
	public static final String CONFIGURATION_ATTRIBUTE_SEQUENCE_DIAGRAM_FILTER_INVISIBLE = "invisiblefilter";
	public static final String CONFIGURATION_ATTRIBUTE_SEQUENCE_DIAGRAM_ORDER = "messageorder";
	public static final String CONFIGURATION_ATTRIBUTE_SEQUENCE_DIAGRAM_FILTER_SETTING = "filtersetting";
	public static final String CONFIGURATION_ATTRIBUTE_SIMULATOR_INOUT_FOLDER = "simulator";
	public static final String CONFIGURATION_ATTRIBUTE_LAUNCH_TYPE = "nl.esi.poosl.rotalumisclient.PooslLaunchConfiguration";
	public static final String CONFIGURATION_ATTRIBUTE_LAUNCH_TEST_TYPE = "nl.esi.poosl.rotalumisclient.PooslTestLaunchConfiguration";
	public static final String CONFIGURATION_ATTRIBUTE_WORKSPACE_MODEL = "workspaceModel";
	public static final String CONFIGURATION_ATTRIBUTE_TEST_CONF = "poosl.launch.test";

	// Command statics
	public static final String COMMAND_CLEAR_LAUNCHES = "nl.esi.poosl.rotalumisclient.views.debug.clearlaunches";
	public static final String COMMAND_CLOSE_THREAD_WINDOWS = "nl.esi.poosl.rotalumisclient.views.debug.closethreadwindows";
	public static final String COMMAND_STEPTYPE = "nl.esi.poosl.rotalumisclient.stepcommand.steptype";
	public static final String COMMAND_STEPTYPE_STEP = "step";
	public static final String COMMAND_STEPTYPE_TIME_STEP = "timestep";
	public static final String COMMAND_STEPTYPE_COMMUNICATION_STEP = "commstep";
	public static final String COMMAND_DIAGRAM_VIEW = "nl.esi.poosl.rotalumisclient.views.diagram.viewaction";
	public static final String COMMAND_DIAGRAM_VIEW_CLEAR_VIEW = "clearview";
	public static final String COMMAND_DIAGRAM_VIEW_SCROLL_LOCK = "scrolllock";
	public static final String COMMAND_DIAGRAM_VIEW_SETTINGS = "settings";
	public static final String COMMAND_DIAGRAM_SETUP = "nl.esi.poosl.rotalumisclient.views.diagram.setupaction";

	// Preferences statics
	public static final String PREFERENCES_LOG_LEVEL = "LOG_LEVEL";
	public static final String PREFERENCES_MESSAGE_BUFFER_SIZE = "MESSAGE_BUFFER_SIZE";
	public static final int DEFAULT_MESSAGE_BUFFER_SIZE = 1000;
	public static final String DEFAULT_LOG_LEVEL = "OFF";
	public static final String PLATFORM_ENCODING = "file.encoding";

	// Thread window
	public static final String THREAD_VIEW_ID = "THREAD_WINDOW";

	public static final String PATH_SEPARATOR = "/";

	private PooslConstants() {
		throw new IllegalStateException("Utility class");
	}
}