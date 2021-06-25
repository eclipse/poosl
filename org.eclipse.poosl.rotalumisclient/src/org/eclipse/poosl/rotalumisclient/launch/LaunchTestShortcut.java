package org.eclipse.poosl.rotalumisclient.launch;

import org.eclipse.poosl.rotalumisclient.PooslConstants;

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
