package nl.esi.poosl.xtext.ui.contentassist;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.impl.CompositeNodeWithSemanticElement;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;

import nl.esi.poosl.Annotable;
import nl.esi.poosl.Channel;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.DataMethodNamed;
import nl.esi.poosl.Instance;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.xtext.ui.PooslHoverProvider;
import nl.esi.poosl.xtext.validation.PooslJavaValidatorSuppress.WarningType;

public class PooslProposalProviderTemplates extends PooslProposalProviderTerminals {
	private static final String EXPL_SYNCHRONOUS_COMMUNICATION = "<br/><br/>Message communication is synchronous and unicast. Send and receive statements are blocked until a pair of them can be executed together, with <ul><li>identical message names,</li><li>identical number of parameters, and</li><li>ports that are connected via channels.</li></ul>";
	private static final int TEMPLATE_PRIORITY = 600;
	private static final int ANNOTATION_PRIORITY = 200;
	private final Image templateImage;

	public PooslProposalProviderTemplates() {
		super();
		templateImage = ImageDescriptor.createFromURL(getClass().getResource("/icons/poosl_template.png"))
				.createImage();
	}

	private void createTemplateProposal(String name, String content, String explanation, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		createTemplateProposal(name, content, explanation, TEMPLATE_PRIORITY, context, acceptor);
	}

	private void createTemplateProposal(String name, String content, String explanation, int priority,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		String finalContent = content;
		String indent = getIndent(context);
		String template = finalContent.replaceAll("\n", "\n" + indent);
		ICompletionProposal proposal = createHtmlCompletionProposal(template, new StyledString(name), templateImage,
				priority, context);
		if (proposal instanceof ConfigurableCompletionProposal) {
			while (finalContent.startsWith("\n") || finalContent.startsWith("\r")) {
				finalContent = finalContent.substring(1);
			}
			finalContent = "<html><body bgcolor=\"#FFFFE1\"><style> body { font-size:9pt; font-family:'Segoe UI' }</style><pre>"
					+ finalContent + "</pre>";
			if (explanation != null) {
				finalContent = finalContent + "<p>" + explanation + "</p>";
			}
			finalContent = finalContent + "</body></html>";
			((ConfigurableCompletionProposal) proposal).setAdditionalProposalInfo(finalContent);
			((ConfigurableCompletionProposal) proposal).setProposalContextResource(context.getResource());
		}
		acceptor.accept(proposal);
	}

	private HtmlConfigurableCompletionProposal createHtmlCompletionProposal(String proposal,
			StyledString displayString, Image image, int priority, ContentAssistContext context) {
		if (isValidProposal(proposal, context.getPrefix(), context)) {
			return doCreateHtmlCompletionProposal(proposal, displayString, image, priority, context);
		}
		return null;
	}

	private HtmlConfigurableCompletionProposal doCreateHtmlCompletionProposal(String proposal,
			StyledString displayString, Image image, int priority, ContentAssistContext context) {
		int replacementOffset = context.getReplaceRegion().getOffset();
		int replacementLength = context.getReplaceRegion().getLength();
		HtmlConfigurableCompletionProposal result = new HtmlConfigurableCompletionProposal(proposal, replacementOffset,
				replacementLength, proposal.length(), image, displayString, null, null);
		result.setPriority(priority);
		result.setMatcher(context.getMatcher());
		result.setReplaceContextLength(context.getReplaceContextLength());
		return result;
	}

	@Override
	public void complete_Import(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_Import(model, ruleCall, context, acceptor);

		String template = "import \"../api/someFile.poosl\"";
		String explanation = "Import the data, process and cluster classes from another file.<br/><br/>The specified file location is absolute, or relative to the current file.";
		createTemplateProposal("import library", template, explanation, context, acceptor);
	}

	@Override
	public void complete_ImportLib(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_ImportLib(model, ruleCall, context, acceptor);

		String template = "importlib \"api/someFile.poosl\"";
		String explanation = "Import the data, process and cluster classes from another file.<br/><br/>The specified file location is relative to any of the Poosl include paths, which can be configured in the project properties (Project -> Properties -> Poosl).";
		createTemplateProposal("importlib library", template, explanation, context, acceptor);
	}

	@Override
	public void complete_DataClass(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_DataClass(model, ruleCall, context, acceptor);

		String template = "data class someClass extends Object\nvariables\n\t\nmethods\n\t\n";
		String explanation = "A data class describes a data structure with atomic operations. It contains variables and methods. Data classes can use inheritance to extend other data classes.";
		createTemplateProposal("data class", template, explanation, context, acceptor);
	}

