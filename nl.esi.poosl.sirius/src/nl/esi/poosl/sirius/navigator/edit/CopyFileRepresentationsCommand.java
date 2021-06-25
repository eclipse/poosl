package nl.esi.poosl.sirius.navigator.edit;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

import nl.esi.poosl.sirius.helpers.DiagramNameHelper;
import nl.esi.poosl.sirius.helpers.GraphicalEditorHelper;
import nl.esi.poosl.sirius.helpers.PooslDiagramRefactorHelper;

public class CopyFileRepresentationsCommand extends RecordingCommand {
	private Session destSession;
	private Resource newResource;
	private List<DRepresentationDescriptor> descriptors;
	private IProgressMonitor monitor;

	public CopyFileRepresentationsCommand(TransactionalEditingDomain domain, Session destSession, Resource newResource,
			List<DRepresentationDescriptor> descriptors, IProgressMonitor monitor) {
		super(domain);
		this.destSession = destSession;
		this.newResource = newResource;
		this.descriptors = descriptors;
		this.monitor = monitor;
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
			DRepresentation newRepresentation = DialectManager.INSTANCE.copyRepresentation(descriptor, label, destSession,
					monitor);
			PooslDiagramRefactorHelper.copyDiagramElements(newRepresentation,
					EcoreUtil.getURI(diagramObject).trimFragment(), newDiagramTarget);
		}
	}

	@Override
	public void dispose() {
		this.destSession = null;
		this.newResource = null;
		this.descriptors = null;
		this.monitor = null;
		super.dispose();
	}
}
