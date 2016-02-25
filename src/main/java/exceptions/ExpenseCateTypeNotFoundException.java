package exceptions;

public class ExpenseCateTypeNotFoundException extends HTTPNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExpenseCateTypeNotFoundException() {
		super();
	}

	public ExpenseCateTypeNotFoundException(long id) {
		super(String.format("ExpenseCateType %s could not be found.", id));
	}

	public ExpenseCateTypeNotFoundException(long expenseCategoryId, long expenseTypeId) {
		super(String.format("ExpenseCategory for ExpenseType %s to setting %s could not be found.", expenseCategoryId,
				expenseTypeId));
	}

}
