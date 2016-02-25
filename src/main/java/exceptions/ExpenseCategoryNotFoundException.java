package exceptions;

public class ExpenseCategoryNotFoundException extends HTTPNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExpenseCategoryNotFoundException() {
		super();
	}

	public ExpenseCategoryNotFoundException(long id) {
		super(String.format("ExpenseCategory %s could not be found.", id));
	}

}
