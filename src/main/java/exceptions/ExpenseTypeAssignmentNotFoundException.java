package exceptions;

public class ExpenseTypeAssignmentNotFoundException extends HTTPNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExpenseTypeAssignmentNotFoundException() {
		super();
	}

	public ExpenseTypeAssignmentNotFoundException(long expenseCategoryId, long expenseTypeId) {
		super(String.format("ExpenseCategory %s and ExpenseType %s assignment could not be found.", expenseCategoryId,
				expenseTypeId));
	}
}