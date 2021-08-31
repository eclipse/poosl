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

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.poosl.generatedxmlclasses.TEengineEventErrorResponse;

/**
 * The StackTraceContentProvider.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class StackTraceContentProvider implements IStructuredContentProvider {

    @Override
    public void dispose() {
        // do nothing
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // do nothing
    }

    @Override
    public Object[] getElements(Object input) {
        if (input instanceof TEengineEventErrorResponse) {
            TEengineEventErrorResponse error = (TEengineEventErrorResponse) input;
            return error.getStacktrace().getStackframe().toArray();
        } else if (input instanceof List) {
            return ((List<?>) input).toArray();
        }
        return null;
    }
}
