package nl.esi.poosl.legacysupport.poosl2xml;

public class PooslValidationException extends Exception {

	private static final long serialVersionUID = -5184729978184948929L;
	private final String message;

	public PooslValidationException(String aMsg) {
		super();
		message = aMsg;
	}

	@Override
	public String getMessage() {
		return message;
	}

}