	@Override
	public void complete_ProcessClass(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_ProcessClass(model, ruleCall, context, acceptor);

		String template = "process class someClass()\nports\n\t\nmessages\n\t\nvariables\n\t\ninit\n\tsomeMethod()()\nmethods\n\tsomeMethod()() | |\n\t\tskip\n";
		String explanation = "A process class describes a parametrized architectural building block with external ports. It contains variables and methods; one method is designated as the initial method. Process classes can use inheritance to extend other process classes. For each external port the messages and their parameters are described.";
		createTemplateProposal("process class", template, explanation, context, acceptor);
	}

	@Override
	public void complete_ClusterClass(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_ClusterClass(model, ruleCall, context, acceptor);

		String template = "cluster class someClass()\nports\n\t\ninstances\n\t\nchannels\n\t";
		String explanation = "A cluster class describes a parametrized architectural layer with external ports. It contains instances of process classes and (other) cluster classes. The external and instance ports can be connected using channels.";
		createTemplateProposal("cluster class", template, explanation, context, acceptor);
	}

	@Override
	public void complete_System(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_System(model, ruleCall, context, acceptor);

		String template = "system\nports\n\t\ninstances\n\t\nchannels\n\t";
		String explanation = "The system class describes the highest architecture layer of a POOSL model with external ports for co-simulation. It contains instances of process classes and cluster classes. The external and instance ports can be connected using channels.";
		createTemplateProposal("system specification", template, explanation, context, acceptor);
	}

	@Override
	public void complete_ProcessMethod(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_ProcessMethod(model, ruleCall, context, acceptor);
		String proposalString = "someMethod()() | |\n\tskip";
		String explanation = "A process method describes the internal behaviour of a process class. It has input parameters, output parameters and local variables.";
		createTemplateProposal("process method", proposalString, explanation, context, acceptor);
	}

	@Override
	public void complete_DataMethodNamed(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_DataMethodNamed(model, ruleCall, context, acceptor);
		String proposalString = "someMethod() : Object | |\n\treturn nil";
		String explanation = "A data method describes an atomic operation on a data class. It has input parameters, a return type and local variables.";
		createTemplateProposal("data method", proposalString, explanation, context, acceptor);
	}

	@Override
	public void complete_Instance(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_Instance(model, ruleCall, context, acceptor);

		String template = "someInstance : someClass()";
		String explanation = "Create an instance of a process or cluster class.";
		createTemplateProposal("class instance", template, explanation, context, acceptor);
	}

	@Override
	public void complete_InstanceParameter(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_InstanceParameter(model, ruleCall, context, acceptor);

		String template = "someParameter := nil";
		String explanation = "Provide a value for a parameter of the instantiated proces or cluster class.";
		createTemplateProposal("instance parameter", template, explanation, context, acceptor);
	}

	@Override
	public void complete_Channel(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_Channel(model, ruleCall, context, acceptor);

		String template = "{ }";
		String explanation = "Create a channel that connects a series of ports.";
		createTemplateProposal("channel declaration", template, explanation, context, acceptor);
	}

	@Override
	public void complete_Port(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_Port(model, ruleCall, context, acceptor);

		String template = "somePort";
		String explanation = "Declare an external port of a cluster class.";
		createTemplateProposal("port declaration", template, explanation, context, acceptor);
	}

	@Override
	public void complete_Declaration(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_Declaration(model, ruleCall, context, acceptor);

		String template = "someVar : Object";
		String explanation = "Declare a variable or parameter of a certain type.";
		createTemplateProposal("declaration", template, explanation, context, acceptor);
	}

	@Override
	public void complete_IfExpression(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_IfExpression(model, ruleCall, context, acceptor);
		String proposalString;
		String explanation;

		proposalString = "if true then\n\tnil\nelse\n\tnil\nfi";
		explanation = "Depending on whether the condition evaluates to true, return the first or the second body expression.";
		createTemplateProposal("if then else expression", proposalString, explanation, context, acceptor);

		proposalString = "if true then\n\tnil\nfi";
		explanation = "Depending on whether the condition evaluates to true, return the body expression or return nil.";
		createTemplateProposal("if then  expression ", proposalString, explanation, context, acceptor);
		// The extra space in the name is important for ordering
	}

