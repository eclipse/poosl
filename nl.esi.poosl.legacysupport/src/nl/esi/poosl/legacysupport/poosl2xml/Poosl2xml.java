package nl.esi.poosl.legacysupport.poosl2xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import nl.esi.poosl.AbortStatement;
import nl.esi.poosl.AssignmentExpression;
import nl.esi.poosl.BinaryOperatorExpression;
import nl.esi.poosl.BooleanConstant;
import nl.esi.poosl.Channel;
import nl.esi.poosl.CharacterConstant;
import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.CurrentTimeExpression;
import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.DataMethodBinaryOperator;
import nl.esi.poosl.DataMethodCallExpression;
import nl.esi.poosl.DataMethodNamed;
import nl.esi.poosl.DataMethodUnaryOperator;
import nl.esi.poosl.Declaration;
import nl.esi.poosl.DelayStatement;
import nl.esi.poosl.Expression;
import nl.esi.poosl.ExpressionSequence;
import nl.esi.poosl.ExpressionStatement;
import nl.esi.poosl.GuardedStatement;
import nl.esi.poosl.IfExpression;
import nl.esi.poosl.IfStatement;
import nl.esi.poosl.Import;
import nl.esi.poosl.Instance;
import nl.esi.poosl.InstanceParameter;
import nl.esi.poosl.InstancePort;
import nl.esi.poosl.IntegerConstant;
import nl.esi.poosl.InterruptStatement;
import nl.esi.poosl.MessageParameter;
import nl.esi.poosl.MessageSignature;
import nl.esi.poosl.NewExpression;
import nl.esi.poosl.NilConstant;
import nl.esi.poosl.OutputVariable;
import nl.esi.poosl.ParStatement;
import nl.esi.poosl.Poosl;
import nl.esi.poosl.Port;
import nl.esi.poosl.PortReference;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.ProcessMethodCall;
import nl.esi.poosl.RealConstant;
import nl.esi.poosl.ReceiveStatement;
import nl.esi.poosl.ReturnExpression;
import nl.esi.poosl.SelStatement;
import nl.esi.poosl.SelfExpression;
import nl.esi.poosl.SendStatement;
import nl.esi.poosl.SkipStatement;
import nl.esi.poosl.Statement;
import nl.esi.poosl.StatementSequence;
import nl.esi.poosl.StringConstant;
import nl.esi.poosl.SwitchExpression;
import nl.esi.poosl.SwitchExpressionCase;
import nl.esi.poosl.SwitchStatement;
import nl.esi.poosl.SwitchStatementCase;
import nl.esi.poosl.UnaryOperatorExpression;
import nl.esi.poosl.Variable;
import nl.esi.poosl.VariableExpression;
import nl.esi.poosl.WhileExpression;
import nl.esi.poosl.WhileStatement;
import nl.esi.poosl.generatedxmlclasses.ObjectFactory;
import nl.esi.poosl.generatedxmlclasses.TAbort;
import nl.esi.poosl.generatedxmlclasses.TAssignment;
import nl.esi.poosl.generatedxmlclasses.TBinaryOperation;
import nl.esi.poosl.generatedxmlclasses.TBinaryOperator;
import nl.esi.poosl.generatedxmlclasses.TBoolean;
import nl.esi.poosl.generatedxmlclasses.TChannel;
import nl.esi.poosl.generatedxmlclasses.TChannelInternal;
import nl.esi.poosl.generatedxmlclasses.TClusterClass;
import nl.esi.poosl.generatedxmlclasses.TClusterClasses;
import nl.esi.poosl.generatedxmlclasses.TClusterInterface;
import nl.esi.poosl.generatedxmlclasses.TConnection;
import nl.esi.poosl.generatedxmlclasses.TConstant;
import nl.esi.poosl.generatedxmlclasses.TConstantType;
import nl.esi.poosl.generatedxmlclasses.TCurrentTime;
import nl.esi.poosl.generatedxmlclasses.TDataClass;
import nl.esi.poosl.generatedxmlclasses.TDataClasses;
import nl.esi.poosl.generatedxmlclasses.TDataMethod;
import nl.esi.poosl.generatedxmlclasses.TDataMethodCall;
import nl.esi.poosl.generatedxmlclasses.TEmpty;
import nl.esi.poosl.generatedxmlclasses.TExpression;
import nl.esi.poosl.generatedxmlclasses.TExpressions;
import nl.esi.poosl.generatedxmlclasses.TGuard;
import nl.esi.poosl.generatedxmlclasses.TIfExpression;
import nl.esi.poosl.generatedxmlclasses.TIfStatement;
import nl.esi.poosl.generatedxmlclasses.TInitialMethodCall;
import nl.esi.poosl.generatedxmlclasses.TInitialProcessMethodCall;
import nl.esi.poosl.generatedxmlclasses.TInstance;
import nl.esi.poosl.generatedxmlclasses.TInstanceType;
import nl.esi.poosl.generatedxmlclasses.TInstantiationExpression;
import nl.esi.poosl.generatedxmlclasses.TInterface;
import nl.esi.poosl.generatedxmlclasses.TInterrupt;
import nl.esi.poosl.generatedxmlclasses.TMessage;
import nl.esi.poosl.generatedxmlclasses.TMessageReceive;
import nl.esi.poosl.generatedxmlclasses.TMessageSend;
import nl.esi.poosl.generatedxmlclasses.TMessageType;
import nl.esi.poosl.generatedxmlclasses.TObjectCreation;
import nl.esi.poosl.generatedxmlclasses.TPooslSpecification;
import nl.esi.poosl.generatedxmlclasses.TPooslTypeAttribute;
import nl.esi.poosl.generatedxmlclasses.TPort;
import nl.esi.poosl.generatedxmlclasses.TProcessClass;
import nl.esi.poosl.generatedxmlclasses.TProcessClasses;
import nl.esi.poosl.generatedxmlclasses.TProcessMethod;
import nl.esi.poosl.generatedxmlclasses.TProcessMethodCall;
import nl.esi.poosl.generatedxmlclasses.TStatement;
import nl.esi.poosl.generatedxmlclasses.TStatements;
import nl.esi.poosl.generatedxmlclasses.TSwitchCaseExpression;
import nl.esi.poosl.generatedxmlclasses.TSwitchCaseStatement;
import nl.esi.poosl.generatedxmlclasses.TSwitchExpression;
import nl.esi.poosl.generatedxmlclasses.TSwitchStatement;
import nl.esi.poosl.generatedxmlclasses.TTopLevelSpecification;
import nl.esi.poosl.generatedxmlclasses.TTypedVariable;
import nl.esi.poosl.generatedxmlclasses.TUnaryOperation;
import nl.esi.poosl.generatedxmlclasses.TUnaryOperator;
import nl.esi.poosl.generatedxmlclasses.TVariableRef;
import nl.esi.poosl.generatedxmlclasses.TWhileExpression;
import nl.esi.poosl.generatedxmlclasses.TWhileStatement;
import nl.esi.poosl.xtext.PooslRuntimeModule;
import nl.esi.poosl.xtext.PooslStandaloneSetup;
import nl.esi.poosl.xtext.helpers.HelperFunctions;
import nl.esi.poosl.xtext.importing.ImportingHelper;

public class Poosl2xml {
	private static final String COULD_NOT_COMPLETE_XML_TRANSFORMATION = "Could not complete xml transformation.";
	private static final String XSLT_URI = "platform:/plugin/nl.esi.poosl.legacysupport/src/nl/esi/poosl/legacysupport/poosl2xml/as_shesim_model.xslt";
	private static final Logger LOGGER = Logger.getLogger(Poosl2xml.class.getName());
	private static final ObjectFactory objFact = new ObjectFactory();
	private boolean isSHESimXml;
	private PooslModelInformation modelInfo;
	private Map<String, TInstanceType> postProcessingClassTypes = new HashMap<>();
	private List<TInstance> postProcessingInstances = new ArrayList<>();
	private Map<String, TProcessClass> postProcessingProcessClassNames = new HashMap<>();

