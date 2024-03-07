package CodeLinguists.codelingo.exceptions;

public class InputValidationException extends RuntimeException{
    public InputValidationException(String message) {
        super(message);
    }

    public InputValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
