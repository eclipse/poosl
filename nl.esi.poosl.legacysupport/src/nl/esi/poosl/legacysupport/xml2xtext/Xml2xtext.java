package nl.esi.poosl.legacysupport.xml2xtext;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import javax.xml.transform.stream.StreamSource;

import nl.esi.poosl.generatedxmlclasses.TBoolean;
import nl.esi.poosl.generatedxmlclasses.TChannel;
import nl.esi.poosl.generatedxmlclasses.TChannelInternal;
import nl.esi.poosl.generatedxmlclasses.TClusterClass;
import nl.esi.poosl.generatedxmlclasses.TConnection;
import nl.esi.poosl.generatedxmlclasses.TDataClass;
import nl.esi.poosl.generatedxmlclasses.TDataMethod;
import nl.esi.poosl.generatedxmlclasses.TInstance;
import nl.esi.poosl.generatedxmlclasses.TInstantiationExpression;
import nl.esi.poosl.generatedxmlclasses.TMessage;
import nl.esi.poosl.generatedxmlclasses.TMessageType;
import nl.esi.poosl.generatedxmlclasses.TPooslSpecification;
import nl.esi.poosl.generatedxmlclasses.TPooslTypeAttribute;
import nl.esi.poosl.generatedxmlclasses.TPort;
import nl.esi.poosl.generatedxmlclasses.TProcessClass;
import nl.esi.poosl.generatedxmlclasses.TProcessMethod;
import nl.esi.poosl.generatedxmlclasses.TTypedVariable;

public class Xml2xtext {
	private static final String COULD_NOT_COMPLETE_XTEXT_TRANSFORMATION = "Could not complete xtext transformation.";

	private static final Logger LOGGER = Logger.getLogger(Xml2xtext.class.getName());

	static TPooslSpecification pooslSpec;

	private Xml2xtext() {
		// Hide the public constructor
	}

