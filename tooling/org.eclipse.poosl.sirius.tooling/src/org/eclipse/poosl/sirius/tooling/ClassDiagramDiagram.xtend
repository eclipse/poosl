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
import org.eclipse.sirius.diagram.BackgroundStyle
import org.eclipse.sirius.diagram.ContainerLayout
import org.eclipse.sirius.diagram.EdgeArrows
import org.eclipse.sirius.diagram.ResizeKind
import org.eclipse.sirius.diagram.description.CenteringStyle
import org.eclipse.sirius.diagram.description.ContainerMapping
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration
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
import org.eclipse.sirius.viewpoint.description.UserFixedColor
import org.eclipse.sirius.viewpoint.description.tool.ContainerModelOperation
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance
import org.eclipse.sirius.viewpoint.description.tool.DropContainerVariable
import org.eclipse.sirius.viewpoint.description.tool.ElementDeleteVariable
import org.eclipse.sirius.viewpoint.description.tool.ElementVariable
import org.eclipse.sirius.viewpoint.description.tool.ElementViewVariable
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionCall
import org.eclipse.sirius.viewpoint.description.tool.If
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation
import org.eclipse.sirius.viewpoint.description.tool.OperationAction
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription

import static extension org.mypsycho.modit.emf.sirius.api.SiriusDesigns.*

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
				semanticConditionExpression = '''self.isBasicClass()'''.trimAql
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
		
		layout = CustomLayoutConfiguration.create [
			id = "org.eclipse.elk.mrtree"
			label = "ELK Mr. Tree"
		]
			
		defaultConcern = ConcernDescription.localRef(Ns.show, "DefaultConcern")
	}

	protected def void setClassStyle(ContainerMapping it, (FlatContainerStyleDescription)=>void init) {
		style = FlatContainerStyleDescription.createStyle [
			backgroundStyle = BackgroundStyle.GRADIENT_LEFT_TO_RIGHT_LITERAL
			labelFormat += FontFormat.BOLD_LITERAL
			roundedCorner = true //type identification
			init.apply(it)
		]
	}

	protected def void setMemberStyle(NodeMapping it, (SquareDescription)=>void init) {
		style = SquareDescription.createStyle [
			labelAlignment = LabelAlignment.LEFT
			resizeKind = ResizeKind.NSEW_LITERAL
			//height = 1
			// borderColor = SystemColor.extraRef("color:black")
			// labelColor = SystemColor.extraRef("color:black")
			// color = SystemColor.extraRef("color:light_blue")
			init.apply(it)
		]
	}

	override initContent(Layer it) {
		containerMappings += ContainerMapping.createAs(Ns.node, "CLA_System") [
			semanticCandidatesExpression = "service:getSystemSpecification()"
			domainClass = ClusterClass
			childrenPresentation = ContainerLayout.LIST
			deletionDescription = DeleteElementDescription.localRef(Ns.del, "DelSystem")

			classStyle = [

				labelExpression = "System"
				iconPath = EXTRA_ICON_PATH + "System.gif"
				
				// borderColor = black
				foregroundColor = SystemColor.extraRef("color:grey")
			]
		]
		containerMappings += ContainerMapping.createAs(Ns.node, "CLA_Cluster") [
			semanticCandidatesExpression = "service:getAllClusterClasses()"
			synchronizationLock = true
			domainClass = ClusterClass
			childrenPresentation = ContainerLayout.LIST
			classStyle = [
				iconPath = DEFAULT_ICON_PATH + "ClusterClass.png"
				borderColor = UserFixedColor.ref("color:ClusterBorder")
				foregroundColor = UserFixedColor.ref("color:ClusterBkg")
			]
			deletionDescription = DeleteElementDescription.localRef(Ns.del, "DelCluster")
			subNodeMappings += NodeMapping.createAs(Ns.node, "CLA_Clus_Parameters") [
				semanticCandidatesExpression = "service:getParameters"
				synchronizationLock = true
				domainClass = Variable
				deletionDescription = DeleteElementDescription.localRef(Ns.del, "DelParameter")
				memberStyle = [
					labelExpression = "service:getVariableDescription"
					iconPath = EXTRA_ICON_PATH + "ProcessParam.gif"
				]
			]
		]
		containerMappings += ContainerMapping.createAs(Ns.node, "CLA_Process") [
			synchronizationLock = true
			domainClass = ProcessClass
			semanticCandidatesExpression = "service:getAllProcessClasses()"
			childrenPresentation = ContainerLayout.LIST
			deletionDescription = DeleteElementDescription.localRef(Ns.del, "DelProcess")
			classStyle = [
				iconPath = DEFAULT_ICON_PATH + "ProcessClass.png"
				borderColor = UserFixedColor.ref("color:ProcessBorder")
				foregroundColor = UserFixedColor.ref("color:ProcessBkg")
			]
			subNodeMappings += NodeMapping.createAs(Ns.node, "CLA_Proc_Parameters") [
				semanticCandidatesExpression = "service:getParameters"
				synchronizationLock = true
				domainClass = Variable
				deletionDescription = DeleteElementDescription.localRef(Ns.del, "DelParameter")
				memberStyle = [
					labelExpression = "service:getVariableDescription"
					iconPath = EXTRA_ICON_PATH + "ProcessParam.gif"
				]
			]
			subNodeMappings += NodeMapping.createAs(Ns.node, "CLA_Proc_InstanceVariables") [
				semanticCandidatesExpression = "service:getVariables"
				synchronizationLock = true
				domainClass = Variable
				memberStyle = [
					labelExpression = '''self.getVariableDescription()'''.trimAql
					iconPath = EXTRA_ICON_PATH + "ProcessVariable.gif"
				]
			]
			subNodeMappings += NodeMapping.createAs(Ns.node, "CLA_ProcessMethods") [
				semanticCandidatesExpression = '''self.methods'''.trimAql
				synchronizationLock = true
				domainClass = ProcessMethod
				deletionDescription = DeleteElementDescription.localRef(Ns.del, "DelMethod")
				memberStyle = [
					labelExpression = '''self.getProcessMethodLabel()'''.trimAql
					iconPath = DEFAULT_ICON_PATH + "ProcessMethod.gif"
				]
			]
		]
		containerMappings += ContainerMapping.createAs(Ns.node, "CLA_Data") [
			semanticCandidatesExpression = "service:getAllDataClasses()"
			synchronizationLock = true
			domainClass = DataClass
			childrenPresentation = ContainerLayout.LIST
			classStyle = [
				labelExpression = "service:getClassName"
				iconPath = DEFAULT_ICON_PATH + "DataClass.gif"

				borderColor = UserFixedColor.ref("color:DataBorder")
				foregroundColor = UserFixedColor.ref("color:DataBkg")
			]
			deletionDescription = DeleteElementDescription.localRef(Ns.del, "DelData")
			subNodeMappings += NodeMapping.createAs(Ns.node, "CLA_Data_InstanceVariables") [
				semanticCandidatesExpression = "service:getVariables"
				synchronizationLock = true
				domainClass = Variable
				memberStyle = [
					labelExpression = "service:getVariableDescription"
					
					// iconPath = "org.eclipse.poosl.sirius/icons/variableicon.png"
					iconPath = EXTRA_ICON_PATH + "DataClass_Variable.gif"
				]
			]
			subNodeMappings += NodeMapping.createAs(Ns.node, "CLA_DataMethods") [
				semanticCandidatesExpression = "aql:self.dataMethodsNamed"
				synchronizationLock = true
				domainClass = DataMethodNamed
				deletionDescription = DeleteElementDescription.localRef(Ns.del, "DelMethod")
				memberStyle = [
					labelExpression = '''self.getDataMethodLabel()'''.trimAql
					iconPath = DEFAULT_ICON_PATH + "DataMethodNamed.gif"
				]
			]
			subNodeMappings += NodeMapping.createAs(Ns.node, "CLA_DataMethodUnaryOperator") [
				semanticCandidatesExpression = '''self.dataMethodsUnaryOperator'''.trimAql
				synchronizationLock = true
				domainClass = DataMethodUnaryOperator
				memberStyle = [
					labelExpression = '''self.name'''.trimAql
					iconPath = DEFAULT_ICON_PATH + "DataMethodUnaryOperator.gif"
				]
			]
			subNodeMappings += NodeMapping.createAs(Ns.node, "CLA_DataMethodBinaryOperator") [
				semanticCandidatesExpression = '''self.dataMethodsBinaryOperator'''.trimAql
				synchronizationLock = true
				domainClass = DataMethodBinaryOperator
				memberStyle = [
					labelExpression = '''self.getDataMethodLabel()'''.trimAql
					iconPath = DEFAULT_ICON_PATH + "DataMethodBinaryOperator.gif"
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
				element = ElementDoubleClickVariable.named("element")
				elementView = ElementDoubleClickVariable.named("elementView")
				operation = "double click instance".callJavaAction("doubleclickclassdiagrammember",
					"element" -> "aql:element",
					"elementView" -> "aql:elementView"
				)
			]
			ownedTools += OperationAction.create [
				name = "Edit Method"
				precondition = "service:isMethod"
				view = ContainerViewVariable.named("views")
				operation = "Edit Method".callJavaAction("editmethod", 
					"view" -> "[views/]"
				)
			]
			ownedTools += OperationAction.create [
				name = "Edit Variable"
				precondition = "service:isVariable"
				view = ContainerViewVariable.named("views")
				operation = "Edit Variable".callJavaAction("editvariable", 
					"view" -> "[views/]"
				)
			]
			ownedTools += OperationAction.create [
				name = "Edit Parameter"
				precondition = "service:isParameter"
				view = ContainerViewVariable.named("views")
				operation = "Edit Parameter".callJavaAction("editparameter", 
					"view" -> "[views/]"
				)
			]
			ownedTools += OperationAction.create [
				name = "Open Textual Editor"
				precondition = "[thisEObject.showMenuOpenTextualEditor()/]"
				view = ContainerViewVariable.named("views")
				operation = "OpenTextualEditor".callJavaAction("opentextualeditor", 
					"view" -> "[views/]"
				)
			]
			ownedTools += OperationAction.create [
				name = "Open Composite Structure Diagram"
				precondition = "[thisEObject.showMenuOpenGraphicalEditor()/]"
				view = ContainerViewVariable.named("views")
				operation = "OpenGraphicalEditor".callJavaAction("opengraphicaleditor", 
					"view" -> "[views/]"
				)
			]
			ownedTools += DeleteElementDescription.createAs(Ns.del, "DelParameter") [
				precondition = "true"
				forceRefresh = true
				element = ElementDeleteVariable.named("element")
				elementView = ElementDeleteVariable.named("elementView")
				containerView = ContainerViewVariable.named("containerView")
				operation = "deleteparameter".callJavaAction("deleteparameter",
					"element" -> "[element/]",
					"view" -> "[elementView/]",
					"containerView" -> "[containerView/]"
				)
			]
			ownedTools += DeleteElementDescription.createAs(Ns.del, "DelMethod") [
				precondition = "true"
				forceRefresh = true
				element = ElementDeleteVariable.named("element")
				elementView = ElementDeleteVariable.named("elementView")
				containerView = ContainerViewVariable.named("containerView")
				operation = "delete Variable".callJavaAction("deletemethod",
					"element" -> "[element/]",
					"view" -> "[elementView/]",
					"containerView" -> "[containerView/]"
				)
			]
			ownedTools += DeleteElementDescription.createAs(Ns.del, "DelVariable") [
				precondition = "true"
				forceRefresh = true
				element = ElementDeleteVariable.named("element")
				elementView = ElementDeleteVariable.named("elementView")
				containerView = ContainerViewVariable.named("containerView")
				operation = "delete Variable".callJavaAction("deletevariable",
					"element" -> "[element/]",
					"view" -> "[elementView/]",
					"containerView" -> "[containerView/]"
				)
			]
			ownedTools += DeleteElementDescription.createAs(Ns.del, "DelInheritance") [
				precondition = "true"
				forceRefresh = true
				element = ElementDeleteVariable.named("element")
				elementView = ElementDeleteVariable.named("elementView")
				containerView = ContainerViewVariable.named("containerView")
				operation = "deleteinheritance".callJavaAction("deleteinheritance",
					"element" -> "[element/]",
					"view" -> "[elementView/]",
					"containerView" -> "[containerView/]"
				)
			]
			ownedTools += DeleteElementDescription.createAs(Ns.del, "DelCluster") [
				precondition = "true"
				forceRefresh = true
				element = ElementDeleteVariable.named("element")
				elementView = ElementDeleteVariable.named("elementView")
				containerView = ContainerViewVariable.named("containerView")
				operation = "deletecluster".callJavaAction("deletecluster",
					"element" -> "[element/]",
					"view" -> "[elementView/]",
					"containerView" -> "[containerView/]"
				)
			]
			ownedTools += DeleteElementDescription.createAs(Ns.del, "DelData") [
				precondition = "true"
				forceRefresh = true
				element = ElementDeleteVariable.named("element")
				elementView = ElementDeleteVariable.named("elementView")
				containerView = ContainerViewVariable.named("containerView")
				operation = "deletedata".callJavaAction("deletedata",
					"element" -> "[element/]",
					"view" -> "[elementView/]",
					"containerView" -> "[containerView/]"
				)
			]
			ownedTools += DeleteElementDescription.createAs(Ns.del, "DelProcess") [
				name = "DelProcess"
				precondition = "true"
				forceRefresh = true
				element = ElementDeleteVariable.named("element")
				elementView = ElementDeleteVariable.named("elementView")
				containerView = ContainerViewVariable.named("containerView")
				
				operation = "deleteprocess".callJavaAction("deleteprocess",
					"element" -> "[element/]",
					"view" -> "[elementView/]",
					"containerView" -> "[containerView/]"
				)
			]
			ownedTools += DeleteElementDescription.createAs(Ns.del, "DelSystem") [
				precondition = "aql:false" // no delete
				forceRefresh = true
				element = ElementDeleteVariable.named("element")
				elementView = ElementDeleteVariable.named("elementView")
				containerView = ContainerViewVariable.named("containerView")
				
				initialOperation = InitialOperation.create
			]
			ownedTools += DeleteElementDescription.createAs(Ns.del, "DelContainment") [
				precondition = "true"
				forceRefresh = true
				element = ElementDeleteVariable.named("element")
				elementView = ElementDeleteVariable.named("elementView")
				containerView = ContainerViewVariable.named("containerView")
				operation = "deletecontainment".callJavaAction("deletecontainment",
					"element" -> "[element/]",
					"view" -> "[elementView/]",
					"containerView" -> "[containerView/]"
				)
			]
			ownedTools += PasteDescription.createAs(Ns.operation, "Copy Classes") [
				forceRefresh = true
				container = DropContainerVariable.named("container")
				containerView = ContainerViewVariable.named("containerView")
				copiedView = ElementViewVariable.named("copiedView")
				copiedElement = ElementVariable.named("copiedElement")
				
				val saveInvocation = [ContainerModelOperation it |					
					subModelOperations += ExternalJavaActionCall.create [
						name = "save"
						action = ExternalJavaAction.ref(ReusedDiagramDiagram, Ns.operation, "Save")
					]
				]

				operation = "var:container".toOperation.andThen [
					subModelOperations += If.create [
						conditionExpression = "[copiedElement.oclIsKindOf(ClusterClass)/]"
						subModelOperations += CreateInstance.create [
							typeName = "poosl.ClusterClass"
							referenceName = "clusterClasses"
							variableName = "clusterclass"
							setValue("name", "container.getUniqueClusterName(copiedElement.name)")
							setValue("parameters", "copiedElement.parameters")
							setValue("ports", "copiedElement.ports")
							setValue("channels", "copiedElement.channels")
							setValue("instances", "copiedElement.instances")
						]
						saveInvocation.apply(it)
					]
					subModelOperations += If.create [
						conditionExpression = "[copiedElement.oclIsKindOf(ProcessClass)/]"
						subModelOperations += CreateInstance.create [
							typeName = "poosl.ProcessClass"
							referenceName = "processClasses"
							variableName = "processClass"
							setValue("name", "container.getUniqueProcessName(copiedElement.name)")
							setValue("parameters", "copiedElement.parameters")
							setValue("ports", "copiedElement.ports")
							setValue("superClass", "copiedElement.superClass")
							setValue("receiveMessages", "copiedElement.receiveMessages")
							setValue("sendMessages", "copiedElement.sendMessages")
							setValue("instanceVariables", "copiedElement.instanceVariables")
							setValue("methods", "copiedElement.methods")
							setValue("initialMethodCall", "copiedElement.initialMethodCall")
						]
						saveInvocation.apply(it)
					]
					subModelOperations += If.create [
						conditionExpression = "[copiedElement.oclIsKindOf(DataClass)/]"
						subModelOperations += CreateInstance.create [
							typeName = "poosl.DataClass"
							referenceName = "dataClasses"
							variableName = "dataclasses"
							setValue("name", "container.getUniqueDataName(copiedElement.name)")
							setValue("superClass", "copiedElement.superClass")
							setValue("instanceVariables", "copiedElement.instanceVariables")
							setValue("native", "copiedElement.native")
							setValue("dataMethodsNamed", "copiedElement.dataMethodsNamed")
							setValue("dataMethodsUnaryOperator", "copiedElement.dataMethodsUnaryOperator")
							setValue("dataMethodsBinaryOperator", "copiedElement.dataMethodsBinaryOperator")
						]
						saveInvocation.apply(it)
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
				iconPath = DEFAULT_ICON_PATH + "DataClass.gif"
				element = ElementVariable.named("element")
				elementView = ElementViewVariable.named("elementView")
				operation = "create Data".callJavaAction("createdata",
					"element" -> "[element/]",
					"view" -> "[elementView/]"
				)
			]
			ownedTools += ToolDescription.create [
				name = "Create Process Class"
				label = "Process Class"
				precondition = "[container.oclIsKindOf(Poosl)/]"
				forceRefresh = true
				iconPath = DEFAULT_ICON_PATH + "ProcessClass.png"
				element = ElementVariable.named("element")
				elementView = ElementViewVariable.named("elementView")
				operation = "createprocessclass".callJavaAction("createprocess",
					"element" -> "[element/]",
					"view" -> "[elementView/]"
				)
			]
			ownedTools += ToolDescription.create [
				name = "Create Cluster Class"
				label = "Cluster Class"
				precondition = "[container.oclIsKindOf(Poosl)/]"
				forceRefresh = true
				iconPath = DEFAULT_ICON_PATH + "ClusterClass.png"
				element = ElementVariable.named("element")
				elementView = ElementViewVariable.named("elementView")
				operation = "create cluster class".callJavaAction("createcluster",
					"element" -> "[element/]",
					"view" -> "[elementView/]"
				)
			]
			ownedTools += ToolDescription.create [
				name = "Create System"
				label = "System"
				precondition = "service:canCreateSystem"
				forceRefresh = true
				iconPath = EXTRA_ICON_PATH + "System.gif"
				element = ElementVariable.named("element")
				elementView = ElementViewVariable.named("elementView")
				operation = "create system".callJavaAction("createsystem",
					"element" -> "[element/]",
					"view" -> "[elementView/]"
				)
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
				iconPath = EXTRA_ICON_PATH + "ProcessParam.gif"
				element = ElementVariable.named("element")
				elementView = ElementViewVariable.named("elementView")
				operation = "create parameter".callJavaAction("createparameter",
					"element" -> "[element/]",
					"view" -> "[elementView/]"
				)
			]
			ownedTools += ToolDescription.create [
				name = "Create Variable"
				label = "Variable"
				precondition = "service:hasVariables"
				forceRefresh = true
				iconPath = EXTRA_ICON_PATH + "ProcessVariable.gif"
				element = ElementVariable.named("element")
				elementView = ElementViewVariable.named("elementView")
				operation = "Create Variable".callJavaAction("createvariable",
					"element" -> "[element/]",
					"view" -> "[elementView/]"
				)
			]
			ownedTools += ToolDescription.create [
				name = "Create Port"
				label = "Port"
				precondition = "service:canCreatePort()"
				forceRefresh = true
				iconPath = DEFAULT_ICON_PATH + "Port.gif"
				element = ElementVariable.named("element")
				elementView = ElementViewVariable.named("elementView")
				operation = "createport".callJavaAction("createport",
					"element" -> "[element/]",
					"view" -> "[elementView/]"
				)
			]
			ownedTools += ToolDescription.create [
				name = "Create Method"
				label = "Method"
				precondition = "service:hasMethods"
				forceRefresh = true
				iconPath = DEFAULT_ICON_PATH + "ProcessMethod.gif"
				element = ElementVariable.named("element")
				elementView = ElementViewVariable.named("elementView")
				operation = "create Method".callJavaAction("createmethod",
					"element" -> "[element/]",
					"view" -> "[elementView/]"
				)
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
				iconPath = EXTRA_ICON_PATH + "Generalization.gif"
				connectionStartPrecondition = "service:canInherit"
				edgeMappings += EdgeMapping.localRef(Ns.edge, "CLA_DataInheritance")
				edgeMappings += EdgeMapping.localRef(Ns.edge, "CLA_ProcessInheritance")

				sourceVariable = SourceEdgeCreationVariable.named("source")
				targetVariable = TargetEdgeCreationVariable.named("target")
				sourceViewVariable = SourceEdgeViewCreationVariable.named("sourceView")
				targetViewVariable = TargetEdgeViewCreationVariable.named("targetView")

				operation = "set Inheritance".callJavaAction("setinheritance",
					"source" -> "[source/]",
					"target" -> "[target/]"
				)
			]
			ownedTools += EdgeCreationDescription.create [
				name = "Create Containment"
				label = "Containment"
				precondition = "service:canBeContained()"
				iconPath = EXTRA_ICON_PATH + "Composition.gif"
				connectionStartPrecondition = "[preSource.oclIsKindOf(ClusterClass)/]"
				edgeMappings += EdgeMapping.localRef(Ns.edge, "CLA_Containment")

				sourceVariable = SourceEdgeCreationVariable.named("source")
				targetVariable = TargetEdgeCreationVariable.named("target")
				sourceViewVariable = SourceEdgeViewCreationVariable.named("sourceView")
				targetViewVariable = TargetEdgeViewCreationVariable.named("targetView")

				operation = "create containment".callJavaAction("createcontainment",
					"source" -> "[source/]",
					"target" -> "[target/]"
				)
			]
		]
	}

	
}
