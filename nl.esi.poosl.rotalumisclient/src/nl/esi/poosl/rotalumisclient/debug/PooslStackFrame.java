package nl.esi.poosl.rotalumisclient.debug;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IRegisterGroup;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;

import nl.esi.poosl.generatedxmlclasses.TVariable;
import nl.esi.poosl.rotalumisclient.sourcemapping.PooslSourceMapping;

public class PooslStackFrame extends PooslDebugElement implements IStackFrame {
	private static final Logger LOGGER = Logger.getLogger(PooslStackFrame.class.getName());

	private final String name;
	private final PooslThread thread;
	private final PooslVariable[] originalVariables;
	private PooslVariable[] variables;
	private PooslSourceMapping sourceMapping;
	private int lineNumber;
	private int charStart;
	private int charEnd;

	public PooslStackFrame(PooslDebugTarget target, PooslThread thread, String name, List<TVariable> vars,
			BigInteger globalHandle) throws DebugException {
		super(target);
		this.thread = thread;
		this.name = name;
		variables = new PooslVariable[vars.size()];
		try {
			for (int i = 0; i < vars.size(); i++) {
				variables[i] = new PooslVariable(target, vars.get(i).getName());
				variables[i].setValue(new PooslValue(target, vars.get(i), globalHandle));
			}
		} catch (DebugException e) {
			LOGGER.log(Level.WARNING, "Could not initialize stackframe.", e);
		}
		originalVariables = variables;
		for (PooslVariable pooslVariable : variables) {
			pooslVariable.getSubVariables();
		}
	}

	@Override
	public boolean canStepInto() {
		return getThread().canStepInto();
	}

	@Override
	public boolean canStepOver() {
		return getThread().canStepOver();
	}

	@Override
	public boolean canStepReturn() {
		return getThread().canStepReturn();
	}

	@Override
	public boolean isStepping() {
		return getThread().isStepping();
	}

	@Override
	public void stepInto() throws DebugException {
		getThread().stepInto();
	}

	@Override
	public void stepOver() throws DebugException {
		getThread().stepOver();
	}

	@Override
	public void stepReturn() throws DebugException {
		getThread().stepReturn();
	}

	@Override
	public boolean canResume() {
		return getThread().canResume();
	}

	@Override
	public boolean canSuspend() {
		return getThread().canSuspend();
	}

	@Override
	public boolean isSuspended() {
		return getThread().isSuspended();
	}

	@Override
	public void resume() throws DebugException {
		getThread().resume();
	}

	@Override
	public void suspend() throws DebugException {
		getThread().suspend();
	}

	@Override
	public boolean canTerminate() {
		return getThread().canTerminate();
	}

	@Override
	public boolean isTerminated() {
		return getThread().isTerminated();
	}

	@Override
	public void terminate() throws DebugException {
		getThread().terminate();
	}

	@Override
	public IThread getThread() {
		return thread;
	}

	@Override
	public IVariable[] getVariables() throws DebugException {
		return variables;
	}

	@Override
	public boolean hasVariables() throws DebugException {
		return variables.length > 0;
	}

	@Override
	public int getLineNumber() throws DebugException {
		return lineNumber;
	}

	@Override
	public int getCharStart() throws DebugException {
		return charStart;
	}

	@Override
	public int getCharEnd() throws DebugException {
		return charEnd;
	}

	@Override
	public String getName() throws DebugException {
		return name;
	}

	@Override
	public IRegisterGroup[] getRegisterGroups() throws DebugException {
		return new IRegisterGroup[0];
	}

	@Override
	public boolean hasRegisterGroups() throws DebugException {
		return false;
	}

	public PooslSourceMapping getSourceMapping() {
		return sourceMapping;
	}

	public void setSourceMapping(PooslSourceMapping sourceMapping) {
		this.sourceMapping = sourceMapping;
		lineNumber = sourceMapping.getLineNumber();
		charStart = sourceMapping.getTotalOffset();
		charEnd = sourceMapping.getTotalEndOffset();
	}

	public void revertToOriginalVariables() {
		variables = originalVariables;
	}

