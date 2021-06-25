package nl.esi.poosl.xtext.ui.quickfix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.impl.HiddenLeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.model.edit.ISemanticModification;
import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;

import nl.esi.poosl.Annotable;
import nl.esi.poosl.Declaration;
import nl.esi.poosl.MessageSignature;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.Variable;
import nl.esi.poosl.xtext.custom.PooslMessageType;
import nl.esi.poosl.xtext.helpers.HelperFunctions;
import nl.esi.poosl.xtext.helpers.PooslMessageSignatureCallHelper;
import nl.esi.poosl.xtext.validation.PooslIssueCodes;
import nl.esi.poosl.xtext.validation.PooslJavaValidatorSuppress;
import nl.esi.poosl.xtext.validation.PooslJavaValidatorSuppress.WarningType;

public class PooslQuickfixProviderUnusedElements extends DefaultQuickfixProvider {
	private static final String QUICKFIX_FAILED = "Quickfix Failed";
	private static final Logger LOGGER = Logger.getLogger(PooslQuickfixProviderUnusedElements.class.getName());

	@Fix(PooslIssueCodes.ERROR_UNUSED_CHANNEL)
	public void unusedChannel(final Issue issue, IssueResolutionAcceptor acceptor) {
		removeChannel(issue, acceptor);
	}

	@Fix(PooslIssueCodes.WARNING_UNUSED_CHANNEL)
	public void unusedChannelWarning(final Issue issue, IssueResolutionAcceptor acceptor) {
		addSuppressWarning(issue, acceptor, WarningType.UNUSED);
		removeChannel(issue, acceptor);
	}

