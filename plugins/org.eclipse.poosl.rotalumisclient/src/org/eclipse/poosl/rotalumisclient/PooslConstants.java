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
package org.eclipse.poosl.rotalumisclient;

/**
 * The PooslConstants.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class PooslConstants {
    // Debug model static
    public static final String DEBUG_MODEL_ID = "org.eclipse.poosl.rotalumisclient.DebugModelPresentation"; //$NON-NLS-1$

    public static final String PLUGIN_ID = "org.eclipse.poosl.rotalumisclient"; //$NON-NLS-1$

    public static final String PLUGIN_ID_ROTALUMIS_EXECUTABLES = "nl.tue.rotalumis.executables"; //$NON-NLS-1$

    public static final String POOSL_EXTENSION = "poosl"; //$NON-NLS-1$

    public static final String EXTERN_CONFIG_EXTENSION = "ini"; //$NON-NLS-1$

    // View ID statics
    public static final String ID_POOSL_SEQUENCEDIAGRAMVIEW = "org.eclipse.poosl.views.sequencediagramview"; //$NON-NLS-1$

    public static final String ID_POOSL_VARIABLESVIEW = "org.eclipse.poosl.views.variablesview"; //$NON-NLS-1$

    public static final String ID_POOSL_PETVIEW = "org.eclipse.poosl.views.petview"; //$NON-NLS-1$

    public static final String ID_POOSL_DEBUGVIEW = "org.eclipse.poosl.views.debugview"; //$NON-NLS-1$

    public static final String ID_POOSL_VARIABLESVIEW_DETAIL_PANE = "org.eclipse.poosl.rotalumisclient.PooslVariableDetailPane"; //$NON-NLS-1$

    public static final String ID_POOSL_STACKTRACEVIEW = "org.eclipse.poosl.views.stacktraceview"; //$NON-NLS-1$

    public static final String ID_POOSL_STACKTRACEVARIABLEVIEW = "org.eclipse.poosl.views.stacktracevariableview"; //$NON-NLS-1$

    // Perspective ID statics
    public static final String ID_POOSL_EDIT_PERSPECTIVE = "org.eclipse.poosl.normalperspective"; //$NON-NLS-1$

    public static final String ID_POOSL_DEBUG_PERSPECTIVE = "org.eclipse.poosl.debugperspective"; //$NON-NLS-1$

    // Debug event statics
    public static final int INSPECT_REQUEST = 1234;

    public static final int BREAKPOINT_HIT = 2345;

    public static final int INSPECT_RECEIVED = 3456;

    public static final int PERFORM_TRANSITION = 4567;

    public static final int COMM_EVENTS_CHANGE = 5678;

    public static final int CLEAR_COMM_EVENTS = 6789;

    public static final int ERROR_STATE = 7890;

    public static final int STOPPED_STATE = 8901;

    public static final int ENGINE_ERROR = 9012;

    public static final int STACKFRAME_INSPECT = 0123;

    // Configuration statics
    public static final String CONFIGURATION_ATTRIBUTE_MODEL_PATH = "modelpath"; //$NON-NLS-1$

    public static final String CONFIGURATION_ATTRIBUTE_EXTERNAL_CONFIG_PATH = "externpath"; //$NON-NLS-1$

    public static final String CONFIGURATION_ATTRIBUTE_PROJECT = "projectname"; //$NON-NLS-1$

    public static final String CONFIGURATION_ATTRIBUTE_SERVER_IP = "127.0.0.1"; //$NON-NLS-1$

    public static final String CONFIGURATION_ATTRIBUTE_SERVER_PORT = "serverport"; //$NON-NLS-1$

    public static final String CONFIGURATION_ATTRIBUTE_DEFAULT_SERVER_PORT = "10001"; //$NON-NLS-1$

    public static final String CONFIGURATION_ATTRIBUTE_MAX_STACKSIZE = "maxStackSize"; //$NON-NLS-1$

    public static final String CONFIGURATION_ATTRIBUTE_DEFAULT_MAX_STACKSIZE_BYTES = "8388608"; //$NON-NLS-1$

    public static final String CONFIGURATION_ATTRIBUTE_RELATIVE_PATH = "relativeModelPath"; //$NON-NLS-1$

    public static final String CONFIGURATION_ATTRIBUTE_IS_RANDOM_SEED = "israndomseed"; //$NON-NLS-1$

    public static final boolean CONFIGURATION_ATTRIBUTE_DEFAULT_IS_RANDOM_SEED = false;

    public static final String CONFIGURATION_ATTRIBUTE_SEED = "simulationseed"; //$NON-NLS-1$

    public static final String CONFIGURATION_ATTRIBUTE_DEFAULT_SEED = "1"; //$NON-NLS-1$

    public static final String CONFIGURATION_ATTRIBUTE_IS_QUIET = "isquiet"; //$NON-NLS-1$

    public static final boolean CONFIGURATION_ATTRIBUTE_DEFAULT_IS_QUIET = false;

    public static final String CONFIGURATION_ATTRIBUTE_SEQUENCE_DIAGRAM_FILTER_VISIBLE = "visiblefilter"; //$NON-NLS-1$

    public static final String CONFIGURATION_ATTRIBUTE_SEQUENCE_DIAGRAM_FILTER_INVISIBLE = "invisiblefilter"; //$NON-NLS-1$

    public static final String CONFIGURATION_ATTRIBUTE_SEQUENCE_DIAGRAM_ORDER = "messageorder"; //$NON-NLS-1$

    public static final String CONFIGURATION_ATTRIBUTE_SEQUENCE_DIAGRAM_FILTER_SETTING = "filtersetting"; //$NON-NLS-1$

    public static final String CONFIGURATION_ATTRIBUTE_SIMULATOR_INOUT_FOLDER = "simulator"; //$NON-NLS-1$

    public static final String CONFIGURATION_ATTRIBUTE_LAUNCH_TYPE = "org.eclipse.poosl.rotalumisclient.PooslLaunchConfiguration"; //$NON-NLS-1$

    public static final String CONFIGURATION_ATTRIBUTE_LAUNCH_TEST_TYPE = "org.eclipse.poosl.rotalumisclient.PooslTestLaunchConfiguration"; //$NON-NLS-1$

    public static final String CONFIGURATION_ATTRIBUTE_WORKSPACE_MODEL = "workspaceModel"; //$NON-NLS-1$

    public static final String CONFIGURATION_ATTRIBUTE_TEST_CONF = "poosl.launch.test"; //$NON-NLS-1$

    // Command statics
    public static final String COMMAND_CLEAR_LAUNCHES = "org.eclipse.poosl.rotalumisclient.views.debug.clearlaunches"; //$NON-NLS-1$

    public static final String COMMAND_CLOSE_THREAD_WINDOWS = "org.eclipse.poosl.rotalumisclient.views.debug.closethreadwindows"; //$NON-NLS-1$

    public static final String COMMAND_STEPTYPE = "org.eclipse.poosl.rotalumisclient.stepcommand.steptype"; //$NON-NLS-1$

    public static final String COMMAND_STEPTYPE_STEP = "step"; //$NON-NLS-1$

    public static final String COMMAND_STEPTYPE_TIME_STEP = "timestep"; //$NON-NLS-1$

    public static final String COMMAND_STEPTYPE_COMMUNICATION_STEP = "commstep"; //$NON-NLS-1$

    public static final String COMMAND_DIAGRAM_VIEW = "org.eclipse.poosl.rotalumisclient.views.diagram.viewaction"; //$NON-NLS-1$

    public static final String COMMAND_DIAGRAM_VIEW_CLEAR_VIEW = "clearview"; //$NON-NLS-1$

    public static final String COMMAND_DIAGRAM_VIEW_SCROLL_LOCK = "scrolllock"; //$NON-NLS-1$

    public static final String COMMAND_DIAGRAM_VIEW_SETTINGS = "settings"; //$NON-NLS-1$

    public static final String COMMAND_DIAGRAM_SETUP = "org.eclipse.poosl.rotalumisclient.views.diagram.setupaction"; //$NON-NLS-1$

    // Preferences statics
    public static final String PREFERENCES_LOG_LEVEL = "LOG_LEVEL"; //$NON-NLS-1$

    public static final String PREFERENCES_MESSAGE_BUFFER_SIZE = "MESSAGE_BUFFER_SIZE"; //$NON-NLS-1$

    public static final int DEFAULT_MESSAGE_BUFFER_SIZE = 1000;

    public static final String DEFAULT_LOG_LEVEL = "OFF"; //$NON-NLS-1$

    public static final String PLATFORM_ENCODING = "file.encoding"; //$NON-NLS-1$

    // Thread window
    public static final String THREAD_VIEW_ID = "THREAD_WINDOW"; //$NON-NLS-1$

    public static final String PATH_SEPARATOR = "/"; //$NON-NLS-1$

    private PooslConstants() {
        throw new IllegalStateException("Utility class");
    }
}
