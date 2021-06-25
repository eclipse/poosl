package nl.esi.poosl.xtext.validation;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.diagnostics.Diagnostic;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckType;

import com.google.common.collect.Iterables;

import nl.esi.poosl.Annotation;
import nl.esi.poosl.AssignmentExpression;
import nl.esi.poosl.Channel;
import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.DataMethodBinaryOperator;
import nl.esi.poosl.DataMethodNamed;
import nl.esi.poosl.DataMethodUnaryOperator;
import nl.esi.poosl.Declaration;
import nl.esi.poosl.Import;
import nl.esi.poosl.Instance;
import nl.esi.poosl.InstanceParameter;
import nl.esi.poosl.InstancePort;
import nl.esi.poosl.InstantiableClass;
import nl.esi.poosl.MessageParameter;
import nl.esi.poosl.MessageSignature;
import nl.esi.poosl.NewExpression;
import nl.esi.poosl.OutputVariable;
import nl.esi.poosl.Poosl;
import nl.esi.poosl.PooslPackage;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.Port;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.ProcessMethodCall;
import nl.esi.poosl.ReceiveStatement;
import nl.esi.poosl.Variable;
import nl.esi.poosl.VariableExpression;
import nl.esi.poosl.xtext.custom.PooslCache;
import nl.esi.poosl.xtext.custom.PooslCacheEntry;
import nl.esi.poosl.xtext.custom.PooslMessageType;
import nl.esi.poosl.xtext.helpers.HelperFunctions;
import nl.esi.poosl.xtext.helpers.PooslMessageSignatureCallHelper;
import nl.esi.poosl.xtext.helpers.PooslReferenceHelper;

public class PooslJavaValidatorUniqueIdentifiers extends PooslJavaValidatorAPI {
	private static final String DUPLICATE_VARIABLE = "There is another variable in scope with the same name {0}";
	private static final String DUPLICATE_VARIABLE_ASSIGNMENT = "Variable {0} is assigned more than once";
	private static final String DUPLICATE_PARAMETER_INSTANTIATION = "There is another instantiation for the same parameter {0}";
	private static final String DUPLICATE_ANNOTATION = "There is another annotation with the same name";

	private static final String DUPLICATE_INSTANCE = "There is another instance with the same name {0}";
	private static final String DUPLICATE_PROCESS_CLUSTER_CLASS = "There is another process or cluster class with the same name {0} in {1}";
	private static final String DUPLICATE_DATA_CLASS = "There is another data class with the same name {0} in {1}";
	private static final String DUPLICATE_PORT = "There is another port in scope with the same name {0}";

	private static final String DUPLICATE_IMPORT_DATA_CLASS = "Data class {0} is defined in two different files: {1} (accessible via import {2}) and {3} (accessible via import {4})";
	private static final String DUPLICATE_IMPORT_PROCESS_CLUSTER_CLASS = "Process or Cluster class {0} is defined in two different files: {1} (accessible via import {2}) and {3} (accessible via import {4})";

	private static final String DUPLICATE_RECEIVE_MESSAGE = "There is another receive message in scope with the same name, port and number of parameters";
	private static final String DUPLICATE_SEND_MESSAGE = "There is another send message in scope with the same name, port and number of parameters";
	private static final String DUPLICATE_METHOD = "There is another method with the same name {0} and number of parameters";

	private static final String MULTIPLE_CONNECTION_EXTERNAL_PORT = "This external port is connected more than once";
	private static final String MULTIPLE_CONNECTION_INSTANCEPORT = "This instance port is connected more than once";

	private static final String UNCONNECTED_PORT = "Port is not connected to a channel";

	private static final double HASHMAP_SIZE_FACTOR = 1.5;

	// --- File level -------

	@Check(CheckType.FAST)
	public void checkDataClasses(Poosl poosl) {
		EList<DataClass> localClasses = poosl.getDataClasses();
		Map<String, String> duplicatesMap = getDuplicateDataClasses(poosl);

		for (DataClass dClass : localClasses) {
			checkDataSuperClass(dClass);
			if (checkUniqueDataClasses(dClass, duplicatesMap) && checkCyclesDataClasses(dClass)) {
				checkUniqueDataMethods(dClass);
				checkUniqueVariables(dClass);
			}
		}
	}

	@Check(CheckType.FAST)
	public void checkInstantiableClasses(Poosl poosl) {
		Iterable<InstantiableClass> localClasses = Iterables.concat(poosl.getProcessClasses(),
				poosl.getClusterClasses());
		Map<InstantiableClass, String> doubleClassesLocation = getDuplicateInstantiableClasses(poosl, localClasses);

		for (InstantiableClass iClass : localClasses) {
			if (checkUniqueInstantiableClasses(iClass, doubleClassesLocation)) {
				if (iClass instanceof ProcessClass) {
					ProcessClass pClass = (ProcessClass) iClass;
					checkProcessSuperClass(pClass);
					if (checkCyclesProcessClasses(pClass)) {
						// There is no cyclic dependency so check the unique
						// elements of this class
						checkUniquePorts(pClass);
						checkProcessClassMessageSignaturePorts(pClass);
						checkUniqueMessageSignatures(pClass, PooslMessageType.RECEIVE);
						checkUniqueMessageSignatures(pClass, PooslMessageType.SEND);
						checkUniqueProcessMethods(pClass);
						checkUniqueVariables(pClass);
					}
				} else if (iClass instanceof ClusterClass) {
					ClusterClass cClass = (ClusterClass) iClass;
					checkCyclesClusterClasses(cClass);
					checkUniquePorts(cClass);
					checkUniqueVariables(cClass);
				}
			}
		}
	}

