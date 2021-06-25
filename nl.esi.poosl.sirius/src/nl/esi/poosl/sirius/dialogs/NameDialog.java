package nl.esi.poosl.sirius.dialogs;

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

import nl.esi.poosl.sirius.helpers.NameHelper;

public class NameDialog extends TitleAreaDialog {
	private static final String ERROR_MESSAGE_INSTANCE = "Cannot create create %s. The name is not available or is empty.";
	private static final String ERROR_TITLE_INSTANCE = "Cannot create %s.";
	private static final String TITLE = "Creating a new %s.";
	private static final String DESCRIPTION = "Define a name and type.";
	private static final String LBL_NAME = "Name:";
	private static final String TXT_NAME = "new%s";

	public enum PooslClassType {
		CLUSTERCLASS, DATACLASS, PROCESSCLASS
	}

	private String name;
	private PooslClassType typeClass;
	private Text txtName;
	private Iterable<String> existingnames;

	public NameDialog(Shell parentShell, Iterable<String> names, PooslClassType type) {
		super(parentShell);
		typeClass = type;
		existingnames = names;
	}

	@Override
	public void create() {
		super.create();
		setTitle(String.format(TITLE, typeToString(typeClass)));
		setMessage(DESCRIPTION, IMessageProvider.INFORMATION);
		txtName.setFocus();
		txtName.selectAll();
	}

	private String typeToString(PooslClassType type) {
		String typeString;
		switch (type) {
		case CLUSTERCLASS:
			typeString = "cluster class";
			break;
		case DATACLASS:
			typeString = "data class";
			break;
		case PROCESSCLASS:
			typeString = "process class";
			break;
		default:
			typeString = "class";
			break;
		}
		return typeString;
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
		lbName.setText(LBL_NAME);

		GridData dataFirstName = new GridData();
		dataFirstName.grabExcessHorizontalSpace = true;
		dataFirstName.horizontalAlignment = GridData.FILL;
		dataFirstName.verticalAlignment = GridData.BEGINNING;
		dataFirstName.horizontalIndent = 10;

		txtName = new Text(composite, SWT.BORDER);
		txtName.setLayoutData(dataFirstName);

		String nameString;
		switch (typeClass) {
		case CLUSTERCLASS:
			nameString = "ClusterClass";
			break;
		case DATACLASS:
			nameString = "DataClass";
			break;
		case PROCESSCLASS:
			nameString = "ProcessClass";
			break;
		default:
			nameString = "Class";
			break;
		}

		if (name != null) {
			txtName.setText(name);
		} else {
			txtName.setText(NameHelper.getUniqueName(String.format(TXT_NAME, nameString), existingnames));
		}
	}

	@Override
	protected void okPressed() {
		saveInput();
		if (getName().isEmpty() || !NameHelper.isNameAvailable(getName(), existingnames)) {
			MessageDialog.openError(Display.getDefault().getActiveShell(),
					String.format(ERROR_TITLE_INSTANCE, typeToString(typeClass)),
					String.format(ERROR_MESSAGE_INSTANCE, typeToString(typeClass)));
		} else {
			super.okPressed();
		}
	}

	private void saveInput() {
		name = txtName.getText();
	}

	public String getName() {
		return name;
	}
}