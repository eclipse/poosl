package nl.esi.poosl.rotalumisclient.views;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBElement;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.internal.ui.InstructionPointerManager;
import org.eclipse.debug.ui.contexts.DebugContextEvent;
import org.eclipse.debug.ui.contexts.IDebugContextListener;
import org.eclipse.debug.ui.contexts.IDebugContextService;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import nl.esi.poosl.generatedxmlclasses.TExecutiontree;
import nl.esi.poosl.generatedxmlclasses.TExecutiontreeBase;
import nl.esi.poosl.generatedxmlclasses.TExecutiontreeStatements;
import nl.esi.poosl.generatedxmlclasses.TTransition;
import nl.esi.poosl.rotalumisclient.Messages;
import nl.esi.poosl.rotalumisclient.PooslConstants;
import nl.esi.poosl.rotalumisclient.debug.ExecutionTreeContext;
import nl.esi.poosl.rotalumisclient.debug.PooslDebugElement;
import nl.esi.poosl.rotalumisclient.debug.PooslDebugHelper;
import nl.esi.poosl.rotalumisclient.debug.PooslDebugTarget;
import nl.esi.poosl.rotalumisclient.debug.PooslStackFrame;
import nl.esi.poosl.rotalumisclient.debug.PooslThread;
import nl.esi.poosl.rotalumisclient.sourcemapping.PooslSourceMap;
import nl.esi.poosl.rotalumisclient.sourcemapping.PooslSourceMapping;
import nl.esi.poosl.rotalumisclient.sourcemapping.PooslSourceMappingListener;

