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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.poosl.AssignmentExpression;
import org.eclipse.poosl.OutputVariable;
import org.eclipse.poosl.VariableExpression;
import org.eclipse.xtext.resource.IEObjectDescription;

/**
 * The PooslSuperClassDescription.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
final class PooslSuperClassDescription {
    private static final String STR_SUPER_CLASS = "SuperClass"; //$NON-NLS-1$

    private static final String STR_USED_VARIABLES = "UsedVariables"; //$NON-NLS-1$

    private static final String SEPARATOR = ","; //$NON-NLS-1$

    private PooslSuperClassDescription() {
        throw new IllegalStateException("Utility class");
    }

    // --- Set -------

    public static void setSuperClass(Map<String, String> userData, String superClass) {
        if (superClass != null) {
            userData.put(STR_SUPER_CLASS, superClass);
        }
    }

    public static void setUsedVariables(Map<String, String> userData, Set<String> usedElements) {
        userData.put(STR_USED_VARIABLES, formatUsedElements(usedElements));
    }

    /**
     * Adds the name of object to usedElements if interested.
     * 
     * @param used
     *            container created with {@link #initUsedElements()}
     * @param local
     * @param object
     *            element to add to the usedelements
     */
    public static void addUsedVariables(Set<String> used, Set<String> local, EObject object) {
        if (object instanceof VariableExpression) {
            addVariable(used, local, ((VariableExpression) object).getVariable());
        } else if (object instanceof AssignmentExpression) {
            addVariable(used, local, ((AssignmentExpression) object).getVariable());
        } else if (object instanceof OutputVariable) {
            addVariable(used, local, ((OutputVariable) object).getVariable());
        }
    }

    private static void addVariable(Set<String> used, Set<String> local, String name) {
        if (name != null && !local.contains(name)) {
            used.add(name);
        }
    }

    public static String formatUsedElements(Set<String> usedElements) {
        StringBuilder builder = new StringBuilder();
        for (String string : usedElements) {
            builder.append(string).append(SEPARATOR);
        }
        return builder.toString();
    }

    // --- Get -------

    public static String getSuperClass(IEObjectDescription descr) {
        return descr.getUserData(STR_SUPER_CLASS);
    }

    public static List<String> getUsedVariables(IEObjectDescription descr) {
        return parseUsedElements(descr.getUserData(STR_USED_VARIABLES));
    }

    public static List<String> parseUsedElements(String usedString) {
        return parseUsedElements(usedString, SEPARATOR);
    }

    public static List<String> parseUsedElements(String usedString, String separator) {
        if (usedString != null) {
            String[] elements = usedString.split(separator);
            return Arrays.asList(elements);
        }
        return Collections.emptyList();
    }
}
