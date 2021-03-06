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
grammar InternalPoosl;

options {
	superClass=AbstractInternalContentAssistParser;
	backtrack=true;
}

@lexer::header {
package org.eclipse.poosl.xtext.ide.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;
}

@parser::header {
package org.eclipse.poosl.xtext.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import org.eclipse.poosl.xtext.services.PooslGrammarAccess;

}
@parser::members {
	private PooslGrammarAccess grammarAccess;

	public void setGrammarAccess(PooslGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}

	@Override
	protected Grammar getGrammar() {
		return grammarAccess.getGrammar();
	}

	@Override
	protected String getValueForTokenName(String tokenName) {
		return tokenName;
	}
}

// Entry rule entryRulePoosl
entryRulePoosl
:
{ before(grammarAccess.getPooslRule()); }
	 rulePoosl
{ after(grammarAccess.getPooslRule()); } 
	 EOF 
;

// Rule Poosl
rulePoosl 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getPooslAccess().getGroup()); }
		(rule__Poosl__Group__0)
		{ after(grammarAccess.getPooslAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleImport
entryRuleImport
:
{ before(grammarAccess.getImportRule()); }
	 ruleImport
{ after(grammarAccess.getImportRule()); } 
	 EOF 
;

// Rule Import
ruleImport 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getImportAccess().getGroup()); }
		(rule__Import__Group__0)
		{ after(grammarAccess.getImportAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleImportLib
entryRuleImportLib
:
{ before(grammarAccess.getImportLibRule()); }
	 ruleImportLib
{ after(grammarAccess.getImportLibRule()); } 
	 EOF 
;

// Rule ImportLib
ruleImportLib 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getImportLibAccess().getGroup()); }
		(rule__ImportLib__Group__0)
		{ after(grammarAccess.getImportLibAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleDataClass
entryRuleDataClass
:
{ before(grammarAccess.getDataClassRule()); }
	 ruleDataClass
{ after(grammarAccess.getDataClassRule()); } 
	 EOF 
;

// Rule DataClass
ruleDataClass 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getDataClassAccess().getGroup()); }
		(rule__DataClass__Group__0)
		{ after(grammarAccess.getDataClassAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleDataMethodNamed
entryRuleDataMethodNamed
:
{ before(grammarAccess.getDataMethodNamedRule()); }
	 ruleDataMethodNamed
{ after(grammarAccess.getDataMethodNamedRule()); } 
	 EOF 
;

// Rule DataMethodNamed
ruleDataMethodNamed 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getDataMethodNamedAccess().getGroup()); }
		(rule__DataMethodNamed__Group__0)
		{ after(grammarAccess.getDataMethodNamedAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleDataMethodBinaryOperator
entryRuleDataMethodBinaryOperator
:
{ before(grammarAccess.getDataMethodBinaryOperatorRule()); }
	 ruleDataMethodBinaryOperator
{ after(grammarAccess.getDataMethodBinaryOperatorRule()); } 
	 EOF 
;

// Rule DataMethodBinaryOperator
ruleDataMethodBinaryOperator 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getGroup()); }
		(rule__DataMethodBinaryOperator__Group__0)
		{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleDataMethodUnaryOperator
entryRuleDataMethodUnaryOperator
:
{ before(grammarAccess.getDataMethodUnaryOperatorRule()); }
	 ruleDataMethodUnaryOperator
{ after(grammarAccess.getDataMethodUnaryOperatorRule()); } 
	 EOF 
;

// Rule DataMethodUnaryOperator
ruleDataMethodUnaryOperator 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getGroup()); }
		(rule__DataMethodUnaryOperator__Group__0)
		{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleDeclaration
entryRuleDeclaration
:
{ before(grammarAccess.getDeclarationRule()); }
	 ruleDeclaration
{ after(grammarAccess.getDeclarationRule()); } 
	 EOF 
;

// Rule Declaration
ruleDeclaration 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getDeclarationAccess().getGroup()); }
		(rule__Declaration__Group__0)
		{ after(grammarAccess.getDeclarationAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleVariable
entryRuleVariable
:
{ before(grammarAccess.getVariableRule()); }
	 ruleVariable
{ after(grammarAccess.getVariableRule()); } 
	 EOF 
;

// Rule Variable
ruleVariable 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getVariableAccess().getNameAssignment()); }
		(rule__Variable__NameAssignment)
		{ after(grammarAccess.getVariableAccess().getNameAssignment()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleAnnotation
entryRuleAnnotation
:
{ before(grammarAccess.getAnnotationRule()); }
	 ruleAnnotation
{ after(grammarAccess.getAnnotationRule()); } 
	 EOF 
;

// Rule Annotation
ruleAnnotation 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getAnnotationAccess().getGroup()); }
		(rule__Annotation__Group__0)
		{ after(grammarAccess.getAnnotationAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleProcessClass
entryRuleProcessClass
:
{ before(grammarAccess.getProcessClassRule()); }
	 ruleProcessClass
{ after(grammarAccess.getProcessClassRule()); } 
	 EOF 
;

// Rule ProcessClass
ruleProcessClass 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getProcessClassAccess().getGroup()); }
		(rule__ProcessClass__Group__0)
		{ after(grammarAccess.getProcessClassAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleProcessMethod
entryRuleProcessMethod
:
{ before(grammarAccess.getProcessMethodRule()); }
	 ruleProcessMethod
{ after(grammarAccess.getProcessMethodRule()); } 
	 EOF 
;

// Rule ProcessMethod
ruleProcessMethod 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getProcessMethodAccess().getGroup()); }
		(rule__ProcessMethod__Group__0)
		{ after(grammarAccess.getProcessMethodAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRulePort
entryRulePort
:
{ before(grammarAccess.getPortRule()); }
	 rulePort
{ after(grammarAccess.getPortRule()); } 
	 EOF 
;

// Rule Port
rulePort 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getPortAccess().getNameAssignment()); }
		(rule__Port__NameAssignment)
		{ after(grammarAccess.getPortAccess().getNameAssignment()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleMessageReceiveSignature
entryRuleMessageReceiveSignature
:
{ before(grammarAccess.getMessageReceiveSignatureRule()); }
	 ruleMessageReceiveSignature
{ after(grammarAccess.getMessageReceiveSignatureRule()); } 
	 EOF 
;

// Rule MessageReceiveSignature
ruleMessageReceiveSignature 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getMessageReceiveSignatureAccess().getGroup()); }
		(rule__MessageReceiveSignature__Group__0)
		{ after(grammarAccess.getMessageReceiveSignatureAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleMessageSendSignature
entryRuleMessageSendSignature
:
{ before(grammarAccess.getMessageSendSignatureRule()); }
	 ruleMessageSendSignature
{ after(grammarAccess.getMessageSendSignatureRule()); } 
	 EOF 
;

// Rule MessageSendSignature
ruleMessageSendSignature 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getMessageSendSignatureAccess().getGroup()); }
		(rule__MessageSendSignature__Group__0)
		{ after(grammarAccess.getMessageSendSignatureAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleMessageParameter
entryRuleMessageParameter
:
{ before(grammarAccess.getMessageParameterRule()); }
	 ruleMessageParameter
{ after(grammarAccess.getMessageParameterRule()); } 
	 EOF 
;

// Rule MessageParameter
ruleMessageParameter 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getMessageParameterAccess().getTypeAssignment()); }
		(rule__MessageParameter__TypeAssignment)
		{ after(grammarAccess.getMessageParameterAccess().getTypeAssignment()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleSystem
entryRuleSystem
:
{ before(grammarAccess.getSystemRule()); }
	 ruleSystem
{ after(grammarAccess.getSystemRule()); } 
	 EOF 
;

// Rule System
ruleSystem 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getSystemAccess().getGroup()); }
		(rule__System__Group__0)
		{ after(grammarAccess.getSystemAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleClusterClass
entryRuleClusterClass
:
{ before(grammarAccess.getClusterClassRule()); }
	 ruleClusterClass
{ after(grammarAccess.getClusterClassRule()); } 
	 EOF 
;

// Rule ClusterClass
ruleClusterClass 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getClusterClassAccess().getGroup()); }
		(rule__ClusterClass__Group__0)
		{ after(grammarAccess.getClusterClassAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleInstance
entryRuleInstance
:
{ before(grammarAccess.getInstanceRule()); }
	 ruleInstance
{ after(grammarAccess.getInstanceRule()); } 
	 EOF 
;

// Rule Instance
ruleInstance 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getInstanceAccess().getGroup()); }
		(rule__Instance__Group__0)
		{ after(grammarAccess.getInstanceAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleInstanceParameter
entryRuleInstanceParameter
:
{ before(grammarAccess.getInstanceParameterRule()); }
	 ruleInstanceParameter
{ after(grammarAccess.getInstanceParameterRule()); } 
	 EOF 
;

// Rule InstanceParameter
ruleInstanceParameter 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getInstanceParameterAccess().getGroup()); }
		(rule__InstanceParameter__Group__0)
		{ after(grammarAccess.getInstanceParameterAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleChannel
entryRuleChannel
:
{ before(grammarAccess.getChannelRule()); }
	 ruleChannel
{ after(grammarAccess.getChannelRule()); } 
	 EOF 
;

// Rule Channel
ruleChannel 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getChannelAccess().getGroup()); }
		(rule__Channel__Group__0)
		{ after(grammarAccess.getChannelAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleInstancePort
entryRuleInstancePort
:
{ before(grammarAccess.getInstancePortRule()); }
	 ruleInstancePort
{ after(grammarAccess.getInstancePortRule()); } 
	 EOF 
;

// Rule InstancePort
ruleInstancePort 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getInstancePortAccess().getGroup()); }
		(rule__InstancePort__Group__0)
		{ after(grammarAccess.getInstancePortAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleExpression
entryRuleExpression
:
{ before(grammarAccess.getExpressionRule()); }
	 ruleExpression
{ after(grammarAccess.getExpressionRule()); } 
	 EOF 
;

// Rule Expression
ruleExpression 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getExpressionAccess().getGroup()); }
		(rule__Expression__Group__0)
		{ after(grammarAccess.getExpressionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleExpressionSingle
entryRuleExpressionSingle
:
{ before(grammarAccess.getExpressionSingleRule()); }
	 ruleExpressionSingle
{ after(grammarAccess.getExpressionSingleRule()); } 
	 EOF 
;

// Rule ExpressionSingle
ruleExpressionSingle 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getExpressionSingleAccess().getExpressionLevel1ParserRuleCall()); }
		ruleExpressionLevel1
		{ after(grammarAccess.getExpressionSingleAccess().getExpressionLevel1ParserRuleCall()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleExpressionLevel1
entryRuleExpressionLevel1
:
{ before(grammarAccess.getExpressionLevel1Rule()); }
	 ruleExpressionLevel1
{ after(grammarAccess.getExpressionLevel1Rule()); } 
	 EOF 
;

// Rule ExpressionLevel1
ruleExpressionLevel1 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getExpressionLevel1Access().getAlternatives()); }
		(rule__ExpressionLevel1__Alternatives)
		{ after(grammarAccess.getExpressionLevel1Access().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleExpressionLevel2
entryRuleExpressionLevel2
:
{ before(grammarAccess.getExpressionLevel2Rule()); }
	 ruleExpressionLevel2
{ after(grammarAccess.getExpressionLevel2Rule()); } 
	 EOF 
;

// Rule ExpressionLevel2
ruleExpressionLevel2 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getExpressionLevel2Access().getGroup()); }
		(rule__ExpressionLevel2__Group__0)
		{ after(grammarAccess.getExpressionLevel2Access().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleExpressionLevel3
entryRuleExpressionLevel3
:
{ before(grammarAccess.getExpressionLevel3Rule()); }
	 ruleExpressionLevel3
{ after(grammarAccess.getExpressionLevel3Rule()); } 
	 EOF 
;

// Rule ExpressionLevel3
ruleExpressionLevel3 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getExpressionLevel3Access().getGroup()); }
		(rule__ExpressionLevel3__Group__0)
		{ after(grammarAccess.getExpressionLevel3Access().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleExpressionLevel4
entryRuleExpressionLevel4
:
{ before(grammarAccess.getExpressionLevel4Rule()); }
	 ruleExpressionLevel4
{ after(grammarAccess.getExpressionLevel4Rule()); } 
	 EOF 
;

// Rule ExpressionLevel4
ruleExpressionLevel4 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getExpressionLevel4Access().getGroup()); }
		(rule__ExpressionLevel4__Group__0)
		{ after(grammarAccess.getExpressionLevel4Access().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleExpressionLevel5
entryRuleExpressionLevel5
:
{ before(grammarAccess.getExpressionLevel5Rule()); }
	 ruleExpressionLevel5
{ after(grammarAccess.getExpressionLevel5Rule()); } 
	 EOF 
;

// Rule ExpressionLevel5
ruleExpressionLevel5 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getExpressionLevel5Access().getGroup()); }
		(rule__ExpressionLevel5__Group__0)
		{ after(grammarAccess.getExpressionLevel5Access().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleExpressionLevel6
entryRuleExpressionLevel6
:
{ before(grammarAccess.getExpressionLevel6Rule()); }
	 ruleExpressionLevel6
{ after(grammarAccess.getExpressionLevel6Rule()); } 
	 EOF 
;

// Rule ExpressionLevel6
ruleExpressionLevel6 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getExpressionLevel6Access().getAlternatives()); }
		(rule__ExpressionLevel6__Alternatives)
		{ after(grammarAccess.getExpressionLevel6Access().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleUnaryOperatorExpression
entryRuleUnaryOperatorExpression
:
{ before(grammarAccess.getUnaryOperatorExpressionRule()); }
	 ruleUnaryOperatorExpression
{ after(grammarAccess.getUnaryOperatorExpressionRule()); } 
	 EOF 
;

// Rule UnaryOperatorExpression
ruleUnaryOperatorExpression 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getUnaryOperatorExpressionAccess().getGroup()); }
		(rule__UnaryOperatorExpression__Group__0)
		{ after(grammarAccess.getUnaryOperatorExpressionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleIfExpression
entryRuleIfExpression
:
{ before(grammarAccess.getIfExpressionRule()); }
	 ruleIfExpression
{ after(grammarAccess.getIfExpressionRule()); } 
	 EOF 
;

// Rule IfExpression
ruleIfExpression 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getIfExpressionAccess().getGroup()); }
		(rule__IfExpression__Group__0)
		{ after(grammarAccess.getIfExpressionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleWhileExpression
entryRuleWhileExpression
:
{ before(grammarAccess.getWhileExpressionRule()); }
	 ruleWhileExpression
{ after(grammarAccess.getWhileExpressionRule()); } 
	 EOF 
;

// Rule WhileExpression
ruleWhileExpression 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getWhileExpressionAccess().getGroup()); }
		(rule__WhileExpression__Group__0)
		{ after(grammarAccess.getWhileExpressionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleSwitchExpression
entryRuleSwitchExpression
:
{ before(grammarAccess.getSwitchExpressionRule()); }
	 ruleSwitchExpression
{ after(grammarAccess.getSwitchExpressionRule()); } 
	 EOF 
;

// Rule SwitchExpression
ruleSwitchExpression 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getSwitchExpressionAccess().getGroup()); }
		(rule__SwitchExpression__Group__0)
		{ after(grammarAccess.getSwitchExpressionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleSwitchExpressionCase
entryRuleSwitchExpressionCase
:
{ before(grammarAccess.getSwitchExpressionCaseRule()); }
	 ruleSwitchExpressionCase
{ after(grammarAccess.getSwitchExpressionCaseRule()); } 
	 EOF 
;

// Rule SwitchExpressionCase
ruleSwitchExpressionCase 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getSwitchExpressionCaseAccess().getGroup()); }
		(rule__SwitchExpressionCase__Group__0)
		{ after(grammarAccess.getSwitchExpressionCaseAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleExpressionLevel7
entryRuleExpressionLevel7
:
{ before(grammarAccess.getExpressionLevel7Rule()); }
	 ruleExpressionLevel7
{ after(grammarAccess.getExpressionLevel7Rule()); } 
	 EOF 
;

// Rule ExpressionLevel7
ruleExpressionLevel7 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getExpressionLevel7Access().getAlternatives()); }
		(rule__ExpressionLevel7__Alternatives)
		{ after(grammarAccess.getExpressionLevel7Access().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleCurrentTimeExpression
entryRuleCurrentTimeExpression
:
{ before(grammarAccess.getCurrentTimeExpressionRule()); }
	 ruleCurrentTimeExpression
{ after(grammarAccess.getCurrentTimeExpressionRule()); } 
	 EOF 
;

// Rule CurrentTimeExpression
ruleCurrentTimeExpression 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getCurrentTimeExpressionAccess().getGroup()); }
		(rule__CurrentTimeExpression__Group__0)
		{ after(grammarAccess.getCurrentTimeExpressionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleSelfExpression
entryRuleSelfExpression
:
{ before(grammarAccess.getSelfExpressionRule()); }
	 ruleSelfExpression
{ after(grammarAccess.getSelfExpressionRule()); } 
	 EOF 
;

// Rule SelfExpression
ruleSelfExpression 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getSelfExpressionAccess().getGroup()); }
		(rule__SelfExpression__Group__0)
		{ after(grammarAccess.getSelfExpressionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleExpressionConstant
entryRuleExpressionConstant
:
{ before(grammarAccess.getExpressionConstantRule()); }
	 ruleExpressionConstant
{ after(grammarAccess.getExpressionConstantRule()); } 
	 EOF 
;

// Rule ExpressionConstant
ruleExpressionConstant 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getExpressionConstantAccess().getAlternatives()); }
		(rule__ExpressionConstant__Alternatives)
		{ after(grammarAccess.getExpressionConstantAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleBooleanConstant
entryRuleBooleanConstant
:
{ before(grammarAccess.getBooleanConstantRule()); }
	 ruleBooleanConstant
{ after(grammarAccess.getBooleanConstantRule()); } 
	 EOF 
;

// Rule BooleanConstant
ruleBooleanConstant 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getBooleanConstantAccess().getAlternatives()); }
		(rule__BooleanConstant__Alternatives)
		{ after(grammarAccess.getBooleanConstantAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleCharacterConstant
entryRuleCharacterConstant
:
{ before(grammarAccess.getCharacterConstantRule()); }
	 ruleCharacterConstant
{ after(grammarAccess.getCharacterConstantRule()); } 
	 EOF 
;

// Rule CharacterConstant
ruleCharacterConstant 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getCharacterConstantAccess().getValueAssignment()); }
		(rule__CharacterConstant__ValueAssignment)
		{ after(grammarAccess.getCharacterConstantAccess().getValueAssignment()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleFloatConstant
entryRuleFloatConstant
:
{ before(grammarAccess.getFloatConstantRule()); }
	 ruleFloatConstant
{ after(grammarAccess.getFloatConstantRule()); } 
	 EOF 
;

// Rule FloatConstant
ruleFloatConstant 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getFloatConstantAccess().getValueAssignment()); }
		(rule__FloatConstant__ValueAssignment)
		{ after(grammarAccess.getFloatConstantAccess().getValueAssignment()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleIntegerConstant
entryRuleIntegerConstant
:
{ before(grammarAccess.getIntegerConstantRule()); }
	 ruleIntegerConstant
{ after(grammarAccess.getIntegerConstantRule()); } 
	 EOF 
;

// Rule IntegerConstant
ruleIntegerConstant 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getIntegerConstantAccess().getValueAssignment()); }
		(rule__IntegerConstant__ValueAssignment)
		{ after(grammarAccess.getIntegerConstantAccess().getValueAssignment()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleNilConstant
entryRuleNilConstant
:
{ before(grammarAccess.getNilConstantRule()); }
	 ruleNilConstant
{ after(grammarAccess.getNilConstantRule()); } 
	 EOF 
;

// Rule NilConstant
ruleNilConstant 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getNilConstantAccess().getGroup()); }
		(rule__NilConstant__Group__0)
		{ after(grammarAccess.getNilConstantAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleRealConstant
entryRuleRealConstant
:
{ before(grammarAccess.getRealConstantRule()); }
	 ruleRealConstant
{ after(grammarAccess.getRealConstantRule()); } 
	 EOF 
;

// Rule RealConstant
ruleRealConstant 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getRealConstantAccess().getValueAssignment()); }
		(rule__RealConstant__ValueAssignment)
		{ after(grammarAccess.getRealConstantAccess().getValueAssignment()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleStringConstant
entryRuleStringConstant
:
{ before(grammarAccess.getStringConstantRule()); }
	 ruleStringConstant
{ after(grammarAccess.getStringConstantRule()); } 
	 EOF 
;

// Rule StringConstant
ruleStringConstant 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getStringConstantAccess().getValueAssignment()); }
		(rule__StringConstant__ValueAssignment)
		{ after(grammarAccess.getStringConstantAccess().getValueAssignment()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleEnvironmentConstant
entryRuleEnvironmentConstant
:
{ before(grammarAccess.getEnvironmentConstantRule()); }
	 ruleEnvironmentConstant
{ after(grammarAccess.getEnvironmentConstantRule()); } 
	 EOF 
;

// Rule EnvironmentConstant
ruleEnvironmentConstant 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getEnvironmentConstantAccess().getValueAssignment()); }
		(rule__EnvironmentConstant__ValueAssignment)
		{ after(grammarAccess.getEnvironmentConstantAccess().getValueAssignment()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleOutputVariable
entryRuleOutputVariable
:
{ before(grammarAccess.getOutputVariableRule()); }
	 ruleOutputVariable
{ after(grammarAccess.getOutputVariableRule()); } 
	 EOF 
;

// Rule OutputVariable
ruleOutputVariable 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getOutputVariableAccess().getVariableAssignment()); }
		(rule__OutputVariable__VariableAssignment)
		{ after(grammarAccess.getOutputVariableAccess().getVariableAssignment()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleVariableExpression
entryRuleVariableExpression
:
{ before(grammarAccess.getVariableExpressionRule()); }
	 ruleVariableExpression
{ after(grammarAccess.getVariableExpressionRule()); } 
	 EOF 
;

// Rule VariableExpression
ruleVariableExpression 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getVariableExpressionAccess().getVariableAssignment()); }
		(rule__VariableExpression__VariableAssignment)
		{ after(grammarAccess.getVariableExpressionAccess().getVariableAssignment()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleNewExpression
entryRuleNewExpression
:
{ before(grammarAccess.getNewExpressionRule()); }
	 ruleNewExpression
{ after(grammarAccess.getNewExpressionRule()); } 
	 EOF 
;

// Rule NewExpression
ruleNewExpression 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getNewExpressionAccess().getGroup()); }
		(rule__NewExpression__Group__0)
		{ after(grammarAccess.getNewExpressionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleExpressionSequenceRoundBracket
entryRuleExpressionSequenceRoundBracket
:
{ before(grammarAccess.getExpressionSequenceRoundBracketRule()); }
	 ruleExpressionSequenceRoundBracket
{ after(grammarAccess.getExpressionSequenceRoundBracketRule()); } 
	 EOF 
;

// Rule ExpressionSequenceRoundBracket
ruleExpressionSequenceRoundBracket 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getExpressionSequenceRoundBracketAccess().getGroup()); }
		(rule__ExpressionSequenceRoundBracket__Group__0)
		{ after(grammarAccess.getExpressionSequenceRoundBracketAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleIDStartWithinStatementExpressionSingle
entryRuleIDStartWithinStatementExpressionSingle
:
{ before(grammarAccess.getIDStartWithinStatementExpressionSingleRule()); }
	 ruleIDStartWithinStatementExpressionSingle
{ after(grammarAccess.getIDStartWithinStatementExpressionSingleRule()); } 
	 EOF 
;

// Rule IDStartWithinStatementExpressionSingle
ruleIDStartWithinStatementExpressionSingle 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getIDStartWithinStatementExpressionSingleAccess().getIDStartWithinStatementExpressionLevel1ParserRuleCall()); }
		ruleIDStartWithinStatementExpressionLevel1
		{ after(grammarAccess.getIDStartWithinStatementExpressionSingleAccess().getIDStartWithinStatementExpressionLevel1ParserRuleCall()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleIDStartWithinStatementExpressionLevel1
entryRuleIDStartWithinStatementExpressionLevel1
:
{ before(grammarAccess.getIDStartWithinStatementExpressionLevel1Rule()); }
	 ruleIDStartWithinStatementExpressionLevel1
{ after(grammarAccess.getIDStartWithinStatementExpressionLevel1Rule()); } 
	 EOF 
;

// Rule IDStartWithinStatementExpressionLevel1
ruleIDStartWithinStatementExpressionLevel1 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getAlternatives()); }
		(rule__IDStartWithinStatementExpressionLevel1__Alternatives)
		{ after(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleIDStartWithinStatementExpressionLevel2
entryRuleIDStartWithinStatementExpressionLevel2
:
{ before(grammarAccess.getIDStartWithinStatementExpressionLevel2Rule()); }
	 ruleIDStartWithinStatementExpressionLevel2
{ after(grammarAccess.getIDStartWithinStatementExpressionLevel2Rule()); } 
	 EOF 
;

// Rule IDStartWithinStatementExpressionLevel2
ruleIDStartWithinStatementExpressionLevel2 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getGroup()); }
		(rule__IDStartWithinStatementExpressionLevel2__Group__0)
		{ after(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleIDStartWithinStatementExpressionLevel3
entryRuleIDStartWithinStatementExpressionLevel3
:
{ before(grammarAccess.getIDStartWithinStatementExpressionLevel3Rule()); }
	 ruleIDStartWithinStatementExpressionLevel3
{ after(grammarAccess.getIDStartWithinStatementExpressionLevel3Rule()); } 
	 EOF 
;

// Rule IDStartWithinStatementExpressionLevel3
ruleIDStartWithinStatementExpressionLevel3 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getGroup()); }
		(rule__IDStartWithinStatementExpressionLevel3__Group__0)
		{ after(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleIDStartWithinStatementExpressionLevel4
entryRuleIDStartWithinStatementExpressionLevel4
:
{ before(grammarAccess.getIDStartWithinStatementExpressionLevel4Rule()); }
	 ruleIDStartWithinStatementExpressionLevel4
{ after(grammarAccess.getIDStartWithinStatementExpressionLevel4Rule()); } 
	 EOF 
;

// Rule IDStartWithinStatementExpressionLevel4
ruleIDStartWithinStatementExpressionLevel4 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getGroup()); }
		(rule__IDStartWithinStatementExpressionLevel4__Group__0)
		{ after(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleIDStartWithinStatementExpressionLevel5
entryRuleIDStartWithinStatementExpressionLevel5
:
{ before(grammarAccess.getIDStartWithinStatementExpressionLevel5Rule()); }
	 ruleIDStartWithinStatementExpressionLevel5
{ after(grammarAccess.getIDStartWithinStatementExpressionLevel5Rule()); } 
	 EOF 
;

// Rule IDStartWithinStatementExpressionLevel5
ruleIDStartWithinStatementExpressionLevel5 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getGroup()); }
		(rule__IDStartWithinStatementExpressionLevel5__Group__0)
		{ after(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleNonIDStartWithinStatementExpressionSingle
entryRuleNonIDStartWithinStatementExpressionSingle
:
{ before(grammarAccess.getNonIDStartWithinStatementExpressionSingleRule()); }
	 ruleNonIDStartWithinStatementExpressionSingle
{ after(grammarAccess.getNonIDStartWithinStatementExpressionSingleRule()); } 
	 EOF 
;

// Rule NonIDStartWithinStatementExpressionSingle
ruleNonIDStartWithinStatementExpressionSingle 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionSingleAccess().getNonIDStartWithinStatementExpressionLevel1ParserRuleCall()); }
		ruleNonIDStartWithinStatementExpressionLevel1
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionSingleAccess().getNonIDStartWithinStatementExpressionLevel1ParserRuleCall()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleNonIDStartWithinStatementExpressionLevel1
entryRuleNonIDStartWithinStatementExpressionLevel1
:
{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Rule()); }
	 ruleNonIDStartWithinStatementExpressionLevel1
{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Rule()); } 
	 EOF 
;

// Rule NonIDStartWithinStatementExpressionLevel1
ruleNonIDStartWithinStatementExpressionLevel1 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Access().getAlternatives()); }
		(rule__NonIDStartWithinStatementExpressionLevel1__Alternatives)
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Access().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleNonIDStartWithinStatementExpressionLevel2
entryRuleNonIDStartWithinStatementExpressionLevel2
:
{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Rule()); }
	 ruleNonIDStartWithinStatementExpressionLevel2
{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Rule()); } 
	 EOF 
;

// Rule NonIDStartWithinStatementExpressionLevel2
ruleNonIDStartWithinStatementExpressionLevel2 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getGroup()); }
		(rule__NonIDStartWithinStatementExpressionLevel2__Group__0)
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleNonIDStartWithinStatementExpressionLevel3
entryRuleNonIDStartWithinStatementExpressionLevel3
:
{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Rule()); }
	 ruleNonIDStartWithinStatementExpressionLevel3
{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Rule()); } 
	 EOF 
;

// Rule NonIDStartWithinStatementExpressionLevel3
ruleNonIDStartWithinStatementExpressionLevel3 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getGroup()); }
		(rule__NonIDStartWithinStatementExpressionLevel3__Group__0)
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleNonIDStartWithinStatementExpressionLevel4
entryRuleNonIDStartWithinStatementExpressionLevel4
:
{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Rule()); }
	 ruleNonIDStartWithinStatementExpressionLevel4
{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Rule()); } 
	 EOF 
;

// Rule NonIDStartWithinStatementExpressionLevel4
ruleNonIDStartWithinStatementExpressionLevel4 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getGroup()); }
		(rule__NonIDStartWithinStatementExpressionLevel4__Group__0)
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleNonIDStartWithinStatementExpressionLevel5
entryRuleNonIDStartWithinStatementExpressionLevel5
:
{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Rule()); }
	 ruleNonIDStartWithinStatementExpressionLevel5
{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Rule()); } 
	 EOF 
;

// Rule NonIDStartWithinStatementExpressionLevel5
ruleNonIDStartWithinStatementExpressionLevel5 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getGroup()); }
		(rule__NonIDStartWithinStatementExpressionLevel5__Group__0)
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleNonIDStartWithinStatementExpressionLevel6
entryRuleNonIDStartWithinStatementExpressionLevel6
:
{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel6Rule()); }
	 ruleNonIDStartWithinStatementExpressionLevel6
{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel6Rule()); } 
	 EOF 
;

// Rule NonIDStartWithinStatementExpressionLevel6
ruleNonIDStartWithinStatementExpressionLevel6 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel6Access().getAlternatives()); }
		(rule__NonIDStartWithinStatementExpressionLevel6__Alternatives)
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel6Access().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleNonIDStartWithinStatementExpressionLevel7
entryRuleNonIDStartWithinStatementExpressionLevel7
:
{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel7Rule()); }
	 ruleNonIDStartWithinStatementExpressionLevel7
{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel7Rule()); } 
	 EOF 
;

// Rule NonIDStartWithinStatementExpressionLevel7
ruleNonIDStartWithinStatementExpressionLevel7 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel7Access().getAlternatives()); }
		(rule__NonIDStartWithinStatementExpressionLevel7__Alternatives)
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel7Access().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleStatement
entryRuleStatement
:
{ before(grammarAccess.getStatementRule()); }
	 ruleStatement
{ after(grammarAccess.getStatementRule()); } 
	 EOF 
;

// Rule Statement
ruleStatement 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getStatementAccess().getGroup()); }
		(rule__Statement__Group__0)
		{ after(grammarAccess.getStatementAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleStatementSingle
entryRuleStatementSingle
:
{ before(grammarAccess.getStatementSingleRule()); }
	 ruleStatementSingle
{ after(grammarAccess.getStatementSingleRule()); } 
	 EOF 
;

// Rule StatementSingle
ruleStatementSingle 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getStatementSingleAccess().getAlternatives()); }
		(rule__StatementSingle__Alternatives)
		{ after(grammarAccess.getStatementSingleAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleExpressionStatement
entryRuleExpressionStatement
:
{ before(grammarAccess.getExpressionStatementRule()); }
	 ruleExpressionStatement
{ after(grammarAccess.getExpressionStatementRule()); } 
	 EOF 
;

// Rule ExpressionStatement
ruleExpressionStatement 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getExpressionStatementAccess().getAlternatives()); }
		(rule__ExpressionStatement__Alternatives)
		{ after(grammarAccess.getExpressionStatementAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleDelayStatement
entryRuleDelayStatement
:
{ before(grammarAccess.getDelayStatementRule()); }
	 ruleDelayStatement
{ after(grammarAccess.getDelayStatementRule()); } 
	 EOF 
;

// Rule DelayStatement
ruleDelayStatement 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getDelayStatementAccess().getGroup()); }
		(rule__DelayStatement__Group__0)
		{ after(grammarAccess.getDelayStatementAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleReceiveStatement
entryRuleReceiveStatement
:
{ before(grammarAccess.getReceiveStatementRule()); }
	 ruleReceiveStatement
{ after(grammarAccess.getReceiveStatementRule()); } 
	 EOF 
;

// Rule ReceiveStatement
ruleReceiveStatement 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getReceiveStatementAccess().getGroup()); }
		(rule__ReceiveStatement__Group__0)
		{ after(grammarAccess.getReceiveStatementAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleSendStatement
entryRuleSendStatement
:
{ before(grammarAccess.getSendStatementRule()); }
	 ruleSendStatement
{ after(grammarAccess.getSendStatementRule()); } 
	 EOF 
;

// Rule SendStatement
ruleSendStatement 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getSendStatementAccess().getGroup()); }
		(rule__SendStatement__Group__0)
		{ after(grammarAccess.getSendStatementAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRulePortDescriptor
entryRulePortDescriptor
:
{ before(grammarAccess.getPortDescriptorRule()); }
	 rulePortDescriptor
{ after(grammarAccess.getPortDescriptorRule()); } 
	 EOF 
;

// Rule PortDescriptor
rulePortDescriptor 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getPortDescriptorAccess().getGroup()); }
		(rule__PortDescriptor__Group__0)
		{ after(grammarAccess.getPortDescriptorAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRulePortReference
entryRulePortReference
:
{ before(grammarAccess.getPortReferenceRule()); }
	 rulePortReference
{ after(grammarAccess.getPortReferenceRule()); } 
	 EOF 
;

// Rule PortReference
rulePortReference 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getPortReferenceAccess().getPortAssignment()); }
		(rule__PortReference__PortAssignment)
		{ after(grammarAccess.getPortReferenceAccess().getPortAssignment()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleSkipStatement
entryRuleSkipStatement
:
{ before(grammarAccess.getSkipStatementRule()); }
	 ruleSkipStatement
{ after(grammarAccess.getSkipStatementRule()); } 
	 EOF 
;

// Rule SkipStatement
ruleSkipStatement 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getSkipStatementAccess().getGroup()); }
		(rule__SkipStatement__Group__0)
		{ after(grammarAccess.getSkipStatementAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleGuardedStatement
entryRuleGuardedStatement
:
{ before(grammarAccess.getGuardedStatementRule()); }
	 ruleGuardedStatement
{ after(grammarAccess.getGuardedStatementRule()); } 
	 EOF 
;

// Rule GuardedStatement
ruleGuardedStatement 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getGuardedStatementAccess().getGroup()); }
		(rule__GuardedStatement__Group__0)
		{ after(grammarAccess.getGuardedStatementAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleIfStatement
entryRuleIfStatement
:
{ before(grammarAccess.getIfStatementRule()); }
	 ruleIfStatement
{ after(grammarAccess.getIfStatementRule()); } 
	 EOF 
;

// Rule IfStatement
ruleIfStatement 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getIfStatementAccess().getGroup()); }
		(rule__IfStatement__Group__0)
		{ after(grammarAccess.getIfStatementAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleWhileStatement
entryRuleWhileStatement
:
{ before(grammarAccess.getWhileStatementRule()); }
	 ruleWhileStatement
{ after(grammarAccess.getWhileStatementRule()); } 
	 EOF 
;

// Rule WhileStatement
ruleWhileStatement 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getWhileStatementAccess().getGroup()); }
		(rule__WhileStatement__Group__0)
		{ after(grammarAccess.getWhileStatementAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleSwitchStatement
entryRuleSwitchStatement
:
{ before(grammarAccess.getSwitchStatementRule()); }
	 ruleSwitchStatement
{ after(grammarAccess.getSwitchStatementRule()); } 
	 EOF 
;

// Rule SwitchStatement
ruleSwitchStatement 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getSwitchStatementAccess().getGroup()); }
		(rule__SwitchStatement__Group__0)
		{ after(grammarAccess.getSwitchStatementAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleSwitchStatementCase
entryRuleSwitchStatementCase
:
{ before(grammarAccess.getSwitchStatementCaseRule()); }
	 ruleSwitchStatementCase
{ after(grammarAccess.getSwitchStatementCaseRule()); } 
	 EOF 
;

// Rule SwitchStatementCase
ruleSwitchStatementCase 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getSwitchStatementCaseAccess().getGroup()); }
		(rule__SwitchStatementCase__Group__0)
		{ after(grammarAccess.getSwitchStatementCaseAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleParStatement
entryRuleParStatement
:
{ before(grammarAccess.getParStatementRule()); }
	 ruleParStatement
{ after(grammarAccess.getParStatementRule()); } 
	 EOF 
;

// Rule ParStatement
ruleParStatement 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getParStatementAccess().getGroup()); }
		(rule__ParStatement__Group__0)
		{ after(grammarAccess.getParStatementAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleSelStatement
entryRuleSelStatement
:
{ before(grammarAccess.getSelStatementRule()); }
	 ruleSelStatement
{ after(grammarAccess.getSelStatementRule()); } 
	 EOF 
;

// Rule SelStatement
ruleSelStatement 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getSelStatementAccess().getGroup()); }
		(rule__SelStatement__Group__0)
		{ after(grammarAccess.getSelStatementAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleAbortStatement
entryRuleAbortStatement
:
{ before(grammarAccess.getAbortStatementRule()); }
	 ruleAbortStatement
{ after(grammarAccess.getAbortStatementRule()); } 
	 EOF 
;

// Rule AbortStatement
ruleAbortStatement 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getAbortStatementAccess().getGroup()); }
		(rule__AbortStatement__Group__0)
		{ after(grammarAccess.getAbortStatementAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleInterruptStatement
entryRuleInterruptStatement
:
{ before(grammarAccess.getInterruptStatementRule()); }
	 ruleInterruptStatement
{ after(grammarAccess.getInterruptStatementRule()); } 
	 EOF 
;

// Rule InterruptStatement
ruleInterruptStatement 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getInterruptStatementAccess().getGroup()); }
		(rule__InterruptStatement__Group__0)
		{ after(grammarAccess.getInterruptStatementAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleProcessMethodCall
entryRuleProcessMethodCall
:
{ before(grammarAccess.getProcessMethodCallRule()); }
	 ruleProcessMethodCall
{ after(grammarAccess.getProcessMethodCallRule()); } 
	 EOF 
;

// Rule ProcessMethodCall
ruleProcessMethodCall 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getProcessMethodCallAccess().getGroup()); }
		(rule__ProcessMethodCall__Group__0)
		{ after(grammarAccess.getProcessMethodCallAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleBracketStartStatement
entryRuleBracketStartStatement
:
{ before(grammarAccess.getBracketStartStatementRule()); }
	 ruleBracketStartStatement
{ after(grammarAccess.getBracketStartStatementRule()); } 
	 EOF 
;

// Rule BracketStartStatement
ruleBracketStartStatement 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getBracketStartStatementAccess().getAlternatives()); }
		(rule__BracketStartStatement__Alternatives)
		{ after(grammarAccess.getBracketStartStatementAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleStatementSequenceRoundBracket
entryRuleStatementSequenceRoundBracket
:
{ before(grammarAccess.getStatementSequenceRoundBracketRule()); }
	 ruleStatementSequenceRoundBracket
{ after(grammarAccess.getStatementSequenceRoundBracketRule()); } 
	 EOF 
;

// Rule StatementSequenceRoundBracket
ruleStatementSequenceRoundBracket 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getStatementSequenceRoundBracketAccess().getGroup()); }
		(rule__StatementSequenceRoundBracket__Group__0)
		{ after(grammarAccess.getStatementSequenceRoundBracketAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleBracketedArgumentStartExpressionStatement
entryRuleBracketedArgumentStartExpressionStatement
:
{ before(grammarAccess.getBracketedArgumentStartExpressionStatementRule()); }
	 ruleBracketedArgumentStartExpressionStatement
{ after(grammarAccess.getBracketedArgumentStartExpressionStatementRule()); } 
	 EOF 
;

// Rule BracketedArgumentStartExpressionStatement
ruleBracketedArgumentStartExpressionStatement 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionStatementAccess().getExpressionAssignment()); }
		(rule__BracketedArgumentStartExpressionStatement__ExpressionAssignment)
		{ after(grammarAccess.getBracketedArgumentStartExpressionStatementAccess().getExpressionAssignment()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleBracketedArgumentStartExpressionSingle
entryRuleBracketedArgumentStartExpressionSingle
:
{ before(grammarAccess.getBracketedArgumentStartExpressionSingleRule()); }
	 ruleBracketedArgumentStartExpressionSingle
{ after(grammarAccess.getBracketedArgumentStartExpressionSingleRule()); } 
	 EOF 
;

// Rule BracketedArgumentStartExpressionSingle
ruleBracketedArgumentStartExpressionSingle 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionSingleAccess().getBracketedArgumentStartExpressionLevel1ParserRuleCall()); }
		ruleBracketedArgumentStartExpressionLevel1
		{ after(grammarAccess.getBracketedArgumentStartExpressionSingleAccess().getBracketedArgumentStartExpressionLevel1ParserRuleCall()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleBracketedArgumentStartExpressionLevel1
entryRuleBracketedArgumentStartExpressionLevel1
:
{ before(grammarAccess.getBracketedArgumentStartExpressionLevel1Rule()); }
	 ruleBracketedArgumentStartExpressionLevel1
{ after(grammarAccess.getBracketedArgumentStartExpressionLevel1Rule()); } 
	 EOF 
;

// Rule BracketedArgumentStartExpressionLevel1
ruleBracketedArgumentStartExpressionLevel1 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel1Access().getBracketedArgumentStartExpressionLevel2ParserRuleCall()); }
		ruleBracketedArgumentStartExpressionLevel2
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel1Access().getBracketedArgumentStartExpressionLevel2ParserRuleCall()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleBracketedArgumentStartExpressionLevel2
entryRuleBracketedArgumentStartExpressionLevel2
:
{ before(grammarAccess.getBracketedArgumentStartExpressionLevel2Rule()); }
	 ruleBracketedArgumentStartExpressionLevel2
{ after(grammarAccess.getBracketedArgumentStartExpressionLevel2Rule()); } 
	 EOF 