	public void addLocalVariables(List<TVariable> vars, BigInteger listHandle) throws DebugException {
		PooslVariable[] localVariables = new PooslVariable[vars.size()];
		for (int i = 0; i < vars.size(); i++) {
			localVariables[i] = new PooslVariable(target, vars.get(i), listHandle);
		}

		variables = (PooslVariable[]) ArrayUtils.addAll(originalVariables, localVariables);

		for (PooslVariable pooslVariable : localVariables) {
			pooslVariable.getSubVariables();
		}
	}

	public boolean updateSubVariables(PooslDebugTarget target, BigInteger listHandle, List<TVariable> variableList)
			throws DebugException {
		boolean updated = false;
		for (PooslValue pooslValue : getSubValuesByListHandle(listHandle)) {
			if (pooslValue.getVariables().length == 0) {
				IVariable[] vars = new IVariable[variableList.size()];
				for (int i = 0; i < variableList.size(); i++) {
					vars[i] = new PooslVariable(target, variableList.get(i), listHandle);
				}
				pooslValue.setVariables(vars);
				updated = true;
			}
		}
		return updated;
	}

	public boolean updateVariable(PooslDebugTarget target, BigInteger objectHandle, List<TVariable> variableList)
			throws DebugException {
		boolean updated = false;
		for (PooslValue pooslValue : getValuesByObjectHandle(objectHandle)) {
			if (pooslValue.getVariables().length == 0) {
				// Disable the change value of subvariables by giving it no listhandle
				// TODO to be removed after Rotalumis fix
				BigInteger listHandle = null;// pooslValue.getListHandle();
				IVariable[] vars = new IVariable[variableList.size()];
				for (int i = 0; i < variableList.size(); i++) {
					vars[i] = new PooslVariable(target, variableList.get(i), listHandle);
				}
				pooslValue.setVariables(vars);
				updated = true;
			}
		}
		return updated;
	}

	private List<PooslValue> getSubValuesByListHandle(BigInteger listHandle) throws DebugException {
		List<PooslValue> values = new ArrayList<>();
		for (IVariable variable : getVariables()) {
			if (variable.getValue() instanceof PooslValue) {
				PooslValue value = (PooslValue) variable.getValue();
				BigInteger handle = value.getSubHandle();
				if (handle != null && listHandle.compareTo(handle) == 0) {
					values.add(value);
				}
				if (value.hasVariables()) {
					values.addAll(getSubValuesByListHandle(listHandle, value));
				}
			}
		}
		return values;
	}

	private static List<PooslValue> getSubValuesByListHandle(BigInteger listHandle, PooslValue value)
			throws DebugException {
		List<PooslValue> values = new ArrayList<>();
		for (IVariable variable : value.getVariables()) {
			if (variable.getValue() instanceof PooslValue) {
				PooslValue subValue = (PooslValue) variable.getValue();
				BigInteger subHandle = subValue.getSubHandle();
				if (subHandle != null && listHandle.compareTo(subHandle) == 0) {
					values.add(subValue);
				}
				if (subValue.hasVariables()) {
					values.addAll(getSubValuesByListHandle(listHandle, subValue));
				}
			}
		}
		return values;
	}

	private List<PooslValue> getValuesByObjectHandle(BigInteger objectHandle) throws DebugException {
		List<PooslValue> values = new ArrayList<>();
		for (IVariable variable : getVariables()) {
			if (variable.getValue() instanceof PooslValue) {
				PooslValue value = (PooslValue) variable.getValue();
				BigInteger handle = value.getObject();
				if (handle != null && objectHandle.compareTo(handle) == 0) {
					values.add(value);
				}
				if (value.hasVariables()) {
					values.addAll(getValuesByObjectHandle(objectHandle, value));
				}
			}
		}
		return values;
	}

	private static List<PooslValue> getValuesByObjectHandle(BigInteger objectHandle, PooslValue value)
			throws DebugException {
		List<PooslValue> values = new ArrayList<>();
		for (IVariable variable : value.getVariables()) {
			if (variable.getValue() instanceof PooslValue) {
				PooslValue subValue = (PooslValue) variable.getValue();
				BigInteger subHandle = subValue.getObject();
				if (subHandle != null && objectHandle.compareTo(subHandle) == 0) {
					values.add(subValue);
				}
				if (subValue.hasVariables()) {
					values.addAll(getValuesByObjectHandle(objectHandle, subValue));
				}
			}
		}
		return values;
	}
}
