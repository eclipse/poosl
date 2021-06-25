package nl.esi.poosl.rotalumisclient.views.stacktraceview;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.internal.ui.views.variables.VariablesView;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import nl.esi.poosl.rotalumisclient.PooslConstants;

@SuppressWarnings("restriction")
public class StackTraceVariableView extends VariablesView {

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		DebugPlugin plugin = DebugPlugin.getDefault();
		if (plugin != null) {
			plugin.addDebugEventListener(debugEventSetListener);
		}

		IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench != null) {
			workbench.getHelpSystem().setHelp(parent, "nl.esi.poosl.help.help_stacktrace_variables");
		}
	}

	IDebugEventSetListener debugEventSetListener = new IDebugEventSetListener() {
		@Override
		public void handleDebugEvents(DebugEvent[] events) {
			final Viewer viewer = getViewer();
			if (viewer != null) {
				for (DebugEvent debugEvent : events) {
					if (debugEvent.getKind() == DebugEvent.MODEL_SPECIFIC
							&& debugEvent.getDetail() == PooslConstants.STACKFRAME_INSPECT) {
						final Object data = debugEvent.getData();
						Display.getDefault().asyncExec(new Runnable() {
							public void run() {
								viewer.setInput(data);
							}
						});
					}
				}
			}
		}
	};

	@Override
	public Viewer createViewer(Composite parent) {
		Viewer viewer = super.createViewer(parent);
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// On selection change send out an model specific debug event
				// for a inspect request for the selected element.
				DebugPlugin plugin = DebugPlugin.getDefault();
				if (plugin != null) {
					plugin.fireDebugEventSet(new DebugEvent[] { new DebugEvent(event.getSelection(),
							DebugEvent.MODEL_SPECIFIC, PooslConstants.INSPECT_REQUEST) });
				}
			}
		});
		return viewer;
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
