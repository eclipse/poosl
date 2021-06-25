package nl.esi.poosl.rotalumisclient.launch;

import nl.esi.poosl.rotalumisclient.PooslConstants;

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
