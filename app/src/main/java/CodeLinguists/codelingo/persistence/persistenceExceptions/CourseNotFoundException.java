package CodeLinguists.codelingo.persistence.persistenceExceptions;

public class CourseNotFoundException extends Exception{
    public CourseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseNotFoundException(String message) {
        super(message);
    }
}
