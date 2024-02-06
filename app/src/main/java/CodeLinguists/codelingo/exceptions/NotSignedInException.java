package CodeLinguists.codelingo.exceptions;

public class NotSignedInException extends RuntimeException {
    public NotSignedInException() {
        super("No account signed in");
    }
}
