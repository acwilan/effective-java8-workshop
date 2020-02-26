package net.wrovira;

import net.wrovira.exception.PropertiesReadException;

import java.util.Map;

public enum ConfigDepotEnum {

    INSTANCE;

    private ConfigDepotEnum() {}

    public static ConfigDepotEnum getInstance() {
        return INSTANCE;
    }

    public Map<String, String> getConfig(String configFileName) throws PropertiesReadException {
        return ConfigDepot.INSTANCE.getConfig(configFileName);
    }

    public void clear() {
        ConfigDepot.INSTANCE.filesMapCache.clear();
    }
}
