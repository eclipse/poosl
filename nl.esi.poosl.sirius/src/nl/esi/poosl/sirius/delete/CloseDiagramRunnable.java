package nl.esi.poosl.sirius.delete;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

public class CloseDiagramRunnable implements IRunnableWithProgress {
	private static final String CLOSING_EDITORS = "Closing diagrams.";

	private final Map<Session, Set<DRepresentationDescriptor>> session2Descriptors;

	public CloseDiagramRunnable(Map<Session, Set<DRepresentationDescriptor>> session2Descriptors) {
		this.session2Descriptors = session2Descriptors;
	}

	@Override
	public void run(final IProgressMonitor monitor) {
		try {
			monitor.beginTask(CLOSING_EDITORS, 1);
			for (Entry<Session, Set<DRepresentationDescriptor>> entry : session2Descriptors.entrySet()) {
				Session session = entry.getKey();
				Set<DRepresentationDescriptor> descriptors = entry.getValue();
				IEditingSession editingSession = SessionUIManager.INSTANCE.getUISession(session);
				if (editingSession != null) {
					for (DRepresentationDescriptor descriptor : descriptors) {
						DialectEditor editor = editingSession.getEditor(descriptor.getRepresentation());
						if (editor != null) {
							DialectUIManager.INSTANCE.closeEditor(editor, false);
							editingSession.detachEditor(editor);
						}
					}
				}
			}
		} finally {
			monitor.done();
		}
	}
}
