package nl.esi.poosl.xtext.descriptions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.common.collect.Iterables;

import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.Declaration;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.Variable;
import nl.esi.poosl.xtext.helpers.PooslValidationHelper;

public class PooslDataClassDescription {

	private PooslDataClassDescription() {
		throw new IllegalStateException("Utility class");
	}

	// --- Set -------

	public static Map<String, String> createUserData(DataClass dClass) {
		Map<String, String> userData = new HashMap<>();
		String superClass = dClass.getSuperClass();
		PooslSuperClassDescription.setSuperClass(userData, superClass);

		Set<String> usedVariables = new HashSet<>();
		for (DataMethod dataMethod : getDataMethods(dClass)) {
			if (dataMethod.getBody() != null) {
				findUsedElements(usedVariables, getLocalVariables(dataMethod), dataMethod.getBody());
			}
		}
		PooslSuperClassDescription.setUsedVariables(userData, usedVariables);
		return userData;
	}

	/**
	 * Find the used elements contained in the eobject and eobject itself wil also
	 * be checked
	 * 
	 * @param usedVariables elements created with {@link #initUsedElements()}
	 * @param startObject   The container to check for used elements
	 */
	private static void findUsedElements(Set<String> usedVariables, Set<String> localVariables, EObject startObject) {
		TreeIterator<EObject> allProperContents = EcoreUtil.getAllProperContents(startObject, false);
		PooslSuperClassDescription.addUsedVariables(usedVariables, localVariables, startObject);
		while (allProperContents.hasNext()) {
			EObject contentObject = allProperContents.next();
			PooslSuperClassDescription.addUsedVariables(usedVariables, localVariables, contentObject);
		}
	}

	private static Set<String> getLocalVariables(DataMethod dataMethod) {
		Set<String> localVariables = new HashSet<>();
		addDeclarationsToVariableNames(localVariables, dataMethod.getLocalVariables());
		addDeclarationsToVariableNames(localVariables, dataMethod.getParameters());
		return localVariables;
	}

	private static void addDeclarationsToVariableNames(Set<String> variableNames, List<Declaration> declarations) {
		for (Declaration decl : declarations) {
			for (Variable variable : decl.getVariables()) {
				variableNames.add(variable.getName());
			}
		}
	}

	private static Iterable<DataMethod> getDataMethods(DataClass dClass) {
		return Iterables.concat(dClass.getDataMethodsBinaryOperator(), dClass.getDataMethodsUnaryOperator(),
				dClass.getDataMethodsNamed());
	}

	// --- Get -------

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
		return PooslValidationHelper.checkValidity(descr, Literals.DATA_CLASS);
	}
}
