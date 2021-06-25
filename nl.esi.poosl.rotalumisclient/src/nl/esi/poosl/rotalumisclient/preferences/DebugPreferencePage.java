package nl.esi.poosl.rotalumisclient.preferences;

import java.util.logging.Level;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

import nl.esi.poosl.rotalumisclient.Activator;
import nl.esi.poosl.rotalumisclient.PooslConstants;

public class DebugPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
	private static final String LABEL_TEXT = "Logging level: ";
	private Scale scale;
	private Label label;

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Interaction between the POOSL IDE and the simulator.");
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite main = new Composite(parent, SWT.NONE);
		main.setLayout(new GridLayout());
		main.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench != null) {
			workbench.getHelpSystem().setHelp(getControl(), "nl.esi.poosl.help.help_preferences_simulator");
		}

		label = new Label(main, SWT.NONE);
		label.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		scale = new Scale(main, SWT.NONE);
		scale.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		scale.setMaximum(8);
		scale.setPageIncrement(1);
		scale.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Scale localScale = (Scale) e.getSource();
				label.setText(LABEL_TEXT + getLevel(localScale.getSelection()));
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
		});
		String level = getPreferenceStore().getString(PooslConstants.PREFERENCES_LOG_LEVEL);
		if (level.isEmpty()) {
			level = PooslConstants.DEFAULT_LOG_LEVEL;
		}
		scale.setSelection(getLevel(Level.parse(level)));
		label.setText(LABEL_TEXT + getLevel(scale.getSelection()));
		Composite composite = new Composite(main, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		composite.setLayout(gridLayout);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.WRAP, true, false));
		Label label2 = new Label(composite, SWT.NONE);
		label2.setText("Off");
		label2.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, false));
		Label label3 = new Label(composite, SWT.NONE);
		label3.setText("All");
		label3.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, false));
		return parent;
	}

	private int getLevel(Level level) {
		int intLevel = 0;
		if (level.equals(Level.OFF)) {
			intLevel = 0;
		} else if (level.equals(Level.SEVERE)) {
			intLevel = 1;
		} else if (level.equals(Level.WARNING)) {
			intLevel = 2;
		} else if (level.equals(Level.INFO)) {
			intLevel = 3;
		} else if (level.equals(Level.CONFIG)) {
			intLevel = 4;
		} else if (level.equals(Level.FINE)) {
			intLevel = 5;
		} else if (level.equals(Level.FINER)) {
			intLevel = 6;
		} else if (level.equals(Level.FINEST)) {
			intLevel = 7;
		} else if (level.equals(Level.ALL)) {
			intLevel = 8;
		}
		return intLevel;
	}

	private Level getLevel(int intLevel) {
		Level level = Level.parse(PooslConstants.DEFAULT_LOG_LEVEL);
		switch (intLevel) {
		case 0:
			level = Level.OFF;
			break;
		case 1:
			level = Level.SEVERE;
			break;
		case 2:
			level = Level.WARNING;
			break;
		case 3:
			level = Level.INFO;
			break;
		case 4:
			level = Level.CONFIG;
			break;
		case 5:
			level = Level.FINE;
			break;
		case 6:
			level = Level.FINER;
			break;
		case 7:
			level = Level.FINEST;
			break;
		case 8:
			level = Level.ALL;
			break;
		default:

			break;
		}
		return level;
	}

	@Override
	protected void performDefaults() {
		scale.setSelection(getLevel(Level.parse(PooslConstants.DEFAULT_LOG_LEVEL)));
		label.setText(LABEL_TEXT + PooslConstants.DEFAULT_LOG_LEVEL);
		super.performDefaults();
	}

	@Override
	protected void performApply() {
		getPreferenceStore().setValue(PooslConstants.PREFERENCES_LOG_LEVEL, getLevel(scale.getSelection()).toString());
		super.performApply();
	}

	@Override
	public boolean performOk() {
		getPreferenceStore().setValue(PooslConstants.PREFERENCES_LOG_LEVEL, getLevel(scale.getSelection()).toString());
		return super.performOk();
	}
}
