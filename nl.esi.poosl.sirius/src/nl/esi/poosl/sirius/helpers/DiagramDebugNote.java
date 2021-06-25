package nl.esi.poosl.sirius.helpers;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;

import nl.esi.poosl.rotalumisclient.extension.ExternDebugMessage;
import nl.esi.poosl.sirius.debug.PooslDrawMessage;

public class DiagramDebugNote {
	private static final String MESSAGE_DESCRIPTION = "* Message: ";
	private static final String MESSAGE_SIMTIME = "* Time: ";
	private static final String MESSAGE_SENDER = "* Sender:  ";
	private static final String MESSAGE_PARAMETERS = "* Parameters: ";
	private static final String MESSAGE_RECEIVER = "* Receiver:  ";

	private static final int CONTAINER_NOTE_GAP = 30;
	private static final int DEFAULT_NOTE_WIDTH = 150;

	private final Shape note;
	private final String owner;

	public DiagramDebugNote(DRepresentation representation) {
		owner = GraphicalEditorHelper.getInstanceFromDocumentation(representation.getDocumentation());
		note = findNote(representation);
	}

	public void setVisible(boolean visible) {
		if (note != null) {
			note.setVisible(visible);
		}
	}

	public boolean isVisible() {
		if (note != null) {
			return note.isVisible();
		}
		return false;
	}

	private Shape findNote(DRepresentation representation) {
		for (AnnotationEntry dAnnotation : representation.getOwnedAnnotationEntries()) {
			EObject annotationData = dAnnotation.getData();
			if (annotationData instanceof Diagram) {
				Diagram diagram = (Diagram) annotationData;
				return getNote(diagram);
			}
		}
		return null;
	}

	public void setDescription(PooslDrawMessage message) {
		if (note != null) {
			boolean empty = true;
			note.setVisible(true);
			if (message != null && owner != null) {
				empty = message.getMessagePath(owner) == null;
			}
			note.setDescription((empty) ? createNoteMessage(null) : createNoteMessage(message.getMessage()));
		}
	}

	private static String createNoteMessage(ExternDebugMessage rawMessage) {
		String senderInfo = "";
		String receiverInfo = "";
		String messageNameInfo = "";
		String parameterInfo = "";
		String simTime = "";

		if (rawMessage != null) {
			senderInfo = rawMessage.getSendProcess() + "." + rawMessage.getSendPort();
			receiverInfo = rawMessage.getReceiveProcess() + "." + rawMessage.getReceivePort();
			messageNameInfo = rawMessage.getMessageName();
			simTime = rawMessage.getSimTime();
			StringBuilder paramBuilder = new StringBuilder();
			for (String key : rawMessage.getParameters().keySet()) {
				paramBuilder.append("-- " + key + ": " + rawMessage.getParameters().get(key) + "\n");
			}
			parameterInfo = paramBuilder.toString();
		}

		StringBuilder builder = new StringBuilder();
		// First word (MESSAGE_DESCRIPTION) in the note acts as an identifier to find
		// the note later on
		builder.append(MESSAGE_DESCRIPTION + messageNameInfo + "\n");
		builder.append(MESSAGE_SIMTIME + simTime + "\n");
		builder.append(MESSAGE_SENDER + senderInfo + "\n");
		builder.append(MESSAGE_RECEIVER + receiverInfo + "\n");
		builder.append(MESSAGE_PARAMETERS + "\n");
		builder.append(parameterInfo);
		return builder.toString();
	}

	private static Shape getNote(Diagram diagram) {
		Shape shape = getNoteInDiagram(diagram);
		if (shape == null) {
			shape = createNote(diagram);
		}
		return shape;
	}

	private static Shape createNote(Diagram diagram) {
		Node node = GMFNotationHelper.createNote(diagram, MESSAGE_DESCRIPTION);

		int noteLocation = 0;
		int noteWidth = -1;
		for (Object object : diagram.getChildren()) {
			if (object instanceof Node) {
				Node childNode = (Node) object;
				if (childNode.getElement() instanceof DNodeContainer) {

					LayoutConstraint constaint = childNode.getLayoutConstraint();
					if (constaint instanceof Bounds) {
						Bounds bounds = (Bounds) constaint;

						// if diagram width can not be determined, place note on the left and move
						// diagram.
						if (bounds.getWidth() != -1) {
							noteLocation = CONTAINER_NOTE_GAP + bounds.getWidth() + bounds.getX();
						} else {
							noteWidth = DEFAULT_NOTE_WIDTH;
							bounds.setX(CONTAINER_NOTE_GAP + noteWidth);
							childNode.setLayoutConstraint(bounds);
						}
						break;
					}
				}
			}
		}

		LayoutConstraint location = GMFNotationHelper.createLayoutPosition(BigInteger.valueOf(noteLocation),
				BigInteger.valueOf(10), BigInteger.valueOf(noteWidth),
				BigInteger.valueOf(GMFNotationHelper.getHeight(node)));

		node.setLayoutConstraint(location);
		node.setVisible(false);

		if (node instanceof Shape) {
			Shape shape = (Shape) node;
			shape.setDescription(createNoteMessage(null));
			return shape;
		} else {
			return null;
		}
	}

	private static Shape getNoteInDiagram(Diagram diagram) {
		for (Object object : diagram.getChildren()) {
			if (object instanceof Shape) {
				Shape shape = (Shape) object;
				if (GMFNotationHelper.isNote(shape) && shape.getDescription().startsWith(MESSAGE_DESCRIPTION)) {
					return shape;
				}
			}
		}
		return null;
	}
}
