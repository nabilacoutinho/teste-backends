package exceptions;

public class InvalidEventException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidEventException() {
		super ("Invalid Event");
	}

	public InvalidEventException(String message) {
		super ("Invalid Event: " + message);
	}
	
}
