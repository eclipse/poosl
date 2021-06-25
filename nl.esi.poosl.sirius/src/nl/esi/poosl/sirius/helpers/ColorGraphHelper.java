package nl.esi.poosl.sirius.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.ColorSelector;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import nl.esi.poosl.Channel;
import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.InstancePort;
import nl.esi.poosl.Port;
import nl.esi.poosl.impl.ChannelImpl;
import nl.esi.poosl.rotalumisclient.PooslConstants;
import nl.esi.poosl.rotalumisclient.RotalumisConstants;
import nl.esi.poosl.sirius.debug.MessagePath;
import nl.esi.poosl.sirius.debug.PathCalculator;
import nl.esi.poosl.sirius.debug.PooslDrawMessage;

public class ColorGraphHelper {
	private static final String PARAMETER_VIEW = "view";
	private static final int LINE_WIDTH = 2;
	private static final RGB DEFAULT_LINE_HIGHLIGHT_COLOR = new RGB(255, 0, 0);
	private static final RGB DEFAULT_LINE_COLOR = new RGB(0, 0, 0);
	private static final int DEFAULT_LINE_WIDTH = 2;
	private static RGB mColor = null;

	private ColorGraphHelper() {
		throw new IllegalStateException("Utility class");
	}

	public static void changeColor(Collection<? extends EObject> eobjects, Map<String, Object> parameters) {
		if (parameters.get(PARAMETER_VIEW) instanceof ArrayList<?>) {
			ArrayList<?> views = (ArrayList<?>) parameters.get(PARAMETER_VIEW);
			if (!views.isEmpty()) {
				Object first = views.iterator().next();
				if (first instanceof DNode) {
					DNode node = (DNode) first;
					colorChannel(node);
				} else if (first instanceof DEdge) {
					DEdge edge = (DEdge) first;
					colorChannel(edge);
				}
			}
		}
	}

