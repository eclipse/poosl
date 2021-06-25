package nl.esi.poosl.rotalumisclient.views.debugview;

import nl.esi.poosl.rotalumisclient.PooslConstants;
import nl.esi.poosl.rotalumisclient.views.WindowCreater;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.ui.PlatformUI;

/**
 * Button handler for the buttons that are shown at the top of the
 * {@link PooslDebugView}
 * 
 * @author staalk
 *
 */
public class PooslDebugButtonHandler implements IHandler {

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		if (event.getCommand().getId().equals(PooslConstants.COMMAND_CLEAR_LAUNCHES)) {
			ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
			for (ILaunch launch : launchManager.getLaunches()) {
				if (launch.isTerminated()) {
					launchManager.removeLaunch(launch);
				}
			}
		} else if (event.getCommand().getId().equals(PooslConstants.COMMAND_CLOSE_THREAD_WINDOWS)) {
			WindowCreater.closeAllThreadWindows(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage());
		}
		return null;
	}

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// This button handler does not need listeners
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// This button handler does not need listeners
	}

	@Override
	public void dispose() {
		// Nothing to dispose
	}
}
