package CodeLinguists.codelingo.logic.logic_exceptions;

public class InputValidationException extends Exception{
    public InputValidationException(String message) {
        super(message);
    }

    public InputValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
