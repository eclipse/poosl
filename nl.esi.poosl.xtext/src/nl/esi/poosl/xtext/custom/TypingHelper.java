package nl.esi.poosl.xtext.custom;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.common.collect.Iterables;

import nl.esi.poosl.Declaration;
import nl.esi.poosl.xtext.descriptions.PooslDataMethodDescription;
import nl.esi.poosl.xtext.helpers.HelperFunctions;

public final class TypingHelper {

	private TypingHelper() {
		throw new IllegalStateException("Utility class");
	}

	public static boolean isCompatible(Resource res, String actualType, String expectedType) {
		if (actualType == null || expectedType == null) {
			return true;
		} else {
			// optimized for actualType being a subtype of expectedType
			return actualType.equals(expectedType)
					|| HelperFunctions.isReflexiveAncestorData(res, expectedType, actualType)
					|| HelperFunctions.isReflexiveAncestorData(res, actualType, expectedType);
		}
	}

	public static boolean isCompatible(Resource res, String actualType, List<String> expectedTypes) {
		if (expectedTypes.size() == 1) {
			return isCompatible(res, actualType, expectedTypes.get(0));
		} else {
			return false;
		}
	}

	public static boolean isCompatible(Resource res, List<String> actualTypes, List<String> expectedTypes) {
		if (expectedTypes.size() == actualTypes.size()) {
			for (int i = 0; i < expectedTypes.size(); i++) {
				if (!isCompatible(res, actualTypes.get(i), expectedTypes.get(i))) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public static boolean isSubtype(Resource res, String actualType, String expectedType) {
		if (actualType == null || expectedType == null) {
			return false;
		} else {
			return actualType.equals(expectedType)
					|| HelperFunctions.isReflexiveAncestorData(res, expectedType, actualType);
		}
	}

	public static boolean isSubtype(Resource res, List<String> actualTypes, List<String> expectedTypes) {
		if (actualTypes.size() == expectedTypes.size()) {
			for (int i = 0; i < actualTypes.size(); i++) {
				String actualType = actualTypes.get(i);
				String expectedType = expectedTypes.get(i);
				if (!isSubtype(res, actualType, expectedType)) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public static List<String> getReturnTypesDataMethodNamed(Resource resource, List<IEObjectDescription> dMethods,
			final String runningClassName, List<String> argumentTypeNames) {
		List<String> returnTypes = new ArrayList<>();
		Iterable<String> classNames = PooslCache.get(resource).getDataReflexiveAncestorsAndChildren(runningClassName);
		for (IEObjectDescription dMethod : dMethods) {
			String dClass = PooslDataMethodDescription.getClassName(dMethod);
			if (dClass != null && Iterables.contains(classNames, dClass)) {
				if (argumentTypeNames.isEmpty()) {
					returnTypes.add(PooslDataMethodDescription.getReturnType(dMethod));
				} else {
					List<String> methodTypes = PooslDataMethodDescription.getParameterTypeNames(dMethod);
					if (isCompatible(resource, argumentTypeNames, methodTypes)) {
						returnTypes.add(PooslDataMethodDescription.getReturnType(dMethod));
					}
				}
			}
		}
		return returnTypes;
	}

	public static List<String> getDeclarationTypeNames(List<Declaration> declarations) {
		List<String> dataNames = new ArrayList<>();
		for (Declaration decl : declarations) {
			String typeName = decl.getType();
			for (int i = 0; i < decl.getVariables().size(); i++) {
				dataNames.add(typeName);
			}
		}
		return dataNames;
	}

	public static String greatestCommonAncestor(Resource resource, List<String> list) {
		if (list.isEmpty()) {
			return null;
		} else {
			String ancestorName = list.remove(0);
			for (String elementName : list) {
				ancestorName = TypingHelper.greatestCommonAncestor(resource, ancestorName, elementName);
			}
			return ancestorName;
		}
	}

	public static String greatestCommonAncestor(Resource resource, String dClass1, String dClass2) {
		if (dClass1 == null || dClass2 == null) {
			return null;
		}
		if (dClass1.equals(dClass2)) {
			return dClass1;
		}

		List<String> listAncestors1 = HelperFunctions.computeDataClassChain(resource, dClass1);
		List<String> listAncestors2 = HelperFunctions.computeDataClassChain(resource, dClass2);

		int size1 = listAncestors1.size();
		int size2 = listAncestors2.size();
		int commonSize = Math.min(size1, size2);

		if ((commonSize > 0) && listAncestors1.get(size1 - 1).equals(listAncestors2.get(size2 - 1))) {
			int index1 = size1 - commonSize;
			int index2 = size2 - commonSize;

			while (!listAncestors1.get(index1).equals(listAncestors2.get(index2))) {
				index1++;
				index2++;
			}
			return listAncestors1.get(index1);
		} else {
			return null;
		}
	}
}
