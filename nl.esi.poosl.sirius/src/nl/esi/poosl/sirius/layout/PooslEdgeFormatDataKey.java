package nl.esi.poosl.sirius.layout;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.ui.tools.internal.format.semantic.SemanticEdgeFormatDataKey;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import nl.esi.poosl.InstancePort;

/**
 * A key that can be used for storing EdgeFormatData. It avoids duplicate keys
 * by adding the name of the instance when the connected object is an
 * {@link InstancePort}
 * 
 * @author kstaal
 */

@SuppressWarnings("restriction")
public class PooslEdgeFormatDataKey extends SemanticEdgeFormatDataKey {
	private final String sourceNode;
	private final String targetNode;

	public PooslEdgeFormatDataKey(DEdge edge) {
		super(edge.getTarget());
		sourceNode = getEdgeTargetUri(edge.getSourceNode());
		targetNode = getEdgeTargetUri(edge.getTargetNode());
	}

	private String getEdgeTargetUri(EdgeTarget edgeTarget) {
		if (edgeTarget instanceof DSemanticDecorator) {
			DSemanticDecorator semanticDecorator = (DSemanticDecorator) edgeTarget;
			EObject realObject = semanticDecorator.getTarget();
			String edgeId = EcoreUtil.getURI(semanticDecorator.getTarget()).fragment();
			// add instance name. when more instanceports connect to the channel
			// same id's can be created; @channels.1/@instancePorts.1
			if (realObject instanceof InstancePort) {
				InstancePort iPort = (InstancePort) realObject;
				edgeId += "//" + iPort.getInstance().getName();
			}
			return edgeId;

		} else {
			return EcoreUtil.getURI(edgeTarget).fragment();
		}
	}

	@Override
	public String getId() {
		return getSemanticElementURIFragment() + "-" + sourceNode + "-" + targetNode;
	}

	@Override
	public int hashCode() {
		return getSemanticElementURIFragment().hashCode() + sourceNode.hashCode() + targetNode.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
