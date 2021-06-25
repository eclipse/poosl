package nl.esi.poosl.rotalumisclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugException;

import com.google.common.collect.Lists;

import nl.esi.poosl.generatedxmlclasses.ObjectFactory;
import nl.esi.poosl.generatedxmlclasses.Request;
import nl.esi.poosl.generatedxmlclasses.Response;
import nl.esi.poosl.generatedxmlclasses.TCommand;
import nl.esi.poosl.generatedxmlclasses.TCompileRequest;
import nl.esi.poosl.generatedxmlclasses.TConstantType;
import nl.esi.poosl.generatedxmlclasses.TCreateBreakpointRequest;
import nl.esi.poosl.generatedxmlclasses.TDeleteBreakpointRequest;
import nl.esi.poosl.generatedxmlclasses.TDisableBreakpointRequest;
import nl.esi.poosl.generatedxmlclasses.TEengineEventSetupRequest;
import nl.esi.poosl.generatedxmlclasses.TEnableBreakpointRequest;
import nl.esi.poosl.generatedxmlclasses.TExecutionStateRequest;
import nl.esi.poosl.generatedxmlclasses.TGetPositionRequest;
import nl.esi.poosl.generatedxmlclasses.TGetTransitionsRequest;
import nl.esi.poosl.generatedxmlclasses.TInclude;
import nl.esi.poosl.generatedxmlclasses.TInspectRequest;
import nl.esi.poosl.generatedxmlclasses.TInspectType;
import nl.esi.poosl.generatedxmlclasses.TListClassesRequest;
import nl.esi.poosl.generatedxmlclasses.TListFilesRequest;
import nl.esi.poosl.generatedxmlclasses.TPerformProcessStepRequest;
import nl.esi.poosl.generatedxmlclasses.TPerformTransitionRequest;
import nl.esi.poosl.generatedxmlclasses.TSetVariableRequest;
import nl.esi.poosl.generatedxmlclasses.TSourcePosition;
import nl.esi.poosl.rotalumisclient.debug.PooslDebugTarget;

public class Client {
	private static final Charset CHARSET = StandardCharsets.ISO_8859_1;
	private static final int MAX_REQUEST_SIZE = 256 * 1024 * 1024;
	private static final int MAX_CONNECTION_RETRY = 20;

	private Socket socket;
	private final PrintWriter socketOutWriter;
	private final ObjectFactory objFactory;
	private PooslDebugTarget debugTarget;
	private static final Logger LOGGER = Logger.getLogger(Client.class.getName());
	private final Marshaller marshaller;
	private final Unmarshaller unmarshaller;
	private final Thread responseListenerThread;

