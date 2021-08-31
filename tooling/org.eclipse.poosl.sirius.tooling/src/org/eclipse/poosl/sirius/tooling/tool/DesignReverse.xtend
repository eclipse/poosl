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
