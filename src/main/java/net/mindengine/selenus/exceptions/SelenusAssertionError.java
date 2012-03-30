package net.mindengine.selenus.exceptions;

public class SelenusAssertionError extends SelenusException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1080778669757598908L;

	public SelenusAssertionError() {
		super();
	}

	public SelenusAssertionError(String message, Throwable cause) {
		super(message, cause);
	}

	public SelenusAssertionError(String message) {
		super(message);
	}

	public SelenusAssertionError(Throwable cause) {
		super(cause);
	}
}
