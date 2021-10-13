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

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.internal.ui.views.variables.VariablesView;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.poosl.rotalumisclient.PooslConstants;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

/**
 * The StackTraceVariableView.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
@SuppressWarnings("restriction")
public class StackTraceVariableView extends VariablesView {

    private final IDebugEventSetListener debugEventSetListener = events -> {
        final Viewer viewer = getViewer();
        if (viewer == null) {
            return; // view is not ready
        }
        Object inspectData = null;
        for (DebugEvent debugEvent : events) {
            if (debugEvent.getKind() == DebugEvent.MODEL_SPECIFIC //
                    && debugEvent.getDetail() == PooslConstants.STACKFRAME_INSPECT) {
                inspectData = debugEvent.getData();
            }
        }
        if (inspectData != null) {
            Object newInput = inspectData;
            Display.getDefault().asyncExec(() -> setViewerInput(newInput));
        }

    };

    @Override
    public void createPartControl(Composite parent) {
        super.createPartControl(parent);

        DebugPlugin plugin = DebugPlugin.getDefault();
        if (plugin != null) {
            plugin.addDebugEventListener(debugEventSetListener);
        }

        IWorkbench workbench = PlatformUI.getWorkbench();
        if (workbench != null) {
            workbench.getHelpSystem().setHelp(parent, //
                    "org.eclipse.poosl.help.help_stacktrace_variables"); //$NON-NLS-1$
        }
    }

    @Override
    public Viewer createViewer(Composite parent) {
        Viewer viewer = super.createViewer(parent);
        // On selection change send out an model specific debug event
        // for a inspect request for the selected element.

        viewer.addSelectionChangedListener(event -> sendInspectDebugEvent(event.getSelection()));

        return viewer;
    }

    private void sendInspectDebugEvent(Object source) {
        DebugPlugin plugin = DebugPlugin.getDefault();
        if (plugin != null) {
            plugin.fireDebugEventSet(new DebugEvent[] { //
                    new DebugEvent(source, DebugEvent.MODEL_SPECIFIC, PooslConstants.INSPECT_REQUEST) });
        }
    }

    @Override
    protected void setViewerInput(Object context) {
        // Cell modifier is built for each input
        super.setViewerInput(context);

        // Rotalumis does not support change here
        // but PooslVariable does not know the context from Execution Tree.
        ((TreeViewer) getViewer()).setCellModifier(null);
    }

    @Override
    public void dispose() {
        DebugPlugin plugin = DebugPlugin.getDefault();
        if (plugin != null) {
            plugin.removeDebugEventListener(debugEventSetListener);
        }
        super.dispose();
    }
}
