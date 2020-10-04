package es.uca.gii.csi20.check;

import es.uca.gii.csi20.check.data.Data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataTest {
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        Data.LoadDriver();
    }

    @Test
    void testDbAccess() throws Exception {
        try (Connection con = Data.Connection(); ResultSet rs = con.createStatement().executeQuery("SELECT Host, User FROM mysql.user;")) {

            int i = 0;
            while (rs.next()) {
                System.out.println(rs.getString("Host") + " " + rs.getString("User"));
                ++i;
            }

            assertEquals(6, i);
        }
    }
}
