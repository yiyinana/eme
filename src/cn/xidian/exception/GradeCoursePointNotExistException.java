package cn.xidian.exception;

@SuppressWarnings("serial")
public class GradeCoursePointNotExistException extends RuntimeException{
	public GradeCoursePointNotExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GradeCoursePointNotExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public GradeCoursePointNotExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public GradeCoursePointNotExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
