package cn.xidian.exception;

@SuppressWarnings("serial")
public class ClazzNotExistException extends RuntimeException{

	public ClazzNotExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClazzNotExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ClazzNotExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ClazzNotExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