	public void checkProcessClassMessageSignaturePorts(ProcessClass pClass) {
		Set<String> portNames = PooslReferenceHelper.getPortNames(pClass);
		checkMessageSignaturePorts(pClass.getSendMessages(), portNames);
		checkMessageSignaturePorts(pClass.getReceiveMessages(), portNames);
	}

	private void checkMessageSignaturePorts(List<MessageSignature> messageSignatures, Set<String> portNames) {
		for (MessageSignature messageSignature : messageSignatures) {
			String portName = messageSignature.getPort().getPort();
			if (!portNames.contains(portName)) {
				error(MessageFormat.format(NOT_DECLARED, Literals.PORT.getName(), portName), messageSignature,
						Literals.MESSAGE_SIGNATURE__PORT, Diagnostic.LINKING_DIAGNOSTIC);
			}
		}
	}

	public void checkProcessSuperClass(ProcessClass pClass) {
		String superClass = pClass.getSuperClass();
		if (superClass != null && PooslCache.get(pClass.eResource()).getProcessClass(superClass) == null) {
			error(MessageFormat.format(NOT_DECLARED, Literals.PROCESS_CLASS.getName(), superClass), pClass,
					Literals.PROCESS_CLASS__SUPER_CLASS, Diagnostic.LINKING_DIAGNOSTIC);
		}
	}

	public void checkDataSuperClass(DataClass dClass) {
		String superClass = dClass.getSuperClass();
		if (superClass != null && PooslCache.get(dClass.eResource()).getDataClass(superClass) == null
				&& !HelperFunctions.permanentDataClasses.contains(superClass)) {
			error(MessageFormat.format(NOT_DECLARED, Literals.DATA_CLASS.getName(), superClass), dClass,
					Literals.DATA_CLASS__SUPER_CLASS, Diagnostic.LINKING_DIAGNOSTIC);
		}
	}

	@Check(CheckType.FAST)
	public void checkImportConflictsDataClasses(Poosl poosl) {
		Map<Import, List<IEObjectDescription>> dataClasses = PooslCache.get(poosl.eResource()).getImportedDataClasses();

		Map<Import, Iterable<IEObjectDescription>> importedDataClasses = new HashMap<>();
		for (Import imported : Iterables.concat(poosl.getImportLibs(), poosl.getImports())) {
			List<IEObjectDescription> dataImports = dataClasses.get(imported);
			if (dataImports != null) {
				importedDataClasses.put(imported, dataImports);
			}
		}

		checkImportDescriptionConflicts(importedDataClasses, DUPLICATE_IMPORT_DATA_CLASS);
	}

	@Check(CheckType.FAST)
	public void checkImportConflictsInstantiableClasses(Poosl poosl) {
		PooslCacheEntry pooslCacheEntry = PooslCache.get(poosl.eResource());
		Map<Import, List<IEObjectDescription>> processClasses = pooslCacheEntry.getImportedProcessClasses();
		Map<Import, List<IEObjectDescription>> clusterClasses = pooslCacheEntry.getImportedClusterClasses();

		Map<Import, Iterable<IEObjectDescription>> importedInstantiableClasses = new HashMap<>();
		for (Import imported : Iterables.concat(poosl.getImportLibs(), poosl.getImports())) {
			List<IEObjectDescription> processImports = processClasses.get(imported);
			List<IEObjectDescription> clusterImports = clusterClasses.get(imported);
			if (processImports != null && clusterImports != null) {
				importedInstantiableClasses.put(imported, Iterables.concat(processImports, clusterImports));
			} else if (processImports != null) {
				importedInstantiableClasses.put(imported, processImports);
			} else if (clusterImports != null) {
				importedInstantiableClasses.put(imported, clusterImports);
			}
		}

		checkImportDescriptionConflicts(importedInstantiableClasses, DUPLICATE_IMPORT_PROCESS_CLUSTER_CLASS);
	}

	private void checkImportDescriptionConflicts(Map<Import, Iterable<IEObjectDescription>> importedDescriptions,
			String error) {
		for (Entry<Import, Iterable<IEObjectDescription>> entry1 : importedDescriptions.entrySet()) {
			Import import1 = entry1.getKey();
			for (Entry<Import, Iterable<IEObjectDescription>> entry2 : importedDescriptions.entrySet()) {
				Import import2 = entry2.getKey();
				if (import1 != import2) {
					checkDuplicateDescriptions(error, entry1, entry2);
				}
			}
		}
	}

	private void checkDuplicateDescriptions(String error, Entry<Import, Iterable<IEObjectDescription>> entry1,
			Entry<Import, Iterable<IEObjectDescription>> entry2) {
		Map<IEObjectDescription, IEObjectDescription> duplicateSet = getDuplicateDescriptionNames(entry1.getValue(),
				entry2.getValue());
		for (Entry<IEObjectDescription, IEObjectDescription> entry : duplicateSet.entrySet()) {
			String file1 = descriptionToFileLocation(entry.getValue());
			String file2 = descriptionToFileLocation(entry.getKey());
			error(MessageFormat.format(error, HelperFunctions.getName(entry.getKey()), file1,
					entry1.getKey().getImportURI(), file2, entry2.getKey().getImportURI()), entry1.getKey(), null,
					null);
		}
	}

