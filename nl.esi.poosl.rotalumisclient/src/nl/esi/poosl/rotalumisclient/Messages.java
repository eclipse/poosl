package nl.esi.poosl.rotalumisclient;

public final class Messages {
	public static final String RESULT_OK = "ok";

	public static final String DEFAULT_STOPPED_TITLE = "Simulator has stopped";
	public static final String DEFAULT_STOPPED_TEXT = "Rotalumis has stopped without providing a message.";

	public static final String DIALOG_RELAUNCH_TITLE = "Save during debug session";
	public static final String DIALOG_RELAUNCH_TEXT = "Saving the file ''{0}'' during a debug session does not update the running session.\n"
			+ "When continuing to save the file the communication diagrams of this model will be closed.\n"
			+ "Do you want to terminate and relaunch the debug session {1}. ";
	public static final String DIALOG_RELAUNCH_BT_CONTINUE = "Continue debugging";
	public static final String DIALOG_RELAUNCH_BT_TERMINATE = "Terminate and relaunch";

	public static final String DIALOG_NOTSTART_TITLE = "Could not start simulation";
	public static final String DIALOG_NOTSTART_TEXT = "Could not start the simulation on port {0}, because this port is already in use {1}. To run two or more simulations at the same time they should all use a different simulator port. This port can be set in the debug configuration.\n\nDo you want to terminate the running simulation and launch this one instead?";
	public static final String DIALOG_NOTSTART_BT_TERMINATE = "Terminate and launch";
	public static final String DIALOG_NOTSTART_BT_CANCEL = "Cancel";

	public static final String DIALOG_CREATE_BREAKPOINT_TITLE = "Create Breakpoint";
	public static final String DIALOG_CREATE_BREAKPOINT_FAILED_MESSAGE = "Failed to create breakpoint.";
	public static final String DIALOG_CREATE_BREAKPOINT_FAILED_LOCATION = "Failed to create breakpoint on line {0} in the file '{1}'";

	public static final String DIALOG_SETVARIABLE_TITLE = "Change Variable failed";
	public static final String DIALOG_SETVARIABLE_TEXT = "The value of the variable could not be changed.";

	public static final String DIALOG_EDITED_TEXT = "Because the model has been edited it's not possible to open a communication diagram during this debug session. Please start a new debug session to be able to open a communication diagram.";
	public static final String DIALOG_EDITED_TITLE = "Open Communication Diagram";
	public static final String DIALOG_EDITED_BT_OK = "Ok";

	public static final String DIALOG_BREAKPOINT_TITLE = "Poosl Breakpoint";
	public static final String DIALOG_BREAKPOINT_NO_STATEMENT = "No statement found; breakpoints can only be placed on statements in process methods.";
	public static final String DIALOG_BREAKPOINT_INIT_METHOD = "Breakpoints cannot be placed on the 'init' method call of a process class.";
	public static final String DIALOG_BREAKPOINT_UNSUPPORTED = "Breakpoints are not supported on this location when using the XML generation for simulation. The XML generation setting can be turned off in Preferences. (Window -> Preferences -> Poosl -> Simulator)";

	public static final String ACTION_MENU_NEW_WINDOW = "Open in New Window";
	public static final String ACTION_MENU_OPEN_DEBUG_DIAGRAM = "Open Communication Diagram";
	public static final String ACTION_MENU_PROCESS_STEP = "Perform Process Step";

	public static final String ROTALUMIS_REQUEST_SEND = "Request Message Send.";
	public static final String ROTALUMIS_REQUEST_MARSHAL_FAILED = "Simulator request failed: Could not marshal message.";
	public static final String ROTALUMIS_REQUEST_NOT_ENCODED = "Simulator request failed: Could not encode message.";
	public static final String ROTALUMIS_REQUEST_SOCKET_CLOSED = "Simulator request failed: Socket is already closed.";
	public static final String ROTALUMIS_REQUEST_NOT_SEND = "Simulator request failed: Could not send message.";
	public static final String ROTALUMIS_REQUEST_NOT_SEND_USER_INFO = " Server port {0} may already be in use by another program. The server port to be used by the simulator can be changed in the Launch Configuration (Debug As -> Debug Configurations...).";
	public static final String ROTALUMIS_REQUEST_TOO_LARGE = "Simulator request failed: Message was too large.";
	public static final String ROTALUMIS_REQUEST_TOO_LARGE_USER_INFO = " If you were starting a debug session, the model might be too large for debugging, but perhaps still feasible for running.";

	private Messages() {
		throw new IllegalStateException("Utility class");
	}
}
