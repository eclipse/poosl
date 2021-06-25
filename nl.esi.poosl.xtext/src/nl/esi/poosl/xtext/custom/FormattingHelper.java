package nl.esi.poosl.xtext.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.DataMethodBinaryOperator;
import nl.esi.poosl.DataMethodNamed;
import nl.esi.poosl.DataMethodUnaryOperator;
import nl.esi.poosl.Declaration;
import nl.esi.poosl.Instance;
import nl.esi.poosl.InstantiableClass;
import nl.esi.poosl.MessageParameter;
import nl.esi.poosl.MessageSignature;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.Port;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.Variable;
import nl.esi.poosl.xtext.descriptions.PooslDataMethodDescription;
import nl.esi.poosl.xtext.descriptions.PooslDeclarationDescription;
import nl.esi.poosl.xtext.descriptions.PooslProcessMethodDescription;
import nl.esi.poosl.xtext.helpers.HelperFunctions;

public final class FormattingHelper {
	private static final String SEPARATOR_CLASS_METHOD = ".";
	private static final String PREFIX_PORTS = " with ports: ";
	private static final String PREFIX_PARAMETERS = "(";
	private static final String SEPARATOR_VARIABLE = ", ";
	private static final String POSTFIX_PARAMETERS = ")";
	private static final String PREFIX_TYPE = ": ";

	private FormattingHelper() {
		throw new IllegalStateException("Utility class");
	}

	public static void formatTypes(StringBuilder buf, List<String> types) {
		buf.append(PREFIX_PARAMETERS);

		boolean isFirstVariable = true;
		for (String type : types) {
			if (isFirstVariable) {
				isFirstVariable = false;
			} else {
				buf.append(SEPARATOR_VARIABLE);
			}

			buf.append(type);
		}
		buf.append(POSTFIX_PARAMETERS);
	}

	public static String[] unformatTypeNames(String typesString) {
		String vars = typesString.substring(1, typesString.length() - 1);
		return vars.split(SEPARATOR_VARIABLE);
	}

	public static void formatDeclarations(StringBuilder buf, List<Declaration> declarations) {
		formatDeclarations(buf, declarations, true);
	}

	public static void formatDeclarations(StringBuilder buf, List<Declaration> declarations, boolean useNode) {
		buf.append(PREFIX_PARAMETERS);
		for (Declaration declaration : declarations) {
			formatDeclaration(buf, declaration == declarations.get(0), declaration, useNode);
		}
		buf.append(POSTFIX_PARAMETERS);
	}

	public static void formatDeclaration(StringBuilder buf, boolean isFirstVariable, Declaration declaration) {
		formatDeclaration(buf, isFirstVariable, declaration, true);
	}

	public static void formatDeclaration(StringBuilder buf, boolean isFirstVariable, Declaration declaration,
			boolean useNodes) {
		String type = null;
		if (useNodes) {
			// still needed?
			List<INode> nodes = NodeModelUtils.findNodesForFeature(declaration, Literals.DECLARATION__TYPE);
			type = (nodes.isEmpty()) ? null : nodes.get(0).getText();
		} else {
			if (declaration.getType() != null) {
				type = declaration.getType();
			}
		}
		boolean useFirst = isFirstVariable;
		for (Variable variable : declaration.getVariables()) {
			if (useFirst) {
				useFirst = false;
			} else {
				buf.append(SEPARATOR_VARIABLE);
			}

			if (variable.getName() != null) {
				buf.append(variable.getName());
			}

			buf.append(PREFIX_TYPE);

			if (type != null) {
				buf.append(type);
			}
		}
	}

	public static void formatVariable(StringBuilder buf, Variable variable, boolean includeClass) {
		if (includeClass) {
			formatClassName(buf, variable);
		}

		if (variable.getName() != null) {
			buf.append(variable.getName());
		}

		buf.append(PREFIX_TYPE);
		String type = ((Declaration) variable.eContainer()).getType();
		if (type != null) {
			buf.append(type);
		}
	}

