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
	superClass=AbstractInternalAntlrParser;
}

@lexer::header {
package org.eclipse.poosl.xtext.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package org.eclipse.poosl.xtext.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.poosl.xtext.services.PooslGrammarAccess;

}

@parser::members {

 	private PooslGrammarAccess grammarAccess;

    public InternalPooslParser(TokenStream input, PooslGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }

    @Override
    protected String getFirstRuleName() {
    	return "Poosl";
   	}

   	@Override
   	protected PooslGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}

}

@rulecatch {
    catch (RecognitionException re) {
        recover(input,re);
        appendSkippedTokens();
    }
}

// Entry rule entryRulePoosl
entryRulePoosl returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getPooslRule()); }
	iv_rulePoosl=rulePoosl
	{ $current=$iv_rulePoosl.current; }
	EOF;

// Rule Poosl
rulePoosl returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getPooslAccess().getPooslAction_0(),
					$current);
			}
		)
		(
			(
				(
					{
						newCompositeNode(grammarAccess.getPooslAccess().getImportsImportParserRuleCall_1_0_0());
					}
					lv_imports_1_0=ruleImport
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getPooslRule());
						}
						add(
							$current,
							"imports",
							lv_imports_1_0,
							"org.eclipse.poosl.xtext.Poosl.Import");
						afterParserOrEnumRuleCall();
					}
				)
			)
			    |
			(
				(
					{
						newCompositeNode(grammarAccess.getPooslAccess().getImportLibsImportLibParserRuleCall_1_1_0());
					}
					lv_importLibs_2_0=ruleImportLib
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getPooslRule());
						}
						add(
							$current,
							"importLibs",
							lv_importLibs_2_0,
							"org.eclipse.poosl.xtext.Poosl.ImportLib");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
		(
			(
				(
					{
						newCompositeNode(grammarAccess.getPooslAccess().getDataClassesDataClassParserRuleCall_2_0_0());
					}
					lv_dataClasses_3_0=ruleDataClass
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getPooslRule());
						}
						add(
							$current,
							"dataClasses",
							lv_dataClasses_3_0,
							"org.eclipse.poosl.xtext.Poosl.DataClass");
						afterParserOrEnumRuleCall();
					}
				)
			)
			    |
			(
				(
					{
						newCompositeNode(grammarAccess.getPooslAccess().getProcessClassesProcessClassParserRuleCall_2_1_0());
					}
					lv_processClasses_4_0=ruleProcessClass
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getPooslRule());
						}
						add(
							$current,
							"processClasses",
							lv_processClasses_4_0,
							"org.eclipse.poosl.xtext.Poosl.ProcessClass");
						afterParserOrEnumRuleCall();
					}
				)
			)
			    |
			(
				(
					{
						newCompositeNode(grammarAccess.getPooslAccess().getClusterClassesClusterClassParserRuleCall_2_2_0());
					}
					lv_clusterClasses_5_0=ruleClusterClass
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getPooslRule());
						}
						add(
							$current,
							"clusterClasses",
							lv_clusterClasses_5_0,
							"org.eclipse.poosl.xtext.Poosl.ClusterClass");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
		(
			(
				(
					{
						newCompositeNode(grammarAccess.getPooslAccess().getClusterClassesSystemParserRuleCall_3_0_0());
					}
					lv_clusterClasses_6_0=ruleSystem
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getPooslRule());
						}
						add(
							$current,
							"clusterClasses",
							lv_clusterClasses_6_0,
							"org.eclipse.poosl.xtext.Poosl.System");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getPooslAccess().getDataClassesDataClassParserRuleCall_3_1_0_0());
						}
						lv_dataClasses_7_0=ruleDataClass
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getPooslRule());
							}
							add(
								$current,
								"dataClasses",
								lv_dataClasses_7_0,
								"org.eclipse.poosl.xtext.Poosl.DataClass");
							afterParserOrEnumRuleCall();
						}
					)
				)
				    |
				(
					(
						{
							newCompositeNode(grammarAccess.getPooslAccess().getProcessClassesProcessClassParserRuleCall_3_1_1_0());
						}
						lv_processClasses_8_0=ruleProcessClass
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getPooslRule());
							}
							add(
								$current,
								"processClasses",
								lv_processClasses_8_0,
								"org.eclipse.poosl.xtext.Poosl.ProcessClass");
							afterParserOrEnumRuleCall();
						}
					)
				)
				    |
				(
					(
						{
							newCompositeNode(grammarAccess.getPooslAccess().getClusterClassesClusterClassParserRuleCall_3_1_2_0());
						}
						lv_clusterClasses_9_0=ruleClusterClass
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getPooslRule());
							}
							add(
								$current,
								"clusterClasses",
								lv_clusterClasses_9_0,
								"org.eclipse.poosl.xtext.Poosl.ClusterClass");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)*
		)?
	)
;

// Entry rule entryRuleImport
entryRuleImport returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getImportRule()); }
	iv_ruleImport=ruleImport
	{ $current=$iv_ruleImport.current; }
	EOF;

// Rule Import
ruleImport returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='import'
		{
			newLeafNode(otherlv_0, grammarAccess.getImportAccess().getImportKeyword_0());
		}
		(
			(
				lv_importURI_1_0=RULE_POOSL_STRING
				{
					newLeafNode(lv_importURI_1_0, grammarAccess.getImportAccess().getImportURIPOOSL_STRINGTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getImportRule());
					}
					setWithLastConsumed(
						$current,
						"importURI",
						lv_importURI_1_0,
						"org.eclipse.poosl.xtext.Poosl.POOSL_STRING");
				}
			)
		)
	)
;

// Entry rule entryRuleImportLib
entryRuleImportLib returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getImportLibRule()); }
	iv_ruleImportLib=ruleImportLib
	{ $current=$iv_ruleImportLib.current; }
	EOF;

// Rule ImportLib
ruleImportLib returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='importlib'
		{
			newLeafNode(otherlv_0, grammarAccess.getImportLibAccess().getImportlibKeyword_0());
		}
		(
			(
				lv_importURI_1_0=RULE_POOSL_STRING
				{
					newLeafNode(lv_importURI_1_0, grammarAccess.getImportLibAccess().getImportURIPOOSL_STRINGTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getImportLibRule());
					}
					setWithLastConsumed(
						$current,
						"importURI",
						lv_importURI_1_0,
						"org.eclipse.poosl.xtext.Poosl.POOSL_STRING");
				}
			)
		)
	)
;

// Entry rule entryRuleDataClass
entryRuleDataClass returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getDataClassRule()); }
	iv_ruleDataClass=ruleDataClass
	{ $current=$iv_ruleDataClass.current; }
	EOF;

// Rule DataClass
ruleDataClass returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					newCompositeNode(grammarAccess.getDataClassAccess().getAnnotationsAnnotationParserRuleCall_0_0());
				}
				lv_annotations_0_0=ruleAnnotation
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getDataClassRule());
					}
					add(
						$current,
						"annotations",
						lv_annotations_0_0,
						"org.eclipse.poosl.xtext.Poosl.Annotation");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		(
			(
				lv_native_1_0='native'
				{
					newLeafNode(lv_native_1_0, grammarAccess.getDataClassAccess().getNativeNativeKeyword_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getDataClassRule());
					}
					setWithLastConsumed($current, "native", true, "native");
				}
			)
		)?
		otherlv_2='data'
		{
			newLeafNode(otherlv_2, grammarAccess.getDataClassAccess().getDataKeyword_2());
		}
		otherlv_3='class'
		{
			newLeafNode(otherlv_3, grammarAccess.getDataClassAccess().getClassKeyword_3());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getDataClassAccess().getNameIDENTIFIERParserRuleCall_4_0());
				}
				lv_name_4_0=ruleIDENTIFIER
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getDataClassRule());
					}
					set(
						$current,
						"name",
						lv_name_4_0,
						"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_5='extends'
			{
				newLeafNode(otherlv_5, grammarAccess.getDataClassAccess().getExtendsKeyword_5_0());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getDataClassAccess().getSuperClassIDENTIFIERParserRuleCall_5_1_0());
					}
					lv_superClass_6_0=ruleIDENTIFIER
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getDataClassRule());
						}
						set(
							$current,
							"superClass",
							lv_superClass_6_0,
							"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)?
		otherlv_7='variables'
		{
			newLeafNode(otherlv_7, grammarAccess.getDataClassAccess().getVariablesKeyword_6());
		}
		(
			(
				(
					{
						newCompositeNode(grammarAccess.getDataClassAccess().getInstanceVariablesDeclarationParserRuleCall_7_0_0());
					}
					lv_instanceVariables_8_0=ruleDeclaration
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getDataClassRule());
						}
						add(
							$current,
							"instanceVariables",
							lv_instanceVariables_8_0,
							"org.eclipse.poosl.xtext.Poosl.Declaration");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				(
					otherlv_9=','
					{
						newLeafNode(otherlv_9, grammarAccess.getDataClassAccess().getCommaKeyword_7_1_0());
					}
				)?
				(
					(
						{
							newCompositeNode(grammarAccess.getDataClassAccess().getInstanceVariablesDeclarationParserRuleCall_7_1_1_0());
						}
						lv_instanceVariables_10_0=ruleDeclaration
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getDataClassRule());
							}
							add(
								$current,
								"instanceVariables",
								lv_instanceVariables_10_0,
								"org.eclipse.poosl.xtext.Poosl.Declaration");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)*
			(
				otherlv_11=','
				{
					newLeafNode(otherlv_11, grammarAccess.getDataClassAccess().getCommaKeyword_7_2());
				}
			)?
		)?
		otherlv_12='methods'
		{
			newLeafNode(otherlv_12, grammarAccess.getDataClassAccess().getMethodsKeyword_8());
		}
		(
			(
				(
					{
						newCompositeNode(grammarAccess.getDataClassAccess().getDataMethodsNamedDataMethodNamedParserRuleCall_9_0_0());
					}
					lv_dataMethodsNamed_13_0=ruleDataMethodNamed
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getDataClassRule());
						}
						add(
							$current,
							"dataMethodsNamed",
							lv_dataMethodsNamed_13_0,
							"org.eclipse.poosl.xtext.Poosl.DataMethodNamed");
						afterParserOrEnumRuleCall();
					}
				)
			)
			    |
			(
				(
					{
						newCompositeNode(grammarAccess.getDataClassAccess().getDataMethodsUnaryOperatorDataMethodUnaryOperatorParserRuleCall_9_1_0());
					}
					lv_dataMethodsUnaryOperator_14_0=ruleDataMethodUnaryOperator
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getDataClassRule());
						}
						add(
							$current,
							"dataMethodsUnaryOperator",
							lv_dataMethodsUnaryOperator_14_0,
							"org.eclipse.poosl.xtext.Poosl.DataMethodUnaryOperator");
						afterParserOrEnumRuleCall();
					}
				)
			)
			    |
			(
				(
					{
						newCompositeNode(grammarAccess.getDataClassAccess().getDataMethodsBinaryOperatorDataMethodBinaryOperatorParserRuleCall_9_2_0());
					}
					lv_dataMethodsBinaryOperator_15_0=ruleDataMethodBinaryOperator
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getDataClassRule());
						}
						add(
							$current,
							"dataMethodsBinaryOperator",
							lv_dataMethodsBinaryOperator_15_0,
							"org.eclipse.poosl.xtext.Poosl.DataMethodBinaryOperator");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
	)
;

// Entry rule entryRuleDataMethodNamed
entryRuleDataMethodNamed returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getDataMethodNamedRule()); }
	iv_ruleDataMethodNamed=ruleDataMethodNamed
	{ $current=$iv_ruleDataMethodNamed.current; }
	EOF;

// Rule DataMethodNamed
ruleDataMethodNamed returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					newCompositeNode(grammarAccess.getDataMethodNamedAccess().getAnnotationsAnnotationParserRuleCall_0_0());
				}
				lv_annotations_0_0=ruleAnnotation
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getDataMethodNamedRule());
					}
					add(
						$current,
						"annotations",
						lv_annotations_0_0,
						"org.eclipse.poosl.xtext.Poosl.Annotation");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		(
			(
				lv_native_1_0='native'
				{
					newLeafNode(lv_native_1_0, grammarAccess.getDataMethodNamedAccess().getNativeNativeKeyword_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getDataMethodNamedRule());
					}
					setWithLastConsumed($current, "native", true, "native");
				}
			)
		)?
		(
			(
				{
					newCompositeNode(grammarAccess.getDataMethodNamedAccess().getNameIDENTIFIERParserRuleCall_2_0());
				}
				lv_name_2_0=ruleIDENTIFIER
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getDataMethodNamedRule());
					}
					set(
						$current,
						"name",
						lv_name_2_0,
						"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_3='('
			{
				newLeafNode(otherlv_3, grammarAccess.getDataMethodNamedAccess().getLeftParenthesisKeyword_3_0());
			}
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getDataMethodNamedAccess().getParametersDeclarationParserRuleCall_3_1_0_0());
						}
						lv_parameters_4_0=ruleDeclaration
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getDataMethodNamedRule());
							}
							add(
								$current,
								"parameters",
								lv_parameters_4_0,
								"org.eclipse.poosl.xtext.Poosl.Declaration");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					otherlv_5=','
					{
						newLeafNode(otherlv_5, grammarAccess.getDataMethodNamedAccess().getCommaKeyword_3_1_1_0());
					}
					(
						(
							{
								newCompositeNode(grammarAccess.getDataMethodNamedAccess().getParametersDeclarationParserRuleCall_3_1_1_1_0());
							}
							lv_parameters_6_0=ruleDeclaration
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getDataMethodNamedRule());
								}
								add(
									$current,
									"parameters",
									lv_parameters_6_0,
									"org.eclipse.poosl.xtext.Poosl.Declaration");
								afterParserOrEnumRuleCall();
							}
						)
					)
				)*
			)?
			otherlv_7=')'
			{
				newLeafNode(otherlv_7, grammarAccess.getDataMethodNamedAccess().getRightParenthesisKeyword_3_2());
			}
		)?
		otherlv_8=':'
		{
			newLeafNode(otherlv_8, grammarAccess.getDataMethodNamedAccess().getColonKeyword_4());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getDataMethodNamedAccess().getReturnTypeIDENTIFIERParserRuleCall_5_0());
				}
				lv_returnType_9_0=ruleIDENTIFIER
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getDataMethodNamedRule());
					}
					set(
						$current,
						"returnType",
						lv_returnType_9_0,
						"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_10='|'
			{
				newLeafNode(otherlv_10, grammarAccess.getDataMethodNamedAccess().getVerticalLineKeyword_6_0());
			}
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getDataMethodNamedAccess().getLocalVariablesDeclarationParserRuleCall_6_1_0_0());
						}
						lv_localVariables_11_0=ruleDeclaration
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getDataMethodNamedRule());
							}
							add(
								$current,
								"localVariables",
								lv_localVariables_11_0,
								"org.eclipse.poosl.xtext.Poosl.Declaration");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					otherlv_12=','
					{
						newLeafNode(otherlv_12, grammarAccess.getDataMethodNamedAccess().getCommaKeyword_6_1_1_0());
					}
					(
						(
							{
								newCompositeNode(grammarAccess.getDataMethodNamedAccess().getLocalVariablesDeclarationParserRuleCall_6_1_1_1_0());
							}
							lv_localVariables_13_0=ruleDeclaration
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getDataMethodNamedRule());
								}
								add(
									$current,
									"localVariables",
									lv_localVariables_13_0,
									"org.eclipse.poosl.xtext.Poosl.Declaration");
								afterParserOrEnumRuleCall();
							}
						)
					)
				)*
			)?
			otherlv_14='|'
			{
				newLeafNode(otherlv_14, grammarAccess.getDataMethodNamedAccess().getVerticalLineKeyword_6_2());
			}
		)?
		(
			(
				{
					newCompositeNode(grammarAccess.getDataMethodNamedAccess().getBodyExpressionParserRuleCall_7_0());
				}
				lv_body_15_0=ruleExpression
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getDataMethodNamedRule());
					}
					set(
						$current,
						"body",
						lv_body_15_0,
						"org.eclipse.poosl.xtext.Poosl.Expression");
					afterParserOrEnumRuleCall();
				}
			)
		)?
	)
;

// Entry rule entryRuleDataMethodBinaryOperator
entryRuleDataMethodBinaryOperator returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getDataMethodBinaryOperatorRule()); }
	iv_ruleDataMethodBinaryOperator=ruleDataMethodBinaryOperator
	{ $current=$iv_ruleDataMethodBinaryOperator.current; }
	EOF;

// Rule DataMethodBinaryOperator
ruleDataMethodBinaryOperator returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					newCompositeNode(grammarAccess.getDataMethodBinaryOperatorAccess().getAnnotationsAnnotationParserRuleCall_0_0());
				}
				lv_annotations_0_0=ruleAnnotation
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getDataMethodBinaryOperatorRule());
					}
					add(
						$current,
						"annotations",
						lv_annotations_0_0,
						"org.eclipse.poosl.xtext.Poosl.Annotation");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		(
			(
				lv_native_1_0='native'
				{
					newLeafNode(lv_native_1_0, grammarAccess.getDataMethodBinaryOperatorAccess().getNativeNativeKeyword_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getDataMethodBinaryOperatorRule());
					}
					setWithLastConsumed($current, "native", true, "native");
				}
			)
		)?
		(
			(
				{
					newCompositeNode(grammarAccess.getDataMethodBinaryOperatorAccess().getNameOperatorBinaryEnumRuleCall_2_0());
				}
				lv_name_2_0=ruleOperatorBinary
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getDataMethodBinaryOperatorRule());
					}
					set(
						$current,
						"name",
						lv_name_2_0,
						"org.eclipse.poosl.xtext.Poosl.OperatorBinary");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_3='('
		{
			newLeafNode(otherlv_3, grammarAccess.getDataMethodBinaryOperatorAccess().getLeftParenthesisKeyword_3());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getDataMethodBinaryOperatorAccess().getParametersDeclarationParserRuleCall_4_0());
				}
				lv_parameters_4_0=ruleDeclaration
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getDataMethodBinaryOperatorRule());
					}
					add(
						$current,
						"parameters",
						lv_parameters_4_0,
						"org.eclipse.poosl.xtext.Poosl.Declaration");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_5=','
			{
				newLeafNode(otherlv_5, grammarAccess.getDataMethodBinaryOperatorAccess().getCommaKeyword_5_0());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getDataMethodBinaryOperatorAccess().getParametersDeclarationParserRuleCall_5_1_0());
					}
					lv_parameters_6_0=ruleDeclaration
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getDataMethodBinaryOperatorRule());
						}
						add(
							$current,
							"parameters",
							lv_parameters_6_0,
							"org.eclipse.poosl.xtext.Poosl.Declaration");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
		otherlv_7=')'
		{
			newLeafNode(otherlv_7, grammarAccess.getDataMethodBinaryOperatorAccess().getRightParenthesisKeyword_6());
		}
		otherlv_8=':'
		{
			newLeafNode(otherlv_8, grammarAccess.getDataMethodBinaryOperatorAccess().getColonKeyword_7());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getDataMethodBinaryOperatorAccess().getReturnTypeIDENTIFIERParserRuleCall_8_0());
				}
				lv_returnType_9_0=ruleIDENTIFIER
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getDataMethodBinaryOperatorRule());
					}
					set(
						$current,
						"returnType",
						lv_returnType_9_0,
						"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_10='|'
			{
				newLeafNode(otherlv_10, grammarAccess.getDataMethodBinaryOperatorAccess().getVerticalLineKeyword_9_0());
			}
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getDataMethodBinaryOperatorAccess().getLocalVariablesDeclarationParserRuleCall_9_1_0_0());
						}
						lv_localVariables_11_0=ruleDeclaration
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getDataMethodBinaryOperatorRule());
							}
							add(
								$current,
								"localVariables",
								lv_localVariables_11_0,
								"org.eclipse.poosl.xtext.Poosl.Declaration");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					otherlv_12=','
					{
						newLeafNode(otherlv_12, grammarAccess.getDataMethodBinaryOperatorAccess().getCommaKeyword_9_1_1_0());
					}
					(
						(
							{
								newCompositeNode(grammarAccess.getDataMethodBinaryOperatorAccess().getLocalVariablesDeclarationParserRuleCall_9_1_1_1_0());
							}
							lv_localVariables_13_0=ruleDeclaration
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getDataMethodBinaryOperatorRule());
								}
								add(
									$current,
									"localVariables",
									lv_localVariables_13_0,
									"org.eclipse.poosl.xtext.Poosl.Declaration");
								afterParserOrEnumRuleCall();
							}
						)
					)
				)*
			)?
			otherlv_14='|'
			{
				newLeafNode(otherlv_14, grammarAccess.getDataMethodBinaryOperatorAccess().getVerticalLineKeyword_9_2());
			}
		)?
		(
			(
				{
					newCompositeNode(grammarAccess.getDataMethodBinaryOperatorAccess().getBodyExpressionParserRuleCall_10_0());
				}
				lv_body_15_0=ruleExpression
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getDataMethodBinaryOperatorRule());
					}
					set(
						$current,
						"body",
						lv_body_15_0,
						"org.eclipse.poosl.xtext.Poosl.Expression");
					afterParserOrEnumRuleCall();
				}
			)
		)?
	)
;

// Entry rule entryRuleDataMethodUnaryOperator
entryRuleDataMethodUnaryOperator returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getDataMethodUnaryOperatorRule()); }
	iv_ruleDataMethodUnaryOperator=ruleDataMethodUnaryOperator
	{ $current=$iv_ruleDataMethodUnaryOperator.current; }
	EOF;

// Rule DataMethodUnaryOperator
ruleDataMethodUnaryOperator returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					newCompositeNode(grammarAccess.getDataMethodUnaryOperatorAccess().getAnnotationsAnnotationParserRuleCall_0_0());
				}
				lv_annotations_0_0=ruleAnnotation
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getDataMethodUnaryOperatorRule());
					}
					add(
						$current,
						"annotations",
						lv_annotations_0_0,
						"org.eclipse.poosl.xtext.Poosl.Annotation");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		(
			(
				lv_native_1_0='native'
				{
					newLeafNode(lv_native_1_0, grammarAccess.getDataMethodUnaryOperatorAccess().getNativeNativeKeyword_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getDataMethodUnaryOperatorRule());
					}
					setWithLastConsumed($current, "native", true, "native");
				}
			)
		)?
		(
			(
				{
					newCompositeNode(grammarAccess.getDataMethodUnaryOperatorAccess().getNameOperatorUnaryEnumRuleCall_2_0());
				}
				lv_name_2_0=ruleOperatorUnary
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getDataMethodUnaryOperatorRule());
					}
					set(
						$current,
						"name",
						lv_name_2_0,
						"org.eclipse.poosl.xtext.Poosl.OperatorUnary");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_3='('
			{
				newLeafNode(otherlv_3, grammarAccess.getDataMethodUnaryOperatorAccess().getLeftParenthesisKeyword_3_0());
			}
			otherlv_4=')'
			{
				newLeafNode(otherlv_4, grammarAccess.getDataMethodUnaryOperatorAccess().getRightParenthesisKeyword_3_1());
			}
		)?
		otherlv_5=':'
		{
			newLeafNode(otherlv_5, grammarAccess.getDataMethodUnaryOperatorAccess().getColonKeyword_4());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getDataMethodUnaryOperatorAccess().getReturnTypeIDENTIFIERParserRuleCall_5_0());
				}
				lv_returnType_6_0=ruleIDENTIFIER
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getDataMethodUnaryOperatorRule());
					}
					set(
						$current,
						"returnType",
						lv_returnType_6_0,
						"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_7='|'
			{
				newLeafNode(otherlv_7, grammarAccess.getDataMethodUnaryOperatorAccess().getVerticalLineKeyword_6_0());
			}
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getDataMethodUnaryOperatorAccess().getLocalVariablesDeclarationParserRuleCall_6_1_0_0());
						}
						lv_localVariables_8_0=ruleDeclaration
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getDataMethodUnaryOperatorRule());
							}
							add(
								$current,
								"localVariables",
								lv_localVariables_8_0,
								"org.eclipse.poosl.xtext.Poosl.Declaration");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					otherlv_9=','
					{
						newLeafNode(otherlv_9, grammarAccess.getDataMethodUnaryOperatorAccess().getCommaKeyword_6_1_1_0());
					}
					(
						(
							{
								newCompositeNode(grammarAccess.getDataMethodUnaryOperatorAccess().getLocalVariablesDeclarationParserRuleCall_6_1_1_1_0());
							}
							lv_localVariables_10_0=ruleDeclaration
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getDataMethodUnaryOperatorRule());
								}
								add(
									$current,
									"localVariables",
									lv_localVariables_10_0,
									"org.eclipse.poosl.xtext.Poosl.Declaration");
								afterParserOrEnumRuleCall();
							}
						)
					)
				)*
			)?
			otherlv_11='|'
			{
				newLeafNode(otherlv_11, grammarAccess.getDataMethodUnaryOperatorAccess().getVerticalLineKeyword_6_2());
			}
		)?
		(
			(
				{
					newCompositeNode(grammarAccess.getDataMethodUnaryOperatorAccess().getBodyExpressionParserRuleCall_7_0());
				}
				lv_body_12_0=ruleExpression
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getDataMethodUnaryOperatorRule());
					}
					set(
						$current,
						"body",
						lv_body_12_0,
						"org.eclipse.poosl.xtext.Poosl.Expression");
					afterParserOrEnumRuleCall();
				}
			)
		)?
	)
