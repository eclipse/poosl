package nl.esi.poosl.xtext.formatting2;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
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

import nl.esi.poosl.AbortStatement;
import nl.esi.poosl.Annotation;
import nl.esi.poosl.AssignmentExpression;
import nl.esi.poosl.BinaryOperatorExpression;
import nl.esi.poosl.BooleanConstant;
import nl.esi.poosl.Channel;
import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.DataMethodBinaryOperator;
import nl.esi.poosl.DataMethodCallExpression;
import nl.esi.poosl.DataMethodNamed;
import nl.esi.poosl.DataMethodUnaryOperator;
import nl.esi.poosl.Declaration;
import nl.esi.poosl.DelayStatement;
import nl.esi.poosl.EnvironmentConstant;
import nl.esi.poosl.Expression;
import nl.esi.poosl.ExpressionSequence;
import nl.esi.poosl.ExpressionSequenceRoundBracket;
import nl.esi.poosl.ExpressionStatement;
import nl.esi.poosl.FloatConstant;
import nl.esi.poosl.GuardedStatement;
import nl.esi.poosl.IfExpression;
import nl.esi.poosl.IfStatement;
import nl.esi.poosl.Import;
import nl.esi.poosl.Instance;
import nl.esi.poosl.InstanceParameter;
import nl.esi.poosl.InstancePort;
import nl.esi.poosl.IntegerConstant;
import nl.esi.poosl.InterruptStatement;
import nl.esi.poosl.MessageSignature;
import nl.esi.poosl.NewExpression;
import nl.esi.poosl.NilConstant;
import nl.esi.poosl.ParStatement;
import nl.esi.poosl.Poosl;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.Port;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.ProcessMethodCall;
import nl.esi.poosl.RealConstant;
import nl.esi.poosl.ReceiveStatement;
import nl.esi.poosl.ReturnExpression;
import nl.esi.poosl.SelStatement;
import nl.esi.poosl.SendStatement;
import nl.esi.poosl.Statement;
import nl.esi.poosl.StatementSequence;
import nl.esi.poosl.StatementSequenceRoundBracket;
import nl.esi.poosl.StringConstant;
import nl.esi.poosl.SwitchExpression;
import nl.esi.poosl.SwitchExpressionCase;
import nl.esi.poosl.SwitchStatement;
import nl.esi.poosl.SwitchStatementCase;
import nl.esi.poosl.UnaryOperatorExpression;
import nl.esi.poosl.WhileExpression;
import nl.esi.poosl.WhileStatement;
import nl.esi.poosl.xtext.services.PooslGrammarAccess;
import nl.esi.poosl.xtext.services.PooslGrammarAccess.ClusterClassElements;
import nl.esi.poosl.xtext.services.PooslGrammarAccess.ProcessClassElements;

