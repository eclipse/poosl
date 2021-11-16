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
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.poosl.AbortStatement;
import org.eclipse.poosl.Annotation;
import org.eclipse.poosl.AssignmentExpression;
import org.eclipse.poosl.BinaryOperatorExpression;
import org.eclipse.poosl.BooleanConstant;
import org.eclipse.poosl.Channel;
import org.eclipse.poosl.CharacterConstant;
import org.eclipse.poosl.ClusterClass;
import org.eclipse.poosl.CurrentTimeExpression;
import org.eclipse.poosl.DataClass;
import org.eclipse.poosl.DataMethodBinaryOperator;
import org.eclipse.poosl.DataMethodCallExpression;
import org.eclipse.poosl.DataMethodNamed;
import org.eclipse.poosl.DataMethodUnaryOperator;
import org.eclipse.poosl.Declaration;
import org.eclipse.poosl.DelayStatement;
import org.eclipse.poosl.EnvironmentConstant;
import org.eclipse.poosl.ExpressionSequence;
import org.eclipse.poosl.ExpressionSequenceRoundBracket;
import org.eclipse.poosl.ExpressionStatement;
import org.eclipse.poosl.FloatConstant;
import org.eclipse.poosl.GuardedStatement;
import org.eclipse.poosl.IfExpression;
import org.eclipse.poosl.IfStatement;
import org.eclipse.poosl.Import;
import org.eclipse.poosl.Instance;
import org.eclipse.poosl.InstanceParameter;
import org.eclipse.poosl.InstancePort;
import org.eclipse.poosl.IntegerConstant;
import org.eclipse.poosl.InterruptStatement;
import org.eclipse.poosl.MessageParameter;
import org.eclipse.poosl.MessageSignature;
import org.eclipse.poosl.NewExpression;
import org.eclipse.poosl.NilConstant;
import org.eclipse.poosl.OutputVariable;
import org.eclipse.poosl.ParStatement;
import org.eclipse.poosl.Poosl;
import org.eclipse.poosl.PooslPackage;
import org.eclipse.poosl.Port;
import org.eclipse.poosl.PortReference;
import org.eclipse.poosl.ProcessClass;
import org.eclipse.poosl.ProcessMethod;
import org.eclipse.poosl.ProcessMethodCall;
import org.eclipse.poosl.RealConstant;
import org.eclipse.poosl.ReceiveStatement;
import org.eclipse.poosl.ReturnExpression;
import org.eclipse.poosl.SelStatement;
import org.eclipse.poosl.SelfExpression;
import org.eclipse.poosl.SendStatement;
import org.eclipse.poosl.SkipStatement;
import org.eclipse.poosl.StatementSequence;
import org.eclipse.poosl.StatementSequenceRoundBracket;
import org.eclipse.poosl.StringConstant;
import org.eclipse.poosl.SwitchExpression;
import org.eclipse.poosl.SwitchExpressionCase;
import org.eclipse.poosl.SwitchStatement;
import org.eclipse.poosl.SwitchStatementCase;
import org.eclipse.poosl.UnaryOperatorExpression;
import org.eclipse.poosl.Variable;
import org.eclipse.poosl.VariableExpression;
import org.eclipse.poosl.WhileExpression;
import org.eclipse.poosl.WhileStatement;
import org.eclipse.poosl.xtext.services.PooslGrammarAccess;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class PooslSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private PooslGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == PooslPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case PooslPackage.ABORT_STATEMENT:
				sequence_AbortStatement(context, (AbortStatement) semanticObject); 
				return; 
			case PooslPackage.ANNOTATION:
				sequence_Annotation(context, (Annotation) semanticObject); 
				return; 
			case PooslPackage.ASSIGNMENT_EXPRESSION:
				if (rule == grammarAccess.getExpressionRule()
						|| action == grammarAccess.getExpressionAccess().getExpressionSequenceExpressionsAction_1_0()
						|| rule == grammarAccess.getExpressionSingleRule()
						|| rule == grammarAccess.getExpressionLevel1Rule()) {
					sequence_ExpressionLevel1(context, (AssignmentExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getIDStartWithinStatementExpressionSingleRule()
						|| rule == grammarAccess.getIDStartWithinStatementExpressionLevel1Rule()) {
					sequence_IDStartWithinStatementExpressionLevel1(context, (AssignmentExpression) semanticObject); 
					return; 
				}
				else break;
			case PooslPackage.BINARY_OPERATOR_EXPRESSION:
				if (action == grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_1_1_0()) {
					sequence_BracketedArgumentStartExpressionLevel2_BinaryOperatorExpression_1_1_0(context, (BinaryOperatorExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getBracketedArgumentStartExpressionSingleRule()
						|| rule == grammarAccess.getBracketedArgumentStartExpressionLevel1Rule()
						|| rule == grammarAccess.getBracketedArgumentStartExpressionLevel2Rule()) {
					sequence_BracketedArgumentStartExpressionLevel2_BracketedArgumentStartExpressionLevel3_BracketedArgumentStartExpressionLevel4(context, (BinaryOperatorExpression) semanticObject); 
					return; 
				}
				else if (action == grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_0_1_0()) {
					sequence_BracketedArgumentStartExpressionLevel2_BracketedArgumentStartExpressionLevel3_BracketedArgumentStartExpressionLevel4_BinaryOperatorExpression_0_1_0(context, (BinaryOperatorExpression) semanticObject); 
					return; 
				}
				else if (action == grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_1_1_0()) {
					sequence_BracketedArgumentStartExpressionLevel3_BinaryOperatorExpression_1_1_0(context, (BinaryOperatorExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getBracketedArgumentStartExpressionLevel3Rule()) {
					sequence_BracketedArgumentStartExpressionLevel3_BracketedArgumentStartExpressionLevel4(context, (BinaryOperatorExpression) semanticObject); 
					return; 
				}
				else if (action == grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_0_1_0()) {
					sequence_BracketedArgumentStartExpressionLevel3_BracketedArgumentStartExpressionLevel4_BinaryOperatorExpression_0_1_0(context, (BinaryOperatorExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getBracketedArgumentStartExpressionLevel4Rule()) {
					sequence_BracketedArgumentStartExpressionLevel4(context, (BinaryOperatorExpression) semanticObject); 
					return; 
				}
				else if (action == grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_0_1_0()) {
					sequence_BracketedArgumentStartExpressionLevel4_BinaryOperatorExpression_0_1_0(context, (BinaryOperatorExpression) semanticObject); 
					return; 
				}
				else if (action == grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_1_0()) {
					sequence_BracketedArgumentStartExpressionLevel4_BinaryOperatorExpression_1_1_0(context, (BinaryOperatorExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getExpressionRule()
						|| action == grammarAccess.getExpressionAccess().getExpressionSequenceExpressionsAction_1_0()
						|| rule == grammarAccess.getExpressionSingleRule()
						|| rule == grammarAccess.getExpressionLevel1Rule()
						|| rule == grammarAccess.getExpressionLevel2Rule()
						|| action == grammarAccess.getExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_1_0()) {
					sequence_ExpressionLevel2_ExpressionLevel3_ExpressionLevel4(context, (BinaryOperatorExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getExpressionLevel3Rule()
						|| action == grammarAccess.getExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_1_0()) {
					sequence_ExpressionLevel3_ExpressionLevel4(context, (BinaryOperatorExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getExpressionLevel4Rule()
						|| action == grammarAccess.getExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_0()) {
					sequence_ExpressionLevel4(context, (BinaryOperatorExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getIDStartWithinStatementExpressionSingleRule()
						|| rule == grammarAccess.getIDStartWithinStatementExpressionLevel1Rule()
						|| rule == grammarAccess.getIDStartWithinStatementExpressionLevel2Rule()
						|| action == grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_1_0()) {
					sequence_IDStartWithinStatementExpressionLevel2_IDStartWithinStatementExpressionLevel3_IDStartWithinStatementExpressionLevel4(context, (BinaryOperatorExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getIDStartWithinStatementExpressionLevel3Rule()
						|| action == grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_1_0()) {
					sequence_IDStartWithinStatementExpressionLevel3_IDStartWithinStatementExpressionLevel4(context, (BinaryOperatorExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getIDStartWithinStatementExpressionLevel4Rule()
						|| action == grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_0()) {
					sequence_IDStartWithinStatementExpressionLevel4(context, (BinaryOperatorExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getNonIDStartWithinStatementExpressionSingleRule()
						|| rule == grammarAccess.getNonIDStartWithinStatementExpressionLevel1Rule()
						|| rule == grammarAccess.getNonIDStartWithinStatementExpressionLevel2Rule()
						|| action == grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_1_0()) {
					sequence_NonIDStartWithinStatementExpressionLevel2_NonIDStartWithinStatementExpressionLevel3_NonIDStartWithinStatementExpressionLevel4(context, (BinaryOperatorExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getNonIDStartWithinStatementExpressionLevel3Rule()
						|| action == grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_1_0()) {
					sequence_NonIDStartWithinStatementExpressionLevel3_NonIDStartWithinStatementExpressionLevel4(context, (BinaryOperatorExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getNonIDStartWithinStatementExpressionLevel4Rule()
						|| action == grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_0()) {
					sequence_NonIDStartWithinStatementExpressionLevel4(context, (BinaryOperatorExpression) semanticObject); 
					return; 
				}
				else break;
			case PooslPackage.BOOLEAN_CONSTANT:
				sequence_BooleanConstant(context, (BooleanConstant) semanticObject); 
				return; 
			case PooslPackage.CHANNEL:
				sequence_Channel(context, (Channel) semanticObject); 
				return; 
			case PooslPackage.CHARACTER_CONSTANT:
				sequence_CharacterConstant(context, (CharacterConstant) semanticObject); 
				return; 
			case PooslPackage.CLUSTER_CLASS:
				if (rule == grammarAccess.getClusterClassRule()) {
					sequence_ClusterClass(context, (ClusterClass) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getSystemRule()) {
					sequence_System(context, (ClusterClass) semanticObject); 
					return; 
				}
				else break;
			case PooslPackage.CURRENT_TIME_EXPRESSION:
				sequence_CurrentTimeExpression(context, (CurrentTimeExpression) semanticObject); 
				return; 
			case PooslPackage.DATA_CLASS:
				sequence_DataClass(context, (DataClass) semanticObject); 
				return; 
			case PooslPackage.DATA_METHOD_BINARY_OPERATOR:
				sequence_DataMethodBinaryOperator(context, (DataMethodBinaryOperator) semanticObject); 
				return; 
			case PooslPackage.DATA_METHOD_CALL_EXPRESSION:
				if (rule == grammarAccess.getBracketedArgumentStartExpressionSingleRule()
						|| rule == grammarAccess.getBracketedArgumentStartExpressionLevel1Rule()
						|| rule == grammarAccess.getBracketedArgumentStartExpressionLevel2Rule()
						|| action == grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_0_1_0()
						|| rule == grammarAccess.getBracketedArgumentStartExpressionLevel3Rule()
						|| action == grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_0_1_0()
						|| rule == grammarAccess.getBracketedArgumentStartExpressionLevel4Rule()
						|| action == grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_0_1_0()
						|| rule == grammarAccess.getBracketedArgumentStartExpressionLevel5Rule()
						|| action == grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getDataMethodCallExpressionTargetAction_1_0()) {
					sequence_BracketedArgumentStartExpressionLevel5(context, (DataMethodCallExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getExpressionRule()
						|| action == grammarAccess.getExpressionAccess().getExpressionSequenceExpressionsAction_1_0()
						|| rule == grammarAccess.getExpressionSingleRule()
						|| rule == grammarAccess.getExpressionLevel1Rule()
						|| rule == grammarAccess.getExpressionLevel2Rule()
						|| action == grammarAccess.getExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_1_0()
						|| rule == grammarAccess.getExpressionLevel3Rule()
						|| action == grammarAccess.getExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_1_0()
						|| rule == grammarAccess.getExpressionLevel4Rule()
						|| action == grammarAccess.getExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_0()
						|| rule == grammarAccess.getExpressionLevel5Rule()
						|| action == grammarAccess.getExpressionLevel5Access().getDataMethodCallExpressionTargetAction_1_0()) {
					sequence_ExpressionLevel5(context, (DataMethodCallExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getIDStartWithinStatementExpressionSingleRule()
						|| rule == grammarAccess.getIDStartWithinStatementExpressionLevel1Rule()
						|| rule == grammarAccess.getIDStartWithinStatementExpressionLevel2Rule()
						|| action == grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_1_0()
						|| rule == grammarAccess.getIDStartWithinStatementExpressionLevel3Rule()
						|| action == grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_1_0()
						|| rule == grammarAccess.getIDStartWithinStatementExpressionLevel4Rule()
						|| action == grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_0()
						|| rule == grammarAccess.getIDStartWithinStatementExpressionLevel5Rule()
						|| action == grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getDataMethodCallExpressionTargetAction_1_0()) {
					sequence_IDStartWithinStatementExpressionLevel5(context, (DataMethodCallExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getNonIDStartWithinStatementExpressionSingleRule()
						|| rule == grammarAccess.getNonIDStartWithinStatementExpressionLevel1Rule()
						|| rule == grammarAccess.getNonIDStartWithinStatementExpressionLevel2Rule()
						|| action == grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_1_0()
						|| rule == grammarAccess.getNonIDStartWithinStatementExpressionLevel3Rule()
						|| action == grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_1_0()
						|| rule == grammarAccess.getNonIDStartWithinStatementExpressionLevel4Rule()
						|| action == grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_0()
						|| rule == grammarAccess.getNonIDStartWithinStatementExpressionLevel5Rule()
						|| action == grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getDataMethodCallExpressionTargetAction_1_0()) {
					sequence_NonIDStartWithinStatementExpressionLevel5(context, (DataMethodCallExpression) semanticObject); 
					return; 
				}
				else break;
			case PooslPackage.DATA_METHOD_NAMED:
				sequence_DataMethodNamed(context, (DataMethodNamed) semanticObject); 
				return; 
			case PooslPackage.DATA_METHOD_UNARY_OPERATOR:
				sequence_DataMethodUnaryOperator(context, (DataMethodUnaryOperator) semanticObject); 
				return; 
			case PooslPackage.DECLARATION:
				sequence_Declaration(context, (Declaration) semanticObject); 
				return; 
			case PooslPackage.DELAY_STATEMENT:
				sequence_DelayStatement(context, (DelayStatement) semanticObject); 
				return; 
			case PooslPackage.ENVIRONMENT_CONSTANT:
				sequence_EnvironmentConstant(context, (EnvironmentConstant) semanticObject); 
				return; 
			case PooslPackage.EXPRESSION_SEQUENCE:
				sequence_Expression(context, (ExpressionSequence) semanticObject); 
				return; 
			case PooslPackage.EXPRESSION_SEQUENCE_ROUND_BRACKET:
				sequence_ExpressionSequenceRoundBracket(context, (ExpressionSequenceRoundBracket) semanticObject); 
				return; 
			case PooslPackage.EXPRESSION_STATEMENT:
				if (rule == grammarAccess.getBracketStartStatementRule()
						|| rule == grammarAccess.getBracketedArgumentStartExpressionStatementRule()) {
					sequence_BracketedArgumentStartExpressionStatement(context, (ExpressionStatement) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getStatementRule()
						|| action == grammarAccess.getStatementAccess().getStatementSequenceStatementsAction_1_0()
						|| rule == grammarAccess.getStatementSingleRule()) {
					sequence_BracketedArgumentStartExpressionStatement_ExpressionStatement(context, (ExpressionStatement) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getExpressionStatementRule()) {
					sequence_ExpressionStatement(context, (ExpressionStatement) semanticObject); 
					return; 
				}
				else break;
			case PooslPackage.FLOAT_CONSTANT:
				sequence_FloatConstant(context, (FloatConstant) semanticObject); 
				return; 
			case PooslPackage.GUARDED_STATEMENT:
				sequence_GuardedStatement(context, (GuardedStatement) semanticObject); 
				return; 
			case PooslPackage.IF_EXPRESSION:
				sequence_IfExpression(context, (IfExpression) semanticObject); 
				return; 
			case PooslPackage.IF_STATEMENT:
				sequence_IfStatement(context, (IfStatement) semanticObject); 
				return; 
			case PooslPackage.IMPORT:
				if (rule == grammarAccess.getImportLibRule()) {
					sequence_ImportLib(context, (Import) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getImportRule()) {
					sequence_Import(context, (Import) semanticObject); 
					return; 
				}
				else break;
			case PooslPackage.INSTANCE:
				sequence_Instance(context, (Instance) semanticObject); 
				return; 
			case PooslPackage.INSTANCE_PARAMETER:
				sequence_InstanceParameter(context, (InstanceParameter) semanticObject); 
				return; 
			case PooslPackage.INSTANCE_PORT:
				sequence_InstancePort(context, (InstancePort) semanticObject); 
				return; 
			case PooslPackage.INTEGER_CONSTANT:
				sequence_IntegerConstant(context, (IntegerConstant) semanticObject); 
				return; 
			case PooslPackage.INTERRUPT_STATEMENT:
				sequence_InterruptStatement(context, (InterruptStatement) semanticObject); 
				return; 
			case PooslPackage.MESSAGE_PARAMETER:
				sequence_MessageParameter(context, (MessageParameter) semanticObject); 
				return; 
			case PooslPackage.MESSAGE_SIGNATURE:
				if (rule == grammarAccess.getMessageReceiveSignatureRule()) {
					sequence_MessageReceiveSignature(context, (MessageSignature) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getMessageSendSignatureRule()) {
					sequence_MessageSendSignature(context, (MessageSignature) semanticObject); 
					return; 
				}
				else break;
			case PooslPackage.NEW_EXPRESSION:
				sequence_NewExpression(context, (NewExpression) semanticObject); 
				return; 
			case PooslPackage.NIL_CONSTANT:
				sequence_NilConstant(context, (NilConstant) semanticObject); 
				return; 
			case PooslPackage.OUTPUT_VARIABLE:
				sequence_OutputVariable(context, (OutputVariable) semanticObject); 
				return; 
			case PooslPackage.PAR_STATEMENT:
				sequence_ParStatement(context, (ParStatement) semanticObject); 
				return; 
			case PooslPackage.POOSL:
				sequence_Poosl(context, (Poosl) semanticObject); 
				return; 
			case PooslPackage.PORT:
				sequence_Port(context, (Port) semanticObject); 
				return; 
			case PooslPackage.PORT_REFERENCE:
				if (rule == grammarAccess.getPortDescriptorRule()) {
					sequence_PortDescriptor(context, (PortReference) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getPortReferenceRule()) {
					sequence_PortReference(context, (PortReference) semanticObject); 
					return; 
				}
				else break;
			case PooslPackage.PROCESS_CLASS:
				sequence_ProcessClass(context, (ProcessClass) semanticObject); 
				return; 
			case PooslPackage.PROCESS_METHOD:
				sequence_ProcessMethod(context, (ProcessMethod) semanticObject); 
				return; 
			case PooslPackage.PROCESS_METHOD_CALL:
				sequence_ProcessMethodCall(context, (ProcessMethodCall) semanticObject); 
				return; 
			case PooslPackage.REAL_CONSTANT:
				sequence_RealConstant(context, (RealConstant) semanticObject); 
				return; 
			case PooslPackage.RECEIVE_STATEMENT:
				sequence_ReceiveStatement(context, (ReceiveStatement) semanticObject); 
				return; 
			case PooslPackage.RETURN_EXPRESSION:
				if (rule == grammarAccess.getExpressionRule()
						|| action == grammarAccess.getExpressionAccess().getExpressionSequenceExpressionsAction_1_0()
						|| rule == grammarAccess.getExpressionSingleRule()
						|| rule == grammarAccess.getExpressionLevel1Rule()) {
					sequence_ExpressionLevel1(context, (ReturnExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getNonIDStartWithinStatementExpressionSingleRule()
						|| rule == grammarAccess.getNonIDStartWithinStatementExpressionLevel1Rule()) {
					sequence_NonIDStartWithinStatementExpressionLevel1(context, (ReturnExpression) semanticObject); 
					return; 
				}
				else break;
			case PooslPackage.SEL_STATEMENT:
				sequence_SelStatement(context, (SelStatement) semanticObject); 
				return; 
			case PooslPackage.SELF_EXPRESSION:
				sequence_SelfExpression(context, (SelfExpression) semanticObject); 
				return; 
			case PooslPackage.SEND_STATEMENT:
				sequence_SendStatement(context, (SendStatement) semanticObject); 
				return; 
			case PooslPackage.SKIP_STATEMENT:
				sequence_SkipStatement(context, (SkipStatement) semanticObject); 
				return; 
			case PooslPackage.STATEMENT_SEQUENCE:
				sequence_Statement(context, (StatementSequence) semanticObject); 
				return; 
			case PooslPackage.STATEMENT_SEQUENCE_ROUND_BRACKET:
				sequence_StatementSequenceRoundBracket(context, (StatementSequenceRoundBracket) semanticObject); 
				return; 
			case PooslPackage.STRING_CONSTANT:
				sequence_StringConstant(context, (StringConstant) semanticObject); 
				return; 
			case PooslPackage.SWITCH_EXPRESSION:
				sequence_SwitchExpression(context, (SwitchExpression) semanticObject); 
				return; 
			case PooslPackage.SWITCH_EXPRESSION_CASE:
				sequence_SwitchExpressionCase(context, (SwitchExpressionCase) semanticObject); 
				return; 
			case PooslPackage.SWITCH_STATEMENT:
				sequence_SwitchStatement(context, (SwitchStatement) semanticObject); 
				return; 
			case PooslPackage.SWITCH_STATEMENT_CASE:
				sequence_SwitchStatementCase(context, (SwitchStatementCase) semanticObject); 
				return; 
			case PooslPackage.UNARY_OPERATOR_EXPRESSION:
				sequence_UnaryOperatorExpression(context, (UnaryOperatorExpression) semanticObject); 
				return; 
			case PooslPackage.VARIABLE:
				sequence_Variable(context, (Variable) semanticObject); 
				return; 
			case PooslPackage.VARIABLE_EXPRESSION:
				sequence_VariableExpression(context, (VariableExpression) semanticObject); 
				return; 
			case PooslPackage.WHILE_EXPRESSION:
				sequence_WhileExpression(context, (WhileExpression) semanticObject); 
				return; 
			case PooslPackage.WHILE_STATEMENT:
				sequence_WhileStatement(context, (WhileStatement) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Contexts:
	 *     Statement returns AbortStatement
	 *     Statement.StatementSequence_1_0 returns AbortStatement
	 *     StatementSingle returns AbortStatement
	 *     AbortStatement returns AbortStatement
	 *
	 * Constraint:
	 *     (normalClause=Statement abortingClause=StatementSingle)
	 */
	protected void sequence_AbortStatement(ISerializationContext context, AbortStatement semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.ABORT_STATEMENT__NORMAL_CLAUSE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.ABORT_STATEMENT__NORMAL_CLAUSE));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.ABORT_STATEMENT__ABORTING_CLAUSE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.ABORT_STATEMENT__ABORTING_CLAUSE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getAbortStatementAccess().getNormalClauseStatementParserRuleCall_1_0(), semanticObject.getNormalClause());
		feeder.accept(grammarAccess.getAbortStatementAccess().getAbortingClauseStatementSingleParserRuleCall_3_0(), semanticObject.getAbortingClause());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Annotation returns Annotation
	 *
	 * Constraint:
	 *     (name=IDENTIFIER (arguments+=ExpressionConstant arguments+=ExpressionConstant*)?)
	 */
	protected void sequence_Annotation(ISerializationContext context, Annotation semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Expression returns BooleanConstant
	 *     Expression.ExpressionSequence_1_0 returns BooleanConstant
	 *     ExpressionSingle returns BooleanConstant
	 *     ExpressionLevel1 returns BooleanConstant
	 *     ExpressionLevel2 returns BooleanConstant
	 *     ExpressionLevel2.BinaryOperatorExpression_1_0 returns BooleanConstant
	 *     ExpressionLevel3 returns BooleanConstant
	 *     ExpressionLevel3.BinaryOperatorExpression_1_0 returns BooleanConstant
	 *     ExpressionLevel4 returns BooleanConstant
	 *     ExpressionLevel4.BinaryOperatorExpression_1_0 returns BooleanConstant
	 *     ExpressionLevel5 returns BooleanConstant
	 *     ExpressionLevel5.DataMethodCallExpression_1_0 returns BooleanConstant
	 *     ExpressionLevel6 returns BooleanConstant
	 *     ExpressionLevel7 returns BooleanConstant
	 *     ExpressionConstant returns BooleanConstant
	 *     BooleanConstant returns BooleanConstant
	 *     NonIDStartWithinStatementExpressionSingle returns BooleanConstant
	 *     NonIDStartWithinStatementExpressionLevel1 returns BooleanConstant
	 *     NonIDStartWithinStatementExpressionLevel2 returns BooleanConstant
	 *     NonIDStartWithinStatementExpressionLevel2.BinaryOperatorExpression_1_0 returns BooleanConstant
	 *     NonIDStartWithinStatementExpressionLevel3 returns BooleanConstant
	 *     NonIDStartWithinStatementExpressionLevel3.BinaryOperatorExpression_1_0 returns BooleanConstant
	 *     NonIDStartWithinStatementExpressionLevel4 returns BooleanConstant
	 *     NonIDStartWithinStatementExpressionLevel4.BinaryOperatorExpression_1_0 returns BooleanConstant
	 *     NonIDStartWithinStatementExpressionLevel5 returns BooleanConstant
	 *     NonIDStartWithinStatementExpressionLevel5.DataMethodCallExpression_1_0 returns BooleanConstant
	 *     NonIDStartWithinStatementExpressionLevel6 returns BooleanConstant
	 *     NonIDStartWithinStatementExpressionLevel7 returns BooleanConstant
	 *
	 * Constraint:
	 *     (value='true' | value='false')
	 */
	protected void sequence_BooleanConstant(ISerializationContext context, BooleanConstant semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     BracketedArgumentStartExpressionLevel2.BinaryOperatorExpression_1_1_0 returns BinaryOperatorExpression
	 *
	 * Constraint:
	 *     (leftOperand=BracketedArgumentStartExpressionLevel2_BinaryOperatorExpression_1_1_0 name=BinaryOperatorLevel2 rightOperand=ExpressionLevel3)
	 */
	protected void sequence_BracketedArgumentStartExpressionLevel2_BinaryOperatorExpression_1_1_0(ISerializationContext context, BinaryOperatorExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__LEFT_OPERAND) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__LEFT_OPERAND));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__NAME));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__RIGHT_OPERAND) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__RIGHT_OPERAND));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_1_1_0(), semanticObject.getLeftOperand());
		feeder.accept(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getNameBinaryOperatorLevel2EnumRuleCall_1_1_1_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getRightOperandExpressionLevel3ParserRuleCall_1_1_2_0(), semanticObject.getRightOperand());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     BracketedArgumentStartExpressionSingle returns BinaryOperatorExpression
	 *     BracketedArgumentStartExpressionLevel1 returns BinaryOperatorExpression
	 *     BracketedArgumentStartExpressionLevel2 returns BinaryOperatorExpression
	 *
	 * Constraint:
	 *     (
	 *         (leftOperand=BracketedArgumentStartExpressionLevel2_BinaryOperatorExpression_0_1_0 name=BinaryOperatorLevel2 rightOperand=ExpressionLevel3) | 
	 *         (leftOperand=BracketedArgumentStartExpressionLevel2_BinaryOperatorExpression_1_1_0 name=BinaryOperatorLevel2 rightOperand=ExpressionLevel3) | 
	 *         (leftOperand=BracketedArgumentStartExpressionLevel3_BinaryOperatorExpression_0_1_0 name=BinaryOperatorLevel3 rightOperand=ExpressionLevel4) | 
	 *         (leftOperand=BracketedArgumentStartExpressionLevel3_BinaryOperatorExpression_1_1_0 name=BinaryOperatorLevel3 rightOperand=ExpressionLevel4) | 
	 *         (leftOperand=BracketedArgumentStartExpressionLevel4_BinaryOperatorExpression_0_1_0 name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5) | 
	 *         (leftOperand=BracketedArgumentStartExpressionLevel4_BinaryOperatorExpression_1_1_0 name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5)
	 *     )
	 */
	protected void sequence_BracketedArgumentStartExpressionLevel2_BracketedArgumentStartExpressionLevel3_BracketedArgumentStartExpressionLevel4(ISerializationContext context, BinaryOperatorExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     BracketedArgumentStartExpressionLevel2.BinaryOperatorExpression_0_1_0 returns BinaryOperatorExpression
	 *
	 * Constraint:
	 *     (
	 *         (leftOperand=BracketedArgumentStartExpressionLevel2_BinaryOperatorExpression_0_1_0 name=BinaryOperatorLevel2 rightOperand=ExpressionLevel3) | 
	 *         (leftOperand=BracketedArgumentStartExpressionLevel3_BinaryOperatorExpression_0_1_0 name=BinaryOperatorLevel3 rightOperand=ExpressionLevel4) | 
	 *         (leftOperand=BracketedArgumentStartExpressionLevel3_BinaryOperatorExpression_1_1_0 name=BinaryOperatorLevel3 rightOperand=ExpressionLevel4) | 
	 *         (leftOperand=BracketedArgumentStartExpressionLevel4_BinaryOperatorExpression_0_1_0 name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5) | 
	 *         (leftOperand=BracketedArgumentStartExpressionLevel4_BinaryOperatorExpression_1_1_0 name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5)
	 *     )
	 */
	protected void sequence_BracketedArgumentStartExpressionLevel2_BracketedArgumentStartExpressionLevel3_BracketedArgumentStartExpressionLevel4_BinaryOperatorExpression_0_1_0(ISerializationContext context, BinaryOperatorExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     BracketedArgumentStartExpressionLevel3.BinaryOperatorExpression_1_1_0 returns BinaryOperatorExpression
	 *
	 * Constraint:
	 *     (leftOperand=BracketedArgumentStartExpressionLevel3_BinaryOperatorExpression_1_1_0 name=BinaryOperatorLevel3 rightOperand=ExpressionLevel4)
	 */
	protected void sequence_BracketedArgumentStartExpressionLevel3_BinaryOperatorExpression_1_1_0(ISerializationContext context, BinaryOperatorExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__LEFT_OPERAND) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__LEFT_OPERAND));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__NAME));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__RIGHT_OPERAND) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__RIGHT_OPERAND));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_1_1_0(), semanticObject.getLeftOperand());
		feeder.accept(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getNameBinaryOperatorLevel3EnumRuleCall_1_1_1_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getRightOperandExpressionLevel4ParserRuleCall_1_1_2_0(), semanticObject.getRightOperand());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     BracketedArgumentStartExpressionLevel3 returns BinaryOperatorExpression
	 *
	 * Constraint:
	 *     (
	 *         (leftOperand=BracketedArgumentStartExpressionLevel3_BinaryOperatorExpression_0_1_0 name=BinaryOperatorLevel3 rightOperand=ExpressionLevel4) | 
	 *         (leftOperand=BracketedArgumentStartExpressionLevel3_BinaryOperatorExpression_1_1_0 name=BinaryOperatorLevel3 rightOperand=ExpressionLevel4) | 
	 *         (leftOperand=BracketedArgumentStartExpressionLevel4_BinaryOperatorExpression_0_1_0 name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5) | 
	 *         (leftOperand=BracketedArgumentStartExpressionLevel4_BinaryOperatorExpression_1_1_0 name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5)
	 *     )
	 */
	protected void sequence_BracketedArgumentStartExpressionLevel3_BracketedArgumentStartExpressionLevel4(ISerializationContext context, BinaryOperatorExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     BracketedArgumentStartExpressionLevel3.BinaryOperatorExpression_0_1_0 returns BinaryOperatorExpression
	 *
	 * Constraint:
	 *     (
	 *         (leftOperand=BracketedArgumentStartExpressionLevel3_BinaryOperatorExpression_0_1_0 name=BinaryOperatorLevel3 rightOperand=ExpressionLevel4) | 
	 *         (leftOperand=BracketedArgumentStartExpressionLevel4_BinaryOperatorExpression_0_1_0 name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5) | 
	 *         (leftOperand=BracketedArgumentStartExpressionLevel4_BinaryOperatorExpression_1_1_0 name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5)
	 *     )
	 */
	protected void sequence_BracketedArgumentStartExpressionLevel3_BracketedArgumentStartExpressionLevel4_BinaryOperatorExpression_0_1_0(ISerializationContext context, BinaryOperatorExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     BracketedArgumentStartExpressionLevel4 returns BinaryOperatorExpression
	 *
	 * Constraint:
	 *     (
	 *         (leftOperand=BracketedArgumentStartExpressionLevel4_BinaryOperatorExpression_0_1_0 name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5) | 
	 *         (leftOperand=BracketedArgumentStartExpressionLevel4_BinaryOperatorExpression_1_1_0 name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5)
	 *     )
	 */
	protected void sequence_BracketedArgumentStartExpressionLevel4(ISerializationContext context, BinaryOperatorExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     BracketedArgumentStartExpressionLevel4.BinaryOperatorExpression_0_1_0 returns BinaryOperatorExpression
	 *
	 * Constraint:
	 *     (leftOperand=BracketedArgumentStartExpressionLevel4_BinaryOperatorExpression_0_1_0 name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5)
	 */
	protected void sequence_BracketedArgumentStartExpressionLevel4_BinaryOperatorExpression_0_1_0(ISerializationContext context, BinaryOperatorExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__LEFT_OPERAND) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__LEFT_OPERAND));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__NAME));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__RIGHT_OPERAND) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__RIGHT_OPERAND));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_0_1_0(), semanticObject.getLeftOperand());
		feeder.accept(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getNameBinaryOperatorLevel4EnumRuleCall_0_1_1_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getRightOperandExpressionLevel5ParserRuleCall_0_1_2_0(), semanticObject.getRightOperand());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     BracketedArgumentStartExpressionLevel4.BinaryOperatorExpression_1_1_0 returns BinaryOperatorExpression
	 *
	 * Constraint:
	 *     (leftOperand=BracketedArgumentStartExpressionLevel4_BinaryOperatorExpression_1_1_0 name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5)
	 */
	protected void sequence_BracketedArgumentStartExpressionLevel4_BinaryOperatorExpression_1_1_0(ISerializationContext context, BinaryOperatorExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__LEFT_OPERAND) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__LEFT_OPERAND));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__NAME));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__RIGHT_OPERAND) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__RIGHT_OPERAND));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_1_0(), semanticObject.getLeftOperand());
		feeder.accept(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getNameBinaryOperatorLevel4EnumRuleCall_1_1_1_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getRightOperandExpressionLevel5ParserRuleCall_1_1_2_0(), semanticObject.getRightOperand());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     BracketedArgumentStartExpressionSingle returns DataMethodCallExpression
	 *     BracketedArgumentStartExpressionLevel1 returns DataMethodCallExpression
	 *     BracketedArgumentStartExpressionLevel2 returns DataMethodCallExpression
	 *     BracketedArgumentStartExpressionLevel2.BinaryOperatorExpression_0_1_0 returns DataMethodCallExpression
	 *     BracketedArgumentStartExpressionLevel3 returns DataMethodCallExpression
	 *     BracketedArgumentStartExpressionLevel3.BinaryOperatorExpression_0_1_0 returns DataMethodCallExpression
	 *     BracketedArgumentStartExpressionLevel4 returns DataMethodCallExpression
	 *     BracketedArgumentStartExpressionLevel4.BinaryOperatorExpression_0_1_0 returns DataMethodCallExpression
	 *     BracketedArgumentStartExpressionLevel5 returns DataMethodCallExpression
	 *     BracketedArgumentStartExpressionLevel5.DataMethodCallExpression_1_0 returns DataMethodCallExpression
	 *
	 * Constraint:
	 *     (
	 *         target=BracketedArgumentStartExpressionLevel5_DataMethodCallExpression_1_0 
	 *         onSuperClass?='^'? 
	 *         name=IDENTIFIER 
	 *         (arguments+=Expression arguments+=Expression*)?
	 *     )
	 */
	protected void sequence_BracketedArgumentStartExpressionLevel5(ISerializationContext context, DataMethodCallExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     BracketStartStatement returns ExpressionStatement
	 *     BracketedArgumentStartExpressionStatement returns ExpressionStatement
	 *
	 * Constraint:
	 *     expression=BracketedArgumentStartExpressionSingle
	 */
	protected void sequence_BracketedArgumentStartExpressionStatement(ISerializationContext context, ExpressionStatement semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.EXPRESSION_STATEMENT__EXPRESSION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.EXPRESSION_STATEMENT__EXPRESSION));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getBracketedArgumentStartExpressionStatementAccess().getExpressionBracketedArgumentStartExpressionSingleParserRuleCall_0(), semanticObject.getExpression());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Statement returns ExpressionStatement
	 *     Statement.StatementSequence_1_0 returns ExpressionStatement
	 *     StatementSingle returns ExpressionStatement
	 *
	 * Constraint:
	 *     (
	 *         expression=IDStartWithinStatementExpressionSingle | 
	 *         expression=NonIDStartWithinStatementExpressionSingle | 
	 *         expression=Expression | 
	 *         expression=BracketedArgumentStartExpressionSingle
	 *     )
	 */
	protected void sequence_BracketedArgumentStartExpressionStatement_ExpressionStatement(ISerializationContext context, ExpressionStatement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Channel returns Channel
	 *
	 * Constraint:
	 *     (
	 *         annotations+=Annotation* 
	 *         (
	 *             (instancePorts+=InstancePort instancePorts+=InstancePort* (externalPort=[Port|IDENTIFIER] instancePorts+=InstancePort*)?) | 
	 *             (externalPort=[Port|IDENTIFIER] instancePorts+=InstancePort*)
	 *         )?
	 *     )
	 */
	protected void sequence_Channel(ISerializationContext context, Channel semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Expression returns CharacterConstant
	 *     Expression.ExpressionSequence_1_0 returns CharacterConstant
	 *     ExpressionSingle returns CharacterConstant
	 *     ExpressionLevel1 returns CharacterConstant
	 *     ExpressionLevel2 returns CharacterConstant
	 *     ExpressionLevel2.BinaryOperatorExpression_1_0 returns CharacterConstant
	 *     ExpressionLevel3 returns CharacterConstant
	 *     ExpressionLevel3.BinaryOperatorExpression_1_0 returns CharacterConstant
	 *     ExpressionLevel4 returns CharacterConstant
	 *     ExpressionLevel4.BinaryOperatorExpression_1_0 returns CharacterConstant
	 *     ExpressionLevel5 returns CharacterConstant
	 *     ExpressionLevel5.DataMethodCallExpression_1_0 returns CharacterConstant
	 *     ExpressionLevel6 returns CharacterConstant
	 *     ExpressionLevel7 returns CharacterConstant
	 *     ExpressionConstant returns CharacterConstant
	 *     CharacterConstant returns CharacterConstant
	 *     NonIDStartWithinStatementExpressionSingle returns CharacterConstant
	 *     NonIDStartWithinStatementExpressionLevel1 returns CharacterConstant
	 *     NonIDStartWithinStatementExpressionLevel2 returns CharacterConstant
	 *     NonIDStartWithinStatementExpressionLevel2.BinaryOperatorExpression_1_0 returns CharacterConstant
	 *     NonIDStartWithinStatementExpressionLevel3 returns CharacterConstant
	 *     NonIDStartWithinStatementExpressionLevel3.BinaryOperatorExpression_1_0 returns CharacterConstant
	 *     NonIDStartWithinStatementExpressionLevel4 returns CharacterConstant
	 *     NonIDStartWithinStatementExpressionLevel4.BinaryOperatorExpression_1_0 returns CharacterConstant
	 *     NonIDStartWithinStatementExpressionLevel5 returns CharacterConstant
	 *     NonIDStartWithinStatementExpressionLevel5.DataMethodCallExpression_1_0 returns CharacterConstant
	 *     NonIDStartWithinStatementExpressionLevel6 returns CharacterConstant
	 *     NonIDStartWithinStatementExpressionLevel7 returns CharacterConstant
	 *
	 * Constraint:
	 *     value=CHARACTER
	 */
	protected void sequence_CharacterConstant(ISerializationContext context, CharacterConstant semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.CHARACTER_CONSTANT__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.CHARACTER_CONSTANT__VALUE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getCharacterConstantAccess().getValueCHARACTERTerminalRuleCall_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     ClusterClass returns ClusterClass
	 *
	 * Constraint:
	 *     (
	 *         annotations+=Annotation* 
	 *         name=IDENTIFIER 
	 *         (parameters+=Declaration parameters+=Declaration*)? 
	 *         (ports+=Port ports+=Port*)? 
	 *         instances+=Instance* 
	 *         channels+=Channel*
	 *     )
	 */
	protected void sequence_ClusterClass(ISerializationContext context, ClusterClass semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Expression returns CurrentTimeExpression
	 *     Expression.ExpressionSequence_1_0 returns CurrentTimeExpression
	 *     ExpressionSingle returns CurrentTimeExpression
	 *     ExpressionLevel1 returns CurrentTimeExpression
	 *     ExpressionLevel2 returns CurrentTimeExpression
	 *     ExpressionLevel2.BinaryOperatorExpression_1_0 returns CurrentTimeExpression
	 *     ExpressionLevel3 returns CurrentTimeExpression
	 *     ExpressionLevel3.BinaryOperatorExpression_1_0 returns CurrentTimeExpression
	 *     ExpressionLevel4 returns CurrentTimeExpression
	 *     ExpressionLevel4.BinaryOperatorExpression_1_0 returns CurrentTimeExpression
	 *     ExpressionLevel5 returns CurrentTimeExpression
	 *     ExpressionLevel5.DataMethodCallExpression_1_0 returns CurrentTimeExpression
	 *     ExpressionLevel6 returns CurrentTimeExpression
	 *     ExpressionLevel7 returns CurrentTimeExpression
	 *     CurrentTimeExpression returns CurrentTimeExpression
	 *     NonIDStartWithinStatementExpressionSingle returns CurrentTimeExpression
	 *     NonIDStartWithinStatementExpressionLevel1 returns CurrentTimeExpression
	 *     NonIDStartWithinStatementExpressionLevel2 returns CurrentTimeExpression
	 *     NonIDStartWithinStatementExpressionLevel2.BinaryOperatorExpression_1_0 returns CurrentTimeExpression
	 *     NonIDStartWithinStatementExpressionLevel3 returns CurrentTimeExpression
	 *     NonIDStartWithinStatementExpressionLevel3.BinaryOperatorExpression_1_0 returns CurrentTimeExpression
	 *     NonIDStartWithinStatementExpressionLevel4 returns CurrentTimeExpression
	 *     NonIDStartWithinStatementExpressionLevel4.BinaryOperatorExpression_1_0 returns CurrentTimeExpression
	 *     NonIDStartWithinStatementExpressionLevel5 returns CurrentTimeExpression
	 *     NonIDStartWithinStatementExpressionLevel5.DataMethodCallExpression_1_0 returns CurrentTimeExpression
	 *     NonIDStartWithinStatementExpressionLevel6 returns CurrentTimeExpression
	 *     NonIDStartWithinStatementExpressionLevel7 returns CurrentTimeExpression
	 *
	 * Constraint:
	 *     {CurrentTimeExpression}
	 */
	protected void sequence_CurrentTimeExpression(ISerializationContext context, CurrentTimeExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     DataClass returns DataClass
	 *
	 * Constraint:
	 *     (
	 *         annotations+=Annotation* 
	 *         native?='native'? 
	 *         name=IDENTIFIER 
	 *         superClass=IDENTIFIER? 
	 *         (instanceVariables+=Declaration instanceVariables+=Declaration*)? 
	 *         (dataMethodsNamed+=DataMethodNamed | dataMethodsUnaryOperator+=DataMethodUnaryOperator | dataMethodsBinaryOperator+=DataMethodBinaryOperator)*
	 *     )
	 */
	protected void sequence_DataClass(ISerializationContext context, DataClass semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     DataMethodBinaryOperator returns DataMethodBinaryOperator
	 *
	 * Constraint:
	 *     (
	 *         annotations+=Annotation* 
	 *         native?='native'? 
	 *         name=OperatorBinary 
	 *         parameters+=Declaration 
	 *         parameters+=Declaration* 
	 *         returnType=IDENTIFIER 
	 *         (localVariables+=Declaration localVariables+=Declaration*)? 
	 *         body=Expression?
	 *     )
	 */
	protected void sequence_DataMethodBinaryOperator(ISerializationContext context, DataMethodBinaryOperator semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     DataMethodNamed returns DataMethodNamed
	 *
	 * Constraint:
	 *     (
	 *         annotations+=Annotation* 
	 *         native?='native'? 
	 *         name=IDENTIFIER 
	 *         (parameters+=Declaration parameters+=Declaration*)? 
	 *         returnType=IDENTIFIER 
	 *         (localVariables+=Declaration localVariables+=Declaration*)? 
	 *         body=Expression?
	 *     )
	 */
	protected void sequence_DataMethodNamed(ISerializationContext context, DataMethodNamed semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     DataMethodUnaryOperator returns DataMethodUnaryOperator
	 *
	 * Constraint:
	 *     (
	 *         annotations+=Annotation* 
	 *         native?='native'? 
	 *         name=OperatorUnary 
	 *         returnType=IDENTIFIER 
	 *         (localVariables+=Declaration localVariables+=Declaration*)? 
	 *         body=Expression?
	 *     )
	 */
	protected void sequence_DataMethodUnaryOperator(ISerializationContext context, DataMethodUnaryOperator semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Declaration returns Declaration
	 *
	 * Constraint:
	 *     ((variables+=Variable variables+=Variable*)? type=IDENTIFIER)
	 */
	protected void sequence_Declaration(ISerializationContext context, Declaration semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Statement returns DelayStatement
	 *     Statement.StatementSequence_1_0 returns DelayStatement
	 *     StatementSingle returns DelayStatement
	 *     DelayStatement returns DelayStatement
	 *
	 * Constraint:
	 *     expression=ExpressionSingle
	 */
	protected void sequence_DelayStatement(ISerializationContext context, DelayStatement semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.DELAY_STATEMENT__EXPRESSION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.DELAY_STATEMENT__EXPRESSION));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getDelayStatementAccess().getExpressionExpressionSingleParserRuleCall_1_0(), semanticObject.getExpression());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Expression returns EnvironmentConstant
	 *     Expression.ExpressionSequence_1_0 returns EnvironmentConstant
	 *     ExpressionSingle returns EnvironmentConstant
	 *     ExpressionLevel1 returns EnvironmentConstant
	 *     ExpressionLevel2 returns EnvironmentConstant
	 *     ExpressionLevel2.BinaryOperatorExpression_1_0 returns EnvironmentConstant
	 *     ExpressionLevel3 returns EnvironmentConstant
	 *     ExpressionLevel3.BinaryOperatorExpression_1_0 returns EnvironmentConstant
	 *     ExpressionLevel4 returns EnvironmentConstant
	 *     ExpressionLevel4.BinaryOperatorExpression_1_0 returns EnvironmentConstant
	 *     ExpressionLevel5 returns EnvironmentConstant
	 *     ExpressionLevel5.DataMethodCallExpression_1_0 returns EnvironmentConstant
	 *     ExpressionLevel6 returns EnvironmentConstant
	 *     ExpressionLevel7 returns EnvironmentConstant
	 *     ExpressionConstant returns EnvironmentConstant
	 *     EnvironmentConstant returns EnvironmentConstant
	 *     NonIDStartWithinStatementExpressionSingle returns EnvironmentConstant
	 *     NonIDStartWithinStatementExpressionLevel1 returns EnvironmentConstant
	 *     NonIDStartWithinStatementExpressionLevel2 returns EnvironmentConstant
	 *     NonIDStartWithinStatementExpressionLevel2.BinaryOperatorExpression_1_0 returns EnvironmentConstant
	 *     NonIDStartWithinStatementExpressionLevel3 returns EnvironmentConstant
	 *     NonIDStartWithinStatementExpressionLevel3.BinaryOperatorExpression_1_0 returns EnvironmentConstant
	 *     NonIDStartWithinStatementExpressionLevel4 returns EnvironmentConstant
	 *     NonIDStartWithinStatementExpressionLevel4.BinaryOperatorExpression_1_0 returns EnvironmentConstant
	 *     NonIDStartWithinStatementExpressionLevel5 returns EnvironmentConstant
	 *     NonIDStartWithinStatementExpressionLevel5.DataMethodCallExpression_1_0 returns EnvironmentConstant
	 *     NonIDStartWithinStatementExpressionLevel6 returns EnvironmentConstant
	 *     NonIDStartWithinStatementExpressionLevel7 returns EnvironmentConstant
	 *
	 * Constraint:
	 *     value=ENVIRONMENT_VARIABLE_NAME
	 */
	protected void sequence_EnvironmentConstant(ISerializationContext context, EnvironmentConstant semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.ENVIRONMENT_CONSTANT__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.ENVIRONMENT_CONSTANT__VALUE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getEnvironmentConstantAccess().getValueENVIRONMENT_VARIABLE_NAMETerminalRuleCall_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Expression returns AssignmentExpression
	 *     Expression.ExpressionSequence_1_0 returns AssignmentExpression
	 *     ExpressionSingle returns AssignmentExpression
	 *     ExpressionLevel1 returns AssignmentExpression
	 *
	 * Constraint:
	 *     (variable=IDENTIFIER expression=ExpressionLevel1)
	 */
	protected void sequence_ExpressionLevel1(ISerializationContext context, AssignmentExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.ASSIGNMENT_EXPRESSION__VARIABLE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.ASSIGNMENT_EXPRESSION__VARIABLE));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getExpressionLevel1Access().getVariableIDENTIFIERParserRuleCall_1_1_0(), semanticObject.getVariable());
		feeder.accept(grammarAccess.getExpressionLevel1Access().getExpressionExpressionLevel1ParserRuleCall_1_3_0(), semanticObject.getExpression());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Expression returns ReturnExpression
	 *     Expression.ExpressionSequence_1_0 returns ReturnExpression
	 *     ExpressionSingle returns ReturnExpression
	 *     ExpressionLevel1 returns ReturnExpression
	 *
	 * Constraint:
	 *     expression=ExpressionLevel1
	 */
	protected void sequence_ExpressionLevel1(ISerializationContext context, ReturnExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.RETURN_EXPRESSION__EXPRESSION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.RETURN_EXPRESSION__EXPRESSION));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getExpressionLevel1Access().getExpressionExpressionLevel1ParserRuleCall_2_2_0(), semanticObject.getExpression());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Expression returns BinaryOperatorExpression
	 *     Expression.ExpressionSequence_1_0 returns BinaryOperatorExpression
	 *     ExpressionSingle returns BinaryOperatorExpression
	 *     ExpressionLevel1 returns BinaryOperatorExpression
	 *     ExpressionLevel2 returns BinaryOperatorExpression
	 *     ExpressionLevel2.BinaryOperatorExpression_1_0 returns BinaryOperatorExpression
	 *
	 * Constraint:
	 *     (
	 *         (leftOperand=ExpressionLevel2_BinaryOperatorExpression_1_0 name=BinaryOperatorLevel2 rightOperand=ExpressionLevel3) | 
	 *         (leftOperand=ExpressionLevel3_BinaryOperatorExpression_1_0 name=BinaryOperatorLevel3 rightOperand=ExpressionLevel4) | 
	 *         (leftOperand=ExpressionLevel4_BinaryOperatorExpression_1_0 name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5)
	 *     )
	 */
	protected void sequence_ExpressionLevel2_ExpressionLevel3_ExpressionLevel4(ISerializationContext context, BinaryOperatorExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ExpressionLevel3 returns BinaryOperatorExpression
	 *     ExpressionLevel3.BinaryOperatorExpression_1_0 returns BinaryOperatorExpression
	 *
	 * Constraint:
	 *     (
	 *         (leftOperand=ExpressionLevel3_BinaryOperatorExpression_1_0 name=BinaryOperatorLevel3 rightOperand=ExpressionLevel4) | 
	 *         (leftOperand=ExpressionLevel4_BinaryOperatorExpression_1_0 name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5)
	 *     )
	 */
	protected void sequence_ExpressionLevel3_ExpressionLevel4(ISerializationContext context, BinaryOperatorExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ExpressionLevel4 returns BinaryOperatorExpression
	 *     ExpressionLevel4.BinaryOperatorExpression_1_0 returns BinaryOperatorExpression
	 *
	 * Constraint:
	 *     (leftOperand=ExpressionLevel4_BinaryOperatorExpression_1_0 name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5)
	 */
	protected void sequence_ExpressionLevel4(ISerializationContext context, BinaryOperatorExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__LEFT_OPERAND) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__LEFT_OPERAND));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__NAME));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__RIGHT_OPERAND) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__RIGHT_OPERAND));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_0(), semanticObject.getLeftOperand());
		feeder.accept(grammarAccess.getExpressionLevel4Access().getNameBinaryOperatorLevel4EnumRuleCall_1_1_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getExpressionLevel4Access().getRightOperandExpressionLevel5ParserRuleCall_1_2_0(), semanticObject.getRightOperand());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Expression returns DataMethodCallExpression
	 *     Expression.ExpressionSequence_1_0 returns DataMethodCallExpression
	 *     ExpressionSingle returns DataMethodCallExpression
	 *     ExpressionLevel1 returns DataMethodCallExpression
	 *     ExpressionLevel2 returns DataMethodCallExpression
	 *     ExpressionLevel2.BinaryOperatorExpression_1_0 returns DataMethodCallExpression
	 *     ExpressionLevel3 returns DataMethodCallExpression
	 *     ExpressionLevel3.BinaryOperatorExpression_1_0 returns DataMethodCallExpression
	 *     ExpressionLevel4 returns DataMethodCallExpression
	 *     ExpressionLevel4.BinaryOperatorExpression_1_0 returns DataMethodCallExpression
	 *     ExpressionLevel5 returns DataMethodCallExpression
	 *     ExpressionLevel5.DataMethodCallExpression_1_0 returns DataMethodCallExpression
	 *
	 * Constraint:
	 *     (target=ExpressionLevel5_DataMethodCallExpression_1_0 onSuperClass?='^'? name=IDENTIFIER (arguments+=Expression arguments+=Expression*)?)
	 */
	protected void sequence_ExpressionLevel5(ISerializationContext context, DataMethodCallExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Expression returns ExpressionSequenceRoundBracket
	 *     Expression.ExpressionSequence_1_0 returns ExpressionSequenceRoundBracket
	 *     ExpressionSingle returns ExpressionSequenceRoundBracket
	 *     ExpressionLevel1 returns ExpressionSequenceRoundBracket
	 *     ExpressionLevel2 returns ExpressionSequenceRoundBracket
	 *     ExpressionLevel2.BinaryOperatorExpression_1_0 returns ExpressionSequenceRoundBracket
	 *     ExpressionLevel3 returns ExpressionSequenceRoundBracket
	 *     ExpressionLevel3.BinaryOperatorExpression_1_0 returns ExpressionSequenceRoundBracket
	 *     ExpressionLevel4 returns ExpressionSequenceRoundBracket
	 *     ExpressionLevel4.BinaryOperatorExpression_1_0 returns ExpressionSequenceRoundBracket
	 *     ExpressionLevel5 returns ExpressionSequenceRoundBracket
	 *     ExpressionLevel5.DataMethodCallExpression_1_0 returns ExpressionSequenceRoundBracket
	 *     ExpressionLevel6 returns ExpressionSequenceRoundBracket
	 *     ExpressionLevel7 returns ExpressionSequenceRoundBracket
	 *     ExpressionSequenceRoundBracket returns ExpressionSequenceRoundBracket
	 *     BracketedArgumentStartExpressionLevel2.BinaryOperatorExpression_1_1_0 returns ExpressionSequenceRoundBracket
	 *     BracketedArgumentStartExpressionLevel3.BinaryOperatorExpression_1_1_0 returns ExpressionSequenceRoundBracket
	 *     BracketedArgumentStartExpressionLevel4.BinaryOperatorExpression_1_1_0 returns ExpressionSequenceRoundBracket
	 *     BracketedArgumentStartExpressionLevel5.DataMethodCallExpression_1_0 returns ExpressionSequenceRoundBracket
	 *
	 * Constraint:
	 *     (expressions+=ExpressionSingle expressions+=ExpressionSingle*)
	 */
	protected void sequence_ExpressionSequenceRoundBracket(ISerializationContext context, ExpressionSequenceRoundBracket semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ExpressionStatement returns ExpressionStatement
	 *
	 * Constraint:
	 *     (expression=IDStartWithinStatementExpressionSingle | expression=NonIDStartWithinStatementExpressionSingle | expression=Expression)
	 */
	protected void sequence_ExpressionStatement(ISerializationContext context, ExpressionStatement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Expression returns ExpressionSequence
	 *
	 * Constraint:
	 *     (expressions+=Expression_ExpressionSequence_1_0 expressions+=ExpressionSingle+)
	 */
	protected void sequence_Expression(ISerializationContext context, ExpressionSequence semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Expression returns FloatConstant
	 *     Expression.ExpressionSequence_1_0 returns FloatConstant
	 *     ExpressionSingle returns FloatConstant
	 *     ExpressionLevel1 returns FloatConstant
	 *     ExpressionLevel2 returns FloatConstant
	 *     ExpressionLevel2.BinaryOperatorExpression_1_0 returns FloatConstant
	 *     ExpressionLevel3 returns FloatConstant
	 *     ExpressionLevel3.BinaryOperatorExpression_1_0 returns FloatConstant
	 *     ExpressionLevel4 returns FloatConstant
	 *     ExpressionLevel4.BinaryOperatorExpression_1_0 returns FloatConstant
	 *     ExpressionLevel5 returns FloatConstant
	 *     ExpressionLevel5.DataMethodCallExpression_1_0 returns FloatConstant
	 *     ExpressionLevel6 returns FloatConstant
	 *     ExpressionLevel7 returns FloatConstant
	 *     ExpressionConstant returns FloatConstant
	 *     FloatConstant returns FloatConstant
	 *     NonIDStartWithinStatementExpressionSingle returns FloatConstant
	 *     NonIDStartWithinStatementExpressionLevel1 returns FloatConstant
	 *     NonIDStartWithinStatementExpressionLevel2 returns FloatConstant
	 *     NonIDStartWithinStatementExpressionLevel2.BinaryOperatorExpression_1_0 returns FloatConstant
	 *     NonIDStartWithinStatementExpressionLevel3 returns FloatConstant
	 *     NonIDStartWithinStatementExpressionLevel3.BinaryOperatorExpression_1_0 returns FloatConstant
	 *     NonIDStartWithinStatementExpressionLevel4 returns FloatConstant
	 *     NonIDStartWithinStatementExpressionLevel4.BinaryOperatorExpression_1_0 returns FloatConstant
	 *     NonIDStartWithinStatementExpressionLevel5 returns FloatConstant
	 *     NonIDStartWithinStatementExpressionLevel5.DataMethodCallExpression_1_0 returns FloatConstant
	 *     NonIDStartWithinStatementExpressionLevel6 returns FloatConstant
	 *     NonIDStartWithinStatementExpressionLevel7 returns FloatConstant
	 *
	 * Constraint:
	 *     value=FLOAT
	 */
	protected void sequence_FloatConstant(ISerializationContext context, FloatConstant semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.FLOAT_CONSTANT__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.FLOAT_CONSTANT__VALUE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getFloatConstantAccess().getValueFLOATParserRuleCall_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Statement returns GuardedStatement
	 *     Statement.StatementSequence_1_0 returns GuardedStatement
	 *     StatementSingle returns GuardedStatement
	 *     GuardedStatement returns GuardedStatement
	 *
	 * Constraint:
	 *     (guard=Expression statement=StatementSingle)
	 */
	protected void sequence_GuardedStatement(ISerializationContext context, GuardedStatement semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.GUARDED_STATEMENT__GUARD) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.GUARDED_STATEMENT__GUARD));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.GUARDED_STATEMENT__STATEMENT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.GUARDED_STATEMENT__STATEMENT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getGuardedStatementAccess().getGuardExpressionParserRuleCall_1_0(), semanticObject.getGuard());
		feeder.accept(grammarAccess.getGuardedStatementAccess().getStatementStatementSingleParserRuleCall_3_0(), semanticObject.getStatement());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     IDStartWithinStatementExpressionSingle returns AssignmentExpression
	 *     IDStartWithinStatementExpressionLevel1 returns AssignmentExpression
	 *
	 * Constraint:
	 *     (variable=IDENTIFIER expression=ExpressionLevel1)
	 */
	protected void sequence_IDStartWithinStatementExpressionLevel1(ISerializationContext context, AssignmentExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.ASSIGNMENT_EXPRESSION__VARIABLE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.ASSIGNMENT_EXPRESSION__VARIABLE));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.ASSIGNMENT_EXPRESSION__EXPRESSION));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getVariableIDENTIFIERParserRuleCall_1_1_0(), semanticObject.getVariable());
		feeder.accept(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getExpressionExpressionLevel1ParserRuleCall_1_3_0(), semanticObject.getExpression());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     IDStartWithinStatementExpressionSingle returns BinaryOperatorExpression
	 *     IDStartWithinStatementExpressionLevel1 returns BinaryOperatorExpression
	 *     IDStartWithinStatementExpressionLevel2 returns BinaryOperatorExpression
	 *     IDStartWithinStatementExpressionLevel2.BinaryOperatorExpression_1_0 returns BinaryOperatorExpression
	 *
	 * Constraint:
	 *     (
	 *         (leftOperand=IDStartWithinStatementExpressionLevel2_BinaryOperatorExpression_1_0 name=BinaryOperatorLevel2 rightOperand=ExpressionLevel3) | 
	 *         (leftOperand=IDStartWithinStatementExpressionLevel3_BinaryOperatorExpression_1_0 name=BinaryOperatorLevel3 rightOperand=ExpressionLevel4) | 
	 *         (leftOperand=IDStartWithinStatementExpressionLevel4_BinaryOperatorExpression_1_0 name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5)
	 *     )
	 */
	protected void sequence_IDStartWithinStatementExpressionLevel2_IDStartWithinStatementExpressionLevel3_IDStartWithinStatementExpressionLevel4(ISerializationContext context, BinaryOperatorExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     IDStartWithinStatementExpressionLevel3 returns BinaryOperatorExpression
	 *     IDStartWithinStatementExpressionLevel3.BinaryOperatorExpression_1_0 returns BinaryOperatorExpression
	 *
	 * Constraint:
	 *     (
	 *         (leftOperand=IDStartWithinStatementExpressionLevel3_BinaryOperatorExpression_1_0 name=BinaryOperatorLevel3 rightOperand=ExpressionLevel4) | 
	 *         (leftOperand=IDStartWithinStatementExpressionLevel4_BinaryOperatorExpression_1_0 name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5)
	 *     )
	 */
	protected void sequence_IDStartWithinStatementExpressionLevel3_IDStartWithinStatementExpressionLevel4(ISerializationContext context, BinaryOperatorExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     IDStartWithinStatementExpressionLevel4 returns BinaryOperatorExpression
	 *     IDStartWithinStatementExpressionLevel4.BinaryOperatorExpression_1_0 returns BinaryOperatorExpression
	 *
	 * Constraint:
	 *     (leftOperand=IDStartWithinStatementExpressionLevel4_BinaryOperatorExpression_1_0 name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5)
	 */
	protected void sequence_IDStartWithinStatementExpressionLevel4(ISerializationContext context, BinaryOperatorExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__LEFT_OPERAND) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__LEFT_OPERAND));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__NAME));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__RIGHT_OPERAND) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__RIGHT_OPERAND));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_0(), semanticObject.getLeftOperand());
		feeder.accept(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getNameBinaryOperatorLevel4EnumRuleCall_1_1_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getRightOperandExpressionLevel5ParserRuleCall_1_2_0(), semanticObject.getRightOperand());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     IDStartWithinStatementExpressionSingle returns DataMethodCallExpression
	 *     IDStartWithinStatementExpressionLevel1 returns DataMethodCallExpression
	 *     IDStartWithinStatementExpressionLevel2 returns DataMethodCallExpression
	 *     IDStartWithinStatementExpressionLevel2.BinaryOperatorExpression_1_0 returns DataMethodCallExpression
	 *     IDStartWithinStatementExpressionLevel3 returns DataMethodCallExpression
	 *     IDStartWithinStatementExpressionLevel3.BinaryOperatorExpression_1_0 returns DataMethodCallExpression
	 *     IDStartWithinStatementExpressionLevel4 returns DataMethodCallExpression
	 *     IDStartWithinStatementExpressionLevel4.BinaryOperatorExpression_1_0 returns DataMethodCallExpression
	 *     IDStartWithinStatementExpressionLevel5 returns DataMethodCallExpression
	 *     IDStartWithinStatementExpressionLevel5.DataMethodCallExpression_1_0 returns DataMethodCallExpression
	 *
	 * Constraint:
	 *     (
	 *         target=IDStartWithinStatementExpressionLevel5_DataMethodCallExpression_1_0 
	 *         onSuperClass?='^'? 
	 *         name=IDENTIFIER 
	 *         (arguments+=Expression arguments+=Expression*)?
	 *     )
	 */
	protected void sequence_IDStartWithinStatementExpressionLevel5(ISerializationContext context, DataMethodCallExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Expression returns IfExpression
	 *     Expression.ExpressionSequence_1_0 returns IfExpression
	 *     ExpressionSingle returns IfExpression
	 *     ExpressionLevel1 returns IfExpression
	 *     ExpressionLevel2 returns IfExpression
	 *     ExpressionLevel2.BinaryOperatorExpression_1_0 returns IfExpression
	 *     ExpressionLevel3 returns IfExpression
	 *     ExpressionLevel3.BinaryOperatorExpression_1_0 returns IfExpression
	 *     ExpressionLevel4 returns IfExpression
	 *     ExpressionLevel4.BinaryOperatorExpression_1_0 returns IfExpression
	 *     ExpressionLevel5 returns IfExpression
	 *     ExpressionLevel5.DataMethodCallExpression_1_0 returns IfExpression
	 *     ExpressionLevel6 returns IfExpression
	 *     IfExpression returns IfExpression
	 *
	 * Constraint:
	 *     (condition=Expression thenClause=Expression elseClause=Expression?)
	 */
	protected void sequence_IfExpression(ISerializationContext context, IfExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Statement returns IfStatement
	 *     Statement.StatementSequence_1_0 returns IfStatement
	 *     StatementSingle returns IfStatement
	 *     IfStatement returns IfStatement
	 *
	 * Constraint:
	 *     (condition=Expression thenClause=Statement elseClause=Statement?)
	 */
	protected void sequence_IfStatement(ISerializationContext context, IfStatement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ImportLib returns Import
	 *
	 * Constraint:
	 *     importURI=POOSL_STRING
	 */
	protected void sequence_ImportLib(ISerializationContext context, Import semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.IMPORT__IMPORT_URI) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.IMPORT__IMPORT_URI));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getImportLibAccess().getImportURIPOOSL_STRINGTerminalRuleCall_1_0(), semanticObject.getImportURI());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Import returns Import
	 *
	 * Constraint:
	 *     importURI=POOSL_STRING
	 */
	protected void sequence_Import(ISerializationContext context, Import semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.IMPORT__IMPORT_URI) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.IMPORT__IMPORT_URI));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getImportAccess().getImportURIPOOSL_STRINGTerminalRuleCall_1_0(), semanticObject.getImportURI());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     InstanceParameter returns InstanceParameter
	 *
	 * Constraint:
	 *     (parameter=IDENTIFIER expression=Expression)
	 */
	protected void sequence_InstanceParameter(ISerializationContext context, InstanceParameter semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.INSTANCE_PARAMETER__PARAMETER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.INSTANCE_PARAMETER__PARAMETER));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.INSTANCE_PARAMETER__EXPRESSION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.INSTANCE_PARAMETER__EXPRESSION));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getInstanceParameterAccess().getParameterIDENTIFIERParserRuleCall_0_0(), semanticObject.getParameter());
		feeder.accept(grammarAccess.getInstanceParameterAccess().getExpressionExpressionParserRuleCall_2_0(), semanticObject.getExpression());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     InstancePort returns InstancePort
	 *
	 * Constraint:
	 *     (instance=[Instance|IDENTIFIER] port=PortReference)
	 */
	protected void sequence_InstancePort(ISerializationContext context, InstancePort semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.INSTANCE_PORT__INSTANCE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.INSTANCE_PORT__INSTANCE));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.INSTANCE_PORT__PORT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.INSTANCE_PORT__PORT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getInstancePortAccess().getInstanceInstanceIDENTIFIERParserRuleCall_0_0_1(), semanticObject.eGet(PooslPackage.Literals.INSTANCE_PORT__INSTANCE, false));
		feeder.accept(grammarAccess.getInstancePortAccess().getPortPortReferenceParserRuleCall_2_0(), semanticObject.getPort());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Instance returns Instance
	 *
	 * Constraint:
	 *     (
	 *         annotations+=Annotation* 
	 *         name=IDENTIFIER 
	 *         classDefinition=IDENTIFIER 
	 *         (instanceParameters+=InstanceParameter instanceParameters+=InstanceParameter*)?
	 *     )
	 */
	protected void sequence_Instance(ISerializationContext context, Instance semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Expression returns IntegerConstant
	 *     Expression.ExpressionSequence_1_0 returns IntegerConstant
	 *     ExpressionSingle returns IntegerConstant
	 *     ExpressionLevel1 returns IntegerConstant
	 *     ExpressionLevel2 returns IntegerConstant
	 *     ExpressionLevel2.BinaryOperatorExpression_1_0 returns IntegerConstant
	 *     ExpressionLevel3 returns IntegerConstant
	 *     ExpressionLevel3.BinaryOperatorExpression_1_0 returns IntegerConstant
	 *     ExpressionLevel4 returns IntegerConstant
	 *     ExpressionLevel4.BinaryOperatorExpression_1_0 returns IntegerConstant
	 *     ExpressionLevel5 returns IntegerConstant
	 *     ExpressionLevel5.DataMethodCallExpression_1_0 returns IntegerConstant
	 *     ExpressionLevel6 returns IntegerConstant
	 *     ExpressionLevel7 returns IntegerConstant
	 *     ExpressionConstant returns IntegerConstant
	 *     IntegerConstant returns IntegerConstant
	 *     NonIDStartWithinStatementExpressionSingle returns IntegerConstant
	 *     NonIDStartWithinStatementExpressionLevel1 returns IntegerConstant
	 *     NonIDStartWithinStatementExpressionLevel2 returns IntegerConstant
	 *     NonIDStartWithinStatementExpressionLevel2.BinaryOperatorExpression_1_0 returns IntegerConstant
	 *     NonIDStartWithinStatementExpressionLevel3 returns IntegerConstant
	 *     NonIDStartWithinStatementExpressionLevel3.BinaryOperatorExpression_1_0 returns IntegerConstant
	 *     NonIDStartWithinStatementExpressionLevel4 returns IntegerConstant
	 *     NonIDStartWithinStatementExpressionLevel4.BinaryOperatorExpression_1_0 returns IntegerConstant
	 *     NonIDStartWithinStatementExpressionLevel5 returns IntegerConstant
	 *     NonIDStartWithinStatementExpressionLevel5.DataMethodCallExpression_1_0 returns IntegerConstant
	 *     NonIDStartWithinStatementExpressionLevel6 returns IntegerConstant
	 *     NonIDStartWithinStatementExpressionLevel7 returns IntegerConstant
	 *
	 * Constraint:
	 *     value=INTEGER
	 */
	protected void sequence_IntegerConstant(ISerializationContext context, IntegerConstant semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.INTEGER_CONSTANT__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.INTEGER_CONSTANT__VALUE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getIntegerConstantAccess().getValueINTEGERParserRuleCall_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Statement returns InterruptStatement
	 *     Statement.StatementSequence_1_0 returns InterruptStatement
	 *     StatementSingle returns InterruptStatement
	 *     InterruptStatement returns InterruptStatement
	 *
	 * Constraint:
	 *     (normalClause=Statement interruptingClause=StatementSingle)
	 */
	protected void sequence_InterruptStatement(ISerializationContext context, InterruptStatement semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.INTERRUPT_STATEMENT__NORMAL_CLAUSE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.INTERRUPT_STATEMENT__NORMAL_CLAUSE));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.INTERRUPT_STATEMENT__INTERRUPTING_CLAUSE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.INTERRUPT_STATEMENT__INTERRUPTING_CLAUSE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getInterruptStatementAccess().getNormalClauseStatementParserRuleCall_1_0(), semanticObject.getNormalClause());
		feeder.accept(grammarAccess.getInterruptStatementAccess().getInterruptingClauseStatementSingleParserRuleCall_3_0(), semanticObject.getInterruptingClause());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     MessageParameter returns MessageParameter
	 *
	 * Constraint:
	 *     type=IDENTIFIER
	 */
	protected void sequence_MessageParameter(ISerializationContext context, MessageParameter semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.MESSAGE_PARAMETER__TYPE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.MESSAGE_PARAMETER__TYPE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getMessageParameterAccess().getTypeIDENTIFIERParserRuleCall_0(), semanticObject.getType());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     MessageReceiveSignature returns MessageSignature
	 *
	 * Constraint:
	 *     (port=PortReference name=IDENTIFIER (parameters+=MessageParameter parameters+=MessageParameter*)?)
	 */
	protected void sequence_MessageReceiveSignature(ISerializationContext context, MessageSignature semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     MessageSendSignature returns MessageSignature
	 *
	 * Constraint:
	 *     (port=PortReference name=IDENTIFIER (parameters+=MessageParameter parameters+=MessageParameter*)?)
	 */
	protected void sequence_MessageSendSignature(ISerializationContext context, MessageSignature semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Expression returns NewExpression
	 *     Expression.ExpressionSequence_1_0 returns NewExpression
	 *     ExpressionSingle returns NewExpression
	 *     ExpressionLevel1 returns NewExpression
	 *     ExpressionLevel2 returns NewExpression
	 *     ExpressionLevel2.BinaryOperatorExpression_1_0 returns NewExpression
	 *     ExpressionLevel3 returns NewExpression
	 *     ExpressionLevel3.BinaryOperatorExpression_1_0 returns NewExpression
	 *     ExpressionLevel4 returns NewExpression
	 *     ExpressionLevel4.BinaryOperatorExpression_1_0 returns NewExpression
	 *     ExpressionLevel5 returns NewExpression
	 *     ExpressionLevel5.DataMethodCallExpression_1_0 returns NewExpression
	 *     ExpressionLevel6 returns NewExpression
	 *     ExpressionLevel7 returns NewExpression
	 *     NewExpression returns NewExpression
	 *     NonIDStartWithinStatementExpressionSingle returns NewExpression
	 *     NonIDStartWithinStatementExpressionLevel1 returns NewExpression
	 *     NonIDStartWithinStatementExpressionLevel2 returns NewExpression
	 *     NonIDStartWithinStatementExpressionLevel2.BinaryOperatorExpression_1_0 returns NewExpression
	 *     NonIDStartWithinStatementExpressionLevel3 returns NewExpression
	 *     NonIDStartWithinStatementExpressionLevel3.BinaryOperatorExpression_1_0 returns NewExpression
	 *     NonIDStartWithinStatementExpressionLevel4 returns NewExpression
	 *     NonIDStartWithinStatementExpressionLevel4.BinaryOperatorExpression_1_0 returns NewExpression
	 *     NonIDStartWithinStatementExpressionLevel5 returns NewExpression
	 *     NonIDStartWithinStatementExpressionLevel5.DataMethodCallExpression_1_0 returns NewExpression
	 *     NonIDStartWithinStatementExpressionLevel6 returns NewExpression
	 *     NonIDStartWithinStatementExpressionLevel7 returns NewExpression
	 *
	 * Constraint:
	 *     dataClass=IDENTIFIER
	 */
	protected void sequence_NewExpression(ISerializationContext context, NewExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.NEW_EXPRESSION__DATA_CLASS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.NEW_EXPRESSION__DATA_CLASS));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getNewExpressionAccess().getDataClassIDENTIFIERParserRuleCall_2_0(), semanticObject.getDataClass());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Expression returns NilConstant
	 *     Expression.ExpressionSequence_1_0 returns NilConstant
	 *     ExpressionSingle returns NilConstant
	 *     ExpressionLevel1 returns NilConstant
	 *     ExpressionLevel2 returns NilConstant
	 *     ExpressionLevel2.BinaryOperatorExpression_1_0 returns NilConstant
	 *     ExpressionLevel3 returns NilConstant
	 *     ExpressionLevel3.BinaryOperatorExpression_1_0 returns NilConstant
	 *     ExpressionLevel4 returns NilConstant
	 *     ExpressionLevel4.BinaryOperatorExpression_1_0 returns NilConstant
	 *     ExpressionLevel5 returns NilConstant
	 *     ExpressionLevel5.DataMethodCallExpression_1_0 returns NilConstant
	 *     ExpressionLevel6 returns NilConstant
	 *     ExpressionLevel7 returns NilConstant
	 *     ExpressionConstant returns NilConstant
	 *     NilConstant returns NilConstant
	 *     NonIDStartWithinStatementExpressionSingle returns NilConstant
	 *     NonIDStartWithinStatementExpressionLevel1 returns NilConstant
	 *     NonIDStartWithinStatementExpressionLevel2 returns NilConstant
	 *     NonIDStartWithinStatementExpressionLevel2.BinaryOperatorExpression_1_0 returns NilConstant
	 *     NonIDStartWithinStatementExpressionLevel3 returns NilConstant
	 *     NonIDStartWithinStatementExpressionLevel3.BinaryOperatorExpression_1_0 returns NilConstant
	 *     NonIDStartWithinStatementExpressionLevel4 returns NilConstant
	 *     NonIDStartWithinStatementExpressionLevel4.BinaryOperatorExpression_1_0 returns NilConstant
	 *     NonIDStartWithinStatementExpressionLevel5 returns NilConstant
	 *     NonIDStartWithinStatementExpressionLevel5.DataMethodCallExpression_1_0 returns NilConstant
	 *     NonIDStartWithinStatementExpressionLevel6 returns NilConstant
	 *     NonIDStartWithinStatementExpressionLevel7 returns NilConstant
	 *
	 * Constraint:
	 *     {NilConstant}
	 */
	protected void sequence_NilConstant(ISerializationContext context, NilConstant semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     NonIDStartWithinStatementExpressionSingle returns ReturnExpression
	 *     NonIDStartWithinStatementExpressionLevel1 returns ReturnExpression
	 *
	 * Constraint:
	 *     expression=ExpressionLevel1
	 */
	protected void sequence_NonIDStartWithinStatementExpressionLevel1(ISerializationContext context, ReturnExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.RETURN_EXPRESSION__EXPRESSION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.RETURN_EXPRESSION__EXPRESSION));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Access().getExpressionExpressionLevel1ParserRuleCall_1_2_0(), semanticObject.getExpression());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     NonIDStartWithinStatementExpressionSingle returns BinaryOperatorExpression
	 *     NonIDStartWithinStatementExpressionLevel1 returns BinaryOperatorExpression
	 *     NonIDStartWithinStatementExpressionLevel2 returns BinaryOperatorExpression
	 *     NonIDStartWithinStatementExpressionLevel2.BinaryOperatorExpression_1_0 returns BinaryOperatorExpression
	 *
	 * Constraint:
	 *     (
	 *         (leftOperand=NonIDStartWithinStatementExpressionLevel2_BinaryOperatorExpression_1_0 name=BinaryOperatorLevel2 rightOperand=ExpressionLevel3) | 
	 *         (leftOperand=NonIDStartWithinStatementExpressionLevel3_BinaryOperatorExpression_1_0 name=BinaryOperatorLevel3 rightOperand=ExpressionLevel4) | 
	 *         (leftOperand=NonIDStartWithinStatementExpressionLevel4_BinaryOperatorExpression_1_0 name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5)
	 *     )
	 */
	protected void sequence_NonIDStartWithinStatementExpressionLevel2_NonIDStartWithinStatementExpressionLevel3_NonIDStartWithinStatementExpressionLevel4(ISerializationContext context, BinaryOperatorExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     NonIDStartWithinStatementExpressionLevel3 returns BinaryOperatorExpression
	 *     NonIDStartWithinStatementExpressionLevel3.BinaryOperatorExpression_1_0 returns BinaryOperatorExpression
	 *
	 * Constraint:
	 *     (
	 *         (leftOperand=NonIDStartWithinStatementExpressionLevel3_BinaryOperatorExpression_1_0 name=BinaryOperatorLevel3 rightOperand=ExpressionLevel4) | 
	 *         (leftOperand=NonIDStartWithinStatementExpressionLevel4_BinaryOperatorExpression_1_0 name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5)
	 *     )
	 */
	protected void sequence_NonIDStartWithinStatementExpressionLevel3_NonIDStartWithinStatementExpressionLevel4(ISerializationContext context, BinaryOperatorExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     NonIDStartWithinStatementExpressionLevel4 returns BinaryOperatorExpression
	 *     NonIDStartWithinStatementExpressionLevel4.BinaryOperatorExpression_1_0 returns BinaryOperatorExpression
	 *
	 * Constraint:
	 *     (leftOperand=NonIDStartWithinStatementExpressionLevel4_BinaryOperatorExpression_1_0 name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5)
	 */
	protected void sequence_NonIDStartWithinStatementExpressionLevel4(ISerializationContext context, BinaryOperatorExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__LEFT_OPERAND) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__LEFT_OPERAND));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__NAME));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__RIGHT_OPERAND) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.BINARY_OPERATOR_EXPRESSION__RIGHT_OPERAND));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_0(), semanticObject.getLeftOperand());
		feeder.accept(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getNameBinaryOperatorLevel4EnumRuleCall_1_1_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getRightOperandExpressionLevel5ParserRuleCall_1_2_0(), semanticObject.getRightOperand());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     NonIDStartWithinStatementExpressionSingle returns DataMethodCallExpression
	 *     NonIDStartWithinStatementExpressionLevel1 returns DataMethodCallExpression
	 *     NonIDStartWithinStatementExpressionLevel2 returns DataMethodCallExpression
	 *     NonIDStartWithinStatementExpressionLevel2.BinaryOperatorExpression_1_0 returns DataMethodCallExpression
	 *     NonIDStartWithinStatementExpressionLevel3 returns DataMethodCallExpression
	 *     NonIDStartWithinStatementExpressionLevel3.BinaryOperatorExpression_1_0 returns DataMethodCallExpression
	 *     NonIDStartWithinStatementExpressionLevel4 returns DataMethodCallExpression
	 *     NonIDStartWithinStatementExpressionLevel4.BinaryOperatorExpression_1_0 returns DataMethodCallExpression
	 *     NonIDStartWithinStatementExpressionLevel5 returns DataMethodCallExpression
	 *     NonIDStartWithinStatementExpressionLevel5.DataMethodCallExpression_1_0 returns DataMethodCallExpression
	 *
	 * Constraint:
	 *     (
	 *         target=NonIDStartWithinStatementExpressionLevel5_DataMethodCallExpression_1_0 
	 *         onSuperClass?='^'? 
	 *         name=IDENTIFIER 
	 *         (arguments+=Expression arguments+=Expression*)?
	 *     )
	 */
	protected void sequence_NonIDStartWithinStatementExpressionLevel5(ISerializationContext context, DataMethodCallExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     OutputVariable returns OutputVariable
	 *
	 * Constraint:
	 *     variable=IDENTIFIER
	 */
	protected void sequence_OutputVariable(ISerializationContext context, OutputVariable semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.OUTPUT_VARIABLE__VARIABLE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.OUTPUT_VARIABLE__VARIABLE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getOutputVariableAccess().getVariableIDENTIFIERParserRuleCall_0(), semanticObject.getVariable());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Statement returns ParStatement
	 *     Statement.StatementSequence_1_0 returns ParStatement
	 *     StatementSingle returns ParStatement
	 *     ParStatement returns ParStatement
	 *
	 * Constraint:
	 *     (clauses+=Statement clauses+=Statement*)
	 */
	protected void sequence_ParStatement(ISerializationContext context, ParStatement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Poosl returns Poosl
	 *
	 * Constraint:
	 *     (
	 *         (imports+=Import | importLibs+=ImportLib)* 
	 *         dataClasses+=DataClass? 
	 *         ((processClasses+=ProcessClass | clusterClasses+=ClusterClass)? dataClasses+=DataClass?)* 
	 *         (clusterClasses+=System (dataClasses+=DataClass | processClasses+=ProcessClass | clusterClasses+=ClusterClass)*)?
	 *     )
	 */
	protected void sequence_Poosl(ISerializationContext context, Poosl semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     PortDescriptor returns PortReference
	 *
	 * Constraint:
	 *     port=IDENTIFIER
	 */
	protected void sequence_PortDescriptor(ISerializationContext context, PortReference semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.PORT_REFERENCE__PORT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.PORT_REFERENCE__PORT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getPortDescriptorAccess().getPortIDENTIFIERParserRuleCall_1_0(), semanticObject.getPort());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     PortReference returns PortReference
	 *
	 * Constraint:
	 *     port=IDENTIFIER
	 */
	protected void sequence_PortReference(ISerializationContext context, PortReference semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.PORT_REFERENCE__PORT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.PORT_REFERENCE__PORT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getPortReferenceAccess().getPortIDENTIFIERParserRuleCall_0(), semanticObject.getPort());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Port returns Port
	 *
	 * Constraint:
	 *     name=IDENTIFIER
	 */
	protected void sequence_Port(ISerializationContext context, Port semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.PORT__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.PORT__NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getPortAccess().getNameIDENTIFIERParserRuleCall_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     ProcessClass returns ProcessClass
	 *
	 * Constraint:
	 *     (
	 *         annotations+=Annotation* 
	 *         name=IDENTIFIER 
	 *         (parameters+=Declaration parameters+=Declaration*)? 
	 *         superClass=IDENTIFIER? 
	 *         (ports+=Port ports+=Port*)? 
	 *         (
	 *             (receiveMessages+=MessageReceiveSignature | sendMessages+=MessageSendSignature) 
	 *             receiveMessages+=MessageReceiveSignature? 
	 *             (sendMessages+=MessageSendSignature? receiveMessages+=MessageReceiveSignature?)*
	 *         )? 
	 *         (instanceVariables+=Declaration instanceVariables+=Declaration*)? 
	 *         initialMethodCall=ProcessMethodCall? 
	 *         methods+=ProcessMethod*
	 *     )
	 */
	protected void sequence_ProcessClass(ISerializationContext context, ProcessClass semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Statement returns ProcessMethodCall
	 *     Statement.StatementSequence_1_0 returns ProcessMethodCall
	 *     StatementSingle returns ProcessMethodCall
	 *     ProcessMethodCall returns ProcessMethodCall
	 *
	 * Constraint:
	 *     (
	 *         method=IDENTIFIER 
	 *         (inputArguments+=Expression inputArguments+=Expression*)? 
	 *         (outputVariables+=OutputVariable outputVariables+=OutputVariable*)?
	 *     )
	 */
	protected void sequence_ProcessMethodCall(ISerializationContext context, ProcessMethodCall semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ProcessMethod returns ProcessMethod
	 *
	 * Constraint:
	 *     (
	 *         annotations+=Annotation* 
	 *         name=IDENTIFIER 
	 *         (inputParameters+=Declaration inputParameters+=Declaration*)? 
	 *         (outputParameters+=Declaration outputParameters+=Declaration*)? 
	 *         (localVariables+=Declaration localVariables+=Declaration*)? 
	 *         body=Statement
	 *     )
	 */
	protected void sequence_ProcessMethod(ISerializationContext context, ProcessMethod semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Expression returns RealConstant
	 *     Expression.ExpressionSequence_1_0 returns RealConstant
	 *     ExpressionSingle returns RealConstant
	 *     ExpressionLevel1 returns RealConstant
	 *     ExpressionLevel2 returns RealConstant
	 *     ExpressionLevel2.BinaryOperatorExpression_1_0 returns RealConstant
	 *     ExpressionLevel3 returns RealConstant
	 *     ExpressionLevel3.BinaryOperatorExpression_1_0 returns RealConstant
	 *     ExpressionLevel4 returns RealConstant
	 *     ExpressionLevel4.BinaryOperatorExpression_1_0 returns RealConstant
	 *     ExpressionLevel5 returns RealConstant
	 *     ExpressionLevel5.DataMethodCallExpression_1_0 returns RealConstant
	 *     ExpressionLevel6 returns RealConstant
	 *     ExpressionLevel7 returns RealConstant
	 *     ExpressionConstant returns RealConstant
	 *     RealConstant returns RealConstant
	 *     NonIDStartWithinStatementExpressionSingle returns RealConstant
	 *     NonIDStartWithinStatementExpressionLevel1 returns RealConstant
	 *     NonIDStartWithinStatementExpressionLevel2 returns RealConstant
	 *     NonIDStartWithinStatementExpressionLevel2.BinaryOperatorExpression_1_0 returns RealConstant
	 *     NonIDStartWithinStatementExpressionLevel3 returns RealConstant
	 *     NonIDStartWithinStatementExpressionLevel3.BinaryOperatorExpression_1_0 returns RealConstant
	 *     NonIDStartWithinStatementExpressionLevel4 returns RealConstant
	 *     NonIDStartWithinStatementExpressionLevel4.BinaryOperatorExpression_1_0 returns RealConstant
	 *     NonIDStartWithinStatementExpressionLevel5 returns RealConstant
	 *     NonIDStartWithinStatementExpressionLevel5.DataMethodCallExpression_1_0 returns RealConstant
	 *     NonIDStartWithinStatementExpressionLevel6 returns RealConstant
	 *     NonIDStartWithinStatementExpressionLevel7 returns RealConstant
	 *
	 * Constraint:
	 *     value=REAL
	 */
	protected void sequence_RealConstant(ISerializationContext context, RealConstant semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.REAL_CONSTANT__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.REAL_CONSTANT__VALUE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getRealConstantAccess().getValueREALParserRuleCall_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Statement returns ReceiveStatement
	 *     Statement.StatementSequence_1_0 returns ReceiveStatement
	 *     StatementSingle returns ReceiveStatement
	 *     ReceiveStatement returns ReceiveStatement
	 *
	 * Constraint:
	 *     (
	 *         portDescriptor=PortDescriptor 
	 *         name=IDENTIFIER 
	 *         (variables+=OutputVariable variables+=OutputVariable*)? 
	 *         receptionCondition=Expression? 
	 *         postCommunicationExpression=Expression?
	 *     )
	 */
	protected void sequence_ReceiveStatement(ISerializationContext context, ReceiveStatement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Statement returns SelStatement
	 *     Statement.StatementSequence_1_0 returns SelStatement
	 *     StatementSingle returns SelStatement
	 *     SelStatement returns SelStatement
	 *
	 * Constraint:
	 *     (clauses+=Statement clauses+=Statement*)
	 */
	protected void sequence_SelStatement(ISerializationContext context, SelStatement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Expression returns SelfExpression
	 *     Expression.ExpressionSequence_1_0 returns SelfExpression
	 *     ExpressionSingle returns SelfExpression
	 *     ExpressionLevel1 returns SelfExpression
	 *     ExpressionLevel2 returns SelfExpression
	 *     ExpressionLevel2.BinaryOperatorExpression_1_0 returns SelfExpression
	 *     ExpressionLevel3 returns SelfExpression
	 *     ExpressionLevel3.BinaryOperatorExpression_1_0 returns SelfExpression
	 *     ExpressionLevel4 returns SelfExpression
	 *     ExpressionLevel4.BinaryOperatorExpression_1_0 returns SelfExpression
	 *     ExpressionLevel5 returns SelfExpression
	 *     ExpressionLevel5.DataMethodCallExpression_1_0 returns SelfExpression
	 *     ExpressionLevel6 returns SelfExpression
	 *     ExpressionLevel7 returns SelfExpression
	 *     SelfExpression returns SelfExpression
	 *     NonIDStartWithinStatementExpressionSingle returns SelfExpression
	 *     NonIDStartWithinStatementExpressionLevel1 returns SelfExpression
	 *     NonIDStartWithinStatementExpressionLevel2 returns SelfExpression
	 *     NonIDStartWithinStatementExpressionLevel2.BinaryOperatorExpression_1_0 returns SelfExpression
	 *     NonIDStartWithinStatementExpressionLevel3 returns SelfExpression
	 *     NonIDStartWithinStatementExpressionLevel3.BinaryOperatorExpression_1_0 returns SelfExpression
	 *     NonIDStartWithinStatementExpressionLevel4 returns SelfExpression
	 *     NonIDStartWithinStatementExpressionLevel4.BinaryOperatorExpression_1_0 returns SelfExpression
	 *     NonIDStartWithinStatementExpressionLevel5 returns SelfExpression
	 *     NonIDStartWithinStatementExpressionLevel5.DataMethodCallExpression_1_0 returns SelfExpression
	 *     NonIDStartWithinStatementExpressionLevel6 returns SelfExpression
	 *     NonIDStartWithinStatementExpressionLevel7 returns SelfExpression
	 *
	 * Constraint:
	 *     {SelfExpression}
	 */
	protected void sequence_SelfExpression(ISerializationContext context, SelfExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Statement returns SendStatement
	 *     Statement.StatementSequence_1_0 returns SendStatement
	 *     StatementSingle returns SendStatement
	 *     SendStatement returns SendStatement
	 *
	 * Constraint:
	 *     (portDescriptor=PortDescriptor name=IDENTIFIER (arguments+=Expression arguments+=Expression*)? postCommunicationExpression=Expression?)
	 */
	protected void sequence_SendStatement(ISerializationContext context, SendStatement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Statement returns SkipStatement
	 *     Statement.StatementSequence_1_0 returns SkipStatement
	 *     StatementSingle returns SkipStatement
	 *     SkipStatement returns SkipStatement
	 *
	 * Constraint:
	 *     {SkipStatement}
	 */
	protected void sequence_SkipStatement(ISerializationContext context, SkipStatement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Statement returns StatementSequenceRoundBracket
	 *     Statement.StatementSequence_1_0 returns StatementSequenceRoundBracket
	 *     StatementSingle returns StatementSequenceRoundBracket
	 *     BracketStartStatement returns StatementSequenceRoundBracket
	 *     StatementSequenceRoundBracket returns StatementSequenceRoundBracket
	 *
	 * Constraint:
	 *     (statements+=StatementSingle statements+=StatementSingle*)
	 */
	protected void sequence_StatementSequenceRoundBracket(ISerializationContext context, StatementSequenceRoundBracket semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Statement returns StatementSequence
	 *
	 * Constraint:
	 *     (statements+=Statement_StatementSequence_1_0 statements+=StatementSingle+)
	 */
	protected void sequence_Statement(ISerializationContext context, StatementSequence semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Expression returns StringConstant
	 *     Expression.ExpressionSequence_1_0 returns StringConstant
	 *     ExpressionSingle returns StringConstant
	 *     ExpressionLevel1 returns StringConstant
	 *     ExpressionLevel2 returns StringConstant
	 *     ExpressionLevel2.BinaryOperatorExpression_1_0 returns StringConstant
	 *     ExpressionLevel3 returns StringConstant
	 *     ExpressionLevel3.BinaryOperatorExpression_1_0 returns StringConstant
	 *     ExpressionLevel4 returns StringConstant
	 *     ExpressionLevel4.BinaryOperatorExpression_1_0 returns StringConstant
	 *     ExpressionLevel5 returns StringConstant
	 *     ExpressionLevel5.DataMethodCallExpression_1_0 returns StringConstant
	 *     ExpressionLevel6 returns StringConstant
	 *     ExpressionLevel7 returns StringConstant
	 *     ExpressionConstant returns StringConstant
	 *     StringConstant returns StringConstant
	 *     NonIDStartWithinStatementExpressionSingle returns StringConstant
	 *     NonIDStartWithinStatementExpressionLevel1 returns StringConstant
	 *     NonIDStartWithinStatementExpressionLevel2 returns StringConstant
	 *     NonIDStartWithinStatementExpressionLevel2.BinaryOperatorExpression_1_0 returns StringConstant
	 *     NonIDStartWithinStatementExpressionLevel3 returns StringConstant
	 *     NonIDStartWithinStatementExpressionLevel3.BinaryOperatorExpression_1_0 returns StringConstant
	 *     NonIDStartWithinStatementExpressionLevel4 returns StringConstant
	 *     NonIDStartWithinStatementExpressionLevel4.BinaryOperatorExpression_1_0 returns StringConstant
	 *     NonIDStartWithinStatementExpressionLevel5 returns StringConstant
	 *     NonIDStartWithinStatementExpressionLevel5.DataMethodCallExpression_1_0 returns StringConstant
	 *     NonIDStartWithinStatementExpressionLevel6 returns StringConstant
	 *     NonIDStartWithinStatementExpressionLevel7 returns StringConstant
	 *
	 * Constraint:
	 *     value=POOSL_STRING
	 */
	protected void sequence_StringConstant(ISerializationContext context, StringConstant semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.STRING_CONSTANT__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.STRING_CONSTANT__VALUE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getStringConstantAccess().getValuePOOSL_STRINGTerminalRuleCall_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     SwitchExpressionCase returns SwitchExpressionCase
	 *
	 * Constraint:
	 *     (value=Expression body=Expression)
	 */
	protected void sequence_SwitchExpressionCase(ISerializationContext context, SwitchExpressionCase semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.SWITCH_EXPRESSION_CASE__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.SWITCH_EXPRESSION_CASE__VALUE));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.SWITCH_EXPRESSION_CASE__BODY) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.SWITCH_EXPRESSION_CASE__BODY));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getSwitchExpressionCaseAccess().getValueExpressionParserRuleCall_1_0(), semanticObject.getValue());
		feeder.accept(grammarAccess.getSwitchExpressionCaseAccess().getBodyExpressionParserRuleCall_3_0(), semanticObject.getBody());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Expression returns SwitchExpression
	 *     Expression.ExpressionSequence_1_0 returns SwitchExpression
	 *     ExpressionSingle returns SwitchExpression
	 *     ExpressionLevel1 returns SwitchExpression
	 *     ExpressionLevel2 returns SwitchExpression
	 *     ExpressionLevel2.BinaryOperatorExpression_1_0 returns SwitchExpression
	 *     ExpressionLevel3 returns SwitchExpression
	 *     ExpressionLevel3.BinaryOperatorExpression_1_0 returns SwitchExpression
	 *     ExpressionLevel4 returns SwitchExpression
	 *     ExpressionLevel4.BinaryOperatorExpression_1_0 returns SwitchExpression
	 *     ExpressionLevel5 returns SwitchExpression
	 *     ExpressionLevel5.DataMethodCallExpression_1_0 returns SwitchExpression
	 *     ExpressionLevel6 returns SwitchExpression
	 *     SwitchExpression returns SwitchExpression
	 *
	 * Constraint:
	 *     (expression=Expression ((cases+=SwitchExpressionCase+ defaultBody=Expression?) | defaultBody=Expression))
	 */
	protected void sequence_SwitchExpression(ISerializationContext context, SwitchExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     SwitchStatementCase returns SwitchStatementCase
	 *
	 * Constraint:
	 *     (value=Expression body=Statement)
	 */
	protected void sequence_SwitchStatementCase(ISerializationContext context, SwitchStatementCase semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.SWITCH_STATEMENT_CASE__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.SWITCH_STATEMENT_CASE__VALUE));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.SWITCH_STATEMENT_CASE__BODY) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.SWITCH_STATEMENT_CASE__BODY));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getSwitchStatementCaseAccess().getValueExpressionParserRuleCall_1_0(), semanticObject.getValue());
		feeder.accept(grammarAccess.getSwitchStatementCaseAccess().getBodyStatementParserRuleCall_3_0(), semanticObject.getBody());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Statement returns SwitchStatement
	 *     Statement.StatementSequence_1_0 returns SwitchStatement
	 *     StatementSingle returns SwitchStatement
	 *     SwitchStatement returns SwitchStatement
	 *
	 * Constraint:
	 *     (expression=Expression ((cases+=SwitchStatementCase+ defaultBody=Statement?) | defaultBody=Statement))
	 */
	protected void sequence_SwitchStatement(ISerializationContext context, SwitchStatement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     System returns ClusterClass
	 *
	 * Constraint:
	 *     (annotations+=Annotation* (parameters+=Declaration parameters+=Declaration*)? (ports+=Port ports+=Port*)? instances+=Instance* channels+=Channel*)
	 */
	protected void sequence_System(ISerializationContext context, ClusterClass semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Expression returns UnaryOperatorExpression
	 *     Expression.ExpressionSequence_1_0 returns UnaryOperatorExpression
	 *     ExpressionSingle returns UnaryOperatorExpression
	 *     ExpressionLevel1 returns UnaryOperatorExpression
	 *     ExpressionLevel2 returns UnaryOperatorExpression
	 *     ExpressionLevel2.BinaryOperatorExpression_1_0 returns UnaryOperatorExpression
	 *     ExpressionLevel3 returns UnaryOperatorExpression
	 *     ExpressionLevel3.BinaryOperatorExpression_1_0 returns UnaryOperatorExpression
	 *     ExpressionLevel4 returns UnaryOperatorExpression
	 *     ExpressionLevel4.BinaryOperatorExpression_1_0 returns UnaryOperatorExpression
	 *     ExpressionLevel5 returns UnaryOperatorExpression
	 *     ExpressionLevel5.DataMethodCallExpression_1_0 returns UnaryOperatorExpression
	 *     ExpressionLevel6 returns UnaryOperatorExpression
	 *     UnaryOperatorExpression returns UnaryOperatorExpression
	 *     NonIDStartWithinStatementExpressionSingle returns UnaryOperatorExpression
	 *     NonIDStartWithinStatementExpressionLevel1 returns UnaryOperatorExpression
	 *     NonIDStartWithinStatementExpressionLevel2 returns UnaryOperatorExpression
	 *     NonIDStartWithinStatementExpressionLevel2.BinaryOperatorExpression_1_0 returns UnaryOperatorExpression
	 *     NonIDStartWithinStatementExpressionLevel3 returns UnaryOperatorExpression
	 *     NonIDStartWithinStatementExpressionLevel3.BinaryOperatorExpression_1_0 returns UnaryOperatorExpression
	 *     NonIDStartWithinStatementExpressionLevel4 returns UnaryOperatorExpression
	 *     NonIDStartWithinStatementExpressionLevel4.BinaryOperatorExpression_1_0 returns UnaryOperatorExpression
	 *     NonIDStartWithinStatementExpressionLevel5 returns UnaryOperatorExpression
	 *     NonIDStartWithinStatementExpressionLevel5.DataMethodCallExpression_1_0 returns UnaryOperatorExpression
	 *     NonIDStartWithinStatementExpressionLevel6 returns UnaryOperatorExpression
	 *
	 * Constraint:
	 *     (name=OperatorUnary operand=ExpressionLevel7)
	 */
	protected void sequence_UnaryOperatorExpression(ISerializationContext context, UnaryOperatorExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.UNARY_OPERATOR_EXPRESSION__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.UNARY_OPERATOR_EXPRESSION__NAME));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.UNARY_OPERATOR_EXPRESSION__OPERAND) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.UNARY_OPERATOR_EXPRESSION__OPERAND));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getUnaryOperatorExpressionAccess().getNameOperatorUnaryEnumRuleCall_0_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getUnaryOperatorExpressionAccess().getOperandExpressionLevel7ParserRuleCall_1_0(), semanticObject.getOperand());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Expression returns VariableExpression
	 *     Expression.ExpressionSequence_1_0 returns VariableExpression
	 *     ExpressionSingle returns VariableExpression
	 *     ExpressionLevel1 returns VariableExpression
	 *     ExpressionLevel2 returns VariableExpression
	 *     ExpressionLevel2.BinaryOperatorExpression_1_0 returns VariableExpression
	 *     ExpressionLevel3 returns VariableExpression
	 *     ExpressionLevel3.BinaryOperatorExpression_1_0 returns VariableExpression
	 *     ExpressionLevel4 returns VariableExpression
	 *     ExpressionLevel4.BinaryOperatorExpression_1_0 returns VariableExpression
	 *     ExpressionLevel5 returns VariableExpression
	 *     ExpressionLevel5.DataMethodCallExpression_1_0 returns VariableExpression
	 *     ExpressionLevel6 returns VariableExpression
	 *     ExpressionLevel7 returns VariableExpression
	 *     VariableExpression returns VariableExpression
	 *     IDStartWithinStatementExpressionSingle returns VariableExpression
	 *     IDStartWithinStatementExpressionLevel1 returns VariableExpression
	 *     IDStartWithinStatementExpressionLevel2 returns VariableExpression
	 *     IDStartWithinStatementExpressionLevel2.BinaryOperatorExpression_1_0 returns VariableExpression
	 *     IDStartWithinStatementExpressionLevel3 returns VariableExpression
	 *     IDStartWithinStatementExpressionLevel3.BinaryOperatorExpression_1_0 returns VariableExpression
	 *     IDStartWithinStatementExpressionLevel4 returns VariableExpression
	 *     IDStartWithinStatementExpressionLevel4.BinaryOperatorExpression_1_0 returns VariableExpression
	 *     IDStartWithinStatementExpressionLevel5 returns VariableExpression
	 *     IDStartWithinStatementExpressionLevel5.DataMethodCallExpression_1_0 returns VariableExpression
	 *
	 * Constraint:
	 *     variable=IDENTIFIER
	 */
	protected void sequence_VariableExpression(ISerializationContext context, VariableExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.VARIABLE_EXPRESSION__VARIABLE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.VARIABLE_EXPRESSION__VARIABLE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getVariableExpressionAccess().getVariableIDENTIFIERParserRuleCall_0(), semanticObject.getVariable());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Variable returns Variable
	 *
	 * Constraint:
	 *     name=IDENTIFIER
	 */
	protected void sequence_Variable(ISerializationContext context, Variable semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.VARIABLE__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.VARIABLE__NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getVariableAccess().getNameIDENTIFIERParserRuleCall_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Expression returns WhileExpression
	 *     Expression.ExpressionSequence_1_0 returns WhileExpression
	 *     ExpressionSingle returns WhileExpression
	 *     ExpressionLevel1 returns WhileExpression
	 *     ExpressionLevel2 returns WhileExpression
	 *     ExpressionLevel2.BinaryOperatorExpression_1_0 returns WhileExpression
	 *     ExpressionLevel3 returns WhileExpression
	 *     ExpressionLevel3.BinaryOperatorExpression_1_0 returns WhileExpression
	 *     ExpressionLevel4 returns WhileExpression
	 *     ExpressionLevel4.BinaryOperatorExpression_1_0 returns WhileExpression
	 *     ExpressionLevel5 returns WhileExpression
	 *     ExpressionLevel5.DataMethodCallExpression_1_0 returns WhileExpression
	 *     ExpressionLevel6 returns WhileExpression
	 *     WhileExpression returns WhileExpression
	 *
	 * Constraint:
	 *     (condition=Expression body=Expression)
	 */
	protected void sequence_WhileExpression(ISerializationContext context, WhileExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.WHILE_EXPRESSION__CONDITION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.WHILE_EXPRESSION__CONDITION));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.WHILE_EXPRESSION__BODY) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.WHILE_EXPRESSION__BODY));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getWhileExpressionAccess().getConditionExpressionParserRuleCall_1_0(), semanticObject.getCondition());
		feeder.accept(grammarAccess.getWhileExpressionAccess().getBodyExpressionParserRuleCall_3_0(), semanticObject.getBody());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Statement returns WhileStatement
	 *     Statement.StatementSequence_1_0 returns WhileStatement
	 *     StatementSingle returns WhileStatement
	 *     WhileStatement returns WhileStatement
	 *
	 * Constraint:
	 *     (condition=Expression body=Statement)
	 */
	protected void sequence_WhileStatement(ISerializationContext context, WhileStatement semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.WHILE_STATEMENT__CONDITION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.WHILE_STATEMENT__CONDITION));
			if (transientValues.isValueTransient(semanticObject, PooslPackage.Literals.WHILE_STATEMENT__BODY) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PooslPackage.Literals.WHILE_STATEMENT__BODY));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getWhileStatementAccess().getConditionExpressionParserRuleCall_1_0(), semanticObject.getCondition());
		feeder.accept(grammarAccess.getWhileStatementAccess().getBodyStatementParserRuleCall_3_0(), semanticObject.getBody());
		feeder.finish();
	}
	
	
}
