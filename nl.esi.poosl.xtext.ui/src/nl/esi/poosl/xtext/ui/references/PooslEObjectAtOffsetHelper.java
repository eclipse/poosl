package nl.esi.poosl.xtext.ui.references;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.resource.EObjectAtOffsetHelper;
import org.eclipse.xtext.resource.IEObjectDescription;

import nl.esi.poosl.AssignmentExpression;
import nl.esi.poosl.BinaryOperatorExpression;
import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.DataMethodCallExpression;
import nl.esi.poosl.Declaration;
import nl.esi.poosl.Instance;
import nl.esi.poosl.InstanceParameter;
import nl.esi.poosl.MessageParameter;
import nl.esi.poosl.NewExpression;
import nl.esi.poosl.OutputVariable;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.PortReference;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethodCall;
import nl.esi.poosl.ReceiveStatement;
import nl.esi.poosl.SendStatement;
import nl.esi.poosl.UnaryOperatorExpression;
import nl.esi.poosl.VariableExpression;
import nl.esi.poosl.xtext.custom.PooslCache;
import nl.esi.poosl.xtext.custom.PooslTypeSystem;
import nl.esi.poosl.xtext.helpers.PooslReferenceHelper;

public class PooslEObjectAtOffsetHelper extends EObjectAtOffsetHelper {
	private final PooslTypeSystem pooslTypeSystem = new PooslTypeSystem(null);

