package exceptions;

public class ParameterNotFoundException extends HTTPNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParameterNotFoundException() {
		super();
	}

	public ParameterNotFoundException(long id) {
		super(String.format("Parameter %s could not be found.", id));
	}

}
