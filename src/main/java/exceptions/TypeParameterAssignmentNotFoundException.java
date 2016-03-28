package exceptions;

public class TypeParameterAssignmentNotFoundException extends HTTPNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TypeParameterAssignmentNotFoundException() {
		super();
	}

	public TypeParameterAssignmentNotFoundException(long typeParameterId, long typeId) {
		super(String.format("ExpenseType %s and TypeParameter %s assignment could not be found.", typeParameterId,
				typeId));
	}

}
