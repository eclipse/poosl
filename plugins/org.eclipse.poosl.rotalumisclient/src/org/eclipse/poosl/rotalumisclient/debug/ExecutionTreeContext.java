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
package org.eclipse.poosl.rotalumisclient.debug;

import org.eclipse.poosl.generatedxmlclasses.TExecutiontreeBase;

/**
 * The ExecutionTreeContext.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class ExecutionTreeContext {
    private final PooslThread context;

    private final TExecutiontreeBase executiontreeBase;

    public ExecutionTreeContext(PooslThread context, TExecutiontreeBase executiontreeBase) {
        this.context = context;
        this.executiontreeBase = executiontreeBase;
    }

    public PooslThread getContext() {
        return context;
    }

    public TExecutiontreeBase getExecutiontreeBase() {
        return executiontreeBase;
    }
}
