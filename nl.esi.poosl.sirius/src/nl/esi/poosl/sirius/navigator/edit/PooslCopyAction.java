package nl.esi.poosl.sirius.navigator.edit;

import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.SelectionListenerAction;

import org.eclipse.ui.part.ResourceTransfer;

/**
 * Action to copy the resource and its sirius diagrams Based on the not
 * extendable org.eclipse.ui.internal.navigator.resources.actions.CopyAction
 * 
 * @author kstaal *
 */

public class PooslCopyAction extends SelectionListenerAction {
	private static final String TOOLTIP = "Copy Resources and referenced diagrams";
	private static final String LABEL = "Copy (including Diagrams)";

	/**
	 * The id of this action.
	 */
	public static final String ID = "PooslCopyAction"; //$NON-NLS-1$

	/**
	 * The shell in which to show any dialogs.
	 */
	private Shell shell;

	/**
	 * System clipboard
	 */
	private Clipboard clipboard;

	/**
	 * Associated paste action. May be <code>null</code>
	 */
	private PooslPasteAction pasteAction;

	/**
	 * Creates a new action.
	 *
	 * @param shell     the shell for any dialogs
	 * @param clipboard a platform clipboard
	 */
	public PooslCopyAction(Shell shell, Clipboard clipboard) {
		super(LABEL);
		Assert.isNotNull(shell);
		Assert.isNotNull(clipboard);
		this.shell = shell;
		this.clipboard = clipboard;
		setToolTipText(TOOLTIP);
		setId(ID);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(this, "CopyHelpId"); //$NON-NLS-1$
	}

	/**
	 * Creates a new action.
	 *
	 * @param shell       the shell for any dialogs
	 * @param clipboard   a platform clipboard
	 * @param pasteAction a paste action
	 *
	 * @since 2.0
	 */
	public PooslCopyAction(Shell shell, Clipboard clipboard, PooslPasteAction pasteAction) {
		this(shell, clipboard);
		this.pasteAction = pasteAction;
	}

	/**
	 * The <code>CopyAction</code> implementation of this method defined on
	 * <code>IAction</code> copies the selected resources to the clipboard.
	 */
	@Override
	public void run() {
		List<? extends IResource> selectedResources = getSelectedResources();
		IResource[] resources = selectedResources.toArray(new IResource[selectedResources.size()]);

		// Get the file names and a string representation
		final int length = resources.length;
		int actualLength = 0;
		String[] fileNames = new String[length];
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			IPath location = resources[i].getLocation();
			// location may be null. See bug 29491.
			if (location != null) {
				fileNames[actualLength++] = location.toOSString();
			}
			if (i > 0) {
				buf.append("\n"); //$NON-NLS-1$
			}
			buf.append(resources[i].getName());
		}
		// was one or more of the locations null?
		if (actualLength < length) {
			String[] tempFileNames = fileNames;
			fileNames = new String[actualLength];
			for (int i = 0; i < actualLength; i++) {
				fileNames[i] = tempFileNames[i];
			}
		}
		setClipboard(resources, fileNames, buf.toString());

		// update the enablement of the paste action
		// workaround since the clipboard does not suppot callbacks
		if (pasteAction != null && pasteAction.getStructuredSelection() != null) {
			pasteAction.selectionChanged(pasteAction.getStructuredSelection());
		}
	}

	/**
	 * Set the clipboard contents. Prompt to retry if clipboard is busy.
	 *
	 * @param resources the resources to copy to the clipboard
	 * @param fileNames file names of the resources to copy to the clipboard
	 * @param names     string representation of all names
	 */
	private void setClipboard(IResource[] resources, String[] fileNames, String names) {
		try {
			// set the clipboard contents
			if (fileNames.length > 0) {
				clipboard.setContents(new Object[] { resources, fileNames, names }, new Transfer[] {
						ResourceTransfer.getInstance(), FileTransfer.getInstance(), TextTransfer.getInstance() });
			} else {
				clipboard.setContents(new Object[] { resources, names },
						new Transfer[] { ResourceTransfer.getInstance(), TextTransfer.getInstance() });
			}
		} catch (SWTError e) {
			if (e.code != DND.ERROR_CANNOT_SET_CLIPBOARD) {
				throw e;
			}
			if (MessageDialog.openQuestion(shell, "Problem with copy title", //$NON-NLS-1$
					"Problem with copy.")) { //$NON-NLS-1$
				setClipboard(resources, fileNames, names);
			}
		}
	}

	/**
	 * The <code>CopyAction</code> implementation of this
	 * <code>SelectionListenerAction</code> method enables this action if one or
	 * more resources of compatible types are selected.
	 */
	@Override
	protected boolean updateSelection(IStructuredSelection selection) {
		if (!super.updateSelection(selection)) {
			return false;
		}

		if (!getSelectedNonResources().isEmpty()) {
			return false;
		}

		List<? extends IResource> selectedResources = getSelectedResources();
		if (selectedResources.isEmpty()) {
			return false;
		}

		boolean projSelected = selectionIsOfType(IResource.PROJECT);
		boolean fileFoldersSelected = selectionIsOfType(IResource.FILE | IResource.FOLDER);
		if (!projSelected && !fileFoldersSelected) {
			return false;
		}

		// selection must be homogeneous
		if (projSelected && fileFoldersSelected) {
			return false;
		}

		// must have a common parent
		IContainer firstParent = selectedResources.get(0).getParent();
		if (firstParent == null) {
			return false;
		}

		for (IResource currentResource : selectedResources) {
			if (!currentResource.getParent().equals(firstParent)) {
				return false;
			}
			// resource location must exist
			if (currentResource.getLocationURI() == null) {
				return false;
			}
		}
		return true;
	}
}
