package nl.esi.poosl.rotalumisclient.debug;

import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IThread;

import nl.esi.poosl.generatedxmlclasses.TBreakpointInfo;
import nl.esi.poosl.generatedxmlclasses.TCreateBreakpointResponse;
import nl.esi.poosl.generatedxmlclasses.TExecutionStateChangeResponse;
import nl.esi.poosl.generatedxmlclasses.TTransition;
import nl.esi.poosl.rotalumisclient.Client;
import nl.esi.poosl.rotalumisclient.Messages;
import nl.esi.poosl.rotalumisclient.PooslConstants;
import nl.esi.poosl.rotalumisclient.sourcemapping.PooslSourceMap;
import nl.esi.poosl.rotalumisclient.sourcemapping.PooslSourceMapping;
import nl.esi.poosl.rotalumisclient.sourcemapping.PooslSourceMappingListener;

public class PooslBreakpointManager {
	private static final String COULD_NOT_ACCES_BREAKPOINT_MARKER = "Could not acces breakpoint marker.";

	private static final Logger LOGGER = Logger.getLogger(PooslBreakpointManager.class.getName());

	private Map<IBreakpoint, Integer> breakpoints = new HashMap<>();
	private Map<String, IBreakpoint> breakpointRequests = new HashMap<>();

	private Client client;
	private Map<String, BigInteger> filesToHandle = new HashMap<>();
	private BigInteger modelHandle;

	public void configureBreakpointManager(Client client, Map<String, BigInteger> filesToHandle,
			BigInteger modelHandle) {
		this.client = client;
		this.filesToHandle = filesToHandle;
		this.modelHandle = modelHandle;

		addAllBreakpoints();
	}

	private void addAllBreakpoints() {
		IBreakpoint[] pooslBreakpoints = DebugPlugin.getDefault().getBreakpointManager()
				.getBreakpoints(PooslConstants.DEBUG_MODEL_ID);
		for (IBreakpoint breakpoint : pooslBreakpoints) {
			addBreakpoint(breakpoint);
			try {
				if (!breakpoint.isEnabled()) {
					disableBreakpoint(breakpoint);
				}
			} catch (CoreException e) {
				LOGGER.log(Level.WARNING, COULD_NOT_ACCES_BREAKPOINT_MARKER, e);
			}
		}
	}

	public void addBreakpoint(IBreakpoint breakpoint) {
		IPath fileLocation = breakpoint.getMarker().getResource().getLocation();
		BigInteger file = filesToHandle.get(fileLocation.toOSString());
		if (file == null) {
			file = filesToHandle.get(fileLocation.toString());
		}
		int lineNr = breakpoint.getMarker().getAttribute(IMarker.LINE_NUMBER, -1);

		if (modelHandle != null && file != null && lineNr != -1) {
			breakpointRequests.put(file + "|" + lineNr, breakpoint);
			client.createBreakpoint(BigInteger.ZERO, file, lineNr);
		} else {
			LOGGER.log(Level.SEVERE,
					"Could not set breakpoint for file "
							+ breakpoint.getMarker().getResource().getLocation().toOSString() + ". (" + file + ", "
							+ lineNr + ")");
		}
	}

	public void deleteBreakpoint(IBreakpoint breakpoint) {
		Integer statementHandle = breakpoints.get(breakpoint);
		if (statementHandle != null) {
			client.deleteBreakpoint(statementHandle);
		}
		breakpoints.remove(breakpoint);
	}

	public void enableBreakpoint(IBreakpoint breakpoint) {
		Integer statementHandle = breakpoints.get(breakpoint);
		if (statementHandle != null) {
			client.enableBreakpoint(statementHandle);
		}
	}

	public void disableBreakpoint(IBreakpoint breakpoint) {
		Integer statementHandle = breakpoints.get(breakpoint);
		if (statementHandle != null) {
			client.disableBreakpoint(statementHandle);
		}
	}

