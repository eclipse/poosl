package nl.esi.poosl.rotalumisclient.debug;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.debug.internal.ui.DebugUIPlugin;
import org.eclipse.debug.internal.ui.DelegatingModelPresentation;
import org.eclipse.debug.internal.ui.InstructionPointerAnnotation;
import org.eclipse.debug.internal.ui.InstructionPointerManager;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.contexts.IDebugContextManager;
import org.eclipse.debug.ui.contexts.IDebugContextService;
import org.eclipse.debug.ui.sourcelookup.SourceLookupDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;

import nl.esi.poosl.generatedxmlclasses.TExecutiontree;
import nl.esi.poosl.generatedxmlclasses.TExecutiontreeBase;
import nl.esi.poosl.rotalumisclient.PooslConstants;
import nl.esi.poosl.rotalumisclient.sourcemapping.PooslSourceMap;
import nl.esi.poosl.rotalumisclient.sourcemapping.PooslSourceMapping;
import nl.esi.poosl.rotalumisclient.views.debugview.PooslDebugTreeItem;

/**
 * @author Jeroen van Schelven
 *
 */
@SuppressWarnings("restriction")
public final class PooslDebugHelper {

	private PooslDebugHelper() {
		// Hide the public constructor
	}

	/**
	 * Get the named thread from the array of threads by it's name
	 * 
	 * @param threads The array of threads to search
	 * @param name    The name of the thread to search for
	 * @return The PooslThread that was found or null if it does not exist in the
	 *         give threads.
	 * @throws DebugException
	 */
	public static PooslThread getThreadByName(IThread[] threads, String name) throws DebugException {
		if (threads != null) {
			for (int i = 0; i < threads.length; i++) {
				if (name.equals(threads[i].getName())) {
					return (PooslThread) threads[i];
				}
			}
		}
		return null;
	}

	/**
	 * Checks if the given debugTarget is the current debug context.
	 * 
	 * @param target The debugTarget to check
	 * @return True if the debugTarget is the current debug context. False
	 *         otherwise.
	 */
	public static boolean isActiveDebugTarget(final PooslDebugTarget target) {
		PooslDebugTarget currentTarget = getCurrentDebugTarget();
		return currentTarget != null && currentTarget.equals(target);
	}

	/**
	 * Get's the current PooslDebugTarget if any from the DebugContextManager. <br>
	 * <strong>(This function should always be called from the UIThread!)</strong>
	 * 
	 * @return The current PooslDebugTarget or null if there is none.
	 */
	public static PooslDebugTarget getCurrentDebugTarget() {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IDebugContextManager manager = DebugUITools.getDebugContextManager();
		if (window != null && manager != null) {
			IDebugContextService service = manager.getContextService(window);
			if (service != null) {
				ISelection activeContext = service.getActiveContext();
				if (activeContext instanceof TreeSelection) {
					Object context = ((TreeSelection) activeContext).getFirstElement();
					if (context instanceof PooslThread) {
						PooslThread thread = (PooslThread) context;
						return (PooslDebugTarget) thread.getDebugTarget();
					} else if (context instanceof PooslDebugTarget) {
						return (PooslDebugTarget) context;
					} else if (context instanceof PooslDebugTreeItem) {
						return (PooslDebugTarget) ((PooslDebugTreeItem) context).getThreadsList().get(0)
								.getDebugTarget();
					}
				}
			}
		}
		return null;
	}

