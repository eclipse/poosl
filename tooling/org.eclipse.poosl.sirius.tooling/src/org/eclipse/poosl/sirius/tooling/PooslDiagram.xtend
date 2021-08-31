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
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription
import org.eclipse.sirius.diagram.description.style.SquareDescription
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription
import org.mypsycho.modit.emf.sirius.api.AbstractDiagram
import org.mypsycho.modit.emf.sirius.api.AbstractGroup

/**
 * Abstract class for all default value for POOSL diagrams.
 *
 * @author nperansin
 * @since 2021-07-22
 */
abstract class PooslDiagram extends AbstractDiagram {
	
	/**
	 * Creates a factory for a diagram description
	 * 
	 * @param parent of diagram
	 */
	new(AbstractGroup parent, String dLabel, Class<? extends EObject> dClass) {
		super(parent, dLabel, dClass)
	}
	
	override initDefaultEdgeStyle(EdgeStyleDescription it) {
		super.initDefaultEdgeStyle(it)
		
		// TODO Comment why "1" is not the applicable value.
		sizeComputationExpression = "2" // By default in all diagrams
	}
	
	override initDefaultStyle(BasicLabelStyleDescription it) {
		super.initDefaultStyle(it)
		
		// Built-in value for square, probably improper
		if (it instanceof SquareDescription) {
			labelSize = 8
			borderSizeComputationExpression = "0"
		}
	}

	
}
