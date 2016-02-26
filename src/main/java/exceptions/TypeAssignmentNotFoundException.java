package exceptions;

public class TypeAssignmentNotFoundException extends HTTPNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TypeAssignmentNotFoundException() {
		super();
	}

	public TypeAssignmentNotFoundException(long parameterId, long typeId) {
		super(String.format("ExpenseType %s and ParameterValue %s assignment could not be found.", parameterId,
				typeId));
	}

}
