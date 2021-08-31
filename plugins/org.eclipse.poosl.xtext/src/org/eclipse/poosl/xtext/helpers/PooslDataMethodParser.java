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
package org.eclipse.poosl.xtext.helpers;

import org.eclipse.poosl.xtext.custom.FormattingHelper;
import org.eclipse.poosl.xtext.descriptions.PooslDataMethodDescription;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

/**
 * The PooslDataMethodParser.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class PooslDataMethodParser {

    private static final String SEPARATOR = "-"; //$NON-NLS-1$

    private PooslDataMethodParser() {
        throw new IllegalStateException("Utility class");
    }

    public static Iterable<String> getDataMethodStrings(Iterable<IEObjectDescription> descriptions) {
        return Iterables.transform(descriptions, new Function<IEObjectDescription, String>() {
            public String apply(IEObjectDescription descr) {
                return HelperFunctions.getName(descr) + SEPARATOR + PooslDataMethodDescription.getParameters(descr) + SEPARATOR + ": " + PooslDataMethodDescription.getReturnType(descr); //$NON-NLS-1$
            }
        });
    }

    public static String getMethodName(String dataMethodString) {
        return dataMethodString.split(SEPARATOR)[0];
    }

    public static String getDeclarationString(String dataMethodString) {
        return dataMethodString.replace(SEPARATOR, ""); //$NON-NLS-1$
    }

    public static Integer getNumberArgs(String dataMethodString) {
        return FormattingHelper.unformatDeclarationsToTypeNames(dataMethodString.split(SEPARATOR)[1]).size();
    }
}
