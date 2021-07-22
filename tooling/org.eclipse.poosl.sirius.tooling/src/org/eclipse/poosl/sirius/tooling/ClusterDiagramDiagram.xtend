package org.eclipse.poosl.sirius.tooling

import org.eclipse.poosl.Channel
import org.eclipse.poosl.ClusterClass
import org.eclipse.poosl.Instance
import org.eclipse.poosl.Port
import org.eclipse.sirius.diagram.LabelPosition
import org.eclipse.sirius.diagram.ResizeKind
import org.eclipse.sirius.diagram.description.CompositeLayout
import org.eclipse.sirius.diagram.description.ContainerMapping
import org.eclipse.sirius.diagram.description.DiagramDescription
import org.eclipse.sirius.diagram.description.EdgeMapping
import org.eclipse.sirius.diagram.description.Layer
import org.eclipse.sirius.diagram.description.LayoutDirection
import org.eclipse.sirius.diagram.description.NodeMapping
import org.eclipse.sirius.diagram.description.style.BeginLabelStyleDescription
import org.eclipse.sirius.diagram.description.style.CenterLabelStyleDescription
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription
import org.eclipse.sirius.diagram.description.style.EndLabelStyleDescription
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription
import org.eclipse.sirius.diagram.description.style.SquareDescription
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
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable
import org.eclipse.sirius.viewpoint.description.tool.ElementDeleteVariable
import org.eclipse.sirius.viewpoint.description.tool.ElementSelectVariable
import org.eclipse.sirius.viewpoint.description.tool.ElementVariable
import org.eclipse.sirius.viewpoint.description.tool.ElementViewVariable
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionParameter
import org.eclipse.sirius.viewpoint.description.tool.InitEdgeCreationOperation
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation
import org.eclipse.sirius.viewpoint.description.tool.NameVariable
import org.eclipse.sirius.viewpoint.description.tool.OperationAction
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription

class ClusterDiagramDiagram extends PooslDiagram {

	new(PooslDesign parent) {
		super(parent, "Cluster diagram", ClusterClass)
	}
	
