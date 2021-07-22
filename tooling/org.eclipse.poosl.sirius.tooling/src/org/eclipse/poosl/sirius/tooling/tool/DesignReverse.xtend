package org.eclipse.poosl.sirius.tooling.tool

import java.nio.file.Paths
import org.eclipse.poosl.sirius.Activator
import org.junit.Test
import org.mypsycho.modit.emf.sirius.tool.SiriusReverseIt

/**
 * Tool to reverse Sirius design model from 'com.huawei.sirius.autosar.design' plugin.
 * <p>
 * Reverse is required when model is modified directly to compare difference with
 * generated model.
 * </p>
 *
 * @author nperansin
 *
 */
class DesignReverse extends DesignToolBase {
		
	protected static val REVERSE_PATH = "target/rvs"
			
	@Test
	def void reverseModel() {
		new SiriusReverseIt(
			Activator.DESIGN_PATH, 
			Paths.get(REVERSE_PATH), 
			TARGET_CLASS.name
		).perform
	}
	
}
