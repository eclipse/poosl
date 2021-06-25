package nl.esi.poosl.rotalumisclient.views.stacktraceview;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.ui.contexts.DebugContextEvent;
import org.eclipse.debug.ui.contexts.IDebugContextListener;
import org.eclipse.debug.ui.contexts.IDebugContextService;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

import nl.esi.poosl.generatedxmlclasses.TEengineEventErrorResponse;
import nl.esi.poosl.generatedxmlclasses.TErrorStackframe;
import nl.esi.poosl.rotalumisclient.PooslConstants;
import nl.esi.poosl.rotalumisclient.debug.PooslDebugElement;
import nl.esi.poosl.rotalumisclient.debug.PooslDebugHelper;
import nl.esi.poosl.rotalumisclient.debug.PooslDebugTarget;
import nl.esi.poosl.rotalumisclient.sourcemapping.PooslSourceMap;
import nl.esi.poosl.rotalumisclient.sourcemapping.PooslSourceMapping;
import nl.esi.poosl.rotalumisclient.sourcemapping.PooslSourceMappingListener;
import nl.esi.poosl.rotalumisclient.views.ViewHelper;

public class StackTraceView extends ViewPart {
	private static final Logger LOGGER = Logger.getLogger(StackTraceView.class.getName());
	private PooslDebugTarget debugContext;

	private Label lblProcessName;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text txtErrorMessage;

	private ListViewer listViewer;
	private Label lblTitleProcessName;
	private Label lblTitleErrorMessage;

	private static final Comparator<StackFrameMapping> STACK_FRAME_COMPARATOR = new Comparator<StackFrameMapping>() {
		@Override
		public int compare(StackFrameMapping frame1, StackFrameMapping frame2) {
			if (frame1 == null || frame1.getFrame() == null) {
				return 1;
			} else if (frame2 == null || frame2.getFrame() == null) {
				return 0;
			} else {
				return frame1.getFrame().getId().compareTo(frame2.getFrame().getId());
			}
		}
	};

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		parent.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		parent.setLayout(new GridLayout(1, false));

