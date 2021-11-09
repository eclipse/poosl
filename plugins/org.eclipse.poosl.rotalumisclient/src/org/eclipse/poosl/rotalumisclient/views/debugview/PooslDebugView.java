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
package org.eclipse.poosl.rotalumisclient.views.debugview;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchListener;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.ui.contexts.DebugContextEvent;
import org.eclipse.debug.ui.contexts.IDebugContextListener;
import org.eclipse.debug.ui.contexts.IDebugContextProvider;
import org.eclipse.debug.ui.contexts.IDebugContextService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.poosl.rotalumisclient.Activator;
import org.eclipse.poosl.rotalumisclient.Messages;
import org.eclipse.poosl.rotalumisclient.PooslConstants;
import org.eclipse.poosl.rotalumisclient.debug.PooslDebugElement;
import org.eclipse.poosl.rotalumisclient.debug.PooslDebugTarget;
import org.eclipse.poosl.rotalumisclient.debug.PooslThread;
import org.eclipse.poosl.rotalumisclient.extension.ExternSelectionInformer;
import org.eclipse.poosl.rotalumisclient.views.PooslProcessStep;
import org.eclipse.poosl.rotalumisclient.views.ViewHelper;
import org.eclipse.poosl.rotalumisclient.views.WindowCreater;
import org.eclipse.poosl.xtext.GlobalConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

/**
 * The Debug view is used when debugging a POOSL model. It shows all models that
 * you are debugging and the containing
 * poosl processes. Every launched debugtarget represents a model and every
 * thread in the debugtarget represents a poosl
 * process. When selecting a node in the Debug view, the Execution Tree view is
 * updated. When double clicking on a
 * process instance (a leaf of the tree), a process step is performed, which
 * advances the simulation to the next
 * transition of the process, and executes a single next transition.
 *
 * @author Koen Staal
 */
