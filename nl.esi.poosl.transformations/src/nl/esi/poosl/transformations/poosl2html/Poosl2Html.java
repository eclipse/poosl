package nl.esi.poosl.transformations.poosl2html;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.documentation.IEObjectDocumentationProvider;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.Declaration;
import nl.esi.poosl.MessageSignature;
import nl.esi.poosl.Poosl;
import nl.esi.poosl.Port;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.Variable;
import nl.esi.poosl.xtext.PooslRuntimeModule;
import nl.esi.poosl.xtext.PooslStandaloneSetup;
import nl.esi.poosl.xtext.custom.FormattingHelper;
import nl.esi.poosl.xtext.custom.PooslCache;

public class Poosl2Html {

	@Inject
	protected IEObjectDocumentationProvider documentationProvider;

	private static final Logger LOGGER = Logger.getLogger(Poosl2Html.class.getName());

	private static final String HTML_HEADER = "<html>\n<head>\n\t<style>body{font-family:Arial;} "
			+ "h3{font-size: 1.2em; margin-bottom: 0em;}h4{font-size: 1em; margin-bottom: 0em;}div.indented{margin-left:2em;}</style>\n</head>\n<body>";
	private static final String HTML_FOOTER = "\n</body>\n</html>";

	public static void main(String[] args) {
		String input = "";
		if (args.length > 0) {
			input = args[0];
		}
		if (!input.isEmpty()) {
			Injector pooslInjector = new PooslStandaloneSetup().createInjectorAndDoEMFRegistration();
			Poosl2Html headless = pooslInjector.getInstance(Poosl2Html.class);
			File fInput = new File(input);
			ResourceSet resourceSet = new ResourceSetImpl();
			resourceSet.getPackageRegistry().put(nl.esi.poosl.PooslPackage.eINSTANCE.getNsURI(),
					nl.esi.poosl.PooslPackage.eINSTANCE);
			URI uri = URI.createFileURI(fInput.getAbsolutePath());
			Resource resource = resourceSet.getResource(uri, true);
			headless.run((Poosl) resource.getContents().get(0), fInput.getAbsolutePath());
		} else {
			LOGGER.severe("please supply a valid input file");
		}
	}

	public static void generateDocumentation(String inputFile) {
		Injector pooslInjector = Guice.createInjector(new PooslRuntimeModule());
		Poosl2Html headless = pooslInjector.getInstance(Poosl2Html.class);
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(nl.esi.poosl.PooslPackage.eINSTANCE.getNsURI(),
				nl.esi.poosl.PooslPackage.eINSTANCE);
		URI uri = URI.createFileURI(inputFile);
		Resource resource = resourceSet.getResource(uri, true);
		if (resource.getContents().get(0) instanceof Poosl) {
			Poosl poosl = (Poosl) resource.getContents().get(0);
			headless.run(poosl, inputFile);
		}
	}

