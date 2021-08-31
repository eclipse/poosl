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
package org.eclipse.poosl.sirius.layout;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.format.IFormatDataManagerProvider;
import org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager;

/**
 * Provides the {@link PooslFormatDataManager} for sirius's {@link DDiagram}.
 * 
 * @author Koen Staal
 */
public class PooslFormatDataManagerProvider implements IFormatDataManagerProvider {
    public PooslFormatDataManagerProvider() {
        // Nothing.
    }

    @Override
    public boolean provides(DDiagram diagram) {
        return true;
    }

    @Override
    public SiriusFormatDataManager getFormatDataManager() {
        return new PooslFormatDataManager();
    }
}