;

// Rule BracketedArgumentStartExpressionLevel2
ruleBracketedArgumentStartExpressionLevel2 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getAlternatives()); }
		(rule__BracketedArgumentStartExpressionLevel2__Alternatives)
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleBracketedArgumentStartExpressionLevel3
entryRuleBracketedArgumentStartExpressionLevel3
:
{ before(grammarAccess.getBracketedArgumentStartExpressionLevel3Rule()); }
	 ruleBracketedArgumentStartExpressionLevel3
{ after(grammarAccess.getBracketedArgumentStartExpressionLevel3Rule()); } 
	 EOF 
;

// Rule BracketedArgumentStartExpressionLevel3
ruleBracketedArgumentStartExpressionLevel3 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getAlternatives()); }
		(rule__BracketedArgumentStartExpressionLevel3__Alternatives)
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleBracketedArgumentStartExpressionLevel4
entryRuleBracketedArgumentStartExpressionLevel4
:
{ before(grammarAccess.getBracketedArgumentStartExpressionLevel4Rule()); }
	 ruleBracketedArgumentStartExpressionLevel4
{ after(grammarAccess.getBracketedArgumentStartExpressionLevel4Rule()); } 
	 EOF 
;

// Rule BracketedArgumentStartExpressionLevel4
ruleBracketedArgumentStartExpressionLevel4 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getAlternatives()); }
		(rule__BracketedArgumentStartExpressionLevel4__Alternatives)
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleBracketedArgumentStartExpressionLevel5
entryRuleBracketedArgumentStartExpressionLevel5
:
{ before(grammarAccess.getBracketedArgumentStartExpressionLevel5Rule()); }
	 ruleBracketedArgumentStartExpressionLevel5
{ after(grammarAccess.getBracketedArgumentStartExpressionLevel5Rule()); } 
	 EOF 
;

// Rule BracketedArgumentStartExpressionLevel5
ruleBracketedArgumentStartExpressionLevel5 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getGroup()); }
		(rule__BracketedArgumentStartExpressionLevel5__Group__0)
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleINTEGER
entryRuleINTEGER
:
{ before(grammarAccess.getINTEGERRule()); }
	 ruleINTEGER
{ after(grammarAccess.getINTEGERRule()); } 
	 EOF 
;

