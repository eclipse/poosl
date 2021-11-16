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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.poosl.MessageParameter;
import org.eclipse.poosl.MessageSignature;
import org.eclipse.poosl.PooslPackage.Literals;
import org.eclipse.poosl.xtext.custom.PooslMessageType;
import org.eclipse.poosl.xtext.helpers.HelperFunctions;
import org.eclipse.poosl.xtext.helpers.PooslValidationHelper;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * The PooslMessageSignatureDescription.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class PooslMessageSignatureDescription {
    private static final String STR_PORT = "Port"; //$NON-NLS-1$

    private static final String STR_CLASS = "Class"; //$NON-NLS-1$

    private static final String STR_TYPE = "Type"; //$NON-NLS-1$

    private static final String STR_MESSAGETYPE_RECEIVE = "Receive"; //$NON-NLS-1$

    private static final String STR_MESSAGETYPE_SEND = "Send"; //$NON-NLS-1$

    private static final String STR_PARAMETERS = "Parameters"; //$NON-NLS-1$

    private PooslMessageSignatureDescription() {
        throw new IllegalStateException("Utility class");
    }

    // --- Set -------

    public static Map<String, String> createUserData(String className, MessageSignature message) {
        Map<String, String> userData = new HashMap<>();
        userData.put(STR_CLASS, className);
        userData.put(STR_PORT, message.getPort().getPort());
        userData.put(STR_PARAMETERS, parametersToString(message.getParameters()));
        if (message.eContainingFeature() == Literals.PROCESS_CLASS__RECEIVE_MESSAGES) {
            userData.put(STR_TYPE, STR_MESSAGETYPE_RECEIVE);
        }
        if (message.eContainingFeature() == Literals.PROCESS_CLASS__SEND_MESSAGES) {
            userData.put(STR_TYPE, STR_MESSAGETYPE_SEND);
        }
        return userData;
    }

    private static String parametersToString(List<MessageParameter> parameters) {
        StringBuilder stringBuilder = new StringBuilder();
        for (MessageParameter msgParameter : parameters) {
            stringBuilder.append(msgParameter.getType()).append(","); //$NON-NLS-1$
        }
        return stringBuilder.toString();
    }

    // --- Get -------

    public static String getClassName(IEObjectDescription descr) {
        if (!checkValidity(descr))
            return null;

        return descr.getUserData(STR_CLASS);
    }

    public static String getPort(IEObjectDescription descr) {
        if (!checkValidity(descr))
            return null;

        return descr.getUserData(STR_PORT);
    }

    public static List<String> getParameterTypes(IEObjectDescription descr) {
        if (!checkValidity(descr))
            return Collections.emptyList();

        String stringDescription = descr.getUserData(STR_PARAMETERS);
        if (!stringDescription.isEmpty()) {
            return Arrays.asList(stringDescription.split(",")); //$NON-NLS-1$
        } else {
            return Collections.emptyList();
        }
    }

    public static PooslMessageType getMessageType(IEObjectDescription descr) {
        if (!checkValidity(descr))
            return PooslMessageType.RECEIVE;

        if (STR_MESSAGETYPE_RECEIVE.equals(descr.getUserData(STR_TYPE))) {
            return PooslMessageType.RECEIVE;
        } else {
            return PooslMessageType.SEND;
        }
    }

    private static boolean checkValidity(IEObjectDescription descr) {
        return PooslValidationHelper.checkValidity(descr, Literals.MESSAGE_SIGNATURE);
    }

    // --- Predicates -------

    public static Predicate<IEObjectDescription> predicateMessage(final Iterable<String> classes) {
        return new Predicate<IEObjectDescription>() {
            @Override
            public boolean apply(IEObjectDescription input) {
                String cName = getClassName(input);
                return cName != null && Iterables.contains(classes, cName);
            }
        };
    }

    public static Predicate<IEObjectDescription> predicateMessage(final String portName) {
        return new Predicate<IEObjectDescription>() {
            @Override
            public boolean apply(IEObjectDescription input) {
                return portName.equals(getPort(input));
            }
        };
    }

    public static Predicate<IEObjectDescription> predicateMessage(
            final String portName, final String messageName) {
        return new Predicate<IEObjectDescription>() {
            @Override
            public boolean apply(IEObjectDescription input) {
                return portName.equals(getPort(input))
                        && messageName.equals(HelperFunctions.getName(input));
            }
        };
    }

    public static Predicate<IEObjectDescription> predicateMessage(
            final String portName, final int vars, final URI excludedObjectURI) {
        return new Predicate<IEObjectDescription>() {
            @Override
            public boolean apply(IEObjectDescription input) {
                return portName.equals(getPort(input)) && vars == getParameterTypes(input).size()
                        && !input.getEObjectURI().equals(excludedObjectURI);
            }
        };
    }
}
