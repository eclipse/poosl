package nl.esi.poosl.xtext.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.util.Pair;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.DataMethodBinaryOperator;
import nl.esi.poosl.DataMethodNamed;
import nl.esi.poosl.DataMethodUnaryOperator;
import nl.esi.poosl.Import;
import nl.esi.poosl.Poosl;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.xtext.descriptions.PooslClusterClassDescription;
import nl.esi.poosl.xtext.descriptions.PooslDataClassDescription;
import nl.esi.poosl.xtext.descriptions.PooslDataMethodDescription;
import nl.esi.poosl.xtext.descriptions.PooslDeclarationDescription;
import nl.esi.poosl.xtext.descriptions.PooslDescription;
import nl.esi.poosl.xtext.descriptions.PooslMessageSignatureDescription;
import nl.esi.poosl.xtext.descriptions.PooslPortDescription;
import nl.esi.poosl.xtext.descriptions.PooslProcessClassDescription;
import nl.esi.poosl.xtext.descriptions.PooslProcessMethodDescription;
import nl.esi.poosl.xtext.helpers.HelperFunctions;
import nl.esi.poosl.xtext.importing.ImportingHelper;
import nl.esi.poosl.xtext.importing.PooslResourceDescription;

public class PooslCacheEntry {
	private static final String ID_SEPARATOR = "|";

	private final Resource resource;

	private List<IEObjectDescription> allRelevantProcessClasses;
	private List<IEObjectDescription> allRelevantClusterClasses;
	private List<IEObjectDescription> allRelevantDataClasses;

	private Map<String, IEObjectDescription> processClassMap;
	private Map<String, IEObjectDescription> clusterClassMap;
	private Map<String, IEObjectDescription> dataClassMap;

	private Map<String, ProcessClass> localProcessClasses;
	private Map<String, DataClass> localDataClasses;
	private Map<String, ClusterClass> localClusterClasses;

	private Map<Import, List<IEObjectDescription>> importProcessClasses;
	private Map<Import, List<IEObjectDescription>> importClusterClasses;
	private Map<Import, List<IEObjectDescription>> importDataclasses;
	private Map<Import, Set<String>> resolvedImportsPerImport;

	// imported importlibs, Map with pair of resolved and unresolved importlib
	// strings and the original containing file URI
	private Map<Import, Map<Pair<String, String>, URI>> importLibsPerImport;

	private final Map<String, Map<String, ProcessClass>> localProcessAncestors = new HashMap<>();
	private final Map<String, Map<String, DataClass>> localDataAncestors = new HashMap<>();
	private final Map<ProcessMethod, IEObjectDescription> localProcessMethodDescriptions = new HashMap<>();
	private final Map<DataMethod, IEObjectDescription> localDataMethodDescriptions = new HashMap<>();

	private final Map<String, List<IEObjectDescription>> dataReflexiveChildren = new HashMap<>();
	private final Map<String, List<IEObjectDescription>> dataAncestors = new HashMap<>();
	private final Map<String, List<IEObjectDescription>> processReflexiveChildren = new HashMap<>();
	private final Map<String, List<IEObjectDescription>> processAncestors = new HashMap<>();
	private final Map<String, List<IEObjectDescription>> messages = new HashMap<>();
	private final Map<String, List<IEObjectDescription>> datamethods = new HashMap<>();

	private final Map<String, Map<String, IEObjectDescription>> dataClassVariables = new HashMap<>();
	private final Map<String, Map<String, IEObjectDescription>> instantiableClassPorts = new HashMap<>();
	private final Map<String, Map<String, IEObjectDescription>> instantiableClassParameters = new HashMap<>();
	private final Map<String, Map<String, IEObjectDescription>> processClassParametersAndVariables = new HashMap<>();
	private final Map<String, Map<String, IEObjectDescription>> dataConversionMethods = new HashMap<>();

	private final Map<String, Map<String, IEObjectDescription>> processMethodsByCall = new HashMap<>();
	private final Map<String, List<IEObjectDescription>> processMethodsByClass = new HashMap<>();
	private final Map<String, List<IEObjectDescription>> reflexiveChildrenProcessMethods = new HashMap<>();

	private final Map<String, List<IEObjectDescription>> dataMethodsByNameAndNumberOfArguments = new HashMap<>();

	private Set<URI> usedURIs;

	public PooslCacheEntry(Resource resource) {
		this.resource = resource;
	}

	// ----------------------- get Process Classes

	public List<IEObjectDescription> getAllRelevantProcessClasses() {
		if (allRelevantProcessClasses == null) {
			allRelevantProcessClasses = new ArrayList<>();
			importProcessClasses = new HashMap<>();

			// Classes in the current resource (globalScope cannot be used)
			for (ProcessClass pClass : ImportingHelper.toPoosl(resource).getProcessClasses()) {
				String pName = pClass.getName();
				if (pName != null) {
					allRelevantProcessClasses.add(EObjectDescription.create(pName, pClass,
							PooslProcessClassDescription.createUserData(pClass)));
				}
			}

			// Classes in other resources (based on globalScope)
			for (IEObjectDescription ieObjectDescription : HelperFunctions
					.getGlobalScope(resource, Literals.POOSL__PROCESS_CLASSES, null).getAllElements()) {
				URI resURI = ieObjectDescription.getEObjectURI().trimFragment();
				if (!resURI.equals(resource.getURI())) {
					allRelevantProcessClasses.add(ieObjectDescription);
					// store them based on which import imports it
					mapDescriptionToImport(ieObjectDescription, resURI, importProcessClasses);
				}
			}
		}

		return allRelevantProcessClasses;
	}