;

// Entry rule entryRuleDeclaration
entryRuleDeclaration returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getDeclarationRule()); }
	iv_ruleDeclaration=ruleDeclaration
	{ $current=$iv_ruleDeclaration.current; }
	EOF;

// Rule Declaration
ruleDeclaration returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				(
					{
						newCompositeNode(grammarAccess.getDeclarationAccess().getVariablesVariableParserRuleCall_0_0_0());
					}
					lv_variables_0_0=ruleVariable
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getDeclarationRule());
						}
						add(
							$current,
							"variables",
							lv_variables_0_0,
							"org.eclipse.poosl.xtext.Poosl.Variable");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				otherlv_1=','
				{
					newLeafNode(otherlv_1, grammarAccess.getDeclarationAccess().getCommaKeyword_0_1_0());
				}
				(
					(
						{
							newCompositeNode(grammarAccess.getDeclarationAccess().getVariablesVariableParserRuleCall_0_1_1_0());
						}
						lv_variables_2_0=ruleVariable
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getDeclarationRule());
							}
							add(
								$current,
								"variables",
								lv_variables_2_0,
								"org.eclipse.poosl.xtext.Poosl.Variable");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)*
		)?
		otherlv_3=':'
		{
			newLeafNode(otherlv_3, grammarAccess.getDeclarationAccess().getColonKeyword_1());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getDeclarationAccess().getTypeIDENTIFIERParserRuleCall_2_0());
				}
				lv_type_4_0=ruleIDENTIFIER
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getDeclarationRule());
					}
					set(
						$current,
						"type",
						lv_type_4_0,
						"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRuleVariable
entryRuleVariable returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getVariableRule()); }
	iv_ruleVariable=ruleVariable
	{ $current=$iv_ruleVariable.current; }
	EOF;

// Rule Variable
ruleVariable returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				newCompositeNode(grammarAccess.getVariableAccess().getNameIDENTIFIERParserRuleCall_0());
			}
			lv_name_0_0=ruleIDENTIFIER
			{
				if ($current==null) {
					$current = createModelElementForParent(grammarAccess.getVariableRule());
				}
				set(
					$current,
					"name",
					lv_name_0_0,
					"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
				afterParserOrEnumRuleCall();
			}
		)
	)
;

// Entry rule entryRuleAnnotation
entryRuleAnnotation returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getAnnotationRule()); }
	iv_ruleAnnotation=ruleAnnotation
	{ $current=$iv_ruleAnnotation.current; }
	EOF;

// Rule Annotation
ruleAnnotation returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='@'
		{
			newLeafNode(otherlv_0, grammarAccess.getAnnotationAccess().getCommercialAtKeyword_0());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getAnnotationAccess().getNameIDENTIFIERParserRuleCall_1_0());
				}
				lv_name_1_0=ruleIDENTIFIER
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getAnnotationRule());
					}
					set(
						$current,
						"name",
						lv_name_1_0,
						"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_2='('
			{
				newLeafNode(otherlv_2, grammarAccess.getAnnotationAccess().getLeftParenthesisKeyword_2_0());
			}
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getAnnotationAccess().getArgumentsExpressionConstantParserRuleCall_2_1_0_0());
						}
						lv_arguments_3_0=ruleExpressionConstant
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getAnnotationRule());
							}
							add(
								$current,
								"arguments",
								lv_arguments_3_0,
								"org.eclipse.poosl.xtext.Poosl.ExpressionConstant");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					otherlv_4=','
					{
						newLeafNode(otherlv_4, grammarAccess.getAnnotationAccess().getCommaKeyword_2_1_1_0());
					}
					(
						(
							{
								newCompositeNode(grammarAccess.getAnnotationAccess().getArgumentsExpressionConstantParserRuleCall_2_1_1_1_0());
							}
							lv_arguments_5_0=ruleExpressionConstant
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getAnnotationRule());
								}
								add(
									$current,
									"arguments",
									lv_arguments_5_0,
									"org.eclipse.poosl.xtext.Poosl.ExpressionConstant");
								afterParserOrEnumRuleCall();
							}
						)
					)
				)*
			)?
			otherlv_6=')'
			{
				newLeafNode(otherlv_6, grammarAccess.getAnnotationAccess().getRightParenthesisKeyword_2_2());
			}
		)?
	)
;

// Entry rule entryRuleProcessClass
entryRuleProcessClass returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getProcessClassRule()); }
	iv_ruleProcessClass=ruleProcessClass
	{ $current=$iv_ruleProcessClass.current; }
	EOF;

// Rule ProcessClass
ruleProcessClass returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					newCompositeNode(grammarAccess.getProcessClassAccess().getAnnotationsAnnotationParserRuleCall_0_0());
				}
				lv_annotations_0_0=ruleAnnotation
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getProcessClassRule());
					}
					add(
						$current,
						"annotations",
						lv_annotations_0_0,
						"org.eclipse.poosl.xtext.Poosl.Annotation");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		otherlv_1='process'
		{
			newLeafNode(otherlv_1, grammarAccess.getProcessClassAccess().getProcessKeyword_1());
		}
		otherlv_2='class'
		{
			newLeafNode(otherlv_2, grammarAccess.getProcessClassAccess().getClassKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getProcessClassAccess().getNameIDENTIFIERParserRuleCall_3_0());
				}
				lv_name_3_0=ruleIDENTIFIER
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getProcessClassRule());
					}
					set(
						$current,
						"name",
						lv_name_3_0,
						"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_4='('
			{
				newLeafNode(otherlv_4, grammarAccess.getProcessClassAccess().getLeftParenthesisKeyword_4_0());
			}
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getProcessClassAccess().getParametersDeclarationParserRuleCall_4_1_0_0());
						}
						lv_parameters_5_0=ruleDeclaration
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getProcessClassRule());
							}
							add(
								$current,
								"parameters",
								lv_parameters_5_0,
								"org.eclipse.poosl.xtext.Poosl.Declaration");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					otherlv_6=','
					{
						newLeafNode(otherlv_6, grammarAccess.getProcessClassAccess().getCommaKeyword_4_1_1_0());
					}
					(
						(
							{
								newCompositeNode(grammarAccess.getProcessClassAccess().getParametersDeclarationParserRuleCall_4_1_1_1_0());
							}
							lv_parameters_7_0=ruleDeclaration
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getProcessClassRule());
								}
								add(
									$current,
									"parameters",
									lv_parameters_7_0,
									"org.eclipse.poosl.xtext.Poosl.Declaration");
								afterParserOrEnumRuleCall();
							}
						)
					)
				)*
			)?
			otherlv_8=')'
			{
				newLeafNode(otherlv_8, grammarAccess.getProcessClassAccess().getRightParenthesisKeyword_4_2());
			}
		)?
		(
			otherlv_9='extends'
			{
				newLeafNode(otherlv_9, grammarAccess.getProcessClassAccess().getExtendsKeyword_5_0());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getProcessClassAccess().getSuperClassIDENTIFIERParserRuleCall_5_1_0());
					}
					lv_superClass_10_0=ruleIDENTIFIER
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getProcessClassRule());
						}
						set(
							$current,
							"superClass",
							lv_superClass_10_0,
							"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)?
		otherlv_11='ports'
		{
			newLeafNode(otherlv_11, grammarAccess.getProcessClassAccess().getPortsKeyword_6());
		}
		(
			(
				(
					{
						newCompositeNode(grammarAccess.getProcessClassAccess().getPortsPortParserRuleCall_7_0_0());
					}
					lv_ports_12_0=rulePort
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getProcessClassRule());
						}
						add(
							$current,
							"ports",
							lv_ports_12_0,
							"org.eclipse.poosl.xtext.Poosl.Port");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				(
					otherlv_13=','
					{
						newLeafNode(otherlv_13, grammarAccess.getProcessClassAccess().getCommaKeyword_7_1_0());
					}
				)?
				(
					(
						{
							newCompositeNode(grammarAccess.getProcessClassAccess().getPortsPortParserRuleCall_7_1_1_0());
						}
						lv_ports_14_0=rulePort
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getProcessClassRule());
							}
							add(
								$current,
								"ports",
								lv_ports_14_0,
								"org.eclipse.poosl.xtext.Poosl.Port");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)*
			(
				otherlv_15=','
				{
					newLeafNode(otherlv_15, grammarAccess.getProcessClassAccess().getCommaKeyword_7_2());
				}
			)?
		)?
		otherlv_16='messages'
		{
			newLeafNode(otherlv_16, grammarAccess.getProcessClassAccess().getMessagesKeyword_8());
		}
		(
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getProcessClassAccess().getReceiveMessagesMessageReceiveSignatureParserRuleCall_9_0_0_0());
						}
						lv_receiveMessages_17_0=ruleMessageReceiveSignature
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getProcessClassRule());
							}
							add(
								$current,
								"receiveMessages",
								lv_receiveMessages_17_0,
								"org.eclipse.poosl.xtext.Poosl.MessageReceiveSignature");
							afterParserOrEnumRuleCall();
						}
					)
				)
				    |
				(
					(
						{
							newCompositeNode(grammarAccess.getProcessClassAccess().getSendMessagesMessageSendSignatureParserRuleCall_9_0_1_0());
						}
						lv_sendMessages_18_0=ruleMessageSendSignature
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getProcessClassRule());
							}
							add(
								$current,
								"sendMessages",
								lv_sendMessages_18_0,
								"org.eclipse.poosl.xtext.Poosl.MessageSendSignature");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)
			(
				(
					otherlv_19=','
					{
						newLeafNode(otherlv_19, grammarAccess.getProcessClassAccess().getCommaKeyword_9_1_0());
					}
				)?
				(
					(
						(
							{
								newCompositeNode(grammarAccess.getProcessClassAccess().getReceiveMessagesMessageReceiveSignatureParserRuleCall_9_1_1_0_0());
							}
							lv_receiveMessages_20_0=ruleMessageReceiveSignature
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getProcessClassRule());
								}
								add(
									$current,
									"receiveMessages",
									lv_receiveMessages_20_0,
									"org.eclipse.poosl.xtext.Poosl.MessageReceiveSignature");
								afterParserOrEnumRuleCall();
							}
						)
					)
					    |
					(
						(
							{
								newCompositeNode(grammarAccess.getProcessClassAccess().getSendMessagesMessageSendSignatureParserRuleCall_9_1_1_1_0());
							}
							lv_sendMessages_21_0=ruleMessageSendSignature
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getProcessClassRule());
								}
								add(
									$current,
									"sendMessages",
									lv_sendMessages_21_0,
									"org.eclipse.poosl.xtext.Poosl.MessageSendSignature");
								afterParserOrEnumRuleCall();
							}
						)
					)
				)
			)*
			(
				otherlv_22=','
				{
					newLeafNode(otherlv_22, grammarAccess.getProcessClassAccess().getCommaKeyword_9_2());
				}
			)?
		)?
		otherlv_23='variables'
		{
			newLeafNode(otherlv_23, grammarAccess.getProcessClassAccess().getVariablesKeyword_10());
		}
		(
			(
				(
					{
						newCompositeNode(grammarAccess.getProcessClassAccess().getInstanceVariablesDeclarationParserRuleCall_11_0_0());
					}
					lv_instanceVariables_24_0=ruleDeclaration
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getProcessClassRule());
						}
						add(
							$current,
							"instanceVariables",
							lv_instanceVariables_24_0,
							"org.eclipse.poosl.xtext.Poosl.Declaration");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				(
					otherlv_25=','
					{
						newLeafNode(otherlv_25, grammarAccess.getProcessClassAccess().getCommaKeyword_11_1_0());
					}
				)?
				(
					(
						{
							newCompositeNode(grammarAccess.getProcessClassAccess().getInstanceVariablesDeclarationParserRuleCall_11_1_1_0());
						}
						lv_instanceVariables_26_0=ruleDeclaration
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getProcessClassRule());
							}
							add(
								$current,
								"instanceVariables",
								lv_instanceVariables_26_0,
								"org.eclipse.poosl.xtext.Poosl.Declaration");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)*
			(
				otherlv_27=','
				{
					newLeafNode(otherlv_27, grammarAccess.getProcessClassAccess().getCommaKeyword_11_2());
				}
			)?
		)?
		otherlv_28='init'
		{
			newLeafNode(otherlv_28, grammarAccess.getProcessClassAccess().getInitKeyword_12());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getProcessClassAccess().getInitialMethodCallProcessMethodCallParserRuleCall_13_0());
				}
				lv_initialMethodCall_29_0=ruleProcessMethodCall
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getProcessClassRule());
					}
					set(
						$current,
						"initialMethodCall",
						lv_initialMethodCall_29_0,
						"org.eclipse.poosl.xtext.Poosl.ProcessMethodCall");
					afterParserOrEnumRuleCall();
				}
			)
		)?
		otherlv_30='methods'
		{
			newLeafNode(otherlv_30, grammarAccess.getProcessClassAccess().getMethodsKeyword_14());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getProcessClassAccess().getMethodsProcessMethodParserRuleCall_15_0());
				}
				lv_methods_31_0=ruleProcessMethod
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getProcessClassRule());
					}
					add(
						$current,
						"methods",
						lv_methods_31_0,
						"org.eclipse.poosl.xtext.Poosl.ProcessMethod");
					afterParserOrEnumRuleCall();
				}
			)
		)*
	)
;

// Entry rule entryRuleProcessMethod
entryRuleProcessMethod returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getProcessMethodRule()); }
	iv_ruleProcessMethod=ruleProcessMethod
	{ $current=$iv_ruleProcessMethod.current; }
	EOF;

// Rule ProcessMethod
ruleProcessMethod returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					newCompositeNode(grammarAccess.getProcessMethodAccess().getAnnotationsAnnotationParserRuleCall_0_0());
				}
				lv_annotations_0_0=ruleAnnotation
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getProcessMethodRule());
					}
					add(
						$current,
						"annotations",
						lv_annotations_0_0,
						"org.eclipse.poosl.xtext.Poosl.Annotation");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		(
			(
				{
					newCompositeNode(grammarAccess.getProcessMethodAccess().getNameIDENTIFIERParserRuleCall_1_0());
				}
				lv_name_1_0=ruleIDENTIFIER
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getProcessMethodRule());
					}
					set(
						$current,
						"name",
						lv_name_1_0,
						"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_2='('
		{
			newLeafNode(otherlv_2, grammarAccess.getProcessMethodAccess().getLeftParenthesisKeyword_2());
		}
		(
			(
				(
					{
						newCompositeNode(grammarAccess.getProcessMethodAccess().getInputParametersDeclarationParserRuleCall_3_0_0());
					}
					lv_inputParameters_3_0=ruleDeclaration
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getProcessMethodRule());
						}
						add(
							$current,
							"inputParameters",
							lv_inputParameters_3_0,
							"org.eclipse.poosl.xtext.Poosl.Declaration");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				otherlv_4=','
				{
					newLeafNode(otherlv_4, grammarAccess.getProcessMethodAccess().getCommaKeyword_3_1_0());
				}
				(
					(
						{
							newCompositeNode(grammarAccess.getProcessMethodAccess().getInputParametersDeclarationParserRuleCall_3_1_1_0());
						}
						lv_inputParameters_5_0=ruleDeclaration
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getProcessMethodRule());
							}
							add(
								$current,
								"inputParameters",
								lv_inputParameters_5_0,
								"org.eclipse.poosl.xtext.Poosl.Declaration");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)*
		)?
		otherlv_6=')'
		{
			newLeafNode(otherlv_6, grammarAccess.getProcessMethodAccess().getRightParenthesisKeyword_4());
		}
		otherlv_7='('
		{
			newLeafNode(otherlv_7, grammarAccess.getProcessMethodAccess().getLeftParenthesisKeyword_5());
		}
		(
			(
				(
					{
						newCompositeNode(grammarAccess.getProcessMethodAccess().getOutputParametersDeclarationParserRuleCall_6_0_0());
					}
					lv_outputParameters_8_0=ruleDeclaration
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getProcessMethodRule());
						}
						add(
							$current,
							"outputParameters",
							lv_outputParameters_8_0,
							"org.eclipse.poosl.xtext.Poosl.Declaration");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				otherlv_9=','
				{
					newLeafNode(otherlv_9, grammarAccess.getProcessMethodAccess().getCommaKeyword_6_1_0());
				}
				(
					(
						{
							newCompositeNode(grammarAccess.getProcessMethodAccess().getOutputParametersDeclarationParserRuleCall_6_1_1_0());
						}
						lv_outputParameters_10_0=ruleDeclaration
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getProcessMethodRule());
							}
							add(
								$current,
								"outputParameters",
								lv_outputParameters_10_0,
								"org.eclipse.poosl.xtext.Poosl.Declaration");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)*
		)?
		otherlv_11=')'
		{
			newLeafNode(otherlv_11, grammarAccess.getProcessMethodAccess().getRightParenthesisKeyword_7());
		}
		(
			otherlv_12='|'
			{
				newLeafNode(otherlv_12, grammarAccess.getProcessMethodAccess().getVerticalLineKeyword_8_0());
			}
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getProcessMethodAccess().getLocalVariablesDeclarationParserRuleCall_8_1_0_0());
						}
						lv_localVariables_13_0=ruleDeclaration
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getProcessMethodRule());
							}
							add(
								$current,
								"localVariables",
								lv_localVariables_13_0,
								"org.eclipse.poosl.xtext.Poosl.Declaration");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					otherlv_14=','
					{
						newLeafNode(otherlv_14, grammarAccess.getProcessMethodAccess().getCommaKeyword_8_1_1_0());
					}
					(
						(
							{
								newCompositeNode(grammarAccess.getProcessMethodAccess().getLocalVariablesDeclarationParserRuleCall_8_1_1_1_0());
							}
							lv_localVariables_15_0=ruleDeclaration
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getProcessMethodRule());
								}
								add(
									$current,
									"localVariables",
									lv_localVariables_15_0,
									"org.eclipse.poosl.xtext.Poosl.Declaration");
								afterParserOrEnumRuleCall();
							}
						)
					)
				)*
			)?
			otherlv_16='|'
			{
				newLeafNode(otherlv_16, grammarAccess.getProcessMethodAccess().getVerticalLineKeyword_8_2());
			}
		)?
		(
			(
				{
					newCompositeNode(grammarAccess.getProcessMethodAccess().getBodyStatementParserRuleCall_9_0());
				}
				lv_body_17_0=ruleStatement
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getProcessMethodRule());
					}
					set(
						$current,
						"body",
						lv_body_17_0,
						"org.eclipse.poosl.xtext.Poosl.Statement");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRulePort
entryRulePort returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getPortRule()); }
	iv_rulePort=rulePort
	{ $current=$iv_rulePort.current; }
	EOF;

// Rule Port
rulePort returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				newCompositeNode(grammarAccess.getPortAccess().getNameIDENTIFIERParserRuleCall_0());
			}
			lv_name_0_0=ruleIDENTIFIER
			{
				if ($current==null) {
					$current = createModelElementForParent(grammarAccess.getPortRule());
				}
				set(
					$current,
					"name",
					lv_name_0_0,
					"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
				afterParserOrEnumRuleCall();
			}
		)
	)
;

// Entry rule entryRuleMessageReceiveSignature
entryRuleMessageReceiveSignature returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getMessageReceiveSignatureRule()); }
	iv_ruleMessageReceiveSignature=ruleMessageReceiveSignature
	{ $current=$iv_ruleMessageReceiveSignature.current; }
	EOF;

// Rule MessageReceiveSignature
ruleMessageReceiveSignature returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					newCompositeNode(grammarAccess.getMessageReceiveSignatureAccess().getPortPortReferenceParserRuleCall_0_0());
				}
				lv_port_0_0=rulePortReference
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getMessageReceiveSignatureRule());
					}
					set(
						$current,
						"port",
						lv_port_0_0,
						"org.eclipse.poosl.xtext.Poosl.PortReference");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_1='?'
		{
			newLeafNode(otherlv_1, grammarAccess.getMessageReceiveSignatureAccess().getQuestionMarkKeyword_1());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getMessageReceiveSignatureAccess().getNameIDENTIFIERParserRuleCall_2_0());
				}
				lv_name_2_0=ruleIDENTIFIER
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getMessageReceiveSignatureRule());
					}
					set(
						$current,
						"name",
						lv_name_2_0,
						"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_3='('
			{
				newLeafNode(otherlv_3, grammarAccess.getMessageReceiveSignatureAccess().getLeftParenthesisKeyword_3_0());
			}
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getMessageReceiveSignatureAccess().getParametersMessageParameterParserRuleCall_3_1_0_0());
						}
						lv_parameters_4_0=ruleMessageParameter
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getMessageReceiveSignatureRule());
							}
							add(
								$current,
								"parameters",
								lv_parameters_4_0,
								"org.eclipse.poosl.xtext.Poosl.MessageParameter");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					otherlv_5=','
					{
						newLeafNode(otherlv_5, grammarAccess.getMessageReceiveSignatureAccess().getCommaKeyword_3_1_1_0());
					}
					(
						(
							{
								newCompositeNode(grammarAccess.getMessageReceiveSignatureAccess().getParametersMessageParameterParserRuleCall_3_1_1_1_0());
							}
							lv_parameters_6_0=ruleMessageParameter
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getMessageReceiveSignatureRule());
								}
								add(
									$current,
									"parameters",
									lv_parameters_6_0,
									"org.eclipse.poosl.xtext.Poosl.MessageParameter");
								afterParserOrEnumRuleCall();
							}
						)
					)
				)*
			)?
			otherlv_7=')'
			{
				newLeafNode(otherlv_7, grammarAccess.getMessageReceiveSignatureAccess().getRightParenthesisKeyword_3_2());
			}
		)?
	)
;

// Entry rule entryRuleMessageSendSignature
entryRuleMessageSendSignature returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getMessageSendSignatureRule()); }
	iv_ruleMessageSendSignature=ruleMessageSendSignature
	{ $current=$iv_ruleMessageSendSignature.current; }
	EOF;

