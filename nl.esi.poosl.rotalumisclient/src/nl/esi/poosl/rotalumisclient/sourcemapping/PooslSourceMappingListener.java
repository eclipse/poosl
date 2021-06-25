package nl.esi.poosl.rotalumisclient.sourcemapping;

import org.eclipse.swt.widgets.Display;

public abstract class PooslSourceMappingListener {
	private final boolean inUI;

	public PooslSourceMappingListener(boolean inUI) {
		this.inUI = inUI;
	}

	public void returnSourceMapping(final PooslSourceMapping mapping) {
		if (inUI) {
			Display display = Display.getDefault();
			if (display != null) {
				display.asyncExec(new Runnable() {
					@Override
					public void run() {
						requestedSourceMapping(mapping);
					}
				});
			}
		} else {
			requestedSourceMapping(mapping);
		}
	}

	public abstract void requestedSourceMapping(PooslSourceMapping mapping);
}
