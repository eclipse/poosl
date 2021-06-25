package nl.esi.poosl.rotalumisclient.views;

import org.eclipse.debug.core.DebugException;
import org.eclipse.e4.ui.model.application.ui.MElementContainer;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.advanced.MPlaceholder;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartSashContainer;
import org.eclipse.e4.ui.model.application.ui.basic.MPartSashContainerElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimmedWindow;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.model.application.ui.basic.MWindowElement;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;

import nl.esi.poosl.rotalumisclient.PooslConstants;
import nl.esi.poosl.rotalumisclient.debug.PooslDebugTarget;
import nl.esi.poosl.rotalumisclient.debug.PooslThread;
import nl.esi.poosl.rotalumisclient.views.stacktraceview.StackTraceVariableView;
import nl.esi.poosl.rotalumisclient.views.stacktraceview.StackTraceView;

/**
 * Helper class to create new seperate windows. </br>
 * {@link WindowCreater#getWindowForError(IWorkbenchPartSite, PooslDebugTarget)}
 * creates if need a new window containing {@link StackTraceView} and
 * {@link StackTraceVariableView} containing stacktrace information when an
 * error occurs. </br>
 * {@link WindowCreater#getWindowForThread(IWorkbenchPartSite, PooslThread)}
 * creates if needed a new window containing {@link PooslPETView} and
 * {@link PooslVariablesView} to show information on a specific thread
 * determined by the views second ID.
 * 
 * @author staalk
 *
 */
public class WindowCreater {
	private static final String POOSL_THREAD_WINDOW = "POOSL_THREAD_WINDOW";

