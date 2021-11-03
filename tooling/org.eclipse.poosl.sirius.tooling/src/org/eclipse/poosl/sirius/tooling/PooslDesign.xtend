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
package org.eclipse.poosl.sirius.tooling

import org.eclipse.poosl.PooslPackage
import org.eclipse.poosl.sirius.services.AbstractServices
import org.eclipse.poosl.sirius.services.ClassDiagramServices
import org.eclipse.poosl.sirius.services.CompositeStructureDiagramServices
import org.eclipse.poosl.sirius.services.CompositeStructureEditServices
import org.eclipse.poosl.xtext.GlobalConstants
import org.eclipse.sirius.viewpoint.description.Group
import org.eclipse.sirius.viewpoint.description.IdentifiedElement
import org.eclipse.sirius.viewpoint.description.UserColorsPalette
import org.eclipse.sirius.viewpoint.description.Viewpoint
import org.mypsycho.modit.emf.sirius.api.AbstractGroup

class PooslDesign extends AbstractGroup {

	new () {
        businessPackages += PooslPackage.eINSTANCE
	}

	override initContent(Group it) {
		name = "poosl"
		version = "10.1.0.201507271600"
		ownedViewpoints += Viewpoint.create [
			name = "POOSL viewpoint"
			i18n("name")
			//label = value
			modelFileExtension = GlobalConstants.FILE_EXTENSION
			
			use(AbstractServices)
			use(CompositeStructureDiagramServices)
			use(CompositeStructureEditServices)
			use(ClassDiagramServices)
			
			ownedRepresentations += #[
				new ClusterDiagramDiagram(this),
				new ClassDiagramDiagram(this),
				new ReusedDiagramDiagram(this)
			].map[ createContent ]

			
		]
		userColorsPalettes += UserColorsPalette.create[
			name = "PooslColors"
			// entries += "ProcessBkg".color(150,177,218) // blue
			entries += "ProcessBkg".color(195,230,255) // blue min
			entries += "ProcessBorder".color(74,74,151) // blue
			entries += "ClusterBkg".color(255,252,183) // yellow
			entries += "ClusterBorder".color(123,105,79) // brown
			entries += "DataBkg".color(232,224,210) // Brown
			entries += "DataBorder".color(123,105,79) // brown
			
			// entries += "SystemBg".color(150,177,218) // System is grey
		]
		
	}

	protected def <R extends IdentifiedElement> R i18n(R it, String key) {
		// ':' is key separator in properties
		// '%' is ODesign convention for substitution
		label = "%VP_" + key.replace(":", "^")
		it
	}
	
	override getContentAlias(Class<?> context) {
		// Remove annoying double name
		val result = super.getContentAlias(context)
		if (result.endsWith("DiagramDiagram")) 
			result.substring(0, result.length - 7)
		else 
			result
	}
	

}
