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

import org.eclipse.poosl.Channel
import org.eclipse.poosl.ClusterClass
import org.eclipse.poosl.Instance
import org.eclipse.poosl.Port
import org.eclipse.poosl.ProcessClass
import org.eclipse.sirius.diagram.LabelPosition
import org.eclipse.sirius.diagram.ResizeKind
import org.eclipse.sirius.diagram.description.ContainerMapping
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration
import org.eclipse.sirius.diagram.description.DiagramDescription
import org.eclipse.sirius.diagram.description.DiagramElementMapping
import org.eclipse.sirius.diagram.description.EdgeMapping
import org.eclipse.sirius.diagram.description.Layer
import org.eclipse.sirius.diagram.description.NodeMapping
import org.eclipse.sirius.diagram.description.style.BeginLabelStyleDescription
import org.eclipse.sirius.diagram.description.style.CenterLabelStyleDescription
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription
import org.eclipse.sirius.diagram.description.style.EndLabelStyleDescription
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription
import org.eclipse.sirius.diagram.description.style.SquareDescription
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription
import org.eclipse.sirius.diagram.description.tool.DeleteElementDescription
import org.eclipse.sirius.diagram.description.tool.DiagramNavigationDescription
import org.eclipse.sirius.diagram.description.tool.DoubleClickDescription
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription
import org.eclipse.sirius.diagram.description.tool.ElementDoubleClickVariable
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription
import org.eclipse.sirius.diagram.description.tool.ReconnectionKind
import org.eclipse.sirius.diagram.description.tool.SourceEdgeCreationVariable
import org.eclipse.sirius.diagram.description.tool.SourceEdgeViewCreationVariable
import org.eclipse.sirius.diagram.description.tool.TargetEdgeCreationVariable
import org.eclipse.sirius.diagram.description.tool.TargetEdgeViewCreationVariable
import org.eclipse.sirius.diagram.description.tool.ToolSection
import org.eclipse.sirius.viewpoint.FontFormat
import org.eclipse.sirius.viewpoint.LabelAlignment
import org.eclipse.sirius.viewpoint.description.SystemColor
import org.eclipse.sirius.viewpoint.description.UserFixedColor
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable
import org.eclipse.sirius.viewpoint.description.tool.ElementDeleteVariable
import org.eclipse.sirius.viewpoint.description.tool.ElementSelectVariable
import org.eclipse.sirius.viewpoint.description.tool.ElementVariable
import org.eclipse.sirius.viewpoint.description.tool.ElementViewVariable
import org.eclipse.sirius.viewpoint.description.tool.NameVariable
import org.eclipse.sirius.viewpoint.description.tool.OperationAction
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription

import static extension org.mypsycho.modit.emf.sirius.api.SiriusDesigns.*

class ClusterDiagramDiagram extends PooslDiagram {

	new(PooslDesign parent) {
		super(parent, "Cluster diagram", ClusterClass)
	}
	
	override initContent(DiagramDescription it) {
		super.initContent(it)

		name = "Cluster diagram" // force name as label
		titleExpression = "aql:self.name"

		enablePopupBars = true
			
		layout = CustomLayoutConfiguration.create [
			id = "org.eclipse.elk.layered"
			label = "ELK Layered"
		]
	}
	
	override initDefaultEdgeStyle(EdgeStyleDescription it) {
		super.initDefaultEdgeStyle(it)
		
		// TODO No label, thus it should be removed.
		beginLabelStyleDescription = BeginLabelStyleDescription.create [
			showIcon = false
			labelColor = SystemColor.extraRef("color:black")
		]
		centerLabelStyleDescription = CenterLabelStyleDescription.create [
			showIcon = false
			labelColor = SystemColor.extraRef("color:black")
		]
		endLabelStyleDescription = EndLabelStyleDescription.create [
			showIcon = false
			labelColor = SystemColor.extraRef("color:black")
		]
	}
	
	def createNodePortStyle() {
		WorkspaceImageDescription.createStyle [
			// labelExpression = '''self.getPortName()'''.trimAql
			labelPosition = LabelPosition.BORDER_LITERAL
			
			// Gif image has a blurry effect in sirius
			workspacePath = EXTRA_ICON_PATH + "Port.png"
		]
	}
	
