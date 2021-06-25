package nl.esi.poosl.xtext.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckType;

import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.Declaration;
import nl.esi.poosl.MessageSignature;
import nl.esi.poosl.Poosl;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.Port;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.Variable;
import nl.esi.poosl.xtext.custom.PooslCache;
import nl.esi.poosl.xtext.custom.PooslMessageType;
import nl.esi.poosl.xtext.descriptions.PooslDataClassDescription;
import nl.esi.poosl.xtext.descriptions.PooslProcessClassDescription;
import nl.esi.poosl.xtext.helpers.HelperFunctions;
import nl.esi.poosl.xtext.helpers.PooslMessageSignatureCallHelper;
import nl.esi.poosl.xtext.helpers.PooslProcessMethodParser;

public class PooslJavaValidatorUnusedElements extends PooslJavaValidatorSuppress {
	private static final String METHOD_IS_NOT_USED = "Method is not used";
	private static final String VARIABLE_IS_NOT_USED = "Variable is not used";
	private static final String PORT_IS_NOT_USED = "Port is not used";
	private static final String NO_RECEIVE_STATEMENT = "No receive statement for this message";
	private static final String NO_SEND_STATEMENT = "No send statement for this message";

	// ==============================================================

	// --- Messages -------

	public void warnUnusedMessageSignature(ProcessClass pClass, PooslMessageType type) {
		Set<String> usedMessages = HelperFunctions.getUsedMessages(pClass, type);
		EList<MessageSignature> declaredMessages = (type == PooslMessageType.RECEIVE) ? pClass.getReceiveMessages()
				: pClass.getSendMessages();
		String warning = (type == PooslMessageType.RECEIVE) ? NO_RECEIVE_STATEMENT : NO_SEND_STATEMENT;

		Map<String, MessageSignature> messageIdMap = new HashMap<>();
		for (MessageSignature messageSignature : declaredMessages) {
			messageIdMap.put(PooslMessageSignatureCallHelper.getSignatureID(messageSignature, type), messageSignature);
		}

		for (Entry<String, MessageSignature> entry : messageIdMap.entrySet()) {
			if (!usedMessages.contains(entry.getKey())) {
				warning(warning, entry.getValue(), null, PooslIssueCodes.UNUSED_MESSAGE_SIGNATURE, WarningType.UNUSED);
			}
		}
	}

	// --- Ports -------

	public void warnUnusedPorts(ProcessClass pClass, Port port) {
		List<IEObjectDescription> pClasses = PooslCache.get(pClass.eResource())
				.getProcessReflexiveChildren(pClass.getName());

		for (IEObjectDescription pDescr : pClasses) {
			List<String> usedPorts = PooslProcessClassDescription.getUsedPorts(pDescr);
			if (usedPorts != null && usedPorts.contains(port.getName()))
				return;
		}

		warning(PORT_IS_NOT_USED, port, null, PooslIssueCodes.UNUSED_PROCESS_PORT, WarningType.UNUSED);

	}

	// --- Methods -------

	@Check(CheckType.FAST)
	public void warnUnusedProcessMethod(Poosl poosl) {
		Set<String> usedClassMethods = getAllUsedProcessMethods(poosl);

		for (ProcessClass pClass : poosl.getProcessClasses()) {
			if (pClass.getName() != null) {
				for (ProcessMethod processMethod : pClass.getMethods()) {
					if (!usedClassMethods
							.contains(PooslProcessMethodParser.getProcessMethodIDWithClassName(processMethod))) {
						warning(METHOD_IS_NOT_USED, processMethod, Literals.PROCESS_METHOD__NAME,
								PooslIssueCodes.UNUSED_PROCESS_METHOD, WarningType.UNUSED);
					}
				}
			}
		}
	}

	public Set<String> getAllUsedProcessMethods(Poosl poosl) {
		Resource resource = poosl.eResource();
		List<IEObjectDescription> allRelevantProcessClasses = PooslCache.get(resource).getAllRelevantProcessClasses();

		Map<String, Map<String, List<String>>> class2method2calledMethods = new HashMap<>();
		for (IEObjectDescription pClass : allRelevantProcessClasses) {
			String name = HelperFunctions.getName(pClass);
			class2method2calledMethods.put(name, PooslProcessClassDescription.getUsedProcessMethods(pClass));
		}

		Set<String> methods = new HashSet<>();
		for (IEObjectDescription pClass : allRelevantProcessClasses) {
			methods.addAll(getAllUsedProcessMethods(resource, class2method2calledMethods, pClass));
		}
		return methods;
	}

