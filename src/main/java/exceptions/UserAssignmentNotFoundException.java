package exceptions;

public class UserAssignmentNotFoundException extends HTTPNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAssignmentNotFoundException() {
		super();
	}

	public UserAssignmentNotFoundException(long userId, long roleId) {
		super(String.format("User %s and Role %s assignment could not be found.", userId, roleId));
	}

}
