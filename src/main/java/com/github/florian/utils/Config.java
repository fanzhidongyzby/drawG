package com.github.florian.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhidong.fzd on 17/4/1.
 */
public class Config {
    private static final Logger LOG         = LoggerFactory.getLogger(Config.class);

    private static final String CONFIG_FILE = "config.properties";

    private static Properties properties  = loadProperty();

    private static Properties loadProperty() {

        Properties properties = new Properties();
        InputStream in = Config.class.getClassLoader().getResourceAsStream(CONFIG_FILE);

        try {
            properties.load(in);
        } catch (IOException e) {
            LOG.error(ExceptionUtils.getFullStackTrace(e));
        }

        return properties;
    }

    public static String getString(String key, String defaultValue) {
        if (StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        return properties.getProperty(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        if (StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        final String value = properties.getProperty(key, String.valueOf(defaultValue));
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            LOG.error(ExceptionUtils.getFullStackTrace(e));
        }

        return defaultValue;
    }

    public static double getDouble(String key, double defaultValue) {
        if (StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        final String value = properties.getProperty(key, String.valueOf(defaultValue));
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            LOG.error(ExceptionUtils.getFullStackTrace(e));
        }

        return defaultValue;
    }

    public static boolean getBoolean(String key, boolean defaultValue) {

        if (StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        final String value = properties.getProperty(key, String.valueOf(false));
        try {
            return Boolean.parseBoolean(value);
        } catch (Exception e) {
            LOG.error(ExceptionUtils.getFullStackTrace(e));
        }

        return defaultValue;
    }

}
