/*******************************************************************************
 * Copyright (c) 2021 TNO/ESI.
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
package org.eclipse.poosl.xtext.serializer;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.poosl.xtext.services.PooslGrammarAccess;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.GroupAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;

@SuppressWarnings("all")
public class PooslSyntacticSequencer extends AbstractSyntacticSequencer {

	protected PooslGrammarAccess grammarAccess;
	protected AbstractElementAlias match_Annotation___LeftParenthesisKeyword_2_0_RightParenthesisKeyword_2_2__q;
	protected AbstractElementAlias match_BracketedArgumentStartExpressionLevel5___LeftParenthesisKeyword_1_3_0_RightParenthesisKeyword_1_3_2__q;
	protected AbstractElementAlias match_ClusterClass_CommaKeyword_6_1_0_q;
	protected AbstractElementAlias match_ClusterClass_CommaKeyword_6_2_q;
	protected AbstractElementAlias match_ClusterClass___LeftParenthesisKeyword_4_0_RightParenthesisKeyword_4_2__q;
	protected AbstractElementAlias match_DataClass_CommaKeyword_7_1_0_q;
	protected AbstractElementAlias match_DataClass_CommaKeyword_7_2_q;
	protected AbstractElementAlias match_DataMethodBinaryOperator___VerticalLineKeyword_9_0_VerticalLineKeyword_9_2__q;
	protected AbstractElementAlias match_DataMethodNamed___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q;
	protected AbstractElementAlias match_DataMethodNamed___VerticalLineKeyword_6_0_VerticalLineKeyword_6_2__q;
	protected AbstractElementAlias match_DataMethodUnaryOperator___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_1__q;
	protected AbstractElementAlias match_DataMethodUnaryOperator___VerticalLineKeyword_6_0_VerticalLineKeyword_6_2__q;
	protected AbstractElementAlias match_ExpressionLevel5___LeftParenthesisKeyword_1_3_0_RightParenthesisKeyword_1_3_2__q;
	protected AbstractElementAlias match_IDStartWithinStatementExpressionLevel5___LeftParenthesisKeyword_1_3_0_RightParenthesisKeyword_1_3_2__q;
	protected AbstractElementAlias match_Instance___LeftParenthesisKeyword_4_0_RightParenthesisKeyword_4_2__q;
	protected AbstractElementAlias match_MessageReceiveSignature___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q;
	protected AbstractElementAlias match_MessageSendSignature___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q;
	protected AbstractElementAlias match_NonIDStartWithinStatementExpressionLevel5___LeftParenthesisKeyword_1_3_0_RightParenthesisKeyword_1_3_2__q;
	protected AbstractElementAlias match_ProcessClass_CommaKeyword_11_1_0_q;
	protected AbstractElementAlias match_ProcessClass_CommaKeyword_11_2_q;
	protected AbstractElementAlias match_ProcessClass_CommaKeyword_7_1_0_q;
	protected AbstractElementAlias match_ProcessClass_CommaKeyword_7_2_q;
	protected AbstractElementAlias match_ProcessClass_CommaKeyword_9_1_0_q;
	protected AbstractElementAlias match_ProcessClass_CommaKeyword_9_2_q;
	protected AbstractElementAlias match_ProcessClass___LeftParenthesisKeyword_4_0_RightParenthesisKeyword_4_2__q;
	protected AbstractElementAlias match_ProcessMethod___VerticalLineKeyword_8_0_VerticalLineKeyword_8_2__q;
	protected AbstractElementAlias match_ReceiveStatement___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_3__q;
	protected AbstractElementAlias match_SendStatement___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q;
	protected AbstractElementAlias match_System_CommaKeyword_4_1_1_0_q;
	protected AbstractElementAlias match_System_CommaKeyword_4_1_2_q;
	protected AbstractElementAlias match_System_PortsKeyword_4_0_q;
	protected AbstractElementAlias match_System___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (PooslGrammarAccess) access;
		match_Annotation___LeftParenthesisKeyword_2_0_RightParenthesisKeyword_2_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getAnnotationAccess().getLeftParenthesisKeyword_2_0()), new TokenAlias(false, false, grammarAccess.getAnnotationAccess().getRightParenthesisKeyword_2_2()));
		match_BracketedArgumentStartExpressionLevel5___LeftParenthesisKeyword_1_3_0_RightParenthesisKeyword_1_3_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getLeftParenthesisKeyword_1_3_0()), new TokenAlias(false, false, grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getRightParenthesisKeyword_1_3_2()));
		match_ClusterClass_CommaKeyword_6_1_0_q = new TokenAlias(false, true, grammarAccess.getClusterClassAccess().getCommaKeyword_6_1_0());
		match_ClusterClass_CommaKeyword_6_2_q = new TokenAlias(false, true, grammarAccess.getClusterClassAccess().getCommaKeyword_6_2());
		match_ClusterClass___LeftParenthesisKeyword_4_0_RightParenthesisKeyword_4_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getClusterClassAccess().getLeftParenthesisKeyword_4_0()), new TokenAlias(false, false, grammarAccess.getClusterClassAccess().getRightParenthesisKeyword_4_2()));
		match_DataClass_CommaKeyword_7_1_0_q = new TokenAlias(false, true, grammarAccess.getDataClassAccess().getCommaKeyword_7_1_0());
		match_DataClass_CommaKeyword_7_2_q = new TokenAlias(false, true, grammarAccess.getDataClassAccess().getCommaKeyword_7_2());
		match_DataMethodBinaryOperator___VerticalLineKeyword_9_0_VerticalLineKeyword_9_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getDataMethodBinaryOperatorAccess().getVerticalLineKeyword_9_0()), new TokenAlias(false, false, grammarAccess.getDataMethodBinaryOperatorAccess().getVerticalLineKeyword_9_2()));
		match_DataMethodNamed___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getDataMethodNamedAccess().getLeftParenthesisKeyword_3_0()), new TokenAlias(false, false, grammarAccess.getDataMethodNamedAccess().getRightParenthesisKeyword_3_2()));
		match_DataMethodNamed___VerticalLineKeyword_6_0_VerticalLineKeyword_6_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getDataMethodNamedAccess().getVerticalLineKeyword_6_0()), new TokenAlias(false, false, grammarAccess.getDataMethodNamedAccess().getVerticalLineKeyword_6_2()));
		match_DataMethodUnaryOperator___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_1__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getDataMethodUnaryOperatorAccess().getLeftParenthesisKeyword_3_0()), new TokenAlias(false, false, grammarAccess.getDataMethodUnaryOperatorAccess().getRightParenthesisKeyword_3_1()));
		match_DataMethodUnaryOperator___VerticalLineKeyword_6_0_VerticalLineKeyword_6_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getDataMethodUnaryOperatorAccess().getVerticalLineKeyword_6_0()), new TokenAlias(false, false, grammarAccess.getDataMethodUnaryOperatorAccess().getVerticalLineKeyword_6_2()));
		match_ExpressionLevel5___LeftParenthesisKeyword_1_3_0_RightParenthesisKeyword_1_3_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getExpressionLevel5Access().getLeftParenthesisKeyword_1_3_0()), new TokenAlias(false, false, grammarAccess.getExpressionLevel5Access().getRightParenthesisKeyword_1_3_2()));
		match_IDStartWithinStatementExpressionLevel5___LeftParenthesisKeyword_1_3_0_RightParenthesisKeyword_1_3_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getLeftParenthesisKeyword_1_3_0()), new TokenAlias(false, false, grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getRightParenthesisKeyword_1_3_2()));
		match_Instance___LeftParenthesisKeyword_4_0_RightParenthesisKeyword_4_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getInstanceAccess().getLeftParenthesisKeyword_4_0()), new TokenAlias(false, false, grammarAccess.getInstanceAccess().getRightParenthesisKeyword_4_2()));
		match_MessageReceiveSignature___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getMessageReceiveSignatureAccess().getLeftParenthesisKeyword_3_0()), new TokenAlias(false, false, grammarAccess.getMessageReceiveSignatureAccess().getRightParenthesisKeyword_3_2()));
		match_MessageSendSignature___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getMessageSendSignatureAccess().getLeftParenthesisKeyword_3_0()), new TokenAlias(false, false, grammarAccess.getMessageSendSignatureAccess().getRightParenthesisKeyword_3_2()));
		match_NonIDStartWithinStatementExpressionLevel5___LeftParenthesisKeyword_1_3_0_RightParenthesisKeyword_1_3_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getLeftParenthesisKeyword_1_3_0()), new TokenAlias(false, false, grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getRightParenthesisKeyword_1_3_2()));
		match_ProcessClass_CommaKeyword_11_1_0_q = new TokenAlias(false, true, grammarAccess.getProcessClassAccess().getCommaKeyword_11_1_0());
		match_ProcessClass_CommaKeyword_11_2_q = new TokenAlias(false, true, grammarAccess.getProcessClassAccess().getCommaKeyword_11_2());
		match_ProcessClass_CommaKeyword_7_1_0_q = new TokenAlias(false, true, grammarAccess.getProcessClassAccess().getCommaKeyword_7_1_0());
		match_ProcessClass_CommaKeyword_7_2_q = new TokenAlias(false, true, grammarAccess.getProcessClassAccess().getCommaKeyword_7_2());
		match_ProcessClass_CommaKeyword_9_1_0_q = new TokenAlias(false, true, grammarAccess.getProcessClassAccess().getCommaKeyword_9_1_0());
		match_ProcessClass_CommaKeyword_9_2_q = new TokenAlias(false, true, grammarAccess.getProcessClassAccess().getCommaKeyword_9_2());
		match_ProcessClass___LeftParenthesisKeyword_4_0_RightParenthesisKeyword_4_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getProcessClassAccess().getLeftParenthesisKeyword_4_0()), new TokenAlias(false, false, grammarAccess.getProcessClassAccess().getRightParenthesisKeyword_4_2()));
		match_ProcessMethod___VerticalLineKeyword_8_0_VerticalLineKeyword_8_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getProcessMethodAccess().getVerticalLineKeyword_8_0()), new TokenAlias(false, false, grammarAccess.getProcessMethodAccess().getVerticalLineKeyword_8_2()));
		match_ReceiveStatement___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_3__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getReceiveStatementAccess().getLeftParenthesisKeyword_3_0()), new TokenAlias(false, false, grammarAccess.getReceiveStatementAccess().getRightParenthesisKeyword_3_3()));
		match_SendStatement___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getSendStatementAccess().getLeftParenthesisKeyword_3_0()), new TokenAlias(false, false, grammarAccess.getSendStatementAccess().getRightParenthesisKeyword_3_2()));
		match_System_CommaKeyword_4_1_1_0_q = new TokenAlias(false, true, grammarAccess.getSystemAccess().getCommaKeyword_4_1_1_0());
		match_System_CommaKeyword_4_1_2_q = new TokenAlias(false, true, grammarAccess.getSystemAccess().getCommaKeyword_4_1_2());
		match_System_PortsKeyword_4_0_q = new TokenAlias(false, true, grammarAccess.getSystemAccess().getPortsKeyword_4_0());
		match_System___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getSystemAccess().getLeftParenthesisKeyword_3_0()), new TokenAlias(false, false, grammarAccess.getSystemAccess().getRightParenthesisKeyword_3_2()));
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		return "";
	}
	
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if (match_Annotation___LeftParenthesisKeyword_2_0_RightParenthesisKeyword_2_2__q.equals(syntax))
				emit_Annotation___LeftParenthesisKeyword_2_0_RightParenthesisKeyword_2_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_BracketedArgumentStartExpressionLevel5___LeftParenthesisKeyword_1_3_0_RightParenthesisKeyword_1_3_2__q.equals(syntax))
				emit_BracketedArgumentStartExpressionLevel5___LeftParenthesisKeyword_1_3_0_RightParenthesisKeyword_1_3_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_ClusterClass_CommaKeyword_6_1_0_q.equals(syntax))
				emit_ClusterClass_CommaKeyword_6_1_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_ClusterClass_CommaKeyword_6_2_q.equals(syntax))
				emit_ClusterClass_CommaKeyword_6_2_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_ClusterClass___LeftParenthesisKeyword_4_0_RightParenthesisKeyword_4_2__q.equals(syntax))
				emit_ClusterClass___LeftParenthesisKeyword_4_0_RightParenthesisKeyword_4_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_DataClass_CommaKeyword_7_1_0_q.equals(syntax))
				emit_DataClass_CommaKeyword_7_1_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_DataClass_CommaKeyword_7_2_q.equals(syntax))
				emit_DataClass_CommaKeyword_7_2_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_DataMethodBinaryOperator___VerticalLineKeyword_9_0_VerticalLineKeyword_9_2__q.equals(syntax))
				emit_DataMethodBinaryOperator___VerticalLineKeyword_9_0_VerticalLineKeyword_9_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_DataMethodNamed___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q.equals(syntax))
				emit_DataMethodNamed___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_DataMethodNamed___VerticalLineKeyword_6_0_VerticalLineKeyword_6_2__q.equals(syntax))
				emit_DataMethodNamed___VerticalLineKeyword_6_0_VerticalLineKeyword_6_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_DataMethodUnaryOperator___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_1__q.equals(syntax))
				emit_DataMethodUnaryOperator___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_1__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_DataMethodUnaryOperator___VerticalLineKeyword_6_0_VerticalLineKeyword_6_2__q.equals(syntax))
				emit_DataMethodUnaryOperator___VerticalLineKeyword_6_0_VerticalLineKeyword_6_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_ExpressionLevel5___LeftParenthesisKeyword_1_3_0_RightParenthesisKeyword_1_3_2__q.equals(syntax))
				emit_ExpressionLevel5___LeftParenthesisKeyword_1_3_0_RightParenthesisKeyword_1_3_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_IDStartWithinStatementExpressionLevel5___LeftParenthesisKeyword_1_3_0_RightParenthesisKeyword_1_3_2__q.equals(syntax))
				emit_IDStartWithinStatementExpressionLevel5___LeftParenthesisKeyword_1_3_0_RightParenthesisKeyword_1_3_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_Instance___LeftParenthesisKeyword_4_0_RightParenthesisKeyword_4_2__q.equals(syntax))
				emit_Instance___LeftParenthesisKeyword_4_0_RightParenthesisKeyword_4_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_MessageReceiveSignature___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q.equals(syntax))
				emit_MessageReceiveSignature___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_MessageSendSignature___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q.equals(syntax))
				emit_MessageSendSignature___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_NonIDStartWithinStatementExpressionLevel5___LeftParenthesisKeyword_1_3_0_RightParenthesisKeyword_1_3_2__q.equals(syntax))
				emit_NonIDStartWithinStatementExpressionLevel5___LeftParenthesisKeyword_1_3_0_RightParenthesisKeyword_1_3_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_ProcessClass_CommaKeyword_11_1_0_q.equals(syntax))
				emit_ProcessClass_CommaKeyword_11_1_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_ProcessClass_CommaKeyword_11_2_q.equals(syntax))
				emit_ProcessClass_CommaKeyword_11_2_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_ProcessClass_CommaKeyword_7_1_0_q.equals(syntax))
				emit_ProcessClass_CommaKeyword_7_1_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_ProcessClass_CommaKeyword_7_2_q.equals(syntax))
				emit_ProcessClass_CommaKeyword_7_2_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_ProcessClass_CommaKeyword_9_1_0_q.equals(syntax))
				emit_ProcessClass_CommaKeyword_9_1_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_ProcessClass_CommaKeyword_9_2_q.equals(syntax))
				emit_ProcessClass_CommaKeyword_9_2_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_ProcessClass___LeftParenthesisKeyword_4_0_RightParenthesisKeyword_4_2__q.equals(syntax))
				emit_ProcessClass___LeftParenthesisKeyword_4_0_RightParenthesisKeyword_4_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_ProcessMethod___VerticalLineKeyword_8_0_VerticalLineKeyword_8_2__q.equals(syntax))
				emit_ProcessMethod___VerticalLineKeyword_8_0_VerticalLineKeyword_8_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_ReceiveStatement___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_3__q.equals(syntax))
				emit_ReceiveStatement___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_3__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_SendStatement___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q.equals(syntax))
				emit_SendStatement___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_System_CommaKeyword_4_1_1_0_q.equals(syntax))
				emit_System_CommaKeyword_4_1_1_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_System_CommaKeyword_4_1_2_q.equals(syntax))
				emit_System_CommaKeyword_4_1_2_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_System_PortsKeyword_4_0_q.equals(syntax))
				emit_System_PortsKeyword_4_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_System___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q.equals(syntax))
				emit_System___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Ambiguous syntax:
	 *     ('(' ')')?
	 *
	 * This ambiguous syntax occurs at:
	 *     name=IDENTIFIER (ambiguity) (rule end)
	 */
	protected void emit_Annotation___LeftParenthesisKeyword_2_0_RightParenthesisKeyword_2_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ('(' ')')?
	 *
	 * This ambiguous syntax occurs at:
	 *     name=IDENTIFIER (ambiguity) (rule end)
	 */
	protected void emit_BracketedArgumentStartExpressionLevel5___LeftParenthesisKeyword_1_3_0_RightParenthesisKeyword_1_3_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ','?
	 *
	 * This ambiguous syntax occurs at:
	 *     ports+=Port (ambiguity) ports+=Port
	 */
	protected void emit_ClusterClass_CommaKeyword_6_1_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ','?
	 *
	 * This ambiguous syntax occurs at:
	 *     ports+=Port (ambiguity) 'instances' 'channels' (rule end)
	 *     ports+=Port (ambiguity) 'instances' 'channels' channels+=Channel
	 *     ports+=Port (ambiguity) 'instances' instances+=Instance
	 */
	protected void emit_ClusterClass_CommaKeyword_6_2_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ('(' ')')?
	 *
	 * This ambiguous syntax occurs at:
	 *     name=IDENTIFIER (ambiguity) 'ports' 'instances' 'channels' (rule end)
	 *     name=IDENTIFIER (ambiguity) 'ports' 'instances' 'channels' channels+=Channel
	 *     name=IDENTIFIER (ambiguity) 'ports' 'instances' instances+=Instance
	 *     name=IDENTIFIER (ambiguity) 'ports' ports+=Port
	 */
	protected void emit_ClusterClass___LeftParenthesisKeyword_4_0_RightParenthesisKeyword_4_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ','?
	 *
	 * This ambiguous syntax occurs at:
	 *     instanceVariables+=Declaration (ambiguity) instanceVariables+=Declaration
	 */
	protected void emit_DataClass_CommaKeyword_7_1_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ','?
	 *
	 * This ambiguous syntax occurs at:
	 *     instanceVariables+=Declaration (ambiguity) 'methods' (rule end)
	 *     instanceVariables+=Declaration (ambiguity) 'methods' dataMethodsBinaryOperator+=DataMethodBinaryOperator
	 *     instanceVariables+=Declaration (ambiguity) 'methods' dataMethodsNamed+=DataMethodNamed
	 *     instanceVariables+=Declaration (ambiguity) 'methods' dataMethodsUnaryOperator+=DataMethodUnaryOperator
	 */
	protected void emit_DataClass_CommaKeyword_7_2_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ('|' '|')?
	 *
	 * This ambiguous syntax occurs at:
	 *     returnType=IDENTIFIER (ambiguity) (rule end)
	 *     returnType=IDENTIFIER (ambiguity) body=Expression
	 */
	protected void emit_DataMethodBinaryOperator___VerticalLineKeyword_9_0_VerticalLineKeyword_9_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ('(' ')')?
	 *
	 * This ambiguous syntax occurs at:
	 *     name=IDENTIFIER (ambiguity) ':' returnType=IDENTIFIER
	 */
	protected void emit_DataMethodNamed___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ('|' '|')?
	 *
	 * This ambiguous syntax occurs at:
	 *     returnType=IDENTIFIER (ambiguity) (rule end)
	 *     returnType=IDENTIFIER (ambiguity) body=Expression
	 */
	protected void emit_DataMethodNamed___VerticalLineKeyword_6_0_VerticalLineKeyword_6_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ('(' ')')?
	 *
	 * This ambiguous syntax occurs at:
	 *     name=OperatorUnary (ambiguity) ':' returnType=IDENTIFIER
	 */
	protected void emit_DataMethodUnaryOperator___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_1__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ('|' '|')?
	 *
	 * This ambiguous syntax occurs at:
	 *     returnType=IDENTIFIER (ambiguity) (rule end)
	 *     returnType=IDENTIFIER (ambiguity) body=Expression
	 */
	protected void emit_DataMethodUnaryOperator___VerticalLineKeyword_6_0_VerticalLineKeyword_6_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ('(' ')')?
	 *
	 * This ambiguous syntax occurs at:
	 *     name=IDENTIFIER (ambiguity) (rule end)
	 */
	protected void emit_ExpressionLevel5___LeftParenthesisKeyword_1_3_0_RightParenthesisKeyword_1_3_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ('(' ')')?
	 *
	 * This ambiguous syntax occurs at:
	 *     name=IDENTIFIER (ambiguity) (rule end)
	 */
	protected void emit_IDStartWithinStatementExpressionLevel5___LeftParenthesisKeyword_1_3_0_RightParenthesisKeyword_1_3_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ('(' ')')?
	 *
	 * This ambiguous syntax occurs at:
	 *     classDefinition=IDENTIFIER (ambiguity) (rule end)
	 */
	protected void emit_Instance___LeftParenthesisKeyword_4_0_RightParenthesisKeyword_4_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ('(' ')')?
	 *
	 * This ambiguous syntax occurs at:
	 *     name=IDENTIFIER (ambiguity) (rule end)
	 */
	protected void emit_MessageReceiveSignature___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ('(' ')')?
	 *
	 * This ambiguous syntax occurs at:
	 *     name=IDENTIFIER (ambiguity) (rule end)
	 */
	protected void emit_MessageSendSignature___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ('(' ')')?
	 *
	 * This ambiguous syntax occurs at:
	 *     name=IDENTIFIER (ambiguity) (rule end)
	 */
	protected void emit_NonIDStartWithinStatementExpressionLevel5___LeftParenthesisKeyword_1_3_0_RightParenthesisKeyword_1_3_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ','?
	 *
	 * This ambiguous syntax occurs at:
	 *     instanceVariables+=Declaration (ambiguity) instanceVariables+=Declaration
	 */
	protected void emit_ProcessClass_CommaKeyword_11_1_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ','?
	 *
	 * This ambiguous syntax occurs at:
	 *     instanceVariables+=Declaration (ambiguity) 'init' 'methods' (rule end)
	 *     instanceVariables+=Declaration (ambiguity) 'init' 'methods' methods+=ProcessMethod
	 *     instanceVariables+=Declaration (ambiguity) 'init' initialMethodCall=ProcessMethodCall
	 */
	protected void emit_ProcessClass_CommaKeyword_11_2_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ','?
	 *
	 * This ambiguous syntax occurs at:
	 *     ports+=Port (ambiguity) ports+=Port
	 */
	protected void emit_ProcessClass_CommaKeyword_7_1_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ','?
	 *
	 * This ambiguous syntax occurs at:
	 *     ports+=Port (ambiguity) 'messages' 'variables' 'init' 'methods' (rule end)
	 *     ports+=Port (ambiguity) 'messages' 'variables' 'init' 'methods' methods+=ProcessMethod
	 *     ports+=Port (ambiguity) 'messages' 'variables' 'init' initialMethodCall=ProcessMethodCall
	 *     ports+=Port (ambiguity) 'messages' 'variables' instanceVariables+=Declaration
	 *     ports+=Port (ambiguity) 'messages' receiveMessages+=MessageReceiveSignature
	 *     ports+=Port (ambiguity) 'messages' sendMessages+=MessageSendSignature
	 */
	protected void emit_ProcessClass_CommaKeyword_7_2_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ','?
	 *
	 * This ambiguous syntax occurs at:
	 *     receiveMessages+=MessageReceiveSignature (ambiguity) receiveMessages+=MessageReceiveSignature
	 *     receiveMessages+=MessageReceiveSignature (ambiguity) sendMessages+=MessageSendSignature
	 *     sendMessages+=MessageSendSignature (ambiguity) receiveMessages+=MessageReceiveSignature
	 *     sendMessages+=MessageSendSignature (ambiguity) sendMessages+=MessageSendSignature
	 */
	protected void emit_ProcessClass_CommaKeyword_9_1_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ','?
	 *
	 * This ambiguous syntax occurs at:
	 *     receiveMessages+=MessageReceiveSignature (ambiguity) 'variables' 'init' 'methods' (rule end)
	 *     receiveMessages+=MessageReceiveSignature (ambiguity) 'variables' 'init' 'methods' methods+=ProcessMethod
	 *     receiveMessages+=MessageReceiveSignature (ambiguity) 'variables' 'init' initialMethodCall=ProcessMethodCall
	 *     receiveMessages+=MessageReceiveSignature (ambiguity) 'variables' instanceVariables+=Declaration
	 *     sendMessages+=MessageSendSignature (ambiguity) 'variables' 'init' 'methods' (rule end)
	 *     sendMessages+=MessageSendSignature (ambiguity) 'variables' 'init' 'methods' methods+=ProcessMethod
	 *     sendMessages+=MessageSendSignature (ambiguity) 'variables' 'init' initialMethodCall=ProcessMethodCall
	 *     sendMessages+=MessageSendSignature (ambiguity) 'variables' instanceVariables+=Declaration
	 */
	protected void emit_ProcessClass_CommaKeyword_9_2_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ('(' ')')?
	 *
	 * This ambiguous syntax occurs at:
	 *     name=IDENTIFIER (ambiguity) 'extends' superClass=IDENTIFIER
	 *     name=IDENTIFIER (ambiguity) 'ports' 'messages' 'variables' 'init' 'methods' (rule end)
	 *     name=IDENTIFIER (ambiguity) 'ports' 'messages' 'variables' 'init' 'methods' methods+=ProcessMethod
	 *     name=IDENTIFIER (ambiguity) 'ports' 'messages' 'variables' 'init' initialMethodCall=ProcessMethodCall
	 *     name=IDENTIFIER (ambiguity) 'ports' 'messages' 'variables' instanceVariables+=Declaration
	 *     name=IDENTIFIER (ambiguity) 'ports' 'messages' receiveMessages+=MessageReceiveSignature
	 *     name=IDENTIFIER (ambiguity) 'ports' 'messages' sendMessages+=MessageSendSignature
	 *     name=IDENTIFIER (ambiguity) 'ports' ports+=Port
	 */
	protected void emit_ProcessClass___LeftParenthesisKeyword_4_0_RightParenthesisKeyword_4_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ('|' '|')?
	 *
	 * This ambiguous syntax occurs at:
	 *     inputParameters+=Declaration ')' '(' ')' (ambiguity) body=Statement
	 *     name=IDENTIFIER '(' ')' '(' ')' (ambiguity) body=Statement
	 *     outputParameters+=Declaration ')' (ambiguity) body=Statement
	 */
	protected void emit_ProcessMethod___VerticalLineKeyword_8_0_VerticalLineKeyword_8_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ('(' ')')?
	 *
	 * This ambiguous syntax occurs at:
	 *     name=IDENTIFIER (ambiguity) '{' postCommunicationExpression=Expression
	 *     name=IDENTIFIER (ambiguity) (rule end)
	 */
	protected void emit_ReceiveStatement___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_3__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ('(' ')')?
	 *
	 * This ambiguous syntax occurs at:
	 *     name=IDENTIFIER (ambiguity) '{' postCommunicationExpression=Expression
	 *     name=IDENTIFIER (ambiguity) (rule end)
	 */
	protected void emit_SendStatement___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ','?
	 *
	 * This ambiguous syntax occurs at:
	 *     ports+=Port (ambiguity) ports+=Port
	 */
	protected void emit_System_CommaKeyword_4_1_1_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ','?
	 *
	 * This ambiguous syntax occurs at:
	 *     ports+=Port (ambiguity) 'instances' 'channels' (rule end)
	 *     ports+=Port (ambiguity) 'instances' 'channels' channels+=Channel
	 *     ports+=Port (ambiguity) 'instances' instances+=Instance
	 */
	protected void emit_System_CommaKeyword_4_1_2_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     'ports'?
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) 'system' ('(' ')')? (ambiguity) 'instances' 'channels' (rule start)
	 *     (rule start) 'system' ('(' ')')? (ambiguity) 'instances' 'channels' channels+=Channel
	 *     (rule start) 'system' ('(' ')')? (ambiguity) 'instances' instances+=Instance
	 *     annotations+=Annotation 'system' ('(' ')')? (ambiguity) 'instances' 'channels' (rule end)
	 *     annotations+=Annotation 'system' ('(' ')')? (ambiguity) 'instances' 'channels' channels+=Channel
	 *     annotations+=Annotation 'system' ('(' ')')? (ambiguity) 'instances' instances+=Instance
	 *     parameters+=Declaration ')' (ambiguity) 'instances' 'channels' (rule end)
	 *     parameters+=Declaration ')' (ambiguity) 'instances' 'channels' channels+=Channel
	 *     parameters+=Declaration ')' (ambiguity) 'instances' instances+=Instance
	 */
	protected void emit_System_PortsKeyword_4_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ('(' ')')?
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) 'system' (ambiguity) 'ports' ports+=Port
	 *     (rule start) 'system' (ambiguity) 'ports'? 'instances' 'channels' (rule start)
	 *     (rule start) 'system' (ambiguity) 'ports'? 'instances' 'channels' channels+=Channel
	 *     (rule start) 'system' (ambiguity) 'ports'? 'instances' instances+=Instance
	 *     annotations+=Annotation 'system' (ambiguity) 'ports' ports+=Port
	 *     annotations+=Annotation 'system' (ambiguity) 'ports'? 'instances' 'channels' (rule end)
	 *     annotations+=Annotation 'system' (ambiguity) 'ports'? 'instances' 'channels' channels+=Channel
	 *     annotations+=Annotation 'system' (ambiguity) 'ports'? 'instances' instances+=Instance
	 */
	protected void emit_System___LeftParenthesisKeyword_3_0_RightParenthesisKeyword_3_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
