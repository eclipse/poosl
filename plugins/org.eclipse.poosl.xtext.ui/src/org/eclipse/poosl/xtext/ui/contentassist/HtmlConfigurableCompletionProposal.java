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
package org.eclipse.poosl.xtext.ui.contentassist;

import org.eclipse.jface.internal.text.html.BrowserInformationControl;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.AbstractReusableInformationControlCreator;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal;

/**
 * The HtmlConfigurableCompletionProposal.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
@SuppressWarnings("restriction")
public class HtmlConfigurableCompletionProposal extends ConfigurableCompletionProposal {

    public HtmlConfigurableCompletionProposal(String proposal, int replacementOffset,
            int replacementLength, int length, Image image, StyledString displayString,
            IContextInformation contextInformation, String additionalProposalInfo) {
        super(proposal, replacementOffset, replacementLength, length, image, displayString,
                contextInformation, additionalProposalInfo);
    }

    @Override
    public IInformationControlCreator getInformationControlCreator() {
        return new AbstractReusableInformationControlCreator() {
            @Override
            public IInformationControl doCreateInformationControl(Shell parent) {
                // this method implicitly initializes variable fgScrollBarSize
                BrowserInformationControl.isAvailable(parent);

                return new BrowserInformationControl(parent, JFaceResources.DIALOG_FONT, true);
            }
        };
    }
}
