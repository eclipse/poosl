/*******************************************************************************
 * Copyright (c) 2021 TNO/ESI
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    TNO/ESI - initial API and implementation
 *    Obeo - refactoring
 *******************************************************************************/
package org.eclipse.poosl.transformations.poosl2html;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.poosl.ClusterClass;
import org.eclipse.poosl.DataClass;
import org.eclipse.poosl.DataMethod;
import org.eclipse.poosl.Declaration;
import org.eclipse.poosl.MessageSignature;
import org.eclipse.poosl.Poosl;
import org.eclipse.poosl.Port;
import org.eclipse.poosl.ProcessClass;
import org.eclipse.poosl.ProcessMethod;
import org.eclipse.poosl.Variable;
import org.eclipse.poosl.xtext.PooslRuntimeModule;
import org.eclipse.poosl.xtext.PooslStandaloneSetup;
import org.eclipse.poosl.xtext.custom.FormattingHelper;
import org.eclipse.poosl.xtext.custom.PooslCache;
import org.eclipse.xtext.documentation.IEObjectDocumentationProvider;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * The Poosl2Html.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class Poosl2Html {

    private static final String HTML_HEADER = //
            "<html>\n<head>\n\t<style>" //$NON-NLS-1$
                    + "body{font-family:Arial;} " //$NON-NLS-1$
                    + "h3{font-size: 1.2em; margin-bottom: 0em;}" //$NON-NLS-1$
                    + "h4{font-size: 1em; margin-bottom: 0em;}" //$NON-NLS-1$
                    + "div.indented{margin-left:2em;}" //$NON-NLS-1$
                    + "</style>\n</head>\n<body>"; //$NON-NLS-1$

    private static final String HTML_FOOTER = "\n</body>\n</html>"; //$NON-NLS-1$

    private static final BiConsumer<String, Throwable> DEFAULT_TRACER = (message, throwable) -> {
        PrintStream err = System.err; // CHECKSTYLE concern 
        err.println(message);
    };

    @Inject
    private IEObjectDocumentationProvider documentationProvider;

    private BiConsumer<String, Throwable> tracer = DEFAULT_TRACER;

    public static void main(String[] args) {
        String input = ""; //$NON-NLS-1$
        if (args.length > 0) {
            input = args[0];
        }

        if (input.isEmpty()) {
            DEFAULT_TRACER.accept("Please supply a valid input file as 1rst argument.", null);
            return;
        }
        Injector pooslInjector = new PooslStandaloneSetup().createInjectorAndDoEMFRegistration();
        Poosl2Html headless = pooslInjector.getInstance(Poosl2Html.class);

        File fInput = new File(input);
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getPackageRegistry().put(org.eclipse.poosl.PooslPackage.eINSTANCE.getNsURI(),
                org.eclipse.poosl.PooslPackage.eINSTANCE);
        URI uri = URI.createFileURI(fInput.getAbsolutePath());
        Resource resource = resourceSet.getResource(uri, true);
        headless.run((Poosl) resource.getContents().get(0), fInput.getAbsolutePath());

    }

    private void setTracer(BiConsumer<String, Throwable> it) {
        tracer = it;
    }

    public static void generateDocumentation(String inputFile) {
        Injector pooslInjector = Guice.createInjector(new PooslRuntimeModule());
        Poosl2Html headless = pooslInjector.getInstance(Poosl2Html.class);

        ILog logger = Platform.getLog(Poosl2Html.class); // not static to handle stand-alone call
        headless.setTracer((message, err) -> logger.warn(message, err));

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getPackageRegistry().put(org.eclipse.poosl.PooslPackage.eINSTANCE.getNsURI(),
                org.eclipse.poosl.PooslPackage.eINSTANCE);
        URI uri = URI.createFileURI(inputFile);
        Resource resource = resourceSet.getResource(uri, true);
        if (resource.getContents().get(0) instanceof Poosl) {
            Poosl poosl = (Poosl) resource.getContents().get(0);
            headless.run(poosl, inputFile);
        }
    }

    // CHECKSTYLE:OFF text generation
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
                    IEObjectDescription superClass = PooslCache.get(poosl.eResource())
                            .getDataClass(extendsClass);
                    if (superClass != null && superClass.getEObjectURI().trimFragment()
                            .equals(poosl.eResource().getURI())) {
                        extendsClass = "<a href=\"#data-" + extendsClass + "\">" + extendsClass //$NON-NLS-1$//$NON-NLS-2$
                                + "</a>"; //$NON-NLS-1$
                    }
                    fullName += " (extends " + extendsClass + ")"; //$NON-NLS-1$ //$NON-NLS-2$
                }
                String doc = documentationProvider.getDocumentation(dataClass);
                appendClassToHtml(html, name, fullName, "data", doc); //$NON-NLS-1$
                appendVariables(html, dataClass.getInstanceVariables());
                appendH3(html, "Methods:");
                html.append("<div class=\"indented\">"); //$NON-NLS-1$
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
                html.append("</div>"); //$NON-NLS-1$
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
                        buf.append("<a href=\"#process-"); //$NON-NLS-1$
                        buf.append(extendsClass);
                        buf.append("\">"); //$NON-NLS-1$
                        buf.append(extendsClass);
                        buf.append("</a>"); //$NON-NLS-1$
                        extendsClass = buf.toString();
                    }
                    fullName += " (extends " + extendsClass + ")"; //$NON-NLS-1$ //$NON-NLS-2$
                }
                String doc = documentationProvider.getDocumentation(processClass);
                appendClassToHtml(html, name, fullName, "process", doc); //$NON-NLS-1$
                // Add parameters
                appendH3(html, "Parameters:");
                html.append("<div class=\"indented\">"); //$NON-NLS-1$
                for (Declaration parameter : processClass.getParameters()) {
                    String type = parameter.getType();
                    for (Variable variable : parameter.getVariables()) {
                        doc = documentationProvider.getDocumentation(variable);
                        appendParameterToHtml(html, variable.getName(), type, doc);
                    }
                }
                html.append("</div>"); //$NON-NLS-1$

                appendVariables(html, processClass.getInstanceVariables());

                // Add Ports
                appendH3(html, "Ports:");
                html.append("<div class=\"indented\">"); //$NON-NLS-1$
                for (Port port : processClass.getPorts()) {
                    doc = documentationProvider.getDocumentation(port);
                    appendNamedElementToHtml(html, port.getName(), doc);
                }
                html.append("</div>"); //$NON-NLS-1$

                // Add Message signatures
                appendH3(html, "Message signatures:");
                html.append("<div class=\"indented\">"); //$NON-NLS-1$

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
                html.append("</div>"); //$NON-NLS-1$

                // Add methods
                appendH3(html, "Methods:");
                html.append("<div class=\"indented\">"); //$NON-NLS-1$
                for (ProcessMethod processMethod : processClass.getMethods()) {
                    StringBuilder nameBuffer = new StringBuilder();
                    FormattingHelper.formatProcessMethod(nameBuffer, processMethod, false);
                    doc = documentationProvider.getDocumentation(processMethod);
                    appendNamedElementToHtml(html, nameBuffer.toString(), doc);
                }
                html.append("</div>"); //$NON-NLS-1$
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
                    appendClassToHtml(html, name, name, "cluster", doc); //$NON-NLS-1$

                    // Add parameters
                    appendH3(html, "Parameters:");
                    html.append("<div class=\"indented\">"); //$NON-NLS-1$
                    for (Declaration parameter : clusterClass.getParameters()) {
                        String type = parameter.getType();
                        for (Variable variable : parameter.getVariables()) {
                            doc = documentationProvider.getDocumentation(variable);
                            appendParameterToHtml(html, variable.getName(), type, doc);
                        }
                    }
                    html.append("</div>"); //$NON-NLS-1$

                    // Add Ports
                    appendH3(html, "Ports:");
                    html.append("<div class=\"indented\">"); //$NON-NLS-1$
                    for (Port port : clusterClass.getPorts()) {
                        doc = documentationProvider.getDocumentation(port);
                        appendNamedElementToHtml(html, port.getName(), doc);
                    }
                    html.append("</div>"); //$NON-NLS-1$
                }
            }
        }
        appendHtmlFooter(html);
        writeHtmlToFile(html, path);
    }
    // CHECKSTYLE:ON

    private void appendVariables(StringBuilder html, EList<Declaration> declarations) {
        appendH3(html, "Variables:");
        html.append("<div class=\"indented\">"); //$NON-NLS-1$
        for (Declaration declaration : declarations) {
            for (Variable var : declaration.getVariables()) {
                String doc = documentationProvider.getDocumentation(var);
                StringBuilder nameBuffer = new StringBuilder();
                FormattingHelper.formatVariable(nameBuffer, var, false);
                appendNamedElementToHtml(html, nameBuffer.toString(), doc);
            }
        }
        html.append("</div>"); //$NON-NLS-1$
    }

    private void appendParameterToHtml(StringBuilder html, String name, String type, String doc) {
        html.append("<p><h4>").append(name).append(":").append(type).append("</h4>") //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
                .append(doc != null ? doc : "").append("</p>"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    private void appendHorizontalLine(StringBuilder html, int width) {
        html.append("<hr width=\"").append(width).append("%\" align=\"left\">"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    private void appendH1(StringBuilder html, String text) {
        html.append("<h1>").append(text).append("</h1>"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    private void appendH3(StringBuilder html, String text) {
        html.append("<h3>").append(text).append("</h3>"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    private void appendTocItem(StringBuilder html, String name, String type) {
        html.append("<li><a href=\"#").append(type).append("-").append(name).append("\">") //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
                .append(name).append("</a></li>"); //$NON-NLS-1$
    }

    private void appendClassToHtml(
            StringBuilder html, String name, String fullName, String type, String doc) {
        html.append("<h2><a id=\"").append(type).append("-").append(name).append("\"/>") //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
                .append(fullName).append("</h2>").append(doc != null ? doc : ""); //$NON-NLS-1$ //$NON-NLS-2$
    }

    private void appendNamedElementToHtml(StringBuilder html, String name, String doc) {
        html.append("<p><h4>").append(name).append("</h4>").append(doc != null ? doc : "") //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
                .append("</p>"); //$NON-NLS-1$
    }

    private void appendHtmlHeader(StringBuilder html) {
        html.append(HTML_HEADER);
    }

    // CHECKSTYLE:OFF text generation
    private void appendToc(StringBuilder html, Poosl poosl, String file) {
        html.append("<h1>").append(file).append("</h1>"); //$NON-NLS-1$ //$NON-NLS-2$
        if (!poosl.getDataClasses().isEmpty()) {
            html.append("<h2>Data classes:</h2><ul>");
            for (DataClass dataClass : poosl.getDataClasses()) {
                appendTocItem(html, dataClass.getName(), "data"); //$NON-NLS-1$
            }
            html.append("</ul>"); //$NON-NLS-1$
        }
        if (!poosl.getProcessClasses().isEmpty()) {
            html.append("<h2>Process classes:</h2><ul>");
            for (ProcessClass processClass : poosl.getProcessClasses()) {
                if (processClass.getName() != null) {
                    appendTocItem(html, processClass.getName(), "process"); //$NON-NLS-1$
                }
            }
            html.append("</ul>"); //$NON-NLS-1$
        }
        if (hasNamedClusterClass(poosl)) {
            html.append("<h2>Cluster classes:</h2><ul>");
            for (ClusterClass clusterClass : poosl.getClusterClasses()) {
                if (clusterClass.getName() != null) {
                    appendTocItem(html, clusterClass.getName(), "cluster"); //$NON-NLS-1$
                }
            }
            html.append("</ul>"); //$NON-NLS-1$
        }
    }
    // CHECKSTYLE:ON

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
        File workingDirectory = new File(path.substring(0, path.lastIndexOf(File.separator))
                + File.separator + "documentation"); //$NON-NLS-1$
        if (!workingDirectory.exists()) {
            workingDirectory.mkdir();
        }
        // Get the html path
        String htmlPath = createHTMLPath(path);
        try (PrintWriter fileWriter = new PrintWriter(htmlPath)) {
            fileWriter.append(html);
        } catch (FileNotFoundException e) {
            tracer.accept(e.getMessage(), e);
        }
    }

    private String createHTMLPath(String path) {
        StringBuilder stringBuilder = new StringBuilder(path);
        stringBuilder.replace(stringBuilder.lastIndexOf(File.separator),
                stringBuilder.lastIndexOf(File.separator), //
                File.separator + "documentation"); //$NON-NLS-1$
        stringBuilder.replace(stringBuilder.lastIndexOf("."), //$NON-NLS-1$ 
                stringBuilder.length(), ".html"); //$NON-NLS-1$ 
        return stringBuilder.toString();
    }
}
