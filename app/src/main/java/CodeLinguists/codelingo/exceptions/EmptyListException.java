package CodeLinguists.codelingo.exceptions;

public class EmptyListException extends Exception{
    public EmptyListException(String message) {
        super(message);
    }

    public EmptyListException(String message, Throwable cause) {
        super(message, cause);
    }
}
