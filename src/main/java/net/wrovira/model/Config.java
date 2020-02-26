package net.wrovira.model;

import static java.lang.String.format;

public class Config {

    private final String protocol;
    private final String host;
    private final String port;
    private final String contextRoot;
    private final boolean useCache;

    private Config(final Builder builder) {
        if (builder.contextRoot == null) {
            throw new IllegalArgumentException("Context root must not be null");
        }
        protocol = builder.protocol;
        host = builder.host;
        port = builder.port;
        contextRoot = builder.contextRoot;
        useCache = builder.useCache;
    }

    @Override
    public String toString() {
        return format("%s://%s:%s/%s/?useCache=%b", protocol, host, port, contextRoot, useCache);
    }

    public static final class Builder {
        private String protocol;
        private String host;
        private String port;
        private final String contextRoot;
        private boolean useCache;

        public Builder(final String contextRoot) {
            protocol = "http";
            port = "80";
            this.contextRoot = contextRoot;
        }

        public Builder withProtocol(final String protocol) {
            this.protocol = protocol;
            return this;
        }

        public Builder withHost(final String host) {
            this.host = host;
            return this;
        }

        public Builder withPort(final String port) {
            this.port = port;
            return this;
        }

        public Builder withUseCache(final boolean useCache) {
            this.useCache = useCache;
            return this;
        }

        public Config build() {
            return new Config(this);
        }
    }
}
