package es.uca.gii.csi20.crsf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import es.uca.gii.csi20.crsf.data.Data;
import es.uca.gii.csi20.crsf.data.Discipline;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DisciplineTest {
  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    Data.LoadDriver();
  }

  @Test
  void testConstructor() throws Exception {
    Discipline discipline = new Discipline(1);

    assertEquals(1, discipline.getId());
    assertEquals("CSI", discipline.getName());
    assertEquals(6, discipline.getCredits());
  }

  @Test
  void testConstructorExeption() {
    assertThrows(
        SQLException.class,
        () -> {
          new Discipline(9999);
        });
  }

  @Test
  void testCreate() throws Exception {
    Discipline discipline = Discipline.Create("VS", 12);

    assertEquals(2, discipline.getId());
    assertEquals("VS", discipline.getName());
    assertEquals(6, discipline.getCredits());
  }
}
