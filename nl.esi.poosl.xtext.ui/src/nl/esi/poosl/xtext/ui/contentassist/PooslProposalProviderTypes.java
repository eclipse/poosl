package nl.esi.poosl.xtext.ui.contentassist;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.eclipse.xtext.ui.editor.hover.IEObjectHover;
import org.eclipse.xtext.ui.editor.hover.html.XtextBrowserInformationControlInput;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;

import nl.esi.poosl.DataMethodCallExpression;
import nl.esi.poosl.Declaration;
import nl.esi.poosl.Expression;
import nl.esi.poosl.InstantiableClass;
import nl.esi.poosl.NewExpression;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.PortReference;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.ReceiveStatement;
import nl.esi.poosl.SendStatement;
import nl.esi.poosl.Statement;
import nl.esi.poosl.Variable;
import nl.esi.poosl.xtext.custom.PooslCache;
import nl.esi.poosl.xtext.custom.PooslMessageType;
import nl.esi.poosl.xtext.custom.PooslTypeSystem;
import nl.esi.poosl.xtext.custom.PooslTypeSystemStatement;
import nl.esi.poosl.xtext.descriptions.PooslDataMethodDescription;
import nl.esi.poosl.xtext.descriptions.PooslMessageSignatureDescription;
import nl.esi.poosl.xtext.descriptions.PooslProcessMethodDescription;
import nl.esi.poosl.xtext.helpers.HelperFunctions;
import nl.esi.poosl.xtext.scoping.PooslScopeProvider;

public class PooslProposalProviderTypes extends PooslProposalProviderLabel {
	private static final int DEFAULT_PRIORITY = 400;
	private static final int OPERATOR_METHOD_PRIORITY = 350;

	@Inject
	private IEObjectHover hover;

	private final PooslTypeSystem pooslTypeSystem = new PooslTypeSystem(null);
	private final PooslTypeSystemStatement pooslTypeSystemStatement = new PooslTypeSystemStatement(pooslTypeSystem);
	private final Image proposalImage;

	public PooslProposalProviderTypes() {
		super();
		proposalImage = ImageDescriptor.createFromURL(getClass().getResource("/icons/poosl_proposal.png"))
				.createImage();
	}

	// =============----------- Variable References -----------=============

	@Override
	public void completeOutputVariable_Variable(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		Iterable<IEObjectDescription> descriptions = PooslScopeProvider.getScopeOutputVariableVariable(model)
				.getAllElements();
		addDefaultIEObjectDescriptionProposals(descriptions, context, acceptor, model);
	}

	@Override
	public void completeVariableExpression_Variable(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		Iterable<IEObjectDescription> descriptions = PooslScopeProvider.getScopeExpressionVariable(model)
				.getAllElements();
		addDefaultIEObjectDescriptionProposals(descriptions, context, acceptor, model);
	}

	@Override
	public void completeInstanceParameter_Parameter(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		Iterable<IEObjectDescription> descriptions = PooslScopeProvider.getScopeInstanceParameterParameter(model)
				.getAllElements();
		addDefaultIEObjectDescriptionProposals(descriptions, context, acceptor, model);
	}

	// =============----------- Class References -----------=============

	@Override
	public void completeProcessClass_SuperClass(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		addDefaultIEObjectDescriptionProposals(PooslCache.get(model.eResource()).getAllRelevantProcessClasses(),
				context, acceptor, model);
	}

	@Override
	public void completeDataClass_SuperClass(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		Iterable<IEObjectDescription> allowedSuperClasses = Iterables.filter(
				PooslCache.get(model.eResource()).getAllRelevantDataClasses(), new Predicate<IEObjectDescription>() {
					@Override
					public boolean apply(IEObjectDescription input) {
						return !HelperFunctions.primitiveDataClasses.contains(HelperFunctions.getName(input));
					}

				});
		addDefaultIEObjectDescriptionProposals(allowedSuperClasses, context, acceptor, model);
	}

	@Override
	public void completeInstance_ClassDefinition(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		PooslProposalAccepterForInstance myAcceptor = new PooslProposalAccepterForInstance(acceptor);
		addDefaultIEObjectDescriptionProposals(HelperFunctions.getAllRelevantInstantiableClasses(model.eResource()),
				context, myAcceptor, model);
	}

