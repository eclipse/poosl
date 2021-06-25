package nl.esi.poosl.xtext.helpers;

public class PooslInstanceHelper {
	private String aClassName;
	private String iName;

	public PooslInstanceHelper(String aClassName, String iName) {
		this.aClassName = aClassName;
		this.iName = iName;
	}

	public String getArchitecturalClassName() {
		return aClassName;
	}

	public String getInstanceName() {
		return iName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PooslInstanceHelper) {
			PooslInstanceHelper instance = (PooslInstanceHelper) obj;
			return instance.getArchitecturalClassName().equals(this.getArchitecturalClassName())
					&& instance.getInstanceName().equals(this.getInstanceName());
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return getArchitecturalClassName().hashCode() + getInstanceName().hashCode();
	}
}