package nl.esi.poosl.rotalumisclient.sourcemapping;

public class PooslSourceMapping {
	private static final String EMPTY_SOURCETEXT = "...";

	private final String filePath;
	private final int lineNumber;
	private final int offset;
	private final int length;
	private final int totalOffset;
	private final int totalEndOffset;
	private final String sourceText;
	private String singleLine;

	public PooslSourceMapping(String filePath, int lineNumber, int offset, int length, int totalOffset,
			int totalEndOffset, String sourceText) {
		this.filePath = filePath;
		this.lineNumber = lineNumber;
		this.offset = offset;
		this.length = length;
		this.totalOffset = totalOffset;
		this.totalEndOffset = totalEndOffset;
		this.sourceText = sourceText;
	}

	public String getFilePath() {
		return filePath;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public int getOffset() {
		return offset;
	}

	public int getLength() {
		return length;
	}

	public int getTotalOffset() {
		return totalOffset;
	}

	public int getTotalEndOffset() {
		return totalEndOffset;
	}

	public String getSourceText() {
		return sourceText;
	}

	/**
	 * Get the source text as a single line without comments
	 * 
	 * @return
	 */
	public String getSingleLineSourceText() {
		if (singleLine == null) {
			if (sourceText == null) {
				return EMPTY_SOURCETEXT;
			}
			singleLine = sourceText;
			// replace comments, source http://ostermiller.org/findcomment.html
			singleLine = singleLine.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)", " ");
			// replace whitespaces
			singleLine = singleLine.replaceAll("\\n", " ").replaceAll("\\t", " ").replaceAll("\\r", "");
			// remove unnecessary whitespaces
			singleLine = singleLine.trim().replaceAll(" +", " ");
		}
		return singleLine;
	}
}
