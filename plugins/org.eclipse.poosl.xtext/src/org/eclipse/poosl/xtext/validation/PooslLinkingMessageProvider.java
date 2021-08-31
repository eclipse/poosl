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
package org.eclipse.poosl.xtext.validation;

import org.eclipse.xtext.diagnostics.DiagnosticMessage;
import org.eclipse.xtext.linking.impl.LinkingDiagnosticMessageProvider;

/**
 * The PooslLinkingMessageProvider.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslLinkingMessageProvider extends LinkingDiagnosticMessageProvider {

    @Override
    public DiagnosticMessage getUnresolvedProxyMessage(ILinkingDiagnosticContext context) {
        DiagnosticMessage diagnosticMessage = super.getUnresolvedProxyMessage(context);
        String diagnosticMessageString = diagnosticMessage.getMessage();
        diagnosticMessageString = diagnosticMessageString.substring("Couldn't resolve reference to ".length());
        return new DiagnosticMessage(diagnosticMessageString.substring(0, diagnosticMessageString.length() - 1) + " is not declared.", diagnosticMessage.getSeverity(),
                diagnosticMessage.getIssueCode(), diagnosticMessage.getIssueData());
    }
}
