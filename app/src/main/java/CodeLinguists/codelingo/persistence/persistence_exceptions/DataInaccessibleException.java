package CodeLinguists.codelingo.persistence.persistence_exceptions;

public class DataInaccessibleException extends Exception{
    public DataInaccessibleException(String message) {
        super(message);
    }

    public DataInaccessibleException(String message, Throwable cause) {
        super(message, cause);
    }
}
