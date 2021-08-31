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

import org.eclipse.poosl.ClusterClass
import org.eclipse.poosl.DataClass
import org.eclipse.poosl.DataMethodBinaryOperator
import org.eclipse.poosl.DataMethodNamed
import org.eclipse.poosl.DataMethodUnaryOperator
import org.eclipse.poosl.Poosl
import org.eclipse.poosl.ProcessClass
import org.eclipse.poosl.ProcessMethod
import org.eclipse.poosl.Variable
import org.eclipse.sirius.diagram.ContainerLayout
import org.eclipse.sirius.diagram.EdgeArrows
import org.eclipse.sirius.diagram.LabelPosition
import org.eclipse.sirius.diagram.ResizeKind
import org.eclipse.sirius.diagram.description.CenteringStyle
import org.eclipse.sirius.diagram.description.ContainerMapping
import org.eclipse.sirius.diagram.description.DiagramDescription
import org.eclipse.sirius.diagram.description.EdgeMapping
import org.eclipse.sirius.diagram.description.Layer
import org.eclipse.sirius.diagram.description.NodeMapping
import org.eclipse.sirius.diagram.description.concern.ConcernDescription
import org.eclipse.sirius.diagram.description.concern.ConcernSet
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription
import org.eclipse.sirius.diagram.description.filter.MappingFilter
import org.eclipse.sirius.diagram.description.style.CenterLabelStyleDescription
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription
import org.eclipse.sirius.diagram.description.style.EndLabelStyleDescription
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription
import org.eclipse.sirius.diagram.description.style.SquareDescription
import org.eclipse.sirius.diagram.description.tool.DeleteElementDescription
import org.eclipse.sirius.diagram.description.tool.DoubleClickDescription
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription
import org.eclipse.sirius.diagram.description.tool.ElementDoubleClickVariable
import org.eclipse.sirius.diagram.description.tool.SourceEdgeCreationVariable
import org.eclipse.sirius.diagram.description.tool.SourceEdgeViewCreationVariable
import org.eclipse.sirius.diagram.description.tool.TargetEdgeCreationVariable
import org.eclipse.sirius.diagram.description.tool.TargetEdgeViewCreationVariable
import org.eclipse.sirius.diagram.description.tool.ToolSection
import org.eclipse.sirius.viewpoint.FontFormat
import org.eclipse.sirius.viewpoint.LabelAlignment
import org.eclipse.sirius.viewpoint.description.SystemColor
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance
import org.eclipse.sirius.viewpoint.description.tool.DropContainerVariable
import org.eclipse.sirius.viewpoint.description.tool.ElementDeleteVariable
import org.eclipse.sirius.viewpoint.description.tool.ElementVariable
import org.eclipse.sirius.viewpoint.description.tool.ElementViewVariable
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionCall
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionParameter
import org.eclipse.sirius.viewpoint.description.tool.If
import org.eclipse.sirius.viewpoint.description.tool.InitEdgeCreationOperation
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation
import org.eclipse.sirius.viewpoint.description.tool.OperationAction
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription
import org.eclipse.sirius.viewpoint.description.tool.SetValue
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription

class ClassDiagramDiagram extends PooslDiagram {

	new(PooslDesign parent) {
		super(parent, "Class diagram", Poosl)
	}
		
	override initDefaultEdgeStyle(EdgeStyleDescription it) {
		super.initDefaultEdgeStyle(it)
		
		// TODO No label, thus it should be removed.
		centerLabelStyleDescription = CenterLabelStyleDescription.create [
			labelColor = SystemColor.extraRef("color:black")
		]
	}
	
	override initContent(DiagramDescription it) {
		super.initContent(it)
		name = "Class diagram" // force name as label
		enablePopupBars = true
		
		pasteDescriptions += PasteDescription.localRef(Ns.operation, "Copy Classes")
		filters += CompositeFilterDescription.createAs(Ns.show, "Hide Basic Classes") [
			documentation = "This filter will collapse dataclasses called Array, Boolean, Char, Integer, Nil, Object, Real and String."
			filters += MappingFilter.create [
				semanticConditionExpression = "[isBasicClass()/]"
				mappings += ContainerMapping.localRef(Ns.node, "CLA_Data")
			]
		]
		filters += CompositeFilterDescription.createAs(Ns.show, "Hide Imports") [
			filters += MappingFilter.create [
				viewConditionExpression = "service:isImportedClass()"
				mappings += ContainerMapping.localRef(Ns.node, "CLA_Cluster")
				mappings += ContainerMapping.localRef(Ns.node, "CLA_Data")
				mappings += ContainerMapping.localRef(Ns.node, "CLA_Process")
				mappings += ContainerMapping.localRef(Ns.node, "CLA_System")
			]
		]
		concerns = ConcernSet.create [
			ownedConcernDescriptions += ConcernDescription.createAs(Ns.show, "DefaultConcern") [
				name = "Default" // Override name for compatibility reason
				filters += CompositeFilterDescription.localRef(Ns.show, "Hide Basic Classes")
				filters += CompositeFilterDescription.localRef(Ns.show, "Hide Imports")
			]
		]
		defaultConcern = ConcernDescription.localRef(Ns.show, "DefaultConcern")
	}


