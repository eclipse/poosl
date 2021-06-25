package nl.esi.poosl.xtext.descriptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.IEObjectDescription;

import nl.esi.poosl.Declaration;
import nl.esi.poosl.MessageSignature;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.ProcessMethodCall;
import nl.esi.poosl.ReceiveStatement;
import nl.esi.poosl.SendStatement;
import nl.esi.poosl.Variable;
import nl.esi.poosl.xtext.helpers.PooslMessageSignatureCallHelper;
import nl.esi.poosl.xtext.helpers.PooslProcessMethodParser;
import nl.esi.poosl.xtext.helpers.PooslValidationHelper;

public class PooslProcessClassDescription {
	private static final String STR_INITIAL_METHOD = "initialMethod";
	private static final String STR_USED_PROCESS_METHODS = "UsedProcessMethods";
	private static final String STR_USED_PORTS = "UsedPorts";
	private static final String STR_USED_RECEIVE_STATEMENTS = "UsedReceiveMessages";
	private static final String STR_USED_SEND_STATEMENTS = "UsedSendMessages";

	private static final String PARSER_METHOD_SEPARATOR = ";";
	private static final String PARSER_METHOD_OPENER = "{";

	private PooslProcessClassDescription() {
		throw new IllegalStateException("Utility class");
	}

	// --- Set -------

	public static Map<String, String> createUserData(ProcessClass pClass) {
		Map<String, String> userData = new HashMap<>();
		PooslSuperClassDescription.setSuperClass(userData, pClass.getSuperClass());
		userData.put(STR_INITIAL_METHOD, PooslProcessMethodParser.getProcessMethodID(pClass.getInitialMethodCall()));

		Set<String> usedMethods = new HashSet<>();
		Set<String> usedPorts = new HashSet<>();
		Set<String> usedReceive = new HashSet<>();
		Set<String> usedSend = new HashSet<>();
		Set<String> usedVariables = new HashSet<>();
		getProcessUsedElements(usedMethods, usedPorts, usedReceive, usedSend, usedVariables, pClass);
		if (!usedMethods.isEmpty()) {
			userData.put(STR_USED_PROCESS_METHODS, PooslSuperClassDescription.formatUsedElements(usedMethods));
		}
		if (!usedPorts.isEmpty()) {
			userData.put(STR_USED_PORTS, PooslSuperClassDescription.formatUsedElements(usedPorts));
		}
		if (!usedReceive.isEmpty()) {
			userData.put(STR_USED_RECEIVE_STATEMENTS, PooslSuperClassDescription.formatUsedElements(usedReceive));
		}
		if (!usedSend.isEmpty()) {
			userData.put(STR_USED_SEND_STATEMENTS, PooslSuperClassDescription.formatUsedElements(usedSend));
		}
		PooslSuperClassDescription.setUsedVariables(userData, usedVariables);
		return userData;
	}

	/**
	 * Get the used elements for the process class
	 * 
	 * @param pClass
	 * @return
	 */

	private static void getProcessUsedElements(Set<String> usedMethods, Set<String> usedPorts, Set<String> usedReceive,
			Set<String> usedSend, Set<String> usedVariables, ProcessClass pClass) {
		for (MessageSignature messageSignature : pClass.getReceiveMessages()) {
			usedPorts.add(messageSignature.getPort().getPort());
		}
		for (MessageSignature messageSignature : pClass.getSendMessages()) {
			usedPorts.add(messageSignature.getPort().getPort());
		}

		findUsedElements(null, usedReceive, usedSend, usedVariables, Collections.<String>emptySet(),
				pClass.getInitialMethodCall());

		for (ProcessMethod processMethod : pClass.getMethods()) {
			Set<String> locallyUsedMethods = new HashSet<>();
			findUsedElements(locallyUsedMethods, usedReceive, usedSend, usedVariables, getLocalVariables(processMethod),
					processMethod.getBody());
			usedMethods.add(createUsedMethodsString(processMethod, locallyUsedMethods));
		}
	}

	/**
	 * Find the used elements contained in the eobject and eobject itself wil also
	 * be checked
	 * 
	 * @param startObject  The container to check for used elements
	 * @param usedElements elements created with {@link #initUsedElements()}
	 */
	private static void findUsedElements(Set<String> usedMethods, Set<String> usedReceive, Set<String> usedSend,
			Set<String> usedVariables, Set<String> localVariables, EObject startObject) {
		if (startObject == null)
			return;

		TreeIterator<EObject> allProperContents = EcoreUtil.getAllProperContents(startObject, false);
		addUsedElement(usedMethods, usedReceive, usedSend, usedVariables, startObject, localVariables);
		if (allProperContents != null) {
			while (allProperContents.hasNext()) {
				EObject contentObject = allProperContents.next();
				addUsedElement(usedMethods, usedReceive, usedSend, usedVariables, contentObject, localVariables);
			}

		}
	}

