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

import org.eclipse.core.runtime.jobs.ISchedulingRule;

/**
 * The DiagramEditScheduler.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class DiagramEditScheduler implements ISchedulingRule {

    @Override
    public boolean contains(ISchedulingRule rule) {
        return rule instanceof DiagramEditScheduler;
    }

    @Override
    public boolean isConflicting(ISchedulingRule rule) {
        return rule instanceof DiagramEditScheduler;
    }
}
