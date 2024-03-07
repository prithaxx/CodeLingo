package CodeLinguists.codelingo.logic.logic_exceptions;

public class NoItemSelectedException extends Exception{
    public NoItemSelectedException(String message) {
        super(message);
    }

    public NoItemSelectedException(String message, Throwable cause) {
        super(message, cause);
    }
}