// Rule INTEGER
ruleINTEGER 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getINTEGERAccess().getGroup()); }
		(rule__INTEGER__Group__0)
		{ after(grammarAccess.getINTEGERAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleREAL
entryRuleREAL
:
{ before(grammarAccess.getREALRule()); }
	 ruleREAL
{ after(grammarAccess.getREALRule()); } 
	 EOF 
;

// Rule REAL
ruleREAL 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getREALAccess().getGroup()); }
		(rule__REAL__Group__0)
		{ after(grammarAccess.getREALAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleFLOAT
entryRuleFLOAT
:
{ before(grammarAccess.getFLOATRule()); }
	 ruleFLOAT
{ after(grammarAccess.getFLOATRule()); } 
	 EOF 
;

// Rule FLOAT
ruleFLOAT 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getFLOATAccess().getGroup()); }
		(rule__FLOAT__Group__0)
		{ after(grammarAccess.getFLOATAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleIDENTIFIER
entryRuleIDENTIFIER
:
{ before(grammarAccess.getIDENTIFIERRule()); }
	 ruleIDENTIFIER
{ after(grammarAccess.getIDENTIFIERRule()); } 
	 EOF 
;

// Rule IDENTIFIER
ruleIDENTIFIER 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getIDENTIFIERAccess().getAlternatives()); }
		(rule__IDENTIFIER__Alternatives)
		{ after(grammarAccess.getIDENTIFIERAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule OperatorUnary
ruleOperatorUnary
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getOperatorUnaryAccess().getAlternatives()); }
		(rule__OperatorUnary__Alternatives)
		{ after(grammarAccess.getOperatorUnaryAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule OperatorBinary
ruleOperatorBinary
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getOperatorBinaryAccess().getAlternatives()); }
		(rule__OperatorBinary__Alternatives)
		{ after(grammarAccess.getOperatorBinaryAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule BinaryOperatorLevel2
ruleBinaryOperatorLevel2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBinaryOperatorLevel2Access().getAlternatives()); }
		(rule__BinaryOperatorLevel2__Alternatives)
		{ after(grammarAccess.getBinaryOperatorLevel2Access().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule BinaryOperatorLevel3
ruleBinaryOperatorLevel3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBinaryOperatorLevel3Access().getAlternatives()); }
		(rule__BinaryOperatorLevel3__Alternatives)
		{ after(grammarAccess.getBinaryOperatorLevel3Access().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule BinaryOperatorLevel4
ruleBinaryOperatorLevel4
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBinaryOperatorLevel4Access().getAlternatives()); }
		(rule__BinaryOperatorLevel4__Alternatives)
		{ after(grammarAccess.getBinaryOperatorLevel4Access().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Poosl__Alternatives_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPooslAccess().getImportsAssignment_1_0()); }
		(rule__Poosl__ImportsAssignment_1_0)
		{ after(grammarAccess.getPooslAccess().getImportsAssignment_1_0()); }
	)
	|
	(
		{ before(grammarAccess.getPooslAccess().getImportLibsAssignment_1_1()); }
		(rule__Poosl__ImportLibsAssignment_1_1)
		{ after(grammarAccess.getPooslAccess().getImportLibsAssignment_1_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Poosl__Alternatives_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPooslAccess().getDataClassesAssignment_2_0()); }
		(rule__Poosl__DataClassesAssignment_2_0)
		{ after(grammarAccess.getPooslAccess().getDataClassesAssignment_2_0()); }
	)
	|
	(
		{ before(grammarAccess.getPooslAccess().getProcessClassesAssignment_2_1()); }
		(rule__Poosl__ProcessClassesAssignment_2_1)
		{ after(grammarAccess.getPooslAccess().getProcessClassesAssignment_2_1()); }
	)
	|
	(
		{ before(grammarAccess.getPooslAccess().getClusterClassesAssignment_2_2()); }
		(rule__Poosl__ClusterClassesAssignment_2_2)
		{ after(grammarAccess.getPooslAccess().getClusterClassesAssignment_2_2()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Poosl__Alternatives_3_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPooslAccess().getDataClassesAssignment_3_1_0()); }
		(rule__Poosl__DataClassesAssignment_3_1_0)
		{ after(grammarAccess.getPooslAccess().getDataClassesAssignment_3_1_0()); }
	)
	|
	(
		{ before(grammarAccess.getPooslAccess().getProcessClassesAssignment_3_1_1()); }
		(rule__Poosl__ProcessClassesAssignment_3_1_1)
		{ after(grammarAccess.getPooslAccess().getProcessClassesAssignment_3_1_1()); }
	)
	|
	(
		{ before(grammarAccess.getPooslAccess().getClusterClassesAssignment_3_1_2()); }
		(rule__Poosl__ClusterClassesAssignment_3_1_2)
		{ after(grammarAccess.getPooslAccess().getClusterClassesAssignment_3_1_2()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Alternatives_9
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataClassAccess().getDataMethodsNamedAssignment_9_0()); }
		(rule__DataClass__DataMethodsNamedAssignment_9_0)
		{ after(grammarAccess.getDataClassAccess().getDataMethodsNamedAssignment_9_0()); }
	)
	|
	(
		{ before(grammarAccess.getDataClassAccess().getDataMethodsUnaryOperatorAssignment_9_1()); }
		(rule__DataClass__DataMethodsUnaryOperatorAssignment_9_1)
		{ after(grammarAccess.getDataClassAccess().getDataMethodsUnaryOperatorAssignment_9_1()); }
	)
	|
	(
		{ before(grammarAccess.getDataClassAccess().getDataMethodsBinaryOperatorAssignment_9_2()); }
		(rule__DataClass__DataMethodsBinaryOperatorAssignment_9_2)
		{ after(grammarAccess.getDataClassAccess().getDataMethodsBinaryOperatorAssignment_9_2()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Alternatives_9_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessClassAccess().getReceiveMessagesAssignment_9_0_0()); }
		(rule__ProcessClass__ReceiveMessagesAssignment_9_0_0)
		{ after(grammarAccess.getProcessClassAccess().getReceiveMessagesAssignment_9_0_0()); }
	)
	|
	(
		{ before(grammarAccess.getProcessClassAccess().getSendMessagesAssignment_9_0_1()); }
		(rule__ProcessClass__SendMessagesAssignment_9_0_1)
		{ after(grammarAccess.getProcessClassAccess().getSendMessagesAssignment_9_0_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Alternatives_9_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessClassAccess().getReceiveMessagesAssignment_9_1_1_0()); }
		(rule__ProcessClass__ReceiveMessagesAssignment_9_1_1_0)
		{ after(grammarAccess.getProcessClassAccess().getReceiveMessagesAssignment_9_1_1_0()); }
	)
	|
	(
		{ before(grammarAccess.getProcessClassAccess().getSendMessagesAssignment_9_1_1_1()); }
		(rule__ProcessClass__SendMessagesAssignment_9_1_1_1)
		{ after(grammarAccess.getProcessClassAccess().getSendMessagesAssignment_9_1_1_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Alternatives_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getChannelAccess().getGroup_3_0()); }
		(rule__Channel__Group_3_0__0)
		{ after(grammarAccess.getChannelAccess().getGroup_3_0()); }
	)
	|
	(
		{ before(grammarAccess.getChannelAccess().getGroup_3_1()); }
		(rule__Channel__Group_3_1__0)
		{ after(grammarAccess.getChannelAccess().getGroup_3_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel1__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionLevel1Access().getExpressionLevel2ParserRuleCall_0()); }
		ruleExpressionLevel2
		{ after(grammarAccess.getExpressionLevel1Access().getExpressionLevel2ParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getExpressionLevel1Access().getGroup_1()); }
		(rule__ExpressionLevel1__Group_1__0)
		{ after(grammarAccess.getExpressionLevel1Access().getGroup_1()); }
	)
	|
	(
		{ before(grammarAccess.getExpressionLevel1Access().getGroup_2()); }
		(rule__ExpressionLevel1__Group_2__0)
		{ after(grammarAccess.getExpressionLevel1Access().getGroup_2()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel6__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionLevel6Access().getExpressionLevel7ParserRuleCall_0()); }
		(ruleExpressionLevel7)
		{ after(grammarAccess.getExpressionLevel6Access().getExpressionLevel7ParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getExpressionLevel6Access().getUnaryOperatorExpressionParserRuleCall_1()); }
		ruleUnaryOperatorExpression
		{ after(grammarAccess.getExpressionLevel6Access().getUnaryOperatorExpressionParserRuleCall_1()); }
	)
	|
	(
		{ before(grammarAccess.getExpressionLevel6Access().getIfExpressionParserRuleCall_2()); }
		ruleIfExpression
		{ after(grammarAccess.getExpressionLevel6Access().getIfExpressionParserRuleCall_2()); }
	)
	|
	(
		{ before(grammarAccess.getExpressionLevel6Access().getWhileExpressionParserRuleCall_3()); }
		ruleWhileExpression
		{ after(grammarAccess.getExpressionLevel6Access().getWhileExpressionParserRuleCall_3()); }
	)
	|
	(
		{ before(grammarAccess.getExpressionLevel6Access().getSwitchExpressionParserRuleCall_4()); }
		ruleSwitchExpression
		{ after(grammarAccess.getExpressionLevel6Access().getSwitchExpressionParserRuleCall_4()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__Alternatives_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSwitchExpressionAccess().getGroup_3_0()); }
		(rule__SwitchExpression__Group_3_0__0)
		{ after(grammarAccess.getSwitchExpressionAccess().getGroup_3_0()); }
	)
	|
	(
		{ before(grammarAccess.getSwitchExpressionAccess().getGroup_3_1()); }
		(rule__SwitchExpression__Group_3_1__0)
		{ after(grammarAccess.getSwitchExpressionAccess().getGroup_3_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel7__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionLevel7Access().getCurrentTimeExpressionParserRuleCall_0()); }
		ruleCurrentTimeExpression
		{ after(grammarAccess.getExpressionLevel7Access().getCurrentTimeExpressionParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getExpressionLevel7Access().getSelfExpressionParserRuleCall_1()); }
		ruleSelfExpression
		{ after(grammarAccess.getExpressionLevel7Access().getSelfExpressionParserRuleCall_1()); }
	)
	|
	(
		{ before(grammarAccess.getExpressionLevel7Access().getExpressionConstantParserRuleCall_2()); }
		ruleExpressionConstant
		{ after(grammarAccess.getExpressionLevel7Access().getExpressionConstantParserRuleCall_2()); }
	)
	|
	(
		{ before(grammarAccess.getExpressionLevel7Access().getNewExpressionParserRuleCall_3()); }
		ruleNewExpression
		{ after(grammarAccess.getExpressionLevel7Access().getNewExpressionParserRuleCall_3()); }
	)
	|
	(
		{ before(grammarAccess.getExpressionLevel7Access().getVariableExpressionParserRuleCall_4()); }
		ruleVariableExpression
		{ after(grammarAccess.getExpressionLevel7Access().getVariableExpressionParserRuleCall_4()); }
	)
	|
	(
		{ before(grammarAccess.getExpressionLevel7Access().getExpressionSequenceRoundBracketParserRuleCall_5()); }
		ruleExpressionSequenceRoundBracket
		{ after(grammarAccess.getExpressionLevel7Access().getExpressionSequenceRoundBracketParserRuleCall_5()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionConstant__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionConstantAccess().getBooleanConstantParserRuleCall_0()); }
		ruleBooleanConstant
		{ after(grammarAccess.getExpressionConstantAccess().getBooleanConstantParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getExpressionConstantAccess().getCharacterConstantParserRuleCall_1()); }
		ruleCharacterConstant
		{ after(grammarAccess.getExpressionConstantAccess().getCharacterConstantParserRuleCall_1()); }
	)
	|
	(
		{ before(grammarAccess.getExpressionConstantAccess().getFloatConstantParserRuleCall_2()); }
		ruleFloatConstant
		{ after(grammarAccess.getExpressionConstantAccess().getFloatConstantParserRuleCall_2()); }
	)
	|
	(
		{ before(grammarAccess.getExpressionConstantAccess().getIntegerConstantParserRuleCall_3()); }
		ruleIntegerConstant
		{ after(grammarAccess.getExpressionConstantAccess().getIntegerConstantParserRuleCall_3()); }
	)
	|
	(
		{ before(grammarAccess.getExpressionConstantAccess().getNilConstantParserRuleCall_4()); }
		ruleNilConstant
		{ after(grammarAccess.getExpressionConstantAccess().getNilConstantParserRuleCall_4()); }
	)
	|
	(
		{ before(grammarAccess.getExpressionConstantAccess().getRealConstantParserRuleCall_5()); }
		ruleRealConstant
		{ after(grammarAccess.getExpressionConstantAccess().getRealConstantParserRuleCall_5()); }
	)
	|
	(
		{ before(grammarAccess.getExpressionConstantAccess().getStringConstantParserRuleCall_6()); }
		ruleStringConstant
		{ after(grammarAccess.getExpressionConstantAccess().getStringConstantParserRuleCall_6()); }
	)
	|
	(
		{ before(grammarAccess.getExpressionConstantAccess().getEnvironmentConstantParserRuleCall_7()); }
		ruleEnvironmentConstant
		{ after(grammarAccess.getExpressionConstantAccess().getEnvironmentConstantParserRuleCall_7()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanConstant__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBooleanConstantAccess().getValueAssignment_0()); }
		(rule__BooleanConstant__ValueAssignment_0)
		{ after(grammarAccess.getBooleanConstantAccess().getValueAssignment_0()); }
	)
	|
	(
		{ before(grammarAccess.getBooleanConstantAccess().getValueAssignment_1()); }
		(rule__BooleanConstant__ValueAssignment_1)
		{ after(grammarAccess.getBooleanConstantAccess().getValueAssignment_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel1__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getIDStartWithinStatementExpressionLevel2ParserRuleCall_0()); }
		ruleIDStartWithinStatementExpressionLevel2
		{ after(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getIDStartWithinStatementExpressionLevel2ParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getGroup_1()); }
		(rule__IDStartWithinStatementExpressionLevel1__Group_1__0)
		{ after(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getGroup_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel1__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Access().getNonIDStartWithinStatementExpressionLevel2ParserRuleCall_0()); }
		ruleNonIDStartWithinStatementExpressionLevel2
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Access().getNonIDStartWithinStatementExpressionLevel2ParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Access().getGroup_1()); }
		(rule__NonIDStartWithinStatementExpressionLevel1__Group_1__0)
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Access().getGroup_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel6__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel6Access().getNonIDStartWithinStatementExpressionLevel7ParserRuleCall_0()); }
		(ruleNonIDStartWithinStatementExpressionLevel7)
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel6Access().getNonIDStartWithinStatementExpressionLevel7ParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel6Access().getUnaryOperatorExpressionParserRuleCall_1()); }
		ruleUnaryOperatorExpression
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel6Access().getUnaryOperatorExpressionParserRuleCall_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel7__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel7Access().getCurrentTimeExpressionParserRuleCall_0()); }
		ruleCurrentTimeExpression
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel7Access().getCurrentTimeExpressionParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel7Access().getSelfExpressionParserRuleCall_1()); }
		ruleSelfExpression
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel7Access().getSelfExpressionParserRuleCall_1()); }
	)
	|
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel7Access().getExpressionConstantParserRuleCall_2()); }
		ruleExpressionConstant
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel7Access().getExpressionConstantParserRuleCall_2()); }
	)
	|
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel7Access().getNewExpressionParserRuleCall_3()); }
		ruleNewExpression
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel7Access().getNewExpressionParserRuleCall_3()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StatementSingle__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStatementSingleAccess().getAbortStatementParserRuleCall_0()); }
		ruleAbortStatement
		{ after(grammarAccess.getStatementSingleAccess().getAbortStatementParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getStatementSingleAccess().getDelayStatementParserRuleCall_1()); }
		ruleDelayStatement
		{ after(grammarAccess.getStatementSingleAccess().getDelayStatementParserRuleCall_1()); }
	)
	|
	(
		{ before(grammarAccess.getStatementSingleAccess().getGuardedStatementParserRuleCall_2()); }
		ruleGuardedStatement
		{ after(grammarAccess.getStatementSingleAccess().getGuardedStatementParserRuleCall_2()); }
	)
	|
	(
		{ before(grammarAccess.getStatementSingleAccess().getInterruptStatementParserRuleCall_3()); }
		ruleInterruptStatement
		{ after(grammarAccess.getStatementSingleAccess().getInterruptStatementParserRuleCall_3()); }
	)
	|
	(
		{ before(grammarAccess.getStatementSingleAccess().getParStatementParserRuleCall_4()); }
		ruleParStatement
		{ after(grammarAccess.getStatementSingleAccess().getParStatementParserRuleCall_4()); }
	)
	|
	(
		{ before(grammarAccess.getStatementSingleAccess().getProcessMethodCallParserRuleCall_5()); }
		ruleProcessMethodCall
		{ after(grammarAccess.getStatementSingleAccess().getProcessMethodCallParserRuleCall_5()); }
	)
	|
	(
		{ before(grammarAccess.getStatementSingleAccess().getSelStatementParserRuleCall_6()); }
		ruleSelStatement
		{ after(grammarAccess.getStatementSingleAccess().getSelStatementParserRuleCall_6()); }
	)
	|
	(
		{ before(grammarAccess.getStatementSingleAccess().getSkipStatementParserRuleCall_7()); }
		ruleSkipStatement
		{ after(grammarAccess.getStatementSingleAccess().getSkipStatementParserRuleCall_7()); }
	)
	|
	(
		{ before(grammarAccess.getStatementSingleAccess().getSendStatementParserRuleCall_8()); }
		ruleSendStatement
		{ after(grammarAccess.getStatementSingleAccess().getSendStatementParserRuleCall_8()); }
	)
	|
	(
		{ before(grammarAccess.getStatementSingleAccess().getReceiveStatementParserRuleCall_9()); }
		ruleReceiveStatement
		{ after(grammarAccess.getStatementSingleAccess().getReceiveStatementParserRuleCall_9()); }
	)
	|
	(
		{ before(grammarAccess.getStatementSingleAccess().getIfStatementParserRuleCall_10()); }
		ruleIfStatement
		{ after(grammarAccess.getStatementSingleAccess().getIfStatementParserRuleCall_10()); }
	)
	|
	(
		{ before(grammarAccess.getStatementSingleAccess().getBracketStartStatementParserRuleCall_11()); }
		ruleBracketStartStatement
		{ after(grammarAccess.getStatementSingleAccess().getBracketStartStatementParserRuleCall_11()); }
	)
	|
	(
		{ before(grammarAccess.getStatementSingleAccess().getWhileStatementParserRuleCall_12()); }
		ruleWhileStatement
		{ after(grammarAccess.getStatementSingleAccess().getWhileStatementParserRuleCall_12()); }
	)
	|
	(
		{ before(grammarAccess.getStatementSingleAccess().getSwitchStatementParserRuleCall_13()); }
		ruleSwitchStatement
		{ after(grammarAccess.getStatementSingleAccess().getSwitchStatementParserRuleCall_13()); }
	)
	|
	(
		{ before(grammarAccess.getStatementSingleAccess().getExpressionStatementParserRuleCall_14()); }
		ruleExpressionStatement
		{ after(grammarAccess.getStatementSingleAccess().getExpressionStatementParserRuleCall_14()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionStatement__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionStatementAccess().getExpressionAssignment_0()); }
		(rule__ExpressionStatement__ExpressionAssignment_0)
		{ after(grammarAccess.getExpressionStatementAccess().getExpressionAssignment_0()); }
	)
	|
	(
		{ before(grammarAccess.getExpressionStatementAccess().getExpressionAssignment_1()); }
		(rule__ExpressionStatement__ExpressionAssignment_1)
		{ after(grammarAccess.getExpressionStatementAccess().getExpressionAssignment_1()); }
	)
	|
	(
		{ before(grammarAccess.getExpressionStatementAccess().getGroup_2()); }
		(rule__ExpressionStatement__Group_2__0)
		{ after(grammarAccess.getExpressionStatementAccess().getGroup_2()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__Alternatives_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSwitchStatementAccess().getGroup_3_0()); }
		(rule__SwitchStatement__Group_3_0__0)
		{ after(grammarAccess.getSwitchStatementAccess().getGroup_3_0()); }
	)
	|
	(
		{ before(grammarAccess.getSwitchStatementAccess().getGroup_3_1()); }
		(rule__SwitchStatement__Group_3_1__0)
		{ after(grammarAccess.getSwitchStatementAccess().getGroup_3_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketStartStatement__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBracketStartStatementAccess().getBracketedArgumentStartExpressionStatementParserRuleCall_0()); }
		(ruleBracketedArgumentStartExpressionStatement)
		{ after(grammarAccess.getBracketStartStatementAccess().getBracketedArgumentStartExpressionStatementParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getBracketStartStatementAccess().getStatementSequenceRoundBracketParserRuleCall_1()); }
		ruleStatementSequenceRoundBracket
		{ after(grammarAccess.getBracketStartStatementAccess().getStatementSequenceRoundBracketParserRuleCall_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel2__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getGroup_0()); }
		(rule__BracketedArgumentStartExpressionLevel2__Group_0__0)
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getGroup_0()); }
	)
	|
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getGroup_1()); }
		(rule__BracketedArgumentStartExpressionLevel2__Group_1__0)
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getGroup_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel3__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getGroup_0()); }
		(rule__BracketedArgumentStartExpressionLevel3__Group_0__0)
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getGroup_0()); }
	)
	|
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getGroup_1()); }
		(rule__BracketedArgumentStartExpressionLevel3__Group_1__0)
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getGroup_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel4__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getGroup_0()); }
		(rule__BracketedArgumentStartExpressionLevel4__Group_0__0)
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getGroup_0()); }
	)
	|
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getGroup_1()); }
		(rule__BracketedArgumentStartExpressionLevel4__Group_1__0)
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getGroup_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__INTEGER__Alternatives_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getINTEGERAccess().getHyphenMinusKeyword_0_0()); }
		'-'
		{ after(grammarAccess.getINTEGERAccess().getHyphenMinusKeyword_0_0()); }
	)
	|
	(
		{ before(grammarAccess.getINTEGERAccess().getPlusSignKeyword_0_1()); }
		'+'
		{ after(grammarAccess.getINTEGERAccess().getPlusSignKeyword_0_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__INTEGER__Alternatives_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getINTEGERAccess().getDECIMAL_CORETerminalRuleCall_1_0()); }
		RULE_DECIMAL_CORE
		{ after(grammarAccess.getINTEGERAccess().getDECIMAL_CORETerminalRuleCall_1_0()); }
	)
	|
	(
		{ before(grammarAccess.getINTEGERAccess().getBINARY_CORETerminalRuleCall_1_1()); }
		RULE_BINARY_CORE
		{ after(grammarAccess.getINTEGERAccess().getBINARY_CORETerminalRuleCall_1_1()); }
	)
	|
	(
		{ before(grammarAccess.getINTEGERAccess().getHEXADECIMAL_CORETerminalRuleCall_1_2()); }
		RULE_HEXADECIMAL_CORE
		{ after(grammarAccess.getINTEGERAccess().getHEXADECIMAL_CORETerminalRuleCall_1_2()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__REAL__Alternatives_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getREALAccess().getHyphenMinusKeyword_0_0()); }
		'-'
		{ after(grammarAccess.getREALAccess().getHyphenMinusKeyword_0_0()); }
	)
	|
	(
		{ before(grammarAccess.getREALAccess().getPlusSignKeyword_0_1()); }
		'+'
		{ after(grammarAccess.getREALAccess().getPlusSignKeyword_0_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__FLOAT__Alternatives_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getFLOATAccess().getHyphenMinusKeyword_0_0()); }
		'-'
		{ after(grammarAccess.getFLOATAccess().getHyphenMinusKeyword_0_0()); }
	)
	|
	(
		{ before(grammarAccess.getFLOATAccess().getPlusSignKeyword_0_1()); }
		'+'
		{ after(grammarAccess.getFLOATAccess().getPlusSignKeyword_0_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__FLOAT__Alternatives_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getFLOATAccess().getFLOAT_CORETerminalRuleCall_1_0()); }
		RULE_FLOAT_CORE
		{ after(grammarAccess.getFLOATAccess().getFLOAT_CORETerminalRuleCall_1_0()); }
	)
	|
	(
		{ before(grammarAccess.getFLOATAccess().getNanKeyword_1_1()); }
		'nan'
		{ after(grammarAccess.getFLOATAccess().getNanKeyword_1_1()); }
	)
	|
	(
		{ before(grammarAccess.getFLOATAccess().getInfKeyword_1_2()); }
		'inf'
		{ after(grammarAccess.getFLOATAccess().getInfKeyword_1_2()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDENTIFIER__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIDENTIFIERAccess().getIDENTIFIER_CORETerminalRuleCall_0()); }
		RULE_IDENTIFIER_CORE
		{ after(grammarAccess.getIDENTIFIERAccess().getIDENTIFIER_CORETerminalRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getIDENTIFIERAccess().getClassKeyword_1()); }
		'class'
		{ after(grammarAccess.getIDENTIFIERAccess().getClassKeyword_1()); }
	)
	|
	(
		{ before(grammarAccess.getIDENTIFIERAccess().getExtendsKeyword_2()); }
		'extends'
		{ after(grammarAccess.getIDENTIFIERAccess().getExtendsKeyword_2()); }
	)
	|
	(
		{ before(grammarAccess.getIDENTIFIERAccess().getVariablesKeyword_3()); }
		'variables'
		{ after(grammarAccess.getIDENTIFIERAccess().getVariablesKeyword_3()); }
	)
	|
	(
		{ before(grammarAccess.getIDENTIFIERAccess().getMethodsKeyword_4()); }
		'methods'
		{ after(grammarAccess.getIDENTIFIERAccess().getMethodsKeyword_4()); }
	)
	|
	(
		{ before(grammarAccess.getIDENTIFIERAccess().getPortsKeyword_5()); }
		'ports'
		{ after(grammarAccess.getIDENTIFIERAccess().getPortsKeyword_5()); }
	)
	|
	(
		{ before(grammarAccess.getIDENTIFIERAccess().getMessagesKeyword_6()); }
		'messages'
		{ after(grammarAccess.getIDENTIFIERAccess().getMessagesKeyword_6()); }
	)
	|
	(
		{ before(grammarAccess.getIDENTIFIERAccess().getInitKeyword_7()); }
		'init'
		{ after(grammarAccess.getIDENTIFIERAccess().getInitKeyword_7()); }
	)
	|
	(
		{ before(grammarAccess.getIDENTIFIERAccess().getChannelsKeyword_8()); }
		'channels'
		{ after(grammarAccess.getIDENTIFIERAccess().getChannelsKeyword_8()); }
	)
	|
	(
		{ before(grammarAccess.getIDENTIFIERAccess().getInstancesKeyword_9()); }
		'instances'
		{ after(grammarAccess.getIDENTIFIERAccess().getInstancesKeyword_9()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__OperatorUnary__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getOperatorUnaryAccess().getMinusEnumLiteralDeclaration_0()); }
		('-')
		{ after(grammarAccess.getOperatorUnaryAccess().getMinusEnumLiteralDeclaration_0()); }
	)
	|
	(
		{ before(grammarAccess.getOperatorUnaryAccess().getNotEnumLiteralDeclaration_1()); }
		('!')
		{ after(grammarAccess.getOperatorUnaryAccess().getNotEnumLiteralDeclaration_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__OperatorBinary__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getOperatorBinaryAccess().getEqualEnumLiteralDeclaration_0()); }
		('=')
		{ after(grammarAccess.getOperatorBinaryAccess().getEqualEnumLiteralDeclaration_0()); }
	)
	|
	(
		{ before(grammarAccess.getOperatorBinaryAccess().getUnequalEnumLiteralDeclaration_1()); }
		('!=')
		{ after(grammarAccess.getOperatorBinaryAccess().getUnequalEnumLiteralDeclaration_1()); }
	)
	|
	(
		{ before(grammarAccess.getOperatorBinaryAccess().getIdenticalEnumLiteralDeclaration_2()); }
		('==')
		{ after(grammarAccess.getOperatorBinaryAccess().getIdenticalEnumLiteralDeclaration_2()); }
	)
	|
	(
		{ before(grammarAccess.getOperatorBinaryAccess().getNotIdenticalEnumLiteralDeclaration_3()); }
		('!==')
		{ after(grammarAccess.getOperatorBinaryAccess().getNotIdenticalEnumLiteralDeclaration_3()); }
	)
	|
	(
		{ before(grammarAccess.getOperatorBinaryAccess().getLessThanEnumLiteralDeclaration_4()); }
		('<')
		{ after(grammarAccess.getOperatorBinaryAccess().getLessThanEnumLiteralDeclaration_4()); }
	)
	|
	(
		{ before(grammarAccess.getOperatorBinaryAccess().getAtMostEnumLiteralDeclaration_5()); }
		('<=')
		{ after(grammarAccess.getOperatorBinaryAccess().getAtMostEnumLiteralDeclaration_5()); }
	)
	|
	(
		{ before(grammarAccess.getOperatorBinaryAccess().getGreaterThanEnumLiteralDeclaration_6()); }
		('>')
		{ after(grammarAccess.getOperatorBinaryAccess().getGreaterThanEnumLiteralDeclaration_6()); }
	)
	|
	(
		{ before(grammarAccess.getOperatorBinaryAccess().getAtLeastEnumLiteralDeclaration_7()); }
		('>=')
		{ after(grammarAccess.getOperatorBinaryAccess().getAtLeastEnumLiteralDeclaration_7()); }
	)
	|
	(
		{ before(grammarAccess.getOperatorBinaryAccess().getAddEnumLiteralDeclaration_8()); }
		('+')
		{ after(grammarAccess.getOperatorBinaryAccess().getAddEnumLiteralDeclaration_8()); }
	)
	|
	(
		{ before(grammarAccess.getOperatorBinaryAccess().getSubtractEnumLiteralDeclaration_9()); }
		('-')
		{ after(grammarAccess.getOperatorBinaryAccess().getSubtractEnumLiteralDeclaration_9()); }
	)
	|
	(
		{ before(grammarAccess.getOperatorBinaryAccess().getAndEnumLiteralDeclaration_10()); }
		('&')
		{ after(grammarAccess.getOperatorBinaryAccess().getAndEnumLiteralDeclaration_10()); }
	)
	|
	(
		{ before(grammarAccess.getOperatorBinaryAccess().getOrEnumLiteralDeclaration_11()); }
		('|')
		{ after(grammarAccess.getOperatorBinaryAccess().getOrEnumLiteralDeclaration_11()); }
	)
	|
	(
		{ before(grammarAccess.getOperatorBinaryAccess().getMultiplyEnumLiteralDeclaration_12()); }
		('*')
		{ after(grammarAccess.getOperatorBinaryAccess().getMultiplyEnumLiteralDeclaration_12()); }
	)
	|
	(
		{ before(grammarAccess.getOperatorBinaryAccess().getDivideEnumLiteralDeclaration_13()); }
		('/')
		{ after(grammarAccess.getOperatorBinaryAccess().getDivideEnumLiteralDeclaration_13()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BinaryOperatorLevel2__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBinaryOperatorLevel2Access().getEqualEnumLiteralDeclaration_0()); }
		('=')
		{ after(grammarAccess.getBinaryOperatorLevel2Access().getEqualEnumLiteralDeclaration_0()); }
	)
	|
	(
		{ before(grammarAccess.getBinaryOperatorLevel2Access().getUnequalEnumLiteralDeclaration_1()); }
		('!=')
		{ after(grammarAccess.getBinaryOperatorLevel2Access().getUnequalEnumLiteralDeclaration_1()); }
	)
	|
	(
		{ before(grammarAccess.getBinaryOperatorLevel2Access().getIdenticalEnumLiteralDeclaration_2()); }
		('==')
		{ after(grammarAccess.getBinaryOperatorLevel2Access().getIdenticalEnumLiteralDeclaration_2()); }
	)
	|
	(
		{ before(grammarAccess.getBinaryOperatorLevel2Access().getNotIdenticalEnumLiteralDeclaration_3()); }
		('!==')
		{ after(grammarAccess.getBinaryOperatorLevel2Access().getNotIdenticalEnumLiteralDeclaration_3()); }
	)
	|
	(
		{ before(grammarAccess.getBinaryOperatorLevel2Access().getLessThanEnumLiteralDeclaration_4()); }
		('<')
		{ after(grammarAccess.getBinaryOperatorLevel2Access().getLessThanEnumLiteralDeclaration_4()); }
	)
	|
	(
		{ before(grammarAccess.getBinaryOperatorLevel2Access().getAtMostEnumLiteralDeclaration_5()); }
		('<=')
		{ after(grammarAccess.getBinaryOperatorLevel2Access().getAtMostEnumLiteralDeclaration_5()); }
	)
	|
	(
		{ before(grammarAccess.getBinaryOperatorLevel2Access().getGreaterThanEnumLiteralDeclaration_6()); }
		('>')
		{ after(grammarAccess.getBinaryOperatorLevel2Access().getGreaterThanEnumLiteralDeclaration_6()); }
	)
	|
	(
		{ before(grammarAccess.getBinaryOperatorLevel2Access().getAtLeastEnumLiteralDeclaration_7()); }
		('>=')
		{ after(grammarAccess.getBinaryOperatorLevel2Access().getAtLeastEnumLiteralDeclaration_7()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BinaryOperatorLevel3__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBinaryOperatorLevel3Access().getAddEnumLiteralDeclaration_0()); }
		('+')
		{ after(grammarAccess.getBinaryOperatorLevel3Access().getAddEnumLiteralDeclaration_0()); }
	)
	|
	(
		{ before(grammarAccess.getBinaryOperatorLevel3Access().getSubtractEnumLiteralDeclaration_1()); }
		('-')
		{ after(grammarAccess.getBinaryOperatorLevel3Access().getSubtractEnumLiteralDeclaration_1()); }
	)
	|
	(
		{ before(grammarAccess.getBinaryOperatorLevel3Access().getAndEnumLiteralDeclaration_2()); }
		('&')
		{ after(grammarAccess.getBinaryOperatorLevel3Access().getAndEnumLiteralDeclaration_2()); }
	)
	|
	(
		{ before(grammarAccess.getBinaryOperatorLevel3Access().getOrEnumLiteralDeclaration_3()); }
		('|')
		{ after(grammarAccess.getBinaryOperatorLevel3Access().getOrEnumLiteralDeclaration_3()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BinaryOperatorLevel4__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBinaryOperatorLevel4Access().getMultiplyEnumLiteralDeclaration_0()); }
		('*')
		{ after(grammarAccess.getBinaryOperatorLevel4Access().getMultiplyEnumLiteralDeclaration_0()); }
	)
	|
	(
		{ before(grammarAccess.getBinaryOperatorLevel4Access().getDivideEnumLiteralDeclaration_1()); }
		('/')
		{ after(grammarAccess.getBinaryOperatorLevel4Access().getDivideEnumLiteralDeclaration_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Poosl__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Poosl__Group__0__Impl
	rule__Poosl__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Poosl__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPooslAccess().getPooslAction_0()); }
	()
	{ after(grammarAccess.getPooslAccess().getPooslAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Poosl__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Poosl__Group__1__Impl
	rule__Poosl__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Poosl__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPooslAccess().getAlternatives_1()); }
	(rule__Poosl__Alternatives_1)*
	{ after(grammarAccess.getPooslAccess().getAlternatives_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Poosl__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Poosl__Group__2__Impl
	rule__Poosl__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Poosl__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPooslAccess().getAlternatives_2()); }
	(rule__Poosl__Alternatives_2)*
	{ after(grammarAccess.getPooslAccess().getAlternatives_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Poosl__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Poosl__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Poosl__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPooslAccess().getGroup_3()); }
	(rule__Poosl__Group_3__0)?
	{ after(grammarAccess.getPooslAccess().getGroup_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Poosl__Group_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Poosl__Group_3__0__Impl
	rule__Poosl__Group_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Poosl__Group_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPooslAccess().getClusterClassesAssignment_3_0()); }
	(rule__Poosl__ClusterClassesAssignment_3_0)
	{ after(grammarAccess.getPooslAccess().getClusterClassesAssignment_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Poosl__Group_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Poosl__Group_3__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Poosl__Group_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPooslAccess().getAlternatives_3_1()); }
	(rule__Poosl__Alternatives_3_1)*
	{ after(grammarAccess.getPooslAccess().getAlternatives_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Import__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Import__Group__0__Impl
	rule__Import__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Import__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getImportAccess().getImportKeyword_0()); }
	'import'
	{ after(grammarAccess.getImportAccess().getImportKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Import__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Import__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Import__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getImportAccess().getImportURIAssignment_1()); }
	(rule__Import__ImportURIAssignment_1)
	{ after(grammarAccess.getImportAccess().getImportURIAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ImportLib__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ImportLib__Group__0__Impl
	rule__ImportLib__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ImportLib__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getImportLibAccess().getImportlibKeyword_0()); }
	'importlib'
	{ after(grammarAccess.getImportLibAccess().getImportlibKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ImportLib__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ImportLib__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ImportLib__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getImportLibAccess().getImportURIAssignment_1()); }
	(rule__ImportLib__ImportURIAssignment_1)
	{ after(grammarAccess.getImportLibAccess().getImportURIAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DataClass__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataClass__Group__0__Impl
	rule__DataClass__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataClassAccess().getAnnotationsAssignment_0()); }
	(rule__DataClass__AnnotationsAssignment_0)*
	{ after(grammarAccess.getDataClassAccess().getAnnotationsAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataClass__Group__1__Impl
	rule__DataClass__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataClassAccess().getNativeAssignment_1()); }
	(rule__DataClass__NativeAssignment_1)?
	{ after(grammarAccess.getDataClassAccess().getNativeAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataClass__Group__2__Impl
	rule__DataClass__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataClassAccess().getDataKeyword_2()); }
	'data'
	{ after(grammarAccess.getDataClassAccess().getDataKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataClass__Group__3__Impl
	rule__DataClass__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataClassAccess().getClassKeyword_3()); }
	'class'
	{ after(grammarAccess.getDataClassAccess().getClassKeyword_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataClass__Group__4__Impl
	rule__DataClass__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataClassAccess().getNameAssignment_4()); }
	(rule__DataClass__NameAssignment_4)
	{ after(grammarAccess.getDataClassAccess().getNameAssignment_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataClass__Group__5__Impl
	rule__DataClass__Group__6
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataClassAccess().getGroup_5()); }
	(rule__DataClass__Group_5__0)?
	{ after(grammarAccess.getDataClassAccess().getGroup_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group__6
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataClass__Group__6__Impl
	rule__DataClass__Group__7
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group__6__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataClassAccess().getVariablesKeyword_6()); }
	'variables'
	{ after(grammarAccess.getDataClassAccess().getVariablesKeyword_6()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group__7
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataClass__Group__7__Impl
	rule__DataClass__Group__8
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group__7__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataClassAccess().getGroup_7()); }
	(rule__DataClass__Group_7__0)?
	{ after(grammarAccess.getDataClassAccess().getGroup_7()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group__8
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataClass__Group__8__Impl
	rule__DataClass__Group__9
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group__8__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataClassAccess().getMethodsKeyword_8()); }
	'methods'
	{ after(grammarAccess.getDataClassAccess().getMethodsKeyword_8()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group__9
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataClass__Group__9__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group__9__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataClassAccess().getAlternatives_9()); }
	(rule__DataClass__Alternatives_9)*
	{ after(grammarAccess.getDataClassAccess().getAlternatives_9()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DataClass__Group_5__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataClass__Group_5__0__Impl
	rule__DataClass__Group_5__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group_5__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataClassAccess().getExtendsKeyword_5_0()); }
	'extends'
	{ after(grammarAccess.getDataClassAccess().getExtendsKeyword_5_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group_5__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataClass__Group_5__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group_5__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataClassAccess().getSuperClassAssignment_5_1()); }
	(rule__DataClass__SuperClassAssignment_5_1)
	{ after(grammarAccess.getDataClassAccess().getSuperClassAssignment_5_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DataClass__Group_7__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataClass__Group_7__0__Impl
	rule__DataClass__Group_7__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group_7__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataClassAccess().getInstanceVariablesAssignment_7_0()); }
	(rule__DataClass__InstanceVariablesAssignment_7_0)
	{ after(grammarAccess.getDataClassAccess().getInstanceVariablesAssignment_7_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group_7__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataClass__Group_7__1__Impl
	rule__DataClass__Group_7__2
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group_7__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataClassAccess().getGroup_7_1()); }
	(rule__DataClass__Group_7_1__0)*
	{ after(grammarAccess.getDataClassAccess().getGroup_7_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group_7__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataClass__Group_7__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group_7__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataClassAccess().getCommaKeyword_7_2()); }
	(',')?
	{ after(grammarAccess.getDataClassAccess().getCommaKeyword_7_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DataClass__Group_7_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataClass__Group_7_1__0__Impl
	rule__DataClass__Group_7_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group_7_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataClassAccess().getCommaKeyword_7_1_0()); }
	(',')?
	{ after(grammarAccess.getDataClassAccess().getCommaKeyword_7_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group_7_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataClass__Group_7_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__Group_7_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataClassAccess().getInstanceVariablesAssignment_7_1_1()); }
	(rule__DataClass__InstanceVariablesAssignment_7_1_1)
	{ after(grammarAccess.getDataClassAccess().getInstanceVariablesAssignment_7_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DataMethodNamed__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodNamed__Group__0__Impl
	rule__DataMethodNamed__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodNamedAccess().getAnnotationsAssignment_0()); }
	(rule__DataMethodNamed__AnnotationsAssignment_0)*
	{ after(grammarAccess.getDataMethodNamedAccess().getAnnotationsAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodNamed__Group__1__Impl
	rule__DataMethodNamed__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodNamedAccess().getNativeAssignment_1()); }
	(rule__DataMethodNamed__NativeAssignment_1)?
	{ after(grammarAccess.getDataMethodNamedAccess().getNativeAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodNamed__Group__2__Impl
	rule__DataMethodNamed__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodNamedAccess().getNameAssignment_2()); }
	(rule__DataMethodNamed__NameAssignment_2)
	{ after(grammarAccess.getDataMethodNamedAccess().getNameAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodNamed__Group__3__Impl
	rule__DataMethodNamed__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodNamedAccess().getGroup_3()); }
	(rule__DataMethodNamed__Group_3__0)?
	{ after(grammarAccess.getDataMethodNamedAccess().getGroup_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodNamed__Group__4__Impl
	rule__DataMethodNamed__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodNamedAccess().getColonKeyword_4()); }
	':'
	{ after(grammarAccess.getDataMethodNamedAccess().getColonKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodNamed__Group__5__Impl
	rule__DataMethodNamed__Group__6
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodNamedAccess().getReturnTypeAssignment_5()); }
	(rule__DataMethodNamed__ReturnTypeAssignment_5)
	{ after(grammarAccess.getDataMethodNamedAccess().getReturnTypeAssignment_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group__6
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodNamed__Group__6__Impl
	rule__DataMethodNamed__Group__7
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group__6__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodNamedAccess().getGroup_6()); }
	(rule__DataMethodNamed__Group_6__0)?
	{ after(grammarAccess.getDataMethodNamedAccess().getGroup_6()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group__7
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodNamed__Group__7__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group__7__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodNamedAccess().getBodyAssignment_7()); }
	(rule__DataMethodNamed__BodyAssignment_7)?
	{ after(grammarAccess.getDataMethodNamedAccess().getBodyAssignment_7()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DataMethodNamed__Group_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodNamed__Group_3__0__Impl
	rule__DataMethodNamed__Group_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodNamedAccess().getLeftParenthesisKeyword_3_0()); }
	'('
	{ after(grammarAccess.getDataMethodNamedAccess().getLeftParenthesisKeyword_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodNamed__Group_3__1__Impl
	rule__DataMethodNamed__Group_3__2
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodNamedAccess().getGroup_3_1()); }
	(rule__DataMethodNamed__Group_3_1__0)?
	{ after(grammarAccess.getDataMethodNamedAccess().getGroup_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group_3__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodNamed__Group_3__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group_3__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodNamedAccess().getRightParenthesisKeyword_3_2()); }
	')'
	{ after(grammarAccess.getDataMethodNamedAccess().getRightParenthesisKeyword_3_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DataMethodNamed__Group_3_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodNamed__Group_3_1__0__Impl
	rule__DataMethodNamed__Group_3_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group_3_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodNamedAccess().getParametersAssignment_3_1_0()); }
	(rule__DataMethodNamed__ParametersAssignment_3_1_0)
	{ after(grammarAccess.getDataMethodNamedAccess().getParametersAssignment_3_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group_3_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodNamed__Group_3_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group_3_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodNamedAccess().getGroup_3_1_1()); }
	(rule__DataMethodNamed__Group_3_1_1__0)*
	{ after(grammarAccess.getDataMethodNamedAccess().getGroup_3_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DataMethodNamed__Group_3_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodNamed__Group_3_1_1__0__Impl
	rule__DataMethodNamed__Group_3_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group_3_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodNamedAccess().getCommaKeyword_3_1_1_0()); }
	','
	{ after(grammarAccess.getDataMethodNamedAccess().getCommaKeyword_3_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group_3_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodNamed__Group_3_1_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group_3_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodNamedAccess().getParametersAssignment_3_1_1_1()); }
	(rule__DataMethodNamed__ParametersAssignment_3_1_1_1)
	{ after(grammarAccess.getDataMethodNamedAccess().getParametersAssignment_3_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DataMethodNamed__Group_6__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodNamed__Group_6__0__Impl
	rule__DataMethodNamed__Group_6__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group_6__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodNamedAccess().getVerticalLineKeyword_6_0()); }
	'|'
	{ after(grammarAccess.getDataMethodNamedAccess().getVerticalLineKeyword_6_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group_6__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodNamed__Group_6__1__Impl
	rule__DataMethodNamed__Group_6__2
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group_6__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodNamedAccess().getGroup_6_1()); }
	(rule__DataMethodNamed__Group_6_1__0)?
	{ after(grammarAccess.getDataMethodNamedAccess().getGroup_6_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group_6__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodNamed__Group_6__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group_6__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodNamedAccess().getVerticalLineKeyword_6_2()); }
	'|'
	{ after(grammarAccess.getDataMethodNamedAccess().getVerticalLineKeyword_6_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DataMethodNamed__Group_6_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodNamed__Group_6_1__0__Impl
	rule__DataMethodNamed__Group_6_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group_6_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodNamedAccess().getLocalVariablesAssignment_6_1_0()); }
	(rule__DataMethodNamed__LocalVariablesAssignment_6_1_0)
	{ after(grammarAccess.getDataMethodNamedAccess().getLocalVariablesAssignment_6_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group_6_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodNamed__Group_6_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group_6_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodNamedAccess().getGroup_6_1_1()); }
	(rule__DataMethodNamed__Group_6_1_1__0)*
	{ after(grammarAccess.getDataMethodNamedAccess().getGroup_6_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DataMethodNamed__Group_6_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodNamed__Group_6_1_1__0__Impl
	rule__DataMethodNamed__Group_6_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group_6_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodNamedAccess().getCommaKeyword_6_1_1_0()); }
	','
	{ after(grammarAccess.getDataMethodNamedAccess().getCommaKeyword_6_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group_6_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodNamed__Group_6_1_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__Group_6_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodNamedAccess().getLocalVariablesAssignment_6_1_1_1()); }
	(rule__DataMethodNamed__LocalVariablesAssignment_6_1_1_1)
	{ after(grammarAccess.getDataMethodNamedAccess().getLocalVariablesAssignment_6_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DataMethodBinaryOperator__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodBinaryOperator__Group__0__Impl
	rule__DataMethodBinaryOperator__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getAnnotationsAssignment_0()); }
	(rule__DataMethodBinaryOperator__AnnotationsAssignment_0)*
	{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getAnnotationsAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodBinaryOperator__Group__1__Impl
	rule__DataMethodBinaryOperator__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getNativeAssignment_1()); }
	(rule__DataMethodBinaryOperator__NativeAssignment_1)?
	{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getNativeAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodBinaryOperator__Group__2__Impl
	rule__DataMethodBinaryOperator__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getNameAssignment_2()); }
	(rule__DataMethodBinaryOperator__NameAssignment_2)
	{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getNameAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodBinaryOperator__Group__3__Impl
	rule__DataMethodBinaryOperator__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getLeftParenthesisKeyword_3()); }
	'('
	{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getLeftParenthesisKeyword_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodBinaryOperator__Group__4__Impl
	rule__DataMethodBinaryOperator__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getParametersAssignment_4()); }
	(rule__DataMethodBinaryOperator__ParametersAssignment_4)
	{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getParametersAssignment_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodBinaryOperator__Group__5__Impl
	rule__DataMethodBinaryOperator__Group__6
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getGroup_5()); }
	(rule__DataMethodBinaryOperator__Group_5__0)*
	{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getGroup_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group__6
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodBinaryOperator__Group__6__Impl
	rule__DataMethodBinaryOperator__Group__7
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group__6__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getRightParenthesisKeyword_6()); }
	')'
	{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getRightParenthesisKeyword_6()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group__7
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodBinaryOperator__Group__7__Impl
	rule__DataMethodBinaryOperator__Group__8
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group__7__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getColonKeyword_7()); }
	':'
	{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getColonKeyword_7()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group__8
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodBinaryOperator__Group__8__Impl
	rule__DataMethodBinaryOperator__Group__9
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group__8__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getReturnTypeAssignment_8()); }
	(rule__DataMethodBinaryOperator__ReturnTypeAssignment_8)
	{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getReturnTypeAssignment_8()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group__9
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodBinaryOperator__Group__9__Impl
	rule__DataMethodBinaryOperator__Group__10
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group__9__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getGroup_9()); }
	(rule__DataMethodBinaryOperator__Group_9__0)?
	{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getGroup_9()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group__10
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodBinaryOperator__Group__10__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group__10__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getBodyAssignment_10()); }
	(rule__DataMethodBinaryOperator__BodyAssignment_10)?
	{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getBodyAssignment_10()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DataMethodBinaryOperator__Group_5__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodBinaryOperator__Group_5__0__Impl
	rule__DataMethodBinaryOperator__Group_5__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group_5__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getCommaKeyword_5_0()); }
	','
	{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getCommaKeyword_5_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group_5__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodBinaryOperator__Group_5__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group_5__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getParametersAssignment_5_1()); }
	(rule__DataMethodBinaryOperator__ParametersAssignment_5_1)
	{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getParametersAssignment_5_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DataMethodBinaryOperator__Group_9__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodBinaryOperator__Group_9__0__Impl
	rule__DataMethodBinaryOperator__Group_9__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group_9__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getVerticalLineKeyword_9_0()); }
	'|'
	{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getVerticalLineKeyword_9_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group_9__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodBinaryOperator__Group_9__1__Impl
	rule__DataMethodBinaryOperator__Group_9__2
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group_9__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getGroup_9_1()); }
	(rule__DataMethodBinaryOperator__Group_9_1__0)?
	{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getGroup_9_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group_9__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodBinaryOperator__Group_9__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group_9__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getVerticalLineKeyword_9_2()); }
	'|'
	{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getVerticalLineKeyword_9_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DataMethodBinaryOperator__Group_9_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodBinaryOperator__Group_9_1__0__Impl
	rule__DataMethodBinaryOperator__Group_9_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group_9_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getLocalVariablesAssignment_9_1_0()); }
	(rule__DataMethodBinaryOperator__LocalVariablesAssignment_9_1_0)
	{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getLocalVariablesAssignment_9_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group_9_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodBinaryOperator__Group_9_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group_9_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getGroup_9_1_1()); }
	(rule__DataMethodBinaryOperator__Group_9_1_1__0)*
	{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getGroup_9_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DataMethodBinaryOperator__Group_9_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodBinaryOperator__Group_9_1_1__0__Impl
	rule__DataMethodBinaryOperator__Group_9_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group_9_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getCommaKeyword_9_1_1_0()); }
	','
	{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getCommaKeyword_9_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group_9_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodBinaryOperator__Group_9_1_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__Group_9_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getLocalVariablesAssignment_9_1_1_1()); }
	(rule__DataMethodBinaryOperator__LocalVariablesAssignment_9_1_1_1)
	{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getLocalVariablesAssignment_9_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DataMethodUnaryOperator__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodUnaryOperator__Group__0__Impl
	rule__DataMethodUnaryOperator__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getAnnotationsAssignment_0()); }
	(rule__DataMethodUnaryOperator__AnnotationsAssignment_0)*
	{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getAnnotationsAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodUnaryOperator__Group__1__Impl
	rule__DataMethodUnaryOperator__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getNativeAssignment_1()); }
	(rule__DataMethodUnaryOperator__NativeAssignment_1)?
	{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getNativeAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodUnaryOperator__Group__2__Impl
	rule__DataMethodUnaryOperator__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getNameAssignment_2()); }
	(rule__DataMethodUnaryOperator__NameAssignment_2)
	{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getNameAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodUnaryOperator__Group__3__Impl
	rule__DataMethodUnaryOperator__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getGroup_3()); }
	(rule__DataMethodUnaryOperator__Group_3__0)?
	{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getGroup_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodUnaryOperator__Group__4__Impl
	rule__DataMethodUnaryOperator__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getColonKeyword_4()); }
	':'
	{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getColonKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodUnaryOperator__Group__5__Impl
	rule__DataMethodUnaryOperator__Group__6
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getReturnTypeAssignment_5()); }
	(rule__DataMethodUnaryOperator__ReturnTypeAssignment_5)
	{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getReturnTypeAssignment_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group__6
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodUnaryOperator__Group__6__Impl
	rule__DataMethodUnaryOperator__Group__7
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group__6__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getGroup_6()); }
	(rule__DataMethodUnaryOperator__Group_6__0)?
	{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getGroup_6()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group__7
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodUnaryOperator__Group__7__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group__7__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getBodyAssignment_7()); }
	(rule__DataMethodUnaryOperator__BodyAssignment_7)?
	{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getBodyAssignment_7()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DataMethodUnaryOperator__Group_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodUnaryOperator__Group_3__0__Impl
	rule__DataMethodUnaryOperator__Group_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getLeftParenthesisKeyword_3_0()); }
	'('
	{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getLeftParenthesisKeyword_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodUnaryOperator__Group_3__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getRightParenthesisKeyword_3_1()); }
	')'
	{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getRightParenthesisKeyword_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DataMethodUnaryOperator__Group_6__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodUnaryOperator__Group_6__0__Impl
	rule__DataMethodUnaryOperator__Group_6__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group_6__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getVerticalLineKeyword_6_0()); }
	'|'
	{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getVerticalLineKeyword_6_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group_6__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodUnaryOperator__Group_6__1__Impl
	rule__DataMethodUnaryOperator__Group_6__2
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group_6__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getGroup_6_1()); }
	(rule__DataMethodUnaryOperator__Group_6_1__0)?
	{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getGroup_6_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group_6__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodUnaryOperator__Group_6__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group_6__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getVerticalLineKeyword_6_2()); }
	'|'
	{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getVerticalLineKeyword_6_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DataMethodUnaryOperator__Group_6_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodUnaryOperator__Group_6_1__0__Impl
	rule__DataMethodUnaryOperator__Group_6_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group_6_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getLocalVariablesAssignment_6_1_0()); }
	(rule__DataMethodUnaryOperator__LocalVariablesAssignment_6_1_0)
	{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getLocalVariablesAssignment_6_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group_6_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodUnaryOperator__Group_6_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group_6_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getGroup_6_1_1()); }
	(rule__DataMethodUnaryOperator__Group_6_1_1__0)*
	{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getGroup_6_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DataMethodUnaryOperator__Group_6_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodUnaryOperator__Group_6_1_1__0__Impl
	rule__DataMethodUnaryOperator__Group_6_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group_6_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getCommaKeyword_6_1_1_0()); }
	','
	{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getCommaKeyword_6_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group_6_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DataMethodUnaryOperator__Group_6_1_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__Group_6_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getLocalVariablesAssignment_6_1_1_1()); }
	(rule__DataMethodUnaryOperator__LocalVariablesAssignment_6_1_1_1)
	{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getLocalVariablesAssignment_6_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Declaration__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Declaration__Group__0__Impl
	rule__Declaration__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Declaration__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDeclarationAccess().getGroup_0()); }
	(rule__Declaration__Group_0__0)?
	{ after(grammarAccess.getDeclarationAccess().getGroup_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Declaration__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Declaration__Group__1__Impl
	rule__Declaration__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Declaration__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDeclarationAccess().getColonKeyword_1()); }
	':'
	{ after(grammarAccess.getDeclarationAccess().getColonKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Declaration__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Declaration__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Declaration__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDeclarationAccess().getTypeAssignment_2()); }
	(rule__Declaration__TypeAssignment_2)
	{ after(grammarAccess.getDeclarationAccess().getTypeAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Declaration__Group_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Declaration__Group_0__0__Impl
	rule__Declaration__Group_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Declaration__Group_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDeclarationAccess().getVariablesAssignment_0_0()); }
	(rule__Declaration__VariablesAssignment_0_0)
	{ after(grammarAccess.getDeclarationAccess().getVariablesAssignment_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Declaration__Group_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Declaration__Group_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Declaration__Group_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDeclarationAccess().getGroup_0_1()); }
	(rule__Declaration__Group_0_1__0)*
	{ after(grammarAccess.getDeclarationAccess().getGroup_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Declaration__Group_0_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Declaration__Group_0_1__0__Impl
	rule__Declaration__Group_0_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Declaration__Group_0_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDeclarationAccess().getCommaKeyword_0_1_0()); }
	','
	{ after(grammarAccess.getDeclarationAccess().getCommaKeyword_0_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Declaration__Group_0_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Declaration__Group_0_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Declaration__Group_0_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDeclarationAccess().getVariablesAssignment_0_1_1()); }
	(rule__Declaration__VariablesAssignment_0_1_1)
	{ after(grammarAccess.getDeclarationAccess().getVariablesAssignment_0_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Annotation__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Annotation__Group__0__Impl
	rule__Annotation__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Annotation__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAnnotationAccess().getCommercialAtKeyword_0()); }
	'@'
	{ after(grammarAccess.getAnnotationAccess().getCommercialAtKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Annotation__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Annotation__Group__1__Impl
	rule__Annotation__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Annotation__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAnnotationAccess().getNameAssignment_1()); }
	(rule__Annotation__NameAssignment_1)
	{ after(grammarAccess.getAnnotationAccess().getNameAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Annotation__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Annotation__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Annotation__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAnnotationAccess().getGroup_2()); }
	(rule__Annotation__Group_2__0)?
	{ after(grammarAccess.getAnnotationAccess().getGroup_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Annotation__Group_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Annotation__Group_2__0__Impl
	rule__Annotation__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Annotation__Group_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAnnotationAccess().getLeftParenthesisKeyword_2_0()); }
	'('
	{ after(grammarAccess.getAnnotationAccess().getLeftParenthesisKeyword_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Annotation__Group_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Annotation__Group_2__1__Impl
	rule__Annotation__Group_2__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Annotation__Group_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAnnotationAccess().getGroup_2_1()); }
	(rule__Annotation__Group_2_1__0)?
	{ after(grammarAccess.getAnnotationAccess().getGroup_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Annotation__Group_2__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Annotation__Group_2__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Annotation__Group_2__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAnnotationAccess().getRightParenthesisKeyword_2_2()); }
	')'
	{ after(grammarAccess.getAnnotationAccess().getRightParenthesisKeyword_2_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Annotation__Group_2_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Annotation__Group_2_1__0__Impl
	rule__Annotation__Group_2_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Annotation__Group_2_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAnnotationAccess().getArgumentsAssignment_2_1_0()); }
	(rule__Annotation__ArgumentsAssignment_2_1_0)
	{ after(grammarAccess.getAnnotationAccess().getArgumentsAssignment_2_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Annotation__Group_2_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Annotation__Group_2_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Annotation__Group_2_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAnnotationAccess().getGroup_2_1_1()); }
	(rule__Annotation__Group_2_1_1__0)*
	{ after(grammarAccess.getAnnotationAccess().getGroup_2_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Annotation__Group_2_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Annotation__Group_2_1_1__0__Impl
	rule__Annotation__Group_2_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Annotation__Group_2_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAnnotationAccess().getCommaKeyword_2_1_1_0()); }
	','
	{ after(grammarAccess.getAnnotationAccess().getCommaKeyword_2_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Annotation__Group_2_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Annotation__Group_2_1_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Annotation__Group_2_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAnnotationAccess().getArgumentsAssignment_2_1_1_1()); }
	(rule__Annotation__ArgumentsAssignment_2_1_1_1)
	{ after(grammarAccess.getAnnotationAccess().getArgumentsAssignment_2_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessClass__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group__0__Impl
	rule__ProcessClass__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getAnnotationsAssignment_0()); }
	(rule__ProcessClass__AnnotationsAssignment_0)*
	{ after(grammarAccess.getProcessClassAccess().getAnnotationsAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group__1__Impl
	rule__ProcessClass__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getProcessKeyword_1()); }
	'process'
	{ after(grammarAccess.getProcessClassAccess().getProcessKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group__2__Impl
	rule__ProcessClass__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getClassKeyword_2()); }
	'class'
	{ after(grammarAccess.getProcessClassAccess().getClassKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group__3__Impl
	rule__ProcessClass__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getNameAssignment_3()); }
	(rule__ProcessClass__NameAssignment_3)
	{ after(grammarAccess.getProcessClassAccess().getNameAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group__4__Impl
	rule__ProcessClass__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getGroup_4()); }
	(rule__ProcessClass__Group_4__0)?
	{ after(grammarAccess.getProcessClassAccess().getGroup_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group__5__Impl
	rule__ProcessClass__Group__6
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getGroup_5()); }
	(rule__ProcessClass__Group_5__0)?
	{ after(grammarAccess.getProcessClassAccess().getGroup_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__6
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group__6__Impl
	rule__ProcessClass__Group__7
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__6__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getPortsKeyword_6()); }
	'ports'
	{ after(grammarAccess.getProcessClassAccess().getPortsKeyword_6()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__7
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group__7__Impl
	rule__ProcessClass__Group__8
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__7__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getGroup_7()); }
	(rule__ProcessClass__Group_7__0)?
	{ after(grammarAccess.getProcessClassAccess().getGroup_7()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__8
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group__8__Impl
	rule__ProcessClass__Group__9
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__8__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getMessagesKeyword_8()); }
	'messages'
	{ after(grammarAccess.getProcessClassAccess().getMessagesKeyword_8()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__9
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group__9__Impl
	rule__ProcessClass__Group__10
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__9__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getGroup_9()); }
	(rule__ProcessClass__Group_9__0)?
	{ after(grammarAccess.getProcessClassAccess().getGroup_9()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__10
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group__10__Impl
	rule__ProcessClass__Group__11
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__10__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getVariablesKeyword_10()); }
	'variables'
	{ after(grammarAccess.getProcessClassAccess().getVariablesKeyword_10()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__11
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group__11__Impl
	rule__ProcessClass__Group__12
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__11__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getGroup_11()); }
	(rule__ProcessClass__Group_11__0)?
	{ after(grammarAccess.getProcessClassAccess().getGroup_11()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__12
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group__12__Impl
	rule__ProcessClass__Group__13
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__12__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getInitKeyword_12()); }
	'init'
	{ after(grammarAccess.getProcessClassAccess().getInitKeyword_12()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__13
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group__13__Impl
	rule__ProcessClass__Group__14
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__13__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getInitialMethodCallAssignment_13()); }
	(rule__ProcessClass__InitialMethodCallAssignment_13)?
	{ after(grammarAccess.getProcessClassAccess().getInitialMethodCallAssignment_13()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__14
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group__14__Impl
	rule__ProcessClass__Group__15
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__14__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getMethodsKeyword_14()); }
	'methods'
	{ after(grammarAccess.getProcessClassAccess().getMethodsKeyword_14()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__15
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group__15__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group__15__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getMethodsAssignment_15()); }
	(rule__ProcessClass__MethodsAssignment_15)*
	{ after(grammarAccess.getProcessClassAccess().getMethodsAssignment_15()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessClass__Group_4__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_4__0__Impl
	rule__ProcessClass__Group_4__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_4__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getLeftParenthesisKeyword_4_0()); }
	'('
	{ after(grammarAccess.getProcessClassAccess().getLeftParenthesisKeyword_4_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_4__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_4__1__Impl
	rule__ProcessClass__Group_4__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_4__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getGroup_4_1()); }
	(rule__ProcessClass__Group_4_1__0)?
	{ after(grammarAccess.getProcessClassAccess().getGroup_4_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_4__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_4__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_4__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getRightParenthesisKeyword_4_2()); }
	')'
	{ after(grammarAccess.getProcessClassAccess().getRightParenthesisKeyword_4_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessClass__Group_4_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_4_1__0__Impl
	rule__ProcessClass__Group_4_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_4_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getParametersAssignment_4_1_0()); }
	(rule__ProcessClass__ParametersAssignment_4_1_0)
	{ after(grammarAccess.getProcessClassAccess().getParametersAssignment_4_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_4_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_4_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_4_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getGroup_4_1_1()); }
	(rule__ProcessClass__Group_4_1_1__0)*
	{ after(grammarAccess.getProcessClassAccess().getGroup_4_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessClass__Group_4_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_4_1_1__0__Impl
	rule__ProcessClass__Group_4_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_4_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getCommaKeyword_4_1_1_0()); }
	','
	{ after(grammarAccess.getProcessClassAccess().getCommaKeyword_4_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_4_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_4_1_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_4_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getParametersAssignment_4_1_1_1()); }
	(rule__ProcessClass__ParametersAssignment_4_1_1_1)
	{ after(grammarAccess.getProcessClassAccess().getParametersAssignment_4_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessClass__Group_5__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_5__0__Impl
	rule__ProcessClass__Group_5__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_5__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getExtendsKeyword_5_0()); }
	'extends'
	{ after(grammarAccess.getProcessClassAccess().getExtendsKeyword_5_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_5__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_5__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_5__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getSuperClassAssignment_5_1()); }
	(rule__ProcessClass__SuperClassAssignment_5_1)
	{ after(grammarAccess.getProcessClassAccess().getSuperClassAssignment_5_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessClass__Group_7__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_7__0__Impl
	rule__ProcessClass__Group_7__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_7__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getPortsAssignment_7_0()); }
	(rule__ProcessClass__PortsAssignment_7_0)
	{ after(grammarAccess.getProcessClassAccess().getPortsAssignment_7_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_7__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_7__1__Impl
	rule__ProcessClass__Group_7__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_7__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getGroup_7_1()); }
	(rule__ProcessClass__Group_7_1__0)*
	{ after(grammarAccess.getProcessClassAccess().getGroup_7_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_7__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_7__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_7__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getCommaKeyword_7_2()); }
	(',')?
	{ after(grammarAccess.getProcessClassAccess().getCommaKeyword_7_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessClass__Group_7_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_7_1__0__Impl
	rule__ProcessClass__Group_7_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_7_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getCommaKeyword_7_1_0()); }
	(',')?
	{ after(grammarAccess.getProcessClassAccess().getCommaKeyword_7_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_7_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_7_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_7_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getPortsAssignment_7_1_1()); }
	(rule__ProcessClass__PortsAssignment_7_1_1)
	{ after(grammarAccess.getProcessClassAccess().getPortsAssignment_7_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessClass__Group_9__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_9__0__Impl
	rule__ProcessClass__Group_9__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_9__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getAlternatives_9_0()); }
	(rule__ProcessClass__Alternatives_9_0)
	{ after(grammarAccess.getProcessClassAccess().getAlternatives_9_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_9__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_9__1__Impl
	rule__ProcessClass__Group_9__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_9__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getGroup_9_1()); }
	(rule__ProcessClass__Group_9_1__0)*
	{ after(grammarAccess.getProcessClassAccess().getGroup_9_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_9__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_9__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_9__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getCommaKeyword_9_2()); }
	(',')?
	{ after(grammarAccess.getProcessClassAccess().getCommaKeyword_9_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessClass__Group_9_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_9_1__0__Impl
	rule__ProcessClass__Group_9_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_9_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getCommaKeyword_9_1_0()); }
	(',')?
	{ after(grammarAccess.getProcessClassAccess().getCommaKeyword_9_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_9_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_9_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_9_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getAlternatives_9_1_1()); }
	(rule__ProcessClass__Alternatives_9_1_1)
	{ after(grammarAccess.getProcessClassAccess().getAlternatives_9_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessClass__Group_11__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_11__0__Impl
	rule__ProcessClass__Group_11__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_11__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getInstanceVariablesAssignment_11_0()); }
	(rule__ProcessClass__InstanceVariablesAssignment_11_0)
	{ after(grammarAccess.getProcessClassAccess().getInstanceVariablesAssignment_11_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_11__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_11__1__Impl
	rule__ProcessClass__Group_11__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_11__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getGroup_11_1()); }
	(rule__ProcessClass__Group_11_1__0)*
	{ after(grammarAccess.getProcessClassAccess().getGroup_11_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_11__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_11__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_11__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getCommaKeyword_11_2()); }
	(',')?
	{ after(grammarAccess.getProcessClassAccess().getCommaKeyword_11_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessClass__Group_11_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_11_1__0__Impl
	rule__ProcessClass__Group_11_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_11_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getCommaKeyword_11_1_0()); }
	(',')?
	{ after(grammarAccess.getProcessClassAccess().getCommaKeyword_11_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_11_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessClass__Group_11_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__Group_11_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessClassAccess().getInstanceVariablesAssignment_11_1_1()); }
	(rule__ProcessClass__InstanceVariablesAssignment_11_1_1)
	{ after(grammarAccess.getProcessClassAccess().getInstanceVariablesAssignment_11_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessMethod__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group__0__Impl
	rule__ProcessMethod__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getAnnotationsAssignment_0()); }
	(rule__ProcessMethod__AnnotationsAssignment_0)*
	{ after(grammarAccess.getProcessMethodAccess().getAnnotationsAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group__1__Impl
	rule__ProcessMethod__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getNameAssignment_1()); }
	(rule__ProcessMethod__NameAssignment_1)
	{ after(grammarAccess.getProcessMethodAccess().getNameAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group__2__Impl
	rule__ProcessMethod__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getLeftParenthesisKeyword_2()); }
	'('
	{ after(grammarAccess.getProcessMethodAccess().getLeftParenthesisKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group__3__Impl
	rule__ProcessMethod__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getGroup_3()); }
	(rule__ProcessMethod__Group_3__0)?
	{ after(grammarAccess.getProcessMethodAccess().getGroup_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group__4__Impl
	rule__ProcessMethod__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getRightParenthesisKeyword_4()); }
	')'
	{ after(grammarAccess.getProcessMethodAccess().getRightParenthesisKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group__5__Impl
	rule__ProcessMethod__Group__6
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getLeftParenthesisKeyword_5()); }
	'('
	{ after(grammarAccess.getProcessMethodAccess().getLeftParenthesisKeyword_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group__6
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group__6__Impl
	rule__ProcessMethod__Group__7
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group__6__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getGroup_6()); }
	(rule__ProcessMethod__Group_6__0)?
	{ after(grammarAccess.getProcessMethodAccess().getGroup_6()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group__7
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group__7__Impl
	rule__ProcessMethod__Group__8
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group__7__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getRightParenthesisKeyword_7()); }
	')'
	{ after(grammarAccess.getProcessMethodAccess().getRightParenthesisKeyword_7()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group__8
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group__8__Impl
	rule__ProcessMethod__Group__9
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group__8__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getGroup_8()); }
	(rule__ProcessMethod__Group_8__0)?
	{ after(grammarAccess.getProcessMethodAccess().getGroup_8()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group__9
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group__9__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group__9__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getBodyAssignment_9()); }
	(rule__ProcessMethod__BodyAssignment_9)
	{ after(grammarAccess.getProcessMethodAccess().getBodyAssignment_9()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessMethod__Group_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group_3__0__Impl
	rule__ProcessMethod__Group_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getInputParametersAssignment_3_0()); }
	(rule__ProcessMethod__InputParametersAssignment_3_0)
	{ after(grammarAccess.getProcessMethodAccess().getInputParametersAssignment_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group_3__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getGroup_3_1()); }
	(rule__ProcessMethod__Group_3_1__0)*
	{ after(grammarAccess.getProcessMethodAccess().getGroup_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessMethod__Group_3_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group_3_1__0__Impl
	rule__ProcessMethod__Group_3_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_3_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getCommaKeyword_3_1_0()); }
	','
	{ after(grammarAccess.getProcessMethodAccess().getCommaKeyword_3_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_3_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group_3_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_3_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getInputParametersAssignment_3_1_1()); }
	(rule__ProcessMethod__InputParametersAssignment_3_1_1)
	{ after(grammarAccess.getProcessMethodAccess().getInputParametersAssignment_3_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessMethod__Group_6__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group_6__0__Impl
	rule__ProcessMethod__Group_6__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_6__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getOutputParametersAssignment_6_0()); }
	(rule__ProcessMethod__OutputParametersAssignment_6_0)
	{ after(grammarAccess.getProcessMethodAccess().getOutputParametersAssignment_6_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_6__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group_6__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_6__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getGroup_6_1()); }
	(rule__ProcessMethod__Group_6_1__0)*
	{ after(grammarAccess.getProcessMethodAccess().getGroup_6_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessMethod__Group_6_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group_6_1__0__Impl
	rule__ProcessMethod__Group_6_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_6_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getCommaKeyword_6_1_0()); }
	','
	{ after(grammarAccess.getProcessMethodAccess().getCommaKeyword_6_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_6_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group_6_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_6_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getOutputParametersAssignment_6_1_1()); }
	(rule__ProcessMethod__OutputParametersAssignment_6_1_1)
	{ after(grammarAccess.getProcessMethodAccess().getOutputParametersAssignment_6_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessMethod__Group_8__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group_8__0__Impl
	rule__ProcessMethod__Group_8__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_8__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getVerticalLineKeyword_8_0()); }
	'|'
	{ after(grammarAccess.getProcessMethodAccess().getVerticalLineKeyword_8_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_8__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group_8__1__Impl
	rule__ProcessMethod__Group_8__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_8__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getGroup_8_1()); }
	(rule__ProcessMethod__Group_8_1__0)?
	{ after(grammarAccess.getProcessMethodAccess().getGroup_8_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_8__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group_8__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_8__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getVerticalLineKeyword_8_2()); }
	'|'
	{ after(grammarAccess.getProcessMethodAccess().getVerticalLineKeyword_8_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessMethod__Group_8_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group_8_1__0__Impl
	rule__ProcessMethod__Group_8_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_8_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getLocalVariablesAssignment_8_1_0()); }
	(rule__ProcessMethod__LocalVariablesAssignment_8_1_0)
	{ after(grammarAccess.getProcessMethodAccess().getLocalVariablesAssignment_8_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_8_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group_8_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_8_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getGroup_8_1_1()); }
	(rule__ProcessMethod__Group_8_1_1__0)*
	{ after(grammarAccess.getProcessMethodAccess().getGroup_8_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessMethod__Group_8_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group_8_1_1__0__Impl
	rule__ProcessMethod__Group_8_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_8_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getCommaKeyword_8_1_1_0()); }
	','
	{ after(grammarAccess.getProcessMethodAccess().getCommaKeyword_8_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_8_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethod__Group_8_1_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__Group_8_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodAccess().getLocalVariablesAssignment_8_1_1_1()); }
	(rule__ProcessMethod__LocalVariablesAssignment_8_1_1_1)
	{ after(grammarAccess.getProcessMethodAccess().getLocalVariablesAssignment_8_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__MessageReceiveSignature__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MessageReceiveSignature__Group__0__Impl
	rule__MessageReceiveSignature__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageReceiveSignature__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMessageReceiveSignatureAccess().getPortAssignment_0()); }
	(rule__MessageReceiveSignature__PortAssignment_0)
	{ after(grammarAccess.getMessageReceiveSignatureAccess().getPortAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageReceiveSignature__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MessageReceiveSignature__Group__1__Impl
	rule__MessageReceiveSignature__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageReceiveSignature__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMessageReceiveSignatureAccess().getQuestionMarkKeyword_1()); }
	'?'
	{ after(grammarAccess.getMessageReceiveSignatureAccess().getQuestionMarkKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageReceiveSignature__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MessageReceiveSignature__Group__2__Impl
	rule__MessageReceiveSignature__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageReceiveSignature__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMessageReceiveSignatureAccess().getNameAssignment_2()); }
	(rule__MessageReceiveSignature__NameAssignment_2)
	{ after(grammarAccess.getMessageReceiveSignatureAccess().getNameAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageReceiveSignature__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MessageReceiveSignature__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageReceiveSignature__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMessageReceiveSignatureAccess().getGroup_3()); }
	(rule__MessageReceiveSignature__Group_3__0)?
	{ after(grammarAccess.getMessageReceiveSignatureAccess().getGroup_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__MessageReceiveSignature__Group_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MessageReceiveSignature__Group_3__0__Impl
	rule__MessageReceiveSignature__Group_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageReceiveSignature__Group_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMessageReceiveSignatureAccess().getLeftParenthesisKeyword_3_0()); }
	'('
	{ after(grammarAccess.getMessageReceiveSignatureAccess().getLeftParenthesisKeyword_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageReceiveSignature__Group_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MessageReceiveSignature__Group_3__1__Impl
	rule__MessageReceiveSignature__Group_3__2
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageReceiveSignature__Group_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMessageReceiveSignatureAccess().getGroup_3_1()); }
	(rule__MessageReceiveSignature__Group_3_1__0)?
	{ after(grammarAccess.getMessageReceiveSignatureAccess().getGroup_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageReceiveSignature__Group_3__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MessageReceiveSignature__Group_3__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageReceiveSignature__Group_3__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMessageReceiveSignatureAccess().getRightParenthesisKeyword_3_2()); }
	')'
	{ after(grammarAccess.getMessageReceiveSignatureAccess().getRightParenthesisKeyword_3_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__MessageReceiveSignature__Group_3_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MessageReceiveSignature__Group_3_1__0__Impl
	rule__MessageReceiveSignature__Group_3_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageReceiveSignature__Group_3_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMessageReceiveSignatureAccess().getParametersAssignment_3_1_0()); }
	(rule__MessageReceiveSignature__ParametersAssignment_3_1_0)
	{ after(grammarAccess.getMessageReceiveSignatureAccess().getParametersAssignment_3_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageReceiveSignature__Group_3_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MessageReceiveSignature__Group_3_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageReceiveSignature__Group_3_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMessageReceiveSignatureAccess().getGroup_3_1_1()); }
	(rule__MessageReceiveSignature__Group_3_1_1__0)*
	{ after(grammarAccess.getMessageReceiveSignatureAccess().getGroup_3_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__MessageReceiveSignature__Group_3_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MessageReceiveSignature__Group_3_1_1__0__Impl
	rule__MessageReceiveSignature__Group_3_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageReceiveSignature__Group_3_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMessageReceiveSignatureAccess().getCommaKeyword_3_1_1_0()); }
	','
	{ after(grammarAccess.getMessageReceiveSignatureAccess().getCommaKeyword_3_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageReceiveSignature__Group_3_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MessageReceiveSignature__Group_3_1_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageReceiveSignature__Group_3_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMessageReceiveSignatureAccess().getParametersAssignment_3_1_1_1()); }
	(rule__MessageReceiveSignature__ParametersAssignment_3_1_1_1)
	{ after(grammarAccess.getMessageReceiveSignatureAccess().getParametersAssignment_3_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__MessageSendSignature__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MessageSendSignature__Group__0__Impl
	rule__MessageSendSignature__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageSendSignature__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMessageSendSignatureAccess().getPortAssignment_0()); }
	(rule__MessageSendSignature__PortAssignment_0)
	{ after(grammarAccess.getMessageSendSignatureAccess().getPortAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageSendSignature__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MessageSendSignature__Group__1__Impl
	rule__MessageSendSignature__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageSendSignature__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMessageSendSignatureAccess().getExclamationMarkKeyword_1()); }
	'!'
	{ after(grammarAccess.getMessageSendSignatureAccess().getExclamationMarkKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageSendSignature__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MessageSendSignature__Group__2__Impl
	rule__MessageSendSignature__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageSendSignature__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMessageSendSignatureAccess().getNameAssignment_2()); }
	(rule__MessageSendSignature__NameAssignment_2)
	{ after(grammarAccess.getMessageSendSignatureAccess().getNameAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageSendSignature__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MessageSendSignature__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageSendSignature__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMessageSendSignatureAccess().getGroup_3()); }
	(rule__MessageSendSignature__Group_3__0)?
	{ after(grammarAccess.getMessageSendSignatureAccess().getGroup_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__MessageSendSignature__Group_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MessageSendSignature__Group_3__0__Impl
	rule__MessageSendSignature__Group_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageSendSignature__Group_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMessageSendSignatureAccess().getLeftParenthesisKeyword_3_0()); }
	'('
	{ after(grammarAccess.getMessageSendSignatureAccess().getLeftParenthesisKeyword_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageSendSignature__Group_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MessageSendSignature__Group_3__1__Impl
	rule__MessageSendSignature__Group_3__2
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageSendSignature__Group_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMessageSendSignatureAccess().getGroup_3_1()); }
	(rule__MessageSendSignature__Group_3_1__0)?
	{ after(grammarAccess.getMessageSendSignatureAccess().getGroup_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageSendSignature__Group_3__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MessageSendSignature__Group_3__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageSendSignature__Group_3__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMessageSendSignatureAccess().getRightParenthesisKeyword_3_2()); }
	')'
	{ after(grammarAccess.getMessageSendSignatureAccess().getRightParenthesisKeyword_3_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__MessageSendSignature__Group_3_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MessageSendSignature__Group_3_1__0__Impl
	rule__MessageSendSignature__Group_3_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageSendSignature__Group_3_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMessageSendSignatureAccess().getParametersAssignment_3_1_0()); }
	(rule__MessageSendSignature__ParametersAssignment_3_1_0)
	{ after(grammarAccess.getMessageSendSignatureAccess().getParametersAssignment_3_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageSendSignature__Group_3_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MessageSendSignature__Group_3_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageSendSignature__Group_3_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMessageSendSignatureAccess().getGroup_3_1_1()); }
	(rule__MessageSendSignature__Group_3_1_1__0)*
	{ after(grammarAccess.getMessageSendSignatureAccess().getGroup_3_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__MessageSendSignature__Group_3_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MessageSendSignature__Group_3_1_1__0__Impl
	rule__MessageSendSignature__Group_3_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageSendSignature__Group_3_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMessageSendSignatureAccess().getCommaKeyword_3_1_1_0()); }
	','
	{ after(grammarAccess.getMessageSendSignatureAccess().getCommaKeyword_3_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageSendSignature__Group_3_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MessageSendSignature__Group_3_1_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageSendSignature__Group_3_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMessageSendSignatureAccess().getParametersAssignment_3_1_1_1()); }
	(rule__MessageSendSignature__ParametersAssignment_3_1_1_1)
	{ after(grammarAccess.getMessageSendSignatureAccess().getParametersAssignment_3_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__System__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group__0__Impl
	rule__System__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getClusterClassAction_0()); }
	()
	{ after(grammarAccess.getSystemAccess().getClusterClassAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group__1__Impl
	rule__System__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getAnnotationsAssignment_1()); }
	(rule__System__AnnotationsAssignment_1)*
	{ after(grammarAccess.getSystemAccess().getAnnotationsAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group__2__Impl
	rule__System__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getSystemKeyword_2()); }
	'system'
	{ after(grammarAccess.getSystemAccess().getSystemKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group__3__Impl
	rule__System__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getGroup_3()); }
	(rule__System__Group_3__0)?
	{ after(grammarAccess.getSystemAccess().getGroup_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group__4__Impl
	rule__System__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getGroup_4()); }
	(rule__System__Group_4__0)?
	{ after(grammarAccess.getSystemAccess().getGroup_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group__5__Impl
	rule__System__Group__6
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getInstancesKeyword_5()); }
	'instances'
	{ after(grammarAccess.getSystemAccess().getInstancesKeyword_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group__6
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group__6__Impl
	rule__System__Group__7
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group__6__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getInstancesAssignment_6()); }
	(rule__System__InstancesAssignment_6)*
	{ after(grammarAccess.getSystemAccess().getInstancesAssignment_6()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group__7
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group__7__Impl
	rule__System__Group__8
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group__7__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getChannelsKeyword_7()); }
	'channels'
	{ after(grammarAccess.getSystemAccess().getChannelsKeyword_7()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group__8
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group__8__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group__8__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getChannelsAssignment_8()); }
	(rule__System__ChannelsAssignment_8)*
	{ after(grammarAccess.getSystemAccess().getChannelsAssignment_8()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__System__Group_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group_3__0__Impl
	rule__System__Group_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getLeftParenthesisKeyword_3_0()); }
	'('
	{ after(grammarAccess.getSystemAccess().getLeftParenthesisKeyword_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group_3__1__Impl
	rule__System__Group_3__2
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getGroup_3_1()); }
	(rule__System__Group_3_1__0)?
	{ after(grammarAccess.getSystemAccess().getGroup_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group_3__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group_3__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group_3__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getRightParenthesisKeyword_3_2()); }
	')'
	{ after(grammarAccess.getSystemAccess().getRightParenthesisKeyword_3_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__System__Group_3_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group_3_1__0__Impl
	rule__System__Group_3_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group_3_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getParametersAssignment_3_1_0()); }
	(rule__System__ParametersAssignment_3_1_0)
	{ after(grammarAccess.getSystemAccess().getParametersAssignment_3_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group_3_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group_3_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group_3_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getGroup_3_1_1()); }
	(rule__System__Group_3_1_1__0)*
	{ after(grammarAccess.getSystemAccess().getGroup_3_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__System__Group_3_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group_3_1_1__0__Impl
	rule__System__Group_3_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group_3_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getCommaKeyword_3_1_1_0()); }
	','
	{ after(grammarAccess.getSystemAccess().getCommaKeyword_3_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group_3_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group_3_1_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group_3_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getParametersAssignment_3_1_1_1()); }
	(rule__System__ParametersAssignment_3_1_1_1)
	{ after(grammarAccess.getSystemAccess().getParametersAssignment_3_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__System__Group_4__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group_4__0__Impl
	rule__System__Group_4__1
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group_4__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getPortsKeyword_4_0()); }
	'ports'
	{ after(grammarAccess.getSystemAccess().getPortsKeyword_4_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group_4__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group_4__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group_4__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getGroup_4_1()); }
	(rule__System__Group_4_1__0)?
	{ after(grammarAccess.getSystemAccess().getGroup_4_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__System__Group_4_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group_4_1__0__Impl
	rule__System__Group_4_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group_4_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getPortsAssignment_4_1_0()); }
	(rule__System__PortsAssignment_4_1_0)
	{ after(grammarAccess.getSystemAccess().getPortsAssignment_4_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group_4_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group_4_1__1__Impl
	rule__System__Group_4_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group_4_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getGroup_4_1_1()); }
	(rule__System__Group_4_1_1__0)*
	{ after(grammarAccess.getSystemAccess().getGroup_4_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group_4_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group_4_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group_4_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getCommaKeyword_4_1_2()); }
	(',')?
	{ after(grammarAccess.getSystemAccess().getCommaKeyword_4_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__System__Group_4_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group_4_1_1__0__Impl
	rule__System__Group_4_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group_4_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getCommaKeyword_4_1_1_0()); }
	(',')?
	{ after(grammarAccess.getSystemAccess().getCommaKeyword_4_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group_4_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__System__Group_4_1_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__System__Group_4_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSystemAccess().getPortsAssignment_4_1_1_1()); }
	(rule__System__PortsAssignment_4_1_1_1)
	{ after(grammarAccess.getSystemAccess().getPortsAssignment_4_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ClusterClass__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group__0__Impl
	rule__ClusterClass__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getAnnotationsAssignment_0()); }
	(rule__ClusterClass__AnnotationsAssignment_0)*
	{ after(grammarAccess.getClusterClassAccess().getAnnotationsAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group__1__Impl
	rule__ClusterClass__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getClusterKeyword_1()); }
	'cluster'
	{ after(grammarAccess.getClusterClassAccess().getClusterKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group__2__Impl
	rule__ClusterClass__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getClassKeyword_2()); }
	'class'
	{ after(grammarAccess.getClusterClassAccess().getClassKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group__3__Impl
	rule__ClusterClass__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getNameAssignment_3()); }
	(rule__ClusterClass__NameAssignment_3)
	{ after(grammarAccess.getClusterClassAccess().getNameAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group__4__Impl
	rule__ClusterClass__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getGroup_4()); }
	(rule__ClusterClass__Group_4__0)?
	{ after(grammarAccess.getClusterClassAccess().getGroup_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group__5__Impl
	rule__ClusterClass__Group__6
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getPortsKeyword_5()); }
	'ports'
	{ after(grammarAccess.getClusterClassAccess().getPortsKeyword_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group__6
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group__6__Impl
	rule__ClusterClass__Group__7
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group__6__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getGroup_6()); }
	(rule__ClusterClass__Group_6__0)?
	{ after(grammarAccess.getClusterClassAccess().getGroup_6()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group__7
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group__7__Impl
	rule__ClusterClass__Group__8
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group__7__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getInstancesKeyword_7()); }
	'instances'
	{ after(grammarAccess.getClusterClassAccess().getInstancesKeyword_7()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group__8
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group__8__Impl
	rule__ClusterClass__Group__9
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group__8__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getInstancesAssignment_8()); }
	(rule__ClusterClass__InstancesAssignment_8)*
	{ after(grammarAccess.getClusterClassAccess().getInstancesAssignment_8()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group__9
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group__9__Impl
	rule__ClusterClass__Group__10
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group__9__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getChannelsKeyword_9()); }
	'channels'
	{ after(grammarAccess.getClusterClassAccess().getChannelsKeyword_9()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group__10
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group__10__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group__10__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getChannelsAssignment_10()); }
	(rule__ClusterClass__ChannelsAssignment_10)*
	{ after(grammarAccess.getClusterClassAccess().getChannelsAssignment_10()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ClusterClass__Group_4__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group_4__0__Impl
	rule__ClusterClass__Group_4__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group_4__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getLeftParenthesisKeyword_4_0()); }
	'('
	{ after(grammarAccess.getClusterClassAccess().getLeftParenthesisKeyword_4_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group_4__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group_4__1__Impl
	rule__ClusterClass__Group_4__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group_4__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getGroup_4_1()); }
	(rule__ClusterClass__Group_4_1__0)?
	{ after(grammarAccess.getClusterClassAccess().getGroup_4_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group_4__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group_4__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group_4__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getRightParenthesisKeyword_4_2()); }
	')'
	{ after(grammarAccess.getClusterClassAccess().getRightParenthesisKeyword_4_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ClusterClass__Group_4_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group_4_1__0__Impl
	rule__ClusterClass__Group_4_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group_4_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getParametersAssignment_4_1_0()); }
	(rule__ClusterClass__ParametersAssignment_4_1_0)
	{ after(grammarAccess.getClusterClassAccess().getParametersAssignment_4_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group_4_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group_4_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group_4_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getGroup_4_1_1()); }
	(rule__ClusterClass__Group_4_1_1__0)*
	{ after(grammarAccess.getClusterClassAccess().getGroup_4_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ClusterClass__Group_4_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group_4_1_1__0__Impl
	rule__ClusterClass__Group_4_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group_4_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getCommaKeyword_4_1_1_0()); }
	','
	{ after(grammarAccess.getClusterClassAccess().getCommaKeyword_4_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group_4_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group_4_1_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group_4_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getParametersAssignment_4_1_1_1()); }
	(rule__ClusterClass__ParametersAssignment_4_1_1_1)
	{ after(grammarAccess.getClusterClassAccess().getParametersAssignment_4_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ClusterClass__Group_6__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group_6__0__Impl
	rule__ClusterClass__Group_6__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group_6__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getPortsAssignment_6_0()); }
	(rule__ClusterClass__PortsAssignment_6_0)
	{ after(grammarAccess.getClusterClassAccess().getPortsAssignment_6_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group_6__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group_6__1__Impl
	rule__ClusterClass__Group_6__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group_6__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getGroup_6_1()); }
	(rule__ClusterClass__Group_6_1__0)*
	{ after(grammarAccess.getClusterClassAccess().getGroup_6_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group_6__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group_6__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group_6__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getCommaKeyword_6_2()); }
	(',')?
	{ after(grammarAccess.getClusterClassAccess().getCommaKeyword_6_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ClusterClass__Group_6_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group_6_1__0__Impl
	rule__ClusterClass__Group_6_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group_6_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getCommaKeyword_6_1_0()); }
	(',')?
	{ after(grammarAccess.getClusterClassAccess().getCommaKeyword_6_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group_6_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ClusterClass__Group_6_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__Group_6_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getClusterClassAccess().getPortsAssignment_6_1_1()); }
	(rule__ClusterClass__PortsAssignment_6_1_1)
	{ after(grammarAccess.getClusterClassAccess().getPortsAssignment_6_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Instance__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Instance__Group__0__Impl
	rule__Instance__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getInstanceAccess().getAnnotationsAssignment_0()); }
	(rule__Instance__AnnotationsAssignment_0)*
	{ after(grammarAccess.getInstanceAccess().getAnnotationsAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Instance__Group__1__Impl
	rule__Instance__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getInstanceAccess().getNameAssignment_1()); }
	(rule__Instance__NameAssignment_1)
	{ after(grammarAccess.getInstanceAccess().getNameAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Instance__Group__2__Impl
	rule__Instance__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getInstanceAccess().getColonKeyword_2()); }
	':'
	{ after(grammarAccess.getInstanceAccess().getColonKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Instance__Group__3__Impl
	rule__Instance__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getInstanceAccess().getClassDefinitionAssignment_3()); }
	(rule__Instance__ClassDefinitionAssignment_3)
	{ after(grammarAccess.getInstanceAccess().getClassDefinitionAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Instance__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getInstanceAccess().getGroup_4()); }
	(rule__Instance__Group_4__0)?
	{ after(grammarAccess.getInstanceAccess().getGroup_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Instance__Group_4__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Instance__Group_4__0__Impl
	rule__Instance__Group_4__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__Group_4__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getInstanceAccess().getLeftParenthesisKeyword_4_0()); }
	'('
	{ after(grammarAccess.getInstanceAccess().getLeftParenthesisKeyword_4_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__Group_4__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Instance__Group_4__1__Impl
	rule__Instance__Group_4__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__Group_4__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getInstanceAccess().getGroup_4_1()); }
	(rule__Instance__Group_4_1__0)?
	{ after(grammarAccess.getInstanceAccess().getGroup_4_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__Group_4__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Instance__Group_4__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__Group_4__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getInstanceAccess().getRightParenthesisKeyword_4_2()); }
	')'
	{ after(grammarAccess.getInstanceAccess().getRightParenthesisKeyword_4_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Instance__Group_4_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Instance__Group_4_1__0__Impl
	rule__Instance__Group_4_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__Group_4_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getInstanceAccess().getInstanceParametersAssignment_4_1_0()); }
	(rule__Instance__InstanceParametersAssignment_4_1_0)
	{ after(grammarAccess.getInstanceAccess().getInstanceParametersAssignment_4_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__Group_4_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Instance__Group_4_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__Group_4_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getInstanceAccess().getGroup_4_1_1()); }
	(rule__Instance__Group_4_1_1__0)*
	{ after(grammarAccess.getInstanceAccess().getGroup_4_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Instance__Group_4_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Instance__Group_4_1_1__0__Impl
	rule__Instance__Group_4_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__Group_4_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getInstanceAccess().getCommaKeyword_4_1_1_0()); }
	','
	{ after(grammarAccess.getInstanceAccess().getCommaKeyword_4_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__Group_4_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Instance__Group_4_1_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__Group_4_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getInstanceAccess().getInstanceParametersAssignment_4_1_1_1()); }
	(rule__Instance__InstanceParametersAssignment_4_1_1_1)
	{ after(grammarAccess.getInstanceAccess().getInstanceParametersAssignment_4_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__InstanceParameter__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__InstanceParameter__Group__0__Impl
	rule__InstanceParameter__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__InstanceParameter__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getInstanceParameterAccess().getParameterAssignment_0()); }
	(rule__InstanceParameter__ParameterAssignment_0)
	{ after(grammarAccess.getInstanceParameterAccess().getParameterAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__InstanceParameter__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__InstanceParameter__Group__1__Impl
	rule__InstanceParameter__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__InstanceParameter__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getInstanceParameterAccess().getColonEqualsSignKeyword_1()); }
	':='
	{ after(grammarAccess.getInstanceParameterAccess().getColonEqualsSignKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__InstanceParameter__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__InstanceParameter__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__InstanceParameter__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getInstanceParameterAccess().getExpressionAssignment_2()); }
	(rule__InstanceParameter__ExpressionAssignment_2)
	{ after(grammarAccess.getInstanceParameterAccess().getExpressionAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Channel__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Channel__Group__0__Impl
	rule__Channel__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getChannelAccess().getChannelAction_0()); }
	()
	{ after(grammarAccess.getChannelAccess().getChannelAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Channel__Group__1__Impl
	rule__Channel__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getChannelAccess().getAnnotationsAssignment_1()); }
	(rule__Channel__AnnotationsAssignment_1)*
	{ after(grammarAccess.getChannelAccess().getAnnotationsAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Channel__Group__2__Impl
	rule__Channel__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getChannelAccess().getLeftCurlyBracketKeyword_2()); }
	'{'
	{ after(grammarAccess.getChannelAccess().getLeftCurlyBracketKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Channel__Group__3__Impl
	rule__Channel__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getChannelAccess().getAlternatives_3()); }
	(rule__Channel__Alternatives_3)?
	{ after(grammarAccess.getChannelAccess().getAlternatives_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Channel__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getChannelAccess().getRightCurlyBracketKeyword_4()); }
	'}'
	{ after(grammarAccess.getChannelAccess().getRightCurlyBracketKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Channel__Group_3_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Channel__Group_3_0__0__Impl
	rule__Channel__Group_3_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group_3_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getChannelAccess().getInstancePortsAssignment_3_0_0()); }
	(rule__Channel__InstancePortsAssignment_3_0_0)
	{ after(grammarAccess.getChannelAccess().getInstancePortsAssignment_3_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group_3_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Channel__Group_3_0__1__Impl
	rule__Channel__Group_3_0__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group_3_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getChannelAccess().getGroup_3_0_1()); }
	(rule__Channel__Group_3_0_1__0)*
	{ after(grammarAccess.getChannelAccess().getGroup_3_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group_3_0__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Channel__Group_3_0__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group_3_0__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getChannelAccess().getGroup_3_0_2()); }
	(rule__Channel__Group_3_0_2__0)?
	{ after(grammarAccess.getChannelAccess().getGroup_3_0_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Channel__Group_3_0_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Channel__Group_3_0_1__0__Impl
	rule__Channel__Group_3_0_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group_3_0_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getChannelAccess().getCommaKeyword_3_0_1_0()); }
	','
	{ after(grammarAccess.getChannelAccess().getCommaKeyword_3_0_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group_3_0_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Channel__Group_3_0_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group_3_0_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getChannelAccess().getInstancePortsAssignment_3_0_1_1()); }
	(rule__Channel__InstancePortsAssignment_3_0_1_1)
	{ after(grammarAccess.getChannelAccess().getInstancePortsAssignment_3_0_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Channel__Group_3_0_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Channel__Group_3_0_2__0__Impl
	rule__Channel__Group_3_0_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group_3_0_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getChannelAccess().getCommaKeyword_3_0_2_0()); }
	','
	{ after(grammarAccess.getChannelAccess().getCommaKeyword_3_0_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group_3_0_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Channel__Group_3_0_2__1__Impl
	rule__Channel__Group_3_0_2__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group_3_0_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getChannelAccess().getExternalPortAssignment_3_0_2_1()); }
	(rule__Channel__ExternalPortAssignment_3_0_2_1)
	{ after(grammarAccess.getChannelAccess().getExternalPortAssignment_3_0_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group_3_0_2__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Channel__Group_3_0_2__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group_3_0_2__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getChannelAccess().getGroup_3_0_2_2()); }
	(rule__Channel__Group_3_0_2_2__0)*
	{ after(grammarAccess.getChannelAccess().getGroup_3_0_2_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Channel__Group_3_0_2_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Channel__Group_3_0_2_2__0__Impl
	rule__Channel__Group_3_0_2_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group_3_0_2_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getChannelAccess().getCommaKeyword_3_0_2_2_0()); }
	','
	{ after(grammarAccess.getChannelAccess().getCommaKeyword_3_0_2_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group_3_0_2_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Channel__Group_3_0_2_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group_3_0_2_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getChannelAccess().getInstancePortsAssignment_3_0_2_2_1()); }
	(rule__Channel__InstancePortsAssignment_3_0_2_2_1)
	{ after(grammarAccess.getChannelAccess().getInstancePortsAssignment_3_0_2_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Channel__Group_3_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Channel__Group_3_1__0__Impl
	rule__Channel__Group_3_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group_3_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getChannelAccess().getExternalPortAssignment_3_1_0()); }
	(rule__Channel__ExternalPortAssignment_3_1_0)
	{ after(grammarAccess.getChannelAccess().getExternalPortAssignment_3_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group_3_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Channel__Group_3_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group_3_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getChannelAccess().getGroup_3_1_1()); }
	(rule__Channel__Group_3_1_1__0)*
	{ after(grammarAccess.getChannelAccess().getGroup_3_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Channel__Group_3_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Channel__Group_3_1_1__0__Impl
	rule__Channel__Group_3_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group_3_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getChannelAccess().getCommaKeyword_3_1_1_0()); }
	','
	{ after(grammarAccess.getChannelAccess().getCommaKeyword_3_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group_3_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Channel__Group_3_1_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__Group_3_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getChannelAccess().getInstancePortsAssignment_3_1_1_1()); }
	(rule__Channel__InstancePortsAssignment_3_1_1_1)
	{ after(grammarAccess.getChannelAccess().getInstancePortsAssignment_3_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__InstancePort__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__InstancePort__Group__0__Impl
	rule__InstancePort__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__InstancePort__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getInstancePortAccess().getInstanceAssignment_0()); }
	(rule__InstancePort__InstanceAssignment_0)
	{ after(grammarAccess.getInstancePortAccess().getInstanceAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__InstancePort__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__InstancePort__Group__1__Impl
	rule__InstancePort__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__InstancePort__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getInstancePortAccess().getFullStopKeyword_1()); }
	'.'
	{ after(grammarAccess.getInstancePortAccess().getFullStopKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__InstancePort__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__InstancePort__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__InstancePort__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getInstancePortAccess().getPortAssignment_2()); }
	(rule__InstancePort__PortAssignment_2)
	{ after(grammarAccess.getInstancePortAccess().getPortAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Expression__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Expression__Group__0__Impl
	rule__Expression__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Expression__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionAccess().getExpressionSingleParserRuleCall_0()); }
	ruleExpressionSingle
	{ after(grammarAccess.getExpressionAccess().getExpressionSingleParserRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Expression__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Expression__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Expression__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionAccess().getGroup_1()); }
	(rule__Expression__Group_1__0)?
	{ after(grammarAccess.getExpressionAccess().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Expression__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Expression__Group_1__0__Impl
	rule__Expression__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Expression__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionAccess().getExpressionSequenceExpressionsAction_1_0()); }
	()
	{ after(grammarAccess.getExpressionAccess().getExpressionSequenceExpressionsAction_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Expression__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Expression__Group_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Expression__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	(
		{ before(grammarAccess.getExpressionAccess().getGroup_1_1()); }
		(rule__Expression__Group_1_1__0)
		{ after(grammarAccess.getExpressionAccess().getGroup_1_1()); }
	)
	(
		{ before(grammarAccess.getExpressionAccess().getGroup_1_1()); }
		(rule__Expression__Group_1_1__0)*
		{ after(grammarAccess.getExpressionAccess().getGroup_1_1()); }
	)
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Expression__Group_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Expression__Group_1_1__0__Impl
	rule__Expression__Group_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Expression__Group_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionAccess().getSemicolonKeyword_1_1_0()); }
	';'
	{ after(grammarAccess.getExpressionAccess().getSemicolonKeyword_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Expression__Group_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Expression__Group_1_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Expression__Group_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionAccess().getExpressionsAssignment_1_1_1()); }
	(rule__Expression__ExpressionsAssignment_1_1_1)
	{ after(grammarAccess.getExpressionAccess().getExpressionsAssignment_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ExpressionLevel1__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel1__Group_1__0__Impl
	rule__ExpressionLevel1__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel1__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel1Access().getAssignmentExpressionAction_1_0()); }
	()
	{ after(grammarAccess.getExpressionLevel1Access().getAssignmentExpressionAction_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel1__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel1__Group_1__1__Impl
	rule__ExpressionLevel1__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel1__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel1Access().getVariableAssignment_1_1()); }
	(rule__ExpressionLevel1__VariableAssignment_1_1)
	{ after(grammarAccess.getExpressionLevel1Access().getVariableAssignment_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel1__Group_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel1__Group_1__2__Impl
	rule__ExpressionLevel1__Group_1__3
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel1__Group_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel1Access().getColonEqualsSignKeyword_1_2()); }
	':='
	{ after(grammarAccess.getExpressionLevel1Access().getColonEqualsSignKeyword_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel1__Group_1__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel1__Group_1__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel1__Group_1__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel1Access().getExpressionAssignment_1_3()); }
	(rule__ExpressionLevel1__ExpressionAssignment_1_3)
	{ after(grammarAccess.getExpressionLevel1Access().getExpressionAssignment_1_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ExpressionLevel1__Group_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel1__Group_2__0__Impl
	rule__ExpressionLevel1__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel1__Group_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel1Access().getReturnExpressionAction_2_0()); }
	()
	{ after(grammarAccess.getExpressionLevel1Access().getReturnExpressionAction_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel1__Group_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel1__Group_2__1__Impl
	rule__ExpressionLevel1__Group_2__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel1__Group_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel1Access().getReturnKeyword_2_1()); }
	'return'
	{ after(grammarAccess.getExpressionLevel1Access().getReturnKeyword_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel1__Group_2__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel1__Group_2__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel1__Group_2__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel1Access().getExpressionAssignment_2_2()); }
	(rule__ExpressionLevel1__ExpressionAssignment_2_2)
	{ after(grammarAccess.getExpressionLevel1Access().getExpressionAssignment_2_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ExpressionLevel2__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel2__Group__0__Impl
	rule__ExpressionLevel2__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel2__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel2Access().getExpressionLevel3ParserRuleCall_0()); }
	ruleExpressionLevel3
	{ after(grammarAccess.getExpressionLevel2Access().getExpressionLevel3ParserRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel2__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel2__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel2__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel2Access().getGroup_1()); }
	(rule__ExpressionLevel2__Group_1__0)*
	{ after(grammarAccess.getExpressionLevel2Access().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ExpressionLevel2__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel2__Group_1__0__Impl
	rule__ExpressionLevel2__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel2__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_1_0()); }
	()
	{ after(grammarAccess.getExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel2__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel2__Group_1__1__Impl
	rule__ExpressionLevel2__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel2__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel2Access().getNameAssignment_1_1()); }
	(rule__ExpressionLevel2__NameAssignment_1_1)
	{ after(grammarAccess.getExpressionLevel2Access().getNameAssignment_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel2__Group_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel2__Group_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel2__Group_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel2Access().getRightOperandAssignment_1_2()); }
	(rule__ExpressionLevel2__RightOperandAssignment_1_2)
	{ after(grammarAccess.getExpressionLevel2Access().getRightOperandAssignment_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ExpressionLevel3__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel3__Group__0__Impl
	rule__ExpressionLevel3__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel3__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel3Access().getExpressionLevel4ParserRuleCall_0()); }
	ruleExpressionLevel4
	{ after(grammarAccess.getExpressionLevel3Access().getExpressionLevel4ParserRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel3__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel3__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel3__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel3Access().getGroup_1()); }
	(rule__ExpressionLevel3__Group_1__0)*
	{ after(grammarAccess.getExpressionLevel3Access().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ExpressionLevel3__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel3__Group_1__0__Impl
	rule__ExpressionLevel3__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel3__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_1_0()); }
	()
	{ after(grammarAccess.getExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel3__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel3__Group_1__1__Impl
	rule__ExpressionLevel3__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel3__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel3Access().getNameAssignment_1_1()); }
	(rule__ExpressionLevel3__NameAssignment_1_1)
	{ after(grammarAccess.getExpressionLevel3Access().getNameAssignment_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel3__Group_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel3__Group_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel3__Group_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel3Access().getRightOperandAssignment_1_2()); }
	(rule__ExpressionLevel3__RightOperandAssignment_1_2)
	{ after(grammarAccess.getExpressionLevel3Access().getRightOperandAssignment_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ExpressionLevel4__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel4__Group__0__Impl
	rule__ExpressionLevel4__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel4__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel4Access().getExpressionLevel5ParserRuleCall_0()); }
	ruleExpressionLevel5
	{ after(grammarAccess.getExpressionLevel4Access().getExpressionLevel5ParserRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel4__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel4__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel4__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel4Access().getGroup_1()); }
	(rule__ExpressionLevel4__Group_1__0)*
	{ after(grammarAccess.getExpressionLevel4Access().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ExpressionLevel4__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel4__Group_1__0__Impl
	rule__ExpressionLevel4__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel4__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_0()); }
	()
	{ after(grammarAccess.getExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel4__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel4__Group_1__1__Impl
	rule__ExpressionLevel4__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel4__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel4Access().getNameAssignment_1_1()); }
	(rule__ExpressionLevel4__NameAssignment_1_1)
	{ after(grammarAccess.getExpressionLevel4Access().getNameAssignment_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel4__Group_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel4__Group_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel4__Group_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel4Access().getRightOperandAssignment_1_2()); }
	(rule__ExpressionLevel4__RightOperandAssignment_1_2)
	{ after(grammarAccess.getExpressionLevel4Access().getRightOperandAssignment_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ExpressionLevel5__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel5__Group__0__Impl
	rule__ExpressionLevel5__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel5Access().getExpressionLevel6ParserRuleCall_0()); }
	ruleExpressionLevel6
	{ after(grammarAccess.getExpressionLevel5Access().getExpressionLevel6ParserRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel5__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel5Access().getGroup_1()); }
	(rule__ExpressionLevel5__Group_1__0)*
	{ after(grammarAccess.getExpressionLevel5Access().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ExpressionLevel5__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel5__Group_1__0__Impl
	rule__ExpressionLevel5__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel5Access().getDataMethodCallExpressionTargetAction_1_0()); }
	()
	{ after(grammarAccess.getExpressionLevel5Access().getDataMethodCallExpressionTargetAction_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel5__Group_1__1__Impl
	rule__ExpressionLevel5__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel5Access().getOnSuperClassAssignment_1_1()); }
	(rule__ExpressionLevel5__OnSuperClassAssignment_1_1)?
	{ after(grammarAccess.getExpressionLevel5Access().getOnSuperClassAssignment_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__Group_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel5__Group_1__2__Impl
	rule__ExpressionLevel5__Group_1__3
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__Group_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel5Access().getNameAssignment_1_2()); }
	(rule__ExpressionLevel5__NameAssignment_1_2)
	{ after(grammarAccess.getExpressionLevel5Access().getNameAssignment_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__Group_1__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel5__Group_1__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__Group_1__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel5Access().getGroup_1_3()); }
	(rule__ExpressionLevel5__Group_1_3__0)?
	{ after(grammarAccess.getExpressionLevel5Access().getGroup_1_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ExpressionLevel5__Group_1_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel5__Group_1_3__0__Impl
	rule__ExpressionLevel5__Group_1_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__Group_1_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel5Access().getLeftParenthesisKeyword_1_3_0()); }
	'('
	{ after(grammarAccess.getExpressionLevel5Access().getLeftParenthesisKeyword_1_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__Group_1_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel5__Group_1_3__1__Impl
	rule__ExpressionLevel5__Group_1_3__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__Group_1_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel5Access().getGroup_1_3_1()); }
	(rule__ExpressionLevel5__Group_1_3_1__0)?
	{ after(grammarAccess.getExpressionLevel5Access().getGroup_1_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__Group_1_3__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel5__Group_1_3__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__Group_1_3__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel5Access().getRightParenthesisKeyword_1_3_2()); }
	')'
	{ after(grammarAccess.getExpressionLevel5Access().getRightParenthesisKeyword_1_3_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ExpressionLevel5__Group_1_3_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel5__Group_1_3_1__0__Impl
	rule__ExpressionLevel5__Group_1_3_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__Group_1_3_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel5Access().getArgumentsAssignment_1_3_1_0()); }
	(rule__ExpressionLevel5__ArgumentsAssignment_1_3_1_0)
	{ after(grammarAccess.getExpressionLevel5Access().getArgumentsAssignment_1_3_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__Group_1_3_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel5__Group_1_3_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__Group_1_3_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel5Access().getGroup_1_3_1_1()); }
	(rule__ExpressionLevel5__Group_1_3_1_1__0)*
	{ after(grammarAccess.getExpressionLevel5Access().getGroup_1_3_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ExpressionLevel5__Group_1_3_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel5__Group_1_3_1_1__0__Impl
	rule__ExpressionLevel5__Group_1_3_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__Group_1_3_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel5Access().getCommaKeyword_1_3_1_1_0()); }
	','
	{ after(grammarAccess.getExpressionLevel5Access().getCommaKeyword_1_3_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__Group_1_3_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionLevel5__Group_1_3_1_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__Group_1_3_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionLevel5Access().getArgumentsAssignment_1_3_1_1_1()); }
	(rule__ExpressionLevel5__ArgumentsAssignment_1_3_1_1_1)
	{ after(grammarAccess.getExpressionLevel5Access().getArgumentsAssignment_1_3_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__UnaryOperatorExpression__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__UnaryOperatorExpression__Group__0__Impl
	rule__UnaryOperatorExpression__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__UnaryOperatorExpression__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getUnaryOperatorExpressionAccess().getNameAssignment_0()); }
	(rule__UnaryOperatorExpression__NameAssignment_0)
	{ after(grammarAccess.getUnaryOperatorExpressionAccess().getNameAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__UnaryOperatorExpression__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__UnaryOperatorExpression__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__UnaryOperatorExpression__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getUnaryOperatorExpressionAccess().getOperandAssignment_1()); }
	(rule__UnaryOperatorExpression__OperandAssignment_1)
	{ after(grammarAccess.getUnaryOperatorExpressionAccess().getOperandAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IfExpression__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IfExpression__Group__0__Impl
	rule__IfExpression__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IfExpression__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIfExpressionAccess().getIfKeyword_0()); }
	'if'
	{ after(grammarAccess.getIfExpressionAccess().getIfKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IfExpression__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IfExpression__Group__1__Impl
	rule__IfExpression__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__IfExpression__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIfExpressionAccess().getConditionAssignment_1()); }
	(rule__IfExpression__ConditionAssignment_1)
	{ after(grammarAccess.getIfExpressionAccess().getConditionAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IfExpression__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IfExpression__Group__2__Impl
	rule__IfExpression__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__IfExpression__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIfExpressionAccess().getThenKeyword_2()); }
	'then'
	{ after(grammarAccess.getIfExpressionAccess().getThenKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IfExpression__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IfExpression__Group__3__Impl
	rule__IfExpression__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__IfExpression__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIfExpressionAccess().getThenClauseAssignment_3()); }
	(rule__IfExpression__ThenClauseAssignment_3)
	{ after(grammarAccess.getIfExpressionAccess().getThenClauseAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IfExpression__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IfExpression__Group__4__Impl
	rule__IfExpression__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__IfExpression__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIfExpressionAccess().getGroup_4()); }
	(rule__IfExpression__Group_4__0)?
	{ after(grammarAccess.getIfExpressionAccess().getGroup_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IfExpression__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IfExpression__Group__5__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IfExpression__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIfExpressionAccess().getFiKeyword_5()); }
	'fi'
	{ after(grammarAccess.getIfExpressionAccess().getFiKeyword_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IfExpression__Group_4__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IfExpression__Group_4__0__Impl
	rule__IfExpression__Group_4__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IfExpression__Group_4__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIfExpressionAccess().getElseKeyword_4_0()); }
	'else'
	{ after(grammarAccess.getIfExpressionAccess().getElseKeyword_4_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IfExpression__Group_4__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IfExpression__Group_4__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IfExpression__Group_4__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIfExpressionAccess().getElseClauseAssignment_4_1()); }
	(rule__IfExpression__ElseClauseAssignment_4_1)
	{ after(grammarAccess.getIfExpressionAccess().getElseClauseAssignment_4_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__WhileExpression__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__WhileExpression__Group__0__Impl
	rule__WhileExpression__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__WhileExpression__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getWhileExpressionAccess().getWhileKeyword_0()); }
	'while'
	{ after(grammarAccess.getWhileExpressionAccess().getWhileKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__WhileExpression__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__WhileExpression__Group__1__Impl
	rule__WhileExpression__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__WhileExpression__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getWhileExpressionAccess().getConditionAssignment_1()); }
	(rule__WhileExpression__ConditionAssignment_1)
	{ after(grammarAccess.getWhileExpressionAccess().getConditionAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__WhileExpression__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__WhileExpression__Group__2__Impl
	rule__WhileExpression__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__WhileExpression__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getWhileExpressionAccess().getDoKeyword_2()); }
	'do'
	{ after(grammarAccess.getWhileExpressionAccess().getDoKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__WhileExpression__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__WhileExpression__Group__3__Impl
	rule__WhileExpression__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__WhileExpression__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getWhileExpressionAccess().getBodyAssignment_3()); }
	(rule__WhileExpression__BodyAssignment_3)
	{ after(grammarAccess.getWhileExpressionAccess().getBodyAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__WhileExpression__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__WhileExpression__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__WhileExpression__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getWhileExpressionAccess().getOdKeyword_4()); }
	'od'
	{ after(grammarAccess.getWhileExpressionAccess().getOdKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SwitchExpression__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchExpression__Group__0__Impl
	rule__SwitchExpression__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchExpressionAccess().getSwitchKeyword_0()); }
	'switch'
	{ after(grammarAccess.getSwitchExpressionAccess().getSwitchKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchExpression__Group__1__Impl
	rule__SwitchExpression__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchExpressionAccess().getExpressionAssignment_1()); }
	(rule__SwitchExpression__ExpressionAssignment_1)
	{ after(grammarAccess.getSwitchExpressionAccess().getExpressionAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchExpression__Group__2__Impl
	rule__SwitchExpression__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchExpressionAccess().getDoKeyword_2()); }
	'do'
	{ after(grammarAccess.getSwitchExpressionAccess().getDoKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchExpression__Group__3__Impl
	rule__SwitchExpression__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchExpressionAccess().getAlternatives_3()); }
	(rule__SwitchExpression__Alternatives_3)
	{ after(grammarAccess.getSwitchExpressionAccess().getAlternatives_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchExpression__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchExpressionAccess().getOdKeyword_4()); }
	'od'
	{ after(grammarAccess.getSwitchExpressionAccess().getOdKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SwitchExpression__Group_3_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchExpression__Group_3_0__0__Impl
	rule__SwitchExpression__Group_3_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__Group_3_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	(
		{ before(grammarAccess.getSwitchExpressionAccess().getCasesAssignment_3_0_0()); }
		(rule__SwitchExpression__CasesAssignment_3_0_0)
		{ after(grammarAccess.getSwitchExpressionAccess().getCasesAssignment_3_0_0()); }
	)
	(
		{ before(grammarAccess.getSwitchExpressionAccess().getCasesAssignment_3_0_0()); }
		(rule__SwitchExpression__CasesAssignment_3_0_0)*
		{ after(grammarAccess.getSwitchExpressionAccess().getCasesAssignment_3_0_0()); }
	)
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__Group_3_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchExpression__Group_3_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__Group_3_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchExpressionAccess().getGroup_3_0_1()); }
	(rule__SwitchExpression__Group_3_0_1__0)?
	{ after(grammarAccess.getSwitchExpressionAccess().getGroup_3_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SwitchExpression__Group_3_0_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchExpression__Group_3_0_1__0__Impl
	rule__SwitchExpression__Group_3_0_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__Group_3_0_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchExpressionAccess().getDefaultKeyword_3_0_1_0()); }
	'default'
	{ after(grammarAccess.getSwitchExpressionAccess().getDefaultKeyword_3_0_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__Group_3_0_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchExpression__Group_3_0_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__Group_3_0_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchExpressionAccess().getDefaultBodyAssignment_3_0_1_1()); }
	(rule__SwitchExpression__DefaultBodyAssignment_3_0_1_1)
	{ after(grammarAccess.getSwitchExpressionAccess().getDefaultBodyAssignment_3_0_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SwitchExpression__Group_3_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchExpression__Group_3_1__0__Impl
	rule__SwitchExpression__Group_3_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__Group_3_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchExpressionAccess().getDefaultKeyword_3_1_0()); }
	'default'
	{ after(grammarAccess.getSwitchExpressionAccess().getDefaultKeyword_3_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__Group_3_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchExpression__Group_3_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__Group_3_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchExpressionAccess().getDefaultBodyAssignment_3_1_1()); }
	(rule__SwitchExpression__DefaultBodyAssignment_3_1_1)
	{ after(grammarAccess.getSwitchExpressionAccess().getDefaultBodyAssignment_3_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SwitchExpressionCase__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchExpressionCase__Group__0__Impl
	rule__SwitchExpressionCase__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpressionCase__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchExpressionCaseAccess().getCaseKeyword_0()); }
	'case'
	{ after(grammarAccess.getSwitchExpressionCaseAccess().getCaseKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpressionCase__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchExpressionCase__Group__1__Impl
	rule__SwitchExpressionCase__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpressionCase__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchExpressionCaseAccess().getValueAssignment_1()); }
	(rule__SwitchExpressionCase__ValueAssignment_1)
	{ after(grammarAccess.getSwitchExpressionCaseAccess().getValueAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpressionCase__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchExpressionCase__Group__2__Impl
	rule__SwitchExpressionCase__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpressionCase__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchExpressionCaseAccess().getThenKeyword_2()); }
	'then'
	{ after(grammarAccess.getSwitchExpressionCaseAccess().getThenKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpressionCase__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchExpressionCase__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpressionCase__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchExpressionCaseAccess().getBodyAssignment_3()); }
	(rule__SwitchExpressionCase__BodyAssignment_3)
	{ after(grammarAccess.getSwitchExpressionCaseAccess().getBodyAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__CurrentTimeExpression__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__CurrentTimeExpression__Group__0__Impl
	rule__CurrentTimeExpression__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__CurrentTimeExpression__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCurrentTimeExpressionAccess().getCurrentTimeExpressionAction_0()); }
	()
	{ after(grammarAccess.getCurrentTimeExpressionAccess().getCurrentTimeExpressionAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__CurrentTimeExpression__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__CurrentTimeExpression__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__CurrentTimeExpression__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCurrentTimeExpressionAccess().getCurrentTimeKeyword_1()); }
	'currentTime'
	{ after(grammarAccess.getCurrentTimeExpressionAccess().getCurrentTimeKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SelfExpression__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SelfExpression__Group__0__Impl
	rule__SelfExpression__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SelfExpression__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSelfExpressionAccess().getSelfExpressionAction_0()); }
	()
	{ after(grammarAccess.getSelfExpressionAccess().getSelfExpressionAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SelfExpression__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SelfExpression__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SelfExpression__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSelfExpressionAccess().getSelfKeyword_1()); }
	'self'
	{ after(grammarAccess.getSelfExpressionAccess().getSelfKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__NilConstant__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NilConstant__Group__0__Impl
	rule__NilConstant__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__NilConstant__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNilConstantAccess().getNilConstantAction_0()); }
	()
	{ after(grammarAccess.getNilConstantAccess().getNilConstantAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NilConstant__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NilConstant__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__NilConstant__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNilConstantAccess().getNilKeyword_1()); }
	'nil'
	{ after(grammarAccess.getNilConstantAccess().getNilKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__NewExpression__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NewExpression__Group__0__Impl
	rule__NewExpression__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__NewExpression__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNewExpressionAccess().getNewKeyword_0()); }
	'new'
	{ after(grammarAccess.getNewExpressionAccess().getNewKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NewExpression__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NewExpression__Group__1__Impl
	rule__NewExpression__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__NewExpression__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNewExpressionAccess().getLeftParenthesisKeyword_1()); }
	'('
	{ after(grammarAccess.getNewExpressionAccess().getLeftParenthesisKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NewExpression__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NewExpression__Group__2__Impl
	rule__NewExpression__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__NewExpression__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNewExpressionAccess().getDataClassAssignment_2()); }
	(rule__NewExpression__DataClassAssignment_2)
	{ after(grammarAccess.getNewExpressionAccess().getDataClassAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NewExpression__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NewExpression__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__NewExpression__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNewExpressionAccess().getRightParenthesisKeyword_3()); }
	')'
	{ after(grammarAccess.getNewExpressionAccess().getRightParenthesisKeyword_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ExpressionSequenceRoundBracket__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionSequenceRoundBracket__Group__0__Impl
	rule__ExpressionSequenceRoundBracket__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionSequenceRoundBracket__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionSequenceRoundBracketAccess().getLeftParenthesisKeyword_0()); }
	'('
	{ after(grammarAccess.getExpressionSequenceRoundBracketAccess().getLeftParenthesisKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionSequenceRoundBracket__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionSequenceRoundBracket__Group__1__Impl
	rule__ExpressionSequenceRoundBracket__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionSequenceRoundBracket__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionSequenceRoundBracketAccess().getExpressionsAssignment_1()); }
	(rule__ExpressionSequenceRoundBracket__ExpressionsAssignment_1)
	{ after(grammarAccess.getExpressionSequenceRoundBracketAccess().getExpressionsAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionSequenceRoundBracket__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionSequenceRoundBracket__Group__2__Impl
	rule__ExpressionSequenceRoundBracket__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionSequenceRoundBracket__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionSequenceRoundBracketAccess().getGroup_2()); }
	(rule__ExpressionSequenceRoundBracket__Group_2__0)*
	{ after(grammarAccess.getExpressionSequenceRoundBracketAccess().getGroup_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionSequenceRoundBracket__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionSequenceRoundBracket__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionSequenceRoundBracket__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionSequenceRoundBracketAccess().getRightParenthesisKeyword_3()); }
	')'
	{ after(grammarAccess.getExpressionSequenceRoundBracketAccess().getRightParenthesisKeyword_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ExpressionSequenceRoundBracket__Group_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionSequenceRoundBracket__Group_2__0__Impl
	rule__ExpressionSequenceRoundBracket__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionSequenceRoundBracket__Group_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionSequenceRoundBracketAccess().getSemicolonKeyword_2_0()); }
	';'
	{ after(grammarAccess.getExpressionSequenceRoundBracketAccess().getSemicolonKeyword_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionSequenceRoundBracket__Group_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionSequenceRoundBracket__Group_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionSequenceRoundBracket__Group_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionSequenceRoundBracketAccess().getExpressionsAssignment_2_1()); }
	(rule__ExpressionSequenceRoundBracket__ExpressionsAssignment_2_1)
	{ after(grammarAccess.getExpressionSequenceRoundBracketAccess().getExpressionsAssignment_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IDStartWithinStatementExpressionLevel1__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel1__Group_1__0__Impl
	rule__IDStartWithinStatementExpressionLevel1__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel1__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getAssignmentExpressionAction_1_0()); }
	()
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getAssignmentExpressionAction_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel1__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel1__Group_1__1__Impl
	rule__IDStartWithinStatementExpressionLevel1__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel1__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getVariableAssignment_1_1()); }
	(rule__IDStartWithinStatementExpressionLevel1__VariableAssignment_1_1)
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getVariableAssignment_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel1__Group_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel1__Group_1__2__Impl
	rule__IDStartWithinStatementExpressionLevel1__Group_1__3
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel1__Group_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getColonEqualsSignKeyword_1_2()); }
	':='
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getColonEqualsSignKeyword_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel1__Group_1__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel1__Group_1__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel1__Group_1__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getExpressionAssignment_1_3()); }
	(rule__IDStartWithinStatementExpressionLevel1__ExpressionAssignment_1_3)
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getExpressionAssignment_1_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IDStartWithinStatementExpressionLevel2__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel2__Group__0__Impl
	rule__IDStartWithinStatementExpressionLevel2__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel2__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getIDStartWithinStatementExpressionLevel3ParserRuleCall_0()); }
	ruleIDStartWithinStatementExpressionLevel3
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getIDStartWithinStatementExpressionLevel3ParserRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel2__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel2__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel2__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getGroup_1()); }
	(rule__IDStartWithinStatementExpressionLevel2__Group_1__0)*
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IDStartWithinStatementExpressionLevel2__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel2__Group_1__0__Impl
	rule__IDStartWithinStatementExpressionLevel2__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel2__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_1_0()); }
	()
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel2__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel2__Group_1__1__Impl
	rule__IDStartWithinStatementExpressionLevel2__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel2__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getNameAssignment_1_1()); }
	(rule__IDStartWithinStatementExpressionLevel2__NameAssignment_1_1)
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getNameAssignment_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel2__Group_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel2__Group_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel2__Group_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getRightOperandAssignment_1_2()); }
	(rule__IDStartWithinStatementExpressionLevel2__RightOperandAssignment_1_2)
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getRightOperandAssignment_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IDStartWithinStatementExpressionLevel3__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel3__Group__0__Impl
	rule__IDStartWithinStatementExpressionLevel3__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel3__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getIDStartWithinStatementExpressionLevel4ParserRuleCall_0()); }
	ruleIDStartWithinStatementExpressionLevel4
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getIDStartWithinStatementExpressionLevel4ParserRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel3__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel3__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel3__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getGroup_1()); }
	(rule__IDStartWithinStatementExpressionLevel3__Group_1__0)*
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IDStartWithinStatementExpressionLevel3__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel3__Group_1__0__Impl
	rule__IDStartWithinStatementExpressionLevel3__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel3__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_1_0()); }
	()
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel3__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel3__Group_1__1__Impl
	rule__IDStartWithinStatementExpressionLevel3__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel3__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getNameAssignment_1_1()); }
	(rule__IDStartWithinStatementExpressionLevel3__NameAssignment_1_1)
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getNameAssignment_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel3__Group_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel3__Group_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel3__Group_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getRightOperandAssignment_1_2()); }
	(rule__IDStartWithinStatementExpressionLevel3__RightOperandAssignment_1_2)
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getRightOperandAssignment_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IDStartWithinStatementExpressionLevel4__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel4__Group__0__Impl
	rule__IDStartWithinStatementExpressionLevel4__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel4__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getIDStartWithinStatementExpressionLevel5ParserRuleCall_0()); }
	ruleIDStartWithinStatementExpressionLevel5
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getIDStartWithinStatementExpressionLevel5ParserRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel4__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel4__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel4__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getGroup_1()); }
	(rule__IDStartWithinStatementExpressionLevel4__Group_1__0)*
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IDStartWithinStatementExpressionLevel4__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel4__Group_1__0__Impl
	rule__IDStartWithinStatementExpressionLevel4__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel4__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_0()); }
	()
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel4__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel4__Group_1__1__Impl
	rule__IDStartWithinStatementExpressionLevel4__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel4__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getNameAssignment_1_1()); }
	(rule__IDStartWithinStatementExpressionLevel4__NameAssignment_1_1)
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getNameAssignment_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel4__Group_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel4__Group_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel4__Group_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getRightOperandAssignment_1_2()); }
	(rule__IDStartWithinStatementExpressionLevel4__RightOperandAssignment_1_2)
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getRightOperandAssignment_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IDStartWithinStatementExpressionLevel5__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel5__Group__0__Impl
	rule__IDStartWithinStatementExpressionLevel5__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getVariableExpressionParserRuleCall_0()); }
	ruleVariableExpression
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getVariableExpressionParserRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel5__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getGroup_1()); }
	(rule__IDStartWithinStatementExpressionLevel5__Group_1__0)*
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IDStartWithinStatementExpressionLevel5__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel5__Group_1__0__Impl
	rule__IDStartWithinStatementExpressionLevel5__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getDataMethodCallExpressionTargetAction_1_0()); }
	()
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getDataMethodCallExpressionTargetAction_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel5__Group_1__1__Impl
	rule__IDStartWithinStatementExpressionLevel5__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getOnSuperClassAssignment_1_1()); }
	(rule__IDStartWithinStatementExpressionLevel5__OnSuperClassAssignment_1_1)?
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getOnSuperClassAssignment_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__Group_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel5__Group_1__2__Impl
	rule__IDStartWithinStatementExpressionLevel5__Group_1__3
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__Group_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getNameAssignment_1_2()); }
	(rule__IDStartWithinStatementExpressionLevel5__NameAssignment_1_2)
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getNameAssignment_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__Group_1__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel5__Group_1__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__Group_1__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getGroup_1_3()); }
	(rule__IDStartWithinStatementExpressionLevel5__Group_1_3__0)?
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getGroup_1_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IDStartWithinStatementExpressionLevel5__Group_1_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel5__Group_1_3__0__Impl
	rule__IDStartWithinStatementExpressionLevel5__Group_1_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__Group_1_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getLeftParenthesisKeyword_1_3_0()); }
	'('
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getLeftParenthesisKeyword_1_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__Group_1_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel5__Group_1_3__1__Impl
	rule__IDStartWithinStatementExpressionLevel5__Group_1_3__2
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__Group_1_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getGroup_1_3_1()); }
	(rule__IDStartWithinStatementExpressionLevel5__Group_1_3_1__0)?
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getGroup_1_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__Group_1_3__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel5__Group_1_3__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__Group_1_3__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getRightParenthesisKeyword_1_3_2()); }
	')'
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getRightParenthesisKeyword_1_3_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IDStartWithinStatementExpressionLevel5__Group_1_3_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel5__Group_1_3_1__0__Impl
	rule__IDStartWithinStatementExpressionLevel5__Group_1_3_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__Group_1_3_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getArgumentsAssignment_1_3_1_0()); }
	(rule__IDStartWithinStatementExpressionLevel5__ArgumentsAssignment_1_3_1_0)
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getArgumentsAssignment_1_3_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__Group_1_3_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel5__Group_1_3_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__Group_1_3_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getGroup_1_3_1_1()); }
	(rule__IDStartWithinStatementExpressionLevel5__Group_1_3_1_1__0)*
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getGroup_1_3_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IDStartWithinStatementExpressionLevel5__Group_1_3_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel5__Group_1_3_1_1__0__Impl
	rule__IDStartWithinStatementExpressionLevel5__Group_1_3_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__Group_1_3_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getCommaKeyword_1_3_1_1_0()); }
	','
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getCommaKeyword_1_3_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__Group_1_3_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IDStartWithinStatementExpressionLevel5__Group_1_3_1_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__Group_1_3_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getArgumentsAssignment_1_3_1_1_1()); }
	(rule__IDStartWithinStatementExpressionLevel5__ArgumentsAssignment_1_3_1_1_1)
	{ after(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getArgumentsAssignment_1_3_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__NonIDStartWithinStatementExpressionLevel1__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel1__Group_1__0__Impl
	rule__NonIDStartWithinStatementExpressionLevel1__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel1__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Access().getReturnExpressionAction_1_0()); }
	()
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Access().getReturnExpressionAction_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel1__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel1__Group_1__1__Impl
	rule__NonIDStartWithinStatementExpressionLevel1__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel1__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Access().getReturnKeyword_1_1()); }
	'return'
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Access().getReturnKeyword_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel1__Group_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel1__Group_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel1__Group_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Access().getExpressionAssignment_1_2()); }
	(rule__NonIDStartWithinStatementExpressionLevel1__ExpressionAssignment_1_2)
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Access().getExpressionAssignment_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__NonIDStartWithinStatementExpressionLevel2__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel2__Group__0__Impl
	rule__NonIDStartWithinStatementExpressionLevel2__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel2__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getNonIDStartWithinStatementExpressionLevel3ParserRuleCall_0()); }
	ruleNonIDStartWithinStatementExpressionLevel3
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getNonIDStartWithinStatementExpressionLevel3ParserRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel2__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel2__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel2__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getGroup_1()); }
	(rule__NonIDStartWithinStatementExpressionLevel2__Group_1__0)*
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__NonIDStartWithinStatementExpressionLevel2__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel2__Group_1__0__Impl
	rule__NonIDStartWithinStatementExpressionLevel2__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel2__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_1_0()); }
	()
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel2__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel2__Group_1__1__Impl
	rule__NonIDStartWithinStatementExpressionLevel2__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel2__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getNameAssignment_1_1()); }
	(rule__NonIDStartWithinStatementExpressionLevel2__NameAssignment_1_1)
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getNameAssignment_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel2__Group_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel2__Group_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel2__Group_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getRightOperandAssignment_1_2()); }
	(rule__NonIDStartWithinStatementExpressionLevel2__RightOperandAssignment_1_2)
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getRightOperandAssignment_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__NonIDStartWithinStatementExpressionLevel3__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel3__Group__0__Impl
	rule__NonIDStartWithinStatementExpressionLevel3__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel3__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getNonIDStartWithinStatementExpressionLevel4ParserRuleCall_0()); }
	ruleNonIDStartWithinStatementExpressionLevel4
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getNonIDStartWithinStatementExpressionLevel4ParserRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel3__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel3__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel3__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getGroup_1()); }
	(rule__NonIDStartWithinStatementExpressionLevel3__Group_1__0)*
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__NonIDStartWithinStatementExpressionLevel3__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel3__Group_1__0__Impl
	rule__NonIDStartWithinStatementExpressionLevel3__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel3__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_1_0()); }
	()
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel3__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel3__Group_1__1__Impl
	rule__NonIDStartWithinStatementExpressionLevel3__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel3__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getNameAssignment_1_1()); }
	(rule__NonIDStartWithinStatementExpressionLevel3__NameAssignment_1_1)
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getNameAssignment_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel3__Group_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel3__Group_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel3__Group_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getRightOperandAssignment_1_2()); }
	(rule__NonIDStartWithinStatementExpressionLevel3__RightOperandAssignment_1_2)
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getRightOperandAssignment_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__NonIDStartWithinStatementExpressionLevel4__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel4__Group__0__Impl
	rule__NonIDStartWithinStatementExpressionLevel4__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel4__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getNonIDStartWithinStatementExpressionLevel5ParserRuleCall_0()); }
	ruleNonIDStartWithinStatementExpressionLevel5
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getNonIDStartWithinStatementExpressionLevel5ParserRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel4__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel4__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel4__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getGroup_1()); }
	(rule__NonIDStartWithinStatementExpressionLevel4__Group_1__0)*
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__NonIDStartWithinStatementExpressionLevel4__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel4__Group_1__0__Impl
	rule__NonIDStartWithinStatementExpressionLevel4__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel4__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_0()); }
	()
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel4__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel4__Group_1__1__Impl
	rule__NonIDStartWithinStatementExpressionLevel4__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel4__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getNameAssignment_1_1()); }
	(rule__NonIDStartWithinStatementExpressionLevel4__NameAssignment_1_1)
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getNameAssignment_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel4__Group_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel4__Group_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel4__Group_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getRightOperandAssignment_1_2()); }
	(rule__NonIDStartWithinStatementExpressionLevel4__RightOperandAssignment_1_2)
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getRightOperandAssignment_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__NonIDStartWithinStatementExpressionLevel5__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel5__Group__0__Impl
	rule__NonIDStartWithinStatementExpressionLevel5__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getNonIDStartWithinStatementExpressionLevel6ParserRuleCall_0()); }
	ruleNonIDStartWithinStatementExpressionLevel6
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getNonIDStartWithinStatementExpressionLevel6ParserRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel5__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getGroup_1()); }
	(rule__NonIDStartWithinStatementExpressionLevel5__Group_1__0)*
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__NonIDStartWithinStatementExpressionLevel5__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel5__Group_1__0__Impl
	rule__NonIDStartWithinStatementExpressionLevel5__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getDataMethodCallExpressionTargetAction_1_0()); }
	()
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getDataMethodCallExpressionTargetAction_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel5__Group_1__1__Impl
	rule__NonIDStartWithinStatementExpressionLevel5__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getOnSuperClassAssignment_1_1()); }
	(rule__NonIDStartWithinStatementExpressionLevel5__OnSuperClassAssignment_1_1)?
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getOnSuperClassAssignment_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__Group_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel5__Group_1__2__Impl
	rule__NonIDStartWithinStatementExpressionLevel5__Group_1__3
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__Group_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getNameAssignment_1_2()); }
	(rule__NonIDStartWithinStatementExpressionLevel5__NameAssignment_1_2)
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getNameAssignment_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__Group_1__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel5__Group_1__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__Group_1__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getGroup_1_3()); }
	(rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3__0)?
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getGroup_1_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3__0__Impl
	rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getLeftParenthesisKeyword_1_3_0()); }
	'('
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getLeftParenthesisKeyword_1_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3__1__Impl
	rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3__2
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getGroup_1_3_1()); }
	(rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3_1__0)?
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getGroup_1_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getRightParenthesisKeyword_1_3_2()); }
	')'
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getRightParenthesisKeyword_1_3_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3_1__0__Impl
	rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getArgumentsAssignment_1_3_1_0()); }
	(rule__NonIDStartWithinStatementExpressionLevel5__ArgumentsAssignment_1_3_1_0)
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getArgumentsAssignment_1_3_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getGroup_1_3_1_1()); }
	(rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3_1_1__0)*
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getGroup_1_3_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3_1_1__0__Impl
	rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getCommaKeyword_1_3_1_1_0()); }
	','
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getCommaKeyword_1_3_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3_1_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__Group_1_3_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getArgumentsAssignment_1_3_1_1_1()); }
	(rule__NonIDStartWithinStatementExpressionLevel5__ArgumentsAssignment_1_3_1_1_1)
	{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getArgumentsAssignment_1_3_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Statement__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Statement__Group__0__Impl
	rule__Statement__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Statement__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStatementAccess().getStatementSingleParserRuleCall_0()); }
	ruleStatementSingle
	{ after(grammarAccess.getStatementAccess().getStatementSingleParserRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Statement__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Statement__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Statement__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStatementAccess().getGroup_1()); }
	(rule__Statement__Group_1__0)?
	{ after(grammarAccess.getStatementAccess().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Statement__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Statement__Group_1__0__Impl
	rule__Statement__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Statement__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStatementAccess().getStatementSequenceStatementsAction_1_0()); }
	()
	{ after(grammarAccess.getStatementAccess().getStatementSequenceStatementsAction_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Statement__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Statement__Group_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Statement__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	(
		{ before(grammarAccess.getStatementAccess().getGroup_1_1()); }
		(rule__Statement__Group_1_1__0)
		{ after(grammarAccess.getStatementAccess().getGroup_1_1()); }
	)
	(
		{ before(grammarAccess.getStatementAccess().getGroup_1_1()); }
		(rule__Statement__Group_1_1__0)*
		{ after(grammarAccess.getStatementAccess().getGroup_1_1()); }
	)
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Statement__Group_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Statement__Group_1_1__0__Impl
	rule__Statement__Group_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Statement__Group_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStatementAccess().getSemicolonKeyword_1_1_0()); }
	';'
	{ after(grammarAccess.getStatementAccess().getSemicolonKeyword_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Statement__Group_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Statement__Group_1_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Statement__Group_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStatementAccess().getStatementsAssignment_1_1_1()); }
	(rule__Statement__StatementsAssignment_1_1_1)
	{ after(grammarAccess.getStatementAccess().getStatementsAssignment_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ExpressionStatement__Group_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionStatement__Group_2__0__Impl
	rule__ExpressionStatement__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionStatement__Group_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionStatementAccess().getLeftCurlyBracketKeyword_2_0()); }
	'{'
	{ after(grammarAccess.getExpressionStatementAccess().getLeftCurlyBracketKeyword_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionStatement__Group_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionStatement__Group_2__1__Impl
	rule__ExpressionStatement__Group_2__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionStatement__Group_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionStatementAccess().getExpressionAssignment_2_1()); }
	(rule__ExpressionStatement__ExpressionAssignment_2_1)
	{ after(grammarAccess.getExpressionStatementAccess().getExpressionAssignment_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionStatement__Group_2__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ExpressionStatement__Group_2__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionStatement__Group_2__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getExpressionStatementAccess().getRightCurlyBracketKeyword_2_2()); }
	'}'
	{ after(grammarAccess.getExpressionStatementAccess().getRightCurlyBracketKeyword_2_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DelayStatement__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DelayStatement__Group__0__Impl
	rule__DelayStatement__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DelayStatement__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDelayStatementAccess().getDelayKeyword_0()); }
	'delay'
	{ after(grammarAccess.getDelayStatementAccess().getDelayKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DelayStatement__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DelayStatement__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DelayStatement__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDelayStatementAccess().getExpressionAssignment_1()); }
	(rule__DelayStatement__ExpressionAssignment_1)
	{ after(grammarAccess.getDelayStatementAccess().getExpressionAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ReceiveStatement__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReceiveStatement__Group__0__Impl
	rule__ReceiveStatement__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReceiveStatementAccess().getPortDescriptorAssignment_0()); }
	(rule__ReceiveStatement__PortDescriptorAssignment_0)
	{ after(grammarAccess.getReceiveStatementAccess().getPortDescriptorAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReceiveStatement__Group__1__Impl
	rule__ReceiveStatement__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReceiveStatementAccess().getQuestionMarkKeyword_1()); }
	'?'
	{ after(grammarAccess.getReceiveStatementAccess().getQuestionMarkKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReceiveStatement__Group__2__Impl
	rule__ReceiveStatement__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReceiveStatementAccess().getNameAssignment_2()); }
	(rule__ReceiveStatement__NameAssignment_2)
	{ after(grammarAccess.getReceiveStatementAccess().getNameAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReceiveStatement__Group__3__Impl
	rule__ReceiveStatement__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReceiveStatementAccess().getGroup_3()); }
	(rule__ReceiveStatement__Group_3__0)?
	{ after(grammarAccess.getReceiveStatementAccess().getGroup_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReceiveStatement__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReceiveStatementAccess().getGroup_4()); }
	(rule__ReceiveStatement__Group_4__0)?
	{ after(grammarAccess.getReceiveStatementAccess().getGroup_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ReceiveStatement__Group_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReceiveStatement__Group_3__0__Impl
	rule__ReceiveStatement__Group_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReceiveStatementAccess().getLeftParenthesisKeyword_3_0()); }
	'('
	{ after(grammarAccess.getReceiveStatementAccess().getLeftParenthesisKeyword_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReceiveStatement__Group_3__1__Impl
	rule__ReceiveStatement__Group_3__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReceiveStatementAccess().getGroup_3_1()); }
	(rule__ReceiveStatement__Group_3_1__0)?
	{ after(grammarAccess.getReceiveStatementAccess().getGroup_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group_3__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReceiveStatement__Group_3__2__Impl
	rule__ReceiveStatement__Group_3__3
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group_3__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReceiveStatementAccess().getGroup_3_2()); }
	(rule__ReceiveStatement__Group_3_2__0)?
	{ after(grammarAccess.getReceiveStatementAccess().getGroup_3_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group_3__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReceiveStatement__Group_3__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group_3__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReceiveStatementAccess().getRightParenthesisKeyword_3_3()); }
	')'
	{ after(grammarAccess.getReceiveStatementAccess().getRightParenthesisKeyword_3_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ReceiveStatement__Group_3_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReceiveStatement__Group_3_1__0__Impl
	rule__ReceiveStatement__Group_3_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group_3_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReceiveStatementAccess().getVariablesAssignment_3_1_0()); }
	(rule__ReceiveStatement__VariablesAssignment_3_1_0)
	{ after(grammarAccess.getReceiveStatementAccess().getVariablesAssignment_3_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group_3_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReceiveStatement__Group_3_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group_3_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReceiveStatementAccess().getGroup_3_1_1()); }
	(rule__ReceiveStatement__Group_3_1_1__0)*
	{ after(grammarAccess.getReceiveStatementAccess().getGroup_3_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ReceiveStatement__Group_3_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReceiveStatement__Group_3_1_1__0__Impl
	rule__ReceiveStatement__Group_3_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group_3_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReceiveStatementAccess().getCommaKeyword_3_1_1_0()); }
	','
	{ after(grammarAccess.getReceiveStatementAccess().getCommaKeyword_3_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group_3_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReceiveStatement__Group_3_1_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group_3_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReceiveStatementAccess().getVariablesAssignment_3_1_1_1()); }
	(rule__ReceiveStatement__VariablesAssignment_3_1_1_1)
	{ after(grammarAccess.getReceiveStatementAccess().getVariablesAssignment_3_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ReceiveStatement__Group_3_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReceiveStatement__Group_3_2__0__Impl
	rule__ReceiveStatement__Group_3_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group_3_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReceiveStatementAccess().getVerticalLineKeyword_3_2_0()); }
	'|'
	{ after(grammarAccess.getReceiveStatementAccess().getVerticalLineKeyword_3_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group_3_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReceiveStatement__Group_3_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group_3_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReceiveStatementAccess().getReceptionConditionAssignment_3_2_1()); }
	(rule__ReceiveStatement__ReceptionConditionAssignment_3_2_1)
	{ after(grammarAccess.getReceiveStatementAccess().getReceptionConditionAssignment_3_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ReceiveStatement__Group_4__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReceiveStatement__Group_4__0__Impl
	rule__ReceiveStatement__Group_4__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group_4__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReceiveStatementAccess().getLeftCurlyBracketKeyword_4_0()); }
	'{'
	{ after(grammarAccess.getReceiveStatementAccess().getLeftCurlyBracketKeyword_4_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group_4__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReceiveStatement__Group_4__1__Impl
	rule__ReceiveStatement__Group_4__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group_4__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReceiveStatementAccess().getPostCommunicationExpressionAssignment_4_1()); }
	(rule__ReceiveStatement__PostCommunicationExpressionAssignment_4_1)
	{ after(grammarAccess.getReceiveStatementAccess().getPostCommunicationExpressionAssignment_4_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group_4__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReceiveStatement__Group_4__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__Group_4__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReceiveStatementAccess().getRightCurlyBracketKeyword_4_2()); }
	'}'
	{ after(grammarAccess.getReceiveStatementAccess().getRightCurlyBracketKeyword_4_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SendStatement__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SendStatement__Group__0__Impl
	rule__SendStatement__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSendStatementAccess().getPortDescriptorAssignment_0()); }
	(rule__SendStatement__PortDescriptorAssignment_0)
	{ after(grammarAccess.getSendStatementAccess().getPortDescriptorAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SendStatement__Group__1__Impl
	rule__SendStatement__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSendStatementAccess().getExclamationMarkKeyword_1()); }
	'!'
	{ after(grammarAccess.getSendStatementAccess().getExclamationMarkKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SendStatement__Group__2__Impl
	rule__SendStatement__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSendStatementAccess().getNameAssignment_2()); }
	(rule__SendStatement__NameAssignment_2)
	{ after(grammarAccess.getSendStatementAccess().getNameAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SendStatement__Group__3__Impl
	rule__SendStatement__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSendStatementAccess().getGroup_3()); }
	(rule__SendStatement__Group_3__0)?
	{ after(grammarAccess.getSendStatementAccess().getGroup_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SendStatement__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSendStatementAccess().getGroup_4()); }
	(rule__SendStatement__Group_4__0)?
	{ after(grammarAccess.getSendStatementAccess().getGroup_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SendStatement__Group_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SendStatement__Group_3__0__Impl
	rule__SendStatement__Group_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSendStatementAccess().getLeftParenthesisKeyword_3_0()); }
	'('
	{ after(grammarAccess.getSendStatementAccess().getLeftParenthesisKeyword_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SendStatement__Group_3__1__Impl
	rule__SendStatement__Group_3__2
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSendStatementAccess().getGroup_3_1()); }
	(rule__SendStatement__Group_3_1__0)?
	{ after(grammarAccess.getSendStatementAccess().getGroup_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group_3__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SendStatement__Group_3__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group_3__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSendStatementAccess().getRightParenthesisKeyword_3_2()); }
	')'
	{ after(grammarAccess.getSendStatementAccess().getRightParenthesisKeyword_3_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SendStatement__Group_3_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SendStatement__Group_3_1__0__Impl
	rule__SendStatement__Group_3_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group_3_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSendStatementAccess().getArgumentsAssignment_3_1_0()); }
	(rule__SendStatement__ArgumentsAssignment_3_1_0)
	{ after(grammarAccess.getSendStatementAccess().getArgumentsAssignment_3_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group_3_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SendStatement__Group_3_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group_3_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSendStatementAccess().getGroup_3_1_1()); }
	(rule__SendStatement__Group_3_1_1__0)*
	{ after(grammarAccess.getSendStatementAccess().getGroup_3_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SendStatement__Group_3_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SendStatement__Group_3_1_1__0__Impl
	rule__SendStatement__Group_3_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group_3_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSendStatementAccess().getCommaKeyword_3_1_1_0()); }
	','
	{ after(grammarAccess.getSendStatementAccess().getCommaKeyword_3_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group_3_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SendStatement__Group_3_1_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group_3_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSendStatementAccess().getArgumentsAssignment_3_1_1_1()); }
	(rule__SendStatement__ArgumentsAssignment_3_1_1_1)
	{ after(grammarAccess.getSendStatementAccess().getArgumentsAssignment_3_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SendStatement__Group_4__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SendStatement__Group_4__0__Impl
	rule__SendStatement__Group_4__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group_4__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSendStatementAccess().getLeftCurlyBracketKeyword_4_0()); }
	'{'
	{ after(grammarAccess.getSendStatementAccess().getLeftCurlyBracketKeyword_4_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group_4__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SendStatement__Group_4__1__Impl
	rule__SendStatement__Group_4__2
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group_4__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSendStatementAccess().getPostCommunicationExpressionAssignment_4_1()); }
	(rule__SendStatement__PostCommunicationExpressionAssignment_4_1)
	{ after(grammarAccess.getSendStatementAccess().getPostCommunicationExpressionAssignment_4_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group_4__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SendStatement__Group_4__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__Group_4__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSendStatementAccess().getRightCurlyBracketKeyword_4_2()); }
	'}'
	{ after(grammarAccess.getSendStatementAccess().getRightCurlyBracketKeyword_4_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__PortDescriptor__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PortDescriptor__Group__0__Impl
	rule__PortDescriptor__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__PortDescriptor__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPortDescriptorAccess().getPortReferenceAction_0()); }
	()
	{ after(grammarAccess.getPortDescriptorAccess().getPortReferenceAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PortDescriptor__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PortDescriptor__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__PortDescriptor__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPortDescriptorAccess().getPortAssignment_1()); }
	(rule__PortDescriptor__PortAssignment_1)
	{ after(grammarAccess.getPortDescriptorAccess().getPortAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SkipStatement__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SkipStatement__Group__0__Impl
	rule__SkipStatement__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SkipStatement__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSkipStatementAccess().getSkipStatementAction_0()); }
	()
	{ after(grammarAccess.getSkipStatementAccess().getSkipStatementAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SkipStatement__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SkipStatement__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SkipStatement__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSkipStatementAccess().getSkipKeyword_1()); }
	'skip'
	{ after(grammarAccess.getSkipStatementAccess().getSkipKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__GuardedStatement__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GuardedStatement__Group__0__Impl
	rule__GuardedStatement__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__GuardedStatement__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGuardedStatementAccess().getLeftSquareBracketKeyword_0()); }
	'['
	{ after(grammarAccess.getGuardedStatementAccess().getLeftSquareBracketKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GuardedStatement__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GuardedStatement__Group__1__Impl
	rule__GuardedStatement__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__GuardedStatement__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGuardedStatementAccess().getGuardAssignment_1()); }
	(rule__GuardedStatement__GuardAssignment_1)
	{ after(grammarAccess.getGuardedStatementAccess().getGuardAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GuardedStatement__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GuardedStatement__Group__2__Impl
	rule__GuardedStatement__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__GuardedStatement__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGuardedStatementAccess().getRightSquareBracketKeyword_2()); }
	']'
	{ after(grammarAccess.getGuardedStatementAccess().getRightSquareBracketKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GuardedStatement__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GuardedStatement__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__GuardedStatement__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGuardedStatementAccess().getStatementAssignment_3()); }
	(rule__GuardedStatement__StatementAssignment_3)
	{ after(grammarAccess.getGuardedStatementAccess().getStatementAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IfStatement__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IfStatement__Group__0__Impl
	rule__IfStatement__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IfStatement__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIfStatementAccess().getIfKeyword_0()); }
	'if'
	{ after(grammarAccess.getIfStatementAccess().getIfKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IfStatement__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IfStatement__Group__1__Impl
	rule__IfStatement__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__IfStatement__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIfStatementAccess().getConditionAssignment_1()); }
	(rule__IfStatement__ConditionAssignment_1)
	{ after(grammarAccess.getIfStatementAccess().getConditionAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IfStatement__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IfStatement__Group__2__Impl
	rule__IfStatement__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__IfStatement__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIfStatementAccess().getThenKeyword_2()); }
	'then'
	{ after(grammarAccess.getIfStatementAccess().getThenKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IfStatement__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IfStatement__Group__3__Impl
	rule__IfStatement__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__IfStatement__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIfStatementAccess().getThenClauseAssignment_3()); }
	(rule__IfStatement__ThenClauseAssignment_3)
	{ after(grammarAccess.getIfStatementAccess().getThenClauseAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IfStatement__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IfStatement__Group__4__Impl
	rule__IfStatement__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__IfStatement__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIfStatementAccess().getGroup_4()); }
	(rule__IfStatement__Group_4__0)?
	{ after(grammarAccess.getIfStatementAccess().getGroup_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IfStatement__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IfStatement__Group__5__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IfStatement__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIfStatementAccess().getFiKeyword_5()); }
	'fi'
	{ after(grammarAccess.getIfStatementAccess().getFiKeyword_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IfStatement__Group_4__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IfStatement__Group_4__0__Impl
	rule__IfStatement__Group_4__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IfStatement__Group_4__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIfStatementAccess().getElseKeyword_4_0()); }
	'else'
	{ after(grammarAccess.getIfStatementAccess().getElseKeyword_4_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IfStatement__Group_4__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IfStatement__Group_4__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IfStatement__Group_4__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIfStatementAccess().getElseClauseAssignment_4_1()); }
	(rule__IfStatement__ElseClauseAssignment_4_1)
	{ after(grammarAccess.getIfStatementAccess().getElseClauseAssignment_4_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__WhileStatement__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__WhileStatement__Group__0__Impl
	rule__WhileStatement__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__WhileStatement__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getWhileStatementAccess().getWhileKeyword_0()); }
	'while'
	{ after(grammarAccess.getWhileStatementAccess().getWhileKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__WhileStatement__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__WhileStatement__Group__1__Impl
	rule__WhileStatement__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__WhileStatement__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getWhileStatementAccess().getConditionAssignment_1()); }
	(rule__WhileStatement__ConditionAssignment_1)
	{ after(grammarAccess.getWhileStatementAccess().getConditionAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__WhileStatement__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__WhileStatement__Group__2__Impl
	rule__WhileStatement__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__WhileStatement__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getWhileStatementAccess().getDoKeyword_2()); }
	'do'
	{ after(grammarAccess.getWhileStatementAccess().getDoKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__WhileStatement__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__WhileStatement__Group__3__Impl
	rule__WhileStatement__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__WhileStatement__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getWhileStatementAccess().getBodyAssignment_3()); }
	(rule__WhileStatement__BodyAssignment_3)
	{ after(grammarAccess.getWhileStatementAccess().getBodyAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__WhileStatement__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__WhileStatement__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__WhileStatement__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getWhileStatementAccess().getOdKeyword_4()); }
	'od'
	{ after(grammarAccess.getWhileStatementAccess().getOdKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SwitchStatement__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchStatement__Group__0__Impl
	rule__SwitchStatement__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchStatementAccess().getSwitchKeyword_0()); }
	'switch'
	{ after(grammarAccess.getSwitchStatementAccess().getSwitchKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchStatement__Group__1__Impl
	rule__SwitchStatement__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchStatementAccess().getExpressionAssignment_1()); }
	(rule__SwitchStatement__ExpressionAssignment_1)
	{ after(grammarAccess.getSwitchStatementAccess().getExpressionAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchStatement__Group__2__Impl
	rule__SwitchStatement__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchStatementAccess().getDoKeyword_2()); }
	'do'
	{ after(grammarAccess.getSwitchStatementAccess().getDoKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchStatement__Group__3__Impl
	rule__SwitchStatement__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchStatementAccess().getAlternatives_3()); }
	(rule__SwitchStatement__Alternatives_3)
	{ after(grammarAccess.getSwitchStatementAccess().getAlternatives_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchStatement__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchStatementAccess().getOdKeyword_4()); }
	'od'
	{ after(grammarAccess.getSwitchStatementAccess().getOdKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SwitchStatement__Group_3_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchStatement__Group_3_0__0__Impl
	rule__SwitchStatement__Group_3_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__Group_3_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	(
		{ before(grammarAccess.getSwitchStatementAccess().getCasesAssignment_3_0_0()); }
		(rule__SwitchStatement__CasesAssignment_3_0_0)
		{ after(grammarAccess.getSwitchStatementAccess().getCasesAssignment_3_0_0()); }
	)
	(
		{ before(grammarAccess.getSwitchStatementAccess().getCasesAssignment_3_0_0()); }
		(rule__SwitchStatement__CasesAssignment_3_0_0)*
		{ after(grammarAccess.getSwitchStatementAccess().getCasesAssignment_3_0_0()); }
	)
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__Group_3_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchStatement__Group_3_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__Group_3_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchStatementAccess().getGroup_3_0_1()); }
	(rule__SwitchStatement__Group_3_0_1__0)?
	{ after(grammarAccess.getSwitchStatementAccess().getGroup_3_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SwitchStatement__Group_3_0_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchStatement__Group_3_0_1__0__Impl
	rule__SwitchStatement__Group_3_0_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__Group_3_0_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchStatementAccess().getDefaultKeyword_3_0_1_0()); }
	'default'
	{ after(grammarAccess.getSwitchStatementAccess().getDefaultKeyword_3_0_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__Group_3_0_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchStatement__Group_3_0_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__Group_3_0_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchStatementAccess().getDefaultBodyAssignment_3_0_1_1()); }
	(rule__SwitchStatement__DefaultBodyAssignment_3_0_1_1)
	{ after(grammarAccess.getSwitchStatementAccess().getDefaultBodyAssignment_3_0_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SwitchStatement__Group_3_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchStatement__Group_3_1__0__Impl
	rule__SwitchStatement__Group_3_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__Group_3_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchStatementAccess().getDefaultKeyword_3_1_0()); }
	'default'
	{ after(grammarAccess.getSwitchStatementAccess().getDefaultKeyword_3_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__Group_3_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchStatement__Group_3_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__Group_3_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchStatementAccess().getDefaultBodyAssignment_3_1_1()); }
	(rule__SwitchStatement__DefaultBodyAssignment_3_1_1)
	{ after(grammarAccess.getSwitchStatementAccess().getDefaultBodyAssignment_3_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SwitchStatementCase__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchStatementCase__Group__0__Impl
	rule__SwitchStatementCase__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatementCase__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchStatementCaseAccess().getCaseKeyword_0()); }
	'case'
	{ after(grammarAccess.getSwitchStatementCaseAccess().getCaseKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatementCase__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchStatementCase__Group__1__Impl
	rule__SwitchStatementCase__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatementCase__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchStatementCaseAccess().getValueAssignment_1()); }
	(rule__SwitchStatementCase__ValueAssignment_1)
	{ after(grammarAccess.getSwitchStatementCaseAccess().getValueAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatementCase__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchStatementCase__Group__2__Impl
	rule__SwitchStatementCase__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatementCase__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchStatementCaseAccess().getThenKeyword_2()); }
	'then'
	{ after(grammarAccess.getSwitchStatementCaseAccess().getThenKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatementCase__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SwitchStatementCase__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatementCase__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSwitchStatementCaseAccess().getBodyAssignment_3()); }
	(rule__SwitchStatementCase__BodyAssignment_3)
	{ after(grammarAccess.getSwitchStatementCaseAccess().getBodyAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ParStatement__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ParStatement__Group__0__Impl
	rule__ParStatement__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ParStatement__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getParStatementAccess().getParKeyword_0()); }
	'par'
	{ after(grammarAccess.getParStatementAccess().getParKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ParStatement__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ParStatement__Group__1__Impl
	rule__ParStatement__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ParStatement__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getParStatementAccess().getClausesAssignment_1()); }
	(rule__ParStatement__ClausesAssignment_1)
	{ after(grammarAccess.getParStatementAccess().getClausesAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ParStatement__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ParStatement__Group__2__Impl
	rule__ParStatement__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__ParStatement__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getParStatementAccess().getGroup_2()); }
	(rule__ParStatement__Group_2__0)*
	{ after(grammarAccess.getParStatementAccess().getGroup_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ParStatement__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ParStatement__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ParStatement__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getParStatementAccess().getRapKeyword_3()); }
	'rap'
	{ after(grammarAccess.getParStatementAccess().getRapKeyword_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ParStatement__Group_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ParStatement__Group_2__0__Impl
	rule__ParStatement__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ParStatement__Group_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getParStatementAccess().getAndKeyword_2_0()); }
	'and'
	{ after(grammarAccess.getParStatementAccess().getAndKeyword_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ParStatement__Group_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ParStatement__Group_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ParStatement__Group_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getParStatementAccess().getClausesAssignment_2_1()); }
	(rule__ParStatement__ClausesAssignment_2_1)
	{ after(grammarAccess.getParStatementAccess().getClausesAssignment_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SelStatement__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SelStatement__Group__0__Impl
	rule__SelStatement__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SelStatement__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSelStatementAccess().getSelKeyword_0()); }
	'sel'
	{ after(grammarAccess.getSelStatementAccess().getSelKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SelStatement__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SelStatement__Group__1__Impl
	rule__SelStatement__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__SelStatement__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSelStatementAccess().getClausesAssignment_1()); }
	(rule__SelStatement__ClausesAssignment_1)
	{ after(grammarAccess.getSelStatementAccess().getClausesAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SelStatement__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SelStatement__Group__2__Impl
	rule__SelStatement__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__SelStatement__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSelStatementAccess().getGroup_2()); }
	(rule__SelStatement__Group_2__0)*
	{ after(grammarAccess.getSelStatementAccess().getGroup_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SelStatement__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SelStatement__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SelStatement__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSelStatementAccess().getLesKeyword_3()); }
	'les'
	{ after(grammarAccess.getSelStatementAccess().getLesKeyword_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SelStatement__Group_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SelStatement__Group_2__0__Impl
	rule__SelStatement__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SelStatement__Group_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSelStatementAccess().getOrKeyword_2_0()); }
	'or'
	{ after(grammarAccess.getSelStatementAccess().getOrKeyword_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SelStatement__Group_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SelStatement__Group_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SelStatement__Group_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSelStatementAccess().getClausesAssignment_2_1()); }
	(rule__SelStatement__ClausesAssignment_2_1)
	{ after(grammarAccess.getSelStatementAccess().getClausesAssignment_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__AbortStatement__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AbortStatement__Group__0__Impl
	rule__AbortStatement__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__AbortStatement__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAbortStatementAccess().getAbortKeyword_0()); }
	'abort'
	{ after(grammarAccess.getAbortStatementAccess().getAbortKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__AbortStatement__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AbortStatement__Group__1__Impl
	rule__AbortStatement__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__AbortStatement__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAbortStatementAccess().getNormalClauseAssignment_1()); }
	(rule__AbortStatement__NormalClauseAssignment_1)
	{ after(grammarAccess.getAbortStatementAccess().getNormalClauseAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__AbortStatement__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AbortStatement__Group__2__Impl
	rule__AbortStatement__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__AbortStatement__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAbortStatementAccess().getWithKeyword_2()); }
	'with'
	{ after(grammarAccess.getAbortStatementAccess().getWithKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__AbortStatement__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AbortStatement__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__AbortStatement__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAbortStatementAccess().getAbortingClauseAssignment_3()); }
	(rule__AbortStatement__AbortingClauseAssignment_3)
	{ after(grammarAccess.getAbortStatementAccess().getAbortingClauseAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__InterruptStatement__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__InterruptStatement__Group__0__Impl
	rule__InterruptStatement__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__InterruptStatement__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getInterruptStatementAccess().getInterruptKeyword_0()); }
	'interrupt'
	{ after(grammarAccess.getInterruptStatementAccess().getInterruptKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__InterruptStatement__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__InterruptStatement__Group__1__Impl
	rule__InterruptStatement__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__InterruptStatement__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getInterruptStatementAccess().getNormalClauseAssignment_1()); }
	(rule__InterruptStatement__NormalClauseAssignment_1)
	{ after(grammarAccess.getInterruptStatementAccess().getNormalClauseAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__InterruptStatement__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__InterruptStatement__Group__2__Impl
	rule__InterruptStatement__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__InterruptStatement__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getInterruptStatementAccess().getWithKeyword_2()); }
	'with'
	{ after(grammarAccess.getInterruptStatementAccess().getWithKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__InterruptStatement__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__InterruptStatement__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__InterruptStatement__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getInterruptStatementAccess().getInterruptingClauseAssignment_3()); }
	(rule__InterruptStatement__InterruptingClauseAssignment_3)
	{ after(grammarAccess.getInterruptStatementAccess().getInterruptingClauseAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessMethodCall__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethodCall__Group__0__Impl
	rule__ProcessMethodCall__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodCallAccess().getMethodAssignment_0()); }
	(rule__ProcessMethodCall__MethodAssignment_0)
	{ after(grammarAccess.getProcessMethodCallAccess().getMethodAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethodCall__Group__1__Impl
	rule__ProcessMethodCall__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodCallAccess().getLeftParenthesisKeyword_1()); }
	'('
	{ after(grammarAccess.getProcessMethodCallAccess().getLeftParenthesisKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethodCall__Group__2__Impl
	rule__ProcessMethodCall__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodCallAccess().getGroup_2()); }
	(rule__ProcessMethodCall__Group_2__0)?
	{ after(grammarAccess.getProcessMethodCallAccess().getGroup_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethodCall__Group__3__Impl
	rule__ProcessMethodCall__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodCallAccess().getRightParenthesisKeyword_3()); }
	')'
	{ after(grammarAccess.getProcessMethodCallAccess().getRightParenthesisKeyword_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethodCall__Group__4__Impl
	rule__ProcessMethodCall__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodCallAccess().getLeftParenthesisKeyword_4()); }
	'('
	{ after(grammarAccess.getProcessMethodCallAccess().getLeftParenthesisKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethodCall__Group__5__Impl
	rule__ProcessMethodCall__Group__6
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodCallAccess().getGroup_5()); }
	(rule__ProcessMethodCall__Group_5__0)?
	{ after(grammarAccess.getProcessMethodCallAccess().getGroup_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group__6
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethodCall__Group__6__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group__6__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodCallAccess().getRightParenthesisKeyword_6()); }
	')'
	{ after(grammarAccess.getProcessMethodCallAccess().getRightParenthesisKeyword_6()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessMethodCall__Group_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethodCall__Group_2__0__Impl
	rule__ProcessMethodCall__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodCallAccess().getInputArgumentsAssignment_2_0()); }
	(rule__ProcessMethodCall__InputArgumentsAssignment_2_0)
	{ after(grammarAccess.getProcessMethodCallAccess().getInputArgumentsAssignment_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethodCall__Group_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodCallAccess().getGroup_2_1()); }
	(rule__ProcessMethodCall__Group_2_1__0)*
	{ after(grammarAccess.getProcessMethodCallAccess().getGroup_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessMethodCall__Group_2_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethodCall__Group_2_1__0__Impl
	rule__ProcessMethodCall__Group_2_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group_2_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodCallAccess().getCommaKeyword_2_1_0()); }
	','
	{ after(grammarAccess.getProcessMethodCallAccess().getCommaKeyword_2_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group_2_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethodCall__Group_2_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group_2_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodCallAccess().getInputArgumentsAssignment_2_1_1()); }
	(rule__ProcessMethodCall__InputArgumentsAssignment_2_1_1)
	{ after(grammarAccess.getProcessMethodCallAccess().getInputArgumentsAssignment_2_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessMethodCall__Group_5__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethodCall__Group_5__0__Impl
	rule__ProcessMethodCall__Group_5__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group_5__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodCallAccess().getOutputVariablesAssignment_5_0()); }
	(rule__ProcessMethodCall__OutputVariablesAssignment_5_0)
	{ after(grammarAccess.getProcessMethodCallAccess().getOutputVariablesAssignment_5_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group_5__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethodCall__Group_5__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group_5__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodCallAccess().getGroup_5_1()); }
	(rule__ProcessMethodCall__Group_5_1__0)*
	{ after(grammarAccess.getProcessMethodCallAccess().getGroup_5_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ProcessMethodCall__Group_5_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethodCall__Group_5_1__0__Impl
	rule__ProcessMethodCall__Group_5_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group_5_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodCallAccess().getCommaKeyword_5_1_0()); }
	','
	{ after(grammarAccess.getProcessMethodCallAccess().getCommaKeyword_5_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group_5_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ProcessMethodCall__Group_5_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__Group_5_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getProcessMethodCallAccess().getOutputVariablesAssignment_5_1_1()); }
	(rule__ProcessMethodCall__OutputVariablesAssignment_5_1_1)
	{ after(grammarAccess.getProcessMethodCallAccess().getOutputVariablesAssignment_5_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__StatementSequenceRoundBracket__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StatementSequenceRoundBracket__Group__0__Impl
	rule__StatementSequenceRoundBracket__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__StatementSequenceRoundBracket__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStatementSequenceRoundBracketAccess().getLeftParenthesisKeyword_0()); }
	'('
	{ after(grammarAccess.getStatementSequenceRoundBracketAccess().getLeftParenthesisKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StatementSequenceRoundBracket__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StatementSequenceRoundBracket__Group__1__Impl
	rule__StatementSequenceRoundBracket__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__StatementSequenceRoundBracket__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStatementSequenceRoundBracketAccess().getStatementsAssignment_1()); }
	(rule__StatementSequenceRoundBracket__StatementsAssignment_1)
	{ after(grammarAccess.getStatementSequenceRoundBracketAccess().getStatementsAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StatementSequenceRoundBracket__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StatementSequenceRoundBracket__Group__2__Impl
	rule__StatementSequenceRoundBracket__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__StatementSequenceRoundBracket__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStatementSequenceRoundBracketAccess().getGroup_2()); }
	(rule__StatementSequenceRoundBracket__Group_2__0)*
	{ after(grammarAccess.getStatementSequenceRoundBracketAccess().getGroup_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StatementSequenceRoundBracket__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StatementSequenceRoundBracket__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__StatementSequenceRoundBracket__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStatementSequenceRoundBracketAccess().getRightParenthesisKeyword_3()); }
	')'
	{ after(grammarAccess.getStatementSequenceRoundBracketAccess().getRightParenthesisKeyword_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__StatementSequenceRoundBracket__Group_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StatementSequenceRoundBracket__Group_2__0__Impl
	rule__StatementSequenceRoundBracket__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__StatementSequenceRoundBracket__Group_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStatementSequenceRoundBracketAccess().getSemicolonKeyword_2_0()); }
	';'
	{ after(grammarAccess.getStatementSequenceRoundBracketAccess().getSemicolonKeyword_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StatementSequenceRoundBracket__Group_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StatementSequenceRoundBracket__Group_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__StatementSequenceRoundBracket__Group_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStatementSequenceRoundBracketAccess().getStatementsAssignment_2_1()); }
	(rule__StatementSequenceRoundBracket__StatementsAssignment_2_1)
	{ after(grammarAccess.getStatementSequenceRoundBracketAccess().getStatementsAssignment_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__BracketedArgumentStartExpressionLevel2__Group_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel2__Group_0__0__Impl
	rule__BracketedArgumentStartExpressionLevel2__Group_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel2__Group_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getBracketedArgumentStartExpressionLevel3ParserRuleCall_0_0()); }
	(ruleBracketedArgumentStartExpressionLevel3)
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getBracketedArgumentStartExpressionLevel3ParserRuleCall_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel2__Group_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel2__Group_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel2__Group_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getGroup_0_1()); }
	(rule__BracketedArgumentStartExpressionLevel2__Group_0_1__0)*
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getGroup_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__BracketedArgumentStartExpressionLevel2__Group_0_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel2__Group_0_1__0__Impl
	rule__BracketedArgumentStartExpressionLevel2__Group_0_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel2__Group_0_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_0_1_0()); }
	()
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_0_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel2__Group_0_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel2__Group_0_1__1__Impl
	rule__BracketedArgumentStartExpressionLevel2__Group_0_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel2__Group_0_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getNameAssignment_0_1_1()); }
	(rule__BracketedArgumentStartExpressionLevel2__NameAssignment_0_1_1)
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getNameAssignment_0_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel2__Group_0_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel2__Group_0_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel2__Group_0_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getRightOperandAssignment_0_1_2()); }
	(rule__BracketedArgumentStartExpressionLevel2__RightOperandAssignment_0_1_2)
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getRightOperandAssignment_0_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__BracketedArgumentStartExpressionLevel2__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel2__Group_1__0__Impl
	rule__BracketedArgumentStartExpressionLevel2__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel2__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getExpressionSequenceRoundBracketParserRuleCall_1_0()); }
	ruleExpressionSequenceRoundBracket
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getExpressionSequenceRoundBracketParserRuleCall_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel2__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel2__Group_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel2__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getGroup_1_1()); }
		(rule__BracketedArgumentStartExpressionLevel2__Group_1_1__0)
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getGroup_1_1()); }
	)
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getGroup_1_1()); }
		(rule__BracketedArgumentStartExpressionLevel2__Group_1_1__0)*
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getGroup_1_1()); }
	)
)
;
finally {
	restoreStackSize(stackSize);
}


