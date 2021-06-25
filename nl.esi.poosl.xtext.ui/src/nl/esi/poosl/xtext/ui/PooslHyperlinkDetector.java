package nl.esi.poosl.xtext.ui;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.hyperlinking.DefaultHyperlinkDetector;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkAcceptor;
import org.eclipse.xtext.ui.editor.hyperlinking.XtextHyperlink;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.util.ITextRegion;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Provider;

import nl.esi.poosl.Import;
import nl.esi.poosl.Poosl;
import nl.esi.poosl.xtext.importing.ImportingHelper;
import nl.esi.poosl.xtext.ui.labeling.PooslLabelProvider;

public class PooslHyperlinkDetector extends DefaultHyperlinkDetector {

	private static class HyperlinkAcceptor implements IHyperlinkAcceptor {
		private final List<IHyperlink> links;

		public HyperlinkAcceptor(List<IHyperlink> links) {
			this.links = links;
		}

		public void accept(IHyperlink hyperlink) {
			if (hyperlink != null) {
				links.add(hyperlink);
			}
		}
	}

	@Inject
	private Provider<XtextHyperlink> hyperlinkProvider;

	@Override
	public IHyperlink[] detectHyperlinks(ITextViewer textViewer, final IRegion region,
			boolean canShowMultipleHyperlinks) {
		IHyperlink[] hyperlinks = super.detectHyperlinks(textViewer, region, canShowMultipleHyperlinks);
		if (hyperlinks == null) {
			return createHyperLinks(textViewer, region);
		}
		return hyperlinks;
	}

	private IHyperlink[] createHyperLinks(ITextViewer textViewer, final IRegion region) {
		return ((IXtextDocument) textViewer.getDocument())
				.priorityReadOnly(new IUnitOfWork<IHyperlink[], XtextResource>() {
					public IHyperlink[] exec(XtextResource resource) throws Exception {
						IParseResult parseResult = resource.getParseResult();
						ILeafNode leaf = NodeModelUtils.findLeafNodeAtOffset(parseResult.getRootNode(),
								region.getOffset());
						if (leaf != null && leaf.getSemanticElement() instanceof Import
								&& leaf.getGrammarElement() instanceof RuleCall) {
							Import imp = (Import) leaf.getSemanticElement();
							Resource res = ImportingHelper.resolveImport(imp, isImportLib(leaf));
							Poosl importedPoosl = ImportingHelper.toPoosl(res);
							if (importedPoosl != null) {
								// Create a hyperlink for the root object
								// (poosl) of the imported resource
								return createHyperLinksFor((XtextResource) res, leaf, res.getContents().get(0));
							}
						}
						return null;
					}
				});
	}

	private boolean isImportLib(ILeafNode leaf) {
		ICompositeNode parent = leaf.getParent();
		for (ILeafNode iLeafNode : parent.getLeafNodes()) {
			Object grammar = iLeafNode.getGrammarElement();
			if (grammar instanceof Keyword) {
				return (((Keyword) grammar).getValue().equals("importlib"));
			}
		}
		return false;
	}

	private IHyperlink[] createHyperLinksFor(XtextResource resource, ILeafNode sourceNode, EObject targetObject) {
		// The code in this method is based on the
		// org.eclipse.xtext.ui.editor.hyperlinking.HyperlinkHelper
		ITextRegion textRegion = sourceNode.getTextRegion();
		IRegion region = new Region(textRegion.getOffset(), textRegion.getLength());
		PooslLabelProvider labelProvider = new PooslLabelProvider(null);
		URIConverter uriConverter = resource.getResourceSet().getURIConverter();
		String hyperlinkText = "Open '" + labelProvider.getText(targetObject) + "' in a Poosl editor.";
		URI uri = EcoreUtil.getURI(targetObject);
		URI normalized = uri.isPlatformResource() ? uri : uriConverter.normalize(uri);

		XtextHyperlink result = hyperlinkProvider.get();
		result.setHyperlinkRegion(region);
		result.setURI(normalized);
		result.setHyperlinkText(hyperlinkText);
		return addHyperLink(result);
	}

	private IHyperlink[] addHyperLink(IHyperlink link) {
		List<IHyperlink> links = Lists.newArrayList();
		IHyperlinkAcceptor acceptor = new HyperlinkAcceptor(links);
		acceptor.accept(link);
		if (!links.isEmpty()) {
			return Iterables.toArray(links, IHyperlink.class);
		}
		return new IHyperlink[0];
	}
}