	/*
	 * Find the referenced element from the node (non-Javadoc)
	 * 
	 * @see org.eclipse.xtext.resource.EObjectAtOffsetHelper#
	 * resolveCrossReferencedElement(org.eclipse.xtext.nodemodel.INode)
	 */
	@Override
	protected EObject resolveCrossReferencedElement(INode node) {
		EObject semanticElement = node.getSemanticElement();
		IEObjectDescription descr = null;

		// --- Message References -------

		if (semanticElement instanceof SendStatement) {
			descr = PooslReferenceHelper.getSendSignatureDescription((SendStatement) semanticElement);
			return resolvedReferencedDescription(descr, semanticElement);
		} else if (semanticElement instanceof ReceiveStatement) {
			descr = PooslReferenceHelper.getReceiveSignatureDescription((ReceiveStatement) semanticElement);
			return resolvedReferencedDescription(descr, semanticElement);
		}

		// --- Process Method References -------

		else if (semanticElement instanceof ProcessMethodCall) {
			descr = PooslReferenceHelper.getProcessMethodDescription((ProcessMethodCall) semanticElement);
			return resolvedReferencedDescription(descr, semanticElement);
		}

		// --- Port References -------

		else if (semanticElement instanceof PortReference) {
			descr = PooslReferenceHelper.getPortDescription((PortReference) semanticElement);
			return resolvedReferencedDescription(descr, semanticElement);
		}

		// --- Class References -------

		else if (semanticElement instanceof ProcessClass) {
			INode pNode = getAssignmentNode(node, Literals.PROCESS_CLASS__SUPER_CLASS);
			if (pNode != null) {
				descr = PooslCache.get(semanticElement.eResource()).getProcessClass(pNode.getText().trim());
				return resolvedReferencedDescription(descr, semanticElement);
			}
		} else if (semanticElement instanceof DataClass) {
			INode pNode = getAssignmentNode(node, Literals.DATA_CLASS__SUPER_CLASS);
			if (pNode != null) {
				descr = PooslCache.get(semanticElement.eResource()).getDataClass(pNode.getText().trim());
				return resolvedReferencedDescription(descr, semanticElement);
			}
		} else if (semanticElement instanceof Instance) {
			INode pNode = getAssignmentNode(node, Literals.INSTANCE__CLASS_DEFINITION);
			if (pNode != null) {
				descr = PooslReferenceHelper.getInstantiableClassDescription((Instance) semanticElement);
				return resolvedReferencedDescription(descr, semanticElement);
			}
		} else if (semanticElement instanceof NewExpression) {
			INode pNode = getAssignmentNode(node, Literals.NEW_EXPRESSION__DATA_CLASS);
			if (pNode != null) {
				descr = PooslReferenceHelper.getDataClassDescription((NewExpression) semanticElement);
				return resolvedReferencedDescription(descr, semanticElement);
			}
		} else if (semanticElement instanceof DataMethod) {
			INode pNode = getAssignmentNode(node, Literals.DATA_METHOD__RETURN_TYPE);
			if (pNode != null) {
				descr = PooslReferenceHelper.getDataClassDescription((DataMethod) semanticElement);
				return resolvedReferencedDescription(descr, semanticElement);
			}
		} else if (semanticElement instanceof Declaration) {
			INode pNode = getAssignmentNode(node, Literals.DECLARATION__TYPE);
			if (pNode != null) {
				descr = PooslReferenceHelper.getDataClassDescription((Declaration) semanticElement);
				return resolvedReferencedDescription(descr, semanticElement);
			}
		} else if (semanticElement instanceof MessageParameter) {
			descr = PooslReferenceHelper.getDataClassDescription((MessageParameter) semanticElement);
			return resolvedReferencedDescription(descr, semanticElement);
		}

		// --- Variable References -------

		else if (semanticElement instanceof OutputVariable) {
			descr = PooslReferenceHelper.getVariableDescription((OutputVariable) semanticElement);
			return resolvedReferencedDescription(descr, semanticElement);
		} else if (semanticElement instanceof VariableExpression) {
			descr = PooslReferenceHelper.getVariableDescription((VariableExpression) semanticElement);
			return resolvedReferencedDescription(descr, semanticElement);
		} else if (semanticElement instanceof AssignmentExpression) {
			INode pNode = getAssignmentNode(node, Literals.ASSIGNMENT_EXPRESSION__VARIABLE);
			if (pNode != null) {
				descr = PooslReferenceHelper.getVariableDescription((AssignmentExpression) semanticElement);
				return resolvedReferencedDescription(descr, semanticElement);
			}
		} else if (semanticElement instanceof InstanceParameter) {
			INode pNode = getAssignmentNode(node, Literals.INSTANCE_PARAMETER__PARAMETER);
			if (pNode != null) {
				descr = PooslReferenceHelper.getVariableDescription((InstanceParameter) semanticElement);
				return resolvedReferencedDescription(descr, semanticElement);
			}
		}

		// --- Data Method References -------

		else if (semanticElement instanceof DataMethodCallExpression) {
			descr = PooslReferenceHelper.getDataMethodNamedDescription((DataMethodCallExpression) semanticElement,
					pooslTypeSystem);
			return resolvedReferencedDescription(descr, semanticElement);
		} else if (semanticElement instanceof BinaryOperatorExpression) {
			descr = PooslReferenceHelper.getDataMethodBinaryDescription((BinaryOperatorExpression) semanticElement,
					pooslTypeSystem);
			return resolvedReferencedDescription(descr, semanticElement);
		} else if (semanticElement instanceof UnaryOperatorExpression) {
			descr = PooslReferenceHelper.getDataMethodUnaryDescription((UnaryOperatorExpression) semanticElement,
					pooslTypeSystem);
			return resolvedReferencedDescription(descr, semanticElement);
		}

		return super.resolveCrossReferencedElement(node);
	}

	/*
	 * Overrides the check if the node is a refering node. (non-Javadoc)
	 * 
	 * @see org.eclipse.xtext.resource.EObjectAtOffsetHelper#
	 * getCrossReferencedElement(org.eclipse.xtext.nodemodel.INode)
	 */
	@Override
	public EObject getCrossReferencedElement(INode node) {
		if (!(node.getGrammarElement() instanceof CrossReference) && !hasPooslReference(node))
			throw new IllegalArgumentException("Passed node not a cross reference node.");
		return resolveCrossReferencedElement(node);
	}