// Rule MessageSendSignature
ruleMessageSendSignature returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					newCompositeNode(grammarAccess.getMessageSendSignatureAccess().getPortPortReferenceParserRuleCall_0_0());
				}
				lv_port_0_0=rulePortReference
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getMessageSendSignatureRule());
					}
					set(
						$current,
						"port",
						lv_port_0_0,
						"org.eclipse.poosl.xtext.Poosl.PortReference");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_1='!'
		{
			newLeafNode(otherlv_1, grammarAccess.getMessageSendSignatureAccess().getExclamationMarkKeyword_1());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getMessageSendSignatureAccess().getNameIDENTIFIERParserRuleCall_2_0());
				}
				lv_name_2_0=ruleIDENTIFIER
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getMessageSendSignatureRule());
					}
					set(
						$current,
						"name",
						lv_name_2_0,
						"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_3='('
			{
				newLeafNode(otherlv_3, grammarAccess.getMessageSendSignatureAccess().getLeftParenthesisKeyword_3_0());
			}
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getMessageSendSignatureAccess().getParametersMessageParameterParserRuleCall_3_1_0_0());
						}
						lv_parameters_4_0=ruleMessageParameter
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getMessageSendSignatureRule());
							}
							add(
								$current,
								"parameters",
								lv_parameters_4_0,
								"org.eclipse.poosl.xtext.Poosl.MessageParameter");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					otherlv_5=','
					{
						newLeafNode(otherlv_5, grammarAccess.getMessageSendSignatureAccess().getCommaKeyword_3_1_1_0());
					}
					(
						(
							{
								newCompositeNode(grammarAccess.getMessageSendSignatureAccess().getParametersMessageParameterParserRuleCall_3_1_1_1_0());
							}
							lv_parameters_6_0=ruleMessageParameter
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getMessageSendSignatureRule());
								}
								add(
									$current,
									"parameters",
									lv_parameters_6_0,
									"org.eclipse.poosl.xtext.Poosl.MessageParameter");
								afterParserOrEnumRuleCall();
							}
						)
					)
				)*
			)?
			otherlv_7=')'
			{
				newLeafNode(otherlv_7, grammarAccess.getMessageSendSignatureAccess().getRightParenthesisKeyword_3_2());
			}
		)?
	)
;

// Entry rule entryRuleMessageParameter
entryRuleMessageParameter returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getMessageParameterRule()); }
	iv_ruleMessageParameter=ruleMessageParameter
	{ $current=$iv_ruleMessageParameter.current; }
	EOF;

// Rule MessageParameter
ruleMessageParameter returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				newCompositeNode(grammarAccess.getMessageParameterAccess().getTypeIDENTIFIERParserRuleCall_0());
			}
			lv_type_0_0=ruleIDENTIFIER
			{
				if ($current==null) {
					$current = createModelElementForParent(grammarAccess.getMessageParameterRule());
				}
				set(
					$current,
					"type",
					lv_type_0_0,
					"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
				afterParserOrEnumRuleCall();
			}
		)
	)
;

// Entry rule entryRuleSystem
entryRuleSystem returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getSystemRule()); }
	iv_ruleSystem=ruleSystem
	{ $current=$iv_ruleSystem.current; }
	EOF;

// Rule System
ruleSystem returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getSystemAccess().getClusterClassAction_0(),
					$current);
			}
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getSystemAccess().getAnnotationsAnnotationParserRuleCall_1_0());
				}
				lv_annotations_1_0=ruleAnnotation
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getSystemRule());
					}
					add(
						$current,
						"annotations",
						lv_annotations_1_0,
						"org.eclipse.poosl.xtext.Poosl.Annotation");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		otherlv_2='system'
		{
			newLeafNode(otherlv_2, grammarAccess.getSystemAccess().getSystemKeyword_2());
		}
		(
			otherlv_3='('
			{
				newLeafNode(otherlv_3, grammarAccess.getSystemAccess().getLeftParenthesisKeyword_3_0());
			}
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getSystemAccess().getParametersDeclarationParserRuleCall_3_1_0_0());
						}
						lv_parameters_4_0=ruleDeclaration
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getSystemRule());
							}
							add(
								$current,
								"parameters",
								lv_parameters_4_0,
								"org.eclipse.poosl.xtext.Poosl.Declaration");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					otherlv_5=','
					{
						newLeafNode(otherlv_5, grammarAccess.getSystemAccess().getCommaKeyword_3_1_1_0());
					}
					(
						(
							{
								newCompositeNode(grammarAccess.getSystemAccess().getParametersDeclarationParserRuleCall_3_1_1_1_0());
							}
							lv_parameters_6_0=ruleDeclaration
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getSystemRule());
								}
								add(
									$current,
									"parameters",
									lv_parameters_6_0,
									"org.eclipse.poosl.xtext.Poosl.Declaration");
								afterParserOrEnumRuleCall();
							}
						)
					)
				)*
			)?
			otherlv_7=')'
			{
				newLeafNode(otherlv_7, grammarAccess.getSystemAccess().getRightParenthesisKeyword_3_2());
			}
		)?
		(
			otherlv_8='ports'
			{
				newLeafNode(otherlv_8, grammarAccess.getSystemAccess().getPortsKeyword_4_0());
			}
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getSystemAccess().getPortsPortParserRuleCall_4_1_0_0());
						}
						lv_ports_9_0=rulePort
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getSystemRule());
							}
							add(
								$current,
								"ports",
								lv_ports_9_0,
								"org.eclipse.poosl.xtext.Poosl.Port");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					(
						otherlv_10=','
						{
							newLeafNode(otherlv_10, grammarAccess.getSystemAccess().getCommaKeyword_4_1_1_0());
						}
					)?
					(
						(
							{
								newCompositeNode(grammarAccess.getSystemAccess().getPortsPortParserRuleCall_4_1_1_1_0());
							}
							lv_ports_11_0=rulePort
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getSystemRule());
								}
								add(
									$current,
									"ports",
									lv_ports_11_0,
									"org.eclipse.poosl.xtext.Poosl.Port");
								afterParserOrEnumRuleCall();
							}
						)
					)
				)*
				(
					otherlv_12=','
					{
						newLeafNode(otherlv_12, grammarAccess.getSystemAccess().getCommaKeyword_4_1_2());
					}
				)?
			)?
		)?
		otherlv_13='instances'
		{
			newLeafNode(otherlv_13, grammarAccess.getSystemAccess().getInstancesKeyword_5());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getSystemAccess().getInstancesInstanceParserRuleCall_6_0());
				}
				lv_instances_14_0=ruleInstance
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getSystemRule());
					}
					add(
						$current,
						"instances",
						lv_instances_14_0,
						"org.eclipse.poosl.xtext.Poosl.Instance");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		otherlv_15='channels'
		{
			newLeafNode(otherlv_15, grammarAccess.getSystemAccess().getChannelsKeyword_7());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getSystemAccess().getChannelsChannelParserRuleCall_8_0());
				}
				lv_channels_16_0=ruleChannel
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getSystemRule());
					}
					add(
						$current,
						"channels",
						lv_channels_16_0,
						"org.eclipse.poosl.xtext.Poosl.Channel");
					afterParserOrEnumRuleCall();
				}
			)
		)*
	)
;

// Entry rule entryRuleClusterClass
entryRuleClusterClass returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getClusterClassRule()); }
	iv_ruleClusterClass=ruleClusterClass
	{ $current=$iv_ruleClusterClass.current; }
	EOF;

// Rule ClusterClass
ruleClusterClass returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					newCompositeNode(grammarAccess.getClusterClassAccess().getAnnotationsAnnotationParserRuleCall_0_0());
				}
				lv_annotations_0_0=ruleAnnotation
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getClusterClassRule());
					}
					add(
						$current,
						"annotations",
						lv_annotations_0_0,
						"org.eclipse.poosl.xtext.Poosl.Annotation");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		otherlv_1='cluster'
		{
			newLeafNode(otherlv_1, grammarAccess.getClusterClassAccess().getClusterKeyword_1());
		}
		otherlv_2='class'
		{
			newLeafNode(otherlv_2, grammarAccess.getClusterClassAccess().getClassKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getClusterClassAccess().getNameIDENTIFIERParserRuleCall_3_0());
				}
				lv_name_3_0=ruleIDENTIFIER
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getClusterClassRule());
					}
					set(
						$current,
						"name",
						lv_name_3_0,
						"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_4='('
			{
				newLeafNode(otherlv_4, grammarAccess.getClusterClassAccess().getLeftParenthesisKeyword_4_0());
			}
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getClusterClassAccess().getParametersDeclarationParserRuleCall_4_1_0_0());
						}
						lv_parameters_5_0=ruleDeclaration
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getClusterClassRule());
							}
							add(
								$current,
								"parameters",
								lv_parameters_5_0,
								"org.eclipse.poosl.xtext.Poosl.Declaration");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					otherlv_6=','
					{
						newLeafNode(otherlv_6, grammarAccess.getClusterClassAccess().getCommaKeyword_4_1_1_0());
					}
					(
						(
							{
								newCompositeNode(grammarAccess.getClusterClassAccess().getParametersDeclarationParserRuleCall_4_1_1_1_0());
							}
							lv_parameters_7_0=ruleDeclaration
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getClusterClassRule());
								}
								add(
									$current,
									"parameters",
									lv_parameters_7_0,
									"org.eclipse.poosl.xtext.Poosl.Declaration");
								afterParserOrEnumRuleCall();
							}
						)
					)
				)*
			)?
			otherlv_8=')'
			{
				newLeafNode(otherlv_8, grammarAccess.getClusterClassAccess().getRightParenthesisKeyword_4_2());
			}
		)?
		otherlv_9='ports'
		{
			newLeafNode(otherlv_9, grammarAccess.getClusterClassAccess().getPortsKeyword_5());
		}
		(
			(
				(
					{
						newCompositeNode(grammarAccess.getClusterClassAccess().getPortsPortParserRuleCall_6_0_0());
					}
					lv_ports_10_0=rulePort
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getClusterClassRule());
						}
						add(
							$current,
							"ports",
							lv_ports_10_0,
							"org.eclipse.poosl.xtext.Poosl.Port");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				(
					otherlv_11=','
					{
						newLeafNode(otherlv_11, grammarAccess.getClusterClassAccess().getCommaKeyword_6_1_0());
					}
				)?
				(
					(
						{
							newCompositeNode(grammarAccess.getClusterClassAccess().getPortsPortParserRuleCall_6_1_1_0());
						}
						lv_ports_12_0=rulePort
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getClusterClassRule());
							}
							add(
								$current,
								"ports",
								lv_ports_12_0,
								"org.eclipse.poosl.xtext.Poosl.Port");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)*
			(
				otherlv_13=','
				{
					newLeafNode(otherlv_13, grammarAccess.getClusterClassAccess().getCommaKeyword_6_2());
				}
			)?
		)?
		otherlv_14='instances'
		{
			newLeafNode(otherlv_14, grammarAccess.getClusterClassAccess().getInstancesKeyword_7());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getClusterClassAccess().getInstancesInstanceParserRuleCall_8_0());
				}
				lv_instances_15_0=ruleInstance
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getClusterClassRule());
					}
					add(
						$current,
						"instances",
						lv_instances_15_0,
						"org.eclipse.poosl.xtext.Poosl.Instance");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		otherlv_16='channels'
		{
			newLeafNode(otherlv_16, grammarAccess.getClusterClassAccess().getChannelsKeyword_9());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getClusterClassAccess().getChannelsChannelParserRuleCall_10_0());
				}
				lv_channels_17_0=ruleChannel
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getClusterClassRule());
					}
					add(
						$current,
						"channels",
						lv_channels_17_0,
						"org.eclipse.poosl.xtext.Poosl.Channel");
					afterParserOrEnumRuleCall();
				}
			)
		)*
	)
;

// Entry rule entryRuleInstance
entryRuleInstance returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getInstanceRule()); }
	iv_ruleInstance=ruleInstance
	{ $current=$iv_ruleInstance.current; }
	EOF;

// Rule Instance
ruleInstance returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					newCompositeNode(grammarAccess.getInstanceAccess().getAnnotationsAnnotationParserRuleCall_0_0());
				}
				lv_annotations_0_0=ruleAnnotation
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getInstanceRule());
					}
					add(
						$current,
						"annotations",
						lv_annotations_0_0,
						"org.eclipse.poosl.xtext.Poosl.Annotation");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		(
			(
				{
					newCompositeNode(grammarAccess.getInstanceAccess().getNameIDENTIFIERParserRuleCall_1_0());
				}
				lv_name_1_0=ruleIDENTIFIER
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getInstanceRule());
					}
					set(
						$current,
						"name",
						lv_name_1_0,
						"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_2=':'
		{
			newLeafNode(otherlv_2, grammarAccess.getInstanceAccess().getColonKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getInstanceAccess().getClassDefinitionIDENTIFIERParserRuleCall_3_0());
				}
				lv_classDefinition_3_0=ruleIDENTIFIER
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getInstanceRule());
					}
					set(
						$current,
						"classDefinition",
						lv_classDefinition_3_0,
						"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_4='('
			{
				newLeafNode(otherlv_4, grammarAccess.getInstanceAccess().getLeftParenthesisKeyword_4_0());
			}
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getInstanceAccess().getInstanceParametersInstanceParameterParserRuleCall_4_1_0_0());
						}
						lv_instanceParameters_5_0=ruleInstanceParameter
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getInstanceRule());
							}
							add(
								$current,
								"instanceParameters",
								lv_instanceParameters_5_0,
								"org.eclipse.poosl.xtext.Poosl.InstanceParameter");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					otherlv_6=','
					{
						newLeafNode(otherlv_6, grammarAccess.getInstanceAccess().getCommaKeyword_4_1_1_0());
					}
					(
						(
							{
								newCompositeNode(grammarAccess.getInstanceAccess().getInstanceParametersInstanceParameterParserRuleCall_4_1_1_1_0());
							}
							lv_instanceParameters_7_0=ruleInstanceParameter
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getInstanceRule());
								}
								add(
									$current,
									"instanceParameters",
									lv_instanceParameters_7_0,
									"org.eclipse.poosl.xtext.Poosl.InstanceParameter");
								afterParserOrEnumRuleCall();
							}
						)
					)
				)*
			)?
			otherlv_8=')'
			{
				newLeafNode(otherlv_8, grammarAccess.getInstanceAccess().getRightParenthesisKeyword_4_2());
			}
		)?
	)
;

// Entry rule entryRuleInstanceParameter
entryRuleInstanceParameter returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getInstanceParameterRule()); }
	iv_ruleInstanceParameter=ruleInstanceParameter
	{ $current=$iv_ruleInstanceParameter.current; }
	EOF;

// Rule InstanceParameter
ruleInstanceParameter returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					newCompositeNode(grammarAccess.getInstanceParameterAccess().getParameterIDENTIFIERParserRuleCall_0_0());
				}
				lv_parameter_0_0=ruleIDENTIFIER
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getInstanceParameterRule());
					}
					set(
						$current,
						"parameter",
						lv_parameter_0_0,
						"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_1=':='
		{
			newLeafNode(otherlv_1, grammarAccess.getInstanceParameterAccess().getColonEqualsSignKeyword_1());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getInstanceParameterAccess().getExpressionExpressionParserRuleCall_2_0());
				}
				lv_expression_2_0=ruleExpression
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getInstanceParameterRule());
					}
					set(
						$current,
						"expression",
						lv_expression_2_0,
						"org.eclipse.poosl.xtext.Poosl.Expression");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRuleChannel
entryRuleChannel returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getChannelRule()); }
	iv_ruleChannel=ruleChannel
	{ $current=$iv_ruleChannel.current; }
	EOF;

// Rule Channel
ruleChannel returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getChannelAccess().getChannelAction_0(),
					$current);
			}
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getChannelAccess().getAnnotationsAnnotationParserRuleCall_1_0());
				}
				lv_annotations_1_0=ruleAnnotation
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getChannelRule());
					}
					add(
						$current,
						"annotations",
						lv_annotations_1_0,
						"org.eclipse.poosl.xtext.Poosl.Annotation");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		otherlv_2='{'
		{
			newLeafNode(otherlv_2, grammarAccess.getChannelAccess().getLeftCurlyBracketKeyword_2());
		}
		(
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getChannelAccess().getInstancePortsInstancePortParserRuleCall_3_0_0_0());
						}
						lv_instancePorts_3_0=ruleInstancePort
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getChannelRule());
							}
							add(
								$current,
								"instancePorts",
								lv_instancePorts_3_0,
								"org.eclipse.poosl.xtext.Poosl.InstancePort");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					otherlv_4=','
					{
						newLeafNode(otherlv_4, grammarAccess.getChannelAccess().getCommaKeyword_3_0_1_0());
					}
					(
						(
							{
								newCompositeNode(grammarAccess.getChannelAccess().getInstancePortsInstancePortParserRuleCall_3_0_1_1_0());
							}
							lv_instancePorts_5_0=ruleInstancePort
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getChannelRule());
								}
								add(
									$current,
									"instancePorts",
									lv_instancePorts_5_0,
									"org.eclipse.poosl.xtext.Poosl.InstancePort");
								afterParserOrEnumRuleCall();
							}
						)
					)
				)*
				(
					otherlv_6=','
					{
						newLeafNode(otherlv_6, grammarAccess.getChannelAccess().getCommaKeyword_3_0_2_0());
					}
					(
						(
							{
								if ($current==null) {
									$current = createModelElement(grammarAccess.getChannelRule());
								}
							}
							{
								newCompositeNode(grammarAccess.getChannelAccess().getExternalPortPortCrossReference_3_0_2_1_0());
							}
							ruleIDENTIFIER
							{
								afterParserOrEnumRuleCall();
							}
						)
					)
					(
						otherlv_8=','
						{
							newLeafNode(otherlv_8, grammarAccess.getChannelAccess().getCommaKeyword_3_0_2_2_0());
						}
						(
							(
								{
									newCompositeNode(grammarAccess.getChannelAccess().getInstancePortsInstancePortParserRuleCall_3_0_2_2_1_0());
								}
								lv_instancePorts_9_0=ruleInstancePort
								{
									if ($current==null) {
										$current = createModelElementForParent(grammarAccess.getChannelRule());
									}
									add(
										$current,
										"instancePorts",
										lv_instancePorts_9_0,
										"org.eclipse.poosl.xtext.Poosl.InstancePort");
									afterParserOrEnumRuleCall();
								}
							)
						)
					)*
				)?
			)
			    |
			(
				(
					(
						{
							if ($current==null) {
								$current = createModelElement(grammarAccess.getChannelRule());
							}
						}
						{
							newCompositeNode(grammarAccess.getChannelAccess().getExternalPortPortCrossReference_3_1_0_0());
						}
						ruleIDENTIFIER
						{
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					otherlv_11=','
					{
						newLeafNode(otherlv_11, grammarAccess.getChannelAccess().getCommaKeyword_3_1_1_0());
					}
					(
						(
							{
								newCompositeNode(grammarAccess.getChannelAccess().getInstancePortsInstancePortParserRuleCall_3_1_1_1_0());
							}
							lv_instancePorts_12_0=ruleInstancePort
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getChannelRule());
								}
								add(
									$current,
									"instancePorts",
									lv_instancePorts_12_0,
									"org.eclipse.poosl.xtext.Poosl.InstancePort");
								afterParserOrEnumRuleCall();
							}
						)
					)
				)*
			)
		)?
		otherlv_13='}'
		{
			newLeafNode(otherlv_13, grammarAccess.getChannelAccess().getRightCurlyBracketKeyword_4());
		}
	)
;

// Entry rule entryRuleInstancePort
entryRuleInstancePort returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getInstancePortRule()); }
	iv_ruleInstancePort=ruleInstancePort
	{ $current=$iv_ruleInstancePort.current; }
	EOF;

// Rule InstancePort
ruleInstancePort returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getInstancePortRule());
					}
				}
				{
					newCompositeNode(grammarAccess.getInstancePortAccess().getInstanceInstanceCrossReference_0_0());
				}
				ruleIDENTIFIER
				{
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_1='.'
		{
			newLeafNode(otherlv_1, grammarAccess.getInstancePortAccess().getFullStopKeyword_1());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getInstancePortAccess().getPortPortReferenceParserRuleCall_2_0());
				}
				lv_port_2_0=rulePortReference
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getInstancePortRule());
					}
					set(
						$current,
						"port",
						lv_port_2_0,
						"org.eclipse.poosl.xtext.Poosl.PortReference");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRuleExpression
entryRuleExpression returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getExpressionRule()); }
	iv_ruleExpression=ruleExpression
	{ $current=$iv_ruleExpression.current; }
	EOF;

// Rule Expression
ruleExpression returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getExpressionAccess().getExpressionSingleParserRuleCall_0());
		}
		this_ExpressionSingle_0=ruleExpressionSingle
		{
			$current = $this_ExpressionSingle_0.current;
			afterParserOrEnumRuleCall();
		}
		(
			(
				{
					$current = forceCreateModelElementAndAdd(
						grammarAccess.getExpressionAccess().getExpressionSequenceExpressionsAction_1_0(),
						$current);
				}
			)
			(
				otherlv_2=';'
				{
					newLeafNode(otherlv_2, grammarAccess.getExpressionAccess().getSemicolonKeyword_1_1_0());
				}
				(
					(
						{
							newCompositeNode(grammarAccess.getExpressionAccess().getExpressionsExpressionSingleParserRuleCall_1_1_1_0());
						}
						lv_expressions_3_0=ruleExpressionSingle
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getExpressionRule());
							}
							add(
								$current,
								"expressions",
								lv_expressions_3_0,
								"org.eclipse.poosl.xtext.Poosl.ExpressionSingle");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)+
		)?
	)
;

// Entry rule entryRuleExpressionSingle
entryRuleExpressionSingle returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getExpressionSingleRule()); }
	iv_ruleExpressionSingle=ruleExpressionSingle
	{ $current=$iv_ruleExpressionSingle.current; }
	EOF;

// Rule ExpressionSingle
ruleExpressionSingle returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	{
		newCompositeNode(grammarAccess.getExpressionSingleAccess().getExpressionLevel1ParserRuleCall());
	}
	this_ExpressionLevel1_0=ruleExpressionLevel1
	{
		$current = $this_ExpressionLevel1_0.current;
		afterParserOrEnumRuleCall();
	}
;

// Entry rule entryRuleExpressionLevel1
entryRuleExpressionLevel1 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getExpressionLevel1Rule()); }
	iv_ruleExpressionLevel1=ruleExpressionLevel1
	{ $current=$iv_ruleExpressionLevel1.current; }
	EOF;

// Rule ExpressionLevel1
ruleExpressionLevel1 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getExpressionLevel1Access().getExpressionLevel2ParserRuleCall_0());
		}
		this_ExpressionLevel2_0=ruleExpressionLevel2
		{
			$current = $this_ExpressionLevel2_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		(
			(
				{
					$current = forceCreateModelElement(
						grammarAccess.getExpressionLevel1Access().getAssignmentExpressionAction_1_0(),
						$current);
				}
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getExpressionLevel1Access().getVariableIDENTIFIERParserRuleCall_1_1_0());
					}
					lv_variable_2_0=ruleIDENTIFIER
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getExpressionLevel1Rule());
						}
						set(
							$current,
							"variable",
							lv_variable_2_0,
							"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
						afterParserOrEnumRuleCall();
					}
				)
			)
			otherlv_3=':='
			{
				newLeafNode(otherlv_3, grammarAccess.getExpressionLevel1Access().getColonEqualsSignKeyword_1_2());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getExpressionLevel1Access().getExpressionExpressionLevel1ParserRuleCall_1_3_0());
					}
					lv_expression_4_0=ruleExpressionLevel1
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getExpressionLevel1Rule());
						}
						set(
							$current,
							"expression",
							lv_expression_4_0,
							"org.eclipse.poosl.xtext.Poosl.ExpressionLevel1");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)
		    |
		(
			(
				{
					$current = forceCreateModelElement(
						grammarAccess.getExpressionLevel1Access().getReturnExpressionAction_2_0(),
						$current);
				}
			)
			otherlv_6='return'
			{
				newLeafNode(otherlv_6, grammarAccess.getExpressionLevel1Access().getReturnKeyword_2_1());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getExpressionLevel1Access().getExpressionExpressionLevel1ParserRuleCall_2_2_0());
					}
					lv_expression_7_0=ruleExpressionLevel1
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getExpressionLevel1Rule());
						}
						set(
							$current,
							"expression",
							lv_expression_7_0,
							"org.eclipse.poosl.xtext.Poosl.ExpressionLevel1");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)
	)
;

// Entry rule entryRuleExpressionLevel2
entryRuleExpressionLevel2 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getExpressionLevel2Rule()); }
	iv_ruleExpressionLevel2=ruleExpressionLevel2
	{ $current=$iv_ruleExpressionLevel2.current; }
	EOF;

