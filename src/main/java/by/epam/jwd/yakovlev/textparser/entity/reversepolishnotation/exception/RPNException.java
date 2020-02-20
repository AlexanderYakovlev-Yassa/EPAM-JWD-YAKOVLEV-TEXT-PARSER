package by.epam.jwd.yakovlev.textparser.entity.reversepolishnotation.exception;

public class RPNException extends Exception {

    public RPNException() {
    }

    public RPNException(String message) {
        super(message);
    }

    public RPNException(String message, Throwable cause) {
        super(message, cause);
    }

    public RPNException(Throwable cause) {
        super(cause);
    }

    public RPNException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
