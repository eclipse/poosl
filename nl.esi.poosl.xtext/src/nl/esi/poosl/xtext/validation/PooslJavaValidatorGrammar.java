package nl.esi.poosl.xtext.validation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckType;

import nl.esi.poosl.Annotation;
import nl.esi.poosl.Channel;
import nl.esi.poosl.CurrentTimeExpression;
import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.DataMethodBinaryOperator;
import nl.esi.poosl.DataMethodCallExpression;
import nl.esi.poosl.Declaration;
import nl.esi.poosl.GuardedStatement;
import nl.esi.poosl.PooslPackage;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ReturnExpression;
import nl.esi.poosl.SelfExpression;
import nl.esi.poosl.xtext.helpers.HelperFunctions;

public class PooslJavaValidatorGrammar extends PooslJavaValidatorTypes {
	private static final String NATIVE_METHOD_EMPTY = "The body of a native data method should be empty";
	private static final String DATA_METHOD_EMPTY = "The body of a non-native data method should not be empty";

	private static final String ILLEGAL_TEST_DATA_METHOD = "Annotation @Test is not allowed before data methods with parameters";

	private static final String NATIVE_METHOD_IN_DATA = "No native method allowed in non-native data class";
	private static final String SELF_IN_DATA = "Expression \"self\" is only allowed in data classes";
	private static final String RETURN_IN_DATA = "Expression \"return\" is only allowed in data classes";

	private static final String UNUSED_CHANNEL_ONE = "Unused channel (only one element)";
	private static final String UNUSED_CHANNEL_NONE = "Unused channel (no elements)";

	private static final String CURRENT_TIME_NOT_ALLOWED = "Expression \"currentTime\" is not allowed inside a guard";
	private static final String CURRENT_TIME_USAGE = "Expression \"currentTime\" is only allowed in process classes";
	private static final String SUPERCLASS_USAGE = "^ can only be preceded by \"self\"";
	private static final String MISSING_VARIABLE = "Missing variable or parameter";
	private static final String DATA_BINARY_1_PARAMETER = "Data methods for binary operators should have exactly 1 parameter";

	private enum Annotations {
		TEST("Test"),
		SKIP("Skip"),
		ERROR("Error"),
		INIT("Init"),
		SUPPRESSWARNINGS("SuppressWarnings");

		private final String text;