public class PooslDebugView extends ViewPart implements
        IDebugContextProvider,
        IDebugContextListener {
    /** The HELP_ID. */
    public static final String HELP_ID = "org.eclipse.poosl.help.help_debug"; //$NON-NLS-1$

    private static final ILog LOGGER = Platform.getLog(PooslDebugView.class);

    private static final int TREE_EXPANSION = 3;

    private ISelection selection;

    private final ListenerList<IDebugContextListener> listenerList = new ListenerList<>();

    private TreeViewer treeViewer;

    private boolean update;

    final ILaunchListener launchListener = new ILaunchListener() {
        @Override
        public void launchRemoved(ILaunch launch) {
            handleLaunchRemoved(launch);
        }

        @Override
        public void launchChanged(ILaunch launch) {
            if (launch.getDebugTarget() != null) {
                handleInputChanged();
            }
        }

        @Override
        public void launchAdded(ILaunch launch) {
            handleInputChanged();
        }
    };

    final IDebugEventSetListener debugEventListener = events -> {
        for (DebugEvent debugEvent : events) {
            final Object source = debugEvent.getSource();
            if (source instanceof PooslDebugTarget) {
                // POOSL Model
                if (debugEvent.getKind() == DebugEvent.SUSPEND
                        || debugEvent.getKind() == DebugEvent.RESUME
                        || debugEvent.getKind() == DebugEvent.TERMINATE
                        || (debugEvent.getKind() == DebugEvent.MODEL_SPECIFIC
                                && debugEvent.getDetail() == PooslConstants.STOPPED_STATE)) {
                    PlatformUI.getWorkbench().getDisplay().asyncExec(() -> {
                        notifyListeners();
                        treeViewer.refresh();
                    });
                } else if (debugEvent.getKind() == DebugEvent.CHANGE) {
                    PlatformUI.getWorkbench().getDisplay().asyncExec(() -> treeViewer.refresh());
                }
            } else if (source instanceof PooslThread) {
                // POOSL Process
                if (debugEvent.getKind() == DebugEvent.MODEL_SPECIFIC
                        && (debugEvent.getDetail() == PooslConstants.BREAKPOINT_HIT
                                || debugEvent.getDetail() == PooslConstants.ERROR_STATE)) {
                    PlatformUI.getWorkbench().getDisplay()
                            .asyncExec(new SelectThreadRunnable((PooslThread) source));
                }
            } else {
                // do nothing
            }
        }
    };

    @Override
    public void createPartControl(Composite parent) {
        treeViewer = new TreeViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL);
        treeViewer.setContentProvider(new PooslDebugContentProvider());
        treeViewer.setLabelProvider(new PooslDebugLabelProvider());
        /*
         * Sets the selection variable and notifies listeners unless update tree is
         * false. This method will set updatetree always to true
         */
        treeViewer.addSelectionChangedListener(event -> {
            if (!event.getSelection().isEmpty()) {
                selection = event.getSelection();
                if (update) {
                    notifyListeners();
                }
                update = true;
            }
        });
        treeViewer.addDoubleClickListener(evt -> {
            Object obj = ((TreeSelection) evt.getSelection()).getFirstElement();
            if (obj instanceof PooslThread) {
                PooslProcessStep.doProcessStep((PooslThread) obj);
            }
        });
        DebugPlugin plugin = DebugPlugin.getDefault();
        if (plugin != null) {
            ILaunchManager launchManager = plugin.getLaunchManager();
            if (launchManager != null) {
                launchManager.addLaunchListener(launchListener);
                treeViewer.setInput(launchManager);
                treeViewer.expandToLevel(TREE_EXPANSION);
            }
            plugin.addDebugEventListener(debugEventListener);
        }

        IDebugContextService debugService = ViewHelper.getDebugService(this);
        if (debugService != null) {
            debugService.addDebugContextProvider(this);
        }

        update = true;
        treeViewer.refresh();
        IWorkbench workbench = PlatformUI.getWorkbench();
        if (workbench != null) {
            workbench.getHelpSystem().setHelp(parent, HELP_ID);
            // Suspicious: createPartControl is always in Display thread.
            workbench.getDisplay().asyncExec(this::notifyListeners);
        }
        updateContextMenu();
    }

    /**
     * Handles when launch is removed.
     * <p>
     * On debug, launch stop on target. <br/>
     * Update tree viewer and terminate process
     * </p>
     *
     * @param launch
     *     removed
     */
    protected void handleLaunchRemoved(ILaunch launch) {
        IDebugTarget target = launch.getDebugTarget();
        if (target instanceof PooslDebugTarget) {
            PooslDebugTarget pooslTarget = (PooslDebugTarget) target;
            pooslTarget.extentensionsInformStop();
        }

        IProcess[] processes = launch.getProcesses();
        DebugPlugin plugin = DebugPlugin.getDefault();
        if (plugin != null) {
            ILaunchManager launchManager = plugin.getLaunchManager();
            if (launchManager != null && launchManager.getLaunches().length == 0) {
                PlatformUI.getWorkbench().getDisplay().asyncExec(() -> {
                    for (Object listener : listenerList.getListeners()) {
                        DebugContextEvent event = new DebugContextEvent(PooslDebugView.this,
                                new TreeSelection(), DebugContextEvent.STATE);
                        ((IDebugContextListener) listener).debugContextChanged(event);
                    }
                });
            }
        }

        handleInputChanged();

        for (IProcess element : processes) {
            try {
                element.terminate();
            } catch (DebugException e) {
                LOGGER.warn("Process could not be terminated" + element.getLabel(), e);
            }
        }

    }

    @Override
    public void dispose() {
        DebugPlugin plugin = DebugPlugin.getDefault();
        if (plugin != null) {
            ILaunchManager launchManager = plugin.getLaunchManager();
            if (launchManager != null) {
                launchManager.removeLaunchListener(launchListener);
            }
            plugin.removeDebugEventListener(debugEventListener);
        }

        IDebugContextService debugService = ViewHelper.getDebugService(this);
        if (debugService != null) {
            debugService.removeDebugContextProvider(this);
        }
        super.dispose();
    }

    /**
     * Finds and selects thread in the debugview treeviewer
     *
     * @param thread
     *     The thread to be selected
     */
    private void selectThread(PooslThread thread) {
        try {
            Object[] segments = ((PooslDebugContentProvider) treeViewer.getContentProvider())
                    .getTreeSegments(thread);
            selection = new TreeSelection(new TreePath(segments));
            for (int i = 0; i < segments.length - 1; i++) {
                // setExpandedState calls the selectionlistener
                update = false;
                treeViewer.setExpandedState(segments[i], true);
            }
            update = true;
            treeViewer.setSelection(selection, true);
            treeViewer.refresh();
        } catch (DebugException e) {
            LOGGER.error("Fail to select thread", e);
        }
    }

    /**
     * Fires an event to IDebugContextListener with the selection in the
     * debugview treeviewer. if selection is null
     * select the debugtarget (root)
     */
    private void notifyListeners() {
        if (selection == null || (selection.isEmpty() && treeViewer.getTree().getItemCount() > 0)) {
            selectRoot();
        }
        if (selection != null) {
            for (Object listener : listenerList.getListeners()) {
                DebugContextEvent event = new DebugContextEvent(this, selection,
                        DebugContextEvent.ACTIVATED);
                ((IDebugContextListener) listener).debugContextChanged(event);
            }
        }
    }

    private void selectRoot() {
        // set selection on the root node of the treeViewer if there is no
        // active selection"

        if (treeViewer.getTree().getItemCount() > 0) {
            Object data = treeViewer.getTree().getItem(0).getData();
            selection = new TreeSelection(new TreePath(new Object[] { data }));
            treeViewer.setSelection(selection);
            treeViewer.expandToLevel(TREE_EXPANSION);
        } else {
            selection = new TreeSelection();
            treeViewer.setSelection(selection);
        }
    }

    class SelectThreadRunnable implements Runnable {
        private PooslThread thread;

        SelectThreadRunnable(PooslThread thread) {
            super();
            this.thread = thread;
        }

        @Override
        public void run() {
            selectThread(thread);
        }
    }

    class UpdateRunnable implements Runnable {
        @Override
        public void run() {
            treeViewer.refresh();
        }
    }

    private void handleInputChanged() {
        IWorkbench workbench = PlatformUI.getWorkbench();
        if (workbench == null) {
            return; // unexpected
        }

        workbench.getDisplay().asyncExec(() -> {

            Object[] expanded = treeViewer.getExpandedElements();
            DebugPlugin plugin = DebugPlugin.getDefault();
            if (plugin != null) {
                ILaunchManager launchManager = plugin.getLaunchManager();
                treeViewer.setInput(launchManager);
                update = false;
                treeViewer.setExpandedElements(expanded);
                treeViewer.expandToLevel(TREE_EXPANSION);
                update = true;

                List<IDebugTarget> targets = Arrays.asList(launchManager.getDebugTargets());
                Object selectionTarget = selection instanceof TreeSelection
                    ? ((TreeSelection) selection).getFirstElement() : null;

                if (selectionTarget instanceof PooslDebugElement) {
                    selectionTarget = ((PooslDebugElement) selectionTarget).getDebugTarget();
                }

                if (selectionTarget instanceof IDebugTarget && !targets.contains(selectionTarget)) {
                    selectRoot();
                }
                treeViewer.refresh();
            }
        });

    }

    @Override
    public void setFocus() {
        treeViewer.getTree().setFocus();
    }

    private void updateContextMenu() {
        final Tree tree = treeViewer.getTree();
        final Menu menu = new Menu(tree);
        menu.addMenuListener(MenuListener.menuShownAdapter(evt -> {
            MenuItem[] items = menu.getItems();
            for (MenuItem item : items) {
                item.dispose();
            }

            TreeItem[] selected = tree.getSelection();
            if (selected != null && selected.length > 0 && selected[0] instanceof TreeItem) {

                Object data = selected[0].getData();

                if (data instanceof PooslThread) {
                    final PooslThread thread = (PooslThread) data;
                    addProcessStepMenuItem(menu, thread);
                    addThreadWindowMenuItem(menu, thread);
                } else if (data instanceof PooslDebugTreeItem) {
                    PooslDebugTreeItem treeItem = (PooslDebugTreeItem) data;
                    // dont show on <adapters>
                    if (!(treeItem.getLevel() == 2
                            && !treeItem.getName().equals(GlobalConstants.POOSL_SYSTEM))) {
                        addDebugDiagramMenuItem(menu, treeItem);
                    }

                }
            }
        }));

        tree.setMenu(menu);
    }

    private void addProcessStepMenuItem(final Menu menu, final PooslThread thread) {
        addMenuItem(menu, Messages.ACTION_MENU_PROCESS_STEP, "icon_process_step.png", //$NON-NLS-1$
                () -> PooslProcessStep.doProcessStep(thread));

    }

    private void addDebugDiagramMenuItem(Menu menu, final Object treeItem) {
        addMenuItem(menu, Messages.ACTION_MENU_OPEN_DEBUG_DIAGRAM,
                "icon_open_communication_diagram.png", //$NON-NLS-1$
                () -> (new ExternSelectionInformer()).executeInformDebugSelection(treeItem));
    }

    public void addThreadWindowMenuItem(final Menu menu, final PooslThread thread) {
        addMenuItem(menu, Messages.ACTION_MENU_NEW_WINDOW, "icon_open_process_window.png", //$NON-NLS-1$
                () -> {
                    try {
                        WindowCreater.getWindowForThread(getSite(), thread);
                        selectThread(thread);
                    } catch (DebugException | PartInitException e) {
                        LOGGER.warn("Thread window could not be created.", e);
                    }
                });

    }

    private static Image getPluginIcon(String iconName) {
        if (iconName == null) {
            return null;
        }
        try {
            return ImageDescriptor.createFromURL(new URL(//
                    "platform:/plugin/" + //$NON-NLS-1$
                            Activator.PLUGIN_ID + //
                            "/icons/" + iconName)) //$NON-NLS-1$
                    .createImage();
        } catch (MalformedURLException e) {
            LOGGER.info("Illegal path for icon: " + iconName, e); //$NON-NLS-1$
        }
        return null;
    }

    private static void addMenuItem(final Menu menu, String text, String icon, Runnable action) {
        MenuItem menuNewWindow = new MenuItem(menu, SWT.NONE);
        menuNewWindow.setText(text);
        menuNewWindow.setImage(getPluginIcon(icon));
        menuNewWindow.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                action.run();
            }

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                action.run();
            }
        });
    }

    @Override
    public IWorkbenchPart getPart() {
        return this;
    }

    @Override
    public void addDebugContextListener(IDebugContextListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void removeDebugContextListener(IDebugContextListener listener) {
        listenerList.remove(listener);
    }

    @Override
    public ISelection getActiveContext() {
        return selection;
    }

    @Override
    public void debugContextChanged(DebugContextEvent event) {
        // adding postListener https://bugs.eclipse.org/bugs/show_bug.cgi?id=499528
        // implemented to avoid no listener error at
        // org.eclipse.debug.internal.ui.contexts.DebugWindowContextService.notify(DebugWindowContextService.java:227)
        // when registering as debugContextProvider
    }
}
