package nl.esi.poosl.sirius.dialogs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import nl.esi.poosl.Declaration;
import nl.esi.poosl.sirius.dialogs.NewMethodDialog.TextDeclaration;
import nl.esi.poosl.xtext.custom.PooslCache;
import nl.esi.poosl.xtext.helpers.HelperFunctions;

public class NewVariableDialog extends TitleAreaDialog {
	private static final String ERROR_MESSAGE_INSTANCE = "Cannot define %s. The name is empty or already in use.";
	private static final String ERROR_TITLE_INSTANCE = "Cannot define %s.";
	private static final String TITLE = "Defining a %s.";
	private static final String DESCRIPTION = "Define a name and type.";
	private static final String LBL_VARIABLE = "Name:";
	private static final String TXT_VARIABLE = "new%s";

	// Data
	private String varName;
	private String varType;
	private final String[] dataclasses;
	private final List<String> variableNames;
	private final String dialogVarType;

	// UI Data Components
	private Text txtVarName;
	private Combo cbVarType;

	public NewVariableDialog(Shell parentShell, Resource resource, List<String> variableNames, String typeVariable) {
		super(parentShell);
		this.dialogVarType = typeVariable;
		this.variableNames = variableNames;
		dataclasses = PooslCache.get(resource).getDataClassMap().keySet().toArray(new String[0]);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public NewVariableDialog(Shell parentShell, String[] dataclasses, String typeVariable, List<String> variableNames) {
		super(parentShell);
		this.dialogVarType = typeVariable;
		this.dataclasses = dataclasses;
		this.variableNames = variableNames;
	}

	@Override
	public void create() {
		super.create();
		setTitle(String.format(TITLE, dialogVarType));
		setMessage(DESCRIPTION, IMessageProvider.INFORMATION);
		txtVarName.setFocus();
		txtVarName.selectAll();
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		area.setLayoutData(new GridData(GridData.FILL_BOTH));
		area.setLayout(new GridLayout());

		createUIComponents(area);
		populateUIComponents();
		return area;
	}

	private void createUIComponents(Composite container) {
		Composite composite = new Composite(container, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2, false);
		composite.setLayout(gridLayout);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		GridData dataFirstName = new GridData();
		dataFirstName.grabExcessHorizontalSpace = true;
		dataFirstName.horizontalAlignment = GridData.FILL;
		dataFirstName.verticalAlignment = GridData.BEGINNING;
		dataFirstName.horizontalIndent = 10;

		// Create Name label
		Label lbName = new Label(composite, SWT.NONE);
		lbName.setText(LBL_VARIABLE);

		// Create Name Textfield
		txtVarName = new Text(composite, SWT.BORDER);
		txtVarName.setLayoutData(dataFirstName);

		// Create Type label
		Label lblSelectClass = new Label(composite, SWT.NONE);
		lblSelectClass.setText("Type:");

		// Create Type Combo
		cbVarType = new Combo(composite, SWT.NONE);
		cbVarType.setLayoutData(dataFirstName);
	}

	private void populateUIComponents() {
		if (varName != null) {
			txtVarName.setText(varName);

		} else {
			txtVarName.setText(String.format(TXT_VARIABLE, dialogVarType));
			int index = 1;
			while (!variableNameAvailable(txtVarName.getText())) {
				txtVarName.setText(String.format(TXT_VARIABLE, dialogVarType) + index);
				index++;
			}
		}
		cbVarType.setItems(dataclasses);
		if (varType != null) {
			cbVarType.setText(varType);
		} else {
			cbVarType.setText(HelperFunctions.CLASS_NAME_OBJECT);
		}
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	// save content of the Text fields because they get disposed
	// as soon as the Dialog closes
	private void saveInput() {
		this.varName = txtVarName.getText();
		this.varType = cbVarType.getText();
	}

	@Override
	protected void okPressed() {
		saveInput();
		if (getName().isEmpty() || !doubleName(getName()) || !variableNameAvailable(getName())) {
			MessageDialog.openError(Display.getDefault().getActiveShell(),
					String.format(ERROR_TITLE_INSTANCE, dialogVarType),
					String.format(ERROR_MESSAGE_INSTANCE, dialogVarType));
		} else {
			super.okPressed();
		}
	}

	private boolean doubleName(String name) {
		Set<String> set = new HashSet<>();
		String[] seperate = name.split(",");
		for (int i = 0; i < seperate.length; i++) {
			if (!set.add(seperate[i].trim())) {
				return false;
			}
		}
		return true;
	}

	private boolean variableNameAvailable(String name) {
		String[] seperate = name.split(",");
		for (int i = 0; i < seperate.length; i++) {
			for (String variablename : variableNames) {
				if (variablename.equalsIgnoreCase(name)) {
					return false;
				}
			}
		}
		return true;
	}

	public String getName() {
		return varName;
	}

	public String getSelectedClass() {
		return varType;
	}

	public void setVariable(String name, String type) {
		this.varName = name;
		this.varType = type;
		String[] seperate = name.split(",");
		for (int i = 0; i < seperate.length; i++) {
			variableNames.remove(seperate[i]);
		}
	}

	public void setVariable(Declaration declaration) {
		StringBuilder varname = new StringBuilder();
		for (int i = 0; i < declaration.getVariables().size(); i++) {
			String newname = declaration.getVariables().get(i).getName();
			variableNames.remove(newname);
			varname = (i > 0) ? varname.append(",") : varname;
			varname.append(newname);
		}

		this.varName = varname.toString();
		this.varType = declaration.getType();
		String[] seperate = varName.split(",");
		for (int i = 0; i < seperate.length; i++) {
			variableNames.remove(seperate[i]);
		}
	}

	public void setVariable(TextDeclaration declaration) {
		StringBuilder varname = new StringBuilder();
		for (int i = 0; i < declaration.getVariables().size(); i++) {
			String newname = declaration.getVariables().get(i);
			variableNames.remove(newname);
			varname = (i > 0) ? varname.append(",") : varname;
			varname.append(newname);
		}

		this.varName = varname.toString();
		this.varType = declaration.getType();
		String[] seperate = varName.split(",");
		for (int i = 0; i < seperate.length; i++) {
			variableNames.remove(seperate[i]);
		}
	}
}