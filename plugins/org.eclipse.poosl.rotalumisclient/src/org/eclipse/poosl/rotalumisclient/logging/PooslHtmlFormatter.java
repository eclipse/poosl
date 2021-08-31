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
package org.eclipse.poosl.rotalumisclient.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.apache.commons.lang.StringUtils;

/**
 * This custom formatter formats parts of a log record to a single line.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslHtmlFormatter extends Formatter {
    // This method is called for every log record
    @Override
    public String format(LogRecord rec) {
        // CHECKSTYLE:OFF text generation
        StringBuilder buf = new StringBuilder();
        buf.append("<tr style=\"color:"); //$NON-NLS-1$
        if (rec.getLevel() == Level.SEVERE) {
            buf.append("red"); //$NON-NLS-1$
        } else if (rec.getLevel() == Level.WARNING) {
            buf.append("orange"); //$NON-NLS-1$
        } else if (rec.getLevel() == Level.FINEST) {
            buf.append("grey"); //$NON-NLS-1$
        } else if (rec.getLevel() == Level.INFO) {
            buf.append("green"); //$NON-NLS-1$
        }
        buf.append("\"><td>"); //$NON-NLS-1$
        buf.append(calcDate(rec.getMillis()));
        buf.append("</td><td>"); //$NON-NLS-1$
        buf.append(rec.getLevel());
        buf.append("</td><td>"); //$NON-NLS-1$
        buf.append(rec.getSourceClassName());
        buf.append('$');
        buf.append(rec.getSourceMethodName());
        buf.append("</td><td>"); //$NON-NLS-1$
        String message = rec.getMessage();
        message = message.replace("\n", System.getProperty("line.separator")); //$NON-NLS-1$ //$NON-NLS-2$
        if (message.startsWith("<pre>") && message.endsWith("</pre>")) { //$NON-NLS-1$ //$NON-NLS-2$
            buf.append("<pre>"); //$NON-NLS-1$
            message = message.substring("<pre>".length(), message.length() - "</pre>".length()); //$NON-NLS-1$ //$NON-NLS-2$
            buf.append(StringUtils.replaceEach(message, new String[] { "&", "\"", "<", ">" }, new String[] { "&amp;", "&quot;", "&lt;", "&gt;" })); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
            buf.append("</pre>"); //$NON-NLS-1$
        } else {
            buf.append("<pre>"); //$NON-NLS-1$
            buf.append(StringUtils.replaceEach(message, new String[] { "&", "\"", "<", ">" }, new String[] { "&amp;", "&quot;", "&lt;", "&gt;" })); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
            buf.append("</pre>"); //$NON-NLS-1$
        }
        buf.append("</td></tr>\n"); //$NON-NLS-1$
        // CHECKSTYLE:ON
        return buf.toString();
    }

    private String calcDate(long millisecs) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS", Locale.getDefault()); //$NON-NLS-1$
        Date resultdate = new Date(millisecs);
        return dateFormat.format(resultdate);
    }

    // This method is called just after the handler using this
    // formatter is created
    @Override
    public String getHead(Handler h) {
        StringBuilder buf = new StringBuilder();
        buf.append("<HTML>\n"); //$NON-NLS-1$
        buf.append("<HEAD>\n"); //$NON-NLS-1$
        buf.append("<style>	table,th,td\n"); //$NON-NLS-1$
        buf.append("{ border:1px solid black; border-collapse:collapse; }\n"); //$NON-NLS-1$
        buf.append("</style>"); //$NON-NLS-1$
        buf.append("</HEAD>\n"); //$NON-NLS-1$
        buf.append("<BODY>\n"); //$NON-NLS-1$
        buf.append("<table width=\"100%\" border>\n"); //$NON-NLS-1$
        buf.append("<tr><th width=\"150px\">Time</th><th>Level</th><th>Source</th><th>Log Message</th></tr>\n"); //$NON-NLS-1$
        return buf.toString();
    }

    // This method is called just after the handler using this
    // formatter is closed
    @Override
    public String getTail(Handler h) {
        return "</table>\n  </BODY>\n</HTML>\n"; //$NON-NLS-1$
    }
}
