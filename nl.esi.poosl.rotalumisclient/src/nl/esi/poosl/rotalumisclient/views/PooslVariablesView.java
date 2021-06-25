package nl.esi.poosl.rotalumisclient.views;

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
import org.eclipse.debug.internal.ui.actions.variables.ChangeVariableValueAction;
import org.eclipse.debug.internal.ui.viewers.model.TreeModelContentProvider;
import org.eclipse.debug.internal.ui.viewers.model.provisional.TreeModelViewer;
import org.eclipse.debug.internal.ui.views.variables.VariablesView;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import nl.esi.poosl.rotalumisclient.PooslConstants;
import nl.esi.poosl.rotalumisclient.debug.PooslDebugElement;
import nl.esi.poosl.rotalumisclient.debug.PooslDebugTarget;
import nl.esi.poosl.rotalumisclient.debug.PooslThread;
import nl.esi.poosl.rotalumisclient.views.debugview.PooslDebugTreeItem;

@SuppressWarnings("restriction")
// This warning is here because of the reuse of certain internal debugging
// classes of eclipse
public class PooslVariablesView extends VariablesView {
	private static final Logger LOGGER = Logger.getLogger(PooslVariablesView.class.getName());
	private PooslThread input = null;

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench != null) {
			workbench.getHelpSystem().setHelp(parent, "nl.esi.poosl.help.help_variables");
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

	@Override
	public Viewer createViewer(Composite parent) {
		Viewer viewer = super.createViewer(parent);
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				sendInspectDebugEvent(event.getSelection());
			}
		});
		if ((viewer instanceof TreeModelViewer)) {
			((TreeModelViewer) viewer).addTreeListener(new ITreeViewerListener() {
				@Override
				public void treeExpanded(TreeExpansionEvent event) {
					sendInspectDebugEvent(event.getElement());
				}

				@Override
				public void treeCollapsed(TreeExpansionEvent event) {
					// do nothing
				}
			});

		}
		return viewer;
	}

	private void sendInspectDebugEvent(Object source) {
		DebugPlugin plugin = DebugPlugin.getDefault();
		if (plugin != null) {
			plugin.fireDebugEventSet(new DebugEvent[] {
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
				if (stackFrame != null) {
					super.setViewerInput(stackFrame);
				} else {
					super.setViewerInput(context);	
				}
			}
		} else if (context instanceof PooslDebugElement && getViewSite().getSecondaryId() == null) {
			super.setViewerInput(null);
		}
	}

	private void clearViewerInput() {
		super.setViewerInput(null);
	}

	IDebugEventSetListener debugEventSetListener = new IDebugEventSetListener() {
		@Override
		public void handleDebugEvents(DebugEvent[] events) {
			for (DebugEvent debugEvent : events) {
				if (debugEvent.getSource() instanceof PooslThread) {
					if (debugEvent.getKind() == DebugEvent.MODEL_SPECIFIC
							&& debugEvent.getDetail() == PooslConstants.INSPECT_RECEIVED) {
						Object source = debugEvent.getSource();
						if (source == input) {
							setUIViewerInput(source);
						}
					}
				} else if (debugEvent.getSource() instanceof PooslDebugTarget) {
					try {
						if (debugEvent.getKind() == DebugEvent.RESUME || debugEvent.getKind() == DebugEvent.TERMINATE) {
							clearUIViewerInput();
						} else {
							String secondaryId = getViewSite().getSecondaryId();
							// Clear default variables View when debugtarget is selected
							if (secondaryId != null) {
								String debugTargetName = ViewHelper.getDebugTargetName(secondaryId);
								Object source = debugEvent.getSource();
								// Its the correct debug target
								if (source instanceof PooslDebugTarget
										&& ((PooslDebugTarget) source).getName().equals(debugTargetName)
										&& (debugEvent.getKind() == DebugEvent.SUSPEND
												|| debugEvent.getKind() == DebugEvent.CHANGE)) {
									setUIViewerInput(ViewHelper.getThreadFromEvent(null, debugEvent, secondaryId));
								}
							}
						}
					} catch (DebugException e) {
						LOGGER.log(Level.WARNING, "Could not get the name of the debug target past by a debug event.",
								e);
					}
				} else if (debugEvent.getSource() == null || debugEvent.getSource() instanceof PooslDebugTreeItem) {
					String secondaryId = getViewSite().getSecondaryId();
					if (secondaryId == null) {
						clearUIViewerInput();
					}
				} else {
					// do nothing
				}
			}
		}

		private void setUIViewerInput(final Object source) {
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					setViewerInput(source);
				}
			});
		}

		private void clearUIViewerInput() {
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					clearViewerInput();
				}
			});
		}
	};

	ILaunchListener launchListener = new ILaunchListener() {
		@Override
		public void launchRemoved(ILaunch launch) {
			boolean clear = false;
			IDebugTarget debugTarget = launch.getDebugTarget();
			if (debugTarget != null) {
				if (getViewSite().getSecondaryId() == null) {
					if (getViewer() != null && getViewer().getInput() instanceof IStackFrame) {
						IStackFrame stackframe = (IStackFrame) getViewer().getInput();
						clear = (stackframe != null && stackframe.getDebugTarget() != null
								&& stackframe.getDebugTarget() == debugTarget);
					}
				} else {
					clear = ViewHelper.isTargetID(launch.getDebugTarget(), getViewSite().getSecondaryId());
				}
			}
			if (clear) {
				PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

					@Override
					public void run() {
						// Removes references from PooslVariable/PooslValue to
						// PooslDebugTarget
						Viewer viewer = getViewer();
						if (viewer instanceof TreeModelViewer) {
							TreeModelViewer tViewer = (TreeModelViewer) viewer;
							tViewer.setContentProvider(new TreeModelContentProvider());
						}

						getViewer().setInput(null);
						getViewer().setSelection(new TreeSelection());
						input = null;
					}
				});
			}
			launch.removeDebugTarget(debugTarget);
		}

		@Override
		public void launchChanged(ILaunch launch) {
			// do nothing
		}

		@Override
		public void launchAdded(ILaunch launch) {
			// do nothing
		}
	};

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

	@Override
	protected void createActions() {
		super.createActions();
		ChangeVariableValueAction action = new ChangeVariableValueAction(this);
		action.setEnabled(false);
		setAction("ChangeVariableValue", action); //$NON-NLS-1$
	}
}
