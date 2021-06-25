package nl.esi.poosl.xtext.ui.contentassist;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;

public class PooslProposalProviderTerminals extends PooslProposalProviderTypes {

	private void complete_ID(EObject model, final ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// Based on org.eclipse.xtext.common.ui.contentassist.TerminalsProposalProvider
		String proposalText = "someName";
		String displayText = "someName - Identifier";
		ICompletionProposal proposal = createCompletionProposal(proposalText, displayText, null, context);
		if (proposal instanceof ConfigurableCompletionProposal) {
			ConfigurableCompletionProposal configurable = (ConfigurableCompletionProposal) proposal;
			configurable.setPriority(300);
			configurable.setSelectionStart(configurable.getReplacementOffset());
			configurable.setSelectionLength(proposalText.length());
			configurable.setAutoInsertable(false);
			configurable.setSimpleLinkedMode(context.getViewer(), '\t', ' ');
		}
		acceptor.accept(proposal);
	}

	@Override
	public void completeClusterClass_Name(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		complete_ID(model, context, acceptor);
	}

	@Override
	public void completeProcessClass_Name(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		complete_ID(model, context, acceptor);
	}

	@Override
	public void completeDataClass_Name(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		complete_ID(model, context, acceptor);
	}

	@Override
	public void completePort_Name(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		complete_ID(model, context, acceptor);
	}

	@Override
	public void completeMessageReceiveSignature_Name(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		complete_ID(model, context, acceptor);
	}

	@Override
	public void completeMessageSendSignature_Name(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		complete_ID(model, context, acceptor);
	}

	@Override
	public void completeVariable_Name(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		complete_ID(model, context, acceptor);
	}

	@Override
	public void completeProcessMethod_Name(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		complete_ID(model, context, acceptor);
	}

	@Override
	public void completeDataMethodNamed_Name(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		complete_ID(model, context, acceptor);
	}

	@Override
	public void completeInstance_Name(EObject model, Assignment assignment, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		complete_ID(model, context, acceptor);
	}
}
