package dk.tellings.app.exceptions;

@SuppressWarnings("serial")
public class CourseIdInvalidException extends Exception{
	
	public CourseIdInvalidException(String courseId) {
		super(courseId + " is not a valid courseId. It has to be 5 digits.");
	}
}
