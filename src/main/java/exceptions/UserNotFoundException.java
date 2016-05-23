package exceptions;

import java.util.Set;

import entity.Report;

public class UserNotFoundException extends HTTPNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(long id) {
		super(String.format("User %s could not be found.", id));
	}

	public UserNotFoundException(Set<Report> id) {
		super(String.format("User %s could not be found.", id));
	}

}