	public Client(String ip, int port) throws JAXBException, IOException, InterruptedException {
		LOGGER.info("Starting a new client on ip: " + ip + ":" + port);
		socket = null;
		for (int i = 1; i <= MAX_CONNECTION_RETRY && socket == null; i++) {
			try {
				socket = new Socket(ip, port);
			} catch (IOException e) {
				socket = null;
				if (i == MAX_CONNECTION_RETRY) {
					throw e;
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e1) {
					LOGGER.log(Level.WARNING, "Thread.sleep failed: " + e.getMessage(), e.getSuppressed());
					throw e1;
				}
				LOGGER.log(Level.INFO, "Failed to connect to retalumis: retry [" + i + "/10]");
			}
		}

		if (socket == null) {
			throw new IOException("Failed to create Socket.");
		}

		socketOutWriter = new PrintWriter(socket.getOutputStream(), true);
		objFactory = new ObjectFactory();
		// Define JAXB context and marshaller/unmarshaller
		final JAXBContext context = JAXBContext.newInstance(Request.class.getPackageName(), Request.class.getClassLoader());
		marshaller = context.createMarshaller();
		marshaller.setEventHandler(new DefaultValidationEventHandler());
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, CHARSET.toString());
		final JAXBContext jaxbContext = JAXBContext.newInstance(Response.class.getPackageName(), Response.class.getClassLoader());
		unmarshaller = jaxbContext.createUnmarshaller();
		unmarshaller.setEventHandler(new DefaultValidationEventHandler());
		// Create a permanent listener for responses from the server
		responseListenerThread = new Thread(new ResponseListener(socket));
		responseListenerThread.setName("ResponseListenerThread");
		responseListenerThread.start();
	}

	public PooslDebugTarget getDebugTarget() {
		return debugTarget;
	}

	public void setDebugTarget(PooslDebugTarget debugTarget) {
		this.debugTarget = debugTarget;
	}

	private class ResponseListener implements Runnable {
		private static final int ROTALUMIS_MESSAGE_HEADER_LENGTH = 12;
		Socket listenerSocket;
		BufferedReader socketInReader;
		InputStream socketInStream;

		public ResponseListener(Socket socket) {
			this.listenerSocket = socket;
			try {
				socketInStream = listenerSocket.getInputStream();
				socketInReader = new BufferedReader(new InputStreamReader(socketInStream, CHARSET));
			} catch (IOException e) {
				LOGGER.log(Level.WARNING, e.getMessage(), e);
			}
		}

		@Override
		public void run() {
			while (listenerSocket.isConnected() && !listenerSocket.isClosed()) {
				try {
					int headerCharsread = 0;
					StringBuilder headerBuilder = new StringBuilder();
					while (headerCharsread < ROTALUMIS_MESSAGE_HEADER_LENGTH) {
						final char readChar = (char) socketInReader.read();
						headerCharsread += 1;
						headerBuilder.append(String.valueOf(readChar));
					}

					if (LOGGER.isLoggable(Level.FINEST)) {
						LOGGER.finest("Receiving response with length: " + headerBuilder.toString());
					}

					String trimmedheader = headerBuilder.toString().trim();
					if (!trimmedheader.startsWith("ROT ")) {
						LOGGER.log(Level.SEVERE, "Rotalumis message contains wrong format, is it up to date?");
						return;
					}

					final int respo = Integer.parseInt(trimmedheader.substring(4), 16);
					int totalCharsRead = 0;
					StringBuilder stringBuilder = new StringBuilder();
					while (totalCharsRead < respo) {
						final char readChar = (char) socketInReader.read();
						totalCharsRead += 1;
						stringBuilder.append(String.valueOf(readChar));
					}
					LOGGER.finest("Received complete response: " + stringBuilder.toString());
					if (stringBuilder.length() > 0) {
						final Response response = unmarshal(Response.class, stringBuilder);
						if (response != null) {
							LOGGER.finer("<pre>" + stringBuilder.toString() + "</pre>");
							if (debugTarget == null) {
								throw new IllegalStateException("Debugtarget should have been set");
							}
							debugTarget.dispatchResponse(response);
						}
					}
				} catch (IOException e) {
					LOGGER.log(Level.INFO, "Client response listener stopped:", e);
					return;
				} catch (JAXBException e) {
					LOGGER.log(Level.SEVERE, "Response listener was unable to parse the received response.", e);
				} catch (DebugException e) {
					LOGGER.log(Level.SEVERE, "Could not dispatch response.", e);
				} catch (NumberFormatException e) {
					LOGGER.log(Level.WARNING, e.getMessage(), e);
					return;
				}
			}
		}
	}

	public void instantiateModel(BigInteger handle, String externalPortDescription) {
		LOGGER.fine("Instantiate model with handle: " + handle);
		Request instantiateRequest = objFactory.createRequest();
		instantiateRequest.setInstantiate(objFactory.createTInstantiateRequest());
		instantiateRequest.getInstantiate().setPooslSpecification(handle);
		instantiateRequest.getInstantiate().setExternalPortDescriptionFilename(externalPortDescription);
		sendRequest(instantiateRequest);
	}

	public void compile(String pooslSpecification, String basicClasses, List<String> includes) {
		LOGGER.fine("Compile Model with " + pooslSpecification);
		Request instantiateRequest = objFactory.createRequest();
		TCompileRequest compileRequest = objFactory.createTCompileRequest();
		compileRequest.setPooslSpecification(pooslSpecification);
		if (basicClasses != null) {
			compileRequest.setBasicClasses(basicClasses);
		}
		if (includes != null) {
			TInclude include = objFactory.createTInclude();
			include.getInclude().addAll(Lists.newArrayList(includes));
			compileRequest.setIncludes(include);
		}
		instantiateRequest.setCompile(compileRequest);
		sendRequest(instantiateRequest);
	}

	public void runModel() {
		sendCommand(TCommand.RUN);
	}

	public void suspendModel() {
		sendCommand(TCommand.PAUSE);
	}

	public void resumeModel() {
		sendCommand(TCommand.RUN);
	}

	public void stepModel() {
		sendCommand(TCommand.STEP);
	}

	public void stopModel() {
		sendCommand(TCommand.STOP);
	}

	public void timeStepModel() {
		sendCommand(TCommand.TIME_STEP);
	}

	public void communicationStepModel() {
		sendCommand(TCommand.COMM_STEP);
	}

	public void sendCommand(TCommand command) {
		LOGGER.fine(command.toString());
		Request request = objFactory.createRequest();
		request.setCommand(objFactory.createTCommandRequest());
		request.getCommand().setType(command);
		sendRequest(request);
	}

	public void inspectByHandle(BigInteger handle, TInspectType inspectType) {
		LOGGER.fine("Inspect by handle: " + handle + " ,Inspect type: " + inspectType.toString());
		Request request = objFactory.createRequest();
		TInspectRequest inspectRequest = objFactory.createTInspectRequest();
		inspectRequest.setHandle(handle);
		inspectRequest.setType(inspectType);
		request.setInspect(inspectRequest);
		sendRequest(request);
	}

	public void inspectByName(String name, TInspectType inspectType) {
		LOGGER.fine("Inspect by name: " + name + " ,Inspect type: " + inspectType.toString());
		Request request = objFactory.createRequest();
		TInspectRequest inspectRequest = objFactory.createTInspectRequest();
		inspectRequest.setName(name);
		inspectRequest.setType(inspectType);
		request.setInspect(inspectRequest);
		sendRequest(request);
	}

	public void getTransitions() {
		LOGGER.fine("Get transitions");
		Request request = objFactory.createRequest();
		TGetTransitionsRequest getTransitionsRequest = objFactory.createTGetTransitionsRequest();
		request.setGetTransitions(getTransitionsRequest);
		sendRequest(request);
	}

	public void performTransition(BigInteger handle) {
		LOGGER.fine("Perform transition on handle: " + handle);
		Request request = objFactory.createRequest();
		TPerformTransitionRequest performTransitionRequest = objFactory.createTPerformTransitionRequest();
		performTransitionRequest.setHandle(handle);
		request.setPerformTransition(performTransitionRequest);
		sendRequest(request);
	}

	public void processStepModel(String path) {
		LOGGER.fine("Perform process step until: " + path);
		Request request = objFactory.createRequest();
		TPerformProcessStepRequest performProcessStepRequest = objFactory.createTPerformProcessStepRequest();
		performProcessStepRequest.setProcessPath(path);
		request.setPerformProcessStep(performProcessStepRequest);
		sendRequest(request);
	}

	public void getExecutionState() {
		LOGGER.fine("Get executionState");
		Request request = objFactory.createRequest();
		TExecutionStateRequest getExecutionStateRequest = objFactory.createTExecutionStateRequest();
		request.setExecutionState(getExecutionStateRequest);
		sendRequest(request);
	}

	public void setupCommunicationEvents(boolean enabled, int creditMax) {
		LOGGER.fine("Set sequence diagram view settings to: " + enabled);
		TEengineEventSetupRequest communicationEventSetupRequest = new TEengineEventSetupRequest();
		communicationEventSetupRequest.setCommunicationMessagesEnable(enabled);
		communicationEventSetupRequest.setCommunicationMessagesCredits(BigInteger.valueOf(creditMax));
		communicationEventSetupRequest.setCommunicationMessagesCreditsEnable(true);
		Request request = new Request();
		request.setEengineEventSetup(communicationEventSetupRequest);
		sendRequest(request);
	}

	public void createBreakpoint(BigInteger handle, BigInteger file, int lineNr) {
		LOGGER.fine("Create breakpoint on statement handle: " + handle);
		TCreateBreakpointRequest createBreakpointRequest = new TCreateBreakpointRequest();
		TSourcePosition sourcePosition = new TSourcePosition();
		sourcePosition.setFile(file);
		sourcePosition.setLine(BigInteger.valueOf(lineNr));
		sourcePosition.setColumn(BigInteger.ZERO);
		sourcePosition.setColumn(BigInteger.ZERO);
		createBreakpointRequest.setPosition(sourcePosition);
		Request request = new Request();
		request.setCreateBreakpoint(createBreakpointRequest);
		sendRequest(request);
	}

	public void createBreakpoint(Integer handle) {
		LOGGER.fine("Create breakpoint on statement handle: " + handle);
		TCreateBreakpointRequest createBreakpointRequest = new TCreateBreakpointRequest();
		createBreakpointRequest.setStmtHandle(handle);
		Request request = new Request();
		request.setCreateBreakpoint(createBreakpointRequest);
		sendRequest(request);
	}

	public void deleteBreakpoint(int statementHandle) {
		LOGGER.fine("Delete breakpoint on statement handle: " + statementHandle);
		TDeleteBreakpointRequest deleteBreakpointRequest = new TDeleteBreakpointRequest();
		deleteBreakpointRequest.setStmtHandle(statementHandle);
		Request request = new Request();
		request.setDeleteBreakpoint(deleteBreakpointRequest);
		sendRequest(request);
	}

	public void enableBreakpoint(int statementHandle) {
		LOGGER.fine("Enable breakpoint on statement handle: " + statementHandle);
		TEnableBreakpointRequest enableBreakpointRequest = new TEnableBreakpointRequest();
		enableBreakpointRequest.setStmtHandle(statementHandle);
		Request request = new Request();
		request.setEnableBreakpoint(enableBreakpointRequest);
		sendRequest(request);
	}

	public void disableBreakpoint(int statementHandle) {
		LOGGER.fine("Disable breakpoint on statement handle: " + statementHandle);
		TDisableBreakpointRequest disableBreakpointRequest = new TDisableBreakpointRequest();
		disableBreakpointRequest.setStmtHandle(statementHandle);
		Request request = new Request();
		request.setDisableBreakpoint(disableBreakpointRequest);
		sendRequest(request);
	}

	public void disconnect() {
		if (socket != null && socket.isConnected() && !socket.isClosed()) {
			try {
				socket.close();
			} catch (IOException e) {
				LOGGER.log(Level.WARNING, e.getMessage(), e);
			}
		}
	}

	public <T> T unmarshal(Class<T> docClass, StringBuilder stringBuilder) throws JAXBException {
		try {
			JAXBElement<T> returnVal = unmarshaller
					.unmarshal(new StreamSource(new StringReader(stringBuilder.toString())), docClass);
			return returnVal.getValue();
		} catch (ClassCastException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return null;
	}

	public synchronized String marshal(Object document) throws JAXBException {
		StringWriter sw = new StringWriter();
		marshaller.marshal(document, sw);
		return sw.toString();
	}

	public synchronized IStatus sendRequest(Request request) {
		String requestMessage = "";
		try {
			requestMessage = marshal(request);
		} catch (JAXBException e) {
			LOGGER.log(Level.SEVERE, Messages.ROTALUMIS_REQUEST_MARSHAL_FAILED, e.getCause());
			return new Status(Status.ERROR, PooslConstants.PLUGIN_ID, Messages.ROTALUMIS_REQUEST_MARSHAL_FAILED,
					e.getCause());
		}
		LOGGER.finest("Message length: " + requestMessage.length());
		LOGGER.finer("<pre>" + requestMessage + "</pre>");

		if (requestMessage.length() > MAX_REQUEST_SIZE) {
			LOGGER.log(Level.SEVERE, Messages.ROTALUMIS_REQUEST_TOO_LARGE);
			return new Status(Status.ERROR, PooslConstants.PLUGIN_ID,
					Messages.ROTALUMIS_REQUEST_TOO_LARGE + Messages.ROTALUMIS_REQUEST_TOO_LARGE_USER_INFO);
		}

		byte[] msg = requestMessage.getBytes(CHARSET);

		String size = String.format("ROT %08X", msg.length);
		if (socket != null && socket.isConnected() && !socket.isClosed()) {
			try {
				socketOutWriter.write(size);
				socketOutWriter.flush();
				socket.getOutputStream().write(msg);
				socketOutWriter.flush();
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, Messages.ROTALUMIS_REQUEST_NOT_SEND + " --- " + requestMessage, e.getCause());
				return new Status(Status.ERROR, PooslConstants.PLUGIN_ID,
						MessageFormat.format(
								Messages.ROTALUMIS_REQUEST_NOT_SEND + Messages.ROTALUMIS_REQUEST_NOT_SEND_USER_INFO,
								socket.getPort()),
						e.getCause());
			}
		} else {
			LOGGER.log(Level.SEVERE, Messages.ROTALUMIS_REQUEST_SOCKET_CLOSED);
			return new Status(Status.ERROR, PooslConstants.PLUGIN_ID, Messages.ROTALUMIS_REQUEST_SOCKET_CLOSED);
		}

		return new Status(Status.OK, PooslConstants.PLUGIN_ID, Messages.ROTALUMIS_REQUEST_SEND);
	}

	public void terminate() {
		disconnect();
	}

	public void sendCredits(int credits, boolean commEnabled) {
		LOGGER.fine("Send Credits : " + credits);
		TEengineEventSetupRequest communicationEventSetupRequest = new TEengineEventSetupRequest();
		communicationEventSetupRequest.setCommunicationMessagesEnable(commEnabled);
		communicationEventSetupRequest.setCommunicationMessagesCredits(BigInteger.valueOf(credits));
		communicationEventSetupRequest.setCommunicationMessagesCreditsEnable(true);
		Request request = new Request();
		request.setEengineEventSetup(communicationEventSetupRequest);
		sendRequest(request);
	}

	public void setCommunicationMessagesEnabled(boolean commEnabled) {
		LOGGER.fine("Set Communications Messages to: " + commEnabled);
		TEengineEventSetupRequest communicationEventSetupRequest = new TEengineEventSetupRequest();
		communicationEventSetupRequest.setCommunicationMessagesEnable(commEnabled);
		communicationEventSetupRequest.setCommunicationMessagesCredits(BigInteger.valueOf(0));
		communicationEventSetupRequest.setCommunicationMessagesCreditsEnable(true);
		Request request = new Request();
		request.setEengineEventSetup(communicationEventSetupRequest);
		sendRequest(request);
	}

	public void getListFiles(BigInteger biginteger) {
		LOGGER.fine("Get Model Files");
		TListFilesRequest listFilesRequest = new TListFilesRequest();
		listFilesRequest.setPooslSpecification(biginteger);
		Request request = new Request();
		request.setListFiles(listFilesRequest);
		sendRequest(request);
	}

	public void getListClasses() {
		LOGGER.fine("Get Classes");
		TListClassesRequest listClassesRequest = new TListClassesRequest();
		Request request = new Request();
		request.setListClasses(listClassesRequest);
		sendRequest(request);
	}

	public void inspectModel() {
		LOGGER.fine("Inspect model");
		Request request = objFactory.createRequest();
		TInspectRequest inspectRequest = objFactory.createTInspectRequest();
		inspectRequest.setName("model");
		inspectRequest.setType(TInspectType.MODEL);
		request.setInspect(inspectRequest);
		sendRequest(request);
	}

	public void getSourceMapping(Integer handle) {
		LOGGER.fine("Request Source Mapping");
		Request request = objFactory.createRequest();
		TGetPositionRequest positionRequest = objFactory.createTGetPositionRequest();
		positionRequest.setStmtHandle(handle);
		request.setGetPosition(positionRequest);
		sendRequest(request);
	}

	public void setVariable(BigInteger handle, BigInteger listHandle, TConstantType type, String literal) {
		Request request = objFactory.createRequest();
		TSetVariableRequest variableRequest = objFactory.createTSetVariableRequest();
		variableRequest.setVarHandle(handle);
		variableRequest.setListHandle(listHandle);
		variableRequest.setType(type);
		variableRequest.setLiteral(literal);
		request.setSetVariable(variableRequest);
		sendRequest(request);
	}
}