// Rule ExpressionLevel2
ruleExpressionLevel2 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getExpressionLevel2Access().getExpressionLevel3ParserRuleCall_0());
		}
		this_ExpressionLevel3_0=ruleExpressionLevel3
		{
			$current = $this_ExpressionLevel3_0.current;
			afterParserOrEnumRuleCall();
		}
		(
			(
				{
					$current = forceCreateModelElementAndSet(
						grammarAccess.getExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_1_0(),
						$current);
				}
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getExpressionLevel2Access().getNameBinaryOperatorLevel2EnumRuleCall_1_1_0());
					}
					lv_name_2_0=ruleBinaryOperatorLevel2
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getExpressionLevel2Rule());
						}
						set(
							$current,
							"name",
							lv_name_2_0,
							"org.eclipse.poosl.xtext.Poosl.BinaryOperatorLevel2");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getExpressionLevel2Access().getRightOperandExpressionLevel3ParserRuleCall_1_2_0());
					}
					lv_rightOperand_3_0=ruleExpressionLevel3
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getExpressionLevel2Rule());
						}
						set(
							$current,
							"rightOperand",
							lv_rightOperand_3_0,
							"org.eclipse.poosl.xtext.Poosl.ExpressionLevel3");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
	)
;

// Entry rule entryRuleExpressionLevel3
entryRuleExpressionLevel3 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getExpressionLevel3Rule()); }
	iv_ruleExpressionLevel3=ruleExpressionLevel3
	{ $current=$iv_ruleExpressionLevel3.current; }
	EOF;

// Rule ExpressionLevel3
ruleExpressionLevel3 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getExpressionLevel3Access().getExpressionLevel4ParserRuleCall_0());
		}
		this_ExpressionLevel4_0=ruleExpressionLevel4
		{
			$current = $this_ExpressionLevel4_0.current;
			afterParserOrEnumRuleCall();
		}
		(
			(
				{
					$current = forceCreateModelElementAndSet(
						grammarAccess.getExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_1_0(),
						$current);
				}
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getExpressionLevel3Access().getNameBinaryOperatorLevel3EnumRuleCall_1_1_0());
					}
					lv_name_2_0=ruleBinaryOperatorLevel3
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getExpressionLevel3Rule());
						}
						set(
							$current,
							"name",
							lv_name_2_0,
							"org.eclipse.poosl.xtext.Poosl.BinaryOperatorLevel3");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getExpressionLevel3Access().getRightOperandExpressionLevel4ParserRuleCall_1_2_0());
					}
					lv_rightOperand_3_0=ruleExpressionLevel4
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getExpressionLevel3Rule());
						}
						set(
							$current,
							"rightOperand",
							lv_rightOperand_3_0,
							"org.eclipse.poosl.xtext.Poosl.ExpressionLevel4");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
	)
;

// Entry rule entryRuleExpressionLevel4
entryRuleExpressionLevel4 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getExpressionLevel4Rule()); }
	iv_ruleExpressionLevel4=ruleExpressionLevel4
	{ $current=$iv_ruleExpressionLevel4.current; }
	EOF;

// Rule ExpressionLevel4
ruleExpressionLevel4 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getExpressionLevel4Access().getExpressionLevel5ParserRuleCall_0());
		}
		this_ExpressionLevel5_0=ruleExpressionLevel5
		{
			$current = $this_ExpressionLevel5_0.current;
			afterParserOrEnumRuleCall();
		}
		(
			(
				{
					$current = forceCreateModelElementAndSet(
						grammarAccess.getExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_0(),
						$current);
				}
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getExpressionLevel4Access().getNameBinaryOperatorLevel4EnumRuleCall_1_1_0());
					}
					lv_name_2_0=ruleBinaryOperatorLevel4
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getExpressionLevel4Rule());
						}
						set(
							$current,
							"name",
							lv_name_2_0,
							"org.eclipse.poosl.xtext.Poosl.BinaryOperatorLevel4");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getExpressionLevel4Access().getRightOperandExpressionLevel5ParserRuleCall_1_2_0());
					}
					lv_rightOperand_3_0=ruleExpressionLevel5
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getExpressionLevel4Rule());
						}
						set(
							$current,
							"rightOperand",
							lv_rightOperand_3_0,
							"org.eclipse.poosl.xtext.Poosl.ExpressionLevel5");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
	)
;

// Entry rule entryRuleExpressionLevel5
entryRuleExpressionLevel5 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getExpressionLevel5Rule()); }
	iv_ruleExpressionLevel5=ruleExpressionLevel5
	{ $current=$iv_ruleExpressionLevel5.current; }
	EOF;

// Rule ExpressionLevel5
ruleExpressionLevel5 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getExpressionLevel5Access().getExpressionLevel6ParserRuleCall_0());
		}
		this_ExpressionLevel6_0=ruleExpressionLevel6
		{
			$current = $this_ExpressionLevel6_0.current;
			afterParserOrEnumRuleCall();
		}
		(
			(
				{
					$current = forceCreateModelElementAndSet(
						grammarAccess.getExpressionLevel5Access().getDataMethodCallExpressionTargetAction_1_0(),
						$current);
				}
			)
			(
				(
					lv_onSuperClass_2_0='^'
					{
						newLeafNode(lv_onSuperClass_2_0, grammarAccess.getExpressionLevel5Access().getOnSuperClassCircumflexAccentKeyword_1_1_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getExpressionLevel5Rule());
						}
						setWithLastConsumed($current, "onSuperClass", true, "^");
					}
				)
			)?
			(
				(
					{
						newCompositeNode(grammarAccess.getExpressionLevel5Access().getNameIDENTIFIERParserRuleCall_1_2_0());
					}
					lv_name_3_0=ruleIDENTIFIER
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getExpressionLevel5Rule());
						}
						set(
							$current,
							"name",
							lv_name_3_0,
							"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				otherlv_4='('
				{
					newLeafNode(otherlv_4, grammarAccess.getExpressionLevel5Access().getLeftParenthesisKeyword_1_3_0());
				}
				(
					(
						(
							{
								newCompositeNode(grammarAccess.getExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_0_0());
							}
							lv_arguments_5_0=ruleExpression
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getExpressionLevel5Rule());
								}
								add(
									$current,
									"arguments",
									lv_arguments_5_0,
									"org.eclipse.poosl.xtext.Poosl.Expression");
								afterParserOrEnumRuleCall();
							}
						)
					)
					(
						otherlv_6=','
						{
							newLeafNode(otherlv_6, grammarAccess.getExpressionLevel5Access().getCommaKeyword_1_3_1_1_0());
						}
						(
							(
								{
									newCompositeNode(grammarAccess.getExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_1_1_0());
								}
								lv_arguments_7_0=ruleExpression
								{
									if ($current==null) {
										$current = createModelElementForParent(grammarAccess.getExpressionLevel5Rule());
									}
									add(
										$current,
										"arguments",
										lv_arguments_7_0,
										"org.eclipse.poosl.xtext.Poosl.Expression");
									afterParserOrEnumRuleCall();
								}
							)
						)
					)*
				)?
				otherlv_8=')'
				{
					newLeafNode(otherlv_8, grammarAccess.getExpressionLevel5Access().getRightParenthesisKeyword_1_3_2());
				}
			)?
		)*
	)
;

// Entry rule entryRuleExpressionLevel6
entryRuleExpressionLevel6 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getExpressionLevel6Rule()); }
	iv_ruleExpressionLevel6=ruleExpressionLevel6
	{ $current=$iv_ruleExpressionLevel6.current; }
	EOF;

// Rule ExpressionLevel6
ruleExpressionLevel6 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(ruleExpressionLevel7)=>
			{
				newCompositeNode(grammarAccess.getExpressionLevel6Access().getExpressionLevel7ParserRuleCall_0());
			}
			this_ExpressionLevel7_0=ruleExpressionLevel7
			{
				$current = $this_ExpressionLevel7_0.current;
				afterParserOrEnumRuleCall();
			}
		)
		    |
		{
			newCompositeNode(grammarAccess.getExpressionLevel6Access().getUnaryOperatorExpressionParserRuleCall_1());
		}
		this_UnaryOperatorExpression_1=ruleUnaryOperatorExpression
		{
			$current = $this_UnaryOperatorExpression_1.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getExpressionLevel6Access().getIfExpressionParserRuleCall_2());
		}
		this_IfExpression_2=ruleIfExpression
		{
			$current = $this_IfExpression_2.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getExpressionLevel6Access().getWhileExpressionParserRuleCall_3());
		}
		this_WhileExpression_3=ruleWhileExpression
		{
			$current = $this_WhileExpression_3.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getExpressionLevel6Access().getSwitchExpressionParserRuleCall_4());
		}
		this_SwitchExpression_4=ruleSwitchExpression
		{
			$current = $this_SwitchExpression_4.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleUnaryOperatorExpression
entryRuleUnaryOperatorExpression returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getUnaryOperatorExpressionRule()); }
	iv_ruleUnaryOperatorExpression=ruleUnaryOperatorExpression
	{ $current=$iv_ruleUnaryOperatorExpression.current; }
	EOF;

// Rule UnaryOperatorExpression
ruleUnaryOperatorExpression returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					newCompositeNode(grammarAccess.getUnaryOperatorExpressionAccess().getNameOperatorUnaryEnumRuleCall_0_0());
				}
				lv_name_0_0=ruleOperatorUnary
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getUnaryOperatorExpressionRule());
					}
					set(
						$current,
						"name",
						lv_name_0_0,
						"org.eclipse.poosl.xtext.Poosl.OperatorUnary");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getUnaryOperatorExpressionAccess().getOperandExpressionLevel7ParserRuleCall_1_0());
				}
				lv_operand_1_0=ruleExpressionLevel7
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getUnaryOperatorExpressionRule());
					}
					set(
						$current,
						"operand",
						lv_operand_1_0,
						"org.eclipse.poosl.xtext.Poosl.ExpressionLevel7");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRuleIfExpression
entryRuleIfExpression returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getIfExpressionRule()); }
	iv_ruleIfExpression=ruleIfExpression
	{ $current=$iv_ruleIfExpression.current; }
	EOF;

// Rule IfExpression
ruleIfExpression returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='if'
		{
			newLeafNode(otherlv_0, grammarAccess.getIfExpressionAccess().getIfKeyword_0());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getIfExpressionAccess().getConditionExpressionParserRuleCall_1_0());
				}
				lv_condition_1_0=ruleExpression
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getIfExpressionRule());
					}
					set(
						$current,
						"condition",
						lv_condition_1_0,
						"org.eclipse.poosl.xtext.Poosl.Expression");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_2='then'
		{
			newLeafNode(otherlv_2, grammarAccess.getIfExpressionAccess().getThenKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getIfExpressionAccess().getThenClauseExpressionParserRuleCall_3_0());
				}
				lv_thenClause_3_0=ruleExpression
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getIfExpressionRule());
					}
					set(
						$current,
						"thenClause",
						lv_thenClause_3_0,
						"org.eclipse.poosl.xtext.Poosl.Expression");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_4='else'
			{
				newLeafNode(otherlv_4, grammarAccess.getIfExpressionAccess().getElseKeyword_4_0());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getIfExpressionAccess().getElseClauseExpressionParserRuleCall_4_1_0());
					}
					lv_elseClause_5_0=ruleExpression
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getIfExpressionRule());
						}
						set(
							$current,
							"elseClause",
							lv_elseClause_5_0,
							"org.eclipse.poosl.xtext.Poosl.Expression");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)?
		otherlv_6='fi'
		{
			newLeafNode(otherlv_6, grammarAccess.getIfExpressionAccess().getFiKeyword_5());
		}
	)
;

// Entry rule entryRuleWhileExpression
entryRuleWhileExpression returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getWhileExpressionRule()); }
	iv_ruleWhileExpression=ruleWhileExpression
	{ $current=$iv_ruleWhileExpression.current; }
	EOF;

// Rule WhileExpression
ruleWhileExpression returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='while'
		{
			newLeafNode(otherlv_0, grammarAccess.getWhileExpressionAccess().getWhileKeyword_0());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getWhileExpressionAccess().getConditionExpressionParserRuleCall_1_0());
				}
				lv_condition_1_0=ruleExpression
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getWhileExpressionRule());
					}
					set(
						$current,
						"condition",
						lv_condition_1_0,
						"org.eclipse.poosl.xtext.Poosl.Expression");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_2='do'
		{
			newLeafNode(otherlv_2, grammarAccess.getWhileExpressionAccess().getDoKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getWhileExpressionAccess().getBodyExpressionParserRuleCall_3_0());
				}
				lv_body_3_0=ruleExpression
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getWhileExpressionRule());
					}
					set(
						$current,
						"body",
						lv_body_3_0,
						"org.eclipse.poosl.xtext.Poosl.Expression");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_4='od'
		{
			newLeafNode(otherlv_4, grammarAccess.getWhileExpressionAccess().getOdKeyword_4());
		}
	)
;

// Entry rule entryRuleSwitchExpression
entryRuleSwitchExpression returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getSwitchExpressionRule()); }
	iv_ruleSwitchExpression=ruleSwitchExpression
	{ $current=$iv_ruleSwitchExpression.current; }
	EOF;

// Rule SwitchExpression
ruleSwitchExpression returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='switch'
		{
			newLeafNode(otherlv_0, grammarAccess.getSwitchExpressionAccess().getSwitchKeyword_0());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getSwitchExpressionAccess().getExpressionExpressionParserRuleCall_1_0());
				}
				lv_expression_1_0=ruleExpression
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getSwitchExpressionRule());
					}
					set(
						$current,
						"expression",
						lv_expression_1_0,
						"org.eclipse.poosl.xtext.Poosl.Expression");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_2='do'
		{
			newLeafNode(otherlv_2, grammarAccess.getSwitchExpressionAccess().getDoKeyword_2());
		}
		(
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getSwitchExpressionAccess().getCasesSwitchExpressionCaseParserRuleCall_3_0_0_0());
						}
						lv_cases_3_0=ruleSwitchExpressionCase
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getSwitchExpressionRule());
							}
							add(
								$current,
								"cases",
								lv_cases_3_0,
								"org.eclipse.poosl.xtext.Poosl.SwitchExpressionCase");
							afterParserOrEnumRuleCall();
						}
					)
				)+
				(
					otherlv_4='default'
					{
						newLeafNode(otherlv_4, grammarAccess.getSwitchExpressionAccess().getDefaultKeyword_3_0_1_0());
					}
					(
						(
							{
								newCompositeNode(grammarAccess.getSwitchExpressionAccess().getDefaultBodyExpressionParserRuleCall_3_0_1_1_0());
							}
							lv_defaultBody_5_0=ruleExpression
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getSwitchExpressionRule());
								}
								set(
									$current,
									"defaultBody",
									lv_defaultBody_5_0,
									"org.eclipse.poosl.xtext.Poosl.Expression");
								afterParserOrEnumRuleCall();
							}
						)
					)
				)?
			)
			    |
			(
				otherlv_6='default'
				{
					newLeafNode(otherlv_6, grammarAccess.getSwitchExpressionAccess().getDefaultKeyword_3_1_0());
				}
				(
					(
						{
							newCompositeNode(grammarAccess.getSwitchExpressionAccess().getDefaultBodyExpressionParserRuleCall_3_1_1_0());
						}
						lv_defaultBody_7_0=ruleExpression
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getSwitchExpressionRule());
							}
							set(
								$current,
								"defaultBody",
								lv_defaultBody_7_0,
								"org.eclipse.poosl.xtext.Poosl.Expression");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)
		)
		otherlv_8='od'
		{
			newLeafNode(otherlv_8, grammarAccess.getSwitchExpressionAccess().getOdKeyword_4());
		}
	)
;

// Entry rule entryRuleSwitchExpressionCase
entryRuleSwitchExpressionCase returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getSwitchExpressionCaseRule()); }
	iv_ruleSwitchExpressionCase=ruleSwitchExpressionCase
	{ $current=$iv_ruleSwitchExpressionCase.current; }
	EOF;

// Rule SwitchExpressionCase
ruleSwitchExpressionCase returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='case'
		{
			newLeafNode(otherlv_0, grammarAccess.getSwitchExpressionCaseAccess().getCaseKeyword_0());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getSwitchExpressionCaseAccess().getValueExpressionParserRuleCall_1_0());
				}
				lv_value_1_0=ruleExpression
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getSwitchExpressionCaseRule());
					}
					set(
						$current,
						"value",
						lv_value_1_0,
						"org.eclipse.poosl.xtext.Poosl.Expression");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_2='then'
		{
			newLeafNode(otherlv_2, grammarAccess.getSwitchExpressionCaseAccess().getThenKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getSwitchExpressionCaseAccess().getBodyExpressionParserRuleCall_3_0());
				}
				lv_body_3_0=ruleExpression
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getSwitchExpressionCaseRule());
					}
					set(
						$current,
						"body",
						lv_body_3_0,
						"org.eclipse.poosl.xtext.Poosl.Expression");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRuleExpressionLevel7
entryRuleExpressionLevel7 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getExpressionLevel7Rule()); }
	iv_ruleExpressionLevel7=ruleExpressionLevel7
	{ $current=$iv_ruleExpressionLevel7.current; }
	EOF;

// Rule ExpressionLevel7
ruleExpressionLevel7 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getExpressionLevel7Access().getCurrentTimeExpressionParserRuleCall_0());
		}
		this_CurrentTimeExpression_0=ruleCurrentTimeExpression
		{
			$current = $this_CurrentTimeExpression_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getExpressionLevel7Access().getSelfExpressionParserRuleCall_1());
		}
		this_SelfExpression_1=ruleSelfExpression
		{
			$current = $this_SelfExpression_1.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getExpressionLevel7Access().getExpressionConstantParserRuleCall_2());
		}
		this_ExpressionConstant_2=ruleExpressionConstant
		{
			$current = $this_ExpressionConstant_2.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getExpressionLevel7Access().getNewExpressionParserRuleCall_3());
		}
		this_NewExpression_3=ruleNewExpression
		{
			$current = $this_NewExpression_3.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getExpressionLevel7Access().getVariableExpressionParserRuleCall_4());
		}
		this_VariableExpression_4=ruleVariableExpression
		{
			$current = $this_VariableExpression_4.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getExpressionLevel7Access().getExpressionSequenceRoundBracketParserRuleCall_5());
		}
		this_ExpressionSequenceRoundBracket_5=ruleExpressionSequenceRoundBracket
		{
			$current = $this_ExpressionSequenceRoundBracket_5.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleCurrentTimeExpression
entryRuleCurrentTimeExpression returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getCurrentTimeExpressionRule()); }
	iv_ruleCurrentTimeExpression=ruleCurrentTimeExpression
	{ $current=$iv_ruleCurrentTimeExpression.current; }
	EOF;

// Rule CurrentTimeExpression
ruleCurrentTimeExpression returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getCurrentTimeExpressionAccess().getCurrentTimeExpressionAction_0(),
					$current);
			}
		)
		otherlv_1='currentTime'
		{
			newLeafNode(otherlv_1, grammarAccess.getCurrentTimeExpressionAccess().getCurrentTimeKeyword_1());
		}
	)
;

// Entry rule entryRuleSelfExpression
entryRuleSelfExpression returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getSelfExpressionRule()); }
	iv_ruleSelfExpression=ruleSelfExpression
	{ $current=$iv_ruleSelfExpression.current; }
	EOF;

// Rule SelfExpression
ruleSelfExpression returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getSelfExpressionAccess().getSelfExpressionAction_0(),
					$current);
			}
		)
		otherlv_1='self'
		{
			newLeafNode(otherlv_1, grammarAccess.getSelfExpressionAccess().getSelfKeyword_1());
		}
	)
;

// Entry rule entryRuleExpressionConstant
entryRuleExpressionConstant returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getExpressionConstantRule()); }
	iv_ruleExpressionConstant=ruleExpressionConstant
	{ $current=$iv_ruleExpressionConstant.current; }
	EOF;

// Rule ExpressionConstant
ruleExpressionConstant returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getExpressionConstantAccess().getBooleanConstantParserRuleCall_0());
		}
		this_BooleanConstant_0=ruleBooleanConstant
		{
			$current = $this_BooleanConstant_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getExpressionConstantAccess().getCharacterConstantParserRuleCall_1());
		}
		this_CharacterConstant_1=ruleCharacterConstant
		{
			$current = $this_CharacterConstant_1.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getExpressionConstantAccess().getFloatConstantParserRuleCall_2());
		}
		this_FloatConstant_2=ruleFloatConstant
		{
			$current = $this_FloatConstant_2.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getExpressionConstantAccess().getIntegerConstantParserRuleCall_3());
		}
		this_IntegerConstant_3=ruleIntegerConstant
		{
			$current = $this_IntegerConstant_3.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getExpressionConstantAccess().getNilConstantParserRuleCall_4());
		}
		this_NilConstant_4=ruleNilConstant
		{
			$current = $this_NilConstant_4.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getExpressionConstantAccess().getRealConstantParserRuleCall_5());
		}
		this_RealConstant_5=ruleRealConstant
		{
			$current = $this_RealConstant_5.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getExpressionConstantAccess().getStringConstantParserRuleCall_6());
		}
		this_StringConstant_6=ruleStringConstant
		{
			$current = $this_StringConstant_6.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getExpressionConstantAccess().getEnvironmentConstantParserRuleCall_7());
		}
		this_EnvironmentConstant_7=ruleEnvironmentConstant
		{
			$current = $this_EnvironmentConstant_7.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleBooleanConstant
entryRuleBooleanConstant returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getBooleanConstantRule()); }
	iv_ruleBooleanConstant=ruleBooleanConstant
	{ $current=$iv_ruleBooleanConstant.current; }
	EOF;

// Rule BooleanConstant
ruleBooleanConstant returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				lv_value_0_0='true'
				{
					newLeafNode(lv_value_0_0, grammarAccess.getBooleanConstantAccess().getValueTrueKeyword_0_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getBooleanConstantRule());
					}
					setWithLastConsumed($current, "value", lv_value_0_0, "true");
				}
			)
		)
		    |
		(
			(
				lv_value_1_0='false'
				{
					newLeafNode(lv_value_1_0, grammarAccess.getBooleanConstantAccess().getValueFalseKeyword_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getBooleanConstantRule());
					}
					setWithLastConsumed($current, "value", lv_value_1_0, "false");
				}
			)
		)
	)
;

// Entry rule entryRuleCharacterConstant
entryRuleCharacterConstant returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getCharacterConstantRule()); }
	iv_ruleCharacterConstant=ruleCharacterConstant
	{ $current=$iv_ruleCharacterConstant.current; }
	EOF;

// Rule CharacterConstant
ruleCharacterConstant returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			lv_value_0_0=RULE_CHARACTER
			{
				newLeafNode(lv_value_0_0, grammarAccess.getCharacterConstantAccess().getValueCHARACTERTerminalRuleCall_0());
			}
			{
				if ($current==null) {
					$current = createModelElement(grammarAccess.getCharacterConstantRule());
				}
				setWithLastConsumed(
					$current,
					"value",
					lv_value_0_0,
					"org.eclipse.poosl.xtext.Poosl.CHARACTER");
			}
		)
	)
;

// Entry rule entryRuleFloatConstant
entryRuleFloatConstant returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getFloatConstantRule()); }
	iv_ruleFloatConstant=ruleFloatConstant
	{ $current=$iv_ruleFloatConstant.current; }
	EOF;

// Rule FloatConstant
ruleFloatConstant returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				newCompositeNode(grammarAccess.getFloatConstantAccess().getValueFLOATParserRuleCall_0());
			}
			lv_value_0_0=ruleFLOAT
			{
				if ($current==null) {
					$current = createModelElementForParent(grammarAccess.getFloatConstantRule());
				}
				set(
					$current,
					"value",
					lv_value_0_0,
					"org.eclipse.poosl.xtext.Poosl.FLOAT");
				afterParserOrEnumRuleCall();
			}
		)
	)
;

// Entry rule entryRuleIntegerConstant
entryRuleIntegerConstant returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getIntegerConstantRule()); }
	iv_ruleIntegerConstant=ruleIntegerConstant
	{ $current=$iv_ruleIntegerConstant.current; }
	EOF;