	private Set<String> getAllUsedProcessMethods(Resource resource,
			Map<String, Map<String, List<String>>> class2method2calledMethods, IEObjectDescription pDescr) {
		String pClassName = HelperFunctions.getName(pDescr);
		List<String> pClasses = HelperFunctions.computeProcessClassChain(resource, pClassName);
		String initial = PooslProcessClassDescription.getInitialMethodCall(pDescr);

		Set<String> processed = new HashSet<>();
		if (initial == null)
			return processed;

		Set<String> methods = new HashSet<>();
		List<String> toBeProcessed = new ArrayList<>();
		toBeProcessed.add(initial);

		while (!toBeProcessed.isEmpty()) {
			String pMethod = toBeProcessed.get(0);
			toBeProcessed.remove(0);
			processed.add(pMethod);

			for (String pClass : pClasses) {
				Map<String, List<String>> method2calledMethods = class2method2calledMethods.get(pClass);
				if (method2calledMethods.containsKey(pMethod)) {
					methods.add(pClass + ":" + pMethod);

					List<String> calledMethods = method2calledMethods.get(pMethod);
					for (String calledMethod : calledMethods) {
						if (!processed.contains(calledMethod) && !toBeProcessed.contains(calledMethod)) {
							toBeProcessed.add(calledMethod);
						}
					}
					break;
				}
			}
		}

		return methods;
	}

	// --- Declarations -------

	public void warnUnusedProcessMethodParameterAndLocalVariables(ProcessMethod pMethod) {
		warnUnusedDeclarations(pMethod.getInputParameters());
		warnUnusedDeclarations(pMethod.getOutputParameters());
		warnUnusedDeclarations(pMethod.getLocalVariables());
	}

	public void warnUnusedDataMethodParameterAndLocalVariables(DataMethod dMethod) {
		// Native methods have no body and don't need unused warnings
		if (!dMethod.isNative()) {
			warnUnusedDeclarations(dMethod.getParameters());
		}
		warnUnusedDeclarations(dMethod.getLocalVariables());
	}

	public void warnUnusedClusterClassParameters(ClusterClass cClass) {
		warnUnusedDeclarations(cClass.getParameters());
	}

	public void warnUnusedProcessClassParametersAndInstanceVariables(ProcessClass pClass) {
		Set<String> variables = new HashSet<>();
		List<IEObjectDescription> pClasses = PooslCache.get(pClass.eResource())
				.getProcessReflexiveChildren(pClass.getName());
		for (IEObjectDescription pDescr : pClasses) {
			variables.addAll(PooslProcessClassDescription.getUsedVariables(pDescr));
		}

		warnUnusedDeclarations(pClass.getParameters(), variables);
		warnUnusedDeclarations(pClass.getInstanceVariables(), variables);
	}

	public void warnUnusedDataClassInstanceVariables(DataClass dClass) {
		Set<String> variables = new HashSet<>();
		List<IEObjectDescription> dClasses = PooslCache.get(dClass.eResource())
				.getDataReflexiveChildren(dClass.getName());
		for (IEObjectDescription dDescr : dClasses) {
			variables.addAll(PooslDataClassDescription.getUsedVariables(dDescr));
		}

		warnUnusedDeclarations(dClass.getInstanceVariables(), variables);
	}

	private void warnUnusedDeclarations(List<Declaration> declarations, Set<String> usedVariables) {
		for (Declaration declaration : declarations) {
			for (Variable variable : declaration.getVariables()) {
				if (!usedVariables.contains(variable.getName())) {
					warning(VARIABLE_IS_NOT_USED, variable, null, PooslIssueCodes.UNUSED_VARIABLE, WarningType.UNUSED);
				}
			}
		}
	}

	private void warnUnusedDeclarations(List<Declaration> declarations) {
		for (Declaration declaration : declarations) {
			for (Variable variable : declaration.getVariables()) {
				if (!isObjectUsed(variable)) {
					warning(VARIABLE_IS_NOT_USED, variable, null, PooslIssueCodes.UNUSED_VARIABLE, WarningType.UNUSED);
				}
			}
		}
	}

	// --- ObjectNotUsed -------

	private boolean isObjectUsed(EObject obj) {
		Resource resource = obj.eResource();
		URI uri = EcoreUtil2.getPlatformResourceOrNormalizedURI(obj);
		return isObjectUsed(resource, uri);
	}

	private boolean isObjectUsed(Resource resource, URI uri) {
		Set<URI> usedURIs = PooslCache.get(resource).getUsedElements();
		return usedURIs.contains(uri);
	}

}
