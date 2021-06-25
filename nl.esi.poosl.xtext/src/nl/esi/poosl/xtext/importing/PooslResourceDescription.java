package nl.esi.poosl.xtext.importing;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescription;
import org.eclipse.xtext.util.IResourceScopeCache;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethodBinaryOperator;
import nl.esi.poosl.DataMethodNamed;
import nl.esi.poosl.DataMethodUnaryOperator;
import nl.esi.poosl.Declaration;
import nl.esi.poosl.MessageSignature;
import nl.esi.poosl.OperatorBinary;
import nl.esi.poosl.OperatorUnary;
import nl.esi.poosl.Poosl;
import nl.esi.poosl.Port;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.Variable;
import nl.esi.poosl.xtext.descriptions.PooslClusterClassDescription;
import nl.esi.poosl.xtext.descriptions.PooslDataClassDescription;
import nl.esi.poosl.xtext.descriptions.PooslDataMethodDescription;
import nl.esi.poosl.xtext.descriptions.PooslDeclarationDescription;
import nl.esi.poosl.xtext.descriptions.PooslDescription;
import nl.esi.poosl.xtext.descriptions.PooslMessageSignatureDescription;
import nl.esi.poosl.xtext.descriptions.PooslPortDescription;
import nl.esi.poosl.xtext.descriptions.PooslProcessClassDescription;
import nl.esi.poosl.xtext.descriptions.PooslProcessMethodDescription;

public class PooslResourceDescription extends DefaultResourceDescription {

	public PooslResourceDescription(Resource resource, IDefaultResourceDescriptionStrategy strategy,
			IResourceScopeCache cache) {
		super(resource, strategy, cache);
	}

	private Set<URI> recursiveDependencies;

	public Set<URI> getRecursiveDependencies() {
		return recursiveDependencies;
	}

	public void setRecursiveDependencies(Set<URI> recursiveDependencies) {
		this.recursiveDependencies = recursiveDependencies;
	}

	// --- Identify which aspects of the models are externally visible
	// (regarding scoping and validation)

	// the value of references is queried using NodeModelUtils to avoid
	// dereferencing (and hence loading imported files)

	@Override
	protected List<IEObjectDescription> computeExportedObjects() {
		final List<IEObjectDescription> exportedEObjects = newArrayList();

		Poosl poosl = ImportingHelper.toPoosl(getResource());
		if (poosl != null) {
			// compute exported imports
			exportedEObjects.add(EObjectDescription.create("", poosl, PooslDescription.createUserData(poosl)));

			computeExportedDataClasses(exportedEObjects, poosl);
			computeExportedProcessClasses(exportedEObjects, poosl);
			computeExportedClusterClasses(exportedEObjects, poosl);
		}

		return exportedEObjects;
	}

	private void computeExportedDataClasses(List<IEObjectDescription> exportedEObjects, Poosl poosl) {
		for (DataClass dClass : poosl.getDataClasses()) {
			String className = dClass.getName();
			if (className != null) {
				exportedEObjects.add(
						EObjectDescription.create(className, dClass, PooslDataClassDescription.createUserData(dClass)));

				// compute exported Variables
				Iterables.addAll(exportedEObjects, computeExportedDataVariables(dClass, className));

				for (DataMethodNamed dMethod : dClass.getDataMethodsNamed()) {
					String methodName = dMethod.getName();
					if (methodName != null) {
						exportedEObjects.add(EObjectDescription.create(methodName, dMethod,
								PooslDataMethodDescription.createUserData(className, dMethod)));
					}
				}
				for (DataMethodBinaryOperator dMethod : dClass.getDataMethodsBinaryOperator()) {
					OperatorBinary methodName = dMethod.getName();
					if (methodName != null) {
						exportedEObjects.add(EObjectDescription.create(methodName.getLiteral(), dMethod,
								PooslDataMethodDescription.createUserData(className, dMethod)));
					}
				}
				for (DataMethodUnaryOperator dMethod : dClass.getDataMethodsUnaryOperator()) {
					OperatorUnary methodName = dMethod.getName();
					if (methodName != null) {
						exportedEObjects.add(EObjectDescription.create(methodName.getLiteral(), dMethod,
								PooslDataMethodDescription.createUserData(className, dMethod)));
					}
				}
			}
		}
	}

	private void computeExportedProcessClasses(List<IEObjectDescription> exportedEObjects, Poosl poosl) {
		for (ProcessClass pClass : poosl.getProcessClasses()) {
			String className = pClass.getName();
			if (className != null) {
				exportedEObjects.add(EObjectDescription.create(className, pClass,
						PooslProcessClassDescription.createUserData(pClass)));

				// compute exported Variables
				Iterables.addAll(exportedEObjects, computeExportedProcessVariables(pClass, className));
				// compute exported ProcessMethods
				Iterables.addAll(exportedEObjects, computeExportedProcesMethods(pClass.getMethods(), className));
				// compute exported Ports
				Iterables.addAll(exportedEObjects, computeExportedPorts(pClass.getPorts(), className));

				computeExportedMessages(exportedEObjects, pClass.getReceiveMessages(), className);
				computeExportedMessages(exportedEObjects, pClass.getSendMessages(), className);
			}
		}
	}

