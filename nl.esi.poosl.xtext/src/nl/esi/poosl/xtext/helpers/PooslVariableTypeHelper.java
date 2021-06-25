package nl.esi.poosl.xtext.helpers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.IEObjectDescription;

import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.Declaration;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.Variable;
import nl.esi.poosl.xtext.custom.PooslCache;
import nl.esi.poosl.xtext.descriptions.PooslDeclarationDescription;
import nl.esi.poosl.xtext.scoping.PooslScopeProvider;

public class PooslVariableTypeHelper {

	private PooslVariableTypeHelper() {
		throw new IllegalStateException("Utility class");
	}

	public static String getVariableType(EObject context, String name) {
		if (context != null && name != null) {
			EObject expressionContainer = getContainer(context);

			if (expressionContainer instanceof DataMethod) {
				DataMethod dMethod = (DataMethod) expressionContainer;
				return getTypeDataMethodParameterOrVariable(dMethod, name);
			} else if (expressionContainer instanceof ProcessMethod) {
				ProcessMethod pMethod = (ProcessMethod) expressionContainer;
				return getTypeProcessMethodParameterOrVariable(pMethod, name);
			} else if (expressionContainer instanceof ProcessClass) {
				ProcessClass pClass = (ProcessClass) expressionContainer;
				return getTypeProcessClassParameterOrVariable(pClass, name);
			} else if (expressionContainer instanceof DataClass) {
				DataClass dClass = (DataClass) expressionContainer;
				return getTypeDataClassVariable(dClass, name);
			} else if (expressionContainer instanceof ClusterClass) {
				ClusterClass cClass = (ClusterClass) expressionContainer;
				return getTypeClusterClassParameter(cClass, name);
			}
		}
		return null;
	}

	private static EObject getContainer(EObject originalObject) {
		EObject object = originalObject;
		while (object != null && !(object instanceof DataClass || object instanceof ProcessClass
				|| object instanceof ClusterClass || object instanceof DataMethod || object instanceof ProcessMethod)) {
			object = object.eContainer();
		}
		return object;
	}

	// --- Parameters and variables -------

	private static String getTypeDataClassVariable(DataClass dClass, String name) {
		String superClass = HelperFunctions.getCorrectedDataClassExtendsAsString(dClass);
		if (superClass != null) {
			IEObjectDescription declarationDescr = PooslCache.get(dClass.eResource()).getDataClassVariables(superClass)
					.get(name);
			if (declarationDescr != null) {
				return PooslDeclarationDescription.getType(declarationDescr);
			}
		}
		Variable var = getVariableFromIterable(PooslScopeProvider.getLocalScopeVariables(dClass), name);
		if (var != null) {
			return ((Declaration) var.eContainer()).getType();
		}
		return null;
	}

	private static String getTypeDataMethodParameterOrVariable(DataMethod dMethod, String name) {
		DataClass dClass = (DataClass) dMethod.eContainer();
		String result = getTypeDataClassVariable(dClass, name);
		if (result == null) {
			Variable var = getVariableFromIterable(PooslScopeProvider.getLocalScopeVariables(dMethod), name);
			if (var != null) {
				return ((Declaration) var.eContainer()).getType();
			}
		}
		return result;
	}

	private static String getTypeClusterClassParameter(ClusterClass cClass, String name) {
		Variable var = getVariableFromIterable(PooslScopeProvider.getLocalScopeParameters(cClass), name);
		if (var != null) {
			return ((Declaration) var.eContainer()).getType();
		}
		return null;
	}

	private static String getTypeProcessClassParameterOrVariable(ProcessClass pClass, String name) {

		if (pClass.getSuperClass() != null) {
			IEObjectDescription descr = PooslCache.get(pClass.eResource())
					.getProcessClassParametersAndVariables(pClass.getSuperClass()).get(name);
			if (descr != null) {
				return PooslDeclarationDescription.getType(descr);
			}
		}

		Variable var = getVariableFromIterable(PooslScopeProvider.getLocalScopeVariables(pClass), name);
		if (var != null) {
			return ((Declaration) var.eContainer()).getType();
		}
		return null;
	}

	private static String getTypeProcessMethodParameterOrVariable(ProcessMethod pMethod, String name) {
		ProcessClass pClass = (ProcessClass) pMethod.eContainer();
		String result = getTypeProcessClassParameterOrVariable(pClass, name);
		if (result == null) {
			Variable var = getVariableFromIterable(PooslScopeProvider.getLocalScopeVariables(pMethod), name);
			if (var != null) {
				return ((Declaration) var.eContainer()).getType();
			}
		}
		return result;
	}

	private static Variable getVariableFromIterable(Iterable<Variable> variables, String name) {
		for (Variable variable : variables) {
			if (variable.getName().equals(name)) {
				return variable;
			}
		}
		return null;
	}
}
