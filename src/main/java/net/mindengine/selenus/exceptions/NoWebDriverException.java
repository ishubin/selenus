package net.mindengine.selenus.exceptions;

public class NoWebDriverException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4359068967957709878L;

	public NoWebDriverException() {
		super();
	}

	public NoWebDriverException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoWebDriverException(String message) {
		super(message);
	}

	public NoWebDriverException(Throwable cause) {
		super(cause);
	}
}
