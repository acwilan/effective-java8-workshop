package net.wrovira;

import net.wrovira.exception.ConfigNotAvailableException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigDepot {

    public static final ConfigDepot INSTANCE = new ConfigDepot();

    Map<String, Map<String, String>> filesMapCache = new HashMap<>();

    private ConfigDepot() {}

    public Map<String, String> getConfig(final String configFileName) throws ConfigNotAvailableException {
        if (filesMapCache.containsKey(configFileName)) {
            return filesMapCache.get(configFileName);
        }

        try {
            try (final InputStream inputStream = ConfigDepot.class.getClassLoader().getResourceAsStream(configFileName)) {
                final Properties properties = new Properties();
                properties.load(inputStream);

                final Map<String, String> propertiesLoaded = new HashMap<>((Map) properties);
                filesMapCache.put(configFileName, propertiesLoaded);

                return propertiesLoaded;
            }
        } catch (final IOException | NullPointerException exception) {
            throw new ConfigNotAvailableException(configFileName, exception);
        }
    }

}