	@Override
	public void completeNewExpression_DataClass(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		addDefaultIEObjectDescriptionProposals(PooslCache.get(model.eResource()).getAllRelevantDataClasses(), context,
				acceptor, model);
	}

	@Override
	public void completeDeclaration_Type(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		addDefaultIEObjectDescriptionProposals(PooslCache.get(model.eResource()).getAllRelevantDataClasses(), context,
				acceptor, model);
	}

	@Override
	public void completeMessageParameter_Type(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		addDefaultIEObjectDescriptionProposals(PooslCache.get(model.eResource()).getAllRelevantDataClasses(), context,
				acceptor, model);
	}

	@Override
	public void completeDataMethodNamed_ReturnType(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		addDefaultIEObjectDescriptionProposals(PooslCache.get(model.eResource()).getAllRelevantDataClasses(), context,
				acceptor, model);
	}

	@Override
	public void completeDataMethodBinaryOperator_ReturnType(EObject model, Assignment assignment,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		addDefaultIEObjectDescriptionProposals(PooslCache.get(model.eResource()).getAllRelevantDataClasses(), context,
				acceptor, model);
	}

	@Override
	public void completeDataMethodUnaryOperator_ReturnType(EObject model, Assignment assignment,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		addDefaultIEObjectDescriptionProposals(PooslCache.get(model.eResource()).getAllRelevantDataClasses(), context,
				acceptor, model);
	}

	// =============----------- Process Method call -----------=============

	@Override
	public void complete_ProcessMethodCall(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		ProcessClass pClass = HelperFunctions.getContainingProcessClass(context.getCurrentModel());
		List<IEObjectDescription> methods = PooslCache.get(pClass.eResource()).getProcessMethods(pClass.getName());

		for (IEObjectDescription mDescr : methods) {
			StringBuilder propBuf = new StringBuilder();
			propBuf.append(HelperFunctions.getName(mDescr));
			propBuf.append(
					createDefaultParameterList(PooslProcessMethodDescription.getNumberOfInputParameters(mDescr)));
			propBuf.append(
					createDefaultVariableList(PooslProcessMethodDescription.getNumberOfOutputParameters(mDescr)));
			acceptor.accept(createDescriptionProposal(propBuf.toString(), mDescr, context));
		}

	}

	// =============-----------Port References -----------=============

	@Override
	public void complete_PortReference(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		EObject prevModel = context.getPreviousModel();
		addDefaultIEObjectDescriptionProposals(PooslScopeProvider.getScopePortDescriptions(prevModel), context,
				acceptor, model);

	}

	@Override
	public void complete_Port(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		EObject prevModel = context.getPreviousModel();

		addDefaultIEObjectDescriptionProposals(PooslScopeProvider.getScopePort(prevModel).getAllElements(), context,
				acceptor, prevModel);
	}

	@Override
	public void complete_PortDescriptor(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		EObject prevModel = context.getPreviousModel();
		addDefaultIEObjectDescriptionProposals(PooslScopeProvider.getScopePort(prevModel).getAllElements(), context,
				acceptor, prevModel);
	}

	@Override

	public void completePortReference_Port(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		EObject prevModel = context.getPreviousModel();
		addDefaultIEObjectDescriptionProposals(PooslScopeProvider.getScopePort(prevModel).getAllElements(), context,
				acceptor, prevModel);
	}

	// =============----------- Message References -----------=============

