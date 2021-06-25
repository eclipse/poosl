package nl.esi.poosl.xtext.ui.labeling;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

import com.google.inject.Inject;

import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.DataMethodBinaryOperator;
import nl.esi.poosl.DataMethodNamed;
import nl.esi.poosl.DataMethodUnaryOperator;
import nl.esi.poosl.Instance;
import nl.esi.poosl.InstantiableClass;
import nl.esi.poosl.MessageSignature;
import nl.esi.poosl.Poosl;
import nl.esi.poosl.Port;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.Variable;
import nl.esi.poosl.xtext.custom.FormattingHelper;

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
			return iClass.getName() + "." + port.getName();
		}
		return port.getName();
	}

	// --- Instance -------

	Object text(Instance instance) {
		ClusterClass cClass = (ClusterClass) instance.eContainer();
		if (cClass.getName() != null) {
			return cClass.getName() + "." + instance.getName();
		}
		return instance.getName();

	}
}
