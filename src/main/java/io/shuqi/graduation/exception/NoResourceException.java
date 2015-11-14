package io.shuqi.graduation.exception;

public class NoResourceException extends Exception {

	private static final long serialVersionUID = 7680080324430874672L;

	public NoResourceException() {
		super();
	}

	public NoResourceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoResourceException(String message) {
		super(message);
	}

	public NoResourceException(Throwable cause) {
		super(cause);
	}

	
}
