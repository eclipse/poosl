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

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.poosl.generatedxmlclasses.TErrorStackframe;
import org.eclipse.poosl.rotalumisclient.sourcemapping.PooslSourceMap;
import org.eclipse.poosl.rotalumisclient.sourcemapping.PooslSourceMapping;

/**
 * The StackTraceLabelProvider.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class StackTraceLabelProvider extends LabelProvider {

    @Override
    public String getText(Object element) {
        if (element instanceof TErrorStackframe) {
            TErrorStackframe stackframe = (TErrorStackframe) element;
            return stackframe.getId().toString() + ' ' + stackframe.getMethod();
        } else if (element instanceof StackFrameMapping) {
            StackFrameMapping frameMapping = (StackFrameMapping) element;
            String mappingInfo = ""; //$NON-NLS-1$

            try {
                PooslSourceMapping mapping = frameMapping.getMapping();
                if (mapping != null && mapping != PooslSourceMap.EMPTY_MAPPING) {
                    Path path = new Path(mapping.getFilePath());
                    mappingInfo = "(" + path.segment(path.segmentCount() - 1) + ":" //$NON-NLS-1$//$NON-NLS-2$
                            + mapping.getLineNumber() + ")"; //$NON-NLS-1$
                }
            } catch (Exception e) {
                // do nothing
            }
            return frameMapping.getFrame().getId().toString() + ' '
                    + frameMapping.getFrame().getMethod() + ' ' + mappingInfo;
        }
        return super.getText(element);
    }
}