	private void run(Poosl poosl, String path) {
		StringBuilder html = new StringBuilder();
		String file = path.substring(path.lastIndexOf(File.separator) + 1);
		appendHtmlHeader(html);
		appendToc(html, poosl, file);
		appendHorizontalLine(html, 100);
		if (!poosl.getDataClasses().isEmpty()) {
			appendH1(html, "Data classes");
			for (DataClass dataClass : poosl.getDataClasses()) {
				appendHorizontalLine(html, 50);
				String name = dataClass.getName();
				String fullName = name;
				String extendsClass = dataClass.getSuperClass();
				if (extendsClass != null) {
					IEObjectDescription superClass = PooslCache.get(poosl.eResource()).getDataClass(extendsClass);
					if (superClass != null
							&& superClass.getEObjectURI().trimFragment().equals(poosl.eResource().getURI())) {
						extendsClass = "<a href=\"#data-" + extendsClass + "\">" + extendsClass + "</a>";
					}
					fullName += " (extends " + extendsClass + ")";
				}
				String doc = documentationProvider.getDocumentation(dataClass);
				appendClassToHtml(html, name, fullName, "data", doc);
				appendVariables(html, dataClass.getInstanceVariables());
				appendH3(html, "Methods:");
				html.append("<div class=\"indented\">");
				// Get all datamethods from the dataclass
				List<DataMethod> dataMethods = new ArrayList<>();
				dataMethods.addAll(dataClass.getDataMethodsUnaryOperator());
				dataMethods.addAll(dataClass.getDataMethodsBinaryOperator());
				dataMethods.addAll(dataClass.getDataMethodsNamed());
				// Add them all to HTML
				for (DataMethod dataMethod : dataMethods) {
					StringBuilder nameBuffer = new StringBuilder();
					FormattingHelper.formatDataMethod(nameBuffer, dataMethod, false);
					doc = documentationProvider.getDocumentation(dataMethod);
					appendNamedElementToHtml(html, nameBuffer.toString(), doc);
				}
				html.append("</div>");
			}
			appendHorizontalLine(html, 100);
		}
		if (!poosl.getProcessClasses().isEmpty()) {
			appendH1(html, "Process classes");
			for (ProcessClass processClass : poosl.getProcessClasses()) {
				appendHorizontalLine(html, 50);
				String name = processClass.getName();
				String fullName = name;
				if (processClass.getSuperClass() != null) {
					String extendsClass = processClass.getSuperClass();
					IEObjectDescription superClass = PooslCache.get(processClass.eResource())
							.getProcessClass(processClass.getSuperClass());
					if (superClass.getEObjectURI().trimFragment()
							.equals(EcoreUtil.getURI(processClass).trimFragment())) {
						StringBuilder buf = new StringBuilder();
						buf.append("<a href=\"#process-");
						buf.append(extendsClass);
						buf.append("\">");
						buf.append(extendsClass);
						buf.append("</a>");
						extendsClass = buf.toString();
					}
					fullName += " (extends " + extendsClass + ")";
				}
				String doc = documentationProvider.getDocumentation(processClass);
				appendClassToHtml(html, name, fullName, "process", doc);
				// Add parameters
				appendH3(html, "Parameters:");
				html.append("<div class=\"indented\">");
				for (Declaration parameter : processClass.getParameters()) {
					String type = parameter.getType();
					for (Variable variable : parameter.getVariables()) {
						doc = documentationProvider.getDocumentation(variable);
						appendParameterToHtml(html, variable.getName(), type, doc);
					}
				}
				html.append("</div>");

				appendVariables(html, processClass.getInstanceVariables());

				// Add Ports
				appendH3(html, "Ports:");
				html.append("<div class=\"indented\">");
				for (Port port : processClass.getPorts()) {
					doc = documentationProvider.getDocumentation(port);
					appendNamedElementToHtml(html, port.getName(), doc);
				}
				html.append("</div>");

				// Add Message signatures
				appendH3(html, "Message signatures:");
				html.append("<div class=\"indented\">");

				for (MessageSignature msg : processClass.getReceiveMessages()) {
					StringBuilder buf = new StringBuilder();
					FormattingHelper.formatMessageSignature(buf, msg, false);
					doc = documentationProvider.getDocumentation(msg);
					appendNamedElementToHtml(html, buf.toString(), doc);
				}
				for (MessageSignature msg : processClass.getSendMessages()) {
					StringBuilder buf = new StringBuilder();
					FormattingHelper.formatMessageSignature(buf, msg, false);
					doc = documentationProvider.getDocumentation(msg);
					appendNamedElementToHtml(html, buf.toString(), doc);
				}
				html.append("</div>");

				// Add methods
				appendH3(html, "Methods:");
				html.append("<div class=\"indented\">");
				for (ProcessMethod processMethod : processClass.getMethods()) {
					StringBuilder nameBuffer = new StringBuilder();
					FormattingHelper.formatProcessMethod(nameBuffer, processMethod, false);
					doc = documentationProvider.getDocumentation(processMethod);
					appendNamedElementToHtml(html, nameBuffer.toString(), doc);
				}
				html.append("</div>");
			}
			appendHorizontalLine(html, 100);
		}
		if (hasNamedClusterClass(poosl)) {
			appendH1(html, "Cluster classes");
			for (ClusterClass clusterClass : poosl.getClusterClasses()) {
				if (clusterClass.getName() != null) {
					appendHorizontalLine(html, 50);
					String name = clusterClass.getName();
					String doc = documentationProvider.getDocumentation(clusterClass);
					appendClassToHtml(html, name, name, "cluster", doc);

					// Add parameters
					appendH3(html, "Parameters:");
					html.append("<div class=\"indented\">");
					for (Declaration parameter : clusterClass.getParameters()) {
						String type = parameter.getType();
						for (Variable variable : parameter.getVariables()) {
							doc = documentationProvider.getDocumentation(variable);
							appendParameterToHtml(html, variable.getName(), type, doc);
						}
					}
					html.append("</div>");

					// Add Ports
					appendH3(html, "Ports:");
					html.append("<div class=\"indented\">");
					for (Port port : clusterClass.getPorts()) {
						doc = documentationProvider.getDocumentation(port);
						appendNamedElementToHtml(html, port.getName(), doc);
					}
					html.append("</div>");
				}
			}
		}
		appendHtmlFooter(html);
		writeHtmlToFile(html, path);
	}