	override initContent(Layer it) {
		containerMappings += ContainerMapping.createAs(Ns.node, "CLA_System") [
			semanticCandidatesExpression = "service:getSystemSpecification()"
			domainClass = ClusterClass
			childrenPresentation = ContainerLayout.LIST
			deletionDescription = DeleteElementDescription.localRef(Ns.del, "DelSystem")
			style = FlatContainerStyleDescription.create [
				borderSizeComputationExpression = "1"
				labelFormat += FontFormat.BOLD_LITERAL
				labelExpression = "System"
				iconPath = "/org.eclipse.poosl.sirius/icons/system_icon.png"
				roundedCorner = true
				borderColor = SystemColor.extraRef("color:black")
				labelColor = SystemColor.extraRef("color:black")
				backgroundColor = SystemColor.extraRef("color:light_gray")
				foregroundColor = SystemColor.extraRef("color:light_gray")
			]
		]
		containerMappings += ContainerMapping.createAs(Ns.node, "CLA_Cluster") [
			semanticCandidatesExpression = "service:getAllClusterClasses()"
			synchronizationLock = true
			domainClass = ClusterClass
			childrenPresentation = ContainerLayout.LIST
			style = FlatContainerStyleDescription.create [
				borderSizeComputationExpression = "1"
				labelFormat += FontFormat.BOLD_LITERAL
				labelExpression = "service:getClassName"
				iconPath = "/org.eclipse.poosl.sirius/icons/cluster_icon.png"
				roundedCorner = true
				borderColor = SystemColor.extraRef("color:black")
				labelColor = SystemColor.extraRef("color:black")
				backgroundColor = SystemColor.extraRef("color:light_yellow")
				foregroundColor = SystemColor.extraRef("color:light_yellow")
			]
			deletionDescription = DeleteElementDescription.localRef(Ns.del, "DelCluster")
			subNodeMappings += NodeMapping.createAs(Ns.node, "CLA_Clus_Parameters") [
				semanticCandidatesExpression = "service:getParameters"
				synchronizationLock = true
				domainClass = Variable
				deletionDescription = DeleteElementDescription.localRef(Ns.del, "DelParameter")
				style = SquareDescription.create [
					labelExpression = "service:getVariableDescription"
					iconPath = "org.eclipse.poosl.sirius/icons/parametericon.png"
					labelAlignment = LabelAlignment.LEFT
					resizeKind = ResizeKind.NSEW_LITERAL
					height = 1
					borderColor = SystemColor.extraRef("color:black")
					labelColor = SystemColor.extraRef("color:black")
					color = SystemColor.extraRef("color:light_blue")
				]
			]
		]
		containerMappings += ContainerMapping.createAs(Ns.node, "CLA_Process") [
			semanticCandidatesExpression = "service:getAllProcessClasses()"
			synchronizationLock = true
			domainClass = ProcessClass
			childrenPresentation = ContainerLayout.LIST
			deletionDescription = DeleteElementDescription.localRef(Ns.del, "DelProcess")
			style = FlatContainerStyleDescription.create [
				borderSizeComputationExpression = "1"
				labelFormat += FontFormat.BOLD_LITERAL
				labelExpression = "service:getClassName"
				iconPath = "/org.eclipse.poosl.sirius/icons/process_icon.png"
				roundedCorner = true
				borderColor = SystemColor.extraRef("color:black")
				labelColor = SystemColor.extraRef("color:black")
				backgroundColor = SystemColor.extraRef("color:light_blue")
				foregroundColor = SystemColor.extraRef("color:light_blue")
			]
			subNodeMappings += NodeMapping.createAs(Ns.node, "CLA_Proc_Parameters") [
				semanticCandidatesExpression = "service:getParameters"
				synchronizationLock = true
				domainClass = Variable
				deletionDescription = DeleteElementDescription.localRef(Ns.del, "DelParameter")
				style = SquareDescription.create [
					labelExpression = "service:getVariableDescription"
					iconPath = "org.eclipse.poosl.sirius/icons/parametericon.png"
					labelAlignment = LabelAlignment.LEFT
					resizeKind = ResizeKind.NSEW_LITERAL
					borderColor = SystemColor.extraRef("color:black")
					labelColor = SystemColor.extraRef("color:black")
					color = SystemColor.extraRef("color:light_blue")
				]
			]
			subNodeMappings += NodeMapping.createAs(Ns.node, "CLA_Proc_InstanceVariables") [
				semanticCandidatesExpression = "service:getVariables"
				synchronizationLock = true
				domainClass = Variable
				style = SquareDescription.create [
					labelExpression = "service:getVariableDescription"
					iconPath = "org.eclipse.poosl.sirius/icons/variableicon.png"
					labelAlignment = LabelAlignment.LEFT
					resizeKind = ResizeKind.NSEW_LITERAL
					borderColor = SystemColor.extraRef("color:black")
					labelColor = SystemColor.extraRef("color:black")
					color = SystemColor.extraRef("color:light_blue")
				]
			]
			subNodeMappings += NodeMapping.createAs(Ns.node, "CLA_ProcessMethods") [
				semanticCandidatesExpression = "[thisEObject.methods/]"
				synchronizationLock = true
				domainClass = ProcessMethod
				deletionDescription = DeleteElementDescription.localRef(Ns.del, "DelMethod")
				style = SquareDescription.create [
					labelExpression = "[thisEObject.getProcessMethodLabel()/]"
					iconPath = "org.eclipse.poosl.sirius/icons/cogicon.png"
					labelAlignment = LabelAlignment.LEFT
					resizeKind = ResizeKind.NSEW_LITERAL
					borderColor = SystemColor.extraRef("color:black")
					labelColor = SystemColor.extraRef("color:black")
					color = SystemColor.extraRef("color:light_blue")
				]
			]
		]
		containerMappings += ContainerMapping.createAs(Ns.node, "CLA_Data") [
			semanticCandidatesExpression = "service:getAllDataClasses()"
			synchronizationLock = true
			domainClass = DataClass
			childrenPresentation = ContainerLayout.LIST
			style = FlatContainerStyleDescription.create [
				borderSizeComputationExpression = "1"
				labelFormat += FontFormat.BOLD_LITERAL
				labelExpression = "service:getClassName"
				iconPath = "/org.eclipse.poosl.sirius/icons/data_icon.png"
				roundedCorner = true
				borderColor = SystemColor.extraRef("color:black")
				labelColor = SystemColor.extraRef("color:black")
				backgroundColor = SystemColor.extraRef("color:white")
				foregroundColor = SystemColor.extraRef("color:white")
			]
			deletionDescription = DeleteElementDescription.localRef(Ns.del, "DelData")
			subNodeMappings += NodeMapping.createAs(Ns.node, "CLA_Data_InstanceVariables") [
				semanticCandidatesExpression = "service:getVariables"
				synchronizationLock = true
				domainClass = Variable
				style = SquareDescription.create [
					labelExpression = "service:getVariableDescription"
					iconPath = "org.eclipse.poosl.sirius/icons/variableicon.png"
					labelAlignment = LabelAlignment.LEFT
					resizeKind = ResizeKind.NSEW_LITERAL
					borderColor = SystemColor.extraRef("color:black")
					labelColor = SystemColor.extraRef("color:black")
					color = SystemColor.extraRef("color:light_blue")
				]
			]
			subNodeMappings += NodeMapping.createAs(Ns.node, "CLA_DataMethods") [
				semanticCandidatesExpression = "[thisEObject.dataMethodsNamed/]"
				synchronizationLock = true
				domainClass = DataMethodNamed
				deletionDescription = DeleteElementDescription.localRef(Ns.del, "DelMethod")
				style = SquareDescription.create [
					labelExpression = "[thisEObject.getDataMethodLabel()/]"
					iconPath = "org.eclipse.poosl.sirius/icons/cogicon.png"
					labelAlignment = LabelAlignment.LEFT
					sizeComputationExpression = "1"
					labelPosition = LabelPosition.NODE_LITERAL
					width = 1
					height = 1
					borderColor = SystemColor.extraRef("color:black")
					labelColor = SystemColor.extraRef("color:black")
					color = SystemColor.extraRef("color:light_blue")
				]
			]
			subNodeMappings += NodeMapping.createAs(Ns.node, "CLA_DataMethodUnaryOperator") [
				semanticCandidatesExpression = "[thisEObject.dataMethodsUnaryOperator/]"
				synchronizationLock = true
				domainClass = DataMethodUnaryOperator
				style = SquareDescription.create [
					labelExpression = "[thisEObject.name/]"
					iconPath = "org.eclipse.poosl.sirius/icons/cogicon.png"
					labelAlignment = LabelAlignment.LEFT
					resizeKind = ResizeKind.NSEW_LITERAL
					borderColor = SystemColor.extraRef("color:black")
					labelColor = SystemColor.extraRef("color:black")
					color = SystemColor.extraRef("color:light_blue")
				]
			]
			subNodeMappings += NodeMapping.createAs(Ns.node, "CLA_DataMethodBinaryOperator") [
				semanticCandidatesExpression = "[thisEObject.dataMethodsBinaryOperator/]"
				synchronizationLock = true
				domainClass = DataMethodBinaryOperator
				style = SquareDescription.create [
					labelExpression = "[thisEObject.getDataMethodLabel()/]"
					iconPath = "org.eclipse.poosl.sirius/icons/cogicon.png"
					labelAlignment = LabelAlignment.LEFT
					resizeKind = ResizeKind.NSEW_LITERAL
					borderColor = SystemColor.extraRef("color:black")
					labelColor = SystemColor.extraRef("color:black")
					color = SystemColor.extraRef("color:light_blue")
				]
			]
		]
		
		edgeMappings += EdgeMapping.createAs(Ns.edge, "CLA_Containment") [
			targetFinderExpression = "[thisEObject.getSystemInstances()/]"
			deletionDescription = DeleteElementDescription.localRef(Ns.del, "DelContainment")
			sourceMapping += ContainerMapping.localRef(Ns.node, "CLA_System")
			sourceMapping += ContainerMapping.localRef(Ns.node, "CLA_Cluster")
			targetMapping += ContainerMapping.localRef(Ns.node, "CLA_Cluster")
			targetMapping += ContainerMapping.localRef(Ns.node, "CLA_Process")
			style = [
				sourceArrow = EdgeArrows.FILL_DIAMOND_LITERAL
				endsCentering = CenteringStyle.NONE
				endLabelStyleDescription = EndLabelStyleDescription.create [
					showIcon = false
					labelExpression = "[getNumberOfInstances(view)/]"
					labelColor = SystemColor.extraRef("color:black")
				]
			]
		]
		edgeMappings += EdgeMapping.createAs(Ns.edge, "CLA_ProcessInheritance") [
			targetFinderExpression = "[thisEObject.getInheritance()/]"
			deletionDescription = DeleteElementDescription.localRef(Ns.del, "DelInheritance")
			sourceMapping += ContainerMapping.localRef(Ns.node, "CLA_Process")
			targetMapping += ContainerMapping.localRef(Ns.node, "CLA_Process")
			style = [
				targetArrow = EdgeArrows.INPUT_CLOSED_ARROW_LITERAL
				endsCentering = CenteringStyle.NONE
			]
		]
		edgeMappings += EdgeMapping.createAs(Ns.edge, "CLA_DataInheritance") [
			targetFinderExpression = "[thisEObject.getInheritance()/]"
			deletionDescription = DeleteElementDescription.localRef(Ns.del, "DelInheritance")
			sourceMapping += ContainerMapping.localRef(Ns.node, "CLA_Data")
			targetMapping += ContainerMapping.localRef(Ns.node, "CLA_Data")
			style = [
				targetArrow = EdgeArrows.INPUT_CLOSED_ARROW_LITERAL
				endsCentering = CenteringStyle.NONE
				// TODO: Suspicious why gray for data ?
				strokeColor = SystemColor.extraRef("color:gray")
			]
		]

		toolSections += createCommonTools()
		toolSections += createNodesTools()
		toolSections += createAttributesTools()
		toolSections += createRelationsTools()

	}
	