	override initContent(Layer it) {
		containerMappings += ContainerMapping.createAs(Ns.node, "ClusterClass") [
			
			semanticCandidatesExpression = '''self.getCleanedCacheSelf()'''.trimAql
			// Why is Xtext cache cleared on refresh ?
			synchronizationLock = true
			domainClass = ClusterClass
			pasteDescriptions += PasteDescription.ref(ReusedDiagramDiagram, Ns.operation, "Copy Instance")
			
			// TODO
			// For conditional style, only icon seems different.
			// Customization should be used.
			val defaultClusterStyle = [FlatContainerStyleDescription it | 
				// labelSize = 8 // Built-in value; probably too small.
				labelExpression = "self.getDiagramName()".trimAql
				labelFormat += FontFormat.BOLD_LITERAL				
				labelAlignment = LabelAlignment.LEFT
				roundedCorner = true
				widthComputationExpression = "20"
				heightComputationExpression = "20"
				foregroundColor = SystemColor.extraRef("color:white")
				labelBorderStyle = LabelBorderStyleDescription
						.extraRef("labelBorder:labelBorderStyleWithBeveledCorner")
			]
			
			style = FlatContainerStyleDescription.createStyle[
				defaultClusterStyle.apply(it)
				iconPath = DEFAULT_ICON_PATH + "ClusterClass.png"
			]

			styleIf(FlatContainerStyleDescription, "service:isSystemDiagram") [
				defaultClusterStyle.apply(it)
				iconPath = EXTRA_ICON_PATH + "System.gif"
			]
			
			borderedNodeMappings += NodeMapping.createAs(Ns.node, "ExternalPort") [
				semanticCandidatesExpression = '''self.ports'''.trimAql
				synchronizationLock = true
				domainClass = Port
				deletionDescription = DeleteElementDescription.localRef(Ns.del, "Delete Extern Port")
				style = createNodePortStyle
			]
			subNodeMappings += NodeMapping.createAs(Ns.node, "ChannelCluster") [
				domainClass = Channel
				semanticCandidatesExpression = "feature:channels"
				preconditionExpression = '''not self.isSimpleChannel()'''.trimAql
				synchronizationLock = true
				style = SquareDescription.createStyle [
					// No resizable by default
					borderSizeComputationExpression = "0"
					showIcon = false
					labelExpression = "" // no name for channel
					sizeComputationExpression = "1"
					width = 1
					height = 1
					color = SystemColor.extraRef("color:black")
				]
			]
			
			subNodeMappings += NodeMapping.createAs(Ns.node, "ClusterInstance") [
				semanticCandidatesExpression = "feature:instances"
				synchronizationLock = true
				domainClass = Instance
				navigationDescriptions += DiagramNavigationDescription.localRef(Ns.operation, "NavigateInstance")
				deletionDescription = DeleteElementDescription.localRef(Ns.del, "Delete instance")

				val defaultInstanceStyle = [SquareDescription it | 
					labelExpression = "aql:self.getInstanceName()"
					sizeComputationExpression = "8"
					
					borderSizeComputationExpression = "2" // Border size matches port icon.
					labelPosition = LabelPosition.NODE_LITERAL
					resizeKind = ResizeKind.NSEW_LITERAL
					width = 15
					height = 10
				]
				
				// Design choice: 
				// SquareDescription center label on element
				// but color gradiant is not supported !
				// Color gradiant is only on FlatContainerStyleDescription.
				style = SquareDescription.createStyle [
					defaultInstanceStyle.apply(it)

					color = Color.gray.lightRef
					iconPath = DEFAULT_ICON_PATH + "Instance.gif"
				]
				
				#[ 
					ProcessClass ->"ProcessBkg", 
					ClusterClass ->"ClusterBkg"
				].forEach[ custo |
					val className = custo.key.simpleName
					
					styleIf(SquareDescription, 
						'''self.getInstanceType().oclIsKindOf(poosl::«className»)'''.trimAql) [
						defaultInstanceStyle.apply(it)
						
						iconPath = '''«DEFAULT_ICON_PATH»«className».png'''
						color = UserFixedColor.ref("color:" + custo.value)
					]
				]

				
				borderedNodeMappings += NodeMapping.createAs(Ns.node, "InstancePort") [
					semanticCandidatesExpression = "aql:self.getDeclaredInstancePorts()"
					synchronizationLock = true
					domainClass = "EObject" // InstancePort + Port
					deletionDescription = DeleteElementDescription.localRef(Ns.del, "Delete Port From Instance")
//					style = SquareDescription.createStyle [
//						sizeComputationExpression="3" // default value; probably to large
//						showIcon = false
//						labelExpression = "aql:self.getPortName()"
//						labelPosition = LabelPosition.NODE_LITERAL
//						resizeKind = ResizeKind.NSEW_LITERAL
//						width = 4
//						height = 2
//						color = SystemColor.extraRef("color:light_blue")
//					]
					style = createNodePortStyle.andThen[
						labelExpression = '''self.getPortName()'''.trimAql
						// customize(parentType.oclIsKindOf(poosl::ProcessClass) && isIn, 
						// "workspacePath", "in.Gif")
					]
					
				]
			]
		]
		// SimpleChannel, ChannelEnd
		
