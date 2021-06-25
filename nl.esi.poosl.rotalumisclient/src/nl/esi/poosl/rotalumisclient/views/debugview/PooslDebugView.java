package nl.esi.poosl.rotalumisclient.views.debugview;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.ListenerList;
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
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import nl.esi.poosl.rotalumisclient.Messages;
import nl.esi.poosl.rotalumisclient.PooslConstants;
import nl.esi.poosl.rotalumisclient.RotalumisConstants;
import nl.esi.poosl.rotalumisclient.debug.PooslDebugElement;
import nl.esi.poosl.rotalumisclient.debug.PooslDebugTarget;
import nl.esi.poosl.rotalumisclient.debug.PooslThread;
import nl.esi.poosl.rotalumisclient.extension.ExternSelectionInformer;
import nl.esi.poosl.rotalumisclient.views.PooslProcessStep;
import nl.esi.poosl.rotalumisclient.views.ViewHelper;
import nl.esi.poosl.rotalumisclient.views.WindowCreater;

/**
 * The Debug view is used when debugging a POOSL model. It shows all models that
 * you are debugging and the containing poosl processes. Every launched
 * debugtarget represents a model and every thread in the debugtarget represents
 * a poosl process. When selecting a node in the Debug view, the Execution Tree
 * view is updated. When double clicking on a process instance (a leaf of the
 * tree), a process step is performed, which advances the simulation to the next
 * transition of the process, and executes a single next transition.
 * 
 * @author staalk
 */
public class PooslDebugView extends ViewPart implements IDebugContextProvider, IDebugContextListener {
	private static final Logger LOGGER = Logger.getLogger(PooslDebugView.class.getName());
	private static final int TREE_EXPANSION = 3;
	private ISelection selection;
	private final ListenerList<IDebugContextListener> listenerList = new ListenerList<>();
	private TreeViewer treeViewer;
	private boolean update;

	@Override
	public void createPartControl(Composite parent) {
		treeViewer = new TreeViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		treeViewer.setContentProvider(new PooslDebugContentProvider());
		treeViewer.setLabelProvider(new PooslDebugLabelProvider());
		treeViewer.addSelectionChangedListener(treeSelectionChangedListener);
		treeViewer.addDoubleClickListener(PooslProcessStep.doubleClickListener);
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
			workbench.getHelpSystem().setHelp(parent, "nl.esi.poosl.help.help_debug");
			workbench.getDisplay().asyncExec(new NotifyListenersRunnable());
		}
		updateContextMenu();
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

	ILaunchListener launchListener = new ILaunchListener() {
		@Override
		public void launchRemoved(ILaunch launch) {
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
					PlatformUI.getWorkbench().getDisplay().asyncExec(new ResetListenersRunnable());
				}
			}

			IWorkbench workbench = PlatformUI.getWorkbench();
			if (workbench != null) {
				workbench.getDisplay().asyncExec(new InputChangedRunnable());
			}

