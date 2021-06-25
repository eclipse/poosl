package nl.esi.poosl.rotalumisclient.debug;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.debug.internal.ui.model.elements.VariableLabelProvider;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IElementLabelProvider;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IPresentationContext;

import nl.esi.poosl.generatedxmlclasses.TInspectType;
import nl.esi.poosl.generatedxmlclasses.TVariable;

@SuppressWarnings("restriction")
public class PooslVariable extends PooslDebugElement implements IVariable {
	private final String name;
	private PooslValue value;
	private String newExpression;
	private boolean isChanged = false;

	private static final Logger LOGGER = Logger.getLogger(PooslVariable.class.getName());

	public PooslVariable(PooslDebugTarget target, String name) {
		super(target);
		this.name = name;
	}

	public PooslVariable(PooslDebugTarget target, TVariable tVariable, BigInteger listHandle) {
		super(target);
		this.name = tVariable.getName();
		this.value = new PooslValue(target, tVariable, listHandle);
	}

	@Override
	public <T> T getAdapter(Class<T> adapter) {
		if (adapter == IElementLabelProvider.class) {
			return adapter.cast(new PooslDebugElementLabelProvider());
		}
		return super.getAdapter(adapter);
	}

	class PooslDebugElementLabelProvider extends VariableLabelProvider {
		@Override
		protected String getValueText(IVariable variable, IValue value, IPresentationContext context)
				throws CoreException {
			return value.getValueString();
		}
	}

	@Override
	public void setValue(String expression) throws DebugException {
		String trimmedExpression = expression.trim();
		if (trimmedExpression.startsWith("\"")) {
			// Remove (id=...) from string value
			int index = trimmedExpression.lastIndexOf('\"');
			trimmedExpression = trimmedExpression.substring(0, index + 1);
		}

		newExpression = trimmedExpression;
		((PooslDebugTarget) getDebugTarget()).setVariable(this, trimmedExpression);
	}

	public void verifiedNewValue() {
		if (newExpression != null) {
			value.setLiteral(newExpression);
			isChanged = true;
			newExpression = null;
		}
	}

	@Override
	public void setValue(IValue value) throws DebugException {
		this.value = (PooslValue) value;
	}

	public BigInteger getListHandle() {
		return value.getListHandle();
	}

	@Override
	public boolean supportsValueModification() {
		return getListHandle() != null;
	}

	@Override
	public boolean verifyValue(String expression) throws DebugException {
		return true;
	}

	@Override
	public boolean verifyValue(IValue value) throws DebugException {
		return false;
	}

	@Override
	public IValue getValue() throws DebugException {
		return value;
	}

	@Override
	public String getName() throws DebugException {
		return name;
	}

	@Override
	public String getReferenceTypeName() throws DebugException {
		return "reftype: " + value.toString();
	}

	@Override
	public boolean hasValueChanged() throws DebugException {
		return isChanged;
	}

	public void getSubVariables() throws DebugException {
		if (!target.isTerminated() && !value.hasVariables()) {
			BigInteger handle = value.getSubHandle();
			if (handle != null) {
				if (handle.compareTo(BigInteger.ZERO) != 0) {
					target.getClient().inspectByHandle(handle, TInspectType.VARIABLE_CONTEXT);
				}
			} else {
				// Needed for at least Rotalumis 23-11-2016
				if (value.getObject() != null) {
					target.getClient().inspectByHandle(value.getObject(), TInspectType.DATA);
				} else {
					LOGGER.log(Level.SEVERE, "Could not perform a request for variable" + value.getLiteral());
				}
			}
		}
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
