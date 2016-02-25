package exceptions;

public class ExpenseAssignmentNotFoundException extends HTTPNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExpenseAssignmentNotFoundException() {
		super();
	}

	public ExpenseAssignmentNotFoundException(long categoryId, long typeId) {
		super(String.format("ExpenseCategory %s and ExpenseType %s assignment could not be found.", categoryId,
				typeId));
	}

}
