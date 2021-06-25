package nl.esi.poosl.rotalumisclient.views;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchListener;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.contexts.DebugContextEvent;
import org.eclipse.debug.ui.contexts.IDebugContextListener;
import org.eclipse.debug.ui.contexts.IDebugContextService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Tracker;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import nl.esi.poosl.generatedxmlclasses.TInstanceType;
import nl.esi.poosl.rotalumisclient.debug.PooslDebugTarget;
import nl.esi.poosl.rotalumisclient.debug.PooslSequenceDiagramMessageProvider;
import nl.esi.poosl.rotalumisclient.debug.PooslThread;
import nl.esi.poosl.rotalumisclient.views.diagram.PooslDiagramMessage;
import nl.esi.poosl.rotalumisclient.views.diagram.PooslDrawableDiagramMessage;

public class PooslSequenceDiagramView extends ViewPart implements ISelectionProvider {
	private static final String CLEAR_MESSAGES = "Clear messages";
	private static final String COULD_NOT_SET_MESSAGE_FILTER_ON_SELECTION = "Could not set message filter on selection.";

	private static final Logger LOGGER = Logger.getLogger(PooslSequenceDiagramView.class.getName());
	private static final double LIFELINE_WIDTH = 100;
	// Store for locally used UI elements
	private Canvas canvas;
	private ScrollBar horizontalScrollBar;
	private ScrollBar verticalScrollBar;
	// Store of calculated number of lifelines and messages
	private int nrOfMessages = 1;
	private int nrOfLifeLines = 1;

	private double scaledLifelineMargin;

	private int lastProcessedSerialNumber;
	// Scrollbar last selection used to determine if the canvas should be
	// redrawn when new messages are added
	private int previousVerticalSelect;
	private int previousHorizontalSelect;
	// Store of scroll lock option from the views UI
	private boolean scrollLock;

	// Store of all source messages
	private PooslDiagramMessage[] messageArray;
	// Store of the messages that are drawn
	private final List<PooslDrawableDiagramMessage> drawMessages = new ArrayList<>();
	// Selection of visible lifelines based on the filter settings in the
	// debugtarget
	private final Map<String, TInstanceType> visibleLifeLines = new HashMap<>();
	// Selection of the lifelines that are drawn
	private final List<String> drawLifeLines = new ArrayList<>();
	// Store of the rectangles a user can click on and the corresponding object
	private Map<Rectangle, Object> mouseMap = new HashMap<>();
	// Store of the selection and selectionListeners used for the
	// ISelectionProvider interface functions
	private ISelection selection;
	private final List<ISelectionChangedListener> selectionListeners = new ArrayList<>();
	// Store of the last debug context to check if the context has changed and
	// messages should be reloaded
	private Object lastDebugContext;
	// Pixel size in terms of points, where a point is one seventy-second of
	// an inch.
	private double textPointSize;

	private final Listener scrollbarListener = new Listener() {
		@Override
		public void handleEvent(Event e) {
			update();
		}
	};

