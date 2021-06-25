package nl.esi.poosl.sirius.navigator;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;

import nl.esi.poosl.sirius.IPreferenceConstants;
import nl.esi.poosl.sirius.navigator.PooslEditorPreferenceDialog.OpenStrategy;

public class GraphicalPreferenceManager extends OpenPreferenceManager {
	private static final String GRAPHICAL_DIALOG_LABEL = "Which type of editor would you like to use?";

	// Used from Graphical views
	public GraphicalPreferenceManager() {
		diagramDescription = PooslEditorPreferenceDialog.COMPOSITE_STRUCTURE_DIAGRAM;
	}

	public String getEditorPreference() {
		IPreferencesService preferencesService = Platform.getPreferencesService();
		Boolean dontask = preferencesService.getBoolean(IPreferenceConstants.PREFERENCE_PLUGIN_ID,
				IPreferenceConstants.PREFERENCE_PROJECT_EXPLORER_DONT_ASK, false, null);
		String currentPref = getCurrentPreference(preferencesService);

		if (dontask) {
			return currentPref;
		} else {
			return askPreference();
		}
	}

	private String getCurrentPreference(IPreferencesService preferencesService) {
		return preferencesService.getString(IPreferenceConstants.PREFERENCE_PLUGIN_ID,
				IPreferenceConstants.PREFERENCE_GRAPHICAL_EDITOR,
				IPreferenceConstants.PREFERENCE_GRAPHICAL_EDITOR_TEXTUAL, null);
	}

	private String askPreference() {
		OpenStrategy result = showEditorPreferenceDialog();
		switch (result) {
		case TEXTUAL:
			return IPreferenceConstants.PREFERENCE_GRAPHICAL_EDITOR_TEXTUAL;
		case GRAPHICAL:
			return IPreferenceConstants.PREFERENCE_GRAPHICAL_EDITOR_GRAPHICAL;
		case CANCEL:
			return null;
		default:
			return IPreferenceConstants.PREFERENCE_GRAPHICAL_EDITOR_TEXTUAL;
		}
	}

	@Override
	protected String getDialogLabel() {
		return GRAPHICAL_DIALOG_LABEL;
	}
}
