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

/**
 * The PooslIssueCodes.
 *
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class PooslIssueCodes {
    // Misc
    public static final String LOCATION_OF_RETURN_MISSING = "LOCATION_OF_RETURN_MISSING"; //$NON-NLS-1$

    public static final String LOCATION_OF_RETURN_SUPERFLUOUS = "LOCATION_OF_RETURN_SUPERFLUOUS"; //$NON-NLS-1$

    public static final String EQUALITY_INSTEAD_OF_ASSIGNMENT = "EQUALITY_INSTEAD_OF_ASSIGNMENT"; //$NON-NLS-1$

    public static final String MISSING_INSTANCE_PARAMETER = "MISSING_INSTANCE_PARAMETER"; //$NON-NLS-1$

    public static final String MULTIPLE_CHANNELS_CONNECTED_TO_INSTANCE_PORT = "MULTIPLE_CHANNELS_CONNECTED_TO_INSTANCE_PORT"; //$NON-NLS-1$

    public static final String MULTIPLE_CHANNELS_CONNECTED_TO_EXTERNAL_PORT = "MULTIPLE_CHANNELS_CONNECTED_TO_EXTERNAL_PORT"; //$NON-NLS-1$

    public static final String UNCONNECTED = "UNCONNECTED"; //$NON-NLS-1$

    public static final String UNCONNECTED_EXTERNAL_PORT = "UNCONNECTED_EXTERNAL_PORT"; //$NON-NLS-1$

    public static final String UNCONNECTED_CHANNEL = "UNCONNECTED_CHANNEL"; //$NON-NLS-1$

    public static final String INVALID_IMPORT_VALID_IMPORTLIB = "INVALID_IMPORT_VALID_IMPORTLIB"; //$NON-NLS-1$

    public static final String INVALID_IMPORTLIB_VALID_IMPORT = "INVALID_IMPORTLIB_VALID_IMPORT"; //$NON-NLS-1$

    public static final String UNCONNECTED_INTERNAL_CHANNEL = "UNCONNECTED_INTERNAL_CHANNEL"; //$NON-NLS-1$

    public static final String INIT_CALL_MISSING = "INIT_CALL_MISSING"; //$NON-NLS-1$

    // Types
    public static final String TYPECHECK = "TYPECHECK"; //$NON-NLS-1$

    public static final String MISSING_MESSAGE_DECLARATION = "MISSING_MESSAGE_DECLARATION"; //$NON-NLS-1$

    public static final String INVALID_TYPES_RECEIVE_STATEMENT = "INVALID_TYPES_RECEIVE_STATEMENT"; //$NON-NLS-1$

    public static final String INVALID_TYPES_SEND_STATEMENT = "INVALID_TYPES_SEND_STATEMENT"; //$NON-NLS-1$

    public static final String INVALID_INPUT_TYPES_PROCESS_METHOD_CALL = "INVALID_INPUT_TYPES_PROCESS_METHOD_CALL"; //$NON-NLS-1$

    public static final String INVALID_OUTPUT_TYPES_PROCESS_METHOD_CALL = "INVALID_OUTPUT_TYPES_PROCESS_METHOD_CALL"; //$NON-NLS-1$

    public static final String INVALID_INSTANCE_PARAMETER_TYPE = "INVALID_INSTANCE_PARAMETER_TYPE"; //$NON-NLS-1$

    public static final String INVALID_RETURN_TYPE = "INVALID_RETURN_TYPE"; //$NON-NLS-1$

    public static final String INVALID_OVERRIDE_RETURN_TYPE = "INVALID_OVERRIDE_RETURN_TYPE"; //$NON-NLS-1$

    public static final String INVALID_OVERRIDE_PARAMETER_TYPE = "INVALID_OVERRIDE_PARAMETER_TYPE"; //$NON-NLS-1$

    public static final String INVALID_OVERRIDE_INPUT_PARAMETER_TYPE = "INVALID_OVERRIDE_INPUT_PARAMETER_TYPE"; //$NON-NLS-1$

    public static final String INVALID_OVERRIDE_OUTPUT_PARAMETER_TYPE = "INVALID_OVERRIDE_OUTPUT_PARAMETER_TYPE"; //$NON-NLS-1$

    public static final String INVALID_ASSIGNMENT_TYPE = "INVALID_ASSIGNMENT_TYPE"; //$NON-NLS-1$

    public static final String INCOMPATIBLE_TYPE = "INCOMPATIBLE_TYPE"; //$NON-NLS-1$

    // Unique Names
    public static final String DUPLICATE_CLASS_NAME = "DUPLICATE_CLASS_NAME"; //$NON-NLS-1$

    public static final String DUPLICATE_METHOD_NAME = "DUPLICATE_METHOD_NAME"; //$NON-NLS-1$

    // Unused elements
    public static final String UNUSED_PROCESS_PORT = "UNUSED_PROCESS_PORT"; //$NON-NLS-1$

    public static final String ERROR_UNUSED_CHANNEL = "UNUSED_CHANNEL"; //$NON-NLS-1$

    public static final String WARNING_UNUSED_CHANNEL = "WARNING_UNUSED_CHANNEL"; //$NON-NLS-1$

    public static final String UNUSED_MESSAGE_SIGNATURE = "UNUSED_MESSAGE_SIGNATURE"; //$NON-NLS-1$

    public static final String UNUSED_PROCESS_METHOD = "UNUSED_PROCESS_METHOD"; //$NON-NLS-1$

    public static final String UNUSED_VARIABLE = "UNUSED_VARIABLE"; //$NON-NLS-1$

    // Unresolved
    public static final String UNDECLARED_DATA_METHOD_NAMED = "UNDECLARED_DATA_METHOD_NAMED"; //$NON-NLS-1$

    public static final String UNDECLARED_DATA_METHOD_BINARY = "UNDECLARED_DATA_METHOD_BINARY"; //$NON-NLS-1$

    public static final String UNDECLARED_DATA_METHOD_UNARY = "UNDECLARED_DATA_METHOD_UNARY"; //$NON-NLS-1$

    // Grammar
    public static final String UNKNOWN_ANNOTATION = "UNKNOWN_ANNOTATION"; //$NON-NLS-1$

    private PooslIssueCodes() { // Utility class
    }
}
