package exceptions;

public class UserRoleNotFoundException extends HTTPNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserRoleNotFoundException() {
		super();
	}

	public UserRoleNotFoundException(long id) {
		super(String.format("UserRole %s could not be found.", id));
	}

	public UserRoleNotFoundException(long userId, long roleId) {
		super(String.format("User for Role %s to setting %s could not be found.", userId, roleId));
	}

}