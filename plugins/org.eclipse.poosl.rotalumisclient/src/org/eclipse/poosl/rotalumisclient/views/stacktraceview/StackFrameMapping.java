package org.eclipse.poosl.rotalumisclient.views.stacktraceview;

import org.eclipse.poosl.generatedxmlclasses.TErrorStackframe;
import org.eclipse.poosl.rotalumisclient.sourcemapping.PooslSourceMapping;

public class StackFrameMapping {
    private final PooslSourceMapping mapping;

    private final TErrorStackframe frame;

    public StackFrameMapping(TErrorStackframe frame, PooslSourceMapping mapping) {
        this.frame = frame;
        this.mapping = mapping;
    }

    public PooslSourceMapping getMapping() {
        return mapping;
    }

    public TErrorStackframe getFrame() {
        return frame;
    }

}