	public IEObjectDescription getProcessClass(String pClassName) {
		if (processClassMap == null) {
			processClassMap = createEObjectDescriptionMap(getAllRelevantProcessClasses());
		}
		return processClassMap.get(pClassName);
	}

	public IEObjectDescription getDataClass(String dClassName) {
		return getDataClassMap().get(dClassName);
	}

	public Map<Import, List<IEObjectDescription>> getImportedProcessClasses() {
		if (importProcessClasses == null) {
			getAllRelevantProcessClasses();
		}
		return importProcessClasses;
	}

	// ----------------------- get Cluster Classes

	public List<IEObjectDescription> getAllRelevantClusterClasses() {
		if (allRelevantClusterClasses == null) {
			allRelevantClusterClasses = new ArrayList<>();
			importClusterClasses = new HashMap<>();

			// Classes in the current resource (globalScope cannot be used)
			for (ClusterClass cClass : ImportingHelper.toPoosl(resource).getClusterClasses()) {
				String cName = cClass.getName();
				if (cName != null) {
					allRelevantClusterClasses.add(EObjectDescription.create(cName, cClass,
							PooslClusterClassDescription.createUserData(cClass)));
				}
			}

			// Classes in other resources (based on globalScope)
			for (IEObjectDescription ieObjectDescription : HelperFunctions
					.getGlobalScope(resource, Literals.POOSL__CLUSTER_CLASSES, null).getAllElements()) {
				URI resURI = ieObjectDescription.getEObjectURI().trimFragment();
				if (!resURI.equals(resource.getURI())) {
					allRelevantClusterClasses.add(ieObjectDescription);
					// store them based on which import imports it
					mapDescriptionToImport(ieObjectDescription, resURI, importClusterClasses);
				}
			}
		}
		return allRelevantClusterClasses;
	}

	private void mapDescriptionToImport(IEObjectDescription ieObjectDescription, URI resURI,
			Map<Import, List<IEObjectDescription>> importClasses) {
		for (Import importing : getImportsUsingUri(resURI)) {
			List<IEObjectDescription> list = importClasses.get(importing);
			if (list == null) {
				list = new ArrayList<>();
				importClasses.put(importing, list);
			}
			list.add(ieObjectDescription);
		}
	}

	public IEObjectDescription getClusterClass(String cClassName) {
		if (clusterClassMap == null) {
			clusterClassMap = createEObjectDescriptionMap(getAllRelevantClusterClasses());
		}
		return clusterClassMap.get(cClassName);
	}

	private Map<String, ClusterClass> getLocalClusterClasses() {
		if (localClusterClasses == null) {
			localClusterClasses = new HashMap<>();
			for (ClusterClass cClass : ImportingHelper.toPoosl(resource).getClusterClasses()) {
				localClusterClasses.put(cClass.getName(), cClass);
			}
		}
		return localClusterClasses;
	}

	public Map<Import, List<IEObjectDescription>> getImportedClusterClasses() {
		if (importClusterClasses == null) {
			getAllRelevantClusterClasses();
		}
		return importClusterClasses;
	}

	// ----------------------- get Data Classes

	public Iterable<IEObjectDescription> getAllRelevantDataClasses() {
		if (allRelevantDataClasses == null) {
			allRelevantDataClasses = new ArrayList<>();
			importDataclasses = new HashMap<>();

			// Classes in the current resource (globalScope cannot be used)
			Poosl poosl = ImportingHelper.toPoosl(resource);
			for (DataClass dClass : poosl.getDataClasses()) {
				String dName = dClass.getName();
				if (dName != null) {
					allRelevantDataClasses.add(
							EObjectDescription.create(dName, dClass, PooslDataClassDescription.createUserData(dClass)));
				}
			}

			// Classes in other resources (based on globalScope)
			for (IEObjectDescription ieObjectDescription : HelperFunctions
					.getGlobalScope(resource, Literals.POOSL__DATA_CLASSES, null).getAllElements()) {
				URI resURI = ieObjectDescription.getEObjectURI().trimFragment();
				if (!resURI.equals(resource.getURI())) {
					allRelevantDataClasses.add(ieObjectDescription);
					// store them based on which import imports it
					mapDescriptionToImport(ieObjectDescription, resURI, importDataclasses);
				}
			}
		}
		return allRelevantDataClasses;
	}

	public Map<String, IEObjectDescription> getDataClassMap() {
		if (dataClassMap == null) {
			dataClassMap = createEObjectDescriptionMap(getAllRelevantDataClasses());
		}
		return dataClassMap;
	}

	public Map<Import, List<IEObjectDescription>> getImportedDataClasses() {
		if (importDataclasses == null) {
			getAllRelevantDataClasses();
		}
		return importDataclasses;
	}

	// -------------------------- Data Class