		// simple channels
		edgeMappings += EdgeMapping.createAs(Ns.edge, "SimpleChannel") [
			useDomainElement = true
			domainClass = Channel
			semanticCandidatesExpression = '''self.channels->select(it | it.isSimpleChannel())'''.trimAql
			
			// same port may appears on several instances,
			// precondition drives  { port.view, target.view } contraint
			preconditionExpression = '''
				self.isInstanceSimpleConnected(sourceView, targetView)
				'''.trimAql // view.target
			
			// Delete: delete channel
			deletionDescription = DeleteElementDescription.ref(ReusedDiagramDiagram, Ns.del, "Delete Poosl Element")
			
			sourceMapping += NodeMapping.localRef(Ns.node, "InstancePort")
			sourceMapping += NodeMapping.localRef(Ns.node, "ExternalPort")
			sourceFinderExpression = "aql:self.getSimpleChannelEnd(false)"

			targetMapping += NodeMapping.localRef(Ns.node, "InstancePort")
			targetFinderExpression = "aql:self.getSimpleChannelEnd(true)"

			reconnections += ReconnectEdgeDescription.localRef(Ns.reconnect, "Reconnect Channel")
			style = []
		]

		edgeMappings += EdgeMapping.createAs(Ns.edge, "ChannelToEnd") [
			synchronizationLock = true
	
			// TODO test
			deletionDescription = DeleteElementDescription.localRef(Ns.del, "Delete Connection End")
			
			sourceMapping += NodeMapping.localRef(Ns.node, "ChannelCluster")
			// sourceMapping += EdgeMapping.localRef(Ns.edge, "SimpleChannel") // This is a trick for creation
			
			targetMapping += NodeMapping.localRef(Ns.node, "InstancePort")
			targetMapping += NodeMapping.localRef(Ns.node, "ExternalPort")
			targetFinderExpression = '''self.getChannelEnds()'''.trimAql
			// same port may appears on several instances,
			// precondition drives  { port.view, target.view } contraint
			preconditionExpression = '''
				source.isInstanceConnected(targetView)
				'''.trimAql // view.target

			reconnections += ReconnectEdgeDescription.localRef(Ns.reconnect, "Reconnect Channel")
			style = []
		]
		
		// A trick to transform a SimpleChannel into a ChannelCluster when 
		val virtualEdgeFactory = [ String id, DiagramElementMapping src, DiagramElementMapping tgt |
			edgeMappings += EdgeMapping.createAs(Ns.edge, id) [
				synchronizationLock = true
				sourceMapping += src
				
				targetFinderExpression = "aql:null" // never actual applicable
				targetMapping += tgt
				
				style = []
			]
		]
		