	/**
	 * Show an error message to the user with the supplied title and message. This
	 * function will work when called from a non UI thread.
	 * 
	 * @param title
	 * @param message
	 */
	public static void showErrorMessage(final String title, final String message) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				MessageDialog.openError(Display.getDefault().getActiveShell(), title, message);
			}
		});
	}

	public static void showInfoMessage(final String title, final String message) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				MessageDialog.openInformation(Display.getDefault().getActiveShell(), title, message);
			}
		});
	}

	public static Map<IStackFrame, List<PooslValue>> getPooslValuesByObjectHandle(List<IStackFrame> frames,
			BigInteger statementHandle) throws DebugException {
		Map<IStackFrame, List<PooslValue>> frameValues = new HashMap<>();

		for (IStackFrame stackframe : frames) {
			List<PooslValue> values = new ArrayList<>();
			if (stackframe != null) {
				for (int j = 0; j < stackframe.getVariables().length; j++) {
					IVariable variable = stackframe.getVariables()[j];
					if (variable.getValue() != null) {
						PooslValue value = (PooslValue) variable.getValue();
						if (value.getObject().equals(statementHandle)) {
							values.add(value);
						}
						if (value.hasVariables()) {
							values.addAll(getSubValuesByObjectHandle(statementHandle, value));
						}
					}
				}
			}
			if (!values.isEmpty()) {
				frameValues.put(stackframe, values);
			}
		}

		return frameValues;
	}

	// Recursive function to help the above function
	private static List<PooslValue> getSubValuesByObjectHandle(BigInteger statementHandle, PooslValue value)
			throws DebugException {
		List<PooslValue> values = new ArrayList<>();
		for (int i = 0; i < value.getVariables().length; i++) {
			PooslValue v = (PooslValue) value.getVariables()[i].getValue();
			if (statementHandle.equals(v.getObject())) {
				values.add(v);
			}
			if (v.hasVariables()) {
				values.addAll(getSubValuesByObjectHandle(statementHandle, v));
			}
		}
		return values;
	}

	public static List<PooslStackFrame> threadsToStackFrames(IThread[] threads) {
		List<PooslStackFrame> frames = new ArrayList<>();
		for (int i = 0; i < threads.length; i++) {
			IStackFrame stackFrame = ((PooslThread) threads[i]).getStackFrame();
			if (stackFrame != null) {
				frames.add((PooslStackFrame) stackFrame);
			}
		}
		return frames;
	}

	public static void setDebugInstructionPointer(IStackFrame stackframe, PooslSourceMapping sourceMapping,
			PooslDebugTarget target, IWorkbenchWindow window) throws DebugException, PartInitException {
		if ((target != null) && (sourceMapping != PooslSourceMap.EMPTY_MAPPING)
				&& (stackframe instanceof PooslStackFrame)) {
			PooslStackFrame frame = (PooslStackFrame) stackframe;
			frame.setSourceMapping(sourceMapping);
			Object sourceElement = getSourceElement(frame, target);
			if (sourceElement != null) {
				setEditorHighlight(frame, sourceElement, window);
			}
		}
	}

	private static void setEditorHighlight(PooslStackFrame stackframe, Object sourceElement, IWorkbenchWindow window)
			throws DebugException, PartInitException {
		IDebugModelPresentation presentation = ((DelegatingModelPresentation) DebugUIPlugin.getModelPresentation())
				.getPresentation(PooslConstants.DEBUG_MODEL_ID);
		IEditorInput editor = presentation.getEditorInput(sourceElement);
		ITextEditor textEditor = (ITextEditor) window.getActivePage().openEditor(editor,
				presentation.getEditorId(editor, sourceElement), false);
		InstructionPointerAnnotation annotation = new InstructionPointerAnnotation(stackframe,
				"org.eclipse.debug.ui.currentIP", "POOSL Instruction pointer", null);
		InstructionPointerManager pointerManager = InstructionPointerManager.getDefault();
		if (pointerManager != null) {
			pointerManager.removeAnnotations(textEditor);
			pointerManager.addAnnotation(textEditor, stackframe, annotation);
		}
		textEditor.selectAndReveal(stackframe.getCharStart(), stackframe.getCharEnd() - stackframe.getCharStart());
	}

	private static Object getSourceElement(PooslStackFrame stackframe, PooslDebugTarget target) {
		PooslSourceLocator locator = (PooslSourceLocator) target.getLaunch().getSourceLocator();
		if (locator.getSourceContainers().length == 0) {
			Display display = Display.getDefault();
			if (display != null) {
				MessageDialog dialog = new MessageDialog(display.getActiveShell(), "No source found", null,
						"The source for this element could not be found because no source containers are specified in the launch configuration.",
						MessageDialog.INFORMATION, new String[] { "OK", "Add Source Container" }, 0);

				int result = dialog.open();
				if (result == 1) {
					SourceLookupDialog sDialog = new SourceLookupDialog(display.getActiveShell(), locator);
					sDialog.open();
				}
			}
		}
		return locator.getSourceElement(stackframe);
	}

	public static PooslThread getThread(PooslThread[] threads, BigInteger listHandle) {
		for (int i = 0; i < threads.length; i++) {
			TExecutiontree executiontree = threads[i].getExecutiontree();

			if (executiontree != null) {
				TExecutiontreeBase containingTreeBase = findTreeBaseWithListHandle(executiontree, listHandle);

				if (containingTreeBase == null) {
					// listhandle not found in the execution tree maybe its a
					// variable listhandle
					IStackFrame frame = threads[i].getStackFrame();
					if (frame != null) {
						BigInteger topHandle;
						try {
							topHandle = findTopVariableListHandle(frame.getVariables(), listHandle);
							if (topHandle != null) {
								containingTreeBase = findTreeBaseWithListHandle(executiontree, topHandle);
							}
						} catch (DebugException e) {
							// do nothing
						}
					}
				}

				if (containingTreeBase != null) {
					return threads[i];
				}
			}
		}
		return null;
	}

	private static TExecutiontreeBase findTreeBaseWithListHandle(TExecutiontree executionTree, BigInteger listHandle) {
		for (JAXBElement<? extends TExecutiontreeBase> tempElement : executionTree
				.getSequentialOrMethodCallOrParallel()) {
			TExecutiontreeBase base = tempElement.getValue();
			if (base.getGlobal().equals(listHandle) || base.getLocal().equals(listHandle)) {
				return base;
			}
		}
		return null;
	}

	private static BigInteger findTopVariableListHandle(IVariable[] vars, BigInteger listHandle) throws DebugException {
		for (IVariable iVariable : vars) {
			IValue value = iVariable.getValue();
			if (value instanceof PooslValue) {
				BigInteger valSubHandle = ((PooslValue) value).getSubHandle();
				BigInteger valListHandle = ((PooslValue) value).getListHandle();

				// return listhandle when this variable OR a subvariable has the
				// listhandle
				if (listHandle.compareTo(valSubHandle) == 0
						|| findTopVariableListHandle(value.getVariables(), listHandle) != null) {
					return valListHandle;
				}
			}
		}
		return null;
	}
}