	private final SelectionListener menuSelectionListener = new SelectionListener() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			MenuItem pressedMenuItem = (MenuItem) e.getSource();
			PooslDebugTarget target = null;
			if (lastDebugContext instanceof PooslDebugTarget) {
				target = (PooslDebugTarget) lastDebugContext;
			}
			if ("Save as image...".equals(pressedMenuItem.getText())) {
				Image image = new Image(canvas.getDisplay(), canvas.getSize().x - canvas.getVerticalBar().getSize().x,
						canvas.getSize().y - canvas.getHorizontalBar().getSize().y);
				GC gc = new GC(canvas);
				gc.copyArea(image, 0, 0);
				gc.dispose();
				FileDialog fileDialog = new FileDialog(canvas.getShell(), SWT.SAVE);
				fileDialog.setText("Get Device File");
				fileDialog.setFilterExtensions(new String[] { "*.bmp", "*.jpg;*.jpeg", "*.png", "*.*" });
				fileDialog.setFilterNames(
						new String[] { "Bitmap (*.bmp)", "JPEG (*.jpg;*.jpeg)", "PNG (*.png)", "All Files (*.*)" });
				String fileName = fileDialog.open();
				if (fileName != null) {
					int format = SWT.IMAGE_BMP;
					if (fileName.toLowerCase().endsWith(".jpg"))
						format = SWT.IMAGE_JPEG;
					if (fileName.toLowerCase().endsWith(".jpeg"))
						format = SWT.IMAGE_JPEG;
					if (fileName.toLowerCase().endsWith(".png"))
						format = SWT.IMAGE_PNG;
					ImageLoader loader = new ImageLoader();
					loader.data = new ImageData[] { image.getImageData() };
					loader.save(fileName, format);
				}
			} else if ("Hide".equals(pressedMenuItem.getText())) {
				if (target.getPooslSequenceDiagramMessageProvider().isFilterSettingEnabled()
						&& target.getPooslSequenceDiagramMessageProvider().getMessageSerialNumber() > 0) {
					MessageDialog dialog = new MessageDialog(Display.getDefault().getActiveShell(), CLEAR_MESSAGES,
							null,
							"Hiding this lifeline will remove all messages from and to the lifeline.\nAre you sure you want to continue?",
							MessageDialog.CONFIRM, new String[] { "Yes", "No" }, 0);
					if (dialog.open() != MessageDialog.OK) {
						return;
					}
				}
				Entry<?, ?>[] newFilter = new Entry<?, ?>[visibleLifeLines.size()];
				int i = 0;
				for (Entry<String, TInstanceType> entry : visibleLifeLines.entrySet()) {
					if (!entry.getKey().equals(pressedMenuItem.getData())) {
						newFilter[i] = entry;
						i++;
					}
				}
				try {
					target.getPooslSequenceDiagramMessageProvider().setMessageFilter(newFilter);
				} catch (CoreException e1) {
					LOGGER.log(Level.WARNING, COULD_NOT_SET_MESSAGE_FILTER_ON_SELECTION, e1);
				}
			} else if ("Collapse".equals(pressedMenuItem.getText())) {
				if (target.getPooslSequenceDiagramMessageProvider().isFilterSettingEnabled()
						&& target.getPooslSequenceDiagramMessageProvider().getMessageSerialNumber() > 0) {
					MessageDialog dialog = new MessageDialog(Display.getDefault().getActiveShell(), CLEAR_MESSAGES,
							null,
							"Collapsing this lifeline will clear all messages.\nAre you sure you want to continue?",
							MessageDialog.CONFIRM, new String[] { "Yes", "No" }, 0);
					if (dialog.open() != MessageDialog.OK) {
						return;
					}
				}
				// Collapse the process or cluster class to its parent
				String instanceName = (String) pressedMenuItem.getData();
				String cluster = instanceName.substring(0, instanceName.lastIndexOf('/'));
				Map<String, TInstanceType> allInstances = target.getInstances();
				Entry<?, ?>[] newFilter = new Entry<?, ?>[allInstances.size()];
				int i = 0;
				for (Entry<String, TInstanceType> entry : allInstances.entrySet()) {
					if (entry.getKey().equals(cluster)
							|| (visibleLifeLines.containsKey(entry.getKey()) && !entry.getKey().startsWith(cluster))) {
						newFilter[i] = entry;
						i++;
					}
				}
				try {
					target.getPooslSequenceDiagramMessageProvider().setMessageFilter(newFilter);
				} catch (CoreException e1) {
					LOGGER.log(Level.WARNING, COULD_NOT_SET_MESSAGE_FILTER_ON_SELECTION, e1);
				}
				if (target.getPooslSequenceDiagramMessageProvider().isFilterSettingEnabled()) {
					target.getPooslSequenceDiagramMessageProvider().clearMessages();
				}
			} else if ("Expand".equals(pressedMenuItem.getText())) {
				if (target.getPooslSequenceDiagramMessageProvider().isFilterSettingEnabled()
						&& target.getPooslSequenceDiagramMessageProvider().getMessageSerialNumber() > 0) {
					MessageDialog dialog = new MessageDialog(Display.getDefault().getActiveShell(), CLEAR_MESSAGES,
							null,
							"Expanding this lifeline will clear all messages.\nAre you sure you want to continue?",
							MessageDialog.CONFIRM, new String[] { "Yes", "No" }, 0);
					if (dialog.open() != MessageDialog.OK) {
						return;
					}
				}
				String instanceName = (String) pressedMenuItem.getData();
				Map<String, TInstanceType> allInstances = target.getInstances();
				Entry<?, ?>[] newFilter = new Entry<?, ?>[allInstances.size()];
				int i = 0;
				for (Entry<String, TInstanceType> entry : allInstances.entrySet()) {
					if (entry.getKey().startsWith(instanceName) && !entry.getKey().equals(instanceName)) {
						if (!entry.getKey().substring(instanceName.length() + 1).contains("/")) {
							newFilter[i] = entry;
							i++;
						}
					} else if (visibleLifeLines.containsKey(entry.getKey()) && !entry.getKey().equals(instanceName)) {
						newFilter[i] = entry;
						i++;
					}
				}
				try {
					target.getPooslSequenceDiagramMessageProvider().setMessageFilter(newFilter);
				} catch (CoreException e1) {
					LOGGER.log(Level.WARNING, COULD_NOT_SET_MESSAGE_FILTER_ON_SELECTION, e1);
				}
				if (target.getPooslSequenceDiagramMessageProvider().isFilterSettingEnabled()) {
					target.getPooslSequenceDiagramMessageProvider().clearMessages();
				}
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// No action required
		}
	};

	private final MouseListener mouseListener = new MouseListener() {
		@Override
		public void mouseUp(MouseEvent e) {
			if (selection != null) {
				Object firstElement = ((StructuredSelection) selection).getFirstElement();
				if (firstElement instanceof PooslDiagramMessage) {
					notifySelectionListeners();
					try {
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
								.showView("org.eclipse.ui.views.PropertySheet");
					} catch (PartInitException e1) {
						LOGGER.log(Level.WARNING, "Could not open propertySheet", e1);
					}
				}
			}
		}

		@Override
		public void mouseDown(MouseEvent e) {
			boolean validMouseDown = false;
			for (Entry<Rectangle, Object> mouseMapEntry : mouseMap.entrySet()) {
				Rectangle rect = mouseMapEntry.getKey();
				if (rect.contains(e.x, e.y)) {
					validMouseDown = true;
					selection = new StructuredSelection(mouseMapEntry.getValue());
					if (e.button == 1 && mouseMapEntry.getValue() instanceof String) {
						Tracker tracker = new Tracker(canvas, SWT.NONE);
						tracker.setStippled(true);
						tracker.setRectangles(new Rectangle[] { rect, });
						if (tracker.open()) {
							Rectangle trackerRect = tracker.getRectangles()[0];
							int lifelineMod = (int) ((trackerRect.x - rect.x)
									/ (LIFELINE_WIDTH + scaledLifelineMargin));
							if (Math.abs(lifelineMod) > 0) {
								reorder(mouseMapEntry.getValue(), lifelineMod);
							}
						}
					} else if (e.button == 3 && mouseMapEntry.getValue() instanceof String) {
						String value = (String) mouseMapEntry.getValue();
						Menu menu = new Menu(canvas);
						MenuItem hideItem = new MenuItem(menu, SWT.PUSH);
						hideItem.setText("Hide");
						hideItem.addSelectionListener(menuSelectionListener);
						hideItem.setData(value);
						MenuItem collapseItem = new MenuItem(menu, SWT.PUSH);
						collapseItem.setText("Collapse");
						collapseItem.addSelectionListener(menuSelectionListener);
						collapseItem.setData(value);
						MenuItem explodeItem = new MenuItem(menu, SWT.PUSH);
						explodeItem.setText("Expand");
						explodeItem.addSelectionListener(menuSelectionListener);
						explodeItem.setData(value);
						if (visibleLifeLines.get(value) == TInstanceType.PROCESS) {
							explodeItem.setEnabled(false);
						}
						if (value.lastIndexOf('/') == 0) {
							collapseItem.setEnabled(false);
						}
						canvas.setMenu(menu);
					}
				}
			}
			if (!validMouseDown) {
				Menu menu = new Menu(canvas);
				MenuItem hideItem = new MenuItem(menu, SWT.PUSH);
				hideItem.setText("Save as image...");
				hideItem.addSelectionListener(menuSelectionListener);
				canvas.setMenu(menu);

				// No valid selection so invalidate the selection
				selection = null;
			}
		}

		@Override
		public void mouseDoubleClick(MouseEvent e) {
			// No action required
		}
	};

	private final MouseMoveListener mouseMoveListener = new MouseMoveListener() {
		@Override
		public void mouseMove(MouseEvent e) {
			for (Entry<Rectangle, Object> mouseMapEntry : mouseMap.entrySet()) {
				Rectangle rect = mouseMapEntry.getKey();
				if (rect.contains(e.x, e.y)) {
					Object value = mouseMapEntry.getValue();
					if (value instanceof PooslDiagramMessage) {
						canvas.setToolTipText(((PooslDiagramMessage) mouseMapEntry.getValue()).getFormattedPayLoad());
					} else if (value instanceof String) {
						if (visibleLifeLines.containsKey(value.toString())) {
							if (visibleLifeLines.get(value) == TInstanceType.PROCESS) {
								canvas.setToolTipText("<<process>>\n" + value.toString());
							} else if (visibleLifeLines.get(value) == TInstanceType.CLUSTER) {
								canvas.setToolTipText("<<cluster>>\n" + value.toString());
							}
						}
					} else if (value instanceof BigDecimal) {
						canvas.setToolTipText(value.toString());
					}
					return;
				}
			}
			canvas.setToolTipText(null);
		}
	};

	private final MouseWheelListener mouseWheelListener = new MouseWheelListener() {
		@Override
		public void mouseScrolled(MouseEvent e) {
			mouseMoveListener.mouseMove(e);
			update();
		}
	};

	private final KeyListener keyListener = new KeyListener() {
		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.keyCode) {
			case SWT.HOME:
				if (verticalScrollBar != null) {
					verticalScrollBar.setSelection(verticalScrollBar.getMinimum());
					update();
				}
				break;
			case SWT.END:
				if (verticalScrollBar != null) {
					verticalScrollBar.setSelection(verticalScrollBar.getMaximum());
					update();
				}
				break;
			default:
				break;
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.keyCode) {
			case SWT.ARROW_UP:
				if (verticalScrollBar != null) {
					int newSelection = verticalScrollBar.getSelection() - 1;
					if (newSelection < verticalScrollBar.getMinimum()) {
						newSelection = verticalScrollBar.getMinimum();
					}
					verticalScrollBar.setSelection(newSelection);
					update();
				}
				break;
			case SWT.ARROW_DOWN:
				if (verticalScrollBar != null) {
					int newSelection = verticalScrollBar.getSelection() + 1;
					if (newSelection > verticalScrollBar.getMaximum()) {
						newSelection = verticalScrollBar.getMaximum();
					}
					verticalScrollBar.setSelection(newSelection);
					update();
				}
				break;
			case SWT.ARROW_LEFT:
				if (horizontalScrollBar != null) {
					int newSelection = verticalScrollBar.getSelection() - 1;
					if (newSelection < horizontalScrollBar.getMinimum()) {
						newSelection = horizontalScrollBar.getMinimum();
					}
					horizontalScrollBar.setSelection(newSelection);
					update();
				}
				break;
			case SWT.ARROW_RIGHT:
				if (horizontalScrollBar != null) {
					int newSelection = verticalScrollBar.getSelection() + 1;
					if (newSelection > horizontalScrollBar.getMaximum()) {
						newSelection = horizontalScrollBar.getMaximum();
					}
					horizontalScrollBar.setSelection(newSelection);
					update();
				}
				break;
			case SWT.PAGE_UP:
				if (verticalScrollBar != null) {
					int newSelection = verticalScrollBar.getSelection() - nrOfMessages;
					if (newSelection > verticalScrollBar.getMaximum()) {
						newSelection = verticalScrollBar.getMaximum();
					}
					verticalScrollBar.setSelection(newSelection);
					update();
				}
				break;
			case SWT.PAGE_DOWN:
				if (verticalScrollBar != null) {
					int newSelection = verticalScrollBar.getSelection() + nrOfMessages;
					if (newSelection > verticalScrollBar.getMaximum()) {
						newSelection = verticalScrollBar.getMaximum();
					}
					verticalScrollBar.setSelection(newSelection);
					update();
				}
				break;
			default:
				break;
			}
		}
	};

	private final IDebugContextListener sequenceDiagramDebugContextListener = new IDebugContextListener() {
		@Override
		public void debugContextChanged(DebugContextEvent event) {
			ISelection eventContext = event.getContext();
			if (eventContext instanceof TreeSelection) {
				TreeSelection treeSelection = (TreeSelection) eventContext;
				PooslDebugTarget target = null;
				if (treeSelection.getFirstElement() instanceof PooslThread) {
					PooslThread thread = (PooslThread) treeSelection.getFirstElement();
					target = (PooslDebugTarget) thread.getDebugTarget();
				} else if (treeSelection.getFirstElement() instanceof PooslDebugTarget) {
					target = (PooslDebugTarget) treeSelection.getFirstElement();
				}
				setDebugTarget(target);
			}
		}
	};

	private void setDebugTarget(PooslDebugTarget target) {
		if (target != lastDebugContext && target != null) {
			lastDebugContext = target;
			if (target.isTerminated()) {
				clearView();
			} else {
				// Reload the messageBufferMaximumSize to make sure it
				// is in sync with the debugtarget

				PooslSequenceDiagramMessageProvider messageProvider = target.getPooslSequenceDiagramMessageProvider();

				lastProcessedSerialNumber = 0;
				drawMessages.clear();

				setOrder(messageProvider.getMessageOrder());
				setFilter(messageProvider.getMessageFilter());
				update(messageProvider.getMessages(), false,
						target.getPooslSequenceDiagramMessageProvider().getMessageSerialNumber());
				messageProvider.updateSequenceDiagramViewEventSetting();
				canvas.redraw();
			}
		}
	}

	private ILaunchListener launchListener = new ILaunchListener() {
		@Override
		public void launchRemoved(ILaunch launch) {
			// Local variable is needed to avoid NullPointerException on isTerminated
			Object debugContext = lastDebugContext;
			if (debugContext instanceof PooslDebugTarget) {
				if (((PooslDebugTarget) debugContext).isTerminated()) {
					lastDebugContext = null;
				}
			}
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
	public void createPartControl(Composite parent) {
		canvas = new Canvas(parent, SWT.V_SCROLL | SWT.H_SCROLL);
		// Pixel size in terms of points, where a point is one seventy-second of
		// an inch.
		Display display = Display.getDefault();
		if (display != null) {
			textPointSize = display.getDPI().y / 72.0;
		} else {
			textPointSize = 0;
		}

		canvas.addPaintListener(new PooslSequenceDiagramViewPaintListener());
		canvas.addMouseListener(mouseListener);
		canvas.addMouseMoveListener(mouseMoveListener);
		canvas.addMouseWheelListener(mouseWheelListener);
		canvas.addKeyListener(keyListener);
		horizontalScrollBar = canvas.getHorizontalBar();
		horizontalScrollBar.setValues(0, 0, 1, 1, 1, 3);
		horizontalScrollBar.addListener(SWT.Selection, scrollbarListener);
		verticalScrollBar = canvas.getVerticalBar();
		verticalScrollBar.setValues(0, 0, 1, 1, 1, 3);
		verticalScrollBar.addListener(SWT.Selection, scrollbarListener);

		getSite().setSelectionProvider(this);
		IDebugContextService service = ViewHelper.getDebugService(this);
		if (service != null) {
			service.addDebugContextListener(sequenceDiagramDebugContextListener);
			// Check if there already is an active debugtarget that has messages
			if (service.getActiveContext() instanceof TreeSelection) {
				TreeSelection treeSelection = (TreeSelection) service.getActiveContext();
				if (treeSelection.getFirstElement() instanceof PooslDebugTarget) {
					setDebugTarget((PooslDebugTarget) treeSelection.getFirstElement());
				}
			}
		}

		IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench != null) {
			workbench.getHelpSystem().setHelp(canvas, "nl.esi.poosl.help.help_sequence_diagram");
		}

		DebugPlugin plugin = DebugPlugin.getDefault();
		if (plugin != null) {
			ILaunchManager launchManager = plugin.getLaunchManager();
			if (launchManager != null) {
				launchManager.addLaunchListener(launchListener);
			}
		}
	}

	private void setOrder(List<String> messageOrder) {
		drawLifeLines.clear();
		drawLifeLines.addAll(messageOrder);
	}

	private void clearView() {
		selection = null;
		drawLifeLines.clear();
		if (messageArray != null) {
			Arrays.fill(messageArray, null);
		}
		lastProcessedSerialNumber = 0;
		drawMessages.clear();
		verticalScrollBar.setMaximum(1);
		horizontalScrollBar.setMaximum(1);
		// After the clear get the ordering and filter from the debugTarget
		if (lastDebugContext instanceof PooslDebugTarget && !((PooslDebugTarget) lastDebugContext).isTerminated()) {
			setOrder(((PooslDebugTarget) lastDebugContext).getPooslSequenceDiagramMessageProvider().getMessageOrder());
			setFilter(
					((PooslDebugTarget) lastDebugContext).getPooslSequenceDiagramMessageProvider().getMessageFilter());
		} else {
			lastDebugContext = null;
		}
		canvas.redraw();
	}

	public void clearView(PooslDebugTarget disposeTarget) {
		if (lastDebugContext != null && lastDebugContext == disposeTarget) {
			clearView();
		}
	}

	public void update() {
		update(messageArray, true, lastProcessedSerialNumber);
	}

	public void update(PooslDiagramMessage[] messageArray, boolean internalUpdate, int serialNumber) {
		if (messageArray == null)
			return;
		if (!internalUpdate) {
			this.messageArray = messageArray.clone();
		}
		int startIndex = Math.max(lastProcessedSerialNumber, serialNumber - messageArray.length);
		boolean messageAdded = false;
		for (int i = startIndex; i < serialNumber; i++) {
			PooslDiagramMessage msg = messageArray[i % messageArray.length];
			PooslDrawableDiagramMessage drawableMessage = getFilteredDrawableMessage(msg);
			if (drawableMessage != null) {
				drawableMessage.setSerialNumber(i);
				drawMessages.add(drawableMessage);
				messageAdded = true;
			}
		}

		// Clear messages that are no longer in the array.
		List<PooslDrawableDiagramMessage> messagesToRemove = new ArrayList<>();
		for (PooslDrawableDiagramMessage drawMessage : drawMessages) {
			if (drawMessage.getSerialNumber() < serialNumber - messageArray.length) {
				messagesToRemove.add(drawMessage);
			}
		}
		drawMessages.removeAll(messagesToRemove);
		if (!verticalScrollBar.isDisposed()) {
			if (drawMessages.isEmpty()) {
				verticalScrollBar.setMaximum(1);
			} else {
				verticalScrollBar.setMaximum(drawMessages.size());
			}
			if (!scrollLock && messageAdded) {
				int newSelection = drawMessages.size() - nrOfMessages;
				verticalScrollBar.setSelection(newSelection);
				canvas.redraw();
			} else {
				verticalScrollBar
						.setSelection(verticalScrollBar.getSelection() - (serialNumber - lastProcessedSerialNumber));
			}
			horizontalScrollBar.setMaximum(drawLifeLines.size());
		}

		if (previousVerticalSelect != verticalScrollBar.getSelection()
				|| drawMessages.size() <= verticalScrollBar.getSelection() + nrOfMessages && messageAdded) {
			canvas.redraw();
			previousVerticalSelect = verticalScrollBar.getSelection();
		}
		if (previousHorizontalSelect != horizontalScrollBar.getSelection()) {
			canvas.redraw();
			previousHorizontalSelect = horizontalScrollBar.getSelection();
		}
		if (internalUpdate) {
			canvas.redraw();
		}
		if (scrollLock && (serialNumber - lastProcessedSerialNumber) > 0) {
			canvas.redraw();
		}
		lastProcessedSerialNumber = serialNumber;
	}

	private PooslDrawableDiagramMessage getFilteredDrawableMessage(PooslDiagramMessage msg) {
		String fromProcessName = getVisibleLifeLineName(msg.getSendProcess());
		if (!fromProcessName.isEmpty()) {
			String toProcessName = getVisibleLifeLineName(msg.getReceiveProcess());
			if (!toProcessName.isEmpty() && !fromProcessName.equals(toProcessName)) {
				return new PooslDrawableDiagramMessage(msg, getLifelineIndexByName(fromProcessName),
						getLifelineIndexByName(toProcessName));
			}
		}
		return null;
	}

	private int getLifelineIndexByName(String lifelineName) {
		for (int i = 0; i < drawLifeLines.size(); i++) {
			String lifeline = drawLifeLines.get(i);
			if (lifeline.equals(lifelineName)) {
				return i;
			}
		}
		drawLifeLines.add(lifelineName);
		return drawLifeLines.size() - 1;
	}

	private String getVisibleLifeLineName(String fromProcess) {
		if (visibleLifeLines.containsKey(fromProcess)) {
			return fromProcess;
		} else {
			int lastIndex = fromProcess.lastIndexOf('/');
			while (lastIndex != -1) {
				String fromProcessPart = fromProcess.substring(0, lastIndex);
				if (visibleLifeLines.containsKey(fromProcessPart)) {
					return fromProcessPart;
				}
				lastIndex = fromProcessPart.lastIndexOf('/');
			}
		}
		return "";
	}

	class PooslSequenceDiagramViewPaintListener implements PaintListener {
		// Margin statics
		private static final double LEFT_MARGIN = 80;
		// LifeLine statics
		private static final double LIFELINE_HEIGHT = 40;
		private static final double LIFELINE_HORIZONTAL_MARGIN = 20;
		private static final double LIFELINE_VERTICAL_MARGIN = 20;

		// Message statics
		private static final double MESSAGE_LINE_HEIGHT = 40;
		private static final double MESSAGE_LINE_MARGIN = 10;
		private static final double ARROW_SIZE = 10;

		private Font smallFont;
		private Font largeFont;
		private static final String NOTHING_TO_DISPLAY = "Nothing to display.";

		@Override
		public void paintControl(PaintEvent e) {
			Display display = Display.getDefault();
			if (display == null) {
				return;
			}
			GC gc = e.gc;
			// Color the background of the view white
			gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
			Rectangle drawableArea = canvas.getClientArea();
			gc.fillRectangle(drawableArea);
			// Check if the fonts are declared
			checkFonts(gc, display);
			// Create a new mouseMap to store the clickable elements.
			// Do not clear the mouseMap here to make sure that any existing
			// uses of the current version are still valid (can still be used by
			// drag drop at the moment of redraw)
			mouseMap = new HashMap<>();
			// Check if there is something to draw
			if (drawMessages.isEmpty() && drawLifeLines.isEmpty()) {
				// Nothing to draw so display a message instead
				gc.setFont(largeFont);
				Point stringExtend = gc.stringExtent(NOTHING_TO_DISPLAY);
				int textX = drawableArea.width / 2 - stringExtend.x / 2;
				int textY = drawableArea.height / 2 - stringExtend.y / 2;
				gc.drawText(NOTHING_TO_DISPLAY, textX, textY);
			} else {
				// Calculate # of messages and lifelines that will fit the
				// screen
				double topMargin = LIFELINE_VERTICAL_MARGIN + LIFELINE_HEIGHT;
				nrOfMessages = (int) ((drawableArea.height - topMargin) / (MESSAGE_LINE_HEIGHT + MESSAGE_LINE_MARGIN));
				nrOfLifeLines = (int) ((drawableArea.width - LEFT_MARGIN)
						/ (LIFELINE_WIDTH + LIFELINE_HORIZONTAL_MARGIN));
				if (nrOfLifeLines >= drawLifeLines.size()) {
					nrOfLifeLines = drawLifeLines.size();
					scaledLifelineMargin = (int) ((drawableArea.width - LEFT_MARGIN - nrOfLifeLines * LIFELINE_WIDTH
							- LIFELINE_HORIZONTAL_MARGIN) / (nrOfLifeLines - 1));
				} else {
					scaledLifelineMargin = LIFELINE_HORIZONTAL_MARGIN;
				}
				// Update the size of the thumbs in the scrollbars
				verticalScrollBar.setThumb(nrOfMessages);
				horizontalScrollBar.setThumb(nrOfLifeLines);
				// Draw the lifelines based on the horizontal scroll bar
				// position
				int lifelineIndex = 0;
				for (int i = horizontalScrollBar.getSelection(); i < drawLifeLines.size(); i++) {
					drawLifeLine(gc, lifelineIndex, drawLifeLines.get(i));
					lifelineIndex++;
				}
				// Draw the messages based on the vertical scroll bar position
				int messageIndex = 0;
				for (int i = verticalScrollBar.getSelection(); i < verticalScrollBar.getSelection()
						+ verticalScrollBar.getThumb(); i++) {
					if (drawMessages.size() <= i) {
						break;
					}
					PooslDrawableDiagramMessage msg = drawMessages.get(i);
					drawMessage(gc, messageIndex, msg.getFrom() - horizontalScrollBar.getSelection(),
							msg.getTo() - horizontalScrollBar.getSelection(), msg, i);
					messageIndex++;
				}
			}
		}

		private void checkFonts(GC gc, Display display) {
			FontData[] fontData = gc.getFont().getFontData();
			if (smallFont == null) {
				fontData[0].setHeight((int) Math.round(11 / textPointSize));
				smallFont = new Font(display, fontData);
			}
			if (largeFont == null) {
				fontData[0].setHeight((int) Math.round(13 / textPointSize));
				largeFont = new Font(display, fontData);
			}
		}

		private void drawLifeLine(GC gc, int position, String lifelineName) {
			// Calculate the size and position for the rectangle of the lifeline
			double rectX = (position * (LIFELINE_WIDTH + scaledLifelineMargin)) + LEFT_MARGIN;
			double rectY = LIFELINE_VERTICAL_MARGIN;
			Rectangle rect = new Rectangle((int) rectX, (int) rectY, (int) LIFELINE_WIDTH, (int) LIFELINE_HEIGHT);
			// Draw the rectangle of the lifeline and store as clickable element
			gc.setLineStyle(SWT.LINE_SOLID);
			gc.drawRectangle(rect);
			mouseMap.put(rect, lifelineName);
			// Draw the type of the lifeline with a smal font
			gc.setFont(smallFont);
			TInstanceType inspectType = visibleLifeLines.get(lifelineName);
			String type = "";
			if (inspectType.equals(TInstanceType.PROCESS)) {
				type = "<<process>>";
			} else if (inspectType.equals(TInstanceType.CLUSTER)) {
				type = "<<cluster>>";
			}
			gc.drawText(type, (int) (rect.x + (LIFELINE_WIDTH - gc.stringExtent(type).x) / 2), (int) rectY + 2, true);
			// Draw the class name with a larger font
			gc.setFont(largeFont);
			// Split the process name on hierarchy and only get the last part
			String[] splittedLifeLine = lifelineName.split("/");
			String lifelineText = splittedLifeLine[splittedLifeLine.length - 1];
			Point stringSize = gc.stringExtent(lifelineText);
			while (stringSize.x >= LIFELINE_WIDTH) {
				lifelineText = lifelineText.substring(0, lifelineText.length() - 4) + "...";
				stringSize = gc.stringExtent(lifelineText);
			}
			double textX = rect.x + (LIFELINE_WIDTH - stringSize.x) / 2;
			if (textX <= rect.x) {
				textX = rect.x + 1.0;
			}
			double textY = rect.y + ((LIFELINE_HEIGHT / 2.0) - stringSize.y / 3.0);
			gc.drawText(lifelineText, (int) textX, (int) textY, true);
			// Draw the line
			int lineX = rect.x + (rect.width / 2);
			int lineY = rect.y + rect.height;
			gc.setLineStyle(SWT.LINE_DASH);
			gc.drawLine(lineX, lineY, lineX, 1000);
		}

		private void drawMessage(GC gc, int messagePosition, int fromposition, int toposition,
				PooslDrawableDiagramMessage msg, int realMessagePosition) {
			// Draw line
			double lineX1 = fromposition * (LIFELINE_WIDTH + scaledLifelineMargin) + LEFT_MARGIN + LIFELINE_WIDTH / 2;
			double lineX2 = toposition * (LIFELINE_WIDTH + scaledLifelineMargin) + LEFT_MARGIN + LIFELINE_WIDTH / 2;
			double heightOffset = LIFELINE_VERTICAL_MARGIN + LIFELINE_HEIGHT;
			double lineY = heightOffset + (messagePosition * (MESSAGE_LINE_HEIGHT + MESSAGE_LINE_MARGIN))
					+ MESSAGE_LINE_MARGIN + MESSAGE_LINE_HEIGHT / 2;
			gc.setLineStyle(SWT.LINE_SOLID);
			gc.drawLine((int) lineX1, (int) lineY, (int) lineX2, (int) lineY);
			// Draw arrow
			double arrowX;
			if (lineX1 < lineX2) {
				// Arrow to the right
				arrowX = lineX2 - ARROW_SIZE;
			} else {
				// Arrow to the left
				arrowX = lineX2 + ARROW_SIZE;
			}
			double arrowY1 = lineY - ARROW_SIZE;
			double arrowY2 = lineY + ARROW_SIZE;
			gc.drawLine((int) arrowX, (int) arrowY1, (int) lineX2, (int) lineY);
			gc.drawLine((int) arrowX, (int) arrowY2, (int) lineX2, (int) lineY);
			// Draw Text
			String text = msg.getContent();
			text = text.replaceAll("[\r\n\t]+", " ");
			Point stringSize = gc.stringExtent(text);
			int maxTextLength = (int) (Math.abs((fromposition - toposition) * (LIFELINE_WIDTH + scaledLifelineMargin))
					- ARROW_SIZE - MESSAGE_LINE_MARGIN);
			while (stringSize.x >= maxTextLength) {
				text = text.substring(0, text.length() - 4) + "...";
				stringSize = gc.stringExtent(text);
			}
			double textY = lineY - MESSAGE_LINE_MARGIN * 2;
			double textX;
			if (lineX1 < lineX2) {
				textX = lineX1 + ARROW_SIZE;
			} else {
				textX = lineX2 + ARROW_SIZE;
			}
			textX = textX + (maxTextLength / 2.0) - (stringSize.x / 2.0);
			gc.drawText(text, (int) textX, (int) textY);
			Point stringExtend = gc.stringExtent(text);
			mouseMap.put(new Rectangle((int) textX, (int) textY, stringExtend.x, stringExtend.y),
					msg.getSourceMessage());
			// Draw timestamp
			gc.fillRectangle(0, (int) (heightOffset + (messagePosition * (MESSAGE_LINE_HEIGHT + MESSAGE_LINE_MARGIN))),
					(int) LEFT_MARGIN, (int) (MESSAGE_LINE_HEIGHT + MESSAGE_LINE_MARGIN));
			String simulatedTimeString = msg.getSourceMessage().getSimulatedTime().toString();
			Double simulatedTime = Double.parseDouble(simulatedTimeString);
			if (simulatedTimeString.length() > 7) {
				NumberFormat formatter = new DecimalFormat("###.###E0");
				formatter.setMinimumFractionDigits(3);
				simulatedTimeString = formatter.format(simulatedTime);
			}
			Point textExtend = gc.textExtent(simulatedTimeString);
			if (messagePosition >= 1) {
				if (!drawMessages.get(realMessagePosition - 1).getSourceMessage().getSimulatedTime()
						.equals(msg.getSourceMessage().getSimulatedTime())) {
					gc.drawText(simulatedTimeString, 5, (int) lineY - textExtend.y / 2);
					mouseMap.put(new Rectangle(5, (int) lineY - textExtend.y / 2, textExtend.x, textExtend.y),
							msg.getSourceMessage().getSimulatedTime());
				}
			} else {
				gc.drawText(simulatedTimeString, 5, (int) lineY - textExtend.y / 2);
				mouseMap.put(new Rectangle(5, (int) lineY - textExtend.y / 2, textExtend.x, textExtend.y),
						msg.getSourceMessage().getSimulatedTime());
			}
		}
	}

	private void reorder(Object value, int lifelineMod) {
		int oldIndex = drawLifeLines.indexOf(value);
		int newIndex = oldIndex + lifelineMod;
		if (newIndex < 0) {
			newIndex = 0;
		} else if (newIndex >= drawLifeLines.size()) {
			newIndex = drawLifeLines.size() - 1;
		}
		drawLifeLines.add(newIndex, drawLifeLines.remove(oldIndex));
		drawMessages.clear();
		int previousLastProcessedSerialNumber = lastProcessedSerialNumber;
		lastProcessedSerialNumber = lastProcessedSerialNumber - messageArray.length;
		if (lastProcessedSerialNumber < 0) {
			lastProcessedSerialNumber = 0;
		}
		if (lastDebugContext instanceof PooslDebugTarget) {
			try {
				((PooslDebugTarget) lastDebugContext).getPooslSequenceDiagramMessageProvider()
						.setMessageOrder(drawLifeLines);
			} catch (CoreException e) {
				LOGGER.log(Level.WARNING, "Could not set message order when reordering.", e);
			}
		}
		update(messageArray, true, previousLastProcessedSerialNumber);
		canvas.redraw();
	}

	@Override
	public void setFocus() {
		canvas.setFocus();
	}

	public void setScrollLock(boolean scrollLock) {
		this.scrollLock = scrollLock;
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionListeners.add(listener);
	}

	@Override
	public ISelection getSelection() {
		return selection;
	}

	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		selectionListeners.remove(listener);
	}

	@Override
	public void setSelection(ISelection selection) {
		this.selection = selection;
	}

	private void notifySelectionListeners() {
		for (ISelectionChangedListener selectionListener : selectionListeners) {
			selectionListener.selectionChanged(new SelectionChangedEvent(this, this.selection));
		}
	}

	public void setFilter(Map<String, TInstanceType> messageFilter) {
		for (String instanceName : messageFilter.keySet()) {
			if (!drawLifeLines.contains(instanceName)) {
				drawLifeLines.add(instanceName);
			}
		}
		List<String> drawLifeLinesToRemove = new ArrayList<>();
		for (String drawLifeLine : drawLifeLines) {
			if (!messageFilter.containsKey(drawLifeLine)) {
				drawLifeLinesToRemove.add(drawLifeLine);
			}
		}
		drawLifeLines.removeAll(drawLifeLinesToRemove);

		visibleLifeLines.clear();
		visibleLifeLines.putAll(messageFilter);
		int previousLastProcessedSerialNumber = lastProcessedSerialNumber;
		lastProcessedSerialNumber = 0;
		drawMessages.clear();
		update(messageArray, true, previousLastProcessedSerialNumber);
	}

	@Override
	public void dispose() {
		IDebugContextService debugService = ViewHelper.getDebugService(this);
		if (debugService != null) {
			debugService.removeDebugContextListener(sequenceDiagramDebugContextListener);
		}

		DebugPlugin display = DebugPlugin.getDefault();
		if (display != null) {
			ILaunchManager launchManager = display.getLaunchManager();
			if (launchManager != null) {
				launchManager.removeLaunchListener(launchListener);
			}
		}

		super.dispose();
	}
}