	protected void removeChannel(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Remove this unused channel", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						try {
							IXtextDocument xtextDocument = context.getXtextDocument();
							xtextDocument.replace(issue.getOffset(), issue.getLength(), "");
						} catch (BadLocationException e) {
							LOGGER.log(Level.SEVERE, "Could not apply textchange in doc: " + context.getXtextDocument()
									+ ", of object: " + element, e);
							showWarning("Could not remove the channel.");
						}
					}
				});
	}

	@Fix(PooslIssueCodes.UNUSED_PROCESS_PORT)
	public void unusedProcessPort(final Issue issue, IssueResolutionAcceptor acceptor) {
		addSuppressWarning(issue, acceptor, WarningType.UNUSED);
		acceptor.accept(issue, "Remove this unused port", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						try {
							IXtextDocument xtextDocument = context.getXtextDocument();
							xtextDocument.replace(issue.getOffset(), issue.getLength(), "");
						} catch (BadLocationException e) {
							LOGGER.log(Level.SEVERE, "Could not apply textchange in doc: " + context.getXtextDocument()
									+ ", of object: " + element, e);
							showWarning("Could not remove the port.");
						}
					}
				});
	}

	@Fix(PooslIssueCodes.UNUSED_MESSAGE_SIGNATURE)
	public void unusedMessageSignatures(final Issue issue, IssueResolutionAcceptor acceptor) {
		addSuppressWarning(issue, acceptor, WarningType.UNUSED);
		acceptor.accept(issue, "Remove all unused messages from this class", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						Resource resource = element.eResource();
						MessageSignature signature = (MessageSignature) element;
						ProcessClass pClass = (ProcessClass) signature.eContainer();

						List<TextChange> changes = new ArrayList<>();
						Set<String> usedSendMessages = HelperFunctions.getUsedMessages(pClass, PooslMessageType.SEND);
						Set<String> usedReceiveMessages = HelperFunctions.getUsedMessages(pClass,
								PooslMessageType.RECEIVE);

						// Send statements
						for (MessageSignature msg : pClass.getSendMessages()) {
							if (!usedSendMessages.contains(
									PooslMessageSignatureCallHelper.getSignatureID(msg, PooslMessageType.SEND)))
								changes.add(new TextChange(msg, ""));
						}
						// Receive statements
						for (MessageSignature msg : pClass.getReceiveMessages()) {
							if (!usedReceiveMessages.contains(
									PooslMessageSignatureCallHelper.getSignatureID(msg, PooslMessageType.RECEIVE)))
								changes.add(new TextChange(msg, ""));
						}

						if (!applyTextChanges(context.getXtextDocument(), resource, changes))
							showWarning("Could not remove all unused messages from this class.");
					}
				});
	}

	@Fix(PooslIssueCodes.UNUSED_PROCESS_METHOD)
	public void unusedProcessMethods(final Issue issue, IssueResolutionAcceptor acceptor) {
		addSuppressWarning(issue, acceptor, WarningType.UNUSED);
		acceptor.accept(issue, "Remove this unused method", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						applyTextChange(context.getXtextDocument(), element.eResource(), element, null, "");
					}
				});
	}

	@Fix(PooslIssueCodes.UNUSED_VARIABLE)
	public void unusedVariable(final Issue issue, IssueResolutionAcceptor acceptor) {
		addSuppressWarning(issue, acceptor, WarningType.UNUSED);
		acceptor.accept(issue, "Remove this unused variable", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						Variable var = (Variable) element;
						Resource resource = var.eResource();
						Declaration dec = (Declaration) var.eContainer();

						if (dec.getVariables().size() == 1) {
							applyTextChange(context.getXtextDocument(), resource, dec, null, "");
						} else {
							ICompositeNode node = NodeModelUtils.getNode(var);
							INode prev = getPreviousVisibleNode(node);

							if (prev.getText().equals(",")) {
								applyTextChange(context.getXtextDocument(), resource, dec, prev.getOffset(),
										node.getTotalEndOffset() - prev.getOffset(), "");
							} else {
								INode next = getNextVisibleNode(node);
								if (next.getText().equals(",")) {
									applyTextChange(context.getXtextDocument(), resource, dec, node.getOffset(),
											next.getTotalEndOffset() - node.getOffset(), "");
								} else {
									applyTextChange(context.getXtextDocument(), resource, dec, null, "");
								}
							}
						}

					}
				});
	}

	private INode getNextVisibleNode(INode node) {
		INode prevNode = node.getNextSibling();
		while (prevNode instanceof HiddenLeafNode) {
			prevNode = prevNode.getNextSibling();
		}
		return prevNode;
	}

	private INode getPreviousVisibleNode(INode node) {
		INode prevNode = node.getPreviousSibling();
		while (prevNode instanceof HiddenLeafNode) {
			prevNode = prevNode.getPreviousSibling();
		}
		return prevNode;
	}

	protected class TextChange implements Comparable<TextChange> {
		private String text;
		private Resource resource;
		private int offSet;
		private int length;

		public TextChange(EObject object, EStructuralFeature declarationType, String text) {
			setNode(NodeModelUtils.findNodesForFeature(object, declarationType).get(0));
			this.resource = object.eResource();
			this.text = text;
		}

		public TextChange(EObject object, String text) {
			setNode(NodeModelUtils.findActualNodeFor(object));
			this.resource = object.eResource();
			this.text = text;
		}

		public TextChange(Resource resource, int offSet, int length, String text) {
			this.resource = resource;
			this.offSet = offSet;
			this.length = length;
			this.text = text;
		}

		public void setNode(INode node) {
			length = node.getLength();
			offSet = node.getOffset();
		}

		@Override
		public int compareTo(TextChange textChange) {
			return Integer.compare(textChange.offSet, this.offSet);
		}
	}

	protected boolean applyTextChanges(IXtextDocument doc, Resource res, List<TextChange> changes) {
		Collections.sort(changes);
		for (TextChange textChange : changes) {
			IXtextDocument correctDoc = doc;
			XtextEditor editor = null;
			if ((res != textChange.resource)) {
				editor = openEditor(textChange.resource);
				if (editor != null) {
					correctDoc = editor.getDocument();
				} else {
					return false;
				}
			}
			try {
				correctDoc.replace(textChange.offSet, textChange.length, textChange.text);
				if (editor != null)
					editor.selectAndReveal(textChange.offSet, textChange.text.length());
			} catch (BadLocationException e) {
				LOGGER.log(Level.SEVERE, "Could not open editor when performing quickfix.", e);
				return false;
			}
		}
		return true;
	}

	private XtextEditor openEditor(Resource changedResource) {
		if (changedResource.getURI().isPlatformResource()) {
			String platformString = changedResource.getURI().toPlatformString(true);
			IFile file = (IFile) ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
			try {
				IEditorPart openedEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
						.openEditor(new FileEditorInput(file), "nl.esi.poosl.xtext.Poosl");
				if (openedEditor instanceof XtextEditor) {
					return (XtextEditor) openedEditor;
				}
			} catch (PartInitException e) {
				LOGGER.log(Level.SEVERE, "Could not open editor when performing quickfix.", e);
			}
		}
		return null;
	}

	protected boolean applyTextChange(IXtextDocument doc, Resource res, EObject object, int offset, int length,
			String tekst) {
		IXtextDocument correctDoc = doc;
		XtextEditor editor = null;
		if ((res != object.eResource())) {
			editor = openEditor(object.eResource());
			if (editor != null) {
				correctDoc = editor.getDocument();
			} else {
				return false;
			}
		}
		try {
			correctDoc.replace(offset, length, tekst);
			if (editor != null)
				editor.selectAndReveal(offset, tekst.length());
		} catch (BadLocationException e) {
			LOGGER.log(Level.SEVERE, "Could not apply textchange in doc: " + doc + ", of object: " + object, e);
			return false;
		}
		return true;
	}

	protected boolean applyTextChange(IXtextDocument doc, Resource res, EObject object, EStructuralFeature ref,
			String tekst) {
		IXtextDocument correctDoc = doc;
		XtextEditor editor = null;
		if ((res != object.eResource())) {
			editor = openEditor(object.eResource());
			if (editor != null) {
				correctDoc = editor.getDocument();
			} else {
				return false;
			}
		}
		try {
			if (ref == null) {
				ICompositeNode node = NodeModelUtils.findActualNodeFor(object);
				correctDoc.replace(node.getOffset(), node.getLength(), tekst);
				if (editor != null)
					editor.selectAndReveal(node.getOffset(), tekst.length());
			} else {
				for (INode iNode : NodeModelUtils.findNodesForFeature(object, ref)) {
					correctDoc.replace(iNode.getOffset(), iNode.getLength(), tekst);
					if (editor != null)
						editor.selectAndReveal(iNode.getOffset(), tekst.length());
				}
			}
		} catch (BadLocationException e) {
			LOGGER.log(Level.SEVERE, "Could not apply textchange in doc: " + doc + ", of object: " + object, e);
			return false;
		}
		return true;
	}

	protected void showWarning(final String message) {
		LOGGER.log(Level.WARNING, QUICKFIX_FAILED + "  " + message);
		Logger.getLogger(PooslQuickfixProviderUnresolved.class.getName());
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				MessageDialog.openWarning(Display.getDefault().getActiveShell(), QUICKFIX_FAILED, message);
			}
		});
	}

	protected void addSuppressWarning(final Issue issue, IssueResolutionAcceptor acceptor, final WarningType type) {
		acceptor.accept(issue,
				"Add @" + PooslJavaValidatorSuppress.ANNOTATION_SUPPRESSWARNINGS + "(" + type.toString() + ")", // label
				null, // description
				null, // icon
				new ISemanticModification() {
					public void apply(EObject element, IModificationContext context) {
						Annotable annotable = EcoreUtil2.getContainerOfType(element, Annotable.class);
						if (annotable != null) {
							String text = "@" + PooslJavaValidatorSuppress.ANNOTATION_SUPPRESSWARNINGS + "("
									+ type.toString() + ")" + System.lineSeparator() + getIndent(annotable);
							if (!applyTextChange(context.getXtextDocument(), element.eResource(), annotable,
									NodeModelUtils.getNode(annotable).getOffset(), 0, text))
								showWarning("Could not add SuppressWarnings Annotation.");
						}
					}
				});
	}

	private String getIndent(EObject object) {
		ICompositeNode node = NodeModelUtils.getNode(object);
		Iterator<ILeafNode> iterator = node.getLeafNodes().iterator();

		String indentAfterLastNewline = "";
		boolean previousLeafNodeEndedWithNewline = false;
		ILeafNode leafNode = iterator.next();
		while (leafNode instanceof HiddenLeafNode) {
			String hidden = leafNode.getText();
			int newLine = hidden.lastIndexOf('\n');
			if (newLine != -1) {
				indentAfterLastNewline = hidden.substring(newLine + 1);
			} else if (previousLeafNodeEndedWithNewline && "".equals(hidden.trim())) {
				indentAfterLastNewline = indentAfterLastNewline + hidden;
			}
			previousLeafNodeEndedWithNewline = (newLine + 1 == hidden.length());

			if (iterator.hasNext()) {
				leafNode = iterator.next();
			} else {
				break;
			}
		}
		return indentAfterLastNewline;
	}
}