	private String descriptionToFileLocation(IEObjectDescription description) {
		URI uri = description.getEObjectURI().trimFragment();
		if (uri.isPlatformResource()) {
			String platformString = uri.toPlatformString(true);
			try {
				IPath rootPath = ResourcesPlugin.getWorkspace().getRoot().getLocation();
				return rootPath.append(platformString).toString();
			} catch (NullPointerException e) {
				return platformString;
			}
		}
		return uri.toString();
	}

	private Map<IEObjectDescription, IEObjectDescription> getDuplicateDescriptionNames(
			Iterable<IEObjectDescription> descriptions, Iterable<IEObjectDescription> descriptions2) {
		Map<String, IEObjectDescription> mapPooslClasses = new HashMap<>();
		Map<IEObjectDescription, IEObjectDescription> duplicates = new HashMap<>();

		for (IEObjectDescription descr : descriptions) {
			String name = HelperFunctions.getName(descr);
			mapPooslClasses.put(name, descr);
		}
		for (IEObjectDescription descr : descriptions2) {
			String name = HelperFunctions.getName(descr);
			IEObjectDescription duplicate = mapPooslClasses.put(name, descr);
			if (duplicate != null && !descr.getEObjectURI().path().equals(duplicate.getEObjectURI().path())) {
				duplicates.put(descr, duplicate);
			}
			if (duplicate != null) {
				// Put the original class back in the map so a
				// possible
				// third duplicate will report as a duplicate of the
				// original one.
				mapPooslClasses.put(name, duplicate);
			}
		}
		return duplicates;
	}

	private Map<String, String> getDuplicateDataClasses(Poosl poosl) {
		Iterable<IEObjectDescription> scopeClasses = PooslCache.get(poosl.eResource()).getAllRelevantDataClasses();
		String resourcePath = poosl.eResource().getURI().path();

		Map<String, String> dataClassesMap = new HashMap<>();
		Map<String, String> duplicatesMap = new HashMap<>();

		for (IEObjectDescription scopeCl : scopeClasses) {
			String className = HelperFunctions.getName(scopeCl);
			String duplicate = dataClassesMap.put(className, scopeCl.getEObjectURI().path());
			if (duplicate != null) {
				if (duplicate.equals(resourcePath)) {
					duplicatesMap.put(className, scopeCl.getEObjectURI().path());
				} else if (scopeCl.getEObjectURI().path().equals(resourcePath)) {
					duplicatesMap.put(className, duplicate);
				}
			}

		}
		return duplicatesMap;
	}

	private boolean checkUniqueDataClasses(DataClass dClass, Map<String, String> duplicatesMap) {
		String hasDuplicate = duplicatesMap.get(dClass.getName());
		if (hasDuplicate != null) {
			String location = URI.createURI(hasDuplicate).lastSegment();
			if (location != null) {
				// When the data class has no name "data class extends Object",
				// the parser sees 'Object' as the data class name
				// This results in the warning "There is another data class with
				// the same name Object"
				error(MessageFormat.format(DUPLICATE_DATA_CLASS, dClass.getName(), location), dClass,
						Literals.DATA_CLASS__NAME, PooslIssueCodes.DUPLICATE_CLASS_NAME);
			}
			return false;
		}
		return true;
	}

	private boolean checkUniqueInstantiableClasses(InstantiableClass iClass,
			Map<InstantiableClass, String> doubleClassesLocation) {
		if (doubleClassesLocation.containsKey(iClass)) {
			error(MessageFormat.format(DUPLICATE_PROCESS_CLUSTER_CLASS, iClass.getName(),
					doubleClassesLocation.get(iClass)), iClass, Literals.INSTANTIABLE_CLASS__NAME,
					PooslIssueCodes.DUPLICATE_CLASS_NAME);
			return false;
		}
		return true;
	}

	private Map<InstantiableClass, String> getDuplicateInstantiableClasses(Poosl poosl,
			Iterable<InstantiableClass> localClasses) {
		Resource resource = poosl.eResource();
		Iterable<IEObjectDescription> scopeClasses = HelperFunctions.getAllRelevantInstantiableClasses(resource);
		Map<String, Object> instantiableClassesMap = new HashMap<>();
		for (IEObjectDescription iClass : scopeClasses) {
			if (!iClass.getEObjectURI().path().equals(resource.getURI().path())) {
				instantiableClassesMap.put(HelperFunctions.getName(iClass), iClass.getEObjectURI());
			}
		}

		Map<InstantiableClass, String> doubleClasses = new HashMap<>();
		for (InstantiableClass iClass : localClasses) {
			String cName = iClass.getName();
			if (cName != null) {
				Object duplicateClass = instantiableClassesMap.put(cName, iClass);

				// if not null classname already exists
				if (duplicateClass != null) {

					String location = null;
					// if the duplicateClass is instance of InstantiableClass it
					// means the duplicate class in the current file
					if (duplicateClass instanceof InstantiableClass) {
						// also show error on the first class
						InstantiableClass firstClass = (InstantiableClass) duplicateClass;
						location = firstClass.eResource().getURI().lastSegment();
						doubleClasses.put(firstClass, location);

						// put back original class
						instantiableClassesMap.put(iClass.getName(), firstClass);
					}
					if (duplicateClass instanceof URI) {
						location = ((URI) duplicateClass).lastSegment();
					}

					if (location != null) {
						doubleClasses.put(iClass, location);
					}
				}
			}
		}
		return doubleClasses;
	}

	// --- Instantiable class -------

