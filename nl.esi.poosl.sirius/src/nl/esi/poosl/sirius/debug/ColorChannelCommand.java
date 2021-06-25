package nl.esi.poosl.sirius.debug;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

import nl.esi.poosl.sirius.helpers.ColorGraphHelper;
import nl.esi.poosl.sirius.helpers.DiagramDebugNote;
import nl.esi.poosl.sirius.helpers.GraphicalEditorHelper;

public class ColorChannelCommand extends RecordingCommand {
	private static final String COMMAND_LABEL = "Color Representations";

	private List<DRepresentationDescriptor> descriptors;
	private Map<String, PooslDrawMessage> messages;
	private Map<String, PathCalculator> pathCalculators;

	/**
	 * Specific command to delete the given representations.
	 * 
	 * @param session         the session on which to delete the
	 *                        {@link DRepresentationDescriptor} s. WARNING : can
	 *                        only delete {@link DRepresentationDescriptor}s owned
	 *                        by this {@link Session} because we have one
	 *                        TransactionalEditingDomain per {@link Session}.
	 * @param descriptors     {@link Set} of {@link DRepresentationDescriptor}s to
	 *                        delete.
	 * @param pathCalculators
	 */
	public ColorChannelCommand(Session session, List<DRepresentationDescriptor> descriptors,
			Map<String, PooslDrawMessage> messages, Map<String, PathCalculator> pathCalculators) {
		super(session.getTransactionalEditingDomain(), COMMAND_LABEL);
		this.descriptors = descriptors;
		this.messages = messages;
		this.pathCalculators = pathCalculators;
	}

	public ColorChannelCommand(Session session, DRepresentationDescriptor descriptor,
			Map<String, PooslDrawMessage> messages, Map<String, PathCalculator> pathCalculators) {
		this(session, new ArrayList<DRepresentationDescriptor>(), messages, pathCalculators);
		this.descriptors.add(descriptor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doExecute() {
		for (DRepresentationDescriptor descriptor : descriptors) {
			String launchId = GraphicalEditorHelper.getLaunchIdFromDocumentation(descriptor.getDocumentation());
			PooslDrawMessage drawMessage = messages.get(launchId);
			PathCalculator pathCalculator = (pathCalculators.get(launchId) == null) ? (new PathCalculator(""))
					: pathCalculators.get(launchId);
			DRepresentation representation = descriptor.getRepresentation();
			ColorGraphHelper.colorChannel(representation, drawMessage, pathCalculator);
			DiagramDebugNote debugNote = new DiagramDebugNote(representation);
			debugNote.setDescription(drawMessage);
		}
	}
}
