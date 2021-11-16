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
package org.eclipse.poosl.xtext.formatting2;

import java.util.List;

import org.eclipse.xtext.formatting2.IHiddenRegionFormatting;
import org.eclipse.xtext.formatting2.ITextReplacer;
import org.eclipse.xtext.formatting2.internal.HiddenRegionReplacer;
import org.eclipse.xtext.formatting2.internal.WhitespaceReplacer;
import org.eclipse.xtext.formatting2.regionaccess.IHiddenRegion;

/**
 * The PooslHiddenRegionReplacer.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
@SuppressWarnings("restriction")
public class PooslHiddenRegionReplacer extends HiddenRegionReplacer {

    public PooslHiddenRegionReplacer(IHiddenRegion region, IHiddenRegionFormatting formatting) {
        super(region, formatting);
    }

    @Override
    protected WhitespaceReplacer findWhitespaceThatSeparatesSemanticRegions(
            List<ITextReplacer> replacers) {
        WhitespaceReplacer whitespaceReplacer = null;
        for (ITextReplacer replacer : replacers) {
            if (replacer instanceof PooslMultilineCommentReplacer) {
                if (whitespaceReplacer != null) {
                    return whitespaceReplacer;
                }
            } else if (replacer instanceof WhitespaceReplacer) {
                whitespaceReplacer = (WhitespaceReplacer) replacer;
                if (whitespaceReplacer.getRegion().isMultiline()) {
                    return whitespaceReplacer;
                }
            }
        }
        return (WhitespaceReplacer) replacers.get(replacers.size() - 1);
    }
}
