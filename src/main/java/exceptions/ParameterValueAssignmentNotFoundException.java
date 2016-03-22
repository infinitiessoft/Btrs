package exceptions;

public class ParameterValueAssignmentNotFoundException extends HTTPNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParameterValueAssignmentNotFoundException() {
		super();
	}

	public ParameterValueAssignmentNotFoundException(long parameterId, long typeId) {
		super(String.format("ExpenseType %s and ParameterValue %s assignment could not be found.", parameterId,
				typeId));
	}

}