	public void checkUniquePorts(InstantiableClass iClass) {
		// get AncestorPorts
		Set<String> ancestorPorts = null;
		boolean isProcessClass = iClass instanceof ProcessClass;
		if (isProcessClass) {
			Resource resource = iClass.eResource();
			String parent = ((ProcessClass) iClass).getSuperClass();
			if (parent != null) {
				ancestorPorts = PooslCache.get(resource).getInstantiableClassPorts(parent).keySet();
			}
		}
		// find all double Ports
		Set<Port> doublePorts = new HashSet<>();
		Map<String, Port> portsMap = new HashMap<>();
		for (Port localPort : iClass.getPorts()) {
			String portName = localPort.getName();
			if (portName != null) {
				if (ancestorPorts != null && ancestorPorts.contains(portName)) {
					doublePorts.add(localPort);
				} else {
					Port duplicate = portsMap.get(portName);
					if (duplicate != null) {
						doublePorts.add(localPort);
						doublePorts.add(duplicate);
					} else {
						portsMap.put(portName, localPort);
					}
				}
			}
		}
		// show warning on double ports, otherwise warn if unused
		for (Port port : iClass.getPorts()) {
			if (doublePorts.contains(port)) {
				error(MessageFormat.format(DUPLICATE_PORT, port.getName()), port, null, null);
			} else {
				if (isProcessClass) {
					warnUnusedPorts((ProcessClass) iClass, port);
				} else {
					warnUnconnectedPorts((ClusterClass) iClass, port);
				}
			}
		}
	}

	public void warnUnconnectedPorts(ClusterClass cClass, Port port) {
		boolean found = false;
		for (Channel channel : cClass.getChannels()) {
			if (channel.getExternalPort() == port) {
				found = true;
				break;
			}
		}
		if (!found) {
			warning(UNCONNECTED_PORT, port, null, PooslIssueCodes.UNCONNECTED_EXTERNAL_PORT, WarningType.UNCONNECTED);
		}
	}

	// --- Architectural class -------

	@Check(CheckType.FAST)
	public void checkArchitecturalClassUniqueInstances(ClusterClass aClass) {
		List<Instance> instances = aClass.getInstances();
		Map<String, Instance> duplicateInstances = new HashMap<>(
				(int) (instances.size() * HASHMAP_SIZE_FACTOR));
		Set<Instance> duplicates = new HashSet<>();

		for (Instance instance : instances) {
			Instance duplicateInstance = duplicateInstances.put(instance.getName(), instance);
			if (duplicateInstance != null) {
				duplicates.add(duplicateInstance);
				duplicates.add(instance);
			}
		}
		for (Instance instance : duplicates) {
			error(MessageFormat.format(DUPLICATE_INSTANCE, instance.getName()), instance, Literals.INSTANCE__NAME,
					null);
		}

		for (Instance instance : instances) {
			IEObjectDescription classDef = PooslReferenceHelper.getInstantiableClassDescription(instance);
			if (classDef == null) {
				error(MessageFormat.format(NOT_DECLARED, Literals.INSTANTIABLE_CLASS.getName(),
						instance.getClassDefinition()), instance, Literals.INSTANCE__CLASS_DEFINITION,
						Diagnostic.LINKING_DIAGNOSTIC);
			}

			if (!duplicates.contains(instance)) {
				warnSendReceiveMismatchUnconnectedPort(instance);
			}
		}
		checkInstancePorts(aClass);
	}

	private void checkInstancePorts(ClusterClass aClass) {
		Map<Instance, Set<String>> instanceToPortNames = new HashMap<>();
		for (Channel channel : aClass.getChannels()) {
			for (InstancePort instancePort : channel.getInstancePorts()) {
				Instance instance = instancePort.getInstance();
				Set<String> namesMap = instanceToPortNames.get(instance);
				if (namesMap == null) {
					namesMap = PooslReferenceHelper.getPortNames(instancePort);
					instanceToPortNames.put(instance, namesMap);
				}
				if (namesMap == null)
					continue;
				String portName = (instancePort.getPort() != null) ? instancePort.getPort().getPort() : "";
				if (!namesMap.contains(portName)) {
					error(MessageFormat.format(NOT_DECLARED, Literals.PORT.getName(), portName), instancePort,
							Literals.INSTANCE_PORT__PORT, Diagnostic.LINKING_DIAGNOSTIC);
				}
			}
		}
	}

