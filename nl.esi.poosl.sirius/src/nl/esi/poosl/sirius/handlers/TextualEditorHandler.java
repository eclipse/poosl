package nl.esi.poosl.sirius.handlers;

import nl.esi.poosl.sirius.helpers.TextualEditorHelper;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.ui.handlers.HandlerUtil;

public class TextualEditorHandler extends AbstractHandler {

	/**
	 * Gets the opened editor and the containing representation The description is
	 * used to determine the type of diagram and then
	 * {@link TextualEditorHelper #openTextualEditor(EObject, boolean)} handles the
	 * rest.
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		DialectEditor dialect = (DialectEditor) HandlerUtil.getActiveEditor(event);
		DRepresentation representation = dialect.getRepresentation();
		RepresentationDescription description = DialectManager.INSTANCE.getDescription(representation);

		boolean classdiagram = description.getName().equalsIgnoreCase("Class diagram");
		EObject target = representation instanceof DSemanticDiagram ? ((DSemanticDiagram) representation).getTarget()
				: representation.getOwnedRepresentationElements().get(0).getTarget();

		TextualEditorHelper.openTextualEditor(target, !classdiagram);
		return null;
	}
}
