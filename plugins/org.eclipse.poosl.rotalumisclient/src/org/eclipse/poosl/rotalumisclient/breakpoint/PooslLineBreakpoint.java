package org.eclipse.poosl.rotalumisclient.breakpoint;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.LineBreakpoint;
import org.eclipse.poosl.rotalumisclient.PooslConstants;

public class PooslLineBreakpoint extends LineBreakpoint {

    public PooslLineBreakpoint() {
        // Add empty constructor for reflection purposes
    }

    public PooslLineBreakpoint(final IResource resource, final int lineNumber) throws DebugException {
        super();
        IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
            public void run(IProgressMonitor monitor) throws CoreException {
                IMarker marker = resource.createMarker("org.eclipse.poosl.rotalumisclient.pooslLineBreakpointMarker");
                setMarker(marker);
                marker.setAttribute(IBreakpoint.ENABLED, true);
                marker.setAttribute(IBreakpoint.PERSISTED, true);
                marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
                marker.setAttribute(IBreakpoint.ID, getModelIdentifier());
                marker.setAttribute(IMarker.MESSAGE, "Poosl Line Breakpoint: " + resource.getName() + " [line: " + lineNumber + "]");
            }
        };
        run(getMarkerRule(resource), runnable);
    }

    @Override
    public String getModelIdentifier() {
        return PooslConstants.DEBUG_MODEL_ID;
    }
}
