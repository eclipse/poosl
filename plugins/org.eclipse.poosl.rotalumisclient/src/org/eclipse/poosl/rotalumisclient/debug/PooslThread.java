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
import java.util.List;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.poosl.generatedxmlclasses.TExecutiontree;
import org.eclipse.poosl.generatedxmlclasses.TInspectType;
import org.eclipse.poosl.generatedxmlclasses.TVariable;

/**
 * The PooslThread.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class PooslThread extends PooslDebugElement implements IThread {
    private final String name;

    private final TInspectType type;

    private PooslStackFrame stackframe;

    private TExecutiontree executiontree;

    private BigInteger activeBreakpointNode;

    private BigInteger globalHandle;

    public PooslThread(PooslDebugTarget target, String name, TInspectType type) {
        super(target);
        this.name = name;
        this.type = type;
    }

    @Override
    public boolean canResume() {
        return (target != null) && target.canResume();
    }

    @Override
    public boolean canSuspend() {
        return (target != null) && target.canSuspend();
    }

    @Override
    public boolean isSuspended() {
        return (target != null) && target.isSuspended();
    }

    @Override
    public void resume() throws DebugException {
        // Clear the stackframes from this thread since it is no longer
        // suspended.
        stackframe = null;
        executiontree = null;
        activeBreakpointNode = null;
        if (isSuspended() && target != null) {
            target.resume();
        }
    }

    @Override
    public void suspend() throws DebugException {
        if (!isSuspended() && target != null) {
            target.suspend();
        }
    }

    public void getRotalumisStackFrames() {
        if ((stackframe == null) && !isTerminated() && isSuspended() && (target != null)) {
            target.getClient().inspectByName(name, type);

        }
    }

    @Override
    public boolean canStepInto() {
        return false;
    }

    @Override
    public boolean canStepOver() {
        return false;
    }

    @Override
    public boolean canStepReturn() {
        return false;
    }

    @Override
    public boolean isStepping() {
        return (target != null) && target.isSuspended();
    }

    @Override
    public void stepInto() throws DebugException {
        // StepInto not supported
    }

    @Override
    public void stepOver() throws DebugException {
        // StepOver not supported
    }

    @Override
    public void stepReturn() throws DebugException {
        // StepReturn not supported
    }

    @Override
    public boolean canTerminate() {
        return (target != null) && target.canTerminate();
    }

    @Override
    public boolean isTerminated() {
        return (target == null) || target.isTerminated();
    }

    @Override
    public void terminate() throws DebugException {
        // Remove the stackframes because they are no longer valid after the
        // thread has been terminated.
        stackframe = null;
        executiontree = null;
        if (target != null && !isTerminated()) {
            target.terminate();
        }

    }

    @Override
    public IStackFrame[] getStackFrames() throws DebugException {
        return new IStackFrame[] { stackframe };
    }

    public IStackFrame getStackFrame() {
        return stackframe;
    }

    @Override
    public boolean hasStackFrames() throws DebugException {
        // Stackframes are being hidden for the Eclipse framework. See the
        // developer documentation for more details
        return false;
    }

    @Override
    public int getPriority() throws DebugException {
        return 0;
    }

    @Override
    public IStackFrame getTopStackFrame() throws DebugException {
        return stackframe;
    }

    @Override
    public String getName() throws DebugException {
        return name;
    }

    @Override
    public IBreakpoint[] getBreakpoints() {
        return new IBreakpoint[0];
    }

    public void addStackFrame(List<TVariable> list) throws DebugException {
        stackframe = new PooslStackFrame(target, this, name + "/stackframe", list, globalHandle); //$NON-NLS-1$
    }

    public void clearStackFrames() {
        stackframe = null;
        executiontree = null;
    }

    public TExecutiontree getExecutiontree() {
        return executiontree;
    }

    public void setExecutiontree(TExecutiontree executiontree) {
        this.executiontree = executiontree;
        if (executiontree != null
                && !this.executiontree.getSequentialOrMethodCallOrParallel().isEmpty()) {
            globalHandle = this.executiontree.getSequentialOrMethodCallOrParallel().get(0)
                    .getValue().getGlobal();
        }
        if (executiontree != null) {
            target.getPooslSourceMap().checkExecutionTreeForExternalMapping(executiontree);
        }
    }

    public BigInteger getActiveBreakpointNode() {
        return activeBreakpointNode;
    }

    public void setActiveBreakpointNode(BigInteger node) {
        this.activeBreakpointNode = node;
    }

    public void setOriginalVariables() {
        if (stackframe != null) {
            stackframe.revertToOriginalVariables();
        }
    }
}