rule__BracketedArgumentStartExpressionLevel2__Group_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel2__Group_1_1__0__Impl
	rule__BracketedArgumentStartExpressionLevel2__Group_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel2__Group_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_1_1_0()); }
	()
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel2__Group_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel2__Group_1_1__1__Impl
	rule__BracketedArgumentStartExpressionLevel2__Group_1_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel2__Group_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getNameAssignment_1_1_1()); }
	(rule__BracketedArgumentStartExpressionLevel2__NameAssignment_1_1_1)
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getNameAssignment_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel2__Group_1_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel2__Group_1_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel2__Group_1_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getRightOperandAssignment_1_1_2()); }
	(rule__BracketedArgumentStartExpressionLevel2__RightOperandAssignment_1_1_2)
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getRightOperandAssignment_1_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__BracketedArgumentStartExpressionLevel3__Group_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel3__Group_0__0__Impl
	rule__BracketedArgumentStartExpressionLevel3__Group_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel3__Group_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getBracketedArgumentStartExpressionLevel4ParserRuleCall_0_0()); }
	(ruleBracketedArgumentStartExpressionLevel4)
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getBracketedArgumentStartExpressionLevel4ParserRuleCall_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel3__Group_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel3__Group_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel3__Group_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getGroup_0_1()); }
	(rule__BracketedArgumentStartExpressionLevel3__Group_0_1__0)*
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getGroup_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__BracketedArgumentStartExpressionLevel3__Group_0_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel3__Group_0_1__0__Impl
	rule__BracketedArgumentStartExpressionLevel3__Group_0_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel3__Group_0_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_0_1_0()); }
	()
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_0_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel3__Group_0_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel3__Group_0_1__1__Impl
	rule__BracketedArgumentStartExpressionLevel3__Group_0_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel3__Group_0_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getNameAssignment_0_1_1()); }
	(rule__BracketedArgumentStartExpressionLevel3__NameAssignment_0_1_1)
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getNameAssignment_0_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel3__Group_0_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel3__Group_0_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel3__Group_0_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getRightOperandAssignment_0_1_2()); }
	(rule__BracketedArgumentStartExpressionLevel3__RightOperandAssignment_0_1_2)
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getRightOperandAssignment_0_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__BracketedArgumentStartExpressionLevel3__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel3__Group_1__0__Impl
	rule__BracketedArgumentStartExpressionLevel3__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel3__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getExpressionSequenceRoundBracketParserRuleCall_1_0()); }
	ruleExpressionSequenceRoundBracket
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getExpressionSequenceRoundBracketParserRuleCall_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel3__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel3__Group_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel3__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getGroup_1_1()); }
		(rule__BracketedArgumentStartExpressionLevel3__Group_1_1__0)
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getGroup_1_1()); }
	)
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getGroup_1_1()); }
		(rule__BracketedArgumentStartExpressionLevel3__Group_1_1__0)*
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getGroup_1_1()); }
	)
)
;
finally {
	restoreStackSize(stackSize);
}