	public List<IEObjectDescription> getDataReflexiveChildren(String dClassName) {
		List<IEObjectDescription> results = dataReflexiveChildren.get(dClassName);
		if (results == null) {
			results = new ArrayList<>();

			IEObjectDescription dClass = getDataClassMap().get(dClassName);
			if (dClass != null) {
				Map<String, Set<IEObjectDescription>> directChildren = new HashMap<>();
				for (IEObjectDescription descr : getAllRelevantDataClasses()) {
					String parentName = HelperFunctions.getCorrectedDataClassExtendsAsString(descr);
					if (parentName != null) {
						Set<IEObjectDescription> parentChildDescrs = directChildren.get(parentName);
						if (parentChildDescrs == null) {
							parentChildDescrs = new HashSet<>();
							directChildren.put(parentName, parentChildDescrs);
						}
						parentChildDescrs.add(descr);
					}
				}

				results.add(dClass);
				findAllChildren(resource, directChildren, dClass, results);
				dataReflexiveChildren.put(dClassName, results);
			}
		}
		return results;
	}

	public List<IEObjectDescription> getDataAncestors(String dClassName) {
		List<IEObjectDescription> results = dataAncestors.get(dClassName);
		if (results == null) {
			results = new ArrayList<>();

			IEObjectDescription dClass = getDataClassMap().get(dClassName);
			if (dClass != null) {
				IEObjectDescription parent = getCorrectedDataClassExtends(resource, dClass);
				while (parent != null && !results.contains(parent) && parent != dClass) {
					results.add(parent);
					parent = getCorrectedDataClassExtends(resource, parent);
				}
				dataAncestors.put(dClassName, results);
			}
		}
		return results;
	}

	private IEObjectDescription getCorrectedDataClassExtends(Resource resource, IEObjectDescription dClass) {
		String resultAsString = HelperFunctions.getCorrectedDataClassExtendsAsString(dClass);
		if (resultAsString == null) {
			return null;
		} else {
			return getDataClassMap().get(resultAsString);
		}
	}

	public Iterable<String> getDataReflexiveAncestorsAndChildren(String dClassName) {
		return Iterables.concat(HelperFunctions.getNames(getDataReflexiveChildren(dClassName)),
				HelperFunctions.getNames(getDataAncestors(dClassName)));
	}

	private Map<String, DataClass> getLocalDataClasses() {
		if (localDataClasses == null) {
			localDataClasses = new HashMap<>();
			for (DataClass dClass : ImportingHelper.toPoosl(resource).getDataClasses()) {
				localDataClasses.put(dClass.getName(), dClass);
			}
		}
		return localDataClasses;
	}

	private Map<String, DataClass> getLocalDataAncestors(String dClassName) {
		Map<String, DataClass> localAncestors = localDataAncestors.get(dClassName);
		if (localAncestors == null) {
			localAncestors = new HashMap<>();
			for (String ancestor : HelperFunctions.computeDataClassChain(resource, dClassName)) {
				DataClass localAncestor = getLocalDataClasses().get(ancestor);
				if (localAncestor != null) {
					localAncestors.put(ancestor, localAncestor);
				}
			}
			localDataAncestors.put(dClassName, localAncestors);
		}
		return localAncestors;
	}

	// -------------------------- Process Class

	public List<IEObjectDescription> getProcessReflexiveChildren(String pClassName) {
		List<IEObjectDescription> results = processReflexiveChildren.get(pClassName);
		if (results == null) {
			results = new ArrayList<>();

			IEObjectDescription pClass = getProcessClass(pClassName);
			if (pClass != null) {
				Map<String, Set<IEObjectDescription>> directChildren = new HashMap<>();
				for (IEObjectDescription descr : getAllRelevantProcessClasses()) {
					String parentName = PooslProcessClassDescription.getSuperClass(descr);
					if (parentName != null) {
						Set<IEObjectDescription> parentChildDescrs = directChildren.get(parentName);
						if (parentChildDescrs == null) {
							parentChildDescrs = new HashSet<>();
							directChildren.put(parentName, parentChildDescrs);
						}
						parentChildDescrs.add(descr);
					}
				}

				results.add(pClass);
				findAllChildren(resource, directChildren, pClass, results);
				processReflexiveChildren.put(pClassName, results);
			}
		}
		return results;
	}

	public List<IEObjectDescription> getProcessAncestors(String pClassName) {
		List<IEObjectDescription> results = processAncestors.get(pClassName);
		if (results == null) {
			results = new ArrayList<>();

			IEObjectDescription pClass = getProcessClass(pClassName);
			if (pClass != null) {
				IEObjectDescription parent = getProcessClass(PooslProcessClassDescription.getSuperClass(pClass));
				while (parent != null && !results.contains(parent) && parent != pClass) {
					results.add(parent);
					parent = getProcessClass(PooslProcessClassDescription.getSuperClass(parent));
				}
				processAncestors.put(pClassName, results);
			}
		}
		return results;
	}

	private Map<String, ProcessClass> getLocalProcessClasses() {
		if (localProcessClasses == null) {
			localProcessClasses = new HashMap<>();
			for (ProcessClass pClass : ImportingHelper.toPoosl(resource).getProcessClasses()) {
				localProcessClasses.put(pClass.getName(), pClass);
			}
		}
		return localProcessClasses;
	}

	private Map<String, ProcessClass> getLocalProcessAncestors(String pClassName) {
		Map<String, ProcessClass> localAncestors = localProcessAncestors.get(pClassName);
		if (localAncestors == null) {
			localAncestors = new HashMap<>();
			for (String ancestor : HelperFunctions.computeProcessClassChain(resource, pClassName)) {
				ProcessClass localCancestor = getLocalProcessClasses().get(ancestor);
				if (localCancestor != null) {
					localAncestors.put(ancestor, localCancestor);
				}
			}
			localProcessAncestors.put(pClassName, localAncestors);
		}
		return localAncestors;
	}

