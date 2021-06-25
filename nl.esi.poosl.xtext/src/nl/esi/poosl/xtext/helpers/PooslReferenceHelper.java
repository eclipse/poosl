package nl.esi.poosl.xtext.helpers;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import nl.esi.poosl.AssignmentExpression;
import nl.esi.poosl.BinaryOperatorExpression;
import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.DataMethodCallExpression;
import nl.esi.poosl.Declaration;
import nl.esi.poosl.Instance;
import nl.esi.poosl.InstanceParameter;
import nl.esi.poosl.InstancePort;
import nl.esi.poosl.InstantiableClass;
import nl.esi.poosl.MessageParameter;
import nl.esi.poosl.NewExpression;
import nl.esi.poosl.OutputVariable;
import nl.esi.poosl.Port;
import nl.esi.poosl.PortReference;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethodCall;
import nl.esi.poosl.ReceiveStatement;
import nl.esi.poosl.SendStatement;
import nl.esi.poosl.UnaryOperatorExpression;
import nl.esi.poosl.VariableExpression;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.xtext.custom.PooslCache;
import nl.esi.poosl.xtext.custom.PooslMessageType;
import nl.esi.poosl.xtext.custom.PooslTypeSystem;
import nl.esi.poosl.xtext.descriptions.PooslDataMethodDescription;
import nl.esi.poosl.xtext.descriptions.PooslMessageSignatureDescription;
import nl.esi.poosl.xtext.descriptions.PooslPortDescription;
import nl.esi.poosl.xtext.scoping.PooslScopeProvider;

public class PooslReferenceHelper {

	private PooslReferenceHelper() {
		throw new IllegalStateException("Utility class");
	}

	// --- Message References -------

	public static IEObjectDescription getSendSignatureDescription(SendStatement statement) {
		Resource resource = statement.eResource();
		ProcessClass pClass = HelperFunctions.getContainingProcessClass(statement);
		String statementMsgName = statement.getName();
		String statementPortName = ((PortReference) statement.getPortDescriptor()).getPort();
		int statementSize = statement.getArguments().size();
		String pName = pClass.getName();

		return getSignatureDescription(resource, pName, statementPortName, statementMsgName, statementSize,
				PooslMessageType.SEND);
	}

	public static IEObjectDescription getReceiveSignatureDescription(ReceiveStatement statement) {
		Resource resource = statement.eResource();
		ProcessClass pClass = HelperFunctions.getContainingProcessClass(statement);
		String statementMsgName = statement.getName();
		String statementPortName = ((PortReference) statement.getPortDescriptor()).getPort();
		int statementSize = statement.getVariables().size();
		String pName = pClass.getName();

		return getSignatureDescription(resource, pName, statementPortName, statementMsgName, statementSize,
				PooslMessageType.RECEIVE);
	}

	private static IEObjectDescription getSignatureDescription(Resource resource, String pClass, String portName,
			String messageName, int vars, PooslMessageType type) {
		for (IEObjectDescription input : PooslCache.get(resource).getMessages(pClass, type)) {
			if (portName.equals(PooslMessageSignatureDescription.getPort(input))
					&& messageName.equals(HelperFunctions.getName(input))
					&& vars == PooslMessageSignatureDescription.getParameterTypes(input).size())
				return input;
		}
		return null;
	}

	// --- Process Method References -------

	public static IEObjectDescription getProcessMethodDescription(ProcessMethodCall call) {
		String methodName = call.getMethod();
		Map<String, IEObjectDescription> methods = PooslScopeProvider
				.getScopeProcessMethodsWithInputAndOuputArguments(call);
		return methods.get(methodName);
	}

	// --- Port References -------

	public static IEObjectDescription getInstancePortDescription(InstancePort iPort) {
		Resource resource = iPort.eResource();
		Instance instance = iPort.getInstance();
		String classDef = instance.getClassDefinition();
		if (classDef != null) {
			return PooslCache.get(resource).getInstantiableClassPorts(classDef)
					.get((iPort.getPort() != null) ? iPort.getPort().getPort() : "");
		}
		return null;
	}