	private void computeExportedClusterClasses(List<IEObjectDescription> exportedEObjects, Poosl poosl) {
		for (ClusterClass cClass : poosl.getClusterClasses()) {
			String className = cClass.getName();
			if (className != null) {
				exportedEObjects.add(EObjectDescription.create(className, cClass,
						PooslClusterClassDescription.createUserData(cClass)));

				// compute exported Variables
				Iterables.addAll(exportedEObjects, computeExportedClusterVariables(cClass, className));
				// compute exported Ports
				Iterables.addAll(exportedEObjects, computeExportedPorts(cClass.getPorts(), className));
			}
		}
	}

	public static void computeExportedDeclarations(List<IEObjectDescription> exportedEObjects,
			List<Declaration> declarations, String className, boolean isFromDataClass, boolean isParameter) {
		Iterables.addAll(exportedEObjects,
				getVariableDescriptionsFromDeclaration(declarations, className, isFromDataClass, isParameter));
	}

	public static void computeExportedMessages(List<IEObjectDescription> exportedEObjects,
			List<MessageSignature> messages, String className) {
		for (MessageSignature message : messages) {
			String messageName = message.getName();
			String portName = message.getPort().getPort();
			if (messageName != null && portName != null) {
				exportedEObjects.add(EObjectDescription.create(messageName, message,
						PooslMessageSignatureDescription.createUserData(className, message)));
			}
		}
	}

	public static Iterable<IEObjectDescription> computeExportedPorts(Iterable<Port> ports, final String pClassName) {
		Iterable<IEObjectDescription> transformed = Iterables.transform(ports,
				new Function<Port, IEObjectDescription>() {
					@Override
					public IEObjectDescription apply(Port from) {
						String portName = from.getName();
						if (portName != null)
							return EObjectDescription.create(portName, from,
									PooslPortDescription.createUserData(pClassName));
						return null;
					}
				});
		return Iterables.filter(transformed, Predicates.notNull());
	}

	public static Iterable<IEObjectDescription> computeExportedProcesMethods(Iterable<ProcessMethod> methods,
			final String pClassName) {
		Iterable<IEObjectDescription> transformed = Iterables.transform(methods,
				new Function<ProcessMethod, IEObjectDescription>() {
					@Override
					public IEObjectDescription apply(ProcessMethod pMethod) {
						String methodName = pMethod.getName();
						if (methodName != null) {
							return EObjectDescription.create(methodName, pMethod,
									PooslProcessMethodDescription.createUserData(pClassName, pMethod));
						}
						return null;
					}
				});
		return Iterables.filter(transformed, Predicates.notNull());
	}

	public static Iterable<IEObjectDescription> computeExportedProcessVariables(ProcessClass pClass,
			String pClassName) {
		return Iterables.concat(getVariableDescriptionsFromDeclaration(pClass.getParameters(), pClassName, false, true),
				getVariableDescriptionsFromDeclaration(pClass.getInstanceVariables(), pClassName, false, false));
	}

	public static Iterable<IEObjectDescription> computeExportedDataVariables(DataClass dClass, String dClassName) {
		return getVariableDescriptionsFromDeclaration(dClass.getInstanceVariables(), dClassName, true, false);
	}

	public static Iterable<IEObjectDescription> computeExportedClusterVariables(ClusterClass cClass,
			String cClassName) {
		return getVariableDescriptionsFromDeclaration(cClass.getParameters(), cClassName, false, true);
	}

	public static Iterable<IEObjectDescription> getVariableDescriptionsFromDeclaration(List<Declaration> declarations,
			final String className, final boolean isFromDataClass, final boolean isParameter) {
		Function<Declaration, Iterable<IEObjectDescription>> declarationToVariableDescriptions = new Function<Declaration, Iterable<IEObjectDescription>>() {
			public Iterable<IEObjectDescription> apply(Declaration declaration) {
				final String type = declaration.getType();
				if (type != null) {
					return Iterables.transform(declaration.getVariables(),
							getVariableToDescriptionFunction(type, className, isFromDataClass, isParameter));

				}
				return null;
			}
		};
		Iterable<Iterable<IEObjectDescription>> variableIterators = Iterables
				.filter(Iterables.transform(declarations, declarationToVariableDescriptions), Predicates.notNull());
		Iterable<IEObjectDescription> transformed = Iterables.concat(variableIterators);
		return Iterables.filter(transformed, Predicates.notNull());
	}

	private static Function<Variable, IEObjectDescription> getVariableToDescriptionFunction(final String type,
			final String className, final boolean isFromDataClass, final boolean isParameter) {
		return new Function<Variable, IEObjectDescription>() {
			@Override
			public IEObjectDescription apply(final Variable variable) {
				final Map<String, String> userData = PooslDeclarationDescription.createUserData(type, className,
						isFromDataClass, isParameter);
				final String variableName = variable.getName();
				if (variableName != null)
					return EObjectDescription.create(variableName, variable, userData);

				return null;
			}
		};
	}

}