	// ------------ get Used Elements

	public Set<URI> getUsedElements() {
		if (usedURIs == null) {
			usedURIs = HelperFunctions.getLocalUsedURIs(resource);
		}
		return usedURIs;
	}

	// ------------ get Scopes

	public Map<String, IEObjectDescription> getDataClassVariables(String dClass) {
		Map<String, IEObjectDescription> map = dataClassVariables.get(dClass);
		if (map == null) {
			List<String> classChain = HelperFunctions.computeDataClassChain(resource, dClass);

			// Classes in the current resource (globalScope cannot be used)
			List<IEObjectDescription> localClassVariableDeclarations = new ArrayList<>();
			for (Entry<String, DataClass> localEntry : getLocalDataAncestors(dClass).entrySet()) {
				String className = localEntry.getKey();
				DataClass localClass = localEntry.getValue();
				PooslResourceDescription.computeExportedDeclarations(localClassVariableDeclarations,
						localClass.getInstanceVariables(), className, true, false);
				classChain.remove(className);
			}

			// Classes in other resources (based on globalScope)
			IScope globalClassVariableDeclartions = HelperFunctions.getGlobalScope(resource, Literals.VARIABLE,
					PooslDeclarationDescription.predicateVariableFromDataClass(classChain));

			map = createEObjectDescriptionMapSupportDuplicateNames(localClassVariableDeclarations);
			map.putAll(createEObjectDescriptionMap(globalClassVariableDeclartions.getAllElements()));
			dataClassVariables.put(dClass, map);
		}
		return map;
	}

	public Map<String, IEObjectDescription> getInstantiableClassPorts(String iClass) {
		Map<String, IEObjectDescription> map = instantiableClassPorts.get(iClass);
		if (map == null) {
			IEObjectDescription pClass = getProcessClass(iClass);
			if (pClass != null) {
				List<String> classChain = HelperFunctions.computeProcessClassChain(resource, iClass);

				// Classes in the current resource (globalScope cannot be used)
				List<IEObjectDescription> localPortDeclarations = new ArrayList<>();
				for (ProcessClass localClass : getLocalProcessAncestors(iClass).values()) {
					String localClassName = localClass.getName();
					Iterables.addAll(localPortDeclarations,
							PooslResourceDescription.computeExportedPorts(localClass.getPorts(), localClassName));
					classChain.remove(localClassName);
				}

				// Classes in other resources (based on globalScope)
				IScope globalPortDeclarations = HelperFunctions.getGlobalScope(resource, Literals.PORT,
						PooslPortDescription.predicatePort(classChain));

				map = createEObjectDescriptionMapSupportDuplicateNames(localPortDeclarations);
				map.putAll(createEObjectDescriptionMap(globalPortDeclarations.getAllElements()));
			} else {
				ClusterClass localClass = getLocalClusterClasses().get(iClass);
				if (localClass != null) {
					map = createEObjectDescriptionMapSupportDuplicateNames(
							PooslResourceDescription.computeExportedPorts(localClass.getPorts(), iClass));
				} else {
					IScope globalPortDeclarations = HelperFunctions.getGlobalScope(resource, Literals.PORT,
							PooslPortDescription.predicatePort(iClass));
					map = createEObjectDescriptionMap(globalPortDeclarations.getAllElements());
				}
			}
			instantiableClassPorts.put(iClass, map);
		}
		return map;
	}

	public Map<String, IEObjectDescription> getProcessClassParametersAndVariables(String pClass) {
		Map<String, IEObjectDescription> map = processClassParametersAndVariables.get(pClass);
		if (map == null) {
			List<String> classChain = HelperFunctions.computeProcessClassChain(resource, pClass);

			// Classes in the current resource (globalScope cannot be used)
			List<IEObjectDescription> localObjects = new ArrayList<>();
			for (ProcessClass localClass : getLocalProcessAncestors(pClass).values()) {
				PooslResourceDescription.computeExportedDeclarations(localObjects, localClass.getParameters(),
						localClass.getName(), false, true);
				PooslResourceDescription.computeExportedDeclarations(localObjects, localClass.getInstanceVariables(),
						localClass.getName(), false, false);
				classChain.remove(localClass.getName());
			}

			// Classes in other resources (based on globalScope)
			IScope globalObjects = HelperFunctions.getGlobalScope(resource, Literals.VARIABLE,
					PooslDeclarationDescription.predicateParameterAndVariableFromNonDataClass(classChain));

			map = createEObjectDescriptionMapSupportDuplicateNames(localObjects);
			map.putAll(createEObjectDescriptionMap(globalObjects.getAllElements()));
			processClassParametersAndVariables.put(pClass, map);
		}
		return map;
	}

