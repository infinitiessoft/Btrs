package exceptions;

public class PhotoNotFoundException extends HTTPNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PhotoNotFoundException() {
		super();
	}

	public PhotoNotFoundException(long id) {
		super(String.format("Photo %s could not be found.", id));
	}

}