public class PooslFormatter extends AbstractFormatter2 {

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
			if (ruleName.startsWith("ML"))
				return new PooslMultilineCommentReplacer(comment, '*');
		}
		return super.createCommentReplacer(comment);
	}

	@Override
	public ITextReplacer createHiddenRegionReplacer(IHiddenRegion region, IHiddenRegionFormatting formatting) {
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
			Logger.getGlobal().fine("Format not specific handled : " + obj.getClass().getName());
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
		ISemanticRegion previousSemantic = textRegionExtensions.regionForEObject(imports).getPreviousSemanticRegion();
		if (previousSemantic != null) {
			document.prepend(imports, newLine);
		}
		document.prepend(imports, noSpace);
	}

	private void format(ClusterClass cluster, IFormattableDocument document) {
		topElementEmptyLine(cluster, document);
		ISemanticRegionsFinder regionFinder = textRegionExtensions.regionFor(cluster);
		format(cluster.getAnnotations(), document);

		ClusterClassElements grammar = pooslGrammar.getClusterClassAccess();
		Procedure1<IHiddenRegionFormatter> oneSpaceWrap = createOneSpaceWrapUpTo(regionFinder.keyword("ports"), indent);

		document.append(regionFinder.keyword("cluster"), oneSpaceWrap);
		document.append(regionFinder.keyword("class"), oneSpaceWrap);
		document.append(regionFinder.feature(Literals.INSTANTIABLE_CLASS__NAME), noSpace);
		keywordsOnNewLine(cluster, document, "ports", "instances", "channels");
		commaSpacing(regionFinder, oneSpaceWrap, document);

		// difference between comma's in cluster class
		commaSpacing(regionFinder, grammar.getCommaKeyword_4_1_1_0(), oneSpaceWrap, document); // process parameters
		commaSpacing(regionFinder, grammar.getCommaKeyword_6_1_0(), document); // ports
		commaSpacing(regionFinder, grammar.getCommaKeyword_6_2(), document);

		List<Pair<ISemanticRegion, ISemanticRegion>> brackets = regionFinder.keywordPairs("(", ")");
		if (!brackets.isEmpty()) {
			formatBracketDeclarations(cluster.getParameters(), brackets.get(0), oneSpaceWrap, document);
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
		Procedure1<IHiddenRegionFormatter> oneSpaceWrap = createOneSpaceWrapUpTo(regionFinder.keyword("variables"),
				indent);

		document.append(regionFinder.keyword("data"), oneSpaceWrap);
		document.append(regionFinder.keyword("class"), oneSpaceWrap);
		formatExtend(regionFinder, oneSpaceWrap, document);

		commaSpacing(regionFinder, oneSpace, document);
		keywordsOnNewLine(data, document, "variables", "methods");

		indentList(data.getInstanceVariables(), document);
		for (Declaration instanceVariables : data.getInstanceVariables()) {
			document.prepend(instanceVariables, newLine);
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

		for (DataMethodUnaryOperator dataMethodsUnaryOperator : data.getDataMethodsUnaryOperator()) {
			format(dataMethodsUnaryOperator, document, first == dataMethodsUnaryOperator);
		}

		for (DataMethodBinaryOperator dataMethodsBinaryOperator : data.getDataMethodsBinaryOperator()) {
			format(dataMethodsBinaryOperator, document, first == dataMethodsBinaryOperator);
		}
	}

	private void format(DataMethod dMethod, IFormattableDocument document, boolean firstMethod) {
		document.prepend(dMethod, (firstMethod) ? newLine : emptyLine);
		ISemanticRegionsFinder mRegionFinder = textRegionExtensions.regionFor(dMethod);
		format(dMethod.getAnnotations(), document);

		Procedure1<IHiddenRegionFormatter> oneSpaceWrap = (dMethod.getBody() == null)
				? createOneSpaceWrapDouble(dMethod)
				: createOneSpaceUpTo(dMethod.getBody());

		bracketPairSpacing(mRegionFinder, document);
		commaSpacing(mRegionFinder, oneSpaceWrap, document);
		colonSpacing(mRegionFinder, document);

		List<Pair<ISemanticRegion, ISemanticRegion>> keywordPairs = mRegionFinder.keywordPairs("(", ")");
		if (!keywordPairs.isEmpty()) {
			formatBracketDeclarations(dMethod.getParameters(), keywordPairs.get(0), oneSpaceWrap, document);
		}
		formatPipeDeclarations(dMethod.getLocalVariables(), mRegionFinder, oneSpaceWrap, document);

		document.prepend(dMethod.getBody(), newLine);
		indent(dMethod.getBody(), document, false);
		format(dMethod.getBody(), document, null, false);
	}

	private void format(ProcessClass process, IFormattableDocument document) {
		topElementEmptyLine(process, document);
		ISemanticRegionsFinder regionFinder = textRegionExtensions.regionFor(process);
		format(process.getAnnotations(), document);

		ProcessClassElements grammar = pooslGrammar.getProcessClassAccess();
		Procedure1<IHiddenRegionFormatter> oneSpaceWrap = createOneSpaceWrapUpTo(regionFinder.keyword("ports"), indent);

		document.append(regionFinder.keyword("process"), oneSpaceWrap);
		document.append(regionFinder.keyword("class"), oneSpaceWrap);

		// difference between comma's in process class
		commaSpacing(regionFinder, grammar.getCommaKeyword_4_1_1_0(), oneSpaceWrap, document); // process parameters
		commaSpacing(regionFinder, grammar.getCommaKeyword_7_1_0(), document); // ports
		commaSpacing(regionFinder, grammar.getCommaKeyword_7_2(), document);
		commaSpacing(regionFinder, grammar.getCommaKeyword_9_1_0(), document); // messages
		commaSpacing(regionFinder, grammar.getCommaKeyword_9_2(), document);
		commaSpacing(regionFinder, grammar.getCommaKeyword_11_1_0(), document); // variables
		commaSpacing(regionFinder, grammar.getCommaKeyword_11_2(), document);

		formatExtend(regionFinder, oneSpaceWrap, document);
		keywordsOnNewLine(process, document, "ports", "messages", "variables", "init", "methods");

		List<Pair<ISemanticRegion, ISemanticRegion>> brackets = regionFinder.keywordPairs("(", ")");
		if (!brackets.isEmpty()) {
			document.prepend(brackets.get(0).getKey(), noSpace);
			formatBracketDeclarations(process.getParameters(), brackets.get(0), oneSpaceWrap, document);
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
			document.prepend(instanceVariables, newLine);
			format(instanceVariables, createOneSpaceWrap(instanceVariables), document);
		}

		if (process.getInitialMethodCall() != null) {
			document.prepend(process.getInitialMethodCall(), newLine);
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

		document.prepend(method, (firstMethod) ? newLine : emptyLine);
		document.append(mRegionFinder.feature(Literals.PROCESS_METHOD__NAME), noSpace);
		bracketPairSpacing(mRegionFinder, document);

		Procedure1<IHiddenRegionFormatter> oneSpaceWrapMethod = (method.getBody() == null)
				? createOneSpaceWrapDouble(method)
				: createOneSpaceUpTo(method.getBody());

		commaSpacing(mRegionFinder, oneSpaceWrapMethod, document);

		List<Pair<ISemanticRegion, ISemanticRegion>> mBrackets = mRegionFinder.keywordPairs("(", ")");
		if (mBrackets.size() == 2) {
			document.append(mBrackets.get(0).getValue(), noSpace);
			formatBracketDeclarations(method.getInputParameters(), mBrackets.get(0), oneSpaceWrapMethod, document);
			formatBracketDeclarations(method.getOutputParameters(), mBrackets.get(1), oneSpaceWrapMethod, document);
		}
		formatPipeDeclarations(method.getLocalVariables(), mRegionFinder, oneSpaceWrapMethod, document);

		document.prepend(method.getBody(), newLine);
		indent(method.getBody(), document, false);
		format(method.getBody(), document, null, false);
	}

	private void formatExtend(ISemanticRegionsFinder regionFinder, Procedure1<IHiddenRegionFormatter> oneSpaceWrap,
			IFormattableDocument document) {
		ISemanticRegion kwExtend = regionFinder.keyword("extends");
		if (kwExtend != null) {
			document.prepend(kwExtend, oneSpaceWrap);
			document.append(kwExtend, oneSpaceWrap);
		}
	}

	private void format(Port port, IFormattableDocument document) {
		document.prepend(port, newLine);
	}

	private void format(Instance instance, IFormattableDocument document) {
		document.prepend(instance, newLine);
		ISemanticRegionsFinder regionFinder = textRegionExtensions.regionFor(instance);
		format(instance.getAnnotations(), document);

		document.append(regionFinder.feature(Literals.INSTANCE__CLASS_DEFINITION), noSpace);
		colonSpacing(regionFinder, document);
		bracketPairSpacing(regionFinder, document);

		Procedure1<IHiddenRegionFormatter> oneSpaceWrap = createOneSpaceWrap(instance);
		commaSpacing(regionFinder, oneSpaceWrap, document);

		for (InstanceParameter instanceParameter : instance.getInstanceParameters()) {
			document.append(
					textRegionExtensions.regionFor(instanceParameter).feature(Literals.INSTANCE_PARAMETER__PARAMETER),
					oneSpaceWrap);
			document.prepend(instanceParameter.getExpression(), oneSpaceWrap);
			format(instanceParameter.getExpression(), document, oneSpaceWrap, true);
		}
	}

	private void format(MessageSignature signature, IFormattableDocument document) {
		document.prepend(signature, newLine);
		ISemanticRegionsFinder regionFinder = textRegionExtensions.regionFor(signature);
		document.append(regionFinder.feature(Literals.MESSAGE_SIGNATURE__NAME), noSpace);
		exclamationSpacing(regionFinder, document);
		questionMarkSpacing(regionFinder, document);
		bracketPairSpacing(regionFinder, document);
		commaSpacing(regionFinder, createOneSpaceWrapDouble(signature), document);
	}

	private void format(Channel channel, IFormattableDocument document) {
		document.prepend(channel, newLine);
		format(channel.getAnnotations(), document);
		ISemanticRegionsFinder regionFinder = textRegionExtensions.regionFor(channel);
		curlyBracketPairSpacing(regionFinder, document);

		Procedure1<IHiddenRegionFormatter> oneSpaceWrap = createOneSpaceWrap(channel);
		// TODO commaspacing needs to use oneSpaceWrap, atm causes an extra indent,
		// fixed in xtext 2.13
		// https://esi-redmine.tno.nl/issues/2605
		commaSpacing(regionFinder, oneSpaceWrap, document);

		for (InstancePort instancePort : channel.getInstancePorts()) {
			periodSpacing(textRegionExtensions.regionFor(instancePort), document);
		}
	}

	private void format(Declaration declaration, Procedure1<? super IHiddenRegionFormatter> oneSpaceWrapper,
			IFormattableDocument document) {
		ISemanticRegionsFinder regionFinder = textRegionExtensions.regionFor(declaration);
		colonSpacing(regionFinder, document);
		commaSpacing(regionFinder, oneSpaceWrapper, document);
	}

	private void formatNewLine(Declaration declaration, Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
			IFormattableDocument document) {
		ISemanticRegionsFinder regionFinder = textRegionExtensions.regionFor(declaration);
		document.prepend(declaration, newLine);
		colonSpacing(regionFinder, document);
		commaSpacing(regionFinder, oneSpaceWrapper, document);
	}

	private void format(Expression expression, IFormattableDocument document,
			Procedure1<IHiddenRegionFormatter> oneSpaceWrapper, boolean singleLine) {
		ISemanticRegionsFinder regionFinder = textRegionExtensions.regionFor(expression);
		if (expression instanceof ExpressionSequence) {
			EList<Expression> expressions = ((ExpressionSequence) expression).getExpressions();
			if (expression instanceof ExpressionSequenceRoundBracket) {
				formatRoundBracketSequence(expressions, document, regionFinder, singleLine);
			}
			semiColonSpacing(regionFinder, document, singleLine);
			for (Expression expr : expressions) {
				format(expr, document, oneSpaceWrapper, singleLine);
			}
		} else {
			oneSpaceWrapper = (oneSpaceWrapper == null) ? createOneSpaceWrap(expression) : oneSpaceWrapper;

			if (expression instanceof AssignmentExpression) {
				document.surround(regionFinder.keyword(":="), oneSpaceWrapper);
				format(((AssignmentExpression) expression).getExpression(), document, oneSpaceWrapper, singleLine);
			} else if (expression instanceof ReturnExpression) {
				document.append(regionFinder.keyword("return"), oneSpaceWrapper);
				format(((ReturnExpression) expression).getExpression(), document, oneSpaceWrapper, singleLine);
			} else if (expression instanceof BinaryOperatorExpression) {
				format((BinaryOperatorExpression) expression, document, oneSpaceWrapper, singleLine);
			} else if (expression instanceof DataMethodCallExpression) {
				format((DataMethodCallExpression) expression, document, regionFinder, oneSpaceWrapper, singleLine);
			} else if (expression instanceof UnaryOperatorExpression) {
				format((UnaryOperatorExpression) expression, document, oneSpaceWrapper, singleLine);
			} else if (expression instanceof IfExpression) {
				format((IfExpression) expression, document, regionFinder, oneSpaceWrapper, singleLine);
			} else if (expression instanceof WhileExpression) {
				format((WhileExpression) expression, document, regionFinder, oneSpaceWrapper, singleLine);
			} else if (expression instanceof SwitchExpression) {
				format((SwitchExpression) expression, document, regionFinder, oneSpaceWrapper, singleLine);
			} else if (expression instanceof NewExpression) {
				format((NewExpression) expression, document, oneSpaceWrapper, regionFinder);
			}
		}
	}

	private void format(IfExpression expression, IFormattableDocument document, ISemanticRegionsFinder regionFinder,
			Procedure1<IHiddenRegionFormatter> oneSpaceWrapper, boolean singleLine) {
		document.append(regionFinder.keyword("if"), oneSpaceNoWrap);
		ISemanticRegion kwThen = regionFinder.keyword("then");
		Procedure1<IHiddenRegionFormatter> oneSpaceCondition = getOneSpaceWrapIncluding(kwThen);

		Procedure1<IHiddenRegionFormatter> lineFormat = getLineFormatting(singleLine, oneSpaceWrapper);
		document.prepend(kwThen, oneSpaceCondition);
		document.append(kwThen, lineFormat);

		ISemanticRegion kwElse = regionFinder.keyword("else");
		ISemanticRegion kwFi = regionFinder.keyword("fi");
		if (kwElse != null) {
			document.prepend(kwElse, lineFormat);
			ISemanticRegion nextSemanticRegion = kwElse.getNextSemanticRegion();
			if (nextSemanticRegion != null && nextSemanticRegion.getText().equals("if")) {
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

	private void format(SwitchExpression expression, IFormattableDocument document, ISemanticRegionsFinder regionFinder,
			Procedure1<IHiddenRegionFormatter> oneSpaceWrapper, boolean singleLine) {
		document.append(regionFinder.keyword("switch"), oneSpaceWrapper);
		List<Pair<ISemanticRegion, ISemanticRegion>> keywordPairs = regionFinder.keywordPairs("do", "od");

		Procedure1<IHiddenRegionFormatter> oneSpaceWrapperCondition = getOneSpaceWrapIncluding(
				keywordPairs.isEmpty() ? null : keywordPairs.get(0).getKey());

		Procedure1<IHiddenRegionFormatter> lineFormat = getLineFormatting(singleLine, oneSpaceWrapper);
		for (Pair<ISemanticRegion, ISemanticRegion> pair : keywordPairs) {
			document.prepend(pair.getKey(), oneSpaceWrapperCondition);
			document.prepend(pair.getValue(), lineFormat);
			document.interior(pair.getKey(), pair.getValue(), indent);
		}
		format(expression.getExpression(), document, oneSpaceWrapperCondition, singleLine);

		for (SwitchExpressionCase switchExpressionCase : expression.getCases()) {
			ISemanticRegionsFinder cRegionFinder = textRegionExtensions.regionFor(switchExpressionCase);
			ISemanticRegion kwCase = cRegionFinder.keyword("case");
			document.prepend(kwCase, lineFormat);

			ISemanticRegion kwThen = cRegionFinder.keyword("then");
			Procedure1<IHiddenRegionFormatter> oneSpaceCaseCondition = getOneSpaceWrapIncluding(kwThen);

			document.append(kwCase, oneSpaceCaseCondition);
			document.prepend(kwThen, oneSpaceCaseCondition);
			document.prepend(switchExpressionCase.getBody(), lineFormat);

			indent(switchExpressionCase.getBody(), document, singleLine);
			format(switchExpressionCase.getValue(), document, oneSpaceCaseCondition, singleLine);
			format(switchExpressionCase.getBody(), document, null, singleLine);
		}

		ISemanticRegion kwDefault = regionFinder.keyword("default");
		document.prepend(kwDefault, lineFormat);
		document.append(kwDefault, lineFormat);
		indent(expression.getDefaultBody(), document, singleLine);

		format(expression.getDefaultBody(), document, null, singleLine);
	}

	private void format(WhileExpression expression, IFormattableDocument document, ISemanticRegionsFinder regionFinder,
			Procedure1<IHiddenRegionFormatter> oneSpaceWrapper, boolean singleLine) {
		document.append(regionFinder.keyword("while"), oneSpaceWrapper);
		ISemanticRegion kwDo = regionFinder.keyword("do");
		Procedure1<IHiddenRegionFormatter> oneSpaceWrapperCond = getOneSpaceWrapIncluding(kwDo);

		document.prepend(kwDo, oneSpaceWrapperCond);
		document.append(kwDo, getLineFormatting(singleLine, oneSpaceWrapper));
		document.prepend(regionFinder.keyword("od"), getLineFormatting(singleLine, oneSpaceWrapper));

		format(expression.getCondition(), document, oneSpaceWrapperCond, singleLine);
		indent(expression.getBody(), document, singleLine);
		format(expression.getBody(), document, null, singleLine);
	}

	private void format(DataMethodCallExpression expression, IFormattableDocument document,
			ISemanticRegionsFinder regionFinder, Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
			boolean singleLine) {
		methodCallBracketSpacing(regionFinder, document);
		commaSpacing(regionFinder, oneSpaceWrapper, document);
		for (Expression expressions : expression.getArguments()) {
			format(expressions, document, null, true);
		}
		if (expression.isOnSuperClass()) {
			document.append(regionFinder.keyword("^"), oneSpaceWrapper);
			document.append(expression.getTarget(), noSpace);
		} else {
			document.append(expression.getTarget(), oneSpaceWrapper);
		}
		format(expression.getTarget(), document, null, singleLine);
	}

	private void format(BinaryOperatorExpression expression, IFormattableDocument document,
			Procedure1<IHiddenRegionFormatter> oneSpaceWrapper, boolean singleLine) {
		Expression left = expression.getLeftOperand();
		Expression right = expression.getRightOperand();

		document.append(left, oneSpaceWrapper);
		document.prepend(right, oneSpaceWrapper);
		format(left, document, oneSpaceWrapper, singleLine);
		format(right, document, oneSpaceWrapper, singleLine);
	}

	private void format(UnaryOperatorExpression expression, IFormattableDocument document,
			Procedure1<IHiddenRegionFormatter> oneSpaceWrapper, boolean singleLine) {
		Expression oper = expression.getOperand();
		document.prepend(oper, noSpace);
		format(oper, document, oneSpaceWrapper, singleLine);
	}

	private void format(NewExpression expression, IFormattableDocument document,
			Procedure1<IHiddenRegionFormatter> oneSpaceWrapper, ISemanticRegionsFinder regionFinder) {
		ISemanticRegion kwNew = regionFinder.keyword("new");
		if (kwNew.immediatelyFollowing().keyword("(") != null) {
			document.append(kwNew, noSpace);
		} else {
			document.append(kwNew, oneSpaceWrapper);
		}
	}

	private void format(Statement statement, IFormattableDocument document,
			Procedure1<IHiddenRegionFormatter> oneSpaceWrapper, boolean singleLine) {
		ISemanticRegionsFinder regionFinder = textRegionExtensions.regionFor(statement);
		if (statement instanceof StatementSequence) {
			EList<Statement> statements = ((StatementSequence) statement).getStatements();
			if (statement instanceof StatementSequenceRoundBracket) {
				formatRoundBracketSequence(statements, document, regionFinder, singleLine);
			}

			semiColonSpacing(regionFinder, document, singleLine);
			for (Statement stat : statements) {
				format(stat, document, oneSpaceWrapper, singleLine);
			}
		} else {
			oneSpaceWrapper = (oneSpaceWrapper == null) ? createOneSpaceWrapDouble(statement) : oneSpaceWrapper;

			if (statement instanceof AbortStatement) {
				format(((AbortStatement) statement), document, regionFinder, oneSpaceWrapper, singleLine);
			} else if (statement instanceof DelayStatement) {
				ISemanticRegion kwDelay = regionFinder.keyword("delay");
				document.append(kwDelay, oneSpaceWrapper);
				format(((DelayStatement) statement).getExpression(), document, oneSpaceWrapper, singleLine);
			} else if (statement instanceof GuardedStatement) {
				squareBracketSpacing(regionFinder, oneSpaceWrapper, document);
				format(((GuardedStatement) statement).getGuard(), document, oneSpaceWrapper, singleLine);
				format(((GuardedStatement) statement).getStatement(), document, oneSpaceWrapper, singleLine);
			} else if (statement instanceof InterruptStatement) {
				format(((InterruptStatement) statement), document, regionFinder, oneSpaceWrapper, singleLine);
			} else if (statement instanceof ParStatement) {
				format(((ParStatement) statement), document, regionFinder, oneSpaceWrapper, singleLine);
			} else if (statement instanceof ProcessMethodCall) {
				format(((ProcessMethodCall) statement), document, regionFinder, oneSpaceWrapper, singleLine);
			} else if (statement instanceof SelStatement) {
				format(((SelStatement) statement), document, regionFinder, oneSpaceWrapper, singleLine);
			} else if (statement instanceof SendStatement) {
				format(((SendStatement) statement), document, regionFinder, oneSpaceWrapper, singleLine);
			} else if (statement instanceof ReceiveStatement) {
				format(((ReceiveStatement) statement), document, regionFinder, oneSpaceWrapper, singleLine);
			} else if (statement instanceof IfStatement) {
				format(((IfStatement) statement), document, regionFinder, oneSpaceWrapper, singleLine);
			} else if (statement instanceof WhileStatement) {
				format(((WhileStatement) statement), document, regionFinder, oneSpaceWrapper, singleLine);
			} else if (statement instanceof SwitchStatement) {
				format(((SwitchStatement) statement), document, regionFinder, oneSpaceWrapper, singleLine);
			} else if (statement instanceof ExpressionStatement) {
				formatCurlyBracketsMultiOrSingle(((ExpressionStatement) statement).getExpression(), document,
						regionFinder, singleLine, oneSpaceWrapper, false);
			}
		}
	}

	private void format(WhileStatement statement, IFormattableDocument document, ISemanticRegionsFinder regionFinder,
			Procedure1<IHiddenRegionFormatter> oneSpaceWrapper, boolean singleLine) {
		ISemanticRegion kwDo = regionFinder.keyword("do");
		Procedure1<IHiddenRegionFormatter> oneSpaceWrapperCondition = getOneSpaceWrapIncluding(kwDo);

		document.prepend(kwDo, oneSpaceWrapperCondition);
		document.append(kwDo, getLineFormatting(singleLine, oneSpaceWrapper));
		document.prepend(regionFinder.keyword("od"), getLineFormatting(singleLine, oneSpaceWrapper));
		format(statement.getCondition(), document, oneSpaceWrapperCondition, singleLine);
		indent(statement.getBody(), document, singleLine);
		format(statement.getBody(), document, null, singleLine);
	}

	private void format(IfStatement statement, IFormattableDocument document, ISemanticRegionsFinder regionFinder,
			Procedure1<IHiddenRegionFormatter> oneSpaceWrapper, boolean singleLine) {
		ISemanticRegion kwThen = regionFinder.keyword("then");
		Procedure1<IHiddenRegionFormatter> oneSpaceWrapperCondition = getOneSpaceWrapIncluding(kwThen);

		Procedure1<IHiddenRegionFormatter> lineFormat = getLineFormatting(singleLine, oneSpaceWrapper);
		document.prepend(kwThen, oneSpaceWrapperCondition);
		document.append(kwThen, lineFormat);
		ISemanticRegion kwElse = regionFinder.keyword("else");
		ISemanticRegion kwFi = regionFinder.keyword("fi");
		if (kwElse != null) {
			document.prepend(kwElse, lineFormat);
			ISemanticRegion nextSemanticRegion = kwElse.getNextSemanticRegion();
			if (nextSemanticRegion != null && nextSemanticRegion.getText().equals("if")) {
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

	private void format(SwitchStatement statement, IFormattableDocument document, ISemanticRegionsFinder regionFinder,
			Procedure1<IHiddenRegionFormatter> oneSpaceWrapper, boolean singleLine) {
		document.append(regionFinder.keyword("switch"), oneSpaceWrapper);
		List<Pair<ISemanticRegion, ISemanticRegion>> keywordPairs = regionFinder.keywordPairs("do", "od");

		Procedure1<IHiddenRegionFormatter> oneSpaceWrapCondition = getOneSpaceWrapIncluding(
				keywordPairs.isEmpty() ? null : keywordPairs.get(0).getKey());

		Procedure1<IHiddenRegionFormatter> lineFormat = getLineFormatting(singleLine, oneSpaceWrapper);
		for (Pair<ISemanticRegion, ISemanticRegion> pair : keywordPairs) {
			document.prepend(pair.getKey(), oneSpaceWrapCondition);
			document.prepend(pair.getValue(), lineFormat);
			document.interior(pair.getKey(), pair.getValue(), indent);
		}
		format(statement.getExpression(), document, oneSpaceWrapCondition, singleLine);

		for (SwitchStatementCase switchExpressionCase : statement.getCases()) {
			ISemanticRegionsFinder cRegionFinder = textRegionExtensions.regionFor(switchExpressionCase);
			ISemanticRegion kwCase = cRegionFinder.keyword("case");
			document.prepend(kwCase, lineFormat);

			ISemanticRegion kwThen = cRegionFinder.keyword("then");
			Procedure1<IHiddenRegionFormatter> oneSpaceCaseCondition = getOneSpaceWrapIncluding(kwThen);

			document.append(kwCase, oneSpaceCaseCondition);
			document.prepend(kwThen, oneSpaceCaseCondition);
			document.prepend(switchExpressionCase.getBody(), lineFormat);

			indent(switchExpressionCase.getBody(), document, singleLine);
			format(switchExpressionCase.getValue(), document, oneSpaceCaseCondition, singleLine);
			format(switchExpressionCase.getBody(), document, null, singleLine);
		}

		ISemanticRegion kwDefault = regionFinder.keyword("default");
		document.prepend(kwDefault, lineFormat);
		document.append(kwDefault, lineFormat);
		indent(statement.getDefaultBody(), document, singleLine);
		format(statement.getDefaultBody(), document, null, singleLine);
	}

	private void format(ParStatement statement, IFormattableDocument document, ISemanticRegionsFinder regionFinder,
			Procedure1<IHiddenRegionFormatter> oneSpaceWrapper, boolean singleLine) {
		Procedure1<IHiddenRegionFormatter> lineFormat = getLineFormatting(singleLine, oneSpaceWrapper);
		document.append(regionFinder.keyword("par"), lineFormat);
		for (ISemanticRegion iSemanticRegion : regionFinder.keywords("and")) {
			document.prepend(iSemanticRegion, lineFormat);
			document.append(iSemanticRegion, lineFormat);
		}

		document.prepend(regionFinder.keyword("rap"), lineFormat);
		for (Statement stat : statement.getClauses()) {
			indent(stat, document, singleLine);
			format(stat, document, null, singleLine);
		}
	}

	private void format(SelStatement statement, IFormattableDocument document, ISemanticRegionsFinder regionFinder,
			Procedure1<IHiddenRegionFormatter> oneSpaceWrapper, boolean singleLine) {
		document.append(regionFinder.keyword("sel"), newLine);
		Procedure1<IHiddenRegionFormatter> lineFormat = getLineFormatting(singleLine, oneSpaceWrapper);
		for (ISemanticRegion iSemanticRegion : regionFinder.keywords("or")) {
			document.prepend(iSemanticRegion, lineFormat);
			document.append(iSemanticRegion, lineFormat);
		}
		document.prepend(regionFinder.keyword("les"), lineFormat);

		for (Statement stat : statement.getClauses()) {
			indent(stat, document, singleLine);
			format(stat, document, null, singleLine);
		}
	}

	private void format(AbortStatement statement, IFormattableDocument document, ISemanticRegionsFinder regionFinder,
			Procedure1<IHiddenRegionFormatter> oneSpaceWrapper, boolean singleLine) {
		document.append(regionFinder.keyword("abort"), getLineFormatting(singleLine, oneSpaceWrapper));
		formatStatementWith(statement.getNormalClause(), statement.getAbortingClause(), document, regionFinder,
				oneSpaceWrapper, singleLine);
	}

	private void format(InterruptStatement statement, IFormattableDocument document,
			ISemanticRegionsFinder regionFinder, Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
			boolean singleLine) {
		document.append(regionFinder.keyword("interrupt"), getLineFormatting(singleLine, oneSpaceWrapper));
		formatStatementWith(statement.getNormalClause(), statement.getInterruptingClause(), document, regionFinder,
				oneSpaceWrapper, singleLine);
	}

	private void formatStatementWith(Statement normal, Statement with, IFormattableDocument document,
			ISemanticRegionsFinder regionFinder, Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
			boolean singleLine) {
		ISemanticRegion kwWith = regionFinder.keyword("with");
		document.prepend(kwWith, getLineFormatting(singleLine, oneSpaceWrapper));
		document.append(kwWith, oneSpaceWrapper);

		indent(normal, document, singleLine);
		format(normal, document, null, singleLine);
		format(with, document, null, singleLine);
	}

	private void format(SendStatement statement, IFormattableDocument document, ISemanticRegionsFinder regionFinder,
			Procedure1<IHiddenRegionFormatter> oneSpaceWrapper, boolean singleLine) {
		document.append(regionFinder.feature(Literals.SEND_STATEMENT__NAME), noSpace);
		bracketPairSpacing(regionFinder, document);
		commaSpacing(regionFinder, oneSpaceWrapper, document);
		exclamationSpacing(regionFinder, document);

		formatCurlyBracketsMultiOrSingle(statement.getPostCommunicationExpression(), document, regionFinder, singleLine,
				oneSpaceWrapper, true);
		for (Expression expression : statement.getArguments()) {
			format(expression, document, null, true);
		}
	}

	private void format(ReceiveStatement statement, IFormattableDocument document, ISemanticRegionsFinder regionFinder,
			Procedure1<IHiddenRegionFormatter> oneSpaceWrapper, boolean singleLine) {
		bracketPairSpacing(regionFinder, document);
		commaSpacing(regionFinder, oneSpaceWrapper, document);
		questionMarkSpacing(regionFinder, document);
		formatCurlyBracketsMultiOrSingle(statement.getPostCommunicationExpression(), document, regionFinder, singleLine,
				oneSpaceWrapper, true);
		format(statement.getReceptionCondition(), document, null, true);
	}

	private void format(ProcessMethodCall processMethodCall, IFormattableDocument document,
			ISemanticRegionsFinder regionFinder, Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
			boolean singleLine) {
		methodCallBracketSpacing(regionFinder, document);
		for (Expression expression : processMethodCall.getInputArguments()) {
			format(expression, document, null, true);
		}
	}

	private void format(EList<Annotation> annotations, IFormattableDocument document) {
		for (Annotation annotation : annotations) {
			document.append(annotation, newLine);
			ISemanticRegionsFinder regionFinder = textRegionExtensions.regionFor(annotation);

			ISemanticRegion annotationName = regionFinder
					.assignment(pooslGrammar.getAnnotationAccess().getNameAssignment_1());
			document.surround(annotationName, noSpace);

			formatRoundBracketSequence(annotation.getArguments(), document, regionFinder, true);
			commaSpacing(regionFinder, oneSpace, document);
		}
	}

	private void formatRoundBracketSequence(EList<? extends EObject> list, IFormattableDocument document,
			ISemanticRegionsFinder regionFinder, Boolean singleLine) {
		List<Pair<ISemanticRegion, ISemanticRegion>> brackets = regionFinder.keywordPairs("(", ")");
		singleLine |= list.size() < 2;
		formatBracketsSingleOrMulti(singleLine, document, brackets);
	}

	private void formatCurlyBracketsMultiOrSingle(Expression expression, IFormattableDocument document,
			ISemanticRegionsFinder regionFinder, boolean singleLine, Procedure1<IHiddenRegionFormatter> oneSpaceWrapper,
			boolean prependSpace) {
		if (expression != null) {
			List<Pair<ISemanticRegion, ISemanticRegion>> brackets = regionFinder.keywordPairs("{", "}");
			if (prependSpace && !brackets.isEmpty()) {
				document.prepend(brackets.get(0).getKey(), oneSpaceWrapper);
			}
			singleLine |= !isMultiLine(expression);
			formatBracketsSingleOrMulti(singleLine, document, brackets);
			format(expression, document, null, singleLine);
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
		return expression instanceof BooleanConstant || expression instanceof EnvironmentConstant
				|| expression instanceof FloatConstant || expression instanceof IntegerConstant
				|| expression instanceof NilConstant || expression instanceof RealConstant
				|| expression instanceof StringConstant;
	}

	private void formatBracketsSingleOrMulti(boolean singleLine, IFormattableDocument document,
			List<Pair<ISemanticRegion, ISemanticRegion>> brackets) {
		if (!brackets.isEmpty()) {
			Pair<ISemanticRegion, ISemanticRegion> pair = brackets.get(0);
			if (singleLine) {
				document.append(pair.getKey(), noSpace);
				document.prepend(pair.getValue(), noSpace);
			} else {
				document.append(pair.getKey(), newLine);
				document.prepend(pair.getValue(), newLine);
				document.interior(pair, indent);
			}
		}
	}

	// --- Text region helper functions -------

	private void topElementEmptyLine(EObject object, IFormattableDocument document) {
		ISemanticRegion previousSemantic = textRegionExtensions.regionForEObject(object).getPreviousSemanticRegion();
		if (previousSemantic != null) {
			EObject element = previousSemantic.getSemanticElement();
			if (element instanceof ProcessClass || element instanceof ClusterClass || element instanceof DataClass) {
				// previous object is a keyword, add extra emptyLine
				document.prepend(object, emptyLines);
			} else {
				document.prepend(object, emptyLine);
			}
		} else {
			// top of the file
			document.prepend(object, noSpace);
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
	 * between each other and behave as one list. Finds which EObject in the lists
	 * is actually the first or last based on its offset in the document and indents
	 * based on that info. It doesn't have to iterate through the lists it only
	 * needs the first and last of the list as they are also the first and last in
	 * the document. (Example: send and receive messages)
	 * 
	 * @param document
	 * @param lists
	 * @return can return null if the lists
	 */
	private EObject indentLists(IFormattableDocument document, List<EList<? extends EObject>> lists) {
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
		document.set(firstHidden, lastHidden, indent);
	}

	private void indent(EObject obj, IFormattableDocument document, boolean singleLine) {
		if (obj != null) {
			if (singleLine) {
				document.prepend(obj, oneSpaceNoWrap);
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
				document.prepend(kwRegion, emptyLine);

			} else {
				document.prepend(kwRegion, newLine);
			}
		}
	}

	private void formatPipeDeclarations(EList<Declaration> declarations, ISemanticRegionsFinder mRegionFinder,
			Procedure1<IHiddenRegionFormatter> oneSpaceWrap, IFormattableDocument document) {
		List<ISemanticRegion> pipes = mRegionFinder.keywords("|");
		if (pipes.size() == 2) {
			if (formatMutlilineDeclarations(pipes.get(0), pipes.get(1), declarations, oneSpaceWrap, document)) {
				document.prepend(pipes.get(0), oneSpaceWrap);
				document.prepend(pipes.get(1), oneSpaceNoWrap);
			} else {
				document.prepend(pipes.get(0), oneSpaceWrap);
				document.append(pipes.get(0), oneSpaceNoWrap);
				document.prepend(pipes.get(1), oneSpaceNoWrap);
			}
		}
	}

	private void formatBracketDeclarations(EList<Declaration> declarations, Pair<ISemanticRegion, ISemanticRegion> pair,
			Procedure1<IHiddenRegionFormatter> oneSpaceWrap, IFormattableDocument document) {
		if (formatMutlilineDeclarations(pair.getKey(), pair.getValue(), declarations, oneSpaceWrap, document)) {
			document.prepend(pair.getValue(), noSpace);
		} else {
			document.append(pair.getKey(), noSpace);
			document.prepend(pair.getValue(), noSpace);
		}
	}

	/**
	 * return true if multiline formatting is applied
	 * 
	 * @param declarations
	 * @param oneSpaceWrap
	 * @param mRegionFinder
	 * @param document
	 * @return
	 */
	private boolean formatMutlilineDeclarations(ISemanticRegion fromRegion, ISemanticRegion toRegion,
			EList<Declaration> declarations, Procedure1<IHiddenRegionFormatter> oneSpaceWrap,
			IFormattableDocument document) {
		if (!declarations.isEmpty()) {
			IEObjectRegion firstLocal = textRegionExtensions.regionForEObject(declarations.get(0));
			IHiddenRegion s = firstLocal.getPreviousHiddenRegion();

			if (s.isMultiline() || s.containsComment()) {
				// format multiline
				if (fromRegion != null && toRegion != null) {
					document.interior(fromRegion, toRegion, doubleIndent);
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

	private Procedure1<IHiddenRegionFormatter> getLineFormatting(boolean singleLine,
			Procedure1<IHiddenRegionFormatter> oneSpaceWrap) {
		return (singleLine) ? oneSpaceWrap : newLine;
	}

	// --- Static helper functions -------

	private static void questionMarkSpacing(ISemanticRegionsFinder regionFinder, IFormattableDocument document) {
		for (ISemanticRegion iSemanticRegion : regionFinder.keywords("?")) {
			document.surround(iSemanticRegion, noSpace);
		}
	}

	private static void bracketPairSpacing(ISemanticRegionsFinder regionFinder, IFormattableDocument document) {
		for (Pair<ISemanticRegion, ISemanticRegion> pair : regionFinder.keywordPairs("(", ")")) {
			document.prepend(pair.getKey(), noSpace);
			document.append(pair.getKey(), noSpace);
			document.prepend(pair.getValue(), noSpace);
		}
	}

	private static void curlyBracketPairSpacing(ISemanticRegionsFinder regionFinder, IFormattableDocument document) {
		for (Pair<ISemanticRegion, ISemanticRegion> pair : regionFinder.keywordPairs("{", "}")) {
			document.prepend(pair.getKey(), noSpace);
			document.append(pair.getKey(), oneSpaceNoWrap);
			document.prepend(pair.getValue(), oneSpaceNoWrap);
			document.append(pair.getValue(), noSpace);
		}
	}

	private static void squareBracketSpacing(ISemanticRegionsFinder regionFinder,
			Procedure1<IHiddenRegionFormatter> oneSpaceWrap, IFormattableDocument document) {
		for (Pair<ISemanticRegion, ISemanticRegion> pair : regionFinder.keywordPairs("[", "]")) {
			document.append(pair.getValue(), oneSpaceWrap);
		}
	}

	private static void methodCallBracketSpacing(ISemanticRegionsFinder regionFinder, IFormattableDocument document) {
		for (Pair<ISemanticRegion, ISemanticRegion> pair : regionFinder.keywordPairs("(", ")")) {
			document.surround(pair.getKey(), noSpace);
			document.prepend(pair.getValue(), noSpace);
		}
	}

	private static void commaSpacing(ISemanticRegionsFinder regionFinder,
			Procedure1<? super IHiddenRegionFormatter> oneSpaceWrapper, IFormattableDocument document) {
		for (ISemanticRegion iSemanticRegion : regionFinder.keywords(",")) {
			document.prepend(iSemanticRegion, noSpace);
			document.append(iSemanticRegion, oneSpaceWrapper);
		}
	}

	private static void commaSpacing(ISemanticRegionsFinder regionFinder, Keyword comma,
			Procedure1<? super IHiddenRegionFormatter> oneSpaceWrapper, IFormattableDocument document) {
		for (ISemanticRegion iSemanticRegion : regionFinder.keywords(comma)) {
			document.prepend(iSemanticRegion, noSpace);
			document.append(iSemanticRegion, oneSpaceWrapper);
		}
	}

	private static void commaSpacing(ISemanticRegionsFinder regionFinder, Keyword comma,
			IFormattableDocument document) {
		for (ISemanticRegion iSemanticRegion : regionFinder.keywords(comma)) {
			document.prepend(iSemanticRegion, noSpace);
		}
	}

	private static void semiColonSpacing(ISemanticRegionsFinder regionFinder, IFormattableDocument document,
			boolean singleLine) {
		for (ISemanticRegion iSemanticRegion : regionFinder.keywords(";")) {
			document.prepend(iSemanticRegion, noSpace);
			if (singleLine) {
				document.append(iSemanticRegion, noSpace);
			} else {
				document.append(iSemanticRegion, newLine);
			}
		}
	}

	private static void periodSpacing(ISemanticRegionsFinder regionFinder, IFormattableDocument document) {
		for (ISemanticRegion iSemanticRegion : regionFinder.keywords(".")) {
			document.surround(iSemanticRegion, noSpace);
		}
	}

	private static void exclamationSpacing(ISemanticRegionsFinder regionFinder, IFormattableDocument document) {
		for (ISemanticRegion iSemanticRegion : regionFinder.keywords("!")) {
			document.surround(iSemanticRegion, noSpace);
		}
	}

	private static void colonSpacing(ISemanticRegionsFinder regionFinder, IFormattableDocument document) {
		for (ISemanticRegion iSemanticRegion : regionFinder.keywords(":")) {
			document.surround(iSemanticRegion, oneSpaceNoWrap);
		}
	}

	private static final Procedure1<IHiddenRegionFormatter> noSpace = new Procedure1<IHiddenRegionFormatter>() {
		@Override
		public void apply(final IHiddenRegionFormatter it) {
			it.noSpace();
		}
	};

	private static final Procedure1<IHiddenRegionFormatter> oneSpace = new Procedure1<IHiddenRegionFormatter>() {
		@Override
		public void apply(final IHiddenRegionFormatter it) {
			it.oneSpace();
			it.autowrap();
		}
	};

	private Procedure1<IHiddenRegionFormatter> createOneSpaceWrap(EObject semanticElement) {
		return (semanticElement == null) ? oneSpace
				: getOneSpaceWrapper(textRegionExtensions.nextHiddenRegion(semanticElement), indent);
	}

	private Procedure1<IHiddenRegionFormatter> createOneSpaceUpTo(EObject semanticElement) {
		return (semanticElement == null) ? oneSpace
				: getOneSpaceWrapper(textRegionExtensions.previousHiddenRegion(semanticElement), doubleIndent);
	}

	private Procedure1<IHiddenRegionFormatter> createOneSpaceWrapDouble(EObject semanticElement) {
		return (semanticElement == null) ? oneSpace
				: getOneSpaceWrapper(textRegionExtensions.nextHiddenRegion(semanticElement), doubleIndent);
	}

	private Procedure1<IHiddenRegionFormatter> getOneSpaceWrapIncluding(ISemanticRegion region) {
		return getOneSpaceWrapIncluding(region, doubleIndent);
	}

	private Procedure1<IHiddenRegionFormatter> getOneSpaceWrapIncluding(ISemanticRegion region,
			Procedure1<IHiddenRegionFormatter> proc) {
		if (region != null) {
			ISemanticRegion nextSem = region.getNextSemanticRegion();
			if (nextSem != null) {
				return getOneSpaceWrapper(nextSem.getPreviousHiddenRegion(), proc);
			}
		}
		return oneSpace;
	}

	private Procedure1<IHiddenRegionFormatter> createOneSpaceWrapUpTo(ISemanticRegion region,
			Procedure1<IHiddenRegionFormatter> proc) {
		if (region != null) {
			return getOneSpaceWrapper(region.getPreviousHiddenRegion(), proc);
		}
		return oneSpace;
	}

	private Procedure1<IHiddenRegionFormatter> getOneSpaceWrapper(final IHiddenRegion last,
			Procedure1<IHiddenRegionFormatter> proc) {
		final PooslAutoWrapper wrapper = new PooslAutoWrapper(last, proc);
		return new Procedure1<IHiddenRegionFormatter>() {
			@Override
			public void apply(final IHiddenRegionFormatter it) {
				it.oneSpace();
				it.setOnAutowrap(wrapper);
			}
		};
	}

	private static final Procedure1<IHiddenRegionFormatter> oneSpaceNoWrap = new Procedure1<IHiddenRegionFormatter>() {
		@Override
		public void apply(final IHiddenRegionFormatter it) {
			it.oneSpace();
		}
	};

	private static final Procedure1<IHiddenRegionFormatter> newLine = new Procedure1<IHiddenRegionFormatter>() {
		@Override
		public void apply(final IHiddenRegionFormatter it) {
			it.newLine();
		}
	};

	private static final Procedure1<IHiddenRegionFormatter> emptyLine = new Procedure1<IHiddenRegionFormatter>() {
		@Override
		public void apply(final IHiddenRegionFormatter it) {
			it.setNewLines(2);
		}
	};

	private static final Procedure1<IHiddenRegionFormatter> emptyLines = new Procedure1<IHiddenRegionFormatter>() {
		@Override
		public void apply(final IHiddenRegionFormatter it) {
			it.setNewLines(3);
		}
	};

	private static final Procedure1<IHiddenRegionFormatter> indent = new Procedure1<IHiddenRegionFormatter>() {
		@Override
		public void apply(final IHiddenRegionFormatter it) {
			it.indent();
		}
	};

	private static final Procedure1<IHiddenRegionFormatter> doubleIndent = new Procedure1<IHiddenRegionFormatter>() {
		@Override
		public void apply(final IHiddenRegionFormatter it) {
			it.indent();
			it.indent();
		}
	};
}
