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

import org.eclipse.jface.window.Window;
import org.eclipse.poosl.sirius.navigator.PooslEditorPreferenceDialog.OpenStrategy;
import org.eclipse.swt.widgets.Display;

public /**
        * The OpenPreferenceManager.
        * 
        * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
        *
        */
abstract class OpenPreferenceManager {
    protected String diagramDescription;

    protected abstract String getDialogLabel();

    protected OpenStrategy showEditorPreferenceDialog() {
        return showEditorPreferenceDialog(false);
    }

    protected OpenStrategy showEditorPreferenceDialog(boolean showClassDiagram) {
        PooslEditorPreferenceDialog dialog = new PooslEditorPreferenceDialog(
                Display.getDefault().getActiveShell(), getDialogLabel(), diagramDescription,
                showClassDiagram);
        if (dialog.open() == Window.OK) {
            return dialog.getSelection();
        } else {
            return OpenStrategy.CANCEL;
        }
    }
}
