package es.uca.gii.csi20.crsf;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import es.uca.gii.csi20.crsf.data.Data;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataTest {
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        Data.LoadDriver();
    }

    @Test
    void testTableAccess() throws Exception {
        try (Connection con = Data.Connection();
                ResultSet count = con.createStatement().executeQuery("SELECT COUNT(*) FROM Discipline;");
                ResultSet entitiesSet = con.createStatement().executeQuery("SELECT * FROM Discipline;")) {
            Integer iCounter = 0;
            while (entitiesSet.next()) {
                System.out.printf("%s, %s, %s, %s\n", entitiesSet.getString(1), entitiesSet.getString(2),
                        entitiesSet.getString(3), entitiesSet.getString(4));
                ++iCounter;
            }

            count.next();
            assertEquals(count.getInt(1), iCounter);
            assertEquals(4, entitiesSet.getMetaData().getColumnCount());
        }
    }

    @Test
    public void testString2SQL() {
        assertEquals("hola", Data.String2Sql("hola", false, false));
        assertEquals("'hola'", Data.String2Sql("hola", true, false));
        assertEquals("%hola%", Data.String2Sql("hola", false, true));
        assertEquals("'%hola%'", Data.String2Sql("hola", true, true));
        assertEquals("O''Connell", Data.String2Sql("O'Connell", false, false));
        assertEquals("'O''Connell'", Data.String2Sql("O'Connell", true, false));
        assertEquals("%''Smith ''%", Data.String2Sql("'Smith '", false, true));
        assertEquals("'''Smith '''", Data.String2Sql("'Smith '", true, false));
        assertEquals("'%''Smith ''%'", Data.String2Sql("'Smith '", true, true));
    }

    @Test
    public void testBoolean2Sql() {
        assertEquals(1, Data.Boolean2Sql(true));
        assertEquals(0, Data.Boolean2Sql(false));
    }
}
