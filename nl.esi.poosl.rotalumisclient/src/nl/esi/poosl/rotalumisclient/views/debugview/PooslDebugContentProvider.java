package nl.esi.poosl.rotalumisclient.views.debugview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import nl.esi.poosl.rotalumisclient.debug.PooslDebugTarget;
import nl.esi.poosl.rotalumisclient.debug.PooslThread;

/**
 * This is the Tree Content Provider for the {@link PooslDebugView} Uses the
 * lauchmanager to show PooslDebugTargets. Creates parent
 * {@link PooslDebugView.TreeItem} of the clusterclasses based on the thread
 * names. The treeitems are created when new launches are added or removed
 * 
 * @author staalk
 *
 */
public class PooslDebugContentProvider implements ITreeContentProvider {
	private static final Logger LOGGER = Logger.getLogger(PooslDebugContentProvider.class.getName());

	private Map<Object, List<Object>> parentchildren;
	private List<IDebugTarget> debugtargets;

	public Object[] getTreeSegments(PooslThread thread) throws DebugException {
		String[] result = thread.getName().split("/");
		Object[] segments = new Object[result.length];
		segments[0] = thread.getDebugTarget();
		int index = result.length - 1;
		segments[index] = thread;
		if (result.length > 2) {
			Object parent = findTreeParent(thread);
			while (parent instanceof PooslDebugTreeItem) {
				index--;
				segments[index] = parent;
				parent = ((PooslDebugTreeItem) parent).getParent();
			}
		}
		return segments;
	}

	/**
	 * Finds the Tree item parent of the thread
	 * 
	 * @param thread
	 * @return the parent {@link PooslDebugView.TreeItem}, returns null if none
	 *         exists
	 * @throws DebugException
	 */
	private PooslDebugTreeItem findTreeParent(PooslThread thread) throws DebugException {
		String[] result = thread.getName().split("/");
		int level = result.length - 2;
		if (level != 0 && parentchildren != null) {

			int startindex = thread.getName().indexOf(result[level]);
			int endindex = startindex + result[level].length();
			String levelstring = thread.getName().substring(startindex, endindex);

			for (Entry<Object, List<Object>> entry : parentchildren.entrySet()) {
				Object key = entry.getKey();
				if (key instanceof PooslDebugTreeItem) {
					String name = ((PooslDebugTreeItem) key).getName();
					if (name.equals(levelstring)) {
						return (PooslDebugTreeItem) key;
					}
				}
			}
		}
		return null;
	}

