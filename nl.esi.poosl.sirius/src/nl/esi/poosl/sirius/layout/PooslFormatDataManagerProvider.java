package nl.esi.poosl.sirius.layout;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.format.IFormatDataManagerProvider;
import org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager;

/**
 * Provides the {@link PooslFormatDataManager} for sirius's {@link DDiagram}
 * 
 * @author kstaal
 */
public class PooslFormatDataManagerProvider implements IFormatDataManagerProvider {
	public PooslFormatDataManagerProvider() {
		// Nothing.
	}

	@Override
	public boolean provides(DDiagram diagram) {
		return true;
	}

	@Override
	public SiriusFormatDataManager getFormatDataManager() {
		return new PooslFormatDataManager();
	}
}
