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
package org.eclipse.poosl.sirius.tooling.tool

import java.nio.file.Paths
import org.eclipse.core.runtime.Platform
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.XMIResource
import org.eclipse.poosl.sirius.Activator
import org.eclipse.sirius.diagram.description.AdditionalLayer
import org.eclipse.sirius.diagram.description.filter.FilterDescription
import org.eclipse.sirius.diagram.description.tool.DoubleClickDescription
import org.eclipse.sirius.diagram.description.tool.ToolSection
import org.eclipse.sirius.viewpoint.description.IdentifiedElement
import org.eclipse.sirius.viewpoint.description.RepresentationDescription
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping
import org.eclipse.sirius.viewpoint.description.Viewpoint
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionCall
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry
import org.junit.Test
import org.mypsycho.modit.emf.sirius.tool.SiriusReverseIt

/**
 * Tool to generate Sirius design model in 'sirius' plugin.
 * <p>
 * Tool also reverse the created model to allows round-trip when design file is modified
 * directly.
 * </p>
 *
 * @author nperansin
 */
class DesignGeneration extends DesignToolBase {

	protected static val GENREVERSE_PATH = "target/gen"
	val i18n = Platform.getResourceBundle(Activator.^default.bundle)
	
	static val DISPLAYED_CLASSES = #{
		ToolSection, ToolEntry, RepresentationDescription, // common
		Viewpoint, AdditionalLayer, FilterDescription // diagram
		// no table
	}
	
	@Test
    def void writeODesign() {

        val res = new ResourceSetImpl()
        	.createResource(URI.createFileURI(ODESIGN_FILE.toString))
        val content = TARGET_CLASS.getDeclaredConstructor().newInstance().loadContent(res).head
                                
        res.save(#{
        	XMIResource.OPTION_ENCODING -> "UTF-8"
        });
        println(ODESIGN_FILE.fileName + " is updated.")
        println(">>" + ODESIGN_FILE)
        
		println("\n### I18N ###\n")
	 	
 		// All elements
	 	(#[ content ].iterator + content.eAllContents)
		 	.filter(IdentifiedElement)
		 	.filter[ // Such types are not interesting.
		 		// in Poosl, external actions are never used at top level
 		 		!(it instanceof ExternalJavaAction
	 			|| it instanceof ExternalJavaActionCall
	 			|| it instanceof DoubleClickDescription
	 			|| it instanceof RepresentationElementMapping)
		 	]
		 	// regular case: no need to trace
		 	.filter[ !(i18nRequired && i18nDefined) ]
	 		.forEach[
		 		println(
		 			'''«name» -> « //
		 			IF label !== null && !label.empty
		 				»«label»=«i18nLabel»«
		 			ELSE
		 				»<«eClass.name»> <anonymous>«
		 			ENDIF
		 			»«
		 			IF i18nRequired && !i18nDefined
		 				»!!!«
		 			ENDIF
		 			»'''
		 		)
		 	]
        
		new SiriusReverseIt(
			content, 
			Paths.get(GENREVERSE_PATH), 
			TARGET_CLASS.name
		).perform;
		

    }
    
    def isI18nRequired(IdentifiedElement value) {
    	DISPLAYED_CLASSES.exists[
    		isInstance(value)
    	]
    }
    
    def isI18nDefined(IdentifiedElement it) {
    	label !== null 
    		&& label.startsWith("%") 
    		&& i18n.containsKey(label.substring(1))
    }

	def getI18nLabel(IdentifiedElement it) {
		if (!label.startsWith("%")) {
			return " /!\\"
		}
		val key = label.substring(1)
		if (i18n.containsKey(key)) 
			i18n.getString(key)
		else
			"<undefined>"
		
	}

}
