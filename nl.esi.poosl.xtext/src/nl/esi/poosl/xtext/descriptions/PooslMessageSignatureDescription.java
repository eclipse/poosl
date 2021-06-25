package nl.esi.poosl.xtext.descriptions;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import nl.esi.poosl.MessageParameter;
import nl.esi.poosl.MessageSignature;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.xtext.custom.PooslMessageType;
import nl.esi.poosl.xtext.helpers.HelperFunctions;
import nl.esi.poosl.xtext.helpers.PooslValidationHelper;

public class PooslMessageSignatureDescription {
	private static final String STR_PORT = "Port";
	private static final String STR_CLASS = "Class";
	private static final String STR_TYPE = "Type";
	private static final String STR_MESSAGETYPE_RECEIVE = "Receive";
	private static final String STR_MESSAGETYPE_SEND = "Send";
	private static final String STR_PARAMETERS = "Parameters";

	private PooslMessageSignatureDescription() {
		throw new IllegalStateException("Utility class");
	}

	// --- Set -------

	public static Map<String, String> createUserData(String className, MessageSignature message) {
		Map<String, String> userData = new HashMap<>();
		userData.put(STR_CLASS, className);
		userData.put(STR_PORT, message.getPort().getPort());
		userData.put(STR_PARAMETERS, parametersToString(message.getParameters()));
		if (message.eContainingFeature() == Literals.PROCESS_CLASS__RECEIVE_MESSAGES) {
			userData.put(STR_TYPE, STR_MESSAGETYPE_RECEIVE);
		}
		if (message.eContainingFeature() == Literals.PROCESS_CLASS__SEND_MESSAGES) {
			userData.put(STR_TYPE, STR_MESSAGETYPE_SEND);
		}
		return userData;
	}

	private static String parametersToString(List<MessageParameter> parameters) {
		StringBuilder stringBuilder = new StringBuilder();
		for (MessageParameter msgParameter : parameters) {
			stringBuilder.append(msgParameter.getType()).append(",");
		}
		return stringBuilder.toString();
	}

	// --- Get -------

	public static String getClassName(IEObjectDescription descr) {
		if (!checkValidity(descr))
			return null;

		return descr.getUserData(STR_CLASS);
	}

	public static String getPort(IEObjectDescription descr) {
		if (!checkValidity(descr))
			return null;

		return descr.getUserData(STR_PORT);
	}

	public static List<String> getParameterTypes(IEObjectDescription descr) {
		if (!checkValidity(descr))
			Collections.emptyList();

		String stringDescription = descr.getUserData(STR_PARAMETERS);
		if (!stringDescription.isEmpty()) {
			return Arrays.asList(stringDescription.split(","));
		} else {
			return Collections.emptyList();
		}
	}

	public static PooslMessageType getMessageType(IEObjectDescription descr) {
		if (!checkValidity(descr))
			return PooslMessageType.RECEIVE;

		if (STR_MESSAGETYPE_RECEIVE.equals(descr.getUserData(STR_TYPE))) {
			return PooslMessageType.RECEIVE;
		} else {
			return PooslMessageType.SEND;
		}
	}

	private static boolean checkValidity(IEObjectDescription descr) {
		return PooslValidationHelper.checkValidity(descr, Literals.MESSAGE_SIGNATURE);
	}

	// --- Predicates -------

	public static Predicate<IEObjectDescription> predicateMessage(final Iterable<String> classes) {
		return new Predicate<IEObjectDescription>() {
			@Override
			public boolean apply(IEObjectDescription input) {
				String cName = getClassName(input);
				return cName != null && Iterables.contains(classes, cName);
			}
		};
	}

	public static Predicate<IEObjectDescription> predicateMessage(final String portName) {
		return new Predicate<IEObjectDescription>() {
			@Override
			public boolean apply(IEObjectDescription input) {
				return portName.equals(getPort(input));
			}
		};
	}

	public static Predicate<IEObjectDescription> predicateMessage(final String portName, final String messageName) {
		return new Predicate<IEObjectDescription>() {
			@Override
			public boolean apply(IEObjectDescription input) {
				return portName.equals(getPort(input)) && messageName.equals(HelperFunctions.getName(input));
			}
		};
	}

	public static Predicate<IEObjectDescription> predicateMessage(final String portName, final int vars,
			final URI excludedObjectURI) {
		return new Predicate<IEObjectDescription>() {
			@Override
			public boolean apply(IEObjectDescription input) {
				return portName.equals(getPort(input)) && vars == getParameterTypes(input).size()
						&& !input.getEObjectURI().equals(excludedObjectURI);
			}
		};
	}
}