	private void appendVariables(StringBuilder html, EList<Declaration> declarations) {
		appendH3(html, "Variables:");
		html.append("<div class=\"indented\">");
		for (Declaration declaration : declarations) {
			for (Variable var : declaration.getVariables()) {
				String doc = documentationProvider.getDocumentation(var);
				StringBuilder nameBuffer = new StringBuilder();
				FormattingHelper.formatVariable(nameBuffer, var, false);
				appendNamedElementToHtml(html, nameBuffer.toString(), doc);
			}
		}
		html.append("</div>");
	}

	private void appendParameterToHtml(StringBuilder html, String name, String type, String doc) {
		html.append("<p><h4>").append(name).append(":").append(type).append("</h4>").append(doc != null ? doc : "")
				.append("</p>");
	}

	private void appendHorizontalLine(StringBuilder html, int width) {
		html.append("<hr width=\"").append(width).append("%\" align=\"left\">");
	}

	private void appendH1(StringBuilder html, String text) {
		html.append("<h1>").append(text).append("</h1>");
	}

	private void appendH3(StringBuilder html, String text) {
		html.append("<h3>").append(text).append("</h3>");
	}

	private void appendTocItem(StringBuilder html, String name, String type) {
		html.append("<li><a href=\"#").append(type).append("-").append(name).append("\">").append(name)
				.append("</a></li>");
	}

	private void appendClassToHtml(StringBuilder html, String name, String fullName, String type, String doc) {
		html.append("<h2><a id=\"").append(type).append("-").append(name).append("\"/>").append(fullName)
				.append("</h2>").append(doc != null ? doc : "");
	}

	private void appendNamedElementToHtml(StringBuilder html, String name, String doc) {
		html.append("<p><h4>").append(name).append("</h4>").append(doc != null ? doc : "").append("</p>");
	}

	private void appendHtmlHeader(StringBuilder html) {
		html.append(HTML_HEADER);
	}

	private void appendToc(StringBuilder html, Poosl poosl, String file) {
		html.append("<h1>").append(file).append("</h1>");
		if (!poosl.getDataClasses().isEmpty()) {
			html.append("<h2>Data classes:</h2><ul>");
			for (DataClass dataClass : poosl.getDataClasses()) {
				appendTocItem(html, dataClass.getName(), "data");
			}
			html.append("</ul>");
		}
		if (!poosl.getProcessClasses().isEmpty()) {
			html.append("<h2>Process classes:</h2><ul>");
			for (ProcessClass processClass : poosl.getProcessClasses()) {
				if (processClass.getName() != null) {
					appendTocItem(html, processClass.getName(), "process");
				}
			}
			html.append("</ul>");
		}
		if (hasNamedClusterClass(poosl)) {
			html.append("<h2>Cluster classes:</h2><ul>");
			for (ClusterClass clusterClass : poosl.getClusterClasses()) {
				if (clusterClass.getName() != null) {
					appendTocItem(html, clusterClass.getName(), "cluster");
				}
			}
			html.append("</ul>");
		}
	}
	
	private static boolean hasNamedClusterClass(Poosl poosl) {
		for (ClusterClass clusterClass : poosl.getClusterClasses()) {
			if (clusterClass.getName() != null) {
				return true;
			}
		}
		return false;
	}

	private void appendHtmlFooter(StringBuilder html) {
		html.append(HTML_FOOTER);
	}

	private void writeHtmlToFile(StringBuilder html, String path) {
		// Check if the simulator directory already exists
		File workingDirectory = new File(
				path.substring(0, path.lastIndexOf(File.separator)) + File.separator + "documentation");
		if (!workingDirectory.exists()) {
			workingDirectory.mkdir();
		}
		// Get the html path
		String htmlPath = createHTMLPath(path);
		try {
			PrintWriter fileWriter = new PrintWriter(htmlPath);
			fileWriter.append(html);
			fileWriter.close();
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.WARNING, e.getMessage(), e);
		}
	}

	private String createHTMLPath(String path) {
		StringBuilder stringBuilder = new StringBuilder(path);
		stringBuilder.replace(stringBuilder.lastIndexOf(File.separator), stringBuilder.lastIndexOf(File.separator),
				File.separator + "documentation");
		stringBuilder.replace(stringBuilder.lastIndexOf("."), stringBuilder.length(), ".html");
		return stringBuilder.toString();
	}
}
