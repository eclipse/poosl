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
import org.eclipse.poosl.sirius.services.ClassDiagramServices
import org.eclipse.poosl.sirius.services.CompositeStructureDiagramServices
import org.eclipse.poosl.xtext.GlobalConstants
import org.eclipse.sirius.viewpoint.description.Group
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
			modelFileExtension = GlobalConstants.FILE_EXTENSION
			
			use(CompositeStructureDiagramServices, ClassDiagramServices)
			
			ownedRepresentations += #[
				new ClusterDiagramDiagram(this),
				new ClassDiagramDiagram(this),
				new ReusedDiagramDiagram(this)
			].map[ createContent ]

		]
	}

}
