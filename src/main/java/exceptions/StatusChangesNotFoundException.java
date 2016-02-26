package exceptions;

public class StatusChangesNotFoundException extends HTTPNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StatusChangesNotFoundException() {
		super();
	}

	public StatusChangesNotFoundException(long id) {
		super(String.format("Status %s could not be found.", id));
	}

}
