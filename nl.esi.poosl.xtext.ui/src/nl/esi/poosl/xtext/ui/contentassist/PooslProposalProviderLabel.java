package nl.esi.poosl.xtext.ui.contentassist;

import org.eclipse.jface.viewers.StyledString;
import org.eclipse.xtext.resource.IEObjectDescription;

import nl.esi.poosl.PooslPackage;
import nl.esi.poosl.xtext.custom.FormattingHelper;
import nl.esi.poosl.xtext.descriptions.PooslDataMethodDescription;
import nl.esi.poosl.xtext.descriptions.PooslDeclarationDescription;
import nl.esi.poosl.xtext.descriptions.PooslMessageSignatureDescription;
import nl.esi.poosl.xtext.descriptions.PooslPortDescription;
import nl.esi.poosl.xtext.descriptions.PooslProcessMethodDescription;

public class PooslProposalProviderLabel extends AbstractPooslProposalProvider {

	@Override
	protected StyledString getStyledDisplayString(IEObjectDescription candidate) {
		String first = null;
		String second = null;
		if (candidate.getEClass() == PooslPackage.Literals.PROCESS_METHOD) {
			StringBuilder buf = new StringBuilder();
			FormattingHelper.formatProcessMethod(buf, candidate);
			first = buf.toString();
			second = PooslProcessMethodDescription.getClassName(candidate);
		} else if (candidate.getEClass() == PooslPackage.Literals.PORT) {
			first = candidate.getName().toString();
			second = PooslPortDescription.getClassName(candidate);
		} else if (candidate.getEClass() == PooslPackage.Literals.VARIABLE) {
			first = candidate.getName().toString();
			second = PooslDeclarationDescription.getClassName(candidate);
		} else if (candidate.getEClass() == PooslPackage.Literals.CLUSTER_CLASS
				|| candidate.getEClass() == PooslPackage.Literals.PROCESS_CLASS) {
			first = candidate.getName().toString();
		} else if (candidate.getEClass() == PooslPackage.Literals.DATA_METHOD_NAMED
				|| candidate.getEClass() == PooslPackage.Literals.DATA_METHOD_BINARY_OPERATOR
				|| candidate.getEClass() == PooslPackage.Literals.DATA_METHOD_UNARY_OPERATOR) {
			StringBuilder buf = new StringBuilder();
			FormattingHelper.formatDataMethod(buf, candidate, false);
			first = buf.toString();
			second = PooslDataMethodDescription.getClassName(candidate);
		} else if (candidate.getEClass() == PooslPackage.Literals.MESSAGE_SIGNATURE) {
			StringBuilder buf = new StringBuilder();
			buf.append(candidate.getName());
			FormattingHelper.formatTypes(buf, PooslMessageSignatureDescription.getParameterTypes(candidate));
			first = buf.toString();
			String className = PooslMessageSignatureDescription.getClassName(candidate);
			String portName = PooslMessageSignatureDescription.getPort(candidate);
			if (portName != null) {
				second = ((className != null) ? className : "") + "." + portName;
			}
		}

		if (first != null) {
			StringBuilder buf = new StringBuilder();
			buf.append(first);
			if (second != null) {
				buf.append(" - ");
				buf.append(second);
				StyledString styledString = new StyledString(buf.toString());
				styledString.setStyle(first.length(), second.length() + 3, StyledString.DECORATIONS_STYLER);
				return styledString;
			} else {
				return new StyledString(buf.toString());
			}
		}

		return super.getStyledDisplayString(candidate);
	}
}
