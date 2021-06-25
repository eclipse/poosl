package nl.esi.poosl.sirius.dialogs;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.DataMethodBinaryOperator;
import nl.esi.poosl.DataMethodNamed;
import nl.esi.poosl.DataMethodUnaryOperator;
import nl.esi.poosl.OperatorBinary;
import nl.esi.poosl.OperatorUnary;
import nl.esi.poosl.xtext.helpers.HelperFunctions;

public class NewMethodDataDialog extends NewMethodDialog {

	// Data
	private String returnType;

	// UI Data Components
	private Combo cbReturnType;

	public NewMethodDataDialog(Shell parentShell, DataClass dataclass) {
		super(parentShell, dataclass);
	}

	public void setDataMethod(DataMethod method) {
		String name = null;
		if (method instanceof DataMethodNamed) {
			name = ((DataMethodNamed) method).getName();
		} else if (method instanceof DataMethodBinaryOperator) {
			OperatorBinary op = ((DataMethodBinaryOperator) method).getName();
			if (op != null) {
				name = op.getName();
			}
		} else if (method instanceof DataMethodUnaryOperator) {
			OperatorUnary op = ((DataMethodUnaryOperator) method).getName();
			if (op != null) {
				name = op.getName();
			}
		}
		setMethod(name, method.getParameters());
		returnType = method.getReturnType();
	}

	@Override
	protected void addOutputField(Composite composite) {
		Label lblInput = new Label(composite, SWT.NONE);
		lblInput.setLayoutData(getLabelGrid());
		lblInput.setText("Return Type:");

		cbReturnType = new Combo(composite, SWT.NONE);
		GridData comboData = new GridData();
		comboData.grabExcessHorizontalSpace = true;

		comboData.verticalSpan = 3;
		comboData.horizontalSpan = 1;

		cbReturnType.setLayoutData(comboData);
		cbReturnType.setItems(dataclasses);

		if (returnType != null) {
			cbReturnType.setText(returnType);
		} else {
			cbReturnType.setText(HelperFunctions.CLASS_NAME_OBJECT);
		}
	}

	@Override
	protected ArrayList<String> getVariableNames() {
		ArrayList<String> names = super.getVariableNames();
		names.addAll(existingVariables);
		return names;
	}

	public String getSelectedClass() {
		return returnType;
	}

	@Override
	protected ArrayList<String> getExistingMethods(EObject container) {
		DataClass dataClass = (DataClass) container;
		ArrayList<String> methods = new ArrayList<>();

		for (Iterator<DataMethodNamed> iterator = dataClass.getDataMethodsNamed().iterator(); iterator.hasNext();) {
			DataMethodNamed dataMethodNamed = iterator.next();
			methods.add(dataMethodNamed.getName());
		}

		for (Iterator<DataMethodUnaryOperator> iterator2 = dataClass.getDataMethodsUnaryOperator().iterator(); iterator2
				.hasNext();) {
			DataMethodUnaryOperator dataMethodUnaryOperator = iterator2.next();
			methods.add(dataMethodUnaryOperator.getName().getName());
		}

		for (Iterator<DataMethodBinaryOperator> iterator3 = dataClass.getDataMethodsBinaryOperator()
				.iterator(); iterator3.hasNext();) {
			DataMethodBinaryOperator dataMethodBinaryOperator = iterator3.next();
			methods.add(dataMethodBinaryOperator.getName().getName());
		}
		return methods;
	}

	@Override
	protected void saveReturnValues() {
		returnType = cbReturnType.getText();
	}
}