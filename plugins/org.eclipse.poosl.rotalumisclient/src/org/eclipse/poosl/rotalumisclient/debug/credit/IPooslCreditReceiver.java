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
package org.eclipse.poosl.rotalumisclient.debug.credit;

/**
 * The IPooslCreditReceiver.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public interface IPooslCreditReceiver {
    void receiveCredits(int credits);
}
