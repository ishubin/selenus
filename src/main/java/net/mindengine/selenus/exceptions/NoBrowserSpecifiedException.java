package net.mindengine.selenus.exceptions;

public class NoBrowserSpecifiedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoBrowserSpecifiedException() {
		super();
	}

	public NoBrowserSpecifiedException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoBrowserSpecifiedException(String message) {
		super(message);
	}

	public NoBrowserSpecifiedException(Throwable cause) {
		super(cause);
	}

}
