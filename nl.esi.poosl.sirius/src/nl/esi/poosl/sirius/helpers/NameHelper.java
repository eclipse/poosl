package nl.esi.poosl.sirius.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.Instance;
import nl.esi.poosl.xtext.custom.PooslCache;
import nl.esi.poosl.xtext.helpers.HelperFunctions;

public class NameHelper {
	private static final Logger LOGGER = Logger.getLogger(NameHelper.class.getName());

	private static final String TXT_INSTANCE = "newInstance";
	private static final String TXT_CLUSTERCLASS = "ClusterClass";
	private static final String TXT_PROCESSCLASS = "ProcessClass";
	private static final String TXT_DATACLASS = "DataClass";
	private static final String UNDEFINED = "Undefined";

	private NameHelper() {
		throw new IllegalStateException("Utility class");
	}
	// get instance names

	public static String getUniqueInstanceName(ClusterClass container) {
		return getUniqueInstanceName(TXT_INSTANCE, container);
	}

	public static String getUniqueInstanceName(String addition, ClusterClass container) {
		return getUniqueName(addition, getInstanceNames(container));
	}

	public static String getUniqueInstanceName(List<String> instances) {
		return getUniqueName(TXT_INSTANCE, instances);
	}

	// get unique name

	public static String getUniqueName(String addition, Iterable<String> names) {
		try {
			String name = addition;
			int index = 1;
			while (!isNameAvailable(name, names)) {
				name = addition + index;
				index++;
			}
			return name;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Can not create new name. " + e.getMessage(), e.getCause());
			return UNDEFINED;
		}
	}

	public static boolean isNameAvailable(String name, Iterable<String> usedNames) {
		for (String instancename : usedNames) {
			if (instancename != null && instancename.equalsIgnoreCase(name)) {
				return false;
			}
		}
		return true;
	}

	// get cluster name

	public static String getUniqueClusterName(EObject container) {
		return getUniqueClusterName(TXT_CLUSTERCLASS, container);
	}

	public static String getUniqueClusterName(String addition, EObject container) {
		return getUniqueName(addition, getAllInstantiableNames(container));
	}

	// get process name

	public static String getUniqueProcessName(EObject container) {
		return getUniqueProcessName(TXT_PROCESSCLASS, container);
	}

	public static String getUniqueProcessName(String addition, EObject container) {
		return getUniqueName(addition, getAllInstantiableNames(container));
	}

	// get data name

	public static String getUniqueDataName(EObject container) {
		return getUniqueDataName(TXT_DATACLASS, container);
	}

	public static String getUniqueDataName(String addition, EObject container) {
		return getUniqueName(addition, getAllDataNames(container));
	}

	// find all existing names

	public static Iterable<String> getAllDataNames(EObject container) {
		Resource resource = container.eResource();
		return HelperFunctions.getNames(PooslCache.get(resource).getAllRelevantDataClasses());
	}

	public static Iterable<String> getAllInstantiableNames(EObject container) {
		Resource resource = container.eResource();
		return HelperFunctions.getNames(HelperFunctions.getAllRelevantInstantiableClasses(resource));
	}

	public static Iterable<String> getAllProcessNames(EObject object) {
		Resource resource = object.eResource();
		return HelperFunctions.getNames(PooslCache.get(resource).getAllRelevantProcessClasses());
	}

	public static Iterable<String> getAllClusterNames(EObject object) {
		Resource resource = object.eResource();
		return HelperFunctions.getNames(PooslCache.get(resource).getAllRelevantClusterClasses());
	}

	public static List<String> getInstanceNames(ClusterClass container) {
		List<String> result = new ArrayList<>();
		for (Instance instance : container.getInstances()) {
			result.add(instance.getName());
		}
		return result;
	}

}