// Rule IntegerConstant
ruleIntegerConstant returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				newCompositeNode(grammarAccess.getIntegerConstantAccess().getValueINTEGERParserRuleCall_0());
			}
			lv_value_0_0=ruleINTEGER
			{
				if ($current==null) {
					$current = createModelElementForParent(grammarAccess.getIntegerConstantRule());
				}
				set(
					$current,
					"value",
					lv_value_0_0,
					"org.eclipse.poosl.xtext.Poosl.INTEGER");
				afterParserOrEnumRuleCall();
			}
		)
	)
;

// Entry rule entryRuleNilConstant
entryRuleNilConstant returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getNilConstantRule()); }
	iv_ruleNilConstant=ruleNilConstant
	{ $current=$iv_ruleNilConstant.current; }
	EOF;

// Rule NilConstant
ruleNilConstant returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getNilConstantAccess().getNilConstantAction_0(),
					$current);
			}
		)
		otherlv_1='nil'
		{
			newLeafNode(otherlv_1, grammarAccess.getNilConstantAccess().getNilKeyword_1());
		}
	)
;

// Entry rule entryRuleRealConstant
entryRuleRealConstant returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getRealConstantRule()); }
	iv_ruleRealConstant=ruleRealConstant
	{ $current=$iv_ruleRealConstant.current; }
	EOF;

// Rule RealConstant
ruleRealConstant returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				newCompositeNode(grammarAccess.getRealConstantAccess().getValueREALParserRuleCall_0());
			}
			lv_value_0_0=ruleREAL
			{
				if ($current==null) {
					$current = createModelElementForParent(grammarAccess.getRealConstantRule());
				}
				set(
					$current,
					"value",
					lv_value_0_0,
					"org.eclipse.poosl.xtext.Poosl.REAL");
				afterParserOrEnumRuleCall();
			}
		)
	)
;

// Entry rule entryRuleStringConstant
entryRuleStringConstant returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getStringConstantRule()); }
	iv_ruleStringConstant=ruleStringConstant
	{ $current=$iv_ruleStringConstant.current; }
	EOF;

// Rule StringConstant
ruleStringConstant returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			lv_value_0_0=RULE_POOSL_STRING
			{
				newLeafNode(lv_value_0_0, grammarAccess.getStringConstantAccess().getValuePOOSL_STRINGTerminalRuleCall_0());
			}
			{
				if ($current==null) {
					$current = createModelElement(grammarAccess.getStringConstantRule());
				}
				setWithLastConsumed(
					$current,
					"value",
					lv_value_0_0,
					"org.eclipse.poosl.xtext.Poosl.POOSL_STRING");
			}
		)
	)
;

// Entry rule entryRuleEnvironmentConstant
entryRuleEnvironmentConstant returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getEnvironmentConstantRule()); }
	iv_ruleEnvironmentConstant=ruleEnvironmentConstant
	{ $current=$iv_ruleEnvironmentConstant.current; }
	EOF;

// Rule EnvironmentConstant
ruleEnvironmentConstant returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			lv_value_0_0=RULE_ENVIRONMENT_VARIABLE_NAME
			{
				newLeafNode(lv_value_0_0, grammarAccess.getEnvironmentConstantAccess().getValueENVIRONMENT_VARIABLE_NAMETerminalRuleCall_0());
			}
			{
				if ($current==null) {
					$current = createModelElement(grammarAccess.getEnvironmentConstantRule());
				}
				setWithLastConsumed(
					$current,
					"value",
					lv_value_0_0,
					"org.eclipse.poosl.xtext.Poosl.ENVIRONMENT_VARIABLE_NAME");
			}
		)
	)
;

// Entry rule entryRuleOutputVariable
entryRuleOutputVariable returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getOutputVariableRule()); }
	iv_ruleOutputVariable=ruleOutputVariable
	{ $current=$iv_ruleOutputVariable.current; }
	EOF;

// Rule OutputVariable
ruleOutputVariable returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				newCompositeNode(grammarAccess.getOutputVariableAccess().getVariableIDENTIFIERParserRuleCall_0());
			}
			lv_variable_0_0=ruleIDENTIFIER
			{
				if ($current==null) {
					$current = createModelElementForParent(grammarAccess.getOutputVariableRule());
				}
				set(
					$current,
					"variable",
					lv_variable_0_0,
					"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
				afterParserOrEnumRuleCall();
			}
		)
	)
;

// Entry rule entryRuleVariableExpression
entryRuleVariableExpression returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getVariableExpressionRule()); }
	iv_ruleVariableExpression=ruleVariableExpression
	{ $current=$iv_ruleVariableExpression.current; }
	EOF;

// Rule VariableExpression
ruleVariableExpression returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				newCompositeNode(grammarAccess.getVariableExpressionAccess().getVariableIDENTIFIERParserRuleCall_0());
			}
			lv_variable_0_0=ruleIDENTIFIER
			{
				if ($current==null) {
					$current = createModelElementForParent(grammarAccess.getVariableExpressionRule());
				}
				set(
					$current,
					"variable",
					lv_variable_0_0,
					"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
				afterParserOrEnumRuleCall();
			}
		)
	)
;

// Entry rule entryRuleNewExpression
entryRuleNewExpression returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getNewExpressionRule()); }
	iv_ruleNewExpression=ruleNewExpression
	{ $current=$iv_ruleNewExpression.current; }
	EOF;

// Rule NewExpression
ruleNewExpression returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='new'
		{
			newLeafNode(otherlv_0, grammarAccess.getNewExpressionAccess().getNewKeyword_0());
		}
		otherlv_1='('
		{
			newLeafNode(otherlv_1, grammarAccess.getNewExpressionAccess().getLeftParenthesisKeyword_1());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getNewExpressionAccess().getDataClassIDENTIFIERParserRuleCall_2_0());
				}
				lv_dataClass_2_0=ruleIDENTIFIER
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getNewExpressionRule());
					}
					set(
						$current,
						"dataClass",
						lv_dataClass_2_0,
						"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_3=')'
		{
			newLeafNode(otherlv_3, grammarAccess.getNewExpressionAccess().getRightParenthesisKeyword_3());
		}
	)
;

// Entry rule entryRuleExpressionSequenceRoundBracket
entryRuleExpressionSequenceRoundBracket returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getExpressionSequenceRoundBracketRule()); }
	iv_ruleExpressionSequenceRoundBracket=ruleExpressionSequenceRoundBracket
	{ $current=$iv_ruleExpressionSequenceRoundBracket.current; }
	EOF;

// Rule ExpressionSequenceRoundBracket
ruleExpressionSequenceRoundBracket returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='('
		{
			newLeafNode(otherlv_0, grammarAccess.getExpressionSequenceRoundBracketAccess().getLeftParenthesisKeyword_0());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getExpressionSequenceRoundBracketAccess().getExpressionsExpressionSingleParserRuleCall_1_0());
				}
				lv_expressions_1_0=ruleExpressionSingle
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getExpressionSequenceRoundBracketRule());
					}
					add(
						$current,
						"expressions",
						lv_expressions_1_0,
						"org.eclipse.poosl.xtext.Poosl.ExpressionSingle");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_2=';'
			{
				newLeafNode(otherlv_2, grammarAccess.getExpressionSequenceRoundBracketAccess().getSemicolonKeyword_2_0());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getExpressionSequenceRoundBracketAccess().getExpressionsExpressionSingleParserRuleCall_2_1_0());
					}
					lv_expressions_3_0=ruleExpressionSingle
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getExpressionSequenceRoundBracketRule());
						}
						add(
							$current,
							"expressions",
							lv_expressions_3_0,
							"org.eclipse.poosl.xtext.Poosl.ExpressionSingle");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
		otherlv_4=')'
		{
			newLeafNode(otherlv_4, grammarAccess.getExpressionSequenceRoundBracketAccess().getRightParenthesisKeyword_3());
		}
	)
;

// Entry rule entryRuleIDStartWithinStatementExpressionSingle
entryRuleIDStartWithinStatementExpressionSingle returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionSingleRule()); }
	iv_ruleIDStartWithinStatementExpressionSingle=ruleIDStartWithinStatementExpressionSingle
	{ $current=$iv_ruleIDStartWithinStatementExpressionSingle.current; }
	EOF;

// Rule IDStartWithinStatementExpressionSingle
ruleIDStartWithinStatementExpressionSingle returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	{
		newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionSingleAccess().getIDStartWithinStatementExpressionLevel1ParserRuleCall());
	}
	this_IDStartWithinStatementExpressionLevel1_0=ruleIDStartWithinStatementExpressionLevel1
	{
		$current = $this_IDStartWithinStatementExpressionLevel1_0.current;
		afterParserOrEnumRuleCall();
	}
;

// Entry rule entryRuleIDStartWithinStatementExpressionLevel1
entryRuleIDStartWithinStatementExpressionLevel1 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionLevel1Rule()); }
	iv_ruleIDStartWithinStatementExpressionLevel1=ruleIDStartWithinStatementExpressionLevel1
	{ $current=$iv_ruleIDStartWithinStatementExpressionLevel1.current; }
	EOF;

// Rule IDStartWithinStatementExpressionLevel1
ruleIDStartWithinStatementExpressionLevel1 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getIDStartWithinStatementExpressionLevel2ParserRuleCall_0());
		}
		this_IDStartWithinStatementExpressionLevel2_0=ruleIDStartWithinStatementExpressionLevel2
		{
			$current = $this_IDStartWithinStatementExpressionLevel2_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		(
			(
				{
					$current = forceCreateModelElement(
						grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getAssignmentExpressionAction_1_0(),
						$current);
				}
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getVariableIDENTIFIERParserRuleCall_1_1_0());
					}
					lv_variable_2_0=ruleIDENTIFIER
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getIDStartWithinStatementExpressionLevel1Rule());
						}
						set(
							$current,
							"variable",
							lv_variable_2_0,
							"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
						afterParserOrEnumRuleCall();
					}
				)
			)
			otherlv_3=':='
			{
				newLeafNode(otherlv_3, grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getColonEqualsSignKeyword_1_2());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionLevel1Access().getExpressionExpressionLevel1ParserRuleCall_1_3_0());
					}
					lv_expression_4_0=ruleExpressionLevel1
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getIDStartWithinStatementExpressionLevel1Rule());
						}
						set(
							$current,
							"expression",
							lv_expression_4_0,
							"org.eclipse.poosl.xtext.Poosl.ExpressionLevel1");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)
	)
;

// Entry rule entryRuleIDStartWithinStatementExpressionLevel2
entryRuleIDStartWithinStatementExpressionLevel2 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionLevel2Rule()); }
	iv_ruleIDStartWithinStatementExpressionLevel2=ruleIDStartWithinStatementExpressionLevel2
	{ $current=$iv_ruleIDStartWithinStatementExpressionLevel2.current; }
	EOF;

// Rule IDStartWithinStatementExpressionLevel2
ruleIDStartWithinStatementExpressionLevel2 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getIDStartWithinStatementExpressionLevel3ParserRuleCall_0());
		}
		this_IDStartWithinStatementExpressionLevel3_0=ruleIDStartWithinStatementExpressionLevel3
		{
			$current = $this_IDStartWithinStatementExpressionLevel3_0.current;
			afterParserOrEnumRuleCall();
		}
		(
			(
				{
					$current = forceCreateModelElementAndSet(
						grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_1_0(),
						$current);
				}
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getNameBinaryOperatorLevel2EnumRuleCall_1_1_0());
					}
					lv_name_2_0=ruleBinaryOperatorLevel2
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getIDStartWithinStatementExpressionLevel2Rule());
						}
						set(
							$current,
							"name",
							lv_name_2_0,
							"org.eclipse.poosl.xtext.Poosl.BinaryOperatorLevel2");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionLevel2Access().getRightOperandExpressionLevel3ParserRuleCall_1_2_0());
					}
					lv_rightOperand_3_0=ruleExpressionLevel3
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getIDStartWithinStatementExpressionLevel2Rule());
						}
						set(
							$current,
							"rightOperand",
							lv_rightOperand_3_0,
							"org.eclipse.poosl.xtext.Poosl.ExpressionLevel3");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
	)
;

// Entry rule entryRuleIDStartWithinStatementExpressionLevel3
entryRuleIDStartWithinStatementExpressionLevel3 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionLevel3Rule()); }
	iv_ruleIDStartWithinStatementExpressionLevel3=ruleIDStartWithinStatementExpressionLevel3
	{ $current=$iv_ruleIDStartWithinStatementExpressionLevel3.current; }
	EOF;

// Rule IDStartWithinStatementExpressionLevel3
ruleIDStartWithinStatementExpressionLevel3 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getIDStartWithinStatementExpressionLevel4ParserRuleCall_0());
		}
		this_IDStartWithinStatementExpressionLevel4_0=ruleIDStartWithinStatementExpressionLevel4
		{
			$current = $this_IDStartWithinStatementExpressionLevel4_0.current;
			afterParserOrEnumRuleCall();
		}
		(
			(
				{
					$current = forceCreateModelElementAndSet(
						grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_1_0(),
						$current);
				}
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getNameBinaryOperatorLevel3EnumRuleCall_1_1_0());
					}
					lv_name_2_0=ruleBinaryOperatorLevel3
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getIDStartWithinStatementExpressionLevel3Rule());
						}
						set(
							$current,
							"name",
							lv_name_2_0,
							"org.eclipse.poosl.xtext.Poosl.BinaryOperatorLevel3");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionLevel3Access().getRightOperandExpressionLevel4ParserRuleCall_1_2_0());
					}
					lv_rightOperand_3_0=ruleExpressionLevel4
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getIDStartWithinStatementExpressionLevel3Rule());
						}
						set(
							$current,
							"rightOperand",
							lv_rightOperand_3_0,
							"org.eclipse.poosl.xtext.Poosl.ExpressionLevel4");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
	)
;

// Entry rule entryRuleIDStartWithinStatementExpressionLevel4
entryRuleIDStartWithinStatementExpressionLevel4 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionLevel4Rule()); }
	iv_ruleIDStartWithinStatementExpressionLevel4=ruleIDStartWithinStatementExpressionLevel4
	{ $current=$iv_ruleIDStartWithinStatementExpressionLevel4.current; }
	EOF;

// Rule IDStartWithinStatementExpressionLevel4
ruleIDStartWithinStatementExpressionLevel4 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getIDStartWithinStatementExpressionLevel5ParserRuleCall_0());
		}
		this_IDStartWithinStatementExpressionLevel5_0=ruleIDStartWithinStatementExpressionLevel5
		{
			$current = $this_IDStartWithinStatementExpressionLevel5_0.current;
			afterParserOrEnumRuleCall();
		}
		(
			(
				{
					$current = forceCreateModelElementAndSet(
						grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_0(),
						$current);
				}
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getNameBinaryOperatorLevel4EnumRuleCall_1_1_0());
					}
					lv_name_2_0=ruleBinaryOperatorLevel4
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getIDStartWithinStatementExpressionLevel4Rule());
						}
						set(
							$current,
							"name",
							lv_name_2_0,
							"org.eclipse.poosl.xtext.Poosl.BinaryOperatorLevel4");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionLevel4Access().getRightOperandExpressionLevel5ParserRuleCall_1_2_0());
					}
					lv_rightOperand_3_0=ruleExpressionLevel5
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getIDStartWithinStatementExpressionLevel4Rule());
						}
						set(
							$current,
							"rightOperand",
							lv_rightOperand_3_0,
							"org.eclipse.poosl.xtext.Poosl.ExpressionLevel5");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
	)
;

// Entry rule entryRuleIDStartWithinStatementExpressionLevel5
entryRuleIDStartWithinStatementExpressionLevel5 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionLevel5Rule()); }
	iv_ruleIDStartWithinStatementExpressionLevel5=ruleIDStartWithinStatementExpressionLevel5
	{ $current=$iv_ruleIDStartWithinStatementExpressionLevel5.current; }
	EOF;

// Rule IDStartWithinStatementExpressionLevel5
ruleIDStartWithinStatementExpressionLevel5 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getVariableExpressionParserRuleCall_0());
		}
		this_VariableExpression_0=ruleVariableExpression
		{
			$current = $this_VariableExpression_0.current;
			afterParserOrEnumRuleCall();
		}
		(
			(
				{
					$current = forceCreateModelElementAndSet(
						grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getDataMethodCallExpressionTargetAction_1_0(),
						$current);
				}
			)
			(
				(
					lv_onSuperClass_2_0='^'
					{
						newLeafNode(lv_onSuperClass_2_0, grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getOnSuperClassCircumflexAccentKeyword_1_1_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getIDStartWithinStatementExpressionLevel5Rule());
						}
						setWithLastConsumed($current, "onSuperClass", true, "^");
					}
				)
			)?
			(
				(
					{
						newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getNameIDENTIFIERParserRuleCall_1_2_0());
					}
					lv_name_3_0=ruleIDENTIFIER
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getIDStartWithinStatementExpressionLevel5Rule());
						}
						set(
							$current,
							"name",
							lv_name_3_0,
							"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				otherlv_4='('
				{
					newLeafNode(otherlv_4, grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getLeftParenthesisKeyword_1_3_0());
				}
				(
					(
						(
							{
								newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_0_0());
							}
							lv_arguments_5_0=ruleExpression
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getIDStartWithinStatementExpressionLevel5Rule());
								}
								add(
									$current,
									"arguments",
									lv_arguments_5_0,
									"org.eclipse.poosl.xtext.Poosl.Expression");
								afterParserOrEnumRuleCall();
							}
						)
					)
					(
						otherlv_6=','
						{
							newLeafNode(otherlv_6, grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getCommaKeyword_1_3_1_1_0());
						}
						(
							(
								{
									newCompositeNode(grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_1_1_0());
								}
								lv_arguments_7_0=ruleExpression
								{
									if ($current==null) {
										$current = createModelElementForParent(grammarAccess.getIDStartWithinStatementExpressionLevel5Rule());
									}
									add(
										$current,
										"arguments",
										lv_arguments_7_0,
										"org.eclipse.poosl.xtext.Poosl.Expression");
									afterParserOrEnumRuleCall();
								}
							)
						)
					)*
				)?
				otherlv_8=')'
				{
					newLeafNode(otherlv_8, grammarAccess.getIDStartWithinStatementExpressionLevel5Access().getRightParenthesisKeyword_1_3_2());
				}
			)?
		)*
	)
;

// Entry rule entryRuleNonIDStartWithinStatementExpressionSingle
entryRuleNonIDStartWithinStatementExpressionSingle returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionSingleRule()); }
	iv_ruleNonIDStartWithinStatementExpressionSingle=ruleNonIDStartWithinStatementExpressionSingle
	{ $current=$iv_ruleNonIDStartWithinStatementExpressionSingle.current; }
	EOF;

// Rule NonIDStartWithinStatementExpressionSingle
ruleNonIDStartWithinStatementExpressionSingle returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	{
		newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionSingleAccess().getNonIDStartWithinStatementExpressionLevel1ParserRuleCall());
	}
	this_NonIDStartWithinStatementExpressionLevel1_0=ruleNonIDStartWithinStatementExpressionLevel1
	{
		$current = $this_NonIDStartWithinStatementExpressionLevel1_0.current;
		afterParserOrEnumRuleCall();
	}
;

// Entry rule entryRuleNonIDStartWithinStatementExpressionLevel1
entryRuleNonIDStartWithinStatementExpressionLevel1 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Rule()); }
	iv_ruleNonIDStartWithinStatementExpressionLevel1=ruleNonIDStartWithinStatementExpressionLevel1
	{ $current=$iv_ruleNonIDStartWithinStatementExpressionLevel1.current; }
	EOF;

// Rule NonIDStartWithinStatementExpressionLevel1
ruleNonIDStartWithinStatementExpressionLevel1 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Access().getNonIDStartWithinStatementExpressionLevel2ParserRuleCall_0());
		}
		this_NonIDStartWithinStatementExpressionLevel2_0=ruleNonIDStartWithinStatementExpressionLevel2
		{
			$current = $this_NonIDStartWithinStatementExpressionLevel2_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		(
			(
				{
					$current = forceCreateModelElement(
						grammarAccess.getNonIDStartWithinStatementExpressionLevel1Access().getReturnExpressionAction_1_0(),
						$current);
				}
			)
			otherlv_2='return'
			{
				newLeafNode(otherlv_2, grammarAccess.getNonIDStartWithinStatementExpressionLevel1Access().getReturnKeyword_1_1());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Access().getExpressionExpressionLevel1ParserRuleCall_1_2_0());
					}
					lv_expression_3_0=ruleExpressionLevel1
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getNonIDStartWithinStatementExpressionLevel1Rule());
						}
						set(
							$current,
							"expression",
							lv_expression_3_0,
							"org.eclipse.poosl.xtext.Poosl.ExpressionLevel1");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)
	)
;

// Entry rule entryRuleNonIDStartWithinStatementExpressionLevel2
entryRuleNonIDStartWithinStatementExpressionLevel2 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Rule()); }
	iv_ruleNonIDStartWithinStatementExpressionLevel2=ruleNonIDStartWithinStatementExpressionLevel2
	{ $current=$iv_ruleNonIDStartWithinStatementExpressionLevel2.current; }
	EOF;

// Rule NonIDStartWithinStatementExpressionLevel2
ruleNonIDStartWithinStatementExpressionLevel2 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getNonIDStartWithinStatementExpressionLevel3ParserRuleCall_0());
		}
		this_NonIDStartWithinStatementExpressionLevel3_0=ruleNonIDStartWithinStatementExpressionLevel3
		{
			$current = $this_NonIDStartWithinStatementExpressionLevel3_0.current;
			afterParserOrEnumRuleCall();
		}
		(
			(
				{
					$current = forceCreateModelElementAndSet(
						grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_1_0(),
						$current);
				}
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getNameBinaryOperatorLevel2EnumRuleCall_1_1_0());
					}
					lv_name_2_0=ruleBinaryOperatorLevel2
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Rule());
						}
						set(
							$current,
							"name",
							lv_name_2_0,
							"org.eclipse.poosl.xtext.Poosl.BinaryOperatorLevel2");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Access().getRightOperandExpressionLevel3ParserRuleCall_1_2_0());
					}
					lv_rightOperand_3_0=ruleExpressionLevel3
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getNonIDStartWithinStatementExpressionLevel2Rule());
						}
						set(
							$current,
							"rightOperand",
							lv_rightOperand_3_0,
							"org.eclipse.poosl.xtext.Poosl.ExpressionLevel3");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
	)
;

// Entry rule entryRuleNonIDStartWithinStatementExpressionLevel3
entryRuleNonIDStartWithinStatementExpressionLevel3 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Rule()); }
	iv_ruleNonIDStartWithinStatementExpressionLevel3=ruleNonIDStartWithinStatementExpressionLevel3
	{ $current=$iv_ruleNonIDStartWithinStatementExpressionLevel3.current; }
	EOF;

// Rule NonIDStartWithinStatementExpressionLevel3
ruleNonIDStartWithinStatementExpressionLevel3 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getNonIDStartWithinStatementExpressionLevel4ParserRuleCall_0());
		}
		this_NonIDStartWithinStatementExpressionLevel4_0=ruleNonIDStartWithinStatementExpressionLevel4
		{
			$current = $this_NonIDStartWithinStatementExpressionLevel4_0.current;
			afterParserOrEnumRuleCall();
		}
		(
			(
				{
					$current = forceCreateModelElementAndSet(
						grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_1_0(),
						$current);
				}
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getNameBinaryOperatorLevel3EnumRuleCall_1_1_0());
					}
					lv_name_2_0=ruleBinaryOperatorLevel3
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Rule());
						}
						set(
							$current,
							"name",
							lv_name_2_0,
							"org.eclipse.poosl.xtext.Poosl.BinaryOperatorLevel3");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Access().getRightOperandExpressionLevel4ParserRuleCall_1_2_0());
					}
					lv_rightOperand_3_0=ruleExpressionLevel4
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getNonIDStartWithinStatementExpressionLevel3Rule());
						}
						set(
							$current,
							"rightOperand",
							lv_rightOperand_3_0,
							"org.eclipse.poosl.xtext.Poosl.ExpressionLevel4");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
	)