		virtualEdgeFactory.apply("ChannelClusterCreationByChannel",
			EdgeMapping.localRef(Ns.edge, "SimpleChannel"),
			NodeMapping.localRef(Ns.node, "InstancePort")
		)
		virtualEdgeFactory.apply("ChannelClusterCreationByEnd",
			NodeMapping.localRef(Ns.node, "InstancePort"),
			EdgeMapping.localRef(Ns.edge, "SimpleChannel")
		)
		
		
		toolSections += createNavigationTools()
		toolSections += createNodesTools()
		toolSections += createRelationsTools()
	}
	
	protected def createNodesTools() {
		ToolSection.create [
			name = "Nodes"
			reusedTools += PasteDescription.ref(ReusedDiagramDiagram, Ns.operation, "Copy Instance")
			ownedTools += DeleteElementDescription.createAs(Ns.del, "Delete instance") [
				name = "Delete instance"
				precondition = "true"
				forceRefresh = true
				element = ElementDeleteVariable.named("element")
				elementView = ElementDeleteVariable.named("elementView")
				containerView = ContainerViewVariable.named("containerView")
				operation = "deleteinstance".callJavaAction("deleteinstance",
					"element" -> "[element/]",
					"view" -> "[elementView/]",
					"containerView" -> "[containerView/]"
				)
			]
			ownedTools += ToolDescription.create [
				name = "Create Instance"
				label = "Instance"
				precondition = "service:canCreateInstance()"
				forceRefresh = true
				iconPath = DEFAULT_ICON_PATH + "Instance.gif"
				element = ElementVariable.named("element")
				elementView = ElementViewVariable.named("elementView")
				operation = "createprocessclass".callJavaAction("createprocessclass",
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

			ownedTools += DeleteElementDescription.createAs(Ns.del, "Delete Port From Instance") [
				precondition = "true"
				forceRefresh = true
				element = ElementDeleteVariable.named("element")
				elementView = ElementDeleteVariable.named("elementView")
				containerView = ContainerViewVariable.named("containerView")
				operation = "deleteconnection".callJavaAction("deleteportfrominstance",
					"element" -> "[element/]",
					"view" -> "[elementView/]",
					"containerView" -> "[containerView/]"
				)
			]
			ownedTools += DeleteElementDescription.createAs(Ns.del, "Delete Extern Port") [
				precondition = "true"
				forceRefresh = true
				element = ElementDeleteVariable.named("element")
				elementView = ElementDeleteVariable.named("elementView")
				containerView = ContainerViewVariable.named("containerView")
				operation = "deleteconnection".callJavaAction("deleteexternport",
					"element" -> "[element/]",
					"view" -> "[elementView/]",
					"containerView" -> "[containerView/]"
				)
			]
		]
	}
	
	protected def ToolSection createNavigationTools() {
		ToolSection.create [
			name = "Navigation"
			label = "Navigation"
			ownedTools += OperationAction.createAs(Ns.operation, "Channel Color") [
				precondition = "aql:self.showMenuChangeColor()"
				forceRefresh = true
				view = ContainerViewVariable.named("views")
				operation = "Change HighLight Color".callJavaAction("changecolor", 
					"view" -> "[views/]"
				)
			]
			reusedTools += OperationAction.ref(ReusedDiagramDiagram, Ns.operation, "Open Textual Editor")
			reusedTools += OperationAction.ref(ReusedDiagramDiagram, Ns.operation, "Open Class Diagram")
			reusedTools += OperationAction.ref(ReusedDiagramDiagram, Ns.operation, "StructureDiagramFromStructure")
			reusedTools += OperationAction.ref(ReusedDiagramDiagram, Ns.operation, "Show/Hide Communication Elements")
			reusedTools += OperationAction.ref(ReusedDiagramDiagram, Ns.operation, "Open Instance in Textual Editor")
			ownedTools += DoubleClickDescription.create [
				name = "Open Textual Editor"
				mappings += ContainerMapping.localRef(Ns.node, "ClusterClass")
				element = ElementDoubleClickVariable.named("element")
				elementView = ElementDoubleClickVariable.named("elementView")
				operation = "double click instance".callJavaAction("opentextualeditor",
					"element" -> "aql:element",
					"elementView" -> "aql:elementView"
				)
			]
			ownedTools += DiagramNavigationDescription.createAs(Ns.operation, "NavigateInstance") [
				navigationNameExpression = "aql:'Navigate to instance'"
				diagramDescription = DiagramDescription.ref("ClusterDiagramDiagram")
				containerViewVariable = ContainerViewVariable.named("containerView")
				containerVariable = ElementSelectVariable.named("container")
				representationNameVariable = NameVariable.named("diagramName")
			]
			ownedTools += DoubleClickDescription.create [
				name = "Open Editor"
				mappings += NodeMapping.localRef(Ns.node, "ClusterInstance")
				element = ElementDoubleClickVariable.named("element")
				elementView = ElementDoubleClickVariable.named("elementView")
				operation = "double click instance".callJavaAction("doubleclickopeneditor",
					"element" -> "aql:element",
					"elementView" -> "aql:elementView"
				)
			]
			ownedTools += DoubleClickDescription.create [
				name = "Edge"
				//mappings += EdgeMapping.localRef(Ns.edge, "ChannelToExternalCluster")
				mappings += EdgeMapping.localRef(Ns.edge, "SimpleChannel")
				mappings += EdgeMapping.localRef(Ns.edge, "ChannelToEnd")
				element = ElementDoubleClickVariable.named("element")
				elementView = ElementDoubleClickVariable.named("elementView")
				operation = "PortOrInstance".callJavaAction("doubleclickedge",
					"element" -> "aql:element",
					"elementView" -> "aql:elementView"
				)
			]
			ownedTools += DoubleClickDescription.create [
				name = "Channel"
				mappings += NodeMapping.localRef(Ns.node, "ChannelCluster")
				element = ElementDoubleClickVariable.named("element")
				elementView = ElementDoubleClickVariable.named("elementView")
				operation = "PortOrInstance".callJavaAction("doubleclickchannel",
					"element" -> "aql:element",
					"elementView" -> "aql:elementView"
				)
			]
		]
	}
	
	protected def createRelationsTools() {
		ToolSection.create [
			name = "Relations"
			ownedTools += EdgeCreationDescription.create [
				name = "Create Channel"
				label = "Channel"
				precondition = '''self.isClusterConnectableEnd()'''.trimAql
				forceRefresh = true
				connectionStartPrecondition = '''self.isClusterConnectableEnd()'''.trimAql
				
				edgeMappings += EdgeMapping.localRef(Ns.edge, "ChannelToEnd")
				edgeMappings += EdgeMapping.localRef(Ns.edge, "SimpleChannel")
				edgeMappings += EdgeMapping.localRef(Ns.edge, "ChannelClusterCreationByChannel")
				edgeMappings += EdgeMapping.localRef(Ns.edge, "ChannelClusterCreationByEnd")

				sourceVariable = SourceEdgeCreationVariable.named("source")
				targetVariable = TargetEdgeCreationVariable.named("target")
				sourceViewVariable = SourceEdgeViewCreationVariable.named("sourceView")
				targetViewVariable = TargetEdgeViewCreationVariable.named("targetView")
				operation = '''source.createConnection(sourceView, targetView)'''.trimAql.toOperation
			]
			ownedTools += ReconnectEdgeDescription.createAs(Ns.reconnect, "Reconnect Channel") [
				reconnectionKind = ReconnectionKind.RECONNECT_BOTH_LITERAL
				
				source = SourceEdgeCreationVariable.named("source")
				target = TargetEdgeCreationVariable.named("target")
				sourceView = SourceEdgeViewCreationVariable.named("sourceView")
				targetView = TargetEdgeViewCreationVariable.named("targetView")
				element = ElementSelectVariable.named("element")
				edgeView = ElementSelectVariable.named("edgeView")
				operation = '''
					element.reconnectConnection(sourceView, targetView)
					'''.trimAql.toOperation
			]
			ownedTools += DeleteElementDescription.createAs(Ns.del, "Delete Connection End") [
				precondition = "true"
				forceRefresh = true
				element = ElementDeleteVariable.named("element") // DEdge.source:Channel
				elementView = ElementDeleteVariable.named("elementView") // DEdge
				containerView = ContainerViewVariable.named("containerView")
				operation = '''
					element.deleteConnectionEnd(containerView,
					  elementView.targetNode.target,
					  elementView.targetNode.eContainer().target)
					'''.trimAql.toOperation
			]
		]
	}


}