	@Inject
	private IResourceValidator validator;

	public static void main(String[] args) {
		String cmdBasicClasses = "";
		Options options = new Options();
		options.addOption("v", "novalidation", false,
				"Setting this flag will skip the validation step in the transformation");
		options.addOption("b", "basicclasses", true, "Use this basic classes file instead of the default one");
		CommandLineParser cmdParser = new BasicParser();
		CommandLine cmd = null;
		HelpFormatter formatter = new HelpFormatter();
		try {
			cmd = cmdParser.parse(options, args, false);
		} catch (ParseException e) {
			LOGGER.log(Level.SEVERE, "Could not parse the command line arguments", e);
			printHelp(formatter, options);
			return;
		}
		if (cmd != null && args.length > 0) {
			String input = args[0];
			boolean validate = true;
			if (cmd.hasOption("v")) {
				validate = false;
			}
			if (cmd.hasOption("b")) {
				cmdBasicClasses = cmd.getOptionValue("b");
				if (cmdBasicClasses.equals(input)) {
					System.out.println("Basic classes cannot be the same as the FILE");
					printHelp(formatter, options);
					return;
				}
				if (!cmd.hasOption("v")) {
					System.out.println(
							"Validation is not available when specifying basic classes. Please add option -v.");
					printHelp(formatter, options);
					return;
				}
			}
			if (!input.isEmpty()) {
				Poosl2xml headless = getInstanceStandalone();
				File fInput = new File(input);
				if (!fInput.exists()) {
					System.out.println("File does not exist");
					printHelp(formatter, options);
					return;
				}
				ResourceSet resourceSet = new ResourceSetImpl();
				resourceSet.getPackageRegistry().put(nl.esi.poosl.PooslPackage.eINSTANCE.getNsURI(),
						nl.esi.poosl.PooslPackage.eINSTANCE);
				if (fInput.isDirectory()) {
					for (File pooslFile : fInput.listFiles(new FilenameFilter() {
						@Override
						public boolean accept(File dir, String name) {
							return name.toLowerCase().endsWith(".poosl");
						}
					})) {
						LOGGER.info("Given input file: " + pooslFile.getAbsolutePath());
						try {
							URI uri = URI.createFileURI(pooslFile.getAbsolutePath());
							Resource resource = resourceSet.getResource(uri, true);
							headless.run(resourceSet, resource, false, validate, cmdBasicClasses, null);
						} catch (FileNotFoundException | UnsupportedEncodingException | JAXBException
								| PooslValidationException e) {
							LOGGER.log(Level.SEVERE, COULD_NOT_COMPLETE_XML_TRANSFORMATION);
						}
					}
				} else {
					LOGGER.info("Given input file: " + fInput.getAbsolutePath());
					try {
						URI uri = URI.createFileURI(fInput.getAbsolutePath());
						Resource resource = resourceSet.getResource(uri, true);
						headless.run(resourceSet, resource, false, validate, cmdBasicClasses, null);
					} catch (FileNotFoundException | UnsupportedEncodingException | JAXBException
							| PooslValidationException e) {
						LOGGER.log(Level.SEVERE, COULD_NOT_COMPLETE_XML_TRANSFORMATION);
					}
				}
			} else {
				LOGGER.severe("please supply a valid input file");
			}
		} else {
			printHelp(formatter, options);
		}
	}

	private static void printHelp(HelpFormatter formatter, Options options) {
		formatter.printHelp("poosl2xml.jar FILE [OPTIONS]", options);
		formatter.printHelp("poosl2xml.jar DIRECTORY [OPTIONS]", options);
	}

	public static Poosl2xml getInstanceRuntime() {
		Injector pooslInjector = Guice.createInjector(new PooslRuntimeModule());
		return pooslInjector.getInstance(Poosl2xml.class);
	}

	public static Poosl2xml getInstanceStandalone() {
		Injector pooslInjector = new PooslStandaloneSetup().createInjectorAndDoEMFRegistration();
		return pooslInjector.getInstance(Poosl2xml.class);
	}

	/**
	 * Will generate {@link PooslModeInformation} from the given inputFile
	 * 
	 * @param inputFile the absolute path to file, for example:
	 *                  C:\dir\hello_world.poosl
	 * @param monitor   A IProgressMonitor to track progress, null is allowed
	 * @param asSHESim  Generate SHESIM xml
	 * @param validate  If true the inputFile and all imported files will be
	 *                  validated, if validate fails a
	 *                  {@link PooslValidationException} will be thrown
	 * @return PooslModelInformation created from the inputFile
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 * @throws JAXBException
	 * @throws PooslValidationException
	 */
	public PooslModelInformation exportPoosl2Xml(String inputFile, IProgressMonitor monitor, boolean asSHESim,
			boolean validate)
			throws FileNotFoundException, UnsupportedEncodingException, JAXBException, PooslValidationException {
		LOGGER.info("Transforming input file: " + inputFile);
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(nl.esi.poosl.PooslPackage.eINSTANCE.getNsURI(),
				nl.esi.poosl.PooslPackage.eINSTANCE);
		URI uri = URI.createFileURI(inputFile);
		Resource resource = resourceSet.getResource(uri, true);
		String cmdBasicClasses = "";
		return run(resourceSet, resource, asSHESim, validate, cmdBasicClasses, monitor);
	}

	/**
	 * Will generate {@link PooslModeInformation} from the given Poosl model
	 * 
	 * @param poosl    The poosl model
	 * @param monitor  A IProgressMonitor to track progress, null is allowed
	 * @param asSHESim Generate SHESIM xml
	 * @param validate If true the inputFile and all imported files will be
	 *                 validated, if validate fails a
	 *                 {@link PooslValidationException} will be thrown A
	 *                 IProgressMonitor to track progress, null is allowed
	 * @param asSHESim Generate SHESIM xml
	 * @param validate If true the inputFile and all imported files will be
	 *                 validated, if validate fails a
	 *                 {@link PooslValidationException} will be thrown
	 * @return PooslModelInformation created from the Poosl model
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 * @throws JAXBException
	 * @throws PooslValidationException
	 */
	public PooslModelInformation exportPoosl2Xml(Poosl poosl, IProgressMonitor monitor, boolean asSHESim,
			boolean validate)
			throws FileNotFoundException, UnsupportedEncodingException, JAXBException, PooslValidationException {
		LOGGER.info("Transforming model: " + poosl.eResource().getURI().toString());
		ResourceSet resourceSet = poosl.eResource().getResourceSet();
		Resource resource = poosl.eResource();
		String cmdBasicClasses = "";
		return run(resourceSet, resource, asSHESim, validate, cmdBasicClasses, monitor);
	}

	// --- Main functionality -------

