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
package org.eclipse.poosl.xtext.helpers;

import java.text.MessageFormat;
import java.util.logging.Logger;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.xtext.resource.IEObjectDescription;

/**
 * The PooslValidationHelper.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class PooslValidationHelper {
    // DO_CHECK can be enabled for debugging purposes
    private static final boolean DO_CHECK = false;

    private static final String DESCRIPTION_SIMPLE_WARNING = "Description validation failed"; //$NON-NLS-1$

    private static final String DESCRIPTION_VALIDATION_WARNING = DESCRIPTION_SIMPLE_WARNING + ", description of {0} was not of the class {1}"; //$NON-NLS-1$

    private static final Logger LOGGER = Logger.getLogger(PooslValidationHelper.class.getName());

    private PooslValidationHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean checkValidity(IEObjectDescription descr, EClass... classes) {
        boolean isValid = true;
        if (DO_CHECK) {
            isValid = checkClass(descr.getEClass(), classes);
            if (!isValid) {
                LOGGER.warning(MessageFormat.format(DESCRIPTION_VALIDATION_WARNING, descr.getEClass().getName(), classesToString(classes)));
            }
            assert isValid : DESCRIPTION_SIMPLE_WARNING;
        }
        return isValid;
    }

    private static boolean checkClass(EClass eClass, EClass[] classes) {
        for (EClass validClass : classes) {
            if (eClass == validClass)
                return true;
        }
        return false;
    }

    private static String classesToString(EClass[] classes) {
        StringBuilder builder = new StringBuilder();
        for (EClass eClass : classes) {
            builder.append(eClass.getName() + " "); //$NON-NLS-1$
        }
        return builder.toString();
    }
}
