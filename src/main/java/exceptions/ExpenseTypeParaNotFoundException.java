package exceptions;

public class ExpenseTypeParaNotFoundException extends HTTPNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExpenseTypeParaNotFoundException() {
		super();
	}

	public ExpenseTypeParaNotFoundException(long id) {
		super(String.format("ExpenseTypePara %s could not be found.", id));
	}

	public ExpenseTypeParaNotFoundException(long expenseTypeId, long parameterId) {
		super(String.format("ExpenseCategory for ExpenseType %s to setting %s could not be found.", expenseTypeId,
				parameterId));
	}

}
