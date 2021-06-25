package nl.esi.poosl.xtext.formatting2;

import java.util.List;

import org.eclipse.xtext.formatting2.IHiddenRegionFormatting;
import org.eclipse.xtext.formatting2.ITextReplacer;
import org.eclipse.xtext.formatting2.internal.HiddenRegionReplacer;
import org.eclipse.xtext.formatting2.internal.WhitespaceReplacer;
import org.eclipse.xtext.formatting2.regionaccess.IHiddenRegion;

@SuppressWarnings("restriction")
public class PooslHiddenRegionReplacer extends HiddenRegionReplacer {

	public PooslHiddenRegionReplacer(IHiddenRegion region, IHiddenRegionFormatting formatting) {
		super(region, formatting);
	}

	@Override
	protected WhitespaceReplacer findWhitespaceThatSeparatesSemanticRegions(List<ITextReplacer> replacers) {
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
