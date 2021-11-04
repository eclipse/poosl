/*******************************************************************************
 * Copyright (c) 2021 TNO/ESI
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    TNO/ESI - initial API and implementation
 *    Obeo - refactoring
 *******************************************************************************/
package org.eclipse.poosl.xtext.ui;

import java.text.MessageFormat;
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
import org.eclipse.poosl.Import;
import org.eclipse.poosl.Poosl;
import org.eclipse.poosl.xtext.importing.ImportingHelper;
import org.eclipse.poosl.xtext.ui.labeling.PooslLabelProvider;
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

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * The PooslHyperlinkDetector.
 *
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslHyperlinkDetector extends DefaultHyperlinkDetector {

    private static final String TEXT = "Open '{0}' in a Poosl editor.";

    private static class HyperlinkAcceptor implements IHyperlinkAcceptor {
        private final List<IHyperlink> links;

        HyperlinkAcceptor(List<IHyperlink> links) {
            this.links = links;
        }

        @Override
        public void accept(IHyperlink hyperlink) {
            if (hyperlink != null) {
                links.add(hyperlink);
            }
        }
    }

    @Inject
    private Provider<XtextHyperlink> hyperlinkProvider;

    @Override
    public IHyperlink[] detectHyperlinks(
            ITextViewer textViewer, final IRegion region, boolean canShowMultipleHyperlinks) {
        IHyperlink[] hyperlinks = super.detectHyperlinks(textViewer, region,
                canShowMultipleHyperlinks);
        if (hyperlinks == null) {
            return createHyperLinks(textViewer, region);
        }
        return hyperlinks;
    }

    private IHyperlink[] createHyperLinks(ITextViewer textViewer, final IRegion region) {
        return ((IXtextDocument) textViewer.getDocument()).priorityReadOnly(resource -> {
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
        });
    }

    private boolean isImportLib(ILeafNode leaf) {
        ICompositeNode parent = leaf.getParent();
        for (ILeafNode iLeafNode : parent.getLeafNodes()) {
            Object grammar = iLeafNode.getGrammarElement();
            if (grammar instanceof Keyword) {
                return ((Keyword) grammar).getValue().equals("importlib"); //$NON-NLS-1$
            }
        }
        return false;
    }

    private IHyperlink[] createHyperLinksFor(
            XtextResource resource, ILeafNode sourceNode, EObject targetObject) {
        // The code in this method is based on the
        // org.eclipse.xtext.ui.editor.hyperlinking.HyperlinkHelper
        ITextRegion textRegion = sourceNode.getTextRegion();
        IRegion region = new Region(textRegion.getOffset(), textRegion.getLength());

        URIConverter uriConverter = resource.getResourceSet().getURIConverter();
        URI uri = EcoreUtil.getURI(targetObject);
        URI normalized = uri.isPlatformResource() ? uri : uriConverter.normalize(uri);

        XtextHyperlink result = hyperlinkProvider.get();
        result.setHyperlinkRegion(region);
        result.setURI(normalized);
        PooslLabelProvider labelProvider = new PooslLabelProvider(null);
        result.setHyperlinkText(MessageFormat.format(TEXT, labelProvider.getText(targetObject)));
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
