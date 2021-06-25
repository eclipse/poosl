package nl.esi.poosl.xtext.scoping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;
import org.eclipse.xtext.scoping.impl.SimpleScope;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.DataMethodBinaryOperator;
import nl.esi.poosl.DataMethodNamed;
import nl.esi.poosl.DataMethodUnaryOperator;
import nl.esi.poosl.Declaration;
import nl.esi.poosl.Instance;
import nl.esi.poosl.InstancePort;
import nl.esi.poosl.InstantiableClass;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.ProcessMethodCall;
import nl.esi.poosl.Variable;
import nl.esi.poosl.xtext.custom.PooslCache;
import nl.esi.poosl.xtext.custom.PooslMessageType;
import nl.esi.poosl.xtext.helpers.HelperFunctions;
import nl.esi.poosl.xtext.importing.PooslResourceDescription;

/**
 * This class provides the Poosl scopes based on context EObject and the
 * EReference. The {@link #getScope(EObject, EReference)} returns the correct
 * scope. If the reference is not handles, which should never happen, it refers
 * back to the AbstractDeclarativeScopeProvider to get the scope.
 * 
 * @author kstaal
 *
 */
public class PooslScopeProvider extends AbstractDeclarativeScopeProvider {

	@Override
	public IScope getScope(EObject context, EReference reference) {

		// --- Instance -------

		if (reference == Literals.INSTANCE_PORT__INSTANCE) {
			return getScopeInstancePortInstance(context, reference);
		}

		// --- Port -------

		if (reference == Literals.CHANNEL__EXTERNAL_PORT) {
			return getScopeChannelExternalPort(context, reference);
		}

		// --- PortReference

		if (reference == Literals.INSTANCE_PORT__PORT) {
			return getScopeInstancePortPort(context);
		}
		if (reference == Literals.MESSAGE_SIGNATURE__PORT) {
			return getScopePort(context);
		}

		return IScope.NULLSCOPE;
	}

	// --- Instance -------

	private IScope getScopeInstancePortInstance(EObject context, EReference reference) {
		EObject obj = context;
		while (obj != null) {
			if (obj instanceof ClusterClass) {
				return Scopes.scopeFor(((ClusterClass) obj).getInstances());
			}
			obj = obj.eContainer();
		}
		return IScope.NULLSCOPE;
	}

	// --- Method -------

	public IScope getScopeProcessMethodCallMethod(EObject context, EReference reference) {
		EObject obj = context;
		while (obj != null) {
			if (obj instanceof ProcessClass) {
				// This is used by content assist, as method is the first token
				// from ProcessMethodCall
				return helperscope_ProcessMethodCall_method((ProcessClass) obj, reference);
			}
			if (obj instanceof ProcessMethodCall) {
				return helperscope_ProcessMethodCall_method((ProcessMethodCall) obj);
			}
			obj = obj.eContainer();
		}

		return IScope.NULLSCOPE;
	}

	public static Map<String, IEObjectDescription> getScopeProcessMethodsWithInputAndOuputArguments(
			ProcessMethodCall call) {

		Resource resource = call.eResource();
		ProcessClass pClass = HelperFunctions.getContainingProcessClass(call);
		final int numberOfInputArguments = call.getInputArguments().size();
		final int numberOfOutputVariables = call.getOutputVariables().size();

		if (pClass.getName() == null) {
			Iterable<ProcessMethod> local = Iterables.filter(pClass.getMethods(), new Predicate<ProcessMethod>() {
				@Override
				public boolean apply(ProcessMethod method) {
					return HelperFunctions
							.computeNumberOfVariables(method.getInputParameters()) == numberOfInputArguments
							&& HelperFunctions
									.computeNumberOfVariables(method.getOutputParameters()) == numberOfOutputVariables;
				}
			});
			Map<String, IEObjectDescription> map = new HashMap<>();
			for (IEObjectDescription descr : PooslResourceDescription.computeExportedProcesMethods(local, "")) {
				map.put(HelperFunctions.getName(descr), descr);
			}
			String superClass = pClass.getSuperClass();
			if (superClass != null) {
				map.putAll(PooslCache.get(resource).getProcessMethods(superClass, numberOfInputArguments,
						numberOfOutputVariables));
			}
			return map;
		} else {
			return PooslCache.get(resource).getProcessMethods(pClass.getName(), call.getInputArguments().size(),
					call.getOutputVariables().size());
		}
	}

	// --- Parameter/Variable -------

