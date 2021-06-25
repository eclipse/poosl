package nl.esi.poosl.xtext.ui.quickfix;

import nl.esi.poosl.xtext.validation.PooslIssueCodes;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.model.edit.ISemanticModification;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;

public class PooslQuickfixProviderUniqueIdentifiers extends PooslQuickfixProviderUnresolved {

	@Fix(PooslIssueCodes.DUPLICATE_CLASS_NAME)
	public void duplicateClassName(final Issue issue,
			IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Remove the currently selected class", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element,
							IModificationContext context)
							throws BadLocationException {
						ICompositeNode node = NodeModelUtils.getNode(element);
						int offset = node.getOffset();
						int length = node.getLength();
						IXtextDocument xtextDocument = context
								.getXtextDocument();
						xtextDocument.replace(offset, length, "");
					}
				});
	}

	@Fix(PooslIssueCodes.DUPLICATE_METHOD_NAME)
	public void duplicateMethodsName(final Issue issue,
			IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Remove the currently selected method", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element,
							IModificationContext context)
							throws BadLocationException {
						ICompositeNode node = NodeModelUtils.getNode(element);
						int offset = node.getOffset();
						int length = node.getLength();
						IXtextDocument xtextDocument = context.getXtextDocument();
						xtextDocument.replace(offset, length, "");
					}
				});
	}
}
