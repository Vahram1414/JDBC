package utils;

import java.io.IOException;
import java.util.Properties;

import static sun.awt.FontConfiguration.loadProperties;

public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesUtil() {
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {
        try(var inputStrream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStrream);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
 }