;

// Entry rule entryRuleNonIDStartWithinStatementExpressionLevel4
entryRuleNonIDStartWithinStatementExpressionLevel4 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Rule()); }
	iv_ruleNonIDStartWithinStatementExpressionLevel4=ruleNonIDStartWithinStatementExpressionLevel4
	{ $current=$iv_ruleNonIDStartWithinStatementExpressionLevel4.current; }
	EOF;

// Rule NonIDStartWithinStatementExpressionLevel4
ruleNonIDStartWithinStatementExpressionLevel4 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getNonIDStartWithinStatementExpressionLevel5ParserRuleCall_0());
		}
		this_NonIDStartWithinStatementExpressionLevel5_0=ruleNonIDStartWithinStatementExpressionLevel5
		{
			$current = $this_NonIDStartWithinStatementExpressionLevel5_0.current;
			afterParserOrEnumRuleCall();
		}
		(
			(
				{
					$current = forceCreateModelElementAndSet(
						grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_0(),
						$current);
				}
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getNameBinaryOperatorLevel4EnumRuleCall_1_1_0());
					}
					lv_name_2_0=ruleBinaryOperatorLevel4
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Rule());
						}
						set(
							$current,
							"name",
							lv_name_2_0,
							"org.eclipse.poosl.xtext.Poosl.BinaryOperatorLevel4");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Access().getRightOperandExpressionLevel5ParserRuleCall_1_2_0());
					}
					lv_rightOperand_3_0=ruleExpressionLevel5
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getNonIDStartWithinStatementExpressionLevel4Rule());
						}
						set(
							$current,
							"rightOperand",
							lv_rightOperand_3_0,
							"org.eclipse.poosl.xtext.Poosl.ExpressionLevel5");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
	)
;

// Entry rule entryRuleNonIDStartWithinStatementExpressionLevel5
entryRuleNonIDStartWithinStatementExpressionLevel5 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Rule()); }
	iv_ruleNonIDStartWithinStatementExpressionLevel5=ruleNonIDStartWithinStatementExpressionLevel5
	{ $current=$iv_ruleNonIDStartWithinStatementExpressionLevel5.current; }
	EOF;

// Rule NonIDStartWithinStatementExpressionLevel5
ruleNonIDStartWithinStatementExpressionLevel5 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getNonIDStartWithinStatementExpressionLevel6ParserRuleCall_0());
		}
		this_NonIDStartWithinStatementExpressionLevel6_0=ruleNonIDStartWithinStatementExpressionLevel6
		{
			$current = $this_NonIDStartWithinStatementExpressionLevel6_0.current;
			afterParserOrEnumRuleCall();
		}
		(
			(
				{
					$current = forceCreateModelElementAndSet(
						grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getDataMethodCallExpressionTargetAction_1_0(),
						$current);
				}
			)
			(
				(
					lv_onSuperClass_2_0='^'
					{
						newLeafNode(lv_onSuperClass_2_0, grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getOnSuperClassCircumflexAccentKeyword_1_1_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Rule());
						}
						setWithLastConsumed($current, "onSuperClass", true, "^");
					}
				)
			)?
			(
				(
					{
						newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getNameIDENTIFIERParserRuleCall_1_2_0());
					}
					lv_name_3_0=ruleIDENTIFIER
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Rule());
						}
						set(
							$current,
							"name",
							lv_name_3_0,
							"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				otherlv_4='('
				{
					newLeafNode(otherlv_4, grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getLeftParenthesisKeyword_1_3_0());
				}
				(
					(
						(
							{
								newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_0_0());
							}
							lv_arguments_5_0=ruleExpression
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Rule());
								}
								add(
									$current,
									"arguments",
									lv_arguments_5_0,
									"org.eclipse.poosl.xtext.Poosl.Expression");
								afterParserOrEnumRuleCall();
							}
						)
					)
					(
						otherlv_6=','
						{
							newLeafNode(otherlv_6, grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getCommaKeyword_1_3_1_1_0());
						}
						(
							(
								{
									newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_1_1_0());
								}
								lv_arguments_7_0=ruleExpression
								{
									if ($current==null) {
										$current = createModelElementForParent(grammarAccess.getNonIDStartWithinStatementExpressionLevel5Rule());
									}
									add(
										$current,
										"arguments",
										lv_arguments_7_0,
										"org.eclipse.poosl.xtext.Poosl.Expression");
									afterParserOrEnumRuleCall();
								}
							)
						)
					)*
				)?
				otherlv_8=')'
				{
					newLeafNode(otherlv_8, grammarAccess.getNonIDStartWithinStatementExpressionLevel5Access().getRightParenthesisKeyword_1_3_2());
				}
			)?
		)*
	)
;

// Entry rule entryRuleNonIDStartWithinStatementExpressionLevel6
entryRuleNonIDStartWithinStatementExpressionLevel6 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel6Rule()); }
	iv_ruleNonIDStartWithinStatementExpressionLevel6=ruleNonIDStartWithinStatementExpressionLevel6
	{ $current=$iv_ruleNonIDStartWithinStatementExpressionLevel6.current; }
	EOF;

// Rule NonIDStartWithinStatementExpressionLevel6
ruleNonIDStartWithinStatementExpressionLevel6 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(ruleNonIDStartWithinStatementExpressionLevel7)=>
			{
				newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel6Access().getNonIDStartWithinStatementExpressionLevel7ParserRuleCall_0());
			}
			this_NonIDStartWithinStatementExpressionLevel7_0=ruleNonIDStartWithinStatementExpressionLevel7
			{
				$current = $this_NonIDStartWithinStatementExpressionLevel7_0.current;
				afterParserOrEnumRuleCall();
			}
		)
		    |
		{
			newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel6Access().getUnaryOperatorExpressionParserRuleCall_1());
		}
		this_UnaryOperatorExpression_1=ruleUnaryOperatorExpression
		{
			$current = $this_UnaryOperatorExpression_1.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleNonIDStartWithinStatementExpressionLevel7
entryRuleNonIDStartWithinStatementExpressionLevel7 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel7Rule()); }
	iv_ruleNonIDStartWithinStatementExpressionLevel7=ruleNonIDStartWithinStatementExpressionLevel7
	{ $current=$iv_ruleNonIDStartWithinStatementExpressionLevel7.current; }
	EOF;

// Rule NonIDStartWithinStatementExpressionLevel7
ruleNonIDStartWithinStatementExpressionLevel7 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel7Access().getCurrentTimeExpressionParserRuleCall_0());
		}
		this_CurrentTimeExpression_0=ruleCurrentTimeExpression
		{
			$current = $this_CurrentTimeExpression_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel7Access().getSelfExpressionParserRuleCall_1());
		}
		this_SelfExpression_1=ruleSelfExpression
		{
			$current = $this_SelfExpression_1.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel7Access().getExpressionConstantParserRuleCall_2());
		}
		this_ExpressionConstant_2=ruleExpressionConstant
		{
			$current = $this_ExpressionConstant_2.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getNonIDStartWithinStatementExpressionLevel7Access().getNewExpressionParserRuleCall_3());
		}
		this_NewExpression_3=ruleNewExpression
		{
			$current = $this_NewExpression_3.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleStatement
entryRuleStatement returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getStatementRule()); }
	iv_ruleStatement=ruleStatement
	{ $current=$iv_ruleStatement.current; }
	EOF;

// Rule Statement
ruleStatement returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getStatementAccess().getStatementSingleParserRuleCall_0());
		}
		this_StatementSingle_0=ruleStatementSingle
		{
			$current = $this_StatementSingle_0.current;
			afterParserOrEnumRuleCall();
		}
		(
			(
				{
					$current = forceCreateModelElementAndAdd(
						grammarAccess.getStatementAccess().getStatementSequenceStatementsAction_1_0(),
						$current);
				}
			)
			(
				otherlv_2=';'
				{
					newLeafNode(otherlv_2, grammarAccess.getStatementAccess().getSemicolonKeyword_1_1_0());
				}
				(
					(
						{
							newCompositeNode(grammarAccess.getStatementAccess().getStatementsStatementSingleParserRuleCall_1_1_1_0());
						}
						lv_statements_3_0=ruleStatementSingle
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getStatementRule());
							}
							add(
								$current,
								"statements",
								lv_statements_3_0,
								"org.eclipse.poosl.xtext.Poosl.StatementSingle");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)+
		)?
	)
;

// Entry rule entryRuleStatementSingle
entryRuleStatementSingle returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getStatementSingleRule()); }
	iv_ruleStatementSingle=ruleStatementSingle
	{ $current=$iv_ruleStatementSingle.current; }
	EOF;

// Rule StatementSingle
ruleStatementSingle returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getStatementSingleAccess().getAbortStatementParserRuleCall_0());
		}
		this_AbortStatement_0=ruleAbortStatement
		{
			$current = $this_AbortStatement_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getStatementSingleAccess().getDelayStatementParserRuleCall_1());
		}
		this_DelayStatement_1=ruleDelayStatement
		{
			$current = $this_DelayStatement_1.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getStatementSingleAccess().getGuardedStatementParserRuleCall_2());
		}
		this_GuardedStatement_2=ruleGuardedStatement
		{
			$current = $this_GuardedStatement_2.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getStatementSingleAccess().getInterruptStatementParserRuleCall_3());
		}
		this_InterruptStatement_3=ruleInterruptStatement
		{
			$current = $this_InterruptStatement_3.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getStatementSingleAccess().getParStatementParserRuleCall_4());
		}
		this_ParStatement_4=ruleParStatement
		{
			$current = $this_ParStatement_4.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getStatementSingleAccess().getProcessMethodCallParserRuleCall_5());
		}
		this_ProcessMethodCall_5=ruleProcessMethodCall
		{
			$current = $this_ProcessMethodCall_5.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getStatementSingleAccess().getSelStatementParserRuleCall_6());
		}
		this_SelStatement_6=ruleSelStatement
		{
			$current = $this_SelStatement_6.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getStatementSingleAccess().getSkipStatementParserRuleCall_7());
		}
		this_SkipStatement_7=ruleSkipStatement
		{
			$current = $this_SkipStatement_7.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getStatementSingleAccess().getSendStatementParserRuleCall_8());
		}
		this_SendStatement_8=ruleSendStatement
		{
			$current = $this_SendStatement_8.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getStatementSingleAccess().getReceiveStatementParserRuleCall_9());
		}
		this_ReceiveStatement_9=ruleReceiveStatement
		{
			$current = $this_ReceiveStatement_9.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getStatementSingleAccess().getIfStatementParserRuleCall_10());
		}
		this_IfStatement_10=ruleIfStatement
		{
			$current = $this_IfStatement_10.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getStatementSingleAccess().getBracketStartStatementParserRuleCall_11());
		}
		this_BracketStartStatement_11=ruleBracketStartStatement
		{
			$current = $this_BracketStartStatement_11.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getStatementSingleAccess().getWhileStatementParserRuleCall_12());
		}
		this_WhileStatement_12=ruleWhileStatement
		{
			$current = $this_WhileStatement_12.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getStatementSingleAccess().getSwitchStatementParserRuleCall_13());
		}
		this_SwitchStatement_13=ruleSwitchStatement
		{
			$current = $this_SwitchStatement_13.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getStatementSingleAccess().getExpressionStatementParserRuleCall_14());
		}
		this_ExpressionStatement_14=ruleExpressionStatement
		{
			$current = $this_ExpressionStatement_14.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleExpressionStatement
entryRuleExpressionStatement returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getExpressionStatementRule()); }
	iv_ruleExpressionStatement=ruleExpressionStatement
	{ $current=$iv_ruleExpressionStatement.current; }
	EOF;

// Rule ExpressionStatement
ruleExpressionStatement returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					newCompositeNode(grammarAccess.getExpressionStatementAccess().getExpressionIDStartWithinStatementExpressionSingleParserRuleCall_0_0());
				}
				lv_expression_0_0=ruleIDStartWithinStatementExpressionSingle
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getExpressionStatementRule());
					}
					set(
						$current,
						"expression",
						lv_expression_0_0,
						"org.eclipse.poosl.xtext.Poosl.IDStartWithinStatementExpressionSingle");
					afterParserOrEnumRuleCall();
				}
			)
		)
		    |
		(
			(
				{
					newCompositeNode(grammarAccess.getExpressionStatementAccess().getExpressionNonIDStartWithinStatementExpressionSingleParserRuleCall_1_0());
				}
				lv_expression_1_0=ruleNonIDStartWithinStatementExpressionSingle
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getExpressionStatementRule());
					}
					set(
						$current,
						"expression",
						lv_expression_1_0,
						"org.eclipse.poosl.xtext.Poosl.NonIDStartWithinStatementExpressionSingle");
					afterParserOrEnumRuleCall();
				}
			)
		)
		    |
		(
			otherlv_2='{'
			{
				newLeafNode(otherlv_2, grammarAccess.getExpressionStatementAccess().getLeftCurlyBracketKeyword_2_0());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getExpressionStatementAccess().getExpressionExpressionParserRuleCall_2_1_0());
					}
					lv_expression_3_0=ruleExpression
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getExpressionStatementRule());
						}
						set(
							$current,
							"expression",
							lv_expression_3_0,
							"org.eclipse.poosl.xtext.Poosl.Expression");
						afterParserOrEnumRuleCall();
					}
				)
			)
			otherlv_4='}'
			{
				newLeafNode(otherlv_4, grammarAccess.getExpressionStatementAccess().getRightCurlyBracketKeyword_2_2());
			}
		)
	)
;

// Entry rule entryRuleDelayStatement
entryRuleDelayStatement returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getDelayStatementRule()); }
	iv_ruleDelayStatement=ruleDelayStatement
	{ $current=$iv_ruleDelayStatement.current; }
	EOF;

// Rule DelayStatement
ruleDelayStatement returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='delay'
		{
			newLeafNode(otherlv_0, grammarAccess.getDelayStatementAccess().getDelayKeyword_0());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getDelayStatementAccess().getExpressionExpressionSingleParserRuleCall_1_0());
				}
				lv_expression_1_0=ruleExpressionSingle
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getDelayStatementRule());
					}
					set(
						$current,
						"expression",
						lv_expression_1_0,
						"org.eclipse.poosl.xtext.Poosl.ExpressionSingle");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRuleReceiveStatement
entryRuleReceiveStatement returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getReceiveStatementRule()); }
	iv_ruleReceiveStatement=ruleReceiveStatement
	{ $current=$iv_ruleReceiveStatement.current; }
	EOF;

// Rule ReceiveStatement
ruleReceiveStatement returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					newCompositeNode(grammarAccess.getReceiveStatementAccess().getPortDescriptorPortDescriptorParserRuleCall_0_0());
				}
				lv_portDescriptor_0_0=rulePortDescriptor
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getReceiveStatementRule());
					}
					set(
						$current,
						"portDescriptor",
						lv_portDescriptor_0_0,
						"org.eclipse.poosl.xtext.Poosl.PortDescriptor");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_1='?'
		{
			newLeafNode(otherlv_1, grammarAccess.getReceiveStatementAccess().getQuestionMarkKeyword_1());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getReceiveStatementAccess().getNameIDENTIFIERParserRuleCall_2_0());
				}
				lv_name_2_0=ruleIDENTIFIER
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getReceiveStatementRule());
					}
					set(
						$current,
						"name",
						lv_name_2_0,
						"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_3='('
			{
				newLeafNode(otherlv_3, grammarAccess.getReceiveStatementAccess().getLeftParenthesisKeyword_3_0());
			}
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getReceiveStatementAccess().getVariablesOutputVariableParserRuleCall_3_1_0_0());
						}
						lv_variables_4_0=ruleOutputVariable
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getReceiveStatementRule());
							}
							add(
								$current,
								"variables",
								lv_variables_4_0,
								"org.eclipse.poosl.xtext.Poosl.OutputVariable");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					otherlv_5=','
					{
						newLeafNode(otherlv_5, grammarAccess.getReceiveStatementAccess().getCommaKeyword_3_1_1_0());
					}
					(
						(
							{
								newCompositeNode(grammarAccess.getReceiveStatementAccess().getVariablesOutputVariableParserRuleCall_3_1_1_1_0());
							}
							lv_variables_6_0=ruleOutputVariable
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getReceiveStatementRule());
								}
								add(
									$current,
									"variables",
									lv_variables_6_0,
									"org.eclipse.poosl.xtext.Poosl.OutputVariable");
								afterParserOrEnumRuleCall();
							}
						)
					)
				)*
			)?
			(
				otherlv_7='|'
				{
					newLeafNode(otherlv_7, grammarAccess.getReceiveStatementAccess().getVerticalLineKeyword_3_2_0());
				}
				(
					(
						{
							newCompositeNode(grammarAccess.getReceiveStatementAccess().getReceptionConditionExpressionParserRuleCall_3_2_1_0());
						}
						lv_receptionCondition_8_0=ruleExpression
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getReceiveStatementRule());
							}
							set(
								$current,
								"receptionCondition",
								lv_receptionCondition_8_0,
								"org.eclipse.poosl.xtext.Poosl.Expression");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)?
			otherlv_9=')'
			{
				newLeafNode(otherlv_9, grammarAccess.getReceiveStatementAccess().getRightParenthesisKeyword_3_3());
			}
		)?
		(
			otherlv_10='{'
			{
				newLeafNode(otherlv_10, grammarAccess.getReceiveStatementAccess().getLeftCurlyBracketKeyword_4_0());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getReceiveStatementAccess().getPostCommunicationExpressionExpressionParserRuleCall_4_1_0());
					}
					lv_postCommunicationExpression_11_0=ruleExpression
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getReceiveStatementRule());
						}
						set(
							$current,
							"postCommunicationExpression",
							lv_postCommunicationExpression_11_0,
							"org.eclipse.poosl.xtext.Poosl.Expression");
						afterParserOrEnumRuleCall();
					}
				)
			)
			otherlv_12='}'
			{
				newLeafNode(otherlv_12, grammarAccess.getReceiveStatementAccess().getRightCurlyBracketKeyword_4_2());
			}
		)?
	)
;

// Entry rule entryRuleSendStatement
entryRuleSendStatement returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getSendStatementRule()); }
	iv_ruleSendStatement=ruleSendStatement
	{ $current=$iv_ruleSendStatement.current; }
	EOF;

// Rule SendStatement
ruleSendStatement returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					newCompositeNode(grammarAccess.getSendStatementAccess().getPortDescriptorPortDescriptorParserRuleCall_0_0());
				}
				lv_portDescriptor_0_0=rulePortDescriptor
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getSendStatementRule());
					}
					set(
						$current,
						"portDescriptor",
						lv_portDescriptor_0_0,
						"org.eclipse.poosl.xtext.Poosl.PortDescriptor");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_1='!'
		{
			newLeafNode(otherlv_1, grammarAccess.getSendStatementAccess().getExclamationMarkKeyword_1());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getSendStatementAccess().getNameIDENTIFIERParserRuleCall_2_0());
				}
				lv_name_2_0=ruleIDENTIFIER
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getSendStatementRule());
					}
					set(
						$current,
						"name",
						lv_name_2_0,
						"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_3='('
			{
				newLeafNode(otherlv_3, grammarAccess.getSendStatementAccess().getLeftParenthesisKeyword_3_0());
			}
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getSendStatementAccess().getArgumentsExpressionParserRuleCall_3_1_0_0());
						}
						lv_arguments_4_0=ruleExpression
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getSendStatementRule());
							}
							add(
								$current,
								"arguments",
								lv_arguments_4_0,
								"org.eclipse.poosl.xtext.Poosl.Expression");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					otherlv_5=','
					{
						newLeafNode(otherlv_5, grammarAccess.getSendStatementAccess().getCommaKeyword_3_1_1_0());
					}
					(
						(
							{
								newCompositeNode(grammarAccess.getSendStatementAccess().getArgumentsExpressionParserRuleCall_3_1_1_1_0());
							}
							lv_arguments_6_0=ruleExpression
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getSendStatementRule());
								}
								add(
									$current,
									"arguments",
									lv_arguments_6_0,
									"org.eclipse.poosl.xtext.Poosl.Expression");
								afterParserOrEnumRuleCall();
							}
						)
					)
				)*
			)?
			otherlv_7=')'
			{
				newLeafNode(otherlv_7, grammarAccess.getSendStatementAccess().getRightParenthesisKeyword_3_2());
			}
		)?
		(
			otherlv_8='{'
			{
				newLeafNode(otherlv_8, grammarAccess.getSendStatementAccess().getLeftCurlyBracketKeyword_4_0());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getSendStatementAccess().getPostCommunicationExpressionExpressionParserRuleCall_4_1_0());
					}
					lv_postCommunicationExpression_9_0=ruleExpression
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getSendStatementRule());
						}
						set(
							$current,
							"postCommunicationExpression",
							lv_postCommunicationExpression_9_0,
							"org.eclipse.poosl.xtext.Poosl.Expression");
						afterParserOrEnumRuleCall();
					}
				)
			)
			otherlv_10='}'
			{
				newLeafNode(otherlv_10, grammarAccess.getSendStatementAccess().getRightCurlyBracketKeyword_4_2());
			}
		)?
	)
;

// Entry rule entryRulePortDescriptor
entryRulePortDescriptor returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getPortDescriptorRule()); }
	iv_rulePortDescriptor=rulePortDescriptor
	{ $current=$iv_rulePortDescriptor.current; }
	EOF;

// Rule PortDescriptor
rulePortDescriptor returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getPortDescriptorAccess().getPortReferenceAction_0(),
					$current);
			}
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getPortDescriptorAccess().getPortIDENTIFIERParserRuleCall_1_0());
				}
				lv_port_1_0=ruleIDENTIFIER
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getPortDescriptorRule());
					}
					set(
						$current,
						"port",
						lv_port_1_0,
						"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRulePortReference
entryRulePortReference returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getPortReferenceRule()); }
	iv_rulePortReference=rulePortReference
	{ $current=$iv_rulePortReference.current; }
	EOF;

// Rule PortReference
rulePortReference returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				newCompositeNode(grammarAccess.getPortReferenceAccess().getPortIDENTIFIERParserRuleCall_0());
			}
			lv_port_0_0=ruleIDENTIFIER
			{
				if ($current==null) {
					$current = createModelElementForParent(grammarAccess.getPortReferenceRule());
				}
				set(
					$current,
					"port",
					lv_port_0_0,
					"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
				afterParserOrEnumRuleCall();
			}
		)
	)
;

// Entry rule entryRuleSkipStatement
entryRuleSkipStatement returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getSkipStatementRule()); }
	iv_ruleSkipStatement=ruleSkipStatement
	{ $current=$iv_ruleSkipStatement.current; }
	EOF;

// Rule SkipStatement
ruleSkipStatement returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getSkipStatementAccess().getSkipStatementAction_0(),
					$current);
			}
		)
		otherlv_1='skip'
		{
			newLeafNode(otherlv_1, grammarAccess.getSkipStatementAccess().getSkipKeyword_1());
		}
	)
;

// Entry rule entryRuleGuardedStatement
entryRuleGuardedStatement returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getGuardedStatementRule()); }
	iv_ruleGuardedStatement=ruleGuardedStatement
	{ $current=$iv_ruleGuardedStatement.current; }
	EOF;

// Rule GuardedStatement
ruleGuardedStatement returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='['
		{
			newLeafNode(otherlv_0, grammarAccess.getGuardedStatementAccess().getLeftSquareBracketKeyword_0());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getGuardedStatementAccess().getGuardExpressionParserRuleCall_1_0());
				}
				lv_guard_1_0=ruleExpression
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getGuardedStatementRule());
					}
					set(
						$current,
						"guard",
						lv_guard_1_0,
						"org.eclipse.poosl.xtext.Poosl.Expression");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_2=']'
		{
			newLeafNode(otherlv_2, grammarAccess.getGuardedStatementAccess().getRightSquareBracketKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getGuardedStatementAccess().getStatementStatementSingleParserRuleCall_3_0());
				}
				lv_statement_3_0=ruleStatementSingle
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getGuardedStatementRule());
					}
					set(
						$current,
						"statement",
						lv_statement_3_0,
						"org.eclipse.poosl.xtext.Poosl.StatementSingle");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRuleIfStatement
