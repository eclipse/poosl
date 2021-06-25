package nl.esi.poosl.legacysupport.preferences;

import nl.esi.poosl.legacysupport.Activator;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class Poosl2DotPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public Poosl2DotPreferencePage(int style) {
		super(style);
	}

	public Poosl2DotPreferencePage(String title, int style) {
		super(title, style);
	}

	public Poosl2DotPreferencePage(String title, ImageDescriptor image, int style) {
		super(title, image, style);
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("These are the preferences that will be used when generating DOT graphs.");
	}

	@Override
	protected void createFieldEditors() {
		addField(new FileFieldEditor("GRAPHVIZ_NEATO", "&Neato executable in Graphviz bin directory: ",
				getFieldEditorParent()));
		addField(new BooleanFieldEditor("HIDE_PORTS", "&Hide ports", getFieldEditorParent()));
	}

}