	/*
	 * Tries to find a node with a cross reference. (non-Javadoc)
	 * 
	 * @see org.eclipse.xtext.resource.EObjectAtOffsetHelper#findCrossReferenceNode(
	 * org.eclipse.xtext.nodemodel.INode)
	 */
	@Override
	protected INode findCrossReferenceNode(INode originalNode) {
		if (originalNode == null) {
			return null;
		}

		INode node = originalNode;
		if (node.getGrammarElement() instanceof CrossReference || hasPooslReference(node)) {
			return node;
		}

		while (node.getGrammarElement() != null && isElementOfDatatypeRule(node.getGrammarElement())) {
			node = node.getParent();
			if (node.getGrammarElement() instanceof CrossReference || hasPooslReference(node)) {
				return node;
			}
		}
		return null;
	}

	private boolean hasPooslReference(INode node) {
		EObject semanticElement = node.getSemanticElement();
		return semanticElement instanceof SendStatement || semanticElement instanceof ReceiveStatement
				|| semanticElement instanceof ProcessMethodCall || semanticElement instanceof PortReference
				|| isClassReference(node, semanticElement) || isVariableReference(node, semanticElement)
				|| isDataMethodReference(semanticElement);
	}

	private boolean isClassReference(INode node, EObject semanticElement) {
		if (semanticElement instanceof MessageParameter) {
			return true;
		} else if (semanticElement instanceof ProcessClass) {
			return getAssignmentNode(node, Literals.PROCESS_CLASS__SUPER_CLASS) != null;
		} else if (semanticElement instanceof DataClass) {
			return getAssignmentNode(node, Literals.DATA_CLASS__SUPER_CLASS) != null;
		} else if (semanticElement instanceof Instance) {
			return getAssignmentNode(node, Literals.INSTANCE__CLASS_DEFINITION) != null;
		} else if (semanticElement instanceof NewExpression) {
			return getAssignmentNode(node, Literals.NEW_EXPRESSION__DATA_CLASS) != null;
		} else if (semanticElement instanceof DataMethod) {
			return getAssignmentNode(node, Literals.DATA_METHOD__RETURN_TYPE) != null;
		} else if (semanticElement instanceof Declaration) {
			return getAssignmentNode(node, Literals.DECLARATION__TYPE) != null;
		}
		return false;
	}

	private boolean isVariableReference(INode node, EObject semanticElement) {
		if (semanticElement instanceof OutputVariable) {
			return true;
		} else if (semanticElement instanceof VariableExpression) {
			return true;
		} else if (semanticElement instanceof AssignmentExpression) {
			return getAssignmentNode(node, Literals.ASSIGNMENT_EXPRESSION__VARIABLE) != null;
		} else if (semanticElement instanceof InstanceParameter) {
			return getAssignmentNode(node, Literals.INSTANCE_PARAMETER__PARAMETER) != null;
		} else {
			return false;
		}
	}

	private boolean isDataMethodReference(EObject semanticElement) {
		return semanticElement instanceof DataMethodCallExpression
				|| semanticElement instanceof BinaryOperatorExpression
				|| semanticElement instanceof UnaryOperatorExpression;

	}

	private INode getAssignmentNode(INode node, EStructuralFeature feature) {
		INode pNode = node;
		Assignment assignment = GrammarUtil.containingAssignment(pNode.getGrammarElement());
		while (assignment == null && pNode != null) {
			pNode = pNode.getParent();
			assignment = GrammarUtil.containingAssignment(pNode.getGrammarElement());
		}
		if (assignment != null && assignment.getFeature().equals(feature.getName())) {
			return pNode;
		} else {
			return null;
		}
	}

	private EObject resolvedReferencedDescription(IEObjectDescription descr, EObject semanticElement) {
		if (descr == null)
			return null;
		EObject referencedElement = descr.getEObjectOrProxy();
		if (referencedElement.eIsProxy())
			referencedElement = EcoreUtil2.resolve(referencedElement, semanticElement);
		return referencedElement;
	}

}
