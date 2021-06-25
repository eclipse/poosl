package nl.esi.poosl.sirius.delete;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

import nl.esi.poosl.sirius.debug.DeleteRepresentationCommand;

public class DeleteDiagramRunnable implements IRunnableWithProgress {
	private static final String DELETE_DIAGRAMS = "Deleting Diagrams";
	private static final String DELETE_DIAGRAM = "Deleting Diagram";

	private final Map<Session, Set<DRepresentationDescriptor>> session2Descriptors;

	public DeleteDiagramRunnable(Map<Session, Set<DRepresentationDescriptor>> session2Descriptors) {
		this.session2Descriptors = session2Descriptors;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		try {
			String taskName = session2Descriptors.size() > 1 ? DELETE_DIAGRAM : DELETE_DIAGRAMS;
			monitor.beginTask(taskName, session2Descriptors.size());
			for (Entry<Session, Set<DRepresentationDescriptor>> entry : session2Descriptors.entrySet()) {
				Session session = entry.getKey();
				Set<DRepresentationDescriptor> descriptors = entry.getValue();
				Command deleteDRepresentationsCmd = new DeleteRepresentationCommand(session, descriptors);
				session.getTransactionalEditingDomain().getCommandStack().execute(deleteDRepresentationsCmd);
			}
		} finally {
			monitor.done();
		}
	}
}