	@Override
	public void complete_SwitchExpression(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_SwitchExpression(model, ruleCall, context, acceptor);
		String proposalString = "switch nil do\n\tcase 42 then \n\t\tnil\n\tdefault \n\t\tnil\nod";
		String explanation = "Evaluate the switch expression and all case expressions. Afterwards return the body of a case with an expression that equals the switch expression. If no such case exists, then execute the default body.";
		createTemplateProposal("switch expression", proposalString, explanation, context, acceptor);
	}

	@Override
	public void complete_SwitchExpressionCase(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_SwitchExpression(model, ruleCall, context, acceptor);
		String proposalString = "case 42 then \n\tnil";
		String explanation = "";
		createTemplateProposal("switch expression case", proposalString, explanation, context, acceptor);
	}

	@Override
	public void complete_WhileExpression(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_WhileExpression(model, ruleCall, context, acceptor);
		String proposalString = "while true do\n\tnil\nod";
		String explanation = "As long as the condition evaluates to true, evaluate the body expression. Finally return nil.";
		createTemplateProposal("while expression", proposalString, explanation, context, acceptor);
	}

	@Override
	public void complete_NewExpression(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_NewExpression(model, ruleCall, context, acceptor);

		String template = "new(Object)";
		String explanation = "Create a new instance of a data class.";
		createTemplateProposal("new expression", template, explanation, context, acceptor);
	}

	@Override
	public void complete_EnvironmentConstant(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_EnvironmentConstant(model, ruleCall, context, acceptor);

		String template = "${PATH}";
		String explanation = "Get the value of an environment variable set in the operating system. The value must be parsable as a POOSL constant.";
		createTemplateProposal("environment variable", template, explanation, context, acceptor);
	}

	@Override
	public void complete_ParStatement(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_ParStatement(model, ruleCall, context, acceptor);
		String proposalString = "par\n\tskip\nand\n\tskip\nrap";
		String explanation = "Execute multiple statements in parallel, in an interleaved way.<br/><br/>Communication and synchronization within a process can only be performed through shared variables.";
		createTemplateProposal("parallel statement", proposalString, explanation, context, acceptor);
	}

	@Override
	public void complete_InterruptStatement(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_InterruptStatement(model, ruleCall, context, acceptor);
		String proposalString = "interrupt\n\tskip\nwith (\n\tskip;\n\tskip\n)";
		String explanation = "Execute the first statement, but temporarily suspend this execution when the other statements execute (possibly multiple times).";
		createTemplateProposal("interrupt statement", proposalString, explanation, context, acceptor);
	}

	@Override
	public void complete_SelStatement(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_SelStatement(model, ruleCall, context, acceptor);
		String proposalString = "sel\n\tskip\nor\n\tskip\nles";
		String explanation = "Non-deterministic choice between multiple alternative statements.<br/><br/>It is blocked when all alternatives are blocked. The choice is finally resolved by the first statement that executes. Fairness of the choice is not guaranteed.";
		createTemplateProposal("select statement", proposalString, explanation, context, acceptor);
	}

	@Override
	public void complete_AbortStatement(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_AbortStatement(model, ruleCall, context, acceptor);
		String proposalString = "abort\n\tskip\nwith (\n\tskip;\n\tskip\n)";
		String explanation = "Execute the first statement, but permanently terminate this execution when the other statements execute.";
		createTemplateProposal("abort statement", proposalString, explanation, context, acceptor);
	}

	@Override
	public void complete_IfStatement(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_IfStatement(model, ruleCall, context, acceptor);
		String proposalString;
		String explanation;

		proposalString = "if true then\n\tskip\nelse\n\tskip\nfi";
		explanation = "Depending on whether the condition evaluates to true, execute the first or the second body statement.<br/><br/>The evaluation of the condition and the execution of any of the body statements are two separate execution steps.";
		createTemplateProposal("if then else statement", proposalString, explanation, context, acceptor);

		proposalString = "if true then\n\tskip\nfi";
		explanation = "If the condition evaluates to true, execute the body statement.<br/><br/>The evaluation of the condition and the execution of the body statement are two separate execution steps.";
		// The extra space in the name is important for ordering
		createTemplateProposal("if then  statement ", proposalString, explanation, context, acceptor);
	}