	public static IEObjectDescription getPortDescription(PortReference portRef) {
		String portName = portRef.getPort();

		Resource resource = portRef.eResource();
		EObject container = portRef.eContainer();
		if (container instanceof InstancePort) {
			InstancePort iPort = (InstancePort) container;
			return PooslCache.get(resource).getInstantiableClassPorts(iPort.getInstance().getClassDefinition())
					.get(portName);
		} else {
			ProcessClass pClass = HelperFunctions.getContainingProcessClass(portRef);
			if (pClass != null) {
				if (pClass.getName() != null) {
					return PooslCache.get(resource).getInstantiableClassPorts(pClass.getName()).get(portName);
				} else {
					for (Port localPort : pClass.getPorts()) {
						if (localPort.getName().equals(portName))
							return EObjectDescription.create(portName, localPort,
									PooslPortDescription.createUserData(""));
					}
					String superClass = pClass.getSuperClass();
					if (superClass != null) {
						return PooslCache.get(resource).getInstantiableClassPorts(pClass.getName()).get(portName);
					}
				}
			}
		}
		return null;
	}

	// --- Class References -------

	public static IEObjectDescription getProcessClassDescription(ProcessClass pClass) {
		String superClass = pClass.getSuperClass();
		if (superClass != null) {
			return PooslCache.get(pClass.eResource()).getProcessClass(superClass);
		} else {
			return null;
		}
	}

	public static IEObjectDescription getDataClassDescription(DataClass dClass) {
		String superClass = dClass.getSuperClass();
		if (superClass != null) {
			return PooslCache.get(dClass.eResource()).getDataClass(superClass);
		} else {
			return null;
		}
	}

	public static IEObjectDescription getInstantiableClassDescription(Instance instance) {
		String className = instance.getClassDefinition();
		return HelperFunctions.getInstantiableClass(instance.eResource(), className);
	}

	public static IEObjectDescription getDataClassDescription(NewExpression newExpr) {
		String className = newExpr.getDataClass();
		return PooslCache.get(newExpr.eResource()).getDataClass(className);
	}

	public static IEObjectDescription getDataClassDescription(DataMethod dataMethod) {
		String className = dataMethod.getReturnType();
		return PooslCache.get(dataMethod.eResource()).getDataClass(className);
	}

	public static IEObjectDescription getDataClassDescription(Declaration declaration) {
		String className = declaration.getType();
		return PooslCache.get(declaration.eResource()).getDataClass(className);
	}

	public static IEObjectDescription getDataClassDescription(MessageParameter msgParam) {
		String className = msgParam.getType();
		return PooslCache.get(msgParam.eResource()).getDataClass(className);
	}

	// --- Variable References -------

	public static IEObjectDescription getVariableDescription(AssignmentExpression assignExpr) {
		if (assignExpr.getVariable() != null) {
			return PooslScopeProvider.getScopeExpressionVariable(assignExpr)
					.getSingleElement(HelperFunctions.toQualifiedNameName(assignExpr.getVariable()));
		} else {
			return null;
		}
	}

	public static IEObjectDescription getVariableDescription(VariableExpression varExpr) {
		if (varExpr.getVariable() != null) {
			return PooslScopeProvider.getScopeExpressionVariable(varExpr)
					.getSingleElement(HelperFunctions.toQualifiedNameName(varExpr.getVariable()));
		} else {
			return null;
		}
	}

	public static IEObjectDescription getVariableDescription(InstanceParameter iParam) {
		if (iParam.getParameter() != null) {
			return PooslScopeProvider.getScopeInstanceParameterParameter(iParam)
					.getSingleElement(HelperFunctions.toQualifiedNameName(iParam.getParameter()));
		} else {
			return null;
		}
	}

	public static IEObjectDescription getVariableDescription(OutputVariable outVar) {
		if (outVar.getVariable() != null) {
			return PooslScopeProvider.getScopeOutputVariableVariable(outVar)
					.getSingleElement(HelperFunctions.toQualifiedNameName(outVar.getVariable()));
		} else {
			return null;
		}
	}

	// --- DataMethod References -------

	public static IEObjectDescription getDataMethodUnaryDescription(UnaryOperatorExpression expr,
			PooslTypeSystem pooslTypeSystem) {
		String runningClassName = pooslTypeSystem.getAndCheckExpressionType(expr.getOperand());
		String methodName = expr.getName().getLiteral();
		if (runningClassName != null && methodName != null) {
			return getDataMethod(expr.eResource(), runningClassName, methodName, 0,
					Literals.DATA_CLASS__DATA_METHODS_UNARY_OPERATOR);

		}
		return null;
	}

