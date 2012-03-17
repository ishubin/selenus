package net.mindengine.selenus.exceptions;

public class InvalidPageException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1386029728720610126L;

	public InvalidPageException() {
		super();
	}

	public InvalidPageException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPageException(String message) {
		super(message);
	}

	public InvalidPageException(Throwable cause) {
		super(cause);
	}

}
