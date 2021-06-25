package nl.esi.poosl.rotalumisclient.debug;

import org.eclipse.debug.core.model.IPersistableSourceLocator;
import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupDirector;
import org.eclipse.debug.core.sourcelookup.ISourceLookupParticipant;

public class PooslSourceLocator extends AbstractSourceLookupDirector implements IPersistableSourceLocator {

	@Override
	public void initializeParticipants() {
		addParticipants(new ISourceLookupParticipant[] { new PooslSourceLookupParticipant() });
	}
}
