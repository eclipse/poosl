package nl.esi.poosl.xtext.helpers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.xtext.resource.IEObjectDescription;

import nl.esi.poosl.MessageSignature;
import nl.esi.poosl.PortReference;
import nl.esi.poosl.ReceiveStatement;
import nl.esi.poosl.SendStatement;
import nl.esi.poosl.xtext.custom.PooslMessageType;
import nl.esi.poosl.xtext.descriptions.PooslMessageSignatureDescription;

public class PooslMessageSignatureCallHelper {
	private String type;
	private String port;
	private String name;
	private int paramCount;

	private static final String STR_MESSAGETYPE_RECEIVE = "Receive";
	private static final String STR_MESSAGETYPE_SEND = "Send";

	public PooslMessageSignatureCallHelper(String stringDescription) {
		String[] result = stringDescription.split(":");
		if (result.length == 3 || result.length == 4) {
			port = result[0];
			name = result[1];
			paramCount = Integer.parseInt(result[2]);
			if (result.length == 4)
				type = result[3];
		} else {
			Logger.getGlobal().log(Level.WARNING, this.getClass().getName() + " : " + stringDescription);
		}
	}

	public PooslMessageSignatureCallHelper(ReceiveStatement stat) {
		this.type = STR_MESSAGETYPE_RECEIVE;
		this.port = ((PortReference) stat.getPortDescriptor()).getPort();
		this.name = stat.getName();
		this.paramCount = stat.getVariables().size();
	}

	public PooslMessageSignatureCallHelper(SendStatement stat) {
		this.type = STR_MESSAGETYPE_SEND;
		this.port = ((PortReference) stat.getPortDescriptor()).getPort();
		this.name = stat.getName();
		this.paramCount = stat.getArguments().size();
	}

	public String getType() {
		return type;
	}

	public String getPort() {
		return port;
	}

	public String getName() {
		return name;
	}

	public int getParamCount() {
		return paramCount;
	}

	@Override
	public String toString() {
		return getID(port, name, paramCount, type);
	}

	private static String getID(String lPort, String lName, int lParamCount, String lType) {
		return getID(lPort, lName, lParamCount) + ":" + lType;
	}

	private static String getID(String lPort, String lName, int lParamCount) {
		return lPort + ":" + lName + ":" + lParamCount;
	}

	public static String getSignatureID(MessageSignature messageSignature, PooslMessageType messageType) {
		String type = messageType == PooslMessageType.RECEIVE ? STR_MESSAGETYPE_RECEIVE : STR_MESSAGETYPE_SEND;
		return getID(messageSignature.getPort().getPort(), messageSignature.getName(),
				messageSignature.getParameters().size(), type);
	}

	public static String getSignatureID(MessageSignature messageSignature) {
		return getID(messageSignature.getPort().getPort(), messageSignature.getName(),
				messageSignature.getParameters().size());
	}

	public static String getSignatureID(IEObjectDescription description) {
		return getID(PooslMessageSignatureDescription.getPort(description), HelperFunctions.getName(description),
				PooslMessageSignatureDescription.getParameterTypes(description).size());
	}

	public static String getSignatureID(ReceiveStatement stat) {
		String statPort = ((PortReference) stat.getPortDescriptor()).getPort();
		String statName = stat.getName();

		if (statName != null && statPort != null) {
			return getID(statPort, statName, stat.getVariables().size());
		}
		return null;

	}

	public static String getSignatureID(SendStatement stat) {
		String statPort = ((PortReference) stat.getPortDescriptor()).getPort();
		String statName = stat.getName();

		if (statName != null && statPort != null) {
			return getID(statPort, statName, stat.getArguments().size());
		}
		return null;
	}
}
