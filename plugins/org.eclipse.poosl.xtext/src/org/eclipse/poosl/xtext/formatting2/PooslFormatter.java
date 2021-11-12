/*******************************************************************************
 * Copyright (c) 2021 TNO/ESI
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
package org.eclipse.poosl.xtext.formatting2;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.poosl.AbortStatement;
import org.eclipse.poosl.Annotation;
import org.eclipse.poosl.AssignmentExpression;
import org.eclipse.poosl.BinaryOperatorExpression;
import org.eclipse.poosl.BooleanConstant;
import org.eclipse.poosl.Channel;
import org.eclipse.poosl.ClusterClass;
import org.eclipse.poosl.DataClass;
import org.eclipse.poosl.DataMethod;
import org.eclipse.poosl.DataMethodBinaryOperator;
import org.eclipse.poosl.DataMethodCallExpression;
import org.eclipse.poosl.DataMethodNamed;
import org.eclipse.poosl.DataMethodUnaryOperator;
import org.eclipse.poosl.Declaration;
import org.eclipse.poosl.DelayStatement;
import org.eclipse.poosl.EnvironmentConstant;
import org.eclipse.poosl.Expression;
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
import org.eclipse.poosl.MessageSignature;
import org.eclipse.poosl.NewExpression;
import org.eclipse.poosl.NilConstant;
import org.eclipse.poosl.ParStatement;
import org.eclipse.poosl.Poosl;
import org.eclipse.poosl.PooslPackage.Literals;
import org.eclipse.poosl.Port;
import org.eclipse.poosl.ProcessClass;
import org.eclipse.poosl.ProcessMethod;
import org.eclipse.poosl.ProcessMethodCall;
import org.eclipse.poosl.RealConstant;
import org.eclipse.poosl.ReceiveStatement;
import org.eclipse.poosl.ReturnExpression;
import org.eclipse.poosl.SelStatement;
import org.eclipse.poosl.SendStatement;
import org.eclipse.poosl.Statement;
import org.eclipse.poosl.StatementSequence;
import org.eclipse.poosl.StatementSequenceRoundBracket;
import org.eclipse.poosl.StringConstant;
import org.eclipse.poosl.SwitchExpression;
import org.eclipse.poosl.SwitchExpressionCase;
import org.eclipse.poosl.SwitchStatement;
import org.eclipse.poosl.SwitchStatementCase;
import org.eclipse.poosl.UnaryOperatorExpression;
import org.eclipse.poosl.WhileExpression;
import org.eclipse.poosl.WhileStatement;
import org.eclipse.poosl.xtext.services.PooslGrammarAccess;
import org.eclipse.poosl.xtext.services.PooslGrammarAccess.ClusterClassElements;
import org.eclipse.poosl.xtext.services.PooslGrammarAccess.ProcessClassElements;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.formatting2.AbstractFormatter2;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.formatting2.IHiddenRegionFormatter;
import org.eclipse.xtext.formatting2.IHiddenRegionFormatting;
import org.eclipse.xtext.formatting2.ITextReplacer;
import org.eclipse.xtext.formatting2.regionaccess.IComment;
import org.eclipse.xtext.formatting2.regionaccess.IEObjectRegion;
import org.eclipse.xtext.formatting2.regionaccess.IHiddenRegion;
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegion;
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegionsFinder;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

import com.google.inject.Inject;

/**
 * The PooslFormatter.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
@SuppressWarnings("restriction") // AbstractFormatter2 is public in newer version
public class PooslFormatter extends AbstractFormatter2 {

    private static final ILog LOGGER = Platform.getLog(PooslFormatter.class);

    private static final String KEYWORD_OD = "od"; //$NON-NLS-1$

    private static final String KEYWORD_DO = "do"; //$NON-NLS-1$

    private static final String KEYWORD_SWITCH = "switch"; //$NON-NLS-1$

    private static final String KEYWORD_FI = "fi"; //$NON-NLS-1$

    private static final String KEYWORD_ELSE = "else"; //$NON-NLS-1$

    private static final String KEYWORD_THEN = "then"; //$NON-NLS-1$

    private static final String KEYWORD_IF = "if"; //$NON-NLS-1$

    private static final String KEYWORD_CLUSTER = "cluster"; //$NON-NLS-1$

    private static final String PAR_CLOSE = ")"; //$NON-NLS-1$

    private static final String PAR_OPEN = "("; //$NON-NLS-1$

    private static final String KEYWORD_PORTS = "ports"; //$NON-NLS-1$

    private static final String KEYWORD_METHODS = "methods"; //$NON-NLS-1$

    private static final String KEYWORD_CLASS = "class"; //$NON-NLS-1$

    private static final String KEYWORD_DATA = "data"; //$NON-NLS-1$

    private static final String KEYWORD_VARIABLES = "variables"; //$NON-NLS-1$

    private static final Procedure1<IHiddenRegionFormatter> NO_SPACE = it -> it.noSpace();

    private static final Procedure1<IHiddenRegionFormatter> ONE_SPACE = it -> {
        it.oneSpace();
        it.autowrap();
    };

    private static final Procedure1<IHiddenRegionFormatter> ONE_SPACE_NO_WRAP = it -> it.oneSpace();

    private static final Procedure1<IHiddenRegionFormatter> NEW_LINE = it -> it.newLine();

    private static final Procedure1<IHiddenRegionFormatter> EMPTY_LINE = it -> it.setNewLines(2);

    private static final Procedure1<IHiddenRegionFormatter> EMPTY_LINES = it -> it.setNewLines(3);

    private static final Procedure1<IHiddenRegionFormatter> INDENT = it -> it.indent();

    private static final Procedure1<IHiddenRegionFormatter> DOUBLE_INDENT = it -> {
        it.indent();
        it.indent();
    };

    /** Grammar. */
    @Inject
    @Extension
    protected PooslGrammarAccess pooslGrammar;

    // Use own multiline comment replacer to place multiline comments /* */
    // always on a new line
    @Override
    public ITextReplacer createCommentReplacer(IComment comment) {
        EObject grammarElement = comment.getGrammarElement();
        if (grammarElement instanceof AbstractRule) {
            String ruleName = ((AbstractRule) grammarElement).getName();
            if (ruleName.startsWith("ML")) //$NON-NLS-1$
                return new PooslMultilineCommentReplacer(comment, '*');
        }
        return super.createCommentReplacer(comment);
    }

    @Override
    public ITextReplacer createHiddenRegionReplacer(
            IHiddenRegion region, IHiddenRegionFormatting formatting) {
        return new PooslHiddenRegionReplacer(region, formatting);
    }

    @Override
    public void format(Object obj, @Extension IFormattableDocument document) {
        if (obj instanceof Poosl) {
            format((Poosl) obj, document);
        } else if (obj instanceof XtextResource) {
            List<EObject> contents = ((XtextResource) obj).getContents();
            if (!contents.isEmpty()) {
                EObject model = contents.get(0);
                format(model, document);
            }
        } else {
            LOGGER.warn("Format not specific handled : " + obj.getClass().getName());
        }
    }

    // --- Format -------

    private void format(Poosl poosl, IFormattableDocument document) {
        for (Import imports : poosl.getImports()) {
            format(imports, document);
        }
        for (DataClass dataClasses : poosl.getDataClasses()) {
            format(dataClasses, document);
        }
        for (ProcessClass processClasses : poosl.getProcessClasses()) {
            format(processClasses, document);
        }
        for (ClusterClass clusterClasses : poosl.getClusterClasses()) {
            format(clusterClasses, document);
        }
    }

    private void format(Import imports, IFormattableDocument document) {
        ISemanticRegion previousSemantic = textRegionExtensions.regionForEObject(imports)
                .getPreviousSemanticRegion();
        if (previousSemantic != null) {
            document.prepend(imports, NEW_LINE);
        }
        document.prepend(imports, NO_SPACE);
    }

    private void format(ClusterClass cluster, IFormattableDocument document) {
        topElementEmptyLine(cluster, document);
        ISemanticRegionsFinder regionFinder = textRegionExtensions.regionFor(cluster);
        format(cluster.getAnnotations(), document);

        ClusterClassElements grammar = pooslGrammar.getClusterClassAccess();
        Procedure1<IHiddenRegionFormatter> oneSpaceWrap = createOneSpaceWrapUpTo(
                regionFinder.keyword(KEYWORD_PORTS), INDENT);

        document.append(regionFinder.keyword(KEYWORD_CLUSTER), oneSpaceWrap);
        document.append(regionFinder.keyword(KEYWORD_CLASS), oneSpaceWrap);
        document.append(regionFinder.feature(Literals.INSTANTIABLE_CLASS__NAME), NO_SPACE);
        keywordsOnNewLine(cluster, document, KEYWORD_PORTS, "instances", "channels"); //$NON-NLS-1$ //$NON-NLS-2$
        commaSpacing(regionFinder, oneSpaceWrap, document);

        // difference between comma's in cluster class
        commaSpacing(regionFinder, grammar.getCommaKeyword_4_1_1_0(), oneSpaceWrap, document); // process parameters
        commaSpacing(regionFinder, grammar.getCommaKeyword_6_1_0(), document); // ports
        commaSpacing(regionFinder, grammar.getCommaKeyword_6_2(), document);

        List<Pair<ISemanticRegion, ISemanticRegion>> brackets = regionFinder.keywordPairs(PAR_OPEN,
                PAR_CLOSE);
        if (!brackets.isEmpty()) {
            formatBracketDeclarations(cluster.getParameters(), brackets.get(0), oneSpaceWrap,
                    document);
        }

        indentList(cluster.getPorts(), document);

        for (Port ports : cluster.getPorts()) {
            format(ports, document);
        }

        indentList(cluster.getInstances(), document);
        for (Instance instances : cluster.getInstances()) {
            format(instances, document);
        }

        indentList(cluster.getChannels(), document);
        for (Channel channels : cluster.getChannels()) {
            format(channels, document);
        }
    }

    private void format(DataClass data, IFormattableDocument document) {
        topElementEmptyLine(data, document);
        ISemanticRegionsFinder regionFinder = textRegionExtensions.regionFor(data);
        format(data.getAnnotations(), document);
        Procedure1<IHiddenRegionFormatter> oneSpaceWrap = createOneSpaceWrapUpTo(
                regionFinder.keyword(KEYWORD_VARIABLES), INDENT);

        document.append(regionFinder.keyword(KEYWORD_DATA), oneSpaceWrap);
        document.append(regionFinder.keyword(KEYWORD_CLASS), oneSpaceWrap);
        formatExtend(regionFinder, oneSpaceWrap, document);

        commaSpacing(regionFinder, ONE_SPACE, document);
        keywordsOnNewLine(data, document, KEYWORD_VARIABLES, KEYWORD_METHODS);

        indentList(data.getInstanceVariables(), document);
        for (Declaration instanceVariables : data.getInstanceVariables()) {
            document.prepend(instanceVariables, NEW_LINE);
            format(instanceVariables, createOneSpaceWrap(instanceVariables), document);
        }

        List<EList<? extends EObject>> dataMethodLists = new ArrayList<>();
        dataMethodLists.add(data.getDataMethodsNamed());
        dataMethodLists.add(data.getDataMethodsUnaryOperator());
        dataMethodLists.add(data.getDataMethodsBinaryOperator());
        EObject first = indentLists(document, dataMethodLists);

        for (DataMethodNamed dataMethodsNamed : data.getDataMethodsNamed()) {
            format(dataMethodsNamed, document, first == dataMethodsNamed);
        }

        for (DataMethodUnaryOperator dataMethodsUnaryOperator : data
                .getDataMethodsUnaryOperator()) {
            format(dataMethodsUnaryOperator, document, first == dataMethodsUnaryOperator);
        }

        for (DataMethodBinaryOperator dataMethodsBinaryOperator : data
                .getDataMethodsBinaryOperator()) {
            format(dataMethodsBinaryOperator, document, first == dataMethodsBinaryOperator);
        }
    }

    private void format(DataMethod dMethod, IFormattableDocument document, boolean firstMethod) {
        document.prepend(dMethod, firstMethod ? NEW_LINE : EMPTY_LINE);
        ISemanticRegionsFinder mRegionFinder = textRegionExtensions.regionFor(dMethod);
        format(dMethod.getAnnotations(), document);

        Procedure1<IHiddenRegionFormatter> oneSpaceWrap = (dMethod.getBody() == null)
            ? createOneSpaceWrapDouble(dMethod) : createOneSpaceUpTo(dMethod.getBody());

        bracketPairSpacing(mRegionFinder, document);
        commaSpacing(mRegionFinder, oneSpaceWrap, document);
        colonSpacing(mRegionFinder, document);

        List<Pair<ISemanticRegion, ISemanticRegion>> keywordPairs = mRegionFinder
                .keywordPairs(PAR_OPEN, PAR_CLOSE);
        if (!keywordPairs.isEmpty()) {
            formatBracketDeclarations(dMethod.getParameters(), keywordPairs.get(0), oneSpaceWrap,
                    document);
        }
        formatPipeDeclarations(dMethod.getLocalVariables(), mRegionFinder, oneSpaceWrap, document);

        document.prepend(dMethod.getBody(), NEW_LINE);
        indent(dMethod.getBody(), document, false);
        format(dMethod.getBody(), document, null, false);
    }

    private void format(ProcessClass process, IFormattableDocument document) {
        topElementEmptyLine(process, document);
        ISemanticRegionsFinder regionFinder = textRegionExtensions.regionFor(process);
        format(process.getAnnotations(), document);

        ProcessClassElements grammar = pooslGrammar.getProcessClassAccess();
        Procedure1<IHiddenRegionFormatter> oneSpaceWrap = createOneSpaceWrapUpTo(
                regionFinder.keyword(KEYWORD_PORTS), INDENT);

        document.append(regionFinder.keyword("process"), oneSpaceWrap); //$NON-NLS-1$
        document.append(regionFinder.keyword(KEYWORD_CLASS), oneSpaceWrap);

        // difference between comma's in process class
        commaSpacing(regionFinder, grammar.getCommaKeyword_4_1_1_0(), oneSpaceWrap, document); // process parameters
        commaSpacing(regionFinder, grammar.getCommaKeyword_7_1_0(), document); // ports
        commaSpacing(regionFinder, grammar.getCommaKeyword_7_2(), document);
        commaSpacing(regionFinder, grammar.getCommaKeyword_9_1_0(), document); // messages
        commaSpacing(regionFinder, grammar.getCommaKeyword_9_2(), document);
        commaSpacing(regionFinder, grammar.getCommaKeyword_11_1_0(), document); // variables
        commaSpacing(regionFinder, grammar.getCommaKeyword_11_2(), document);

        formatExtend(regionFinder, oneSpaceWrap, document);
        keywordsOnNewLine(process, document, KEYWORD_PORTS, "messages", KEYWORD_VARIABLES, "init", //$NON-NLS-1$//$NON-NLS-2$
                KEYWORD_METHODS);

        List<Pair<ISemanticRegion, ISemanticRegion>> brackets = regionFinder.keywordPairs(PAR_OPEN,
                PAR_CLOSE);
        if (!brackets.isEmpty()) {
            document.prepend(brackets.get(0).getKey(), NO_SPACE);
            formatBracketDeclarations(process.getParameters(), brackets.get(0), oneSpaceWrap,
                    document);
        }

        indentList(process.getPorts(), document);
        for (Port ports : process.getPorts()) {
            format(ports, document);
        }

        List<EList<? extends EObject>> messageLists = new ArrayList<>();
        messageLists.add(process.getSendMessages());
        messageLists.add(process.getReceiveMessages());
        indentLists(document, messageLists);

        for (MessageSignature receiveMessages : process.getReceiveMessages()) {
            format(receiveMessages, document);
        }
        for (MessageSignature sendMessages : process.getSendMessages()) {
            format(sendMessages, document);
        }

        indentList(process.getInstanceVariables(), document);
        for (Declaration instanceVariables : process.getInstanceVariables()) {
            document.prepend(instanceVariables, NEW_LINE);
            format(instanceVariables, createOneSpaceWrap(instanceVariables), document);
        }

        if (process.getInitialMethodCall() != null) {
            document.prepend(process.getInitialMethodCall(), NEW_LINE);
            indent(process.getInitialMethodCall(), document, false);
            format(process.getInitialMethodCall(), document, null, false);
        }

        indentList(process.getMethods(), document);
        boolean firstMethod = true;

        for (ProcessMethod method : process.getMethods()) {
            format(method, document, firstMethod);
            firstMethod = false;
        }
    }

    private void format(ProcessMethod method, IFormattableDocument document, boolean firstMethod) {
        ISemanticRegionsFinder mRegionFinder = textRegionExtensions.regionFor(method);
        format(method.getAnnotations(), document);
        format(method.getAnnotations(), document);

        document.prepend(method, firstMethod ? NEW_LINE : EMPTY_LINE);
        document.append(mRegionFinder.feature(Literals.PROCESS_METHOD__NAME), NO_SPACE);
        bracketPairSpacing(mRegionFinder, document);

        Procedure1<IHiddenRegionFormatter> oneSpaceWrapMethod = (method.getBody() == null)
            ? createOneSpaceWrapDouble(method) : createOneSpaceUpTo(method.getBody());

        commaSpacing(mRegionFinder, oneSpaceWrapMethod, document);

        List<Pair<ISemanticRegion, ISemanticRegion>> mBrackets = mRegionFinder
                .keywordPairs(PAR_OPEN, PAR_CLOSE);
        if (mBrackets.size() == 2) {
            document.append(mBrackets.get(0).getValue(), NO_SPACE);
            formatBracketDeclarations(method.getInputParameters(), mBrackets.get(0),
                    oneSpaceWrapMethod, document);
            formatBracketDeclarations(method.getOutputParameters(), mBrackets.get(1),
                    oneSpaceWrapMethod, document);
        }
        formatPipeDeclarations(method.getLocalVariables(), mRegionFinder, oneSpaceWrapMethod,
                document);

        document.prepend(method.getBody(), NEW_LINE);
        indent(method.getBody(), document, false);
        format(method.getBody(), document, null, false);
    }

    private void formatExtend(
            ISemanticRegionsFinder regionFinder, Procedure1<IHiddenRegionFormatter> oneSpaceWrap,
            IFormattableDocument document) {
        ISemanticRegion kwExtend = regionFinder.keyword("extends"); //$NON-NLS-1$
        if (kwExtend != null) {
            document.prepend(kwExtend, oneSpaceWrap);
            document.append(kwExtend, oneSpaceWrap);
        }
    }

    private void format(Port port, IFormattableDocument document) {
        document.prepend(port, NEW_LINE);
    }

    private void format(Instance instance, IFormattableDocument document) {
        document.prepend(instance, NEW_LINE);
        ISemanticRegionsFinder regionFinder = textRegionExtensions.regionFor(instance);
        format(instance.getAnnotations(), document);

        document.append(regionFinder.feature(Literals.INSTANCE__CLASS_DEFINITION), NO_SPACE);
        colonSpacing(regionFinder, document);
        bracketPairSpacing(regionFinder, document);

        Procedure1<IHiddenRegionFormatter> oneSpaceWrap = createOneSpaceWrap(instance);
        commaSpacing(regionFinder, oneSpaceWrap, document);

        for (InstanceParameter instanceParameter : instance.getInstanceParameters()) {
            document.append(textRegionExtensions.regionFor(instanceParameter)
                    .feature(Literals.INSTANCE_PARAMETER__PARAMETER), oneSpaceWrap);
            document.prepend(instanceParameter.getExpression(), oneSpaceWrap);
            format(instanceParameter.getExpression(), document, oneSpaceWrap, true);
        }
    }

    private void format(MessageSignature signature, IFormattableDocument document) {
        document.prepend(signature, NEW_LINE);
        ISemanticRegionsFinder regionFinder = textRegionExtensions.regionFor(signature);
        document.append(regionFinder.feature(Literals.MESSAGE_SIGNATURE__NAME), NO_SPACE);
        exclamationSpacing(regionFinder, document);
        questionMarkSpacing(regionFinder, document);
        bracketPairSpacing(regionFinder, document);
        commaSpacing(regionFinder, createOneSpaceWrapDouble(signature), document);
    }

    private void format(Channel channel, IFormattableDocument document) {
        document.prepend(channel, NEW_LINE);
        format(channel.getAnnotations(), document);
        ISemanticRegionsFinder regionFinder = textRegionExtensions.regionFor(channel);
        curlyBracketPairSpacing(regionFinder, document);

        Procedure1<IHiddenRegionFormatter> oneSpaceWrap = createOneSpaceWrap(channel);
        // XXX commaspacing needs to use oneSpaceWrap, atm causes an extra indent,
        // fixed in xtext 2.13
        // https://esi-redmine.tno.nl/issues/2605
        commaSpacing(regionFinder, oneSpaceWrap, document);

        for (InstancePort instancePort : channel.getInstancePorts()) {
            periodSpacing(textRegionExtensions.regionFor(instancePort), document);
        }
    }

    private void format(
            Declaration declaration, Procedure1<? super IHiddenRegionFormatter> oneSpaceWrapper,
            IFormattableDocument document) {
        ISemanticRegionsFinder regionFinder = textRegionExtensions.regionFor(declaration);
        colonSpacing(regionFinder, document);
        commaSpacing(regionFinder, oneSpaceWrapper, document);
    }

    private void formatNewLine(
            Declaration declaration, Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
            IFormattableDocument document) {
        ISemanticRegionsFinder regionFinder = textRegionExtensions.regionFor(declaration);
        document.prepend(declaration, NEW_LINE);
        colonSpacing(regionFinder, document);
        commaSpacing(regionFinder, oneSpaceWrapper, document);
    }

    private void format(
            Expression expression, IFormattableDocument document,
            Procedure1<IHiddenRegionFormatter> wrapper, boolean singleLine) {
        ISemanticRegionsFinder regionFinder = textRegionExtensions.regionFor(expression);
        if (expression instanceof ExpressionSequence) {
            EList<Expression> expressions = ((ExpressionSequence) expression).getExpressions();
            if (expression instanceof ExpressionSequenceRoundBracket) {
                formatRoundBracketSequence(expressions, document, regionFinder, singleLine);
            }
            semiColonSpacing(regionFinder, document, singleLine);
            for (Expression expr : expressions) {
                format(expr, document, wrapper, singleLine);
            }
        } else {
            Procedure1<IHiddenRegionFormatter> oneSpaceWrapper = (wrapper == null)
                ? createOneSpaceWrap(expression) : wrapper;

            if (expression instanceof AssignmentExpression) {
                document.surround(regionFinder.keyword(":="), oneSpaceWrapper); //$NON-NLS-1$
                format(((AssignmentExpression) expression).getExpression(), document,
                        oneSpaceWrapper, singleLine);
            } else if (expression instanceof ReturnExpression) {
                document.append(regionFinder.keyword("return"), oneSpaceWrapper); //$NON-NLS-1$
                format(((ReturnExpression) expression).getExpression(), document, oneSpaceWrapper,
                        singleLine);
            } else if (expression instanceof BinaryOperatorExpression) {
                format((BinaryOperatorExpression) expression, document, oneSpaceWrapper,
                        singleLine);
            } else if (expression instanceof DataMethodCallExpression) {
                format((DataMethodCallExpression) expression, document, regionFinder,
                        oneSpaceWrapper, singleLine);
            } else if (expression instanceof UnaryOperatorExpression) {
                format((UnaryOperatorExpression) expression, document, oneSpaceWrapper, singleLine);
            } else if (expression instanceof IfExpression) {
                format((IfExpression) expression, document, regionFinder, oneSpaceWrapper,
                        singleLine);
            } else if (expression instanceof WhileExpression) {
                format((WhileExpression) expression, document, regionFinder, oneSpaceWrapper,
                        singleLine);
            } else if (expression instanceof SwitchExpression) {
                format((SwitchExpression) expression, document, regionFinder, oneSpaceWrapper,
                        singleLine);
            } else if (expression instanceof NewExpression) {
                format((NewExpression) expression, document, oneSpaceWrapper, regionFinder);
            }
        }
    }

    private void format(
            IfExpression expression, IFormattableDocument document,
            ISemanticRegionsFinder regionFinder, Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
            boolean singleLine) {
        document.append(regionFinder.keyword(KEYWORD_IF), ONE_SPACE_NO_WRAP);
        ISemanticRegion kwThen = regionFinder.keyword(KEYWORD_THEN);
        Procedure1<IHiddenRegionFormatter> oneSpaceCondition = getOneSpaceWrapIncluding(kwThen);

        Procedure1<IHiddenRegionFormatter> lineFormat = getLineFormatting(singleLine,
                oneSpaceWrapper);
        document.prepend(kwThen, oneSpaceCondition);
        document.append(kwThen, lineFormat);

        ISemanticRegion kwElse = regionFinder.keyword(KEYWORD_ELSE);
        ISemanticRegion kwFi = regionFinder.keyword(KEYWORD_FI);
        if (kwElse != null) {
            document.prepend(kwElse, lineFormat);
            ISemanticRegion nextSemanticRegion = kwElse.getNextSemanticRegion();
            if (nextSemanticRegion != null && nextSemanticRegion.getText().equals(KEYWORD_IF)) {
                // else if
                document.append(kwElse, oneSpaceWrapper);
                document.prepend(kwFi, oneSpaceWrapper);
            } else {
                document.append(kwElse, lineFormat);
                document.prepend(kwFi, lineFormat);
                indent(expression.getElseClause(), document, singleLine);
            }
        } else {
            document.prepend(kwFi, lineFormat);
        }

        indent(expression.getThenClause(), document, singleLine);
        format(expression.getCondition(), document, oneSpaceCondition, singleLine);
        format(expression.getThenClause(), document, null, singleLine);
        format(expression.getElseClause(), document, null, singleLine);
    }

    private void format(
            SwitchExpression expression, IFormattableDocument document,
            ISemanticRegionsFinder regionFinder, Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
            boolean singleLine) {
        document.append(regionFinder.keyword(KEYWORD_SWITCH), oneSpaceWrapper);
        List<Pair<ISemanticRegion, ISemanticRegion>> keywordPairs = regionFinder
                .keywordPairs(KEYWORD_DO, KEYWORD_OD);

        Procedure1<IHiddenRegionFormatter> oneSpaceWrapperCondition = getOneSpaceWrapIncluding(
                keywordPairs.isEmpty() ? null : keywordPairs.get(0).getKey());

        Procedure1<IHiddenRegionFormatter> lineFormat = getLineFormatting(singleLine,
                oneSpaceWrapper);
        for (Pair<ISemanticRegion, ISemanticRegion> pair : keywordPairs) {
            document.prepend(pair.getKey(), oneSpaceWrapperCondition);
            document.prepend(pair.getValue(), lineFormat);
            document.interior(pair.getKey(), pair.getValue(), INDENT);
        }
        format(expression.getExpression(), document, oneSpaceWrapperCondition, singleLine);

        for (SwitchExpressionCase switchExpressionCase : expression.getCases()) {
            ISemanticRegionsFinder cRegionFinder = textRegionExtensions
                    .regionFor(switchExpressionCase);
            ISemanticRegion kwCase = cRegionFinder.keyword("case"); //$NON-NLS-1$
            document.prepend(kwCase, lineFormat);

            ISemanticRegion kwThen = cRegionFinder.keyword(KEYWORD_THEN);
            Procedure1<IHiddenRegionFormatter> oneSpaceCaseCondition = getOneSpaceWrapIncluding(
                    kwThen);

            document.append(kwCase, oneSpaceCaseCondition);
            document.prepend(kwThen, oneSpaceCaseCondition);
            document.prepend(switchExpressionCase.getBody(), lineFormat);

            indent(switchExpressionCase.getBody(), document, singleLine);
            format(switchExpressionCase.getValue(), document, oneSpaceCaseCondition, singleLine);
            format(switchExpressionCase.getBody(), document, null, singleLine);
        }

        ISemanticRegion kwDefault = regionFinder.keyword("default"); //$NON-NLS-1$
        document.prepend(kwDefault, lineFormat);
        document.append(kwDefault, lineFormat);
        indent(expression.getDefaultBody(), document, singleLine);

        format(expression.getDefaultBody(), document, null, singleLine);
    }

    private void format(
            WhileExpression expression, IFormattableDocument document,
            ISemanticRegionsFinder regionFinder, Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
            boolean singleLine) {
        document.append(regionFinder.keyword("while"), oneSpaceWrapper); //$NON-NLS-1$
        ISemanticRegion kwDo = regionFinder.keyword(KEYWORD_DO);
        Procedure1<IHiddenRegionFormatter> oneSpaceWrapperCond = getOneSpaceWrapIncluding(kwDo);

        document.prepend(kwDo, oneSpaceWrapperCond);
        document.append(kwDo, getLineFormatting(singleLine, oneSpaceWrapper));
        document.prepend(regionFinder.keyword(KEYWORD_OD),
                getLineFormatting(singleLine, oneSpaceWrapper));

        format(expression.getCondition(), document, oneSpaceWrapperCond, singleLine);
        indent(expression.getBody(), document, singleLine);
        format(expression.getBody(), document, null, singleLine);
    }

    private void format(
            DataMethodCallExpression expression, IFormattableDocument document,
            ISemanticRegionsFinder regionFinder, Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
            boolean singleLine) {
        methodCallBracketSpacing(regionFinder, document);
        commaSpacing(regionFinder, oneSpaceWrapper, document);
        for (Expression expressions : expression.getArguments()) {
            format(expressions, document, null, true);
        }
        if (expression.isOnSuperClass()) {
            document.append(regionFinder.keyword("^"), oneSpaceWrapper); //$NON-NLS-1$
            document.append(expression.getTarget(), NO_SPACE);
        } else {
            document.append(expression.getTarget(), oneSpaceWrapper);
        }
        format(expression.getTarget(), document, null, singleLine);
    }

    private void format(
            BinaryOperatorExpression expression, IFormattableDocument document,
            Procedure1<IHiddenRegionFormatter> oneSpaceWrapper, boolean singleLine) {
        Expression left = expression.getLeftOperand();
        Expression right = expression.getRightOperand();

        document.append(left, oneSpaceWrapper);
        document.prepend(right, oneSpaceWrapper);
        format(left, document, oneSpaceWrapper, singleLine);
        format(right, document, oneSpaceWrapper, singleLine);
    }

    private void format(
            UnaryOperatorExpression expression, IFormattableDocument document,
            Procedure1<IHiddenRegionFormatter> oneSpaceWrapper, boolean singleLine) {
        Expression oper = expression.getOperand();
        document.prepend(oper, NO_SPACE);
        format(oper, document, oneSpaceWrapper, singleLine);
    }

    private void format(
            NewExpression expression, IFormattableDocument document,
            Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
            ISemanticRegionsFinder regionFinder) {
        ISemanticRegion kwNew = regionFinder.keyword("new"); //$NON-NLS-1$
        if (kwNew.immediatelyFollowing().keyword(PAR_OPEN) != null) {
            document.append(kwNew, NO_SPACE);
        } else {
            document.append(kwNew, oneSpaceWrapper);
        }
    }

    private void format(
            Statement statement, IFormattableDocument document,
            Procedure1<IHiddenRegionFormatter> wrapper, boolean singleLine) {
        ISemanticRegionsFinder regionFinder = textRegionExtensions.regionFor(statement);
        if (statement instanceof StatementSequence) {
            formatStatementSequence((StatementSequence) statement, document, wrapper, singleLine,
                    regionFinder);
        } else {
            Procedure1<IHiddenRegionFormatter> oneSpaceWrapper = (wrapper == null)
                ? createOneSpaceWrapDouble(statement) : wrapper;

            if (statement instanceof AbortStatement) {
                format((AbortStatement) statement, document, regionFinder, oneSpaceWrapper,
                        singleLine);
            } else if (statement instanceof DelayStatement) {
                ISemanticRegion kwDelay = regionFinder.keyword("delay"); //$NON-NLS-1$
                document.append(kwDelay, oneSpaceWrapper);
                format(((DelayStatement) statement).getExpression(), document, oneSpaceWrapper,
                        singleLine);
            } else if (statement instanceof GuardedStatement) {
                squareBracketSpacing(regionFinder, oneSpaceWrapper, document);
                format(((GuardedStatement) statement).getGuard(), document, oneSpaceWrapper,
                        singleLine);
                format(((GuardedStatement) statement).getStatement(), document, oneSpaceWrapper,
                        singleLine);
            } else if (statement instanceof InterruptStatement) {
                format((InterruptStatement) statement, document, regionFinder, oneSpaceWrapper,
                        singleLine);
            } else if (statement instanceof ParStatement) {
                format((ParStatement) statement, document, regionFinder, oneSpaceWrapper,
                        singleLine);
            } else if (statement instanceof ProcessMethodCall) {
                format((ProcessMethodCall) statement, document, regionFinder, oneSpaceWrapper,
                        singleLine);
            } else if (statement instanceof SelStatement) {
                format((SelStatement) statement, document, regionFinder, oneSpaceWrapper,
                        singleLine);
            } else if (statement instanceof SendStatement) {
                format((SendStatement) statement, document, regionFinder, oneSpaceWrapper,
                        singleLine);
            } else if (statement instanceof ReceiveStatement) {
                format((ReceiveStatement) statement, document, regionFinder, oneSpaceWrapper,
                        singleLine);
            } else if (statement instanceof IfStatement) {
                format((IfStatement) statement, document, regionFinder, oneSpaceWrapper,
                        singleLine);
            } else if (statement instanceof WhileStatement) {
                format((WhileStatement) statement, document, regionFinder, oneSpaceWrapper,
                        singleLine);
            } else if (statement instanceof SwitchStatement) {
                format((SwitchStatement) statement, document, regionFinder, oneSpaceWrapper,
                        singleLine);
            } else if (statement instanceof ExpressionStatement) {
                formatCurlyBracketsMultiOrSingle(((ExpressionStatement) statement).getExpression(),
                        document, regionFinder, singleLine, oneSpaceWrapper, false);
            }
        }
    }

    private void formatStatementSequence(
            StatementSequence statement, IFormattableDocument document,
            Procedure1<IHiddenRegionFormatter> wrapper, boolean singleLine,
            ISemanticRegionsFinder regionFinder) {
        EList<Statement> statements = statement.getStatements();
        if (statement instanceof StatementSequenceRoundBracket) {
            formatRoundBracketSequence(statements, document, regionFinder, singleLine);
        }

        semiColonSpacing(regionFinder, document, singleLine);
        for (Statement stat : statements) {
            format(stat, document, wrapper, singleLine);
        }
    }

    private void format(
            WhileStatement statement, IFormattableDocument document,
            ISemanticRegionsFinder regionFinder, Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
            boolean singleLine) {
        ISemanticRegion kwDo = regionFinder.keyword(KEYWORD_DO);
        Procedure1<IHiddenRegionFormatter> oneSpaceWrapperCondition = getOneSpaceWrapIncluding(
                kwDo);

        document.prepend(kwDo, oneSpaceWrapperCondition);
        document.append(kwDo, getLineFormatting(singleLine, oneSpaceWrapper));
        document.prepend(regionFinder.keyword(KEYWORD_OD),
                getLineFormatting(singleLine, oneSpaceWrapper));
        format(statement.getCondition(), document, oneSpaceWrapperCondition, singleLine);
        indent(statement.getBody(), document, singleLine);
        format(statement.getBody(), document, null, singleLine);
    }

    private void format(
            IfStatement statement, IFormattableDocument document,
            ISemanticRegionsFinder regionFinder, Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
            boolean singleLine) {
        ISemanticRegion kwThen = regionFinder.keyword(KEYWORD_THEN);
        Procedure1<IHiddenRegionFormatter> oneSpaceWrapperCondition = getOneSpaceWrapIncluding(
                kwThen);

        Procedure1<IHiddenRegionFormatter> lineFormat = getLineFormatting(singleLine,
                oneSpaceWrapper);
        document.prepend(kwThen, oneSpaceWrapperCondition);
        document.append(kwThen, lineFormat);
        ISemanticRegion kwElse = regionFinder.keyword(KEYWORD_ELSE);
        ISemanticRegion kwFi = regionFinder.keyword(KEYWORD_FI);
        if (kwElse != null) {
            document.prepend(kwElse, lineFormat);
            ISemanticRegion nextSemanticRegion = kwElse.getNextSemanticRegion();
            if (nextSemanticRegion != null && nextSemanticRegion.getText().equals(KEYWORD_IF)) {
                // else if
                document.append(kwElse, oneSpaceWrapper);
                document.prepend(kwFi, oneSpaceWrapper);
            } else {
                document.append(kwElse, lineFormat);
                document.prepend(kwFi, lineFormat);
                indent(statement.getElseClause(), document, singleLine);
            }
        } else {
            document.prepend(kwFi, lineFormat);
        }

        indent(statement.getThenClause(), document, singleLine);
        format(statement.getCondition(), document, oneSpaceWrapperCondition, singleLine);
        format(statement.getElseClause(), document, null, singleLine);
        format(statement.getThenClause(), document, null, singleLine);
    }

    private void format(
            SwitchStatement statement, IFormattableDocument document,
            ISemanticRegionsFinder regionFinder, Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
            boolean singleLine) {
        document.append(regionFinder.keyword(KEYWORD_SWITCH), oneSpaceWrapper);
        List<Pair<ISemanticRegion, ISemanticRegion>> keywordPairs = regionFinder
                .keywordPairs(KEYWORD_DO, KEYWORD_OD);

        Procedure1<IHiddenRegionFormatter> oneSpaceWrapCondition = getOneSpaceWrapIncluding(
                keywordPairs.isEmpty() ? null : keywordPairs.get(0).getKey());

        Procedure1<IHiddenRegionFormatter> lineFormat = getLineFormatting(singleLine,
                oneSpaceWrapper);
        for (Pair<ISemanticRegion, ISemanticRegion> pair : keywordPairs) {
            document.prepend(pair.getKey(), oneSpaceWrapCondition);
            document.prepend(pair.getValue(), lineFormat);
            document.interior(pair.getKey(), pair.getValue(), INDENT);
        }
        format(statement.getExpression(), document, oneSpaceWrapCondition, singleLine);

        for (SwitchStatementCase switchExpressionCase : statement.getCases()) {
            ISemanticRegionsFinder cRegionFinder = textRegionExtensions
                    .regionFor(switchExpressionCase);
            ISemanticRegion kwCase = cRegionFinder.keyword("case"); //$NON-NLS-1$
            document.prepend(kwCase, lineFormat);

            ISemanticRegion kwThen = cRegionFinder.keyword(KEYWORD_THEN);
            Procedure1<IHiddenRegionFormatter> oneSpaceCaseCondition = getOneSpaceWrapIncluding(
                    kwThen);

            document.append(kwCase, oneSpaceCaseCondition);
            document.prepend(kwThen, oneSpaceCaseCondition);
            document.prepend(switchExpressionCase.getBody(), lineFormat);

            indent(switchExpressionCase.getBody(), document, singleLine);
            format(switchExpressionCase.getValue(), document, oneSpaceCaseCondition, singleLine);
            format(switchExpressionCase.getBody(), document, null, singleLine);
        }

        ISemanticRegion kwDefault = regionFinder.keyword("default"); //$NON-NLS-1$
        document.prepend(kwDefault, lineFormat);
        document.append(kwDefault, lineFormat);
        indent(statement.getDefaultBody(), document, singleLine);
        format(statement.getDefaultBody(), document, null, singleLine);
    }

    private void format(
            ParStatement statement, IFormattableDocument document,
            ISemanticRegionsFinder regionFinder, Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
            boolean singleLine) {
        Procedure1<IHiddenRegionFormatter> lineFormat = getLineFormatting(singleLine,
                oneSpaceWrapper);
        document.append(regionFinder.keyword("par"), lineFormat); //$NON-NLS-1$
        for (ISemanticRegion iSemanticRegion : regionFinder.keywords("and")) { //$NON-NLS-1$
            document.prepend(iSemanticRegion, lineFormat);
            document.append(iSemanticRegion, lineFormat);
        }

        document.prepend(regionFinder.keyword("rap"), lineFormat); //$NON-NLS-1$
        for (Statement stat : statement.getClauses()) {
            indent(stat, document, singleLine);
            format(stat, document, null, singleLine);
        }
    }

    private void format(
            SelStatement statement, IFormattableDocument document,
            ISemanticRegionsFinder regionFinder, Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
            boolean singleLine) {
        document.append(regionFinder.keyword("sel"), NEW_LINE); //$NON-NLS-1$
        Procedure1<IHiddenRegionFormatter> lineFormat = getLineFormatting(singleLine,
                oneSpaceWrapper);
        for (ISemanticRegion iSemanticRegion : regionFinder.keywords("or")) { //$NON-NLS-1$
            document.prepend(iSemanticRegion, lineFormat);
            document.append(iSemanticRegion, lineFormat);
        }
        document.prepend(regionFinder.keyword("les"), lineFormat); //$NON-NLS-1$

        for (Statement stat : statement.getClauses()) {
            indent(stat, document, singleLine);
            format(stat, document, null, singleLine);
        }
    }

    private void format(
            AbortStatement statement, IFormattableDocument document,
            ISemanticRegionsFinder regionFinder, Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
            boolean singleLine) {
        document.append(regionFinder.keyword("abort"), //$NON-NLS-1$
                getLineFormatting(singleLine, oneSpaceWrapper));
        formatStatementWith(statement.getNormalClause(), statement.getAbortingClause(), document,
                regionFinder, oneSpaceWrapper, singleLine);
    }

    private void format(
            InterruptStatement statement, IFormattableDocument document,
            ISemanticRegionsFinder regionFinder, Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
            boolean singleLine) {
        document.append(regionFinder.keyword("interrupt"), //$NON-NLS-1$
                getLineFormatting(singleLine, oneSpaceWrapper));
        formatStatementWith(statement.getNormalClause(), statement.getInterruptingClause(),
                document, regionFinder, oneSpaceWrapper, singleLine);
    }

    private void formatStatementWith(
            Statement normal, Statement with, IFormattableDocument document,
            ISemanticRegionsFinder regionFinder, Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
            boolean singleLine) {
        ISemanticRegion kwWith = regionFinder.keyword("with"); //$NON-NLS-1$
        document.prepend(kwWith, getLineFormatting(singleLine, oneSpaceWrapper));
        document.append(kwWith, oneSpaceWrapper);

        indent(normal, document, singleLine);
        format(normal, document, null, singleLine);
        format(with, document, null, singleLine);
    }

    private void format(
            SendStatement statement, IFormattableDocument document,
            ISemanticRegionsFinder regionFinder, Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
            boolean singleLine) {
        bracketPairSpacing(regionFinder, document);
        commaSpacing(regionFinder, oneSpaceWrapper, document);
        exclamationSpacing(regionFinder, document);
        formatCurlyBracketsMultiOrSingle(statement.getPostCommunicationExpression(), document,
                regionFinder, singleLine, oneSpaceWrapper, true);
        for (Expression expression : statement.getArguments()) {
            format(expression, document, null, true);
        }
    }

    private void format(
            ReceiveStatement statement, IFormattableDocument document,
            ISemanticRegionsFinder regionFinder, Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
            boolean singleLine) {
        bracketPairSpacing(regionFinder, document);
        commaSpacing(regionFinder, oneSpaceWrapper, document);
        questionMarkSpacing(regionFinder, document);
        formatCurlyBracketsMultiOrSingle(statement.getPostCommunicationExpression(), document,
                regionFinder, singleLine, oneSpaceWrapper, true);
        format(statement.getReceptionCondition(), document, null, true);
    }

    private void format(
            ProcessMethodCall processMethodCall, IFormattableDocument document,
            ISemanticRegionsFinder regionFinder, Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
            boolean singleLine) {
        methodCallBracketSpacing(regionFinder, document);
        for (Expression expression : processMethodCall.getInputArguments()) {
            format(expression, document, null, true);
        }
    }

    private void format(EList<Annotation> annotations, IFormattableDocument document) {
        for (Annotation annotation : annotations) {
            document.append(annotation, NEW_LINE);
            ISemanticRegionsFinder regionFinder = textRegionExtensions.regionFor(annotation);

            ISemanticRegion annotationName = regionFinder
                    .assignment(pooslGrammar.getAnnotationAccess().getNameAssignment_1());
            document.surround(annotationName, NO_SPACE);

            formatRoundBracketSequence(annotation.getArguments(), document, regionFinder, true);
            commaSpacing(regionFinder, ONE_SPACE, document);
        }
    }

    private void formatRoundBracketSequence(
            EList<? extends EObject> list, IFormattableDocument document,
            ISemanticRegionsFinder regionFinder, boolean singleLine) {
        List<Pair<ISemanticRegion, ISemanticRegion>> brackets = regionFinder.keywordPairs(PAR_OPEN,
                PAR_CLOSE);
        formatBracketsSingleOrMulti(singleLine || list.size() < 2, document, brackets);
    }

    private void formatCurlyBracketsMultiOrSingle(
            Expression expression, IFormattableDocument document,
            ISemanticRegionsFinder regionFinder, boolean singleLine,
            Procedure1<IHiddenRegionFormatter> oneSpaceWrapper, boolean prependSpace) {
        if (expression != null) {
            List<Pair<ISemanticRegion, ISemanticRegion>> brackets = regionFinder.keywordPairs("{", //$NON-NLS-1$
                    "}"); //$NON-NLS-1$
            if (prependSpace && !brackets.isEmpty()) {
                document.prepend(brackets.get(0).getKey(), oneSpaceWrapper);
            }
            boolean single = singleLine || !isMultiLine(expression);
            formatBracketsSingleOrMulti(single, document, brackets);
            format(expression, document, null, single);
        }
    }

    private boolean isMultiLine(Expression expression) {
        if (expression instanceof ExpressionSequence) {
            EList<Expression> expressions = ((ExpressionSequence) expression).getExpressions();
            if (expressions.size() > 1) {
                for (Expression expr : expressions) {
                    // all assignments allow 4; 5; 6; on a singleline
                    if (!allowOnSingleLine(expr)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean allowOnSingleLine(Expression expression) {
        // CHECKSTYLE.OFF: BooleanExpressionComplexity
        // @formatter:off
        return expression instanceof BooleanConstant
                || expression instanceof EnvironmentConstant
                || expression instanceof FloatConstant
                || expression instanceof IntegerConstant
                || expression instanceof NilConstant
                || expression instanceof RealConstant
                || expression instanceof StringConstant;
        // @formatter:on
        // CHECKSTYLE.ON
    }

    private void formatBracketsSingleOrMulti(
            boolean singleLine, IFormattableDocument document,
            List<Pair<ISemanticRegion, ISemanticRegion>> brackets) {
        if (!brackets.isEmpty()) {
            Pair<ISemanticRegion, ISemanticRegion> pair = brackets.get(0);
            if (singleLine) {
                document.append(pair.getKey(), NO_SPACE);
                document.prepend(pair.getValue(), NO_SPACE);
            } else {
                document.append(pair.getKey(), NEW_LINE);
                document.prepend(pair.getValue(), NEW_LINE);
                document.interior(pair, INDENT);
            }
        }
    }

    // --- Text region helper functions -------

    private void topElementEmptyLine(EObject object, IFormattableDocument document) {
        ISemanticRegion previousSemantic = textRegionExtensions.regionForEObject(object)
                .getPreviousSemanticRegion();
        if (previousSemantic != null) {
            EObject element = previousSemantic.getSemanticElement();
            if (element instanceof ProcessClass || element instanceof ClusterClass
                    || element instanceof DataClass) {
                // previous object is a keyword, add extra emptyLine
                document.prepend(object, EMPTY_LINES);
            } else {
                document.prepend(object, EMPTY_LINE);
            }
        } else {
            // top of the file
            document.prepend(object, NO_SPACE);
        }
    }

    private void indentList(EList<? extends EObject> objects, IFormattableDocument document) {
        if (!objects.isEmpty()) {
            EObject first = objects.get(0);
            EObject last = objects.get(objects.size() - 1);
            indentFromTo(first, last, document);
        }
    }

    /**
     * Indenting multiple lists containing EObjects that syntactically can be in
     * between each other and behave as one
     * list. Finds which EObject in the lists is actually the first or last
     * based on its offset in the document and
     * indents based on that info. It doesn't have to iterate through the lists
     * it only needs the first and last of the
     * list as they are also the first and last in the document. (Example: send
     * and receive messages)
     * 
     * @param document
     * @param lists
     * @return can return null if the lists
     */
    private EObject indentLists(
            IFormattableDocument document, List<EList<? extends EObject>> lists) {
        EObject first = null;
        EObject last = null;
        int firstOffset = -1;
        int lastOffset = -1;

        for (EList<? extends EObject> list : lists) {
            if (!list.isEmpty()) {
                EObject listFirst = list.get(0);
                EObject listLast = list.get(list.size() - 1);
                int listFirstOffset = textRegionExtensions.regionForEObject(listFirst).getOffset();
                int listLastOffset = textRegionExtensions.regionForEObject(listLast).getOffset();

                if (first == null) {
                    first = listFirst;
                    last = listLast;
                    firstOffset = listFirstOffset;
                    lastOffset = listLastOffset;
                } else {
                    if (listFirstOffset < firstOffset) {
                        first = listFirst;
                        firstOffset = listFirstOffset;
                    }
                    if (listLastOffset > lastOffset) {
                        last = listLast;
                        lastOffset = listLastOffset;
                    }
                }
            }
        }
        if (first != null) {
            indentFromTo(first, last, document);
        }
        return first;
    }

    private void indentFromTo(EObject from, EObject to, IFormattableDocument document) {
        IHiddenRegion firstHidden = textRegionExtensions.previousHiddenRegion(from);
        IHiddenRegion lastHidden = textRegionExtensions.nextHiddenRegion(to);
        document.set(firstHidden, lastHidden, INDENT);
    }

    private void indent(EObject obj, IFormattableDocument document, boolean singleLine) {
        if (obj != null) {
            if (singleLine) {
                document.prepend(obj, ONE_SPACE_NO_WRAP);
            } else {
                indentFromTo(obj, obj, document);
            }
        }
    }

    private void keywordsOnNewLine(EObject obj, IFormattableDocument document, String... keywords) {
        ISemanticRegionsFinder regionFinder = textRegionExtensions.regionFor(obj);

        List<ISemanticRegion> kwRegions = regionFinder.keywords(keywords);
        for (int i = 0; i < kwRegions.size(); i++) {
            ISemanticRegion kwRegion = kwRegions.get(i);

            ISemanticRegion previous = kwRegion.getPreviousSemanticRegion();
            if (kwRegions.contains(previous)) {
                document.prepend(kwRegion, EMPTY_LINE);

            } else {
                document.prepend(kwRegion, NEW_LINE);
            }
        }
    }

    private void formatPipeDeclarations(
            EList<Declaration> declarations, ISemanticRegionsFinder mRegionFinder,
            Procedure1<IHiddenRegionFormatter> oneSpaceWrap, IFormattableDocument document) {
        List<ISemanticRegion> pipes = mRegionFinder.keywords("|"); //$NON-NLS-1$
        if (pipes.size() == 2) {
            if (formatMutlilineDeclarations(pipes.get(0), pipes.get(1), declarations, oneSpaceWrap,
                    document)) {
                document.prepend(pipes.get(0), oneSpaceWrap);
                document.prepend(pipes.get(1), ONE_SPACE_NO_WRAP);
            } else {
                document.prepend(pipes.get(0), oneSpaceWrap);
                document.append(pipes.get(0), ONE_SPACE_NO_WRAP);
                document.prepend(pipes.get(1), ONE_SPACE_NO_WRAP);
            }
        }
    }

    private void formatBracketDeclarations(
            EList<Declaration> declarations, Pair<ISemanticRegion, ISemanticRegion> pair,
            Procedure1<IHiddenRegionFormatter> oneSpaceWrap, IFormattableDocument document) {
        if (formatMutlilineDeclarations(pair.getKey(), pair.getValue(), declarations, oneSpaceWrap,
                document)) {
            document.prepend(pair.getValue(), NO_SPACE);
        } else {
            document.append(pair.getKey(), NO_SPACE);
            document.prepend(pair.getValue(), NO_SPACE);
        }
    }

    /**
     * return true if multiline formatting is applied.
     * 
     * @param fromRegion
     * @param toRegion
     * @param declarations
     * @param oneSpaceWrap
     * @param document
     * @return true if multiline formatting is applied
     */
    private boolean formatMutlilineDeclarations(
            ISemanticRegion fromRegion, ISemanticRegion toRegion, EList<Declaration> declarations,
            Procedure1<IHiddenRegionFormatter> oneSpaceWrap, IFormattableDocument document) {
        if (!declarations.isEmpty()) {
            IEObjectRegion firstLocal = textRegionExtensions.regionForEObject(declarations.get(0));
            IHiddenRegion s = firstLocal.getPreviousHiddenRegion();

            if (s.isMultiline() || s.containsComment()) {
                // format multiline
                if (fromRegion != null && toRegion != null) {
                    document.interior(fromRegion, toRegion, DOUBLE_INDENT);
                }
                for (Declaration declaration : declarations) {
                    formatNewLine(declaration, oneSpaceWrap, document);
                }
                return true;
            } else {
                // format singleLine
                for (Declaration declaration : declarations) {
                    format(declaration, oneSpaceWrap, document);
                }
            }
        }
        return false;
    }

    private Procedure1<IHiddenRegionFormatter> getLineFormatting(
            boolean singleLine, Procedure1<IHiddenRegionFormatter> oneSpaceWrap) {
        return singleLine ? oneSpaceWrap : NEW_LINE;
    }

    // --- Static helper functions -------

    private static void questionMarkSpacing(
            ISemanticRegionsFinder regionFinder, IFormattableDocument document) {
        for (ISemanticRegion iSemanticRegion : regionFinder.keywords("?")) { //$NON-NLS-1$
            document.surround(iSemanticRegion, NO_SPACE);
        }
    }

    private static void bracketPairSpacing(
            ISemanticRegionsFinder regionFinder, IFormattableDocument document) {
        for (Pair<ISemanticRegion, ISemanticRegion> pair : regionFinder.keywordPairs(PAR_OPEN,
                PAR_CLOSE)) {
            document.prepend(pair.getKey(), NO_SPACE);
            document.append(pair.getKey(), NO_SPACE);
            document.prepend(pair.getValue(), NO_SPACE);
        }
    }

    private static void curlyBracketPairSpacing(
            ISemanticRegionsFinder regionFinder, IFormattableDocument document) {
        for (Pair<ISemanticRegion, ISemanticRegion> pair : regionFinder.keywordPairs("{", "}")) { //$NON-NLS-1$ //$NON-NLS-2$
            document.prepend(pair.getKey(), NO_SPACE);
            document.append(pair.getKey(), ONE_SPACE_NO_WRAP);
            document.prepend(pair.getValue(), ONE_SPACE_NO_WRAP);
            document.append(pair.getValue(), NO_SPACE);
        }
    }

    private static void squareBracketSpacing(
            ISemanticRegionsFinder regionFinder, Procedure1<IHiddenRegionFormatter> oneSpaceWrap,
            IFormattableDocument document) {
        for (Pair<ISemanticRegion, ISemanticRegion> pair : regionFinder.keywordPairs("[", "]")) { //$NON-NLS-1$ //$NON-NLS-2$
            document.append(pair.getValue(), oneSpaceWrap);
        }
    }

    private static void methodCallBracketSpacing(
            ISemanticRegionsFinder regionFinder, IFormattableDocument document) {
        for (Pair<ISemanticRegion, ISemanticRegion> pair : regionFinder.keywordPairs(PAR_OPEN,
                PAR_CLOSE)) {
            document.surround(pair.getKey(), NO_SPACE);
            document.prepend(pair.getValue(), NO_SPACE);
        }
    }

    private static void commaSpacing(
            ISemanticRegionsFinder regionFinder,
            Procedure1<? super IHiddenRegionFormatter> oneSpaceWrapper,
            IFormattableDocument document) {
        for (ISemanticRegion iSemanticRegion : regionFinder.keywords(",")) { //$NON-NLS-1$
            document.prepend(iSemanticRegion, NO_SPACE);
            document.append(iSemanticRegion, oneSpaceWrapper);
        }
    }

    private static void commaSpacing(
            ISemanticRegionsFinder regionFinder, Keyword comma,
            Procedure1<? super IHiddenRegionFormatter> oneSpaceWrapper,
            IFormattableDocument document) {
        for (ISemanticRegion iSemanticRegion : regionFinder.keywords(comma)) {
            document.prepend(iSemanticRegion, NO_SPACE);
            document.append(iSemanticRegion, oneSpaceWrapper);
        }
    }

    private static void commaSpacing(
            ISemanticRegionsFinder regionFinder, Keyword comma, IFormattableDocument document) {
        for (ISemanticRegion iSemanticRegion : regionFinder.keywords(comma)) {
            document.prepend(iSemanticRegion, NO_SPACE);
        }
    }

    private static void semiColonSpacing(
            ISemanticRegionsFinder regionFinder, IFormattableDocument document,
            boolean singleLine) {
        for (ISemanticRegion iSemanticRegion : regionFinder.keywords(";")) { //$NON-NLS-1$
            document.prepend(iSemanticRegion, NO_SPACE);
            if (singleLine) {
                document.append(iSemanticRegion, NO_SPACE);
            } else {
                document.append(iSemanticRegion, NEW_LINE);
            }
        }
    }

    private static void periodSpacing(
            ISemanticRegionsFinder regionFinder, IFormattableDocument document) {
        for (ISemanticRegion iSemanticRegion : regionFinder.keywords(".")) { //$NON-NLS-1$
            document.surround(iSemanticRegion, NO_SPACE);
        }
    }

    private static void exclamationSpacing(
            ISemanticRegionsFinder regionFinder, IFormattableDocument document) {
        for (ISemanticRegion iSemanticRegion : regionFinder.keywords("!")) { //$NON-NLS-1$
            document.surround(iSemanticRegion, NO_SPACE);
        }
    }

    private static void colonSpacing(
            ISemanticRegionsFinder regionFinder, IFormattableDocument document) {
        for (ISemanticRegion iSemanticRegion : regionFinder.keywords(":")) { //$NON-NLS-1$
            document.surround(iSemanticRegion, ONE_SPACE_NO_WRAP);
        }
    }

    private Procedure1<IHiddenRegionFormatter> createOneSpaceWrap(EObject semanticElement) {
        return (semanticElement == null)
            ? ONE_SPACE
            : getOneSpaceWrapper(textRegionExtensions.nextHiddenRegion(semanticElement), INDENT);
    }

    private Procedure1<IHiddenRegionFormatter> createOneSpaceUpTo(EObject semanticElement) {
        return (semanticElement == null)
            ? ONE_SPACE : getOneSpaceWrapper(
                    textRegionExtensions.previousHiddenRegion(semanticElement), DOUBLE_INDENT);
    }

    private Procedure1<IHiddenRegionFormatter> createOneSpaceWrapDouble(EObject semanticElement) {
        return (semanticElement == null)
            ? ONE_SPACE : getOneSpaceWrapper(textRegionExtensions.nextHiddenRegion(semanticElement),
                    DOUBLE_INDENT);
    }

    private Procedure1<IHiddenRegionFormatter> getOneSpaceWrapIncluding(ISemanticRegion region) {
        return getOneSpaceWrapIncluding(region, DOUBLE_INDENT);
    }

    private Procedure1<IHiddenRegionFormatter> getOneSpaceWrapIncluding(
            ISemanticRegion region, Procedure1<IHiddenRegionFormatter> proc) {
        if (region != null) {
            ISemanticRegion nextSem = region.getNextSemanticRegion();
            if (nextSem != null) {
                return getOneSpaceWrapper(nextSem.getPreviousHiddenRegion(), proc);
            }
        }
        return ONE_SPACE;
    }

    private Procedure1<IHiddenRegionFormatter> createOneSpaceWrapUpTo(
            ISemanticRegion region, Procedure1<IHiddenRegionFormatter> proc) {
        if (region != null) {
            return getOneSpaceWrapper(region.getPreviousHiddenRegion(), proc);
        }
        return ONE_SPACE;
    }

    private Procedure1<IHiddenRegionFormatter> getOneSpaceWrapper(
            final IHiddenRegion last, Procedure1<IHiddenRegionFormatter> proc) {
        final PooslAutoWrapper wrapper = new PooslAutoWrapper(last, proc);
        return new Procedure1<IHiddenRegionFormatter>() {
            @Override
            public void apply(final IHiddenRegionFormatter it) {
                it.oneSpace();
                it.setOnAutowrap(wrapper);
            }
        };
    }
}
