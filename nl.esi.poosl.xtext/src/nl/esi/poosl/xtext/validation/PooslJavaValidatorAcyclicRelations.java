package nl.esi.poosl.xtext.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckType;

import nl.esi.poosl.AbortStatement;
import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.DataClass;
import nl.esi.poosl.GuardedStatement;
import nl.esi.poosl.Instance;
import nl.esi.poosl.InterruptStatement;
import nl.esi.poosl.ParStatement;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.ProcessMethodCall;
import nl.esi.poosl.SelStatement;
import nl.esi.poosl.Statement;
import nl.esi.poosl.StatementSequence;
import nl.esi.poosl.xtext.custom.FormattingHelper;
import nl.esi.poosl.xtext.custom.PooslCache;
import nl.esi.poosl.xtext.descriptions.PooslClusterClassDescription;
import nl.esi.poosl.xtext.descriptions.PooslProcessClassDescription;
import nl.esi.poosl.xtext.descriptions.PooslProcessMethodDescription;
import nl.esi.poosl.xtext.helpers.HelperFunctions;
import nl.esi.poosl.xtext.helpers.PooslProcessMethodParser;

public class PooslJavaValidatorAcyclicRelations extends PooslJavaValidatorMisc {
	private static final String UNGUARDED_LOOP = "Unguarded recursive loop: ";
	private static final String CYCLIC_CLUSTER = "Cyclic cluster class dependency: ";
	private static final String CYCLIC_PROCESS = "Cyclic process class dependency: ";
	private static final String CYCLIC_DATA = "Cyclic data class dependency: ";

	private static final String ARROW = " -> ";
	private static final String SEPARATOR = "_";

	public boolean checkCyclesDataClasses(DataClass dClass) {
		Resource resource = dClass.eResource();
		String dClassName = dClass.getName();
		if (dClassName != null) {
			List<IEObjectDescription> parents = PooslCache.get(resource).getDataAncestors(dClassName);
			if (!parents.isEmpty()) {
				String lastParentName = HelperFunctions.getCorrectedDataClassExtendsAsString(parents.get(parents.size() - 1));
				if (dClassName.equals(lastParentName)) {
					// cyclic
					StringBuilder buf = new StringBuilder();
					for (IEObjectDescription description : parents) {
						buf.append(HelperFunctions.getName(description)).append(ARROW);
					}
					error(CYCLIC_DATA + dClassName + ARROW + buf.toString() + dClassName, dClass,
							Literals.DATA_CLASS__SUPER_CLASS);
					return false;
				}
			} else {
				String superClass = dClass.getSuperClass();
				if (dClassName.equals(superClass)) {
					error(CYCLIC_DATA + dClassName + ARROW + dClassName, dClass, Literals.DATA_CLASS__SUPER_CLASS);
				}
				return false;
			}
		}
		return true;
	}

	public boolean checkCyclesProcessClasses(ProcessClass pClass) {
		Resource resource = pClass.eResource();
		String pClassName = pClass.getName();
		if (pClassName != null) {
			List<IEObjectDescription> parents = PooslCache.get(resource).getProcessAncestors(pClassName);
			if (!parents.isEmpty()) {
				String lastParentName = PooslProcessClassDescription.getSuperClass(parents.get(parents.size() - 1));
				if (pClassName.equals(lastParentName)) {
					// cyclic
					StringBuilder buf = new StringBuilder();
					for (IEObjectDescription description : parents) {
						buf.append(HelperFunctions.getName(description)).append(ARROW);
					}
					error(CYCLIC_PROCESS + pClassName + ARROW + buf.toString() + pClassName, pClass,
							Literals.PROCESS_CLASS__SUPER_CLASS);
					return false;
				}
			} else {
				String superClass = pClass.getSuperClass();
				if (pClassName.equals(superClass)) {
					error(CYCLIC_PROCESS + pClassName + ARROW + pClassName, pClass,
							Literals.PROCESS_CLASS__SUPER_CLASS);
					return false;
				}
			}
		}
		return true;
	}

	public boolean checkCyclesClusterClasses(ClusterClass cClass) {
		Resource resource = cClass.eResource();
		String cClassName = cClass.getName();
		if (cClassName != null) {
			for (Instance instance : cClass.getInstances()) {
				String iClassName = instance.getClassDefinition();
				if (cClassName.equals(iClassName)) {
					error(CYCLIC_CLUSTER + cClassName + ARROW + cClassName, instance,
							Literals.INSTANCE__CLASS_DEFINITION, null);
					return false;
				} else {
					List<String> history = new ArrayList<>();
					history.add(cClassName);

					String cycle = checkCyclesClusterClassesRecursion(resource, iClassName, cClassName, history);
					if (cycle != null) {
						error(CYCLIC_CLUSTER + cClassName + ARROW + cycle, instance,
								Literals.INSTANCE__CLASS_DEFINITION, null);
						return false;
					}
				}
			}
		}
		return true;
	}

	private String checkCyclesClusterClassesRecursion(Resource resource, String iClassName, String goal,
			List<String> history) {
		history.add(iClassName);
		IEObjectDescription descr = HelperFunctions.getInstantiableClass(resource, iClassName);
		if (descr != null && descr.getEClass() == Literals.CLUSTER_CLASS) {
			Map<String, String> instances = PooslClusterClassDescription.getInstancesMap(descr);
			for (Entry<String, String> instance : instances.entrySet()) {
				String instanceClass = instance.getValue();
				if (history.contains(instanceClass)) {
					if (goal.equals(instanceClass)) {
						return iClassName + ARROW + goal;
					}
				} else {
					String cycle = checkCyclesClusterClassesRecursion(resource, instanceClass, goal, history);
					if (cycle != null) {
						return iClassName + ARROW + cycle;
					}
				}
			}
		}

		history.remove(iClassName);
		return null;
	}

