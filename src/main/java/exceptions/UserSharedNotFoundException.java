package exceptions;

public class UserSharedNotFoundException extends HTTPNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserSharedNotFoundException() {
		super();
	}

	public UserSharedNotFoundException(long id) {
		super(String.format("UserShared %s could not be found.", id));
	}

}
