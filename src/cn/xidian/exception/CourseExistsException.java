package cn.xidian.exception;

@SuppressWarnings("serial")
public class CourseExistsException extends RuntimeException{
	public CourseExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CourseExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CourseExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
