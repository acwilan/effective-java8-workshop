package net.wrovira;

import net.wrovira.exception.PropertiesReadException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigDepot {

    static Map<String, Map<String, String>> filesMapCache = new HashMap<>();

    public static Map<String, String> getConfig(String configFileName) throws PropertiesReadException {
        if (filesMapCache.containsKey(configFileName)) {
            return filesMapCache.get(configFileName);
        }

        final InputStream inputStream = ConfigDepot.class.getClassLoader().getResourceAsStream(configFileName);
        final Properties properties = new Properties();

        try {
            properties.load(inputStream);
        } catch (final IOException | NullPointerException exception) {
            throw new PropertiesReadException("An error occured while trying to load from config file " + configFileName, exception);
        }

        final Map<String, String> propertiesLoaded = new HashMap<>((Map) properties);
        filesMapCache.put(configFileName, propertiesLoaded);

        return propertiesLoaded;
    }

}
