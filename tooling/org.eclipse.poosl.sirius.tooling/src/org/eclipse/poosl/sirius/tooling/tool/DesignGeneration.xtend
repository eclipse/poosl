package org.eclipse.poosl.sirius.tooling.tool

import java.nio.file.Paths
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.XMIResource
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
	
	@Test
    def void writeODesign() {

        val res = new ResourceSetImpl()
        	.createResource(URI.createFileURI(ODESIGN_FILE.toString
        		
        ))
        val content = TARGET_CLASS.getDeclaredConstructor().newInstance().loadContent(res).head
                                
        res.save(#{
        	XMIResource.OPTION_ENCODING -> "UTF-8"
        });
        println(ODESIGN_FILE.fileName + " is updated.")
        println(">>" + ODESIGN_FILE)
        
		new SiriusReverseIt(
			content, 
			Paths.get(GENREVERSE_PATH), 
			TARGET_CLASS.name
		).perform
        
    }

}
