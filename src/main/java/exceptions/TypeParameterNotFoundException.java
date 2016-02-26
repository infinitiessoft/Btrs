package exceptions;

public class TypeParameterNotFoundException extends HTTPNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TypeParameterNotFoundException() {
		super();
	}

	public TypeParameterNotFoundException(long id) {
		super(String.format("Type %s could not be found.", id));
	}
}
