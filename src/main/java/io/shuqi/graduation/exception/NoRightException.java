package io.shuqi.graduation.exception;

/**
 * 没有权限异常
 * @author shuqi
 * @date   2015年5月2日
 * @version since 1.0
 */
public class NoRightException extends Exception{

	private static final long serialVersionUID = -4982364999970978891L;

	public NoRightException() {
		super();
	}

	public NoRightException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoRightException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoRightException(String message) {
		super(message);
	}

	public NoRightException(Throwable cause) {
		super(cause);
	}
	
}
