package CodeLinguists.codelingo.application.runtime_exceptions;

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
