package CodeLinguists.codelingo.ui.uiExceptions;

public class SlideTypeNotHandledException extends RuntimeException {
    public SlideTypeNotHandledException(String message) {
        super(message);
    }

    public SlideTypeNotHandledException(String message, Throwable cause) {
        super(message, cause);
    }
}
