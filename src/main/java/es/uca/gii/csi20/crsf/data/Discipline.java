package es.uca.gii.csi20.crsf.data;

import es.uca.gii.csi20.crsf.util.Config;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Discipline {
  private Integer _iId;
  private String _sName;
  private Integer _iCredits;

  public Discipline(Integer iId) throws IOException, SQLException {
    try (Connection con = Data.Connection();
        ResultSet rs =
            con.createStatement()
                .executeQuery("SELECT id, name, credits FROM Discipline WHERE id = " + iId + ";")) {
      rs.next();
      _iId = rs.getInt("id");
      _sName = rs.getString("name");
      _iCredits = rs.getInt("credits");
    }
  }

  public Integer getId() {
    return _iId;
  }

  public void setId(Integer iId) {
    _iId = iId;
  }

  public Integer getCredits() {
    return _iCredits;
  }

  public void setCredits(Integer iCredits) {
    _iCredits = iCredits;
  }

  public String getName() {
    return _sName;
  }

  public void setName(String sName) {
    _sName = sName;
  }

  public static Discipline Create(String sName, Integer iCredits) throws IOException, SQLException {
    final String sStatement =
        "INSERT INTO Discipline (name, credits) VALUES ("
            + Data.String2Sql(sName, true, false)
            + ","
            + iCredits
            + ");";

    try (Connection con = Data.Connection()) {
      con.createStatement().executeUpdate(sStatement);
      Integer id = LastId(con);
      return new Discipline(id);
    }
  }

  @Override
  public String toString() {
    return super.toString() + _iId + ":" + _sName + ":" + _iCredits;
  }

  public static Integer LastId(Connection con) throws SQLException, IOException {
    Properties properties = Config.Properties(Data.getPropertiesUrl());
    try (ResultSet rs =
        con.createStatement().executeQuery(properties.getProperty("jdbc.lastIdSentence"))) {
      rs.next();
      return rs.getInt(1);
    }
  }
}
