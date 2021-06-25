package nl.esi.poosl.xtext.ui;

import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ide.editor.syntaxcoloring.HighlightingStyles;
import org.eclipse.xtext.ide.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.ide.editor.syntaxcoloring.ISemanticHighlightingCalculator;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.CancelIndicator;

import nl.esi.poosl.Annotation;
import nl.esi.poosl.AssignmentExpression;
import nl.esi.poosl.Channel;
import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.DataMethodCallExpression;
import nl.esi.poosl.Declaration;
import nl.esi.poosl.FloatConstant;
import nl.esi.poosl.Instance;
import nl.esi.poosl.InstanceParameter;
import nl.esi.poosl.InstancePort;
import nl.esi.poosl.InstantiableClass;
import nl.esi.poosl.IntegerConstant;
import nl.esi.poosl.MessageParameter;
import nl.esi.poosl.MessageSignature;
import nl.esi.poosl.NewExpression;
import nl.esi.poosl.OutputVariable;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.Port;
import nl.esi.poosl.PortReference;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.ProcessMethodCall;
import nl.esi.poosl.RealConstant;
import nl.esi.poosl.ReceiveStatement;
import nl.esi.poosl.SendStatement;
import nl.esi.poosl.Variable;
import nl.esi.poosl.VariableExpression;

public class PooslHighlightingCalculator implements ISemanticHighlightingCalculator {

	@Override
	public void provideHighlightingFor(XtextResource resource, IHighlightedPositionAcceptor acceptor,
			CancelIndicator cancel) {
		if (resource == null || resource.getParseResult() == null) {
			return;
		}

		TreeIterator<EObject> contents = resource.getAllContents();
		while (contents != null && contents.hasNext()) {
			EObject element = contents.next();

			// IDENTIFIER
			if (element instanceof Annotation) {
				// note: non-default style
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.ANNOTATION__NAME),
						HighlightingStyles.NUMBER_ID);
			} else if (element instanceof Channel) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.CHANNEL__EXTERNAL_PORT));
			} else if (element instanceof InstantiableClass) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.INSTANTIABLE_CLASS__NAME));
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.PROCESS_CLASS__SUPER_CLASS));
			} else if (element instanceof DataClass) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.DATA_CLASS__NAME));
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.DATA_CLASS__SUPER_CLASS));
			} else if (element instanceof DataMethod) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.DATA_METHOD_NAMED__NAME));
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.DATA_METHOD__RETURN_TYPE));
			} else if (element instanceof Declaration) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.DECLARATION__TYPE));
			} else if (element instanceof AssignmentExpression) {
				process(acceptor,
						NodeModelUtils.findNodesForFeature(element, Literals.ASSIGNMENT_EXPRESSION__VARIABLE));
			} else if (element instanceof DataMethodCallExpression) {
				process(acceptor,
						NodeModelUtils.findNodesForFeature(element, Literals.DATA_METHOD_CALL_EXPRESSION__NAME));
			} else if (element instanceof InstanceParameter) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.INSTANCE_PARAMETER__PARAMETER));
			} else if (element instanceof Instance) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.INSTANCE__NAME));
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.INSTANCE__CLASS_DEFINITION));
			} else if (element instanceof InstancePort) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.INSTANCE_PORT__INSTANCE));
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.INSTANCE_PORT__PORT));
			} else if (element instanceof MessageParameter) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.MESSAGE_PARAMETER__TYPE));
			} else if (element instanceof MessageSignature) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.MESSAGE_SIGNATURE__PORT));
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.MESSAGE_SIGNATURE__NAME));
			} else if (element instanceof NewExpression) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.NEW_EXPRESSION__DATA_CLASS));
			} else if (element instanceof OutputVariable) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.OUTPUT_VARIABLE__VARIABLE));
			} else if (element instanceof PortReference) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.PORT_REFERENCE__PORT));
			} else if (element instanceof Port) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.PORT__NAME));
			} else if (element instanceof ProcessMethodCall) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.PROCESS_METHOD_CALL__METHOD));
			} else if (element instanceof ProcessMethod) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.PROCESS_METHOD__NAME));
			} else if (element instanceof ReceiveStatement) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.RECEIVE_STATEMENT__NAME));
			} else if (element instanceof SendStatement) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.SEND_STATEMENT__NAME));
			} else if (element instanceof VariableExpression) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.VARIABLE_EXPRESSION__VARIABLE));
			} else if (element instanceof Variable) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.VARIABLE__NAME));
			}

			// Float, Integer and Real
			else if (element instanceof FloatConstant) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.FLOAT_CONSTANT__VALUE),
						HighlightingStyles.NUMBER_ID);
			} else if (element instanceof IntegerConstant) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.INTEGER_CONSTANT__VALUE),
						HighlightingStyles.NUMBER_ID);
			} else if (element instanceof RealConstant) {
				process(acceptor, NodeModelUtils.findNodesForFeature(element, Literals.REAL_CONSTANT__VALUE),
						HighlightingStyles.NUMBER_ID);
			}
		}
	}

	private void process(IHighlightedPositionAcceptor acceptor, List<INode> actualNodes) {
		process(acceptor, actualNodes, HighlightingStyles.DEFAULT_ID);
	}

	private void process(IHighlightedPositionAcceptor acceptor, List<INode> actualNodes, String highlighting) {
		for (INode actualNode : actualNodes) {
			acceptor.addPosition(actualNode.getOffset(), actualNode.getLength(), highlighting);
		}
	}
}
