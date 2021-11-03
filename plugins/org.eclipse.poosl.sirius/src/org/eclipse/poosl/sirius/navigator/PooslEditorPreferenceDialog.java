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
package org.eclipse.poosl.sirius.navigator;

import java.text.MessageFormat;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.poosl.sirius.Activator;
import org.eclipse.poosl.sirius.IPreferenceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * The PooslEditorPreferenceDialog.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslEditorPreferenceDialog extends MessageDialog {
    public static final String CLASS_DIAGRAM = "Class Diagram";

    public static final String DIAGRAM_NAME_SYSTEM = " of System";

    public static final String DIAGRAM_NAME_CLUSTER = " of Cluster ";

    public static final String COMPOSITE_STRUCTURE_DIAGRAM = "Composite Structure Diagram";

    private static final String TEXTUAL_EDITOR = "Textual Editor";

    private static final String GRAPHICAL_EDITOR = "Graphical Editor ({0})";

    private static final String TITLE = "Choose Editor";

    private Button btnTextual;

    private Button btnGraphical;

    private Button btnRememberMyDecision;

    private OpenStrategy selection;

    private final String diagramDescription;

    private Button btnClassDiagram;

    private final boolean isClassDiagramShown;

    /**
     * Create the dialog.
     * 
     * @param parentShell
     * @param dialogLabel
     * @param diagramDescription
     * @param isClassDiagramShown
     */
    public PooslEditorPreferenceDialog(Shell parentShell, String dialogLabel, String diagramDescription, boolean isClassDiagramShown) {
        super(parentShell, TITLE, null, dialogLabel, MessageDialog.QUESTION, new String[] { IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL }, 0);
        this.diagramDescription = diagramDescription;
        this.isClassDiagramShown = isClassDiagramShown;
    }

    private void createButtonListeners() {
        btnGraphical.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                btnGraphical.setSelection(true);
                btnTextual.setSelection(false);
                btnClassDiagram.setSelection(false);
            }
        });
        btnTextual.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                btnGraphical.setSelection(false);
                btnTextual.setSelection(true);
                btnClassDiagram.setSelection(false);
            }
        });
        btnClassDiagram.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                btnGraphical.setSelection(false);
                btnTextual.setSelection(false);
                btnClassDiagram.setSelection(true);
            }
        });
    }

    private void createButtons(Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        container.setLayout(new GridLayout(1, false));

        btnTextual = new Button(container, SWT.RADIO);
        btnTextual.setText(TEXTUAL_EDITOR);

        btnGraphical = new Button(container, SWT.RADIO | SWT.WRAP);
        btnGraphical.setText(MessageFormat.format(GRAPHICAL_EDITOR, diagramDescription));

        btnClassDiagram = new Button(container, SWT.RADIO | SWT.WRAP);
        if (isClassDiagramShown) {
            btnClassDiagram.setText(MessageFormat.format(GRAPHICAL_EDITOR, CLASS_DIAGRAM));
        } else {
            btnClassDiagram.setVisible(false);
        }

        btnRememberMyDecision = new Button(parent, SWT.CHECK);
        btnRememberMyDecision.setText("Remember my decision");
    }

    @Override
    protected Control createCustomArea(Composite parent) {
        createButtons(parent);
        createButtonListeners();
        loadPreferences();
        return super.createCustomArea(parent);
    }

    @Override
    protected void buttonPressed(int buttonId) {
        if (buttonId == OK) {
            saveInput();
        }
        super.buttonPressed(buttonId);
    }

    private void loadPreferences() {
        IPreferencesService preferencesService = Platform.getPreferencesService();
        String pref = preferencesService.getString(IPreferenceConstants.PREFERENCE_PLUGIN_ID, IPreferenceConstants.PROJECT_EXPLORER,
                IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_TEXTUAL, null);
        btnTextual.setSelection(pref.equals(IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_TEXTUAL));
        btnGraphical.setSelection(pref.equals(IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_GRAPHICAL));
        btnClassDiagram.setSelection(pref.equals(IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_CLASS_DIAGRAM));

    }

    private void saveInput() {
        if (btnTextual.getSelection()) {
            selection = OpenStrategy.TEXTUAL;
        } else if (btnGraphical.getSelection()) {
            selection = OpenStrategy.GRAPHICAL;
        } else {
            selection = OpenStrategy.CLASSDIAGRAM;
        }

        IPreferenceStore pStore = Activator.getDefault().getPreferenceStore();
        if (pStore != null) {
            if (btnTextual.getSelection()) {
                pStore.setValue(IPreferenceConstants.PREFERENCE_GRAPHICAL_EDITOR, IPreferenceConstants.PREFERENCE_GRAPHICAL_EDITOR_TEXTUAL);
                pStore.setValue(IPreferenceConstants.PROJECT_EXPLORER, IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_TEXTUAL);
                pStore.setValue(IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_NO_SYSTEM, IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_TEXTUAL);
            } else {
                pStore.setValue(IPreferenceConstants.PREFERENCE_GRAPHICAL_EDITOR, IPreferenceConstants.PREFERENCE_GRAPHICAL_EDITOR_GRAPHICAL);
                pStore.setValue(IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_NO_SYSTEM, IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_CLASS_DIAGRAM);
                if (btnGraphical.getSelection()) {
                    pStore.setValue(IPreferenceConstants.PROJECT_EXPLORER, IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_GRAPHICAL);
                } else {
                    pStore.setValue(IPreferenceConstants.PROJECT_EXPLORER, IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_CLASS_DIAGRAM);
                }
            }
            pStore.setValue(IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_DONT_ASK, btnRememberMyDecision.getSelection());
        }
    }

    public OpenStrategy getSelection() {
        return selection;
    }

    public enum OpenStrategy {
        TEXTUAL, GRAPHICAL, CLASSDIAGRAM, CANCEL
    }
}
