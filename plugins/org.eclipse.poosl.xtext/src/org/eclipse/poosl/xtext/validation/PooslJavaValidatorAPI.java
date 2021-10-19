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

import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.poosl.DataClass;
import org.eclipse.poosl.Poosl;
import org.eclipse.poosl.PooslPackage.Literals;
import org.eclipse.poosl.xtext.custom.PooslCache;
import org.eclipse.poosl.xtext.helpers.HelperFunctions;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckType;

/**
 * The PooslJavaValidatorAPI.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslJavaValidatorAPI extends PooslJavaValidatorGrammar {
    private static final String ERROR_MESSAGE_NOT_EXTENDABLE = "Data class %s cannot be extended as super class";

    private static final String ERROR_MESSAGE_MISSING_PERMANENT_CLASS = "Permanent data class %s is missing (can be fixed in Preferences -> Poosl -> Basic Classes)";

    @Check(CheckType.FAST)
    public void checkPermanentDataClasses(Poosl poosl) {
        PERF.benchmark("checkPermanentDataClasses", () -> { //$NON-NLS-1$
            Resource resource = poosl.eResource();
            Map<String, IEObjectDescription> allDataClasses = PooslCache.get(resource)
                    .getDataClassMap();
            for (String dClass : HelperFunctions.PERMANENT_DATA_CLASSES) {
                if (!allDataClasses.containsKey(dClass)) {
                    error(String.format(ERROR_MESSAGE_MISSING_PERMANENT_CLASS, dClass), null);
                }
            }
        });
    }

    @Check(CheckType.FAST)
    public void checkExtends(DataClass dClass) {
        String superClass = dClass.getSuperClass();

        if (HelperFunctions.PRIMITIVE_DATA_CLASSES.contains(superClass)) {
            error(String.format(ERROR_MESSAGE_NOT_EXTENDABLE, superClass),
                    Literals.DATA_CLASS__SUPER_CLASS);
        }
    }
}
