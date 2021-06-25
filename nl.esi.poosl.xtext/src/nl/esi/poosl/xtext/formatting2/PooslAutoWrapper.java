package nl.esi.poosl.xtext.formatting2;

import org.eclipse.xtext.formatting2.IAutowrapFormatter;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.formatting2.IHiddenRegionFormatter;
import org.eclipse.xtext.formatting2.IHiddenRegionFormatting;
import org.eclipse.xtext.formatting2.regionaccess.IHiddenRegion;
import org.eclipse.xtext.formatting2.regionaccess.IHiddenRegionPart;
import org.eclipse.xtext.formatting2.regionaccess.ITextSegment;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

public class PooslAutoWrapper implements IAutowrapFormatter {
	private final IHiddenRegion last;
	private final Procedure1<IHiddenRegionFormatter> formatting;
	private boolean hasWrapped = false;

	public PooslAutoWrapper(IHiddenRegion last, Procedure1<IHiddenRegionFormatter> formatting) {
		this.last = last;
		this.formatting = formatting;
	}

	@Override
	public void format(ITextSegment region, IHiddenRegionFormatting wrapped, IFormattableDocument document) {
		if (!hasWrapped) {
			IHiddenRegion hiddenRegion = null;
			if (region instanceof IHiddenRegion) {
				hiddenRegion = (IHiddenRegion) region;
			} else if (region instanceof IHiddenRegionPart) {
				hiddenRegion = ((IHiddenRegionPart) region).getHiddenRegion();
			}

			if (hiddenRegion != null) {
				document.set(hiddenRegion, last, formatting);
			}
			hasWrapped = true;
		}
	}
}