	@Check(CheckType.FAST)
	public void checkUnguardedCallLoop(ProcessMethod pMethod) {
		String strPMethod = processMethod2StringNameAndParameterCounts(pMethod);
		checkUnguardedCallLoopRecursionStatement(pMethod, strPMethod, pMethod.getBody());
	}

	private void checkUnguardedCallLoopRecursionStatement(ProcessMethod pMethod, String strPMethod,
			Statement statement) {
		if (statement instanceof ProcessMethodCall) {
			ProcessMethodCall call = (ProcessMethodCall) statement;
			String pClass = HelperFunctions.getContainingProcessClass(call).getName();

			if (pClass != null) {
				Resource resource = pMethod.eResource();
				IEObjectDescription calledMethod = PooslCache.get(resource)
						.getProcessMethods(pClass, call.getInputArguments().size(), call.getOutputVariables().size())
						.get(call.getMethod());

				if (calledMethod != null) {
					String strCalledPMethod = processMethod2StringNameAndParameterCounts(calledMethod);
					if (strPMethod.equals(strCalledPMethod)) {
						StringBuilder buf = new StringBuilder();
						FormattingHelper.formatProcessMethod(buf, pMethod, false);
						buf.append(ARROW);
						FormattingHelper.formatProcessMethod(buf, pMethod, false);
						warning(UNGUARDED_LOOP + buf.toString(), statement, null, null, WarningType.UNGUARDED_LOOP);
					} else {
						List<String> history = new ArrayList<>();
						history.add(strPMethod);
						String cycle = checkUnguardedCallLoopRecursionEObjectDescription(
								(ProcessClass) pMethod.eContainer(), pMethod, strPMethod, history, calledMethod);
						if (cycle != null) {
							StringBuilder buf = new StringBuilder();
							FormattingHelper.formatProcessMethod(buf, pMethod, false);
							buf.append(ARROW);
							buf.append(cycle);
							FormattingHelper.formatProcessMethod(buf, pMethod, false);
							warning(UNGUARDED_LOOP + buf.toString(), statement, null, null, WarningType.UNGUARDED_LOOP);
						}
					}
				}
			}
			return;
		}

		if (statement instanceof StatementSequence) {
			List<Statement> sequence = ((StatementSequence) statement).getStatements();
			if (!sequence.isEmpty()) {
				checkUnguardedCallLoopRecursionStatement(pMethod, strPMethod, sequence.get(0));
			}
		} else if (statement instanceof AbortStatement) {
			checkUnguardedCallLoopRecursionStatement(pMethod, strPMethod,
					((AbortStatement) statement).getNormalClause());
			checkUnguardedCallLoopRecursionStatement(pMethod, strPMethod,
					((AbortStatement) statement).getAbortingClause());
		} else if (statement instanceof GuardedStatement) {
			checkUnguardedCallLoopRecursionStatement(pMethod, strPMethod,
					((GuardedStatement) statement).getStatement());
		} else if (statement instanceof InterruptStatement) {
			checkUnguardedCallLoopRecursionStatement(pMethod, strPMethod,
					((InterruptStatement) statement).getNormalClause());
			checkUnguardedCallLoopRecursionStatement(pMethod, strPMethod,
					((InterruptStatement) statement).getInterruptingClause());
		} else if (statement instanceof ParStatement) {
			for (Statement clause : ((ParStatement) statement).getClauses()) {
				checkUnguardedCallLoopRecursionStatement(pMethod, strPMethod, clause);
			}
		} else if (statement instanceof SelStatement) {
			for (Statement clause : ((SelStatement) statement).getClauses()) {
				checkUnguardedCallLoopRecursionStatement(pMethod, strPMethod, clause);
			}
		}
	}

	private String checkUnguardedCallLoopRecursionEObjectDescription(ProcessClass pClass, ProcessMethod pMethod,
			String strPMethod, List<String> history, IEObjectDescription calledMethod) {
		if (calledMethod == null) {
			return null;
		}

		String strCalledPMethod = processMethod2StringNameAndParameterCounts(calledMethod);
		if (history.contains(strCalledPMethod)) {
			if (strPMethod.equals(strCalledPMethod)) {
				return "";
			}
		} else {
			history.add(strCalledPMethod);

			for (PooslProcessMethodParser ungCall : PooslProcessMethodDescription
					.getUnguardedCallDescriptions(calledMethod)) {
				IEObjectDescription recCalledMethodDescr = ungCall.getCalledMethod(pClass);
				String cycle = checkUnguardedCallLoopRecursionEObjectDescription(pClass, pMethod, strPMethod, history,
						recCalledMethodDescr);
				if (cycle != null) {
					StringBuilder buf = new StringBuilder();
					FormattingHelper.formatProcessMethod(buf, calledMethod);
					buf.append(ARROW);
					buf.append(cycle);
					return buf.toString();
				}
			}

			history.remove(strCalledPMethod);
		}

		return null;
	}

	protected static String processMethod2StringNameAndParameterCounts(ProcessMethod method) {
		return method.getName() + SEPARATOR + HelperFunctions.computeNumberOfVariables(method.getInputParameters())
				+ SEPARATOR + HelperFunctions.computeNumberOfVariables(method.getOutputParameters());
	}

	private static String processMethod2StringNameAndParameterCounts(IEObjectDescription descr) {
		return HelperFunctions.getName(descr) + SEPARATOR
				+ PooslProcessMethodDescription.getNumberOfInputParameters(descr) + SEPARATOR
				+ PooslProcessMethodDescription.getNumberOfOutputParameters(descr);
	}
}
