package exceptions;

import enumpackage.StatusEnum;

public class InvalidStatusException extends BadRequestException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidStatusException(StatusEnum status) {
		super(String.format("invalid currentStatus %s", status));
	}

}