	private synchronized PooslModelInformation run(ResourceSet resourceSet, Resource resource, boolean asSHESim,
			boolean validate, String cmdBasicClasses, IProgressMonitor monitor)
			throws JAXBException, FileNotFoundException, UnsupportedEncodingException, PooslValidationException {
		postProcessingProcessClassNames.clear();
		postProcessingClassTypes.clear();
		postProcessingInstances.clear();
		URI originalURI = resource.getURI();
		modelInfo = new PooslModelInformation();
		modelInfo.setModelPath(originalURI.toFileString());
		isSHESimXml = asSHESim;

		if (monitor != null) {
			monitor.beginTask("Running transformation to xml", 3);
			monitor.subTask("Validating");
		}
		// Validate the main resource
		if (validate) {
			validateResource(resource);
		}

		if (monitor != null) {
			monitor.worked(1);
			monitor.subTask("Creating xml");
		}

		// This list will be filled with files to transform based on imports
		List<URI> filesToTransform = new ArrayList<>();
		// Add the main file first
		filesToTransform.add(originalURI);
		// Add the basic classes
		URI basicClassesURI = getBasicClassesUri(cmdBasicClasses);
		filesToTransform.add(basicClassesURI);

		TPooslSpecification pooslSpec = null;
		// Transform the imports one by one
		for (int i = 0; i < filesToTransform.size(); i++) {
			Resource nextResource = resourceSet.getResource(filesToTransform.get(i), true);
			// Validate the resource
			if (validate && !filesToTransform.get(i).equals(basicClassesURI)) {
				validateResource(nextResource);
			}
			Poosl poosl = (Poosl) nextResource.getContents().get(0);
			TPooslSpecification nextPooslSpec = createPooslSpec(asSHESim, poosl);

			if (pooslSpec == null) {
				// Create the xml pooslspec from the first/main file
				pooslSpec = nextPooslSpec;
			} else {
				pooslSpec.getDataClasses().getDataClass().addAll(nextPooslSpec.getDataClasses().getDataClass());
				pooslSpec.getProcessClasses().getProcessClass()
						.addAll(nextPooslSpec.getProcessClasses().getProcessClass());
				pooslSpec.getClusterClasses().getClusterClass()
						.addAll(nextPooslSpec.getClusterClasses().getClusterClass());
			}

			for (Import imports : poosl.getImports()) {
				String importUri = imports.getImportURI().substring(1, imports.getImportURI().length() - 1);
				URI importURI = URI.createURI(importUri);
				URI resolvedURI = importURI.resolve(filesToTransform.get(i));
				if (!filesToTransform.contains(resolvedURI)) {
					filesToTransform.add(resolvedURI);
				}
			}
		}

		modelInfo.setFiles(filesToTransform);
		/************************************
		 * POST PROCESSING
		 ***************************/
		// Sort all process classes
		List<TProcessClass> unorderedProcessClasses = pooslSpec.getProcessClasses().getProcessClass();
		LinkedHashMap<String, TProcessClass> orderedProcessClasses = new LinkedHashMap<>();
		for (TProcessClass processClass : unorderedProcessClasses) {
			if (processClass.getSuperClass() == null) {
				orderedProcessClasses.put(processClass.getName(), processClass);
			}
		}
		while (orderedProcessClasses.size() < unorderedProcessClasses.size()) {
			for (TProcessClass processClass : unorderedProcessClasses) {
				if (!orderedProcessClasses.containsKey(processClass.getName())
						&& orderedProcessClasses.containsKey(processClass.getSuperClass())) {
					orderedProcessClasses.put(processClass.getName(), processClass);
				}
			}
		}
		pooslSpec.getProcessClasses().getProcessClass().clear();
		pooslSpec.getProcessClasses().getProcessClass().addAll(orderedProcessClasses.values());
		// Sort all data classes
		List<TDataClass> unorderedDataClasses = pooslSpec.getDataClasses().getDataClass();
		LinkedHashMap<String, TDataClass> orderedDataClasses = new LinkedHashMap<>();
		for (TDataClass dataClass : unorderedDataClasses) {
			if (dataClass.getSuperClass() == null || dataClass.getName().equals("Object")) {
				orderedDataClasses.put(dataClass.getName(), dataClass);
			}
		}
		while (orderedDataClasses.size() < unorderedDataClasses.size()) {
			for (TDataClass dataClass : unorderedDataClasses) {
				if (!orderedDataClasses.containsKey(dataClass.getName())
						&& orderedDataClasses.containsKey(dataClass.getSuperClass())) {
					orderedDataClasses.put(dataClass.getName(), dataClass);
				}
			}
		}
		pooslSpec.getDataClasses().getDataClass().clear();
		pooslSpec.getDataClasses().getDataClass().addAll(orderedDataClasses.values());

		// Fix missing instance types
		for (TInstance instance : postProcessingInstances) {
			TInstanceType instanceType = postProcessingClassTypes.get(instance.getClazz());
			instance.setType(instanceType);
		}

		// Sort cluster classes
		List<TClusterClass> unorderedClusterClasses = pooslSpec.getClusterClasses().getClusterClass();
		LinkedHashMap<String, TClusterClass> orderedClusterClasses = new LinkedHashMap<>();
		while (orderedClusterClasses.size() < unorderedClusterClasses.size()) {
			for (TClusterClass clusterClass : unorderedClusterClasses) {
				boolean move = true;
				for (TInstance instance : clusterClass.getInstance()) {
					if (instance.getType() == TInstanceType.CLUSTER) {
						move = move && orderedClusterClasses.containsKey(instance.getClazz());
					}
				}
				if (move && (!orderedClusterClasses.containsValue(clusterClass))) {
					orderedClusterClasses.put(clusterClass.getName(), clusterClass);
				}
			}
		}
		pooslSpec.getClusterClasses().getClusterClass().clear();
		pooslSpec.getClusterClasses().getClusterClass().addAll(orderedClusterClasses.values());

		// Add all super class messages to process class definitions
		for (TProcessClass tProcessClass : pooslSpec.getProcessClasses().getProcessClass()) {
			if (tProcessClass.getSuperClass() != null) {
				TProcessClass superClass = postProcessingProcessClassNames.get(tProcessClass.getSuperClass());
				copyInterfaces(superClass.getInterface(), tProcessClass.getInterface());
			}
		}

		Map<String, String> instancePortMap = new LinkedHashMap<>();
		Map<String, TInstanceType> instances = new LinkedHashMap<>();
		if (pooslSpec.getTopLevelSpecification() != null) {
			for (TInstance tInstance : pooslSpec.getTopLevelSpecification().getInstance()) {
				String iName = "/" + tInstance.getName();
				instances.put(iName, tInstance.getType());
				if (tInstance.getType().equals(TInstanceType.CLUSTER)) {
					List<TClusterClass> clusters = pooslSpec.getClusterClasses().getClusterClass();
					instances.putAll(
							getInstancesFromClusterClass(clusters, tInstance.getClazz(), iName, instancePortMap));
				}
			}
		}

		modelInfo.setInstances(instances);
		modelInfo.setInstancePortMap(instancePortMap);

		/************************************
		 * END POST PROCESSING
		 **********************/
		// Create JAXBContext and mashal the PooslSpecification to xml
		// If this statement is causing nullpointer exceptions than the
		// installed JAVA version is below
		// 1.7.0_22 and should be updated
		Marshaller m = null;
		try {
			JAXBContext context = JAXBContext.newInstance(TPooslSpecification.class.getPackageName(), TPooslSpecification.class.getClassLoader());
			m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
			m.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
		} catch (NullPointerException e) {
			LOGGER.log(Level.SEVERE,
					"Could not instantiate JAXB context.\n"
							+ "This is most likely caused by an outdated version of Java.\n"
							+ "Update to the latest version to solve this problem.",
					e);
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
					MessageDialog.openError(Display.getDefault().getActiveShell(),
							"Could not instantiate JAXB context.",
							"This is most likely caused by an outdated version of Java.\nUpdate to the latest version to solve this problem.");
				}
			});
			return null;
		}

		String inputFile = originalURI.toFileString();
		File workingDirectory = new File(
				inputFile.substring(0, inputFile.lastIndexOf(File.separator)) + File.separator + "simulator");
		if (!workingDirectory.exists()) {
			workingDirectory.mkdir();
		}

		PrintWriter fileWriter = new PrintWriter(modelInfo.getXmlPath(), "ISO-8859-1");
		m.marshal(new JAXBElement<TPooslSpecification>(new QName("uri:poosl", "poosl_specification"),
				TPooslSpecification.class, pooslSpec), fileWriter);
		fileWriter.close();
		LOGGER.info("Generated xml file: " + modelInfo.getXmlPath());
		if (monitor != null) {
			monitor.worked(1);
		}
		if (asSHESim) {
			if (monitor != null) {
				monitor.subTask("Running xslt transformation to add graphical information");
			}
			try {
				TransformerFactory factory = TransformerFactory.newInstance();
				URL xsltPath = new URL(XSLT_URI);
				StreamSource xslt = new StreamSource(xsltPath.openStream());
				Transformer transformer;
				transformer = factory.newTransformer(xslt);
				Source text = new StreamSource(new File(modelInfo.getXmlPath()));
				transformer.transform(text,
						new StreamResult(new File(modelInfo.getXmlPath().replace(".xml", "_shesim.xml"))));
			} catch (TransformerException | IOException e) {
				LOGGER.log(Level.SEVERE, COULD_NOT_COMPLETE_XML_TRANSFORMATION, e);
			}
		}
		if (monitor != null) {
			monitor.worked(1);
		}
		return modelInfo;
	}

	private URI getBasicClassesUri(String cmdBasicClasses) {
		if (cmdBasicClasses == null || cmdBasicClasses.isEmpty()) {
			return ImportingHelper.getBasicClassesURI();
		} else {
			return URI.createURI("file:///" + cmdBasicClasses);
		}
	}

	private Map<String, TInstanceType> getInstancesFromClusterClass(List<TClusterClass> clusters, String clusterName,
			String instanceName, Map<String, String> instancePortMap) {
		Map<String, TInstanceType> instances = new LinkedHashMap<>();
		TClusterClass cc = findCluster(clusters, clusterName);
		if (cc != null) {
			for (TInstance tInstance : cc.getInstance()) {
				String iName = instanceName + "/" + tInstance.getName();
				instances.put(iName, tInstance.getType());
				for (TConnection tConnection : tInstance.getConnection()) {
					String extern = instanceName + "."
							+ cc.getChannel().get(Integer.parseInt(tConnection.getChannel())).getOutputPort();
					instancePortMap.put(iName + "." + tConnection.getPort(), extern);
				}

				if (tInstance.getType().equals(TInstanceType.CLUSTER)) {
					instances.putAll(
							getInstancesFromClusterClass(clusters, tInstance.getClazz(), iName, instancePortMap));
				}
			}
		}
		return instances;
	}

	private TClusterClass findCluster(List<TClusterClass> clusters, String clusterName) {
		for (TClusterClass cc : clusters) {
			if (cc.getName().equals(clusterName)) {
				return cc;
			}
		}
		return null;
	}

	private void validateResource(Resource nextResource) throws PooslValidationException {
		String errorMessage = "";
		List<Issue> issues = validator.validate(nextResource, CheckMode.ALL, null);
		for (Issue issue : issues) {
			if (issue.getSeverity() == Severity.ERROR) {
				errorMessage += issue.getMessage() + " on line " + issue.getLineNumber() + " in file "
						+ nextResource.getURI().lastSegment() + "\n";
			}
		}
		if (!errorMessage.isEmpty()) {
			LOGGER.severe("Error occured while validating the model: " + errorMessage);
			throw new PooslValidationException(errorMessage);
		}
	}

	private void copyInterfaces(TInterface sourceInterface, TInterface targetInterface) {
		for (TPort superClassPort : sourceInterface.getPort()) {
			TPort port = null;
			for (TPort sourcePort : targetInterface.getPort()) {
				if (superClassPort.getName().equals(sourcePort.getName())) {
					port = sourcePort;
					break;
				}
			}
			if (port == null) {
				port = objFact.createTPort();
				port.setName(superClassPort.getName());
				targetInterface.getPort().add(port);
			}
			for (TMessage message : superClassPort.getMessage()) {
				port.getMessage().add(message);
			}
		}
	}

	private TPooslSpecification createPooslSpec(boolean asSHESim, Poosl poosl) {
		TPooslSpecification pooslSpec = objFact.createTPooslSpecification();
		// ######## Create DataClasses container and fill with classes ########
		TDataClasses pooslDataClasses = objFact.createTDataClasses();
		for (DataClass dc : poosl.getDataClasses()) {
			TDataClass dataClass = objFact.createTDataClass();
			dataClass.setName(dc.getName());
			dataClass.setNative(TBoolean.valueOf(dc.isNative() ? "TRUE" : "FALSE"));
			dataClass.setSuperClass(HelperFunctions.getCorrectedDataClassExtendsAsString(dc));
			dataClass.getInstanceVariable().addAll(translateDeclarations(dc.getInstanceVariables()));
			dataClass.getDataMethod().addAll(translateDataMethods(dc.getDataMethodsNamed()));
			dataClass.getDataMethod().addAll(translateDataMethods(dc.getDataMethodsBinaryOperator()));
			dataClass.getDataMethod().addAll(translateDataMethods(dc.getDataMethodsUnaryOperator()));
			pooslDataClasses.getDataClass().add(dataClass);
		}
		pooslSpec.setDataClasses(pooslDataClasses);

		// ##### Create ProcessClasses container and fill with classes #####
		TProcessClasses processClasses = objFact.createTProcessClasses();
		for (ProcessClass pc : poosl.getProcessClasses()) {
			TProcessClass processClass = objFact.createTProcessClass();
			processClass.setName(pc.getName());
			processClass.setSuperClass(pc.getSuperClass());
			processClass.getInstanceVariable().addAll(translateDeclarations(pc.getInstanceVariables()));
			processClass.getInstantiationParameter().addAll(translateDeclarations(pc.getParameters()));
			TInterface interFace = objFact.createTInterface();
			EList<MessageSignature> receiveMessages = pc.getReceiveMessages();
			EList<MessageSignature> sendMessages = pc.getSendMessages();
			interFace.getPort().addAll(translatePorts(pc.getPorts()));
			translateMessages(interFace, receiveMessages, sendMessages);
			processClass.setInterface(interFace);
			processClass.getProcessMethod().addAll(translateProcessMethods(pc.getMethods()));

			ProcessMethodCall pcInitialMethodCall = pc.getInitialMethodCall();
			if (pcInitialMethodCall != null) {
				TInitialMethodCall initialMethodCall = objFact.createTInitialMethodCall();
				TInitialProcessMethodCall initialProcessMethodCall = objFact.createTInitialProcessMethodCall();
				initialProcessMethodCall.setMethodName(pcInitialMethodCall.getMethod());
				initialProcessMethodCall.setStmtHandle(modelInfo.addMapping(pcInitialMethodCall));
				for (Expression e : pcInitialMethodCall.getInputArguments()) {
					initialProcessMethodCall.getArgument().add(translateExpression(e));
				}
				for (OutputVariable v : pcInitialMethodCall.getOutputVariables()) {
					initialProcessMethodCall.getReturnVariable().add(v.getVariable());

				}
				if (asSHESim) {
					initialMethodCall.setMethodCallText(parseComments(NodeModelUtils.getNode(pcInitialMethodCall)));
				}
				initialMethodCall.setProcessMethodCall(initialProcessMethodCall);
				processClass.setInitialMethodCall(initialMethodCall);
			}
			processClasses.getProcessClass().add(processClass);
			// Post processing info
			postProcessingClassTypes.put(pc.getName(), TInstanceType.PROCESS);
			postProcessingProcessClassNames.put(pc.getName(), processClass);

		}
		pooslSpec.setProcessClasses(processClasses);

		// ####### Create Clusterclasses container and fill with classes #####
		TClusterClasses clusterClasses = objFact.createTClusterClasses();
		for (ClusterClass cc : poosl.getClusterClasses()) {
			TClusterClass clusterClass = objFact.createTClusterClass();
			TClusterInterface clusterInterface = objFact.createTClusterInterface();
			clusterInterface.getPort().addAll(translatePorts(cc.getPorts()));
			clusterClass.setInterface(clusterInterface);
			clusterClass.getChannel().addAll(translateChannelsClusterClass(cc.getChannels()));
			clusterClass.getChannel().addAll(checkUnconnectedExternalPorts(cc));
			clusterClasses.getClusterClass().add(clusterClass);
			clusterClass.setName(cc.getName());
			clusterClass.getInstantiationParameter().addAll(translateDeclarations(cc.getParameters()));
			clusterClass.getInstance().addAll(translateInstances(cc.getInstances()));
			// Post processing info
			postProcessingClassTypes.put(cc.getName(), TInstanceType.CLUSTER);
		}
		pooslSpec.setClusterClasses(clusterClasses);

		// ################ Create TopLevelSpecification container and fill with
		// classes #####################
		ClusterClass system = HelperFunctions.getSystem(poosl);
		if (system != null) {
			TTopLevelSpecification topLevelSpecification = objFact.createTTopLevelSpecification();
			topLevelSpecification.getChannel().addAll(translateChannelsSystem(system.getChannels()));
			topLevelSpecification.getInstance().addAll(translateInstances(system.getInstances()));
			pooslSpec.setTopLevelSpecification(topLevelSpecification);
		}
		return pooslSpec;
	}

	private void translateMessages(TInterface interFace, EList<MessageSignature> receiveMessages,
			EList<MessageSignature> sendMessages) {
		for (MessageSignature messageSignature : sendMessages) {
			TMessage message = objFact.createTMessage();
			message.setType(TMessageType.WRITE);
			translateMessagePart(interFace, messageSignature, message);
		}
		for (MessageSignature messageSignature : receiveMessages) {
			TMessage message = objFact.createTMessage();
			message.setType(TMessageType.READ);
			translateMessagePart(interFace, messageSignature, message);
		}
	}

	private void translateMessagePart(TInterface interFace, MessageSignature messageSignature, TMessage message) {
		String portName = messageSignature.getPort().getPort();
		message.setName(messageSignature.getName());
		for (MessageParameter messageParameter : messageSignature.getParameters()) {
			TPooslTypeAttribute typeAttribute = objFact.createTPooslTypeAttribute();
			typeAttribute.setType(messageParameter.getType());
			message.getArgument().add(typeAttribute);
		}
		TPort destinationPort = null;
		for (TPort port : interFace.getPort()) {
			if (portName.equals(port.getName())) {
				destinationPort = port;
				break;
			}
		}
		if (destinationPort == null) {
			destinationPort = objFact.createTPort();
			destinationPort.setName(portName);
			interFace.getPort().add(destinationPort);
		}
		destinationPort.getMessage().add(message);
	}

	private List<TPort> translatePorts(EList<Port> ports) {
		List<TPort> translatedPorts = new ArrayList<>();
		for (Port port : ports) {
			TPort translatedPort = objFact.createTPort();
			translatedPort.setName(port.getName());
			translatedPorts.add(translatedPort);
		}
		return translatedPorts;
	}

	private Collection<TChannel> checkUnconnectedExternalPorts(ClusterClass cc) {
		List<TChannel> newChannels = new ArrayList<>();
		for (Port port : cc.getPorts()) {
			boolean found = false;
			for (Channel channel : cc.getChannels()) {
				if (port.equals(channel.getExternalPort())) {
					found = true;
				}
			}
			if (!found) {
				TChannel channel = objFact.createTChannel();
				channel.setName("Unconnected" + port.getName());
				channel.setOutputPort(port.getName());
				newChannels.add(channel);
			}
		}
		return newChannels;
	}

	private List<TChannelInternal> translateChannelsSystem(EList<Channel> channels) {
		List<TChannelInternal> translatedChannels = new ArrayList<>();
		for (Channel c : channels) {
			TChannelInternal channelInternal = objFact.createTChannelInternal();
			channelInternal.setName(String.valueOf(getChannelIndex(c)));
			translatedChannels.add(channelInternal);
		}
		return translatedChannels;
	}

	private List<TChannel> translateChannelsClusterClass(EList<Channel> channels) {
		List<TChannel> translatedChannels = new ArrayList<>();
		for (Channel c : channels) {
			TChannel channel = objFact.createTChannel();
			channel.setName(String.valueOf(getChannelIndex(c)));
			if (c.getExternalPort() != null) {
				channel.setOutputPort(c.getExternalPort().getName());
			}
			translatedChannels.add(channel);
		}
		return translatedChannels;
	}

	public static int getChannelIndex(Channel ch) {
		EObject container = ch.eContainer();
		if (container instanceof ClusterClass) {
			return ((ClusterClass) container).getChannels().indexOf(ch);
		}
		return 0;
	}

	private List<TInstance> translateInstances(EList<Instance> instances) {
		List<TInstance> translatedInstances = new ArrayList<>();
		for (Instance i : instances) {
			TInstance instance = objFact.createTInstance();
			instance.setName(i.getName());
			instance.setClazz(i.getClassDefinition());
			for (InstanceParameter a : i.getInstanceParameters()) {
				instance.getInstantiationExpression().add(translateInstanceParameter(a));
			}
			for (Channel ch : ((ClusterClass) i.eContainer()).getChannels()) {
				instance.getConnection().addAll(translateInstancePorts(ch, i));
			}
			translatedInstances.add(instance);
			// Post processing info
			postProcessingInstances.add(instance);
		}
		return translatedInstances;
	}

	private TInstantiationExpression translateInstanceParameter(InstanceParameter a) {
		TInstantiationExpression instantiationExpression = objFact.createTInstantiationExpression();
		instantiationExpression.setParameterName(a.getParameter());
		instantiationExpression.setBodyExpression(translateExpression(a.getExpression()));
		if (isSHESimXml) {
			instantiationExpression.setBodyText(parseComments(NodeModelUtils.getNode(a.getExpression())));
		}
		return instantiationExpression;
	}

	private List<TConnection> translateInstancePorts(Channel channel, Instance i) {
		List<TConnection> translatedConnections = new ArrayList<>();
		for (InstancePort instancePort : channel.getInstancePorts()) {
			if (instancePort.getInstance().equals(i)) {
				TConnection connection = objFact.createTConnection();
				connection.setPort((instancePort.getPort() != null) ? instancePort.getPort().getPort() : "");
				connection.setChannel(String.valueOf(getChannelIndex(channel)));
				translatedConnections.add(connection);
			}
		}
		return translatedConnections;
	}

	private List<TProcessMethod> translateProcessMethods(EList<ProcessMethod> methods) {
		List<TProcessMethod> processMethods = new ArrayList<>();
		for (ProcessMethod pm : methods) {
			TProcessMethod processMethod = objFact.createTProcessMethod();
			processMethod.setName(pm.getName());
			processMethod.setBodyStatement(translateStatement(pm.getBody()));
			if (pm.getBody() != null && isSHESimXml) {
				processMethod.setBodyText(parseComments(NodeModelUtils.getNode(pm)) + ".");
			}
			processMethod.getOutputParameter().addAll(translateDeclarations(pm.getOutputParameters()));
			processMethod.getArgument().addAll(translateDeclarations(pm.getInputParameters()));
			processMethod.getLocalVariable().addAll(translateDeclarations(pm.getLocalVariables()));
			processMethods.add(processMethod);
		}
		return processMethods;
	}

	private List<TDataMethod> translateDataMethods(EList<? extends DataMethod> methods) {
		List<TDataMethod> dataMethods = new ArrayList<>();
		for (DataMethod dm : methods) {
			TDataMethod dataMethod = objFact.createTDataMethod();
			if (dm instanceof DataMethodNamed) {
				dataMethod.setName(((DataMethodNamed) dm).getName());
			} else if (dm instanceof DataMethodBinaryOperator) {
				dataMethod.setName(((DataMethodBinaryOperator) dm).getName().getLiteral());
			} else if (dm instanceof DataMethodUnaryOperator) {
				dataMethod.setName(((DataMethodUnaryOperator) dm).getName().getLiteral());
			}
			dataMethod.setNative(TBoolean.valueOf(dm.isNative() ? "TRUE" : "FALSE"));
			dataMethod.setReturnType(dm.getReturnType());
			if (dm.getBody() != null) {
				dataMethod.setBodyExpression(translateExpression(dm.getBody()));
			}
			if (dm.getBody() != null && isSHESimXml) {
				dataMethod.setBodyText(parseComments(NodeModelUtils.getNode(dm)) + ".");
			}
			dataMethod.getArgument().addAll(translateDeclarations(dm.getParameters()));
			dataMethod.getLocalVariable().addAll(translateDeclarations(dm.getLocalVariables()));
			dataMethods.add(dataMethod);
		}
		return dataMethods;
	}

	private List<TTypedVariable> translateDeclarations(EList<Declaration> declarations) {
		List<TTypedVariable> variables = new ArrayList<>();
		for (Declaration d : declarations) {
			for (Variable v : d.getVariables()) {
				TTypedVariable typedVariable = objFact.createTTypedVariable();
				typedVariable.setName(v.getName());
				typedVariable.setType(d.getType());
				variables.add(typedVariable);
			}
		}
		return variables;
	}

	private TExpression translateExpression(Expression ex) {
		TExpression expression = objFact.createTExpression();
		translateExpression(ex, expression);
		return expression;
	}

	private void translateExpression(Expression ex, TExpression expression) {
		if (ex instanceof ReturnExpression) {
			expression.getSequenceOfExpressionsOrAssignmentOrDataMethodCall().add(
					objFact.createTExpressionsReturn(translateExpression(((ReturnExpression) ex).getExpression())));
		} else if (ex instanceof BooleanConstant) {
			TConstant constant = objFact.createTConstant();
			constant.setType(TConstantType.BOOLEAN);
			constant.setValue(((BooleanConstant) ex).getValue());
			expression.getSequenceOfExpressionsOrAssignmentOrDataMethodCall()
					.add(objFact.createTExpressionsConstant(constant));
		} else if (ex instanceof IntegerConstant) {
			TConstant constant = objFact.createTConstant();
			constant.setType(TConstantType.INTEGER);
			constant.setValue(getTokenTextWithoutHiddenTokens(NodeModelUtils.getNode(ex)));
			expression.getSequenceOfExpressionsOrAssignmentOrDataMethodCall()
					.add(objFact.createTExpressionsConstant(constant));
		} else if (ex instanceof CharacterConstant) {
			TConstant constant = objFact.createTConstant();
			constant.setType(TConstantType.CHARACTER);
			constant.setValue(((CharacterConstant) ex).getValue());
			expression.getSequenceOfExpressionsOrAssignmentOrDataMethodCall()
					.add(objFact.createTExpressionsConstant(constant));
		} else if (ex instanceof RealConstant) {
			TConstant constant = objFact.createTConstant();
			constant.setType(TConstantType.REAL);
			constant.setValue(getTokenTextWithoutHiddenTokens(NodeModelUtils.getNode(ex)));
			expression.getSequenceOfExpressionsOrAssignmentOrDataMethodCall()
					.add(objFact.createTExpressionsConstant(constant));
		} else if (ex instanceof StringConstant) {
			TConstant constant = objFact.createTConstant();
			constant.setType(TConstantType.STRING);
			/*
			 * Currently there is a workaround in Rotalumis that removes the outer most
			 * double quotes This is legacy code because a string containing of ONE double
			 * quote would look like this in SHESim """" 2 Quotes to have a valid string
			 * representation. and 1 quote to escape the actual quote. there for we add the
			 * additional surrounding quotes here.
			 */
			constant.setValue(((StringConstant) ex).getValue());
			expression.getSequenceOfExpressionsOrAssignmentOrDataMethodCall()
					.add(objFact.createTExpressionsConstant(constant));
		} else if (ex instanceof NilConstant) {
			expression.getSequenceOfExpressionsOrAssignmentOrDataMethodCall()
					.add(objFact.createTExpressionsNil(objFact.createTEmpty()));
		} else if (ex instanceof SelfExpression) {
			expression.getSequenceOfExpressionsOrAssignmentOrDataMethodCall()
					.add(objFact.createTExpressionsSelf(objFact.createTEmpty()));
		} else if (ex instanceof VariableExpression) {
			TVariableRef var = translateVariable(((VariableExpression) ex));
			expression.getSequenceOfExpressionsOrAssignmentOrDataMethodCall()
					.add(objFact.createTExpressionsVariable(var));
		} else if (ex instanceof IfExpression) {
			TIfExpression ifExpression = objFact.createTIfExpression();
			ifExpression.setCondition(translateExpression(((IfExpression) ex).getCondition()));
			if (((IfExpression) ex).getElseClause() != null) {
				ifExpression.setElse(translateExpression(((IfExpression) ex).getElseClause()));
			}
			ifExpression.setThen(translateExpression(((IfExpression) ex).getThenClause()));
			expression.getSequenceOfExpressionsOrAssignmentOrDataMethodCall()
					.add(objFact.createTExpressionsIf(ifExpression));
		} else if (ex instanceof AssignmentExpression) {
			TAssignment assignment = objFact.createTAssignment();
			AssignmentExpression assignmentExpression = (AssignmentExpression) ex;
			assignment.setVariableName(
					(assignmentExpression.getVariable() != null) ? assignmentExpression.getVariable() : "");
			translateExpression(assignmentExpression.getExpression(), assignment);
			expression.getSequenceOfExpressionsOrAssignmentOrDataMethodCall()
					.add(objFact.createTExpressionsAssignment(assignment));
		} else if (ex instanceof BinaryOperatorExpression) {
			TBinaryOperation binaryOperation = objFact.createTBinaryOperation();
			binaryOperation.setStmtHandle(modelInfo.addMapping(ex, false));
			binaryOperation.setLeftOperand(translateExpression(((BinaryOperatorExpression) ex).getLeftOperand()));
			binaryOperation.setRightOperand(translateExpression(((BinaryOperatorExpression) ex).getRightOperand()));
			binaryOperation
					.setOperator(TBinaryOperator.fromValue(((BinaryOperatorExpression) ex).getName().getLiteral()));
			expression.getSequenceOfExpressionsOrAssignmentOrDataMethodCall()
					.add(objFact.createTExpressionsBinaryOperation(binaryOperation));
		} else if (ex instanceof UnaryOperatorExpression) {
			TUnaryOperation unaryOperation = objFact.createTUnaryOperation();
			unaryOperation.setStmtHandle(modelInfo.addMapping(ex, false));
			unaryOperation.setOperand(translateExpression(((UnaryOperatorExpression) ex).getOperand()));
			unaryOperation.setOperator(TUnaryOperator.fromValue(((UnaryOperatorExpression) ex).getName().getLiteral()));
			expression.getSequenceOfExpressionsOrAssignmentOrDataMethodCall()
					.add(objFact.createTExpressionsUnaryOperation(unaryOperation));
		} else if (ex instanceof NewExpression) {
			TObjectCreation objectCreation = objFact.createTObjectCreation();
			objectCreation.setDataClass(((NewExpression) ex).getDataClass());
			expression.getSequenceOfExpressionsOrAssignmentOrDataMethodCall()
					.add(objFact.createTExpressionsNew(objectCreation));
		} else if (ex instanceof CurrentTimeExpression) {
			TCurrentTime currentTime = objFact.createTCurrentTime();
			expression.getSequenceOfExpressionsOrAssignmentOrDataMethodCall()
					.add(objFact.createTExpressionsCurrentTime(currentTime));
		} else if (ex instanceof DataMethodCallExpression) {
			TDataMethodCall dataMethodCall = objFact.createTDataMethodCall();
			dataMethodCall.setStmtHandle(modelInfo.addMapping(ex, false));
			dataMethodCall.setMethodName(((DataMethodCallExpression) ex).getName());
			dataMethodCall.setReceiver(translateExpression(((DataMethodCallExpression) ex).getTarget()));
			for (Expression argumentExpression : ((DataMethodCallExpression) ex).getArguments()) {
				dataMethodCall.getArgument().add(translateExpression(argumentExpression));
			}
			dataMethodCall
					.setSuper(TBoolean.valueOf(((DataMethodCallExpression) ex).isOnSuperClass() ? "TRUE" : "FALSE"));
			expression.getSequenceOfExpressionsOrAssignmentOrDataMethodCall()
					.add(objFact.createTExpressionsDataMethodCall(dataMethodCall));
		} else if (ex instanceof ExpressionSequence) {
			TExpressions expressions = objFact.createTExpressions();
			for (Expression e : ((ExpressionSequence) ex).getExpressions()) {
				expressions.getSequenceOfExpressionsOrAssignmentOrDataMethodCall()
						.addAll(translateExpression(e).getSequenceOfExpressionsOrAssignmentOrDataMethodCall());
			}
			expression.getSequenceOfExpressionsOrAssignmentOrDataMethodCall()
					.add(objFact.createTExpressionsSequenceOfExpressions(expressions));
		} else if (ex instanceof WhileExpression) {
			TWhileExpression whileExpression = objFact.createTWhileExpression();
			whileExpression.setCondition(translateExpression(((WhileExpression) ex).getCondition()));
			whileExpression.setBody(translateExpression(((WhileExpression) ex).getBody()));
			expression.getSequenceOfExpressionsOrAssignmentOrDataMethodCall()
					.add(objFact.createTExpressionsWhile(whileExpression));
		} else if (ex instanceof SwitchExpression) {
			SwitchExpression switchExpression = (SwitchExpression) ex;
			TSwitchExpression tSwitchExpression = objFact.createTSwitchExpression();
			tSwitchExpression.setControlExpression(translateExpression(switchExpression.getExpression()));

			if (switchExpression.getDefaultBody() != null) {
				tSwitchExpression.setDefault(translateExpression(switchExpression.getDefaultBody()));
			}

			for (SwitchExpressionCase switchExpressionCase : switchExpression.getCases()) {
				TSwitchCaseExpression tExpressionCase = objFact.createTSwitchCaseExpression();
				tExpressionCase.setValueExpression(translateExpression(switchExpressionCase.getValue()));
				tExpressionCase.setBody(translateExpression(switchExpressionCase.getBody()));
				tSwitchExpression.getCase().add(tExpressionCase);
			}

			expression.getSequenceOfExpressionsOrAssignmentOrDataMethodCall()
					.add(objFact.createTExpressionsSwitch(tSwitchExpression));
		} else {
			LOGGER.warning("Unimplemented expression: " + ex.getClass().getName());
		}
	}

	private TStatement translateStatement(Statement st) {
		TStatement statement = objFact.createTStatement();
		if (st instanceof AbortStatement) {
			TAbort abort = objFact.createTAbort();
			abort.setStmtHandle(modelInfo.addMapping((AbortStatement) st));
			abort.setNormalBehavior(translateStatement(((AbortStatement) st).getNormalClause()));
			abort.setAbortingBehavior(translateStatement(((AbortStatement) st).getAbortingClause()));
			statement.getSequenceOfStatementsOrParallelStatementsOrSelectStatements()
					.add(objFact.createTStatementsAbort(abort));
		} else if (st instanceof StatementSequence) {
			TStatements sequence = objFact.createTStatements();
			sequence.setStmtHandle(modelInfo.addMapping((StatementSequence) st));
			for (Statement tempStatement : ((StatementSequence) st).getStatements()) {
				TStatement translatedStatement = translateStatement(tempStatement);
				sequence.getSequenceOfStatementsOrParallelStatementsOrSelectStatements()
						.addAll(translatedStatement.getSequenceOfStatementsOrParallelStatementsOrSelectStatements());
			}
			statement.getSequenceOfStatementsOrParallelStatementsOrSelectStatements()
					.add(objFact.createTStatementsSequenceOfStatements(sequence));
		} else if (st instanceof SkipStatement) {
			TEmpty skip = objFact.createTEmpty();
			skip.setStmtHandle(modelInfo.addMapping((SkipStatement) st));
			statement.getSequenceOfStatementsOrParallelStatementsOrSelectStatements()
					.add(objFact.createTStatementsSkip(skip));
		} else if (st instanceof SendStatement) {
			TMessageSend messageSend = objFact.createTMessageSend();
			messageSend.setStmtHandle(modelInfo.addMapping((SendStatement) st));
			String messagePortName = ((PortReference) ((SendStatement) st).getPortDescriptor()).getPort();
			messageSend.setMessageName(((SendStatement) st).getName());
			messageSend.setPort(messagePortName);
			if (((SendStatement) st).getPostCommunicationExpression() != null) {
				messageSend.setAtomicExpression(
						translateExpression(((SendStatement) st).getPostCommunicationExpression()));
			}
			for (Expression ex : ((SendStatement) st).getArguments()) {
				messageSend.getArgument().add(translateExpression(ex));
			}
			statement.getSequenceOfStatementsOrParallelStatementsOrSelectStatements()
					.add(objFact.createTStatementsMessageSend(messageSend));
		} else if (st instanceof ReceiveStatement) {
			TMessageReceive messageReceive = objFact.createTMessageReceive();
			messageReceive.setStmtHandle(modelInfo.addMapping((ReceiveStatement) st));
			String messagePortName = ((PortReference) ((ReceiveStatement) st).getPortDescriptor()).getPort();
			messageReceive.setMessageName(((ReceiveStatement) st).getName());
			messageReceive.setPort(messagePortName);
			if (((ReceiveStatement) st).getPostCommunicationExpression() != null) {
				messageReceive.setAtomicExpression(
						translateExpression(((ReceiveStatement) st).getPostCommunicationExpression()));
			}
			if (((ReceiveStatement) st).getReceptionCondition() != null) {
				messageReceive.setCondition(translateExpression(((ReceiveStatement) st).getReceptionCondition()));
			}
			for (OutputVariable v : ((ReceiveStatement) st).getVariables()) {
				messageReceive.getVariable().add(v.getVariable());
			}
			statement.getSequenceOfStatementsOrParallelStatementsOrSelectStatements()
					.add(objFact.createTStatementsMessageReceive(messageReceive));
		} else if (st instanceof IfStatement) {
			TIfStatement ifStatement = objFact.createTIfStatement();
			ifStatement.setStmtHandle(modelInfo.addMapping((IfStatement) st));
			ifStatement.setCondition(translateExpression(((IfStatement) st).getCondition()));
			ifStatement.setThen(translateStatement(((IfStatement) st).getThenClause()));
			if (((IfStatement) st).getElseClause() != null) {
				ifStatement.setElse(translateStatement(((IfStatement) st).getElseClause()));
			}
			statement.getSequenceOfStatementsOrParallelStatementsOrSelectStatements()
					.add(objFact.createTStatementsIf(ifStatement));
		} else if (st instanceof ParStatement) {
			TStatements listOfParStatements = objFact.createTStatements();
			listOfParStatements.setStmtHandle(modelInfo.addMapping((ParStatement) st));
			for (Statement parStatement : ((ParStatement) st).getClauses()) {
				TStatement translatedStatement = translateStatement(parStatement);
				listOfParStatements.getSequenceOfStatementsOrParallelStatementsOrSelectStatements()
						.addAll(translatedStatement.getSequenceOfStatementsOrParallelStatementsOrSelectStatements());
			}
			statement.getSequenceOfStatementsOrParallelStatementsOrSelectStatements()
					.add(objFact.createTStatementsParallelStatements(listOfParStatements));
		} else if (st instanceof SelStatement) {
			TStatements listOfSelStatements = objFact.createTStatements();
			listOfSelStatements.setStmtHandle(modelInfo.addMapping((SelStatement) st));
			for (Statement selStatement : ((SelStatement) st).getClauses()) {
				TStatement translatedStatement = translateStatement(selStatement);
				listOfSelStatements.getSequenceOfStatementsOrParallelStatementsOrSelectStatements()
						.addAll(translatedStatement.getSequenceOfStatementsOrParallelStatementsOrSelectStatements());
			}
			statement.getSequenceOfStatementsOrParallelStatementsOrSelectStatements()
					.add(objFact.createTStatementsSelectStatements(listOfSelStatements));
		} else if (st instanceof DelayStatement) {
			TExpression delay = translateExpression(((DelayStatement) st).getExpression());
			delay.setStmtHandle(modelInfo.addMapping((DelayStatement) st));
			JAXBElement<TExpression> jaxbDelay = objFact.createTStatementsDelay(delay);
			statement.getSequenceOfStatementsOrParallelStatementsOrSelectStatements().add(jaxbDelay);
		} else if (st instanceof ExpressionStatement) {
			TExpression expression = translateExpression(((ExpressionStatement) st).getExpression());
			expression.setStmtHandle(modelInfo.addMapping((ExpressionStatement) st));
			JAXBElement<TExpression> jaxbExpression = objFact.createTStatementsExpression(expression);
			statement.getSequenceOfStatementsOrParallelStatementsOrSelectStatements().add(jaxbExpression);
		} else if (st instanceof GuardedStatement) {
			TGuard guard = objFact.createTGuard();
			guard.setStmtHandle(modelInfo.addMapping((GuardedStatement) st));
			guard.setStatement(translateStatement(((GuardedStatement) st).getStatement()));
			guard.setExpression(translateExpression(((GuardedStatement) st).getGuard()));
			statement.getSequenceOfStatementsOrParallelStatementsOrSelectStatements()
					.add(objFact.createTStatementsGuard(guard));

		} else if (st instanceof InterruptStatement) {
			TInterrupt interrupt = objFact.createTInterrupt();
			interrupt.setStmtHandle(modelInfo.addMapping((InterruptStatement) st));
			interrupt.setNormalBehavior(translateStatement(((InterruptStatement) st).getNormalClause()));
			interrupt.setInterruptingBehavior(translateStatement(((InterruptStatement) st).getInterruptingClause()));
			statement.getSequenceOfStatementsOrParallelStatementsOrSelectStatements()
					.add(objFact.createTStatementsInterrupt(interrupt));
		} else if (st instanceof ProcessMethodCall) {
			TProcessMethodCall processMethodCall = objFact.createTProcessMethodCall();
			processMethodCall.setStmtHandle(modelInfo.addMapping((ProcessMethodCall) st));
			String processMethodCallName = ((ProcessMethodCall) st).getMethod();
			processMethodCall.setMethodName(processMethodCallName);
			for (Expression ex : ((ProcessMethodCall) st).getInputArguments()) {
				processMethodCall.getArgument().add(translateExpression(ex));
			}
			for (OutputVariable v : ((ProcessMethodCall) st).getOutputVariables()) {
				processMethodCall.getReturnVariable().add(v.getVariable());
			}
			statement.getSequenceOfStatementsOrParallelStatementsOrSelectStatements()
					.add(objFact.createTStatementsProcessMethodCall(processMethodCall));
		} else if (st instanceof WhileStatement) {
			TWhileStatement whileStatement = objFact.createTWhileStatement();
			whileStatement.setStmtHandle(modelInfo.addMapping((WhileStatement) st));
			whileStatement.setCondition(translateExpression(((WhileStatement) st).getCondition()));
			whileStatement.setBody(translateStatement(((WhileStatement) st).getBody()));
			statement.getSequenceOfStatementsOrParallelStatementsOrSelectStatements()
					.add(objFact.createTStatementsWhile(whileStatement));
		} else if (st instanceof SwitchStatement) {
			SwitchStatement switchStatement = (SwitchStatement) st;
			TSwitchStatement tSwitchStatement = objFact.createTSwitchStatement();
			tSwitchStatement.setStmtHandle(modelInfo.addMapping(switchStatement));
			tSwitchStatement.setControlExpression(translateExpression(switchStatement.getExpression()));

			if (switchStatement.getDefaultBody() != null) {
				tSwitchStatement.setDefault(translateStatement(switchStatement.getDefaultBody()));
			}
			for (SwitchStatementCase switchStatementCase : switchStatement.getCases()) {
				TSwitchCaseStatement tStatementCase = objFact.createTSwitchCaseStatement();
				tStatementCase.setValueExpression(translateExpression(switchStatementCase.getValue()));
				tStatementCase.setStmtHandle(modelInfo.addMapping(switchStatementCase));
				tStatementCase.setBody(translateStatement(switchStatementCase.getBody()));
				tSwitchStatement.getCase().add(tStatementCase);
			}
			statement.getSequenceOfStatementsOrParallelStatementsOrSelectStatements()
					.add(objFact.createTStatementsSwitch(tSwitchStatement));

		} else {
			LOGGER.warning("Unimplemented statement: " + st.getClass().getName());
		}
		return statement;
	}

	private TVariableRef translateVariable(VariableExpression ex) {
		String name = (ex.getVariable() != null) ? ex.getVariable().trim() : "";
		TVariableRef variableRef = objFact.createTVariableRef();
		variableRef.setName(name);
		return variableRef;
	}

	private String parseComments(ICompositeNode iCompositeNode) {
		StringBuilder bodyText = new StringBuilder(iCompositeNode.getText());
		int commentsFound = 0;
		for (ILeafNode node : iCompositeNode.getLeafNodes()) {
			EObject grammerElement = node.getGrammarElement();
			if (grammerElement instanceof TerminalRule
					&& "SL_COMMENT".equals(((TerminalRule) grammerElement).getName())) {
				int startOffset = node.getOffset() - iCompositeNode.getTotalOffset() + commentsFound * 4;
				int endOffset = node.getEndOffset() - iCompositeNode.getTotalOffset() + commentsFound * 4;

				// Skip the optional line endings in terminal SL_COMMENT:
				// ('\r'? '\n')?
				if (bodyText.charAt(endOffset - 1) == '\n') {
					endOffset--;
					if (bodyText.charAt(endOffset - 1) == '\r') {
						endOffset--;
					}
				}

				bodyText.insert(startOffset, "/*");
				bodyText.insert(endOffset + 2, "*/");
				commentsFound++;
			}
		}
		return bodyText.toString();
	}

	/*
	 * Based on NodeModelUtils.getTokenText(INode node). Text from hidden tokens is
	 * removed (not replaced by a space).
	 */
	private static String getTokenTextWithoutHiddenTokens(INode node) {
		if (node instanceof ILeafNode)
			return ((ILeafNode) node).getText();
		else {
			StringBuilder builder = new StringBuilder(Math.max(node.getTotalLength(), 1));
			for (ILeafNode leaf : node.getLeafNodes()) {
				if (!leaf.isHidden()) {
					builder.append(leaf.getText());
				}
			}
			return builder.toString();
		}
	}
}
