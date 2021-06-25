package nl.esi.poosl.sirius.dialogs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import nl.esi.poosl.Declaration;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.sirius.helpers.CreationHelper;

public class NewMethodProcessDialog extends NewMethodDialog {
	private Map<TextDeclaration, Declaration> outputVariables = new HashMap<>();
	private Table mOutputTable;

	public NewMethodProcessDialog(Shell parentShell, ProcessClass processClass) {
		super(parentShell, processClass);
	}

	public void setProcessMethod(ProcessMethod method) {
		setMethod(method.getName(), method.getInputParameters());
		outputVariables = getDeclarations(method.getOutputParameters());
	}

	public List<Declaration> getOutputVariables() {
		return new ArrayList<>(outputVariables.values());
	}

	@Override
	protected void addOutputField(Composite composite) {
		Label lblInput = new Label(composite, SWT.NONE);
		lblInput.setLayoutData(getLabelGrid());
		lblInput.setText("Output Variables:");

		mOutputTable = new Table(composite, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.NO_SCROLL | SWT.FULL_SELECTION);
		createTable(mOutputTable, composite, outputVariables);
	}

	@Override
	protected ArrayList<String> getExistingMethods(EObject container) {
		ProcessClass processClass = (ProcessClass) container;
		ArrayList<String> methods = new ArrayList<>();
		for (Iterator<ProcessMethod> iterator = processClass.getMethods().iterator(); iterator.hasNext();) {
			ProcessMethod processMethod = iterator.next();
			methods.add(processMethod.getName());
		}
		return methods;
	}

	@Override
	protected ArrayList<String> getVariableNames() {
		ArrayList<String> names = super.getVariableNames();
		for (TextDeclaration declaration : outputVariables.keySet()) {
			names.addAll(declaration.getVariables());
		}
		names.addAll(existingVariables);
		return names;
	}

	@Override
	protected void reloadDeclarations() {
		mOutputTable.removeAll();
		for (TextDeclaration declaration : outputVariables.keySet()) {
			for (String variable : declaration.getVariables()) {
				TableItem newVariableItem = new TableItem(mOutputTable, SWT.NONE);
				newVariableItem.setText(0, variable);
				newVariableItem.setText(1, declaration.getType());
			}
		}
		super.reloadDeclarations();
	}

	@Override
	protected void saveReturnValues() {
		for (TextDeclaration textDeclaration : outputVariables.keySet()) {
			Declaration declaration = outputVariables.get(textDeclaration);
			if (declaration == null && !textDeclaration.getVariables().isEmpty()) {
				declaration = CreationHelper.createDeclaration(textDeclaration.getVariables(),
						textDeclaration.getType());
				outputVariables.put(textDeclaration, declaration);
			} else {
				CreationHelper.editDeclarationVariables(declaration, textDeclaration.getVariables());
			}
		}
	}
}