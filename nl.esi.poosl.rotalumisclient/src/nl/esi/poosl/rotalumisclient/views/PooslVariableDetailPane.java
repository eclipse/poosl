package nl.esi.poosl.rotalumisclient.views;

import java.util.logging.Level;
import java.util.logging.Logger;

import nl.esi.poosl.rotalumisclient.PooslConstants;
import nl.esi.poosl.rotalumisclient.debug.PooslValue;
import nl.esi.poosl.rotalumisclient.debug.PooslVariable;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.ui.IDetailPane;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.help.IWorkbenchHelpSystem;

public class PooslVariableDetailPane implements IDetailPane {
	private static final Logger LOGGER = Logger.getLogger(PooslVariableDetailPane.class.getName());
	private Text detailPaneLabel;

	@Override
	public void init(IWorkbenchPartSite partSite) {
		if (detailPaneLabel != null) {
			detailPaneLabel.setText("");
		}
	}

	@Override
	public Control createControl(Composite parent) {
		detailPaneLabel = new Text(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		detailPaneLabel.setEditable(false);
		GridData gd = new GridData(GridData.FILL_BOTH);
		detailPaneLabel.setLayoutData(gd);

		Display display = Display.getDefault();
		if (display != null) {
			detailPaneLabel.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
		}

		IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench != null) {
			IWorkbenchHelpSystem helpSystem = workbench.getHelpSystem();
			if (helpSystem != null) {
				helpSystem.setHelp(detailPaneLabel, "nl.esi.poosl.help.help_variables");
			}
		}

		return detailPaneLabel;
	}

	@Override
	public void dispose() {
		// Nothing to dispose
	}

	@Override
	public void display(IStructuredSelection selection) {
		if (selection == null || selection.isEmpty()) {
			detailPaneLabel.setText("");
		} else {
			if (selection.getFirstElement() instanceof PooslVariable) {
				PooslVariable var = (PooslVariable) selection.getFirstElement();
				try {
					detailPaneLabel.setText(((PooslValue) var.getValue()).getLiteral());
				} catch (DebugException e) {
					LOGGER.log(Level.WARNING, e.getMessage(), e);
				}
			}
		}
	}

	@Override
	public boolean setFocus() {
		return detailPaneLabel.setFocus();
	}

	@Override
	public String getID() {
		return PooslConstants.ID_POOSL_VARIABLESVIEW_DETAIL_PANE;
	}

	@Override
	public String getName() {
		return "POOSL detail pane";
	}

	@Override
	public String getDescription() {
		return "This detail pane shows the content of a selected POOSL Variable in more detail.";
	}
}