	public void handleBreakpointResonse(TCreateBreakpointResponse createBreakpoint) {
		String result = createBreakpoint.getResult();
		String id = getCreatedBreakpointId(createBreakpoint);
		IBreakpoint breakpoint = breakpointRequests.remove(id);

		if (!Messages.RESULT_OK.equals(result)) {
			LOGGER.warning(Messages.DIALOG_CREATE_BREAKPOINT_FAILED_MESSAGE);
			if (breakpoint != null) {
				try {
					String fileString = breakpoint.getMarker().getResource().getLocationURI().toString();
					int lineNr = breakpoint.getMarker().getAttribute(IMarker.LINE_NUMBER, -1);
					PooslDebugHelper.showErrorMessage(Messages.DIALOG_CREATE_BREAKPOINT_TITLE, MessageFormat
							.format(Messages.DIALOG_CREATE_BREAKPOINT_FAILED_LOCATION, lineNr, fileString));
					breakpoint.delete();
				} catch (CoreException e) {
					LOGGER.log(Level.WARNING, "Breakpoint could not automatically be deleted." + id, e);
				}
			} else {
				PooslDebugHelper.showErrorMessage(Messages.DIALOG_CREATE_BREAKPOINT_TITLE,
						Messages.DIALOG_CREATE_BREAKPOINT_FAILED_MESSAGE + id);
			}
		} else {
			if (breakpoint == null) {
				LOGGER.severe("Breakpoint create response returned unmatched location" + id + ". Open requests: "
						+ breakpointRequests.keySet());
				return;
			}
			breakpoints.put(breakpoint, createBreakpoint.getStmtHandle());
			try {
				if (!breakpoint.isEnabled()) {
					disableBreakpoint(breakpoint);
				}
			} catch (CoreException e) {
				LOGGER.log(Level.WARNING, COULD_NOT_ACCES_BREAKPOINT_MARKER, e);
			}
		}
	}

	public void handleBreakpointHit(final PooslDebugTarget pooslDebugTarget, final TExecutionStateChangeResponse state,
			PooslSourceMap pooslSourceMap) {
		TBreakpointInfo breakpoint = state.getBreakpoints().getBreakpoint().get(0);
		pooslSourceMap.getSourceMapping(breakpoint.getStmtHandle(), new PooslSourceMappingListener(false) {

			public void requestedSourceMapping(PooslSourceMapping mapping) {
				LOGGER.fine("Execution state response has breakpoint: " + mapping);
				TTransition transition = state.getTransition();

				try {
					PooslThread breakpointThread = getBreakpointThread(pooslDebugTarget.getThreads(), mapping,
							transition);

					// Get the possible transitions
					client.getTransitions();

					// Fire a debug event to inform other views of the breakpoint hit
					pooslDebugTarget.fireEvent(
							new DebugEvent(breakpointThread, DebugEvent.MODEL_SPECIFIC, PooslConstants.BREAKPOINT_HIT));
				} catch (DebugException e) {
					LOGGER.warning("Couldnt find breakpoint thread " + mapping);
				}
			}
		});
	}

	/**
	 * Gets the thread from the array of threads that has the transition inside.
	 * Also sets the active breakpoint node that corresponds with the given
	 * modelElement on this thread.
	 * 
	 * @param threads
	 * @param sourceMapping
	 * @param transition
	 * @return
	 * @throws DebugException if the thread could not be found by name
	 */
	public static PooslThread getBreakpointThread(IThread[] threads, PooslSourceMapping sourceMapping,
			TTransition transition) throws DebugException {
		String processPath = "";
		BigInteger node = null;
		if (transition.getProcessStep() != null) {
			processPath = transition.getProcessStep().getProcessPath();
			node = transition.getProcessStep().getNode();
		} else if (transition.getCommunication() != null) {
			if (sourceMapping.getSourceText().contains("!")) {
				processPath = transition.getCommunication().getSender().getProcessPath();
				node = transition.getCommunication().getSender().getNode();
			} else if (sourceMapping.getSourceText().contains("?")) {
				processPath = transition.getCommunication().getReceiver().getProcessPath();
				node = transition.getCommunication().getReceiver().getNode();
			}
		} else if (transition.getDelay() != null) {
			processPath = transition.getDelay().getProcessPath();
			node = transition.getDelay().getNode();
		}
		final PooslThread breakpointThread = PooslDebugHelper.getThreadByName(threads, processPath);
		if (breakpointThread != null) {
			breakpointThread.setActiveBreakpointNode(node);
		}
		return breakpointThread;
	}

	private String getCreatedBreakpointId(TCreateBreakpointResponse response) {
		return response.getPosition().getFile() + "|" + response.getPosition().getLine();
	}

	public void clear() {
		breakpoints.clear();
	}

	public void breakpointChanged(IBreakpoint breakpoint) {
		// If the breakpoint manager is disabled this means that the user
		// has selected to skip all the breakpoints.
		// The only way to implement this is currently to disable all
		// breakpoints in Rotalumis.
		// There for the enabled state of the breakpoint manager is copied
		// to the individual breakpoint
		if (!DebugPlugin.getDefault().getBreakpointManager().isEnabled()) {
			disableBreakpoint(breakpoint);
		}
		try {
			if (breakpoint.isEnabled()) {
				enableBreakpoint(breakpoint);
			} else {
				disableBreakpoint(breakpoint);
			}
		} catch (CoreException e) {
			LOGGER.log(Level.WARNING, COULD_NOT_ACCES_BREAKPOINT_MARKER, e);
		}
	}
}
