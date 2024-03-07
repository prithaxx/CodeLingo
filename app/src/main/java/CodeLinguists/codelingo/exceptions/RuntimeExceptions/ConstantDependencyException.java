package CodeLinguists.codelingo.exceptions.RuntimeExceptions;

/**
 * Thrown when trying to change dependencies after initialization
 */
public class ConstantDependencyException extends RuntimeException {
    public ConstantDependencyException(String message) {
        super(message);
    }

    public ConstantDependencyException(String message, Throwable cause) {
        super(message, cause);
    }
}
