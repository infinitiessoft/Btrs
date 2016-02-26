package exceptions;

public class ReportNotFoundException extends HTTPNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReportNotFoundException() {
		super();
	}

	public ReportNotFoundException(long id) {
		super(String.format("Report %s could not be found.", id));
	}

}
