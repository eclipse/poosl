package org.eclipse.poosl.sirius.handlers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.poosl.sirius.helpers.ConvertHelper;
import org.eclipse.poosl.sirius.helpers.GraphicalEditorHelper;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.ide.IDE;

public class ProjectExplorerHandler extends AbstractHandler {
    private static final Logger LOGGER = Logger.getLogger(ProjectExplorerHandler.class.getName());

    private static final String COMMAND_EXPLORER_OPEN_SYSTEM = "org.eclipse.poosl.commands.sirius.explorer.opensystemdiagram"; //$NON-NLS-1$

    private static final String COMMAND_EXPLORER_OPEN_CLASS = "org.eclipse.poosl.commands.sirius.explorer.openclassdiagram"; //$NON-NLS-1$

    private static final String COMMAND_EXPLORER_OPEN_TEXTUAL = "org.eclipse.poosl.commands.sirius.explorer.opentextualeditor"; //$NON-NLS-1$

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);

        if (event.getCommand().getId().equals(COMMAND_EXPLORER_OPEN_CLASS)) {
            GraphicalEditorHelper.openGraphicalEditorFromSelection(selection, true);
        } else if (event.getCommand().getId().equals(COMMAND_EXPLORER_OPEN_SYSTEM)) {
            GraphicalEditorHelper.openGraphicalEditorFromSelection(selection, false);
        } else if (event.getCommand().getId().equals(COMMAND_EXPLORER_OPEN_TEXTUAL)) {
            try {
                IWorkbenchSite site = HandlerUtil.getActiveSite(event);
                IDE.openEditor(site.getPage(), ConvertHelper.convertISelectionToIFile(selection));
            } catch (PartInitException e) {
                LOGGER.log(Level.WARNING, "Could not open the texual editor of the selected file: " + e.getMessage());
            }
        } else {
            LOGGER.log(Level.WARNING, "Command id is unknown, opening default diagram.");
            GraphicalEditorHelper.openGraphicalEditorFromSelection(selection, true);
        }
        return null;
    }
}
