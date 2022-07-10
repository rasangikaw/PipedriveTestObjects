package utils;

import java.util.Properties;

public class Config {

    private Properties configFile;
    private static Config instance;
    private static String path;

    private Config() {

        try {
            configFile = new Properties();
            configFile.load(this.getClass().getResourceAsStream(path));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getValue(String key) {
        return configFile.getProperty(key);
    }

    public static void setConfigFilePath(String configFilePath) {
        path = configFilePath;
        instance = null;
    }

    public static String getProperty(String key) {
        if (instance == null)
            instance = new Config();
        return instance.getValue(key);
    }
}