	@Override
	public void complete_SwitchStatement(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_SwitchStatement(model, ruleCall, context, acceptor);
		String proposalString = "switch nil do\n\tcase 42 then \n\t\tskip\n\tdefault \n\t\tskip\nod";
		String explanation = "Evaluate the switch expression and all case expressions. Afterwards execute the body statement of a case with an expression that equals the switch expression. If no such case exists, then execute the default body statement.<br/><br/>The evaluation of all expressions and the execution of any of the body statements are separate execution steps.";
		createTemplateProposal("switch statement", proposalString, explanation, context, acceptor);
	}

	@Override
	public void complete_SwitchStatementCase(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_SwitchExpression(model, ruleCall, context, acceptor);
		String proposalString = "case 42 then \n\tskip";
		String explanation = "";
		createTemplateProposal("switch statement case", proposalString, explanation, context, acceptor);
	}

	@Override
	public void complete_WhileStatement(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_WhileStatement(model, ruleCall, context, acceptor);
		String proposalString = "while true do\n\tskip\nod";
		String explanation = "As long as the condition evaluates to true, execute the statement.<br/><br/>The evaluation of the condition and the execution of the statement are two separate execution steps.";
		createTemplateProposal("while statement", proposalString, explanation, context, acceptor);
	}

	@Override
	public void complete_DelayStatement(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_DelayStatement(model, ruleCall, context, acceptor);

		String template = "delay 1";
		String explanation = "Postpone the execution with a number of time units (integer or real).";
		createTemplateProposal("delay statement", template, explanation, context, acceptor);
	}

	@Override
	public void complete_GuardedStatement(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_GuardedStatement(model, ruleCall, context, acceptor);

		String template = "[true] skip";
		String explanation = "Block the statement whenever the guard does not evaluate to true.<br/><br/>The evaluation of the condition and the execution of the statement together form a single execution step.";
		createTemplateProposal("guarded statement", template, explanation, context, acceptor);
	}

	@Override
	public void complete_ProcessMethodCall(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_ProcessMethodCall(model, ruleCall, context, acceptor);

		String template = "method()()";
		String explanation = "Invoke a process method with a series of input parameter values and a series of output variables.<br/><br/>If the process method call is the last statement of a method, then there is no danger of stack overflow.";
		createTemplateProposal("process method call statement", template, explanation, context, acceptor);
	}

	@Override
	public void complete_SkipStatement(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_SkipStatement(model, ruleCall, context, acceptor);

		String template = "skip";
		String explanation = "Execute without any observeable effect on variables or messages.<br/><br/>If used in the context of a sel statement, a skip statement is never blocked, and can resolve the choice. This can be used to model an internal decision.";
		createTemplateProposal("skip statement", template, explanation, context, acceptor);
	}

	@Override
	public void complete_ReceiveStatement(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_ReceiveStatement(model, ruleCall, context, acceptor);
		String template = "port?msg(var1, var2 | true)";
		String explanation = "Receive a message over a port, and assign the message parameters to variables. The (optional) condition restricts the accepted parameter values."
				+ EXPL_SYNCHRONOUS_COMMUNICATION;
		createTemplateProposal("receive statement", template, explanation, context, acceptor);
	}

	@Override
	public void complete_SendStatement(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_SendStatement(model, ruleCall, context, acceptor);
		String template = "port!msg(expr1, expr2)";
		String explanation = "Send a message over a port, and add the expressions as message parameters."
				+ EXPL_SYNCHRONOUS_COMMUNICATION;
		createTemplateProposal("send statement", template, explanation, context, acceptor);
	}

	@Override
	public void complete_MessageReceiveSignature(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_MessageReceiveSignature(model, ruleCall, context, acceptor);

		String template = "somePort?someMessage(Object, Object)";
		String explanation = "Declare a message that can be received by this process." + EXPL_SYNCHRONOUS_COMMUNICATION;
		createTemplateProposal("receive message", template, explanation, context, acceptor);
	}

	@Override
	public void complete_MessageSendSignature(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_MessageSendSignature(model, ruleCall, context, acceptor);

		String template = "somePort!someMessage(Object, Object)";
		String explanation = "Declare a message that can be send by this process." + EXPL_SYNCHRONOUS_COMMUNICATION;
		createTemplateProposal("send message", template, explanation, context, acceptor);
	}