	protected def createCommonTools() {
		ToolSection.create [
			ownedTools += DoubleClickDescription.create [
				name = "Variable"
				mappings += NodeMapping.localRef(Ns.node, "CLA_Clus_Parameters")
				mappings += NodeMapping.localRef(Ns.node, "CLA_Data_InstanceVariables")
				mappings += NodeMapping.localRef(Ns.node, "CLA_Proc_Parameters")
				mappings += NodeMapping.localRef(Ns.node, "CLA_Proc_InstanceVariables")
				mappings += NodeMapping.localRef(Ns.node, "CLA_DataMethodBinaryOperator")
				mappings += NodeMapping.localRef(Ns.node, "CLA_DataMethods")
				mappings += NodeMapping.localRef(Ns.node, "CLA_DataMethodUnaryOperator")
				mappings += NodeMapping.localRef(Ns.node, "CLA_ProcessMethods")
				mappings += ContainerMapping.localRef(Ns.node, "CLA_Cluster")
				mappings += ContainerMapping.localRef(Ns.node, "CLA_Data")
				mappings += ContainerMapping.localRef(Ns.node, "CLA_Process")
				mappings += ContainerMapping.localRef(Ns.node, "CLA_System")
				element = ElementDoubleClickVariable.create [
					name = "element"
				]
				elementView = ElementDoubleClickVariable.create [
					name = "elementView"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "double click instance"
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "doubleclickclassdiagrammember"
						]
					]
				]
			]
			ownedTools += OperationAction.create [
				name = "Edit Method"
				precondition = "service:isMethod"
				view = ContainerViewVariable.create [
					name = "views"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "Edit Method"
						forceRefresh = true
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "editmethod"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[views/]"
						]
					]
				]
			]
			ownedTools += OperationAction.create [
				name = "Edit Variable"
				precondition = "service:isVariable"
				view = ContainerViewVariable.create [
					name = "views"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "Edit Method"
						forceRefresh = true
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "editvariable"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[views/]"
						]
					]
				]
			]
			ownedTools += OperationAction.create [
				name = "Edit Parameter"
				precondition = "service:isParameter"
				view = ContainerViewVariable.create [
					name = "views"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "Edit Method"
						forceRefresh = true
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "editparameter"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[views/]"
						]
					]
				]
			]
			ownedTools += OperationAction.create [
				name = "Open Textual Editor"
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
			ownedTools += OperationAction.create [
				name = "Open Composite Structure Diagram"
				precondition = "[thisEObject.showMenuOpenGraphicalEditor()/]"
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
			ownedTools += DeleteElementDescription.createAs(Ns.del, "DelParameter") [
				precondition = "true"
				forceRefresh = true
				element = ElementDeleteVariable.create [
					name = "element"
				]
				elementView = ElementDeleteVariable.create [
					name = "elementView"
				]
				containerView = ContainerViewVariable.create [
					name = "containerView"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "deleteparameter"
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "element"
							value = "[element/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[elementView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "containerView"
							value = "[containerView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "deleteparameter"
						]
					]
				]
			]
			ownedTools += DeleteElementDescription.createAs(Ns.del, "DelMethod") [
				precondition = "true"
				forceRefresh = true
				element = ElementDeleteVariable.create [
					name = "element"
				]
				elementView = ElementDeleteVariable.create [
					name = "elementView"
				]
				containerView = ContainerViewVariable.create [
					name = "containerView"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "delete Variable"
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "element"
							value = "[element/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[elementView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "containerView"
							value = "[containerView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "deletemethod"
						]
					]
				]
			]
			ownedTools += DeleteElementDescription.createAs(Ns.del, "DelVariable") [
				precondition = "true"
				forceRefresh = true
				element = ElementDeleteVariable.create [
					name = "element"
				]
				elementView = ElementDeleteVariable.create [
					name = "elementView"
				]
				containerView = ContainerViewVariable.create [
					name = "containerView"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "deleteparameter"
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "element"
							value = "[element/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[elementView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "containerView"
							value = "[containerView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "deletevariable"
						]
					]
				]
			]
			ownedTools += DeleteElementDescription.createAs(Ns.del, "DelInheritance") [
				precondition = "true"
				forceRefresh = true
				element = ElementDeleteVariable.create [
					name = "element"
				]
				elementView = ElementDeleteVariable.create [
					name = "elementView"
				]
				containerView = ContainerViewVariable.create [
					name = "containerView"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "deleteinheritance"
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "element"
							value = "[element/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[elementView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "containerView"
							value = "[containerView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "deleteinheritance"
						]
					]
				]
			]
			ownedTools += DeleteElementDescription.createAs(Ns.del, "DelCluster") [
				precondition = "true"
				forceRefresh = true
				element = ElementDeleteVariable.create [
					name = "element"
				]
				elementView = ElementDeleteVariable.create [
					name = "elementView"
				]
				containerView = ContainerViewVariable.create [
					name = "containerView"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "deleteparameter"
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "element"
							value = "[element/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[elementView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "containerView"
							value = "[containerView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "deletecluster"
						]
					]
				]
			]
			ownedTools += DeleteElementDescription.createAs(Ns.del, "DelData") [
				precondition = "true"
				forceRefresh = true
				element = ElementDeleteVariable.create [
					name = "element"
				]
				elementView = ElementDeleteVariable.create [
					name = "elementView"
				]
				containerView = ContainerViewVariable.create [
					name = "containerView"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "deleteparameter"
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "element"
							value = "[element/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[elementView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "containerView"
							value = "[containerView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "deletedata"
						]
					]
				]
			]
			ownedTools += DeleteElementDescription.createAs(Ns.del, "DelProcess") [
				name = "DelProcess"
				precondition = "true"
				forceRefresh = true
				element = ElementDeleteVariable.create [
					name = "element"
				]
				elementView = ElementDeleteVariable.create [
					name = "elementView"
				]
				containerView = ContainerViewVariable.create [
					name = "containerView"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "deleteparameter"
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "element"
							value = "[element/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[elementView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "containerView"
							value = "[containerView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "deleteprocess"
						]
					]
				]
			]
			ownedTools += DeleteElementDescription.create [
				name = "DelSystem do Nothing"
				element = ElementDeleteVariable.create [
					name = "element"
				]
				elementView = ElementDeleteVariable.create [
					name = "elementView"
				]
				containerView = ContainerViewVariable.create [
					name = "containerView"
				]
				initialOperation = InitialOperation.create
			]
			ownedTools += DeleteElementDescription.createAs(Ns.del, "DelSystem") [
				precondition = "true"
				forceRefresh = true
				element = ElementDeleteVariable.create [
					name = "element"
				]
				elementView = ElementDeleteVariable.create [
					name = "elementView"
				]
				containerView = ContainerViewVariable.create [
					name = "containerView"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "deleteparameter"
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "element"
							value = "[element/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[elementView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "containerView"
							value = "[containerView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "deletesystem"
						]
					]
				]
			]
			ownedTools += DeleteElementDescription.createAs(Ns.del, "DelContainment") [
				precondition = "true"
				forceRefresh = true
				element = ElementDeleteVariable.create [
					name = "element"
				]
				elementView = ElementDeleteVariable.create [
					name = "elementView"
				]
				containerView = ContainerViewVariable.create [
					name = "containerView"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "deletecontainment"
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "element"
							value = "[element/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[elementView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "containerView"
							value = "[containerView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "deletecontainment"
						]
					]
				]
			]
			ownedTools += PasteDescription.createAs(Ns.operation, "Copy Classes") [
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
						subModelOperations += If.create [
							conditionExpression = "[copiedElement.oclIsKindOf(ClusterClass)/]"
							subModelOperations += CreateInstance.create [
								typeName = "poosl.ClusterClass"
								referenceName = "clusterClasses"
								variableName = "clusterclass"
								subModelOperations += SetValue.create [
									featureName = "name"
									valueExpression = "[container.getUniqueClusterName(copiedElement.name)/]"
								]
								subModelOperations += SetValue.create [
									featureName = "parameters"
									valueExpression = "[copiedElement.parameters/]"
								]
								subModelOperations += SetValue.create [
									featureName = "ports"
									valueExpression = "[copiedElement.ports/]"
								]
								subModelOperations += SetValue.create [
									featureName = "channels"
									valueExpression = "[copiedElement.channels/]"
								]
								subModelOperations += SetValue.create [
									featureName = "instances"
									valueExpression = "[copiedElement.instances/]"
								]
							]
							subModelOperations += ExternalJavaActionCall.create [
								name = "save"
								action = ExternalJavaAction.ref(ReusedDiagramDiagram, Ns.operation, "Save")
							]
						]
						subModelOperations += If.create [
							conditionExpression = "[copiedElement.oclIsKindOf(ProcessClass)/]"
							subModelOperations += CreateInstance.create [
								typeName = "poosl.ProcessClass"
								referenceName = "processClasses"
								variableName = "processClass"
								subModelOperations += SetValue.create [
									featureName = "name"
									valueExpression = "[container.getUniqueProcessName(copiedElement.name)/]"
								]
								subModelOperations += SetValue.create [
									featureName = "parameters"
									valueExpression = "[copiedElement.parameters/]"
								]
								subModelOperations += SetValue.create [
									featureName = "ports"
									valueExpression = "[copiedElement.ports/]"
								]
								subModelOperations += SetValue.create [
									featureName = "superClass"
									valueExpression = "[copiedElement.superClass/]"
								]
								subModelOperations += SetValue.create [
									featureName = "receiveMessages"
									valueExpression = "[copiedElement.receiveMessages/]"
								]
								subModelOperations += SetValue.create [
									featureName = "sendMessages"
									valueExpression = "[copiedElement.sendMessages/]"
								]
								subModelOperations += SetValue.create [
									featureName = "instanceVariables"
									valueExpression = "[copiedElement.instanceVariables/]"
								]
								subModelOperations += SetValue.create [
									featureName = "methods"
									valueExpression = "[copiedElement.methods/]"
								]
								subModelOperations += SetValue.create [
									featureName = "initialMethodCall"
									valueExpression = "[copiedElement.initialMethodCall/]"
								]
							]
							subModelOperations += ExternalJavaActionCall.create [
								name = "save"
								action = ExternalJavaAction.ref(ReusedDiagramDiagram, Ns.operation, "Save")
							]
						]
						subModelOperations += If.create [
							conditionExpression = "[copiedElement.oclIsKindOf(DataClass)/]"
							subModelOperations += CreateInstance.create [
								typeName = "poosl.DataClass"
								referenceName = "dataClasses"
								variableName = "dataclasses"
								subModelOperations += SetValue.create [
									featureName = "name"
									valueExpression = "[container.getUniqueDataName(copiedElement.name)/]"
								]
								subModelOperations += SetValue.create [
									featureName = "superClass"
									valueExpression = "[copiedElement.superClass/]"
								]
								subModelOperations += SetValue.create [
									featureName = "instanceVariables"
									valueExpression = "[copiedElement.instanceVariables/]"
								]
								subModelOperations += SetValue.create [
									featureName = "native"
									valueExpression = "[copiedElement.native/]"
								]
								subModelOperations += SetValue.create [
									featureName = "dataMethodsNamed"
									valueExpression = "[copiedElement.dataMethodsNamed/]"
								]
								subModelOperations += SetValue.create [
									featureName = "dataMethodsUnaryOperator"
									valueExpression = "[copiedElement.dataMethodsUnaryOperator/]"
								]
								subModelOperations += SetValue.create [
									featureName = "dataMethodsBinaryOperator"
									valueExpression = "[copiedElement.dataMethodsBinaryOperator/]"
								]
							]
							subModelOperations += ExternalJavaActionCall.create [
								name = "save"
								action = ExternalJavaAction.ref(ReusedDiagramDiagram, Ns.operation, "Save")
							]
						]
					]
				]
			]
		]
	}
	
	protected def createNodesTools() {
		ToolSection.create [
			name = "Nodes"
			ownedTools += ToolDescription.create [
				name = "Create Data Class"
				label = "Data Class"
				precondition = "[container.oclIsKindOf(Poosl)/]"
				forceRefresh = true
				iconPath = "org.eclipse.poosl.sirius/icons/data_icon.png"
				element = ElementVariable.create [
					name = "element"
				]
				elementView = ElementViewVariable.create [
					name = "elementView"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "createprocessclass"
						forceRefresh = true
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "createdata"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[elementView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "element"
							value = "[element/]"
						]
					]
				]
			]
			ownedTools += ToolDescription.create [
				name = "Create Process Class"
				label = "Process Class"
				precondition = "[container.oclIsKindOf(Poosl)/]"
				forceRefresh = true
				iconPath = "org.eclipse.poosl.sirius/icons/process_icon.png"
				element = ElementVariable.create [
					name = "element"
				]
				elementView = ElementViewVariable.create [
					name = "elementView"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "createprocessclass"
						forceRefresh = true
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "createprocess"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[elementView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "element"
							value = "[element/]"
						]
					]
				]
			]
			ownedTools += ToolDescription.create [
				name = "Create Cluster Class"
				label = "Cluster Class"
				precondition = "[container.oclIsKindOf(Poosl)/]"
				forceRefresh = true
				iconPath = "org.eclipse.poosl.sirius/icons/cluster_icon.png"
				element = ElementVariable.create [
					name = "element"
				]
				elementView = ElementViewVariable.create [
					name = "elementView"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "createprocessclass"
						forceRefresh = true
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "createcluster"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[elementView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "element"
							value = "[element/]"
						]
					]
				]
			]
			ownedTools += ToolDescription.create [
				name = "Create System"
				label = "System"
				precondition = "service:canCreateSystem"
				forceRefresh = true
				iconPath = "org.eclipse.poosl.sirius/icons/system_icon.png"
				element = ElementVariable.create [
					name = "element"
				]
				elementView = ElementViewVariable.create [
					name = "elementView"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "createprocessclass"
						forceRefresh = true
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "createsystem"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[elementView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "element"
							value = "[element/]"
						]
					]
				]
			]
		]
	}
	
	protected def createAttributesTools() {
		ToolSection.create [
			name = "Attributes"
			ownedTools += ToolDescription.create [
				name = "Create Parameter"
				label = "Parameter"
				precondition = "service:hasParameters"
				forceRefresh = true
				iconPath = "org.eclipse.poosl.sirius/icons/parametericon.png"
				element = ElementVariable.create [
					name = "element"
				]
				elementView = ElementViewVariable.create [
					name = "elementView"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "createprocessclass"
						forceRefresh = true
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "createparameter"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[elementView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "element"
							value = "[element/]"
						]
					]
				]
			]
			ownedTools += ToolDescription.create [
				name = "Create Variable"
				label = "Variable"
				precondition = "service:hasVariables"
				forceRefresh = true
				iconPath = "org.eclipse.poosl.sirius/icons/variableicon.png"
				element = ElementVariable.create [
					name = "element"
				]
				elementView = ElementViewVariable.create [
					name = "elementView"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "createprocessclass"
						forceRefresh = true
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "createvariable"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[elementView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "element"
							value = "[element/]"
						]
					]
				]
			]
			ownedTools += ToolDescription.create [
				name = "Create Method"
				label = "Method"
				precondition = "service:hasMethods"
				forceRefresh = true
				iconPath = "org.eclipse.poosl.sirius/icons/cogicon.png"
				element = ElementVariable.create [
					name = "element"
				]
				elementView = ElementViewVariable.create [
					name = "elementView"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "createprocessclass"
						forceRefresh = true
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "createmethod"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "view"
							value = "[elementView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "element"
							value = "[element/]"
						]
					]
				]
			]
		]
	}
	
	protected def createRelationsTools() {
		ToolSection.create [
			name = "Relations"
			ownedTools += EdgeCreationDescription.create [
				name = "Create Inheritance"
				label = "Inheritance"
				precondition = "[preTarget.oclIsKindOf(ProcessClass) or  preTarget.oclIsKindOf(DataClass)/]"
				iconPath = "org.eclipse.poosl.sirius/icons/inheritance2_icon.png"
				connectionStartPrecondition = "service:canInherit"
				edgeMappings += EdgeMapping.localRef(Ns.edge, "CLA_DataInheritance")
				edgeMappings += EdgeMapping.localRef(Ns.edge, "CLA_ProcessInheritance")
				sourceVariable = SourceEdgeCreationVariable.create [
					name = "source"
				]
				targetVariable = TargetEdgeCreationVariable.create [
					name = "target"
				]
				sourceViewVariable = SourceEdgeViewCreationVariable.create [
					name = "sourceView"
				]
				targetViewVariable = TargetEdgeViewCreationVariable.create [
					name = "targetView"
				]
				initialOperation = InitEdgeCreationOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "set Inheritance"
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "source"
							value = "[source/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "setinheritance"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "target"
							value = "[target/]"
						]
					]
				]
			]
			ownedTools += EdgeCreationDescription.create [
				name = "Create Containment"
				label = "Containment"
				precondition = "service:canBeContained()"
				iconPath = "org.eclipse.poosl.sirius/icons/containment_icon.png"
				connectionStartPrecondition = "[preSource.oclIsKindOf(ClusterClass)/]"
				edgeMappings += EdgeMapping.localRef(Ns.edge, "CLA_Containment")
				sourceVariable = SourceEdgeCreationVariable.create [
					name = "source"
				]
				targetVariable = TargetEdgeCreationVariable.create [
					name = "target"
				]
				sourceViewVariable = SourceEdgeViewCreationVariable.create [
					name = "sourceView"
				]
				targetViewVariable = TargetEdgeViewCreationVariable.create [
					name = "targetView"
				]

				initialOperation = InitEdgeCreationOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "set Inheritance"
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "source"
							value = "[source/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "createcontainment"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "target"
							value = "[target/]"
						]
					]
				]
			]
		]
	}

	
}
