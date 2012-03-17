package net.mindengine.selenus.exceptions;

public class InvalidPageObjectException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2575316973347078092L;

	public InvalidPageObjectException() {
		super();
	}

	public InvalidPageObjectException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPageObjectException(String message) {
		super(message);
	}

	public InvalidPageObjectException(Throwable cause) {
		super(cause);
	}

}
