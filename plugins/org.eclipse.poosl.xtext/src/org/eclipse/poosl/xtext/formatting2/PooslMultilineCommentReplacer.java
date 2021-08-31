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

import org.eclipse.xtext.formatting2.ITextReplacerContext;
import org.eclipse.xtext.formatting2.internal.MultilineCommentReplacer;
import org.eclipse.xtext.formatting2.internal.WhitespaceReplacer;
import org.eclipse.xtext.formatting2.regionaccess.IComment;
import org.eclipse.xtext.formatting2.regionaccess.ILineRegion;
import org.eclipse.xtext.formatting2.regionaccess.ITextRegionAccess;
import org.eclipse.xtext.formatting2.regionaccess.ITextSegment;

/**
 * The PooslMultilineCommentReplacer.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
@SuppressWarnings("restriction")
public class PooslMultilineCommentReplacer extends MultilineCommentReplacer {
    private final char prefix;

    public PooslMultilineCommentReplacer(IComment comment, char prefix) {
        super(comment, prefix);
        this.prefix = prefix;
    }

    @Override
    public void configureWhitespace(WhitespaceReplacer leading, WhitespaceReplacer trailing) {
        enforceNewLine(leading);
        enforceNewLine(trailing);
    }

    @Override
    public ITextReplacerContext createReplacements(ITextReplacerContext context) {
        IComment comment = getComment();
        ITextRegionAccess access = comment.getTextRegionAccess();
        List<ILineRegion> lines = comment.getLineRegions();

        String oldIndentation = lines.get(0).getIndentation().getText();
        String indentationString = context.getIndentationString();
        String newIndentation = indentationString + ' ' + prefix + ' ';

        for (int i = 1; i < lines.size() - 1; i++) {
            ITextSegment line = lines.get(i);
            String text = line.getText();
            int prefixOffset = prefixOffset(text);

            ITextSegment target;
            if (prefixOffset >= 0) {
                target = access.regionForOffset(line.getOffset(), prefixOffset + 1);
            } else if (text.startsWith(oldIndentation)) {
                target = access.regionForOffset(line.getOffset(), oldIndentation.length());
            } else {
                target = access.regionForOffset(line.getOffset(), 0);
            }
            context.addReplacement(target.replaceWith(newIndentation));
        }
        if (lines.size() > 1) {
            ILineRegion line = lines.get(lines.size() - 1);
            context.addReplacement(line.getIndentation().replaceWith(indentationString + ' '));
        }
        return context;
    }
}
