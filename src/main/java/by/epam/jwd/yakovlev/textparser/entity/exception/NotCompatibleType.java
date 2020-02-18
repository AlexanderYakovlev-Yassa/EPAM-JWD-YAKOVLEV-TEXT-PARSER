package by.epam.jwd.yakovlev.textparser.entity.exception;

public class NotCompatibleType extends Exception {

    public NotCompatibleType() {
    }

    public NotCompatibleType(String message) {
        super(message);
    }

    public NotCompatibleType(String message, Throwable cause) {
        super(message, cause);
    }

    public NotCompatibleType(Throwable cause) {
        super(cause);
    }

    public NotCompatibleType(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
