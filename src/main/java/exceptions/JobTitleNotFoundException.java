package exceptions;

public class JobTitleNotFoundException extends HTTPNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JobTitleNotFoundException() {
		super();
	}

	public JobTitleNotFoundException(long id) {
		super(String.format("JobTitle %s could not be found.", id));
	}

}