	/**
	 * Adds the name of object to usedElements if interested
	 * 
	 * @param usedElements container created with {@link #initUsedElements()}
	 * @param object       element to add to the usedelements
	 */
	private static void addUsedElement(Set<String> usedMethods, Set<String> usedReceive, Set<String> usedSend,
			Set<String> usedVariables, EObject object, Set<String> localVariables) {
		PooslSuperClassDescription.addUsedVariables(usedVariables, localVariables, object);

		if (object instanceof ProcessMethodCall) {
			if (usedMethods != null) {
				usedMethods.add(PooslProcessMethodParser.getProcessMethodID((ProcessMethodCall) object));
			}
		}

		if (object instanceof ReceiveStatement) {
			ReceiveStatement stat = (ReceiveStatement) object;
			if (stat.getName() != null) {
				PooslMessageSignatureCallHelper usedMessageDescription = new PooslMessageSignatureCallHelper(stat);
				usedReceive.add(usedMessageDescription.toString());
			}
		}

		if (object instanceof SendStatement) {
			SendStatement stat = (SendStatement) object;
			if (stat.getName() != null) {
				PooslMessageSignatureCallHelper usedMessageDescription = new PooslMessageSignatureCallHelper(stat);
				usedSend.add(usedMessageDescription.toString());
			}
		}
	}

	private static String createUsedMethodsString(ProcessMethod method, Set<String> calledMethods) {
		String processMethodID = PooslProcessMethodParser.getProcessMethodID(method);
		StringBuilder usedMethods = new StringBuilder(processMethodID);
		usedMethods.append(PARSER_METHOD_OPENER);

		for (String usedMethod : calledMethods) {
			usedMethods.append(usedMethod + PARSER_METHOD_SEPARATOR);
		}
		return usedMethods.toString();
	}

	/**
	 * Get all local variable names of the processMethod.
	 * 
	 * @param processMethod
	 * @return
	 */
	private static Set<String> getLocalVariables(ProcessMethod processMethod) {
		Set<String> localVariables = new HashSet<>();
		addVariableNames(processMethod.getLocalVariables(), localVariables);
		addVariableNames(processMethod.getInputParameters(), localVariables);
		addVariableNames(processMethod.getOutputParameters(), localVariables);
		return localVariables;
	}

	private static void addVariableNames(List<Declaration> declarations, Set<String> localVariables) {
		for (Declaration decl : declarations) {
			for (Variable variable : decl.getVariables()) {
				localVariables.add(variable.getName());
			}
		}
	}

	// --- Get -------

	public static Map<String, List<String>> getUsedProcessMethods(IEObjectDescription descr) {
		if (!checkValidity(descr))
			return null;

		return parseUsedMethods(descr.getUserData(STR_USED_PROCESS_METHODS));
	}

	public static List<String> getUsedPorts(IEObjectDescription descr) {
		if (!checkValidity(descr))
			return Collections.emptyList();

		return PooslSuperClassDescription.parseUsedElements(descr.getUserData(STR_USED_PORTS));
	}

	public static List<String> getUsedSendStatements(IEObjectDescription descr) {
		if (!checkValidity(descr))
			return Collections.emptyList();

		return PooslSuperClassDescription.parseUsedElements(descr.getUserData(STR_USED_SEND_STATEMENTS));
	}

	public static List<String> getUsedReceiveStatements(IEObjectDescription descr) {
		if (!checkValidity(descr))
			return Collections.emptyList();

		return PooslSuperClassDescription.parseUsedElements(descr.getUserData(STR_USED_RECEIVE_STATEMENTS));
	}

	public static String getInitialMethodCall(IEObjectDescription descr) {
		if (!checkValidity(descr))
			return null;

		return descr.getUserData(STR_INITIAL_METHOD);
	}

	public static String getSuperClass(IEObjectDescription descr) {
		if (!checkValidity(descr))
			return null;

		return PooslSuperClassDescription.getSuperClass(descr);
	}

	public static List<String> getUsedVariables(IEObjectDescription descr) {
		if (!checkValidity(descr))
			return null;

		return PooslSuperClassDescription.getUsedVariables(descr);
	}

	private static boolean checkValidity(IEObjectDescription descr) {
		return PooslValidationHelper.checkValidity(descr, Literals.PROCESS_CLASS);
	}

	private static Map<String, List<String>> parseUsedMethods(String usedMethods) {
		Map<String, List<String>> method2UsedMethods = new HashMap<>();
		for (String method : PooslSuperClassDescription.parseUsedElements(usedMethods)) {
			int open = method.indexOf(PARSER_METHOD_OPENER);
			if (open != -1) {
				String callingMethod = method.substring(0, open);
				String usedNamesString = method.substring(open + 1);
				List<String> methodNames = (usedNamesString.isEmpty()) ? Collections.<String>emptyList()
						: PooslSuperClassDescription.parseUsedElements(usedNamesString, PARSER_METHOD_SEPARATOR);

				method2UsedMethods.put(callingMethod, methodNames);
			}
		}
		return method2UsedMethods;
	}
}
