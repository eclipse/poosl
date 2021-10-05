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
package org.eclipse.poosl.xtext.ui.outline;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.poosl.ClusterClass;
import org.eclipse.poosl.DataClass;
import org.eclipse.poosl.DataMethod;
import org.eclipse.poosl.DataMethodBinaryOperator;
import org.eclipse.poosl.DataMethodNamed;
import org.eclipse.poosl.DataMethodUnaryOperator;
import org.eclipse.poosl.Instance;
import org.eclipse.poosl.Poosl;
import org.eclipse.poosl.PooslPackage;
import org.eclipse.poosl.ProcessClass;
import org.eclipse.poosl.ProcessMethod;
import org.eclipse.poosl.provider.PooslEditPlugin;
import org.eclipse.poosl.xtext.custom.FormattingHelper;
import org.eclipse.poosl.xtext.helpers.HelperFunctions;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider;
import org.eclipse.xtext.ui.editor.outline.impl.DocumentRootNode;
import org.eclipse.xtext.ui.editor.outline.impl.EStructuralFeatureNode;

/**
 * The PooslOutlineTreeProvider.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
// CHECKSTYLE:OFF Naming inherited from XText generation
public class PooslOutlineTreeProvider extends DefaultOutlineTreeProvider {
    private static final String LABEL_DATA_CLASSES = "Data classes"; //$NON-NLS-1$

    private static final String LABEL_PROCESS_CLASSES = "Process classes"; //$NON-NLS-1$

    private static final String LABEL_CLUSTER_CLASSES = "Cluster classes";

    private static final String LABEL_SYSTEM = "System";

    private static final String PREFIX_DATA_CLASS = "Data class ";

    private static final String PREFIX_DATA_METHOD_UNARY_OPERATOR = "Operator ";

    private static final String PREFIX_DATA_METHOD_BINARY_OPERATOR = "Operator ";

    private static final String PREFIX_DATA_METHOD = "Method ";

    private static final String PREFIX_PROCESS_CLASS = "Process class ";

    private static final String PREFIX_PROCESS_METHOD = "Method ";

    private static final String PREFIX_CLUSTER_CLASS = "Cluster class ";

    private static final String PREFIX_INSTANCE = "Instance ";

    // --- POOSL -------

    protected void _createChildren(DocumentRootNode parentNode, Poosl poosl) {
        IOutlineNode nodeDataClasses = createEStructuralFeatureNode(parentNode, poosl, PooslPackage.Literals.POOSL__DATA_CLASSES, _image(null), LABEL_DATA_CLASSES, poosl.getDataClasses().isEmpty());
        for (DataClass dClass : poosl.getDataClasses()) {
            createNode(nodeDataClasses, dClass);
        }

        IOutlineNode processDataClasses = createEStructuralFeatureNode(parentNode, poosl, PooslPackage.Literals.POOSL__PROCESS_CLASSES, _image(null), LABEL_PROCESS_CLASSES,
                poosl.getProcessClasses().isEmpty());
        for (ProcessClass pClass : poosl.getProcessClasses()) {
            createNode(processDataClasses, pClass);
        }

        IOutlineNode clusterDataClasses = createEStructuralFeatureNode(parentNode, poosl, PooslPackage.Literals.POOSL__CLUSTER_CLASSES, _image(null), LABEL_CLUSTER_CLASSES,
                poosl.getClusterClasses().isEmpty());
        for (ClusterClass cClass : poosl.getClusterClasses()) {
            if (cClass.getName() != null) {
                createNode(clusterDataClasses, cClass);
            }
        }
        ClusterClass system = HelperFunctions.getSystem(poosl);
        if (system != null) {
            createNode(parentNode, system);
        }
    }

    @Override
    protected void _createChildren(EStructuralFeatureNode parentNode, EObject modelElement) {
    }

    // --- Data class -------

    String _text(DataClass dClass) {
        StringBuilder buf = new StringBuilder();
        buf.append(PREFIX_DATA_CLASS);
        if (dClass.getName() != null) {
            buf.append(dClass.getName());
        }
        return buf.toString();
    }

    protected void _createChildren(IOutlineNode parentNode, DataClass dClass) {
        for (DataMethod dMethod : dClass.getDataMethodsUnaryOperator()) {
            createNode(parentNode, dMethod);
        }
        for (DataMethod dMethod : dClass.getDataMethodsBinaryOperator()) {
            createNode(parentNode, dMethod);
        }
        for (DataMethod dMethod : dClass.getDataMethodsNamed()) {
            createNode(parentNode, dMethod);
        }
    }

    String _text(DataMethodUnaryOperator dMethod) {
        StringBuilder buf = new StringBuilder();
        buf.append(PREFIX_DATA_METHOD_UNARY_OPERATOR);
        FormattingHelper.formatDataMethod(buf, dMethod, false);
        return buf.toString();
    }

    String _text(DataMethodBinaryOperator dMethod) {
        StringBuilder buf = new StringBuilder();
        buf.append(PREFIX_DATA_METHOD_BINARY_OPERATOR);
        FormattingHelper.formatDataMethod(buf, dMethod, false);
        return buf.toString();
    }

    String _text(DataMethodNamed dMethod) {
        StringBuilder buf = new StringBuilder();
        buf.append(PREFIX_DATA_METHOD);
        FormattingHelper.formatDataMethod(buf, dMethod, false);
        return buf.toString();

    }

    protected void _createChildren(IOutlineNode parentNode, DataMethod dMethod) {
        // We do not want to show any children for a data method
    }

    protected boolean _isLeaf(DataMethod dMethod) {
        return true;
    }

    // --- Process class -------

    String _text(ProcessClass pClass) {
        StringBuilder buf = new StringBuilder();
        buf.append(PREFIX_PROCESS_CLASS);
        if (pClass.getName() != null) {
            buf.append(pClass.getName());
        }
        FormattingHelper.formatDeclarations(buf, pClass.getParameters());
        return buf.toString();
    }

    protected void _createChildren(IOutlineNode parentNode, ProcessClass pClass) {
        for (ProcessMethod pMethod : pClass.getMethods()) {
            createNode(parentNode, pMethod);
        }
    }

    String _text(ProcessMethod pMethod) {
        StringBuilder buf = new StringBuilder();
        buf.append(PREFIX_PROCESS_METHOD);
        FormattingHelper.formatProcessMethod(buf, pMethod, false);
        return buf.toString();
    }

    protected void _createChildren(IOutlineNode parentNode, ProcessMethod pMethod) {
        // We do not want to show any children for a process method
    }

    protected boolean _isLeaf(ProcessMethod processMethod) {
        return true;
    }

    // --- Cluster class -------

    String _text(ClusterClass cClass) {
        StringBuilder buf = new StringBuilder();

        if (cClass.getName() != null) {
            buf.append(PREFIX_CLUSTER_CLASS);
            buf.append(cClass.getName());
        } else {
            buf.append(LABEL_SYSTEM);
        }
        FormattingHelper.formatDeclarations(buf, cClass.getParameters());
        return buf.toString();
    }

    protected void _createChildren(IOutlineNode parentNode, ClusterClass cClass) {
        for (Instance instance : cClass.getInstances()) {
            createNode(parentNode, instance);
        }
    }

    String _text(Instance instance) {
        StringBuilder buf = new StringBuilder();
        buf.append(PREFIX_INSTANCE);
        FormattingHelper.formatInstance(buf, instance);
        return buf.toString();
    }

    protected void _createChildren(IOutlineNode parentNode, Instance instance) {
        // We do not want to show any children for a instance
    }

    protected boolean _isLeaf(Instance instance) {
        return true;
    }

    @Override
    protected Image _image(Object modelElement) {
        // Do not rely on the default implementation, as this accesses
        // org.eclipse.poosl.model.edit in a way that causes memory leaks
        // Code below is based on AdapterFactoryLabelProvider: getDefaultImage and
        // getImageFromObject

        String imageStr = null;
        if (modelElement instanceof Poosl) {
            imageStr = "full/obj16/Poosl"; //$NON-NLS-1$
        } else if (modelElement instanceof ClusterClass) {
            imageStr = "full/obj16/ClusterClass"; //$NON-NLS-1$
        } else if (modelElement instanceof DataClass) {
            imageStr = "full/obj16/DataClass"; //$NON-NLS-1$
        } else if (modelElement instanceof ProcessClass) {
            imageStr = "full/obj16/ProcessClass"; //$NON-NLS-1$
        } else if (modelElement instanceof ProcessMethod) {
            imageStr = "full/obj16/ProcessMethod"; //$NON-NLS-1$
        } else if (modelElement instanceof DataMethodNamed) {
            imageStr = "full/obj16/DataMethod"; //$NON-NLS-1$
        } else if (modelElement instanceof Instance) {
            imageStr = "full/obj16/Instance"; //$NON-NLS-1$
        } else if (modelElement instanceof DataMethodUnaryOperator) {
            imageStr = "full/obj16/DataMethodUnaryOperator"; //$NON-NLS-1$
        } else if (modelElement instanceof DataMethodBinaryOperator) {
            imageStr = "full/obj16/DataMethodBinaryOperator"; //$NON-NLS-1$
        }

        Object imageObj = null;
        if (imageStr != null) {
            imageObj = PooslEditPlugin.INSTANCE.getImage(imageStr);
        } else {
            imageStr = "full/obj16/GenericValue"; //$NON-NLS-1$
            imageObj = EMFEditPlugin.INSTANCE.getImage(imageStr);
        }
        return ExtendedImageRegistry.INSTANCE.getImage(imageObj);
    }
}
