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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Poosl</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.poosl.Poosl#getImports <em>Imports</em>}</li>
 * <li>{@link org.eclipse.poosl.Poosl#getDataClasses <em>Data Classes</em>}</li>
 * <li>{@link org.eclipse.poosl.Poosl#getProcessClasses <em>Process Classes</em>}</li>
 * <li>{@link org.eclipse.poosl.Poosl#getClusterClasses <em>Cluster Classes</em>}</li>
 * <li>{@link org.eclipse.poosl.Poosl#getImportLibs <em>Import Libs</em>}</li>
 * </ul>
 *
 * @see org.eclipse.poosl.PooslPackage#getPoosl()
 * @model
 * @generated
 */
public interface Poosl extends EObject {
    /**
     * Returns the value of the '<em><b>Imports</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.poosl.Import}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Imports</em>' containment reference list.
     * @see org.eclipse.poosl.PooslPackage#getPoosl_Imports()
     * @model containment="true"
     * @generated
     */
    EList<Import> getImports();

    /**
     * Returns the value of the '<em><b>Data Classes</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.poosl.DataClass}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Data Classes</em>' containment reference list.
     * @see org.eclipse.poosl.PooslPackage#getPoosl_DataClasses()
     * @model containment="true"
     * @generated
     */
    EList<DataClass> getDataClasses();

    /**
     * Returns the value of the '<em><b>Process Classes</b></em>' containment reference list. The list contents are of
     * type {@link org.eclipse.poosl.ProcessClass}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Process Classes</em>' containment reference list.
     * @see org.eclipse.poosl.PooslPackage#getPoosl_ProcessClasses()
     * @model containment="true"
     * @generated
     */
    EList<ProcessClass> getProcessClasses();

    /**
     * Returns the value of the '<em><b>Cluster Classes</b></em>' containment reference list. The list contents are of
     * type {@link org.eclipse.poosl.ClusterClass}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Cluster Classes</em>' containment reference list.
     * @see org.eclipse.poosl.PooslPackage#getPoosl_ClusterClasses()
     * @model containment="true"
     * @generated
     */
    EList<ClusterClass> getClusterClasses();

    /**
     * Returns the value of the '<em><b>Import Libs</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.poosl.Import}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Import Libs</em>' containment reference list.
     * @see org.eclipse.poosl.PooslPackage#getPoosl_ImportLibs()
     * @model containment="true"
     * @generated
     */
    EList<Import> getImportLibs();

} // Poosl
