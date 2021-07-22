package org.eclipse.poosl.sirius.tooling.tool

import java.nio.file.Paths
import org.eclipse.poosl.sirius.Activator
import org.eclipse.poosl.sirius.tooling.PooslDesign

/**
 * Common elements for tool of dynamic Design model of '*.sirius' plugin.
 * 
 * @author Obeo
 */
class DesignToolBase {

	
	// Specific
	protected static val PLUGINS_PATH = "../../plugins"
	
	protected static val TARGET_CLASS = PooslDesign
	
	// Derived
	protected static val ODESIGN_FILE = Paths.get(PLUGINS_PATH)
			// /!\ File path matches java bundle path.
        	.resolve(Activator.DESIGN_PATH)
        	.toAbsolutePath


}