	private static void formatClassName(StringBuilder buf, Variable variable) {
		EObject obj = variable;
		while (!(obj instanceof DataClass) && !(obj instanceof DataMethod) && !(obj instanceof InstantiableClass)
				&& !(obj instanceof ProcessMethod)) {
			obj = obj.eContainer();
		}
		if (obj instanceof DataClass) {
			appendClassName(buf, ((DataClass) obj).getName());
		} else if (obj instanceof InstantiableClass) {
			appendClassName(buf, ((InstantiableClass) obj).getName());
		}
	}

	public static void formatDataMethod(StringBuilder buf, DataMethod dMethod, boolean includeClass) {
		formatDataMethod(buf, dMethod, includeClass, true);
	}

	public static void formatDataMethod(StringBuilder buf, IEObjectDescription dMethod, boolean includeClass) {
		if (includeClass)
			appendClassName(buf, PooslDataMethodDescription.getClassName(dMethod));
		buf.append(HelperFunctions.getName(dMethod));
		buf.append(PooslDataMethodDescription.getParameters(dMethod));
		String returnType = PooslDataMethodDescription.getReturnType(dMethod);
		if (returnType != null) {
			buf.append(PREFIX_TYPE);
			buf.append(returnType);
		}
	}

	// useNode is set to false when called from sirius
	public static void formatDataMethod(StringBuilder buf, DataMethod dMethod, boolean includeClass, boolean useNode) {
		if (includeClass)
			appendClassName(buf, ((DataClass) dMethod.eContainer()).getName());
		formatDataName(buf, dMethod);
		formatDeclarations(buf, dMethod.getParameters(), useNode);
		buf.append(PREFIX_TYPE);
		formatDataReturnType(buf, dMethod, useNode);
	}

	private static void formatDataReturnType(StringBuilder buf, DataMethod dMethod, boolean useNode) {
		String type = null;
		if (useNode) {
			List<INode> nodes = NodeModelUtils.findNodesForFeature(dMethod, Literals.DATA_METHOD__RETURN_TYPE);
			type = (nodes.isEmpty()) ? null : nodes.get(0).getText();
		} else {
			if (dMethod.getReturnType() != null) {
				type = dMethod.getReturnType();
			}
		}

		if (type != null) {
			buf.append(type);
		}
	}

	private static void formatDataName(StringBuilder buf, DataMethod dMethod) {
		if (dMethod instanceof DataMethodNamed) {
			DataMethodNamed named = (DataMethodNamed) dMethod;
			if (named.getName() != null) {
				buf.append(named.getName());
			}
		} else if (dMethod instanceof DataMethodUnaryOperator) {
			DataMethodUnaryOperator unary = (DataMethodUnaryOperator) dMethod;
			if (unary.getName() != null) {
				buf.append(unary.getName());
			}
		} else if (dMethod instanceof DataMethodBinaryOperator) {
			DataMethodBinaryOperator binary = (DataMethodBinaryOperator) dMethod;
			if (binary.getName() != null) {
				buf.append(binary.getName());
			}
		}
	}

	public static void formatProcessMethod(StringBuilder buf, IEObjectDescription descr) {
		buf.append(HelperFunctions.getName(descr));
		String formattedInputParameters = PooslProcessMethodDescription.getFormattedInputParameters(descr);
		String formattedOutputParameters = PooslProcessMethodDescription.getFormattedOutputParameters(descr);
		if (formattedInputParameters != null && formattedOutputParameters != null) {
			buf.append(formattedInputParameters);
			buf.append(formattedOutputParameters);
		}
	}

	public static void formatProcessMethod(StringBuilder buf, ProcessMethod pMethod, boolean includeClass) {
		formatProcessMethod(buf, pMethod, includeClass, true);
	}

	public static void formatProcessMethod(StringBuilder buf, ProcessMethod pMethod, boolean includeClass,
			boolean useNode) {
		if (includeClass)
			appendClassName(buf, ((ProcessClass) pMethod.eContainer()).getName());
		if (pMethod.getName() != null) {
			buf.append(pMethod.getName());
		}
		formatDeclarations(buf, pMethod.getInputParameters(), useNode);
		formatDeclarations(buf, pMethod.getOutputParameters(), useNode);
	}

