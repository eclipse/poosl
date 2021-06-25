package nl.esi.poosl.rotalumisclient.views.stacktraceview;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.LabelProvider;

import nl.esi.poosl.generatedxmlclasses.TErrorStackframe;
import nl.esi.poosl.rotalumisclient.sourcemapping.PooslSourceMap;
import nl.esi.poosl.rotalumisclient.sourcemapping.PooslSourceMapping;

public class StackTraceLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof TErrorStackframe) {
			TErrorStackframe stackframe = (TErrorStackframe) element;
			return stackframe.getId().toString() + " " + stackframe.getMethod();
		} else if (element instanceof StackFrameMapping) {
			StackFrameMapping frameMapping = (StackFrameMapping) element;
			String mappingInfo = "";

			try {
				PooslSourceMapping mapping = frameMapping.getMapping();
				if (mapping != null && mapping != PooslSourceMap.EMPTY_MAPPING) {
					Path path = new Path(mapping.getFilePath());
					mappingInfo = "(" + path.segment(path.segmentCount() - 1) + ":" + mapping.getLineNumber() + ")";
				}
			} catch (Exception e) {
				// do nothing
			}
			return frameMapping.getFrame().getId().toString() + " " + frameMapping.getFrame().getMethod() + " "
					+ mappingInfo;
		}
		return super.getText(element);
	}
}
