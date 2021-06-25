package nl.esi.poosl.rotalumisclient.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.apache.commons.lang.StringUtils;

//This custom formatter formats parts of a log record to a single line
public class PooslHtmlFormatter extends Formatter {
	// This method is called for every log record
	public String format(LogRecord rec) {
		StringBuilder buf = new StringBuilder();
		buf.append("<tr style=\"color:");
		if (rec.getLevel() == Level.SEVERE) {
			buf.append("red");
		} else if (rec.getLevel() == Level.WARNING) {
			buf.append("orange");
		} else if (rec.getLevel() == Level.FINEST) {
			buf.append("grey");
		} else if (rec.getLevel() == Level.INFO) {
			buf.append("green");
		}
		buf.append("\"><td>");
		buf.append(calcDate(rec.getMillis()));
		buf.append("</td><td>");
		buf.append(rec.getLevel());
		buf.append("</td><td>");
		buf.append(rec.getSourceClassName());
		buf.append('$');
		buf.append(rec.getSourceMethodName());
		buf.append("</td><td>");
		String message = rec.getMessage();
		message = message.replace("\n", System.getProperty("line.separator"));
		if (message.startsWith("<pre>") && message.endsWith("</pre>")) {
			buf.append("<pre>");
			message = message.substring("<pre>".length(), message.length() - "</pre>".length());
			buf.append(StringUtils.replaceEach(message, new String[] { "&", "\"", "<", ">" },
					new String[] { "&amp;", "&quot;", "&lt;", "&gt;" }));
			buf.append("</pre>");
		} else {
			buf.append("<pre>");
			buf.append(StringUtils.replaceEach(message, new String[] { "&", "\"", "<", ">" },
					new String[] { "&amp;", "&quot;", "&lt;", "&gt;" }));
			buf.append("</pre>");
		}
		buf.append("</td></tr>\n");
		return buf.toString();
	}

	private String calcDate(long millisecs) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS", Locale.getDefault());
		Date resultdate = new Date(millisecs);
		return dateFormat.format(resultdate);
	}

	// This method is called just after the handler using this
	// formatter is created
	@Override
	public String getHead(Handler h) {
		StringBuilder buf = new StringBuilder();
		buf.append("<HTML>\n");
		buf.append("<HEAD>\n");
		buf.append("<style>	table,th,td\n");
		buf.append("{ border:1px solid black; border-collapse:collapse; }\n");
		buf.append("</style>");
		buf.append("</HEAD>\n");
		buf.append("<BODY>\n");
		buf.append("<table width=\"100%\" border>\n");
		buf.append("<tr><th width=\"150px\">Time</th><th>Level</th><th>Source</th><th>Log Message</th></tr>\n");
		return buf.toString();
	}

	// This method is called just after the handler using this
	// formatter is closed
	@Override
	public String getTail(Handler h) {
		return "</table>\n  </BODY>\n</HTML>\n";
	}
}
