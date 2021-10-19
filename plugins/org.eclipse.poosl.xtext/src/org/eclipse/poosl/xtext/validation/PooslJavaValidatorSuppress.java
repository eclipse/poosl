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
package org.eclipse.poosl.xtext.validation;

import java.io.UnsupportedEncodingException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.poosl.Annotable;
import org.eclipse.poosl.Annotation;
import org.eclipse.poosl.Expression;
import org.eclipse.poosl.StringConstant;
import org.eclipse.poosl.xtext.util.Perf;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;

/**
 * The PooslJavaValidatorSuppress.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslJavaValidatorSuppress extends AbstractPooslValidator {

    /** Suppress warning text in language. */
    public static final String ANNOTATION_SUPPRESSWARNINGS = "SuppressWarnings"; //$NON-NLS-1$

    /** Warning types. */
    // WarningType text has no l10n: used as technical tokens.
    public enum WarningType {
        UNUSED("\"unused\"", true), //$NON-NLS-1$ 
        UNCONNECTED("\"unconnected\"", true), //$NON-NLS-1$ 
        TYPECHECK("\"typecheck\"", true), //$NON-NLS-1$ 
        RETURN("\"return\"", true), //$NON-NLS-1$ 
        ANNOTATION("\"annotation\"", true), //$NON-NLS-1$
        IMPORT("\"import\"", false), //$NON-NLS-1$
        ASSIGNMENT("\"assignment\"", false), //$NON-NLS-1$//$NON-NLS-2$
        UNGUARDED_LOOP("\"unguardedloop\"", false); //$NON-NLS-1$

        private final String text;

        private final boolean allowSuppressed;

        WarningType(final String text, final boolean canBeSuppressed) {
            this.text = text;
            this.allowSuppressed = canBeSuppressed;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    /** Singleton for performance measure. */
    protected static final Perf PERF = new Perf("PooslValidation", true); //$NON-NLS-1$

    /** Message on misuse of warning method. */
    private static final String ILLEGAL_WARNING_CALL = //
            "Unsupported method, call same method with WarningType parameter instead."; //$NON-NLS-1$

    private String normalizeMessage(String value) {
        // Based on MarkerInfo.checkValidAttribute
        if (value.length() < 21000) {
            return value;
        }

        try {
            if (value.getBytes("UTF-8").length > 65_535) { //$NON-NLS-1$
                return value.substring(0, 10000) + "..."; //$NON-NLS-1$
            }
        } catch (UnsupportedEncodingException uee) {
            // Unlikely, UTF-8 is requirement for Java.
        }

        return value;
    }

    @Override
    protected void info(
            String message, EObject source, EStructuralFeature feature, int index, String code,
            String... issueData) {
        getMessageAcceptor().acceptInfo(normalizeMessage(message), source, feature, index, code,
                issueData);
    }

    @Override
    protected void info(
            String message, EObject source, EStructuralFeature feature, String code,
            String... issueData) {
        getMessageAcceptor().acceptInfo(normalizeMessage(message), source, feature,
                ValidationMessageAcceptor.INSIGNIFICANT_INDEX, code, issueData);
    }

    @Override
    protected void error(
            String message, EObject source, EStructuralFeature feature, int index, String code,
            String... issueData) {
        getMessageAcceptor().acceptError(normalizeMessage(message), source, feature, index, code,
                issueData);
    }

    @Override
    protected void error(
            String message, EObject source, EStructuralFeature feature, String code,
            String... issueData) {
        getMessageAcceptor().acceptError(normalizeMessage(message), source, feature,
                ValidationMessageAcceptor.INSIGNIFICANT_INDEX, code, issueData);
    }

    @Override
    @Deprecated // Use same method with WarningType parameter
    protected void warning(
            String message, EObject source, EStructuralFeature feature, int index, String code,
            String... issueData) {
        super.warning(message, source, feature, index, code, issueData);
        throw new IllegalArgumentException(ILLEGAL_WARNING_CALL);
    }

    @Override
    @Deprecated // Use same method with WarningType parameter
    protected void warning(
            String message, EObject source, EStructuralFeature feature, String code,
            String... issueData) {
        super.warning(message, source, feature, code, issueData);
        throw new IllegalArgumentException(ILLEGAL_WARNING_CALL);
    }

    protected void warning(
            String message, EStructuralFeature feature, String code, WarningType warningType,
            String... issueData) {
        warning(message, getCurrentObject(), feature, ValidationMessageAcceptor.INSIGNIFICANT_INDEX,
                code, warningType, issueData);
    }

    protected void warning(
            String message, EStructuralFeature feature, int index, String code,
            WarningType warningType, String... issueData) {
        warning(message, getCurrentObject(), feature, index, code, warningType, issueData);
    }

    protected void warning(
            String message, EObject source, EStructuralFeature feature, int index, String code,
            WarningType warningType, String... issueData) {
        if (!isWarningSuppressed(source, warningType)) {
            getMessageAcceptor().acceptWarning(normalizeMessage(message), source, feature, index,
                    code, issueData);
        }
    }

    protected void warning(
            String message, EObject source, EStructuralFeature feature, String code,
            WarningType warningType, String... issueData) {
        if (!isWarningSuppressed(source, warningType)) {
            getMessageAcceptor().acceptWarning(normalizeMessage(message), source, feature,
                    ValidationMessageAcceptor.INSIGNIFICANT_INDEX, code, issueData);
        }
    }

    // -----------------SuppressWarning-----------------//

    private boolean isWarningSuppressed(EObject element, WarningType warningType) {
        if (!warningType.allowSuppressed) {
            return false;
        }
        EObject runningElement = element;
        while (runningElement != null && !(runningElement instanceof Annotable)) {
            runningElement = runningElement.eContainer();
        }
        if (runningElement == null) {
            return false;
        }
        String warningLabel = warningType.toString();
        for (Annotation annotation : ((Annotable) runningElement).getAnnotations()) {
            String name = annotation.getName();
            if (name.equalsIgnoreCase(ANNOTATION_SUPPRESSWARNINGS)) {
                for (Expression exp : annotation.getArguments()) {
                    if (exp instanceof StringConstant
                            && ((StringConstant) exp).getValue().equalsIgnoreCase(warningLabel)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