		Composite compInfo = new Composite(parent, SWT.NONE);
		compInfo.setBackgroundMode(SWT.INHERIT_FORCE);
		compInfo.setLayout(new GridLayout(2, false));
		GridData gdCompInfo = new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1);
		gdCompInfo.widthHint = 256;
		compInfo.setLayoutData(gdCompInfo);
		formToolkit.adapt(compInfo);
		formToolkit.paintBordersFor(compInfo);

		lblTitleErrorMessage = new Label(compInfo, SWT.NONE);
		formToolkit.adapt(lblTitleErrorMessage, true, true);
		lblTitleErrorMessage.setText("Error Message:");

		txtErrorMessage = new Text(compInfo, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);

		GridData gdTxtErrorMessage = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gdTxtErrorMessage.heightHint = 55;
		gdTxtErrorMessage.minimumHeight = 50;//
		txtErrorMessage.setLayoutData(gdTxtErrorMessage);
		txtErrorMessage.setEditable(false);
		formToolkit.adapt(txtErrorMessage, true, true);

		lblTitleProcessName = new Label(compInfo, SWT.NONE);
		lblTitleProcessName.setBounds(0, 0, 55, 15);
		formToolkit.adapt(lblTitleProcessName, true, true);
		lblTitleProcessName.setText("Process Name:");

		lblProcessName = new Label(compInfo, SWT.NONE);
		lblProcessName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblProcessName.setBounds(0, 0, 55, 15);
		formToolkit.adapt(lblProcessName, true, true);

		listViewer = new ListViewer(parent, SWT.BORDER | SWT.V_SCROLL);
		listViewer.setContentProvider(new StackTraceContentProvider());
		listViewer.setLabelProvider(new StackTraceLabelProvider());
		IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench != null) {
			workbench.getHelpSystem().setHelp(parent, "nl.esi.poosl.help.help_stacktrace");
		}
		org.eclipse.swt.widgets.List list = listViewer.getList();
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		setDebugContext(PooslDebugHelper.getCurrentDebugTarget());
		initializeListeners();
		createActions();
		createContextMenu();
		createMenu();
	}

	public void setDebugContext(final PooslDebugTarget target) {
		debugContext = target;
		// check if target and tacktrace exist,
		if (target != null && target.getStackTrace() != null) {
			createStackFrameMapping(target);
		} else {
			clear();
		}
	}

	private void createStackFrameMapping(PooslDebugTarget target) {
		final TEengineEventErrorResponse trace = target.getStackTrace();
		PooslSourceMap modelMapping = target.getPooslSourceMap();
		final List<StackFrameMapping> frameMapping = new ArrayList<>();
		if (trace.getStacktrace() != null && trace.getStacktrace().getStackframe() != null) {
			// dont set new input if trace did not change
			final int total = trace.getStacktrace().getStackframe().size();

			txtErrorMessage.setText(trace.getError());
			lblProcessName.setText(trace.getProcessPath());
			if (modelMapping != null) {
				for (final TErrorStackframe frame : trace.getStacktrace().getStackframe()) {
					modelMapping.getSourceMapping(Integer.valueOf(frame.getStmtHandle()),
							new PooslSourceMappingListener(true) {
								@Override
								public void requestedSourceMapping(PooslSourceMapping mapping) {
									frameMapping.add(new StackFrameMapping(frame, mapping));
									frameMapping.sort(STACK_FRAME_COMPARATOR);
									listViewer.setInput(frameMapping);
									if (frameMapping.size() == total) {
										Object firstElement = listViewer
												.getElementAt(getFirstMappedElementIndex(frameMapping));
										if (firstElement != null) {
											listViewer.setSelection(new StructuredSelection(firstElement), true);
										}
									}
								}
							});
				}
			}
		}
		if (trace.getStacktrace() == null && trace.getStmtHandle() != 0 && modelMapping != null) {
			modelMapping.getSourceMapping(trace.getStmtHandle(), new PooslSourceMappingListener(true) {
				@Override
				public void requestedSourceMapping(PooslSourceMapping mapping) {
					txtErrorMessage.setText(trace.getError() + "\n\'" + mapping.getSourceText() + "\'\n\""
							+ mapping.getFilePath() + ":" + mapping.getLineNumber() + "\"");
				}
			});
		}
	}

	private void initializeListeners() {
		DebugPlugin plugin = DebugPlugin.getDefault();
		if (plugin != null) {
			plugin.addDebugEventListener(debugEventSetListener);
		}

		IDebugContextService service = ViewHelper.getDebugService(this);
		if (service != null) {
			service.addDebugContextListener(debugContextListener);
		}

		listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent selectionEvent) {
				ISelection selection = selectionEvent.getSelection();
				if (selection instanceof IStructuredSelection) {
					Object element = ((IStructuredSelection) selection).getFirstElement();
					if (element instanceof StackFrameMapping) {
						TErrorStackframe tFrame = ((StackFrameMapping) element).getFrame();
						IStackFrame frame = debugContext.getErrorStackFrame(tFrame.getId());
						if (frame != null) {
							PooslSourceMapping mapping = ((StackFrameMapping) element).getMapping();
							try {
								if (mapping != null && frame.getThread() != null) {
									PooslDebugHelper.setDebugInstructionPointer(frame, mapping, debugContext,
											getSite().getWorkbenchWindow());
								}
							} catch (Exception e) {
								LOGGER.log(Level.WARNING, "Could not point to error.", e.getMessage());
							}
							fireSelectionEvent(frame);
						}
					}
				}
			}
		});
	}

	private void fireSelectionEvent(Object data) {
		DebugPlugin plugin = DebugPlugin.getDefault();
		if (plugin != null) {
			DebugEvent event = new DebugEvent(this, DebugEvent.MODEL_SPECIFIC, PooslConstants.STACKFRAME_INSPECT);
			event.setData(data);
			plugin.fireDebugEventSet(new DebugEvent[] { event });
		}
	}

	IDebugEventSetListener debugEventSetListener = new IDebugEventSetListener() {
		@Override
		public void handleDebugEvents(DebugEvent[] debugEvents) {
			for (int i = 0; i < debugEvents.length; i++) {
				DebugEvent debugEvent = debugEvents[i];
				if (debugEvent.getKind() == DebugEvent.MODEL_SPECIFIC
						&& debugEvent.getDetail() == PooslConstants.ENGINE_ERROR
						&& debugEvent.getSource() instanceof PooslDebugTarget) {

					final PooslDebugTarget debugTarget = (PooslDebugTarget) debugEvent.getSource();
					if (debugContext == null || debugContext != debugTarget) {
						setDebugContext(debugTarget);
					}
				}
			}
		}
	};

	IDebugContextListener debugContextListener = new IDebugContextListener() {
		@Override
		public void debugContextChanged(DebugContextEvent event) {
			if (event.getContext() instanceof IStructuredSelection) {
				Object element = ((IStructuredSelection) event.getContext()).getFirstElement();
				if (element instanceof PooslDebugElement) {
					IDebugTarget target = ((PooslDebugElement) element).getDebugTarget();
					if (target instanceof PooslDebugTarget) {
						setDebugContext((PooslDebugTarget) target);
						return;
					}
				}
			}
			clear();
		}
	};

	private Action copyTraceAction;

	private void clear() {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				txtErrorMessage.setText("");
				lblProcessName.setText("");
				listViewer.setInput(null);
			}
		});
		fireSelectionEvent(null);
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void dispose() {
		DebugPlugin plugin = DebugPlugin.getDefault();
		if (plugin != null) {
			plugin.removeDebugEventListener(debugEventSetListener);
		}
		IDebugContextService service = ViewHelper.getDebugService(this);
		if (service != null) {
			service.removeDebugContextListener(debugContextListener);
		}
		debugContext = null;
		super.dispose();
	}

	private void createContextMenu() {
		// Create menu manager.
		MenuManager menuMgr = new MenuManager();
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {

			@Override
			public void menuAboutToShow(IMenuManager mgr) {
				fillContextMenu(mgr);

			}
		});

		// Create menu.
		Menu menu = menuMgr.createContextMenu(listViewer.getControl());
		listViewer.getControl().setMenu(menu);

		// Register menu for extension.
		getSite().registerContextMenu(menuMgr, listViewer);
	}

	private void fillContextMenu(IMenuManager mgr) {
		mgr.add(copyTraceAction);
	}

	public void createActions() {
		copyTraceAction = new Action("Copy Trace to Clipboard") {
			@Override
			public void run() {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Clipboard clipboard = toolkit.getSystemClipboard();
				StringSelection strSel = new StringSelection(createCopyString());
				clipboard.setContents(strSel, null);
			}
		};
	}

	private String createCopyString() {
		String info = "";
		info += lblTitleErrorMessage.getText() + " ";
		info += txtErrorMessage.getText() + "\r\n";
		info += lblTitleProcessName.getText() + " ";
		info += lblProcessName.getText() + "\r\n";
		for (String item : listViewer.getList().getItems()) {
			info += " " + item + "\r\n";
		}
		return info;

	}

	private int getFirstMappedElementIndex(List<StackFrameMapping> mapping) {
		for (int i = 0; i < mapping.size(); i++) {
			PooslSourceMapping source = mapping.get(i).getMapping();
			if (source != null && (source != PooslSourceMap.EMPTY_MAPPING
					|| source.getOffset() != -1 && source.getLength() != 0)) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * Create menu.
	 */
	private void createMenu() {
		IMenuManager mgr = getViewSite().getActionBars().getMenuManager();
		mgr.add(copyTraceAction);
	}
}
