package nl.esi.poosl.sirius.navigator.edit;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.dialogs.IDEResourceInfoUtils;

@SuppressWarnings("restriction")
public class PooslOverwriteRunnable implements Runnable {
	private final IResource source;
	private final IResource destination;
	private int result;
	private final Shell messageShell;

	public PooslOverwriteRunnable(IResource writeSource, IResource writeDestination, Shell shell) {
		source = writeSource;
		destination = writeDestination;
		messageShell = shell;
	}

	public void run() {
		String message;
		int[] resultId = { IDialogConstants.YES_ID, IDialogConstants.YES_TO_ALL_ID, IDialogConstants.NO_ID,
				IDialogConstants.CANCEL_ID };
		String[] labels = new String[] { IDialogConstants.YES_LABEL, IDialogConstants.YES_TO_ALL_LABEL,
				IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL };

		if (destination.getType() == IResource.FOLDER) {
			if (homogenousResources(source, destination)) {
				message = NLS.bind(IDEWorkbenchMessages.CopyFilesAndFoldersOperation_overwriteMergeQuestion,
						destination.getFullPath().makeRelative());
			} else {
				if (destination.isLinked()) {
					message = NLS.bind(IDEWorkbenchMessages.CopyFilesAndFoldersOperation_overwriteNoMergeLinkQuestion,
							destination.getFullPath().makeRelative());
				} else {
					message = NLS.bind(IDEWorkbenchMessages.CopyFilesAndFoldersOperation_overwriteNoMergeNoLinkQuestion,
							destination.getFullPath().makeRelative());
				}
				resultId = new int[] { IDialogConstants.YES_ID, IDialogConstants.NO_ID, IDialogConstants.CANCEL_ID };
				labels = new String[] { IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL,
						IDialogConstants.CANCEL_LABEL };
			}
		} else {
			String[] bindings = new String[] { IDEResourceInfoUtils.getLocationText(destination),
					IDEResourceInfoUtils.getDateStringValue(destination), IDEResourceInfoUtils.getLocationText(source),
					IDEResourceInfoUtils.getDateStringValue(source) };
			message = NLS.bind(IDEWorkbenchMessages.CopyFilesAndFoldersOperation_overwriteWithDetailsQuestion,
					bindings);
		}
		MessageDialog dialog = new MessageDialog(messageShell,
				IDEWorkbenchMessages.CopyFilesAndFoldersOperation_resourceExists, null, message, MessageDialog.QUESTION,
				labels, 0) {
			@Override
			protected int getShellStyle() {
				return super.getShellStyle() | SWT.SHEET;
			}
		};
		dialog.open();
		if (dialog.getReturnCode() == SWT.DEFAULT) {
			// A window close returns SWT.DEFAULT, which has to be
			// mapped to a cancel
			result = IDialogConstants.CANCEL_ID;
		} else {
			result = resultId[dialog.getReturnCode()];
		}
	}

	public int getResult() {
		return result;
	}

	/**
	 * Returns whether the given resources are either both linked or both unlinked.
	 *
	 * @param source      source resource
	 * @param destination destination resource
	 * @return boolean <code>true</code> if both resources are either linked or
	 *         unlinked. <code>false</code> otherwise.
	 */
	public static boolean homogenousResources(IResource source, IResource destination) {
		boolean isSourceLinked = source.isLinked();
		boolean isDestinationLinked = destination.isLinked();
		return isSourceLinked == isDestinationLinked;
	}
}
