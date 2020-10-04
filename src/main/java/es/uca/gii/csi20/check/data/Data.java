package es.uca.gii.csi20.check.data;

import es.uca.gii.csi20.check.util.Config;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Data {
    public static String getPropertiesUrl() {
        return "db.properties";
    }

    public static Connection Connection() throws Exception {
        Properties properties = Config.Properties(getPropertiesUrl());

        return DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"),
                properties.getProperty("jdbc.password"));
    }

    public static void LoadDriver() throws InstantiationException, IOException, ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class.forName(Config.Properties(Data.getPropertiesUrl()).getProperty("jdbc.driverClassName"))
                .getDeclaredConstructor().newInstance();
    }
}