	@Check(CheckType.FAST)
	public void errorOverlappingChannels(ClusterClass aClass) {
		Set<InstancePort> duplicateInstancePorts = new HashSet<>();
		Set<Channel> duplicateExternalPorts = new HashSet<>();
		Map<String, Set<Port>> instancePortToExternalPorts = new HashMap<>();
		Map<String, InstancePort> instancePortNameToInstancePort = new HashMap<>();
		Map<Port, Channel> externalPortToChannel = new HashMap<>();

		for (Channel channel : aClass.getChannels()) {
			// Each combination (instance, instance port) should be used at most
			// once
			for (InstancePort instancePort : channel.getInstancePorts()) {
				String instancePortName = computeInstancePortName(instancePort);
				InstancePort duplicateInstancePort = instancePortNameToInstancePort.put(instancePortName, instancePort);
				if (duplicateInstancePort != null) {
					duplicateInstancePorts.add(duplicateInstancePort);
					duplicateInstancePorts.add(instancePort);

					// Save references to external ports of this instance
					// port to use later
					Set<Port> externalPorts = instancePortToExternalPorts.get(instancePortName);
					if (externalPorts == null) {
						externalPorts = new HashSet<>();
						instancePortToExternalPorts.put(instancePortName, externalPorts);
					}
					if (((Channel) duplicateInstancePort.eContainer()).getExternalPort() != null) {
						externalPorts.add(((Channel) duplicateInstancePort.eContainer()).getExternalPort());
					}
					if (((Channel) instancePort.eContainer()).getExternalPort() != null) {
						externalPorts.add(((Channel) instancePort.eContainer()).getExternalPort());
					}
				}
			}
			// Each external port should be used at most once
			if (channel.getExternalPort() != null) {
				Channel duplicateExternalPort = externalPortToChannel.put(channel.getExternalPort(), channel);
				if (duplicateExternalPort != null) {
					duplicateExternalPorts.add(duplicateExternalPort);
					duplicateExternalPorts.add(channel);
				}
			}
		}

		for (InstancePort instancePort : duplicateInstancePorts) {
			// Check if different external ports are referenced by the duplicate
			// instance port. If so, then don't offer a quickfix
			String instancePortName = computeInstancePortName(instancePort);
			if (instancePortToExternalPorts.get(instancePortName).size() > 1) {
				error(MULTIPLE_CONNECTION_INSTANCEPORT, instancePort, null, null);
			} else {
				error(MULTIPLE_CONNECTION_INSTANCEPORT, instancePort, null,
						PooslIssueCodes.MULTIPLE_CHANNELS_CONNECTED_TO_INSTANCE_PORT);
			}
		}
		for (Channel ch : duplicateExternalPorts) {
			error(MULTIPLE_CONNECTION_EXTERNAL_PORT, ch, PooslPackage.Literals.CHANNEL__EXTERNAL_PORT,
					PooslIssueCodes.MULTIPLE_CHANNELS_CONNECTED_TO_EXTERNAL_PORT);
		}
	}

	private String computeInstancePortName(InstancePort instancePort) {
		Instance instance = instancePort.getInstance();
		if (instance != null) {
			String instanceName = instance.getName();
			String portName = (instancePort.getPort() != null) ? instancePort.getPort().getPort() : "";
			if (instanceName != null && portName != null) {
				return instanceName + "." + portName;
			}
		}
		return null;
	}

	@Check(CheckType.FAST)
	public void checkInstanceUniqueParameterInstantiations(Instance instance) {
		List<InstanceParameter> localParameterInstantiations = instance.getInstanceParameters();
		Map<String, InstanceParameter> variablesMap = new HashMap<>(
				(int) (localParameterInstantiations.size() * HASHMAP_SIZE_FACTOR));
		Set<InstanceParameter> duplicateVariables = new HashSet<>();

		for (InstanceParameter parameterInstantiation : localParameterInstantiations) {
			String parName = parameterInstantiation.getParameter();
			if (parName != null) {
				InstanceParameter duplicateParameterInstantiation = variablesMap.put(parName, parameterInstantiation);
				if (duplicateParameterInstantiation != null) {
					duplicateVariables.add(parameterInstantiation);
					duplicateVariables.add(duplicateParameterInstantiation);
				}
			}
		}
		for (InstanceParameter parameterInstantiation : duplicateVariables) {
			error(MessageFormat.format(DUPLICATE_PARAMETER_INSTANTIATION, parameterInstantiation.getParameter()),
					parameterInstantiation, Literals.INSTANCE_PARAMETER__PARAMETER, null);
		}
	}

	// --- Methods -------

	public void checkUniqueProcessMethods(ProcessClass pClass) {
		List<ProcessMethod> methods = pClass.getMethods();
		Map<String, ProcessMethod> methodsMap = new HashMap<>((int) (methods.size() * HASHMAP_SIZE_FACTOR));
		Set<ProcessMethod> duplicates = new HashSet<>();
		for (ProcessMethod method : methods) {
			ProcessMethod duplicateMethod = methodsMap.put(processMethod2StringNameAndParameterCounts(method), method);
			if (duplicateMethod != null) {
				duplicates.add(duplicateMethod);
				duplicates.add(method);
			}
		}
		for (ProcessMethod method : duplicates) {
			error(MessageFormat.format(DUPLICATE_METHOD, method.getName()), method, Literals.PROCESS_METHOD__NAME,
					PooslIssueCodes.DUPLICATE_METHOD_NAME);
		}
	}

