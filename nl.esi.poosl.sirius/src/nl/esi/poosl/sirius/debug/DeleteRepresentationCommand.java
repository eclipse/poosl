package nl.esi.poosl.sirius.debug;

import java.util.Set;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

public class DeleteRepresentationCommand extends RecordingCommand {
	private Set<DRepresentationDescriptor> descriptors;
	private Session session;

	/**
	 * Specific command to delete the given representations.
	 * 
	 * @param session     the session on which to delete the
	 *                    {@link DRepresentationDescriptor} s. WARNING : can only
	 *                    delete {@link DRepresentationDescriptor}s owned by this
	 *                    {@link Session} because we have one
	 *                    TransactionalEditingDomain per {@link Session}.
	 * @param descriptors {@link Set} of {@link DRepresentationDescriptor}s to
	 *                    delete.
	 */
	public DeleteRepresentationCommand(Session session, Set<DRepresentationDescriptor> descriptors) {
		super(session.getTransactionalEditingDomain(), "Delete representations");
		this.session = session;
		this.descriptors = descriptors;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doExecute() {
		for (DRepresentationDescriptor descriptor : descriptors) {
			if (descriptor != null) {
				DialectManager.INSTANCE.deleteRepresentation(descriptor, session);
			}
		}
	}
}