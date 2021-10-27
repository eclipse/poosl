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
package org.eclipse.poosl.xtext.descriptions;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.poosl.PooslPackage.Literals;
import org.eclipse.poosl.xtext.helpers.PooslValidationHelper;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.common.base.Predicate;

/**
 * The PooslPortDescription.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class PooslPortDescription {
    private static final String STR_CLASS = "Class"; //$NON-NLS-1$

    private PooslPortDescription() {
        throw new IllegalStateException("Utility class");
    }

    // --- Set -------

    public static Map<String, String> createUserData(String className) {
        return Collections.singletonMap(STR_CLASS, className);
    }

    // --- Get -------

    public static String getClassName(IEObjectDescription descr) {
        return checkValidity(descr) ? descr.getUserData(STR_CLASS) : null;
    }

    private static boolean checkValidity(IEObjectDescription descr) {
        return PooslValidationHelper.checkValidity(descr, Literals.PORT);
    }

    // --- Predicates -------

    public static Predicate<IEObjectDescription> predicatePort(final List<String> classes) {
        return input -> {
            String cName = getClassName(input);
            return cName != null && classes.contains(cName);
        };
    }

    public static Predicate<IEObjectDescription> predicatePort(final String className) {
        return input -> {
            String cName = getClassName(input);
            return cName != null && cName.equals(className);
        };
    }
}