	@Override
	public synchronized void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		if (newInput instanceof ILaunchManager) {
			if (parentchildren == null) {
				parentchildren = new HashMap<>();
			}
			if (debugtargets == null) {
				debugtargets = new ArrayList<>();
			}
			IDebugTarget[] newTargets = ((ILaunchManager) newInput).getDebugTargets();

			// remove old
			List<IDebugTarget> notalive = new ArrayList<>();
			for (IDebugTarget iDebugTarget : debugtargets) {
				boolean alive = false;
				for (IDebugTarget newTarget : newTargets) {
					if (newTarget.equals(iDebugTarget)) {
						alive = true;
						break;
					}
				}
				if (!alive) {
					notalive.add(iDebugTarget);
				}
			}
			for (IDebugTarget iDebugTarget : notalive) {
				removeTarget(iDebugTarget);
			}

			// add new
			for (IDebugTarget newTarget : newTargets) {
				if (!debugtargets.contains(newTarget) && newTarget instanceof PooslDebugTarget) {
					try {
						debugtargets.add(newTarget);
						addTreeItems(viewer, newTarget, (PooslDebugTarget) newTarget);
					} catch (DebugException e) {
						LOGGER.log(Level.SEVERE, "Failed to create tree items in the debug view. " + e.getMessage(),
								e.getSuppressed());
					}
				}

			}
		}
	}

	/**
	 * Creates {@link PooslDebugView.TreeItem} if the parent is
	 * {@link PooslDebugTarget} or {@link PooslDebugView.TreeItem} and adds them to
	 * parentchildren
	 * 
	 * @param parent {@link PooslDebugTarget} or {@link PooslDebugView.TreeItem}
	 * @param target the current {@link IDebugTarget}
	 * @throws DebugException
	 */
	private void addTreeItems(Viewer viewer, Object parent, PooslDebugTarget target) throws DebugException {
		IThread[] threads;
		int level;
		if (parent instanceof PooslDebugTarget) {
			threads = ((PooslDebugTarget) parent).getThreads();
			level = 1;
		} else {
			threads = ((PooslDebugTreeItem) parent).getThreads();
			level = ((PooslDebugTreeItem) parent).getLevel();
		}

		List<Object> children = new ArrayList<>();
		Map<String, List<IThread>> sameparent = new LinkedHashMap<>();
		if (threads != null) {
			for (IThread iThread : threads) {
				String[] result = iThread.getName().split("/");
				if (result.length > 0) {
					if (result.length == level + 1) {
						// process class
						children.add(iThread);
					} else {
						// cluster class
						int startindex = iThread.getName().indexOf(result[level]);
						int endindex = startindex + result[level].length();
						String levelstring = iThread.getName().substring(startindex, endindex);
						if (sameparent.containsKey(levelstring)) {
							sameparent.get(levelstring).add(iThread);
						} else {
							List<IThread> list = new ArrayList<>();
							list.add(iThread);
							sameparent.put(levelstring, list);
							PooslDebugTreeItem treeitem = new PooslDebugTreeItem(viewer, levelstring, list, level + 1,
									parent, target);
							children.add(treeitem);
						}
					}
				}
			}
		}

		for (Object object : children) {
			if (object instanceof PooslDebugTreeItem) {
				addTreeItems(viewer, (PooslDebugTreeItem) object, target);
			}
		}
		parentchildren.put(parent, children);
	}

	/**
	 * removes target from this contentprovider
	 * 
	 * @param target
	 */
	private void removeTarget(IDebugTarget target) {
		debugtargets.remove(target);
		if (parentchildren != null) {
			parentchildren.remove(target);
			List<Object> toBeRemoved = new LinkedList<>();
			for (Object object : parentchildren.keySet()) {
				if (object instanceof PooslDebugTreeItem) {
					IDebugTarget debugTarget = ((PooslDebugTreeItem) object).getDebugTarget();
					if (debugTarget == target) {
						toBeRemoved.add(object);
					}
				}
			}
			for (Object object : toBeRemoved) {
				parentchildren.remove(object);
			}
		}
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof ILaunchManager) {
			List<Object> elements = new ArrayList<>();
			for (ILaunch launch : ((ILaunchManager) inputElement).getLaunches()) {
				IDebugTarget debugTarget = launch.getDebugTarget();
				if (debugTarget instanceof PooslDebugTarget) {
					elements.add(debugTarget);
				}
			}
			return elements.toArray();
		}
		return new Object[0];
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentchildren == null || parentchildren.get(parentElement) == null) {
			return new Object[0];
		} else {
			return parentchildren.get(parentElement).toArray();
		}
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof IThread) {
			return ((IThread) element).getDebugTarget();
		} else if (element instanceof PooslDebugTreeItem) {
			((PooslDebugTreeItem) element).getParent();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof IDebugTarget) {
			try {
				return ((IDebugTarget) element).getThreads().length > 0;
			} catch (DebugException e) {
				LOGGER.log(Level.WARNING, e.getMessage(), e);
			}
		} else if (element instanceof PooslDebugTreeItem) {
			return true;
		}
		return false;
	}

	@Override
	public void dispose() {
		debugtargets.clear();
		if (parentchildren != null) {
			for (Object object : parentchildren.keySet()) {
				if (object instanceof PooslDebugTreeItem) {
					((PooslDebugTreeItem) object).dispose();
				}
			}
			parentchildren.clear();
		}
	}
}
