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
package org.eclipse.poosl.xtext.ui.labeling;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.poosl.ClusterClass;
import org.eclipse.poosl.DataMethodBinaryOperator;
import org.eclipse.poosl.DataMethodNamed;
import org.eclipse.poosl.DataMethodUnaryOperator;
import org.eclipse.poosl.Instance;
import org.eclipse.poosl.InstantiableClass;
import org.eclipse.poosl.MessageSignature;
import org.eclipse.poosl.Poosl;
import org.eclipse.poosl.Port;
import org.eclipse.poosl.ProcessMethod;
import org.eclipse.poosl.Variable;
import org.eclipse.poosl.xtext.custom.FormattingHelper;

import com.google.inject.Inject;

/**
 * The PooslLabelProvider.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslLabelProvider extends org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider {

    @Inject
    public PooslLabelProvider(AdapterFactoryLabelProvider delegate) {
        super(delegate);
    }

    Object text(Poosl poosl) {
        return poosl.eResource().getURI().trimFragment().toString();
    }

    // --- Message signatures -------

    Object text(MessageSignature msgSig) {
        StringBuilder buf = new StringBuilder();
        FormattingHelper.formatMessageSignature(buf, msgSig, true);
        return buf.toString();
    }

    // --- Variables -------

    Object text(Variable variable) {
        StringBuilder buf = new StringBuilder();
        FormattingHelper.formatVariable(buf, variable, true);
        return buf.toString();
    }

    // --- Data methods -------

    Object text(DataMethodUnaryOperator dMethod) {
        StringBuilder buf = new StringBuilder();
        FormattingHelper.formatDataMethod(buf, dMethod, true);
        return buf.toString();
    }

    Object text(DataMethodBinaryOperator dMethod) {
        StringBuilder buf = new StringBuilder();
        FormattingHelper.formatDataMethod(buf, dMethod, true);
        return buf.toString();
    }

    Object text(DataMethodNamed dMethod) {
        StringBuilder buf = new StringBuilder();
        FormattingHelper.formatDataMethod(buf, dMethod, true);
        return buf.toString();
    }

    // --- Process methods -------

    Object text(ProcessMethod pMethod) {
        StringBuilder buf = new StringBuilder();
        FormattingHelper.formatProcessMethod(buf, pMethod, true);
        return buf.toString();
    }

    // --- Instantiable class -------

    Object text(InstantiableClass iClass) {
        StringBuilder buf = new StringBuilder();
        FormattingHelper.formatInstantiableClass(buf, iClass, true);
        return buf.toString();
    }

    // --- Port -------

    Object text(Port port) {
        InstantiableClass iClass = (InstantiableClass) port.eContainer();
        if (iClass.getName() != null) {
            return iClass.getName() + "." + port.getName(); //$NON-NLS-1$
        }
        return port.getName();
    }

    // --- Instance -------

    Object text(Instance instance) {
        ClusterClass cClass = (ClusterClass) instance.eContainer();
        if (cClass.getName() != null) {
            return cClass.getName() + "." + instance.getName(); //$NON-NLS-1$
        }
        return instance.getName();

    }
}
