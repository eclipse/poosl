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
package org.eclipse.poosl.rotalumisclient.debug;

import java.math.BigInteger;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.poosl.generatedxmlclasses.TVariable;
import org.eclipse.poosl.xtext.helpers.HelperFunctions;

/**
 * The PooslValue.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslValue extends PooslDebugElement implements IValue {
    private final TVariable var;

    private IVariable[] vars = new PooslVariable[0];

    private final BigInteger listHandle;

    public PooslValue(PooslDebugTarget target, TVariable tVariable, BigInteger listHandle) {
        super(target);
        this.var = tVariable;
        this.listHandle = listHandle;
    }

    public void setVariables(IVariable[] pVars) {
        this.vars = pVars;
    }

    @Override
    public String getReferenceTypeName() throws DebugException {
        return var.getType();
    }

    @Override
    public String getValueString() throws DebugException {
        String typeName = var.getType();
        if (HelperFunctions.PRIMITIVE_DATA_CLASSES.contains(typeName)) {
            return var.getLiteral();
        } else {
            return var.getLiteral() + " (id=" + var.getObject() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    @Override
    public boolean isAllocated() throws DebugException {
        return true;
    }

    @Override
    public IVariable[] getVariables() throws DebugException {
        return vars;
    }

    @Override
    public boolean hasVariables() throws DebugException {
        return vars.length > 0;
    }

    public BigInteger getSubHandle() {
        if (var.getVariables() == null) {
            return null;
        } else {
            return (getLocalHandle().compareTo(BigInteger.ZERO) == 0) ? getGlobalHandle() : getLocalHandle();
        }
    }

    public BigInteger getObject() {
        if (var != null) {
            return var.getObject();
        }
        return null;
    }

    public void setLiteral(String value) {
        var.setLiteral(value);
    }

    public String getLiteral() {
        if (var != null) {
            String literal = var.getLiteral();
            if (literal != null) {
                return literal;
            }
        }
        return "null"; //$NON-NLS-1$
    }

    public BigInteger getHandle() {
        return var.getHandle();
    }

    public BigInteger getListHandle() {
        return listHandle;
    }

    private BigInteger getLocalHandle() {
        return var.getVariables().getLocal();
    }

    private BigInteger getGlobalHandle() {
        return var.getVariables().getGlobal();
    }

    @Override
    public boolean canTerminate() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public void terminate() throws DebugException {
        // do nothing
    }
}