	public Map<String, IEObjectDescription> getInstantiableClassParameters(String iClass) {
		Map<String, IEObjectDescription> map = instantiableClassParameters.get(iClass);
		if (map == null) {
			IEObjectDescription processClass = getProcessClass(iClass);
			if (processClass != null) {
				List<String> classChain = HelperFunctions.computeProcessClassChain(resource, iClass);

				// Classes in the current resource (globalScope cannot be used)
				List<IEObjectDescription> localParameterDeclarations = new ArrayList<>();
				for (ProcessClass localClass : getLocalProcessAncestors(iClass).values()) {
					PooslResourceDescription.computeExportedDeclarations(localParameterDeclarations,
							localClass.getParameters(), localClass.getName(), false, true);
					classChain.remove(localClass.getName());
				}

				// Classes in other resources (based on globalScope)
				IScope globalParameterDeclarations = HelperFunctions.getGlobalScope(resource, Literals.VARIABLE,
						PooslDeclarationDescription.predicateParameterFromNonDataClass(classChain));

				map = createEObjectDescriptionMapSupportDuplicateNames(localParameterDeclarations);
				map.putAll(createEObjectDescriptionMap(globalParameterDeclarations.getAllElements()));
			} else {
				ClusterClass localClass = getLocalClusterClasses().get(iClass);
				if (localClass != null) {
					List<IEObjectDescription> localParameterDeclarations = new ArrayList<>();
					PooslResourceDescription.computeExportedDeclarations(localParameterDeclarations,
							localClass.getParameters(), localClass.getName(), false, true);
					map = createEObjectDescriptionMapSupportDuplicateNames(localParameterDeclarations);
				} else {
					IScope globalParameterDeclarations = HelperFunctions.getGlobalScope(resource, Literals.VARIABLE,
							PooslDeclarationDescription.predicateParameterFromNonDataClass(iClass));
					map = createEObjectDescriptionMap(globalParameterDeclarations.getAllElements());
				}
			}
			instantiableClassParameters.put(iClass, map);
		}
		return map;
	}

	public Map<String, IEObjectDescription> getProcessMethods(String pClass, int args, int outputVar) {
		String id = pClass + ID_SEPARATOR + args + ID_SEPARATOR + outputVar;
		Map<String, IEObjectDescription> map = processMethodsByCall.get(id);

		if (map == null) {
			map = new HashMap<>();
			List<String> classChain = HelperFunctions.computeProcessClassChain(resource, pClass);

			// Have to iterate the classchain to add only the first method if
			// any methods of the same name exist.
			Map<String, ProcessClass> localAncestors = getLocalProcessAncestors(pClass);
			for (String className : classChain) {
				ProcessClass localAncestor = localAncestors.get(className);
				if (localAncestor != null) {
					Set<String> addedNames = new HashSet<>();
					for (ProcessMethod pMethod : localAncestor.getMethods()) {
						String pName = pMethod.getName();
						boolean containsKey = map.containsKey(pName);
						if (HelperFunctions.computeNumberOfVariables(pMethod.getInputParameters()) == args
								&& HelperFunctions.computeNumberOfVariables(pMethod.getOutputParameters()) == outputVar
								&& (!containsKey || addedNames.contains(pName))) {
							IEObjectDescription descr = getLocalProcessMethodDescription(pMethod,
									localAncestor.getName());
							// add methods with the same name,
							if (descr != null) {
								String descrID = getDescriptionIDWithDouble(containsKey, map, pName);
								addedNames.add(pName);
								map.put(descrID, EObjectDescription.create(pName, pMethod,
										PooslProcessMethodDescription.createUserData(pClass, pMethod)));
							}
						}
					}

				} else {
					IScope localMethods = HelperFunctions.getGlobalScope(resource, Literals.PROCESS_CLASS__METHODS,
							PooslProcessMethodDescription.predicateMethod(args, outputVar, className));
					for (IEObjectDescription pMethod : localMethods.getAllElements()) {
						String pMethodName = HelperFunctions.getName(pMethod);
						if (!map.containsKey(pMethodName)) {
							map.put(pMethodName, pMethod);
						}
					}
				}
			}
			processMethodsByCall.put(id, map);
		}
		return map;
	}

	/**
	 * If process method is already stored add an integer to the process method name
	 * 
	 * @param containsKey
	 * @param map
	 * @param pName
	 * @return
	 */
	private String getDescriptionIDWithDouble(boolean containsKey, Map<String, IEObjectDescription> map, String pName) {
		if (containsKey) {
			int i = 0;
			String descrID;
			do {
				descrID = pName + ID_SEPARATOR + i++;
			} while (map.containsKey(descrID));
			return descrID;
		}
		return pName;
	}

	public List<IEObjectDescription> getProcessMethods(String pClass) {
		List<IEObjectDescription> list = processMethodsByClass.get(pClass);
		if (list == null) {
			list = new ArrayList<>();
			List<String> classChain = HelperFunctions.computeProcessClassChain(resource, pClass);
			Set<String> filteredNames = new HashSet<>();
			Map<String, ProcessClass> localAncestors = getLocalProcessAncestors(pClass);

			for (String className : classChain) {
				ProcessClass localAncestor = localAncestors.get(className);
				if (localAncestor != null) {
					for (ProcessMethod pMethod : localAncestor.getMethods()) {
						if (filteredNames.add(pMethod.getName())) {
							IEObjectDescription descr = getLocalProcessMethodDescription(pMethod,
									localAncestor.getName());
							if (descr != null) {
								list.add(descr);
							}
						}
					}
				} else {
					IScope localMethods = HelperFunctions.getGlobalScope(resource, Literals.PROCESS_METHOD,
							PooslProcessMethodDescription.predicateMethod(className));
					for (IEObjectDescription pMethod : localMethods.getAllElements()) {
						if (filteredNames.add(HelperFunctions.getName(pMethod))) {
							list.add(pMethod);
						}
					}
				}
			}

			processMethodsByClass.put(pClass, list);
		}
		return list;
	}

