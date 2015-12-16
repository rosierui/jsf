package org.moonwave.util;

import java.util.Properties;

public class AppProperties {

    public static String KEY_web_server_home    = "web_server_home";
    public static String KEY_outgoing_folder    = "outgoing_folder";
    public static String KEY_upload_folder      = "upload_folder";
    public static String KEY_normal_attachments = "normal_attachments";
    public static String KEY_default_timezone   = "default_timezone";
    public static String KEY_all_day_start      = "all_day_start";
    public static String KEY_all_day_end        = "all_day_end";

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