	@Override
	public void complete_Annotation(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.complete_Annotation(model, ruleCall, context, acceptor);

		// determine if next semantic element is Annotable
		INode node = context.getCurrentNode();
		EObject owner = node.getSemanticElement();
		while (node != null && !(node instanceof CompositeNodeWithSemanticElement)) {
			node = node.getParent();
			owner = node.getSemanticElement();
		}

		if (owner instanceof Annotable) {
			complete_SuppressWarningsAnnotations(WarningType.UNUSED, context, acceptor);

			if (owner instanceof DataMethod) {
				complete_TestAnnotations(context, acceptor);
				complete_SuppressWarningsAnnotations(WarningType.RETURN, context, acceptor);
				complete_SuppressWarningsAnnotations(WarningType.TYPECHECK, context, acceptor);

				if (owner instanceof DataMethodNamed) {
					complete_InitAnnotation(context, acceptor);
				}
			} else if (owner instanceof Instance) {
				complete_SuppressWarningsAnnotations(WarningType.UNCONNECTED, context, acceptor);
			} else if (owner instanceof Channel) {
				complete_SuppressWarningsAnnotations(WarningType.UNCONNECTED, context, acceptor);
			} else if (owner instanceof ProcessMethod) {
				complete_SuppressWarningsAnnotations(WarningType.TYPECHECK, context, acceptor);
			}
		}

	}

	public void complete_SuppressWarningsAnnotations(WarningType warningType, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		String template = "@SuppressWarnings(" + warningType + ")";
		createTemplateProposal(template, template, PooslHoverProvider.SUPPRESSWARNINGS_DOCUMENTATION,
				ANNOTATION_PRIORITY, context, acceptor);
	}

	public void complete_TestAnnotations(ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		String testTemplate = "@Test";
		createTemplateProposal(testTemplate, testTemplate,
				PooslHoverProvider.TEST_DOCUMENTATION + PooslHoverProvider.TEST_HELP_DOCUMENTATION, ANNOTATION_PRIORITY,
				context, acceptor);

		String errorTemplate = "@Error";
		createTemplateProposal(errorTemplate, errorTemplate,
				PooslHoverProvider.ERROR_DOCUMENTATION + PooslHoverProvider.TEST_HELP_DOCUMENTATION,
				ANNOTATION_PRIORITY, context, acceptor);

		String skipTemplate = "@Skip";
		createTemplateProposal(skipTemplate, skipTemplate,
				PooslHoverProvider.SKIP_DOCUMENTATION + PooslHoverProvider.TEST_HELP_DOCUMENTATION, ANNOTATION_PRIORITY,
				context, acceptor);
	}

	public void complete_InitAnnotation(ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		String template = "@Init";
		createTemplateProposal(template, template, PooslHoverProvider.INIT_DOCUMENTATION, ANNOTATION_PRIORITY, context,
				acceptor);
	}

	/**
	 * This function will get the Indentation used by the user at the point in the
	 * editor of the context parameter
	 * 
	 * @param context The context parameter where the user invoked content-assist
	 * @return a string containing the indentation at the context
	 */
	private String getIndent(ContentAssistContext context) {
		INode currentNode = context.getCurrentNode();
		if (currentNode == null) {
			return "";
		}
		String indent = "";
		indent = currentNode.getText();
		// context.getPrefix() contains already partially typed tokens.
		// If these are present they need to be stripped from the actual text so
		// that only the indentation is left
		if (indent.contains(context.getPrefix())) {
			indent = indent.substring(0, currentNode.getText().length() - context.getPrefix().length());
			// When the prefix is stripped and nothing is left anymore this can
			// mean that
			// 1. the token was complete already (and this will not return any
			// indentation preceding it)
			// 2. this is actually a selection (without indentation)
			if (indent.length() == 0 || context.getSelectedText().length() > 0) {
				// Both cases require us to look at the parent text to get the
				// indentation.
				INode node = context.getCurrentNode();
				if (node.getParent() != null) {
					indent = node.getParent().getText().replace(context.getCurrentNode().getText(), "");
				}
			}
		}
		// Indentation can include any newline characters that we want to
		// remove.
		String[] indents = indent.split("\n");
		if (indents.length >= 2) {
			// If more remains then only newline characters there is acutally
			// some indentation (so spaces or tabs)
			indent = indents[1].replace("\r", "");
		} else {
			indent = "";
		}
		if (indent.length() > 0) {
			// remove non whitespaces
			char[] chars = new char[indent.length()];
			char[] newChars = new char[indent.length()];
			indent.getChars(0, indent.length(), chars, 0);
			int length = 0;
			for (int i = 0; i < chars.length; i++) {
				char c = chars[i];
				if (Character.isWhitespace(c)) {
					newChars[length] = c;
					length++;
				}
			}
			return new String(newChars, 0, length);
		}
		return indent;
	}
}