	public static void main(String[] args) {
		String input = "";
		if (args.length > 0) {
			input = args[0];
		}
		if (!input.isEmpty()) {
			File fInput = new File(input);
			if (fInput.isDirectory()) {
				FilenameFilter xmlFilter = new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						return name.toLowerCase().endsWith(".xml");
					}
				};
				for (File xmlFile : fInput.listFiles(xmlFilter)) {
					StringBuilder stringBuilder = new StringBuilder(xmlFile.getAbsolutePath());
					stringBuilder.replace(stringBuilder.lastIndexOf("."), stringBuilder.length(), ".poosl");
					String outputFile = stringBuilder.toString();

					try (FileWriter fileWriter = new FileWriter(outputFile);
							InputStream inputStream = parse(xmlFile.getAbsolutePath())) {
						System.out.println("writing output file: " + outputFile);
						byte[] buffer = new byte[256];
						while (inputStream.read(buffer) != -1) {
							fileWriter.write(new String(buffer, StandardCharsets.UTF_8));
						}
					} catch (IOException e) {
						LOGGER.log(Level.SEVERE, COULD_NOT_COMPLETE_XTEXT_TRANSFORMATION, e);
					}
				}
			} else {

				StringBuilder stringBuilder = new StringBuilder(fInput.getAbsolutePath());
				stringBuilder.replace(stringBuilder.lastIndexOf("."), stringBuilder.length(), ".poosl");
				String outputFile = stringBuilder.toString();

				try (FileWriter fileWriter = new FileWriter(outputFile); InputStream inputStream = parse(input)) {
					System.out.println("writing output file: " + outputFile);
					byte[] buffer = new byte[256];
					while (inputStream.read(buffer) != -1) {
						fileWriter.write(new String(buffer, StandardCharsets.UTF_8));
					}
				} catch (IOException e) {
					LOGGER.log(Level.SEVERE, COULD_NOT_COMPLETE_XTEXT_TRANSFORMATION, e);
				}
			}
		} else {
			System.err.println(
					"Please provide a path to either a directory with poosl xml files or a specific poosl xml file.");
		}
	}

	public static InputStream parse(String fileName) {
		try {
			return new ByteArrayInputStream(run(fileName).toString().getBytes());
		} catch (JAXBException | IOException e) {
			LOGGER.log(Level.SEVERE, COULD_NOT_COMPLETE_XTEXT_TRANSFORMATION, e);
			return new ByteArrayInputStream(new byte[0]);
		}
	}

	private static StringWriter run(String fileName) throws JAXBException, IOException {
		StringWriter stringWriter = new StringWriter();

		LOGGER.info("Parsing: " + fileName + " and generating xtext file.");
		InputStream xml = new FileInputStream(fileName);
		JAXBElement<TPooslSpecification> xmlPooslRoot = unmarshal(TPooslSpecification.class, xml);

		if (xmlPooslRoot == null) {
			return stringWriter;
		}
		pooslSpec = xmlPooslRoot.getValue();

		for (TDataClass dc : pooslSpec.getDataClasses().getDataClass()) {
			if (dc.getNative() == TBoolean.TRUE) {
				stringWriter.append("native ");
			}
			stringWriter.append("data class " + dc.getName());
			if (dc.getSuperClass() != null) {
				stringWriter.append(" extends " + dc.getSuperClass() + "\n");
			} else {
				stringWriter.append("\n");
			}
			stringWriter.append("variables" + "\n\t");
			printTypedVariableList(stringWriter, dc.getInstanceVariable());
			stringWriter.append("\n");
			stringWriter.append("methods" + "\n");
			for (TDataMethod dm : dc.getDataMethod()) {
				if (dm.getBodyText() != null) {
					String bodyText = dm.getBodyText().trim();
					if (bodyText.endsWith(".")) {
						stringWriter.append(bodyText.substring(0, bodyText.length() - 1));
					} else {
						stringWriter.append(bodyText);
					}
				} else {
					if (dm.getNative() == TBoolean.TRUE) {
						stringWriter.append("native ");
					}
					stringWriter.append(dm.getName() + "(");
					printTypedVariableList(stringWriter, dm.getArgument());
					stringWriter.append("): " + dm.getReturnType());
				}
				stringWriter.append("\n");
				stringWriter.append("\n");
			}
			stringWriter.append("\n");
		}

		if (pooslSpec.getProcessClasses() != null) {
			for (TProcessClass pc : pooslSpec.getProcessClasses().getProcessClass()) {
				stringWriter.append("process class " + pc.getName() + " (");
				printTypedVariableList(stringWriter, pc.getInstantiationParameter());
				if (pc.getSuperClass() != null) {
					stringWriter.append(") extends " + pc.getSuperClass() + "\n");
				} else {
					stringWriter.append(")" + "\n");
				}
				printPortInterface(stringWriter, pc);
				printMessageInterface(stringWriter, pc);
				stringWriter.append("\n");
				stringWriter.append("variables" + "\n\t");
				printTypedVariableList(stringWriter, pc.getInstanceVariable());
				stringWriter.append("\n");
				stringWriter.append("init" + "\n\t");
				stringWriter.append(pc.getInitialMethodCall().getMethodCallText());
				stringWriter.append("\n");

				stringWriter.append("methods" + "\n");
				for (TProcessMethod pm : pc.getProcessMethod()) {
					if (pm.getBodyText() != null) {
						String bodyText = pm.getBodyText().trim();
						if (bodyText.endsWith(".")) {
							stringWriter.append(bodyText.substring(0, bodyText.length() - 1));
						} else {
							stringWriter.append(bodyText);
						}
					}
					stringWriter.append("\n");
					stringWriter.append("\n");
				}
			}
		}
		if (pooslSpec.getClusterClasses() != null) {
			for (TClusterClass cc : pooslSpec.getClusterClasses().getClusterClass()) {
				stringWriter.append("cluster class " + cc.getName() + " (");
				printTypedVariableList(stringWriter, cc.getInstantiationParameter());
				stringWriter.append(")" + "\n");
				printPortInterface(stringWriter, cc);
				printInstances(stringWriter, cc.getInstance());

				stringWriter.append("channels" + "\n");
				for (int i = 0; i < cc.getChannel().size(); i++) {
					TChannel ch = cc.getChannel().get(i);
					stringWriter.append("\t{");
					boolean firstPort = true;
					if (ch.getOutputPort() != null) {
						stringWriter.append(ch.getOutputPort());
						firstPort = false;
					}
					for (TInstance instance : cc.getInstance()) {
						for (TConnection connection : instance.getConnection()) {
							if (connection.getChannel().equals(ch.getName())) {
								if (firstPort) {
									stringWriter.append(instance.getName() + "." + connection.getPort());
									firstPort = false;
								} else {
									stringWriter.append(", " + instance.getName() + "." + connection.getPort());
								}
							}
						}
					}
					stringWriter.append(" } \n");
				}
				stringWriter.append("\n");
			}
		}
		stringWriter.append("\n");

		if (pooslSpec.getTopLevelSpecification() != null) {
			stringWriter.append("system" + "\n");
			printInstances(stringWriter, pooslSpec.getTopLevelSpecification().getInstance());

			stringWriter.append("channels" + "\n");
			for (int i = 0; i < pooslSpec.getTopLevelSpecification().getChannel().size(); i++) {
				TChannelInternal ch = pooslSpec.getTopLevelSpecification().getChannel().get(i);
				stringWriter.append("\t{");
				boolean firstPort = true;
				for (TInstance instance : pooslSpec.getTopLevelSpecification().getInstance()) {
					for (TConnection connection : instance.getConnection()) {
						if (connection.getChannel().equals(ch.getName())) {
							if (firstPort) {
								stringWriter.append(instance.getName() + "." + connection.getPort());
								firstPort = false;
							} else {
								stringWriter.append(", " + instance.getName() + "." + connection.getPort());
							}
						}
					}
				}
				stringWriter.append(" } \n");
			}
			stringWriter.append("\n");
		}

		return stringWriter;
	}

	private static void printPortInterface(StringWriter stringWriter, TClusterClass cc) {
		List<TPort> ports = cc.getInterface().getPort();
		stringWriter.append("ports" + "\n\t");
		for (int i = 0; i < ports.size(); i++) {
			stringWriter.append(ports.get(i).getName());
			if (i < ports.size() - 1) {
				stringWriter.append(", ");
			}
		}
		stringWriter.append("\n");
	}

	private static TProcessClass resolveProcessClassByName(String className) {
		for (TProcessClass pc : pooslSpec.getProcessClasses().getProcessClass()) {
			if (pc.getName().equals(className)) {
				return pc;
			}
		}
		return null;
	}

	private static void printInstances(StringWriter stringWriter, List<TInstance> instances) {
		stringWriter.append("instances" + "\n");
		for (TInstance i : instances) {
			stringWriter.append("\t" + i.getName() + ": " + i.getClazz() + "(");
			printExpressionList(stringWriter, i.getInstantiationExpression());
			stringWriter.append(")\n");
		}
	}

	private static void printMessageInterface(StringWriter stringWriter, TProcessClass pc) {
		List<TPort> ports = pc.getInterface().getPort();
		stringWriter.append("messages" + "\n");
		int firstMessage = -1;
		boolean firstMessageB = false;
		for (int i = 0; i < ports.size(); i++) {
			TPort p = ports.get(i);
			for (int j = 0; j < p.getMessage().size(); j++) {
				if (!isSuperClassMessage(pc, p, p.getMessage().get(j))) {
					if (firstMessage == -1) {
						firstMessage = j;
						firstMessageB = true;
					}
					if (!firstMessageB) {
						stringWriter.append(",\n");
					} else {
						firstMessageB = false;
					}
					stringWriter.append("\t" + p.getName());
					TMessageType messageType = p.getMessage().get(j).getType();
					if (messageType == TMessageType.READ) {
						stringWriter.append("?");
					} else {
						stringWriter.append("!");
					}
					stringWriter.append(p.getMessage().get(j).getName());
					if (p.getMessage().get(j).getArgument() != null) {
						stringWriter.append("(");
						printTypeList(stringWriter, p.getMessage().get(j).getArgument());
						stringWriter.append(")");
					}
				}
			}
		}
	}

	private static boolean isSuperClassMessage(TProcessClass pc, TPort port, TMessage message) {
		boolean messageFound = false;
		if (pc.getSuperClass() != null) {
			TProcessClass superClass = resolveProcessClassByName(pc.getSuperClass());
			if (superClass != null) {
				for (TPort p : superClass.getInterface().getPort()) {
					if (p.getName().equals(port.getName())) {
						for (TMessage m : p.getMessage()) {
							if (m.getName().equals(message.getName())
									&& compareArguments(m.getArgument(), message.getArgument())) {
								messageFound = true;
							}
						}
					}
				}
				if (superClass.getSuperClass() != null && !messageFound) {
					messageFound = isSuperClassMessage(superClass, port, message);
				}
			}
		}
		return messageFound;
	}

	private static boolean compareArguments(List<TPooslTypeAttribute> arguments, List<TPooslTypeAttribute> arguments2) {
		if (arguments.size() == arguments2.size()) {
			for (int i = 0; i < arguments.size(); i++) {
				if (!arguments.get(i).getType().equals(arguments2.get(i).getType())) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	private static void printPortInterface(StringWriter stringWriter, TProcessClass pc) {
		List<TPort> ports = pc.getInterface().getPort();
		stringWriter.append("ports" + "\n\t");
		int firstPort = -1;
		for (int i = 0; i < ports.size(); i++) {
			if (!isSuperClassPort(pc, ports.get(i))) {
				if (firstPort == -1) {
					firstPort = i;
				}
				if (i != firstPort) {
					stringWriter.append(", ");
				}
				stringWriter.append(ports.get(i).getName());
			}
		}
		stringWriter.append("\n");
	}

	private static boolean isSuperClassPort(TProcessClass pc, TPort tPort) {
		boolean portFound = false;
		if (pc.getSuperClass() != null) {
			TProcessClass superClass = resolveProcessClassByName(pc.getSuperClass());
			if (superClass != null) {
				for (TPort p : superClass.getInterface().getPort()) {
					if (p.getName().equals(tPort.getName())) {
						portFound = true;
					}
				}
				if (superClass.getSuperClass() != null && !portFound) {
					portFound = isSuperClassPort(superClass, tPort);
				}
			}
		}
		return portFound;
	}

	private static void printExpressionList(StringWriter stringWriter,
			List<TInstantiationExpression> instantiationExpressions) {
		for (int i = 0; i < instantiationExpressions.size(); i++) {
			stringWriter.append(instantiationExpressions.get(i).getParameterName() + " := "
					+ instantiationExpressions.get(i).getBodyText());
			if (i < instantiationExpressions.size() - 1) {
				stringWriter.append(", ");
			}
		}
	}

	private static void printTypeList(StringWriter stringWriter, List<TPooslTypeAttribute> tPooslType) {
		for (int i = 0; i < tPooslType.size(); i++) {
			stringWriter.append(tPooslType.get(i).getType());
			if (i < tPooslType.size() - 1) {
				stringWriter.append(", ");
			}
		}
	}

	private static void printTypedVariableList(StringWriter stringWriter, List<TTypedVariable> tTypedVariables) {
		for (int i = 0; i < tTypedVariables.size(); i++) {
			stringWriter.append(tTypedVariables.get(i).getName() + ": " + tTypedVariables.get(i).getType());
			if (i < tTypedVariables.size() - 1) {
				stringWriter.append(", ");
			}
		}
	}

	public static <T> JAXBElement<T> unmarshal(Class<T> docClass, InputStream input) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(docClass.getPackageName(), docClass.getClassLoader());
		Unmarshaller u = jc.createUnmarshaller();
		u.setEventHandler(new DefaultValidationEventHandler());
		JAXBElement<T> returnVal = null;
		try {
			returnVal = u.unmarshal(new StreamSource(input), docClass);
		} catch (ClassCastException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return returnVal;
	}
}
