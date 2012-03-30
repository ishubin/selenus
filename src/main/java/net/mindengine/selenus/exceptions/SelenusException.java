package net.mindengine.selenus.exceptions;

public class SelenusException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7082632396166412723L;

	public SelenusException() {
		super();
	}

	public SelenusException(String message, Throwable cause) {
		super(message, cause);
	}

	public SelenusException(String message) {
		super(message);
	}

	public SelenusException(Throwable cause) {
		super(cause);
	}
}