			for (int i = 0; i < processes.length; i++) {
				try {
					processes[i].terminate();
				} catch (DebugException e) {
					LOGGER.log(Level.WARNING, "Process could not be terminated" + processes[i].getLabel(),
							e.getSuppressed());
				}
			}
		}

		@Override
		public void launchChanged(ILaunch launch) {
			if (launch.getDebugTarget() != null) {
				IWorkbench workbench = PlatformUI.getWorkbench();
				if (workbench != null) {
					workbench.getDisplay().asyncExec(new InputChangedRunnable());
				}
			}
		}

		@Override
		public void launchAdded(ILaunch launch) {
			IWorkbench workbench = PlatformUI.getWorkbench();
			if (workbench != null) {
				workbench.getDisplay().asyncExec(new InputChangedRunnable());
			}
		}
	};

	IDebugEventSetListener debugEventListener = new IDebugEventSetListener() {
		@Override
		public void handleDebugEvents(DebugEvent[] events) {
			for (DebugEvent debugEvent : events) {
				final Object source = debugEvent.getSource();
				if (source instanceof PooslDebugTarget) {
					// POOSL Model
					if (debugEvent.getKind() == DebugEvent.SUSPEND || debugEvent.getKind() == DebugEvent.RESUME
							|| debugEvent.getKind() == DebugEvent.TERMINATE
							|| (debugEvent.getKind() == DebugEvent.MODEL_SPECIFIC
									&& debugEvent.getDetail() == PooslConstants.STOPPED_STATE)) {
						PlatformUI.getWorkbench().getDisplay().asyncExec(new NotifyListenersRunnable());
						PlatformUI.getWorkbench().getDisplay().asyncExec(new UpdateRunnable());
					} else if (debugEvent.getKind() == DebugEvent.CHANGE) {
						PlatformUI.getWorkbench().getDisplay().asyncExec(new UpdateRunnable());
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
		}
	};

	/**
	 * Find and select thread in the debugview treeviewer
	 * 
	 * @param thread The thread to be selected
	 */
	private void selectThread(PooslThread thread) {
		try {
			Object[] segments = ((PooslDebugContentProvider) treeViewer.getContentProvider()).getTreeSegments(thread);
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
			LOGGER.log(Level.SEVERE, e.getMessage(), e.getSuppressed());
		}
	}

	/**
	 * Sets the selection variable and notifies listeners unless update tree is
	 * false. This method will set updatetree always to true
	 */
	ISelectionChangedListener treeSelectionChangedListener = new ISelectionChangedListener() {
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			if (!event.getSelection().isEmpty()) {
				selection = event.getSelection();
				if (update) {
					notifyListeners();
				}
				update = true;
			}
		}
	};

	class NotifyListenersRunnable implements Runnable {
		@Override
		public void run() {
			notifyListeners();
		}
	}

	/**
	 * Fires an event to IDebugContextListener with the selection in the debugview
	 * treeviewer. if selection is null select the debugtarget (root)
	 */
	private void notifyListeners() {
		if (selection == null || (selection.isEmpty() && treeViewer.getTree().getItemCount() > 0)) {
			selectRoot();
		}
		if (selection != null) {
			for (Object listener : listenerList.getListeners()) {
				DebugContextEvent event = new DebugContextEvent(this, selection, DebugContextEvent.ACTIVATED);
				((IDebugContextListener) listener).debugContextChanged(event);
			}
		}
	}

	class ResetListenersRunnable implements Runnable {
		@Override
		public void run() {
			for (Object listener : listenerList.getListeners()) {
				DebugContextEvent event = new DebugContextEvent(PooslDebugView.this, new TreeSelection(),
						DebugContextEvent.STATE);
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

		public SelectThreadRunnable(PooslThread thread) {
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

	class InputChangedRunnable implements Runnable {
		@Override
		public void run() {
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
				if (selection instanceof TreeSelection) {
					Object element = ((TreeSelection) selection).getFirstElement();
					if (element instanceof IDebugTarget) {
						if (!targets.contains(element)) {
							selectRoot();
						}
					} else if (element instanceof PooslDebugElement) {
						IDebugTarget debugTarget = ((PooslDebugElement) element).getDebugTarget();
						if (!targets.contains(debugTarget)) {
							selectRoot();
						}
					}
				}
				treeViewer.refresh();
			}
		}
	}

	@Override
	public void setFocus() {
		treeViewer.getTree().setFocus();
	}

	private void updateContextMenu() {
		final Tree tree = treeViewer.getTree();
		final Menu menu = new Menu(tree);
		menu.addMenuListener(menuAdapter(tree, menu));
		tree.setMenu(menu);
	}

	private MenuAdapter menuAdapter(final Tree tree, final Menu menu) {
		return new MenuAdapter() {
			@Override
			public void menuShown(MenuEvent e) {
				MenuItem[] items = menu.getItems();
				for (int i = 0; i < items.length; i++) {
					items[i].dispose();
				}

				if (tree.getSelection() != null && tree.getSelectionCount() > 0
						&& tree.getSelection()[0] instanceof TreeItem) {

					TreeItem item = tree.getSelection()[0];
					Object data = item.getData();

					if (data instanceof PooslThread) {
						final PooslThread thread = (PooslThread) data;
						PooslProcessStep.addMenuItemProcessStep(menu, thread);
						addMenuItemThreadWindow(menu, thread);
					} else if (data instanceof PooslDebugTreeItem) {
						PooslDebugTreeItem treeItem = (PooslDebugTreeItem) data;
						// dont show on <adapters>
						if (!(treeItem.getLevel() == 2
								&& !treeItem.getName().equals(RotalumisConstants.CLUSTER_SYSTEM))) {
							addMenuItemDebugDiagram(menu, treeItem);
						}

					}
				}
			}
		};
	}

	private void addMenuItemDebugDiagram(Menu menu, final Object treeItem) {
		MenuItem menuNewWindow = new MenuItem(menu, SWT.NONE);
		menuNewWindow.setText(Messages.ACTION_MENU_OPEN_DEBUG_DIAGRAM);

		ImageDescriptor stepIcon = null;
		try {
			stepIcon = ImageDescriptor.createFromURL(
					new URL("platform:/plugin/nl.esi.poosl.rotalumisclient/icons/icon_open_communication_diagram.png"));
			menuNewWindow.setImage(stepIcon.createImage());
		} catch (MalformedURLException e) {
			LOGGER.log(Level.FINE, "Could not find diagram icon");
		}
		menuNewWindow.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				(new ExternSelectionInformer()).executeInformDebugSelection(treeItem);
			}
		});
	}

	public void addMenuItemThreadWindow(final Menu menu, final PooslThread thread) {
		MenuItem menuNewWindow = new MenuItem(menu, SWT.NONE);
		menuNewWindow.setText(Messages.ACTION_MENU_NEW_WINDOW);
		ImageDescriptor stepIcon = null;
		try {
			stepIcon = ImageDescriptor.createFromURL(
					new URL("platform:/plugin/nl.esi.poosl.rotalumisclient/icons/icon_open_process_window.png"));
			menuNewWindow.setImage(stepIcon.createImage());
		} catch (MalformedURLException e) {
			LOGGER.log(Level.FINE, "Could not find step icon");
		}
		menuNewWindow.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					WindowCreater.getWindowForThread(getSite(), thread);
					selectThread(thread);
				} catch (Exception e) {
					LOGGER.log(Level.WARNING, "Thread window could not be created.", e.getMessage());
				}
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
