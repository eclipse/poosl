package nl.esi.poosl.sirius.dialogs;

import javax.annotation.PostConstruct;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ProgressBar;

public class PooslProgressMonitor implements IProgressMonitor {
	private ProgressBar progressBar;

	@PostConstruct
	public void createControls(Composite parent) {
		progressBar = new ProgressBar(parent, SWT.SMOOTH);
		progressBar.setBounds(100, 10, 200, 20);
	}

	@Override
	public void worked(final int work) {
		progressBar.setSelection(progressBar.getSelection() + work);
	}

	@Override
	public void subTask(String name) {
		// do nothing
	}

	@Override
	public void setTaskName(String name) {
		// do nothing
	}

	@Override
	public void setCanceled(boolean value) {
		// do nothing
	}

	@Override
	public boolean isCanceled() {
		return false;
	}

	@Override
	public void internalWorked(double work) {
		// do nothing
	}

	@Override
	public void done() {
		// do nothing
	}

	@Override
	public void beginTask(String name, int totalWork) {
		progressBar.setMaximum(totalWork);
		progressBar.setToolTipText(name);
	}
}
