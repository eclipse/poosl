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
package org.eclipse.poosl.rotalumisclient.views.diagram;

/**
 * The PooslDrawableDiagramMessage.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslDrawableDiagramMessage {
    private int serialNumber;

    private final String content;

    private final int from;

    private final int to;

    private final PooslDiagramMessage sourceMessage;

    public PooslDrawableDiagramMessage(PooslDiagramMessage sourceMessage, int from, int to) {
        this.sourceMessage = sourceMessage;
        this.content = sourceMessage.getPayload();
        this.from = from;
        this.to = to;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getContent() {
        return content;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public PooslDiagramMessage getSourceMessage() {
        return sourceMessage;
    }
}
