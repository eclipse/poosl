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
package org.eclipse.poosl.sirius.debug;

/**
 * The PathCounter.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PathCounter {
    private final String pathId;

    private int in;

    private int out;

    public PathCounter(String pathId) {
        super();
        this.pathId = pathId;
        this.in = 0;
        this.out = 0;
    }

    public void addIncoming() {
        in++;
    }

    public void addOutgoing() {
        out++;
    }

    public int getIn() {
        return in;
    }

    public int getOut() {
        return out;
    }

    public String getPathId() {
        return pathId;
    }
}
