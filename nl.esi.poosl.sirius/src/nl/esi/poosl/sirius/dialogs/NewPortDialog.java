package nl.esi.poosl.sirius.dialogs;

import nl.esi.poosl.InstantiableClass;
import nl.esi.poosl.Port;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class NewPortDialog extends TitleAreaDialog {
	private static final String ERROR_MESSAGE = "Cannot create create port. The name is empty or already in use.";
	private static final String ERROR_TITLE = "Cannot create port.";
	private static final String TITLE = "Creating a new port.";
	private static final String DESCRIPTION = "Define a name.";
	private static final String LBL_PORT = "Name:";
	private static final String TXT_PORT = "newPort";

	private String name;
	private Text txtName;
	private final InstantiableClass owner;

	public NewPortDialog(Shell parentShell, InstantiableClass instclass) {
		super(parentShell);
		owner = instclass;
	}

	@Override
	public void create() {
		super.create();
		setTitle(TITLE);
		setMessage(DESCRIPTION, IMessageProvider.INFORMATION);
		txtName.setFocus();
		txtName.selectAll();
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		area.setLayoutData(new GridData(GridData.FILL_BOTH));
		area.setLayout(new GridLayout());
		addNamingField(area);

		return area;
	}

	private void addNamingField(Composite container) {
		Composite composite = new Composite(container, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2, false);
		composite.setLayout(gridLayout);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

		Label lbName = new Label(composite, SWT.NONE);
		lbName.setText(LBL_PORT);

		GridData dataFirstName = new GridData();
		dataFirstName.grabExcessHorizontalSpace = true;
		dataFirstName.horizontalAlignment = GridData.FILL;
		dataFirstName.verticalAlignment = GridData.BEGINNING;
		dataFirstName.horizontalIndent = 10;

		txtName = new Text(composite, SWT.BORDER);
		txtName.setLayoutData(dataFirstName);
		txtName.setText(TXT_PORT);

		int index = 1;
		while (!nameIsAvailable(txtName.getText())) {
			txtName.setText(TXT_PORT + index);
			index++;
		}
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	@Override
	protected void okPressed() {
		// save content of the Text fields because they get disposed
		// as soon as the Dialog closes
		this.name = txtName.getText();

		if (getName().isEmpty() || !nameIsAvailable(name)) {
			MessageDialog.openError(Display.getDefault().getActiveShell(), ERROR_TITLE, ERROR_MESSAGE);
		} else {
			super.okPressed();
		}
	}

	private boolean nameIsAvailable(String newname) {
		for (Port port : owner.getPorts()) {
			if (newname.equalsIgnoreCase(port.getName())) {
				return false;
			}
		}
		return true;
	}

	public String getName() {
		return name;
	}
}