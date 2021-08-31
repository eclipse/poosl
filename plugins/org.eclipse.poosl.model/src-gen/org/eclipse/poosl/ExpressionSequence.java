/**
 * Copyright (c) 2021 TNO/ESI
 *  This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License 2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 * 
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     TNO/ESI - initial API and implementation
 *     Obeo - refactoring
 */
package org.eclipse.poosl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Expression Sequence</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.poosl.ExpressionSequence#getExpressions <em>Expressions</em>}</li>
 * </ul>
 *
 * @see org.eclipse.poosl.PooslPackage#getExpressionSequence()
 * @model
 * @generated
 */
public interface ExpressionSequence extends Expression {
    /**
     * Returns the value of the '<em><b>Expressions</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.poosl.Expression}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Expressions</em>' containment reference list.
     * @see org.eclipse.poosl.PooslPackage#getExpressionSequence_Expressions()
     * @model containment="true"
     * @generated
     */
    EList<Expression> getExpressions();

} // ExpressionSequence