	public void checkUniqueDataMethods(DataClass dClass) {
		List<DataMethodNamed> dataMethodsNamed = dClass.getDataMethodsNamed();
		Map<String, DataMethodNamed> dataMethodsNamedMap = new HashMap<>(
				(int) (dataMethodsNamed.size() * HASHMAP_SIZE_FACTOR));
		Set<DataMethodNamed> dataMethodsNamedduplicates = new HashSet<>();
		for (DataMethodNamed method : dataMethodsNamed) {
			DataMethodNamed duplicateMethod = dataMethodsNamedMap
					.put(dataMethod2StringNameAndParameterCount(method.getName(), method), method);
			if (duplicateMethod != null) {
				dataMethodsNamedduplicates.add(duplicateMethod);
				dataMethodsNamedduplicates.add(method);
			}
		}
		for (DataMethodNamed method : dataMethodsNamedduplicates) {
			error(MessageFormat.format(DUPLICATE_METHOD, method.getName()), method, Literals.DATA_METHOD_NAMED__NAME,
					PooslIssueCodes.DUPLICATE_METHOD_NAME);
		}

		List<DataMethodUnaryOperator> dataMethodsUnary = dClass.getDataMethodsUnaryOperator();
		Map<String, DataMethodUnaryOperator> dataMethodsUnaryMap = new HashMap<>(
				(int) (dataMethodsUnary.size() * HASHMAP_SIZE_FACTOR));
		Set<DataMethodUnaryOperator> dataMethodsUnaryduplicates = new HashSet<>();
		for (DataMethodUnaryOperator method : dataMethodsUnary) {
			DataMethodUnaryOperator duplicateMethod = dataMethodsUnaryMap
					.put(dataMethod2StringNameAndParameterCount(method.getName().getLiteral(), method), method);
			if (duplicateMethod != null) {
				dataMethodsUnaryduplicates.add(duplicateMethod);
				dataMethodsUnaryduplicates.add(method);
			}
		}
		for (DataMethodUnaryOperator method : dataMethodsUnaryduplicates) {
			error(MessageFormat.format(DUPLICATE_METHOD, method.getName()), method,
					Literals.DATA_METHOD_UNARY_OPERATOR__NAME, PooslIssueCodes.DUPLICATE_METHOD_NAME);
		}

		List<DataMethodBinaryOperator> dataMethodsBinary = dClass.getDataMethodsBinaryOperator();
		Map<String, DataMethodBinaryOperator> dataMethodsBinaryMap = new HashMap<>(
				(int) (dataMethodsBinary.size() * HASHMAP_SIZE_FACTOR));
		Set<DataMethodBinaryOperator> dataMethodsBinaryduplicates = new HashSet<>();
		for (DataMethodBinaryOperator method : dataMethodsBinary) {
			DataMethodBinaryOperator duplicateMethod = dataMethodsBinaryMap
					.put(dataMethod2StringNameAndParameterCount(method.getName().getLiteral(), method), method);
			if (duplicateMethod != null) {
				dataMethodsBinaryduplicates.add(duplicateMethod);
				dataMethodsBinaryduplicates.add(method);
			}
		}
		for (DataMethodBinaryOperator method : dataMethodsBinaryduplicates) {
			error(MessageFormat.format(DUPLICATE_METHOD, method.getName()), method,
					Literals.DATA_METHOD_BINARY_OPERATOR__NAME, PooslIssueCodes.DUPLICATE_METHOD_NAME);
		}
	}

	// --- Variables -------

	public void checkUniqueVariables(ClusterClass cClass) {
		boolean hasError = false;
		Set<String> localVariables = new HashSet<>((int) (cClass.getParameters().size() * HASHMAP_SIZE_FACTOR));
		for (Declaration declaration : cClass.getParameters()) {
			for (Variable variable : declaration.getVariables()) {
				String varName = variable.getName();
				if (localVariables.contains(varName)) {
					hasError = true;
					error(MessageFormat.format(DUPLICATE_VARIABLE, variable.getName()), variable, null, null);
				} else {
					localVariables.add(varName);
				}
			}
		}

		if (!hasError) {
			warnUnusedClusterClassParameters(cClass);
		}
	}

	public void checkUniqueVariables(ProcessClass pClass) {
		Set<String> scopeVars = new HashSet<>();
		String parent = pClass.getSuperClass();
		if (parent != null) {
			scopeVars.addAll(PooslCache.get(pClass.eResource()).getProcessClassParametersAndVariables(parent).keySet());
		}

		boolean hasError = checkOverlapVariablesDeclarations(scopeVars, pClass.getParameters());
		addVariablesToScope(scopeVars, pClass.getParameters());
		hasError = checkOverlapVariablesDeclarations(scopeVars, pClass.getInstanceVariables()) || hasError;
		addVariablesToScope(scopeVars, pClass.getInstanceVariables());

		if (!hasError) {
			warnUnusedProcessClassParametersAndInstanceVariables(pClass);
		}

		for (ProcessMethod processMethod : pClass.getMethods()) {
			checkUniqueVariables(processMethod, scopeVars);
		}
	}

	public boolean checkUniqueVariables(ProcessMethod pMethod, Set<String> scopeVariables) {
		Set<String> methodScope = new HashSet<>(scopeVariables);

		boolean hasError = checkOverlapVariablesDeclarations(methodScope, pMethod.getInputParameters());
		addVariablesToScope(methodScope, pMethod.getInputParameters());

		hasError = checkOverlapVariablesDeclarations(methodScope, pMethod.getLocalVariables()) || hasError;
		addVariablesToScope(methodScope, pMethod.getLocalVariables());

		hasError = checkOverlapVariablesDeclarations(methodScope, pMethod.getOutputParameters()) || hasError;
		if (!hasError) {
			warnUnusedProcessMethodParameterAndLocalVariables(pMethod);
		}
		return hasError;
	}

	public void checkUniqueVariables(DataClass dClass) {
		String parentName = HelperFunctions.getCorrectedDataClassExtendsAsString(dClass);

		Set<String> scopeVariables = new HashSet<>();
		if (parentName != null) {
			scopeVariables.addAll(PooslCache.get(dClass.eResource()).getDataClassVariables(parentName).keySet());
		}

		boolean hasError = checkOverlapVariablesDeclarations(scopeVariables, dClass.getInstanceVariables());

		addVariablesToScope(scopeVariables, dClass.getInstanceVariables());

		if (!hasError) {
			warnUnusedDataClassInstanceVariables(dClass);
		}

		for (DataMethodNamed dataMethod : dClass.getDataMethodsNamed()) {
			hasError = checkUniqueVariables(dataMethod, scopeVariables) || hasError;
		}
		for (DataMethodBinaryOperator dataMethod : dClass.getDataMethodsBinaryOperator()) {
			hasError = checkUniqueVariables(dataMethod, scopeVariables) || hasError;
		}
		for (DataMethodUnaryOperator dataMethod : dClass.getDataMethodsUnaryOperator()) {
			hasError = checkUniqueVariables(dataMethod, scopeVariables) || hasError;
		}

		// check if instance variable are used if the methods and instance
		// Preventing duplicate errors.
	}

