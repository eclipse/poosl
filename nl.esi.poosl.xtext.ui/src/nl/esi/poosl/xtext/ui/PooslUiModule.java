package nl.esi.poosl.xtext.ui;

import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.findReferences.IReferenceFinder;
import org.eclipse.xtext.ide.editor.syntaxcoloring.ISemanticHighlightingCalculator;
import org.eclipse.xtext.resource.EObjectAtOffsetHelper;
import org.eclipse.xtext.ui.editor.IURIEditorOpener;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.autoedit.AbstractEditStrategyProvider;
import org.eclipse.xtext.ui.editor.folding.IFoldingRegionProvider;
import org.eclipse.xtext.ui.editor.hover.IEObjectHoverProvider;
import org.eclipse.xtext.ui.editor.model.ITokenTypeToPartitionTypeMapper;
import org.eclipse.xtext.ui.editor.syntaxcoloring.AbstractAntlrTokenToAttributeIdMapper;
import org.eclipse.xtext.ui.refactoring.IReferenceUpdater;
import org.eclipse.xtext.ui.refactoring.impl.ReferenceUpdaterDispatcher;

import nl.esi.poosl.xtext.ui.references.PooslEObjectAtOffsetHelper;
import nl.esi.poosl.xtext.ui.references.PooslReferenceFinder;
import nl.esi.poosl.xtext.ui.references.PooslReferenceUpdater;
import nl.esi.poosl.xtext.ui.references.PooslReferenceUpdaterDispatcher;

@SuppressWarnings({ "restriction" })
public class PooslUiModule extends nl.esi.poosl.xtext.ui.AbstractPooslUiModule {
	public PooslUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	public Class<? extends IFoldingRegionProvider> bindIFoldingRegionProvider() {
		return PooslFoldingProvider.class;
	}

	// Based on: http://www.mo-seph.com/projects/syntaxhighlighting
	public Class<? extends ISemanticHighlightingCalculator> bindISemanticHighlightingCalculator() {
		return PooslHighlightingCalculator.class;
	}

	// Based on:
	// http://www.eclipse.org/forums/index.php?t=msg&goto=479001&#msg_479001
	public Class<? extends AbstractAntlrTokenToAttributeIdMapper> bindAbstractAntlrTokenToAttributeIdMapper() {
		return PooslAntlrTokenToAttributeIdMapper.class;
	}

	@Override
	public Class<? extends AbstractEditStrategyProvider> bindAbstractEditStrategyProvider() {
		return PooslAutoEditStrategyProvider.class;
	}

	public Class<? extends ITokenTypeToPartitionTypeMapper> bindITokenTypeToPartitionTypeMapper() {
		return PooslTerminalsTokenTypeToPartitionMapper.class;
	}

	public Class<? extends XtextEditor> bindXtextEditor() {
		return PooslEditor.class;
	}

	public Class<? extends IEObjectHoverProvider> bindIEObjectHoverProvider() {
		return PooslHoverProvider.class;
	}

	@Override
	public Class<? extends IHyperlinkDetector> bindIHyperlinkDetector() {
		return PooslHyperlinkDetector.class;
	}

	public Class<? extends EObjectAtOffsetHelper> bindEObjectAtOffsetHelper() {
		return PooslEObjectAtOffsetHelper.class;
	}

	public Class<? extends IReferenceFinder> bindIReferenceFinder() {
		return PooslReferenceFinder.class;
	}

	@Override
	public Class<? extends IReferenceUpdater> bindIReferenceUpdater() {
		return PooslReferenceUpdater.class;
	}

	public Class<? extends ReferenceUpdaterDispatcher> bindReferenceUpdaterDispatcher() {
		return PooslReferenceUpdaterDispatcher.class;
	}

	public Class<? extends IURIEditorOpener> bindURIEditorOpener() {
		return PooslURIEditorOpener.class;
	}
}
