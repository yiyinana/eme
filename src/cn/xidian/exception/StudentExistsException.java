package cn.xidian.exception;

@SuppressWarnings("serial")
public class StudentExistsException extends RuntimeException {

	public StudentExistsException() {
		// TODO Auto-generated constructor stub
	}

	public StudentExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public StudentExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public StudentExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public StudentExistsException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		//super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
