package org.moonwave.util;

import java.util.Properties;

public class AppProperties {

    public static String KEY_WEB_SERVER_HOME    = "web_server_home";
    public static String KEY_UPLOAD_FOLDER = "upload_folder";
    public static String KEY_OUTGOING_FOLDER    = "outgoing_folder";
    public static String KEY_NORMAL_ATTACHMENTS = "normalAttachments";

    Properties properties = new Properties();
    private static AppProperties instance;

    public static AppProperties getInstance() {
        if (instance == null) {
            instance = new AppProperties();
        }
        return instance;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperties(String key, String value) {
        properties.setProperty(key, value);
    }
}
