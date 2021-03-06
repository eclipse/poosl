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
package org.eclipse.poosl.xtext.ide.contentassist.antlr;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.Map;
import org.eclipse.poosl.xtext.ide.contentassist.antlr.internal.InternalPooslParser;
import org.eclipse.poosl.xtext.services.PooslGrammarAccess;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.AbstractContentAssistParser;

public class PooslParser extends AbstractContentAssistParser {

	@Singleton
	public static final class NameMappings {
		
		private final Map<AbstractElement, String> mappings;
		
		@Inject
		public NameMappings(PooslGrammarAccess grammarAccess) {
			ImmutableMap.Builder<AbstractElement, String> builder = ImmutableMap.builder();
			init(builder, grammarAccess);
			this.mappings = builder.build();
		}
		
		public String getRuleName(AbstractElement element) {
			return mappings.get(element);
		}
		
		private static void init(ImmutableMap.Builder<AbstractElement, String> builder, PooslGrammarAccess grammarAccess) {
			builder.put(grammarAccess.getPooslAccess().getAlternatives_1(), "rule__Poosl__Alternatives_1");
			builder.put(grammarAccess.getPooslAccess().getAlternatives_2(), "rule__Poosl__Alternatives_2");
			builder.put(grammarAccess.getPooslAccess().getAlternatives_3_1(), "rule__Poosl__Alternatives_3_1");
			builder.put(grammarAccess.getDataClassAccess().getAlternatives_9(), "rule__DataClass__Alternatives_9");
			builder.put(grammarAccess.getProcessClassAccess().getAlternatives_9_0(), "rule__ProcessClass__Alternatives_9_0");
			builder.put(grammarAccess.getProcessClassAccess().getAlternatives_9_1_1(), "rule__ProcessClass__Alternatives_9_1_1");
			builder.put(grammarAccess.getChannelAccess().getAlternatives_3(), "rule__Channel__Alternatives_3");
			builder.put(grammarAccess.getExpressionLevel1Access().getAlternatives(), "rule__ExpressionLevel1__Alternatives");
			builder.put(grammarAccess.getExpressionLevel6Access().getAlternatives(), "rule__ExpressionLevel6__Alternatives");
			builder.put(grammarAccess.getSwitchExpressionAccess().getAlternatives_3(), "rule__SwitchExpression__Alternatives_3");
			builder.put(grammarAccess.getExpressionLevel7Access().getAlternatives(), "rule__ExpressionLevel7__Alternatives");
			builder.put(grammarAccess.getExpressionConstantAccess().getAlternatives(), "rule__ExpressionConstant__Alternatives");
			builder.put(grammarAccess.getBooleanConstantAccess().getAlternatives(), "rule__BooleanConstant__Alternatives");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getAlternatives(), "rule__IDStartWithinStatementExpressionLevel1__Alternatives");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Access().getAlternatives(), "rule__NonIDStartWithinStatementExpressionLevel1__Alternatives");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel6Access().getAlternatives(), "rule__NonIDStartWithinStatementExpressionLevel6__Alternatives");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel7Access().getAlternatives(), "rule__NonIDStartWithinStatementExpressionLevel7__Alternatives");
			builder.put(grammarAccess.getStatementSingleAccess().getAlternatives(), "rule__StatementSingle__Alternatives");
			builder.put(grammarAccess.getExpressionStatementAccess().getAlternatives(), "rule__ExpressionStatement__Alternatives");
			builder.put(grammarAccess.getSwitchStatementAccess().getAlternatives_3(), "rule__SwitchStatement__Alternatives_3");
			builder.put(grammarAccess.getBracketStartStatementAccess().getAlternatives(), "rule__BracketStartStatement__Alternatives");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getAlternatives(), "rule__BracketedArgumentStartExpressionLevel2__Alternatives");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getAlternatives(), "rule__BracketedArgumentStartExpressionLevel3__Alternatives");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getAlternatives(), "rule__BracketedArgumentStartExpressionLevel4__Alternatives");
			builder.put(grammarAccess.getINTEGERAccess().getAlternatives_0(), "rule__INTEGER__Alternatives_0");
			builder.put(grammarAccess.getINTEGERAccess().getAlternatives_1(), "rule__INTEGER__Alternatives_1");
			builder.put(grammarAccess.getREALAccess().getAlternatives_0(), "rule__REAL__Alternatives_0");
			builder.put(grammarAccess.getFLOATAccess().getAlternatives_0(), "rule__FLOAT__Alternatives_0");
			builder.put(grammarAccess.getFLOATAccess().getAlternatives_1(), "rule__FLOAT__Alternatives_1");
			builder.put(grammarAccess.getIDENTIFIERAccess().getAlternatives(), "rule__IDENTIFIER__Alternatives");
			builder.put(grammarAccess.getOperatorUnaryAccess().getAlternatives(), "rule__OperatorUnary__Alternatives");
			builder.put(grammarAccess.getOperatorBinaryAccess().getAlternatives(), "rule__OperatorBinary__Alternatives");
			builder.put(grammarAccess.getBinaryOperatorLevel2Access().getAlternatives(), "rule__BinaryOperatorLevel2__Alternatives");
			builder.put(grammarAccess.getBinaryOperatorLevel3Access().getAlternatives(), "rule__BinaryOperatorLevel3__Alternatives");
			builder.put(grammarAccess.getBinaryOperatorLevel4Access().getAlternatives(), "rule__BinaryOperatorLevel4__Alternatives");
			builder.put(grammarAccess.getPooslAccess().getGroup(), "rule__Poosl__Group__0");
			builder.put(grammarAccess.getPooslAccess().getGroup_3(), "rule__Poosl__Group_3__0");
			builder.put(grammarAccess.getImportAccess().getGroup(), "rule__Import__Group__0");
			builder.put(grammarAccess.getImportLibAccess().getGroup(), "rule__ImportLib__Group__0");
			builder.put(grammarAccess.getDataClassAccess().getGroup(), "rule__DataClass__Group__0");
			builder.put(grammarAccess.getDataClassAccess().getGroup_5(), "rule__DataClass__Group_5__0");
			builder.put(grammarAccess.getDataClassAccess().getGroup_7(), "rule__DataClass__Group_7__0");
			builder.put(grammarAccess.getDataClassAccess().getGroup_7_1(), "rule__DataClass__Group_7_1__0");
			builder.put(grammarAccess.getDataMethodNamedAccess().getGroup(), "rule__DataMethodNamed__Group__0");
			builder.put(grammarAccess.getDataMethodNamedAccess().getGroup_3(), "rule__DataMethodNamed__Group_3__0");
			builder.put(grammarAccess.getDataMethodNamedAccess().getGroup_3_1(), "rule__DataMethodNamed__Group_3_1__0");
			builder.put(grammarAccess.getDataMethodNamedAccess().getGroup_3_1_1(), "rule__DataMethodNamed__Group_3_1_1__0");
			builder.put(grammarAccess.getDataMethodNamedAccess().getGroup_6(), "rule__DataMethodNamed__Group_6__0");
			builder.put(grammarAccess.getDataMethodNamedAccess().getGroup_6_1(), "rule__DataMethodNamed__Group_6_1__0");
			builder.put(grammarAccess.getDataMethodNamedAccess().getGroup_6_1_1(), "rule__DataMethodNamed__Group_6_1_1__0");
			builder.put(grammarAccess.getDataMethodBinaryOperatorAccess().getGroup(), "rule__DataMethodBinaryOperator__Group__0");
			builder.put(grammarAccess.getDataMethodBinaryOperatorAccess().getGroup_5(), "rule__DataMethodBinaryOperator__Group_5__0");
			builder.put(grammarAccess.getDataMethodBinaryOperatorAccess().getGroup_9(), "rule__DataMethodBinaryOperator__Group_9__0");
			builder.put(grammarAccess.getDataMethodBinaryOperatorAccess().getGroup_9_1(), "rule__DataMethodBinaryOperator__Group_9_1__0");
			builder.put(grammarAccess.getDataMethodBinaryOperatorAccess().getGroup_9_1_1(), "rule__DataMethodBinaryOperator__Group_9_1_1__0");
			builder.put(grammarAccess.getDataMethodUnaryOperatorAccess().getGroup(), "rule__DataMethodUnaryOperator__Group__0");
			builder.put(grammarAccess.getDataMethodUnaryOperatorAccess().getGroup_3(), "rule__DataMethodUnaryOperator__Group_3__0");
			builder.put(grammarAccess.getDataMethodUnaryOperatorAccess().getGroup_6(), "rule__DataMethodUnaryOperator__Group_6__0");
			builder.put(grammarAccess.getDataMethodUnaryOperatorAccess().getGroup_6_1(), "rule__DataMethodUnaryOperator__Group_6_1__0");
			builder.put(grammarAccess.getDataMethodUnaryOperatorAccess().getGroup_6_1_1(), "rule__DataMethodUnaryOperator__Group_6_1_1__0");
			builder.put(grammarAccess.getDeclarationAccess().getGroup(), "rule__Declaration__Group__0");
			builder.put(grammarAccess.getDeclarationAccess().getGroup_0(), "rule__Declaration__Group_0__0");
			builder.put(grammarAccess.getDeclarationAccess().getGroup_0_1(), "rule__Declaration__Group_0_1__0");
			builder.put(grammarAccess.getAnnotationAccess().getGroup(), "rule__Annotation__Group__0");
			builder.put(grammarAccess.getAnnotationAccess().getGroup_2(), "rule__Annotation__Group_2__0");
			builder.put(grammarAccess.getAnnotationAccess().getGroup_2_1(), "rule__Annotation__Group_2_1__0");
			builder.put(grammarAccess.getAnnotationAccess().getGroup_2_1_1(), "rule__Annotation__Group_2_1_1__0");
			builder.put(grammarAccess.getProcessClassAccess().getGroup(), "rule__ProcessClass__Group__0");
			builder.put(grammarAccess.getProcessClassAccess().getGroup_4(), "rule__ProcessClass__Group_4__0");
			builder.put(grammarAccess.getProcessClassAccess().getGroup_4_1(), "rule__ProcessClass__Group_4_1__0");
			builder.put(grammarAccess.getProcessClassAccess().getGroup_4_1_1(), "rule__ProcessClass__Group_4_1_1__0");
			builder.put(grammarAccess.getProcessClassAccess().getGroup_5(), "rule__ProcessClass__Group_5__0");
			builder.put(grammarAccess.getProcessClassAccess().getGroup_7(), "rule__ProcessClass__Group_7__0");
			builder.put(grammarAccess.getProcessClassAccess().getGroup_7_1(), "rule__ProcessClass__Group_7_1__0");
			builder.put(grammarAccess.getProcessClassAccess().getGroup_9(), "rule__ProcessClass__Group_9__0");
			builder.put(grammarAccess.getProcessClassAccess().getGroup_9_1(), "rule__ProcessClass__Group_9_1__0");
			builder.put(grammarAccess.getProcessClassAccess().getGroup_11(), "rule__ProcessClass__Group_11__0");
			builder.put(grammarAccess.getProcessClassAccess().getGroup_11_1(), "rule__ProcessClass__Group_11_1__0");
			builder.put(grammarAccess.getProcessMethodAccess().getGroup(), "rule__ProcessMethod__Group__0");
			builder.put(grammarAccess.getProcessMethodAccess().getGroup_3(), "rule__ProcessMethod__Group_3__0");
			builder.put(grammarAccess.getProcessMethodAccess().getGroup_3_1(), "rule__ProcessMethod__Group_3_1__0");
			builder.put(grammarAccess.getProcessMethodAccess().getGroup_6(), "rule__ProcessMethod__Group_6__0");
			builder.put(grammarAccess.getProcessMethodAccess().getGroup_6_1(), "rule__ProcessMethod__Group_6_1__0");
			builder.put(grammarAccess.getProcessMethodAccess().getGroup_8(), "rule__ProcessMethod__Group_8__0");
			builder.put(grammarAccess.getProcessMethodAccess().getGroup_8_1(), "rule__ProcessMethod__Group_8_1__0");
			builder.put(grammarAccess.getProcessMethodAccess().getGroup_8_1_1(), "rule__ProcessMethod__Group_8_1_1__0");
			builder.put(grammarAccess.getMessageReceiveSignatureAccess().getGroup(), "rule__MessageReceiveSignature__Group__0");
			builder.put(grammarAccess.getMessageReceiveSignatureAccess().getGroup_3(), "rule__MessageReceiveSignature__Group_3__0");
			builder.put(grammarAccess.getMessageReceiveSignatureAccess().getGroup_3_1(), "rule__MessageReceiveSignature__Group_3_1__0");
			builder.put(grammarAccess.getMessageReceiveSignatureAccess().getGroup_3_1_1(), "rule__MessageReceiveSignature__Group_3_1_1__0");
			builder.put(grammarAccess.getMessageSendSignatureAccess().getGroup(), "rule__MessageSendSignature__Group__0");
			builder.put(grammarAccess.getMessageSendSignatureAccess().getGroup_3(), "rule__MessageSendSignature__Group_3__0");
			builder.put(grammarAccess.getMessageSendSignatureAccess().getGroup_3_1(), "rule__MessageSendSignature__Group_3_1__0");
			builder.put(grammarAccess.getMessageSendSignatureAccess().getGroup_3_1_1(), "rule__MessageSendSignature__Group_3_1_1__0");
			builder.put(grammarAccess.getSystemAccess().getGroup(), "rule__System__Group__0");
			builder.put(grammarAccess.getSystemAccess().getGroup_3(), "rule__System__Group_3__0");
			builder.put(grammarAccess.getSystemAccess().getGroup_3_1(), "rule__System__Group_3_1__0");
			builder.put(grammarAccess.getSystemAccess().getGroup_3_1_1(), "rule__System__Group_3_1_1__0");
			builder.put(grammarAccess.getSystemAccess().getGroup_4(), "rule__System__Group_4__0");
			builder.put(grammarAccess.getSystemAccess().getGroup_4_1(), "rule__System__Group_4_1__0");
			builder.put(grammarAccess.getSystemAccess().getGroup_4_1_1(), "rule__System__Group_4_1_1__0");
			builder.put(grammarAccess.getClusterClassAccess().getGroup(), "rule__ClusterClass__Group__0");
			builder.put(grammarAccess.getClusterClassAccess().getGroup_4(), "rule__ClusterClass__Group_4__0");
			builder.put(grammarAccess.getClusterClassAccess().getGroup_4_1(), "rule__ClusterClass__Group_4_1__0");
			builder.put(grammarAccess.getClusterClassAccess().getGroup_4_1_1(), "rule__ClusterClass__Group_4_1_1__0");
			builder.put(grammarAccess.getClusterClassAccess().getGroup_6(), "rule__ClusterClass__Group_6__0");
			builder.put(grammarAccess.getClusterClassAccess().getGroup_6_1(), "rule__ClusterClass__Group_6_1__0");
			builder.put(grammarAccess.getInstanceAccess().getGroup(), "rule__Instance__Group__0");
			builder.put(grammarAccess.getInstanceAccess().getGroup_4(), "rule__Instance__Group_4__0");
			builder.put(grammarAccess.getInstanceAccess().getGroup_4_1(), "rule__Instance__Group_4_1__0");
			builder.put(grammarAccess.getInstanceAccess().getGroup_4_1_1(), "rule__Instance__Group_4_1_1__0");
			builder.put(grammarAccess.getInstanceParameterAccess().getGroup(), "rule__InstanceParameter__Group__0");
			builder.put(grammarAccess.getChannelAccess().getGroup(), "rule__Channel__Group__0");
			builder.put(grammarAccess.getChannelAccess().getGroup_3_0(), "rule__Channel__Group_3_0__0");
			builder.put(grammarAccess.getChannelAccess().getGroup_3_0_1(), "rule__Channel__Group_3_0_1__0");
			builder.put(grammarAccess.getChannelAccess().getGroup_3_0_2(), "rule__Channel__Group_3_0_2__0");
			builder.put(grammarAccess.getChannelAccess().getGroup_3_0_2_2(), "rule__Channel__Group_3_0_2_2__0");
			builder.put(grammarAccess.getChannelAccess().getGroup_3_1(), "rule__Channel__Group_3_1__0");
			builder.put(grammarAccess.getChannelAccess().getGroup_3_1_1(), "rule__Channel__Group_3_1_1__0");
			builder.put(grammarAccess.getInstancePortAccess().getGroup(), "rule__InstancePort__Group__0");
			builder.put(grammarAccess.getExpressionAccess().getGroup(), "rule__Expression__Group__0");
			builder.put(grammarAccess.getExpressionAccess().getGroup_1(), "rule__Expression__Group_1__0");
			builder.put(grammarAccess.getExpressionAccess().getGroup_1_1(), "rule__Expression__Group_1_1__0");
			builder.put(grammarAccess.getExpressionLevel1Access().getGroup_1(), "rule__ExpressionLevel1__Group_1__0");
			builder.put(grammarAccess.getExpressionLevel1Access().getGroup_2(), "rule__ExpressionLevel1__Group_2__0");
			builder.put(grammarAccess.getExpressionLevel2Access().getGroup(), "rule__ExpressionLevel2__Group__0");
			builder.put(grammarAccess.getExpressionLevel2Access().getGroup_1(), "rule__ExpressionLevel2__Group_1__0");
			builder.put(grammarAccess.getExpressionLevel3Access().getGroup(), "rule__ExpressionLevel3__Group__0");
			builder.put(grammarAccess.getExpressionLevel3Access().getGroup_1(), "rule__ExpressionLevel3__Group_1__0");
			builder.put(grammarAccess.getExpressionLevel4Access().getGroup(), "rule__ExpressionLevel4__Group__0");
			builder.put(grammarAccess.getExpressionLevel4Access().getGroup_1(), "rule__ExpressionLevel4__Group_1__0");
			builder.put(grammarAccess.getExpressionLevel5Access().getGroup(), "rule__ExpressionLevel5__Group__0");
			builder.put(grammarAccess.getExpressionLevel5Access().getGroup_1(), "rule__ExpressionLevel5__Group_1__0");
			builder.put(grammarAccess.getExpressionLevel5Access().getGroup_1_3(), "rule__ExpressionLevel5__Group_1_3__0");
			builder.put(grammarAccess.getExpressionLevel5Access().getGroup_1_3_1(), "rule__ExpressionLevel5__Group_1_3_1__0");
			builder.put(grammarAccess.getExpressionLevel5Access().getGroup_1_3_1_1(), "rule__ExpressionLevel5__Group_1_3_1_1__0");
			builder.put(grammarAccess.getUnaryOperatorExpressionAccess().getGroup(), "rule__UnaryOperatorExpression__Group__0");
			builder.put(grammarAccess.getIfExpressionAccess().getGroup(), "rule__IfExpression__Group__0");
			builder.put(grammarAccess.getIfExpressionAccess().getGroup_4(), "rule__IfExpression__Group_4__0");
			builder.put(grammarAccess.getWhileExpressionAccess().getGroup(), "rule__WhileExpression__Group__0");
			builder.put(grammarAccess.getSwitchExpressionAccess().getGroup(), "rule__SwitchExpression__Group__0");
			builder.put(grammarAccess.getSwitchExpressionAccess().getGroup_3_0(), "rule__SwitchExpression__Group_3_0__0");
			builder.put(grammarAccess.getSwitchExpressionAccess().getGroup_3_0_1(), "rule__SwitchExpression__Group_3_0_1__0");
			builder.put(grammarAccess.getSwitchExpressionAccess().getGroup_3_1(), "rule__SwitchExpression__Group_3_1__0");
			builder.put(grammarAccess.getSwitchExpressionCaseAccess().getGroup(), "rule__SwitchExpressionCase__Group__0");
			builder.put(grammarAccess.getCurrentTimeExpressionAccess().getGroup(), "rule__CurrentTimeExpression__Group__0");
			builder.put(grammarAccess.getSelfExpressionAccess().getGroup(), "rule__SelfExpression__Group__0");
			builder.put(grammarAccess.getNilConstantAccess().getGroup(), "rule__NilConstant__Group__0");
			builder.put(grammarAccess.getNewExpressionAccess().getGroup(), "rule__NewExpression__Group__0");
			builder.put(grammarAccess.getExpressionSequenceRoundBracketAccess().getGroup(), "rule__ExpressionSequenceRoundBracket__Group__0");
			builder.put(grammarAccess.getExpressionSequenceRoundBracketAccess().getGroup_2(), "rule__ExpressionSequenceRoundBracket__Group_2__0");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getGroup_1(), "rule__IDStartWithinStatementExpressionLevel1__Group_1__0");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getGroup(), "rule__IDStartWithinStatementExpressionLevel2__Group__0");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getGroup_1(), "rule__IDStartWithinStatementExpressionLevel2__Group_1__0");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getGroup(), "rule__IDStartWithinStatementExpressionLevel3__Group__0");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getGroup_1(), "rule__IDStartWithinStatementExpressionLevel3__Group_1__0");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getGroup(), "rule__IDStartWithinStatementExpressionLevel4__Group__0");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getGroup_1(), "rule__IDStartWithinStatementExpressionLevel4__Group_1__0");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getGroup(), "rule__IDStartWithinStatementExpressionLevel5__Group__0");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getGroup_1(), "rule__IDStartWithinStatementExpressionLevel5__Group_1__0");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getGroup_1_3(), "rule__IDStartWithinStatementExpressionLevel5__Group_1_3__0");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getGroup_1_3_1(), "rule__IDStartWithinStatementExpressionLevel5__Group_1_3_1__0");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getGroup_1_3_1_1(), "rule__IDStartWithinStatementExpressionLevel5__Group_1_3_1_1__0");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Access().getGroup_1(), "rule__NonIDStartWithinStatementExpressionLevel1__Group_1__0");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getGroup(), "rule__NonIDStartWithinStatementExpressionLevel2__Group__0");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getGroup_1(), "rule__NonIDStartWithinStatementExpressionLevel2__Group_1__0");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getGroup(), "rule__NonIDStartWithinStatementExpressionLevel3__Group__0");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getGroup_1(), "rule__NonIDStartWithinStatementExpressionLevel3__Group_1__0");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getGroup(), "rule__NonIDStartWithinStatementExpressionLevel4__Group__0");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getGroup_1(), "rule__NonIDStartWithinStatementExpressionLevel4__Group_1__0");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getGroup(), "rule__NonIDStartWithinStatementExpressionLevel5__Group__0");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getGroup_1(), "rule__NonIDStartWithinStatementExpressionLevel5__Group_1__0");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getGroup_1_3(), "rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3__0");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getGroup_1_3_1(), "rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3_1__0");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getGroup_1_3_1_1(), "rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3_1_1__0");
			builder.put(grammarAccess.getStatementAccess().getGroup(), "rule__Statement__Group__0");
			builder.put(grammarAccess.getStatementAccess().getGroup_1(), "rule__Statement__Group_1__0");
			builder.put(grammarAccess.getStatementAccess().getGroup_1_1(), "rule__Statement__Group_1_1__0");
			builder.put(grammarAccess.getExpressionStatementAccess().getGroup_2(), "rule__ExpressionStatement__Group_2__0");
			builder.put(grammarAccess.getDelayStatementAccess().getGroup(), "rule__DelayStatement__Group__0");
			builder.put(grammarAccess.getReceiveStatementAccess().getGroup(), "rule__ReceiveStatement__Group__0");
			builder.put(grammarAccess.getReceiveStatementAccess().getGroup_3(), "rule__ReceiveStatement__Group_3__0");
			builder.put(grammarAccess.getReceiveStatementAccess().getGroup_3_1(), "rule__ReceiveStatement__Group_3_1__0");
			builder.put(grammarAccess.getReceiveStatementAccess().getGroup_3_1_1(), "rule__ReceiveStatement__Group_3_1_1__0");
			builder.put(grammarAccess.getReceiveStatementAccess().getGroup_3_2(), "rule__ReceiveStatement__Group_3_2__0");
			builder.put(grammarAccess.getReceiveStatementAccess().getGroup_4(), "rule__ReceiveStatement__Group_4__0");
			builder.put(grammarAccess.getSendStatementAccess().getGroup(), "rule__SendStatement__Group__0");
			builder.put(grammarAccess.getSendStatementAccess().getGroup_3(), "rule__SendStatement__Group_3__0");
			builder.put(grammarAccess.getSendStatementAccess().getGroup_3_1(), "rule__SendStatement__Group_3_1__0");
			builder.put(grammarAccess.getSendStatementAccess().getGroup_3_1_1(), "rule__SendStatement__Group_3_1_1__0");
			builder.put(grammarAccess.getSendStatementAccess().getGroup_4(), "rule__SendStatement__Group_4__0");
			builder.put(grammarAccess.getPortDescriptorAccess().getGroup(), "rule__PortDescriptor__Group__0");
			builder.put(grammarAccess.getSkipStatementAccess().getGroup(), "rule__SkipStatement__Group__0");
			builder.put(grammarAccess.getGuardedStatementAccess().getGroup(), "rule__GuardedStatement__Group__0");
			builder.put(grammarAccess.getIfStatementAccess().getGroup(), "rule__IfStatement__Group__0");
			builder.put(grammarAccess.getIfStatementAccess().getGroup_4(), "rule__IfStatement__Group_4__0");
			builder.put(grammarAccess.getWhileStatementAccess().getGroup(), "rule__WhileStatement__Group__0");
			builder.put(grammarAccess.getSwitchStatementAccess().getGroup(), "rule__SwitchStatement__Group__0");
			builder.put(grammarAccess.getSwitchStatementAccess().getGroup_3_0(), "rule__SwitchStatement__Group_3_0__0");
			builder.put(grammarAccess.getSwitchStatementAccess().getGroup_3_0_1(), "rule__SwitchStatement__Group_3_0_1__0");
			builder.put(grammarAccess.getSwitchStatementAccess().getGroup_3_1(), "rule__SwitchStatement__Group_3_1__0");
			builder.put(grammarAccess.getSwitchStatementCaseAccess().getGroup(), "rule__SwitchStatementCase__Group__0");
			builder.put(grammarAccess.getParStatementAccess().getGroup(), "rule__ParStatement__Group__0");
			builder.put(grammarAccess.getParStatementAccess().getGroup_2(), "rule__ParStatement__Group_2__0");
			builder.put(grammarAccess.getSelStatementAccess().getGroup(), "rule__SelStatement__Group__0");
			builder.put(grammarAccess.getSelStatementAccess().getGroup_2(), "rule__SelStatement__Group_2__0");
			builder.put(grammarAccess.getAbortStatementAccess().getGroup(), "rule__AbortStatement__Group__0");
			builder.put(grammarAccess.getInterruptStatementAccess().getGroup(), "rule__InterruptStatement__Group__0");
			builder.put(grammarAccess.getProcessMethodCallAccess().getGroup(), "rule__ProcessMethodCall__Group__0");
			builder.put(grammarAccess.getProcessMethodCallAccess().getGroup_2(), "rule__ProcessMethodCall__Group_2__0");
			builder.put(grammarAccess.getProcessMethodCallAccess().getGroup_2_1(), "rule__ProcessMethodCall__Group_2_1__0");
			builder.put(grammarAccess.getProcessMethodCallAccess().getGroup_5(), "rule__ProcessMethodCall__Group_5__0");
			builder.put(grammarAccess.getProcessMethodCallAccess().getGroup_5_1(), "rule__ProcessMethodCall__Group_5_1__0");
			builder.put(grammarAccess.getStatementSequenceRoundBracketAccess().getGroup(), "rule__StatementSequenceRoundBracket__Group__0");
			builder.put(grammarAccess.getStatementSequenceRoundBracketAccess().getGroup_2(), "rule__StatementSequenceRoundBracket__Group_2__0");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getGroup_0(), "rule__BracketedArgumentStartExpressionLevel2__Group_0__0");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getGroup_0_1(), "rule__BracketedArgumentStartExpressionLevel2__Group_0_1__0");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getGroup_1(), "rule__BracketedArgumentStartExpressionLevel2__Group_1__0");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getGroup_1_1(), "rule__BracketedArgumentStartExpressionLevel2__Group_1_1__0");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getGroup_0(), "rule__BracketedArgumentStartExpressionLevel3__Group_0__0");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getGroup_0_1(), "rule__BracketedArgumentStartExpressionLevel3__Group_0_1__0");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getGroup_1(), "rule__BracketedArgumentStartExpressionLevel3__Group_1__0");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getGroup_1_1(), "rule__BracketedArgumentStartExpressionLevel3__Group_1_1__0");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getGroup_0(), "rule__BracketedArgumentStartExpressionLevel4__Group_0__0");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getGroup_0_1(), "rule__BracketedArgumentStartExpressionLevel4__Group_0_1__0");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getGroup_1(), "rule__BracketedArgumentStartExpressionLevel4__Group_1__0");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getGroup_1_1(), "rule__BracketedArgumentStartExpressionLevel4__Group_1_1__0");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getGroup(), "rule__BracketedArgumentStartExpressionLevel5__Group__0");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getGroup_1(), "rule__BracketedArgumentStartExpressionLevel5__Group_1__0");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getGroup_1_3(), "rule__BracketedArgumentStartExpressionLevel5__Group_1_3__0");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getGroup_1_3_1(), "rule__BracketedArgumentStartExpressionLevel5__Group_1_3_1__0");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getGroup_1_3_1_1(), "rule__BracketedArgumentStartExpressionLevel5__Group_1_3_1_1__0");
			builder.put(grammarAccess.getINTEGERAccess().getGroup(), "rule__INTEGER__Group__0");
			builder.put(grammarAccess.getREALAccess().getGroup(), "rule__REAL__Group__0");
			builder.put(grammarAccess.getFLOATAccess().getGroup(), "rule__FLOAT__Group__0");
			builder.put(grammarAccess.getPooslAccess().getImportsAssignment_1_0(), "rule__Poosl__ImportsAssignment_1_0");
			builder.put(grammarAccess.getPooslAccess().getImportLibsAssignment_1_1(), "rule__Poosl__ImportLibsAssignment_1_1");
			builder.put(grammarAccess.getPooslAccess().getDataClassesAssignment_2_0(), "rule__Poosl__DataClassesAssignment_2_0");
			builder.put(grammarAccess.getPooslAccess().getProcessClassesAssignment_2_1(), "rule__Poosl__ProcessClassesAssignment_2_1");
			builder.put(grammarAccess.getPooslAccess().getClusterClassesAssignment_2_2(), "rule__Poosl__ClusterClassesAssignment_2_2");
			builder.put(grammarAccess.getPooslAccess().getClusterClassesAssignment_3_0(), "rule__Poosl__ClusterClassesAssignment_3_0");
			builder.put(grammarAccess.getPooslAccess().getDataClassesAssignment_3_1_0(), "rule__Poosl__DataClassesAssignment_3_1_0");
			builder.put(grammarAccess.getPooslAccess().getProcessClassesAssignment_3_1_1(), "rule__Poosl__ProcessClassesAssignment_3_1_1");
			builder.put(grammarAccess.getPooslAccess().getClusterClassesAssignment_3_1_2(), "rule__Poosl__ClusterClassesAssignment_3_1_2");
			builder.put(grammarAccess.getImportAccess().getImportURIAssignment_1(), "rule__Import__ImportURIAssignment_1");
			builder.put(grammarAccess.getImportLibAccess().getImportURIAssignment_1(), "rule__ImportLib__ImportURIAssignment_1");
			builder.put(grammarAccess.getDataClassAccess().getAnnotationsAssignment_0(), "rule__DataClass__AnnotationsAssignment_0");
			builder.put(grammarAccess.getDataClassAccess().getNativeAssignment_1(), "rule__DataClass__NativeAssignment_1");
			builder.put(grammarAccess.getDataClassAccess().getNameAssignment_4(), "rule__DataClass__NameAssignment_4");
			builder.put(grammarAccess.getDataClassAccess().getSuperClassAssignment_5_1(), "rule__DataClass__SuperClassAssignment_5_1");
			builder.put(grammarAccess.getDataClassAccess().getInstanceVariablesAssignment_7_0(), "rule__DataClass__InstanceVariablesAssignment_7_0");
			builder.put(grammarAccess.getDataClassAccess().getInstanceVariablesAssignment_7_1_1(), "rule__DataClass__InstanceVariablesAssignment_7_1_1");
			builder.put(grammarAccess.getDataClassAccess().getDataMethodsNamedAssignment_9_0(), "rule__DataClass__DataMethodsNamedAssignment_9_0");
			builder.put(grammarAccess.getDataClassAccess().getDataMethodsUnaryOperatorAssignment_9_1(), "rule__DataClass__DataMethodsUnaryOperatorAssignment_9_1");
			builder.put(grammarAccess.getDataClassAccess().getDataMethodsBinaryOperatorAssignment_9_2(), "rule__DataClass__DataMethodsBinaryOperatorAssignment_9_2");
			builder.put(grammarAccess.getDataMethodNamedAccess().getAnnotationsAssignment_0(), "rule__DataMethodNamed__AnnotationsAssignment_0");
			builder.put(grammarAccess.getDataMethodNamedAccess().getNativeAssignment_1(), "rule__DataMethodNamed__NativeAssignment_1");
			builder.put(grammarAccess.getDataMethodNamedAccess().getNameAssignment_2(), "rule__DataMethodNamed__NameAssignment_2");
			builder.put(grammarAccess.getDataMethodNamedAccess().getParametersAssignment_3_1_0(), "rule__DataMethodNamed__ParametersAssignment_3_1_0");
			builder.put(grammarAccess.getDataMethodNamedAccess().getParametersAssignment_3_1_1_1(), "rule__DataMethodNamed__ParametersAssignment_3_1_1_1");
			builder.put(grammarAccess.getDataMethodNamedAccess().getReturnTypeAssignment_5(), "rule__DataMethodNamed__ReturnTypeAssignment_5");
			builder.put(grammarAccess.getDataMethodNamedAccess().getLocalVariablesAssignment_6_1_0(), "rule__DataMethodNamed__LocalVariablesAssignment_6_1_0");
			builder.put(grammarAccess.getDataMethodNamedAccess().getLocalVariablesAssignment_6_1_1_1(), "rule__DataMethodNamed__LocalVariablesAssignment_6_1_1_1");
			builder.put(grammarAccess.getDataMethodNamedAccess().getBodyAssignment_7(), "rule__DataMethodNamed__BodyAssignment_7");
			builder.put(grammarAccess.getDataMethodBinaryOperatorAccess().getAnnotationsAssignment_0(), "rule__DataMethodBinaryOperator__AnnotationsAssignment_0");
			builder.put(grammarAccess.getDataMethodBinaryOperatorAccess().getNativeAssignment_1(), "rule__DataMethodBinaryOperator__NativeAssignment_1");
			builder.put(grammarAccess.getDataMethodBinaryOperatorAccess().getNameAssignment_2(), "rule__DataMethodBinaryOperator__NameAssignment_2");
			builder.put(grammarAccess.getDataMethodBinaryOperatorAccess().getParametersAssignment_4(), "rule__DataMethodBinaryOperator__ParametersAssignment_4");
			builder.put(grammarAccess.getDataMethodBinaryOperatorAccess().getParametersAssignment_5_1(), "rule__DataMethodBinaryOperator__ParametersAssignment_5_1");
			builder.put(grammarAccess.getDataMethodBinaryOperatorAccess().getReturnTypeAssignment_8(), "rule__DataMethodBinaryOperator__ReturnTypeAssignment_8");
			builder.put(grammarAccess.getDataMethodBinaryOperatorAccess().getLocalVariablesAssignment_9_1_0(), "rule__DataMethodBinaryOperator__LocalVariablesAssignment_9_1_0");
			builder.put(grammarAccess.getDataMethodBinaryOperatorAccess().getLocalVariablesAssignment_9_1_1_1(), "rule__DataMethodBinaryOperator__LocalVariablesAssignment_9_1_1_1");
			builder.put(grammarAccess.getDataMethodBinaryOperatorAccess().getBodyAssignment_10(), "rule__DataMethodBinaryOperator__BodyAssignment_10");
			builder.put(grammarAccess.getDataMethodUnaryOperatorAccess().getAnnotationsAssignment_0(), "rule__DataMethodUnaryOperator__AnnotationsAssignment_0");
			builder.put(grammarAccess.getDataMethodUnaryOperatorAccess().getNativeAssignment_1(), "rule__DataMethodUnaryOperator__NativeAssignment_1");
			builder.put(grammarAccess.getDataMethodUnaryOperatorAccess().getNameAssignment_2(), "rule__DataMethodUnaryOperator__NameAssignment_2");
			builder.put(grammarAccess.getDataMethodUnaryOperatorAccess().getReturnTypeAssignment_5(), "rule__DataMethodUnaryOperator__ReturnTypeAssignment_5");
			builder.put(grammarAccess.getDataMethodUnaryOperatorAccess().getLocalVariablesAssignment_6_1_0(), "rule__DataMethodUnaryOperator__LocalVariablesAssignment_6_1_0");
			builder.put(grammarAccess.getDataMethodUnaryOperatorAccess().getLocalVariablesAssignment_6_1_1_1(), "rule__DataMethodUnaryOperator__LocalVariablesAssignment_6_1_1_1");
			builder.put(grammarAccess.getDataMethodUnaryOperatorAccess().getBodyAssignment_7(), "rule__DataMethodUnaryOperator__BodyAssignment_7");
			builder.put(grammarAccess.getDeclarationAccess().getVariablesAssignment_0_0(), "rule__Declaration__VariablesAssignment_0_0");
			builder.put(grammarAccess.getDeclarationAccess().getVariablesAssignment_0_1_1(), "rule__Declaration__VariablesAssignment_0_1_1");
			builder.put(grammarAccess.getDeclarationAccess().getTypeAssignment_2(), "rule__Declaration__TypeAssignment_2");
			builder.put(grammarAccess.getVariableAccess().getNameAssignment(), "rule__Variable__NameAssignment");
			builder.put(grammarAccess.getAnnotationAccess().getNameAssignment_1(), "rule__Annotation__NameAssignment_1");
			builder.put(grammarAccess.getAnnotationAccess().getArgumentsAssignment_2_1_0(), "rule__Annotation__ArgumentsAssignment_2_1_0");
			builder.put(grammarAccess.getAnnotationAccess().getArgumentsAssignment_2_1_1_1(), "rule__Annotation__ArgumentsAssignment_2_1_1_1");
			builder.put(grammarAccess.getProcessClassAccess().getAnnotationsAssignment_0(), "rule__ProcessClass__AnnotationsAssignment_0");
			builder.put(grammarAccess.getProcessClassAccess().getNameAssignment_3(), "rule__ProcessClass__NameAssignment_3");
			builder.put(grammarAccess.getProcessClassAccess().getParametersAssignment_4_1_0(), "rule__ProcessClass__ParametersAssignment_4_1_0");
			builder.put(grammarAccess.getProcessClassAccess().getParametersAssignment_4_1_1_1(), "rule__ProcessClass__ParametersAssignment_4_1_1_1");
			builder.put(grammarAccess.getProcessClassAccess().getSuperClassAssignment_5_1(), "rule__ProcessClass__SuperClassAssignment_5_1");
			builder.put(grammarAccess.getProcessClassAccess().getPortsAssignment_7_0(), "rule__ProcessClass__PortsAssignment_7_0");
			builder.put(grammarAccess.getProcessClassAccess().getPortsAssignment_7_1_1(), "rule__ProcessClass__PortsAssignment_7_1_1");
			builder.put(grammarAccess.getProcessClassAccess().getReceiveMessagesAssignment_9_0_0(), "rule__ProcessClass__ReceiveMessagesAssignment_9_0_0");
			builder.put(grammarAccess.getProcessClassAccess().getSendMessagesAssignment_9_0_1(), "rule__ProcessClass__SendMessagesAssignment_9_0_1");
			builder.put(grammarAccess.getProcessClassAccess().getReceiveMessagesAssignment_9_1_1_0(), "rule__ProcessClass__ReceiveMessagesAssignment_9_1_1_0");
			builder.put(grammarAccess.getProcessClassAccess().getSendMessagesAssignment_9_1_1_1(), "rule__ProcessClass__SendMessagesAssignment_9_1_1_1");
			builder.put(grammarAccess.getProcessClassAccess().getInstanceVariablesAssignment_11_0(), "rule__ProcessClass__InstanceVariablesAssignment_11_0");
			builder.put(grammarAccess.getProcessClassAccess().getInstanceVariablesAssignment_11_1_1(), "rule__ProcessClass__InstanceVariablesAssignment_11_1_1");
			builder.put(grammarAccess.getProcessClassAccess().getInitialMethodCallAssignment_13(), "rule__ProcessClass__InitialMethodCallAssignment_13");
			builder.put(grammarAccess.getProcessClassAccess().getMethodsAssignment_15(), "rule__ProcessClass__MethodsAssignment_15");
			builder.put(grammarAccess.getProcessMethodAccess().getAnnotationsAssignment_0(), "rule__ProcessMethod__AnnotationsAssignment_0");
			builder.put(grammarAccess.getProcessMethodAccess().getNameAssignment_1(), "rule__ProcessMethod__NameAssignment_1");
			builder.put(grammarAccess.getProcessMethodAccess().getInputParametersAssignment_3_0(), "rule__ProcessMethod__InputParametersAssignment_3_0");
			builder.put(grammarAccess.getProcessMethodAccess().getInputParametersAssignment_3_1_1(), "rule__ProcessMethod__InputParametersAssignment_3_1_1");
			builder.put(grammarAccess.getProcessMethodAccess().getOutputParametersAssignment_6_0(), "rule__ProcessMethod__OutputParametersAssignment_6_0");
			builder.put(grammarAccess.getProcessMethodAccess().getOutputParametersAssignment_6_1_1(), "rule__ProcessMethod__OutputParametersAssignment_6_1_1");
			builder.put(grammarAccess.getProcessMethodAccess().getLocalVariablesAssignment_8_1_0(), "rule__ProcessMethod__LocalVariablesAssignment_8_1_0");
			builder.put(grammarAccess.getProcessMethodAccess().getLocalVariablesAssignment_8_1_1_1(), "rule__ProcessMethod__LocalVariablesAssignment_8_1_1_1");
			builder.put(grammarAccess.getProcessMethodAccess().getBodyAssignment_9(), "rule__ProcessMethod__BodyAssignment_9");
			builder.put(grammarAccess.getPortAccess().getNameAssignment(), "rule__Port__NameAssignment");
			builder.put(grammarAccess.getMessageReceiveSignatureAccess().getPortAssignment_0(), "rule__MessageReceiveSignature__PortAssignment_0");
			builder.put(grammarAccess.getMessageReceiveSignatureAccess().getNameAssignment_2(), "rule__MessageReceiveSignature__NameAssignment_2");
			builder.put(grammarAccess.getMessageReceiveSignatureAccess().getParametersAssignment_3_1_0(), "rule__MessageReceiveSignature__ParametersAssignment_3_1_0");
			builder.put(grammarAccess.getMessageReceiveSignatureAccess().getParametersAssignment_3_1_1_1(), "rule__MessageReceiveSignature__ParametersAssignment_3_1_1_1");
			builder.put(grammarAccess.getMessageSendSignatureAccess().getPortAssignment_0(), "rule__MessageSendSignature__PortAssignment_0");
			builder.put(grammarAccess.getMessageSendSignatureAccess().getNameAssignment_2(), "rule__MessageSendSignature__NameAssignment_2");
			builder.put(grammarAccess.getMessageSendSignatureAccess().getParametersAssignment_3_1_0(), "rule__MessageSendSignature__ParametersAssignment_3_1_0");
			builder.put(grammarAccess.getMessageSendSignatureAccess().getParametersAssignment_3_1_1_1(), "rule__MessageSendSignature__ParametersAssignment_3_1_1_1");
			builder.put(grammarAccess.getMessageParameterAccess().getTypeAssignment(), "rule__MessageParameter__TypeAssignment");
			builder.put(grammarAccess.getSystemAccess().getAnnotationsAssignment_1(), "rule__System__AnnotationsAssignment_1");
			builder.put(grammarAccess.getSystemAccess().getParametersAssignment_3_1_0(), "rule__System__ParametersAssignment_3_1_0");
			builder.put(grammarAccess.getSystemAccess().getParametersAssignment_3_1_1_1(), "rule__System__ParametersAssignment_3_1_1_1");
			builder.put(grammarAccess.getSystemAccess().getPortsAssignment_4_1_0(), "rule__System__PortsAssignment_4_1_0");
			builder.put(grammarAccess.getSystemAccess().getPortsAssignment_4_1_1_1(), "rule__System__PortsAssignment_4_1_1_1");
			builder.put(grammarAccess.getSystemAccess().getInstancesAssignment_6(), "rule__System__InstancesAssignment_6");
			builder.put(grammarAccess.getSystemAccess().getChannelsAssignment_8(), "rule__System__ChannelsAssignment_8");
			builder.put(grammarAccess.getClusterClassAccess().getAnnotationsAssignment_0(), "rule__ClusterClass__AnnotationsAssignment_0");
			builder.put(grammarAccess.getClusterClassAccess().getNameAssignment_3(), "rule__ClusterClass__NameAssignment_3");
			builder.put(grammarAccess.getClusterClassAccess().getParametersAssignment_4_1_0(), "rule__ClusterClass__ParametersAssignment_4_1_0");
			builder.put(grammarAccess.getClusterClassAccess().getParametersAssignment_4_1_1_1(), "rule__ClusterClass__ParametersAssignment_4_1_1_1");
			builder.put(grammarAccess.getClusterClassAccess().getPortsAssignment_6_0(), "rule__ClusterClass__PortsAssignment_6_0");
			builder.put(grammarAccess.getClusterClassAccess().getPortsAssignment_6_1_1(), "rule__ClusterClass__PortsAssignment_6_1_1");
			builder.put(grammarAccess.getClusterClassAccess().getInstancesAssignment_8(), "rule__ClusterClass__InstancesAssignment_8");
			builder.put(grammarAccess.getClusterClassAccess().getChannelsAssignment_10(), "rule__ClusterClass__ChannelsAssignment_10");
			builder.put(grammarAccess.getInstanceAccess().getAnnotationsAssignment_0(), "rule__Instance__AnnotationsAssignment_0");
			builder.put(grammarAccess.getInstanceAccess().getNameAssignment_1(), "rule__Instance__NameAssignment_1");
			builder.put(grammarAccess.getInstanceAccess().getClassDefinitionAssignment_3(), "rule__Instance__ClassDefinitionAssignment_3");
			builder.put(grammarAccess.getInstanceAccess().getInstanceParametersAssignment_4_1_0(), "rule__Instance__InstanceParametersAssignment_4_1_0");
			builder.put(grammarAccess.getInstanceAccess().getInstanceParametersAssignment_4_1_1_1(), "rule__Instance__InstanceParametersAssignment_4_1_1_1");
			builder.put(grammarAccess.getInstanceParameterAccess().getParameterAssignment_0(), "rule__InstanceParameter__ParameterAssignment_0");
			builder.put(grammarAccess.getInstanceParameterAccess().getExpressionAssignment_2(), "rule__InstanceParameter__ExpressionAssignment_2");
			builder.put(grammarAccess.getChannelAccess().getAnnotationsAssignment_1(), "rule__Channel__AnnotationsAssignment_1");
			builder.put(grammarAccess.getChannelAccess().getInstancePortsAssignment_3_0_0(), "rule__Channel__InstancePortsAssignment_3_0_0");
			builder.put(grammarAccess.getChannelAccess().getInstancePortsAssignment_3_0_1_1(), "rule__Channel__InstancePortsAssignment_3_0_1_1");
			builder.put(grammarAccess.getChannelAccess().getExternalPortAssignment_3_0_2_1(), "rule__Channel__ExternalPortAssignment_3_0_2_1");
			builder.put(grammarAccess.getChannelAccess().getInstancePortsAssignment_3_0_2_2_1(), "rule__Channel__InstancePortsAssignment_3_0_2_2_1");
			builder.put(grammarAccess.getChannelAccess().getExternalPortAssignment_3_1_0(), "rule__Channel__ExternalPortAssignment_3_1_0");
			builder.put(grammarAccess.getChannelAccess().getInstancePortsAssignment_3_1_1_1(), "rule__Channel__InstancePortsAssignment_3_1_1_1");
			builder.put(grammarAccess.getInstancePortAccess().getInstanceAssignment_0(), "rule__InstancePort__InstanceAssignment_0");
			builder.put(grammarAccess.getInstancePortAccess().getPortAssignment_2(), "rule__InstancePort__PortAssignment_2");
			builder.put(grammarAccess.getExpressionAccess().getExpressionsAssignment_1_1_1(), "rule__Expression__ExpressionsAssignment_1_1_1");
			builder.put(grammarAccess.getExpressionLevel1Access().getVariableAssignment_1_1(), "rule__ExpressionLevel1__VariableAssignment_1_1");
			builder.put(grammarAccess.getExpressionLevel1Access().getExpressionAssignment_1_3(), "rule__ExpressionLevel1__ExpressionAssignment_1_3");
			builder.put(grammarAccess.getExpressionLevel1Access().getExpressionAssignment_2_2(), "rule__ExpressionLevel1__ExpressionAssignment_2_2");
			builder.put(grammarAccess.getExpressionLevel2Access().getNameAssignment_1_1(), "rule__ExpressionLevel2__NameAssignment_1_1");
			builder.put(grammarAccess.getExpressionLevel2Access().getRightOperandAssignment_1_2(), "rule__ExpressionLevel2__RightOperandAssignment_1_2");
			builder.put(grammarAccess.getExpressionLevel3Access().getNameAssignment_1_1(), "rule__ExpressionLevel3__NameAssignment_1_1");
			builder.put(grammarAccess.getExpressionLevel3Access().getRightOperandAssignment_1_2(), "rule__ExpressionLevel3__RightOperandAssignment_1_2");
			builder.put(grammarAccess.getExpressionLevel4Access().getNameAssignment_1_1(), "rule__ExpressionLevel4__NameAssignment_1_1");
			builder.put(grammarAccess.getExpressionLevel4Access().getRightOperandAssignment_1_2(), "rule__ExpressionLevel4__RightOperandAssignment_1_2");
			builder.put(grammarAccess.getExpressionLevel5Access().getOnSuperClassAssignment_1_1(), "rule__ExpressionLevel5__OnSuperClassAssignment_1_1");
			builder.put(grammarAccess.getExpressionLevel5Access().getNameAssignment_1_2(), "rule__ExpressionLevel5__NameAssignment_1_2");
			builder.put(grammarAccess.getExpressionLevel5Access().getArgumentsAssignment_1_3_1_0(), "rule__ExpressionLevel5__ArgumentsAssignment_1_3_1_0");
			builder.put(grammarAccess.getExpressionLevel5Access().getArgumentsAssignment_1_3_1_1_1(), "rule__ExpressionLevel5__ArgumentsAssignment_1_3_1_1_1");
			builder.put(grammarAccess.getUnaryOperatorExpressionAccess().getNameAssignment_0(), "rule__UnaryOperatorExpression__NameAssignment_0");
			builder.put(grammarAccess.getUnaryOperatorExpressionAccess().getOperandAssignment_1(), "rule__UnaryOperatorExpression__OperandAssignment_1");
			builder.put(grammarAccess.getIfExpressionAccess().getConditionAssignment_1(), "rule__IfExpression__ConditionAssignment_1");
			builder.put(grammarAccess.getIfExpressionAccess().getThenClauseAssignment_3(), "rule__IfExpression__ThenClauseAssignment_3");
			builder.put(grammarAccess.getIfExpressionAccess().getElseClauseAssignment_4_1(), "rule__IfExpression__ElseClauseAssignment_4_1");
			builder.put(grammarAccess.getWhileExpressionAccess().getConditionAssignment_1(), "rule__WhileExpression__ConditionAssignment_1");
			builder.put(grammarAccess.getWhileExpressionAccess().getBodyAssignment_3(), "rule__WhileExpression__BodyAssignment_3");
			builder.put(grammarAccess.getSwitchExpressionAccess().getExpressionAssignment_1(), "rule__SwitchExpression__ExpressionAssignment_1");
			builder.put(grammarAccess.getSwitchExpressionAccess().getCasesAssignment_3_0_0(), "rule__SwitchExpression__CasesAssignment_3_0_0");
			builder.put(grammarAccess.getSwitchExpressionAccess().getDefaultBodyAssignment_3_0_1_1(), "rule__SwitchExpression__DefaultBodyAssignment_3_0_1_1");
			builder.put(grammarAccess.getSwitchExpressionAccess().getDefaultBodyAssignment_3_1_1(), "rule__SwitchExpression__DefaultBodyAssignment_3_1_1");
			builder.put(grammarAccess.getSwitchExpressionCaseAccess().getValueAssignment_1(), "rule__SwitchExpressionCase__ValueAssignment_1");
			builder.put(grammarAccess.getSwitchExpressionCaseAccess().getBodyAssignment_3(), "rule__SwitchExpressionCase__BodyAssignment_3");
			builder.put(grammarAccess.getBooleanConstantAccess().getValueAssignment_0(), "rule__BooleanConstant__ValueAssignment_0");
			builder.put(grammarAccess.getBooleanConstantAccess().getValueAssignment_1(), "rule__BooleanConstant__ValueAssignment_1");
			builder.put(grammarAccess.getCharacterConstantAccess().getValueAssignment(), "rule__CharacterConstant__ValueAssignment");
			builder.put(grammarAccess.getFloatConstantAccess().getValueAssignment(), "rule__FloatConstant__ValueAssignment");
			builder.put(grammarAccess.getIntegerConstantAccess().getValueAssignment(), "rule__IntegerConstant__ValueAssignment");
			builder.put(grammarAccess.getRealConstantAccess().getValueAssignment(), "rule__RealConstant__ValueAssignment");
			builder.put(grammarAccess.getStringConstantAccess().getValueAssignment(), "rule__StringConstant__ValueAssignment");
			builder.put(grammarAccess.getEnvironmentConstantAccess().getValueAssignment(), "rule__EnvironmentConstant__ValueAssignment");
			builder.put(grammarAccess.getOutputVariableAccess().getVariableAssignment(), "rule__OutputVariable__VariableAssignment");
			builder.put(grammarAccess.getVariableExpressionAccess().getVariableAssignment(), "rule__VariableExpression__VariableAssignment");
			builder.put(grammarAccess.getNewExpressionAccess().getDataClassAssignment_2(), "rule__NewExpression__DataClassAssignment_2");
			builder.put(grammarAccess.getExpressionSequenceRoundBracketAccess().getExpressionsAssignment_1(), "rule__ExpressionSequenceRoundBracket__ExpressionsAssignment_1");
			builder.put(grammarAccess.getExpressionSequenceRoundBracketAccess().getExpressionsAssignment_2_1(), "rule__ExpressionSequenceRoundBracket__ExpressionsAssignment_2_1");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getVariableAssignment_1_1(), "rule__IDStartWithinStatementExpressionLevel1__VariableAssignment_1_1");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getExpressionAssignment_1_3(), "rule__IDStartWithinStatementExpressionLevel1__ExpressionAssignment_1_3");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getNameAssignment_1_1(), "rule__IDStartWithinStatementExpressionLevel2__NameAssignment_1_1");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getRightOperandAssignment_1_2(), "rule__IDStartWithinStatementExpressionLevel2__RightOperandAssignment_1_2");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getNameAssignment_1_1(), "rule__IDStartWithinStatementExpressionLevel3__NameAssignment_1_1");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getRightOperandAssignment_1_2(), "rule__IDStartWithinStatementExpressionLevel3__RightOperandAssignment_1_2");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getNameAssignment_1_1(), "rule__IDStartWithinStatementExpressionLevel4__NameAssignment_1_1");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getRightOperandAssignment_1_2(), "rule__IDStartWithinStatementExpressionLevel4__RightOperandAssignment_1_2");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getOnSuperClassAssignment_1_1(), "rule__IDStartWithinStatementExpressionLevel5__OnSuperClassAssignment_1_1");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getNameAssignment_1_2(), "rule__IDStartWithinStatementExpressionLevel5__NameAssignment_1_2");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getArgumentsAssignment_1_3_1_0(), "rule__IDStartWithinStatementExpressionLevel5__ArgumentsAssignment_1_3_1_0");
			builder.put(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getArgumentsAssignment_1_3_1_1_1(), "rule__IDStartWithinStatementExpressionLevel5__ArgumentsAssignment_1_3_1_1_1");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Access().getExpressionAssignment_1_2(), "rule__NonIDStartWithinStatementExpressionLevel1__ExpressionAssignment_1_2");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getNameAssignment_1_1(), "rule__NonIDStartWithinStatementExpressionLevel2__NameAssignment_1_1");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getRightOperandAssignment_1_2(), "rule__NonIDStartWithinStatementExpressionLevel2__RightOperandAssignment_1_2");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getNameAssignment_1_1(), "rule__NonIDStartWithinStatementExpressionLevel3__NameAssignment_1_1");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getRightOperandAssignment_1_2(), "rule__NonIDStartWithinStatementExpressionLevel3__RightOperandAssignment_1_2");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getNameAssignment_1_1(), "rule__NonIDStartWithinStatementExpressionLevel4__NameAssignment_1_1");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getRightOperandAssignment_1_2(), "rule__NonIDStartWithinStatementExpressionLevel4__RightOperandAssignment_1_2");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getOnSuperClassAssignment_1_1(), "rule__NonIDStartWithinStatementExpressionLevel5__OnSuperClassAssignment_1_1");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getNameAssignment_1_2(), "rule__NonIDStartWithinStatementExpressionLevel5__NameAssignment_1_2");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getArgumentsAssignment_1_3_1_0(), "rule__NonIDStartWithinStatementExpressionLevel5__ArgumentsAssignment_1_3_1_0");
			builder.put(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getArgumentsAssignment_1_3_1_1_1(), "rule__NonIDStartWithinStatementExpressionLevel5__ArgumentsAssignment_1_3_1_1_1");
			builder.put(grammarAccess.getStatementAccess().getStatementsAssignment_1_1_1(), "rule__Statement__StatementsAssignment_1_1_1");
			builder.put(grammarAccess.getExpressionStatementAccess().getExpressionAssignment_0(), "rule__ExpressionStatement__ExpressionAssignment_0");
			builder.put(grammarAccess.getExpressionStatementAccess().getExpressionAssignment_1(), "rule__ExpressionStatement__ExpressionAssignment_1");
			builder.put(grammarAccess.getExpressionStatementAccess().getExpressionAssignment_2_1(), "rule__ExpressionStatement__ExpressionAssignment_2_1");
			builder.put(grammarAccess.getDelayStatementAccess().getExpressionAssignment_1(), "rule__DelayStatement__ExpressionAssignment_1");
			builder.put(grammarAccess.getReceiveStatementAccess().getPortDescriptorAssignment_0(), "rule__ReceiveStatement__PortDescriptorAssignment_0");
			builder.put(grammarAccess.getReceiveStatementAccess().getNameAssignment_2(), "rule__ReceiveStatement__NameAssignment_2");
			builder.put(grammarAccess.getReceiveStatementAccess().getVariablesAssignment_3_1_0(), "rule__ReceiveStatement__VariablesAssignment_3_1_0");
			builder.put(grammarAccess.getReceiveStatementAccess().getVariablesAssignment_3_1_1_1(), "rule__ReceiveStatement__VariablesAssignment_3_1_1_1");
			builder.put(grammarAccess.getReceiveStatementAccess().getReceptionConditionAssignment_3_2_1(), "rule__ReceiveStatement__ReceptionConditionAssignment_3_2_1");
			builder.put(grammarAccess.getReceiveStatementAccess().getPostCommunicationExpressionAssignment_4_1(), "rule__ReceiveStatement__PostCommunicationExpressionAssignment_4_1");
			builder.put(grammarAccess.getSendStatementAccess().getPortDescriptorAssignment_0(), "rule__SendStatement__PortDescriptorAssignment_0");
			builder.put(grammarAccess.getSendStatementAccess().getNameAssignment_2(), "rule__SendStatement__NameAssignment_2");
			builder.put(grammarAccess.getSendStatementAccess().getArgumentsAssignment_3_1_0(), "rule__SendStatement__ArgumentsAssignment_3_1_0");
			builder.put(grammarAccess.getSendStatementAccess().getArgumentsAssignment_3_1_1_1(), "rule__SendStatement__ArgumentsAssignment_3_1_1_1");
			builder.put(grammarAccess.getSendStatementAccess().getPostCommunicationExpressionAssignment_4_1(), "rule__SendStatement__PostCommunicationExpressionAssignment_4_1");
			builder.put(grammarAccess.getPortDescriptorAccess().getPortAssignment_1(), "rule__PortDescriptor__PortAssignment_1");
			builder.put(grammarAccess.getPortReferenceAccess().getPortAssignment(), "rule__PortReference__PortAssignment");
			builder.put(grammarAccess.getGuardedStatementAccess().getGuardAssignment_1(), "rule__GuardedStatement__GuardAssignment_1");
			builder.put(grammarAccess.getGuardedStatementAccess().getStatementAssignment_3(), "rule__GuardedStatement__StatementAssignment_3");
			builder.put(grammarAccess.getIfStatementAccess().getConditionAssignment_1(), "rule__IfStatement__ConditionAssignment_1");
			builder.put(grammarAccess.getIfStatementAccess().getThenClauseAssignment_3(), "rule__IfStatement__ThenClauseAssignment_3");
			builder.put(grammarAccess.getIfStatementAccess().getElseClauseAssignment_4_1(), "rule__IfStatement__ElseClauseAssignment_4_1");
			builder.put(grammarAccess.getWhileStatementAccess().getConditionAssignment_1(), "rule__WhileStatement__ConditionAssignment_1");
			builder.put(grammarAccess.getWhileStatementAccess().getBodyAssignment_3(), "rule__WhileStatement__BodyAssignment_3");
			builder.put(grammarAccess.getSwitchStatementAccess().getExpressionAssignment_1(), "rule__SwitchStatement__ExpressionAssignment_1");
			builder.put(grammarAccess.getSwitchStatementAccess().getCasesAssignment_3_0_0(), "rule__SwitchStatement__CasesAssignment_3_0_0");
			builder.put(grammarAccess.getSwitchStatementAccess().getDefaultBodyAssignment_3_0_1_1(), "rule__SwitchStatement__DefaultBodyAssignment_3_0_1_1");
			builder.put(grammarAccess.getSwitchStatementAccess().getDefaultBodyAssignment_3_1_1(), "rule__SwitchStatement__DefaultBodyAssignment_3_1_1");
			builder.put(grammarAccess.getSwitchStatementCaseAccess().getValueAssignment_1(), "rule__SwitchStatementCase__ValueAssignment_1");
			builder.put(grammarAccess.getSwitchStatementCaseAccess().getBodyAssignment_3(), "rule__SwitchStatementCase__BodyAssignment_3");
			builder.put(grammarAccess.getParStatementAccess().getClausesAssignment_1(), "rule__ParStatement__ClausesAssignment_1");
			builder.put(grammarAccess.getParStatementAccess().getClausesAssignment_2_1(), "rule__ParStatement__ClausesAssignment_2_1");
			builder.put(grammarAccess.getSelStatementAccess().getClausesAssignment_1(), "rule__SelStatement__ClausesAssignment_1");
			builder.put(grammarAccess.getSelStatementAccess().getClausesAssignment_2_1(), "rule__SelStatement__ClausesAssignment_2_1");
			builder.put(grammarAccess.getAbortStatementAccess().getNormalClauseAssignment_1(), "rule__AbortStatement__NormalClauseAssignment_1");
			builder.put(grammarAccess.getAbortStatementAccess().getAbortingClauseAssignment_3(), "rule__AbortStatement__AbortingClauseAssignment_3");
			builder.put(grammarAccess.getInterruptStatementAccess().getNormalClauseAssignment_1(), "rule__InterruptStatement__NormalClauseAssignment_1");
			builder.put(grammarAccess.getInterruptStatementAccess().getInterruptingClauseAssignment_3(), "rule__InterruptStatement__InterruptingClauseAssignment_3");
			builder.put(grammarAccess.getProcessMethodCallAccess().getMethodAssignment_0(), "rule__ProcessMethodCall__MethodAssignment_0");
			builder.put(grammarAccess.getProcessMethodCallAccess().getInputArgumentsAssignment_2_0(), "rule__ProcessMethodCall__InputArgumentsAssignment_2_0");
			builder.put(grammarAccess.getProcessMethodCallAccess().getInputArgumentsAssignment_2_1_1(), "rule__ProcessMethodCall__InputArgumentsAssignment_2_1_1");
			builder.put(grammarAccess.getProcessMethodCallAccess().getOutputVariablesAssignment_5_0(), "rule__ProcessMethodCall__OutputVariablesAssignment_5_0");
			builder.put(grammarAccess.getProcessMethodCallAccess().getOutputVariablesAssignment_5_1_1(), "rule__ProcessMethodCall__OutputVariablesAssignment_5_1_1");
			builder.put(grammarAccess.getStatementSequenceRoundBracketAccess().getStatementsAssignment_1(), "rule__StatementSequenceRoundBracket__StatementsAssignment_1");
			builder.put(grammarAccess.getStatementSequenceRoundBracketAccess().getStatementsAssignment_2_1(), "rule__StatementSequenceRoundBracket__StatementsAssignment_2_1");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionStatementAccess().getExpressionAssignment(), "rule__BracketedArgumentStartExpressionStatement__ExpressionAssignment");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getNameAssignment_0_1_1(), "rule__BracketedArgumentStartExpressionLevel2__NameAssignment_0_1_1");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getRightOperandAssignment_0_1_2(), "rule__BracketedArgumentStartExpressionLevel2__RightOperandAssignment_0_1_2");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getNameAssignment_1_1_1(), "rule__BracketedArgumentStartExpressionLevel2__NameAssignment_1_1_1");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getRightOperandAssignment_1_1_2(), "rule__BracketedArgumentStartExpressionLevel2__RightOperandAssignment_1_1_2");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getNameAssignment_0_1_1(), "rule__BracketedArgumentStartExpressionLevel3__NameAssignment_0_1_1");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getRightOperandAssignment_0_1_2(), "rule__BracketedArgumentStartExpressionLevel3__RightOperandAssignment_0_1_2");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getNameAssignment_1_1_1(), "rule__BracketedArgumentStartExpressionLevel3__NameAssignment_1_1_1");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getRightOperandAssignment_1_1_2(), "rule__BracketedArgumentStartExpressionLevel3__RightOperandAssignment_1_1_2");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getNameAssignment_0_1_1(), "rule__BracketedArgumentStartExpressionLevel4__NameAssignment_0_1_1");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getRightOperandAssignment_0_1_2(), "rule__BracketedArgumentStartExpressionLevel4__RightOperandAssignment_0_1_2");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getNameAssignment_1_1_1(), "rule__BracketedArgumentStartExpressionLevel4__NameAssignment_1_1_1");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getRightOperandAssignment_1_1_2(), "rule__BracketedArgumentStartExpressionLevel4__RightOperandAssignment_1_1_2");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getOnSuperClassAssignment_1_1(), "rule__BracketedArgumentStartExpressionLevel5__OnSuperClassAssignment_1_1");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getNameAssignment_1_2(), "rule__BracketedArgumentStartExpressionLevel5__NameAssignment_1_2");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getArgumentsAssignment_1_3_1_0(), "rule__BracketedArgumentStartExpressionLevel5__ArgumentsAssignment_1_3_1_0");
			builder.put(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getArgumentsAssignment_1_3_1_1_1(), "rule__BracketedArgumentStartExpressionLevel5__ArgumentsAssignment_1_3_1_1_1");
		}
	}
	
	@Inject
	private NameMappings nameMappings;

	@Inject
	private PooslGrammarAccess grammarAccess;

	@Override
	protected InternalPooslParser createParser() {
		InternalPooslParser result = new InternalPooslParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}

	@Override
	protected String getRuleName(AbstractElement element) {
		return nameMappings.getRuleName(element);
	}

	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}

	public PooslGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(PooslGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
	public NameMappings getNameMappings() {
		return nameMappings;
	}
	
	public void setNameMappings(NameMappings nameMappings) {
		this.nameMappings = nameMappings;
	}
}
