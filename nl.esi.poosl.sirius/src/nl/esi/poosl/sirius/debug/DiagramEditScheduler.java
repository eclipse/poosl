package nl.esi.poosl.sirius.debug;

import org.eclipse.core.runtime.jobs.ISchedulingRule;

public class DiagramEditScheduler implements ISchedulingRule {

	@Override
	public boolean contains(ISchedulingRule rule) {
		return (rule instanceof DiagramEditScheduler);
	}

	@Override
	public boolean isConflicting(ISchedulingRule rule) {
		return (rule instanceof DiagramEditScheduler);
	}
}