	@Override
	public void completeReceiveStatement_Name(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {

		EObject previousModel = context.getPreviousModel();
		Iterable<IEObjectDescription> signatures = PooslScopeProvider.getScopeMessages(previousModel,
				PooslMessageType.RECEIVE);

		if (previousModel instanceof ReceiveStatement) {
			ReceiveStatement rcvMsg = (ReceiveStatement) previousModel;
			if (rcvMsg.getPortDescriptor() instanceof PortReference) {
				String port = ((PortReference) rcvMsg.getPortDescriptor()).getPort();
				if (port != null && !port.isEmpty()) {
					signatures = Iterables.filter(signatures, PooslMessageSignatureDescription.predicateMessage(port));
				}
			}
		}

		for (IEObjectDescription descr : signatures) {
			acceptor.accept(createDescriptionProposal(
					HelperFunctions.getName(descr) + createDefaultMessageParameterList(descr), descr, context));
		}
	}

	@Override
	public void completeSendStatement_Name(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {

		EObject previousModel = context.getPreviousModel();
		Iterable<IEObjectDescription> signatures = PooslScopeProvider.getScopeMessages(previousModel,
				PooslMessageType.SEND);

		if (previousModel instanceof SendStatement) {
			SendStatement sendMsg = (SendStatement) previousModel;
			if (sendMsg.getPortDescriptor() instanceof PortReference) {
				String port = ((PortReference) sendMsg.getPortDescriptor()).getPort();
				if (port != null && !port.isEmpty()) {
					signatures = Iterables.filter(signatures, PooslMessageSignatureDescription.predicateMessage(port));
				}
			}
		}

		for (IEObjectDescription descr : signatures) {
			acceptor.accept(createDescriptionProposal(
					HelperFunctions.getName(descr) + createDefaultMessageParameterList(descr), descr, context));
		}
	}

	@Override
	public void completeExpressionLevel5_Name(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.completeExpressionLevel5_Name(model, assignment, context, acceptor);
		completeDataMethodCall_Name(model, context, acceptor);
	}

	@Override
	public void completeIDStartWithinStatementExpressionLevel5_Name(EObject model, Assignment assignment,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeIDStartWithinStatementExpressionLevel5_Name(model, assignment, context, acceptor);
		completeDataMethodCall_Name(model, context, acceptor);
	}

	@Override
	public void completeNonIDStartWithinStatementExpressionLevel5_Name(EObject model, Assignment assignment,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeNonIDStartWithinStatementExpressionLevel5_Name(model, assignment, context, acceptor);
		completeDataMethodCall_Name(model, context, acceptor);
	}

	@Override
	public void completeBracketedArgumentStartExpressionLevel5_Name(EObject model, Assignment assignment,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeBracketedArgumentStartExpressionLevel5_Name(model, assignment, context, acceptor);
		completeDataMethodCall_Name(model, context, acceptor);
	}

	private void completeDataMethodCall_Name(EObject model, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		EObject previousModel = context.getPreviousModel();
		Resource resource = model.eResource();

		String runningClass;
		boolean allowBinaryOperators;
		if (previousModel instanceof Expression) {
			if (previousModel instanceof DataMethodCallExpression) {
				Expression target = ((DataMethodCallExpression) previousModel).getTarget();
				boolean onSuperClass = ((DataMethodCallExpression) previousModel).isOnSuperClass();

				runningClass = pooslTypeSystem.getAndCheckExpressionType(target);
				if (onSuperClass) {
					String correctedExtendsClass = HelperFunctions.getCorrectedDataClassExtendsAsString(resource,
							runningClass);
					if (correctedExtendsClass != null) {
						runningClass = correctedExtendsClass;
					}
				}
				allowBinaryOperators = !onSuperClass;
			} else if (previousModel instanceof NewExpression) {
				if (completeInitMethodCall((NewExpression) previousModel, context, acceptor)) {
					// if init methods are available don't propose others
					return;
				}
				Expression target = (Expression) previousModel;

				runningClass = pooslTypeSystem.getAndCheckExpressionType(target);
				allowBinaryOperators = true;
			} else {
				Expression target = (Expression) previousModel;

				runningClass = pooslTypeSystem.getAndCheckExpressionType(target);
				allowBinaryOperators = true;
			}
		} else if (previousModel instanceof Statement) {
			// some texts can be parsed as both Expression and Statement
			Statement target = (Statement) previousModel;

			runningClass = pooslTypeSystemStatement.getExpressionType(target);
			allowBinaryOperators = true;
		} else {
			return;
		}

		if (runningClass != null) {
			Iterable<String> allClassNames = PooslCache.get(resource)
					.getDataReflexiveAncestorsAndChildren(runningClass);
			Iterable<IEObjectDescription> listNamedMethods = HelperFunctions
					.getGlobalScope(resource, Literals.DATA_CLASS__DATA_METHODS_NAMED,
							PooslDataMethodDescription.predicateDataMethod(allClassNames, Literals.DATA_METHOD_NAMED))
					.getAllElements();

			for (IEObjectDescription dMethod : listNamedMethods) {
				addDataMethodProposal(dMethod, context, acceptor);
			}
			if (allowBinaryOperators) {
				Iterable<IEObjectDescription> listBinaryMethods = HelperFunctions.getGlobalScope(resource,
						Literals.DATA_CLASS__DATA_METHODS_BINARY_OPERATOR, PooslDataMethodDescription
								.predicateDataMethod(allClassNames, Literals.DATA_METHOD_BINARY_OPERATOR))
						.getAllElements();
				for (IEObjectDescription dMethod : listBinaryMethods) {
					addDataMethodProposal(dMethod, context, acceptor);
				}
			}
		}
	}

	private boolean completeInitMethodCall(NewExpression expression, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		IScope initMethods = HelperFunctions.getGlobalScope(expression.eResource(),
				Literals.DATA_CLASS__DATA_METHODS_NAMED,
				PooslDataMethodDescription.predicateDataMethodInit(expression.getDataClass()));

		if (!initMethods.getAllElements().iterator().hasNext()) {
			return false;
		}

		for (IEObjectDescription dMethod : initMethods.getAllElements()) {
			addDataMethodProposal(dMethod, context, acceptor);
		}
		return true;
	}

	@Override
	public void completeUnaryOperatorExpression_Name(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		super.completeUnaryOperatorExpression_Name(model, assignment, context, acceptor);
		Resource resource = model.eResource();

		Iterable<String> allClassNames = PooslCache.get(resource)
				.getDataReflexiveAncestorsAndChildren(HelperFunctions.CLASS_NAME_OBJECT);
		Iterable<IEObjectDescription> listUnaryMethods = HelperFunctions.getGlobalScope(resource,
				Literals.DATA_CLASS__DATA_METHODS_UNARY_OPERATOR,
				PooslDataMethodDescription.predicateDataMethod(allClassNames, Literals.DATA_METHOD_UNARY_OPERATOR))
				.getAllElements();
		for (IEObjectDescription dMethod : listUnaryMethods) {
			String methodName = HelperFunctions.getName(dMethod);
			acceptor.accept(createDescriptionProposal(methodName + "nil", dMethod, context));
		}
	}

	@Override
	public void completeProcessMethodCall_Method(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		PooslProposalAccepterForProcessMethod myAcceptor = new PooslProposalAccepterForProcessMethod(acceptor);
		super.completeProcessMethodCall_Method(model, assignment, context, myAcceptor);
	}

	// =============----------- Proposal Creation ----------=============
	/**
	 * Adds a simple proposal with the description's name
	 * 
	 * @param descriptions
	 * @param context
	 * @param acceptor
	 * @param model
	 */
	private void addDefaultIEObjectDescriptionProposals(Iterable<IEObjectDescription> descriptions,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor, EObject model) {
		for (IEObjectDescription descr : descriptions) {
			String dName = HelperFunctions.getName(descr);
			acceptor.accept(createDescriptionProposal(dName, descr, context));
		}
	}

	private void addDataMethodProposal(IEObjectDescription dMethod, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		String methodName = HelperFunctions.getName(dMethod);
		String parameters = createDefaultParameterList(
				PooslDataMethodDescription.getParameterTypeNames(dMethod).size());
		acceptor.accept(createDescriptionProposal(methodName + parameters, dMethod, context));
	}

	private ICompletionProposal createDescriptionProposal(String proposalString, IEObjectDescription descr,
			ContentAssistContext context) {
		int priority;
		if (descr.getEClass() == Literals.DATA_METHOD_BINARY_OPERATOR
				|| descr.getEClass() == Literals.DATA_METHOD_UNARY_OPERATOR) {
			priority = OPERATOR_METHOD_PRIORITY;
		} else {
			priority = DEFAULT_PRIORITY;
		}

		ICompletionProposal proposal = createCompletionProposal(proposalString, getStyledDisplayString(descr),
				proposalImage, priority, context.getPrefix(), context);
		if (proposal instanceof ConfigurableCompletionProposal) {
			((ConfigurableCompletionProposal) proposal).setAdditionalProposalInfo(descr.getEObjectOrProxy());
			((ConfigurableCompletionProposal) proposal).setHover(hover);
			((ConfigurableCompletionProposal) proposal).setProposalContextResource(context.getResource());
		}
		return proposal;
	}

	private static String createDefaultParameterList(List<Declaration> parameters) {
		int length = HelperFunctions.computeNumberOfVariables(parameters);
		return createDefaultParameterList(length);
	}

	private static String createDefaultVariableList(List<Declaration> parameters) {
		int length = HelperFunctions.computeNumberOfVariables(parameters);
		return createDefaultVariableList(length);
	}

	private static String createDefaultMessageParameterList(IEObjectDescription descr) {
		int length = PooslMessageSignatureDescription.getParameterTypes(descr).size();
		if (PooslMessageSignatureDescription.getMessageType(descr) == PooslMessageType.SEND) {
			return createDefaultParameterList(length);
		} else {
			return createDefaultVariableList(length);
		}
	}

	public static String createDefaultParameterList(int length) {
		String body = "";
		if (length > 0) {
			body = "nil";
			for (int i = 1; i < length; i++) {
				body += ", nil";
			}
		}
		return "(" + body + ")";
	}

	private static String createDefaultVariableList(int length) {
		String body = "";
		if (length > 0) {
			body = "var1";
			for (int i = 1; i < length; i++) {
				body += ", var" + (i + 1);
			}
		}
		return "(" + body + ")";
	}

	private static String createDefaultInstanceParameterList(List<Declaration> declarations) {
		List<Variable> variables = HelperFunctions.declarationsToVariables(declarations);
		String body = "";
		for (Variable variable : variables) {
			if (variable != variables.get(0)) {
				body += ", ";
			}
			body += variable.getName() + " := nil";
		}
		return "(" + body + ")";
	}

	private class PooslProposalAccepterForProcessMethod implements ICompletionProposalAcceptor {
		private IProgressMonitor monitor = new NullProgressMonitor();
		private ICompletionProposalAcceptor acceptor;

		PooslProposalAccepterForProcessMethod(ICompletionProposalAcceptor acceptor) {
			this.acceptor = acceptor;
		}

		@Override
		public void accept(ICompletionProposal proposal) {
			if (proposal instanceof ConfigurableCompletionProposal) {
				Object obj = ((ConfigurableCompletionProposal) proposal).getAdditionalProposalInfo(monitor);
				if (obj instanceof XtextBrowserInformationControlInput) {
					EObject eobj = ((XtextBrowserInformationControlInput) obj).getElement();
					if (eobj instanceof ProcessMethod) {
						String replacementString = ((ConfigurableCompletionProposal) proposal).getReplacementString();
						((ConfigurableCompletionProposal) proposal).setReplacementString(replacementString
								+ PooslProposalProviderTypes
										.createDefaultParameterList(((ProcessMethod) eobj).getInputParameters())
								+ PooslProposalProviderTypes
										.createDefaultVariableList(((ProcessMethod) eobj).getOutputParameters()));
					}
				}
			}
			acceptor.accept(proposal);
		}

		@Override
		public boolean canAcceptMoreProposals() {
			return acceptor.canAcceptMoreProposals();
		}
	}

	private class PooslProposalAccepterForInstance implements ICompletionProposalAcceptor {
		private IProgressMonitor monitor = new NullProgressMonitor();
		private ICompletionProposalAcceptor acceptor;

		PooslProposalAccepterForInstance(ICompletionProposalAcceptor acceptor) {
			this.acceptor = acceptor;
		}

		@Override
		public void accept(ICompletionProposal proposal) {
			if (proposal instanceof ConfigurableCompletionProposal) {
				Object obj = ((ConfigurableCompletionProposal) proposal).getAdditionalProposalInfo(monitor);
				if (obj instanceof XtextBrowserInformationControlInput) {
					EObject eobj = ((XtextBrowserInformationControlInput) obj).getElement();
					if (eobj instanceof InstantiableClass) {
						String replacementString = ((ConfigurableCompletionProposal) proposal).getReplacementString();
						((ConfigurableCompletionProposal) proposal).setReplacementString(
								replacementString + PooslProposalProviderTypes.createDefaultInstanceParameterList(
										((InstantiableClass) eobj).getParameters()));
					}
				}
			}
			acceptor.accept(proposal);
		}

		@Override
		public boolean canAcceptMoreProposals() {
			return acceptor.canAcceptMoreProposals();
		}
	}
}
