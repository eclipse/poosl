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
package org.eclipse.poosl.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.poosl.PooslPackage;
import org.eclipse.poosl.SelfExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Self Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class SelfExpressionImpl extends ExpressionImpl implements SelfExpression
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SelfExpressionImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PooslPackage.Literals.SELF_EXPRESSION;
	}

} //SelfExpressionImpl