	public static void formatInstantiableClass(StringBuilder buf, InstantiableClass iClass, boolean includePorts) {
		if (iClass != null) {
			boolean hasName = iClass.getName() != null;
			if (hasName) {
				buf.append(iClass.getName());

				Map<String, IEObjectDescription> params = PooslCache.get(iClass.eResource())
						.getInstantiableClassParameters(iClass.getName());
				formatDeclarations(buf, params);
			}

			if (includePorts) {
				Iterable<String> ports = (hasName)
						? PooslCache.get(iClass.eResource()).getInstantiableClassPorts(iClass.getName()).keySet()
						: Iterables.transform(iClass.getPorts(), new Function<Port, String>() {
							@Override
							public String apply(Port port) {
								return port.getName();
							}
						});
				if (ports.iterator().hasNext()) {
					buf.append(PREFIX_PORTS);
					boolean isFirstPort = true;
					for (String port : ports) {
						if (isFirstPort) {
							isFirstPort = false;
						} else {
							buf.append(SEPARATOR_VARIABLE);
						}

						if (port != null) {
							buf.append(port);
						}
					}
				}
			}
		}
	}

	public static void formatInstance(StringBuilder buf, Instance instance) {
		if (instance.getName() != null) {
			buf.append(instance.getName());
		}
		buf.append(PREFIX_TYPE);
		if (instance.getClassDefinition() != null) {
			buf.append(instance.getClassDefinition());
		}
	}

	public static void formatMessageSignature(StringBuilder buf, MessageSignature msgSig, boolean appendClassName) {
		if (appendClassName) {
			appendClassName(buf, ((ProcessClass) msgSig.eContainer()).getName());
		}
		String pName = msgSig.getPort().getPort();
		if (pName != null) {
			if (msgSig.eContainingFeature() == Literals.PROCESS_CLASS__RECEIVE_MESSAGES) {
				buf.append(pName);
				buf.append('?');
			}
			if (msgSig.eContainingFeature() == Literals.PROCESS_CLASS__SEND_MESSAGES) {
				buf.append(pName);
				buf.append('!');
			}
		}
		buf.append(msgSig.getName());
		formatMessageTypes(buf, msgSig.getParameters());
	}

	public static void formatMessageTypes(StringBuilder buf, List<MessageParameter> parameters) {
		List<String> types = new ArrayList<>();
		for (MessageParameter parameter : parameters) {
			types.add(parameter.getType());
		}
		formatTypes(buf, types);
	}

	private static void appendClassName(StringBuilder buf, String className) {
		if (className != null)
			buf.append(className);
		buf.append(SEPARATOR_CLASS_METHOD);
	}

	// --- Unformatting -------

	public static List<String> unformatDeclarationsToTypeNames(String formattedString) {
		List<String> declarations = new ArrayList<>();
		if (formattedString != null && !formattedString.isEmpty() && !formattedString.equals("()")) {
			String withoutBrackets = formattedString.substring(1, formattedString.length() - 1);
			String[] vars = withoutBrackets.split(",");
			for (String var : vars) {
				String[] def = var.split(":");
				if (def.length == 2 && !def[0].isEmpty() && !def[1].isEmpty()) {
					declarations.add(def[1].trim());
				}
			}
		}
		return declarations;
	}

	public static int unformatDeclarationsToSize(String formattedString) {
		if (formattedString != null && formattedString.length() > 2) {
			return formattedString.split(",").length;
		}
		return 0;
	}

	public static void formatDeclarations(StringBuilder buf, Map<String, IEObjectDescription> declarationDescriptions) {
		buf.append(PREFIX_PARAMETERS);
		boolean isFirstDeclaration = true;
		for (Entry<String, IEObjectDescription> entry : declarationDescriptions.entrySet()) {
			if (isFirstDeclaration) {
				isFirstDeclaration = false;
			} else {
				buf.append(SEPARATOR_VARIABLE);
			}
			buf.append(entry.getKey());
			buf.append(PREFIX_TYPE);
			String type = PooslDeclarationDescription.getType(entry.getValue());
			if (type != null) {
				buf.append(type);
			}
		}
		buf.append(POSTFIX_PARAMETERS);
	}
}