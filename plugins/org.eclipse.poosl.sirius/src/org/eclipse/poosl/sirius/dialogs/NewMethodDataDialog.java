/*******************************************************************************
 * Copyright (c) 2021 TNO/ESI
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    TNO/ESI - initial API and implementation
 *    Obeo - refactoring
 *******************************************************************************/
package org.eclipse.poosl.sirius.dialogs;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.poosl.DataClass;
import org.eclipse.poosl.DataMethod;
import org.eclipse.poosl.DataMethodBinaryOperator;
import org.eclipse.poosl.DataMethodNamed;
import org.eclipse.poosl.DataMethodUnaryOperator;
import org.eclipse.poosl.OperatorBinary;
import org.eclipse.poosl.OperatorUnary;
import org.eclipse.poosl.xtext.helpers.HelperFunctions;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * The NewMethodDataDialog.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
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

        for (DataMethodNamed dataMethodNamed : dataClass.getDataMethodsNamed()) {
            methods.add(dataMethodNamed.getName());
        }

        for (DataMethodUnaryOperator dataMethodUnaryOperator : dataClass
                .getDataMethodsUnaryOperator()) {
            methods.add(dataMethodUnaryOperator.getName().getName());
        }

        for (DataMethodBinaryOperator dataMethodBinaryOperator : dataClass
                .getDataMethodsBinaryOperator()) {
            methods.add(dataMethodBinaryOperator.getName().getName());
        }
        return methods;
    }

    @Override
    protected void saveReturnValues() {
        returnType = cbReturnType.getText();
    }
}
