package nl.esi.poosl.sirius.helpers;

import java.text.MessageFormat;

import org.eclipse.emf.ecore.EObject;

import nl.esi.poosl.ClusterClass;

public class DiagramNameHelper {
	public static final String COMMUNICATION_DIAGRAM_PREFIX = "<COMM>";

	private static final String CLASS_DIAGRAM_LABEL = "Classes ({0})";
	private static final String SYSTEM_DIAGRAM_LABEL = "System ({0})";
	private static final String CLUSTER_DIAGRAM_LABEL = "Cluster {0} ({1})";

	private static final String INSTANCE_LABEL_BREAKOFF = "..";
	private static final String INSTANCE_LABEL_SUFFIX = "]";
	private static final String INSTANCE_LABEL_PREFIX = " [";

	private DiagramNameHelper() {
		throw new IllegalStateException("Utility class");
	}

	public static String getCommunicationDiagramName(EObject target, String instance) {
		String normal = getDiagramName(target);
		return getCommunicationDiagramNameFromOriginal(normal, instance);
	}

	public static String getCommunicationDiagramNameFromOriginal(String originalName, String instance) {
		return COMMUNICATION_DIAGRAM_PREFIX + originalName + getInstanceLabel(instance);
	}

	public static String getDiagramName(EObject target) {
		String filename = target.eResource().getURI().lastSegment();
		String name;
		if (target instanceof ClusterClass) {
			name = (((ClusterClass) target).getName() != null)
					? MessageFormat.format(CLUSTER_DIAGRAM_LABEL, ((ClusterClass) target).getName(), filename)
					: MessageFormat.format(SYSTEM_DIAGRAM_LABEL, filename);
		} else {
			name = MessageFormat.format(CLASS_DIAGRAM_LABEL, filename);
		}
		return name;
	}

	private static String getInstanceLabel(String instance) {
		String coreInstanceLabel;
		if (instance.length() > 25) {
			coreInstanceLabel = INSTANCE_LABEL_BREAKOFF + instance.substring(instance.length() - 25);
		} else {
			coreInstanceLabel = instance;
		}
		return INSTANCE_LABEL_PREFIX + coreInstanceLabel + INSTANCE_LABEL_SUFFIX;
	}
}
