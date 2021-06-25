package nl.esi.poosl.xtext.validation;

import org.eclipse.xtext.diagnostics.DiagnosticMessage;
import org.eclipse.xtext.linking.impl.LinkingDiagnosticMessageProvider;

public class PooslLinkingMessageProvider extends LinkingDiagnosticMessageProvider {

	@Override
	public DiagnosticMessage getUnresolvedProxyMessage(ILinkingDiagnosticContext context) {
		DiagnosticMessage diagnosticMessage = super.getUnresolvedProxyMessage(context);
		String diagnosticMessageString = diagnosticMessage.getMessage();
		diagnosticMessageString = diagnosticMessageString.substring("Couldn't resolve reference to ".length());
		return new DiagnosticMessage(
				diagnosticMessageString.substring(0, diagnosticMessageString.length() - 1) + " is not declared.",
				diagnosticMessage.getSeverity(), diagnosticMessage.getIssueCode(), diagnosticMessage.getIssueData());
	}
}