rule__BracketedArgumentStartExpressionLevel3__Group_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel3__Group_1_1__0__Impl
	rule__BracketedArgumentStartExpressionLevel3__Group_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel3__Group_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_1_1_0()); }
	()
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel3__Group_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel3__Group_1_1__1__Impl
	rule__BracketedArgumentStartExpressionLevel3__Group_1_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel3__Group_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getNameAssignment_1_1_1()); }
	(rule__BracketedArgumentStartExpressionLevel3__NameAssignment_1_1_1)
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getNameAssignment_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel3__Group_1_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel3__Group_1_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel3__Group_1_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getRightOperandAssignment_1_1_2()); }
	(rule__BracketedArgumentStartExpressionLevel3__RightOperandAssignment_1_1_2)
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getRightOperandAssignment_1_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__BracketedArgumentStartExpressionLevel4__Group_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel4__Group_0__0__Impl
	rule__BracketedArgumentStartExpressionLevel4__Group_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel4__Group_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getBracketedArgumentStartExpressionLevel5ParserRuleCall_0_0()); }
	(ruleBracketedArgumentStartExpressionLevel5)
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getBracketedArgumentStartExpressionLevel5ParserRuleCall_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel4__Group_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel4__Group_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel4__Group_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getGroup_0_1()); }
	(rule__BracketedArgumentStartExpressionLevel4__Group_0_1__0)*
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getGroup_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__BracketedArgumentStartExpressionLevel4__Group_0_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel4__Group_0_1__0__Impl
	rule__BracketedArgumentStartExpressionLevel4__Group_0_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel4__Group_0_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_0_1_0()); }
	()
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_0_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel4__Group_0_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel4__Group_0_1__1__Impl
	rule__BracketedArgumentStartExpressionLevel4__Group_0_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel4__Group_0_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getNameAssignment_0_1_1()); }
	(rule__BracketedArgumentStartExpressionLevel4__NameAssignment_0_1_1)
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getNameAssignment_0_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel4__Group_0_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel4__Group_0_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel4__Group_0_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getRightOperandAssignment_0_1_2()); }
	(rule__BracketedArgumentStartExpressionLevel4__RightOperandAssignment_0_1_2)
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getRightOperandAssignment_0_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__BracketedArgumentStartExpressionLevel4__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel4__Group_1__0__Impl
	rule__BracketedArgumentStartExpressionLevel4__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel4__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getExpressionSequenceRoundBracketParserRuleCall_1_0()); }
	ruleExpressionSequenceRoundBracket
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getExpressionSequenceRoundBracketParserRuleCall_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel4__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel4__Group_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel4__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getGroup_1_1()); }
		(rule__BracketedArgumentStartExpressionLevel4__Group_1_1__0)
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getGroup_1_1()); }
	)
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getGroup_1_1()); }
		(rule__BracketedArgumentStartExpressionLevel4__Group_1_1__0)*
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getGroup_1_1()); }
	)
)
;
finally {
	restoreStackSize(stackSize);
}