@SuppressWarnings("restriction")
public class PooslPETView extends ViewPart implements SelectionListener, KeyListener {
	private static final Logger LOGGER = Logger.getLogger(PooslPETView.class.getName());
	private static final Color INACTIVE_COLOR = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);
	private static final Color EXECUTABLE_COLOR = Display.getDefault().getSystemColor(SWT.COLOR_RED);
	private static final Color DELAY_COLOR = Display.getDefault().getSystemColor(SWT.COLOR_BLUE);

	private Composite treeParent;
	private Tree tree;
	private TreeItem treeRoot;
	private final Map<BigInteger, BigInteger> possibleTransitionIds = new HashMap<>();
	private final List<BigInteger> possibleDelayTransitionIds = new ArrayList<>();
	private final List<TreeItem> possibleTransitionItems = new ArrayList<>();
	private final List<TreeItem> notPossibleTransitionItems = new ArrayList<>();
	private IAction processStepAction;
	private PooslThread debugContext;

	IDebugContextListener debugContextListener = new IDebugContextListener() {
		@Override
		public void debugContextChanged(DebugContextEvent event) {
			if (event.getSource() == null) {
				clear();
				return;
			}
			Object newContext = ((StructuredSelection) event.getContext()).getFirstElement();

			if (getViewSite().getSecondaryId() == null) {
				if (newContext instanceof PooslThread) {
					PooslThread newThread = (PooslThread) newContext;
					if (newThread.isSuspended() && !newThread.isTerminated()) {
						update(newThread);
					} else {
						clear();
						debugContext = newThread;
					}
				} else {
					if (newContext instanceof PooslDebugElement) {
						setSelection((PooslDebugElement) newContext);
					}
					debugContext = null;
					clear();
				}
			} else {
				if (newContext instanceof PooslThread) {
					PooslThread thread = (PooslThread) newContext;
					if (ViewHelper.isThreadID(thread, getViewSite().getSecondaryId())) {
						debugContext = thread;
						if (debugContext.isSuspended() && !debugContext.isTerminated()) {
							update(debugContext);
						} else {
							clear();
						}
					}
				}
			}
		}
	};

	IDebugEventSetListener debugEventSetListener = new IDebugEventSetListener() {
		@Override
		public void handleDebugEvents(DebugEvent[] events) {
			for (DebugEvent debugEvent : events) {

				final PooslThread newDebugContext = ViewHelper.getThreadFromEvent(debugContext, debugEvent,
						getViewSite().getSecondaryId());

				if (newDebugContext != null) {
					if (debugEvent.getSource().equals(newDebugContext.getDebugTarget())) {
						if (debugEvent.getKind() == DebugEvent.SUSPEND || debugEvent.getKind() == DebugEvent.CHANGE) {
							newDebugContext.getRotalumisStackFrames();
							removeDebugInstructionPointer(debugEvent.getSource());
							Display display = Display.getDefault();
							if (display != null) {
								display.asyncExec(new Runnable() {
									@Override
									public void run() {
										update(newDebugContext);
									}
								});
							}
						}
						if (debugEvent.getKind() == DebugEvent.RESUME || debugEvent.getKind() == DebugEvent.TERMINATE) {
							Display display = Display.getDefault();
							if (display != null) {
								display.asyncExec(new Runnable() {
									@Override
									public void run() {
										clear();
									}
								});
							}
							if (debugEvent.getKind() == DebugEvent.SUSPEND) {
								removeDebugInstructionPointer(debugEvent.getSource());
							}
						}

						if (debugEvent.getKind() == DebugEvent.TERMINATE
								|| (newDebugContext != null && newDebugContext.isTerminated())) {
							debugContext = null;
							break;
						}
					} else if (debugEvent.getSource() instanceof PooslThread) {
						final PooslThread thread = (PooslThread) debugEvent.getSource();
						if (newDebugContext == thread) {
							Display display = Display.getDefault();
							if (display != null) {
								display.asyncExec(new Runnable() {
									@Override
									public void run() {
										update(newDebugContext);
									}
								});
							}
						}
					}
				} else if (debugEvent.getDetail() == PooslConstants.ERROR_STATE
						|| debugEvent.getDetail() == PooslConstants.BREAKPOINT_HIT) {
					// dont Clear
				} else {
					removeDebugInstructionPointer(debugEvent.getSource());

					Display display = Display.getDefault();
					if (display != null) {
						display.asyncExec(new Runnable() {
							@Override
							public void run() {
								clear();
							}
						});
					}
				}
				debugContext = newDebugContext;
			}
		}

	};

	@Override
	public void createPartControl(Composite parent) {
		treeParent = new Composite(parent, SWT.NONE);
		treeParent.setLayout(new FillLayout());
		IDebugContextService service = ViewHelper.getDebugService(this);

		if (service != null) {
			service.addDebugContextListener(debugContextListener);
		}

		DebugPlugin plugin = DebugPlugin.getDefault();
		if (plugin != null) {
			plugin.addDebugEventListener(debugEventSetListener);
		}

		tree = new Tree(treeParent, SWT.VIRTUAL);
		tree.addSelectionListener(this);
		tree.addKeyListener(this);
		tree.setLayout(new FillLayout());
		tree.addListener(SWT.MouseDoubleClick, new Listener() {
			@Override
			public void handleEvent(Event event) {
				TreeItem treeItem = tree.getItem(new Point(event.x, event.y));
				if (treeItem != null && possibleTransitionItems.contains(treeItem)) {
					performTransition((TExecutiontreeBase) treeItem.getData());
				}
			}
		});
		treeRoot = new TreeItem(tree, SWT.NONE);
		treeRoot.setText("No information to display.");
		IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench != null) {
			workbench.getHelpSystem().setHelp(treeParent, "nl.esi.poosl.help.help_pet");
		}
		processStepAction = createProcessStepAction();
		createToolBarMenu();
	}

	@Override
	public void setFocus() {
		treeParent.setFocus();
	}

	public void parseTree(PooslThread thread) {
		String processName = "";
		try {
			processName = thread.getName();
		} catch (DebugException e1) {
			LOGGER.log(Level.WARNING, "Could not get the name of the thread to parse the execution tree.", e1);
			return;
		}
		TExecutiontree executionTree = thread.getExecutiontree();
		if (executionTree == null) {
			return;
		}
		tree.removeAll();
		treeRoot = new TreeItem(tree, SWT.NONE);
		treeRoot.setText(processName);
		for (JAXBElement<? extends TExecutiontreeBase> executionTreeElement : executionTree
				.getSequentialOrMethodCallOrParallel()) {
			// First get the main entry level nodes (e.g. where the parent is
			// equal to BigInteger 0 / BigInteger.ZERO)
			if (executionTreeElement.getValue().getParent().equals(BigInteger.ZERO)) {
				addChilds(executionTree, treeRoot, executionTreeElement, true);
			}
		}
		// Add menu listener
		final Menu menu = new Menu(tree);
		tree.setMenu(menu);
		menu.addMenuListener(new MenuAdapter() {
			@Override
			public void menuShown(MenuEvent e) {
				MenuItem[] items = menu.getItems();
				for (int i = 0; i < items.length; i++) {
					items[i].dispose();
				}
				if (possibleTransitionIds
						.containsKey(((TExecutiontreeBase) tree.getSelection()[0].getData()).getHandle())) {
					MenuItem newItem = new MenuItem(menu, SWT.NONE);
					newItem.setText("Perform transition for " + tree.getSelection()[0].getText());
					newItem.addSelectionListener(new SelectionListener() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							if (tree.getSelection()[0].getData() instanceof TExecutiontreeBase) {
								TExecutiontreeBase base = (TExecutiontreeBase) tree.getSelection()[0].getData();
								performTransition(base);
							}
						}

						@Override
						public void widgetDefaultSelected(SelectionEvent e) {
							widgetSelected(e);
						}
					});
				}
			}
		});
		// Expand the complete tree
		for (TreeItem treeItem : tree.getItems()) {
			treeItem.setExpanded(true);
			if (treeItem.getItemCount() > 0) {
				expandTreeChildren(treeItem);
			}
		}
		treeParent.layout();
	}

	private void performTransition(TExecutiontreeBase executiontreeBase) {
		// Set the handle of the executiontree item to the actual transition
		// handle
		executiontreeBase.setHandle(possibleTransitionIds.get(executiontreeBase.getHandle()));
		DebugPlugin.getDefault().fireDebugEventSet(
				new DebugEvent[] { new DebugEvent(new ExecutionTreeContext(debugContext, executiontreeBase),
						DebugEvent.MODEL_SPECIFIC, PooslConstants.PERFORM_TRANSITION) });
	}

	private void expandTreeChildren(TreeItem treeItem) {
		for (TreeItem child : treeItem.getItems()) {
			child.setExpanded(true);
			if (child.getItemCount() > 0) {
				expandTreeChildren(child);
			}
		}
	}

	private void addChilds(TExecutiontree executionTree, TreeItem treeItem,
			JAXBElement<? extends TExecutiontreeBase> executionTreeElement, boolean possiblyActive) {
		boolean active = possiblyActive;
		TreeItem child = new TreeItem(treeItem, SWT.NONE);
		setText(child, executionTreeElement);
		child.setData(executionTreeElement.getValue());

		if (active) {
			String localNamePart = executionTreeElement.getName().getLocalPart();
			if (!"guard".equals(localNamePart)) {
				if ("sequential".equals(localNamePart) || "parallel".equals(localNamePart)
						|| "select".equals(localNamePart) || "method_call".equals(localNamePart)
						|| "abort".equals(localNamePart) || "interrupt".equals(localNamePart)) {
					child.setForeground(INACTIVE_COLOR);
				} else {
					active = false;
				}
			}
		} else {
			child.setForeground(INACTIVE_COLOR);
		}

		if (possibleTransitionIds.containsKey(executionTreeElement.getValue().getHandle())) {
			if (possibleDelayTransitionIds.contains(executionTreeElement.getValue().getHandle())) {
				child.setForeground(DELAY_COLOR);
			} else {
				child.setForeground(EXECUTABLE_COLOR);
			}
			possibleTransitionItems.add(child);
		} else {
			notPossibleTransitionItems.add(child);
		}

		if (executionTreeElement.getValue() instanceof TExecutiontreeStatements) {
			TExecutiontreeStatements statements = (TExecutiontreeStatements) executionTreeElement.getValue();
			for (BigInteger bigInt : statements.getStatement()) {
				// Find the corresponding ExecutionTree Element
				JAXBElement<? extends TExecutiontreeBase> subElement = null;
				for (JAXBElement<? extends TExecutiontreeBase> tempElement : executionTree
						.getSequentialOrMethodCallOrParallel()) {
					if (tempElement.getValue().getHandle().equals(bigInt)) {
						subElement = tempElement;
						break;
					}
				}
				addChilds(executionTree, child, subElement,
						active && (!"sequential".equals(executionTreeElement.getName().getLocalPart())
								|| 0 == statements.getStatement().indexOf(bigInt)));
			}
		} else {
			JAXBElement<? extends TExecutiontreeBase> subElement = null;
			for (JAXBElement<? extends TExecutiontreeBase> tempElement : executionTree
					.getSequentialOrMethodCallOrParallel()) {
				if (tempElement.getValue().getParent().equals(executionTreeElement.getValue().getHandle())) {
					subElement = tempElement;
					addChilds(executionTree, child, subElement, active);
				}
			}
			if (subElement != null && "while".equals(executionTreeElement.getName().getLocalPart())
					&& !possibleTransitionIds.containsKey(executionTreeElement.getValue().getHandle())) {
				child.setForeground(INACTIVE_COLOR);
			}
		}
	}

	private void setText(final TreeItem child, JAXBElement<? extends TExecutiontreeBase> executionTreeElement) {
		if (executionTreeElement.getValue() instanceof TExecutiontreeStatements) {
			String text = executionTreeElement.getName().toString().replace("{uri:poosl}", "");
			child.setText(text);
		} else {
			final int statementHandle = executionTreeElement.getValue().getStmtHandle();
			PooslDebugTarget target = getDebugTarget();

			if (target != null) {
				PooslSourceMap pooslSourceMap = target.getPooslSourceMap();
				pooslSourceMap.getSourceMapping(statementHandle, new PooslSourceMappingListener(true) {
					@Override
					public void requestedSourceMapping(final PooslSourceMapping mapping) {
						if (child != null && !child.isDisposed()) {
							if (mapping == null) {
								child.setText("...");
							} else {
								child.setText(mapping.getSingleLineSourceText());
							}
						}
					}
				});
			}
		}
	}

	@Override
	public void dispose() {
		IDebugContextService service = ViewHelper.getDebugService(this);

		if (service != null) {
			service.removeDebugContextListener(debugContextListener);
		}

		DebugPlugin plugin = DebugPlugin.getDefault();
		if (plugin != null) {
			plugin.removeDebugEventListener(debugEventSetListener);
		}
		debugContext = null;
		super.dispose();
	}

	public void update(PooslThread thread) {
		// If the shown thread is the same remember the selected executiontree handle
		BigInteger oldSelectedHandle = null;
		if (thread == debugContext) {
			TreeItem[] oldTreeItem = tree.getSelection();
			if (oldTreeItem.length > 0 && oldTreeItem[0].getData() != null) {
				oldSelectedHandle = ((TExecutiontreeBase) oldTreeItem[0].getData()).getHandle();
			}
		}
		debugContext = thread;
		debugContext.setOriginalVariables();
		possibleTransitionIds.clear();
		possibleTransitionItems.clear();
		notPossibleTransitionItems.clear();
		possibleDelayTransitionIds.clear();
		if (thread != null) {
			List<TTransition> possibleTransitions = ((PooslDebugTarget) thread.getDebugTarget())
					.getPossibleTransitions();
			for (TTransition transition : possibleTransitions) {
				if (transition.getProcessStep() != null) {
					possibleTransitionIds.put(transition.getProcessStep().getNode(),
							transition.getProcessStep().getHandle());
				} else if (transition.getCommunication() != null) {
					possibleTransitionIds.put(transition.getCommunication().getSender().getNode(),
							transition.getCommunication().getHandle());
					possibleTransitionIds.put(transition.getCommunication().getReceiver().getNode(),
							transition.getCommunication().getHandle());
				} else if (transition.getDelay() != null) {
					possibleTransitionIds.put(transition.getDelay().getNode(), transition.getDelay().getHandle());
					possibleDelayTransitionIds.add(transition.getDelay().getNode());
				} else {
					LOGGER.warning("possible transition not recognized: " + transition.toString());
				}
			}
			parseTree(thread);
			BigInteger breakpointNode = thread.getActiveBreakpointNode();
			if (breakpointNode != null) {
				for (TreeItem treeItem : possibleTransitionItems) {
					TExecutiontreeBase executiontreeBaseItem = (TExecutiontreeBase) treeItem.getData();
					if (executiontreeBaseItem.getHandle().equals(breakpointNode)) {
						tree.setSelection(treeItem);
						setDebugInstructionPointer(executiontreeBaseItem.getStmtHandle());
						updateVariables(executiontreeBaseItem);
					}
				}
				if (possibleTransitionItems.isEmpty()) {
					for (TreeItem treeItem : notPossibleTransitionItems) {
						TExecutiontreeBase executiontreeBaseItem = (TExecutiontreeBase) treeItem.getData();
						if (executiontreeBaseItem.getHandle().equals(breakpointNode)) {
							tree.setSelection(treeItem);
							setDebugInstructionPointer(executiontreeBaseItem.getStmtHandle());
							updateVariables(executiontreeBaseItem);
						}
					}
				}
			}
		}
		// Select the old selection if available
		TreeItem oldSelectedElement = findTreeItemWithHandle(oldSelectedHandle);
		if (oldSelectedElement != null) {
			tree.select(oldSelectedElement);
			setSelection(debugContext);
		}
	}

	public void clear() {
		possibleTransitionIds.clear();
		possibleTransitionItems.clear();
		notPossibleTransitionItems.clear();
		treeRoot.removeAll();
		treeRoot.setText("No information to display.");
		treeParent.layout();
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		setSelection(debugContext);
	}

	private void setSelection(PooslDebugElement debugElement) {
		if ((tree.getSelection().length > 0) && (tree.getSelection()[0].getData() != null)) {
			TExecutiontreeBase treeData = (TExecutiontreeBase) tree.getSelection()[0].getData();
			if (getViewSite().getSecondaryId() == null) {
				setDebugInstructionPointer(treeData.getStmtHandle());
			}
			updateVariables(treeData);
		} else {
			if (debugElement != null) {
				IDebugTarget iTarget = debugElement.getDebugTarget();
				if (iTarget instanceof PooslDebugTarget) {
					PooslDebugTarget target = (PooslDebugTarget) iTarget;
					Object eventData = debugElement;
					if (debugElement instanceof PooslThread) {
						PooslStackFrame stackFrame = (PooslStackFrame) ((PooslThread) debugElement).getStackFrame();
						if (stackFrame != null) {
							stackFrame.revertToOriginalVariables();
							eventData = stackFrame;
						}
					}
					target.fireEvent(new DebugEvent(eventData, DebugEvent.CHANGE, DebugEvent.CONTENT));
				}
			}
		}
	}

	private void updateVariables(TExecutiontreeBase treeData) {
		PooslDebugTarget target = getDebugTarget();
		if (target != null) {
			if (!treeData.getLocal().equals(BigInteger.ZERO)) {
				target.fireEvent(new DebugEvent(new ExecutionTreeContext(debugContext, treeData),
						DebugEvent.MODEL_SPECIFIC, PooslConstants.INSPECT_REQUEST));
			} else {
				if (debugContext != null) {
					PooslStackFrame stackframe = (PooslStackFrame) debugContext.getStackFrame();
					if (stackframe != null) {
						stackframe.revertToOriginalVariables();
						target.fireEvent(new DebugEvent(stackframe, DebugEvent.CHANGE, DebugEvent.CONTENT));
					}
				}
			}
		}
	}

	private void removeDebugInstructionPointer(Object object) {
		if (object instanceof PooslDebugElement) {
			PooslDebugElement element = (PooslDebugElement) object;
			InstructionPointerManager pointerManager = InstructionPointerManager.getDefault();
			if (element.getDebugTarget() != null && pointerManager != null) {
				pointerManager.removeAnnotations(element.getDebugTarget());
			}
		}
	}

	private void setDebugInstructionPointer(Integer stmtHandle) {
		if (debugContext != null) {
			PooslDebugTarget target = (PooslDebugTarget) debugContext.getDebugTarget();
			if (target.getPooslSourceMap().containsSourceMapping(stmtHandle)) {
				target.getPooslSourceMap().getSourceMapping(stmtHandle, new PooslSourceMappingListener(true) {
					public void requestedSourceMapping(final PooslSourceMapping mapping) {
						try {
							PooslDebugHelper.setDebugInstructionPointer(debugContext.getStackFrame(), mapping,
									getDebugTarget(), getSite().getWorkbenchWindow());
						} catch (PartInitException | DebugException e) {
							LOGGER.log(Level.WARNING, "Could not set debug instruction pointer.", e);
						}
						PooslPETView.this.setFocus();
					}
				});
			}
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		widgetSelected(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (((e.stateMask & SWT.CTRL) == SWT.CTRL) && e.keyCode == SWT.ARROW_DOWN) {
			TreeItem[] currentSelection = tree.getSelection();
			if (currentSelection.length > 0) {
				tree.select(getNextPossibleTransition(currentSelection[0]));
				setSelection(debugContext);
			}
		} else if (((e.stateMask & SWT.CTRL) == SWT.CTRL) && e.keyCode == SWT.ARROW_UP) {
			TreeItem[] currentSelection = tree.getSelection();
			if (currentSelection.length > 0) {
				tree.select(getPreviousPossibleTransition(currentSelection[0]));
				setSelection(debugContext);
			}
		} else if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
			TreeItem[] currentSelection = tree.getSelection();
			if (currentSelection.length > 0 && possibleTransitionIds
					.containsKey(((TExecutiontreeBase) currentSelection[0].getData()).getHandle())) {
				performTransition((TExecutiontreeBase) currentSelection[0].getData());
			}
		} else if (e.keyCode == SWT.KEYPAD_1 || e.keyCode == '1') {
			if (possibleTransitionItems.size() > 0) {
				performTransition((TExecutiontreeBase) possibleTransitionItems.get(0).getData());
			}
		} else if (e.keyCode == SWT.KEYPAD_2 || e.keyCode == '2') {
			if (possibleTransitionItems.size() > 1) {
				performTransition((TExecutiontreeBase) possibleTransitionItems.get(1).getData());
			}
		} else if (e.keyCode == SWT.KEYPAD_3 || e.keyCode == '3') {
			if (possibleTransitionItems.size() > 2) {
				performTransition((TExecutiontreeBase) possibleTransitionItems.get(2).getData());
			}
		} else if (e.keyCode == SWT.KEYPAD_4 || e.keyCode == '4') {
			if (possibleTransitionItems.size() > 3) {
				performTransition((TExecutiontreeBase) possibleTransitionItems.get(3).getData());
			}
		} else if (e.keyCode == SWT.KEYPAD_5 || e.keyCode == '5') {
			if (possibleTransitionItems.size() > 4) {
				performTransition((TExecutiontreeBase) possibleTransitionItems.get(4).getData());
			}
		} else if (e.keyCode == SWT.KEYPAD_6 || e.keyCode == '6') {
			if (possibleTransitionItems.size() > 5) {
				performTransition((TExecutiontreeBase) possibleTransitionItems.get(5).getData());
			}
		} else if (e.keyCode == SWT.KEYPAD_7 || e.keyCode == '7') {
			if (possibleTransitionItems.size() > 6) {
				performTransition((TExecutiontreeBase) possibleTransitionItems.get(6).getData());
			}
		} else if (e.keyCode == SWT.KEYPAD_8 || e.keyCode == '8') {
			if (possibleTransitionItems.size() > 7) {
				performTransition((TExecutiontreeBase) possibleTransitionItems.get(7).getData());
			}
		} else if (e.keyCode == SWT.KEYPAD_9 || e.keyCode == '9') {
			if (possibleTransitionItems.size() > 8) {
				performTransition((TExecutiontreeBase) possibleTransitionItems.get(8).getData());
			}
		}
	}

	private TreeItem getNextPossibleTransition(TreeItem startItem) {
		if (!possibleTransitionIds.isEmpty()) {
			boolean startSearching = false;
			List<TreeItem> treeItems = getTreeItemsRecursive(tree.getTopItem());
			if (startItem.equals(tree.getTopItem())) {
				startSearching = true;
			}
			for (int i = 0; i < treeItems.size(); i++) {
				if (startSearching) {
					if (possibleTransitionIds
							.containsKey(((TExecutiontreeBase) treeItems.get(i).getData()).getHandle())) {
						return treeItems.get(i);
					}
				} else if (treeItems.get(i).equals(startItem)) {
					startSearching = true;
				}
			}
		}
		// no next transition
		return null;
	}

	private List<TreeItem> getTreeItemsRecursive(TreeItem baseTreeItem) {
		List<TreeItem> items = new ArrayList<>();
		for (TreeItem treeItem : baseTreeItem.getItems()) {
			items.add(treeItem);
			if (treeItem.getItemCount() > 0) {
				items.addAll(getTreeItemsRecursive(treeItem));
			}
		}
		return items;
	}

	private TreeItem getPreviousPossibleTransition(TreeItem startItem) {
		if (!possibleTransitionIds.isEmpty()) {
			if (startItem.equals(tree.getTopItem())) {
				return null;
			}
			boolean startSearching = false;
			List<TreeItem> treeItems = getTreeItemsRecursive(tree.getTopItem());
			for (int i = treeItems.size() - 1; i > 0; i--) {
				if (startSearching) {
					if (possibleTransitionIds
							.containsKey(((TExecutiontreeBase) treeItems.get(i).getData()).getHandle())) {
						return treeItems.get(i);
					}
				} else if (treeItems.get(i).equals(startItem)) {
					startSearching = true;
				}
			}
		}
		// no previous transition
		return null;
	}

	private TreeItem findTreeItemWithHandle(BigInteger handle) {
		if (handle != null) {
			List<TreeItem> treeItems = getTreeItemsRecursive(tree.getTopItem());
			for (TreeItem treeItem : treeItems) {
				if (((TExecutiontreeBase) treeItem.getData()).getHandle().equals(handle)) {
					return treeItem;
				}
			}
		}
		return null;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// No action has to be taken.
	}

	private PooslDebugTarget getDebugTarget() {
		IDebugContextService service = ViewHelper.getDebugService(this);
		if (service != null) {
			ISelection activeContext = service.getActiveContext();
			Object context = null;
			if (activeContext instanceof TreeSelection) {
				context = ((TreeSelection) activeContext).getFirstElement();
			}
			PooslDebugTarget debugTarget = null;
			if (context instanceof PooslThread) {
				PooslThread thread = (PooslThread) context;
				debugTarget = (PooslDebugTarget) thread.getDebugTarget();
			} else if (context instanceof PooslDebugTarget) {
				debugTarget = (PooslDebugTarget) context;
			}
			return debugTarget;
		}
		return null;
	}

	private void createToolBarMenu() {
		getViewSite().getActionBars().getToolBarManager().add(processStepAction);
	}

	public Action createProcessStepAction() {
		ImageDescriptor stepIcon = null;
		try {
			stepIcon = ImageDescriptor.createFromURL(
					new URL("platform:/plugin/nl.esi.poosl.rotalumisclient/icons/icon_process_step.png"));
		} catch (MalformedURLException e) {
			LOGGER.log(Level.FINE, "Could not find step icon");
		}
		return new Action(Messages.ACTION_MENU_PROCESS_STEP, stepIcon) {
			@Override
			public void run() {
				PooslProcessStep.doProcessStep(debugContext);
			}
		};
	}
}