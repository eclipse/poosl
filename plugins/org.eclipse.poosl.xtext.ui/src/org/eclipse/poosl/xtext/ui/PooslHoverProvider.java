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
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.poosl.Annotation;
import org.eclipse.poosl.ClusterClass;
import org.eclipse.poosl.DataClass;
import org.eclipse.poosl.DataMethod;
import org.eclipse.poosl.MessageSignature;
import org.eclipse.poosl.PooslPackage.Literals;
import org.eclipse.poosl.ProcessClass;
import org.eclipse.poosl.ProcessMethod;
import org.eclipse.poosl.Variable;
import org.eclipse.poosl.xtext.annotation.AnnotationType;
import org.eclipse.xtext.ui.editor.hover.html.DefaultEObjectHoverProvider;

/**
 * The PooslHoverProvider.
 *
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslHoverProvider extends DefaultEObjectHoverProvider {

    private static final String ANNOTATION_MESSAGE = "{0}\nSee Help(F1), \"{1}\" for more info.";

    private static final String PRIMITIVE_HELP = "Syntax of Test Primitives";

    private static final String INIT_HELP = "Initialization Data Methods";

    private static final String SUPPRESSWARNINGS_HELP = "Initialization Data Methods";

    private static final String TEST_DOCUMENTATION = "This annotation indicates that the data method must be executed as test by the test framework. "
            + "By default the test succeeds if no exception occurs.";

    private static final String SKIP_DOCUMENTATION = "This annotation should be combined with \"@Test\", and is used to skip the test.";

    private static final String ERROR_DOCUMENTATION = "This annotation should be combined with \"@Test\", and modifies the default behavior such that the test succeeds if any exception occurs. "
            + "A parameter can be added to indicate a specific expected exception, e.g., \"@Error(\"string\")\".";

    private static final String INIT_DOCUMENTATION = "This annotation indicates that the data method is an initialization method. "
            + "If a data class has initialization methods, one of these initialization methods has to be called directly after creating an instance of the data clas (using the \"new\" construct). "
            + "The \"@Init\" annotation is not inherited by subclasses.";

    private static final String SUPPRESSWARNINGS_DOCUMENTATION = "This annotation hides the warnings of the provided type in the following class, method, instance or channel. "
            + "The warning types that can be passed are \"unused\", \"unconnected\", \"typecheck\" and \"return\". "
            + "Multiple types can be provided with one annotation by seperating the types with a comma (e.g. @SuppressWarnings(\"unused\", \"typecheck\").";

    private static final Map<AnnotationType, String> ANNOTATION_DOCS = new HashMap<>();

    private static void setAnnotationDoc(AnnotationType type, String doc, String helpChapter) {
        ANNOTATION_DOCS.put(type, MessageFormat.format(ANNOTATION_MESSAGE, doc, helpChapter));
    }

    static {
        setAnnotationDoc(AnnotationType.TEST, TEST_DOCUMENTATION, PRIMITIVE_HELP);
        setAnnotationDoc(AnnotationType.ERROR, ERROR_DOCUMENTATION, PRIMITIVE_HELP);
        setAnnotationDoc(AnnotationType.SKIP, SKIP_DOCUMENTATION, PRIMITIVE_HELP);
        setAnnotationDoc(AnnotationType.INIT, INIT_DOCUMENTATION, INIT_HELP);
        setAnnotationDoc(AnnotationType.SUPPRESSWARNINGS, SUPPRESSWARNINGS_DOCUMENTATION,
                SUPPRESSWARNINGS_HELP);
    }

    /**
     * Returns the documentation of the type.
     *
     * @param it
     *     annotation type
     * @return documentation
     */
    public static String getDocumentation(AnnotationType it) {
        return ANNOTATION_DOCS.get(it);
    }

    @Override
    protected String getFirstLine(EObject o) {
        String objectType;
        if (o instanceof ClusterClass) {
            objectType = "Cluster class";
        } else if (o instanceof DataClass) {
            objectType = "Data class";
        } else if (o instanceof DataMethod) {
            objectType = "Data method";
        } else if (o instanceof MessageSignature) {
            objectType = "Message";
        } else if (o instanceof ProcessClass) {
            objectType = "Process class";
        } else if (o instanceof ProcessMethod) {
            objectType = "Process method";
        } else if (o instanceof Variable
                && o.eContainer().eContainingFeature() == Literals.DATA_CLASS__INSTANCE_VARIABLES) {
            objectType = "Class variable";
        } else if (o instanceof Variable
                && o.eContainer().eContainingFeature() == Literals.DATA_METHOD__PARAMETERS) {
            objectType = "Input parameter";
        } else if (o instanceof Variable
                && o.eContainer().eContainingFeature() == Literals.DATA_METHOD__LOCAL_VARIABLES) {
            objectType = "Local variable";
        } else if (o instanceof Variable
                && o.eContainer().eContainingFeature() == Literals.INSTANTIABLE_CLASS__PARAMETERS) {
            objectType = "Class parameter";
        } else if (o instanceof Variable && o.eContainer().eContainingFeature()
                == Literals.PROCESS_CLASS__INSTANCE_VARIABLES) {
            objectType = "Class variable";
        } else if (o instanceof Variable && o.eContainer().eContainingFeature()
                == Literals.PROCESS_METHOD__INPUT_PARAMETERS) {
            objectType = "Input parameter";
        } else if (o instanceof Variable && o.eContainer().eContainingFeature()
                == Literals.PROCESS_METHOD__OUTPUT_PARAMETERS) {
            objectType = "Output parameter";
        } else if (o instanceof Variable && o.eContainer().eContainingFeature()
                == Literals.PROCESS_METHOD__LOCAL_VARIABLES) {
            objectType = "Local variable";
        } else {
            objectType = o.eClass().getName();
        }

        String label = getLabel(o);
        return objectType + ((label != null) ? " <b>" + label + "</b>" : ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    @Override
    protected String getDocumentation(EObject o) {
        if (o instanceof Annotation) {
            String result = ANNOTATION_DOCS.get(AnnotationType.getValue((Annotation) o));
            if (result != null) {
                return result;
            }
        }
        return super.getDocumentation(o);
    }
}
