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

import org.eclipse.emf.ecore.EObject
import org.eclipse.sirius.diagram.description.DiagramDescription
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription
import org.eclipse.sirius.diagram.description.style.SquareDescription
import org.eclipse.sirius.diagram.description.tool.DeleteElementDescription
import org.eclipse.sirius.diagram.description.tool.DoubleClickDescription
import org.eclipse.sirius.viewpoint.description.AbstractVariable
import org.eclipse.sirius.viewpoint.description.IdentifiedElement
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping
import org.eclipse.sirius.viewpoint.description.SystemColor
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription
import org.eclipse.sirius.viewpoint.description.tool.ContainerModelOperation
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionParameter
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation
import org.eclipse.sirius.viewpoint.description.tool.OperationAction
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription
import org.eclipse.sirius.viewpoint.description.tool.SetValue
import org.mypsycho.modit.emf.sirius.api.AbstractDiagram

import static extension org.mypsycho.modit.emf.sirius.api.SiriusDesigns.*

/**
 * Abstract class for all default value for POOSL diagrams.
 *
 * @author nperansin
 * @since 2021-07-22
 */
abstract class PooslDiagram extends AbstractDiagram {
	
	enum Color {
		white, black, blue, chocolate, gray, green, red, yellow, purple, orange
	}
	
	public val ICON_BASEPATH = "/org.eclipse.poosl.model.edit/icons/full/"
	
	public val DEFAULT_ICON_PATH = ICON_BASEPATH + "obj16/"
	public val EXTRA_ICON_PATH = ICON_BASEPATH + "custo16/"
	public val TOOL_ICON_PATH = ICON_BASEPATH + "ctool16/"
	
	
	/**
	 * Creates a factory for a diagram description
	 * 
	 * @param parent of diagram
	 */
	new(PooslDesign parent, String dLabel, Class<? extends EObject> dClass) {
		super(parent, dLabel, dClass)
	}
	
	override initDefaultEdgeStyle(EdgeStyleDescription it) {
		super.initDefaultEdgeStyle(it)
		
		// TODO Comment why "1" is not the applicable value.
		sizeComputationExpression = "2" // By default in all diagrams
	}
	
	override initDefaultStyle(BasicLabelStyleDescription it) {
		super.initDefaultStyle(it)
		labelSize = 9 // default of Eclipse is 
					
		labelExpression = "feature:name" // Ecore default value
		
		// Built-in value for square, probably improper
		if (it instanceof SquareDescription) {
			borderSizeComputationExpression = "0"
		}
	}

	def param(ExternalJavaAction it, String paramName, String paramValue) {
		parameters += ExternalJavaActionParameter.create [
			name = paramName
			value = paramValue
		]
	}
	
	override initContent(DiagramDescription it) {
		super.initContent(it)
		i18n = "def".id("name")
	}


	def <R extends AbstractVariable> R named(Class<R> type, String varName) {
		type.create [
			name = varName
		]
	}
	
		
	/**
	 * Creates an {@link IdentifiedElement} and initializes it.
	 * 
	 * @param type of IdentifiedElement to instantiate
	 * @param cat category of element
	 * @param eName name of element
	 * @param initializer of the given {@link EObject}
	 */
	override <R extends IdentifiedElement> R createAs(Class<R> type, Enum<?> cat, String eName, (R)=>void init) {
		super.createAs(type, cat, eName) [
			i18n = cat.id(eName)
			name = cat.name + "_" + eName
			init?.apply(it)
		]
	}
	
	override protected id(String category, String path) {
		super.id(category, path)
	}
	
	def protected setI18n(IdentifiedElement it, String key) {
		if (!(it instanceof RepresentationElementMapping)) {
			(context as PooslDesign).i18n(it, key)	
		} // else mapping are passive, no need have displayed text
	}
	
	
	def setValue(ContainerModelOperation it, String feat, String aqlExpr) {
		subModelOperations += SetValue.create [
			featureName = feat
			valueExpression = aqlExpr.trimAql
		]
	}

    
	// TODO: update AbstractDiagram
	override setOperation(AbstractToolDescription it, ModelOperation value) {
		switch(it) {
			OperationAction: initialOperation = value.toTool
			DeleteElementDescription: initialOperation = value.toTool
			DoubleClickDescription: initialOperation = value.toTool
			PasteDescription: initialOperation = value.toTool
			default: super.setOperation(it, value)
		}	
	}
	
	
	// TODO: update to AbstractEdition
	def asAql(Class<? extends EObject> type) {
		context.asEClass(type).asAql
	}
		
	def callJavaAction(String actionName, String actionCall, Pair<String, String>... params) {
		ExternalJavaAction.create [
			id = "externalcall"
			name = actionName
			forceRefresh = true
			param("action", actionCall)			
			params.forEach[ p |
				param(p.key, p.value)
			]
		]
	}
	
	
	private def ref(Color it, String dim) {
		SystemColor.extraRef('''color:«name»«dim»''')
	}
	
	def ref(Color it) {
		SystemColor.extraRef("color:" + it.name)
	}

	def lightRef(Color it) {
		ref("_light")
	}
	
	def darkRef(Color it) {
		ref("_dark")
	}
	
}