rule__BracketedArgumentStartExpressionLevel4__Group_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel4__Group_1_1__0__Impl
	rule__BracketedArgumentStartExpressionLevel4__Group_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel4__Group_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_1_0()); }
	()
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel4__Group_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel4__Group_1_1__1__Impl
	rule__BracketedArgumentStartExpressionLevel4__Group_1_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel4__Group_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getNameAssignment_1_1_1()); }
	(rule__BracketedArgumentStartExpressionLevel4__NameAssignment_1_1_1)
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getNameAssignment_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel4__Group_1_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel4__Group_1_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel4__Group_1_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getRightOperandAssignment_1_1_2()); }
	(rule__BracketedArgumentStartExpressionLevel4__RightOperandAssignment_1_1_2)
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getRightOperandAssignment_1_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__BracketedArgumentStartExpressionLevel5__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel5__Group__0__Impl
	rule__BracketedArgumentStartExpressionLevel5__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getExpressionSequenceRoundBracketParserRuleCall_0()); }
	ruleExpressionSequenceRoundBracket
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getExpressionSequenceRoundBracketParserRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel5__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getGroup_1()); }
		(rule__BracketedArgumentStartExpressionLevel5__Group_1__0)
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getGroup_1()); }
	)
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getGroup_1()); }
		(rule__BracketedArgumentStartExpressionLevel5__Group_1__0)*
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getGroup_1()); }
	)
)
;
finally {
	restoreStackSize(stackSize);
}


