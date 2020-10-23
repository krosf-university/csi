package es.uca.gii.csi20.crsf.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
  private Config() {}

  public static Properties Properties(String sFile) throws IOException {
    try (InputStream inputStream = Config.class.getClassLoader().getResourceAsStream(sFile)) {
      Properties result = new Properties();
      result.load(inputStream);
      return result;
    }
  }
}
