package by.epam.jwd.yakovlev.textparser.entity.exception;

public class WrongType extends RuntimeException {
    public WrongType() {
    }

    public WrongType(String message) {
        super(message);
    }

    public WrongType(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongType(Throwable cause) {
        super(cause);
    }

    public WrongType(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