	private static RGB getColor() {
		final ColorSelector colorSelector = new ColorSelector(Display.getDefault().getActiveShell());
		colorSelector.addListener(new IPropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				mColor = (RGB) event.getNewValue();
			}
		});
		colorSelector.open();
		return mColor;
	}

	public static void colorChannel(DEdge edge) {
		RGB color = getColor();
		if (color != null && !isEdgeColor(edge, color)) {
			if (edge.getTarget() instanceof ChannelImpl) {
				if (edge.getSourceNode() instanceof DNode) {
					colorAllEdges((DNode) edge.getSourceNode(), color);
				}
			} else {
				toggleColor(edge, true, color);
			}
		}
	}

	public static void colorChannel(DNode nodeSpec) {
		RGB color = getColor();
		if (color != null) {
			colorChannel(nodeSpec, color);
		}
	}

	private static void colorChannel(DNode nodeSpec, RGB color) {
		if (nodeSpec.getTarget() instanceof ChannelImpl) {
			colorAllEdges(nodeSpec, color);
		} else {
			DEdge edge = null;
			if (!nodeSpec.getIncomingEdges().isEmpty()) {
				edge = (nodeSpec.getIncomingEdges().get(0));
			} else if (!nodeSpec.getOutgoingEdges().isEmpty()) {
				edge = (nodeSpec.getOutgoingEdges().get(0));
			}

			if (edge != null) {
				if (edge.getTarget() instanceof ChannelImpl) {
					if (edge.getSourceNode() instanceof DNode) {
						colorAllEdges((DNode) edge.getSourceNode(), color);
					}
				} else {
					if (color == null) {
						toggleColor(edge, rgbCompare(edge.getOwnedStyle(), DEFAULT_LINE_COLOR),
								DEFAULT_LINE_HIGHLIGHT_COLOR);
					} else {
						toggleColor(edge, true, color);
					}
				}
			}
		}
	}

	private static void colorAllEdges(DNode nodeSpec, RGB color) {
		boolean customcolor = false;
		RGB calculatedColor;
		if (color == null) {
			calculatedColor = DEFAULT_LINE_HIGHLIGHT_COLOR;
			if (!nodeSpec.getIncomingEdges().isEmpty()) {
				customcolor = rgbCompare(nodeSpec.getIncomingEdges().get(0).getOwnedStyle(), DEFAULT_LINE_COLOR);
			} else if (!nodeSpec.getOutgoingEdges().isEmpty()) {
				customcolor = rgbCompare(nodeSpec.getOutgoingEdges().get(0).getOwnedStyle(), DEFAULT_LINE_COLOR);
			} else {
				return;
			}
		} else {
			calculatedColor = color;
			customcolor = true;
		}

		toggleColor(nodeSpec, customcolor, calculatedColor);

		for (DEdge dEdge : nodeSpec.getIncomingEdges()) {
			toggleColor(dEdge, customcolor, calculatedColor);
		}
		for (DEdge dEdge : nodeSpec.getOutgoingEdges()) {
			toggleColor(dEdge, customcolor, calculatedColor);
		}
	}

	private static boolean rgbCompare(EdgeStyle style, RGB color) {
		RGBValues values = style.getStrokeColor();
		return (values.getRed() == color.red) && (values.getGreen() == color.green) && (values.getBlue() == color.blue);
	}

	private static void toggleColor(DEdge dEdge, boolean customColor, RGB color) {
		EdgeStyle style = dEdge.getOwnedStyle();
		if (customColor) {
			style = setEdgeColorAndWidth(style, color, LINE_WIDTH);
		} else {
			style = setEdgeDefault(style);
		}
		dEdge.setOwnedStyle(style);
	}

	private static EdgeStyle setEdgeDefault(EdgeStyle style) {
		return setEdgeColorAndWidth(style, DEFAULT_LINE_COLOR, DEFAULT_LINE_WIDTH);
	}

	private static DEdge setEdgeDefault(DEdge edge) {
		edge.setOwnedStyle(setEdgeDefault(edge.getOwnedStyle()));
		return edge;

	}

	private static EdgeStyle setEdgeColorAndWidth(EdgeStyle style, RGB color, int width) {
		setEdgeColor(style, color);
		setEdgeWidth(style, width);
		return style;
	}

	private static EdgeStyle setEdgeWidth(EdgeStyle style, int lineWidth) {
		turnOnWidthCustomFeature(style);
		style.setSize(lineWidth);
		return style;
	}

	private static Boolean isEdgeColor(DEdge edge, RGB newColor) {
		return rgbCompare(edge.getOwnedStyle(), newColor);
	}

	private static NodeStyle adjustChannelColor(NodeStyle style, RGB color) {
		if (style instanceof Square) {
			Square squarestyle = (Square) style;
			squarestyle.setColor(RGBValues.create(color.red, color.green, color.blue));
		}

		style.setBorderColor(RGBValues.create(color.red, color.green, color.blue));

		style.getCustomFeatures().remove(DiagramPackage.Literals.SQUARE__COLOR.getName());
		style.getCustomFeatures().remove(DiagramPackage.Literals.NODE_STYLE.getName());
		style.getCustomFeatures().add(DiagramPackage.Literals.SQUARE__COLOR.getName());
		style.getCustomFeatures().add(DiagramPackage.Literals.NODE_STYLE.getName());
		return style;
	}

	private static void toggleColor(DNode channel, boolean customColor, RGB color) {
		NodeStyle style = channel.getOwnedStyle();
		if (customColor) {
			style = adjustChannelColor(style, color);
		} else {
			style.getCustomFeatures().remove(DiagramPackage.Literals.SQUARE__COLOR.getName());
			style.getCustomFeatures().remove(DiagramPackage.Literals.BORDERED_STYLE__BORDER_COLOR.getName());
		}
		channel.setOwnedStyle(style);
	}

	public static void colorChannel(DRepresentation representation, PooslDrawMessage message,
			PathCalculator pathCalculator) {
		String owner = GraphicalEditorHelper.getInstanceFromDocumentation(representation.getDocumentation());

		MessagePath drawPath = (message == null) ? null : message.getMessagePaths().get(owner);
		for (DRepresentationElement element : representation.getRepresentationElements()) {
			if (element instanceof DEdge) {
				DEdge edge = (DEdge) element;

				ConnectionDescription sourceDescription = createConnectionDescription(owner, edge.getSourceNode());
				ConnectionDescription targetDescription = createConnectionDescription(owner, edge.getTargetNode());

				switch (getEdgePathType(drawPath, sourceDescription, targetDescription)) {
				case NONE:
					setEdgeDefault(edge);
					edge.getOwnedStyle().setTargetArrow(EdgeArrows.NO_DECORATION_LITERAL);
					edge.getOwnedStyle().setSourceArrow(EdgeArrows.NO_DECORATION_LITERAL);
					break;
				case FROMCHANNELRECEIVER:
					setEdgeRed(edge);
					edge.getOwnedStyle().setTargetArrow(EdgeArrows.OUTPUT_CLOSED_ARROW_LITERAL);
					break;
				case FROMCHANNELSENDER:
					setEdgeRed(edge);
					edge.getOwnedStyle().setTargetArrow(EdgeArrows.INPUT_ARROW_LITERAL);
					break;
				case TOCHANNELRECEIVER:
					setEdgeRed(edge);
					edge.getOwnedStyle().setSourceArrow(EdgeArrows.INPUT_ARROW_LITERAL);
					break;
				case TOCHANNELSENDER:
					setEdgeRed(edge);
					edge.getOwnedStyle().setSourceArrow(EdgeArrows.OUTPUT_CLOSED_ARROW_LITERAL);
					break;
				case RECEIVER:
					setEdgeRed(edge);
					edge.getOwnedStyle().setSourceArrow(EdgeArrows.INPUT_ARROW_LITERAL);
					edge.getOwnedStyle().setTargetArrow(EdgeArrows.OUTPUT_CLOSED_ARROW_LITERAL);
					break;
				case SENDER:
					setEdgeRed(edge);
					edge.getOwnedStyle().setSourceArrow(EdgeArrows.OUTPUT_CLOSED_ARROW_LITERAL);
					edge.getOwnedStyle().setTargetArrow(EdgeArrows.INPUT_ARROW_LITERAL);
					break;
				default:
					break;
				}

				if (sourceDescription != null) {
					edge.setBeginLabel(pathCalculator.getPathCounterLabel(owner, sourceDescription.getParent(),
							sourceDescription.getPort()));
				}
				if (targetDescription != null) {
					edge.setEndLabel(pathCalculator.getPathCounterLabel(owner, targetDescription.getParent(),
							targetDescription.getPort()));
				}
			}
		}
	}

	private static EdgeType getEdgePathType(MessagePath drawPath, ConnectionDescription source,
			ConnectionDescription target) {
		if (drawPath == null) {
			return EdgeType.NONE;
		}

		if (source != null && source.getPort() != null && source.getParent() != null) {
			Boolean channelConnection = (target == null);
			if (source.getPort().equals(drawPath.getReceiverPort())
					&& source.getParent().equals(drawPath.getReceiver())) {
				return (channelConnection) ? EdgeType.TOCHANNELRECEIVER : EdgeType.RECEIVER;

			} else if (source.getPort().equals(drawPath.getSenderPort())
					&& source.getParent().equals(drawPath.getSender())) {
				return (channelConnection) ? EdgeType.TOCHANNELSENDER : EdgeType.SENDER;

			}
		}
		if (target != null && target.getPort() != null && target.getParent() != null) {
			Boolean channelConnection = (source == null);
			if (target.getPort().equals(drawPath.getReceiverPort())
					&& target.getParent().equals(drawPath.getReceiver())) {

				return (channelConnection) ? EdgeType.FROMCHANNELSENDER : EdgeType.SENDER;

			} else if (target.getPort().equals(drawPath.getSenderPort())
					&& target.getParent().equals(drawPath.getSender())) {
				return (channelConnection) ? EdgeType.FROMCHANNELRECEIVER : EdgeType.RECEIVER;
			}
		}

		return EdgeType.NONE;
	}

	private enum EdgeType {
		NONE, SENDER, RECEIVER, TOCHANNELRECEIVER, FROMCHANNELRECEIVER, TOCHANNELSENDER, FROMCHANNELSENDER
	}

	private static void setEdgeRed(DEdge dEdge) {
		EdgeStyle edgeStyle = dEdge.getOwnedStyle();
		edgeStyle = setEdgeColor(edgeStyle, new RGB(255, 0, 0));
		edgeStyle = setEdgeWidth(edgeStyle, 2);
		dEdge.setOwnedStyle(edgeStyle);
	}

	private static EdgeStyle setEdgeColor(EdgeStyle edgeStyle, RGB color) {
		turnOnColorCustomFeature(edgeStyle);
		edgeStyle.setStrokeColor(RGBValues.create(color.red, color.green, color.blue));
		return edgeStyle;
	}

	private static void turnOnColorCustomFeature(EdgeStyle style) {
		String edgeFeature = DiagramPackage.Literals.EDGE_STYLE__STROKE_COLOR.getName();
		if (!style.getCustomFeatures().contains(edgeFeature)) {
			style.getCustomFeatures().add(edgeFeature);
		}
	}

	private static void turnOnWidthCustomFeature(EdgeStyle style) {
		String edgeFeature = DiagramPackage.Literals.EDGE_STYLE__SIZE.getName();
		if (!style.getCustomFeatures().contains(edgeFeature)) {
			style.getCustomFeatures().add(edgeFeature);
		}
	}

	private static ConnectionDescription createConnectionDescription(String owner, EObject object) {
		if (object instanceof DNode) {
			EObject target = ((DNode) object).getTarget();

			String portName = null;
			String parentName = null;
			if (target instanceof InstancePort) {
				InstancePort iPort = (InstancePort) target;
				portName = (iPort.getPort() != null) ? iPort.getPort().getPort() : "";
				parentName = owner + "/" + iPort.getInstance().getName();
			} else if (target instanceof Port) {
				Port port = (Port) target;
				portName = port.getName();
				if (port.eContainer() instanceof ClusterClass) {
					parentName = (((ClusterClass) port.eContainer()).getName() == null)
							? PooslConstants.PATH_SEPARATOR + RotalumisConstants.CLUSTER_SYSTEM
							: owner;
				}

			} else if (target instanceof Channel) {
				return null;
			}
			return new ConnectionDescription(portName, parentName);
		}
		return null;
	}

	public static class ConnectionDescription {
		private final String port;
		private final String parent;

		public ConnectionDescription(String port, String parent) {
			this.port = port;
			this.parent = parent;
		}

		public String getPort() {
			return port;
		}

		public String getParent() {
			return parent;
		}
	}
}
