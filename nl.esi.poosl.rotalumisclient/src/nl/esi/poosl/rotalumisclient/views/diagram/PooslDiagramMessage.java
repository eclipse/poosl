package nl.esi.poosl.rotalumisclient.views.diagram;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import nl.esi.poosl.generatedxmlclasses.TCommunicationEvent;
import nl.esi.poosl.generatedxmlclasses.TCommunicationEventParameter.Parameter;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;

/**
 * This is a wrapper class for a diagram message to add a property descriptor to
 * it.
 * 
 * @author Jeroen van Schelven
 *
 */
public class PooslDiagramMessage implements IPropertySource {
	private final TCommunicationEvent communicationEvent;

	private static final String PROPERTY_SEND_PORT = "poosldiagrammessage.sendport";
	private static final String PROPERTY_RECEIVE_PORT = "poosldiagrammessage.receiveport";
	private static final String PROPERTY_TIMESTAMP = "poosldiagrammessage.timestamp";
	private static final String PROPERTY_MESSAGE = "poosldiagrammessage.message";
	private final IPropertyDescriptor[] properties;

	public PooslDiagramMessage(TCommunicationEvent event) {
		this.communicationEvent = event;
		List<IPropertyDescriptor> propertiesList = new ArrayList<>();
		propertiesList.add(new PropertyDescriptor(PROPERTY_TIMESTAMP, "Simulated time"));
		propertiesList.add(new PropertyDescriptor(PROPERTY_SEND_PORT, "Sender port"));
		propertiesList.add(new PropertyDescriptor(PROPERTY_RECEIVE_PORT, "Receiver port"));
		propertiesList.add(new PropertyDescriptor(PROPERTY_MESSAGE, "Message name"));

		int i = 1;
		for (Parameter parameter : communicationEvent.getMessage().getParameters().getParameter()) {
			propertiesList.add(new PropertyDescriptor(parameter, "Parameter " + i));
			i++;
		}
		properties = propertiesList.toArray(new IPropertyDescriptor[0]);
	}

	@Override
	public Object getEditableValue() {
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return properties;
	}

	@Override
	public Object getPropertyValue(Object id) {
		if (id.equals(PROPERTY_TIMESTAMP)) {
			return communicationEvent.getSimulationTime().toString();
		} else if (id.equals(PROPERTY_SEND_PORT)) {
			return communicationEvent.getSender().getProcessPath() + "." + communicationEvent.getSender().getPortName();
		} else if (id.equals(PROPERTY_RECEIVE_PORT)) {
			return communicationEvent.getReceiver().getProcessPath() + "."
					+ communicationEvent.getReceiver().getPortName();
		} else if (id.equals(PROPERTY_MESSAGE)) {
			return communicationEvent.getMessage().getName();
		}
		for (Parameter parameter : communicationEvent.getMessage().getParameters().getParameter()) {
			if (id.equals(parameter)) {
				return new CommunicationMessageParamater(parameter);
			}
		}
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		return false;
	}

	@Override
	public void resetPropertyValue(Object id) {
		// Not used due to the fact that isPropertySet returns false
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		// Not used due to the fact that isPropertySet returns false
	}

	public String getSendProcess() {
		return communicationEvent.getSender().getProcessPath();
	}

	public String getSendPort() {
		return communicationEvent.getSender().getPortName();
	}

	public String getReceiveProcess() {
		return communicationEvent.getReceiver().getProcessPath();
	}

	public String getReceivePort() {
		return communicationEvent.getReceiver().getPortName();
	}

	public List<Parameter> getParamters() {
		return communicationEvent.getMessage().getParameters().getParameter();
	}

	public String getMessageName() {
		return communicationEvent.getMessage().getName();
	}

	public BigDecimal getSimulatedTime() {
		return communicationEvent.getSimulationTime();
	}

	class CommunicationMessageParamater implements IPropertySource {
		Parameter parameter;
		private static final String PROPERTY_MESSAGE_PARAMETER_VALUE = "poosldiagrammessage.message.parameter.value";
		private static final String PROPERTY_MESSAGE_PARAMETER_TYPE = "poosldiagrammessage.message.parameter.type";

		public CommunicationMessageParamater(Parameter parameter) {
			this.parameter = parameter;
		}

		@Override
		public Object getEditableValue() {
			return null;
		}

		@Override
		public IPropertyDescriptor[] getPropertyDescriptors() {
			return new IPropertyDescriptor[] { new PropertyDescriptor(PROPERTY_MESSAGE_PARAMETER_VALUE, "Value"),
					new PropertyDescriptor(PROPERTY_MESSAGE_PARAMETER_TYPE, "Type") };
		}

		@Override
		public Object getPropertyValue(Object id) {
			if (id.equals(PROPERTY_MESSAGE_PARAMETER_VALUE)) {
				return parameter.getValue();
			} else if (id.equals(PROPERTY_MESSAGE_PARAMETER_TYPE)) {
				return parameter.getType();
			}
			return null;
		}

		@Override
		public boolean isPropertySet(Object id) {
			return false;
		}

		@Override
		public void resetPropertyValue(Object id) {
			// Not used due to the fact that isPropertySet returns false
		}

		@Override
		public void setPropertyValue(Object id, Object value) {
			// Not used due to the fact that isPropertySet returns false
		}

	}

	public String getFormattedPayLoad() {
		String payload = communicationEvent.getMessage().getName();
		for (Parameter param : communicationEvent.getMessage().getParameters().getParameter()) {
			payload += "\n    " + param.getValue() + " : " + param.getType();
		}
		return payload;
	}

	public String getPayload() {
		String payload = "";
		for (Parameter param : communicationEvent.getMessage().getParameters().getParameter()) {
			payload += ", " + param.getValue();
		}
		if (!payload.isEmpty()) {
			payload = payload.substring(2);
		}
		payload = communicationEvent.getMessage().getName() + "(" + payload + ")";
		return payload;
	}
}
