package CodeLinguists.codelingo.logic.logicExceptions;

public class NoItemSelectedException extends RuntimeException{
    public NoItemSelectedException(String message) {
        super(message);
    }

    public NoItemSelectedException(String message, Throwable cause) {
        super(message, cause);
    }
}