	public static IEObjectDescription getDataMethodBinaryDescription(BinaryOperatorExpression expr,
			PooslTypeSystem pooslTypeSystem) {
		String runningClassName = pooslTypeSystem.getAndCheckExpressionType(expr.getLeftOperand());
		String methodName = expr.getName().getLiteral();
		String argumentType = pooslTypeSystem.getAndCheckExpressionType(expr.getRightOperand());
		if (runningClassName != null && methodName != null && argumentType != null) {
			return getDataMethod(expr.eResource(), runningClassName, methodName, 1,
					Literals.DATA_CLASS__DATA_METHODS_BINARY_OPERATOR);
		}
		return null;
	}

	public static IEObjectDescription getDataMethodNamedDescription(DataMethodCallExpression expr,
			PooslTypeSystem pooslTypeSystem) {
		Resource resource = expr.eResource();
		String runningClassName = pooslTypeSystem.getAndCheckExpressionType(expr.getTarget());
		if (expr.isOnSuperClass() && runningClassName != null
				&& !HelperFunctions.CLASS_NAME_OBJECT.equals(runningClassName)) {
			runningClassName = HelperFunctions.getCorrectedDataClassExtendsAsString(resource, runningClassName);
		}

		String methodName = expr.getName();
		List<String> argumentTypeNames = pooslTypeSystem.getAndCheckExpressionsType(expr.getArguments());
		if (runningClassName != null && methodName != null && argumentTypeNames != null) {
			return getDataMethod(resource, runningClassName, methodName, argumentTypeNames.size(),
					Literals.DATA_CLASS__DATA_METHODS_NAMED);

		}
		return null;
	}

	// --- Find all Identifiers -------

	/**
	 * Find existing process method names with the same number of input and output
	 * arguments s. This method is used for renaming process methods. It returns
	 * names that are already in use (thus not allowed) and excludes the name of the
	 * method that is being renamed
	 * 
	 * @param statement
	 * @param excludedObject
	 * @return
	 */
	public static Iterable<String> getProcessMethodNamesByNumberOfInputAndOutputArguments(ProcessMethodCall call,
			final URI excludedObjectURI) {
		Iterable<IEObjectDescription> methods = PooslScopeProvider.helperscope_ProcessMethodCall_method(call)
				.getAllElements();
		return HelperFunctions.getNames(removeDescriptionWithURI(excludedObjectURI, methods));
	}

	private static Iterable<IEObjectDescription> removeDescriptionWithURI(final URI excludedObjectURI,
			Iterable<IEObjectDescription> iterable) {
		return Iterables.filter(iterable, new Predicate<IEObjectDescription>() {
			@Override
			public boolean apply(IEObjectDescription input) {
				return !input.getEObjectURI().equals(excludedObjectURI);
			}
		});
	}

	/**
	 * Find existing send signature names with the same port and number of
	 * parameters. This method is used for renaming signatures. It returns names
	 * that are already in use (thus not allowed) and excludes the name of the
	 * signature that is being renamed
	 * 
	 * @param statement
	 * @param excludedObject
	 * @return
	 */
	public static Iterable<String> getExistingSendSignatureNamesByPortAndNumberOfParameters(SendStatement statement,
			URI excludedObject) {
		Resource resource = statement.eResource();
		ProcessClass pClass = HelperFunctions.getContainingProcessClass(statement);
		String statementPortName = ((PortReference) statement.getPortDescriptor()).getPort();
		int nrArguments = statement.getArguments().size();
		return getExistingSignatureNamesByPortAndNumberOfParameters(resource, pClass, statementPortName, nrArguments,
				PooslMessageType.SEND, excludedObject);
	}

	/**
	 * Find existing receive signature names with the same port and number of
	 * parameters. This method is used for renaming signatures. It returns names
	 * that are already in use (thus not allowed) and excludes the name of the
	 * signature that is being renamed
	 * 
	 * @param statement
	 * @param excludedObject
	 * @return
	 */
	public static Iterable<String> getExistingReceiveSignatureNamesByPortAndNumberOfParameters(
			ReceiveStatement statement, URI excludedObject) {
		Resource resource = statement.eResource();
		ProcessClass pClass = HelperFunctions.getContainingProcessClass(statement);
		String statementPortName = ((PortReference) statement.getPortDescriptor()).getPort();
		int nrArguments = statement.getVariables().size();
		return getExistingSignatureNamesByPortAndNumberOfParameters(resource, pClass, statementPortName, nrArguments,
				PooslMessageType.RECEIVE, excludedObject);
	}

	private static Iterable<String> getExistingSignatureNamesByPortAndNumberOfParameters(Resource resource,
			ProcessClass pClass, String statementPortName, int nrArguments, PooslMessageType type,
			URI excludedObjectURI) {
		List<IEObjectDescription> messages = PooslCache.get(resource).getMessages(pClass.getName(), type);
		return HelperFunctions.getNames(Iterables.filter(messages,
				PooslMessageSignatureDescription.predicateMessage(statementPortName, nrArguments, excludedObjectURI)));
	}

