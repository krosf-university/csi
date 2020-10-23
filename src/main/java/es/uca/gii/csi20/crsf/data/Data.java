package es.uca.gii.csi20.crsf.data;

import es.uca.gii.csi20.crsf.util.Config;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Data {
  private Data() {}

  public static String getPropertiesUrl() {
    return "db.properties";
  }

  public static Connection Connection() throws IOException, SQLException {
    Properties properties = Config.Properties(getPropertiesUrl());

    return DriverManager.getConnection(
        properties.getProperty("jdbc.url"),
        properties.getProperty("jdbc.username"),
        properties.getProperty("jdbc.password"));
  }

  public static void LoadDriver()
      throws InstantiationException, IOException, ClassNotFoundException, NoSuchMethodException,
          InvocationTargetException, IllegalAccessException {
    Class.forName(Config.Properties(Data.getPropertiesUrl()).getProperty("jdbc.driverClassName"))
        .getDeclaredConstructor()
        .newInstance();
  }

  public static String String2Sql(String sInput, boolean bAddQuotes, boolean bAddWildcards) {
    StringBuilder sb = new StringBuilder(sInput.replace("'", "''"));

    if (bAddWildcards) {
      sb.insert(0, "%");
      sb.append("%");
    }

    if (bAddQuotes) {
      sb.insert(0, "\'");
      sb.append("\'");
    }

    return sb.toString();
  }

  public static int Boolean2Sql(boolean bInput) {
    return bInput ? 1 : 0;
  }
}
