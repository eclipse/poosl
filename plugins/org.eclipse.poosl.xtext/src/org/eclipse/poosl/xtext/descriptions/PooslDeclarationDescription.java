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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.poosl.PooslPackage.Literals;
import org.eclipse.poosl.xtext.helpers.PooslValidationHelper;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.common.base.Predicate;

/**
 * The PooslDeclarationDescription.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class PooslDeclarationDescription {

    private static final String STR_CLASS = "Class"; //$NON-NLS-1$

    private static final String STR_IS_FROM_DATA_CLASS = "IsFromDataClass"; //$NON-NLS-1$

    private static final String STR_TYPE = "Type"; //$NON-NLS-1$

    private static final String STR_IS_PARAMETER = "IsParameter"; //$NON-NLS-1$

    private PooslDeclarationDescription() {
        throw new IllegalStateException("Utility class");
    }

    // --- Set -------

    public static Map<String, String> createUserData(
            String type, String className, boolean isFromDataClass, boolean isParameter) {
        Map<String, String> userData = new HashMap<>();
        userData.put(STR_TYPE, type);
        userData.put(STR_CLASS, className);
        if (isFromDataClass) {
            userData.put(STR_IS_FROM_DATA_CLASS, STR_IS_FROM_DATA_CLASS);
        }
        if (isParameter) {
            userData.put(STR_IS_PARAMETER, STR_IS_PARAMETER);
        }
        return userData;
    }

    // --- Get -------

    public static String getType(IEObjectDescription descr) {
        if (!checkValidity(descr))
            return null;

        return descr.getUserData(STR_TYPE);
    }

    public static String getClassName(IEObjectDescription descr) {
        if (!checkValidity(descr))
            return null;

        return descr.getUserData(STR_CLASS);
    }

    public static boolean isFromDataClass(IEObjectDescription descr) {
        if (!checkValidity(descr))
            return false;

        return STR_IS_FROM_DATA_CLASS.equals(descr.getUserData(STR_IS_FROM_DATA_CLASS));
    }

    public static boolean isParameter(IEObjectDescription descr) {
        if (!checkValidity(descr))
            return false;

        return STR_IS_PARAMETER.equals(descr.getUserData(STR_IS_PARAMETER));
    }

    private static boolean checkValidity(IEObjectDescription descr) {
        return PooslValidationHelper.checkValidity(descr, Literals.VARIABLE);
    }

    // --- Predicates -------

    public static Predicate<IEObjectDescription> predicateParameterFromNonDataClass(
            final String className) {
        return new Predicate<IEObjectDescription>() {
            @Override
            public boolean apply(IEObjectDescription input) {
                String cName = getClassName(input);
                return cName != null && cName.equals(className) && !isFromDataClass(input)
                        && isParameter(input);
            }
        };
    }

    public static Predicate<IEObjectDescription> predicateParameterFromNonDataClass(
            final List<String> classes) {
        return new Predicate<IEObjectDescription>() {
            @Override
            public boolean apply(IEObjectDescription input) {
                String cName = getClassName(input);
                return cName != null && classes.contains(cName) && !isFromDataClass(input)
                        && isParameter(input);
            }
        };
    }

    public static Predicate<IEObjectDescription> predicateParameterAndVariableFromNonDataClass(
            final List<String> classes) {
        return new Predicate<IEObjectDescription>() {
            @Override
            public boolean apply(IEObjectDescription input) {
                String cName = getClassName(input);
                return cName != null && classes.contains(cName) && !isFromDataClass(input);
            }
        };
    }

    public static Predicate<IEObjectDescription> predicateVariableFromDataClass(
            final List<String> classes) {
        return new Predicate<IEObjectDescription>() {
            @Override
            public boolean apply(IEObjectDescription input) {
                String cName = getClassName(input);
                return cName != null && classes.contains(cName) && isFromDataClass(input);
            }
        };
    }
}
