package nl.esi.poosl.sirius.dialogs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.google.common.collect.Iterables;

import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.InstantiableClass;
import nl.esi.poosl.Poosl;
import nl.esi.poosl.sirius.helpers.NameHelper;

public class NewInstanceDialog extends TitleAreaDialog {
	private static final String ERROR_MESSAGE_INSTANCE = "Cannot create instance. The name is empty or already in use.";
	private static final String ERROR_TITLE_INSTANCE = "Cannot create instance.";
	private static final String ERROR_MESSAGE_CONTAINER = "Cannot create instance. The class of the instance cannot be the same as the cluster class that contains the instance.";
	private static final String ERROR_MESSAGE_NO_CLASS = "Cannot create instance. A class needs to be selected to create an instance.";
	private static final String ERROR_TITLE_CONTAINER = "Cannot create instance.";
	private static final String ERROR_MESSAGE_CLASS = "Cannot create the new class. The name is empty or already in use.";
	private static final String ERROR_TITLE_CLASS = "Cannot create class.";
	private static final String TITLE = "Creating a new instance.";
	private static final String DESCRIPTION = "Define a name and class.";
	private static final String LBL_INSTANCE = "Name:";
	private static final String TXT_CLASS = "newClass";
	private static final String LBL_CLASS = "Name:";

	public enum Type {
		CLUSTERCLASS, PROCESSCLASS
	}

	private String name;
	private String selectedClass;
	private Type selectedType;
	private boolean hasNewClass;
	private String newClassName;
	private final String containerName;

	private Text txtName;
	private final String[] processclasses;
	private final String[] clusterclasses;
	private final List<String> instances;
	private Button btnProcess;
	private Button btnCluster;
	private Combo btnSelectClass;
	private Button btnNewClass;
	private Text txtClassName;
	private Label lblSelectClass;
	private Label lblNewClass;
	private InstantiableClass loadedClass;

	public NewInstanceDialog(Shell parentShell, Poosl poosl, ClusterClass container) {
		super(parentShell);
		processclasses = reorderNames(NameHelper.getAllProcessNames(poosl));
		clusterclasses = reorderNames(NameHelper.getAllClusterNames(poosl));
		instances = NameHelper.getInstanceNames(container);
		containerName = (container.getName() != null) ? container.getName() : "";
	}

	public void setInstantiableClass(InstantiableClass inst) {
		loadedClass = inst;
	}

