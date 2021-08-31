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
package org.eclipse.poosl.rotalumisclient.launch;

import org.eclipse.poosl.rotalumisclient.PooslConstants;

/**
 * The LaunchTestShortcut.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class LaunchTestShortcut extends LaunchShortcut {

    @Override
    protected String getLaunchType() {
        return PooslConstants.CONFIGURATION_ATTRIBUTE_LAUNCH_TEST_TYPE;
    }

    @Override
    protected boolean isTestConfiguration() {
        return true;
    }
}
