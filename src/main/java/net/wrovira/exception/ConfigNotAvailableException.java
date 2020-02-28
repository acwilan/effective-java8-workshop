package net.wrovira.exception;

import static java.lang.String.format;

public class ConfigNotAvailableException extends IllegalArgumentException {
    private final String configName;

    public ConfigNotAvailableException(final String configName, final Throwable cause) {
        super(cause);
        this.configName = configName;
    }

    @Override
    public String getMessage() {
        return format("%s configuration not found", configName);
    }
}
