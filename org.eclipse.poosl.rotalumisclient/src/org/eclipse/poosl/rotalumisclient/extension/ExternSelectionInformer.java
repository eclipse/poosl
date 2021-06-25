package org.eclipse.poosl.rotalumisclient.extension;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.poosl.rotalumisclient.Messages;
import org.eclipse.poosl.rotalumisclient.PooslConstants;
import org.eclipse.poosl.rotalumisclient.debug.PooslDebugTarget;
import org.eclipse.poosl.rotalumisclient.views.debugview.PooslDebugTreeItem;
import org.eclipse.poosl.rotalumisclient.views.diagram.PooslDiagramMessage;
import org.eclipse.swt.widgets.Display;

public class ExternSelectionInformer {
    private static final Logger LOGGER = Logger.getLogger(ExternSelectionInformer.class.getName());

    @Execute
    public void executeInformDebugSelection(Object debugItem) {
        IDebugTarget target = null;
        String diagram = "system";

        if (debugItem instanceof PooslDebugTarget) {
            target = (PooslDebugTarget) debugItem;
        } else if (debugItem instanceof PooslDebugTreeItem) {
            PooslDebugTreeItem treeItem = (PooslDebugTreeItem) debugItem;

            diagram = "";
            target = treeItem.getDebugTarget();

            Object item = treeItem;
            while (item instanceof PooslDebugTreeItem) {
                PooslDebugTreeItem nameItem = (PooslDebugTreeItem) item;
                diagram = "/" + nameItem.getName() + diagram;
                item = nameItem.getParent();
            }
        }

        if (target instanceof PooslDebugTarget) {
            PooslDebugTarget pooslTarget = (PooslDebugTarget) target;

            if (!target.isTerminated() && !isTargetEdited(pooslTarget)) {
                try {
                    ILaunchConfiguration launchConfiguration = pooslTarget.getLaunch().getLaunchConfiguration();

                    PooslDiagramMessage lastMessage = pooslTarget.getPooslSequenceDiagramMessageProvider().getLastMessage();
                    ExternDebugMessage message = null;

                    if (lastMessage != null) {
                        message = new ExternDebugMessage(launchConfiguration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_PORT, ""),
                                launchConfiguration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_RELATIVE_PATH, ""), lastMessage);
                    }
                    ExternDebugItem selecteditem = new ExternDebugItem(diagram, launchConfiguration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_RELATIVE_PATH, ""),
                            launchConfiguration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_PROJECT, ""),
                            launchConfiguration.getAttribute(PooslConstants.CONFIGURATION_ATTRIBUTE_SERVER_PORT, ""));
                    for (IPooslDebugInformer extension : ExtensionHelper.getExtensions()) {
                        executeExtensionSelection(extension, selecteditem, message);
                    }

                } catch (CoreException | InstantiationException e) {
                    LOGGER.log(Level.WARNING, e.getMessage(), e);
                }
            }
        }
    }

    private boolean isTargetEdited(PooslDebugTarget pooslTarget) {
        boolean edited = pooslTarget.isEdited();
        if (edited) {
            Display.getDefault().asyncExec(new Runnable() {
                @Override
                public void run() {

                    MessageDialog dialog = new MessageDialog(Display.getDefault().getActiveShell(), Messages.DIALOG_EDITED_TITLE, MessageDialog.getImage(Dialog.DLG_IMG_MESSAGE_INFO),
                            Messages.DIALOG_EDITED_TEXT, MessageDialog.INFORMATION, new String[] { Messages.DIALOG_EDITED_BT_OK }, 0);
                    dialog.open();
                }
            });
        }
        return edited;
    }

    private void executeExtensionSelection(final IPooslDebugInformer extension, final ExternDebugItem debugItem, final ExternDebugMessage message) {
        ISafeRunnable runnable = new ISafeRunnable() {
            @Override
            public void handleException(Throwable e) {
                LOGGER.log(Level.FINE, "Exception in client" + e.getMessage());
            }

            @Override
            public void run() throws Exception {
                extension.debugSelectionChanged(debugItem, message);
            }
        };
        SafeRunner.run(runnable);
    }
}
