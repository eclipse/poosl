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
import org.eclipse.sirius.diagram.description.tool.ToolSection
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance
import org.eclipse.sirius.viewpoint.description.tool.DropContainerVariable
import org.eclipse.sirius.viewpoint.description.tool.ElementVariable
import org.eclipse.sirius.viewpoint.description.tool.ElementViewVariable
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionParameter
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation
import org.eclipse.sirius.viewpoint.description.tool.OperationAction
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription
import org.eclipse.sirius.viewpoint.description.tool.SetValue

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

		toolSections += ToolSection.create [
			ownedTools += OperationAction.createAs(Ns.operation, "Channel Color") [
				precondition = "[thisEObject.showMenuChangeColor()/]"
				forceRefresh = true
				view = ContainerViewVariable.create [
					name = "views"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "Change HighLight  Color"
						forceRefresh = true
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "changecolor"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[views/]"
						]
					]
				]
			]
			ownedTools += OperationAction.createAs(Ns.operation, "Open Textual Editor") [
				precondition = "[thisEObject.showMenuOpenTextualEditor()/]"
				view = ContainerViewVariable.create [
					name = "views"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "OpenTextualEditor"
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "opentextualeditor"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[views/]"
						]
					]
				]
			]
			ownedTools += OperationAction.createAs(Ns.operation, "Open Class Diagram") [
				precondition = "true"
				view = ContainerViewVariable.create [
					name = "views"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "OpenClassDiagram"
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "openclassdiagram"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[views/]"
						]
					]
				]
			]
			ownedTools += PasteDescription.createAs(Ns.operation, "Copy Instance") [
				forceRefresh = true
				container = DropContainerVariable.create [
					name = "container"
				]
				containerView = ContainerViewVariable.create [
					name = "containerView"
				]
				copiedView = ElementViewVariable.create [
					name = "copiedView"
				]
				copiedElement = ElementVariable.create [
					name = "copiedElement"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ChangeContext.create [
						browseExpression = "var:container"
						subModelOperations += CreateInstance.create [
							typeName = "poosl.Instance"
							referenceName = "instances"
							subModelOperations += SetValue.create [
								featureName = "name"
								valueExpression = "[container.getUniqueInstanceName(copiedElement.name)/]"
							]
							subModelOperations += SetValue.create [
								featureName = "classDefinition"
								valueExpression = "[copiedElement.classDefinition/]"
							]
							subModelOperations += SetValue.create [
								featureName = "instanceParameters"
								valueExpression = "[copiedElement.instanceParameters/]"
							]
						]
						subModelOperations += ExternalJavaAction.createAs(Ns.operation, "Save") [
							id = "externalcall"
							parameters += ExternalJavaActionParameter.create [
								name = "container"
								value = "[container/]"
							]
							parameters += ExternalJavaActionParameter.create [
								name = "action"
								value = "save"
							]
						]
					]
				]
			]
			ownedTools += OperationAction.createAs(Ns.operation, "Show/Hide Communication Elements") [
				precondition = "true"
				forceRefresh = true
				view = ContainerViewVariable.create [
					name = "views"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "OpenTextualEditor"
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "showhidedebug"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[views/]"
						]
					]
				]
			]
			ownedTools += OperationAction.createAs(Ns.operation, "StructureDiagramFromStructure") [
				label = "Open Composite Structure Diagram"
				precondition = "[thisEObject.showMenuInstanceOpenStructureDiagram()/]"
				view = ContainerViewVariable.create [
					name = "views"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "OpenGraphicalEditor"
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "opengraphicaleditor"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[views/]"
						]
					]
				]
			]
			ownedTools += OperationAction.createAs(Ns.operation, "Open Instance in Textual Editor") [
				precondition = "[thisEObject.showMenuOpenTextualEditorInstance()/]"
				view = ContainerViewVariable.create [
					name = "views"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "OpenTextualEditor"
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "openinstancetextualeditor"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[views/]"
						]
					]
				]
			]
		]

	}


	
}
