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

import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.ITerminate;
import org.eclipse.poosl.rotalumisclient.PooslConstants;

/**
 * The PooslDebugElement.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public abstract class PooslDebugElement extends PlatformObject implements IDebugElement, ITerminate {
    /**
     * The debug target of the element.
     */
    protected PooslDebugTarget target;

    public PooslDebugElement(PooslDebugTarget target) {
        super();
        this.target = target;
    }

    @Override
    public String getModelIdentifier() {
        return PooslConstants.DEBUG_MODEL_ID;
    }

    @Override
    public IDebugTarget getDebugTarget() {
        return target;
    }

    @Override
    public ILaunch getLaunch() {
        return getDebugTarget().getLaunch();
    }

    @Override
    public <T> T getAdapter(Class<T> adapter) {
        if (adapter == IDebugElement.class) {
            return adapter.cast(this);
        }
        return super.getAdapter(adapter);
    }

    public void fireEvent(DebugEvent event) {
        DebugPlugin plugin = DebugPlugin.getDefault();
        if (plugin != null) {
            plugin.fireDebugEventSet(new DebugEvent[] { event });
        }
    }

    public void fireCreationEvent() {
        fireEvent(new DebugEvent(this, DebugEvent.CREATE));
    }
}
