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
package org.eclipse.poosl.xtext.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.EnumLiteralDeclaration;
import org.eclipse.xtext.EnumRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.service.AbstractElementFinder.AbstractEnumRuleElementFinder;
import org.eclipse.xtext.service.AbstractElementFinder.AbstractGrammarElementFinder;
import org.eclipse.xtext.service.GrammarProvider;

@Singleton
public class PooslGrammarAccess extends AbstractGrammarElementFinder {

    public class PooslElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.Poosl");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Action cPooslAction_0 = (Action) cGroup.eContents().get(0);

        private final Alternatives cAlternatives_1 = (Alternatives) cGroup.eContents().get(1);

        private final Assignment cImportsAssignment_1_0 = (Assignment) cAlternatives_1.eContents()
                .get(0);

        private final RuleCall cImportsImportParserRuleCall_1_0_0 = (RuleCall) cImportsAssignment_1_0
                .eContents().get(0);

        private final Assignment cImportLibsAssignment_1_1 = (Assignment) cAlternatives_1
                .eContents().get(1);

        private final RuleCall cImportLibsImportLibParserRuleCall_1_1_0 = (RuleCall) cImportLibsAssignment_1_1
                .eContents().get(0);

        private final Alternatives cAlternatives_2 = (Alternatives) cGroup.eContents().get(2);

        private final Assignment cDataClassesAssignment_2_0 = (Assignment) cAlternatives_2
                .eContents().get(0);

        private final RuleCall cDataClassesDataClassParserRuleCall_2_0_0 = (RuleCall) cDataClassesAssignment_2_0
                .eContents().get(0);

        private final Assignment cProcessClassesAssignment_2_1 = (Assignment) cAlternatives_2
                .eContents().get(1);

        private final RuleCall cProcessClassesProcessClassParserRuleCall_2_1_0 = (RuleCall) cProcessClassesAssignment_2_1
                .eContents().get(0);

        private final Assignment cClusterClassesAssignment_2_2 = (Assignment) cAlternatives_2
                .eContents().get(2);

        private final RuleCall cClusterClassesClusterClassParserRuleCall_2_2_0 = (RuleCall) cClusterClassesAssignment_2_2
                .eContents().get(0);

        private final Group cGroup_3 = (Group) cGroup.eContents().get(3);

        private final Assignment cClusterClassesAssignment_3_0 = (Assignment) cGroup_3.eContents()
                .get(0);

        private final RuleCall cClusterClassesSystemParserRuleCall_3_0_0 = (RuleCall) cClusterClassesAssignment_3_0
                .eContents().get(0);

        private final Alternatives cAlternatives_3_1 = (Alternatives) cGroup_3.eContents().get(1);

        private final Assignment cDataClassesAssignment_3_1_0 = (Assignment) cAlternatives_3_1
                .eContents().get(0);

        private final RuleCall cDataClassesDataClassParserRuleCall_3_1_0_0 = (RuleCall) cDataClassesAssignment_3_1_0
                .eContents().get(0);

        private final Assignment cProcessClassesAssignment_3_1_1 = (Assignment) cAlternatives_3_1
                .eContents().get(1);

        private final RuleCall cProcessClassesProcessClassParserRuleCall_3_1_1_0 = (RuleCall) cProcessClassesAssignment_3_1_1
                .eContents().get(0);

        private final Assignment cClusterClassesAssignment_3_1_2 = (Assignment) cAlternatives_3_1
                .eContents().get(2);

        private final RuleCall cClusterClassesClusterClassParserRuleCall_3_1_2_0 = (RuleCall) cClusterClassesAssignment_3_1_2
                .eContents().get(0);

        //// === Main entry point =======
        //Poosl:
        //	{Poosl} (imports+=Import | importLibs+=ImportLib)* (dataClasses+=DataClass
        //	| processClasses+=ProcessClass
        //	| clusterClasses+=ClusterClass)* (clusterClasses+=System (dataClasses+=DataClass
        //	| processClasses+=ProcessClass
        //	| clusterClasses+=ClusterClass)*)?;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //{Poosl} (imports+=Import | importLibs+=ImportLib)* (dataClasses+=DataClass | processClasses+=ProcessClass |
        //clusterClasses+=ClusterClass)* (clusterClasses+=System (dataClasses+=DataClass | processClasses+=ProcessClass |
        //clusterClasses+=ClusterClass)*)?
        public Group getGroup() {
            return cGroup;
        }

        //{Poosl}
        public Action getPooslAction_0() {
            return cPooslAction_0;
        }

        //(imports+=Import | importLibs+=ImportLib)*
        public Alternatives getAlternatives_1() {
            return cAlternatives_1;
        }

        //imports+=Import
        public Assignment getImportsAssignment_1_0() {
            return cImportsAssignment_1_0;
        }

        //Import
        public RuleCall getImportsImportParserRuleCall_1_0_0() {
            return cImportsImportParserRuleCall_1_0_0;
        }

        //importLibs+=ImportLib
        public Assignment getImportLibsAssignment_1_1() {
            return cImportLibsAssignment_1_1;
        }

        //ImportLib
        public RuleCall getImportLibsImportLibParserRuleCall_1_1_0() {
            return cImportLibsImportLibParserRuleCall_1_1_0;
        }

        //(dataClasses+=DataClass | processClasses+=ProcessClass | clusterClasses+=ClusterClass)*
        public Alternatives getAlternatives_2() {
            return cAlternatives_2;
        }

        //dataClasses+=DataClass
        public Assignment getDataClassesAssignment_2_0() {
            return cDataClassesAssignment_2_0;
        }

        //DataClass
        public RuleCall getDataClassesDataClassParserRuleCall_2_0_0() {
            return cDataClassesDataClassParserRuleCall_2_0_0;
        }

        //processClasses+=ProcessClass
        public Assignment getProcessClassesAssignment_2_1() {
            return cProcessClassesAssignment_2_1;
        }

        //ProcessClass
        public RuleCall getProcessClassesProcessClassParserRuleCall_2_1_0() {
            return cProcessClassesProcessClassParserRuleCall_2_1_0;
        }

        //clusterClasses+=ClusterClass
        public Assignment getClusterClassesAssignment_2_2() {
            return cClusterClassesAssignment_2_2;
        }

        //ClusterClass
        public RuleCall getClusterClassesClusterClassParserRuleCall_2_2_0() {
            return cClusterClassesClusterClassParserRuleCall_2_2_0;
        }

        //(clusterClasses+=System (dataClasses+=DataClass | processClasses+=ProcessClass | clusterClasses+=ClusterClass)*)?
        public Group getGroup_3() {
            return cGroup_3;
        }

        //clusterClasses+=System
        public Assignment getClusterClassesAssignment_3_0() {
            return cClusterClassesAssignment_3_0;
        }

        //System
        public RuleCall getClusterClassesSystemParserRuleCall_3_0_0() {
            return cClusterClassesSystemParserRuleCall_3_0_0;
        }

        //(dataClasses+=DataClass | processClasses+=ProcessClass | clusterClasses+=ClusterClass)*
        public Alternatives getAlternatives_3_1() {
            return cAlternatives_3_1;
        }

        //dataClasses+=DataClass
        public Assignment getDataClassesAssignment_3_1_0() {
            return cDataClassesAssignment_3_1_0;
        }

        //DataClass
        public RuleCall getDataClassesDataClassParserRuleCall_3_1_0_0() {
            return cDataClassesDataClassParserRuleCall_3_1_0_0;
        }

        //processClasses+=ProcessClass
        public Assignment getProcessClassesAssignment_3_1_1() {
            return cProcessClassesAssignment_3_1_1;
        }

        //ProcessClass
        public RuleCall getProcessClassesProcessClassParserRuleCall_3_1_1_0() {
            return cProcessClassesProcessClassParserRuleCall_3_1_1_0;
        }

        //clusterClasses+=ClusterClass
        public Assignment getClusterClassesAssignment_3_1_2() {
            return cClusterClassesAssignment_3_1_2;
        }

        //ClusterClass
        public RuleCall getClusterClassesClusterClassParserRuleCall_3_1_2_0() {
            return cClusterClassesClusterClassParserRuleCall_3_1_2_0;
        }
    }

    public class ImportElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.Import");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Keyword cImportKeyword_0 = (Keyword) cGroup.eContents().get(0);

        private final Assignment cImportURIAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cImportURIPOOSL_STRINGTerminalRuleCall_1_0 = (RuleCall) cImportURIAssignment_1
                .eContents().get(0);

        //Import:
        //	'import' importURI=POOSL_STRING;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //'import' importURI=POOSL_STRING
        public Group getGroup() {
            return cGroup;
        }

        //'import'
        public Keyword getImportKeyword_0() {
            return cImportKeyword_0;
        }

        //importURI=POOSL_STRING
        public Assignment getImportURIAssignment_1() {
            return cImportURIAssignment_1;
        }

        //POOSL_STRING
        public RuleCall getImportURIPOOSL_STRINGTerminalRuleCall_1_0() {
            return cImportURIPOOSL_STRINGTerminalRuleCall_1_0;
        }
    }

    public class ImportLibElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.ImportLib");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Keyword cImportlibKeyword_0 = (Keyword) cGroup.eContents().get(0);

        private final Assignment cImportURIAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cImportURIPOOSL_STRINGTerminalRuleCall_1_0 = (RuleCall) cImportURIAssignment_1
                .eContents().get(0);

        //ImportLib Import:
        //	'importlib' importURI=POOSL_STRING;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //'importlib' importURI=POOSL_STRING
        public Group getGroup() {
            return cGroup;
        }

        //'importlib'
        public Keyword getImportlibKeyword_0() {
            return cImportlibKeyword_0;
        }

        //importURI=POOSL_STRING
        public Assignment getImportURIAssignment_1() {
            return cImportURIAssignment_1;
        }

        //POOSL_STRING
        public RuleCall getImportURIPOOSL_STRINGTerminalRuleCall_1_0() {
            return cImportURIPOOSL_STRINGTerminalRuleCall_1_0;
        }
    }

    public class DataClassElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.DataClass");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Assignment cAnnotationsAssignment_0 = (Assignment) cGroup.eContents().get(0);

        private final RuleCall cAnnotationsAnnotationParserRuleCall_0_0 = (RuleCall) cAnnotationsAssignment_0
                .eContents().get(0);

        private final Assignment cNativeAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final Keyword cNativeNativeKeyword_1_0 = (Keyword) cNativeAssignment_1.eContents()
                .get(0);

        private final Keyword cDataKeyword_2 = (Keyword) cGroup.eContents().get(2);

        private final Keyword cClassKeyword_3 = (Keyword) cGroup.eContents().get(3);

        private final Assignment cNameAssignment_4 = (Assignment) cGroup.eContents().get(4);

        private final RuleCall cNameIDENTIFIERParserRuleCall_4_0 = (RuleCall) cNameAssignment_4
                .eContents().get(0);

        private final Group cGroup_5 = (Group) cGroup.eContents().get(5);

        private final Keyword cExtendsKeyword_5_0 = (Keyword) cGroup_5.eContents().get(0);

        private final Assignment cSuperClassAssignment_5_1 = (Assignment) cGroup_5.eContents()
                .get(1);

        private final RuleCall cSuperClassIDENTIFIERParserRuleCall_5_1_0 = (RuleCall) cSuperClassAssignment_5_1
                .eContents().get(0);

        private final Keyword cVariablesKeyword_6 = (Keyword) cGroup.eContents().get(6);

        private final Group cGroup_7 = (Group) cGroup.eContents().get(7);

        private final Assignment cInstanceVariablesAssignment_7_0 = (Assignment) cGroup_7
                .eContents().get(0);

        private final RuleCall cInstanceVariablesDeclarationParserRuleCall_7_0_0 = (RuleCall) cInstanceVariablesAssignment_7_0
                .eContents().get(0);

        private final Group cGroup_7_1 = (Group) cGroup_7.eContents().get(1);

        private final Keyword cCommaKeyword_7_1_0 = (Keyword) cGroup_7_1.eContents().get(0);

        private final Assignment cInstanceVariablesAssignment_7_1_1 = (Assignment) cGroup_7_1
                .eContents().get(1);

        private final RuleCall cInstanceVariablesDeclarationParserRuleCall_7_1_1_0 = (RuleCall) cInstanceVariablesAssignment_7_1_1
                .eContents().get(0);

        private final Keyword cCommaKeyword_7_2 = (Keyword) cGroup_7.eContents().get(2);

        private final Keyword cMethodsKeyword_8 = (Keyword) cGroup.eContents().get(8);

        private final Alternatives cAlternatives_9 = (Alternatives) cGroup.eContents().get(9);

        private final Assignment cDataMethodsNamedAssignment_9_0 = (Assignment) cAlternatives_9
                .eContents().get(0);

        private final RuleCall cDataMethodsNamedDataMethodNamedParserRuleCall_9_0_0 = (RuleCall) cDataMethodsNamedAssignment_9_0
                .eContents().get(0);

        private final Assignment cDataMethodsUnaryOperatorAssignment_9_1 = (Assignment) cAlternatives_9
                .eContents().get(1);

        private final RuleCall cDataMethodsUnaryOperatorDataMethodUnaryOperatorParserRuleCall_9_1_0 = (RuleCall) cDataMethodsUnaryOperatorAssignment_9_1
                .eContents().get(0);

        private final Assignment cDataMethodsBinaryOperatorAssignment_9_2 = (Assignment) cAlternatives_9
                .eContents().get(2);

        private final RuleCall cDataMethodsBinaryOperatorDataMethodBinaryOperatorParserRuleCall_9_2_0 = (RuleCall) cDataMethodsBinaryOperatorAssignment_9_2
                .eContents().get(0);

        //// === Data Class =======
        //DataClass:
        //	annotations+=Annotation*
        //	native?='native'?
        //	'data' 'class' name=IDENTIFIER ('extends' superClass=IDENTIFIER)?
        //	'variables' (instanceVariables+=Declaration (','? instanceVariables+=Declaration)* ','?)?
        //	'methods' (dataMethodsNamed+=DataMethodNamed | dataMethodsUnaryOperator+=DataMethodUnaryOperator |
        //	dataMethodsBinaryOperator+=DataMethodBinaryOperator)*;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //annotations+=Annotation* native?='native'? 'data' 'class' name=IDENTIFIER ('extends' superClass=IDENTIFIER)? 'variables'
        //(instanceVariables+=Declaration (','? instanceVariables+=Declaration)* ','?)? 'methods'
        //(dataMethodsNamed+=DataMethodNamed | dataMethodsUnaryOperator+=DataMethodUnaryOperator |
        //dataMethodsBinaryOperator+=DataMethodBinaryOperator)*
        public Group getGroup() {
            return cGroup;
        }

        //annotations+=Annotation*
        public Assignment getAnnotationsAssignment_0() {
            return cAnnotationsAssignment_0;
        }

        //Annotation
        public RuleCall getAnnotationsAnnotationParserRuleCall_0_0() {
            return cAnnotationsAnnotationParserRuleCall_0_0;
        }

        //native?='native'?
        public Assignment getNativeAssignment_1() {
            return cNativeAssignment_1;
        }

        //'native'
        public Keyword getNativeNativeKeyword_1_0() {
            return cNativeNativeKeyword_1_0;
        }

        //'data'
        public Keyword getDataKeyword_2() {
            return cDataKeyword_2;
        }

        //'class'
        public Keyword getClassKeyword_3() {
            return cClassKeyword_3;
        }

        //name=IDENTIFIER
        public Assignment getNameAssignment_4() {
            return cNameAssignment_4;
        }

        //IDENTIFIER
        public RuleCall getNameIDENTIFIERParserRuleCall_4_0() {
            return cNameIDENTIFIERParserRuleCall_4_0;
        }

        //('extends' superClass=IDENTIFIER)?
        public Group getGroup_5() {
            return cGroup_5;
        }

        //'extends'
        public Keyword getExtendsKeyword_5_0() {
            return cExtendsKeyword_5_0;
        }

        //superClass=IDENTIFIER
        public Assignment getSuperClassAssignment_5_1() {
            return cSuperClassAssignment_5_1;
        }

        //IDENTIFIER
        public RuleCall getSuperClassIDENTIFIERParserRuleCall_5_1_0() {
            return cSuperClassIDENTIFIERParserRuleCall_5_1_0;
        }

        //'variables'
        public Keyword getVariablesKeyword_6() {
            return cVariablesKeyword_6;
        }

        //(instanceVariables+=Declaration (','? instanceVariables+=Declaration)* ','?)?
        public Group getGroup_7() {
            return cGroup_7;
        }

        //instanceVariables+=Declaration
        public Assignment getInstanceVariablesAssignment_7_0() {
            return cInstanceVariablesAssignment_7_0;
        }

        //Declaration
        public RuleCall getInstanceVariablesDeclarationParserRuleCall_7_0_0() {
            return cInstanceVariablesDeclarationParserRuleCall_7_0_0;
        }

        //(','? instanceVariables+=Declaration)*
        public Group getGroup_7_1() {
            return cGroup_7_1;
        }

        //','?
        public Keyword getCommaKeyword_7_1_0() {
            return cCommaKeyword_7_1_0;
        }

        //instanceVariables+=Declaration
        public Assignment getInstanceVariablesAssignment_7_1_1() {
            return cInstanceVariablesAssignment_7_1_1;
        }

        //Declaration
        public RuleCall getInstanceVariablesDeclarationParserRuleCall_7_1_1_0() {
            return cInstanceVariablesDeclarationParserRuleCall_7_1_1_0;
        }

        //','?
        public Keyword getCommaKeyword_7_2() {
            return cCommaKeyword_7_2;
        }

        //'methods'
        public Keyword getMethodsKeyword_8() {
            return cMethodsKeyword_8;
        }

        //(dataMethodsNamed+=DataMethodNamed | dataMethodsUnaryOperator+=DataMethodUnaryOperator |
        //dataMethodsBinaryOperator+=DataMethodBinaryOperator)*
        public Alternatives getAlternatives_9() {
            return cAlternatives_9;
        }

        //dataMethodsNamed+=DataMethodNamed
        public Assignment getDataMethodsNamedAssignment_9_0() {
            return cDataMethodsNamedAssignment_9_0;
        }

        //DataMethodNamed
        public RuleCall getDataMethodsNamedDataMethodNamedParserRuleCall_9_0_0() {
            return cDataMethodsNamedDataMethodNamedParserRuleCall_9_0_0;
        }

        //dataMethodsUnaryOperator+=DataMethodUnaryOperator
        public Assignment getDataMethodsUnaryOperatorAssignment_9_1() {
            return cDataMethodsUnaryOperatorAssignment_9_1;
        }

        //DataMethodUnaryOperator
        public RuleCall getDataMethodsUnaryOperatorDataMethodUnaryOperatorParserRuleCall_9_1_0() {
            return cDataMethodsUnaryOperatorDataMethodUnaryOperatorParserRuleCall_9_1_0;
        }

        //dataMethodsBinaryOperator+=DataMethodBinaryOperator
        public Assignment getDataMethodsBinaryOperatorAssignment_9_2() {
            return cDataMethodsBinaryOperatorAssignment_9_2;
        }

        //DataMethodBinaryOperator
        public RuleCall getDataMethodsBinaryOperatorDataMethodBinaryOperatorParserRuleCall_9_2_0() {
            return cDataMethodsBinaryOperatorDataMethodBinaryOperatorParserRuleCall_9_2_0;
        }
    }

    public class DataMethodNamedElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.DataMethodNamed");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Assignment cAnnotationsAssignment_0 = (Assignment) cGroup.eContents().get(0);

        private final RuleCall cAnnotationsAnnotationParserRuleCall_0_0 = (RuleCall) cAnnotationsAssignment_0
                .eContents().get(0);

        private final Assignment cNativeAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final Keyword cNativeNativeKeyword_1_0 = (Keyword) cNativeAssignment_1.eContents()
                .get(0);

        private final Assignment cNameAssignment_2 = (Assignment) cGroup.eContents().get(2);

        private final RuleCall cNameIDENTIFIERParserRuleCall_2_0 = (RuleCall) cNameAssignment_2
                .eContents().get(0);

        private final Group cGroup_3 = (Group) cGroup.eContents().get(3);

        private final Keyword cLeftParenthesisKeyword_3_0 = (Keyword) cGroup_3.eContents().get(0);

        private final Group cGroup_3_1 = (Group) cGroup_3.eContents().get(1);

        private final Assignment cParametersAssignment_3_1_0 = (Assignment) cGroup_3_1.eContents()
                .get(0);

        private final RuleCall cParametersDeclarationParserRuleCall_3_1_0_0 = (RuleCall) cParametersAssignment_3_1_0
                .eContents().get(0);

        private final Group cGroup_3_1_1 = (Group) cGroup_3_1.eContents().get(1);

        private final Keyword cCommaKeyword_3_1_1_0 = (Keyword) cGroup_3_1_1.eContents().get(0);

        private final Assignment cParametersAssignment_3_1_1_1 = (Assignment) cGroup_3_1_1
                .eContents().get(1);

        private final RuleCall cParametersDeclarationParserRuleCall_3_1_1_1_0 = (RuleCall) cParametersAssignment_3_1_1_1
                .eContents().get(0);

        private final Keyword cRightParenthesisKeyword_3_2 = (Keyword) cGroup_3.eContents().get(2);

        private final Keyword cColonKeyword_4 = (Keyword) cGroup.eContents().get(4);

        private final Assignment cReturnTypeAssignment_5 = (Assignment) cGroup.eContents().get(5);

        private final RuleCall cReturnTypeIDENTIFIERParserRuleCall_5_0 = (RuleCall) cReturnTypeAssignment_5
                .eContents().get(0);

        private final Group cGroup_6 = (Group) cGroup.eContents().get(6);

        private final Keyword cVerticalLineKeyword_6_0 = (Keyword) cGroup_6.eContents().get(0);

        private final Group cGroup_6_1 = (Group) cGroup_6.eContents().get(1);

        private final Assignment cLocalVariablesAssignment_6_1_0 = (Assignment) cGroup_6_1
                .eContents().get(0);

        private final RuleCall cLocalVariablesDeclarationParserRuleCall_6_1_0_0 = (RuleCall) cLocalVariablesAssignment_6_1_0
                .eContents().get(0);

        private final Group cGroup_6_1_1 = (Group) cGroup_6_1.eContents().get(1);

        private final Keyword cCommaKeyword_6_1_1_0 = (Keyword) cGroup_6_1_1.eContents().get(0);

        private final Assignment cLocalVariablesAssignment_6_1_1_1 = (Assignment) cGroup_6_1_1
                .eContents().get(1);

        private final RuleCall cLocalVariablesDeclarationParserRuleCall_6_1_1_1_0 = (RuleCall) cLocalVariablesAssignment_6_1_1_1
                .eContents().get(0);

        private final Keyword cVerticalLineKeyword_6_2 = (Keyword) cGroup_6.eContents().get(2);

        private final Assignment cBodyAssignment_7 = (Assignment) cGroup.eContents().get(7);

        private final RuleCall cBodyExpressionParserRuleCall_7_0 = (RuleCall) cBodyAssignment_7
                .eContents().get(0);

        //DataMethodNamed:
        //	annotations+=Annotation*
        //	native?='native'?
        //	name=IDENTIFIER ('(' (parameters+=Declaration (',' parameters+=Declaration)*)? ')')?
        //	':' returnType=IDENTIFIER ('|' (localVariables+=Declaration (',' localVariables+=Declaration)*)? '|')?
        //	body=Expression?;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //annotations+=Annotation* native?='native'? name=IDENTIFIER ('(' (parameters+=Declaration (','
        //parameters+=Declaration)*)? ')')? ':' returnType=IDENTIFIER ('|' (localVariables+=Declaration (','
        //localVariables+=Declaration)*)? '|')? body=Expression?
        public Group getGroup() {
            return cGroup;
        }

        //annotations+=Annotation*
        public Assignment getAnnotationsAssignment_0() {
            return cAnnotationsAssignment_0;
        }

        //Annotation
        public RuleCall getAnnotationsAnnotationParserRuleCall_0_0() {
            return cAnnotationsAnnotationParserRuleCall_0_0;
        }

        //native?='native'?
        public Assignment getNativeAssignment_1() {
            return cNativeAssignment_1;
        }

        //'native'
        public Keyword getNativeNativeKeyword_1_0() {
            return cNativeNativeKeyword_1_0;
        }

        //name=IDENTIFIER
        public Assignment getNameAssignment_2() {
            return cNameAssignment_2;
        }

        //IDENTIFIER
        public RuleCall getNameIDENTIFIERParserRuleCall_2_0() {
            return cNameIDENTIFIERParserRuleCall_2_0;
        }

        //('(' (parameters+=Declaration (',' parameters+=Declaration)*)? ')')?
        public Group getGroup_3() {
            return cGroup_3;
        }

        //'('
        public Keyword getLeftParenthesisKeyword_3_0() {
            return cLeftParenthesisKeyword_3_0;
        }

        //(parameters+=Declaration (',' parameters+=Declaration)*)?
        public Group getGroup_3_1() {
            return cGroup_3_1;
        }

        //parameters+=Declaration
        public Assignment getParametersAssignment_3_1_0() {
            return cParametersAssignment_3_1_0;
        }

        //Declaration
        public RuleCall getParametersDeclarationParserRuleCall_3_1_0_0() {
            return cParametersDeclarationParserRuleCall_3_1_0_0;
        }

        //(',' parameters+=Declaration)*
        public Group getGroup_3_1_1() {
            return cGroup_3_1_1;
        }

        //','
        public Keyword getCommaKeyword_3_1_1_0() {
            return cCommaKeyword_3_1_1_0;
        }

        //parameters+=Declaration
        public Assignment getParametersAssignment_3_1_1_1() {
            return cParametersAssignment_3_1_1_1;
        }

        //Declaration
        public RuleCall getParametersDeclarationParserRuleCall_3_1_1_1_0() {
            return cParametersDeclarationParserRuleCall_3_1_1_1_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_3_2() {
            return cRightParenthesisKeyword_3_2;
        }

        //':'
        public Keyword getColonKeyword_4() {
            return cColonKeyword_4;
        }

        //returnType=IDENTIFIER
        public Assignment getReturnTypeAssignment_5() {
            return cReturnTypeAssignment_5;
        }

        //IDENTIFIER
        public RuleCall getReturnTypeIDENTIFIERParserRuleCall_5_0() {
            return cReturnTypeIDENTIFIERParserRuleCall_5_0;
        }

        //('|' (localVariables+=Declaration (',' localVariables+=Declaration)*)? '|')?
        public Group getGroup_6() {
            return cGroup_6;
        }

        //'|'
        public Keyword getVerticalLineKeyword_6_0() {
            return cVerticalLineKeyword_6_0;
        }

        //(localVariables+=Declaration (',' localVariables+=Declaration)*)?
        public Group getGroup_6_1() {
            return cGroup_6_1;
        }

        //localVariables+=Declaration
        public Assignment getLocalVariablesAssignment_6_1_0() {
            return cLocalVariablesAssignment_6_1_0;
        }

        //Declaration
        public RuleCall getLocalVariablesDeclarationParserRuleCall_6_1_0_0() {
            return cLocalVariablesDeclarationParserRuleCall_6_1_0_0;
        }

        //(',' localVariables+=Declaration)*
        public Group getGroup_6_1_1() {
            return cGroup_6_1_1;
        }

        //','
        public Keyword getCommaKeyword_6_1_1_0() {
            return cCommaKeyword_6_1_1_0;
        }

        //localVariables+=Declaration
        public Assignment getLocalVariablesAssignment_6_1_1_1() {
            return cLocalVariablesAssignment_6_1_1_1;
        }

        //Declaration
        public RuleCall getLocalVariablesDeclarationParserRuleCall_6_1_1_1_0() {
            return cLocalVariablesDeclarationParserRuleCall_6_1_1_1_0;
        }

        //'|'
        public Keyword getVerticalLineKeyword_6_2() {
            return cVerticalLineKeyword_6_2;
        }

        //body=Expression?
        public Assignment getBodyAssignment_7() {
            return cBodyAssignment_7;
        }

        //Expression
        public RuleCall getBodyExpressionParserRuleCall_7_0() {
            return cBodyExpressionParserRuleCall_7_0;
        }
    }

    public class DataMethodBinaryOperatorElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.DataMethodBinaryOperator");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Assignment cAnnotationsAssignment_0 = (Assignment) cGroup.eContents().get(0);

        private final RuleCall cAnnotationsAnnotationParserRuleCall_0_0 = (RuleCall) cAnnotationsAssignment_0
                .eContents().get(0);

        private final Assignment cNativeAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final Keyword cNativeNativeKeyword_1_0 = (Keyword) cNativeAssignment_1.eContents()
                .get(0);

        private final Assignment cNameAssignment_2 = (Assignment) cGroup.eContents().get(2);

        private final RuleCall cNameOperatorBinaryEnumRuleCall_2_0 = (RuleCall) cNameAssignment_2
                .eContents().get(0);

        private final Keyword cLeftParenthesisKeyword_3 = (Keyword) cGroup.eContents().get(3);

        private final Assignment cParametersAssignment_4 = (Assignment) cGroup.eContents().get(4);

        private final RuleCall cParametersDeclarationParserRuleCall_4_0 = (RuleCall) cParametersAssignment_4
                .eContents().get(0);

        private final Group cGroup_5 = (Group) cGroup.eContents().get(5);

        private final Keyword cCommaKeyword_5_0 = (Keyword) cGroup_5.eContents().get(0);

        private final Assignment cParametersAssignment_5_1 = (Assignment) cGroup_5.eContents()
                .get(1);

        private final RuleCall cParametersDeclarationParserRuleCall_5_1_0 = (RuleCall) cParametersAssignment_5_1
                .eContents().get(0);

        private final Keyword cRightParenthesisKeyword_6 = (Keyword) cGroup.eContents().get(6);

        private final Keyword cColonKeyword_7 = (Keyword) cGroup.eContents().get(7);

        private final Assignment cReturnTypeAssignment_8 = (Assignment) cGroup.eContents().get(8);

        private final RuleCall cReturnTypeIDENTIFIERParserRuleCall_8_0 = (RuleCall) cReturnTypeAssignment_8
                .eContents().get(0);

        private final Group cGroup_9 = (Group) cGroup.eContents().get(9);

        private final Keyword cVerticalLineKeyword_9_0 = (Keyword) cGroup_9.eContents().get(0);

        private final Group cGroup_9_1 = (Group) cGroup_9.eContents().get(1);

        private final Assignment cLocalVariablesAssignment_9_1_0 = (Assignment) cGroup_9_1
                .eContents().get(0);

        private final RuleCall cLocalVariablesDeclarationParserRuleCall_9_1_0_0 = (RuleCall) cLocalVariablesAssignment_9_1_0
                .eContents().get(0);

        private final Group cGroup_9_1_1 = (Group) cGroup_9_1.eContents().get(1);

        private final Keyword cCommaKeyword_9_1_1_0 = (Keyword) cGroup_9_1_1.eContents().get(0);

        private final Assignment cLocalVariablesAssignment_9_1_1_1 = (Assignment) cGroup_9_1_1
                .eContents().get(1);

        private final RuleCall cLocalVariablesDeclarationParserRuleCall_9_1_1_1_0 = (RuleCall) cLocalVariablesAssignment_9_1_1_1
                .eContents().get(0);

        private final Keyword cVerticalLineKeyword_9_2 = (Keyword) cGroup_9.eContents().get(2);

        private final Assignment cBodyAssignment_10 = (Assignment) cGroup.eContents().get(10);

        private final RuleCall cBodyExpressionParserRuleCall_10_0 = (RuleCall) cBodyAssignment_10
                .eContents().get(0);

        //DataMethodBinaryOperator:
        //	annotations+=Annotation*
        //	native?='native'?
        //	name=OperatorBinary
        //	'(' parameters+=Declaration (',' parameters+=Declaration)* ')'
        //	':' returnType=IDENTIFIER ('|' (localVariables+=Declaration (',' localVariables+=Declaration)*)? '|')?
        //	body=Expression?;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //annotations+=Annotation* native?='native'? name=OperatorBinary '(' parameters+=Declaration (','
        //parameters+=Declaration)* ')' ':' returnType=IDENTIFIER ('|' (localVariables+=Declaration (','
        //localVariables+=Declaration)*)? '|')? body=Expression?
        public Group getGroup() {
            return cGroup;
        }

        //annotations+=Annotation*
        public Assignment getAnnotationsAssignment_0() {
            return cAnnotationsAssignment_0;
        }

        //Annotation
        public RuleCall getAnnotationsAnnotationParserRuleCall_0_0() {
            return cAnnotationsAnnotationParserRuleCall_0_0;
        }

        //native?='native'?
        public Assignment getNativeAssignment_1() {
            return cNativeAssignment_1;
        }

        //'native'
        public Keyword getNativeNativeKeyword_1_0() {
            return cNativeNativeKeyword_1_0;
        }

        //name=OperatorBinary
        public Assignment getNameAssignment_2() {
            return cNameAssignment_2;
        }

        //OperatorBinary
        public RuleCall getNameOperatorBinaryEnumRuleCall_2_0() {
            return cNameOperatorBinaryEnumRuleCall_2_0;
        }

        //'('
        public Keyword getLeftParenthesisKeyword_3() {
            return cLeftParenthesisKeyword_3;
        }

        //parameters+=Declaration
        public Assignment getParametersAssignment_4() {
            return cParametersAssignment_4;
        }

        //Declaration
        public RuleCall getParametersDeclarationParserRuleCall_4_0() {
            return cParametersDeclarationParserRuleCall_4_0;
        }

        //(',' parameters+=Declaration)*
        public Group getGroup_5() {
            return cGroup_5;
        }

        //','
        public Keyword getCommaKeyword_5_0() {
            return cCommaKeyword_5_0;
        }

        //parameters+=Declaration
        public Assignment getParametersAssignment_5_1() {
            return cParametersAssignment_5_1;
        }

        //Declaration
        public RuleCall getParametersDeclarationParserRuleCall_5_1_0() {
            return cParametersDeclarationParserRuleCall_5_1_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_6() {
            return cRightParenthesisKeyword_6;
        }

        //':'
        public Keyword getColonKeyword_7() {
            return cColonKeyword_7;
        }

        //returnType=IDENTIFIER
        public Assignment getReturnTypeAssignment_8() {
            return cReturnTypeAssignment_8;
        }

        //IDENTIFIER
        public RuleCall getReturnTypeIDENTIFIERParserRuleCall_8_0() {
            return cReturnTypeIDENTIFIERParserRuleCall_8_0;
        }

        //('|' (localVariables+=Declaration (',' localVariables+=Declaration)*)? '|')?
        public Group getGroup_9() {
            return cGroup_9;
        }

        //'|'
        public Keyword getVerticalLineKeyword_9_0() {
            return cVerticalLineKeyword_9_0;
        }

        //(localVariables+=Declaration (',' localVariables+=Declaration)*)?
        public Group getGroup_9_1() {
            return cGroup_9_1;
        }

        //localVariables+=Declaration
        public Assignment getLocalVariablesAssignment_9_1_0() {
            return cLocalVariablesAssignment_9_1_0;
        }

        //Declaration
        public RuleCall getLocalVariablesDeclarationParserRuleCall_9_1_0_0() {
            return cLocalVariablesDeclarationParserRuleCall_9_1_0_0;
        }

        //(',' localVariables+=Declaration)*
        public Group getGroup_9_1_1() {
            return cGroup_9_1_1;
        }

        //','
        public Keyword getCommaKeyword_9_1_1_0() {
            return cCommaKeyword_9_1_1_0;
        }

        //localVariables+=Declaration
        public Assignment getLocalVariablesAssignment_9_1_1_1() {
            return cLocalVariablesAssignment_9_1_1_1;
        }

        //Declaration
        public RuleCall getLocalVariablesDeclarationParserRuleCall_9_1_1_1_0() {
            return cLocalVariablesDeclarationParserRuleCall_9_1_1_1_0;
        }

        //'|'
        public Keyword getVerticalLineKeyword_9_2() {
            return cVerticalLineKeyword_9_2;
        }

        //body=Expression?
        public Assignment getBodyAssignment_10() {
            return cBodyAssignment_10;
        }

        //Expression
        public RuleCall getBodyExpressionParserRuleCall_10_0() {
            return cBodyExpressionParserRuleCall_10_0;
        }
    }

    public class DataMethodUnaryOperatorElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.DataMethodUnaryOperator");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Assignment cAnnotationsAssignment_0 = (Assignment) cGroup.eContents().get(0);

        private final RuleCall cAnnotationsAnnotationParserRuleCall_0_0 = (RuleCall) cAnnotationsAssignment_0
                .eContents().get(0);

        private final Assignment cNativeAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final Keyword cNativeNativeKeyword_1_0 = (Keyword) cNativeAssignment_1.eContents()
                .get(0);

        private final Assignment cNameAssignment_2 = (Assignment) cGroup.eContents().get(2);

        private final RuleCall cNameOperatorUnaryEnumRuleCall_2_0 = (RuleCall) cNameAssignment_2
                .eContents().get(0);

        private final Group cGroup_3 = (Group) cGroup.eContents().get(3);

        private final Keyword cLeftParenthesisKeyword_3_0 = (Keyword) cGroup_3.eContents().get(0);

        private final Keyword cRightParenthesisKeyword_3_1 = (Keyword) cGroup_3.eContents().get(1);

        private final Keyword cColonKeyword_4 = (Keyword) cGroup.eContents().get(4);

        private final Assignment cReturnTypeAssignment_5 = (Assignment) cGroup.eContents().get(5);

        private final RuleCall cReturnTypeIDENTIFIERParserRuleCall_5_0 = (RuleCall) cReturnTypeAssignment_5
                .eContents().get(0);

        private final Group cGroup_6 = (Group) cGroup.eContents().get(6);

        private final Keyword cVerticalLineKeyword_6_0 = (Keyword) cGroup_6.eContents().get(0);

        private final Group cGroup_6_1 = (Group) cGroup_6.eContents().get(1);

        private final Assignment cLocalVariablesAssignment_6_1_0 = (Assignment) cGroup_6_1
                .eContents().get(0);

        private final RuleCall cLocalVariablesDeclarationParserRuleCall_6_1_0_0 = (RuleCall) cLocalVariablesAssignment_6_1_0
                .eContents().get(0);

        private final Group cGroup_6_1_1 = (Group) cGroup_6_1.eContents().get(1);

        private final Keyword cCommaKeyword_6_1_1_0 = (Keyword) cGroup_6_1_1.eContents().get(0);

        private final Assignment cLocalVariablesAssignment_6_1_1_1 = (Assignment) cGroup_6_1_1
                .eContents().get(1);

        private final RuleCall cLocalVariablesDeclarationParserRuleCall_6_1_1_1_0 = (RuleCall) cLocalVariablesAssignment_6_1_1_1
                .eContents().get(0);

        private final Keyword cVerticalLineKeyword_6_2 = (Keyword) cGroup_6.eContents().get(2);

        private final Assignment cBodyAssignment_7 = (Assignment) cGroup.eContents().get(7);

        private final RuleCall cBodyExpressionParserRuleCall_7_0 = (RuleCall) cBodyAssignment_7
                .eContents().get(0);

        //DataMethodUnaryOperator:
        //	annotations+=Annotation*
        //	native?='native'?
        //	name=OperatorUnary ('(' ')')?
        //	':' returnType=IDENTIFIER ('|' (localVariables+=Declaration (',' localVariables+=Declaration)*)? '|')?
        //	body=Expression?;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //annotations+=Annotation* native?='native'? name=OperatorUnary ('(' ')')? ':' returnType=IDENTIFIER ('|'
        //(localVariables+=Declaration (',' localVariables+=Declaration)*)? '|')? body=Expression?
        public Group getGroup() {
            return cGroup;
        }

        //annotations+=Annotation*
        public Assignment getAnnotationsAssignment_0() {
            return cAnnotationsAssignment_0;
        }

        //Annotation
        public RuleCall getAnnotationsAnnotationParserRuleCall_0_0() {
            return cAnnotationsAnnotationParserRuleCall_0_0;
        }

        //native?='native'?
        public Assignment getNativeAssignment_1() {
            return cNativeAssignment_1;
        }

        //'native'
        public Keyword getNativeNativeKeyword_1_0() {
            return cNativeNativeKeyword_1_0;
        }

        //name=OperatorUnary
        public Assignment getNameAssignment_2() {
            return cNameAssignment_2;
        }

        //OperatorUnary
        public RuleCall getNameOperatorUnaryEnumRuleCall_2_0() {
            return cNameOperatorUnaryEnumRuleCall_2_0;
        }

        //('(' ')')?
        public Group getGroup_3() {
            return cGroup_3;
        }

        //'('
        public Keyword getLeftParenthesisKeyword_3_0() {
            return cLeftParenthesisKeyword_3_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_3_1() {
            return cRightParenthesisKeyword_3_1;
        }

        //':'
        public Keyword getColonKeyword_4() {
            return cColonKeyword_4;
        }

        //returnType=IDENTIFIER
        public Assignment getReturnTypeAssignment_5() {
            return cReturnTypeAssignment_5;
        }

        //IDENTIFIER
        public RuleCall getReturnTypeIDENTIFIERParserRuleCall_5_0() {
            return cReturnTypeIDENTIFIERParserRuleCall_5_0;
        }

        //('|' (localVariables+=Declaration (',' localVariables+=Declaration)*)? '|')?
        public Group getGroup_6() {
            return cGroup_6;
        }

        //'|'
        public Keyword getVerticalLineKeyword_6_0() {
            return cVerticalLineKeyword_6_0;
        }

        //(localVariables+=Declaration (',' localVariables+=Declaration)*)?
        public Group getGroup_6_1() {
            return cGroup_6_1;
        }

        //localVariables+=Declaration
        public Assignment getLocalVariablesAssignment_6_1_0() {
            return cLocalVariablesAssignment_6_1_0;
        }

        //Declaration
        public RuleCall getLocalVariablesDeclarationParserRuleCall_6_1_0_0() {
            return cLocalVariablesDeclarationParserRuleCall_6_1_0_0;
        }

        //(',' localVariables+=Declaration)*
        public Group getGroup_6_1_1() {
            return cGroup_6_1_1;
        }

        //','
        public Keyword getCommaKeyword_6_1_1_0() {
            return cCommaKeyword_6_1_1_0;
        }

        //localVariables+=Declaration
        public Assignment getLocalVariablesAssignment_6_1_1_1() {
            return cLocalVariablesAssignment_6_1_1_1;
        }

        //Declaration
        public RuleCall getLocalVariablesDeclarationParserRuleCall_6_1_1_1_0() {
            return cLocalVariablesDeclarationParserRuleCall_6_1_1_1_0;
        }

        //'|'
        public Keyword getVerticalLineKeyword_6_2() {
            return cVerticalLineKeyword_6_2;
        }

        //body=Expression?
        public Assignment getBodyAssignment_7() {
            return cBodyAssignment_7;
        }

        //Expression
        public RuleCall getBodyExpressionParserRuleCall_7_0() {
            return cBodyExpressionParserRuleCall_7_0;
        }
    }

    public class DeclarationElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.Declaration");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Group cGroup_0 = (Group) cGroup.eContents().get(0);

        private final Assignment cVariablesAssignment_0_0 = (Assignment) cGroup_0.eContents()
                .get(0);

        private final RuleCall cVariablesVariableParserRuleCall_0_0_0 = (RuleCall) cVariablesAssignment_0_0
                .eContents().get(0);

        private final Group cGroup_0_1 = (Group) cGroup_0.eContents().get(1);

        private final Keyword cCommaKeyword_0_1_0 = (Keyword) cGroup_0_1.eContents().get(0);

        private final Assignment cVariablesAssignment_0_1_1 = (Assignment) cGroup_0_1.eContents()
                .get(1);

        private final RuleCall cVariablesVariableParserRuleCall_0_1_1_0 = (RuleCall) cVariablesAssignment_0_1_1
                .eContents().get(0);

        private final Keyword cColonKeyword_1 = (Keyword) cGroup.eContents().get(1);

        private final Assignment cTypeAssignment_2 = (Assignment) cGroup.eContents().get(2);

        private final RuleCall cTypeIDENTIFIERParserRuleCall_2_0 = (RuleCall) cTypeAssignment_2
                .eContents().get(0);

        //Declaration:
        //	(variables+=Variable (',' variables+=Variable)*)? ':' type=IDENTIFIER;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //(variables+=Variable (',' variables+=Variable)*)? ':' type=IDENTIFIER
        public Group getGroup() {
            return cGroup;
        }

        //(variables+=Variable (',' variables+=Variable)*)?
        public Group getGroup_0() {
            return cGroup_0;
        }

        //variables+=Variable
        public Assignment getVariablesAssignment_0_0() {
            return cVariablesAssignment_0_0;
        }

        //Variable
        public RuleCall getVariablesVariableParserRuleCall_0_0_0() {
            return cVariablesVariableParserRuleCall_0_0_0;
        }

        //(',' variables+=Variable)*
        public Group getGroup_0_1() {
            return cGroup_0_1;
        }

        //','
        public Keyword getCommaKeyword_0_1_0() {
            return cCommaKeyword_0_1_0;
        }

        //variables+=Variable
        public Assignment getVariablesAssignment_0_1_1() {
            return cVariablesAssignment_0_1_1;
        }

        //Variable
        public RuleCall getVariablesVariableParserRuleCall_0_1_1_0() {
            return cVariablesVariableParserRuleCall_0_1_1_0;
        }

        //':'
        public Keyword getColonKeyword_1() {
            return cColonKeyword_1;
        }

        //type=IDENTIFIER
        public Assignment getTypeAssignment_2() {
            return cTypeAssignment_2;
        }

        //IDENTIFIER
        public RuleCall getTypeIDENTIFIERParserRuleCall_2_0() {
            return cTypeIDENTIFIERParserRuleCall_2_0;
        }
    }

    public class VariableElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.Variable");

        private final Assignment cNameAssignment = (Assignment) rule.eContents().get(1);

        private final RuleCall cNameIDENTIFIERParserRuleCall_0 = (RuleCall) cNameAssignment
                .eContents().get(0);

        //Variable:
        //	name=IDENTIFIER;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //name=IDENTIFIER
        public Assignment getNameAssignment() {
            return cNameAssignment;
        }

        //IDENTIFIER
        public RuleCall getNameIDENTIFIERParserRuleCall_0() {
            return cNameIDENTIFIERParserRuleCall_0;
        }
    }

    public class AnnotationElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.Annotation");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Keyword cCommercialAtKeyword_0 = (Keyword) cGroup.eContents().get(0);

        private final Assignment cNameAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cNameIDENTIFIERParserRuleCall_1_0 = (RuleCall) cNameAssignment_1
                .eContents().get(0);

        private final Group cGroup_2 = (Group) cGroup.eContents().get(2);

        private final Keyword cLeftParenthesisKeyword_2_0 = (Keyword) cGroup_2.eContents().get(0);

        private final Group cGroup_2_1 = (Group) cGroup_2.eContents().get(1);

        private final Assignment cArgumentsAssignment_2_1_0 = (Assignment) cGroup_2_1.eContents()
                .get(0);

        private final RuleCall cArgumentsExpressionConstantParserRuleCall_2_1_0_0 = (RuleCall) cArgumentsAssignment_2_1_0
                .eContents().get(0);

        private final Group cGroup_2_1_1 = (Group) cGroup_2_1.eContents().get(1);

        private final Keyword cCommaKeyword_2_1_1_0 = (Keyword) cGroup_2_1_1.eContents().get(0);

        private final Assignment cArgumentsAssignment_2_1_1_1 = (Assignment) cGroup_2_1_1
                .eContents().get(1);

        private final RuleCall cArgumentsExpressionConstantParserRuleCall_2_1_1_1_0 = (RuleCall) cArgumentsAssignment_2_1_1_1
                .eContents().get(0);

        private final Keyword cRightParenthesisKeyword_2_2 = (Keyword) cGroup_2.eContents().get(2);

        //Annotation:
        //	'@' name=IDENTIFIER ('(' (arguments+=ExpressionConstant (',' arguments+=ExpressionConstant)*)?
        //	')')?;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //'@' name=IDENTIFIER ('(' (arguments+=ExpressionConstant (',' arguments+=ExpressionConstant)*)? ')')?
        public Group getGroup() {
            return cGroup;
        }

        //'@'
        public Keyword getCommercialAtKeyword_0() {
            return cCommercialAtKeyword_0;
        }

        //name=IDENTIFIER
        public Assignment getNameAssignment_1() {
            return cNameAssignment_1;
        }

        //IDENTIFIER
        public RuleCall getNameIDENTIFIERParserRuleCall_1_0() {
            return cNameIDENTIFIERParserRuleCall_1_0;
        }

        //('(' (arguments+=ExpressionConstant (',' arguments+=ExpressionConstant)*)? ')')?
        public Group getGroup_2() {
            return cGroup_2;
        }

        //'('
        public Keyword getLeftParenthesisKeyword_2_0() {
            return cLeftParenthesisKeyword_2_0;
        }

        //(arguments+=ExpressionConstant (',' arguments+=ExpressionConstant)*)?
        public Group getGroup_2_1() {
            return cGroup_2_1;
        }

        //arguments+=ExpressionConstant
        public Assignment getArgumentsAssignment_2_1_0() {
            return cArgumentsAssignment_2_1_0;
        }

        //ExpressionConstant
        public RuleCall getArgumentsExpressionConstantParserRuleCall_2_1_0_0() {
            return cArgumentsExpressionConstantParserRuleCall_2_1_0_0;
        }

        //(',' arguments+=ExpressionConstant)*
        public Group getGroup_2_1_1() {
            return cGroup_2_1_1;
        }

        //','
        public Keyword getCommaKeyword_2_1_1_0() {
            return cCommaKeyword_2_1_1_0;
        }

        //arguments+=ExpressionConstant
        public Assignment getArgumentsAssignment_2_1_1_1() {
            return cArgumentsAssignment_2_1_1_1;
        }

        //ExpressionConstant
        public RuleCall getArgumentsExpressionConstantParserRuleCall_2_1_1_1_0() {
            return cArgumentsExpressionConstantParserRuleCall_2_1_1_1_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_2_2() {
            return cRightParenthesisKeyword_2_2;
        }
    }

    public class ProcessClassElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.ProcessClass");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Assignment cAnnotationsAssignment_0 = (Assignment) cGroup.eContents().get(0);

        private final RuleCall cAnnotationsAnnotationParserRuleCall_0_0 = (RuleCall) cAnnotationsAssignment_0
                .eContents().get(0);

        private final Keyword cProcessKeyword_1 = (Keyword) cGroup.eContents().get(1);

        private final Keyword cClassKeyword_2 = (Keyword) cGroup.eContents().get(2);

        private final Assignment cNameAssignment_3 = (Assignment) cGroup.eContents().get(3);

        private final RuleCall cNameIDENTIFIERParserRuleCall_3_0 = (RuleCall) cNameAssignment_3
                .eContents().get(0);

        private final Group cGroup_4 = (Group) cGroup.eContents().get(4);

        private final Keyword cLeftParenthesisKeyword_4_0 = (Keyword) cGroup_4.eContents().get(0);

        private final Group cGroup_4_1 = (Group) cGroup_4.eContents().get(1);

        private final Assignment cParametersAssignment_4_1_0 = (Assignment) cGroup_4_1.eContents()
                .get(0);

        private final RuleCall cParametersDeclarationParserRuleCall_4_1_0_0 = (RuleCall) cParametersAssignment_4_1_0
                .eContents().get(0);

        private final Group cGroup_4_1_1 = (Group) cGroup_4_1.eContents().get(1);

        private final Keyword cCommaKeyword_4_1_1_0 = (Keyword) cGroup_4_1_1.eContents().get(0);

        private final Assignment cParametersAssignment_4_1_1_1 = (Assignment) cGroup_4_1_1
                .eContents().get(1);

        private final RuleCall cParametersDeclarationParserRuleCall_4_1_1_1_0 = (RuleCall) cParametersAssignment_4_1_1_1
                .eContents().get(0);

        private final Keyword cRightParenthesisKeyword_4_2 = (Keyword) cGroup_4.eContents().get(2);

        private final Group cGroup_5 = (Group) cGroup.eContents().get(5);

        private final Keyword cExtendsKeyword_5_0 = (Keyword) cGroup_5.eContents().get(0);

        private final Assignment cSuperClassAssignment_5_1 = (Assignment) cGroup_5.eContents()
                .get(1);

        private final RuleCall cSuperClassIDENTIFIERParserRuleCall_5_1_0 = (RuleCall) cSuperClassAssignment_5_1
                .eContents().get(0);

        private final Keyword cPortsKeyword_6 = (Keyword) cGroup.eContents().get(6);

        private final Group cGroup_7 = (Group) cGroup.eContents().get(7);

        private final Assignment cPortsAssignment_7_0 = (Assignment) cGroup_7.eContents().get(0);

        private final RuleCall cPortsPortParserRuleCall_7_0_0 = (RuleCall) cPortsAssignment_7_0
                .eContents().get(0);

        private final Group cGroup_7_1 = (Group) cGroup_7.eContents().get(1);

        private final Keyword cCommaKeyword_7_1_0 = (Keyword) cGroup_7_1.eContents().get(0);

        private final Assignment cPortsAssignment_7_1_1 = (Assignment) cGroup_7_1.eContents()
                .get(1);

        private final RuleCall cPortsPortParserRuleCall_7_1_1_0 = (RuleCall) cPortsAssignment_7_1_1
                .eContents().get(0);

        private final Keyword cCommaKeyword_7_2 = (Keyword) cGroup_7.eContents().get(2);

        private final Keyword cMessagesKeyword_8 = (Keyword) cGroup.eContents().get(8);

        private final Group cGroup_9 = (Group) cGroup.eContents().get(9);

        private final Alternatives cAlternatives_9_0 = (Alternatives) cGroup_9.eContents().get(0);

        private final Assignment cReceiveMessagesAssignment_9_0_0 = (Assignment) cAlternatives_9_0
                .eContents().get(0);

        private final RuleCall cReceiveMessagesMessageReceiveSignatureParserRuleCall_9_0_0_0 = (RuleCall) cReceiveMessagesAssignment_9_0_0
                .eContents().get(0);

        private final Assignment cSendMessagesAssignment_9_0_1 = (Assignment) cAlternatives_9_0
                .eContents().get(1);

        private final RuleCall cSendMessagesMessageSendSignatureParserRuleCall_9_0_1_0 = (RuleCall) cSendMessagesAssignment_9_0_1
                .eContents().get(0);

        private final Group cGroup_9_1 = (Group) cGroup_9.eContents().get(1);

        private final Keyword cCommaKeyword_9_1_0 = (Keyword) cGroup_9_1.eContents().get(0);

        private final Alternatives cAlternatives_9_1_1 = (Alternatives) cGroup_9_1.eContents()
                .get(1);

        private final Assignment cReceiveMessagesAssignment_9_1_1_0 = (Assignment) cAlternatives_9_1_1
                .eContents().get(0);

        private final RuleCall cReceiveMessagesMessageReceiveSignatureParserRuleCall_9_1_1_0_0 = (RuleCall) cReceiveMessagesAssignment_9_1_1_0
                .eContents().get(0);

        private final Assignment cSendMessagesAssignment_9_1_1_1 = (Assignment) cAlternatives_9_1_1
                .eContents().get(1);

        private final RuleCall cSendMessagesMessageSendSignatureParserRuleCall_9_1_1_1_0 = (RuleCall) cSendMessagesAssignment_9_1_1_1
                .eContents().get(0);

        private final Keyword cCommaKeyword_9_2 = (Keyword) cGroup_9.eContents().get(2);

        private final Keyword cVariablesKeyword_10 = (Keyword) cGroup.eContents().get(10);

        private final Group cGroup_11 = (Group) cGroup.eContents().get(11);

        private final Assignment cInstanceVariablesAssignment_11_0 = (Assignment) cGroup_11
                .eContents().get(0);

        private final RuleCall cInstanceVariablesDeclarationParserRuleCall_11_0_0 = (RuleCall) cInstanceVariablesAssignment_11_0
                .eContents().get(0);

        private final Group cGroup_11_1 = (Group) cGroup_11.eContents().get(1);

        private final Keyword cCommaKeyword_11_1_0 = (Keyword) cGroup_11_1.eContents().get(0);

        private final Assignment cInstanceVariablesAssignment_11_1_1 = (Assignment) cGroup_11_1
                .eContents().get(1);

        private final RuleCall cInstanceVariablesDeclarationParserRuleCall_11_1_1_0 = (RuleCall) cInstanceVariablesAssignment_11_1_1
                .eContents().get(0);

        private final Keyword cCommaKeyword_11_2 = (Keyword) cGroup_11.eContents().get(2);

        private final Keyword cInitKeyword_12 = (Keyword) cGroup.eContents().get(12);

        private final Assignment cInitialMethodCallAssignment_13 = (Assignment) cGroup.eContents()
                .get(13);

        private final RuleCall cInitialMethodCallProcessMethodCallParserRuleCall_13_0 = (RuleCall) cInitialMethodCallAssignment_13
                .eContents().get(0);

        private final Keyword cMethodsKeyword_14 = (Keyword) cGroup.eContents().get(14);

        private final Assignment cMethodsAssignment_15 = (Assignment) cGroup.eContents().get(15);

        private final RuleCall cMethodsProcessMethodParserRuleCall_15_0 = (RuleCall) cMethodsAssignment_15
                .eContents().get(0);

        //// === Process Class =======
        //ProcessClass:
        //	annotations+=Annotation*
        //	'process' 'class' name=IDENTIFIER ('(' (parameters+=Declaration (',' parameters+=Declaration)*)?
        //	')')? ('extends' superClass=IDENTIFIER)?
        //	'ports' (ports+=Port (','? ports+=Port)* ','?)?
        //	'messages' ((receiveMessages+=MessageReceiveSignature | sendMessages+=MessageSendSignature) (','?
        //	(receiveMessages+=MessageReceiveSignature | sendMessages+=MessageSendSignature))* ','?)?
        //	'variables' (instanceVariables+=Declaration (','? instanceVariables+=Declaration)* ','?)?
        //	'init' initialMethodCall=ProcessMethodCall?
        //	'methods' methods+=ProcessMethod*;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //annotations+=Annotation* 'process' 'class' name=IDENTIFIER ('(' (parameters+=Declaration (','
        //parameters+=Declaration)*)? ')')? ('extends' superClass=IDENTIFIER)? 'ports' (ports+=Port (','? ports+=Port)* ','?)?
        //'messages' ((receiveMessages+=MessageReceiveSignature | sendMessages+=MessageSendSignature) (','?
        //(receiveMessages+=MessageReceiveSignature | sendMessages+=MessageSendSignature))* ','?)? 'variables'
        //(instanceVariables+=Declaration (','? instanceVariables+=Declaration)* ','?)? 'init'
        //initialMethodCall=ProcessMethodCall? 'methods' methods+=ProcessMethod*
        public Group getGroup() {
            return cGroup;
        }

        //annotations+=Annotation*
        public Assignment getAnnotationsAssignment_0() {
            return cAnnotationsAssignment_0;
        }

        //Annotation
        public RuleCall getAnnotationsAnnotationParserRuleCall_0_0() {
            return cAnnotationsAnnotationParserRuleCall_0_0;
        }

        //'process'
        public Keyword getProcessKeyword_1() {
            return cProcessKeyword_1;
        }

        //'class'
        public Keyword getClassKeyword_2() {
            return cClassKeyword_2;
        }

        //name=IDENTIFIER
        public Assignment getNameAssignment_3() {
            return cNameAssignment_3;
        }

        //IDENTIFIER
        public RuleCall getNameIDENTIFIERParserRuleCall_3_0() {
            return cNameIDENTIFIERParserRuleCall_3_0;
        }

        //('(' (parameters+=Declaration (',' parameters+=Declaration)*)? ')')?
        public Group getGroup_4() {
            return cGroup_4;
        }

        //'('
        public Keyword getLeftParenthesisKeyword_4_0() {
            return cLeftParenthesisKeyword_4_0;
        }

        //(parameters+=Declaration (',' parameters+=Declaration)*)?
        public Group getGroup_4_1() {
            return cGroup_4_1;
        }

        //parameters+=Declaration
        public Assignment getParametersAssignment_4_1_0() {
            return cParametersAssignment_4_1_0;
        }

        //Declaration
        public RuleCall getParametersDeclarationParserRuleCall_4_1_0_0() {
            return cParametersDeclarationParserRuleCall_4_1_0_0;
        }

        //(',' parameters+=Declaration)*
        public Group getGroup_4_1_1() {
            return cGroup_4_1_1;
        }

        //','
        public Keyword getCommaKeyword_4_1_1_0() {
            return cCommaKeyword_4_1_1_0;
        }

        //parameters+=Declaration
        public Assignment getParametersAssignment_4_1_1_1() {
            return cParametersAssignment_4_1_1_1;
        }

        //Declaration
        public RuleCall getParametersDeclarationParserRuleCall_4_1_1_1_0() {
            return cParametersDeclarationParserRuleCall_4_1_1_1_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_4_2() {
            return cRightParenthesisKeyword_4_2;
        }

        //('extends' superClass=IDENTIFIER)?
        public Group getGroup_5() {
            return cGroup_5;
        }

        //'extends'
        public Keyword getExtendsKeyword_5_0() {
            return cExtendsKeyword_5_0;
        }

        //superClass=IDENTIFIER
        public Assignment getSuperClassAssignment_5_1() {
            return cSuperClassAssignment_5_1;
        }

        //IDENTIFIER
        public RuleCall getSuperClassIDENTIFIERParserRuleCall_5_1_0() {
            return cSuperClassIDENTIFIERParserRuleCall_5_1_0;
        }

        //'ports'
        public Keyword getPortsKeyword_6() {
            return cPortsKeyword_6;
        }

        //(ports+=Port (','? ports+=Port)* ','?)?
        public Group getGroup_7() {
            return cGroup_7;
        }

        //ports+=Port
        public Assignment getPortsAssignment_7_0() {
            return cPortsAssignment_7_0;
        }

        //Port
        public RuleCall getPortsPortParserRuleCall_7_0_0() {
            return cPortsPortParserRuleCall_7_0_0;
        }

        //(','? ports+=Port)*
        public Group getGroup_7_1() {
            return cGroup_7_1;
        }

        //','?
        public Keyword getCommaKeyword_7_1_0() {
            return cCommaKeyword_7_1_0;
        }

        //ports+=Port
        public Assignment getPortsAssignment_7_1_1() {
            return cPortsAssignment_7_1_1;
        }

        //Port
        public RuleCall getPortsPortParserRuleCall_7_1_1_0() {
            return cPortsPortParserRuleCall_7_1_1_0;
        }

        //','?
        public Keyword getCommaKeyword_7_2() {
            return cCommaKeyword_7_2;
        }

        //'messages'
        public Keyword getMessagesKeyword_8() {
            return cMessagesKeyword_8;
        }

        //((receiveMessages+=MessageReceiveSignature | sendMessages+=MessageSendSignature) (','?
        //(receiveMessages+=MessageReceiveSignature | sendMessages+=MessageSendSignature))* ','?)?
        public Group getGroup_9() {
            return cGroup_9;
        }

        //(receiveMessages+=MessageReceiveSignature | sendMessages+=MessageSendSignature)
        public Alternatives getAlternatives_9_0() {
            return cAlternatives_9_0;
        }

        //receiveMessages+=MessageReceiveSignature
        public Assignment getReceiveMessagesAssignment_9_0_0() {
            return cReceiveMessagesAssignment_9_0_0;
        }

        //MessageReceiveSignature
        public RuleCall getReceiveMessagesMessageReceiveSignatureParserRuleCall_9_0_0_0() {
            return cReceiveMessagesMessageReceiveSignatureParserRuleCall_9_0_0_0;
        }

        //sendMessages+=MessageSendSignature
        public Assignment getSendMessagesAssignment_9_0_1() {
            return cSendMessagesAssignment_9_0_1;
        }

        //MessageSendSignature
        public RuleCall getSendMessagesMessageSendSignatureParserRuleCall_9_0_1_0() {
            return cSendMessagesMessageSendSignatureParserRuleCall_9_0_1_0;
        }

        //(','? (receiveMessages+=MessageReceiveSignature | sendMessages+=MessageSendSignature))*
        public Group getGroup_9_1() {
            return cGroup_9_1;
        }

        //','?
        public Keyword getCommaKeyword_9_1_0() {
            return cCommaKeyword_9_1_0;
        }

        //(receiveMessages+=MessageReceiveSignature | sendMessages+=MessageSendSignature)
        public Alternatives getAlternatives_9_1_1() {
            return cAlternatives_9_1_1;
        }

        //receiveMessages+=MessageReceiveSignature
        public Assignment getReceiveMessagesAssignment_9_1_1_0() {
            return cReceiveMessagesAssignment_9_1_1_0;
        }

        //MessageReceiveSignature
        public RuleCall getReceiveMessagesMessageReceiveSignatureParserRuleCall_9_1_1_0_0() {
            return cReceiveMessagesMessageReceiveSignatureParserRuleCall_9_1_1_0_0;
        }

        //sendMessages+=MessageSendSignature
        public Assignment getSendMessagesAssignment_9_1_1_1() {
            return cSendMessagesAssignment_9_1_1_1;
        }

        //MessageSendSignature
        public RuleCall getSendMessagesMessageSendSignatureParserRuleCall_9_1_1_1_0() {
            return cSendMessagesMessageSendSignatureParserRuleCall_9_1_1_1_0;
        }

        //','?
        public Keyword getCommaKeyword_9_2() {
            return cCommaKeyword_9_2;
        }

        //'variables'
        public Keyword getVariablesKeyword_10() {
            return cVariablesKeyword_10;
        }

        //(instanceVariables+=Declaration (','? instanceVariables+=Declaration)* ','?)?
        public Group getGroup_11() {
            return cGroup_11;
        }

        //instanceVariables+=Declaration
        public Assignment getInstanceVariablesAssignment_11_0() {
            return cInstanceVariablesAssignment_11_0;
        }

        //Declaration
        public RuleCall getInstanceVariablesDeclarationParserRuleCall_11_0_0() {
            return cInstanceVariablesDeclarationParserRuleCall_11_0_0;
        }

        //(','? instanceVariables+=Declaration)*
        public Group getGroup_11_1() {
            return cGroup_11_1;
        }

        //','?
        public Keyword getCommaKeyword_11_1_0() {
            return cCommaKeyword_11_1_0;
        }

        //instanceVariables+=Declaration
        public Assignment getInstanceVariablesAssignment_11_1_1() {
            return cInstanceVariablesAssignment_11_1_1;
        }

        //Declaration
        public RuleCall getInstanceVariablesDeclarationParserRuleCall_11_1_1_0() {
            return cInstanceVariablesDeclarationParserRuleCall_11_1_1_0;
        }

        //','?
        public Keyword getCommaKeyword_11_2() {
            return cCommaKeyword_11_2;
        }

        //'init'
        public Keyword getInitKeyword_12() {
            return cInitKeyword_12;
        }

        //initialMethodCall=ProcessMethodCall?
        public Assignment getInitialMethodCallAssignment_13() {
            return cInitialMethodCallAssignment_13;
        }

        //ProcessMethodCall
        public RuleCall getInitialMethodCallProcessMethodCallParserRuleCall_13_0() {
            return cInitialMethodCallProcessMethodCallParserRuleCall_13_0;
        }

        //'methods'
        public Keyword getMethodsKeyword_14() {
            return cMethodsKeyword_14;
        }

        //methods+=ProcessMethod*
        public Assignment getMethodsAssignment_15() {
            return cMethodsAssignment_15;
        }

        //ProcessMethod
        public RuleCall getMethodsProcessMethodParserRuleCall_15_0() {
            return cMethodsProcessMethodParserRuleCall_15_0;
        }
    }

    public class ProcessMethodElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.ProcessMethod");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Assignment cAnnotationsAssignment_0 = (Assignment) cGroup.eContents().get(0);

        private final RuleCall cAnnotationsAnnotationParserRuleCall_0_0 = (RuleCall) cAnnotationsAssignment_0
                .eContents().get(0);

        private final Assignment cNameAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cNameIDENTIFIERParserRuleCall_1_0 = (RuleCall) cNameAssignment_1
                .eContents().get(0);

        private final Keyword cLeftParenthesisKeyword_2 = (Keyword) cGroup.eContents().get(2);

        private final Group cGroup_3 = (Group) cGroup.eContents().get(3);

        private final Assignment cInputParametersAssignment_3_0 = (Assignment) cGroup_3.eContents()
                .get(0);

        private final RuleCall cInputParametersDeclarationParserRuleCall_3_0_0 = (RuleCall) cInputParametersAssignment_3_0
                .eContents().get(0);

        private final Group cGroup_3_1 = (Group) cGroup_3.eContents().get(1);

        private final Keyword cCommaKeyword_3_1_0 = (Keyword) cGroup_3_1.eContents().get(0);

        private final Assignment cInputParametersAssignment_3_1_1 = (Assignment) cGroup_3_1
                .eContents().get(1);

        private final RuleCall cInputParametersDeclarationParserRuleCall_3_1_1_0 = (RuleCall) cInputParametersAssignment_3_1_1
                .eContents().get(0);

        private final Keyword cRightParenthesisKeyword_4 = (Keyword) cGroup.eContents().get(4);

        private final Keyword cLeftParenthesisKeyword_5 = (Keyword) cGroup.eContents().get(5);

        private final Group cGroup_6 = (Group) cGroup.eContents().get(6);

        private final Assignment cOutputParametersAssignment_6_0 = (Assignment) cGroup_6.eContents()
                .get(0);

        private final RuleCall cOutputParametersDeclarationParserRuleCall_6_0_0 = (RuleCall) cOutputParametersAssignment_6_0
                .eContents().get(0);

        private final Group cGroup_6_1 = (Group) cGroup_6.eContents().get(1);

        private final Keyword cCommaKeyword_6_1_0 = (Keyword) cGroup_6_1.eContents().get(0);

        private final Assignment cOutputParametersAssignment_6_1_1 = (Assignment) cGroup_6_1
                .eContents().get(1);

        private final RuleCall cOutputParametersDeclarationParserRuleCall_6_1_1_0 = (RuleCall) cOutputParametersAssignment_6_1_1
                .eContents().get(0);

        private final Keyword cRightParenthesisKeyword_7 = (Keyword) cGroup.eContents().get(7);

        private final Group cGroup_8 = (Group) cGroup.eContents().get(8);

        private final Keyword cVerticalLineKeyword_8_0 = (Keyword) cGroup_8.eContents().get(0);

        private final Group cGroup_8_1 = (Group) cGroup_8.eContents().get(1);

        private final Assignment cLocalVariablesAssignment_8_1_0 = (Assignment) cGroup_8_1
                .eContents().get(0);

        private final RuleCall cLocalVariablesDeclarationParserRuleCall_8_1_0_0 = (RuleCall) cLocalVariablesAssignment_8_1_0
                .eContents().get(0);

        private final Group cGroup_8_1_1 = (Group) cGroup_8_1.eContents().get(1);

        private final Keyword cCommaKeyword_8_1_1_0 = (Keyword) cGroup_8_1_1.eContents().get(0);

        private final Assignment cLocalVariablesAssignment_8_1_1_1 = (Assignment) cGroup_8_1_1
                .eContents().get(1);

        private final RuleCall cLocalVariablesDeclarationParserRuleCall_8_1_1_1_0 = (RuleCall) cLocalVariablesAssignment_8_1_1_1
                .eContents().get(0);

        private final Keyword cVerticalLineKeyword_8_2 = (Keyword) cGroup_8.eContents().get(2);

        private final Assignment cBodyAssignment_9 = (Assignment) cGroup.eContents().get(9);

        private final RuleCall cBodyStatementParserRuleCall_9_0 = (RuleCall) cBodyAssignment_9
                .eContents().get(0);

        //ProcessMethod:
        //	annotations+=Annotation*
        //	name=IDENTIFIER
        //	'(' (inputParameters+=Declaration (',' inputParameters+=Declaration)*)? ')'
        //	'(' (outputParameters+=Declaration (',' outputParameters+=Declaration)*)? ')' ('|' (localVariables+=Declaration (','
        //	localVariables+=Declaration)*)? '|')?
        //	body=Statement;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //annotations+=Annotation* name=IDENTIFIER '(' (inputParameters+=Declaration (',' inputParameters+=Declaration)*)? ')' '('
        //(outputParameters+=Declaration (',' outputParameters+=Declaration)*)? ')' ('|' (localVariables+=Declaration (','
        //localVariables+=Declaration)*)? '|')? body=Statement
        public Group getGroup() {
            return cGroup;
        }

        //annotations+=Annotation*
        public Assignment getAnnotationsAssignment_0() {
            return cAnnotationsAssignment_0;
        }

        //Annotation
        public RuleCall getAnnotationsAnnotationParserRuleCall_0_0() {
            return cAnnotationsAnnotationParserRuleCall_0_0;
        }

        //name=IDENTIFIER
        public Assignment getNameAssignment_1() {
            return cNameAssignment_1;
        }

        //IDENTIFIER
        public RuleCall getNameIDENTIFIERParserRuleCall_1_0() {
            return cNameIDENTIFIERParserRuleCall_1_0;
        }

        //'('
        public Keyword getLeftParenthesisKeyword_2() {
            return cLeftParenthesisKeyword_2;
        }

        //(inputParameters+=Declaration (',' inputParameters+=Declaration)*)?
        public Group getGroup_3() {
            return cGroup_3;
        }

        //inputParameters+=Declaration
        public Assignment getInputParametersAssignment_3_0() {
            return cInputParametersAssignment_3_0;
        }

        //Declaration
        public RuleCall getInputParametersDeclarationParserRuleCall_3_0_0() {
            return cInputParametersDeclarationParserRuleCall_3_0_0;
        }

        //(',' inputParameters+=Declaration)*
        public Group getGroup_3_1() {
            return cGroup_3_1;
        }

        //','
        public Keyword getCommaKeyword_3_1_0() {
            return cCommaKeyword_3_1_0;
        }

        //inputParameters+=Declaration
        public Assignment getInputParametersAssignment_3_1_1() {
            return cInputParametersAssignment_3_1_1;
        }

        //Declaration
        public RuleCall getInputParametersDeclarationParserRuleCall_3_1_1_0() {
            return cInputParametersDeclarationParserRuleCall_3_1_1_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_4() {
            return cRightParenthesisKeyword_4;
        }

        //'('
        public Keyword getLeftParenthesisKeyword_5() {
            return cLeftParenthesisKeyword_5;
        }

        //(outputParameters+=Declaration (',' outputParameters+=Declaration)*)?
        public Group getGroup_6() {
            return cGroup_6;
        }

        //outputParameters+=Declaration
        public Assignment getOutputParametersAssignment_6_0() {
            return cOutputParametersAssignment_6_0;
        }

        //Declaration
        public RuleCall getOutputParametersDeclarationParserRuleCall_6_0_0() {
            return cOutputParametersDeclarationParserRuleCall_6_0_0;
        }

        //(',' outputParameters+=Declaration)*
        public Group getGroup_6_1() {
            return cGroup_6_1;
        }

        //','
        public Keyword getCommaKeyword_6_1_0() {
            return cCommaKeyword_6_1_0;
        }

        //outputParameters+=Declaration
        public Assignment getOutputParametersAssignment_6_1_1() {
            return cOutputParametersAssignment_6_1_1;
        }

        //Declaration
        public RuleCall getOutputParametersDeclarationParserRuleCall_6_1_1_0() {
            return cOutputParametersDeclarationParserRuleCall_6_1_1_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_7() {
            return cRightParenthesisKeyword_7;
        }

        //('|' (localVariables+=Declaration (',' localVariables+=Declaration)*)? '|')?
        public Group getGroup_8() {
            return cGroup_8;
        }

        //'|'
        public Keyword getVerticalLineKeyword_8_0() {
            return cVerticalLineKeyword_8_0;
        }

        //(localVariables+=Declaration (',' localVariables+=Declaration)*)?
        public Group getGroup_8_1() {
            return cGroup_8_1;
        }

        //localVariables+=Declaration
        public Assignment getLocalVariablesAssignment_8_1_0() {
            return cLocalVariablesAssignment_8_1_0;
        }

        //Declaration
        public RuleCall getLocalVariablesDeclarationParserRuleCall_8_1_0_0() {
            return cLocalVariablesDeclarationParserRuleCall_8_1_0_0;
        }

        //(',' localVariables+=Declaration)*
        public Group getGroup_8_1_1() {
            return cGroup_8_1_1;
        }

        //','
        public Keyword getCommaKeyword_8_1_1_0() {
            return cCommaKeyword_8_1_1_0;
        }

        //localVariables+=Declaration
        public Assignment getLocalVariablesAssignment_8_1_1_1() {
            return cLocalVariablesAssignment_8_1_1_1;
        }

        //Declaration
        public RuleCall getLocalVariablesDeclarationParserRuleCall_8_1_1_1_0() {
            return cLocalVariablesDeclarationParserRuleCall_8_1_1_1_0;
        }

        //'|'
        public Keyword getVerticalLineKeyword_8_2() {
            return cVerticalLineKeyword_8_2;
        }

        //body=Statement
        public Assignment getBodyAssignment_9() {
            return cBodyAssignment_9;
        }

        //Statement
        public RuleCall getBodyStatementParserRuleCall_9_0() {
            return cBodyStatementParserRuleCall_9_0;
        }
    }

    public class PortElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.Port");

        private final Assignment cNameAssignment = (Assignment) rule.eContents().get(1);

        private final RuleCall cNameIDENTIFIERParserRuleCall_0 = (RuleCall) cNameAssignment
                .eContents().get(0);

        //Port:
        //	name=IDENTIFIER;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //name=IDENTIFIER
        public Assignment getNameAssignment() {
            return cNameAssignment;
        }

        //IDENTIFIER
        public RuleCall getNameIDENTIFIERParserRuleCall_0() {
            return cNameIDENTIFIERParserRuleCall_0;
        }
    }

    public class MessageReceiveSignatureElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.MessageReceiveSignature");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Assignment cPortAssignment_0 = (Assignment) cGroup.eContents().get(0);

        private final RuleCall cPortPortReferenceParserRuleCall_0_0 = (RuleCall) cPortAssignment_0
                .eContents().get(0);

        private final Keyword cQuestionMarkKeyword_1 = (Keyword) cGroup.eContents().get(1);

        private final Assignment cNameAssignment_2 = (Assignment) cGroup.eContents().get(2);

        private final RuleCall cNameIDENTIFIERParserRuleCall_2_0 = (RuleCall) cNameAssignment_2
                .eContents().get(0);

        private final Group cGroup_3 = (Group) cGroup.eContents().get(3);

        private final Keyword cLeftParenthesisKeyword_3_0 = (Keyword) cGroup_3.eContents().get(0);

        private final Group cGroup_3_1 = (Group) cGroup_3.eContents().get(1);

        private final Assignment cParametersAssignment_3_1_0 = (Assignment) cGroup_3_1.eContents()
                .get(0);

        private final RuleCall cParametersMessageParameterParserRuleCall_3_1_0_0 = (RuleCall) cParametersAssignment_3_1_0
                .eContents().get(0);

        private final Group cGroup_3_1_1 = (Group) cGroup_3_1.eContents().get(1);

        private final Keyword cCommaKeyword_3_1_1_0 = (Keyword) cGroup_3_1_1.eContents().get(0);

        private final Assignment cParametersAssignment_3_1_1_1 = (Assignment) cGroup_3_1_1
                .eContents().get(1);

        private final RuleCall cParametersMessageParameterParserRuleCall_3_1_1_1_0 = (RuleCall) cParametersAssignment_3_1_1_1
                .eContents().get(0);

        private final Keyword cRightParenthesisKeyword_3_2 = (Keyword) cGroup_3.eContents().get(2);

        //MessageReceiveSignature MessageSignature:
        //	port=PortReference '?' name=IDENTIFIER ('(' (parameters+=MessageParameter (',' parameters+=MessageParameter)*)?
        //	')')?;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //port=PortReference '?' name=IDENTIFIER ('(' (parameters+=MessageParameter (',' parameters+=MessageParameter)*)? ')')?
        public Group getGroup() {
            return cGroup;
        }

        //port=PortReference
        public Assignment getPortAssignment_0() {
            return cPortAssignment_0;
        }

        //PortReference
        public RuleCall getPortPortReferenceParserRuleCall_0_0() {
            return cPortPortReferenceParserRuleCall_0_0;
        }

        //'?'
        public Keyword getQuestionMarkKeyword_1() {
            return cQuestionMarkKeyword_1;
        }

        //name=IDENTIFIER
        public Assignment getNameAssignment_2() {
            return cNameAssignment_2;
        }

        //IDENTIFIER
        public RuleCall getNameIDENTIFIERParserRuleCall_2_0() {
            return cNameIDENTIFIERParserRuleCall_2_0;
        }

        //('(' (parameters+=MessageParameter (',' parameters+=MessageParameter)*)? ')')?
        public Group getGroup_3() {
            return cGroup_3;
        }

        //'('
        public Keyword getLeftParenthesisKeyword_3_0() {
            return cLeftParenthesisKeyword_3_0;
        }

        //(parameters+=MessageParameter (',' parameters+=MessageParameter)*)?
        public Group getGroup_3_1() {
            return cGroup_3_1;
        }

        //parameters+=MessageParameter
        public Assignment getParametersAssignment_3_1_0() {
            return cParametersAssignment_3_1_0;
        }

        //MessageParameter
        public RuleCall getParametersMessageParameterParserRuleCall_3_1_0_0() {
            return cParametersMessageParameterParserRuleCall_3_1_0_0;
        }

        //(',' parameters+=MessageParameter)*
        public Group getGroup_3_1_1() {
            return cGroup_3_1_1;
        }

        //','
        public Keyword getCommaKeyword_3_1_1_0() {
            return cCommaKeyword_3_1_1_0;
        }

        //parameters+=MessageParameter
        public Assignment getParametersAssignment_3_1_1_1() {
            return cParametersAssignment_3_1_1_1;
        }

        //MessageParameter
        public RuleCall getParametersMessageParameterParserRuleCall_3_1_1_1_0() {
            return cParametersMessageParameterParserRuleCall_3_1_1_1_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_3_2() {
            return cRightParenthesisKeyword_3_2;
        }
    }

    public class MessageSendSignatureElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.MessageSendSignature");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Assignment cPortAssignment_0 = (Assignment) cGroup.eContents().get(0);

        private final RuleCall cPortPortReferenceParserRuleCall_0_0 = (RuleCall) cPortAssignment_0
                .eContents().get(0);

        private final Keyword cExclamationMarkKeyword_1 = (Keyword) cGroup.eContents().get(1);

        private final Assignment cNameAssignment_2 = (Assignment) cGroup.eContents().get(2);

        private final RuleCall cNameIDENTIFIERParserRuleCall_2_0 = (RuleCall) cNameAssignment_2
                .eContents().get(0);

        private final Group cGroup_3 = (Group) cGroup.eContents().get(3);

        private final Keyword cLeftParenthesisKeyword_3_0 = (Keyword) cGroup_3.eContents().get(0);

        private final Group cGroup_3_1 = (Group) cGroup_3.eContents().get(1);

        private final Assignment cParametersAssignment_3_1_0 = (Assignment) cGroup_3_1.eContents()
                .get(0);

        private final RuleCall cParametersMessageParameterParserRuleCall_3_1_0_0 = (RuleCall) cParametersAssignment_3_1_0
                .eContents().get(0);

        private final Group cGroup_3_1_1 = (Group) cGroup_3_1.eContents().get(1);

        private final Keyword cCommaKeyword_3_1_1_0 = (Keyword) cGroup_3_1_1.eContents().get(0);

        private final Assignment cParametersAssignment_3_1_1_1 = (Assignment) cGroup_3_1_1
                .eContents().get(1);

        private final RuleCall cParametersMessageParameterParserRuleCall_3_1_1_1_0 = (RuleCall) cParametersAssignment_3_1_1_1
                .eContents().get(0);

        private final Keyword cRightParenthesisKeyword_3_2 = (Keyword) cGroup_3.eContents().get(2);

        //MessageSendSignature MessageSignature:
        //	port=PortReference '!' name=IDENTIFIER ('(' (parameters+=MessageParameter (',' parameters+=MessageParameter)*)?
        //	')')?;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //port=PortReference '!' name=IDENTIFIER ('(' (parameters+=MessageParameter (',' parameters+=MessageParameter)*)? ')')?
        public Group getGroup() {
            return cGroup;
        }

        //port=PortReference
        public Assignment getPortAssignment_0() {
            return cPortAssignment_0;
        }

        //PortReference
        public RuleCall getPortPortReferenceParserRuleCall_0_0() {
            return cPortPortReferenceParserRuleCall_0_0;
        }

        //'!'
        public Keyword getExclamationMarkKeyword_1() {
            return cExclamationMarkKeyword_1;
        }

        //name=IDENTIFIER
        public Assignment getNameAssignment_2() {
            return cNameAssignment_2;
        }

        //IDENTIFIER
        public RuleCall getNameIDENTIFIERParserRuleCall_2_0() {
            return cNameIDENTIFIERParserRuleCall_2_0;
        }

        //('(' (parameters+=MessageParameter (',' parameters+=MessageParameter)*)? ')')?
        public Group getGroup_3() {
            return cGroup_3;
        }

        //'('
        public Keyword getLeftParenthesisKeyword_3_0() {
            return cLeftParenthesisKeyword_3_0;
        }

        //(parameters+=MessageParameter (',' parameters+=MessageParameter)*)?
        public Group getGroup_3_1() {
            return cGroup_3_1;
        }

        //parameters+=MessageParameter
        public Assignment getParametersAssignment_3_1_0() {
            return cParametersAssignment_3_1_0;
        }

        //MessageParameter
        public RuleCall getParametersMessageParameterParserRuleCall_3_1_0_0() {
            return cParametersMessageParameterParserRuleCall_3_1_0_0;
        }

        //(',' parameters+=MessageParameter)*
        public Group getGroup_3_1_1() {
            return cGroup_3_1_1;
        }

        //','
        public Keyword getCommaKeyword_3_1_1_0() {
            return cCommaKeyword_3_1_1_0;
        }

        //parameters+=MessageParameter
        public Assignment getParametersAssignment_3_1_1_1() {
            return cParametersAssignment_3_1_1_1;
        }

        //MessageParameter
        public RuleCall getParametersMessageParameterParserRuleCall_3_1_1_1_0() {
            return cParametersMessageParameterParserRuleCall_3_1_1_1_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_3_2() {
            return cRightParenthesisKeyword_3_2;
        }
    }

    public class MessageParameterElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.MessageParameter");

        private final Assignment cTypeAssignment = (Assignment) rule.eContents().get(1);

        private final RuleCall cTypeIDENTIFIERParserRuleCall_0 = (RuleCall) cTypeAssignment
                .eContents().get(0);

        //MessageParameter:
        //	type=IDENTIFIER;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //type=IDENTIFIER
        public Assignment getTypeAssignment() {
            return cTypeAssignment;
        }

        //IDENTIFIER
        public RuleCall getTypeIDENTIFIERParserRuleCall_0() {
            return cTypeIDENTIFIERParserRuleCall_0;
        }
    }

    public class SystemElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.System");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Action cClusterClassAction_0 = (Action) cGroup.eContents().get(0);

        private final Assignment cAnnotationsAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cAnnotationsAnnotationParserRuleCall_1_0 = (RuleCall) cAnnotationsAssignment_1
                .eContents().get(0);

        private final Keyword cSystemKeyword_2 = (Keyword) cGroup.eContents().get(2);

        private final Group cGroup_3 = (Group) cGroup.eContents().get(3);

        private final Keyword cLeftParenthesisKeyword_3_0 = (Keyword) cGroup_3.eContents().get(0);

        private final Group cGroup_3_1 = (Group) cGroup_3.eContents().get(1);

        private final Assignment cParametersAssignment_3_1_0 = (Assignment) cGroup_3_1.eContents()
                .get(0);

        private final RuleCall cParametersDeclarationParserRuleCall_3_1_0_0 = (RuleCall) cParametersAssignment_3_1_0
                .eContents().get(0);

        private final Group cGroup_3_1_1 = (Group) cGroup_3_1.eContents().get(1);

        private final Keyword cCommaKeyword_3_1_1_0 = (Keyword) cGroup_3_1_1.eContents().get(0);

        private final Assignment cParametersAssignment_3_1_1_1 = (Assignment) cGroup_3_1_1
                .eContents().get(1);

        private final RuleCall cParametersDeclarationParserRuleCall_3_1_1_1_0 = (RuleCall) cParametersAssignment_3_1_1_1
                .eContents().get(0);

        private final Keyword cRightParenthesisKeyword_3_2 = (Keyword) cGroup_3.eContents().get(2);

        private final Group cGroup_4 = (Group) cGroup.eContents().get(4);

        private final Keyword cPortsKeyword_4_0 = (Keyword) cGroup_4.eContents().get(0);

        private final Group cGroup_4_1 = (Group) cGroup_4.eContents().get(1);

        private final Assignment cPortsAssignment_4_1_0 = (Assignment) cGroup_4_1.eContents()
                .get(0);

        private final RuleCall cPortsPortParserRuleCall_4_1_0_0 = (RuleCall) cPortsAssignment_4_1_0
                .eContents().get(0);

        private final Group cGroup_4_1_1 = (Group) cGroup_4_1.eContents().get(1);

        private final Keyword cCommaKeyword_4_1_1_0 = (Keyword) cGroup_4_1_1.eContents().get(0);

        private final Assignment cPortsAssignment_4_1_1_1 = (Assignment) cGroup_4_1_1.eContents()
                .get(1);

        private final RuleCall cPortsPortParserRuleCall_4_1_1_1_0 = (RuleCall) cPortsAssignment_4_1_1_1
                .eContents().get(0);

        private final Keyword cCommaKeyword_4_1_2 = (Keyword) cGroup_4_1.eContents().get(2);

        private final Keyword cInstancesKeyword_5 = (Keyword) cGroup.eContents().get(5);

        private final Assignment cInstancesAssignment_6 = (Assignment) cGroup.eContents().get(6);

        private final RuleCall cInstancesInstanceParserRuleCall_6_0 = (RuleCall) cInstancesAssignment_6
                .eContents().get(0);

        private final Keyword cChannelsKeyword_7 = (Keyword) cGroup.eContents().get(7);

        private final Assignment cChannelsAssignment_8 = (Assignment) cGroup.eContents().get(8);

        private final RuleCall cChannelsChannelParserRuleCall_8_0 = (RuleCall) cChannelsAssignment_8
                .eContents().get(0);

        //// === System and Cluster Class =======
        //System ClusterClass:
        //	{ClusterClass} annotations+=Annotation*
        //	'system' ('(' (parameters+=Declaration (',' parameters+=Declaration)*)?
        //	')')? ('ports' (ports+=Port (','? ports+=Port)* ','?)?)?
        //	'instances' instances+=Instance*
        //	'channels' channels+=Channel*;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //{ClusterClass} annotations+=Annotation* 'system' ('(' (parameters+=Declaration (',' parameters+=Declaration)*)? ')')?
        //('ports' (ports+=Port (','? ports+=Port)* ','?)?)? 'instances' instances+=Instance* 'channels' channels+=Channel*
        public Group getGroup() {
            return cGroup;
        }

        //{ClusterClass}
        public Action getClusterClassAction_0() {
            return cClusterClassAction_0;
        }

        //annotations+=Annotation*
        public Assignment getAnnotationsAssignment_1() {
            return cAnnotationsAssignment_1;
        }

        //Annotation
        public RuleCall getAnnotationsAnnotationParserRuleCall_1_0() {
            return cAnnotationsAnnotationParserRuleCall_1_0;
        }

        //'system'
        public Keyword getSystemKeyword_2() {
            return cSystemKeyword_2;
        }

        //('(' (parameters+=Declaration (',' parameters+=Declaration)*)? ')')?
        public Group getGroup_3() {
            return cGroup_3;
        }

        //'('
        public Keyword getLeftParenthesisKeyword_3_0() {
            return cLeftParenthesisKeyword_3_0;
        }

        //(parameters+=Declaration (',' parameters+=Declaration)*)?
        public Group getGroup_3_1() {
            return cGroup_3_1;
        }

        //parameters+=Declaration
        public Assignment getParametersAssignment_3_1_0() {
            return cParametersAssignment_3_1_0;
        }

        //Declaration
        public RuleCall getParametersDeclarationParserRuleCall_3_1_0_0() {
            return cParametersDeclarationParserRuleCall_3_1_0_0;
        }

        //(',' parameters+=Declaration)*
        public Group getGroup_3_1_1() {
            return cGroup_3_1_1;
        }

        //','
        public Keyword getCommaKeyword_3_1_1_0() {
            return cCommaKeyword_3_1_1_0;
        }

        //parameters+=Declaration
        public Assignment getParametersAssignment_3_1_1_1() {
            return cParametersAssignment_3_1_1_1;
        }

        //Declaration
        public RuleCall getParametersDeclarationParserRuleCall_3_1_1_1_0() {
            return cParametersDeclarationParserRuleCall_3_1_1_1_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_3_2() {
            return cRightParenthesisKeyword_3_2;
        }

        //('ports' (ports+=Port (','? ports+=Port)* ','?)?)?
        public Group getGroup_4() {
            return cGroup_4;
        }

        //'ports'
        public Keyword getPortsKeyword_4_0() {
            return cPortsKeyword_4_0;
        }

        //(ports+=Port (','? ports+=Port)* ','?)?
        public Group getGroup_4_1() {
            return cGroup_4_1;
        }

        //ports+=Port
        public Assignment getPortsAssignment_4_1_0() {
            return cPortsAssignment_4_1_0;
        }

        //Port
        public RuleCall getPortsPortParserRuleCall_4_1_0_0() {
            return cPortsPortParserRuleCall_4_1_0_0;
        }

        //(','? ports+=Port)*
        public Group getGroup_4_1_1() {
            return cGroup_4_1_1;
        }

        //','?
        public Keyword getCommaKeyword_4_1_1_0() {
            return cCommaKeyword_4_1_1_0;
        }

        //ports+=Port
        public Assignment getPortsAssignment_4_1_1_1() {
            return cPortsAssignment_4_1_1_1;
        }

        //Port
        public RuleCall getPortsPortParserRuleCall_4_1_1_1_0() {
            return cPortsPortParserRuleCall_4_1_1_1_0;
        }

        //','?
        public Keyword getCommaKeyword_4_1_2() {
            return cCommaKeyword_4_1_2;
        }

        //'instances'
        public Keyword getInstancesKeyword_5() {
            return cInstancesKeyword_5;
        }

        //instances+=Instance*
        public Assignment getInstancesAssignment_6() {
            return cInstancesAssignment_6;
        }

        //Instance
        public RuleCall getInstancesInstanceParserRuleCall_6_0() {
            return cInstancesInstanceParserRuleCall_6_0;
        }

        //'channels'
        public Keyword getChannelsKeyword_7() {
            return cChannelsKeyword_7;
        }

        //channels+=Channel*
        public Assignment getChannelsAssignment_8() {
            return cChannelsAssignment_8;
        }

        //Channel
        public RuleCall getChannelsChannelParserRuleCall_8_0() {
            return cChannelsChannelParserRuleCall_8_0;
        }
    }

    public class ClusterClassElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.ClusterClass");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Assignment cAnnotationsAssignment_0 = (Assignment) cGroup.eContents().get(0);

        private final RuleCall cAnnotationsAnnotationParserRuleCall_0_0 = (RuleCall) cAnnotationsAssignment_0
                .eContents().get(0);

        private final Keyword cClusterKeyword_1 = (Keyword) cGroup.eContents().get(1);

        private final Keyword cClassKeyword_2 = (Keyword) cGroup.eContents().get(2);

        private final Assignment cNameAssignment_3 = (Assignment) cGroup.eContents().get(3);

        private final RuleCall cNameIDENTIFIERParserRuleCall_3_0 = (RuleCall) cNameAssignment_3
                .eContents().get(0);

        private final Group cGroup_4 = (Group) cGroup.eContents().get(4);

        private final Keyword cLeftParenthesisKeyword_4_0 = (Keyword) cGroup_4.eContents().get(0);

        private final Group cGroup_4_1 = (Group) cGroup_4.eContents().get(1);

        private final Assignment cParametersAssignment_4_1_0 = (Assignment) cGroup_4_1.eContents()
                .get(0);

        private final RuleCall cParametersDeclarationParserRuleCall_4_1_0_0 = (RuleCall) cParametersAssignment_4_1_0
                .eContents().get(0);

        private final Group cGroup_4_1_1 = (Group) cGroup_4_1.eContents().get(1);

        private final Keyword cCommaKeyword_4_1_1_0 = (Keyword) cGroup_4_1_1.eContents().get(0);

        private final Assignment cParametersAssignment_4_1_1_1 = (Assignment) cGroup_4_1_1
                .eContents().get(1);

        private final RuleCall cParametersDeclarationParserRuleCall_4_1_1_1_0 = (RuleCall) cParametersAssignment_4_1_1_1
                .eContents().get(0);

        private final Keyword cRightParenthesisKeyword_4_2 = (Keyword) cGroup_4.eContents().get(2);

        private final Keyword cPortsKeyword_5 = (Keyword) cGroup.eContents().get(5);

        private final Group cGroup_6 = (Group) cGroup.eContents().get(6);

        private final Assignment cPortsAssignment_6_0 = (Assignment) cGroup_6.eContents().get(0);

        private final RuleCall cPortsPortParserRuleCall_6_0_0 = (RuleCall) cPortsAssignment_6_0
                .eContents().get(0);

        private final Group cGroup_6_1 = (Group) cGroup_6.eContents().get(1);

        private final Keyword cCommaKeyword_6_1_0 = (Keyword) cGroup_6_1.eContents().get(0);

        private final Assignment cPortsAssignment_6_1_1 = (Assignment) cGroup_6_1.eContents()
                .get(1);

        private final RuleCall cPortsPortParserRuleCall_6_1_1_0 = (RuleCall) cPortsAssignment_6_1_1
                .eContents().get(0);

        private final Keyword cCommaKeyword_6_2 = (Keyword) cGroup_6.eContents().get(2);

        private final Keyword cInstancesKeyword_7 = (Keyword) cGroup.eContents().get(7);

        private final Assignment cInstancesAssignment_8 = (Assignment) cGroup.eContents().get(8);

        private final RuleCall cInstancesInstanceParserRuleCall_8_0 = (RuleCall) cInstancesAssignment_8
                .eContents().get(0);

        private final Keyword cChannelsKeyword_9 = (Keyword) cGroup.eContents().get(9);

        private final Assignment cChannelsAssignment_10 = (Assignment) cGroup.eContents().get(10);

        private final RuleCall cChannelsChannelParserRuleCall_10_0 = (RuleCall) cChannelsAssignment_10
                .eContents().get(0);

        //ClusterClass:
        //	annotations+=Annotation*
        //	'cluster' 'class' name=IDENTIFIER ('(' (parameters+=Declaration (',' parameters+=Declaration)*)?
        //	')')?
        //	'ports' (ports+=Port (','? ports+=Port)* ','?)?
        //	'instances' instances+=Instance*
        //	'channels' channels+=Channel*;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //annotations+=Annotation* 'cluster' 'class' name=IDENTIFIER ('(' (parameters+=Declaration (','
        //parameters+=Declaration)*)? ')')? 'ports' (ports+=Port (','? ports+=Port)* ','?)? 'instances' instances+=Instance*
        //'channels' channels+=Channel*
        public Group getGroup() {
            return cGroup;
        }

        //annotations+=Annotation*
        public Assignment getAnnotationsAssignment_0() {
            return cAnnotationsAssignment_0;
        }

        //Annotation
        public RuleCall getAnnotationsAnnotationParserRuleCall_0_0() {
            return cAnnotationsAnnotationParserRuleCall_0_0;
        }

        //'cluster'
        public Keyword getClusterKeyword_1() {
            return cClusterKeyword_1;
        }

        //'class'
        public Keyword getClassKeyword_2() {
            return cClassKeyword_2;
        }

        //name=IDENTIFIER
        public Assignment getNameAssignment_3() {
            return cNameAssignment_3;
        }

        //IDENTIFIER
        public RuleCall getNameIDENTIFIERParserRuleCall_3_0() {
            return cNameIDENTIFIERParserRuleCall_3_0;
        }

        //('(' (parameters+=Declaration (',' parameters+=Declaration)*)? ')')?
        public Group getGroup_4() {
            return cGroup_4;
        }

        //'('
        public Keyword getLeftParenthesisKeyword_4_0() {
            return cLeftParenthesisKeyword_4_0;
        }

        //(parameters+=Declaration (',' parameters+=Declaration)*)?
        public Group getGroup_4_1() {
            return cGroup_4_1;
        }

        //parameters+=Declaration
        public Assignment getParametersAssignment_4_1_0() {
            return cParametersAssignment_4_1_0;
        }

        //Declaration
        public RuleCall getParametersDeclarationParserRuleCall_4_1_0_0() {
            return cParametersDeclarationParserRuleCall_4_1_0_0;
        }

        //(',' parameters+=Declaration)*
        public Group getGroup_4_1_1() {
            return cGroup_4_1_1;
        }

        //','
        public Keyword getCommaKeyword_4_1_1_0() {
            return cCommaKeyword_4_1_1_0;
        }

        //parameters+=Declaration
        public Assignment getParametersAssignment_4_1_1_1() {
            return cParametersAssignment_4_1_1_1;
        }

        //Declaration
        public RuleCall getParametersDeclarationParserRuleCall_4_1_1_1_0() {
            return cParametersDeclarationParserRuleCall_4_1_1_1_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_4_2() {
            return cRightParenthesisKeyword_4_2;
        }

        //'ports'
        public Keyword getPortsKeyword_5() {
            return cPortsKeyword_5;
        }

        //(ports+=Port (','? ports+=Port)* ','?)?
        public Group getGroup_6() {
            return cGroup_6;
        }

        //ports+=Port
        public Assignment getPortsAssignment_6_0() {
            return cPortsAssignment_6_0;
        }

        //Port
        public RuleCall getPortsPortParserRuleCall_6_0_0() {
            return cPortsPortParserRuleCall_6_0_0;
        }

        //(','? ports+=Port)*
        public Group getGroup_6_1() {
            return cGroup_6_1;
        }

        //','?
        public Keyword getCommaKeyword_6_1_0() {
            return cCommaKeyword_6_1_0;
        }

        //ports+=Port
        public Assignment getPortsAssignment_6_1_1() {
            return cPortsAssignment_6_1_1;
        }

        //Port
        public RuleCall getPortsPortParserRuleCall_6_1_1_0() {
            return cPortsPortParserRuleCall_6_1_1_0;
        }

        //','?
        public Keyword getCommaKeyword_6_2() {
            return cCommaKeyword_6_2;
        }

        //'instances'
        public Keyword getInstancesKeyword_7() {
            return cInstancesKeyword_7;
        }

        //instances+=Instance*
        public Assignment getInstancesAssignment_8() {
            return cInstancesAssignment_8;
        }

        //Instance
        public RuleCall getInstancesInstanceParserRuleCall_8_0() {
            return cInstancesInstanceParserRuleCall_8_0;
        }

        //'channels'
        public Keyword getChannelsKeyword_9() {
            return cChannelsKeyword_9;
        }

        //channels+=Channel*
        public Assignment getChannelsAssignment_10() {
            return cChannelsAssignment_10;
        }

        //Channel
        public RuleCall getChannelsChannelParserRuleCall_10_0() {
            return cChannelsChannelParserRuleCall_10_0;
        }
    }

    public class InstanceElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.Instance");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Assignment cAnnotationsAssignment_0 = (Assignment) cGroup.eContents().get(0);

        private final RuleCall cAnnotationsAnnotationParserRuleCall_0_0 = (RuleCall) cAnnotationsAssignment_0
                .eContents().get(0);

        private final Assignment cNameAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cNameIDENTIFIERParserRuleCall_1_0 = (RuleCall) cNameAssignment_1
                .eContents().get(0);

        private final Keyword cColonKeyword_2 = (Keyword) cGroup.eContents().get(2);

        private final Assignment cClassDefinitionAssignment_3 = (Assignment) cGroup.eContents()
                .get(3);

        private final RuleCall cClassDefinitionIDENTIFIERParserRuleCall_3_0 = (RuleCall) cClassDefinitionAssignment_3
                .eContents().get(0);

        private final Group cGroup_4 = (Group) cGroup.eContents().get(4);

        private final Keyword cLeftParenthesisKeyword_4_0 = (Keyword) cGroup_4.eContents().get(0);

        private final Group cGroup_4_1 = (Group) cGroup_4.eContents().get(1);

        private final Assignment cInstanceParametersAssignment_4_1_0 = (Assignment) cGroup_4_1
                .eContents().get(0);

        private final RuleCall cInstanceParametersInstanceParameterParserRuleCall_4_1_0_0 = (RuleCall) cInstanceParametersAssignment_4_1_0
                .eContents().get(0);

        private final Group cGroup_4_1_1 = (Group) cGroup_4_1.eContents().get(1);

        private final Keyword cCommaKeyword_4_1_1_0 = (Keyword) cGroup_4_1_1.eContents().get(0);

        private final Assignment cInstanceParametersAssignment_4_1_1_1 = (Assignment) cGroup_4_1_1
                .eContents().get(1);

        private final RuleCall cInstanceParametersInstanceParameterParserRuleCall_4_1_1_1_0 = (RuleCall) cInstanceParametersAssignment_4_1_1_1
                .eContents().get(0);

        private final Keyword cRightParenthesisKeyword_4_2 = (Keyword) cGroup_4.eContents().get(2);

        //Instance:
        //	annotations+=Annotation*
        //	name=IDENTIFIER
        //	':' classDefinition=IDENTIFIER ('(' (instanceParameters+=InstanceParameter (','
        //	instanceParameters+=InstanceParameter)*)? ')')?;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //annotations+=Annotation* name=IDENTIFIER ':' classDefinition=IDENTIFIER ('(' (instanceParameters+=InstanceParameter (','
        //instanceParameters+=InstanceParameter)*)? ')')?
        public Group getGroup() {
            return cGroup;
        }

        //annotations+=Annotation*
        public Assignment getAnnotationsAssignment_0() {
            return cAnnotationsAssignment_0;
        }

        //Annotation
        public RuleCall getAnnotationsAnnotationParserRuleCall_0_0() {
            return cAnnotationsAnnotationParserRuleCall_0_0;
        }

        //name=IDENTIFIER
        public Assignment getNameAssignment_1() {
            return cNameAssignment_1;
        }

        //IDENTIFIER
        public RuleCall getNameIDENTIFIERParserRuleCall_1_0() {
            return cNameIDENTIFIERParserRuleCall_1_0;
        }

        //':'
        public Keyword getColonKeyword_2() {
            return cColonKeyword_2;
        }

        //classDefinition=IDENTIFIER
        public Assignment getClassDefinitionAssignment_3() {
            return cClassDefinitionAssignment_3;
        }

        //IDENTIFIER
        public RuleCall getClassDefinitionIDENTIFIERParserRuleCall_3_0() {
            return cClassDefinitionIDENTIFIERParserRuleCall_3_0;
        }

        //('(' (instanceParameters+=InstanceParameter (',' instanceParameters+=InstanceParameter)*)? ')')?
        public Group getGroup_4() {
            return cGroup_4;
        }

        //'('
        public Keyword getLeftParenthesisKeyword_4_0() {
            return cLeftParenthesisKeyword_4_0;
        }

        //(instanceParameters+=InstanceParameter (',' instanceParameters+=InstanceParameter)*)?
        public Group getGroup_4_1() {
            return cGroup_4_1;
        }

        //instanceParameters+=InstanceParameter
        public Assignment getInstanceParametersAssignment_4_1_0() {
            return cInstanceParametersAssignment_4_1_0;
        }

        //InstanceParameter
        public RuleCall getInstanceParametersInstanceParameterParserRuleCall_4_1_0_0() {
            return cInstanceParametersInstanceParameterParserRuleCall_4_1_0_0;
        }

        //(',' instanceParameters+=InstanceParameter)*
        public Group getGroup_4_1_1() {
            return cGroup_4_1_1;
        }

        //','
        public Keyword getCommaKeyword_4_1_1_0() {
            return cCommaKeyword_4_1_1_0;
        }

        //instanceParameters+=InstanceParameter
        public Assignment getInstanceParametersAssignment_4_1_1_1() {
            return cInstanceParametersAssignment_4_1_1_1;
        }

        //InstanceParameter
        public RuleCall getInstanceParametersInstanceParameterParserRuleCall_4_1_1_1_0() {
            return cInstanceParametersInstanceParameterParserRuleCall_4_1_1_1_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_4_2() {
            return cRightParenthesisKeyword_4_2;
        }
    }

    public class InstanceParameterElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.InstanceParameter");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Assignment cParameterAssignment_0 = (Assignment) cGroup.eContents().get(0);

        private final RuleCall cParameterIDENTIFIERParserRuleCall_0_0 = (RuleCall) cParameterAssignment_0
                .eContents().get(0);

        private final Keyword cColonEqualsSignKeyword_1 = (Keyword) cGroup.eContents().get(1);

        private final Assignment cExpressionAssignment_2 = (Assignment) cGroup.eContents().get(2);

        private final RuleCall cExpressionExpressionParserRuleCall_2_0 = (RuleCall) cExpressionAssignment_2
                .eContents().get(0);

        //InstanceParameter:
        //	parameter=IDENTIFIER ':=' expression=Expression;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //parameter=IDENTIFIER ':=' expression=Expression
        public Group getGroup() {
            return cGroup;
        }

        //parameter=IDENTIFIER
        public Assignment getParameterAssignment_0() {
            return cParameterAssignment_0;
        }

        //IDENTIFIER
        public RuleCall getParameterIDENTIFIERParserRuleCall_0_0() {
            return cParameterIDENTIFIERParserRuleCall_0_0;
        }

        //':='
        public Keyword getColonEqualsSignKeyword_1() {
            return cColonEqualsSignKeyword_1;
        }

        //expression=Expression
        public Assignment getExpressionAssignment_2() {
            return cExpressionAssignment_2;
        }

        //Expression
        public RuleCall getExpressionExpressionParserRuleCall_2_0() {
            return cExpressionExpressionParserRuleCall_2_0;
        }
    }

    public class ChannelElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.Channel");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Action cChannelAction_0 = (Action) cGroup.eContents().get(0);

        private final Assignment cAnnotationsAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cAnnotationsAnnotationParserRuleCall_1_0 = (RuleCall) cAnnotationsAssignment_1
                .eContents().get(0);

        private final Keyword cLeftCurlyBracketKeyword_2 = (Keyword) cGroup.eContents().get(2);

        private final Alternatives cAlternatives_3 = (Alternatives) cGroup.eContents().get(3);

        private final Group cGroup_3_0 = (Group) cAlternatives_3.eContents().get(0);

        private final Assignment cInstancePortsAssignment_3_0_0 = (Assignment) cGroup_3_0
                .eContents().get(0);

        private final RuleCall cInstancePortsInstancePortParserRuleCall_3_0_0_0 = (RuleCall) cInstancePortsAssignment_3_0_0
                .eContents().get(0);

        private final Group cGroup_3_0_1 = (Group) cGroup_3_0.eContents().get(1);

        private final Keyword cCommaKeyword_3_0_1_0 = (Keyword) cGroup_3_0_1.eContents().get(0);

        private final Assignment cInstancePortsAssignment_3_0_1_1 = (Assignment) cGroup_3_0_1
                .eContents().get(1);

        private final RuleCall cInstancePortsInstancePortParserRuleCall_3_0_1_1_0 = (RuleCall) cInstancePortsAssignment_3_0_1_1
                .eContents().get(0);

        private final Group cGroup_3_0_2 = (Group) cGroup_3_0.eContents().get(2);

        private final Keyword cCommaKeyword_3_0_2_0 = (Keyword) cGroup_3_0_2.eContents().get(0);

        private final Assignment cExternalPortAssignment_3_0_2_1 = (Assignment) cGroup_3_0_2
                .eContents().get(1);

        private final CrossReference cExternalPortPortCrossReference_3_0_2_1_0 = (CrossReference) cExternalPortAssignment_3_0_2_1
                .eContents().get(0);

        private final RuleCall cExternalPortPortIDENTIFIERParserRuleCall_3_0_2_1_0_1 = (RuleCall) cExternalPortPortCrossReference_3_0_2_1_0
                .eContents().get(1);

        private final Group cGroup_3_0_2_2 = (Group) cGroup_3_0_2.eContents().get(2);

        private final Keyword cCommaKeyword_3_0_2_2_0 = (Keyword) cGroup_3_0_2_2.eContents().get(0);

        private final Assignment cInstancePortsAssignment_3_0_2_2_1 = (Assignment) cGroup_3_0_2_2
                .eContents().get(1);

        private final RuleCall cInstancePortsInstancePortParserRuleCall_3_0_2_2_1_0 = (RuleCall) cInstancePortsAssignment_3_0_2_2_1
                .eContents().get(0);

        private final Group cGroup_3_1 = (Group) cAlternatives_3.eContents().get(1);

        private final Assignment cExternalPortAssignment_3_1_0 = (Assignment) cGroup_3_1.eContents()
                .get(0);

        private final CrossReference cExternalPortPortCrossReference_3_1_0_0 = (CrossReference) cExternalPortAssignment_3_1_0
                .eContents().get(0);

        private final RuleCall cExternalPortPortIDENTIFIERParserRuleCall_3_1_0_0_1 = (RuleCall) cExternalPortPortCrossReference_3_1_0_0
                .eContents().get(1);

        private final Group cGroup_3_1_1 = (Group) cGroup_3_1.eContents().get(1);

        private final Keyword cCommaKeyword_3_1_1_0 = (Keyword) cGroup_3_1_1.eContents().get(0);

        private final Assignment cInstancePortsAssignment_3_1_1_1 = (Assignment) cGroup_3_1_1
                .eContents().get(1);

        private final RuleCall cInstancePortsInstancePortParserRuleCall_3_1_1_1_0 = (RuleCall) cInstancePortsAssignment_3_1_1_1
                .eContents().get(0);

        private final Keyword cRightCurlyBracketKeyword_4 = (Keyword) cGroup.eContents().get(4);

        //Channel:
        //	{Channel} annotations+=Annotation*
        //	'{' (instancePorts+=InstancePort (',' instancePorts+=InstancePort)* (',' externalPort=[Port|IDENTIFIER] (','
        //	instancePorts+=InstancePort)*)?
        //	| externalPort=[Port|IDENTIFIER] (',' instancePorts+=InstancePort)*)? '}';
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //{Channel} annotations+=Annotation* '{' (instancePorts+=InstancePort (',' instancePorts+=InstancePort)* (','
        //externalPort=[Port|IDENTIFIER] (',' instancePorts+=InstancePort)*)? | externalPort=[Port|IDENTIFIER] (','
        //instancePorts+=InstancePort)*)? '}'
        public Group getGroup() {
            return cGroup;
        }

        //{Channel}
        public Action getChannelAction_0() {
            return cChannelAction_0;
        }

        //annotations+=Annotation*
        public Assignment getAnnotationsAssignment_1() {
            return cAnnotationsAssignment_1;
        }

        //Annotation
        public RuleCall getAnnotationsAnnotationParserRuleCall_1_0() {
            return cAnnotationsAnnotationParserRuleCall_1_0;
        }

        //'{'
        public Keyword getLeftCurlyBracketKeyword_2() {
            return cLeftCurlyBracketKeyword_2;
        }

        //(instancePorts+=InstancePort (',' instancePorts+=InstancePort)* (',' externalPort=[Port|IDENTIFIER] (','
        //instancePorts+=InstancePort)*)? | externalPort=[Port|IDENTIFIER] (',' instancePorts+=InstancePort)*)?
        public Alternatives getAlternatives_3() {
            return cAlternatives_3;
        }

        //instancePorts+=InstancePort (',' instancePorts+=InstancePort)* (',' externalPort=[Port|IDENTIFIER] (','
        //instancePorts+=InstancePort)*)?
        public Group getGroup_3_0() {
            return cGroup_3_0;
        }

        //instancePorts+=InstancePort
        public Assignment getInstancePortsAssignment_3_0_0() {
            return cInstancePortsAssignment_3_0_0;
        }

        //InstancePort
        public RuleCall getInstancePortsInstancePortParserRuleCall_3_0_0_0() {
            return cInstancePortsInstancePortParserRuleCall_3_0_0_0;
        }

        //(',' instancePorts+=InstancePort)*
        public Group getGroup_3_0_1() {
            return cGroup_3_0_1;
        }

        //','
        public Keyword getCommaKeyword_3_0_1_0() {
            return cCommaKeyword_3_0_1_0;
        }

        //instancePorts+=InstancePort
        public Assignment getInstancePortsAssignment_3_0_1_1() {
            return cInstancePortsAssignment_3_0_1_1;
        }

        //InstancePort
        public RuleCall getInstancePortsInstancePortParserRuleCall_3_0_1_1_0() {
            return cInstancePortsInstancePortParserRuleCall_3_0_1_1_0;
        }

        //(',' externalPort=[Port|IDENTIFIER] (',' instancePorts+=InstancePort)*)?
        public Group getGroup_3_0_2() {
            return cGroup_3_0_2;
        }

        //','
        public Keyword getCommaKeyword_3_0_2_0() {
            return cCommaKeyword_3_0_2_0;
        }

        //externalPort=[Port|IDENTIFIER]
        public Assignment getExternalPortAssignment_3_0_2_1() {
            return cExternalPortAssignment_3_0_2_1;
        }

        //[Port|IDENTIFIER]
        public CrossReference getExternalPortPortCrossReference_3_0_2_1_0() {
            return cExternalPortPortCrossReference_3_0_2_1_0;
        }

        //IDENTIFIER
        public RuleCall getExternalPortPortIDENTIFIERParserRuleCall_3_0_2_1_0_1() {
            return cExternalPortPortIDENTIFIERParserRuleCall_3_0_2_1_0_1;
        }

        //(',' instancePorts+=InstancePort)*
        public Group getGroup_3_0_2_2() {
            return cGroup_3_0_2_2;
        }

        //','
        public Keyword getCommaKeyword_3_0_2_2_0() {
            return cCommaKeyword_3_0_2_2_0;
        }

        //instancePorts+=InstancePort
        public Assignment getInstancePortsAssignment_3_0_2_2_1() {
            return cInstancePortsAssignment_3_0_2_2_1;
        }

        //InstancePort
        public RuleCall getInstancePortsInstancePortParserRuleCall_3_0_2_2_1_0() {
            return cInstancePortsInstancePortParserRuleCall_3_0_2_2_1_0;
        }

        //externalPort=[Port|IDENTIFIER] (',' instancePorts+=InstancePort)*
        public Group getGroup_3_1() {
            return cGroup_3_1;
        }

        //externalPort=[Port|IDENTIFIER]
        public Assignment getExternalPortAssignment_3_1_0() {
            return cExternalPortAssignment_3_1_0;
        }

        //[Port|IDENTIFIER]
        public CrossReference getExternalPortPortCrossReference_3_1_0_0() {
            return cExternalPortPortCrossReference_3_1_0_0;
        }

        //IDENTIFIER
        public RuleCall getExternalPortPortIDENTIFIERParserRuleCall_3_1_0_0_1() {
            return cExternalPortPortIDENTIFIERParserRuleCall_3_1_0_0_1;
        }

        //(',' instancePorts+=InstancePort)*
        public Group getGroup_3_1_1() {
            return cGroup_3_1_1;
        }

        //','
        public Keyword getCommaKeyword_3_1_1_0() {
            return cCommaKeyword_3_1_1_0;
        }

        //instancePorts+=InstancePort
        public Assignment getInstancePortsAssignment_3_1_1_1() {
            return cInstancePortsAssignment_3_1_1_1;
        }

        //InstancePort
        public RuleCall getInstancePortsInstancePortParserRuleCall_3_1_1_1_0() {
            return cInstancePortsInstancePortParserRuleCall_3_1_1_1_0;
        }

        //'}'
        public Keyword getRightCurlyBracketKeyword_4() {
            return cRightCurlyBracketKeyword_4;
        }
    }

    public class InstancePortElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.InstancePort");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Assignment cInstanceAssignment_0 = (Assignment) cGroup.eContents().get(0);

        private final CrossReference cInstanceInstanceCrossReference_0_0 = (CrossReference) cInstanceAssignment_0
                .eContents().get(0);

        private final RuleCall cInstanceInstanceIDENTIFIERParserRuleCall_0_0_1 = (RuleCall) cInstanceInstanceCrossReference_0_0
                .eContents().get(1);

        private final Keyword cFullStopKeyword_1 = (Keyword) cGroup.eContents().get(1);

        private final Assignment cPortAssignment_2 = (Assignment) cGroup.eContents().get(2);

        private final RuleCall cPortPortReferenceParserRuleCall_2_0 = (RuleCall) cPortAssignment_2
                .eContents().get(0);

        //InstancePort:
        //	instance=[Instance|IDENTIFIER] '.' port=PortReference;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //instance=[Instance|IDENTIFIER] '.' port=PortReference
        public Group getGroup() {
            return cGroup;
        }

        //instance=[Instance|IDENTIFIER]
        public Assignment getInstanceAssignment_0() {
            return cInstanceAssignment_0;
        }

        //[Instance|IDENTIFIER]
        public CrossReference getInstanceInstanceCrossReference_0_0() {
            return cInstanceInstanceCrossReference_0_0;
        }

        //IDENTIFIER
        public RuleCall getInstanceInstanceIDENTIFIERParserRuleCall_0_0_1() {
            return cInstanceInstanceIDENTIFIERParserRuleCall_0_0_1;
        }

        //'.'
        public Keyword getFullStopKeyword_1() {
            return cFullStopKeyword_1;
        }

        //port=PortReference
        public Assignment getPortAssignment_2() {
            return cPortAssignment_2;
        }

        //PortReference
        public RuleCall getPortPortReferenceParserRuleCall_2_0() {
            return cPortPortReferenceParserRuleCall_2_0;
        }
    }

    public class ExpressionElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.Expression");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final RuleCall cExpressionSingleParserRuleCall_0 = (RuleCall) cGroup.eContents()
                .get(0);

        private final Group cGroup_1 = (Group) cGroup.eContents().get(1);

        private final Action cExpressionSequenceExpressionsAction_1_0 = (Action) cGroup_1
                .eContents().get(0);

        private final Group cGroup_1_1 = (Group) cGroup_1.eContents().get(1);

        private final Keyword cSemicolonKeyword_1_1_0 = (Keyword) cGroup_1_1.eContents().get(0);

        private final Assignment cExpressionsAssignment_1_1_1 = (Assignment) cGroup_1_1.eContents()
                .get(1);

        private final RuleCall cExpressionsExpressionSingleParserRuleCall_1_1_1_0 = (RuleCall) cExpressionsAssignment_1_1_1
                .eContents().get(0);

        //// === Expressions =======
        //Expression:
        //	ExpressionSingle ({ExpressionSequence.expressions+=current} (';' expressions+=ExpressionSingle)+)?;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //ExpressionSingle ({ExpressionSequence.expressions+=current} (';' expressions+=ExpressionSingle)+)?
        public Group getGroup() {
            return cGroup;
        }

        //ExpressionSingle
        public RuleCall getExpressionSingleParserRuleCall_0() {
            return cExpressionSingleParserRuleCall_0;
        }

        //({ExpressionSequence.expressions+=current} (';' expressions+=ExpressionSingle)+)?
        public Group getGroup_1() {
            return cGroup_1;
        }

        //{ExpressionSequence.expressions+=current}
        public Action getExpressionSequenceExpressionsAction_1_0() {
            return cExpressionSequenceExpressionsAction_1_0;
        }

        //(';' expressions+=ExpressionSingle)+
        public Group getGroup_1_1() {
            return cGroup_1_1;
        }

        //';'
        public Keyword getSemicolonKeyword_1_1_0() {
            return cSemicolonKeyword_1_1_0;
        }

        //expressions+=ExpressionSingle
        public Assignment getExpressionsAssignment_1_1_1() {
            return cExpressionsAssignment_1_1_1;
        }

        //ExpressionSingle
        public RuleCall getExpressionsExpressionSingleParserRuleCall_1_1_1_0() {
            return cExpressionsExpressionSingleParserRuleCall_1_1_1_0;
        }
    }

    public class ExpressionSingleElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.ExpressionSingle");

        private final RuleCall cExpressionLevel1ParserRuleCall = (RuleCall) rule.eContents().get(1);

        //ExpressionSingle Expression:
        //	ExpressionLevel1;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //ExpressionLevel1
        public RuleCall getExpressionLevel1ParserRuleCall() {
            return cExpressionLevel1ParserRuleCall;
        }
    }

    public class ExpressionLevel1Elements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.ExpressionLevel1");

        private final Alternatives cAlternatives = (Alternatives) rule.eContents().get(1);

        private final RuleCall cExpressionLevel2ParserRuleCall_0 = (RuleCall) cAlternatives
                .eContents().get(0);

        private final Group cGroup_1 = (Group) cAlternatives.eContents().get(1);

        private final Action cAssignmentExpressionAction_1_0 = (Action) cGroup_1.eContents().get(0);

        private final Assignment cVariableAssignment_1_1 = (Assignment) cGroup_1.eContents().get(1);

        private final RuleCall cVariableIDENTIFIERParserRuleCall_1_1_0 = (RuleCall) cVariableAssignment_1_1
                .eContents().get(0);

        private final Keyword cColonEqualsSignKeyword_1_2 = (Keyword) cGroup_1.eContents().get(2);

        private final Assignment cExpressionAssignment_1_3 = (Assignment) cGroup_1.eContents()
                .get(3);

        private final RuleCall cExpressionExpressionLevel1ParserRuleCall_1_3_0 = (RuleCall) cExpressionAssignment_1_3
                .eContents().get(0);

        private final Group cGroup_2 = (Group) cAlternatives.eContents().get(2);

        private final Action cReturnExpressionAction_2_0 = (Action) cGroup_2.eContents().get(0);

        private final Keyword cReturnKeyword_2_1 = (Keyword) cGroup_2.eContents().get(1);

        private final Assignment cExpressionAssignment_2_2 = (Assignment) cGroup_2.eContents()
                .get(2);

        private final RuleCall cExpressionExpressionLevel1ParserRuleCall_2_2_0 = (RuleCall) cExpressionAssignment_2_2
                .eContents().get(0);

        //ExpressionLevel1 Expression:
        //	ExpressionLevel2
        //	| {AssignmentExpression} variable=IDENTIFIER ':=' expression=ExpressionLevel1 | {ReturnExpression} 'return'
        //	expression=ExpressionLevel1;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //ExpressionLevel2 | {AssignmentExpression} variable=IDENTIFIER ':=' expression=ExpressionLevel1 | {ReturnExpression}
        //'return' expression=ExpressionLevel1
        public Alternatives getAlternatives() {
            return cAlternatives;
        }

        //ExpressionLevel2
        public RuleCall getExpressionLevel2ParserRuleCall_0() {
            return cExpressionLevel2ParserRuleCall_0;
        }

        //{AssignmentExpression} variable=IDENTIFIER ':=' expression=ExpressionLevel1
        public Group getGroup_1() {
            return cGroup_1;
        }

        //{AssignmentExpression}
        public Action getAssignmentExpressionAction_1_0() {
            return cAssignmentExpressionAction_1_0;
        }

        //variable=IDENTIFIER
        public Assignment getVariableAssignment_1_1() {
            return cVariableAssignment_1_1;
        }

        //IDENTIFIER
        public RuleCall getVariableIDENTIFIERParserRuleCall_1_1_0() {
            return cVariableIDENTIFIERParserRuleCall_1_1_0;
        }

        //':='
        public Keyword getColonEqualsSignKeyword_1_2() {
            return cColonEqualsSignKeyword_1_2;
        }

        //expression=ExpressionLevel1
        public Assignment getExpressionAssignment_1_3() {
            return cExpressionAssignment_1_3;
        }

        //ExpressionLevel1
        public RuleCall getExpressionExpressionLevel1ParserRuleCall_1_3_0() {
            return cExpressionExpressionLevel1ParserRuleCall_1_3_0;
        }

        //{ReturnExpression} 'return' expression=ExpressionLevel1
        public Group getGroup_2() {
            return cGroup_2;
        }

        //{ReturnExpression}
        public Action getReturnExpressionAction_2_0() {
            return cReturnExpressionAction_2_0;
        }

        //'return'
        public Keyword getReturnKeyword_2_1() {
            return cReturnKeyword_2_1;
        }

        //expression=ExpressionLevel1
        public Assignment getExpressionAssignment_2_2() {
            return cExpressionAssignment_2_2;
        }

        //ExpressionLevel1
        public RuleCall getExpressionExpressionLevel1ParserRuleCall_2_2_0() {
            return cExpressionExpressionLevel1ParserRuleCall_2_2_0;
        }
    }

    public class ExpressionLevel2Elements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.ExpressionLevel2");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final RuleCall cExpressionLevel3ParserRuleCall_0 = (RuleCall) cGroup.eContents()
                .get(0);

        private final Group cGroup_1 = (Group) cGroup.eContents().get(1);

        private final Action cBinaryOperatorExpressionLeftOperandAction_1_0 = (Action) cGroup_1
                .eContents().get(0);

        private final Assignment cNameAssignment_1_1 = (Assignment) cGroup_1.eContents().get(1);

        private final RuleCall cNameBinaryOperatorLevel2EnumRuleCall_1_1_0 = (RuleCall) cNameAssignment_1_1
                .eContents().get(0);

        private final Assignment cRightOperandAssignment_1_2 = (Assignment) cGroup_1.eContents()
                .get(2);

        private final RuleCall cRightOperandExpressionLevel3ParserRuleCall_1_2_0 = (RuleCall) cRightOperandAssignment_1_2
                .eContents().get(0);

        //ExpressionLevel2 Expression:
        //	ExpressionLevel3 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel2
        //	rightOperand=ExpressionLevel3)*;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //// Left associativity
        //ExpressionLevel3 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel2
        //rightOperand=ExpressionLevel3)*
        public Group getGroup() {
            return cGroup;
        }

        //// Left associativity
        //ExpressionLevel3
        public RuleCall getExpressionLevel3ParserRuleCall_0() {
            return cExpressionLevel3ParserRuleCall_0;
        }

        //({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel2 rightOperand=ExpressionLevel3)*
        public Group getGroup_1() {
            return cGroup_1;
        }

        //{BinaryOperatorExpression.leftOperand=current}
        public Action getBinaryOperatorExpressionLeftOperandAction_1_0() {
            return cBinaryOperatorExpressionLeftOperandAction_1_0;
        }

        //name=BinaryOperatorLevel2
        public Assignment getNameAssignment_1_1() {
            return cNameAssignment_1_1;
        }

        //BinaryOperatorLevel2
        public RuleCall getNameBinaryOperatorLevel2EnumRuleCall_1_1_0() {
            return cNameBinaryOperatorLevel2EnumRuleCall_1_1_0;
        }

        //rightOperand=ExpressionLevel3
        public Assignment getRightOperandAssignment_1_2() {
            return cRightOperandAssignment_1_2;
        }

        //ExpressionLevel3
        public RuleCall getRightOperandExpressionLevel3ParserRuleCall_1_2_0() {
            return cRightOperandExpressionLevel3ParserRuleCall_1_2_0;
        }
    }

    public class ExpressionLevel3Elements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.ExpressionLevel3");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final RuleCall cExpressionLevel4ParserRuleCall_0 = (RuleCall) cGroup.eContents()
                .get(0);

        private final Group cGroup_1 = (Group) cGroup.eContents().get(1);

        private final Action cBinaryOperatorExpressionLeftOperandAction_1_0 = (Action) cGroup_1
                .eContents().get(0);

        private final Assignment cNameAssignment_1_1 = (Assignment) cGroup_1.eContents().get(1);

        private final RuleCall cNameBinaryOperatorLevel3EnumRuleCall_1_1_0 = (RuleCall) cNameAssignment_1_1
                .eContents().get(0);

        private final Assignment cRightOperandAssignment_1_2 = (Assignment) cGroup_1.eContents()
                .get(2);

        private final RuleCall cRightOperandExpressionLevel4ParserRuleCall_1_2_0 = (RuleCall) cRightOperandAssignment_1_2
                .eContents().get(0);

        //ExpressionLevel3 Expression:
        //	ExpressionLevel4 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel3
        //	rightOperand=ExpressionLevel4)*;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //// Left associativity
        //ExpressionLevel4 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel3
        //rightOperand=ExpressionLevel4)*
        public Group getGroup() {
            return cGroup;
        }

        //// Left associativity
        //ExpressionLevel4
        public RuleCall getExpressionLevel4ParserRuleCall_0() {
            return cExpressionLevel4ParserRuleCall_0;
        }

        //({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel3 rightOperand=ExpressionLevel4)*
        public Group getGroup_1() {
            return cGroup_1;
        }

        //{BinaryOperatorExpression.leftOperand=current}
        public Action getBinaryOperatorExpressionLeftOperandAction_1_0() {
            return cBinaryOperatorExpressionLeftOperandAction_1_0;
        }

        //name=BinaryOperatorLevel3
        public Assignment getNameAssignment_1_1() {
            return cNameAssignment_1_1;
        }

        //BinaryOperatorLevel3
        public RuleCall getNameBinaryOperatorLevel3EnumRuleCall_1_1_0() {
            return cNameBinaryOperatorLevel3EnumRuleCall_1_1_0;
        }

        //rightOperand=ExpressionLevel4
        public Assignment getRightOperandAssignment_1_2() {
            return cRightOperandAssignment_1_2;
        }

        //ExpressionLevel4
        public RuleCall getRightOperandExpressionLevel4ParserRuleCall_1_2_0() {
            return cRightOperandExpressionLevel4ParserRuleCall_1_2_0;
        }
    }

    public class ExpressionLevel4Elements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.ExpressionLevel4");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final RuleCall cExpressionLevel5ParserRuleCall_0 = (RuleCall) cGroup.eContents()
                .get(0);

        private final Group cGroup_1 = (Group) cGroup.eContents().get(1);

        private final Action cBinaryOperatorExpressionLeftOperandAction_1_0 = (Action) cGroup_1
                .eContents().get(0);

        private final Assignment cNameAssignment_1_1 = (Assignment) cGroup_1.eContents().get(1);

        private final RuleCall cNameBinaryOperatorLevel4EnumRuleCall_1_1_0 = (RuleCall) cNameAssignment_1_1
                .eContents().get(0);

        private final Assignment cRightOperandAssignment_1_2 = (Assignment) cGroup_1.eContents()
                .get(2);

        private final RuleCall cRightOperandExpressionLevel5ParserRuleCall_1_2_0 = (RuleCall) cRightOperandAssignment_1_2
                .eContents().get(0);

        //ExpressionLevel4 Expression:
        //	ExpressionLevel5 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel4
        //	rightOperand=ExpressionLevel5)*;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //// Left associativity
        //ExpressionLevel5 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel4
        //rightOperand=ExpressionLevel5)*
        public Group getGroup() {
            return cGroup;
        }

        //// Left associativity
        //ExpressionLevel5
        public RuleCall getExpressionLevel5ParserRuleCall_0() {
            return cExpressionLevel5ParserRuleCall_0;
        }

        //({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5)*
        public Group getGroup_1() {
            return cGroup_1;
        }

        //{BinaryOperatorExpression.leftOperand=current}
        public Action getBinaryOperatorExpressionLeftOperandAction_1_0() {
            return cBinaryOperatorExpressionLeftOperandAction_1_0;
        }

        //name=BinaryOperatorLevel4
        public Assignment getNameAssignment_1_1() {
            return cNameAssignment_1_1;
        }

        //BinaryOperatorLevel4
        public RuleCall getNameBinaryOperatorLevel4EnumRuleCall_1_1_0() {
            return cNameBinaryOperatorLevel4EnumRuleCall_1_1_0;
        }

        //rightOperand=ExpressionLevel5
        public Assignment getRightOperandAssignment_1_2() {
            return cRightOperandAssignment_1_2;
        }

        //ExpressionLevel5
        public RuleCall getRightOperandExpressionLevel5ParserRuleCall_1_2_0() {
            return cRightOperandExpressionLevel5ParserRuleCall_1_2_0;
        }
    }

    public class ExpressionLevel5Elements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.ExpressionLevel5");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final RuleCall cExpressionLevel6ParserRuleCall_0 = (RuleCall) cGroup.eContents()
                .get(0);

        private final Group cGroup_1 = (Group) cGroup.eContents().get(1);

        private final Action cDataMethodCallExpressionTargetAction_1_0 = (Action) cGroup_1
                .eContents().get(0);

        private final Assignment cOnSuperClassAssignment_1_1 = (Assignment) cGroup_1.eContents()
                .get(1);

        private final Keyword cOnSuperClassCircumflexAccentKeyword_1_1_0 = (Keyword) cOnSuperClassAssignment_1_1
                .eContents().get(0);

        private final Assignment cNameAssignment_1_2 = (Assignment) cGroup_1.eContents().get(2);

        private final RuleCall cNameIDENTIFIERParserRuleCall_1_2_0 = (RuleCall) cNameAssignment_1_2
                .eContents().get(0);

        private final Group cGroup_1_3 = (Group) cGroup_1.eContents().get(3);

        private final Keyword cLeftParenthesisKeyword_1_3_0 = (Keyword) cGroup_1_3.eContents()
                .get(0);

        private final Group cGroup_1_3_1 = (Group) cGroup_1_3.eContents().get(1);

        private final Assignment cArgumentsAssignment_1_3_1_0 = (Assignment) cGroup_1_3_1
                .eContents().get(0);

        private final RuleCall cArgumentsExpressionParserRuleCall_1_3_1_0_0 = (RuleCall) cArgumentsAssignment_1_3_1_0
                .eContents().get(0);

        private final Group cGroup_1_3_1_1 = (Group) cGroup_1_3_1.eContents().get(1);

        private final Keyword cCommaKeyword_1_3_1_1_0 = (Keyword) cGroup_1_3_1_1.eContents().get(0);

        private final Assignment cArgumentsAssignment_1_3_1_1_1 = (Assignment) cGroup_1_3_1_1
                .eContents().get(1);

        private final RuleCall cArgumentsExpressionParserRuleCall_1_3_1_1_1_0 = (RuleCall) cArgumentsAssignment_1_3_1_1_1
                .eContents().get(0);

        private final Keyword cRightParenthesisKeyword_1_3_2 = (Keyword) cGroup_1_3.eContents()
                .get(2);

        //ExpressionLevel5 Expression:
        //	ExpressionLevel6 ({DataMethodCallExpression.target=current} onSuperClass?='^'? name=IDENTIFIER ('('
        //	(arguments+=Expression (',' arguments+=Expression)*)? ')')?)*;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //ExpressionLevel6 ({DataMethodCallExpression.target=current} onSuperClass?='^'? name=IDENTIFIER ('('
        //(arguments+=Expression (',' arguments+=Expression)*)? ')')?)*
        public Group getGroup() {
            return cGroup;
        }

        //ExpressionLevel6
        public RuleCall getExpressionLevel6ParserRuleCall_0() {
            return cExpressionLevel6ParserRuleCall_0;
        }

        //({DataMethodCallExpression.target=current} onSuperClass?='^'? name=IDENTIFIER ('(' (arguments+=Expression (','
        //arguments+=Expression)*)? ')')?)*
        public Group getGroup_1() {
            return cGroup_1;
        }

        //{DataMethodCallExpression.target=current}
        public Action getDataMethodCallExpressionTargetAction_1_0() {
            return cDataMethodCallExpressionTargetAction_1_0;
        }

        //onSuperClass?='^'?
        public Assignment getOnSuperClassAssignment_1_1() {
            return cOnSuperClassAssignment_1_1;
        }

        //'^'
        public Keyword getOnSuperClassCircumflexAccentKeyword_1_1_0() {
            return cOnSuperClassCircumflexAccentKeyword_1_1_0;
        }

        //name=IDENTIFIER
        public Assignment getNameAssignment_1_2() {
            return cNameAssignment_1_2;
        }

        //IDENTIFIER
        public RuleCall getNameIDENTIFIERParserRuleCall_1_2_0() {
            return cNameIDENTIFIERParserRuleCall_1_2_0;
        }

        //('(' (arguments+=Expression (',' arguments+=Expression)*)? ')')?
        public Group getGroup_1_3() {
            return cGroup_1_3;
        }

        //'('
        public Keyword getLeftParenthesisKeyword_1_3_0() {
            return cLeftParenthesisKeyword_1_3_0;
        }

        //(arguments+=Expression (',' arguments+=Expression)*)?
        public Group getGroup_1_3_1() {
            return cGroup_1_3_1;
        }

        //arguments+=Expression
        public Assignment getArgumentsAssignment_1_3_1_0() {
            return cArgumentsAssignment_1_3_1_0;
        }

        //Expression
        public RuleCall getArgumentsExpressionParserRuleCall_1_3_1_0_0() {
            return cArgumentsExpressionParserRuleCall_1_3_1_0_0;
        }

        //(',' arguments+=Expression)*
        public Group getGroup_1_3_1_1() {
            return cGroup_1_3_1_1;
        }

        //','
        public Keyword getCommaKeyword_1_3_1_1_0() {
            return cCommaKeyword_1_3_1_1_0;
        }

        //arguments+=Expression
        public Assignment getArgumentsAssignment_1_3_1_1_1() {
            return cArgumentsAssignment_1_3_1_1_1;
        }

        //Expression
        public RuleCall getArgumentsExpressionParserRuleCall_1_3_1_1_1_0() {
            return cArgumentsExpressionParserRuleCall_1_3_1_1_1_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_1_3_2() {
            return cRightParenthesisKeyword_1_3_2;
        }
    }

    public class ExpressionLevel6Elements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.ExpressionLevel6");

        private final Alternatives cAlternatives = (Alternatives) rule.eContents().get(1);

        private final RuleCall cExpressionLevel7ParserRuleCall_0 = (RuleCall) cAlternatives
                .eContents().get(0);

        private final RuleCall cUnaryOperatorExpressionParserRuleCall_1 = (RuleCall) cAlternatives
                .eContents().get(1);

        private final RuleCall cIfExpressionParserRuleCall_2 = (RuleCall) cAlternatives.eContents()
                .get(2);

        private final RuleCall cWhileExpressionParserRuleCall_3 = (RuleCall) cAlternatives
                .eContents().get(3);

        private final RuleCall cSwitchExpressionParserRuleCall_4 = (RuleCall) cAlternatives
                .eContents().get(4);

        //ExpressionLevel6 Expression:
        //	=> ExpressionLevel7 | UnaryOperatorExpression | IfExpression | WhileExpression | SwitchExpression
        //	// ExpressionLevel7 should occur before UnaryOperatorExpression, to avoid unary minus when possible
        //;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //=> ExpressionLevel7 | UnaryOperatorExpression | IfExpression | WhileExpression | SwitchExpression
        public Alternatives getAlternatives() {
            return cAlternatives;
        }

        //=> ExpressionLevel7
        public RuleCall getExpressionLevel7ParserRuleCall_0() {
            return cExpressionLevel7ParserRuleCall_0;
        }

        //UnaryOperatorExpression
        public RuleCall getUnaryOperatorExpressionParserRuleCall_1() {
            return cUnaryOperatorExpressionParserRuleCall_1;
        }

        //IfExpression
        public RuleCall getIfExpressionParserRuleCall_2() {
            return cIfExpressionParserRuleCall_2;
        }

        //WhileExpression
        public RuleCall getWhileExpressionParserRuleCall_3() {
            return cWhileExpressionParserRuleCall_3;
        }

        //SwitchExpression
        public RuleCall getSwitchExpressionParserRuleCall_4() {
            return cSwitchExpressionParserRuleCall_4;
        }
    }

    public class UnaryOperatorExpressionElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.UnaryOperatorExpression");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Assignment cNameAssignment_0 = (Assignment) cGroup.eContents().get(0);

        private final RuleCall cNameOperatorUnaryEnumRuleCall_0_0 = (RuleCall) cNameAssignment_0
                .eContents().get(0);

        private final Assignment cOperandAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cOperandExpressionLevel7ParserRuleCall_1_0 = (RuleCall) cOperandAssignment_1
                .eContents().get(0);

        //UnaryOperatorExpression:
        //	name=OperatorUnary operand=ExpressionLevel7;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //name=OperatorUnary operand=ExpressionLevel7
        public Group getGroup() {
            return cGroup;
        }

        //name=OperatorUnary
        public Assignment getNameAssignment_0() {
            return cNameAssignment_0;
        }

        //OperatorUnary
        public RuleCall getNameOperatorUnaryEnumRuleCall_0_0() {
            return cNameOperatorUnaryEnumRuleCall_0_0;
        }

        //operand=ExpressionLevel7
        public Assignment getOperandAssignment_1() {
            return cOperandAssignment_1;
        }

        //ExpressionLevel7
        public RuleCall getOperandExpressionLevel7ParserRuleCall_1_0() {
            return cOperandExpressionLevel7ParserRuleCall_1_0;
        }
    }

    public class IfExpressionElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.IfExpression");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Keyword cIfKeyword_0 = (Keyword) cGroup.eContents().get(0);

        private final Assignment cConditionAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cConditionExpressionParserRuleCall_1_0 = (RuleCall) cConditionAssignment_1
                .eContents().get(0);

        private final Keyword cThenKeyword_2 = (Keyword) cGroup.eContents().get(2);

        private final Assignment cThenClauseAssignment_3 = (Assignment) cGroup.eContents().get(3);

        private final RuleCall cThenClauseExpressionParserRuleCall_3_0 = (RuleCall) cThenClauseAssignment_3
                .eContents().get(0);

        private final Group cGroup_4 = (Group) cGroup.eContents().get(4);

        private final Keyword cElseKeyword_4_0 = (Keyword) cGroup_4.eContents().get(0);

        private final Assignment cElseClauseAssignment_4_1 = (Assignment) cGroup_4.eContents()
                .get(1);

        private final RuleCall cElseClauseExpressionParserRuleCall_4_1_0 = (RuleCall) cElseClauseAssignment_4_1
                .eContents().get(0);

        private final Keyword cFiKeyword_5 = (Keyword) cGroup.eContents().get(5);

        //IfExpression:
        //	'if' condition=Expression 'then' thenClause=Expression ('else' elseClause=Expression)? 'fi';
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //'if' condition=Expression 'then' thenClause=Expression ('else' elseClause=Expression)? 'fi'
        public Group getGroup() {
            return cGroup;
        }

        //'if'
        public Keyword getIfKeyword_0() {
            return cIfKeyword_0;
        }

        //condition=Expression
        public Assignment getConditionAssignment_1() {
            return cConditionAssignment_1;
        }

        //Expression
        public RuleCall getConditionExpressionParserRuleCall_1_0() {
            return cConditionExpressionParserRuleCall_1_0;
        }

        //'then'
        public Keyword getThenKeyword_2() {
            return cThenKeyword_2;
        }

        //thenClause=Expression
        public Assignment getThenClauseAssignment_3() {
            return cThenClauseAssignment_3;
        }

        //Expression
        public RuleCall getThenClauseExpressionParserRuleCall_3_0() {
            return cThenClauseExpressionParserRuleCall_3_0;
        }

        //('else' elseClause=Expression)?
        public Group getGroup_4() {
            return cGroup_4;
        }

        //'else'
        public Keyword getElseKeyword_4_0() {
            return cElseKeyword_4_0;
        }

        //elseClause=Expression
        public Assignment getElseClauseAssignment_4_1() {
            return cElseClauseAssignment_4_1;
        }

        //Expression
        public RuleCall getElseClauseExpressionParserRuleCall_4_1_0() {
            return cElseClauseExpressionParserRuleCall_4_1_0;
        }

        //'fi'
        public Keyword getFiKeyword_5() {
            return cFiKeyword_5;
        }
    }

    public class WhileExpressionElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.WhileExpression");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Keyword cWhileKeyword_0 = (Keyword) cGroup.eContents().get(0);

        private final Assignment cConditionAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cConditionExpressionParserRuleCall_1_0 = (RuleCall) cConditionAssignment_1
                .eContents().get(0);

        private final Keyword cDoKeyword_2 = (Keyword) cGroup.eContents().get(2);

        private final Assignment cBodyAssignment_3 = (Assignment) cGroup.eContents().get(3);

        private final RuleCall cBodyExpressionParserRuleCall_3_0 = (RuleCall) cBodyAssignment_3
                .eContents().get(0);

        private final Keyword cOdKeyword_4 = (Keyword) cGroup.eContents().get(4);

        //WhileExpression:
        //	'while' condition=Expression 'do' body=Expression 'od';
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //'while' condition=Expression 'do' body=Expression 'od'
        public Group getGroup() {
            return cGroup;
        }

        //'while'
        public Keyword getWhileKeyword_0() {
            return cWhileKeyword_0;
        }

        //condition=Expression
        public Assignment getConditionAssignment_1() {
            return cConditionAssignment_1;
        }

        //Expression
        public RuleCall getConditionExpressionParserRuleCall_1_0() {
            return cConditionExpressionParserRuleCall_1_0;
        }

        //'do'
        public Keyword getDoKeyword_2() {
            return cDoKeyword_2;
        }

        //body=Expression
        public Assignment getBodyAssignment_3() {
            return cBodyAssignment_3;
        }

        //Expression
        public RuleCall getBodyExpressionParserRuleCall_3_0() {
            return cBodyExpressionParserRuleCall_3_0;
        }

        //'od'
        public Keyword getOdKeyword_4() {
            return cOdKeyword_4;
        }
    }

    public class SwitchExpressionElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.SwitchExpression");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Keyword cSwitchKeyword_0 = (Keyword) cGroup.eContents().get(0);

        private final Assignment cExpressionAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cExpressionExpressionParserRuleCall_1_0 = (RuleCall) cExpressionAssignment_1
                .eContents().get(0);

        private final Keyword cDoKeyword_2 = (Keyword) cGroup.eContents().get(2);

        private final Alternatives cAlternatives_3 = (Alternatives) cGroup.eContents().get(3);

        private final Group cGroup_3_0 = (Group) cAlternatives_3.eContents().get(0);

        private final Assignment cCasesAssignment_3_0_0 = (Assignment) cGroup_3_0.eContents()
                .get(0);

        private final RuleCall cCasesSwitchExpressionCaseParserRuleCall_3_0_0_0 = (RuleCall) cCasesAssignment_3_0_0
                .eContents().get(0);

        private final Group cGroup_3_0_1 = (Group) cGroup_3_0.eContents().get(1);

        private final Keyword cDefaultKeyword_3_0_1_0 = (Keyword) cGroup_3_0_1.eContents().get(0);

        private final Assignment cDefaultBodyAssignment_3_0_1_1 = (Assignment) cGroup_3_0_1
                .eContents().get(1);

        private final RuleCall cDefaultBodyExpressionParserRuleCall_3_0_1_1_0 = (RuleCall) cDefaultBodyAssignment_3_0_1_1
                .eContents().get(0);

        private final Group cGroup_3_1 = (Group) cAlternatives_3.eContents().get(1);

        private final Keyword cDefaultKeyword_3_1_0 = (Keyword) cGroup_3_1.eContents().get(0);

        private final Assignment cDefaultBodyAssignment_3_1_1 = (Assignment) cGroup_3_1.eContents()
                .get(1);

        private final RuleCall cDefaultBodyExpressionParserRuleCall_3_1_1_0 = (RuleCall) cDefaultBodyAssignment_3_1_1
                .eContents().get(0);

        private final Keyword cOdKeyword_4 = (Keyword) cGroup.eContents().get(4);

        //SwitchExpression:
        //	'switch' expression=Expression
        //	'do' (cases+=SwitchExpressionCase+ ('default' defaultBody=Expression)?
        //	|
        //	'default' defaultBody=Expression)
        //	'od';
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //'switch' expression=Expression 'do' (cases+=SwitchExpressionCase+ ('default' defaultBody=Expression)? | 'default'
        //defaultBody=Expression) 'od'
        public Group getGroup() {
            return cGroup;
        }

        //'switch'
        public Keyword getSwitchKeyword_0() {
            return cSwitchKeyword_0;
        }

        //expression=Expression
        public Assignment getExpressionAssignment_1() {
            return cExpressionAssignment_1;
        }

        //Expression
        public RuleCall getExpressionExpressionParserRuleCall_1_0() {
            return cExpressionExpressionParserRuleCall_1_0;
        }

        //'do'
        public Keyword getDoKeyword_2() {
            return cDoKeyword_2;
        }

        //(cases+=SwitchExpressionCase+ ('default' defaultBody=Expression)? | 'default' defaultBody=Expression)
        public Alternatives getAlternatives_3() {
            return cAlternatives_3;
        }

        //cases+=SwitchExpressionCase+ ('default' defaultBody=Expression)?
        public Group getGroup_3_0() {
            return cGroup_3_0;
        }

        //cases+=SwitchExpressionCase+
        public Assignment getCasesAssignment_3_0_0() {
            return cCasesAssignment_3_0_0;
        }

        //SwitchExpressionCase
        public RuleCall getCasesSwitchExpressionCaseParserRuleCall_3_0_0_0() {
            return cCasesSwitchExpressionCaseParserRuleCall_3_0_0_0;
        }

        //('default' defaultBody=Expression)?
        public Group getGroup_3_0_1() {
            return cGroup_3_0_1;
        }

        //'default'
        public Keyword getDefaultKeyword_3_0_1_0() {
            return cDefaultKeyword_3_0_1_0;
        }

        //defaultBody=Expression
        public Assignment getDefaultBodyAssignment_3_0_1_1() {
            return cDefaultBodyAssignment_3_0_1_1;
        }

        //Expression
        public RuleCall getDefaultBodyExpressionParserRuleCall_3_0_1_1_0() {
            return cDefaultBodyExpressionParserRuleCall_3_0_1_1_0;
        }

        //'default' defaultBody=Expression
        public Group getGroup_3_1() {
            return cGroup_3_1;
        }

        //'default'
        public Keyword getDefaultKeyword_3_1_0() {
            return cDefaultKeyword_3_1_0;
        }

        //defaultBody=Expression
        public Assignment getDefaultBodyAssignment_3_1_1() {
            return cDefaultBodyAssignment_3_1_1;
        }

        //Expression
        public RuleCall getDefaultBodyExpressionParserRuleCall_3_1_1_0() {
            return cDefaultBodyExpressionParserRuleCall_3_1_1_0;
        }

        //'od'
        public Keyword getOdKeyword_4() {
            return cOdKeyword_4;
        }
    }

    public class SwitchExpressionCaseElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.SwitchExpressionCase");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Keyword cCaseKeyword_0 = (Keyword) cGroup.eContents().get(0);

        private final Assignment cValueAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cValueExpressionParserRuleCall_1_0 = (RuleCall) cValueAssignment_1
                .eContents().get(0);

        private final Keyword cThenKeyword_2 = (Keyword) cGroup.eContents().get(2);

        private final Assignment cBodyAssignment_3 = (Assignment) cGroup.eContents().get(3);

        private final RuleCall cBodyExpressionParserRuleCall_3_0 = (RuleCall) cBodyAssignment_3
                .eContents().get(0);

        //SwitchExpressionCase:
        //	'case' value=Expression 'then' body=Expression;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //'case' value=Expression 'then' body=Expression
        public Group getGroup() {
            return cGroup;
        }

        //'case'
        public Keyword getCaseKeyword_0() {
            return cCaseKeyword_0;
        }

        //value=Expression
        public Assignment getValueAssignment_1() {
            return cValueAssignment_1;
        }

        //Expression
        public RuleCall getValueExpressionParserRuleCall_1_0() {
            return cValueExpressionParserRuleCall_1_0;
        }

        //'then'
        public Keyword getThenKeyword_2() {
            return cThenKeyword_2;
        }

        //body=Expression
        public Assignment getBodyAssignment_3() {
            return cBodyAssignment_3;
        }

        //Expression
        public RuleCall getBodyExpressionParserRuleCall_3_0() {
            return cBodyExpressionParserRuleCall_3_0;
        }
    }

    public class ExpressionLevel7Elements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.ExpressionLevel7");

        private final Alternatives cAlternatives = (Alternatives) rule.eContents().get(1);

        private final RuleCall cCurrentTimeExpressionParserRuleCall_0 = (RuleCall) cAlternatives
                .eContents().get(0);

        private final RuleCall cSelfExpressionParserRuleCall_1 = (RuleCall) cAlternatives
                .eContents().get(1);

        private final RuleCall cExpressionConstantParserRuleCall_2 = (RuleCall) cAlternatives
                .eContents().get(2);

        private final RuleCall cNewExpressionParserRuleCall_3 = (RuleCall) cAlternatives.eContents()
                .get(3);

        private final RuleCall cVariableExpressionParserRuleCall_4 = (RuleCall) cAlternatives
                .eContents().get(4);

        private final RuleCall cExpressionSequenceRoundBracketParserRuleCall_5 = (RuleCall) cAlternatives
                .eContents().get(5);

        //ExpressionLevel7 Expression:
        //	CurrentTimeExpression | SelfExpression | ExpressionConstant | NewExpression | VariableExpression |
        //	ExpressionSequenceRoundBracket;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //CurrentTimeExpression | SelfExpression | ExpressionConstant | NewExpression | VariableExpression |
        //ExpressionSequenceRoundBracket
        public Alternatives getAlternatives() {
            return cAlternatives;
        }

        //CurrentTimeExpression
        public RuleCall getCurrentTimeExpressionParserRuleCall_0() {
            return cCurrentTimeExpressionParserRuleCall_0;
        }

        //SelfExpression
        public RuleCall getSelfExpressionParserRuleCall_1() {
            return cSelfExpressionParserRuleCall_1;
        }

        //ExpressionConstant
        public RuleCall getExpressionConstantParserRuleCall_2() {
            return cExpressionConstantParserRuleCall_2;
        }

        //NewExpression
        public RuleCall getNewExpressionParserRuleCall_3() {
            return cNewExpressionParserRuleCall_3;
        }

        //VariableExpression
        public RuleCall getVariableExpressionParserRuleCall_4() {
            return cVariableExpressionParserRuleCall_4;
        }

        //ExpressionSequenceRoundBracket
        public RuleCall getExpressionSequenceRoundBracketParserRuleCall_5() {
            return cExpressionSequenceRoundBracketParserRuleCall_5;
        }
    }

    public class CurrentTimeExpressionElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.CurrentTimeExpression");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Action cCurrentTimeExpressionAction_0 = (Action) cGroup.eContents().get(0);

        private final Keyword cCurrentTimeKeyword_1 = (Keyword) cGroup.eContents().get(1);

        //CurrentTimeExpression:
        //	{CurrentTimeExpression} 'currentTime';
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //{CurrentTimeExpression} 'currentTime'
        public Group getGroup() {
            return cGroup;
        }

        //{CurrentTimeExpression}
        public Action getCurrentTimeExpressionAction_0() {
            return cCurrentTimeExpressionAction_0;
        }

        //'currentTime'
        public Keyword getCurrentTimeKeyword_1() {
            return cCurrentTimeKeyword_1;
        }
    }

    public class SelfExpressionElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.SelfExpression");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Action cSelfExpressionAction_0 = (Action) cGroup.eContents().get(0);

        private final Keyword cSelfKeyword_1 = (Keyword) cGroup.eContents().get(1);

        //SelfExpression:
        //	{SelfExpression} 'self';
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //{SelfExpression} 'self'
        public Group getGroup() {
            return cGroup;
        }

        //{SelfExpression}
        public Action getSelfExpressionAction_0() {
            return cSelfExpressionAction_0;
        }

        //'self'
        public Keyword getSelfKeyword_1() {
            return cSelfKeyword_1;
        }
    }

    public class ExpressionConstantElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.ExpressionConstant");

        private final Alternatives cAlternatives = (Alternatives) rule.eContents().get(1);

        private final RuleCall cBooleanConstantParserRuleCall_0 = (RuleCall) cAlternatives
                .eContents().get(0);

        private final RuleCall cCharacterConstantParserRuleCall_1 = (RuleCall) cAlternatives
                .eContents().get(1);

        private final RuleCall cFloatConstantParserRuleCall_2 = (RuleCall) cAlternatives.eContents()
                .get(2);

        private final RuleCall cIntegerConstantParserRuleCall_3 = (RuleCall) cAlternatives
                .eContents().get(3);

        private final RuleCall cNilConstantParserRuleCall_4 = (RuleCall) cAlternatives.eContents()
                .get(4);

        private final RuleCall cRealConstantParserRuleCall_5 = (RuleCall) cAlternatives.eContents()
                .get(5);

        private final RuleCall cStringConstantParserRuleCall_6 = (RuleCall) cAlternatives
                .eContents().get(6);

        private final RuleCall cEnvironmentConstantParserRuleCall_7 = (RuleCall) cAlternatives
                .eContents().get(7);

        //ExpressionConstant Expression:
        //	BooleanConstant | CharacterConstant | FloatConstant | IntegerConstant | NilConstant | RealConstant | StringConstant |
        //	EnvironmentConstant;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //BooleanConstant | CharacterConstant | FloatConstant | IntegerConstant | NilConstant | RealConstant | StringConstant |
        //EnvironmentConstant
        public Alternatives getAlternatives() {
            return cAlternatives;
        }

        //BooleanConstant
        public RuleCall getBooleanConstantParserRuleCall_0() {
            return cBooleanConstantParserRuleCall_0;
        }

        //CharacterConstant
        public RuleCall getCharacterConstantParserRuleCall_1() {
            return cCharacterConstantParserRuleCall_1;
        }

        //FloatConstant
        public RuleCall getFloatConstantParserRuleCall_2() {
            return cFloatConstantParserRuleCall_2;
        }

        //IntegerConstant
        public RuleCall getIntegerConstantParserRuleCall_3() {
            return cIntegerConstantParserRuleCall_3;
        }

        //NilConstant
        public RuleCall getNilConstantParserRuleCall_4() {
            return cNilConstantParserRuleCall_4;
        }

        //RealConstant
        public RuleCall getRealConstantParserRuleCall_5() {
            return cRealConstantParserRuleCall_5;
        }

        //StringConstant
        public RuleCall getStringConstantParserRuleCall_6() {
            return cStringConstantParserRuleCall_6;
        }

        //EnvironmentConstant
        public RuleCall getEnvironmentConstantParserRuleCall_7() {
            return cEnvironmentConstantParserRuleCall_7;
        }
    }

    public class BooleanConstantElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.BooleanConstant");

        private final Alternatives cAlternatives = (Alternatives) rule.eContents().get(1);

        private final Assignment cValueAssignment_0 = (Assignment) cAlternatives.eContents().get(0);

        private final Keyword cValueTrueKeyword_0_0 = (Keyword) cValueAssignment_0.eContents()
                .get(0);

        private final Assignment cValueAssignment_1 = (Assignment) cAlternatives.eContents().get(1);

        private final Keyword cValueFalseKeyword_1_0 = (Keyword) cValueAssignment_1.eContents()
                .get(0);

        //BooleanConstant:
        //	value='true' | value='false';
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //value='true' | value='false'
        public Alternatives getAlternatives() {
            return cAlternatives;
        }

        //value='true'
        public Assignment getValueAssignment_0() {
            return cValueAssignment_0;
        }

        //'true'
        public Keyword getValueTrueKeyword_0_0() {
            return cValueTrueKeyword_0_0;
        }

        //value='false'
        public Assignment getValueAssignment_1() {
            return cValueAssignment_1;
        }

        //'false'
        public Keyword getValueFalseKeyword_1_0() {
            return cValueFalseKeyword_1_0;
        }
    }

    public class CharacterConstantElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.CharacterConstant");

        private final Assignment cValueAssignment = (Assignment) rule.eContents().get(1);

        private final RuleCall cValueCHARACTERTerminalRuleCall_0 = (RuleCall) cValueAssignment
                .eContents().get(0);

        //CharacterConstant:
        //	value=CHARACTER;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //value=CHARACTER
        public Assignment getValueAssignment() {
            return cValueAssignment;
        }

        //CHARACTER
        public RuleCall getValueCHARACTERTerminalRuleCall_0() {
            return cValueCHARACTERTerminalRuleCall_0;
        }
    }

    public class FloatConstantElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.FloatConstant");

        private final Assignment cValueAssignment = (Assignment) rule.eContents().get(1);

        private final RuleCall cValueFLOATParserRuleCall_0 = (RuleCall) cValueAssignment.eContents()
                .get(0);

        //FloatConstant:
        //	value=FLOAT;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //value=FLOAT
        public Assignment getValueAssignment() {
            return cValueAssignment;
        }

        //FLOAT
        public RuleCall getValueFLOATParserRuleCall_0() {
            return cValueFLOATParserRuleCall_0;
        }
    }

    public class IntegerConstantElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.IntegerConstant");

        private final Assignment cValueAssignment = (Assignment) rule.eContents().get(1);

        private final RuleCall cValueINTEGERParserRuleCall_0 = (RuleCall) cValueAssignment
                .eContents().get(0);

        //IntegerConstant:
        //	value=INTEGER;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //value=INTEGER
        public Assignment getValueAssignment() {
            return cValueAssignment;
        }

        //INTEGER
        public RuleCall getValueINTEGERParserRuleCall_0() {
            return cValueINTEGERParserRuleCall_0;
        }
    }

    public class NilConstantElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.NilConstant");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Action cNilConstantAction_0 = (Action) cGroup.eContents().get(0);

        private final Keyword cNilKeyword_1 = (Keyword) cGroup.eContents().get(1);

        //NilConstant:
        //	{NilConstant} 'nil';
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //{NilConstant} 'nil'
        public Group getGroup() {
            return cGroup;
        }

        //{NilConstant}
        public Action getNilConstantAction_0() {
            return cNilConstantAction_0;
        }

        //'nil'
        public Keyword getNilKeyword_1() {
            return cNilKeyword_1;
        }
    }

    public class RealConstantElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.RealConstant");

        private final Assignment cValueAssignment = (Assignment) rule.eContents().get(1);

        private final RuleCall cValueREALParserRuleCall_0 = (RuleCall) cValueAssignment.eContents()
                .get(0);

        //RealConstant:
        //	value=REAL;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //value=REAL
        public Assignment getValueAssignment() {
            return cValueAssignment;
        }

        //REAL
        public RuleCall getValueREALParserRuleCall_0() {
            return cValueREALParserRuleCall_0;
        }
    }

    public class StringConstantElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.StringConstant");

        private final Assignment cValueAssignment = (Assignment) rule.eContents().get(1);

        private final RuleCall cValuePOOSL_STRINGTerminalRuleCall_0 = (RuleCall) cValueAssignment
                .eContents().get(0);

        //StringConstant:
        //	value=POOSL_STRING;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //value=POOSL_STRING
        public Assignment getValueAssignment() {
            return cValueAssignment;
        }

        //POOSL_STRING
        public RuleCall getValuePOOSL_STRINGTerminalRuleCall_0() {
            return cValuePOOSL_STRINGTerminalRuleCall_0;
        }
    }

    public class EnvironmentConstantElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.EnvironmentConstant");

        private final Assignment cValueAssignment = (Assignment) rule.eContents().get(1);

        private final RuleCall cValueENVIRONMENT_VARIABLE_NAMETerminalRuleCall_0 = (RuleCall) cValueAssignment
                .eContents().get(0);

        //EnvironmentConstant:
        //	value=ENVIRONMENT_VARIABLE_NAME;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //value=ENVIRONMENT_VARIABLE_NAME
        public Assignment getValueAssignment() {
            return cValueAssignment;
        }

        //ENVIRONMENT_VARIABLE_NAME
        public RuleCall getValueENVIRONMENT_VARIABLE_NAMETerminalRuleCall_0() {
            return cValueENVIRONMENT_VARIABLE_NAMETerminalRuleCall_0;
        }
    }

    public class OutputVariableElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.OutputVariable");

        private final Assignment cVariableAssignment = (Assignment) rule.eContents().get(1);

        private final RuleCall cVariableIDENTIFIERParserRuleCall_0 = (RuleCall) cVariableAssignment
                .eContents().get(0);

        //OutputVariable:
        //	variable=IDENTIFIER;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //variable=IDENTIFIER
        public Assignment getVariableAssignment() {
            return cVariableAssignment;
        }

        //IDENTIFIER
        public RuleCall getVariableIDENTIFIERParserRuleCall_0() {
            return cVariableIDENTIFIERParserRuleCall_0;
        }
    }

    public class VariableExpressionElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.VariableExpression");

        private final Assignment cVariableAssignment = (Assignment) rule.eContents().get(1);

        private final RuleCall cVariableIDENTIFIERParserRuleCall_0 = (RuleCall) cVariableAssignment
                .eContents().get(0);

        //VariableExpression:
        //	variable=IDENTIFIER;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //variable=IDENTIFIER
        public Assignment getVariableAssignment() {
            return cVariableAssignment;
        }

        //IDENTIFIER
        public RuleCall getVariableIDENTIFIERParserRuleCall_0() {
            return cVariableIDENTIFIERParserRuleCall_0;
        }
    }

    public class NewExpressionElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.NewExpression");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Keyword cNewKeyword_0 = (Keyword) cGroup.eContents().get(0);

        private final Keyword cLeftParenthesisKeyword_1 = (Keyword) cGroup.eContents().get(1);

        private final Assignment cDataClassAssignment_2 = (Assignment) cGroup.eContents().get(2);

        private final RuleCall cDataClassIDENTIFIERParserRuleCall_2_0 = (RuleCall) cDataClassAssignment_2
                .eContents().get(0);

        private final Keyword cRightParenthesisKeyword_3 = (Keyword) cGroup.eContents().get(3);

        //NewExpression:
        //	'new' '(' dataClass=IDENTIFIER ')';
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //'new' '(' dataClass=IDENTIFIER ')'
        public Group getGroup() {
            return cGroup;
        }

        //'new'
        public Keyword getNewKeyword_0() {
            return cNewKeyword_0;
        }

        //'('
        public Keyword getLeftParenthesisKeyword_1() {
            return cLeftParenthesisKeyword_1;
        }

        //dataClass=IDENTIFIER
        public Assignment getDataClassAssignment_2() {
            return cDataClassAssignment_2;
        }

        //IDENTIFIER
        public RuleCall getDataClassIDENTIFIERParserRuleCall_2_0() {
            return cDataClassIDENTIFIERParserRuleCall_2_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_3() {
            return cRightParenthesisKeyword_3;
        }
    }

    public class ExpressionSequenceRoundBracketElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.ExpressionSequenceRoundBracket");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Keyword cLeftParenthesisKeyword_0 = (Keyword) cGroup.eContents().get(0);

        private final Assignment cExpressionsAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cExpressionsExpressionSingleParserRuleCall_1_0 = (RuleCall) cExpressionsAssignment_1
                .eContents().get(0);

        private final Group cGroup_2 = (Group) cGroup.eContents().get(2);

        private final Keyword cSemicolonKeyword_2_0 = (Keyword) cGroup_2.eContents().get(0);

        private final Assignment cExpressionsAssignment_2_1 = (Assignment) cGroup_2.eContents()
                .get(1);

        private final RuleCall cExpressionsExpressionSingleParserRuleCall_2_1_0 = (RuleCall) cExpressionsAssignment_2_1
                .eContents().get(0);

        private final Keyword cRightParenthesisKeyword_3 = (Keyword) cGroup.eContents().get(3);

        //ExpressionSequenceRoundBracket:
        //	'(' expressions+=ExpressionSingle (';' expressions+=ExpressionSingle)* ')';
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //'(' expressions+=ExpressionSingle (';' expressions+=ExpressionSingle)* ')'
        public Group getGroup() {
            return cGroup;
        }

        //'('
        public Keyword getLeftParenthesisKeyword_0() {
            return cLeftParenthesisKeyword_0;
        }

        //expressions+=ExpressionSingle
        public Assignment getExpressionsAssignment_1() {
            return cExpressionsAssignment_1;
        }

        //ExpressionSingle
        public RuleCall getExpressionsExpressionSingleParserRuleCall_1_0() {
            return cExpressionsExpressionSingleParserRuleCall_1_0;
        }

        //(';' expressions+=ExpressionSingle)*
        public Group getGroup_2() {
            return cGroup_2;
        }

        //';'
        public Keyword getSemicolonKeyword_2_0() {
            return cSemicolonKeyword_2_0;
        }

        //expressions+=ExpressionSingle
        public Assignment getExpressionsAssignment_2_1() {
            return cExpressionsAssignment_2_1;
        }

        //ExpressionSingle
        public RuleCall getExpressionsExpressionSingleParserRuleCall_2_1_0() {
            return cExpressionsExpressionSingleParserRuleCall_2_1_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_3() {
            return cRightParenthesisKeyword_3;
        }
    }

    public class IDStartWithinStatementExpressionSingleElements
            extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.IDStartWithinStatementExpressionSingle");

        private final RuleCall cIDStartWithinStatementExpressionLevel1ParserRuleCall = (RuleCall) rule
                .eContents().get(1);

        //// --- Restricted expressions for use in statement, split by first token -------
        //IDStartWithinStatementExpressionSingle Expression:
        //	IDStartWithinStatementExpressionLevel1;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //IDStartWithinStatementExpressionLevel1
        public RuleCall getIDStartWithinStatementExpressionLevel1ParserRuleCall() {
            return cIDStartWithinStatementExpressionLevel1ParserRuleCall;
        }
    }

    public class IDStartWithinStatementExpressionLevel1Elements
            extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.IDStartWithinStatementExpressionLevel1");

        private final Alternatives cAlternatives = (Alternatives) rule.eContents().get(1);

        private final RuleCall cIDStartWithinStatementExpressionLevel2ParserRuleCall_0 = (RuleCall) cAlternatives
                .eContents().get(0);

        private final Group cGroup_1 = (Group) cAlternatives.eContents().get(1);

        private final Action cAssignmentExpressionAction_1_0 = (Action) cGroup_1.eContents().get(0);

        private final Assignment cVariableAssignment_1_1 = (Assignment) cGroup_1.eContents().get(1);

        private final RuleCall cVariableIDENTIFIERParserRuleCall_1_1_0 = (RuleCall) cVariableAssignment_1_1
                .eContents().get(0);

        private final Keyword cColonEqualsSignKeyword_1_2 = (Keyword) cGroup_1.eContents().get(2);

        private final Assignment cExpressionAssignment_1_3 = (Assignment) cGroup_1.eContents()
                .get(3);

        private final RuleCall cExpressionExpressionLevel1ParserRuleCall_1_3_0 = (RuleCall) cExpressionAssignment_1_3
                .eContents().get(0);

        //IDStartWithinStatementExpressionLevel1 Expression:
        //	IDStartWithinStatementExpressionLevel2
        //	| {AssignmentExpression} variable=IDENTIFIER ':=' expression=ExpressionLevel1;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //IDStartWithinStatementExpressionLevel2 | {AssignmentExpression} variable=IDENTIFIER ':=' expression=ExpressionLevel1
        public Alternatives getAlternatives() {
            return cAlternatives;
        }

        //IDStartWithinStatementExpressionLevel2
        public RuleCall getIDStartWithinStatementExpressionLevel2ParserRuleCall_0() {
            return cIDStartWithinStatementExpressionLevel2ParserRuleCall_0;
        }

        //{AssignmentExpression} variable=IDENTIFIER ':=' expression=ExpressionLevel1
        public Group getGroup_1() {
            return cGroup_1;
        }

        //{AssignmentExpression}
        public Action getAssignmentExpressionAction_1_0() {
            return cAssignmentExpressionAction_1_0;
        }

        //variable=IDENTIFIER
        public Assignment getVariableAssignment_1_1() {
            return cVariableAssignment_1_1;
        }

        //IDENTIFIER
        public RuleCall getVariableIDENTIFIERParserRuleCall_1_1_0() {
            return cVariableIDENTIFIERParserRuleCall_1_1_0;
        }

        //':='
        public Keyword getColonEqualsSignKeyword_1_2() {
            return cColonEqualsSignKeyword_1_2;
        }

        //expression=ExpressionLevel1
        public Assignment getExpressionAssignment_1_3() {
            return cExpressionAssignment_1_3;
        }

        //ExpressionLevel1
        public RuleCall getExpressionExpressionLevel1ParserRuleCall_1_3_0() {
            return cExpressionExpressionLevel1ParserRuleCall_1_3_0;
        }
    }

    public class IDStartWithinStatementExpressionLevel2Elements
            extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.IDStartWithinStatementExpressionLevel2");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final RuleCall cIDStartWithinStatementExpressionLevel3ParserRuleCall_0 = (RuleCall) cGroup
                .eContents().get(0);

        private final Group cGroup_1 = (Group) cGroup.eContents().get(1);

        private final Action cBinaryOperatorExpressionLeftOperandAction_1_0 = (Action) cGroup_1
                .eContents().get(0);

        private final Assignment cNameAssignment_1_1 = (Assignment) cGroup_1.eContents().get(1);

        private final RuleCall cNameBinaryOperatorLevel2EnumRuleCall_1_1_0 = (RuleCall) cNameAssignment_1_1
                .eContents().get(0);

        private final Assignment cRightOperandAssignment_1_2 = (Assignment) cGroup_1.eContents()
                .get(2);

        private final RuleCall cRightOperandExpressionLevel3ParserRuleCall_1_2_0 = (RuleCall) cRightOperandAssignment_1_2
                .eContents().get(0);

        //IDStartWithinStatementExpressionLevel2 Expression:
        //	IDStartWithinStatementExpressionLevel3 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel2
        //	rightOperand=ExpressionLevel3)*;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //// Left associativity
        //IDStartWithinStatementExpressionLevel3 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel2
        //rightOperand=ExpressionLevel3)*
        public Group getGroup() {
            return cGroup;
        }

        //// Left associativity
        //IDStartWithinStatementExpressionLevel3
        public RuleCall getIDStartWithinStatementExpressionLevel3ParserRuleCall_0() {
            return cIDStartWithinStatementExpressionLevel3ParserRuleCall_0;
        }

        //({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel2 rightOperand=ExpressionLevel3)*
        public Group getGroup_1() {
            return cGroup_1;
        }

        //{BinaryOperatorExpression.leftOperand=current}
        public Action getBinaryOperatorExpressionLeftOperandAction_1_0() {
            return cBinaryOperatorExpressionLeftOperandAction_1_0;
        }

        //name=BinaryOperatorLevel2
        public Assignment getNameAssignment_1_1() {
            return cNameAssignment_1_1;
        }

        //BinaryOperatorLevel2
        public RuleCall getNameBinaryOperatorLevel2EnumRuleCall_1_1_0() {
            return cNameBinaryOperatorLevel2EnumRuleCall_1_1_0;
        }

        //rightOperand=ExpressionLevel3
        public Assignment getRightOperandAssignment_1_2() {
            return cRightOperandAssignment_1_2;
        }

        //ExpressionLevel3
        public RuleCall getRightOperandExpressionLevel3ParserRuleCall_1_2_0() {
            return cRightOperandExpressionLevel3ParserRuleCall_1_2_0;
        }
    }

    public class IDStartWithinStatementExpressionLevel3Elements
            extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.IDStartWithinStatementExpressionLevel3");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final RuleCall cIDStartWithinStatementExpressionLevel4ParserRuleCall_0 = (RuleCall) cGroup
                .eContents().get(0);

        private final Group cGroup_1 = (Group) cGroup.eContents().get(1);

        private final Action cBinaryOperatorExpressionLeftOperandAction_1_0 = (Action) cGroup_1
                .eContents().get(0);

        private final Assignment cNameAssignment_1_1 = (Assignment) cGroup_1.eContents().get(1);

        private final RuleCall cNameBinaryOperatorLevel3EnumRuleCall_1_1_0 = (RuleCall) cNameAssignment_1_1
                .eContents().get(0);

        private final Assignment cRightOperandAssignment_1_2 = (Assignment) cGroup_1.eContents()
                .get(2);

        private final RuleCall cRightOperandExpressionLevel4ParserRuleCall_1_2_0 = (RuleCall) cRightOperandAssignment_1_2
                .eContents().get(0);

        //IDStartWithinStatementExpressionLevel3 Expression:
        //	IDStartWithinStatementExpressionLevel4 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel3
        //	rightOperand=ExpressionLevel4)*;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //// Left associativity
        //IDStartWithinStatementExpressionLevel4 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel3
        //rightOperand=ExpressionLevel4)*
        public Group getGroup() {
            return cGroup;
        }

        //// Left associativity
        //IDStartWithinStatementExpressionLevel4
        public RuleCall getIDStartWithinStatementExpressionLevel4ParserRuleCall_0() {
            return cIDStartWithinStatementExpressionLevel4ParserRuleCall_0;
        }

        //({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel3 rightOperand=ExpressionLevel4)*
        public Group getGroup_1() {
            return cGroup_1;
        }

        //{BinaryOperatorExpression.leftOperand=current}
        public Action getBinaryOperatorExpressionLeftOperandAction_1_0() {
            return cBinaryOperatorExpressionLeftOperandAction_1_0;
        }

        //name=BinaryOperatorLevel3
        public Assignment getNameAssignment_1_1() {
            return cNameAssignment_1_1;
        }

        //BinaryOperatorLevel3
        public RuleCall getNameBinaryOperatorLevel3EnumRuleCall_1_1_0() {
            return cNameBinaryOperatorLevel3EnumRuleCall_1_1_0;
        }

        //rightOperand=ExpressionLevel4
        public Assignment getRightOperandAssignment_1_2() {
            return cRightOperandAssignment_1_2;
        }

        //ExpressionLevel4
        public RuleCall getRightOperandExpressionLevel4ParserRuleCall_1_2_0() {
            return cRightOperandExpressionLevel4ParserRuleCall_1_2_0;
        }
    }

    public class IDStartWithinStatementExpressionLevel4Elements
            extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.IDStartWithinStatementExpressionLevel4");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final RuleCall cIDStartWithinStatementExpressionLevel5ParserRuleCall_0 = (RuleCall) cGroup
                .eContents().get(0);

        private final Group cGroup_1 = (Group) cGroup.eContents().get(1);

        private final Action cBinaryOperatorExpressionLeftOperandAction_1_0 = (Action) cGroup_1
                .eContents().get(0);

        private final Assignment cNameAssignment_1_1 = (Assignment) cGroup_1.eContents().get(1);

        private final RuleCall cNameBinaryOperatorLevel4EnumRuleCall_1_1_0 = (RuleCall) cNameAssignment_1_1
                .eContents().get(0);

        private final Assignment cRightOperandAssignment_1_2 = (Assignment) cGroup_1.eContents()
                .get(2);

        private final RuleCall cRightOperandExpressionLevel5ParserRuleCall_1_2_0 = (RuleCall) cRightOperandAssignment_1_2
                .eContents().get(0);

        //IDStartWithinStatementExpressionLevel4 Expression:
        //	IDStartWithinStatementExpressionLevel5 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel4
        //	rightOperand=ExpressionLevel5)*;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //// Left associativity
        //IDStartWithinStatementExpressionLevel5 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel4
        //rightOperand=ExpressionLevel5)*
        public Group getGroup() {
            return cGroup;
        }

        //// Left associativity
        //IDStartWithinStatementExpressionLevel5
        public RuleCall getIDStartWithinStatementExpressionLevel5ParserRuleCall_0() {
            return cIDStartWithinStatementExpressionLevel5ParserRuleCall_0;
        }

        //({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5)*
        public Group getGroup_1() {
            return cGroup_1;
        }

        //{BinaryOperatorExpression.leftOperand=current}
        public Action getBinaryOperatorExpressionLeftOperandAction_1_0() {
            return cBinaryOperatorExpressionLeftOperandAction_1_0;
        }

        //name=BinaryOperatorLevel4
        public Assignment getNameAssignment_1_1() {
            return cNameAssignment_1_1;
        }

        //BinaryOperatorLevel4
        public RuleCall getNameBinaryOperatorLevel4EnumRuleCall_1_1_0() {
            return cNameBinaryOperatorLevel4EnumRuleCall_1_1_0;
        }

        //rightOperand=ExpressionLevel5
        public Assignment getRightOperandAssignment_1_2() {
            return cRightOperandAssignment_1_2;
        }

        //ExpressionLevel5
        public RuleCall getRightOperandExpressionLevel5ParserRuleCall_1_2_0() {
            return cRightOperandExpressionLevel5ParserRuleCall_1_2_0;
        }
    }

    public class IDStartWithinStatementExpressionLevel5Elements
            extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.IDStartWithinStatementExpressionLevel5");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final RuleCall cVariableExpressionParserRuleCall_0 = (RuleCall) cGroup.eContents()
                .get(0);

        private final Group cGroup_1 = (Group) cGroup.eContents().get(1);

        private final Action cDataMethodCallExpressionTargetAction_1_0 = (Action) cGroup_1
                .eContents().get(0);

        private final Assignment cOnSuperClassAssignment_1_1 = (Assignment) cGroup_1.eContents()
                .get(1);

        private final Keyword cOnSuperClassCircumflexAccentKeyword_1_1_0 = (Keyword) cOnSuperClassAssignment_1_1
                .eContents().get(0);

        private final Assignment cNameAssignment_1_2 = (Assignment) cGroup_1.eContents().get(2);

        private final RuleCall cNameIDENTIFIERParserRuleCall_1_2_0 = (RuleCall) cNameAssignment_1_2
                .eContents().get(0);

        private final Group cGroup_1_3 = (Group) cGroup_1.eContents().get(3);

        private final Keyword cLeftParenthesisKeyword_1_3_0 = (Keyword) cGroup_1_3.eContents()
                .get(0);

        private final Group cGroup_1_3_1 = (Group) cGroup_1_3.eContents().get(1);

        private final Assignment cArgumentsAssignment_1_3_1_0 = (Assignment) cGroup_1_3_1
                .eContents().get(0);

        private final RuleCall cArgumentsExpressionParserRuleCall_1_3_1_0_0 = (RuleCall) cArgumentsAssignment_1_3_1_0
                .eContents().get(0);

        private final Group cGroup_1_3_1_1 = (Group) cGroup_1_3_1.eContents().get(1);

        private final Keyword cCommaKeyword_1_3_1_1_0 = (Keyword) cGroup_1_3_1_1.eContents().get(0);

        private final Assignment cArgumentsAssignment_1_3_1_1_1 = (Assignment) cGroup_1_3_1_1
                .eContents().get(1);

        private final RuleCall cArgumentsExpressionParserRuleCall_1_3_1_1_1_0 = (RuleCall) cArgumentsAssignment_1_3_1_1_1
                .eContents().get(0);

        private final Keyword cRightParenthesisKeyword_1_3_2 = (Keyword) cGroup_1_3.eContents()
                .get(2);

        //IDStartWithinStatementExpressionLevel5 Expression:
        //	VariableExpression ({DataMethodCallExpression.target=current} onSuperClass?='^'? name=IDENTIFIER ('('
        //	(arguments+=Expression (',' arguments+=Expression)*)? ')')?)*;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //VariableExpression ({DataMethodCallExpression.target=current} onSuperClass?='^'? name=IDENTIFIER ('('
        //(arguments+=Expression (',' arguments+=Expression)*)? ')')?)*
        public Group getGroup() {
            return cGroup;
        }

        //VariableExpression
        public RuleCall getVariableExpressionParserRuleCall_0() {
            return cVariableExpressionParserRuleCall_0;
        }

        //({DataMethodCallExpression.target=current} onSuperClass?='^'? name=IDENTIFIER ('(' (arguments+=Expression (','
        //arguments+=Expression)*)? ')')?)*
        public Group getGroup_1() {
            return cGroup_1;
        }

        //{DataMethodCallExpression.target=current}
        public Action getDataMethodCallExpressionTargetAction_1_0() {
            return cDataMethodCallExpressionTargetAction_1_0;
        }

        //onSuperClass?='^'?
        public Assignment getOnSuperClassAssignment_1_1() {
            return cOnSuperClassAssignment_1_1;
        }

        //'^'
        public Keyword getOnSuperClassCircumflexAccentKeyword_1_1_0() {
            return cOnSuperClassCircumflexAccentKeyword_1_1_0;
        }

        //name=IDENTIFIER
        public Assignment getNameAssignment_1_2() {
            return cNameAssignment_1_2;
        }

        //IDENTIFIER
        public RuleCall getNameIDENTIFIERParserRuleCall_1_2_0() {
            return cNameIDENTIFIERParserRuleCall_1_2_0;
        }

        //('(' (arguments+=Expression (',' arguments+=Expression)*)? ')')?
        public Group getGroup_1_3() {
            return cGroup_1_3;
        }

        //'('
        public Keyword getLeftParenthesisKeyword_1_3_0() {
            return cLeftParenthesisKeyword_1_3_0;
        }

        //(arguments+=Expression (',' arguments+=Expression)*)?
        public Group getGroup_1_3_1() {
            return cGroup_1_3_1;
        }

        //arguments+=Expression
        public Assignment getArgumentsAssignment_1_3_1_0() {
            return cArgumentsAssignment_1_3_1_0;
        }

        //Expression
        public RuleCall getArgumentsExpressionParserRuleCall_1_3_1_0_0() {
            return cArgumentsExpressionParserRuleCall_1_3_1_0_0;
        }

        //(',' arguments+=Expression)*
        public Group getGroup_1_3_1_1() {
            return cGroup_1_3_1_1;
        }

        //','
        public Keyword getCommaKeyword_1_3_1_1_0() {
            return cCommaKeyword_1_3_1_1_0;
        }

        //arguments+=Expression
        public Assignment getArgumentsAssignment_1_3_1_1_1() {
            return cArgumentsAssignment_1_3_1_1_1;
        }

        //Expression
        public RuleCall getArgumentsExpressionParserRuleCall_1_3_1_1_1_0() {
            return cArgumentsExpressionParserRuleCall_1_3_1_1_1_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_1_3_2() {
            return cRightParenthesisKeyword_1_3_2;
        }
    }

    public class NonIDStartWithinStatementExpressionSingleElements
            extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.NonIDStartWithinStatementExpressionSingle");

        private final RuleCall cNonIDStartWithinStatementExpressionLevel1ParserRuleCall = (RuleCall) rule
                .eContents().get(1);

        //NonIDStartWithinStatementExpressionSingle Expression:
        //	NonIDStartWithinStatementExpressionLevel1;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //NonIDStartWithinStatementExpressionLevel1
        public RuleCall getNonIDStartWithinStatementExpressionLevel1ParserRuleCall() {
            return cNonIDStartWithinStatementExpressionLevel1ParserRuleCall;
        }
    }

    public class NonIDStartWithinStatementExpressionLevel1Elements
            extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.NonIDStartWithinStatementExpressionLevel1");

        private final Alternatives cAlternatives = (Alternatives) rule.eContents().get(1);

        private final RuleCall cNonIDStartWithinStatementExpressionLevel2ParserRuleCall_0 = (RuleCall) cAlternatives
                .eContents().get(0);

        private final Group cGroup_1 = (Group) cAlternatives.eContents().get(1);

        private final Action cReturnExpressionAction_1_0 = (Action) cGroup_1.eContents().get(0);

        private final Keyword cReturnKeyword_1_1 = (Keyword) cGroup_1.eContents().get(1);

        private final Assignment cExpressionAssignment_1_2 = (Assignment) cGroup_1.eContents()
                .get(2);

        private final RuleCall cExpressionExpressionLevel1ParserRuleCall_1_2_0 = (RuleCall) cExpressionAssignment_1_2
                .eContents().get(0);

        //NonIDStartWithinStatementExpressionLevel1 Expression:
        //	NonIDStartWithinStatementExpressionLevel2
        //	| {ReturnExpression} 'return' expression=ExpressionLevel1;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //NonIDStartWithinStatementExpressionLevel2 | {ReturnExpression} 'return' expression=ExpressionLevel1
        public Alternatives getAlternatives() {
            return cAlternatives;
        }

        //NonIDStartWithinStatementExpressionLevel2
        public RuleCall getNonIDStartWithinStatementExpressionLevel2ParserRuleCall_0() {
            return cNonIDStartWithinStatementExpressionLevel2ParserRuleCall_0;
        }

        //{ReturnExpression} 'return' expression=ExpressionLevel1
        public Group getGroup_1() {
            return cGroup_1;
        }

        //{ReturnExpression}
        public Action getReturnExpressionAction_1_0() {
            return cReturnExpressionAction_1_0;
        }

        //'return'
        public Keyword getReturnKeyword_1_1() {
            return cReturnKeyword_1_1;
        }

        //expression=ExpressionLevel1
        public Assignment getExpressionAssignment_1_2() {
            return cExpressionAssignment_1_2;
        }

        //ExpressionLevel1
        public RuleCall getExpressionExpressionLevel1ParserRuleCall_1_2_0() {
            return cExpressionExpressionLevel1ParserRuleCall_1_2_0;
        }
    }

    public class NonIDStartWithinStatementExpressionLevel2Elements
            extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.NonIDStartWithinStatementExpressionLevel2");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final RuleCall cNonIDStartWithinStatementExpressionLevel3ParserRuleCall_0 = (RuleCall) cGroup
                .eContents().get(0);

        private final Group cGroup_1 = (Group) cGroup.eContents().get(1);

        private final Action cBinaryOperatorExpressionLeftOperandAction_1_0 = (Action) cGroup_1
                .eContents().get(0);

        private final Assignment cNameAssignment_1_1 = (Assignment) cGroup_1.eContents().get(1);

        private final RuleCall cNameBinaryOperatorLevel2EnumRuleCall_1_1_0 = (RuleCall) cNameAssignment_1_1
                .eContents().get(0);

        private final Assignment cRightOperandAssignment_1_2 = (Assignment) cGroup_1.eContents()
                .get(2);

        private final RuleCall cRightOperandExpressionLevel3ParserRuleCall_1_2_0 = (RuleCall) cRightOperandAssignment_1_2
                .eContents().get(0);

        //NonIDStartWithinStatementExpressionLevel2 Expression:
        //	NonIDStartWithinStatementExpressionLevel3 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel2
        //	rightOperand=ExpressionLevel3)*;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //// Left associativity
        //NonIDStartWithinStatementExpressionLevel3 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel2
        //rightOperand=ExpressionLevel3)*
        public Group getGroup() {
            return cGroup;
        }

        //// Left associativity
        //NonIDStartWithinStatementExpressionLevel3
        public RuleCall getNonIDStartWithinStatementExpressionLevel3ParserRuleCall_0() {
            return cNonIDStartWithinStatementExpressionLevel3ParserRuleCall_0;
        }

        //({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel2 rightOperand=ExpressionLevel3)*
        public Group getGroup_1() {
            return cGroup_1;
        }

        //{BinaryOperatorExpression.leftOperand=current}
        public Action getBinaryOperatorExpressionLeftOperandAction_1_0() {
            return cBinaryOperatorExpressionLeftOperandAction_1_0;
        }

        //name=BinaryOperatorLevel2
        public Assignment getNameAssignment_1_1() {
            return cNameAssignment_1_1;
        }

        //BinaryOperatorLevel2
        public RuleCall getNameBinaryOperatorLevel2EnumRuleCall_1_1_0() {
            return cNameBinaryOperatorLevel2EnumRuleCall_1_1_0;
        }

        //rightOperand=ExpressionLevel3
        public Assignment getRightOperandAssignment_1_2() {
            return cRightOperandAssignment_1_2;
        }

        //ExpressionLevel3
        public RuleCall getRightOperandExpressionLevel3ParserRuleCall_1_2_0() {
            return cRightOperandExpressionLevel3ParserRuleCall_1_2_0;
        }
    }

    public class NonIDStartWithinStatementExpressionLevel3Elements
            extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.NonIDStartWithinStatementExpressionLevel3");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final RuleCall cNonIDStartWithinStatementExpressionLevel4ParserRuleCall_0 = (RuleCall) cGroup
                .eContents().get(0);

        private final Group cGroup_1 = (Group) cGroup.eContents().get(1);

        private final Action cBinaryOperatorExpressionLeftOperandAction_1_0 = (Action) cGroup_1
                .eContents().get(0);

        private final Assignment cNameAssignment_1_1 = (Assignment) cGroup_1.eContents().get(1);

        private final RuleCall cNameBinaryOperatorLevel3EnumRuleCall_1_1_0 = (RuleCall) cNameAssignment_1_1
                .eContents().get(0);

        private final Assignment cRightOperandAssignment_1_2 = (Assignment) cGroup_1.eContents()
                .get(2);

        private final RuleCall cRightOperandExpressionLevel4ParserRuleCall_1_2_0 = (RuleCall) cRightOperandAssignment_1_2
                .eContents().get(0);

        //NonIDStartWithinStatementExpressionLevel3 Expression:
        //	NonIDStartWithinStatementExpressionLevel4 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel3
        //	rightOperand=ExpressionLevel4)*;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //// Left associativity
        //NonIDStartWithinStatementExpressionLevel4 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel3
        //rightOperand=ExpressionLevel4)*
        public Group getGroup() {
            return cGroup;
        }

        //// Left associativity
        //NonIDStartWithinStatementExpressionLevel4
        public RuleCall getNonIDStartWithinStatementExpressionLevel4ParserRuleCall_0() {
            return cNonIDStartWithinStatementExpressionLevel4ParserRuleCall_0;
        }

        //({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel3 rightOperand=ExpressionLevel4)*
        public Group getGroup_1() {
            return cGroup_1;
        }

        //{BinaryOperatorExpression.leftOperand=current}
        public Action getBinaryOperatorExpressionLeftOperandAction_1_0() {
            return cBinaryOperatorExpressionLeftOperandAction_1_0;
        }

        //name=BinaryOperatorLevel3
        public Assignment getNameAssignment_1_1() {
            return cNameAssignment_1_1;
        }

        //BinaryOperatorLevel3
        public RuleCall getNameBinaryOperatorLevel3EnumRuleCall_1_1_0() {
            return cNameBinaryOperatorLevel3EnumRuleCall_1_1_0;
        }

        //rightOperand=ExpressionLevel4
        public Assignment getRightOperandAssignment_1_2() {
            return cRightOperandAssignment_1_2;
        }

        //ExpressionLevel4
        public RuleCall getRightOperandExpressionLevel4ParserRuleCall_1_2_0() {
            return cRightOperandExpressionLevel4ParserRuleCall_1_2_0;
        }
    }

    public class NonIDStartWithinStatementExpressionLevel4Elements
            extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.NonIDStartWithinStatementExpressionLevel4");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final RuleCall cNonIDStartWithinStatementExpressionLevel5ParserRuleCall_0 = (RuleCall) cGroup
                .eContents().get(0);

        private final Group cGroup_1 = (Group) cGroup.eContents().get(1);

        private final Action cBinaryOperatorExpressionLeftOperandAction_1_0 = (Action) cGroup_1
                .eContents().get(0);

        private final Assignment cNameAssignment_1_1 = (Assignment) cGroup_1.eContents().get(1);

        private final RuleCall cNameBinaryOperatorLevel4EnumRuleCall_1_1_0 = (RuleCall) cNameAssignment_1_1
                .eContents().get(0);

        private final Assignment cRightOperandAssignment_1_2 = (Assignment) cGroup_1.eContents()
                .get(2);

        private final RuleCall cRightOperandExpressionLevel5ParserRuleCall_1_2_0 = (RuleCall) cRightOperandAssignment_1_2
                .eContents().get(0);

        //NonIDStartWithinStatementExpressionLevel4 Expression:
        //	NonIDStartWithinStatementExpressionLevel5 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel4
        //	rightOperand=ExpressionLevel5)*;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //// Left associativity
        //NonIDStartWithinStatementExpressionLevel5 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel4
        //rightOperand=ExpressionLevel5)*
        public Group getGroup() {
            return cGroup;
        }

        //// Left associativity
        //NonIDStartWithinStatementExpressionLevel5
        public RuleCall getNonIDStartWithinStatementExpressionLevel5ParserRuleCall_0() {
            return cNonIDStartWithinStatementExpressionLevel5ParserRuleCall_0;
        }

        //({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5)*
        public Group getGroup_1() {
            return cGroup_1;
        }

        //{BinaryOperatorExpression.leftOperand=current}
        public Action getBinaryOperatorExpressionLeftOperandAction_1_0() {
            return cBinaryOperatorExpressionLeftOperandAction_1_0;
        }

        //name=BinaryOperatorLevel4
        public Assignment getNameAssignment_1_1() {
            return cNameAssignment_1_1;
        }

        //BinaryOperatorLevel4
        public RuleCall getNameBinaryOperatorLevel4EnumRuleCall_1_1_0() {
            return cNameBinaryOperatorLevel4EnumRuleCall_1_1_0;
        }

        //rightOperand=ExpressionLevel5
        public Assignment getRightOperandAssignment_1_2() {
            return cRightOperandAssignment_1_2;
        }

        //ExpressionLevel5
        public RuleCall getRightOperandExpressionLevel5ParserRuleCall_1_2_0() {
            return cRightOperandExpressionLevel5ParserRuleCall_1_2_0;
        }
    }

    public class NonIDStartWithinStatementExpressionLevel5Elements
            extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.NonIDStartWithinStatementExpressionLevel5");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final RuleCall cNonIDStartWithinStatementExpressionLevel6ParserRuleCall_0 = (RuleCall) cGroup
                .eContents().get(0);

        private final Group cGroup_1 = (Group) cGroup.eContents().get(1);

        private final Action cDataMethodCallExpressionTargetAction_1_0 = (Action) cGroup_1
                .eContents().get(0);

        private final Assignment cOnSuperClassAssignment_1_1 = (Assignment) cGroup_1.eContents()
                .get(1);

        private final Keyword cOnSuperClassCircumflexAccentKeyword_1_1_0 = (Keyword) cOnSuperClassAssignment_1_1
                .eContents().get(0);

        private final Assignment cNameAssignment_1_2 = (Assignment) cGroup_1.eContents().get(2);

        private final RuleCall cNameIDENTIFIERParserRuleCall_1_2_0 = (RuleCall) cNameAssignment_1_2
                .eContents().get(0);

        private final Group cGroup_1_3 = (Group) cGroup_1.eContents().get(3);

        private final Keyword cLeftParenthesisKeyword_1_3_0 = (Keyword) cGroup_1_3.eContents()
                .get(0);

        private final Group cGroup_1_3_1 = (Group) cGroup_1_3.eContents().get(1);

        private final Assignment cArgumentsAssignment_1_3_1_0 = (Assignment) cGroup_1_3_1
                .eContents().get(0);

        private final RuleCall cArgumentsExpressionParserRuleCall_1_3_1_0_0 = (RuleCall) cArgumentsAssignment_1_3_1_0
                .eContents().get(0);

        private final Group cGroup_1_3_1_1 = (Group) cGroup_1_3_1.eContents().get(1);

        private final Keyword cCommaKeyword_1_3_1_1_0 = (Keyword) cGroup_1_3_1_1.eContents().get(0);

        private final Assignment cArgumentsAssignment_1_3_1_1_1 = (Assignment) cGroup_1_3_1_1
                .eContents().get(1);

        private final RuleCall cArgumentsExpressionParserRuleCall_1_3_1_1_1_0 = (RuleCall) cArgumentsAssignment_1_3_1_1_1
                .eContents().get(0);

        private final Keyword cRightParenthesisKeyword_1_3_2 = (Keyword) cGroup_1_3.eContents()
                .get(2);

        //NonIDStartWithinStatementExpressionLevel5 Expression:
        //	NonIDStartWithinStatementExpressionLevel6 ({DataMethodCallExpression.target=current} onSuperClass?='^'?
        //	name=IDENTIFIER ('(' (arguments+=Expression (',' arguments+=Expression)*)? ')')?)*;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //NonIDStartWithinStatementExpressionLevel6 ({DataMethodCallExpression.target=current} onSuperClass?='^'? name=IDENTIFIER
        //('(' (arguments+=Expression (',' arguments+=Expression)*)? ')')?)*
        public Group getGroup() {
            return cGroup;
        }

        //NonIDStartWithinStatementExpressionLevel6
        public RuleCall getNonIDStartWithinStatementExpressionLevel6ParserRuleCall_0() {
            return cNonIDStartWithinStatementExpressionLevel6ParserRuleCall_0;
        }

        //({DataMethodCallExpression.target=current} onSuperClass?='^'? name=IDENTIFIER ('(' (arguments+=Expression (','
        //arguments+=Expression)*)? ')')?)*
        public Group getGroup_1() {
            return cGroup_1;
        }

        //{DataMethodCallExpression.target=current}
        public Action getDataMethodCallExpressionTargetAction_1_0() {
            return cDataMethodCallExpressionTargetAction_1_0;
        }

        //onSuperClass?='^'?
        public Assignment getOnSuperClassAssignment_1_1() {
            return cOnSuperClassAssignment_1_1;
        }

        //'^'
        public Keyword getOnSuperClassCircumflexAccentKeyword_1_1_0() {
            return cOnSuperClassCircumflexAccentKeyword_1_1_0;
        }

        //name=IDENTIFIER
        public Assignment getNameAssignment_1_2() {
            return cNameAssignment_1_2;
        }

        //IDENTIFIER
        public RuleCall getNameIDENTIFIERParserRuleCall_1_2_0() {
            return cNameIDENTIFIERParserRuleCall_1_2_0;
        }

        //('(' (arguments+=Expression (',' arguments+=Expression)*)? ')')?
        public Group getGroup_1_3() {
            return cGroup_1_3;
        }

        //'('
        public Keyword getLeftParenthesisKeyword_1_3_0() {
            return cLeftParenthesisKeyword_1_3_0;
        }

        //(arguments+=Expression (',' arguments+=Expression)*)?
        public Group getGroup_1_3_1() {
            return cGroup_1_3_1;
        }

        //arguments+=Expression
        public Assignment getArgumentsAssignment_1_3_1_0() {
            return cArgumentsAssignment_1_3_1_0;
        }

        //Expression
        public RuleCall getArgumentsExpressionParserRuleCall_1_3_1_0_0() {
            return cArgumentsExpressionParserRuleCall_1_3_1_0_0;
        }

        //(',' arguments+=Expression)*
        public Group getGroup_1_3_1_1() {
            return cGroup_1_3_1_1;
        }

        //','
        public Keyword getCommaKeyword_1_3_1_1_0() {
            return cCommaKeyword_1_3_1_1_0;
        }

        //arguments+=Expression
        public Assignment getArgumentsAssignment_1_3_1_1_1() {
            return cArgumentsAssignment_1_3_1_1_1;
        }

        //Expression
        public RuleCall getArgumentsExpressionParserRuleCall_1_3_1_1_1_0() {
            return cArgumentsExpressionParserRuleCall_1_3_1_1_1_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_1_3_2() {
            return cRightParenthesisKeyword_1_3_2;
        }
    }

    public class NonIDStartWithinStatementExpressionLevel6Elements
            extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.NonIDStartWithinStatementExpressionLevel6");

        private final Alternatives cAlternatives = (Alternatives) rule.eContents().get(1);

        private final RuleCall cNonIDStartWithinStatementExpressionLevel7ParserRuleCall_0 = (RuleCall) cAlternatives
                .eContents().get(0);

        private final RuleCall cUnaryOperatorExpressionParserRuleCall_1 = (RuleCall) cAlternatives
                .eContents().get(1);

        //NonIDStartWithinStatementExpressionLevel6 Expression:
        //	=> NonIDStartWithinStatementExpressionLevel7 | UnaryOperatorExpression;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //=> NonIDStartWithinStatementExpressionLevel7 | UnaryOperatorExpression
        public Alternatives getAlternatives() {
            return cAlternatives;
        }

        //=> NonIDStartWithinStatementExpressionLevel7
        public RuleCall getNonIDStartWithinStatementExpressionLevel7ParserRuleCall_0() {
            return cNonIDStartWithinStatementExpressionLevel7ParserRuleCall_0;
        }

        //UnaryOperatorExpression
        public RuleCall getUnaryOperatorExpressionParserRuleCall_1() {
            return cUnaryOperatorExpressionParserRuleCall_1;
        }
    }

    public class NonIDStartWithinStatementExpressionLevel7Elements
            extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.NonIDStartWithinStatementExpressionLevel7");

        private final Alternatives cAlternatives = (Alternatives) rule.eContents().get(1);

        private final RuleCall cCurrentTimeExpressionParserRuleCall_0 = (RuleCall) cAlternatives
                .eContents().get(0);

        private final RuleCall cSelfExpressionParserRuleCall_1 = (RuleCall) cAlternatives
                .eContents().get(1);

        private final RuleCall cExpressionConstantParserRuleCall_2 = (RuleCall) cAlternatives
                .eContents().get(2);

        private final RuleCall cNewExpressionParserRuleCall_3 = (RuleCall) cAlternatives.eContents()
                .get(3);

        //NonIDStartWithinStatementExpressionLevel7 Expression:
        //	CurrentTimeExpression | SelfExpression | ExpressionConstant | NewExpression;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //CurrentTimeExpression | SelfExpression | ExpressionConstant | NewExpression
        public Alternatives getAlternatives() {
            return cAlternatives;
        }

        //CurrentTimeExpression
        public RuleCall getCurrentTimeExpressionParserRuleCall_0() {
            return cCurrentTimeExpressionParserRuleCall_0;
        }

        //SelfExpression
        public RuleCall getSelfExpressionParserRuleCall_1() {
            return cSelfExpressionParserRuleCall_1;
        }

        //ExpressionConstant
        public RuleCall getExpressionConstantParserRuleCall_2() {
            return cExpressionConstantParserRuleCall_2;
        }

        //NewExpression
        public RuleCall getNewExpressionParserRuleCall_3() {
            return cNewExpressionParserRuleCall_3;
        }
    }

    public class StatementElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.Statement");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final RuleCall cStatementSingleParserRuleCall_0 = (RuleCall) cGroup.eContents()
                .get(0);

        private final Group cGroup_1 = (Group) cGroup.eContents().get(1);

        private final Action cStatementSequenceStatementsAction_1_0 = (Action) cGroup_1.eContents()
                .get(0);

        private final Group cGroup_1_1 = (Group) cGroup_1.eContents().get(1);

        private final Keyword cSemicolonKeyword_1_1_0 = (Keyword) cGroup_1_1.eContents().get(0);

        private final Assignment cStatementsAssignment_1_1_1 = (Assignment) cGroup_1_1.eContents()
                .get(1);

        private final RuleCall cStatementsStatementSingleParserRuleCall_1_1_1_0 = (RuleCall) cStatementsAssignment_1_1_1
                .eContents().get(0);

        //// === Statements =======
        //Statement:
        //	StatementSingle ({StatementSequence.statements+=current} (';' statements+=StatementSingle)+)?;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //StatementSingle ({StatementSequence.statements+=current} (';' statements+=StatementSingle)+)?
        public Group getGroup() {
            return cGroup;
        }

        //StatementSingle
        public RuleCall getStatementSingleParserRuleCall_0() {
            return cStatementSingleParserRuleCall_0;
        }

        //({StatementSequence.statements+=current} (';' statements+=StatementSingle)+)?
        public Group getGroup_1() {
            return cGroup_1;
        }

        //{StatementSequence.statements+=current}
        public Action getStatementSequenceStatementsAction_1_0() {
            return cStatementSequenceStatementsAction_1_0;
        }

        //(';' statements+=StatementSingle)+
        public Group getGroup_1_1() {
            return cGroup_1_1;
        }

        //';'
        public Keyword getSemicolonKeyword_1_1_0() {
            return cSemicolonKeyword_1_1_0;
        }

        //statements+=StatementSingle
        public Assignment getStatementsAssignment_1_1_1() {
            return cStatementsAssignment_1_1_1;
        }

        //StatementSingle
        public RuleCall getStatementsStatementSingleParserRuleCall_1_1_1_0() {
            return cStatementsStatementSingleParserRuleCall_1_1_1_0;
        }
    }

    public class StatementSingleElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.StatementSingle");

        private final Alternatives cAlternatives = (Alternatives) rule.eContents().get(1);

        private final RuleCall cAbortStatementParserRuleCall_0 = (RuleCall) cAlternatives
                .eContents().get(0);

        private final RuleCall cDelayStatementParserRuleCall_1 = (RuleCall) cAlternatives
                .eContents().get(1);

        private final RuleCall cGuardedStatementParserRuleCall_2 = (RuleCall) cAlternatives
                .eContents().get(2);

        private final RuleCall cInterruptStatementParserRuleCall_3 = (RuleCall) cAlternatives
                .eContents().get(3);

        private final RuleCall cParStatementParserRuleCall_4 = (RuleCall) cAlternatives.eContents()
                .get(4);

        private final RuleCall cProcessMethodCallParserRuleCall_5 = (RuleCall) cAlternatives
                .eContents().get(5);

        private final RuleCall cSelStatementParserRuleCall_6 = (RuleCall) cAlternatives.eContents()
                .get(6);

        private final RuleCall cSkipStatementParserRuleCall_7 = (RuleCall) cAlternatives.eContents()
                .get(7);

        private final RuleCall cSendStatementParserRuleCall_8 = (RuleCall) cAlternatives.eContents()
                .get(8);

        private final RuleCall cReceiveStatementParserRuleCall_9 = (RuleCall) cAlternatives
                .eContents().get(9);

        private final RuleCall cIfStatementParserRuleCall_10 = (RuleCall) cAlternatives.eContents()
                .get(10);

        private final RuleCall cBracketStartStatementParserRuleCall_11 = (RuleCall) cAlternatives
                .eContents().get(11);

        private final RuleCall cWhileStatementParserRuleCall_12 = (RuleCall) cAlternatives
                .eContents().get(12);

        private final RuleCall cSwitchStatementParserRuleCall_13 = (RuleCall) cAlternatives
                .eContents().get(13);

        private final RuleCall cExpressionStatementParserRuleCall_14 = (RuleCall) cAlternatives
                .eContents().get(14);

        //StatementSingle Statement:
        //	AbortStatement | DelayStatement | GuardedStatement | InterruptStatement | ParStatement | ProcessMethodCall |
        //	SelStatement | SkipStatement | SendStatement | ReceiveStatement | IfStatement | BracketStartStatement |
        //	WhileStatement | SwitchStatement | ExpressionStatement;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //AbortStatement | DelayStatement | GuardedStatement | InterruptStatement | ParStatement | ProcessMethodCall |
        //SelStatement | SkipStatement | SendStatement | ReceiveStatement | IfStatement | BracketStartStatement | WhileStatement
        //| SwitchStatement | ExpressionStatement
        public Alternatives getAlternatives() {
            return cAlternatives;
        }

        //AbortStatement
        public RuleCall getAbortStatementParserRuleCall_0() {
            return cAbortStatementParserRuleCall_0;
        }

        //DelayStatement
        public RuleCall getDelayStatementParserRuleCall_1() {
            return cDelayStatementParserRuleCall_1;
        }

        //GuardedStatement
        public RuleCall getGuardedStatementParserRuleCall_2() {
            return cGuardedStatementParserRuleCall_2;
        }

        //InterruptStatement
        public RuleCall getInterruptStatementParserRuleCall_3() {
            return cInterruptStatementParserRuleCall_3;
        }

        //ParStatement
        public RuleCall getParStatementParserRuleCall_4() {
            return cParStatementParserRuleCall_4;
        }

        //ProcessMethodCall
        public RuleCall getProcessMethodCallParserRuleCall_5() {
            return cProcessMethodCallParserRuleCall_5;
        }

        //SelStatement
        public RuleCall getSelStatementParserRuleCall_6() {
            return cSelStatementParserRuleCall_6;
        }

        //SkipStatement
        public RuleCall getSkipStatementParserRuleCall_7() {
            return cSkipStatementParserRuleCall_7;
        }

        //SendStatement
        public RuleCall getSendStatementParserRuleCall_8() {
            return cSendStatementParserRuleCall_8;
        }

        //ReceiveStatement
        public RuleCall getReceiveStatementParserRuleCall_9() {
            return cReceiveStatementParserRuleCall_9;
        }

        //IfStatement
        public RuleCall getIfStatementParserRuleCall_10() {
            return cIfStatementParserRuleCall_10;
        }

        //BracketStartStatement
        public RuleCall getBracketStartStatementParserRuleCall_11() {
            return cBracketStartStatementParserRuleCall_11;
        }

        //WhileStatement
        public RuleCall getWhileStatementParserRuleCall_12() {
            return cWhileStatementParserRuleCall_12;
        }

        //SwitchStatement
        public RuleCall getSwitchStatementParserRuleCall_13() {
            return cSwitchStatementParserRuleCall_13;
        }

        //ExpressionStatement
        public RuleCall getExpressionStatementParserRuleCall_14() {
            return cExpressionStatementParserRuleCall_14;
        }
    }

    public class ExpressionStatementElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.ExpressionStatement");

        private final Alternatives cAlternatives = (Alternatives) rule.eContents().get(1);

        private final Assignment cExpressionAssignment_0 = (Assignment) cAlternatives.eContents()
                .get(0);

        private final RuleCall cExpressionIDStartWithinStatementExpressionSingleParserRuleCall_0_0 = (RuleCall) cExpressionAssignment_0
                .eContents().get(0);

        private final Assignment cExpressionAssignment_1 = (Assignment) cAlternatives.eContents()
                .get(1);

        private final RuleCall cExpressionNonIDStartWithinStatementExpressionSingleParserRuleCall_1_0 = (RuleCall) cExpressionAssignment_1
                .eContents().get(0);

        private final Group cGroup_2 = (Group) cAlternatives.eContents().get(2);

        private final Keyword cLeftCurlyBracketKeyword_2_0 = (Keyword) cGroup_2.eContents().get(0);

        private final Assignment cExpressionAssignment_2_1 = (Assignment) cGroup_2.eContents()
                .get(1);

        private final RuleCall cExpressionExpressionParserRuleCall_2_1_0 = (RuleCall) cExpressionAssignment_2_1
                .eContents().get(0);

        private final Keyword cRightCurlyBracketKeyword_2_2 = (Keyword) cGroup_2.eContents().get(2);

        //ExpressionStatement:
        //	expression=IDStartWithinStatementExpressionSingle | expression=NonIDStartWithinStatementExpressionSingle |
        //	'{' expression=Expression '}';
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //expression=IDStartWithinStatementExpressionSingle | expression=NonIDStartWithinStatementExpressionSingle | '{'
        //expression=Expression '}'
        public Alternatives getAlternatives() {
            return cAlternatives;
        }

        //expression=IDStartWithinStatementExpressionSingle
        public Assignment getExpressionAssignment_0() {
            return cExpressionAssignment_0;
        }

        //IDStartWithinStatementExpressionSingle
        public RuleCall getExpressionIDStartWithinStatementExpressionSingleParserRuleCall_0_0() {
            return cExpressionIDStartWithinStatementExpressionSingleParserRuleCall_0_0;
        }

        //expression=NonIDStartWithinStatementExpressionSingle
        public Assignment getExpressionAssignment_1() {
            return cExpressionAssignment_1;
        }

        //NonIDStartWithinStatementExpressionSingle
        public RuleCall getExpressionNonIDStartWithinStatementExpressionSingleParserRuleCall_1_0() {
            return cExpressionNonIDStartWithinStatementExpressionSingleParserRuleCall_1_0;
        }

        //'{' expression=Expression '}'
        public Group getGroup_2() {
            return cGroup_2;
        }

        //'{'
        public Keyword getLeftCurlyBracketKeyword_2_0() {
            return cLeftCurlyBracketKeyword_2_0;
        }

        //expression=Expression
        public Assignment getExpressionAssignment_2_1() {
            return cExpressionAssignment_2_1;
        }

        //Expression
        public RuleCall getExpressionExpressionParserRuleCall_2_1_0() {
            return cExpressionExpressionParserRuleCall_2_1_0;
        }

        //'}'
        public Keyword getRightCurlyBracketKeyword_2_2() {
            return cRightCurlyBracketKeyword_2_2;
        }
    }

    public class DelayStatementElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.DelayStatement");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Keyword cDelayKeyword_0 = (Keyword) cGroup.eContents().get(0);

        private final Assignment cExpressionAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cExpressionExpressionSingleParserRuleCall_1_0 = (RuleCall) cExpressionAssignment_1
                .eContents().get(0);

        //DelayStatement:
        //	'delay' expression=ExpressionSingle;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //'delay' expression=ExpressionSingle
        public Group getGroup() {
            return cGroup;
        }

        //'delay'
        public Keyword getDelayKeyword_0() {
            return cDelayKeyword_0;
        }

        //expression=ExpressionSingle
        public Assignment getExpressionAssignment_1() {
            return cExpressionAssignment_1;
        }

        //ExpressionSingle
        public RuleCall getExpressionExpressionSingleParserRuleCall_1_0() {
            return cExpressionExpressionSingleParserRuleCall_1_0;
        }
    }

    public class ReceiveStatementElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.ReceiveStatement");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Assignment cPortDescriptorAssignment_0 = (Assignment) cGroup.eContents()
                .get(0);

        private final RuleCall cPortDescriptorPortDescriptorParserRuleCall_0_0 = (RuleCall) cPortDescriptorAssignment_0
                .eContents().get(0);

        private final Keyword cQuestionMarkKeyword_1 = (Keyword) cGroup.eContents().get(1);

        private final Assignment cNameAssignment_2 = (Assignment) cGroup.eContents().get(2);

        private final RuleCall cNameIDENTIFIERParserRuleCall_2_0 = (RuleCall) cNameAssignment_2
                .eContents().get(0);

        private final Group cGroup_3 = (Group) cGroup.eContents().get(3);

        private final Keyword cLeftParenthesisKeyword_3_0 = (Keyword) cGroup_3.eContents().get(0);

        private final Group cGroup_3_1 = (Group) cGroup_3.eContents().get(1);

        private final Assignment cVariablesAssignment_3_1_0 = (Assignment) cGroup_3_1.eContents()
                .get(0);

        private final RuleCall cVariablesOutputVariableParserRuleCall_3_1_0_0 = (RuleCall) cVariablesAssignment_3_1_0
                .eContents().get(0);

        private final Group cGroup_3_1_1 = (Group) cGroup_3_1.eContents().get(1);

        private final Keyword cCommaKeyword_3_1_1_0 = (Keyword) cGroup_3_1_1.eContents().get(0);

        private final Assignment cVariablesAssignment_3_1_1_1 = (Assignment) cGroup_3_1_1
                .eContents().get(1);

        private final RuleCall cVariablesOutputVariableParserRuleCall_3_1_1_1_0 = (RuleCall) cVariablesAssignment_3_1_1_1
                .eContents().get(0);

        private final Group cGroup_3_2 = (Group) cGroup_3.eContents().get(2);

        private final Keyword cVerticalLineKeyword_3_2_0 = (Keyword) cGroup_3_2.eContents().get(0);

        private final Assignment cReceptionConditionAssignment_3_2_1 = (Assignment) cGroup_3_2
                .eContents().get(1);

        private final RuleCall cReceptionConditionExpressionParserRuleCall_3_2_1_0 = (RuleCall) cReceptionConditionAssignment_3_2_1
                .eContents().get(0);

        private final Keyword cRightParenthesisKeyword_3_3 = (Keyword) cGroup_3.eContents().get(3);

        private final Group cGroup_4 = (Group) cGroup.eContents().get(4);

        private final Keyword cLeftCurlyBracketKeyword_4_0 = (Keyword) cGroup_4.eContents().get(0);

        private final Assignment cPostCommunicationExpressionAssignment_4_1 = (Assignment) cGroup_4
                .eContents().get(1);

        private final RuleCall cPostCommunicationExpressionExpressionParserRuleCall_4_1_0 = (RuleCall) cPostCommunicationExpressionAssignment_4_1
                .eContents().get(0);

        private final Keyword cRightCurlyBracketKeyword_4_2 = (Keyword) cGroup_4.eContents().get(2);

        //ReceiveStatement:
        //	portDescriptor=PortDescriptor '?' name=IDENTIFIER ('(' (variables+=OutputVariable (',' variables+=OutputVariable)*)?
        //	('|' receptionCondition=Expression)? ')')? ('{' postCommunicationExpression=Expression '}')?;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //portDescriptor=PortDescriptor '?' name=IDENTIFIER ('(' (variables+=OutputVariable (',' variables+=OutputVariable)*)?
        //('|' receptionCondition=Expression)? ')')? ('{' postCommunicationExpression=Expression '}')?
        public Group getGroup() {
            return cGroup;
        }

        //portDescriptor=PortDescriptor
        public Assignment getPortDescriptorAssignment_0() {
            return cPortDescriptorAssignment_0;
        }

        //PortDescriptor
        public RuleCall getPortDescriptorPortDescriptorParserRuleCall_0_0() {
            return cPortDescriptorPortDescriptorParserRuleCall_0_0;
        }

        //'?'
        public Keyword getQuestionMarkKeyword_1() {
            return cQuestionMarkKeyword_1;
        }

        //name=IDENTIFIER
        public Assignment getNameAssignment_2() {
            return cNameAssignment_2;
        }

        //IDENTIFIER
        public RuleCall getNameIDENTIFIERParserRuleCall_2_0() {
            return cNameIDENTIFIERParserRuleCall_2_0;
        }

        //('(' (variables+=OutputVariable (',' variables+=OutputVariable)*)? ('|' receptionCondition=Expression)? ')')?
        public Group getGroup_3() {
            return cGroup_3;
        }

        //'('
        public Keyword getLeftParenthesisKeyword_3_0() {
            return cLeftParenthesisKeyword_3_0;
        }

        //(variables+=OutputVariable (',' variables+=OutputVariable)*)?
        public Group getGroup_3_1() {
            return cGroup_3_1;
        }

        //variables+=OutputVariable
        public Assignment getVariablesAssignment_3_1_0() {
            return cVariablesAssignment_3_1_0;
        }

        //OutputVariable
        public RuleCall getVariablesOutputVariableParserRuleCall_3_1_0_0() {
            return cVariablesOutputVariableParserRuleCall_3_1_0_0;
        }

        //(',' variables+=OutputVariable)*
        public Group getGroup_3_1_1() {
            return cGroup_3_1_1;
        }

        //','
        public Keyword getCommaKeyword_3_1_1_0() {
            return cCommaKeyword_3_1_1_0;
        }

        //variables+=OutputVariable
        public Assignment getVariablesAssignment_3_1_1_1() {
            return cVariablesAssignment_3_1_1_1;
        }

        //OutputVariable
        public RuleCall getVariablesOutputVariableParserRuleCall_3_1_1_1_0() {
            return cVariablesOutputVariableParserRuleCall_3_1_1_1_0;
        }

        //('|' receptionCondition=Expression)?
        public Group getGroup_3_2() {
            return cGroup_3_2;
        }

        //'|'
        public Keyword getVerticalLineKeyword_3_2_0() {
            return cVerticalLineKeyword_3_2_0;
        }

        //receptionCondition=Expression
        public Assignment getReceptionConditionAssignment_3_2_1() {
            return cReceptionConditionAssignment_3_2_1;
        }

        //Expression
        public RuleCall getReceptionConditionExpressionParserRuleCall_3_2_1_0() {
            return cReceptionConditionExpressionParserRuleCall_3_2_1_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_3_3() {
            return cRightParenthesisKeyword_3_3;
        }

        //('{' postCommunicationExpression=Expression '}')?
        public Group getGroup_4() {
            return cGroup_4;
        }

        //'{'
        public Keyword getLeftCurlyBracketKeyword_4_0() {
            return cLeftCurlyBracketKeyword_4_0;
        }

        //postCommunicationExpression=Expression
        public Assignment getPostCommunicationExpressionAssignment_4_1() {
            return cPostCommunicationExpressionAssignment_4_1;
        }

        //Expression
        public RuleCall getPostCommunicationExpressionExpressionParserRuleCall_4_1_0() {
            return cPostCommunicationExpressionExpressionParserRuleCall_4_1_0;
        }

        //'}'
        public Keyword getRightCurlyBracketKeyword_4_2() {
            return cRightCurlyBracketKeyword_4_2;
        }
    }

    public class SendStatementElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.SendStatement");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Assignment cPortDescriptorAssignment_0 = (Assignment) cGroup.eContents()
                .get(0);

        private final RuleCall cPortDescriptorPortDescriptorParserRuleCall_0_0 = (RuleCall) cPortDescriptorAssignment_0
                .eContents().get(0);

        private final Keyword cExclamationMarkKeyword_1 = (Keyword) cGroup.eContents().get(1);

        private final Assignment cNameAssignment_2 = (Assignment) cGroup.eContents().get(2);

        private final RuleCall cNameIDENTIFIERParserRuleCall_2_0 = (RuleCall) cNameAssignment_2
                .eContents().get(0);

        private final Group cGroup_3 = (Group) cGroup.eContents().get(3);

        private final Keyword cLeftParenthesisKeyword_3_0 = (Keyword) cGroup_3.eContents().get(0);

        private final Group cGroup_3_1 = (Group) cGroup_3.eContents().get(1);

        private final Assignment cArgumentsAssignment_3_1_0 = (Assignment) cGroup_3_1.eContents()
                .get(0);

        private final RuleCall cArgumentsExpressionParserRuleCall_3_1_0_0 = (RuleCall) cArgumentsAssignment_3_1_0
                .eContents().get(0);

        private final Group cGroup_3_1_1 = (Group) cGroup_3_1.eContents().get(1);

        private final Keyword cCommaKeyword_3_1_1_0 = (Keyword) cGroup_3_1_1.eContents().get(0);

        private final Assignment cArgumentsAssignment_3_1_1_1 = (Assignment) cGroup_3_1_1
                .eContents().get(1);

        private final RuleCall cArgumentsExpressionParserRuleCall_3_1_1_1_0 = (RuleCall) cArgumentsAssignment_3_1_1_1
                .eContents().get(0);

        private final Keyword cRightParenthesisKeyword_3_2 = (Keyword) cGroup_3.eContents().get(2);

        private final Group cGroup_4 = (Group) cGroup.eContents().get(4);

        private final Keyword cLeftCurlyBracketKeyword_4_0 = (Keyword) cGroup_4.eContents().get(0);

        private final Assignment cPostCommunicationExpressionAssignment_4_1 = (Assignment) cGroup_4
                .eContents().get(1);

        private final RuleCall cPostCommunicationExpressionExpressionParserRuleCall_4_1_0 = (RuleCall) cPostCommunicationExpressionAssignment_4_1
                .eContents().get(0);

        private final Keyword cRightCurlyBracketKeyword_4_2 = (Keyword) cGroup_4.eContents().get(2);

        //SendStatement:
        //	portDescriptor=PortDescriptor '!' name=IDENTIFIER ('(' (arguments+=Expression (',' arguments+=Expression)*)? ')')?
        //	('{' postCommunicationExpression=Expression '}')?;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //portDescriptor=PortDescriptor '!' name=IDENTIFIER ('(' (arguments+=Expression (',' arguments+=Expression)*)? ')')? ('{'
        //postCommunicationExpression=Expression '}')?
        public Group getGroup() {
            return cGroup;
        }

        //portDescriptor=PortDescriptor
        public Assignment getPortDescriptorAssignment_0() {
            return cPortDescriptorAssignment_0;
        }

        //PortDescriptor
        public RuleCall getPortDescriptorPortDescriptorParserRuleCall_0_0() {
            return cPortDescriptorPortDescriptorParserRuleCall_0_0;
        }

        //'!'
        public Keyword getExclamationMarkKeyword_1() {
            return cExclamationMarkKeyword_1;
        }

        //name=IDENTIFIER
        public Assignment getNameAssignment_2() {
            return cNameAssignment_2;
        }

        //IDENTIFIER
        public RuleCall getNameIDENTIFIERParserRuleCall_2_0() {
            return cNameIDENTIFIERParserRuleCall_2_0;
        }

        //('(' (arguments+=Expression (',' arguments+=Expression)*)? ')')?
        public Group getGroup_3() {
            return cGroup_3;
        }

        //'('
        public Keyword getLeftParenthesisKeyword_3_0() {
            return cLeftParenthesisKeyword_3_0;
        }

        //(arguments+=Expression (',' arguments+=Expression)*)?
        public Group getGroup_3_1() {
            return cGroup_3_1;
        }

        //arguments+=Expression
        public Assignment getArgumentsAssignment_3_1_0() {
            return cArgumentsAssignment_3_1_0;
        }

        //Expression
        public RuleCall getArgumentsExpressionParserRuleCall_3_1_0_0() {
            return cArgumentsExpressionParserRuleCall_3_1_0_0;
        }

        //(',' arguments+=Expression)*
        public Group getGroup_3_1_1() {
            return cGroup_3_1_1;
        }

        //','
        public Keyword getCommaKeyword_3_1_1_0() {
            return cCommaKeyword_3_1_1_0;
        }

        //arguments+=Expression
        public Assignment getArgumentsAssignment_3_1_1_1() {
            return cArgumentsAssignment_3_1_1_1;
        }

        //Expression
        public RuleCall getArgumentsExpressionParserRuleCall_3_1_1_1_0() {
            return cArgumentsExpressionParserRuleCall_3_1_1_1_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_3_2() {
            return cRightParenthesisKeyword_3_2;
        }

        //('{' postCommunicationExpression=Expression '}')?
        public Group getGroup_4() {
            return cGroup_4;
        }

        //'{'
        public Keyword getLeftCurlyBracketKeyword_4_0() {
            return cLeftCurlyBracketKeyword_4_0;
        }

        //postCommunicationExpression=Expression
        public Assignment getPostCommunicationExpressionAssignment_4_1() {
            return cPostCommunicationExpressionAssignment_4_1;
        }

        //Expression
        public RuleCall getPostCommunicationExpressionExpressionParserRuleCall_4_1_0() {
            return cPostCommunicationExpressionExpressionParserRuleCall_4_1_0;
        }

        //'}'
        public Keyword getRightCurlyBracketKeyword_4_2() {
            return cRightCurlyBracketKeyword_4_2;
        }
    }

    public class PortDescriptorElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.PortDescriptor");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Action cPortReferenceAction_0 = (Action) cGroup.eContents().get(0);

        private final Assignment cPortAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cPortIDENTIFIERParserRuleCall_1_0 = (RuleCall) cPortAssignment_1
                .eContents().get(0);

        //PortDescriptor:
        //	{PortReference} port=IDENTIFIER;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //{PortReference} port=IDENTIFIER
        public Group getGroup() {
            return cGroup;
        }

        //{PortReference}
        public Action getPortReferenceAction_0() {
            return cPortReferenceAction_0;
        }

        //port=IDENTIFIER
        public Assignment getPortAssignment_1() {
            return cPortAssignment_1;
        }

        //IDENTIFIER
        public RuleCall getPortIDENTIFIERParserRuleCall_1_0() {
            return cPortIDENTIFIERParserRuleCall_1_0;
        }
    }

    public class PortReferenceElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.PortReference");

        private final Assignment cPortAssignment = (Assignment) rule.eContents().get(1);

        private final RuleCall cPortIDENTIFIERParserRuleCall_0 = (RuleCall) cPortAssignment
                .eContents().get(0);

        //PortReference:
        //	port=IDENTIFIER;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //port=IDENTIFIER
        public Assignment getPortAssignment() {
            return cPortAssignment;
        }

        //IDENTIFIER
        public RuleCall getPortIDENTIFIERParserRuleCall_0() {
            return cPortIDENTIFIERParserRuleCall_0;
        }
    }

    public class SkipStatementElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.SkipStatement");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Action cSkipStatementAction_0 = (Action) cGroup.eContents().get(0);

        private final Keyword cSkipKeyword_1 = (Keyword) cGroup.eContents().get(1);

        //SkipStatement:
        //	{SkipStatement} 'skip';
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //{SkipStatement} 'skip'
        public Group getGroup() {
            return cGroup;
        }

        //{SkipStatement}
        public Action getSkipStatementAction_0() {
            return cSkipStatementAction_0;
        }

        //'skip'
        public Keyword getSkipKeyword_1() {
            return cSkipKeyword_1;
        }
    }

    public class GuardedStatementElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.GuardedStatement");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Keyword cLeftSquareBracketKeyword_0 = (Keyword) cGroup.eContents().get(0);

        private final Assignment cGuardAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cGuardExpressionParserRuleCall_1_0 = (RuleCall) cGuardAssignment_1
                .eContents().get(0);

        private final Keyword cRightSquareBracketKeyword_2 = (Keyword) cGroup.eContents().get(2);

        private final Assignment cStatementAssignment_3 = (Assignment) cGroup.eContents().get(3);

        private final RuleCall cStatementStatementSingleParserRuleCall_3_0 = (RuleCall) cStatementAssignment_3
                .eContents().get(0);

        //GuardedStatement:
        //	'[' guard=Expression ']' statement=StatementSingle;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //'[' guard=Expression ']' statement=StatementSingle
        public Group getGroup() {
            return cGroup;
        }

        //'['
        public Keyword getLeftSquareBracketKeyword_0() {
            return cLeftSquareBracketKeyword_0;
        }

        //guard=Expression
        public Assignment getGuardAssignment_1() {
            return cGuardAssignment_1;
        }

        //Expression
        public RuleCall getGuardExpressionParserRuleCall_1_0() {
            return cGuardExpressionParserRuleCall_1_0;
        }

        //']'
        public Keyword getRightSquareBracketKeyword_2() {
            return cRightSquareBracketKeyword_2;
        }

        //statement=StatementSingle
        public Assignment getStatementAssignment_3() {
            return cStatementAssignment_3;
        }

        //StatementSingle
        public RuleCall getStatementStatementSingleParserRuleCall_3_0() {
            return cStatementStatementSingleParserRuleCall_3_0;
        }
    }

    public class IfStatementElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.IfStatement");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Keyword cIfKeyword_0 = (Keyword) cGroup.eContents().get(0);

        private final Assignment cConditionAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cConditionExpressionParserRuleCall_1_0 = (RuleCall) cConditionAssignment_1
                .eContents().get(0);

        private final Keyword cThenKeyword_2 = (Keyword) cGroup.eContents().get(2);

        private final Assignment cThenClauseAssignment_3 = (Assignment) cGroup.eContents().get(3);

        private final RuleCall cThenClauseStatementParserRuleCall_3_0 = (RuleCall) cThenClauseAssignment_3
                .eContents().get(0);

        private final Group cGroup_4 = (Group) cGroup.eContents().get(4);

        private final Keyword cElseKeyword_4_0 = (Keyword) cGroup_4.eContents().get(0);

        private final Assignment cElseClauseAssignment_4_1 = (Assignment) cGroup_4.eContents()
                .get(1);

        private final RuleCall cElseClauseStatementParserRuleCall_4_1_0 = (RuleCall) cElseClauseAssignment_4_1
                .eContents().get(0);

        private final Keyword cFiKeyword_5 = (Keyword) cGroup.eContents().get(5);

        //IfStatement:
        //	'if' condition=Expression 'then' thenClause=Statement ('else' elseClause=Statement)? 'fi';
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //'if' condition=Expression 'then' thenClause=Statement ('else' elseClause=Statement)? 'fi'
        public Group getGroup() {
            return cGroup;
        }

        //'if'
        public Keyword getIfKeyword_0() {
            return cIfKeyword_0;
        }

        //condition=Expression
        public Assignment getConditionAssignment_1() {
            return cConditionAssignment_1;
        }

        //Expression
        public RuleCall getConditionExpressionParserRuleCall_1_0() {
            return cConditionExpressionParserRuleCall_1_0;
        }

        //'then'
        public Keyword getThenKeyword_2() {
            return cThenKeyword_2;
        }

        //thenClause=Statement
        public Assignment getThenClauseAssignment_3() {
            return cThenClauseAssignment_3;
        }

        //Statement
        public RuleCall getThenClauseStatementParserRuleCall_3_0() {
            return cThenClauseStatementParserRuleCall_3_0;
        }

        //('else' elseClause=Statement)?
        public Group getGroup_4() {
            return cGroup_4;
        }

        //'else'
        public Keyword getElseKeyword_4_0() {
            return cElseKeyword_4_0;
        }

        //elseClause=Statement
        public Assignment getElseClauseAssignment_4_1() {
            return cElseClauseAssignment_4_1;
        }

        //Statement
        public RuleCall getElseClauseStatementParserRuleCall_4_1_0() {
            return cElseClauseStatementParserRuleCall_4_1_0;
        }

        //'fi'
        public Keyword getFiKeyword_5() {
            return cFiKeyword_5;
        }
    }

    public class WhileStatementElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.WhileStatement");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Keyword cWhileKeyword_0 = (Keyword) cGroup.eContents().get(0);

        private final Assignment cConditionAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cConditionExpressionParserRuleCall_1_0 = (RuleCall) cConditionAssignment_1
                .eContents().get(0);

        private final Keyword cDoKeyword_2 = (Keyword) cGroup.eContents().get(2);

        private final Assignment cBodyAssignment_3 = (Assignment) cGroup.eContents().get(3);

        private final RuleCall cBodyStatementParserRuleCall_3_0 = (RuleCall) cBodyAssignment_3
                .eContents().get(0);

        private final Keyword cOdKeyword_4 = (Keyword) cGroup.eContents().get(4);

        //WhileStatement:
        //	'while' condition=Expression 'do' body=Statement 'od';
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //'while' condition=Expression 'do' body=Statement 'od'
        public Group getGroup() {
            return cGroup;
        }

        //'while'
        public Keyword getWhileKeyword_0() {
            return cWhileKeyword_0;
        }

        //condition=Expression
        public Assignment getConditionAssignment_1() {
            return cConditionAssignment_1;
        }

        //Expression
        public RuleCall getConditionExpressionParserRuleCall_1_0() {
            return cConditionExpressionParserRuleCall_1_0;
        }

        //'do'
        public Keyword getDoKeyword_2() {
            return cDoKeyword_2;
        }

        //body=Statement
        public Assignment getBodyAssignment_3() {
            return cBodyAssignment_3;
        }

        //Statement
        public RuleCall getBodyStatementParserRuleCall_3_0() {
            return cBodyStatementParserRuleCall_3_0;
        }

        //'od'
        public Keyword getOdKeyword_4() {
            return cOdKeyword_4;
        }
    }

    public class SwitchStatementElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.SwitchStatement");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Keyword cSwitchKeyword_0 = (Keyword) cGroup.eContents().get(0);

        private final Assignment cExpressionAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cExpressionExpressionParserRuleCall_1_0 = (RuleCall) cExpressionAssignment_1
                .eContents().get(0);

        private final Keyword cDoKeyword_2 = (Keyword) cGroup.eContents().get(2);

        private final Alternatives cAlternatives_3 = (Alternatives) cGroup.eContents().get(3);

        private final Group cGroup_3_0 = (Group) cAlternatives_3.eContents().get(0);

        private final Assignment cCasesAssignment_3_0_0 = (Assignment) cGroup_3_0.eContents()
                .get(0);

        private final RuleCall cCasesSwitchStatementCaseParserRuleCall_3_0_0_0 = (RuleCall) cCasesAssignment_3_0_0
                .eContents().get(0);

        private final Group cGroup_3_0_1 = (Group) cGroup_3_0.eContents().get(1);

        private final Keyword cDefaultKeyword_3_0_1_0 = (Keyword) cGroup_3_0_1.eContents().get(0);

        private final Assignment cDefaultBodyAssignment_3_0_1_1 = (Assignment) cGroup_3_0_1
                .eContents().get(1);

        private final RuleCall cDefaultBodyStatementParserRuleCall_3_0_1_1_0 = (RuleCall) cDefaultBodyAssignment_3_0_1_1
                .eContents().get(0);

        private final Group cGroup_3_1 = (Group) cAlternatives_3.eContents().get(1);

        private final Keyword cDefaultKeyword_3_1_0 = (Keyword) cGroup_3_1.eContents().get(0);

        private final Assignment cDefaultBodyAssignment_3_1_1 = (Assignment) cGroup_3_1.eContents()
                .get(1);

        private final RuleCall cDefaultBodyStatementParserRuleCall_3_1_1_0 = (RuleCall) cDefaultBodyAssignment_3_1_1
                .eContents().get(0);

        private final Keyword cOdKeyword_4 = (Keyword) cGroup.eContents().get(4);

        //SwitchStatement:
        //	'switch' expression=Expression
        //	'do' (cases+=SwitchStatementCase+ ('default' defaultBody=Statement)?
        //	|
        //	'default' defaultBody=Statement)
        //	'od';
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //'switch' expression=Expression 'do' (cases+=SwitchStatementCase+ ('default' defaultBody=Statement)? | 'default'
        //defaultBody=Statement) 'od'
        public Group getGroup() {
            return cGroup;
        }

        //'switch'
        public Keyword getSwitchKeyword_0() {
            return cSwitchKeyword_0;
        }

        //expression=Expression
        public Assignment getExpressionAssignment_1() {
            return cExpressionAssignment_1;
        }

        //Expression
        public RuleCall getExpressionExpressionParserRuleCall_1_0() {
            return cExpressionExpressionParserRuleCall_1_0;
        }

        //'do'
        public Keyword getDoKeyword_2() {
            return cDoKeyword_2;
        }

        //(cases+=SwitchStatementCase+ ('default' defaultBody=Statement)? | 'default' defaultBody=Statement)
        public Alternatives getAlternatives_3() {
            return cAlternatives_3;
        }

        //cases+=SwitchStatementCase+ ('default' defaultBody=Statement)?
        public Group getGroup_3_0() {
            return cGroup_3_0;
        }

        //cases+=SwitchStatementCase+
        public Assignment getCasesAssignment_3_0_0() {
            return cCasesAssignment_3_0_0;
        }

        //SwitchStatementCase
        public RuleCall getCasesSwitchStatementCaseParserRuleCall_3_0_0_0() {
            return cCasesSwitchStatementCaseParserRuleCall_3_0_0_0;
        }

        //('default' defaultBody=Statement)?
        public Group getGroup_3_0_1() {
            return cGroup_3_0_1;
        }

        //'default'
        public Keyword getDefaultKeyword_3_0_1_0() {
            return cDefaultKeyword_3_0_1_0;
        }

        //defaultBody=Statement
        public Assignment getDefaultBodyAssignment_3_0_1_1() {
            return cDefaultBodyAssignment_3_0_1_1;
        }

        //Statement
        public RuleCall getDefaultBodyStatementParserRuleCall_3_0_1_1_0() {
            return cDefaultBodyStatementParserRuleCall_3_0_1_1_0;
        }

        //'default' defaultBody=Statement
        public Group getGroup_3_1() {
            return cGroup_3_1;
        }

        //'default'
        public Keyword getDefaultKeyword_3_1_0() {
            return cDefaultKeyword_3_1_0;
        }

        //defaultBody=Statement
        public Assignment getDefaultBodyAssignment_3_1_1() {
            return cDefaultBodyAssignment_3_1_1;
        }

        //Statement
        public RuleCall getDefaultBodyStatementParserRuleCall_3_1_1_0() {
            return cDefaultBodyStatementParserRuleCall_3_1_1_0;
        }

        //'od'
        public Keyword getOdKeyword_4() {
            return cOdKeyword_4;
        }
    }

    public class SwitchStatementCaseElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.SwitchStatementCase");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Keyword cCaseKeyword_0 = (Keyword) cGroup.eContents().get(0);

        private final Assignment cValueAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cValueExpressionParserRuleCall_1_0 = (RuleCall) cValueAssignment_1
                .eContents().get(0);

        private final Keyword cThenKeyword_2 = (Keyword) cGroup.eContents().get(2);

        private final Assignment cBodyAssignment_3 = (Assignment) cGroup.eContents().get(3);

        private final RuleCall cBodyStatementParserRuleCall_3_0 = (RuleCall) cBodyAssignment_3
                .eContents().get(0);

        //SwitchStatementCase:
        //	'case' value=Expression 'then' body=Statement;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //'case' value=Expression 'then' body=Statement
        public Group getGroup() {
            return cGroup;
        }

        //'case'
        public Keyword getCaseKeyword_0() {
            return cCaseKeyword_0;
        }

        //value=Expression
        public Assignment getValueAssignment_1() {
            return cValueAssignment_1;
        }

        //Expression
        public RuleCall getValueExpressionParserRuleCall_1_0() {
            return cValueExpressionParserRuleCall_1_0;
        }

        //'then'
        public Keyword getThenKeyword_2() {
            return cThenKeyword_2;
        }

        //body=Statement
        public Assignment getBodyAssignment_3() {
            return cBodyAssignment_3;
        }

        //Statement
        public RuleCall getBodyStatementParserRuleCall_3_0() {
            return cBodyStatementParserRuleCall_3_0;
        }
    }

    public class ParStatementElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.ParStatement");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Keyword cParKeyword_0 = (Keyword) cGroup.eContents().get(0);

        private final Assignment cClausesAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cClausesStatementParserRuleCall_1_0 = (RuleCall) cClausesAssignment_1
                .eContents().get(0);

        private final Group cGroup_2 = (Group) cGroup.eContents().get(2);

        private final Keyword cAndKeyword_2_0 = (Keyword) cGroup_2.eContents().get(0);

        private final Assignment cClausesAssignment_2_1 = (Assignment) cGroup_2.eContents().get(1);

        private final RuleCall cClausesStatementParserRuleCall_2_1_0 = (RuleCall) cClausesAssignment_2_1
                .eContents().get(0);

        private final Keyword cRapKeyword_3 = (Keyword) cGroup.eContents().get(3);

        //ParStatement:
        //	'par' clauses+=Statement ('and' clauses+=Statement)* 'rap';
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //'par' clauses+=Statement ('and' clauses+=Statement)* 'rap'
        public Group getGroup() {
            return cGroup;
        }

        //'par'
        public Keyword getParKeyword_0() {
            return cParKeyword_0;
        }

        //clauses+=Statement
        public Assignment getClausesAssignment_1() {
            return cClausesAssignment_1;
        }

        //Statement
        public RuleCall getClausesStatementParserRuleCall_1_0() {
            return cClausesStatementParserRuleCall_1_0;
        }

        //('and' clauses+=Statement)*
        public Group getGroup_2() {
            return cGroup_2;
        }

        //'and'
        public Keyword getAndKeyword_2_0() {
            return cAndKeyword_2_0;
        }

        //clauses+=Statement
        public Assignment getClausesAssignment_2_1() {
            return cClausesAssignment_2_1;
        }

        //Statement
        public RuleCall getClausesStatementParserRuleCall_2_1_0() {
            return cClausesStatementParserRuleCall_2_1_0;
        }

        //'rap'
        public Keyword getRapKeyword_3() {
            return cRapKeyword_3;
        }
    }

    public class SelStatementElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.SelStatement");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Keyword cSelKeyword_0 = (Keyword) cGroup.eContents().get(0);

        private final Assignment cClausesAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cClausesStatementParserRuleCall_1_0 = (RuleCall) cClausesAssignment_1
                .eContents().get(0);

        private final Group cGroup_2 = (Group) cGroup.eContents().get(2);

        private final Keyword cOrKeyword_2_0 = (Keyword) cGroup_2.eContents().get(0);

        private final Assignment cClausesAssignment_2_1 = (Assignment) cGroup_2.eContents().get(1);

        private final RuleCall cClausesStatementParserRuleCall_2_1_0 = (RuleCall) cClausesAssignment_2_1
                .eContents().get(0);

        private final Keyword cLesKeyword_3 = (Keyword) cGroup.eContents().get(3);

        //SelStatement:
        //	'sel' clauses+=Statement ('or' clauses+=Statement)* 'les';
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //'sel' clauses+=Statement ('or' clauses+=Statement)* 'les'
        public Group getGroup() {
            return cGroup;
        }

        //'sel'
        public Keyword getSelKeyword_0() {
            return cSelKeyword_0;
        }

        //clauses+=Statement
        public Assignment getClausesAssignment_1() {
            return cClausesAssignment_1;
        }

        //Statement
        public RuleCall getClausesStatementParserRuleCall_1_0() {
            return cClausesStatementParserRuleCall_1_0;
        }

        //('or' clauses+=Statement)*
        public Group getGroup_2() {
            return cGroup_2;
        }

        //'or'
        public Keyword getOrKeyword_2_0() {
            return cOrKeyword_2_0;
        }

        //clauses+=Statement
        public Assignment getClausesAssignment_2_1() {
            return cClausesAssignment_2_1;
        }

        //Statement
        public RuleCall getClausesStatementParserRuleCall_2_1_0() {
            return cClausesStatementParserRuleCall_2_1_0;
        }

        //'les'
        public Keyword getLesKeyword_3() {
            return cLesKeyword_3;
        }
    }

    public class AbortStatementElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.AbortStatement");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Keyword cAbortKeyword_0 = (Keyword) cGroup.eContents().get(0);

        private final Assignment cNormalClauseAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cNormalClauseStatementParserRuleCall_1_0 = (RuleCall) cNormalClauseAssignment_1
                .eContents().get(0);

        private final Keyword cWithKeyword_2 = (Keyword) cGroup.eContents().get(2);

        private final Assignment cAbortingClauseAssignment_3 = (Assignment) cGroup.eContents()
                .get(3);

        private final RuleCall cAbortingClauseStatementSingleParserRuleCall_3_0 = (RuleCall) cAbortingClauseAssignment_3
                .eContents().get(0);

        //AbortStatement:
        //	'abort' normalClause=Statement 'with' abortingClause=StatementSingle;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //'abort' normalClause=Statement 'with' abortingClause=StatementSingle
        public Group getGroup() {
            return cGroup;
        }

        //'abort'
        public Keyword getAbortKeyword_0() {
            return cAbortKeyword_0;
        }

        //normalClause=Statement
        public Assignment getNormalClauseAssignment_1() {
            return cNormalClauseAssignment_1;
        }

        //Statement
        public RuleCall getNormalClauseStatementParserRuleCall_1_0() {
            return cNormalClauseStatementParserRuleCall_1_0;
        }

        //'with'
        public Keyword getWithKeyword_2() {
            return cWithKeyword_2;
        }

        //abortingClause=StatementSingle
        public Assignment getAbortingClauseAssignment_3() {
            return cAbortingClauseAssignment_3;
        }

        //StatementSingle
        public RuleCall getAbortingClauseStatementSingleParserRuleCall_3_0() {
            return cAbortingClauseStatementSingleParserRuleCall_3_0;
        }
    }

    public class InterruptStatementElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.InterruptStatement");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Keyword cInterruptKeyword_0 = (Keyword) cGroup.eContents().get(0);

        private final Assignment cNormalClauseAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cNormalClauseStatementParserRuleCall_1_0 = (RuleCall) cNormalClauseAssignment_1
                .eContents().get(0);

        private final Keyword cWithKeyword_2 = (Keyword) cGroup.eContents().get(2);

        private final Assignment cInterruptingClauseAssignment_3 = (Assignment) cGroup.eContents()
                .get(3);

        private final RuleCall cInterruptingClauseStatementSingleParserRuleCall_3_0 = (RuleCall) cInterruptingClauseAssignment_3
                .eContents().get(0);

        //InterruptStatement:
        //	'interrupt' normalClause=Statement 'with' interruptingClause=StatementSingle;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //'interrupt' normalClause=Statement 'with' interruptingClause=StatementSingle
        public Group getGroup() {
            return cGroup;
        }

        //'interrupt'
        public Keyword getInterruptKeyword_0() {
            return cInterruptKeyword_0;
        }

        //normalClause=Statement
        public Assignment getNormalClauseAssignment_1() {
            return cNormalClauseAssignment_1;
        }

        //Statement
        public RuleCall getNormalClauseStatementParserRuleCall_1_0() {
            return cNormalClauseStatementParserRuleCall_1_0;
        }

        //'with'
        public Keyword getWithKeyword_2() {
            return cWithKeyword_2;
        }

        //interruptingClause=StatementSingle
        public Assignment getInterruptingClauseAssignment_3() {
            return cInterruptingClauseAssignment_3;
        }

        //StatementSingle
        public RuleCall getInterruptingClauseStatementSingleParserRuleCall_3_0() {
            return cInterruptingClauseStatementSingleParserRuleCall_3_0;
        }
    }

    public class ProcessMethodCallElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.ProcessMethodCall");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Assignment cMethodAssignment_0 = (Assignment) cGroup.eContents().get(0);

        private final RuleCall cMethodIDENTIFIERParserRuleCall_0_0 = (RuleCall) cMethodAssignment_0
                .eContents().get(0);

        private final Keyword cLeftParenthesisKeyword_1 = (Keyword) cGroup.eContents().get(1);

        private final Group cGroup_2 = (Group) cGroup.eContents().get(2);

        private final Assignment cInputArgumentsAssignment_2_0 = (Assignment) cGroup_2.eContents()
                .get(0);

        private final RuleCall cInputArgumentsExpressionParserRuleCall_2_0_0 = (RuleCall) cInputArgumentsAssignment_2_0
                .eContents().get(0);

        private final Group cGroup_2_1 = (Group) cGroup_2.eContents().get(1);

        private final Keyword cCommaKeyword_2_1_0 = (Keyword) cGroup_2_1.eContents().get(0);

        private final Assignment cInputArgumentsAssignment_2_1_1 = (Assignment) cGroup_2_1
                .eContents().get(1);

        private final RuleCall cInputArgumentsExpressionParserRuleCall_2_1_1_0 = (RuleCall) cInputArgumentsAssignment_2_1_1
                .eContents().get(0);

        private final Keyword cRightParenthesisKeyword_3 = (Keyword) cGroup.eContents().get(3);

        private final Keyword cLeftParenthesisKeyword_4 = (Keyword) cGroup.eContents().get(4);

        private final Group cGroup_5 = (Group) cGroup.eContents().get(5);

        private final Assignment cOutputVariablesAssignment_5_0 = (Assignment) cGroup_5.eContents()
                .get(0);

        private final RuleCall cOutputVariablesOutputVariableParserRuleCall_5_0_0 = (RuleCall) cOutputVariablesAssignment_5_0
                .eContents().get(0);

        private final Group cGroup_5_1 = (Group) cGroup_5.eContents().get(1);

        private final Keyword cCommaKeyword_5_1_0 = (Keyword) cGroup_5_1.eContents().get(0);

        private final Assignment cOutputVariablesAssignment_5_1_1 = (Assignment) cGroup_5_1
                .eContents().get(1);

        private final RuleCall cOutputVariablesOutputVariableParserRuleCall_5_1_1_0 = (RuleCall) cOutputVariablesAssignment_5_1_1
                .eContents().get(0);

        private final Keyword cRightParenthesisKeyword_6 = (Keyword) cGroup.eContents().get(6);

        //ProcessMethodCall:
        //	method=IDENTIFIER //method=[ProcessMethod|IDENTIFIER] 
        //	'(' (inputArguments+=Expression (',' inputArguments+=Expression)*)? ')'
        //	'(' (outputVariables+=OutputVariable (',' outputVariables+=OutputVariable)*)? ')';
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //method=IDENTIFIER //method=[ProcessMethod|IDENTIFIER] 
        //'(' (inputArguments+=Expression (',' inputArguments+=Expression)*)? ')' '(' (outputVariables+=OutputVariable (','
        //outputVariables+=OutputVariable)*)? ')'
        public Group getGroup() {
            return cGroup;
        }

        //method=IDENTIFIER
        public Assignment getMethodAssignment_0() {
            return cMethodAssignment_0;
        }

        //IDENTIFIER
        public RuleCall getMethodIDENTIFIERParserRuleCall_0_0() {
            return cMethodIDENTIFIERParserRuleCall_0_0;
        }

        ////method=[ProcessMethod|IDENTIFIER] 
        //'('
        public Keyword getLeftParenthesisKeyword_1() {
            return cLeftParenthesisKeyword_1;
        }

        //(inputArguments+=Expression (',' inputArguments+=Expression)*)?
        public Group getGroup_2() {
            return cGroup_2;
        }

        //inputArguments+=Expression
        public Assignment getInputArgumentsAssignment_2_0() {
            return cInputArgumentsAssignment_2_0;
        }

        //Expression
        public RuleCall getInputArgumentsExpressionParserRuleCall_2_0_0() {
            return cInputArgumentsExpressionParserRuleCall_2_0_0;
        }

        //(',' inputArguments+=Expression)*
        public Group getGroup_2_1() {
            return cGroup_2_1;
        }

        //','
        public Keyword getCommaKeyword_2_1_0() {
            return cCommaKeyword_2_1_0;
        }

        //inputArguments+=Expression
        public Assignment getInputArgumentsAssignment_2_1_1() {
            return cInputArgumentsAssignment_2_1_1;
        }

        //Expression
        public RuleCall getInputArgumentsExpressionParserRuleCall_2_1_1_0() {
            return cInputArgumentsExpressionParserRuleCall_2_1_1_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_3() {
            return cRightParenthesisKeyword_3;
        }

        //'('
        public Keyword getLeftParenthesisKeyword_4() {
            return cLeftParenthesisKeyword_4;
        }

        //(outputVariables+=OutputVariable (',' outputVariables+=OutputVariable)*)?
        public Group getGroup_5() {
            return cGroup_5;
        }

        //outputVariables+=OutputVariable
        public Assignment getOutputVariablesAssignment_5_0() {
            return cOutputVariablesAssignment_5_0;
        }

        //OutputVariable
        public RuleCall getOutputVariablesOutputVariableParserRuleCall_5_0_0() {
            return cOutputVariablesOutputVariableParserRuleCall_5_0_0;
        }

        //(',' outputVariables+=OutputVariable)*
        public Group getGroup_5_1() {
            return cGroup_5_1;
        }

        //','
        public Keyword getCommaKeyword_5_1_0() {
            return cCommaKeyword_5_1_0;
        }

        //outputVariables+=OutputVariable
        public Assignment getOutputVariablesAssignment_5_1_1() {
            return cOutputVariablesAssignment_5_1_1;
        }

        //OutputVariable
        public RuleCall getOutputVariablesOutputVariableParserRuleCall_5_1_1_0() {
            return cOutputVariablesOutputVariableParserRuleCall_5_1_1_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_6() {
            return cRightParenthesisKeyword_6;
        }
    }

    public class BracketStartStatementElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.BracketStartStatement");

        private final Alternatives cAlternatives = (Alternatives) rule.eContents().get(1);

        private final RuleCall cBracketedArgumentStartExpressionStatementParserRuleCall_0 = (RuleCall) cAlternatives
                .eContents().get(0);

        private final RuleCall cStatementSequenceRoundBracketParserRuleCall_1 = (RuleCall) cAlternatives
                .eContents().get(1);

        //BracketStartStatement Statement:
        //	=> BracketedArgumentStartExpressionStatement | StatementSequenceRoundBracket
        //	// BracketedDataMethodCallExpressionStatement should occur before SubStatement  
        //;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //=> BracketedArgumentStartExpressionStatement | StatementSequenceRoundBracket
        public Alternatives getAlternatives() {
            return cAlternatives;
        }

        //=> BracketedArgumentStartExpressionStatement
        public RuleCall getBracketedArgumentStartExpressionStatementParserRuleCall_0() {
            return cBracketedArgumentStartExpressionStatementParserRuleCall_0;
        }

        //StatementSequenceRoundBracket
        public RuleCall getStatementSequenceRoundBracketParserRuleCall_1() {
            return cStatementSequenceRoundBracketParserRuleCall_1;
        }
    }

    public class StatementSequenceRoundBracketElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.StatementSequenceRoundBracket");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Keyword cLeftParenthesisKeyword_0 = (Keyword) cGroup.eContents().get(0);

        private final Assignment cStatementsAssignment_1 = (Assignment) cGroup.eContents().get(1);

        private final RuleCall cStatementsStatementSingleParserRuleCall_1_0 = (RuleCall) cStatementsAssignment_1
                .eContents().get(0);

        private final Group cGroup_2 = (Group) cGroup.eContents().get(2);

        private final Keyword cSemicolonKeyword_2_0 = (Keyword) cGroup_2.eContents().get(0);

        private final Assignment cStatementsAssignment_2_1 = (Assignment) cGroup_2.eContents()
                .get(1);

        private final RuleCall cStatementsStatementSingleParserRuleCall_2_1_0 = (RuleCall) cStatementsAssignment_2_1
                .eContents().get(0);

        private final Keyword cRightParenthesisKeyword_3 = (Keyword) cGroup.eContents().get(3);

        //StatementSequenceRoundBracket:
        //	'(' statements+=StatementSingle (';' statements+=StatementSingle)* ')';
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //'(' statements+=StatementSingle (';' statements+=StatementSingle)* ')'
        public Group getGroup() {
            return cGroup;
        }

        //'('
        public Keyword getLeftParenthesisKeyword_0() {
            return cLeftParenthesisKeyword_0;
        }

        //statements+=StatementSingle
        public Assignment getStatementsAssignment_1() {
            return cStatementsAssignment_1;
        }

        //StatementSingle
        public RuleCall getStatementsStatementSingleParserRuleCall_1_0() {
            return cStatementsStatementSingleParserRuleCall_1_0;
        }

        //(';' statements+=StatementSingle)*
        public Group getGroup_2() {
            return cGroup_2;
        }

        //';'
        public Keyword getSemicolonKeyword_2_0() {
            return cSemicolonKeyword_2_0;
        }

        //statements+=StatementSingle
        public Assignment getStatementsAssignment_2_1() {
            return cStatementsAssignment_2_1;
        }

        //StatementSingle
        public RuleCall getStatementsStatementSingleParserRuleCall_2_1_0() {
            return cStatementsStatementSingleParserRuleCall_2_1_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_3() {
            return cRightParenthesisKeyword_3;
        }
    }

    public class BracketedArgumentStartExpressionStatementElements
            extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.BracketedArgumentStartExpressionStatement");

        private final Assignment cExpressionAssignment = (Assignment) rule.eContents().get(1);

        private final RuleCall cExpressionBracketedArgumentStartExpressionSingleParserRuleCall_0 = (RuleCall) cExpressionAssignment
                .eContents().get(0);

        //BracketedArgumentStartExpressionStatement ExpressionStatement:
        //	expression=BracketedArgumentStartExpressionSingle;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //expression=BracketedArgumentStartExpressionSingle
        public Assignment getExpressionAssignment() {
            return cExpressionAssignment;
        }

        //BracketedArgumentStartExpressionSingle
        public RuleCall getExpressionBracketedArgumentStartExpressionSingleParserRuleCall_0() {
            return cExpressionBracketedArgumentStartExpressionSingleParserRuleCall_0;
        }
    }

    public class BracketedArgumentStartExpressionSingleElements
            extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.BracketedArgumentStartExpressionSingle");

        private final RuleCall cBracketedArgumentStartExpressionLevel1ParserRuleCall = (RuleCall) rule
                .eContents().get(1);

        //BracketedArgumentStartExpressionSingle Expression:
        //	BracketedArgumentStartExpressionLevel1;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //BracketedArgumentStartExpressionLevel1
        public RuleCall getBracketedArgumentStartExpressionLevel1ParserRuleCall() {
            return cBracketedArgumentStartExpressionLevel1ParserRuleCall;
        }
    }

    public class BracketedArgumentStartExpressionLevel1Elements
            extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.BracketedArgumentStartExpressionLevel1");

        private final RuleCall cBracketedArgumentStartExpressionLevel2ParserRuleCall = (RuleCall) rule
                .eContents().get(1);

        //BracketedArgumentStartExpressionLevel1 Expression:
        //	BracketedArgumentStartExpressionLevel2;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //BracketedArgumentStartExpressionLevel2
        public RuleCall getBracketedArgumentStartExpressionLevel2ParserRuleCall() {
            return cBracketedArgumentStartExpressionLevel2ParserRuleCall;
        }
    }

    public class BracketedArgumentStartExpressionLevel2Elements
            extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.BracketedArgumentStartExpressionLevel2");

        private final Alternatives cAlternatives = (Alternatives) rule.eContents().get(1);

        private final Group cGroup_0 = (Group) cAlternatives.eContents().get(0);

        private final RuleCall cBracketedArgumentStartExpressionLevel3ParserRuleCall_0_0 = (RuleCall) cGroup_0
                .eContents().get(0);

        private final Group cGroup_0_1 = (Group) cGroup_0.eContents().get(1);

        private final Action cBinaryOperatorExpressionLeftOperandAction_0_1_0 = (Action) cGroup_0_1
                .eContents().get(0);

        private final Assignment cNameAssignment_0_1_1 = (Assignment) cGroup_0_1.eContents().get(1);

        private final RuleCall cNameBinaryOperatorLevel2EnumRuleCall_0_1_1_0 = (RuleCall) cNameAssignment_0_1_1
                .eContents().get(0);

        private final Assignment cRightOperandAssignment_0_1_2 = (Assignment) cGroup_0_1.eContents()
                .get(2);

        private final RuleCall cRightOperandExpressionLevel3ParserRuleCall_0_1_2_0 = (RuleCall) cRightOperandAssignment_0_1_2
                .eContents().get(0);

        private final Group cGroup_1 = (Group) cAlternatives.eContents().get(1);

        private final RuleCall cExpressionSequenceRoundBracketParserRuleCall_1_0 = (RuleCall) cGroup_1
                .eContents().get(0);

        private final Group cGroup_1_1 = (Group) cGroup_1.eContents().get(1);

        private final Action cBinaryOperatorExpressionLeftOperandAction_1_1_0 = (Action) cGroup_1_1
                .eContents().get(0);

        private final Assignment cNameAssignment_1_1_1 = (Assignment) cGroup_1_1.eContents().get(1);

        private final RuleCall cNameBinaryOperatorLevel2EnumRuleCall_1_1_1_0 = (RuleCall) cNameAssignment_1_1_1
                .eContents().get(0);

        private final Assignment cRightOperandAssignment_1_1_2 = (Assignment) cGroup_1_1.eContents()
                .get(2);

        private final RuleCall cRightOperandExpressionLevel3ParserRuleCall_1_1_2_0 = (RuleCall) cRightOperandAssignment_1_1_2
                .eContents().get(0);

        //BracketedArgumentStartExpressionLevel2 Expression:
        //	=>
        //	BracketedArgumentStartExpressionLevel3 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel2
        //	rightOperand=ExpressionLevel3)*
        //	| ExpressionSequenceRoundBracket ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel2
        //	rightOperand=ExpressionLevel3)+;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //// Left associativity
        //=> BracketedArgumentStartExpressionLevel3 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel2
        //rightOperand=ExpressionLevel3)* | ExpressionSequenceRoundBracket ({BinaryOperatorExpression.leftOperand=current}
        //name=BinaryOperatorLevel2 rightOperand=ExpressionLevel3)+
        public Alternatives getAlternatives() {
            return cAlternatives;
        }

        //// Left associativity
        //=> BracketedArgumentStartExpressionLevel3 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel2
        //rightOperand=ExpressionLevel3)*
        public Group getGroup_0() {
            return cGroup_0;
        }

        //=> BracketedArgumentStartExpressionLevel3
        public RuleCall getBracketedArgumentStartExpressionLevel3ParserRuleCall_0_0() {
            return cBracketedArgumentStartExpressionLevel3ParserRuleCall_0_0;
        }

        //({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel2 rightOperand=ExpressionLevel3)*
        public Group getGroup_0_1() {
            return cGroup_0_1;
        }

        //{BinaryOperatorExpression.leftOperand=current}
        public Action getBinaryOperatorExpressionLeftOperandAction_0_1_0() {
            return cBinaryOperatorExpressionLeftOperandAction_0_1_0;
        }

        //name=BinaryOperatorLevel2
        public Assignment getNameAssignment_0_1_1() {
            return cNameAssignment_0_1_1;
        }

        //BinaryOperatorLevel2
        public RuleCall getNameBinaryOperatorLevel2EnumRuleCall_0_1_1_0() {
            return cNameBinaryOperatorLevel2EnumRuleCall_0_1_1_0;
        }

        //rightOperand=ExpressionLevel3
        public Assignment getRightOperandAssignment_0_1_2() {
            return cRightOperandAssignment_0_1_2;
        }

        //ExpressionLevel3
        public RuleCall getRightOperandExpressionLevel3ParserRuleCall_0_1_2_0() {
            return cRightOperandExpressionLevel3ParserRuleCall_0_1_2_0;
        }

        //ExpressionSequenceRoundBracket ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel2
        //rightOperand=ExpressionLevel3)+
        public Group getGroup_1() {
            return cGroup_1;
        }

        //ExpressionSequenceRoundBracket
        public RuleCall getExpressionSequenceRoundBracketParserRuleCall_1_0() {
            return cExpressionSequenceRoundBracketParserRuleCall_1_0;
        }

        //({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel2 rightOperand=ExpressionLevel3)+
        public Group getGroup_1_1() {
            return cGroup_1_1;
        }

        //{BinaryOperatorExpression.leftOperand=current}
        public Action getBinaryOperatorExpressionLeftOperandAction_1_1_0() {
            return cBinaryOperatorExpressionLeftOperandAction_1_1_0;
        }

        //name=BinaryOperatorLevel2
        public Assignment getNameAssignment_1_1_1() {
            return cNameAssignment_1_1_1;
        }

        //BinaryOperatorLevel2
        public RuleCall getNameBinaryOperatorLevel2EnumRuleCall_1_1_1_0() {
            return cNameBinaryOperatorLevel2EnumRuleCall_1_1_1_0;
        }

        //rightOperand=ExpressionLevel3
        public Assignment getRightOperandAssignment_1_1_2() {
            return cRightOperandAssignment_1_1_2;
        }

        //ExpressionLevel3
        public RuleCall getRightOperandExpressionLevel3ParserRuleCall_1_1_2_0() {
            return cRightOperandExpressionLevel3ParserRuleCall_1_1_2_0;
        }
    }

    public class BracketedArgumentStartExpressionLevel3Elements
            extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.BracketedArgumentStartExpressionLevel3");

        private final Alternatives cAlternatives = (Alternatives) rule.eContents().get(1);

        private final Group cGroup_0 = (Group) cAlternatives.eContents().get(0);

        private final RuleCall cBracketedArgumentStartExpressionLevel4ParserRuleCall_0_0 = (RuleCall) cGroup_0
                .eContents().get(0);

        private final Group cGroup_0_1 = (Group) cGroup_0.eContents().get(1);

        private final Action cBinaryOperatorExpressionLeftOperandAction_0_1_0 = (Action) cGroup_0_1
                .eContents().get(0);

        private final Assignment cNameAssignment_0_1_1 = (Assignment) cGroup_0_1.eContents().get(1);

        private final RuleCall cNameBinaryOperatorLevel3EnumRuleCall_0_1_1_0 = (RuleCall) cNameAssignment_0_1_1
                .eContents().get(0);

        private final Assignment cRightOperandAssignment_0_1_2 = (Assignment) cGroup_0_1.eContents()
                .get(2);

        private final RuleCall cRightOperandExpressionLevel4ParserRuleCall_0_1_2_0 = (RuleCall) cRightOperandAssignment_0_1_2
                .eContents().get(0);

        private final Group cGroup_1 = (Group) cAlternatives.eContents().get(1);

        private final RuleCall cExpressionSequenceRoundBracketParserRuleCall_1_0 = (RuleCall) cGroup_1
                .eContents().get(0);

        private final Group cGroup_1_1 = (Group) cGroup_1.eContents().get(1);

        private final Action cBinaryOperatorExpressionLeftOperandAction_1_1_0 = (Action) cGroup_1_1
                .eContents().get(0);

        private final Assignment cNameAssignment_1_1_1 = (Assignment) cGroup_1_1.eContents().get(1);

        private final RuleCall cNameBinaryOperatorLevel3EnumRuleCall_1_1_1_0 = (RuleCall) cNameAssignment_1_1_1
                .eContents().get(0);

        private final Assignment cRightOperandAssignment_1_1_2 = (Assignment) cGroup_1_1.eContents()
                .get(2);

        private final RuleCall cRightOperandExpressionLevel4ParserRuleCall_1_1_2_0 = (RuleCall) cRightOperandAssignment_1_1_2
                .eContents().get(0);

        //BracketedArgumentStartExpressionLevel3 Expression:
        //	=>
        //	BracketedArgumentStartExpressionLevel4 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel3
        //	rightOperand=ExpressionLevel4)*
        //	| ExpressionSequenceRoundBracket ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel3
        //	rightOperand=ExpressionLevel4)+;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //// Left associativity
        //=> BracketedArgumentStartExpressionLevel4 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel3
        //rightOperand=ExpressionLevel4)* | ExpressionSequenceRoundBracket ({BinaryOperatorExpression.leftOperand=current}
        //name=BinaryOperatorLevel3 rightOperand=ExpressionLevel4)+
        public Alternatives getAlternatives() {
            return cAlternatives;
        }

        //// Left associativity
        //=> BracketedArgumentStartExpressionLevel4 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel3
        //rightOperand=ExpressionLevel4)*
        public Group getGroup_0() {
            return cGroup_0;
        }

        //=> BracketedArgumentStartExpressionLevel4
        public RuleCall getBracketedArgumentStartExpressionLevel4ParserRuleCall_0_0() {
            return cBracketedArgumentStartExpressionLevel4ParserRuleCall_0_0;
        }

        //({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel3 rightOperand=ExpressionLevel4)*
        public Group getGroup_0_1() {
            return cGroup_0_1;
        }

        //{BinaryOperatorExpression.leftOperand=current}
        public Action getBinaryOperatorExpressionLeftOperandAction_0_1_0() {
            return cBinaryOperatorExpressionLeftOperandAction_0_1_0;
        }

        //name=BinaryOperatorLevel3
        public Assignment getNameAssignment_0_1_1() {
            return cNameAssignment_0_1_1;
        }

        //BinaryOperatorLevel3
        public RuleCall getNameBinaryOperatorLevel3EnumRuleCall_0_1_1_0() {
            return cNameBinaryOperatorLevel3EnumRuleCall_0_1_1_0;
        }

        //rightOperand=ExpressionLevel4
        public Assignment getRightOperandAssignment_0_1_2() {
            return cRightOperandAssignment_0_1_2;
        }

        //ExpressionLevel4
        public RuleCall getRightOperandExpressionLevel4ParserRuleCall_0_1_2_0() {
            return cRightOperandExpressionLevel4ParserRuleCall_0_1_2_0;
        }

        //ExpressionSequenceRoundBracket ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel3
        //rightOperand=ExpressionLevel4)+
        public Group getGroup_1() {
            return cGroup_1;
        }

        //ExpressionSequenceRoundBracket
        public RuleCall getExpressionSequenceRoundBracketParserRuleCall_1_0() {
            return cExpressionSequenceRoundBracketParserRuleCall_1_0;
        }

        //({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel3 rightOperand=ExpressionLevel4)+
        public Group getGroup_1_1() {
            return cGroup_1_1;
        }

        //{BinaryOperatorExpression.leftOperand=current}
        public Action getBinaryOperatorExpressionLeftOperandAction_1_1_0() {
            return cBinaryOperatorExpressionLeftOperandAction_1_1_0;
        }

        //name=BinaryOperatorLevel3
        public Assignment getNameAssignment_1_1_1() {
            return cNameAssignment_1_1_1;
        }

        //BinaryOperatorLevel3
        public RuleCall getNameBinaryOperatorLevel3EnumRuleCall_1_1_1_0() {
            return cNameBinaryOperatorLevel3EnumRuleCall_1_1_1_0;
        }

        //rightOperand=ExpressionLevel4
        public Assignment getRightOperandAssignment_1_1_2() {
            return cRightOperandAssignment_1_1_2;
        }

        //ExpressionLevel4
        public RuleCall getRightOperandExpressionLevel4ParserRuleCall_1_1_2_0() {
            return cRightOperandExpressionLevel4ParserRuleCall_1_1_2_0;
        }
    }

    public class BracketedArgumentStartExpressionLevel4Elements
            extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.BracketedArgumentStartExpressionLevel4");

        private final Alternatives cAlternatives = (Alternatives) rule.eContents().get(1);

        private final Group cGroup_0 = (Group) cAlternatives.eContents().get(0);

        private final RuleCall cBracketedArgumentStartExpressionLevel5ParserRuleCall_0_0 = (RuleCall) cGroup_0
                .eContents().get(0);

        private final Group cGroup_0_1 = (Group) cGroup_0.eContents().get(1);

        private final Action cBinaryOperatorExpressionLeftOperandAction_0_1_0 = (Action) cGroup_0_1
                .eContents().get(0);

        private final Assignment cNameAssignment_0_1_1 = (Assignment) cGroup_0_1.eContents().get(1);

        private final RuleCall cNameBinaryOperatorLevel4EnumRuleCall_0_1_1_0 = (RuleCall) cNameAssignment_0_1_1
                .eContents().get(0);

        private final Assignment cRightOperandAssignment_0_1_2 = (Assignment) cGroup_0_1.eContents()
                .get(2);

        private final RuleCall cRightOperandExpressionLevel5ParserRuleCall_0_1_2_0 = (RuleCall) cRightOperandAssignment_0_1_2
                .eContents().get(0);

        private final Group cGroup_1 = (Group) cAlternatives.eContents().get(1);

        private final RuleCall cExpressionSequenceRoundBracketParserRuleCall_1_0 = (RuleCall) cGroup_1
                .eContents().get(0);

        private final Group cGroup_1_1 = (Group) cGroup_1.eContents().get(1);

        private final Action cBinaryOperatorExpressionLeftOperandAction_1_1_0 = (Action) cGroup_1_1
                .eContents().get(0);

        private final Assignment cNameAssignment_1_1_1 = (Assignment) cGroup_1_1.eContents().get(1);

        private final RuleCall cNameBinaryOperatorLevel4EnumRuleCall_1_1_1_0 = (RuleCall) cNameAssignment_1_1_1
                .eContents().get(0);

        private final Assignment cRightOperandAssignment_1_1_2 = (Assignment) cGroup_1_1.eContents()
                .get(2);

        private final RuleCall cRightOperandExpressionLevel5ParserRuleCall_1_1_2_0 = (RuleCall) cRightOperandAssignment_1_1_2
                .eContents().get(0);

        //BracketedArgumentStartExpressionLevel4 Expression:
        //	=>
        //	BracketedArgumentStartExpressionLevel5 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel4
        //	rightOperand=ExpressionLevel5)*
        //	| ExpressionSequenceRoundBracket ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel4
        //	rightOperand=ExpressionLevel5)+;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //// Left associativity
        //=> BracketedArgumentStartExpressionLevel5 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel4
        //rightOperand=ExpressionLevel5)* | ExpressionSequenceRoundBracket ({BinaryOperatorExpression.leftOperand=current}
        //name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5)+
        public Alternatives getAlternatives() {
            return cAlternatives;
        }

        //// Left associativity
        //=> BracketedArgumentStartExpressionLevel5 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel4
        //rightOperand=ExpressionLevel5)*
        public Group getGroup_0() {
            return cGroup_0;
        }

        //=> BracketedArgumentStartExpressionLevel5
        public RuleCall getBracketedArgumentStartExpressionLevel5ParserRuleCall_0_0() {
            return cBracketedArgumentStartExpressionLevel5ParserRuleCall_0_0;
        }

        //({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5)*
        public Group getGroup_0_1() {
            return cGroup_0_1;
        }

        //{BinaryOperatorExpression.leftOperand=current}
        public Action getBinaryOperatorExpressionLeftOperandAction_0_1_0() {
            return cBinaryOperatorExpressionLeftOperandAction_0_1_0;
        }

        //name=BinaryOperatorLevel4
        public Assignment getNameAssignment_0_1_1() {
            return cNameAssignment_0_1_1;
        }

        //BinaryOperatorLevel4
        public RuleCall getNameBinaryOperatorLevel4EnumRuleCall_0_1_1_0() {
            return cNameBinaryOperatorLevel4EnumRuleCall_0_1_1_0;
        }

        //rightOperand=ExpressionLevel5
        public Assignment getRightOperandAssignment_0_1_2() {
            return cRightOperandAssignment_0_1_2;
        }

        //ExpressionLevel5
        public RuleCall getRightOperandExpressionLevel5ParserRuleCall_0_1_2_0() {
            return cRightOperandExpressionLevel5ParserRuleCall_0_1_2_0;
        }

        //ExpressionSequenceRoundBracket ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel4
        //rightOperand=ExpressionLevel5)+
        public Group getGroup_1() {
            return cGroup_1;
        }

        //ExpressionSequenceRoundBracket
        public RuleCall getExpressionSequenceRoundBracketParserRuleCall_1_0() {
            return cExpressionSequenceRoundBracketParserRuleCall_1_0;
        }

        //({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel4 rightOperand=ExpressionLevel5)+
        public Group getGroup_1_1() {
            return cGroup_1_1;
        }

        //{BinaryOperatorExpression.leftOperand=current}
        public Action getBinaryOperatorExpressionLeftOperandAction_1_1_0() {
            return cBinaryOperatorExpressionLeftOperandAction_1_1_0;
        }

        //name=BinaryOperatorLevel4
        public Assignment getNameAssignment_1_1_1() {
            return cNameAssignment_1_1_1;
        }

        //BinaryOperatorLevel4
        public RuleCall getNameBinaryOperatorLevel4EnumRuleCall_1_1_1_0() {
            return cNameBinaryOperatorLevel4EnumRuleCall_1_1_1_0;
        }

        //rightOperand=ExpressionLevel5
        public Assignment getRightOperandAssignment_1_1_2() {
            return cRightOperandAssignment_1_1_2;
        }

        //ExpressionLevel5
        public RuleCall getRightOperandExpressionLevel5ParserRuleCall_1_1_2_0() {
            return cRightOperandExpressionLevel5ParserRuleCall_1_1_2_0;
        }
    }

    public class BracketedArgumentStartExpressionLevel5Elements
            extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.BracketedArgumentStartExpressionLevel5");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final RuleCall cExpressionSequenceRoundBracketParserRuleCall_0 = (RuleCall) cGroup
                .eContents().get(0);

        private final Group cGroup_1 = (Group) cGroup.eContents().get(1);

        private final Action cDataMethodCallExpressionTargetAction_1_0 = (Action) cGroup_1
                .eContents().get(0);

        private final Assignment cOnSuperClassAssignment_1_1 = (Assignment) cGroup_1.eContents()
                .get(1);

        private final Keyword cOnSuperClassCircumflexAccentKeyword_1_1_0 = (Keyword) cOnSuperClassAssignment_1_1
                .eContents().get(0);

        private final Assignment cNameAssignment_1_2 = (Assignment) cGroup_1.eContents().get(2);

        private final RuleCall cNameIDENTIFIERParserRuleCall_1_2_0 = (RuleCall) cNameAssignment_1_2
                .eContents().get(0);

        private final Group cGroup_1_3 = (Group) cGroup_1.eContents().get(3);

        private final Keyword cLeftParenthesisKeyword_1_3_0 = (Keyword) cGroup_1_3.eContents()
                .get(0);

        private final Group cGroup_1_3_1 = (Group) cGroup_1_3.eContents().get(1);

        private final Assignment cArgumentsAssignment_1_3_1_0 = (Assignment) cGroup_1_3_1
                .eContents().get(0);

        private final RuleCall cArgumentsExpressionParserRuleCall_1_3_1_0_0 = (RuleCall) cArgumentsAssignment_1_3_1_0
                .eContents().get(0);

        private final Group cGroup_1_3_1_1 = (Group) cGroup_1_3_1.eContents().get(1);

        private final Keyword cCommaKeyword_1_3_1_1_0 = (Keyword) cGroup_1_3_1_1.eContents().get(0);

        private final Assignment cArgumentsAssignment_1_3_1_1_1 = (Assignment) cGroup_1_3_1_1
                .eContents().get(1);

        private final RuleCall cArgumentsExpressionParserRuleCall_1_3_1_1_1_0 = (RuleCall) cArgumentsAssignment_1_3_1_1_1
                .eContents().get(0);

        private final Keyword cRightParenthesisKeyword_1_3_2 = (Keyword) cGroup_1_3.eContents()
                .get(2);

        //BracketedArgumentStartExpressionLevel5 Expression:
        //	ExpressionSequenceRoundBracket ({DataMethodCallExpression.target=current} onSuperClass?='^'? name=IDENTIFIER ('('
        //	(arguments+=Expression (',' arguments+=Expression)*)? ')')?)+;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //ExpressionSequenceRoundBracket ({DataMethodCallExpression.target=current} onSuperClass?='^'? name=IDENTIFIER ('('
        //(arguments+=Expression (',' arguments+=Expression)*)? ')')?)+
        public Group getGroup() {
            return cGroup;
        }

        //ExpressionSequenceRoundBracket
        public RuleCall getExpressionSequenceRoundBracketParserRuleCall_0() {
            return cExpressionSequenceRoundBracketParserRuleCall_0;
        }

        //({DataMethodCallExpression.target=current} onSuperClass?='^'? name=IDENTIFIER ('(' (arguments+=Expression (','
        //arguments+=Expression)*)? ')')?)+
        public Group getGroup_1() {
            return cGroup_1;
        }

        //{DataMethodCallExpression.target=current}
        public Action getDataMethodCallExpressionTargetAction_1_0() {
            return cDataMethodCallExpressionTargetAction_1_0;
        }

        //onSuperClass?='^'?
        public Assignment getOnSuperClassAssignment_1_1() {
            return cOnSuperClassAssignment_1_1;
        }

        //'^'
        public Keyword getOnSuperClassCircumflexAccentKeyword_1_1_0() {
            return cOnSuperClassCircumflexAccentKeyword_1_1_0;
        }

        //name=IDENTIFIER
        public Assignment getNameAssignment_1_2() {
            return cNameAssignment_1_2;
        }

        //IDENTIFIER
        public RuleCall getNameIDENTIFIERParserRuleCall_1_2_0() {
            return cNameIDENTIFIERParserRuleCall_1_2_0;
        }

        //('(' (arguments+=Expression (',' arguments+=Expression)*)? ')')?
        public Group getGroup_1_3() {
            return cGroup_1_3;
        }

        //'('
        public Keyword getLeftParenthesisKeyword_1_3_0() {
            return cLeftParenthesisKeyword_1_3_0;
        }

        //(arguments+=Expression (',' arguments+=Expression)*)?
        public Group getGroup_1_3_1() {
            return cGroup_1_3_1;
        }

        //arguments+=Expression
        public Assignment getArgumentsAssignment_1_3_1_0() {
            return cArgumentsAssignment_1_3_1_0;
        }

        //Expression
        public RuleCall getArgumentsExpressionParserRuleCall_1_3_1_0_0() {
            return cArgumentsExpressionParserRuleCall_1_3_1_0_0;
        }

        //(',' arguments+=Expression)*
        public Group getGroup_1_3_1_1() {
            return cGroup_1_3_1_1;
        }

        //','
        public Keyword getCommaKeyword_1_3_1_1_0() {
            return cCommaKeyword_1_3_1_1_0;
        }

        //arguments+=Expression
        public Assignment getArgumentsAssignment_1_3_1_1_1() {
            return cArgumentsAssignment_1_3_1_1_1;
        }

        //Expression
        public RuleCall getArgumentsExpressionParserRuleCall_1_3_1_1_1_0() {
            return cArgumentsExpressionParserRuleCall_1_3_1_1_1_0;
        }

        //')'
        public Keyword getRightParenthesisKeyword_1_3_2() {
            return cRightParenthesisKeyword_1_3_2;
        }
    }

    public class INTEGERElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.INTEGER");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Alternatives cAlternatives_0 = (Alternatives) cGroup.eContents().get(0);

        private final Keyword cHyphenMinusKeyword_0_0 = (Keyword) cAlternatives_0.eContents()
                .get(0);

        private final Keyword cPlusSignKeyword_0_1 = (Keyword) cAlternatives_0.eContents().get(1);

        private final Alternatives cAlternatives_1 = (Alternatives) cGroup.eContents().get(1);

        private final RuleCall cDECIMAL_CORETerminalRuleCall_1_0 = (RuleCall) cAlternatives_1
                .eContents().get(0);

        private final RuleCall cBINARY_CORETerminalRuleCall_1_1 = (RuleCall) cAlternatives_1
                .eContents().get(1);

        private final RuleCall cHEXADECIMAL_CORETerminalRuleCall_1_2 = (RuleCall) cAlternatives_1
                .eContents().get(2);

        //// only the parser can distinguish between unary and binary '-'
        //INTEGER:
        //	('-' | '+')? (DECIMAL_CORE | BINARY_CORE | HEXADECIMAL_CORE);
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //('-' | '+')? (DECIMAL_CORE | BINARY_CORE | HEXADECIMAL_CORE)
        public Group getGroup() {
            return cGroup;
        }

        //('-' | '+')?
        public Alternatives getAlternatives_0() {
            return cAlternatives_0;
        }

        //'-'
        public Keyword getHyphenMinusKeyword_0_0() {
            return cHyphenMinusKeyword_0_0;
        }

        //'+'
        public Keyword getPlusSignKeyword_0_1() {
            return cPlusSignKeyword_0_1;
        }

        //(DECIMAL_CORE | BINARY_CORE | HEXADECIMAL_CORE)
        public Alternatives getAlternatives_1() {
            return cAlternatives_1;
        }

        //DECIMAL_CORE
        public RuleCall getDECIMAL_CORETerminalRuleCall_1_0() {
            return cDECIMAL_CORETerminalRuleCall_1_0;
        }

        //BINARY_CORE
        public RuleCall getBINARY_CORETerminalRuleCall_1_1() {
            return cBINARY_CORETerminalRuleCall_1_1;
        }

        //HEXADECIMAL_CORE
        public RuleCall getHEXADECIMAL_CORETerminalRuleCall_1_2() {
            return cHEXADECIMAL_CORETerminalRuleCall_1_2;
        }
    }

    public class REALElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.REAL");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Alternatives cAlternatives_0 = (Alternatives) cGroup.eContents().get(0);

        private final Keyword cHyphenMinusKeyword_0_0 = (Keyword) cAlternatives_0.eContents()
                .get(0);

        private final Keyword cPlusSignKeyword_0_1 = (Keyword) cAlternatives_0.eContents().get(1);

        private final RuleCall cREAL_CORETerminalRuleCall_1 = (RuleCall) cGroup.eContents().get(1);

        //REAL:
        //	('-' | '+')? REAL_CORE;
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //('-' | '+')? REAL_CORE
        public Group getGroup() {
            return cGroup;
        }

        //('-' | '+')?
        public Alternatives getAlternatives_0() {
            return cAlternatives_0;
        }

        //'-'
        public Keyword getHyphenMinusKeyword_0_0() {
            return cHyphenMinusKeyword_0_0;
        }

        //'+'
        public Keyword getPlusSignKeyword_0_1() {
            return cPlusSignKeyword_0_1;
        }

        //REAL_CORE
        public RuleCall getREAL_CORETerminalRuleCall_1() {
            return cREAL_CORETerminalRuleCall_1;
        }
    }

    public class FLOATElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.FLOAT");

        private final Group cGroup = (Group) rule.eContents().get(1);

        private final Alternatives cAlternatives_0 = (Alternatives) cGroup.eContents().get(0);

        private final Keyword cHyphenMinusKeyword_0_0 = (Keyword) cAlternatives_0.eContents()
                .get(0);

        private final Keyword cPlusSignKeyword_0_1 = (Keyword) cAlternatives_0.eContents().get(1);

        private final Alternatives cAlternatives_1 = (Alternatives) cGroup.eContents().get(1);

        private final RuleCall cFLOAT_CORETerminalRuleCall_1_0 = (RuleCall) cAlternatives_1
                .eContents().get(0);

        private final Keyword cNanKeyword_1_1 = (Keyword) cAlternatives_1.eContents().get(1);

        private final Keyword cInfKeyword_1_2 = (Keyword) cAlternatives_1.eContents().get(2);

        //FLOAT:
        //	('-' | '+')? (FLOAT_CORE | 'nan' | 'inf');
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //('-' | '+')? (FLOAT_CORE | 'nan' | 'inf')
        public Group getGroup() {
            return cGroup;
        }

        //('-' | '+')?
        public Alternatives getAlternatives_0() {
            return cAlternatives_0;
        }

        //'-'
        public Keyword getHyphenMinusKeyword_0_0() {
            return cHyphenMinusKeyword_0_0;
        }

        //'+'
        public Keyword getPlusSignKeyword_0_1() {
            return cPlusSignKeyword_0_1;
        }

        //(FLOAT_CORE | 'nan' | 'inf')
        public Alternatives getAlternatives_1() {
            return cAlternatives_1;
        }

        //FLOAT_CORE
        public RuleCall getFLOAT_CORETerminalRuleCall_1_0() {
            return cFLOAT_CORETerminalRuleCall_1_0;
        }

        //'nan'
        public Keyword getNanKeyword_1_1() {
            return cNanKeyword_1_1;
        }

        //'inf'
        public Keyword getInfKeyword_1_2() {
            return cInfKeyword_1_2;
        }
    }

    public class IDENTIFIERElements extends AbstractParserRuleElementFinder {
        private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.IDENTIFIER");

        private final Alternatives cAlternatives = (Alternatives) rule.eContents().get(1);

        private final RuleCall cIDENTIFIER_CORETerminalRuleCall_0 = (RuleCall) cAlternatives
                .eContents().get(0);

        private final Keyword cClassKeyword_1 = (Keyword) cAlternatives.eContents().get(1);

        private final Keyword cExtendsKeyword_2 = (Keyword) cAlternatives.eContents().get(2);

        private final Keyword cVariablesKeyword_3 = (Keyword) cAlternatives.eContents().get(3);

        private final Keyword cMethodsKeyword_4 = (Keyword) cAlternatives.eContents().get(4);

        private final Keyword cPortsKeyword_5 = (Keyword) cAlternatives.eContents().get(5);

        private final Keyword cMessagesKeyword_6 = (Keyword) cAlternatives.eContents().get(6);

        private final Keyword cInitKeyword_7 = (Keyword) cAlternatives.eContents().get(7);

        private final Keyword cChannelsKeyword_8 = (Keyword) cAlternatives.eContents().get(8);

        private final Keyword cInstancesKeyword_9 = (Keyword) cAlternatives.eContents().get(9);

        //IDENTIFIER:
        //	IDENTIFIER_CORE
        //	| 'class' | 'extends'
        //	| 'variables' | 'methods' | 'ports' | 'messages' | 'init' | 'channels' | 'instances';
        @Override
        public ParserRule getRule() {
            return rule;
        }

        //IDENTIFIER_CORE | 'class' | 'extends' | 'variables' | 'methods' | 'ports' | 'messages' | 'init' | 'channels' |
        //'instances'
        public Alternatives getAlternatives() {
            return cAlternatives;
        }

        //IDENTIFIER_CORE
        public RuleCall getIDENTIFIER_CORETerminalRuleCall_0() {
            return cIDENTIFIER_CORETerminalRuleCall_0;
        }

        //'class'
        public Keyword getClassKeyword_1() {
            return cClassKeyword_1;
        }

        //'extends'
        public Keyword getExtendsKeyword_2() {
            return cExtendsKeyword_2;
        }

        //'variables'
        public Keyword getVariablesKeyword_3() {
            return cVariablesKeyword_3;
        }

        //'methods'
        public Keyword getMethodsKeyword_4() {
            return cMethodsKeyword_4;
        }

        //'ports'
        public Keyword getPortsKeyword_5() {
            return cPortsKeyword_5;
        }

        //'messages'
        public Keyword getMessagesKeyword_6() {
            return cMessagesKeyword_6;
        }

        //'init'
        public Keyword getInitKeyword_7() {
            return cInitKeyword_7;
        }

        //'channels'
        public Keyword getChannelsKeyword_8() {
            return cChannelsKeyword_8;
        }

        //'instances'
        public Keyword getInstancesKeyword_9() {
            return cInstancesKeyword_9;
        }
    }

    public class OperatorUnaryElements extends AbstractEnumRuleElementFinder {
        private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.OperatorUnary");

        private final Alternatives cAlternatives = (Alternatives) rule.eContents().get(1);

        private final EnumLiteralDeclaration cMinusEnumLiteralDeclaration_0 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(0);

        private final Keyword cMinusHyphenMinusKeyword_0_0 = (Keyword) cMinusEnumLiteralDeclaration_0
                .eContents().get(0);

        private final EnumLiteralDeclaration cNotEnumLiteralDeclaration_1 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(1);

        private final Keyword cNotExclamationMarkKeyword_1_0 = (Keyword) cNotEnumLiteralDeclaration_1
                .eContents().get(0);

        //enum OperatorUnary:
        //	minus='-' | not='!';
        public EnumRule getRule() {
            return rule;
        }

        //minus='-' | not='!'
        public Alternatives getAlternatives() {
            return cAlternatives;
        }

        //minus='-'
        public EnumLiteralDeclaration getMinusEnumLiteralDeclaration_0() {
            return cMinusEnumLiteralDeclaration_0;
        }

        //'-'
        public Keyword getMinusHyphenMinusKeyword_0_0() {
            return cMinusHyphenMinusKeyword_0_0;
        }

        //not='!'
        public EnumLiteralDeclaration getNotEnumLiteralDeclaration_1() {
            return cNotEnumLiteralDeclaration_1;
        }

        //'!'
        public Keyword getNotExclamationMarkKeyword_1_0() {
            return cNotExclamationMarkKeyword_1_0;
        }
    }

    public class OperatorBinaryElements extends AbstractEnumRuleElementFinder {
        private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.OperatorBinary");

        private final Alternatives cAlternatives = (Alternatives) rule.eContents().get(1);

        private final EnumLiteralDeclaration cEqualEnumLiteralDeclaration_0 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(0);

        private final Keyword cEqualEqualsSignKeyword_0_0 = (Keyword) cEqualEnumLiteralDeclaration_0
                .eContents().get(0);

        private final EnumLiteralDeclaration cUnequalEnumLiteralDeclaration_1 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(1);

        private final Keyword cUnequalExclamationMarkEqualsSignKeyword_1_0 = (Keyword) cUnequalEnumLiteralDeclaration_1
                .eContents().get(0);

        private final EnumLiteralDeclaration cIdenticalEnumLiteralDeclaration_2 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(2);

        private final Keyword cIdenticalEqualsSignEqualsSignKeyword_2_0 = (Keyword) cIdenticalEnumLiteralDeclaration_2
                .eContents().get(0);

        private final EnumLiteralDeclaration cNotIdenticalEnumLiteralDeclaration_3 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(3);

        private final Keyword cNotIdenticalExclamationMarkEqualsSignEqualsSignKeyword_3_0 = (Keyword) cNotIdenticalEnumLiteralDeclaration_3
                .eContents().get(0);

        private final EnumLiteralDeclaration cLessThanEnumLiteralDeclaration_4 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(4);

        private final Keyword cLessThanLessThanSignKeyword_4_0 = (Keyword) cLessThanEnumLiteralDeclaration_4
                .eContents().get(0);

        private final EnumLiteralDeclaration cAtMostEnumLiteralDeclaration_5 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(5);

        private final Keyword cAtMostLessThanSignEqualsSignKeyword_5_0 = (Keyword) cAtMostEnumLiteralDeclaration_5
                .eContents().get(0);

        private final EnumLiteralDeclaration cGreaterThanEnumLiteralDeclaration_6 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(6);

        private final Keyword cGreaterThanGreaterThanSignKeyword_6_0 = (Keyword) cGreaterThanEnumLiteralDeclaration_6
                .eContents().get(0);

        private final EnumLiteralDeclaration cAtLeastEnumLiteralDeclaration_7 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(7);

        private final Keyword cAtLeastGreaterThanSignEqualsSignKeyword_7_0 = (Keyword) cAtLeastEnumLiteralDeclaration_7
                .eContents().get(0);

        private final EnumLiteralDeclaration cAddEnumLiteralDeclaration_8 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(8);

        private final Keyword cAddPlusSignKeyword_8_0 = (Keyword) cAddEnumLiteralDeclaration_8
                .eContents().get(0);

        private final EnumLiteralDeclaration cSubtractEnumLiteralDeclaration_9 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(9);

        private final Keyword cSubtractHyphenMinusKeyword_9_0 = (Keyword) cSubtractEnumLiteralDeclaration_9
                .eContents().get(0);

        private final EnumLiteralDeclaration cAndEnumLiteralDeclaration_10 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(10);

        private final Keyword cAndAmpersandKeyword_10_0 = (Keyword) cAndEnumLiteralDeclaration_10
                .eContents().get(0);

        private final EnumLiteralDeclaration cOrEnumLiteralDeclaration_11 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(11);

        private final Keyword cOrVerticalLineKeyword_11_0 = (Keyword) cOrEnumLiteralDeclaration_11
                .eContents().get(0);

        private final EnumLiteralDeclaration cMultiplyEnumLiteralDeclaration_12 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(12);

        private final Keyword cMultiplyAsteriskKeyword_12_0 = (Keyword) cMultiplyEnumLiteralDeclaration_12
                .eContents().get(0);

        private final EnumLiteralDeclaration cDivideEnumLiteralDeclaration_13 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(13);

        private final Keyword cDivideSolidusKeyword_13_0 = (Keyword) cDivideEnumLiteralDeclaration_13
                .eContents().get(0);

        //enum OperatorBinary:
        //	equal='=' | unequal='!=' | identical='==' | notIdentical='!==' |
        //	lessThan='<' | atMost='<=' | greaterThan='>' | atLeast='>=' |
        //	add='+' | subtract='-' | and='&' | or='|' |
        //	multiply='*' | divide='/';
        public EnumRule getRule() {
            return rule;
        }

        //equal='=' | unequal='!=' | identical='==' | notIdentical='!==' | lessThan='<' | atMost='<=' | greaterThan='>' |
        //atLeast='>=' | add='+' | subtract='-' | and='&' | or='|' | multiply='*' | divide='/'
        public Alternatives getAlternatives() {
            return cAlternatives;
        }

        //equal='='
        public EnumLiteralDeclaration getEqualEnumLiteralDeclaration_0() {
            return cEqualEnumLiteralDeclaration_0;
        }

        //'='
        public Keyword getEqualEqualsSignKeyword_0_0() {
            return cEqualEqualsSignKeyword_0_0;
        }

        //unequal='!='
        public EnumLiteralDeclaration getUnequalEnumLiteralDeclaration_1() {
            return cUnequalEnumLiteralDeclaration_1;
        }

        //'!='
        public Keyword getUnequalExclamationMarkEqualsSignKeyword_1_0() {
            return cUnequalExclamationMarkEqualsSignKeyword_1_0;
        }

        //identical='=='
        public EnumLiteralDeclaration getIdenticalEnumLiteralDeclaration_2() {
            return cIdenticalEnumLiteralDeclaration_2;
        }

        //'=='
        public Keyword getIdenticalEqualsSignEqualsSignKeyword_2_0() {
            return cIdenticalEqualsSignEqualsSignKeyword_2_0;
        }

        //notIdentical='!=='
        public EnumLiteralDeclaration getNotIdenticalEnumLiteralDeclaration_3() {
            return cNotIdenticalEnumLiteralDeclaration_3;
        }

        //'!=='
        public Keyword getNotIdenticalExclamationMarkEqualsSignEqualsSignKeyword_3_0() {
            return cNotIdenticalExclamationMarkEqualsSignEqualsSignKeyword_3_0;
        }

        //lessThan='<'
        public EnumLiteralDeclaration getLessThanEnumLiteralDeclaration_4() {
            return cLessThanEnumLiteralDeclaration_4;
        }

        //'<'
        public Keyword getLessThanLessThanSignKeyword_4_0() {
            return cLessThanLessThanSignKeyword_4_0;
        }

        //atMost='<='
        public EnumLiteralDeclaration getAtMostEnumLiteralDeclaration_5() {
            return cAtMostEnumLiteralDeclaration_5;
        }

        //'<='
        public Keyword getAtMostLessThanSignEqualsSignKeyword_5_0() {
            return cAtMostLessThanSignEqualsSignKeyword_5_0;
        }

        //greaterThan='>'
        public EnumLiteralDeclaration getGreaterThanEnumLiteralDeclaration_6() {
            return cGreaterThanEnumLiteralDeclaration_6;
        }

        //'>'
        public Keyword getGreaterThanGreaterThanSignKeyword_6_0() {
            return cGreaterThanGreaterThanSignKeyword_6_0;
        }

        //atLeast='>='
        public EnumLiteralDeclaration getAtLeastEnumLiteralDeclaration_7() {
            return cAtLeastEnumLiteralDeclaration_7;
        }

        //'>='
        public Keyword getAtLeastGreaterThanSignEqualsSignKeyword_7_0() {
            return cAtLeastGreaterThanSignEqualsSignKeyword_7_0;
        }

        //add='+'
        public EnumLiteralDeclaration getAddEnumLiteralDeclaration_8() {
            return cAddEnumLiteralDeclaration_8;
        }

        //'+'
        public Keyword getAddPlusSignKeyword_8_0() {
            return cAddPlusSignKeyword_8_0;
        }

        //subtract='-'
        public EnumLiteralDeclaration getSubtractEnumLiteralDeclaration_9() {
            return cSubtractEnumLiteralDeclaration_9;
        }

        //'-'
        public Keyword getSubtractHyphenMinusKeyword_9_0() {
            return cSubtractHyphenMinusKeyword_9_0;
        }

        //and='&'
        public EnumLiteralDeclaration getAndEnumLiteralDeclaration_10() {
            return cAndEnumLiteralDeclaration_10;
        }

        //'&'
        public Keyword getAndAmpersandKeyword_10_0() {
            return cAndAmpersandKeyword_10_0;
        }

        //or='|'
        public EnumLiteralDeclaration getOrEnumLiteralDeclaration_11() {
            return cOrEnumLiteralDeclaration_11;
        }

        //'|'
        public Keyword getOrVerticalLineKeyword_11_0() {
            return cOrVerticalLineKeyword_11_0;
        }

        //multiply='*'
        public EnumLiteralDeclaration getMultiplyEnumLiteralDeclaration_12() {
            return cMultiplyEnumLiteralDeclaration_12;
        }

        //'*'
        public Keyword getMultiplyAsteriskKeyword_12_0() {
            return cMultiplyAsteriskKeyword_12_0;
        }

        //divide='/'
        public EnumLiteralDeclaration getDivideEnumLiteralDeclaration_13() {
            return cDivideEnumLiteralDeclaration_13;
        }

        //'/'
        public Keyword getDivideSolidusKeyword_13_0() {
            return cDivideSolidusKeyword_13_0;
        }
    }

    public class BinaryOperatorLevel2Elements extends AbstractEnumRuleElementFinder {
        private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.BinaryOperatorLevel2");

        private final Alternatives cAlternatives = (Alternatives) rule.eContents().get(1);

        private final EnumLiteralDeclaration cEqualEnumLiteralDeclaration_0 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(0);

        private final Keyword cEqualEqualsSignKeyword_0_0 = (Keyword) cEqualEnumLiteralDeclaration_0
                .eContents().get(0);

        private final EnumLiteralDeclaration cUnequalEnumLiteralDeclaration_1 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(1);

        private final Keyword cUnequalExclamationMarkEqualsSignKeyword_1_0 = (Keyword) cUnequalEnumLiteralDeclaration_1
                .eContents().get(0);

        private final EnumLiteralDeclaration cIdenticalEnumLiteralDeclaration_2 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(2);

        private final Keyword cIdenticalEqualsSignEqualsSignKeyword_2_0 = (Keyword) cIdenticalEnumLiteralDeclaration_2
                .eContents().get(0);

        private final EnumLiteralDeclaration cNotIdenticalEnumLiteralDeclaration_3 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(3);

        private final Keyword cNotIdenticalExclamationMarkEqualsSignEqualsSignKeyword_3_0 = (Keyword) cNotIdenticalEnumLiteralDeclaration_3
                .eContents().get(0);

        private final EnumLiteralDeclaration cLessThanEnumLiteralDeclaration_4 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(4);

        private final Keyword cLessThanLessThanSignKeyword_4_0 = (Keyword) cLessThanEnumLiteralDeclaration_4
                .eContents().get(0);

        private final EnumLiteralDeclaration cAtMostEnumLiteralDeclaration_5 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(5);

        private final Keyword cAtMostLessThanSignEqualsSignKeyword_5_0 = (Keyword) cAtMostEnumLiteralDeclaration_5
                .eContents().get(0);

        private final EnumLiteralDeclaration cGreaterThanEnumLiteralDeclaration_6 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(6);

        private final Keyword cGreaterThanGreaterThanSignKeyword_6_0 = (Keyword) cGreaterThanEnumLiteralDeclaration_6
                .eContents().get(0);

        private final EnumLiteralDeclaration cAtLeastEnumLiteralDeclaration_7 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(7);

        private final Keyword cAtLeastGreaterThanSignEqualsSignKeyword_7_0 = (Keyword) cAtLeastEnumLiteralDeclaration_7
                .eContents().get(0);

        //enum BinaryOperatorLevel2 returns OperatorBinary:
        //	equal='=' | unequal='!=' | identical='==' | notIdentical='!==' |
        //	lessThan='<' | atMost='<=' | greaterThan='>' | atLeast='>=';
        public EnumRule getRule() {
            return rule;
        }

        //equal='=' | unequal='!=' | identical='==' | notIdentical='!==' | lessThan='<' | atMost='<=' | greaterThan='>' |
        //atLeast='>='
        public Alternatives getAlternatives() {
            return cAlternatives;
        }

        //equal='='
        public EnumLiteralDeclaration getEqualEnumLiteralDeclaration_0() {
            return cEqualEnumLiteralDeclaration_0;
        }

        //'='
        public Keyword getEqualEqualsSignKeyword_0_0() {
            return cEqualEqualsSignKeyword_0_0;
        }

        //unequal='!='
        public EnumLiteralDeclaration getUnequalEnumLiteralDeclaration_1() {
            return cUnequalEnumLiteralDeclaration_1;
        }

        //'!='
        public Keyword getUnequalExclamationMarkEqualsSignKeyword_1_0() {
            return cUnequalExclamationMarkEqualsSignKeyword_1_0;
        }

        //identical='=='
        public EnumLiteralDeclaration getIdenticalEnumLiteralDeclaration_2() {
            return cIdenticalEnumLiteralDeclaration_2;
        }

        //'=='
        public Keyword getIdenticalEqualsSignEqualsSignKeyword_2_0() {
            return cIdenticalEqualsSignEqualsSignKeyword_2_0;
        }

        //notIdentical='!=='
        public EnumLiteralDeclaration getNotIdenticalEnumLiteralDeclaration_3() {
            return cNotIdenticalEnumLiteralDeclaration_3;
        }

        //'!=='
        public Keyword getNotIdenticalExclamationMarkEqualsSignEqualsSignKeyword_3_0() {
            return cNotIdenticalExclamationMarkEqualsSignEqualsSignKeyword_3_0;
        }

        //lessThan='<'
        public EnumLiteralDeclaration getLessThanEnumLiteralDeclaration_4() {
            return cLessThanEnumLiteralDeclaration_4;
        }

        //'<'
        public Keyword getLessThanLessThanSignKeyword_4_0() {
            return cLessThanLessThanSignKeyword_4_0;
        }

        //atMost='<='
        public EnumLiteralDeclaration getAtMostEnumLiteralDeclaration_5() {
            return cAtMostEnumLiteralDeclaration_5;
        }

        //'<='
        public Keyword getAtMostLessThanSignEqualsSignKeyword_5_0() {
            return cAtMostLessThanSignEqualsSignKeyword_5_0;
        }

        //greaterThan='>'
        public EnumLiteralDeclaration getGreaterThanEnumLiteralDeclaration_6() {
            return cGreaterThanEnumLiteralDeclaration_6;
        }

        //'>'
        public Keyword getGreaterThanGreaterThanSignKeyword_6_0() {
            return cGreaterThanGreaterThanSignKeyword_6_0;
        }

        //atLeast='>='
        public EnumLiteralDeclaration getAtLeastEnumLiteralDeclaration_7() {
            return cAtLeastEnumLiteralDeclaration_7;
        }

        //'>='
        public Keyword getAtLeastGreaterThanSignEqualsSignKeyword_7_0() {
            return cAtLeastGreaterThanSignEqualsSignKeyword_7_0;
        }
    }

    public class BinaryOperatorLevel3Elements extends AbstractEnumRuleElementFinder {
        private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.BinaryOperatorLevel3");

        private final Alternatives cAlternatives = (Alternatives) rule.eContents().get(1);

        private final EnumLiteralDeclaration cAddEnumLiteralDeclaration_0 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(0);

        private final Keyword cAddPlusSignKeyword_0_0 = (Keyword) cAddEnumLiteralDeclaration_0
                .eContents().get(0);

        private final EnumLiteralDeclaration cSubtractEnumLiteralDeclaration_1 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(1);

        private final Keyword cSubtractHyphenMinusKeyword_1_0 = (Keyword) cSubtractEnumLiteralDeclaration_1
                .eContents().get(0);

        private final EnumLiteralDeclaration cAndEnumLiteralDeclaration_2 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(2);

        private final Keyword cAndAmpersandKeyword_2_0 = (Keyword) cAndEnumLiteralDeclaration_2
                .eContents().get(0);

        private final EnumLiteralDeclaration cOrEnumLiteralDeclaration_3 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(3);

        private final Keyword cOrVerticalLineKeyword_3_0 = (Keyword) cOrEnumLiteralDeclaration_3
                .eContents().get(0);

        //enum BinaryOperatorLevel3 returns OperatorBinary:
        //	add='+' | subtract='-' | and='&' | or='|';
        public EnumRule getRule() {
            return rule;
        }

        //add='+' | subtract='-' | and='&' | or='|'
        public Alternatives getAlternatives() {
            return cAlternatives;
        }

        //add='+'
        public EnumLiteralDeclaration getAddEnumLiteralDeclaration_0() {
            return cAddEnumLiteralDeclaration_0;
        }

        //'+'
        public Keyword getAddPlusSignKeyword_0_0() {
            return cAddPlusSignKeyword_0_0;
        }

        //subtract='-'
        public EnumLiteralDeclaration getSubtractEnumLiteralDeclaration_1() {
            return cSubtractEnumLiteralDeclaration_1;
        }

        //'-'
        public Keyword getSubtractHyphenMinusKeyword_1_0() {
            return cSubtractHyphenMinusKeyword_1_0;
        }

        //and='&'
        public EnumLiteralDeclaration getAndEnumLiteralDeclaration_2() {
            return cAndEnumLiteralDeclaration_2;
        }

        //'&'
        public Keyword getAndAmpersandKeyword_2_0() {
            return cAndAmpersandKeyword_2_0;
        }

        //or='|'
        public EnumLiteralDeclaration getOrEnumLiteralDeclaration_3() {
            return cOrEnumLiteralDeclaration_3;
        }

        //'|'
        public Keyword getOrVerticalLineKeyword_3_0() {
            return cOrVerticalLineKeyword_3_0;
        }
    }

    public class BinaryOperatorLevel4Elements extends AbstractEnumRuleElementFinder {
        private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.BinaryOperatorLevel4");

        private final Alternatives cAlternatives = (Alternatives) rule.eContents().get(1);

        private final EnumLiteralDeclaration cMultiplyEnumLiteralDeclaration_0 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(0);

        private final Keyword cMultiplyAsteriskKeyword_0_0 = (Keyword) cMultiplyEnumLiteralDeclaration_0
                .eContents().get(0);

        private final EnumLiteralDeclaration cDivideEnumLiteralDeclaration_1 = (EnumLiteralDeclaration) cAlternatives
                .eContents().get(1);

        private final Keyword cDivideSolidusKeyword_1_0 = (Keyword) cDivideEnumLiteralDeclaration_1
                .eContents().get(0);

        //enum BinaryOperatorLevel4 returns OperatorBinary:
        //	multiply='*' | divide='/';
        public EnumRule getRule() {
            return rule;
        }

        //multiply='*' | divide='/'
        public Alternatives getAlternatives() {
            return cAlternatives;
        }

        //multiply='*'
        public EnumLiteralDeclaration getMultiplyEnumLiteralDeclaration_0() {
            return cMultiplyEnumLiteralDeclaration_0;
        }

        //'*'
        public Keyword getMultiplyAsteriskKeyword_0_0() {
            return cMultiplyAsteriskKeyword_0_0;
        }

        //divide='/'
        public EnumLiteralDeclaration getDivideEnumLiteralDeclaration_1() {
            return cDivideEnumLiteralDeclaration_1;
        }

        //'/'
        public Keyword getDivideSolidusKeyword_1_0() {
            return cDivideSolidusKeyword_1_0;
        }
    }

    private final PooslElements pPoosl;

    private final ImportElements pImport;

    private final ImportLibElements pImportLib;

    private final DataClassElements pDataClass;

    private final DataMethodNamedElements pDataMethodNamed;

    private final DataMethodBinaryOperatorElements pDataMethodBinaryOperator;

    private final DataMethodUnaryOperatorElements pDataMethodUnaryOperator;

    private final DeclarationElements pDeclaration;

    private final VariableElements pVariable;

    private final AnnotationElements pAnnotation;

    private final ProcessClassElements pProcessClass;

    private final ProcessMethodElements pProcessMethod;

    private final PortElements pPort;

    private final MessageReceiveSignatureElements pMessageReceiveSignature;

    private final MessageSendSignatureElements pMessageSendSignature;

    private final MessageParameterElements pMessageParameter;

    private final SystemElements pSystem;

    private final ClusterClassElements pClusterClass;

    private final InstanceElements pInstance;

    private final InstanceParameterElements pInstanceParameter;

    private final ChannelElements pChannel;

    private final InstancePortElements pInstancePort;

    private final ExpressionElements pExpression;

    private final ExpressionSingleElements pExpressionSingle;

    private final ExpressionLevel1Elements pExpressionLevel1;

    private final ExpressionLevel2Elements pExpressionLevel2;

    private final ExpressionLevel3Elements pExpressionLevel3;

    private final ExpressionLevel4Elements pExpressionLevel4;

    private final ExpressionLevel5Elements pExpressionLevel5;

    private final ExpressionLevel6Elements pExpressionLevel6;

    private final UnaryOperatorExpressionElements pUnaryOperatorExpression;

    private final IfExpressionElements pIfExpression;

    private final WhileExpressionElements pWhileExpression;

    private final SwitchExpressionElements pSwitchExpression;

    private final SwitchExpressionCaseElements pSwitchExpressionCase;

    private final ExpressionLevel7Elements pExpressionLevel7;

    private final CurrentTimeExpressionElements pCurrentTimeExpression;

    private final SelfExpressionElements pSelfExpression;

    private final ExpressionConstantElements pExpressionConstant;

    private final BooleanConstantElements pBooleanConstant;

    private final CharacterConstantElements pCharacterConstant;

    private final FloatConstantElements pFloatConstant;

    private final IntegerConstantElements pIntegerConstant;

    private final NilConstantElements pNilConstant;

    private final RealConstantElements pRealConstant;

    private final StringConstantElements pStringConstant;

    private final EnvironmentConstantElements pEnvironmentConstant;

    private final OutputVariableElements pOutputVariable;

    private final VariableExpressionElements pVariableExpression;

    private final NewExpressionElements pNewExpression;

    private final ExpressionSequenceRoundBracketElements pExpressionSequenceRoundBracket;

    private final IDStartWithinStatementExpressionSingleElements pIDStartWithinStatementExpressionSingle;

    private final IDStartWithinStatementExpressionLevel1Elements pIDStartWithinStatementExpressionLevel1;

    private final IDStartWithinStatementExpressionLevel2Elements pIDStartWithinStatementExpressionLevel2;

    private final IDStartWithinStatementExpressionLevel3Elements pIDStartWithinStatementExpressionLevel3;

    private final IDStartWithinStatementExpressionLevel4Elements pIDStartWithinStatementExpressionLevel4;

    private final IDStartWithinStatementExpressionLevel5Elements pIDStartWithinStatementExpressionLevel5;

    private final NonIDStartWithinStatementExpressionSingleElements pNonIDStartWithinStatementExpressionSingle;

    private final NonIDStartWithinStatementExpressionLevel1Elements pNonIDStartWithinStatementExpressionLevel1;

    private final NonIDStartWithinStatementExpressionLevel2Elements pNonIDStartWithinStatementExpressionLevel2;

    private final NonIDStartWithinStatementExpressionLevel3Elements pNonIDStartWithinStatementExpressionLevel3;

    private final NonIDStartWithinStatementExpressionLevel4Elements pNonIDStartWithinStatementExpressionLevel4;

    private final NonIDStartWithinStatementExpressionLevel5Elements pNonIDStartWithinStatementExpressionLevel5;

    private final NonIDStartWithinStatementExpressionLevel6Elements pNonIDStartWithinStatementExpressionLevel6;

    private final NonIDStartWithinStatementExpressionLevel7Elements pNonIDStartWithinStatementExpressionLevel7;

    private final StatementElements pStatement;

    private final StatementSingleElements pStatementSingle;

    private final ExpressionStatementElements pExpressionStatement;

    private final DelayStatementElements pDelayStatement;

    private final ReceiveStatementElements pReceiveStatement;

    private final SendStatementElements pSendStatement;

    private final PortDescriptorElements pPortDescriptor;

    private final PortReferenceElements pPortReference;

    private final SkipStatementElements pSkipStatement;

    private final GuardedStatementElements pGuardedStatement;

    private final IfStatementElements pIfStatement;

    private final WhileStatementElements pWhileStatement;

    private final SwitchStatementElements pSwitchStatement;

    private final SwitchStatementCaseElements pSwitchStatementCase;

    private final ParStatementElements pParStatement;

    private final SelStatementElements pSelStatement;

    private final AbortStatementElements pAbortStatement;

    private final InterruptStatementElements pInterruptStatement;

    private final ProcessMethodCallElements pProcessMethodCall;

    private final BracketStartStatementElements pBracketStartStatement;

    private final StatementSequenceRoundBracketElements pStatementSequenceRoundBracket;

    private final BracketedArgumentStartExpressionStatementElements pBracketedArgumentStartExpressionStatement;

    private final BracketedArgumentStartExpressionSingleElements pBracketedArgumentStartExpressionSingle;

    private final BracketedArgumentStartExpressionLevel1Elements pBracketedArgumentStartExpressionLevel1;

    private final BracketedArgumentStartExpressionLevel2Elements pBracketedArgumentStartExpressionLevel2;

    private final BracketedArgumentStartExpressionLevel3Elements pBracketedArgumentStartExpressionLevel3;

    private final BracketedArgumentStartExpressionLevel4Elements pBracketedArgumentStartExpressionLevel4;

    private final BracketedArgumentStartExpressionLevel5Elements pBracketedArgumentStartExpressionLevel5;

    private final OperatorUnaryElements eOperatorUnary;

    private final OperatorBinaryElements eOperatorBinary;

    private final BinaryOperatorLevel2Elements eBinaryOperatorLevel2;

    private final BinaryOperatorLevel3Elements eBinaryOperatorLevel3;

    private final BinaryOperatorLevel4Elements eBinaryOperatorLevel4;

    private final TerminalRule tWS;

    private final TerminalRule tSL_COMMENT;

    private final TerminalRule tMLC_ANY;

    private final TerminalRule tMLC_SLASH;

    private final TerminalRule tMLC_STAR;

    private final TerminalRule tMLC_BODY;

    private final TerminalRule tML_COMMENT;

    private final TerminalRule tBINARY_CORE;

    private final TerminalRule tHEXADECIMAL_CORE;

    private final TerminalRule tDIGITS;

    private final TerminalRule tDECIMAL_CORE;

    private final TerminalRule tREAL_CORE;

    private final TerminalRule tFLOAT_CORE;

    private final INTEGERElements pINTEGER;

    private final REALElements pREAL;

    private final FLOATElements pFLOAT;

    private final IDENTIFIERElements pIDENTIFIER;

    private final TerminalRule tIDENTIFIER_CORE;

    private final TerminalRule tCHARACTER;

    private final TerminalRule tPOOSL_STRING;

    private final TerminalRule tENVIRONMENT_VARIABLE_NAME;

    private final TerminalRule tCHARACTER_ELEMENT;

    private final TerminalRule tPOOSL_STRING_ELEMENT;

    private final TerminalRule tESCAPE_SEQUENCE;

    private final TerminalRule tESCAPE_ZERO;

    private final Grammar grammar;

    @Inject
    public PooslGrammarAccess(GrammarProvider grammarProvider) {
        this.grammar = internalFindGrammar(grammarProvider);
        this.pPoosl = new PooslElements();
        this.pImport = new ImportElements();
        this.pImportLib = new ImportLibElements();
        this.pDataClass = new DataClassElements();
        this.pDataMethodNamed = new DataMethodNamedElements();
        this.pDataMethodBinaryOperator = new DataMethodBinaryOperatorElements();
        this.pDataMethodUnaryOperator = new DataMethodUnaryOperatorElements();
        this.pDeclaration = new DeclarationElements();
        this.pVariable = new VariableElements();
        this.pAnnotation = new AnnotationElements();
        this.pProcessClass = new ProcessClassElements();
        this.pProcessMethod = new ProcessMethodElements();
        this.pPort = new PortElements();
        this.pMessageReceiveSignature = new MessageReceiveSignatureElements();
        this.pMessageSendSignature = new MessageSendSignatureElements();
        this.pMessageParameter = new MessageParameterElements();
        this.pSystem = new SystemElements();
        this.pClusterClass = new ClusterClassElements();
        this.pInstance = new InstanceElements();
        this.pInstanceParameter = new InstanceParameterElements();
        this.pChannel = new ChannelElements();
        this.pInstancePort = new InstancePortElements();
        this.pExpression = new ExpressionElements();
        this.pExpressionSingle = new ExpressionSingleElements();
        this.pExpressionLevel1 = new ExpressionLevel1Elements();
        this.pExpressionLevel2 = new ExpressionLevel2Elements();
        this.pExpressionLevel3 = new ExpressionLevel3Elements();
        this.pExpressionLevel4 = new ExpressionLevel4Elements();
        this.pExpressionLevel5 = new ExpressionLevel5Elements();
        this.pExpressionLevel6 = new ExpressionLevel6Elements();
        this.pUnaryOperatorExpression = new UnaryOperatorExpressionElements();
        this.pIfExpression = new IfExpressionElements();
        this.pWhileExpression = new WhileExpressionElements();
        this.pSwitchExpression = new SwitchExpressionElements();
        this.pSwitchExpressionCase = new SwitchExpressionCaseElements();
        this.pExpressionLevel7 = new ExpressionLevel7Elements();
        this.pCurrentTimeExpression = new CurrentTimeExpressionElements();
        this.pSelfExpression = new SelfExpressionElements();
        this.pExpressionConstant = new ExpressionConstantElements();
        this.pBooleanConstant = new BooleanConstantElements();
        this.pCharacterConstant = new CharacterConstantElements();
        this.pFloatConstant = new FloatConstantElements();
        this.pIntegerConstant = new IntegerConstantElements();
        this.pNilConstant = new NilConstantElements();
        this.pRealConstant = new RealConstantElements();
        this.pStringConstant = new StringConstantElements();
        this.pEnvironmentConstant = new EnvironmentConstantElements();
        this.pOutputVariable = new OutputVariableElements();
        this.pVariableExpression = new VariableExpressionElements();
        this.pNewExpression = new NewExpressionElements();
        this.pExpressionSequenceRoundBracket = new ExpressionSequenceRoundBracketElements();
        this.pIDStartWithinStatementExpressionSingle = new IDStartWithinStatementExpressionSingleElements();
        this.pIDStartWithinStatementExpressionLevel1 = new IDStartWithinStatementExpressionLevel1Elements();
        this.pIDStartWithinStatementExpressionLevel2 = new IDStartWithinStatementExpressionLevel2Elements();
        this.pIDStartWithinStatementExpressionLevel3 = new IDStartWithinStatementExpressionLevel3Elements();
        this.pIDStartWithinStatementExpressionLevel4 = new IDStartWithinStatementExpressionLevel4Elements();
        this.pIDStartWithinStatementExpressionLevel5 = new IDStartWithinStatementExpressionLevel5Elements();
        this.pNonIDStartWithinStatementExpressionSingle = new NonIDStartWithinStatementExpressionSingleElements();
        this.pNonIDStartWithinStatementExpressionLevel1 = new NonIDStartWithinStatementExpressionLevel1Elements();
        this.pNonIDStartWithinStatementExpressionLevel2 = new NonIDStartWithinStatementExpressionLevel2Elements();
        this.pNonIDStartWithinStatementExpressionLevel3 = new NonIDStartWithinStatementExpressionLevel3Elements();
        this.pNonIDStartWithinStatementExpressionLevel4 = new NonIDStartWithinStatementExpressionLevel4Elements();
        this.pNonIDStartWithinStatementExpressionLevel5 = new NonIDStartWithinStatementExpressionLevel5Elements();
        this.pNonIDStartWithinStatementExpressionLevel6 = new NonIDStartWithinStatementExpressionLevel6Elements();
        this.pNonIDStartWithinStatementExpressionLevel7 = new NonIDStartWithinStatementExpressionLevel7Elements();
        this.pStatement = new StatementElements();
        this.pStatementSingle = new StatementSingleElements();
        this.pExpressionStatement = new ExpressionStatementElements();
        this.pDelayStatement = new DelayStatementElements();
        this.pReceiveStatement = new ReceiveStatementElements();
        this.pSendStatement = new SendStatementElements();
        this.pPortDescriptor = new PortDescriptorElements();
        this.pPortReference = new PortReferenceElements();
        this.pSkipStatement = new SkipStatementElements();
        this.pGuardedStatement = new GuardedStatementElements();
        this.pIfStatement = new IfStatementElements();
        this.pWhileStatement = new WhileStatementElements();
        this.pSwitchStatement = new SwitchStatementElements();
        this.pSwitchStatementCase = new SwitchStatementCaseElements();
        this.pParStatement = new ParStatementElements();
        this.pSelStatement = new SelStatementElements();
        this.pAbortStatement = new AbortStatementElements();
        this.pInterruptStatement = new InterruptStatementElements();
        this.pProcessMethodCall = new ProcessMethodCallElements();
        this.pBracketStartStatement = new BracketStartStatementElements();
        this.pStatementSequenceRoundBracket = new StatementSequenceRoundBracketElements();
        this.pBracketedArgumentStartExpressionStatement = new BracketedArgumentStartExpressionStatementElements();
        this.pBracketedArgumentStartExpressionSingle = new BracketedArgumentStartExpressionSingleElements();
        this.pBracketedArgumentStartExpressionLevel1 = new BracketedArgumentStartExpressionLevel1Elements();
        this.pBracketedArgumentStartExpressionLevel2 = new BracketedArgumentStartExpressionLevel2Elements();
        this.pBracketedArgumentStartExpressionLevel3 = new BracketedArgumentStartExpressionLevel3Elements();
        this.pBracketedArgumentStartExpressionLevel4 = new BracketedArgumentStartExpressionLevel4Elements();
        this.pBracketedArgumentStartExpressionLevel5 = new BracketedArgumentStartExpressionLevel5Elements();
        this.eOperatorUnary = new OperatorUnaryElements();
        this.eOperatorBinary = new OperatorBinaryElements();
        this.eBinaryOperatorLevel2 = new BinaryOperatorLevel2Elements();
        this.eBinaryOperatorLevel3 = new BinaryOperatorLevel3Elements();
        this.eBinaryOperatorLevel4 = new BinaryOperatorLevel4Elements();
        this.tWS = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.WS");
        this.tSL_COMMENT = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.SL_COMMENT");
        this.tMLC_ANY = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.MLC_ANY");
        this.tMLC_SLASH = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.MLC_SLASH");
        this.tMLC_STAR = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.MLC_STAR");
        this.tMLC_BODY = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.MLC_BODY");
        this.tML_COMMENT = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.ML_COMMENT");
        this.tBINARY_CORE = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.BINARY_CORE");
        this.tHEXADECIMAL_CORE = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.HEXADECIMAL_CORE");
        this.tDIGITS = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.DIGITS");
        this.tDECIMAL_CORE = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.DECIMAL_CORE");
        this.tREAL_CORE = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.REAL_CORE");
        this.tFLOAT_CORE = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.FLOAT_CORE");
        this.pINTEGER = new INTEGERElements();
        this.pREAL = new REALElements();
        this.pFLOAT = new FLOATElements();
        this.pIDENTIFIER = new IDENTIFIERElements();
        this.tIDENTIFIER_CORE = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.IDENTIFIER_CORE");
        this.tCHARACTER = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.CHARACTER");
        this.tPOOSL_STRING = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.POOSL_STRING");
        this.tENVIRONMENT_VARIABLE_NAME = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.ENVIRONMENT_VARIABLE_NAME");
        this.tCHARACTER_ELEMENT = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.CHARACTER_ELEMENT");
        this.tPOOSL_STRING_ELEMENT = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.POOSL_STRING_ELEMENT");
        this.tESCAPE_SEQUENCE = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.ESCAPE_SEQUENCE");
        this.tESCAPE_ZERO = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(),
                "org.eclipse.poosl.xtext.Poosl.ESCAPE_ZERO");
    }

    protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
        Grammar grammar = grammarProvider.getGrammar(this);
        while (grammar != null) {
            if ("org.eclipse.poosl.xtext.Poosl".equals(grammar.getName())) {
                return grammar;
            }
            List<Grammar> grammars = grammar.getUsedGrammars();
            if (!grammars.isEmpty()) {
                grammar = grammars.iterator().next();
            } else {
                return null;
            }
        }
        return grammar;
    }

    @Override
    public Grammar getGrammar() {
        return grammar;
    }

    //// === Main entry point =======
    //Poosl:
    //	{Poosl} (imports+=Import | importLibs+=ImportLib)* (dataClasses+=DataClass
    //	| processClasses+=ProcessClass
    //	| clusterClasses+=ClusterClass)* (clusterClasses+=System (dataClasses+=DataClass
    //	| processClasses+=ProcessClass
    //	| clusterClasses+=ClusterClass)*)?;
    public PooslElements getPooslAccess() {
        return pPoosl;
    }

    public ParserRule getPooslRule() {
        return getPooslAccess().getRule();
    }

    //Import:
    //	'import' importURI=POOSL_STRING;
    public ImportElements getImportAccess() {
        return pImport;
    }

    public ParserRule getImportRule() {
        return getImportAccess().getRule();
    }

    //ImportLib Import:
    //	'importlib' importURI=POOSL_STRING;
    public ImportLibElements getImportLibAccess() {
        return pImportLib;
    }

    public ParserRule getImportLibRule() {
        return getImportLibAccess().getRule();
    }

    //// === Data Class =======
    //DataClass:
    //	annotations+=Annotation*
    //	native?='native'?
    //	'data' 'class' name=IDENTIFIER ('extends' superClass=IDENTIFIER)?
    //	'variables' (instanceVariables+=Declaration (','? instanceVariables+=Declaration)* ','?)?
    //	'methods' (dataMethodsNamed+=DataMethodNamed | dataMethodsUnaryOperator+=DataMethodUnaryOperator |
    //	dataMethodsBinaryOperator+=DataMethodBinaryOperator)*;
    public DataClassElements getDataClassAccess() {
        return pDataClass;
    }

    public ParserRule getDataClassRule() {
        return getDataClassAccess().getRule();
    }

    //DataMethodNamed:
    //	annotations+=Annotation*
    //	native?='native'?
    //	name=IDENTIFIER ('(' (parameters+=Declaration (',' parameters+=Declaration)*)? ')')?
    //	':' returnType=IDENTIFIER ('|' (localVariables+=Declaration (',' localVariables+=Declaration)*)? '|')?
    //	body=Expression?;
    public DataMethodNamedElements getDataMethodNamedAccess() {
        return pDataMethodNamed;
    }

    public ParserRule getDataMethodNamedRule() {
        return getDataMethodNamedAccess().getRule();
    }

    //DataMethodBinaryOperator:
    //	annotations+=Annotation*
    //	native?='native'?
    //	name=OperatorBinary
    //	'(' parameters+=Declaration (',' parameters+=Declaration)* ')'
    //	':' returnType=IDENTIFIER ('|' (localVariables+=Declaration (',' localVariables+=Declaration)*)? '|')?
    //	body=Expression?;
    public DataMethodBinaryOperatorElements getDataMethodBinaryOperatorAccess() {
        return pDataMethodBinaryOperator;
    }

    public ParserRule getDataMethodBinaryOperatorRule() {
        return getDataMethodBinaryOperatorAccess().getRule();
    }

    //DataMethodUnaryOperator:
    //	annotations+=Annotation*
    //	native?='native'?
    //	name=OperatorUnary ('(' ')')?
    //	':' returnType=IDENTIFIER ('|' (localVariables+=Declaration (',' localVariables+=Declaration)*)? '|')?
    //	body=Expression?;
    public DataMethodUnaryOperatorElements getDataMethodUnaryOperatorAccess() {
        return pDataMethodUnaryOperator;
    }

    public ParserRule getDataMethodUnaryOperatorRule() {
        return getDataMethodUnaryOperatorAccess().getRule();
    }

    //Declaration:
    //	(variables+=Variable (',' variables+=Variable)*)? ':' type=IDENTIFIER;
    public DeclarationElements getDeclarationAccess() {
        return pDeclaration;
    }

    public ParserRule getDeclarationRule() {
        return getDeclarationAccess().getRule();
    }

    //Variable:
    //	name=IDENTIFIER;
    public VariableElements getVariableAccess() {
        return pVariable;
    }

    public ParserRule getVariableRule() {
        return getVariableAccess().getRule();
    }

    //Annotation:
    //	'@' name=IDENTIFIER ('(' (arguments+=ExpressionConstant (',' arguments+=ExpressionConstant)*)?
    //	')')?;
    public AnnotationElements getAnnotationAccess() {
        return pAnnotation;
    }

    public ParserRule getAnnotationRule() {
        return getAnnotationAccess().getRule();
    }

    //// === Process Class =======
    //ProcessClass:
    //	annotations+=Annotation*
    //	'process' 'class' name=IDENTIFIER ('(' (parameters+=Declaration (',' parameters+=Declaration)*)?
    //	')')? ('extends' superClass=IDENTIFIER)?
    //	'ports' (ports+=Port (','? ports+=Port)* ','?)?
    //	'messages' ((receiveMessages+=MessageReceiveSignature | sendMessages+=MessageSendSignature) (','?
    //	(receiveMessages+=MessageReceiveSignature | sendMessages+=MessageSendSignature))* ','?)?
    //	'variables' (instanceVariables+=Declaration (','? instanceVariables+=Declaration)* ','?)?
    //	'init' initialMethodCall=ProcessMethodCall?
    //	'methods' methods+=ProcessMethod*;
    public ProcessClassElements getProcessClassAccess() {
        return pProcessClass;
    }

    public ParserRule getProcessClassRule() {
        return getProcessClassAccess().getRule();
    }

    //ProcessMethod:
    //	annotations+=Annotation*
    //	name=IDENTIFIER
    //	'(' (inputParameters+=Declaration (',' inputParameters+=Declaration)*)? ')'
    //	'(' (outputParameters+=Declaration (',' outputParameters+=Declaration)*)? ')' ('|' (localVariables+=Declaration (','
    //	localVariables+=Declaration)*)? '|')?
    //	body=Statement;
    public ProcessMethodElements getProcessMethodAccess() {
        return pProcessMethod;
    }

    public ParserRule getProcessMethodRule() {
        return getProcessMethodAccess().getRule();
    }

    //Port:
    //	name=IDENTIFIER;
    public PortElements getPortAccess() {
        return pPort;
    }

    public ParserRule getPortRule() {
        return getPortAccess().getRule();
    }

    //MessageReceiveSignature MessageSignature:
    //	port=PortReference '?' name=IDENTIFIER ('(' (parameters+=MessageParameter (',' parameters+=MessageParameter)*)?
    //	')')?;
    public MessageReceiveSignatureElements getMessageReceiveSignatureAccess() {
        return pMessageReceiveSignature;
    }

    public ParserRule getMessageReceiveSignatureRule() {
        return getMessageReceiveSignatureAccess().getRule();
    }

    //MessageSendSignature MessageSignature:
    //	port=PortReference '!' name=IDENTIFIER ('(' (parameters+=MessageParameter (',' parameters+=MessageParameter)*)?
    //	')')?;
    public MessageSendSignatureElements getMessageSendSignatureAccess() {
        return pMessageSendSignature;
    }

    public ParserRule getMessageSendSignatureRule() {
        return getMessageSendSignatureAccess().getRule();
    }

    //MessageParameter:
    //	type=IDENTIFIER;
    public MessageParameterElements getMessageParameterAccess() {
        return pMessageParameter;
    }

    public ParserRule getMessageParameterRule() {
        return getMessageParameterAccess().getRule();
    }

    //// === System and Cluster Class =======
    //System ClusterClass:
    //	{ClusterClass} annotations+=Annotation*
    //	'system' ('(' (parameters+=Declaration (',' parameters+=Declaration)*)?
    //	')')? ('ports' (ports+=Port (','? ports+=Port)* ','?)?)?
    //	'instances' instances+=Instance*
    //	'channels' channels+=Channel*;
    public SystemElements getSystemAccess() {
        return pSystem;
    }

    public ParserRule getSystemRule() {
        return getSystemAccess().getRule();
    }

    //ClusterClass:
    //	annotations+=Annotation*
    //	'cluster' 'class' name=IDENTIFIER ('(' (parameters+=Declaration (',' parameters+=Declaration)*)?
    //	')')?
    //	'ports' (ports+=Port (','? ports+=Port)* ','?)?
    //	'instances' instances+=Instance*
    //	'channels' channels+=Channel*;
    public ClusterClassElements getClusterClassAccess() {
        return pClusterClass;
    }

    public ParserRule getClusterClassRule() {
        return getClusterClassAccess().getRule();
    }

    //Instance:
    //	annotations+=Annotation*
    //	name=IDENTIFIER
    //	':' classDefinition=IDENTIFIER ('(' (instanceParameters+=InstanceParameter (','
    //	instanceParameters+=InstanceParameter)*)? ')')?;
    public InstanceElements getInstanceAccess() {
        return pInstance;
    }

    public ParserRule getInstanceRule() {
        return getInstanceAccess().getRule();
    }

    //InstanceParameter:
    //	parameter=IDENTIFIER ':=' expression=Expression;
    public InstanceParameterElements getInstanceParameterAccess() {
        return pInstanceParameter;
    }

    public ParserRule getInstanceParameterRule() {
        return getInstanceParameterAccess().getRule();
    }

    //Channel:
    //	{Channel} annotations+=Annotation*
    //	'{' (instancePorts+=InstancePort (',' instancePorts+=InstancePort)* (',' externalPort=[Port|IDENTIFIER] (','
    //	instancePorts+=InstancePort)*)?
    //	| externalPort=[Port|IDENTIFIER] (',' instancePorts+=InstancePort)*)? '}';
    public ChannelElements getChannelAccess() {
        return pChannel;
    }

    public ParserRule getChannelRule() {
        return getChannelAccess().getRule();
    }

    //InstancePort:
    //	instance=[Instance|IDENTIFIER] '.' port=PortReference;
    public InstancePortElements getInstancePortAccess() {
        return pInstancePort;
    }

    public ParserRule getInstancePortRule() {
        return getInstancePortAccess().getRule();
    }

    //// === Expressions =======
    //Expression:
    //	ExpressionSingle ({ExpressionSequence.expressions+=current} (';' expressions+=ExpressionSingle)+)?;
    public ExpressionElements getExpressionAccess() {
        return pExpression;
    }

    public ParserRule getExpressionRule() {
        return getExpressionAccess().getRule();
    }

    //ExpressionSingle Expression:
    //	ExpressionLevel1;
    public ExpressionSingleElements getExpressionSingleAccess() {
        return pExpressionSingle;
    }

    public ParserRule getExpressionSingleRule() {
        return getExpressionSingleAccess().getRule();
    }

    //ExpressionLevel1 Expression:
    //	ExpressionLevel2
    //	| {AssignmentExpression} variable=IDENTIFIER ':=' expression=ExpressionLevel1 | {ReturnExpression} 'return'
    //	expression=ExpressionLevel1;
    public ExpressionLevel1Elements getExpressionLevel1Access() {
        return pExpressionLevel1;
    }

    public ParserRule getExpressionLevel1Rule() {
        return getExpressionLevel1Access().getRule();
    }

    //ExpressionLevel2 Expression:
    //	ExpressionLevel3 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel2
    //	rightOperand=ExpressionLevel3)*;
    public ExpressionLevel2Elements getExpressionLevel2Access() {
        return pExpressionLevel2;
    }

    public ParserRule getExpressionLevel2Rule() {
        return getExpressionLevel2Access().getRule();
    }

    //ExpressionLevel3 Expression:
    //	ExpressionLevel4 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel3
    //	rightOperand=ExpressionLevel4)*;
    public ExpressionLevel3Elements getExpressionLevel3Access() {
        return pExpressionLevel3;
    }

    public ParserRule getExpressionLevel3Rule() {
        return getExpressionLevel3Access().getRule();
    }

    //ExpressionLevel4 Expression:
    //	ExpressionLevel5 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel4
    //	rightOperand=ExpressionLevel5)*;
    public ExpressionLevel4Elements getExpressionLevel4Access() {
        return pExpressionLevel4;
    }

    public ParserRule getExpressionLevel4Rule() {
        return getExpressionLevel4Access().getRule();
    }

    //ExpressionLevel5 Expression:
    //	ExpressionLevel6 ({DataMethodCallExpression.target=current} onSuperClass?='^'? name=IDENTIFIER ('('
    //	(arguments+=Expression (',' arguments+=Expression)*)? ')')?)*;
    public ExpressionLevel5Elements getExpressionLevel5Access() {
        return pExpressionLevel5;
    }

    public ParserRule getExpressionLevel5Rule() {
        return getExpressionLevel5Access().getRule();
    }

    //ExpressionLevel6 Expression:
    //	=> ExpressionLevel7 | UnaryOperatorExpression | IfExpression | WhileExpression | SwitchExpression
    //	// ExpressionLevel7 should occur before UnaryOperatorExpression, to avoid unary minus when possible
    //;
    public ExpressionLevel6Elements getExpressionLevel6Access() {
        return pExpressionLevel6;
    }

    public ParserRule getExpressionLevel6Rule() {
        return getExpressionLevel6Access().getRule();
    }

    //UnaryOperatorExpression:
    //	name=OperatorUnary operand=ExpressionLevel7;
    public UnaryOperatorExpressionElements getUnaryOperatorExpressionAccess() {
        return pUnaryOperatorExpression;
    }

    public ParserRule getUnaryOperatorExpressionRule() {
        return getUnaryOperatorExpressionAccess().getRule();
    }

    //IfExpression:
    //	'if' condition=Expression 'then' thenClause=Expression ('else' elseClause=Expression)? 'fi';
    public IfExpressionElements getIfExpressionAccess() {
        return pIfExpression;
    }

    public ParserRule getIfExpressionRule() {
        return getIfExpressionAccess().getRule();
    }

    //WhileExpression:
    //	'while' condition=Expression 'do' body=Expression 'od';
    public WhileExpressionElements getWhileExpressionAccess() {
        return pWhileExpression;
    }

    public ParserRule getWhileExpressionRule() {
        return getWhileExpressionAccess().getRule();
    }

    //SwitchExpression:
    //	'switch' expression=Expression
    //	'do' (cases+=SwitchExpressionCase+ ('default' defaultBody=Expression)?
    //	|
    //	'default' defaultBody=Expression)
    //	'od';
    public SwitchExpressionElements getSwitchExpressionAccess() {
        return pSwitchExpression;
    }

    public ParserRule getSwitchExpressionRule() {
        return getSwitchExpressionAccess().getRule();
    }

    //SwitchExpressionCase:
    //	'case' value=Expression 'then' body=Expression;
    public SwitchExpressionCaseElements getSwitchExpressionCaseAccess() {
        return pSwitchExpressionCase;
    }

    public ParserRule getSwitchExpressionCaseRule() {
        return getSwitchExpressionCaseAccess().getRule();
    }

    //ExpressionLevel7 Expression:
    //	CurrentTimeExpression | SelfExpression | ExpressionConstant | NewExpression | VariableExpression |
    //	ExpressionSequenceRoundBracket;
    public ExpressionLevel7Elements getExpressionLevel7Access() {
        return pExpressionLevel7;
    }

    public ParserRule getExpressionLevel7Rule() {
        return getExpressionLevel7Access().getRule();
    }

    //CurrentTimeExpression:
    //	{CurrentTimeExpression} 'currentTime';
    public CurrentTimeExpressionElements getCurrentTimeExpressionAccess() {
        return pCurrentTimeExpression;
    }

    public ParserRule getCurrentTimeExpressionRule() {
        return getCurrentTimeExpressionAccess().getRule();
    }

    //SelfExpression:
    //	{SelfExpression} 'self';
    public SelfExpressionElements getSelfExpressionAccess() {
        return pSelfExpression;
    }

    public ParserRule getSelfExpressionRule() {
        return getSelfExpressionAccess().getRule();
    }

    //ExpressionConstant Expression:
    //	BooleanConstant | CharacterConstant | FloatConstant | IntegerConstant | NilConstant | RealConstant | StringConstant |
    //	EnvironmentConstant;
    public ExpressionConstantElements getExpressionConstantAccess() {
        return pExpressionConstant;
    }

    public ParserRule getExpressionConstantRule() {
        return getExpressionConstantAccess().getRule();
    }

    //BooleanConstant:
    //	value='true' | value='false';
    public BooleanConstantElements getBooleanConstantAccess() {
        return pBooleanConstant;
    }

    public ParserRule getBooleanConstantRule() {
        return getBooleanConstantAccess().getRule();
    }

    //CharacterConstant:
    //	value=CHARACTER;
    public CharacterConstantElements getCharacterConstantAccess() {
        return pCharacterConstant;
    }

    public ParserRule getCharacterConstantRule() {
        return getCharacterConstantAccess().getRule();
    }

    //FloatConstant:
    //	value=FLOAT;
    public FloatConstantElements getFloatConstantAccess() {
        return pFloatConstant;
    }

    public ParserRule getFloatConstantRule() {
        return getFloatConstantAccess().getRule();
    }

    //IntegerConstant:
    //	value=INTEGER;
    public IntegerConstantElements getIntegerConstantAccess() {
        return pIntegerConstant;
    }

    public ParserRule getIntegerConstantRule() {
        return getIntegerConstantAccess().getRule();
    }

    //NilConstant:
    //	{NilConstant} 'nil';
    public NilConstantElements getNilConstantAccess() {
        return pNilConstant;
    }

    public ParserRule getNilConstantRule() {
        return getNilConstantAccess().getRule();
    }

    //RealConstant:
    //	value=REAL;
    public RealConstantElements getRealConstantAccess() {
        return pRealConstant;
    }

    public ParserRule getRealConstantRule() {
        return getRealConstantAccess().getRule();
    }

    //StringConstant:
    //	value=POOSL_STRING;
    public StringConstantElements getStringConstantAccess() {
        return pStringConstant;
    }

    public ParserRule getStringConstantRule() {
        return getStringConstantAccess().getRule();
    }

    //EnvironmentConstant:
    //	value=ENVIRONMENT_VARIABLE_NAME;
    public EnvironmentConstantElements getEnvironmentConstantAccess() {
        return pEnvironmentConstant;
    }

    public ParserRule getEnvironmentConstantRule() {
        return getEnvironmentConstantAccess().getRule();
    }

    //OutputVariable:
    //	variable=IDENTIFIER;
    public OutputVariableElements getOutputVariableAccess() {
        return pOutputVariable;
    }

    public ParserRule getOutputVariableRule() {
        return getOutputVariableAccess().getRule();
    }

    //VariableExpression:
    //	variable=IDENTIFIER;
    public VariableExpressionElements getVariableExpressionAccess() {
        return pVariableExpression;
    }

    public ParserRule getVariableExpressionRule() {
        return getVariableExpressionAccess().getRule();
    }

    //NewExpression:
    //	'new' '(' dataClass=IDENTIFIER ')';
    public NewExpressionElements getNewExpressionAccess() {
        return pNewExpression;
    }

    public ParserRule getNewExpressionRule() {
        return getNewExpressionAccess().getRule();
    }

    //ExpressionSequenceRoundBracket:
    //	'(' expressions+=ExpressionSingle (';' expressions+=ExpressionSingle)* ')';
    public ExpressionSequenceRoundBracketElements getExpressionSequenceRoundBracketAccess() {
        return pExpressionSequenceRoundBracket;
    }

    public ParserRule getExpressionSequenceRoundBracketRule() {
        return getExpressionSequenceRoundBracketAccess().getRule();
    }

    //// --- Restricted expressions for use in statement, split by first token -------
    //IDStartWithinStatementExpressionSingle Expression:
    //	IDStartWithinStatementExpressionLevel1;
    public IDStartWithinStatementExpressionSingleElements getIDStartWithinStatementExpressionSingleAccess() {
        return pIDStartWithinStatementExpressionSingle;
    }

    public ParserRule getIDStartWithinStatementExpressionSingleRule() {
        return getIDStartWithinStatementExpressionSingleAccess().getRule();
    }

    //IDStartWithinStatementExpressionLevel1 Expression:
    //	IDStartWithinStatementExpressionLevel2
    //	| {AssignmentExpression} variable=IDENTIFIER ':=' expression=ExpressionLevel1;
    public IDStartWithinStatementExpressionLevel1Elements getIDStartWithinStatementExpressionLevel1Access() {
        return pIDStartWithinStatementExpressionLevel1;
    }

    public ParserRule getIDStartWithinStatementExpressionLevel1Rule() {
        return getIDStartWithinStatementExpressionLevel1Access().getRule();
    }

    //IDStartWithinStatementExpressionLevel2 Expression:
    //	IDStartWithinStatementExpressionLevel3 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel2
    //	rightOperand=ExpressionLevel3)*;
    public IDStartWithinStatementExpressionLevel2Elements getIDStartWithinStatementExpressionLevel2Access() {
        return pIDStartWithinStatementExpressionLevel2;
    }

    public ParserRule getIDStartWithinStatementExpressionLevel2Rule() {
        return getIDStartWithinStatementExpressionLevel2Access().getRule();
    }

    //IDStartWithinStatementExpressionLevel3 Expression:
    //	IDStartWithinStatementExpressionLevel4 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel3
    //	rightOperand=ExpressionLevel4)*;
    public IDStartWithinStatementExpressionLevel3Elements getIDStartWithinStatementExpressionLevel3Access() {
        return pIDStartWithinStatementExpressionLevel3;
    }

    public ParserRule getIDStartWithinStatementExpressionLevel3Rule() {
        return getIDStartWithinStatementExpressionLevel3Access().getRule();
    }

    //IDStartWithinStatementExpressionLevel4 Expression:
    //	IDStartWithinStatementExpressionLevel5 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel4
    //	rightOperand=ExpressionLevel5)*;
    public IDStartWithinStatementExpressionLevel4Elements getIDStartWithinStatementExpressionLevel4Access() {
        return pIDStartWithinStatementExpressionLevel4;
    }

    public ParserRule getIDStartWithinStatementExpressionLevel4Rule() {
        return getIDStartWithinStatementExpressionLevel4Access().getRule();
    }

    //IDStartWithinStatementExpressionLevel5 Expression:
    //	VariableExpression ({DataMethodCallExpression.target=current} onSuperClass?='^'? name=IDENTIFIER ('('
    //	(arguments+=Expression (',' arguments+=Expression)*)? ')')?)*;
    public IDStartWithinStatementExpressionLevel5Elements getIDStartWithinStatementExpressionLevel5Access() {
        return pIDStartWithinStatementExpressionLevel5;
    }

    public ParserRule getIDStartWithinStatementExpressionLevel5Rule() {
        return getIDStartWithinStatementExpressionLevel5Access().getRule();
    }

    //NonIDStartWithinStatementExpressionSingle Expression:
    //	NonIDStartWithinStatementExpressionLevel1;
    public NonIDStartWithinStatementExpressionSingleElements getNonIDStartWithinStatementExpressionSingleAccess() {
        return pNonIDStartWithinStatementExpressionSingle;
    }

    public ParserRule getNonIDStartWithinStatementExpressionSingleRule() {
        return getNonIDStartWithinStatementExpressionSingleAccess().getRule();
    }

    //NonIDStartWithinStatementExpressionLevel1 Expression:
    //	NonIDStartWithinStatementExpressionLevel2
    //	| {ReturnExpression} 'return' expression=ExpressionLevel1;
    public NonIDStartWithinStatementExpressionLevel1Elements getNonIDStartWithinStatementExpressionLevel1Access() {
        return pNonIDStartWithinStatementExpressionLevel1;
    }

    public ParserRule getNonIDStartWithinStatementExpressionLevel1Rule() {
        return getNonIDStartWithinStatementExpressionLevel1Access().getRule();
    }

    //NonIDStartWithinStatementExpressionLevel2 Expression:
    //	NonIDStartWithinStatementExpressionLevel3 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel2
    //	rightOperand=ExpressionLevel3)*;
    public NonIDStartWithinStatementExpressionLevel2Elements getNonIDStartWithinStatementExpressionLevel2Access() {
        return pNonIDStartWithinStatementExpressionLevel2;
    }

    public ParserRule getNonIDStartWithinStatementExpressionLevel2Rule() {
        return getNonIDStartWithinStatementExpressionLevel2Access().getRule();
    }

    //NonIDStartWithinStatementExpressionLevel3 Expression:
    //	NonIDStartWithinStatementExpressionLevel4 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel3
    //	rightOperand=ExpressionLevel4)*;
    public NonIDStartWithinStatementExpressionLevel3Elements getNonIDStartWithinStatementExpressionLevel3Access() {
        return pNonIDStartWithinStatementExpressionLevel3;
    }

    public ParserRule getNonIDStartWithinStatementExpressionLevel3Rule() {
        return getNonIDStartWithinStatementExpressionLevel3Access().getRule();
    }

    //NonIDStartWithinStatementExpressionLevel4 Expression:
    //	NonIDStartWithinStatementExpressionLevel5 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel4
    //	rightOperand=ExpressionLevel5)*;
    public NonIDStartWithinStatementExpressionLevel4Elements getNonIDStartWithinStatementExpressionLevel4Access() {
        return pNonIDStartWithinStatementExpressionLevel4;
    }

    public ParserRule getNonIDStartWithinStatementExpressionLevel4Rule() {
        return getNonIDStartWithinStatementExpressionLevel4Access().getRule();
    }

    //NonIDStartWithinStatementExpressionLevel5 Expression:
    //	NonIDStartWithinStatementExpressionLevel6 ({DataMethodCallExpression.target=current} onSuperClass?='^'?
    //	name=IDENTIFIER ('(' (arguments+=Expression (',' arguments+=Expression)*)? ')')?)*;
    public NonIDStartWithinStatementExpressionLevel5Elements getNonIDStartWithinStatementExpressionLevel5Access() {
        return pNonIDStartWithinStatementExpressionLevel5;
    }

    public ParserRule getNonIDStartWithinStatementExpressionLevel5Rule() {
        return getNonIDStartWithinStatementExpressionLevel5Access().getRule();
    }

    //NonIDStartWithinStatementExpressionLevel6 Expression:
    //	=> NonIDStartWithinStatementExpressionLevel7 | UnaryOperatorExpression;
    public NonIDStartWithinStatementExpressionLevel6Elements getNonIDStartWithinStatementExpressionLevel6Access() {
        return pNonIDStartWithinStatementExpressionLevel6;
    }

    public ParserRule getNonIDStartWithinStatementExpressionLevel6Rule() {
        return getNonIDStartWithinStatementExpressionLevel6Access().getRule();
    }

    //NonIDStartWithinStatementExpressionLevel7 Expression:
    //	CurrentTimeExpression | SelfExpression | ExpressionConstant | NewExpression;
    public NonIDStartWithinStatementExpressionLevel7Elements getNonIDStartWithinStatementExpressionLevel7Access() {
        return pNonIDStartWithinStatementExpressionLevel7;
    }

    public ParserRule getNonIDStartWithinStatementExpressionLevel7Rule() {
        return getNonIDStartWithinStatementExpressionLevel7Access().getRule();
    }

    //// === Statements =======
    //Statement:
    //	StatementSingle ({StatementSequence.statements+=current} (';' statements+=StatementSingle)+)?;
    public StatementElements getStatementAccess() {
        return pStatement;
    }

    public ParserRule getStatementRule() {
        return getStatementAccess().getRule();
    }

    //StatementSingle Statement:
    //	AbortStatement | DelayStatement | GuardedStatement | InterruptStatement | ParStatement | ProcessMethodCall |
    //	SelStatement | SkipStatement | SendStatement | ReceiveStatement | IfStatement | BracketStartStatement |
    //	WhileStatement | SwitchStatement | ExpressionStatement;
    public StatementSingleElements getStatementSingleAccess() {
        return pStatementSingle;
    }

    public ParserRule getStatementSingleRule() {
        return getStatementSingleAccess().getRule();
    }

    //ExpressionStatement:
    //	expression=IDStartWithinStatementExpressionSingle | expression=NonIDStartWithinStatementExpressionSingle |
    //	'{' expression=Expression '}';
    public ExpressionStatementElements getExpressionStatementAccess() {
        return pExpressionStatement;
    }

    public ParserRule getExpressionStatementRule() {
        return getExpressionStatementAccess().getRule();
    }

    //DelayStatement:
    //	'delay' expression=ExpressionSingle;
    public DelayStatementElements getDelayStatementAccess() {
        return pDelayStatement;
    }

    public ParserRule getDelayStatementRule() {
        return getDelayStatementAccess().getRule();
    }

    //ReceiveStatement:
    //	portDescriptor=PortDescriptor '?' name=IDENTIFIER ('(' (variables+=OutputVariable (',' variables+=OutputVariable)*)?
    //	('|' receptionCondition=Expression)? ')')? ('{' postCommunicationExpression=Expression '}')?;
    public ReceiveStatementElements getReceiveStatementAccess() {
        return pReceiveStatement;
    }

    public ParserRule getReceiveStatementRule() {
        return getReceiveStatementAccess().getRule();
    }

    //SendStatement:
    //	portDescriptor=PortDescriptor '!' name=IDENTIFIER ('(' (arguments+=Expression (',' arguments+=Expression)*)? ')')?
    //	('{' postCommunicationExpression=Expression '}')?;
    public SendStatementElements getSendStatementAccess() {
        return pSendStatement;
    }

    public ParserRule getSendStatementRule() {
        return getSendStatementAccess().getRule();
    }

    //PortDescriptor:
    //	{PortReference} port=IDENTIFIER;
    public PortDescriptorElements getPortDescriptorAccess() {
        return pPortDescriptor;
    }

    public ParserRule getPortDescriptorRule() {
        return getPortDescriptorAccess().getRule();
    }

    //PortReference:
    //	port=IDENTIFIER;
    public PortReferenceElements getPortReferenceAccess() {
        return pPortReference;
    }

    public ParserRule getPortReferenceRule() {
        return getPortReferenceAccess().getRule();
    }

    //SkipStatement:
    //	{SkipStatement} 'skip';
    public SkipStatementElements getSkipStatementAccess() {
        return pSkipStatement;
    }

    public ParserRule getSkipStatementRule() {
        return getSkipStatementAccess().getRule();
    }

    //GuardedStatement:
    //	'[' guard=Expression ']' statement=StatementSingle;
    public GuardedStatementElements getGuardedStatementAccess() {
        return pGuardedStatement;
    }

    public ParserRule getGuardedStatementRule() {
        return getGuardedStatementAccess().getRule();
    }

    //IfStatement:
    //	'if' condition=Expression 'then' thenClause=Statement ('else' elseClause=Statement)? 'fi';
    public IfStatementElements getIfStatementAccess() {
        return pIfStatement;
    }

    public ParserRule getIfStatementRule() {
        return getIfStatementAccess().getRule();
    }

    //WhileStatement:
    //	'while' condition=Expression 'do' body=Statement 'od';
    public WhileStatementElements getWhileStatementAccess() {
        return pWhileStatement;
    }

    public ParserRule getWhileStatementRule() {
        return getWhileStatementAccess().getRule();
    }

    //SwitchStatement:
    //	'switch' expression=Expression
    //	'do' (cases+=SwitchStatementCase+ ('default' defaultBody=Statement)?
    //	|
    //	'default' defaultBody=Statement)
    //	'od';
    public SwitchStatementElements getSwitchStatementAccess() {
        return pSwitchStatement;
    }

    public ParserRule getSwitchStatementRule() {
        return getSwitchStatementAccess().getRule();
    }

    //SwitchStatementCase:
    //	'case' value=Expression 'then' body=Statement;
    public SwitchStatementCaseElements getSwitchStatementCaseAccess() {
        return pSwitchStatementCase;
    }

    public ParserRule getSwitchStatementCaseRule() {
        return getSwitchStatementCaseAccess().getRule();
    }

    //ParStatement:
    //	'par' clauses+=Statement ('and' clauses+=Statement)* 'rap';
    public ParStatementElements getParStatementAccess() {
        return pParStatement;
    }

    public ParserRule getParStatementRule() {
        return getParStatementAccess().getRule();
    }

    //SelStatement:
    //	'sel' clauses+=Statement ('or' clauses+=Statement)* 'les';
    public SelStatementElements getSelStatementAccess() {
        return pSelStatement;
    }

    public ParserRule getSelStatementRule() {
        return getSelStatementAccess().getRule();
    }

    //AbortStatement:
    //	'abort' normalClause=Statement 'with' abortingClause=StatementSingle;
    public AbortStatementElements getAbortStatementAccess() {
        return pAbortStatement;
    }

    public ParserRule getAbortStatementRule() {
        return getAbortStatementAccess().getRule();
    }

    //InterruptStatement:
    //	'interrupt' normalClause=Statement 'with' interruptingClause=StatementSingle;
    public InterruptStatementElements getInterruptStatementAccess() {
        return pInterruptStatement;
    }

    public ParserRule getInterruptStatementRule() {
        return getInterruptStatementAccess().getRule();
    }

    //ProcessMethodCall:
    //	method=IDENTIFIER //method=[ProcessMethod|IDENTIFIER] 
    //	'(' (inputArguments+=Expression (',' inputArguments+=Expression)*)? ')'
    //	'(' (outputVariables+=OutputVariable (',' outputVariables+=OutputVariable)*)? ')';
    public ProcessMethodCallElements getProcessMethodCallAccess() {
        return pProcessMethodCall;
    }

    public ParserRule getProcessMethodCallRule() {
        return getProcessMethodCallAccess().getRule();
    }

    //BracketStartStatement Statement:
    //	=> BracketedArgumentStartExpressionStatement | StatementSequenceRoundBracket
    //	// BracketedDataMethodCallExpressionStatement should occur before SubStatement  
    //;
    public BracketStartStatementElements getBracketStartStatementAccess() {
        return pBracketStartStatement;
    }

    public ParserRule getBracketStartStatementRule() {
        return getBracketStartStatementAccess().getRule();
    }

    //StatementSequenceRoundBracket:
    //	'(' statements+=StatementSingle (';' statements+=StatementSingle)* ')';
    public StatementSequenceRoundBracketElements getStatementSequenceRoundBracketAccess() {
        return pStatementSequenceRoundBracket;
    }

    public ParserRule getStatementSequenceRoundBracketRule() {
        return getStatementSequenceRoundBracketAccess().getRule();
    }

    //BracketedArgumentStartExpressionStatement ExpressionStatement:
    //	expression=BracketedArgumentStartExpressionSingle;
    public BracketedArgumentStartExpressionStatementElements getBracketedArgumentStartExpressionStatementAccess() {
        return pBracketedArgumentStartExpressionStatement;
    }

    public ParserRule getBracketedArgumentStartExpressionStatementRule() {
        return getBracketedArgumentStartExpressionStatementAccess().getRule();
    }

    //BracketedArgumentStartExpressionSingle Expression:
    //	BracketedArgumentStartExpressionLevel1;
    public BracketedArgumentStartExpressionSingleElements getBracketedArgumentStartExpressionSingleAccess() {
        return pBracketedArgumentStartExpressionSingle;
    }

    public ParserRule getBracketedArgumentStartExpressionSingleRule() {
        return getBracketedArgumentStartExpressionSingleAccess().getRule();
    }

    //BracketedArgumentStartExpressionLevel1 Expression:
    //	BracketedArgumentStartExpressionLevel2;
    public BracketedArgumentStartExpressionLevel1Elements getBracketedArgumentStartExpressionLevel1Access() {
        return pBracketedArgumentStartExpressionLevel1;
    }

    public ParserRule getBracketedArgumentStartExpressionLevel1Rule() {
        return getBracketedArgumentStartExpressionLevel1Access().getRule();
    }

    //BracketedArgumentStartExpressionLevel2 Expression:
    //	=>
    //	BracketedArgumentStartExpressionLevel3 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel2
    //	rightOperand=ExpressionLevel3)*
    //	| ExpressionSequenceRoundBracket ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel2
    //	rightOperand=ExpressionLevel3)+;
    public BracketedArgumentStartExpressionLevel2Elements getBracketedArgumentStartExpressionLevel2Access() {
        return pBracketedArgumentStartExpressionLevel2;
    }

    public ParserRule getBracketedArgumentStartExpressionLevel2Rule() {
        return getBracketedArgumentStartExpressionLevel2Access().getRule();
    }

    //BracketedArgumentStartExpressionLevel3 Expression:
    //	=>
    //	BracketedArgumentStartExpressionLevel4 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel3
    //	rightOperand=ExpressionLevel4)*
    //	| ExpressionSequenceRoundBracket ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel3
    //	rightOperand=ExpressionLevel4)+;
    public BracketedArgumentStartExpressionLevel3Elements getBracketedArgumentStartExpressionLevel3Access() {
        return pBracketedArgumentStartExpressionLevel3;
    }

    public ParserRule getBracketedArgumentStartExpressionLevel3Rule() {
        return getBracketedArgumentStartExpressionLevel3Access().getRule();
    }

    //BracketedArgumentStartExpressionLevel4 Expression:
    //	=>
    //	BracketedArgumentStartExpressionLevel5 ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel4
    //	rightOperand=ExpressionLevel5)*
    //	| ExpressionSequenceRoundBracket ({BinaryOperatorExpression.leftOperand=current} name=BinaryOperatorLevel4
    //	rightOperand=ExpressionLevel5)+;
    public BracketedArgumentStartExpressionLevel4Elements getBracketedArgumentStartExpressionLevel4Access() {
        return pBracketedArgumentStartExpressionLevel4;
    }

    public ParserRule getBracketedArgumentStartExpressionLevel4Rule() {
        return getBracketedArgumentStartExpressionLevel4Access().getRule();
    }

    //BracketedArgumentStartExpressionLevel5 Expression:
    //	ExpressionSequenceRoundBracket ({DataMethodCallExpression.target=current} onSuperClass?='^'? name=IDENTIFIER ('('
    //	(arguments+=Expression (',' arguments+=Expression)*)? ')')?)+;
    public BracketedArgumentStartExpressionLevel5Elements getBracketedArgumentStartExpressionLevel5Access() {
        return pBracketedArgumentStartExpressionLevel5;
    }

    public ParserRule getBracketedArgumentStartExpressionLevel5Rule() {
        return getBracketedArgumentStartExpressionLevel5Access().getRule();
    }

    //enum OperatorUnary:
    //	minus='-' | not='!';
    public OperatorUnaryElements getOperatorUnaryAccess() {
        return eOperatorUnary;
    }

    public EnumRule getOperatorUnaryRule() {
        return getOperatorUnaryAccess().getRule();
    }

    //enum OperatorBinary:
    //	equal='=' | unequal='!=' | identical='==' | notIdentical='!==' |
    //	lessThan='<' | atMost='<=' | greaterThan='>' | atLeast='>=' |
    //	add='+' | subtract='-' | and='&' | or='|' |
    //	multiply='*' | divide='/';
    public OperatorBinaryElements getOperatorBinaryAccess() {
        return eOperatorBinary;
    }

    public EnumRule getOperatorBinaryRule() {
        return getOperatorBinaryAccess().getRule();
    }

    //enum BinaryOperatorLevel2 returns OperatorBinary:
    //	equal='=' | unequal='!=' | identical='==' | notIdentical='!==' |
    //	lessThan='<' | atMost='<=' | greaterThan='>' | atLeast='>=';
    public BinaryOperatorLevel2Elements getBinaryOperatorLevel2Access() {
        return eBinaryOperatorLevel2;
    }

    public EnumRule getBinaryOperatorLevel2Rule() {
        return getBinaryOperatorLevel2Access().getRule();
    }

    //enum BinaryOperatorLevel3 returns OperatorBinary:
    //	add='+' | subtract='-' | and='&' | or='|';
    public BinaryOperatorLevel3Elements getBinaryOperatorLevel3Access() {
        return eBinaryOperatorLevel3;
    }

    public EnumRule getBinaryOperatorLevel3Rule() {
        return getBinaryOperatorLevel3Access().getRule();
    }

    //enum BinaryOperatorLevel4 returns OperatorBinary:
    //	multiply='*' | divide='/';
    public BinaryOperatorLevel4Elements getBinaryOperatorLevel4Access() {
        return eBinaryOperatorLevel4;
    }

    public EnumRule getBinaryOperatorLevel4Rule() {
        return getBinaryOperatorLevel4Access().getRule();
    }

    //terminal WS:
    //	' ' | '\t' | '\r' | '\n'+;
    public TerminalRule getWSRule() {
        return tWS;
    }

    //terminal SL_COMMENT:
    //	'//' !('\n' | '\r')* ('\r'? '\n')?;
    public TerminalRule getSL_COMMENTRule() {
        return tSL_COMMENT;
    }

    //terminal fragment MLC_ANY:
    //	!('*' | '/');
    public TerminalRule getMLC_ANYRule() {
        return tMLC_ANY;
    }

    //terminal fragment MLC_SLASH:
    //	'/'+ ('*' MLC_BODY | MLC_ANY);
    public TerminalRule getMLC_SLASHRule() {
        return tMLC_SLASH;
    }

    //terminal fragment MLC_STAR:
    //	'*'+ MLC_ANY;
    public TerminalRule getMLC_STARRule() {
        return tMLC_STAR;
    }

    //terminal fragment MLC_BODY:
    //	(MLC_ANY | MLC_SLASH | MLC_STAR)* '*'+ '/';
    public TerminalRule getMLC_BODYRule() {
        return tMLC_BODY;
    }

    //terminal ML_COMMENT:
    //	'/*' MLC_BODY;
    public TerminalRule getML_COMMENTRule() {
        return tML_COMMENT;
    }

    //terminal BINARY_CORE:
    //	'0' ('b' | 'B') '0'..'1'+;
    public TerminalRule getBINARY_CORERule() {
        return tBINARY_CORE;
    }

    //terminal HEXADECIMAL_CORE:
    //	'0' ('x' | 'X') ('0'..'9' | 'a'..'f' | 'A'..'F')+;
    public TerminalRule getHEXADECIMAL_CORERule() {
        return tHEXADECIMAL_CORE;
    }

    //terminal fragment DIGITS:
    //	'0'..'9' | '1'..'9' '0'..'9'+;
    public TerminalRule getDIGITSRule() {
        return tDIGITS;
    }

    //terminal DECIMAL_CORE:
    //	DIGITS (('e' | 'E') '+'? '0'..'9'+)?;
    public TerminalRule getDECIMAL_CORERule() {
        return tDECIMAL_CORE;
    }

    //terminal REAL_CORE:
    //	(DIGITS '.' '0'..'9'*
    //	| '.' '0'..'9'+) (('e' | 'E') ('+' | '-')? '0'..'9'+)?;
    public TerminalRule getREAL_CORERule() {
        return tREAL_CORE;
    }

    //terminal FLOAT_CORE:
    //	REAL_CORE ('f' | 'F');
    public TerminalRule getFLOAT_CORERule() {
        return tFLOAT_CORE;
    }

    //// only the parser can distinguish between unary and binary '-'
    //INTEGER:
    //	('-' | '+')? (DECIMAL_CORE | BINARY_CORE | HEXADECIMAL_CORE);
    public INTEGERElements getINTEGERAccess() {
        return pINTEGER;
    }

    public ParserRule getINTEGERRule() {
        return getINTEGERAccess().getRule();
    }

    //REAL:
    //	('-' | '+')? REAL_CORE;
    public REALElements getREALAccess() {
        return pREAL;
    }

    public ParserRule getREALRule() {
        return getREALAccess().getRule();
    }

    //FLOAT:
    //	('-' | '+')? (FLOAT_CORE | 'nan' | 'inf');
    public FLOATElements getFLOATAccess() {
        return pFLOAT;
    }

    public ParserRule getFLOATRule() {
        return getFLOATAccess().getRule();
    }

    //IDENTIFIER:
    //	IDENTIFIER_CORE
    //	| 'class' | 'extends'
    //	| 'variables' | 'methods' | 'ports' | 'messages' | 'init' | 'channels' | 'instances';
    public IDENTIFIERElements getIDENTIFIERAccess() {
        return pIDENTIFIER;
    }

    public ParserRule getIDENTIFIERRule() {
        return getIDENTIFIERAccess().getRule();
    }

    //terminal IDENTIFIER_CORE:
    //	('a'..'z' | 'A'..'Z') ('a'..'z' | 'A'..'Z' | '0'..'9' | '_')*;
    public TerminalRule getIDENTIFIER_CORERule() {
        return tIDENTIFIER_CORE;
    }

    //terminal CHARACTER:
    //	"'" CHARACTER_ELEMENT "'";
    public TerminalRule getCHARACTERRule() {
        return tCHARACTER;
    }

    //terminal POOSL_STRING:
    //	'"' POOSL_STRING_ELEMENT* '"';
    public TerminalRule getPOOSL_STRINGRule() {
        return tPOOSL_STRING;
    }

    //terminal ENVIRONMENT_VARIABLE_NAME:
    //	'${' ('0'..'9' | 'a'..'z' | 'A'..'Z' | '_')+ '}';
    public TerminalRule getENVIRONMENT_VARIABLE_NAMERule() {
        return tENVIRONMENT_VARIABLE_NAME;
    }

    //terminal fragment CHARACTER_ELEMENT:
    //	'\\u0009' // include TAB (= 0009)
    //	| '\\u0020'..'\\u0026' | '\\u0028'..'\\u005B' | '\\u005D'..'\\u00FF' // exclude ' (= 0027) and \ (= 005C) 
    //	| ESCAPE_SEQUENCE
    //	| ESCAPE_ZERO;
    public TerminalRule getCHARACTER_ELEMENTRule() {
        return tCHARACTER_ELEMENT;
    }

    //terminal fragment POOSL_STRING_ELEMENT:
    //	'\\u0009' // include TAB (= 0009)
    //	| '\\u0020'..'\\u0021' | '\\u0023'..'\\u005B' | '\\u005D'..'\\u00FF' // exclude " (= 0022) and \ (= 005C)
    //	| ESCAPE_SEQUENCE;
    public TerminalRule getPOOSL_STRING_ELEMENTRule() {
        return tPOOSL_STRING_ELEMENT;
    }

    //terminal fragment ESCAPE_SEQUENCE:
    //	'\\x' '0' ('1'..'9' | 'a'..'f' | 'A'..'F') | '\\x' ('1'..'9' | 'a'..'f' | 'A'..'F') ('0'..'9' | 'a'..'f' | 'A'..'F')?
    //	| '\\n' | '\\t' | '\\v' | '\\b' | '\\r' | '\\f' | '\\a' | '\\\\' | '\\?' | '\\\'' | '\\\"';
    public TerminalRule getESCAPE_SEQUENCERule() {
        return tESCAPE_SEQUENCE;
    }

    //terminal fragment ESCAPE_ZERO:
    //	'\\x' '0' '0'?;
    public TerminalRule getESCAPE_ZERORule() {
        return tESCAPE_ZERO;
    }
}