	public static IScope getScopeExpressionVariable(EObject context) {
		EObject obj = context;
		while (obj != null) {
			if (obj instanceof ClusterClass) {
				return new SimpleScope(getLocalScopeParameterDescriptions((ClusterClass) obj));
			}
			if (obj instanceof ProcessClass) {
				return helperScope_ProcessClass_parameterAndVariable((ProcessClass) obj);
			}
			if (obj instanceof ProcessMethod) {
				return helperScope_ProcessMethod_parameterAndVariable((ProcessMethod) obj);
			}
			if (obj instanceof DataMethod) {
				return helperScope_DataMethod_parameterAndVariable((DataMethod) obj);
			}

			obj = obj.eContainer();
		}

		return IScope.NULLSCOPE;
	}

	public static IScope getScopeInstanceParameterParameter(EObject context) {
		EObject obj = context;
		while ((obj != null)) {
			if (obj instanceof Instance) {
				Resource resource = obj.eResource();
				String definitionName = ((Instance) obj).getClassDefinition();
				return new SimpleScope(
						PooslCache.get(resource).getInstantiableClassParameters(definitionName).values());
			}
			obj = obj.eContainer();
		}
		return IScope.NULLSCOPE;
	}

	public static IScope getScopeOutputVariableVariable(EObject context) {
		EObject obj = context;
		while (obj != null) {
			if (obj instanceof ProcessClass) {
				return helperScope_ProcessClass_parameterAndVariable((ProcessClass) obj);
			}
			if (obj instanceof ProcessMethod) {
				return helperScope_ProcessMethod_parameterAndVariable((ProcessMethod) obj);
			}
			obj = obj.eContainer();
		}
		return IScope.NULLSCOPE;
	}

	// --- Port -------

	private IScope getScopeChannelExternalPort(EObject context, EReference reference) {
		EObject obj = context;
		while (obj != null) {
			if (obj instanceof ClusterClass) {
				InstantiableClass inst = (InstantiableClass) obj;
				return new SimpleScope(PooslResourceDescription.computeExportedPorts(inst.getPorts(),
						(inst.getName() != null) ? inst.getName() : ""));
			}
			obj = obj.eContainer();
		}
		return IScope.NULLSCOPE;
	}

	public static IScope getScopeInstancePortPort(EObject context) {
		EObject obj = context;
		while (obj != null) {
			if (obj instanceof InstancePort) {
				InstancePort iPort = (InstancePort) obj;
				Resource resource = iPort.eResource();
				String definitionName = iPort.getInstance().getClassDefinition();
				return new SimpleScope(PooslCache.get(resource).getInstantiableClassPorts(definitionName).values());
			}
			obj = obj.eContainer();
		}
		return IScope.NULLSCOPE;
	}

	public static IScope getScopePort(EObject context) {
		EObject obj = context;
		while (obj != null) {
			if (obj instanceof ProcessClass) {
				return helperScope_ProcessClass_port((ProcessClass) obj);
			}
			obj = obj.eContainer();
		}
		return IScope.NULLSCOPE;
	}

	// === Helper scopes =======

	// --- Method -------

	private static IScope helperscope_ProcessMethodCall_method(ProcessClass pClass, EReference ref) {
		Resource resource = pClass.eResource();
		if (pClass.getName() == null) {
			Iterable<IEObjectDescription> localMethods = PooslResourceDescription
					.computeExportedProcesMethods(pClass.getMethods(), "");
			String superClass = pClass.getSuperClass();
			if (superClass == null) {
				return new SimpleScope(localMethods);
			} else {
				return new SimpleScope(
						Iterables.concat(localMethods, PooslCache.get(resource).getProcessMethods(superClass)));
			}
		} else {
			return new SimpleScope(PooslCache.get(resource).getProcessMethods(pClass.getName()));
		}
	}

	public static IScope helperscope_ProcessMethodCall_method(ProcessMethodCall call) {
		ProcessClass pClass = HelperFunctions.getContainingProcessClass(call);
		final int numberOfInputArguments = call.getInputArguments().size();
		final int numberOfOutputVariables = call.getOutputVariables().size();
		Resource resource = pClass.eResource();
		if (pClass.getName() == null) {
			Iterable<ProcessMethod> local = Iterables.filter(pClass.getMethods(), new Predicate<ProcessMethod>() {
				@Override
				public boolean apply(ProcessMethod method) {
					return HelperFunctions
							.computeNumberOfVariables(method.getInputParameters()) == numberOfInputArguments
							&& HelperFunctions
									.computeNumberOfVariables(method.getOutputParameters()) == numberOfOutputVariables;
				}
			});
			Iterable<IEObjectDescription> localMethods = PooslResourceDescription.computeExportedProcesMethods(local,
					"");
			String superClass = pClass.getSuperClass();
			if (superClass == null) {
				return new SimpleScope(localMethods);
			} else {
				return new SimpleScope(Iterables.concat(localMethods, PooslCache.get(resource)
						.getProcessMethods(superClass, numberOfInputArguments, numberOfOutputVariables).values()));
			}
		} else {
			return new SimpleScope(PooslCache.get(resource)
					.getProcessMethods(pClass.getName(), numberOfInputArguments, numberOfOutputVariables).values());
		}
	}

