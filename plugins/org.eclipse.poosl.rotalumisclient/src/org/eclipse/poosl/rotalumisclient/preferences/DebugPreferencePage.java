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
package org.eclipse.poosl.rotalumisclient.preferences;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.poosl.rotalumisclient.Activator;
import org.eclipse.poosl.rotalumisclient.PooslConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

/**
 * The DebugPreferencePage.
 *
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 */
public class DebugPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
    /** The HELP_ID. */
    public static final String HELP_ID = "org.eclipse.poosl.help.help_preferences_simulator"; //$NON-NLS-1$

    private static final String LABEL_TEXT = "Logging level: ";

    private static final List<Level> LEVELS = Arrays.asList(Level.OFF, Level.SEVERE, Level.WARNING,
            Level.INFO, Level.CONFIG, Level.FINE, Level.FINER, Level.FINEST, Level.ALL);

    private static final Level DEFAULT_LOG_LEVEL = Level.parse(PooslConstants.DEFAULT_LOG_LEVEL);

    private Scale scale;

    private Label label;

    @Override
    public void init(IWorkbench workbench) {
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("Interaction between the POOSL IDE and the simulator.");
    }

    @Override
    protected Control createContents(Composite parent) {
        Composite main = new Composite(parent, SWT.NONE);
        main.setLayout(new GridLayout());
        main.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        IWorkbench workbench = PlatformUI.getWorkbench();
        if (workbench != null) {
            workbench.getHelpSystem().setHelp(getControl(), HELP_ID);
        }

        label = new Label(main, SWT.NONE);
        label.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
        scale = new Scale(main, SWT.NONE);
        scale.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
        scale.setMaximum(8);
        scale.setPageIncrement(1);
        scale.addSelectionListener(SelectionListener.widgetSelectedAdapter(
                evt -> label.setText(LABEL_TEXT + getLevel(scale.getSelection()))));

        String level = getPreferenceStore().getString(PooslConstants.PREFERENCES_LOG_LEVEL);
        if (level.isEmpty()) {
            level = PooslConstants.DEFAULT_LOG_LEVEL;
        }
        scale.setSelection(getLevel(Level.parse(level)));
        label.setText(LABEL_TEXT + getLevel(scale.getSelection()));
        Composite composite = new Composite(main, SWT.NONE);
        composite.setLayout(new GridLayout(2, false));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.WRAP, true, false));
        Label label2 = new Label(composite, SWT.NONE);
        label2.setText("Off");
        label2.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, false));
        Label label3 = new Label(composite, SWT.NONE);
        label3.setText("All");
        label3.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, false));
        return parent;
    }

    private static int getLevel(Level level) {
        int code = LEVELS.indexOf(level);
        return code != -1 ? code : 0 /*OFF*/;
    }

    private static Level getLevel(int intLevel) {
        return (0 <= intLevel && intLevel < LEVELS.size())
            ? LEVELS.get(intLevel) : DEFAULT_LOG_LEVEL;
    }

    @Override
    protected void performDefaults() {
        scale.setSelection(getLevel(Level.parse(PooslConstants.DEFAULT_LOG_LEVEL)));
        label.setText(LABEL_TEXT + PooslConstants.DEFAULT_LOG_LEVEL);
        super.performDefaults();
    }

    @Override
    protected void performApply() {
        getPreferenceStore().setValue(PooslConstants.PREFERENCES_LOG_LEVEL,
                getLevel(scale.getSelection()).toString());
        super.performApply();
    }

    @Override
    public boolean performOk() {
        getPreferenceStore().setValue(PooslConstants.PREFERENCES_LOG_LEVEL,
                getLevel(scale.getSelection()).toString());
        return super.performOk();
    }
}
