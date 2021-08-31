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
package org.eclipse.poosl.rotalumisclient.sourcemapping;

import org.eclipse.swt.widgets.Display;

/**
 * The PooslSourceMappingListener.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public abstract class PooslSourceMappingListener {
    private final boolean inUI;

    public PooslSourceMappingListener(boolean inUI) {
        this.inUI = inUI;
    }

    public void returnSourceMapping(final PooslSourceMapping mapping) {
        if (inUI) {
            Display display = Display.getDefault();
            if (display != null) {
                display.asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        requestedSourceMapping(mapping);
                    }
                });
            }
        } else {
            requestedSourceMapping(mapping);
        }
    }

    public abstract void requestedSourceMapping(PooslSourceMapping mapping);
}