	// --- Parameter/Variable -------

	public static IScope helperScope_DataClass_variable(DataClass dClass) {
		Resource resource = dClass.eResource();
		if (dClass.getName() == null) {
			Iterable<IEObjectDescription> localVariables = PooslResourceDescription.computeExportedDataVariables(dClass,
					"");
			String superClass = dClass.getSuperClass();
			if (superClass == null) {
				return new SimpleScope(localVariables);
			} else {
				return new SimpleScope(Iterables.concat(localVariables,
						PooslCache.get(resource).getDataClassVariables(superClass).values()));
			}
		} else {
			return new SimpleScope(PooslCache.get(resource).getDataClassVariables(dClass.getName()).values());
		}
	}

	private static IScope helperScope_DataMethod_parameterAndVariable(DataMethod dMethod) {
		DataClass dClass = (DataClass) dMethod.eContainer();
		String dClassName = (dClass.getName() != null) ? dClass.getName() : "";
		Iterable<IEObjectDescription> methodIterable = getLocalScopeVariableDescriptions(dClassName, dMethod);
		IScope classScope = helperScope_DataClass_variable(dClass);
		return new SimpleScope(classScope, methodIterable);
	}

	public static IScope helperScope_ProcessClass_parameterAndVariable(ProcessClass pClass) {
		Resource resource = pClass.eResource();
		if (pClass.getName() == null) {
			Iterable<IEObjectDescription> localVariables = PooslResourceDescription
					.computeExportedProcessVariables(pClass, "");
			String superClass = pClass.getSuperClass();
			if (superClass == null) {
				return new SimpleScope(localVariables);
			} else {
				return new SimpleScope(Iterables.concat(localVariables,
						PooslCache.get(resource).getProcessClassParametersAndVariables(superClass).values()));
			}
		} else {
			return new SimpleScope(
					PooslCache.get(resource).getProcessClassParametersAndVariables(pClass.getName()).values());
		}
	}

	public static IScope helperScope_ProcessMethod_parameterAndVariable(ProcessMethod pMethod) {
		ProcessClass pClass = (ProcessClass) pMethod.eContainer();
		IScope classScope = helperScope_ProcessClass_parameterAndVariable(pClass);
		String pClassName = (pClass.getName() != null) ? pClass.getName() : "";
		Iterable<IEObjectDescription> methodIterable = getLocalScopeVariableDescriptions(pClassName, pMethod);
		return new SimpleScope(classScope, methodIterable);
	}

	// --- Local Parameter/Variable -------

	private static final Function<Declaration, Iterable<Variable>> FUNCTION_DECLARATION_TO_VARIABLES = new Function<Declaration, Iterable<Variable>>() {
		public Iterable<Variable> apply(Declaration declaration) {
			return declaration.getVariables();
		}
	};

	public static Iterable<IEObjectDescription> getLocalScopeParameterDescriptions(ClusterClass cClass) {
		return PooslResourceDescription.computeExportedClusterVariables(cClass,
				(cClass.getName() != null) ? cClass.getName() : "");
	}

	public static Iterable<Variable> getLocalScopeParameters(ClusterClass cClass) {
		return Iterables.concat(Iterables.transform(cClass.getParameters(), FUNCTION_DECLARATION_TO_VARIABLES));
	}

	public static Iterable<Variable> getLocalScopeVariables(ProcessClass pClass) {
		Iterable<Variable> itVariables = Iterables
				.concat(Iterables.transform(pClass.getInstanceVariables(), FUNCTION_DECLARATION_TO_VARIABLES));
		Iterable<Variable> itParameters = Iterables
				.concat(Iterables.transform(pClass.getParameters(), FUNCTION_DECLARATION_TO_VARIABLES));
		return Iterables.concat(itVariables, itParameters);
	}

	public static Iterable<Variable> getLocalScopeVariables(DataClass dClass) {
		return Iterables.concat(Iterables.transform(dClass.getInstanceVariables(), FUNCTION_DECLARATION_TO_VARIABLES));
	}

	public static Iterable<Variable> getLocalScopeVariables(DataMethod dMethod) {
		Iterable<Variable> itParameters = Iterables
				.concat(Iterables.transform(dMethod.getParameters(), FUNCTION_DECLARATION_TO_VARIABLES));
		Iterable<Variable> itLocalVariables = Iterables
				.concat(Iterables.transform(dMethod.getLocalVariables(), FUNCTION_DECLARATION_TO_VARIABLES));
		return Iterables.concat(itParameters, itLocalVariables);
	}