		private Annotations(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	@Check(CheckType.FAST)
	public void checkDataMethodBinaryOperator(DataMethodBinaryOperator dMethod) {
		int numberOfParameters = HelperFunctions.computeNumberOfVariables(dMethod.getParameters());
		if (numberOfParameters > 1) {
			error(DATA_BINARY_1_PARAMETER, Literals.DATA_METHOD__PARAMETERS, 1);
		}
	}

	@Check(CheckType.FAST)
	public void checkAtLeastOneVariableInDeclaration(Declaration declaration) {
		if (declaration.getVariables().isEmpty()) {
			error(MISSING_VARIABLE, null);
		}
	}

	@Check(CheckType.FAST)
	public void errorUseOfOnSuperClass(DataMethodCallExpression dmc) {
		if (dmc.isOnSuperClass() && !(dmc.getTarget() instanceof SelfExpression)) {
			error(SUPERCLASS_USAGE, Literals.DATA_METHOD_CALL_EXPRESSION__TARGET);
		}
	}

	@Check(CheckType.FAST)
	public void errorContextOfCurrentTimeExpression(CurrentTimeExpression expr) {
		EObject previous = null;
		EObject current = expr;
		while ((current != null) && !(current instanceof ProcessClass) && !(current instanceof GuardedStatement)) {
			previous = current;
			current = current.eContainer();
		}

		if (current == null) {
			error(CURRENT_TIME_USAGE, null);
		} else {
			if (current instanceof GuardedStatement && previous == ((GuardedStatement) current).getGuard()) {
				error(CURRENT_TIME_NOT_ALLOWED, null);
			}
		}
	}

	@Check(CheckType.FAST)
	public void errorContextOfReturnExpression(ReturnExpression expr) {
		DataClass dClass = HelperFunctions.getContainingDataClass(expr);
		if (dClass == null) {
			error(RETURN_IN_DATA, null);
		}
	}

	@Check(CheckType.FAST)
	public void errorContextOfSelfExpression(SelfExpression expr) {
		DataClass dClass = HelperFunctions.getContainingDataClass(expr);
		if (dClass == null) {
			error(SELF_IN_DATA, null);
		}
	}

	@Check(CheckType.FAST)
	public void errorContextOfNativeDataMethod(DataMethod dMethod) {
		if (dMethod.isNative()) {
			DataClass dClass = (DataClass) dMethod.eContainer();
			if (!dClass.isNative()) {
				error(NATIVE_METHOD_IN_DATA, Literals.DATA_METHOD__NATIVE);
			}
		}
	}

	@Check(CheckType.FAST)
	public void errorEmptyBodyTextNonNativeDataMethod(DataMethod dMethod) {
		if (dMethod.getReturnType() == null) {
			return;
		}

		if (!dMethod.isNative()) {
			if (dMethod.getBody() == null) {
				error(DATA_METHOD_EMPTY, null);
			}
		} else {
			if (dMethod.getBody() != null) {
				error(NATIVE_METHOD_EMPTY, null);
			}
		}
	}

	// Annotations

	@Check(CheckType.FAST)
	public void warningAnnotationGrammar(Annotation annotation) {
		Annotations eAnnotation = getEnumAnnotation(annotation);

		if (eAnnotation == null) {
			warning("Annotation is not recognized. (Supported annotation are " + Annotations.SUPPRESSWARNINGS + ", "
					+ Annotations.INIT + ", " + Annotations.TEST + ", " + Annotations.ERROR + " and " + Annotations.SKIP
					+ ")", annotation, PooslPackage.Literals.ANNOTATION__NAME, PooslIssueCodes.UNKNOWN_ANNOTATION,
					WarningType.ANNOTATION);
		} else {
			switch (eAnnotation) {
			case SUPPRESSWARNINGS:
				checkSuppresWarningArguments(annotation);
				break;
			case ERROR:
				// Allow arguments
				break;
			case INIT:
			case TEST:
			case SKIP:
			default:
				checkNoArguments(annotation);
				break;
			}
		}
	}

	private Annotations getEnumAnnotation(Annotation annotation) {
		for (Annotations c : Annotations.values()) {
			if (c.name().equalsIgnoreCase(annotation.getName())) {
				return c;
			}
		}
		return null;
	}

	private void checkNoArguments(Annotation annotation) {
		for (int i = 0; i < annotation.getArguments().size(); i++) {
			warning("This annotation doesn't support any arguments.", annotation,
					PooslPackage.Literals.ANNOTATION__ARGUMENTS, i, null, WarningType.ANNOTATION);
		}
	}

	@Check(CheckType.FAST)
	public void errorTestDataMethod(DataMethod dMethod) {
		if (!dMethod.getParameters().isEmpty()) {
			for (Annotation annotation : dMethod.getAnnotations()) {
				if (annotation.getName().equalsIgnoreCase("test")) {
					error(ILLEGAL_TEST_DATA_METHOD, annotation, null);
				}
			}
		}
	}

	@Check(CheckType.FAST)
	public void errorUnconnectedChannel(Channel channel) {
		int portCount = channel.getInstancePorts().size();
		if (channel.getExternalPort() != null) {
			portCount++;
		}
		if (portCount == 0) {
			error(UNUSED_CHANNEL_NONE, channel, null, PooslIssueCodes.ERROR_UNUSED_CHANNEL);
		} else if (portCount == 1) {
			warning(UNUSED_CHANNEL_ONE, channel, null, PooslIssueCodes.WARNING_UNUSED_CHANNEL, WarningType.UNUSED);
		}
	}

	@Check(CheckType.FAST)
	public void missingProcessInitMethod(ProcessClass pClass) {
		if (pClass.getInitialMethodCall() == null) {
			error("Process class is missing an init method call.", Literals.INSTANTIABLE_CLASS__NAME);
		}
	}
}
