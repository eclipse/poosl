package nl.esi.poosl.rotalumisclient.views;

import java.util.ArrayList;
import java.util.List;

import nl.esi.poosl.rotalumisclient.views.diagram.PooslTreeContentProvider;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.CheckedTreeSelectionDialog;

public class PooslSequenceDiagramConfigurationView extends CheckedTreeSelectionDialog {
	private boolean filterSetting;
	private Button filterSettingButton;
	private boolean initialFilterSetting;
	private int messageSerialNumber;
	private ICheckStateListener iCheckStateListener;
	private CheckboxTreeViewer treeViewer;

	public PooslSequenceDiagramConfigurationView(Shell shell, ILabelProvider labelProvider,
			ITreeContentProvider contentProvider) {
		super(shell, labelProvider, contentProvider);
		setHelpAvailable(false);
	}

	@Override
	protected CheckboxTreeViewer createTreeViewer(Composite parent) {
		treeViewer = super.createTreeViewer(parent);
		treeViewer.expandAll();

		iCheckStateListener = new ICheckStateListener() {
			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				if (event.getChecked()) {
					setChildCheckedStateRecursivly(event.getElement());
					setParentCheckedStateRecursivly(event.getElement());
				}
			}

			private void setParentCheckedStateRecursivly(Object element) {
				PooslTreeContentProvider contentProvider = (PooslTreeContentProvider) treeViewer.getContentProvider();
				Object parent = contentProvider.getParent(element);
				if (parent != null) {
					treeViewer.setChecked(parent, false);
					setParentCheckedStateRecursivly(parent);
				}
			}

			private void setChildCheckedStateRecursivly(Object element) {
				PooslTreeContentProvider contentProvider = (PooslTreeContentProvider) treeViewer.getContentProvider();
				if (contentProvider.hasChildren(element)) {
					Object[] children = contentProvider.getChildren(element);
					for (Object child : children) {
						treeViewer.setChecked(child, false);
						setChildCheckedStateRecursivly(child);
					}
				}
			}
		};

		treeViewer.addCheckStateListener(iCheckStateListener);
		filterSettingButton = new Button(parent, SWT.CHECK);
		filterSettingButton.setText("Only save filtered messages.");
		filterSettingButton.setSelection(filterSetting);
		filterSettingButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				filterSetting = ((Button) e.getSource()).getSelection();
				updateOKStatus();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// No action required
			}
		});
		return treeViewer;
	}

	@Override
	protected Composite createSelectionButtons(Composite composite) {
		final CheckboxTreeViewer fViewer = getTreeViewer();
		Composite buttonComposite = new Composite(composite, SWT.RIGHT);
		GridLayout layout = new GridLayout();
		layout.numColumns = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		buttonComposite.setLayout(layout);
		buttonComposite.setFont(composite.getFont());
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		buttonComposite.setLayoutData(data);
		Button selectButton = createButton(buttonComposite, IDialogConstants.SELECT_ALL_ID, "Select all instances",
				false);
		SelectionListener listener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object[] checkedElements = getLeafs(
						((PooslTreeContentProvider) fViewer.getContentProvider()).getElements(fViewer.getInput()),
						(PooslTreeContentProvider) fViewer.getContentProvider());
				fViewer.setCheckedElements(checkedElements);
				updateOKStatus();
			}
		};
		selectButton.addSelectionListener(listener);
		Button deselectButton = createButton(buttonComposite, IDialogConstants.DESELECT_ALL_ID, "Deselect all", false);
		listener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				fViewer.setCheckedElements(new Object[0]);
				updateOKStatus();
			}
		};
		deselectButton.addSelectionListener(listener);
		return buttonComposite;
	}

	public Object[] getLeafs(Object[] rootNodes, PooslTreeContentProvider contentProvider) {
		List<Object> leafs = new ArrayList<>();
		for (Object rootNode : rootNodes) {
			leafs.addAll(getLeafs(rootNode, contentProvider));
		}
		return leafs.toArray();
	}

	public List<Object> getLeafs(Object input, PooslTreeContentProvider contentProvider) {
		List<Object> leafs = new ArrayList<>();
		if (contentProvider.hasChildren(input)) {
			for (Object child : contentProvider.getChildren(input)) {
				leafs.addAll(getLeafs(child, contentProvider));
			}
		} else {
			leafs.add(input);
		}
		return leafs;
	}

	public boolean isFilterSettingEnabled() {
		return filterSetting;
	}

	public void setInitialFilterSetting(boolean filterSetting) {
		this.filterSetting = filterSetting;
		initialFilterSetting = filterSetting;
	}

	@Override
	protected void okPressed() {
		if (filterSettingButton.getSelection() != initialFilterSetting
				|| filterSettingButton.getSelection() && messageSerialNumber > 0) {
			MessageDialog dialog = new MessageDialog(Display.getDefault().getActiveShell(), "Clear messages", null,
					"This change in the filter settings will clear all stored messages.\nAre you sure you want to continue?",
					MessageDialog.CONFIRM, new String[] { "Yes", "No" }, 0);
			if (dialog.open() != MessageDialog.OK) {
				return;
			}
		}
		if (treeViewer != null && iCheckStateListener != null) {
			treeViewer.removeCheckStateListener(iCheckStateListener);
		}
		super.okPressed();
	}

	public void setMessageCount(int messageSerialNumber) {
		this.messageSerialNumber = messageSerialNumber;
	}

	@Override
	public boolean close() {
		if (treeViewer != null && iCheckStateListener != null) {
			treeViewer.removeCheckStateListener(iCheckStateListener);
		}
		return super.close();
	}
}
