package nl.esi.poosl.sirius.debug;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

import nl.esi.poosl.sirius.helpers.DiagramNameHelper;

public class CopyRepresentationCommand extends RecordingCommand {
	private static final String COMMAND_LABEL = "Copy Communication Diagram";

	private final Collection<DRepresentationDescriptor> descriptors;
	private final Session session;
	private final String launch;
	private final String instance;
	private final List<DRepresentationDescriptor> createdDescriptors = new ArrayList<>();

	/**
	 * Specific command to copy the given representations.
	 * 
	 * @param domain      the current editing domain.
	 * @param discriptors the representations to copy.
	 * @param session     the current session.
	 */
	public CopyRepresentationCommand(TransactionalEditingDomain domain,
			Collection<DRepresentationDescriptor> discriptors, Session session, String launch, String instance) {
		super(domain, COMMAND_LABEL);
		this.descriptors = discriptors;
		this.launch = launch;
		this.session = session;
		this.instance = instance;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doExecute() {
		if (descriptors == null || session == null) {
			return;
		}

		for (DRepresentationDescriptor descriptor : descriptors) {
			DRepresentation createdRepresentation = DialectManager.INSTANCE.copyRepresentation(descriptor,
					DiagramNameHelper.getCommunicationDiagramNameFromOriginal(descriptor.getName(), instance), session,
					null);
			DRepresentationDescriptor createdDescriptor = new DRepresentationQuery(createdRepresentation)
					.getRepresentationDescriptor();
			String documentation = launch + "," + instance;
			createdDescriptor.setDocumentation(documentation);
			createdDescriptors.add(createdDescriptor);
		}
	}

	public List<DRepresentationDescriptor> getCreatedDescriptors() {
		return createdDescriptors;
	}
}
