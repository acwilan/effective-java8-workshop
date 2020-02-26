package net.wrovira.exception;

public class PropertiesReadException extends IllegalArgumentException {
    public PropertiesReadException(final String message, final Exception exception) {
        super(message, exception);
    }
}
