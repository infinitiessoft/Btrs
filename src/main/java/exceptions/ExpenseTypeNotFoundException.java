package exceptions;

public class ExpenseTypeNotFoundException extends HTTPNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExpenseTypeNotFoundException() {
		super();
	}

	public ExpenseTypeNotFoundException(long id) {
		super(String.format("ExpenseType %s could not be found.", id));
	}

}