package nl.esi.poosl.xtext.descriptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import nl.esi.poosl.Annotation;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.xtext.custom.FormattingHelper;
import nl.esi.poosl.xtext.helpers.HelperFunctions;
import nl.esi.poosl.xtext.helpers.PooslValidationHelper;

public class PooslDataMethodDescription {
	private static final String STR_CLASS = "Class";
	private static final String STR_PARAMETERS = "Parameters";
	private static final String STR_RETURNTYPE = "ReturnType";

	private static final String STR_IS_INIT = "isInit";
	private static final String ANNOTATION = "init";

	private PooslDataMethodDescription() {
		throw new IllegalStateException("Utility class");
	}

	// --- Set -------

	public static Map<String, String> createUserData(String className, DataMethod dMethod) {
		Map<String, String> userData = new HashMap<>();
		userData.put(STR_CLASS, className);

		StringBuilder buf = new StringBuilder();
		FormattingHelper.formatDeclarations(buf, dMethod.getParameters());
		userData.put(STR_PARAMETERS, buf.toString());

		String type = dMethod.getReturnType();
		if (type != null) {
			userData.put(STR_RETURNTYPE, type);
		}

		setInitMethod(dMethod, userData);
		return userData;
	}

	private static void setInitMethod(DataMethod dMethod, Map<String, String> userData) {
		for (Annotation annotation : dMethod.getAnnotations()) {
			if (annotation.getName().equalsIgnoreCase(ANNOTATION)) {
				userData.put(STR_IS_INIT, STR_IS_INIT);
			}
		}
	}

	// --- Get -------

	public static String getClassName(IEObjectDescription descr) {
		if (!checkValidity(descr))
			return null;
		return descr.getUserData(STR_CLASS);
	}

	public static String getParameters(IEObjectDescription descr) {
		if (!checkValidity(descr))
			return null;
		return descr.getUserData(STR_PARAMETERS);
	}

	public static List<String> getParameterTypeNames(IEObjectDescription descr) {
		if (!checkValidity(descr))
			return Collections.emptyList();

		List<String> dataNames = new ArrayList<>();
		List<String> declarations = FormattingHelper.unformatDeclarationsToTypeNames(descr.getUserData(STR_PARAMETERS));
		for (String decl : declarations) {
			dataNames.add(decl);
		}
		return dataNames;
	}

	public static String getReturnType(IEObjectDescription descr) {
		if (!checkValidity(descr))
			return null;

		return descr.getUserData(STR_RETURNTYPE);
	}

	public static boolean isInitMethod(IEObjectDescription descr) {
		if (!checkValidity(descr))
			return false;

		return STR_IS_INIT.equals(descr.getUserData(STR_IS_INIT));
	}

	private static boolean checkValidity(IEObjectDescription descr) {
		return PooslValidationHelper.checkValidity(descr, Literals.DATA_METHOD_NAMED,
				Literals.DATA_METHOD_UNARY_OPERATOR, Literals.DATA_METHOD_BINARY_OPERATOR);

	}

	// --- Predicates -------

	public static Predicate<IEObjectDescription> predicateDataMethod(final String name,
			final Iterable<String> classes) {
		return new Predicate<IEObjectDescription>() {
			@Override
			public boolean apply(IEObjectDescription input) {
				String className = getClassName(input);
				return name.equals(HelperFunctions.getName(input)) && className != null
						&& Iterables.contains(classes, className);
			}
		};
	}

	public static Predicate<IEObjectDescription> predicateDataMethod(final String name, final int numberOfArguments) {
		return new Predicate<IEObjectDescription>() {
			@Override
			public boolean apply(IEObjectDescription input) {
				int numberOfParameters = getParameterTypeNames(input).size();
				return name.equals(HelperFunctions.getName(input)) && numberOfArguments == numberOfParameters;
			}
		};
	}

	public static Predicate<IEObjectDescription> predicateDataMethod(final int numberOfArguments,
			final String returnType, final Iterable<String> classes) {
		return new Predicate<IEObjectDescription>() {
			@Override
			public boolean apply(IEObjectDescription input) {
				int numberOfParameters = getParameterTypeNames(input).size();
				String className = getClassName(input);
				String type = getReturnType(input);
				return numberOfArguments == numberOfParameters && type != null && type.equals(returnType)
						&& className != null && Iterables.contains(classes, className);
			}
		};
	}

	public static Predicate<IEObjectDescription> predicateDataMethod(final String name, final int numberOfArguments,
			final Collection<String> notInClasses) {
		return new Predicate<IEObjectDescription>() {
			@Override
			public boolean apply(IEObjectDescription input) {
				int numberOfParameters = getParameterTypeNames(input).size();
				return name.equals(HelperFunctions.getName(input)) && numberOfArguments == numberOfParameters
						&& !notInClasses.contains(getClassName(input));
			}
		};
	}

	public static Predicate<IEObjectDescription> predicateDataMethod(final Iterable<String> classes,
			final EClass eClass) {
		return new Predicate<IEObjectDescription>() {
			@Override
			public boolean apply(IEObjectDescription input) {
				String className = getClassName(input);
				return className != null && Iterables.contains(classes, className) && input.getEClass() == eClass;
			}
		};
	}

	public static Predicate<IEObjectDescription> predicateDataMethod(final String methodName,
			final Iterable<String> classes, final int parameters) {
		return new Predicate<IEObjectDescription>() {
			@Override
			public boolean apply(IEObjectDescription input) {
				String className = getClassName(input);
				return className != null && Iterables.contains(classes, className)
						&& methodName.equals(HelperFunctions.getName(input))
						&& PooslDataMethodDescription.getParameterTypeNames(input).size() == parameters;
			}
		};
	}

	/**
	 * Returns methods with the init annotation
	 * 
	 * @param _class
	 * @return
	 */
	public static Predicate<IEObjectDescription> predicateDataMethodInit(final String _class) {
		return new Predicate<IEObjectDescription>() {
			@Override
			public boolean apply(IEObjectDescription input) {
				return _class.equals(getClassName(input)) && isInitMethod(input);
			}
		};
	}
}
