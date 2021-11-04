/* ************************************************************************** *
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
 * ************************************************************************* */
package org.eclipse.poosl.xtext.annotation;

import org.eclipse.poosl.Annotation;

// Annotation text has no l10n: used as technical tokens.
public enum AnnotationType {

    TEST("Test"), //$NON-NLS-1$
    SKIP("Skip"), //$NON-NLS-1$
    ERROR("Error"), //$NON-NLS-1$
    INIT("Init"), //$NON-NLS-1$
    SUPPRESSWARNINGS("SuppressWarnings"); //$NON-NLS-1$

    private static final AnnotationType[] VALUES = AnnotationType.values();

    private final String text;

    AnnotationType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public boolean isApplicable(Annotation annotation) {
        return name().equalsIgnoreCase(annotation.getName());
    }

    /**
     * Returns the type of annotations.
     * <p>
     * If none is applicable, return null
     * </p>
     *
     * @param annotation
     *     to
     * @return type or null
     */
    public static AnnotationType getValue(Annotation annotation) {
        for (AnnotationType c : VALUES) {
            if (c.isApplicable(annotation)) {
                return c;
            }
        }
        return null;
    }
}
