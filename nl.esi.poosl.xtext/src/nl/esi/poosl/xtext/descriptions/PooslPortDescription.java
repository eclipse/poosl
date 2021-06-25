package nl.esi.poosl.xtext.descriptions;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.common.base.Predicate;

import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.xtext.helpers.PooslValidationHelper;

public class PooslPortDescription {
	private static final String STR_CLASS = "Class";

	private PooslPortDescription() {
		throw new IllegalStateException("Utility class");
	}

	// --- Set -------

	public static Map<String, String> createUserData(String className) {
		return Collections.singletonMap(STR_CLASS, className);
	}

	// --- Get -------

	public static String getClassName(IEObjectDescription descr) {
		if (!checkValidity(descr))
			return null;

		return descr.getUserData(STR_CLASS);
	}

	private static boolean checkValidity(IEObjectDescription descr) {
		return PooslValidationHelper.checkValidity(descr, Literals.PORT);
	}

	// --- Predicates -------

	public static Predicate<IEObjectDescription> predicatePort(final List<String> classes) {
		return new Predicate<IEObjectDescription>() {
			@Override
			public boolean apply(IEObjectDescription input) {
				String cName = getClassName(input);
				return cName != null && classes.contains(cName);
			}
		};
	}

	public static Predicate<IEObjectDescription> predicatePort(final String className) {
		return new Predicate<IEObjectDescription>() {
			@Override
			public boolean apply(IEObjectDescription input) {
				String cName = getClassName(input);
				return cName != null && cName.equals(className);
			}
		};
	}
}