rule__BracketedArgumentStartExpressionLevel5__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel5__Group_1__0__Impl
	rule__BracketedArgumentStartExpressionLevel5__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getDataMethodCallExpressionTargetAction_1_0()); }
	()
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getDataMethodCallExpressionTargetAction_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel5__Group_1__1__Impl
	rule__BracketedArgumentStartExpressionLevel5__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getOnSuperClassAssignment_1_1()); }
	(rule__BracketedArgumentStartExpressionLevel5__OnSuperClassAssignment_1_1)?
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getOnSuperClassAssignment_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__Group_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel5__Group_1__2__Impl
	rule__BracketedArgumentStartExpressionLevel5__Group_1__3
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__Group_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getNameAssignment_1_2()); }
	(rule__BracketedArgumentStartExpressionLevel5__NameAssignment_1_2)
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getNameAssignment_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__Group_1__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel5__Group_1__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__Group_1__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getGroup_1_3()); }
	(rule__BracketedArgumentStartExpressionLevel5__Group_1_3__0)?
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getGroup_1_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__BracketedArgumentStartExpressionLevel5__Group_1_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel5__Group_1_3__0__Impl
	rule__BracketedArgumentStartExpressionLevel5__Group_1_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__Group_1_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getLeftParenthesisKeyword_1_3_0()); }
	'('
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getLeftParenthesisKeyword_1_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__Group_1_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel5__Group_1_3__1__Impl
	rule__BracketedArgumentStartExpressionLevel5__Group_1_3__2
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__Group_1_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getGroup_1_3_1()); }
	(rule__BracketedArgumentStartExpressionLevel5__Group_1_3_1__0)?
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getGroup_1_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__Group_1_3__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel5__Group_1_3__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__Group_1_3__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getRightParenthesisKeyword_1_3_2()); }
	')'
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getRightParenthesisKeyword_1_3_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__BracketedArgumentStartExpressionLevel5__Group_1_3_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel5__Group_1_3_1__0__Impl
	rule__BracketedArgumentStartExpressionLevel5__Group_1_3_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__Group_1_3_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getArgumentsAssignment_1_3_1_0()); }
	(rule__BracketedArgumentStartExpressionLevel5__ArgumentsAssignment_1_3_1_0)
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getArgumentsAssignment_1_3_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__Group_1_3_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel5__Group_1_3_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__Group_1_3_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getGroup_1_3_1_1()); }
	(rule__BracketedArgumentStartExpressionLevel5__Group_1_3_1_1__0)*
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getGroup_1_3_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__BracketedArgumentStartExpressionLevel5__Group_1_3_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel5__Group_1_3_1_1__0__Impl
	rule__BracketedArgumentStartExpressionLevel5__Group_1_3_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__Group_1_3_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getCommaKeyword_1_3_1_1_0()); }
	','
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getCommaKeyword_1_3_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__Group_1_3_1_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BracketedArgumentStartExpressionLevel5__Group_1_3_1_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__Group_1_3_1_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getArgumentsAssignment_1_3_1_1_1()); }
	(rule__BracketedArgumentStartExpressionLevel5__ArgumentsAssignment_1_3_1_1_1)
	{ after(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getArgumentsAssignment_1_3_1_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__INTEGER__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__INTEGER__Group__0__Impl
	rule__INTEGER__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__INTEGER__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getINTEGERAccess().getAlternatives_0()); }
	(rule__INTEGER__Alternatives_0)?
	{ after(grammarAccess.getINTEGERAccess().getAlternatives_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__INTEGER__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__INTEGER__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__INTEGER__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getINTEGERAccess().getAlternatives_1()); }
	(rule__INTEGER__Alternatives_1)
	{ after(grammarAccess.getINTEGERAccess().getAlternatives_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__REAL__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__REAL__Group__0__Impl
	rule__REAL__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__REAL__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getREALAccess().getAlternatives_0()); }
	(rule__REAL__Alternatives_0)?
	{ after(grammarAccess.getREALAccess().getAlternatives_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__REAL__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__REAL__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__REAL__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getREALAccess().getREAL_CORETerminalRuleCall_1()); }
	RULE_REAL_CORE
	{ after(grammarAccess.getREALAccess().getREAL_CORETerminalRuleCall_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__FLOAT__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FLOAT__Group__0__Impl
	rule__FLOAT__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__FLOAT__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFLOATAccess().getAlternatives_0()); }
	(rule__FLOAT__Alternatives_0)?
	{ after(grammarAccess.getFLOATAccess().getAlternatives_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__FLOAT__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FLOAT__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__FLOAT__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFLOATAccess().getAlternatives_1()); }
	(rule__FLOAT__Alternatives_1)
	{ after(grammarAccess.getFLOATAccess().getAlternatives_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Poosl__ImportsAssignment_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPooslAccess().getImportsImportParserRuleCall_1_0_0()); }
		ruleImport
		{ after(grammarAccess.getPooslAccess().getImportsImportParserRuleCall_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Poosl__ImportLibsAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPooslAccess().getImportLibsImportLibParserRuleCall_1_1_0()); }
		ruleImportLib
		{ after(grammarAccess.getPooslAccess().getImportLibsImportLibParserRuleCall_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Poosl__DataClassesAssignment_2_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPooslAccess().getDataClassesDataClassParserRuleCall_2_0_0()); }
		ruleDataClass
		{ after(grammarAccess.getPooslAccess().getDataClassesDataClassParserRuleCall_2_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Poosl__ProcessClassesAssignment_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPooslAccess().getProcessClassesProcessClassParserRuleCall_2_1_0()); }
		ruleProcessClass
		{ after(grammarAccess.getPooslAccess().getProcessClassesProcessClassParserRuleCall_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Poosl__ClusterClassesAssignment_2_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPooslAccess().getClusterClassesClusterClassParserRuleCall_2_2_0()); }
		ruleClusterClass
		{ after(grammarAccess.getPooslAccess().getClusterClassesClusterClassParserRuleCall_2_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Poosl__ClusterClassesAssignment_3_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPooslAccess().getClusterClassesSystemParserRuleCall_3_0_0()); }
		ruleSystem
		{ after(grammarAccess.getPooslAccess().getClusterClassesSystemParserRuleCall_3_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Poosl__DataClassesAssignment_3_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPooslAccess().getDataClassesDataClassParserRuleCall_3_1_0_0()); }
		ruleDataClass
		{ after(grammarAccess.getPooslAccess().getDataClassesDataClassParserRuleCall_3_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Poosl__ProcessClassesAssignment_3_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPooslAccess().getProcessClassesProcessClassParserRuleCall_3_1_1_0()); }
		ruleProcessClass
		{ after(grammarAccess.getPooslAccess().getProcessClassesProcessClassParserRuleCall_3_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Poosl__ClusterClassesAssignment_3_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPooslAccess().getClusterClassesClusterClassParserRuleCall_3_1_2_0()); }
		ruleClusterClass
		{ after(grammarAccess.getPooslAccess().getClusterClassesClusterClassParserRuleCall_3_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Import__ImportURIAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getImportAccess().getImportURIPOOSL_STRINGTerminalRuleCall_1_0()); }
		RULE_POOSL_STRING
		{ after(grammarAccess.getImportAccess().getImportURIPOOSL_STRINGTerminalRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ImportLib__ImportURIAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getImportLibAccess().getImportURIPOOSL_STRINGTerminalRuleCall_1_0()); }
		RULE_POOSL_STRING
		{ after(grammarAccess.getImportLibAccess().getImportURIPOOSL_STRINGTerminalRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__AnnotationsAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataClassAccess().getAnnotationsAnnotationParserRuleCall_0_0()); }
		ruleAnnotation
		{ after(grammarAccess.getDataClassAccess().getAnnotationsAnnotationParserRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__NativeAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataClassAccess().getNativeNativeKeyword_1_0()); }
		(
			{ before(grammarAccess.getDataClassAccess().getNativeNativeKeyword_1_0()); }
			'native'
			{ after(grammarAccess.getDataClassAccess().getNativeNativeKeyword_1_0()); }
		)
		{ after(grammarAccess.getDataClassAccess().getNativeNativeKeyword_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__NameAssignment_4
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataClassAccess().getNameIDENTIFIERParserRuleCall_4_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getDataClassAccess().getNameIDENTIFIERParserRuleCall_4_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__SuperClassAssignment_5_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataClassAccess().getSuperClassIDENTIFIERParserRuleCall_5_1_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getDataClassAccess().getSuperClassIDENTIFIERParserRuleCall_5_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__InstanceVariablesAssignment_7_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataClassAccess().getInstanceVariablesDeclarationParserRuleCall_7_0_0()); }
		ruleDeclaration
		{ after(grammarAccess.getDataClassAccess().getInstanceVariablesDeclarationParserRuleCall_7_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__InstanceVariablesAssignment_7_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataClassAccess().getInstanceVariablesDeclarationParserRuleCall_7_1_1_0()); }
		ruleDeclaration
		{ after(grammarAccess.getDataClassAccess().getInstanceVariablesDeclarationParserRuleCall_7_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__DataMethodsNamedAssignment_9_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataClassAccess().getDataMethodsNamedDataMethodNamedParserRuleCall_9_0_0()); }
		ruleDataMethodNamed
		{ after(grammarAccess.getDataClassAccess().getDataMethodsNamedDataMethodNamedParserRuleCall_9_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__DataMethodsUnaryOperatorAssignment_9_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataClassAccess().getDataMethodsUnaryOperatorDataMethodUnaryOperatorParserRuleCall_9_1_0()); }
		ruleDataMethodUnaryOperator
		{ after(grammarAccess.getDataClassAccess().getDataMethodsUnaryOperatorDataMethodUnaryOperatorParserRuleCall_9_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataClass__DataMethodsBinaryOperatorAssignment_9_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataClassAccess().getDataMethodsBinaryOperatorDataMethodBinaryOperatorParserRuleCall_9_2_0()); }
		ruleDataMethodBinaryOperator
		{ after(grammarAccess.getDataClassAccess().getDataMethodsBinaryOperatorDataMethodBinaryOperatorParserRuleCall_9_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__AnnotationsAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodNamedAccess().getAnnotationsAnnotationParserRuleCall_0_0()); }
		ruleAnnotation
		{ after(grammarAccess.getDataMethodNamedAccess().getAnnotationsAnnotationParserRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__NativeAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodNamedAccess().getNativeNativeKeyword_1_0()); }
		(
			{ before(grammarAccess.getDataMethodNamedAccess().getNativeNativeKeyword_1_0()); }
			'native'
			{ after(grammarAccess.getDataMethodNamedAccess().getNativeNativeKeyword_1_0()); }
		)
		{ after(grammarAccess.getDataMethodNamedAccess().getNativeNativeKeyword_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__NameAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodNamedAccess().getNameIDENTIFIERParserRuleCall_2_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getDataMethodNamedAccess().getNameIDENTIFIERParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__ParametersAssignment_3_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodNamedAccess().getParametersDeclarationParserRuleCall_3_1_0_0()); }
		ruleDeclaration
		{ after(grammarAccess.getDataMethodNamedAccess().getParametersDeclarationParserRuleCall_3_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__ParametersAssignment_3_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodNamedAccess().getParametersDeclarationParserRuleCall_3_1_1_1_0()); }
		ruleDeclaration
		{ after(grammarAccess.getDataMethodNamedAccess().getParametersDeclarationParserRuleCall_3_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__ReturnTypeAssignment_5
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodNamedAccess().getReturnTypeIDENTIFIERParserRuleCall_5_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getDataMethodNamedAccess().getReturnTypeIDENTIFIERParserRuleCall_5_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__LocalVariablesAssignment_6_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodNamedAccess().getLocalVariablesDeclarationParserRuleCall_6_1_0_0()); }
		ruleDeclaration
		{ after(grammarAccess.getDataMethodNamedAccess().getLocalVariablesDeclarationParserRuleCall_6_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__LocalVariablesAssignment_6_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodNamedAccess().getLocalVariablesDeclarationParserRuleCall_6_1_1_1_0()); }
		ruleDeclaration
		{ after(grammarAccess.getDataMethodNamedAccess().getLocalVariablesDeclarationParserRuleCall_6_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodNamed__BodyAssignment_7
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodNamedAccess().getBodyExpressionParserRuleCall_7_0()); }
		ruleExpression
		{ after(grammarAccess.getDataMethodNamedAccess().getBodyExpressionParserRuleCall_7_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__AnnotationsAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getAnnotationsAnnotationParserRuleCall_0_0()); }
		ruleAnnotation
		{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getAnnotationsAnnotationParserRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__NativeAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getNativeNativeKeyword_1_0()); }
		(
			{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getNativeNativeKeyword_1_0()); }
			'native'
			{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getNativeNativeKeyword_1_0()); }
		)
		{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getNativeNativeKeyword_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__NameAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getNameOperatorBinaryEnumRuleCall_2_0()); }
		ruleOperatorBinary
		{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getNameOperatorBinaryEnumRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__ParametersAssignment_4
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getParametersDeclarationParserRuleCall_4_0()); }
		ruleDeclaration
		{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getParametersDeclarationParserRuleCall_4_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__ParametersAssignment_5_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getParametersDeclarationParserRuleCall_5_1_0()); }
		ruleDeclaration
		{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getParametersDeclarationParserRuleCall_5_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__ReturnTypeAssignment_8
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getReturnTypeIDENTIFIERParserRuleCall_8_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getReturnTypeIDENTIFIERParserRuleCall_8_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__LocalVariablesAssignment_9_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getLocalVariablesDeclarationParserRuleCall_9_1_0_0()); }
		ruleDeclaration
		{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getLocalVariablesDeclarationParserRuleCall_9_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__LocalVariablesAssignment_9_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getLocalVariablesDeclarationParserRuleCall_9_1_1_1_0()); }
		ruleDeclaration
		{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getLocalVariablesDeclarationParserRuleCall_9_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodBinaryOperator__BodyAssignment_10
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodBinaryOperatorAccess().getBodyExpressionParserRuleCall_10_0()); }
		ruleExpression
		{ after(grammarAccess.getDataMethodBinaryOperatorAccess().getBodyExpressionParserRuleCall_10_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__AnnotationsAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getAnnotationsAnnotationParserRuleCall_0_0()); }
		ruleAnnotation
		{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getAnnotationsAnnotationParserRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__NativeAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getNativeNativeKeyword_1_0()); }
		(
			{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getNativeNativeKeyword_1_0()); }
			'native'
			{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getNativeNativeKeyword_1_0()); }
		)
		{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getNativeNativeKeyword_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__NameAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getNameOperatorUnaryEnumRuleCall_2_0()); }
		ruleOperatorUnary
		{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getNameOperatorUnaryEnumRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__ReturnTypeAssignment_5
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getReturnTypeIDENTIFIERParserRuleCall_5_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getReturnTypeIDENTIFIERParserRuleCall_5_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__LocalVariablesAssignment_6_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getLocalVariablesDeclarationParserRuleCall_6_1_0_0()); }
		ruleDeclaration
		{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getLocalVariablesDeclarationParserRuleCall_6_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__LocalVariablesAssignment_6_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getLocalVariablesDeclarationParserRuleCall_6_1_1_1_0()); }
		ruleDeclaration
		{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getLocalVariablesDeclarationParserRuleCall_6_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DataMethodUnaryOperator__BodyAssignment_7
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDataMethodUnaryOperatorAccess().getBodyExpressionParserRuleCall_7_0()); }
		ruleExpression
		{ after(grammarAccess.getDataMethodUnaryOperatorAccess().getBodyExpressionParserRuleCall_7_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Declaration__VariablesAssignment_0_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDeclarationAccess().getVariablesVariableParserRuleCall_0_0_0()); }
		ruleVariable
		{ after(grammarAccess.getDeclarationAccess().getVariablesVariableParserRuleCall_0_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Declaration__VariablesAssignment_0_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDeclarationAccess().getVariablesVariableParserRuleCall_0_1_1_0()); }
		ruleVariable
		{ after(grammarAccess.getDeclarationAccess().getVariablesVariableParserRuleCall_0_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Declaration__TypeAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDeclarationAccess().getTypeIDENTIFIERParserRuleCall_2_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getDeclarationAccess().getTypeIDENTIFIERParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Variable__NameAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getVariableAccess().getNameIDENTIFIERParserRuleCall_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getVariableAccess().getNameIDENTIFIERParserRuleCall_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Annotation__NameAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getAnnotationAccess().getNameIDENTIFIERParserRuleCall_1_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getAnnotationAccess().getNameIDENTIFIERParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Annotation__ArgumentsAssignment_2_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getAnnotationAccess().getArgumentsExpressionConstantParserRuleCall_2_1_0_0()); }
		ruleExpressionConstant
		{ after(grammarAccess.getAnnotationAccess().getArgumentsExpressionConstantParserRuleCall_2_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Annotation__ArgumentsAssignment_2_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getAnnotationAccess().getArgumentsExpressionConstantParserRuleCall_2_1_1_1_0()); }
		ruleExpressionConstant
		{ after(grammarAccess.getAnnotationAccess().getArgumentsExpressionConstantParserRuleCall_2_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__AnnotationsAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessClassAccess().getAnnotationsAnnotationParserRuleCall_0_0()); }
		ruleAnnotation
		{ after(grammarAccess.getProcessClassAccess().getAnnotationsAnnotationParserRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__NameAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessClassAccess().getNameIDENTIFIERParserRuleCall_3_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getProcessClassAccess().getNameIDENTIFIERParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__ParametersAssignment_4_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessClassAccess().getParametersDeclarationParserRuleCall_4_1_0_0()); }
		ruleDeclaration
		{ after(grammarAccess.getProcessClassAccess().getParametersDeclarationParserRuleCall_4_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__ParametersAssignment_4_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessClassAccess().getParametersDeclarationParserRuleCall_4_1_1_1_0()); }
		ruleDeclaration
		{ after(grammarAccess.getProcessClassAccess().getParametersDeclarationParserRuleCall_4_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__SuperClassAssignment_5_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessClassAccess().getSuperClassIDENTIFIERParserRuleCall_5_1_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getProcessClassAccess().getSuperClassIDENTIFIERParserRuleCall_5_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__PortsAssignment_7_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessClassAccess().getPortsPortParserRuleCall_7_0_0()); }
		rulePort
		{ after(grammarAccess.getProcessClassAccess().getPortsPortParserRuleCall_7_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__PortsAssignment_7_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessClassAccess().getPortsPortParserRuleCall_7_1_1_0()); }
		rulePort
		{ after(grammarAccess.getProcessClassAccess().getPortsPortParserRuleCall_7_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__ReceiveMessagesAssignment_9_0_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessClassAccess().getReceiveMessagesMessageReceiveSignatureParserRuleCall_9_0_0_0()); }
		ruleMessageReceiveSignature
		{ after(grammarAccess.getProcessClassAccess().getReceiveMessagesMessageReceiveSignatureParserRuleCall_9_0_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__SendMessagesAssignment_9_0_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessClassAccess().getSendMessagesMessageSendSignatureParserRuleCall_9_0_1_0()); }
		ruleMessageSendSignature
		{ after(grammarAccess.getProcessClassAccess().getSendMessagesMessageSendSignatureParserRuleCall_9_0_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__ReceiveMessagesAssignment_9_1_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessClassAccess().getReceiveMessagesMessageReceiveSignatureParserRuleCall_9_1_1_0_0()); }
		ruleMessageReceiveSignature
		{ after(grammarAccess.getProcessClassAccess().getReceiveMessagesMessageReceiveSignatureParserRuleCall_9_1_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__SendMessagesAssignment_9_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessClassAccess().getSendMessagesMessageSendSignatureParserRuleCall_9_1_1_1_0()); }
		ruleMessageSendSignature
		{ after(grammarAccess.getProcessClassAccess().getSendMessagesMessageSendSignatureParserRuleCall_9_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__InstanceVariablesAssignment_11_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessClassAccess().getInstanceVariablesDeclarationParserRuleCall_11_0_0()); }
		ruleDeclaration
		{ after(grammarAccess.getProcessClassAccess().getInstanceVariablesDeclarationParserRuleCall_11_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__InstanceVariablesAssignment_11_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessClassAccess().getInstanceVariablesDeclarationParserRuleCall_11_1_1_0()); }
		ruleDeclaration
		{ after(grammarAccess.getProcessClassAccess().getInstanceVariablesDeclarationParserRuleCall_11_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__InitialMethodCallAssignment_13
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessClassAccess().getInitialMethodCallProcessMethodCallParserRuleCall_13_0()); }
		ruleProcessMethodCall
		{ after(grammarAccess.getProcessClassAccess().getInitialMethodCallProcessMethodCallParserRuleCall_13_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessClass__MethodsAssignment_15
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessClassAccess().getMethodsProcessMethodParserRuleCall_15_0()); }
		ruleProcessMethod
		{ after(grammarAccess.getProcessClassAccess().getMethodsProcessMethodParserRuleCall_15_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__AnnotationsAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessMethodAccess().getAnnotationsAnnotationParserRuleCall_0_0()); }
		ruleAnnotation
		{ after(grammarAccess.getProcessMethodAccess().getAnnotationsAnnotationParserRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__NameAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessMethodAccess().getNameIDENTIFIERParserRuleCall_1_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getProcessMethodAccess().getNameIDENTIFIERParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__InputParametersAssignment_3_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessMethodAccess().getInputParametersDeclarationParserRuleCall_3_0_0()); }
		ruleDeclaration
		{ after(grammarAccess.getProcessMethodAccess().getInputParametersDeclarationParserRuleCall_3_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__InputParametersAssignment_3_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessMethodAccess().getInputParametersDeclarationParserRuleCall_3_1_1_0()); }
		ruleDeclaration
		{ after(grammarAccess.getProcessMethodAccess().getInputParametersDeclarationParserRuleCall_3_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__OutputParametersAssignment_6_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessMethodAccess().getOutputParametersDeclarationParserRuleCall_6_0_0()); }
		ruleDeclaration
		{ after(grammarAccess.getProcessMethodAccess().getOutputParametersDeclarationParserRuleCall_6_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__OutputParametersAssignment_6_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessMethodAccess().getOutputParametersDeclarationParserRuleCall_6_1_1_0()); }
		ruleDeclaration
		{ after(grammarAccess.getProcessMethodAccess().getOutputParametersDeclarationParserRuleCall_6_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__LocalVariablesAssignment_8_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessMethodAccess().getLocalVariablesDeclarationParserRuleCall_8_1_0_0()); }
		ruleDeclaration
		{ after(grammarAccess.getProcessMethodAccess().getLocalVariablesDeclarationParserRuleCall_8_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__LocalVariablesAssignment_8_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessMethodAccess().getLocalVariablesDeclarationParserRuleCall_8_1_1_1_0()); }
		ruleDeclaration
		{ after(grammarAccess.getProcessMethodAccess().getLocalVariablesDeclarationParserRuleCall_8_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethod__BodyAssignment_9
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessMethodAccess().getBodyStatementParserRuleCall_9_0()); }
		ruleStatement
		{ after(grammarAccess.getProcessMethodAccess().getBodyStatementParserRuleCall_9_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Port__NameAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPortAccess().getNameIDENTIFIERParserRuleCall_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getPortAccess().getNameIDENTIFIERParserRuleCall_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageReceiveSignature__PortAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getMessageReceiveSignatureAccess().getPortPortReferenceParserRuleCall_0_0()); }
		rulePortReference
		{ after(grammarAccess.getMessageReceiveSignatureAccess().getPortPortReferenceParserRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageReceiveSignature__NameAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getMessageReceiveSignatureAccess().getNameIDENTIFIERParserRuleCall_2_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getMessageReceiveSignatureAccess().getNameIDENTIFIERParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageReceiveSignature__ParametersAssignment_3_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getMessageReceiveSignatureAccess().getParametersMessageParameterParserRuleCall_3_1_0_0()); }
		ruleMessageParameter
		{ after(grammarAccess.getMessageReceiveSignatureAccess().getParametersMessageParameterParserRuleCall_3_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageReceiveSignature__ParametersAssignment_3_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getMessageReceiveSignatureAccess().getParametersMessageParameterParserRuleCall_3_1_1_1_0()); }
		ruleMessageParameter
		{ after(grammarAccess.getMessageReceiveSignatureAccess().getParametersMessageParameterParserRuleCall_3_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageSendSignature__PortAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getMessageSendSignatureAccess().getPortPortReferenceParserRuleCall_0_0()); }
		rulePortReference
		{ after(grammarAccess.getMessageSendSignatureAccess().getPortPortReferenceParserRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageSendSignature__NameAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getMessageSendSignatureAccess().getNameIDENTIFIERParserRuleCall_2_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getMessageSendSignatureAccess().getNameIDENTIFIERParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageSendSignature__ParametersAssignment_3_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getMessageSendSignatureAccess().getParametersMessageParameterParserRuleCall_3_1_0_0()); }
		ruleMessageParameter
		{ after(grammarAccess.getMessageSendSignatureAccess().getParametersMessageParameterParserRuleCall_3_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageSendSignature__ParametersAssignment_3_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getMessageSendSignatureAccess().getParametersMessageParameterParserRuleCall_3_1_1_1_0()); }
		ruleMessageParameter
		{ after(grammarAccess.getMessageSendSignatureAccess().getParametersMessageParameterParserRuleCall_3_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageParameter__TypeAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getMessageParameterAccess().getTypeIDENTIFIERParserRuleCall_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getMessageParameterAccess().getTypeIDENTIFIERParserRuleCall_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__AnnotationsAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSystemAccess().getAnnotationsAnnotationParserRuleCall_1_0()); }
		ruleAnnotation
		{ after(grammarAccess.getSystemAccess().getAnnotationsAnnotationParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__ParametersAssignment_3_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSystemAccess().getParametersDeclarationParserRuleCall_3_1_0_0()); }
		ruleDeclaration
		{ after(grammarAccess.getSystemAccess().getParametersDeclarationParserRuleCall_3_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__ParametersAssignment_3_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSystemAccess().getParametersDeclarationParserRuleCall_3_1_1_1_0()); }
		ruleDeclaration
		{ after(grammarAccess.getSystemAccess().getParametersDeclarationParserRuleCall_3_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__PortsAssignment_4_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSystemAccess().getPortsPortParserRuleCall_4_1_0_0()); }
		rulePort
		{ after(grammarAccess.getSystemAccess().getPortsPortParserRuleCall_4_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__PortsAssignment_4_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSystemAccess().getPortsPortParserRuleCall_4_1_1_1_0()); }
		rulePort
		{ after(grammarAccess.getSystemAccess().getPortsPortParserRuleCall_4_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__InstancesAssignment_6
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSystemAccess().getInstancesInstanceParserRuleCall_6_0()); }
		ruleInstance
		{ after(grammarAccess.getSystemAccess().getInstancesInstanceParserRuleCall_6_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__System__ChannelsAssignment_8
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSystemAccess().getChannelsChannelParserRuleCall_8_0()); }
		ruleChannel
		{ after(grammarAccess.getSystemAccess().getChannelsChannelParserRuleCall_8_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__AnnotationsAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getClusterClassAccess().getAnnotationsAnnotationParserRuleCall_0_0()); }
		ruleAnnotation
		{ after(grammarAccess.getClusterClassAccess().getAnnotationsAnnotationParserRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__NameAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getClusterClassAccess().getNameIDENTIFIERParserRuleCall_3_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getClusterClassAccess().getNameIDENTIFIERParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__ParametersAssignment_4_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getClusterClassAccess().getParametersDeclarationParserRuleCall_4_1_0_0()); }
		ruleDeclaration
		{ after(grammarAccess.getClusterClassAccess().getParametersDeclarationParserRuleCall_4_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__ParametersAssignment_4_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getClusterClassAccess().getParametersDeclarationParserRuleCall_4_1_1_1_0()); }
		ruleDeclaration
		{ after(grammarAccess.getClusterClassAccess().getParametersDeclarationParserRuleCall_4_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__PortsAssignment_6_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getClusterClassAccess().getPortsPortParserRuleCall_6_0_0()); }
		rulePort
		{ after(grammarAccess.getClusterClassAccess().getPortsPortParserRuleCall_6_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__PortsAssignment_6_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getClusterClassAccess().getPortsPortParserRuleCall_6_1_1_0()); }
		rulePort
		{ after(grammarAccess.getClusterClassAccess().getPortsPortParserRuleCall_6_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__InstancesAssignment_8
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getClusterClassAccess().getInstancesInstanceParserRuleCall_8_0()); }
		ruleInstance
		{ after(grammarAccess.getClusterClassAccess().getInstancesInstanceParserRuleCall_8_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ClusterClass__ChannelsAssignment_10
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getClusterClassAccess().getChannelsChannelParserRuleCall_10_0()); }
		ruleChannel
		{ after(grammarAccess.getClusterClassAccess().getChannelsChannelParserRuleCall_10_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__AnnotationsAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getInstanceAccess().getAnnotationsAnnotationParserRuleCall_0_0()); }
		ruleAnnotation
		{ after(grammarAccess.getInstanceAccess().getAnnotationsAnnotationParserRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__NameAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getInstanceAccess().getNameIDENTIFIERParserRuleCall_1_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getInstanceAccess().getNameIDENTIFIERParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__ClassDefinitionAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getInstanceAccess().getClassDefinitionIDENTIFIERParserRuleCall_3_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getInstanceAccess().getClassDefinitionIDENTIFIERParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__InstanceParametersAssignment_4_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getInstanceAccess().getInstanceParametersInstanceParameterParserRuleCall_4_1_0_0()); }
		ruleInstanceParameter
		{ after(grammarAccess.getInstanceAccess().getInstanceParametersInstanceParameterParserRuleCall_4_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Instance__InstanceParametersAssignment_4_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getInstanceAccess().getInstanceParametersInstanceParameterParserRuleCall_4_1_1_1_0()); }
		ruleInstanceParameter
		{ after(grammarAccess.getInstanceAccess().getInstanceParametersInstanceParameterParserRuleCall_4_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__InstanceParameter__ParameterAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getInstanceParameterAccess().getParameterIDENTIFIERParserRuleCall_0_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getInstanceParameterAccess().getParameterIDENTIFIERParserRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__InstanceParameter__ExpressionAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getInstanceParameterAccess().getExpressionExpressionParserRuleCall_2_0()); }
		ruleExpression
		{ after(grammarAccess.getInstanceParameterAccess().getExpressionExpressionParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__AnnotationsAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getChannelAccess().getAnnotationsAnnotationParserRuleCall_1_0()); }
		ruleAnnotation
		{ after(grammarAccess.getChannelAccess().getAnnotationsAnnotationParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__InstancePortsAssignment_3_0_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getChannelAccess().getInstancePortsInstancePortParserRuleCall_3_0_0_0()); }
		ruleInstancePort
		{ after(grammarAccess.getChannelAccess().getInstancePortsInstancePortParserRuleCall_3_0_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__InstancePortsAssignment_3_0_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getChannelAccess().getInstancePortsInstancePortParserRuleCall_3_0_1_1_0()); }
		ruleInstancePort
		{ after(grammarAccess.getChannelAccess().getInstancePortsInstancePortParserRuleCall_3_0_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__ExternalPortAssignment_3_0_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getChannelAccess().getExternalPortPortCrossReference_3_0_2_1_0()); }
		(
			{ before(grammarAccess.getChannelAccess().getExternalPortPortIDENTIFIERParserRuleCall_3_0_2_1_0_1()); }
			ruleIDENTIFIER
			{ after(grammarAccess.getChannelAccess().getExternalPortPortIDENTIFIERParserRuleCall_3_0_2_1_0_1()); }
		)
		{ after(grammarAccess.getChannelAccess().getExternalPortPortCrossReference_3_0_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__InstancePortsAssignment_3_0_2_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getChannelAccess().getInstancePortsInstancePortParserRuleCall_3_0_2_2_1_0()); }
		ruleInstancePort
		{ after(grammarAccess.getChannelAccess().getInstancePortsInstancePortParserRuleCall_3_0_2_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__ExternalPortAssignment_3_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getChannelAccess().getExternalPortPortCrossReference_3_1_0_0()); }
		(
			{ before(grammarAccess.getChannelAccess().getExternalPortPortIDENTIFIERParserRuleCall_3_1_0_0_1()); }
			ruleIDENTIFIER
			{ after(grammarAccess.getChannelAccess().getExternalPortPortIDENTIFIERParserRuleCall_3_1_0_0_1()); }
		)
		{ after(grammarAccess.getChannelAccess().getExternalPortPortCrossReference_3_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Channel__InstancePortsAssignment_3_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getChannelAccess().getInstancePortsInstancePortParserRuleCall_3_1_1_1_0()); }
		ruleInstancePort
		{ after(grammarAccess.getChannelAccess().getInstancePortsInstancePortParserRuleCall_3_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__InstancePort__InstanceAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getInstancePortAccess().getInstanceInstanceCrossReference_0_0()); }
		(
			{ before(grammarAccess.getInstancePortAccess().getInstanceInstanceIDENTIFIERParserRuleCall_0_0_1()); }
			ruleIDENTIFIER
			{ after(grammarAccess.getInstancePortAccess().getInstanceInstanceIDENTIFIERParserRuleCall_0_0_1()); }
		)
		{ after(grammarAccess.getInstancePortAccess().getInstanceInstanceCrossReference_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__InstancePort__PortAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getInstancePortAccess().getPortPortReferenceParserRuleCall_2_0()); }
		rulePortReference
		{ after(grammarAccess.getInstancePortAccess().getPortPortReferenceParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Expression__ExpressionsAssignment_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionAccess().getExpressionsExpressionSingleParserRuleCall_1_1_1_0()); }
		ruleExpressionSingle
		{ after(grammarAccess.getExpressionAccess().getExpressionsExpressionSingleParserRuleCall_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel1__VariableAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionLevel1Access().getVariableIDENTIFIERParserRuleCall_1_1_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getExpressionLevel1Access().getVariableIDENTIFIERParserRuleCall_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel1__ExpressionAssignment_1_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionLevel1Access().getExpressionExpressionLevel1ParserRuleCall_1_3_0()); }
		ruleExpressionLevel1
		{ after(grammarAccess.getExpressionLevel1Access().getExpressionExpressionLevel1ParserRuleCall_1_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel1__ExpressionAssignment_2_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionLevel1Access().getExpressionExpressionLevel1ParserRuleCall_2_2_0()); }
		ruleExpressionLevel1
		{ after(grammarAccess.getExpressionLevel1Access().getExpressionExpressionLevel1ParserRuleCall_2_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel2__NameAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionLevel2Access().getNameBinaryOperatorLevel2EnumRuleCall_1_1_0()); }
		ruleBinaryOperatorLevel2
		{ after(grammarAccess.getExpressionLevel2Access().getNameBinaryOperatorLevel2EnumRuleCall_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel2__RightOperandAssignment_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionLevel2Access().getRightOperandExpressionLevel3ParserRuleCall_1_2_0()); }
		ruleExpressionLevel3
		{ after(grammarAccess.getExpressionLevel2Access().getRightOperandExpressionLevel3ParserRuleCall_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel3__NameAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionLevel3Access().getNameBinaryOperatorLevel3EnumRuleCall_1_1_0()); }
		ruleBinaryOperatorLevel3
		{ after(grammarAccess.getExpressionLevel3Access().getNameBinaryOperatorLevel3EnumRuleCall_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel3__RightOperandAssignment_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionLevel3Access().getRightOperandExpressionLevel4ParserRuleCall_1_2_0()); }
		ruleExpressionLevel4
		{ after(grammarAccess.getExpressionLevel3Access().getRightOperandExpressionLevel4ParserRuleCall_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel4__NameAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionLevel4Access().getNameBinaryOperatorLevel4EnumRuleCall_1_1_0()); }
		ruleBinaryOperatorLevel4
		{ after(grammarAccess.getExpressionLevel4Access().getNameBinaryOperatorLevel4EnumRuleCall_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel4__RightOperandAssignment_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionLevel4Access().getRightOperandExpressionLevel5ParserRuleCall_1_2_0()); }
		ruleExpressionLevel5
		{ after(grammarAccess.getExpressionLevel4Access().getRightOperandExpressionLevel5ParserRuleCall_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__OnSuperClassAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionLevel5Access().getOnSuperClassCircumflexAccentKeyword_1_1_0()); }
		(
			{ before(grammarAccess.getExpressionLevel5Access().getOnSuperClassCircumflexAccentKeyword_1_1_0()); }
			'^'
			{ after(grammarAccess.getExpressionLevel5Access().getOnSuperClassCircumflexAccentKeyword_1_1_0()); }
		)
		{ after(grammarAccess.getExpressionLevel5Access().getOnSuperClassCircumflexAccentKeyword_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__NameAssignment_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionLevel5Access().getNameIDENTIFIERParserRuleCall_1_2_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getExpressionLevel5Access().getNameIDENTIFIERParserRuleCall_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__ArgumentsAssignment_1_3_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_0_0()); }
		ruleExpression
		{ after(grammarAccess.getExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionLevel5__ArgumentsAssignment_1_3_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_1_1_0()); }
		ruleExpression
		{ after(grammarAccess.getExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__UnaryOperatorExpression__NameAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getUnaryOperatorExpressionAccess().getNameOperatorUnaryEnumRuleCall_0_0()); }
		ruleOperatorUnary
		{ after(grammarAccess.getUnaryOperatorExpressionAccess().getNameOperatorUnaryEnumRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__UnaryOperatorExpression__OperandAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getUnaryOperatorExpressionAccess().getOperandExpressionLevel7ParserRuleCall_1_0()); }
		ruleExpressionLevel7
		{ after(grammarAccess.getUnaryOperatorExpressionAccess().getOperandExpressionLevel7ParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IfExpression__ConditionAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIfExpressionAccess().getConditionExpressionParserRuleCall_1_0()); }
		ruleExpression
		{ after(grammarAccess.getIfExpressionAccess().getConditionExpressionParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IfExpression__ThenClauseAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIfExpressionAccess().getThenClauseExpressionParserRuleCall_3_0()); }
		ruleExpression
		{ after(grammarAccess.getIfExpressionAccess().getThenClauseExpressionParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IfExpression__ElseClauseAssignment_4_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIfExpressionAccess().getElseClauseExpressionParserRuleCall_4_1_0()); }
		ruleExpression
		{ after(grammarAccess.getIfExpressionAccess().getElseClauseExpressionParserRuleCall_4_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__WhileExpression__ConditionAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getWhileExpressionAccess().getConditionExpressionParserRuleCall_1_0()); }
		ruleExpression
		{ after(grammarAccess.getWhileExpressionAccess().getConditionExpressionParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__WhileExpression__BodyAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getWhileExpressionAccess().getBodyExpressionParserRuleCall_3_0()); }
		ruleExpression
		{ after(grammarAccess.getWhileExpressionAccess().getBodyExpressionParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__ExpressionAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSwitchExpressionAccess().getExpressionExpressionParserRuleCall_1_0()); }
		ruleExpression
		{ after(grammarAccess.getSwitchExpressionAccess().getExpressionExpressionParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__CasesAssignment_3_0_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSwitchExpressionAccess().getCasesSwitchExpressionCaseParserRuleCall_3_0_0_0()); }
		ruleSwitchExpressionCase
		{ after(grammarAccess.getSwitchExpressionAccess().getCasesSwitchExpressionCaseParserRuleCall_3_0_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__DefaultBodyAssignment_3_0_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSwitchExpressionAccess().getDefaultBodyExpressionParserRuleCall_3_0_1_1_0()); }
		ruleExpression
		{ after(grammarAccess.getSwitchExpressionAccess().getDefaultBodyExpressionParserRuleCall_3_0_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpression__DefaultBodyAssignment_3_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSwitchExpressionAccess().getDefaultBodyExpressionParserRuleCall_3_1_1_0()); }
		ruleExpression
		{ after(grammarAccess.getSwitchExpressionAccess().getDefaultBodyExpressionParserRuleCall_3_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpressionCase__ValueAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSwitchExpressionCaseAccess().getValueExpressionParserRuleCall_1_0()); }
		ruleExpression
		{ after(grammarAccess.getSwitchExpressionCaseAccess().getValueExpressionParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchExpressionCase__BodyAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSwitchExpressionCaseAccess().getBodyExpressionParserRuleCall_3_0()); }
		ruleExpression
		{ after(grammarAccess.getSwitchExpressionCaseAccess().getBodyExpressionParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanConstant__ValueAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBooleanConstantAccess().getValueTrueKeyword_0_0()); }
		(
			{ before(grammarAccess.getBooleanConstantAccess().getValueTrueKeyword_0_0()); }
			'true'
			{ after(grammarAccess.getBooleanConstantAccess().getValueTrueKeyword_0_0()); }
		)
		{ after(grammarAccess.getBooleanConstantAccess().getValueTrueKeyword_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanConstant__ValueAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBooleanConstantAccess().getValueFalseKeyword_1_0()); }
		(
			{ before(grammarAccess.getBooleanConstantAccess().getValueFalseKeyword_1_0()); }
			'false'
			{ after(grammarAccess.getBooleanConstantAccess().getValueFalseKeyword_1_0()); }
		)
		{ after(grammarAccess.getBooleanConstantAccess().getValueFalseKeyword_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__CharacterConstant__ValueAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getCharacterConstantAccess().getValueCHARACTERTerminalRuleCall_0()); }
		RULE_CHARACTER
		{ after(grammarAccess.getCharacterConstantAccess().getValueCHARACTERTerminalRuleCall_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatConstant__ValueAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getFloatConstantAccess().getValueFLOATParserRuleCall_0()); }
		ruleFLOAT
		{ after(grammarAccess.getFloatConstantAccess().getValueFLOATParserRuleCall_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IntegerConstant__ValueAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIntegerConstantAccess().getValueINTEGERParserRuleCall_0()); }
		ruleINTEGER
		{ after(grammarAccess.getIntegerConstantAccess().getValueINTEGERParserRuleCall_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__RealConstant__ValueAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getRealConstantAccess().getValueREALParserRuleCall_0()); }
		ruleREAL
		{ after(grammarAccess.getRealConstantAccess().getValueREALParserRuleCall_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StringConstant__ValueAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStringConstantAccess().getValuePOOSL_STRINGTerminalRuleCall_0()); }
		RULE_POOSL_STRING
		{ after(grammarAccess.getStringConstantAccess().getValuePOOSL_STRINGTerminalRuleCall_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnvironmentConstant__ValueAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEnvironmentConstantAccess().getValueENVIRONMENT_VARIABLE_NAMETerminalRuleCall_0()); }
		RULE_ENVIRONMENT_VARIABLE_NAME
		{ after(grammarAccess.getEnvironmentConstantAccess().getValueENVIRONMENT_VARIABLE_NAMETerminalRuleCall_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__OutputVariable__VariableAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getOutputVariableAccess().getVariableIDENTIFIERParserRuleCall_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getOutputVariableAccess().getVariableIDENTIFIERParserRuleCall_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__VariableExpression__VariableAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getVariableExpressionAccess().getVariableIDENTIFIERParserRuleCall_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getVariableExpressionAccess().getVariableIDENTIFIERParserRuleCall_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__NewExpression__DataClassAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getNewExpressionAccess().getDataClassIDENTIFIERParserRuleCall_2_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getNewExpressionAccess().getDataClassIDENTIFIERParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionSequenceRoundBracket__ExpressionsAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionSequenceRoundBracketAccess().getExpressionsExpressionSingleParserRuleCall_1_0()); }
		ruleExpressionSingle
		{ after(grammarAccess.getExpressionSequenceRoundBracketAccess().getExpressionsExpressionSingleParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionSequenceRoundBracket__ExpressionsAssignment_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionSequenceRoundBracketAccess().getExpressionsExpressionSingleParserRuleCall_2_1_0()); }
		ruleExpressionSingle
		{ after(grammarAccess.getExpressionSequenceRoundBracketAccess().getExpressionsExpressionSingleParserRuleCall_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel1__VariableAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getVariableIDENTIFIERParserRuleCall_1_1_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getVariableIDENTIFIERParserRuleCall_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel1__ExpressionAssignment_1_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getExpressionExpressionLevel1ParserRuleCall_1_3_0()); }
		ruleExpressionLevel1
		{ after(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getExpressionExpressionLevel1ParserRuleCall_1_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel2__NameAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getNameBinaryOperatorLevel2EnumRuleCall_1_1_0()); }
		ruleBinaryOperatorLevel2
		{ after(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getNameBinaryOperatorLevel2EnumRuleCall_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel2__RightOperandAssignment_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getRightOperandExpressionLevel3ParserRuleCall_1_2_0()); }
		ruleExpressionLevel3
		{ after(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getRightOperandExpressionLevel3ParserRuleCall_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel3__NameAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getNameBinaryOperatorLevel3EnumRuleCall_1_1_0()); }
		ruleBinaryOperatorLevel3
		{ after(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getNameBinaryOperatorLevel3EnumRuleCall_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel3__RightOperandAssignment_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getRightOperandExpressionLevel4ParserRuleCall_1_2_0()); }
		ruleExpressionLevel4
		{ after(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getRightOperandExpressionLevel4ParserRuleCall_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel4__NameAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getNameBinaryOperatorLevel4EnumRuleCall_1_1_0()); }
		ruleBinaryOperatorLevel4
		{ after(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getNameBinaryOperatorLevel4EnumRuleCall_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel4__RightOperandAssignment_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getRightOperandExpressionLevel5ParserRuleCall_1_2_0()); }
		ruleExpressionLevel5
		{ after(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getRightOperandExpressionLevel5ParserRuleCall_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__OnSuperClassAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getOnSuperClassCircumflexAccentKeyword_1_1_0()); }
		(
			{ before(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getOnSuperClassCircumflexAccentKeyword_1_1_0()); }
			'^'
			{ after(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getOnSuperClassCircumflexAccentKeyword_1_1_0()); }
		)
		{ after(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getOnSuperClassCircumflexAccentKeyword_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__NameAssignment_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getNameIDENTIFIERParserRuleCall_1_2_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getNameIDENTIFIERParserRuleCall_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__ArgumentsAssignment_1_3_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_0_0()); }
		ruleExpression
		{ after(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IDStartWithinStatementExpressionLevel5__ArgumentsAssignment_1_3_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_1_1_0()); }
		ruleExpression
		{ after(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel1__ExpressionAssignment_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Access().getExpressionExpressionLevel1ParserRuleCall_1_2_0()); }
		ruleExpressionLevel1
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Access().getExpressionExpressionLevel1ParserRuleCall_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel2__NameAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getNameBinaryOperatorLevel2EnumRuleCall_1_1_0()); }
		ruleBinaryOperatorLevel2
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getNameBinaryOperatorLevel2EnumRuleCall_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel2__RightOperandAssignment_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getRightOperandExpressionLevel3ParserRuleCall_1_2_0()); }
		ruleExpressionLevel3
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getRightOperandExpressionLevel3ParserRuleCall_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel3__NameAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getNameBinaryOperatorLevel3EnumRuleCall_1_1_0()); }
		ruleBinaryOperatorLevel3
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getNameBinaryOperatorLevel3EnumRuleCall_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel3__RightOperandAssignment_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getRightOperandExpressionLevel4ParserRuleCall_1_2_0()); }
		ruleExpressionLevel4
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getRightOperandExpressionLevel4ParserRuleCall_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel4__NameAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getNameBinaryOperatorLevel4EnumRuleCall_1_1_0()); }
		ruleBinaryOperatorLevel4
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getNameBinaryOperatorLevel4EnumRuleCall_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel4__RightOperandAssignment_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getRightOperandExpressionLevel5ParserRuleCall_1_2_0()); }
		ruleExpressionLevel5
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getRightOperandExpressionLevel5ParserRuleCall_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__OnSuperClassAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getOnSuperClassCircumflexAccentKeyword_1_1_0()); }
		(
			{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getOnSuperClassCircumflexAccentKeyword_1_1_0()); }
			'^'
			{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getOnSuperClassCircumflexAccentKeyword_1_1_0()); }
		)
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getOnSuperClassCircumflexAccentKeyword_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__NameAssignment_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getNameIDENTIFIERParserRuleCall_1_2_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getNameIDENTIFIERParserRuleCall_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__ArgumentsAssignment_1_3_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_0_0()); }
		ruleExpression
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__NonIDStartWithinStatementExpressionLevel5__ArgumentsAssignment_1_3_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_1_1_0()); }
		ruleExpression
		{ after(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Statement__StatementsAssignment_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStatementAccess().getStatementsStatementSingleParserRuleCall_1_1_1_0()); }
		ruleStatementSingle
		{ after(grammarAccess.getStatementAccess().getStatementsStatementSingleParserRuleCall_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionStatement__ExpressionAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionStatementAccess().getExpressionIDStartWithinStatementExpressionSingleParserRuleCall_0_0()); }
		ruleIDStartWithinStatementExpressionSingle
		{ after(grammarAccess.getExpressionStatementAccess().getExpressionIDStartWithinStatementExpressionSingleParserRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionStatement__ExpressionAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionStatementAccess().getExpressionNonIDStartWithinStatementExpressionSingleParserRuleCall_1_0()); }
		ruleNonIDStartWithinStatementExpressionSingle
		{ after(grammarAccess.getExpressionStatementAccess().getExpressionNonIDStartWithinStatementExpressionSingleParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ExpressionStatement__ExpressionAssignment_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getExpressionStatementAccess().getExpressionExpressionParserRuleCall_2_1_0()); }
		ruleExpression
		{ after(grammarAccess.getExpressionStatementAccess().getExpressionExpressionParserRuleCall_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DelayStatement__ExpressionAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDelayStatementAccess().getExpressionExpressionSingleParserRuleCall_1_0()); }
		ruleExpressionSingle
		{ after(grammarAccess.getDelayStatementAccess().getExpressionExpressionSingleParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__PortDescriptorAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getReceiveStatementAccess().getPortDescriptorPortDescriptorParserRuleCall_0_0()); }
		rulePortDescriptor
		{ after(grammarAccess.getReceiveStatementAccess().getPortDescriptorPortDescriptorParserRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__NameAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getReceiveStatementAccess().getNameIDENTIFIERParserRuleCall_2_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getReceiveStatementAccess().getNameIDENTIFIERParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__VariablesAssignment_3_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getReceiveStatementAccess().getVariablesOutputVariableParserRuleCall_3_1_0_0()); }
		ruleOutputVariable
		{ after(grammarAccess.getReceiveStatementAccess().getVariablesOutputVariableParserRuleCall_3_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__VariablesAssignment_3_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getReceiveStatementAccess().getVariablesOutputVariableParserRuleCall_3_1_1_1_0()); }
		ruleOutputVariable
		{ after(grammarAccess.getReceiveStatementAccess().getVariablesOutputVariableParserRuleCall_3_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__ReceptionConditionAssignment_3_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getReceiveStatementAccess().getReceptionConditionExpressionParserRuleCall_3_2_1_0()); }
		ruleExpression
		{ after(grammarAccess.getReceiveStatementAccess().getReceptionConditionExpressionParserRuleCall_3_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReceiveStatement__PostCommunicationExpressionAssignment_4_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getReceiveStatementAccess().getPostCommunicationExpressionExpressionParserRuleCall_4_1_0()); }
		ruleExpression
		{ after(grammarAccess.getReceiveStatementAccess().getPostCommunicationExpressionExpressionParserRuleCall_4_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__PortDescriptorAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSendStatementAccess().getPortDescriptorPortDescriptorParserRuleCall_0_0()); }
		rulePortDescriptor
		{ after(grammarAccess.getSendStatementAccess().getPortDescriptorPortDescriptorParserRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__NameAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSendStatementAccess().getNameIDENTIFIERParserRuleCall_2_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getSendStatementAccess().getNameIDENTIFIERParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__ArgumentsAssignment_3_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSendStatementAccess().getArgumentsExpressionParserRuleCall_3_1_0_0()); }
		ruleExpression
		{ after(grammarAccess.getSendStatementAccess().getArgumentsExpressionParserRuleCall_3_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__ArgumentsAssignment_3_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSendStatementAccess().getArgumentsExpressionParserRuleCall_3_1_1_1_0()); }
		ruleExpression
		{ after(grammarAccess.getSendStatementAccess().getArgumentsExpressionParserRuleCall_3_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SendStatement__PostCommunicationExpressionAssignment_4_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSendStatementAccess().getPostCommunicationExpressionExpressionParserRuleCall_4_1_0()); }
		ruleExpression
		{ after(grammarAccess.getSendStatementAccess().getPostCommunicationExpressionExpressionParserRuleCall_4_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__PortDescriptor__PortAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPortDescriptorAccess().getPortIDENTIFIERParserRuleCall_1_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getPortDescriptorAccess().getPortIDENTIFIERParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__PortReference__PortAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPortReferenceAccess().getPortIDENTIFIERParserRuleCall_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getPortReferenceAccess().getPortIDENTIFIERParserRuleCall_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__GuardedStatement__GuardAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGuardedStatementAccess().getGuardExpressionParserRuleCall_1_0()); }
		ruleExpression
		{ after(grammarAccess.getGuardedStatementAccess().getGuardExpressionParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__GuardedStatement__StatementAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGuardedStatementAccess().getStatementStatementSingleParserRuleCall_3_0()); }
		ruleStatementSingle
		{ after(grammarAccess.getGuardedStatementAccess().getStatementStatementSingleParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IfStatement__ConditionAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIfStatementAccess().getConditionExpressionParserRuleCall_1_0()); }
		ruleExpression
		{ after(grammarAccess.getIfStatementAccess().getConditionExpressionParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IfStatement__ThenClauseAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIfStatementAccess().getThenClauseStatementParserRuleCall_3_0()); }
		ruleStatement
		{ after(grammarAccess.getIfStatementAccess().getThenClauseStatementParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IfStatement__ElseClauseAssignment_4_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIfStatementAccess().getElseClauseStatementParserRuleCall_4_1_0()); }
		ruleStatement
		{ after(grammarAccess.getIfStatementAccess().getElseClauseStatementParserRuleCall_4_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__WhileStatement__ConditionAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getWhileStatementAccess().getConditionExpressionParserRuleCall_1_0()); }
		ruleExpression
		{ after(grammarAccess.getWhileStatementAccess().getConditionExpressionParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__WhileStatement__BodyAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getWhileStatementAccess().getBodyStatementParserRuleCall_3_0()); }
		ruleStatement
		{ after(grammarAccess.getWhileStatementAccess().getBodyStatementParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__ExpressionAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSwitchStatementAccess().getExpressionExpressionParserRuleCall_1_0()); }
		ruleExpression
		{ after(grammarAccess.getSwitchStatementAccess().getExpressionExpressionParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__CasesAssignment_3_0_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSwitchStatementAccess().getCasesSwitchStatementCaseParserRuleCall_3_0_0_0()); }
		ruleSwitchStatementCase
		{ after(grammarAccess.getSwitchStatementAccess().getCasesSwitchStatementCaseParserRuleCall_3_0_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__DefaultBodyAssignment_3_0_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSwitchStatementAccess().getDefaultBodyStatementParserRuleCall_3_0_1_1_0()); }
		ruleStatement
		{ after(grammarAccess.getSwitchStatementAccess().getDefaultBodyStatementParserRuleCall_3_0_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatement__DefaultBodyAssignment_3_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSwitchStatementAccess().getDefaultBodyStatementParserRuleCall_3_1_1_0()); }
		ruleStatement
		{ after(grammarAccess.getSwitchStatementAccess().getDefaultBodyStatementParserRuleCall_3_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatementCase__ValueAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSwitchStatementCaseAccess().getValueExpressionParserRuleCall_1_0()); }
		ruleExpression
		{ after(grammarAccess.getSwitchStatementCaseAccess().getValueExpressionParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SwitchStatementCase__BodyAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSwitchStatementCaseAccess().getBodyStatementParserRuleCall_3_0()); }
		ruleStatement
		{ after(grammarAccess.getSwitchStatementCaseAccess().getBodyStatementParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ParStatement__ClausesAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getParStatementAccess().getClausesStatementParserRuleCall_1_0()); }
		ruleStatement
		{ after(grammarAccess.getParStatementAccess().getClausesStatementParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ParStatement__ClausesAssignment_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getParStatementAccess().getClausesStatementParserRuleCall_2_1_0()); }
		ruleStatement
		{ after(grammarAccess.getParStatementAccess().getClausesStatementParserRuleCall_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SelStatement__ClausesAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSelStatementAccess().getClausesStatementParserRuleCall_1_0()); }
		ruleStatement
		{ after(grammarAccess.getSelStatementAccess().getClausesStatementParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SelStatement__ClausesAssignment_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSelStatementAccess().getClausesStatementParserRuleCall_2_1_0()); }
		ruleStatement
		{ after(grammarAccess.getSelStatementAccess().getClausesStatementParserRuleCall_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__AbortStatement__NormalClauseAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getAbortStatementAccess().getNormalClauseStatementParserRuleCall_1_0()); }
		ruleStatement
		{ after(grammarAccess.getAbortStatementAccess().getNormalClauseStatementParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__AbortStatement__AbortingClauseAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getAbortStatementAccess().getAbortingClauseStatementSingleParserRuleCall_3_0()); }
		ruleStatementSingle
		{ after(grammarAccess.getAbortStatementAccess().getAbortingClauseStatementSingleParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__InterruptStatement__NormalClauseAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getInterruptStatementAccess().getNormalClauseStatementParserRuleCall_1_0()); }
		ruleStatement
		{ after(grammarAccess.getInterruptStatementAccess().getNormalClauseStatementParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__InterruptStatement__InterruptingClauseAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getInterruptStatementAccess().getInterruptingClauseStatementSingleParserRuleCall_3_0()); }
		ruleStatementSingle
		{ after(grammarAccess.getInterruptStatementAccess().getInterruptingClauseStatementSingleParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__MethodAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessMethodCallAccess().getMethodIDENTIFIERParserRuleCall_0_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getProcessMethodCallAccess().getMethodIDENTIFIERParserRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__InputArgumentsAssignment_2_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessMethodCallAccess().getInputArgumentsExpressionParserRuleCall_2_0_0()); }
		ruleExpression
		{ after(grammarAccess.getProcessMethodCallAccess().getInputArgumentsExpressionParserRuleCall_2_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__InputArgumentsAssignment_2_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessMethodCallAccess().getInputArgumentsExpressionParserRuleCall_2_1_1_0()); }
		ruleExpression
		{ after(grammarAccess.getProcessMethodCallAccess().getInputArgumentsExpressionParserRuleCall_2_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__OutputVariablesAssignment_5_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessMethodCallAccess().getOutputVariablesOutputVariableParserRuleCall_5_0_0()); }
		ruleOutputVariable
		{ after(grammarAccess.getProcessMethodCallAccess().getOutputVariablesOutputVariableParserRuleCall_5_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ProcessMethodCall__OutputVariablesAssignment_5_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getProcessMethodCallAccess().getOutputVariablesOutputVariableParserRuleCall_5_1_1_0()); }
		ruleOutputVariable
		{ after(grammarAccess.getProcessMethodCallAccess().getOutputVariablesOutputVariableParserRuleCall_5_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StatementSequenceRoundBracket__StatementsAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStatementSequenceRoundBracketAccess().getStatementsStatementSingleParserRuleCall_1_0()); }
		ruleStatementSingle
		{ after(grammarAccess.getStatementSequenceRoundBracketAccess().getStatementsStatementSingleParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StatementSequenceRoundBracket__StatementsAssignment_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStatementSequenceRoundBracketAccess().getStatementsStatementSingleParserRuleCall_2_1_0()); }
		ruleStatementSingle
		{ after(grammarAccess.getStatementSequenceRoundBracketAccess().getStatementsStatementSingleParserRuleCall_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionStatement__ExpressionAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionStatementAccess().getExpressionBracketedArgumentStartExpressionSingleParserRuleCall_0()); }
		ruleBracketedArgumentStartExpressionSingle
		{ after(grammarAccess.getBracketedArgumentStartExpressionStatementAccess().getExpressionBracketedArgumentStartExpressionSingleParserRuleCall_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel2__NameAssignment_0_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getNameBinaryOperatorLevel2EnumRuleCall_0_1_1_0()); }
		ruleBinaryOperatorLevel2
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getNameBinaryOperatorLevel2EnumRuleCall_0_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel2__RightOperandAssignment_0_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getRightOperandExpressionLevel3ParserRuleCall_0_1_2_0()); }
		ruleExpressionLevel3
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getRightOperandExpressionLevel3ParserRuleCall_0_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel2__NameAssignment_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getNameBinaryOperatorLevel2EnumRuleCall_1_1_1_0()); }
		ruleBinaryOperatorLevel2
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getNameBinaryOperatorLevel2EnumRuleCall_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel2__RightOperandAssignment_1_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getRightOperandExpressionLevel3ParserRuleCall_1_1_2_0()); }
		ruleExpressionLevel3
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getRightOperandExpressionLevel3ParserRuleCall_1_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel3__NameAssignment_0_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getNameBinaryOperatorLevel3EnumRuleCall_0_1_1_0()); }
		ruleBinaryOperatorLevel3
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getNameBinaryOperatorLevel3EnumRuleCall_0_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel3__RightOperandAssignment_0_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getRightOperandExpressionLevel4ParserRuleCall_0_1_2_0()); }
		ruleExpressionLevel4
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getRightOperandExpressionLevel4ParserRuleCall_0_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel3__NameAssignment_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getNameBinaryOperatorLevel3EnumRuleCall_1_1_1_0()); }
		ruleBinaryOperatorLevel3
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getNameBinaryOperatorLevel3EnumRuleCall_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel3__RightOperandAssignment_1_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getRightOperandExpressionLevel4ParserRuleCall_1_1_2_0()); }
		ruleExpressionLevel4
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getRightOperandExpressionLevel4ParserRuleCall_1_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel4__NameAssignment_0_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getNameBinaryOperatorLevel4EnumRuleCall_0_1_1_0()); }
		ruleBinaryOperatorLevel4
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getNameBinaryOperatorLevel4EnumRuleCall_0_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel4__RightOperandAssignment_0_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getRightOperandExpressionLevel5ParserRuleCall_0_1_2_0()); }
		ruleExpressionLevel5
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getRightOperandExpressionLevel5ParserRuleCall_0_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel4__NameAssignment_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getNameBinaryOperatorLevel4EnumRuleCall_1_1_1_0()); }
		ruleBinaryOperatorLevel4
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getNameBinaryOperatorLevel4EnumRuleCall_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel4__RightOperandAssignment_1_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getRightOperandExpressionLevel5ParserRuleCall_1_1_2_0()); }
		ruleExpressionLevel5
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getRightOperandExpressionLevel5ParserRuleCall_1_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__OnSuperClassAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getOnSuperClassCircumflexAccentKeyword_1_1_0()); }
		(
			{ before(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getOnSuperClassCircumflexAccentKeyword_1_1_0()); }
			'^'
			{ after(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getOnSuperClassCircumflexAccentKeyword_1_1_0()); }
		)
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getOnSuperClassCircumflexAccentKeyword_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__NameAssignment_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getNameIDENTIFIERParserRuleCall_1_2_0()); }
		ruleIDENTIFIER
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getNameIDENTIFIERParserRuleCall_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__ArgumentsAssignment_1_3_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_0_0()); }
		ruleExpression
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BracketedArgumentStartExpressionLevel5__ArgumentsAssignment_1_3_1_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_1_1_0()); }
		ruleExpression
		{ after(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

fragment RULE_MLC_ANY : ~(('*'|'/'));

fragment RULE_MLC_SLASH : '/'+ ('*' RULE_MLC_BODY|RULE_MLC_ANY);

fragment RULE_MLC_STAR : '*'+ RULE_MLC_ANY;

fragment RULE_MLC_BODY : (RULE_MLC_ANY|RULE_MLC_SLASH|RULE_MLC_STAR)* '*'+ '/';

RULE_ML_COMMENT : '/*' RULE_MLC_BODY;

RULE_BINARY_CORE : '0' ('b'|'B') ('0'..'1')+;

RULE_HEXADECIMAL_CORE : '0' ('x'|'X') ('0'..'9'|'a'..'f'|'A'..'F')+;

fragment RULE_DIGITS : ('0'..'9'|'1'..'9' ('0'..'9')+);

RULE_DECIMAL_CORE : RULE_DIGITS (('e'|'E') '+'? ('0'..'9')+)?;

RULE_REAL_CORE : (RULE_DIGITS '.' ('0'..'9')*|'.' ('0'..'9')+) (('e'|'E') ('+'|'-')? ('0'..'9')+)?;

RULE_FLOAT_CORE : RULE_REAL_CORE ('f'|'F');

RULE_IDENTIFIER_CORE : ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

RULE_CHARACTER : '\'' RULE_CHARACTER_ELEMENT '\'';

RULE_POOSL_STRING : '"' RULE_POOSL_STRING_ELEMENT* '"';

RULE_ENVIRONMENT_VARIABLE_NAME : '${' ('0'..'9'|'a'..'z'|'A'..'Z'|'_')+ '}';

fragment RULE_CHARACTER_ELEMENT : ('\t'|' '..'&'|'('..'['|']'..'\u00FF'|RULE_ESCAPE_SEQUENCE|RULE_ESCAPE_ZERO);

fragment RULE_POOSL_STRING_ELEMENT : ('\t'|' '..'!'|'#'..'['|']'..'\u00FF'|RULE_ESCAPE_SEQUENCE);

fragment RULE_ESCAPE_SEQUENCE : ('\\x' '0' ('1'..'9'|'a'..'f'|'A'..'F')|'\\x' ('1'..'9'|'a'..'f'|'A'..'F') ('0'..'9'|'a'..'f'|'A'..'F')?|'\\n'|'\\t'|'\\v'|'\\b'|'\\r'|'\\f'|'\\a'|'\\\\'|'\\?'|'\\\''|'\\"');

fragment RULE_ESCAPE_ZERO : '\\x' '0' '0'?;
