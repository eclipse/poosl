package nl.esi.poosl.sirius.navigator.edit;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

import nl.esi.poosl.sirius.helpers.DiagramNameHelper;
import nl.esi.poosl.sirius.helpers.GraphicalEditorHelper;
import nl.esi.poosl.sirius.helpers.PooslDiagramRefactorHelper;

public class RenameFileRepresentationsCommand extends RecordingCommand {
	private Session destSession;
	private Resource newResource;
	private List<DRepresentationDescriptor> descriptors;

	public RenameFileRepresentationsCommand(Session destSession, Resource newResource,
			List<DRepresentationDescriptor> descriptors) {
		super(destSession.getTransactionalEditingDomain());
		this.destSession = destSession;
		this.newResource = newResource;
		this.descriptors = descriptors;
	}

	@Override
	public void doExecute() {
		for (DRepresentationDescriptor descriptor : descriptors) {
			DRepresentation diagram = descriptor.getRepresentation();
			EObject diagramObject = diagram instanceof DSemanticDiagram ? ((DSemanticDiagram) diagram).getTarget()
					: diagram.getOwnedRepresentationElements().get(0).getTarget();
			String objFragment = EcoreUtil.getURI(diagramObject).fragment();
			EObject newDiagramTarget = GraphicalEditorHelper.getSiriusObject(newResource.getEObject(objFragment),
					destSession);
			String label = DiagramNameHelper.getDiagramName(newDiagramTarget);
			descriptor.setName(label);
			PooslDiagramRefactorHelper.copyDiagramElements(diagram, EcoreUtil.getURI(diagramObject).trimFragment(),
					newDiagramTarget);
		}
	}

	@Override
	public void dispose() {
		this.destSession = null;
		this.newResource = null;
		this.descriptors = null;
		super.dispose();
	}
}
