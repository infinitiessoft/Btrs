package exceptions;

public class ExpenseNotFoundException extends HTTPNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExpenseNotFoundException() {
		super();
	}

	public ExpenseNotFoundException(long id) {
		super(String.format("Expense %s could not be found.", id));
	}

}
