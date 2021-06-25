package nl.esi.poosl.rotalumisclient.debug;

import java.math.BigInteger;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

import nl.esi.poosl.generatedxmlclasses.TVariable;
import nl.esi.poosl.xtext.helpers.HelperFunctions;

public class PooslValue extends PooslDebugElement implements IValue {
	private final TVariable var;
	private IVariable[] vars = new PooslVariable[0];
	private final BigInteger listHandle;

	public PooslValue(PooslDebugTarget target, TVariable tVariable, BigInteger listHandle) {
		super(target);
		this.var = tVariable;
		this.listHandle = listHandle;
	}

	public void setVariables(IVariable[] vars) {
		this.vars = vars;
	}

	@Override
	public String getReferenceTypeName() throws DebugException {
		return var.getType();
	}

	@Override
	public String getValueString() throws DebugException {
		String typeName = var.getType();
		if (HelperFunctions.primitiveDataClasses.contains(typeName)) {
			return var.getLiteral();
		} else {
			return var.getLiteral() + " (id=" + var.getObject() + ")";
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
		return "null";
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
