package nl.esi.poosl.sirius.dialogs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import nl.esi.poosl.Declaration;
import nl.esi.poosl.Variable;
import nl.esi.poosl.sirius.helpers.CreationHelper;
import nl.esi.poosl.xtext.custom.PooslCache;

abstract class NewMethodDialog extends TitleAreaDialog {
	private static final int MINWIDTH = 60;
	private static final String ERROR_MESSAGE_INSTANCE = "Cannot define method. The name is empty or already in use.";
	private static final String ERROR_TITLE_INSTANCE = "Cannot define method.";
	private static final String TITLE = "Defining a method.";
	private static final String DESCRIPTION = "Define a name and parameters.";
	private static final String LBL_METHOD = "Name:";
	private static final String TXT_METHOD = "newMethod";

	private String name;
	private Map<TextDeclaration, Declaration> inputVariables = new HashMap<>();
	private final List<String> existingmethods;
	protected final List<String> existingVariables;
	protected final String[] dataclasses;
	private Text txtName;
	private Table inputTable;

	public NewMethodDialog(Shell parentShell, EObject container) {
		super(parentShell);
		existingmethods = getExistingMethods(container);
		existingVariables = CreationHelper.getExistingVariablesFromClass(container);
		dataclasses = PooslCache.get(container.eResource()).getDataClassMap().keySet().toArray(new String[0]);
	}

	protected void setMethod(String name, EList<Declaration> declarations) {
		this.name = name;
		inputVariables = getDeclarations(declarations);
		existingmethods.remove(name);
	}

	protected Map<TextDeclaration, Declaration> getDeclarations(EList<Declaration> declarations) {
		Map<TextDeclaration, Declaration> items = new HashMap<>();
		for (Declaration declaration : declarations) {
			List<String> textVars = new ArrayList<>();
			for (Variable variable : declaration.getVariables()) {
				textVars.add(variable.getName());
			}
			items.put(new TextDeclaration(textVars, declaration.getType()), declaration);
		}

		return items;
	}

	protected abstract ArrayList<String> getExistingMethods(EObject container);

	@Override
	public void create() {
		super.create();
		setTitle(TITLE);
		setMessage(DESCRIPTION, IMessageProvider.INFORMATION);
		txtName.setFocus();
		txtName.selectAll();
		getShell().pack();
		getShell().open();
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);

		area.setLayoutData(new GridData(GridData.FILL_BOTH));
		Composite composite = new Composite(area, SWT.NONE);
		GridLayout gridLayout = new GridLayout(4, false);
		composite.setLayout(gridLayout);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

		addNamingField(composite);
		addEmptyRow(composite);

		addInputField(composite);
		addOutputField(composite);

		reloadDeclarations();