entryRuleIfStatement returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getIfStatementRule()); }
	iv_ruleIfStatement=ruleIfStatement
	{ $current=$iv_ruleIfStatement.current; }
	EOF;

// Rule IfStatement
ruleIfStatement returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='if'
		{
			newLeafNode(otherlv_0, grammarAccess.getIfStatementAccess().getIfKeyword_0());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getIfStatementAccess().getConditionExpressionParserRuleCall_1_0());
				}
				lv_condition_1_0=ruleExpression
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getIfStatementRule());
					}
					set(
						$current,
						"condition",
						lv_condition_1_0,
						"org.eclipse.poosl.xtext.Poosl.Expression");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_2='then'
		{
			newLeafNode(otherlv_2, grammarAccess.getIfStatementAccess().getThenKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getIfStatementAccess().getThenClauseStatementParserRuleCall_3_0());
				}
				lv_thenClause_3_0=ruleStatement
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getIfStatementRule());
					}
					set(
						$current,
						"thenClause",
						lv_thenClause_3_0,
						"org.eclipse.poosl.xtext.Poosl.Statement");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_4='else'
			{
				newLeafNode(otherlv_4, grammarAccess.getIfStatementAccess().getElseKeyword_4_0());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getIfStatementAccess().getElseClauseStatementParserRuleCall_4_1_0());
					}
					lv_elseClause_5_0=ruleStatement
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getIfStatementRule());
						}
						set(
							$current,
							"elseClause",
							lv_elseClause_5_0,
							"org.eclipse.poosl.xtext.Poosl.Statement");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)?
		otherlv_6='fi'
		{
			newLeafNode(otherlv_6, grammarAccess.getIfStatementAccess().getFiKeyword_5());
		}
	)
;

// Entry rule entryRuleWhileStatement
entryRuleWhileStatement returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getWhileStatementRule()); }
	iv_ruleWhileStatement=ruleWhileStatement
	{ $current=$iv_ruleWhileStatement.current; }
	EOF;

// Rule WhileStatement
ruleWhileStatement returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='while'
		{
			newLeafNode(otherlv_0, grammarAccess.getWhileStatementAccess().getWhileKeyword_0());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getWhileStatementAccess().getConditionExpressionParserRuleCall_1_0());
				}
				lv_condition_1_0=ruleExpression
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getWhileStatementRule());
					}
					set(
						$current,
						"condition",
						lv_condition_1_0,
						"org.eclipse.poosl.xtext.Poosl.Expression");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_2='do'
		{
			newLeafNode(otherlv_2, grammarAccess.getWhileStatementAccess().getDoKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getWhileStatementAccess().getBodyStatementParserRuleCall_3_0());
				}
				lv_body_3_0=ruleStatement
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getWhileStatementRule());
					}
					set(
						$current,
						"body",
						lv_body_3_0,
						"org.eclipse.poosl.xtext.Poosl.Statement");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_4='od'
		{
			newLeafNode(otherlv_4, grammarAccess.getWhileStatementAccess().getOdKeyword_4());
		}
	)
;

// Entry rule entryRuleSwitchStatement
entryRuleSwitchStatement returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getSwitchStatementRule()); }
	iv_ruleSwitchStatement=ruleSwitchStatement
	{ $current=$iv_ruleSwitchStatement.current; }
	EOF;

// Rule SwitchStatement
ruleSwitchStatement returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='switch'
		{
			newLeafNode(otherlv_0, grammarAccess.getSwitchStatementAccess().getSwitchKeyword_0());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getSwitchStatementAccess().getExpressionExpressionParserRuleCall_1_0());
				}
				lv_expression_1_0=ruleExpression
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getSwitchStatementRule());
					}
					set(
						$current,
						"expression",
						lv_expression_1_0,
						"org.eclipse.poosl.xtext.Poosl.Expression");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_2='do'
		{
			newLeafNode(otherlv_2, grammarAccess.getSwitchStatementAccess().getDoKeyword_2());
		}
		(
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getSwitchStatementAccess().getCasesSwitchStatementCaseParserRuleCall_3_0_0_0());
						}
						lv_cases_3_0=ruleSwitchStatementCase
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getSwitchStatementRule());
							}
							add(
								$current,
								"cases",
								lv_cases_3_0,
								"org.eclipse.poosl.xtext.Poosl.SwitchStatementCase");
							afterParserOrEnumRuleCall();
						}
					)
				)+
				(
					otherlv_4='default'
					{
						newLeafNode(otherlv_4, grammarAccess.getSwitchStatementAccess().getDefaultKeyword_3_0_1_0());
					}
					(
						(
							{
								newCompositeNode(grammarAccess.getSwitchStatementAccess().getDefaultBodyStatementParserRuleCall_3_0_1_1_0());
							}
							lv_defaultBody_5_0=ruleStatement
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getSwitchStatementRule());
								}
								set(
									$current,
									"defaultBody",
									lv_defaultBody_5_0,
									"org.eclipse.poosl.xtext.Poosl.Statement");
								afterParserOrEnumRuleCall();
							}
						)
					)
				)?
			)
			    |
			(
				otherlv_6='default'
				{
					newLeafNode(otherlv_6, grammarAccess.getSwitchStatementAccess().getDefaultKeyword_3_1_0());
				}
				(
					(
						{
							newCompositeNode(grammarAccess.getSwitchStatementAccess().getDefaultBodyStatementParserRuleCall_3_1_1_0());
						}
						lv_defaultBody_7_0=ruleStatement
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getSwitchStatementRule());
							}
							set(
								$current,
								"defaultBody",
								lv_defaultBody_7_0,
								"org.eclipse.poosl.xtext.Poosl.Statement");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)
		)
		otherlv_8='od'
		{
			newLeafNode(otherlv_8, grammarAccess.getSwitchStatementAccess().getOdKeyword_4());
		}
	)
;

// Entry rule entryRuleSwitchStatementCase
entryRuleSwitchStatementCase returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getSwitchStatementCaseRule()); }
	iv_ruleSwitchStatementCase=ruleSwitchStatementCase
	{ $current=$iv_ruleSwitchStatementCase.current; }
	EOF;

// Rule SwitchStatementCase
ruleSwitchStatementCase returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='case'
		{
			newLeafNode(otherlv_0, grammarAccess.getSwitchStatementCaseAccess().getCaseKeyword_0());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getSwitchStatementCaseAccess().getValueExpressionParserRuleCall_1_0());
				}
				lv_value_1_0=ruleExpression
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getSwitchStatementCaseRule());
					}
					set(
						$current,
						"value",
						lv_value_1_0,
						"org.eclipse.poosl.xtext.Poosl.Expression");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_2='then'
		{
			newLeafNode(otherlv_2, grammarAccess.getSwitchStatementCaseAccess().getThenKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getSwitchStatementCaseAccess().getBodyStatementParserRuleCall_3_0());
				}
				lv_body_3_0=ruleStatement
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getSwitchStatementCaseRule());
					}
					set(
						$current,
						"body",
						lv_body_3_0,
						"org.eclipse.poosl.xtext.Poosl.Statement");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRuleParStatement
entryRuleParStatement returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getParStatementRule()); }
	iv_ruleParStatement=ruleParStatement
	{ $current=$iv_ruleParStatement.current; }
	EOF;

// Rule ParStatement
ruleParStatement returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='par'
		{
			newLeafNode(otherlv_0, grammarAccess.getParStatementAccess().getParKeyword_0());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getParStatementAccess().getClausesStatementParserRuleCall_1_0());
				}
				lv_clauses_1_0=ruleStatement
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getParStatementRule());
					}
					add(
						$current,
						"clauses",
						lv_clauses_1_0,
						"org.eclipse.poosl.xtext.Poosl.Statement");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_2='and'
			{
				newLeafNode(otherlv_2, grammarAccess.getParStatementAccess().getAndKeyword_2_0());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getParStatementAccess().getClausesStatementParserRuleCall_2_1_0());
					}
					lv_clauses_3_0=ruleStatement
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getParStatementRule());
						}
						add(
							$current,
							"clauses",
							lv_clauses_3_0,
							"org.eclipse.poosl.xtext.Poosl.Statement");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
		otherlv_4='rap'
		{
			newLeafNode(otherlv_4, grammarAccess.getParStatementAccess().getRapKeyword_3());
		}
	)
;

// Entry rule entryRuleSelStatement
entryRuleSelStatement returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getSelStatementRule()); }
	iv_ruleSelStatement=ruleSelStatement
	{ $current=$iv_ruleSelStatement.current; }
	EOF;

// Rule SelStatement
ruleSelStatement returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='sel'
		{
			newLeafNode(otherlv_0, grammarAccess.getSelStatementAccess().getSelKeyword_0());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getSelStatementAccess().getClausesStatementParserRuleCall_1_0());
				}
				lv_clauses_1_0=ruleStatement
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getSelStatementRule());
					}
					add(
						$current,
						"clauses",
						lv_clauses_1_0,
						"org.eclipse.poosl.xtext.Poosl.Statement");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_2='or'
			{
				newLeafNode(otherlv_2, grammarAccess.getSelStatementAccess().getOrKeyword_2_0());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getSelStatementAccess().getClausesStatementParserRuleCall_2_1_0());
					}
					lv_clauses_3_0=ruleStatement
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getSelStatementRule());
						}
						add(
							$current,
							"clauses",
							lv_clauses_3_0,
							"org.eclipse.poosl.xtext.Poosl.Statement");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
		otherlv_4='les'
		{
			newLeafNode(otherlv_4, grammarAccess.getSelStatementAccess().getLesKeyword_3());
		}
	)
;

// Entry rule entryRuleAbortStatement
entryRuleAbortStatement returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getAbortStatementRule()); }
	iv_ruleAbortStatement=ruleAbortStatement
	{ $current=$iv_ruleAbortStatement.current; }
	EOF;

// Rule AbortStatement
ruleAbortStatement returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='abort'
		{
			newLeafNode(otherlv_0, grammarAccess.getAbortStatementAccess().getAbortKeyword_0());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getAbortStatementAccess().getNormalClauseStatementParserRuleCall_1_0());
				}
				lv_normalClause_1_0=ruleStatement
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getAbortStatementRule());
					}
					set(
						$current,
						"normalClause",
						lv_normalClause_1_0,
						"org.eclipse.poosl.xtext.Poosl.Statement");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_2='with'
		{
			newLeafNode(otherlv_2, grammarAccess.getAbortStatementAccess().getWithKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getAbortStatementAccess().getAbortingClauseStatementSingleParserRuleCall_3_0());
				}
				lv_abortingClause_3_0=ruleStatementSingle
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getAbortStatementRule());
					}
					set(
						$current,
						"abortingClause",
						lv_abortingClause_3_0,
						"org.eclipse.poosl.xtext.Poosl.StatementSingle");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRuleInterruptStatement
entryRuleInterruptStatement returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getInterruptStatementRule()); }
	iv_ruleInterruptStatement=ruleInterruptStatement
	{ $current=$iv_ruleInterruptStatement.current; }
	EOF;

// Rule InterruptStatement
ruleInterruptStatement returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='interrupt'
		{
			newLeafNode(otherlv_0, grammarAccess.getInterruptStatementAccess().getInterruptKeyword_0());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getInterruptStatementAccess().getNormalClauseStatementParserRuleCall_1_0());
				}
				lv_normalClause_1_0=ruleStatement
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getInterruptStatementRule());
					}
					set(
						$current,
						"normalClause",
						lv_normalClause_1_0,
						"org.eclipse.poosl.xtext.Poosl.Statement");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_2='with'
		{
			newLeafNode(otherlv_2, grammarAccess.getInterruptStatementAccess().getWithKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getInterruptStatementAccess().getInterruptingClauseStatementSingleParserRuleCall_3_0());
				}
				lv_interruptingClause_3_0=ruleStatementSingle
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getInterruptStatementRule());
					}
					set(
						$current,
						"interruptingClause",
						lv_interruptingClause_3_0,
						"org.eclipse.poosl.xtext.Poosl.StatementSingle");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRuleProcessMethodCall
entryRuleProcessMethodCall returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getProcessMethodCallRule()); }
	iv_ruleProcessMethodCall=ruleProcessMethodCall
	{ $current=$iv_ruleProcessMethodCall.current; }
	EOF;

// Rule ProcessMethodCall
ruleProcessMethodCall returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					newCompositeNode(grammarAccess.getProcessMethodCallAccess().getMethodIDENTIFIERParserRuleCall_0_0());
				}
				lv_method_0_0=ruleIDENTIFIER
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getProcessMethodCallRule());
					}
					set(
						$current,
						"method",
						lv_method_0_0,
						"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_1='('
		{
			newLeafNode(otherlv_1, grammarAccess.getProcessMethodCallAccess().getLeftParenthesisKeyword_1());
		}
		(
			(
				(
					{
						newCompositeNode(grammarAccess.getProcessMethodCallAccess().getInputArgumentsExpressionParserRuleCall_2_0_0());
					}
					lv_inputArguments_2_0=ruleExpression
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getProcessMethodCallRule());
						}
						add(
							$current,
							"inputArguments",
							lv_inputArguments_2_0,
							"org.eclipse.poosl.xtext.Poosl.Expression");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				otherlv_3=','
				{
					newLeafNode(otherlv_3, grammarAccess.getProcessMethodCallAccess().getCommaKeyword_2_1_0());
				}
				(
					(
						{
							newCompositeNode(grammarAccess.getProcessMethodCallAccess().getInputArgumentsExpressionParserRuleCall_2_1_1_0());
						}
						lv_inputArguments_4_0=ruleExpression
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getProcessMethodCallRule());
							}
							add(
								$current,
								"inputArguments",
								lv_inputArguments_4_0,
								"org.eclipse.poosl.xtext.Poosl.Expression");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)*
		)?
		otherlv_5=')'
		{
			newLeafNode(otherlv_5, grammarAccess.getProcessMethodCallAccess().getRightParenthesisKeyword_3());
		}
		otherlv_6='('
		{
			newLeafNode(otherlv_6, grammarAccess.getProcessMethodCallAccess().getLeftParenthesisKeyword_4());
		}
		(
			(
				(
					{
						newCompositeNode(grammarAccess.getProcessMethodCallAccess().getOutputVariablesOutputVariableParserRuleCall_5_0_0());
					}
					lv_outputVariables_7_0=ruleOutputVariable
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getProcessMethodCallRule());
						}
						add(
							$current,
							"outputVariables",
							lv_outputVariables_7_0,
							"org.eclipse.poosl.xtext.Poosl.OutputVariable");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				otherlv_8=','
				{
					newLeafNode(otherlv_8, grammarAccess.getProcessMethodCallAccess().getCommaKeyword_5_1_0());
				}
				(
					(
						{
							newCompositeNode(grammarAccess.getProcessMethodCallAccess().getOutputVariablesOutputVariableParserRuleCall_5_1_1_0());
						}
						lv_outputVariables_9_0=ruleOutputVariable
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getProcessMethodCallRule());
							}
							add(
								$current,
								"outputVariables",
								lv_outputVariables_9_0,
								"org.eclipse.poosl.xtext.Poosl.OutputVariable");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)*
		)?
		otherlv_10=')'
		{
			newLeafNode(otherlv_10, grammarAccess.getProcessMethodCallAccess().getRightParenthesisKeyword_6());
		}
	)
;

// Entry rule entryRuleBracketStartStatement
entryRuleBracketStartStatement returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getBracketStartStatementRule()); }
	iv_ruleBracketStartStatement=ruleBracketStartStatement
	{ $current=$iv_ruleBracketStartStatement.current; }
	EOF;

// Rule BracketStartStatement
ruleBracketStartStatement returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(ruleBracketedArgumentStartExpressionStatement)=>
			{
				newCompositeNode(grammarAccess.getBracketStartStatementAccess().getBracketedArgumentStartExpressionStatementParserRuleCall_0());
			}
			this_BracketedArgumentStartExpressionStatement_0=ruleBracketedArgumentStartExpressionStatement
			{
				$current = $this_BracketedArgumentStartExpressionStatement_0.current;
				afterParserOrEnumRuleCall();
			}
		)
		    |
		{
			newCompositeNode(grammarAccess.getBracketStartStatementAccess().getStatementSequenceRoundBracketParserRuleCall_1());
		}
		this_StatementSequenceRoundBracket_1=ruleStatementSequenceRoundBracket
		{
			$current = $this_StatementSequenceRoundBracket_1.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleStatementSequenceRoundBracket
entryRuleStatementSequenceRoundBracket returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getStatementSequenceRoundBracketRule()); }
	iv_ruleStatementSequenceRoundBracket=ruleStatementSequenceRoundBracket
	{ $current=$iv_ruleStatementSequenceRoundBracket.current; }
	EOF;

// Rule StatementSequenceRoundBracket
ruleStatementSequenceRoundBracket returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='('
		{
			newLeafNode(otherlv_0, grammarAccess.getStatementSequenceRoundBracketAccess().getLeftParenthesisKeyword_0());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getStatementSequenceRoundBracketAccess().getStatementsStatementSingleParserRuleCall_1_0());
				}
				lv_statements_1_0=ruleStatementSingle
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getStatementSequenceRoundBracketRule());
					}
					add(
						$current,
						"statements",
						lv_statements_1_0,
						"org.eclipse.poosl.xtext.Poosl.StatementSingle");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_2=';'
			{
				newLeafNode(otherlv_2, grammarAccess.getStatementSequenceRoundBracketAccess().getSemicolonKeyword_2_0());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getStatementSequenceRoundBracketAccess().getStatementsStatementSingleParserRuleCall_2_1_0());
					}
					lv_statements_3_0=ruleStatementSingle
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getStatementSequenceRoundBracketRule());
						}
						add(
							$current,
							"statements",
							lv_statements_3_0,
							"org.eclipse.poosl.xtext.Poosl.StatementSingle");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
		otherlv_4=')'
		{
			newLeafNode(otherlv_4, grammarAccess.getStatementSequenceRoundBracketAccess().getRightParenthesisKeyword_3());
		}
	)
;

// Entry rule entryRuleBracketedArgumentStartExpressionStatement
entryRuleBracketedArgumentStartExpressionStatement returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionStatementRule()); }
	iv_ruleBracketedArgumentStartExpressionStatement=ruleBracketedArgumentStartExpressionStatement
	{ $current=$iv_ruleBracketedArgumentStartExpressionStatement.current; }
	EOF;

// Rule BracketedArgumentStartExpressionStatement
ruleBracketedArgumentStartExpressionStatement returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionStatementAccess().getExpressionBracketedArgumentStartExpressionSingleParserRuleCall_0());
			}
			lv_expression_0_0=ruleBracketedArgumentStartExpressionSingle
			{
				if ($current==null) {
					$current = createModelElementForParent(grammarAccess.getBracketedArgumentStartExpressionStatementRule());
				}
				set(
					$current,
					"expression",
					lv_expression_0_0,
					"org.eclipse.poosl.xtext.Poosl.BracketedArgumentStartExpressionSingle");
				afterParserOrEnumRuleCall();
			}
		)
	)
;

// Entry rule entryRuleBracketedArgumentStartExpressionSingle
entryRuleBracketedArgumentStartExpressionSingle returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionSingleRule()); }
	iv_ruleBracketedArgumentStartExpressionSingle=ruleBracketedArgumentStartExpressionSingle
	{ $current=$iv_ruleBracketedArgumentStartExpressionSingle.current; }
	EOF;

// Rule BracketedArgumentStartExpressionSingle
ruleBracketedArgumentStartExpressionSingle returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	{
		newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionSingleAccess().getBracketedArgumentStartExpressionLevel1ParserRuleCall());
	}
	this_BracketedArgumentStartExpressionLevel1_0=ruleBracketedArgumentStartExpressionLevel1
	{
		$current = $this_BracketedArgumentStartExpressionLevel1_0.current;
		afterParserOrEnumRuleCall();
	}
;

// Entry rule entryRuleBracketedArgumentStartExpressionLevel1
entryRuleBracketedArgumentStartExpressionLevel1 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel1Rule()); }
	iv_ruleBracketedArgumentStartExpressionLevel1=ruleBracketedArgumentStartExpressionLevel1
	{ $current=$iv_ruleBracketedArgumentStartExpressionLevel1.current; }
	EOF;

// Rule BracketedArgumentStartExpressionLevel1
ruleBracketedArgumentStartExpressionLevel1 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	{
		newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel1Access().getBracketedArgumentStartExpressionLevel2ParserRuleCall());
	}
	this_BracketedArgumentStartExpressionLevel2_0=ruleBracketedArgumentStartExpressionLevel2
	{
		$current = $this_BracketedArgumentStartExpressionLevel2_0.current;
		afterParserOrEnumRuleCall();
	}
;

// Entry rule entryRuleBracketedArgumentStartExpressionLevel2
entryRuleBracketedArgumentStartExpressionLevel2 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel2Rule()); }
	iv_ruleBracketedArgumentStartExpressionLevel2=ruleBracketedArgumentStartExpressionLevel2
	{ $current=$iv_ruleBracketedArgumentStartExpressionLevel2.current; }
	EOF;

// Rule BracketedArgumentStartExpressionLevel2
ruleBracketedArgumentStartExpressionLevel2 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				(ruleBracketedArgumentStartExpressionLevel3)=>
				{
					newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getBracketedArgumentStartExpressionLevel3ParserRuleCall_0_0());
				}
				this_BracketedArgumentStartExpressionLevel3_0=ruleBracketedArgumentStartExpressionLevel3
				{
					$current = $this_BracketedArgumentStartExpressionLevel3_0.current;
					afterParserOrEnumRuleCall();
				}
			)
			(
				(
					{
						$current = forceCreateModelElementAndSet(
							grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_0_1_0(),
							$current);
					}
				)
				(
					(
						{
							newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getNameBinaryOperatorLevel2EnumRuleCall_0_1_1_0());
						}
						lv_name_2_0=ruleBinaryOperatorLevel2
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getBracketedArgumentStartExpressionLevel2Rule());
							}
							set(
								$current,
								"name",
								lv_name_2_0,
								"org.eclipse.poosl.xtext.Poosl.BinaryOperatorLevel2");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					(
						{
							newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getRightOperandExpressionLevel3ParserRuleCall_0_1_2_0());
						}
						lv_rightOperand_3_0=ruleExpressionLevel3
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getBracketedArgumentStartExpressionLevel2Rule());
							}
							set(
								$current,
								"rightOperand",
								lv_rightOperand_3_0,
								"org.eclipse.poosl.xtext.Poosl.ExpressionLevel3");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)*
		)
		    |
		(
			{
				newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getExpressionSequenceRoundBracketParserRuleCall_1_0());
			}
			this_ExpressionSequenceRoundBracket_4=ruleExpressionSequenceRoundBracket
			{
				$current = $this_ExpressionSequenceRoundBracket_4.current;
				afterParserOrEnumRuleCall();
			}
			(
				(
					{
						$current = forceCreateModelElementAndSet(
							grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getBinaryOperatorExpressionLeftOperandAction_1_1_0(),
							$current);
					}
				)
				(
					(
						{
							newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getNameBinaryOperatorLevel2EnumRuleCall_1_1_1_0());
						}
						lv_name_6_0=ruleBinaryOperatorLevel2
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getBracketedArgumentStartExpressionLevel2Rule());
							}
							set(
								$current,
								"name",
								lv_name_6_0,
								"org.eclipse.poosl.xtext.Poosl.BinaryOperatorLevel2");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					(
						{
							newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel2Access().getRightOperandExpressionLevel3ParserRuleCall_1_1_2_0());
						}
						lv_rightOperand_7_0=ruleExpressionLevel3
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getBracketedArgumentStartExpressionLevel2Rule());
							}
							set(
								$current,
								"rightOperand",
								lv_rightOperand_7_0,
								"org.eclipse.poosl.xtext.Poosl.ExpressionLevel3");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)+
		)
	)
;

