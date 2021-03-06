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

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Platform;
import org.eclipse.poosl.ProcessClass;
import org.eclipse.poosl.ProcessMethod;
import org.eclipse.poosl.ProcessMethodCall;
import org.eclipse.poosl.xtext.custom.PooslCache;
import org.eclipse.xtext.resource.IEObjectDescription;

/**
 * The PooslProcessMethodParser.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslProcessMethodParser {

    private static final ILog LOGGER = Platform.getLog(PooslMessageSignatureCallHelper.class);

    private static final String PARSER_METHOD_PARAM_SEPARATOR = "|"; //$NON-NLS-1$

    private String calledMethod;

    private int numberOfInputs;

    private int numberOfOutputs;

    public PooslProcessMethodParser(String stringDescription) {
        String[] properties = stringDescription.split("\\" + PARSER_METHOD_PARAM_SEPARATOR); //$NON-NLS-1$
        if (properties.length == 3) {
            this.calledMethod = properties[0];
            this.numberOfInputs = Integer.parseInt(properties[1]);
            this.numberOfOutputs = Integer.parseInt(properties[2]);
        } else {
            LOGGER.warn(getClass().getName() + ":" + stringDescription); //$NON-NLS-1$
        }
    }

    public PooslProcessMethodParser(ProcessMethodCall statement) {
        this.calledMethod = statement.getMethod();
        this.numberOfInputs = statement.getInputArguments().size();
        this.numberOfOutputs = statement.getOutputVariables().size();
    }

    private static String getID(String name, int input, int output) {
        return name + PARSER_METHOD_PARAM_SEPARATOR + String.valueOf(input)
                + PARSER_METHOD_PARAM_SEPARATOR + String.valueOf(output);
    }

    public static String getProcessMethodID(ProcessMethodCall methodCall) {
        if (methodCall != null) {
            return getID(methodCall.getMethod(), methodCall.getInputArguments().size(),
                    methodCall.getOutputVariables().size());
        }
        return null;
    }

    public static String getProcessMethodID(ProcessMethod pMethod) {
        return getID(pMethod.getName(),
                HelperFunctions.computeNumberOfVariables(pMethod.getInputParameters()),
                HelperFunctions.computeNumberOfVariables(pMethod.getOutputParameters()));
    }

    public static String getProcessMethodIDWithClassName(ProcessMethod pMethod) {
        ProcessClass pClass = (ProcessClass) pMethod.eContainer();
        return pClass.getName() + ":" + getProcessMethodID(pMethod); //$NON-NLS-1$
    }

    public IEObjectDescription getCalledMethod(ProcessClass pClass) {
        return PooslCache.get(pClass.eResource())
                .getProcessMethods(pClass.getName(), numberOfInputs, numberOfOutputs)
                .get(calledMethod);
    }

    @Override
    public String toString() {
        return getID(calledMethod, numberOfInputs, numberOfOutputs);
    }

    public String getCalledMethod() {
        return calledMethod;
    }

    public int getNumberOfInputs() {
        return numberOfInputs;
    }

    public int getNumberOfOutputs() {
        return numberOfOutputs;
    }
}
