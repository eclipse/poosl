package nl.esi.poosl.xtext.helpers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.xtext.resource.IEObjectDescription;

import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.ProcessMethodCall;
import nl.esi.poosl.xtext.custom.PooslCache;

public class PooslProcessMethodParser {
	private static final String PARSER_METHOD_PARAM_SEPARATOR = "|";

	private String calledMethod;
	private int numberOfInputs;
	private int numberOfOutputs;

	public PooslProcessMethodParser(String stringDescription) {
		String[] properties = stringDescription.split("\\" + PARSER_METHOD_PARAM_SEPARATOR);
		if (properties.length == 3) {
			this.calledMethod = properties[0];
			this.numberOfInputs = Integer.parseInt(properties[1]);
			this.numberOfOutputs = Integer.parseInt(properties[2]);
		} else {
			Logger.getGlobal().log(Level.WARNING, this.getClass().getName() + " " + stringDescription);
		}
	}

	public PooslProcessMethodParser(ProcessMethodCall statement) {
		this.calledMethod = statement.getMethod();
		this.numberOfInputs = statement.getInputArguments().size();
		this.numberOfOutputs = statement.getOutputVariables().size();
	}

	private static String getID(String name, int input, int output) {
		return name + PARSER_METHOD_PARAM_SEPARATOR + String.valueOf(input) + PARSER_METHOD_PARAM_SEPARATOR
				+ String.valueOf(output);
	}

	public static String getProcessMethodID(ProcessMethodCall methodCall) {
		if (methodCall != null) {
			return getID(methodCall.getMethod(), methodCall.getInputArguments().size(),
					methodCall.getOutputVariables().size());
		}
		return null;
	}

	public static String getProcessMethodID(ProcessMethod pMethod) {
		return getID(pMethod.getName(), HelperFunctions.computeNumberOfVariables(pMethod.getInputParameters()),
				HelperFunctions.computeNumberOfVariables(pMethod.getOutputParameters()));
	}

	public static String getProcessMethodIDWithClassName(ProcessMethod pMethod) {
		ProcessClass pClass = (ProcessClass) pMethod.eContainer();
		return pClass.getName() + ":" + getProcessMethodID(pMethod);
	}

	public IEObjectDescription getCalledMethod(ProcessClass pClass) {
		return PooslCache.get(pClass.eResource()).getProcessMethods(pClass.getName(), numberOfInputs, numberOfOutputs)
				.get(calledMethod);
	}

	@Override
	public String toString() {
		return getID(calledMethod, numberOfInputs, numberOfOutputs);
	}

	public String getCalledMethod() {
		return calledMethod;
	}

	public int getNumberOfInputs() {
		return numberOfInputs;
	}

	public int getNumberOfOutputs() {
		return numberOfOutputs;
	}
}
