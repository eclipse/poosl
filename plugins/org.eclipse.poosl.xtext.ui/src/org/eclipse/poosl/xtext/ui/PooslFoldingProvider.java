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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.poosl.ClusterClass;
import org.eclipse.poosl.DataClass;
import org.eclipse.poosl.InstantiableClass;
import org.eclipse.poosl.Poosl;
import org.eclipse.poosl.ProcessClass;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.folding.DefaultFoldingRegionProvider;
import org.eclipse.xtext.ui.editor.folding.IFoldingRegionAcceptor;
import org.eclipse.xtext.util.ITextRegion;

/**
 * The PooslFoldingProvider.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslFoldingProvider extends DefaultFoldingRegionProvider {

    @Override
    protected void computeObjectFolding(XtextResource xtextResource, IFoldingRegionAcceptor<ITextRegion> foldingRegionAcceptor) {
        super.computeObjectFolding(xtextResource, foldingRegionAcceptor);

        IParseResult parseResult = xtextResource.getParseResult();
        if (parseResult != null) {
            Poosl poosl = (Poosl) parseResult.getRootASTElement();
            if (poosl != null) {
                for (DataClass dClass : poosl.getDataClasses()) {
                    foldDataClassVariables(dClass, foldingRegionAcceptor);
                }
                for (ProcessClass pClass : poosl.getProcessClasses()) {
                    foldProcessClassVariables(pClass, foldingRegionAcceptor);
                    foldInstantiableClassPorts(pClass, foldingRegionAcceptor);
                    foldProcessClassMessages(pClass, foldingRegionAcceptor);
                }
                for (ClusterClass cClass : poosl.getClusterClasses()) {
                    foldInstantiableClassPorts(cClass, foldingRegionAcceptor);
                }
            }
        }

    }

    private void foldDataClassVariables(DataClass dClass, IFoldingRegionAcceptor<ITextRegion> foldingRegionAcceptor) {
        INode iNodeClass = NodeModelUtils.getNode(dClass);
        int begin = iNodeClass.getOffset() + iNodeClass.getLength();
        int end = iNodeClass.getOffset();

        for (EObject eObject : dClass.getInstanceVariables()) {
            INode iNode = NodeModelUtils.getNode(eObject);
            begin = Math.min(begin, iNode.getOffset());
            end = Math.max(end, iNode.getOffset() + iNode.getLength());
        }

        foldingRegionAcceptor.accept(begin, end - begin);
    }

    private void foldProcessClassVariables(ProcessClass pClass, IFoldingRegionAcceptor<ITextRegion> foldingRegionAcceptor) {
        INode iNodeClass = NodeModelUtils.getNode(pClass);
        int begin = iNodeClass.getOffset() + iNodeClass.getLength();
        int end = iNodeClass.getOffset();

        for (EObject eObject : pClass.getInstanceVariables()) {
            INode iNode = NodeModelUtils.getNode(eObject);
            begin = Math.min(begin, iNode.getOffset());
            end = Math.max(end, iNode.getOffset() + iNode.getLength());
        }

        foldingRegionAcceptor.accept(begin, end - begin);
    }

    private void foldInstantiableClassPorts(InstantiableClass iClass, IFoldingRegionAcceptor<ITextRegion> foldingRegionAcceptor) {
        INode iNodeClass = NodeModelUtils.getNode(iClass);
        int begin = iNodeClass.getOffset() + iNodeClass.getLength();
        int end = iNodeClass.getOffset();

        for (EObject eObject : iClass.getPorts()) {
            INode iNode = NodeModelUtils.getNode(eObject);
            begin = Math.min(begin, iNode.getOffset());
            end = Math.max(end, iNode.getOffset() + iNode.getLength());
        }

        foldingRegionAcceptor.accept(begin, end - begin);
    }

    private void foldProcessClassMessages(ProcessClass pClass, IFoldingRegionAcceptor<ITextRegion> foldingRegionAcceptor) {
        INode iNodeClass = NodeModelUtils.getNode(pClass);
        int begin = iNodeClass.getOffset() + iNodeClass.getLength();
        int end = iNodeClass.getOffset();

        for (EObject eObject : pClass.getReceiveMessages()) {
            INode iNode = NodeModelUtils.getNode(eObject);
            begin = Math.min(begin, iNode.getOffset());
            end = Math.max(end, iNode.getOffset() + iNode.getLength());
        }
        for (EObject eObject : pClass.getSendMessages()) {
            INode iNode = NodeModelUtils.getNode(eObject);
            begin = Math.min(begin, iNode.getOffset());
            end = Math.max(end, iNode.getOffset() + iNode.getLength());
        }

        foldingRegionAcceptor.accept(begin, end - begin);
    }
}