	private boolean checkUniqueVariables(DataMethod dMethod, Set<String> scopeVariables) {
		Set<String> methodScope = new HashSet<>(scopeVariables);
		boolean hasError = checkOverlapVariablesDeclarations(methodScope, dMethod.getParameters());

		addVariablesToScope(methodScope, dMethod.getParameters());

		hasError = checkOverlapVariablesDeclarations(methodScope, dMethod.getLocalVariables()) || hasError;

		if (!hasError) {
			warnUnusedDataMethodParameterAndLocalVariables(dMethod);
		}
		return hasError;
	}

	private boolean checkOverlapVariablesDeclarations(Set<String> scopeVariables, List<Declaration> declarations) {
		boolean error = false;
		Set<String> localVariables = new HashSet<>((int) (declarations.size() * HASHMAP_SIZE_FACTOR));

		for (Declaration declaration : declarations) {
			for (Variable variable : declaration.getVariables()) {
				String varName = variable.getName();
				if (scopeVariables.contains(varName)) {
					error = true;
					error(MessageFormat.format(DUPLICATE_VARIABLE, variable.getName()), variable, null, null);
				} else {
					if (localVariables.contains(varName)) {
						error = true;
						error(MessageFormat.format(DUPLICATE_VARIABLE, variable.getName()), variable, null, null);
					} else {
						localVariables.add(varName);
					}
				}
			}
		}
		return error;
	}

	private void addVariablesToScope(Set<String> scope, List<Declaration> declarations) {
		for (Declaration declaration : declarations) {
			for (Variable variable : declaration.getVariables()) {
				scope.add(variable.getName());
			}
		}
	}

	@Check(CheckType.FAST)
	public void checkUniqueOutputVariables(ProcessMethodCall processMethodCall) {
		List<OutputVariable> outputVariables = processMethodCall.getOutputVariables();
		reportDuplicateAssignments(outputVariables);
	}

	@Check(CheckType.FAST)
	public void checkUniqueOutputVariables(ReceiveStatement receiveStatement) {
		List<OutputVariable> outputVariables = receiveStatement.getVariables();
		reportDuplicateAssignments(outputVariables);
	}

	private void reportDuplicateAssignments(List<OutputVariable> outputVariables) {
		Map<String, OutputVariable> duplicateInstances = new HashMap<>(
				(int) (outputVariables.size() * HASHMAP_SIZE_FACTOR));
		Set<OutputVariable> duplicates = new HashSet<>();
		for (OutputVariable outputVariable : outputVariables) {
			String outputName = outputVariable.getVariable();
			if (outputName != null) {
				OutputVariable duplicateVariable = duplicateInstances.put(outputName, outputVariable);
				if (duplicateVariable != null) {
					duplicates.add(duplicateVariable);
					duplicates.add(outputVariable);
				}
			}
		}
		for (OutputVariable outputVariable : duplicates) {
			warning(MessageFormat.format(DUPLICATE_VARIABLE_ASSIGNMENT, outputVariable.getVariable()), outputVariable,
					null, null, WarningType.ASSIGNMENT);
		}
	}

	// --- Message signatures -------

	public void checkUniqueMessageSignatures(ProcessClass pClass, PooslMessageType type) {
		List<MessageSignature> localSignatures = (type == PooslMessageType.RECEIVE) ? pClass.getReceiveMessages()
				: pClass.getSendMessages();

		String superClass = pClass.getSuperClass();
		Iterable<IEObjectDescription> ancestorSignatures = (superClass != null)
				? PooslCache.get(pClass.eResource()).getMessages(superClass, type)
				: null;

		Set<MessageSignature> duplicateSignatures = checkOverlapMessageSignatures(ancestorSignatures, localSignatures);
		String duplicateWarning = (type == PooslMessageType.RECEIVE) ? DUPLICATE_RECEIVE_MESSAGE
				: DUPLICATE_SEND_MESSAGE;
		for (MessageSignature signature : duplicateSignatures) {
			error(duplicateWarning, signature, null, null);
		}
		if (duplicateSignatures.isEmpty()) {
			warnUnusedMessageSignature(pClass, type);
		}
	}

	private Set<MessageSignature> checkOverlapMessageSignatures(Iterable<IEObjectDescription> ancestorSignatures,
			List<MessageSignature> localSignatures) {
		Map<String, Object> signaturesMap = new HashMap<>((int) (localSignatures.size() * HASHMAP_SIZE_FACTOR));
		if (ancestorSignatures != null) {
			for (IEObjectDescription signature : ancestorSignatures) {
				signaturesMap.put(PooslMessageSignatureCallHelper.getSignatureID(signature), signature);
			}
		}
		Set<MessageSignature> duplicateSignatures = new HashSet<>();
		for (MessageSignature signature : localSignatures) {
			String key = PooslMessageSignatureCallHelper.getSignatureID(signature);
			Object duplicateSignature = signaturesMap.put(key, signature);
			if (duplicateSignature != null) {
				duplicateSignatures.add(signature);
				if (duplicateSignature instanceof MessageSignature) {
					duplicateSignatures.add((MessageSignature) duplicateSignature);
				}

				// Put the original signature back in the map so a possible
				// third duplicate will report as a duplicate of the original
				// one.
				signaturesMap.put(key, duplicateSignature);
			}
		}
		return duplicateSignatures;
	}