		return area;
	}

	private void addNamingField(Composite container) {
		Label lbName = new Label(container, SWT.NONE);
		lbName.setText(LBL_METHOD);
		GridData labelData = new GridData();
		labelData.grabExcessHorizontalSpace = false;
		labelData.horizontalAlignment = GridData.END;
		lbName.setLayoutData(labelData);

		GridData dataFirstName = new GridData();
		dataFirstName.grabExcessHorizontalSpace = true;
		dataFirstName.horizontalAlignment = GridData.FILL;
		dataFirstName.verticalAlignment = GridData.END;

		dataFirstName.horizontalSpan = 2;

		txtName = new Text(container, SWT.BORDER);
		txtName.setLayoutData(dataFirstName);

		if (name == null) {
			// create new Name
			txtName.setText(TXT_METHOD);
			int index = 1;
			while (!methodNameAvailable(txtName.getText())) {
				txtName.setText(TXT_METHOD + index);
				index++;
			}
		} else {
			txtName.setText(name);
		}

		Label lblEmpty = new Label(container, SWT.NONE);
		lblEmpty.setText("");

		GridData emptyData = new GridData();
		emptyData.grabExcessHorizontalSpace = true;
		emptyData.horizontalAlignment = GridData.FILL;
		lblEmpty.setLayoutData(emptyData);

	}

	private void addEmptyRow(Composite area) {
		Label lblEmpty = new Label(area, SWT.NONE);
		lblEmpty.setText("");

		GridData emptyData = new GridData();
		emptyData.grabExcessHorizontalSpace = true;
		emptyData.horizontalAlignment = GridData.FILL;
		emptyData.horizontalSpan = 4;
		lblEmpty.setLayoutData(emptyData);
	}

	private void addInputField(final Composite composite) {
		Label lblInput = new Label(composite, SWT.NONE);
		lblInput.setLayoutData(getLabelGrid());
		lblInput.setText("Input Parameters:");

		inputTable = new Table(composite, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.NO_SCROLL | SWT.FULL_SELECTION);
		createTable(inputTable, composite, inputVariables);
	}

	protected abstract void addOutputField(Composite composite);

	protected void createTable(final Table table, final Composite composite,
			final Map<TextDeclaration, Declaration> variables) {
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 100;
		data.verticalSpan = 3;
		data.horizontalSpan = 2;
		table.setLayoutData(data);

		final TableColumn nameColumn = new TableColumn(table, SWT.NONE);
		nameColumn.setText("Name");
		nameColumn.setWidth(200);
		final TableColumn typeColumn = new TableColumn(table, SWT.NONE);
		typeColumn.setText("Type");

		composite.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				Rectangle area = composite.getClientArea();
				int width = area.width - table.computeTrim(0, 0, 0, 0).width;

				Point oldSize = table.getSize();
				if (oldSize.x > area.width) {
					// table is getting smaller so make the columns
					// smaller first and then resize the table to
					// match the client area width
					typeColumn.setWidth(width - nameColumn.getWidth());
					if (typeColumn.getWidth() < MINWIDTH && area.width > 2 * MINWIDTH) {
						nameColumn.setWidth(area.width - MINWIDTH);
					}
					table.setSize(area.width, area.height);
				} else {
					// table is getting bigger so make the table
					// bigger first and then make the columns wider
					// to match the client area width
					table.setSize(area.width, area.height);
					typeColumn.setWidth(width - nameColumn.getWidth());
				}
			}
		});

		GridData buttonData = new GridData();
		buttonData.grabExcessHorizontalSpace = false;
		buttonData.horizontalAlignment = GridData.END;
		buttonData.verticalAlignment = GridData.BEGINNING;
		buttonData.horizontalSpan = 1;
		buttonData.verticalSpan = 1;

		final Button btnAdd = new Button(composite, SWT.PUSH);
		btnAdd.setText("Add");
		btnAdd.setLayoutData(buttonData);

		final Button btnEdit = new Button(composite, SWT.PUSH);
		btnEdit.setText("Edit");
		btnEdit.setLayoutData(buttonData);

		final Button btnRemove = new Button(composite, SWT.PUSH);
		btnRemove.setText("Remove");
		btnRemove.setLayoutData(buttonData);

		btnEdit.setEnabled(false);
		btnRemove.setEnabled(false);

		table.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (table.getSelectionCount() != 0) {
					btnEdit.setEnabled(true);
					btnRemove.setEnabled(true);
				} else {
					btnEdit.setEnabled(false);
					btnRemove.setEnabled(false);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
		});

		btnAdd.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewVariableDialog variableDialog = new NewVariableDialog(Display.getDefault().getActiveShell(),
						dataclasses, "Variable", getVariableNames());
				if (variableDialog.open() == Window.OK) {
					variables.put(new TextDeclaration(variableDialog.getName(), variableDialog.getSelectedClass()),
							null);
					reloadDeclarations();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
		});

		btnEdit.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (table.getSelectionCount() != 0) {
					TextDeclaration declaration = getVariableFromIndex(table.getSelectionIndices()[0],
							variables.keySet());
					if (declaration != null) {
						ArrayList<String> varnames = getVariableNames();
						NewVariableDialog variableDialog = new NewVariableDialog(Display.getDefault().getActiveShell(),
								dataclasses, "Variable", varnames);

						variableDialog.setVariable(declaration);
						if (variableDialog.open() == Window.OK) {
							declaration.setVariables(variableDialog.getName());
							declaration.setType(variableDialog.getSelectedClass());
							reloadDeclarations();
						}
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
		});

		btnRemove.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int firstselected = table.getSelectionIndices()[0];
				for (int row : table.getSelectionIndices()) {
					removeVariableAtIndex(variables, row);
				}
				firstselected = (firstselected == 0) ? 0 : firstselected - 1;
				table.select(firstselected);

				reloadDeclarations();

				btnRemove.setEnabled(table.getSelectionCount() > 0);
				btnEdit.setEnabled(table.getSelectionCount() > 0);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
		});
	}

	protected void reloadDeclarations() {
		inputTable.removeAll();
		for (TextDeclaration declaration : inputVariables.keySet()) {
			for (String variable : declaration.getVariables()) {
				TableItem newVariableItem = new TableItem(inputTable, SWT.NONE);
				newVariableItem.setText(0, variable);
				newVariableItem.setText(1, declaration.getType());
			}
		}
	}

	private void removeVariableAtIndex(final Map<TextDeclaration, Declaration> variables, int row) {
		int looking = 0;
		for (TextDeclaration textDeclaration : variables.keySet()) {
			if (row < (looking + textDeclaration.getVariables().size())) {
				int varindex = row - looking;
				textDeclaration.getVariables().remove(varindex);
				break;
			}
			looking = +textDeclaration.getVariables().size();
		}
	}

	private TextDeclaration getVariableFromIndex(int rowindex, Set<TextDeclaration> declarations) {
		int looking = 0;
		for (TextDeclaration textDeclaration : declarations) {
			int size = textDeclaration.getVariables().size();
			if (rowindex < looking + size) {
				return textDeclaration;
			}
			looking += size;
		}
		return null;
	}

	protected ArrayList<String> getVariableNames() {
		ArrayList<String> names = new ArrayList<>();
		for (TextDeclaration declaration : inputVariables.keySet()) {
			names.addAll(declaration.getVariables());
		}
		return names;
	}

	protected GridData getLabelGrid() {
		GridData labelData = new GridData();
		labelData.grabExcessHorizontalSpace = false;
		labelData.horizontalAlignment = GridData.END;
		labelData.horizontalSpan = 1;
		labelData.verticalSpan = 3;
		return labelData;
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	private void saveInput() {
		name = txtName.getText();

		for (TextDeclaration textDeclaration : inputVariables.keySet()) {
			Declaration declaration = inputVariables.get(textDeclaration);
			if (declaration == null) {
				if (!textDeclaration.getVariables().isEmpty()) {
					declaration = CreationHelper.createDeclaration(textDeclaration.getVariables(),
							textDeclaration.getType());
					inputVariables.put(textDeclaration, declaration);
				}
			} else {
				List<Variable> used = CreationHelper.getUsedMethodVariables(declaration);
				if (!textDeclaration.getType().equals(declaration.getType())) {
					if (!used.isEmpty()) {
						MessageDialog.openError(Display.getDefault().getActiveShell(), "Variable has reference",
								"Can not change the type of the variable because it has a reference.");
						break;
					} else {
						declaration.setType(textDeclaration.getType());
					}
				}
				CreationHelper.editDeclarationVariables(declaration, textDeclaration.getVariables());
			}
		}
		saveReturnValues();
	}

	protected abstract void saveReturnValues();

	@Override
	protected void okPressed() {
		saveInput();
		if (getName().isEmpty() || !methodNameAvailable(getName())) {
			MessageDialog.openError(Display.getDefault().getActiveShell(), ERROR_TITLE_INSTANCE,
					ERROR_MESSAGE_INSTANCE);
			return;
		}
		super.okPressed();
	}

	private boolean methodNameAvailable(String name) {
		for (String methodname : existingmethods) {
			if (methodname.equalsIgnoreCase(name)) {
				return false;
			}
		}
		return true;
	}

	public String getName() {
		return name;
	}

	public List<Declaration> getInputVariables() {
		return new ArrayList<>(inputVariables.values());
	}

	public class TextDeclaration {
		private String type;
		private List<String> variables;

		public TextDeclaration(List<String> vars, String type) {
			variables = vars;
			this.type = type;
		}

		public TextDeclaration(String name, String type) {
			variables = new ArrayList<>();
			String[] split = name.split(",");
			for (int i = 0; i < split.length; i++) {
				variables.add(split[i].trim());
			}
			this.type = type;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public List<String> getVariables() {
			return variables;
		}

		public void setVariables(List<String> variables) {
			this.variables = variables;
		}

		public void addVariable(String name) {
			if (variables == null) {
				variables = new ArrayList<>();
			}
			String[] vars = name.split(",");
			for (int i = 0; i < vars.length; i++) {
				variables.add(vars[i].trim());
			}
		}

		public void setVariables(String name) {
			variables = new ArrayList<>();

			String[] vars = name.split(",");
			for (int i = 0; i < vars.length; i++) {
				variables.add(vars[i].trim());
			}
		}
	}
}