	public List<IEObjectDescription> getReflexiveChildrenProcessMethods(String pClass) {
		List<IEObjectDescription> list = reflexiveChildrenProcessMethods.get(pClass);
		if (list == null) {
			list = new ArrayList<>();
			Iterable<IEObjectDescription> listProcessClasses = PooslCache.get(resource)
					.getProcessReflexiveChildren(pClass);
			List<String> names = Lists.newArrayList(HelperFunctions.getNames(listProcessClasses));

			// Iterate directly over reflexive children because the local
			// reflexive children are searched only ones and not cached (unlike
			// the local ancestors)
			// Classes in the current resource (globalScope cannot be used)
			Map<String, ProcessClass> local = getLocalProcessClasses();
			for (Iterator<String> iterator = names.iterator(); iterator.hasNext();) {
				String pName = iterator.next();
				ProcessClass localClass = local.get(pName);
				if (localClass != null) {
					for (ProcessMethod pMethod : localClass.getMethods()) {
						IEObjectDescription descr = getLocalProcessMethodDescription(pMethod, localClass.getName());
						if (descr != null) {
							list.add(descr);
						}
					}
					iterator.remove();
				}
			}

			// Classes in other resources (based on globalScope)
			Iterables.addAll(list, HelperFunctions.getGlobalScope(resource, Literals.PROCESS_CLASS__METHODS,
					PooslProcessMethodDescription.predicateMethod(names)).getAllElements());

			reflexiveChildrenProcessMethods.put(pClass, list);
		}
		return list;
	}

	public List<IEObjectDescription> getMessages(String pClassName, PooslMessageType type) {
		String id = pClassName + ID_SEPARATOR + type.toString();
		List<IEObjectDescription> descriptions = messages.get(id);
		if (descriptions == null) {
			List<String> classChain = HelperFunctions.computeProcessClassChain(resource, pClassName);
			List<IEObjectDescription> receive = new ArrayList<>();
			List<IEObjectDescription> send = new ArrayList<>();

			// Classes in the current resource (globalScope cannot be used)
			for (ProcessClass localClass : getLocalProcessAncestors(pClassName).values()) {
				PooslResourceDescription.computeExportedMessages(receive, localClass.getReceiveMessages(), pClassName);
				PooslResourceDescription.computeExportedMessages(send, localClass.getSendMessages(), pClassName);
				classChain.remove(localClass.getName());
			}

			// Classes in other resources (based on globalScope)
			IScope globalMessages = HelperFunctions.getGlobalScope(resource, Literals.PROCESS_CLASS__SEND_MESSAGES,
					PooslMessageSignatureDescription.predicateMessage(classChain));
			for (IEObjectDescription descr : globalMessages.getAllElements()) {
				if (PooslMessageSignatureDescription.getMessageType(descr).equals(PooslMessageType.RECEIVE)) {
					receive.add(descr);
				} else {
					send.add(descr);
				}
			}

			messages.put(pClassName + PooslMessageType.RECEIVE, receive);
			messages.put(pClassName + PooslMessageType.SEND, send);
			descriptions = type.equals(PooslMessageType.RECEIVE) ? receive : send;
		}
		return descriptions;
	}

	public List<IEObjectDescription> getDataMethods(String dClassName, String dMethodName, int args,
			EReference literal) {
		String classMethodParam = dClassName + ID_SEPARATOR + dMethodName + ID_SEPARATOR + args;
		List<IEObjectDescription> methods = datamethods.get(classMethodParam);
		if (methods == null) {
			methods = new ArrayList<>();
			List<String> classChain = HelperFunctions.computeDataClassChain(resource, dClassName);

			// Classes in the current resource (globalScope cannot be used)
			for (Entry<String, DataClass> localEntry : getLocalDataAncestors(dClassName).entrySet()) {
				String className = localEntry.getKey();
				DataClass localClass = localEntry.getValue();

				if (literal == Literals.DATA_CLASS__DATA_METHODS_NAMED) {
					for (DataMethodNamed dMethod : localClass.getDataMethodsNamed()) {
						String localName = dMethod.getName();
						if (dMethodName.equals(localName)
								&& HelperFunctions.computeNumberOfVariables(dMethod.getParameters()) == args) {
							methods.add(getLocalDataMethodDescription(localName, dMethod, className));
						}
					}
				}
				if (literal == Literals.DATA_CLASS__DATA_METHODS_BINARY_OPERATOR) {
					for (DataMethodBinaryOperator dMethod : localClass.getDataMethodsBinaryOperator()) {
						String localName = dMethod.getName().getLiteral();
						if (dMethodName.equals(localName)
								&& HelperFunctions.computeNumberOfVariables(dMethod.getParameters()) == args) {
							methods.add(getLocalDataMethodDescription(localName, dMethod, className));
						}
					}
				}
				if (literal == Literals.DATA_CLASS__DATA_METHODS_UNARY_OPERATOR) {
					for (DataMethodUnaryOperator dMethod : localClass.getDataMethodsUnaryOperator()) {
						String localName = dMethod.getName().getLiteral();
						if (dMethodName.equals(localName)
								&& HelperFunctions.computeNumberOfVariables(dMethod.getParameters()) == args) {
							methods.add(getLocalDataMethodDescription(localName, dMethod, className));
						}
					}
				}

				classChain.remove(className);
			}

			// Classes in other resources (based on globalScope)
			IScope scope = HelperFunctions.getGlobalScope(resource, literal,
					PooslDataMethodDescription.predicateDataMethod(dMethodName, classChain, args));
			methods.addAll(Lists.newArrayList(scope.getAllElements()));
			datamethods.put(classMethodParam, methods);

		}
		return methods;
	}

