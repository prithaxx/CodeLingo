package CodeLinguists.codelingo.application.runtime_exceptions;

public class DBStateException extends RuntimeException{
    public DBStateException(String message) {
        super(message);
    }

    public DBStateException(String message, Throwable cause) {
        super(message, cause);
    }
}
