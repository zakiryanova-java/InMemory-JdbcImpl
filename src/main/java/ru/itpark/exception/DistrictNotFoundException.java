package ru.itpark.exception;

public class DistrictNotFoundException extends RuntimeException {
    public DistrictNotFoundException() {
    }

    public DistrictNotFoundException(String message) {
        super(message);
    }

    public DistrictNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DistrictNotFoundException(Throwable cause) {
        super(cause);
    }

    public DistrictNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
