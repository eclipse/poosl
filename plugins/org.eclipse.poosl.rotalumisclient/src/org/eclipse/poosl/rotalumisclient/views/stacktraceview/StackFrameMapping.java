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
package org.eclipse.poosl.rotalumisclient.views.stacktraceview;

import org.eclipse.poosl.generatedxmlclasses.TErrorStackframe;
import org.eclipse.poosl.rotalumisclient.sourcemapping.PooslSourceMapping;

/**
 * The StackFrameMapping.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class StackFrameMapping {
    private final PooslSourceMapping mapping;

    private final TErrorStackframe frame;

    public StackFrameMapping(TErrorStackframe frame, PooslSourceMapping mapping) {
        this.frame = frame;
        this.mapping = mapping;
    }

    public PooslSourceMapping getMapping() {
        return mapping;
    }

    public TErrorStackframe getFrame() {
        return frame;
    }

}