	private String dataMethod2StringNameAndParameterCount(String name, DataMethod dMethod) {
		return name + "|" + HelperFunctions.computeNumberOfVariables(dMethod.getParameters());
	}

	// --- Annotations ---

	@Check(CheckType.FAST)
	public void checkUniqueAnnotations(DataMethod method) {
		Set<String> annotationNames = new HashSet<>();
		Set<String> duplicateAnnotationNames = new HashSet<>();
		for (Annotation annotation : method.getAnnotations()) {
			final String name = annotation.getName().toLowerCase();

			if (annotationNames.contains(name)) {
				duplicateAnnotationNames.add(name);
			}
			annotationNames.add(name);
		}

		for (Annotation annotation : method.getAnnotations()) {
			if (duplicateAnnotationNames.contains(annotation.getName().toLowerCase())) {
				error(DUPLICATE_ANNOTATION, annotation, Literals.ANNOTATION__NAME);
			}
		}
	}

	// --- Variable reference ---

	@Check(CheckType.FAST)
	public void checkAssignmentExpression(AssignmentExpression assignExpr) {
		IEObjectDescription var = PooslReferenceHelper.getVariableDescription(assignExpr);
		if (var == null) {
			error(MessageFormat.format(NOT_DECLARED, Literals.VARIABLE.getName(),
					(assignExpr.getVariable() != null) ? assignExpr.getVariable() : ""), assignExpr,
					Literals.ASSIGNMENT_EXPRESSION__VARIABLE, Diagnostic.LINKING_DIAGNOSTIC);
		}
	}

	@Check(CheckType.FAST)
	public void checkVariableExpression(VariableExpression varExpr) {
		IEObjectDescription var = PooslReferenceHelper.getVariableDescription(varExpr);
		if (var == null) {
			error(MessageFormat.format(NOT_DECLARED, Literals.VARIABLE.getName(),
					(varExpr.getVariable() != null) ? varExpr.getVariable() : ""), varExpr,
					Literals.VARIABLE_EXPRESSION__VARIABLE, Diagnostic.LINKING_DIAGNOSTIC);
		}
	}

	@Check(CheckType.FAST)
	public void checkOutputVariable(OutputVariable outVar) {
		IEObjectDescription var = PooslReferenceHelper.getVariableDescription(outVar);
		if (var == null) {
			error(MessageFormat.format(NOT_DECLARED, Literals.VARIABLE.getName(),
					(outVar.getVariable() != null) ? outVar.getVariable() : ""), outVar,
					Literals.OUTPUT_VARIABLE__VARIABLE, Diagnostic.LINKING_DIAGNOSTIC);
		}
	}

	@Check(CheckType.FAST)
	public void checkInstanceParameter(InstanceParameter iParam) {
		IEObjectDescription var = PooslReferenceHelper.getVariableDescription(iParam);
		if (var == null) {
			error(MessageFormat.format(NOT_DECLARED, Literals.VARIABLE.getName(),
					(iParam.getParameter() != null) ? iParam.getParameter() : ""), iParam,
					Literals.INSTANCE_PARAMETER__PARAMETER, Diagnostic.LINKING_DIAGNOSTIC);
		}
	}

	// --- Data Class reference ---

	@Check(CheckType.FAST)
	public void checkNewExpression(NewExpression newExpr) {
		IEObjectDescription dClass = PooslReferenceHelper.getDataClassDescription(newExpr);
		if (dClass == null) {
			error(MessageFormat.format(NOT_DECLARED, Literals.DATA_CLASS.getName(), newExpr.getDataClass()), newExpr,
					Literals.NEW_EXPRESSION__DATA_CLASS, Diagnostic.LINKING_DIAGNOSTIC);
		}
	}

	@Check(CheckType.FAST)
	public void checkDataMethod(DataMethod dMethod) {
		IEObjectDescription dClass = PooslReferenceHelper.getDataClassDescription(dMethod);
		if (dClass == null) {
			error(MessageFormat.format(NOT_DECLARED, Literals.DATA_CLASS.getName(), dMethod.getReturnType()), dMethod,
					Literals.DATA_METHOD__RETURN_TYPE, Diagnostic.LINKING_DIAGNOSTIC);
		}
	}

	@Check(CheckType.FAST)
	public void checkDeclaration(Declaration declaration) {
		IEObjectDescription dClass = PooslReferenceHelper.getDataClassDescription(declaration);
		if (dClass == null) {
			error(MessageFormat.format(NOT_DECLARED, Literals.DATA_CLASS.getName(), declaration.getType()), declaration,
					Literals.DECLARATION__TYPE, Diagnostic.LINKING_DIAGNOSTIC);
		}
	}

	@Check(CheckType.FAST)
	public void checkNewExpression(MessageParameter msgParam) {
		IEObjectDescription dClass = PooslReferenceHelper.getDataClassDescription(msgParam);
		if (dClass == null) {
			error(MessageFormat.format(NOT_DECLARED, Literals.DATA_CLASS.getName(), msgParam.getType()), msgParam,
					Literals.MESSAGE_PARAMETER__TYPE, Diagnostic.LINKING_DIAGNOSTIC);
		}
	}
}