// Entry rule entryRuleBracketedArgumentStartExpressionLevel3
entryRuleBracketedArgumentStartExpressionLevel3 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel3Rule()); }
	iv_ruleBracketedArgumentStartExpressionLevel3=ruleBracketedArgumentStartExpressionLevel3
	{ $current=$iv_ruleBracketedArgumentStartExpressionLevel3.current; }
	EOF;

// Rule BracketedArgumentStartExpressionLevel3
ruleBracketedArgumentStartExpressionLevel3 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				(ruleBracketedArgumentStartExpressionLevel4)=>
				{
					newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getBracketedArgumentStartExpressionLevel4ParserRuleCall_0_0());
				}
				this_BracketedArgumentStartExpressionLevel4_0=ruleBracketedArgumentStartExpressionLevel4
				{
					$current = $this_BracketedArgumentStartExpressionLevel4_0.current;
					afterParserOrEnumRuleCall();
				}
			)
			(
				(
					{
						$current = forceCreateModelElementAndSet(
							grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_0_1_0(),
							$current);
					}
				)
				(
					(
						{
							newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getNameBinaryOperatorLevel3EnumRuleCall_0_1_1_0());
						}
						lv_name_2_0=ruleBinaryOperatorLevel3
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getBracketedArgumentStartExpressionLevel3Rule());
							}
							set(
								$current,
								"name",
								lv_name_2_0,
								"org.eclipse.poosl.xtext.Poosl.BinaryOperatorLevel3");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					(
						{
							newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getRightOperandExpressionLevel4ParserRuleCall_0_1_2_0());
						}
						lv_rightOperand_3_0=ruleExpressionLevel4
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getBracketedArgumentStartExpressionLevel3Rule());
							}
							set(
								$current,
								"rightOperand",
								lv_rightOperand_3_0,
								"org.eclipse.poosl.xtext.Poosl.ExpressionLevel4");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)*
		)
		    |
		(
			{
				newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getExpressionSequenceRoundBracketParserRuleCall_1_0());
			}
			this_ExpressionSequenceRoundBracket_4=ruleExpressionSequenceRoundBracket
			{
				$current = $this_ExpressionSequenceRoundBracket_4.current;
				afterParserOrEnumRuleCall();
			}
			(
				(
					{
						$current = forceCreateModelElementAndSet(
							grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getBinaryOperatorExpressionLeftOperandAction_1_1_0(),
							$current);
					}
				)
				(
					(
						{
							newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getNameBinaryOperatorLevel3EnumRuleCall_1_1_1_0());
						}
						lv_name_6_0=ruleBinaryOperatorLevel3
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getBracketedArgumentStartExpressionLevel3Rule());
							}
							set(
								$current,
								"name",
								lv_name_6_0,
								"org.eclipse.poosl.xtext.Poosl.BinaryOperatorLevel3");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					(
						{
							newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel3Access().getRightOperandExpressionLevel4ParserRuleCall_1_1_2_0());
						}
						lv_rightOperand_7_0=ruleExpressionLevel4
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getBracketedArgumentStartExpressionLevel3Rule());
							}
							set(
								$current,
								"rightOperand",
								lv_rightOperand_7_0,
								"org.eclipse.poosl.xtext.Poosl.ExpressionLevel4");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)+
		)
	)
;

// Entry rule entryRuleBracketedArgumentStartExpressionLevel4
entryRuleBracketedArgumentStartExpressionLevel4 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel4Rule()); }
	iv_ruleBracketedArgumentStartExpressionLevel4=ruleBracketedArgumentStartExpressionLevel4
	{ $current=$iv_ruleBracketedArgumentStartExpressionLevel4.current; }
	EOF;

// Rule BracketedArgumentStartExpressionLevel4
ruleBracketedArgumentStartExpressionLevel4 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				(ruleBracketedArgumentStartExpressionLevel5)=>
				{
					newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getBracketedArgumentStartExpressionLevel5ParserRuleCall_0_0());
				}
				this_BracketedArgumentStartExpressionLevel5_0=ruleBracketedArgumentStartExpressionLevel5
				{
					$current = $this_BracketedArgumentStartExpressionLevel5_0.current;
					afterParserOrEnumRuleCall();
				}
			)
			(
				(
					{
						$current = forceCreateModelElementAndSet(
							grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_0_1_0(),
							$current);
					}
				)
				(
					(
						{
							newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getNameBinaryOperatorLevel4EnumRuleCall_0_1_1_0());
						}
						lv_name_2_0=ruleBinaryOperatorLevel4
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getBracketedArgumentStartExpressionLevel4Rule());
							}
							set(
								$current,
								"name",
								lv_name_2_0,
								"org.eclipse.poosl.xtext.Poosl.BinaryOperatorLevel4");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					(
						{
							newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getRightOperandExpressionLevel5ParserRuleCall_0_1_2_0());
						}
						lv_rightOperand_3_0=ruleExpressionLevel5
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getBracketedArgumentStartExpressionLevel4Rule());
							}
							set(
								$current,
								"rightOperand",
								lv_rightOperand_3_0,
								"org.eclipse.poosl.xtext.Poosl.ExpressionLevel5");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)*
		)
		    |
		(
			{
				newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getExpressionSequenceRoundBracketParserRuleCall_1_0());
			}
			this_ExpressionSequenceRoundBracket_4=ruleExpressionSequenceRoundBracket
			{
				$current = $this_ExpressionSequenceRoundBracket_4.current;
				afterParserOrEnumRuleCall();
			}
			(
				(
					{
						$current = forceCreateModelElementAndSet(
							grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getBinaryOperatorExpressionLeftOperandAction_1_1_0(),
							$current);
					}
				)
				(
					(
						{
							newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getNameBinaryOperatorLevel4EnumRuleCall_1_1_1_0());
						}
						lv_name_6_0=ruleBinaryOperatorLevel4
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getBracketedArgumentStartExpressionLevel4Rule());
							}
							set(
								$current,
								"name",
								lv_name_6_0,
								"org.eclipse.poosl.xtext.Poosl.BinaryOperatorLevel4");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					(
						{
							newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel4Access().getRightOperandExpressionLevel5ParserRuleCall_1_1_2_0());
						}
						lv_rightOperand_7_0=ruleExpressionLevel5
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getBracketedArgumentStartExpressionLevel4Rule());
							}
							set(
								$current,
								"rightOperand",
								lv_rightOperand_7_0,
								"org.eclipse.poosl.xtext.Poosl.ExpressionLevel5");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)+
		)
	)
;

// Entry rule entryRuleBracketedArgumentStartExpressionLevel5
entryRuleBracketedArgumentStartExpressionLevel5 returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel5Rule()); }
	iv_ruleBracketedArgumentStartExpressionLevel5=ruleBracketedArgumentStartExpressionLevel5
	{ $current=$iv_ruleBracketedArgumentStartExpressionLevel5.current; }
	EOF;

// Rule BracketedArgumentStartExpressionLevel5
ruleBracketedArgumentStartExpressionLevel5 returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getExpressionSequenceRoundBracketParserRuleCall_0());
		}
		this_ExpressionSequenceRoundBracket_0=ruleExpressionSequenceRoundBracket
		{
			$current = $this_ExpressionSequenceRoundBracket_0.current;
			afterParserOrEnumRuleCall();
		}
		(
			(
				{
					$current = forceCreateModelElementAndSet(
						grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getDataMethodCallExpressionTargetAction_1_0(),
						$current);
				}
			)
			(
				(
					lv_onSuperClass_2_0='^'
					{
						newLeafNode(lv_onSuperClass_2_0, grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getOnSuperClassCircumflexAccentKeyword_1_1_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getBracketedArgumentStartExpressionLevel5Rule());
						}
						setWithLastConsumed($current, "onSuperClass", true, "^");
					}
				)
			)?
			(
				(
					{
						newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getNameIDENTIFIERParserRuleCall_1_2_0());
					}
					lv_name_3_0=ruleIDENTIFIER
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getBracketedArgumentStartExpressionLevel5Rule());
						}
						set(
							$current,
							"name",
							lv_name_3_0,
							"org.eclipse.poosl.xtext.Poosl.IDENTIFIER");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				otherlv_4='('
				{
					newLeafNode(otherlv_4, grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getLeftParenthesisKeyword_1_3_0());
				}
				(
					(
						(
							{
								newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_0_0());
							}
							lv_arguments_5_0=ruleExpression
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getBracketedArgumentStartExpressionLevel5Rule());
								}
								add(
									$current,
									"arguments",
									lv_arguments_5_0,
									"org.eclipse.poosl.xtext.Poosl.Expression");
								afterParserOrEnumRuleCall();
							}
						)
					)
					(
						otherlv_6=','
						{
							newLeafNode(otherlv_6, grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getCommaKeyword_1_3_1_1_0());
						}
						(
							(
								{
									newCompositeNode(grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getArgumentsExpressionParserRuleCall_1_3_1_1_1_0());
								}
								lv_arguments_7_0=ruleExpression
								{
									if ($current==null) {
										$current = createModelElementForParent(grammarAccess.getBracketedArgumentStartExpressionLevel5Rule());
									}
									add(
										$current,
										"arguments",
										lv_arguments_7_0,
										"org.eclipse.poosl.xtext.Poosl.Expression");
									afterParserOrEnumRuleCall();
								}
							)
						)
					)*
				)?
				otherlv_8=')'
				{
					newLeafNode(otherlv_8, grammarAccess.getBracketedArgumentStartExpressionLevel5Access().getRightParenthesisKeyword_1_3_2());
				}
			)?
		)+
	)
;

// Entry rule entryRuleINTEGER
entryRuleINTEGER returns [String current=null]:
	{ newCompositeNode(grammarAccess.getINTEGERRule()); }
	iv_ruleINTEGER=ruleINTEGER
	{ $current=$iv_ruleINTEGER.current.getText(); }
	EOF;

// Rule INTEGER
ruleINTEGER returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			kw='-'
			{
				$current.merge(kw);
				newLeafNode(kw, grammarAccess.getINTEGERAccess().getHyphenMinusKeyword_0_0());
			}
			    |
			kw='+'
			{
				$current.merge(kw);
				newLeafNode(kw, grammarAccess.getINTEGERAccess().getPlusSignKeyword_0_1());
			}
		)?
		(
			this_DECIMAL_CORE_2=RULE_DECIMAL_CORE
			{
				$current.merge(this_DECIMAL_CORE_2);
			}
			{
				newLeafNode(this_DECIMAL_CORE_2, grammarAccess.getINTEGERAccess().getDECIMAL_CORETerminalRuleCall_1_0());
			}
			    |
			this_BINARY_CORE_3=RULE_BINARY_CORE
			{
				$current.merge(this_BINARY_CORE_3);
			}
			{
				newLeafNode(this_BINARY_CORE_3, grammarAccess.getINTEGERAccess().getBINARY_CORETerminalRuleCall_1_1());
			}
			    |
			this_HEXADECIMAL_CORE_4=RULE_HEXADECIMAL_CORE
			{
				$current.merge(this_HEXADECIMAL_CORE_4);
			}
			{
				newLeafNode(this_HEXADECIMAL_CORE_4, grammarAccess.getINTEGERAccess().getHEXADECIMAL_CORETerminalRuleCall_1_2());
			}
		)
	)
;

// Entry rule entryRuleREAL
entryRuleREAL returns [String current=null]:
	{ newCompositeNode(grammarAccess.getREALRule()); }
	iv_ruleREAL=ruleREAL
	{ $current=$iv_ruleREAL.current.getText(); }
	EOF;

// Rule REAL
ruleREAL returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			kw='-'
			{
				$current.merge(kw);
				newLeafNode(kw, grammarAccess.getREALAccess().getHyphenMinusKeyword_0_0());
			}
			    |
			kw='+'
			{
				$current.merge(kw);
				newLeafNode(kw, grammarAccess.getREALAccess().getPlusSignKeyword_0_1());
			}
		)?
		this_REAL_CORE_2=RULE_REAL_CORE
		{
			$current.merge(this_REAL_CORE_2);
		}
		{
			newLeafNode(this_REAL_CORE_2, grammarAccess.getREALAccess().getREAL_CORETerminalRuleCall_1());
		}
	)
;

// Entry rule entryRuleFLOAT
entryRuleFLOAT returns [String current=null]:
	{ newCompositeNode(grammarAccess.getFLOATRule()); }
	iv_ruleFLOAT=ruleFLOAT
	{ $current=$iv_ruleFLOAT.current.getText(); }
	EOF;

// Rule FLOAT
ruleFLOAT returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			kw='-'
			{
				$current.merge(kw);
				newLeafNode(kw, grammarAccess.getFLOATAccess().getHyphenMinusKeyword_0_0());
			}
			    |
			kw='+'
			{
				$current.merge(kw);
				newLeafNode(kw, grammarAccess.getFLOATAccess().getPlusSignKeyword_0_1());
			}
		)?
		(
			this_FLOAT_CORE_2=RULE_FLOAT_CORE
			{
				$current.merge(this_FLOAT_CORE_2);
			}
			{
				newLeafNode(this_FLOAT_CORE_2, grammarAccess.getFLOATAccess().getFLOAT_CORETerminalRuleCall_1_0());
			}
			    |
			kw='nan'
			{
				$current.merge(kw);
				newLeafNode(kw, grammarAccess.getFLOATAccess().getNanKeyword_1_1());
			}
			    |
			kw='inf'
			{
				$current.merge(kw);
				newLeafNode(kw, grammarAccess.getFLOATAccess().getInfKeyword_1_2());
			}
		)
	)
;

// Entry rule entryRuleIDENTIFIER
entryRuleIDENTIFIER returns [String current=null]:
	{ newCompositeNode(grammarAccess.getIDENTIFIERRule()); }
	iv_ruleIDENTIFIER=ruleIDENTIFIER
	{ $current=$iv_ruleIDENTIFIER.current.getText(); }
	EOF;

// Rule IDENTIFIER
ruleIDENTIFIER returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		this_IDENTIFIER_CORE_0=RULE_IDENTIFIER_CORE
		{
			$current.merge(this_IDENTIFIER_CORE_0);
		}
		{
			newLeafNode(this_IDENTIFIER_CORE_0, grammarAccess.getIDENTIFIERAccess().getIDENTIFIER_CORETerminalRuleCall_0());
		}
		    |
		kw='class'
		{
			$current.merge(kw);
			newLeafNode(kw, grammarAccess.getIDENTIFIERAccess().getClassKeyword_1());
		}
		    |
		kw='extends'
		{
			$current.merge(kw);
			newLeafNode(kw, grammarAccess.getIDENTIFIERAccess().getExtendsKeyword_2());
		}
		    |
		kw='variables'
		{
			$current.merge(kw);
			newLeafNode(kw, grammarAccess.getIDENTIFIERAccess().getVariablesKeyword_3());
		}
		    |
		kw='methods'
		{
			$current.merge(kw);
			newLeafNode(kw, grammarAccess.getIDENTIFIERAccess().getMethodsKeyword_4());
		}
		    |
		kw='ports'
		{
			$current.merge(kw);
			newLeafNode(kw, grammarAccess.getIDENTIFIERAccess().getPortsKeyword_5());
		}
		    |
		kw='messages'
		{
			$current.merge(kw);
			newLeafNode(kw, grammarAccess.getIDENTIFIERAccess().getMessagesKeyword_6());
		}
		    |
		kw='init'
		{
			$current.merge(kw);
			newLeafNode(kw, grammarAccess.getIDENTIFIERAccess().getInitKeyword_7());
		}
		    |
		kw='channels'
		{
			$current.merge(kw);
			newLeafNode(kw, grammarAccess.getIDENTIFIERAccess().getChannelsKeyword_8());
		}
		    |
		kw='instances'
		{
			$current.merge(kw);
			newLeafNode(kw, grammarAccess.getIDENTIFIERAccess().getInstancesKeyword_9());
		}
	)
;

// Rule OperatorUnary
ruleOperatorUnary returns [Enumerator current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			enumLiteral_0='-'
			{
				$current = grammarAccess.getOperatorUnaryAccess().getMinusEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_0, grammarAccess.getOperatorUnaryAccess().getMinusEnumLiteralDeclaration_0());
			}
		)
		    |
		(
			enumLiteral_1='!'
			{
				$current = grammarAccess.getOperatorUnaryAccess().getNotEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_1, grammarAccess.getOperatorUnaryAccess().getNotEnumLiteralDeclaration_1());
			}
		)
	)
;

// Rule OperatorBinary
ruleOperatorBinary returns [Enumerator current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			enumLiteral_0='='
			{
				$current = grammarAccess.getOperatorBinaryAccess().getEqualEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_0, grammarAccess.getOperatorBinaryAccess().getEqualEnumLiteralDeclaration_0());
			}
		)
		    |
		(
			enumLiteral_1='!='
			{
				$current = grammarAccess.getOperatorBinaryAccess().getUnequalEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_1, grammarAccess.getOperatorBinaryAccess().getUnequalEnumLiteralDeclaration_1());
			}
		)
		    |
		(
			enumLiteral_2='=='
			{
				$current = grammarAccess.getOperatorBinaryAccess().getIdenticalEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_2, grammarAccess.getOperatorBinaryAccess().getIdenticalEnumLiteralDeclaration_2());
			}
		)
		    |
		(
			enumLiteral_3='!=='
			{
				$current = grammarAccess.getOperatorBinaryAccess().getNotIdenticalEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_3, grammarAccess.getOperatorBinaryAccess().getNotIdenticalEnumLiteralDeclaration_3());
			}
		)
		    |
		(
			enumLiteral_4='<'
			{
				$current = grammarAccess.getOperatorBinaryAccess().getLessThanEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_4, grammarAccess.getOperatorBinaryAccess().getLessThanEnumLiteralDeclaration_4());
			}
		)
		    |
		(
			enumLiteral_5='<='
			{
				$current = grammarAccess.getOperatorBinaryAccess().getAtMostEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_5, grammarAccess.getOperatorBinaryAccess().getAtMostEnumLiteralDeclaration_5());
			}
		)
		    |
		(
			enumLiteral_6='>'
			{
				$current = grammarAccess.getOperatorBinaryAccess().getGreaterThanEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_6, grammarAccess.getOperatorBinaryAccess().getGreaterThanEnumLiteralDeclaration_6());
			}
		)
		    |
		(
			enumLiteral_7='>='
			{
				$current = grammarAccess.getOperatorBinaryAccess().getAtLeastEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_7, grammarAccess.getOperatorBinaryAccess().getAtLeastEnumLiteralDeclaration_7());
			}
		)
		    |
		(
			enumLiteral_8='+'
			{
				$current = grammarAccess.getOperatorBinaryAccess().getAddEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_8, grammarAccess.getOperatorBinaryAccess().getAddEnumLiteralDeclaration_8());
			}
		)
		    |
		(
			enumLiteral_9='-'
			{
				$current = grammarAccess.getOperatorBinaryAccess().getSubtractEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_9, grammarAccess.getOperatorBinaryAccess().getSubtractEnumLiteralDeclaration_9());
			}
		)
		    |
		(
			enumLiteral_10='&'
			{
				$current = grammarAccess.getOperatorBinaryAccess().getAndEnumLiteralDeclaration_10().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_10, grammarAccess.getOperatorBinaryAccess().getAndEnumLiteralDeclaration_10());
			}
		)
		    |
		(
			enumLiteral_11='|'
			{
				$current = grammarAccess.getOperatorBinaryAccess().getOrEnumLiteralDeclaration_11().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_11, grammarAccess.getOperatorBinaryAccess().getOrEnumLiteralDeclaration_11());
			}
		)
		    |
		(
			enumLiteral_12='*'
			{
				$current = grammarAccess.getOperatorBinaryAccess().getMultiplyEnumLiteralDeclaration_12().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_12, grammarAccess.getOperatorBinaryAccess().getMultiplyEnumLiteralDeclaration_12());
			}
		)
		    |
		(
			enumLiteral_13='/'
			{
				$current = grammarAccess.getOperatorBinaryAccess().getDivideEnumLiteralDeclaration_13().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_13, grammarAccess.getOperatorBinaryAccess().getDivideEnumLiteralDeclaration_13());
			}
		)
	)
;

// Rule BinaryOperatorLevel2
ruleBinaryOperatorLevel2 returns [Enumerator current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			enumLiteral_0='='
			{
				$current = grammarAccess.getBinaryOperatorLevel2Access().getEqualEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_0, grammarAccess.getBinaryOperatorLevel2Access().getEqualEnumLiteralDeclaration_0());
			}
		)
		    |
		(
			enumLiteral_1='!='
			{
				$current = grammarAccess.getBinaryOperatorLevel2Access().getUnequalEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_1, grammarAccess.getBinaryOperatorLevel2Access().getUnequalEnumLiteralDeclaration_1());
			}
		)
		    |
		(
			enumLiteral_2='=='
			{
				$current = grammarAccess.getBinaryOperatorLevel2Access().getIdenticalEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_2, grammarAccess.getBinaryOperatorLevel2Access().getIdenticalEnumLiteralDeclaration_2());
			}
		)
		    |
		(
			enumLiteral_3='!=='
			{
				$current = grammarAccess.getBinaryOperatorLevel2Access().getNotIdenticalEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_3, grammarAccess.getBinaryOperatorLevel2Access().getNotIdenticalEnumLiteralDeclaration_3());
			}
		)
		    |
		(
			enumLiteral_4='<'
			{
				$current = grammarAccess.getBinaryOperatorLevel2Access().getLessThanEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_4, grammarAccess.getBinaryOperatorLevel2Access().getLessThanEnumLiteralDeclaration_4());
			}
		)
		    |
		(
			enumLiteral_5='<='
			{
				$current = grammarAccess.getBinaryOperatorLevel2Access().getAtMostEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_5, grammarAccess.getBinaryOperatorLevel2Access().getAtMostEnumLiteralDeclaration_5());
			}
		)
		    |
		(
			enumLiteral_6='>'
			{
				$current = grammarAccess.getBinaryOperatorLevel2Access().getGreaterThanEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_6, grammarAccess.getBinaryOperatorLevel2Access().getGreaterThanEnumLiteralDeclaration_6());
			}
		)
		    |
		(
			enumLiteral_7='>='
			{
				$current = grammarAccess.getBinaryOperatorLevel2Access().getAtLeastEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_7, grammarAccess.getBinaryOperatorLevel2Access().getAtLeastEnumLiteralDeclaration_7());
			}
		)
	)
;

// Rule BinaryOperatorLevel3
ruleBinaryOperatorLevel3 returns [Enumerator current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			enumLiteral_0='+'
			{
				$current = grammarAccess.getBinaryOperatorLevel3Access().getAddEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_0, grammarAccess.getBinaryOperatorLevel3Access().getAddEnumLiteralDeclaration_0());
			}
		)
		    |
		(
			enumLiteral_1='-'
			{
				$current = grammarAccess.getBinaryOperatorLevel3Access().getSubtractEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_1, grammarAccess.getBinaryOperatorLevel3Access().getSubtractEnumLiteralDeclaration_1());
			}
		)
		    |
		(
			enumLiteral_2='&'
			{
				$current = grammarAccess.getBinaryOperatorLevel3Access().getAndEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_2, grammarAccess.getBinaryOperatorLevel3Access().getAndEnumLiteralDeclaration_2());
			}
		)
		    |
		(
			enumLiteral_3='|'
			{
				$current = grammarAccess.getBinaryOperatorLevel3Access().getOrEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_3, grammarAccess.getBinaryOperatorLevel3Access().getOrEnumLiteralDeclaration_3());
			}
		)
	)
;

// Rule BinaryOperatorLevel4
ruleBinaryOperatorLevel4 returns [Enumerator current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			enumLiteral_0='*'
			{
				$current = grammarAccess.getBinaryOperatorLevel4Access().getMultiplyEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_0, grammarAccess.getBinaryOperatorLevel4Access().getMultiplyEnumLiteralDeclaration_0());
			}
		)
		    |
		(
			enumLiteral_1='/'
			{
				$current = grammarAccess.getBinaryOperatorLevel4Access().getDivideEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
				newLeafNode(enumLiteral_1, grammarAccess.getBinaryOperatorLevel4Access().getDivideEnumLiteralDeclaration_1());
			}
		)
	)
;

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
