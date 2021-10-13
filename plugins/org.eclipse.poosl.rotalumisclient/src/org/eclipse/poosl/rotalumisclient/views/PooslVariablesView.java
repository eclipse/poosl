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
package org.eclipse.poosl.rotalumisclient.views;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchListener;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.internal.ui.viewers.model.TreeModelContentProvider;
import org.eclipse.debug.internal.ui.viewers.model.provisional.TreeModelViewer;
import org.eclipse.debug.internal.ui.views.variables.VariablesView;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.poosl.rotalumisclient.PooslConstants;
import org.eclipse.poosl.rotalumisclient.debug.PooslDebugElement;
import org.eclipse.poosl.rotalumisclient.debug.PooslDebugTarget;
import org.eclipse.poosl.rotalumisclient.debug.PooslThread;
import org.eclipse.poosl.rotalumisclient.views.debugview.PooslDebugTreeItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

/**
 * The PooslVariablesView.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
@SuppressWarnings("restriction")
// This warning is here because of the reuse of certain internal debugging
// classes of eclipse
public class PooslVariablesView extends VariablesView {
    private static final Logger LOGGER = Logger.getLogger(PooslVariablesView.class.getName());

    private PooslThread input;

    private final IDebugEventSetListener debugEventSetListener = events -> {
        for (DebugEvent debugEvent : events) {
            handleDebugEvent(debugEvent);
        }
    };

    private final ILaunchListener launchListener = new ILaunchListener() {
        @Override
        public void launchAdded(ILaunch launch) {
            // do nothing
        }

        @Override
        public void launchChanged(ILaunch launch) {
            // do nothing
        }

        @Override
        public void launchRemoved(ILaunch launch) {
            cleanLaunch(launch);
        }

    };

    private void handleDebugEvent(DebugEvent debugEvent) {
        if (debugEvent.getSource() instanceof PooslThread) {
            handleThreadEvent(debugEvent);
        } else if (debugEvent.getSource() instanceof PooslDebugTarget) {
            handleTargetEvent(debugEvent);
        } else if (debugEvent.getSource() == null || debugEvent.getSource() instanceof PooslDebugTreeItem) {
            String secondaryId = getViewSite().getSecondaryId();
            if (secondaryId == null) {
                Display.getDefault().asyncExec(() -> clearViewerInput());
            }
        } // else do nothing
    }

    private void handleTargetEvent(DebugEvent debugEvent) {
        try {
            if (debugEvent.getKind() == DebugEvent.RESUME || debugEvent.getKind() == DebugEvent.TERMINATE) {
                Display.getDefault().asyncExec(() -> clearViewerInput());
            } else {
                String secondaryId = getViewSite().getSecondaryId();
                // Clear default variables View when debugtarget is selected
                if (secondaryId != null) {
                    String debugTargetName = ViewHelper.getDebugTargetName(secondaryId);
                    Object source = debugEvent.getSource();
                    // Its the correct debug target
                    if (source instanceof PooslDebugTarget //
                            && ((PooslDebugTarget) source).getName().equals(debugTargetName) //
                            && (debugEvent.getKind() == DebugEvent.SUSPEND //
                                    || debugEvent.getKind() == DebugEvent.CHANGE)) {
                        Display.getDefault().asyncExec(() -> setViewerInput(ViewHelper.getThreadFromEvent(null, debugEvent, secondaryId)));
                    }
                }
            }
        } catch (DebugException e) {
            LOGGER.log(Level.WARNING, "Could not get the name of the debug target past by a debug event.", e);
        }
    }

    private void handleThreadEvent(DebugEvent debugEvent) {
        if (debugEvent.getKind() == DebugEvent.MODEL_SPECIFIC //
                && debugEvent.getDetail() == PooslConstants.INSPECT_RECEIVED) {
            Object source = debugEvent.getSource();
            if (source == input) {
                Display.getDefault().asyncExec(() -> setViewerInput(source));
            }
        }
    }

    @Override
    public void createPartControl(Composite parent) {
        super.createPartControl(parent);
        IWorkbench workbench = PlatformUI.getWorkbench();
        if (workbench != null) {
            workbench.getHelpSystem().setHelp(parent, "org.eclipse.poosl.help.help_variables"); //$NON-NLS-1$
        }
        DebugPlugin plugin = DebugPlugin.getDefault();
        if (plugin != null) {
            ILaunchManager launchManager = plugin.getLaunchManager();
            if (launchManager != null) {
                launchManager.addLaunchListener(launchListener);
            }
            plugin.addDebugEventListener(debugEventSetListener);
        }
    }

    private void cleanLaunch(ILaunch launch) {
        boolean clear = false;
        IDebugTarget debugTarget = launch.getDebugTarget();
        if (debugTarget != null) {
            if (getViewSite().getSecondaryId() == null) {
                if (getViewer() != null && getViewer().getInput() instanceof IStackFrame) {
                    IStackFrame stackframe = (IStackFrame) getViewer().getInput();
                    clear = stackframe != null //
                            && stackframe.getDebugTarget() != null //
                            && stackframe.getDebugTarget() == debugTarget;
                }
            } else {
                clear = ViewHelper.isTargetID(launch.getDebugTarget(), getViewSite().getSecondaryId());
            }
        }
        if (clear) {
            PlatformUI.getWorkbench().getDisplay().asyncExec(() -> {
                // Removes references from PooslVariable/PooslValue to
                // PooslDebugTarget
                Viewer viewer = getViewer();
                if (viewer instanceof TreeModelViewer) {
                    TreeModelViewer tViewer = (TreeModelViewer) viewer;
                    tViewer.setContentProvider(new TreeModelContentProvider());
                }

                clearViewerInput();
                getViewer().setSelection(new TreeSelection());
                input = null;
            });
        }
        launch.removeDebugTarget(debugTarget);
    }

    @Override
    public Viewer createViewer(Composite parent) {
        TreeModelViewer viewer = (TreeModelViewer) super.createViewer(parent);
        viewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                sendInspectDebugEvent(event.getSelection());
            }
        });
        viewer.addTreeListener(new ITreeViewerListener() {
            @Override
            public void treeExpanded(TreeExpansionEvent event) {
                sendInspectDebugEvent(event.getElement());
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent event) {
                // do nothing
            }
        });

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
        if (context instanceof PooslThread) {
            PooslThread thread = (PooslThread) context;
            input = thread;
            if (ViewHelper.isThreadID(thread, getViewSite().getSecondaryId())) {
                IStackFrame stackFrame = thread.getStackFrame();
                super.setViewerInput(stackFrame != null ? stackFrame : context);
            }
        } else if (context instanceof PooslDebugElement && getViewSite().getSecondaryId() == null) {
            super.setViewerInput(null);
        }
    }

    private void clearViewerInput() {
        super.setViewerInput(null);
    }

    @Override
    public void dispose() {
        DebugPlugin plugin = DebugPlugin.getDefault();
        if (plugin != null) {
            ILaunchManager launchManager = plugin.getLaunchManager();
            if (launchManager != null) {
                launchManager.removeLaunchListener(launchListener);
            }
            plugin.removeDebugEventListener(debugEventSetListener);

        }
        input = null;
        super.dispose();
    }

}
