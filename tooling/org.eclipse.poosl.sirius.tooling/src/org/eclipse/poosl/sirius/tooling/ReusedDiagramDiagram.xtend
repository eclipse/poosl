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

import org.eclipse.poosl.Poosl
import org.eclipse.sirius.diagram.description.DiagramDescription
import org.eclipse.sirius.diagram.description.Layer
import org.eclipse.sirius.diagram.description.tool.DeleteElementDescription
import org.eclipse.sirius.diagram.description.tool.ToolSection
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance
import org.eclipse.sirius.viewpoint.description.tool.DropContainerVariable
import org.eclipse.sirius.viewpoint.description.tool.ElementDeleteVariable
import org.eclipse.sirius.viewpoint.description.tool.ElementVariable
import org.eclipse.sirius.viewpoint.description.tool.ElementViewVariable
import org.eclipse.sirius.viewpoint.description.tool.OperationAction
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription

import static extension org.mypsycho.modit.emf.sirius.api.SiriusDesigns.*

class ReusedDiagramDiagram extends PooslDiagram {

	new(PooslDesign parent) {
		super(parent, "Reused diagram", Poosl)
	}
	
	override initContent(DiagramDescription it) {
		super.initContent(it)
		name = "Reused diagram"
		preconditionExpression = "false" // This diagram only contain reusable tools.
	}
	
	override initContent(Layer it) {

		toolSections += ToolSection.createAs(Ns.operation, "reuses") [
			ownedTools += DeleteElementDescription.createAs(Ns.del, "pooslElement") [
				precondition = "true"
				forceRefresh = true
				element = ElementDeleteVariable.named("element")
				elementView = ElementDeleteVariable.named("elementView")
				containerView = ContainerViewVariable.named("containerView")
				operation = '''element.deletePooslObject(elementView)'''.trimAql.toOperation
			]
			ownedTools += OperationAction.createAs(Ns.operation, "openText") [
				precondition = '''self.showMenuOpenTextualEditor()'''.trimAql
				view = ContainerViewVariable.named("views")
				operation = "OpenTextualEditor".callJavaAction("opentextualeditor", 
					"view" -> "[views/]"
				)
			]
			ownedTools += OperationAction.createAs(Ns.operation, "openClassDiagram") [
				precondition = "true"
				view = ContainerViewVariable.named("views")
				operation = "OpenClassDiagram".callJavaAction("openclassdiagram", 
					"view" -> "[views/]"
				)
			]
			ownedTools += PasteDescription.createAs(Ns.operation, "copyInstance") [
				forceRefresh = true
				container = DropContainerVariable.named("container")
				containerView = ContainerViewVariable.named("containerView")
				copiedView = ElementViewVariable.named("copiedView")
				copiedElement = ElementVariable.named("copiedElement")

				operation = "var:container".toOperation.andThen [
					subModelOperations += CreateInstance.create [
						typeName = "poosl.Instance"
						referenceName = "instances"
						setValue("name", "container.getUniqueDataName(copiedElement.name)")
						setValue("classDefinition", "copiedElement.classDefinition")
						setValue("instanceParameters", "copiedElement.instanceParameters")
					]
//					subModelOperations += ExternalJavaAction.createAs(Ns.operation, "Save") [
//						id = "externalcall"
//						parameters += ExternalJavaActionParameter.create [
//							name = "container"
//							value = "[container/]"
//						]
//						parameters += ExternalJavaActionParameter.create [
//							name = "action"
//							value = "save"
//						]
//					]
					
					subModelOperations += Ns.operation.id("Save").alias(
						"save".callJavaAction("save", 
							"view" -> "[views/]"
						))
				]
			]
			ownedTools += OperationAction.createAs(Ns.operation, "toggleSimComm") [
				precondition = "true"
				forceRefresh = true
				view = ContainerViewVariable.named("views")
				operation = "toggle debugging communication visibility".callJavaAction("showhidedebug", 
					"view" -> "[views/]"
				)
			]
			ownedTools += OperationAction.createAs(Ns.operation, "openStructureDiagram") [
				precondition = '''self.showMenuInstanceOpenStructureDiagram()'''.trimAql
				view = ContainerViewVariable.named("views")
				operation = "OpenGraphicalEditor".callJavaAction("opengraphicaleditor", 
					"view" -> "[views/]"
				)
			]
			ownedTools += OperationAction.createAs(Ns.operation, "openInstanceText") [
				precondition = '''self.showMenuOpenTextualEditorInstance()'''.trimAql
				view = ContainerViewVariable.named("views")
				operation = "OpenTextualEditor".callJavaAction("openinstancetextualeditor", 
					"view" -> "[views/]"
				)
			]
		]

	}


	
}
