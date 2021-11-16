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
package org.eclipse.poosl.xtext.helpers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.poosl.ClusterClass;
import org.eclipse.poosl.DataClass;
import org.eclipse.poosl.DataMethod;
import org.eclipse.poosl.Declaration;
import org.eclipse.poosl.ProcessClass;
import org.eclipse.poosl.ProcessMethod;
import org.eclipse.poosl.Variable;
import org.eclipse.poosl.xtext.custom.PooslCache;
import org.eclipse.poosl.xtext.descriptions.PooslDeclarationDescription;
import org.eclipse.poosl.xtext.scoping.PooslScopeProvider;
import org.eclipse.xtext.resource.IEObjectDescription;

/**
 * The PooslVariableTypeHelper.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class PooslVariableTypeHelper {

    private PooslVariableTypeHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static String getVariableType(EObject context, String name) {
        if (context != null && name != null) {
            EObject expressionContainer = getContainer(context);

            if (expressionContainer instanceof DataMethod) {
                DataMethod dMethod = (DataMethod) expressionContainer;
                return getTypeDataMethodParameterOrVariable(dMethod, name);
            } else if (expressionContainer instanceof ProcessMethod) {
                ProcessMethod pMethod = (ProcessMethod) expressionContainer;
                return getTypeProcessMethodParameterOrVariable(pMethod, name);
            } else if (expressionContainer instanceof ProcessClass) {
                ProcessClass pClass = (ProcessClass) expressionContainer;
                return getTypeProcessClassParameterOrVariable(pClass, name);
            } else if (expressionContainer instanceof DataClass) {
                DataClass dClass = (DataClass) expressionContainer;
                return getTypeDataClassVariable(dClass, name);
            } else if (expressionContainer instanceof ClusterClass) {
                ClusterClass cClass = (ClusterClass) expressionContainer;
                return getTypeClusterClassParameter(cClass, name);
            }
        }
        return null;
    }

    private static EObject getContainer(EObject originalObject) {
        EObject object = originalObject;
        while (object != null && !(object instanceof DataClass || object instanceof ProcessClass
                || object instanceof ClusterClass || object instanceof DataMethod
                || object instanceof ProcessMethod)) {
            object = object.eContainer();
        }
        return object;
    }

    // --- Parameters and variables -------

    private static String getTypeDataClassVariable(DataClass dClass, String name) {
        String superClass = HelperFunctions.getCorrectedDataClassExtendsAsString(dClass);
        if (superClass != null) {
            IEObjectDescription declarationDescr = PooslCache.get(dClass.eResource())
                    .getDataClassVariables(superClass).get(name);
            if (declarationDescr != null) {
                return PooslDeclarationDescription.getType(declarationDescr);
            }
        }
        Variable var = getVariableFromIterable(PooslScopeProvider.getLocalScopeVariables(dClass),
                name);
        if (var != null) {
            return ((Declaration) var.eContainer()).getType();
        }
        return null;
    }

    private static String getTypeDataMethodParameterOrVariable(DataMethod dMethod, String name) {
        DataClass dClass = (DataClass) dMethod.eContainer();
        String result = getTypeDataClassVariable(dClass, name);
        if (result == null) {
            Variable var = getVariableFromIterable(
                    PooslScopeProvider.getLocalScopeVariables(dMethod), name);
            if (var != null) {
                return ((Declaration) var.eContainer()).getType();
            }
        }
        return result;
    }

    private static String getTypeClusterClassParameter(ClusterClass cClass, String name) {
        Variable var = getVariableFromIterable(PooslScopeProvider.getLocalScopeParameters(cClass),
                name);
        if (var != null) {
            return ((Declaration) var.eContainer()).getType();
        }
        return null;
    }

    private static String getTypeProcessClassParameterOrVariable(ProcessClass pClass, String name) {

        if (pClass.getSuperClass() != null) {
            IEObjectDescription descr = PooslCache.get(pClass.eResource())
                    .getProcessClassParametersAndVariables(pClass.getSuperClass()).get(name);
            if (descr != null) {
                return PooslDeclarationDescription.getType(descr);
            }
        }

        Variable var = getVariableFromIterable(PooslScopeProvider.getLocalScopeVariables(pClass),
                name);
        if (var != null) {
            return ((Declaration) var.eContainer()).getType();
        }
        return null;
    }

    private static String getTypeProcessMethodParameterOrVariable(
            ProcessMethod pMethod, String name) {
        ProcessClass pClass = (ProcessClass) pMethod.eContainer();
        String result = getTypeProcessClassParameterOrVariable(pClass, name);
        if (result == null) {
            Variable var = getVariableFromIterable(
                    PooslScopeProvider.getLocalScopeVariables(pMethod), name);
            if (var != null) {
                return ((Declaration) var.eContainer()).getType();
            }
        }
        return result;
    }

    private static Variable getVariableFromIterable(Iterable<Variable> variables, String name) {
        for (Variable variable : variables) {
            if (variable.getName().equals(name)) {
                return variable;
            }
        }
        return null;
    }
}
