package nl.esi.poosl.xtext.ui;

import org.apache.log4j.Logger;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.xtext.ui.editor.LanguageSpecificURIEditorOpener;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;

public class PooslURIEditorOpener extends LanguageSpecificURIEditorOpener {
	private static final Logger logger = Logger.getLogger(PooslURIEditorOpener.class);

	@Override
	public IEditorPart open(URI uri, EReference crossReference, int indexInList, boolean select) {
		IEditorPart part = super.open(uri, crossReference, indexInList, select);
		if (part == null) {
			try {
				if (uri.isFile()) {
					IPath path = new Path(uri.toFileString());
					if (path.isAbsolute()) {
						IFileStore fileStore = EFS.getLocalFileSystem().getStore(path);
						if (fileStore != null) {
							IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
									.getActivePage();
							IEditorPart editor = IDE.openEditorOnFileStore(activePage, fileStore);
							selectAndReveal(editor, uri, crossReference, indexInList, select);
							return EditorUtils.getXtextEditor(editor);
						}
					}
				}

			} catch (WrappedException e) {
				logger.error("Error while opening editor part for EMF URI '" + uri + "'", e.getCause());
			} catch (PartInitException partInitException) {
				logger.error("Error while opening editor part for EMF URI '" + uri + "'", partInitException);
			}
		}
		return part;
	}
}