	public List<IEObjectDescription> getDataMethods(String dMethodName, int args, EReference ref) {
		String nameAndNumberOfArguments = dMethodName + ID_SEPARATOR + args;
		List<IEObjectDescription> methods = dataMethodsByNameAndNumberOfArguments.get(nameAndNumberOfArguments);
		if (methods == null) {
			methods = new ArrayList<>();

			// Classes in the current resource (globalScope cannot be used)
			for (DataClass dataClass : getLocalDataClasses().values()) {
				String dName = dataClass.getName();
				if (dName != null) {
					if (ref.equals(Literals.DATA_CLASS__DATA_METHODS_NAMED)) {
						for (DataMethodNamed dMethod : dataClass.getDataMethodsNamed()) {
							String localName = dMethod.getName();
							if (localName.equals(dMethodName)
									&& HelperFunctions.computeNumberOfVariables(dMethod.getParameters()) == args) {
								methods.add(getLocalDataMethodDescription(localName, dMethod, dName));
							}
						}
					} else if (ref.equals(Literals.DATA_CLASS__DATA_METHODS_BINARY_OPERATOR)) {
						for (DataMethodBinaryOperator dMethod : dataClass.getDataMethodsBinaryOperator()) {
							String localName = dMethod.getName().getLiteral();
							if (localName.equals(dMethodName)) {
								methods.add(getLocalDataMethodDescription(localName, dMethod, dName));
							}
						}
					} else if (ref.equals(Literals.DATA_CLASS__DATA_METHODS_UNARY_OPERATOR)) {

						for (DataMethodUnaryOperator dMethod : dataClass.getDataMethodsUnaryOperator()) {
							String localName = dMethod.getName().getLiteral();
							if (localName.equals(dMethodName)) {
								methods.add(getLocalDataMethodDescription(localName, dMethod, dName));
							}
						}
					}
				}
			}

			// Classes in other resources (based on globalScope)
			IScope total = HelperFunctions.getGlobalScope(resource, ref,
					PooslDataMethodDescription.predicateDataMethod(dMethodName, args, getLocalDataClasses().keySet()));

			methods.addAll(Lists.newArrayList(total.getAllElements()));
			dataMethodsByNameAndNumberOfArguments.put(nameAndNumberOfArguments, methods);
		}
		return methods;
	}

	/**
	 * Returns the data methods based on number of arguments and return type. It
	 * allows for computing conversion methods.
	 * 
	 * @param dClassName
	 * @param args
	 * @param returnType
	 * @return Map with the data method descriptions
	 */
	public Map<String, IEObjectDescription> getDataMethodsNamed(String dClassName, int args, String returnType) {
		String id = dClassName + ID_SEPARATOR + args + ID_SEPARATOR + returnType;
		Map<String, IEObjectDescription> map = dataConversionMethods.get(id);
		if (map == null) {
			map = new HashMap<>();
			List<String> classChain = HelperFunctions.computeDataClassChain(resource, dClassName);

			// Classes in the current resource (globalScope cannot be used)
			for (Entry<String, DataClass> entry : getLocalDataAncestors(dClassName).entrySet()) {
				String className = entry.getKey();
				DataClass localClass = entry.getValue();

				for (DataMethodNamed dMethod : localClass.getDataMethodsNamed()) {
					String dMethodName = dMethod.getName();
					String dReturnType = dMethod.getReturnType();
					if (dMethodName != null && dReturnType != null && dReturnType.equals(returnType)
							&& HelperFunctions.computeNumberOfVariables(dMethod.getParameters()) == args) {
						map.put(dMethodName, getLocalDataMethodDescription(dMethodName, dMethod, className));
					}
				}
				classChain.remove(className);
			}

			// Classes in other resources (based on globalScope)
			IScope scope = HelperFunctions.getGlobalScope(resource, Literals.DATA_CLASS__DATA_METHODS_NAMED,
					PooslDataMethodDescription.predicateDataMethod(args, returnType, classChain));
			for (IEObjectDescription description : scope.getAllElements()) {
				map.put(HelperFunctions.getName(description), description);
			}
			dataConversionMethods.put(id, map);
		}
		return map;
	}

	public Map<Import, Set<String>> getResolvedImportsPerImport() {
		if (resolvedImportsPerImport == null) {
			createImportAndImportLibMappings();
		}
		return resolvedImportsPerImport;
	}

	public Map<Import, Map<Pair<String, String>, URI>> getImportLibsPerImport() {
		if (importLibsPerImport == null) {
			createImportAndImportLibMappings();
		}
		return importLibsPerImport;
	}

	private void createImportAndImportLibMappings() {
		resolvedImportsPerImport = new HashMap<>();
		importLibsPerImport = new HashMap<>();

		Map<String, IEObjectDescription> fileString2PooslDescription = new HashMap<>();
		for (IEObjectDescription pooslDescr : HelperFunctions.getGlobalScope(resource, Literals.POOSL, null)
				.getAllElements()) {
			fileString2PooslDescription.put(pooslDescr.getEObjectURI().trimFragment().toString(), pooslDescr);
		}

		Poosl poosl = ImportingHelper.toPoosl(resource);
		addImportMappings(poosl.getImports(), fileString2PooslDescription, resolvedImportsPerImport,
				importLibsPerImport, false);
		addImportMappings(poosl.getImportLibs(), fileString2PooslDescription, resolvedImportsPerImport,
				importLibsPerImport, true);
	}

