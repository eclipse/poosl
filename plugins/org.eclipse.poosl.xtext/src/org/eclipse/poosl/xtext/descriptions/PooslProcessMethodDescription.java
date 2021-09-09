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
package org.eclipse.poosl.xtext.descriptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.poosl.AbortStatement;
import org.eclipse.poosl.GuardedStatement;
import org.eclipse.poosl.InterruptStatement;
import org.eclipse.poosl.ParStatement;
import org.eclipse.poosl.PooslPackage.Literals;
import org.eclipse.poosl.ProcessMethod;
import org.eclipse.poosl.ProcessMethodCall;
import org.eclipse.poosl.SelStatement;
import org.eclipse.poosl.Statement;
import org.eclipse.poosl.StatementSequence;
import org.eclipse.poosl.xtext.custom.FormattingHelper;
import org.eclipse.poosl.xtext.helpers.PooslProcessMethodParser;
import org.eclipse.poosl.xtext.helpers.PooslValidationHelper;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * The PooslProcessMethodDescription.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class PooslProcessMethodDescription {
    private static final String STR_CLASS = "Class"; //$NON-NLS-1$

    private static final String STR_INPUT_PARAMETERS = "InputParameters"; //$NON-NLS-1$

    private static final String STR_OUTPUT_PARAMETERS = "OutputParameters"; //$NON-NLS-1$

    private static final String STR_UNGUARDED_METHOD_CALLS = "UnguardedMethodCalls"; //$NON-NLS-1$

    private PooslProcessMethodDescription() {
        throw new IllegalStateException("Utility class");
    }

    // --- Set -------

    public static Map<String, String> createUserData(String className, ProcessMethod pMethod) {
        Map<String, String> userData = new HashMap<>();
        userData.put(STR_CLASS, className);

        StringBuilder bufInput = new StringBuilder();
        FormattingHelper.formatDeclarations(bufInput, pMethod.getInputParameters());
        userData.put(STR_INPUT_PARAMETERS, bufInput.toString());

        StringBuilder bufOutput = new StringBuilder();
        FormattingHelper.formatDeclarations(bufOutput, pMethod.getOutputParameters());
        userData.put(STR_OUTPUT_PARAMETERS, bufOutput.toString());

        Set<String> unguardedCalls = new HashSet<>();
        computeUnguardedMethodCalls(unguardedCalls, pMethod.getBody());
        userData.put(STR_UNGUARDED_METHOD_CALLS, unguardedCallsToString(unguardedCalls));

        return userData;
    }

    private static String unguardedCallsToString(Set<String> unguardedCalls) {
        StringBuilder builder = new StringBuilder();
        for (String string : unguardedCalls) {
            builder.append(string).append(","); //$NON-NLS-1$
        }
        return builder.toString();
    }

    // --- Get -------

    public static String getClassName(IEObjectDescription descr) {
        if (!checkValidity(descr))
            return null;

        return descr.getUserData(STR_CLASS);
    }

    public static String getFormattedInputParameters(IEObjectDescription descr) {
        if (!checkValidity(descr))
            return null;

        return descr.getUserData(STR_INPUT_PARAMETERS);
    }

    public static String getFormattedOutputParameters(IEObjectDescription descr) {
        if (!checkValidity(descr))
            return null;

        return descr.getUserData(STR_OUTPUT_PARAMETERS);
    }

    public static List<PooslProcessMethodParser> getUnguardedCallDescriptions(IEObjectDescription descr) {
        if (!checkValidity(descr))
            return Collections.emptyList();

        String stringDescriptions = descr.getUserData(STR_UNGUARDED_METHOD_CALLS);
        List<PooslProcessMethodParser> calls = new ArrayList<>();
        if (stringDescriptions != null && stringDescriptions.length() != 0) {
            String[] mStatements = stringDescriptions.split(","); //$NON-NLS-1$
            for (int i = 0; i < mStatements.length; i++) {
                calls.add(new PooslProcessMethodParser(mStatements[i]));
            }
        }
        return calls;
    }

    private static boolean checkValidity(IEObjectDescription descr) {
        return PooslValidationHelper.checkValidity(descr, Literals.PROCESS_METHOD);
    }

    // --- Custom -------

    public static List<String> getInputParameterTypeNames(IEObjectDescription descr) {
        return FormattingHelper.unformatDeclarationsToTypeNames(getFormattedInputParameters(descr));
    }

    public static List<String> getOutputParameterTypeNames(IEObjectDescription descr) {
        return FormattingHelper.unformatDeclarationsToTypeNames(getFormattedOutputParameters(descr));
    }

    public static int getNumberOfInputParameters(IEObjectDescription input) {
        return FormattingHelper.unformatDeclarationsToSize(getFormattedInputParameters(input));
    }

    public static int getNumberOfOutputParameters(IEObjectDescription input) {
        return FormattingHelper.unformatDeclarationsToSize(getFormattedOutputParameters(input));
    }

    // --- Predicates -------

    public static Predicate<IEObjectDescription> predicateMethod(final String className) {
        return new Predicate<IEObjectDescription>() {
            @Override
            public boolean apply(IEObjectDescription input) {
                return className.equals(getClassName(input));
            }
        };
    }

    public static Predicate<IEObjectDescription> predicateMethod(final Iterable<String> classes) {
        return new Predicate<IEObjectDescription>() {
            @Override
            public boolean apply(IEObjectDescription input) {
                String cName = getClassName(input);
                return cName != null && Iterables.contains(classes, cName);
            }
        };
    }

    public static Predicate<IEObjectDescription> predicateMethod(final int inputParams, final int outputParams, final String className) {
        return new Predicate<IEObjectDescription>() {
            @Override
            public boolean apply(IEObjectDescription input) {
                return className.equals(getClassName(input)) && inputParams == getNumberOfInputParameters(input) && outputParams == getNumberOfOutputParameters(input);
            }
        };
    }

    // ------------------------- Unguarded loop

    private static void computeUnguardedMethodCalls(Set<String> methodCalls, Statement statement) {
        if (statement instanceof ProcessMethodCall) {
            ProcessMethodCall call = (ProcessMethodCall) statement;
            PooslProcessMethodParser unguardedMethodCallDescription = new PooslProcessMethodParser(call);
            methodCalls.add(unguardedMethodCallDescription.toString());
            return;
        }
        if (statement instanceof StatementSequence) {
            List<Statement> sequence = ((StatementSequence) statement).getStatements();
            if (!sequence.isEmpty()) {
                computeUnguardedMethodCalls(methodCalls, sequence.get(0));
            }
        } else if (statement instanceof AbortStatement) {
            computeUnguardedMethodCalls(methodCalls, ((AbortStatement) statement).getNormalClause());
            computeUnguardedMethodCalls(methodCalls, ((AbortStatement) statement).getAbortingClause());
        } else if (statement instanceof GuardedStatement) {
            computeUnguardedMethodCalls(methodCalls, ((GuardedStatement) statement).getStatement());
        } else if (statement instanceof InterruptStatement) {
            computeUnguardedMethodCalls(methodCalls, ((InterruptStatement) statement).getNormalClause());
            computeUnguardedMethodCalls(methodCalls, ((InterruptStatement) statement).getInterruptingClause());
        } else if (statement instanceof ParStatement) {
            for (Statement clause : ((ParStatement) statement).getClauses()) {
                computeUnguardedMethodCalls(methodCalls, clause);
            }
        } else if (statement instanceof SelStatement) {
            for (Statement clause : ((SelStatement) statement).getClauses()) {
                computeUnguardedMethodCalls(methodCalls, clause);
            }
        }
    }
}