	private int getIndex(String[] classes, String name) {
		for (int i = 0; i < classes.length; i++) {
			if (classes[i].equals(name)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void create() {
		super.create();
		setTitle(TITLE);
		setMessage(DESCRIPTION, IMessageProvider.INFORMATION);
		txtName.setFocus();
		txtName.selectAll();
		loadClass();
	}

	private void loadClass() {
		if (loadedClass != null) {
			if (loadedClass instanceof ClusterClass) {
				btnCluster.setSelection(true);
				btnProcess.setSelection(false);
				btnSelectClass.setItems(clusterclasses);
				btnSelectClass.select(getIndex(clusterclasses, loadedClass.getName()));
			} else {
				btnCluster.setSelection(false);
				btnProcess.setSelection(true);
				btnSelectClass.setItems(processclasses);
				btnSelectClass.select(getIndex(processclasses, loadedClass.getName()));
			}
			btnSelectClass.setEnabled(false);
			btnProcess.setEnabled(false);
			btnCluster.setEnabled(false);
			btnNewClass.setVisible(false);
			txtClassName.setVisible(false);
			lblNewClass.setVisible(false);
		}
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		area.setLayoutData(new GridData(GridData.FILL_BOTH));
		area.setLayout(new GridLayout());

		addNamingField(area);
		addClassTypeField(area);
		addClassSelectField(area);
		addNewClassOptionField(area);
		addNewClassNameField(area);
		return area;
	}

	private void addNamingField(Composite container) {
		Composite composite = new Composite(container, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2, false);
		composite.setLayout(gridLayout);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

		Label lbName = new Label(composite, SWT.NONE);
		lbName.setText(LBL_INSTANCE);

		GridData dataFirstName = new GridData();
		dataFirstName.grabExcessHorizontalSpace = true;
		dataFirstName.horizontalAlignment = GridData.FILL;
		dataFirstName.verticalAlignment = GridData.BEGINNING;
		dataFirstName.horizontalIndent = 10;

		txtName = new Text(composite, SWT.BORDER);
		txtName.setLayoutData(dataFirstName);
		txtName.setText(NameHelper.getUniqueInstanceName(instances));
	}

	private void addClassTypeField(Composite container) {
		Composite composite = new Composite(container, SWT.NONE);
		GridData gridData = new GridData();
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = GridData.CENTER;
		gridData.verticalAlignment = GridData.BEGINNING;
		composite.setLayoutData(gridData);
		RowLayout rowLayout = new RowLayout(SWT.HORIZONTAL);
		rowLayout.wrap = true;
		rowLayout.pack = true;
		rowLayout.justify = false;
		rowLayout.marginLeft = 20;
		rowLayout.marginTop = 5;
		rowLayout.marginBottom = 5;
		rowLayout.spacing = 20;
		composite.setLayout(rowLayout);

		btnProcess = new Button(composite, SWT.RADIO);
		btnProcess.setText("Process Class");
		btnProcess.setSelection(true);
		selectedType = Type.PROCESSCLASS;
		btnProcess.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectedType = Type.PROCESSCLASS;
				btnSelectClass.setItems(processclasses);
				btnSelectClass.select(0);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
		});

		btnCluster = new Button(composite, SWT.RADIO);
		btnCluster.setText("Cluster Class");
		btnCluster.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectedType = Type.CLUSTERCLASS;
				btnSelectClass.setItems(clusterclasses);
				btnSelectClass.select(0);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
		});
	}

	private void addClassSelectField(Composite container) {
		Composite composite = new Composite(container, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2, false);
		composite.setLayout(gridLayout);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

		lblSelectClass = new Label(composite, SWT.NONE);
		lblSelectClass.setText("Class:");

		GridData dataFirstName = new GridData();
		dataFirstName.grabExcessHorizontalSpace = true;
		dataFirstName.horizontalAlignment = GridData.FILL;
		dataFirstName.verticalAlignment = GridData.BEGINNING;
		dataFirstName.horizontalIndent = 15;

		btnSelectClass = new Combo(composite, SWT.READ_ONLY);
		btnSelectClass.setLayoutData(dataFirstName);
		btnSelectClass.setItems(processclasses);
		btnSelectClass.select(0);
	}

	private void addNewClassNameField(Composite container) {
		Composite composite = new Composite(container, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2, false);
		composite.setLayout(gridLayout);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

		lblNewClass = new Label(composite, SWT.NONE);
		lblNewClass.setText(LBL_CLASS);

		GridData dataFirstName = new GridData();
		dataFirstName.grabExcessHorizontalSpace = true;
		dataFirstName.horizontalAlignment = GridData.FILL;
		dataFirstName.verticalAlignment = GridData.BEGINNING;
		dataFirstName.horizontalIndent = 10;

		txtClassName = new Text(composite, SWT.BORDER);
		txtClassName.setLayoutData(dataFirstName);
		txtClassName.setText(TXT_CLASS);

		lblNewClass.setEnabled(false);
		txtClassName.setEnabled(false);

		if (!NameHelper.isNameAvailable(txtClassName.getText(), getCurrentNames())) {
			txtClassName.setText(NameHelper.getUniqueName(TXT_CLASS, getCurrentNames()));
		}
	}

	private void addNewClassOptionField(Composite container) {
		Composite composite = new Composite(container, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2, false);
		composite.setLayout(gridLayout);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		btnNewClass = new Button(composite, SWT.CHECK);
		btnNewClass.setText("&Create instance of a new class");

		btnNewClass.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnSelectClass.setEnabled(!btnNewClass.getSelection());
				lblSelectClass.setEnabled(!btnNewClass.getSelection());
				lblNewClass.setEnabled(btnNewClass.getSelection());
				txtClassName.setEnabled(btnNewClass.getSelection());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
		});
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	// save content of the Text fields because they get disposed
	// as soon as the Dialog closes
	private void saveInput() {
		hasNewClass = btnNewClass.getSelection();
		newClassName = txtClassName.getText();
		name = txtName.getText();
		if (btnSelectClass.getSelectionIndex() != -1) {
			this.selectedClass = btnSelectClass.getItem(btnSelectClass.getSelectionIndex());
		} else {
			this.selectedClass = null;
		}
	}

	@Override
	protected void okPressed() {
		saveInput();
		boolean ok = true;
		if (getHasNewClass()) {
			if (getNewClassName().isEmpty() || !NameHelper.isNameAvailable(getNewClassName(), getCurrentNames())) {
				MessageDialog.openError(Display.getDefault().getActiveShell(), ERROR_TITLE_CLASS, ERROR_MESSAGE_CLASS);
				ok = false;

			}
		} else {
			if (btnSelectClass.getSelectionIndex() == -1) {
				MessageDialog.openError(Display.getDefault().getActiveShell(), ERROR_TITLE_CONTAINER,
						ERROR_MESSAGE_NO_CLASS);
				ok = false;
			} else if (selectedType == Type.CLUSTERCLASS
					&& btnSelectClass.getItems()[btnSelectClass.getSelectionIndex()].equalsIgnoreCase(containerName)) {
				MessageDialog.openError(Display.getDefault().getActiveShell(), ERROR_TITLE_CONTAINER,
						ERROR_MESSAGE_CONTAINER);
				ok = false;
			}
		}

		if (getName().isEmpty() || !NameHelper.isNameAvailable(getName(), instances)) {
			MessageDialog.openError(Display.getDefault().getActiveShell(), ERROR_TITLE_INSTANCE,
					ERROR_MESSAGE_INSTANCE);
			ok = false;
		}

		if (ok) {
			super.okPressed();
		}
	}

	private List<String> getCurrentNames() {
		List<String> currentNames = new ArrayList<>();
		currentNames.addAll(Arrays.asList(clusterclasses));
		currentNames.addAll(Arrays.asList(processclasses));
		return currentNames;
	}

	public String getName() {
		return name;
	}

	public String getSelectedClass() {
		return selectedClass;
	}

	public Type getSelectedType() {
		return selectedType;
	}

	public boolean getHasNewClass() {
		return hasNewClass;
	}

	public String getNewClassName() {
		return newClassName;
	}

	private String[] reorderNames(Iterable<String> names) {
		String[] arrayNames = Iterables.toArray(names, String.class);
		Arrays.sort(arrayNames);
		return Arrays.copyOf(arrayNames, arrayNames.length, String[].class);
	}
}