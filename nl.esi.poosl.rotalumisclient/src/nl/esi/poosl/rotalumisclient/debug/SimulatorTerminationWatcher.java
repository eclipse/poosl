package nl.esi.poosl.rotalumisclient.debug;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;

public class SimulatorTerminationWatcher implements Runnable {
	private static final Logger LOGGER = Logger.getLogger(SimulatorTerminationWatcher.class.getName());

	private final PooslDebugTarget debugTarget;
	private final Process simulationProcess;
	private final String projectName;

	public SimulatorTerminationWatcher(PooslDebugTarget debugTarget, Process simulationProcess, String projectName) {
		this.debugTarget = debugTarget;
		this.simulationProcess = simulationProcess;
		this.projectName = projectName;
	}

	@Override
	public void run() {
		try {
			int exitCode = simulationProcess.waitFor();
			LOGGER.info("Simulator process terminated with return value: " + exitCode);
			if (debugTarget != null) {
				debugTarget.terminate();
			}
		} catch (InterruptedException e) {
			LOGGER.log(Level.WARNING, "Simulator was interrupted.", e);
			Thread.currentThread().interrupt();
		} catch (DebugException e) {
			LOGGER.log(Level.WARNING, "Debugexception occured during simulator process.", e);
		}
		try {
			if (projectName != null && !projectName.isEmpty()) {
				IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
				project.refreshLocal(IResource.DEPTH_INFINITE, null);
			}
		} catch (CoreException e) {
			LOGGER.log(Level.WARNING,
					"Could not get project name from launch configuration.\nWill not be able to refresh the project.",
					e);
		}
	}
}
