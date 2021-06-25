package nl.esi.poosl.sirius.navigator;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.Instance;
import nl.esi.poosl.Poosl;
import nl.esi.poosl.xtext.helpers.HelperFunctions;

public class CompositeStructureDiagram {

	private CompositeStructureDiagram() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Returns the main target for a composite structure diagram in the poosl model
	 * 
	 * @param poosl
	 * @return null if not appropriate target is found
	 */
	public static EObject findTarget(Poosl poosl) {
		ClusterClass system = HelperFunctions.getSystem(poosl);
		if (system != null) {
			return system;
		} else if (poosl.getClusterClasses().size() == 1) {
			return poosl.getClusterClasses().get(0);
		} else {
			return getMainCluster(poosl);
		}
	}

	/**
	 * find a main cluster in poosl
	 * 
	 * @return Main ClusterClass, returns null if there are multiple or none
	 */
	private static EObject getMainCluster(Poosl poosl) {
		Set<String> clusterSet = new HashSet<>();
		for (ClusterClass clusterClass : poosl.getClusterClasses()) {
			for (Instance instance : clusterClass.getInstances()) {
				clusterSet.add(instance.getClassDefinition());
			}
		}

		ClusterClass mainCluster = null;
		for (ClusterClass clusterClass : poosl.getClusterClasses()) {
			if (!clusterSet.contains(clusterClass.getName())) {
				if (mainCluster != null) {
					// multiple main
					return null;
				}
				mainCluster = clusterClass;
			}
		}
		return mainCluster;
	}
}
