package nl.esi.poosl.rotalumisclient.debug;

import nl.esi.poosl.rotalumisclient.PooslConstants;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;

public class PooslCommandHandler implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// Listeners are not supported by this command handler
	}

	@Override
	public void dispose() {
		// Nothing to dispose
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String param = event.getParameter(PooslConstants.COMMAND_STEPTYPE);
		PooslDebugTarget debugTarget = PooslDebugHelper.getCurrentDebugTarget();
		if (debugTarget != null && debugTarget.isSuspended() && !debugTarget.isTerminated()) {
			if (param.equals(PooslConstants.COMMAND_STEPTYPE_STEP)) {
				debugTarget.step();
			} else if (param.equals(PooslConstants.COMMAND_STEPTYPE_TIME_STEP)) {
				debugTarget.timeStep();
			} else if (param.equals(PooslConstants.COMMAND_STEPTYPE_COMMUNICATION_STEP)) {
				debugTarget.communicationStep();
			}
		}
		return null;
	}

	@Override
	public boolean isEnabled() {
		PooslDebugTarget debugTarget = PooslDebugHelper.getCurrentDebugTarget();
		if (debugTarget != null) {
			return debugTarget.isSuspended() && !debugTarget.isTerminated();
		}
		return false;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// Listeners are not supported by this command handler
	}
}
