package nl.esi.poosl.rotalumisclient.debug;

import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.ITerminate;

import nl.esi.poosl.rotalumisclient.PooslConstants;

public abstract class PooslDebugElement extends PlatformObject implements IDebugElement, ITerminate {
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