	override initContent(DiagramDescription it) {
		super.initContent(it)

		name = "Cluster diagram" // force name as label
		titleExpression = "[thisEObject.name/]"

		enablePopupBars = true
			
		layout = CompositeLayout.create [
			padding = 60
			direction = LayoutDirection.LEFT_TO_RIGHT
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
	
	override initContent(Layer it) {
		containerMappings += ContainerMapping.createAs(Ns.node, "ClusterClass") [
			semanticCandidatesExpression = "service:getCluster()"
			synchronizationLock = true
			domainClass = ClusterClass
			pasteDescriptions += PasteDescription.ref(ReusedDiagramDiagram, Ns.operation, "Copy Instance")
			
			// TODO
			// For conditional style, only icon seems different.
			// Customization should be used.
			val defaultClusterStyle = [FlatContainerStyleDescription it | 
				labelSize = 8 // Built-in value; probably too small.
				labelExpression = "[thisEObject.getDiagramName()/]"
				labelFormat += FontFormat.BOLD_LITERAL				
				labelAlignment = LabelAlignment.LEFT
				roundedCorner = true
				widthComputationExpression = "20"
				heightComputationExpression = "20"
				foregroundColor = SystemColor.extraRef("color:white")
				labelBorderStyle = LabelBorderStyleDescription.extraRef("labelBorder:labelBorderStyleWithBeveledCorner")
			]
			
			styleIf(FlatContainerStyleDescription, "service:isClusterDiagram") [
				defaultClusterStyle.apply(it)
				iconPath = "/org.eclipse.poosl.sirius/icons/cluster_icon.png"				
			]

			styleIf(FlatContainerStyleDescription, "service:isSystemDiagram") [
				defaultClusterStyle.apply(it)
				iconPath = "/org.eclipse.poosl.sirius/icons/system_icon.png"
			]
			
			borderedNodeMappings += NodeMapping.createAs(Ns.node, "ExternalPort") [
				semanticCandidatesExpression = "[thisEObject.ports/]"
				synchronizationLock = true
				domainClass = Port
				deletionDescription = DeleteElementDescription.localRef(Ns.del, "Delete Extern Port")
				style = SquareDescription.createStyle [
					borderSizeComputationExpression = "0"
					labelExpression = null // XXX default value is a real issue here
					showIcon = false
					sizeComputationExpression = "4"
					labelPosition = LabelPosition.NODE_LITERAL
					resizeKind = ResizeKind.NSEW_LITERAL
					width = 4
					height = 2
					color = SystemColor.extraRef("color:light_yellow")
				]
			]
			subNodeMappings += NodeMapping.createAs(Ns.node, "ChannelCluster") [
				semanticCandidatesExpression = "[thisEObject.getChannels()/]"
				synchronizationLock = true
				domainClass = Channel
				style = SquareDescription.createStyle [
					borderSizeComputationExpression = "0"
					showIcon = false
					labelExpression = ""
					sizeComputationExpression = "1"
					width = 1
					height = 1
					color = SystemColor.extraRef("color:black")
				]
			]
			subNodeMappings += NodeMapping.createAs(Ns.node, "InstanceCluster") [
				semanticCandidatesExpression = "feature:instances"
				synchronizationLock = true
				domainClass = Instance
				navigationDescriptions += DiagramNavigationDescription.localRef(Ns.operation, "NavigateInstance")
				deletionDescription = DeleteElementDescription.localRef(Ns.del, "Delete instance")

				val defaultStyle = [SquareDescription it | 
					labelExpression = "[thisEObject.getInstanceName()/]"
					sizeComputationExpression = "8"
					labelPosition = LabelPosition.NODE_LITERAL
					resizeKind = ResizeKind.NSEW_LITERAL
					width = 15
					height = 10
					color = SystemColor.extraRef("color:white")
				]

				style = SquareDescription.createStyle [
					defaultStyle.apply(it)
					iconPath = "/org.eclipse.poosl.sirius/icons/process_icon.png"
				]
				
				// TODO
				// Only iconPath is different, change to customProperty
				styleIf(SquareDescription, "service:isClusterClass") [
					defaultStyle.apply(it)
					iconPath = "/org.eclipse.poosl.sirius/icons/cluster_icon.png"
				]
				
				borderedNodeMappings += NodeMapping.createAs(Ns.node, "PortsCluster") [
					semanticCandidatesExpression = "[thisEObject.getClassDefinitionPorts()/]"
					synchronizationLock = true
					domainClass = "EObject" // InstancePort + Port
					deletionDescription = DeleteElementDescription.localRef(Ns.del, "Delete Port From Instance")
					style = SquareDescription.createStyle [
						sizeComputationExpression="3" // default value; probably to large
						showIcon = false
						labelExpression = "[thisEObject.getPortName()/]"
						labelPosition = LabelPosition.NODE_LITERAL
						resizeKind = ResizeKind.NSEW_LITERAL
						width = 4
						height = 2
						color = SystemColor.extraRef("color:light_blue")
					]
				]
			]
		]
		
		edgeMappings += EdgeMapping.createAs(Ns.edge, "ChannelToPortCluster") [
			synchronizationLock = true
			targetFinderExpression = "[thisEObject.instancePorts/]"
			deletionDescription = DeleteElementDescription.localRef(Ns.del, "Delete Connection")
			sourceMapping += NodeMapping.localRef(Ns.node, "ChannelCluster")
			targetMapping += NodeMapping.localRef(Ns.node, "PortsCluster")
			reconnections += ReconnectEdgeDescription.localRef(Ns.reconnect, "Reconnect Channel")
			style = []
		]
		edgeMappings += EdgeMapping.createAs(Ns.edge, "ChannelToExternalCluster") [
			synchronizationLock = true
			targetFinderExpression = "[externalPort/]"
			deletionDescription = DeleteElementDescription.localRef(Ns.del, "Delete Connection")
			sourceMapping += NodeMapping.localRef(Ns.node, "ChannelCluster")
			targetMapping += NodeMapping.localRef(Ns.node, "ExternalPort")
			reconnections += ReconnectEdgeDescription.localRef(Ns.reconnect, "Reconnect Channel")
			style = []
		]
		edgeMappings += EdgeMapping.createAs(Ns.edge, "PortToPortCluster") [
			targetFinderExpression = "[thisEObject.getSingleConnectedPort()/]"
			deletionDescription = DeleteElementDescription.localRef(Ns.del, "Delete Connection")
			sourceMapping += NodeMapping.localRef(Ns.node, "PortsCluster")
			targetMapping += NodeMapping.localRef(Ns.node, "ExternalPort")
			targetMapping += NodeMapping.localRef(Ns.node, "PortsCluster")
			reconnections += ReconnectEdgeDescription.localRef(Ns.reconnect, "Reconnect Channel")
			style = []
		]
		edgeMappings += EdgeMapping.createAs(Ns.edge, "FakeExtPortToChannelCluster") [
			synchronizationLock = true
			targetFinderExpression = "[thisEObject.getCreationChannel()/]"
			sourceMapping += NodeMapping.localRef(Ns.node, "ExternalPort")
			targetMapping += NodeMapping.localRef(Ns.node, "ChannelCluster")
			style = []
		]
		edgeMappings += EdgeMapping.createAs(Ns.edge, "FakeExtPortToPortCluster") [
			synchronizationLock = true
			targetFinderExpression = "[thisEObject.getCreationChannel()/]"
			sourceMapping += NodeMapping.localRef(Ns.node, "ExternalPort")
			targetMapping += NodeMapping.localRef(Ns.node, "PortsCluster")
			style = []
		]
		edgeMappings += EdgeMapping.createAs(Ns.edge, "FakeInstancePortToChannelCluster") [
			synchronizationLock = true
			targetFinderExpression = "[thisEObject.getCreationChannel()/]"
			sourceMapping += NodeMapping.localRef(Ns.node, "PortsCluster")
			targetMapping += NodeMapping.localRef(Ns.node, "ChannelCluster")
			style = []
		]
		
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
						name = "deleteinstance"
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
							value = "deleteinstance"
						]
					]
				]
			]
			ownedTools += ToolDescription.create [
				name = "Create Instance"
				label = "Instance"
				precondition = "service:canCreateInstance()"
				forceRefresh = true
				iconPath = "org.eclipse.poosl.sirius/icons/instance.png"
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
							value = "createprocessclass"
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
				name = "Create Port"
				label = "Port"
				precondition = "service:canCreatePort()"
				forceRefresh = true
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
							value = "createport"
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
			ownedTools += DeleteElementDescription.createAs(Ns.del, "Delete Connection") [
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
						name = "deleteconnection"
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
							value = "deleteconnection"
						]
					]
				]
			]
			ownedTools += DeleteElementDescription.createAs(Ns.del, "Delete Port From Instance") [
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
						name = "deleteconnection"
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
							value = "deleteportfrominstance"
						]
					]
				]
			]
			ownedTools += DeleteElementDescription.createAs(Ns.del, "Delete Extern Port") [
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
						name = "deleteinstance"
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
							value = "deleteexternport"
						]
					]
				]
			]
		]
	}
	
	protected def ToolSection createNavigationTools() {
		ToolSection.create [
			name = "Navigation"
			label = "Navigation"
			reusedTools += OperationAction.ref(ReusedDiagramDiagram, Ns.operation, "Channel Color")
			reusedTools += OperationAction.ref(ReusedDiagramDiagram, Ns.operation, "Open Textual Editor")
			reusedTools += OperationAction.ref(ReusedDiagramDiagram, Ns.operation, "Open Class Diagram")
			reusedTools += OperationAction.ref(ReusedDiagramDiagram, Ns.operation, "StructureDiagramFromStructure")
			reusedTools += OperationAction.ref(ReusedDiagramDiagram, Ns.operation, "Show/Hide Communication Elements")
			reusedTools += OperationAction.ref(ReusedDiagramDiagram, Ns.operation, "Open Instance in Textual Editor")
			ownedTools += DiagramNavigationDescription.createAs(Ns.operation, "NavigateInstance") [
				navigationNameExpression = "[\"Navigate to instance\"/]"
				diagramDescription = DiagramDescription.ref("ClusterDiagramDiagram")
				containerViewVariable = ContainerViewVariable.create [
					name = "containerView"
				]
				containerVariable = ElementSelectVariable.create [
					name = "container"
				]
				representationNameVariable = NameVariable.create [
					name = "diagramName"
				]
			]
			ownedTools += DoubleClickDescription.create [
				name = "Open Editor"
				mappings += NodeMapping.localRef(Ns.node, "InstanceCluster")
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
							value = "doubleclickopeneditor"
						]
					]
				]
			]
			ownedTools += DoubleClickDescription.create [
				name = "Edge"
				mappings += EdgeMapping.localRef(Ns.edge, "ChannelToExternalCluster")
				mappings += EdgeMapping.localRef(Ns.edge, "ChannelToPortCluster")
				mappings += EdgeMapping.localRef(Ns.edge, "PortToPortCluster")
				element = ElementDoubleClickVariable.create [
					name = "element"
				]
				elementView = ElementDoubleClickVariable.create [
					name = "elementView"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "PortOrInstance"
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "doubleclickedge"
						]
					]
				]
			]
			ownedTools += DoubleClickDescription.create [
				name = "Channel"
				mappings += NodeMapping.localRef(Ns.node, "ChannelCluster")
				element = ElementDoubleClickVariable.create [
					name = "element"
				]
				elementView = ElementDoubleClickVariable.create [
					name = "elementView"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "PortOrInstance"
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "doubleclickchannel"
						]
					]
				]
			]
			ownedTools += DoubleClickDescription.create [
				name = "Open Textual Editor"
				mappings += ContainerMapping.localRef(Ns.node, "ClusterClass")
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
							value = "opentextualeditor"
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
				name = "Create Channel"
				label = "Channel"
				precondition = "[thisEObject.isPort()/]"
				forceRefresh = true
				connectionStartPrecondition = "[thisEObject.isPort()/]"
				edgeMappings += EdgeMapping.localRef(Ns.edge, "PortToPortCluster")
				edgeMappings += EdgeMapping.localRef(Ns.edge, "ChannelToExternalCluster")
				edgeMappings += EdgeMapping.localRef(Ns.edge, "ChannelToPortCluster")
				edgeMappings += EdgeMapping.localRef(Ns.edge, "FakeExtPortToChannelCluster")
				edgeMappings += EdgeMapping.localRef(Ns.edge, "FakeExtPortToPortCluster")
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
						name = "createconnection"
						forceRefresh = true
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "createconnection"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "source"
							value = "[source/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "target"
							value = "[target/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "targetview"
							value = "[targetView/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "sourceview"
							value = "[sourceView/]"
						]
					]
				]
			]
			ownedTools += ReconnectEdgeDescription.createAs(Ns.reconnect, "Reconnect Channel") [
				reconnectionKind = ReconnectionKind.RECONNECT_BOTH_LITERAL
				source = SourceEdgeCreationVariable.create [
					name = "source"
				]
				target = TargetEdgeCreationVariable.create [
					name = "target"
				]
				sourceView = SourceEdgeViewCreationVariable.create [
					name = "sourceView"
				]
				targetView = TargetEdgeViewCreationVariable.create [
					name = "targetView"
				]
				element = ElementSelectVariable.create [
					name = "element"
				]
				initialOperation = InitialOperation.create [
					firstModelOperations = ExternalJavaAction.create [
						name = "createconnection"
						forceRefresh = true
						id = "externalcall"
						parameters += ExternalJavaActionParameter.create [
							name = "action"
							value = "reconnectconnection"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "source"
							value = "[source/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "target"
							value = "[target/]"
						]
						parameters += ExternalJavaActionParameter.create [
							name = "targetview"
							value = "[targetView/]"
						]
					]
				]
				edgeView = ElementSelectVariable.create [
					name = "edgeView"
				]
			]
		]
	}

}
