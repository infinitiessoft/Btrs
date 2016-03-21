package exceptions;

import enumpackage.StatusEnum;

public class InvalidValueException extends BadRequestException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidValueException(StatusEnum statusEnum) {
		super(String.format("invalid value %s", statusEnum));
	}
}