	private static Iterable<IEObjectDescription> getLocalScopeVariableDescriptions(String dClass, DataMethod dMethod) {
		String containerName = null;
		if (dMethod instanceof DataMethodNamed) {
			containerName = ((DataMethodNamed) dMethod).getName();
		} else if (dMethod instanceof DataMethodBinaryOperator) {
			containerName = ((DataMethodBinaryOperator) dMethod).getName().getLiteral();
		} else if (dMethod instanceof DataMethodUnaryOperator) {
			containerName = ((DataMethodUnaryOperator) dMethod).getName().getLiteral();
		}
		// warning: exploiting the PooslDeclaratonDescription to store the containerName
		// as class name
		containerName = dClass + "." + ((containerName != null) ? containerName : "");
		Iterable<IEObjectDescription> itInputParameters = PooslResourceDescription
				.getVariableDescriptionsFromDeclaration(dMethod.getParameters(), containerName, false, false);
		Iterable<IEObjectDescription> itLocalVariables = PooslResourceDescription
				.getVariableDescriptionsFromDeclaration(dMethod.getLocalVariables(), containerName, false, false);
		return Iterables.concat(itInputParameters, itLocalVariables);
	}

	public static Iterable<Variable> getLocalScopeVariables(ProcessMethod pMethod) {
		Iterable<Variable> itInputParameters = Iterables
				.concat(Iterables.transform(pMethod.getInputParameters(), FUNCTION_DECLARATION_TO_VARIABLES));
		Iterable<Variable> itOutputParameters = Iterables
				.concat(Iterables.transform(pMethod.getOutputParameters(), FUNCTION_DECLARATION_TO_VARIABLES));
		Iterable<Variable> itLocalVariables = Iterables
				.concat(Iterables.transform(pMethod.getLocalVariables(), FUNCTION_DECLARATION_TO_VARIABLES));
		return Iterables.concat(itInputParameters, itOutputParameters, itLocalVariables);
	}

	private static Iterable<IEObjectDescription> getLocalScopeVariableDescriptions(String pClass,
			ProcessMethod pMethod) {
		// warning: exploiting the PooslDeclaratonDescription to store the containerName
		// as class name
		String containerName = pClass + "." + ((pMethod.getName() != null) ? pMethod.getName() : "");
		Iterable<IEObjectDescription> itInputParameters = PooslResourceDescription
				.getVariableDescriptionsFromDeclaration(pMethod.getInputParameters(), containerName, false, false);
		Iterable<IEObjectDescription> itOutputParameters = PooslResourceDescription
				.getVariableDescriptionsFromDeclaration(pMethod.getOutputParameters(), containerName, false, false);
		Iterable<IEObjectDescription> itLocalVariables = PooslResourceDescription
				.getVariableDescriptionsFromDeclaration(pMethod.getLocalVariables(), containerName, false, false);
		return Iterables.concat(itInputParameters, itOutputParameters, itLocalVariables);
	}

	// --- Port -------

	private static IScope helperScope_ProcessClass_port(ProcessClass pClass) {
		Resource resource = pClass.eResource();
		if (pClass.getName() == null) {
			Iterable<IEObjectDescription> localPorts = PooslResourceDescription.computeExportedPorts(pClass.getPorts(),
					"");
			String superClass = pClass.getSuperClass();
			if (superClass == null) {
				return new SimpleScope(localPorts);
			} else {
				return new SimpleScope(Iterables.concat(localPorts,
						PooslCache.get(resource).getInstantiableClassPorts(superClass).values()));
			}
		} else {
			return new SimpleScope(PooslCache.get(resource).getInstantiableClassPorts(pClass.getName()).values());
		}
	}

	public static Iterable<IEObjectDescription> getScopePortDescriptions(EObject referringElement) {
		EObject obj = referringElement;
		while ((obj != null) && !(obj instanceof InstancePort) && !(obj instanceof ProcessClass)) {
			obj = obj.eContainer();
		}
		if (obj instanceof InstancePort) {
			return PooslScopeProvider.getScopeInstancePortPort(referringElement).getAllElements();
		} else {
			return PooslScopeProvider.getScopePort(referringElement).getAllElements();
		}
	}

	public static List<IEObjectDescription> getScopeMessages(EObject object, PooslMessageType type) {
		Resource resource = object.eResource();
		ProcessClass pClass = HelperFunctions.getContainingProcessClass(object);
		if (pClass.getName() == null) {
			List<IEObjectDescription> descriptions = new ArrayList<>();
			PooslResourceDescription.computeExportedMessages(descriptions,
					(type == PooslMessageType.RECEIVE) ? pClass.getReceiveMessages() : pClass.getSendMessages(), "");
			if (pClass.getSuperClass() != null) {
				descriptions.addAll(PooslCache.get(resource).getMessages(pClass.getSuperClass(), type));
			}
			return descriptions;
		} else {
			return PooslCache.get(resource).getMessages(pClass.getName(), type);
		}

	}
}