	private List<Import> getImportsUsingUri(URI importedFile) {
		String importedString = importedFile.toString();

		List<Import> imports = new ArrayList<>();
		for (Entry<Import, Set<String>> entry : getResolvedImportsPerImport().entrySet()) {
			if (entry.getValue().contains(importedString)) {
				imports.add(entry.getKey());
			}
		}
		return imports;
	}

	private static void addImportMappings(List<Import> imports,
			Map<String, IEObjectDescription> fileString2PooslDescription,
			Map<Import, Set<String>> resolvedImportsPerImport,
			Map<Import, Map<Pair<String, String>, URI>> importLibsPerImport, boolean importlib) {
		for (Import pImport : imports) {
			String importString = ImportingHelper.importToString(pImport);
			if (importString != null) {
				URI importUri = URI.createURI(importString);
				URI resolvedURI;
				if (importlib) {
					resolvedURI = ImportingHelper.resolveImportLibUri(pImport.eResource(), importUri);
				} else {
					resolvedURI = ImportingHelper.resolveImportUri(pImport.eResource().getURI(), importUri);
				}

				if (resolvedURI != null) {
					Set<String> resolvedImports = new HashSet<>();
					Map<Pair<String, String>, URI> importLibs = new HashMap<>();
					getImportedfiles(resolvedURI.toString(), resolvedImports, importLibs, fileString2PooslDescription);
					resolvedImportsPerImport.put(pImport, resolvedImports);
					importLibsPerImport.put(pImport, importLibs);
				}
			}
		}
	}

	private static void getImportedfiles(String resolvedFile, Set<String> resolvedImports,
			Map<Pair<String, String>, URI> importLibs, Map<String, IEObjectDescription> pooslDescriptions) {
		if (resolvedFile != null && !resolvedFile.isEmpty() && !resolvedImports.contains(resolvedFile)) {
			resolvedImports.add(resolvedFile);
			IEObjectDescription pooslDescription = pooslDescriptions.get(resolvedFile);
			if (pooslDescription != null) {
				for (String importString : PooslDescription.getImports(pooslDescription)) {
					getImportedfiles(importString, resolvedImports, importLibs, pooslDescriptions);
				}
				for (Pair<String, String> pair : PooslDescription.getImportLibRaw(pooslDescription)) {
					String resolvedImportString = pair.getFirst();
					URI sourceFileOfImportLib = pooslDescription.getEObjectURI();
					importLibs.put(pair, sourceFileOfImportLib);
					getImportedfiles(resolvedImportString, resolvedImports, importLibs, pooslDescriptions);
				}
			}
		}
	}

	private IEObjectDescription getLocalDataMethodDescription(String dMethodName, DataMethod dMethod, String dClass) {
		IEObjectDescription localDescription = localDataMethodDescriptions.get(dMethod);
		if (localDescription == null && dMethodName != null) {
			localDescription = EObjectDescription.create(dMethodName, dMethod,
					PooslDataMethodDescription.createUserData(dClass, dMethod));
			localDataMethodDescriptions.put(dMethod, localDescription);
		}
		return localDescription;
	}

	private IEObjectDescription getLocalProcessMethodDescription(ProcessMethod pMethod, String pClass) {
		IEObjectDescription localDescription = localProcessMethodDescriptions.get(pMethod);
		if (localDescription == null && pClass != null) {
			String pName = pMethod.getName();
			if (pName != null) {
				localDescription = EObjectDescription.create(pName, pMethod,
						PooslProcessMethodDescription.createUserData(pClass, pMethod));
				localProcessMethodDescriptions.put(pMethod, localDescription);
			}
		}
		return localDescription;
	}

	private static Map<String, IEObjectDescription> createEObjectDescriptionMap(
			Iterable<IEObjectDescription> iterable) {
		Map<String, IEObjectDescription> map = new HashMap<>();
		for (IEObjectDescription descr : iterable) {
			map.put(HelperFunctions.getName(descr), descr);
		}
		return map;
	}

	private Map<String, IEObjectDescription> createEObjectDescriptionMapSupportDuplicateNames(
			Iterable<IEObjectDescription> iterable) {
		Map<String, IEObjectDescription> map = new HashMap<>();
		for (IEObjectDescription descr : iterable) {
			String name = HelperFunctions.getName(descr);
			String descrID = getDescriptionIDWithDouble(map.containsKey(name), map, name);
			map.put(descrID, descr);
		}
		return map;
	}

	private static void findAllChildren(Resource resource, Map<String, Set<IEObjectDescription>> directChildren,
			IEObjectDescription descr, List<IEObjectDescription> results) {
		String name = HelperFunctions.getName(descr);
		Set<IEObjectDescription> childDescrs = directChildren.get(name);
		if (childDescrs != null) {
			for (IEObjectDescription childDescr : childDescrs) {
				if (!results.contains(childDescr)) {
					results.add(childDescr);
					findAllChildren(resource, directChildren, childDescr, results);
				}
			}
		}
	}
}
