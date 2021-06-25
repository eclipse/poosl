package nl.esi.poosl.sirius.helpers;

import java.util.Collection;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

import com.google.common.collect.Sets;

import nl.esi.poosl.Poosl;
import nl.esi.poosl.xtext.importing.ImportingHelper;

public class CreateRepresentationCommand extends RecordingCommand {
	private RepresentationDescription description;
	private Session session;
	private EObject semantic;
	private DRepresentationDescriptor descriptor;
	private String launchID;
	private String instance;
	private IProgressMonitor monitor;

	/**
	 * Construct a new instance with {@link IProgressMonitor}.
	 * 
	 * @param session     the session
	 * @param description the representation description
	 * @param eObject     the semantic element on which to create the representation
	 * @param launchID    if lauchID is not null or empty provided it will make a
	 *                    communication diagram
	 */
	public CreateRepresentationCommand(Session session, RepresentationDescription description, EObject eObject,
			String launchID, String instance, IProgressMonitor monitor) {
		super(session.getTransactionalEditingDomain(), "Create Representation");
		this.session = session;
		this.description = description;
		this.semantic = eObject;
		this.launchID = launchID;
		this.instance = instance;
		this.monitor = monitor;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#canExecute()
	 */
	@Override
	public boolean canExecute() {
		/*
		 * if there is an initial operation an semantic element could be added and used
		 * after on precondition
		 */
		EObject target = GraphicalEditorHelper.getSiriusObject(semantic, session);
		return DialectManager.INSTANCE.canCreate(target, description);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doExecute() {
		addResource(semantic, session);
		EObject target = GraphicalEditorHelper.getSiriusObject(semantic, session);
		DRepresentation representation = DialectManager.INSTANCE.createRepresentation(getDiagramName(target), target,
				description, session, monitor);
		descriptor = new DRepresentationQuery(representation).getRepresentationDescriptor();

		if (isDebugDiagram()) {
			String documentation = launchID + "," + instance;
			descriptor.setDocumentation(documentation);
		}
	}

	private String getDiagramName(EObject target) {
		String name = DiagramNameHelper.getDiagramName(target);
		if (isDebugDiagram()) {
			name = DiagramNameHelper.getCommunicationDiagramNameFromOriginal(name, instance);
		}
		return name;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#getResult()
	 */
	@Override
	public Collection<?> getResult() {
		final Set<DRepresentationDescriptor> descriptors = Sets.newHashSet();
		descriptors.add(descriptor);
		/* do not leak */
		clearData();
		return descriptors;
	}

	/**
	 * Get the created representation.
	 * 
	 * @return the created representation
	 */
	public DRepresentationDescriptor getCreatedDescriptor() {
		return descriptor;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.transaction.RecordingCommand#dispose()
	 */
	@Override
	public void dispose() {
		super.dispose();
		clearData();
	}

	private void clearData() {
		this.descriptor = null;
		this.session = null;
		this.description = null;
		this.semantic = null;
	}

	private boolean isDebugDiagram() {
		return launchID != null && !launchID.isEmpty();
	}

	private void addResource(final EObject diagramTarget, final Session session) {
		Poosl poosl;
		if (diagramTarget instanceof Poosl) {
			poosl = (Poosl) diagramTarget;
		} else {
			poosl = ImportingHelper.toPoosl(diagramTarget.eResource());
		}

		for (Resource resource : ImportingHelper.computeAllDependencies(poosl.eResource())) {
			session.addSemanticResource(resource.getURI(), monitor);
		}
	}
}