	private WindowCreater() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Creates or finds the thread window that contains the seperate PETView and
	 * VariableView for the given thread. The created views get a secondid, see
	 * {@link ViewHelper#getSecondId(PooslThread)}. It creates a new window if both
	 * views are not in the same window.
	 * 
	 * @param site   {@link IWorkbenchPartSite} used as context to search for the
	 *               views and creating the window.
	 * @param thread The {@link PooslThread} that should be shown in the window
	 * @return A {@link MWindow} with a PET view and Variable view of the thread
	 * @throws DebugException
	 * @throws PartInitException
	 */
	public static MWindow getWindowForThread(IWorkbenchPartSite site, PooslThread thread)
			throws DebugException, PartInitException {
		MWindow window = null;
		IViewPart petView = null;
		IViewPart varView = null;

		String secondID = ViewHelper.getSecondId(thread);

		IViewReference petReference = site.getPage().findViewReference(PooslConstants.ID_POOSL_PETVIEW, secondID);
		if (petReference != null) {
			petView = petReference.getView(true);
		}

		IViewReference varReference = site.getPage().findViewReference(PooslConstants.ID_POOSL_VARIABLESVIEW, secondID);
		if (varReference != null) {
			varView = varReference.getView(true);
		}
		if (petView == null) {
			petView = site.getPage().showView(PooslConstants.ID_POOSL_PETVIEW, secondID, IWorkbenchPage.VIEW_VISIBLE);
		}
		if (varView == null) {
			varView = site.getPage().showView(PooslConstants.ID_POOSL_VARIABLESVIEW, secondID,
					IWorkbenchPage.VIEW_VISIBLE);
		}
		EModelService service = site.getService(EModelService.class);
		window = findWindow(petView, varView);
		if (window == null) {
			window = WindowCreater.createCombinedWindow(service, petView, varView, new WindowShape(450, 600, false));
			window.setLabel(thread.getDebugTarget().getName() + " " + thread.getName());
		} else {
			service.bringToTop(window);
		}
		return window;
	}

	/**
	 * If either the {@link StackTraceView} or the {@link StackTraceVariableView} is
	 * not shown a new window is created with them in it. If they are already shown
	 * not window is created and null is returned. (The views dont have to be in the
	 * same Window.)
	 * 
	 * @param site   {@link IWorkbenchPartSite} used as context to search for the
	 *               views and creating the window.
	 * @param target {@link PooslDebugTarget} is given to the {@link StackTraceView}
	 *               after creation
	 * @return {@link MWindow} if one is created otherwise it returns null
	 * @throws DebugException
	 * @throws PartInitException
	 */
	public static MWindow getWindowForError(IWorkbenchPartSite site, PooslDebugTarget target) throws PartInitException {
		IViewReference traceReference = site.getPage().findViewReference(PooslConstants.ID_POOSL_STACKTRACEVIEW);
		IViewReference frameReference = site.getPage()
				.findViewReference(PooslConstants.ID_POOSL_STACKTRACEVARIABLEVIEW);
		if (traceReference != null && frameReference != null) {
			IViewPart traceView = traceReference.getView(true);
			IViewPart frameView = frameReference.getView(true);

			frameView.getViewSite().getPage().showView(PooslConstants.ID_POOSL_STACKTRACEVARIABLEVIEW, null,
					IWorkbenchPage.VIEW_ACTIVATE);
			traceView.getViewSite().getPage().showView(PooslConstants.ID_POOSL_STACKTRACEVIEW, null,
					IWorkbenchPage.VIEW_ACTIVATE);
			return null;
		} else {
			IViewPart traceView = site.getPage().showView(PooslConstants.ID_POOSL_STACKTRACEVIEW);
			IViewPart frameView = site.getPage().showView(PooslConstants.ID_POOSL_STACKTRACEVARIABLEVIEW);

			if (traceView instanceof StackTraceView) {
				((StackTraceView) traceView).setDebugContext(target);
			}

			EModelService service = site.getService(EModelService.class);
			MWindow window = findWindow(traceView, frameView);
			if (window == null) {
				window = createCombinedWindow(service, traceView, frameView, new WindowShape(900, 600, true));
				window.setLabel("StackTrace");
			} else {
				service.bringToTop(window);
			}
			return window;
		}
	}

	/**
	 * Finds the seperate window which has the PET and Variable View
	 * 
	 * @param petView
	 * @param varView
	 * @return null if the views are not in their own window otherwise return the
	 *         window
	 */
	private static MTrimmedWindow findWindow(IViewPart petView, IViewPart varView) {
		MUIElement petElement = petView.getSite().getService(MPart.class);
		MUIElement varElement = varView.getSite().getService(MPart.class);

		if (petElement.getCurSharedRef() != null) {
			petElement = petElement.getCurSharedRef();
		}
		if (varElement.getCurSharedRef() != null) {
			varElement = varElement.getCurSharedRef();
		}

		if (petElement.getParent() != null && petElement.getParent().getParent() != null
				&& varElement.getParent() != null && varElement.getParent().getParent() != null) {
			MElementContainer<MUIElement> petContainer = petElement.getParent().getParent();
			MElementContainer<MUIElement> varContainer = varElement.getParent().getParent();
			if ((petContainer == varContainer) && (petContainer.getParent() != null)) {
				MUIElement parentElement = petContainer.getParent();
				if (parentElement instanceof MTrimmedWindow && parentElement.getElementId() != null
						&& parentElement.getElementId().equals(POOSL_THREAD_WINDOW)) {
					return (MTrimmedWindow) parentElement;
				}

			}
		}
		return null;

	}

	/**
	 * Creates a new window with both Views and based on the {@link WindowShape}
	 * 
	 * @param service
	 * @param firstView
	 * @param secondView
	 * @param shape
	 * @return
	 * @throws PartInitException
	 * @see {@link WindowShape}
	 */
	private static MWindow createCombinedWindow(EModelService service, IViewPart firstView, IViewPart secondView,
			WindowShape shape) {
		MPart topPart = firstView.getSite().getService(MPart.class);
		MPart botPart = secondView.getSite().getService(MPart.class);

		MWindow window = service.getTopLevelWindowFor(topPart);
		MPerspective thePersp = service.getPerspectiveFor(topPart);

		MWindowElement container = wrapElementsInContainer(service, topPart, botPart, shape.isHorizontal());
		MTrimmedWindow newWindow = createNewWindow(service, shape);
		newWindow.setElementId(POOSL_THREAD_WINDOW);
		newWindow.getChildren().add(container);

		if (thePersp != null) {
			thePersp.getWindows().add(newWindow);
		} else if (window != null) {
			window.getWindows().add(newWindow);
		}
		return newWindow;
	}

	/**
	 * Puts both elements in a container. Vital for arranging them in a window.
	 * 
	 * @param service
	 * @param firstElement
	 * @param secondElement
	 * @param horizontal    The direction the views will be placed compared to each
	 *                      other
	 * @return {@link MPartSashContainer} as {@link MWindowElement}
	 */
	private static MWindowElement wrapElementsInContainer(EModelService service, MPartSashContainerElement firstElement,
			MPartSashContainerElement secondElement, boolean horizontal) {
		if (firstElement.getCurSharedRef() != null) {
			firstElement = firstElement.getCurSharedRef();
		}
		if (secondElement.getCurSharedRef() != null) {
			secondElement = secondElement.getCurSharedRef();
		}

		MPartSashContainer sashcontainer = service.createModelElement(MPartSashContainer.class);
		firstElement.getParent().getChildren().remove(firstElement);
		secondElement.getParent().getChildren().remove(secondElement);
		MPartStack newTop = wrapElementForWindow(service, firstElement);
		MPartStack newBot = wrapElementForWindow(service, secondElement);
		if (newTop != null) {
			newTop.setContainerData("5000");
		}
		if (newBot != null) {
			newBot.setContainerData("5000");
		}
		sashcontainer.setHorizontal(horizontal);
		sashcontainer.getChildren().add(newTop);
		sashcontainer.getChildren().add(newBot);
		return sashcontainer;

	}

	/**
	 * Create new Window based on the shape
	 * 
	 * @param service
	 * @param shape
	 * @return
	 */
	private static MTrimmedWindow createNewWindow(EModelService service, WindowShape shape) {
		MTrimmedWindow newWindow = service.createModelElement(MTrimmedWindow.class);
		newWindow.setX(shape.getX());
		newWindow.setY(shape.getY());
		newWindow.setWidth(shape.getWidth());
		newWindow.setHeight(shape.getHeight());
		return newWindow;
	}

	private static MPartStack wrapElementForWindow(EModelService service, MPartSashContainerElement element) {
		if (element instanceof MPlaceholder) {
			MUIElement ref = ((MPlaceholder) element).getRef();
			if (ref instanceof MPart) {
				MPartStack newPS = service.createModelElement(MPartStack.class);
				newPS.getChildren().add((MPlaceholder) element);
				return newPS;
			}
		} else if (element instanceof MPart) {
			MPartStack newPS = service.createModelElement(MPartStack.class);
			newPS.getChildren().add((MPart) element);
			return newPS;
		}
		return null;
	}

	/**
	 * Struct to pass window settings
	 * 
	 * @author staalk
	 *
	 */
	private static class WindowShape {
		private final int x;
		private final int y;
		private final int width;
		private final int height;
		private final boolean horizontal;

		public WindowShape(int width, int height, boolean horizontal) {
			super();
			this.x = 100;
			this.y = 100;
			this.width = width;
			this.height = height;
			this.horizontal = horizontal;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}

		public boolean isHorizontal() {
			return horizontal;
		}
	}

	/**
	 * Closes all thread windows within the {@link IWorkbenchPage}.
	 * 
	 * @param page {@link IWorkbenchPage} In this page it searches for views having
	 *             a secondary id starting with
	 *             {@link PooslConstants#THREAD_VIEW_ID} and hides them.
	 */
	public static void closeAllThreadWindows(IWorkbenchPage page) {
		for (IViewReference iViewReference : page.getViewReferences()) {
			if (iViewReference.getSecondaryId() != null
					&& iViewReference.getSecondaryId().startsWith(PooslConstants.THREAD_VIEW_ID)) {
				// A view can be shown in multiple pages, it is disposed when its hidden in all
				// of them
				page.hideView(iViewReference);
			}
		}
	}
}
