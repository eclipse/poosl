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
