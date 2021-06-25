package nl.esi.poosl.rotalumisclient.views.debugview;

import java.util.Arrays;
import java.util.List;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.jface.viewers.Viewer;

import nl.esi.poosl.rotalumisclient.debug.PooslDebugElement;
import nl.esi.poosl.rotalumisclient.debug.PooslDebugTarget;

/**
 * The {@link PooslDebugTreeItem} are items shown in the tree of the
 * {@link PooslDebugView}. The tree items represent either a cluster or a
 * process of the Poosl model.
 * 
 * @author staalk
 *
 */
public class PooslDebugTreeItem extends PooslDebugElement implements IThread {
	private String name;
	private final List<IThread> threads;
	private final int level;
	private Object parent;
	private final Viewer outerType;

	public PooslDebugTreeItem(Viewer viewer, String name, List<IThread> threads, int level, Object parent,
			PooslDebugTarget target) {
		super(target);
		this.name = name;
		this.threads = threads;
		this.level = level;
		this.parent = parent;
		this.outerType = viewer;
	}

	public List<IThread> getThreadsList() {
		return threads;
	}

	public IThread[] getThreads() {
		return Arrays.copyOf(threads.toArray(), threads.toArray().length, IThread[].class);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public Object getParent() {
		return parent;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean canResume() {
		return getDebugTarget().canResume();
	}

	@Override
	public boolean canSuspend() {
		return getDebugTarget().canSuspend();
	}

	@Override
	public boolean isSuspended() {
		return getDebugTarget().isSuspended();
	}

	@Override
	public void resume() throws DebugException {
		// Clear the stackframes from this thread since it is no longer
		// suspended.
		if (isSuspended()) {
			getDebugTarget().resume();
		}
	}

	@Override
	public void suspend() throws DebugException {
		if (!isSuspended()) {
			getDebugTarget().suspend();
		}
	}

	@Override
	public boolean canStepInto() {
		return false;
	}

	@Override
	public boolean canStepOver() {
		return false;
	}

	@Override
	public boolean canStepReturn() {
		return false;
	}

	@Override
	public boolean isStepping() {
		return isSuspended();
	}

	@Override
	public void stepInto() throws DebugException {
		// not supported
	}

	@Override
	public void stepOver() throws DebugException {
		// not supported
	}

	@Override
	public void stepReturn() throws DebugException {
		// not supported
	}

	@Override
	public boolean canTerminate() {
		return getDebugTarget().canTerminate();
	}

	@Override
	public boolean isTerminated() {
		return getDebugTarget().isTerminated();
	}

	@Override
	public void terminate() throws DebugException {
		if (!isTerminated()) {
			getDebugTarget().terminate();
		}
	}

	@Override
	public IBreakpoint[] getBreakpoints() {
		return null;
	}

	@Override
	public int getPriority() throws DebugException {
		return 0;
	}

	@Override
	public IStackFrame[] getStackFrames() throws DebugException {
		return null;
	}

	@Override
	public IStackFrame getTopStackFrame() throws DebugException {
		return null;
	}

	@Override
	public boolean hasStackFrames() throws DebugException {
		return false;
	}

	public void dispose() {
		parent = null;
		target = null;
	}

	private Viewer getOuterType() {
		return outerType;
	}

	private static final int PRIME = 31;

	@Override
	public int hashCode() {
		int result = 1;
		result = PRIME * result + getOuterType().hashCode();
		result = PRIME * result + level;
		result = PRIME * result + ((name == null) ? 0 : name.hashCode());
		result = PRIME * result + ((parent == null) ? 0 : parent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PooslDebugTreeItem other = (PooslDebugTreeItem) obj;
		if (!getOuterType().equals(other.getOuterType()))
			return false;
		if (level != other.level)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		return true;
	}
}