	public static Iterable<String> getPortNames(EObject referringElement, URI excludedObjectURI) {
		return HelperFunctions.getNames(removeDescriptionWithURI(excludedObjectURI,
				PooslScopeProvider.getScopePortDescriptions(referringElement)));
	}

	public static Iterable<String> getInstantiableClassNames(EObject referringElement, URI uri) {
		return HelperFunctions.getNames(removeDescriptionWithURI(uri,
				HelperFunctions.getAllRelevantInstantiableClasses(referringElement.eResource())));
	}

	public static Iterable<String> getDataClassNames(EObject referringElement, URI uri) {
		return HelperFunctions.getNames(removeDescriptionWithURI(uri,
				PooslCache.get(referringElement.eResource()).getAllRelevantDataClasses()));
	}

	public static Iterable<String> getVariableNames(AssignmentExpression assignExpr, final URI excludedURI) {
		return HelperFunctions.getNames(removeDescriptionWithURI(excludedURI,
				PooslScopeProvider.getScopeExpressionVariable(assignExpr).getAllElements()));
	}

	public static Iterable<String> getVariableNames(VariableExpression varExpr, final URI excludedURI) {
		return HelperFunctions.getNames(removeDescriptionWithURI(excludedURI,
				PooslScopeProvider.getScopeExpressionVariable(varExpr).getAllElements()));
	}

	public static Iterable<String> getVariableNames(InstanceParameter iParam, final URI excludedURI) {
		return HelperFunctions.getNames(removeDescriptionWithURI(excludedURI,
				PooslScopeProvider.getScopeInstanceParameterParameter(iParam).getAllElements()));
	}

	public static Iterable<String> getVariableNames(OutputVariable outVar, final URI excludedURI) {
		return HelperFunctions.getNames(removeDescriptionWithURI(excludedURI,
				PooslScopeProvider.getScopeOutputVariableVariable(outVar).getAllElements()));
	}

	// ----------- Additional Helpers

	public static InstantiableClass getInstantiableClassEObject(Instance instance) {
		IEObjectDescription descr = getInstantiableClassDescription(instance);
		if (descr != null) {
			EObject obj = descr.getEObjectOrProxy();
			if (obj.eIsProxy())
				obj = EcoreUtil.resolve(obj, instance);
			return (InstantiableClass) obj;
		} else {
			return null;
		}
	}

	public static IEObjectDescription getDataMethod(Resource resource, String dClassName, String dMethodName, int args,
			EReference literal) {
		final List<String> classChain = HelperFunctions.computeDataClassChain(resource, dClassName);
		List<IEObjectDescription> methods = PooslCache.get(resource).getDataMethods(dClassName, dMethodName, args,
				literal);
		int i = -1;
		IEObjectDescription methodDescription = null;
		for (IEObjectDescription descr : methods) {
			int o = classChain.indexOf(PooslDataMethodDescription.getClassName(descr));
			if (o == 0)
				return descr;
			if (i == -1 || o < i) {
				i = o;
				methodDescription = descr;
			}
		}
		return methodDescription;
	}

	// Port Descriptions dont contain extra usable information for validation,
	// return only names avoids creating a description and then creating names out
	// of the description
	public static Set<String> getPortNames(EObject referringElement) {

		Resource resource = referringElement.eResource();
		EObject obj = referringElement;
		while ((obj != null) && !(obj instanceof InstancePort) && !(obj instanceof ProcessClass)) {
			obj = obj.eContainer();
		}
		if (obj instanceof InstancePort) {
			return PooslCache.get(resource)
					.getInstantiableClassPorts(((InstancePort) obj).getInstance().getClassDefinition()).keySet();
		} else if (obj instanceof ProcessClass) {
			ProcessClass pClass = (ProcessClass) obj;
			if (pClass.getName() != null) {
				return PooslCache.get(resource).getInstantiableClassPorts(pClass.getName()).keySet();
			} else {
				Set<String> portNames = new HashSet<>();
				for (Port port : pClass.getPorts()) {
					portNames.add(port.getName());
				}
				String superClass = pClass.getSuperClass();
				if (superClass != null)
					portNames.addAll(PooslCache.get(resource).getInstantiableClassPorts(superClass).keySet());
				return portNames;
			}
		}
		return null;
	}
}
