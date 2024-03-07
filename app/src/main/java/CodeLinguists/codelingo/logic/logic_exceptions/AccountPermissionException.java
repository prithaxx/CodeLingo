package CodeLinguists.codelingo.logic.logic_exceptions;

public class AccountPermissionException extends Exception{
    public AccountPermissionException(String message) {
        super(message);
    }

    public AccountPermissionException(String message, Throwable cause) {
        super(message, cause);
    }
}
