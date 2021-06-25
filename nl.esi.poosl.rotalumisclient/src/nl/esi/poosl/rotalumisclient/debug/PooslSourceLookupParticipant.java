package nl.esi.poosl.rotalumisclient.debug;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupParticipant;
import org.eclipse.debug.core.sourcelookup.ISourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.ProjectSourceContainer;

import nl.esi.poosl.rotalumisclient.sourcemapping.PooslSourceMapping;

public class PooslSourceLookupParticipant extends AbstractSourceLookupParticipant {

	@Override
	public String getSourceName(Object object) throws CoreException {
		if (object instanceof PooslStackFrame) {
			// Return the fileName of the model
			PooslStackFrame stackFrame = (PooslStackFrame) object;
			PooslSourceMapping sourceMapping = stackFrame.getSourceMapping();

			if (sourceMapping != null) {
				return getRelativeFileString(sourceMapping);
			}
		}
		return null;
	}

	private String getRelativeFileString(PooslSourceMapping sourceMapping) {
		IPath path = new Path(sourceMapping.getFilePath());
		for (ISourceContainer iSourceContainer : getSourceContainers()) {
			if (iSourceContainer instanceof ProjectSourceContainer) {
				IPath ploc = ((ProjectSourceContainer) iSourceContainer).getProject().getLocation();
				if (path.toOSString().startsWith(ploc.toOSString())) {
					return path.makeRelativeTo(ploc).toOSString();
				}
			}
		}
		return null;
	}
}
